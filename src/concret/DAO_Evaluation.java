package concret;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import dao.DAO;
import bean.Defense;
import bean.Evaluation;
import bean.Stage;
import bean.TFE;
import bean.Utilisateur;

import java.util.ArrayList;

public class DAO_Evaluation extends DAO<Evaluation> {
	public boolean create(Evaluation obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `evaluations` (`owner_id`, `tfe_id`, `stage_id`, `defense_id`, `critere_id`, `date`, `note`, `commentaire`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			
			prepare.setInt(1, obj.getOwner().getId());
			if(obj.getTFE() == null)
				prepare.setNull(2, Types.INTEGER);
			else
				prepare.setInt(2, obj.getTFE().getId());
			if(obj.getStage() == null)
				prepare.setNull(3, Types.INTEGER);
			else
				prepare.setInt(3, obj.getStage().getId());
			if(obj.getDefense() == null)
				prepare.setNull(4, Types.INTEGER);
			else
				prepare.setInt(4, obj.getDefense().getId());
			prepare.setInt(5, obj.getCritere().getId());
			prepare.setTimestamp(6, new Timestamp(obj.getDate().getTime()));
			prepare.setDouble(7, obj.getNote());
			prepare.setString(8, obj.getCommentaire());
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
	 * Méthode permettant de récupérer toutes les évaluations liées à un stage
	 * @param stage Le stage dont on désire récupérer les évaluations
	 * @return une liste d'évaluations
	 */
	public ArrayList<Evaluation> fetchAll(Stage stage) {
		return fetchAllBack(" WHERE `stage_id` = ?", stage.getId(), null);
	}
	
	/**
	 * Méthode permettant de récupérer toutes les évaluations liées à un TFE
	 * @param tfe Le TFE dont on désire récupérer les évaluations
	 * @return une liste d'évaluations
	 */
	public ArrayList<Evaluation> fetchAll(TFE tfe) {
		return fetchAllBack(" WHERE `tfe_id` = ?", tfe.getId(), null);
	}
	
	/**
	 * Méthode permettant de récupérer toutes les évaluations liées à une défense
	 * @param defense La défense dont on désire récupérer les évaluations
	 * @return une liste d'évaluations
	 */
	public ArrayList<Evaluation> fetchAll(Defense defense) {
		return fetchAllBack(" WHERE `defense_id` = ?", defense.getId(), null);
	}
	
	/**
	 * Méthode permettant de récupérer toutes les évaluations liées à un stage, faites par l'utilisateur spécifié
	 * Cas d'utilisation : afficher les évaluations d'un stage faites par un maitre de stage
	 * @param owner L'utilisateur dont on désire récupérérer les évaluations effectuées sur le stage
	 * @param stage Le stage dont on désire récupérer les évaluations
	 * @return une liste d'évaluations
	 */
	public ArrayList<Evaluation> fetchAll(Utilisateur owner, Stage stage) {
		return fetchAllBack(" WHERE `owner_id` = ? AND stage_id = ?", owner.getId(), stage.getId());
	}
	
	/**
	 * Méthode permettant de récupérer toutes les évaluations liées à une défense, faites par l'utilisateur spécifié
	 * Cas d'utilisation : afficher les évaluations d'une défense faites par un président de jury
	 * @param owner L'utilisateur dont on désire récupérérer les évaluations effectuées sur le stage
	 * @param defense La défense dont on désire récupérer les évaluations
	 * @return une liste d'évaluations
	 */
	public ArrayList<Evaluation> fetchAll(Utilisateur owner, Defense defense) {
		return fetchAllBack(" WHERE `owner_id` = ? AND defense_id = ?", owner.getId(), defense.getId());
	}
	
	private ArrayList<Evaluation> fetchAllBack(String query, Integer param, Integer param2) {
		ArrayList<Evaluation> liste = new ArrayList<Evaluation>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_CritereEvaluation dao_critere = new DAO_CritereEvaluation();
		DAO_Defense dao_defense = new DAO_Defense();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `evaluations`"+query
			);
			
			if(param != null)
				prepare.setInt(1, param);
			if(param2 != null)
				prepare.setInt(2, param2);
			
			res = prepare.executeQuery();
			while(res.next()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				Evaluation evaluation = null;
				if(res.wasNull()) {
					res.getInt("defense_id");
					if(res.wasNull())
						evaluation = new Evaluation(
							res.getInt("id"),
							res.getTimestamp("date"),
							dao_user.find(res.getInt("owner_id")),
							dao_critere.find(res.getInt("critere_id")),
							dao_stage.find(res.getInt("stage_id")),
							res.getDouble("note"),
							res.getString("commentaire")
						);
					else
						evaluation = new Evaluation(
							res.getInt("id"),
							res.getTimestamp("date"),
							dao_user.find(res.getInt("owner_id")),
							dao_critere.find(res.getInt("critere_id")),
							dao_defense.find(res.getInt("defense_id")),
							res.getDouble("note"),
							res.getString("commentaire")
						);
				} else
					evaluation = new Evaluation(
						res.getInt("id"),
						res.getTimestamp("date"),
						dao_user.find(res.getInt("owner_id")),
						dao_critere.find(res.getInt("critere_id")),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getDouble("note"),
						res.getString("commentaire")
					);
				liste.add(evaluation);
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
	
	public Evaluation find(int id) {
		Evaluation evaluation = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		DAO_CritereEvaluation dao_critere = new DAO_CritereEvaluation();
		DAO_Defense dao_defense = new DAO_Defense();
		DAO_Stage dao_stage = new DAO_Stage();
		DAO_TFE dao_tfe = new DAO_TFE();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `evaluations` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				res.getInt("tfe_id"); // nécessaire pour faire le wasNull
				if(res.wasNull()) {
					res.getInt("defense_id");
					if(res.wasNull())
						evaluation = new Evaluation(
							res.getInt("id"),
							res.getTimestamp("date"),
							dao_user.find(res.getInt("owner_id")),
							dao_critere.find(res.getInt("critere_id")),
							dao_stage.find(res.getInt("stage_id")),
							res.getDouble("note"),
							res.getString("commentaire")
						);
					else
						evaluation = new Evaluation(
							res.getInt("id"),
							res.getTimestamp("date"),
							dao_user.find(res.getInt("owner_id")),
							dao_critere.find(res.getInt("critere_id")),
							dao_defense.find(res.getInt("defense_id")),
							res.getDouble("note"),
							res.getString("commentaire")
						);
				} else
					evaluation = new Evaluation(
						res.getInt("id"),
						res.getTimestamp("date"),
						dao_user.find(res.getInt("owner_id")),
						dao_critere.find(res.getInt("critere_id")),
						dao_tfe.find(res.getInt("tfe_id")),
						res.getDouble("note"),
						res.getString("commentaire")
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
		return evaluation;
	}
	
	public boolean update(Evaluation obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `evaluations` SET `defense_id` = ?, `note` = ?, `commentaire` = ? WHERE `id` = ? LIMIT 1"
			);
			if(obj.getDefense() == null)
				prepare.setNull(1, Types.INTEGER);
			else
				prepare.setInt(1, obj.getDefense().getId());
			prepare.setDouble(2, obj.getNote());
			prepare.setString(3, obj.getCommentaire());
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
	
	public boolean delete(Evaluation obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `evaluations` WHERE `id` = ? LIMIT 1"
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