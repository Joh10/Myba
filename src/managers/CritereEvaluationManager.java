package managers;

import beans.CritereEvaluation;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.List;

public class CritereEvaluationManager extends HibernateManager<CritereEvaluation>
{
    public List<CritereEvaluation> fetchAll(String type)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from CritereEvaluation s where s.type = :type");
        q.setParameter("type", type);
        return fetchAll(q);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return CritereEvaluation.class;
    }
}