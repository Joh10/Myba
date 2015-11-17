package Server;

import java.util.Date;


public class Evaluation
{
    private final Date date;
    private final Critere critere;
    private int id;
    private int note;
    private String commentaire;
    private TFE tfe;
    private Stage stage;
    private final Evaluateur evaluateur;
    private Defense defense;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Evaluation(int id, int note, String commentaire, Date date, Critere critere, TFE tfe, Evaluateur evaluateur)
    {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.critere = critere;
        this.date = date;
        this.tfe = tfe;
        this.evaluateur = evaluateur;
    }

    public Evaluation(int id, int note, String commentaire, Date date, Critere critere, Stage stage, Evaluateur evaluateur)
    {
        this.id = id;
        this.note = note;
        this.commentaire = commentaire;
        this.critere = critere;
        this.date = date;
        this.stage = stage;
        this.evaluateur = evaluateur;
    }

    public Evaluation(int note, String commentaire, Date date, Critere critere, TFE tfe, Evaluateur evaluateur)
    {
        this.note = note;
        this.commentaire = commentaire;
        this.critere = critere;
        this.date = date;
        this.tfe = tfe;
        this.evaluateur = evaluateur;
    }

    public Evaluation(int note, String commentaire, Date date, Critere critere, Stage stage, Evaluateur evaluateur)
    {
        this.note = note;
        this.commentaire = commentaire;
        this.critere = critere;
        this.date = date;
        this.stage = stage;
        this.evaluateur = evaluateur;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public Critere getCritere()
    {
        return critere;
    }

    public int getNote()
    {
        return note;
    }

    public void setNote(int note)
    {
        this.note = note;
    }

    public Date getDate()
    {
        return date;
    }

    public String getCommentaire()
    {
        return commentaire;
    }

    public void setCommentaire(String commentaire)
    {
        this.commentaire = commentaire;
    }

    public Stage getStage()
    {
        return stage;
    }

    public TFE getTfe()
    {
        return tfe;
    }

    public Defense getDefense()
    {
        return defense;
    }

    public void setDefense(Defense d)
    {
        this.defense = d;
    }

    public Evaluateur getEvaluateur()
    {
        return evaluateur;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;

        Evaluation that = (Evaluation) o;

        if (id != that.id) return false;
        if (note != that.note) return false;
        if (critere != null ? !critere.equals(that.critere) : that.critere != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (tfe != null ? !tfe.equals(that.tfe) : that.tfe != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        return !(commentaire != null ? !commentaire.equals(that.commentaire) : that.commentaire != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (critere != null ? critere.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + note;
        result = 31 * result + (commentaire != null ? commentaire.hashCode() : 0);
        return result;
    }
}
