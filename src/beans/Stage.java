package beans;

import java.util.ArrayList;
import java.util.Date;

public class Stage {
	private int id;
	private Utilisateur owner;
	private Utilisateur superviseur;
	private Utilisateur suiveur;
	private PropositionStage proposition;
	private Date dateDebut;
	private Date dateFin;
	private double pointsTotaux;
	private String commentaires;
	private ArrayList<Technologie> technologies;
	
	/**
	 * Constructeur
	 * @param	_id				ID (identifiant) du stage
	 * @param	_owner			L'étudiant concerné par ce stage
	 * @param	_superviseur	Le professeur promoteur de ce stage
	 * @param	_suiveur		Le maitre de stage assigné à ce stage
	 * @param	_proposition	La proposition de stage liée à ce stage
	 * @param	_dDebut			La date de début du stage
	 * @param	_dFin			La date de fin du stage
	 * @param	_ptsTotaux		Les points totaux attribués à ce stage
	 * @param	_commentaires	Le commentaire professeur effectué par les professeurs
	 * @param	_technologies	La liste des technologies utilisées durant ce stage
	 */
	public Stage(int _id, Utilisateur _owner, Utilisateur _superviseur, Utilisateur _suiveur, PropositionStage _proposition, Date _dDebut, Date _dFin, double _ptsTotaux, String _commentaires, ArrayList<Technologie> _technologies) {
		id = _id;
		owner = _owner;
		superviseur = _superviseur;
		suiveur = _suiveur;
		proposition = _proposition;
		dateDebut = _dDebut;
		dateFin = _dFin;
		pointsTotaux = _ptsTotaux;
		commentaires = _commentaires;
		technologies = _technologies;
	}
	
	/**
	 * Pré	:	_superviseur, _suiveur, _dDebut, _dFin, _commentaires, _technologies sont initialisés<br>
	 * Post	:	les informations de ce stage sont mises à jour.
	 * @param	_superviseur	Le professeur promoteur de ce stage
	 * @param	_suiveur		Le maitre de stage assigné à ce stage
	 * @param	_dDebut			La date de début du stage
	 * @param	_dFin			La date de fin du stage
	 * @param	_commentaires	Le commentaire professeur effectué par les professeurs
	 * @param	_technologies	La liste des technologies utilisées durant ce stage
	 */
	public void update(Utilisateur _superviseur, Utilisateur _suiveur, Date _dDebut, Date _dFin, String _commentaires, ArrayList<Technologie> _technologies) {
		superviseur = _superviseur;
		suiveur = _suiveur;
		dateDebut = _dDebut;
		dateFin = _dFin;
		commentaires = _commentaires;
		technologies = _technologies;
	}
	
	/**
	 * Pré : _ptsTotaux est initialisé<br>
	 * Post : les points totaux de ce stage sont mis à jour
	 * @param _ptsTotaux Les points totaux du stage
	 */
	public void update(double _ptsTotaux) {
		pointsTotaux = _ptsTotaux;
	}
	
	/**
	 * @return	l'identifiant du stage
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'ID du stage est modifié par _id
	 * @param	_id	L'identifiant du stage
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return	l'étudiant concerné par le stage.
	 */
	public Utilisateur getOwner() {
		return owner;
	}
	
	/**
	 * @return	le professeur qui supervise le stage.
	 */
	public Utilisateur getSuperviseur() {
		return superviseur;
	}
	
	/**
	 * @return	l'évaluateur (maitre de stage) concerné par ce stage.
	 */
	public Utilisateur getSuiveur() {
		return suiveur;
	}
	
	/**
	 * @return	la proposition de stage liée à ce stage.
	 */
	public PropositionStage getProposition() {
		return proposition;
	}
	
	/**
	 * @return la date de début effective du stage.
	 */
	public Date getDateDebut() {
		return dateDebut;
	}
	
	/**
	 * @return	la date de fin effective du stage.
	 */
	public Date getDateFin() {
		return dateFin;
	}
	
	/**
	 * @return	les points totaux obtenus à ce stage.
	 */
	public double getPoints() {
		return pointsTotaux;
	}
	
	/**
	 * @return	le commentaire (professeur) sur ce stage.
	 */
	public String getCommentaire() {
		return commentaires;
	}
	
	/**
	 * @return la liste des technologies utilisées durant ce stage.
	 */
	public ArrayList<Technologie> getTechnologies() {
		return technologies;
	}
}