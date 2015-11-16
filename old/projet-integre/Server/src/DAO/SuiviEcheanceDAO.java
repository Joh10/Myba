package DAO;

import DAO.utils.Query;
import Server.SuiviEcheance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 14:43.
 */

public class SuiviEcheanceDAO extends AbstractDAO<SuiviEcheance>
{
    private static SuiviEcheanceDAO Instance;

    public static SuiviEcheanceDAO getInstance()
    {
        if (Instance == null) Instance = new SuiviEcheanceDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "SUIVIECHEANCE";
    }


    @Override
    public SuiviEcheance findById(Object... id)
    {
        List<SuiviEcheance> list = findFromLabels(id, "ID_SUI");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(SuiviEcheance object)
    {
        return new Query().addPair("ID_SUI", null).addPair("DOCUMENTRENDU", object.getCheminDocEtudiant()).addPair("DATEETHEUREREMISE", object.getDateRemise()).addPair("CORRECTION", object.getCorrection()).addPair("DOCUMENTCORRECTION", object.getCheminDocCorrection()).addPair("VALIDE", object.isValide()).addPair("ADRESSEMAIL", object.getEtudiant().getID()).addPair("ID_ECH", object.getEcheance().getId());
    }

    @Override
    protected Query getUpdateQuery(SuiviEcheance object)
    {
        return new Query().addPair("DOCUMENTRENDU", object.getCheminDocEtudiant()).addPair("DATEETHEUREREMISE", object.getDateRemise()).addPair("CORRECTION", object.getCorrection()).addPair("DOCUMENTCORRECTION", object.getCheminDocCorrection()).addPair("VALIDE", object.isValide()).addPair("ADRESSEMAIL", object.getEtudiant().getID()).addPair("ID_ECH", object.getEcheance().getId()).setWhereClause("ID_SUI", object.getId());
    }

    @Override
    protected Query getDeleteQuery(SuiviEcheance object)
    {
        return new Query().addPair("ID_SUI", object.getId());
    }

    @Override
    protected SuiviEcheance getObject(ResultSet res) throws SQLException
    {
        return new SuiviEcheance(res.getInt("ID_SUI"), EtudiantDAO.getInstance().findById(res.getString("ADRESSEMAIL")), EcheanceDAO.getInstance().findById(res.getString("ID_ECH")), res.getString("DOCUMENTRENDU"), res.getDate("DATEETHEUREREMISE"), res.getString("CORRECTION"), res.getString("DOCUMENTCORRECTION"), res.getBoolean("VALIDE"));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<SuiviEcheance> findFromEcheance(int id)
    {
        return findFromLabel(id, "ID_ECH");
    }

    public List<SuiviEcheance> findFromEtudiant(String mail)
    {
        return findFromLabel(mail, "ADRESSEMAIL");
    }
}
