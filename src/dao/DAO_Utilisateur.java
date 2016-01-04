package dao;

import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_Utilisateur extends DAO<Utilisateur>
{
    public ArrayList<Utilisateur> fetchAll(String role_name)
    {
        return null;
    }

    public Utilisateur find(String identifiant, String password)
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Utilisateur.class;
    }
}