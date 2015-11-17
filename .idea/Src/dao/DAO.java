package dao;

import java.sql.Connection;

public abstract class DAO<T> {
	public Connection connect = ConnectionMySQL.getInstance();
	
	/**
	 * Méthode générique de recherche d'un objet T en base de données par son ID
	 * @param id L'identifiant de l'objet à rechercher
	 * @return l'objet trouvé pour id, null si aucun objet ne correspond à l'id
	 */
	public abstract T find(int id);
	
	/**
	 * Méthode générique de création d'un objet T en base de données
	 * @param obj L'objet à créer en base de données
	 * @return true si l'opération s'est déroulée correctement, false sinon
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Méthode générique de mise à jour d'un objet T en base de données
	 * @param obj L'objet à mettre à jour en base de données
	 * @return true si l'opération s'est déroulée correctement, false sinon
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Méthode générique de suppression d'un objet T en base de données
	 * @param obj L'objet à supprimer de la base de données
	 * @return true si l'opération s'est déroulée correctement, false sinon
	 */
	public abstract boolean delete(T obj);
}