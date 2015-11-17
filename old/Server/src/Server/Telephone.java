package Server;

/**
 * Created by Mixmania on 31-05-15 at 17:32.
 */
public class Telephone
{
    private final String owner;
    private final String number;

    public Telephone(String number, String owner)
    {
        this.number = number;
        this.owner = owner;
    }

    public String getNumber()
    {
        return number;
    }

    public String getOwner()
    {
        return owner;
    }
}
