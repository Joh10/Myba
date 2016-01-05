package ui;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import ui.Connexion;
import ui.Dashboard;
import ui.MonCompte;
import ui.PublicDefenses;

/**
 * Created by Mixmania on 05-01-16 at 13:34.
 *
 */
public class Pi_webUI extends UI
{
    @Override
    public void init(VaadinRequest request)
    {
        getPage().setTitle("HERS - Gestion des stages et TFE");

        Navigator navigator = new Navigator(this, this);

        navigator.addView("", new Connexion());
        navigator.addView("Defenses", new PublicDefenses());
        navigator.addView("Dashboard", new Dashboard());
        navigator.addView("Account", new MonCompte());
    }
}
