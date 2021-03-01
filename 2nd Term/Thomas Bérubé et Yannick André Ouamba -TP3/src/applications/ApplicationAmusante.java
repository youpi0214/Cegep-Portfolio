package applications;

import javax.swing.JOptionPane;

import exceptions.FormeException;
import formes.Cercle;
import formes.Rectangle;
import utilitaires.Input;
import utilitaires.Output;
import utilitaires.Utilitaires;

public class ApplicationAmusante
{

	private static final int MIN_VAL = 1;
	private static final int MAX_VAL = 30;

	public static void bienvenu()
	{
		String sTitre = "Exemple d'utilisation potentielle de vos classes Cercle et Rectangle";
		String sMessage = "Utilisation pédagogique pour des enfants du primaire..."
				+ "\n\nVoici une petite application vous permettant de pratiquer le calcul\n"
				+ "du périmétre et de la surface d'un rectangle ou d'un cercle."
				+ "\n\nL'ensemble des informations sont sélectionnées aléatoirement.\n\n";

		Output.afficherMessage(sMessage, sTitre);
	}

	public static Object choisirForme()
	{
		Object obj = null;

		try
		{

			// Choix arbitraire : 1 = cercle et 2 = rectangle
			obj = (Utilitaires.alea(1, 2) == 1)
					? new Cercle(Utilitaires.alea(MIN_VAL, MAX_VAL))
					: new Rectangle(
							Utilitaires.alea(Rectangle.MIN_VAL,
									Rectangle.MAX_VAL),
							Utilitaires.alea(Rectangle.MIN_VAL,
									Rectangle.MAX_VAL));
		}
		catch (FormeException e)
		{
			System.out.println("Ne devrait jamais venir ici!");
		}

		return obj;
	}

	public static void chercherSurfaceCercle(Cercle pCercle)
	{
		Integer valReponse = null;
		int surface = pCercle.calculerSurface();
		String sTitre = "Calculer la surface d'un cercle!!!";
		String sQuestion = "Entrez la valeur de la SURFACE d'un CERCLE dont le rayon est "
				+ pCercle.getRayon() + " ?";
		String sEchec = "Désolé, ce n'est pas la bonne réponse. Reprendre s.v.p!";
		String sOk = "Bravo, la surface d'un cercle de rayon "
				+ pCercle.getRayon() + " est bien " + surface
				+ ".";

		valReponse = Input.saisirEntier(sQuestion, sTitre);
		while ((valReponse != null)
				&& (valReponse.intValue() != surface))
		{
			valReponse = Input.saisirEntier(sEchec + "\n\n" + sQuestion,
					sTitre);
		}

		if ((valReponse != null)
				&& (valReponse.intValue() == surface))
		{
			Output.afficherMessage(sOk, sTitre);
		}
	}

	public static void chercherPerimetreCercle(Cercle pCercle)
	{
		Integer valReponse = null;
		int perimetre = pCercle.calculerPerimetre();
		String sTitre = "Calculer la périmètre d'un cercle!!!";
		String sQuestion = "Entrer la valeur du PÉRIMÈTRE d'un CERCLE dont le rayon est "
				+ pCercle.getRayon() + " ?";
		String sEchec = "Désolé, ce n'est pas la bonne réponse. Reprendre s.v.p!";
		String sOk = "Bravo, le périmètre d'un cercle de rayon "
				+ pCercle.getRayon() + " est bien "
				+ perimetre + ".";

		valReponse = Input.saisirEntier(sQuestion, sTitre);
		while ((valReponse != null)
				&& (valReponse.intValue() != perimetre))
		{
			valReponse = Input.saisirEntier(sEchec + "\n\n" + sQuestion,
					sTitre);
		}

		if ((valReponse != null)
				&& (valReponse.intValue() == perimetre))
		{
			Output.afficherMessage(sOk, sTitre);
		}
	}

	public static void chercherSurfaceRectangle(Rectangle pRec)
	{
		Integer valReponse = null;
		int surface = pRec.calculerSurface();
		String sTitre = "Calculer la surface d'un rectangle!!!";
		String sQuestion = "Entrer la valeur de la SURFACE d'un RECTANGLE dont la hauteur est "
				+ pRec.getHauteur() + " et la largeur est " + pRec.getLargeur()
				+ " ?";
		String sEchec = "Désolé, ce n'est pas la bonne réponse. Reprendre s.v.p!";
		String sOk = "Bravo, la surface d'un rectangle de hauteur "
				+ pRec.getHauteur() + " et de largeur " + pRec.getLargeur()
				+ " est bien " + surface + ".";

		valReponse = Input.saisirEntier(sQuestion, sTitre);
		while ((valReponse != null)
				&& (valReponse.intValue() != surface))
		{
			valReponse = Input.saisirEntier(sEchec + "\n\n" + sQuestion,
					sTitre);
		}

		if ((valReponse != null)
				&& (valReponse.intValue() == surface))
		{
			Output.afficherMessage(sOk, sTitre);
		}
	}

	public static void checherPerimetreRectangle(Rectangle pRec)
	{
		Integer valReponse = null;
		int perimetre = pRec.calculerPerimetre();
		String sTitre = "Calculer le périmètre d'un rectangle!!!";
		String sQuestion = "Entrer la valeur du PÉRIMÈTRE d'un RECTANGLE dont la hauteur est "
				+ pRec.getHauteur() + " et la largeur est " + pRec.getLargeur()
				+ " ?";
		String sEchec = "Désolé, ce n'est pas la bonne réponse. Reprendre s.v.p!";
		String sOk = "Bravo, le périmètre d'un rectangle de hauteur "
				+ pRec.getHauteur() + " et de largeur " + pRec.getLargeur()
				+ " est bien " + perimetre + ".";

		valReponse = Input.saisirEntier(sQuestion, sTitre);
		while ((valReponse != null)
				&& (valReponse.intValue() != perimetre))
		{
			valReponse = Input.saisirEntier(sEchec + "\n\n" + sQuestion ,
					sTitre);
		}

		if ((valReponse != null)
				&& (valReponse.intValue() == perimetre))
		{
			Output.afficherMessage(sOk, sTitre);
		}
	}

	public static void main(String[] args)
	{
		Object formeAlea = null;
		String sTitre = "Essayer encore!";
		String sQuestion = "Voulez-vous faire un autre calcul ?";

		bienvenu();

		do
		{
			formeAlea = choisirForme();
			// Choix arbitraire : 1 = surface et 2 = périmètre
			if (Utilitaires.alea(1, 2) == 1)
			{
				// Surface selon la forme
				if (formeAlea instanceof Cercle)
				{
					chercherSurfaceCercle((Cercle) formeAlea);
				}
				else
				{
					chercherSurfaceRectangle((Rectangle) formeAlea);
				}
			}
			else
			// Périmètre selon la forme
			{
				if (formeAlea instanceof Cercle)
				{
					chercherPerimetreCercle((Cercle) formeAlea);
				}
				else
				{
					checherPerimetreRectangle((Rectangle) formeAlea);
				}
			}
			// Permet de sortir de la boucle de calcul
		}
		while (JOptionPane.YES_OPTION == Input.confirmerMessage(sQuestion,
				sTitre));
	}
}
