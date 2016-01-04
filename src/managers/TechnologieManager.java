package managers;


import beans.Technologie;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class TechnologieManager extends HibernateManager<Technologie>
{
    public List<Technologie> fetchAll()
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Technologie s");
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Technologie.class;
    }
}