package DAO;

import DAO.utils.Query;
import Server.Technologie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 16:07.
 */

public class TechnologieDAO extends AbstractDAO<Technologie>
{
    private static TechnologieDAO Instance;

    public static TechnologieDAO getInstance()
    {
        if (Instance == null) Instance = new TechnologieDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "TECHNOLOGIE";
    }

    @Override
    public Technologie findById(Object... id)
    {
        List<Technologie> list = findFromLabels(id, "ID_TEC");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Technologie object)
    {
        return new Query().addPair("ID_TECH", null).addPair("NOM", object.getNom()).addPair("VERSION", object.getVersion());
    }

    @Override
    protected Query getUpdateQuery(Technologie object)
    {
        return new Query().addPair("NOM", object.getNom()).addPair("VERSION", object.getVersion()).setWhereClause("ID_TECH", object.getId());
    }

    @Override
    protected Query getDeleteQuery(Technologie object)
    {
        return new Query().addPair("ID_TEC", object.getId());
    }

    @Override
    protected Technologie getObject(ResultSet res) throws SQLException
    {
        return new Technologie(res.getInt("ID_TEC"), res.getString("NOM"), res.getString("VERSION"));
    }
}
