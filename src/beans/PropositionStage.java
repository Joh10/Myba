package beans;

import javax.persistence.*;

@Entity
@Table(name = "PROPOSITIONSTAGE")
public class PropositionStage
{
    @Id
    @Column(name = "ID_PRO")
    private int id;

    @Column(name = "VALIDE")
    private boolean valide;

    @Column(name = "SUJET")
    private String sujet;

    @Column(name = "ANNEXE")
    private String annexe;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_LIEUSTAGE")
    private LieuStage lieuStage;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "propositionStage")
    private Stage stage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_UTILISATEUR")
    private Utilisateur owner;

    /**
     * Constructeur
     *
     * @param _owner  L'utilisateur (étudiant/professeur) qui a effecué cette proposition de stage
     * @param _lieu   L'entreprise concernée par cette proposition
     * @param _valide Si cette proposition de stage est validée ou non (transformée en stage)
     * @param _sujet  Le sujet (temporaire) de la proposition de stage
     * @param _annexe L'adresse interne du fichier annexe
     */
    public PropositionStage(int _id, Utilisateur _owner, LieuStage _lieu, boolean _valide, String _sujet, String _annexe)
    {
        id = _id;
        owner = _owner;
        lieuStage = _lieu;
        valide = _valide;
        sujet = _sujet;
        annexe = _annexe;
    }

    public PropositionStage()
    {
    }

    /**
     * Pré	:	_sujet, _annexe sont initialisés<br>
     * Post	:	les informations de la proposition de stage sont mises à jour.
     *
     * @param _sujet  Le sujet (temporaire) de la proposition de stage
     * @param _annexe L'adresse interne du fichier annexe
     */
    public void update(String _sujet, String _annexe)
    {
        sujet = _sujet;
        annexe = _annexe;
    }

    /**
     * Pré	:	_valide est initialisé<br>
     * Post	:	le statut "validé" de la proposition de stage est mis à jour.
     *
     * @param _valide Si cette proposition de stage est validée ou non (transformée en stage)
     */
    public void update(boolean _valide)
    {
        valide = _valide;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSujet()
    {
        return sujet;
    }

    public String getAnnexe()
    {
        return annexe;
    }

    public LieuStage getLieuStage()
    {
        return lieuStage;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Utilisateur getOwner()
    {
        return owner;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PropositionStage)) return false;

        PropositionStage that = (PropositionStage) o;

        if (id != that.id) return false;
        if (valide != that.valide) return false;
        if (sujet != null ? !sujet.equals(that.sujet) : that.sujet != null) return false;
        if (annexe != null ? !annexe.equals(that.annexe) : that.annexe != null) return false;
        if (lieuStage != null ? !lieuStage.equals(that.lieuStage) : that.lieuStage != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        return !(owner != null ? !owner.equals(that.owner) : that.owner != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (valide ? 1 : 0);
        result = 31 * result + (sujet != null ? sujet.hashCode() : 0);
        result = 31 * result + (annexe != null ? annexe.hashCode() : 0);
        result = 31 * result + (lieuStage != null ? lieuStage.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}