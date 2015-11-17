package Server;

import DAO.ProfesseurDAO;
import DAO.relations.CorrigerDAO;
import DAO.utils.DAOList;

import java.util.Date;
import java.util.List;

public class SuiviEcheance
{
    private final Date dateRemise;
    private final boolean valide;
    private final Etudiant etudiant;
    private final Echeance echeance;
    private final DAOList<Professeur> evaluateur;
    private int id;
    private String cheminDocEtudiant;
    private String cheminDocCorrection;
    private String correction;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public SuiviEcheance(int id, Etudiant etudiant, Echeance echeance, String cheminDocEtudiant, Date dateetheureremise, String correction, String documentcorrection, boolean valide)
    {
        this.evaluateur = new DAOList<>(ProfesseurDAO.getInstance());
        this.id = id;
        this.etudiant = etudiant;
        this.echeance = echeance;
        this.cheminDocEtudiant = cheminDocEtudiant;
        this.valide = valide;
        this.correction = correction;
        this.cheminDocCorrection = documentcorrection;
        this.dateRemise = dateetheureremise;
    }

    public SuiviEcheance(int id, Etudiant etudiant, Echeance echeance, String cheminDocEtudiant)
    {
        this.evaluateur = new DAOList<>(ProfesseurDAO.getInstance());
        this.id = id;
        this.etudiant = etudiant;
        this.echeance = echeance;
        this.cheminDocEtudiant = cheminDocEtudiant;
        this.valide = new Date().getTime() <= echeance.getDate().getTime();
        this.dateRemise = new Date();
    }


    public SuiviEcheance(Etudiant etudiant, Echeance echeance, String cheminDocEtudiant)
    {
        this.evaluateur = new DAOList<>(ProfesseurDAO.getInstance());
        this.etudiant = etudiant;
        this.echeance = echeance;
        this.cheminDocEtudiant = cheminDocEtudiant;
        this.valide = new Date().getTime() <= echeance.getDate().getTime();
        this.dateRemise = new Date();
    }

    private void init()
    {
        this.evaluateur.setSource(() -> CorrigerDAO.getInstance().findEvaluateurs(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public Date getDateRemise()
    {
        return dateRemise;
    }

    public boolean isValide()
    {
        return valide;
    }

    public String getCorrection()
    {
        return correction;
    }

    public void setCorrection(String correction)
    {
        this.correction = correction;
    }

    public String getCheminDocEtudiant()
    {
        return cheminDocEtudiant;
    }

    public void setCheminDocEtudiant(String cheminDocEtudiant)
    {
        this.cheminDocEtudiant = cheminDocEtudiant;
    }

    public String getCheminDocCorrection()
    {
        return cheminDocCorrection;
    }

    public void setCheminDocCorrection(String cheminDocCorrection)
    {
        this.cheminDocCorrection = cheminDocCorrection;
    }

    public Etudiant getEtudiant()
    {
        return etudiant;
    }

    public Echeance getEcheance()
    {
        return echeance;
    }

    public List<Professeur> getEvaluateur()
    {
        return evaluateur.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof SuiviEcheance)) return false;

        SuiviEcheance that = (SuiviEcheance) o;

        if (id != that.id) return false;
        if (valide != that.valide) return false;
        if (dateRemise != null ? !dateRemise.equals(that.dateRemise) : that.dateRemise != null) return false;
        if (cheminDocEtudiant != null ? !cheminDocEtudiant.equals(that.cheminDocEtudiant) : that.cheminDocEtudiant != null)
            return false;
        if (cheminDocCorrection != null ? !cheminDocCorrection.equals(that.cheminDocCorrection) : that.cheminDocCorrection != null)
            return false;
        if (etudiant != null ? !etudiant.equals(that.etudiant) : that.etudiant != null) return false;
        if (echeance != null ? !echeance.equals(that.echeance) : that.echeance != null) return false;
        if (evaluateur != null ? !evaluateur.equals(that.evaluateur) : that.evaluateur != null) return false;
        return !(correction != null ? !correction.equals(that.correction) : that.correction != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (dateRemise != null ? dateRemise.hashCode() : 0);
        result = 31 * result + (valide ? 1 : 0);
        result = 31 * result + (cheminDocEtudiant != null ? cheminDocEtudiant.hashCode() : 0);
        result = 31 * result + (cheminDocCorrection != null ? cheminDocCorrection.hashCode() : 0);
        result = 31 * result + (etudiant != null ? etudiant.hashCode() : 0);
        result = 31 * result + (echeance != null ? echeance.hashCode() : 0);
        result = 31 * result + (evaluateur != null ? evaluateur.hashCode() : 0);
        result = 31 * result + (correction != null ? correction.hashCode() : 0);
        return result;
    }
}