/**
 * @author Jocelyn -- Cégep Limoilou
 *
 * @version 2013
 */
package utilitaires;

/**
 * Cette classe offre certains outils utiles.
 *
 */
public class Utilitaires
{

	/**
	 * Retourne un nombre entier aléatoire entre min et max [pMin, pMax]
	 *
	 * IMPORTANT: Il faut vérifier si pMin est plus petit que pMax sinon les
	 * inverser.
	 *
	 * @param pMin la borne minimum.
	 * @param pMax la borne maximum.
	 *
	 * @return int, nombre entier aléatoire entre pMin et pMax.
	 */
	public static int alea(int pMin, int pMax)
	{
		int minTemp = Math.min(pMin, pMax);
		int maxTemp = Math.max(pMin, pMax);

		return (int) (Math.random() * (maxTemp - minTemp + 1)) + minTemp;
	}
}
