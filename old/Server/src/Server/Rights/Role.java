package Server.Rights;

/**
 * Created by Mixmania on 31-05-15 at 15:28.
 */
public enum Role
{
    PROFESSEUR("PROFESSEUR"),
    ETUDIANT("ETUDIANT"),
    MAITRESTAGE("MAITRESTAGE"),
    PRESIDENTJURY("PRESIDENTJURY");

    private final String data;

    Role(String name)
    {
        this.data = name;
    }

    public String getData()
    {
        return data;
    }
}
