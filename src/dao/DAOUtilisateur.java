package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Utilisateur;

public class DAOUtilisateur {
	
	private Connection connection;
	
	public DAOUtilisateur(Connection c){
		this.connection = c;
	}
	
	public Utilisateur get(int id){
		
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message WHERE PRO_ID = " + id +";");

			
			if (myResult.next()){
			Utilisateur myUtilisateur = new Utilisateur();
			myUtilisateur.setId(myResult.getInt("UTI_ID"));
			myUtilisateur.setPseudo(myResult.getString("UTI_PSEUDO"));
			myUtilisateur.setMotDePasse(myResult.getString("UTI_MDP"));
			myUtilisateur.setAvatar(myResult.getString("UTI_AVATAR"));
			myUtilisateur.setConnected(myResult.getBoolean("UTI_CONNECTED"));
			return myUtilisateur;
			}
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	

}
