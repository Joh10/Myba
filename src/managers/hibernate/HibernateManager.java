package managers.hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public abstract class HibernateManager<T>
{
    public <U> U execute(IFunction<U> i)
    {
        Session session = null;
        Transaction txn;
        try
        {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            txn = session.beginTransaction();

            U t = i.execute(session);
            txn.commit();
            return t;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            session.flush();
            session.close();
        }

        return null;
    }

    protected final List<T> fetchAll(Query q)
    {
       /* return execute(s ->
        {*/
            List list = q.list();

            if (list != null && list.isEmpty())
                return null;

            return (List<T>) list;
       /* });*/

    }

    public final T find(int id)
    {
        return execute(s ->
        {
            Query query = s.createQuery("from " + HibernateManager.this.getEntityClass() + " s where s.id = :id");
            query.setParameter("id", id);

            List queryList = query.list();

            if (queryList != null && queryList.isEmpty())
                return null;

            return (T) queryList.get(0);
        });
    }

    public final boolean insert(T obj)
    {
        return execute(s ->
        {
            try
            {
                s.save(obj);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        });
    }

    public final boolean update(T obj)
    {
        return execute(s ->
        {
            try
            {
                s.saveOrUpdate(obj);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        });
    }

    public final boolean delete(T obj)
    {
        return execute(s ->
        {
            try
            {
                s.delete(obj);
                return true;
            }
            catch (Exception e)
            {
                return false;
            }
        });
    }

    protected abstract Class<?> getEntityClass();
}