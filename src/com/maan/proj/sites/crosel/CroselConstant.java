package com.maan.proj.sites.crosel;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Cross Selling.
  * <li> Purpose : To Store the constant settings like server, username, password etc. 
  * </ul>
  */

public interface CroselConstant
{
	public final String MONTH_ARR[]			= {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public final String DRIVER				= "org.gjt.mm.mysql.Driver";
	public final String SERVER_URL			= "jdbc:mysql://172.42.172.20/crosel";  // Local SERVER_URL
	public final String USER_NAME			= "root";     // Local USER_NAME
	public final String PASSWORD			= "";         // Local PASSWORD
	public final String MAILBOX				= "172.42.172.20";  // Local MailBox
	public final String MAIL_FROM			= "mani";
	public final String MAIL_TO				= "mani";
	//public final String MAIL_ATTACH_FOLDER	= "/usr/local/apache/htdocs/uii/html/TravelPolicy.xls"; 
}