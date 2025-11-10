package com.maan.proj.util;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Accurate truss brace.
  * <li> Purpose : To Store the constant settings like server, username, password etc. for both local and net.
  * </ul>
  */

public interface Constant
{
	/*public final String SERVER_URL	= "jdbc:mysql://172.42.172.20/accurate";  // Local SERVER_URL
	public final String USER_NAME	= "root";     // Local USER_NAME
	public final String PASSWORD	= "";         // Local PASSWORD
	//public final String PATH		= "/usr/local/apache/cgi-bin/accurate";   // Local PATH for html 
	public final String PATH		= "cgi-bin/accurate";   // Local PATH for html 
	public final String MAILBOX		= "172.42.172.20";  // Local MailBox
	public final String FROM		= "Mani"; // Local mail from
	public final String SECURE		= "http://www.accurate.com"; */

	
	public final String SERVER_URL	= "jdbc:mysql://wserv7/accur_db"; // Net SERVER_URL
	public final String USER_NAME	= "accurftp";   //Net USER_NAME
	public final String PASSWORD	= "X8%sD2";      //Net PASSWORD
	public final String PATH		= "home/accur/cgi-bin";    //Net PATH for html
	public final String MAILBOX		= "mail.accuratetrussbrace.com"; // Net MailBox  mail.accuratetrussbrace.com or mail.bigmailbox.com
	public final String FROM		= "accur@accuratetrussbrace.com"; // Net mail from 
	public final String CC			= "tom@1888nailgun.com"; // Net mail from 
	public final String SECURE		= "https://secure2.inetu.net/accuratetrussbrace.com";
}