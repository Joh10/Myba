package DAO.relations;

import DAO.AbstractDAO;
import DAO.ProfesseurDAO;
import DAO.TFEDAO;
import DAO.utils.Query;
import Server.Professeur;
import Server.TFE;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 31-05-15 at 15:27.
 */

public class SurveillerDAO extends AbstractDAO<SurveillerDAO.Surveiller>
{
    private static SurveillerDAO Instance;

    public static SurveillerDAO getInstance()
    {
        if (Instance == null) Instance = new SurveillerDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "SURVEILLER";
    }

    @Override
    public Surveiller findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Surveiller object)
    {
        return new Query().addPair("ID_TFE", object.tfe.getId()).addPair("ADRESSEMAIL", object.evaluateur.getID());
    }

    @Override
    protected Query getUpdateQuery(Surveiller object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Surveiller object)
    {
        return null;
    }

    @Override
    protected Surveiller getObject(ResultSet res) throws SQLException
    {
        return new Surveiller(ProfesseurDAO.getInstance().findById(res.getString("ADRESSEMAIL")), TFEDAO.getInstance().findById(res.getInt("ID_TFE")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Professeur> findEvaluateurs(int idSui)
    {
        return findFromLabel(idSui, "ID_TFE").stream().map(Surveiller::getEvaluateur).collect(Collectors.toList());
    }

    public List<TFE> findTFEs(String id)
    {
        return findFromLabel(id, "ADRESSEMAIL").stream().map(Surveiller::getTFE).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public class Surveiller
    {
        final Professeur evaluateur;
        final TFE tfe;

        public Surveiller(Professeur evaluateur, TFE tfe)
        {
            this.evaluateur = evaluateur;
            this.tfe = tfe;
        }

        public Professeur getEvaluateur()
        {
            return evaluateur;
        }

        public TFE getTFE()
        {
            return tfe;
        }
    }
}
