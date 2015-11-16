package DAO;

import DAO.utils.Query;
import Server.Telephone;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 30-05-15 at 11:08.
 */

public class TelephoneDAO extends AbstractDAO<Telephone>
{
    private static TelephoneDAO Instance;

    public static TelephoneDAO getInstance()
    {
        if (Instance == null) Instance = new TelephoneDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "TELEPHONE";
    }

    @Override
    public Telephone findById(Object... id)
    {
        List<Telephone> list = findFromLabels(id, "ADRESSEMAIL", "TELEPHONE");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Telephone object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getOwner()).addPair("TELEPHONE", object.getNumber());
    }

    @Override
    protected Query getUpdateQuery(Telephone object)
    {
        return new Query().addPair("TELEPHONE", object.getNumber()).setWhereClause("ADRESSEMAIL", object.getOwner());
    }

    @Override
    protected Query getDeleteQuery(Telephone object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getOwner());
    }

    @Override
    protected Telephone getObject(ResultSet res) throws SQLException
    {
        return new Telephone(res.getString("ADRESSEMAIL"), res.getString("TELEPHONE"));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Telephone> findFromEvaluateur(String mail)
    {
        return findFromLabel(mail, "ADRESSEMAIL");
    }
}
