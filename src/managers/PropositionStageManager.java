package managers;

import beans.PropositionStage;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;

import java.util.ArrayList;

public class PropositionStageManager extends HibernateManager<PropositionStage>
{
    public ArrayList<PropositionStage> fetchAll(Utilisateur obj, String passrole)
    {
        //TODO
         /* OLD CODE

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
          */
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return PropositionStage.class;
    }
}