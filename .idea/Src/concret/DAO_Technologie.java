package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import bean.Technologie;

import java.util.ArrayList;

public class DAO_Technologie extends DAO<Technologie> {
	public boolean create(Technologie obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `technologies` (`nom`, `version`) VALUES (?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getVersion());
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
	 * Methode permettant de récupérer toutes les technologies
	 * @return liste des technologies
	 */
	public ArrayList<Technologie> fetchAll() {
		ArrayList<Technologie> liste = new ArrayList<Technologie>();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `technologies`"
			);
			
			res = prepare.executeQuery();
			while(res.next()) {
				Technologie tech = new Technologie(
					res.getInt("id"),
					res.getString("nom"),
					res.getString("version")
				);
				liste.add(tech);
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
	
	public Technologie find(int id) {
		Technologie tech = null;
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `technologies` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				tech = new Technologie(
					id,
					res.getString("nom"),
					res.getString("version")
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
		return tech;
	}
	
	public boolean update(Technologie obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `technologies` SET `nom` = ?, `version` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getVersion());
			prepare.setInt(3, obj.getId());
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
	
	public boolean delete(Technologie obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `technologies` WHERE `id` = ? LIMIT 1"
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