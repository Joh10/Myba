package beans;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "TFE")
public class TFE
{
    @Id
    @Column(name = "ID_TFE")
    private int id;

    @Column(name = "TITRE")
    private String titre;

    @Column(name = "POINTSTOTAUX")
    private double pointsTotaux;

    @Column(name = "ANNEEACADDEBUT")
    private int anneeDebut;

    @Column(name = "ANNEEACADFIN")
    private int anneeFin;

    //TODO ????
    private Utilisateur owner;
    private Utilisateur promoteur;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="TECHNOLOGIEXTFE", joinColumns=@JoinColumn(name="ID_TFE"), inverseJoinColumns=@JoinColumn(name="ID_TEC"))
    private ArrayList<Technologie> technologies;

    /**
     * Constructeur
     *
     * @param _id           ID (identifiant) du TFE
     * @param _owner        L'élève lié au TFE
     * @param _promoteur    Le promoteur (professeur) lié au TFE
     * @param _titre        Le titre du TFE
     * @param _pointsTotaux Les points totaux du TFE
     * @param _anneeDebut   L'année académique de début du TFE
     * @param _anneeFin     L'année académique de fin du TFE
     * @param _technologies La liste des technologies liées à ce TFE
     */
    public TFE(int _id, Utilisateur _owner, Utilisateur _promoteur, String _titre, double _pointsTotaux, int _anneeDebut, int _anneeFin, ArrayList<Technologie> _technologies)
    {
        id = _id;
        owner = _owner;
        promoteur = _promoteur;
        titre = _titre;
        pointsTotaux = _pointsTotaux;
        anneeDebut = _anneeDebut;
        anneeFin = _anneeFin;
        technologies = _technologies;
    }

    public TFE()
    {
    }

    /**
     * Pré	:	_promoteur, _titre, _pointsTotaux, _anneeDebut, _anneeFin sont initialisés<br>
     * Post	:	les informations de ce TFE sont mises à jour.
     *
     * @param _promoteur    Le promoteur (professeur) lié au TFE
     * @param _titre        Le titre du TFE
     * @param _pointsTotaux Les points totaux du TFE
     * @param _anneeDebut   L'année académique de début du TFE
     * @param _anneeFin     L'année académique de fin du TFE
     * @param _technologies La liste des technologies utilisées par ce TFE
     */
    public void update(Utilisateur _promoteur, String _titre, double _pointsTotaux, int _anneeDebut, int _anneeFin, ArrayList<Technologie> _technologies)
    {
        promoteur = _promoteur;
        titre = _titre;
        pointsTotaux = _pointsTotaux;
        anneeDebut = _anneeDebut;
        anneeFin = _anneeFin;
        technologies = _technologies;
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

    /**
     * @return l'identifiant du TFE
     */
    public int getId()
    {
        return id;
    }

    /**
     * Pré	:	_id est initialisé<br>
     * Post :	l'ID du TFE est modifié par _id
     *
     * @param    _id    L'identifiant du TFE
     */
    public void setId(int _id)
    {
        id = _id;
    }

    /**
     * @return l'élève lié au TFE.
     */
    public Utilisateur getOwner()
    {
        return owner;
    }

    /**
     * @return le promoteur (professeur) lié au TFE.
     */
    public Utilisateur getPromoteur()
    {
        return promoteur;
    }

    /**
     * @return le titre(sujet) du TFE.
     */
    public String getTitre()
    {
        return titre;
    }

    /**
     * @return les points actuels du TFE.
     */
    public double getPoints()
    {
        return pointsTotaux;
    }

    /**
     * @return l'année académique de début du TFE.
     */
    public int getAnneeDebut()
    {
        return anneeDebut;
    }

    /**
     * @return l'année académique de fin du TFE.
     */
    public int getAnneeFin()
    {
        return anneeFin;
    }

    /**
     * @return la liste des technologies utilisées par ce TFE.
     */
    public ArrayList<Technologie> getTechnologies()
    {
        return technologies;
    }
}