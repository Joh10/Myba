package ui;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;

/**
 * !! DO NOT EDIT THIS FILE !!
 * <p>
 * This class is generated by Vaadin Designer and will be overwritten.
 * <p>
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { … }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Dashboard_design extends CssLayout
{
    protected VerticalLayout bandeau;
    protected Button button_monCompte;
    protected Button button_deconnexion;
    protected TabSheet onglets;
    protected VerticalLayout onglet_administration;
    protected TabSheet onglets_administration;
    protected VerticalLayout onglet_prof;
    protected Button button_search7;
    protected TextField tf_professeur_recherche;
    protected Table tab_professeurs;
    protected Button ajouter;
    protected Button modifier;
    protected Button supprimer;
    protected FormLayout form_prof;
    protected TextField tf_professeur_nom;
    protected TextField tf_professeur_prenom;
    protected TextField tf_professeur_tel;
    protected TextField tf_professeur_adresseMail;
    protected PasswordField tf_professeur_mdp;
    protected CheckBox cb_professeur_compteActif;
    protected Button button_professeur_valider;
    protected Button button_professeur_annuler;
    protected VerticalLayout onglet_etudiant;
    protected Button button_search6;
    protected TextField tf_etudiant_recherche;
    protected Table tab_etudiants;
    protected Button ajouter2;
    protected Button modifier2;
    protected Button supprimer2;
    protected FormLayout form_etudiant;
    protected TextField tf_etudiant_nom;
    protected TextField tf_etudiant_prenom;
    protected TextField tf_etudiant_matricule;
    protected TextField tf_etudiant_tel;
    protected Slider sl_etudiant_annee;
    protected CheckBox cb_etudiant_doublant;
    protected TextField tf_etudiant_mail;
    protected PasswordField tf_etudiant_mdp;
    protected CheckBox cb_etudiant_compteActif;
    protected ComboBox cb_etudiant_permission;
    protected Button button_etudiant_valider;
    protected Button button_etudiant_annuler;
    protected VerticalLayout onglet_maitreStage;
    protected Button button_search5;
    protected TextField tf_maitreDeStage_recherche;
    protected Table tab_maitresDeStage;
    protected Button ajouter3;
    protected Button modifier3;
    protected Button supprimer3;
    protected FormLayout form_maitreDeStage;
    protected TextField tf_maitreDeStage_nom;
    protected TextField tf_maitreDeStage_prenom;
    protected TextField tf_maitreDeStage_tel;
    protected TextField tf_maitreDeStage_mail;
    protected PasswordField tf_maitreDeStage_mdp;
    protected CheckBox cb_maitreDeStage_compteActif;
    protected Button button_maitreDeStage_valider;
    protected Button button_maitreDeStage_annuler;
    protected VerticalLayout onglet_presidentJury;
    protected Button button_search4;
    protected TextField tf_presidentDeJury_recherche;
    protected Table tab_presidentsDeJury;
    protected Button ajouter4;
    protected Button modifier4;
    protected Button supprimer4;
    protected FormLayout form_presidentDeJury;
    protected TextField tf_presidentJury_nom;
    protected TextField tf_presidentJury_prenom;
    protected TextField tf_presidentJury_tel;
    protected TextField tf_presidentJury_mail;
    protected PasswordField tf_presidentJury_mdp;
    protected CheckBox tf_presidentJury_compteActif;
    protected Button button_presidentDeJury_valider;
    protected Button button_presidentDeJury_annuler;
    protected VerticalLayout onglet_Entreprise;
    protected Button button_search3;
    protected TextField tf_entreprise_recherche;
    protected Table tab_entreprises;
    protected Button ajouter5;
    protected Button modifier5;
    protected Button supprimer5;
    protected FormLayout form_entreprise;
    protected Label label_entreprise;
    protected TextField tf_entreprise_nom;
    protected TextArea ta_entreprise_adresse;
    protected TextField tf_entreprise_personneDeContact;
    protected TextField tf_entreprise_adresseMail;
    protected TextField tf_entreprise_telephone;
    protected Button button_entreprise_valider;
    protected Button button_entreprise_annuler;
    protected VerticalLayout onglet_CritereEvaluation;
    protected Button button_search2;
    protected TextField tf_evaluation_recherche;
    protected Table tab_criteresEvaluation;
    protected Button ajouter6;
    protected Button modifier6;
    protected Button supprimer6;
    protected FormLayout form_critereEvaluation_editer;
    protected TextField tf_critereEvaluation_nom;
    protected ComboBox cb_critereEvaluation_type;
    protected TextField tf_critereEvaluation_noteMax;
    protected Button button_critereEvaluation_valider;
    protected Button button_critereEvaluation_annuler;
    protected VerticalLayout onglet_technologies;
    protected Button button_search;
    protected TextField tf_technologie_recherche;
    protected Table tab_technologies;
    protected Button ajouter7;
    protected Button modifier7;
    protected Button supprimer7;
    protected FormLayout form_technologie;
    protected TextField tf_technologie_nom;
    protected TextField tf_technologie_version;
    protected Button button_technologie_valider;
    protected Button button_technologie_annuler;
    protected VerticalLayout onglet_professeurs;
    protected TabSheet onglets_prof;
    protected VerticalLayout onglet_gererTFE;
    protected HorizontalSplitPanel split_prof_tfe;
    protected Button button_retour_TFE;
    protected Label label_echeances_tfe;
    protected HorizontalSplitPanel tfe_cb_recherche;
    protected ComboBox cb_tfe_technologie;
    protected ComboBox cb_tfe_anneeAcademique;
    protected TextField tf_tfe_etudiant;
    protected Button button_retour_TFE2;
    protected Label label_evaluationTFE;
    protected Table tab_tfe;
    protected Table tab_echeance3;
    protected Table tab_evaluationTFE;
    protected AbsoluteLayout layout_prof_outils_tfe;
    protected Button button_ajouterTFE;
    protected Button button_modifierTFE;
    protected Button button_supprimerTFE;
    protected Button button_evaluerTFE;
    protected Button button_defenseTFE;
    protected Button button_echeanceTFE;
    protected Button button_TFE_ajouterEcheance;
    protected Button button_TFE_modifierEcheance;
    protected Button button_TFE_supprimerEcheance;
    protected Button buton_ajouter_evaluationTFE;
    protected Button button_modifier_evaluationTFE;
    protected Button button_supprimer_evaluationTFE;
    protected FormLayout form_TFE_ajouter;
    protected TextField tf_tfe_ajouter_titre;
    protected ComboBox cb_tfe_ajouter_etudiant;
    protected ComboBox cb_tfe_ajouter_promoteur;
    protected TextField tf_tfe_ajouter_anneeDebut;
    protected TextField tf_tfe_ajouter_anneeFin;
    protected FormLayout form_TFE_evaluer;
    protected ComboBox tfe_evaluer_critere;
    protected TextField tfe_evaluer_note;
    protected TextArea tfe_evaluer_commentaire;
    protected Button button_tfe_evaluer_valider;
    protected Button button_tfe_evaluer_annuler;
    protected FormLayout form_TFE_defense;
    protected ComboBox tfe_defense_presidentJury;
    protected PopupDateField tfe_defense_date;
    protected TextField tfe_defense_local;
    protected Button button_tfe_defense_valider;
    protected Button button_tfe_defense_annuler;
    protected FormLayout form_TFE_echeance;
    protected TextArea tfe_echeance_description;
    protected PopupDateField tfe_echeance_date;
    protected Upload tfe_echeance_annexe;
    protected Button button_tfe_echeance_valider;
    protected Button button_tfe_echeance_annuler;
    protected FormLayout form_TFE_modifier;
    protected TextField tf_tfe_modifier_titre;
    protected TextField tf_tfe_modifier_anneeDebut;
    protected TextField tf_tfe_modifier_anneeFin;
    protected ComboBox cb_tfe_modifier_promoteur;
    protected AbsoluteLayout form_TFE_ajouter2;
    protected Button button_tfe_ajouter_annuler;
    protected Button button_tfe_ajouter_valider;
    protected TwinColSelect tc_tfe_ajouter_technologie;
    protected AbsoluteLayout form_TFE_modifier2;
    protected TwinColSelect tc_tfe_modifier_technologie;
    protected Button button_tfe_modifier_annuler;
    protected Button button_tfe_modifier_valider;
    protected VerticalLayout onglet_gererStages;
    protected HorizontalSplitPanel stage_cb_recherche;
    protected ComboBox cb_stage_technologie;
    protected ComboBox cb_stage_entreprise;
    protected ComboBox cb_stage_anneeAcademique;
    protected TextField tf_stage_adresse;
    protected Button button_retour_Stage;
    protected Label label_echeances_stage;
    protected Button button_retour_Stage2;
    protected Label label_evaluationStage;
    protected Table tab_stage;
    protected Table tab_echeance4;
    protected Table tab_evaluationStage;
    protected Button button_modifierStage;
    protected Button button_supprimerStage;
    protected Button button_evaluerStage;
    protected Button button_defenseStage;
    protected Button button_echeanceStage;
    protected Button button_commenterStage;
    protected Button button_stage_ajouterEcheance;
    protected Button button_stage_modifierEcheance;
    protected Button button_stage_supprimerEcheance;
    protected Button buton_ajouter_evaluationStage;
    protected Button button_modifier_evaluationStage;
    protected Button button_supprimer_evaluationStage;
    protected FormLayout form_stage_editer;
    protected TextField tf_stage_editer_sujet;
    protected ComboBox cb_stage_editer_etudiant;
    protected DateField date_stage_editer_dateDebut;
    protected DateField date_stage_editer_dateFin;
    protected ComboBox cb_stage_editer_entreprise;
    protected ComboBox cb_stage_editer_maitreDeStage;
    protected ComboBox cb_stage_editer_promoteur;
    protected FormLayout form_stage_evaluer;
    protected ComboBox stage_evaluer_critere;
    protected TextField stage_evaluer_note;
    protected TextArea stage_evaluer_commentaire;
    protected Button button_stage_evaluer_valider;
    protected Button button_stage_evaluer_annuler;
    protected FormLayout form_stage_defense;
    protected ComboBox stage_defense_presidentJury;
    protected DateField stage_defense_date;
    protected TextField stage_defense_local;
    protected Button button_stage_defense_valider;
    protected Button button_stage_defense_annuler;
    protected FormLayout form_stage_echeance;
    protected TextArea stage_echeance_description;
    protected PopupDateField stage_echeance_date;
    protected Button button_stage_echeance_valider;
    protected Button button_stage_echeance_annuler;
    protected FormLayout form_stage_commenter;
    protected TextArea stage_commenter_commentaire;
    protected Button button_stage_commenter_valider;
    protected Button button_stage_commenter_annuler;
    protected AbsoluteLayout form_stage_editer2;
    protected Button button_stage_editer_annuler;
    protected Button button_stage_editer_valider;
    protected TwinColSelect tc_stage_editer_technologies;
    protected VerticalLayout onglet_gererPropositionsStage;
    protected Table tab_propositionStage;
    protected Button button_ajouterPropositionStage;
    protected Button button_modifierPropositionStage;
    protected Button button_supprimerPropositionStage;
    protected Button button_validerPropositionStage;
    protected FormLayout form_propositionStage_editer;
    protected TextField tf_PropositionStage_editer_sujet;
    protected ComboBox cb_PropositionStage_editer_entreprise;
    protected Upload upload_professeur_propositionStage_editer_annexe;
    protected Button button_propositionStage_editer_valider;
    protected Button button_propositionStage_editer_annuler;
    protected FormLayout form_propositionStage_valider;
    protected TextField tf_PropositionStage_valider_sujet;
    protected PopupDateField date_propositionStage_valider_dateDebut;
    protected PopupDateField date_propositionStage_valider_dateFin;
    protected ComboBox cb_PropositionStage_valider_maitreDeStage;
    protected ComboBox cb_PropositionStage_valider_promoteur;
    protected AbsoluteLayout form_propositionStage_valider2;
    protected Button button_propositionStage_valider_annuler;
    protected Button button_propositionStage_valider_valider;
    protected TwinColSelect tc_PropositionStage_valider_technologies;
    protected VerticalLayout onglet_gererDefenses;
    protected Table tab_defense;
    protected Table tab_evaluationDefense;
    protected Button button_modifierDefense;
    protected Button button_supprimerDefense;
    protected Button button_evaluerDefense;
    protected Button button_ajouter_evaluationDefense;
    protected Button button_modifier_evaluationDefense;
    protected Button button_supprimer_evaluationDefense;
    protected Button button_retour_Defense;
    protected FormLayout form_defense_editer;
    protected PopupDateField date_defense_date;
    protected TextField tf_defense_local;
    protected Button button_defense_editer_valider;
    protected Button button_defense_editer_annuler;
    protected FormLayout form_defense_evaluer;
    protected ComboBox defense_evaluer_critere;
    protected TextField defense_evaluer_note;
    protected TextArea defense_evaluer_commentaire;
    protected Button button_defense_evaluer_valider;
    protected Button button_defense_evaluer_annuler;
    protected VerticalLayout onglet_gererEcheance;
    protected Table tab_echeance2;
    protected Button button_ajouterEcheance;
    protected Button button_modifierEcheance;
    protected Button button_supprimerEcheance;
    protected FormLayout form_echeance_editer;
    protected TextArea tf_echeance_ajouter_description;
    protected DateField date_echeance_ajouter_date;
    protected Upload upload_echeance_ajouter_annexe;
    protected AbsoluteLayout form_echeance_editer2;
    protected TwinColSelect tc_echeance_ajouter_utilisateurs;
    protected Button button_echeance_editer_annuler;
    protected Button button_echeance_editer_valider;
    protected VerticalLayout onglet_etudiants;
    protected TabSheet onglets_etudiant;
    protected VerticalLayout onglet_etudiant_TFE;
    protected ComboBox cb_tfe_technologie2;
    protected ComboBox cb_tfe_anneeAcademique2;
    protected TextField tf_tfe_etudiant2;
    protected Table tab_tfe2;
    protected VerticalLayout onglet_etudiant_stages;
    protected ComboBox cb_stage_technologie3;
    protected ComboBox cb_stage_entreprise3;
    protected ComboBox cb_stage_anneeAcademique3;
    protected TextField tf_stage_adresse3;
    protected Table tab_stage2;
    protected VerticalLayout onglet_etudiant_propositionStage;
    protected Table tab_propositionStage2;
    protected Button button_ajouterPropositionStage3;
    protected Button button_modifierPropositionStage3;
    protected Button button_supprimerPropositionStage3;
    protected Button button_dupliquerPropositionStage3;
    protected FormLayout form_propositionStage_etudiant_editer;
    protected TextField tf_PropositionStage_etudiant_editer_sujet;
    protected ComboBox cb_PropositionStage_etudiant_editer_entreprise;
    protected Button button_etudiant_ajouterEntreprise;
    protected Upload upload_etudiant_propositionStage_editer_annexe;
    protected Button button_propositionStage_etudiant_editer_valider;
    protected Button button_propositionStage_etudiant_editer_annuler;
    protected FormLayout form_propositionStage_etudiant_ajouterEntreprise;
    protected TextField tf_entreprise_nom2;
    protected TextArea ta_entreprise_adresse2;
    protected TextField tf_entreprise_personneDeContact2;
    protected TextField tf_entreprise_adresseMail2;
    protected TextField tf_entreprise_telephone2;
    protected Button button_entreprise_valider2;
    protected Button button_entreprise_annuler2;
    protected VerticalLayout onglet_etudiant_echeance;
    protected Table tab_echeance;
    protected FormLayout form_defense_editer3;
    protected ComboBox stage_defense_presidentJury7;
    protected PopupDateField stage_defense_date7;
    protected TextField tf_stage_defense_heure6;
    protected TextField stage_defense_local7;
    protected Button button_defense_editer_valider3;
    protected Button button_defense_editer_annuler3;
    protected FormLayout form_defense_evaluer3;
    protected ComboBox defense_evaluer_critere3;
    protected TextField defense_evaluer_note3;
    protected TextArea defense_evaluer_commentaire3;
    protected PopupDateField defense_evaluer_date3;
    protected Button button_defense_evaluer_valider3;
    protected Button button_defense_evaluer_annuler3;
    protected VerticalLayout onglet_etudiant_defense;
    protected Table tab_defense2;
    protected FormLayout form_defense_editer2;
    protected ComboBox stage_defense_presidentJury6;
    protected PopupDateField stage_defense_date6;
    protected TextField tf_stage_defense_heure5;
    protected TextField stage_defense_local6;
    protected Button button_defense_editer_valider2;
    protected Button button_defense_editer_annuler2;
    protected FormLayout form_defense_evaluer2;
    protected ComboBox defense_evaluer_critere2;
    protected TextField defense_evaluer_note2;
    protected TextArea defense_evaluer_commentaire2;
    protected PopupDateField defense_evaluer_date2;
    protected Button button_defense_evaluer_valider2;
    protected Button button_defense_evaluer_annuler2;
    protected VerticalLayout onglet_Entreprise3;
    protected Button button_search16;
    protected TextField tf_entreprise_recherche3;
    protected Table tab_entreprises3;
    protected VerticalLayout onglet_maitreDeStage;
    protected TabSheet onglets_maitreDeStage;
    protected VerticalLayout onglet_maitreDeStage_stage;
    protected Table tab_stage3;
    protected Table tab_evaluations_maitreStage;
    protected Button button_evaluerStage_maitreDeStage;
    protected Button button_retour_maitreDeStage;
    protected Button button_modifierEvaluation_maitreDeStage;
    protected Button button_supprimerEvaluation_maitreDeStage;
    protected FormLayout form_stage_maitreDeStage_evaluer;
    protected Label label_maitreStage_form;
    protected ComboBox stage_evaluer_maitreDeStage_critere;
    protected TextField stage_evaluer_maitreDeStage_note;
    protected TextArea stage_evaluer_maitreDeStage_commentaire;
    protected Button button_stage_evaluer_maitreDeStage_valider;
    protected Button button_stage_evaluer_maitreDeStage_annuler;
    protected VerticalLayout onglet_presidentDeJury;
    protected TabSheet onglets_presidentDeJury;
    protected VerticalLayout onglet_presidentDeJury_defense;
    protected Table tab_defense3;
    protected Table tab_evaluations_presidentJury;
    protected Button button_evaluerDefense_presidentDeJury;
    protected Button button_retour_presidentDeJury;
    protected Button button_modifierEvaluation_presidentDeJury;
    protected Button button_supprimerEvaluation_presidentDeJury;
    protected FormLayout form_defense_evaluer_presidentDeJury;
    protected Label label_form_presidentJury;
    protected ComboBox defense_evaluer_presidentDeJury_critere;
    protected TextField defense_evaluer_presidentDeJury_note;
    protected TextArea defense_evaluer_presidentDeJury_commentaire;
    protected Button button_defense_evaluer_presidentDeJury_valider;
    protected Button button_defense_evaluer_presidentDeJury_annuler;

    public Dashboard_design()
    {
        Design.read(this);
    }
}
