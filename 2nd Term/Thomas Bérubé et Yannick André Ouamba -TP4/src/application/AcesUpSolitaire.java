package application;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cartes.Carte;
import cartes.SorteCartes;
import cartes.ValeurCartes;
import cartes.PaquetDeCartes;
import cartes.Pioche;

/**
 * Application de jeu de Aces Up Solitaire
 *
 * @author Vos noms
 */
public class AcesUpSolitaire extends JFrame
{

	/**
	 * Chemin par défaut pour les JFileChooser
	 */
	public static final String CHEMIN_DEFAUT = ".";

	/**
	 * Nombre de colonnes de cartes
	 */
	public static final int NBR_COLONNES_DE_CARTES = 4;

	/**
	 * Tient le compte du nombre de fois que l'utilisateur triche, s'il triche
	 */
	private int nbTriche = 0;

	// Les menus
	private JMenuBar menuBar = new JMenuBar();

	// Menu jeu
	private JMenu jeu = new JMenu("Jeu");
	private JMenuItem nouveau = new JMenuItem("Nouveau jeu");
	private JMenuItem enregister = new JMenuItem("Enregistrer le jeu");
	private JMenuItem reprendre = new JMenuItem("Reprendre un jeu");
	private JMenuItem fermer = new JMenuItem("Fermer");
	private ActionMenu ecouteurMenu = new ActionMenu();

	// Les colonnes de cartes
	private JPanel paneListes = new JPanel(new GridLayout(1, 4));
	private JPanel[] paneListeCartes = new JPanel[AcesUpSolitaire.NBR_COLONNES_DE_CARTES];
	private JButton[] btnDeplacerListe = new JButton[AcesUpSolitaire.NBR_COLONNES_DE_CARTES];
	private JButton[] btnEnleverListe = new JButton[AcesUpSolitaire.NBR_COLONNES_DE_CARTES];
	private JEditorPane[] txtListeCartes = new JEditorPane[AcesUpSolitaire.NBR_COLONNES_DE_CARTES];
	private ActionSouris ecouteurSouris = new ActionSouris();
	private ActionBouton ecouteurBtn = new ActionBouton();

	// La pioche graphique
	private JPanel panePioche = new JPanel(
			new FlowLayout(FlowLayout.CENTER, 5, 5));
	private JButton btnPiger = new JButton("Piger");
	private JEditorPane txtPioche = new JEditorPane();

	// La pioche qui va recevoir le paquet de cartes
	Pioche pioche = null;

	// Les listes qui représentent les infos des colonnes de cartes
	List[] colonneCartes = new ArrayList[AcesUpSolitaire.NBR_COLONNES_DE_CARTES];

