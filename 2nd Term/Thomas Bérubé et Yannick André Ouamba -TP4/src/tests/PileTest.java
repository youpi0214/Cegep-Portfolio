package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.PileException;
import structures.pile.Pile;

public class PileTest
{
	private Pile p1;

	@Before
	public void testPile()
	{
		p1 = new Pile();
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(p1.isEmpty());

		p1.empiler(new Integer(3));
		assertFalse(p1.isEmpty());
	}

	@Test
	public void testVider()
	{
		p1.empiler(new Integer(1));
		p1.empiler(new Integer(2));
		p1.empiler(new Integer(3));
		assertFalse(p1.isEmpty());

		p1.vider();
		assertTrue(p1.isEmpty());
	}

	@Test
	public void testEmpiler()
	{
		p1.empiler(new Integer(1));
		assertEquals(p1.getPremier(), 1);
		p1.empiler(new Integer(2));
		assertEquals(p1.getPremier(), 2);
		p1.empiler(new Integer(3));
		assertEquals(p1.getPremier(), 3);

	}

	@Test
	public void testGetPremier()
	{
		try
		{
			p1.getPremier();
			fail("pile à sec");
		}
		catch (PileException e)
		{
		}

		p1.empiler(new Integer(1));
		p1.empiler(new Integer(2));
		p1.empiler(new Integer(3));

		assertEquals(p1.getPremier(), 3);
	}

	@Test
	public void testDepiler()
	{
		try
		{
			p1.depiler();
			fail("pile à sec");
		}
		catch (PileException e)
		{
		}

		p1.empiler(new Integer(1));
		p1.empiler(new Integer(2));
		p1.empiler(new Integer(3));

		assertEquals(p1.depiler(), 3);
		assertEquals(p1.depiler(), 2);
		assertEquals(p1.depiler(), 1);
	}

	@Test
	public void testSize()
	{
		assertEquals(p1.size(), 0);

		p1.empiler(new Integer(1));
		p1.empiler(new Integer(2));
		p1.empiler(new Integer(3));

		assertEquals(p1.size(), 3);
	}

	@Test
	public void testToString()
	{
		assertEquals(p1.toString(), "vide!\n");

		p1.empiler(new Integer(1));
		p1.empiler(new Integer(2));
		p1.empiler(new Integer(3));
		assertEquals(p1.toString(), "3\n2\n1\n");
	}

}
