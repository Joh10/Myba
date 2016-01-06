package managers;

import beans.PropositionStage;
import beans.Utilisateur;
import managers.hibernate.HibernateManager;
import managers.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PropositionStageManager extends HibernateManager<PropositionStage>
{
    /**
     * Methode permettant de récupérer toutes propositions de stage
     *
     * @param user     L'étudiant dont on désire afficher les propositions dont il est concerné (null s'il faut tout afficher)
     * @param passrole Le nom du rôle "passe partout" dont les propositions doivent être affichées à tous
     * @return liste de proposition de stage
     */
    public List<PropositionStage> fetchAll(Utilisateur user, String passrole)
    {
        Session s = HibernateUtil.getInstance().getSession();
        s.beginTransaction();

        List<PropositionStage> t = new ArrayList<>();

        if (user == null && passrole == null)
        {
            Query q = s.createQuery("from PropositionStage s");
            t = q.list();
        }
        else if (passrole == null)
        {
            Query q = s.createQuery("from PropositionStage s where s.owner.id = :id");
            q.setParameter("id", user.getId());
            t = q.list();
        }
        else if (user == null)
        {
            Query q = s.createQuery("from PropositionStage s");
            List<PropositionStage> list = q.list();

            for (PropositionStage sp : list)
                if (sp.getOwner().getRole().getNom().equals(passrole))
                    t.add(sp);
        }

        s.getTransaction().commit();
        return t;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return PropositionStage.class;
    }
}