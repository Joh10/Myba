package managers;

import beans.*;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

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

    public List<Echeance> fetchAll(Utilisateur utilisateur)
    {
        //TODO
        //return fetchAllBack("SELECT e.* FROM `echeances` e, `echeances_cibles` c WHERE e.`tfe_id` is null AND e.`stage_id` is null AND e.`id` = c.`echeance_id` AND c.`user_id` = ? " + "UNION SELECT e.* FROM `echeances` e, `tfe` t WHERE e.`tfe_id` is not null AND e.`tfe_id` = t.`id` AND t.`owner_id` = ? " + "UNION SELECT e.* FROM `echeances` e, `stages` s WHERE e.`stage_id` is not null AND e.`stage_id` = s.`id` AND s.`owner_id` = ?", utilisateur.getId(), true);
        return null;
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