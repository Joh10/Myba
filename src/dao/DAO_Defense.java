package dao;


import beans.Defense;
import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_Defense extends DAO<Defense>
{
    public boolean create(Defense obj)
    {
        return true;
    }

    public ArrayList<Defense> fetchAll(int type, Utilisateur obj)
    {
        return null;
    }

    public Defense find(int id)
    {

        return null;
    }

    public boolean update(Defense obj)
    {

        return false;
    }

    public boolean delete(Defense obj)
    {
        return false;
    }
}