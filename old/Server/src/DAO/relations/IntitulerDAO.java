package DAO.relations;

import DAO.AbstractDAO;
import DAO.utils.Query;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 31-05-15 at 15:27.
 */

public class IntitulerDAO extends AbstractDAO<IntitulerDAO.Intituler>
{
    private static IntitulerDAO Instance;

    public static IntitulerDAO getInstance()
    {
        if (Instance == null) Instance = new IntitulerDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "INTITULER";
    }

    @Override
    public Intituler findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Intituler object)
    {
        return new Query().addPair("NOM", object.role.getData()).addPair("ADRESSEMAIL", object.evaluateur);
    }

    @Override
    protected Query getUpdateQuery(Intituler object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Intituler object)
    {
        return null;
    }

    @Override
    protected Intituler getObject(ResultSet res) throws SQLException
    {
        return new Intituler(res.getString("ADRESSEMAIL"), Role.valueOf(res.getString("NOM")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Role> findRoles(String id)
    {
        return findFromLabel(id, "ADRESSEMAIL").stream().map(Intituler::getRole).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public class Intituler
    {
        final String evaluateur;
        final Role role;

        public Intituler(String evaluateur, Role role)
        {
            this.evaluateur = evaluateur;
            this.role = role;
        }

        public String getEvaluateur()
        {
            return evaluateur;
        }

        public Role getRole()
        {
            return role;
        }
    }
}
