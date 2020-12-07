package com.jdc.mdy.utis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {

	public static final String user="root";
	public static final String pass="admin123";
	public static final String url="jdbc:mysql://localhost:3306/student_db";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,pass);
	}
}
