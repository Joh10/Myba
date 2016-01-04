package ui;

import beans.Utilisateur;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import managers.UtilisateurManager;

import java.util.regex.Pattern;

@SuppressWarnings({"serial", "deprecation"})
public class MonCompte extends MonCompte_design implements View
{
    private static final long serialVersionUID = 8185504690169599567L;
    private Navigator navigateur;
    private WrappedSession currentSession;
    private Utilisateur currentUser;

    public MonCompte()
    {
        button_retour.setIcon(FontAwesome.ARROW_LEFT);
        button_confirmer.setIcon(FontAwesome.CHECK);
        tf_nom.setIcon(FontAwesome.ASTERISK);
        tf_prenom.setIcon(FontAwesome.ASTERISK);
        tf_tel.setIcon(FontAwesome.PHONE);
        tf_mail.setIcon(FontAwesome.ASTERISK);
        mdp_actuel.setIcon(FontAwesome.ASTERISK);
        mdp_nouveau.setIcon(FontAwesome.KEY);
        mdp_confirmation.setIcon(FontAwesome.KEY);
        button_confirmer.setClickShortcut(KeyCode.ENTER, null);
        mdp_actuel.setInputPrompt("*******");
        mdp_nouveau.setInputPrompt("*******");
        mdp_confirmation.setInputPrompt("*******");
        tf_tel.setInputPrompt("0470/12.34.56");
        tf_mail.setInputPrompt("mon.adresse.mail@hers.be");

        button_retour.addClickListener((ClickListener) event -> navigateur.navigateTo("Dashboard"));

        button_confirmer.addClickListener((ClickListener) event ->
        {
            String tel = tf_tel.getValue();
            String mail = tf_mail.getValue();
            String mdpActuel = mdp_actuel.getValue();
            String mdpNouveau = mdp_nouveau.getValue();
            String mdpConfirmation = mdp_confirmation.getValue();
            if (!tel.equals(currentUser.getTelephone()) || !mail.equals(currentUser.getEmail()) || mdpNouveau.length() > 0)
            {
                boolean mdpRequis = false;
                if (mdpNouveau.length() > 0 || !mail.equals(currentUser.getEmail())) mdpRequis = true;
                String erreurMsg = "";
                if (mdpNouveau.length() > 0)
                {
                    if (mdpNouveau.length() < 5)
                        erreurMsg = "Le nouveau mot de passe doit contenir 5 caractères au minimum";
                    else if (!mdpNouveau.equals(mdpConfirmation))
                        erreurMsg = "Le nouveau mot de passe et sa confirmation sont différents";
                }
                if (!mail.equals(currentUser.getEmail()))
                {
                    // on vérifie la validité de l'adresse email
                    if (!Pattern.matches("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$", mail))
                        erreurMsg = "L'adresse mail est invalide";
                }
                if (erreurMsg.length() == 0 && mdpRequis)
                {
                    if (mdpActuel.length() == 0)
                        erreurMsg = "Le mot de passe actuel est requis pour modifier l'email ou le mot de passe";
                    else if (!currentUser.checkPassword(mdpActuel))
                        erreurMsg = "Le mot de passe actuel est incorrect";
                }
                if (erreurMsg.length() == 0)
                {
                    UtilisateurManager user_DB = new UtilisateurManager();
                    Utilisateur user = user_DB.find(currentUser.getId());
                    user.update(true, mail, user.getMatricule(), user.getNom(), user.getPrenom(), tel, user.getAnnee(), user.isDoublant());
                    if (mdpNouveau.length() > 0) user.setPassword(mdpNouveau);
                    if (user_DB.update(user))
                    {
                        mdp_actuel.setValue("");
                        mdp_nouveau.setValue("");
                        mdp_confirmation.setValue("");
                        if (mdpNouveau.length() > 0)
                        {
                            // Si le mot de passe est modifié, on demande de se reconnecter
                            Notification.show("Votre mot de passe a été mis à jour, veuillez vous reconnecter", Notification.TYPE_WARNING_MESSAGE);
                            currentSession.removeAttribute("user");
                            navigateur.navigateTo("");
                        } else
                        {
                            // Sinon, on met à jour les données de l'utilisateur
                            currentUser = user;
                            currentSession.setAttribute("user", user);
                        }
                        Notification.show("Vos informations ont bien été modifiées", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de vos informations", Notification.TYPE_ERROR_MESSAGE);
                } else Notification.show(erreurMsg, Notification.TYPE_WARNING_MESSAGE);
            } else
                Notification.show("Vous n'avez effectué aucune modification", Notification.TYPE_TRAY_NOTIFICATION);
        });
    }

    public void enter(ViewChangeEvent event)
    {
        this.navigateur = event.getNavigator();

        currentSession = VaadinService.getCurrentRequest().getWrappedSession();
        currentUser = (Utilisateur) currentSession.getAttribute("user");
        if (currentUser == null) navigateur.navigateTo("");
        else
        {
            tf_nom.setReadOnly(false);
            tf_nom.setValue(currentUser.getNom());
            tf_nom.setReadOnly(true);
            tf_prenom.setReadOnly(false);
            tf_prenom.setValue(currentUser.getPrenom());
            tf_prenom.setReadOnly(true);
            tf_tel.setValue(currentUser.getTelephone());
            tf_mail.setValue(currentUser.getEmail());
            mdp_actuel.setValue("");
            mdp_nouveau.setValue("");
            mdp_confirmation.setValue("");
        }
    }
}
