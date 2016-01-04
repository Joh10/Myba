package dao;

import beans.PropositionStage;
import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_PropositionStage extends DAO<PropositionStage>
{
    public boolean create(PropositionStage obj)
    {
        return true;
    }

    public ArrayList<PropositionStage> fetchAll(Utilisateur obj, String passrole)
    {
        return null;
    }

    public PropositionStage find(int id)
    {
        return null;
    }

    public boolean update(PropositionStage obj)
    {
        return false;
    }

    public boolean delete(PropositionStage obj)
    {
        return false;
    }
}