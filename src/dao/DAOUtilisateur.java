package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Utilisateur;

public class DAOUtilisateur {

	private Connection connection;

	public DAOUtilisateur(Connection c) {
		this.connection = c;
	}

	public Utilisateur get(int id) {

		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM utilisateur WHERE UTI_ID = " + id + ";");

			if (myResult.next()) {
				Utilisateur myUtilisateur = new Utilisateur();
				myUtilisateur.setId(myResult.getInt("UTI_ID"));
				myUtilisateur.setPseudo(myResult.getString("UTI_PSEUDO"));
				myUtilisateur.setMotDePasse(myResult.getString("UTI_MDP"));
				myUtilisateur.setAvatar(myResult.getString("UTI_AVATAR"));
				myUtilisateur.setConnected(myResult.getBoolean("UTI_CONNECTED"));
				return myUtilisateur;
			}
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
		return null;
	}
	
	public ArrayList<Utilisateur> getAll() {

		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM utilisateur WHERE UTI_CONNECTED = 1 ;");
			ArrayList<Utilisateur> listu  = new ArrayList<Utilisateur>();
			while (myResult.next()) {
				Utilisateur myUtilisateur = new Utilisateur();
				myUtilisateur.setId(myResult.getInt("UTI_ID"));
				myUtilisateur.setPseudo(myResult.getString("UTI_PSEUDO"));
				myUtilisateur.setMotDePasse(myResult.getString("UTI_MDP"));
				myUtilisateur.setAvatar(myResult.getString("UTI_AVATAR"));
				myUtilisateur.setConnected(myResult.getBoolean("UTI_CONNECTED"));
				listu.add(myUtilisateur);
				
			}
			return listu;
			
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
		return null;
	}

	public Utilisateur getByPseudoAndMdp(String pseudo, String mdp) {

		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("start");
			ResultSet myResult = myStatement.executeQuery(
					"SELECT * FROM utilisateur WHERE UTI_PSEUDO like '" + pseudo + "' AND UTI_MDP like '" + mdp + "' ;");
			System.out.println("requete envoyée");
			if (myResult.next()) {
				Utilisateur myUtilisateur = new Utilisateur();
				System.out.println(myResult.getInt("UTI_ID"));
				myUtilisateur.setId(myResult.getInt("UTI_ID"));
				myUtilisateur.setPseudo(myResult.getString("UTI_PSEUDO"));
				myUtilisateur.setMotDePasse(myResult.getString("UTI_MDP"));
				myUtilisateur.setAvatar(myResult.getString("UTI_AVATAR"));
				myUtilisateur.setConnected(myResult.getBoolean("UTI_CONNECTED"));
				return myUtilisateur;
			}
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
		return null;
	}

	public void insert(Utilisateur u) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Ajout d'un nouvel utilisateur a la base de donnees");
			String requete = "INSERT INTO utilisateur (UTI_PSEUDO, UTI_MDP, UTI_AVATAR) VALUES ('" + u.getPseudo() + "'"
					+ ",'" + u.getMotDePasse() + "'" + ", " + "'" + u.getAvatar() + "'" + ");";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
			System.out.println("succès");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Echec de l'ajout");
		}

	}

	public void Deconnexion(Utilisateur u) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Deconnexion de " + u.getPseudo());
			String requete = "UPDATE utilisateur SET UTI_CONNECTED = 0 WHERE UTI_PSEUDO like '" + u.getPseudo()+"';";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
		} catch (Exception e) {
			System.out.println("Echec de la deconnexion");
		}
	}
	public void Connexion(Utilisateur u) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Connexion de " + u.getPseudo());
			String requete = "UPDATE utilisateur SET UTI_CONNECTED = 1 WHERE UTI_PSEUDO like '" + u.getPseudo()+"';";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
		} catch (Exception e) {
			System.out.println("Echec de la connexion");
		}
	}

	public boolean checkMDP(String mdpATester, Utilisateur u) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Verification du mot de passe");
			String requete = "SELECT UTI_MDP WHERE UTI_MDP = " + mdpATester + ";";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			ResultSet myResult = myStatement.executeQuery(requete);
			if (myResult.next()) {
				return true;
			}
		} catch (Exception e) {
			if (mdpATester == u.getMotDePasse()) {
				return true;
			} else {
				System.out.println("Mot de passe incorrect");
			}
		}
		return false;
	}

}
