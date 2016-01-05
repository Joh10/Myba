package managers;

import beans.LieuStage;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;


public class LieuStageManager extends HibernateManager<LieuStage>
{
    public List<LieuStage> fetchAll()
    {
        return execute(s ->
        {
            Query q = s.createQuery("from LieuStage s");
            return fetchAll(q);
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return LieuStage.class;
    }
}