package dao;

import beans.PropositionStage;
import beans.Utilisateur;

import java.util.ArrayList;

public class DAO_PropositionStage extends DAO<PropositionStage>
{
    public ArrayList<PropositionStage> fetchAll(Utilisateur obj, String passrole)
    {
        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return PropositionStage.class;
    }
}