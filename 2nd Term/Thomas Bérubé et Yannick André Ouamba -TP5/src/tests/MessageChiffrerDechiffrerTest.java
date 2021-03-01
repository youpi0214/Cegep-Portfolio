package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import exceptions.ConstructeurException;
import structures.ListeMatricesChiffrement;
import structures.MessageChiffrerDechiffrer;
import structures.VecteurDeCaracteres;
import utilitaires.FichierUtilitaires;

public class MessageChiffrerDechiffrerTest
{
	private MessageChiffrerDechiffrer m1, m2, m3;

	@Before
	public void testMessageChiffrerDechiffrer()
	{
		m1 = new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
				new ListeMatricesChiffrement(0, 6, 2, 28), FichierUtilitaires
						.lireDictionnaire(new File("dictionnaire.txt")));
		m2 = new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
				new ListeMatricesChiffrement(10, 23, 3, 28), FichierUtilitaires
						.lireDictionnaire(new File("dictionnaire.txt")));
		m3 = new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
				new ListeMatricesChiffrement(10, 37, 5, 28), FichierUtilitaires
						.lireDictionnaire(new File("dictionnaire.txt")));
	}

	@Test
	public void testMessageChiffrerDechiffrerInvalides()
	{
		// vecteur de caractères null
		try
		{
			new MessageChiffrerDechiffrer(null,
					new ListeMatricesChiffrement(10, 27, 2, 28),
					FichierUtilitaires
							.lireDictionnaire(new File("dictionnaire.txt")));
		}
		catch (ConstructeurException e)
		{
		}

		// vecteur de caractères vide
		char[] ch =
		{};
		try
		{
			new MessageChiffrerDechiffrer(new VecteurDeCaracteres(ch),
					new ListeMatricesChiffrement(10, 27, 2, 28),
					FichierUtilitaires
							.lireDictionnaire(new File("dictionnaire.txt")));
		}
		catch (ConstructeurException e)
		{
		}

		// listes de matrices null
		try
		{
			new MessageChiffrerDechiffrer(new VecteurDeCaracteres(), null,
					FichierUtilitaires
							.lireDictionnaire(new File("dictionnaire.txt")));
		}
		catch (ConstructeurException e)
		{
		}

		// liste de matrices vides
		try
		{
			new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
					new ListeMatricesChiffrement(0, 0, 1, 28),
					FichierUtilitaires
							.lireDictionnaire(new File("dictionnaire.txt")));
		}
		catch (ConstructeurException e)
		{
		}

		// dictionnaire null
		try
		{
			new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
					new ListeMatricesChiffrement(10, 27, 2, 28), null);
		}
		catch (ConstructeurException e)
		{
		}

		// dictionnaire vide
		try
		{
			new MessageChiffrerDechiffrer(new VecteurDeCaracteres(),
					new ListeMatricesChiffrement(10, 27, 2, 28),
					FichierUtilitaires
							.lireDictionnaire(new File("dicoInvalide.txt")));
		}
		catch (ConstructeurException e)
		{
		}
	}

	@Test
	public void testValiderMessageSelonDico()
	{
		// pourcentage de mots trouvées > 80%
		assertTrue(m1.validerMessageSelonDico("bonjour",
				MessageChiffrerDechiffrer.POURCENTAGE_ACCEPTABLE));
		assertTrue(m2.validerMessageSelonDico(
				"ce n est pas a un vieux singe qu on apprend a faire des grimaces",
				MessageChiffrerDechiffrer.POURCENTAGE_ACCEPTABLE));
		assertTrue(m1.validerMessageSelonDico("bonjour les ksk amis",
				MessageChiffrerDechiffrer.POURCENTAGE_ACCEPTABLE));

		// pourcentage de mots trouvées < 80%
		assertFalse(m3.validerMessageSelonDico("asdf kasd kasdf ce",
				MessageChiffrerDechiffrer.POURCENTAGE_ACCEPTABLE));
		assertFalse(m3.validerMessageSelonDico("je suis unk graconr",
				MessageChiffrerDechiffrer.POURCENTAGE_ACCEPTABLE));
	}

	@Test
	public void testAjusterMessageBrute()
	{
		String s = "ce n est pas a un vieux";
		assertTrue(s.length() == 23);
		assertTrue(m1.ajusterMessageBrute(s, 13).length() == 26);

		s = "je suis petit";
		assertTrue(s.length() == 13);
		assertTrue(m1.ajusterMessageBrute(s, 8).length() == 16);

		s = " ";
		assertTrue(s.length() == 1);
		assertTrue(m1.ajusterMessageBrute(s, 4).length() == 4);

		s = " fin de session";
		assertTrue(s.length() == 15);
		assertTrue(m1.ajusterMessageBrute(s, 15).length() == 15);
	}

	@Test
	public void testEncoder()
	{
		// 2x2
		assertEquals(m1.decoder(m1.encoder("vive la fin de session")),
				"VIVE LA FIN DE SESSION");
		// 5x5
		assertEquals(m3.decoder(m3.encoder("amis")), "AMIS");
		// 3x3
		assertEquals(m2.decoder(m2.encoder("230j0jrw")), null);
	}

	@Test
	public void testDecoder()
	{
		// l homme est un loup pour l homme
		assertEquals(m1.decoder("HGHOIYAGMWPKSFUT QLWPS ZUTM-KEUM"),
				"L HOMME EST UN LOUP POUR L HOMME");
		assertEquals(m1.decoder(null), null);

		// ni vu ni connu
		assertEquals(m2.decoder(" ZTORT ZTWKLSZX"), "NI VU NI CONNU");
		assertEquals(m2.decoder(null), null);

		// bonjour les amis
		assertEquals(m3.decoder("ZK-LOPJSGE S KWSYCIC"), "BONJOUR LES AMIS");
		assertEquals(m3.decoder(null), null);
	}

}
