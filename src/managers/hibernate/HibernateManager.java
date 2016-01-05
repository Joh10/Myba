package managers.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class HibernateManager<T>
{
    protected final List<T> fetchAll(Query q)
    {
        List list = q.list();

        if (list != null && list.isEmpty())
            return null;

        return (List<T>) list;
    }

    public final T find(int id)
    {
        try (Session session = HibernateConnector.getInstance().getSession())
        {
            Query query = session.createQuery("from " + getEntityClass() + " s where s.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();

            if (queryList != null && queryList.isEmpty()) return null;

            return (T) queryList.get(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean create(T obj)
    {
        try (Session session = HibernateConnector.getInstance().getSession())
        {
            Transaction transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public final boolean update(T obj)
    {
        try (Session session = HibernateConnector.getInstance().getSession())
        {
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    public final boolean delete(T obj)
    {
        try (Session session = HibernateConnector.getInstance().getSession())
        {
            Transaction t = session.beginTransaction();
            session.delete(obj);
            t.commit();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    protected abstract Class<?> getEntityClass();
}