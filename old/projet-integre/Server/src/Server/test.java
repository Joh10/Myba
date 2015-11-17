package Server;

import DAO.EtudiantDAO;

/**
 * Created by Mixmania on 01-06-15 at 14:48.
 */

public class test
{
    public static void main(String[] args)
    {
        EtudiantDAO.getInstance().delete(new Etudiant("loncinbenjamin@hotmail.com", null, null, null, null, 0, false, false, false));
    }
}
