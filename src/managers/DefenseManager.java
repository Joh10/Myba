package managers;


import beans.Defense;
import beans.Stage;
import beans.TFE;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DefenseManager extends HibernateManager<Defense>
{
    /**
     * Méthode permettant de récupérer toutes les défenses
     *
     * @param user l'utilisateur (président de jury/étudiant) de la recherche
     * @return liste de défense
     */
    public List<Defense> fetchAll(Utilisateur user)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();
        List<Defense> out = new ArrayList<>();

        if (user != null && user.getRole().getNom().equals("president_jury"))
        {
            Query q = s.createQuery("from Defense s where s.presidentJury.id = :id");
            q.setParameter("id", user.getId());
            out = q.list();
        }
        else
        {
            Query q = s.createQuery("from Defense s");
            List<Defense> list = q.list();

            for (Defense d : list)
            {
                if (d.getTfe() != null)
                {
                    Query q2 = s.createQuery("from TFE s where s.id = :id");
                    q2.setParameter("id", d.getTfe().getId());

                    List<TFE> l = q2.list();

                    for (TFE t : l)
                        if (t.getOwner().getId() == user.getId()) out.add(d);
                }
                else
                {
                    Query q2 = s.createQuery("from Stage s where s.id = :id");
                    q2.setParameter("id", d.getStage().getId());

                    List<Stage> l = q2.list();

                    for (Stage t : l)
                        if (t.getOwner().getId() == user.getId()) out.add(d);
                }
            }
        }

        return out;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Defense.class;
    }
}