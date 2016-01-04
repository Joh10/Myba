package dao;


import beans.Technologie;

import java.util.ArrayList;

public class DAO_Technologie extends DAO<Technologie>
{
    public ArrayList<Technologie> fetchAll()
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Technologie.class;
    }
}