package Model;

public class PropositionStage {
	private int id;
	private Utilisateur owner;
	private LieuStage lieu;
	private boolean valide;
	private String sujet;
	private String annexe;
	
	/**
	 * Constructeur
	 * @param	_owner		L'utilisateur (étudiant/professeur) qui a effecué cette proposition de stage
	 * @param	_lieu		L'entreprise concernée par cette proposition
	 * @param	_valide		Si cette proposition de stage est validée ou non (transformée en stage)
	 * @param	_sujet		Le sujet (temporaire) de la proposition de stage
	 * @param	_annexe		L'adresse interne du fichier annexe
	 */
	public PropositionStage(int _id, Utilisateur _owner, LieuStage _lieu, boolean _valide, String _sujet, String _annexe) {
		id = _id;
		owner = _owner;
		lieu = _lieu;
		valide = _valide;
		sujet = _sujet;
		annexe = _annexe;
	}
	
	/**
	 * Pré	:	_sujet, _annexe sont initialisés<br>
	 * Post	:	les informations de la proposition de stage sont mises à jour.
	 * @param	_sujet		Le sujet (temporaire) de la proposition de stage
	 * @param	_annexe		L'adresse interne du fichier annexe
	 */
	public void update(String _sujet, String _annexe) {
		sujet = _sujet;
		annexe = _annexe;
	}
	
	/**
	 * Pré	:	_valide est initialisé<br>
	 * Post	:	le statut "validé" de la proposition de stage est mis à jour.
	 * @param	_valide	Si cette proposition de stage est validée ou non (transformée en stage)
	 */
	public void update(boolean _valide) {
		valide = _valide;
	}
	
	/**
	 * @return	l'identifiant de la proposition de stage
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'ID de la proposition de stage est modifié par _id
	 * @param	_id	L'identifiant de la proposition de stage
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return l'étudiant/professeur qui a posté la proposition de stage.
	 */
	public Utilisateur getOwner() {
		return owner;
	}
	
	/**
	 * @return	le lieu de stage (entreprise) de la proposition.
	 */
	public LieuStage getPlace() {
		return lieu;
	}
	
	/**
	 * @return	true si la proposition de stage est validée, false sinon.
	 */
	public boolean isValid() {
		return valide;
	}
	
	/**
	 * @return	le sujet de la proposition de stage.
	 */
	public String getSubject() {
		return sujet;
	}
	
	/**
	 * @return	le lien vers l'annexe de la proposition.
	 */
	public String getAnnexe() {
		return annexe;
	}
}