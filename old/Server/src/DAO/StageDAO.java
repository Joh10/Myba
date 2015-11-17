package DAO;

import DAO.utils.Query;
import Server.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mixmania on 31/05/2015 at 14:26.
 */

public class StageDAO extends AbstractDAO<Stage>
{
    private static StageDAO Instance;

    public static StageDAO getInstance()
    {
        if (Instance == null) Instance = new StageDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "STAGE";
    }

    @Override
    public Stage findById(Object... id)
    {
        List<Stage> list = findFromLabels(id, "ID_STA");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected Query getInsertQuery(Stage object)
    {
        return new Query().addPair("ID_STA", null).addPair("ID_PRO", object.getPropositionStage().getId()).addPair("DATEDEBUT", object.getDateDebut()).addPair("DATEFIN", object.getDateFin()).addPair("ADRESSEMAIL", object.getPromoteur().getID()).addPair("COMMENTAIRE", object.getCommentaire()).addPair("MON_ADRESSEMAIL", object.getMaitreStage().getID()).addPair("EFF_ADRESSEMAIL", object.getEtudiant().getID());
    }

    @Override
    protected Query getUpdateQuery(Stage object)
    {
        return new Query().addPair("ID_PRO", object.getPropositionStage().getId()).addPair("DATEDEBUT", object.getDateDebut()).addPair("DATEFIN", object.getDateFin()).addPair("ADRESSEMAIL", object.getPromoteur().getID()).addPair("COMMENTAIRE", object.getCommentaire()).addPair("MON_ADRESSEMAIL", object.getMaitreStage().getID()).addPair("EFF_ADRESSEMAIL", object.getEtudiant().getID()).setWhereClause("ID_STA", object.getId());
    }

    @Override
    protected Query getDeleteQuery(Stage object)
    {
        return new Query().addPair("ID_STA", object.getId());
    }

    @Override
    protected Stage getObject(ResultSet res) throws SQLException
    {
        return new Stage(res.getInt("ID_STA"), res.getDate("DATEDEBUT"), res.getDate("DATEFIN"), PropositionStageDAO.getInstance().findById(res.getInt("ID_PRO")), EtudiantDAO.getInstance().findById(res.getString("EFF_ADRESSEMAIL")), MaitreStageDAO.getInstance().findById(res.getString("MON_ADRESSEMAIL")), ProfesseurDAO.getInstance().findById(res.getString("ADRESSEMAIL")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Stage> findFromMaitreStage(String mail)
    {
        return findFromLabel(mail, "MON_ADRESSEMAIL");
    }

    public List<Stage> findFromProfesseur(String mail)
    {
        return findFromLabel(mail, "ADRESSEMAIL");
    }

    public Stage findFromEtudiant(String mail)
    {
        List<Stage> t = findFromLabel(mail, "EFF_ADRESSEMAIL");

        if (t == null || t.size() == 0) return null;
        else return t.get(0);
    }
}
