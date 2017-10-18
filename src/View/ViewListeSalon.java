package View;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import dao.*;
import javafx.scene.layout.Border;
import model.*;

public class ViewListeSalon extends JFrame implements ActionListener, KeyListener, MouseListener, ItemListener {
	
	private Connection myConnection;
	private Utilisateur utilisateur = null;

	private JPanel PanelNorth = new JPanel();
	private JPanel PanelNorthLeft = new JPanel();
	private JPanel PanelNorthRight = new JPanel();

	private JPanel PanelNorthSecond = new JPanel();

	private JPanel PanelCenter = new JPanel();
	private JPanel PanelCenterNorth = new JPanel();
	private JPanel PanelCenterCenter = new JPanel();

	private JPanel PanelSouth = new JPanel();
	private JPanel PanelSouthNorth = new JPanel();
	private JPanel PanelSouthNorthLeft = new JPanel();
	private JPanel PanelSouthNorthRight = new JPanel();
	private JPanel PanelSouthCenter = new JPanel();
	private JPanel PanelSouthSouth = new JPanel();

	private JLabel labelListeSalon = new JLabel("Liste des salons :");

	private JLabel labelInfo = new JLabel("Pr�t !");

	private PlaceholderTextField textFieldPseudo = new PlaceholderTextField("");
	private PlaceholderTextField textFieldMdp = new PlaceholderTextField("");
	private PlaceholderTextField textFieldNomSalon = new PlaceholderTextField("");
	private PlaceholderTextField textFieldMdpSalon = new PlaceholderTextField("");

	private JButton buttonConnect = new JButton("Se Connecter");
	private JButton buttonRefresh = new JButton("Raffraichir");
	private JButton buttonCreateSalon = new JButton("Cr�er le salon");
	private JButton buttonDeconnecter = new JButton("Se d�connecter");
	private JButton buttonCreerUtilisateur = new JButton("cr�er un utilisateur");

	private List ListSalon = new List();
	private List ListConnectees = new List();

	public ViewListeSalon(Connection myConnection) {
		this.myConnection = myConnection;
		initialyse();
	}

