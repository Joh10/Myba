package dao;

import beans.TFE;
import beans.Technologie;

import java.util.ArrayList;

public class DAO_TFE extends DAO<TFE>
{
    public boolean create(TFE obj)
    {
        return true;
    }

    private boolean addTechnologie(TFE obj, Technologie obj1)
    {
        return true;
    }

    private ArrayList<Technologie> loadTechnologies(int id)
    {
        return null;
    }

    private boolean clearTechnologies(TFE obj)
    {
        return true;
    }

    public ArrayList<TFE> fetchAll()
    {
        return null;
    }

    public TFE find(int id)
    {
        return null;
    }

    public boolean update(TFE obj)
    {
        return false;
    }

    public boolean delete(TFE obj)
    {
        return false;
    }
}