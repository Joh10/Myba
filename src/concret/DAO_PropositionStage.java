package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import bean.PropositionStage;
import bean.Utilisateur;

import java.util.ArrayList;

public class DAO_PropositionStage extends DAO<PropositionStage> {
	public boolean create(PropositionStage obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `stages_propositions` (`owner_id`, `lieustage_id`, `valide`, `sujet`, `annexe`) VALUES (?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getOwner().getId());
			prepare.setInt(2, obj.getPlace().getId());
			prepare.setBoolean(3, obj.isValid());
			prepare.setString(4, obj.getSubject());
			prepare.setString(5, obj.getAnnexe());
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
	 * Methode permettant de récupérer toutes propositions de stage
	 * @param obj		L'étudiant dont on désire afficher les propositions dont il est concerné (null s'il faut tout afficher)
	 * @param passrole	Le nom du rôle "passe partout" dont les propositions doivent être affichées à tous
	 * @return liste de proposition de stage
	 */
	public ArrayList<PropositionStage> fetchAll(Utilisateur obj, String passrole) {
		ArrayList<PropositionStage> liste = new ArrayList<PropositionStage>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_LieuStage dao_lieu = new DAO_LieuStage();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			String query;
			if(obj == null)
				query = "SELECT * FROM `stages_propositions` WHERE `valide` = 0";
			else
				query = "SELECT sp.* FROM `stages_propositions` sp, `utilisateurs` u, `roles` r WHERE sp.`valide` = 0 AND sp.`owner_id` = u.`id` AND u.`role_id` = r.`id` AND (sp.`owner_id` = ? OR r.`nom` = ?)";
			prepare = this.connect.prepareStatement(query);
			
			if(obj != null) {
				prepare.setInt(1, obj.getId());
				prepare.setString(2,  passrole);
			}
			
			res = prepare.executeQuery();
			while(res.next()) {
				PropositionStage proposition = new PropositionStage(
					res.getInt("id"),
					dao_user.find(res.getInt("owner_id")),
					dao_lieu.find(res.getInt("lieustage_id")),
					res.getBoolean("valide"),
					res.getString("sujet"),
					res.getString("annexe")
				);
				liste.add(proposition);
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
	
	public PropositionStage find(int id) {
		PropositionStage proposition = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_LieuStage dao_lieu = new DAO_LieuStage();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `stages_propositions` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				proposition = new PropositionStage(
					id,
					dao_user.find(res.getInt("owner_id")),
					dao_lieu.find(res.getInt("lieustage_id")),
					res.getBoolean("valide"),
					res.getString("sujet"),
					res.getString("annexe")
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
		return proposition;
	}
	
	public boolean update(PropositionStage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `stages_propositions` SET `valide` = ?, `sujet` = ?, `annexe` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setBoolean(1, obj.isValid());
			prepare.setString(2, obj.getSubject());
			prepare.setString(3, obj.getAnnexe());
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
	
	public boolean delete(PropositionStage obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `stages_propositions` WHERE `id` = ? LIMIT 1"
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