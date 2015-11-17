package DAO;

import DAO.utils.Query;
import Server.Evaluation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nicolas on 8/05/2015 at 08:07.
 */
public class EvaluationDAO extends AbstractDAO<Evaluation>
{
    private static EvaluationDAO Instance;

    public static EvaluationDAO getInstance()
    {
        if (Instance == null) Instance = new EvaluationDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    @Deprecated
    public Evaluation findById(Object... id)
    {
        return null;
    }

    @Override
    protected String getTableName()
    {
        return "EVALUATION";
    }

    @Override
    protected Query getInsertQuery(Evaluation object)
    {
        Query q = new Query().addPair("NOTE", object.getNote()).addPair("COMMENTAIRE", object.getCommentaire()).addPair("DATEEVAL", object.getDate()).addPair("ADRESSEMAIL", object.getEvaluateur().getID()).addPair("ID_DEF", object.getDefense()).addPair("ID_CRI", object.getCritere());

        if (object.getTfe() == null) q.addPair("ID_STA", object.getStage().getId());
        else q.addPair("ID_TFE", object.getTfe().getId());

        return q;
    }

    @Override
    @Deprecated
    protected Query getUpdateQuery(Evaluation object)
    {
        return null;
    }

    @Override
    @Deprecated
    protected Query getDeleteQuery(Evaluation object)
    {
        return null;
    }

    @Override
    protected Evaluation getObject(ResultSet res) throws SQLException
    {
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Evaluation> findFromDefense(int id)
    {
        return findFromLabel(id, "ID_DEF");
    }

    public List<Evaluation> findFromEvaluateur(String mail)
    {
        return findFromLabel(mail, "ADRESSEMAIL");
    }

    public List<Evaluation> findFromStage(int id)
    {
        return findFromLabel(id, "ID_STA");
    }

    public List<Evaluation> findFromTFE(int id)
    {
        return findFromLabel(id, "ID_TFE");
    }
}
