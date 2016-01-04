package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.security.MessageDigest;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable
{
    /*
     * Sérializable car l'utilisateur va être stocké dans les sessions
     */
    private static final long serialVersionUID = -2505999672679566044L;

    @Id
    @Column(name = "id_Uti")
    private int id;

    @Column(name = "enable")
    private boolean enabled;

    //TODO ?????
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String passwordHash;

    @Column(name = "matricule")
    private Integer matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "annee")
    private Integer annee;

    @Column(name = "doublant")
    private Boolean doublant;

    /**
     * Constructeur
     *
     * @param _id        ID (identifiant) de l'utilisateur
     * @param _enabled   L'état d'activation de l'utilisateur
     * @param _role      Le rôle de l'utilisateur
     * @param _email     L'adresse email de l'utilisateur
     * @param _password  Le mot de passe de l'utilisateur
     * @param _matricule Le matricule de l'utilisateur (uniquement pour les étudiants)
     * @param _nom       Le nom de l'utilisateur
     * @param _prenom    Le prénom de l'utilisateur
     * @param _telephone Le numéro de téléphone de l'utilisateur
     * @param _annee     L'année dans laquelle se trouve l'utilisateur (uniquement pour les étudiants)
     * @param _doublant  L'utilisateur est doublant ou non (uniquement pour les étudiants)
     */
    public Utilisateur(int _id, boolean _enabled, Role _role, String _email, String _password, Integer _matricule, String _nom, String _prenom, String _telephone, Integer _annee, Boolean _doublant)
    {
        id = _id;
        enabled = _enabled;
        role = _role;
        email = _email;
        passwordHash = _password;
        matricule = _matricule;
        nom = _nom;
        prenom = _prenom;
        telephone = _telephone;
        annee = _annee;
        doublant = _doublant;
    }

    public Utilisateur()
    {
    }

    /**
     * Pré	:	_email, _matricule, _nom, _prenom, _telephone, _annee, _doublant sont initialisés<br>
     * Post	:	les données de l'utilisateur sont mises à jour.
     *
     * @param _enabled   L'état d'activation de l'utilisateur
     * @param _email     L'adresse email de l'utilisateur
     * @param _matricule Le matricule de l'utilisateur (uniquement pour les étudiants)
     * @param _nom       Le nom de l'utilisateur
     * @param _prenom    Le prénom de l'utilisateur
     * @param _telephone Le numéro de téléphone de l'utilisateur
     * @param _annee     L'année dans laquelle est l'utilisateur (uniquement pour les étudiants)
     * @param _doublant  L'utilisateur est doublant ou non (uniquement pour les étudiants)
     */
    public void update(boolean _enabled, String _email, Integer _matricule, String _nom, String _prenom, String _telephone, Integer _annee, Boolean _doublant)
    {
        enabled = _enabled;
        email = _email;
        matricule = _matricule;
        nom = _nom;
        prenom = _prenom;
        telephone = _telephone;
        annee = _annee;
        doublant = _doublant;
    }

    /**
     * Pré :	password est un String initialisé
     *
     * @param password Le mot de passe à hashé
     * @return le chiffrement SHA-256 avec un sel, de password.
     */
    private String getHash(String password)
    {
        try
        {
            password = "Projet**Integre_" + password + "*" + id + "2014-1025";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++)
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));

            return sb.toString();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    /**
     * @return l'ID de l'utilisateur
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'ID de l'utilisateur est modifié par _id
     *
     * @param _id L'ID de l'utilisateur
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return true si l'utilisateur est actif, false sinon
     */
    public boolean isEnabled()
    {
        return enabled;
    }

    /**
     * @return le rôle de l'utilisateur
     */
    public Role getRole()
    {
        return role;
    }


    /**
     * Pré	:	_role est initialisé<br>
     * Post :	le rôle de l'utilisateur est modifié par _role
     *
     * @param _role Le rôle de l'utilisateur
     */
    public void setRole(Role _role)
    {
        role = _role;
    }

    /**
     * @return l'adresse e-mail de l'utilisateur
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return le chiffrement du mot de passe de l'utilisateur
     */
    public String getPasswordHash()
    {
        return passwordHash;
    }

    /**
     * @return true si le mot de passe est correct, false sinon
     */
    public boolean checkPassword(String password)
    {
        return (passwordHash.equals(getHash(password)));
    }

    /**
     * @return le numéro de matricule de l'utilisateur (étudiant)
     */
    public Integer getMatricule()
    {
        return matricule;
    }

    /**
     * @return le nom de l'utilisateur
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @return le prénom de l'utilisateur
     */
    public String getPrenom()
    {
        return prenom;
    }

    /**
     * @return le numéro de téléphone de l'utilisateur
     */
    public String getTelephone()
    {
        return telephone;
    }

    /**
     * @return l'année de l'utilisateur (étudiant)
     */
    public Integer getAnnee()
    {
        return annee;
    }

    /**
     * @return true si l'étudiant est doublant, false sinon
     */
    public Boolean isDoublant()
    {
        return doublant;
    }

    /**
     * Pré	:	password est initialisé<br>
     * Post :	le hash du mot de passe de l'utilisateur est actualisé
     *
     * @param    password    Le nouveau mot de passe de l'utilisateur
     */
    public void setPassword(String password)
    {
        passwordHash = getHash(password);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((annee == null) ? 0 : annee.hashCode());
        result = prime * result + ((doublant == null) ? 0 : doublant.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + id;
        result = prime * result + ((matricule == null) ? 0 : matricule.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
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
        Utilisateur other = (Utilisateur) obj;
        if (annee == null)
        {
            if (other.annee != null) return false;
        } else if (!annee.equals(other.annee)) return false;
        if (doublant == null)
        {
            if (other.doublant != null) return false;
        } else if (!doublant.equals(other.doublant)) return false;
        if (email == null)
        {
            if (other.email != null) return false;
        } else if (!email.equals(other.email)) return false;
        if (enabled != other.enabled) return false;
        if (id != other.id) return false;
        if (matricule == null)
        {
            if (other.matricule != null) return false;
        } else if (!matricule.equals(other.matricule)) return false;
        if (nom == null)
        {
            if (other.nom != null) return false;
        } else if (!nom.equals(other.nom)) return false;
        if (prenom == null)
        {
            if (other.prenom != null) return false;
        } else if (!prenom.equals(other.prenom)) return false;
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
        return nom + " " + prenom;
    }
}