package com.maan.common.login;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class LogInService {
	private static final Logger logger = LogUtil.getLogger(LogInService.class);
	
	LogInDAO dao=new LogInDAO();
    String SMTP_AUTH_USER; 
	String SMTP_AUTH_PWD;

	public String getClientIpAddr(HttpServletRequest request) {
		return dao.getClientIpAddr(request);
	}
	
	public boolean insertSessionInfo(final String loginId, final String sessionId, final String ipAddress){
		return dao.insertSessionInfo(loginId, sessionId, ipAddress);
	}
	
	public boolean updateSessionInfo(final String userId, final String sessionId){
		return dao.updateSessionInfo(userId, sessionId);
	}
	
	public String[] validateUser(final String userId, final String password,final String display, final String appId,String pwdcount){
        return dao.validateUser(userId, password,display,appId,pwdcount);
    }
	public boolean checkPasswordChange(final String userId, String userStatus,String expiTime,String expiDate, String appId){
        return dao.checkPasswordChange(userId, userStatus,expiTime,expiDate,appId);
    }
	
	public List <Object>  getUserInfo(final AdminAction ac, final String userId, String appId){
        return dao.getUserInfo(ac, userId, appId);
    }
	
	public List <Object>  getUserInfo(final String userId, String appId){
        return dao.getUserInfo(userId, appId);
    }
	
	public String changePassword(String userId, String newPassword, String appId) {
        return dao.changePassword(userId, newPassword, appId);
	}
	
	public List<Object>  validateMailForgot(Object[] obj) {
        return dao.validateMailForgot(obj);
	}
	
	public String getAppName(String appId) {
        return dao.getAppName(appId);
	}
	
	public List<Object>  getappList() {
        return dao.getappList();
	}
	
	public Map<String, String> getMailDetails(String appId){
	    return dao.getMailDetails(appId);
	}
	
	public String sendUserPwd(final String user, String temp, String appId){
	 	List<Object>  list=null;
	 	String mail="";
		String expireTime="";
		String result="";
		try{
			Map<String, String> mapt=dao.getMailDetails(appId);
			list=dao.getUserInfo(user,appId);
			Map rs = (Map)list.get(0);
			mail=(String)rs.get("USER_MAIL");
			expireTime=dao.getExpireTime((String)mapt.get("EXP_TIME"));
			if(mail==null){
				result = "User doesn't Exists";	//E111=
			}else{
				String password =dao.getPassword(user,appId);			
				Map<String, String> details=new HashMap<String, String>();
				if(details != null){
					details.put("MAIL_TO", mail);
					details.put("MAIL_CC", "");// (String)mapt.get("MAIL_CC"));
					details.put("USER", user);
					details.put("PASSWORD", password);
					details.put("SMTP_HOST", (String)mapt.get("SMTP_HOST"));
					details.put("SMTP_USER", (String)mapt.get("SMTP_USER"));
					details.put("SMTP_PWD", (String)mapt.get("SMTP_PWD"));
					details.put("MAIL_FROM", (String)mapt.get("SMTP_USER"));
					details.put("expireTime", expireTime);
					details.put("SMTP_PORT", (String)mapt.get("SMTP_PORT"));
			}
			result = sendPasswordMail(details,temp);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return result;
	 }
	
    public String sendPasswordMail(Map<String, String> details, String temp){
    	String result = null;
	    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
    	try{
	    	if(details !=null && !details.isEmpty()){
	    		String SMTP_HOST_NAME = details.get("SMTP_HOST");
	    		String user = details.get("SMTP_USER");
	    		String pwd = details.get("SMTP_PWD");
	    		String SMTP_MAIL_FROM = details.get("MAIL_FROM");
	    		String subject = "Ecargo-Password Reset";
	    		String toAddress = details.get("MAIL_TO");
	    		String ccAddress =""; ///details.get("MAIL_CC");
	    		String port= details.get("SMTP_PORT");
	    		if(toAddress!=null && !"".equals(toAddress)){
		    		String[] toAddresses = (toAddress.indexOf(",")!=-1)?toAddress.split(","):new String[]{toAddress};
		    		String[] ccAddresses = new String[0];
		    		if(ccAddress!=null && !"".equals(ccAddress)){
		    			ccAddresses = (ccAddress.indexOf(",")!=-1)?ccAddress.split(","):new String[]{ccAddress};
		    		}
		    		StringBuffer msg = new StringBuffer();
		        	msg.append("<br /><table width='100%' align='center'><tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append("Dear "+(String)details.get("USER")+",</td></tr><tr><td>&nbsp;</td></tr>");
		        	if("locked".equalsIgnoreCase(temp)){
		        		msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
			        	msg.append("Your UserId is locked due to invalid login of 3 times.</td></tr><tr><td>&nbsp;</td></tr>");
		        	}
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append(ltp.findDefaultText("mail.forgot.pass.success", Locale.ENGLISH, new String[]{})+"</td></tr><tr><td>&nbsp;</td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'>");
		        	msg.append("(This Password will be expired at "+(String)details.get("expireTime")+")</td></tr><tr><td>&nbsp;</td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'><b>");
		        	msg.append("LoginID: "+(String)details.get("USER")+"</b></td></tr>");
		        	msg.append("<tr><td style='font-family: Arial, Helvetica, sans-serif; font-size: 14px; height: 25px;'><b>");
		        	msg.append("Password: "+(String)details.get("PASSWORD")+"</b></td></tr><tr><td>&nbsp;</td></tr></table>");
		        	msg.append(getMailSignature());
		        	StringBuffer mailDatas = new StringBuffer();
		    		mailDatas.append("<html><head></head>");
		    		mailDatas.append("<body><table style='min-height: 200px;' align='left'>");
		    		mailDatas.append("<tr height='20'><td>&nbsp;</td></tr><tr><td align='left'>");
		    		mailDatas.append(msg);
		    		mailDatas.append("</td></tr><tr height='20'><td>&nbsp;</td></tr>");
		    		mailDatas.append("<tr><td><p>");
		    		mailDatas.append("</table></body></html>");
		    		String message = mailDatas.toString();
		    		sendResponseMail(SMTP_HOST_NAME, user, pwd, SMTP_MAIL_FROM, subject, message, toAddresses, ccAddress,port);
	    		}
	    	}
    	}catch(Exception e){
    		System.out.println("Exception Send mail => {}"+e);
    		result = e.getMessage();
    	}
    	return result;
    }
    
    public String getMailSignature(){
    	StringBuffer msg = new StringBuffer();
    	msg.append("<br /><table width='100%'><tr><td><span style='font-size: 14px;'>");
    	msg.append("Best Regards,<br /><br />Ecargo Admin Team");
    	msg.append("</td></tr><tr><td><img src=\"C:\\Documents and Settings\\Administrator\\Desktop\\HEAD1.GIF\" />");
    	msg.append("<img src=\"C:\\Documents and Settings\\Administrator\\Desktop\\HEAD2.GIF\" />");
    	msg.append("</td></tr>");    	
    	msg.append("</table>");
    	return msg.toString();
    }
    
    public void sendResponseMail(final String SMTP_HOST_NAME, final String user,  final String pwd, final String SMTP_MAIL_FROM, final String subject,
    		final String message, final String[] toAddress, final String ccAddress,String SMTP_PORT){
    	
    	SMTP_AUTH_USER = user;
    	SMTP_AUTH_PWD = pwd;
    	try{
	    	Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.port", SMTP_PORT);
			props.put("mail.smtp.starttls.enable", "true");

			Session session = null; 
			if(SMTP_AUTH_PWD != null && !"".equals(SMTP_AUTH_PWD.trim())){
				props.put("mail.smtp.auth", "true");
				Authenticator auth = new SMTPAuthenticator();
				session = Session.getInstance(props, auth);
			}else{
				props.put("mail.smtp.auth", "false");
				session = Session.getInstance(props);
			}
			session.setDebug(false);
			Message msg1 = new MimeMessage(session);
			InternetAddress addressFrom = new InternetAddress(SMTP_MAIL_FROM, "Ecargo");
			msg1.setFrom(addressFrom);
			if(toAddress != null && toAddress.length>0){
				InternetAddress[] addressTo = new InternetAddress[toAddress.length];			
				for (int i = 0; i < toAddress.length; i++){
					addressTo[i] = new InternetAddress(toAddress[i]);
					msg1.addRecipient(Message.RecipientType.TO, addressTo[i]);
				}
			}
			/*			if(ccAddress != null && ccAddress.length>0){
				InternetAddress[] addressToCC = new InternetAddress[ccAddress.length];			
				for(int i=0;i<ccAddress.length;i++){
					addressToCC[i] = new InternetAddress(ccAddress[i]);
					msg1.addRecipient(Message.RecipientType.CC, addressToCC[i]);
				}
			}*/
			if(ccAddress != null){ 
				InternetAddress[] myToList = InternetAddress.parse(ccAddress);
				msg1.addRecipients(Message.RecipientType.CC, myToList);
			}

			msg1.setSubject(subject);
			msg1.setContent(message, "text/html");
			System.out.println(msg1);
			Transport.send(msg1);
		}catch(Exception e){
			System.out.println(e);
		}
    }
    
    private class SMTPAuthenticator extends jakarta.mail.Authenticator{
		public PasswordAuthentication getPasswordAuthentication(){
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PWD;
			return new PasswordAuthentication(username, password);
		}
	}
}