package Server;

import DAO.TechnologieDAO;
import DAO.relations.UtiliserDAO;
import DAO.utils.DAOList;
import Server.Rights.IPosteur;

import java.util.List;

public class PropositionStage
{
    private final LieuStage lieuStage;
    private final IPosteur posteur;
    private final DAOList<Technologie> technologies;
    private int id;
    private String sujet;
    private String cheminAnnexe;
    private boolean valide;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public PropositionStage(int id, LieuStage lieuStage, String sujet, String cheminAnnexe, IPosteur posteur)
    {
        this.id = id;
        this.lieuStage = lieuStage;
        this.sujet = sujet;
        this.cheminAnnexe = cheminAnnexe;
        this.posteur = posteur;
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());

        init();
    }

    public PropositionStage(int id, LieuStage lieuStage, String sujet, IPosteur posteur)
    {
        this.id = id;
        this.lieuStage = lieuStage;
        this.sujet = sujet;
        this.posteur = posteur;
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());

        init();
    }

    public PropositionStage(LieuStage lieuStage, String sujet, String cheminAnnexe, IPosteur posteur)
    {
        this.lieuStage = lieuStage;
        this.sujet = sujet;
        this.cheminAnnexe = cheminAnnexe;
        this.posteur = posteur;
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());

        init();
    }

    public PropositionStage(LieuStage lieuStage, String sujet, IPosteur posteur)
    {
        this.lieuStage = lieuStage;
        this.sujet = sujet;
        this.posteur = posteur;
        this.technologies = new DAOList<>(TechnologieDAO.getInstance());

        init();
    }

    private void init()
    {
        this.technologies.setSource(() -> UtiliserDAO.getInstance().findTechnologies(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public String getSujet()
    {
        return sujet;
    }

    public void setSujet(String sujet)
    {
        this.sujet = sujet;
    }

    public String getCheminAnnexe()
    {
        return cheminAnnexe;
    }

    public void setCheminAnnexe(String cheminAnnexe)
    {
        this.cheminAnnexe = cheminAnnexe;
    }

    public LieuStage getLieuStage()
    {
        return lieuStage;
    }

    public boolean isValide()
    {
        return valide;
    }

    public void setValide(boolean valide)
    {
        this.valide = valide;
    }

    public IPosteur getPosteur()
    {
        return posteur;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addTechnologie(Technologie t)
    {
        return technologies.add(t);
    }

    public boolean removeTechnologie(Technologie t)
    {
        return technologies.remove(t);
    }

    public List<Technologie> getTechnologies()
    {
        return technologies.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PropositionStage)) return false;

        PropositionStage that = (PropositionStage) o;

        if (valide != that.valide) return false;
        if (sujet != null ? !sujet.equals(that.sujet) : that.sujet != null) return false;
        if (cheminAnnexe != null ? !cheminAnnexe.equals(that.cheminAnnexe) : that.cheminAnnexe != null) return false;
        if (lieuStage != null ? !lieuStage.equals(that.lieuStage) : that.lieuStage != null) return false;
        if (posteur != null ? !posteur.equals(that.posteur) : that.posteur != null) return false;
        return !(technologies != null ? !technologies.equals(that.technologies) : that.technologies != null);
    }

    @Override
    public int hashCode()
    {
        int result = sujet != null ? sujet.hashCode() : 0;
        result = 31 * result + (cheminAnnexe != null ? cheminAnnexe.hashCode() : 0);
        result = 31 * result + (valide ? 1 : 0);
        result = 31 * result + (lieuStage != null ? lieuStage.hashCode() : 0);
        result = 31 * result + (posteur != null ? posteur.hashCode() : 0);
        result = 31 * result + (technologies != null ? technologies.hashCode() : 0);
        return result;
    }
}
