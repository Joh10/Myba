package beans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class Utilisateur implements Serializable
{
    private static final long serialVersionUID = -2505999672679566044L;

    private int id;
    private boolean enabled;
    private String email;
    private String passwordHash;
    private String nom;
    private String prenom;
    private String telephone;
    private int matricule;
    private int annee;
    private boolean doublant;
    private List<Role> role;

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
        role = new ArrayList<>();
        role.add(_role);
        email = _email;
        passwordHash = _password;
        matricule = _matricule;
        nom = _nom;
        prenom = _prenom;
        telephone = _telephone;
        annee = _annee;
        doublant = _doublant;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPassword(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public boolean checkPassword(String password)
    {
        return (passwordHash.equals(getHash(password)));
    }

    private String getHash(String password)
    {
        try
        {
            password = "Projet**Integre_" + password + "*" + id + "2014-1025";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByteData : byteData)
                sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));

            return sb.toString();
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public Integer getMatricule()
    {
        return matricule;
    }

    public void setMatricule(int matricule)
    {
        this.matricule = matricule;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    public int getAnnee()
    {
        return annee;
    }

    public void setAnnee(int annee)
    {
        this.annee = annee;
    }

    public boolean isDoublant()
    {
        return doublant;
    }

    public void setDoublant(boolean doublant)
    {
        this.doublant = doublant;
    }

    public Role getRole()
    {
        return role.get(0);
    }

    public void setRole(Role role)
    {
        this.role.clear();
        this.role.add(role);
    }

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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Utilisateur)) return false;

        Utilisateur that = (Utilisateur) o;

        if (id != that.id) return false;
        if (enabled != that.enabled) return false;
        if (annee != that.annee) return false;
        if (doublant != that.doublant) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (passwordHash != null ? !passwordHash.equals(that.passwordHash) : that.passwordHash != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (telephone != null ? !telephone.equals(that.telephone) : that.telephone != null) return false;
        return !(role != null ? !role.equals(that.role) : that.role != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (passwordHash != null ? passwordHash.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
        result = 31 * result + annee;
        result = 31 * result + (doublant ? 1 : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public String toString()
    {
        return nom + " " + prenom;
    }
}