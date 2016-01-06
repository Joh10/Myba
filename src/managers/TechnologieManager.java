package managers;


import beans.Technologie;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class TechnologieManager extends HibernateManager<Technologie>
{
    public List<Technologie> fetchAll()
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();

        Query q = se.createQuery("from Technologie s");
        List<Technologie> t = q.list();

        se.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Technologie.class;
    }
}