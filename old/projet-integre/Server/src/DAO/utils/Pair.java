package DAO.utils;

/**
 * Created by Mixmania on 26-05-15 at 22:51.
 */

public class Pair
{
    private final String name;
    private final Object data;

    public Pair(String name, Object data)
    {
        this.data = data;
        this.name = name;
    }

    public Object getData()
    {
        return data;
    }

    public String getName()
    {
        return name;
    }
}