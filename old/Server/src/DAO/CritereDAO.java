package DAO;

import DAO.utils.Query;
import Server.Critere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CritereDAO extends AbstractDAO<Critere>
{
    private static CritereDAO Instance;

    public static CritereDAO getInstance()
    {
        if (Instance == null) Instance = new CritereDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "CRITERE";
    }

    @Override
    public Critere findById(Object... id)
    {
        List<Critere> list = findFromLabels(id, "ID_CRI");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Critere object)
    {
        return new Query().addPair("ID_CRI", null).addPair("NOM", object.getNom()).addPair("TYPE", object.getType()).addPair("NOTEMAX", object.getNoteMax());
    }

    @Override
    protected Query getUpdateQuery(Critere object)
    {
        return new Query().addPair("NOM", object.getNom()).addPair("TYPE", object.getType()).addPair("NOTEMAX", object.getNoteMax()).setWhereClause("ID_CRI", object.getId());
    }

    @Override
    protected Query getDeleteQuery(Critere object)
    {
        return new Query().addPair("ID_CRI", object.getId());
    }

    @Override
    protected Critere getObject(ResultSet res) throws SQLException
    {
        return new Critere(res.getInt(1), res.getString(2), res.getString(3), res.getInt(4));
    }
}
