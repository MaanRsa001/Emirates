package master.bean;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

import com.maan.services.util.runner;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import proj.util.HtmlReader;

public class SimpleMail {
	String host = "";

	String from = "";

	String to = "";

	String subject = "";

	String content = "";

	String attach = "";

	String cc = "";

	String bcc = "";

	String file = "";

	String SMTP_AUTH_USER = "", SMTP_HOST_NAME = "", SMTP_AUTH_PWD = "";

	Connection con = null;

	Statement smt = null;

	String sqlQuery1 = null;

	String as1[][] = new String[0][0];

	/*
	 * public SimpleMail( String from, String to, String subject, String
	 * content) {
	 * 
	 * this.host = host; this.from = from; this.to = to; this.subject = subject;
	 * this.content = content;
	 *  }
	 */

	public void send() throws AddressException, MessagingException {

		try {

			sqlQuery1 = "select host_name,user_name, password, remarks, from_address from bhomemail_constant_values";
			System.out.println("Query is" + sqlQuery1);

			as1 = runner.multipleSelection(sqlQuery1, new String[0]);
		} catch (Exception e) {
			System.out.println("getting mail user,pwd prob" + e);
		}
		if (as1.length > 0) {
			SMTP_HOST_NAME = as1[0][0]; // Getting the values from beans in the
										// form of Hashtable
			SMTP_AUTH_USER = as1[0][1];
			SMTP_AUTH_PWD = as1[0][2];
		}

		System.out.println("Host name" + SMTP_HOST_NAME);
		System.out.println("Authorized usr" + SMTP_AUTH_USER);
		System.out.println("Authorized pss" + SMTP_AUTH_PWD);

		System.out.println("Inside postMail");
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Authenticator auth = new SMTPAuthenticator();
		boolean debug = false;
		Session session = Session.getInstance(props, auth);
		session.setDebug(debug);
		Message message = new MimeMessage(session);
		try {

			Address fromAdd = new InternetAddress(from, "Better Homes");
			Address[] toAdd = checkRecipients(to);

			// Message message = new MimeMessage(session);
			message.setFrom(fromAdd);
			message.setRecipients(Message.RecipientType.TO, toAdd);
		} catch (Exception e) {
		}
		if (cc.length() > 0) {
			Address[] ccAdd = checkRecipients(cc);
			message.setRecipients(Message.RecipientType.CC, ccAdd);
		}

		if (bcc.length() > 0) {
			Address[] bccAdd = checkRecipients(bcc);
			message.setRecipients(Message.RecipientType.BCC, bccAdd);
		}

		// message.setContent(content,"text/html");

		message.setSubject(subject);

		Multipart multiPart = new MimeMultipart();

		/*
		 * if(file.length() <= 0) multiPart.addBodyPart(getMailContent()); else
		 * multiPart.addBodyPart(getMailContent(file));
		 */

		if (attach.length() > 0)
			multiPart.addBodyPart(getMailAttachment());

		multiPart.addBodyPart(getHtmlMailContent());

		message.setContent(multiPart);
		System.out.println("host" + host + "from" + from + "to" + to
				+ "subject" + subject + "content" + content);
		Transport.send(message);

	}

	public MimeBodyPart getMailContent() throws MessagingException {
		MimeBodyPart messageBody = new MimeBodyPart();

		messageBody.setText(content);
		return messageBody;
	}

	public MimeBodyPart getHtmlMailContent() throws MessagingException {
		MimeBodyPart messageBody1 = new MimeBodyPart();
		messageBody1.setContent(content, "text/html");
		return messageBody1;
	}

	public MimeBodyPart getMailContent(String fileName)
			throws MessagingException {
		MimeBodyPart messageBody = new MimeBodyPart();
		messageBody.setContent(readFile(fileName), "text/html");
		return messageBody;
	}

	public BodyPart getMailAttachment() throws MessagingException {
		BodyPart messageAttach = new MimeBodyPart();
		DataSource source = new FileDataSource(attach);
		messageAttach.setDataHandler(new DataHandler(source));

		String fileName = attach.substring(attach.lastIndexOf("/") + 1);
		messageAttach.setFileName(fileName);
		return messageAttach;
	}

	public String readFile(String fileName) {
		String arr[][] = { { "%%%%", "" } };
		return HtmlReader.replaceValue(fileName, arr).toString();
	}

	public InternetAddress[] checkRecipients(String toAddress)
			throws AddressException {
		Vector v = new Vector();
		StringTokenizer token = new StringTokenizer(toAddress, ",");
		while (token.hasMoreTokens()) {
			String s = token.nextToken();
			v.addElement(s);
		}

		if (v.size() > 1) {
			InternetAddress[] address = new InternetAddress[v.size()];
			for (int i = 0; i < v.size(); i++) {
				address[i] = new InternetAddress((String) v.elementAt(i));
			}
			System.out.println("addresss is" + address[0]);
			return address;
		} else {
			InternetAddress address[] = { new InternetAddress(toAddress) };
			System.out.println("addresss is" + address[0]);
			return address;
		}

	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setCC(String cc) {
		this.cc = cc;
	}

	public void setBCC(String bcc) {
		this.bcc = bcc;
	}

	public String getHost() {
		return host;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getContent() {
		return content;
	}

	public String getSubject() {
		return subject;
	}

	public String getAttach() {
		return attach;
	}

	public String getCC() {
		return cc;
	}

	public String getBCC() {
		return bcc;
	}

	public String getFile() {
		return file;
	}

	private class SMTPAuthenticator extends jakarta.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			System.out.println("Inside authentication");
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}
