package Myba.UI;

import DAO.*;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.ResourcesUtils;
import Server.*;
import Server.Rights.Role;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.DateRenderer;

/**
 * Created by Jo on 21/05/15.
 */
class AdminView extends MybaLayout
{
    private final NativeSelect ns = new NativeSelect("");
    private final Grid table = new Grid();

    public AdminView()
    {
        super("adminViewLayout", true, true, true, Role.PROFESSEUR);

        table.setEditorEnabled(true);

        ns.addItem("Stages");
        ns.addItem("TFEs");
        ns.addItem("Entreprises");
        ns.addItem("Étudiants");
        ns.addItem("Professeurs");
        ns.addItem("Maîtres de stage");
        ns.addItem("Présidents de jury");
        ns.addItem("Évaluations");

        ns.setNullSelectionAllowed(false);
        ns.setImmediate(true);
        ns.select("Étudiants");

        ns.addValueChangeListener(e -> nsChangeListener());

        nsChangeListener();
        table.setSelectionMode(Grid.SelectionMode.SINGLE);
        table.setImmediate(true);
        table.setSizeFull();

        final Button delete = new Button("Supprimer");
        final Button update = new Button("Modifier");
        final Link back = new Link("Retour", ResourcesUtils.getPageURL(getReferer()));

        this.addComponent(ns, "adminNs");
        this.addComponent(table, "adminTable");
        this.addComponent(delete, "adminDeleteButton");
        this.addComponent(update, "adminUpdateButton");
        this.addComponent(back, "adminBackLink");

        delete.addClickListener(clickEvent -> deleteItem());
    }

    private void deleteItem()
    {
        switch ((String) ns.getValue())
        {
            case "Stages":
                deleteStage();
                break;
            case "TFEs":
                deleteTFE();
                break;
            case "Entreprises":
                deleteEntreprise();
                break;
            case "Étudiants":
                deleteEtudiant();
                break;
            case "Professeurs":
                deleteProfesseur();
                break;
            case "Maîtres de stage":
                deleteMaitreStage();
                break;
            case "Présidents de jury":
                deletePresidentJury();
                break;
            case "Évaluations":
                deleteEvaluation();
                break;
            default:
                break;
        }
    }

    private void deleteStage()
    {
        Stage item = (Stage) table.getSelectedRow();
        StageDAO.getInstance().delete(item);
        loadStages();
    }

    private void deleteTFE()
    {
        TFE item = (TFE) table.getSelectedRow();
        TFEDAO.getInstance().delete(item);
        loadTFEs();
    }

    private void deleteEntreprise()
    {
        LieuStage item = (LieuStage) table.getSelectedRow();
        LieuStageDAO.getInstance().delete(item);
        loadEntreprises();
    }

    private void deleteEtudiant()
    {
        Etudiant item = (Etudiant) table.getSelectedRow();
        EtudiantDAO.getInstance().delete(item);
        loadEtudiants();
    }

    private void deleteProfesseur()
    {
        Professeur item = (Professeur) table.getSelectedRow();
        ProfesseurDAO.getInstance().delete(item);
        loadProfesseurs();
    }

    private void deleteMaitreStage()
    {
        MaitreStage item = (MaitreStage) table.getSelectedRow();
        MaitreStageDAO.getInstance().delete(item);
        loadMaitreStages();
    }

    private void deletePresidentJury()
    {
        PresidentJury item = (PresidentJury) table.getSelectedRow();
        PresidentJuryDAO.getInstance().delete(item);
        loadPresidentsJury();
    }

    private void deleteEvaluation()
    {
        Evaluation item = (Evaluation) table.getSelectedRow();
        EvaluationDAO.getInstance().delete(item);
        loadEvaluations();
    }

    private void nsChangeListener()
    {
        switch ((String) ns.getValue())
        {
            case "Stages":
                loadStages();
                break;
            case "TFEs":
                loadTFEs();
                break;
            case "Entreprises":
                loadEntreprises();
                break;
            case "Étudiants":
                loadEtudiants();
                break;
            case "Professeurs":
                loadProfesseurs();
                break;
            case "Maîtres de stage":
                loadMaitreStages();
                break;
            case "Présidents de jury":
                loadPresidentsJury();
                break;
            case "Évaluations":
                loadEvaluations();
                break;
            default:
                break;
        }
    }

