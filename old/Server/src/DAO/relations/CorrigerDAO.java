package DAO.relations;

import DAO.AbstractDAO;
import DAO.ProfesseurDAO;
import DAO.SuiviEcheanceDAO;
import DAO.utils.Query;
import Server.Professeur;
import Server.SuiviEcheance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 31-05-15 at 15:27.
 */
public class CorrigerDAO extends AbstractDAO<CorrigerDAO.Corriger>
{
    private static CorrigerDAO Instance;

    public static CorrigerDAO getInstance()
    {
        if (Instance == null) Instance = new CorrigerDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    protected String getTableName()
    {
        return "INTITULER";
    }

    @Override
    public Corriger findById(Object... id)
    {
        return null;
    }

    @Override
    protected Query getInsertQuery(Corriger object)
    {
        return new Query().addPair("ID_SUI", object.suivis.getId()).addPair("ADRESSEMAIL", object.evaluateur.getID());
    }

    @Override
    protected Query getUpdateQuery(Corriger object)
    {
        return null;
    }

    @Override
    protected Query getDeleteQuery(Corriger object)
    {
        return null;
    }

    @Override
    protected Corriger getObject(ResultSet res) throws SQLException
    {
        return new Corriger(ProfesseurDAO.getInstance().findById(res.getInt("ADRESSEMAIL")), SuiviEcheanceDAO.getInstance().findById(res.getString("ID_SUI")));
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public List<Professeur> findEvaluateurs(int idSui)
    {
        return findFromLabel(idSui, "ID_SUI").stream().map(Corriger::getEvaluateur).collect(Collectors.toList());
    }

    public List<SuiviEcheance> findSuivis(String id)
    {
        return findFromLabel(id, "ADRESSEMAIL").stream().map(Corriger::getSuivis).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public class Corriger
    {
        final Professeur evaluateur;
        final SuiviEcheance suivis;

        public Corriger(Professeur evaluateur, SuiviEcheance suivis)
        {
            this.evaluateur = evaluateur;
            this.suivis = suivis;
        }

        public Professeur getEvaluateur()
        {
            return evaluateur;
        }

        public SuiviEcheance getSuivis()
        {
            return suivis;
        }
    }

}
