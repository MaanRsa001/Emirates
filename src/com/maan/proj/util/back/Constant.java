package com.maan.proj.util.back;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Accurate truss brace.
  * <li> Purpose : To Store the constant settings like server, username, password etc. for both local and net.
  * </ul>
  */

public interface Constant
{
	public final String SERVER_URL	= "jdbc:mysql://172.42.172.21/accurate";  // Local SERVER_URL
	public final String USER_NAME	= "root";     // Local USER_NAME
	public final String PASSWORD	= "";         // Local PASSWORD
	public final String PATH		= "htdocs";   // Local PATH for html 
	public final String MAILBOX		= "172.42.172.21";  // Local MailBox
	public final String FROM		= "Mani"; // Local mail from
	public final String SECURE		= "http://www.accurate.com"; 

	
	/*public final String SERVER_URL	= "jdbc:mysql://wserv7/accur_db"; // Net SERVER_URL
	public final String USER_NAME	= "accurftp";   //Net USER_NAME
	public final String PASSWORD	= "X8%sD2";      //Net PASSWORD
	public final String PATH		= "home/accur/cgi-bin";    //Net PATH for html
	public final String MAILBOX		= "mail.bigmailbox.com"; // Net MailBox  mail.accuratetrussbrace.com
	public final String FROM		= "info@accurtetrussbrace.com"; // Net mail from 
	public final String CC			= "tom@1888nailgun.com"; // Net mail from 
	public final String SECURE		= "https://secure2.inetu.net/accuratetrussbrace.com";*/
}