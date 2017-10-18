import java.sql.*;

import View.ViewListeSalon;

public class Principal {
	
	public static void main(String[] args) {
		
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			ViewListeSalon l = new ViewListeSalon(myConnection);
			
		}
		catch (Exception e){
			System.out.println("Connexion impossible");
		}

	}

}
