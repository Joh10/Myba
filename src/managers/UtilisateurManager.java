package managers;

import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager extends HibernateManager<Utilisateur>
{
    public List<Utilisateur> fetchAll(String role_name)
    {
        return execute(se ->
        {
            Query q = se.createQuery("from Utilisateur s");
            List<Utilisateur> queryList = q.list();
            List<Utilisateur> temp = new ArrayList<>();

            for(Utilisateur u : queryList)
                if(u.getRole().getNom().equals(role_name))
                    temp.add(u);

            return temp;
        });
    }

    public Utilisateur find(String identifiant, String password)
    {
        return execute(se ->
        {
            System.err.println(identifiant + "  " + password);
            Query q = se.createQuery("from Utilisateur s");
            //q.setParameter("x", identifiant);
            //q.setParameter("y", password);

            List queryList = q.list();

            queryList.forEach(System.err::println);

            if (queryList != null && queryList.isEmpty()) return null;

            return null;
        });
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Utilisateur.class;
    }
}