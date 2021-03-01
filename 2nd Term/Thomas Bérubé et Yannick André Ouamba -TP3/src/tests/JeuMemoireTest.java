package tests;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import formes.Forme;
import formes.VecteurFormes;
import jeu.JeuMemoire;

public class JeuMemoireTest
{
	private JeuMemoire JM = null;

	@Before
	public void testJeuMemoire()
	{
		JM = new JeuMemoire();
	}

	@Test
	public void testToString()
	{
		// test visuel
		// Ce test s'effectue en partant le programme avec la Classe
		// ControlleurMemoire, le programme ouvre une fenêtre de jeu où est
		// graphiquement affiché la grille et affiche également
		// la grille dans la console mais en chaînes de caractères

	}

	@Test
	public void testGetNomForme()
	{
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				assertEquals(JM.getNomForme(i, j),
						(JM.getGrille()[i][j].getNom()
								+ JM.getGrille()[i][j].getCouleur()));
			}
		}
	}

	@Test
	public void testJouerOrdi()
	{
		ArrayList<Point> aP = null;

		// pour chaque niveau
		for (int i = 1; i <= JeuMemoire.NIVEAU_MAX; i++)
		{
			aP = (ArrayList<Point>) JM.jouerOrdi().clone();
			assertEquals(aP.size(), i + 2);

			// Vérifie que 2 formes ne soient pas pareil
			for (int j = 0; j < aP.size() && j != i - 1; j++)
			{
				assertFalse(aP.get(i - 1).getX() == aP.get(j).getX()
						&& aP.get(i - 1).getY() == aP.get(j).getY());
			}

			JM.setNiveauPlusUn();
		}
	}

	@Test
	public void testJouerHumain()
	{
		ArrayList<Point> aP = null;
		// pour chaque niveau
		for (int j = 1; j <= JeuMemoire.NIVEAU_MAX; j++)
		{
			aP = (ArrayList<Point>) JM.jouerOrdi().clone();

			// pour chaque postion du vecteur
			for (int i = 0; i < aP.size(); i++)
			{
				assertTrue(JM.jouerHumain((int) aP.get(i).getX(),
						(int) aP.get(i).getY()));
			}
			JM.setNiveauPlusUn();
		}

	}

	@Test
	public void testGetNiveau()
	{
		for (int i = 1; i <= JeuMemoire.NIVEAU_MAX; i++)
		{
			assertTrue(JM.getNiveau() == i);
			JM.setNiveauPlusUn();
		}
	}

	@Test
	public void testGetVecteur()
	{
		assertTrue(JM.getVecteur() instanceof VecteurFormes);

		assertEquals(JM.getVecteur().getVecteur().size(),
				JeuMemoire.NBR_ELEMENTS_GRILLE);
	}

	@Test
	public void testGetGrille()
	{
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				assertTrue(JM.getGrille()[i][j] instanceof Forme);
			}
		}
	}

	@Test
	public void testSetNiveauPlusUn()
	{
		for (int i = 1; i <= JeuMemoire.NIVEAU_MAX + 4; i++)
		{
			if (i <= JeuMemoire.NIVEAU_MAX)
				assertTrue(JM.getNiveau() == i);
			else
				assertTrue(JM.getNiveau() == JeuMemoire.NIVEAU_MAX);

			JM.setNiveauPlusUn();
		}

	}

}
