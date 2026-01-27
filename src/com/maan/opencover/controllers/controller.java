package com.maan.opencover.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import jakarta.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.SummaryInformation;
import rsa.opencoverpdf.ScheduleBean;
import com.maan.Mail.DAO.MailInformation;
import com.maan.Mail.controller.mailController;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.opencover.ConditionsOpenCover;
import com.maan.opencover.bean.OpenCoverDocGenerator;
import com.maan.opencover.bean.openCoverQuotation;
import com.maan.opencover.bean.opencoverEntry;
import com.maan.opencover.bean.opencoverSummary;
import com.maan.opencover.bean.rateModification;
import com.maan.services.util.runner;

public class controller extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(controller.class);
	private static final long serialVersionUID = 1L;
	HttpSession session = null;
	PrintWriter out = null;
	boolean format = false;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		com.maan.opencover.bean.newCoverBean cover = new com.maan.opencover.bean.newCoverBean();
		session = request.getSession(true);
		RequestDispatcher rd = null;
		response.setContentType("text/html");
		out = response.getWriter();
		String path = request.getRequestURI();
		path = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
		out.println("path--->" + path);
		String error = "";
		String error1 = "";
		String ErrorIdentify = "";
		if (session.getAttribute("ses") == null) {
			response.sendRedirect("../login/error_messg.jsp");
			return;
		}
		if ((String) session.getAttribute("product_id") == null
				|| !"11".equalsIgnoreCase((String) session
						.getAttribute("product_id"))) {
			response.sendRedirect("../login/error_messg.jsp");
			return;
		}
		try {
			String usrModeController = (String) session
					.getAttribute("userLoginMode") == null ? ""
					: (String) session.getAttribute("userLoginMode");
			if ("".equalsIgnoreCase(usrModeController)
					|| " ".equalsIgnoreCase(usrModeController)) {
				response.sendRedirect("../login/error_messg.jsp");
				return;
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;
		} catch (Exception e) {
		}
		if("newCustomer".equalsIgnoreCase(path))
		{
				com.maan.opencover.bean.customerInfo cc=new com.maan.opencover.bean.customerInfo();
				cc.setTitle(request.getParameter("title")==null?"":request.getParameter("title"));
				cc.setGender(request.getParameter("gender")==null?"":request.getParameter("gender"));
				cc.setFirstName(request.getParameter("firstName")==null?"":request.getParameter("firstName"));
				cc.setLastName(request.getParameter("lastName")==null?"":request.getParameter("lastName"));
				cc.setNationality(request.getParameter("nationality")==null?"":request.getParameter("nationality"));
				cc.setDobDay(request.getParameter("dobDay")==null?"":request.getParameter("dobDay"));
				cc.setDobMonth(request.getParameter("dobMonth")==null?"":request.getParameter("dobMonth"));
				cc.setDobYear(request.getParameter("dobYear")==null?"":request.getParameter("dobYear"));
				cc.setCity(request.getParameter("city")==null?"":request.getParameter("city"));
				cc.setCityStatus(request.getParameter("cityStatus")==null?"":request.getParameter("cityStatus"));
				cc.setTelephone(request.getParameter("telephone")==null?"":request.getParameter("telephone"));
				cc.setMobile(request.getParameter("mobile")==null?"":request.getParameter("mobile"));
				cc.setFax(request.getParameter("fax")==null?"":request.getParameter("fax"));
				cc.setEmail(request.getParameter("email")==null?"":request.getParameter("email"));
				cc.setAddress1(request.getParameter("address1")==null?"":request.getParameter("address1"));
				cc.setAddress2(request.getParameter("address2")==null?"":request.getParameter("address2"));
				cc.setOccupation(request.getParameter("occupation")==null?"":request.getParameter("occupation"));
				cc.setEmirate(request.getParameter("emirate")==null?"":request.getParameter("emirate"));
				cc.setCountry(request.getParameter("country")==null?"":request.getParameter("country"));
				cc.setPoBox(request.getParameter("poBox")==null?"":request.getParameter("poBox"));
				cc.setOrgName(request.getParameter("orgName")==null?"":request.getParameter("orgName"));
				cc.setCustomerCode(request.getParameter("customerCode")==null?"":request.getParameter("customerCode"));
				cc.setMode(request.getParameter("mode")==null?"":request.getParameter("mode"));
				cc.setBrokerId(request.getParameter("brokerId")==null?"":request.getParameter("brokerId"));
				cc.setCLoginId(request.getParameter("loginid")==null?"":request.getParameter("loginid"));
				cc.setCPassword(request.getParameter("password")==null?"":request.getParameter("password"));
				cc.setArNo(request.getParameter("arNo")==null?"":request.getParameter("arNo"));
				cc.setCustomerName(request.getParameter("customerName")==null?"":request.getParameter("customerName"));
				cc.setCustVatRegNo(request.getParameter("custVatRegNo")==null?"":request.getParameter("custVatRegNo"));
				cc.setCustVatRegYn(request.getParameter("custVatRegYn")==null?"":request.getParameter("custVatRegYn"));
				String branch = (String) session.getAttribute("AdminBranchCode");
				branch = branch.replaceAll("'","");
				cc.setBranch(branch);
				error = cc.validateFields(session.getAttribute("customer_id")==null?"":(String)session.getAttribute("customer_id"));

					if(error.length()>0)
					{
						request.setAttribute("errorDetail",error);
						rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
					}
					//else
				//	{
						//String referal=null;

						/*if(cc.checkingMissippiCode((String)session.getAttribute("customer_id")))
							error="<br>*Missippi Customer Code Already Excist";
							if(error.length()>0)
					{
						request.setAttribute("errorDetail",error);
						rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
					}*/
					else{
						String adminBranch = (String) session.getAttribute("AdminBranchCode");
						String cusVal=cc.storedValues(session.getAttribute("customer_id")==null?"":(String)session.getAttribute("customer_id"),request.getParameter("brokerId"),
								adminBranch);
						
						if("DIDN'T INSERTED".equalsIgnoreCase(cusVal) || "DIDN'T UPDATE".equalsIgnoreCase(cusVal))
						{
							error=" Invalid Data";
							request.setAttribute("errorDetail",error);
							rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
						}
						else
						{
						session.setAttribute("customer_id",cusVal==null?"":cusVal);
						
						if(session.getAttribute("customer_id")==null)
						return;
						else
							rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
						System.out.println("************* END in newCustomer.do   ************");
						}
					//}
				}

		}
		else if("newOpenCover".equalsIgnoreCase(path))
		{
				error="0";
				out.println("busines type"+request.getParameter("businessType"));
				if(request.getParameter("brokerId")==null || ("Select".equalsIgnoreCase(request.getParameter("brokerId"))))
					error=error+","+"134";			
				if(request.getParameter("executiveId")==null || "".equals(request.getParameter("executiveId"))){
					error=error+","+"479";
				}
				if(request.getParameter("debitType")==null || "".equals(request.getParameter("debitType"))) {
					error=error+","+"480";
				}
				if(request.getParameter("type")==null)
					error=error+","+"135";		
				
				if(request.getParameter("customerName")==null || request.getParameter("customerName").length()<2)
					error=error+","+"136";			
			
				
				com.maan.premium.DAO.PremiumInputsBean premiumInput = new com.maan.premium.DAO.PremiumInputsBean();
				HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
				String cid="";
				
				String currencyType = "";
				if(brokerDetails.size()>0)
				{
					cid = (String)brokerDetails.get("Orgination");
				}
				String currencyDetails1[][] = new String[0][0];
				currencyDetails1 = premiumInput.getCurrencyDetails(cid);
				request.setAttribute("currencyDetails",currencyDetails1);
				
			if("Quotation.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
			{
				double estimateAmount=0.0,utilzedAmount=0.0;
				
				if(request.getParameter("businessType")==null || ("Select".equalsIgnoreCase(request.getParameter("businessType"))))
					error=error+","+"133";			//error=error+"<br>*Please Select Broker";
				try
				{
					if(request.getParameter("backDays")==null || Double.parseDouble(request.getParameter("backDays"))<0 || Double.parseDouble(request.getParameter("backDays"))>=366)
					error=error+","+"471";	
				}
				catch(Exception e)
				{
					error=error+","+"471";
				}
				try
				{
					if(Double.parseDouble(request.getParameter("estimateAmount"))<=0)
						error=error+","+"138";
					else {
						estimateAmount = Double.parseDouble(request.getParameter("estimateAmount"));
					}
				}
				catch(NumberFormatException e)
				{
					error=error+","+"138";	
				}
				try
				{
					//if(Double.parseDouble(request.getParameter("rsaValue"))<=0 || Double.parseDouble(request.getParameter("rsaValue"))>100)
						//error=error+","+"139";	
				
				}
				catch(NumberFormatException e)
				{
					error=error+","+"139";
				}
				
				try{
					String utAmount=request.getParameter("utilizedAmount")==null?"":request.getParameter("utilizedAmount");
					if(!"".equalsIgnoreCase(utAmount) &&  Double.parseDouble(request.getParameter("utilizedAmount").replaceAll(",", ""))<0){
						error=error+","+"481";
					}else if(!"".equalsIgnoreCase(utAmount)){
						utilzedAmount=Double.parseDouble(request.getParameter("utilizedAmount").replaceAll(",", ""));
					}
				}catch (Exception e) {
					error=error+","+"481";
				}
				try{
					if(utilzedAmount>estimateAmount){
						error=error+","+"482";
					}
				}catch (Exception e) {
					error=error+","+"";
				}
			try
			{
				//if(Double.parseDouble(request.getParameter("rsaValue"))<100 && Integer.parseInt(request.getParameter("no_ofCompany"))<=0)
					//error=error+","+"140";//error=error+"<br>*Please Give No-Of Insurance Company";
				//else if(request.getParameter("no_ofCompany")!=null && request.getParameter("no_ofCompany").length()>=1 && (Integer.parseInt(request.getParameter("no_ofCompany"))>0 && Double.parseDouble(request.getParameter("rsaValue"))>=100))
				//	error=error+","+"145";
			}
			catch(NumberFormatException e)
			{
				error=error+","+"140";//error=error+"<br>*Please Give No-Of Insurance Company";
			}
				
			if(request.getParameter("crossVoyage")==null)
				error=error+","+"141";//error=error+"<br>*Please Choose Cross Voyage Option";
			
			try
			{
				if("Y".equalsIgnoreCase(request.getParameter("crossVoyage"))&& (Double.parseDouble(request.getParameter("voyageValue"))<0 ||Double.parseDouble(request.getParameter("voyageValue"))>100))
				error=error+","+"142";//error=error+"<br>*Please Give Cross Voyage Amount";
			}
			catch(NumberFormatException e)
			{
				error=error+","+"142";//error=error+"<br>*Please Give Cross Voyage Amount";
			}
			
			try
			{
			//	if("Y".equalsIgnoreCase(request.getParameter("crossVoyage"))&& (Double.parseDouble(request.getParameter("crossMiniPremium"))<=0))
				//error=error+","+"300";//error=error+"<br>*Please Give crossMiniPremium Amount";
				
			}
			catch(NumberFormatException e)
			{
				error=error+","+"300";//error=error+"<br>*Please Give crossMiniPremium Voyage Amount";
			}

			if(request.getParameter("freeText")==null)
				error=error+","+"143";//error=error+"<br>*Please Choose Free Text Option";
				
			try
			{
				if(!"RSABROKER123".equalsIgnoreCase(request.getParameter("brokerId")))
				{
					String commission="".equals(request.getParameter("commission"))?"0":request.getParameter("commission");
					if(Double.parseDouble(commission)<0 || Double.parseDouble(commission)>100)
					error=error+","+"144";//error=error+"<br>*Please Give Commission";
				}
			}catch(NumberFormatException e)
			{error=error+","+"144";//error=error+"<br>*Please Give Commission";
			}
			
			try
			{
				if(Double.parseDouble(request.getParameter("miniPremium"))<0)
				error=error+","+"146";//error=error+"<br>*Please Give Commission";
			}catch(NumberFormatException e)
			{
				error=error+","+"146";//error=error+"<br>*Please Give Commission";
			}
			//Added by Hari 16/7/2012
			if(request.getParameter("currency").equalsIgnoreCase("0"))
				error=error+","+"468";
			 
			try
			{
				if(Double.parseDouble(request.getParameter("issuanceFee"))<0 || Double.parseDouble(request.getParameter("issuanceFee"))>100)
				error=error+","+"469";
			}catch(NumberFormatException e)
			{
				error=error+","+"469";
			}
			try
			{
				if(Double.parseDouble(request.getParameter("issuanceFee"))>0 && (Double.parseDouble(request.getParameter("minPremiumIssuance"))<=0) ){
					error=error+","+"470";
				}
			}catch(NumberFormatException e)
			{
				error=error+","+"470";
			}
			//End Addition 16/7/2012
			
			try 
			{
				//	if(Double.parseDouble(request.getParameter("impMiniPremium"))<0)
					//	error=error+","+"292";
			}
			catch(NumberFormatException e)
			{
				error=error+","+"292";
			}

			try 
			{
			//	if(Double.parseDouble(request.getParameter("expMiniPremium"))<0)
				//	error=error+","+"293";
			}
			catch(NumberFormatException e)
			{
				error=error+","+"293";
			}			

			try{
					if(session.getAttribute("openCoverNo")==null && request.getParameter("missippiCode")!=null && request.getParameter("missippiCode").length()>1 && !"Y".equalsIgnoreCase(request.getParameter("renewalYN")) && !"R".equalsIgnoreCase(request.getParameter("renewalYN")) && !"Y".equalsIgnoreCase(request.getParameter("endtYN")))
					{
						String businessType=request.getParameter("businessType");
					/*	if(businessType!=null && !businessType.equalsIgnoreCase("Select")){
							if(businessType.indexOf(request.getParameter("missippiCode").charAt(1))==-1){
								error=error+","+"459";
							}else{
								String result=cover.checkmisscodeProce(request.getParameter("missippiCode"));
								if(result==null || result.trim().equals("") || cover.isCkeyExists((String)session.getAttribute("proposalNo"), result)){
									error=error+","+"459";	
								}else{
									cover.setCkey(result);
								}
							}
						}*/
					}
				}
			catch(NumberFormatException e)
				{error=error+","+"161";}
			/*try
			{
				String misOpenPolicyId = "";
				boolean flag = true;
				misOpenPolicyId = request.getParameter("missi_open_policyId");
				misOpenPolicyId = misOpenPolicyId == null ? "" : misOpenPolicyId;
				misOpenPolicyId = misOpenPolicyId.trim();
				if(misOpenPolicyId.length() > 0)
				{
					flag = mocOpenPolicyId(misOpenPolicyId);
					if(!flag)
						error=error+","+"323";	
					else if(misOpenPolicyId.equals("0"))
						error=error+","+"323";	
				}
				else
					error=error+","+"171";						
			}
			catch(Exception e)
			{
				error=error+","+"171";
			}*/
			
			}	
			if(!"Quotation.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
			{
				try
				{
				if(request.getParameter("backDays")!=null && request.getParameter("backDays").length()>=1 && Double.parseDouble(request.getParameter("backDays"))<=0)
					{}
				}catch(NumberFormatException e)
				{error=error+","+"471";}

				try
				{
				if(request.getParameter("estimateAmount")!=null && request.getParameter("estimateAmount").length()>=1 && Double.parseDouble(request.getParameter("estimateAmount"))<=0)
				{}
				}catch(NumberFormatException e)
				{error=error+","+"138";}
				
				try
				{
				if(request.getParameter("rsaValue")!=null &&request.getParameter("rsaValue").length()>=1 && Double.parseDouble(request.getParameter("rsaValue"))<=0)
					{}
				}catch(NumberFormatException e)
				{error=error+","+"139";}

				try
				{
				if(request.getParameter("no_ofCompany")!=null && request.getParameter("no_ofCompany").length()>=1 && Double.parseDouble(request.getParameter("no_ofCompany"))<=0)
					{}
				}catch(NumberFormatException e)
				{error=error+","+"140";}

				try
				{
				if(request.getParameter("voyageValue")!=null && request.getParameter("voyageValue").length()>=1 &&  Double.parseDouble(request.getParameter("voyageValue"))<=0)
					{}
				}catch(NumberFormatException e)
				{error=error+","+"142";}
				try
				{
					
					//if("Y".equalsIgnoreCase(request.getParameter("crossVoyage"))&& (Double.parseDouble(request.getParameter("crossMiniPremium"))<=0))
					//error=error+","+"300";//error=error+"<br>*Please Give crossMiniPremium Amount";
				}
				catch(NumberFormatException e)
				{
					error=error+","+"300";//error=error+"<br>*Please Give crossMiniPremium Voyage Amount";
				}
				try
				{
				if(!"RSABROKER123".equalsIgnoreCase(request.getParameter("brokerId")))
					{
					if(request.getParameter("commission")!=null && request.getParameter("commission").length()>=1 && Double.parseDouble(request.getParameter("commission"))<=0)
					{}
					}
				}catch(NumberFormatException e)
				{error=error+","+"144";}
				
				try
				{
				if(request.getParameter("miniPremium")!=null && request.getParameter("miniPremium").length()>=1 && Double.parseDouble(request.getParameter("miniPremium"))<0)
					{}
				}catch(NumberFormatException e)
				{error=error+","+"146";}
				
				/*try 
				{
						if(Double.parseDouble(request.getParameter("impMiniPremium"))<0)
							error=error+","+"292";
				}
				catch(NumberFormatException e)
				{
					error=error+","+"292";
				}

				try 
				{
						if(Double.parseDouble(request.getParameter("expMiniPremium"))<0)
							error=error+","+"293";
				}
				catch(NumberFormatException e)
				{
					error=error+","+"293";
				}*/

				
				
				try
				{
					if(request.getParameter("missi_open_policyId")!=null && request.getParameter("missi_open_policyId").length()>=1)
					{}
				}
				catch(NumberFormatException e)
				{	
					error=error+","+"172";
				}
				if(request.getParameter("businessType")==null || ("Select".equalsIgnoreCase(request.getParameter("businessType"))))
					error=error+","+"133";	
			}
			//Start & End date Validation goes here
			boolean startDate=true;
			ErrorIdentify = checkDate(request.getParameter("dobDay")+"/"+request.getParameter("dobMonth")+"/"+request.getParameter("dobYear"));
			if("Invalid".equalsIgnoreCase(ErrorIdentify))
			{
				error=error+","+"116";
				startDate=false;
			}
			//End Date Validation
			if("One Stop".equalsIgnoreCase(request.getParameter("type")))
			{
				ErrorIdentify = checkDate(request.getParameter("dobDay1")+"/"+request.getParameter("dobMonth1")+"/"+request.getParameter("dobYear1"));
				if("Invalid".equalsIgnoreCase(ErrorIdentify))
				{
					error=error+","+"117";
				}
				else if(startDate)
				{
					boolean checkDate=checkingDates(request.getParameter("dobDay"),request.getParameter("dobMonth"),request.getParameter("dobYear"),request.getParameter("dobDay1"),request.getParameter("dobMonth1"),request.getParameter("dobYear1"));
					if(checkDate)
					{
						error=error+","+"117";
					}
				}
			}
			//End of date Validation
			try
			{
				String minPreMul=request.getParameter("minPreMul");
				if(minPreMul==null || minPreMul.trim().length()<=0){
					if("M".equalsIgnoreCase(request.getParameter("minPreMulType"))){
						error=error+","+"472";
					}
				}else if(Double.parseDouble(minPreMul)<=0){
					error=error+","+"472";
				}
			}catch(NumberFormatException e)
			{error=error+","+"472";}
			//	error="";
				if("0".equalsIgnoreCase(error))
					error="";
			if(error.length()>0)
			{
					request.setAttribute("errorDetail",getErrors(error));
					rd=getServletContext().getRequestDispatcher("/premiumOpenCover/newOpenCover.jsp");
			}
			else
			{		
						
				  String sdate="";
				  String edate="";
				  String dobDay = "";
				  String dobDay1 = "";
				  String dobMonth = "";
				  String dobMonth1 = "";
				  String dobYear = "";
				  String dobYear1 = "";
				  String proCommission = "";
				  String type="";
				//Setting Values To Bean
				  type=request.getParameter("type");
				String estimateAmount = request.getParameter("estimateAmount")==null?"0":request.getParameter("estimateAmount");
				String exchange = request.getParameter("currencyValue")==null?"1":request.getParameter("currencyValue");
				cover.setBrokerId(request.getParameter("brokerId"));
				cover.setType(request.getParameter("type"));
				cover.setCustomerName(request.getParameter("customerName"));
				cover
						.setCustomerId(request.getParameter("customerId") == null ? ""
								: request.getParameter("customerId"));
				cover
						.setBackDays(request.getParameter("backDays") == null ? "0"
								: request.getParameter("backDays"));
				cover.setEstimateAmount(Float.toString((Float
						.parseFloat(estimateAmount))
						* (Float.parseFloat(exchange))));
				System.out.println("Estimate ::" + cover.getEstimateAmount());
				cover
						.setRsaValue(request.getParameter("rsaValue") == null ? "0"
								: request.getParameter("rsaValue"));
				cover
						.setNo_ofCompany(request.getParameter("no_ofCompany") == null ? "0"
								: request.getParameter("no_ofCompany"));
				cover
						.setCrossVoyage(request.getParameter("crossVoyage") == null ? ""
								: request.getParameter("crossVoyage"));
				cover
						.setVoyageValue(request.getParameter("voyageValue") == null ? ""
								: request.getParameter("voyageValue"));
				cover.setFreeText(request.getParameter("freeText") == null ? ""
						: request.getParameter("freeText"));
				cover
						.setCommission((request.getParameter("commission") == null || request
								.getParameter("commission")
								.equalsIgnoreCase("")) ? "0" : request
								.getParameter("commission"));
				cover
						.setMiniPremium(request.getParameter("miniPremium") == null ? "0"
								: request.getParameter("miniPremium"));
				cover.setMissippiCode(request.getParameter("missippiCode"));
				cover.setBusinessType(request.getParameter("businessType")==null?"":request.getParameter("businessType"));
				cover.setMissippiOpenPolicyId(request.getParameter("missi_open_policyId")==null?"":request.getParameter("missi_open_policyId"));
				cover.setImpMiniPremium(request.getParameter("impMiniPremium")==null?"0":request.getParameter("impMiniPremium"));
				cover.setExpMiniPremium(request.getParameter("expMiniPremium")==null?"0":request.getParameter("expMiniPremium"));
				cover.setCrossMiniPremium(request.getParameter("crossMiniPremium")==null?"0":request.getParameter("crossMiniPremium"));
				cover.setCurrency(request.getParameter("currency")==null?"":request.getParameter("currency"));
				cover.setExchangeRate(exchange);
				cover.setMinPremiumIssuance((request.getParameter("minPremiumIssuance")==null || request.getParameter("minPremiumIssuance").equalsIgnoreCase(""))?"0":request.getParameter("minPremiumIssuance"));
				cover.setIssuanceFee(request.getParameter("issuanceFee")==null?"":request.getParameter("issuanceFee"));
				cover.setForeignTurnOver(estimateAmount);
				cover.setMinPreMulType((request.getParameter("minPreMulType")==null || request.getParameter("minPreMulType").equalsIgnoreCase(""))?"I":request.getParameter("minPreMulType"));
				cover.setMinPreMul((request.getParameter("minPreMul")==null || request.getParameter("minPreMul").equalsIgnoreCase(""))?"":request.getParameter("minPreMul"));
				cover.setLossDetail((request.getParameter("lossDetail")==null || request.getParameter("lossDetail").equalsIgnoreCase(""))?"":request.getParameter("lossDetail"));
				cover.setClaimRatio((request.getParameter("claimRatio")==null || request.getParameter("claimRatio").equalsIgnoreCase(""))?"":request.getParameter("claimRatio"));
				cover.setAdditionalInfo((request.getParameter("additionalInfo")==null || request.getParameter("additionalInfo").equalsIgnoreCase(""))?"":request.getParameter("additionalInfo"));
				cover.setGuestLogin((request.getParameter("guestLogin")==null || request.getParameter("guestLogin").equalsIgnoreCase(""))?"":request.getParameter("guestLogin"));
				//July 30
				cover.setWsrc((String)session.getAttribute("wsrc1"));
				cover.setUserId((String)session.getAttribute("BROKERUSERID"));
				
				cover.setUtilizedAmount(request.getParameter("utilizedAmount")==null?"0":request.getParameter("utilizedAmount").replaceAll(",", ""));
				
				System.out.println("hello");
				 dobDay=request.getParameter("dobDay")==null?"0":request.getParameter("dobDay");
				 dobMonth=request.getParameter("dobMonth")==null?"0":request.getParameter("dobMonth");
				 dobYear=request.getParameter("dobYear")==null?"0":request.getParameter("dobYear");
				 dobDay1=request.getParameter("dobDay1")==null?"0":request.getParameter("dobDay1");
				 dobMonth1=request.getParameter("dobMonth1")==null?"0":request.getParameter("dobMonth1");
				 dobYear1=request.getParameter("dobYear1")==null?"0":request.getParameter("dobYear1");
				 sdate = dobDay+"-"+dobMonth+"-"+dobYear;
				 
				 
				 System.out.println("sdate:"+sdate+"edate--->");
				 if(type.equals("Standard"))
				 {
					 edate =dobDay+"-"+dobMonth+"-"+(Integer.parseInt(dobYear)+20); 
				 }
				 else{
					 edate = dobDay1+"-"+dobMonth1+"-"+dobYear1; 
				 }
				 if(type.equals("One Stop") && "GUEST".equalsIgnoreCase(request.getParameter("user")))
				 {
					 edate =dobDay+"-"+dobMonth+"-"+(Integer.parseInt(dobYear)+1); 
				 }
				 System.out.println("sdate:"+sdate+"edate--->"+edate);
				 System.out.println("edate:"+edate+"type--->"+type);
				 ////sdate = dobDay+"-"+dobMonth+"-"+dobYear;
				 				 
				 proCommission = request.getParameter("proCommission");
				 proCommission = proCommission==null?"0":proCommission;
				 cover.setProCommission(proCommission);				 
				 cover.setSdate(sdate);
				 cover.setEdate(edate);
				//if(!"Y".equalsIgnoreCase(request.getParameter("renewalYN"))&& !"R".equalsIgnoreCase(request.getParameter("renewalYN")) && !"Y".equalsIgnoreCase(request.getParameter("endtYN")) && cover.checkingMissippiCode((String)session.getAttribute("proposalNo")))
				//if(!"Y".equalsIgnoreCase(request.getParameter("renewalYN"))&& !"R".equalsIgnoreCase(request.getParameter("renewalYN")) && !"Y".equalsIgnoreCase(request.getParameter("endtYN"))) {
				 if("Y".equalsIgnoreCase(request.getParameter("renewalYN")) && StringUtils.isNotBlank(request.getParameter("missippiCode"))){
					 cover.setMissippiCode(request.getParameter("missippiCode"));
					 boolean code = cover.checkingMissippiCode((String)session.getAttribute("proposalNo"));
					 if(code)
						 error="<br>*Existing Core Application Policy No already used";
				 }
				 
				 if(!"Y".equalsIgnoreCase(request.getParameter("endtYN"))) {
					 String missippiCode = cover.getMissippiCode()==null?"":cover.getMissippiCode();
					 String[] missippiPreix = missippiCode.split("/");
					 String adminBranch = (String)session.getAttribute("AdminBranchCode");
					 /*if(!"".equals(missippiPreix[0]) && !missippiPreix[0].equals(adminBranch)) {
						 error="<br>*This Policy No can not be created in this Branch Admin";
					 }
					 else if(cover.CoreMissippiCheck()<=0) {
						 error="<br>*Core Application Policy No Not Available in Premia";
					 }
					 else if(cover.checkingMissippiCode((String)session.getAttribute("proposalNo"))) {
						 error="<br>*Existing Core Application Policy No already used";
					 }*/
				 }
				if(error.length()>1)
				{
					request.setAttribute("errorDetail",error);

					rd=getServletContext().getRequestDispatcher("/premiumOpenCover/newOpenCover.jsp");
				}
				else
				{
					String adminBranch = (String)session.getAttribute("AdminBranchCode");
					session.setAttribute("missippiCode",request.getParameter("missippiCode"));
					if(session.getAttribute("proposalNo")==null)
					session.setAttribute("proposalNo",cover.getProposalNos(adminBranch));
					cover.setDefaultCluases((request.getParameter("defaultClauses")==null || request.getParameter("defaultClauses").equalsIgnoreCase(""))?"":request.getParameter("defaultClauses"));
					cover.setExecutiveId(request.getParameter("executiveId")==null?"":request.getParameter("executiveId"));
					cover.setDebitType(request.getParameter("debitType")==null?"":request.getParameter("debitType"));
					System.out.println("typeQutation:"+type);
					session.setAttribute("type",type);
					cover.setProposalNo((String)session.getAttribute("proposalNo"));
					cover.setProposalStatus(request.getParameter("proposalStatus"));
					cover.setConfirmStatus(request.getParameter("confirmStatus"));
					out.println("busines type"+request.getParameter("businessType"));
				    error=cover.storedValues((String)session.getAttribute("product_id"),(String)session.getAttribute("openCoverNo"),
					adminBranch,(String)session.getAttribute("user"));

				if(error.length()>1)
				{
					request.setAttribute("errorDetail","Invalid Data");
					rd=getServletContext().getRequestDispatcher("/premiumOpenCover/newOpenCover.jsp");
				}
				
				else
					{response.sendRedirect(request.getParameter("actionPath"));
				out.println("   REDIRECT    ");
				if(true)
					return;
					}}
			}
		}

		else if("Quotation".equalsIgnoreCase(path))
			
		{
			System.out.println("requestform---->"+path);
			/***  COMING FROM Quotation.jsp   ***/
			java.util.HashMap modeValues=null;
			modeValues=new java.util.HashMap();
			String type=request.getParameter("type");
			System.out.println("Type:"+type);
			//EffectiveDate Validation
			ErrorIdentify = checkDate(request.getParameter("effectiveDay")+"/"+request.getParameter("effectiveMonth")+"/"+request.getParameter("effectiveYear"));
	
			if("Invalid".equalsIgnoreCase(ErrorIdentify))
			{
				error=error+"<br>*"+runner.getErrormsg("351");
				error1=error1+"<br>*"+runner.getErrormsg("351");
			}
			if(error.length()==0)
			{
				if(session.getAttribute("openCoverNo")!=null)
				{
					boolean checkDate=checkingSysDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"));
					if(checkDate)
					{
						error=error+"<br>*"+runner.getErrormsg("351");
						error1=error1+"<br>*"+runner.getErrormsg("351");
					}if(!checkDate)
					{
						String[][] date=new com.maan.opencover.bean.openCoverQuotation().getOpenCoverDate((String)session.getAttribute("proposalNo"));
						if(date!=null && date.length>0)
						{
							checkDate=checkingDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"),date[0][3],date[0][4],date[0][5]);
							if(checkDate)
							{
								error=error+"<br>*"+runner.getErrormsg("351");
								error1=error1+"<br>*"+runner.getErrormsg("351");
							}
						}
					}
				}
			}
			//EffectiveDate Validation
			//Start Date Validation
	/*ErrorIdentify = checkDate(request.getParameter("startDay")+"/"+request.getParameter("startMonth")+"/"+request.getParameter("startYear"));

			if("Invalid".equalsIgnoreCase(ErrorIdentify))
			{
				error=error+"<br>*"+runner.getErrormsg("116");
				error1=error1+"<br>*"+runner.getErrormsg("116");
			}
			if(error.length()==0)
			{
				boolean checkDate=checkingDates(request.getParameter("startDay"),request.getParameter("startMonth"),request.getParameter("startYear"),request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"));
				if(checkDate)
				{
					error=error+"<br>*"+runner.getErrormsg("116");
					error1=error1+"<br>*"+runner.getErrormsg("116");
				}
				if(session.getAttribute("openCoverNo")!=null && !checkDate)
				{
					checkDate=checkingSysDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"));
					if(checkDate)
					{
						error=error+"<br>*"+runner.getErrormsg("351");
						error1=error1+"<br>*"+runner.getErrormsg("351");
					}
				}
			}
			
			//End Date Validation
			System.out.println("typequatation"+type);			
				  ErrorIdentify=checkDate(request.getParameter("endDay")+"/"+request.getParameter("endMonth")+"/"+request.getParameter("endYear"));
		
			
			System.out.println("endday---->"+request.getParameter("endDay"));
	System.out.println("endday---->"+request.getParameter("endMonth"));		
	System.out.println("endday---->"+request.getParameter("endYear"));
	
			if("Invalid".equalsIgnoreCase(ErrorIdentify))
			{
				error=error+"<br>*"+runner.getErrormsg("117");
				error1=error1+"<br>*"+runner.getErrormsg("117");
			}
			else
			{
				boolean checkDate=checkingDates(request.getParameter("startDay"),request.getParameter("startMonth"),request.getParameter("startYear"),request.getParameter("endDay"),request.getParameter("endMonth"),request.getParameter("endYear"));
				if(checkDate)
				{
					error=error+"<br>*"+runner.getErrormsg("117");
					error1=error1+"<br>*"+runner.getErrormsg("117");
				}
			  }*/
			
			String extraCovers="";
	
			//Originating Country Validation
	ErrorIdentify=request.getParameter("totalTransitId");
				try
			{if(Integer.parseInt(ErrorIdentify)<1)
				error=error+"<br>*"+runner.getErrormsg("349");

			}catch(Exception e)
			{
				error=error+"<br>*"+runner.getErrormsg("349");
			}
			
			//Destination Country Validation
	ErrorIdentify=request.getParameter("totalDestinationId");
	
			try
			{
				if(Integer.parseInt(ErrorIdentify)<1)
				{
					error=error+"<br>*"+runner.getErrormsg("350");
				}
			}catch(Exception e)
			{
				error=error+"<br>*"+runner.getErrormsg("350");
			}
	ErrorIdentify=request.getParameter("txtarCryRemarkes");
			try{
				if(!ErrorIdentify.equals(""))
				{
				if(ErrorIdentify.length()>300)
				{
					error=error+"<br>*"+runner.getErrormsg("460");	
				}
				}
			}catch (Exception e)
			{
				System.out.println(e.getMessage());
			}

	//Destination Country Validation
	ErrorIdentify=request.getParameter("TotalCommudity");
			try
			{
				if(Integer.parseInt(ErrorIdentify)<1)
					error=error+"<br>*"+runner.getErrormsg("120");
			}
			catch(Exception e)
			{
				error=error+"<br>*"+runner.getErrormsg("120");
			}
			//Sale Term Master
			ErrorIdentify = request.getParameter("saleTermId")==null?"":request.getParameter("saleTermId");
			System.out.println("ErrorIdentify..........."+ErrorIdentify);
			System.out.println("ErrorIdentify..........."+ErrorIdentify.length());
			if(ErrorIdentify.length() == 0 )
			{
				error=error+"<br>*"+runner.getErrormsg("359");
			}
			System.out.println("Errorrrrrrrrrrrrrrrr....."+error);
			//Mode And Cover Selection and perbottom Limit Validation
	if(session.getAttribute("modeMaster")!=null || session.getAttribute("coverMaster")!=null)
	{
			String[][] modeMaster=(String[][])session.getAttribute("modeMaster");
			String[][] coverMaster=(String[][])session.getAttribute("coverMaster");
			String Error="", limit="", currencyValue="";
			double total=0;
//			double total1=0;
			boolean modeChecking=true;
			boolean coverChecking=true;
			modeValues.put("modeLength",""+modeMaster.length);
			modeValues.put("coverLength",""+coverMaster.length);
			String turnover=cover.getAnnualTurnover((String)session.getAttribute("proposalNo"));
			for(int i=0;i<modeMaster.length;i++)
			{
				if(request.getParameter("modeTransport_"+modeMaster[i][0])!=null)
				{
					modeValues.put("mode-id"+i,modeMaster[i][0]);
					modeValues.put("mode-name"+i,request.getParameter("modeTransport_"+modeMaster[i][0]));
					modeValues.put("perbottom-limit"+i,request.getParameter("perbottomLimit_"+modeMaster[i][0]));
					modeValues.put("currencyId"+i,request.getParameter("currencyId_"+modeMaster[i][0]));
					modeValues.put("currencyValue"+i,"null".equals(request.getParameter("currencyValue_"+modeMaster[i][0]))?"":request.getParameter("currencyValue_"+modeMaster[i][0]));

					if(modeChecking)
					modeChecking=false;
				
				 ErrorIdentify=request.getParameter("perbottomLimit_"+modeMaster[i][0]);
				 Error=request.getParameter("currencyId_"+modeMaster[i][0]);
				 try{
				 currencyValue=request.getParameter("currencyValue_"+modeMaster[i][0]);
				 currencyValue=currencyValue==null||"null".equals(currencyValue)||"".equals(currencyValue) ?"0":currencyValue;
				 limit=request.getParameter("perbottomLimit_"+modeMaster[i][0]);
				 limit=limit==null||"".equals(limit)||"null".equals(limit)?"0":limit;
				 total=Double.parseDouble(limit)*Double.parseDouble(currencyValue);
				 }catch(Exception e){}
//				 total1+=Double.parseDouble(limit)*Double.parseDouble(currencyValue);
			try
			{
			if(Double.parseDouble(ErrorIdentify)<=0)
			{
			error=error+"<br>*Please Provide PerBottom Limit for "+modeMaster[i][1]+" Transport";	
			}
			}
			catch(Exception e)
			{
				error=error+"<br>*Please Provide PerBottom Limit for "+modeMaster[i][1]+" Transport";
			}
			try
			{
			if(Double.parseDouble(Error)<=0)
			{
			error=error+"<br>*Please Select Currency for "+modeMaster[i][1]+" Transport";	
			}
			}
			catch(Exception e)
			{
				error=error+"<br>*Please Select Currency for "+modeMaster[i][1]+" Transport";
			}
			try
			{
			if(total > Double.parseDouble(turnover))
		    {
			error=error+"<br>*"+modeMaster[i][1]+" Transport PerBottom Limit should be less than Annual Turnover";
		    }
			 
			}
			catch(Exception e)
			{
				error=error+"<br>*Please Provide PerBottom Limit for "+modeMaster[i][1]+" Transport";
			}
				 
				if(!"Premium.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
				{
					try
					{
				if(ErrorIdentify!=null && ErrorIdentify.length()>=1 && Double.parseDouble(ErrorIdentify)<=0)
			error1=error1+"<br>*Please Provide PerBottom Limit for "+modeMaster[i][1]+" Transport";			}catch(Exception e)
			{
				error1=error1+"<br>*Please Provide PerBottom Limit for "+modeMaster[i][1]+" Transport";
			}

				}
				 coverChecking=true;
				
				 for(int j=0;j<coverMaster.length;j++)
				 {
					if(coverMaster[j][0].equalsIgnoreCase(modeMaster[i][0]))
					{
						if(request.getParameter("cover_"+coverMaster[j][1])!=null)
						{
							modeValues.put("mode-idd"+j,modeMaster[i][0]);
							//modeValues.put("mode-name"+j,modeMaster[i][1]);
							modeValues.put("cover-id"+j,coverMaster[j][1]);
							modeValues.put("cover-name"+j,coverMaster[j][2]);
							modeValues.put("cover-type"+j,(request.getParameter("coverTypeIds_"+coverMaster[j][1])==null || "null".equals(request.getParameter("coverTypeIds_"+coverMaster[j][1])))?"":request.getParameter("coverTypeIds_"+coverMaster[j][1]));
						
							if(coverChecking)
								coverChecking=false;
						  }
					  }
				 }
			
			  if(coverChecking)
				error=error+"<br>*Please select coverages for "+modeMaster[i][1]+" transport";
				}
			}
			/*if(total1 > Double.parseDouble(cover.getEstimateAmount()))
			{
			error=error+"<br>*Total PerBottom Limit should be less than Annual Turnover";	
			}*/
			if(modeChecking)
				error=error+"<br>*"+runner.getErrormsg("121");
		}

	  		for(int i=0;i<Integer.parseInt(request.getParameter("extraCoverLength")==null?"0":request.getParameter("extraCoverLength"));i++)
			{
				  if(request.getParameter("extracover"+i)!=null)
				extraCovers=extraCovers+","+(request.getParameter("extracover"+i));
			}
			if((extraCovers.trim()).length()>1)
				extraCovers=extraCovers.substring(1,extraCovers.length());
	
	if(!"Premium.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
	{		
			error="";
			if(error1.length()>0)
				error=error1;
			}
			
			if(error.length()<=0)
			{
				com.maan.opencover.bean.openCoverQuotation quote=new com.maan.opencover.bean.openCoverQuotation();
				quote.setProposalNo((String)session.getAttribute("proposalNo"));

				quote.setStartDay(request.getParameter("startDay"));
				quote.setStartMonth(request.getParameter("startMonth"));
				quote.setStartYear(request.getParameter("startYear"));
				
				quote.setEndDay(request.getParameter("endDay"));
				quote.setEndMonth(request.getParameter("endMonth"));
				quote.setEndYear(request.getParameter("endYear"));
			    quote.setTransit_countryId(request.getParameter("transit_countryId")==null?"":request.getParameter("transit_countryId"));
			    quote.setDestination_countryId(request.getParameter("destination_countryId")==null?"":request.getParameter("destination_countryId"));
			    quote.setCountryRemarks(request.getParameter("txtarCryRemarkes")==null?"":request.getParameter("txtarCryRemarkes"));	
                System.out.println("country:"+request.getParameter("txtarCryRemarkes"));
				String commodities[][]=new String[Integer.parseInt(request.getParameter("TotalCommudity")==null?"0":request.getParameter("TotalCommudity"))][4];
			java.util.StringTokenizer tokens=new java.util.StringTokenizer(request.getParameter("commodityIds")==null?"":request.getParameter("commodityIds"),",");
				int i=0;
				//while(tokens.hasMoreTokens())
				for(int k=1;k<=Integer.parseInt(request.getParameter("commoditylen"));k++)
				{
					if("selected".equals(request.getParameter("commodityId"+k))){
					String nextValue=k+"";
						//(String)tokens.nextToken();
					commodities[i][0]=request.getParameter("commodityNum"+nextValue)==null?"":request.getParameter("commodityNum"+nextValue);
					commodities[i][1]=request.getParameter("commodity_desc"+nextValue)==null?"":request.getParameter("commodity_desc"+nextValue);
					commodities[i][2]=request.getParameter("commodity_Fragile"+nextValue)==null?"":request.getParameter("commodity_Fragile"+nextValue);
					String comType = request.getParameter("commodityType"+nextValue)==null?"":request.getParameter("commodityType"+nextValue);
					if(comType.equalsIgnoreCase("select")) 
						commodities[i][3]= "";
					commodities[i][3]= comType;
					i++;
					}
				}
			
				String currencyType = (String) session.getAttribute("currencyType");
				quote.setCurrencyName(currencyType);
				quote.setExchangeRate("1");
				quote.setCurrencyId("11");
				quote.setExchangeId("1");
				quote.setExtraCovers(extraCovers);
				quote.setEffectiveDay(request.getParameter("effectiveDay"));
				quote.setEffectiveMonth(request.getParameter("effectiveMonth"));
				quote.setEffectiveYear(request.getParameter("effectiveYear"));
				quote.setWsrc((request.getParameter("wsrc")==null?"N":request.getParameter("wsrc")));
				quote.setSaleTermId(request.getParameter("saleTermId")==null?"":request.getParameter("saleTermId"));
				quote.setToleranceId(request.getParameter("toleranceId")==null?"":request.getParameter("toleranceId"));
				quote.setToleranceName(request.getParameter("tolerance")==null?"":request.getParameter("tolerance"));
				quote.setAdditionalInsured(request.getParameter("customerId")==null?"":request.getParameter("customerId"));
				/*quote.setAdditionalInsuredName(request.getParameter("customer")==null?"":request.getParameter("customer"));
				//
				quote.storedValues(modeValues,commodities,(String)session.getAttribute("openCoverNo"),currencyType);
				
				out.println("   REDIRECT    "+request.getParameter("actionPath"));
				response.sendRedirect(request.getParameter("actionPath"));*/
				quote.setProposalStatus(request.getParameter("proposalStatus")==null?"Normal":request.getParameter("proposalStatus"));
				//
				int count=0;
				for(int j=0;j<commodities.length;j++){
					if(commodities[j][0]!=null ||!"null".equalsIgnoreCase(commodities[j][0])){
						boolean flag = getReferralStatus(commodities[j][0],(String)session.getAttribute("BelongingBranch"));
						if(!flag)
							count++;
					}
				}
				if(count>0 && ("Normal".equalsIgnoreCase(request.getParameter("proposalStatus")) || "Referral".equalsIgnoreCase(request.getParameter("proposalStatus")))){
					quote.setProposalStatus("Referral");
				}
				
				quote.storedValues(modeValues,commodities,(String)session.getAttribute("openCoverNo"),currencyType);
				
				if("GUEST".equalsIgnoreCase(request.getParameter("user"))){
					if(count>0 && ("Normal".equalsIgnoreCase(request.getParameter("proposalStatus")) || "Referral".equalsIgnoreCase(request.getParameter("proposalStatus")))){
						String message="Commodity Referral in HEAD OFFICE";
						sendMail(message);
						response.sendRedirect("showOpencover.jsp?referral=referral");
					}else{
						response.sendRedirect(request.getParameter("actionPath"));
					}
				}else{
					out.println("   REDIRECT    "+request.getParameter("actionPath"));
					response.sendRedirect(request.getParameter("actionPath"));
				}
				if("Premium.jsp".equalsIgnoreCase(request.getParameter("actionPath"))) {
					String defaultClauses = quote.getDefaultClauses(quote.getProposalNo());
					if("Y".equalsIgnoreCase(defaultClauses)) {
						String belongingBranch = (String) session.getAttribute("BelongingBranch");
						quote.insertDefaultClauses(quote.getProposalNo(),belongingBranch);
					}
				}
				
					if(true)
						return;
			}
			else
			{
				request.setAttribute("error",error);
				rd = getServletContext().getRequestDispatcher("/premiumOpenCover/Quotation.jsp");
			}	
		}
		/*         INPUT OPENCOVER PAGE CONTROLLER         */

		/*        RATE MODIFICATION SCREEN     */
		else if("rateModification".equalsIgnoreCase(path))
		{	
			
			if(request.getParameter("addDeductible").equalsIgnoreCase("Y")){
				String res=request.getParameter("totalDeductibles");
				request.setAttribute("totalDeductibles", (Integer.parseInt(res)));
				System.out.println(" (Integer.parseInt(res)+1)"+ (Integer.parseInt(res)));
				RequestDispatcher rd1 = request.getRequestDispatcher(request.getParameter("actionPath"));
				rd1.forward(request, response);
				return;
			}
			double finaltotal=0.0;
			java.util.ArrayList storedValue=new java.util.ArrayList();
				error="";error1="";
				out.println("<br>error--------->"+error.length());
				String covers=null;
				java.util.StringTokenizer tokens=null;
			ErrorIdentify=checkDate(request.getParameter("effectiveDay")+"/"+request.getParameter("effectiveMonth")+"/"+request.getParameter("effectiveYear"));
			if("Invalid".equalsIgnoreCase(ErrorIdentify))
			{
				error=error+"<br>*"+runner.getErrormsg("351");
				error1=error1+"<br>*"+runner.getErrormsg("351");
			}if(error.length()==0)
			{
				if(session.getAttribute("openCoverNo")!=null)
				{
					boolean checkDate=checkingSysDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"));
					if(checkDate)
					{
						error=error+"<br>*"+runner.getErrormsg("351");
						error1=error1+"<br>*"+runner.getErrormsg("351");
					}if(!checkDate)
					{
						String[][] date=new com.maan.opencover.bean.openCoverQuotation().getOpenCoverDate((String)session.getAttribute("proposalNo"));
						if(date!=null && date.length>0)
						{
							checkDate=checkingDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"),date[0][3],date[0][4],date[0][5]);
							if(checkDate)
							{
								error=error+"<br>*"+runner.getErrormsg("351");
								error1=error1+"<br>*"+runner.getErrormsg("351");
							}
						}
					}
				}
			}	
			/*if(error.length()==0)
			{
				if(session.getAttribute("openCoverNo")!=null)
				{
					boolean checkDate=checkingSysDates(request.getParameter("effectiveDay"),request.getParameter("effectiveMonth"),request.getParameter("effectiveYear"));
					if(checkDate)
						error=error+"<br>*"+runner.getErrormsg("351");
				}
			}*/
					//Error Finding In rate for Each Commodity
				int count=0,count1=0;
				String[][] totalValue=new String[Integer.parseInt(request.getParameter("totalCommodity"))][2];
				/****Starrt deductible validation****/
				int total = Integer.parseInt(request.getParameter("totalDeductibles"));
				
				boolean exists = false;
				boolean invalid =false;
				List<Map> finalvalues =new ArrayList();
				for(int i=1;i<=total;i++){
					boolean startExists = false;
					boolean endExists = false;
					boolean amountExists = false;
					boolean invalidExists = false;
					boolean invalidStart = false;
					boolean invalidEnd = false;
					boolean invalidAmount = false;
					boolean currencyExists = false;
					boolean percentExists=false;
					String curError = "";
					Map<String,String> values = new HashMap();
					try{
						if(request.getParameter("startRange"+i)==null){
							startExists = false;
						}
						else if((request.getParameter("startRange"+i)!=null) && (!request.getParameter("startRange"+i).equalsIgnoreCase(""))){
							startExists = true;
							values.put("start",request.getParameter("startRange"+i));
							if(Double.parseDouble(request.getParameter("startRange"+i))<0 )
							{
								error=error+"<br>* Enter Valid Start Range in row "+i;
								curError = "<br>* Enter Valid Start Range in row "+i;
							}
						}
						else{
							startExists = false;
							
						}
					
					}
					catch(NumberFormatException e){
						if(startExists ){
						error=error+"<br>* Enter Valid Start Range in row "+i;
						curError = "error";
						}
						invalidExists =true;						
						invalidStart = true;
						
					}
					try{
						if(request.getParameter("endRange"+i)==null){
							endExists = false;
						}
						else if((request.getParameter("endRange"+i)!=null) && (!request.getParameter("endRange"+i).equalsIgnoreCase(""))){
							endExists = true;
							values.put("end",request.getParameter("endRange"+i));
						if(Double.parseDouble(request.getParameter("endRange"+i))<0 )
						{
							error=error+"<br>* Enter Valid End Range in row "+i;
						}
						if (Double.parseDouble(request.getParameter("endRange"+i))<=Double.parseDouble(request.getParameter("startRange"+i)))
						 {
							 error=error+"<br>*  End Range should be greater than Start Range in row "+i;
						 }
						}else{
							endExists = false;
							
						}
						 
					
					}
					catch(NumberFormatException e){
						if(endExists){
						error=error+"<br>* Enter Valid End Range in row "+i;
						curError = "error";
						}
						invalidExists =true;
						invalidEnd = true;
					}
					try{
						if(request.getParameter("excessAmount"+i)==null){
							amountExists = false;
						}
						else if((request.getParameter("excessAmount"+i)!=null) && (!request.getParameter("excessAmount"+i).equalsIgnoreCase(""))){
							amountExists = true;
							values.put("amount",request.getParameter("excessAmount"+i));
							if(Double.parseDouble(request.getParameter("excessAmount"+i))<0 )
							{
								error=error+"<br>* Enter Valid Excess Amount in row "+i;
								curError = "error";
							}
						}
						else{
							values.put("amount","0");
							amountExists = false;							
						}
						
						if(request.getParameter("excessPercent"+i)==null){
							percentExists=false;
							
						}else if((request.getParameter("excessPercent"+i)!=null)&&(!request.getParameter("excessPercent"+i).equalsIgnoreCase(""))){
							percentExists=true;
							values.put("percent", request.getParameter("excessPercent"+i));
							if(Double.parseDouble(request.getParameter("excessPercent"+i))<0){
								error=error+"<br>* Enter Valid Excess Percent in row "+i;
								curError = "error";
							}
						}
						else{
							 percentExists=false;
							 values.put("percent","0");
						}
						
						/*if(!amountExists && !percentExists && (startExists || endExists) ){ 
							error=error+"<br>* Enter Excess Amount or Excess Percent in row "+i;
							curError = "error";
						}*/
						
							
						
						if( (!request.getParameter("currencyValue"+i).equalsIgnoreCase("0"))){
							values.put("currencyValue",request.getParameter("currencyValue"+i));
							currencyExists = true;
						}
						else
						{
							currencyExists = false;
						}
						
					}
					catch(NumberFormatException e){
						if(amountExists){
						error=error+"<br>*  Enter Valid Excess Amount in row "+i;
						curError = "error";
						}
						if(percentExists){
							error=error+"<br>*  Enter Valid Excess Percent in row "+i;
							curError = "error";
							}
						invalidExists =true;
						invalidAmount = true;
					}
				
					
					if(invalidExists || startExists || endExists || amountExists || currencyExists ||percentExists){
						if(!startExists )
							error=error+"<br>* Enter  Start Range in row "+i;
						if(!endExists  )
							error=error+"<br>* Enter  End Range in row "+i;
						if(!amountExists && ! percentExists)
							error=error+"<br>* Enter Excess Amount or Excess Percent in row "+i;
						if(!currencyExists)	
							error=error+"<br>* Select Currency in row "+i;
						/*if(!percentExists)
							error=error+"<br>*  Enter Valid Excess Percent in row "+i;*/
					}
			
					if(invalidExists)
						invalid=true;
					 if(((amountExists || percentExists) && endExists && startExists))
					{
						 if(curError.equalsIgnoreCase("") && values.get("start")!=null && values.get("end")!=null && (values.get("amount")!=null || values.get("percent")!=null))
							{
								finalvalues.add(values);
							}
						exists = true;
					}
										 
				}
				/*if(!(exists) && error.equalsIgnoreCase(""))
				{
					error="<br>*  Enter Start Range Value"
						+"<br>*  Enter End Range Value"
						+"<br>*  Enter Excess Amount Value";
				}*/
				/*for(int k=0;k<finalvalues.size();k++){
					Map m = finalvalues.get(k);
					if(k!=0){
					 Map m1 = finalvalues.get(k-1);
					 if((Integer.parseInt(m.get("start").toString())-1)!=((Integer.parseInt(m1.get("end").toString())))){
						 error=error+"Incorrect Start Range in row "+( k+1);
					 }
					}
					System.out.println("Map 1::"+ m.get("start"));
					System.out.println("Map 2::"+ m.get("end"));
					System.out.println("Map 3::"+ m.get("amount"));
				}*/
				rateModification rate=new rateModification();
                error = error+rate.getRangeValidation(finalvalues);
				
				/*****End Deductible validation***/
			String coverId=null;
			for(int i=0;i<Integer.parseInt(request.getParameter("totalCommodity"));i++)
			{
				totalValue[i][0]=request.getParameter("commoditys_"+i);
				totalValue[i][1]="";
				count=0;count1=0;
				tokens=new java.util.StringTokenizer(request.getParameter("coverName"),",");
				coverId="";
				while(tokens.hasMoreTokens())
						{covers=(String)tokens.nextToken();
						coverId=covers;
				covers=covers.substring(0,covers.indexOf('-'));
				
				/*try
				{
					if(i<1&& (request.getParameter("clauses_"+covers)==null || Double.parseDouble(request.getParameter("clauses_"+covers))<=0))
						error=error+"<br>*Please Select Clauses for "+(coverId.substring(coverId.indexOf('-')+1,coverId.length()));
				}catch(Exception e){}*/
				
				try{
					HashMap fullDetails=new HashMap();
					String[][] clauses   = new String[0][0];
					String args[] = new String[0];
					String clausesId="",description="",effectDate="",coversId="",Query="",result="";
					int finalCount=0;
					ConditionsOpenCover cT=new  ConditionsOpenCover();
					//INSERTING CLAUSES FOR GUEST USER
					Query = "SELECT COUNT(*) FROM OPEN_COVER_CLAUSES WHERE PROPOSAL_NO='"+(String)session.getAttribute("proposalNo")+"' AND COVER_ID=" + covers +"";
					result = runner.singleSelection(Query);
					if("GUEST".equalsIgnoreCase(request.getParameter("user")) && result.equalsIgnoreCase("0")){
		            String coverDesc = "", coverDescQry = "";
		            coverDescQry = "select distinct description from cover_master where branch_code = " + (String)session.getAttribute("adminBranch") + " and cover_id = " + covers +"";
		            coverDesc = runner.singleSelection(coverDescQry);
					args = new String[4];
					args[0] = (String)session.getAttribute("adminBranch");
					args[1] = covers;
					args[2] = (String)session.getAttribute("proposalNo");
					args[3] = (String)session.getAttribute("proposalNo");
					Query="SELECT DISTINCT CM.CLAUSES_ID CLAUSES_ID,CM.CLAUSES_DESCRIPTION CLAUSES_DESCRIPTION,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM CLAUSES_MASTER CM,COMMODITYMASTER C WHERE CM.BRANCH_CODE=? AND CM.COVER_ID=? AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (SELECT COMMODITY_ID FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=?)) ORDER BY CM.CLAUSES_ID";
					clauses	= runner.multipleSelection(Query,args);
					if(clauses.length > 0){
						for(int j=0;j<clauses.length;j++){
							clausesId=clauses[j][0]==null?"":clauses[j][0];
							description=clauses[j][1]==null?"":clauses[j][1];
							effectDate=clauses[j][2]==null?"":clauses[j][2];
							fullDetails.put("clausesId"+(j+1),clausesId);
							fullDetails.put("description"+(j+1),description);
							finalCount=finalCount+1;
						}
						fullDetails.put("finalCount",""+finalCount);
						cT.setProposalNo((String)session.getAttribute("proposalNo"));
						cT.setModeOfTransport("0");
						cT.setCoverId(covers);
						cT.setExtraCoverId("0");
						cT.setEffectDate(effectDate);
						cT.insertUpdateConditions(fullDetails);
					}
				}
				//INSERTING WAR CLAUSES FOR GUEST USER
				String values[][] = new String[0][0];
				StringBuffer totalCover = new StringBuffer();
				Query ="SELECT DISTINCT(COVER_ID) FROM OPEN_COVER_CLAUSES WHERE PROPOSAL_NO='"+(String)session.getAttribute("proposalNo")+"'";
				values = runner.multipleSelection(Query);
				for (int j = 0; j < values.length; j++) {
					totalCover.append(values[j][0] + ",");
				}
				if (totalCover.length() > 0) {
					totalCover.deleteCharAt(totalCover.length() - 1);
				}
				Query = "SELECT COUNT(*) FROM OPEN_COVER_CLAUSES WHERE PROPOSAL_NO='"+(String)session.getAttribute("proposalNo")+"' AND EXTRA_COVER_ID IN (1) AND COVER_ID=" + covers +"";
				result = runner.singleSelection(Query);
				if("GUEST".equalsIgnoreCase(request.getParameter("user")) && result.equalsIgnoreCase("0")){
				finalCount=0;
				args = new String[1];
				args[0] = (String)session.getAttribute("adminBranch");
				Query="SELECT CLAUSES_ID,CLAUSES_DESCRIPTION,EXTRA_COVER_ID,COVER_ID FROM CLAUSES_MASTER WHERE STATUS IN ('Y','R') AND BRANCH_CODE = ? AND EXTRA_COVER_ID IN (1) AND COVER_ID IN ("+totalCover+") GROUP BY EXTRA_COVER_ID,CLAUSES_ID,CLAUSES_DESCRIPTION,COVER_ID ORDER BY CLAUSES_ID";
				clauses	= runner.multipleSelection(Query,args);
				if(clauses.length > 0){
					for(int j=0;j<clauses.length;j++){
						clausesId=clauses[j][0]==null?"":clauses[j][0];
						description=clauses[j][1]==null?"":clauses[j][1];
						coversId=clauses[j][3]==null?"":clauses[j][3];
						fullDetails.put("clausesId"+(j+1),clausesId);
						fullDetails.put("description"+(j+1),description);
						fullDetails.put("coverId"+(j+1),coversId);
						finalCount=finalCount+1;
					}
					fullDetails.put("finalCount",""+finalCount);
					cT.setProposalNo((String)session.getAttribute("proposalNo"));
					cT.setModeOfTransport("0");
					cT.setCoverId(covers);
					cT.setExtraCoverId("0");
					cT.setEffectDate(effectDate);
					cT.insertUpdateWSRCCText(fullDetails,(String)session.getAttribute("adminBranch"));
					}
				}
				}catch(Exception e){e.printStackTrace();}
				try
				{
					if(!"GUEST".equalsIgnoreCase(request.getParameter("user")) && (i<1&& (request.getParameter("clauses_"+covers)==null || Double.parseDouble(request.getParameter("clauses_"+covers))<=0)))
						error=error+"<br>*Please Select Clauses for "+(coverId.substring(coverId.indexOf('-')+1,coverId.length()));
				}catch(Exception e){}
				
				
				String ids[]= null;
				String coverTypeIds=request.getParameter("coverType"+covers);
				if(coverTypeIds!=null && coverTypeIds.length()>0 && !"null".equalsIgnoreCase(coverTypeIds))
				{
					ids= coverTypeIds.split(",");
					for (int j = 0; j < ids.length; j++) {
						try
						{
							totalValue[i][1]=totalValue[i][1]+","+covers+"-"+request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j])+"~"+ids[j];
					if(request.getParameter("rate_"+(i+1)+"_"+covers)!=null && request.getParameter("rate_"+(i+1)+"_"+covers).length()>=1)
							{
						if(Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j]))<0 || Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j]))>=100)
								{	count1++;count++;}
							}else														{if(Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j]))<0 || Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j]))>=100)
								count++;
							}

						}catch(Exception e)
						{
						if(request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j])!=null && request.getParameter("rate_"+(i+1)+"_"+covers+"_"+ids[j]).length()>=1)
							count1++;
							count++;
						}
					}
				}else{
						try
							{
							//if(i<1&& (request.getParameter("clauses_"+covers)==null || Double.parseDouble(request.getParameter("clauses_"+covers))<=0))
							//	error=error+"<br>*Please Select Clauses for "+(coverId.substring(coverId.indexOf('-')+1,coverId.length()));
							totalValue[i][1]=totalValue[i][1]+","+covers+"-"+request.getParameter("rate_"+(i+1)+"_"+covers);
						if(request.getParameter("rate_"+(i+1)+"_"+covers)!=null && request.getParameter("rate_"+(i+1)+"_"+covers).length()>=1)
								{
							if(Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers))<0 || Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers))>=100)
									{	count1++;count++;}
								}else														{if(Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers))<0 || Double.parseDouble(request.getParameter("rate_"+(i+1)+"_"+covers))>=100)
									count++;
								}

							}catch(Exception e)
							{
							if(request.getParameter("rate_"+(i+1)+"_"+covers)!=null && request.getParameter("rate_"+(i+1)+"_"+covers).length()>=1)
								count1++;
							
								count++;
							}
						}
						}
						//if(totalValue[i][1].indexOf("-")!=-1)
							totalValue[i][1]=totalValue[i][1].substring(1,totalValue[i][1].length());
						if(count>0)
							error=error+"<br>*"+runner.getErrormsg("352")+"  "+(i+1);
						else if(count1>0)
							error=error+"<br>*"+runner.getErrormsg("352")+"  "+(i+1);
			}
					out.println("<br>error- goods-------->"+error.length());
					//Error Finding In rate for Each Commodity

			try
			{
				String wsrc = "";
				wsrc = request.getParameter("wsrc")==null?"":request.getParameter("wsrc");
				out.println("<br>  RoyalTest----wsrc->"+wsrc);
				if(request.getParameter("wsrcc_clauses")==null || Double.parseDouble(request.getParameter("wsrcc_clauses"))<=0)
				{
					System.out.println("Please Select  WSRCC Clauses Try Block If Condition");
					//if(wsrc.equalsIgnoreCase("Y"))
					if(wsrc.equalsIgnoreCase("Y") && !"GUEST".equalsIgnoreCase(request.getParameter("user")))
						error=error+"<br>*"+runner.getErrormsg("353");
				}
			}
			catch(Exception e)
			{
				error=error+"<br>*"+runner.getErrormsg("353");
				System.out.println("   ERROR in  Clauses  "+e.toString());
				e.printStackTrace();
			}
				
				storedValue.add(0,totalValue);
				//Error Findout for Each Destination Country
			out.println("<br>   DEstination   Country---->"+request.getParameter("dest_countryId"));
		String[][] Nationality=(String[][])session.getAttribute("Nationality");
		String countryId=null;
		String contry_rate="";
		tokens=new java.util.StringTokenizer(request.getParameter("dest_countryId")==null?"":request.getParameter("dest_countryId"),",");
		while(tokens.hasMoreTokens())
		{countryId=tokens.nextToken();
		
	//	for(int i=0;i<Nationality.length;i++)
		//{
			//if(Nationality[i][0].equalsIgnoreCase(countryId))
		//	{
				try
				{
					contry_rate=contry_rate+"#"+(countryId+"~"+request.getParameter("dest_wsrcc_"+countryId));
					System.out.println("country_rate:"+contry_rate);
					if(request.getParameter("dest_wsrcc_"+countryId)!=null && request.getParameter("dest_wsrcc_"+countryId).length()>=1)
					{
					if(Double.parseDouble(request.getParameter("dest_wsrcc_"+countryId))>100)
						{
						error1=error1+"<br>*"+runner.getErrormsg("354")+getCountry(countryId,Nationality);	
						error=error+"<br>*"+runner.getErrormsg("354")+getCountry(countryId,Nationality);	
						}
					}
					else
					{
						if(Double.parseDouble(request.getParameter("dest_wsrcc_"+countryId))>100)
						error=error+"<br>*"+runner.getErrormsg("354")+getCountry(countryId,Nationality);	
					}
				}catch(Exception e){
					//if(request.getParameter("dest_wsrcc_"+countryId)!=null && request.getParameter("dest_wsrcc_"+countryId).length()>=1)
					error1=error1+"<br>*"+runner.getErrormsg("354")+getCountry(countryId,Nationality);	
					//else
					error=error+"<br>*"+runner.getErrormsg("354")+getCountry(countryId,Nationality);	
				}
			//}
		//}
		}
		if(contry_rate.length()>0)
			contry_rate=contry_rate.substring(1,contry_rate.length());
		    System.out.println("<br>contry_rate-------->"+contry_rate);
			out.println("<br>contry_rate-------->"+contry_rate);
			storedValue.add(1,contry_rate);
			out.println("<br>error- Country-------->"+error.length());
				//Error Findout for Each Destination Country
				// ERROR Identification for Share Percentage and Company name     
				int k=0;int l=0;int m=0;
				String[][] shareCompany=null;
				double tot = 0.0;  //marimuthu
				double rsa = 0.0;
				shareCompany=new String[Integer.parseInt(request.getParameter("shareCompany"))][2];
				rsa=Double.parseDouble(request.getParameter("rsaper")==null?"0":request.getParameter("rsaper"));
				//finaltotal=rsa;
				for(int i=0;i<Integer.parseInt(request.getParameter("shareCompany"));i++)
				{
					out.println("<br> company name---->"+request.getParameter("company_"+i));
					try
					{
					shareCompany[i][0]=""+Integer.parseInt(request.getParameter("company_"+i));
					shareCompany[i][1]=""+Double.parseDouble(request.getParameter("values_"+i));
					tot = tot+Double.parseDouble(request.getParameter("values_"+i));  //marimuthu
					}catch(Exception e)
					{
						shareCompany[i][0]="0";
						shareCompany[i][1]="0";
					}
					finaltotal =tot;
					for(int j=0;j<i;j++)
					{
						if(shareCompany[j][0].equalsIgnoreCase(shareCompany[i][0]))
						{
							if(m==0)
								error=error+"<br>*"+runner.getErrormsg("357");
							m++;
						}
					}
					if("select".equalsIgnoreCase(request.getParameter("company_"+i)))
					{
						k++;
						if(k==1)
						error=error+"<br>*"+runner.getErrormsg("358");
					}
					try{
						if(Double.parseDouble(request.getParameter("values_"+i))<=0 || Double.parseDouble(request.getParameter("values_"+i))>=100)
						{
							l++;
							if(l==1)
							{
							if(request.getParameter("values_"+i)!=null && request.getParameter("values_"+i).length()>=1)
								error1=error1+"<br>*"+runner.getErrormsg("355");
							else
							error=error+"<br>*"+runner.getErrormsg("355");
							}
						}
						}catch(Exception e)
						{
							l++;
							if(l==1)
							{
							if(request.getParameter("values_"+i)!=null && request.getParameter("values_"+i).length()>=1)
							error1=error1+"<br>*"+runner.getErrormsg("356");
							else
							error=error+"<br>*"+runner.getErrormsg("356");
							}
						}
				out.println("<br> %---->"+request.getParameter("values_"+i));
				}
				finaltotal=finaltotal+rsa;
				if(finaltotal > 100)
				{
				  	error=error+"<br>*"+runner.getErrormsg("145");
				}
				else if(finaltotal < 100)
				{
				  	error=error+"<br>*"+runner.getErrormsg("139");
				}
				storedValue.add(2,shareCompany);
				
				storedValue.add(3,new ArrayList());
				
				//Discount PERCENT
				if(error.length()==0)
				{
				count=0;count1=0;
				String[][] totalValue1=new String[Integer.parseInt(request.getParameter("totalCommodity"))][2];
				coverId=null;
				for(int i=0;i<Integer.parseInt(request.getParameter("totalCommodity"));i++)
				{
					totalValue1[i][0]=request.getParameter("commoditys_"+i);
					totalValue1[i][1]="";
					count=0;count1=0;
					tokens=new java.util.StringTokenizer(request.getParameter("coverName"),",");
					coverId="";
					while(tokens.hasMoreTokens())
							{covers=(String)tokens.nextToken();
							coverId=covers;
					covers=covers.substring(0,covers.indexOf('-'));
					
					String ids[]= null;
					String coverTypeIds=request.getParameter("coverType"+covers);
					if(coverTypeIds!=null && coverTypeIds.length()>0 && !"null".equalsIgnoreCase(coverTypeIds))
					{
						ids= coverTypeIds.split(",");
						for (int j = 0; j < ids.length; j++) {
							try
							{
								totalValue1[i][1]=totalValue1[i][1]+","+covers+"-"+request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j])+"~"+ids[j];
						if(request.getParameter("disPercent_"+(i+1)+"_"+covers)!=null && request.getParameter("disPercent_"+(i+1)+"_"+covers).length()>=1)
								{
							if(Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j]))<0 || Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j]))>100)
									{	count1++;count++;}
								}else														{if(Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j]))<0 || Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j]))>100)
									count++;
								}

							}catch(Exception e)
							{
							if(request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j])!=null && request.getParameter("disPercent_"+(i+1)+"_"+covers+"_"+ids[j]).length()>=1)
								count1++;
								count++;
							}
						}
					}else{
							try
								{
								totalValue1[i][1]=totalValue1[i][1]+","+covers+"-"+request.getParameter("disPercent_"+(i+1)+"_"+covers);
							if(request.getParameter("disPercent_"+(i+1)+"_"+covers)!=null && request.getParameter("disPercent_"+(i+1)+"_"+covers).length()>=1)
									{
								if(Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers))<0 || Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers))>100)
										{	count1++;count++;}
									}else														{if(Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers))<0 || Double.parseDouble(request.getParameter("disPercent_"+(i+1)+"_"+covers))>100)
										count++;
									}

								}catch(Exception e)
								{
								if(request.getParameter("disPercent_"+(i+1)+"_"+covers)!=null && request.getParameter("disPercent_"+(i+1)+"_"+covers).length()>=1)
									count1++;
								
									count++;
								}
							}
							}
							//if(totalValue1[i][1].indexOf("-")!=-1)
					totalValue1[i][1]=totalValue1[i][1].substring(1,totalValue1[i][1].length());
							if(count>0)
								error=error+"<br>*"+runner.getErrormsg("483")+"  "+(i+1);
							else if(count1>0)
								error=error+"<br>*"+runner.getErrormsg("483")+"  "+(i+1);
				}
				storedValue.add(4,totalValue1);
				}
				
				//Discount Rate
				if(error.length()==0)
				{
				count=0;count1=0;
				String[][] totalValue2=new String[Integer.parseInt(request.getParameter("totalCommodity"))][2];
				coverId=null;
				for(int i=0;i<Integer.parseInt(request.getParameter("totalCommodity"));i++)
				{
					totalValue2[i][0]=request.getParameter("commoditys_"+i);
					totalValue2[i][1]="";
					count=0;count1=0;
					String errorCovers="";
					tokens=new java.util.StringTokenizer(request.getParameter("coverName"),",");
					coverId="";
					while(tokens.hasMoreTokens())
							{covers=(String)tokens.nextToken();
							coverId=covers;
					covers=covers.substring(0,covers.indexOf('-'));
					
					String ids[]= null;
					String coverTypeIds=request.getParameter("coverType"+covers);
					if(coverTypeIds!=null && coverTypeIds.length()>0 && !"null".equalsIgnoreCase(coverTypeIds))
					{
						ids= coverTypeIds.split(",");
						for (int j = 0; j < ids.length; j++) {
							try{
								totalValue2[i][1]=totalValue2[i][1]+","+covers+"-"+request.getParameter("disrate_"+(i+1)+"_"+covers+"_"+ids[j])+"~"+ids[j];
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}else{
						try{
							totalValue2[i][1]=totalValue2[i][1]+","+covers+"-"+request.getParameter("disrate_"+(i+1)+"_"+covers);
							}catch(Exception e){
							e.printStackTrace();
							}
						}
						String disrate=request.getParameter("disrate_"+(i+1)+"_"+covers)==null?"0":request.getParameter("disrate_"+(i+1)+"_"+covers);
						String value=request.getParameter("disPercent_"+(i+1)+"_"+covers)==null?"0":request.getParameter("disPercent_"+(i+1)+"_"+covers);
						/*if(Double.parseDouble(disrate)==0 && Double.parseDouble(value)==0){
							errorCovers=errorCovers+","+(coverId.substring(coverId.indexOf('-')+1,coverId.length()));
						}*/
					}
					if(errorCovers.replaceFirst(",", "").length()>2){
						error=error+"<br/>* Please Enter Policy Excess Percentage or Value for "+errorCovers.replaceFirst(",", "")+" in row number "+(i+1);
					}
					totalValue2[i][1]=totalValue2[i][1].substring(1,totalValue2[i][1].length());
				}
				storedValue.add(5,totalValue2);
				}
				
				
				out.println("<br>error- Percentage-------->"+error.length());
				out.println("<br>     ERRROR--->"+error);
				out.println(" <br>  END  	rateModification   ");
				
				//block for endtType Validation
				String endtTypeId="";
				if("Y".equals(request.getParameter("endtYN"))){
					String[] endtType=request.getParameterValues("endtType");
					if(endtType==null || endtType.length<=0){
						error=error+"<br>* "+runner.getErrormsg("478");
					}else{
						endtTypeId=StringUtils.join(endtType, ",");
					}
				}
				//End
				
				if(!"showOpencoverSummary.jsp".equalsIgnoreCase(request.getParameter("actionPath")))
				{
					error="";
				if(error1.length()>=1)
					error=error1;
				}
				if(error.length()>1)
				{
					out.println("<br>     ERRROR--->"+error);

				out.println(" <br>  END  	rateModification   ");
				request.setAttribute("errorDetail",error);
			rd = request.getRequestDispatcher("Premium.jsp");
			}
			else
			{
				out.println("<br>  NOT ERROR  ");
				com.maan.opencover.bean.rateModification rateValue=new com.maan.opencover.bean.rateModification();

				rateValue.setEffectiveDay(request.getParameter("effectiveDay"));
				rateValue.setEffectiveMonth(request.getParameter("effectiveMonth"));
				rateValue.setEffectiveYear(request.getParameter("effectiveYear"));
                rateValue.setRangeValues(finalvalues);
				rateValue.setProposalNo((String)session.getAttribute("proposalNo"));
				
			
				System.out.println("Modified code enters for Deducts");
				rateValue.storedRates(storedValue,(String)session.getAttribute("openCoverNo"));
				
				if("Y".equalsIgnoreCase(request.getParameter("endtYN"))){
					openCoverQuotation.updateEndtType((String)session.getAttribute("proposalNo"), endtTypeId);
				}
				//response.sendRedirect(request.getParameter("actionPath"));
				if(request.getParameter("guestRateYN")!=null){
					openCoverQuotation.updateConfirmStatus((String)session.getAttribute("proposalNo"),(String)request.getParameter("guestRateYN"));
				}
				if(request.getParameter("proposalStatus")!=null){
					openCoverQuotation.updateApprovedStatus((String)session.getAttribute("proposalNo"),(String)request.getParameter("proposalStatus"));
				}
				String message="";
				if("GUEST".equalsIgnoreCase(request.getParameter("user"))){
					String mailStatus=request.getParameter("guestRateYN");
					if("YES".equalsIgnoreCase(mailStatus)){
						message="CONFIRMED BY GUEST USER in HEAD OFFICE";
						sendMail(message);
					}else{
						message="GUEST USER in HEAD OFFICE";
						sendMail(message);
					}
					response.sendRedirect("showOpencover.jsp?referral="+mailStatus);
				}else{
					response.sendRedirect(request.getParameter("actionPath"));
				}
				if(true)
					return;
				//rd = request.getRequestDispatcher(request.getParameter("actionPath"));

			}
			
		}
		/*        RATE MODIFICATION SCREEN     */

			//Edit Page Setting
		else if("numberGenerate".equalsIgnoreCase(path))
		{
			com.maan.opencover.bean.opencoverSummary summary=new com.maan.opencover.bean.opencoverSummary();
			String deposit="", depositType="", installType="", debitNoteName="", debitNoteNo="", creditNoteNo="", issuanceFee="",ratesYN="",cacelClaus="",conveyanceCount="",transportId="",conveyance="",remarks="",amendedClauseYN="",depositDate="",renewalYN="", status="", marinePremium="", warPremium="",endtYN="";
			Map<String, String> map=new HashMap<String, String>();
			String coverno=null;
			String missippiCode = (String)session.getAttribute("missippiCode");
			String adminBranch = (String)session.getAttribute("adminBranch");
			String opencoverNo = (String)session.getAttribute("openCoverNo")==null?"":(String)session.getAttribute("openCoverNo");
			deposit=request.getParameter("deposit")==null?"":request.getParameter("deposit");
			depositType=request.getParameter("depositType")==null?"":request.getParameter("depositType");
			installType=request.getParameter("installType")==null?"":request.getParameter("installType");
			marinePremium=request.getParameter("marinePremium")==null?"0":request.getParameter("marinePremium");
			warPremium=request.getParameter("warPremium")==null?"0":request.getParameter("warPremium");
			debitNoteName=request.getParameter("debitNoteName")==null?"":request.getParameter("debitNoteName");
			issuanceFee=request.getParameter("issuanceFee")==null?"":request.getParameter("issuanceFee");
			ratesYN=request.getParameter("ratesYN")==null?"":request.getParameter("ratesYN");
			cacelClaus=request.getParameter("cancelClaus")==null?"":request.getParameter("cancelClaus");
			amendedClauseYN=request.getParameter("amendedClauseYN")==null?"":request.getParameter("amendedClauseYN");
			conveyanceCount=request.getParameter("conveyanceCount")==null?"0":request.getParameter("conveyanceCount");
			remarks=request.getParameter("remarks")==null?"0":request.getParameter("remarks");
			depositDate=request.getParameter("depositDate")==null?"":request.getParameter("depositDate");
			debitNoteNo=request.getParameter("debitNoteNo")==null?"":request.getParameter("debitNoteNo");
			creditNoteNo=request.getParameter("creditNoteNo")==null?"":request.getParameter("creditNoteNo");
			renewalYN=request.getParameter("renewalYN")==null?"":request.getParameter("renewalYN");
			endtYN=request.getParameter("endtYN")==null?"":request.getParameter("endtYN");
			for(int i=0;i<Integer.parseInt(conveyanceCount);i++)
			{
				transportId=request.getParameter("transportId"+i)==null?"":request.getParameter("transportId"+i);
				conveyance=request.getParameter("conveyance"+i)==null?"":request.getParameter("conveyance"+i);
				summary.updateConveyance((String)session.getAttribute("proposalNo"), transportId, conveyance);
			}
			if("Y".equalsIgnoreCase(request.getParameter("opencover")))
			{
				coverno = summary.openCoverNumber((String)session.getAttribute("proposalNo"),adminBranch,ratesYN,cacelClaus,amendedClauseYN,(String)session.getAttribute("userLoginMode"),renewalYN,endtYN);
			} 
			else
			{
				coverno = opencoverNo;
				summary.updaterateYN((String)session.getAttribute("proposalNo"), ratesYN, cacelClaus,amendedClauseYN);
			}
			if(coverno != null && coverno.length()>0)
			{
				summary.updateDepositInfo((String)session.getAttribute("proposalNo"), adminBranch, deposit, depositType, installType, debitNoteName, issuanceFee,remarks, depositDate, debitNoteNo, creditNoteNo, marinePremium, warPremium);
				String noteNo[][]=summary.getNoteNo((String)session.getAttribute("proposalNo"));
				if(noteNo!=null & noteNo.length>0 && "Y".equalsIgnoreCase(deposit))
				{
					debitNoteNo=noteNo[0][0]==null?"":"&debitNoteNo="+noteNo[0][0];
					creditNoteNo=noteNo[0][1]==null?"":"&creditNoteNo="+noteNo[0][1];
				}else{
					debitNoteNo="";
					creditNoteNo="";
				}
			}if("Y".equalsIgnoreCase(request.getParameter("opencover")) && missippiCode.length()<=0)
			{
				map=summary.openCoverPolicyIntegration(coverno);
				if(map!=null && !map.isEmpty()){
					error=map.get("ERROR")==null?"":(String)map.get("ERROR");
					status=map.get("STATUS")==null?"":(String)map.get("STATUS");
					debitNoteNo=map.get("DN")==null?"":"&debitNoteNo="+map.get("DN");
					creditNoteNo=map.get("CN")==null?"":"&creditNoteNo="+map.get("CN");
				}
			}
			else if("Y".equalsIgnoreCase(request.getParameter("opencover")) && missippiCode.length()>0){
				opencoverNo=missippiCode;
			}
			String guestStatus="",proposalStatus="",message="";
			String[][] data = runner.multipleSelection("SELECT DECODE(GUEST_LOGIN_STATUS, NULL, 'NO',GUEST_LOGIN_STATUS),PROPOSAL_STATUS FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO='"+(String)session.getAttribute("proposalNo")+"' and AMEND_ID = (select max(amend_id) from OPEN_COVER_MASTER where Proposal_no = '"+(String)session.getAttribute("proposalNo")+"')");
			if(data!=null && data.length>0){
				guestStatus=data[0][0]==null?"":data[0][0];
				proposalStatus=data[0][1]==null?"":data[0][1];
			}
			if(opencoverNo.length()>0 || (error.length()<=0 && "Y".equalsIgnoreCase(status.trim()))){
				if("GUEST".equalsIgnoreCase(guestStatus)){
					message="POLICY GENERATED BY UW in HEAD OFFICE";
					sendMail(message);
				}
				response.sendRedirect("showOpencover.jsp?coverno="+coverno+debitNoteNo+creditNoteNo);
			}else{
				if("GUEST".equalsIgnoreCase(guestStatus) && "Approved".equalsIgnoreCase(proposalStatus)){
					message="Referral Approved BY UW in HEAD OFFICE";
					// sendMail(message);
				}else if("GUEST".equalsIgnoreCase(guestStatus)){
					message="Proposal Modified BY UW in HEAD OFFICE";
					// sendMail(message);
				}
				response.sendRedirect("showOpencover.jsp?error="+error+"&renewalYN="+renewalYN);
			}
			return;
		}else if("GenerateDocument".equalsIgnoreCase(path))
		{
			try 
			{
				String mode=request.getContextPath().indexOf("Test")!=-1?"Test":"Live";
				String proposalNo=request.getParameter("proposalNo")==null?"":request.getParameter("proposalNo");
				String openCoverNo=request.getParameter("policynumber")==null?"":request.getParameter("policynumber");
				String docType=request.getParameter("docType")==null?"":request.getParameter("docType");
				String endtstatus=request.getParameter("endtstatus")==null?"":request.getParameter("endtstatus");
				String docNo=request.getParameter("docNo")==null?"":request.getParameter("docNo");
				String amendId=request.getParameter("amendId")==null?"":request.getParameter("amendId");
				String loginId=request.getParameter("loginId")==null?"":request.getParameter("loginId");
				String baseFilePath="/"+(mode.equalsIgnoreCase("Test")?"testpolicy":docType)+"pdf/"+docNo.replaceAll("/", "-")+"OpenCover"+docType+".pdf";
				String filePath=getServletContext().getRealPath(baseFilePath);
				List<HashMap<String,Object>> list=opencoverSummary.getOpenCoverPolicyInfo(openCoverNo, loginId, amendId);
				OpenCoverDocGenerator docGen=new OpenCoverDocGenerator();
				String fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
				String imageURL=request.getSession().getServletContext().getRealPath("/"+ "/images/");
				if("debit".equalsIgnoreCase(docType))
				{
					docGen.writeDebitPDF(fontPath, openCoverNo, filePath, imageURL, mode, list);
					
					session.setAttribute("pdfFilePath", baseFilePath);
					response.sendRedirect(request.getContextPath()+"/pdfReport.action");
				}else if("credit".equalsIgnoreCase(docType))
				{
					docGen.writeCreditPDF(fontPath, openCoverNo, filePath, imageURL, mode, list);
					
					session.setAttribute("pdfFilePath", baseFilePath);
					response.sendRedirect(request.getContextPath()+"/pdfReport.action");
				}else if("schedule".equalsIgnoreCase(docType))
				{
					/*
					 * Removed for Jasper Reports
					 * 
					 * ScheduleBean bean=new ScheduleBean();
					//bean.getPolicyInfoByProposalNo((String)session.getAttribute("AdminBranchCode"), proposalNo, imageURL, endtstatus);
					bean.getPolicyInfoByProposalNo((String)session.getAttribute("BelongingBranch"), proposalNo, imageURL, endtstatus);
					bean.setFontPath(fontPath);
					bean.setFilePath(filePath);
					bean.setOpenCoverNo(openCoverNo);
					if("Test".equalsIgnoreCase(mode))
						bean.setDisplayText("TEST POLICY");
					bean.createSchedulePDF();*/
					
					com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
					jr.opencoverSchedule(proposalNo, openCoverNo, endtstatus, (String)session.getAttribute("BelongingBranch"), filePath);
					
					session.setAttribute("pdfFilePath", baseFilePath);
					response.sendRedirect(request.getContextPath()+"/pdfReport.action");
					
				}
				else if("clauses".equalsIgnoreCase(docType)) {
					baseFilePath = "/print4Clauses.pdfClauses?proposalNo="+proposalNo+"&loginid="+loginId;
					response.sendRedirect(request.getContextPath()+baseFilePath);
				}
				//response.sendRedirect(request.getContextPath()+baseFilePath);
				return;
			} catch (BaseException e) {
				logger.debug(e);
			}
		}else if("Renewal".equalsIgnoreCase(path))
		{
			String openCoverNo=request.getParameter("openNo")==null?"":request.getParameter("openNo");
			String proposalNo=request.getParameter("proposalno")==null?"":request.getParameter("proposalno");
			String newProposalNo=new opencoverSummary().openCoverRenewal(proposalNo, "Renewal");
			response.sendRedirect("renewalSuccess.jsp?openCoverNo="+openCoverNo+"&proposalNo="+newProposalNo);
			return;
		}else if("Endorsement".equalsIgnoreCase(path))
		{
			String openCoverNo=request.getParameter("openNo")==null?"":request.getParameter("openNo");
			String proposalNo=request.getParameter("proposalno")==null?"":request.getParameter("proposalno");
			String newProposalNo=new opencoverSummary().openCoverRenewal(proposalNo, "Endt");
			response.sendRedirect("newOpenCover.jsp?proposalno="+newProposalNo+"&endtYN=Y");
			return;
		}else if ("ExtendOpenCover".equals(path)) {
			String proposalNo=request.getParameter("proposalno")==null?"":request.getParameter("proposalno");
			String openCoverEndDate=request.getParameter("openCoverEndDate")==null?"":request.getParameter("openCoverEndDate");
			boolean isaved=new opencoverSummary().updateExtend(proposalNo,openCoverEndDate);
			response.sendRedirect("showApprovedCover.jsp");
			return ;
		}else if ("UpdateEstimateOpenCover".equals(path)) {
			String proposalNo=request.getParameter("proposalno")==null?"":request.getParameter("proposalno");
			String estimateAmount=request.getParameter("openCoverEndDate")==null?"":request.getParameter("openCoverEndDate").replaceAll(",", "");
			boolean isaved=new opencoverSummary().updateAnnualEstimated(proposalNo,estimateAmount);
			response.sendRedirect("showApprovedCover.jsp");
			return ;
		}
		
		//Edit Page Setting
	if(rd==null)
		{
		//return;
		}
		rd.forward(request, response);

	}

	public String getCustomer(String appId)
	{
		String sql = ""; 
		String res = "";
		String args[] = new String[1];
		try
		{
			args[0] = appId;
			sql = "select customer_id from position_master where application_no=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String checkDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);		
		//String strDate = " 12/13/2003";		
		java.util.Date date = df.parse(strDate, pos);		
                // Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			System.out.println("Error: " + pos.getIndex());
		
			if (date == null) {
				System.out.println("Date is null");
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				//System.out.println("Error index: " + pos.getErrorIndex());
				return "Invalid";
			}	
				return "Invalid";
		}
		return returnVal;
	}
						//Validation for Date

	public String getCountry(String id,String[][] country)
	{
		String name="Invalid";
		try
		{
			for(int i=0;i<country.length;i++)
			{
				if(country[i][0].equalsIgnoreCase(id))
					name=country[i][1];
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return name;
	}

	public String getErrors(String error)
	{
		String desc="";
		String sql = "";
		try
		{
			String[][] names = new String[0][0];
			sql = "select error_desc from error_master where error_id in ("+error+") order by error_id";
			names = runner.multipleSelection(sql);
	
			for(int i=0;i<names.length;i++)
				desc=desc+"<br>*"+names[i][0];
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return desc;
	}
	public boolean checkingSysDates(String Days,String months,String years)
	{
		String result = "0"; 
		String sql = ""; 
		try
		{
			Date date=new Date();
			String stdate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			sql = "select to_date(sysdate,'DD-MM-YYYY')-to_date('"+stdate+"','DD-MM-YYYY') from dual";
			result = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(Double.parseDouble(result) > 0)
			return true;
		else
			return false;
	}

	public boolean checkingDates(String Days,String months,String years,String Days1,String months1,String years1)
	{
		String result = ""; 
		String sql = "";
		try
		{
			Date date=new Date();
			String stdate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			sql = "select to_date('"+stdate+"','DD-MM-YYYY')-to_date('"+stdate+"','DD-MM-YYYY') from dual";
			result = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(Double.parseDouble(result) > 0)
			return true;
		else
			return false;
	}
	
	public boolean mocOpenPolicyId(String value)
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890-";
				for(int i=0;i<value.length();i++)
				{
					if(validChar.indexOf(value.charAt(i))== -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  "+ flag);
		return flag;
	}
	public boolean getReferralStatus(String commodity, String branch)
	{
		String result = ""; 
		String sql = "";
		try
		{
			sql = "SELECT COUNT(*) FROM COMMODITYMASTER WHERE STATUS='Y' AND COMMODITY_ID='"+commodity+"' AND BRANCH_CODE="+branch+"";
			result = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(Double.parseDouble(result) > 0)
			return true;
		else
			return false;
	}
	public void sendMail(String message){
		final MailInformation mailBean = new MailInformation();
		String[][] branchInfo = runner.multipleSelection("SELECT DISTINCT BRANCH_CODE, EMAIL_TO, EMAIL_CC FROM  MAIL_DETAILS WHERE BRANCH_CODE="+(String)session.getAttribute("adminBranch")+"");
		List<LinkedHashMap<String,Object>> openCovers=null;
		String emailSubjectTxt="", emailMsgTxt="";
		String path1=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String basePath=path1.substring(1, path1.indexOf("WEB-INF"));
		final String basePaths = basePath+"MailServerInfo/MailServerInfo.xml"; 
		String testMsg=basePath.indexOf("test")!=-1?" - FROM TEST ENVIRONMENT":"";
		if(branchInfo!=null && branchInfo.length>0){
			for (int k = 0; k < branchInfo.length; k++) {
				openCovers=getProposalInfo((String)session.getAttribute("proposalNo"),(String)session.getAttribute("adminBranch"));
				if(openCovers!=null && !openCovers.isEmpty()){
					emailSubjectTxt = "Proposal No"+(String)session.getAttribute("proposalNo")+"- "+message;
					emailMsgTxt = mailBean.getOpenCoverReferralMsgTxt(openCovers, emailSubjectTxt);
					final String[] emailList = branchInfo[k][1].split(",");
					final String cc1Address[] = branchInfo[k][2].split(",");
					final mailController smtpMailSender = new mailController();
					try {
						smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt+testMsg, emailMsgTxt.toString(), "",basePaths,branchInfo[k][0]);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}
			}
		} 
	}
	public List<LinkedHashMap<String,Object>> getProposalInfo(String proposalNo,String branch)
	{
		List<LinkedHashMap<String,Object>> result = null;
		String sql = "";
		try
		{
			sql="SELECT ROWNUM SNO,X.* FROM (SELECT NVL (A.OPEN_COVER_NO, '0') OPEN_COVER_NO, A.PROPOSAL_NO PROPOSAL_NO, E.COMPANY_NAME BROKER, NVL (C.FIRST_NAME, C.COMPANY_NAME) CUSTOMER, NVL (TO_CHAR (A.INCEPTION_DATE, 'DD-MON-YYYY'), ' ') INCEPTION_DATE, NVL (TO_CHAR (A.EXPIRY_DATE, 'DD-MON-YYYY'), ' ') EXPIRY_DATE FROM OPEN_COVER_POSITION_MASTER A, PERSONAL_INFO C, OPEN_COVER_MASTER D, BROKER_COMPANY_MASTER E WHERE A.PROPOSAL_NO =? AND A.PROPOSAL_NO = D.PROPOSAL_NO AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = D.PROPOSAL_NO) AND C.CUSTOMER_ID = D.CUSTOMER_ID AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND E.AGENCY_CODE = (SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID = D.BROKER_ID) AND D.BRANCH_CODE="+branch+" ORDER BY EXPIRY_DATE) X";
			result = (List<LinkedHashMap<String,Object>>)runner.getOrderedResultList(sql, new String[]{proposalNo});
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
} // Class