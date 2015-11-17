package DAO;

import DAO.utils.Query;
import Server.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EtudiantDAO extends AbstractDAO<Etudiant>
{
    private static EtudiantDAO Instance;

    public static EtudiantDAO getInstance()
    {
        if (Instance == null) Instance = new EtudiantDAO();

        return Instance;
    }

    @Override
    protected String getTableName()
    {
        return "ETUDIANT";
    }

    @Override
    public Etudiant findById(Object... id)
    {
        List<Etudiant> list = findFromLabels(id, "ADRESSEMAIL");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getUpdateQuery(Etudiant object)
    {
        return new Query().addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom()).addPair("MATRICULE", object.getMatricule()).addPair("ANNEE", object.getAnnee()).addPair("ESTDOUBLANT", false).addPair("ESTDISPENSE", false).setWhereClause("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Query getInsertQuery(Etudiant object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID()).addPair("MOTDEPASSE", object.getMotDePasse()).addPair("PRENOM", object.getPrenom()).addPair("NOM", object.getNom()).addPair("MATRICULE", object.getMatricule()).addPair("ANNEE", object.getAnnee()).addPair("ESTDOUBLANT", false).addPair("ESTDISPENSE", false);
    }

    @Override
    protected Query getDeleteQuery(Etudiant object)
    {
        return new Query().addPair("ADRESSEMAIL", object.getID());
    }

    @Override
    protected Etudiant getObject(ResultSet res) throws SQLException
    {
        return new Etudiant(res.getString("ADRESSEMAIL"), res.getString("MOTDEPASSE"), res.getString("PRENOM"), res.getString("NOM"), res.getString("MATRICULE"), res.getInt("ANNEE"), res.getBoolean("ESTDOUBLANT"), res.getBoolean("ESTDISPENSETFE"), res.getBoolean("ESTDISPENSESTAGE"));
    }

    public boolean connect(String username, String password)
    {
        Etudiant t = findById(username);
        return !(t == null || !findById(username).getMotDePasse().equals(password));
    }
}
