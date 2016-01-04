package dao;

import beans.CritereEvaluation;

import java.util.ArrayList;

public class DAO_CritereEvaluation extends DAO<CritereEvaluation>
{
    public boolean create(CritereEvaluation obj)
    {
        return true;
    }

    public ArrayList<CritereEvaluation> fetchAll(String type)
    {
        return null;
    }

    public CritereEvaluation find(int id)
    {
        return null;
    }

    public boolean update(CritereEvaluation obj)
    {
        return false;
    }

    public boolean delete(CritereEvaluation obj)
    {
        return false;
    }
}