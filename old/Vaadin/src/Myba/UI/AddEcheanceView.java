package Myba.UI;

import Myba.UI.customComponents.FileUploader;
import Myba.UI.customComponents.MybaLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

import java.io.File;


/**
 * Created by Jo on 12/06/15.
 */

class AddEcheanceView extends MybaLayout
{

    public AddEcheanceView()
    {
        super("addEcheanceViewLayout", true, true, true);

        TextField title = new TextField();
        TextField endDate = new TextField();
        TextArea description = new TextArea();

        Button addButton = new Button("Ajouter");
        Button backButton = new Button("Retour");

        title.setInputPrompt("Titre");
        endDate.setInputPrompt("Date de fin");
        description.setInputPrompt("Description");

        description.setWidth("100%");
        description.setHeight("160px");

        //FileUploader
        FileUploader uploader = new FileUploader()
        {
            @Override
            public void uploadSucceeded(File file, SucceededEvent event)
            {
                UI.getCurrent().getNavigator().navigateTo(getReferer());
            }
        };

        this.addComponent(title, "title");
        this.addComponent(endDate, "endDate");
        this.addComponent(description, "description");
        this.addComponent(uploader, "upload");

        this.addComponent(addButton, "addButton");
        this.addComponent(backButton, "backButton");

    }
}
