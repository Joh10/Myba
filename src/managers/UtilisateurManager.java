package managers;

import beans.Utilisateur;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager extends HibernateManager<Utilisateur>
{
    public ArrayList<Utilisateur> fetchAll(String role_name)
    {
        //TODO
        return null;
    }

    public Utilisateur find(String identifiant, String password)
    {
        Query q = HibernateConnector.getInstance().getSession().createQuery("from Utilisateur s where s.email = :x and s.passwordHash = :y");
        q.setParameter("x", identifiant);
        q.setParameter("y", password);

        List queryList = q.list();

        if (queryList != null && queryList.isEmpty()) return null;

        return (Utilisateur) queryList.get(0);
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Utilisateur.class;
    }
}