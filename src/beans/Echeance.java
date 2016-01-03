package beans;

import java.util.ArrayList;
import java.util.Date;

public class Echeance {

	@Id
	@Column(name = "id_Ech")
	private int id;

	private Utilisateur owner;

	@Column(name = "dateCreation")
	private Date dateCreation;

	@Column(name = "dateEcheance")
	private Date dateEcheance;

	private TFE tfe;
	private Stage stage;
	private ArrayList<Utilisateur> users;

	@Column(name = "description")
	private String description;

	@Column(name = "annexe")
	private String annexe;

	/**
	 * Constructeur (type utilisateurs)
	 * @param	_id				ID (identifiant) de l'échéance
	 * @param	_owner			Utilisateur (professeur) qui a créé l'échéance
	 * @param	_dateCreation	Date de création de l'échéance
	 * @param	_dateEcheance	Date à laquelle l'échéance expire
	 * @param	_users			La liste des utilisateurs concernés par cette échéance
	 * @param	_description	Description (détails) manuscits de l'échéance
	 * @param	_annexe		 	Lien(nom) local vers un fichier (facultatif)
	 */
	public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, ArrayList<Utilisateur> _users, String _description, String _annexe) {
		id = _id;
		owner = _owner;
		dateCreation = _dateCreation;
		dateEcheance = _dateEcheance;
		tfe = null;
		stage = null;
		users = _users;
		description = _description;
		annexe = _annexe;
	}
	
	/**
	 * Constructeur (type TFE)
	 * @param	_id				ID (identifiant) de l'échéance
	 * @param	_owner			Utilisateur (professeur) qui a créé l'échéance
	 * @param	_dateCreation	Date de création de l'échéance
	 * @param	_dateEcheance	Date à laquelle l'échéance expire
	 * @param	_tfe			Le TFE pour quel l'échéance est fixée
	 * @param	_description	Description (détails) manuscits de l'échéance
	 * @param	_annexe		 	Lien(nom) local vers un fichier (facultatif)
	 */
	public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, TFE _tfe, String _description, String _annexe) {
		id = _id;
		owner = _owner;
		dateCreation = _dateCreation;
		dateEcheance = _dateEcheance;
		tfe = _tfe;
		stage = null;
		users = null;
		description = _description;
		annexe = _annexe;
	}
	
	/**
	 * Constructeur (type stage)
	 * @param	_id				ID (identifiant) de l'échéance
	 * @param	_owner			Utilisateur (professeur) qui a créé l'échéance
	 * @param	_dateCreation	Date de création de l'échéance
	 * @param	_dateEcheance	Date à laquelle l'échéance expire
	 * @param	_stage			Le stage pour quel l'échéance est fixée
	 * @param	_description	Description (détails) manuscits de l'échéance
	 * @param	_annexe		 	Lien(nom) local vers un fichier (facultatif)
	 */
	public Echeance(int _id, Utilisateur _owner, Date _dateCreation, Date _dateEcheance, Stage _stage, String _description, String _annexe) {
		id = _id;
		owner = _owner;
		dateCreation = _dateCreation;
		dateEcheance = _dateEcheance;
		tfe = null;
		stage = _stage;
		users = null;
		description = _description;
		annexe = _annexe;
	}
	
	/**
	 * Pré	:	_date, _description, _annexe sont initialisés <br>
	 * Post	:	les informations de l'échéance sont mises à jour.
	 * @param	_date			Date à laquelle l'échéance expire
	 * @param	_description	Description (détails) manuscits de l'échéance
	 * @param	_annexe	Note 	Lien(nom) local vers un fichier (facultatif)
	 */
	public void update(Date _date, String _description, String _annexe) {
		dateEcheance = _date;
		description = _description;
		annexe = _annexe;
	}
	
	/**
	 * Pré	:	_user est initialisé <br>
	 * Post	:	la liste des utilisateurs concernés par cette écheance est mise à jour.
	 * @param	_users		La liste des utilisateurs concernés par cette échéance
	 */
	public void update(ArrayList<Utilisateur> _users) {
		users = _users;
	}
	
	/**
	 * @return	l'identifiant de l'échéance
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'ID de l'échéance est modifié par _id
	 * @param	_id	L'identifiant de l'échéance
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return	l'utilisateur (professeur) qui a créé l'échéance.
	 */
	public Utilisateur getOwner() {
		return owner;
	}
	
	/**
	 * @return	la date de création de l'échéance.
	 */
	public Date getDateCreation() {
		return dateCreation;
	}
	
	/**
	 * @return	la date limite de l'échéance.
	 */
	public Date getDateEcheance() {
		return dateEcheance;
	}
	
	/**
	 * @return	la liste des utilisateurs concernés par cette échéance, s'il s'agit d'une échance utilisateur. Sinon, null
	 */
	public ArrayList<Utilisateur> getUsers() {
		return users;
	}
	
	/**
	 * @return	le TFE concerné par cette échéance, s'il s'agit d'un TFE. Sinon, null
	 */
	public TFE getTFE() {
		return tfe;
	}
	
	/**
	 * @return	le stage concerné par cette échéance, s'il s'agit d'un stage. Sinon, null
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * @return	la description (détails) de l'échéance.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return	l'adresse du fichier annexe à l'échéance.
	 */
	public String getAnnexe() {
		return annexe;
	}
}