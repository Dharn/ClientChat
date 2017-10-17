package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Salon;

public class DAOSalon {
	private Connection connection;
	
	public DAOSalon(Connection c){
		this.connection = c;
	}
	
	public Salon get(int id){
		
		try {
			Statement myStatement = this.connection.createStatement();
			ResultSet myResult = myStatement.executeQuery("SELECT * FROM message WHERE PRO_ID = " + id +";");

			
			if (myResult.next()){
			Salon mySalon = new Salon();
			mySalon.setId(myResult.getInt("SAL_ID"));
			mySalon.setName(myResult.getString("SAL_NAME"));
			mySalon.setMdp(myResult.getString("SAL_MDP"));
			mySalon.setCreateurId(myResult.getInt("SAL_CREATEUR_ID"));
			return mySalon;
			}
			}
		catch(Exception e) {
			System.out.println("Impossible de se connecter");
			}
		return null; 
	}
	
}
