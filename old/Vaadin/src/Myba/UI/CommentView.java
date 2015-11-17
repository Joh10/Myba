package Myba.UI;

import Myba.UI.customComponents.MybaLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;

/**
 * Created by Jo on 21/05/15.
 */
class CommentView extends MybaLayout
{
    public CommentView()
    {
        super("commentViewLayout", true, true, true);

        final TextArea comment = new TextArea("Commentaire:");
        final Button add = new Button("Ajouter");
        final Button back = new Button("Retour");

        comment.setWidth("100%");
        comment.setHeight("160px");

        this.addComponent(comment, "comment");
        this.addComponent(add, "addComment");
        this.addComponent(back, "backPage");

        add.addClickListener(clickEvent -> MybaUI.getCurrent().getNavigator().navigateTo("home"));
    }
}
