package exceptions;

/**
 * Classe de l'exception lancée lorsque l'une des formes créée est invalide
 * 
 * @version 2019
 * @author Yannick André Ouamba
 */
public class FormeException extends Exception
{
	/**
	 * Appelle le constructeur de sa classe mère(Exception) pour créer une
	 * exception de type FormeException
	 */
	public FormeException()
	{
		super();
	}

	/**
	 * Appelle le constructeur de sa classe mère(Exception) pour créer une
	 * exception de type FormeException en lui passant un message qui sera
	 * affichée en cas de non traitement de l'exception
	 * 
	 * @param pMessage message à afficher si l'exception n'est pas gérée
	 */
	public FormeException(String pMessage)
	{
		super(pMessage);
	}
}
