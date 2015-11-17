package DAO.relations;

import DAO.AbstractDAO;
import DAO.TFEDAO;
import DAO.TechnologieDAO;
import DAO.utils.Query;
import Server.TFE;
import Server.Technologie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 31-05-15 at 15:27.
 */

public class ImplementerDAO extends AbstractDAO<ImplementerDAO.Implementer>
{
    private static ImplementerDAO Instance;

    public static ImplementerDAO getInstance()
    {
        if (Instance == null) Instance = new ImplementerDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "IMPLEMENTER";
    }

    @Override
    public Implementer findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Implementer object)
    {
        return new Query().addPair("ID_TEC", object.technologie.getId()).addPair("ID_TFE", object.tfe.getId());
    }

    @Override
    protected Query getUpdateQuery(Implementer object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Implementer object)
    {
        return null;
    }

    @Override
    protected Implementer getObject(ResultSet res) throws SQLException
    {
        return new Implementer(TFEDAO.getInstance().findById(res.getInt("ID_TFE")), TechnologieDAO.getInstance().findById(res.getString("ID_TEC")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<TFE> findTFE(int idtec)
    {
        return findFromLabel(idtec, "ID_TEC").stream().map(Implementer::getTFE).collect(Collectors.toList());
    }

    public List<Technologie> findTechnologies(int idtfe)
    {
        return findFromLabel(idtfe, "ID_TFE").stream().map(Implementer::getTechnologie).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public class Implementer
    {
        final TFE tfe;
        final Technologie technologie;

        public Implementer(TFE tfe, Technologie technologie)
        {
            this.tfe = tfe;
            this.technologie = technologie;
        }

        public TFE getTFE()
        {
            return tfe;
        }

        public Technologie getTechnologie()
        {
            return technologie;
        }
    }
}
