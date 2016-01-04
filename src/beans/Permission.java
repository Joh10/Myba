package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Mixmania on 04-01-16 at 14:41.
 */

@Entity
@Table(name = "PERMISSIONTABLE")
public class Permission
{
    @Id
    @Column(name = "ID_PER")
    private int id;

    @Column(name ="NOM")
    private String nom;

    public Permission(int id, String nom)
    {
        this.id = id;
        this.nom = nom;
    }

    public int getId()
    {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }
}
