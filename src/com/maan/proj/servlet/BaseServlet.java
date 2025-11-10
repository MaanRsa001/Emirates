package com.maan.proj.servlet;

import java.sql.*;
import java.io.*;

import proj.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : This is the base servlet class file which extends HttpServlet and implements Constanct class for database connection details.
  * </ul>
  */

public class BaseServlet extends HttpServlet implements Constant
{
	/**
	  * Connection object to establish database connection.
	  */
	public Connection CON;

	/**
	  * Statement object to execute SQL queries.
	  */
	public Statement SMT;


	/**
	  * init method to establish data base connection for all sub classes.
	  * @param config to refer initial configuration settings.
	  * @return void
	  * @Exception SevletException.
	  */
	
	public void init(ServletConfig config) throws ServletException
    {
		// call super class init for initial configuration settings.
		super.init(config);
		try
        {
		   	// get the driver class for database connection.
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();

			// get the connection object for the database.
			CON = DriverManager.getConnection(SERVER_URL,USER_NAME,PASSWORD);

			// get the statement object to execute SQL queries.
			SMT = CON.createStatement();
		}
        catch(Exception e)
        {
		}
	}
	

	/**
	  * doGet method to handle client request.
	  * @param req client request object
	  * @param res client request object
	  * @return void 
	  * @Exception SevletException, IOException.
	  */

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
	}


	/**
	  * To destroy object when the servlet going to be stop.
	  * @return void 
	  */

	public void destroy()
	{
		try
		{
			// set Connection and statement object to null;
			CON = null;
			SMT = null;

			// close the statement and connection.
			CON.close();
			SMT.close();
		}
		catch(Exception e)
		{
		}
	}
}
