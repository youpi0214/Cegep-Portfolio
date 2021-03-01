package gui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import jeu.JeuMemoire;

/**
 * Contrôleur pour le jeu de mémoire TODO Ajuster le thread pour le jeu de
 * l'ordi
 * 
 * @author Julie.Freve
 */
public class ControleurMemoire implements ActionListener, Runnable
{

	private int essai;
	private VueMemoire vue;
	private boolean fini;
	private Thread t1;

	// TODO - Tp 3 - Ajouter une variable pour une instance de type "JeuMemoire"
	// du nom de "jeu". Voir le diagramme de classe
	private JeuMemoire jeu;

	public ControleurMemoire()
	{

		// TODO - Tp 3 - Instancier la variable "jeu"
		jeu = new JeuMemoire();
		vue = new VueMemoire();
		System.out.println(jeu.toString());

		ajouterEcouteur();
		mettreAJourNiveau();

		essai = 1;
		fini = true;
		t1 = new Thread(this);
		t1.start();
	}

	public static void bienvenu()
	{
		String sTitre = "Souviens-toi ...";
		String sMessage = "Éducation physique pour votre mémoire :o)"
				+ "\n\nLe but du jeu est de mémoriser les formes présentées par l'application\n"
				+ "et de les sélectionner dans le même ordre après.\n\n"
				+ "Si vous réussissez vous augmenterez de niveau et vous aurez plus de formes à mémoriser.\n\n"
				+ "Bonne chance et amusez-vous bien!...";

		JOptionPane.showMessageDialog(null, sMessage, sTitre,
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void actionPerformed(ActionEvent e)
	{
		JButton b = (JButton) e.getSource();

		verifierBouton(b);
	}

	private void ajouterEcouteur()
	{
		String image = "";
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				image = jeu.getNomForme(i, j);
				vue.boutons[j][i].addActionListener(this);
				vue.boutons[i][j]
						.setIcon(new ImageIcon("images\\" + image + ".png"));
			}
		}
	}

	private void verifierBouton(JButton b)
	{
		int x, y;
		int fin = 0;
		Point p;
		// Le niveau 1 a 3 caches, niveau 2 en a 4, etc. jusqu'à 7 qui en a 9
		// le joueur peut jouer jusqu'à ce qu'il ait réussi ou jusqu'à la
		// première erreur
		if (fin == 0 && essai <= jeu.getNiveau() + 2)
		{
			x = Integer.parseInt(("" + b.getName().charAt(0)));
			y = Integer.parseInt(("" + b.getName().charAt(1)));
			vue.boutons[x][y].setBackground(Color.DARK_GRAY);
			// on a réussi on passe à la cache suivante

			if (jeu.jouerHumain(x, y))
			{
				vue.nbTrouve.setText(" Nombre de formes trouvées :  " + essai);
				essai++;
				if (essai > jeu.getNiveau() + 2)
					fin = 2;
			}

			else
				fin = 1;
		}

		// réussite ou échec, on recommence
		if (fin == 1 || fin == 2)
		{
			retirerEcouteur();
			essai = 1;
			fini = true;
			if (fin == 1)
				JOptionPane.showMessageDialog(null,
						"Erreur, on recommence ce niveau");
			else
			{
				JOptionPane.showMessageDialog(null, "On change de niveau");
				jeu.setNiveauPlusUn();
			}
			mettreAJourNiveau();
			t1 = new Thread(this);
			t1.start();
		}
	}

	private void retirerEcouteur()
	{
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				vue.boutons[i][j].removeActionListener(this);
			}
		}
	}

	private void retirerFond()
	{
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				vue.boutons[i][j].setBackground(null);
			}
		}
	}

	private void mettreAJourNiveau()
	{
		vue.titre.setText("Niveau " + jeu.getNiveau());
		vue.nbTrouve.setText(" Nombre de formes trouvées :  0");
		vue.nbReste.setText(
				"Nombre de formes à trouver : " + (jeu.getNiveau() + 2));
	}

	private void faireJouerOrdi()
	{
		ArrayList<Point> points;

		retirerEcouteur();
		retirerFond();
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		points = jeu.jouerOrdi();

		try
		{
			for (Point p : points)
			{
				vue.boutons[p.x][p.y].setBackground(Color.DARK_GRAY);
				// System.out.println(p);
				try
				{
					Thread.sleep(800);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				vue.boutons[p.x][p.y].setBackground(null);

				try
				{
					Thread.sleep(800);
				}
				catch (InterruptedException e)
				{
				}
			}
		}
		catch (Exception e)
		{
		}
		this.ajouterEcouteur();
		// System.out.println("\n\n");
	}

	public void run()
	{
		faireJouerOrdi();
		fini = false;
	}

	public static void main(String[] args)
	{
		bienvenu();
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new ControleurMemoire();
			}
		});
	}
}
