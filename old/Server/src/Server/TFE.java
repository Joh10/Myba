package Server;

import DAO.*;
import DAO.relations.ImplementerDAO;
import DAO.relations.SurveillerDAO;
import DAO.utils.DAOList;

import java.util.List;
import java.util.stream.Collectors;

public class TFE
{
    private final int anneeAcadDebut;
    private final Etudiant etudiant;
    private final DAOList<Echeance> echeances;
    private final DAOList<Technologie> technologies;
    private final DAOList<Evaluation> evaluations;
    private final DAOList<Defense> defenses;
    private final DAOList<Professeur> promoteurs;
    private int id;
    private int anneeAcadFin;
    private String titre;
    private int totalTFE;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public TFE(int id, String titre, Etudiant etudiant, int anneeAcadDebut, int anneeAcadFin)
    {
        this.id = id;
        this.titre = titre;
        this.anneeAcadFin = anneeAcadFin;
        this.etudiant = etudiant;
        this.anneeAcadDebut = anneeAcadDebut;
        this.echeances = new DAOList<>(EcheanceDAO.getInstance());
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());
        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.promoteurs = new DAOList<>(ProfesseurDAO.getInstance());

        init();
    }

    public TFE(String titre, Etudiant etudiant, int anneeAcadDebut)
    {
        this.titre = titre;
        this.etudiant = etudiant;
        this.anneeAcadDebut = anneeAcadDebut;
        this.echeances = new DAOList<>(EcheanceDAO.getInstance());
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());
        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.promoteurs = new DAOList<>(ProfesseurDAO.getInstance());

        init();
    }

    private void init()
    {
        this.echeances.setSource(() -> EcheanceDAO.getInstance().findFromTFE(id));
        this.technologies.setSource(() -> ImplementerDAO.getInstance().findTechnologies(id));
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromTFE(id));
        this.defenses.setSource(() -> DefenseDAO.getInstance().findFromTFE(id));
        this.promoteurs.setSource(() -> SurveillerDAO.getInstance().findEvaluateurs(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public int getTotalTFE()
    {
        return totalTFE;
    }

    public void setTotalTFE(int totalTFE)
    {
        this.totalTFE = totalTFE;
    }

    public int getAnneeAcadDebut()
    {
        return anneeAcadDebut;
    }

    public int getAnneeAcadFin()
    {
        return anneeAcadFin;
    }

    public void setAnneeAcadFin(int anneeAcadFin)
    {
        this.anneeAcadFin = anneeAcadFin;
    }

    public Etudiant getEtudiant()
    {
        return etudiant;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

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

    public boolean addTechnologie(Technologie p)
    {
        return technologies.add(p);
    }

    public boolean removeTechnologie(Technologie p)
    {
        return technologies.remove(p);
    }

    public List<Technologie> getTechnologies()
    {
        return technologies.get();
    }

    public boolean addEvaluation(Evaluation p)
    {
        return evaluations.add(p);
    }

    public boolean removeEvaluation(Evaluation p)
    {
        return evaluations.remove(p);
    }

    public List<Evaluation> getEvaluations()
    {
        return evaluations.get();
    }

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

    public boolean addPromoteur(Professeur p)
    {
        return promoteurs.add(p);
    }

    public boolean removePromoteur(Professeur p)
    {
        return promoteurs.remove(p);
    }

    public List<Professeur> getPromoteur()
    {
        return promoteurs.get().stream().map(e -> e).collect(Collectors.toList());
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof TFE)) return false;

        TFE tfe = (TFE) o;

        if (anneeAcadDebut != tfe.anneeAcadDebut) return false;
        if (id != tfe.id) return false;
        if (anneeAcadFin != tfe.anneeAcadFin) return false;
        if (totalTFE != tfe.totalTFE) return false;
        if (etudiant != null ? !etudiant.equals(tfe.etudiant) : tfe.etudiant != null) return false;
        if (echeances != null ? !echeances.equals(tfe.echeances) : tfe.echeances != null) return false;
        if (technologies != null ? !technologies.equals(tfe.technologies) : tfe.technologies != null) return false;
        if (evaluations != null ? !evaluations.equals(tfe.evaluations) : tfe.evaluations != null) return false;
        if (defenses != null ? !defenses.equals(tfe.defenses) : tfe.defenses != null) return false;
        if (promoteurs != null ? !promoteurs.equals(tfe.promoteurs) : tfe.promoteurs != null) return false;
        return !(titre != null ? !titre.equals(tfe.titre) : tfe.titre != null);

    }

    @Override
    public int hashCode()
    {
        int result = anneeAcadDebut;
        result = 31 * result + (etudiant != null ? etudiant.hashCode() : 0);
        result = 31 * result + (echeances != null ? echeances.hashCode() : 0);
        result = 31 * result + (technologies != null ? technologies.hashCode() : 0);
        result = 31 * result + (evaluations != null ? evaluations.hashCode() : 0);
        result = 31 * result + (defenses != null ? defenses.hashCode() : 0);
        result = 31 * result + (promoteurs != null ? promoteurs.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + anneeAcadFin;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + totalTFE;
        return result;
    }
}
