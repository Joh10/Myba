package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ECHEANCE")
public class Echeance
{
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_ECH")
    private Integer id=null;

    @Column(name = "DATECREATION")
    private Date dateCreation;

    @Column(name = "DATEECHEANCE")
    private Date dateEcheance;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ANNEXE")
    private String annexe;

    @ManyToMany(targetEntity = Stage.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "STAGEXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_STA"))
    private Set<Stage> stage;

    @ManyToMany(targetEntity = TFE.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "TFEXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_TFE"))
    private Set<TFE> tfe;

    @ManyToMany(targetEntity = Utilisateur.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_UTI"))
    private Set<Utilisateur> utilisateur;

    /**
     * Constructeur (type utilisateurs)
     *
     * @param _owner        Utilisateur (professeur) qui a créé l'échéance
     * @param _dateCreation Date de création de l'échéance
     * @param _dateEcheance Date à laquelle l'échéance expire
     * @param _users        La liste des utilisateurs concernés par cette échéance
     * @param _description  Description (détails) manuscits de l'échéance
     * @param _annexe       Lien(nom) local vers un fichier (facultatif)
     */
    public Echeance(Utilisateur _owner, Date _dateCreation, Date _dateEcheance, HashSet<Utilisateur> _users, String _description, String _annexe)
    {
        stage = new HashSet<>();
        tfe = new HashSet<>();

        utilisateur.add(_owner);
        dateCreation = _dateCreation;
        dateEcheance = _dateEcheance;
        description = _description;
        annexe = _annexe;
    }

    /**
     * Constructeur (type TFE)
     *
     * @param _owner        Utilisateur (professeur) qui a créé l'échéance
     * @param _dateCreation Date de création de l'échéance
     * @param _dateEcheance Date à laquelle l'échéance expire
     * @param _tfe          Le TFE pour quel l'échéance est fixée
     * @param _description  Description (détails) manuscits de l'échéance
     * @param _annexe       Lien(nom) local vers un fichier (facultatif)
     */
    public Echeance(Utilisateur _owner, Date _dateCreation, Date _dateEcheance, TFE _tfe, String _description, String _annexe)
    {
        stage = new HashSet<>();
        tfe = new HashSet<>();

        utilisateur.add(_owner);
        dateCreation = _dateCreation;
        dateEcheance = _dateEcheance;
        tfe.add(_tfe);
        description = _description;
        annexe = _annexe;
    }

    /**
     * Constructeur (type stage)
     *
     * @param _id           ID (identifiant) de l'échéance
     * @param _owner        Utilisateur (professeur) qui a créé l'échéance
     * @param _dateCreation Date de création de l'échéance
     * @param _dateEcheance Date à laquelle l'échéance expire
     * @param _stage        Le stage pour quel l'échéance est fixée
     * @param _description  Description (détails) manuscits de l'échéance
     * @param _annexe       Lien(nom) local vers un fichier (facultatif)
     */
    public Echeance(Utilisateur _owner, Date _dateCreation, Date _dateEcheance, Stage _stage, String _description, String _annexe)
    {
        stage = new HashSet<>();
        tfe = new HashSet<>();

        utilisateur.add(_owner);
        dateCreation = _dateCreation;
        dateEcheance = _dateEcheance;
        stage.add(_stage);
        description = _description;
        annexe = _annexe;
    }

    public Echeance()
    {
    }

    /**
     * Pré	:	_date, _description, _annexe sont initialisés <br>
     * Post	:	les informations de l'échéance sont mises à jour.
     *
     * @param _date        Date à laquelle l'échéance expire
     * @param _description Description (détails) manuscits de l'échéance
     * @param _annexe      Note Lien(nom) local vers un fichier (facultatif)
     */
    public void update(Date _date, String _description, String _annexe)
    {
        dateEcheance = _date;
        description = _description;
        annexe = _annexe;
    }

    /**
     * Pré	:	_user est initialisé <br>
     * Post	:	la liste des utilisateurs concernés par cette écheance est mise à jour.
     *
     * @param _users La liste des utilisateurs concernés par cette échéance
     */
    public void update(HashSet<Utilisateur> _users)
    {
        utilisateur = _users;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDateCreation()
    {
        return dateCreation;
    }

    public Date getDateEcheance()
    {
        return dateEcheance;
    }

    public String getDescription()
    {
        return description;
    }

    public String getAnnexe()
    {
        return annexe;
    }

    public Set<Stage> getStage()
    {
        return stage;
    }

    public void setStage(Set<Stage> stage)
    {
        this.stage = stage;
    }

    public Set<TFE> getTfe()
    {
        return tfe;
    }

    public void setTfe(Set<TFE> tfe)
    {
        this.tfe = tfe;
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
            if (!u.getRole().getNom().equals("etudiant_tfe") && !u.getRole().getNom().equals("etudiant_tfe_stage"))
                return u;

        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Echeance)) return false;

        Echeance echeance = (Echeance) o;

        if (id != echeance.id) return false;
        if (dateCreation != null ? !dateCreation.equals(echeance.dateCreation) : echeance.dateCreation != null)
            return false;
        if (dateEcheance != null ? !dateEcheance.equals(echeance.dateEcheance) : echeance.dateEcheance != null)
            return false;
        if (description != null ? !description.equals(echeance.description) : echeance.description != null)
            return false;
        if (annexe != null ? !annexe.equals(echeance.annexe) : echeance.annexe != null) return false;
        if (stage != null ? !stage.equals(echeance.stage) : echeance.stage != null) return false;
        if (tfe != null ? !tfe.equals(echeance.tfe) : echeance.tfe != null) return false;
        return !(utilisateur != null ? !utilisateur.equals(echeance.utilisateur) : echeance.utilisateur != null);
    }
}