package managers;

import beans.LieuStage;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class LieuStageManager extends HibernateManager<LieuStage>
{
    public List<LieuStage> fetchAll()
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        Query q = s.createQuery("from LieuStage s");
        List<LieuStage> t = q.list();

        s.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return LieuStage.class;
    }
}