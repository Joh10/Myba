package DAO.utils;

import DAO.AbstractDAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mixmania on 29-05-15 at 22:35.
 */

public class DAOList<T> implements Iterable<T>
{
    private List<T> data;
    private final AbstractDAO<T> theDAO;

    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    private boolean loaded;
    private MethodDataSource<T> loadingFunction;

    //Le DAO avec le quel la liste travaille
    public DAOList(AbstractDAO<T> d)
    {
        this.data = new ArrayList<>();
        this.loaded = false;
        this.theDAO = d;
    }

    @Override
    public Iterator<T> iterator()
    {
        return data.iterator();
    }

    //Ajoute un objet a la liste, et dis au DAO de l'ajouter dans la table
    public boolean add(T t)
    {
        if (!loaded) load();

        theDAO.insert(t);
        return data.add(t);
    }

    //Supprime un objet de la liste, et le supprime de la table
    public T remove(int index)
    {
        theDAO.delete(data.get(index));
        return data.remove(index);
    }

    //Supprime un objet de la liste, et le supprime de la table
    public boolean remove(T o)
    {
        theDAO.delete(o);
        return data.remove(o);
    }

    //Recupère la List<>
    public List<T> get()
    {
        if (!loaded) load();

        return data;
    }

    private void load()
    {
        data = loadingFunction.call();
        loaded = true;
    }

    public void setSource(MethodDataSource<T> t)
    {
        this.loadingFunction = t;
    }

    @FunctionalInterface
    public interface MethodDataSource<T>
    {
        List<T> call();
    }
}
