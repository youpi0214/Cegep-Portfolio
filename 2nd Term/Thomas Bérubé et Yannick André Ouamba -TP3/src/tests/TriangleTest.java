package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.FormeException;
import formes.Cercle;
import formes.Couleur;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;
import formes.TypeTriangle;

public class TriangleTest
{
	Triangle t1, t2, t3, t4, t5, t6;

	@Before
	public void testTriangle() throws FormeException
	{
		t1 = new Triangle(Forme.MIN_VAL, Forme.MIN_VAL, Forme.MIN_VAL);
		t2 = new Triangle(Forme.MAX_VAL, Forme.MAX_VAL, Forme.MAX_VAL);
		t3 = new Triangle(7, 7, 12);
		t4 = new Triangle(13, 9, 17);
		t5 = new Triangle(5, 3, 4);
		t6 = new Triangle(10, 8, 6);
	}

	@Test
	public void testTriangleInvalide()
	{
		// côté invalide
		try
		{
			new Triangle(Forme.MIN_VAL - 1, 2, 2);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(2, Forme.MIN_VAL - 1, 2);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(2, 2, Forme.MIN_VAL - 1);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(2, 2, Forme.MIN_VAL - 1);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(Forme.MIN_VAL + 1, 20, 25);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(20, Forme.MIN_VAL + 1, 25);
		}
		catch (FormeException e)
		{

		}

		try
		{
			new Triangle(20, 25, Forme.MIN_VAL + 1);
		}
		catch (FormeException e)
		{

		}

		// triangle plat
		try
		{
			new Triangle(Forme.MAX_VAL, 15, 15);
		}
		catch (FormeException e)
		{

		}
	}

	@Test
	public void testGetCoteA()
	{

		assertTrue(t1.getCoteA() == 1);
		assertTrue(t2.getCoteA() == 30);
		assertTrue(t3.getCoteA() == 7);
		assertTrue(t4.getCoteA() == 13);
		assertTrue(t5.getCoteA() == 5);
		assertTrue(t6.getCoteA() == 10);

	}

	@Test
	public void testGetCoteB()
	{
		assertTrue(t1.getCoteB() == 1);
		assertTrue(t2.getCoteB() == 30);
		assertTrue(t3.getCoteB() == 7);
		assertTrue(t4.getCoteB() == 9);
		assertTrue(t5.getCoteB() == 3);
		assertTrue(t6.getCoteB() == 8);
	}

	@Test
	public void testGetCoteC()
	{
		assertTrue(t1.getCoteC() == 1);
		assertTrue(t2.getCoteC() == 30);
		assertTrue(t3.getCoteC() == 12);
		assertTrue(t4.getCoteC() == 17);
		assertTrue(t5.getCoteC() == 4);
		assertTrue(t6.getCoteC() == 6);
	}

	@Test
	public void testGetType()
	{
		assertEquals(t1.getType(), TypeTriangle.EQUILATERAL);
		assertEquals(t2.getType(), TypeTriangle.EQUILATERAL);
		assertEquals(t3.getType(), TypeTriangle.ISOCELE);
		assertEquals(t4.getType(), TypeTriangle.SCALENE);
		assertEquals(t5.getType(), TypeTriangle.RECTANGLE);
		assertEquals(t6.getType(), TypeTriangle.RECTANGLE);
	}

	@Test
	public void testCalculerSurface()
	{
		assertTrue(t1.calculerSurface() == 0);
		assertTrue(t2.calculerSurface() == 390);
		assertTrue(t3.calculerSurface() == 22);
		assertTrue(t4.calculerSurface() == 58);
		assertTrue(t5.calculerSurface() == 6);
		assertTrue(t6.calculerSurface() == 24);

	}

	@Test
	public void testCalculerPerimetre()
	{
		assertTrue(t1.calculerPerimetre() == 3);
		assertTrue(t2.calculerPerimetre() == 90);
		assertTrue(t3.calculerPerimetre() == 26);
		assertTrue(t4.calculerPerimetre() == 39);
		assertTrue(t5.calculerPerimetre() == 12);
		assertTrue(t6.calculerPerimetre() == 24);
	}

	@Test
	public void testToString()
	{
		assertEquals(t1.toString(), "Triangle rouge équilatéral 1, 1, 1");
		assertEquals(t2.toString(), "Triangle rouge équilatéral 30, 30, 30");
		assertEquals(t3.toString(), "Triangle rouge isocèle 7, 7, 12");
		assertEquals(t4.toString(), "Triangle rouge scalène 9, 13, 17");
		assertEquals(t5.toString(), "Triangle rouge rectangle 3, 4, 5");
		assertEquals(t6.toString(), "Triangle rouge rectangle 6, 8, 10");
	}

	@Test
	public void testEqualsObject() throws FormeException
	{
		assertTrue(t3.equals(new Triangle(7, 7, 12)));

		assertFalse(t1.equals(t2));
		assertFalse(t4.equals(null));
		assertFalse(t5.equals(new Integer(21)));
		t2.setCouleur(Couleur.BLEU);
		t6.setCouleur(Couleur.BLEU);
		assertFalse(t6.equals(t2));

	}

	@Test
	public void testCompareTo()
	{
		try
		{
			// réussites
			assertTrue(t1.compareTo(new Triangle(10, 15, 19)) == 0);
			assertTrue(t2.compareTo(new Triangle(7, 3, 5)) == 0);
			assertTrue(t3.compareTo(new Triangle(11, 13, 16)) == 0);

			// échecs
			t4.setCouleur(Couleur.JAUNE);
			assertTrue(t4.compareTo(new Triangle(5, 7, 10)) < 0);
			assertTrue(t5.compareTo(new Cercle(14)) > 0);
			assertTrue(t6.compareTo(new Rectangle(12, 4)) > 0);
		}
		catch (FormeException e)
		{

		}
	}
}
