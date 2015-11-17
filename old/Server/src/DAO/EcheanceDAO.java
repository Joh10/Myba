package DAO;

import DAO.utils.Query;
import Server.Echeance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nicolas on 8/05/2015 at 08:06.
 */
public class EcheanceDAO extends AbstractDAO<Echeance>
{
    private static EcheanceDAO Instance;

    public static EcheanceDAO getInstance()
    {
        if (Instance == null) Instance = new EcheanceDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "ECHEANCE";
    }

    @Override
    public Echeance findById(Object... id)
    {
        List<Echeance> list = findFromLabels(id, "ID_ECH");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Echeance object)
    {
        Query q = new Query().addPair("ID_ECH", null).addPair("DATEETHEURE", object.getDate()).addPair("TITRE", object.getTitre()).addPair("DESCRIPTION", object.getDescription()).addPair("ANNEXE", object.getAnnexe());

        if (object.getTfe() == null) q.addPair("ID_STA", object.getStage().getId());
        else q.addPair("ID_TFE", object.getTfe().getId());

        return q;
    }

    @Override
    protected Query getUpdateQuery(Echeance object)
    {
        return new Query().addPair("DATEHEURE", object.getDate()).addPair("TITRE", object.getTitre()).addPair("DESCRIPTION", object.getDescription()).addPair("ANNEXE", object.getAnnexe()).setWhereClause("ID_ECH", object.getId());
    }

    @Override
    protected Query getDeleteQuery(Echeance object)
    {
        return new Query().addPair("ID_ECH", object.getId());
    }

    @Override
    protected Echeance getObject(ResultSet res) throws SQLException
    {
        if (res.getObject("ID_STA") == null)
        {
            return new Echeance(res.getInt("ID_ECH"), res.getDate("DATEHEURE"), res.getString("TITRE"), res.getString("DESCRIPTION"), res.getString("ANNEXE"), TFEDAO.getInstance().findById(res.getInt("ID_TFE")));
        } else
        {
            return new Echeance(res.getInt("ID_ECH"), res.getDate("DATEHEURE"), res.getString("TITRE"), res.getString("DESCRIPTION"), res.getString("ANNEXE"), StageDAO.getInstance().findById(res.getInt("ID_STA")));
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Echeance> findFromStage(int id)
    {
        return findFromLabel(id, "ID_STA");
    }

    public List<Echeance> findFromTFE(int id)
    {
        return findFromLabel(id, "ID_TFE");
    }
}
