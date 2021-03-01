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

public class RectangleTest
{
	private Rectangle rectangle1, rectangle2, rectangle3, rectangle4,
			rectangle5;

	@Before
	public void testRectangleIntInt() throws FormeException
	{
		rectangle1 = new Rectangle(12, 4);
		rectangle2 = new Rectangle(Forme.MIN_VAL + 1, 3);
		rectangle3 = new Rectangle(27, Forme.MAX_VAL - 1);
		rectangle4 = new Rectangle(15, 20);
		rectangle5 = new Rectangle(7, 13);
	}

	@Test
	public void testRectangleInvalide()
	{
		try
		{
			new Rectangle(Forme.MIN_VAL - 1, 15);
			fail("hauteur invalide");
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Rectangle(Forme.MIN_VAL - 2, 15);
			fail("hauteur invalide");
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Rectangle(Forme.MAX_VAL + 1, 15);
			fail("hauteur invalide");
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Rectangle(15, Forme.MIN_VAL - 1);
			fail("largeur invalide");
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Rectangle(15, Forme.MIN_VAL - 2);
			fail("largeur invalide");
		}
		catch (FormeException e)
		{

		}
	}

	@Test
	public void testGetHauteur()
	{
		assertTrue(rectangle1.getHauteur() == 12);
		assertTrue(rectangle2.getHauteur() == 2);
		assertTrue(rectangle3.getHauteur() == 27);
		assertTrue(rectangle4.getHauteur() == 15);
		assertTrue(rectangle5.getHauteur() == 7);
	}

	@Test
	public void testGetLargeur()
	{
		assertTrue(rectangle1.getLargeur() == 4);
		assertTrue(rectangle2.getLargeur() == 3);
		assertTrue(rectangle3.getLargeur() == 29);
		assertTrue(rectangle4.getLargeur() == 20);
		assertTrue(rectangle5.getLargeur() == 13);
	}

	@Test
	public void testGetNom()
	{
		assertEquals(rectangle1.getNom(), "Rectangle");
		assertEquals(rectangle2.getNom(), "Rectangle");
		assertEquals(rectangle3.getNom(), "Rectangle");
		assertEquals(rectangle4.getNom(), "Rectangle");
		assertEquals(rectangle5.getNom(), "Rectangle");
	}

	@Test
	public void testGetCouleur()
	{
		Rectangle[] rectangleTab =
		{ rectangle1, rectangle2, rectangle3, rectangle4, rectangle5 };

		for (int i = 0; i < rectangleTab.length; i++)
		{

			assertEquals(rectangleTab[i].getCouleur(), Forme.COULEUR_DEFAUT);
		}
	}

	@Test
	public void testSetHauteur()
	{
		// réussites
		rectangle1.setHauteur(Forme.MIN_VAL);
		assertTrue(rectangle1.getHauteur() == Forme.MIN_VAL);

		rectangle2.setHauteur(Forme.MIN_VAL + 1);
		assertTrue(rectangle2.getHauteur() == Forme.MIN_VAL + 1);

		rectangle3.setHauteur(Forme.MAX_VAL);
		assertTrue(rectangle3.getHauteur() == Forme.MAX_VAL);

		rectangle4.setHauteur(Forme.MAX_VAL - 1);
		assertTrue(rectangle4.getHauteur() == Forme.MAX_VAL - 1);

		rectangle5.setHauteur(15);
		assertTrue(rectangle5.getHauteur() == 15);

		// échecs
		rectangle1.setHauteur(Forme.MIN_VAL - 1);
		assertFalse(rectangle1.getHauteur() == Forme.MIN_VAL - 1);

		rectangle2.setHauteur(Forme.MIN_VAL - 2);
		assertFalse(rectangle2.getHauteur() == Forme.MIN_VAL - 2);

		rectangle3.setHauteur(Forme.MAX_VAL + 1);
		assertFalse(rectangle3.getHauteur() == Forme.MAX_VAL + 1);

		rectangle4.setHauteur(40);
		assertFalse(rectangle4.getHauteur() == 40);

		for (int i = 0; i < (Forme.MAX_VAL * 3); i++)
		{
			int haut = (int) (Math.random() * (Forme.MAX_VAL - Forme.MIN_VAL))
					+ Forme.MIN_VAL;
			rectangle4.setHauteur(haut);
			assertTrue(rectangle4.getHauteur() == haut);
		}

	}

