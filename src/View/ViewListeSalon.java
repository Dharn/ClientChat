package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javafx.scene.layout.Border;

public class ViewListeSalon extends JFrame {

	private JPanel PanelNorth = new JPanel();

	private JPanel PanelCenter = new JPanel();
	private JPanel PanelCenterNorth = new JPanel();
	private JPanel PanelCenterCenter = new JPanel();

	private JPanel PanelSouth = new JPanel();
	private JPanel PanelSouthNorth = new JPanel();
	private JPanel PanelSouthNorthLeft = new JPanel();
	private JPanel PanelSouthNorthRight = new JPanel();
	private JPanel PanelSouthCenter = new JPanel();


	private JLabel labelListeSalon = new JLabel("Liste des salons :");

	private JLabel labelInfo = new JLabel("Prêt !");

	private PlaceholderTextField textFieldPseudo = new PlaceholderTextField("");
	private PlaceholderTextField textFieldMdp = new PlaceholderTextField("");
	private PlaceholderTextField textFieldNomSalon = new PlaceholderTextField("");
	private PlaceholderTextField textFieldMdpSalon = new PlaceholderTextField("");

	private JButton buttonConnect = new JButton("Se Connecter");
	private JButton buttonRefresh = new JButton("Raffraichir");
	private JButton buttonCreateSalon = new JButton("Créer le salon");

	private List ListSalon = new List();

	public ViewListeSalon() {
		// gestion des panels et layouts
		this.setLayout(new BorderLayout());
		this.PanelNorth.setLayout(new GridLayout(2, 2));

		this.PanelCenter.setLayout(new BorderLayout());
		this.PanelCenterNorth.setLayout(new GridLayout(1, 2));
		this.PanelCenterCenter.setLayout(new GridLayout());

		this.PanelSouth.setLayout(new BorderLayout());
		this.PanelSouthNorth.setLayout(new GridLayout(1, 2));
		this.PanelSouthNorthLeft.setLayout(new GridLayout(2, 1));
		this.PanelSouthNorthRight.setLayout(new FlowLayout());
		this.PanelSouthCenter.setLayout(new BorderLayout());

		// ajout des components dans les panels

		this.add(this.PanelNorth, BorderLayout.NORTH);


		this.PanelNorth.add(this.textFieldPseudo);
		
        
		this.PanelNorth.add(this.buttonConnect);

		this.PanelNorth.add(this.textFieldMdp);

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
		this.PanelSouthCenter.add(this.labelInfo);

		// center components

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

		// Affichage de la fenêtre
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//pop up
		//JOptionPane.showMessageDialog(null, textFieldPseudo);

	}
}
