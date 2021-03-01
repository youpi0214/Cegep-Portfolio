package structures.pile;

import java.io.Serializable;

import exception.PileException;

/**
 * Cette classe permet de créer une pile d'objet dont seul le sommet est
 * accessible
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 */
public class Pile implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3031983087399508019L;

	/**
	 * Pointeur pour le sommet de la pile
	 */
	private NoeudPile sommet;

	/**
	 * Conserve le nombre d'éléments dans la pile
	 */
	private int taille;

	/**
	 * Construit une pile vide
	 */
	public Pile()
	{
		this.sommet = null;

		this.taille = 0;
	}

	/**
	 * Vérifie si la pile est vide
	 * 
	 * @return boolean vrai si elle est vide
	 */
	public boolean isEmpty()
	{
		return sommet == null;
	}

	/**
	 * Vide la pile
	 */
	public void vider()
	{
		sommet = null;
		taille = 0;
		System.gc();
	}

	/**
	 * Empile un objet dans la pile.
	 * 
	 * @param element l'élément à empiler
	 */
	public void empiler(Object element)
	{
		sommet = new NoeudPile(element, this.sommet);
		this.taille++;
	}

	/**
	 * Retourne l'objet contenu dans le noeud sur le dessus de la pile sans le
	 * dépiler Lève une exception si la pile est vide.
	 * 
	 * @return Object, l'objet sur le dessus de la pile.
	 * @throws PileException exception lancée lorsque la pile est vide
	 */
	public Object getPremier() throws PileException
	{
		Object retour = null;

		if (!isEmpty())
		{
			retour = sommet.getElement();
		}
		else
		{
			throw new PileException("La pile est vide");
		}

		return retour;

	}

	/**
	 * Retourne l'objet dépilé. Lève une exception si la pile est vide. Se sert
	 * de getPremier()
	 * 
	 * @return Object l'élément dépilé
	 * @throws PileException exception lancée lorsque la pile est vide
	 */
	public Object depiler() throws PileException
	{
		Object retour = getPremier();

		if (!isEmpty())
		{
			this.sommet = this.sommet.getPrecedent();

			this.taille--;
		}
		else
		{
			throw new PileException("Pile vide");
		}

		return retour;
	}

	/**
	 * Retourne le nombre d'éléments dans la pile
	 * 
	 * @return le nombre d'éléments dans la pile
	 */
	public int size()
	{
		return taille;

	}

	/**
	 * Permet de créer une chaîne représentant les éléments qui sont dans la
	 * pile.
	 * 
	 * @return le contenu de la pile sous forme de caractères
	 */
	@Override
	public String toString()
	{
		NoeudPile temp = this.sommet;
		// if raccourci
		String s = (isEmpty()) ? "vide!\n" : "";

		while (temp != null)
		{
			s = s + temp.getElement() + "\n";
			temp = temp.getPrecedent();
		}

		return s;
	}
}
