package DAO;

import DAO.utils.Query;
import Server.Adresse;
import Server.LieuStage;
import Server.PersonneContact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class LieuStageDAO extends AbstractDAO<LieuStage>
{
    private static LieuStageDAO Instance;

    public static LieuStageDAO getInstance()
    {
        if (Instance == null) Instance = new LieuStageDAO();

        return Instance;
    }

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    @Override
    public LieuStage findById(Object... id)
    {
        List<LieuStage> list = findFromLabels(id, "ID_LIEU");

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }

    @Override
    protected String getTableName()
    {
        return "LIEUDESTAGE";
    }

    @Override
    protected Query getInsertQuery(LieuStage object)
    {
        return new Query().addPair("ID_LIEU", object.getId()).addPair("ENTREPRISE", object.getEntreprise()).addPair("ADR_RUE", object.getAdresse().getStreet()).addPair("ADR_NUMERO", object.getAdresse().getNumber()).addPair("ADR_BOITE", object.getAdresse().getBoite()).addPair("ADR_CODEPOSTAL", object.getAdresse().getCodePostal()).addPair("ADR_VILLE", object.getAdresse().getCity()).addPair("ADR_PAYS", object.getAdresse().getCountry()).addPair("PER_PRENOM", object.getPersonneContact().getPrenom()).addPair("PER_NOM", object.getPersonneContact().getNom()).addPair("PER_TELEPHONE", object.getPersonneContact().getTelephone()).addPair("PE_ADRESSEMAIL", object.getPersonneContact().getMail());
    }

    @Override
    protected Query getUpdateQuery(LieuStage object)
    {
        return new Query().addPair("ENTREPRISE", object.getEntreprise()).addPair("ADR_RUE", object.getAdresse().getStreet()).addPair("ADR_NUMERO", object.getAdresse().getNumber()).addPair("ADR_BOITE", object.getAdresse().getBoite()).addPair("ADR_CODEPOSTAL", object.getAdresse().getCodePostal()).addPair("ADR_VILLE", object.getAdresse().getCity()).addPair("ADR_PAYS", object.getAdresse().getCountry()).addPair("PER_PRENOM", object.getPersonneContact().getPrenom()).addPair("PER_NOM", object.getPersonneContact().getNom()).addPair("PER_TELEPHONE", object.getPersonneContact().getTelephone()).addPair("PE_ADRESSEMAIL", object.getPersonneContact().getMail()).setWhereClause("ID_LIEU", object.getId());
    }

    @Override
    protected Query getDeleteQuery(LieuStage object)
    {
        return new Query().addPair("ID_LIEU", object.getId());
    }

    @Override
    protected LieuStage getObject(ResultSet res) throws SQLException
    {
        return new LieuStage(res.getInt("ID_LIEU"), res.getString("ENTREPRISE"), new Adresse(res.getString("ADR_RUE"), res.getInt("ADR_NUMERO"), res.getString("ADR_BOITE"), res.getInt("ADR_CODEPOSTAL"), res.getString("ADR_VILLE"), res.getString("ADR_PAYS")), new PersonneContact(res.getString("PER_NOM"), res.getString("PER_PRENOM"), res.getString("PER_TELEPHONE"), res.getString("PER_ADRESSEMAIL")

        ));
    }
}
