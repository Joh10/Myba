package Server;

import DAO.DefenseDAO;
import DAO.EcheanceDAO;
import DAO.EvaluationDAO;
import DAO.utils.DAOList;

import java.util.Date;
import java.util.List;

public class Stage
{
    private final MaitreStage maitreStage;
    private final PropositionStage propositionStage;
    private final Etudiant etudiant;
    private final Professeur promoteur;
    private final DAOList<Evaluation> evaluations;
    private final DAOList<Defense> defenses;
    private final DAOList<Echeance> echeances;
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private int totalStage;
    private String commentaire;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Stage(int id, Date dateDebut, Date dateFin, PropositionStage propositionStage, Etudiant etudiant, MaitreStage maitreStage, Professeur promoteur)
    {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.maitreStage = maitreStage;
        this.propositionStage = propositionStage;
        this.etudiant = etudiant;
        this.promoteur = promoteur;

        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.echeances = new DAOList<>(EcheanceDAO.getInstance());

        init();
    }

    public Stage(Date dateDebut, Date dateFin, PropositionStage propositionStage, Etudiant etudiant, MaitreStage maitreStage, Professeur promoteur)
    {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.maitreStage = maitreStage;
        this.propositionStage = propositionStage;
        this.etudiant = etudiant;
        this.promoteur = promoteur;

        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.defenses = new DAOList<>(DefenseDAO.getInstance());
        this.echeances = new DAOList<>(EcheanceDAO.getInstance());

        init();
    }

    private void init()
    {
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromStage(id));
        this.defenses.setSource(() -> DefenseDAO.getInstance().findFromStage(id));
        this.echeances.setSource(() -> EcheanceDAO.getInstance().findFromStage(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public Date getDateDebut()
    {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }

    public int getTotalStage()
    {
        return totalStage;
    }

    public void setTotalStage(int totalStage)
    {
        this.totalStage = totalStage;
    }

    public String getCommentaire()
    {
        return commentaire;
    }

    public void setCommentaire(String commentaire)
    {
        this.commentaire = commentaire;
    }

    public MaitreStage getMaitreStage()
    {
        return maitreStage;
    }

    public PropositionStage getPropositionStage()
    {
        return propositionStage;
    }

    public Etudiant getEtudiant()
    {
        return etudiant;
    }

    public Professeur getPromoteur()
    {
        return promoteur;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addEvaluation(Evaluation e)
    {
        return evaluations.add(e);
    }

    public boolean removeEvaluation(Evaluation e)
    {
        return evaluations.remove(e);
    }

    public List<Evaluation> getEvaluations()
    {
        return evaluations.get();
    }

    public boolean addEcheance(Echeance e)
    {
        return echeances.add(e);
    }

    public boolean removeEcheance(Echeance e)
    {
        return echeances.remove(e);
    }

    public List<Echeance> getEcheances()
    {
        return echeances.get();
    }

    public boolean addDefense(Defense e)
    {
        return defenses.add(e);
    }

    public boolean removeDefense(Defense e)
    {
        return defenses.remove(e);
    }

    public List<Defense> getDefenses()
    {
        return defenses.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Stage)) return false;

        Stage stage = (Stage) o;

        if (id != stage.id) return false;
        if (totalStage != stage.totalStage) return false;
        if (dateDebut != null ? !dateDebut.equals(stage.dateDebut) : stage.dateDebut != null) return false;
        if (dateFin != null ? !dateFin.equals(stage.dateFin) : stage.dateFin != null) return false;
        if (commentaire != null ? !commentaire.equals(stage.commentaire) : stage.commentaire != null) return false;
        if (maitreStage != null ? !maitreStage.equals(stage.maitreStage) : stage.maitreStage != null) return false;
        if (propositionStage != null ? !propositionStage.equals(stage.propositionStage) : stage.propositionStage != null)
            return false;
        if (etudiant != null ? !etudiant.equals(stage.etudiant) : stage.etudiant != null) return false;
        if (promoteur != null ? !promoteur.equals(stage.promoteur) : stage.promoteur != null) return false;
        if (evaluations != null ? !evaluations.equals(stage.evaluations) : stage.evaluations != null) return false;
        if (defenses != null ? !defenses.equals(stage.defenses) : stage.defenses != null) return false;
        return !(echeances != null ? !echeances.equals(stage.echeances) : stage.echeances != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (dateDebut != null ? dateDebut.hashCode() : 0);
        result = 31 * result + (dateFin != null ? dateFin.hashCode() : 0);
        result = 31 * result + totalStage;
        result = 31 * result + (commentaire != null ? commentaire.hashCode() : 0);
        result = 31 * result + (maitreStage != null ? maitreStage.hashCode() : 0);
        result = 31 * result + (propositionStage != null ? propositionStage.hashCode() : 0);
        result = 31 * result + (etudiant != null ? etudiant.hashCode() : 0);
        result = 31 * result + (promoteur != null ? promoteur.hashCode() : 0);
        result = 31 * result + (evaluations != null ? evaluations.hashCode() : 0);
        result = 31 * result + (defenses != null ? defenses.hashCode() : 0);
        result = 31 * result + (echeances != null ? echeances.hashCode() : 0);
        return result;
    }
}
