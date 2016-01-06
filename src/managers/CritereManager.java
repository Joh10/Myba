package managers;

import beans.Critere;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class CritereManager extends HibernateManager<Critere>
{
    public List<Critere> fetchAll(String type)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from Critere s where s.type = :type");
        q.setParameter("type", type);
        List<Critere> r = q.list();

        s.getTransaction().commit();

        return r;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Critere.class;
    }
}