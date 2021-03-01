package cartes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import exception.ConstructeurException;

/**
 * Classe permettant de représenter un paquet de cartes à jouer. Peut créer un
 * paquet de cartes complet ou prendre un vecteur de carte comme paquet. Offre
 * des services pour brasser le paquet, piger une carte dans le paquet, regarder
 * une carte du paquet, retourner toutes les cartes du paquet.
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public class PaquetDeCartes implements Serializable
{
	/**
	 * Nombre d'échanges pour brasser un paquet de carte
	 */
	public static final int NBR_ECHANGE = 1000;

	private static final long serialVersionUID = -3742502342755309295L;

	/**
	 * Le paquet de cartes une structure qui hérite de l'interface java "List"
	 */
	private List<Carte> paquet = null;

	/**
	 * Constructeur permettant de créer un paquet complet de cartes de 4 sortes
	 * X 13 cartes. Utilise les énumérations "SorteCartes" et "ValeurCartes".
	 */
	public PaquetDeCartes()
	{
		paquet = new ArrayList<Carte>();
		for (ValeurCartes v : ValeurCartes.values())
		{
			for (SorteCartes s : SorteCartes.values())
			{
				paquet.add(new Carte(v, s));
			}
		}

	}

	/**
	 * Constructeur avec paramètre permettant de faire des tests. On donne une
	 * liste de cartes organisée à notre goût.
	 * 
	 * @param pPaquet un paquet de cartes. Ne peut-être "null"
	 * @throws ConstructeurException exception lancée lorsque paquet de carte
	 *             reçu est invalide ou vide
	 */
	public PaquetDeCartes(List<Carte> pPaquet) throws ConstructeurException
	{
		if (pPaquet != null)
		{
			this.paquet = new ArrayList<Carte>();
			for (int i = 0; i < ((ArrayList<Carte>) pPaquet).size(); i++)
			{
				((ArrayList<Carte>) this.paquet)
						.add(((ArrayList<Carte>) pPaquet).get(i));
			}
		}
		else
		{
			throw new ConstructeurException("Paquet de cartes invalide!");
		}
	}

	/**
	 * Brasse le paquet de cartes en provoquant un certain nombre d'échanges
	 * entre les cartes du paquet. Plus le nombre d'échanges est grand, mieux le
	 * paquet sera brassé. Utilise permuterCarte.
	 */
	public void brasser()
	{
		if (this.paquet != null)
		{
			int taille = this.size();
			int index = 0;
			for (int i = 0; i < taille * NBR_ECHANGE; i++)
			{
				index = (index == taille) ? 0 : index;
				int random = (int) (Math.random() * taille);
				permuterCarte(index, random);
				index++;
			}
		}
	}

	/**
	 * Permet de permuter 2 cartes dans le paquet selon leur position
	 * 
	 * @param index1 index d'une carte
	 * @param index2 index d'une autre carte
	 */
	private void permuterCarte(int index1, int index2)
	{
		Carte temp = paquet.get(index1);
		this.paquet.set(index1, paquet.get(index2));
		this.paquet.set(index2, temp);

	}

	/**
	 * Retire une carte du paquet selon la position voulue. Ne modifie pas la
	 * visibilité de la carte retirée.
	 * 
	 * @param position la position voulue entre 1 et le nombre de cartes dans le
	 *            paquet.
	 * @return Carte la carte choisie ou "null" si le paquet est vide ou si la
	 *         position est invalide.
	 */
	public Carte consulterCarte(int position)
	{
		Carte retour = null;

		if (!isEmpty() && validerPosition(position))
		{
			retour = paquet.get(--position);
		}
		return retour;
	}

	/**
	 * Retire une carte du paquet selon la position voulue. Ne modifie pas la
	 * visibilité de la carte retirée.
	 * 
	 * @param position la position voulue entre 1 et le nombre de cartes dans le
	 *            paquet.
	 * @return Carte la carte enlevée ou "null" si le paquet est vide ou si la
	 *         position est invalide.
	 */
	public Carte prendreCarte(int position)
	{
		Carte retour = null;

		if (!isEmpty() && validerPosition(position))
		{
			retour = paquet.remove(--position);
		}
		return retour;
	}

	/**
	 * Rend toutes les cartes du paquet visibles ou invisibles (face cachée).
	 * 
	 * @param visible vrai pour visible et faux pour face cachée
	 */
	public void retournerToutesLesCartes(boolean visible)
	{
		for (Carte c : paquet)
		{
			c.setVisible(visible);
		}

	}

	/**
	 * Permet de savoir le nombre de cartes dans le paquet.
	 * 
	 * @return int, le nombre de cartes
	 */
	public int size()
	{
		return (int) paquet.size();
	}

	/**
	 * Permet de savoir si le paquet de cartes est vide
	 * 
	 * @return boolean, vrai si le paquet est vide
	 */
	public boolean isEmpty()
	{
		return paquet.isEmpty();

	}

	/**
	 * Valide si l'entier reçu en entrée est valide selon le nombre de cartes
	 * dans le paquet. Attention on parle ici de la position et non de l'index.
	 * 
	 * @param position une position dans le paquet entre 1 et la taille du
	 *            paquet
	 * @return boolean, vrai si l'entier est valide selon le nombre de cartes
	 *         dans le paquet
	 */
	private boolean validerPosition(int position)
	{
		return position >= 1 && position <= size();

	}

}
