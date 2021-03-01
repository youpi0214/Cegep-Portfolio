package tests;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import formes.Cercle;
import formes.Couleur;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;
import formes.VecteurFormes;

public class VecteurFormesTest
{

	private VecteurFormes v1;

	@Before
	public void testVecteurFormes()
	{
		v1 = new VecteurFormes();
		assertTrue(v1.getVecteur().isEmpty());
	}

	@Test
	public void testRemplir()
	{
		Couleur[] couleur = Couleur.values();
		ArrayList<Forme> formes = new ArrayList<>();

		int w = 0;
		// remplir un array de toute les combinaison de formes et
		// couleurs, les formes étant dans cette ordre : Cercle,
		// Rectangle, Triangle (pour chaque type de couleur couleur)
		try
		{
			for (Couleur c : couleur)
			{

				(formes).add(new Cercle(5));
				w = (w != 0) ? w + 1 : w;
				formes.get(w).setCouleur(c);

				(formes).add(new Rectangle(5, 5));
				w += 1;
				formes.get(w).setCouleur(c);

				(formes).add(new Triangle(5, 5, 5));
				w += 1;
				formes.get(w).setCouleur(c);
			}
		}
		catch (Exception e)
		{
		}

		int val = 0;
		// la première boucle modifie le nombre de formes dans mon acteurs et le
		// vide a la fin de chaque itération
		for (int test = 0; test < 5; test++)
		{
			if (test == 0)
				val = 50;
			else if (test == 1)
				val = 36;
			else if (test == 2)
				val = 15;
			else if (test == 3)
				val = 3;

			v1.remplir(val);

			// cette boucle vérifie si les objets de l'array de mon acteur ont
			// été créés de la manière prévue
			for (int i = 0, a = 0; i < v1.getVecteur().size(); i++, a++)
			{
				a = (a == formes.size()) ? 0 : a;
				assertEquals(v1.getVecteur().get(i).toStringCourt(),
						(formes.get(a).toStringCourt()));
			}
			v1 = new VecteurFormes();
		}
	}

	@Test
	public void testInvalides()
	{
		int val = 0;
		for (int i = 0; i < 4; i++)
		{
			if (i == 1)
				val = -1;
			else if (i == 2)
				val = -15;

			VecteurFormes vec = new VecteurFormes();
			vec.remplir(val);
			assertTrue(vec.getVecteur().isEmpty());
		}
	}

	@Test
	public void testGetVecteur()
	{
		// vecteur plein
		for (int i = 17; i < 40; i++)
		{
			v1.remplir(i);

			assertFalse(v1.getVecteur().isEmpty());
			assertTrue(v1.getVecteur().size() > 0);
			for (int a = 0; a < v1.getVecteur().size(); a++)
			{
				assertTrue(v1.getVecteur().get(a) instanceof Forme);
				assertNotNull(v1.getVecteur().get(a));

			}
			v1 = new VecteurFormes();
		}

	}

	@Test
	public void testToString()
	{
		int val = 0;
		for (int i = 0; i < 4; i++)
		{
			if (i == 0)
				val = 40;
			else if (i == 1)
				val = 36;
			else if (i == 2)
				val = 15;
			else if (i == 3)
				val = 3;

			v1.remplir(val);
			VecteurFormes vTemp = new VecteurFormes();
			vTemp.remplir(val);
			assertEquals(v1.toString(), vTemp.toString());
			System.out.println("Vecteur acteur : \n" + v1
					+ "\nVecteurde comparaison\n" + vTemp);
			v1 = new VecteurFormes();
		}
	}

	@Test
	public void testTrier()
	{
		for (int val = 15; val < 50; val++)
		{
			v1.remplir(val);
			v1.trier();
			for (int i = 0; i < v1.getVecteur().size() - 1; i++)
			{
				assertTrue(v1.getVecteur().get(i).toStringCourt().compareTo(
						v1.getVecteur().get(i + 1).toStringCourt()) <= 0);

			}
		}
	}

	@Test
	public void testMelanger()
	{
		DecimalFormat dec = new DecimalFormat("##.##");
		double moyenne = 0.0;
		for (int a = 15; a <= 60; a++)
		{
			v1 = new VecteurFormes();
			v1.remplir(a);
			VecteurFormes vTemp = new VecteurFormes();
			vTemp.remplir(v1.getVecteur().size());
			v1.melanger();
			double changement = 0.0;
			for (int i = 0; i < v1.getVecteur().size(); i++)
			{
				changement = (v1.getVecteur().get(i)
						.compareTo(vTemp.getVecteur().get(i)) != 0)
								? changement + 1
								: changement;

			}

			changement = (changement * 100) / v1.getVecteur().size();
			moyenne += changement;
		}
		moyenne = moyenne / (60 - 15);
		System.out.println("Test de mélanger :\n " + "Efficacité moyenne "
				+ dec.format(moyenne) + " %");

	}
}
