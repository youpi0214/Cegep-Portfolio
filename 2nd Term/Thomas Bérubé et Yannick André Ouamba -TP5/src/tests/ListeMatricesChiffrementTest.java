package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.ConstructeurException;
import structures.ListeMatricesChiffrement;
import utilitaires.MathUtilitaires;
import utilitaires.MatriceUtilitaires;

public class ListeMatricesChiffrementTest
{
	private ListeMatricesChiffrement mc1, mc2;

	@Before
	public void testListeMatricesChiffrement()
	{
		mc1 = new ListeMatricesChiffrement(4, 14, 3, 28);
		mc2 = new ListeMatricesChiffrement(1, 6, 2, 28);
	}

	@Test
	public void testInvalides()
	{
		try
		{
			new ListeMatricesChiffrement(4, 18, -1, 28);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			new ListeMatricesChiffrement(-3, 18, 3, 28);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			new ListeMatricesChiffrement(3, -18, 3, 28);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			new ListeMatricesChiffrement(14, 4, 3, 28);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			new ListeMatricesChiffrement(4, 14, 3, 0);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			new ListeMatricesChiffrement(1, 5, 3, 28);
		}
		catch (ConstructeurException e)
		{
		}
	}

	@Test
	public void testChoisirMatriceCourante()
	{
		for (int i = 0; i < 50; i++)
		{
			mc1.choisirMatriceCourante();
			mc2.choisirMatriceCourante();
			assertNotNull(mc1.getCopieMatriceCourante());
			assertNotNull(mc2.getCopieMatriceCourante());
		}
	}

	@Test
	public void testChoisirMatriceCouranteInt()
	{
		for (int i = 0; i < mc1.getNombreMatricesCandidates(); i++)
		{
			mc1.choisirMatriceCourante(i);
			assertNotNull(mc1.getCopieMatriceCourante());

			assertEquals(
					MathUtilitaires.PGCD(
							MatriceUtilitaires.getDeterminant(
									mc1.getCopieMatriceCourante()),
							mc1.getCoefDansZ()),
					1);
		}

		for (int i = 0; i < mc2.getNombreMatricesCandidates(); i++)
		{
			mc2.choisirMatriceCourante(i);
			assertNotNull(mc2.getCopieMatriceCourante());

			assertEquals(
					MathUtilitaires.PGCD(
							MatriceUtilitaires.getDeterminant(
									mc2.getCopieMatriceCourante()),
							mc2.getCoefDansZ()),
					1);
		}

		mc1.choisirMatriceCourante(-1);
		assertNull(mc1.getCopieMatriceCourante());
		mc1.choisirMatriceCourante(mc1.getNombreMatricesCandidates() + 1);
		assertNull(mc1.getCopieMatriceCourante());

		mc2.choisirMatriceCourante(-1);
		assertNull(mc2.getCopieMatriceCourante());
		mc2.choisirMatriceCourante(mc2.getNombreMatricesCandidates() + 1);
		assertNull(mc2.getCopieMatriceCourante());
	}

	@Test
	public void testGetCopieMatriceCourante()
	{
		mc1.choisirMatriceCourante(1);
		assertEquals(
				MatriceUtilitaires.toStringMat(mc1.getCopieMatriceCourante()),
				"[4, 5, 6]\n[7, 8, 9]\n[10, 12, 13]\n");

		mc1.choisirMatriceCourante(6);
		assertEquals(
				MatriceUtilitaires.toStringMat(mc1.getCopieMatriceCourante()),
				"[4, 5, 7]\n[9, 10, 11]\n[12, 13, 14]\n");

		mc2.choisirMatriceCourante(1);
		assertEquals(
				MatriceUtilitaires.toStringMat(mc2.getCopieMatriceCourante()),
				"[1, 2]\n[4, 5]\n");

		mc2.choisirMatriceCourante(3);
		assertEquals(
				MatriceUtilitaires.toStringMat(mc2.getCopieMatriceCourante()),
				"[2, 3]\n[5, 6]\n");

	}

	@Test
	public void testGetMatriceCouranteInverseHill()
	{
		mc1.choisirMatriceCourante(6);
		assertEquals(
				MatriceUtilitaires
						.toStringMat(mc1.getMatriceCouranteInverseHill()),
				"[1, 21, 5]\n[26, 0, 3]\n[1, 16, 11]\n");

		mc1.choisirMatriceCourante(5);
		assertEquals(
				MatriceUtilitaires
						.toStringMat(mc1.getMatriceCouranteInverseHill()),
				"[12, 21, 11]\n[6, 7, 8]\n[13, 19, 12]\n");

		mc2.choisirMatriceCourante(1);
		assertEquals(
				MatriceUtilitaires
						.toStringMat(mc2.getMatriceCouranteInverseHill()),
				"[17, 10]\n[20, 9]\n");

		mc2.choisirMatriceCourante(3);
		assertEquals(
				MatriceUtilitaires
						.toStringMat(mc2.getMatriceCouranteInverseHill()),
				"[26, 1]\n[11, 18]\n");
	}

}
