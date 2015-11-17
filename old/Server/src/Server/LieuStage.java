package Server;

public class LieuStage
{
    private final String entreprise;
    private int id;
    private Adresse adresse;
    private PersonneContact personneContact;

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public LieuStage(int id, String entreprise, Adresse adresse, PersonneContact personneContact)
    {
        this.id = id;
        this.entreprise = entreprise;
        this.adresse = adresse;
        this.personneContact = personneContact;
    }

    public LieuStage(String entreprise, Adresse adresse, PersonneContact personneContact)
    {
        this.entreprise = entreprise;
        this.adresse = adresse;
        this.personneContact = personneContact;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    public int getId()
    {
        return id;
    }

    public String getEntreprise()
    {
        return entreprise;
    }

    public Adresse getAdresse()
    {
        return adresse;
    }

    public void setAdresse(Adresse adresse)
    {
        this.adresse = adresse;
    }

    public PersonneContact getPersonneContact()
    {
        return personneContact;
    }

    public void setPersonneContact(PersonneContact personneContact)
    {
        this.personneContact = personneContact;
    }

    ////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof LieuStage)) return false;

        LieuStage lieuStage = (LieuStage) o;

        if (id != lieuStage.id) return false;
        if (entreprise != null ? !entreprise.equals(lieuStage.entreprise) : lieuStage.entreprise != null) return false;
        if (adresse != null ? !adresse.equals(lieuStage.adresse) : lieuStage.adresse != null) return false;
        return !(personneContact != null ? !personneContact.equals(lieuStage.personneContact) : lieuStage.personneContact != null);

    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (entreprise != null ? entreprise.hashCode() : 0);
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (personneContact != null ? personneContact.hashCode() : 0);
        return result;
    }
}