	public void initialyse() {
		// gestion des panels et layouts
		this.setLayout(new BorderLayout());
		this.PanelNorth.setLayout(new GridLayout());
		this.PanelNorth.setBorder(BorderFactory.createTitledBorder("Connexion"));
		this.PanelNorthLeft.setLayout(new GridLayout(2, 1));
		this.PanelNorthRight.setLayout(new GridLayout(2,1));

		this.PanelNorthSecond.setLayout(new GridLayout());
		this.PanelNorthSecond.setBorder(BorderFactory.createTitledBorder("D�connexion"));

		this.PanelCenter.setLayout(new BorderLayout());
		this.PanelCenter.setBorder(BorderFactory.createTitledBorder("Salons"));
		this.PanelCenterNorth.setLayout(new GridLayout(1, 2));
		this.PanelCenterCenter.setLayout(new GridLayout());

		this.PanelSouth.setLayout(new BorderLayout());

		this.PanelSouthNorth.setLayout(new GridLayout(1, 2));
		this.PanelSouthNorth.setBorder(BorderFactory.createTitledBorder("Cr�ation d'un salon"));
		this.PanelSouthNorthLeft.setLayout(new GridLayout(2, 1));
		this.PanelSouthNorthRight.setLayout(new FlowLayout());
		this.PanelSouthCenter.setLayout(new GridLayout());
		this.PanelSouthSouth.setLayout(new BorderLayout());

		// ajout des components dans les panels

		this.add(this.PanelNorth, BorderLayout.NORTH);

		this.PanelNorth.add(this.PanelNorthLeft, BorderLayout.WEST);
		this.PanelNorth.add(this.PanelNorthRight, BorderLayout.EAST);
		this.PanelNorthLeft.add(this.textFieldPseudo);
		this.PanelNorthLeft.add(this.textFieldMdp);
		this.PanelNorthRight.add(this.buttonConnect);
		this.PanelNorthRight.add(this.buttonCreerUtilisateur);

		this.PanelNorthSecond.add(this.buttonDeconnecter);

		this.add(this.PanelCenter, BorderLayout.CENTER);
		this.PanelCenter.add(this.PanelCenterNorth, BorderLayout.NORTH);
		this.PanelCenterNorth.add(this.labelListeSalon);
		this.PanelCenterNorth.add(this.buttonRefresh);
		this.PanelCenter.add(this.PanelCenterCenter, BorderLayout.CENTER);
		this.PanelCenterCenter.add(this.ListSalon);

		this.add(this.PanelSouth, BorderLayout.SOUTH);

		this.PanelSouth.add(this.PanelSouthNorth, BorderLayout.NORTH);
		this.PanelSouthNorth.add(this.PanelSouthNorthLeft, BorderLayout.WEST);

		this.PanelSouthNorthLeft.add(this.textFieldNomSalon);
		this.PanelSouthNorthLeft.add(this.textFieldMdpSalon);
		this.PanelSouthNorth.add(this.PanelSouthNorthRight, BorderLayout.EAST);
		this.PanelSouthNorthRight.add(this.buttonCreateSalon);
		this.PanelSouth.add(this.PanelSouthCenter, BorderLayout.CENTER);
		this.PanelSouthCenter.add(this.ListConnectees);

		this.PanelSouth.add(this.PanelSouthSouth, BorderLayout.SOUTH);
		this.PanelSouthSouth.add(this.labelInfo);
		// add wrap and scroll

		// On ajoute les �v�nements sur les boutons
		this.buttonConnect.addActionListener(this);
		this.buttonCreerUtilisateur.addActionListener(this);
		this.buttonDeconnecter.addActionListener(this);
		this.buttonCreateSalon.addActionListener(this);
		this.buttonRefresh.addActionListener(this);
		this.textFieldMdp.addKeyListener(this);
		this.textFieldMdpSalon.addKeyListener(this);
		this.ListSalon.addItemListener(this);
		this.ListSalon.addMouseListener(this);

		// set placeholder
		final Font f = textFieldPseudo.getFont();

		textFieldPseudo.setColumns(7);
		textFieldPseudo.setPlaceholder("Pseudo");
		textFieldPseudo.setFont(new Font(f.getName(), f.getStyle(), 12));

		textFieldMdp.setColumns(7);
		textFieldMdp.setPlaceholder("Mot de passe");
		textFieldMdp.setFont(new Font(f.getName(), f.getStyle(), 12));

		textFieldNomSalon.setColumns(7);
		textFieldNomSalon.setPlaceholder("Nom du salon");
		textFieldNomSalon.setFont(new Font(f.getName(), f.getStyle(), 12));

		textFieldMdpSalon.setColumns(7);
		textFieldMdpSalon.setPlaceholder("Mot de passe");
		textFieldMdpSalon.setFont(new Font(f.getName(), f.getStyle(), 12));

		// Affichage de la fen�tre
		this.setSize(300, 500);
		this.setMinimumSize(new Dimension(300, 500));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Chat");

		// set invisible tant que pas connect�
		this.PanelCenter.setVisible(false);
		this.PanelSouthCenter.setVisible(false);
		this.PanelSouthNorth.setVisible(false);
		this.PanelSouthSouth.setVisible(true);
		this.PanelNorthSecond.setVisible(false);
		this.PanelNorth.setVisible(true);
		// pop up
		// JOptionPane.showMessageDialog(null, textFieldPseudo);

		// test interface
		onButtonRafraichir();
	}

	public void onButtonconnexion() {
		DAOUtilisateur DAOu = new DAOUtilisateur(myConnection);
		try {
			
			Utilisateur u = DAOu.getByPseudoAndMdp(this.textFieldPseudo.getText(), this.textFieldMdp.getText());
			this.textFieldMdp.setText("");
			if (u == null) {

				this.labelInfo.setText("Pseudo ou mot de passe incorrect.");
				
			} else {
				this.utilisateur = u;
				DAOu.Connexion(u);
				this.PanelCenter.setVisible(true);
				this.PanelSouthCenter.setVisible(true);
				this.PanelSouthNorth.setVisible(true);
				this.PanelSouthSouth.setVisible(true);
				this.PanelNorthSecond.setVisible(true);
				this.PanelNorth.setVisible(false);
				this.add(this.PanelNorthSecond, BorderLayout.NORTH);
			}

		} catch (Exception e) {
			this.labelInfo.setText("Erreur de requete sql.");
		}
		
	}

