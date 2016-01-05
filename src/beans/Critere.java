package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CRITERE")
public class Critere implements Serializable
{
    private static final long serialVersionUID = 2079767621417345684L;

    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_CRI")
    private Integer id = null;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "NOTEMAX")
    private int noteMax;

    /**
     * Constructeur
     * @param _nom     Nom du critère d'évaluation
     * @param _type    Type du critère (enum "tfe", "stage" or "defense")
     * @param _noteMax Note maximale de ce critère d'évaluation
     * @throws IllegalArgumentException si le type n'est ni un stage, ni un tfe, ni une défense.
     */
    public Critere(String _nom, String _type, int _noteMax)
    {
        if (!_type.equals("tfe") && !_type.equals("stage") && !_type.equals("defense"))
            throw new IllegalArgumentException();
        nom = _nom;
        type = _type;
        noteMax = _noteMax;
    }

    public Critere()
    {
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
        return !(nom != null ? !nom.equals(critere.nom) : critere.nom != null);

    }

    public String toString()
    {
        return nom + " (" + noteMax + ")";
    }
}