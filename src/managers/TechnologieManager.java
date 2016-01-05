package managers;


import beans.Technologie;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class TechnologieManager extends HibernateManager<Technologie>
{
    public List<Technologie> fetchAll()
    {
        return execute(se ->
        {
            Query q = se.createQuery("from Technologie s");
            return fetchAll(q);
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Technologie.class;
    }
}