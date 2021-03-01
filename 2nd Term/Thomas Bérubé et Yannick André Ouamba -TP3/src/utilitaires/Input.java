/**
 * @author Jocelyn -- Cégep Limoilou
 *
 * @version 2013
 */
package utilitaires;

import javax.swing.JOptionPane;

/**
 * Cette classe offre certains outils utiles.
 *
 */
public class Input
{
	/**
	 * Saisir un entier. La saisie est non bloquante.
	 *
	 * @param pQuestion chaîne permettant de pauser une question.
	 *
	 * @return Integer, un objet entier (Integer) permettant de retourner la
	 *         valeur ou "null" si l'utilisateur a décidé d'annuler la saisie.
	 *
	 */
	public static Integer saisirEntier(String pQuestion)
	{
		return Input.saisirEntier(pQuestion, "Saisie d'une valeur...");
	}

	public static Integer saisirEntier(String pQuestion, String sTitre)
	{
		Integer objetEntier = null;
		boolean valide = false;
		String reponse = "";

		// Saisir tant que pas valide
		do
		{
			// Boîte de dialogue de saisie
			reponse = JOptionPane.showInputDialog(null, pQuestion, sTitre,
					JOptionPane.QUESTION_MESSAGE);

			if (reponse != null)
			{
				try
				{
					objetEntier = new Integer(Integer.parseInt(reponse));
					valide = true;
				}
				catch (Exception e)
				{
					valide = false;
				}
			}
		}
		while (!valide && reponse != null);

		return objetEntier;
	}

	/**
	 * Saisir une chaîne de caractères. La saisie est non bloquante.
	 *
	 * @param pQuestion chaîne permettant de pauser une question.
	 *
	 * @return String un objet chaîne de caractères permettant de retourner la
	 *         valeur ou "null" si l'utilisateur a décidé d'annuler la saisie.
	 */
	public static String saisirString(String pQuestion)
	{
		String stringTemp = null;
		boolean valide = false;
		String reponse = "";

		// Saisir tant que pas valide
		do
		{
			// Boîte de dialogue de saisie
			reponse = JOptionPane.showInputDialog(null, pQuestion);

			if (reponse != null)
			{
				stringTemp = reponse;
				valide = true;
			}
		}
		while (!valide && reponse != null);

		return stringTemp;
	}

	/**
	 * Permet d'afficher une question (message) dans une boîte de confirmation
	 * avec les boutons oui et non
	 *
	 * @param pQuestion
	 * @return
	 */
	public static int confirmerMessage(String pQuestion)
	{
		// Boîte de dialogue d'affichage
		return Input.confirmerMessage(pQuestion, "Confirmation");
	}

	public static int confirmerMessage(String pQuestion, String pTitre)
	{
		// Boîte de dialogue d'affichage
		return JOptionPane.showConfirmDialog(null, pQuestion, pTitre,
				JOptionPane.YES_NO_OPTION);
	}
}
