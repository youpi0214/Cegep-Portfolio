package formes;

/**
 * Classe abstraite permettant à d'autre classe de créer une forme géométrique
 * qui possèdera un nom, une couleur, une surface et un périmètre
 * 
 * @version 2019
 * @author Yannick André Ouamba
 */
public abstract class Forme implements Comparable<Forme>
{
	/**
	 * valeur minimal des mesures des formes géométriques
	 */
	public static final int MIN_VAL = 1;

	/**
	 * valeur maximal des mesures des formes géométriques
	 */
	public static final int MAX_VAL = 30;

	/**
	 * couleur par défaut d'une forme
	 */
	public static final Couleur COULEUR_DEFAUT = Couleur.ROUGE;

	/**
	 * nom de la forme géométrique
	 */
	private String nom;

	/**
	 * couleur de la forme géométrique
	 */
	private Couleur couleur;

	/**
	 * Créer une forme géométrique avec un nom et une couleur par défaut rouge
	 * 
	 * @param pNom valeur en chaîne caractère de la forme géométrique
	 */
	public Forme(String pNom)
	{
		nom = pNom;
		couleur = COULEUR_DEFAUT;
	}

	/**
	 * Retourne la couleur de la forme
	 * 
	 * @return couleur de la forme
	 */
	public Couleur getCouleur()
	{

		return couleur;
	}

	/**
	 * Retourne le nom de la forme
	 * 
	 * @return nom de la forme
	 */
	public String getNom()
	{
		return nom;
	}

	/**
	 * Modifie la couleur de la forme pour celle reçue en paramètre si elle
	 * correspond à une des valeur de l'enum. Si elle ne correspond à aucune, la
	 * couleur de la forme ne subit aucune modification
	 * 
	 * @param pCouleur couleur de la forme (rouge, vert, bleu, jaune, noir,
	 *            orange);
	 */
	public void setCouleur(Couleur pCouleur)
	{
		if (pCouleur != null)
			couleur = pCouleur;
	}

	/**
	 * Méthode abstraite qui oblige toute les classes héritant de Forme à avoir
	 * une surface
	 * 
	 * @return surface de la forme arrondie a l'unité prêt
	 */
	public abstract int calculerSurface();

	/**
	 * Méthode abstraite qui oblige toute les classes héritant de Forme à avoir
	 * un périmètre
	 * 
	 * @return périmètre de la forme arrondie a l'unité prêt
	 */
	public abstract int calculerPerimetre();

	/**
	 * 
	 * @return un résumé de la forme
	 */
	public String toStringCourt()
	{
		return getNom() + " " + getCouleur();
	}

	/**
	 * Retourne le résumé de la forme dans ce format : ("nom de la forme"
	 * "couleur")
	 * 
	 * @return un résumé de la forme (utilisé par les classes enfants de cette
	 *         méthode)
	 */
	@Override
	public String toString()
	{
		return getNom() + " " + getCouleur();
	}

	/**
	 * Compare deux formes en vérifiant si elles ont le même nom et ensuite la
	 * même couleur, elle retourne la différence (nombre entier) de leur chaîne
	 * de caractères
	 * 
	 * @return la différence du code ASCII des chaînes de caractères comparées
	 */
	@Override
	public int compareTo(Forme pForme)
	{
		int compare = 0;
		if (pForme != null)
		{
			String nomCouleurPForme = (pForme.getNom() + pForme.getCouleur());

			compare = ((this.getNom() + this.getCouleur()))
					.compareToIgnoreCase(nomCouleurPForme);
		}
		return compare;
	}

	/**
	 * Vérifie si les deux formes comparées ont le même nom, la même surface et
	 * la même couleur. Elle retourne vrai si ces trois attributs sont
	 * identiques et faux dans le cas où l'une (ou plus) d'elle ne l'ai pas
	 * 
	 * @return vrai si l'aire et la couleur sont égales
	 */
	@Override
	public boolean equals(Object pObject)
	{

		// vérifie si l'objet reçu est une instance de Forme avant
		// de comparer

		boolean valide = (pObject instanceof Forme)
				? this.getNom().equals(((Forme) pObject).getNom())
						&& this.calculerSurface() == ((Forme) pObject)
								.calculerSurface()
						&& this.getCouleur()
								.equals(((Forme) pObject).getCouleur())
				: false;

		return valide;
	}
}
