package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jeu.JeuMemoire;

/**
 * Interface graphique du jeu de mémoire Hiver 2014 Tp3 420-204
 * 
 * @author Julie.Freve
 */
public class VueMemoire extends JFrame
{

	protected JButton[][] boutons;
	protected JLabel titre, nbTrouve, nbReste;
	private GridLayout gridLayout;
	private BorderLayout borderLayout;

	public VueMemoire()
	{

		super("Souviens-toi ...");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		borderLayout = new BorderLayout(30, 30);
		gridLayout = new GridLayout(6, 6, 4, 2);
		JPanel panel1 = new JPanel(borderLayout);
		JPanel panel2 = new JPanel(new BorderLayout(30, 30));
		JPanel panel3 = new JPanel(gridLayout);

		titre = new JLabel();
		titre.setFont(new Font("Arial", Font.BOLD, 24));
		nbTrouve = new JLabel();
		nbReste = new JLabel();
		nbTrouve.setFont(new Font("Arial", Font.BOLD, 12));
		nbReste.setFont(new Font("Arial", Font.BOLD, 12));

		boutons = new JButton[JeuMemoire.LIGNE][JeuMemoire.COLONNE];
		for (int i = 0; i < JeuMemoire.LIGNE; i++)
		{
			for (int j = 0; j < JeuMemoire.COLONNE; j++)
			{
				boutons[i][j] = new JButton();
				boutons[i][j].setName("" + i + j);
				panel3.add(boutons[i][j]);
			}
		}

		panel2.add(nbTrouve, BorderLayout.WEST);
		panel2.add(nbReste, BorderLayout.EAST);

		panel1.add(titre, BorderLayout.NORTH);
		panel1.add(panel2, BorderLayout.SOUTH);
		panel1.add(panel3, BorderLayout.CENTER);
		getContentPane().add(panel1, BorderLayout.CENTER);

		setPreferredSize(new Dimension(600, 600));
		pack();
		setLocation((screenSize.width - this.getWidth()) / 2,
				(screenSize.height - this.getHeight()) / 2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args)
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new VueMemoire();
			}
		});
	}
}
