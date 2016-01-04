package dao;

import beans.LieuStage;

import java.util.ArrayList;


public class DAO_LieuStage extends DAO<LieuStage>
{
    public ArrayList<LieuStage> fetchAll()
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return LieuStage.class;
    }
}