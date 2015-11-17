package Server;

public class Critere
{
    private int id;
    private String nom;
    private String type;
    private int noteMax;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Critere(int id, String nom, String type, int noteMax)
    {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.noteMax = noteMax;
    }

    public Critere(String nom, String type, int noteMax)
    {
        this.nom = nom;
        this.type = type;
        this.noteMax = noteMax;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getNoteMax()
    {
        return noteMax;
    }

    public void setNoteMax(int noteMax)
    {
        this.noteMax = noteMax;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Critere)) return false;

        Critere critere = (Critere) o;

        if (id != critere.id) return false;
        if (noteMax != critere.noteMax) return false;
        if (nom != null ? !nom.equals(critere.nom) : critere.nom != null) return false;
        return !(type != null ? !type.equals(critere.type) : critere.type != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + noteMax;
        return result;
    }
}
