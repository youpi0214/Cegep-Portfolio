package jeu;

import java.awt.Point;
import java.util.ArrayList;

import formes.Forme;
import formes.VecteurFormes;

/**
 * Cette classe permet de créer une matrice représentant une grille de jeu et de
 * la remplir de formes
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public class JeuMemoire implements Memorisable
{
	/**
	 * Le nombre de lignes de la grille de jeu
	 */
	public static final int LIGNE = 6;

	/**
	 * Le nombre de colonnes de la grille de jeu
	 */
	public static final int COLONNE = 6;

	/**
	 * Le nombre d'éléments dans la gille de jeu
	 */
	public static final int NBR_ELEMENTS_GRILLE = LIGNE * COLONNE;

	/**
	 * La longueur de la chaîne de caractères dans chaque case de la grille de
	 * jeu
	 */
	private static final int LONGUEUR_CHAINE = 17;

	/**
	 * Un vecteur de points(coordonnées graphiques)
	 */
	private ArrayList<Point> vecteurPoints;

	/**
	 * Le niveau du jeu
	 */
	private int niveau = 0;

	/**
	 * Un vecteur de formes
	 */
	private VecteurFormes vecteurFormes;

	/**
	 * Matrice de la grille de jeu contenant des formes
	 */
	private Forme[][] grilleDeJeu;

	/**
	 * Permet de remplir la matrice à partir d’un vecteur de formes
	 * (VecteurFormes) préalablement rempli et mélangé
	 * 
	 */
	public JeuMemoire()
	{
		preparerGrilleDeJeu();
		preparerVecteurForme();

		int indexForme = 0;
		for (int i = 0; i < LIGNE && indexForme < NBR_ELEMENTS_GRILLE; i++)
		{
			for (int j = 0; j < COLONNE
					&& indexForme < NBR_ELEMENTS_GRILLE; j++)
			{
				this.grilleDeJeu[i][j] = vecteurFormes.getVecteur()
						.get(indexForme);
				indexForme++;
			}
		}
		this.niveau = 1;
	}

	/**
	 * Rempli un vecteur de Formes et le mélange
	 * 
	 */
	private void preparerVecteurForme()
	{
		this.vecteurFormes = new VecteurFormes();
		this.vecteurFormes.remplir(NBR_ELEMENTS_GRILLE);
		this.vecteurFormes.melanger();

	}

	/**
	 * Instancie la grille de jeu avec le bon nombre de lignes et de colonnes
	 */
	private void preparerGrilleDeJeu()
	{
		grilleDeJeu = new Forme[LIGNE][COLONNE];
	}

	/**
	 * Ajoute des espaces à la String reçu afin d'atteindre le nombre de
	 * caractère demandé, puis retourne la String
	 * 
	 * @param nbreCaractères le nombre d'espaces à ajouter
	 * @param pString le nom de la forme et sa couleur
	 * @return une chaîne de 17 caractères incluant le nom et la couleur de la
	 *         forme
	 */
	private String ajouterEspaces(int nbreCaractères, String pString)
	{
		while (pString.length() < nbreCaractères)
			pString += " ";

		return pString;
	}

	/**
	 * Retourne un point choisi aléatoirement dans les limites de la grille
	 * 
	 * @return un point au hasard
	 */
	private Point choisirForme()
	{
		Point pPoint = null;
		do
		{
			pPoint = new Point((int) (Math.random() * LIGNE),
					(int) (Math.random() * COLONNE));
		}
		while (this.vecteurPoints.contains(pPoint));

		return pPoint;
	}

	/**
	 * Permet de savoir si la coordonnée à deviner est celle reçue en paramètre
	 * 
	 * @param i numéro de la ligne choisir par le joueur
	 * @param j numéro de la colonne choisir par le joueur
	 * @return retourne vrai si la coordonnée i, j de la forme à deviner est
	 *         celle reçue en paramètre
	 */
	@Override
	public boolean jouerHumain(int i, int j)
	{
		boolean egale = (int) this.vecteurPoints.get(0).getX() == i
				&& (int) this.vecteurPoints.get(0).getY() == j;
		this.vecteurPoints.remove(0);

		return egale;
	}

	/**
	 * Permet de créer une liste de points uniques pris au hasard dans la
	 * matrice
	 * 
	 * @return un vecteur avec des points choisi au hasard
	 */
	@Override
	public ArrayList<Point> jouerOrdi()
	{
		vecteurPoints = new ArrayList<>();
		for (int i = 0; i < this.niveau + 2; i++)
		{
			vecteurPoints.add(choisirForme());
		}
		return vecteurPoints;
	}

	/**
	 * Retourne le nom de la forme et sa couleur (toStringCourt) selon les
	 * coordonnées reçues en paramètre
	 * 
	 * @param i numéro de la ligne de la grille de jeu
	 * @param j numéro de la colonne de la grille de jeu
	 * @return le nom de la forme et sa couleur
	 */
	@Override
	public String getNomForme(int i, int j)
	{
		return this.grilleDeJeu[i][j].toStringCourt().replaceAll(" ", "");
	}

	/**
	 * Retourne la valeur du niveau où est rendu le joueur
	 * 
	 * @return le niveau
	 */
	@Override
	public int getNiveau()
	{
		return this.niveau;
	}

	/**
	 * Retourne la grille de Jeu contenant les formes
	 * 
	 * @return la grille de jeu
	 */
	public Forme[][] getGrille()
	{
		return this.grilleDeJeu;
	}

	/**
	 * Retourne le vecteur contenant les formes (vecteurFormes)
	 * 
	 * @return le vecteur de formes
	 */
	public VecteurFormes getVecteur()
	{
		return this.vecteurFormes;
	}

	/**
	 * Augmente le niveau de 1 unité
	 */
	@Override
	public void setNiveauPlusUn()
	{
		if (this.niveau < NIVEAU_MAX)
		{
			this.niveau++;
		}
	}

	/**
	 * Retourne une chaîne avec les caractéristiques résumant les éléments de la
	 * grille de jeu
	 * 
	 * @return String la chaîne de caractères représentant la grille de jeu
	 */
	@Override
	public String toString()
	{
		String chaine = "";
		int nbreForme = 0;
		for (int i = 0; i < LIGNE
				&& nbreForme < this.vecteurFormes.getVecteur().size(); i++)
		{
			for (int j = 0; j < COLONNE
					&& nbreForme < this.vecteurFormes.getVecteur().size(); j++)
			{
				chaine += ajouterEspaces(LONGUEUR_CHAINE,
						this.grilleDeJeu[i][j].toStringCourt()) + "| ";
				nbreForme++;
			}
			chaine += "\n";
		}
		return chaine;
	}
}
