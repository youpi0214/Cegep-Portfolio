package structures.pile;

import java.io.Serializable;

/**
 * Cette classe nécessaire au fonctionnement la classe Pile
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 */
public class NoeudPile implements Serializable
{
	private static final long serialVersionUID = -100106444972236145L;

	/**
	 * Élément à conserver
	 */
	private Object element;

	/**
	 * Pointeur sur le noeud qui est en dessous
	 */
	private NoeudPile precedent;

	/**
	 * Constructeur
	 * 
	 * @param pElement l'élément à mettre dans le noeud
	 */
	public NoeudPile(Object pElement)
	{
		this(pElement, null);
	}

	/**
	 * Constructeur
	 * 
	 * @param pElement l'élément à mettre dans le noeud
	 * @param pPrecedent un pointeur sur un NoeudPile
	 */
	public NoeudPile(Object pElement, NoeudPile pPrecedent)
	{
		this.element = pElement;
		this.precedent = pPrecedent;
	}

	/**
	 * Obtenir une référence sur le noeud précédent.
	 * 
	 * @return NoeudPile le pointeur sur le noeud précédent
	 */
	public NoeudPile getPrecedent()
	{
		return this.precedent;
	}

	/**
	 * Modifier la valeur de la référence du noeud précédent.
	 * 
	 * @param precedent le pointeur du nouveau précédent
	 */
	public void setPrecedent(NoeudPile precedent)
	{
		this.precedent = precedent;
	}

	/**
	 * Obtenir l'élément contenu dans le noeud
	 * 
	 * @return Object l'objet dans le noeud
	 */
	public Object getElement()
	{
		return this.element;
	}

	/**
	 * Modifier la valeur de l'élément contenu dans le noeud
	 * 
	 * @param element l'élément à mettre à jour
	 */
	public void setElement(Object element)
	{
		this.element = element;

	}
}
