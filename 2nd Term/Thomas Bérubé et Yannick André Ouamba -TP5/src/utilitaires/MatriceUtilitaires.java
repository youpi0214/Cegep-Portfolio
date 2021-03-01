package utilitaires;

import java.util.Iterator;
import java.util.SortedSet;

/**
 * Classe utilitaires pour la gestion des matrices carrées
 *
 * @author Thomas Bérubé et Yannick André Ouamba
 */
public class MatriceUtilitaires
{

	/**
	 * Permet de produire une chaîne de caractères pour l'affichage d'une
	 * matrice N X M dans la console. Très utile pour faire des tests.
	 *
	 * <pre>
	 *  Exemple d'affichage voulu:
	 *
	 *  [6, 1, -5]
	 *  [-2, -5, 4]
	 *  [-3, 3, -1]
	 * </pre>
	 *
	 * @param mat la matrice N X M à afficher
	 *
	 * @return la chaîne de caractères
	 */
	public static String toStringMat(int[][] mat)
	{
		String matrice = "";

		if (mat != null && mat.length > 0)
			for (int i = 0; i < mat.length; i++)
			{
				if (mat[i].length > 0)
				{
					matrice += "[";
					for (int j = 0; j < mat[i].length; j++)
					{
						matrice += (j != mat[i].length - 1) ? (mat[i][j] + ", ")
								: mat[i][j];
					}
					matrice += "]\n";
				}
			}

		return matrice;
	}

	/**
	 * Retourne la matrice carrée M X N transposée à partir d'une matrice carrée
	 * N X M reçue.
	 *
	 * La matrice transposée d'une matrice est obtenue en échangeant les lignes
	 * et les colonnes.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée M X N transposée
	 */
	public static int[][] getMatTranspose(int[][] mat)
	{
		// faut tu se rassurer que la matrice est carre?

		int[][] matTemp = new int[mat[0].length][mat.length];

		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[0].length; j++)
				matTemp[j][i] = mat[i][j];

		return matTemp;
	}

	/**
	 * Retourne la matrice carrée (N-1) X (N-1) mineure d'une matrice carrée N X
	 * N reçue.
	 *
	 * Est la matrice carrée résultante, lorsque l'on supprime toutes les
	 * valeurs de la ligne et de la colonne reçues, à partir la matrice
	 * d'origine reçue.
	 *
	 * @param mat la matrice d'origine
	 * @param ligne la ligne de valeurs à supprimer
	 * @param col la colonne de valeurs à supprimer
	 *
	 * @return la matrice carrée (N-1) X (N-1) mineure résultante
	 *
	 */
	private static int[][] getMatMineur(int[][] mat, int ligne, int col)
	{
		int[][] matTemp = new int[mat.length - 1][mat[0].length - 1];
		int ligneTemp = 0;
		for (int j = 0; j < mat.length; j++)
		{
			if (j != col)
			{
				int colTemp = 0;
				for (int i = 0; i < mat[0].length; i++)
				{
					if (i != ligne)
					{
						matTemp[colTemp][ligneTemp] = mat[i][j];
						colTemp++;
					}
				}
				ligneTemp++;
			}
		}
		return matTemp;
	}

	/**
	 * Retourne une matrice N X M résultant de la multiplication d'un scalaire
	 * reçu avec chaque élément d'une matrice N X M reçue. Pas d'arrondi (partie
	 * entière)
	 *
	 * @param mat la matrice d'origine
	 * @param scalaire le scalaire
	 *
	 * @return la matrice résultante de la multiplication avec un scalaire
	 */
	public static int[][] getMatMultScalaire(int[][] mat, float scalaire)
	{
		int[][] matTemp = new int[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[i].length; j++)
			{
				matTemp[i][j] = (int) (mat[i][j] * scalaire);
			}
		return matTemp;
	}

	/**
	 * Retourne une matrice N X M résultant de l'application d'un modulo reçu,
	 * sur chaque élément d'une matrice N X M reçue. Utilise la méthode modulo
	 * de MathUtilitaires.
	 *
	 * @param mat la matrice d'origine
	 * @param mod le modulo à appliquer
	 *
	 * @return la matrice résultante de l'application d'un modulo
	 */
	public static int[][] getMatModuloX(int[][] mat, int mod)
	{
		int[][] matTemp = new int[mat.length][mat[0].length];
		for (int i = 0; i < mat.length; i++)
			for (int j = 0; j < mat[i].length; j++)
			{
				matTemp[i][j] = MathUtilitaires.modulo(mat[i][j], mod);
			}
		return matTemp;
	}

	/**
	 * Calcule le déterminant d'une matrice carrée de N X N.
	 *
	 * ATTENTION ; Il existe plusieurs façons de calculer un déterminant, il
	 * faut faire de la recherche.
	 *
	 * Devrait être une méthode récursive...
	 *
	 * @param mat la matrice carrée pour les calculs.
	 *
	 * @return le déterminant de la matrice.
	 */
	public static int getDeterminant(int[][] mat)
	{
		int det = 0;

		if (mat.length == mat[0].length && mat.length > 0)
		{
			if (mat.length == 1)
			{
				det = mat[0][0];
			}
			else if (mat.length != 2)
			{
				for (int j = 0; j < mat.length; j++)
				{
					det += Math.pow(-1, ((0 + 1) + (j + 1))) * mat[0][j]
							* getDeterminant(getMatMineur(mat, 0, j));
				}
			}
			else
				det = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
		}

		return det;
	}

	/**
	 * Retourne la matrice carrée des cofacteurs N X N d'une matrice carrée N X
	 * N reçue, utile pour déterminer la matrice adjointe.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée des cofacteurs de la matrice d'origine
	 */
	public static int[][] getMatCofacteurs(int[][] mat)
	{
		int[][] matTemp = new int[mat.length][mat[0].length];

		if (mat.length > 1 && mat.length == mat[0].length)
		{
			for (int i = 0; i < mat.length; i++)
				for (int j = 0; j < mat[i].length; j++)
				{
					matTemp[i][j] = MatriceUtilitaires.getDeterminant(
							MatriceUtilitaires.getMatMineur(mat, i, j))
							* (int) Math.pow(-1, ((i + 1 + j + 1)));
				}
		}
		return matTemp;
	}

	/**
	 * Retourne la matrice N X N adjointe à partir d'une matrice N X N reçue.
	 *
	 * Est la matrice transposée des cofacteurs de la matrice d'origine.
	 *
	 * @param mat la matrice d'origine
	 *
	 * @return la matrice carrée N X N adjointe
	 */
	public static int[][] getMatAdjointe(int[][] mat)
	{
		return getMatTranspose(getMatCofacteurs(mat));
	}

	/**
	 * Calcule le déterminant inverse du chiffre de Hill selon la force brute.
	 *
	 * @param valDet déterminant de la matrice d'origine
	 * @param valMod la valeur du modulo pour le calcul
	 *
	 * @return la valeur du déterminant inverse ou 0
	 */
	public static int getDeterminantInverseHill(int valDet, int valMod)
	{
		int detInv = 0;
		SortedSet<Integer> premierEntreEux = MathUtilitaires.xPremierEntreEux(0,
				valMod);

		Iterator<Integer> it = premierEntreEux.iterator();
		Integer i = 0;

		while (it.hasNext() && (detInv == 0))
		{
			i = it.next();
			if (MathUtilitaires.modulo((valDet * i), valMod) == 1)
			{
				detInv = i;
			}
		}

		return detInv;
	}

}
