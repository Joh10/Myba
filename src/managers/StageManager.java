package managers;

import beans.Stage;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class StageManager extends HibernateManager<Stage>
{
    public List<Stage> fetchAll(Utilisateur maitreDeStage)
    {
        return execute(se ->
        {
            Query q = se.createQuery("from Stage s");
            List<Stage> queryList = q.list();
            List<Stage> temp = new ArrayList<>();

            for(Stage s : queryList)
                for(Utilisateur u : s.getUtilisateur())
                    if(u.getRole().getNom().equals("maitre_stage") && u.getEmail().equals(maitreDeStage.getEmail()))
                        temp.add(s);

            return temp;
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Stage.class;
    }
}