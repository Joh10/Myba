import beans.Role;
import beans.Utilisateur;
import managers.UtilisateurManager;

public class HibernateMain
{
    public static void main(String[] args)
    {
        UtilisateurManager x = new UtilisateurManager();
        Utilisateur course = new Utilisateur(true, new Role("professeur"), "mixmaniaqedfqeze@gmail.com", "1234", 10173, "x", "y", "0496039911", 1, false);
        System.err.println(x.insert(course));
    }
}
