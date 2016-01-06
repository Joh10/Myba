package managers;

import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurManager extends HibernateManager<Utilisateur>
{
    public List<Utilisateur> fetchAll(String role_name)
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();

        Query q = se.createQuery("from Utilisateur s");
        List<Utilisateur> queryList = q.list();
        List<Utilisateur> t = new ArrayList<>();

        for (Utilisateur u : queryList)
            if (u.getRole().getNom().equals(role_name)) t.add(u);

        se.getTransaction().commit();
        return t;
    }


    public List<Utilisateur> fetchAll()
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();

        Query q = se.createQuery("from Utilisateur s");
        List<Utilisateur> t = q.list();

        se.getTransaction().commit();
        return t;
    }

    public Utilisateur find(String identifiant, String password)
    {
        Session se = HibernateUtil.getInstance().getSession();
        se.beginTransaction();
        boolean ok = true;

        Query q = se.createQuery("from Utilisateur s where s.email = :x and s.passwordHash = :y");
        q.setParameter("x", identifiant);
        q.setParameter("y", password);

        List queryList = q.list();

        if (queryList != null && queryList.isEmpty())
            ok = false;

        Utilisateur t = (Utilisateur) queryList.get(0);
        se.getTransaction().commit();

        return ok ? t : null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return Utilisateur.class;
    }

}