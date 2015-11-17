package DAO.relations;

import DAO.AbstractDAO;
import DAO.PropositionStageDAO;
import DAO.TechnologieDAO;
import DAO.utils.Query;
import Server.PropositionStage;
import Server.Technologie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 31-05-15 at 15:27.
 */

public class UtiliserDAO extends AbstractDAO<UtiliserDAO.Utiliser>
{
    private static UtiliserDAO Instance;

    public static UtiliserDAO getInstance()
    {
        if (Instance == null) Instance = new UtiliserDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "UTILISER";
    }

    @Override
    public Utiliser findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Utiliser object)
    {
        return new Query().addPair("ID_TEC", object.technologie.getId()).addPair("ID_PRO", object.propo.getId());
    }

    @Override
    protected Query getUpdateQuery(Utiliser object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Utiliser object)
    {
        return null;
    }

    @Override
    protected Utiliser getObject(ResultSet res) throws SQLException
    {
        return new Utiliser(PropositionStageDAO.getInstance().findById(res.getInt("ID_PRO")), TechnologieDAO.getInstance().findById(res.getString("ID_TEC")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<PropositionStage> findPropositions(int idtec)
    {
        return findFromLabel(idtec, "ID_TEC").stream().map(Utiliser::getProposition).collect(Collectors.toList());
    }

    public List<Technologie> findTechnologies(int idpro)
    {
        return findFromLabel(idpro, "ID_PRO").stream().map(Utiliser::getTechnologie).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public class Utiliser
    {
        final PropositionStage propo;
        final Technologie technologie;

        public Utiliser(PropositionStage propo, Technologie technologie)
        {
            this.propo = propo;
            this.technologie = technologie;
        }

        public PropositionStage getProposition()
        {
            return propo;
        }

        public Technologie getTechnologie()
        {
            return technologie;
        }
    }
}
