package Myba.UI;

import Myba.UI.customComponents.FileUploader;
import Myba.UI.customComponents.MybaLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;

import java.io.File;

/**
 * Created by Jo on 12/06/15.
 */

class EcheanceView extends MybaLayout
{
    public EcheanceView()
    {
        super("echeanceViewLayout", true, true, true);

        final Button add = new Button("Poster");
        final Button back = new Button("Retour");
        final TextArea comment = new TextArea();
        final Label title = new Label();
        final Label startDate = new Label();
        final Label endDate = new Label();

        comment.setWidth("100%");
        comment.setHeight("160px");

        //FileUploader
        FileUploader uploader = new FileUploader()
        {
            @Override
            public void uploadSucceeded(File file, SucceededEvent event)
            {

            }
        };

        this.addComponent(title, "titleLabel");
        this.addComponent(startDate, "startDateLabel");
        this.addComponent(endDate, "endDateLabel");
        this.addComponent(comment, "comment");
        this.addComponent(uploader, "uploader");
        this.addComponent(add, "addButton");
        this.addComponent(back, "backButton");

    }
}
