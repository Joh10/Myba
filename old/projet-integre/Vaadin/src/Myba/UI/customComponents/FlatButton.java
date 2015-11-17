package Myba.UI.customComponents;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
class FlatButton extends VerticalLayout
{
    public FlatButton()
    {
        Label label = new Label("", ContentMode.HTML);
        setWidth("100%");
        setHeight("100%");
        addComponent(label);
    }
}