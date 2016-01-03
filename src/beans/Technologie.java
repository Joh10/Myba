package beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class Technologie implements Serializable
{
    /*
     * Sérializable nécessaire pour les HashMap (TwinColSelect)
     */
    private static final long serialVersionUID = 3824815621140749028L;

    @Id
    @Column(name = "id_Tech")
    private int id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "version")
    private String version;

    /**
     * Constructeur
     *
     * @param    _id            ID (identifiant) de la technologie
     * @param    _nom        Le nom de la technologie
     * @param    _version    La version de la technologie
     */
    public Technologie(int _id, String _nom, String _version)
    {
        id = _id;
        nom = _nom;
        version = _version;
    }

    /**
     * Pré	:	_nom, _version sont initialisés<br>
     * Post	:	le nom et la version de la technologie sont mises à jour.
     *
     * @param    _nom        Le nom de la technologie
     * @param    _version    La version de la technologie
     */
    public void update(String _nom, String _version)
    {
        nom = _nom;
        version = _version;
    }

    /**
     * @return l'identifiant de la technologie
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'ID de la technologie est modifié par _id
     *
     * @param    _id    L'identifiant de la technologie
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return le nom de la technologie
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return la version de la technologie
     */
    public String getVersion()
    {
        return version;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        if (version.length() > 0) return nom + " (" + version + ")";
        else return nom;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Technologie other = (Technologie) obj;
        if (id != other.id) return false;
        if (nom == null)
        {
            if (other.nom != null) return false;
        } else if (!nom.equals(other.nom)) return false;
        if (version == null)
        {
            if (other.version != null) return false;
        } else if (!version.equals(other.version)) return false;
        return true;
    }
}