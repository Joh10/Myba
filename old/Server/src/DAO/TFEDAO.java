package DAO;

import DAO.utils.Query;
import Server.TFE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TFEDAO extends AbstractDAO<TFE>
{
    private static TFEDAO Instance;

    public static TFEDAO getInstance()
    {
        if (Instance == null) Instance = new TFEDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "TFE";
    }

    @Override
    public TFE findById(Object... id)
    {
        List<TFE> list = findFromLabels(id, "ID_TFE");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(TFE object)
    {
        return new Query().addPair("ID_TFE", null).addPair("ANNEEACADDEBUT", object.getAnneeAcadDebut()).addPair("ANNEEACADFIN", object.getAnneeAcadFin()).addPair("TITRE", object.getTitre()).addPair("ADRESSEMAIL", object.getEtudiant().getID());
    }

    @Override
    protected Query getUpdateQuery(TFE object)
    {
        return new Query().addPair("ANNEEACADDEBUT", object.getAnneeAcadDebut()).addPair("ANNEEACADFIN", object.getAnneeAcadFin()).addPair("TITRE", object.getTitre()).addPair("ADRESSEMAIL", object.getEtudiant().getID()).setWhereClause("ID_TFE", object.getId());
    }

    @Override
    protected Query getDeleteQuery(TFE object)
    {
        return new Query().addPair("ID_TFE", object.getId());
    }

    @Override
    protected TFE getObject(ResultSet res) throws SQLException
    {
        return new TFE(res.getInt("ID_TFE"), res.getString("TITRE"), EtudiantDAO.getInstance().findById(res.getString("ADRESSEMAIL")), res.getInt("ANNEEACADDEBUT"), res.getInt("ANNEEACADFIN"));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public TFE findFromEtudiant(String mail)
    {
        List<TFE> t = findFromLabel(mail, "ADRESSEMAIL");

        if (t == null || t.size() == 0) return null;
        else return t.get(0);
    }
}
