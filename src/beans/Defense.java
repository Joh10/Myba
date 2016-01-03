package beans;

import java.util.Date;

public class Defense {

	@Id
	@Column(name = "id_Def")
	private int id;

	private Utilisateur presidentJury;
	private Stage stage;
	private TFE tfe;

	@Column(name = "dateDefense")
	private Date date;

	@Column(name = "local")
	private String local;
	
	/**
	 * Constructeur (type TFE)
	 * @param	_id				ID (identifiant) de la défense
	 * @param	_presidentJury	Le président du jury qui est assigné à cette défense
	 * @param	_tfe			Le TFE dont est objet de cette défense
	 * @param	_date			La date à laquelle se déroule cette défense
	 * @param	_local			Le nom du local ou est organisée cette défense
	 */
	public Defense(int _id, Utilisateur _presidentJury, TFE _tfe, Date _date, String _local) {
		id = _id;
		presidentJury = _presidentJury;
		stage = null;
		tfe = _tfe;
		date = _date;
		local = _local;
	}
	
	/**
	 * Constructeur (type stage)
	 * @param	_id				ID (identifiant) de la défense
	 * @param	_presidentJury	Le président du jury qui est assigné à cette défense
	 * @param	_stage			Le stage dont est objet de cette défense
	 * @param	_date			La date à laquelle se déroule cette défense
	 * @param	_local			Le nom du local ou est organisée cette défense
	 */
	public Defense(int _id, Utilisateur _presidentJury, Stage _stage, Date _date, String _local) {
		id = _id;
		presidentJury = _presidentJury;
		stage = _stage;
		tfe = null;
		date = _date;
		local = _local;
	}
	
	/**
	 * Pré	:	_date, _local sont initialisés<br>
	 * Post	:	les informations de cette défense sont mises à jour.
	 * @param	_date			La date à laquelle se déroule cette défense
	 * @param	_local			Le local ou se déroule cette défense
	 */
	public void update(Date _date, String _local) {
		date = _date;
		local = _local;
	}
	
	/**
	 * @return	l'identifiant de la défense
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'ID de la défense est modifié par _id
	 * @param	_id	L'identifiant de la défense
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return	le président du jury qui est assigné à la défense.
	 */
	public Utilisateur getPresident() {
		return presidentJury;
	}
	
	/**
	 * @return	le TFE concerné par cette défense, s'il s'agit d'un TFE. Sinon, null
	 */
	public TFE getTFE() {
		return tfe;
	}
	
	/**
	 * @return	le stage concerné par cette défense, s'il s'agit d'un TFE. Sinon, null
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * @return	la date programmée pour la défense.
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return	le nom du local ou est organisée la défense.
	 */
	public String getLocal() {
		return local;
	}
}