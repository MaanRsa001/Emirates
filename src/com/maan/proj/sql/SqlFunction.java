package com.maan.proj.sql;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To execute the SQL queries and check for the data base connection.	  
  *	<blockquote> This class is extends Thread class, to send the mail as seperate process. </blockquote>
  * </ul>
  */

public class SqlFunction
{
	 /**
	  * To get the result set of a query in a two dimension array. 
	  * @param query query to be execute.
	  * @param SMT statement object for execute the SQL queries.
	  * @param fieldLength number of fields in the SQL queries.
	  * @return two dimension array of string.
	  * @Exception Exception.
	  */
	
	public static String[][] getResultSet(String query, Statement SMT, int fieldLength) throws Exception
	{
		ResultSet rs      = SMT.executeQuery(query);
		Vector result = new Vector();
		int counter = 0;
		
		// untill the records in the result set, get the fields and add into the Vector.
		while(rs.next())
		{
			String record[] = new String[fieldLength];
			for(int i=0; i < fieldLength; i++)
			{
				record[i] = rs.getString(i+1);
			}
			result.addElement(record);
			counter++;
		}
		rs.close();

		// convert the vector into two dimension string array and return the array.
		String customer[][] = new String[counter][fieldLength];
		for(int i=0; i < counter; i++)
		{
			customer[i] = (String[])result.elementAt(i);
		}
		return customer;
		
	}


	/**
	  * To get the result set of a query in a two dimension array. 
	  * @param query query to be execute.
	  * @param SMT statement object for execute the SQL queries.
	  * @return String.
	  * @Exception Exception.
	  */

	public static String getResultSet(String query, Statement SMT) throws Exception
	{
		ResultSet rs      = SMT.executeQuery(query);
		String record = "";
		
		// get the fields from the result set and return the value.
		while(rs.next())
		{
			record = rs.getString(1);
		}
		rs.close();

		return record;
	}

	
	/**
	  * To check the availability of database connection.
	  * @param SMT statement object for execute the SQL queries.
	  * @return void
	  * @Exception Exception.
	  */

	public void connectionCheck(Statement SMT) throws Exception
	{
		String sql = "select curdate()";
		ResultSet rs = SMT.executeQuery(sql);
		
		// check for the execution of the query 
		while(rs.next())
		{
			return;
		}
		rs.close();
		return;
	}

	
	/**
	  * To get the count of total no of rows returned by a SQL statement
	  * @param query query to be execute.
	  * @param SMT statement object for execute the SQL queries.
	  * @param fieldLength number of fields in the SQL queries.
	  * @return int
	  * @Exception Exception.
	  */

	public static int getRecordCount(String query, Statement SMT, int fieldLength) throws Exception
	{
		ResultSet rs  = SMT.executeQuery(query);
		
		//Vector result = new Vector();
		//String record[] = new String[fieldLength];
		int counter = 0;
		while(rs.next())
		{
			// 
			for(int i=0; i < fieldLength; i++)
			{
				//record[i] = rs.getString(i+1);
			}
			//result.addElement(record);
			counter++;
		}
		rs.close();

		return counter;
	}
}
