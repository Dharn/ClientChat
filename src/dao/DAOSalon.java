package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Salon;
import model.Utilisateur;

public class DAOSalon {
	private Connection connection;

	public DAOSalon(Connection c) {
		this.connection = c;
	}

	public Salon get(int id) {

		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM salon WHERE SAL_ID = " + id + ";");

			if (myResult.next()) {
				Salon mySalon = new Salon();
				mySalon.setId(myResult.getInt("SAL_ID"));
				mySalon.setName(myResult.getString("SAL_NAME"));
				mySalon.setMdp(myResult.getString("SAL_MDP"));
				mySalon.setCreateurId(myResult.getInt("SAL_CREATEUR_ID"));
				return mySalon;
			}
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
		return null;
	}

	public Salon getByName(String name) {

		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM salon WHERE SAL_NAME LIKE  '" + name + "' ;");

			if (myResult.next()) {
				Salon mySalon = new Salon();
				mySalon.setId(myResult.getInt("SAL_ID"));
				mySalon.setName(myResult.getString("SAL_NAME"));
				mySalon.setMdp(myResult.getString("SAL_MDP"));
				mySalon.setCreateurId(myResult.getInt("SAL_CREATEUR_ID"));
				return mySalon;
			}
		} catch (Exception e) {
			System.out.println("Impossible de se connecter");
		}
		return null;
	}

	public ArrayList<Salon> getAll() {

		try {
			System.out.println("Raffraichissement de la liste des salons disponibles");
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM salon;");

			ArrayList<Salon> salons = new ArrayList<Salon>();

			while (myResult.next()) {
				Salon mySalon = new Salon();

				mySalon.setId(myResult.getInt("SAL_ID"));
				mySalon.setName(myResult.getString("SAL_NAME"));
				mySalon.setMdp(myResult.getString("SAL_MDP"));
				mySalon.setCreateurId(myResult.getInt("SAL_CREATEUR_ID"));

				salons.add(mySalon);

			}
			return salons;

		} catch (Exception e) {
			System.out.println("Echec de la lecture");
			return null;
		}

	}

	public void insert(Salon s, Utilisateur u) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Ajout d'un nouveau salon a la base de donnees");
			String requete = "INSERT INTO salon (SAL_NAME, SAL_MDP, SAL_CREATEUR_ID) VALUES (" + "'" + s.getName() + "'"
					+ ", " + "'" + s.getMdp() + "'," + u.getId() + ");";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
		} catch (Exception e) {
			System.out.println("Echec de l'ajout");
		}

	}

	public boolean checkMDP(String mdpATester, Salon s) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Verification du mot de passe");
			String requete = "SELECT SAL_MDP WHERE SAL_MDP like '" + mdpATester + "' AND SAL_NAME like '"+s.getName()+"';";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			ResultSet myResult = myStatement.executeQuery(requete);
			if (myResult.next()) {
				return true;
			}
		} catch (Exception e) {
			if (mdpATester.equals(s.getMdp()) ) {
				return true;
			} else {
				System.out.println("Mot de passe incorrect");
			}
		}
		return false;
	}

	public void supprimerSalon(Salon s) {
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Supression d'un salon");
			String requete = "DELETE FROM salon WHERE SAL_NAME = " + "'" + s.getName() + "'";

			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
		} catch (Exception e) {
			System.out.println("Echec de la supression");
		}

	}

}
