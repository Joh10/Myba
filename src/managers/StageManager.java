package managers;

import beans.Stage;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;

import java.util.ArrayList;


public class StageManager extends HibernateManager<Stage>
{
    public ArrayList<Stage> fetchAll(Utilisateur maitreDeStage)
    {
        //TODO
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Stage.class;
    }
}