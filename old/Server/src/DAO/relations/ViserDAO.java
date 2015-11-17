package DAO.relations;

import DAO.AbstractDAO;
import DAO.EcheanceDAO;
import DAO.EtudiantDAO;
import DAO.utils.Query;
import Server.Echeance;
import Server.Etudiant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 30-05-15 at 15:34.
 */
public class ViserDAO extends AbstractDAO<ViserDAO.Viser>
{
    private static ViserDAO Instance;

    public static ViserDAO getInstance()
    {
        if (Instance == null) Instance = new ViserDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "VISER";
    }

    @Override
    public Viser findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Viser object)
    {
        return new Query().addPair("ID_ECH", object.echeance.getId()).addPair("ADRESSEMAIL", object.etudiant.getID());
    }

    @Override
    protected Query getUpdateQuery(Viser object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Viser object)
    {
        return null;
    }

    @Override
    protected Viser getObject(ResultSet res) throws SQLException
    {
        return new Viser(EcheanceDAO.getInstance().findById(res.getInt("ID_ECH")), EtudiantDAO.getInstance().findById(res.getString("ADRESSEMAIL")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Etudiant> findEtudiants(int echeanceID)
    {
        return findFromLabel(echeanceID, "ID_ECH").stream().map(Viser::getEtudiant).collect(Collectors.toList());
    }

    public List<Echeance> findEcheances(String etudiantID)
    {
        return findFromLabel(etudiantID, "ADRESSEMAIL").stream().map(Viser::getEcheance).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public class Viser
    {
        final Etudiant etudiant;
        final Echeance echeance;

        public Viser(Echeance echeance, Etudiant etudiant)
        {
            this.echeance = echeance;
            this.etudiant = etudiant;
        }

        public Echeance getEcheance()
        {
            return echeance;
        }

        public Etudiant getEtudiant()
        {
            return etudiant;
        }
    }
}
