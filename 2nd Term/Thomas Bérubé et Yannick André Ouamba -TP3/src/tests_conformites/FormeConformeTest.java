package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;

import org.junit.Test;

import formes.Couleur;
import formes.Forme;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 * 
 * @author Jocelyn.Goulet
 *
 */
public class FormeConformeTest
{

	@Test
	public void testFormeConforme() throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.Forme");

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{ String.class }).getName() == "formes.Forme");

		// Attributs
		assertTrue((c.getField("MIN_VAL").getModifiers() & Modifier.STATIC
				+ Modifier.FINAL + Modifier.PUBLIC) == Modifier.STATIC
						+ Modifier.FINAL + Modifier.PUBLIC);
		assertTrue((c.getField("MIN_VAL").getType() == int.class)
				&& (Forme.MIN_VAL == 1));
		assertTrue((c.getField("MAX_VAL").getModifiers() & Modifier.STATIC
				+ Modifier.FINAL + Modifier.PUBLIC) == Modifier.STATIC
						+ Modifier.FINAL + Modifier.PUBLIC);
		assertTrue((c.getField("MAX_VAL").getType() == int.class)
				&& (Forme.MAX_VAL == 30));
		assertTrue(
				(c.getField("COULEUR_DEFAUT").getModifiers() & Modifier.STATIC
						+ Modifier.FINAL + Modifier.PUBLIC) == Modifier.STATIC
								+ Modifier.FINAL + Modifier.PUBLIC);
		assertTrue((c.getField("COULEUR_DEFAUT").getType() == Couleur.class)
				&& (Forme.COULEUR_DEFAUT.equals(Couleur.ROUGE)));
		assertTrue(c.getDeclaredField("nom").getType() == String.class);
		assertTrue((c.getDeclaredField("nom").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("couleur").getType() == Couleur.class);
		assertTrue((c.getDeclaredField("couleur").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes
		assertTrue((c.getMethod("calculerPerimetre", new Class[]
		{}).getModifiers()
				& Modifier.ABSTRACT + Modifier.PUBLIC) == Modifier.ABSTRACT
						+ Modifier.PUBLIC);
		assertTrue(c.getMethod("calculerPerimetre", new Class[]
		{}).getReturnType() == int.class);
		assertTrue((c.getMethod("calculerSurface", new Class[]
		{}).getModifiers()
				& Modifier.ABSTRACT + Modifier.PUBLIC) == Modifier.ABSTRACT
						+ Modifier.PUBLIC);
		assertTrue(c.getMethod("calculerSurface", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("toStringCourt", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("getCouleur", new Class[]
		{}).getReturnType() == Couleur.class);
		assertTrue(c.getMethod("setCouleur", new Class[]
		{ Couleur.class }).getReturnType() == void.class);
		assertTrue(c.getMethod("getNom", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("equals", new Class[]
		{ Object.class }).getReturnType() == boolean.class);
		assertTrue(c.getMethod("compareTo", new Class[]
		{ Forme.class }).getReturnType() == int.class);
	}
}
