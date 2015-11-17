package DAO;

import DAO.utils.Query;
import Server.PropositionStage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 14:18.
 */

public class PropositionStageDAO extends AbstractDAO<PropositionStage>
{
    private static PropositionStageDAO Instance;

    public static PropositionStageDAO getInstance()
    {
        if (Instance == null) Instance = new PropositionStageDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "PROPOSITIONSTAGE";
    }

    @Override
    public PropositionStage findById(Object... id)
    {
        List<PropositionStage> list = findFromLabels(id, "ID_PRO");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(PropositionStage object)
    {
        return new Query().addPair("ID_PRO", null).addPair("SUJET", object.getSujet()).addPair("ANNEXEEVENTUELLE", object.getCheminAnnexe()).addPair("VALIDE", object.isValide()).addPair("ADRESSEMAIL", object.getPosteur().getID()).addPair("OFF_ADRESSEMAIL", "").addPair("ID_LIEU", object.getLieuStage().getId());
    }

    @Override
    protected Query getUpdateQuery(PropositionStage object)
    {
        return new Query().addPair("SUJET", object.getSujet()).addPair("ANNEXEEVENTUELLE", object.getCheminAnnexe()).addPair("VALIDE", object.isValide()).addPair("ADRESSEMAIL", object.getPosteur().getID()).addPair("OFF_ADRESSEMAIL", "").addPair("ID_LIEU", object.getLieuStage().getId()).setWhereClause("ID_PRO", object.getId());
    }

    @Override
    protected Query getDeleteQuery(PropositionStage object)
    {
        return new Query().addPair("ID_PRO", object.getId());
    }

    @Override
    protected PropositionStage getObject(ResultSet res) throws SQLException
    {
        return new PropositionStage(res.getInt("ID_PRO"), LieuStageDAO.getInstance().findById(res.getInt("ID_LIEU")), res.getString("SUJET"), res.getString("ANNEXEEVENTUELLE"),

                () -> {
                    try
                    {
                        return res.getString("ADRESSEMAIL");
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }

                    return null;
                });
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<PropositionStage> findFromEtudiant(String mail)
    {
        return findFromLabel(mail, "ADRESSEMAIL");
    }

    public List<PropositionStage> findFromEvaluateur(String mail)
    {
        return findFromLabel(mail, "OFF_ADRESSEMAIL");
    }
}
