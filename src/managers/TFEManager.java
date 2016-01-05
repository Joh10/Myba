package managers;

import beans.TFE;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class TFEManager extends HibernateManager<TFE>
{
    public List<TFE> fetchAll()
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from TFE s");
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return TFE.class;
    }
}