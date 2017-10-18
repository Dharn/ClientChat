import java.sql.*;

import View.ViewListeSalon;

public class PrincipalChat {
	
	public static void main(String[] args) {
		
		
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection myConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "");
			//Connection myConnection = null;
			ViewListeSalon l = new ViewListeSalon(myConnection);
			
		}
		catch (Exception e){
			System.out.println("Connexion impossible");
		}

	}

}
