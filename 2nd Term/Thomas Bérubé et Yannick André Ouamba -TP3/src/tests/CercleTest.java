package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import exceptions.FormeException;
import formes.Cercle;
import formes.Couleur;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;

public class CercleTest
{
	private Cercle c1, c2, c3, c4, c5, c6;

	@Before
	public void testCercleInt() throws FormeException
	{
		c1 = new Cercle(Forme.MIN_VAL);
		c2 = new Cercle(7);
		c3 = new Cercle(15);
		c4 = new Cercle(Forme.MAX_VAL);
		c5 = new Cercle(21);
		c6 = new Cercle(9);
	}

	@Test
	public void testCercleInvalide()
	{
		try
		{
			new Cercle(Forme.MIN_VAL - 1);
			fail("rayon invalide");
		}

		catch (FormeException e)
		{

		}

		try
		{
			new Cercle(Forme.MIN_VAL - 2);
			fail("rayon invalide");
		}

		catch (FormeException e)
		{

		}

		try
		{
			new Cercle(Forme.MAX_VAL + 1);
			fail("rayon invalide");
		}

		catch (FormeException e)
		{

		}

		try
		{
			new Cercle(Forme.MAX_VAL + 10);
			fail("rayon invalide");
		}

		catch (FormeException e)
		{

		}
	}

	@Test
	public void testGetRayon()
	{
		assertTrue(c1.getRayon() == Forme.MIN_VAL);
		assertTrue(c2.getRayon() == 7);
		assertTrue(c3.getRayon() == 15);
		assertTrue(c4.getRayon() == Forme.MAX_VAL);
		assertTrue(c5.getRayon() == 21);
	}

	@Test
	public void testGetNom()
	{
		assertEquals(c1.getNom(), "Cercle");
		assertEquals(c2.getNom(), "Cercle");
		assertEquals(c3.getNom(), "Cercle");
		assertEquals(c4.getNom(), "Cercle");
		assertEquals(c5.getNom(), "Cercle");
	}

	@Test
	public void testGetCouleur()
	{
		Cercle[] cercleTab =
		{ c1, c2, c3, c4, c5, c6 };

		for (int i = 0; i < cercleTab.length; i++)
		{

			assertEquals(cercleTab[i].getCouleur(), Forme.COULEUR_DEFAUT);
		}
	}

	@Test
	public void testSetCouleur()
	{
		Couleur[] couleurTab = Couleur.values();

		for (Couleur i : couleurTab)
		{
			c1.setCouleur(i);
			assertEquals(c1.getCouleur(), i);
		}
	}

	@Test
	public void testSetRayon()
	{
		// réussites
		c1.setRayon(1);
		assertTrue(c1.getRayon() == 1);

		c2.setRayon(2);
		assertTrue(c2.getRayon() == 2);

		c3.setRayon(30);
		assertTrue(c3.getRayon() == 30);

		c4.setRayon(29);
		assertTrue(c4.getRayon() == 29);

		c5.setRayon(15);
		assertTrue(c5.getRayon() == 15);

		// échecs
		c1.setRayon(0);
		assertFalse(c1.getRayon() == 0);

		c2.setRayon(-1);
		assertFalse(c2.getRayon() == -1);

		c3.setRayon(31);
		assertFalse(c3.getRayon() == 31);

		c4.setRayon(40);
		assertFalse(c4.getRayon() == 40);

		for (int i = 0; i < (Forme.MAX_VAL * 3); i++)
		{
			int haut = (int) (Math.random() * (Forme.MAX_VAL - Forme.MIN_VAL))
					+ Forme.MIN_VAL;
			c4.setRayon(haut);
			assertTrue(c4.getRayon() == haut);
		}
	}

	@Test
	public void testCalculerSurface()
	{
		assertTrue(c1.calculerSurface() == 3);
		assertTrue(c2.calculerSurface() == 154);
		assertTrue(c3.calculerSurface() == 707);
		assertTrue(c4.calculerSurface() == 2827);
		assertTrue(c5.calculerSurface() == 1385);
	}

	@Test
	public void testCalculerPerimetre()
	{
		assertTrue(c1.calculerPerimetre() == 6);
		assertTrue(c2.calculerPerimetre() == 44);
		assertTrue(c3.calculerPerimetre() == 94);
		assertTrue(c4.calculerPerimetre() == 188);
		assertTrue(c5.calculerPerimetre() == 132);
	}

	@Test
	public void testEqualsObject() throws FormeException
	{
		assertTrue(c3.equals(new Cercle(15)));

		assertFalse(c1.equals(c2));
		assertFalse(c4.equals(null));
		assertFalse(c5.equals(new Integer(21)));
		c2.setCouleur(Couleur.BLEU);
		c6.setCouleur(Couleur.BLEU);
		assertFalse(c6.equals(c2));
	}

	@Test
	public void testToString()
	{
		assertTrue(c1.toString().equals("Cercle rouge 1"));
		assertTrue(c2.toString().equals("Cercle rouge 7"));
		assertTrue(c3.toString().equals("Cercle rouge 15"));
		assertTrue(c4.toString().equals("Cercle rouge 30"));
		assertTrue(c5.toString().equals("Cercle rouge 21"));
		assertTrue(c6.toString().equals("Cercle rouge 9"));
	}

	@Test
	public void testCompareTo()
	{
		try
		{
			// réussites
			assertTrue(c1.compareTo(new Cercle(1)) == 0);
			assertTrue(c2.compareTo(new Cercle(7)) == 0);
			assertTrue(c3.compareTo(new Cercle(15)) == 0);

			// échecs
			c4.setCouleur(Couleur.JAUNE);
			assertTrue(c4.compareTo(new Cercle(5)) < 0);
			assertTrue(c5.compareTo(new Triangle(14, 18, 24)) < 0);
			assertTrue(c6.compareTo(new Rectangle(11, 29)) < 0);
		}
		catch (FormeException e)
		{

		}
	}
}
