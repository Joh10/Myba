package DAO;

import DAO.relations.IntitulerDAO;
import DAO.utils.Query;
import Server.Professeur;
import Server.Rights.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 14:06.
 */

public class ProfesseurDAO extends AbstractDAO<Professeur>
{
    private static ProfesseurDAO Instance;

    public static ProfesseurDAO getInstance()
    {
        if (Instance == null) Instance = new ProfesseurDAO();

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
    public Professeur findById(Object... id)
    {
        List<Professeur> list = findFromLabels(id, "ADRESSEMAIL");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Professeur object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID()).addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom());
    }

    @Override
    protected Query getUpdateQuery(Professeur object)
    {
        return new Query().addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom()).setWhereClause("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Query getDeleteQuery(Professeur object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Professeur getObject(ResultSet res) throws SQLException
    {
        List<Role> roles = IntitulerDAO.getInstance().findRoles(res.getString("ADRESSEMAIL"));

        if (roles.contains(Role.PROFESSEUR))
        {
            return new Professeur(res.getString("ADRESSEMAIL"), res.getString("MOTDEPASSE"), res.getString("PRENOM"), res.getString("NOM"), roles);
        }

        return null;
    }

    @Override
    public List<Professeur> findAll()
    {
        List<Professeur> t = super.findAll();
        Iterator<Professeur> it = t.iterator();

        while (it.hasNext()) if (it.next() == null) it.remove();

        return t;
    }

    public boolean connect(String username, String password)
    {
        Professeur t = findById(username);
        return !(t == null || !findById(username).getMotDePasse().equals(password));
    }
}
