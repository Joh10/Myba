package managers;

import beans.Echeance;
import beans.Stage;
import beans.TFE;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;


public class EcheanceManager extends HibernateManager<Echeance>
{
    public List<Echeance> fetchAll(Stage stage)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Echeance s");
        List<Echeance> t = new ArrayList<>();

        for (Object e : q.list())
            if (((Echeance) e).getStage().contains(stage))
                t.add((Echeance) e);

        s.getTransaction().commit();
        return t;
    }

    public List<Echeance> fetchAll(TFE tfe)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Echeance s");
        List<Echeance> t = new ArrayList<>();

        for (Object e : q.list())
            if (((Echeance) e).getTfe().contains(tfe)) t.add((Echeance) e);

        s.getTransaction().commit();
        return t;
    }

    /**
     * Méthode permettant de récupérer toutes les échéances liées à un utilisateur
     *
     * @param utilisateur L'utilisateur dont on désire récupérer les échéances
     * @return une liste d'échéances
     */
    public List<Echeance> fetchAll(Utilisateur utilisateur)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Echeance s");
        List<Echeance> list = q.list();
        List<Echeance> t = new ArrayList<>();

        for (Echeance e : list)
            for (Utilisateur u : e.getUtilisateur())
                if (u.getId() == utilisateur.getId())
                    t.add(e);

        s.getTransaction().commit();
        return t;
    }

    public List<Echeance> fetchAll()
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Echeance s");
        List<Echeance> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    @Override protected Class<?>getEntityClass()
    {
        return Echeance.class;
    }
}