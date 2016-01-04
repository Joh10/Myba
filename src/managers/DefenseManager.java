package managers;


import beans.Defense;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;

import java.util.ArrayList;

public class DefenseManager extends HibernateManager<Defense>
{
    public ArrayList<Defense> fetchAll(int type, Utilisateur obj)
    {
        //TODO WTF
        /* OLD CODE

        ArrayList<Defense> liste = new ArrayList<Defense>();
        DAO_Utilisateur dao_user = new DAO_Utilisateur();
        DAO_Stage dao_stage = new DAO_Stage();
        DAO_TFE dao_tfe = new DAO_TFE();
        PreparedStatement prepare = null;
        ResultSet res = null;
        try
        {
            String query;
            switch (type)
            {
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
            if (type > 0) prepare.setInt(1, obj.getId());
            if (type == 2) prepare.setInt(2, obj.getId());

            res = prepare.executeQuery();
            while (res.next())
            {
                res.getInt("tfe_id"); // nécessaire pour faire le wasNull
                Defense defense = null;
                if (res.wasNull())
                    defense = new Defense(res.getInt("id"), dao_user.find(res.getInt("user_id")), dao_stage.find(res.getInt("stage_id")), res.getTimestamp("date"), res.getString("local"));
                else
                    defense = new Defense(res.getInt("id"), dao_user.find(res.getInt("user_id")), dao_tfe.find(res.getInt("tfe_id")), res.getTimestamp("date"), res.getString("local"));
                liste.add(defense);
            }
        }

         */
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Defense.class;
    }
}