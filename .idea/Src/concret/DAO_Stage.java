package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import dao.DAO;
import bean.Stage;
import bean.Technologie;
import bean.Utilisateur;

import java.util.ArrayList;

public class DAO_Stage extends DAO<Stage> {
	public boolean create(Stage obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `stages` (`owner_id`, `superviseur_id`, `suiveur_id`, `proposition_id`, `dateDebut`, `dateFin`, `pointsTotaux`, `commentaires`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getOwner().getId());
			prepare.setInt(2, obj.getSuperviseur().getId());
			prepare.setInt(3, obj.getSuiveur().getId());
			prepare.setInt(4, obj.getProposition().getId());
			prepare.setDate(5, new Date(obj.getDateDebut().getTime()));
			prepare.setDate(6, new Date(obj.getDateFin().getTime()));
			prepare.setDouble(7, obj.getPoints());
			prepare.setString(8, obj.getCommentaire());
			prepare.executeUpdate();
			
			generatedKeys = prepare.getGeneratedKeys();
			if(generatedKeys.next()) {
				obj.setId(generatedKeys.getInt(1));
				clearTechnologies(obj);
				for(Technologie t : obj.getTechnologies())
					addTechnologie(obj, t);
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
	
	private boolean addTechnologie(Stage obj, Technologie obj1) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `technologies_utilisation` (`stage_id`, `techno_id`) VALUES (?, ?)"
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
	
	private ArrayList<Technologie> loadTechnologies(int id) {
		ArrayList<Technologie> technologies = new ArrayList<Technologie>();
		DAO_Technologie dao_techno = new DAO_Technologie();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT `techno_id` FROM `technologies_utilisation` WHERE `stage_id` = ?"
			);
			
			prepare.setInt(1, id);
			res = prepare.executeQuery();
			while(res.next())
				technologies.add(dao_techno.find(res.getInt("techno_id")));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return technologies;
	}
	
	private boolean clearTechnologies(Stage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `technologies_utilisation` WHERE `stage_id` = ?"
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
	 * Methode permettant de récupérer tout les stages
	 * @param maitreDeStage Le maitre de stage dont on désire n'afficher que les stages qui le concerne (null pour tout afficher)
	 * @return liste de stages
	 */
	public ArrayList<Stage> fetchAll(Utilisateur maitreDeStage) {
		ArrayList<Stage> liste = new ArrayList<Stage>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_PropositionStage dao_prop = new DAO_PropositionStage();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			String query = "";
			if(maitreDeStage != null)
				query = " WHERE `suiveur_id` = ?";
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `stages`"+query
			);
			
			if(maitreDeStage != null)
				prepare.setInt(1, maitreDeStage.getId());
			
			res = prepare.executeQuery();
			while(res.next()) {
				Stage stage = new Stage(
					res.getInt("id"),
					dao_user.find(res.getInt("owner_id")),
					dao_user.find(res.getInt("superviseur_id")),
					dao_user.find(res.getInt("suiveur_id")),
					dao_prop.find(res.getInt("proposition_id")),
					res.getDate("dateDebut"),
					res.getDate("dateFin"),
					res.getDouble("pointsTotaux"),
					res.getString("commentaires"),
					loadTechnologies(res.getInt("id"))
				);
				liste.add(stage);
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
	
	public Stage find(int id) {
		Stage stage = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_PropositionStage dao_prop = new DAO_PropositionStage();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `stages` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				stage = new Stage(
					id,
					dao_user.find(res.getInt("owner_id")),
					dao_user.find(res.getInt("superviseur_id")),
					dao_user.find(res.getInt("suiveur_id")),
					dao_prop.find(res.getInt("proposition_id")),
					res.getDate("dateDebut"),
					res.getDate("dateFin"),
					res.getDouble("pointsTotaux"),
					res.getString("commentaires"),
					loadTechnologies(res.getInt("id"))
				);
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
		return stage;
	}
	
	public boolean update(Stage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `stages` SET `superviseur_id` = ?, `suiveur_id` = ?, `dateDebut` = ?, `dateFin` = ?, `pointsTotaux` = ?, `commentaires` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getSuperviseur().getId());
			prepare.setInt(2, obj.getSuiveur().getId());
			prepare.setDate(3, new Date(obj.getDateDebut().getTime()));
			prepare.setDate(4, new Date(obj.getDateFin().getTime()));
			prepare.setDouble(5, obj.getPoints());
			prepare.setString(6, obj.getCommentaire());
			prepare.setInt(7, obj.getId());
			prepare.executeUpdate();
			
			clearTechnologies(obj);
			for(Technologie t : obj.getTechnologies())
				addTechnologie(obj, t);
			
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
	
	public boolean delete(Stage obj) {
		PreparedStatement prepare = null;
		try {
			this.connect.setAutoCommit(false);
			clearTechnologies(obj);
			prepare = this.connect.prepareStatement(
				"DELETE FROM `stages` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			this.connect.commit(); // je ne supprime les technologiques QUE SI la suppression du stage est ok (et inverse)
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