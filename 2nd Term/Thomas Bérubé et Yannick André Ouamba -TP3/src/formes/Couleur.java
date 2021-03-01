package formes;

/**
 * Classe d'énum listant les couleurs valides d'une forme
 * 
 * @version 2019
 * @author Yannick André Ouamba
 */
public enum Couleur
{
	ROUGE("rouge"), VERT("vert"), BLEU("bleu"), JAUNE("jaune"), NOIR(
			"noir"), ORANGE("orange");

	/**
	 * nom de la couleur
	 */
	private String nom;

	/**
	 * Créer un objet Couleur avec la String reçu en paramètre
	 * 
	 * @param pNom couleur reçue en paramètre
	 */
	private Couleur(String pNom)
	{
		setNom(pNom);
	}

	/**
	 * Retourne la couleur de l'objet(Enum)
	 * 
	 * @return nom
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Assigner la couleur de l'objet avec une String reçu en paramètre
	 * 
	 * @param pNom nom de la couleur
	 */
	private void setNom(String pNom)
	{
		nom = pNom;
	}

	/**
	 * Retourne une chaîne de caractères identifiant la couleur de l'objet
	 * (Enum)
	 * 
	 * @return nom la couleur
	 */
	@Override
	public String toString()
	{
		return getNom();
	}
}
