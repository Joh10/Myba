package com.example.pi_web;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("valo")
public class Pi_webUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Pi_webUI.class)
	public static class Servlet extends VaadinServlet {
	}

	
	protected void init(VaadinRequest request) {
		getPage().setTitle("HERS - Gestion des stages et TFE");
		
		Navigator navigator = new Navigator(this, this);
		
		navigator.addView("", new Connexion());
		navigator.addView("Defenses", new PublicDefenses());
		navigator.addView("Dashboard", new Dashboard());
		navigator.addView("Account", new MonCompte());
	}

}