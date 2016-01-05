package managers;

import beans.Echeance;
import beans.Stage;
import beans.TFE;
import beans.Utilisateur;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class EcheanceManager extends HibernateManager<Echeance>
{
    public List<Echeance> fetchAll(Stage stage)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Echeance s where s.stage.id = :stage");
        q.setParameter("stage", stage.getId());
        return fetchAll(q);
    }

    public List<Echeance> fetchAll(TFE tfe)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Echeance s where s.tfe.id = :tfe");
        q.setParameter("tfe", tfe.getId());
        return fetchAll(q);
    }

    /**
     * Méthode permettant de récupérer toutes les échéances liées à un utilisateur
     * @param utilisateur L'utilisateur dont on désire récupérer les échéances
     * @return une liste d'échéances
     */
    public List<Echeance> fetchAll(Utilisateur utilisateur)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Echeance s");
        List<Echeance> list = q.list();
        List<Echeance> t = new ArrayList<>();

        for(Echeance e : list)
            for(Utilisateur u : e.getUtilisateur())
                    if(u.getId() == utilisateur.getId())
                        t.add(e);

        return t;
    }

    public List<Echeance> fetchAll()
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Echeance s");
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Echeance.class;
    }
}