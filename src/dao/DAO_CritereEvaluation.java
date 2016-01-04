package dao;

import beans.CritereEvaluation;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class DAO_CritereEvaluation extends DAO<CritereEvaluation>
{

    public List<CritereEvaluation> fetchAll(String type)
    {
        try (Session session = HibernateConnector.getInstance().getSession())
        {
            Query query = session.createQuery("from CritereEvaluation s where s.type = :type");
            query.setParameter("type", type);

            List list = query.list();

            if (list != null && list.isEmpty())
                return null;

            return (List<CritereEvaluation>) list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return CritereEvaluation.class;
    }
}