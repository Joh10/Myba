package DAO;

import DAO.utils.Query;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 31-05-15 at 15:32.
 */
public class RoleDAO extends AbstractDAO<Role>
{
    private static RoleDAO Instance;

    public static RoleDAO getInstance()
    {
        if (Instance == null) Instance = new RoleDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "ROLE";
    }

    @Override
    public Role findById(Object... id)
    {
        List<Role> list = findFromLabels(id, "NOM");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Role object)
    {
        return new Query().addPair("NOM", object.getData());
    }

    @Override
    @Deprecated
    protected Query getUpdateQuery(Role object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Role object)
    {
        return new Query().addPair("NOM", object.getData());
    }

    @Override
    protected Role getObject(ResultSet res) throws SQLException
    {
        return Role.valueOf(res.getString("NOM"));
    }
}
