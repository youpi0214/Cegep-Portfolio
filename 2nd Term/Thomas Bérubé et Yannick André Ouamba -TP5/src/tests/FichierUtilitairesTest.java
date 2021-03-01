package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

import utilitaires.FichierUtilitaires;

public class FichierUtilitairesTest
{

	@Test
	public void testEnregistrerMessageEtLireMessage()
	{
		File f = null;
		File f2 = null;
		f = FichierUtilitaires.obtenirNomFichier("une ligne de texte", f);

		// une ligne le choix de fichier permet d'en prendre un déja existant ou
		// en créer un autre
		FichierUtilitaires.enregistrerMessage("Vive la fin de session!", f);
		assertEquals(FichierUtilitaires.lireMessage(f),
				"Vive la fin de session!");

		// plus d'une ligne
		f2 = FichierUtilitaires.obtenirNomFichier("plus d'une ligne de texte",
				f2);
		FichierUtilitaires.enregistrerMessage(
				"Vive la fin de session!\nVivre le Cégep!\nVivre la prog!", f2);
		assertEquals(FichierUtilitaires.lireMessage(f2),
				"Vive la fin de session!");
	}

	@Test
	public void testLireDictionnaire()
	{
		SortedSet<String> dic = new TreeSet<>();
		dic = FichierUtilitaires.lireDictionnaire(
				FichierUtilitaires.obtenirNomFichier("Test dictionnaire"));

		// que les mots soient en minuscule sans espace
		for (String s : dic)
		{
			assertEquals(s, s.replaceAll(" ", "").toLowerCase());
		}
	}

}
