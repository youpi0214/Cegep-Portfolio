package formes;

import java.util.ArrayList;
import exceptions.FormeException;

/**
 * Cette classe permet de créer des vecteurs et de les manipuler
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public class VecteurFormes implements ManipulerVecteur
{

	/**
	 * Vecteur de formes
	 */
	private ArrayList<Forme> vecteur;

	/**
	 * Instancie un vecteur vide
	 */
	public VecteurFormes()
	{
		this.vecteur = new ArrayList<>();
	}

	/**
	 * Vérifie si le ne nombre de forme est supérieur à zéro
	 * 
	 * @param pValeur le nombre de forme
	 * @return vrai si le nombre de forme est valide
	 */
	private static boolean validerNbrFormes(int pValeur)
	{
		return pValeur > 0;
	}

	/**
	 * Retourne le vecteur contenant les formes
	 * 
	 * @return le vecteur de formes
	 */
	@Override
	public ArrayList<Forme> getVecteur()
	{
		return this.vecteur;
	}

	/**
	 * Rempli un vecteur en créant les fomres selon le nombre de forme reçu
	 * 
	 * @param nbrFormes le nombre de formes à construire
	 */
	@Override
	public void remplir(int nbrFormes)
	{
		if (validerNbrFormes(nbrFormes))
		{
			int i = 0;

			while (i < nbrFormes)
			{
				for (Couleur c : Couleur.values())
				{
					int b = 0;
					while (i < nbrFormes && b < 3)
					{
						if (b == 0 && i < nbrFormes)
						{
							try
							{
								Cercle ce = new Cercle(5);
								vecteur.add(ce);
								ce.setCouleur(c);
								i++;
								b++;
							}
							catch (FormeException e)
							{
								System.err.println("forme invalide");
							}
						}
						else if (b == 1 && i < nbrFormes)
						{
							try
							{
								Rectangle re = new Rectangle(5, 5);
								vecteur.add(re);
								re.setCouleur(c);
								i++;
								b++;
							}
							catch (FormeException e)
							{
								System.err.println("forme invalide");
							}
						}
						else if (b == 2 && i < nbrFormes)
						{
							try
							{
								Triangle tri = new Triangle(5, 5, 5);
								vecteur.add(tri);
								tri.setCouleur(c);
								i++;
								b++;
							}
							catch (FormeException e)
							{
								System.err.println("forme invalide");
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Tri le vecteur de formes selon la forme et la couleur de celui-ci
	 */
	@Override
	public void trier()
	{
		ArrayList<Forme> v = getVecteur();

		// déclaration des variables
		boolean permut = false;
		int courant = (v.size() - 2);

		// pour chaque passe, tri à bulles amélioré
		do
		{
			permut = false;

			for (int i = 0; i <= courant; i++)
			{
				if (v.get(i).compareTo(v.get(i + 1)) > 0)
				{
					// permutation des valeurs
					permuter(i, (i + 1));
					permut = true;
				}
			}
			courant--;

		}
		while (permut == true);

	}

	/**
	 * S'assure que le plus d'éléments possibles du vecteur subissent un
	 * changement de position
	 */
	@Override
	public void melanger()
	{
		if (this.vecteur != null)
		{
			int taille = getVecteur().size();
			int index = 0;
			for (int i = 0; i < taille * 2; i++)
			{
				index = (index == taille) ? 0 : index;
				int random = (int) (Math.random() * taille);
				permuter(index, random);
				index++;
			}
		}
	}

	/**
	 * Permet d'interpositionner deux élément l'un à la position de l'autre
	 * 
	 * @param pIndex1 emplacement de l'élément 1
	 * @param pIndex2 emplacement de l'élément 2
	 */
	private void permuter(int pIndex1, int pIndex2)
	{
		Forme temp = this.vecteur.get(pIndex1);
		this.vecteur.set(pIndex1, vecteur.get(pIndex2));
		this.vecteur.set(pIndex2, temp);
	}

	/**
	 * 
	 * @return une chaîne de caractères résumant tout les formes contenues dans
	 *         le vecteur de formes
	 */
	@Override
	public String toString()
	{
		String vecResume = "";
		if (this.vecteur != null)
		{
			for (Forme f : this.vecteur)
			{
				vecResume += f.toStringCourt() + "\n";
			}
		}

		return vecResume;
	}
}