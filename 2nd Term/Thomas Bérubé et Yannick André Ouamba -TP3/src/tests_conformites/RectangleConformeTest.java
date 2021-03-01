package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 * 
 * @author Jocelyn.Goulet
 *
 */
public class RectangleConformeTest
{

	@Test
	public void testRectangleConforme() throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.Rectangle");

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{ int.class, int.class }).getName() == "formes.Rectangle");

		// Attributs publics
		assertTrue(c.getDeclaredField("hauteur").getType() == int.class);
		assertTrue((c.getDeclaredField("hauteur").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("largeur").getType() == int.class);
		assertTrue((c.getDeclaredField("largeur").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes publiques
		assertTrue(c.getMethod("calculerPerimetre", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("calculerSurface", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("getHauteur", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("setHauteur", new Class[]
		{ int.class }).getReturnType() == void.class);
		assertTrue(c.getMethod("getLargeur", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("setLargeur", new Class[]
		{ int.class }).getReturnType() == void.class);
		assertTrue((c.getDeclaredMethod("validerHauteur", new Class[]
		{ int.class }).getModifiers()
				& Modifier.STATIC + Modifier.PRIVATE) == Modifier.STATIC
						+ Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("validerHauteur", new Class[]
		{ int.class }).getReturnType() == boolean.class);
		assertTrue((c.getDeclaredMethod("validerLargeur", new Class[]
		{ int.class }).getModifiers()
				& Modifier.STATIC + Modifier.PRIVATE) == Modifier.STATIC
						+ Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("validerLargeur", new Class[]
		{ int.class }).getReturnType() == boolean.class);
	}
}
