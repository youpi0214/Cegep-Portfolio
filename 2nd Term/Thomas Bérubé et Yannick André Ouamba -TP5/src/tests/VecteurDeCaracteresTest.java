package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.ConstructeurException;
import structures.VecteurDeCaracteres;

public class VecteurDeCaracteresTest
{
	VecteurDeCaracteres v1, v2;

	@Before
	public void testVecteurDeCaracteres()
	{
		v1 = new VecteurDeCaracteres();

		char[] tab =
		{ 'a', 'e', 'u', 'i', 'o', 'y', '+', '=' };
		v2 = new VecteurDeCaracteres(tab);
	}

	@Test
	public void testVecteurDeCaracteresInvalide()
	{
		try
		{
			new VecteurDeCaracteres(null);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{
			char[] tab =
			{};
			new VecteurDeCaracteres(tab);
		}
		catch (ConstructeurException e)
		{
		}
	}

	@Test
	public void testGetCaractere()
	{
		char[] tab_char =
		{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ',
				'-' };

		for (int i = 0; i < tab_char.length; i++)
			assertEquals(v1.getCaractere(0), tab_char[0]);

		try
		{
			// v2 a une taille de 8, de l'index 0 à 7
			v2.getCaractere(-1);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
		}

		try
		{
			// v2 a une taille de 8, de l'index 0 à 7
			v2.getCaractere(-1);
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
		}
	}

	@Test
	public void testGetIndice()
	{
		char[] tab_char =
		{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ',
				'-' };
		for (int i = 0; i < tab_char.length; i++)
		{
			assertTrue(v1.getIndice(tab_char[i]) == i);
		}

		for (int i = 0; i < tab_char.length; i++)
		{
			assertTrue(v1.getIndice('+') == -1);
		}

		char[] tab_char2 =
		{ 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
				'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ',
				'-' };
		for (int i = 0; i < tab_char.length; i++)
		{
			assertTrue(v1.getIndice(tab_char2[i]) == i);
		}

	}

	@Test
	public void testGetTaille()
	{
		assertTrue(v1.getTaille() == 28);
		assertTrue(v2.getTaille() == 8);
	}

	@Test
	public void testToString()
	{
		assertEquals(v1.toString(),
				"Table de correspondance = [A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z,  , -]");

		assertEquals(v2.toString(),
				"Table de correspondance = [A, E, U, I, O, Y, +, =]");
	}

}
