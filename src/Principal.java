import java.sql.*;

import View.ViewListeSalon;

public class Principal {
	
	
	
	public static void main(String[] args) {
		try {
			Connection myConnection;
			
			ViewListeSalon l = new ViewListeSalon(myConnection);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

	}

}
