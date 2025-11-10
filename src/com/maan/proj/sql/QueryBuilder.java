package com.maan.proj.sql;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;


/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To execute the SQL queries and check for the data base connection.	  
  *	<blockquote> This class is extends Thread class, to send the mail as seperate process. </blockquote>
  * </ul>
  */

public class QueryBuilder
{

	Statement smt = null;

	public QueryBuilder(Statement smt1)
	{
		this.smt = smt1;
	}

	public void setStatement(Statement smt1)
	{
		this.smt = smt1;
	}
	
	public Statement getStatement()
	{
		return smt;
	}
	 
	 /**
	  * To get the result set of a query in a two dimension array. 
	  * @param query query to be execute.
	  * @return two dimension array of string.
	  * @Exception Exception.
	  */
	
	public String[][] getResultSet(String query) throws Exception
	{
		ResultSet rs = smt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();

		Vector result = new Vector();
		int col = rsmd.getColumnCount();
		
		// untill the records in the result set, get the fields and add into the Vector.
		while(rs.next())
		{
			String record[] = new String[col];
			for(int i=0; i < col; i++)
			{
				record[i] = rs.getString(i+1);
			}
			result.addElement(record);
		}
		rs.close();

		// convert the vector into two dimension string array and return the array.
		String finalResult[][] = new String[result.size()][col];
		for(int i=0; i < result.size(); i++)
		{
			finalResult[i] = (String[])result.elementAt(i);
		}
		return finalResult;
		
	}



	
	/**
	  * To check the availability of database connection.
	  * @return void
	  * @Exception Exception.
	  */

	public boolean connectionCheck() throws Exception
	{
		String sql = "select curdate()";
		ResultSet rs = smt.executeQuery(sql);
		
		// check for the execution of the query 
		while(rs.next())
		{
			return true;
		}
		rs.close();
		return false;
	}

	
	/**
	  * To get the count of total no of rows returned by a SQL statement
	  * @param query query to be execute.
	  * @return int
	  * @Exception Exception.
	  */

	public int getRecordCount(String query) throws Exception
	{
		ResultSet rs  = smt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int col = rsmd.getColumnCount();
		
		int counter = 0;
		while(rs.next())
		{
			for(int i=0; i < col; i++)
			{
			}
			counter++;
		}
		rs.close();

		return counter;
	} 
}