package Myba.UI;

import Myba.UI.customComponents.MybaLayout;
import Myba.utils.ResourcesUtils;
import com.vaadin.ui.*;

/**
 * Created by Mixmania on 11-06-15 at 11:57.
 */

class NoteFinaleView extends MybaLayout
{

    public NoteFinaleView()
    {
        super("noteFinaleViewLayout", true, true, true);

        Link back = new Link(null, ResourcesUtils.getPageURL("home"));
        TextField note = new TextField("");
        Label noteMax = new Label("/20");
        TextArea comment = new TextArea();
        Button ok = new Button("Confirmer");
        Button reset = new Button("Vider les champs");

        note.setWidth("10%");
        comment.setWidth("100%");
        comment.setHeight("160px");

        this.addComponent(back, "notefinal-retour");
        this.addComponent(note, "notefinal-note");
        this.addComponent(noteMax, "notefinal-notetotal");
        this.addComponent(comment, "notefinal-commentaire");
        this.addComponent(ok, "notefinal-okbutton");
        this.addComponent(reset, "notefinal-resetbutton");

        this.setWidth("100%");
        comment.setWidth("100%");
        this.setSizeFull();
    }
}
