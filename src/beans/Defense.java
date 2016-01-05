package beans;

import java.util.Date;
import java.util.List;

public class Defense
{
    private int id;
    private Utilisateur presidentJury;
    private List<Evaluation> evaluation;
    private Stage stage;
    private TFE tfe;
    private Date date;
    private String local;

    /**
     * Constructeur (type TFE)
     *
     * @param _id            ID (identifiant) de la défense
     * @param _presidentJury Le président du jury qui est assigné à cette défense
     * @param _tfe           Le TFE dont est objet de cette défense
     * @param _date          La date à laquelle se déroule cette défense
     * @param _local         Le nom du local ou est organisée cette défense
     */
    public Defense(int _id, Utilisateur _presidentJury, TFE _tfe, Date _date, String _local)
    {
        id = _id;
        presidentJury = _presidentJury;
        stage = null;
        tfe = _tfe;
        date = _date;
        local = _local;
    }

    /**
     * Constructeur (type stage)
     *
     * @param _id            ID (identifiant) de la défense
     * @param _presidentJury Le président du jury qui est assigné à cette défense
     * @param _stage         Le stage dont est objet de cette défense
     * @param _date          La date à laquelle se déroule cette défense
     * @param _local         Le nom du local ou est organisée cette défense
     */
    public Defense(int _id, Utilisateur _presidentJury, Stage _stage, Date _date, String _local)
    {
        id = _id;
        presidentJury = _presidentJury;
        stage = _stage;
        tfe = null;
        date = _date;
        local = _local;
    }

    public void update(Date _date, String _local)
    {
        date = _date;
        local = _local;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Utilisateur getPresidentJury()
    {
        return presidentJury;
    }

    public void setPresidentJury(Utilisateur presidentJury)
    {
        this.presidentJury = presidentJury;
    }

    public List<Evaluation> getEvaluation()
    {
        return evaluation;
    }

    public void setEvaluation(List<Evaluation> evaluation)
    {
        this.evaluation = evaluation;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public TFE getTfe()
    {
        return tfe;
    }

    public void setTfe(TFE tfe)
    {
        this.tfe = tfe;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getLocal()
    {
        return local;
    }

    public void setLocal(String local)
    {
        this.local = local;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Defense)) return false;

        Defense defense = (Defense) o;

        if (id != defense.id) return false;
        if (presidentJury != null ? !presidentJury.equals(defense.presidentJury) : defense.presidentJury != null)
            return false;
        if (evaluation != null ? !evaluation.equals(defense.evaluation) : defense.evaluation != null) return false;
        if (stage != null ? !stage.equals(defense.stage) : defense.stage != null) return false;
        if (tfe != null ? !tfe.equals(defense.tfe) : defense.tfe != null) return false;
        if (date != null ? !date.equals(defense.date) : defense.date != null) return false;
        return !(local != null ? !local.equals(defense.local) : defense.local != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (presidentJury != null ? presidentJury.hashCode() : 0);
        result = 31 * result + (evaluation != null ? evaluation.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (local != null ? local.hashCode() : 0);
        return result;
    }
}