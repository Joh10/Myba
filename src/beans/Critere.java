package beans;

import java.io.Serializable;
import java.util.List;

public class Critere implements Serializable
{
    private static final long serialVersionUID = 2079767621417345684L;

    private int id;
    private String type;
    private String nom;
    private int noteMax;
    private List<Evaluation> evaluation;

    /**
     * Constructeur
     *
     * @param _id      ID (identifiant) du critère d'évaluation
     * @param _nom     Nom du critère d'évaluation
     * @param _type    Type du critère (enum "tfe", "stage" or "defense")
     * @param _noteMax Note maximale de ce critère d'évaluation
     * @throws IllegalArgumentException si le type n'est ni un stage, ni un tfe, ni une défense.
     */
    public Critere(int _id, String _nom, String _type, int _noteMax)
    {
        if (!_type.equals("tfe") && !_type.equals("stage") && !_type.equals("defense"))
            throw new IllegalArgumentException();
        id = _id;
        nom = _nom;
        type = _type;
        noteMax = _noteMax;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public int getNoteMax()
    {
        return noteMax;
    }

    public void setNoteMax(int noteMax)
    {
        this.noteMax = noteMax;
    }

    public List<Evaluation> getEvaluation()
    {
        return evaluation;
    }

    public void setEvaluation(List<Evaluation> evaluation)
    {
        this.evaluation = evaluation;
    }

    public void update(String _nom, String _type, int _noteMax)
    {
        if (!_type.equals("tfe") && !_type.equals("stage") && !_type.equals("defense"))
            throw new IllegalArgumentException();
        nom = _nom;
        type = _type;
        noteMax = _noteMax;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Critere)) return false;

        Critere critere = (Critere) o;

        if (id != critere.id) return false;
        if (noteMax != critere.noteMax) return false;
        if (type != null ? !type.equals(critere.type) : critere.type != null) return false;
        if (nom != null ? !nom.equals(critere.nom) : critere.nom != null) return false;
        return !(evaluation != null ? !evaluation.equals(critere.evaluation) : critere.evaluation != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + noteMax;
        result = 31 * result + (evaluation != null ? evaluation.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return nom + " (" + noteMax + ")";
    }
}