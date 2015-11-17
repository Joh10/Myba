package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import dao.DAO;
import bean.Echeance;
import bean.Stage;
import bean.TFE;
import bean.Utilisateur;

import java.util.ArrayList;

public class DAO_Echeance extends DAO<Echeance> {
	public boolean create(Echeance obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `echeances` (`owner_id`, `tfe_id`, `stage_id`, `date_creation`, `date_echeance`, `description`, `annexe`) VALUES (?, ?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1,  obj.getOwner().getId());
			if(obj.getTFE() == null)
				prepare.setNull(2, Types.INTEGER);
			else
				prepare.setInt(2, obj.getTFE().getId());
			if(obj.getStage() == null)
				prepare.setNull(3, Types.INTEGER);
			else
				prepare.setInt(3, obj.getStage().getId());
			prepare.setTimestamp(4, new Timestamp(obj.getDateCreation().getTime()));
			prepare.setTimestamp(5, new Timestamp(obj.getDateEcheance().getTime()));
			prepare.setString(6, obj.getDescription());
			prepare.setString(7, obj.getAnnexe());
			prepare.executeUpdate();
			
			generatedKeys = prepare.getGeneratedKeys();
			if(generatedKeys.next()) {
				obj.setId(generatedKeys.getInt(1));
				if(obj.getUsers() != null) {
					for(Utilisateur u : obj.getUsers())
						addUser(obj, u);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				prepare.close();
				generatedKeys.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return true;
	}
	
	private boolean addUser(Echeance obj, Utilisateur obj1) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `echeances_cibles` (`echeance_id`, `user_id`) VALUES (?, ?)"
			);
			prepare.setInt(1, obj.getId());
			prepare.setInt(2, obj1.getId());
			prepare.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				prepare.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return true;
	}
	
	private ArrayList<Utilisateur> loadUsers(int id) {
		ArrayList<Utilisateur> users = new ArrayList<Utilisateur>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT `user_id` FROM `echeances_cibles` WHERE `echeance_id` = ?"
			);
			
