package dao;

import beans.Stage;
import beans.Utilisateur;

import java.util.ArrayList;


public class DAO_Stage extends DAO<Stage>
{
    public ArrayList<Stage> fetchAll(Utilisateur maitreDeStage)
    {

        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Stage.class;
    }
}