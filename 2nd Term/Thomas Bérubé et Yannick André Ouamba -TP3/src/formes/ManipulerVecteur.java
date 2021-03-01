package formes;

import java.util.ArrayList;

/**
 * Classe abstraite déterminant les caractéristique d'un Objet capable de
 * manipuler un vecteur
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public interface ManipulerVecteur
{
	public ArrayList<Forme> getVecteur();

	public void remplir(int pValeur);

	public void trier();

	public void melanger();
}