package com.example.pi_web;

import com.vaadin.annotations.AutoGenerated;
import com.vaadin.annotations.DesignRoot;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.declarative.Design;

/** 
 * !! DO NOT EDIT THIS FILE !!
 * 
 * This class is generated by Vaadin Designer and will be overwritten.
 * 
 * Please make a subclass with logic and additional interfaces as needed,
 * e.g class LoginView extends LoginDesign implements View { … }
 */
@DesignRoot
@AutoGenerated
@SuppressWarnings("serial")
public class Connexion_design extends AbsoluteLayout {
	protected com.vaadin.ui.TextField identifiant;
	protected com.vaadin.ui.PasswordField motDePasse;
	protected com.vaadin.ui.Button button_connexion;
	protected com.vaadin.ui.Button button_defenses;

	public Connexion_design() {
		Design.read(this);
	}
}