package managers;

import beans.Critere;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class CritereManager extends HibernateManager<Critere>
{
    public List<Critere> fetchAll(String type)
    {
        return execute(s ->
        {
            Query q = s.createQuery("from Critere s where s.type = :type");
            q.setParameter("type", type);
            return fetchAll(q);
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Critere.class;
    }
}