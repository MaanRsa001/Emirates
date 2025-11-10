package com.maan.proj.mail;

import java.util.Properties;import java.util.StringTokenizer;import java.util.Vector;import jakarta.activation.DataHandler;import jakarta.activation.DataSource;import jakarta.activation.FileDataSource;import jakarta.mail.Address;import jakarta.mail.BodyPart;import jakarta.mail.Message;import jakarta.mail.MessagingException;import jakarta.mail.Multipart;import jakarta.mail.Session;import jakarta.mail.Transport;import jakarta.mail.internet.AddressException;import jakarta.mail.internet.InternetAddress;import jakarta.mail.internet.MimeBodyPart;import jakarta.mail.internet.MimeMessage;import jakarta.mail.internet.MimeMultipart;import proj.util.HtmlReader;


public class  SimpleMail
{
	String host = "";
	String from = "";
	String to	= "";
	String subject = "";
	String content = "";
	String attach = "";
	String cc = "";
	String bcc = "";
	String file = "";
			
	public SimpleMail(String host, String from, String to, String subject, String content)
	{
		this.host	 = host;
		this.from	 = from;
		this.to		 = to;
		this.subject = subject;
		this.content = content;
	}

	public void send() throws AddressException, MessagingException
	{
		Properties prop = new Properties();
		Session session = Session.getDefaultInstance(prop, null);

		Address fromAdd = new InternetAddress(from);
		Address[] toAdd	= checkRecipients(to);

		MimeMessage	message = new MimeMessage(session);
		message.setFrom(fromAdd);
		message.setRecipients(Message.RecipientType.TO, toAdd);

		if(cc.length() > 0)
		{
			Address[] ccAdd	= checkRecipients(cc);
			message.setRecipients(Message.RecipientType.CC, ccAdd);
		}

		if(bcc.length() > 0)
		{
			Address[] bccAdd	= checkRecipients(bcc);
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
		}
			
		//message.setContent(content, "text/plain");
		message.setSubject(subject);

		Multipart multiPart = new MimeMultipart();
		
		if(file.length() <= 0)
			multiPart.addBodyPart(getMailContent());
		else
			multiPart.addBodyPart(getMailContent(file));

		if(attach.length() > 0)
			multiPart.addBodyPart(getMailAttachment());

		message.setContent(multiPart);

		Transport.send(message); 
	}

	public MimeBodyPart getMailContent() throws MessagingException
	{
		MimeBodyPart messageBody = new MimeBodyPart();
		messageBody.setText(content);
		return messageBody;
	}

	public MimeBodyPart getMailContent(String fileName) throws MessagingException
	{
		MimeBodyPart messageBody = new MimeBodyPart();
		messageBody.setContent(readFile(fileName), "text/html");
		return messageBody;
	}



	public BodyPart getMailAttachment() throws MessagingException
	{
		BodyPart messageAttach = new MimeBodyPart();
		DataSource source = new FileDataSource(attach);
		messageAttach.setDataHandler(new DataHandler(source));
		
		String fileName = attach.substring(attach.lastIndexOf("/")+1);
		messageAttach.setFileName(fileName);
		return messageAttach;
	}

	public String readFile(String fileName)
	{
		String arr[][] = {{"%%%%",""}};
		return HtmlReader.replaceValue(fileName, arr).toString();
	}

	public InternetAddress[] checkRecipients(String toAddress) throws AddressException
	{
		Vector v = new Vector();
		StringTokenizer token = new StringTokenizer(toAddress,",");
		while(token.hasMoreTokens())
		{
			String s = token.nextToken();
			v.addElement(s);
		}

		if (v.size() > 1)
		{
			InternetAddress[] address = new InternetAddress[v.size()];
			for(int i=0; i < v.size(); i++)
			{
				address[i] = new InternetAddress((String)v.elementAt(i));
			}

			return address;
		}
		else
		{
			InternetAddress address[] =  {new InternetAddress(toAddress)};
			return address;
		}
		
	}

	public void setHost(String host)
	{
		this.host = host;
	}

	public void setFrom(String from)
	{
		this.from = from;
	}

	public void setTo(String to)
	{
		this.to = to;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public void setAttach(String attach)
	{
		this.attach = attach;
	}

	public void setFile(String file)
	{
		this.file = file;
	}

	public void setCC(String cc)
	{
		this.cc = cc;
	}

	public void setBCC(String bcc)
	{
		this.bcc = bcc;
	}

	public String getHost()
	{
		return host;
	}

	public String getFrom()
	{
		return from;
	}

	public String getTo()
	{
		return to;
	}

	public String getContent()
	{
		return content;
	}

	public String getSubject()
	{
		return subject;
	}

	public String getAttach()
	{
		return attach;
	}

	public String getCC()
	{
		return cc;
	}

	public String getBCC()
	{
		return bcc;
	}

	public String getFile()
	{
		return file;
	}

}
