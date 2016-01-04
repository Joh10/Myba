package ui;

import beans.*;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Upload.Receiver;
import dao.*;
import org.vaadin.dialogs.ConfirmDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"serial", "deprecation"})
public class Dashboard extends Dashboard_IconsAndTabs implements View
{
    private static final long serialVersionUID = 827623503302889604L;
    private Navigator navigateur;
    private Object elementSelected;
    private boolean modification;
    private Table selectedTab = new Table();
    private WrappedSession currentSession;
    private Utilisateur currentUser = null;
    private TFE tfeSelected;
    private Stage stageSelected;
    private Defense defenseSelected;
    private File fileUploaded;

    public Dashboard()
    {
        load_tab_professeurs();
        // Listeners
        // TABSHEET
        // ONGLETS PRINCIPAUX
        onglets.addSelectedTabChangeListener((TabSheet.SelectedTabChangeListener) event -> {
            unselectSelectedTab();
            TabSheet tabsheet = event.getTabSheet();
            Layout tab = (Layout) tabsheet.getSelectedTab();
            String caption = tabsheet.getTab(tab).getCaption();
            switch (caption)
            {
                case "Administration":
                    unselectSelectedTab();
                    hideForms();
                    removeAllClickShortcuts();
                    load_tab_professeurs();
                    break;
                case "Professeurs":
                    unselectSelectedTab();
                    hideForms();
                    removeAllClickShortcuts();
                    load_tab_tfe();
                    break;
                case "Etudiants":
                    unselectSelectedTab();
                    hideForms();
                    removeAllClickShortcuts();
                    load_tab_tfe2();
                    break;
                case "Maitre de stage":
                    unselectSelectedTab();
                    hideForms();
                    removeAllClickShortcuts();
                    load_tab_stage3();
                    break;
                case "Président de jury":
                    unselectSelectedTab();
                    hideForms();
                    removeAllClickShortcuts();
                    load_tab_defense3();
                    break;
            }
        });

        // ADMINISTRATION
        onglets_administration.addSelectedTabChangeListener((TabSheet.SelectedTabChangeListener) event -> {
            unselectSelectedTab();
            TabSheet tabsheet = event.getTabSheet();
            Layout tab = (Layout) tabsheet.getSelectedTab();
            String caption = tabsheet.getTab(tab).getCaption();
            removeAllClickShortcuts();
            switch (caption)
            {
                case "Gérer les professeurs":
                    unselectSelectedTab();
                    load_tab_professeurs();
                    hideForms();
                    break;
                case "Gérer les étudiants":
                    unselectSelectedTab();
                    load_tab_etudiants();
                    hideForms();
                    break;
                case "Gérer les maitres de stage":
                    unselectSelectedTab();
                    load_tab_maitresDeStage();
                    hideForms();
                    break;
                case "Gérer les présidents de jury":
                    unselectSelectedTab();
                    load_tab_presidentsDeJury();
                    hideForms();
                    break;
                case "Gérer les entreprises":
                    unselectSelectedTab();
                    load_tab_entreprises();
                    hideForms();
                    break;
                case "Gérer les critères d'évaluation":
                    unselectSelectedTab();
                    load_tab_criteresEvaluation();
                    load_cb_critereEvaluation();
                    hideForms();
                    break;
                case "Gérer les technologies":
                    unselectSelectedTab();
                    load_tab_technologies();
                    hideForms();
                    break;
            }
        });
        // PROFESSEURS
        onglets_prof.addSelectedTabChangeListener((TabSheet.SelectedTabChangeListener) event -> {
            unselectSelectedTab();
            TabSheet tabsheet = event.getTabSheet();
            Layout tab = (Layout) tabsheet.getSelectedTab();
            String caption = tabsheet.getTab(tab).getCaption();
            removeAllClickShortcuts();
            switch (caption)
            {
                case "Gérer les TFE":
                    unselectSelectedTab();
                    load_tab_tfe();
                    hideForms();
                    break;
                case "Gérer les stages":
                    unselectSelectedTab();
                    load_tab_stage();
                    hideForms();
                    break;
                case "Gérer les propositions de stage":
                    unselectSelectedTab();
                    load_tab_propositionStage();
                    hideForms();
                    break;
                case "Gérer les défenses":
                    unselectSelectedTab();
                    load_tab_defense();
                    hideForms();
                    break;
                case "Gérer les échéances":
                    unselectSelectedTab();
                    load_tab_echeance2();
                    hideForms();
                    break;
            }
        });
        // ETUDIANT
        onglets_etudiant.addSelectedTabChangeListener((TabSheet.SelectedTabChangeListener) event -> {
            unselectSelectedTab();
            TabSheet tabsheet = event.getTabSheet();
            Layout tab = (Layout) tabsheet.getSelectedTab();
            String caption = tabsheet.getTab(tab).getCaption();
            removeAllClickShortcuts();
            switch (caption)
            {
                case "Consulter les TFE":
                    unselectSelectedTab();
                    load_tab_tfe2();
                    break;
                case "Consulter les stages":
                    unselectSelectedTab();
                    load_tab_stage2();
                    break;
                case "Gérer mes propositions de stage":
                    unselectSelectedTab();
                    form_propositionStage_etudiant_editer.setVisible(false);
                    load_tab_propositionStage2();
                    break;
                case "Consulter mes échéances":
                    unselectSelectedTab();
                    load_tab_echeance();
                    break;
                case "Consulter mes défenses":
                    unselectSelectedTab();
                    load_tab_defense2();
                    break;
                case "Consulter les entreprises":
                    unselectSelectedTab();
                    load_tab_entreprises_etudiant();
                    break;
            }
        });
        // * * * MON COMPTE * * *
        button_monCompte.addClickListener((ClickListener) event -> {
            removeAllClickShortcuts();
            unselectSelectedTab();
            hideForms();
            navigateur.navigateTo("Account");
        });
        // * * * DECONNEXION * * *
        button_deconnexion.addClickListener((ClickListener) event -> {
            removeAllClickShortcuts();
            unselectSelectedTab();
            hideForms();
            currentSession.removeAttribute("user");
            navigateur.navigateTo("");
        });
        // * * * ADMINISTRATION * * *
        // PROFESSEUR
        // Ajouter un professeur
        ajouter.addClickListener((ClickListener) event -> {
            modification = false;
            tf_professeur_prenom.clear();
            tf_professeur_nom.clear();
            tf_professeur_adresseMail.clear();
            tf_professeur_tel.clear();
            tf_professeur_mdp.setValue("");
            cb_professeur_compteActif.setValue(true);
            tf_professeur_mdp.setValue("");
            button_professeur_valider.setClickShortcut(KeyCode.ENTER);
            form_prof.setVisible(true);
        });
        // Modifier un professeur
        modifier.addClickListener((ClickListener) event -> {
            modification = true;
            DAO_Utilisateur user_DB = new DAO_Utilisateur();
            Utilisateur user = user_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_professeur_prenom.setValue(user.getPrenom());
            tf_professeur_nom.setValue(user.getNom());
            tf_professeur_adresseMail.setValue(user.getEmail());
            tf_professeur_tel.setValue(user.getTelephone());
            cb_professeur_compteActif.setValue(user.isEnabled());
            tf_professeur_mdp.setValue("");
            button_professeur_valider.setClickShortcut(KeyCode.ENTER);
            form_prof.setVisible(true);
        });
        // Valider editer professeur
        button_professeur_valider.addClickListener((ClickListener) event -> {
            try
            {
                String prenom = tf_professeur_prenom.getValue();
                String nom = tf_professeur_nom.getValue();
                String email = tf_professeur_adresseMail.getValue();
                String tel = tf_professeur_tel.getValue();
                String password = tf_professeur_mdp.getValue();
                boolean compteActif = cb_professeur_compteActif.getValue();
                if (!prenom.isEmpty() && !nom.isEmpty() && !email.isEmpty() && (!password.isEmpty() || modification))
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    if (modification)
                    {
                        Utilisateur user = user_DB.find((int) elementSelected);
                        user.update(compteActif, email, null, nom, prenom, tel, null, null);
                        if (!password.isEmpty()) user.setPassword(password);

                        if (user_DB.update(user))
                        {
                            Notification.show("Le professeur a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                            form_prof.setVisible(false);
                            load_tab_professeurs();
                            button_professeur_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la modification du professeur", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        Utilisateur userCreate = new Utilisateur(0, compteActif, new Role("professeur"), email, password, null, nom, prenom, tel, null, null);

                        if (user_DB.create(userCreate))
                        {
                            Notification.show("Le professeur a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                            form_prof.setVisible(false);
                            load_tab_professeurs();
                            button_professeur_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la suppression du professeur", Notification.TYPE_ERROR_MESSAGE);
                    }
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler editer professeur
        button_professeur_annuler.addClickListener((ClickListener) event -> {
            form_prof.setVisible(false);
            button_professeur_valider.removeClickShortcut();
        });
        // Supprimer un professeur
        supprimer.addClickListener((ClickListener) event -> {
            form_prof.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    Utilisateur user = user_DB.find((int) elementSelected);

                    if (user_DB.delete(user))
                    {
                        Notification.show("Le professeur a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du professeur", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_professeurs();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // ETUDIANT
        // Ajouter un étudiant
        ajouter2.addClickListener((ClickListener) event -> {
            modification = false;
            tf_etudiant_prenom.clear();
            tf_etudiant_nom.clear();
            tf_etudiant_matricule.clear();
            sl_etudiant_annee.clear();
            tf_etudiant_mail.clear();
            tf_etudiant_tel.clear();
            tf_etudiant_mdp.setValue("");
            cb_etudiant_doublant.clear();
            cb_etudiant_compteActif.setValue(true);
            button_etudiant_valider.setClickShortcut(KeyCode.ENTER);
            form_etudiant.setVisible(true);
        });
        // Modifier un étudiant
        modifier2.addClickListener((ClickListener) event -> {
            modification = true;
            DAO_Utilisateur user_DB = new DAO_Utilisateur();
            Utilisateur user = user_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_etudiant_prenom.setValue(user.getPrenom());
            tf_etudiant_nom.setValue(user.getNom());
            tf_etudiant_matricule.setValue(user.getMatricule().toString());
            sl_etudiant_annee.setValue((double) user.getAnnee());
            tf_etudiant_mail.setValue(user.getEmail());
            tf_etudiant_tel.setValue(user.getTelephone());
            cb_etudiant_doublant.setValue(user.isDoublant());
            cb_etudiant_compteActif.setValue(user.isEnabled());
            tf_etudiant_mdp.setValue("");
            button_etudiant_valider.setClickShortcut(KeyCode.ENTER);
            if (user.getRole().getNom().equals("etudiant_tfe")) cb_etudiant_permission.setValue("TFE");
            else if (user.getRole().getNom().equals("etudiant_tfe_stage"))
                cb_etudiant_permission.setValue("TFE et Stage");
            form_etudiant.setVisible(true);
        });
        // Valider editer étudiant
        button_etudiant_valider.addClickListener((ClickListener) event -> {
            try
            {
                String prenom = tf_etudiant_prenom.getValue();
                String nom = tf_etudiant_nom.getValue();
                String email = tf_etudiant_mail.getValue();
                int matricule = Integer.parseInt(tf_etudiant_matricule.getValue());
                int annee = (int) (double) sl_etudiant_annee.getValue();
                String tel = tf_etudiant_tel.getValue();
                String password = tf_etudiant_mdp.getValue();
                Boolean doublant = cb_etudiant_doublant.getValue();
                boolean compteActif = cb_etudiant_compteActif.getValue();
                String permission = (String) cb_etudiant_permission.getValue();
                Role role = null;
                if (permission.equals("TFE")) role = new Role("etudiant_tfe");
                else if (permission.equals("TFE et Stage")) role = new Role("etudiant_tfe_stage");
                if (role != null && !prenom.isEmpty() && !nom.isEmpty() && !email.isEmpty() && matricule > 0 && annee > 0 && (!password.isEmpty() || modification))
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    if (modification)
                    {
                        Utilisateur user = user_DB.find((int) elementSelected);
                        user.update(compteActif, email, matricule, nom, prenom, tel, annee, doublant);
                        if (!password.isEmpty()) user.setPassword(password);
                        user.setRole(role);
                        if (user_DB.update(user))
                        {
                            Notification.show("L'étudiant a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                            form_etudiant.setVisible(false);
                            load_tab_etudiants();
                            button_etudiant_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la modification de l'étudiant", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        Utilisateur userCreate = new Utilisateur(0, compteActif, role, email, password, matricule, nom, prenom, tel, annee, doublant);

                        if (user_DB.create(userCreate))
                        {
                            Notification.show("L'étudiant a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                            form_etudiant.setVisible(false);
                            load_tab_etudiants();
                            button_etudiant_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la suppression de l'étudiant", Notification.TYPE_ERROR_MESSAGE);
                    }
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
            catch (NumberFormatException e)
            {
                Notification.show("Le matricule doit être un nombre", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler editer étudiant
        button_etudiant_annuler.addClickListener((ClickListener) event -> {
            form_etudiant.setVisible(false);
            button_etudiant_valider.removeClickShortcut();
        });
        // Supprimer un étudiant
        supprimer2.addClickListener(event ->
        {
            form_etudiant.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    Utilisateur user = user_DB.find((int) elementSelected);

                    if (user_DB.delete(user))
                    {
                        Notification.show("L'étudiant a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'élève", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_etudiants();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // MAITRE DE STAGE
        // Ajouter un maitre de stage
        ajouter3.addClickListener(event -> 
        {
            modification = false;
            tf_maitreDeStage_prenom.clear();
            tf_maitreDeStage_nom.clear();
            tf_maitreDeStage_mail.clear();
            tf_maitreDeStage_tel.clear();
            tf_maitreDeStage_mdp.setValue("");
            cb_maitreDeStage_compteActif.setValue(true);
            button_maitreDeStage_valider.setClickShortcut(KeyCode.ENTER);
            form_maitreDeStage.setVisible(true);
        });
        // Modifier un maitre de stage
        modifier3.addClickListener(event ->
        {
            modification = true;
            DAO_Utilisateur user_DB = new DAO_Utilisateur();
            Utilisateur user = user_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_maitreDeStage_prenom.setValue(user.getPrenom());
            tf_maitreDeStage_nom.setValue(user.getNom());
            tf_maitreDeStage_mail.setValue(user.getEmail());
            tf_maitreDeStage_tel.setValue(user.getTelephone());
            cb_maitreDeStage_compteActif.setValue(user.isEnabled());
            tf_maitreDeStage_mdp.setValue("");
            button_maitreDeStage_valider.setClickShortcut(KeyCode.ENTER);
            form_maitreDeStage.setVisible(true);
        });
        // Valider editer maitre de stage
        button_maitreDeStage_valider.addClickListener(event -> {
            String prenom = tf_maitreDeStage_prenom.getValue();
            String nom = tf_maitreDeStage_nom.getValue();
            String email = tf_maitreDeStage_mail.getValue();
            String tel = tf_maitreDeStage_tel.getValue();
            String password = tf_maitreDeStage_mdp.getValue();
            boolean compteActif = cb_maitreDeStage_compteActif.getValue();
            if (!prenom.isEmpty() && !nom.isEmpty() && !email.isEmpty() && (!password.isEmpty() || modification))
            {
                DAO_Utilisateur user_DB = new DAO_Utilisateur();
                if (modification)
                {
                    Utilisateur user = user_DB.find((int) elementSelected);
                    user.update(compteActif, email, null, nom, prenom, tel, null, null);
                    if (!password.isEmpty()) user.setPassword(password);

                    if (user_DB.update(user))
                    {
                        Notification.show("Le maitre de stage a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                        form_maitreDeStage.setVisible(false);
                        load_tab_maitresDeStage();
                        button_maitreDeStage_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification du maitre de stage", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    Utilisateur userCreate = new Utilisateur(0, compteActif, new Role("maitre_stage"), email, password, null, nom, prenom, tel, null, null);

                    if (user_DB.create(userCreate))
                    {
                        Notification.show("Le maitre a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                        form_maitreDeStage.setVisible(false);
                        load_tab_maitresDeStage();
                        button_maitreDeStage_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du maitre de stage", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer maitre de stage
        button_maitreDeStage_annuler.addClickListener(event -> {
            form_maitreDeStage.setVisible(false);
            button_maitreDeStage_valider.removeClickShortcut();
        });
        // Supprimer un maitre de stage
        supprimer3.addClickListener(event -> {
            form_maitreDeStage.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    Utilisateur user = user_DB.find((int) elementSelected);
                    if (user_DB.delete(user))
                    {
                        elementSelected = null;
                        Notification.show("Le maitre de stage a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du maitre de stage", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_maitresDeStage();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // PRESIDENT DE JURY
        // Ajouter un président de jury
        ajouter4.addClickListener(event -> {
            modification = false;
            tf_presidentJury_prenom.clear();
            tf_presidentJury_nom.clear();
            tf_presidentJury_mail.clear();
            tf_presidentJury_tel.clear();
            tf_presidentJury_mdp.setValue("");
            tf_presidentJury_compteActif.setValue(true);
            button_presidentDeJury_valider.setClickShortcut(KeyCode.ENTER);
            form_presidentDeJury.setVisible(true);
        });
        // Modifier un président de jury
        modifier4.addClickListener(event -> {
            modification = true;
            DAO_Utilisateur user_DB = new DAO_Utilisateur();
            Utilisateur user = user_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_presidentJury_prenom.setValue(user.getPrenom());
            tf_presidentJury_nom.setValue(user.getNom());
            tf_presidentJury_mail.setValue(user.getEmail());
            tf_presidentJury_tel.setValue(user.getTelephone());
            tf_presidentJury_compteActif.setValue(user.isEnabled());
            tf_presidentJury_mdp.setValue("");
            button_presidentDeJury_valider.setClickShortcut(KeyCode.ENTER);
            form_presidentDeJury.setVisible(true);
        });
        // Valider editer president de jury
        button_presidentDeJury_valider.addClickListener(event -> {
            String prenom = tf_presidentJury_prenom.getValue();
            String nom = tf_presidentJury_nom.getValue();
            String email = tf_presidentJury_mail.getValue();
            String tel = tf_presidentJury_tel.getValue();
            String password = tf_presidentJury_mdp.getValue();
            boolean compteActif = tf_presidentJury_compteActif.getValue();
            if (!prenom.isEmpty() && !nom.isEmpty() && !email.isEmpty() && (!password.isEmpty() || modification))
            {
                DAO_Utilisateur user_DB = new DAO_Utilisateur();
                if (modification)
                {
                    Utilisateur user = user_DB.find((int) elementSelected);
                    user.update(compteActif, email, null, nom, prenom, tel, null, null);
                    if (!password.isEmpty()) user.setPassword(password);
                    if (user_DB.update(user))
                    {
                        Notification.show("Le président de jury a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                        form_presidentDeJury.setVisible(false);
                        load_tab_presidentsDeJury();
                        button_presidentDeJury_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification du président de jury", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    Utilisateur userCreate = new Utilisateur(0, compteActif, new Role("president_jury"), email, password, null, nom, prenom, tel, null, null);

                    if (user_DB.create(userCreate))
                    {
                        Notification.show("Le président de jury a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                        form_presidentDeJury.setVisible(false);
                        load_tab_presidentsDeJury();
                        button_presidentDeJury_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du président de jury", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer président de jury
        button_presidentDeJury_annuler.addClickListener(event -> {
            form_presidentDeJury.setVisible(false);
            button_presidentDeJury_valider.removeClickShortcut();
        });
        // Supprimer un président de jury
        supprimer4.addClickListener(event -> {
            form_presidentDeJury.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Utilisateur user_DB = new DAO_Utilisateur();
                    Utilisateur user = user_DB.find((int) elementSelected);
                    if (user_DB.delete(user))
                    {
                        elementSelected = null;
                        Notification.show("Le président de jury a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du président de jury", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_presidentsDeJury();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
            DAO_Utilisateur user_DB = new DAO_Utilisateur();
            Utilisateur user = user_DB.find((int) elementSelected);
            if (user_DB.delete(user))
            {
                elementSelected = null;
                Notification.show("Le président de jury a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
            } else
                Notification.show("Une erreur est survenue lors de la suppression du président de jury", Notification.TYPE_ERROR_MESSAGE);
            load_tab_presidentsDeJury();
        });
        // ENTREPRISE
        // Ajouter une entreprise
        ajouter5.addClickListener(event -> {
            modification = false;
            tf_entreprise_nom.clear();
            ta_entreprise_adresse.setValue("");
            tf_entreprise_personneDeContact.clear();
            tf_entreprise_telephone.clear();
            button_entreprise_valider.setClickShortcut(KeyCode.ENTER);
            label_entreprise.setValue("Entreprise");
            form_entreprise.setVisible(true);
        });
        // Modifier une entreprise
        modifier5.addClickListener(event -> {
            modification = true;
            DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
            LieuStage lieuStage = lieuStage_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_entreprise_nom.setValue(lieuStage.getNom());
            ta_entreprise_adresse.setValue(lieuStage.getAdresse());
            tf_entreprise_personneDeContact.setValue(lieuStage.getContact());
            tf_entreprise_telephone.setValue(lieuStage.getTelephone());
            tf_entreprise_adresseMail.setValue(lieuStage.getEmail());
            button_entreprise_valider.setClickShortcut(KeyCode.ENTER);
            label_entreprise.setValue("Entreprise (ajoutée par " + lieuStage.getOwner() + ")");
            form_entreprise.setVisible(true);
        });
        // Valider editer entreprise
        button_entreprise_valider.addClickListener(event -> {
            String nomEntreprise = tf_entreprise_nom.getValue();
            String adresse = ta_entreprise_adresse.getValue();
            String tel = tf_entreprise_telephone.getValue();
            String contact = tf_entreprise_personneDeContact.getValue();
            String mail = tf_entreprise_adresseMail.getValue();
            if (!nomEntreprise.isEmpty() && !adresse.isEmpty() && !contact.isEmpty())
            {
                DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
                if (modification)
                {
                    LieuStage lieuStage = lieuStage_DB.find((int) elementSelected);
                    lieuStage.update(nomEntreprise, adresse, contact, tel, mail);

                    if (lieuStage_DB.update(lieuStage))
                    {
                        Notification.show("Le lieu de stage a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                        form_entreprise.setVisible(false);
                        load_tab_entreprises();
                        button_entreprise_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification du lieu de stage", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    LieuStage lieuStage = new LieuStage(0, currentUser, nomEntreprise, adresse, contact, tel, mail);

                    if (lieuStage_DB.create(lieuStage))
                    {
                        Notification.show("Le lieu de stage a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                        form_entreprise.setVisible(false);
                        load_tab_entreprises();
                        button_entreprise_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout du lieu de stage", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer entreprise
        button_entreprise_annuler.addClickListener(event -> {
            form_entreprise.setVisible(false);
            button_entreprise_valider.removeClickShortcut();
        });
        // Supprimer une entreprise
        supprimer5.addClickListener(event -> {
            form_entreprise.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
                    LieuStage lieuStage = lieuStage_DB.find((int) elementSelected);

                    if (lieuStage_DB.delete(lieuStage))
                    {
                        elementSelected = null;
                        Notification.show("Le lieu de stage a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du lieu de stage", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_entreprises();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // CRITERE D'EVALUATION
        // Ajouter un critère d'évaluation
        ajouter6.addClickListener(event -> {
            modification = false;
            tf_critereEvaluation_nom.clear();
            tf_critereEvaluation_noteMax.clear();
            button_critereEvaluation_valider.setClickShortcut(KeyCode.ENTER);
            form_critereEvaluation_editer.setVisible(true);
        });
        // Modifier un critère d'évaluation
        modifier6.addClickListener(event -> {
            modification = true;
            DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
            CritereEvaluation critereEvaluation = critereEvaluation_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_critereEvaluation_nom.setValue(critereEvaluation.getNom());
            tf_critereEvaluation_noteMax.setValue(((Integer) critereEvaluation.getNote()).toString());
            cb_critereEvaluation_type.select(critereEvaluation.getType());
            button_critereEvaluation_valider.setClickShortcut(KeyCode.ENTER);
            form_critereEvaluation_editer.setVisible(true);
        });
        // Valider editer critère d'évaluation
        button_critereEvaluation_valider.addClickListener(event -> {
            try
            {
                String nom = tf_critereEvaluation_nom.getValue();
                Object typeCB = cb_critereEvaluation_type.getValue();
                String noteMax = tf_critereEvaluation_noteMax.getValue();
                String type = typeCB.toString();
                if (!nom.isEmpty() && !noteMax.isEmpty() && !type.isEmpty())
                {
                    DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
                    if (modification)
                    {
                        CritereEvaluation critereEvaluation = critereEvaluation_DB.find((int) elementSelected);
                        critereEvaluation.update(nom, type, Integer.parseInt(noteMax));

                        if (critereEvaluation_DB.update(critereEvaluation))
                        {
                            Notification.show("Le critère d'évaluation a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                            form_critereEvaluation_editer.setVisible(false);
                            load_tab_criteresEvaluation();
                            button_critereEvaluation_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la modification du critère d'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        CritereEvaluation critereEvaluation = new CritereEvaluation(0, nom, type, Integer.parseInt(noteMax));

                        if (critereEvaluation_DB.create(critereEvaluation))
                        {
                            Notification.show("Le critère d'évaluation a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                            form_critereEvaluation_editer.setVisible(false);
                            load_tab_criteresEvaluation();
                            button_critereEvaluation_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout du critère d'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    }
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);

            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez vérifier la note maximale", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler editer critère d'évaluation
        button_critereEvaluation_annuler.addClickListener(event -> {
            form_critereEvaluation_editer.setVisible(false);
            button_critereEvaluation_valider.removeClickShortcut();
        });
        // Supprimer un critère d'évaluation
        supprimer6.addClickListener(event -> {
            form_critereEvaluation_editer.setVisible(false);

            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
                    CritereEvaluation critereEvaluation = critereEvaluation_DB.find((int) elementSelected);
                    if (critereEvaluation_DB.delete(critereEvaluation))
                    {
                        elementSelected = null;
                        Notification.show("Le critère d'évaluation a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du critère d'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_criteresEvaluation();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // TECHNOLOGIE
        // Ajouter une technologie
        ajouter7.addClickListener(event -> {
            modification = false;
            tf_technologie_nom.clear();
            tf_technologie_version.clear();
            button_technologie_valider.setClickShortcut(KeyCode.ENTER);
            form_technologie.setVisible(true);
        });
        // Modifier une technologie
        modifier7.addClickListener(event -> {
            modification = true;
            DAO_Technologie technologie_DB = new DAO_Technologie();
            Technologie technologie = technologie_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_technologie_nom.setValue(technologie.getNom());
            tf_technologie_version.setValue(technologie.getVersion());
            button_technologie_valider.setClickShortcut(KeyCode.ENTER);
            form_technologie.setVisible(true);
        });
        // Valider editer technologie
        button_technologie_valider.addClickListener(event -> {
            String nomTechnologie = tf_technologie_nom.getValue();
            String versionTechnologie = tf_technologie_version.getValue();
            if (!nomTechnologie.isEmpty())
            {
                DAO_Technologie technologie_DB = new DAO_Technologie();
                if (modification)
                {
                    Technologie technologie = technologie_DB.find((int) elementSelected);
                    technologie.update(nomTechnologie, versionTechnologie);

                    if (technologie_DB.update(technologie))
                    {
                        Notification.show("La technologie a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_technologie.setVisible(false);
                        load_tab_technologies();
                        button_technologie_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de la technologie", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    Technologie technologie = new Technologie(0, nomTechnologie, versionTechnologie);

                    if (technologie_DB.create(technologie))
                    {
                        Notification.show("La technologie a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_technologie.setVisible(false);
                        load_tab_technologies();
                        button_technologie_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout de la technologie", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer technologie
        button_technologie_annuler.addClickListener(event -> {
            form_technologie.setVisible(false);
            button_technologie_valider.removeClickShortcut();
        });
        // Supprimer une technologie
        supprimer7.addClickListener(event -> {
            form_technologie.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Technologie technologie_DB = new DAO_Technologie();
                    Technologie technologie = technologie_DB.find((int) elementSelected);

                    if (technologie_DB.delete(technologie))
                    {
                        elementSelected = null;
                        Notification.show("La technologie a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de la technologie", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_technologies();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });

        // * * * PROFESSEURS * * *
        // TFE
        // Ajouter un TFE
        button_ajouterTFE.addClickListener(event -> {
            modification = false;
            tf_tfe_ajouter_titre.clear();
            cb_tfe_ajouter_promoteur.clear();
            cb_tfe_ajouter_etudiant.clear();
            tf_tfe_ajouter_anneeDebut.clear();
            tf_tfe_ajouter_anneeFin.clear();
            button_tfe_ajouter_valider.setClickShortcut(KeyCode.ENTER);
            hideForms();
            form_TFE_ajouter.setVisible(true);
            form_TFE_ajouter2.setVisible(true);
        });
        // valider ajouter TFE
        button_tfe_ajouter_valider.addClickListener(event -> {
            try
            {
                String titre = tf_tfe_ajouter_titre.getValue();
                Utilisateur promoteur = (Utilisateur) cb_tfe_ajouter_promoteur.getValue();
                Utilisateur etudiant = (Utilisateur) cb_tfe_ajouter_etudiant.getValue();
                int anneeDebut = Integer.parseInt(tf_tfe_ajouter_anneeDebut.getValue());
                int anneeFin = Integer.parseInt(tf_tfe_ajouter_anneeFin.getValue());
                @SuppressWarnings("unchecked") Set<Technologie> temp = (Set<Technologie>) tc_tfe_ajouter_technologie.getValue();
                ArrayList<Technologie> technologies = new ArrayList<>(temp);
                if (!titre.isEmpty() && promoteur != null && etudiant != null && anneeDebut > 0 && anneeFin > 0)
                {
                    DAO_TFE tfe_DB = new DAO_TFE();
                    TFE tfe = new TFE(0, etudiant, promoteur, titre, (double) 0, anneeDebut, anneeFin, technologies);
                    if (tfe_DB.create(tfe))
                    {
                        Notification.show("Le TFE a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                        form_TFE_ajouter.setVisible(false);
                        form_TFE_ajouter2.setVisible(false);
                        load_tab_tfe();
                        button_tfe_ajouter_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout du TFE", Notification.TYPE_ERROR_MESSAGE);
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // valider modifier TFE
        button_tfe_modifier_valider.addClickListener(event -> {
            String titre = tf_tfe_modifier_titre.getValue();
            int anneeDebut = Integer.parseInt(tf_tfe_modifier_anneeDebut.getValue());
            int anneeFin = Integer.parseInt(tf_tfe_modifier_anneeFin.getValue());
            Utilisateur promoteur = (Utilisateur) cb_tfe_modifier_promoteur.getValue();
            @SuppressWarnings("unchecked") Set<Technologie> temp = (Set<Technologie>) tc_tfe_modifier_technologie.getValue();
            ArrayList<Technologie> technologies = new ArrayList<>(temp);
            if (!titre.isEmpty() && anneeDebut > 0 && anneeFin > 0)
            {
                DAO_TFE tfe_DB = new DAO_TFE();
                TFE tfe = tfe_DB.find((int) elementSelected);
                tfe.update(promoteur, titre, tfe.getPoints(), anneeDebut, anneeFin, technologies);
                if (tfe_DB.update(tfe))
                {
                    Notification.show("Le TFE a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                    form_TFE_modifier.setVisible(false);
                    form_TFE_modifier2.setVisible(false);
                    load_tab_tfe();
                    button_tfe_modifier_valider.removeClickShortcut();
                } else
                    Notification.show("Une erreur est survenue lors de la modification du TFE", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler ajouter TFE
        button_tfe_ajouter_annuler.addClickListener(event -> {
            form_TFE_ajouter.setVisible(false);
            form_TFE_ajouter2.setVisible(false);
            button_tfe_ajouter_valider.removeClickShortcut();
        });

        // Annuler modifier TFE
        button_tfe_modifier_annuler.addClickListener(event -> {
            form_TFE_modifier.setVisible(false);
            form_TFE_modifier2.setVisible(false);
            button_tfe_modifier_valider.removeClickShortcut();
        });
        // Modifier un TFE
        button_modifierTFE.addClickListener(event -> {
            modification = true;
            DAO_TFE tfe_DB = new DAO_TFE();
            TFE tfe = tfe_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_tfe_modifier_titre.setValue(tfe.getTitre());
            tf_tfe_modifier_anneeDebut.setValue(Integer.toString(tfe.getAnneeDebut()));
            tf_tfe_modifier_anneeFin.setValue(Integer.toString(tfe.getAnneeFin()));
            cb_tfe_modifier_promoteur.setValue(tfe.getPromoteur());
            HashSet<Technologie> preselected = new HashSet<>(tfe.getTechnologies());
            tc_tfe_modifier_technologie.setValue(preselected);
            tc_tfe_modifier_technologie.setImmediate(true);
            hideForms();
            form_TFE_modifier.setVisible(true);
            form_TFE_modifier2.setVisible(true);
            button_tfe_modifier_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer un TFE
        button_supprimerTFE.addClickListener(event -> {
            hideForms();
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_TFE tfe_DB = new DAO_TFE();
                    TFE tfe = tfe_DB.find((int) elementSelected);

                    if (tfe_DB.delete(tfe))
                    {
                        elementSelected = null;
                        Notification.show("Le TFE a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du TFE", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_tfe();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Evaluer un TFE
        button_evaluerTFE.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_evaluations"))
            {
                hideForms();
                button_ajouterTFE.setVisible(false);
                button_modifierTFE.setVisible(false);
                button_supprimerTFE.setVisible(false);
                button_evaluerTFE.setVisible(false);
                button_defenseTFE.setVisible(false);
                button_echeanceTFE.setVisible(false);
                tfe_cb_recherche.setVisible(false);
                button_TFE_ajouterEcheance.setVisible(false);
                button_TFE_modifierEcheance.setVisible(false);
                button_TFE_supprimerEcheance.setVisible(false);
                buton_ajouter_evaluationTFE.setVisible(true);
                button_modifier_evaluationTFE.setVisible(true);
                button_supprimer_evaluationTFE.setVisible(true);
                tab_tfe.setVisible(false);
                button_retour_TFE.setVisible(false);
                button_retour_TFE2.setVisible(true);
                tab_echeance3.setVisible(false);
                tab_evaluationTFE.setVisible(true);
                DAO_TFE tfe_DB = new DAO_TFE();
                TFE tfe = tfe_DB.find((int) elementSelected);
                tfeSelected = tfe;
                label_evaluationTFE.setValue("Evaluations du TFE de " + tfe.getOwner().toString() + " (" + tfe.getTitre() + ")");
                label_echeances_tfe.setVisible(false);
                label_evaluationTFE.setVisible(true);
                load_tab_evaluationTFE(tfe);
            } else button_evaluerTFE.setEnabled(false);
            removeAllClickShortcuts();
        });
        // Retour TFE 2
        button_retour_TFE2.addClickListener(event -> {
            hideForms();
            button_ajouterTFE.setVisible(true);
            button_modifierTFE.setVisible(true);
            button_supprimerTFE.setVisible(true);
            button_evaluerTFE.setVisible(true);
            button_defenseTFE.setVisible(true);
            button_echeanceTFE.setVisible(true);
            tfe_cb_recherche.setVisible(true);
            buton_ajouter_evaluationTFE.setVisible(false);
            button_modifier_evaluationTFE.setVisible(false);
            button_supprimer_evaluationTFE.setVisible(false);
            tab_tfe.setVisible(true);
            button_retour_TFE2.setVisible(false);
            tab_evaluationTFE.setVisible(false);
            unselectSelectedTab();
            tfeSelected = null;
            label_evaluationTFE.setVisible(false);
            load_tab_tfe();
            button_tfe_evaluer_valider.removeClickShortcut();
        });
        // Ajouter evaluation
        buton_ajouter_evaluationTFE.addClickListener(event -> {
            modification = false;
            tfe_evaluer_critere.clear();
            tfe_evaluer_note.clear();
            tfe_evaluer_commentaire.setValue("");
            tfe_evaluer_critere.setVisible(true);
            form_TFE_evaluer.setVisible(true);
            button_tfe_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Modifier evaluation
        button_modifier_evaluationTFE.addClickListener(event -> {
            modification = true;
            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
            Evaluation evaluation = evaluation_DB.find((int) elementSelected);
            tfe_evaluer_critere.clear();
            tfe_evaluer_critere.setVisible(false);
            tfe_evaluer_note.setValue(((Double) evaluation.getNote()).toString());
            tfe_evaluer_commentaire.setValue(evaluation.getCommentaire());
            form_TFE_evaluer.setVisible(true);
            button_tfe_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer evaluation
        button_supprimer_evaluationTFE.addClickListener(event -> {
            form_TFE_evaluer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                    if (evaluation_DB.delete(evaluation))
                    {
                        // Mise à jour des points du TFE
                        calculPointsTFE(tfeSelected);

                        Notification.show("L'évaluation a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_evaluationTFE(tfeSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Valider evaluer TFE
        button_tfe_evaluer_valider.addClickListener(event -> {
            try
            {
                CritereEvaluation critere = (CritereEvaluation) tfe_evaluer_critere.getValue();
                Double note = Double.parseDouble(tfe_evaluer_note.getValue());
                String commentaire = "";
                commentaire += tfe_evaluer_commentaire.getValue();
                Date date = Date.from(Instant.now());
                if (modification)
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();

                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);

                    if (note > 0 && note <= evaluation.getCritere().getNote())
                    {
                        evaluation.update(note, commentaire);
                        if (evaluation_DB.update(evaluation))
                        {
                            // Mise à jour de la note du TFE
                            calculPointsTFE(tfeSelected);

                            Notification.show("L'évaluation a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                            load_tab_evaluationTFE(tfeSelected);
                            form_TFE_evaluer.setVisible(false);
                            button_tfe_evaluer_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la modification de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        if (note > critere.getNote())
                            Notification.show("La note ne peut pas dépasser la note maximale du critère", Notification.TYPE_WARNING_MESSAGE);
                        else if (note < 0)
                            Notification.show("La note ne peut pas être nulle", Notification.TYPE_WARNING_MESSAGE);
                        else
                            Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                    }
                } else
                {
                    if (critere != null && note != null && note > 0 && note <= critere.getNote())
                    {
                        DAO_Evaluation evaluation_DB = new DAO_Evaluation();

                        Evaluation evaluation = new Evaluation(0, date, currentUser, critere, tfeSelected, note, commentaire);
                        if (evaluation_DB.create(evaluation))
                        {
                            // Mise à jour de la note du TFE
                            calculPointsTFE(tfeSelected);

                            Notification.show("L'évaluation a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                            load_tab_evaluationTFE(tfeSelected);
                            form_TFE_evaluer.setVisible(false);
                            button_tfe_evaluer_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else if (note > critere.getNote())
                        Notification.show("La note ne peut pas dépasser la note maximale du critère", Notification.TYPE_WARNING_MESSAGE);
                    else
                        Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                }
            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez vérifier les points accordés à l'évaluation", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler evaluer TFE
        button_tfe_evaluer_annuler.addClickListener(event -> {
            form_TFE_evaluer.setVisible(false);
            button_tfe_evaluer_valider.removeClickShortcut();
        });
        // Programmer une defense de TFE
        button_defenseTFE.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_defenses"))
            {
                hideForms();
                tfe_defense_presidentJury.clear();
                tfe_defense_date.clear();
                tfe_defense_local.clear();
                form_TFE_defense.setVisible(true);
            } else button_defenseTFE.setEnabled(false);

            removeAllClickShortcuts();
        });

        // Valider programmer defense TFE
        button_tfe_defense_valider.addClickListener(event -> {
            Utilisateur presidentJury = (Utilisateur) tfe_defense_presidentJury.getValue();
            Date date = tfe_defense_date.getValue();
            String local = tfe_defense_local.getValue();
            DAO_TFE tfe_DB = new DAO_TFE();
            TFE tfe = tfe_DB.find((int) elementSelected);
            if (presidentJury != null && date != null && !local.isEmpty())
            {
                DAO_Defense defense_DB = new DAO_Defense();
                Defense defense = new Defense(0, presidentJury, tfe, date, local);
                if (defense_DB.create(defense))
                {
                    Notification.show("La défense a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                    form_TFE_defense.setVisible(false);
                    load_tab_tfe();
                    button_tfe_defense_valider.removeClickShortcut();
                } else
                    Notification.show("Une erreur est survenue lors de l'ajout de la défense", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler programmer defense TFE
        button_tfe_defense_annuler.addClickListener(event -> {
            form_TFE_defense.setVisible(false);
            button_tfe_defense_valider.removeClickShortcut();
        });
        // Programmer une echeance de TFE
        button_echeanceTFE.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_planning"))
            {
                hideForms();
                button_ajouterTFE.setVisible(false);
                button_modifierTFE.setVisible(false);
                button_supprimerTFE.setVisible(false);
                button_evaluerTFE.setVisible(false);
                button_defenseTFE.setVisible(false);
                button_echeanceTFE.setVisible(false);
                tfe_cb_recherche.setVisible(false);
                button_TFE_ajouterEcheance.setVisible(true);
                button_TFE_modifierEcheance.setVisible(true);
                button_TFE_supprimerEcheance.setVisible(true);
                buton_ajouter_evaluationTFE.setVisible(false);
                button_modifier_evaluationTFE.setVisible(false);
                button_supprimer_evaluationTFE.setVisible(false);
                tab_tfe.setVisible(false);
                button_retour_TFE.setVisible(true);
                button_retour_TFE2.setVisible(false);
                tab_echeance3.setVisible(true);
                tab_evaluationTFE.setVisible(false);
                DAO_TFE tfe_DB = new DAO_TFE();
                TFE tfe = tfe_DB.find((int) elementSelected);
                tfeSelected = tfe;
                label_echeances_tfe.setValue("Echéances du TFE de " + tfe.getOwner().toString() + " (" + tfe.getTitre() + ")");
                label_echeances_tfe.setVisible(true);
                label_evaluationTFE.setVisible(false);
                load_tab_echeance3(tfe);
            } else button_echeanceTFE.setEnabled(false);
            removeAllClickShortcuts();
        });
        // Retour TFE
        button_retour_TFE.addClickListener(event -> {
            hideForms();
            button_ajouterTFE.setVisible(true);
            button_modifierTFE.setVisible(true);
            button_supprimerTFE.setVisible(true);
            button_evaluerTFE.setVisible(true);
            button_defenseTFE.setVisible(true);
            button_echeanceTFE.setVisible(true);
            tfe_cb_recherche.setVisible(true);
            button_TFE_ajouterEcheance.setVisible(false);
            button_TFE_modifierEcheance.setVisible(false);
            button_TFE_supprimerEcheance.setVisible(false);
            label_echeances_tfe.setVisible(false);
            tab_tfe.setVisible(true);
            button_retour_TFE.setVisible(false);
            tab_echeance3.setVisible(false);
            unselectSelectedTab();
            load_tab_tfe();
            removeAllClickShortcuts();
        });

        // Ajouter echeance de TFE
        button_TFE_ajouterEcheance.addClickListener(event -> {
            modification = false;
            tfe_echeance_description.setValue("");
            tfe_echeance_date.clear();
            form_TFE_echeance.setVisible(true);
            button_tfe_echeance_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Modifier une echeance de TFE
        button_TFE_modifierEcheance.addClickListener(event -> {
            modification = true;
            DAO_Echeance echeance_DB = new DAO_Echeance();
            Echeance echeance = echeance_DB.find((int) elementSelected);
            tfe_echeance_description.setValue(echeance.getDescription());
            tfe_echeance_date.setValue(echeance.getDateEcheance());
            form_TFE_echeance.setVisible(true);
            button_tfe_echeance_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer une echeance de TFE
        button_TFE_supprimerEcheance.addClickListener(event -> {
            form_TFE_echeance.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Echeance echeance_DB = new DAO_Echeance();
                    Echeance echeance = echeance_DB.find((int) elementSelected);
                    if (echeance_DB.delete(echeance))
                    {
                        Notification.show("L'échéance a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_echeance3(tfeSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Valider echance TFE
        button_tfe_echeance_valider.addClickListener(event -> {
            String description = tfe_echeance_description.getValue();
            Date date = tfe_echeance_date.getValue();
            if (!description.isEmpty() && date != null)
            {
                DAO_Echeance echeance_DB = new DAO_Echeance();
                Echeance echeance = echeance_DB.find((int) elementSelected);
                if (modification)
                {
                    echeance.update(date, description, "");
                    if (echeance_DB.update(echeance))
                    {
                        Notification.show("L'échéance a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_TFE_echeance.setVisible(false);
                        button_tfe_echeance_valider.removeClickShortcut();
                        load_tab_echeance3(tfeSelected);
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    Echeance ech = new Echeance(0, currentUser, Date.from(Instant.now()), date, tfeSelected, description, null);
                    if (echeance_DB.create(ech))
                    {
                        Notification.show("L'échéance a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_TFE_echeance.setVisible(false);
                        button_tfe_echeance_valider.removeClickShortcut();
                        load_tab_echeance3(tfeSelected);
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler echeance TFE
        button_tfe_echeance_annuler.addClickListener(event -> {
            form_TFE_echeance.setVisible(false);
            button_tfe_echeance_valider.removeClickShortcut();
        });
        // STAGE
        // valider editer stage
        button_stage_editer_valider.addClickListener(event -> {
            String sujet = tf_stage_editer_sujet.getValue();
            Date dateDebut = date_stage_editer_dateDebut.getValue();
            Date dateFin = date_stage_editer_dateFin.getValue();
            Utilisateur maitreStage = (Utilisateur) cb_stage_editer_maitreDeStage.getValue();
            Utilisateur promoteur = (Utilisateur) cb_stage_editer_promoteur.getValue();
            @SuppressWarnings("unchecked") Set<Technologie> temp = (Set<Technologie>) tc_stage_editer_technologies.getValue();
            ArrayList<Technologie> technologies = new ArrayList<>(temp);
            if (!sujet.isEmpty() && dateDebut != null && dateFin != null && maitreStage != null && promoteur != null)
            {
                DAO_Stage stage_DB = new DAO_Stage();
                DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
                Stage stage = stage_DB.find((int) elementSelected);
                stage.update(promoteur, maitreStage, dateDebut, dateFin, "", technologies);
                PropositionStage propStage = stage.getProposition();
                propStage.update(sujet, propStage.getAnnexe());
                if (stage_DB.update(stage) && propositionStage_DB.update(propStage))
                {
                    Notification.show("Le stage a bien été modifié", Notification.TYPE_TRAY_NOTIFICATION);
                    form_stage_editer.setVisible(false);
                    form_stage_editer2.setVisible(false);
                    button_stage_editer_valider.removeClickShortcut();
                    load_tab_stage();
                } else
                    Notification.show("Une erreur est survenue lors de la modification du stage", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer stage
        button_stage_editer_annuler.addClickListener(event -> {
            form_stage_editer.setVisible(false);
            form_stage_editer2.setVisible(false);
            button_stage_editer_valider.removeClickShortcut();
        });
        // Modifier un stage
        button_modifierStage.addClickListener(event -> {
            modification = true;
            DAO_Stage stage_DB = new DAO_Stage();
            Stage stage = stage_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_stage_editer_sujet.setValue(stage.getProposition().getSubject());
            cb_stage_editer_etudiant.setReadOnly(false);
            cb_stage_editer_etudiant.setValue(stage.getOwner());
            cb_stage_editer_etudiant.setReadOnly(true);
            date_stage_editer_dateDebut.setValue(stage.getDateDebut());
            date_stage_editer_dateFin.setValue(stage.getDateFin());
            cb_stage_editer_entreprise.setReadOnly(false);
            cb_stage_editer_entreprise.setValue(stage.getProposition().getPlace());
            cb_stage_editer_entreprise.setReadOnly(true);
            cb_stage_editer_maitreDeStage.setValue(stage.getSuiveur());
            cb_stage_editer_promoteur.setValue(stage.getSuperviseur());
            HashSet<Technologie> preselected = new HashSet<>(stage.getTechnologies());
            tc_stage_editer_technologies.setValue(preselected);
            tc_stage_editer_technologies.setImmediate(true);
            hideForms();
            form_stage_editer.setVisible(true);
            form_stage_editer2.setVisible(true);
            button_stage_editer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer un stage
        button_supprimerStage.addClickListener(event -> {
            hideForms();
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Stage stage_DB = new DAO_Stage();
                    Stage stage = stage_DB.find((int) elementSelected);
                    if (stage_DB.delete(stage))
                    {
                        elementSelected = null;
                        Notification.show("Le stage a bien été supprimé", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression du stage", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_stage();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Retour stages 2
        button_retour_Stage2.addClickListener(event -> {
            hideForms();
            button_modifierStage.setVisible(true);
            button_supprimerStage.setVisible(true);
            button_evaluerStage.setVisible(true);
            button_defenseStage.setVisible(true);
            button_echeanceStage.setVisible(true);
            button_commenterStage.setVisible(true);
            buton_ajouter_evaluationStage.setVisible(false);
            button_modifier_evaluationStage.setVisible(false);
            button_supprimer_evaluationStage.setVisible(false);
            stage_cb_recherche.setVisible(true);
            tab_stage.setVisible(true);
            tab_evaluationStage.setVisible(false);
            button_retour_Stage2.setVisible(false);
            label_evaluationStage.setVisible(false);
            unselectSelectedTab();
            load_tab_stage();
            removeAllClickShortcuts();
        });
        // Evaluer un stage
        button_evaluerStage.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_evaluations"))
            {
                buton_ajouter_evaluationStage.setVisible(true);
                button_modifier_evaluationStage.setVisible(true);
                button_supprimer_evaluationStage.setVisible(true);
                stage_cb_recherche.setVisible(false);
                tab_stage.setVisible(false);
                tab_evaluationStage.setVisible(true);
                button_modifierStage.setVisible(false);
                button_supprimerStage.setVisible(false);
                button_evaluerStage.setVisible(false);
                button_defenseStage.setVisible(false);
                button_echeanceStage.setVisible(false);
                button_commenterStage.setVisible(false);
                button_retour_Stage2.setVisible(true);
                label_evaluationStage.setVisible(true);
                DAO_Stage stage_DB = new DAO_Stage();
                Stage stage = stage_DB.find((int) elementSelected);
                stageSelected = stage;
                label_evaluationStage.setValue("Evaluations du stage de " + stage.getOwner() + " (" + stage.getProposition().getSubject() + ")");
                hideForms();
                load_tab_evaluationStage(stage);
            } else button_evaluerStage.setEnabled(false);
            removeAllClickShortcuts();
        });
        // Ajouter évaluation stage
        buton_ajouter_evaluationStage.addClickListener(event -> {
            modification = false;
            stage_evaluer_critere.clear();
            stage_evaluer_note.clear();
            stage_evaluer_commentaire.setValue("");
            form_stage_evaluer.setVisible(true);
            stage_evaluer_critere.setVisible(true);
            button_stage_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Modifier evaluation stage
        button_modifier_evaluationStage.addClickListener(event -> {
            modification = true;
            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
            Evaluation evaluation = evaluation_DB.find((int) elementSelected);
            stage_evaluer_critere.clear();
            stage_evaluer_note.setValue(((Double) evaluation.getNote()).toString());
            stage_evaluer_commentaire.setValue(evaluation.getCommentaire());
            form_stage_evaluer.setVisible(true);
            stage_evaluer_critere.setVisible(false);
            button_stage_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer evaluation stage
        button_supprimer_evaluationStage.addClickListener(event -> {
            form_stage_evaluer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);

                    if (evaluation_DB.delete(evaluation))
                    {
                        // Mise à jour des points du stage
                        calculPointsStage(stageSelected);

                        Notification.show("L'évaluation a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_evaluationStage(stageSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Valider evaluer stage
        button_stage_evaluer_valider.addClickListener(event -> {
            try
            {
                CritereEvaluation critere = (CritereEvaluation) stage_evaluer_critere.getValue();
                Double note = Double.parseDouble(stage_evaluer_note.getValue());
                String commentaire = stage_evaluer_commentaire.getValue();
                Date date = Date.from(Instant.now());
                if (modification)
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                    if (note > 0 && note <= evaluation.getCritere().getNote())
                    {
                        evaluation.update(note, commentaire);
                        if (evaluation_DB.update(evaluation))
                        {
                            // Mise à jour des points du stage
                            calculPointsStage(stageSelected);

                            Notification.show("L'évaluation a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                            form_stage_evaluer.setVisible(false);
                            load_tab_evaluationStage(stageSelected);
                            button_stage_evaluer_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de la modification de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        if (note > critere.getNote())
                            Notification.show("La note ne peut pas dépasser la note maximale du critère", Notification.TYPE_WARNING_MESSAGE);
                        else if (note < 0)
                            Notification.show("La note ne peut pas être nulle", Notification.TYPE_WARNING_MESSAGE);
                        else
                            Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                    }
                } else
                {
                    if (critere != null)
                    {
                        if (note >= 0 && note <= critere.getNote())
                        {
                            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                            Evaluation evaluation = new Evaluation(0, date, currentUser, critere, stageSelected, note, commentaire);
                            if (evaluation_DB.create(evaluation))
                            {
                                // Mise à jour des points du stage
                                calculPointsStage(stageSelected);

                                Notification.show("L'évaluation a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                                form_stage_evaluer.setVisible(false);
                                load_tab_evaluationStage(stageSelected);
                                button_stage_evaluer_valider.removeClickShortcut();
                            } else
                                Notification.show("Une erreur est survenue lors de l'ajout de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                        } else
                        {
                            if (note > critere.getNote())
                                Notification.show("La note doit être inférieure à la note maximale", Notification.TYPE_WARNING_MESSAGE);
                            else
                                Notification.show("La doit être supérieure ou égale à 0", Notification.TYPE_WARNING_MESSAGE);
                        }
                    } else
                        Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                }
            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler evaluer stage
        button_stage_evaluer_annuler.addClickListener(event -> {
            form_stage_evaluer.setVisible(false);
            button_stage_evaluer_valider.removeClickShortcut();
        });
        // Programmer une defense de stage
        button_defenseStage.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_defenses"))
            {
                hideForms();
                form_stage_defense.setVisible(true);
                stage_defense_presidentJury.clear();
                stage_defense_date.clear();
                stage_defense_local.clear();
            } else button_defenseStage.setEnabled(false);
            removeAllClickShortcuts();
        });
        // Valider programmer defense stage
        button_stage_defense_valider.addClickListener(event -> {
            Utilisateur presidentJury = (Utilisateur) stage_defense_presidentJury.getValue();
            Date date = stage_defense_date.getValue();
            String local = stage_defense_local.getValue();
            DAO_Stage stage_DB = new DAO_Stage();
            Stage stage = stage_DB.find((int) elementSelected);
            if (presidentJury != null && date != null && !local.isEmpty())
            {
                DAO_Defense defense_DB = new DAO_Defense();
                Defense defense = new Defense(0, presidentJury, stage, date, local);
                if (defense_DB.create(defense))
                {
                    Notification.show("La défense a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                    form_stage_defense.setVisible(false);
                    load_tab_stage();
                    button_stage_defense_valider.removeClickShortcut();
                } else
                    Notification.show("Une erreur est survenue lors de l'ajout de la défense", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler programmer defense stage
        button_stage_defense_annuler.addClickListener(event -> {
            form_stage_defense.setVisible(false);
            button_stage_defense_valider.removeClickShortcut();
        });
        // Programmer une echeance de stage
        button_echeanceStage.addClickListener(event -> {
            if (currentUser.getRole().isAllowed("professor_manage_planning"))
            {
                hideForms();
                button_modifierStage.setVisible(false);
                button_supprimerStage.setVisible(false);
                button_evaluerStage.setVisible(false);
                button_defenseStage.setVisible(false);
                button_echeanceStage.setVisible(false);
                button_commenterStage.setVisible(false);
                button_stage_ajouterEcheance.setVisible(true);
                button_stage_modifierEcheance.setVisible(true);
                button_stage_supprimerEcheance.setVisible(true);
                stage_cb_recherche.setVisible(false);
                tab_stage.setVisible(false);
                tab_echeance4.setVisible(true);
                DAO_Stage stage_DB = new DAO_Stage();
                Stage stage = stage_DB.find((int) elementSelected);
                label_echeances_stage.setValue("Echéances du stage de " + stage.getOwner().toString() + " (" + stage.getProposition().getSubject() + ")");
                button_retour_Stage.setVisible(true);
                label_echeances_stage.setVisible(true);
                load_tab_echeance4(stage);
                stageSelected = stage;
            } else button_echeanceStage.setEnabled(false);
            removeAllClickShortcuts();
        });
        // Retour stages
        button_retour_Stage.addClickListener(event -> {
            hideForms();
            button_modifierStage.setVisible(true);
            button_supprimerStage.setVisible(true);
            button_evaluerStage.setVisible(true);
            button_defenseStage.setVisible(true);
            button_echeanceStage.setVisible(true);
            button_commenterStage.setVisible(true);
            button_stage_ajouterEcheance.setVisible(false);
            button_stage_modifierEcheance.setVisible(false);
            button_stage_supprimerEcheance.setVisible(false);
            stage_cb_recherche.setVisible(true);
            tab_stage.setVisible(true);
            tab_echeance4.setVisible(false);
            button_retour_Stage.setVisible(false);
            label_echeances_stage.setVisible(false);
            unselectSelectedTab();
            load_tab_stage();
            removeAllClickShortcuts();
        });
        // Ajouter echeance stage
        button_stage_ajouterEcheance.addClickListener(event -> {
            modification = false;
            stage_echeance_description.setValue("");
            stage_echeance_date.clear();
            form_stage_echeance.setVisible(true);
            button_stage_echeance_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Modifier echeance stage
        button_stage_modifierEcheance.addClickListener(event -> {
            modification = true;
            DAO_Echeance echeance_DB = new DAO_Echeance();
            Echeance echeance = echeance_DB.find((int) elementSelected);
            stage_echeance_description.setValue(echeance.getDescription());
            stage_echeance_date.setValue(echeance.getDateEcheance());
            form_stage_echeance.setVisible(true);
            button_stage_echeance_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer echeance stage
        button_stage_supprimerEcheance.addClickListener(event -> ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
            if (dialog.isConfirmed())
            {
                DAO_Echeance echeance_DB = new DAO_Echeance();
                Echeance echeance = echeance_DB.find(Integer.parseInt(elementSelected.toString()));
                if (echeance_DB.delete(echeance))
                {
                    Notification.show("L'échéance a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    elementSelected = null;
                } else
                    Notification.show("Une erreur est survenue lors de la suppression de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                load_tab_echeance4(stageSelected);
            } else
            {
                Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
            }
        }));
        // Valider echance stage
        button_stage_echeance_valider.addClickListener(event -> {
            String description = stage_echeance_description.getValue();
            Date date = stage_echeance_date.getValue();
            DAO_Stage stage_DB = new DAO_Stage();
            Stage stage = stage_DB.find((int) elementSelected);
            if (!description.isEmpty() && date != null)
            {
                DAO_Echeance echeance_DB = new DAO_Echeance();
                Echeance echeance = new Echeance(0, currentUser, Date.from(Instant.now()), date, stage, description, null);
                if (echeance_DB.create(echeance))
                {
                    Notification.show("L'échéance a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                    form_stage_echeance.setVisible(false);
                    load_tab_echeance4(stage);
                    button_stage_echeance_valider.removeClickShortcut();
                } else
                    Notification.show("Une erreur est survenue lors de l'ajout de l'échéance", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler echeance stage
        button_stage_echeance_annuler.addClickListener(event -> {
            form_stage_echeance.setVisible(false);
            button_stage_echeance_valider.removeClickShortcut();
        });
        // Commenter un stage
        button_commenterStage.addClickListener(event -> {
            hideForms();
            form_stage_commenter.setVisible(true);
            DAO_Stage stage_DB = new DAO_Stage();
            Stage stage = stage_DB.find((int) elementSelected);
            stage_commenter_commentaire.setValue(stage.getCommentaire());
            button_stage_commenter_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Valider commenter stage
        button_stage_commenter_valider.addClickListener(event -> {
            String commentaire = stage_commenter_commentaire.getValue();
            if (!commentaire.isEmpty())
            {
                DAO_Stage stage_DB = new DAO_Stage();
                Stage stage = stage_DB.find((int) elementSelected);
                stage.update(stage.getSuperviseur(), stage.getSuiveur(), stage.getDateDebut(), stage.getDateFin(), commentaire, stage.getTechnologies());
                if (stage_DB.update(stage))
                    Notification.show("Le commentaire a été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                else
                    Notification.show("Une erreur est survenue lors de l'ajout du commentaire", Notification.TYPE_ERROR_MESSAGE);
            }
            form_stage_commenter.setVisible(false);
            button_stage_commenter_valider.removeClickShortcut();
        });
        // Annuler commenter stage
        button_stage_commenter_annuler.addClickListener(event -> {
            form_stage_commenter.setVisible(false);
            button_stage_commenter_valider.removeClickShortcut();
        });
        // PROPOSITION DE STAGE
        // valider editer proposition de stage
        button_propositionStage_editer_valider.addClickListener(event -> {
            String sujet = tf_PropositionStage_editer_sujet.getValue();
            String file = "";
            if (fileUploaded != null) file = fileUploaded.getName();

            if (!sujet.isEmpty())
            {
                DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
                if (modification)
                {
                    cb_PropositionStage_editer_entreprise.setReadOnly(false);
                    PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);

                    if (file == "") file = propositionStage.getAnnexe();

                    propositionStage.update(sujet, file);
                    if (propositionStage_DB.update(propositionStage))
                    {
                        Notification.show("La proposition de stage a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_propositionStage_editer.setVisible(false);
                        load_tab_propositionStage();
                        button_propositionStage_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    LieuStage entreprise = (LieuStage) cb_PropositionStage_editer_entreprise.getValue();
                    if (entreprise != null)
                    {
                        PropositionStage propositionStage = new PropositionStage(0, currentUser, entreprise, false, sujet, file);
                        if (propositionStage_DB.create(propositionStage))
                        {
                            Notification.show("La proposition de stage a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                            form_propositionStage_editer.setVisible(false);
                            load_tab_propositionStage();
                            button_propositionStage_editer_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                    } else
                        Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                }

            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer proposition de stage
        button_propositionStage_editer_annuler.addClickListener(event -> {
            form_propositionStage_editer.setVisible(false);
            button_propositionStage_editer_valider.removeClickShortcut();
        });
        // Ajouter une proposition de stage
        button_ajouterPropositionStage.addClickListener(event -> {
            modification = false;
            cb_PropositionStage_editer_entreprise.setReadOnly(false);
            tf_PropositionStage_editer_sujet.clear();
            cb_PropositionStage_editer_entreprise.clear();
            form_propositionStage_valider.setVisible(false);
            form_propositionStage_valider2.setVisible(false);
            form_propositionStage_editer.setVisible(true);
            button_propositionStage_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_professeur_propositionStage_editer_annexe.setReceiver(receiver);
            upload_professeur_propositionStage_editer_annexe.setVisible(true);
        });
        // Modifier une proposition de stage
        button_modifierPropositionStage.addClickListener(event -> {
            modification = true;
            cb_PropositionStage_editer_entreprise.setReadOnly(false);
            DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
            PropositionStage propositionStage = propositionStage_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_PropositionStage_editer_sujet.setValue(propositionStage.getSubject());
            cb_PropositionStage_editer_entreprise.setReadOnly(false);
            cb_PropositionStage_editer_entreprise.setValue(propositionStage.getPlace());
            cb_PropositionStage_editer_entreprise.setReadOnly(true);
            form_propositionStage_valider.setVisible(false);
            form_propositionStage_valider2.setVisible(false);
            form_propositionStage_editer.setVisible(true);
            button_propositionStage_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_professeur_propositionStage_editer_annexe.setReceiver(receiver);
            upload_professeur_propositionStage_editer_annexe.setVisible(true);
        });
        // Supprimer une proposition de stage
        button_supprimerPropositionStage.addClickListener(event -> {
            hideForms();
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
                    PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);

                    if (propositionStage_DB.delete(propositionStage))
                    {
                        elementSelected = null;
                        Notification.show("La proposition de stage a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_propositionStage();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        /* Valider une proposition de stage */
        button_validerPropositionStage.addClickListener(event -> {
            DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
            PropositionStage propositionStage = propositionStage_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_PropositionStage_valider_sujet.setValue(propositionStage.getSubject());
            form_propositionStage_editer.setVisible(false);
            form_propositionStage_valider.setVisible(true);
            form_propositionStage_valider2.setVisible(true);
            button_propositionStage_valider_valider.setClickShortcut(KeyCode.ENTER);
        });
        // valider valider proposition de stage
        button_propositionStage_valider_valider.addClickListener(event -> {
            String sujet = tf_PropositionStage_valider_sujet.getValue();
            Date dateDebut = date_propositionStage_valider_dateDebut.getValue();
            Date dateFin = date_propositionStage_valider_dateFin.getValue();
            Utilisateur maitreStage = (Utilisateur) cb_PropositionStage_valider_maitreDeStage.getValue();
            Utilisateur promoteur = (Utilisateur) cb_PropositionStage_valider_promoteur.getValue();
            @SuppressWarnings("unchecked") Set<Technologie> temp = (Set<Technologie>) tc_PropositionStage_valider_technologies.getValue();
            ArrayList<Technologie> technologies = new ArrayList<>(temp);
            DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
            PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);
            if (!sujet.isEmpty() && dateDebut != null && dateFin != null && maitreStage != null && promoteur != null)
            {
                if (dateDebut.before(dateFin))
                {
                    DAO_Stage stage_DB = new DAO_Stage();
                    Stage stage = new Stage(0, propositionStage.getOwner(), promoteur, maitreStage, propositionStage, dateDebut, dateFin, 0, "", technologies);
                    propositionStage.update(sujet, propositionStage.getAnnexe());
                    propositionStage.update(true);
                    if (stage_DB.create(stage) && propositionStage_DB.update(propositionStage))
                        Notification.show("Stage validé", Notification.TYPE_TRAY_NOTIFICATION);
                    else
                        Notification.show("Une erreur est survenue lors de la validation de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                    form_propositionStage_valider.setVisible(false);
                    form_propositionStage_valider2.setVisible(false);
                    load_tab_propositionStage();
                    button_propositionStage_valider_valider.removeClickShortcut();
                } else
                    Notification.show("La date de début du stage doit être strictement supérieure à la date de fin du stage", Notification.TYPE_WARNING_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler valider proposition de stage
        button_propositionStage_valider_annuler.addClickListener(event -> {
            form_propositionStage_valider.setVisible(false);
            form_propositionStage_valider2.setVisible(false);
            button_propositionStage_valider_valider.removeClickShortcut();
        });
        // DEFENSE
        // valider editer defense
        button_defense_editer_valider.addClickListener(event -> {
            String local = tf_defense_local.getValue();
            Date date = date_defense_date.getValue();
            if (!local.isEmpty() && date != null)
            {
                DAO_Defense defense_DB = new DAO_Defense();
                if (modification)
                {
                    Defense defense = defense_DB.find((int) elementSelected);
                    defense.update(date, local);
                    if (defense_DB.update(defense))
                    {
                        Notification.show("La défense a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_defense_editer.setVisible(false);
                        load_tab_defense();
                        button_defense_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de la défense", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler editer defense
        button_defense_editer_annuler.addClickListener(event -> {
            form_defense_editer.setVisible(false);
            button_defense_editer_valider.removeClickShortcut();
        });
        // Modifier une defense
        button_modifierDefense.addClickListener(event -> {
            modification = true;
            DAO_Defense defense_DB = new DAO_Defense();
            Defense defense = defense_DB.find(Integer.parseInt(elementSelected.toString()));
            Date date = defense.getDate();
            tf_defense_local.setValue(defense.getLocal());
            date_defense_date.setValue(date);
            form_defense_editer.setVisible(true);
            button_defense_editer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer une defense
        button_supprimerDefense.addClickListener(event -> {
            form_defense_editer.setVisible(false);
            form_defense_evaluer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Defense defense_DB = new DAO_Defense();
                    Defense defense = defense_DB.find((int) elementSelected);
                    if (defense_DB.delete(defense))
                        Notification.show("La défense a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    else
                        Notification.show("Un problème est survenu lors de la suppression de la défense", Notification.TYPE_ERROR_MESSAGE);
                    elementSelected = null;
                    load_tab_defense();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Retour défenses
        button_retour_Defense.addClickListener(event -> {
            button_modifierDefense.setVisible(true);
            button_supprimerDefense.setVisible(true);
            button_evaluerDefense.setVisible(true);
            button_ajouter_evaluationDefense.setVisible(false);
            button_modifier_evaluationDefense.setVisible(false);
            button_supprimer_evaluationDefense.setVisible(false);
            button_retour_Defense.setVisible(false);
            tab_defense.setVisible(true);
            tab_evaluationDefense.setVisible(false);
            load_tab_defense();
            form_defense_editer.setVisible(false);
            form_defense_evaluer.setVisible(false);
            removeAllClickShortcuts();
        });
        // Evaluer une defense
        button_evaluerDefense.addClickListener(event -> {
            load_cb_defense();
            button_modifierDefense.setVisible(false);
            button_supprimerDefense.setVisible(false);
            button_evaluerDefense.setVisible(false);
            button_ajouter_evaluationDefense.setVisible(true);
            button_modifier_evaluationDefense.setVisible(true);
            button_supprimer_evaluationDefense.setVisible(true);
            button_retour_Defense.setVisible(true);
            tab_defense.setVisible(false);
            tab_evaluationDefense.setVisible(true);
            DAO_Defense defense_DB = new DAO_Defense();
            Defense defense = defense_DB.find((int) elementSelected);
            defenseSelected = defense;
            load_tab_evaluationDefense(defense);
            form_defense_editer.setVisible(false);
            form_defense_evaluer.setVisible(false);
            removeAllClickShortcuts();
        });
        // Ajouter une evaluation de défense
        button_ajouter_evaluationDefense.addClickListener(event -> {
            modification = false;
            defense_evaluer_critere.clear();
            defense_evaluer_note.clear();
            defense_evaluer_commentaire.setValue("");
            defense_evaluer_critere.setVisible(true);
            form_defense_evaluer.setVisible(true);
            button_defense_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Modifier une evaluation de défense
        button_modifier_evaluationDefense.addClickListener(event -> {
            modification = true;
            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
            Evaluation evaluation = evaluation_DB.find((int) elementSelected);
            defense_evaluer_critere.clear();
            defense_evaluer_note.setValue(((Double) evaluation.getNote()).toString());
            defense_evaluer_commentaire.setValue(evaluation.getCommentaire());
            defense_evaluer_critere.setVisible(false);
            form_defense_evaluer.setVisible(true);
            button_defense_evaluer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // Supprimer une evaluation de défense
        button_supprimer_evaluationDefense.addClickListener(event -> {
            form_defense_evaluer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                    if (evaluation_DB.delete(evaluation))
                    {
                        // Mise à jour des points de la défense
                        if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                        else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                        Notification.show("L'évaluation a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                        elementSelected = null;
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_evaluationDefense(defenseSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // Valider evaluer defense
        button_defense_evaluer_valider.addClickListener(event -> {
            try
            {
                Double note = Double.parseDouble(defense_evaluer_note.getValue());
                String commentaire = defense_evaluer_commentaire.getValue();
                Date date = Date.from(Instant.now());
                if (modification)
                {
                    if (note > 0 && commentaire != null)
                    {
                        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                        Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                        CritereEvaluation critere = evaluation.getCritere();
                        if (critere != null && note <= critere.getNote())
                        {
                            evaluation.update(note, commentaire);
                            if (evaluation_DB.update(evaluation))
                            {
                                // Mise à jour des points de la défense
                                if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                                else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                                Notification.show("L'évaluation a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                                form_defense_evaluer.setVisible(false);
                                load_tab_evaluationDefense(defenseSelected);
                                button_defense_evaluer_valider.removeClickShortcut();
                            } else
                                Notification.show("Une erreur est survenue lors de la modification de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                        } else
                        {
                            Notification.show("La note ne peut pas dépasser la note maximale du critère", Notification.TYPE_WARNING_MESSAGE);
                        }
                    } else
                    {
                        Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                    }
                } else
                {
                    CritereEvaluation critere = (CritereEvaluation) defense_evaluer_critere.getValue();
                    if (critere != null && note != null && note > 0 && note <= critere.getNote())
                    {
                        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                        Evaluation evaluation = new Evaluation(0, date, currentUser, critere, defenseSelected, note, commentaire);
                        if (evaluation_DB.create(evaluation))
                        {
                            // Mise à jour des points de la défense
                            if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                            else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                            Notification.show("L'évaluation a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                            form_defense_evaluer.setVisible(false);
                            load_tab_evaluationDefense(defenseSelected);
                            button_defense_evaluer_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else if (note > critere.getNote())
                        Notification.show("La note ne peut pas dépasser la note maximale du critère", Notification.TYPE_WARNING_MESSAGE);
                    else
                        Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
                }
            }
            catch (NumberFormatException e)
            {
                Notification.show("Veuillez vérifier les points accordés à l'évaluation", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // Annuler valider defense
        button_defense_evaluer_annuler.addClickListener(event -> {
            form_defense_evaluer.setVisible(false);
            button_defense_evaluer_valider.removeClickShortcut();
        });
        // ECHEANCES
        // Ajouter echeance
        button_ajouterEcheance.addClickListener(event -> {
            modification = false;
            tf_echeance_ajouter_description.setValue("");
            date_echeance_ajouter_date.clear();
            form_echeance_editer.setVisible(true);
            form_echeance_editer2.setVisible(true);
            button_echeance_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_echeance_ajouter_annexe.setReceiver(receiver);
            upload_echeance_ajouter_annexe.setVisible(true);
        });
        // Modifier echeance
        button_modifierEcheance.addClickListener(event -> {
            modification = true;
            DAO_Echeance echeance_DB = new DAO_Echeance();
            Echeance echeance = echeance_DB.find(Integer.parseInt(elementSelected.toString()));
            tf_echeance_ajouter_description.setValue(echeance.getDescription());
            date_echeance_ajouter_date.setValue(echeance.getDateEcheance());
            HashSet<Utilisateur> preselected = new HashSet<>(echeance.getUsers());
            tc_echeance_ajouter_utilisateurs.setValue(preselected);
            tc_echeance_ajouter_utilisateurs.setImmediate(true);
            form_echeance_editer.setVisible(true);
            form_echeance_editer2.setVisible(true);
            button_echeance_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_echeance_ajouter_annexe.setReceiver(receiver);
            upload_echeance_ajouter_annexe.setVisible(true);
        });
        // Supprimer echeance
        button_supprimerEcheance.addClickListener(event -> ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
            if (dialog.isConfirmed())
            {
                DAO_Echeance echeance_DB = new DAO_Echeance();
                Echeance echeance = echeance_DB.find(Integer.parseInt(elementSelected.toString()));
                if (echeance_DB.delete(echeance))
                {
                    Notification.show("L'échéance a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    elementSelected = null;
                } else
                    Notification.show("Une erreur est survenue lors de la suppression de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                load_tab_echeance2();
            } else
            {
                Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
            }
        }));
        // valider editer echeance
        button_echeance_editer_valider.addClickListener(event -> {
            String description = tf_echeance_ajouter_description.getValue();
            Date date = date_echeance_ajouter_date.getValue();
            @SuppressWarnings("unchecked") Set<Utilisateur> temp = (Set<Utilisateur>) tc_echeance_ajouter_utilisateurs.getValue();
            ArrayList<Utilisateur> etudiants = new ArrayList<>(temp);
            String file = "";
            if (fileUploaded != null) file = fileUploaded.getName();

            if (!description.isEmpty() && date != null && etudiants.size() > 0)
            {
                DAO_Echeance echeance_DB = new DAO_Echeance();
                if (modification)
                {
                    Echeance echeance = echeance_DB.find((int) elementSelected);

                    if (file == "") file = echeance.getAnnexe();

                    echeance.update(date, description, file);
                    echeance.update(etudiants);
                    if (echeance_DB.update(echeance))
                    {
                        Notification.show("L'échéance a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_echeance_editer.setVisible(false);
                        form_echeance_editer2.setVisible(false);
                        load_tab_echeance2();
                        button_echeance_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    Echeance echeance = new Echeance(0, currentUser, Date.from(Instant.now()), date, etudiants, description, file);
                    if (echeance_DB.create(echeance))
                    {
                        Notification.show("L'échéance a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_echeance_editer.setVisible(false);
                        form_echeance_editer2.setVisible(false);
                        load_tab_echeance2();
                        button_echeance_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout de l'échéance", Notification.TYPE_ERROR_MESSAGE);
                }

            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // annuler editer echeance
        button_echeance_editer_annuler.addClickListener(event -> {
            form_echeance_editer.setVisible(false);
            form_echeance_editer2.setVisible(false);
            button_echeance_editer_valider.removeClickShortcut();
        });
        // * * * ETUDIANT * * *
        // proposition de stage
        // Ajouter entreprise
        button_etudiant_ajouterEntreprise.addClickListener(event -> {
            form_propositionStage_etudiant_editer.setVisible(false);
            form_propositionStage_etudiant_ajouterEntreprise.setVisible(true);
            button_entreprise_valider2.setClickShortcut(KeyCode.ENTER);
        });
        // Valider ajouter entreprise
        button_entreprise_valider2.addClickListener(event -> {
            String nomEntreprise = tf_entreprise_nom2.getValue();
            String adresse = ta_entreprise_adresse2.getValue();
            String tel = tf_entreprise_telephone2.getValue();
            String contact = tf_entreprise_personneDeContact2.getValue();
            String mail = tf_entreprise_adresseMail2.getValue();
            if (!nomEntreprise.isEmpty() && !adresse.isEmpty() && !contact.isEmpty())
            {
                DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
                LieuStage lieuStage = new LieuStage(0, currentUser, nomEntreprise, adresse, contact, tel, mail);
                if (lieuStage_DB.create(lieuStage))
                {
                    Notification.show("Le lieu de stage a bien été ajouté", Notification.TYPE_TRAY_NOTIFICATION);
                    form_propositionStage_etudiant_editer.setVisible(true);
                    form_propositionStage_etudiant_ajouterEntreprise.setVisible(false);
                    load_cb_propositionStage2();
                    button_entreprise_valider2.removeClickShortcut();
                } else
                    Notification.show("Une erreur est survenue lors de l'ajout du lieu de stage", Notification.TYPE_ERROR_MESSAGE);
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // Annuler ajouter entreprise
        button_entreprise_annuler2.addClickListener(event -> {
            form_propositionStage_etudiant_editer.setVisible(true);
            form_propositionStage_etudiant_ajouterEntreprise.setVisible(false);
            button_entreprise_valider2.removeClickShortcut();
        });

        // ajouter
        button_ajouterPropositionStage3.addClickListener(event -> {
            modification = false;
            cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(false);
            tf_PropositionStage_etudiant_editer_sujet.clear();
            cb_PropositionStage_etudiant_editer_entreprise.clear();
            form_propositionStage_etudiant_editer.setVisible(true);
            button_propositionStage_etudiant_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_etudiant_propositionStage_editer_annexe.setReceiver(receiver);
            upload_etudiant_propositionStage_editer_annexe.setVisible(true);
        });
        // modifier
        button_modifierPropositionStage3.addClickListener(event -> {
            modification = true;
            DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
            PropositionStage propositionStage = propositionStage_DB.find(Integer.parseInt(elementSelected.toString()));
            cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(false);
            tf_PropositionStage_etudiant_editer_sujet.setValue(propositionStage.getSubject());
            cb_PropositionStage_etudiant_editer_entreprise.setValue(propositionStage.getPlace());
            cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(true);
            form_propositionStage_etudiant_editer.setVisible(true);
            button_propositionStage_etudiant_editer_valider.setClickShortcut(KeyCode.ENTER);
            AnnexeUploader receiver = new AnnexeUploader();
            fileUploaded = null;
            upload_etudiant_propositionStage_editer_annexe.setReceiver(receiver);
            upload_etudiant_propositionStage_editer_annexe.setVisible(true);
        });
        // valider editer
        button_propositionStage_etudiant_editer_valider.addClickListener(event -> {
            String sujet = tf_PropositionStage_etudiant_editer_sujet.getValue();
            String file = "";
            if (fileUploaded != null) file = fileUploaded.getName();

            if (!sujet.isEmpty())
            {
                DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
                if (modification)
                {
                    cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(false);
                    PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);

                    if (file == "") file = propositionStage.getAnnexe();

                    propositionStage.update(sujet, file);
                    if (propositionStage_DB.update(propositionStage))
                    {
                        Notification.show("La proposition de stage a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_propositionStage_etudiant_editer.setVisible(false);
                        load_tab_propositionStage2();
                        button_propositionStage_etudiant_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    LieuStage entreprise = (LieuStage) cb_PropositionStage_etudiant_editer_entreprise.getValue();
                    PropositionStage propositionStage = new PropositionStage(0, currentUser, entreprise, false, sujet, file);
                    if (propositionStage_DB.create(propositionStage))
                    {
                        Notification.show("La proposition de stage a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_propositionStage_etudiant_editer.setVisible(false);
                        load_tab_propositionStage2();
                        button_propositionStage_etudiant_editer_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de l'ajout de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                }
            } else
                Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
        });
        // annuler editer
        button_propositionStage_etudiant_editer_annuler.addClickListener(event -> {
            form_propositionStage_etudiant_editer.setVisible(false);
            button_propositionStage_etudiant_editer_valider.removeClickShortcut();
        });
        // supprimer
        button_supprimerPropositionStage3.addClickListener(event -> {
            form_propositionStage_etudiant_editer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
                    PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);
                    if (propositionStage_DB.delete(propositionStage))
                    {
                        elementSelected = null;
                        Notification.show("La proposition de stage a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de la proposition de stage", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_propositionStage2();
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });

        // dupliquer
        button_dupliquerPropositionStage3.addClickListener(event -> {
            modification = false;
            DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
            PropositionStage propositionStage = propositionStage_DB.find((int) elementSelected);
            cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(false);
            tf_PropositionStage_etudiant_editer_sujet.setValue(propositionStage.getSubject());
            cb_PropositionStage_etudiant_editer_entreprise.setValue(propositionStage.getPlace());
            cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(true);
            form_propositionStage_etudiant_editer.setVisible(true);
            button_propositionStage_etudiant_editer_valider.setClickShortcut(KeyCode.ENTER);
        });
        // * * * MAITRE DE STAGE * * *
        // evaluer stage
        button_evaluerStage_maitreDeStage.addClickListener(event -> {
            stage_evaluer_maitreDeStage_critere.setVisible(true);
            stage_evaluer_maitreDeStage_critere.clear();
            stage_evaluer_maitreDeStage_note.clear();
            stage_evaluer_maitreDeStage_commentaire.setValue("");
            form_stage_maitreDeStage_evaluer.setVisible(true);
            button_evaluerStage_maitreDeStage.setIcon(FontAwesome.PLUS);
            button_retour_maitreDeStage.setVisible(true);
            button_modifierEvaluation_maitreDeStage.setVisible(true);
            button_supprimerEvaluation_maitreDeStage.setVisible(true);
            tab_stage3.setVisible(false);
            tab_evaluations_maitreStage.setVisible(true);
            label_maitreStage_form.setValue("Nouvelle évaluation");
            modification = false;
            DAO_Stage stage_DB = new DAO_Stage();
            stageSelected = stage_DB.find(Integer.parseInt(elementSelected.toString()));
            load_tab_evaluations_maitreStage(stageSelected);
            button_stage_evaluer_maitreDeStage_valider.setClickShortcut(KeyCode.ENTER);
        });
        // modifier evaluation
        button_modifierEvaluation_maitreDeStage.addClickListener(event -> {
            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
            Evaluation evaluation = evaluation_DB.find(Integer.parseInt(elementSelected.toString()));
            stage_evaluer_maitreDeStage_critere.clear();
            stage_evaluer_maitreDeStage_critere.setVisible(false);
            stage_evaluer_maitreDeStage_note.setValue(((Double) evaluation.getNote()).toString());
            stage_evaluer_maitreDeStage_commentaire.setValue(evaluation.getCommentaire());
            label_maitreStage_form.setValue("Modifier une évaluation");
            modification = true;
            form_stage_maitreDeStage_evaluer.setVisible(true);
            button_stage_evaluer_maitreDeStage_valider.setClickShortcut(KeyCode.ENTER);
        });
        // supprimer evaluation
        button_supprimerEvaluation_maitreDeStage.addClickListener(event -> {
            form_stage_maitreDeStage_evaluer.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                    if (evaluation_DB.delete(evaluation))
                    {
                        // Mise à jour des points de la défense
                        if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());

                        elementSelected = null;
                        Notification.show("L'évaluation a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_evaluations_maitreStage(stageSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // retour
        button_retour_maitreDeStage.addClickListener(event -> {
            form_stage_maitreDeStage_evaluer.setVisible(false);
            button_evaluerStage_maitreDeStage.setIcon(FontAwesome.STAR);
            button_retour_maitreDeStage.setVisible(false);
            button_modifierEvaluation_maitreDeStage.setVisible(false);
            button_supprimerEvaluation_maitreDeStage.setVisible(false);
            tab_stage3.setVisible(true);
            tab_evaluations_maitreStage.setVisible(false);
            stageSelected = null;
            unselectSelectedTab();
            load_tab_stage3();
            removeAllClickShortcuts();
        });
        // valider evaluation stage
        button_stage_evaluer_maitreDeStage_valider.addClickListener(event -> {
            Double note = Double.parseDouble(stage_evaluer_maitreDeStage_note.getValue());
            String commentaire = stage_evaluer_maitreDeStage_commentaire.getValue();
            Date date = Date.from(Instant.now());
            if (modification)
            {
                DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                CritereEvaluation critere = evaluation.getCritere();
                if (note >= 0 && note <= critere.getNote())
                {
                    evaluation.update(note, commentaire);
                    if (evaluation_DB.update(evaluation))
                    {
                        // Mise à jour des points de la défense
                        if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());

                        Notification.show("L'évaluation a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_stage_maitreDeStage_evaluer.setVisible(false);
                        load_tab_evaluations_maitreStage(stageSelected);
                        button_stage_evaluer_maitreDeStage_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    if (note > critere.getNote())
                        Notification.show("La note doit être inférieure à la note maximale", Notification.TYPE_WARNING_MESSAGE);
                    else
                        Notification.show("La doit être supérieure ou égale à 0", Notification.TYPE_WARNING_MESSAGE);
                }
            } else
            {
                CritereEvaluation critere = (CritereEvaluation) stage_evaluer_maitreDeStage_critere.getValue();
                if (critere != null)
                {
                    if (note >= 0 && note <= critere.getNote())
                    {
                        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                        DAO_Stage stage_DB = new DAO_Stage();
                        Stage stage = stage_DB.find((int) elementSelected);
                        Evaluation evaluation = new Evaluation(0, date, currentUser, critere, stage, note, commentaire);
                        if (evaluation_DB.create(evaluation))
                        {
                            // Mise à jour des points de la défense
                            calculPointsStage(stage);

                            Notification.show("L'évaluation a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                            form_stage_maitreDeStage_evaluer.setVisible(false);
                            load_tab_evaluations_maitreStage(stageSelected);
                            button_stage_evaluer_maitreDeStage_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout de l'évaluation", Notification.TYPE_ERROR_MESSAGE);

                    } else
                    {
                        if (note > critere.getNote())
                            Notification.show("La note doit être inférieure à la note maximale", Notification.TYPE_WARNING_MESSAGE);
                        else
                            Notification.show("La doit être supérieure ou égale à 0", Notification.TYPE_WARNING_MESSAGE);
                    }
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // annuler evaluation stage
        button_stage_evaluer_maitreDeStage_annuler.addClickListener(event -> {
            form_stage_maitreDeStage_evaluer.setVisible(false);
            button_stage_evaluer_maitreDeStage_valider.removeClickShortcut();
        });
        // * * * PRESIDENT DE JURY * * *
        // evaluer defense
        button_evaluerDefense_presidentDeJury.addClickListener(event -> {
            stage_evaluer_maitreDeStage_critere.clear();
            stage_evaluer_maitreDeStage_critere.setVisible(true);
            defense_evaluer_presidentDeJury_note.clear();
            defense_evaluer_presidentDeJury_commentaire.setValue("");
            form_defense_evaluer_presidentDeJury.setVisible(true);
            button_evaluerDefense_presidentDeJury.setIcon(FontAwesome.PLUS);
            button_retour_presidentDeJury.setVisible(true);
            button_modifierEvaluation_presidentDeJury.setVisible(true);
            button_supprimerEvaluation_presidentDeJury.setVisible(true);
            tab_defense3.setVisible(false);
            tab_evaluations_presidentJury.setVisible(true);
            label_form_presidentJury.setValue("Nouvelle évaluation");
            modification = false;
            DAO_Defense defense_DB = new DAO_Defense();
            defenseSelected = defense_DB.find(Integer.parseInt(elementSelected.toString()));
            load_tab_evaluations_presidentJury(defenseSelected);
            button_defense_evaluer_presidentDeJury_valider.setClickShortcut(KeyCode.ENTER);
        });
        // modifier evaluation
        button_modifierEvaluation_presidentDeJury.addClickListener(event -> {
            DAO_Evaluation evaluation_DB = new DAO_Evaluation();
            Evaluation evaluation = evaluation_DB.find(Integer.parseInt(elementSelected.toString()));
            stage_evaluer_maitreDeStage_critere.clear();
            stage_evaluer_maitreDeStage_critere.setVisible(false);
            defense_evaluer_presidentDeJury_note.setValue(((Double) evaluation.getNote()).toString());
            defense_evaluer_presidentDeJury_commentaire.setValue(evaluation.getCommentaire());
            label_form_presidentJury.setValue("Modifier une évaluation");
            modification = true;
            form_defense_evaluer_presidentDeJury.setVisible(true);
            button_defense_evaluer_presidentDeJury_valider.setClickShortcut(KeyCode.ENTER);
        });
        // supprimer evaluation
        button_supprimerEvaluation_presidentDeJury.addClickListener(event -> {
            form_defense_evaluer_presidentDeJury.setVisible(false);
            ConfirmDialog.show((UI) getParent(), "Veuillez confirmer:", "Etes-vous certain de vouloir supprimer ?", "Oui", "Non", dialog -> {
                if (dialog.isConfirmed())
                {
                    DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                    Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                    if (evaluation_DB.delete(evaluation))
                    {
                        // Mise à jour des points de la défense
                        if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                        else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                        elementSelected = null;
                        Notification.show("L'évaluation a bien été supprimée", Notification.TYPE_TRAY_NOTIFICATION);
                    } else
                        Notification.show("Une erreur est survenue lors de la suppression de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    load_tab_evaluations_presidentJury(defenseSelected);
                } else
                {
                    Notification.show("Suppression annulée", Notification.TYPE_TRAY_NOTIFICATION);
                }
            });
        });
        // retour
        button_retour_presidentDeJury.addClickListener(event -> {
            form_defense_evaluer_presidentDeJury.setVisible(false);
            button_evaluerDefense_presidentDeJury.setIcon(FontAwesome.STAR);
            button_retour_presidentDeJury.setVisible(false);
            button_modifierEvaluation_presidentDeJury.setVisible(false);
            button_supprimerEvaluation_presidentDeJury.setVisible(false);
            tab_defense3.setVisible(true);
            tab_evaluations_presidentJury.setVisible(false);
            defenseSelected = null;
            unselectSelectedTab();
            load_tab_defense3();
            removeAllClickShortcuts();
        });
        // valider evaluation defense
        button_defense_evaluer_presidentDeJury_valider.addClickListener(event -> {
            Double note = Double.parseDouble(defense_evaluer_presidentDeJury_note.getValue());
            String commentaire = defense_evaluer_presidentDeJury_commentaire.getValue();
            Date date = Date.from(Instant.now());
            if (modification)
            {
                DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                Evaluation evaluation = evaluation_DB.find((int) elementSelected);
                CritereEvaluation critere = evaluation.getCritere();
                if (note >= 0 && note <= critere.getNote())
                {
                    evaluation.update(note, commentaire);
                    if (evaluation_DB.update(evaluation))
                    {
                        // Mise à jour des points de la défense
                        if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                        else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                        Notification.show("L'évaluation a bien été modifiée", Notification.TYPE_TRAY_NOTIFICATION);
                        form_stage_maitreDeStage_evaluer.setVisible(false);
                        load_tab_evaluations_presidentJury(defenseSelected);
                        button_defense_evaluer_presidentDeJury_valider.removeClickShortcut();
                    } else
                        Notification.show("Une erreur est survenue lors de la modification de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                } else
                {
                    if (note > critere.getNote())
                        Notification.show("La note doit être inférieure à la note maximale", Notification.TYPE_WARNING_MESSAGE);
                    else
                        Notification.show("La doit être supérieure ou égale à 0", Notification.TYPE_WARNING_MESSAGE);
                }
            } else
            {
                CritereEvaluation critere = (CritereEvaluation) defense_evaluer_presidentDeJury_critere.getValue();
                if (critere != null)
                {
                    if (note >= 0 && note <= critere.getNote())
                    {
                        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
                        DAO_Defense defense_DB = new DAO_Defense();
                        Defense defense = defense_DB.find((int) elementSelected);
                        Evaluation evaluation = new Evaluation(0, date, currentUser, critere, defense, note, commentaire);
                        if (evaluation_DB.create(evaluation))
                        {
                            // Mise à jour des points de la défense
                            if (evaluation.getStage() != null) calculPointsStage(evaluation.getStage());
                            else if (evaluation.getTFE() != null) calculPointsTFE(evaluation.getTFE());

                            Notification.show("L'évaluation a bien été ajoutée", Notification.TYPE_TRAY_NOTIFICATION);
                            form_defense_evaluer_presidentDeJury.setVisible(false);
                            load_tab_evaluations_presidentJury(defenseSelected);
                            button_defense_evaluer_presidentDeJury_valider.removeClickShortcut();
                        } else
                            Notification.show("Une erreur est survenue lors de l'ajout de l'évaluation", Notification.TYPE_ERROR_MESSAGE);
                    } else
                    {
                        if (note > critere.getNote())
                            Notification.show("La note doit être inférieure à la note maximale", Notification.TYPE_WARNING_MESSAGE);
                        else
                            Notification.show("La doit être supérieure ou égale à 0", Notification.TYPE_WARNING_MESSAGE);
                    }
                } else
                    Notification.show("Veuillez compléter les champs obligatoires (*)", Notification.TYPE_WARNING_MESSAGE);
            }
        });
        // annuler evaluation defense
        button_defense_evaluer_presidentDeJury_annuler.addClickListener(event -> {
            form_defense_evaluer_presidentDeJury.setVisible(false);
            button_defense_evaluer_presidentDeJury_valider.removeClickShortcut();
        });
    }

    public void load_tab_etudiants()
    {
        tab_etudiants.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("etudiant_tfe");
        userList.addAll(user_DB.fetchAll("etudiant_tfe_stage"));
        for (Utilisateur user : userList)
            tab_etudiants.addItem(new Object[]{user.getNom(), user.getPrenom(), user.getMatricule(), user.getEmail(), user.getTelephone()}, user.getId());
        tab_etudiants.addValueChangeListener(event -> {
            selectedTab = tab_etudiants;
            form_etudiant.setVisible(false);
            elementSelected = tab_etudiants.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier2.setEnabled(boutons);
            supprimer2.setEnabled(boutons);
        });
        tf_etudiant_recherche.setImmediate(true);
        tf_etudiant_recherche.addTextChangeListener(event -> {
            tf_etudiant_recherche.setValue(event.getText());
            load_tab_etudiants_filtre();
        });
        load_cb_administration_etudiant();
        modifier2.setEnabled(false);
        supprimer2.setEnabled(false);
    }

    public void load_tab_etudiants_filtre()
    {
        tab_etudiants.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("etudiant_tfe");
        userList.addAll(user_DB.fetchAll("etudiant_tfe_stage"));
        userList.stream().filter(etudiant -> tf_etudiant_recherche.isEmpty() || (etudiant.toString().toLowerCase().contains(tf_etudiant_recherche.getValue().toLowerCase()))).forEach(etudiant -> tab_etudiants.addItem(new Object[]{etudiant.getNom(), etudiant.getPrenom(), etudiant.getMatricule(), etudiant.getEmail(), etudiant.getTelephone()}, etudiant.getId()));
    }

    public void load_tab_professeurs()
    {
        tab_professeurs.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("professeur");
        for (Utilisateur user : userList)
            tab_professeurs.addItem(new Object[]{user.getNom(), user.getPrenom(), user.getEmail(), user.getTelephone()}, user.getId());
        tab_professeurs.addValueChangeListener(event -> {
            form_prof.setVisible(false);
            elementSelected = tab_professeurs.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier.setEnabled(boutons);
            supprimer.setEnabled(boutons);
        });
        tf_professeur_recherche.setImmediate(true);
        tf_professeur_recherche.addTextChangeListener(event -> {
            tf_professeur_recherche.setValue(event.getText());
            load_tab_professeurs_filtre();
        });
        modifier.setEnabled(false);
        supprimer.setEnabled(false);
    }

    public void load_tab_professeurs_filtre()
    {
        tab_professeurs.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("professeur");
        userList.stream().filter(professeur -> tf_professeur_recherche.isEmpty() || (professeur.toString().toLowerCase().contains(tf_professeur_recherche.getValue().toLowerCase()))).forEach(professeur -> tab_professeurs.addItem(new Object[]{professeur.getNom(), professeur.getPrenom(), professeur.getEmail(), professeur.getTelephone()}, professeur.getId()));
    }

    public void load_tab_maitresDeStage()
    {
        tab_maitresDeStage.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("maitre_stage");
        for (Utilisateur user : userList)
            tab_maitresDeStage.addItem(new Object[]{user.getNom(), user.getPrenom(), user.getTelephone(), user.getEmail()}, user.getId());
        tab_maitresDeStage.addValueChangeListener(event -> {
            form_maitreDeStage.setVisible(false);
            elementSelected = tab_maitresDeStage.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier3.setEnabled(boutons);
            supprimer3.setEnabled(boutons);
        });
        tf_maitreDeStage_recherche.setImmediate(true);
        tf_maitreDeStage_recherche.addTextChangeListener(event -> {
            tf_maitreDeStage_recherche.setValue(event.getText());
            load_tab_maitresDeStage_filtre();
        });
        modifier3.setEnabled(false);
        supprimer3.setEnabled(false);
    }

    public void load_tab_maitresDeStage_filtre()
    {
        tab_maitresDeStage.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("maitre_stage");
        userList.stream().filter(maitreStage -> tf_maitreDeStage_recherche.isEmpty() || (maitreStage.toString().toLowerCase().contains(tf_maitreDeStage_recherche.getValue().toLowerCase()))).forEach(maitreStage -> tab_maitresDeStage.addItem(new Object[]{maitreStage.getNom(), maitreStage.getPrenom(), maitreStage.getTelephone(), maitreStage.getEmail()}, maitreStage.getId()));
    }

    public void load_tab_presidentsDeJury()
    {
        tab_presidentsDeJury.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("president_jury");
        for (Utilisateur user : userList)
            tab_presidentsDeJury.addItem(new Object[]{user.getNom(), user.getPrenom(), user.getTelephone(), user.getEmail()}, user.getId());
        tab_presidentsDeJury.addValueChangeListener(event -> {
            form_presidentDeJury.setVisible(false);
            elementSelected = tab_presidentsDeJury.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier4.setEnabled(boutons);
            supprimer4.setEnabled(boutons);
        });
        tf_presidentDeJury_recherche.setImmediate(true);
        tf_presidentDeJury_recherche.addTextChangeListener(event -> {
            tf_presidentDeJury_recherche.setValue(event.getText());
            load_tab_presidentsDeJury_filtre();
        });
        modifier4.setEnabled(false);
        supprimer4.setEnabled(false);
    }

    public void load_tab_presidentsDeJury_filtre()
    {
        tab_presidentsDeJury.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("president_jury");
        userList.stream().filter(presidentJury -> tf_presidentDeJury_recherche.isEmpty() || (presidentJury.toString().toLowerCase().contains(tf_presidentDeJury_recherche.getValue().toLowerCase()))).forEach(presidentJury -> tab_presidentsDeJury.addItem(new Object[]{presidentJury.getNom(), presidentJury.getPrenom(), presidentJury.getTelephone(), presidentJury.getEmail()}, presidentJury.getId()));
    }

    public void load_tab_entreprises()
    {
        tab_entreprises.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> lieuStageList = lieuStage_DB.fetchAll();
        for (LieuStage lieuStage : lieuStageList)
            tab_entreprises.addItem(new Object[]{lieuStage.getNom(), lieuStage.getAdresse(), lieuStage.getContact(), lieuStage.getTelephone()}, lieuStage.getId());
        tab_entreprises.addValueChangeListener(event -> {
            form_entreprise.setVisible(false);
            elementSelected = tab_entreprises.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier5.setEnabled(boutons);
            supprimer5.setEnabled(boutons);
        });
        tf_entreprise_recherche.setImmediate(true);
        tf_entreprise_recherche.addTextChangeListener(event -> {
            tf_entreprise_recherche.setValue(event.getText());
            load_tab_entreprises_filtre();
        });
        modifier5.setEnabled(false);
        supprimer5.setEnabled(false);
    }

    public void load_tab_entreprises_etudiant()
    {
        tab_entreprises3.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> lieuStageList = lieuStage_DB.fetchAll();
        for (LieuStage lieuStage : lieuStageList)
            tab_entreprises3.addItem(new Object[]{lieuStage.getNom(), lieuStage.getAdresse(), lieuStage.getContact(), lieuStage.getTelephone()}, lieuStage.getId());

        tf_entreprise_recherche3.setImmediate(true);
        tf_entreprise_recherche3.addTextChangeListener(event -> {
            tf_entreprise_recherche3.setValue(event.getText());
            load_tab_entreprises_etudiant_filtre();
        });
    }

    public void load_tab_entreprises_filtre()
    {
        tab_entreprises.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> lieuStageList = lieuStage_DB.fetchAll();
        lieuStageList.stream().filter(lieuStage -> tf_entreprise_recherche.isEmpty() || (lieuStage.getNom().toLowerCase().contains(tf_entreprise_recherche.getValue().toLowerCase())) || (lieuStage.getAdresse().toLowerCase().contains(tf_entreprise_recherche.getValue().toLowerCase()))).forEach(lieuStage -> tab_entreprises.addItem(new Object[]{lieuStage.getNom(), lieuStage.getAdresse(), lieuStage.getContact(), lieuStage.getTelephone()}, lieuStage.getId()));
    }

    public void load_tab_entreprises_etudiant_filtre()
    {
        tab_entreprises3.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> lieuStageList = lieuStage_DB.fetchAll();
        lieuStageList.stream().filter(lieuStage -> tf_entreprise_recherche3.isEmpty() || (lieuStage.getNom().toLowerCase().contains(tf_entreprise_recherche3.getValue().toLowerCase())) || (lieuStage.getAdresse().toLowerCase().contains(tf_entreprise_recherche3.getValue().toLowerCase()))).forEach(lieuStage -> tab_entreprises3.addItem(new Object[]{lieuStage.getNom(), lieuStage.getAdresse(), lieuStage.getContact(), lieuStage.getTelephone()}, lieuStage.getId()));
    }

    public void load_tab_criteresEvaluation()
    {
        tab_criteresEvaluation.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList = critereEvaluation_DB.fetchAll(null);
        for (CritereEvaluation critereEvaluation : critereEvaluationList)
            tab_criteresEvaluation.addItem(new Object[]{critereEvaluation.getNom(), critereEvaluation.getType(), critereEvaluation.getNote()}, critereEvaluation.getId());
        tab_criteresEvaluation.addValueChangeListener(event -> {
            form_critereEvaluation_editer.setVisible(false);
            elementSelected = tab_criteresEvaluation.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier6.setEnabled(boutons);
            supprimer6.setEnabled(boutons);
        });
        tf_evaluation_recherche.setImmediate(true);
        tf_evaluation_recherche.addTextChangeListener(event -> {
            tf_evaluation_recherche.setValue(event.getText());
            load_tab_criteresEvaluation_filtre();
        });
        modifier6.setEnabled(false);
        supprimer6.setEnabled(false);
    }

    public void load_tab_criteresEvaluation_filtre()
    {
        tab_criteresEvaluation.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList = critereEvaluation_DB.fetchAll(null);
        critereEvaluationList.stream().filter(critereEvaluation -> tf_evaluation_recherche.isEmpty() || (critereEvaluation.getNom().toLowerCase().contains(tf_evaluation_recherche.getValue().toLowerCase()))).forEach(critereEvaluation -> tab_criteresEvaluation.addItem(new Object[]{critereEvaluation.getNom(), critereEvaluation.getType(), critereEvaluation.getNote()}, critereEvaluation.getId()));
    }

    public void load_tab_technologies()
    {
        tab_technologies.removeAllItems();
        DAO_Technologie technologie_DB = new DAO_Technologie();
        ArrayList<Technologie> technologieList = technologie_DB.fetchAll();
        for (Technologie technologie : technologieList)
            tab_technologies.addItem(new Object[]{technologie.getNom(), technologie.getVersion()}, technologie.getId());
        tab_technologies.addValueChangeListener(event -> {
            form_technologie.setVisible(false);
            elementSelected = tab_technologies.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            modifier7.setEnabled(boutons);
            supprimer7.setEnabled(boutons);
        });
        tf_technologie_recherche.setImmediate(true);
        tf_technologie_recherche.addTextChangeListener(event -> {
            tf_technologie_recherche.setValue(event.getText());
            load_tab_technologies_filtre();
        });
        modifier7.setEnabled(false);
        supprimer7.setEnabled(false);
    }

    public void load_tab_technologies_filtre()
    {
        tab_technologies.removeAllItems();
        DAO_Technologie technologie_DB = new DAO_Technologie();
        ArrayList<Technologie> technologieList = technologie_DB.fetchAll();
        technologieList.stream().filter(technologie -> tf_technologie_recherche.isEmpty() || (technologie.getNom().toLowerCase().contains(tf_technologie_recherche.getValue().toLowerCase()))).forEach(technologie -> tab_technologies.addItem(new Object[]{technologie.getNom(), technologie.getVersion()}, technologie.getId()));
    }

    public void load_tab_tfe()
    {
        tab_tfe.removeAllItems();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        for (TFE tfe : tfeList)
            tab_tfe.addItem(new Object[]{tfe.getTitre(), tfe.getOwner().toString(), tfe.getPromoteur().toString(), (Double) tfe.getPoints(), (Integer) tfe.getAnneeDebut(), (Integer) tfe.getAnneeFin(),}, tfe.getId());
        tab_tfe.addValueChangeListener(event -> {
            hideForms();
            elementSelected = tab_tfe.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifierTFE.setEnabled(boutons);
            button_supprimerTFE.setEnabled(boutons);
            if (boutons)
            {
                if (currentUser.getRole().isAllowed("professor_manage_evaluations"))
                    button_evaluerTFE.setEnabled(true);
                if (currentUser.getRole().isAllowed("professor_manage_defenses"))
                    button_defenseTFE.setEnabled(true);
                if (currentUser.getRole().isAllowed("professor_manage_planning"))
                    button_echeanceTFE.setEnabled(true);
            } else
            {
                button_evaluerTFE.setEnabled(boutons);
                button_defenseTFE.setEnabled(boutons);
                button_echeanceTFE.setEnabled(boutons);
            }
        });
        button_modifierTFE.setEnabled(false);
        button_supprimerTFE.setEnabled(false);
        button_evaluerTFE.setEnabled(false);
        button_defenseTFE.setEnabled(false);
        button_echeanceTFE.setEnabled(false);
        if (tab_echeance3.isVisible()) load_tab_echeance3(tfeSelected);
        load_cb_TFE();
    }

    public void load_tab_tfe_filtre()
    {
        unselectSelectedTab();
        hideForms();
        button_modifierTFE.setEnabled(false);
        button_supprimerTFE.setEnabled(false);
        button_evaluerTFE.setEnabled(false);
        button_defenseTFE.setEnabled(false);
        button_echeanceTFE.setEnabled(false);
        tab_tfe.removeAllItems();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        Technologie technologie = (Technologie) cb_tfe_technologie.getValue();
        int anneeAcademiqueDebut = 0;
        int anneeAcademiqueFin = 0;
        if (cb_tfe_anneeAcademique.getValue() != null)
        {
            String anneeAcademique[] = ((String) cb_tfe_anneeAcademique.getValue()).split("-");
            anneeAcademiqueDebut = Integer.parseInt(anneeAcademique[0]);
            anneeAcademiqueFin = Integer.parseInt(anneeAcademique[1]);
        }
        String nom = ((String) tf_tfe_etudiant.getValue()).toLowerCase();
        for (TFE tfe : tfeList)
        {
            ArrayList<Technologie> technologiesTFE = tfe.getTechnologies();
            int anneeDebut = tfe.getAnneeDebut();
            int anneeFin = tfe.getAnneeFin();
            String nomPrenom = (tfe.getOwner().toString()).toLowerCase();
            if (((technologie != null && technologiesTFE.contains(technologie)) || technologie == null) && ((cb_tfe_anneeAcademique.getValue() != null && ((anneeDebut >= anneeAcademiqueDebut) && (anneeFin == anneeAcademiqueDebut || anneeFin == anneeAcademiqueFin))) || cb_tfe_anneeAcademique.getValue() == null) && ((!nom.isEmpty() && nomPrenom.contains(nom)) || nom.isEmpty()))
                tab_tfe.addItem(new Object[]{tfe.getTitre(), tfe.getOwner().toString(), tfe.getPromoteur().toString(), (Double) tfe.getPoints(), (Integer) tfe.getAnneeDebut(), (Integer) tfe.getAnneeFin(),}, tfe.getId());
        }
    }

    public void load_tab_tfe2_filtre()
    {
        unselectSelectedTab();
        tab_tfe2.removeAllItems();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        Technologie technologie = (Technologie) cb_tfe_technologie2.getValue();
        int anneeAcademiqueDebut = 0;
        int anneeAcademiqueFin = 0;
        if (cb_tfe_anneeAcademique2.getValue() != null)
        {
            String anneeAcademique[] = ((String) cb_tfe_anneeAcademique2.getValue()).split("-");
            anneeAcademiqueDebut = Integer.parseInt(anneeAcademique[0]);
            anneeAcademiqueFin = Integer.parseInt(anneeAcademique[1]);
        }
        String nom = ((String) tf_tfe_etudiant2.getValue()).toLowerCase();
        for (TFE tfe : tfeList)
        {
            ArrayList<Technologie> technologiesTFE = tfe.getTechnologies();
            int anneeDebut = tfe.getAnneeDebut();
            int anneeFin = tfe.getAnneeFin();
            String nomPrenom = (tfe.getOwner().toString()).toLowerCase();
            if (((technologie != null && technologiesTFE.contains(technologie)) || technologie == null) && ((cb_tfe_anneeAcademique2.getValue() != null && ((anneeDebut >= anneeAcademiqueDebut) && (anneeFin == anneeAcademiqueDebut || anneeFin == anneeAcademiqueFin))) || cb_tfe_anneeAcademique2.getValue() == null) && ((nom != null && nomPrenom.contains(nom))))
                tab_tfe2.addItem(new Object[]{tfe.getTitre(), tfe.getOwner().toString(), tfe.getPromoteur().toString(), (Integer) tfe.getAnneeDebut(), (Integer) tfe.getAnneeFin(),}, tfe.getId());
        }
    }

    public void load_tab_stage()
    {
        tab_stage.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        for (Stage stage : stageList)
            tab_stage.addItem(new Object[]{stage.getProposition().getSubject(), stage.getOwner().toString(), stage.getSuperviseur().toString(), DateFormat.getDateInstance().format(stage.getDateDebut()), DateFormat.getDateInstance().format(stage.getDateFin()), stage.getProposition().getPlace().getNom(), stage.getPoints()}, stage.getId());
        tab_stage.addValueChangeListener(event -> {
            hideForms();
            elementSelected = tab_stage.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifierStage.setEnabled(boutons);
            button_supprimerStage.setEnabled(boutons);
            button_commenterStage.setEnabled(boutons);
            if (boutons)
            {
                if (currentUser.getRole().isAllowed("professor_manage_evaluations"))
                    button_evaluerStage.setEnabled(true);
                if (currentUser.getRole().isAllowed("professor_manage_defenses"))
                    button_defenseStage.setEnabled(true);
                if (currentUser.getRole().isAllowed("professor_manage_planning"))
                    button_echeanceStage.setEnabled(true);
            } else
            {
                button_evaluerStage.setEnabled(boutons);
                button_defenseStage.setEnabled(boutons);
                button_echeanceStage.setEnabled(boutons);
            }
        });
        button_modifierStage.setEnabled(false);
        button_supprimerStage.setEnabled(false);
        button_evaluerStage.setEnabled(false);
        button_defenseStage.setEnabled(false);
        button_echeanceStage.setEnabled(false);
        button_commenterStage.setEnabled(false);
        if (tab_echeance4.isVisible()) load_tab_echeance4(stageSelected);
        load_cb_stage();
    }

    public void load_tab_stage_filtre()
    {
        unselectSelectedTab();
        hideForms();
        button_modifierStage.setEnabled(false);
        button_supprimerStage.setEnabled(false);
        button_evaluerStage.setEnabled(false);
        button_defenseStage.setEnabled(false);
        button_echeanceStage.setEnabled(false);
        button_commenterStage.setEnabled(false);
        tab_stage.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        Technologie technologie = (Technologie) cb_stage_technologie.getValue();
        LieuStage lieuStage = (LieuStage) cb_stage_entreprise.getValue();
        String adresse = tf_stage_adresse.getValue();
        int anneeAcademiqueDebut = 0;
        int anneeAcademiqueFin = 0;
        if (cb_stage_anneeAcademique.getValue() != null)
        {
            String anneeAcademique[] = ((String) cb_stage_anneeAcademique.getValue()).split("-");
            anneeAcademiqueDebut = Integer.parseInt(anneeAcademique[0]);
            anneeAcademiqueFin = Integer.parseInt(anneeAcademique[1]);
        }
        for (Stage stage : stageList)
        {
            ArrayList<Technologie> technologiesStage = stage.getTechnologies();
            LieuStage lieuStageStage = stage.getProposition().getPlace();
            String adresseStage = stage.getProposition().getPlace().getAdresse();
            int anneeDebut = stage.getDateDebut().getYear() + 1900;
            int anneeFin = stage.getDateFin().getYear() + 1900;
            if (((technologie != null && technologiesStage.contains(technologie)) || technologie == null) && ((lieuStage != null && lieuStageStage.equals(lieuStage)) || lieuStage == null) && ((!adresseStage.isEmpty() && adresseStage.toLowerCase().contains(adresse.toLowerCase())) || adresseStage.isEmpty()) && ((cb_stage_anneeAcademique.getValue() != null && ((anneeDebut >= anneeAcademiqueDebut) && (anneeFin == anneeAcademiqueDebut || anneeFin == anneeAcademiqueFin))) || cb_stage_anneeAcademique.getValue() == null))
                tab_stage.addItem(new Object[]{stage.getProposition().getSubject(), stage.getOwner().toString(), stage.getSuperviseur().toString(), DateFormat.getDateInstance().format(stage.getDateDebut()), DateFormat.getDateInstance().format(stage.getDateFin()), stage.getProposition().getPlace().getNom(), stage.getPoints()}, stage.getId());
        }
    }

    public void load_tab_stage2_filtre()
    {
        unselectSelectedTab();
        tab_stage2.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        Technologie technologie = (Technologie) cb_stage_technologie3.getValue();
        LieuStage lieuStage = (LieuStage) cb_stage_entreprise3.getValue();
        String adresse = tf_stage_adresse3.getValue();
        int anneeAcademiqueDebut = 0;
        int anneeAcademiqueFin = 0;
        if (cb_stage_anneeAcademique3.getValue() != null)
        {
            String anneeAcademique[] = ((String) cb_stage_anneeAcademique3.getValue()).split("-");
            anneeAcademiqueDebut = Integer.parseInt(anneeAcademique[0]);
            anneeAcademiqueFin = Integer.parseInt(anneeAcademique[1]);
        }
        for (Stage stage : stageList)
        {
            ArrayList<Technologie> technologiesStage = stage.getTechnologies();
            LieuStage lieuStageStage = stage.getProposition().getPlace();
            String adresseStage = stage.getProposition().getPlace().getAdresse();
            int anneeDebut = stage.getDateDebut().getYear() + 1900;
            int anneeFin = stage.getDateFin().getYear() + 1900;
            if (((technologie != null && technologiesStage.contains(technologie)) || technologie == null) && ((lieuStage != null && lieuStageStage.equals(lieuStage)) || lieuStage == null) && ((!adresseStage.isEmpty() && adresseStage.toLowerCase().contains(adresse.toLowerCase())) || adresseStage.isEmpty()) && ((cb_stage_anneeAcademique3.getValue() != null && ((anneeDebut >= anneeAcademiqueDebut) && (anneeFin == anneeAcademiqueDebut || anneeFin == anneeAcademiqueFin))) || cb_stage_anneeAcademique3.getValue() == null))
                tab_stage2.addItem(new Object[]{stage.getProposition().getSubject(), stage.getOwner().toString(), stage.getSuperviseur().toString(), DateFormat.getDateInstance().format(stage.getDateDebut()), DateFormat.getDateInstance().format(stage.getDateFin()), stage.getProposition().getPlace().getNom()}, stage.getId());
        }
    }

    public void load_tab_propositionStage()
    {
        tab_propositionStage.removeAllItems();
        DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
        ArrayList<PropositionStage> propositionStageList = propositionStage_DB.fetchAll(null, null);
        for (PropositionStage propositionStage : propositionStageList)
            tab_propositionStage.addItem(new Object[]{propositionStage.getSubject(), propositionStage.getOwner().toString(), propositionStage.getPlace().getAdresse(), propositionStage.getPlace().getNom()}, propositionStage.getId());
        tab_propositionStage.addValueChangeListener(event -> {
            hideForms();
            elementSelected = tab_propositionStage.getValue();
            boolean boutons = false;
            if (elementSelected != null)
            {
                boutons = true;
                DAO_PropositionStage propositionStage_DB1 = new DAO_PropositionStage();
                PropositionStage propositionStage = propositionStage_DB1.find((int) elementSelected);
                if (propositionStage.getOwner().getRole().getNom().equals("professeur"))
                    button_validerPropositionStage.setEnabled(false);
                else button_validerPropositionStage.setEnabled(true);
            }
            button_modifierPropositionStage.setEnabled(boutons);
            button_supprimerPropositionStage.setEnabled(boutons);
        });
        button_modifierPropositionStage.setEnabled(false);
        button_supprimerPropositionStage.setEnabled(false);
        button_validerPropositionStage.setEnabled(false);
        load_cb_propositionStage();
    }

    public void load_tab_defense()
    {
        tab_defense.removeAllItems();
        DAO_Defense defense_DB = new DAO_Defense();
        ArrayList<Defense> defenseList = defense_DB.fetchAll(0, null);
        for (Defense defense : defenseList)
        {
            Stage stage = defense.getStage();
            TFE tfe = defense.getTFE();
            String etudiant = "";
            String sujet = "";
            String type = "";
            if (tfe != null)
            {
                sujet = tfe.getTitre();
                etudiant = tfe.getOwner().toString();
                type = "TFE";
            } else if (stage != null)
            {
                sujet = stage.getProposition().getSubject();
                etudiant = stage.getOwner().toString();
                type = "Stage";
            }
            tab_defense.addItem(new Object[]{type, etudiant, sujet, defense.getLocal(), defense.getDate(), defense.getPresident().toString()}, defense.getId());
        }
        tab_defense.addValueChangeListener(event -> {
            form_defense_editer.setVisible(false);
            form_defense_evaluer.setVisible(false);
            elementSelected = tab_defense.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifierDefense.setEnabled(boutons);
            button_supprimerDefense.setEnabled(boutons);
            if (boutons && currentUser.getRole().isAllowed("professor_manage_evaluations"))
                button_evaluerDefense.setEnabled(true);
            else button_evaluerDefense.setEnabled(false);
        });
        button_modifierDefense.setEnabled(false);
        button_supprimerDefense.setEnabled(false);
        button_evaluerDefense.setEnabled(false);
    }

    public void load_tab_evaluationTFE(TFE tfe)
    {
        tab_evaluationTFE.removeAllItems();
        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
        ArrayList<Evaluation> evaluationList = evaluation_DB.fetchAll(tfe);
        for (Evaluation evaluation : evaluationList)
        {
            String critere = evaluation.getCritere().getNom();
            Double note = evaluation.getNote();
            String commentaire = evaluation.getCommentaire();
            String auteur = evaluation.getOwner().toString();
            Date date = evaluation.getDate();
            tab_evaluationTFE.addItem(new Object[]{critere, note, commentaire, auteur, date}, evaluation.getId());
        }
        tab_evaluationTFE.addValueChangeListener(event -> {
            form_TFE_evaluer.setVisible(false);
            elementSelected = tab_evaluationTFE.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifier_evaluationTFE.setEnabled(boutons);
            button_supprimer_evaluationTFE.setEnabled(boutons);
        });
        button_modifier_evaluationTFE.setEnabled(false);
        button_supprimer_evaluationTFE.setEnabled(false);
    }

    public void load_tab_evaluationStage(Stage stage)
    {
        tab_evaluationStage.removeAllItems();
        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
        ArrayList<Evaluation> evaluationList = evaluation_DB.fetchAll(stage);
        for (Evaluation evaluation : evaluationList)
        {
            String critere = evaluation.getCritere().getNom();
            Double note = evaluation.getNote();
            String commentaire = evaluation.getCommentaire();
            String auteur = evaluation.getOwner().toString();
            Date date = evaluation.getDate();
            tab_evaluationStage.addItem(new Object[]{critere, note, commentaire, auteur, date}, evaluation.getId());
        }
        tab_evaluationStage.addValueChangeListener(event -> {
            form_stage_evaluer.setVisible(false);
            elementSelected = tab_evaluationStage.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifier_evaluationStage.setEnabled(boutons);
            button_supprimer_evaluationStage.setEnabled(boutons);
        });
        button_modifier_evaluationStage.setEnabled(false);
        button_supprimer_evaluationStage.setEnabled(false);
    }

    public void load_tab_evaluationDefense(Defense defense)
    {
        tab_evaluationDefense.removeAllItems();
        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
        ArrayList<Evaluation> evaluationList = evaluation_DB.fetchAll(defense);
        for (Evaluation evaluation : evaluationList)
        {
            String critere = evaluation.getCritere().getNom();
            Double note = evaluation.getNote();
            String commentaire = evaluation.getCommentaire();
            String auteur = evaluation.getOwner().toString();
            Date date = evaluation.getDate();
            tab_evaluationDefense.addItem(new Object[]{critere, note, commentaire, auteur, date}, evaluation.getId());
        }
        tab_evaluationDefense.addValueChangeListener(event -> {
            form_defense_evaluer.setVisible(false);
            elementSelected = tab_evaluationDefense.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifier_evaluationDefense.setEnabled(boutons);
            button_supprimer_evaluationDefense.setEnabled(boutons);
        });
        button_modifier_evaluationDefense.setEnabled(false);
        button_supprimer_evaluationDefense.setEnabled(false);
    }

    public void load_tab_tfe2()
    {
        tab_tfe2.removeAllItems();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        for (TFE tfe : tfeList)
            tab_tfe2.addItem(new Object[]{tfe.getTitre(), tfe.getOwner().toString(), tfe.getPromoteur().toString(), (Integer) tfe.getAnneeDebut(), (Integer) tfe.getAnneeFin(),}, tfe.getId());
        load_cb_TFE2();
    }

    public void load_tab_stage2()
    {
        tab_stage2.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        for (Stage stage : stageList)
            tab_stage2.addItem(new Object[]{stage.getProposition().getSubject(), stage.getOwner().toString(), stage.getSuperviseur().toString(), DateFormat.getDateInstance().format(stage.getDateDebut()), DateFormat.getDateInstance().format(stage.getDateFin()), stage.getProposition().getPlace().getNom()}, stage.getId());
        load_cb_stage2();
    }

    public void load_tab_propositionStage2()
    {
        tab_propositionStage2.removeAllItems();
        DAO_PropositionStage propositionStage_DB = new DAO_PropositionStage();
        ArrayList<PropositionStage> propositionStageList = propositionStage_DB.fetchAll(currentUser, "professeur");
        for (PropositionStage propositionStage : propositionStageList)
        {
            tab_propositionStage2.addItem(new Object[]{propositionStage.getSubject(), propositionStage.getOwner().toString(), propositionStage.getPlace().getAdresse(), propositionStage.getPlace().getNom()}, propositionStage.getId());
        }
        tab_propositionStage2.addValueChangeListener(event -> {
            form_propositionStage_etudiant_editer.setVisible(false);
            elementSelected = tab_propositionStage2.getValue();
            boolean boutons = false;
            if (elementSelected == null) button_dupliquerPropositionStage3.setEnabled(false);
            else
            {
                DAO_PropositionStage propositionStage_DB1 = new DAO_PropositionStage();
                PropositionStage propositionStage = propositionStage_DB1.find((int) elementSelected);

                if (propositionStage.getOwner().getRole().getNom().equals("professeur"))
                    button_dupliquerPropositionStage3.setEnabled(true);
                else
                {
                    boutons = true;
                    button_dupliquerPropositionStage3.setEnabled(false);
                }
            }
            button_modifierPropositionStage3.setEnabled(boutons);
            button_supprimerPropositionStage3.setEnabled(boutons);
        });
        button_modifierPropositionStage3.setEnabled(false);
        button_supprimerPropositionStage3.setEnabled(false);
        button_dupliquerPropositionStage3.setEnabled(false);
        load_cb_propositionStage2();
    }

    public void load_tab_echeance()
    {
        tab_echeance.removeAllItems();
        DAO_Echeance echeance_DB = new DAO_Echeance();
        ArrayList<Echeance> echeanceList = echeance_DB.fetchAll(currentUser);
        for (Echeance echeance : echeanceList)
        {
            String type;
            if (echeance.getTFE() != null) type = "TFE";
            else if (echeance.getStage() != null) type = "Stage";
            else type = "Utilisateur";
            tab_echeance.addItem(new Object[]{type, echeance.getOwner().toString(), echeance.getDateCreation(), echeance.getDateEcheance(), echeance.getDescription()}, echeance.getId());
        }
    }

    public void load_tab_echeance2()
    {
        tab_echeance2.removeAllItems();
        DAO_Echeance echeance_DB = new DAO_Echeance();
        ArrayList<Echeance> echeanceList = echeance_DB.fetchAll();
        for (Echeance echeance : echeanceList)
        {
            String type;
            if (echeance.getTFE() != null) type = "TFE";
            else if (echeance.getStage() != null) type = "Stage";
            else type = "Utilisateur";
            tab_echeance2.addItem(new Object[]{type, echeance.getOwner().toString(), echeance.getDateCreation(), echeance.getDateEcheance(), echeance.getDescription()}, echeance.getId());
        }
        tab_echeance2.addValueChangeListener(event -> {
            form_echeance_editer.setVisible(false);
            form_echeance_editer2.setVisible(false);
            elementSelected = tab_echeance2.getValue();
            DAO_Echeance echeance_DB1 = new DAO_Echeance();
            boolean boutons = false;
            if (elementSelected == null) button_modifierEcheance.setEnabled(false);
            else
            {
                boutons = true;
                Echeance ech = echeance_DB1.find(Integer.parseInt(elementSelected.toString()));
                if (ech.getTFE() == null && ech.getStage() == null) button_modifierEcheance.setEnabled(true);
                else button_modifierEcheance.setEnabled(false);
            }
            button_supprimerEcheance.setEnabled(boutons);
        });
        load_cb_echeance();
    }

    public void load_tab_echeance3(TFE tfe)
    {
        tab_echeance3.removeAllItems();
        DAO_Echeance echeance_DB = new DAO_Echeance();
        ArrayList<Echeance> echeanceList = echeance_DB.fetchAll(tfe);
        for (Echeance echeance : echeanceList)
            tab_echeance3.addItem(new Object[]{echeance.getOwner().toString(), echeance.getDateCreation(), echeance.getDateEcheance(), echeance.getDescription()}, echeance.getId());
        tab_echeance3.addValueChangeListener(event -> {
            form_TFE_echeance.setVisible(false);
            elementSelected = tab_echeance3.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_TFE_modifierEcheance.setEnabled(boutons);
            button_TFE_supprimerEcheance.setEnabled(boutons);
        });
    }

    public void load_tab_echeance4(Stage stage)
    {
        tab_echeance4.removeAllItems();
        DAO_Echeance echeance_DB = new DAO_Echeance();
        ArrayList<Echeance> echeanceList = echeance_DB.fetchAll(stage);
        for (Echeance echeance : echeanceList)
            tab_echeance4.addItem(new Object[]{echeance.getOwner().toString(), echeance.getDateCreation(), echeance.getDateEcheance(), echeance.getDescription()}, echeance.getId());
        tab_echeance4.addValueChangeListener(event -> {
            form_stage_echeance.setVisible(false);
            elementSelected = tab_echeance4.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_stage_modifierEcheance.setEnabled(boutons);
            button_stage_supprimerEcheance.setEnabled(boutons);
        });
    }

    public void load_tab_defense2()
    {
        tab_defense2.removeAllItems();
        DAO_Defense defense_DB = new DAO_Defense();
        ArrayList<Defense> defenseList = defense_DB.fetchAll(2, currentUser);
        for (Defense defense : defenseList)
        {
            Stage stage = defense.getStage();
            TFE tfe = defense.getTFE();
            String etudiant = "";
            String sujet = "";
            String type = "";
            if (tfe != null)
            {
                sujet = tfe.getTitre();
                etudiant = tfe.getOwner().toString();
                type = "TFE";
            } else if (stage != null)
            {
                sujet = stage.getProposition().getSubject();
                etudiant = stage.getOwner().toString();
                type = "Stage";
            }
            String promoteur = "";
            if (defense.getStage() != null) promoteur = defense.getStage().getSuperviseur().toString();
            else if (defense.getTFE() != null) promoteur = defense.getTFE().getPromoteur().toString();

            tab_defense2.addItem(new Object[]{type, etudiant, sujet, defense.getLocal(), defense.getDate(), promoteur, defense.getPresident().toString()}, defense.getId());
        }
    }

    public void load_tab_stage3()
    {
        tab_stage3.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(currentUser);
        for (Stage stage : stageList)
            tab_stage3.addItem(new Object[]{stage.getProposition().getSubject(), stage.getOwner().toString(), stage.getSuperviseur().toString(), DateFormat.getDateInstance().format(stage.getDateDebut()), DateFormat.getDateInstance().format(stage.getDateFin()), stage.getProposition().getPlace().getNom(), stage.getPoints()}, stage.getId());
        tab_stage3.addValueChangeListener(event -> {
            form_stage_maitreDeStage_evaluer.setVisible(false);
            elementSelected = tab_stage3.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_evaluerStage_maitreDeStage.setEnabled(boutons);
        });
        button_evaluerStage_maitreDeStage.setEnabled(false);
        load_cb_maitreStage();
    }

    public void load_tab_defense3()
    {
        tab_defense3.removeAllItems();
        DAO_Defense defense_DB = new DAO_Defense();
        ArrayList<Defense> defenseList = defense_DB.fetchAll(1, currentUser);
        for (Defense defense : defenseList)
        {
            Stage stage = defense.getStage();
            TFE tfe = defense.getTFE();
            String etudiant = "";
            String sujet = "";
            String type = "";
            if (tfe != null)
            {
                sujet = tfe.getTitre();
                etudiant = tfe.getOwner().getNom() + " " + tfe.getOwner().toString();
                type = "TFE";
            } else if (stage != null)
            {
                sujet = stage.getProposition().getSubject();
                etudiant = stage.getOwner().getNom() + " " + stage.getOwner().toString();
                type = "Stage";
            }
            tab_defense3.addItem(new Object[]{type, etudiant, sujet, defense.getLocal(), defense.getDate(), defense.getPresident().toString()}, defense.getId());
        }
        tab_defense3.addValueChangeListener(event -> {
            form_defense_evaluer_presidentDeJury.setVisible(false);
            elementSelected = tab_defense3.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_evaluerDefense_presidentDeJury.setEnabled(boutons);
        });
        load_cb_presidentJury();
    }

    public void load_tab_evaluations_maitreStage(Stage stage)
    {
        tab_evaluations_maitreStage.removeAllItems();
        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
        ArrayList<Evaluation> evaluationList = evaluation_DB.fetchAll(currentUser, stage);
        for (Evaluation evaluation : evaluationList)
        {
            String critere = evaluation.getCritere().getNom();
            Double note = evaluation.getNote();
            String commentaire = evaluation.getCommentaire();
            String auteur = evaluation.getOwner().toString();
            Date date = evaluation.getDate();
            String etudiant = evaluation.getStage().getOwner().toString();
            tab_evaluations_maitreStage.addItem(new Object[]{etudiant, critere, note, commentaire, auteur, date}, evaluation.getId());
        }

        tab_evaluations_maitreStage.addValueChangeListener(event -> {
            form_stage_evaluer.setVisible(false);
            elementSelected = tab_evaluations_maitreStage.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifierEvaluation_maitreDeStage.setEnabled(boutons);
            button_supprimerEvaluation_maitreDeStage.setEnabled(boutons);
        });
        button_modifierEvaluation_maitreDeStage.setEnabled(false);
        button_supprimerEvaluation_maitreDeStage.setEnabled(false);
    }

    public void load_tab_evaluations_presidentJury(Defense defense)
    {
        tab_evaluations_presidentJury.removeAllItems();
        DAO_Evaluation evaluation_DB = new DAO_Evaluation();
        ArrayList<Evaluation> evaluationList = evaluation_DB.fetchAll(currentUser, defense);
        for (Evaluation evaluation : evaluationList)
        {
            String critere = evaluation.getCritere().getNom();
            Double note = evaluation.getNote();
            String commentaire = evaluation.getCommentaire();
            String auteur = evaluation.getOwner().toString();
            Date date = evaluation.getDate();
            String etudiant = "";
            Defense defenseEval = evaluation.getDefense();
            Stage stage = defenseEval.getStage();
            TFE tfe = defenseEval.getTFE();
            if (stage != null) etudiant = stage.getOwner().toString();
            else if (tfe != null) etudiant = tfe.getOwner().toString();
            tab_evaluations_presidentJury.addItem(new Object[]{etudiant, critere, note, commentaire, auteur, date}, evaluation.getId());
        }
        tab_evaluations_presidentJury.addValueChangeListener(event -> {
            form_stage_evaluer.setVisible(false);
            elementSelected = tab_evaluations_presidentJury.getValue();
            boolean boutons = false;
            if (elementSelected != null) boutons = true;
            button_modifierEvaluation_presidentDeJury.setEnabled(boutons);
            button_supprimerEvaluation_presidentDeJury.setEnabled(boutons);
        });
        button_modifierEvaluation_presidentDeJury.setEnabled(false);
        button_supprimerEvaluation_presidentDeJury.setEnabled(false);
    }

    public void load_cb_administration_etudiant()
    {
        cb_etudiant_permission.removeAllItems();
        cb_etudiant_permission.addItem("TFE");
        cb_etudiant_permission.addItem("TFE et Stage");
        cb_etudiant_permission.setValue("TFE");
    }

    public void load_cb_presidentJury()
    {
        defense_evaluer_presidentDeJury_critere.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList;
        critereEvaluationList = critereEvaluation_DB.fetchAll("defense");
        critereEvaluationList.forEach(defense_evaluer_presidentDeJury_critere::addItem);
    }

    public void load_cb_critereEvaluation()
    {
        cb_critereEvaluation_type.addItem("stage");
        cb_critereEvaluation_type.addItem("tfe");
        cb_critereEvaluation_type.addItem("defense");
        cb_critereEvaluation_type.setValue("stage");
    }

    public void load_cb_defense()
    {
        defense_evaluer_critere.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList;
        critereEvaluationList = critereEvaluation_DB.fetchAll("defense");
        critereEvaluationList.forEach(defense_evaluer_critere::addItem);
    }

    public void load_cb_TFE()
    {
        tc_tfe_ajouter_technologie.removeAllItems();
        tc_tfe_modifier_technologie.removeAllItems();
        cb_tfe_technologie.removeAllItems();
        DAO_Technologie technologie_DB = new DAO_Technologie();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        ArrayList<Technologie> technologieList = technologie_DB.fetchAll();
        for (Technologie technologie : technologieList)
        {
            tc_tfe_ajouter_technologie.addItem(technologie);
            tc_tfe_modifier_technologie.addItem(technologie);
        }
        ArrayList<Technologie> technologiesUtilisees = new ArrayList<>();
        for (TFE tfe : tfeList)
        {
            ArrayList<Technologie> technologiesStage = tfe.getTechnologies();
            technologiesUtilisees.remove(technologiesStage);
            technologiesUtilisees.addAll(technologiesStage);
        }
        technologiesUtilisees.forEach(cb_tfe_technologie::addItem);
        cb_tfe_technologie.addValueChangeListener(event -> load_tab_tfe_filtre());

        cb_tfe_anneeAcademique.removeAllItems();
        tfe_generer_anneesAcademiques(tfeList).forEach(cb_tfe_anneeAcademique::addItem);

        cb_tfe_anneeAcademique.addValueChangeListener(event -> load_tab_tfe_filtre());

        tf_tfe_etudiant.setImmediate(true);
        tf_tfe_etudiant.addTextChangeListener(event -> {
            tf_tfe_etudiant.setValue(event.getText());
            load_tab_tfe_filtre();
        });
        cb_tfe_ajouter_promoteur.removeAllItems();
        cb_tfe_modifier_promoteur.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("professeur");
        for (Utilisateur user : userList)
        {
            cb_tfe_ajouter_promoteur.addItem(user);
            cb_tfe_modifier_promoteur.addItem(user);
        }
        cb_tfe_ajouter_etudiant.removeAllItems();
        ArrayList<Utilisateur> userEtuList = user_DB.fetchAll("etudiant_tfe");
        userEtuList.addAll(user_DB.fetchAll("etudiant_tfe_stage"));
        userEtuList.forEach(cb_tfe_ajouter_etudiant::addItem);
        tfe_evaluer_critere.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList = critereEvaluation_DB.fetchAll("tfe");
        critereEvaluationList.forEach(tfe_evaluer_critere::addItem);
        tfe_defense_presidentJury.removeAllItems();
        ArrayList<Utilisateur> userPresJuryList = user_DB.fetchAll("president_jury");
        userPresJuryList.forEach(tfe_defense_presidentJury::addItem);
    }

    public void load_cb_TFE2()
    {
        cb_tfe_technologie2.removeAllItems();
        DAO_TFE tfe_DB = new DAO_TFE();
        ArrayList<TFE> tfeList = tfe_DB.fetchAll();
        ArrayList<Technologie> technologiesUtilisees = new ArrayList<>();
        for (TFE tfe : tfeList)
        {
            ArrayList<Technologie> technologiesStage = tfe.getTechnologies();
            technologiesUtilisees.remove(technologiesStage);
            technologiesUtilisees.addAll(technologiesStage);
        }
        technologiesUtilisees.forEach(cb_tfe_technologie2::addItem);
        cb_tfe_technologie2.addValueChangeListener(event -> load_tab_tfe2_filtre());
        cb_tfe_anneeAcademique2.removeAllItems();
        tfe_generer_anneesAcademiques(tfeList).forEach(cb_tfe_anneeAcademique2::addItem);
        cb_tfe_anneeAcademique2.addValueChangeListener(event -> load_tab_tfe2_filtre());
        tf_tfe_etudiant2.setImmediate(true);
        tf_tfe_etudiant2.addTextChangeListener(event -> {
            tf_tfe_etudiant2.setValue(event.getText());
            load_tab_tfe2_filtre();
        });
    }

    private ArrayList<String> tfe_generer_anneesAcademiques(ArrayList<TFE> listTfe)
    {
        ArrayList<String> retour = new ArrayList<>();

        for (TFE tfe : listTfe)
        {
            int annee_debut = tfe.getAnneeDebut();
            int annee_fin = tfe.getAnneeFin();
            int action = 0;

            if (annee_debut == annee_fin) action = -1;

            String proposition = (annee_debut + action) + "-" + (annee_fin);
            if (!retour.contains(proposition)) retour.add(proposition);
        }

        return retour;
    }

    private ArrayList<String> stages_generer_anneesAcademiques(ArrayList<Stage> stages)
    {
        ArrayList<String> retour = new ArrayList<>();
        SimpleDateFormat annee_format = new SimpleDateFormat("yyyy");
        SimpleDateFormat mois_format = new SimpleDateFormat("M");

        for (Stage stage : stages)
        {
            int annee_debut = Integer.valueOf(annee_format.format(stage.getDateDebut()));
            int annee_fin = Integer.valueOf(annee_format.format(stage.getDateFin()));
            int action = 0;
            int action2 = 0;

            if (annee_debut == annee_fin)
            {
                int mois_fin = Integer.valueOf(mois_format.format(stage.getDateFin()));
                if (mois_fin > 6) action2 = 1;
                else action = -1;
            }

            String proposition = (annee_debut + action) + "-" + (annee_fin + action2);
            if (!retour.contains(proposition)) retour.add(proposition);
        }

        retour.sort(null);

        return retour;
    }

    public void load_cb_stage()
    {
        tc_stage_editer_technologies.removeAllItems();
        cb_stage_technologie.removeAllItems();
        DAO_Technologie technologie_DB = new DAO_Technologie();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        ArrayList<Technologie> technologieList = technologie_DB.fetchAll();
        technologieList.forEach(tc_stage_editer_technologies::addItem);
        ArrayList<Technologie> technologiesUtilisees = new ArrayList<>();
        for (Stage stage : stageList)
        {
            ArrayList<Technologie> technologiesStage = stage.getTechnologies();
            technologiesUtilisees.remove(technologiesStage);
            technologiesUtilisees.addAll(technologiesStage);
        }
        technologiesUtilisees.forEach(cb_stage_technologie::addItem);
        cb_stage_technologie.addValueChangeListener(event -> load_tab_stage_filtre());
        cb_stage_entreprise.removeAllItems();
        cb_stage_editer_entreprise.setReadOnly(false);
        cb_stage_editer_entreprise.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> lieuStageList = lieuStage_DB.fetchAll();
        lieuStageList.forEach(cb_stage_editer_entreprise::addItem);
        ArrayList<LieuStage> lieuxStagesUtilises = new ArrayList<>();
        for (Stage stage : stageList)
        {
            LieuStage lieuStageStage = stage.getProposition().getPlace();
            lieuxStagesUtilises.remove(lieuStageStage);
            lieuxStagesUtilises.add(lieuStageStage);
        }
        lieuxStagesUtilises.forEach(cb_stage_entreprise::addItem);
        cb_stage_entreprise.addValueChangeListener(event -> load_tab_stage_filtre());

        tf_stage_adresse.setImmediate(true);
        tf_stage_adresse.addTextChangeListener(event -> {
            tf_stage_adresse.setValue(event.getText());
            load_tab_stage_filtre();
        });

        cb_stage_anneeAcademique.removeAllItems();
        stages_generer_anneesAcademiques(stageList).forEach(cb_stage_anneeAcademique::addItem);
        cb_stage_anneeAcademique.addValueChangeListener(event -> load_tab_stage_filtre());

        cb_stage_editer_promoteur.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> promoteurList = user_DB.fetchAll("professeur");
        promoteurList.forEach(cb_stage_editer_promoteur::addItem);

        cb_stage_editer_maitreDeStage.removeAllItems();
        ArrayList<Utilisateur> maitreDeStageList = user_DB.fetchAll("maitre_stage");
        maitreDeStageList.forEach(cb_stage_editer_maitreDeStage::addItem);
        cb_stage_editer_etudiant.setReadOnly(false);
        cb_stage_editer_etudiant.removeAllItems();

        ArrayList<Utilisateur> etudiantList = user_DB.fetchAll("etudiant_tfe_stage");
        etudiantList.forEach(cb_stage_editer_etudiant::addItem);

        stage_evaluer_critere.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList = critereEvaluation_DB.fetchAll("stage");
        critereEvaluationList.forEach(stage_evaluer_critere::addItem);

        stage_defense_presidentJury.removeAllItems();
        ArrayList<Utilisateur> presidentJuryList = user_DB.fetchAll("president_jury");
        presidentJuryList.forEach(stage_defense_presidentJury::addItem);
    }

    public void load_cb_stage2()
    {
        cb_stage_technologie3.removeAllItems();
        DAO_Stage stage_DB = new DAO_Stage();
        ArrayList<Stage> stageList = stage_DB.fetchAll(null);
        ArrayList<Technologie> technologiesUtilisees = new ArrayList<>();
        for (Stage stage : stageList)
        {
            ArrayList<Technologie> technologiesStage = stage.getTechnologies();
            technologiesUtilisees.remove(technologiesStage);
            technologiesUtilisees.addAll(technologiesStage);
        }
        technologiesUtilisees.forEach(cb_stage_technologie3::addItem);
        cb_stage_technologie3.addValueChangeListener(event -> load_tab_stage2_filtre());
        cb_stage_entreprise3.removeAllItems();
        ArrayList<LieuStage> lieuxStagesUtilises = new ArrayList<>();
        for (Stage stage : stageList)
        {
            LieuStage lieuStageStage = stage.getProposition().getPlace();
            lieuxStagesUtilises.remove(lieuStageStage);
            lieuxStagesUtilises.add(lieuStageStage);
        }
        lieuxStagesUtilises.forEach(cb_stage_entreprise3::addItem);
        cb_stage_entreprise3.addValueChangeListener(event -> load_tab_stage2_filtre());
        tf_stage_adresse3.addTextChangeListener(event -> {
            tf_stage_adresse3.setValue(event.getText());
            load_tab_stage2_filtre();
        });
        cb_stage_anneeAcademique3.removeAllItems();
        stages_generer_anneesAcademiques(stageList).forEach(cb_stage_anneeAcademique3::addItem);
        cb_stage_anneeAcademique3.addValueChangeListener(event -> load_tab_stage2_filtre());
    }

    public void load_cb_propositionStage()
    {
        cb_PropositionStage_editer_entreprise.setReadOnly(false);
        cb_PropositionStage_editer_entreprise.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> entrepriseList = lieuStage_DB.fetchAll();
        entrepriseList.forEach(cb_PropositionStage_editer_entreprise::addItem);
        cb_PropositionStage_valider_maitreDeStage.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userMaitreStageList = user_DB.fetchAll("maitre_stage");
        userMaitreStageList.forEach(cb_PropositionStage_valider_maitreDeStage::addItem);
        cb_PropositionStage_valider_promoteur.removeAllItems();
        ArrayList<Utilisateur> userPromoteurList = user_DB.fetchAll("professeur");
        userPromoteurList.forEach(cb_PropositionStage_valider_promoteur::addItem);
        tc_PropositionStage_valider_technologies.removeAllItems();
        DAO_Technologie technologie_DB = new DAO_Technologie();
        ArrayList<Technologie> technologieList = technologie_DB.fetchAll();
        technologieList.forEach(tc_PropositionStage_valider_technologies::addItem);
    }

    public void load_cb_propositionStage2()
    {
        cb_PropositionStage_etudiant_editer_entreprise.setReadOnly(false);
        cb_PropositionStage_etudiant_editer_entreprise.removeAllItems();
        DAO_LieuStage lieuStage_DB = new DAO_LieuStage();
        ArrayList<LieuStage> entrepriseList = lieuStage_DB.fetchAll();
        entrepriseList.forEach(cb_PropositionStage_etudiant_editer_entreprise::addItem);
    }

    public void load_cb_maitreStage()
    {
        stage_evaluer_maitreDeStage_critere.removeAllItems();
        DAO_CritereEvaluation critereEvaluation_DB = new DAO_CritereEvaluation();
        ArrayList<CritereEvaluation> critereEvaluationList = critereEvaluation_DB.fetchAll("stage");
        critereEvaluationList.forEach(stage_evaluer_maitreDeStage_critere::addItem);
    }

    public void load_cb_echeance()
    {
        tc_echeance_ajouter_utilisateurs.removeAllItems();
        DAO_Utilisateur user_DB = new DAO_Utilisateur();
        ArrayList<Utilisateur> userList = user_DB.fetchAll("etudiant_tfe");
        userList.addAll(user_DB.fetchAll("etudiant_tfe_stage"));
        userList.forEach(tc_echeance_ajouter_utilisateurs::addItem);
    }

    public void unselectSelectedTab()
    {
        if (elementSelected != null) selectedTab.unselect(elementSelected);
        elementSelected = null;
        modification = false;
    }

    public void hideForms()
    {
        Layout forms[] = {form_defense_evaluer_presidentDeJury, form_stage_maitreDeStage_evaluer, form_propositionStage_etudiant_editer, form_TFE_ajouter, form_TFE_ajouter2, form_TFE_modifier, form_TFE_modifier2, form_TFE_evaluer, form_TFE_defense, form_TFE_echeance, form_stage_editer, form_stage_editer2, form_stage_defense, form_stage_echeance, form_stage_evaluer, form_stage_commenter, form_propositionStage_editer, form_propositionStage_valider, form_propositionStage_valider2, form_defense_editer, form_defense_evaluer, form_echeance_editer, form_echeance_editer2, form_prof, form_maitreDeStage, form_presidentDeJury, form_entreprise, form_critereEvaluation_editer, form_technologie};
        form_defense_evaluer_presidentDeJury.setVisible(false);
        form_stage_maitreDeStage_evaluer.setVisible(false);
        form_propositionStage_etudiant_editer.setVisible(false);
        for (Layout l : forms)
            l.setVisible(false);
    }

    public void removeAllClickShortcuts()
    {
        Button boutons[] = {button_etudiant_valider, button_professeur_valider, button_maitreDeStage_valider, button_presidentDeJury_valider, button_entreprise_valider, button_critereEvaluation_valider, button_technologie_valider, button_tfe_ajouter_valider, button_tfe_modifier_valider, button_tfe_evaluer_valider, button_tfe_defense_valider, button_tfe_echeance_valider, button_stage_editer_valider, button_stage_evaluer_valider, button_stage_defense_valider, button_stage_echeance_valider, button_stage_commenter_valider, button_propositionStage_valider_valider, button_defense_editer_valider, button_defense_evaluer_valider, button_echeance_editer_valider, button_entreprise_valider2, button_propositionStage_etudiant_editer_valider, button_stage_evaluer_maitreDeStage_valider, button_defense_evaluer_presidentDeJury_valider};
        for (Button b : boutons)
            b.removeClickShortcut();
    }

    public void enter(ViewChangeEvent event)
    {
        this.navigateur = event.getNavigator();
        currentSession = VaadinService.getCurrentRequest().getWrappedSession();
        currentUser = (Utilisateur) currentSession.getAttribute("user");
        if (currentUser == null) navigateur.navigateTo("");
        else
        {
            for (int i = 0; i < onglets.getComponentCount(); i++)
                onglets.getTab(i).setVisible(true);
            if (currentUser.getRole().isAllowed("admin_management"))
            {
                for (int i = 0; i < onglets_administration.getComponentCount(); i++)
                    onglets_administration.getTab(i).setEnabled(true);
                if (!currentUser.getRole().isAllowed("admin_manage_professors"))
                    onglets_administration.getTab(onglet_prof).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_students"))
                    onglets_administration.getTab(onglet_etudiant).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_tutors"))
                    onglets_administration.getTab(onglet_maitreStage).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_jury"))
                    onglets_administration.getTab(onglet_presidentJury).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_enterprises"))
                    onglets_administration.getTab(onglet_Entreprise).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_criteres"))
                    onglets_administration.getTab(onglet_CritereEvaluation).setEnabled(false);
                if (!currentUser.getRole().isAllowed("admin_manage_technologies"))
                    onglets_administration.getTab(onglet_technologies).setEnabled(false);

                onglets_administration.setSelectedTab(1);
                onglets_administration.setSelectedTab(0);
            } else onglets.getTab(onglet_administration).setVisible(false);
            if (currentUser.getRole().isAllowed("professor_management"))
            {
                for (int i = 0; i < onglets_prof.getComponentCount(); i++)
                    onglets_prof.getTab(i).setEnabled(true);
                if (!currentUser.getRole().isAllowed("professor_manage_tfe"))
                    onglets_prof.getTab(onglet_gererTFE).setEnabled(false);
                if (!currentUser.getRole().isAllowed("professor_manage_stages"))
                {
                    onglets_prof.getTab(onglet_gererStages).setEnabled(false);
                    onglets_prof.getTab(onglet_gererPropositionsStage).setEnabled(false);
                }
                if (!currentUser.getRole().isAllowed("professor_manage_defenses"))
                    onglets_prof.getTab(onglet_gererDefenses).setEnabled(false);
                if (!currentUser.getRole().isAllowed("professor_manage_planning"))
                    onglets_prof.getTab(onglet_gererEcheance).setEnabled(false);

                onglets_prof.setSelectedTab(0);
            } else onglets.getTab(onglet_professeurs).setVisible(false);
            if (currentUser.getRole().isAllowed("student_management"))
            {
                for (int i = 0; i < onglets_etudiant.getComponentCount(); i++)
                    onglets_etudiant.getTab(i).setEnabled(true);
                if (!currentUser.getRole().isAllowed("student_see_tfe"))
                    onglets_etudiant.getTab(onglet_etudiant_TFE).setEnabled(false);
                if (!currentUser.getRole().isAllowed("student_see_stages"))
                {
                    onglets_etudiant.getTab(onglet_etudiant_stages).setEnabled(false);
                    onglets_etudiant.getTab(onglet_etudiant_propositionStage).setEnabled(false);
                }
                if (!currentUser.getRole().isAllowed("student_see_tfe") && !currentUser.getRole().isAllowed("student_see_stages"))
                {
                    onglets_etudiant.getTab(onglet_etudiant_echeance).setEnabled(false);
                    onglets_etudiant.getTab(onglet_etudiant_defense).setEnabled(false);
                }

                onglets_etudiant.setSelectedTab(0);
            } else onglets.getTab(onglet_etudiants).setVisible(false);
            if (!currentUser.getRole().isAllowed("tutor_management"))
                onglets.getTab(onglet_maitreDeStage).setVisible(false);
            if (!currentUser.getRole().isAllowed("jury_management"))
                onglets.getTab(onglet_presidentDeJury).setVisible(false);
        }
        onglets.setSelectedTab(0);
    }

    // TODO
    void calculPointsTFE(TFE tfe)
    {
        double res = 0;
        int total = 0;

        DAO_Evaluation evaluation_DB = new DAO_Evaluation();

        for (Evaluation evaluation : evaluation_DB.fetchAll(tfe))
        {
            res += evaluation.getNote();
            total += evaluation.getCritere().getNote();
        }

        if (total != 0) res = (res / total) * 100;
        else res = 0;

        DAO_TFE tfe_DB = new DAO_TFE();

        tfe.update(res);
        tfe_DB.update(tfe);
    }

    void calculPointsStage(Stage stage)
    {
        double res = 0;
        int total = 0;

        DAO_Evaluation evaluation_DB = new DAO_Evaluation();

        for (Evaluation evaluation : evaluation_DB.fetchAll(stage))
        {
            res += evaluation.getNote();
            total += evaluation.getCritere().getNote();
        }

        if (total != 0) res = (res / total) * 100;
        else res = 0;

        DAO_Stage stage_DB = new DAO_Stage();

        stage.update(res);
        stage_DB.update(stage);
    }


    // Implement receiver that saves upload in a file
    class AnnexeUploader implements Receiver
    {
        public File file;

        public OutputStream receiveUpload(String filename, String mimeType)
        {
            // Create upload stream
            FileOutputStream fos; // Stream to write to
            try
            {
                // Open the file for writing.
                // Répertoire courant : System.getProperty("user.dir")
                file = new File("/tmp/" + System.currentTimeMillis() + "_" + filename);
                fos = new FileOutputStream(file);
                fileUploaded = file;
            }
            catch (final java.io.FileNotFoundException e)
            {
                Notification.show("Un problème est survenu lors de l'upload du fichier", Notification.TYPE_ERROR_MESSAGE);
                return null;
            }
            return fos; // Return the output stream to write to
        }
    }

}