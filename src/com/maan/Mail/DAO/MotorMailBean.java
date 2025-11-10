package com.maan.Mail.DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class MotorMailBean
{
	private static final Logger logger = LogUtil.getLogger(MotorMailBean.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String EMAIL = "email";
	final static transient private String USERNAME = "username";
	final static transient private String BREAK = "<br>";
	
	public Map getCustomerInfo(String quoteNo) throws BaseException	
	{
		logger.info("MotorMailBean  getCustomerInfo method()");
		logger.debug(ENTER);
		
		Map mailDetails = new HashMap();
		final String values[] = {quoteNo,quoteNo};
		final String sqlQuery = "select nvl(pf.email,''),initcap(pf.first_name), pf.address1, pf.address2, initcap(pf.country), pf.telephone, pf.title,pf.company_name, to_char(pm.entry_date,'dd-mm-YYYY') " +
				"from personal_info pf, home_position_master pm where pf.customer_id= pm.customer_id " +
				"and pf.customer_id = (select customer_id from home_position_master where quote_no = ?) and pm.quote_no=? ";
		mailDetails = getHashDetails(mailDetails,values,sqlQuery);
		
		logger.debug(EXIT);
			
		
		return mailDetails;
	}
	
	public Map getHashDetails(final Map details,final String values[],final String sqlQuery)throws BaseException{
		final String result[][] = runner.multipleSelection(sqlQuery,values);
		if(result.length>0) {
				details.put(EMAIL, isNull(result[0][0],"0"));
				details.put("firstname", isNull(result[0][1], result[0][7]));
				details.put("quotdate", isNull(result[0][8],""));
				details.put("title", isNull(result[0][6],"M/S"));
				details.put("comp", isNull(result[0][7],result[0][1]));
				details.put("addr1", isNull(result[0][2],""));
				details.put("addr2", isNull(result[0][3],""));
				details.put("count", isNull(result[0][4],""));
				details.put("tele", isNull(result[0][5],""));
				
			}
		return details;
	}
	public String isNull(final String content,final String value)throws BaseException{
		final StringBuffer contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else if(content.equalsIgnoreCase("select")){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
	
	
	
	public String getEmailSubject(){
		logger.info("MotorMailBean  getEmailSubject  method()");
		logger.debug(ENTER);
		
		String subMsg = "";
		
		subMsg = "Motor Insurance Quotation";
		
		logger.debug(EXIT);
		
		
		return subMsg;		
	}	
	
	public StringBuffer getEmailMsgTxt(final String applicationNo,final HashMap brokerDetails) throws BaseException
	{
		logger.info("MotorMailBean  getEmailMsgTxt  method()");
		logger.debug(ENTER);
		StringBuffer msg = new StringBuffer(20000);
		
		String quoteNo = "";
		String customerId = "";
		
		final String[] args = {applicationNo};
		String query = "select quote_no,customer_id from home_position_master where application_no = ?";
		String[][] details = runner.multipleSelection(query,args);
		if(details != null && details.length > 0){
			quoteNo =  details[0][0]==null?"":details[0][0];
			customerId=details[0][1]==null?"":details[0][1];
		}
		
		
		msg.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>Please find enclose Quote no:&nbsp;<b>");
		msg.append(quoteNo);
		msg.append("</b>&nbsp;&nbsp;with this mail for your kind persual. Looking forward for your reply</font>");
		
		StringBuffer detailedMsg = getDetailedTable(quoteNo,customerId);
		
		msg.append(detailedMsg);
		
		logger.debug(EXIT);
		
		return msg;		
	}
	
	public StringBuffer getDetailedTable(String quoteNo,String customerId) throws BaseException
	{

		logger.info("MotorMailBean getDetailedTable method()");
		logger.debug(ENTER);
		final MailerBean mailer = new MailerBean();
		final MailBean mailBeans = new MailBean();
		final StringBuffer messages = new StringBuffer(10000);
		
		messages.append("<html><head></head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>" +
				"<meta name='GENERATOR' content='Microsoft FrontPage 4.0'><meta name='ProgId' content='FrontPage.Editor.Document'>" +
				"<meta name='Microsoft Theme' content='rmnsque 1111'><br><p align = 'center'><b><u><font face = 'Arial' font size = 3>" +
				"<br>QUOTE INFORMATION</u></font></p><table border='0' width='100%' height='50'><tr> <td height='22'><font size = 2 face = 'Arial'>To, <br>");
		
		Map mailDet =  getCustomerInfo(quoteNo);
		
				
		messages.append(isNull((String)mailDet.get("title"),"")+"&nbsp;"+isNull((String)mailDet.get("firstname"),"")+BREAK);
		messages.append(isNull((String)mailDet.get("addr1"),""));
		messages.append(BREAK+isNull((String)mailDet.get("count"),""));
		messages.append(BREAK+mailer.isNull((String)mailDet.get("tele"),"")+BREAK);
		
		
		String[][] quoteDet = getQuoteDetails(quoteNo);
		
		if(quoteDet != null && quoteDet.length>0){
			
			messages.append("</td><td height='22'><b><font size = 2 face = 'Arial'>Quotation&nbsp;No:&nbsp;&nbsp;&nbsp;");
			messages.append(quoteNo);
			messages.append("<br>Quotation&nbsp;Date:&nbsp;");
			messages.append(quoteDet[0][0]==null?"&nbsp;":quoteDet[0][0]);
			messages.append(" </td></b></tr><table border=1 width='100%'><TR align=left>" +
							"<TD><font size = 2 face = 'Arial'><STRONG>Cover Start Date : </STRONG></TD><TD><font size = 2 face = 'Arial'>" +
							"<STRONG>");
			messages.append(quoteDet[0][1]==null?"&nbsp;":quoteDet[0][1]);
			messages.append("</STRONG></TD><TD><font size = 2 face = 'Arial'><STRONG>Make  : </STRONG></TD>");
			
			messages.append("<TD><font size =2 face = 'Arial'><STRONG>");
			messages.append(quoteDet[0][2]==null?"&nbsp;":quoteDet[0][2]);
			messages.append("</STRONG></TD><TR align=left><TD> <font size = 2 face = 'Arial'><STRONG>Model : </STRONG></TD>" +
					"<TD><font size= 2 face = 'Arial'><STRONG>");
			messages.append(quoteDet[0][3]==null?"&nbsp;":quoteDet[0][3]);
			messages.append("</STRONG></TD><TD><font size = 2 face ='Arial'><STRONG>Foreign Sum Insured : </STRONG></TD><TD><font size = 2 face='Arial'><STRONG>");
			messages.append(quoteDet[0][4]==null?"&nbsp;":quoteDet[0][4]);
			messages.append("</STRONG></TD><TR align=left><TD><font size=2 face ='Arial'><STRONG>Local Sum Insured </STRONG>" +
					"<TD><font size = 2 face = 'Arial'><STRONG>");
			messages.append(quoteDet[0][5]==null?"&nbsp;":quoteDet[0][5]);
			messages.append("</STRONG></td><Td>&nbsp;</td><Td>&nbsp;</td></tr>");
			
		}
		messages.append("</table>");
		
		logger.debug(EXIT);	
		
		return messages;
	
	}
	
	public String[][] getQuoteDetails(String quoteNo) throws BaseException
	{
		logger.info("MotorMailBean getDetailedTable method()");
		logger.debug(ENTER);
		
		String query  =  "select to_char(motor.entry_date,'dd-mm-yyyy'),to_char(motor.inception_date,'dd-mm-yyyy'),make.MAKE_NAME,model.MODEL_NAME,motor.SUMINSURED_VALUE_FOREIGN,motor.SUMINSURED_VALUE_LOCAL from motor_data_detail motor,MOTOR_MAKE_MASTER make,MOTOR_MODEL_MASTER model "
						+"where motor.quote_no = "+quoteNo+" and make.MAKE_ID =motor.MAKE_ID and model.MODEL_ID = motor.model_id and amend_id = (select max(amend_id) from motor_data_detail where quote_no = "+quoteNo+")";
		
		String[][] result = runner.multipleSelection(query);
		
		logger.debug(EXIT);	
		
		
		return result;
	}
	
	
}
