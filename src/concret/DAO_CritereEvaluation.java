package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAO;
import bean.CritereEvaluation;

import java.util.ArrayList;

public class DAO_CritereEvaluation extends DAO<CritereEvaluation> {
	public boolean create(CritereEvaluation obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `criteres` (`nom`, `type`, `noteMax`) VALUES (?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getType());
			prepare.setInt(3, obj.getNote());
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
	 * Methode permettant de récupérer tout les critères d'évaluation
	 * @param type Le type de critère (si null, tout les critères)
	 * @return liste de critère
	 */
	public ArrayList<CritereEvaluation> fetchAll(String type) {
		ArrayList<CritereEvaluation> liste = new ArrayList<CritereEvaluation>();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			String requete = (type == null) ? "" : " WHERE `type` = ?"; 
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `criteres`"+requete
			);
			if(type != null)
				prepare.setString(1,  type);
			
			res = prepare.executeQuery();
			while(res.next()) {
				CritereEvaluation critere = new CritereEvaluation(
					res.getInt("id"),
					res.getString("nom"),
					res.getString("type"),
					res.getInt("noteMax")
				);
				liste.add(critere);
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
	
	public CritereEvaluation find(int id) {
		CritereEvaluation critere = null;
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `criteres` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				critere = new CritereEvaluation(
					res.getInt("id"),
					res.getString("nom"),
					res.getString("type"),
					res.getInt("noteMax")
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
		return critere;
	}
	
	public boolean update(CritereEvaluation obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `criteres` SET `nom` = ?, `type` = ?, `noteMax` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setString(1, obj.getNom());
			prepare.setString(2, obj.getType());
			prepare.setInt(3, obj.getNote());
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
	
	public boolean delete(CritereEvaluation obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `criteres` WHERE `id` = ? LIMIT 1"
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