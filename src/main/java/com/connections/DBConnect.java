package com.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect 
{
	public static Connection con = null;
	
	public static Connection getConnection()
	{
		try 
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost/demodb","om","om123");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
}
