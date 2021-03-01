package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;
import formes.Triangle;
import formes.TypeTriangle;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 * 
 * @author Jocelyn.Goulet
 *
 */
public class TriangleConformeTest
{

	@Test
	public void testTriangleConforme() throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.Triangle");

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{ int.class, int.class, int.class }).getName() == "formes.Triangle");

		// Attributs
		assertTrue(c.getDeclaredField("coteA").getType() == int.class);
		assertTrue((c.getDeclaredField("coteA").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("coteB").getType() == int.class);
		assertTrue((c.getDeclaredField("coteB").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("coteC").getType() == int.class);
		assertTrue((c.getDeclaredField("coteC").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes publiques
		assertTrue(c.getMethod("calculerPerimetre", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("calculerSurface", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("getCoteA", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("getCoteB", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("getCoteC", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("getType", new Class[]
		{}).getReturnType() == TypeTriangle.class);
		assertTrue((c.getDeclaredMethod("estTriangle", new Class[]
		{ int.class, int.class, int.class }).getModifiers()
				& Modifier.STATIC + Modifier.PRIVATE) == Modifier.STATIC
						+ Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("estTriangle", new Class[]
		{ int.class, int.class, int.class }).getReturnType() == boolean.class);
		assertTrue((c.getDeclaredMethod("getNbrCoteEgaux", new Class[]
		{}).getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("getNbrCoteEgaux", new Class[]
		{}).getReturnType() == int.class);
		assertTrue((c.getDeclaredMethod("estRectangle", new Class[]
		{}).getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("estRectangle", new Class[]
		{}).getReturnType() == boolean.class);
		assertTrue((c.getDeclaredMethod("getCotesTries", new Class[]
		{}).getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("getCotesTries", new Class[]
		{}).getReturnType() == int[].class);
		assertTrue((c.getDeclaredMethod("validerCote", new Class[]
		{ int.class }).getModifiers()
				& Modifier.STATIC + Modifier.PRIVATE) == Modifier.STATIC
						+ Modifier.PRIVATE);
		assertTrue(c.getDeclaredMethod("validerCote", new Class[]
		{ int.class }).getReturnType() == boolean.class);
	}
}
