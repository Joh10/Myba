package dao;

import beans.*;

import java.util.ArrayList;

public class DAO_Evaluation extends DAO<Evaluation>
{
    public ArrayList<Evaluation> fetchAll(Stage stage)
    {
        return fetchAllBack(" WHERE `stage_id` = ?", stage.getId(), null);
    }

    public ArrayList<Evaluation> fetchAll(TFE tfe)
    {
        return fetchAllBack(" WHERE `tfe_id` = ?", tfe.getId(), null);
    }

    public ArrayList<Evaluation> fetchAll(Defense defense)
    {
        return fetchAllBack(" WHERE `defense_id` = ?", defense.getId(), null);
    }

    public ArrayList<Evaluation> fetchAll(Utilisateur owner, Stage stage)
    {
        return fetchAllBack(" WHERE `owner_id` = ? AND stage_id = ?", owner.getId(), stage.getId());
    }

    public ArrayList<Evaluation> fetchAll(Utilisateur owner, Defense defense)
    {
        return fetchAllBack(" WHERE `owner_id` = ? AND defense_id = ?", owner.getId(), defense.getId());
    }

    private ArrayList<Evaluation> fetchAllBack(String query, Integer param, Integer param2)
    {
        return null;
    }


    @Override
    protected Class<?> getEntityClass()
    {
        return Evaluation.class;
    }
}