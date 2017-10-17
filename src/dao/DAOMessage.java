package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Message;


public class DAOMessage {
	private Connection connection;
	
	public DAOMessage(Connection c){
		this.connection = c;
	}
	
	public Message get(int id){
		
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message WHERE PRO_ID = " + id +";");

			
			if (myResult.next()){
			Message myMessage = new Message();
			myMessage.setId(myResult.getInt("MES_ID"));
			myMessage.setUserId(myResult.getString("MES_USER_ID"));
			myMessage.setUserId(myResult.getString("MES_MESSAGE"));
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
	
}
