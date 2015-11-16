package Server;

import DAO.EcheanceDAO;
import DAO.PropositionStageDAO;
import DAO.RoleDAO;
import DAO.SuiviEcheanceDAO;
import DAO.relations.AssumerDAO;
import DAO.relations.ViserDAO;
import DAO.utils.DAOList;
import Server.Rights.IPosteur;
import Server.Rights.Role;

import java.util.List;

public class Etudiant implements IPosteur
{
    private final String mail; //ID
    private final String matricule;
    private final DAOList<PropositionStage> propositionsStage;
    private final DAOList<Echeance> echeances;
    private final DAOList<SuiviEcheance> suivisEcheances;
    private final DAOList<Role> roles;
    private String nom;
    private String prenom;
    private String motDePasse;
    private int annee;
    private boolean doublant;
    private final boolean dispenseTFE;
    private final boolean dispenseStage;
    private TFE tfe;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Etudiant(String mail, String motDePasse, String prenom, String nom, String matricule, int annee, boolean doublant, boolean dispenseTFE, boolean dispenseStage)
    {
        this.motDePasse = motDePasse;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.annee = annee;
        this.doublant = doublant;
        this.dispenseTFE = dispenseTFE;
        this.dispenseStage = dispenseStage;

        this.propositionsStage = new DAOList<>(PropositionStageDAO.getInstance());
        this.echeances = new DAOList<>(EcheanceDAO.getInstance());
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.roles = new DAOList<>(RoleDAO.getInstance());

        init();
    }

    private void init()
    {
        this.propositionsStage.setSource(() -> PropositionStageDAO.getInstance().findFromEtudiant(mail));
        this.echeances.setSource(() -> ViserDAO.getInstance().findEcheances(mail));
        this.suivisEcheances.setSource(() -> SuiviEcheanceDAO.getInstance().findFromEtudiant(mail));
        this.roles.setSource(() -> AssumerDAO.getInstance().findRoles(mail));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getMatricule()
    {
        return matricule;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public int getAnnee()
    {
        return annee;
    }

    public void setAnnee(int annee)
    {
        this.annee = annee;
    }

    public boolean isDoublant()
    {
        return doublant;
    }

    public void setDoublant(boolean doublant)
    {
        this.doublant = doublant;
    }

    public List<Role> getRoles()
    {
        return roles.get();
    }

    public boolean isDispenseStage()
    {
        return dispenseStage;
    }

    public boolean isDispenseTFE()
    {
        return dispenseTFE;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public TFE getTfe()
    {
        return tfe;
    }

    public boolean addPropositionStage(PropositionStage p)
    {
        return propositionsStage.add(p);
    }

    public boolean removePropositionStage(PropositionStage p)
    {
        return propositionsStage.remove(p);
    }

    public List<PropositionStage> getPropositionsStage()
    {
        return propositionsStage.get();
    }

    public boolean addEcheance(Echeance p)
    {
        return echeances.add(p);
    }

    public boolean removeEcheance(Echeance p)
    {
        return echeances.remove(p);
    }

    public List<Echeance> getEcheances()
    {
        return echeances.get();
    }

    public boolean addSuiviEcheance(SuiviEcheance p)
    {
        return suivisEcheances.add(p);
    }

    public boolean removeSuiviEcheance(SuiviEcheance p)
    {
        return suivisEcheances.remove(p);
    }

    public List<SuiviEcheance> getSuivisEcheances()
    {
        return suivisEcheances.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public String getID()
    {
        return this.mail;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////


    @Override
    public String toString()
    {
        return nom + " " + prenom;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Etudiant)) return false;

        Etudiant etudiant = (Etudiant) o;

        if (annee != etudiant.annee) return false;
        if (doublant != etudiant.doublant) return false;
        if (dispenseTFE != etudiant.dispenseTFE) return false;
        if (dispenseStage != etudiant.dispenseStage) return false;
        if (mail != null ? !mail.equals(etudiant.mail) : etudiant.mail != null) return false;
        if (matricule != null ? !matricule.equals(etudiant.matricule) : etudiant.matricule != null) return false;
        if (nom != null ? !nom.equals(etudiant.nom) : etudiant.nom != null) return false;
        if (prenom != null ? !prenom.equals(etudiant.prenom) : etudiant.prenom != null) return false;
        if (motDePasse != null ? !motDePasse.equals(etudiant.motDePasse) : etudiant.motDePasse != null) return false;
        if (tfe != null ? !tfe.equals(etudiant.tfe) : etudiant.tfe != null) return false;
        if (propositionsStage != null ? !propositionsStage.equals(etudiant.propositionsStage) : etudiant.propositionsStage != null)
            return false;
        if (echeances != null ? !echeances.equals(etudiant.echeances) : etudiant.echeances != null) return false;
        if (suivisEcheances != null ? !suivisEcheances.equals(etudiant.suivisEcheances) : etudiant.suivisEcheances != null)
            return false;
        return !(roles != null ? !roles.equals(etudiant.roles) : etudiant.roles != null);

    }

    @Override
    public int hashCode()
    {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (matricule != null ? matricule.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + annee;
        result = 31 * result + (doublant ? 1 : 0);
        result = 31 * result + (dispenseTFE ? 1 : 0);
        result = 31 * result + (dispenseStage ? 1 : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (propositionsStage != null ? propositionsStage.hashCode() : 0);
        result = 31 * result + (echeances != null ? echeances.hashCode() : 0);
        result = 31 * result + (suivisEcheances != null ? suivisEcheances.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
