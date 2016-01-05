package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TECHNOLOGIE")
public class Technologie implements Serializable
{
    private static final long serialVersionUID = 3824815621140749028L;

    @Id
    @Column(name = "ID_TEC")
    private int id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VERSION")
    private String version;

    /**
     * Constructeur
     *
     * @param _id      ID (identifiant) de la technologie
     * @param _nom     Le nom de la technologie
     * @param _version La version de la technologie
     */
    public Technologie(int _id, String _nom, String _version)
    {
        id = _id;
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

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        if (version.length() > 0) return nom + " (" + version + ")";
        else return nom;
    }
}