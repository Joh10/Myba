package ui;

import beans.Defense;
import beans.Stage;
import beans.TFE;
import beans.Technologie;
import com.vaadin.data.Property;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button.ClickListener;
import managers.DefenseManager;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class PublicDefenses extends PublicDefenses_design implements View
{
    private Navigator navigateur;
    private Object elementSelected;

    public PublicDefenses()
    {
        tab_defenses.addContainerProperty("Type", String.class, null);
        tab_defenses.addContainerProperty("Etudiant", String.class, null);
        tab_defenses.addContainerProperty("Sujet", String.class, null);
        tab_defenses.addContainerProperty("Local", String.class, null);
        tab_defenses.addContainerProperty("Date", Date.class, null);
        tab_defenses.setSelectable(true);
        tab_technologies.addContainerProperty("Nom", String.class, null);
        tab_technologies.addContainerProperty("Version", String.class, null);
        tab_technologies.setSelectable(false);

        button_connexion.setIcon(FontAwesome.SIGN_IN);

        button_connexion.addClickListener((ClickListener) event -> navigateur.navigateTo(""));

        load_tab_defenses();
    }

    @Override
    public void enter(ViewChangeEvent event)
    {
        this.navigateur = event.getNavigator();

        WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
        if (currentSession.getAttribute("user") != null) navigateur.navigateTo("Dashboard");
    }

    public void load_tab_defenses()
    {
        tab_defenses.removeAllItems();

        DefenseManager defense_DB = new DefenseManager();
        List<Defense> defenseList = defense_DB.fetchAll(null);

        if(defenseList!= null)
        {
            for (Defense defense : defenseList)
            {
                Stage stage = defense.getStage();
                TFE tfe = defense.getTfe();
                String etudiant = "";
                String sujet = "";
                String type = "";
                if (tfe != null)
                {
                    sujet = tfe.getTitre();
                    etudiant = tfe.getOwner().toString();
                    type = "TFE";
                } else if (stage != null)
                {
                    sujet = stage.getPropositionStage().getSujet();
                    etudiant = stage.getOwner().toString();
                    type = "Stage";
                }
                tab_defenses.addItem(new Object[]{type, etudiant, sujet, defense.getLocal(), defense.getDate()}, defense.getId());
            }
        }

        tab_defenses.addValueChangeListener((Property.ValueChangeListener) event ->
        {
            elementSelected = tab_defenses.getValue();
            load_tab_technologies();
        });
    }

    public void load_tab_technologies()
    {
        tab_technologies.removeAllItems();
        if (elementSelected != null)
        {
            DefenseManager defense_DB = new DefenseManager();
            Defense defense = defense_DB.find((int) elementSelected);
            Stage stage = defense.getStage();
            TFE tfe = defense.getTfe();
            Set<Technologie> technologies = null;
            if (tfe != null)
            {
                technologies = tfe.getTechnologies();
            } else if (stage != null)
            {
                technologies = stage.getTechnologies();
            }

            if (technologies != null)
            {
                for (Technologie technologie : technologies)
                {
                    tab_technologies.addItem(new Object[]{technologie.getNom(), technologie.getVersion()}, technologie.getId());
                }
            }
        }
    }
}
