package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import model.Message;
import model.Salon;
import model.Utilisateur;


public class DAOMessage {
	private Connection connection;
	
	public DAOMessage(Connection c){
		this.connection = c;
	}
	
	public Message getById(int id){
		
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message WHERE MES_ID = " + id +";");

			
			if (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setUserId(myResult.getInt("MES_USER_ID"));
			myMessage.setDateMessage(myResult.getDate("MES_DATE"));
			myMessage.setSalonId(myResult.getInt("MES_SALON_ID"));
			return myMessage;
			}
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	
	public ArrayList<Message> getAll(){
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message ;");

			
			while (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setMessage(myResult.getString("MES_MESSAGE"));
			myMessage.setUserId(myResult.getInt("MES_USER_ID"));
			myMessage.setDateMessage(myResult.getDate("MES_DATE"));
			myMessage.setSalonId(myResult.getInt("MES_SALON_ID"));
			messages.add(myMessage);
			}
			return messages;
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	public ArrayList<Message> getAllBySalon(Salon salon){
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message where MES_SALON_ID like '"+salon.getId()+"' ;");

			
			while (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setMessage(myResult.getString("MES_MESSAGE"));
			myMessage.setUserId(myResult.getInt("MES_USER_ID"));
			myMessage.setDateMessage(myResult.getDate("MES_DATE"));
			myMessage.setSalonId(myResult.getInt("MES_SALON_ID"));
			messages.add(myMessage);
			}
			return messages;
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	
	public ArrayList<Message> Refresh(ArrayList<Message> messagesConversation){
		ArrayList<Message> messages = new ArrayList<Message>();
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message ;");

			
			while (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setUserId(myResult.getInt("MES_USER_ID"));
			myMessage.setDateMessage(myResult.getDate("MES_DATE"));
			myMessage.setSalonId(myResult.getInt("MES_SALON_ID"));
			messages.add(myMessage);
			}
			if (messagesConversation.size() < messages.size()){
				ArrayList<Message> nouveauxMessages = new ArrayList<Message>();
				while(messagesConversation.size() != nouveauxMessages.size()){
					nouveauxMessages.add(messages.get(messagesConversation.size()));
					messagesConversation.add(messages.get(messagesConversation.size()));
				}
				return nouveauxMessages;
			}
			return null;
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	
	public ArrayList<Message> getBySalonId(int salonId){
		
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message WHERE MES_SALON_ID = " + salonId +";");
			ArrayList<Message> messages = new ArrayList<Message>();
			
			while (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setUserId(myResult.getInt("MES_USER_ID"));
			myMessage.setDateMessage(myResult.getDate("MES_DATE"));
			myMessage.setSalonId(myResult.getInt("MES_SALON_ID"));
			
			messages.add(myMessage);
			}
			return messages;
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	
	
	
	public void Envoyer(Message m, Salon s, Utilisateur u){
		try {
			Statement myStatement = this.connection.createStatement();
			System.out.println("Envoi du message");
			String requete = "INSERT INTO message (MES_USER_ID, MES_MESSAGE, MES_SALON_ID) VALUES (" +
			u.getId() + ", " + "'" + m.getMessage() + "'," +s.getId() + ");";
			System.out.println("La requete suivante a ete envoyee a la BDD");
			System.out.println(requete);
			myStatement.execute(requete);
			}
		catch (Exception e) {
			System.out.println("Echec de l'envoi");
			}
			
		}
	
	
}
