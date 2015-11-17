package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import dao.DAO;
import bean.Defense;
import bean.Utilisateur;

import java.util.ArrayList;

public class DAO_Defense extends DAO<Defense> {
	public boolean create(Defense obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `defenses` (`user_id`, `tfe_id`, `stage_id`, `date`, `local`) VALUES (?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getPresident().getId());
			if(obj.getTFE() == null)
				prepare.setNull(2, Types.INTEGER);
			else
				prepare.setInt(2, obj.getTFE().getId());
			if(obj.getStage() == null)
				prepare.setNull(3, Types.INTEGER);
			else
				prepare.setInt(3, obj.getStage().getId());
			prepare.setTimestamp(4, new Timestamp(obj.getDate().getTime()));
			prepare.setString(5, obj.getLocal());
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
	 * Méthode permettant de récupérer toutes les défenses
	 * @param type type de la recherche (1 = défenses gérées par président de jury, 2 = défenses d'un étudiant)
	 * @param obj l'utilisateur (président de jury/étudiant) de la recherche
	 * @return liste de défense
	 */
	public ArrayList<Defense> fetchAll(int type, Utilisateur obj) {
		ArrayList<Defense> liste = new ArrayList<Defense>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			String query;
			switch(type) {
				case 1: // recherche par président de Jury
					query = "SELECT * FROM `defenses` WHERE `user_id` = ?";
					break;
				case 2: // recherche par étudiant
					query = "SELECT d.* FROM `defenses` d, `stages` s, `tfe` t WHERE (d.`tfe_id` is not null AND d.`tfe_id` = t.`id` AND t.`owner_id` = ?) OR (d.`stage_id` is not null AND d.`stage_id` = s.`id` AND s.`owner_id` = ?)";
					break;
				default:
					query = "SELECT * FROM `defenses`";
					break;
			}
			
			prepare = this.connect.prepareStatement(query);
			if(type > 0)
				prepare.setInt(1, obj.getId());
			if(type == 2)
				prepare.setInt(2, obj.getId());
			
			res = prepare.executeQuery();
			while(res.next()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				Defense defense = null;
				if(res.wasNull())
					defense = new Defense(
						res.getInt("id"),
						dao_user.find(res.getInt("user_id")),
						dao_stage.find(res.getInt("stage_id")),
						res.getTimestamp("date"),
						res.getString("local")
					);
				else
					defense = new Defense(
						res.getInt("id"),
						dao_user.find(res.getInt("user_id")),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getTimestamp("date"),
						res.getString("local")
					);
				liste.add(defense);
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
	
	public Defense find(int id) {
		Defense defense = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `defenses` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				if(res.wasNull())
					defense = new Defense(
						res.getInt("id"),
						dao_user.find(res.getInt("user_id")),
						dao_stage.find(res.getInt("stage_id")),
						res.getTimestamp("date"),
						res.getString("local")
					);
				else
					defense = new Defense(
						res.getInt("id"),
						dao_user.find(res.getInt("user_id")),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getTimestamp("date"),
						res.getString("local")
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
		return defense;
	}
	
	public boolean update(Defense obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `defenses` SET `user_id` = ?, `date` = ?, `local` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getPresident().getId());
			prepare.setTimestamp(2, new Timestamp(obj.getDate().getTime()));
			prepare.setString(3, obj.getLocal());
			prepare.setInt(4, obj.getId());
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
	
	public boolean delete(Defense obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `defenses` WHERE `id` = ? LIMIT 1"
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