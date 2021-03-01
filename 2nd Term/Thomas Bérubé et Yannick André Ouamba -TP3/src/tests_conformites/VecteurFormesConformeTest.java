package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import org.junit.Test;

import formes.ManipulerVecteur;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 * 
 * @author Jocelyn.Goulet
 *
 */
public class VecteurFormesConformeTest
{

	@Test
	public void testFormeConforme() throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("formes.VecteurFormes");

		// Interface
		assertTrue(c.newInstance() instanceof ManipulerVecteur);

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{}).getName() == "formes.VecteurFormes");

		// Attributs
		assertTrue(c.getDeclaredField("vecteur").getType() == ArrayList.class);
		assertTrue((c.getDeclaredField("vecteur").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes
		assertTrue(c.getMethod("remplir", new Class[]
		{ int.class }).getReturnType() == void.class);
		assertTrue(c.getMethod("getVecteur", new Class[]
		{}).getReturnType() == ArrayList.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("trier", new Class[]
		{}).getReturnType() == void.class);
		assertTrue(c.getMethod("melanger", new Class[]
		{}).getReturnType() == void.class);
	}
}
