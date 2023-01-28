package edu.wccnet.adytynenko.pizzaApp.entity;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/pizzas?useSSL=false";
		String user = "root";
		String pass = "Platon1996";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connecting successfull!!!");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
 