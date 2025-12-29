package com.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	private static final String  URL= "jdbc:mysql://localhost:3306/hospital";
	private static final String user = "root";
	private static final String pass = "Root";

	public static Connection getConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, user, pass);
		
	}
}
