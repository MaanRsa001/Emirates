package com.maan.common.util;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.Statement;

import java.util.StringTokenizer;
import java.util.Hashtable;
import java.util.Enumeration;

import proj.sql.QueryBuilder;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Cross Selling.
  * <li> Purpose : To Store the constant settings like server, username, password etc. 
  * </ul>
  */

public class CroselConstantBean
{
	public final String MONTH_ARR[]			= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};	
	public final String driver				= "org.gjt.mm.mysql.Driver";
	//public final String dbUrl				= "jdbc:mysql://172.42.172.20/crosel";  // Local SERVER_URL
	//public final String dbUrl				= "jdbc:mysql://sbsserv1/crosel";  // SBS SERVER_URL
	
	public final String userName			= "root";     // Local USER_NAME
	public final String password			= "";         // Local PASSWORD
	public final String mailBox				= "192.168.1.100";  // Local MailBox
	public final String mailFrom			= "mani";
	public final String mailTo				= "mani";
	public final String dbUrl				= "mani";
	
		

	public final int MAX_SESSION  = 180;

	//public final String MAIL_ATTACH_FOLDER	= "/usr/local/apache/htdocs/uii/html/TravelPolicy.xls"; 


	public CroselConstantBean()
	{
		
	}

	public String getDriver()
	{
		return this.driver;
	}

	public String getDbUrl()
	{
		return this.dbUrl;
	}
	
	public String getUserName()
	{
		return this.userName;
	}
	
	public String getPassword()
	{
		return this.password;
	}



	public StringBuffer[] getSelectionHtml(String[][] row1, String[][] row2, String rowName1, String rowName2) throws Exception
	{
		StringBuffer scriptString  = new StringBuffer();
		StringBuffer projString    = new StringBuffer();
		StringBuffer fileString    = new StringBuffer();

		scriptString.append("NS = document.layers? 1:0; \n");
		scriptString.append("function populate()  \n");
		scriptString.append("{ \n");
		scriptString.append("	var number = document.form1."+rowName1+".selectedIndex; \n");
	
		for(int i=0; i < row1.length; i++)
		{
			scriptString.append("if(number == "+i+") \n");
			scriptString.append("{ \n");

			int k =0;
			for(int j=0; j < row2.length; j++)
			{
				if(!row2[j][0].equalsIgnoreCase(row1[i][0]))
					continue;
				scriptString.append("option"+k+" = new Option('"+row2[j][2]+"','"+row2[j][1]+"'); \n");
				if(i==0)
				{
					fileString.append("<option value='"+row2[j][1]+"'>"+row2[j][2]+" \n");
				}
				if(j < row2.length-1 && !row2[j+1][0].equalsIgnoreCase(row1[i][0]))
				{
					scriptString.append("document.form1."+rowName2+".length = "+(k+1)+"; \n");
					k++;
					break;
				}
				else if(j == row2.length-1)
				{
					scriptString.append("document.form1."+rowName2+".length = "+(k+1)+"; \n");
				}
				
				k++;
			}
			if(k == 0)
				scriptString.append("document.form1."+rowName2+".length = 0; \n");
			scriptString.append("} \n");
			projString.append("<option value='"+row1[i][0]+"'>"+row1[i][1]+" \n");
		}

		scriptString.append("var num = document.form1."+rowName2+".length; \n");
		scriptString.append("for (var i=0; i<num; i++) \n");
		scriptString.append("{ \n");
		scriptString.append("	eval('document.form1."+rowName2+".options[i]=option'+i); \n");
		scriptString.append("} \n");
		scriptString.append("if (NS) \n");
		scriptString.append("{ \n");
		scriptString.append("	history.go(0); \n");
		scriptString.append("} \n");
		scriptString.append("} \n");

		StringBuffer[] printString = {scriptString, projString, fileString};
		return printString;

	}


	public boolean isValidSession(String uid, String login, Connection connection) throws Exception
	{
		String query = "";
		Statement statement = connection.createStatement();

			// Check the availability of the uid in session_master.
			query = "select date_format(end_time,'%d/%m/%Y'), session_id from session_master where session_id = '"+uid+"' and user_name = '"+login+"'";
			QueryBuilder sql = new QueryBuilder(statement);
			
			String retValue[][] = new String[0][0];
			retValue = sql.getResultSet(query);
		
			// if not available, show the UID Error page.
			if(retValue.length == 0)
			{
				return false;
			}
			else
			{
				query = "select session_id, user_name from session_master where unix_timestamp(sysdate())-unix_timestamp(end_time) < "+(MAX_SESSION * 60)+" and session_id = '"+uid+"'";
				
				retValue = sql.getResultSet(query);
				if(retValue.length > 0)
				{
					query = "update session_master set end_time = sysdate() where session_id = '"+uid+"' and user_name ='"+login+"'";
					statement.executeUpdate(query);
					return true;
				}
				else
				{
					query = "update session_master set status = 'N' session_id = '"+uid+"' and user_name ='"+login+"'";
					statement.executeUpdate(query);
					return false;
				}
			}
	}


	public String[] split(String str) throws Exception
	{	
		StringTokenizer st = new StringTokenizer(str,",");
		String[] arr = new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens())
		{
			arr[i++] = st.nextToken();
		}
		if(arr.length > 0)
		{
			return arr;
		}
		else
		{
			String arr1[] = {str};
			return arr1;
		}
	}

	public String[] split(String str, String delimiter) throws Exception
	{	
		StringTokenizer st = new StringTokenizer(str, delimiter);
		String[] arr = new String[st.countTokens()];
		int i=0;
		while(st.hasMoreTokens())
		{
			arr[i++] = st.nextToken(delimiter);
		}
		if(arr.length > 0)
		{
			return arr;
		}
		else
		{
			String arr1[] = {str};
			return arr1;
		}
	}


	public StringBuffer queryFormat(String str[]) throws Exception 
	{	
		StringBuffer format = new StringBuffer();

		for(int i=0; i < str.length; i++)
		{
			format.append("'"+str[i]+"',");
		}

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length()-1)) : format;
	}

	public StringBuffer getCommaSeparated(String str[]) throws Exception 
	{	
		StringBuffer format = new StringBuffer();

		for(int i=0; i < str.length; i++)
		{
			if(!str[i].equals(""))
				format.append(str[i]+",");
		}

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length()-1)) : format;
	}

	public StringBuffer getSeparated(String str[], String delim) throws Exception 
	{	
		StringBuffer format = new StringBuffer();

		for(int i=0; i < str.length; i++)
		{
			if(!str[i].equals(""))
				format.append(str[i]+delim);
		}

		return (format.length() > 0) ? new StringBuffer(format.substring(0,format.length()-1)) : format;
	}


	public String[] toArray(String str[][], int index) throws Exception 
	{	
		String[] retStr = new String[str.length];

		for(int i=0; i < str.length; i++)
		{
			retStr[i] = str[i][index];
		}

		return retStr;
	}

	public Hashtable convertHash(String[] arr)
	{
		Hashtable hash = new Hashtable();
		for(int i=0; i < arr.length; i++)
		{
			hash.put(arr[i],arr[i]);
		}
		return hash;
	}


	public Hashtable convertHash(String[][] arr)
	{
		Hashtable hash = new Hashtable();
		for(int i=0; i < arr.length; i++)
		{
			hash.put(arr[i][0],arr[i][1]);
		}
		return hash;
	}

	public String toString(Hashtable hash, String delimiter)
	{
		StringBuffer buffer = new StringBuffer();
		Enumeration Enumeration = hash.elements();
		while(Enumeration.hasMoreElements())
		{
			buffer.append(Enumeration.nextElement()+delimiter);
		}
		
		int len = buffer.length();
		return len > 0 ? buffer.substring(0, len-1) : "";
	}


	public String toString(Hashtable hash, String delimiter, String enclosed)
	{
		StringBuffer buffer = new StringBuffer();
		Enumeration Enumeration = hash.elements();
		while(Enumeration.hasMoreElements())
		{
			buffer.append(enclosed+Enumeration.nextElement()+enclosed+delimiter);
		}
		
		int len = buffer.length();
		return len > 0 ? buffer.substring(0, len-1) : "";
	}


	public StringBuffer[] getSelectionHtml(String[][] company, String[][] companyAndPolicy, String rowName1, String rowName2, String defaultValueRow1, String defaultViewRow1,String defaultValueRow2, String defaultViewRow2) 
	{
		
		StringBuffer scriptString  = new StringBuffer();
		StringBuffer projString    = new StringBuffer();
		StringBuffer fileString    = new StringBuffer();
		
		scriptString.append("NS = document.layers? 1:0; \n");
		scriptString.append("function populate()  \n");
		scriptString.append("{ \n");
		scriptString.append("	var number = document.form1."+rowName1+".selectedIndex; \n");
	
	/*	scriptString.append("if(number == 0) \n");
		scriptString.append("{ \n");
		scriptString.append("	option0 = new Option(' nil','nil'); \n");
		scriptString.append("	document.form1."+rowName2+".length = 1; \n");
		scriptString.append("} \n");*/

		for(int i=0; i < company.length; i++)
		{
			scriptString.append("if(number == "+i+") \n");
			scriptString.append("{ \n");

			int k =0;
			for(int j=0; j < companyAndPolicy.length; j++)
			{
				if(!companyAndPolicy[j][0].equalsIgnoreCase(company[i][0]))
					continue;
				scriptString.append("option"+k+" = new Option('"+companyAndPolicy[j][2]+"','"+companyAndPolicy[j][1]+"'); \n");
				if(i==0)
				{
					//fileString.append("<option value='"+companyAndPolicy[j][1]+"'>"+companyAndPolicy[j][2]+" \n");

					if( companyAndPolicy[j][1].trim().indexOf(defaultValueRow2.trim()) >=0 )
					{
						fileString.append("<option selected value='"+defaultValueRow2+"'>"+defaultViewRow2+" \n");
					}
					else
					{
						fileString.append("<option value='"+companyAndPolicy[j][1]+"'>"+companyAndPolicy[j][2]+" \n");
					}
				}
				if(j < companyAndPolicy.length-1 && !companyAndPolicy[j+1][0].equalsIgnoreCase(company[i][0]))
				{
					scriptString.append("document.form1."+rowName2+".length = "+(k+1)+"; \n");
					k++;
					break;
				}
				else if(j == companyAndPolicy.length-1)
				{
					scriptString.append("document.form1."+rowName2+".length = "+(k+1)+"; \n");
				}
				
				k++;
			}
			if(k == 0)
				scriptString.append("document.form1."+rowName2+".length = 0; \n");
			scriptString.append("} \n");
			
			if(defaultValueRow1.equalsIgnoreCase(""))
			{
				projString.append("<option value='"+company[i][0]+"'>"+company[i][1]+" \n");
			}
			else
			{
				if(  company[i][0].trim().indexOf(defaultValueRow1.trim()) >=0  ){
					projString.append("<option selected value='"+defaultValueRow1+"'>"+defaultViewRow1+" \n");
				}else{
					projString.append("<option value='"+company[i][0]+"'>"+company[i][1]+" \n");
					}
			}
		}
		scriptString.append("var num = document.form1."+rowName2+".length; \n");
		scriptString.append("for (var i=0; i<num; i++) \n");
		scriptString.append("{ \n");
		scriptString.append("	eval('document.form1."+rowName2+".options[i]=option'+i); \n");
		scriptString.append("} \n");
		scriptString.append("if (NS) \n");
		scriptString.append("{ \n");
		scriptString.append("	history.go(0); \n");
		scriptString.append("} \n");
		scriptString.append("} \n");

		StringBuffer[] printString = {scriptString, projString, fileString};
		return printString;

	}

}
