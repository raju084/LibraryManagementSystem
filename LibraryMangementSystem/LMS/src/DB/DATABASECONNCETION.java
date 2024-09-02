package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DATABASECONNCETION {
	private static final String URL="jdbc:mysql://localhost:3306/library_db";
	private static final String USER="root";
	private static final String PASSWORD="Nbraju@12345";
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}
	public static void main(String[] args) {
	    // Testing the connection
	    try {
	        Connection connection = getConnection();
	        if (connection != null) {
	            System.out.println("Connection established successfully!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Failed to establish connection!");
	    }
	}

}
