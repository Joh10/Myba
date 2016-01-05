package beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "LIEUSTAGE")
public class LieuStage implements Serializable
{
    private static final long serialVersionUID = -4714026859138312539L;

    @Id
    @Column(name = "ID_LIE")
    private int id;

    @Column(name = "ENTREPRISE")
    private String entreprise;

    @Column(name = "ADRESSE")
    private String adresse;

    @Column(name = "PERSONNECONTACT")
    private String personneContact;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_UTILISATEUR")
    private Utilisateur owner;

    /**
     * Constructeur
     *
     * @param _id        ID (identifiant) du lieu de stage (entreprise)
     * @param _owner     L'utilisateur (étudiant/professeur) qui a ajouté ce lieu de stage (entreprise)
     * @param _nom       Le nom  (dénomination sociale) de l'entreprise
     * @param _adresse   L'adresse postale (siège social) de l'entreprise
     * @param _pContact  Le nom et/ou prénom de la personne de contact
     * @param _telephone Le numéro de téléphone de la personne de contact
     * @param _email     L'adresse courriel de la personne de contact ou de l'entreprise
     */
    public LieuStage(int _id, Utilisateur _owner, String _nom, String _adresse, String _pContact, String _telephone, String _email)
    {
        id = _id;
        owner = _owner;
        entreprise = _nom;
        adresse = _adresse;
        personneContact = _pContact;
        telephone = _telephone;
        email = _email;
    }

    public LieuStage()
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

    public String getEntreprise()
    {
        return entreprise;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public String getPersonneContact()
    {
        return personneContact;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public String getEmail()
    {
        return email;
    }

    public Utilisateur getOwner()
    {
        return owner;
    }


    public void update(String _nom, String _adresse, String _pContact, String _telephone, String _email)
    {
        entreprise = _nom;
        adresse = _adresse;
        personneContact = _pContact;
        telephone = _telephone;
        email = _email;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof LieuStage)) return false;

        LieuStage lieuStage = (LieuStage) o;

        if (id != lieuStage.id) return false;
        if (entreprise != null ? !entreprise.equals(lieuStage.entreprise) : lieuStage.entreprise != null) return false;
        if (adresse != null ? !adresse.equals(lieuStage.adresse) : lieuStage.adresse != null) return false;
        if (personneContact != null ? !personneContact.equals(lieuStage.personneContact) : lieuStage.personneContact != null)
            return false;
        if (telephone != null ? !telephone.equals(lieuStage.telephone) : lieuStage.telephone != null) return false;
        if (email != null ? !email.equals(lieuStage.email) : lieuStage.email != null) return false;
        return !(owner != null ? !owner.equals(lieuStage.owner) : lieuStage.owner != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (entreprise != null ? entreprise.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (personneContact != null ? personneContact.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return entreprise;
    }
}