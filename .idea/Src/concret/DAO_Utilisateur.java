package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import dao.DAO;
import bean.Utilisateur;
import bean.Role;

import java.util.ArrayList;

public class DAO_Utilisateur extends DAO<Utilisateur> {
	public boolean create(Utilisateur obj) {
		PreparedStatement prepare = null;
		PreparedStatement prepare2 = null;
		ResultSet generatedKeys = null;
		obj.getRole().setId(findRole(obj.getRole().getNom()));
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `utilisateurs` (`role_id`, `enabled`, `email`, `password`, `matricule`, `nom`, `prenom`, `telephone`, `annee`, `doublant`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getRole().getId());
			prepare.setBoolean(2, obj.isEnabled());
			prepare.setString(3, obj.getEmail());
			prepare.setString(4, "TO_BE_UPDATED");
			if(obj.getMatricule() == null)
				prepare.setNull(5, Types.INTEGER);
			else
				prepare.setInt(5, obj.getMatricule());
			prepare.setString(6, obj.getNom());
			prepare.setString(7, obj.getPrenom());
			prepare.setString(8, obj.getTelephone());
			if(obj.getAnnee() == null)
				prepare.setNull(9, Types.INTEGER);
			else
				prepare.setInt(9, obj.getAnnee());
			if(obj.isDoublant() == null)
				prepare.setNull(10, Types.BOOLEAN);
			else
				prepare.setBoolean(10, obj.isDoublant());
			prepare.executeUpdate();
			
			generatedKeys = prepare.getGeneratedKeys();
			if(generatedKeys.next()) {
				obj.setId(generatedKeys.getInt(1));
				obj.setPassword(obj.getPasswordHash());
				prepare2 = this.connect.prepareStatement(
					"UPDATE `utilisateurs` SET `password` = ? WHERE `id` = ? LIMIT 1"
				);
				prepare2.setString(1, obj.getPasswordHash());
				prepare2.setInt(2, obj.getId());
				prepare2.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				prepare.close();
				generatedKeys.close();
				prepare2.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return true;
	}
	
	/**
	 * Methode permettant de récupérer tout les utilisateurs d'un rôle
	 * @param role_name	Le rôle dont on désire rechercher les utilisateurs
	 * @return	liste d'utilisateurs
	 */
	public ArrayList<Utilisateur> fetchAll(String role_name) {
		ArrayList<Utilisateur> liste = new ArrayList<Utilisateur>();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `utilisateurs` WHERE `role_id` = (SELECT `id` FROM `roles` WHERE `nom` = ?)"
			);
			prepare.setString(1, role_name);
			
			res = prepare.executeQuery();
			while(res.next()) {
				Utilisateur user = new Utilisateur(
					res.getInt("id"),
					res.getBoolean("enabled"),
					new Role(res.getInt("role_id"), role_name),
					res.getString("email"),
					res.getString("password"),
					res.getInt("matricule"),
					res.getString("nom"),
					res.getString("prenom"),
					res.getString("telephone"),
					res.getInt("annee"),
					res.getBoolean("doublant")
				);
				liste.add(user);
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
	
	private Integer findRole(String role_name) {
		Integer role_id = null;
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT `id` FROM `roles` WHERE `nom` = ? LIMIT 1"
			);
			prepare.setString(1, role_name);
			res = prepare.executeQuery();
			if(res.first())
				role_id = res.getInt("id");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return role_id;
	}
	
	public Utilisateur find(int id) {
		Utilisateur utilisateur = null;
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT u.*, r.nom AS `role_name` FROM `utilisateurs` u, `roles` r WHERE u.`id` = ? AND u.`role_id` = r.`id` LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				utilisateur = new Utilisateur(
					id,
					res.getBoolean("enabled"),
					new Role(res.getInt("role_id"), res.getString("role_name")),
					res.getString("email"),
					res.getString("password"),
					res.getInt("matricule"),
					res.getString("nom"),
					res.getString("prenom"),
					res.getString("telephone"),
					res.getInt("annee"),
					res.getBoolean("doublant")
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
		return utilisateur;
	}
	
	private void loadPermissions(Role obj) {
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT `nom` FROM `permissions` WHERE `role_id` = ?"
			);
			
			prepare.setInt(1, obj.getId());
			res = prepare.executeQuery();
			while(res.next())
				obj.addPermission(res.getString("nom"));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
	}
	
	/**
	 * Methode permettant de récupérer un utilisateur par son identifiant et son mot de passe<br>
	 * - Tout les utilisateurs peuvent se connecter à l'aide de leur adresse e-mail<br>
	 * - Les professeurs, maitres de stage et présidents de jury peuvent utiliser prenom.nom<br>
	 * - Les étudiants peuvent utiliser login-matricule
	 * @param	identifiant	L'identifiant de l'utilisateur (email, identifiant court)
	 * @param	password	Le mot de passe de l'utilisateur
	 * @return	l'utilisateur trouvé, null sinon
	 */
	public Utilisateur find(String identifiant, String password) {
		Utilisateur utilisateur = null;
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT u.*, r.nom AS `role_name` FROM `utilisateurs` u, `roles` r WHERE (u.`email` = ? OR (u.`matricule` is not null AND CONCAT(u.`prenom`, '-', u.`matricule`) = ?) OR (u.`matricule` is null AND CONCAT(u.`prenom`, '.', u.`nom`) = ?)) AND u.`role_id` = r.`id` LIMIT 1"
			);
			prepare.setString(1, identifiant);
			prepare.setString(2, identifiant);
			prepare.setString(3, identifiant);
			
			res = prepare.executeQuery();
			if(res.first()) {
				utilisateur = new Utilisateur(
					res.getInt("id"),
					res.getBoolean("enabled"),
					new Role(res.getInt("role_id"), res.getString("role_name")),
					res.getString("email"),
					res.getString("password"),
					res.getInt("matricule"),
					res.getString("nom"),
					res.getString("prenom"),
					res.getString("telephone"),
					res.getInt("annee"),
					res.getBoolean("doublant")
				);
				if(!utilisateur.checkPassword(password))
					utilisateur = null;
				else
					loadPermissions(utilisateur.getRole());
			}
		} catch(SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		finally {
			try {
				prepare.close();
				res.close();
			} catch(SQLException | NullPointerException e) { }
		}
		return utilisateur;
	}
	
	public boolean update(Utilisateur obj) {
		PreparedStatement prepare = null;
		obj.getRole().setId(findRole(obj.getRole().getNom()));
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `utilisateurs` SET `role_id` = ?,`email` = ?, `password` = ?, `enabled` = ?, `nom` = ?, `prenom` = ?, `matricule` = ?, `telephone` = ?, `annee` = ?, `doublant` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getRole().getId());
			prepare.setString(2, obj.getEmail());
			prepare.setString(3, obj.getPasswordHash());
			prepare.setBoolean(4, obj.isEnabled());
			prepare.setString(5, obj.getNom());
			prepare.setString(6, obj.getPrenom());
			if(obj.getMatricule() == null)
				prepare.setNull(7, Types.INTEGER);
			else
				prepare.setInt(7, obj.getMatricule());
			prepare.setString(8, obj.getTelephone());
			if(obj.getAnnee() == null)
				prepare.setNull(9, Types.INTEGER);
			else
				prepare.setInt(9, obj.getAnnee());
			if(obj.isDoublant() == null)
				prepare.setNull(10, Types.BOOLEAN);
			else
				prepare.setBoolean(10, obj.isDoublant());
			prepare.setInt(11, obj.getId());
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
	
	public boolean delete(Utilisateur obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `utilisateurs` WHERE `id` = ? LIMIT 1"
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