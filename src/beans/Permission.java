package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Mixmania on 04-01-16 at 14:41.
 */

@Entity
@Table(name = "PERMISSIONTABLE")
public class Permission
{
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_PER")
    private Integer id=null;

    @Column(name = "NOM")
    private String nom;

    public Permission(String nom)
    {
        this.nom = nom;
    }

    public Permission()
    {
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
