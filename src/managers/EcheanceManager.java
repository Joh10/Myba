package managers;

import beans.Echeance;
import beans.Stage;
import beans.TFE;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class EcheanceManager extends HibernateManager<Echeance>
{
    public List<Echeance> fetchAll(Stage stage)
    {
        return execute(s ->
        {
            Query q = s.createQuery("from Echeance s");
            List<Echeance> t = new ArrayList<>();

            for(Object e : q.list())
                if(((Echeance)e).getStage().contains(stage))
                    t.add((Echeance) e);

            return t;
        });
    }

    public List<Echeance> fetchAll(TFE tfe)
    {
        return execute(s ->
        {
            Query q = s.createQuery("from Echeance s");
            List<Echeance> t = new ArrayList<>();

            for(Object e : q.list())
                if(((Echeance)e).getTfe().contains(tfe))
                    t.add((Echeance) e);

            return t;
        });
    }

    /**
     * Méthode permettant de récupérer toutes les échéances liées à un utilisateur
     * @param utilisateur L'utilisateur dont on désire récupérer les échéances
     * @return une liste d'échéances
     */
    public List<Echeance> fetchAll(Utilisateur utilisateur)
    {
        return execute(s ->
        {
            Query q = s.createQuery("from Echeance s");
            List<Echeance> list = q.list();
            List<Echeance> t = new ArrayList<>();

            for(Echeance e : list)
                for(Utilisateur u : e.getUtilisateur())
                    if(u.getId() == utilisateur.getId())
                        t.add(e);

            return t;
        });
    }

    public List<Echeance> fetchAll()
    {
        return execute(s ->
        {
            Query q = s.createQuery("from Echeance s");
            return fetchAll(q);
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Echeance.class;
    }
}