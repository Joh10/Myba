package beans;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Evaluation
{

    @Id
    @Column(name = "id_Eva")
    private int id;

    @Column(name = "id_dateDefense")
    private Date date;
    private Utilisateur owner;
    private CritereEvaluation critere;
    private TFE tfe;
    private Stage stage;

    private Defense defense;

    @Column(name = "note")
    private double note;

    @Column(name = "commentaire")
    private String commentaire;

    /**
     * Constructeur (évaluation TFE)
     *
     * @throws IllegalArgumentException si le critère n'est pas de type TFE
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     * @param    _id            ID (identifiant) de l'évaluation
     * @param    _date        La date effective de l'évaluation
     * @param    _owner        L'évaluateur de cette évaluation
     * @param    _critere    Le critère sur lequel se base cette évaluation
     * @param    _tfe        Le TFE évalué
     * @param    _note        La note attribuée à cette évaluation
     * @param    _commentaire    Le commentaire assigné à cette évaluation
     */
    public Evaluation(int _id, Date _date, Utilisateur _owner, CritereEvaluation _critere, TFE _tfe, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("tfe")) throw new IllegalArgumentException("Le critère doit concerner un TFE");
        else if (_critere.getNote() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");
        id = _id;
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
     * @throws IllegalArgumentException si le critère n'est pas de type stage
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     * @param    _id            ID (identifiant) de l'évaluation
     * @param    _date        La date effective de l'évaluation
     * @param    _owner        L'évaluateur de cette évaluation
     * @param    _critere    Le critère sur lequel se base cette évaluation
     * @param    _stage        Le stage évalué
     * @param    _note        La note attribuée à cette évaluation
     * @param    _commentaire    Le commentaire assigné à cette évaluation
     */
    public Evaluation(int _id, Date _date, Utilisateur _owner, CritereEvaluation _critere, Stage _stage, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("stage"))
            throw new IllegalArgumentException("Le critère doit concerner un stage");
        else if (_critere.getNote() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");
        id = _id;
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
     * @throws IllegalArgumentException si le critère n'est pas de type stage
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     * @param    _id            ID (identifiant) de l'évaluation
     * @param    _date        La date effective de l'évaluation
     * @param    _owner        L'évaluateur de cette évaluation
     * @param    _critere    Le critère sur lequel se base cette évaluation
     * @param    _defense    La défense qui a menée à cette évaluation
     * @param    _note        La note attribuée à cette évaluation
     * @param    _commentaire    Le commentaire assigné à cette évaluation
     */
    public Evaluation(int _id, Date _date, Utilisateur _owner, CritereEvaluation _critere, Defense _defense, double _note, String _commentaire)
    {
        if (!_critere.getType().equals("defense"))
            throw new IllegalArgumentException("Le critère doit concerner une défense");
        else if (_critere.getNote() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale du critère");
        id = _id;
        date = _date;
        owner = _owner;
        critere = _critere;
        tfe = null;
        stage = null;
        defense = _defense;
        note = _note;
        commentaire = _commentaire;
    }

    /**
     * Pré	:	_note, _commentaire sont initialisés <br>
     * Post	:	les informations de l'évaluation sont mises à jour.
     *
     * @throws IllegalArgumentException si la note attribuée est supérieure à la note maximale du critère
     * @param    _note            La note attribuée à cette évaluation
     * @param    _commentaire    Le commentaire assigné à cette évaluation
     */
    public void update(double _note, String _commentaire)
    {
        if (critere.getNote() < _note)
            throw new IllegalArgumentException("La note ne peut être supérieure à la note maximale de son critère");
        note = _note;
        commentaire = _commentaire;
    }

    /**
     * @return l'identifiant de l'évaluation
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'ID du critère d'évaluation est modifié par _id
     *
     * @param    _id    L'identifiant du critère d'évaluation
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return la date laquelle l'évaluation a été effectuée
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * @return l'évaluateur qui a enregistré cette évaluation
     */
    public Utilisateur getOwner()
    {
        return owner;
    }

    /**
     * @return le TFE concerné par cette évaluation, s'il s'agit d'un TFE. Sinon, null
     */
    public TFE getTFE()
    {
        return tfe;
    }

    /**
     * @return le stage concerné par cette évaluation, s'il s'agit d'un stage. Sinon, null
     */
    public Stage getStage()
    {
        return stage;
    }

    /**
     * @return la défense ayant mené à cette évaluation. Si aucune défense n'a été associée, null
     */
    public Defense getDefense()
    {
        return defense;
    }

    /**
     * @return le critère sur laquelle cette évaluation repose
     */
    public CritereEvaluation getCritere()
    {
        return critere;
    }

    /**
     * @return la note attribuée à cette évaluation
     */
    public double getNote()
    {
        return note;
    }

    /**
     * @return le commentaire attribué à cette évaluation
     */
    public String getCommentaire()
    {
        return commentaire;
    }
}