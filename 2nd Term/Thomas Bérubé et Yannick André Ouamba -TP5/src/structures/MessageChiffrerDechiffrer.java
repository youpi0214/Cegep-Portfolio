package structures;

import java.io.File;
import java.util.SortedSet;
import java.util.StringTokenizer;

import exceptions.ConstructeurException;
import utilitaires.FichierUtilitaires;
import utilitaires.MathUtilitaires;

public class MessageChiffrerDechiffrer implements iCrypto
{
	// Si le caractère de remplacement est un espace on peut mettre au moins 80%
	public static final float POURCENTAGE_ACCEPTABLE = 0.8f;
	public static final char CAR_DE_COMPLEMENT = ' ';

	private VecteurDeCaracteres vecCaracteres = null;
	private ListeMatricesChiffrement listeMatricesCandidates = null;
	private SortedSet<String> dico = null;

	/**
	 * Constructeur, permet d'affecter les différents attributs à partir des
	 * objets reçus en entrée. Avant d'affecter les attributs de la classe avec
	 * les objets reçus en entrée, il faut valider ces derniers (<> null et pas
	 * vide).
	 *
	 * @param vecCars la table de caractères pour la correspondance lors de
	 *            l'encodage et du décodage.
	 * @param listeMats liste des matrices candidates.
	 * @param dico liste des mots pour la validation lors du décodage.
	 *
	 * @throws ConstructeurException
	 */
	public MessageChiffrerDechiffrer(VecteurDeCaracteres vecCars,
			ListeMatricesChiffrement listeMats, SortedSet<String> dico)
			throws ConstructeurException
	{
		if (validerVecCaracteres(vecCars) && validerMatsEncodage(listeMats)
				&& validerDico(dico))
		{
			setVecCaracteres(vecCars);
			setMatsEncodage(listeMats);
			setDico(dico);
		}
		else
			throw new ConstructeurException(
					"Le constructeur MessageChiffrerDechiffrer est invalide!");
	}

	private void setVecCaracteres(VecteurDeCaracteres pVec)
	{
		if (validerVecCaracteres(pVec))
		{
			this.vecCaracteres = pVec;
		}
	}

	private void setMatsEncodage(ListeMatricesChiffrement listeMats)
	{
		if (validerMatsEncodage(listeMats))
		{
			this.listeMatricesCandidates = listeMats;
		}
	}

	private void setDico(SortedSet<String> dico)
	{
		if (validerDico(dico))
		{
			this.dico = dico;
		}
	}

	private static boolean validerVecCaracteres(VecteurDeCaracteres pVec)
	{
		return ((pVec != null) && (pVec.getTaille() > 0));
	}

	private static boolean validerMatsEncodage(
			ListeMatricesChiffrement listeMats)
	{
		return ((listeMats != null)
				&& (listeMats.getNombreMatricesCandidates() > 0));
	}

	private static boolean validerDico(SortedSet<String> dico)
	{
		return ((dico != null) && !dico.isEmpty());
	}

	/**
	 * Obtenir la matrice courante qui a été utilisée par l'encodage ou le
	 * décodage. Très utile pour les tests.
	 *
	 * @return la matrice courante utilisée à partir de l'objet
	 *         "listeMatricesCandidates".
	 */
	public int[][] getMatriceCourante()
	{
		return listeMatricesCandidates.getCopieMatriceCourante();
	}

	@Override
	public boolean validerMessageSelonDico(String message,
			float pourcentageDeReussite)
	{
		dico = FichierUtilitaires
				.lireDictionnaire(new File("dictionnaire.txt"));

		int totalChar = 0;
		int CharTrouve = 0;

		StringTokenizer mots = new StringTokenizer(message, " ");

		while (mots.hasMoreTokens())
		{
			String temp = mots.nextToken();
			int cpt = 0;
			int longueurMot = temp.length();

			int j = 1;
			while (j <= longueurMot)
			{
				if (this.dico.contains(temp.substring(0, j).toLowerCase()))
					cpt = temp.substring(0, j).length();
				j++;
			}

			totalChar += longueurMot;
			CharTrouve += cpt;
		}

		return (float) CharTrouve / totalChar >= pourcentageDeReussite;
	}

	@Override
	public String ajusterMessageBrute(String message, int diviseur)
	{
		while (MathUtilitaires.modulo(message.length(), diviseur) != 0)
			message += CAR_DE_COMPLEMENT;

		return message;
	}

	@Override
	public String encoder(String message)
	{
		String messageCrypt = "";
		if (message != null)
		{
			this.listeMatricesCandidates.choisirMatriceCourante();
			int pDimension = listeMatricesCandidates.getDimension();
			message = ajusterMessageBrute(message, pDimension);

			messageCrypt = messageCryptDeCrypt(getMatriceCourante(), message);
		}

		return messageCrypt;

	}

	@Override
	public String decoder(String message)
	{
		String messageDeCrypt = "";
		boolean dechiffer = false;

		if (message != null)
			for (int matInd = 0; matInd < listeMatricesCandidates
					.getNombreMatricesCandidates() && !dechiffer; matInd++)
			{
				messageDeCrypt = "";
				listeMatricesCandidates.choisirMatriceCourante(matInd);
				int[][] matInverse = listeMatricesCandidates
						.getMatriceCouranteInverseHill().clone();

				messageDeCrypt = messageCryptDeCrypt(matInverse, message);

				dechiffer = validerMessageSelonDico(messageDeCrypt,
						POURCENTAGE_ACCEPTABLE);
			}

		return (dechiffer) ? messageDeCrypt.trim() : null;
	}

	/**
	 * permet de crypter ou décrypter un message selon la matrice et le message
	 * reçus en paramètre
	 * 
	 * @param cleChiffreDechiffre clé de chiffrement ou de déchiffrement
	 * @param message texte crypté ou à crypter
	 * @return retourne le message de façon crypté ou décrypté
	 */
	private String messageCryptDeCrypt(int[][] cleChiffreDechiffre,
			String message)
	{
		int pDimension = listeMatricesCandidates.getDimension();
		String messageSecret = "";
		int longueur = 0;
		int c = 0;

		while (message.length() > 0)
		{
			for (int i = 0; i < pDimension; i++)
			{
				for (int j = 0; j < pDimension; j++)
				{
					c += (cleChiffreDechiffre[i][j] * vecCaracteres
							.getIndice(message.charAt(longueur)));
					longueur++;
				}
				messageSecret += vecCaracteres.getCaractere(
						MathUtilitaires.modulo(c, vecCaracteres.getTaille()));
				c = 0;
				longueur = 0;
			}
			message = message.substring(pDimension);
		}
		return messageSecret;
	}
}
