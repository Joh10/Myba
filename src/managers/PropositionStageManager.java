package managers;

import beans.PropositionStage;
import beans.Utilisateur;
import managers.hibernate.HibernateConnector;
import managers.hibernate.HibernateManager;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

public class PropositionStageManager extends HibernateManager<PropositionStage>
{
	/**
	 * Methode permettant de récupérer toutes propositions de stage
	 * @param user		L'étudiant dont on désire afficher les propositions dont il est concerné (null s'il faut tout afficher)
	 * @param passrole	Le nom du rôle "passe partout" dont les propositions doivent être affichées à tous
	 * @return liste de proposition de stage
	 */
    public List<PropositionStage> fetchAll(Utilisateur user, String passrole)
    {
        if(user == null && passrole == null)
        {
            Query q = HibernateConnector.getInstance().getSession().createQuery("from PropositionStage s");
            return (List<PropositionStage>) q.list();
        }
        else if(passrole == null)
        {
            Query q = HibernateConnector.getInstance().getSession().createQuery("from PropositionStage s where s.owner.id = :id");
            q.setParameter("id", user.getId());
            return (List<PropositionStage>) q.list();
        }
        else if(user == null)
        {
            Query q = HibernateConnector.getInstance().getSession().createQuery("from PropositionStage s");
            List<PropositionStage> list = q.list();
            List<PropositionStage> t = new ArrayList<>();

            for(PropositionStage s : list)
                if(s.getOwner().getRole().getNom().equals(passrole))
                    t.add(s);

            return t;
        }

        return null;
    }

    @Override
    protected Class<?> getEntityClass()
    {
        return PropositionStage.class;
    }
}