package dao;

import beans.TFE;

import java.util.ArrayList;

public class DAO_TFE extends DAO<TFE>
{
    public ArrayList<TFE> fetchAll()
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return TFE.class;
    }
}