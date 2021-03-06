package beans;

import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "UTILISATEUR")
public class Utilisateur implements Serializable
{
    private static final long serialVersionUID = -2505999672679566044L;

    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name="ID_UTI")
    private Integer id=null;

    @Column(name = "ENABLE")
    private boolean enabled;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String passwordHash;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "MATRICULE")
    private int matricule;

    @Column(name = "ANNEE")
    private int annee;

    @Column(name = "DOUBLANT")
    private boolean doublant;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXROLETABLE", joinColumns = @JoinColumn(name = "ID_UTI"), inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
    private Set<Role> role;

    /**
     * Constructeur
     *
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
    public Utilisateur(boolean _enabled, Role _role, String _email, String _password, Integer _matricule, String _nom, String _prenom, String _telephone, Integer _annee, Boolean _doublant)
    {
        enabled = _enabled;
        role = new HashSet<>();
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

    public Utilisateur()
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
        Hibernate.initialize(this.role);
        return role.stream().findFirst().get();
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

    public String toString()
    {
        return nom + " " + prenom;
    }
}