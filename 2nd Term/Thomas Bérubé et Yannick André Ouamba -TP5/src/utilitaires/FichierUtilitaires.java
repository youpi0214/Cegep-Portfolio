package utilitaires;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/**
 * Classe utilitaires pour la gestion de fichiers
 *
 * @author Thomas Bérubé et Yannick André Ouamba
 */
public class FichierUtilitaires
{

	/**
	 * Enregistrer un message (une chaîne) dans un fichier (PrintWriter)
	 *
	 * @param message: le message à enregistrer
	 * @param nomFichier: un objet File, le fichier dans lequel il faut
	 *            enregistrer le message
	 *
	 * @return vrai si le message a été enregistré.
	 */
	public static boolean enregistrerMessage(String message, File nomFichier)
	{
		boolean succes = true;
		try
		{
			PrintWriter sortie = new PrintWriter(new FileWriter(nomFichier));
			sortie.println(message);

			sortie.flush();
			sortie.close();
		}
		catch (IOException e)
		{
			succes = false;
		}

		return succes;
	}

	/**
	 * Lire seulement la première ligne (une chaîne) du fichier reçu
	 *
	 * @param nomFichier, un File dans lequel lire.
	 *
	 * @return la ligne lue
	 */
	public static String lireMessage(File nomFichier)
	{
		String ligneLu = "";
		try
		{
			FileReader fluxSource = new FileReader(nomFichier);
			BufferedReader bufferSource = new BufferedReader(fluxSource);
			try
			{
				ligneLu = bufferSource.readLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			bufferSource.close();
		}
		catch (IOException e)
		{
			ligneLu = null;
		}
		return ligneLu;
	}

	/**
	 * Lire un fichier de mots. Chaque mot est sur une ligne différente. Chaque
	 * mot doit être mis en minuscule et on doit enlever les espaces de chaque
	 * bout (voir la classe String). Il faut aussi éliminer les doublons.
	 *
	 * @param nomFichier le nom du fichier dictionnaire
	 *
	 * @return un SortedSet des mots du dictionnaire ou null s'il n'y a pas de
	 *         mot dans le fichier.
	 */
	public static SortedSet<String> lireDictionnaire(File nomDic)
	{
		SortedSet<String> dic = new TreeSet<>();
		String ligneLu = "";
		try
		{
			FileReader fluxSource = new FileReader(nomDic);
			BufferedReader bufferSource = new BufferedReader(fluxSource);
			ligneLu = bufferSource.readLine();
			while (ligneLu != null)
			{
				dic.add(ligneLu.trim().toLowerCase());
				ligneLu = bufferSource.readLine();
			}
			bufferSource.close();
		}
		catch (IOException e)
		{
		}
		return (dic.size() != 0 && !dic.isEmpty()) ? dic : null;
	}

	/**
	 * Obtenir le nom du fichier de l'utilisateur à partir d'une boîte de
	 * dialogue graphique.
	 * 
	 * @param option: le nom du bouton principal
	 * 
	 * @return un File, le fichier sélectionné ou null
	 */
	public static File obtenirNomFichier(String option)
	{
		File f = null;
		JFileChooser chooser = new JFileChooser(".");

		if (chooser.showDialog(null, option) == JFileChooser.APPROVE_OPTION)
			f = chooser.getSelectedFile();

		return f;
	}

	/**
	 * Obtenir le nom du fichier de l'utilisateur à partir d'une boîte de
	 * dialogue graphique.
	 *
	 * @param option: le nom du bouton principal
	 * @param le nom du fichier pré-sélectionné
	 *
	 * @return un File, le fichier sélectionné ou null
	 */
	public static File obtenirNomFichier(String option, File fichier)
	{
		File f = null;
		JFileChooser chooser = new JFileChooser(".");
		chooser.setSelectedFile(fichier);

		if (chooser.showDialog(null, option) == JFileChooser.APPROVE_OPTION)
			f = chooser.getSelectedFile();

		return f;
	}
}