	public void onButtonDeconnection() {
		DAOUtilisateur DAOu = new DAOUtilisateur(myConnection);
		try {
			DAOu.Deconnexion(this.utilisateur);
			this.PanelCenter.setVisible(false);
			this.PanelSouthCenter.setVisible(false);
			this.PanelSouthNorth.setVisible(false);
			this.PanelSouthSouth.setVisible(true);
			this.PanelNorthSecond.setVisible(false);
			this.PanelNorth.setVisible(true);
			this.add(this.PanelNorth, BorderLayout.NORTH);
			this.utilisateur=null;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public void onButtonRafraichir() {
		DAOSalon daos = new DAOSalon(myConnection);
		ArrayList<Salon> salons = daos.getAll();
		this.ListSalon.removeAll();

		for (Salon salon : salons) {
			this.ListSalon.add(salon.getName());
		}

	}

	public void onButtonCreateSalon() {
		
		DAOSalon daos = new DAOSalon(myConnection);
		if (!(this.textFieldNomSalon.getText().equals(""))) {
			Salon s = new Salon(this.textFieldNomSalon.getText(), this.textFieldMdpSalon.getText(), utilisateur.getId());
			try {

				daos.insert(s, this.utilisateur);
				this.labelInfo.setText("Salon cr�e.");
				onButtonRafraichir();
			} catch (Exception e) {
				this.labelInfo.setText("impossible de cr�er le salon.");
			}
		}
		else {
			this.labelInfo.setText("impossible de cr�er le salon.");
		}
		
		

	}

	public void onConnectSalon() {

	}
	
	public void onButtonCreerUtilisateur(){
		DAOUtilisateur DAOu = new DAOUtilisateur(myConnection);
		try {

			Utilisateur u = new Utilisateur(this.textFieldPseudo.getText(), this.textFieldMdp.getText(), "");
			this.textFieldMdp.setText("");
			if (!(u.getPseudo().equals("")) &&  !(u.getMotDePasse().equals(""))) {
				

				int choixCreationUtilisateur = JOptionPane.showConfirmDialog(this,
						"Voulez vous cr�er un nouvelle utilisateur '"+u.getPseudo()+"' avec les donn�es fournies ? :",
						"Cr�ation", JOptionPane.YES_NO_OPTION);
				if (choixCreationUtilisateur == 0) {
					DAOu.insert(u);
					this.PanelCenter.setVisible(true);
					this.PanelSouthCenter.setVisible(true);
					this.PanelSouthNorth.setVisible(true);
					this.PanelSouthSouth.setVisible(true);
					this.PanelNorthSecond.setVisible(true);
					this.PanelNorth.setVisible(false);
					this.add(this.PanelNorthSecond, BorderLayout.NORTH);
					this.utilisateur = u;
					this.labelInfo.setText("Utilisateur cr�e.");
				}
			}

		} catch (Exception e) {
			this.labelInfo.setText("Impossible de cr�er l'utilisateur.");
		}
	}

	public void getConnectees() {
		DAOUtilisateur DAOu = new DAOUtilisateur(myConnection);
		try {
			ArrayList<Utilisateur> listu= DAOu.getAll();
			this.ListConnectees.removeAll();
			for (Utilisateur utilisateur : listu) {
				this.ListConnectees.add(utilisateur.getPseudo());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			if (e.getSource() == this.textFieldMdp) {
				onButtonconnexion();
			}
			if (e.getSource() == this.textFieldMdpSalon) {

			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonConnect) {
			onButtonconnexion();
		}
		if (e.getSource() == this.buttonDeconnecter) {
			onButtonDeconnection();
		}
		if (e.getSource() == this.buttonCreateSalon) {
			onButtonCreateSalon();
		}
		
		if (e.getSource() == this.buttonCreerUtilisateur) {
			onButtonCreerUtilisateur();
		}
		if (e.getSource()==this.buttonRefresh) {
			onButtonRafraichir();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if (arg0.getSource() == this.ListSalon) {
			String mdp = JOptionPane.showInputDialog(this,
					"entrez le mot de passe du salon " + this.ListSalon.getSelectedItem(),
					this.ListSalon.getSelectedItem(), JOptionPane.PLAIN_MESSAGE);
			if (true) {
				ViewSalon newsalon = new ViewSalon(this.ListSalon.getSelectedItem(), myConnection);
			}
		}

	}

}
