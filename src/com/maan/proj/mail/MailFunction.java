package com.maan.proj.mail;

import proj.util.Constant;

import sun.net.smtp.SmtpClient;
import java.io.*;

/**
  * <ul>
  * <li> Author  : Manikandan.
  *	<li> Project : Common file for all projects.
  * <li> Purpose : To sent a mail to the client.	  
  *	<blockquote> This class is extends Thread class, to send the mail as seperate process. </blockquote>
  * </ul>
  */
 
public class MailFunction extends Thread implements Constant
{
	StringBuffer subject = new StringBuffer(); // content of the mail.
	String from		 	 = new String();	   // from address of the mail.
	String to			 = new String();	   // to address of the mail.
	String mailSubject	 = new String();	   // subject of the mail.


	/**
	  * Constructor to set the user's value to this class.
	  * @param subject content of the mail.
	  * @param from from address of the mail.
	  * @param to to address of the mail.
	  * @param mailSubject subject of the mail.
	  */

	public MailFunction(StringBuffer subject, String  from, String to, String mailSubject)
	{
		this.subject	 = subject;
		this.from		 = from;
		this.to			 = to;
		this.mailSubject = mailSubject;
	}


	/**
	  * Function to start the mail thread.
	  * @param subject content of the mail.
	  * @param from from address of the mail.
	  * @param to to address of the mail.
	  * @param mailSubject subject of the mail.
	  * @return void.
	  */
	
	public void run()
	{
		try
		{
			// call the sendMail method to send the mail.
			sendMail(subject, from, to, mailSubject);			
		}
		catch (Exception e)
		{
		}
	}
	

	/**
	  * Function to send the mail.
	  * @param subject content of the mail.
	  * @param from from address of the mail.
	  * @param to to address of the mail.
	  * @param mailSubject subject of the mail.
	  * @return String.
	  */

	public String sendMail(StringBuffer subject,String from, String to, String mailSubject)
	{
		try
		{
			//Pass a string containing the name of your smtp server as a parameter to the following constructor                                     
			/**
			  * On Net
			  * SmtpClient smtp = new SmtpClient("mail.yahoo.com");
			  */
			//mailrecv.bigmailbox.com
			SmtpClient smtp = new SmtpClient(MAILBOX); // for net mail.bigmailbox.com, local 172.42.172.21
			smtp.from(from);
			
			//Pass the email address of the recipient of the message to the next method.                      
			smtp.to(to);
			smtp.to(CC);
			
			//smtp.replyTo("martin@insurancemust.com");
			//Get an output stream for the message
			PrintStream msg = smtp.startMessage();
			msg.println("To: "+to);
			msg.println("CC: "+CC);
			msg.println("Subject: "+mailSubject);
			//msg.println("Content-type: text/html\n\n");
			msg.println();
			
			msg.println(subject.toString());
			msg.println(new java.util.Date());//put date and time in msg
			smtp.closeServer();
		}
		catch(Exception e)
		{
			  return "General Error Occured During the mail send : "+e;
		}
	
		return "Your message has been sent";
    }

	
	/**
	  * To send a mail without thread.
	  * @return String.
	  */

	public String startMail()
	{
		String retValue = "";
		try
		{
			// call the sendMail method to send the mail.
			retValue = sendMail(subject, from, to, mailSubject);			
		}
		catch (Exception e)
		{
			retValue = "Error in mail function : "+e;
		}

		return retValue;
	}

}
