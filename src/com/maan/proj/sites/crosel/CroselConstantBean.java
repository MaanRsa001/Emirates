package com.maan.proj.sites.crosel;

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
	public final String dbUrl				= "jdbc:mysql://172.42.172.20/crosel";  // Local SERVER_URL
	public final String userName			= "root";     // Local USER_NAME
	public final String password			= "";         // Local PASSWORD
	public final String mailBox				= "172.42.172.20";  // Local MailBox
	public final String mailFrom			= "mani";
	public final String mailTo				= "mani";
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

}