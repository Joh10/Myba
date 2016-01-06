package managers.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public abstract class HibernateManager<T>
{
    public final T find(int id)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query query = s.createQuery("from " + HibernateManager.this.getEntityClass() + " s where s.id = :id");
        query.setParameter("id", id);

        List queryList = query.list();

        if (queryList != null && queryList.isEmpty())
            return null;

        T t = (T) queryList.get(0);

        s.getTransaction().commit();
        return t;
    }

    public final boolean insertOrUpdate(T obj)
    {
        try
        {
            Session s = HibernateUtil.getInstance().getSession();
            s.beginTransaction();
            s.saveOrUpdate(obj);
            s.getTransaction().commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public final boolean delete(T obj)
    {
        try
        {
            Session s = HibernateUtil.getInstance().getSession();
            s.beginTransaction();
            s.delete(obj);
            s.getTransaction().commit();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    protected abstract Class<?> getEntityClass();
}