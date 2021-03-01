package test_prof_conformes;

import static org.junit.Assert.*;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import org.junit.Test;

import structures.pile.NoeudPile;
import structures.pile.Pile;

/**
 * Tests de conformité pour la classe Pile Tp4 420-202-LI Hiver 2017
 *
 * @author Jocelyn.Goulet
 *
 */
public class PileConformeTest {

	@Test
	public void testPileConforme() throws ClassNotFoundException, Exception {
		// Package et nom de la classe
		Class c = Class.forName("structures.pile.Pile");

		// Constructeurs
		assertTrue(c.getConstructor(new Class[] {}).getName() == "structures.pile.Pile");

		// Interface
		Pile pile = new Pile();
		assertTrue(pile instanceof Serializable);

		// Attributs
		assertTrue(c.getDeclaredField("sommet").getType() == NoeudPile.class);
		assertTrue((c.getDeclaredField("sommet").getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE);
		assertTrue(c.getDeclaredField("taille").getType() == int.class);
		assertTrue((c.getDeclaredField("taille").getModifiers() & Modifier.PRIVATE) == Modifier.PRIVATE);

		// Méthodes
		assertTrue(c.getMethod("isEmpty", new Class[] {}).getReturnType() == boolean.class);
		assertTrue(c.getMethod("vider", new Class[] {}).getReturnType() == void.class);
		assertTrue(c.getMethod("empiler", new Class[] {Object.class}).getReturnType() == void.class);
		assertTrue(
				c.getMethod("depiler", new Class[] {}).getReturnType() == Object.class);
		assertTrue(c.getMethod("getPremier", new Class[] {}).getReturnType() == Object.class);
		assertTrue(c.getMethod("size", new Class[] {}).getReturnType() == int.class);
		assertTrue(c.getMethod("toString", new Class[] {}).getReturnType() == String.class);
	}
}
