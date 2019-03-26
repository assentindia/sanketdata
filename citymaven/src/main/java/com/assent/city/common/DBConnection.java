/**
 * 
 */
package com.assent.city.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sanket
 *
 */
public class DBConnection 
{
	private static DBConnection instance = new DBConnection();
	static Connection connection =null;
	
	private DBConnection()
	{
		
	}
	
	
	public static DBConnection getInstance()
	{
		return instance;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/city", "root", "sanket");
		return connection;
	}
	
	public void releaseConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
