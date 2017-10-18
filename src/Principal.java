import java.sql.*;

import View.ViewListeSalon;

public class Principal {

	public static void main(String[] args) {
		
		ViewListeSalon l = new ViewListeSalon();
		Connection myConnection;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			
			
		}
		catch (Exception e){
			System.out.println("Connexion impossible");
		}

	}

}
