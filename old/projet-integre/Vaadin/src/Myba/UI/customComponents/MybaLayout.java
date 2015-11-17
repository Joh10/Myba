package Myba.UI.customComponents;

import Myba.utils.ResourcesUtils;
import Myba.utils.SessionUtils;
import Server.Rights.Role;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Image;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mixmania on 23-05-15 at 17:56.
 */

public class MybaLayout extends CustomLayout implements View
{
    private static String referer;
    private final boolean showLogo;
    private final boolean showOverlay;
    private final boolean docked;
    private final CustomLayout panel = new CustomLayout("headerLayout");
    private Image logo;
    private final ProfileButton button = new ProfileButton();
    private final ProfileOverlay overlay = new ProfileOverlay(275, 225);

    protected MybaLayout(String template, boolean showLogo, boolean showOverlay, boolean docked, Role... granted)
    {
        super(template);

        try
        {
            this.logo = new Image(null, ResourcesUtils.load("logo64.png"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        this.showLogo = showLogo;
        this.showOverlay = showOverlay;
        this.docked = docked;
        List<Role> granted1 = Arrays.asList(granted);

        panel.setStyleName("headerL");

        //Click sur le bouton on ouvre le panel
        button.addLayoutClickListener(e -> {
            if (overlay.isShown())
            {
                overlay.setVisible(false);
            } else
            {
                overlay.setVisible(true);
                overlay.setPos((int) (Page.getCurrent().getBrowserWindowWidth() - overlay.getWidth() - 20), 85);
            }
        });

        //Resize de la page
        Page.getCurrent().addBrowserWindowResizeListener(browserWindowResizeEvent -> overlay.setPos((int) (Page.getCurrent().getBrowserWindowWidth() - overlay.getWidth() - 20), 85));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent e)
    {
        overlay.setVisible(false);

        if (showLogo) panel.addComponent(logo, "header-logo");

        if (showOverlay)
        {
            button.setImage(SessionUtils.getUsername());
            button.setVisible(true);
            panel.addComponent(button, "profile");
            panel.addComponent(overlay, "profile-overlay");
        } else
        {
            button.setVisible(false);
        }

        if (showLogo || showOverlay)
        {
            if (docked)
            {
                Page.getCurrent().getStyles().add(".header-root {" +
                        "position: static;" +
                        "width: ;" +
                        "height: 1px;" +
                        "visibility: visible;" +
                        "}");
            } else
            {
                Page.getCurrent().getStyles().add(".header-root {" +
                        "position: absolute;" +
                        "visibility: hidden;" +
                        "width: 1px;" +
                        "height: 1px;" +
                        "z-index: 5;" +
                        "}");

                Page.getCurrent().getStyles().add(".headerL{" +
                        "position: absolute;" +
                        "visibility: hidden;" +
                        "width: 1px;" +
                        "height: 1px;" +
                        "}");
            }

            addComponent(panel, "header");
        }

        //Overlay title
        overlay.setNomPrenom();

        /*if (SessionUtils.getRoles().isEmpty() && !e.getViewName().equals("login"))
        {
            UI.getCurrent().getNavigator().navigateTo("login");
            return;
        }

        if (!SessionUtils.getRoles().containsAll(granted))
        {
            UI.getCurrent().getNavigator().navigateTo(referer);
            return;
        }*/

        referer = e.getViewName();
    }

    protected String getReferer()
    {
        return referer;
    }
}