	/**
	 * Constructeur de l'application Aces Up Solitaire. Il met en place une
	 * interface simple
	 */
	public AcesUpSolitaire(PaquetDeCartes pPDC)
	{
		// Init de la fenêtre
		this.setTitle("Aces Up Solitaire");
		this.setSize(450, 550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Init des menus
		this.jeu.add(nouveau);
		this.jeu.add(enregister);
		this.jeu.add(reprendre);
		this.jeu.addSeparator();
		this.jeu.add(fermer);
		this.menuBar.add(jeu);

		// Ajout des écouteurs au menu jeu
		nouveau.addActionListener(ecouteurMenu);
		enregister.addActionListener(ecouteurMenu);
		reprendre.addActionListener(ecouteurMenu);
		fermer.addActionListener(ecouteurMenu);

		// Ajout du menu jeu à la barre de menus
		this.setJMenuBar(menuBar);

		// Mise en place des composants pour les colonnes de cartes (4 colonnes)
		// et les boutons (2 boutons sur chaque colonne)
		for (int i = 0; i < AcesUpSolitaire.NBR_COLONNES_DE_CARTES; i++)
		{
			txtListeCartes[i] = new JEditorPane();
			txtListeCartes[i].setPreferredSize(new Dimension(80, 325));
			txtListeCartes[i].setEditable(false);
			txtListeCartes[i].setContentType("text/html");
			txtListeCartes[i].addMouseListener(ecouteurSouris);

			btnEnleverListe[i] = new JButton("Enlever");
			btnEnleverListe[i].addActionListener(ecouteurBtn);
			btnDeplacerListe[i] = new JButton("Déplacer");
			btnDeplacerListe[i].addActionListener(ecouteurBtn);

			paneListeCartes[i] = new JPanel(
					new FlowLayout(FlowLayout.CENTER, 5, 5));
			paneListeCartes[i].add(txtListeCartes[i]);
			paneListeCartes[i].add(btnEnleverListe[i]);
			paneListeCartes[i].add(btnDeplacerListe[i]);

			paneListes.add(paneListeCartes[i]);
		}

		this.add(paneListes, BorderLayout.CENTER);

		// La pioche
		btnPiger.addActionListener(ecouteurBtn);
		panePioche.add(btnPiger);
		txtPioche.setPreferredSize(new Dimension(80, 80));
		txtPioche.setEditable(false);
		txtPioche.setContentType("text/html");
		panePioche.add(txtPioche);

		this.add(panePioche, BorderLayout.SOUTH);

		initPartie(pPDC);

		this.setVisible(true);
	}

	/**
	 * Retourne la colonne de cartes correspondant au numéro demandé en
	 * paramètre.
	 *
	 * @param index le numéro de la colonne souhaitée de 0 à 3
	 *
	 * @return un pointeur sur la colonne demandée ou null si le numéro n'est
	 *         pas bon.
	 */
	public List getColonneCartes(int index)
	{
		List retColonne;
		if (index >= 0 && index < NBR_COLONNES_DE_CARTES)
		{
			retColonne = colonneCartes[index];
		}
		else
		{
			retColonne = null;
		}
		return retColonne;
	}

	/**
	 * Compte le nombre de fois que le joueur a triché
	 */
	public void addTricher()
	{
		nbTriche++;
	}

	/**
	 * Initialiser la partie avec un paquet de cartes standard.
	 */
	public void initPartie()
	{
		initPartie(new PaquetDeCartes());
	}

	/**
	 * Permet d'instancier et d'initialiser les éléments de l'application comme:
	 *
	 * <pre>
	 * -	Le nombre de triches
	 * -	Le paquet de cartes
	 * -	la pioche
	 * -	vider les zones de texte pour l'affichage des premières cartes
	 * -	les colonnes de cartes en pigeant une carte par colonne
	 * -	dessiner la pioche
	 * </pre>
	 */
	public void initPartie(PaquetDeCartes pPDC)
	{
		nbTriche = 0;
		Carte carteTemp = null;
		pPDC.brasser();
		pioche = new Pioche(pPDC);

		for (int i = 0; i < AcesUpSolitaire.NBR_COLONNES_DE_CARTES; i++)
		{
			colonneCartes[i] = new ArrayList();

			// La pioche peut être vide
			if (!pioche.isEmpty())
			{
				carteTemp = pioche.piger();
				carteTemp.setVisible(true);
				colonneCartes[i].add(0, carteTemp);
			}

			dessinerListeCartes(i);
		}

		dessinerPioche();
	}

	/**
	 * Permet de dessiner (d'afficher) les cartes dans les zones texte de cartes
	 * selon la structure de données associée
	 *
	 * @param noColonne, le numéro de la colonne de cartes à dessiner
	 */
	public void dessinerListeCartes(int noColonne)
	{
		Carte carteTemp = null;
		String txtHtml = "<center>";

		txtListeCartes[noColonne].setText("");

		for (int i = (colonneCartes[noColonne].size() - 1); i >= 0; i--)
		{
			carteTemp = (Carte) colonneCartes[noColonne].get(i);
			if (carteTemp.getSorte().equals(SorteCartes.COEUR)
					|| carteTemp.getSorte().equals(SorteCartes.CARREAU))
			{
				txtHtml = txtHtml + "<font size='5' color='red'>"
						+ carteTemp.toStringCarte() + "</font><br/>";
			}
			else
			{
				txtHtml = txtHtml + "<font size='5' color='black'>"
						+ carteTemp.toStringCarte() + "</font><br/>";
			}
		}

		txtHtml += "</center>";

		txtListeCartes[noColonne].setText(txtHtml);
	}

	/**
	 * Permet d'afficher le nombre de cartes restantes dans la pioche.
	 */
	public void dessinerPioche()
	{
		String txtHtml = "<center><font size='22' color='blue'>";

		if (!pioche.isEmpty())
		{
			txtHtml = txtHtml + "" + pioche.size() + "<br/>";
		}

		txtHtml += "</font></center>";

		txtPioche.setText(txtHtml);
	}

	/**
	 * Classe interne qui permet de faire l'écoute des options de menu
	 *
	 * @author Vos noms
	 */
	private class ActionMenu implements ActionListener
	{
		/**
		 * Méthode invoquée lorsqu'une option de menu est cliquée
		 *
		 * @param pAE , pointeur d'événement
		 */
		public void actionPerformed(ActionEvent pAE)
		{
			if (pAE.getSource() == nouveau)
			{
				gestionNouveauJeu();
			}
			else if (pAE.getSource() == enregister)
			{
				gestionEnregistrerJeu();
			}
			else if (pAE.getSource() == reprendre)
			{
				gestionReprendreJeu();
			}
			else if (pAE.getSource() == fermer)
			{
				gestionFermer();
			}
		}
	}

	/**
	 * Classe interne qui permet de faire l'écoute des boutons
	 *
	 * @author Vos noms
	 */
	private class ActionBouton implements ActionListener
	{
		/**
		 * Méthode invoquée lorsqu'un bouton est cliqué
		 *
		 * @param pAE , un pointeur d'événement
		 */
		public void actionPerformed(ActionEvent pAE)
		{
			JButton btn = (JButton) pAE.getSource();

			if (btn == btnPiger)
			{
				gestionPiger();
			}
			else
			{
				// Boutons enlever
				for (int i = 0; i < btnEnleverListe.length; i++)
				{
					if (btn == btnEnleverListe[i])
					{
						gestionEnleverListe(i);
					}
				}

				// Bouton déplacer
				for (int i = 0; i < btnDeplacerListe.length; i++)
				{
					if (btn == btnDeplacerListe[i])
					{
						gestionDeplacerListe(i);
					}
				}
			}

			gestionFinPartie();
		}
	}

	/**
	 * Classe interne qui permet de faire l'écoute des évènements de la souris
	 *
	 * @author Vos noms
	 */
	private class ActionSouris extends MouseAdapter
	{
		/**
		 * Méthode invoquée lorsqu'un évènement souris arrive
		 *
		 * @param pME pointeur d'événement
		 */
		public void mouseClicked(MouseEvent pME)
		{
			if (pME.getSource() instanceof JEditorPane)
			{
				if (pME.getButton() == MouseEvent.BUTTON1)
				{
					for (int i = 0; i < txtListeCartes.length; i++)
					{
						if (pME.getSource() == txtListeCartes[i])
						{
							gestionEnleverListe(i);
						}
					}
				}
				else
				{
					if (pME.getButton() == MouseEvent.BUTTON3)
					{
						for (int i = 0; i < txtListeCartes.length; i++)
						{
							if (pME.getSource() == txtListeCartes[i])
							{
								gestionDeplacerListe(i);
							}
						}
					}
				}
			}

			if (pME.getButton() == MouseEvent.BUTTON2)
			{
				gestionPiger();
			}

			gestionFinPartie();
		}
	}

	/**
	 * Permet de commencer une nouvelle partie
	 */
	public void gestionNouveauJeu()
	{
		initPartie(new PaquetDeCartes());
	}

	/**
	 * Permet d'enregistrer une partie en cours. L'enregistrement des données
	 * devrait être fait par sérialisation. Cette fonction fait de l'affichage
	 * de messages
	 */
	public void gestionEnregistrerJeu()
	{
		JFileChooser jFC = new JFileChooser(AcesUpSolitaire.CHEMIN_DEFAUT);
		int ok = jFC.showSaveDialog(this);

		if (ok == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				enregistrerInfoPartie(jFC.getSelectedFile());
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(this,
						"Erreur: Il n'est pas possible d'enregistrer les données du jeu.",
						"Enregistrement de la partie en cours",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Permet de lire un fichier qui contient les informations au sujet d'une
	 * partie. En plus de lire les données, elle remplit et dessine les
	 * composants
	 */
	public void gestionReprendreJeu()
	{
		JFileChooser jFC = new JFileChooser(AcesUpSolitaire.CHEMIN_DEFAUT);
		int ok = jFC.showOpenDialog(this);

		if (ok == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				lireInfoPartie(jFC.getSelectedFile());
			}
			catch (IOException e)
			{
				JOptionPane.showMessageDialog(this,
						"Erreur: Il n'est pas possible de lire les données du jeu.",
						"Reprendre une partie", JOptionPane.ERROR_MESSAGE);

			}
			catch (ClassNotFoundException e)
			{
				JOptionPane.showMessageDialog(this,
						"Erreur: Il n'est pas possible de lire les données du jeu.",
						"Reprendre une partie", JOptionPane.ERROR_MESSAGE);
			}
		}

		// Dessiner les composants du jeu
		for (int i = 0; i < colonneCartes.length; i++)
		{
			dessinerListeCartes(i);
		}

		dessinerPioche();
	}

	/**
	 * Permet de faire la gestion de l'option Fermer du menu
	 */
	public void gestionFermer()
	{
		System.exit(0);
	}

	/**
	 * Permet de déplacer, selon les règles du jeu, une carte de la colonne de
	 * cartes dont l'index de la colonne est reçu en entrée. La dernière carte
	 * de cette colonne est placée dans la première colonne vide en partant de
	 * la gauche.
	 *
	 * Il faut penser à redessiner (réafficher) les deux listes touchées s'il y
	 * a lieu
	 *
	 * @param indexColonne, le numéro de la colonne d'où on veut déplacer la
	 *            carte.
	 */
	public void gestionDeplacerListe(int indexColonne)
	{
		if (!((ArrayList<Carte>) getColonneCartes(indexColonne)).isEmpty())
		{
			for (int i = 0; i < NBR_COLONNES_DE_CARTES; i++)
			{
				boolean changement = false;

				if (getColonneCartes(i).isEmpty()
						&& ((ArrayList<Carte>) getColonneCartes(indexColonne))
								.size() != 1
						&& !changement)
				{
					((ArrayList<Carte>) getColonneCartes(i))
							.add(((ArrayList<Carte>) this
									.getColonneCartes(indexColonne)).remove(0));
					dessinerListeCartes(i);
					dessinerListeCartes(indexColonne);
					changement = true;
				}
			}
		}

	}

	/**
	 * Permet d'enlever, selon les règles du jeu, une carte de la colonne de
	 * cartes dont l'index de la colonne de carte est reçu en entrée.
	 *
	 * Faire attention, s'il ne reste que 2 cartes de la même sorte dans la
	 * colonne, et que celles-ci sont inférieures à une autre de la même sorte
	 * dans une autre colonne, les 2 sont enlevées et la colonne se libère.
	 *
	 * Il faut penser à redessiner (réafficher) la liste touchée s'il y a lieu
	 *
	 * @param pNoListe, le numéro de la colonne d'où on veut enlever la carte.
	 */
	// TODO Complétez le code de la méthode : gestionEnleverListe
	public void gestionEnleverListe(int pNoListe)
	{
		// si la liste n'est pas vide
		if (!((ArrayList<Carte>) getColonneCartes(pNoListe)).isEmpty())
		{
			Carte temp = ((ArrayList<Carte>) getColonneCartes(pNoListe)).get(0);
			// nbr passages = 2 si meme sorte sinon 1
			int nbrPasses = getColonneCartes(pNoListe).size() == 2
					&& temp.getSorte().equals(
							((ArrayList<Carte>) getColonneCartes(pNoListe))
									.get(1).getSorte()) ? 2 : 1;
			while (nbrPasses > 0)
			{
				temp = ((ArrayList<Carte>) getColonneCartes(pNoListe)).get(0);
				// si la carte a enlevé n'est pas un as
				if (!temp.getValeur().equals(ValeurCartes.V_AS))
				{
					boolean ok = false;
					// pour toutes les colonne
					for (int i = 0; i < NBR_COLONNES_DE_CARTES && !ok; i++)
					{
						// si la colonne n'est pas vide et si la carte de la
						// pile i
						// est plus grande et de même sorte
						// que la carte que l'on veut enlever ou s'il y a un as
						// de
						// même sorte sur le dessus d'une autre pile
						if (!((ArrayList<Carte>) getColonneCartes(i)).isEmpty()
								&& ((((ArrayList<Carte>) getColonneCartes(i))
										.get(0).getValeur()
										.compareTo(temp.getValeur()) > 0
										&& ((ArrayList<Carte>) getColonneCartes(
												i)).get(0).getSorte().equals(
														temp.getSorte()))
										|| (((ArrayList<Carte>) getColonneCartes(
												i)).get(0).compareTo(new Carte(
														ValeurCartes.V_AS,
														temp.getSorte())) == 0)))
						{
							((ArrayList<Carte>) getColonneCartes(pNoListe))
									.remove(0);
							ok = true;
						}

					}
					dessinerListeCartes(pNoListe);
				}
				nbrPasses--;
			}
		}
	}

	/**
	 * Permet de faire la gestion de l'action piger, dans la pioche. Il faut
	 * piger une carte pour chaque colonne de cartes.
	 *
	 * Il faut penser à redessiner (réafficher) chaque liste de cartes touchée
	 * s'il y a lieu.
	 */
	public void gestionPiger()
	{
		if (!pioche.isEmpty())
		{
			for (int i = 0; i < NBR_COLONNES_DE_CARTES; i++)
			{
				Carte temp = pioche.piger();
				temp.setVisible(true);
				((ArrayList<Carte>) getColonneCartes(i)).add(0, temp);
				dessinerListeCartes(i);
				dessinerPioche();
			}
		}
	}

	/**
	 * Permet de faire la gestion des messages à présenter au joueur si la
	 * partie est terminée parce qu'il a gagné ou qu'il ne peut plus jouer.
	 * Cette méthode affiche des messages. Elle permet également de tricher un
	 * peu.
	 */
	public void gestionFinPartie()
	{
		if (pioche.isEmpty())
		{
			if (partieGagner())
			{
				if (nbTriche == 0)
				{
					JOptionPane.showMessageDialog(AcesUpSolitaire.this,
							"Bravo! vous avez gagné honnêtement ;-)",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(AcesUpSolitaire.this,
							"Vous avez réussi ... en trichant " + nbTriche
									+ "X \n... À vaincre sans péril, on triomphe sans gloire!",
							"Information", JOptionPane.INFORMATION_MESSAGE);

				}
				// on recommence automatiquement
				initPartie();

			}
			else
			{
				if (partieTerminer())
				{
					Triche.finiOuTriche(AcesUpSolitaire.this);
				}
			}
		}
	}

	/**
	 * Permet de savoir, lorsque la pioche est vide, s'il y a une victoire. Donc
	 * qu'il y ait seulement 1 carte, un as, en haut de chacune des colonnes de
	 * cartes
	 *
	 * @return boolean, vrai si on a une victoire.
	 */
	public boolean partieGagner()
	{
		int fini = 0;
		for (List l : colonneCartes)
		{
			fini = (l.size() == 1
					&& ((Carte) l.get(0)).getValeur().equals(ValeurCartes.V_AS))
							? fini + 1
							: fini;
		}
		boolean gagner = fini == NBR_COLONNES_DE_CARTES;

		return gagner;
	}

	/**
	 * Permet de savoir, lorsque la pioche est vide, s'il est possible de jouer
	 * encore un coup ou si la partie est terminée.
	 *
	 * @return boolean, vrai s'il n'est pas possible de jouer un autre coup,
	 *         donc que la partie est terminée.
	 */
	public boolean partieTerminer()
	{
		Carte carteTemp = null;
		int compteSorte = 0;
		boolean colonneDeplacable = false;

		if (pioche.isEmpty())
		{
			// Vérifier si je peux encore enlever des cartes, trouver s'il y a
			// au moins une colonne vide et trouver si une colonne a plus d'une
			// carte, donc encore déplaçable.
			for (int i = 0; i < colonneCartes.length; i++)
			{
				if (!colonneCartes[i].isEmpty())
				{
					colonneDeplacable = (colonneDeplacable
							|| (colonneCartes[i].size() > 1));
					carteTemp = (Carte) colonneCartes[i].get(0);
					// Addition de bits (bitwise)
					compteSorte += Math.pow(2,
							(carteTemp.getSorte().ordinal()));
				}
			}
		}

		return (!(compteSorte != (Math.pow(2, colonneCartes.length) - 1))
				|| !colonneDeplacable);
	}

	/**
	 * Permet de faire l'enregistrement des infos d'une partie dans le fichier
	 * (File) reçu en paramètre. Les infos d'une partie sont les colonnes de
	 * cartes et la pioche. On utilise la sérialisation pour faire
	 * l'enregistrement de l'information.
	 *
	 * @param pCible , le fichier cible
	 *
	 * @throws IOException
	 */
	// TODO Complétez le code de la méthode : enregistrerInfoPartie
	public void enregistrerInfoPartie(File pCible) throws IOException
	{
		ObjectOutputStream fichObjet = null;

		try
		{
			fichObjet = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(pCible)));

			fichObjet.writeObject(pioche);
			for (int i = 0; i < NBR_COLONNES_DE_CARTES; i++)
			{
				fichObjet.writeObject(this.getColonneCartes(i));
			}

			fichObjet.flush();
			fichObjet.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}

	/**
	 * Permet de récupérer les infos d'une partie à partir du fichier (File)
	 * reçu en paramètre.
	 *
	 * @param pSource , le fichier source
	 *
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	// TODO Complétez le code de la méthode : lireInfoPartie
	public void lireInfoPartie(File pSource)
			throws IOException, ClassNotFoundException
	{
		Object o = null;
		ObjectInputStream fichObject = null;

		try
		{
			fichObject = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(pSource)));

			pioche = (Pioche) fichObject.readObject();

			for (int i = 0; i < colonneCartes.length
					&& (o = fichObject.readObject()) != null; i++)
			{
				colonneCartes[i] = (List<Carte>) o;
			}

			fichObject.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

	private static void partiePiper()
	{
		// Créer une liste de cartes pour les tests
		List listeCartes = new ArrayList();
		ValeurCartes[] valeur =
		{ ValeurCartes.V_AS, ValeurCartes.V_2, ValeurCartes.V_3 };

		for (int i = 0; i < valeur.length; i++)
		{
			for (SorteCartes sorte : SorteCartes.values())
			{
				listeCartes.add(0, new Carte(valeur[i], sorte));
			}
		}

		// Créer une instance de l'application pour les tests
		AcesUpSolitaire app = new AcesUpSolitaire(
				new PaquetDeCartes(listeCartes));
	}

	private static void vraiePartie()
	{
		// Créer une instance de l'application pour vraiment jouer
		AcesUpSolitaire app = new AcesUpSolitaire(new PaquetDeCartes());
	}

	/**
	 * Point d'entrée de l'appliaction
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{

		// Mode tests
		partiePiper();
		// Mode jeu
		// vraiePartie();
	}
}
