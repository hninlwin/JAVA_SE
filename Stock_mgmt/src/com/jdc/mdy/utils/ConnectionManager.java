package com.jdc.mdy.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	private static final String url="jdbc:mysql://localhost:3306/stock_db";
	private static final String user="root";
	private static final String pass="admin123";
	
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
}
