package dao;

import beans.Stage;
import beans.Technologie;
import beans.Utilisateur;

import java.util.ArrayList;


public class DAO_Stage extends DAO<Stage>
{
    public boolean create(Stage obj)
    {
        return true;
    }

    private boolean addTechnologie(Stage obj, Technologie obj1)
    {

        return true;
    }

    private ArrayList<Technologie> loadTechnologies(int id)
    {

        return null;
    }

    private boolean clearTechnologies(Stage obj)
    {
        return true;
    }

    public ArrayList<Stage> fetchAll(Utilisateur maitreDeStage)
    {

        return null;
    }

    public Stage find(int id)
    {

        return null;
    }

    public boolean update(Stage obj)
    {

        return false;
    }

    public boolean delete(Stage obj)
    {
        return false;
    }
}