package Myba.UI;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Mixmania on 20-05-15 at 18:04.
 */

@Theme("myba")
@PreserveOnRefresh
public class MybaUI extends UI
{
    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        //Check si les dossiers existent
        File temp = new File("/tmp/uploads/");

        if (!temp.exists()) temp.mkdirs();

        //Création du navigator
        Navigator navigator = new Navigator(this, this);

        try
        {
            navigator.addView("home", new HomeView());
            navigator.addView("login", new LoginView());
            navigator.addView("disconnect", new DisconnectView());
            navigator.addView("forgetPassword", new ForgetPasswordView());
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MybaUI.class, widgetset = "com.vaadin.tapio.googlemaps.demo.DemoWidgetset")
    public static class Servlet extends VaadinServlet
    {
    }
}
