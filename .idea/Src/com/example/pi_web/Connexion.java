package com.example.pi_web;

import bean.*;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

import concret.DAO_Utilisateur;

@SuppressWarnings({"serial", "deprecation"})
public class Connexion extends Connexion_design implements View {
	private static final long serialVersionUID = -1786729494327199327L;
	private Navigator navigateur;
	private WrappedSession currentSession;

	public Connexion() {
		currentSession = VaadinService.getCurrentRequest().getWrappedSession();
		identifiant.focus();
		button_connexion.setClickShortcut(KeyCode.ENTER);
		button_connexion.setIcon(FontAwesome.SIGN_IN);
		button_defenses.setIcon(FontAwesome.CALENDAR);

		button_connexion.addClickListener(
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						String pseudo = identifiant.getValue();
						String password = motDePasse.getValue();

						DAO_Utilisateur user_DB = new DAO_Utilisateur();
						Utilisateur user = user_DB.find(pseudo, password);

						if(user != null) {
							if(user.isEnabled()) {
								currentSession.setAttribute("user", user);
								currentSession.setAttribute("defaultLogin", pseudo);
								Notification.show("Bienvenue "+user.getPrenom(), Notification.TYPE_HUMANIZED_MESSAGE);
								navigateur.navigateTo("Dashboard");
							} else
								Notification.show("Authentification échouée", "Ce compte est désactivé", Notification.Type.WARNING_MESSAGE);
						} else {
							Notification.show("Authentification échouée", "Vérifiez votre identifiant et/ou votre mot de passe", Notification.Type.WARNING_MESSAGE);
						}

					}
				});

		button_defenses.addClickListener(
				new ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						navigateur.navigateTo("Defenses");
					}
				});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.navigateur = event.getNavigator();

		if(currentSession.getAttribute("user") != null)
			navigateur.navigateTo("Dashboard");
		else {
			if(currentSession.getAttribute("defaultLogin") != null)
				identifiant.setValue((String)currentSession.getAttribute("defaultLogin"));
			motDePasse.setValue("");
		}
	}
}
