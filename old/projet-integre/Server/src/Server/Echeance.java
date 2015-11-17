package Server;

import DAO.EtudiantDAO;
import DAO.SuiviEcheanceDAO;
import DAO.relations.ViserDAO;
import DAO.utils.DAOList;

import java.util.Date;
import java.util.List;

public class Echeance
{
    private final DAOList<SuiviEcheance> suivisEcheances;
    private final DAOList<Etudiant> etudiants;
    private int id;
    private Date date;
    private String titre;
    private String description;
    private String annexe;
    private TFE tfe;
    private Stage stage;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public Echeance(int id, Date date, String titre, String description, String annexe, TFE tfe)
    {
        this.tfe = tfe;
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.description = description;
        this.annexe = annexe;
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.etudiants = new DAOList<>(EtudiantDAO.getInstance());

        init();
    }

    public Echeance(int id, Date date, String titre, String description, String annexe, Stage stage)
    {
        this.stage = stage;
        this.id = id;
        this.date = date;
        this.titre = titre;
        this.description = description;
        this.annexe = annexe;
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.etudiants = new DAOList<>(EtudiantDAO.getInstance());

        init();
    }

    public Echeance(Date date, String titre, String description, String annexe, TFE tfe)
    {
        this.tfe = tfe;
        this.date = date;
        this.titre = titre;
        this.description = description;
        this.annexe = annexe;
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.etudiants = new DAOList<>(EtudiantDAO.getInstance());

        init();
    }

    public Echeance(Date date, String titre, String description, String annexe, Stage stage)
    {
        this.stage = stage;
        this.date = date;
        this.titre = titre;
        this.description = description;
        this.annexe = annexe;
        this.suivisEcheances = new DAOList<>(SuiviEcheanceDAO.getInstance());
        this.etudiants = new DAOList<>(EtudiantDAO.getInstance());

        init();
    }

    private void init()
    {
        this.suivisEcheances.setSource(() -> SuiviEcheanceDAO.getInstance().findFromEcheance(id));
        this.etudiants.setSource(() -> ViserDAO.getInstance().findEtudiants(id));
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAnnexe()
    {
        return annexe;
    }

    public void setAnnexe(String annexe)
    {
        this.annexe = annexe;
    }

    public Stage getStage()
    {
        return stage;
    }

    public TFE getTfe()
    {
        return tfe;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public boolean addSuivisEcheance(SuiviEcheance e)
    {
        return suivisEcheances.add(e);
    }

    public boolean removeSuivisEcheance(SuiviEcheance e)
    {
        return suivisEcheances.remove(e);
    }

    public List<SuiviEcheance> getSuivisEcheances()
    {
        return suivisEcheances.get();
    }

    public boolean addEtudiant(Etudiant e)
    {
        return etudiants.add(e);
    }

    public boolean removeEtudiant(Etudiant e)
    {
        return etudiants.remove(e);
    }

    public List<Etudiant> getEtudiants()
    {
        return etudiants.get();
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Echeance)) return false;

        Echeance echeance = (Echeance) o;

        if (id != echeance.id) return false;
        if (date != null ? !date.equals(echeance.date) : echeance.date != null) return false;
        if (titre != null ? !titre.equals(echeance.titre) : echeance.titre != null) return false;
        if (description != null ? !description.equals(echeance.description) : echeance.description != null)
            return false;
        if (annexe != null ? !annexe.equals(echeance.annexe) : echeance.annexe != null) return false;
        return !(suivisEcheances != null ? !suivisEcheances.equals(echeance.suivisEcheances) : echeance.suivisEcheances != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (annexe != null ? annexe.hashCode() : 0);
        result = 31 * result + (suivisEcheances != null ? suivisEcheances.hashCode() : 0);
        return result;
    }
}
