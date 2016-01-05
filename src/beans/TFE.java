package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TFE
{
    private int id;
    private String titre;
    private double pointsTotaux;
    private int anneeAcadDebut;
    private int anneeAcadFin;
    private List<Technologie> technologie;
    private List<TFE> tFE;
    private List<Utilisateur> utilisateur;

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
        utilisateur = new ArrayList<>();

        id = _id;
        utilisateur.add(_owner);
        utilisateur.add(_promoteur);
        titre = _titre;
        pointsTotaux = _pointsTotaux;
        anneeAcadDebut = _anneeDebut;
        anneeAcadFin = _anneeFin;
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

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public double getPointsTotaux()
    {
        return pointsTotaux;
    }

    public void setPointsTotaux(double pointsTotaux)
    {
        this.pointsTotaux = pointsTotaux;
    }

    public int getAnneeAcadDebut()
    {
        return anneeAcadDebut;
    }

    public void setAnneeAcadDebut(int anneeAcadDebut)
    {
        this.anneeAcadDebut = anneeAcadDebut;
    }

    public int getAnneeAcadFin()
    {
        return anneeAcadFin;
    }

    public void setAnneeAcadFin(int anneeAcadFin)
    {
        this.anneeAcadFin = anneeAcadFin;
    }

    public List<Technologie> getTechnologies()
    {
        return technologie;
    }

    public void setTechnologie(List<Technologie> technologie)
    {
        this.technologie = technologie;
    }

    public List<TFE> gettFE()
    {
        return tFE;
    }

    public void settFE(List<TFE> tFE)
    {
        this.tFE = tFE;
    }

    public List<Utilisateur> getUtilisateur()
    {
        return utilisateur;
    }

    public void setUtilisateur(List<Utilisateur> utilisateur)
    {
        this.utilisateur = utilisateur;
    }

    public void update(Utilisateur _promoteur, String _titre, double _pointsTotaux, int _anneeDebut, int _anneeFin, ArrayList<Technologie> _technologies)
    {
        //Remplace le promoteurs
        if(!utilisateur.contains(_promoteur))
        {
            Utilisateur x = null;
            for(Utilisateur u : utilisateur)
                if(u.getRole().getNom().equals("professeur"))
                    x = u;

            if(x != null)
                utilisateur.remove(x);

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
        for(Utilisateur u : utilisateur)
            if(u.getRole().getNom().equals("etudiant_tfe") || u.getRole().getNom().equals("etudiant_tfe_stage"))
                return u;

        return null;
    }

    public Object getPromoteur()
    {
        for(Utilisateur u : utilisateur)
            if(u.getRole().getNom().equals("professeur"))
                return u;

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
        if (tFE != null ? !tFE.equals(tfe.tFE) : tfe.tFE != null) return false;
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
        result = 31 * result + (tFE != null ? tFE.hashCode() : 0);
        result = 31 * result + (utilisateur != null ? utilisateur.hashCode() : 0);
        return result;
    }
}