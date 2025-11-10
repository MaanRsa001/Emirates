package com.maan.common.Customer;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.DBCon.DBConnectionStatus;
//import com.maan.Travel.DAO.TravelBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.ValidationFormat;
import com.maan.services.util.runner;

public class MasterController extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(MasterController.class);
	private static final long serialVersionUID = 1L;

	 final static transient private String TRAVELPID = "TravelProductId";
	 final static transient private String CUSID = "customer_id";
	 final static transient private String CHECKING = "checking";
	 final static transient private String OPTION = "Option";
	 final static transient private String 	QUOTENUMBER =   "QuoteNo";
	 final static transient private String  USER = "user";
	 final static transient private String  CLAUSES = "clauses";
	 final static transient private String QUOTENOS = "quoteno";
	 final static transient private String BRANCH = "branch";
	 final static transient private String PROID = "proId";
	public void doPost(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException{
			try{
				processResult(request, response);
			} catch(BaseException e) {
				logger.debug(e);
			}
	}

	public void doGet(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void processResult(final HttpServletRequest request,final  HttpServletResponse response)throws ServletException, IOException,BaseException
	{
		try{
	    response.setContentType("text/html");
   		HttpSession session= request.getSession(true);
        String productId = (String)session.getAttribute("product_id");
        String pathh = request.getContextPath();

		if(session.getAttribute("ses")==null){
			response.sendRedirect(pathh+"/login/error_messg.jsp");
		}

		//String pids = (String)session.getAttribute(TRAVELPID);
		String pids = productId;
        String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		DBConnectionStatus.statusStatic=usrModeSC;
		String path=request.getRequestURI();
        String back = request.getParameter("back");
        //String cusid  = (String)session.getAttribute(CUSID);

        if(path.indexOf('.') == -1){
           path = path.substring(path.lastIndexOf('/')+1,path.length());
        }
        else{
        	path=path.substring(path.lastIndexOf('/')+1,path.lastIndexOf('.'));
        }

		com.maan.Home.DataManipualtion.DataSelect dataSelect	=	new com.maan.Home.DataManipualtion.DataSelect();
        String branch = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid = "";
		if(!brokerDetails.isEmpty()){
			cid = (String)brokerDetails.get("Orgination");
		}
        if("CustomerMasterController".equalsIgnoreCase(path))
		{
            HomeCustomerBean ccBean = new HomeCustomerBean();
		    String cusId = isNull(request.getParameter("cusId"));
		    logger.info("Messages royal tested from travel customer controller..cusId is.."+cusId);
		    if("0".equalsIgnoreCase(cusId))
			{
			    ccBean.setTitle(isNull(request.getParameter("title")));
			    ccBean.setFirstName(isNull(request.getParameter("firstName")));
			    ccBean.setNationality(isNull(request.getParameter("nationality")));
           		ccBean.setDobDay(isNull(request.getParameter("dobDay")));
			    ccBean.setDobMonth(isNull(request.getParameter("dobMonth")));
			    ccBean.setDobYear(isNull(request.getParameter("dobYear")));
			    ccBean.setTelephone(isNull(request.getParameter("telephone")));
			    ccBean.setMobile(isNull(request.getParameter("mobile")));
			    ccBean.setFax(isNull(request.getParameter("fax")));
			    ccBean.setEmail(isNull(request.getParameter("email")));
			    ccBean.setAddress1(isNull(request.getParameter("address1")));
			    ccBean.setOccupation(isNull(request.getParameter("occupation")));
				ccBean.setOtherSource(isNull(request.getParameter("otherSource")));
			    ccBean.setSource(isNull(request.getParameter("source")));
			    ccBean.setEmirate(isNull(request.getParameter("emirate")));
			    ccBean.setCountry(isNull(request.getParameter("country")));
			    ccBean.setPoBox(isNull(request.getParameter("poBox")));
			    ccBean.setOrgName(isNull(request.getParameter("orgName")));
			}
		   else if(!"0".equalsIgnoreCase(cusId) && cusId.length()>1){
				ccBean.settingValuesForCustomer(cusId);
		   }
		    
		    
		    
		   StringBuffer ErrorMsg	=	new StringBuffer(1000);
	       String CheckCustomerWay	=	isNull(request.getParameter("CheckCustomerWay"));
	       ccBean.setCid(cid);
		    if("SearchOnly".equalsIgnoreCase(CheckCustomerWay)){
				ErrorMsg	=	 ccBean.validateFieldsSearch();
		    }
			else{
				ErrorMsg	=	 ccBean.validateFields("Travel");
			}
			RequestDispatcher reqDis = null;

            if(ErrorMsg.length()>1)
			{
				request.setAttribute("ErrorMsg",ErrorMsg);
                reqDis = request.getRequestDispatcher("/CustomerInfo/CustomerInfo.jsp");
				request.setAttribute("qid",(String)session.getAttribute("TravelQuoteNumber"));
				request.setAttribute("cusid",(String)session.getAttribute(CUSID));
				if("EditSaveCus".equalsIgnoreCase(CheckCustomerWay)){
					 reqDis = request.getRequestDispatcher("/CustomerInfo/CustomerInfo.jsp");
					 request.setAttribute("errorDetail",ErrorMsg);
				}
                if(reqDis!=null){
					reqDis.forward(request,response);
                }
			}
			else{
				if("SearchOnly".equalsIgnoreCase(CheckCustomerWay)){

					String  returnval[][]=new String[0][0];
					HomeCustomerBean homeBean = new HomeCustomerBean();
					 homeBean.setFirstName(isNull(request.getParameter("firstName")));
        			 homeBean.setLastName(isNull(request.getParameter("lastName")));
		        	 homeBean.setDobDay(isNull(request.getParameter("dobDay")));
			         homeBean.setDobMonth(isNull(request.getParameter("dobMonth")));
			         homeBean.setDobYear(isNull(request.getParameter("dobYear")));
			         homeBean.setTelephone(isNull(request.getParameter("telephone")));
			         homeBean.setMobile(isNull(request.getParameter("mobile")));
			         homeBean.setEmail(isNull(request.getParameter("email")));
			         homeBean.setOrgName(isNull(request.getParameter("orgName")));
					String login = "";
					if(session.getAttribute(USER)!=null){
						login = (String)session.getAttribute(USER);
					}
					returnval=(String[][]) homeBean.searchValues(login);
					request.setAttribute("Search_Result","SearchResult");
					request.setAttribute("result",returnval);
					reqDis = request.getRequestDispatcher("/CustomerInfo/CustomerInfo.jsp");
				}
				else{
						//String referal=null;
						String customers = isNull(request.getParameter("customer"));
						String cusIDs = isNull((String)session.getAttribute(CUSID));
						if(customers.length()>0){
							cusIDs = customers;
						}
						String cusVal=ccBean.storedValues(cusIDs,(String)session.getAttribute(USER),branch,pids);
						session.setAttribute(CUSID,isNull(cusVal));

						if("SaveAndContinue".equalsIgnoreCase(CheckCustomerWay)){
							if(back == null || back.length() == 0){
								   request.setAttribute("customerIDD",cusVal);
                                   request.setAttribute(CHECKING,CHECKING);
                                   reqDis = request.getRequestDispatcher("/TravelInsurance/TravelInformation.jsp");
							}
							else{
									request.setAttribute("customerIDD",cusVal);
									request.setAttribute(CHECKING,CHECKING);
									reqDis = request.getRequestDispatcher("../servlet/BackTrace");
							}
						}
						else if("SaveAndExit".equalsIgnoreCase(CheckCustomerWay)){
							ErrorMsg.append("Customer Added Successfully");
							request.setAttribute("ErrorMsg",ErrorMsg);
							request.setAttribute("FromSave","FromSave");
							request.setAttribute("back","toquote"); //oct28
							reqDis = request.getRequestDispatcher("/CustomerInfo/CustomerInfo.jsp");
						}
						else if("SaveOnly".equalsIgnoreCase(CheckCustomerWay)){
							reqDis = request.getRequestDispatcher("/HomeInsurance/generalMsg.jsp");
						}
						else if("EditSaveCus".equalsIgnoreCase(CheckCustomerWay)){

							if("".equalsIgnoreCase(request.getParameter("quoteNos"))){
								reqDis = request.getRequestDispatcher("/HomeInsurance/ExistingCustomers_B2B.jsp");
							}
							else{
								reqDis = request.getRequestDispatcher("/HomeInsurance/final.jsp");
							}
						}
				}
				if(reqDis != null){
					reqDis.forward(request,response);
				}
			}
		}
		if("PdfSummary_Schedule".equalsIgnoreCase(path))
		{
				//com.maan.common.Customer.DataSelect dataSelection;
				//dataSelection = new com.maan.common.Customer.DataSelect();
				//com.maan.Home.DataManipualtion.DataSelect data;
				//data = new com.maan.Home.DataManipualtion.DataSelect();
				String quoteNo ="";
				String loginId = "";

				/*if(session.getAttribute(QUOTENUMBER) != null)
				{
					quoteNo = (String)session.getAttribute(QUOTENUMBER);
					session.setAttribute(QUOTENUMBER,quoteNo);
				}*/
				if(request.getParameter("quoteNo") != null)
				{
					quoteNo = (String)request.getParameter("quoteNo");
					session.setAttribute(QUOTENUMBER,quoteNo);
				}

				//String hour;hour =  data.getSysdateTime(quoteNo,QUOTENUMBER);
				/*String clauses = isNull(request.getParameter(CLAUSES));
				String modeOfPurchase = isNull(request.getParameter("modeOfPurchase"),"D");
				String memberNo = isNull(request.getParameter("memberNo"));
				String motor = isNull(request.getParameter("motor"));
				String loyalty = isNull(request.getParameter("Loyalty"));
				if(motor.length()>0){
					motor = motor.substring(0,(motor.length()-1));
				}
				if(loyalty.equals("0")){
					memberNo = "";
				}*/
				/*String payMode;payMode = dataSelection.gerModeofPurchase(modeOfPurchase);
				dataSelection.updateMemberShipDetails(quoteNo,memberNo,motor,clauses,payMode,loyalty);*/

				if(session.getAttribute(USER)!=null)
				{
					loginId = (String)session.getAttribute(USER);
					session.setAttribute(USER,loginId);
				}
				request.setAttribute(OPTION,"Schedule");
				//String travelProductId=productId;
				/*if(session.getAttribute(TRAVELPID)!=null){
					travelProductId = (String)session.getAttribute(TRAVELPID);
				}*/

				/*String addSign    = isNull(request.getParameter("addSign"));
				String addPremium = isNull(request.getParameter("addPremium"));
				boolean loadDisStatus =  dataSelect.getLoadDisStatus(loginId,travelProductId);
				if(loadDisStatus){
					new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean().getAdminReferralUpdation(quoteNo,addSign,addPremium,loginId,travelProductId,"Broker");
				}
				//new try combining like home
				final Map policyCollection;policyCollection = new HashMap();
				policyCollection.put(QUOTENOS, quoteNo);
				policyCollection.put(PROID, travelProductId);
				policyCollection.put(BRANCH, branch);
				policyCollection.put("paymentMode", payMode);
				dataSelect.updatePolicyDetails(policyCollection,hour,branch);*/
				//
				RequestDispatcher dispatcher=null;
				if("41".equalsIgnoreCase(session.getAttribute("product_id").toString()))
				{
					dispatcher=request.getRequestDispatcher("*.HealthPDFCreator");
				}else
				{
					dispatcher=request.getRequestDispatcher("*.TravelPDFCreator");
				}
				dispatcher.forward(request, response);
		}
		if("PdfSummary_Debit".equalsIgnoreCase(path))
		{
				com.maan.Home.DataManipualtion.DataSelect data;
				data = new com.maan.Home.DataManipualtion.DataSelect();
				String quoteNo ="";
				if(request.getParameter("quoteNo") != null)
				{
					quoteNo = (String)request.getParameter("quoteNo");
					session.setAttribute(QUOTENUMBER,quoteNo);
				}
				/*if(session.getAttribute(QUOTENUMBER) != null)
				{
					quoteNo = (String)session.getAttribute(QUOTENUMBER);
					session.setAttribute(QUOTENUMBER,quoteNo);
				}*/
				request.setAttribute(OPTION,"Debit");
				String travelProductId=productId;
			/*	if(session.getAttribute(TRAVELPID)!=null){
					travelProductId = (String)session.getAttribute(TRAVELPID);
				}*/
				//new try combining like home
				String hour;hour =  data.getSysdateTime(quoteNo,QUOTENUMBER);
				final Map policyCollection;policyCollection = new HashMap();
				policyCollection.put(QUOTENOS, quoteNo);
				policyCollection.put(PROID, travelProductId);
				policyCollection.put(BRANCH, branch);
				dataSelect.updateDebitDetails(policyCollection,hour,branch);
				//
				RequestDispatcher dispatcher=null;
				if("41".equalsIgnoreCase(session.getAttribute("product_id").toString()))
				{
					dispatcher=request.getRequestDispatcher("*.HealthPDFCreator");
				}else
				{
					dispatcher=request.getRequestDispatcher("*.TravelPDFCreator");
				}
				dispatcher.forward(request, response);
		}
		if("PdfSummary_Receipt".equalsIgnoreCase(path))
		{
				String quoteNo ="";
				/*if(session.getAttribute(QUOTENUMBER) != null)	{
				quoteNo = (String)session.getAttribute(QUOTENUMBER);
				session.setAttribute(QUOTENUMBER,quoteNo);
				}*/
				if(request.getParameter("quoteNo") != null)
				{
					quoteNo = (String)request.getParameter("quoteNo");
					session.setAttribute(QUOTENUMBER,quoteNo);
				}
				/*if(quoteNo ==null || quoteNo.length()<=0 )
				  { quoteNo=request.getParameter("quoteNo");}*/
				request.setAttribute(OPTION,"Receipt");
				String travelProductId=productId;
			/*	if(session.getAttribute(TRAVELPID)!=null){
					travelProductId = (String)session.getAttribute(TRAVELPID);
				}
				if(travelProductId.length()<=0)
					travelProductId="31";*/
				com.maan.Home.DataManipualtion.HomePdfDataSelect data;
				data = new com.maan.Home.DataManipualtion.HomePdfDataSelect();
				String receiptNo;
				receiptNo = data.getReceiptNoNew(quoteNo,travelProductId,branch,request.getParameter("modeOfPurchase"));
				
				RequestDispatcher dispatcher=null;
				if("41".equalsIgnoreCase(session.getAttribute("product_id").toString()))
				{
					dispatcher=request.getRequestDispatcher("*.HealthPDFCreator?receipt_no="+receiptNo+"&quoteNo="+quoteNo);
				}else
				{
					dispatcher=request.getRequestDispatcher("*.TravelPDFCreator?receipt_no="+receiptNo+"&quoteNo="+quoteNo);
				}
				dispatcher.forward(request, response);
		}
		if("PdfSummary_Draft".equalsIgnoreCase(path))
		{
				String quoteNo ="";
				/*if(session.getAttribute(QUOTENUMBER) != null)
				{
					quoteNo = (String)session.getAttribute(QUOTENUMBER);
					session.setAttribute(QUOTENUMBER,quoteNo);
				}*/
				if(request.getParameter("quoteNo") != null)
				{
					quoteNo = (String)request.getParameter("quoteNo");
					session.setAttribute(QUOTENUMBER,quoteNo);
				}
				String clauses = isNull(request.getParameter(CLAUSES));
				final String args[] = {clauses,quoteNo};
				runner.multipleUpdation("update home_position_master set SUMMARY_CLAUSES=? where QUOTE_NO=?",args);
				request.setAttribute(OPTION,"Draft");
				RequestDispatcher dispatcher=null;
				if("41".equalsIgnoreCase(session.getAttribute("product_id").toString()))
				{
					dispatcher=request.getRequestDispatcher("*.HealthPDFCreator");
				}else
				{
					dispatcher=request.getRequestDispatcher("*.TravelPDFCreator");
				}
				dispatcher.forward(request, response);
		}
		if("PdfSummary_PolicyWording".equalsIgnoreCase(path))
		{
			    request.setAttribute(OPTION,"PolicyWording");
				RequestDispatcher dispatcher=request.getRequestDispatcher("*.TravelPDFCreator");
				dispatcher.forward(request, response);
		}

        if("PdfSummary_PrintQuote".equalsIgnoreCase(path))
		{
                logger.info("Customer Master Controller...PdfSummary_PrintQuote...");
				String quoteNo ="";

				if(session.getAttribute(QUOTENUMBER) != null)
				{
					quoteNo = (String)session.getAttribute(QUOTENUMBER);
					session.setAttribute(QUOTENUMBER,quoteNo);
				}
				//String clauses = isNull(request.getParameter(CLAUSES));
				String loginId = "";
				if(session.getAttribute(USER)!=null){
					loginId = (String)session.getAttribute(USER);
					session.setAttribute(USER,loginId);
				}
				String travelProductId="";
				if(session.getAttribute(TRAVELPID)!=null){
					travelProductId = (String)session.getAttribute(TRAVELPID);
				}
				String addSign = isNull(request.getParameter("addSign"));
				String addPremium = isNull(request.getParameter("addPremium"));
				boolean loadDisStatus = dataSelect.getLoadDisStatus(loginId,travelProductId);
				if(loadDisStatus){
					new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean().getAdminReferralUpdation(quoteNo,addSign,addPremium,loginId,travelProductId,"Broker");
				}
				request.setAttribute(OPTION,"PrintQuote");
				RequestDispatcher dispatcher=request.getRequestDispatcher("*.TravelPDFCreator");

				dispatcher.forward(request, response);
		}
		if("showPolicys".equalsIgnoreCase(path))
		{
				com.maan.common.Customer.DataSelect dataSelection;dataSelection = new com.maan.common.Customer.DataSelect();
				com.maan.Home.DataManipualtion.DataSelect data;data = new com.maan.Home.DataManipualtion.DataSelect();

				String quoteno			= (String)session.getAttribute(QUOTENUMBER);
				String clauses 			= isNull(request.getParameter(CLAUSES));
				String payFrom 			= isNull(request.getParameter("royalPayFrom"));
				String modeOfPurchase 	= isNull(request.getParameter("modeOfPurchase"),"D");
				String memberNo 	  	= isNull(request.getParameter("memberNo"));
				String motor 	      	= isNull(request.getParameter("motor"));
				String loyalty = isNull(request.getParameter("Loyalty"));
				if(motor.length()>0){
					motor = motor.substring(0,(motor.length()-1));
				}
				if(loyalty.equals("0")){
					memberNo = "";
				}
				String payMode;payMode = dataSelection.gerModeofPurchase(modeOfPurchase);
				if(payFrom.length()<=0){
					dataSelection.updateMemberShipDetails(quoteno,memberNo,motor,clauses,payMode,loyalty);
				}
				String loginId = "";
				if(session.getAttribute(USER)!=null)
				{
					loginId = (String)session.getAttribute(USER);
					session.setAttribute(USER,loginId);
				}
				String travelProductId="";
				if(session.getAttribute(TRAVELPID)!=null){
					travelProductId = (String)session.getAttribute(TRAVELPID);
				}

				String addSign = isNull(request.getParameter("addSign"));
				String addPremium =isNull(request.getParameter("addPremium"));
				boolean loadDisStatus = false;
				if(payFrom.length()<=0){
					loadDisStatus = dataSelect.getLoadDisStatus(loginId,travelProductId);
					if(loadDisStatus){
						new com.maan.Home.DataManipualtion.HomeQuoteRegisterBean().getAdminReferralUpdation(quoteno,addSign,addPremium,loginId,travelProductId,"Broker");
					}
				}
				String hour = data.getSysdateTime(quoteno,QUOTENUMBER);
				//new try combining like home
				final Map policyCollection;policyCollection = new HashMap();
				policyCollection.put(QUOTENOS, quoteno);
				policyCollection.put(PROID, travelProductId);
				policyCollection.put(BRANCH, branch);
				policyCollection.put("paymentMode", payMode);
				dataSelect.updatePolicyDetails(policyCollection,hour,branch);
				//
				request.setAttribute(OPTION,"All");
				RequestDispatcher dispatcher1=request.getRequestDispatcher("*.TravelPDFCreator");
				dispatcher1.include(request, response);

				RequestDispatcher dispatcher=request.getRequestDispatcher("/TravelInsurance/showPolicys.jsp?selectProd="+productId);
				dispatcher.forward(request, response);
		}
		if("AdminReferral".equalsIgnoreCase(path))
		{
			String adminStatus  = isNull(request.getParameter("adminStatus"));
			String adminRemarks = isNull(request.getParameter("adminRemarks"));
			//com.maan.Travel.DAO.TravelBean bean = new  com.maan.Travel.DAO.TravelBean();
			if("Y".equalsIgnoreCase(adminStatus))
			{
					String quoteno = isNull((String)session.getAttribute(QUOTENUMBER));
					request.setAttribute("TravelReferralQuoteNumber",quoteno);
					session.setAttribute(QUOTENUMBER,quoteno);
					request.setAttribute("ReferralDescriptions","Admin Referral");
					adminRemarks = adminRemarks.trim();
					//bean.updateReferalStatus(quoteno,"Admin Referral",adminRemarks,adminStatus);
					RequestDispatcher dispatcher=request.getRequestDispatcher("/TravelInsurance/ShowReferralCases.jsp");
					dispatcher.forward(request, response);
			}
		}
		if("EndorsementDetails".equalsIgnoreCase(path))
		{
			 //TravelBean bean = new TravelBean();
			 String counter  = isNull(request.getParameter("cnt"),"0");
			 int cnt 		 = Integer.parseInt(counter);
			 Vector error    = new Vector();
			 HashMap hTable	 = new HashMap();

			 String covPeriod         = isNull(request.getParameter("T-PERIOD"),"0");
			 String effDay            = isNull(request.getParameter("EffectiveDay"),"0");
			 String effMonth          = isNull(request.getParameter("EffectiveMonth"),"0");
			 String effYear           = isNull(request.getParameter("EffectiveYear"),"0");
			 String expDate           = isNull(request.getParameter("ExpiryDate"),"0");
			 String customerid 		  = isNull(request.getParameter("customerid"),"0");
			 String QuoteNo 		  = isNull(request.getParameter(QUOTENUMBER),"0");
			 String policyNo 		  = isNull(request.getParameter("policyNo"),"0");

			 if(covPeriod !=null){
				 hTable.put("coverPeriod",covPeriod);
			 }
			 if(effDay != null){
				 hTable.put("EffDay",effDay);
			 }
			 if(effMonth != null){
				 hTable.put("EffMonth",effMonth);
			 }
			 if(effYear != null){
				 hTable.put("EffYear",effYear);
			 }
			 if(expDate != null){
				 hTable.put("ExpDate",expDate);
			 }
			 if(customerid != null){
				 hTable.put("customerid",customerid);
			 }

			String[] names          =  new String[cnt];
			String[] dbday          =  new String[cnt];
			String[] dbmonth        =  new String[cnt];
			String[] dbyear         =  new String[cnt];
			String[] relations      =  new String[cnt];
			String[] nationality    =  new String[cnt];
			String[] genders     	=  new String[cnt];
			String[] serialnumber   =  new String[cnt];
			int[] age               =  new int[cnt];

			for(int i=0;i<cnt;i++)
			{
                String nam = "";
                String dbd = "";
                String dbm = "";
                String dby = "";
                String rel = "";
                String gen = "";
                String nationalities = "";
                // String dis = "";
                // String rem = "";
                String sno = "";

                nam =  request.getParameter("Name"+i);
                dbd =  request.getParameter("DOBDay"+i);
                dbm =  request.getParameter("DOBMonth"+i);
                dby =  request.getParameter("DOBYear"+i);
                rel =  request.getParameter("Relatives"+i);
                gen =  request.getParameter("genders"+i);
                nationalities =  request.getParameter("nationality"+i);
                sno =  request.getParameter("serialnumb"+i);

                names[i]         =  isNull(nam);
                dbday[i]         =  isNull(dbd,"0");
                dbmonth[i]       =  isNull(dbm,"0");
                dbyear[i]        =  isNull(dby,"0");
                relations[i]     =  isNull(rel);
                nationality[i]   =  isNull(nationalities);
                genders[i]       =  isNull(gen);
                age[i]           =  0;//bean.calculateAge(dbyear[i],dbmonth[i],dbday[i],branch);
                serialnumber[i]  =  isNull(sno);

			 }
				if(names!=null){
					hTable.put("names",names);
				}
				if(dbday!=null){
					hTable.put("dbday",dbday);
				}
				if(dbmonth!=null){
					hTable.put("dbmonth",dbmonth);
				}
				if(dbyear!=null){
					hTable.put("dbyear",dbyear);
				}
				if(relations != null){
					hTable.put("relations",relations);
				}
				if(nationality != null){
					hTable.put("nationality",nationality);
				}
				if(genders != null){
					hTable.put("genders",genders);
				}
				if(age != null){
					hTable.put("age",age);
				}
                if(serialnumber != null){
                	hTable.put("serialnum",serialnumber);
                }

                ValidationFormat form = new ValidationFormat();
                if(names.length==0){
                	error.add("Please Enter atleast one name to insure");
                }
                else{
                	for(int i=0;i<names.length;i++){
                		String str   = names[i].toLowerCase(Locale.ENGLISH).trim();
                		if(str != null && str.length() >0){
                			if(!form.validateStringWithSpace(str)){
                				error.add("Please Enter a Valid Name in" +(i+1)+ " Row");
                			}
                		}
                		else{
                			error.add("Please enter  name in "+(i+1)+" Row");
                		}
                	}
                }

                if(relations.length!=0){
                	int no_of_father = 0;
                	int no_of_mother = 0;
                    String seflGender = "";
                    String spouseGender = "";
                    for(int i=0;i<relations.length;i++){
                    	if(relations[i] == null || "0".equals(relations[i])){
                    		error.add("Please Select Relations in "+(i+1)+" Row");
                    	}
                    	else if("1".equals(relations[i])){
                    		seflGender = isNull(genders[i]);
                    	}
                    	else if(relations[i].equals("2")){
                    		spouseGender = isNull(genders[i]);
                    	}
                    	else if("4".equals(relations[i])){
                    		no_of_father = no_of_father + 1;
                    		if(genders[i]!=null && "F".equalsIgnoreCase(genders[i])){
                    			error.add("Invalid Gender - relationship in row "+(i+1));
                    		}
    				}
                    else if("5".equals(relations[i])){
                    	no_of_mother = no_of_mother + 1;
    					if(genders[i]!=null && "M".equalsIgnoreCase(genders[i])){
    						error.add("Invalid Gender - relationship in row "+(i+1));
    					}
    				}
                }
    			if(seflGender.length()>0 && spouseGender.length()>0 && seflGender.equalsIgnoreCase(spouseGender)){
    					error.add("Invalid Gender between Self and Spouse");
    			}
             }
                String str1 = validateExpDate(Integer.parseInt(effYear),Integer.parseInt(effMonth),Integer.parseInt(effDay));
                if(str1.length() > 0){
                	error.add(str1);
                }
                else{
                	DateFunction dateFunc = new DateFunction();
                	Calendar cal1 = Calendar.getInstance();
                	cal1.set(Integer.parseInt(effYear),Integer.parseInt(effMonth),Integer.parseInt(effDay));
                	Calendar cal2 = Calendar.getInstance();
                	String[][] serverDate =  null;//bean.getTodaysDate(branch);
                	cal2.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
                	long diff=0;
                	try{
                	diff = dateFunc.getDayDifference(cal1,cal2);
                	}catch(Exception e){logger.info(e);}
                	int backDates=0;
                	String backD="";
                	logger.info("Tarvel endronsement..diff."+diff);
                	if(diff > 0) {
						backD = "";//bean.getBackDatesAllowed((String)session.getAttribute(USER));
						logger.info("Tarvel endronsement..backD."+backD);
						if(backD != null && !"".equals(backD)){
							backDates = Integer.parseInt(backD);
						}
						logger.info("Tarvel endronsement..backDates."+backDates);
						if(backDates == 0){
							 error.add("Effective date cannot be less than current date");
						}
						else if(diff>backDates){
							error.add(runner.getErrormsg("266")+backDates+" days only");
						}
				  }
			}
                logger.info("Tarvel endronsement..backDates."+error.size());
			if(!error.isEmpty()){
				request.setAttribute("ErrorVector",error);
				RequestDispatcher dispatcher=request.getRequestDispatcher("/TravelInsurance/EndorsementDetails.jsp?EndorsementQno="+QuoteNo+"&EndorsementCusId="+customerid+"&EndorsementPno="+policyNo);
				dispatcher.forward(request, response);
			}
			else{
				boolean statusFlag = false;//bean.getEndronsementStatus(QuoteNo);
				if(statusFlag){
					String headAmend ="";//bean.insertForTravellersEndorsementDetails(hTable,QuoteNo);
					policyNo = policyNo +"/"+headAmend;
					request.setAttribute("EndorsementPolicy",policyNo);
				}
				else{
					request.setAttribute("EndorsementError","Noway");
				}
				RequestDispatcher dispatcher=request.getRequestDispatcher("/TravelInsurance/EndorsementShowQuote.jsp");
				dispatcher.forward(request, response);
			}
		}
		if("dataTransfer".equalsIgnoreCase(path))
		{
				String numbers;
				String quotPolBranch;
				RequestDispatcher reqDis = null;
				String quotes;
				quotes=request.getParameter("optionSearch");
				String selection = request.getParameter("quotes");
				com.maan.admin.DataSelection dataSelects;dataSelects = new com.maan.admin.DataSelection();
				quotPolBranch = dataSelects.getBranchForQuote(quotes,selection);
				
				numbers = dataSelects.getSearchQuote(quotes,selection);
				numbers=isNull(numbers,"DIDN'T SELECTED");

				if(numbers == null || "DIDN'T SELECTED".equalsIgnoreCase(numbers) || "".equalsIgnoreCase(numbers) || numbers.length() < 1){
					request.setAttribute("error","Invalid Number");
					reqDis = request.getRequestDispatcher("../TravelExport/DataTransfer.jsp");
					reqDis.forward(request, response);
				}
				else{
					/*com.maan.Travel.Services.HomeTravelMissippiEngine mississippiEngine;
					mississippiEngine=new com.maan.Travel.Services.HomeTravelMissippiEngine();
					String travelProductId;travelProductId = dataSelects.getProductforQuote(numbers);
					boolean changeStatus=false;
					mississippiEngine.setQuoteNo(numbers);
					mississippiEngine.setProductId(travelProductId);
					mississippiEngine.setLoginBra(branch);
					mississippiEngine.setBcid(cid);
					//mississippiEngine.LoadingData();
					changeStatus=mississippiEngine.LoadingData();
					String products = mississippiEngine.getProducts(new String[]{numbers});
					String productType ="";
					if(products.equalsIgnoreCase("21")){
						 productType = "Home";
					 }else if(products.equalsIgnoreCase("30")){
						 productType = "Office Shield";
					 }
					 else{
						 productType="Travel";
					 }
					request.setAttribute("productType",productType);
					session.setAttribute("quote_no",numbers);
					if(changeStatus){
						request.setAttribute("error","DataTransferFailed");
					}
					if(!branch.equalsIgnoreCase(quotPolBranch)){
						request.setAttribute("error","Invalid Branch Policy Nos or Quote Nos");
					}
					reqDis = request.getRequestDispatcher("/TravelExport/showTables.jsp");
					reqDis.forward(request, response);*/
				}
		}
	}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
	}
	 public String validateExpDate(final int year,final int month,final int day){
           String mess = "";
           if((month == 2 && year%4 == 0) && (day == 30 || day == 31)){
                   mess ="Please Select  a Valid  Effective Date";
           }
           else if((month == 2 && year%4 != 0) && (day == 29 || day == 30 || day ==31)){
                   mess ="Please Select  a Valid  Effective Date";
           }
           else if((month == 4 || month == 6 || month == 9 || month == 11) && (day == 31)){
                   mess ="Please Select  a Valid  Effective Date";
           }
           return mess;
     }

	 public String isNull(final String content)throws BaseException{
			final StringBuffer contents = new StringBuffer();
			if(content==null){
				contents.append("");
			}
			else{
				contents.append(content);
			}
			return contents.toString();
	 }
	 public String isNull(final String content,final String value)throws BaseException{
			final StringBuffer contents = new StringBuffer();
			if(content==null){
				contents.append(value);
			}
			else{
				contents.append(content);
			}
			return contents.toString();
	 }
}