package Myba.UI;

import DAO.EcheanceDAO;
import DAO.PropositionStageDAO;
import DAO.StageDAO;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.SessionUtils;
import Server.Echeance;
import Server.PropositionStage;
import Server.Rights.Role;
import Server.Stage;
import Server.Technologie;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by Jo on 03/06/15.
 */
public class MesStagesView extends MybaLayout
{
    private final Button choose = new Button("Choisir");
    private final Button back = new Button("Retour");
    private final Button add = new Button("Ajouter");
    private final Button update = new Button("Modifier");
    private final Button delete = new Button("Supprimer");
    private final Label title;
    private final Label promotteur;
    private final Label entreprise;
    //Etudiant
    private final Table tableEtat = new Table();
    private final Table echeance = new Table();
    private final Table proposals = new Table();
    private final Table follows = new Table();

    public MesStagesView()
    {
        super(null, true, true, true);
        setSizeFull();
        setWidth("100%");

        title = new Label();
        promotteur = new Label();
        entreprise = new Label();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent e)
    {
        super.enter(e);

        echeance.removeAllItems();
        tableEtat.removeAllItems();
        follows.removeAllItems();
        proposals.removeAllItems();

        if (SessionUtils.getRoles().contains(Role.ETUDIANT))
        {
            Stage s = StageDAO.getInstance().findFromEtudiant(SessionUtils.getUsername());

            if (s != null)
            {
                setTemplateName("mesStagesStudentAcceptedViewLayout");

                title.setValue(s.getPropositionStage().getSujet());
                entreprise.setValue(s.getPropositionStage().getLieuStage().getEntreprise());
                promotteur.setValue(s.getPromoteur().getPrenom() + " " + s.getPromoteur().getNom());

                tableEtat.setSelectable(true);
                tableEtat.setMultiSelect(false);
                tableEtat.setImmediate(true);

                this.addComponent(title, "title");
                this.addComponent(entreprise, "entreprise");
                this.addComponent(promotteur, "promotteur");

                loadEcheances();

                this.addComponent(echeance, "tableEcheance");
                this.addComponent(choose, "chooseButton");
                this.addComponent(back, "backButton");
            } else
            {
                setTemplateName("mesStagesStudentNotAcceptedViewLayout");

                loadEtats();
                tableEtat.setSelectable(true);
                tableEtat.setMultiSelect(false);
                tableEtat.setImmediate(true);

                this.addComponent(tableEtat, "tableEtat");
                this.addComponent(choose, "chooseButton");
                this.addComponent(back, "backButton");
            }
        }

        if (SessionUtils.getRoles().contains(Role.PROFESSEUR))
        {
            setTemplateName("mesStagesTeacherViewLayout");

            loadProposals();

            proposals.setSelectable(true);
            proposals.setMultiSelect(false);
            proposals.setImmediate(true);

            this.addComponent(proposals, "tableProposals");
            this.addComponent(add, "addButton");
            this.addComponent(update, "updateButton");
            this.addComponent(delete, "deleteButton");

            loadFollows();
            follows.setSelectable(true);
            follows.setMultiSelect(false);
            follows.setImmediate(true);

            this.addComponent(follows, "tableFollows");
            this.addComponent(choose, "consultButton");
            this.addComponent(back, "backButton");

        }

        if (SessionUtils.getRoles().contains(Role.MAITRESTAGE))
        {
            setTemplateName("mesStagesMaitreViewLayout");
            loadFollows();

            follows.setSelectable(true);
            follows.setMultiSelect(false);
            follows.setImmediate(true);

            this.addComponent(follows, "tableFollows");

            final Button noter = new Button("Noter");
            final Button back = new Button("Retour");

            this.addComponent(noter, "noteButton");
            this.addComponent(back, "backButton");
        }
    }

    private void loadEcheances()
    {
        BeanItemContainer<Echeance> bic = new BeanItemContainer<>(Echeance.class);
        bic.addAll(EcheanceDAO.getInstance().findAll());
        echeance.setContainerDataSource(bic);
        tableAdjustHeight();
    }

    private void loadEtats()
    {
        BeanItemContainer<PropositionStage> bic = new BeanItemContainer<>(PropositionStage.class);
        bic.addAll(PropositionStageDAO.getInstance().findFromEtudiant(SessionUtils.getUsername()));
        tableEtat.setContainerDataSource(bic);
        tableAdjustHeight();
    }

    private void loadProposals()
    {
        proposals.addContainerProperty("Sujet", String.class, null);
        proposals.addContainerProperty("Technologies", String.class, null);
        proposals.addContainerProperty("Lieu de stage", String.class, null);
        proposals.addContainerProperty("Chemin Annexe", String.class, null);

        for (PropositionStage p : PropositionStageDAO.getInstance().findFromEvaluateur(SessionUtils.getUsername()))
            proposals.addItem(new Object[]{p.getSujet(), Arrays.toString(p.getTechnologies().toArray(new Technologie[p.getTechnologies().size()])), p.getLieuStage().getAdresse().toString(), p.getCheminAnnexe()}, null);

        tableAdjustHeight();
    }


    //Todo en fonction du professeur, du prési ou du maitre de stage en passant le role en paramètre
    private void loadFollows()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        follows.addContainerProperty("Titre", String.class, null);
        follows.addContainerProperty("Etudiant", String.class, null);
        follows.addContainerProperty("Date début", String.class, null);
        follows.addContainerProperty("Date fin", String.class, null);
        follows.addContainerProperty("Commentaire", String.class, null);

        if (SessionUtils.getRoles().contains(Role.MAITRESTAGE))
        {
            for (Stage s : StageDAO.getInstance().findFromMaitreStage(SessionUtils.getUsername()))
                follows.addItem(new Object[]{s.getPropositionStage().getSujet(), s.getEtudiant().toString(), sdf.format(s.getDateDebut()), sdf.format(s.getDateFin()), s.getCommentaire()}, null);

        } else
        {
            for (Stage s : StageDAO.getInstance().findFromProfesseur(SessionUtils.getUsername()))
                follows.addItem(new Object[]{s.getPropositionStage().getSujet(), s.getEtudiant().toString(), sdf.format(s.getDateDebut()), sdf.format(s.getDateFin()), s.getCommentaire()}, null);

        }
        tableAdjustHeight();
    }

    private void tableAdjustHeight()
    {
        tableEtat.setWidth("100%");
        proposals.setWidth("100%");
        follows.setWidth("100%");

        tableEtat.setPageLength(tableEtat.getItemIds().size());
        follows.setPageLength(tableEtat.getItemIds().size());
        proposals.setPageLength(tableEtat.getItemIds().size());
    }
}
