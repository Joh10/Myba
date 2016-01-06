package managers.hibernate;


import managers.UtilisateurManager;

public class test
{
    public static void main(String[] args)
    {
        UtilisateurManager o = new UtilisateurManager();
        System.err.println(o.fetchAll().get(0).getRole());
        System.exit(0);
    }

}
