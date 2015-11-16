package Myba.UI.customComponents;

import Myba.utils.ResourcesUtils;
import com.vaadin.server.Page;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

import java.io.FileNotFoundException;

@SuppressWarnings("serial")
class ProfileButton extends VerticalLayout
{
    private final Image label;

    public ProfileButton()
    {
        label = new Image();

        setWidth("100%");
        setHeight("100%");
        addComponent(label);

        Page.getCurrent().getStyles().add(".header-button {" +
                "width:1px;" +
                "height:1px;" +
                "position: absolute;" +
                "visibility: hidden;" +
                "}");
    }

    @Override
    public void setVisible(boolean visible)
    {
        if (visible)
        {
            Page.getCurrent().getStyles().add(".header-button {" +
                    "visibility:visible;" +
                    "position: static;" +
                    "width:64px;" +
                    "height:64px;" +
                    "}");
        } else
        {
            Page.getCurrent().getStyles().add(".header-button {" +
                    "visibility:hidden;" +
                    "position: absolute;" +
                    "width:1px;" +
                    "height:1px;" +
                    "}");
        }
    }

    public void setImage(String fileName)
    {
        try
        {
            this.label.setSource(ResourcesUtils.load(fileName));
        }
        catch (FileNotFoundException e)
        {
            try
            {
                this.label.setSource(ResourcesUtils.load("account64.png"));
            }
            catch (FileNotFoundException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}