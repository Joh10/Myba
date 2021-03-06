package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STAGE")
public class Stage
{
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_STA")
    private Integer id=null;

    @Column(name = "DATEDEBUT")
    private Date dateDebut;

    @Column(name = "DATEFIN")
    private Date dateFin;

    @Column(name = "POINTSTOTAUX")
    private double pointsTotaux;

    @Column(name = "COMMENTAIRE")
    private String commentaires;

    @ManyToMany(targetEntity = Technologie.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TECHNOLOGIEXSTA", joinColumns = @JoinColumn(name = "ID_STA"), inverseJoinColumns = @JoinColumn(name = "ID_TEC"))
    private Set<Technologie> technologie;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_PROPOSITIONSTAGE")
    private PropositionStage propositionStage;

    @ManyToMany(targetEntity = Utilisateur.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXSTAGE", joinColumns = @JoinColumn(name = "ID_STA"), inverseJoinColumns = @JoinColumn(name = "ID_UTI"))
    private Set<Utilisateur> utilisateur;

    /**
     * Constructeur
     *
     * @param _owner        L'étudiant concerné par ce stage
     * @param _superviseur  Le professeur promoteur de ce stage
     * @param _suiveur      Le maitre de stage assigné à ce stage
     * @param _proposition  La proposition de stage liée à ce stage
     * @param _dDebut       La date de début du stage
     * @param _dFin         La date de fin du stage
     * @param _ptsTotaux    Les points totaux attribués à ce stage
     * @param _commentaires Le commentaire professeur effectué par les professeurs
     * @param _technologies La liste des technologies utilisées durant ce stage
     */
    public Stage(Utilisateur _owner, Utilisateur _superviseur, Utilisateur _suiveur, PropositionStage _proposition, Date _dDebut, Date _dFin, double _ptsTotaux, String _commentaires, HashSet<Technologie> _technologies)
    {
        utilisateur = new HashSet<>();

        utilisateur.add(_owner);
        utilisateur.add(_superviseur);
        utilisateur.add(_suiveur);
        propositionStage = _proposition;
        dateDebut = _dDebut;
        dateFin = _dFin;
        pointsTotaux = _ptsTotaux;
        commentaires = _commentaires;
        technologie = _technologies;
    }

    public Stage()
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

    public Date getDateDebut()
    {
        return dateDebut;
    }

    public Date getDateFin()
    {
        return dateFin;
    }

    public double getPointsTotaux()
    {
        return pointsTotaux;
    }


    public String getCommentaire()
    {
        return commentaires;
    }

    public Set<Technologie> getTechnologies()
    {
        return technologie;
    }

    public void setTechnologie(Set<Technologie> technologie)
    {
        this.technologie = technologie;
    }

    public PropositionStage getPropositionStage()
    {
        return propositionStage;
    }

    public Set<Utilisateur> getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(Set<Utilisateur> utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getOwner()
    {
        for (Utilisateur u : utilisateur)
            if (u.getRole().getNom().equals("etudiant_tfe") || u.getRole().getNom().equals("etudiant_tfe_stage"))
                return u;

        return null;
    }

    public Utilisateur getSuiveur()
    {
        for (Utilisateur u : utilisateur)
            if (u.getRole().getNom().equals("maitre_stage")) return u;

        return null;
    }

    public Utilisateur getSuperviseur()
    {
        for (Utilisateur u : utilisateur)
            if (u.getRole().getNom().equals("professeur")) return u;

        return null;
    }


    /**
     * Pré	:	_superviseur, _suiveur, _dDebut, _dFin, _commentaires, _technologies sont initialisés<br>
     * Post	:	les informations de ce stage sont mises à jour.
     *
     * @param _superviseur  Le professeur promoteur de ce stage
     * @param _suiveur      Le maitre de stage assigné à ce stage
     * @param _dDebut       La date de début du stage
     * @param _dFin         La date de fin du stage
     * @param _commentaires Le commentaire professeur effectué par les professeurs
     * @param _technologies La liste des technologies utilisées durant ce stage
     */
    public void update(Utilisateur _superviseur, Utilisateur _suiveur, Date _dDebut, Date _dFin, String _commentaires, Set<Technologie> _technologies)
    {
        //Remplace le professeur
        if (!utilisateur.contains(_superviseur))
        {
            Utilisateur x = null;
            for (Utilisateur u : utilisateur)
                if (u.getRole().getNom().equals("professeur")) x = u;

            if (x != null) utilisateur.remove(x);

            utilisateur.add(_superviseur);
        }

        //Remplace le maitre de stage
        if (!utilisateur.contains(_suiveur))
        {
            Utilisateur x = null;
            for (Utilisateur u : utilisateur)
                if (u.getRole().getNom().equals("maitre_stage")) x = u;

            if (x != null) utilisateur.remove(x);

            utilisateur.add(_suiveur);
        }

        dateDebut = _dDebut;
        dateFin = _dFin;
        commentaires = _commentaires;
        technologie = _technologies;
    }

    /**
     * Pré : _ptsTotaux est initialisé<br>
     * Post : les points totaux de ce stage sont mis à jour
     *
     * @param _ptsTotaux Les points totaux du stage
     */
    public void update(double _ptsTotaux)
    {
        pointsTotaux = _ptsTotaux;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Stage)) return false;

        Stage stage = (Stage) o;

        if (id != stage.id) return false;
        if (Double.compare(stage.pointsTotaux, pointsTotaux) != 0) return false;
        if (dateDebut != null ? !dateDebut.equals(stage.dateDebut) : stage.dateDebut != null) return false;
        if (dateFin != null ? !dateFin.equals(stage.dateFin) : stage.dateFin != null) return false;
        if (commentaires != null ? !commentaires.equals(stage.commentaires) : stage.commentaires != null) return false;
        if (technologie != null ? !technologie.equals(stage.technologie) : stage.technologie != null) return false;
        if (propositionStage != null ? !propositionStage.equals(stage.propositionStage) : stage.propositionStage != null)
            return false;
        return !(utilisateur != null ? !utilisateur.equals(stage.utilisateur) : stage.utilisateur != null);

    }
}