package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "ROLETABLE")
public class Role implements Serializable
{
    private static final long serialVersionUID = -1900169871483409885L;

    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_ROL")
    private Integer id=null;

    @Column(name = "NOM")
    private String nom;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PERMISSIONTABLEXROLETABLE", joinColumns = @JoinColumn(name = "ID_ROL"), inverseJoinColumns = @JoinColumn(name = "ID_PER"))
    private List<Permission> permission;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXROLETABLE", joinColumns = @JoinColumn(name = "ID_ROL"), inverseJoinColumns = @JoinColumn(name = "ID_UTI"))
    private List<Utilisateur> utilisateur;

    /**
     * Constructeur (type recherche, sans identifiant)
     *
     * @param _nom Nom du rôle
     */
    public Role(String _nom)
    {
        nom = _nom;
    }

    public Role()
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

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public List<Utilisateur> getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(List<Utilisateur> utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    /**
     * @param name nom de la permission à vérifier
     * @return true si la permission name est associée à ce rôle, false sinon
     */
    public boolean isAllowed(String name)
    {
        return permission.contains(name);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        if (id != role.id) return false;
        if (nom != null ? !nom.equals(role.nom) : role.nom != null) return false;
        if (permission != null ? !permission.equals(role.permission) : role.permission != null) return false;
        return !(utilisateur != null ? !utilisateur.equals(role.utilisateur) : role.utilisateur != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        result = 31 * result + (utilisateur != null ? utilisateur.hashCode() : 0);
        return result;
    }
}