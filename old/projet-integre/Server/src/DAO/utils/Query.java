package DAO.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mixmania on 26-05-15 at 22:10.
 */

public class Query
{
    private final List<Pair> dataPair;
    private Pair PKPair;

    public Query()
    {
        dataPair = new ArrayList<>();
    }

    public Query addPair(String name, Object data)
    {
        dataPair.add(new Pair(name, data));
        return this;
    }

    public Query setWhereClause(String name, Object data)
    {
        PKPair = new Pair(name, data);
        return this;
    }

    public Pair getWhereClause()
    {
        return PKPair;
    }

    public List<Pair> getDataPairs()
    {
        return dataPair;
    }

    public int size()
    {
        return dataPair.size();
    }
}
