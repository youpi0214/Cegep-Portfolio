package tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cartes.Carte;
import cartes.PaquetDeCartes;
import cartes.Pioche;
import cartes.SorteCartes;
import cartes.ValeurCartes;
import exception.PiocheException;

public class PiocheTest
{
	private Pioche p1, p2, p3;

	@Before
	public void testPioche()
	{
		p1 = new Pioche(new PaquetDeCartes());

		PaquetDeCartes paquetCarte = new PaquetDeCartes();
		paquetCarte.retournerToutesLesCartes(true);
		p2 = new Pioche(paquetCarte);

		p3 = new Pioche(new PaquetDeCartes(new ArrayList<Carte>()));
	}

	@Test
	public void testPiger()
	{
		PaquetDeCartes paquet = new PaquetDeCartes();
		// p2 ou p1 change rien et TODO sans le toString ne marche pas(pas
		// egal??)
		int i = paquet.size();
		while (i > 0)
		{
			assertEquals(p2.piger().toString(),
					paquet.consulterCarte(i).toString());
			i--;
		}

		// pioche vide
		assertEquals(p3.piger(), null);
	}

	@Test
	public void testConsulterDessus()
	{
		PaquetDeCartes paquet = new PaquetDeCartes();

		// carte non-visibles
		assertEquals(p1.consulterDessus(),
				paquet.consulterCarte(52).toStringCarte());

		// carte visibles
		paquet.retournerToutesLesCartes(true);
		int i = paquet.size();
		while (i > 0)
		{
			assertEquals(p2.consulterDessus(),
					paquet.consulterCarte(i).toStringCarte());
			p2.piger();
			i--;
		}

		// pioche vide
		try
		{
			p3.consulterDessus();
			fail("pioche vide");
		}
		catch (PiocheException e)
		{
		}
	}

	@Test
	public void testIsEmpty()
	{
		assertFalse(p1.isEmpty());
		assertFalse(p2.isEmpty());
		assertTrue(p3.isEmpty());
	}

	@Test
	public void testSize()
	{
		assertEquals(p1.size(), 52);
		assertEquals(p2.size(), 52);
		assertEquals(p3.size(), 0);
	}

}
