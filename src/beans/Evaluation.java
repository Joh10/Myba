package beans;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EVALUATION")
public class Evaluation
{
    @Id
    @GenericGenerator(name="gen" , strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID_EVA")
    private Integer id=null;

    @Column(name = "DATEDEFENSE")
    private Date date;

    @Column(name = "NOTE")
    private double note;

    @Column(name = "COMMENTAIRE")
    private String commentaire;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_TFE")
    private TFE tfe;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_STAGE")
    private Stage stage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_CRITERE")
    private Critere critere;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_DEFENSE")
    private Defense defense;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "REF_UTILISATEUR")
    private Utilisateur owner;

    /**
     * Constructeur (évaluation TFE)
     *
     * @param _date        La date effective de l'évaluation
     * @param _owner       L'évaluateur de cette évaluation
     * @param _critere     Le critère sur lequel se base cette évaluation
     * @param _tfe         Le TFE évalué
     * @param _note        La note attribuée à cette évaluation
     * @param _commentaire Le commentaire assigné à cette évaluation
     * @throws IllegalArgumentException si le critère n'est pas de type TFE
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     */
    public Evaluation(Date _date, Utilisateur _owner, Critere _critere, TFE _tfe, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("tfe")) throw new IllegalArgumentException("Le critère doit concerner un TFE");
        else if (_critere.getNoteMax() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");

        date = _date;
        owner = _owner;
        critere = _critere;
        tfe = _tfe;
        stage = null;
        defense = null;
        note = _note;
        commentaire = _commentaire;
    }

    /**
     * Constructeur (évaluation stage)
     *
     * @param _id          ID (identifiant) de l'évaluation
     * @param _date        La date effective de l'évaluation
     * @param _owner       L'évaluateur de cette évaluation
     * @param _critere     Le critère sur lequel se base cette évaluation
     * @param _stage       Le stage évalué
     * @param _note        La note attribuée à cette évaluation
     * @param _commentaire Le commentaire assigné à cette évaluation
     * @throws IllegalArgumentException si le critère n'est pas de type stage
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     */
    public Evaluation( Date _date, Utilisateur _owner, Critere _critere, Stage _stage, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("stage"))
            throw new IllegalArgumentException("Le critère doit concerner un stage");
        else if (_critere.getNoteMax() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");
        date = _date;
        owner = _owner;
        critere = _critere;
        tfe = null;
        stage = _stage;
        defense = null;
        note = _note;
        commentaire = _commentaire;
    }

    /**
     * Constructeur (évaluation défense)
     *
     * @param _id          ID (identifiant) de l'évaluation
     * @param _date        La date effective de l'évaluation
     * @param _owner       L'évaluateur de cette évaluation
     * @param _critere     Le critère sur lequel se base cette évaluation
     * @param _defense     La défense qui a menée à cette évaluation
     * @param _note        La note attribuée à cette évaluation
     * @param _commentaire Le commentaire assigné à cette évaluation
     * @throws IllegalArgumentException si le critère n'est pas de type stage
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     */
    public Evaluation( Date _date, Utilisateur _owner, Critere _critere, Defense _defense, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("defense"))
            throw new IllegalArgumentException("Le critère doit concerner une défense");
        else if (_critere.getNoteMax() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");

        date = _date;
        owner = _owner;
        critere = _critere;
        tfe = null;
        stage = null;
        defense = _defense;
        note = _note;
        commentaire = _commentaire;
    }

    public Evaluation()
    {
    }

    public void update(double _note, String _commentaire)
    {
        if (critere.getNoteMax() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale de son critère");
        note = _note;
        commentaire = _commentaire;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public double getNote()
    {
        return note;
    }

    public String getCommentaire()
    {
        return commentaire;
    }

    public TFE getTFE()
    {
        return tfe;
    }

    public Stage getStage()
    {
        return stage;
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public Critere getCritere()
    {
        return critere;
    }

    public Defense getDefense()
    {
        return defense;
    }

    public Utilisateur getOwner()
    {
        return owner;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;

        Evaluation that = (Evaluation) o;

        if (id != that.id) return false;
        if (Double.compare(that.note, note) != 0) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (commentaire != null ? !commentaire.equals(that.commentaire) : that.commentaire != null) return false;
        if (tfe != null ? !tfe.equals(that.tfe) : that.tfe != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        if (critere != null ? !critere.equals(that.critere) : that.critere != null) return false;
        if (defense != null ? !defense.equals(that.defense) : that.defense != null) return false;
        return !(owner != null ? !owner.equals(that.owner) : that.owner != null);

    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        temp = Double.doubleToLongBits(note);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (commentaire != null ? commentaire.hashCode() : 0);
        result = 31 * result + (tfe != null ? tfe.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (critere != null ? critere.hashCode() : 0);
        result = 31 * result + (defense != null ? defense.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }
}