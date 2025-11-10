package com.maan.proj.sites.travel;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Accurate truss brace.
  * <li> Purpose : To Store the constant settings like server, username, password etc. for both local and net.
  * </ul>
  */

public interface TravelConstant
{
	public final String MONTH_ARR[]   = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	
	public final String DRIVER		= "org.gjt.mm.mysql.Driver";
	public final String SERVER_URL	= "jdbc:mysql://172.42.172.20/uii";  // Local SERVER_URL
	public final String USER_NAME	= "root";     // Local USER_NAME
	public final String PASSWORD	= "";         // Local PASSWORD
	public final String PATH		= "/usr/local/apache/htdocs/uii";   // Local PATH for html 
	public final String MAILBOX		= "172.42.172.20";  // Local MailBox
	public final String MAIL_FROM	= "mani";
	public final String MAIL_TO		= "mani";
	public final String MAIL_ATTACH	= "/usr/local/apache/htdocs/uii/html/TravelPolicy.xls"; 
	public final String SECURE		= "http://www.imc.com"; 

	
	

	
/*	public final String DRIVER				= "org.gjt.mm.mysql.Driver";
	public final String SERVER_URL			= "jdbc:mysql://wserv7/insurance_db1"; // Net SERVER_URL
	public final String USER_NAME			= "insurance";   //Net USER_NAME
	public final String PASSWORD			= "hUng3r";      //Net PASSWORD
	public final String PATH				= "home/insure";    //Net PATH for html
	public final String MAILBOX				= "mail.accuratetrussbrace.com"; // Net MailBox  mail.accuratetrussbrace.com or mail.bigmailbox.com
	public final String MAIL_FROM			= "mmani_in@hotmail.com";
	public final String MAIL_TO				= "mmani_in@yahoo.com,mmani_in@rediffmail.com";
	public final String MAIL_CC				= "mani.net@sify.com";
	public final String MAIL_ATTACH			= "/home/insure/html/TravelPolicy.xls";  // "/home/insure/www/product_list.xls";
//	public final String MAIL_MESSAGE_FILE   = "/home/insure/www/bulkmail_new.htm";
	public final String SECURE				= "https://secure2.inetu.net/accuratetrussbrace.com"; */

}