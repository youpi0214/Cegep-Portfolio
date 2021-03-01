package tests;

import static org.junit.Assert.*;

import java.awt.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import cartes.Carte;
import cartes.PaquetDeCartes;
import cartes.SorteCartes;
import cartes.ValeurCartes;
import exception.ConstructeurException;

public class PaquetDeCartesTest
{
	private PaquetDeCartes p1, p2, p3;

	@Before
	public void testPaquetDeCartes()
	{
		// paquet classique
		p1 = new PaquetDeCartes();

		// paquet de une carte
		ArrayList<Carte> l1 = new ArrayList<Carte>();
		l1.add(new Carte(ValeurCartes.V_10, SorteCartes.CARREAU));
		p2 = new PaquetDeCartes(l1);

		// paquet aléatoire entre 10 et 99*** cartes
		int aleat = (int) (Math.random() * (100 - 10) + 10);

		ArrayList<Carte> l2 = new ArrayList<Carte>();
		for (int i = 0; i < aleat; i++)
		{
			int random1 = (int) (Math.random() * (4));
			int random2 = (int) (Math.random() * (13));
			l2.add(new Carte(ValeurCartes.values()[random2],
					SorteCartes.values()[random1]));
		}
		p3 = new PaquetDeCartes(l2);
	}

	@Test
	public void Invalide()
	{
		try
		{
			new PaquetDeCartes(null);
			fail("le paquet n'est pas bon");
		}
		catch (ConstructeurException e)
		{
		}
	}

	@Test
	public void testBrasser()
	{
		// avec p1(paquet classique)
		PaquetDeCartes pTemp = new PaquetDeCartes();
		pTemp.brasser();
		int changement = 0;
		for (int i = 1; i <= p1.size(); i++)
		{
			if (!p1.consulterCarte(i).equals(pTemp.consulterCarte(i)))
				changement++;

		}
		System.out.println("le nombre de carte à une position différente est: "
				+ changement + " sur " + p1.size());

		// avec p3(paquet aléatoire)
		// recopier la le paquet
		ArrayList<Carte> l = new ArrayList<Carte>();
		for (int j = 1; j <= p3.size(); j++)
		{
			l.add(p3.consulterCarte(j));
		}
		PaquetDeCartes pTemp2 = new PaquetDeCartes(l);

		// tester le mélanger sur le paquet 3 TODO revoir avec le pointeur vu
		// que 2 cartes similaires pourraient exister
		pTemp2.brasser();
		int changement2 = 0;
		for (int i = 1; i <= p3.size(); i++)
		{
			if (!p3.consulterCarte(i).equals(pTemp2.consulterCarte(i)))
				changement2++;

		}
		System.out.println(
				"le nombre de carte de valeur et sorte différentes à une position différente est: "
						+ changement2 + " sur " + p3.size());
	}

	@Test
	public void testConsulterCarte()
	{
		// p1 dans les borne
		assertEquals(p1.consulterCarte(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.COEUR));

		assertEquals(p1.consulterCarte(52),
				new Carte(ValeurCartes.V_KING, SorteCartes.TREFLE));

		// p1 hors normes
		assertEquals(p1.consulterCarte(0), null);

		assertEquals(p1.consulterCarte(p1.size() + 1), null);

		// p2
		assertEquals(p2.consulterCarte(1),
				new Carte(ValeurCartes.V_10, SorteCartes.CARREAU));

	}

	@Test
	public void testPrendreCarte()
	{
		// p1
		assertEquals(p1.prendreCarte(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.COEUR));

		assertEquals(p1.prendreCarte(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.CARREAU));

		assertEquals(p1.prendreCarte(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.PIQUE));

		assertEquals(p1.prendreCarte(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.TREFLE));

		assertEquals(p1.prendreCarte(1),
				new Carte(ValeurCartes.V_2, SorteCartes.COEUR));

		// p2
		assertEquals(p2.prendreCarte(1),
				new Carte(ValeurCartes.V_10, SorteCartes.CARREAU));

		assertEquals(p2.prendreCarte(1), null);
	}

	@Test
	public void testRetournerToutesLesCartes()
	{
		p1.retournerToutesLesCartes(true);
		for (int i = 1; i <= p1.size(); i++)
		{
			assertTrue(p1.consulterCarte(i).estVisible());
		}

		p2.retournerToutesLesCartes(true);
		for (int i = 1; i <= p2.size(); i++)
		{
			assertTrue(p2.consulterCarte(i).estVisible());
		}
	}

	@Test
	public void testSize()
	{
		assertEquals(p1.size(), 52);

		assertEquals(p2.size(), 1);

		assertTrue(p3.size() <= 99 && p3.size() >= 10);

	}

	@Test
	public void testIsEmpty()
	{
		assertFalse(p1.isEmpty());

		assertFalse(p2.isEmpty());

		p2.prendreCarte(1);
		assertTrue(p2.isEmpty());
	}

}
