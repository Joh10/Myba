package DAO.relations;

import DAO.AbstractDAO;
import DAO.EtudiantDAO;
import DAO.RoleDAO;
import DAO.utils.Query;
import Server.Etudiant;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 14-06-15 at 16:08.
 */
public class AssumerDAO extends AbstractDAO<AssumerDAO.Assumer>
{
    private static AssumerDAO Instance;

    public static AssumerDAO getInstance()
    {
        if (Instance == null) Instance = new AssumerDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "ASSUMER";
    }

    @Override
    public Assumer findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Assumer object)
    {
        return new Query().addPair("NOM", object.role.getData()).addPair("ADRESSEMAIL", object.etudiant.getID());
    }

    @Override
    protected Query getUpdateQuery(Assumer object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Assumer object)
    {
        return null;
    }

    @Override
    protected Assumer getObject(ResultSet res) throws SQLException
    {
        return new Assumer(EtudiantDAO.getInstance().findById(res.getString("ADRESSEMAIL")), RoleDAO.getInstance().findById(res.getString("NOM")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Role> findRoles(String id)
    {
        System.err.println("FIND ROLES");
        return findFromLabel(id, "ADRESSEMAIL").stream().map(Assumer::getRole).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public class Assumer
    {
        final Etudiant etudiant;
        final Role role;

        public Assumer(Etudiant etudiant, Role role)
        {
            this.etudiant = etudiant;
            this.role = role;
        }

        public Etudiant getEtudiant()
        {
            return etudiant;
        }

        public Role getRole()
        {
            return role;
        }
    }
}
