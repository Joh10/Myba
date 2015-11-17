package Myba.UI;

import DAO.EcheanceDAO;
import DAO.TFEDAO;
import DAO.relations.SurveillerDAO;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.SessionUtils;
import Server.Echeance;
import Server.Rights.Role;
import Server.TFE;
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

public class MesTFEsView extends MybaLayout
{
    private final Button consult = new Button("Consulter");
    private final Button back = new Button("Retour");
    private final Label title;
    private final Label promotteur;
    private final Table echeance = new Table();
    private final Table follows = new Table();

    public MesTFEsView()
    {
        super(null, true, true, true);

        setSizeFull();
        setWidth("100%");
        setHeight("100%");

        title = new Label();
        promotteur = new Label();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent e)
    {
        super.enter(e);

        if (SessionUtils.getRoles().contains(Role.ETUDIANT))
        {
            TFE t = TFEDAO.getInstance().findFromEtudiant(SessionUtils.getUsername());

            if (t != null)
            {
                setTemplateName("mesTFEsStudentViewLayout");

                title.setValue(t.getTitre());
                promotteur.setValue(t.getPromoteur() + " ");

                loadEcheances();
                this.addComponent(title, "title");
                this.addComponent(promotteur, "promotteur");

                echeance.setSelectable(true);
                echeance.setMultiSelect(false);
                echeance.setImmediate(true);

                this.addComponent(echeance, "tableEcheance");
                this.addComponent(consult, "chooseButton");
                this.addComponent(back, "backButton");
            } else
            {
                setTemplateName("notTFEsStudentViewLayout");
                this.addComponent(back, "backButton");
            }

        }

        if (SessionUtils.getRoles().contains(Role.PROFESSEUR))
        {
            setTemplateName("mesTFEsTeacherViewLayout");

            loadFollows();

            follows.setSelectable(true);
            follows.setMultiSelect(false);
            follows.setImmediate(true);

            this.addComponent(follows, "tableFollows");
            this.addComponent(consult, "consultButton");
            this.addComponent(back, "backButton");

        }

        if (SessionUtils.getRoles().contains(Role.PRESIDENTJURY))
        {
            setTemplateName("mesTFEsPresiViewLayout");

            loadFollows();
            follows.setSelectable(true);
            follows.setMultiSelect(false);
            follows.setImmediate(true);

            this.addComponent(follows, "tableFollows");
            this.addComponent(consult, "noteButton");
            this.addComponent(back, "backButton");
        }
    }

    private void loadEcheances()
    {
        BeanItemContainer<Echeance> bic = new BeanItemContainer<>(Echeance.class);
        bic.addAll(EcheanceDAO.getInstance().findAll());
        echeance.setContainerDataSource(bic);
        tableAdjustHeight(echeance);
    }

    private void loadFollows()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

        follows.addContainerProperty("Titre", String.class, null);
        follows.addContainerProperty("Etudiant", String.class, null);
        follows.addContainerProperty("Technologies", String.class, null);
        follows.addContainerProperty("Année début", String.class, null);
        follows.addContainerProperty("Année fin", String.class, null);

        for (TFE t : SurveillerDAO.getInstance().findTFEs(SessionUtils.getUsername()))
            follows.addItem(new Object[]{t.getTitre(), t.getEtudiant().toString(), Arrays.toString(t.getTechnologies().toArray(new Technologie[t.getTechnologies().size()])), sdf.format(t.getAnneeAcadDebut()), sdf.format(t.getAnneeAcadFin())}, null);

        tableAdjustHeight(follows);
    }

    private void tableAdjustHeight(Table table)
    {
        //table.setSizeFull();
        table.setPageLength(table.getItemIds().size());
        table.markAsDirty();
        table.setWidth("100%");
    }
}
