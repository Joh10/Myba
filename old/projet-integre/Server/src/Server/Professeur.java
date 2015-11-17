package Server;

import DAO.*;
import DAO.relations.CorrigerDAO;
import DAO.relations.SurveillerDAO;
import DAO.utils.DAOList;
import Server.Rights.IPosteur;
import Server.Rights.Role;

import java.util.List;

public class Professeur extends Evaluateur implements IPosteur
{
    private final DAOList<Stage> stages;
    private final DAOList<TFE> tfes;
    private final DAOList<Evaluation> evaluations;
    private final DAOList<PropositionStage> propositionsStage;
    private final DAOList<SuiviEcheance> suivisEcheances;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Professeur(String mail, String motDePasse, String prenom, String nom)
    {
        super(mail, motDePasse, prenom, nom);

        this.stages = new DAOList<>(StageDAO.getInstance());
        this.stages.setSource(() -> StageDAO.getInstance().findFromProfesseur(mail));
        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromEvaluateur(mail));
        this.propositionsStage = new DAOList<>(PropositionStageDAO.getInstance());
        this.propositionsStage.setSource(() -> PropositionStageDAO.getInstance().findFromEvaluateur(mail));
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.suivisEcheances.setSource(() -> CorrigerDAO.getInstance().findSuivis(mail));
        this.tfes = new DAOList<>(TFEDAO.getInstance());
        this.tfes.setSource(() -> SurveillerDAO.getInstance().findTFEs(mail));
    }

    public Professeur(String mail, String motDePasse, String prenom, String nom, List<Role> r)
    {
        super(mail, motDePasse, prenom, nom, r);

        this.stages = new DAOList<>(StageDAO.getInstance());
        this.stages.setSource(() -> StageDAO.getInstance().findFromProfesseur(mail));
        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromEvaluateur(mail));
        this.propositionsStage = new DAOList<>(PropositionStageDAO.getInstance());
        this.propositionsStage.setSource(() -> PropositionStageDAO.getInstance().findFromEvaluateur(mail));
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.suivisEcheances.setSource(() -> CorrigerDAO.getInstance().findSuivis(mail));
        this.tfes = new DAOList<>(TFEDAO.getInstance());
        this.tfes.setSource(() -> SurveillerDAO.getInstance().findTFEs(mail));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addStage(Stage s)
    {
        return stages.add(s);
    }

    public boolean removeStage(Stage s)
    {
        return stages.remove(s);
    }

    public List<Stage> getStages()
    {
        return stages.get();
    }

    public boolean addEvaluation(Evaluation s)
    {
        return evaluations.add(s);
    }

    public boolean removeEvaluation(Evaluation s)
    {
        return evaluations.remove(s);
    }

    public List<Evaluation> getEvaluations()
    {
        return evaluations.get();
    }

    public boolean addPropositionStage(PropositionStage s)
    {
        return propositionsStage.add(s);
    }

    public boolean removePropositionStage(PropositionStage s)
    {
        return propositionsStage.remove(s);
    }

    public List<PropositionStage> getPropositionsStage()
    {
        return propositionsStage.get();
    }

    public boolean addSuiviEcheance(SuiviEcheance s)
    {
        return suivisEcheances.add(s);
    }

    public boolean removeSuiviEcheance(SuiviEcheance s)
    {
        return suivisEcheances.remove(s);
    }

    public List<SuiviEcheance> getSuivisEcheances()
    {
        return suivisEcheances.get();
    }

    public boolean addTFE(TFE s)
    {
        return tfes.add(s);
    }

    public boolean removeTFE(TFE s)
    {
        return tfes.remove(s);
    }

    public List<TFE> getTFE()
    {
        return tfes.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public String getID()
    {
        return mail;
    }

    @Override
    public String toString()
    {
        return nom + " " + prenom;
    }
}
