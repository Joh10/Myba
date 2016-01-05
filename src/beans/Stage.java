package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class Stage
{
    private int id;
    private Date dateDebut;
    private Date dateFin;
    private double pointsTotaux;
    private String commentaires;
    private List<Technologie> technologie;
    private PropositionStage propositionStage;
    private List<Utilisateur> utilisateur;

    /**
     * Constructeur
     *
     * @param _id           ID (identifiant) du stage
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
    public Stage(int _id, Utilisateur _owner, Utilisateur _superviseur, Utilisateur _suiveur, PropositionStage _proposition, Date _dDebut, Date _dFin, double _ptsTotaux, String _commentaires, ArrayList<Technologie> _technologies)
    {
        utilisateur = new ArrayList<>();

        id = _id;
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

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin()
    {
        return dateFin;
    }

    public void setDateFin(Date dateFin)
    {
        this.dateFin = dateFin;
    }

    public double getPointsTotaux()
    {
        return pointsTotaux;
    }

    public void setPointsTotaux(double pointsTotaux)
    {
        this.pointsTotaux = pointsTotaux;
    }

    public String getCommentaire()
    {
        return commentaires;
    }

    public void setCommentaires(String commentaires)
    {
        this.commentaires = commentaires;
    }

    public List<Technologie> getTechnologies()
    {
        return technologie;
    }

    public void setTechnologie(List<Technologie> technologie)
    {
        this.technologie = technologie;
    }

    public PropositionStage getPropositionStage()
    {
        return propositionStage;
    }

    public void setPropositionStage(PropositionStage propositionStage)
    {
        this.propositionStage = propositionStage;
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
        for(Utilisateur u : utilisateur)
            if(u.getRole().getNom().equals("etudiant_tfe") || u.getRole().getNom().equals("etudiant_tfe_stage"))
                return u;

        return null;
    }

    public Utilisateur getSuiveur()
    {
        for(Utilisateur u : utilisateur)
            if(u.getRole().getNom().equals("maitre_stage"))
                return u;

        return null;
    }

    public Utilisateur getSuperviseur()
    {
        for(Utilisateur u : utilisateur)
            if(u.getRole().getNom().equals("professeur"))
                return u;

        return null;
    }


    /**
     * Pré	:	_superviseur, _suiveur, _dDebut, _dFin, _commentaires, _technologies sont initialisés<br>
     * Post	:	les informations de ce stage sont mises à jour.
     *
     * @param    _superviseur    Le professeur promoteur de ce stage
     * @param    _suiveur        Le maitre de stage assigné à ce stage
     * @param    _dDebut            La date de début du stage
     * @param    _dFin            La date de fin du stage
     * @param    _commentaires    Le commentaire professeur effectué par les professeurs
     * @param    _technologies    La liste des technologies utilisées durant ce stage
     */
    public void update(Utilisateur _superviseur, Utilisateur _suiveur, Date _dDebut, Date _dFin, String _commentaires, List<Technologie> _technologies)
    {
        //Remplace le professeur
        if(!utilisateur.contains(_superviseur))
        {
            Utilisateur x = null;
            for(Utilisateur u : utilisateur)
                if(u.getRole().getNom().equals("professeur"))
                    x = u;

            if(x != null)
                utilisateur.remove(x);

            utilisateur.add(_superviseur);
        }

        //Remplace le maitre de stage
        if(!utilisateur.contains(_suiveur))
        {
            Utilisateur x = null;
            for(Utilisateur u : utilisateur)
                if(u.getRole().getNom().equals("maitre_stage"))
                    x = u;

            if(x != null)
                utilisateur.remove(x);

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

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = id;
        result = 31 * result + (dateDebut != null ? dateDebut.hashCode() : 0);
        result = 31 * result + (dateFin != null ? dateFin.hashCode() : 0);
        temp = Double.doubleToLongBits(pointsTotaux);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (commentaires != null ? commentaires.hashCode() : 0);
        result = 31 * result + (technologie != null ? technologie.hashCode() : 0);
        result = 31 * result + (propositionStage != null ? propositionStage.hashCode() : 0);
        result = 31 * result + (utilisateur != null ? utilisateur.hashCode() : 0);
        return result;
    }
}