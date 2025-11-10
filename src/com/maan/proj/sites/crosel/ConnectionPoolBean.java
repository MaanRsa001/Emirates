package com.maan.proj.sites.crosel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Hashtable;
import java.util.Enumeration;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Cross Selling.
  * <li> Purpose : To Store the constant settings like server, username, password etc. 
  * </ul>
  */

public class ConnectionPoolBean implements CroselConstant
{
	private int maxConnection = 20;
	private int increment = 5;
	private Hashtable connections = null;

	// Empty Constructor
	public ConnectionPoolBean()
	{
		try
		{
			connections = new Hashtable();
			getDriver();
			initConnection(maxConnection);
		}
		catch(Exception e)
		{
		}
	}

	public void setMaxConnection(int max)
	{
		this.maxConnection = max;
	}

	public void setIncrement(int inc)
	{
		this.increment = inc;
	}

	public int getMaxConnection()
	{
		return this.maxConnection;
	}

	public int increment()
	{
		return this.increment;
	}

	
	// To load the Driver class
	public void getDriver() throws Exception
	{
		Class.forName(DRIVER).newInstance();
	}

	// To get the connection object
	public Connection getDBConnection() throws SQLException
	{
		return DriverManager.getConnection(SERVER_URL,USER_NAME,PASSWORD);
	}

	// To initialize connection
	public void initConnection(int count) throws Exception
	{
		for(int i=0; i < count; i++)
		{
			connections.put(getDBConnection(), Boolean.FALSE);		
		}
	}

	// To close the connection
	public void closeConnection() throws Exception
	{
		Enumeration cons = connections.keys();

		while(cons.hasMoreElements())
		{
			Connection con = (Connection) cons.nextElement();
			con.close();
		}
	}

	// To get the freely available connection.
	public Connection getConnection() throws Exception
	{
		Connection con = null;
		
		Enumeration cons = connections.keys();

		synchronized(connections)
		{
			while(cons.hasMoreElements())
			{
				con = (Connection) cons.nextElement();
				
				Boolean b = (Boolean) connections.get(con);
				if(b == Boolean.FALSE)
				{
					try
					{
						con.setAutoCommit(true);
					}
					catch(SQLException e)
					{
						con = getDBConnection();
					}
					
					connections.put(con, Boolean.TRUE);
					return con;
				}
			}
		}

		initConnection(increment);

		return getConnection();
	}

	
	// To return back and make the connection is free
	public void returnConnection(Connection returned)
	{
		Connection con;

		Enumeration cons = connections.keys();
		while(cons.hasMoreElements())
		{
			con = (Connection) cons.nextElement();
			if(con == returned)
			{
				connections.put(con, Boolean.FALSE);
				break;
			}
		}
	}


	// To destroy the created connection object
	public void finalize() throws Exception
	{
		closeConnection();	
	}

}