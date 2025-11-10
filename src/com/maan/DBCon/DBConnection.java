package com.maan.DBCon;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DBConnection implements Serializable
{
	private transient JdbcTemplate template;
	private static final long serialVersionUID = 1L;
	private static DBConnection ourInstance = new DBConnection(); 
	Context initContext = null;
	DataSource ds = null;
	
	public static DBConnection getInstance() 
	{ 
		if(null==ourInstance)
		{ 
			ourInstance= new DBConnection(); 
		} 
		return ourInstance; 
	} 

	private DBConnection() 
	{ 
		System.out.println("Intial DBConnection object Created"+new Date());
		try
		{	
			initContext = new InitialContext();
			ds = (DataSource)initContext.lookup("java:comp/env/EmiratesIntg");
			template = new JdbcTemplate(ds);
		}
		catch(Exception e)
		{
			System.out.println("DBConnection object "+e);
			e.printStackTrace();
		}
		finally {
			System.out.println("Intial DBConnection Closed "+new Date());
		}
	} 

	public Connection getDBConnection()
	{
		Connection connection = null;
		try
		{
		    connection = ds.getConnection();
		}
		catch(Exception exception)
		{
			System.out.println("getDBConnection ..."+exception);
		    exception.printStackTrace();
		}
		return connection;
	}

	public JdbcTemplate gettemplate() throws SQLException {
		return template;
	}
}