    private void loadProfesseurs()
    {
        table.removeAllColumns();
        BeanItemContainer<Professeur> bic = new BeanItemContainer<>(Professeur.class);
        bic.addAll(ProfesseurDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("nom", "prenom");
        table.removeColumn("ID");
        table.removeColumn("TFE");
        table.removeColumn("evaluations");
        table.removeColumn("noTel");
        table.removeColumn("propositionsStage");
        table.removeColumn("roles");
        table.removeColumn("stages");
        table.removeColumn("suivisEcheances");
    }

    private void loadMaitreStages()
    {
        table.removeAllColumns();
        BeanItemContainer<MaitreStage> bic = new BeanItemContainer<>(MaitreStage.class);
        bic.addAll(MaitreStageDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("nom", "prenom", "motDePasse");
        table.removeColumn("ID");
        table.removeColumn("evaluations");
        table.removeColumn("noTel");
        table.removeColumn("roles");
        table.removeColumn("stages");
    }

    private void loadPresidentsJury()
    {
        table.removeAllColumns();
        BeanItemContainer<PresidentJury> bic = new BeanItemContainer<>(PresidentJury.class);
        bic.addAll(PresidentJuryDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("nom", "prenom");
        table.removeColumn("ID");
        table.removeColumn("defenses");
        table.removeColumn("noTel");
        table.removeColumn("roles");
    }

    private void loadStages()
    {
        table.removeAllColumns();
        BeanItemContainer<Stage> bic = new BeanItemContainer<>(Stage.class);
        bic.addAll(StageDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("dateDebut", "dateFin", "etudiant", "maitreStage", "promoteur");
        table.getColumn("dateDebut").setRenderer(new DateRenderer());
        table.getColumn("dateFin").setRenderer(new DateRenderer());
        table.removeColumn("defenses");
        table.removeColumn("echeances");
        table.removeColumn("evaluations");
        table.removeColumn("id");
        table.removeColumn("propositionStage");
        table.removeColumn("totalStage");
    }

    private void loadTFEs()
    {
        table.removeAllColumns();
        BeanItemContainer<TFE> bic = new BeanItemContainer<>(TFE.class);
        bic.addAll(TFEDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("titre", "anneeAcadDebut", "anneeAcadFin", "etudiant");
        table.removeColumn("defenses");
        table.removeColumn("echeances");
        table.removeColumn("evaluations");
        table.removeColumn("id");
        table.removeColumn("technologies");
        table.removeColumn("totalTFE");
        table.removeColumn("promoteur");
    }

    private void loadEntreprises()
    {
        table.removeAllColumns();
        BeanItemContainer<LieuStage> bic = new BeanItemContainer<>(LieuStage.class);
        bic.addAll(LieuStageDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("entreprise", "adresse", "personneContact");
        table.removeColumn("id");
    }

    private void loadEtudiants()
    {
        table.removeAllColumns();
        BeanItemContainer<Etudiant> bic = new BeanItemContainer<>(Etudiant.class);
        bic.addAll(EtudiantDAO.getInstance().findAll());

        table.setContainerDataSource(bic);
        table.setColumnOrder("matricule", "nom", "prenom", "annee", "motDePasse");
        table.removeColumn("ID");
        table.removeColumn("dispenseStage");
        table.removeColumn("dispenseTFE");
        table.removeColumn("doublant");
        table.removeColumn("echeances");
        table.removeColumn("propositionsStage");
        table.removeColumn("roles");
        table.removeColumn("suivisEcheances");
        table.removeColumn("tfe");
    }

    private void loadEvaluations()
    {
        table.removeAllColumns();
        BeanItemContainer<Evaluation> bic = new BeanItemContainer<>(Evaluation.class);
        bic.addAll(EvaluationDAO.getInstance().findAll());
        table.setContainerDataSource(bic);
        table.setColumnOrder("date", "note", "evaluateur", "commentaire");
        table.getColumn("date").setRenderer(new DateRenderer());
        table.removeColumn("id");
        table.removeColumn("tfe");
        table.removeColumn("stage");
        table.removeColumn("critere");
        table.removeColumn("defense");
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent e)
    {
        super.enter(e);

        FieldGroup fieldGroup = new FieldGroup();
        table.setEditorFieldGroup(fieldGroup);
        fieldGroup.addCommitHandler(new FieldGroup.CommitHandler()
        {
            @Override
            public void preCommit(FieldGroup.CommitEvent commitEvent)  throws FieldGroup.CommitException
            {
            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException
            {
            }
        });

    }
}
