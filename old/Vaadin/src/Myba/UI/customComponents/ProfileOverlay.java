package Myba.UI.customComponents;

import Myba.utils.ResourcesUtils;
import Myba.utils.SessionUtils;
import Server.Rights.Role;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

import java.io.FileNotFoundException;

/**
 * Created by Mixmania on 23-05-15 at 20:50.
 */
class ProfileOverlay extends CustomLayout
{
    private final int width;
    private final int height;
    private boolean isVisible;
    private final Label title = new Label();

    //TODO ADD
    public ProfileOverlay(int width, int height)
    {
        super("profileOverlayLayout");
        this.width = width;
        this.height = height;

        Page.getCurrent().getStyles().add(".profileOverlay {" +
                "background-color: white;" +
                "visibility: hidden;" +
                "width: 1px;" +
                "height: 1px;" +
                "display: inline-block;" +
                "border: 1px solid #AAA;" +
                "-webkit-box-shadow: 0px 0px 20px 2px rgba(0,0,0,0.3);" +
                "-moz-box-shadow: 0px 0px 20px 2px rgba(0,0,0,0.3);" +
                "box-shadow: 0px 0px 20px 2px rgba(0,0,0,0.3);" +
                "z-index: 15;" +
                "}");

        FlatButton closeButton = new FlatButton();

        try
        {
            Link myAccountLink = new Link("Mon compte", ResourcesUtils.getPageURL("myAccount"));
            Image myAccountImage = new Image(null, ResourcesUtils.load("account.png"));
            Link myStageLink = new Link("Mon stage", ResourcesUtils.getPageURL("mesStages"));
            Image myStageImage = new Image(null, ResourcesUtils.load("mystage.png"));
            Link myTFELink = new Link("Mon TFE", ResourcesUtils.getPageURL("mesTFEs"));
            Image myTFEImage = new Image(null, ResourcesUtils.load("tfe.png"));
            Link disconnectLink = new Link("Se déconnecter", ResourcesUtils.getPageURL("disconnect"));
            Image disconnectImage = new Image(null, ResourcesUtils.load("disconnect.png"));

            closeButton.addLayoutClickListener(layoutClickEvent -> ProfileOverlay.this.setVisible(false));

            addComponent(title, "header");
            addComponent(myAccountLink, "myAccountBoxText");
            addComponent(myAccountImage, "myAccountBoxLogo");
            addComponent(myStageLink, "myStageBoxText");
            addComponent(myStageImage, "myStageBoxLogo");
            addComponent(myTFELink, "myTFEBoxText");
            addComponent(myTFEImage, "myTFEBoxLogo");
            addComponent(disconnectLink, "disconnectBoxText");
            addComponent(disconnectImage, "disconnectBoxLogo");
            addComponent(closeButton, "closeButton");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void setVisible(boolean visible)
    {
        Page.Styles styles = Page.getCurrent().getStyles();
        this.isVisible = visible;

        if (SessionUtils.getRoles().contains(Role.PROFESSEUR))
        {
            Link adminLink = new Link("Administration", ResourcesUtils.getPageURL("admin"));
            Image adminImage = null;
            try
            {
                adminImage = new Image(null, ResourcesUtils.load("admin.png"));
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            addComponent(adminLink, "adminBoxText");
            addComponent(adminImage, "adminBoxLogo");
        } else
        {
            removeComponent("adminBoxText");
            removeComponent("adminBoxLogo");


        }

        if (visible)
        {
            styles.add(".profileOverlay {" +
                    "position: absolute;" +
                    "visibility: visible;" +
                    "width: " + width + "px;" +
                    "height: " + height + "px;" +
                    "z-index: 11;" +
                    "}");
        } else
        {
            styles.add(".profileOverlay {" +
                    "visibility: hidden;" +
                    "width: 1px;" +
                    "height: 1px;" +
                    "}");
        }
    }

    @Override
    public float getWidth()
    {
        return width;
    }

    @Override
    public float getHeight()
    {
        return height;
    }

    public void setPos(int x, int y)
    {
        Page.Styles styles = Page.getCurrent().getStyles();

        styles.add(".profileOverlay {" +
                "top: " + y + "px;" +
                "left: " + x + "px;" +
                "}");
    }

    public boolean isShown()
    {
        return isVisible;
    }

    public void setNomPrenom()
    {
        title.setValue(SessionUtils.getNomPrenom());
    }
}
