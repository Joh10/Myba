package dao;


import beans.Technologie;

import java.util.ArrayList;

public class DAO_Technologie extends DAO<Technologie>
{
    public boolean create(Technologie obj)
    {

        return true;
    }

    public ArrayList<Technologie> fetchAll()
    {
        return null;
    }

    public Technologie find(int id)
    {
        return null;
    }

    public boolean update(Technologie obj)
    {
        return false;
    }

    public boolean delete(Technologie obj)
    {
        return false;
    }
}