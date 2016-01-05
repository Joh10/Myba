package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TECHNOLOGIE")
public class Technologie implements Serializable
{
    private static final long serialVersionUID = 3824815621140749028L;

    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_TEC")
    private Integer id=null;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VERSION")
    private String version;

    /**
     * Constructeur
     *
     * @param _nom     Le nom de la technologie
     * @param _version La version de la technologie
     */
    public Technologie(String _nom, String _version)
    {
        nom = _nom;
        version = _version;
    }

    public Technologie()
    {
    }

    public void update(String _nom, String _version)
    {
        nom = _nom;
        version = _version;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getVersion()
    {
        return version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Technologie)) return false;

        Technologie that = (Technologie) o;

        if (id != that.id) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        return !(version != null ? !version.equals(that.version) : that.version != null);

    }

    public String toString()
    {
        if (version.length() > 0) return nom + " (" + version + ")";
        else return nom;
    }
}