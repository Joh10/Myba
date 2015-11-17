package DAO;

import DAO.relations.IntitulerDAO;
import DAO.utils.Query;
import Server.PresidentJury;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 13:58.
 */

public class PresidentJuryDAO extends AbstractDAO<PresidentJury>
{
    private static PresidentJuryDAO Instance;

    public static PresidentJuryDAO getInstance()
    {
        if (Instance == null) Instance = new PresidentJuryDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "EVALUATEUR";
    }

    @Override
    public PresidentJury findById(Object... id)
    {
        List<PresidentJury> list = findFromLabels(id, "ADRESSEMAIL");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(PresidentJury object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID()).addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom());
    }

    @Override
    protected Query getUpdateQuery(PresidentJury object)
    {
        return new Query().addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom()).setWhereClause("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Query getDeleteQuery(PresidentJury object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID());
    }

    @Override
    protected PresidentJury getObject(ResultSet res) throws SQLException
    {
        List<Role> roles = IntitulerDAO.getInstance().findRoles(res.getString("ADRESSEMAIL"));

        if (roles.contains(Role.PRESIDENTJURY))
        {
            return new PresidentJury(res.getString("ADRESSEMAIL"), res.getString("MOTDEPASSE"), res.getString("PRENOM"), res.getString("NOM"), roles);
        }

        return null;
    }

    @Override
    public List<PresidentJury> findAll()
    {
        List<PresidentJury> t = super.findAll();
        Iterator<PresidentJury> it = t.iterator();

        while (it.hasNext()) if (it.next() == null) it.remove();

        return t;
    }

    public boolean connect(String username, String password)
    {
        PresidentJury t = findById(username);
        return !(t == null || !findById(username).getMotDePasse().equals(password));
    }
}
