package Server;

import DAO.DefenseDAO;
import DAO.utils.DAOList;
import Server.Rights.Role;

import java.util.List;

/**
 * Created by Nicolas on 29/04/2015 at 09:06.
 */

public class PresidentJury extends Evaluateur
{
    private final DAOList<Defense> defenses;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public PresidentJury(String mail, String motDePasse, String prenom, String nom)
    {
        super(mail, motDePasse, prenom, nom);
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.defenses.setSource(() -> DefenseDAO.getInstance().findFromPresident(mail));
    }

    public PresidentJury(String mail, String motDePasse, String prenom, String nom, List<Role> r)
    {
        super(mail, motDePasse, prenom, nom, r);
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.defenses.setSource(() -> DefenseDAO.getInstance().findFromPresident(mail));
    }

    /////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addDefense(Defense p)
    {
        return defenses.add(p);
    }

    public boolean removeDefense(Defense p)
    {
        return defenses.remove(p);
    }

    public List<Defense> getDefenses()
    {
        return defenses.get();
    }
}
