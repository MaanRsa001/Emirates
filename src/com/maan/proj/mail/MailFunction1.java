package com.maan.proj.mail;

import proj.util.Constant;

import sun.net.smtp.SmtpClient;
import java.io.*;
 
public class MailFunction1 extends Thread implements Constant
{
	StringBuffer subject = new StringBuffer();
	String from		 	 = new String();
	String to			 = new String();
	String mailSubject	 = new String();

	public static String testString    = new String();

	public MailFunction1(StringBuffer subject, String  from, String to, String mailSubject)
	{
		this.subject	 = subject;
		this.from		 = from;
		this.to			 = to;
		this.mailSubject = mailSubject;
	}

	public void run()
	{
		try
		{
			testString = sendMail(subject, from, to, mailSubject);			
		}
		catch (Exception e)
		{
			testString = "Exception in run : "+e;
		}
	}
	
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
			testString += MAILBOX+", ";
			smtp.from(from);

			testString += from+", ";
			
			//Pass the email address of the recipient of the message to the next method.                      
			smtp.to(to);
			//smtp.to("mmani_in@rediffmail.com,mmani_in@yahoo.com,manikandan@mailnova.com");
			testString += to+", ";
			
			//smtp.replyTo("martin@insurancemust.com");
			//Get an output stream for the message
			PrintStream msg = smtp.startMessage();
			msg.println("To: "+to);
			//msg.println("Bcc : mmani_in@rediffmail.com,mmani_in@yahoo.com,manikandan@mailnova.com");
			msg.println("Subject: "+mailSubject);
			//msg.println("Content-type: text/html\n\n");
			msg.println();
			
			msg.println(subject.toString());
			msg.println(new java.util.Date());//put date and time in msg
			smtp.closeServer();
			testString += "End of method";

		}
		catch(Exception e)
		{
			  testString += " Inside catch : "+e;
			  return "General Error Occured During the mail send : "+e+" "+ testString;
		}
	
		return "Your message has been sent";
    }
}
