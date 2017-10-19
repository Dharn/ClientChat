import java.sql.*;

import View.ViewListeSalon;

public class PrincipalChat {
	
	public static void main(String[] args) {
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://192.168.1.78:3306/chat", "chatuser", "password");
			//Connection myConnection = null;
			ViewListeSalon l = new ViewListeSalon(myConnection);
			System.out.println("Connecte a la BDD");
			
		}
		catch (Exception e){
			System.out.println("Connexion impossible");
		}

	}

}
