package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;

import formes.Cercle;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 *
 * @author Jocelyn.Goulet
 *
 */
public class CercleConformeTest
{

	@Test
	public void testCercleConforme() throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.Cercle");

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{ int.class }).getName() == "formes.Cercle");

		// Attributs
		assertTrue(c.getDeclaredField("rayon").getType() == int.class);
		assertTrue((c.getDeclaredField("rayon").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes
		assertTrue(c.getMethod("calculerPerimetre", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("calculerSurface", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("getRayon", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("setRayon", new Class[]
		{ int.class }).getReturnType() == void.class);
		assertTrue(c.getDeclaredMethod("validerRayon", new Class[]
		{ int.class }).getReturnType() == boolean.class);
		assertTrue((c.getDeclaredMethod("validerRayon", new Class[]
		{ int.class }).getModifiers()
				& Modifier.STATIC + Modifier.PRIVATE) == Modifier.STATIC
						+ Modifier.PRIVATE);
	}
}
