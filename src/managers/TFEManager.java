package managers;

import beans.TFE;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class TFEManager extends HibernateManager<TFE>
{
    public List<TFE> fetchAll()
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();

        Query q = se.createQuery("from TFE s");
        List<TFE> t = q.list();

        se.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return TFE.class;
    }
}