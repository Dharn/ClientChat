package View;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import com.sun.glass.events.WindowEvent;

import dao.*;
import dao.DAOMessage;
import model.Message;
import model.Salon;
import model.Utilisateur;

public class ViewSalon extends JFrame implements ActionListener, KeyListener {

	private Connection myConnection;

	private JPanel Panel1 = new JPanel();
	private JPanel PanelNorth = new JPanel();

	private JPanel PanelCenter = new JPanel();

	private JPanel PanelSouth = new JPanel();
	private JPanel PanelSouthNorth = new JPanel();
	private JPanel PanelSouthCenter = new JPanel();
	private JPanel PanelSouthSouth = new JPanel();

	private JLabel labelNomSalon = new JLabel("");
	private JLabel labelConnecteesAuSalon = new JLabel("Connectée(s) à ce salon : ");

	private JTextArea textAreaDiscussion = new JTextArea();
	private JTextArea textAreaMessageToSend = new JTextArea();

	private JButton buttonSend = new JButton(">");
	private JButton buttonDeleteSalon = new JButton("Détruire ce salon");

	private List ListConnectees = new List();

	private String nomDuSalon;
	private Salon monSalon;
	private Utilisateur monUtilisateur;

	private ArrayList<Message> listeDesMessages = new ArrayList<Message>();
	private ArrayList<Message> listeDesMessagesNEW = new ArrayList<Message>();

	private Timer tRefresh;

	public ViewSalon(Salon monSalon, Connection myConnection, Utilisateur u) {
		this.monSalon = monSalon;
		this.myConnection = myConnection;
		this.monUtilisateur = u;
		tRefresh = new Timer(100, this);
		tRefresh.start();
		initialyse();

	}

	public void initialyse() {

		this.setLayout(new GridLayout());

		this.Panel1.setLayout(new BorderLayout());
		this.Panel1.setBorder(BorderFactory.createTitledBorder("Salon : " + this.nomDuSalon));

		this.PanelNorth.setLayout(new GridLayout(1, 2));

		this.PanelCenter.setLayout(new BorderLayout());
		this.PanelCenter.setBorder(BorderFactory.createTitledBorder("Fil de discussion"));

		this.PanelSouth.setLayout(new FlowLayout());

		// ajout des components dans les panels
		this.add(this.Panel1);

		this.Panel1.add(this.PanelNorth, BorderLayout.NORTH);

		if (isAdmin(this.monSalon, this.monUtilisateur)) {
			this.PanelNorth.add(buttonDeleteSalon);
		}

		this.Panel1.add(this.PanelCenter, BorderLayout.CENTER);
		this.PanelCenter.add(this.textAreaDiscussion);
		this.textAreaDiscussion.setEditable(false);
		JScrollPane scrollDiscussion = new JScrollPane(textAreaDiscussion);
		scrollDiscussion.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PanelCenter.add(scrollDiscussion);
		this.Panel1.add(this.PanelSouth, BorderLayout.SOUTH);

		this.PanelSouth.add(this.textAreaMessageToSend);
		this.textAreaMessageToSend.setMinimumSize(new Dimension(150, 20));
		this.textAreaMessageToSend.setColumns(22);
		this.textAreaMessageToSend.setRows(3);
		JScrollPane scroll = new JScrollPane(textAreaMessageToSend);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		PanelSouth.add(scroll);

		this.PanelSouth.add(this.buttonSend);

		// add wrap and scroll

		// On ajoute les évènements sur les boutons
		this.buttonSend.addActionListener(this);
		this.buttonDeleteSalon.addActionListener(this);
		this.textAreaMessageToSend.addKeyListener(this);
		// set placeholder

		// Affichage de la fenêtre
		this.setSize(350, 450);
		this.setMinimumSize(new Dimension(350, 450));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(this.nomDuSalon);
		System.out.println("fenetre salon");

	}

	public boolean isAdmin(Salon s, Utilisateur u) {
		if (s.getCreateurId() == u.getId()) {
			return true;
		}
		return false;
	}

	public void refreshMessages() {
		System.out.println("refresh messages");
		DAOMessage daoMessage = new DAOMessage(myConnection);
		ArrayList<Message> conversation = new ArrayList<Message>();
		conversation = daoMessage.getAll();
		listeDesMessagesNEW = conversation;
		for (Message mNew : listeDesMessagesNEW) {
			boolean estPresent = false;
			for (Message m : listeDesMessages) {
				if (mNew.getId() == m.getId()) {
					estPresent = true;
				}
			}
			if (!estPresent) {
				this.textAreaDiscussion.append(mNew.getMessage());
				this.listeDesMessages.add(mNew);
			}

		}
	}

	public void onButtonSend() {
		if (!(this.textAreaMessageToSend.getText().isEmpty()) && !(this.textAreaMessageToSend.getText().equals("\n"))) {

			String monMessageStr = this.textAreaMessageToSend.getText();
			Message monMessage = new Message(this.monUtilisateur.getId(), monMessageStr, this.monSalon.getId());

			DAOMessage daoMessage = new DAOMessage(myConnection);
			this.textAreaMessageToSend.setText("");
			try {
				daoMessage.Envoyer(monMessage, this.monSalon, this.monUtilisateur);
			} catch (Exception e) {
			}
		}
		this.textAreaMessageToSend.setText("");

	}

	public void onButtonDetruireSalon() {
		DAOSalon daoSalon = new DAOSalon(myConnection);
		daoSalon.supprimerSalon(this.monSalon);
		tRefresh.stop();
		this.setVisible(false);
		this.dispose();

		}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
			if (arg0.getSource() == this.textAreaMessageToSend) {
				onButtonSend();
			}

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.buttonSend) {
			this.textAreaMessageToSend.append("\n");
			;
			onButtonSend();
		}
		if (e.getSource() == this.tRefresh) {
			refreshMessages();
		}
		if (e.getSource() == this.buttonDeleteSalon) {
			onButtonDetruireSalon();
		}

	}

}
