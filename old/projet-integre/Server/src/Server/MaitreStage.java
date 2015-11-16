package Server;

import DAO.EvaluationDAO;
import DAO.StageDAO;
import DAO.utils.DAOList;
import Server.Rights.Role;

import java.util.List;

public class MaitreStage extends Evaluateur
{
    private final DAOList<Stage> stages;
    private final DAOList<Evaluation> evaluations;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public MaitreStage(String mail, String motDePasse, String prenom, String nom)
    {
        super(mail, motDePasse, prenom, nom);

        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromEvaluateur(mail));
        this.stages = new DAOList<>(StageDAO.getInstance());
        this.stages.setSource(() -> StageDAO.getInstance().findFromMaitreStage(mail));
    }

    public MaitreStage(String mail, String motDePasse, String prenom, String nom, List<Role> r)
    {
        super(mail, motDePasse, prenom, nom, r);

        this.evaluations = new DAOList<>(EvaluationDAO.getInstance());
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromEvaluateur(mail));
        this.stages = new DAOList<>(StageDAO.getInstance());
        this.stages.setSource(() -> StageDAO.getInstance().findFromMaitreStage(mail));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addStage(Stage stage)
    {
        return this.stages.add(stage);
    }

    public boolean removeStage(Stage stage)
    {
        return this.stages.remove(stage);
    }

    public List<Stage> getStages()
    {
        return stages.get();
    }

    public boolean addEvaluation(Evaluation evaluation)
    {
        return this.evaluations.add(evaluation);
    }

    public boolean removeEvaluation(Evaluation evaluation)
    {
        return this.evaluations.remove(evaluation);
    }

    public List<Evaluation> getEvaluations()
    {
        return evaluations.get();
    }


    @Override
    public String toString()
    {
        return nom + " " + prenom;
    }
}
