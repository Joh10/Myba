package DAO;

import DAO.relations.IntitulerDAO;
import DAO.utils.Query;
import Server.MaitreStage;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 13:42.
 */

public class MaitreStageDAO extends AbstractDAO<MaitreStage>
{
    private static MaitreStageDAO Instance;

    public static MaitreStageDAO getInstance()
    {
        if (Instance == null) Instance = new MaitreStageDAO();

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
    public MaitreStage findById(Object... id)
    {
        List<MaitreStage> list = findFromLabels(id, "ADRESSEMAIL");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(MaitreStage object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID()).addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom());
    }

    @Override
    protected Query getUpdateQuery(MaitreStage object)
    {
        return new Query().addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom()).setWhereClause("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Query getDeleteQuery(MaitreStage object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID());
    }

    @Override
    protected MaitreStage getObject(ResultSet res) throws SQLException
    {
        List<Role> roles = IntitulerDAO.getInstance().findRoles(res.getString("ADRESSEMAIL"));

        if (roles.contains(Role.MAITRESTAGE))
        {
            return new MaitreStage(res.getString("ADRESSEMAIL"), res.getString("MOTDEPASSE"), res.getString("PRENOM"), res.getString("NOM"), roles);
        }

        return null;
    }

    @Override
    public List<MaitreStage> findAll()
    {
        List<MaitreStage> t = super.findAll();
        Iterator<MaitreStage> it = t.iterator();

        while (it.hasNext()) if (it.next() == null) it.remove();

        return t;
    }

    public boolean connect(String username, String password)
    {
        MaitreStage t = findById(username);
        return !(t == null || !findById(username).getMotDePasse().equals(password));
    }

}
