package DAO;

import DAO.utils.Query;
import Server.Defense;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DefenseDAO extends AbstractDAO<Defense>
{
    private static DefenseDAO Instance;

    public static DefenseDAO getInstance()
    {
        if (Instance == null) Instance = new DefenseDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "DEFENSE";
    }

    @Override
    public Defense findById(Object... id)
    {
        List<Defense> list = findFromLabels(id, "ID_DEF");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Defense object)
    {
        Query q = new Query().addPair("ID_DEF", null).addPair("DATEETHEURE", object.getDateHeure()).addPair("LOCAL", object.getLocal()).addPair("ADRESSEMAIL", object.getPresident().getID());

        if (object.getTfe() == null) q.addPair("ID_STA", object.getStage().getId());
        else q.addPair("ID_TFE", object.getTfe().getId());

        return q;
    }

    @Override
    protected Query getUpdateQuery(Defense object)
    {
        return new Query().addPair("ID_DEF", null).addPair("DATEETHEURE", object.getDateHeure()).addPair("LOCAL", object.getLocal()).setWhereClause("ID_DEF", object.getId());
    }

    @Override
    protected Query getDeleteQuery(Defense object)
    {
        return new Query().addPair("ID_DEF", object.getId());
    }

    @Override
    protected Defense getObject(ResultSet res) throws SQLException
    {
        if (res.getObject("ID_STA") == null)
        {
            return new Defense(res.getInt("ID_DEF"), res.getDate("DATEETHEURE"), res.getString("LOCAL"), TFEDAO.getInstance().findById(res.getInt("ID_TFE")), PresidentJuryDAO.getInstance().findById(res.getString("ADRESSEMAIL")));
        } else
        {
            return new Defense(res.getInt("ID_DEF"), res.getDate("DATEETHEURE"), res.getString("LOCAL"), StageDAO.getInstance().findById(res.getInt("ID_STA")), PresidentJuryDAO.getInstance().findById(res.getString("ADRESSEMAIL")));
        }

    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Defense> findFromPresident(String id)
    {
        return findFromLabel(id, "ADRESSEMAIL");
    }

    public List<Defense> findFromStage(int id)
    {
        return findFromLabel(id, "ID_STA");
    }

    public List<Defense> findFromTFE(int id)
    {
        return findFromLabel(id, "ID_TFE");
    }
}
