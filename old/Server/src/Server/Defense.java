package Server;

import DAO.EvaluationDAO;
import DAO.utils.DAOList;

import java.util.Date;
import java.util.List;

public class Defense
{
    private final PresidentJury president;
    private final DAOList<Evaluation> evaluations = new DAOList<>(EvaluationDAO.getInstance());
    private int id;
    private Date dateHeure;
    private String local;
    private Stage stage;
    private TFE tfe;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Defense(int id, Date dateHeure, String local, Stage s, PresidentJury president)
    {
        this.president = president;
        this.id = id;
        this.dateHeure = dateHeure;
        this.local = local;
        this.stage = s;

        init();
    }

    public Defense(int id, Date dateHeure, String local, TFE f, PresidentJury president)
    {
        this.president = president;
        this.id = id;
        this.dateHeure = dateHeure;
        this.local = local;
        this.tfe = f;

        init();
    }

    public Defense(Date dateHeure, String local, Stage s, PresidentJury president)
    {
        this.president = president;
        this.dateHeure = dateHeure;
        this.local = local;
        this.stage = s;

        init();
    }

    public Defense(Date dateHeure, String local, TFE f, PresidentJury president)
    {
        this.president = president;
        this.dateHeure = dateHeure;
        this.local = local;
        this.tfe = f;

        init();
    }

    private void init()
    {
        this.evaluations.setSource(() -> EvaluationDAO.getInstance().findFromDefense(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public PresidentJury getPresident()
    {
        return president;
    }

    public Date getDateHeure()
    {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure)
    {
        this.dateHeure = dateHeure;
    }

    public int getId()
    {
        return id;
    }

    public String getLocal()
    {
        return local;
    }

    public void setLocal(String local)
    {
        this.local = local;
    }

    public TFE getTfe()
    {
        return tfe;
    }

    public Stage getStage()
    {
        return stage;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public void addEvaluation(Evaluation e)
    {
        this.evaluations.add(e);
    }

    public void removeEvaluation(Evaluation e)
    {
        this.evaluations.remove(e);
    }

    public List<Evaluation> getEvaluations()
    {
        return evaluations.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Defense)) return false;

        Defense defense = (Defense) o;

        if (id != defense.id) return false;
        if (dateHeure != null ? !dateHeure.equals(defense.dateHeure) : defense.dateHeure != null) return false;
        if (local != null ? !local.equals(defense.local) : defense.local != null) return false;
        if (president != null ? !president.equals(defense.president) : defense.president != null) return false;
        if (stage != null ? !stage.equals(defense.stage) : defense.stage != null) return false;
        if (tfe != null ? !tfe.equals(defense.tfe) : defense.tfe != null) return false;
        return !(evaluations != null ? !evaluations.equals(defense.evaluations) : defense.evaluations != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (dateHeure != null ? dateHeure.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        result = 31 * result + (president != null ? president.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (evaluations != null ? evaluations.hashCode() : 0);
        return result;
    }
}
