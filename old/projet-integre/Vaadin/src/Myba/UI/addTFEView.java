package Myba.UI;

import DAO.EtudiantDAO;
import DAO.TechnologieDAO;
import Myba.UI.customComponents.MybaLayout;
import Server.Etudiant;
import Server.Technologie;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Jo on 11/06/15.
 */
class AddTFEView extends MybaLayout
{
    public AddTFEView()
    {
        super("addTFEViewLayout", true, true, true);

        //TextFields
        TextField title = new TextField();
        TextField startYear = new TextField();

        title.setWidth(100.0f, Unit.PERCENTAGE);
        startYear.setWidth(10.0f, Unit.PERCENTAGE);

        title.setInputPrompt("Titre");
        startYear.setInputPrompt("Année début");

        this.addComponent(title, "title");
        this.addComponent(startYear, "startYear");

        //Boutons
        Button add = new Button("Ajouter");
        Button reset = new Button("Vider les champs");
        Button back = new Button("Retour");

        this.addComponent(add, "addButton");
        this.addComponent(reset, "resetButton");
        this.addComponent(back, "backButton");

        //Ajout des informations au ns
        NativeSelect ns = new NativeSelect("");

        List<Technologie> techno = TechnologieDAO.getInstance().findAll();
        techno.stream().filter(t -> t.getNom() != null && !t.getNom().isEmpty()).forEach(t -> ns.addItem(t.getNom()));

        ns.setNullSelectionAllowed(false);
        ns.setImmediate(true);
        ns.select(techno.get(0));

        Grid table = new Grid();

        ns.addValueChangeListener(e -> {
            BeanItemContainer<Etudiant> bic = new BeanItemContainer<>(Etudiant.class);
            bic.addAll(EtudiantDAO.getInstance().findAll());
            table.setSelectionMode(Grid.SelectionMode.SINGLE);
            table.setContainerDataSource(bic);
            table.setSizeFull();
        });

        this.addComponent(ns, "ns");

        //Upload file
        // Show uploaded file in this placeholder
        final Embedded image = new Embedded("Uploaded Image");
        image.setVisible(false);

        // Implement both receiver that saves upload in a file and
        // listener for successful upload

        class ImageUploader implements Upload.Receiver, Upload.SucceededListener
        {
            public File file;

            public OutputStream receiveUpload(String filename, String mimeType)
            {
                // Create upload stream
                FileOutputStream fos; // Stream to write to
                try
                {
                    // Open the file for writing.
                    file = new File("/tmp/uploads/" + filename);
                    fos = new FileOutputStream(file);
                }
                catch (final java.io.FileNotFoundException e)
                {
                    new Notification("Could not open file<br/>", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
                    return null;
                }
                return fos; // Return the output stream to write to
            }

            public void uploadSucceeded(Upload.SucceededEvent event)
            {
                // Show the uploaded file in the image viewer
                image.setVisible(true);
                image.setSource(new FileResource(file));
            }
        }


        ImageUploader receiver = new ImageUploader();

        // Create the upload with a caption and set receiver later
        Upload upload = new Upload("Upload Image Here", receiver);
        upload.setButtonCaption("Start Upload");
        upload.addSucceededListener(receiver);

        // Put the components in a panel
        Panel panel = new Panel("Cool Image Storage");
        Layout panelContent = new VerticalLayout();
        panelContent.addComponents(upload, image);
        panel.setContent(panelContent);

        this.addComponent(upload, "upload");
    }


}

