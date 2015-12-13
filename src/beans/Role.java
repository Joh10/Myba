package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Role implements Serializable {
	/*
	 * Serializable car dépendant de Utilisateur, qui est sérialisé pour les sessions
	 */
	private static final long serialVersionUID = -1900169871483409885L;
	private int id;
	private String nom;
	private ArrayList<String> permissions;
	
	/**
	 * Constructeur
	 * @param	_id			ID (identifiant) du rôle
	 * @param	_nom		Nom du rôle
	 */
	public Role(int _id, String _nom) {
		id = _id;
		nom = _nom;
		permissions = new ArrayList<String>();
	}
	
	/**
	 * Constructeur (type recherche, sans identifiant)
	 * @param	_nom		Nom du rôle
	 */
	public Role(String _nom) {
		nom = _nom;
	}
	
	/**
	 * @return l'identifiant du rôle
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'identifiant est modifié par _id
	 * @param	_id	L'identifiant du rôle
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return le nom du rôle
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * @param name nom de la permission à vérifier
	 * @return true si la permission name est associée à ce rôle, false sinon
	 */
	public boolean isAllowed(String name) {
		return permissions.contains(name);
	}
	
	/**
	 * @param name nom de la permission à ajouter
	 */
	public void addPermission(String name) {
		permissions.add(name);
	}
}