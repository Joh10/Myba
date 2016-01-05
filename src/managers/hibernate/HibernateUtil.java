package managers.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static HibernateUtil instance = new HibernateUtil();
    private SessionFactory sessionFactory;

    private HibernateUtil()
    {
        this.sessionFactory = buildSessionFactory();
    }

    private synchronized static SessionFactory buildSessionFactory()
    {
        return new Configuration().configure().buildSessionFactory();
    }

    public Session getSession()
    {
        Session sess;

        try
        {
            sess = sessionFactory.getCurrentSession();
        }
        catch (org.hibernate.HibernateException he)
        {
            sess = sessionFactory.openSession();
        }

        return sess;
    }

    public static HibernateUtil getInstance()
    {
        if (instance == null)
            return new HibernateUtil();

        return instance;
    }
}