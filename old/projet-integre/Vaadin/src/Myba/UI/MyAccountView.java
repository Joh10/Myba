package Myba.UI;

import DAO.EtudiantDAO;
import DAO.MaitreStageDAO;
import DAO.PresidentJuryDAO;
import DAO.ProfesseurDAO;
import Myba.UI.customComponents.FileUploader;
import Myba.UI.customComponents.MybaLayout;
import Myba.utils.SessionUtils;
import Server.Etudiant;
import Server.MaitreStage;
import Server.PresidentJury;
import Server.Professeur;
import Server.Rights.Role;
import com.vaadin.ui.*;

import java.io.File;

/**
 * Created by Jo on 14/06/15.
 */
class MyAccountView extends MybaLayout
{
    private final PasswordField newPassword;
    private final PasswordField newPassword2;
    private final PasswordField oldPassword;

    public MyAccountView()
    {
        super("myAccountViewLayout", true, true, true);

        Label email = new Label();
        Button update = new Button("Modifier");
        Button save = new Button("Enregistrer");
        TextField newEmail = new TextField();
        newEmail.setInputPrompt("Entrez la nouvelle adresse email");

        Label password = new Label();
        oldPassword = new PasswordField();
        oldPassword.setInputPrompt("Entrez votre mot de passe");
        newPassword = new PasswordField();
        newPassword.setInputPrompt("Nouveau mot de passe");
        newPassword2 = new PasswordField();
        newPassword2.setInputPrompt("Réentrez nouveau mot de passe");

        newEmail.setWidth(100.0f, Unit.PERCENTAGE);
        oldPassword.setWidth(100.0f, Unit.PERCENTAGE);
        newPassword.setWidth(100.0f, Unit.PERCENTAGE);
        newPassword2.setWidth(100.0f, Unit.PERCENTAGE);

        //FileUploader
        FileUploader uploader = new FileUploader()
        {
            @Override
            public void uploadSucceeded(File file, SucceededEvent event)
            {
                UI.getCurrent().getNavigator().navigateTo(getReferer());
            }
        };

        this.addComponent(uploader, "upload");
        this.addComponent(email, "email");
        this.addComponent(newEmail, "newEmail");
        this.addComponent(password, "password");
        this.addComponent(oldPassword, "oldPassword");
        this.addComponent(newPassword, "newPassword");
        this.addComponent(newPassword2, "newPassword2");
        this.addComponent(save, "saveButton");

        save.addClickListener(clickEvent -> {

            if (!oldPassword.isEmpty() && oldPassword.getValue().equals(SessionUtils.getPassword()))
            {
                if (!newPassword.isEmpty() && !newPassword2.isEmpty() && newPassword.getValue().equals(newPassword2.getValue()))
                {
                    if (SessionUtils.getRoles().contains(Role.ETUDIANT))
                    {
                        Etudiant e = EtudiantDAO.getInstance().findById(SessionUtils.getUsername());
                        e.setMotDePasse(newPassword.getValue());
                        EtudiantDAO.getInstance().update(e);
                    }
                    if (SessionUtils.getRoles().contains(Role.PROFESSEUR))
                    {
                        Professeur e = ProfesseurDAO.getInstance().findById(SessionUtils.getUsername());
                        e.setMotDePasse(newPassword.getValue());
                        ProfesseurDAO.getInstance().update(e);
                    }
                    if (SessionUtils.getRoles().contains(Role.MAITRESTAGE))
                    {
                        MaitreStage e = MaitreStageDAO.getInstance().findById(SessionUtils.getUsername());
                        e.setMotDePasse(newPassword.getValue());
                        MaitreStageDAO.getInstance().update(e);
                    }
                    if (SessionUtils.getRoles().contains(Role.PRESIDENTJURY))
                    {
                        PresidentJury e = PresidentJuryDAO.getInstance().findById(SessionUtils.getUsername());
                        e.setMotDePasse(newPassword.getValue());
                        PresidentJuryDAO.getInstance().update(e);
                    }
                } else
                {
                    Notification.show("Erreur", "Entrez 2 fois le même mot de passe", Notification.Type.TRAY_NOTIFICATION);
                }
            } else
            {
                Notification.show("Erreur", "Mot de passe incorrect!", Notification.Type.TRAY_NOTIFICATION);
            }
        });

    }

}
