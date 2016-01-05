package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable
{
    private static final long serialVersionUID = -1900169871483409885L;

    private int id;
    private String nom;
    private List<Permission> permission;
    private List<Utilisateur> utilisateur;

    /**
     * Constructeur
     *
     * @param _id  ID (identifiant) du rôle
     * @param _nom Nom du rôle
     */
    public Role(int _id, String _nom)
    {
        id = _id;
        nom = _nom;
        permission = new ArrayList<>();
        utilisateur = new ArrayList<>();
    }

    /**
     * Constructeur (type recherche, sans identifiant)
     *
     * @param _nom Nom du rôle
     */
    public Role(String _nom)
    {
        nom = _nom;
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

    public List<Permission> getPermission()
    {
        return permission;
    }

    public void setPermission(List<Permission> permission)
    {
        this.permission = permission;
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

    /**
     * @param name nom de la permission à ajouter
     */
    public void addPermission(Permission name)
    {
        permission.add(name);
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