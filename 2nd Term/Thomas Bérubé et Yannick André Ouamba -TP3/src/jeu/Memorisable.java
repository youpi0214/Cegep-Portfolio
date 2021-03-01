package jeu;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Classe abstraite déterminant les caractéristique d'un Objet mémorisable
 * 
 * @version 2019
 * @author Thomas Bérubé et Yannick André Ouamba
 *
 */
public interface Memorisable
{
	public static int NIVEAU_MAX = 6;

	public ArrayList<Point> jouerOrdi();

	public boolean jouerHumain(int i, int j);

	public String getNomForme(int pValeur1, int pValeur2);

	public int getNiveau();

	public void setNiveauPlusUn();
}
