package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exceptions.ConstructeurException;
import structures.ListeCombinatoire;

public class ListeCombinatoireTest
{
	ListeCombinatoire list1, list2, list3, list4;

	@Before
	public void testListeCombinatoire()
	{
		list1 = new ListeCombinatoire(1, 5, 3);
		list2 = new ListeCombinatoire(6, 11, 3);
		list3 = new ListeCombinatoire(0, 8, 5);
		list4 = new ListeCombinatoire(0, 50, 5);
	}

	@Test
	public void testListeCombinatoireInvalides()
	{
		try
		{ // min< minLim
			new ListeCombinatoire(-1, 10, 3);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{ // max > maxLimite
			new ListeCombinatoire(0, 51, 3);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{ // min > max
			new ListeCombinatoire(15, 5, 3);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{ // min > maxLim
			new ListeCombinatoire(51, 52, 0);
		}
		catch (ConstructeurException e)
		{
		}

		try
		{ // combiLongueur < combiLongeurMin
			new ListeCombinatoire(5, 16, 0);
		}
		catch (ConstructeurException e)
		{
		}
		try
		{ // combiLongueur < longueurEnsemble
			new ListeCombinatoire(5, 16, 15);
		}
		catch (ConstructeurException e)
		{
		}

	}

	@Test
	public void testGenererEnsembleValeurs()
	{
		int a = 0, b = 0, c = 0, d = 0;

		int[] tab1 =
		{ 1, 2, 3, 4, 5 };
		for (int i : list1.genererEnsembleValeurs())
			assertTrue(tab1[a++] == i);

		int[] tab2 =
		{ 6, 7, 8, 9, 10, 11 };
		for (int i : list2.genererEnsembleValeurs())
			assertTrue(tab2[b++] == i);

		int[] tab3 =
		{ 0, 1, 2, 3, 4, 5, 6, 7, 8 };
		for (int i : list3.genererEnsembleValeurs())
			assertTrue(tab3[c++] == i);

		int[] tab4 = new int[51];
		for (int x = 0; x < tab4.length; x++)
			tab4[x] = x;

		for (int i : list4.genererEnsembleValeurs())
			assertTrue(tab4[d++] == i);
	}

	@Test
	public void testToString()
	{
		// TODO
	}

}
