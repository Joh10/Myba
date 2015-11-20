import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateMain
{
    private static final Logger LOGGER = Logger.getLogger("Hibernate-Tutorial");

    public static void main(String[] args)
    {
        HibernateMain main = new HibernateMain();
        main.run();
    }

    public void run()
    {
        SessionFactory sessionFactory = null;
        Session session = null;

        try
        {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            session = sessionFactory.openSession();
        }
        catch (Exception e)
        {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
        finally
        {
            if (session != null)
                session.close();

            if (sessionFactory != null)
                sessionFactory.close();
        }
    }
}
