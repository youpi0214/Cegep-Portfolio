package formes;

/**
 * Classe d'énum listant les types valides d'un triangle
 * 
 * @version 2019
 * @author Yannick André Ouamba
 */
public enum TypeTriangle
{
	ISOCELE("isocèle"), EQUILATERAL("équilatéral"), RECTANGLE(
			"rectangle"), SCALENE("scalène");

	/**
	 * type de triangle
	 */
	private String type;

	/**
	 * Créer un objet TypeTriangle avec la String reçu en paramètre
	 * 
	 * @param pType nom de type
	 */
	TypeTriangle(String pType)
	{
		setType(pType);
	}

	/**
	 * Retourne le type de triangle
	 * 
	 * @return type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Assigner le type de l'objet avec une String reçu en paramètre
	 * 
	 * @param pType nom du type reçu en paramètre
	 */
	private void setType(String pType)
	{
		type = pType;
	}

	/**
	 * Retourne une chaîne de caractères identifiant le type de triangle
	 * 
	 * @return type le type de triangle
	 */
	public String toString()
	{
		return getType();
	}
}
