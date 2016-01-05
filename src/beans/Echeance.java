package beans;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ECHEANCE")
public class Echeance
{
    @Id
    @Column(name = "ID_ECH")
    private int id;

    @Column(name = "DATECREATION")
    private Date dateCreation;

    @Column(name = "DATEECHEANCE")
    private Date dateEcheance;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ANNEXE")
    private String annexe;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "STAGEXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_STA"))
    private List<Stage> stage;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TFEXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_TFE"))
    private List<TFE> tfe;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "UTILISATEURXECHEANCE", joinColumns = @JoinColumn(name = "ID_ECH"), inverseJoinColumns = @JoinColumn(name = "ID_UTI"))
    private List<Utilisateur> utilisateur;

    /**
     * Constructeur (type utilisateurs)
     *
     * @param _id           ID (identifiant) de l'échéance
     * @param _owner        Utilisateur (professeur) qui a créé l'échéance
     * @param _dateCreation Date de création de l'échéance
     * @param _dateEcheance Date à laquelle l'échéance expire
     * @param _users        La liste des utilisateurs concernés par cette échéance
     * @param _description  Description (détails) manuscits de l'échéance
     * @param _annexe       Lien(nom) local vers un fichier (facultatif)
     */
    public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, ArrayList<Utilisateur> _users, String _description, String _annexe)
    {
        stage = new ArrayList<>();
        tfe = new ArrayList<>();

        id = _id;
        utilisateur.add(_owner);
        dateCreation = _dateCreation;
        dateEcheance = _dateEcheance;
        description = _description;
        annexe = _annexe;
    }

    /**
     * Constructeur (type TFE)
     *
     * @param _id           ID (identifiant) de l'échéance
     * @param _owner        Utilisateur (professeur) qui a créé l'échéance
     * @param _dateCreation Date de création de l'échéance
     * @param _dateEcheance Date à laquelle l'échéance expire
     * @param _tfe          Le TFE pour quel l'échéance est fixée
     * @param _description  Description (détails) manuscits de l'échéance
     * @param _annexe       Lien(nom) local vers un fichier (facultatif)
     */
    public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, TFE _tfe, String _description, String _annexe)
    {
        stage = new ArrayList<>();
        tfe = new ArrayList<>();

        id = _id;
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
    public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, Stage _stage, String _description, String _annexe)
    {
        stage = new ArrayList<>();
        tfe = new ArrayList<>();

        id = _id;
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
    public void update(ArrayList<Utilisateur> _users)
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

    public List<Stage> getStage()
    {
        return stage;
    }

    public void setStage(List<Stage> stage)
    {
        this.stage = stage;
    }

    public List<TFE> getTfe()
    {
        return tfe;
    }

    public void setTfe(List<TFE> tfe)
    {
        this.tfe = tfe;
    }

    public List<Utilisateur> getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(List<Utilisateur> utilisateur)
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

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (dateCreation != null ? dateCreation.hashCode() : 0);
        result = 31 * result + (dateEcheance != null ? dateEcheance.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (annexe != null ? annexe.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (utilisateur != null ? utilisateur.hashCode() : 0);
        return result;
    }

}