			prepare.setInt(1, id);
			ResultSet res = prepare.executeQuery();
			while(res.next())
				users.add(dao_user.find(res.getInt("user_id")));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return users;
	}
	
	private boolean clearUsers(Echeance obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `echeances_cibles` WHERE `echeance_id` = ?"
			);
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				prepare.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return true;
	}
	
	/**
	 * Méthode permettant de récupérer toutes les échéances liées à un stage
	 * @param stage Le stage dont on désire récupérer les échéances
	 * @return une liste d'échéances
	 */
	public ArrayList<Echeance> fetchAll(Stage stage) {
		return fetchAllBack("SELECT * FROM `echeances` WHERE `stage_id` = ?", stage.getId(), false);
	}
	
	/**
	 * Méthode permettant de récupérer toutes les échéances liées à un TFE
	 * @param tfe Le TFE dont on désire récupérer les échéances
	 * @return une liste d'échéances
	 */
	public ArrayList<Echeance> fetchAll(TFE tfe) {
		return fetchAllBack("SELECT * FROM `echeances` WHERE `tfe_id` = ?", tfe.getId(), false);
	}
	
	/**
	 * Méthode permettant de récupérer toutes les échéances liées à un utilisateur
	 * @param utilisateur L'utilisateur dont on désire récupérer les échéances
	 * @return une liste d'échéances
	 */
	public ArrayList<Echeance> fetchAll(Utilisateur utilisateur) {
		return fetchAllBack(
				"SELECT e.* FROM `echeances` e, `echeances_cibles` c WHERE e.`tfe_id` is null AND e.`stage_id` is null AND e.`id` = c.`echeance_id` AND c.`user_id` = ? "
						+ "UNION SELECT e.* FROM `echeances` e, `tfe` t WHERE e.`tfe_id` is not null AND e.`tfe_id` = t.`id` AND t.`owner_id` = ? "
						+ "UNION SELECT e.* FROM `echeances` e, `stages` s WHERE e.`stage_id` is not null AND e.`stage_id` = s.`id` AND s.`owner_id` = ?"
				, utilisateur.getId(), true);
	}
	
	public ArrayList<Echeance> fetchAll() {
		return fetchAllBack("SELECT * FROM `echeances`", null, false);
	}
	
	private ArrayList<Echeance> fetchAllBack(String query, Integer param, boolean repeat) {
		ArrayList<Echeance> liste = new ArrayList<Echeance>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(query);
			int repeatNB = (param == null) ? 0 : (repeat) ? 3 : 1;
			for(int i=1;i<=repeatNB;i++)
				prepare.setInt(i, param);
			
			res = prepare.executeQuery();
			while(res.next()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				Echeance echeance = null;
				if(!res.wasNull())
					echeance = new Echeance(
						res.getInt("id"),
						dao_user.find(res.getInt("owner_id")),
						res.getTimestamp("date_creation"),
						res.getTimestamp("date_echeance"),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getString("description"),
						res.getString("annexe")
					);
				else {
					res.getInt("stage_id"); // nécessaire pour faire le wasNull
					if(res.wasNull())
						echeance = new Echeance(
							res.getInt("id"),
							dao_user.find(res.getInt("owner_id")),
							res.getTimestamp("date_creation"),
							res.getTimestamp("date_echeance"),
							loadUsers(res.getInt("id")),
							res.getString("description"),
							res.getString("annexe")
						);
					else
						echeance = new Echeance(
							res.getInt("id"),
							dao_user.find(res.getInt("owner_id")),
							res.getTimestamp("date_creation"),
							res.getTimestamp("date_echeance"),
							dao_stage.find(res.getInt("stage_id")),
							res.getString("description"),
							res.getString("annexe")
						);
				}
				liste.add(echeance);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return liste;
	}
	
	public Echeance find(int id) {
		Echeance echeance = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `echeances` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				if(!res.wasNull())
					echeance = new Echeance(
						res.getInt("id"),
						dao_user.find(res.getInt("owner_id")),
						res.getTimestamp("date_creation"),
						res.getTimestamp("date_echeance"),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getString("description"),
						res.getString("annexe")
					);
				else {
					res.getInt("stage_id"); // nécessaire pour faire le wasNull
					if(res.wasNull())
						echeance = new Echeance(
							res.getInt("id"),
							dao_user.find(res.getInt("owner_id")),
							res.getTimestamp("date_creation"),
							res.getTimestamp("date_echeance"),
							loadUsers(res.getInt("id")),
							res.getString("description"),
							res.getString("annexe")
						);
					else
						echeance = new Echeance(
							res.getInt("id"),
							dao_user.find(res.getInt("owner_id")),
							res.getTimestamp("date_creation"),
							res.getTimestamp("date_echeance"),
							dao_stage.find(res.getInt("stage_id")),
							res.getString("description"),
							res.getString("annexe")
						);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return echeance;
	}
	
	public boolean update(Echeance obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `echeances` SET `date_echeance` = ?, `description` = ?, `annexe` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setTimestamp(1, new Timestamp(obj.getDateEcheance().getTime()));
			prepare.setString(2, obj.getDescription());
			prepare.setString(3, obj.getAnnexe());
			prepare.setInt(4, obj.getId());
			prepare.executeUpdate();
			
			if(obj.getUsers() != null) {
				clearUsers(obj);
				for(Utilisateur u : obj.getUsers())
					addUser(obj, u);
			}
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return false;
	}
	
	public boolean delete(Echeance obj) {
		PreparedStatement prepare = null;
		try {
			this.connect.setAutoCommit(false);
			clearUsers(obj);
			prepare = this.connect.prepareStatement(
				"DELETE FROM `echeances` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			this.connect.commit(); // je ne supprime les cibles QUE SI la suppression de l'échéance est ok (et inverse)
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				this.connect.rollback();
			} catch(SQLException f) { }
		} finally {
			try {
				this.connect.setAutoCommit(true);
				prepare.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return false;
	}
}