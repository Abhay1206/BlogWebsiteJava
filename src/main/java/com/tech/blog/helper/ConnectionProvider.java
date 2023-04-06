package com.tech.blog.helper;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionProvider {

	
	
	static java.sql.Connection connection = null;
	static String url = "jdbc:mysql://localhost:3306/advancecon";
static 	String user = "root";
	static String password = "abhay";

	
	public static java.sql.Connection getConnection() {
		
		
		
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return connection;
	}
	
}
