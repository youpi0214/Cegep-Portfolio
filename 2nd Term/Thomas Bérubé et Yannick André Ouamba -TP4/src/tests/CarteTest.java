package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cartes.Carte;
import cartes.SorteCartes;
import cartes.ValeurCartes;
import exception.ConstructeurException;

public class CarteTest
{
	private List<Carte> tab = null;

	@Before
	public void testCarte()
	{
		tab = new ArrayList<Carte>();
		for (ValeurCartes v : ValeurCartes.values())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				tab.add(new Carte(v, s));
			}
		}
	}

	@Test
	public void Invalides()
	{
		try
		{
			new Carte(null, SorteCartes.CARREAU);
			fail("Carte invalide");
		}
		catch (ConstructeurException e)
		{

		}

		try
		{
			new Carte(null, null);
			fail("Carte invalide");
		}
		catch (ConstructeurException e)
		{

		}

		try
		{
			new Carte(ValeurCartes.V_10, null);
			fail("Carte invalide");
		}
		catch (ConstructeurException e)
		{

		}

	}

	@Test
	public void testGetValeur()
	{
		int passage = 0;
		for (ValeurCartes v : ValeurCartes.values())
		{
			do
			{
				assertEquals(tab.get(passage).getValeur(), v);
				passage++;
			}
			while (passage % SorteCartes.values().length != 0);
		}

	}

	@Test
	public void testGetValeurSymbole()
	{
		int passage = 0;
		for (ValeurCartes v : ValeurCartes.values())
		{
			do
			{
				assertEquals(tab.get(passage).getValeurSymbole(),
						v.getSymbole());
				passage++;
			}
			while (passage % SorteCartes.values().length != 0);
		}
	}

	@Test
	public void testGetSorte()
	{
		int passage = 0;
		while (passage != tab.size())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				assertEquals(tab.get(passage).getSorte(), s);
				passage++;
			}
		}

	}

	@Test
	public void testGetSorteSymbole()
	{
		int passage = 0;
		while (passage != tab.size())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				assertEquals(tab.get(passage).getSorteSymbole(),
						s.getSymbole());
				passage++;
			}
		}
	}

	@Test
	public void testEstVisible()
	{
		for (Carte c : tab)
		{
			assertFalse(c.estVisible());
		}
	}

	@Test
	public void testSetVisible()
	{
		for (Carte c : tab)
		{
			boolean vis = !c.estVisible();
			c.setVisible(vis);
			assertEquals(c.estVisible(), vis);
		}
	}

	@Test
	public void testToStringCarte()
	{
		char image = 9786;
		String dos = new String();
		dos += image;
		// pas visible TODO revoir
		for (Carte c : tab)
		{
			assertEquals(c.toStringCarte(), dos);
		}

		// visible
		for (Carte c : tab)
		{
			c.setVisible(true);
		}

		int i = 0;
		for (ValeurCartes v : ValeurCartes.values())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				assertEquals(tab.get(i).toStringCarte(),
						v.getSymbole() + s.getSymbole());
				i++;
			}
		}
	}

	@Test
	public void testEqualsObject()
	{
		// for (Carte c : tab)
		// {
		// int i = 0;
		// for (int j = 0; j < tab.size(); j++)
		// {
		// while(i != j)
		// assertNotEquals(c, tab.get(j));
		// }
		// i++;
		// }

		// pareil
		assertEquals(tab.get(0),
				new Carte(ValeurCartes.V_AS, SorteCartes.COEUR));
		assertEquals(tab.get(5),
				new Carte(ValeurCartes.V_2, SorteCartes.CARREAU));
		assertEquals(tab.get(10),
				new Carte(ValeurCartes.V_3, SorteCartes.PIQUE));
		assertEquals(tab.get(15),
				new Carte(ValeurCartes.V_4, SorteCartes.TREFLE));

		// pas pareil
		// sorte
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_AS, SorteCartes.CARREAU));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_AS, SorteCartes.TREFLE));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_AS, SorteCartes.PIQUE));
		assertNotEquals(tab.get(1),
				new Carte(ValeurCartes.V_AS, SorteCartes.COEUR));

		// valeur
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_KING, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_QUEEN, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_JACK, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_10, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_9, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_8, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_7, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_6, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_5, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_4, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_3, SorteCartes.COEUR));
		assertNotEquals(tab.get(0),
				new Carte(ValeurCartes.V_2, SorteCartes.COEUR));

		// completement différent
		assertNotEquals(tab.get(51),
				new Carte(ValeurCartes.V_AS, SorteCartes.COEUR));
		assertNotEquals(tab.get(0), null);
		assertNotEquals(tab.get(0), new Integer(1));
	}

	@Test
	public void testCompareTo()
	{
		assertTrue(tab.get(51).compareTo(tab.get(0)) > 0);
		assertTrue(tab.get(3).compareTo(tab.get(30)) < 0);
		assertTrue(tab.get(4).compareTo(tab.get(8)) < 0);
		assertTrue(tab.get(40).compareTo(tab.get(29)) > 0);

		assertTrue(tab.get(15).compareTo(new Carte(ValeurCartes.V_4, SorteCartes.TREFLE)) == 0);
		
		 try
		 {
		 tab.get(3).compareTo(null);
		 }
		 catch (NullPointerException e)
		 {
		 }
	}

	@Test
	public void testToString()
	{
		int i = 0;
		for (ValeurCartes v : ValeurCartes.values())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				assertEquals(tab.get(i).toString(), "La carte est un "
						+ v.getSymbole() + " de " + s.getSymbole());
				i++;
			}
		}

	}

}
