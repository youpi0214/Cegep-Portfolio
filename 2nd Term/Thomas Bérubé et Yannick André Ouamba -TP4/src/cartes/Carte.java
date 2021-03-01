package cartes;

import java.io.Serializable;

import exception.ConstructeurException;

/**
 * Classe permettant de représenter l'abstration d'une carte à jouer. Les objets
 * de cette classe peuvent servir à produire un paquet de cartes.
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public class Carte implements Serializable, Comparable<Carte>
{
	/**
	 * Image du dos de la carte
	 */
	public static final char IMAGE_DOS = 9786;

	private static final long serialVersionUID = 8196721120250324474L;

	/**
	 * La sorte de la carte (Coeur, carreau, pique et trèfle).
	 */
	private SorteCartes sorte;

	/**
	 * La valeur de la carte.
	 */
	private ValeurCartes valeur;

	/**
	 * Un boolean pour savoir si une carte est face visible (vrai) ou pas (faux)
	 */
	private boolean visible = false;

	/**
	 * Constructeur avec paramètres.
	 * 
	 * @param valeur la valeur de la carte
	 * @param sorte la sorte de la carte (Coeur, carreau, pique et trèfle)
	 * @throws ConstructeurException exception lancée si la la valeur ou la
	 *             sorte reçue en paramètre est invalide
	 */
	public Carte(ValeurCartes valeur, SorteCartes sorte)
			throws ConstructeurException
	{
		if (valeur != null && sorte != null)
		{
			setValeur(valeur);
			setSorte(sorte);
		}
		else
		{
			throw new ConstructeurException("la valeur vaut null");
		}
	}

	/**
	 * Obtenir la valeur de la carte
	 * 
	 * @return ValeurCartes un objet de l'énumération.
	 */
	public ValeurCartes getValeur()
	{
		return valeur;
	}

	/**
	 * Mettre à jour la valeur de la carte
	 * 
	 * @param valeur la nouvelle valeur de la carte, null n'est pas accepté
	 */
	private void setValeur(ValeurCartes valeur)
	{
		if (!(valeur == null))
			this.valeur = valeur;
	}

	/**
	 * Obtenir la représentation chaîne de caractères pour la valeur de la
	 * carte.
	 * 
	 * @return String, la chaîne représentant la valeur de la carte.
	 */
	public String getValeurSymbole()
	{
		return valeur.getSymbole();
	}

	/**
	 * Obtenir la sorte de carte (Coeur, carreau, pique et trèfle)
	 * 
	 * @return SorteCartes, un objet énumération qui représente la sorte de la
	 *         carte
	 */
	public SorteCartes getSorte()
	{
		return sorte;
	}

	/**
	 * Mettre à jour la sorte de carte
	 * 
	 * @param sorte la nouvelle sorte de carte, null n'est pas accepté
	 */
	private void setSorte(SorteCartes sorte)
	{
		if (sorte != null)
			this.sorte = sorte;
	}

	/**
	 * Obtenir la représentation caractère pour la sorte de carte.
	 * 
	 * @return char, le caractère représentant la sorte de carte.
	 */
	public char getSorteSymbole()
	{
		return sorte.getSymbole();
	}

	/**
	 * Permet de savoir si une carte est visible (face vers le haut ou pas)
	 * 
	 * @return boolean, vrai si la face est vers le haut
	 */
	public boolean estVisible()
	{
		return visible;
	}

	/**
	 * Permet de modifier la visibilité d'une carte (retourne la carte), soit on
	 * voit le dos avec "false" ou sa face visible avec "true"
	 * 
	 * @param visible vrai si on veut que la carte soit visible, faux sinon.
	 */
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	/**
	 * Retourne l'image de la carte selon sa visibilité. Soit le dos de la carte
	 * ou sa face visible.
	 * 
	 * @return String, l'image de la carte. Une concaténation du symbole de la
	 *         valeur et du symbole de la sorte OU l'image du dos
	 */
	public String toStringCarte()
	{
		String s = "";

		if (estVisible())
			s += getValeurSymbole() + getSorteSymbole();
		else
			s += IMAGE_DOS;

		return s;
	}

	/**
	 * Permet de savoir si deux cartes sont égales en terme de valeur et de
	 * sorte.
	 * 
	 * @param obj l'objet à comparer avec l'objet courant "this"
	 * @return boolean, vrai si les deux cartes sont visibles et qu'elles ont la
	 *         même valeur et la même sorte.
	 */
	@Override
	public boolean equals(Object obj)
	{
		boolean egal = false;
		Carte c = null;

		if (obj instanceof Carte)
		{
			c = (Carte) obj;
			egal = estVisible() == c.estVisible()
					&& getValeur().equals(c.getValeur())
					&& getSorte().equals(c.getSorte());
		}
		return egal;

	}

	/**
	 * Compare 2 cartes selon leur valeur et leur sorte. Si la carte courante
	 * est plus petite que celle reçue en entrée on retourne une valeur
	 * négative, si la carte courante est plus grand que celle reçue en entrée
	 * on retourne une valeur positive, sinon on retourne 0.
	 * 
	 * @param obj l'objet carte à comparer
	 * @return int, une valeur négative ou = à 0 ou positive selon que l'objet
	 *         courant est plus plus petite que ou égale ou plus grand que que
	 *         l'objet reçu en entrée.
	 */
	@Override
	public int compareTo(Carte obj)
	{
		int difference;
		difference = this.getValeur().compareTo(obj.getValeur());

		difference = (difference == 0)
				? +this.getSorte().compareTo(((Carte) obj).getSorte())
				: difference;
		return difference;
	}

	/**
	 * Obtenir une chaîne des infos au sujet d'une carte. Pour les tests
	 * 
	 * @return une chaîne des infos au sujet d'une carte
	 */
	@Override
	public String toString()
	{
		return "La carte est un " + this.getValeurSymbole() + " de "
				+ this.getSorteSymbole();
	}

}
