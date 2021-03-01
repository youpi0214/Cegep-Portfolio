package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import exceptions.FormeException;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 *
 * @author Jocelyn.Goulet
 *
 */
public class TypeTriangleConformeTest
{

	@Test
	public void testTypeTriangleConforme()
			throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.TypeTriangle");

		// Constructeurs
		assertTrue(c.isEnum());

		// Attributs
		assertTrue(c.getDeclaredField("type").getType() == String.class);

		// Méthodes
		assertTrue(c.getMethod("getType", new Class[]
		{}).getReturnType() == String.class);
		assertTrue((c.getDeclaredMethod("setType", new Class[]
		{ String.class }).getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("setType", new Class[]
		{ String.class }).getReturnType() == void.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
	}
}
