package dao;


import beans.Defense;
import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_Defense extends DAO<Defense>
{
    public ArrayList<Defense> fetchAll(int type, Utilisateur obj)
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Defense.class;
    }
}