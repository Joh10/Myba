package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import bean.LieuStage;

import java.util.ArrayList;

public class DAO_LieuStage extends DAO<LieuStage> {
	public boolean create(LieuStage obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `stages_lieux` (`owner_id`, `entreprise`, `adresse`, `personneContact`, `telephone`, `email`) VALUES (?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getOwner().getId());
			prepare.setString(2, obj.getNom());
			prepare.setString(3, obj.getAdresse());
			prepare.setString(4, obj.getContact());
			prepare.setString(5, obj.getTelephone());
			prepare.setString(6, obj.getEmail());
			prepare.executeUpdate();
			
			generatedKeys = prepare.getGeneratedKeys();
			if(generatedKeys.next())
				obj.setId(generatedKeys.getInt(1));
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
	
	/**
	 * Methode permettant de récupérer tout les lieux de stages (entreprises)
	 * @return liste de lieux de stages
	 */
	public ArrayList<LieuStage> fetchAll() {
		ArrayList<LieuStage> liste = new ArrayList<LieuStage>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `stages_lieux`"
			);
			
			res = prepare.executeQuery();
			while(res.next()) {
				LieuStage lieu = new LieuStage(
					res.getInt("id"),
					dao_user.find(res.getInt("owner_id")),
					res.getString("entreprise"),
					res.getString("adresse"),
					res.getString("personneContact"),
					res.getString("telephone"),
					res.getString("email")
				);
				liste.add(lieu);
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
	
	public LieuStage find(int id) {
		LieuStage lieu = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `stages_lieux` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				lieu = new LieuStage(
					id,
					dao_user.find(res.getInt("owner_id")),
					res.getString("entreprise"),
					res.getString("adresse"),
					res.getString("personneContact"),
					res.getString("telephone"),
					res.getString("email")
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
		return lieu;
	}
	
	public boolean update(LieuStage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `stages_lieux` SET `entreprise` = ?, `adresse` = ?, `personneContact` = ?, `telephone` = ?, `email` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getAdresse());
			prepare.setString(3, obj.getContact());
			prepare.setString(4, obj.getTelephone());
			prepare.setString(5, obj.getEmail());
			prepare.setInt(6, obj.getId());
			return (prepare.executeUpdate() == 1);
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
	
	public boolean delete(LieuStage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `stages_lieux` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getId());
			return (prepare.executeUpdate() == 1);
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
}