	@Test
	public void testSetLargeur()
	{
		// réussites
		rectangle1.setLargeur(Forme.MIN_VAL);
		assertTrue(rectangle1.getLargeur() == Forme.MIN_VAL);

		rectangle2.setLargeur(Forme.MIN_VAL + 1);
		assertTrue(rectangle2.getLargeur() == Forme.MIN_VAL + 1);

		rectangle3.setLargeur(Forme.MAX_VAL);
		assertTrue(rectangle3.getLargeur() == Forme.MAX_VAL);

		rectangle4.setLargeur(Forme.MAX_VAL - 1);
		assertTrue(rectangle4.getLargeur() == Forme.MAX_VAL - 1);

		rectangle5.setLargeur(15);
		assertTrue(rectangle5.getLargeur() == 15);

		// échecs
		rectangle1.setLargeur(Forme.MIN_VAL - 1);
		assertFalse(rectangle1.getLargeur() == Forme.MIN_VAL - 1);

		rectangle2.setLargeur(Forme.MIN_VAL - 2);
		assertFalse(rectangle2.getLargeur() == Forme.MIN_VAL - 2);

		rectangle3.setLargeur(Forme.MAX_VAL + 1);
		assertFalse(rectangle3.getLargeur() == Forme.MAX_VAL + 1);

		rectangle4.setLargeur(40);
		assertFalse(rectangle4.getLargeur() == 40);

		for (int i = 0; i < (Forme.MAX_VAL * 3); i++)
		{
			int haut = (int) (Math.random() * (Forme.MAX_VAL - Forme.MIN_VAL))
					+ Forme.MIN_VAL;
			rectangle4.setLargeur(haut);
			assertTrue(rectangle4.getLargeur() == haut);
		}
	}

	@Test
	public void testSetCouleur()
	{

		Couleur[] couleurTab = Couleur.values();

		for (Couleur i : couleurTab)
		{
			rectangle1.setCouleur(i);
			assertEquals(rectangle1.getCouleur(), i);
		}
	}

	@Test
	public void testCalculerSurface()
	{
		assertEquals(rectangle1.calculerSurface(), 48);
		assertEquals(rectangle2.calculerSurface(), 6);
		assertEquals(rectangle3.calculerSurface(), 783);
		assertEquals(rectangle4.calculerSurface(), 300);
		assertEquals(rectangle5.calculerSurface(), 91);
	}

	@Test
	public void testCalculerPerimetre()
	{
		assertEquals(rectangle1.calculerPerimetre(), 32);
		assertEquals(rectangle2.calculerPerimetre(), 10);
		assertEquals(rectangle3.calculerPerimetre(), 112);
		assertEquals(rectangle4.calculerPerimetre(), 70);
		assertEquals(rectangle5.calculerPerimetre(), 40);
	}

	@Test
	public void testEqualsObject() throws FormeException
	{
		assertTrue(rectangle1.equals(new Rectangle(12, 4)));

		assertFalse(rectangle2.equals(null));
		assertFalse(rectangle3.equals(rectangle4));
		assertFalse(rectangle4.equals(new Integer(2)));
		rectangle2.setCouleur(Couleur.BLEU);
		rectangle5.setCouleur(Couleur.BLEU);
		assertFalse(rectangle5.equals(rectangle2));

	}

	@Test
	public void testToString()
	{
		assertTrue(rectangle1.toString().equals("Rectangle rouge 12, 4"));
		assertTrue(rectangle2.toString().equals("Rectangle rouge 2, 3"));
		assertTrue(rectangle3.toString().equals("Rectangle rouge 27, 29"));
		assertTrue(rectangle4.toString().equals("Rectangle rouge 15, 20"));
		assertTrue(rectangle5.toString().equals("Rectangle rouge 7, 13"));
	}

	@Test
	public void testCompareTo()
	{
		try
		{
			// réussites
			assertTrue(rectangle1.compareTo(new Rectangle(10, 15)) == 0);
			assertTrue(rectangle2.compareTo(new Rectangle(7, 3)) == 0);
			assertTrue(rectangle3.compareTo(new Rectangle(11, 13)) == 0);

			// échecs
			rectangle4.setCouleur(Couleur.JAUNE);
			assertTrue(rectangle4.compareTo(new Rectangle(15, 20)) < 0);
			assertTrue(rectangle5.compareTo(new Cercle(14)) > 0);
			assertTrue(rectangle1.compareTo(new Triangle(18, 25, 17)) < 0);
		}
		catch (FormeException e)
		{

		}
	}
}
