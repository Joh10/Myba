package Myba.UI;

import DAO.EtudiantDAO;
import DAO.TechnologieDAO;
import Myba.UI.customComponents.FileUploader;
import Myba.UI.customComponents.MybaLayout;
import Server.Etudiant;
import Server.Technologie;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import java.io.File;
import java.util.List;

/**
 * Created by Jo on 11/06/15.
 */

class AddStageView extends MybaLayout
{

    public AddStageView()
    {
        super("addStageViewLayout", true, true, true);

        //TextFields
        TextField title = new TextField();
        TextField location = new TextField();
        TextField firstname = new TextField();
        TextField lastname = new TextField();
        TextField telephoneNumber = new TextField();
        TextField mail = new TextField();
        TextField entreprise = new TextField();
        TextField street = new TextField();
        TextField num = new TextField();
        TextField CP = new TextField();
        TextField boite = new TextField();
        TextField city = new TextField();
        TextField country = new TextField();
        Button add = new Button("Ajouter");
        Button reset = new Button("Vider les champs");
        Button back = new Button("Retour");

        //Style
        title.setWidth(100.0f, Unit.PERCENTAGE);
        location.setWidth("90%");
        firstname.setWidth("90%");
        lastname.setWidth("90%");
        telephoneNumber.setWidth("90%");
        mail.setWidth("90%");
        entreprise.setWidth("90%");
        street.setWidth("90%");
        num.setWidth("90%");
        boite.setWidth("90%");
        city.setWidth("90%");
        CP.setWidth("90%");
        country.setWidth("90%");

        title.setInputPrompt("Titre");
        location.setInputPrompt("Lieu");
        firstname.setInputPrompt("Nom");
        lastname.setInputPrompt("Prénom");
        telephoneNumber.setInputPrompt("Numéro de téléphone");
        mail.setInputPrompt("Adresse email");
        entreprise.setInputPrompt("Nom de l'entreprise");
        street.setInputPrompt("Rue");
        num.setInputPrompt("Numéro");
        CP.setInputPrompt("Code postal");
        boite.setInputPrompt("Boîte");
        city.setInputPrompt("Ville");
        country.setInputPrompt("Pays");

        //List check
        NativeSelect ns = new NativeSelect("");

        List<Technologie> techno = TechnologieDAO.getInstance().findAll();
        techno.stream().filter(t -> t.getNom() != null && !t.getNom().isEmpty()).forEach(t -> ns.addItem(t.getNom()));

        ns.setNullSelectionAllowed(false);
        ns.setImmediate(true);
        //  ns.select(techno.get(0));

        //Grid
        Grid table = new Grid();

        ns.addValueChangeListener(e -> {
            BeanItemContainer<Etudiant> bic = new BeanItemContainer<>(Etudiant.class);
            bic.addAll(EtudiantDAO.getInstance().findAll());
            table.setSelectionMode(Grid.SelectionMode.SINGLE);
            table.setContainerDataSource(bic);
            table.setSizeFull();
        });

        //FileUploader
        FileUploader uploader = new FileUploader()
        {
            @Override
            public void uploadSucceeded(File file, SucceededEvent event)
            {
                UI.getCurrent().getNavigator().navigateTo(getReferer());
            }
        };

        add.addClickListener(event -> uploader.submitUpload());

        this.addComponent(title, "title");
        this.addComponent(location, "location");
        this.addComponent(firstname, "firstname");
        this.addComponent(lastname, "lastname");
        this.addComponent(telephoneNumber, "telephoneNumber");
        this.addComponent(mail, "mail");
        this.addComponent(entreprise, "entreprise");
        this.addComponent(street, "street");
        this.addComponent(num, "num");
        this.addComponent(CP, "CP");
        this.addComponent(boite, "boite");
        this.addComponent(city, "city");
        this.addComponent(country, "country");
        this.addComponent(add, "addButton");
        this.addComponent(reset, "resetButton");
        this.addComponent(back, "backButton");
        this.addComponent(ns, "ns");
        this.addComponent(uploader, "upload");
    }
}

