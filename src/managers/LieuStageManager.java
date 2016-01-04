package managers;

import beans.LieuStage;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;


public class LieuStageManager extends HibernateManager<LieuStage>
{
    public List<LieuStage> fetchAll()
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from LieuStage s");
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return LieuStage.class;
    }
}