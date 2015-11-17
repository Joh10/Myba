package concret;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DAO;
import bean.TFE;
import bean.Technologie;

public class DAO_TFE extends DAO<TFE> {
	public boolean create(TFE obj) {
		PreparedStatement prepare = null;
		ResultSet generatedKeys = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `tfe` (`owner_id`, `promoteur_id`, `titre`, `pointsTotal`, `anneeAcadDebut`, `anneeAcadFin`) VALUES (?, ?, ?, ?, ?, ?)"
			, Statement.RETURN_GENERATED_KEYS);
			prepare.setInt(1, obj.getOwner().getId());
			prepare.setInt(2, obj.getPromoteur().getId());
			prepare.setString(3, obj.getTitre());
			prepare.setDouble(4, obj.getPoints());
			prepare.setInt(5, obj.getAnneeDebut());
			prepare.setInt(6, obj.getAnneeFin());
			prepare.executeUpdate();
			
			generatedKeys = prepare.getGeneratedKeys();
			if(generatedKeys.next()) {
				obj.setId(generatedKeys.getInt(1));
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
	
	private boolean addTechnologie(TFE obj, Technologie obj1) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"INSERT INTO `technologies_utilisation` (`tfe_id`, `techno_id`) VALUES (?, ?)"
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
				"SELECT `techno_id` FROM `technologies_utilisation` WHERE `tfe_id` = ?"
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
	
	private boolean clearTechnologies(TFE obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"DELETE FROM `technologies_utilisation` WHERE `tfe_id` = ?"
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
	 * Methode permettant de récupérer tout les TFE
	 * @return liste de TFE
	 */
	public ArrayList<TFE> fetchAll() {
		ArrayList<TFE> liste = new ArrayList<TFE>();
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `tfe`"
			);
			
			res = prepare.executeQuery();
			while(res.next()) {
				TFE tfe = new TFE(
					res.getInt("id"),
					dao_user.find(res.getInt("owner_id")),
					dao_user.find(res.getInt("promoteur_id")),
					res.getString("titre"),
					res.getInt("pointsTotal"),
					res.getInt("anneeAcadDebut"),
					res.getInt("anneeAcadFin"),
					loadTechnologies(res.getInt("id"))
				);
				liste.add(tfe);
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
	
	public TFE find(int id) {
		TFE tfe = null;
		DAO_Utilisateur dao_user = new DAO_Utilisateur();
		PreparedStatement prepare = null;
		ResultSet res = null;
		try {
			prepare = this.connect.prepareStatement(
				"SELECT * FROM `tfe` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, id);
			
			res = prepare.executeQuery();
			if(res.first()) {
				tfe = new TFE(
					id,
					dao_user.find(res.getInt("owner_id")),
					dao_user.find(res.getInt("promoteur_id")),
					res.getString("titre"),
					res.getInt("pointsTotal"),
					res.getInt("anneeAcadDebut"),
					res.getInt("anneeAcadFin"),
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
		return tfe;
	}
	
	public boolean update(TFE obj) {
		PreparedStatement prepare = null;
		try {
			prepare = this.connect.prepareStatement(
				"UPDATE `tfe` SET `promoteur_id` = ?, `titre` = ?, `pointsTotal` = ?, `anneeAcadDebut` = ?, `anneeAcadFin` = ? WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getPromoteur().getId());
			prepare.setString(2, obj.getTitre());
			prepare.setDouble(3, obj.getPoints());
			prepare.setInt(4, obj.getAnneeDebut());
			prepare.setInt(5, obj.getAnneeFin());
			prepare.setInt(6, obj.getId());
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
	
	public boolean delete(TFE obj) {
		PreparedStatement prepare = null;
		try {
			this.connect.setAutoCommit(false);
			clearTechnologies(obj);
			prepare = this.connect.prepareStatement(
				"DELETE FROM `tfe` WHERE `id` = ? LIMIT 1"
			);
			prepare.setInt(1, obj.getId());
			prepare.executeUpdate();
			this.connect.commit(); // je ne supprime les technologiques QUE SI la suppression du TFE est ok (et inverse)
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