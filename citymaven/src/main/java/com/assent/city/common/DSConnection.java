/**
 * 
 */
package com.assent.city.common;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
 * @author Sanket
 *
 */
public class DSConnection 
{
	static Connection connection =null;
	
	private static DataSource ds;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/citydb");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		connection=ds.getConnection();
		return connection;
	}
	
	public static void releaseConnection(Connection connection)
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
