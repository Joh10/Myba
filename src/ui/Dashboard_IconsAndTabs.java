package ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;

import java.util.Date;

@SuppressWarnings("serial")
public class Dashboard_IconsAndTabs extends Dashboard_design
{
    @SuppressWarnings("deprecation")
    public Dashboard_IconsAndTabs()
    {
        //Upload
        upload_echeance_ajouter_annexe.addListener((Upload.SucceededListener) event ->
        {
            // This method gets called when the upload finished successfully
            upload_echeance_ajouter_annexe.setVisible(false);
            Notification.show("Le fichier a bien été uploadé", Notification.TYPE_TRAY_NOTIFICATION);
        });

        upload_echeance_ajouter_annexe.addListener((Upload.FailedListener) event ->
        {
            // This method gets called when the upload failed
            Notification.show("Un problème est survenu lors de l'upload du fichier", Notification.TYPE_ERROR_MESSAGE);
        });

        upload_etudiant_propositionStage_editer_annexe.addListener((Upload.SucceededListener) event ->
        {
            // This method gets called when the upload finished successfully
            upload_etudiant_propositionStage_editer_annexe.setVisible(false);
            Notification.show("Le fichier a bien été uploadé", Notification.TYPE_TRAY_NOTIFICATION);
        });

        upload_etudiant_propositionStage_editer_annexe.addListener((Upload.FailedListener) event ->
        {
            // This method gets called when the upload failed
            Notification.show("Un problème est survenu lors de l'upload du fichier", Notification.TYPE_ERROR_MESSAGE);
        });

        upload_professeur_propositionStage_editer_annexe.addListener((Upload.SucceededListener) event ->
        {
            // This method gets called when the upload finished successfully
            upload_echeance_ajouter_annexe.setVisible(false);
            Notification.show("Le fichier a bien été uploadé", Notification.TYPE_TRAY_NOTIFICATION);
        });

        upload_professeur_propositionStage_editer_annexe.addListener((Upload.FailedListener) event ->
        {
            // This method gets called when the upload failed
            Notification.show("Un problème est survenu lors de l'upload du fichier", Notification.TYPE_ERROR_MESSAGE);
        });

        // SLIDERS
        sl_etudiant_annee.setMin(1);
        sl_etudiant_annee.setMax(5);
        sl_etudiant_annee.setResolution(0);
        //DATE
        tfe_defense_date.setResolution(Resolution.MINUTE);
        stage_defense_date.setResolution(Resolution.MINUTE);
        date_echeance_ajouter_date.setResolution(Resolution.MINUTE);
        tfe_echeance_date.setResolution(Resolution.MINUTE);
        stage_echeance_date.setResolution(Resolution.MINUTE);
        date_defense_date.setResolution(Resolution.MINUTE);
        button_monCompte.setIcon(FontAwesome.USER);
        ajouter.setIcon(FontAwesome.PLUS);
        ajouter.setDescription("Ajouter");
        modifier.setIcon(FontAwesome.EDIT);
        modifier.setDescription("Modifier");
        supprimer.setIcon(FontAwesome.TIMES);
        supprimer.setDescription("Supprimer");
        ajouter2.setIcon(FontAwesome.PLUS);
        ajouter2.setDescription("Ajouter");
        modifier2.setIcon(FontAwesome.EDIT);
        modifier2.setDescription("Modifier");
        supprimer2.setIcon(FontAwesome.TIMES);
        supprimer2.setDescription("Supprimer");
        ajouter3.setIcon(FontAwesome.PLUS);
        ajouter3.setDescription("Ajouter");
        modifier3.setIcon(FontAwesome.EDIT);
        modifier3.setDescription("Modifier");
        supprimer3.setIcon(FontAwesome.TIMES);
        supprimer3.setDescription("Supprimer");
        ajouter4.setIcon(FontAwesome.PLUS);
        ajouter4.setDescription("Ajouter");
        modifier4.setIcon(FontAwesome.EDIT);
        modifier4.setDescription("Modifier");
        supprimer4.setIcon(FontAwesome.TIMES);
        supprimer4.setDescription("Supprimer");
        ajouter5.setIcon(FontAwesome.PLUS);
        ajouter5.setDescription("Ajouter");
        modifier5.setIcon(FontAwesome.EDIT);
        modifier5.setDescription("Modifier");
        supprimer5.setIcon(FontAwesome.TIMES);
        supprimer5.setDescription("Supprimer");
        ajouter6.setIcon(FontAwesome.PLUS);
        ajouter6.setDescription("Ajouter");
        modifier6.setIcon(FontAwesome.EDIT);
        modifier6.setDescription("Modifier");
        supprimer6.setIcon(FontAwesome.TIMES);
        supprimer6.setDescription("Supprimer");
        ajouter7.setIcon(FontAwesome.PLUS);
        ajouter7.setDescription("Ajouter");
        modifier7.setIcon(FontAwesome.EDIT);
        modifier7.setDescription("Modifier");
        supprimer7.setIcon(FontAwesome.TIMES);
        supprimer7.setDescription("Supprimer");
        button_ajouterTFE.setIcon(FontAwesome.PLUS);
        button_ajouterTFE.setDescription("Ajouter");
        button_modifierTFE.setIcon(FontAwesome.EDIT);
        button_modifierTFE.setDescription("Modifier");
        button_supprimerTFE.setIcon(FontAwesome.TIMES);
        button_supprimerTFE.setDescription("Supprimer");
        button_modifierStage.setIcon(FontAwesome.EDIT);
        button_modifierStage.setDescription("Modifier");
        button_supprimerStage.setIcon(FontAwesome.TIMES);
        button_supprimerStage.setDescription("Supprimer");
        button_ajouterPropositionStage.setIcon(FontAwesome.PLUS);
        button_ajouterPropositionStage.setDescription("Ajouter");
        button_modifierPropositionStage.setIcon(FontAwesome.EDIT);
        button_modifierPropositionStage.setDescription("Modifier");
        button_supprimerPropositionStage.setIcon(FontAwesome.TIMES);
        button_supprimerPropositionStage.setDescription("Supprimer");
        button_modifierDefense.setIcon(FontAwesome.EDIT);
        button_modifierDefense.setDescription("Modifier");
        button_supprimerDefense.setIcon(FontAwesome.TIMES);
        button_supprimerDefense.setDescription("Supprimer");
        button_ajouterEcheance.setIcon(FontAwesome.PLUS);
        button_ajouterEcheance.setDescription("Ajouter");
        button_modifierEcheance.setIcon(FontAwesome.EDIT);
        button_modifierEcheance.setDescription("Modifier");
        button_supprimerEcheance.setIcon(FontAwesome.TIMES);
        button_supprimerEcheance.setDescription("Supprimer");
        button_TFE_ajouterEcheance.setIcon(FontAwesome.PLUS);
        button_TFE_ajouterEcheance.setDescription("Ajouter");
        button_TFE_modifierEcheance.setIcon(FontAwesome.EDIT);
        button_TFE_modifierEcheance.setDescription("Modifier");
        button_TFE_supprimerEcheance.setIcon(FontAwesome.TIMES);
        button_TFE_supprimerEcheance.setDescription("Supprimer");
        button_stage_ajouterEcheance.setIcon(FontAwesome.PLUS);
        button_stage_ajouterEcheance.setDescription("Ajouter");
        button_stage_modifierEcheance.setIcon(FontAwesome.EDIT);
        button_stage_modifierEcheance.setDescription("Modifier");
        button_stage_supprimerEcheance.setIcon(FontAwesome.TIMES);
        button_stage_supprimerEcheance.setDescription("Supprimer");
        buton_ajouter_evaluationTFE.setIcon(FontAwesome.PLUS);
        buton_ajouter_evaluationTFE.setDescription("Ajouter");
        button_modifier_evaluationTFE.setIcon(FontAwesome.EDIT);
        button_modifier_evaluationTFE.setDescription("Modifier");
        button_supprimer_evaluationTFE.setIcon(FontAwesome.TIMES);
        button_supprimer_evaluationTFE.setDescription("Supprimer");
        buton_ajouter_evaluationStage.setIcon(FontAwesome.PLUS);
        buton_ajouter_evaluationStage.setDescription("Ajouter");
        button_modifier_evaluationStage.setIcon(FontAwesome.EDIT);
        button_modifier_evaluationStage.setDescription("Modifier");
        button_supprimer_evaluationStage.setIcon(FontAwesome.TIMES);
        button_supprimer_evaluationStage.setDescription("Supprimer");
        button_ajouter_evaluationDefense.setIcon(FontAwesome.PLUS);
        button_ajouter_evaluationDefense.setDescription("Ajouter");
        button_modifier_evaluationDefense.setIcon(FontAwesome.EDIT);
        button_modifier_evaluationDefense.setDescription("Modifier");
        button_supprimer_evaluationDefense.setIcon(FontAwesome.TIMES);
        button_supprimer_evaluationDefense.setDescription("Supprimer");
        button_evaluerTFE.setIcon(FontAwesome.STAR);
        button_evaluerTFE.setDescription("Evaluations");
        button_defenseTFE.setIcon(FontAwesome.SHIELD);
        button_defenseTFE.setDescription("Programmer une défense");
        button_echeanceTFE.setIcon(FontAwesome.CALENDAR);
        button_echeanceTFE.setDescription("Echéances");
        button_evaluerStage.setIcon(FontAwesome.STAR);
        button_evaluerStage.setDescription("Evaluations");
        button_defenseStage.setIcon(FontAwesome.SHIELD);
        button_defenseStage.setDescription("Programmer une défense");
        button_echeanceStage.setIcon(FontAwesome.CALENDAR);
        button_echeanceStage.setDescription("Echéances");
        button_commenterStage.setIcon(FontAwesome.COMMENT);
        button_commenterStage.setDescription("Commenter");
        button_validerPropositionStage.setIcon(FontAwesome.CHECK);
        button_validerPropositionStage.setDescription("Valider");
        button_evaluerDefense.setIcon(FontAwesome.STAR);
        button_evaluerDefense.setDescription("Evaluer");
        button_evaluerStage_maitreDeStage.setIcon(FontAwesome.STAR);
        button_evaluerStage_maitreDeStage.setDescription("Evaluer");
        button_evaluerDefense_presidentDeJury.setIcon(FontAwesome.STAR);
        button_evaluerDefense_presidentDeJury.setDescription("Evaluations");
        button_retour_TFE.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_TFE.setDescription("Retour aux TFE");
        button_retour_Stage.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_Stage.setDescription("Retour aux stages");
        button_retour_TFE2.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_TFE2.setDescription("Retour aux TFE");
        button_retour_Stage2.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_Stage2.setDescription("Retour aux stages");
        button_retour_Defense.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_maitreDeStage.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_maitreDeStage.setDescription("Retour aux stages");
        button_retour_presidentDeJury.setIcon(FontAwesome.ARROW_LEFT);
        button_retour_presidentDeJury.setDescription("Retour aux stages");
        button_modifierEvaluation_presidentDeJury.setIcon(FontAwesome.EDIT);
        button_modifierEvaluation_presidentDeJury.setDescription("Modifier");
        button_supprimerEvaluation_presidentDeJury.setIcon(FontAwesome.TIMES);
        button_supprimerEvaluation_presidentDeJury.setDescription("Supprimer");
        button_modifierEvaluation_maitreDeStage.setIcon(FontAwesome.EDIT);
        button_modifierEvaluation_maitreDeStage.setDescription("Modifier");
        button_supprimerEvaluation_maitreDeStage.setIcon(FontAwesome.TIMES);
        button_supprimerEvaluation_maitreDeStage.setDescription("Supprimer");
        button_retour_Defense.setDescription("Retour aux défenses");
        button_ajouterPropositionStage3.setIcon(FontAwesome.PLUS);
        button_modifierPropositionStage3.setIcon(FontAwesome.EDIT);
        button_supprimerPropositionStage3.setIcon(FontAwesome.TIMES);
        tf_professeur_nom.setIcon(FontAwesome.ASTERISK);
        tf_professeur_prenom.setIcon(FontAwesome.ASTERISK);
        tf_professeur_tel.setIcon(FontAwesome.PHONE);
        tf_professeur_adresseMail.setIcon(FontAwesome.ASTERISK);
        tf_professeur_mdp.setIcon(FontAwesome.ASTERISK);
        button_professeur_valider.setIcon(FontAwesome.CHECK);
        button_professeur_annuler.setIcon(FontAwesome.TIMES);
        tf_etudiant_nom.setIcon(FontAwesome.ASTERISK);
        tf_etudiant_prenom.setIcon(FontAwesome.ASTERISK);
        tf_etudiant_matricule.setIcon(FontAwesome.ASTERISK);
        tf_etudiant_tel.setIcon(FontAwesome.PHONE);
        sl_etudiant_annee.setIcon(FontAwesome.ASTERISK);
        tf_etudiant_mail.setIcon(FontAwesome.ASTERISK);
        tf_etudiant_mdp.setIcon(FontAwesome.ASTERISK);
        button_etudiant_valider.setIcon(FontAwesome.CHECK);
        button_etudiant_annuler.setIcon(FontAwesome.TIMES);
        tf_maitreDeStage_nom.setIcon(FontAwesome.ASTERISK);
        tf_maitreDeStage_prenom.setIcon(FontAwesome.ASTERISK);
        tf_maitreDeStage_tel.setIcon(FontAwesome.PHONE);
        tf_maitreDeStage_mail.setIcon(FontAwesome.ASTERISK);
        tf_maitreDeStage_mdp.setIcon(FontAwesome.ASTERISK);
        button_maitreDeStage_valider.setIcon(FontAwesome.CHECK);
        button_maitreDeStage_annuler.setIcon(FontAwesome.TIMES);
        tf_presidentJury_nom.setIcon(FontAwesome.ASTERISK);
        tf_presidentJury_prenom.setIcon(FontAwesome.ASTERISK);
        tf_presidentJury_tel.setIcon(FontAwesome.PHONE);
        tf_presidentJury_mail.setIcon(FontAwesome.ASTERISK);
        tf_presidentJury_mdp.setIcon(FontAwesome.ASTERISK);
        button_presidentDeJury_valider.setIcon(FontAwesome.CHECK);
        button_presidentDeJury_annuler.setIcon(FontAwesome.TIMES);
        tf_entreprise_nom.setIcon(FontAwesome.ASTERISK);
        ta_entreprise_adresse.setIcon(FontAwesome.ASTERISK);
        tf_entreprise_adresseMail.setIcon(FontAwesome.ENVELOPE);
        tf_entreprise_personneDeContact.setIcon(FontAwesome.ASTERISK);
        tf_entreprise_telephone.setIcon(FontAwesome.PHONE);
        tf_entreprise_nom2.setIcon(FontAwesome.ASTERISK);
        ta_entreprise_adresse2.setIcon(FontAwesome.ASTERISK);
        tf_entreprise_adresseMail2.setIcon(FontAwesome.ENVELOPE);
        tf_entreprise_personneDeContact2.setIcon(FontAwesome.ASTERISK);
        tf_entreprise_telephone2.setIcon(FontAwesome.PHONE);
        button_entreprise_valider.setIcon(FontAwesome.CHECK);
        button_entreprise_annuler.setIcon(FontAwesome.TIMES);
        button_entreprise_valider2.setIcon(FontAwesome.CHECK);
        button_entreprise_annuler2.setIcon(FontAwesome.TIMES);
        tf_critereEvaluation_nom.setIcon(FontAwesome.ASTERISK);
        cb_critereEvaluation_type.setIcon(FontAwesome.ASTERISK);
        tf_critereEvaluation_noteMax.setIcon(FontAwesome.ASTERISK);
        button_critereEvaluation_valider.setIcon(FontAwesome.CHECK);
        button_critereEvaluation_annuler.setIcon(FontAwesome.TIMES);
        tf_technologie_nom.setIcon(FontAwesome.ASTERISK);
        tf_technologie_version.setIcon(FontAwesome.GEARS);
        button_technologie_valider.setIcon(FontAwesome.CHECK);
        button_technologie_annuler.setIcon(FontAwesome.TIMES);
        tf_tfe_ajouter_titre.setIcon(FontAwesome.ASTERISK);
        cb_tfe_ajouter_etudiant.setIcon(FontAwesome.ASTERISK);
        cb_tfe_ajouter_promoteur.setIcon(FontAwesome.ASTERISK);
        tf_tfe_ajouter_anneeDebut.setIcon(FontAwesome.ASTERISK);
        tf_tfe_ajouter_anneeFin.setIcon(FontAwesome.ASTERISK);
        button_tfe_ajouter_valider.setIcon(FontAwesome.CHECK);
        button_tfe_ajouter_annuler.setIcon(FontAwesome.TIMES);
        tfe_evaluer_critere.setIcon(FontAwesome.ASTERISK);
        tfe_evaluer_note.setIcon(FontAwesome.ASTERISK);
        tfe_evaluer_commentaire.setIcon(FontAwesome.COMMENT);
        button_tfe_evaluer_valider.setIcon(FontAwesome.CHECK);
        button_tfe_evaluer_annuler.setIcon(FontAwesome.TIMES);
        tfe_defense_presidentJury.setIcon(FontAwesome.ASTERISK);
        tfe_defense_date.setIcon(FontAwesome.ASTERISK);
        tfe_defense_local.setIcon(FontAwesome.ASTERISK);
        button_tfe_defense_valider.setIcon(FontAwesome.CHECK);
        button_tfe_defense_annuler.setIcon(FontAwesome.TIMES);
        tfe_echeance_description.setIcon(FontAwesome.ASTERISK);
        tfe_echeance_date.setIcon(FontAwesome.ASTERISK);
        tfe_echeance_annexe.setIcon(FontAwesome.UPLOAD);
        button_tfe_echeance_valider.setIcon(FontAwesome.CHECK);
        button_tfe_echeance_annuler.setIcon(FontAwesome.TIMES);
        tf_tfe_modifier_titre.setIcon(FontAwesome.ASTERISK);
        tf_tfe_modifier_anneeDebut.setIcon(FontAwesome.ASTERISK);
        tf_tfe_modifier_anneeFin.setIcon(FontAwesome.ASTERISK);
        button_tfe_modifier_valider.setIcon(FontAwesome.CHECK);
        button_tfe_modifier_annuler.setIcon(FontAwesome.TIMES);
        tf_stage_editer_sujet.setIcon(FontAwesome.ASTERISK);
        cb_stage_editer_etudiant.setIcon(FontAwesome.ASTERISK);
        date_stage_editer_dateDebut.setIcon(FontAwesome.ASTERISK);
        date_stage_editer_dateFin.setIcon(FontAwesome.ASTERISK);
        cb_stage_editer_entreprise.setIcon(FontAwesome.ASTERISK);
        cb_stage_editer_maitreDeStage.setIcon(FontAwesome.ASTERISK);
        cb_stage_editer_promoteur.setIcon(FontAwesome.ASTERISK);
        button_stage_editer_valider.setIcon(FontAwesome.CHECK);
        button_stage_editer_annuler.setIcon(FontAwesome.TIMES);
        stage_evaluer_critere.setIcon(FontAwesome.ASTERISK);
        stage_evaluer_note.setIcon(FontAwesome.ASTERISK);
        stage_evaluer_commentaire.setIcon(FontAwesome.COMMENT);
        button_stage_evaluer_valider.setIcon(FontAwesome.CHECK);
        button_stage_evaluer_annuler.setIcon(FontAwesome.TIMES);
        stage_defense_presidentJury.setIcon(FontAwesome.ASTERISK);
        stage_defense_date.setIcon(FontAwesome.ASTERISK);
        stage_defense_local.setIcon(FontAwesome.ASTERISK);
        button_stage_defense_valider.setIcon(FontAwesome.CHECK);
        button_stage_defense_annuler.setIcon(FontAwesome.TIMES);
        stage_echeance_description.setIcon(FontAwesome.ASTERISK);
        stage_echeance_date.setIcon(FontAwesome.ASTERISK);
        button_stage_echeance_valider.setIcon(FontAwesome.CHECK);
        button_stage_echeance_annuler.setIcon(FontAwesome.TIMES);
        stage_commenter_commentaire.setIcon(FontAwesome.ASTERISK);
        button_stage_commenter_valider.setIcon(FontAwesome.CHECK);
        button_stage_commenter_annuler.setIcon(FontAwesome.TIMES);
        tf_PropositionStage_editer_sujet.setIcon(FontAwesome.ASTERISK);
        cb_PropositionStage_editer_entreprise.setIcon(FontAwesome.ASTERISK);
        button_propositionStage_editer_valider.setIcon(FontAwesome.CHECK);
        button_propositionStage_editer_annuler.setIcon(FontAwesome.TIMES);
        tf_PropositionStage_valider_sujet.setIcon(FontAwesome.ASTERISK);
        date_propositionStage_valider_dateDebut.setIcon(FontAwesome.ASTERISK);
        date_propositionStage_valider_dateFin.setIcon(FontAwesome.ASTERISK);
        cb_PropositionStage_valider_maitreDeStage.setIcon(FontAwesome.ASTERISK);
        cb_PropositionStage_valider_promoteur.setIcon(FontAwesome.ASTERISK);
        button_propositionStage_valider_valider.setIcon(FontAwesome.CHECK);
        button_propositionStage_valider_annuler.setIcon(FontAwesome.TIMES);
        date_defense_date.setIcon(FontAwesome.ASTERISK);
        tf_defense_local.setIcon(FontAwesome.ASTERISK);
        button_defense_editer_valider.setIcon(FontAwesome.CHECK);
        button_defense_editer_annuler.setIcon(FontAwesome.TIMES);
        defense_evaluer_critere.setIcon(FontAwesome.ASTERISK);
        defense_evaluer_note.setIcon(FontAwesome.ASTERISK);
        defense_evaluer_commentaire.setIcon(FontAwesome.COMMENT);
        button_defense_evaluer_valider.setIcon(FontAwesome.CHECK);
        button_defense_evaluer_annuler.setIcon(FontAwesome.TIMES);
        tf_echeance_ajouter_description.setIcon(FontAwesome.ASTERISK);
        date_echeance_ajouter_date.setIcon(FontAwesome.ASTERISK);
        button_echeance_editer_valider.setIcon(FontAwesome.CHECK);
        button_echeance_editer_annuler.setIcon(FontAwesome.TIMES);
        tf_PropositionStage_etudiant_editer_sujet.setIcon(FontAwesome.ASTERISK);
        cb_PropositionStage_etudiant_editer_entreprise.setIcon(FontAwesome.ASTERISK);
        button_propositionStage_etudiant_editer_valider.setIcon(FontAwesome.CHECK);
        button_propositionStage_etudiant_editer_annuler.setIcon(FontAwesome.TIMES);
        stage_evaluer_maitreDeStage_critere.setIcon(FontAwesome.ASTERISK);
        stage_evaluer_maitreDeStage_note.setIcon(FontAwesome.ASTERISK);
        stage_evaluer_maitreDeStage_commentaire.setIcon(FontAwesome.COMMENT);
        button_stage_evaluer_maitreDeStage_valider.setIcon(FontAwesome.CHECK);
        button_stage_evaluer_maitreDeStage_annuler.setIcon(FontAwesome.TIMES);
        defense_evaluer_presidentDeJury_note.setIcon(FontAwesome.ASTERISK);
        defense_evaluer_presidentDeJury_commentaire.setIcon(FontAwesome.COMMENT);
        button_stage_evaluer_maitreDeStage_valider.setIcon(FontAwesome.CHECK);
        button_stage_evaluer_maitreDeStage_annuler.setIcon(FontAwesome.TIMES);
        defense_evaluer_presidentDeJury_critere.setIcon(FontAwesome.ASTERISK);
        button_dupliquerPropositionStage3.setIcon(FontAwesome.COPY);
        button_dupliquerPropositionStage3.setDescription("Dupliquer");
        button_deconnexion.setIcon(FontAwesome.SIGN_OUT);
        cb_tfe_modifier_promoteur.setIcon(FontAwesome.ASTERISK);
        button_search.setIcon(FontAwesome.SEARCH);
        button_search2.setIcon(FontAwesome.SEARCH);
        button_search3.setIcon(FontAwesome.SEARCH);
        button_search4.setIcon(FontAwesome.SEARCH);
        button_search5.setIcon(FontAwesome.SEARCH);
        button_search6.setIcon(FontAwesome.SEARCH);
        button_search7.setIcon(FontAwesome.SEARCH);
        button_deconnexion.setDescription("Déconnexion");
        button_monCompte.setDescription("Mon compte");
        tf_technologie_recherche.setInputPrompt("Rechercher");
        tf_evaluation_recherche.setInputPrompt("Rechercher");
        tf_entreprise_recherche.setInputPrompt("Rechercher");
        tf_presidentDeJury_recherche.setInputPrompt("Rechercher");
        tf_maitreDeStage_recherche.setInputPrompt("Rechercher");
        tf_etudiant_recherche.setInputPrompt("Rechercher");
        tf_professeur_recherche.setInputPrompt("Rechercher");
        cb_etudiant_permission.setInputPrompt("Permission");
        defense_evaluer_presidentDeJury_critere.setInputPrompt("Critère");
        cb_critereEvaluation_type.setInputPrompt("Type");
        defense_evaluer_critere.setInputPrompt("Critère");
        cb_tfe_technologie.setInputPrompt("Technologie");
        cb_tfe_anneeAcademique.setInputPrompt("Année académique");
        tf_tfe_etudiant.setInputPrompt("Etudiant");
        cb_tfe_ajouter_promoteur.setInputPrompt("Promoteur");
        cb_tfe_ajouter_etudiant.setInputPrompt("Etudiant");
        tfe_evaluer_critere.setInputPrompt("Critère");
        tfe_defense_presidentJury.setInputPrompt("Président Jury");
        cb_tfe_technologie2.setInputPrompt("Technologie");
        cb_tfe_anneeAcademique2.setInputPrompt("Année académique");
        tf_tfe_etudiant2.setInputPrompt("Etudiant");
        cb_stage_technologie.setInputPrompt("Technologie");
        cb_stage_entreprise.setInputPrompt("Entreprise");
        tf_stage_adresse.setInputPrompt("Adresse");
        cb_stage_anneeAcademique.setInputPrompt("Année académique");
        stage_evaluer_critere.setInputPrompt("Critère");
        cb_stage_technologie3.setInputPrompt("Technologie");
        cb_stage_entreprise3.setInputPrompt("Entreprise");
        tf_stage_adresse3.setInputPrompt("Adresse");
        cb_stage_anneeAcademique3.setInputPrompt("Année académique");
        cb_PropositionStage_editer_entreprise.setInputPrompt("Entreprise");
        cb_PropositionStage_valider_maitreDeStage.setInputPrompt("Maitre de stage");
        cb_PropositionStage_valider_promoteur.setInputPrompt("Promoteur");
        cb_PropositionStage_etudiant_editer_entreprise.setInputPrompt("Entreprise");
        stage_evaluer_maitreDeStage_critere.setInputPrompt("Critère");
        tc_tfe_ajouter_technologie.setItemCaptionMode(ItemCaptionMode.ID);
        tc_tfe_modifier_technologie.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_technologie.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_anneeAcademique.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_ajouter_promoteur.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_ajouter_etudiant.setItemCaptionMode(ItemCaptionMode.ID);
        tfe_evaluer_critere.setItemCaptionMode(ItemCaptionMode.ID);
        tfe_defense_presidentJury.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_technologie2.setItemCaptionMode(ItemCaptionMode.ID);
        cb_tfe_anneeAcademique2.setItemCaptionMode(ItemCaptionMode.ID);
        tc_stage_editer_technologies.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_technologie.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_editer_entreprise.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_entreprise.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_anneeAcademique.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_editer_promoteur.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_editer_maitreDeStage.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_editer_etudiant.setItemCaptionMode(ItemCaptionMode.ID);
        stage_evaluer_critere.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_technologie3.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_entreprise3.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_anneeAcademique3.setItemCaptionMode(ItemCaptionMode.ID);
        cb_PropositionStage_editer_entreprise.setItemCaptionMode(ItemCaptionMode.ID);
        cb_PropositionStage_valider_maitreDeStage.setItemCaptionMode(ItemCaptionMode.ID);
        cb_PropositionStage_valider_promoteur.setItemCaptionMode(ItemCaptionMode.ID);
        tc_PropositionStage_valider_technologies.setItemCaptionMode(ItemCaptionMode.ID);
        cb_PropositionStage_etudiant_editer_entreprise.setItemCaptionMode(ItemCaptionMode.ID);
        stage_evaluer_maitreDeStage_critere.setItemCaptionMode(ItemCaptionMode.ID);
        tc_echeance_ajouter_utilisateurs.setItemCaptionMode(ItemCaptionMode.ID);
        cb_etudiant_permission.setItemCaptionMode(ItemCaptionMode.ID);
        defense_evaluer_presidentDeJury_critere.setItemCaptionMode(ItemCaptionMode.ID);
        cb_critereEvaluation_type.setItemCaptionMode(ItemCaptionMode.ID);
        defense_evaluer_critere.setItemCaptionMode(ItemCaptionMode.ID);
        cb_stage_technologie.setNullSelectionAllowed(true);
        cb_stage_entreprise.setNullSelectionAllowed(true);
        cb_stage_anneeAcademique.setNullSelectionAllowed(true);
        cb_stage_editer_promoteur.setNullSelectionAllowed(false);
        cb_stage_editer_maitreDeStage.setNullSelectionAllowed(false);
        cb_stage_editer_etudiant.setNullSelectionAllowed(false);
        stage_evaluer_critere.setNullSelectionAllowed(false);
        cb_stage_technologie3.setNullSelectionAllowed(true);
        cb_stage_entreprise3.setNullSelectionAllowed(true);
        cb_stage_anneeAcademique3.setNullSelectionAllowed(true);
        cb_PropositionStage_editer_entreprise.setNullSelectionAllowed(false);
        cb_PropositionStage_valider_maitreDeStage.setNullSelectionAllowed(false);
        cb_PropositionStage_valider_promoteur.setNullSelectionAllowed(false);
        cb_PropositionStage_etudiant_editer_entreprise.setNullSelectionAllowed(false);
        stage_evaluer_maitreDeStage_critere.setNullSelectionAllowed(false);
        cb_etudiant_permission.setNullSelectionAllowed(false);
        defense_evaluer_presidentDeJury_critere.setNullSelectionAllowed(false);
        cb_critereEvaluation_type.setNullSelectionAllowed(false);
        defense_evaluer_critere.setNullSelectionAllowed(false);
        cb_tfe_technologie.setNullSelectionAllowed(true);
        tfe_evaluer_critere.setNullSelectionAllowed(false);
        cb_tfe_ajouter_etudiant.setNullSelectionAllowed(false);
        tfe_defense_presidentJury.setNullSelectionAllowed(false);
        cb_tfe_technologie2.setNullSelectionAllowed(true);
        cb_tfe_anneeAcademique2.setNullSelectionAllowed(true);
        cb_tfe_anneeAcademique.setNullSelectionAllowed(true);
        cb_tfe_ajouter_promoteur.setNullSelectionAllowed(false);
        cb_critereEvaluation_type.setTextInputAllowed(false);
        stage_defense_presidentJury.setItemCaptionMode(ItemCaptionMode.ID);
        stage_defense_presidentJury.setTextInputAllowed(false);
        stage_defense_presidentJury.setNullSelectionAllowed(false);


        // Ajout des colonnes aux tableaux
        tab_professeurs.addContainerProperty("Nom", String.class, null);
        tab_professeurs.addContainerProperty("Prénom", String.class, null);
        tab_professeurs.addContainerProperty("Adresse mail", String.class, null);
        tab_professeurs.addContainerProperty("Téléphone", String.class, null);
        tab_professeurs.setSelectable(true);

        tab_etudiants.addContainerProperty("Nom", String.class, null);
        tab_etudiants.addContainerProperty("Prénom", String.class, null);
        tab_etudiants.addContainerProperty("Matricule", Integer.class, null);
        tab_etudiants.addContainerProperty("Adresse mail", String.class, null);
        tab_etudiants.addContainerProperty("Téléphone", String.class, null);
        tab_etudiants.setSelectable(true);

        tab_maitresDeStage.addContainerProperty("Nom", String.class, null);
        tab_maitresDeStage.addContainerProperty("Prénom", String.class, null);
        tab_maitresDeStage.addContainerProperty("Téléphone", String.class, null);
        tab_maitresDeStage.addContainerProperty("Adresse mail", String.class, null);
        tab_maitresDeStage.setSelectable(true);

        tab_presidentsDeJury.addContainerProperty("Nom", String.class, null);
        tab_presidentsDeJury.addContainerProperty("Prénom", String.class, null);
        tab_presidentsDeJury.addContainerProperty("Téléphone", String.class, null);
        tab_presidentsDeJury.addContainerProperty("Adresse mail", String.class, null);
        tab_presidentsDeJury.setSelectable(true);

        tab_entreprises.addContainerProperty("Nom", String.class, null);
        tab_entreprises.addContainerProperty("Adresse", String.class, null);
        tab_entreprises.addContainerProperty("Personne", String.class, null);
        tab_entreprises.addContainerProperty("Téléphone", String.class, null);
        tab_entreprises.setSelectable(true);

        tab_entreprises3.addContainerProperty("Nom", String.class, null);
        tab_entreprises3.addContainerProperty("Adresse", String.class, null);
        tab_entreprises3.addContainerProperty("Personne", String.class, null);
        tab_entreprises3.addContainerProperty("Téléphone", String.class, null);
        tab_entreprises3.setSelectable(false);

        tab_criteresEvaluation.addContainerProperty("Nom", String.class, null);
        tab_criteresEvaluation.addContainerProperty("Type", String.class, null);
        tab_criteresEvaluation.addContainerProperty("Note maximale", Integer.class, null);
        tab_criteresEvaluation.setSelectable(true);
        cb_critereEvaluation_type.setNullSelectionAllowed(false);

        tab_technologies.addContainerProperty("Nom", String.class, null);
        tab_technologies.addContainerProperty("Version", String.class, null);
        tab_technologies.setSelectable(true);

        tab_tfe.addContainerProperty("Titre", String.class, null);
        tab_tfe.addContainerProperty("Etudiant", String.class, null);
        tab_tfe.addContainerProperty("Promoteur", String.class, null);
        tab_tfe.addContainerProperty("Note", Double.class, null);
        tab_tfe.addContainerProperty("Année de début", Integer.class, null);
        tab_tfe.addContainerProperty("Année de fin", Integer.class, null);
        tab_tfe.setSelectable(true);

        tab_echeance3.addContainerProperty("Auteur", String.class, null);
        tab_echeance3.addContainerProperty("Date de création", Date.class, null);
        tab_echeance3.addContainerProperty("Date d'échéance", Date.class, null);
        tab_echeance3.addContainerProperty("Description", String.class, null);
        tab_echeance3.setSelectable(true);

        tab_evaluationTFE.addContainerProperty("Critère", String.class, null);
        tab_evaluationTFE.addContainerProperty("Note", Double.class, null);
        tab_evaluationTFE.addContainerProperty("Commentaire", String.class, null);
        tab_evaluationTFE.addContainerProperty("Auteur", String.class, null);
        tab_evaluationTFE.addContainerProperty("Date", Date.class, null);
        tab_evaluationTFE.setSelectable(true);

        tab_stage.addContainerProperty("Sujet", String.class, null);
        tab_stage.addContainerProperty("Etudiant", String.class, null);
        tab_stage.addContainerProperty("Promoteur", String.class, null);
        tab_stage.addContainerProperty("Date de début", String.class, null);
        tab_stage.addContainerProperty("Date de fin", String.class, null);
        tab_stage.addContainerProperty("Entreprise", String.class, null);
        tab_stage.addContainerProperty("Note", Double.class, null);
        tab_stage.setSelectable(true);

        tab_echeance4.addContainerProperty("Auteur", String.class, null);
        tab_echeance4.addContainerProperty("Date de création", Date.class, null);
        tab_echeance4.addContainerProperty("Date d'échéance", Date.class, null);
        tab_echeance4.addContainerProperty("Description", String.class, null);
        tab_echeance4.setSelectable(true);

        tab_evaluationStage.addContainerProperty("Critère", String.class, null);
        tab_evaluationStage.addContainerProperty("Note", Double.class, null);
        tab_evaluationStage.addContainerProperty("Commentaire", String.class, null);
        tab_evaluationStage.addContainerProperty("Auteur", String.class, null);
        tab_evaluationStage.addContainerProperty("Date", Date.class, null);
        tab_evaluationStage.setSelectable(true);

        tab_propositionStage.addContainerProperty("Sujet", String.class, null);
        tab_propositionStage.addContainerProperty("Proposée par", String.class, null);
        tab_propositionStage.addContainerProperty("Lieu de stage", String.class, null);
        tab_propositionStage.addContainerProperty("Entreprise", String.class, null);
        tab_propositionStage.setSelectable(true);

        tab_defense.addContainerProperty("Type", String.class, null);
        tab_defense.addContainerProperty("Etudiant", String.class, null);
        tab_defense.addContainerProperty("Sujet", String.class, null);
        tab_defense.addContainerProperty("Local", String.class, null);
        tab_defense.addContainerProperty("Date", Date.class, null);
        tab_defense.addContainerProperty("Président de jury", String.class, null);
        tab_defense.setSelectable(true);

        tab_evaluationDefense.addContainerProperty("Critère", String.class, null);
        tab_evaluationDefense.addContainerProperty("Note", Double.class, null);
        tab_evaluationDefense.addContainerProperty("Commentaire", String.class, null);
        tab_evaluationDefense.addContainerProperty("Auteur", String.class, null);
        tab_evaluationDefense.addContainerProperty("Date", Date.class, null);
        tab_evaluationDefense.setSelectable(true);

        tab_tfe2.addContainerProperty("Titre", String.class, null);
        tab_tfe2.addContainerProperty("Etudiant", String.class, null);
        tab_tfe2.addContainerProperty("Promoteur", String.class, null);
        tab_tfe2.addContainerProperty("Année de début", Integer.class, null);
        tab_tfe2.addContainerProperty("Année de fin", Integer.class, null);

        tab_stage2.addContainerProperty("Sujet", String.class, null);
        tab_stage2.addContainerProperty("Etudiant", String.class, null);
        tab_stage2.addContainerProperty("Promoteur", String.class, null);
        tab_stage2.addContainerProperty("Date de début", String.class, null);
        tab_stage2.addContainerProperty("Date de fin", String.class, null);
        tab_stage2.addContainerProperty("Entreprise", String.class, null);

        tab_propositionStage2.addContainerProperty("Sujet", String.class, null);
        tab_propositionStage2.addContainerProperty("Proposée par", String.class, null);
        tab_propositionStage2.addContainerProperty("Lieu de stage", String.class, null);
        tab_propositionStage2.addContainerProperty("Entreprise", String.class, null);
        tab_propositionStage2.setSelectable(true);

        tab_defense2.addContainerProperty("Type", String.class, null);
        tab_defense2.addContainerProperty("Etudiant", String.class, null);
        tab_defense2.addContainerProperty("Sujet", String.class, null);
        tab_defense2.addContainerProperty("Local", String.class, null);
        tab_defense2.addContainerProperty("Date", Date.class, null);
        tab_defense2.addContainerProperty("Promoteur", String.class, null);
        tab_defense2.addContainerProperty("Président de jury", String.class, null);

        tab_echeance.addContainerProperty("Type", String.class, null);
        tab_echeance.addContainerProperty("Auteur", String.class, null);
        tab_echeance.addContainerProperty("Date de création", Date.class, null);
        tab_echeance.addContainerProperty("Date d'échéance", Date.class, null);
        tab_echeance.addContainerProperty("Description", String.class, null);

        tab_echeance2.addContainerProperty("Type", String.class, null);
        tab_echeance2.addContainerProperty("Auteur", String.class, null);
        tab_echeance2.addContainerProperty("Date de création", Date.class, null);
        tab_echeance2.addContainerProperty("Date d'échéance", Date.class, null);
        tab_echeance2.addContainerProperty("Description", String.class, null);
        tab_echeance2.setSelectable(true);

        tab_stage3.addContainerProperty("Sujet", String.class, null);
        tab_stage3.addContainerProperty("Etudiant", String.class, null);
        tab_stage3.addContainerProperty("Promoteur", String.class, null);
        tab_stage3.addContainerProperty("Date de début", String.class, null);
        tab_stage3.addContainerProperty("Date de fin", String.class, null);
        tab_stage3.addContainerProperty("Entreprise", String.class, null);
        tab_stage3.addContainerProperty("Note", Double.class, null);
        tab_stage3.setSelectable(true);

        tab_evaluations_maitreStage.addContainerProperty("Etudiant", String.class, null);
        tab_evaluations_maitreStage.addContainerProperty("Critère", String.class, null);
        tab_evaluations_maitreStage.addContainerProperty("Note", Double.class, null);
        tab_evaluations_maitreStage.addContainerProperty("Commentaire", String.class, null);
        tab_evaluations_maitreStage.addContainerProperty("Auteur", String.class, null);
        tab_evaluations_maitreStage.addContainerProperty("Date", Date.class, null);
        tab_evaluations_maitreStage.setSelectable(true);

        tab_defense3.addContainerProperty("Type", String.class, null);
        tab_defense3.addContainerProperty("Etudiant", String.class, null);
        tab_defense3.addContainerProperty("Sujet", String.class, null);
        tab_defense3.addContainerProperty("Local", String.class, null);
        tab_defense3.addContainerProperty("Date", Date.class, null);
        tab_defense3.addContainerProperty("Président de jury", String.class, null);
        tab_defense3.setSelectable(true);

        tab_evaluations_presidentJury.addContainerProperty("Etudiant", String.class, null);
        tab_evaluations_presidentJury.addContainerProperty("Critère", String.class, null);
        tab_evaluations_presidentJury.addContainerProperty("Note", Double.class, null);
        tab_evaluations_presidentJury.addContainerProperty("Commentaire", String.class, null);
        tab_evaluations_presidentJury.addContainerProperty("Auteur", String.class, null);
        tab_evaluations_presidentJury.addContainerProperty("Date", Date.class, null);
        tab_evaluations_presidentJury.setSelectable(true);
    }
}

