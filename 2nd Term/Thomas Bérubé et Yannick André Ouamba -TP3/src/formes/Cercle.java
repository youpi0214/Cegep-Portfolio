package formes;

import exceptions.FormeException;

/**
 * Classe héritant de Forme, elle permet de créer un cercle et calculer son
 * périmètre ainsi que son aire à l'aide d'un rayon reçu en paramètre
 * 
 * @version 2019
 * @author Yannick André Ouamba
 *
 */
public class Cercle extends Forme
{

	/**
	 * Valeur du rayon du cercle
	 */
	private int rayon = 0;

	/**
	 * Construit un cercle avec un rayon reçu en paramètre et lance une
	 * exception de type FormeException s'il est invalide
	 * 
	 * @param pRayon valeur du rayon(entier)
	 * @throws FormeException Exception lancée lorsque le rayon reçu en
	 *             paramètre est invalide
	 */
	public Cercle(int pRayon) throws FormeException
	{
		// appelle le constructeur de Forme pour afin de créer une forme au nom
		// de 'Cercle'
		super("Cercle");

		if (validerRayon(pRayon))
		{
			rayon = pRayon;
		}
		else
		{
			throw new FormeException();
		}
	}

	/**
	 * Retourne pRayon valeur du rayon(entier)
	 * 
	 * @return rayon
	 */
	public int getRayon()
	{
		return rayon;
	}

	/**
	 * Permet de changer la valeur du rayon pour une nouvelle si celle-ci est
	 * valide
	 * 
	 * @param pRayon valeur du rayon(entier)
	 */
	public void setRayon(int pRayon)
	{
		if (validerRayon(pRayon))
		{
			rayon = pRayon;
		}
	}

	/**
	 * Vérifie si le rayon reçu en paramètre est plus petit ou égale à 30 et
	 * plus grand ou égale à 1, puis retourne vraie le rayon est valide et faux
	 * si le contraire
	 *
	 * @param pRayon valeur du rayon(entier)
	 * @return vraie si le rayon est valide
	 */
	private static boolean validerRayon(int pRayon)
	{
		return pRayon <= Forme.MAX_VAL && pRayon >= Forme.MIN_VAL;
	}

	/**
	 * Calcule l'aire du cercle et l'arondie à l'unité prêt avant de le
	 * retourner
	 * 
	 * @return l'aire du cercle
	 */
	@Override
	public int calculerSurface()
	{
		return (int) Math.round(Math.pow(getRayon(), 2) * Math.PI);
	}

	/**
	 * Calcule le périmètre du cercle et l'arondie à l'unité prêt avant de le
	 * retourner
	 * 
	 * @return le périmètre du cercle
	 */
	@Override
	public int calculerPerimetre()
	{
		return (int) Math.round(2 * Math.PI * getRayon());
	}

	/**
	 * Retourne un résumé de l'objet sous forme de chaîne de caractères avec le
	 * format suivant "nom couleur rayon"
	 * 
	 * @return String la chaîne de caractères représentant l'objet
	 */
	@Override
	public String toString()
	{
		return super.toString() + " " + getRayon();
	}
}
