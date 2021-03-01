package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import utilitaires.MathUtilitaires;

public class MathUtilitairesTest
{

	@Test
	public void testFact()
	{
		assertTrue(MathUtilitaires.fact(1) == 1.0);
		assertTrue(MathUtilitaires.fact(2) == 2.0);
		assertTrue(MathUtilitaires.fact(3) == 6.0);
		assertTrue(MathUtilitaires.fact(4) == 24.0);
	} 

	@Test
	public void testModulo()
	{
		assertTrue(MathUtilitaires.modulo(117, 17) == 15);
		assertTrue(MathUtilitaires.modulo(-117, 17) == 2);
		assertTrue(MathUtilitaires.modulo(117, -17) == -2);
		assertTrue(MathUtilitaires.modulo(-117, -17) == -15);

		try
		{
			MathUtilitaires.modulo(117, 0);
			fail("doit lancer une ArithmeticException");
		}
		catch (ArithmeticException e)
		{
			System.out.println(e);
		}
	}

	@Test
	public void testDiviseursDe()
	{
		int[] tabDiv =
		{ 1, 2, 4, 8, 16 };

		for (int i = 0; i < tabDiv.length; i++)
			assertTrue(MathUtilitaires.diviseursDe(16).contains(tabDiv[i]));

		int[] tabDiv2 =
		{ 1, 3, 9, 27, 37, 111, 333, 999 };

		for (int i = 0; i < tabDiv2.length; i++)
			assertTrue(MathUtilitaires.diviseursDe(-999).contains(tabDiv2[i]));

		assertNull(MathUtilitaires.diviseursDe(0));
	}

	@Test
	public void testEstPremier()
	{
		// nombre premier
		assertTrue(MathUtilitaires.estPremier(2));
		assertTrue(MathUtilitaires.estPremier(3));
		assertTrue(MathUtilitaires.estPremier(7));
		assertTrue(MathUtilitaires.estPremier(11));
		assertTrue(MathUtilitaires.estPremier(19));
		assertTrue(MathUtilitaires.estPremier(59));
		assertTrue(MathUtilitaires.estPremier(65537));

		// nombre pas premier
		assertFalse(MathUtilitaires.estPremier(-1));
		assertFalse(MathUtilitaires.estPremier(-55));
		assertFalse(MathUtilitaires.estPremier(0));
		assertFalse(MathUtilitaires.estPremier(4));
	}

	@Test
	public void testXPremier()
	{
		int[] tabPrem =
		{ 2, 3, 5, 7, 11, 13 };

		for (int i = 0; i < tabPrem.length; i++)
			assertTrue(MathUtilitaires.xPremier(16).contains(tabPrem[i]));

		int[] tabPrem2 =
		{ 2, 3, 5, 7, 11, 13, 19, 23, 29, 31, 37, 41, 43, 53, 59 };

		for (int i = 0; i < tabPrem2.length; i++)
			assertTrue(MathUtilitaires.xPremier(59).contains(tabPrem2[i]));

		assertNull(MathUtilitaires.xPremier(0));
		assertNull(MathUtilitaires.xPremier(1));
		assertNull(MathUtilitaires.xPremier(-1));
		assertNull(MathUtilitaires.xPremier(-10));
	}

	@Test
	public void testPGCD()
	{
		assertEquals(MathUtilitaires.PGCD(108, 4896), 36);
		assertEquals(MathUtilitaires.PGCD(999, 18576), 27);
		assertEquals(MathUtilitaires.PGCD(345345, 555), 15);
		assertEquals(MathUtilitaires.PGCD(-108, 4896), 36);
		assertEquals(MathUtilitaires.PGCD(-7, 0), 0);
		assertEquals(MathUtilitaires.PGCD(0, 7), 0);
		assertEquals(MathUtilitaires.PGCD(0, 0), 0);

	}

	@Test
	public void testXPremierEntreEux()
	{
		int[] tabXPrem =
		{ 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25 };

		// valeur debut 1
		for (int i = 0; i < tabXPrem.length; i++)
			assertTrue(MathUtilitaires.xPremierEntreEux(1, 26)
					.contains(tabXPrem[i]));

		// valeur debut negative
		for (int i = 0; i < tabXPrem.length; i++)
			assertTrue(MathUtilitaires.xPremierEntreEux(-99, 26)
					.contains(tabXPrem[i]));

		// valeur debut autre que 1
		int[] tabXPrem2 =
		{ 9, 11, 15, 17, 19, 21, 23, 25 };

		for (int i = 0; i < tabXPrem2.length; i++)
			assertTrue(MathUtilitaires.xPremierEntreEux(9, 26)
					.contains(tabXPrem2[i]));

		// retorne null
		assertNull(MathUtilitaires.xPremierEntreEux(1, -4));

	}

	@Test
	public void testAlea()
	{
		for (int i = 0; i < 15; i++)
		{
			int random = 0;

			random = MathUtilitaires.alea(0, 1);
			assertTrue(random < 2 && random > -1);

			random = MathUtilitaires.alea(-10, 10);
			assertTrue(random < 11 && random > -11);

			random = MathUtilitaires.alea(17, 4);
			assertTrue(random < 18 && random > 3);
		}
	}
}
