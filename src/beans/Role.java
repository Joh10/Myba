package beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
@Table(name = "ROLETABLE")
public class Role implements Serializable
{
    private static final long serialVersionUID = -1900169871483409885L;

    @Id
    @Column(name = "id_rol")
    private int id;

    @Column(name = "nom")
    private String nom;

    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="UTILISATEURXROLETABLE", joinColumns=@JoinColumn(name="ID_ROL"), inverseJoinColumns=@JoinColumn(name="ID_PER"))
    private ArrayList<Permission> permissions;

    /**
     * Constructeur
     *
     * @param    _id            ID (identifiant) du rôle
     * @param    _nom        Nom du rôle
     */
    public Role(int _id, String _nom)
    {
        id = _id;
        nom = _nom;
        permissions = new ArrayList<>();
    }

    /**
     * Constructeur (type recherche, sans identifiant)
     *
     * @param    _nom        Nom du rôle
     */
    public Role(String _nom)
    {
        nom = _nom;
    }

    public Role()
    {
    }

    /**
     * @return l'identifiant du rôle
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'identifiant est modifié par _id
     *
     * @param    _id    L'identifiant du rôle
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return le nom du rôle
     */
    public String getNom()
    {
        return nom;
    }

    /**
     * @param name nom de la permission à vérifier
     * @return true si la permission name est associée à ce rôle, false sinon
     */
    public boolean isAllowed(String name)
    {
        return permissions.contains(name);
    }

    /**
     * @param name nom de la permission à ajouter
     */
    public void addPermission(Permission name)
    {
        permissions.add(name);
    }
}