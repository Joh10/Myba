package Myba.UI;

import DAO.EtudiantDAO;
import DAO.MaitreStageDAO;
import DAO.PresidentJuryDAO;
import DAO.ProfesseurDAO;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.ResourcesUtils;
import Myba.utils.SessionUtils;
import Server.Etudiant;
import Server.MaitreStage;
import Server.PresidentJury;
import Server.Professeur;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public class LoginView extends MybaLayout
{
    private final TextField username;
    private final PasswordField password;

    public LoginView()
    {
        super("loginViewLayout", true, false, true);

        //Redirect the user to the home page if he is already logged
        if (SessionUtils.isAlreadyLogged()) UI.getCurrent().getNavigator().navigateTo("home");

        username = new TextField();
        password = new PasswordField();
        Button ok = new Button("Se connecter");
        Button reset = new Button("Vider les champs");
        Link forgetpassword = new Link("Mot de passe oublié..", ResourcesUtils.getPageURL("forgetPassword"));

        username.setWidth(100.0f, Unit.PERCENTAGE);
        password.setWidth(100.0f, Unit.PERCENTAGE);
        username.setInputPrompt("Adresse Email");
        password.setInputPrompt("             ");

        this.addComponent(username, "username");
        this.addComponent(password, "password");
        this.addComponent(ok, "okbutton");
        this.addComponent(reset, "resetbutton");
        this.addComponent(forgetpassword, "forgetlink");

        ok.addClickListener(clickEvent -> {
            //Check
            if (EtudiantDAO.getInstance().findById(username.getValue()) != null)
            {
                if (EtudiantDAO.getInstance().connect(username.getValue(), password.getValue()))
                {
                    Etudiant e = EtudiantDAO.getInstance().findById(username.getValue());
                    SessionUtils.add("Roles", e.getRoles());
                    SessionUtils.add("Name", e.getNom());
                    SessionUtils.add("Surname", e.getPrenom());
                } else
                {
                    Notification.show("Erreur", "Le mot de passe est incorrect", Notification.Type.TRAY_NOTIFICATION);
                    return;
                }
            } else if (ProfesseurDAO.getInstance().findById(username.getValue()) != null)
            {
                if (ProfesseurDAO.getInstance().connect(username.getValue(), password.getValue()))
                {
                    Professeur e = ProfesseurDAO.getInstance().findById(username.getValue());
                    SessionUtils.add("Roles", e.getRoles());
                    SessionUtils.add("Name", e.getNom());
                    SessionUtils.add("Surname", e.getPrenom());
                } else
                {
                    Notification.show("Erreur", "Le mot de passe est incorrect", Notification.Type.TRAY_NOTIFICATION);
                    return;
                }
            } else if (MaitreStageDAO.getInstance().findById(username.getValue()) != null)
            {
                if (MaitreStageDAO.getInstance().connect(username.getValue(), password.getValue()))
                {
                    MaitreStage e = MaitreStageDAO.getInstance().findById(username.getValue());
                    SessionUtils.add("Roles", e.getRoles());
                    SessionUtils.add("Name", e.getNom());
                    SessionUtils.add("Surname", e.getPrenom());
                } else
                {
                    Notification.show("Erreur", "Le mot de passe est incorrect", Notification.Type.TRAY_NOTIFICATION);
                    return;
                }
            } else if (PresidentJuryDAO.getInstance().findById(username.getValue()) != null)
            {
                if (PresidentJuryDAO.getInstance().connect(username.getValue(), password.getValue()))
                {
                    PresidentJury e = PresidentJuryDAO.getInstance().findById(username.getValue());
                    SessionUtils.add("Roles", e.getRoles());
                    SessionUtils.add("Name", e.getNom());
                    SessionUtils.add("Surname", e.getPrenom());
                } else
                {
                    Notification.show("Erreur", "Le mot de passe est incorrect", Notification.Type.TRAY_NOTIFICATION);
                    return;
                }
            } else
            {
                Notification.show("Erreur", "Cette adresse email n'est pas attribuée", Notification.Type.TRAY_NOTIFICATION);
                return;
            }

            //ADD PAGES
            Navigator navigator = UI.getCurrent().getNavigator();

            navigator.addView("comment", new CommentView());
            navigator.addView("admin", new AdminView());
            navigator.addView("addStage", new AddStageView());
            navigator.addView("addTFE", new AddTFEView());
            navigator.addView("echeance", new EcheanceView());
            navigator.addView("finalNote", new NoteFinaleView());
            navigator.addView("addEcheance", new AddEcheanceView());
            navigator.addView("mesStages", new MesStagesView());
            navigator.addView("mesTFEs", new MesTFEsView());
            navigator.addView("myAccount", new MyAccountView());

            SessionUtils.add("Username", username.getValue());
            SessionUtils.add("Password", password.getValue());
            navigator.navigateTo("home");
        });

        reset.addClickListener(clickEvent -> {
            //FIXME BUG SUR LE PASSWORD CLEAR
            username.clear();
            password.clear();
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent e)
    {
        super.enter(e);
        username.clear();
        password.clear();
    }
}
