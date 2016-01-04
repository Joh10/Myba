package dao;

import beans.Role;
import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_Utilisateur extends DAO<Utilisateur>
{
    public boolean create(Utilisateur obj)
    {
        return true;
    }

    public ArrayList<Utilisateur> fetchAll(String role_name)
    {
        return null;
    }

    private Integer findRole(String role_name)
    {
        return null;
    }

    public Utilisateur find(int id)
    {
        return null;
    }

    private void loadPermissions(Role obj)
    {
    }

    public Utilisateur find(String identifiant, String password)
    {
        return null;
    }

    public boolean update(Utilisateur obj)
    {
        return false;
    }

    public boolean delete(Utilisateur obj)
    {
        return false;
    }
}