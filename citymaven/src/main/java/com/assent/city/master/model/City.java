/**
 * 
 */
package com.assent.city.master.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.assent.city.common.DBConnection;
import com.assent.city.common.DSConnection;
import com.assent.city.common.Validator;

/**
 * @author Sanket
 *
 */
public class City 
{
	public String insertCity(com.assent.city.master.entity.City city)
	{
		String msg="";
		Connection connection = null;
		PreparedStatement pstmt=null;
		Timestamp today = new Timestamp(System.currentTimeMillis());
		String sql="INSERT INTO CITY(CITY_NAME,STATUS_FLAG,CREATED_DATE) VALUES(?,?,?)";
		try
		{
			connection = DBConnection.getInstance().getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, city.getCityName());
			pstmt.setString(2, "N");
			pstmt.setTimestamp(3, today);
			
			int i=pstmt.executeUpdate();
			
			if(i>0)
			{
				msg ="Successfully Saved.";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			//msg ="Internal server error occured..";
		}
		finally
		{
			DBConnection.getInstance().releaseConnection(connection);
		}
		return msg;
	}
	
	public List<com.assent.city.master.entity.City> getCities() throws SQLException
	{
		List<com.assent.city.master.entity.City> cityList= new ArrayList<>();
		Connection connection = null;
		Statement stmt=null;
		
		String sql="SELECT * FROM CITY WHERE STATUS_FLAG!='D'";
		try
		{
			connection = DBConnection.getInstance().getConnection();
			stmt=connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				com.assent.city.master.entity.City city = new com.assent.city.master.entity.City();
				
				city.setCityId(Validator.replaceNull(rs.getString("CITY_ID")));
				city.setCityName(Validator.replaceNull(rs.getString("CITY_NAME")));
				
				cityList.add(city);
			}
		}
		catch(Exception e)
		{
			throw new SQLException("Error occured while fetching list.");
		}
		finally
		{
			DBConnection.getInstance().releaseConnection(connection);
		}
		return cityList;
	}
	
	public String updateCity(com.assent.city.master.entity.City city) throws ClassNotFoundException, SQLException
	{
		String msg="";
		Connection connection = null;
		PreparedStatement pstmt=null;
		Timestamp today = new Timestamp(System.currentTimeMillis());
		String sql="UPDATE CITY SET CITY_NAME=?,STATUS_FLAG=?,MODIFIED_DATE=? WHERE CITY_ID=?";
		try
		{
			connection = DBConnection.getInstance().getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, city.getCityName());
			pstmt.setString(2, "U");
			pstmt.setTimestamp(3, today);
			pstmt.setString(4, city.getCityId());
			
			int i=pstmt.executeUpdate();
			
			if(i>0)
			{
				msg ="Successfully Updated.";
			}
		}
		catch(Exception e)
		{
			msg ="Internal server error occured..";
		}
		finally
		{
			DBConnection.getInstance().releaseConnection(connection);
		}
		return msg;
	}
	
	public String deleteCity(String cityId)
	{
		String msg="";
		Connection connection = null;
		PreparedStatement pstmt=null;
		Timestamp today = new Timestamp(System.currentTimeMillis());
		String sql="UPDATE CITY SET STATUS_FLAG=?,MODIFIED_DATE=? WHERE CITY_ID=?";
		try
		{
			connection = DBConnection.getInstance().getConnection();
			pstmt=connection.prepareStatement(sql);
			pstmt.setString(1, "D");
			pstmt.setTimestamp(2, today);
			pstmt.setString(3, cityId);
			
			int i=pstmt.executeUpdate();
			
			if(i>0)
			{
				msg ="Successfully Deleted.";
			}
		}
		catch(Exception e)
		{
			msg ="Internal server error occured..";
		}
		finally
		{
			DBConnection.getInstance().releaseConnection(connection);
		}
		return msg;
	}
}
