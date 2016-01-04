package managers;

import beans.*;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class EvaluationManager extends HibernateManager<Evaluation>
{
    public List<Evaluation> fetchAll(Stage stage)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Evaluation s where s.stage.id = :x");
        q.setParameter("x", stage.getId());
        return fetchAll(q);
    }

    public List<Evaluation> fetchAll(TFE tfe)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Evaluation s where s.tfe.id = :x");
        q.setParameter("x", tfe.getId());
        return fetchAll(q);
    }

    public List<Evaluation> fetchAll(Defense defense)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Evaluation s where s.defense.id = :x");
        q.setParameter("x", defense.getId());
        return fetchAll(q);
    }

    public List<Evaluation> fetchAll(Utilisateur owner, Stage stage)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Evaluation s where s.owner.id = :x and s.stage.id = :y");
        q.setParameter("x", owner.getId());
        q.setParameter("y", stage.getId());
        return fetchAll(q);
    }

    public List<Evaluation> fetchAll(Utilisateur owner, Defense defense)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Evaluation s where s.owner.id = :x and s.defense.id = :y");
        q.setParameter("x", owner.getId());
        q.setParameter("y", defense.getId());
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Evaluation.class;
    }
}