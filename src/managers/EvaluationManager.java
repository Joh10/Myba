package managers;

import beans.*;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class EvaluationManager extends HibernateManager<Evaluation>
{
    public List<Evaluation> fetchAll(Stage stage)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Evaluation s where s.stage.id = :x");
        q.setParameter("x", stage.getId());
        List<Evaluation> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    public List<Evaluation> fetchAll(TFE tfe)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Evaluation s where s.tfe.id = :x");
        q.setParameter("x", tfe.getId());
        List<Evaluation> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    public List<Evaluation> fetchAll(Defense defense)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Evaluation s where s.defense.id = :x");
        q.setParameter("x", defense.getId());
        List<Evaluation> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    public List<Evaluation> fetchAll(Utilisateur owner, Stage stage)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Evaluation s where s.owner.id = :x and s.stage.id = :y");
        q.setParameter("x", owner.getId());
        q.setParameter("y", stage.getId());
        List<Evaluation> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    public List<Evaluation> fetchAll(Utilisateur owner, Defense defense)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Evaluation s where s.owner.id = :x and s.defense.id = :y");
        q.setParameter("x", owner.getId());
        q.setParameter("y", defense.getId());
        List<Evaluation> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Evaluation.class;
    }
}