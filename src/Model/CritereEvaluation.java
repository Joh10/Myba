package Model;

import java.io.Serializable;

public class CritereEvaluation implements Serializable {
	/*
	 * Serializable car utilisé dans des combobox
	 */
	private static final long serialVersionUID = 2079767621417345684L;
	private int id;
	private String nom;
	private String type;
	private int noteMax;
	
	/**
	 * Constructeur
	 * @param	_id			ID (identifiant) du critère d'évaluation
	 * @param	_nom		Nom du critère d'évaluation
	 * @param	_type		Type du critère (enum "tfe", "stage" or "defense")
	 * @param	_noteMax	Note maximale de ce critère d'évaluation
	 * @exception IllegalArgumentException si le type n'est ni un stage, ni un tfe, ni une défense.
	 */
	public CritereEvaluation(int _id, String _nom, String _type, int _noteMax) {
		if(!_type.equals("tfe") && !_type.equals("stage") && !_type.equals("defense"))
			throw new IllegalArgumentException();
		id = _id;
		nom = _nom;
		type = _type;
		noteMax = _noteMax;
	}
	
	/**
	 * Pré	:	_nom, _type, _noteMax sont initialisés <br>
	 * Post	:	les informations du critère d'évaluation sont mises à jour.
	 * @param	_nom		Nom du critère d'évaluation
	 * @param	_type		Type du critère (enum "tfe", "stage" or "defense")
	 * @param	_noteMax	Note maximale de ce critère d'évaluation
	 * @exception IllegalArgumentException si le type n'est ni un stage, ni un tfe, ni une défense.
	 */
	public void update(String _nom, String _type, int _noteMax) {
		if(!_type.equals("tfe") && !_type.equals("stage") && !_type.equals("defense"))
			throw new IllegalArgumentException();
		nom = _nom;
		type = _type;
		noteMax = _noteMax;
	}
	
	/**
	 * @return	l'identifiant du critère d'évaluation
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return	le nom du critère d'évaluation
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Pré	:	_id est initialisé<br>
	 * Post :	l'ID du critère d'évaluation est modifié par _id
	 * @param	_id	L'identifiant du critère d'évaluation
	 */
	public void setId(int _id) {
		id = _id;
	}
	
	/**
	 * @return	le type du critère d'évaluation.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return la note maximale du critère d'évaluation.
	 */
	public int getNote() {
		return noteMax;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return nom+" ("+noteMax+")";
	}
}