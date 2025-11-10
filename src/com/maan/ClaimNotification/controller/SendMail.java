package com.maan.ClaimNotification.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.HashMap;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import proj.date.DateFunction;
import com.maan.ClaimNotification.DAO.ClaimNotificationBean;
import com.maan.DBCon.DBConnectionStatus;
//import jakarta.mail.*;
//import jakarta.mail.internet.*;
//import javax.activation.*;

public class SendMail extends HttpServlet
{
		com.maan.Home.DataManipualtion.DataSelect Cover = new com.maan.Home.DataManipualtion.DataSelect();
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{
				ProcessResult(request,response);
		}
		public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{
				ProcessResult(request,response);
		}
		public void ProcessResult(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
		{
			ClaimNotificationBean claim = new ClaimNotificationBean();
			response.setContentType("text/html");
			HttpSession session = request.getSession(true);
			String path = request.getContextPath();
			String usrModeSC = (String)session.getAttribute("userLoginMode");
			usrModeSC = usrModeSC==null?"":usrModeSC;
			DBConnectionStatus.statusStatic=usrModeSC;
			if(session.getAttribute("ses")==null){
				response.sendRedirect(path+"/login/error_messg.jsp");
				return;
			}
			java.text.NumberFormat fmt = new java.text.DecimalFormat("##,##0");

			String policyNumber = (String)request.getAttribute("PolicyNumber");
			String claimNumber 	= (String)request.getAttribute("ClaimNumber");
			String status 		= (String)request.getAttribute("Status");
			String remarks 		= (String)request.getAttribute("Remarks");

			policyNumber = policyNumber==null?"":policyNumber;
			claimNumber = claimNumber==null?"":claimNumber;
			status =status==null?"":status;
			remarks = remarks==null?"":remarks;

			String emailMsgTxt ="";
			String emailSubjectTxt = "Policy Number "+policyNumber+", RSA Insurance - Claim Response";
			String fullAddr = "";
			//Policy Number 1000, RSA INsurance - Claim Response

			if(status.equalsIgnoreCase("A")){
				status = "Accepted";
			}
			else if(status.equalsIgnoreCase("P")){
				status = "Pending";
			}
			else if(status.equalsIgnoreCase("R")){
			    status = "Rejected";
			}
			try{
				String messs[][] =null;// travelMail.BROKER_COMPANY_MASTER_SELECT(LoginId);
				if(messs != null && messs.length >0 ){
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			try{
				//We are pleased to offer the quotation for your Travel Insurance. In case of any query please contact at the under mentioned address.
				//emailMsgTxt = "<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'> Dear Sir<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thank you for getting a quote from RSA. Please find attached the quotation with the terms, conditions and exclusions applicable to your insurance. In case of any query, please contact the undersigned. <br><br><br>Regards<br>"+fullAddr+"</font><br><br><br>";
				emailMsgTxt = "<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'> Dear Customer<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Your Claim(Claim Number:<b>"+claimNumber+"</b>) for policy number :<b>"+policyNumber+"</b> has been in "+status+" state </font><br><br>";
				emailMsgTxt = emailMsgTxt + "<font style='font-family: Arial, Helvetica, sans-serif' size = 2 face = 'arial'>Reason for "+status+":<br>&nbsp;&nbsp;"+remarks+"</font>";
				String bremail="";
				String mailFrom[][] = new String[0][0];
				mailFrom = Cover.getFromEmailForLogin((String)session.getAttribute("user"));
				if(mailFrom.length>0){
					bremail = mailFrom[0][0]!=null?mailFrom[0][0]:"";
				}
				String toId = claim.getEmailIdForPolicy(policyNumber);
				toId = toId == null ? "" : toId;
				String[] emailList = {""+toId};
				SendMail home = new SendMail();
				String branch = (String)session.getAttribute("LoginBranchCode");
				HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
				String cid = "";
				String currencyType = "";
				if(brokerDetails.size()>0){
					cid = (String)brokerDetails.get("Orgination");
					currencyType = (String)brokerDetails.get("CurrencyAbb");
				}
				bremail = "maansarovaraoic@gmail.com";
				 final String basePaths = request.getSession().getServletContext().getRealPath("/"+"MailServerInfo/MailServerInfo.xml");
				home.postMail(emailList,emailSubjectTxt,emailMsgTxt,bremail,branch,basePaths);
		}
		catch(Exception e1){
            e1.printStackTrace();
        }
	}
	// create some properties and get the default Session
		String SMTP_AUTH_USER = "";
		String SMTP_AUTH_PWD  = "";
		String SMTP_HOST_NAME = "";

		public void postMail(String recipients[],String subject, String message, String emailFromAddress,String branch,String path) throws MessagingException
		{
			try{
				final com.maan.common.MailServerInfo mailInfo = new com.maan.common.MailServerInfo();
				//final HashMap PortDet = mailInfo.getMailServerInfo(branch,path);
				final HashMap PortDet = Cover.getMailInfo(branch);
				//HashMap PortDet = Cover.getPortDetails();
				SMTP_HOST_NAME = (String)PortDet.get("hostname");   // Getting the values from beans in the form of HashMap
				SMTP_AUTH_USER = (String)PortDet.get("username");
				SMTP_AUTH_PWD = (String)PortDet.get("password");
				boolean debug = false;
				Properties props = new Properties();
				props.put("mail.smtp.host", SMTP_HOST_NAME);
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				Authenticator auth = new SMTPAuthenticator();
				Session session = Session.getInstance(props, auth);
				session.setDebug(debug);
				Message msg = new MimeMessage(session);
				if("".equals(emailFromAddress)){
				   emailFromAddress = "royalsupport@maansarovar.com";
				}
				InternetAddress addressFrom = new InternetAddress(emailFromAddress);
				msg.setFrom(addressFrom);
				try{
					InternetAddress[] addressTo = new InternetAddress[recipients.length];
					for (int i = 0; i < recipients.length; i++){
						addressTo[i] = new InternetAddress(recipients[i]);
					}
					msg.setRecipients(Message.RecipientType.TO, addressTo);
				}
				catch(Exception bdd){
					System.out.println("Error in to address"+bdd);
					bdd.printStackTrace();
				}
				msg.setSubject(subject);
				Multipart multiPart = new MimeMultipart();
				multiPart.addBodyPart(getHtmlMailContent(message));
				//multiPart.addBodyPart(getMailAttachment(path1,qnum));
				msg.setContent(multiPart);
				Transport.send(msg);
				System.out.println("Message send successfully");
			}
	catch(Exception ex)
	{
		System.out.println(ex);
        ex.printStackTrace();
	 }
} //Post Mail

	private  class SMTPAuthenticator extends jakarta.mail.Authenticator
	{
	   public PasswordAuthentication getPasswordAuthentication()
	   {
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
		}
	}

    /*public BodyPart getMailAttachment(String path ,String qnum) throws MessagingException
	{

        String attach = path+"/PremiumSummary_PrintQuote_"+qnum+".pdf";
        BodyPart messageAttach = null;
        DataSource source =  null;
        try{
		    messageAttach = new MimeBodyPart();
		    source = new FileDataSource(attach);
		    messageAttach.setDataHandler(new DataHandler(source));
		    String fileName = attach.substring(attach.lastIndexOf("/")+1);
		    messageAttach.setFileName(fileName);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
		return messageAttach;
	}*/

    public MimeBodyPart getHtmlMailContent(String content) throws MessagingException
    {
       MimeBodyPart messageBody1 = new MimeBodyPart();
       messageBody1.setContent(content,"text/html");
	   return messageBody1;
    }

} // Class






