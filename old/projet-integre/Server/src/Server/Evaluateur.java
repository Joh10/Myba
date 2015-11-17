package Server;

import DAO.TelephoneDAO;
import DAO.utils.DAOList;
import Server.Rights.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Mixmania on 30-05-15 at 16:29.
 */

public abstract class Evaluateur
{
    final String mail; //ID
    private final DAOList<Telephone> noTel;
    private List<Role> roles;
    private String motDePasse;
    String prenom;
    String nom;

    Evaluateur(String mail, String motDePasse, String prenom, String nom)
    {
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;

        this.noTel = new DAOList<>(TelephoneDAO.getInstance());
        this.noTel.setSource(() -> TelephoneDAO.getInstance().findFromEvaluateur(mail));
    }

    Evaluateur(String mail, String motDePasse, String prenom, String nom, List<Role> roles)
    {
        this.mail = mail;
        this.roles = roles;
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;

        this.noTel = new DAOList<>(TelephoneDAO.getInstance());
        this.noTel.setSource(() -> TelephoneDAO.getInstance().findFromEvaluateur(mail));
    }

    public String getID()
    {
        return mail;
    }

    public String getMotDePasse()
    {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse)
    {
        this.motDePasse = motDePasse;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public boolean addTelephone(String s)
    {
        return this.noTel.add(new Telephone(mail, s));
    }

    public boolean removeTelephone(String s)
    {
        for (Telephone t : noTel)
            if (t.getNumber().equals(s)) return noTel.remove(t);

        return false;
    }

    public List<String> getNoTel()
    {
        return noTel.get().stream().map(Telephone::getNumber).collect(Collectors.toList());
    }

    @Override
    public String toString()
    {
        return nom + " " + prenom;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Evaluateur)) return false;

        Evaluateur that = (Evaluateur) o;

        if (mail != null ? !mail.equals(that.mail) : that.mail != null) return false;
        if (motDePasse != null ? !motDePasse.equals(that.motDePasse) : that.motDePasse != null) return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        return !(noTel != null ? !noTel.equals(that.noTel) : that.noTel != null);
    }

    @Override
    public int hashCode()
    {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (noTel != null ? noTel.hashCode() : 0);
        return result;
    }
}
