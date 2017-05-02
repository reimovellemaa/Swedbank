package com.swed;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class DBConnection {
   
	//private static DBConnection instance;
	private String url;
	
	private DBConnection()
	{
		
		try {
			
			Class.forName("org.postgresql.Driver");
			url = "jdbc:postgresql://52.56.213.74:5432/postgres";			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		//if (instance == null) {
		DBConnection 	instance = new DBConnection();
	//}
		try {
	         Properties parameters = new Properties();
	         parameters.put("user", "postgres");
	         parameters.put("password", "postgres");
			return DriverManager.getConnection(instance.url, parameters);
			
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public static void close(Connection connection)
	{
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

	

    
