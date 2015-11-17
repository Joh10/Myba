package Myba.UI;

import Myba.UI.customComponents.MybaLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Created by Mixmania on 02-06-15 at 15:15.
 */
class ForgetPasswordView extends MybaLayout
{

    public ForgetPasswordView()
    {
        super("forgetPasswordLayout", true, false, true);

        TextField username = new TextField();
        Button ok = new Button("Valider");
        Button goback = new Button("Retour");

        username.setWidth(100.0f, Unit.PERCENTAGE);
        username.setInputPrompt("Adresse Email");

        this.addComponent(username, "username");
        this.addComponent(ok, "okbutton");
        this.addComponent(goback, "gobackbutton");

        goback.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo("login"));

        ok.addClickListener(event -> {
            //FIXME
            /*
            //Check
            if (EtudiantDAO.getInstance().findById(username.getValue()) != null)
            {
                Etudiant e = EtudiantDAO.getInstance().findById(username.getValue());
                //FIXME SEND MAIL
                e.setMotDePasse(new BigInteger(130, new SecureRandom()).toString(32));
                EtudiantDAO.getInstance().update(e);
            } else if (EvaluateurDAO.getInstance().findById(username.getValue()) != null)
            {
                Evaluateur e = EvaluateurDAO.getInstance().findById(username.getValue());
                //FIXME SEND MAIL
                e.setMotDePasse(new BigInteger(130, new SecureRandom()).toString(32));
                EvaluateurDAO.getInstance().update(e);
            } else
            {
                Notification.show("Erreur", "Cette adresse email n'est pas attribuée", Notification.Type.TRAY_NOTIFICATION);
                return;
            }*/

            UI.getCurrent().getNavigator().navigateTo("login");
        });
    }
}
