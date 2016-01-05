package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TFE")
public class TFE
{
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_TFE")
    private Integer id=null;

    @Column(name = "TITRE")
    private String titre;

    @Column(name = "POINTSTOTAUX")
    private double pointsTotaux;

    @Column(name = "ANNEEACADDEBUT")
    private int anneeAcadDebut;

    @Column(name = "ANNEEACADFIN")
    private int anneeAcadFin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TECHNOLOGIEXTFE", joinColumns = @JoinColumn(name = "ID_TFE"), inverseJoinColumns = @JoinColumn(name = "ID_TEC"))
    private Set<Technologie> technologie;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXTFE", joinColumns = @JoinColumn(name = "ID_TFE"), inverseJoinColumns = @JoinColumn(name = "ID_UTI"))
    private Set<Utilisateur> utilisateur;

    /**
     * Constructeur
     *
     * @param _owner        L'élève lié au TFE
     * @param _promoteur    Le promoteur (professeur) lié au TFE
     * @param _titre        Le titre du TFE
     * @param _pointsTotaux Les points totaux du TFE
     * @param _anneeDebut   L'année académique de début du TFE
     * @param _anneeFin     L'année académique de fin du TFE
     * @param _technologies La liste des technologies liées à ce TFE
     */
    public TFE(Utilisateur _owner, Utilisateur _promoteur, String _titre, double _pointsTotaux, int _anneeDebut, int _anneeFin, HashSet<Technologie> _technologies)
    {
        utilisateur = new HashSet<>();

        utilisateur.add(_owner);
        utilisateur.add(_promoteur);
        titre = _titre;
        pointsTotaux = _pointsTotaux;
        anneeAcadDebut = _anneeDebut;
        anneeAcadFin = _anneeFin;
        technologie = _technologies;
    }

    public TFE()
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

    public String getTitre()
    {
        return titre;
    }

    public double getPointsTotaux()
    {
        return pointsTotaux;
    }

    public int getAnneeAcadDebut()
    {
        return anneeAcadDebut;
    }

    public int getAnneeAcadFin()
    {
        return anneeAcadFin;
    }

    public Set<Technologie> getTechnologies()
    {
        return technologie;
    }

    public void setTechnologie(Set<Technologie> technologie)
    {
        this.technologie = technologie;
    }

    public Set<Utilisateur> getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Set<Utilisateur> utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public void update(Utilisateur _promoteur, String _titre, double _pointsTotaux, int _anneeDebut, int _anneeFin, HashSet<Technologie> _technologies)
    {
        //Remplace le promoteurs
        if (!utilisateur.contains(_promoteur))
        {
            Utilisateur x = null;
            for (Utilisateur u : utilisateur)
                if (u.getRole().getNom().equals("professeur")) x = u;

            if (x != null) utilisateur.remove(x);

            utilisateur.add(_promoteur);
        }

        titre = _titre;
        pointsTotaux = _pointsTotaux;
        anneeAcadDebut = _anneeDebut;
        anneeAcadFin = _anneeFin;
        technologie = _technologies;
    }

    /**
     * Pré : _pointsTotaux est initialisé<br>
     * Post : les points de ce TFE sont mis à jour
     *
     * @param _pointsTotaux Les points totaux du TFE
     */
    public void update(double _pointsTotaux)
    {
        pointsTotaux = _pointsTotaux;
    }

    public Utilisateur getOwner()
    {
        for (Utilisateur u : utilisateur)
            if (u.getRole().getNom().equals("etudiant_tfe") || u.getRole().getNom().equals("etudiant_tfe_stage"))
                return u;

        return null;
    }

    public Object getPromoteur()
    {
        for (Utilisateur u : utilisateur)
            if (u.getRole().getNom().equals("professeur")) return u;

        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof TFE)) return false;

        TFE tfe = (TFE) o;

        if (id != tfe.id) return false;
        if (Double.compare(tfe.pointsTotaux, pointsTotaux) != 0) return false;
        if (anneeAcadDebut != tfe.anneeAcadDebut) return false;
        if (anneeAcadFin != tfe.anneeAcadFin) return false;
        if (titre != null ? !titre.equals(tfe.titre) : tfe.titre != null) return false;
        if (technologie != null ? !technologie.equals(tfe.technologie) : tfe.technologie != null) return false;
        return !(utilisateur != null ? !utilisateur.equals(tfe.utilisateur) : tfe.utilisateur != null);

    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = id;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        temp = Double.doubleToLongBits(pointsTotaux);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + anneeAcadDebut;
        result = 31 * result + anneeAcadFin;
        result = 31 * result + (technologie != null ? technologie.hashCode() : 0);
        result = 31 * result + (utilisateur != null ? utilisateur.hashCode() : 0);
        return result;
    }
}