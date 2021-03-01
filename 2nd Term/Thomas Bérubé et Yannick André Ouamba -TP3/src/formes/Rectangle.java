package formes;

import exceptions.FormeException;

/**
 * Classe héritant de Forme, elle permet de créer un rectangle et calculer son
 * périmètre ainsi que son aire à l'aide d'un d'une hauteur et d'une largeur
 * reçues en paramètre
 * 
 * @version 2019
 * @author Yannick André Ouamba
 *
 */
public class Rectangle extends Forme
{
	/**
	 * Hauteur du rectangle
	 */
	private int hauteur = 0;

	/**
	 * largeur du rectangle
	 */
	private int largeur = 0;

	/**
	 * Construit un rectangle avec une hauteur et une largeur reçu en paramètre
	 * et lance une exception de type FormeException si l'un des deux paramètres
	 * est invalide
	 * 
	 * @param pHauteur valeur de la hauteur(entier)
	 * @param pLargeur pLargeur valeur de la largeur(entier)
	 * @throws FormeException Exception lancée lorsque la hauteur ou la largeur
	 *             reçu en paramètre est invalide
	 */
	public Rectangle(int pHauteur, int pLargeur) throws FormeException
	{
		// appelle le constructeur de Forme pour afin de créer une forme au nom
		// de 'Rectangle'
		super("Rectangle");

		if (validerHauteur(pHauteur) && validerLargeur(pLargeur))
		{
			hauteur = pHauteur;
			largeur = pLargeur;
		}
		else
		{
			throw new FormeException();

		}
	}

	/**
	 * Retourne la hauteur du rectangle
	 * 
	 * @return la hauteur
	 */
	public int getHauteur()
	{
		return hauteur;
	}

	/**
	 * Retourne la largeur du rectangle
	 * 
	 * @return la largeur
	 */
	public int getLargeur()
	{
		return largeur;
	}

	/**
	 * Permet de changer la valeur de la hauteur pour une nouvelle si celle-ci
	 * est valide
	 * 
	 * @param pHauteur valeur de la hauteur(entier)
	 */
	public void setHauteur(int pHauteur)
	{
		if (validerHauteur(pHauteur))
		{
			hauteur = pHauteur;
		}
	}

	/**
	 * Permet de changer la valeur de la largeur pour une nouvelle si celle-ci
	 * est valide
	 * 
	 * @param pLargeur valeur de la largeur(entier)
	 */
	public void setLargeur(int pLargeur)
	{
		if (validerLargeur(pLargeur))
		{
			largeur = pLargeur;
		}
	}

	/**
	 * Vérifie si la hauteur reçue en paramètre est plus petit ou égale à 30 et
	 * plus grand ou égale à 1, puis retourne vraie la hauteur est valide et
	 * faux si le contraire
	 * 
	 * @param pHauteur valeur de la hauteur(entier)
	 * @return vrai si la hauteur est valide
	 */
	private static boolean validerHauteur(int pHauteur)
	{
		return pHauteur <= Forme.MAX_VAL && pHauteur >= Forme.MIN_VAL;
	}

	/**
	 * Vérifie si la largeur reçue en paramètre est plus petit ou égale à 30 et
	 * plus grand ou égale à 1, puis retourne vraie la largeur est valide et
	 * faux si le contraire
	 * 
	 * @param pLargeur valeur de la largeur(entier)
	 * @return vrai si la largeur est valide
	 */
	private static boolean validerLargeur(int pLargeur)
	{
		return pLargeur <= Forme.MAX_VAL && pLargeur >= Forme.MIN_VAL;
	}

	/**
	 * Calcule l'aire du rectangle et le retourne
	 * 
	 * @return l'aire du rectangle
	 */
	@Override
	public int calculerSurface()
	{
		return getHauteur() * getLargeur();
	}

	/**
	 * Calcule le périmètre du rectangle et le retourne
	 * 
	 * @return le périmètre du rectangle
	 */
	@Override
	public int calculerPerimetre()
	{
		return (getHauteur() + getLargeur()) * 2;
	}

	/**
	 * Retourne un résumé de l'objet sous forme de chaîne de caractères avec le
	 * format suivant "nom couleur hauteur, largeur"
	 * 
	 * @return String la chaîne de caractères représentant l'objet
	 */
	@Override
	public String toString()
	{
		return super.toString() + " " + getHauteur() + ", " + getLargeur();
	}
}
