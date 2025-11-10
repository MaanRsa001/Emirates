package com.maan.Mail.DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.dao.CommonDAO;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.premium.DAO.PremiumLogic;
import com.maan.quotation.dao.QuotationDAO;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;
import com.maan.webservice.dao.PolicyGenerationDAO;

public class MailInformation {
	private static final Logger logger = LogUtil.getLogger(MailInformation.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String EMAIL = "email";
	final static transient private String FGROUP = "FreightGroup";
	final static transient private String FBROKERMAIL = "freBrokerMail";
	final static transient private String FRESTATUS = "freightStatus";
	final static transient private String STATUS = "status";
	final static transient private String BREAK = "<br>";
	final static transient private String ADDRESS = "address1";
	final static transient private String NORMAL = "Normal";
	final static transient private String RATEOPT = "rateOption";
	final static transient private String TRUE = "true";
	final static transient private String COMPVIEW = "CompViewQuote";
	final static transient private String FONTCLOSE = "</font>";
	final static transient private String ADMIN = "Admin";
		public Map getDetailedTable(final String brokerBra, final String cid, final String quoteNo, 
				final String loginId, final String applicationNo,final String CurrencyType,final java.text.NumberFormat fmt1,
				final String mailFrom,final Map freDetails,final String Freight) throws BaseException{
			logger.info("getDetailedTable mailInformation method()");
			logger.debug(ENTER);
			final MailerBean mailer = new MailerBean();
			final MailBean mailBeans = new MailBean();
			final StringBuffer messages = new StringBuffer(10000);
			String finalPremium = null;
			String comment;
			double sumIns=0.0;
			double sum=0.0;
			double rate=0.0;
			String fragile="";
			final Map mailInfo = new HashMap();
				mailBeans.setLoginBra(brokerBra);
				mailBeans.setCid(cid);
				mailBeans.setApplicationNo(applicationNo);
				mailer.setQuoteNo(quoteNo);
				mailer.setLoginId(loginId);
				mailer.setApplicationNo(applicationNo);
				final Map mailDet = mailer.geteMailQuoteNo();
				mailInfo.put("ClientName", mailer.isNull((String)mailDet.get("firstname"),""));
					final String mailId = (String)mailDet.get(EMAIL);
					mailInfo.put("id", mailId);
					final String qDate = mailer.isNull((String)mailDet.get("quotdate"),"");
				final Map brokerDet  = mailer.getBrokerInformation();
					final String brokerName = (String)brokerDet.get("company_name")==null?"":(String)brokerDet.get("company_name");
					mailInfo.put("brokerName",brokerName);
				Map userDet = new HashMap();
				String brokerMail = "";
				if(mailFrom.equalsIgnoreCase(FGROUP)){
					userDet = mailer.getFreightUserInformation(quoteNo);
					final Map freBrokerDet = mailer.getFreightBrokerInformation(quoteNo);
					brokerMail = freBrokerDet.get(EMAIL)==null?"":(String)freBrokerDet.get(EMAIL);
					mailInfo.put(FBROKERMAIL, brokerMail);
					mailInfo.put("freUserMail", mailer.isNull((String)userDet.get(EMAIL), ""));
					mailInfo.put("freUserName", mailer.isNull((String)userDet.get("first_name"), ""));
				}
				final Map fullDetails = mailBeans.getPremiumDetails();
				
				final String transportName=(String)fullDetails.get("transportName")==null?"":(String)fullDetails.get("transportName");
				final String wareHouse=(String)fullDetails.get("wareHouse") ==null?"":(String)fullDetails.get("wareHouse");
				final String coverName=(String)fullDetails.get("coverName")==null?"":(String)fullDetails.get("coverName");
				final String inceptionDate=(String)fullDetails.get("inceptionDate")==null?"":(String)fullDetails.get("inceptionDate");
				final String totalPremium=(String)fullDetails.get("premium")==null?"0":(String)fullDetails.get("premium");
				final String saleTermPremium=(String)fullDetails.get("commodityPremium")==null?"0":(String)fullDetails.get("commodityPremium");
				final String tollCharges=(String)fullDetails.get("tollCharges")==null?"0":(String)fullDetails.get("tollCharges");
				final String totalSumInsured=(String)fullDetails.get("Total_AEDS")==null?"0":(String)fullDetails.get("Total_AEDS");
				final String[][] mailDetails=(String[][])fullDetails.get("premiumDetails");
				final String excessPremium = (String)fullDetails.get("excessPremium");
				String wsrc = (String)fullDetails.get("warRate");//previous WSRC need to check
				final String wareHousefinal = (String)fullDetails.get("wareHouse1");
				String remarks = (String)(fullDetails.get("Remarks")==null?"":fullDetails.get("Remarks"));
				
				mailInfo.put("remarks",remarks);
				/*need to check latter
				if(remarks.equals("0"))
				{
					remarks=session.getAttribute("ReferalForPrint")==null?"":(String)session.getAttribute("ReferalForPrint");
				}*/
				final String warrate = fullDetails.get("warRate") == null ?"0":(String)fullDetails.get("warRate");
				
				final StringBuffer tranFromBuf = new StringBuffer();
				
				
				if("No".equalsIgnoreCase(wareHouse.toString().trim())){
					tranFromBuf.append("AnyPort");
					
				}else{
					tranFromBuf.append(mailBeans.getStartingPlace(applicationNo,cid));
				}
				tranFromBuf.append(" in ");
				final String fromCity = mailer.isNull((String)fullDetails.get("fromcity"),"");
				if((fromCity.trim()).length()>2){
					tranFromBuf.append(fromCity);
					tranFromBuf.append(',');
				}
				tranFromBuf.append("&nbsp;");
				tranFromBuf.append((String)fullDetails.get("fromcountryNames"));
				
				final StringBuffer tranToBuf = new StringBuffer();
				if("No".equalsIgnoreCase(wareHousefinal==null?"":wareHousefinal.toString().trim())){
					tranToBuf.append("AnyPort");
				}else{
					tranToBuf.append(mailBeans.getEndingPlace(applicationNo,cid));
				}
				tranToBuf.append(" in ");
				final String tocity=mailer.isNull((String)fullDetails.get("tocity"),"");
				if((tocity.trim()).length()>2){
					tranToBuf.append(tocity);
					tranToBuf.append(',');
				}
				tranToBuf.append("&nbsp;");
				tranToBuf.append((String)fullDetails.get("tocountryNames"));
				
				final String currencyName=(String)fullDetails.get("currencyName")==null?"":(String)fullDetails.get("currencyName");
				final String exchangeRate=(String)fullDetails.get("exchangeRate")==null?"0":(String)fullDetails.get("exchangeRate");
			final String saletermName = (String)fullDetails.get("saleTermName")==null?"":(String)fullDetails.get("saleTermName");
			final String toleranceName=new PremiumLogic().getToleranceName((String)fullDetails.get("toleranceId")==null?"":(String)fullDetails.get("toleranceId"),brokerBra);
			final String admrem = mailer.adminRefStatus();
			mailInfo.put("admrem", admrem);
			
			messages.append("<html><head></head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>" +
					"<meta name='GENERATOR' content='Microsoft FrontPage 4.0'><meta name='ProgId' content='FrontPage.Editor.Document'>" +
					"<meta name='Microsoft Theme' content='rmnsque 1111'><br><p align = 'center'><b><u><font face = 'Arial' font size = 3>" +
					"<br>QUOTE INFORMATION</u></font></p><table border='0' width='100%' height='50'><tr> <td height='22' width='50%'><font size = 2 face = 'Arial'>To, <br>");
			//<tr><td width='50%'><img src='D:/tomcat 5.5/webapps/Emirates/images/AoicHeader.jpg'/></td><td width='50%'></td></tr>
			if(mailFrom.equalsIgnoreCase(FGROUP)){
				if("F".equalsIgnoreCase((String)freDetails.get(FRESTATUS)) && "F".equalsIgnoreCase((String)freDetails.get(STATUS))){
					messages.append("M/s. "+brokerName+BREAK);
					messages.append(mailer.isNull((String)brokerDet.get(ADDRESS), "")+BREAK);
					messages.append(brokerMail+BREAK);
					messages.append(mailer.isNull((String)brokerDet.get("phone"), "")+BREAK);
				}else{
					messages.append("<br>M/s. "+mailer.isNull((String)userDet.get("first_name"),"")+BREAK);
					messages.append(mailer.isNull((String)userDet.get(ADDRESS), "")+BREAK);
					messages.append(mailer.isNull((String)userDet.get(EMAIL), "")+BREAK);
				}
			}else{
				messages.append(mailer.isNull((String)mailDet.get("title"),"")+"&nbsp;"+mailer.isNull((String)mailDet.get("firstname"),"")+BREAK);
				messages.append(mailer.isNull((String)mailDet.get("addr1"),""));
				messages.append(BREAK+mailer.isNull((String)mailDet.get("city"),""));
				messages.append(BREAK+mailer.isNull((String)mailDet.get("count"),""));
				messages.append(BREAK+mailer.isNull((String)mailDet.get("pobox"),""));
				messages.append(BREAK+mailer.isNull((String)mailDet.get("tele"),"")+BREAK);
			}
			messages.append("</td><td height='22' width='50%' align='left'><b><font size = 2 face = 'Arial'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quotation&nbsp;No&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			messages.append(quoteNo);
			messages.append("<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Quotation&nbsp;Date&nbsp;&nbsp;&nbsp;");
			messages.append(qDate);
			messages.append(" </td></b></tr><tr><td colspan='2' align='center'><table border='1' width='100%' style='border: 1px solid #333333;'><TR align=left>" +
							"<TD width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Cover Start Date</STRONG></TD><TD  width='20%'><font size = 2 face = 'Arial'>" +
							"<STRONG>");
			messages.append(inceptionDate);
			messages.append("</STRONG></TD><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>WAR&SRCC Option</STRONG></TD>");
			if("0".equals(wsrc)){
				  wsrc = "NO";
			}else{
				wsrc = "YES";
			}
			messages.append("<TD  width='20%'><font size =2 face = 'Arial'><STRONG>");
			messages.append(wsrc);
			messages.append("</STRONG></TD></TR><TR align=left><TD  width='30%' style='background-color: #F0F0F0;'> <font size = 2 face = 'Arial'><STRONG>Form of Transport</STRONG></TD>" +
					"<TD  width='20%'><font size= 2 face = 'Arial'><STRONG>");
			messages.append(transportName);
			messages.append("</STRONG></TD><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face ='Arial'><STRONG>Coverage</STRONG></TD><TD  width='30%'><font size = 2 face='Arial'><STRONG>");
			messages.append(coverName);
			messages.append("</STRONG></TD></TR><TR align=left><TD  width='30%' style='background-color: #F0F0F0;'><font size=2 face ='Arial'><STRONG>Sale Term For Valuation</STRONG>" +
					"<TD  width='20%'><font size = 2 face = 'Arial'><STRONG>");
			messages.append((saletermName+(toleranceName==null || "None".equalsIgnoreCase(toleranceName) || toleranceName.trim().length()<=0 ?"":" + "+toleranceName)));
			messages.append("</STRONG><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Currency</STRONG></TD><TD  width='20%'><font size = 2 face = 'Arial'><STRONG>");
			messages.append(currencyName);
			messages.append("</STRONG></TD></TR><TR align=left><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Country Of Origin</STRONG></TD>" +
					"<TD  width='20%'><font size = 2 face = 'Arial'><STRONG>");
			messages.append(tranFromBuf.toString());
			messages.append("</STRONG> </TD><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Country Of Destination</STRONG></TD>" +
					"<TD  width='20%'><font size = 2 face = 'Arial'><STRONG> ");
			messages.append(tranToBuf.toString());
			messages.append(" </STRONG> </TD><TR align=left><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Warehouse Option for Country Of Origin</STRONG></TD><TD  width='20%'><font size = 2 face = 'Arial'><STRONG>");
			messages.append(wareHouse); 
			messages.append("</STRONG> </TD><TD  width='30%' style='background-color: #F0F0F0;'><font size = 2 face = 'Arial'><STRONG>Warehouse Option for Country Of Destination</STRONG></TD>" +
					"<TD  width='20%'><font size = 2 face = 'Arial'><STRONG> ");
			messages.append(wareHousefinal);
			messages.append(" </STRONG> </TD></TR></table></td></tr><tr><td colspan='2' align='center'><table border=1 width='100%' style='border: 1px solid #333333;'><TR style='background-color: #F0F0F0;'><TD  width='10%'><font size = 2 face = 'Arial'><p align='center'><b>SNo</b></TD>" +
					" <TD align='middle'  width='20%'><font size = 2 face = 'Arial'><p align='center'><b>Category Of Goods</b></TD><TD align='middle'  width='20%'><font size = 2 face = 'Arial'>" +
					"<p align='center'><STRONG>Description</STRONG></TD><TD align='middle'  width='23%'><font size = 2 face = 'Arial'><p align='center'>" +
					"<b>Insured Value (");
			messages.append(currencyName);
			messages.append(") </b></TD>");
			if(NORMAL.equalsIgnoreCase(remarks) && "N".equalsIgnoreCase(admrem))
			{
				if((mailFrom.equalsIgnoreCase(FGROUP)&&!"N".equalsIgnoreCase((String)freDetails.get("premiumStatus")) 
						&& "".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")) && !"N".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")))||!TRUE.equalsIgnoreCase(Freight))
				{
					messages.append("<TD align='middle'  width='14%'><font size = 2 face = 'Arial'><p align='center'><STRONG>MARINE RATE (%) </STRONG></TD>");
					messages.append("<TD align='middle'  width='13%'><font size = 2 face = 'Arial'><p align='center'><STRONG>WAR RATE (%) </STRONG></TD>");
				}
			}
			messages.append("<TD align='middle' width='10%'><font size = 2 face = 'Arial'><p align='center'>" +
					"<STRONG>FRAGILE</STRONG></TD></TR></font>");
			BigDecimal bigDec2=null,bigDec4=null;
		for(int k=0;k<(mailDetails.length);k++)
	    {
			final String commName = mailer.isNull(mailDetails[k][20],"");
			final String sumInsured = mailer.isNull(mailDetails[k][12],"0");
			final String description=mailer.isNull(mailDetails[k][13],"");
			fragile = mailer.isNull(mailDetails[k][14],"0");
			final String rate1=mailer.isNull(mailDetails[k][15],"0");
			rate = Double.parseDouble(rate1)+Double.parseDouble(warrate);
			if(fragile.equalsIgnoreCase("on"))
			{
				fragile="YES";
			}else
			{
				fragile="NO";
			}
			sumIns= Double.parseDouble(sumInsured);
			sum=sum+sumIns;
			final BigDecimal bigDec = new BigDecimal(totalSumInsured);
			final BigDecimal bigDec1 = new BigDecimal(saleTermPremium);
			final BigDecimal tollbd = new BigDecimal(tollCharges);
			final BigDecimal tollbd2 = bigDec.add(bigDec1);
			bigDec2 = tollbd2.add(tollbd);
			bigDec2 = bigDec2.setScale(0,BigDecimal.ROUND_HALF_UP);
			final BigDecimal bigDec5 = new BigDecimal(exchangeRate);
			bigDec4 = bigDec2.divide(bigDec5,BigDecimal.ROUND_UP);
			bigDec4 = bigDec4.setScale(0,BigDecimal.ROUND_HALF_UP);
			final double tot_Pre = Double.parseDouble(totalPremium);
			final double ex_Pre =  Double.parseDouble(excessPremium);
			final double fin_Pre = tot_Pre+ex_Pre;
				 finalPremium = fmt1.format(fin_Pre);
		        messages.append("<TR> <TD width='10%'><p align='center'><font size = 2 face = 'Arial'><strong>");
		        messages.append((k+1));
		        messages.append("</strong></TD><TD width='20%'><p align='center'><font face = 'Arial' size = 2>");
		        messages.append(commName);
		        messages.append("</TD><TD width='20%'><p align='center'><font face = 'Arial' size = 2>");
		        messages.append(description);
		        messages.append("</TD><TD width='23%' align='right'><font face = 'Arial' size = 2>");
		        messages.append(fmt1.format(sumIns));
		        messages.append("</TD>");
				if(NORMAL.equalsIgnoreCase(remarks) && "N".equalsIgnoreCase(admrem))
				{
					if((mailFrom.equalsIgnoreCase(FGROUP)&&!"N".equalsIgnoreCase((String)freDetails.get("premiumStatus")) && 
							"".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")) && !"N".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")))
							||!TRUE.equalsIgnoreCase(Freight))
					{
						java.text.NumberFormat fmt3=new java.text.DecimalFormat("0.000000") ;
						messages.append("<TD width='14%' align='right'><font face = 'Arial'size = 2>"+fmt3.format(Double.parseDouble(rate1))+"</TD>");
						messages.append("<TD width='13%' align='right'><font face = 'Arial'size = 2>"+fmt3.format(Double.parseDouble(warrate))+"</TD>");
					}
				 }
	            messages.append("<TD width='10%'><p align='center'><font face = 'Arial' size = 2>");
	            messages.append(fragile);
	            messages.append("</p></font></TD></tr>");
			}  //end for loop
			messages.append("</table></td></tr>");
			if(NORMAL.equalsIgnoreCase(remarks) && "N".equalsIgnoreCase(admrem))
			{
				if((mailFrom.equalsIgnoreCase(FGROUP)&&!"N".equalsIgnoreCase((String)freDetails.get("premiumStatus")) && "".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")) 
						&& !"N".equalsIgnoreCase(mailer.isNull((String)freDetails.get(RATEOPT),"")))||!TRUE.equalsIgnoreCase(Freight))
				{
						    messages.append("<tr><td colspan='2' align='center'><table border=1 cellSpacing=1 cellPadding=1 width='100%' align='center' style='border: 1px solid #333333;'>" +
						    		"<TBODY></font><tr align='center '>" +
						    		"<td width='30%' align='left' style='background-color: #F0F0F0;'><b><font face='Arial' size='2'>Total Insured Value (");
						    messages.append(CurrencyType);
						    messages.append(")</font></b></td><TD align=right width='20%'><b><font size = 2  face = 'Arial'>");
						    messages.append(fmt1.format(Double.parseDouble((String)fullDetails.get("sumInsL"))));
							messages.append("</b></TD><td align='left' width='30%' style='background-color: #F0F0F0;'><b><font face='Arial' size='2'>"+"[");
							messages.append(currencyName);
							messages.append("]  Equivalent   "+"</font></b></td><td align='right' width='20%' height='33'><b><font face='Arial' size='2'>");
							messages.append(fmt1.format(Double.parseDouble((String)fullDetails.get("sumInsF"))));
							messages.append("</font></b></td></tr><tr align='center '><td width='30%' align='left' style='background-color: #F0F0F0;'><font face='Arial' size='2'><b> Premium (");
							messages.append(CurrencyType);
							messages.append(")</b></font></td><td align='right' width='20%'><font face='Arial' size='2'><b>");
							messages.append(finalPremium);
							messages.append("</b></font></td><td style='background-color: #F0F0F0;'></td><td></td></tr></tbody></table></td></tr></table>");
				}
			}
		    if( "Y".equalsIgnoreCase( admrem.trim()))
		    {
		    	remarks = "Admin referral";
				messages.append("<br><center>This Quote comes under "+remarks+"</center>");
			}
			else if( ! NORMAL.equalsIgnoreCase(remarks.trim()))
			{
			  messages.append("<br><center>This Quote comes under "+remarks+"</center>");
			}
			if(fragile.equalsIgnoreCase("YES"))
			{
			   comment = "0.50% of the Sum Insured subject to minimum of 'US$ 250/- Each and Every Claim' for FRAGILE Goods";
			}
			else
			{
			   comment = "";
			}
			messages.append(BREAK);
			messages.append("<br><font size = 2 face = 'Arial'>");
			messages.append(comment);
			messages.append(FONTCLOSE);
			final PremiumLogic logic = new PremiumLogic();
			final String adminRefremarks = logic.getAdminRefRemarks(applicationNo);
			if(adminRefremarks.length()>0 && !adminRefremarks.equalsIgnoreCase(" ")&&mailFrom.equalsIgnoreCase(ADMIN))
			{
				messages.append(BREAK);
				messages.append("<br><font size = 2 face = 'Arial'><b>Admin Remarks</b></font><br><br><font size = 2 face = 'Arial'>");
				messages.append(adminRefremarks);
				messages.append("</font><br>");
			}
			messages.append("<font size = 2 face = 'Arial'>");
			if(mailFrom.equalsIgnoreCase(FGROUP))
			{
				if("F".equalsIgnoreCase((String)freDetails.get(FRESTATUS)) && "F".equalsIgnoreCase((String)freDetails.get(STATUS))){
					messages.append("<br>Regards,<br><br>M/s. ");
					messages.append(mailer.isNull((String)userDet.get("first_name"),"")+BREAK);
					if(!mailer.isNull((String)userDet.get(ADDRESS), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)userDet.get(ADDRESS), "")+BREAK);
					if(!mailer.isNull((String)userDet.get(EMAIL), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)userDet.get(EMAIL), "")+BREAK);
					messages.append("</font></strong>");
				}else{
					messages.append("<br>Regards,<br><br>M/s. ");
					messages.append(brokerName+BREAK);
					if(!mailer.isNull((String)brokerDet.get(ADDRESS), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)brokerDet.get(ADDRESS), "")+BREAK);
					if(brokerMail.equalsIgnoreCase(""))
						messages.append(BREAK+brokerMail+BREAK);
					if(!mailer.isNull((String)brokerDet.get("phone"), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)brokerDet.get("phone"), "")+BREAK);
					messages.append("</font></strong>");
				}
			}else
			{
				if(mailFrom.equalsIgnoreCase(ADMIN))
				{
					messages.append("<br>Regards,<br>Admin<br><br>");
				}
	   			else
				{
					messages.append("<br>Regards,<br><br>M/s. ");
					messages.append(brokerName+BREAK);
					if(!mailer.isNull((String)brokerDet.get(ADDRESS), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)brokerDet.get(ADDRESS), "")+BREAK);
					if(!mailer.isNull((String)brokerDet.get("phone"), "").equalsIgnoreCase(""))
						messages.append(BREAK+mailer.isNull((String)brokerDet.get("phone"), "")+BREAK);
					messages.append("</font></strong>");
				}
			}
			mailInfo.put("content",messages.toString());
			logger.debug(EXIT);	
			
			return mailInfo;
		}
		public String getCluausesDetails(final String brokerBra, final String cid, final String quoteNo,
				final String loginId, final String applicationNo, final String productId,final String freightUser,final String openCoverNo) throws BaseException
		{
			logger.info("getCluausesDetails mailInformation method()");
			logger.debug(ENTER);
			Map displayResults = new HashMap();
			String[][] extraClauses=new String[0][0];
			String[][] exclusions=new String[0][0];
			String[][] warranties=new String[0][0];
			String[][] clauses=new String[0][0];
			String[][] EditExtraClauses=new String[0][0];
			String[][] EditExClauses=new String[0][0];
			String[][] EditWarClauses=new String[0][0];
			String[][] EditClauses=new String[0][0];
			
			final policyInfo pol = new policyInfo();
			pol.setLoginBra(brokerBra);
			pol.setCid(cid);
			pol.setQuoteNo(quoteNo);
			final StringBuffer messages = new StringBuffer(10000);
			/*if(productId.equalsIgnoreCase("3"))
			{
				displayResults=pol.getExclusionWarrantyClauses(quoteNo,loginId,cid,brokerBra);
			}
			else
			{
				displayResults = pol.getExclusionWarrantyClauses(quoteNo,loginId,"editStatus",productId,openCoverNo,brokerBra);
			}*/
			if(applicationNo != null)
			{
				displayResults = new policyInfo().getConditions(applicationNo,brokerBra);	
				final String[][] backDaysCheck = pol.getBackDatedStatus(quoteNo);
				String backDaysPolicy;
				String backDaysBL;
				if(backDaysCheck.length >0)
				{
					backDaysPolicy=backDaysCheck[0][0];
					backDaysBL=backDaysCheck[0][1];
	
				}else
				{
					backDaysPolicy="0";
					backDaysBL="0";
				}
				if(!displayResults.isEmpty())
				{
						if(displayResults.get("exclusionsDesc")!=null){
							exclusions =(String[][])displayResults.get("exclusionsDesc");
						}
						if(displayResults.get("warrantyDesc")!=null){
							warranties=(String[][])displayResults.get("warrantyDesc");
						}
						if(displayResults.get("clausesDesc")!=null){
							clauses =(String[][])displayResults.get("clausesDesc");
						}
						if(displayResults.get("extraClausesDesc")!=null){
							extraClauses=(String[][])displayResults.get("extraClausesDesc");
						}
						if(displayResults.get("editedClauses")!=null){
							EditClauses=(String[][])displayResults.get("editedClauses");
						}
						if(displayResults.get("editedExtraClauses")!=null){
							EditExtraClauses=(String[][])displayResults.get("editedExtraClauses");
						}
						if(displayResults.get("editedWarClauses")!=null){
							EditWarClauses=(String[][])displayResults.get("editedWarClauses");
						}
						if(displayResults.get("editedExClauses")!=null){
							EditExClauses=(String[][])displayResults.get("editedExClauses");
						}
				}
			    if(EditClauses.length>0 || clauses.length>0)
				{
				   messages.append("<b><u>Clauses</u></b><br>");
				}
			    final MailerBean mailer = new MailerBean();
				if(clauses.length>0)
				{
					messages.append("<font size = 2>");
					for(int kc=0;kc<clauses.length;kc++)
					{
						if(!mailer.isNull((clauses[kc][0]==null?"":clauses[kc][0]).trim(),"").equalsIgnoreCase(""))
						{
							messages.append(mailer.isNull(clauses[kc][0].trim(),""));
							messages.append(BREAK);
						}
					}
					messages.append(FONTCLOSE);
				}
				if(EditClauses.length>0)
				{
					messages.append("<font sizes = 2>");
						for(int kc=0;kc<EditClauses.length;kc++)
						{
							if(!mailer.isNull((EditClauses[kc][1]==null?"":EditClauses[kc][1]).trim(),"").equalsIgnoreCase(""))
							{
								messages.append(mailer.isNull(EditClauses[kc][1].trim(),""));
								messages.append(BREAK);
							}
						}
					messages.append(FONTCLOSE);
				}
				if(extraClauses.length>0)
				{
					messages.append("<font size = 2>");
						for(int kec=0;kec<extraClauses.length;kec++)
						{
							if(!mailer.isNull(extraClauses[kec][0],"").equalsIgnoreCase(""))
							{
								messages.append(mailer.isNull(extraClauses[kec][0],""));
								messages.append(BREAK);
							}
						}
					messages.append(FONTCLOSE);
				}
				if(EditExtraClauses.length > 0)
				{
					messages.append("<font size = 2>");
					for(int nc=0;nc<EditExtraClauses.length;nc++)
					{
						if(!mailer.isNull(EditExtraClauses[nc][1],"").equalsIgnoreCase(""))
						{
							messages.append(mailer.isNull(EditExtraClauses[nc][1],""));
							messages.append(BREAK);
						}
					}
					messages.append(FONTCLOSE);
					messages.append(BREAK);
				}
				if(exclusions.length>0)
				{
					messages.append(" <br><b><u>Exclusions</u></b><br><font size = 2>");
			   		for(int ke=0;ke<exclusions.length;ke++)
			   	 	{
			   			if(!mailer.isNull(exclusions[ke][0],"").equalsIgnoreCase(""))
			   			{
			   				messages.append(mailer.isNull(exclusions[ke][0],""));
			   				messages.append(BREAK);
			   			}
			   		}
			   		messages.append(FONTCLOSE);
	   				messages.append(BREAK);
				}
				if(EditExClauses.length > 0)
				{
					messages.append(" <br><b><u>Exclusions</u></b><br><font size = 2>");
					for(int nc=0;nc<EditExClauses.length;nc++)
					{
						if(!mailer.isNull(EditExClauses[nc][1],"").equalsIgnoreCase(""))
						{
							messages.append(mailer.isNull(EditExClauses[nc][1],""));
							messages.append(BREAK);
						}
					}
					messages.append(FONTCLOSE);
					messages.append(BREAK);
				}
				if(warranties.length>0)
				{
					messages.append(BREAK);
					messages.append("<b><u>Warranties</u></b>");
					messages.append(BREAK);
					messages.append("<font size=2>");
					for(int kw=0;kw<warranties.length;kw++)
					{
						if(!mailer.isNull(warranties[kw][0],"").equalsIgnoreCase(""))
						{
							messages.append(mailer.isNull(warranties[kw][0],""));
							messages.append(BREAK);
						}
					}
					messages.append(FONTCLOSE);
				}
				if(EditWarClauses.length > 0)
				{
					messages.append(" <br><b><u>Warranties</u></b><br><font size = 2>");
					for(int nc=0;nc<EditWarClauses.length;nc++)
					{
						if(!mailer.isNull(EditWarClauses[nc][1],"").equalsIgnoreCase(""))
						{
							messages.append(mailer.isNull(EditWarClauses[nc][1],""));
							messages.append(BREAK);
						}
					}
					messages.append(FONTCLOSE);
					messages.append(BREAK);
				}
				backDaysPolicy = mailer.isNull(backDaysPolicy,"0");
				backDaysBL = mailer.isNull(backDaysBL,"0");
				int countBackDays = 0;
				if(productId.equalsIgnoreCase("3"))
				{
						final PremiumInputsBean premiumBean = new PremiumInputsBean();
						if(freightUser.equalsIgnoreCase("freight"))
						{
							final String temp = premiumBean.getBackDatesAllowed(loginId,"3");
							if(temp!=null&&temp.length()>0){
								countBackDays = Integer.parseInt(temp);}
						}else{
							countBackDays=0;
						} 
				}
				else if(productId.equalsIgnoreCase("11"))
				{
					final String temp = pol.getBackDatedStatusOpencover(openCoverNo);
					if(temp!=null&&temp.length()>0){
							countBackDays = Integer.parseInt(temp);}
				}
				if(productId.equalsIgnoreCase("3"))
				{
					messages.append(BREAK+pol.getPolicysFreshBackDesc(brokerBra));
				}
				else if(productId.equalsIgnoreCase("11")&&(Integer.parseInt(backDaysPolicy) > countBackDays || Integer.parseInt(backDaysBL) >countBackDays))
				{
						messages.append(BREAK+pol.getPolicysFreshBackDesc(brokerBra));
				}
			}
			messages.append("</font></html></head></html>");
			logger.debug(EXIT);
			
			return messages.toString();
	 }
		public String[] getCcAddress(final String mailFrom,final String brokerBra,final String ccId,final String scrnFrom, final String brokerLoginId) throws BaseException{
			String cc1Address[] = null;
			logger.info("getCcAddress mailInformation method()");
			logger.debug(ENTER);
			final MailerBean mailer =new MailerBean();
			if((mailFrom.equalsIgnoreCase("None")||mailFrom.equalsIgnoreCase(ADMIN))&&!scrnFrom.equalsIgnoreCase("QuoteRegister"))
			 {
				Map rDetails = new HashMap();
				rDetails = mailer.getReferralDetails(brokerBra);
				final String ccAdd = (String)rDetails.get("emailcc");
				final StringTokenizer st2 = new StringTokenizer(ccAdd,",");
				cc1Address = new String[st2.countTokens()];
				int loopI=0;
				while(st2.hasMoreElements())
				{
				    cc1Address[loopI++] = (String)st2.nextElement();
				}
			 }else if(mailFrom.equalsIgnoreCase(COMPVIEW)){
				 final StringTokenizer stCc = new StringTokenizer(ccId,",");
				 cc1Address = new String[stCc.countTokens()];
				 int loopI=0;	
				 while(stCc.hasMoreElements()){
						cc1Address[loopI++] = (String)stCc.nextElement();
				 }
			 }
			 else if("brokers".equalsIgnoreCase(mailFrom) && scrnFrom.equalsIgnoreCase("QuoteRegister")) //Email quote CC for Broker  
			 {
				 cc1Address = new String[]{new CommonDAO().getUserMail(brokerLoginId)};
			 }
			 else{
				 cc1Address = new String[0];
			 }
			logger.debug(EXIT);
			
		return cc1Address;
	}
	public String[] getEmailList(final String remarks,final String Freight,final String admrem,final String adminid,final String mailId,final String mailFrom,final String mailIds,final String toId){
		logger.info("getEmailList mailInformation method()");
		logger.debug(ENTER);
		logger.info("royal test reposne to mail id from new mail ciontroleler"+remarks);
		logger.info("royal test reposne to mail id from new mail mailFrom"+mailFrom);
		logger.info("royal test reposne to mail id from new mail Freight"+Freight);
		logger.info("royal test reposne to mail id from new mail mailId.."+mailId);
		logger.info("royal test reposne to mail id from new mail mailIds.."+mailIds);
		final Map toMail = new HashMap();
		if(mailIds.length()>0){
			toMail.put(EMAIL, mailIds);
		}
		else if(mailFrom.equalsIgnoreCase(COMPVIEW)){
			toMail.put(EMAIL, toId);
		}
		else{
			if(NORMAL.equalsIgnoreCase(remarks.trim())||TRUE.equalsIgnoreCase(Freight)||ADMIN.equalsIgnoreCase(mailFrom))
			{
				toMail.put(EMAIL, mailId);
			}
			else if("Y".equalsIgnoreCase( admrem.trim()) || ! NORMAL.equalsIgnoreCase(remarks.trim()))   // and
			{
				if(TRUE.equalsIgnoreCase(Freight)){
					toMail.put(EMAIL, mailId);
				}else if(!TRUE.equalsIgnoreCase(Freight)){
					toMail.put(EMAIL, adminid);
				}
			}else if(!NORMAL.equalsIgnoreCase(remarks.trim()))
			{
				if(TRUE.equalsIgnoreCase(Freight)){
					toMail.put(EMAIL, mailId);
				}else if(!TRUE.equalsIgnoreCase(Freight)){
					toMail.put(EMAIL, adminid);
				} 
			}
			if(" ".equals(remarks)||remarks.length()<=0){
				toMail.put(EMAIL, mailId);
			}
		}
		final String[] emailList = {(String)toMail.get(EMAIL)};
		logger.info("emailList.size==>"+emailList.length);
		logger.debug(EXIT);
		
		return emailList;
	}
	public String getEmailSubjectTxt(final String remarks,final String mailFrom,final String compMess,
			final String pid,final String quoteNo,final String clientName){
		logger.info("getEmailSubjectTxt mailInformation method()");
		logger.debug(ENTER);
		final StringBuffer emailSubTxt = new StringBuffer(); 
		if(NORMAL.equalsIgnoreCase(remarks.trim())&&!mailFrom.equalsIgnoreCase(COMPVIEW)){
			if(pid.equalsIgnoreCase("3")){
				emailSubTxt.append("Quotation for Marine One off Policy");
			}else{
				emailSubTxt.append("Quotation for Marine Open Cover");
			}
		}else if(mailFrom.equalsIgnoreCase(ADMIN)){
			emailSubTxt.append("Quote No #");
			emailSubTxt.append(quoteNo);
			emailSubTxt.append(" Client Name #");
			emailSubTxt.append(clientName);
			emailSubTxt.append(" # ");
			emailSubTxt.append(remarks);
			emailSubTxt.append(" - Response");
		}else if(mailFrom.equalsIgnoreCase(COMPVIEW)){
			emailSubTxt.append(compMess);
		}
		else{
			emailSubTxt.append("Quote No #");
			emailSubTxt.append(quoteNo);
			emailSubTxt.append(" Client Name #");
			emailSubTxt.append(clientName);
			emailSubTxt.append(" - ");
			emailSubTxt.append(remarks);
		}
		logger.debug(EXIT);
		
		return emailSubTxt.toString();
	}
	
	

	public String getEmailMsgTxt(final String quoteNo,final String mailFrom,final String refStatus,final String mess){
		logger.info("getEmailMsgTxt mailInformation method()");
		logger.debug(ENTER);
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		if(mailFrom.equalsIgnoreCase(ADMIN)){
			if(refStatus.equalsIgnoreCase("accept")){
				emailMsgTxt.append("<br> <font size = 3 face = 'arial'>The Quote Number : &nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp; was " );
				emailMsgTxt.append(refStatus);
				emailMsgTxt.append("ed and Issue policy to the customer</font></a>");
			}else if(refStatus.equalsIgnoreCase("reject") || refStatus.equalsIgnoreCase("updated") ){
				emailMsgTxt.append("<br> <font size = 3 face = 'arial'>The Quote Number : &nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp; was rejected.</a>");
			}else if(refStatus.equalsIgnoreCase("none")){
				emailMsgTxt.append("<br> <font size = 3 face = 'arial'>The Quote Number : &nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp; was paused.</a>");
			}
		}else if(mailFrom.equalsIgnoreCase(COMPVIEW)){
			emailMsgTxt.append("<b> Dear Sir/Madam <br><br></b>");
			emailMsgTxt.append(mess);
			emailMsgTxt.append("<br><br>");
		}
		else{
			emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>Please find enclose Quote no:&nbsp;<b>");
			emailMsgTxt.append(quoteNo);
			emailMsgTxt.append("</b>&nbsp;&nbsp;with this mail for your kind persual. Looking forward for your reply</font>");
		}
		logger.debug(EXIT);
		
		return emailMsgTxt.toString();
	}
	
	public String getQuoteMailMgtTxt(final String quoteNo) {
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>Please find enclose Quote no:&nbsp;<b>");
		emailMsgTxt.append(quoteNo);
		emailMsgTxt.append("</b>&nbsp;&nbsp;with this mail for your kind persual. Looking forward for your reply</font>");
		return emailMsgTxt.toString();
	}
	
	public Map getFreAddDetails(final String loginId,final String brokerCode,final Map freDetails,final Map mailInfo) throws BaseException{
		logger.info("getFreAddDetails mailInformation method()");
		logger.debug(ENTER);
		final MailerBean mailer = new MailerBean(); 
		final Map freAddDetails = new HashMap();
		String[] emailList1=null;
		String[] ccAddress = new String[1];
		if("F".equalsIgnoreCase((String)freDetails.get(FRESTATUS)) && "F".equalsIgnoreCase((String)freDetails.get(STATUS)))
		{
			final String freRedirection = mailer.getFreightRedirection(loginId);
			if(freRedirection.equalsIgnoreCase("Y"))
			{
				String adminMail[][] = new String[0][0];
				adminMail = mailer.getFreightAdminMail(brokerCode);
				emailList1 = new String[adminMail.length+1];
				int loopA=0;
				for(loopA=0;loopA<adminMail.length;loopA++)
				{
					emailList1[loopA] = adminMail[loopA][0];
				}
				emailList1[loopA] = (String)mailInfo.get(FBROKERMAIL);
			}
			else
			{
				emailList1 = new String[1];
				emailList1[0] = (String)mailInfo.get(FBROKERMAIL);
			}
			ccAddress[0] = (String)mailInfo.get("freUserMail");
			freAddDetails.put("fromName", (String)mailInfo.get("freUserName"));
		}
		else
		{
			emailList1 = new String[1];
			ccAddress[0] = (String)mailInfo.get(FBROKERMAIL);
		    emailList1[0] = (String)mailInfo.get("freUserMail");
		    freAddDetails.put("fromName", (String)mailInfo.get("brokerName"));
		}
		freAddDetails.put("emailList", emailList1);
		freAddDetails.put("ccAddress", ccAddress);
		logger.debug(EXIT);
		
		return freAddDetails;
	}
	public String getFreEmailMsgTxt(final String quoteNo,final Map freDetails)throws BaseException	{
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		if("F".equalsIgnoreCase((String)freDetails.get(STATUS)))
		{
			emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>Please kindly accept the Quote no:&nbsp;<b>");
			emailMsgTxt.append(quoteNo);
			emailMsgTxt.append("</b>&nbsp;&nbsp;with this mail for your kind persual.  Looking forward for your reply</font>");
		}
		else if("F".equalsIgnoreCase((String)freDetails.get(FRESTATUS)))
		{
			if("A".equalsIgnoreCase((String)freDetails.get("quoteStatus")) && "Y".equalsIgnoreCase((String)freDetails.get("policyStatus")))
			{
				emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>The Quote no:&nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp;was accepted and Issue policy to the customer</font>");
			}
			else if("R".equalsIgnoreCase((String)freDetails.get("quoteStatus")) && !"Y".equalsIgnoreCase((String)freDetails.get("policyStatus")))
			{
				emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>The Quote no:&nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp;was Rejected and Please contact the broker</font>");
			}
			else if("A".equalsIgnoreCase((String)freDetails.get("quoteStatus")) && !"Y".equalsIgnoreCase((String)freDetails.get("policyStatus")))
			{
				emailMsgTxt.append("<b>Dear sir,</b><br> <font size = 3 face = 'arial'>The Quote no:&nbsp;<b>");
				emailMsgTxt.append(quoteNo);
				emailMsgTxt.append("</b>&nbsp;&nbsp;was Accepted., but u don't have the rights to generate policy. So, Please contact the broker</font>");
			}
		}
		return emailMsgTxt.toString();
	}
	public String getOpenCoverEmailMsgTxt(List<LinkedHashMap<String,Object>> openCovers, String branchName){
		logger.info("getOpenCoverEmailMsgTxt - Enter");
		logger.debug(ENTER);
		Map<String, Object> map=null;
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		emailMsgTxt.append("<html><head></head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>" +
				"<meta name='GENERATOR' content='Microsoft FrontPage 4.0'><meta name='ProgId' content='FrontPage.Editor.Document'>" +
				"<meta name='Microsoft Theme' content='rmnsque 1111'>");
		emailMsgTxt.append(" <font size = 3 face = 'arial'><b>Dear sir,</b><br><br>Please find the ").append(branchName).append("&nbsp;<b><br><br>");
		emailMsgTxt.append("<table width='100%' border='0' cellspacing='1' cellpadding='0' > <tbody> 	 <tr> <td align='left' colspan='9'><span Style='FONT-WEIGHT: bold; FONT-SIZE: 13px; height: 20px; COLOR: #e86f00; FONT-FAMILY: Arial; border-bottom: thick 2px solid #000000'>List of OpenCovers</span></td> </tr> <tr Style='FONT-SIZE: 11px;COLOR: white;FONT-FAMILY: Arial;TEXT-DECORATION: none;BACKGROUND-COLOR: #4f6180;text-align: center;font-weight: BOLD;'> <td >S.No</td><td>Core Application Policy No</td> <td>Proposal No</td><td>Broker Name</td> <td>Customer Name</td><td>Policy Start Date</td><td>Policy End Date</td></tr>");
		for (int i = 0; i < openCovers.size(); i++) {
			map=openCovers.get(i);
			emailMsgTxt.append("<tr Style='FONT-SIZE: 12px; COLOR: #666666; font-weight: BOLD; padding-left: 5px; FONT-FAMILY: Arial; text-decoration: none; background-position: 50px; background-color: #efefef; text-align: center'>");
			emailMsgTxt.append("<td width='5%'>").append(map.get("SNO")).append("</td>");
			emailMsgTxt.append("<td width='15%'>").append(map.get("OPEN_COVER_NO")).append("</td>");
			emailMsgTxt.append("<td width='10%'> ").append(map.get("PROPOSAL_NO")).append("</td>");
			emailMsgTxt.append("<td width='25%'>").append(map.get("BROKER")).append("</td>");
			emailMsgTxt.append("<td width='25%'>").append(map.get("CUSTOMER")).append("</td>");
			emailMsgTxt.append("<td width='10%'>").append(map.get("INCEPTION_DATE")).append("</td>");
			emailMsgTxt.append("<td width='10%'>").append(map.get("EXPIRY_DATE")).append("</td></tr>");
		}
		emailMsgTxt.append("<tr bgcolor='#4f6180'><td height='12' align='right' colspan='10'></td></tr></table>");
		emailMsgTxt.append("<br>Regards,<br>Admin<br><br></font></html>");
		logger.info("getOpenCoverEmailMsgTxt - Exit");
		
		return emailMsgTxt.toString();
	}
	public String getOpenCoverReferralMsgTxt(List<LinkedHashMap<String,Object>> openCovers, String branchName){
		logger.info("getOpenCoverEmailMsgTxt - Enter");
		logger.debug(ENTER);
		Map<String, Object> map=null;
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		emailMsgTxt.append("<html><head></head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'>" +
				"<meta name='GENERATOR' content='Microsoft FrontPage 4.0'><meta name='ProgId' content='FrontPage.Editor.Document'>" +
				"<meta name='Microsoft Theme' content='rmnsque 1111'>");
		emailMsgTxt.append(" <font size = 3 face = 'arial'><b>Dear sir,</b><br><br>Please find the ").append(branchName).append("&nbsp;<b><br><br>");
		emailMsgTxt.append("<table width='100%' border='0' cellspacing='1' cellpadding='0' > <tbody> 	 <tr> <td align='left' colspan='9'><span Style='FONT-WEIGHT: bold; FONT-SIZE: 13px; height: 20px; COLOR: #e86f00; FONT-FAMILY: Arial; border-bottom: thick 2px solid #000000'>List of OpenCovers</span></td> </tr> <tr Style='FONT-SIZE: 11px;COLOR: white;FONT-FAMILY: Arial;TEXT-DECORATION: none;BACKGROUND-COLOR: #4f6180;text-align: center;font-weight: BOLD;'> <td >S.No</td> <td>Proposal No</td><td>Broker Name</td> <td>Customer Name</td><td>Policy Start Date</td><td>Policy End Date</td></tr>");
		for (int i = 0; i < openCovers.size(); i++) {
			map=openCovers.get(i);
			emailMsgTxt.append("<tr Style='FONT-SIZE: 12px; COLOR: #666666; font-weight: BOLD; padding-left: 5px; FONT-FAMILY: Arial; text-decoration: none; background-position: 50px; background-color: #efefef; text-align: center'>");
			emailMsgTxt.append("<td width='5%'>").append(map.get("SNO")).append("</td>");
			emailMsgTxt.append("<td width='10%'> ").append(map.get("PROPOSAL_NO")).append("</td>");
			emailMsgTxt.append("<td width='30%'>").append(map.get("BROKER")).append("</td>");
			emailMsgTxt.append("<td width='35%'>").append(map.get("CUSTOMER")).append("</td>");
			emailMsgTxt.append("<td width='10%'>").append(map.get("INCEPTION_DATE")).append("</td>");
			emailMsgTxt.append("<td width='10%'>").append(map.get("EXPIRY_DATE")).append("</td></tr>");
		}
		emailMsgTxt.append("<tr bgcolor='#4f6180'><td height='12' align='right' colspan='10'></td></tr></table>");
		emailMsgTxt.append("<br>Regards,<br>").append(map.get("CUSTOMER")).append("<br><br></font></html>");
		logger.info("getOpenCoverEmailMsgTxt - Exit");
		
		return emailMsgTxt.toString();
	}
	public Map<String, String[]> quoteMailList(String applicationNo, String branchCode, String mailType, String toMailAddress) {
		PolicyGenerationDAO policyDAO = new PolicyGenerationDAO();
		Map<String, String[]> quoteMailList = new HashMap<String, String[]>();
		try {
			String commonCCAdress = policyDAO.getValue("GET_COMMON_CCADDRESS", new String[]{"16"});
			commonCCAdress = StringUtils.isBlank(commonCCAdress)?"":commonCCAdress;
			String[] commonCCAdressArray =  commonCCAdress.split(",");
			
			Map<String,Object> mailDetails = policyDAO.quoteMailList(applicationNo,branchCode,mailType);
			
			if("referralResponse".equalsIgnoreCase(mailType)) {
				String[] toAddress = mailDetails.get("TO_ADDRESS")==null?new String[]{}: mailDetails.get("TO_ADDRESS").toString().split(",");
				String ccAddress = mailDetails.get("CC_ADDRESS")==null?"": mailDetails.get("CC_ADDRESS").toString();
				String[] ccAddressArray = ccAddress.split(",");
				
				if(StringUtils.isBlank(ccAddress)) {
					quoteMailList.put("TO_ADDRESS",toAddress);
					quoteMailList.put("CC_ADDRESS",commonCCAdressArray);
				}
				else {
					quoteMailList.put("TO_ADDRESS",ccAddressArray);
					quoteMailList.put("CC_ADDRESS",commonCCAdressArray);
				}
				
			}
			else if("referralRequest".equalsIgnoreCase(mailType)) {
				String ccAddress = mailDetails.get("CC_ADDRESS")==null?"": mailDetails.get("CC_ADDRESS").toString();
				List<String> ccAddressList = new ArrayList<String>();
				for(String tempString : ccAddress.split(",")) {
					if(StringUtils.isNotBlank(tempString)) {
						ccAddressList.add(tempString);
					}
				}
				for(String tempString : commonCCAdressArray) {
					if(StringUtils.isNotBlank(tempString)) {
						ccAddressList.add(tempString);
					}
				}
				
				quoteMailList.put("TO_ADDRESS", mailDetails.get("TO_ADDRESS")==null?new String[]{}: mailDetails.get("TO_ADDRESS").toString().split(","));
				quoteMailList.put("CC_ADDRESS", ccAddressList.toArray(new String[0]));
			}
			else if("quoteMail".equalsIgnoreCase(mailType)) {
				List<String> ccAddressList = new ArrayList<String>();
				for(String tempString : commonCCAdressArray) {
					if(StringUtils.isNotBlank(tempString)) {
						ccAddressList.add(tempString);
					}
				}
				
				quoteMailList.put("TO_ADDRESS", toMailAddress.split(","));
				quoteMailList.put("CC_ADDRESS", ccAddressList.toArray(new String[0]));
			}
			else if("branchReport".equalsIgnoreCase(mailType)) {
				String ccAddress = mailDetails.get("CC_ADDRESS")==null?"": mailDetails.get("CC_ADDRESS").toString();
				List<String> ccAddressList = new ArrayList<String>();
				for(String tempString : ccAddress.split(",")) {
					if(StringUtils.isNotBlank(tempString)) {
						ccAddressList.add(tempString);
					}
				}
				for(String tempString : commonCCAdressArray) {
					if(StringUtils.isNotBlank(tempString)) {
						ccAddressList.add(tempString);
					}
				}
				
				quoteMailList.put("TO_ADDRESS", mailDetails.get("TO_ADDRESS")==null?new String[]{}: mailDetails.get("TO_ADDRESS").toString().split(","));
				quoteMailList.put("CC_ADDRESS", ccAddressList.toArray(new String[0]));
			}else if("integration".equalsIgnoreCase(mailType)){
				String[] toAddress = mailDetails.get("TO_ADDRESS")==null?new String[]{}: mailDetails.get("TO_ADDRESS").toString().split(",");
				String ccAddress = mailDetails.get("CC_ADDRESS")==null?"": mailDetails.get("CC_ADDRESS").toString();
				String[] ccAddressArray=null;
				
				if(StringUtils.isNotBlank(ccAddress)){
					ccAddressArray = ccAddress.split(",");
					
				}else{
					ccAddressArray=new String[0];
				}
				
				quoteMailList.put("CC_ADDRESS",ccAddressArray);
				quoteMailList.put("TO_ADDRESS",toAddress);	
					
				
			}
			
		}
		catch(Exception ex) {
			logger.debug(ex);
		}
		return quoteMailList;
	}
	public Map<String,Object> getReferralMailDatas(String applicationNo, String branchCode) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		PolicyGenerationDAO policyDAO = new PolicyGenerationDAO();
		try {
			String directStatus = new QuotationDAO().getDirectStatus(applicationNo,branchCode);
			if("Y".equalsIgnoreCase(directStatus)) {
				resultMap = policyDAO.getResultMap("GET_REFERRAL_USER_MAIL_DATAS",new String[]{applicationNo});
			}
			else {
				resultMap = policyDAO.getResultMap("GET_REFERRAL_MAIL_DATAS",new String[]{applicationNo});
			}
		}
		catch(Exception ex) {
			logger.debug(ex);
		}
		return resultMap;
	}
	
	public Map<String,Object> getReferralIntgMailDatas(String policyNo) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		PolicyGenerationDAO policyDAO = new PolicyGenerationDAO();
		try {
				resultMap = policyDAO.getResultMap("INTEGRATION_MAIL_STATUS",new String[]{policyNo});
			
			
		}
		catch(Exception ex) {
			logger.debug(ex);
		}
		return resultMap;
	}
	public String getReferralEmailMsgTxt(Map<String,Object> mailInfo) {
		final StringBuffer messageContent = new StringBuffer(10000);
		messageContent.append("<HTML>");
		messageContent.append("<HEAD>");
		messageContent.append("</HEAD>");
		messageContent.append("<BODY>");
		messageContent.append("<div style='width: 450px;'>");
		messageContent.append("<p>");
		messageContent.append("Dear All,");
		messageContent.append("<br><br>");
		messageContent.append("The following quote has been referred to Emirates Insurance for approval:-");
		messageContent.append("</p>");
		messageContent.append("<table width='450px'>");
		messageContent.append("<tr>");
		messageContent.append("<td width='47.5%'>Quote No</td>");
		messageContent.append("<td width='5%'>:</td>");
		messageContent.append("<td width='47.5%'>"+mailInfo.get("QUOTE_NO")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Customer/Broker Name</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("BROKER_COMPANY_NAME")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Assured Name</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("CUSTOMER_NAME")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Referral Remarks</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("REFERRAL_REMARKS").toString().replaceAll("~", ", ")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("</table>");
		messageContent.append("</div>");
		messageContent.append("</BODY>");
		messageContent.append("</HTML>");
		return messageContent.toString();
	}
	public String getReferralResEmailMsgTxt(Map<String,Object> mailInfo, String refStatus) {
		final StringBuffer messageContent = new StringBuffer(10000);
		messageContent.append("<!DOCTYPE HTML>");
		messageContent.append("<HTML>");
		messageContent.append("<HEAD>");
		messageContent.append("</HEAD>");
		messageContent.append("<BODY>");
		messageContent.append("<div style='width: 450px;'>");
		messageContent.append("<p>");
		messageContent.append("Dear Sir,");
		messageContent.append("<br><br>");
		messageContent.append("The following quote has been "+ refStatus +" by Emirates Insurance:-");
		messageContent.append("</p>");
		messageContent.append("<table width='450px'>");
		messageContent.append("<tr>");
		messageContent.append("<td width='47.5%'>Quote No</td>");
		messageContent.append("<td width='5%'>:</td>");
		messageContent.append("<td width='47.5%'>"+mailInfo.get("QUOTE_NO")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Assured Name</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("CUSTOMER_NAME")+"</td>");
		messageContent.append("</tr>");
		messageContent.append("</table>");
		messageContent.append("<br>");
		messageContent.append("Thanks & Best Regards,<br>");
		messageContent.append("Emirates Insurance");
		messageContent.append("</div>");
		messageContent.append("</BODY>");
		messageContent.append("</HTML>");
		return messageContent.toString();
	}
	public String branchReportMailContent(String branchName) throws Exception{
		final StringBuffer emailMsgTxt = new StringBuffer(1000);
		
		emailMsgTxt.append("<!DOCTYPE HTML>");
		emailMsgTxt.append("<HTML>");
		emailMsgTxt.append("<HEAD>");
		emailMsgTxt.append("</HEAD>");
		emailMsgTxt.append("<BODY>");
		emailMsgTxt.append("<div style='width: 450px;'>");
		emailMsgTxt.append("<p>");
		emailMsgTxt.append("Dear All,");
		emailMsgTxt.append("<br><br>");
		emailMsgTxt.append("Please find the attachment for Policy Generated at "+ branchName);
		emailMsgTxt.append("</p>");
		emailMsgTxt.append("</div>");
		emailMsgTxt.append("</BODY>");
		emailMsgTxt.append("</HTML>");
		
		return emailMsgTxt.toString();
	}
	public String getEmailSubjectIntgTxt(final String policyNo
			,final String clientName,final String intgStatus){
		logger.info("getEmailSubjectTxt mailInformation method()");
		logger.debug(ENTER);
		final StringBuffer emailSubTxt = new StringBuffer(); 
		    emailSubTxt.append("eCargo Alert: Integration Failed ");
			emailSubTxt.append("Policy No #");
			emailSubTxt.append(policyNo);
			emailSubTxt.append(" Client Name #");
			emailSubTxt.append(clientName);
			
		
		logger.debug(EXIT);
		
		return emailSubTxt.toString();
	}
	public String getIntgEmailMsgTxt(Map<String,Object> mailInfo) {
		final StringBuffer messageContent = new StringBuffer(10000);
		messageContent.append("<HTML>");
		messageContent.append("<HEAD>");
		messageContent.append("</HEAD>");
		messageContent.append("<BODY>");
		messageContent.append("<div style='width: 450px;'>");
		messageContent.append("<p>");
		messageContent.append("Dear All,");
		messageContent.append("<br><br>");
		messageContent.append("Below Policy was failed to integration");
		messageContent.append("<br><br>");
		messageContent.append("</p>");
		messageContent.append("<table width='450px'>");
		messageContent.append("<tr>");
		messageContent.append("<td width='47.5%'>Policy No:-</td>");
		messageContent.append("<td width='5%'>:</td>");
		messageContent.append("<td width='47.5%'>"+mailInfo.get("POLICY_NO")+"&nbsp;</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Customer  Name:-</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("CUSTOMER_NAME")+"</td>");
		messageContent.append("<br><br>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		messageContent.append("<td>Integration Status:-</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("INTEGRATION_STATUS")+"&nbsp;&nbsp;</td>");
		messageContent.append("</tr>");
		messageContent.append("<tr>");
		
		messageContent.append("<td>Error  Message:-</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td>"+mailInfo.get("INTEGRATION_ERROR")+"&nbsp; &nbsp; </td>");
		
		messageContent.append("</tr>");
		messageContent.append("<td> Responsibility:-</td>");
		messageContent.append("<td>:</td>");
		messageContent.append("<td><b>"+mailInfo.get("INTEGRATION_RES")+"</b>&nbsp;</td>");
		messageContent.append("</tr>");
		messageContent.append("</table>");
		messageContent.append("</div>");
		messageContent.append("</BODY>");
		messageContent.append("</HTML>");
		return messageContent.toString();
	}
}
