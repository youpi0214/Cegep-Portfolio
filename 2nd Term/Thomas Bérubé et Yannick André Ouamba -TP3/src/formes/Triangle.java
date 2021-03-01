package formes;

import java.util.Arrays;

import exceptions.FormeException;

/**
 * Classe héritant de Forme, elle permet de créer un triangle et calculer son
 * périmètre ainsi que son aire à l'aide de trois valeurs reçues en paramètre en
 * guises de ses cotés
 * 
 * @version 2019
 * @author Yannick André Ouamba
 *
 */
public class Triangle extends Forme
{

	/**
	 * Mesure du coté A
	 */
	private int coteA = 0;

	/**
	 * Mesure du coté B
	 */
	private int coteB = 0;

	/**
	 * Mesure du coté C
	 */
	private int coteC = 0;

	/**
	 * Construit un triangle avec trois valeurs reçues en paramètre en guise de
	 * cotés et lance une exception de type FormeException s'il est invalide
	 * 
	 * @param pCoteA valeur d'un des cotés du triangle(entier)
	 * @param pCoteB valeur d'un des cotés du triangle(entier)
	 * @param pCoteC valeur d'un des cotés du triangle(entier)
	 * @throws FormeException Exception lancée lorsque l'un(ou plus) des cotés
	 *             reçus en paramètre est invalide
	 */
	public Triangle(int pCoteA, int pCoteB, int pCoteC) throws FormeException
	{
		// appelle le constructeur de Forme pour afin de créer une forme au nom
		// de 'Triangle'
		super("Triangle");

		if (estTriangle(pCoteA, pCoteB, pCoteC))
		{
			coteA = pCoteA;
			coteB = pCoteB;
			coteC = pCoteC;
		}
		else
		{
			throw new FormeException();
		}
	}

	/**
	 * Retourne la valeur du coté A
	 * 
	 * @return coteA (un entier)
	 */
	public int getCoteA()
	{
		return coteA;
	}

	/**
	 * Retourne la valeur du coté B
	 * 
	 * @return coteB (un entier)
	 */
	public int getCoteB()
	{
		return coteB;
	}

	/**
	 * Retourne la valeur du coté C
	 * 
	 * @return coteC (un entier)
	 */
	public int getCoteC()
	{
		return coteC;
	}

	/**
	 * Verifier si la valeur du coté reçu en paramètre est compris entre 1 et 30
	 * et retourne faux s'il ne l'ai pas
	 * 
	 * @param pCote coté du rectangle
	 * @return vrai si le coté est valide
	 */
	private static boolean validerCote(int pCote)
	{
		return pCote >= Forme.MIN_VAL && pCote <= Forme.MAX_VAL;
	}

	/**
	 * Calcule l'aire du triangle et l'arrondie à l'unité prêt avant de le
	 * retourner
	 * 
	 * @return l'aire du triangle
	 */
	@Override
	public int calculerSurface()
	{

		double demiPerim = calculerPerimetre() / 2.0;

		int surface = (int) Math
				.round(Math.sqrt((demiPerim) * (demiPerim - coteA)
						* (demiPerim - coteB) * (demiPerim - coteC)));

		return surface;
	}

	/**
	 * Calcule le périmètre du triangle et le retourne
	 * 
	 * @return périmètre du triangle
	 */
	@Override
	public int calculerPerimetre()
	{
		return coteA + coteB + coteC;
	}

	/**
	 * un résumé du triangle sous forme de chaîne de caractères avec le format
	 * suivant "nom couleur type du rectangle plus petit côté, moyen côté, plus
	 * grand côté"
	 * 
	 * @return String la chaîne de caractères représentant l'objet
	 */
	@Override
	public String toString()
	{
		int[] vec = getCotesTries();

		return super.toString() + " " + getType() + " " + vec[0] + ", " + vec[1]
				+ ", " + vec[2];
	}

	/**
	 * Vérifie si le triangle a des côtés égaux et les compte
	 * 
	 * @return le nombre de côtés égaux
	 */
	private int getNbrCoteEgaux()
	{
		int coteEgaux = 0;

		if (coteA == coteB && coteA == coteC)
			coteEgaux = 3;
		else if (coteA == coteB || coteA == coteC || coteC == coteB)
			coteEgaux = 2;

		return coteEgaux;
	}

	/**
	 * Crée un tableau avec les côtés du triangle puis les trie du plus petit au
	 * plus grand avant de retourner ce tableau
	 * 
	 * @return un tableau contenant les côtés triés
	 */
	private int[] getCotesTries()
	{
		int[] coteTab =
		{ getCoteA(), getCoteB(), getCoteC() };

		Arrays.sort(coteTab);

		return coteTab;
	}

	/**
	 * vérifie si le triangle est un triangle rectangle ou non
	 * 
	 * @return vrai si le triangle est un triangle rectangle
	 */
	private boolean estRectangle()
	{
		int[] vec = getCotesTries();

		boolean valide = Math.pow(vec[2], 2) == Math.pow(vec[1], 2)
				+ Math.pow(vec[0], 2);

		return valide;
	}

	/**
	 * Recherche le type du triangle à l'aide du nombre de côtés égaux qu'il
	 * possède puis le retourne
	 * 
	 * @return le type du triangle
	 */
	public TypeTriangle getType()
	{
		// Redemander à Caro et aussi vérifier de mon côté
		TypeTriangle pType = null;

		if (estRectangle())
		{
			pType = TypeTriangle.RECTANGLE;
		}
		else if (getNbrCoteEgaux() == 3)
		{
			pType = TypeTriangle.EQUILATERAL;
		}
		else if (getNbrCoteEgaux() == 2)
		{
			pType = TypeTriangle.ISOCELE;
		}
		else
		{
			pType = TypeTriangle.SCALENE;
		}

		return pType;
	}

	/**
	 * Vérifie si le triangle est bel et bien un triangle, c'est à dire : ses
	 * côtés sont valides et aucun d'eux n'est plus grand ou égale à la somme
	 * des deux autres
	 * 
	 * @param pCoteA valeur d'un des cotés du triangle(entier)
	 * @param pCoteB valeur d'un des cotés du triangle(entier)
	 * @param pCoteC valeur d'un des cotés du triangle(entier)
	 * @return vrai si le triangle est valide
	 */
	private static boolean estTriangle(int pCoteA, int pCoteB, int pCoteC)
	{
		boolean vraiTriangle = pCoteA < pCoteB + pCoteC
				&& pCoteB < pCoteA + pCoteC && pCoteC < pCoteB + pCoteA;

		return validerCote(pCoteA) && validerCote(pCoteB) && validerCote(pCoteC)
				&& vraiTriangle;
	}

}
