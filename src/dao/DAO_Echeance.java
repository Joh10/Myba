package dao;

import beans.Echeance;
import beans.Stage;
import beans.TFE;
import beans.Utilisateur;

import java.util.ArrayList;


public class DAO_Echeance extends DAO<Echeance>
{
    public boolean create(Echeance obj)
    {
        return true;
    }

    private boolean addUser(Echeance obj, Utilisateur obj1)
    {
        return true;
    }

    private ArrayList<Utilisateur> loadUsers(int id)
    {
        return null;
    }

    private boolean clearUsers(Echeance obj)
    {
        return true;
    }

    public ArrayList<Echeance> fetchAll(Stage stage)
    {
        return fetchAllBack("SELECT * FROM `echeances` WHERE `stage_id` = ?", stage.getId(), false);
    }

    public ArrayList<Echeance> fetchAll(TFE tfe)
    {
        return fetchAllBack("SELECT * FROM `echeances` WHERE `tfe_id` = ?", tfe.getId(), false);
    }

    public ArrayList<Echeance> fetchAll(Utilisateur utilisateur)
    {
        return fetchAllBack("SELECT e.* FROM `echeances` e, `echeances_cibles` c WHERE e.`tfe_id` is null AND e.`stage_id` is null AND e.`id` = c.`echeance_id` AND c.`user_id` = ? " + "UNION SELECT e.* FROM `echeances` e, `tfe` t WHERE e.`tfe_id` is not null AND e.`tfe_id` = t.`id` AND t.`owner_id` = ? " + "UNION SELECT e.* FROM `echeances` e, `stages` s WHERE e.`stage_id` is not null AND e.`stage_id` = s.`id` AND s.`owner_id` = ?", utilisateur.getId(), true);
    }

    public ArrayList<Echeance> fetchAll()
    {
        return fetchAllBack("SELECT * FROM `echeances`", null, false);
    }

    private ArrayList<Echeance> fetchAllBack(String query, Integer param, boolean repeat)
    {
        return null;
    }

    public Echeance find(int id)
    {
        return null;
    }

    public boolean update(Echeance obj)
    {
        return false;
    }

    public boolean delete(Echeance obj)
    {
        return false;
    }
}