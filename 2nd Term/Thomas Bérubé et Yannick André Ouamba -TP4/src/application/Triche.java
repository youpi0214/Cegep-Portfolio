package application;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cartes.Carte;
import cartes.ValeurCartes;

/**
 * Classe utilitaire qui permet à un joueur de tricher lorsque la partie est
 * terminée.
 *
 * @author Vos noms
 *
 */
public class Triche
{

	/**
	 * Informe l'utilisateur qu'il n'a plus de possibilités. On permet alors à
	 * l'utilisateur de tricher un peu pour se déprendre.
	 *
	 * @param parent la classe de jeu à modifier.
	 */
	public static void finiOuTriche(AcesUpSolitaire parent)
	{
		int choixColonne = 0;

		Object[] options =
		{ "Mon dieu NON !!!!!!", "monter 1", "monter 2", "monter 3", "monter 4",
				"descendre 1", "descendre 2", "descendre 3", "descendre 4" };
		choixColonne = JOptionPane.showOptionDialog(parent,
				"Désolé, la partie est terminée, il n'est plus possible "
						+ "de jouer un autre coup \n... Voulez-vous tricher ?\n"
						+ " (le jeu va descendre ou monter la carte la plus forte dans "
						+ "la colonne de votre choix)",
				"Fin de partie !", JOptionPane.WARNING_MESSAGE, 0, null,
				options, options[0]);

		if (choixColonne > 0)
		{
			// TODO Attention laisse passer les null
			parent.addTricher();
			if (choixColonne < 5)
			{
				tricheMonter(parent.getColonneCartes(choixColonne - 1));
				parent.dessinerListeCartes(choixColonne - 1);
			}
			else
			{
				tricheDescendre(parent.getColonneCartes(choixColonne - 5));
				parent.dessinerListeCartes(choixColonne - 5);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(parent,
					"On commence une nouvelle partie");
			parent.initPartie();
		}
	}

	/**
	 * Cette méthode prend la carte la plus forte de la colonne et elle la monte
	 * en haut de la colonne. En cas d'égalité (pour 2 As), la méthode considère
	 * la carte la plus haute dans la colonne. Ça permet entre autre de changer
	 * l'As de place. Il faut ici considérer l'As comme étant la carte la plus
	 * forte.
	 *
	 * @param colonneCartes la colonne à modifier.
	 *
	 */
	// TODO Complétez le code de la méthode : tricheMonter et revoir pour
	// comparer la grandeur pour que sa ne change pas tout et changer le permut
	private static void tricheMonter(List<Carte> colonneCartes)
	{
		if (colonneCartes != null && !colonneCartes.isEmpty())
		{
			int position = colonneCartes.size() - 1;
			boolean passage = false;

			// si la carte en haut n'est pas un as
			if ((((Carte) colonneCartes.get(position))
					.getValeur() != ValeurCartes.V_AS))
			{
				// pour chaque carte du paquet
				for (int i = colonneCartes.size() - 1; i > 0 && !passage; i--)
				{
					// si la carte en dessous du i est un as
					if (((Carte) colonneCartes.get(i - 1)).getValeur()
							.equals(ValeurCartes.V_AS))
					{
						position = i - 1;
						passage = true;
					}
					// si la carte en bas du i est plus grande que la position
					else if (((Carte) colonneCartes.get(position)).getValeur()
							.compareTo(
									colonneCartes.get(i - 1).getValeur()) < 0)
					{
						position = i - 1;
					}
				}
				Carte temp = (Carte) ((ArrayList<Carte>) colonneCartes)
						.remove(position);
				((ArrayList<Carte>) colonneCartes).add(temp);
			}
		}
	}

	/**
	 * Cette méthode prend la carte la plus forte de la colonne et elle la
	 * descend en bas de la colonne. En cas d'égalité (pour 2 As), la méthode
	 * considère la carte la plus basse dans la colonne. Ça permet entre autre
	 * de changer l'As de place. Il faut ici considérer l'As comme étant la
	 * carte la plus forte.
	 *
	 * @param colonneCartes la colonne à modifier.
	 *
	 */
	// TODO Complétez le code de la méthode : tricheDescendre et changer le
	// permut
	private static void tricheDescendre(List<Carte> colonneCartes)
	{
		if (colonneCartes != null && !colonneCartes.isEmpty())
		{
			int position = 0;
			boolean passage = false;

			// si la carte en bas n'est pas un as
			if ((((Carte) colonneCartes.get(0))
					.getValeur() != ValeurCartes.V_AS))
			{
				// pour chaque carte du paquet
				for (int i = 0; i < colonneCartes.size() - 1 && !passage; i++)
				{
					// si la carte au dessus du i est un as
					if (((Carte) colonneCartes.get(i + 1)).getValeur()
							.equals(ValeurCartes.V_AS))
					{
						position = i + 1;
						passage = true;
					}
					// si la carte en haut du i est plus grande que la position
					else if (((Carte) colonneCartes.get(position)).getValeur()
							.compareTo(
									colonneCartes.get(i + 1).getValeur()) < 0)
					{
						position = i + 1;
					}
				}
				Carte temp = (Carte) ((ArrayList<Carte>) colonneCartes)
						.remove(position);
				((ArrayList<Carte>) colonneCartes).add(0, temp);
			}

		}

	}

}
