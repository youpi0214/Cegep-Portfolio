package tests_conformites;

import static org.junit.Assert.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

import jeu.JeuMemoire;
import jeu.Memorisable;
import java.awt.Point;

import org.junit.Test;

import formes.Forme;
import formes.VecteurFormes;

/**
 * Tests de conformité pour la classe Rectangle Tp2 420-202-LI Hiver 2015
 * 
 * @author Jocelyn.Goulet
 *
 */
public class JeuMemoireConformeTest
{

	@Test
	public void testJeuMemoireConforme()
			throws ClassNotFoundException, Exception
	{
		// Package et nom de la classe
		Class c = Class.forName("jeu.JeuMemoire");

		// Interface
		assertTrue(c.newInstance() instanceof Memorisable);

		// Constructeurs
		assertTrue(c.getConstructor(new Class[]
		{}).getName() == "jeu.JeuMemoire");

		// Attributs publics
		assertTrue((c.getField("LIGNE").getType() == int.class)
				&& (JeuMemoire.LIGNE == 6));
		assertTrue((c.getField("COLONNE").getType() == int.class)
				&& (JeuMemoire.COLONNE == 6));
		assertTrue((c.getField("NBR_ELEMENTS_GRILLE").getType() == int.class)
				&& (JeuMemoire.NBR_ELEMENTS_GRILLE == JeuMemoire.COLONNE
						* JeuMemoire.LIGNE));

		// Attributs
		assertTrue(c.getDeclaredField("vecteurPoints")
				.getType() == ArrayList.class);
		assertTrue((c.getDeclaredField("vecteurPoints").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("niveau").getType() == int.class);
		assertTrue((c.getDeclaredField("niveau").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("vecteurFormes")
				.getType() == VecteurFormes.class);
		assertTrue((c.getDeclaredField("vecteurFormes").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(
				c.getDeclaredField("grilleDeJeu").getType() == Forme[][].class);
		assertTrue((c.getDeclaredField("grilleDeJeu").getModifiers()
				& Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes
		assertTrue(c.getMethod("getNomForme", new Class[]
		{ int.class, int.class }).getReturnType() == String.class);
		assertTrue(c.getMethod("jouerOrdi", new Class[]
		{}).getReturnType() == ArrayList.class);
		assertTrue(c.getMethod("toString", new Class[]
		{}).getReturnType() == String.class);
		assertTrue(c.getMethod("jouerHumain", new Class[]
		{ int.class, int.class }).getReturnType() == boolean.class);
		assertTrue(c.getMethod("getNiveau", new Class[]
		{}).getReturnType() == int.class);
		assertTrue(c.getMethod("setNiveauPlusUn", new Class[]
		{}).getReturnType() == void.class);
	}
}
