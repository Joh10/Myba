package managers;

import beans.Stage;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class StageManager extends HibernateManager<Stage>
{
    public List<Stage> fetchAll(Utilisateur maitreDeStage)
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();

        Query q = se.createQuery("from Stage s");
        List<Stage> queryList = q.list();
        List<Stage> t = new ArrayList<>();

        for (Stage s : queryList)
            for (Utilisateur u : s.getUtilisateur())
                if (u.getRole().getNom().equals("maitre_stage") && u.getEmail().equals(maitreDeStage.getEmail()))
                    t.add(s);

        se.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Stage.class;
    }
}