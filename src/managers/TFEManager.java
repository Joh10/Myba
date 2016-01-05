package managers;

import beans.TFE;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class TFEManager extends HibernateManager<TFE>
{
    public List<TFE> fetchAll()
    {
        return execute(se ->
        {
            Query q = se.createQuery("from TFE s");
            return fetchAll(q);
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return TFE.class;
    }
}