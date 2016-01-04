package beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LIEUSTAGE")
public class LieuStage implements Serializable
{
    private static final long serialVersionUID = -4714026859138312539L;

    @Id
    @Column(name = "id_Lie")
    private int id;

    @OneToOne
    @JoinColumn(name="REF_UTILISATEUR")
    private Utilisateur owner;

    @Column(name = "entreprise")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "personneContact")
    private String personneContact;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    /**
     * Constructeur
     *
     * @param    _id            ID (identifiant) du lieu de stage (entreprise)
     * @param    _owner        L'utilisateur (étudiant/professeur) qui a ajouté ce lieu de stage (entreprise)
     * @param    _nom        Le nom  (dénomination sociale) de l'entreprise
     * @param    _adresse    L'adresse postale (siège social) de l'entreprise
     * @param    _pContact    Le nom et/ou prénom de la personne de contact
     * @param    _telephone    Le numéro de téléphone de la personne de contact
     * @param    _email        L'adresse courriel de la personne de contact ou de l'entreprise
     */
    public LieuStage(int _id, Utilisateur _owner, String _nom, String _adresse, String _pContact, String _telephone, String _email)
    {
        id = _id;
        owner = _owner;
        nom = _nom;
        adresse = _adresse;
        personneContact = _pContact;
        telephone = _telephone;
        email = _email;
    }

    public LieuStage()
    {
    }

    /**
     * Pré	:	_nom, _adresse, _pContact, _telephone sont initialisés<br>
     * Post	:	les informations du lieu de stage sont mises à jour.
     *
     * @param    _nom        Le nom  (dénomination sociale) de l'entreprise
     * @param    _adresse    L'adresse postale (siège social) de l'entreprise
     * @param    _pContact    Le nom et/ou prénom de la personne de contact
     * @param    _telephone    Le numéro de téléphone de la personne de contact
     * @param    _email        L'adresse courriel de la personne de contact ou de l'entreprise
     */
    public void update(String _nom, String _adresse, String _pContact, String _telephone, String _email)
    {
        nom = _nom;
        adresse = _adresse;
        personneContact = _pContact;
        telephone = _telephone;
        email = _email;
    }

    /**
     * @return l'identifiant du lieu de stage
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'ID du lieu de stage est modifié par _id
     *
     * @param    _id    L'identifiant du lieu de stage
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return l'utilisateur (professeur/étudiant) qui a ajouté l'entreprise.
     */
    public Utilisateur getOwner()
    {
        return owner;
    }

    /**
     * @return le nom (dénomination sociale) de l'entreprise.
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return l'adresse e-mail de l'entreprise (ou la personne de contact).
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return l'adresse postale (siège social) de l'entreprise.
     */
    public String getAdresse()
    {
        return adresse;
    }

    /**
     * @return le nom/prénom de la personne de contact.
     */
    public String getContact()
    {
        return personneContact;
    }

    /**
     * @return le numéro de téléphone de la personne de contact.
     */
    public String getTelephone()
    {
        return telephone;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + id;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((personneContact == null) ? 0 : personneContact.hashCode());
        result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        LieuStage other = (LieuStage) obj;
        if (adresse == null)
        {
            if (other.adresse != null) return false;
        } else if (!adresse.equals(other.adresse)) return false;
        if (email == null)
        {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (id != other.id) return false;
        if (nom == null)
        {
            if (other.nom != null) return false;
        } else if (!nom.equals(other.nom)) return false;
        if (personneContact == null)
        {
            if (other.personneContact != null) return false;
        } else if (!personneContact.equals(other.personneContact)) return false;
        if (telephone == null)
        {
            if (other.telephone != null) return false;
        } else if (!telephone.equals(other.telephone)) return false;
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return nom;
    }
}