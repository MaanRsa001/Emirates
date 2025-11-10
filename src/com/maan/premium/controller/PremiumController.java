package com.maan.premium.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.common.LogUtil;
import com.maan.pdf.PDFDisplay;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.premium.DAO.PremiumLogic;
import com.maan.services.util.runner;

public class PremiumController extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(PremiumController.class);
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	private RequestDispatcher dispatcher1;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			ProcessResult(request,response);
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
			ProcessResult(request,response);
	}

	public void ProcessResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=null;
	
		StringBuffer error=new StringBuffer();
		
		//Declaration start - Modified by Rajesh
		String modeOfTransport  = "";
		String warehouse        = "";
		String warehouse1	    = "";
		String transitFrom      = "";
		String finalDestination = "";
		String finalVia = "";
		String commodity        = "";
		String noOfItems        = "";
		String totalSumInsured  = "0";
		String currency         = "";
		String currencyValue    = "";
		String policyDay        = "";
		String policyMonth      = "";
		String policyYear       = "";
		String saleTerm         = "";
		String saleTermPercent  = "";
		String tolerance        = "";
		String cover            = "";
		String wsrcc            = "";
		String executive        = "";
		String fromCountryId    = "0";
		String toCountryId      = "0";
		String viaCountryId      = "0";
		String fromCityId       = "0";
		String toCityId         = "0";
		String viaCityId         = "0";
		String productId        = "";
		String loginId          = "";
		String companyId        = "";
		String sessionId        = "";
		String applicationNo    = "";
		String warStatus        = "";
		String partialShip      = "";
		String partialShipmentAmt ="";
		String openCoverNo="";
		String bankName="0";
		String lcNumber="0";
		String vesselName="";
		String vesselOption="";
		String openBrokerId="";//0
		String openBrokerUserId="";//1
		String openCoverStartDate="";//2
		String openCoverEndDate="";//3
		String openProductId="";//4
		String openCustomerId="";//5
		String openBranchCode="";//6
		String openEstimatedTurnOver="";//7
		String openCrossVoyageSumInsuredLimit="";//8
		String openBackDays="";//9
		String openCommission="";//10
		String openNoOfInsuranceCompany="";//11
		String openCrossVoyagePercentage="";//12
		String openRsaSharedPercentage="";//13
		String openCrossVoyage="";//14
		String openMinimumPremium="";//15
		error=new StringBuffer();
		String agreeStatus = "";
		String coverStartDate = "";
		String seasOptionValue="";
		String exposureCurrency="";
		String exposureCurrencyValue="";
		String vesselId="";
		PremiumInputsBean premiumTransaction=new PremiumInputsBean();
		
		PremiumLogic premiumLogic=new PremiumLogic();
		//Declaration End - Modified by Rajesh
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		session=request.getSession(false);
		String contextPath=request.getContextPath();
		if(session==null || session.getAttribute("ses")==null){
			response.sendRedirect(contextPath+"/login/error_messg.jsp");	
			return;
		}
		String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
		
		if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController) ){
				response.sendRedirect(contextPath+"/login/error_messg.jsp");	
				return;
		}
		
		System.out.println(" ****************** SESSION CHECKING   ****************");
		String[][] sessionMaster_id=new String[0][0];
		String[][] tracking_id=new String[0][0];
		
		//END
		String user1 = (String)session.getAttribute("user1");
		String user = (String)session.getAttribute("user");
		if(!"admin".equalsIgnoreCase(user1))
		{
			sessionMaster_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from session_master where session_id='"+(String)session.getAttribute("ses")+"'");
			tracking_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from tracking_master where session_id='"+(String)session.getAttribute("ses")+"'");
			if(sessionMaster_id.length>0 && tracking_id.length>0){
				try{
					if(!(user).equalsIgnoreCase(sessionMaster_id[0][1]) && !"s".equalsIgnoreCase((String)session.getAttribute("rsa_type"))){
						response.sendRedirect(contextPath+"/login/error_messg.jsp");
						return;
					}
					else if(!(user).equalsIgnoreCase(tracking_id[0][1]) && session.getAttribute("RSAISSUER")==null){
						response.sendRedirect(contextPath+"/login/error_messg.jsp");
						return;
					}					
				}
				catch(Exception e){
					System.out.println("ERROR NOT AVAILABLE SESSION ID\n "+e.toString());
				}
			}
		}
		//Rajesh World WOrk statred
		
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		String refMaster[][]=new String[0][0];
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
			dcid = (String)brokerDetails.get("Destination");
		}

		premiumTransaction.setCid(cid);
		premiumTransaction.setLoginBra(brokerBra);
		premiumLogic.setCid(cid);
		premiumLogic.setLoginBra(brokerBra);
        premiumLogic.setLoginCode(loginId);
		refMaster = premiumTransaction.getReferralDetailsMaster(brokerBra);
		boolean countryRef = false;
		boolean policyDateRef = false;
		boolean roadRef = false;
		boolean suninsuredRef = false;
		boolean crossvoyagesuninsuredRef = false;
		boolean crossvoyagecountryRef = false;
		boolean warehousecountryRef = false;
		boolean samecountryRef = false;
		boolean LCsumRef = false;
		boolean vesselRef = false;
		boolean defaultRoadRef = false;
		for(int i=0;i<refMaster.length;i++)
		{
			if(refMaster[i][0].equalsIgnoreCase("1"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					countryRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("2"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					policyDateRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("4"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					roadRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("11"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					suninsuredRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("12"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					crossvoyagesuninsuredRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("13"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					crossvoyagecountryRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("17"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					warehousecountryRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("18"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					samecountryRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("19"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					LCsumRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("20"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					vesselRef = true;
			}
			else if(refMaster[i][0].equalsIgnoreCase("35"))
			{
				if(refMaster[i][1].equalsIgnoreCase("Y"))
					defaultRoadRef = true;
			}
		}
		agreeStatus =request.getParameter("agreeStatus")==null?"":request.getParameter("agreeStatus");
		

		System.out.println("  ID from session  "+(String)session.getAttribute("ses"));
		System.out.println("  USER NAME from session  "+user);
		System.out.println(" ****************** SESSION CHECKING   ****************");
		out.println("the user Mode is"+usrModeController);
		out.println("<br>transitFrom----"+request.getParameter("transitFrom"));
		out.println("<br>finalDestination---"+request.getParameter("finalDestination"));
		String path=request.getRequestURI();
		path=path.substring(0,path.lastIndexOf("/")+1);
		String nextMissing="NOTHING";
		nextMissing=request.getParameter("next")==null?nextMissing:request.getParameter("next").equals("")?nextMissing:request.getParameter("next");
		productId=request.getParameter("productId")==null?(String)session.getAttribute("productId")==null?"0":(String)session.getAttribute("productId"):request.getParameter("productId").equals("")?"0":request.getParameter("productId");
		loginId=request.getParameter("loginId")==null?"":request.getParameter("loginId");
		companyId=request.getParameter("companyId")==null?"0":request.getParameter("companyId").equals("")?"0":request.getParameter("companyId");
		sessionId=request.getParameter("sessionId")==null?"":request.getParameter("sessionId");

		openCoverNo=request.getParameter("openCoverNo")==null?(String)session.getAttribute("openCoverNo")==null?"0":(String)session.getAttribute("openCoverNo"):request.getParameter("openCoverNo");
		
		if(("0".equalsIgnoreCase(openCoverNo) && "11".equalsIgnoreCase(productId))|| "0".equalsIgnoreCase(productId)){
			response.sendRedirect("login/error_messg.jsp");
			return;
		}
		applicationNo=request.getParameter("applicationNo")==null?((String)session.getAttribute("applicationNo")):request.getParameter("applicationNo");
		if(applicationNo==null){
			out.println("<br>    applicationNo       NULL   ");
			if(true)	return;
		}
		else
			session.setAttribute("applicationNo",applicationNo);
		
		///System.out.println("applicationNo in premium contoller..."+applicationNo);
		modeOfTransport=request.getParameter("modeOfTransport")==null?modeOfTransport:request.getParameter("modeOfTransport");
		lcNumber=request.getParameter("lcNumber")==null?lcNumber:request.getParameter("lcNumber");
		vesselName=request.getParameter("vesselName")==null?vesselName:request.getParameter("vesselName");
		fromCountryId=request.getParameter("transit_from")==null?"0":request.getParameter("transit_from");
		toCountryId=request.getParameter("final_from")==null?"0":request.getParameter("final_from");
		viaCountryId=request.getParameter("final_via")==null?"0":request.getParameter("final_via");
		warehouse=request.getParameter("warehouse")==null?"NO":request.getParameter("warehouse");
		warehouse1=request.getParameter("warehouse1")==null?"NO":request.getParameter("warehouse1");
		cover=request.getParameter("cover")==null?cover:request.getParameter("cover");
		saleTerm=request.getParameter("saleTerm")==null?"":request.getParameter("saleTerm");
		saleTermPercent=request.getParameter("saleTermPercent")==null?"":request.getParameter("saleTermPercent");
		tolerance=request.getParameter("tolerance")==null?tolerance:request.getParameter("tolerance");
		currency=request.getParameter("currency")==null?currency:request.getParameter("currency");
		currencyValue=request.getParameter("currencyValue")==null?currencyValue:request.getParameter("currencyValue");
		policyDay=request.getParameter("policyDay")==null?policyDay:request.getParameter("policyDay");
		policyMonth=request.getParameter("policyMonth")==null?policyMonth:request.getParameter("policyMonth");
		policyYear=request.getParameter("policyYear")==null?policyYear:request.getParameter("policyYear");
		commodity=request.getParameter("commodity")==null?commodity:request.getParameter("commodity");
		noOfItems=request.getParameter("noOfItems")==null?noOfItems:request.getParameter("noOfItems");
		totalSumInsured=request.getParameter("totalSumInsured")==null?totalSumInsured:request.getParameter("totalSumInsured");
		totalSumInsured=totalSumInsured.replaceAll(",", "");
		executive=request.getParameter("executive")==null?executive:request.getParameter("executive");
		bankName=request.getParameter("bankName")==null?bankName:request.getParameter("bankName");
		fromCityId=request.getParameter("transit_from_city")==null?"0":request.getParameter("transit_from_city");
		toCityId=request.getParameter("transit_final_city")==null?"0":request.getParameter("transit_final_city");
		viaCityId=request.getParameter("transit_via_city")==null?"0":request.getParameter("transit_via_city");
		finalVia=request.getParameter("finalVia")==null?finalVia:request.getParameter("finalVia");
		finalDestination=request.getParameter("finalDestination")==null?finalDestination:request.getParameter("finalDestination");
		transitFrom=request.getParameter("transitFrom")==null?transitFrom:request.getParameter("transitFrom");
		coverStartDate=request.getParameter("coverStartDate")==null?transitFrom:request.getParameter("coverStartDate");
		seasOptionValue=request.getParameter("seasOption")==null?"0":request.getParameter("seasOption");
		exposureCurrency=request.getParameter("exposureCurrency")==null?"0":request.getParameter("exposureCurrency");
		exposureCurrencyValue=request.getParameter("exposureCurrencyValue")==null?"0":request.getParameter("exposureCurrencyValue");
		vesselId=request.getParameter("vesselId")==null?"0":request.getParameter("vesselId");
//		originatingCityName
//		destinationCityName
		String startPlace="", EndPlace="";
		startPlace = premiumTransaction.getStartingPlace(fromCountryId,cid);
		EndPlace = premiumTransaction.getEndingPlace(toCountryId,cid);
		System.out.println("modeOfTransport " + modeOfTransport + " seasOptionValue " + seasOptionValue);
		
		error=error.append(checkInputs(modeOfTransport,"MODE OF TRANSPORT","74"));
		if(!seasOptionValue.equalsIgnoreCase("0") && !"select".equalsIgnoreCase(seasOptionValue))
		{
			premiumTransaction.setSeaOption(seasOptionValue);
			request.setAttribute("seasOptionValue",seasOptionValue);
			System.out.println("-------INSIDE CONTROLLER OF PREMIUM @@@@@@------>"+seasOptionValue);
			if(!"admin".equalsIgnoreCase(user1))
			{
				seasOptionValue = findSeasReferal(seasOptionValue,productId,brokerBra);
				request.setAttribute("seasReferal",seasOptionValue);
			}
		} else if("11".equalsIgnoreCase(productId)){
			premiumTransaction.setSeaOption(seasOptionValue);
			if(!"0".equalsIgnoreCase(seasOptionValue))
				error=error.append(checkInputs(seasOptionValue,"CONVEYANCE","462"));			
		} else {
			error=error.append(checkInputs(seasOptionValue,"CONVEYANCE","462"));			
		}
		error=error.append(checkInputs(cover,"COVERAGES","81"));///
		
		request.setAttribute("transitFrom",transitFrom);
		request.setAttribute("finalDestination",finalDestination);
		request.setAttribute("finalVia",finalVia);
		
		System.out.println(" transitFrom BEFORE CONDITION   "+transitFrom+"----finalDestination" +finalDestination);
		if(transitFrom!=null)
		{
			if(transitFrom.length()<1)
				transitFrom=" ";	
		}
		else
			transitFrom=" ";
		
		if(finalDestination!=null)
		{
			if(finalDestination.length()<1)
				finalDestination=" ";	
		}
		else
			finalDestination=" ";
		
		if(finalVia!=null)
		{
			if(finalVia.length()<1)
				finalVia=" ";	
		}
		else
			finalVia=" ";
		
		System.out.println(" transitFrom AFTER CONDITION   "+transitFrom+"----finalDestination   "+finalDestination);
		if(warehouse==null){
			System.out.println("<br>  warehouse is null  ");
		}
		out.println(" TransitFrom is-------------->>>>>"+transitFrom);
		out.println("Final Destination is-------------->>>>>"+finalDestination);
		
		//Block For warehouse country Referral
		if("3".equalsIgnoreCase(productId)&&warehousecountryRef)
		{
			if(!"Warehouse".equalsIgnoreCase(startPlace) && "YES".equalsIgnoreCase(warehouse)){
					if(!"admin".equalsIgnoreCase(user1))
						request.setAttribute("countryReferal","referal");	
			}
			else if("Warehouse".equalsIgnoreCase(startPlace) && "YES".equalsIgnoreCase(warehouse)){
				if((transitFrom.trim()).length()<1)
					transitFrom=null;
				error=error.append(checkInputs(transitFrom,"CITY FOR TRANSIT FROM","93"));
			}
			
			if(!"Warehouse".equalsIgnoreCase(EndPlace) && "YES".equalsIgnoreCase(warehouse1)){
				if(!"admin".equalsIgnoreCase(user1))
					request.setAttribute("countryReferal","referal");	
				//request.setAttribute("countryReferal","NO referal");	
			}
			else if("Warehouse".equalsIgnoreCase(EndPlace) && "YES".equalsIgnoreCase(warehouse1)){
				if((finalDestination.trim()).length()<1)
					finalDestination=null;
				error=error.append(checkInputs(finalDestination,"CITY FOR FINAL DESTINATION","94"));
			}
		}
		/*error=error.append(checkInputs(policyDay,"POLICY DAY","83");
		error=error.append(checkInputs(policyMonth,"POLICY MONTH","84");
		error=error.append(checkInputs(policyYear,"POLICY YEAR","85");*/
		String startDate[]=coverStartDate.split("/");
		if(startDate!=null && startDate.length==3)
		{
			policyDay=startDate[0];
			policyMonth=startDate[1];
			policyYear=startDate[2];
		}
		error=error.append(checkInputs((policyDay+"/"+policyMonth+"/"+policyYear),"DATECHECK","92"));
		error=error.append(checkInputs(commodity,"COMMODITY","90"));
		
		if(commodity.length()>0)
		{
			//Commodity Validation March -06
			boolean comFlag = false;
			comFlag = premiumTransaction.commodityValidation(applicationNo,openCoverNo,productId,brokerBra);
			if(!comFlag)
				error.append("<font color=red size=2><br>* <b>"+runner.getErrormsg("360")+" </b><br></font>");
			// Commodity Validation March -06
		}
		
		if(!commodity.equalsIgnoreCase("")){
			error=error.append(checkInputs(noOfItems,"NO OF ITEMS","76"));
			error=error.append(checkInputs(totalSumInsured,"TOTAL SUM INSURED","91"));
		}
		
		error=error.append(checkInputs(currency,"CURRENCY","78"));
		
		if(!currency.equalsIgnoreCase("")  && !currency.equalsIgnoreCase("0"))
			error=error.append(checkInputs(currencyValue,"CURRENCY VALUE","79"));
		
		
		
		error=error.append(checkInputs(executive,"A/C Executive","82"));
		
		if("11".equalsIgnoreCase(productId))
		{
			error=error.append(checkInputs(lcNumber,"LC NUMBER","130"));
			if(!lcNumber.equalsIgnoreCase("") && !lcNumber.equalsIgnoreCase("0"))
				error=error.append(checkInputs(bankName,"BANK NAME","129"));
		}
		
		if("3".equalsIgnoreCase(productId))
		{
			error=error.append(checkInputs(warehouse1,"WAREHOUSE","99"));
			error=error.append(checkInputs(warehouse,"WAREHOUSE","75"));
		}
		
		if("3".equalsIgnoreCase(modeOfTransport))
		{
			warehouse="YES";
			warehouse1="YES";
		}
		
		request.setAttribute("warehouse",warehouse);
		request.setAttribute("warehouse1",warehouse1);
		
		//Open Cover POlicy
		//if("11".equalsIgnoreCase(productId))
		/*{
			if("1".equalsIgnoreCase(modeOfTransport))
			{
				if(vesselName == null || vesselName.length() == 0 || vesselName.equals("") || vesselName.equalsIgnoreCase("null"))
				{	
					error.append("<br><font color=red size=2><b>* "+runner.getErrormsg("128")+"</b></font><br>");
				}
			}
		}*/
		
			String userName="";
			if("admin".equalsIgnoreCase(user1))
				userName=premiumTransaction.getLogins(applicationNo);
			else
				userName=user==null?"NOUSER":user;
			userName=userName==null?"NOUSER":userName;
			
			out.println("logged person is "+userName);
			/****Import Export Premium	****/
			String origin = "";
			String destination = "";
			origin = request.getParameter("transit_from");
			destination = request.getParameter("final_from");
			
			origin = origin == null ? "" : origin;
			destination = destination == null ? "" : destination;
			
			/*String comExcesPremOption="";
			if("3".equalsIgnoreCase(productId))
			{
				if(modeOfTransport.equalsIgnoreCase("1") || modeOfTransport.equalsIgnoreCase("2") )
				{
					if( origin.equalsIgnoreCase(cid) && !destination.equalsIgnoreCase(dcid) )
					{
						comExcesPremOption = "Y";
						request.setAttribute("comExcesPremOption",comExcesPremOption);
					}
					else
					{
						comExcesPremOption="N";
						request.setAttribute("comExcesPremOption",comExcesPremOption);
					}
				}
				else
				{
					comExcesPremOption="N";
					request.setAttribute("comExcesPremOption",comExcesPremOption);
				}
			}*/
			request.setAttribute("comExcesPremOption","Y");
			if("NOUSER".equalsIgnoreCase(userName)){
				request.setAttribute("minPremium","0");
			}
			else
			{
				String[][] minPrem=new String[0][0];
				/*String origin = "";
				String destination = "";
				origin = request.getParameter("transit_from");
				destination = request.getParameter("final_from");
				origin = origin == null ? "" : origin;
				destination = destination == null ? "" : destination;*/
				if("11".equalsIgnoreCase(productId))
				{
					if(modeOfTransport.equalsIgnoreCase("1") || modeOfTransport.equalsIgnoreCase("2") || modeOfTransport.equalsIgnoreCase("4") )
					{
						if( !origin.equalsIgnoreCase(cid) && !destination.equalsIgnoreCase(dcid) )
							minPrem = premiumTransaction.getOpenCoverCrossMiniPremium(openCoverNo);
						else if( !origin.equalsIgnoreCase(cid) && destination.equalsIgnoreCase(dcid) )
							minPrem = premiumTransaction.getOpenCoverImportMiniPremium(openCoverNo);	
						else
							minPrem = premiumTransaction.getOpenCoverExportMiniPremium(openCoverNo);	
					}
					else
					{
						minPrem=premiumTransaction.getOpenCoverMiniPremium(openCoverNo);			
					}
				}
				else if("3".equalsIgnoreCase(productId)){
					//For Freight Forwarder
					boolean freightQuote = false;
					try{
						if(session.getAttribute("quote_no")!=null)
						{
						 freightQuote = premiumTransaction.getFreightQuoteStatus((String)session.getAttribute("quote_no"));
						}
						String freightLogin = "";
						if(session.getAttribute("freight")!=null)
							freightLogin = (String)session.getAttribute("freight");
						
						if(freightLogin.equalsIgnoreCase("freight"))
							minPrem=premiumTransaction.getFreightMinPrem(userName);
						else if((freightQuote&&"Broker".equals(session.getAttribute("usertype"))))
						{
							minPrem=premiumTransaction.getFreightMinPremQuoteNo((String)session.getAttribute("quote_no"));
						}
						else
						{
							minPrem=premiumTransaction.getMinPrem(userName,productId);
						}
						
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				if(minPrem.length > 0){
					if("11".equalsIgnoreCase(productId)){
						request.setAttribute("minPremium",(minPrem[0][15]==null?"0":minPrem[0][15]));
					}
					else if ("3".equalsIgnoreCase(productId))
					{
						request.setAttribute("minPremium",(minPrem[0][1]==null?"0":minPrem[0][1]));
					}
				}
				else{
					request.setAttribute("minPremium","0");
				}
			}			//end of Else
			out.println("error is on the "+error);
			
		//For Freight Forwarders - Rajesh
		String freight = "";
		String freightUser = "";
		if(session.getAttribute("freight")!=null)
			freight = (String)session.getAttribute("freight");
		if(user!=null)
			freightUser = user;
		
		//For Freight Forwarder BackDates Test - Rajesh
		if(freight.equalsIgnoreCase("freight")&&!freightUser.equalsIgnoreCase("admin"))
		{
			String DD1 = "";
			String MM1 = "";
			String YYYY1 = ""; 
			DD1 = policyDay;
			MM1 = policyMonth;
			YYYY1 = policyYear;
			if(DD1.equals("Select") || MM1.equals("Select") || YYYY1.equals("Select")||DD1.equals("DD") || MM1.equals("MM") || YYYY1.equals("YYYY")){
				  // ErrorMsg.append("Please Select Insurance Start Date");
				  // ErrorMsg.append(",");
				  error=error.append(checkInputs("Please Select Insurance Start Date","BackDates","3482"));
			}
			else{
				  String[] months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
				 for(int m=0;m<months.length;m++){
					 if(months[m].equalsIgnoreCase(MM1)){
						 MM1 = ""+(m+1);
						 break;
					}
				}
				DateFunction df = new DateFunction();
				//
				Calendar c1 = Calendar.getInstance();
				Calendar c2 = Calendar.getInstance();
				
				c2.set(Integer.parseInt(YYYY1),Integer.parseInt(MM1),Integer.parseInt(DD1));
				
				String[][] serverDate =  new String[0][0];
				serverDate = premiumTransaction.getTodaysDate();
				
				if(serverDate.length>0)
				{
					 
					c1.set(Integer.parseInt(serverDate[0][0]),Integer.parseInt(serverDate[0][1]),Integer.parseInt(serverDate[0][2]));
				}
				 long diff = 0;
				 try{
					   diff = df.getDayDifference(c2,c1);
				 }
				 catch(Exception ex){
					   ex.printStackTrace();
				 }
				 //
				int backDates = 0;
				String backD = "0";				
				backD = premiumTransaction.getBackDatesAllowed(userName,productId);
				try{
					if(backD!=null&&!backD.equals(""))
						backDates = Integer.parseInt(backD);
				}
				catch(Exception e){
					backDates = 0;
				}
				
				if(diff > backDates)
				{
						if(backDates==0)
						{
							
							 error=error.append(checkInputs("Insurance start date should not be less than today date","BackDates","3482"));
							 //error=error.append(checkInputs("Insurance Start Date cannot be less than Todays Date","BackDates","3482");
						}
						else if(backDates>0)
						{
						  error=error.append(checkInputs("Back dates allwoed maximum "+backDates+" days only","BackDates","3482"));
						}
				}
			 }
		}
		//Freight BackDates End - Rajesh
		if("3".equalsIgnoreCase(productId))
			saleTerm=getSaleTermId(saleTerm,saleTermPercent,brokerBra);
		error=error.append(checkInputs(saleTerm,"SALE TERM","80"));		
		wsrcc=request.getParameter("wsrcc")==null?wsrcc:request.getParameter("wsrcc");
		System.out.println("the WSRCC IS--"+wsrcc);
		
		//Open Cover Policy
		if("11".equalsIgnoreCase(productId))
		{
			/*vesselOption=request.getParameter("vesselOption")==null?vesselOption:request.getParameter("vesselOption");
			if("2".equalsIgnoreCase(modeOfTransport) || "3".equalsIgnoreCase(modeOfTransport))
			{
				vesselOption=request.getParameter("vesselOption")==null?vesselOption:request.getParameter("vesselOption");
			}
			else
			{
				error=error.append(checkInputs(vesselOption,"Vessel Option","131");
			}
			vesselName=request.getParameter("vesselName")==null?vesselName:request.getParameter("vesselName");
			//error=error.append(checkInputs(vesselName,"Vessel Name","128");
			if("2".equalsIgnoreCase(modeOfTransport) || "3".equalsIgnoreCase(modeOfTransport))
			{
				vesselName=request.getParameter("vesselName")==null?vesselName:request.getParameter("vesselName");
			}
			else
			{
				if("NO".equalsIgnoreCase(vesselOption.trim()))
				{
					vesselName="";
				}
				else
				{
					error=error.append(checkInputs(vesselName,"Vessel Name","128");
				}
			}*/
			
			//Open Cover Policy
			//vesselOption=request.getParameter("vesselOption")==null?vesselOption:request.getParameter("vesselOption");
			//error=error.append(checkInputs(vesselOption,"Vessel Option","131");
			
			//Block For SumInsured Exceeds LCAmount Referral
			if("11".equalsIgnoreCase(productId)&&LCsumRef)
			{
				String[][] lcTotalSumInsured=new String[0][0];
				String[][] lcAmountForLCID=new String[0][0];
				
				if("0".equalsIgnoreCase(lcNumber) || "99999".equalsIgnoreCase(lcNumber) || "99999".equalsIgnoreCase(bankName) || "0".equalsIgnoreCase(bankName) || "".equalsIgnoreCase(lcNumber) || "".equalsIgnoreCase(bankName) ||  " ".equalsIgnoreCase(lcNumber) || " ".equalsIgnoreCase(bankName) || "0".equalsIgnoreCase(totalSumInsured) || "".equalsIgnoreCase(totalSumInsured) || "".equalsIgnoreCase(currencyValue) || "0".equalsIgnoreCase(currencyValue))
				{

				}
				else
				{
					String lcTotalSI="0";
					String lcOriginalAmount="0";
					String lcExchangeRate="0";
					String lcCheckAmount="0";
					System.out.println("the LC CHECKING ERRORS STARTING HERE ");
					System.out.println("the LC bankName is "+bankName);
					System.out.println("the LC lcNumber is "+lcNumber);
					System.out.println("the LC openCoverNo is "+openCoverNo);
					
					String lcDateCheck = "";
					if(!"admin".equalsIgnoreCase(user1))
						lcDateCheck = premiumTransaction.getLcDateCheck(policyDay,policyMonth,policyYear,bankName,lcNumber,openCoverNo);
					if(lcDateCheck.length()>0)
					{
						error.append(lcDateCheck);
					}
					else
					{
						lcTotalSumInsured = premiumTransaction.getLCBasedTotalSumInsured(bankName,lcNumber,openCoverNo);

						if(lcTotalSumInsured.length>0)
						{
							System.out.println("the LC TOTAL SI is For Certificates Issued "+lcTotalSumInsured[0][0]==null?"0":lcTotalSumInsured[0][0]);
							System.out.println("the LC TOTAL SI CURRENCY VALUE is "+currencyValue);
							System.out.println("the LC TOTAL totalSumInsured Entered is "+totalSumInsured);
							double ConvertedSumIns = 0.0;
							ConvertedSumIns = premiumTransaction.getConvertedTotalSumInsured(saleTerm,tolerance,totalSumInsured,currencyValue);
							lcTotalSI=""+((Double.parseDouble(lcTotalSumInsured[0][0]==null?"0":lcTotalSumInsured[0][0]))+ConvertedSumIns);
							System.out.println("the LC TOTAL SI is "+lcTotalSI);
						}
						lcAmountForLCID=premiumTransaction.getsLCDetails(bankName,lcNumber,openCoverNo,cid);
						if(lcAmountForLCID.length>0)
						{
							lcOriginalAmount=lcAmountForLCID[0][2]==null?"0":lcAmountForLCID[0][2];
							lcExchangeRate=lcAmountForLCID[0][6]==null?"0":lcAmountForLCID[0][6];
							System.out.println("the LC lcOriginalAmount is "+lcOriginalAmount);
							System.out.println("the LC lcExchangeRate is "+lcExchangeRate);
							lcCheckAmount=""+((Double.parseDouble(lcOriginalAmount)*Double.parseDouble(lcExchangeRate)));
							System.out.println("the LC lcCheckAmount is "+lcCheckAmount);
						
							System.out.println("Oman Checking lcTotalSI..."+lcTotalSI);
							System.out.println("Oman Checking lcCheckAmount..."+lcCheckAmount);
							System.out.println("Oman Checking user1..."+user1);

							if( (Math.round(Double.parseDouble(lcTotalSI)) > Math.round(Double.parseDouble(lcCheckAmount))) && !"admin".equalsIgnoreCase(user1) )
							{
								request.setAttribute("totalSumInsuredExceedsLCAmount","referal");
							}
						  }	
					}             //End of Else Part
				}
			 }
	    }
		/*else{
			vesselName="";
			vesselOption="";
		}*/
		
		String[][] openCoverMasterDatasBefore=new String[0][0];
		String openCrossVoyageBefore="";
		String openCrossVoyageSumInsuredLimitBefore="0";
		if("11".equalsIgnoreCase(productId))
		{
			openCoverMasterDatasBefore=premiumTransaction.getOpenCoverMasterDatas(openCoverNo);
			if(openCoverMasterDatasBefore.length>0)
			{
				openCrossVoyageBefore=openCoverMasterDatasBefore[0][14]==null?"":openCoverMasterDatasBefore[0][14];//14
						print("openCrossVoyageBefore",openCrossVoyageBefore,"value");
				openCrossVoyageSumInsuredLimitBefore=openCoverMasterDatasBefore[0][8]==null?"0":openCoverMasterDatasBefore[0][8];//14
						print("openCrossVoyageSumInsuredLimitBefore",openCrossVoyageSumInsuredLimitBefore,"value");
			}
		}
		String validMsg=null;
		//Block for countryReferal,invalidCrossVoyageSIReferal and invalidCrossVoyageCountryReferal Referral
		if(countryRef||crossvoyagesuninsuredRef||crossvoyagecountryRef)
		{
			if(("3".equalsIgnoreCase(productId) || "11".equalsIgnoreCase(productId)) && countryRef)
			{
				validMsg=findDubai(request.getParameter("transit_from"),request.getParameter("final_from"),cid,productId);
			}
			if (("11".equalsIgnoreCase(productId)) && !"invalid".equalsIgnoreCase(validMsg))
			{
				validMsg=findDubaiOpen(request.getParameter("transit_from"),request.getParameter("final_from"),openCrossVoyageBefore,openCrossVoyageSumInsuredLimitBefore,openCoverNo,cid,String.valueOf(Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue)));
			}
			//validMsg="valid";
			if(!"admin".equalsIgnoreCase(user1))
			{
				if("invalid".equalsIgnoreCase(validMsg)&&countryRef)
					request.setAttribute("countryReferal","referal");
				else if("invalidCrossVoyageSIReferal".equalsIgnoreCase(validMsg)&&crossvoyagesuninsuredRef)
					request.setAttribute("crossVoyageSIReferal","referal");
				else if("invalidCrossVoyageCountryReferal".equalsIgnoreCase(validMsg)&&crossvoyagecountryRef)
					request.setAttribute("crossVoyageCountryReferal","referal");
			}
		}
		//error.append("<br><font color=red size=2><b>*Please Kindly Choose Country as UNITED ARAB EMIRATES in TRANSIT FROM or FINAL DISTINATION</b></font><br>");	
		/*fromCountryId=request.getParameter("fromCountryId")==null?fromCountryId:
			request.getParameter("fromCountryId");
		toCountryId=request.getParameter("toCountryId")==null?toCountryId:
			request.getParameter("toCountryId");*/
		/*fromCountryId=(String)session.getAttribute("fromCountryId")==null?fromCountryId:
			(String)session.getAttribute("fromCountryId");
		toCountryId=(String)session.getAttribute("toCountryId")==null?toCountryId:
			(String)session.getAttribute("toCountryId");
		fromCityId=(String)session.getAttribute("fromCityId")==null?fromCityId:
			(String)session.getAttribute("fromCityId");
		toCityId=(String)session.getAttribute("toCityId")==null?toCityId:
			(String)session.getAttribute("toCityId");*/
		out.println("transit_from_country  "+request.getParameter("transit_from"));

		fromCountryId=request.getParameter("transit_from")==null?"0":request.getParameter("transit_from");
		toCountryId=request.getParameter("final_from")==null?"0":request.getParameter("final_from");
		viaCountryId=request.getParameter("final_via")==null?"0":request.getParameter("final_via");
		out.println("the fromCOuntrId is "+fromCountryId);
		out.println("the toCOuntrId is "+toCountryId);
		out.println("the viaCOuntrId is "+viaCountryId);
				
		//if("11".equalsIgnoreCase(productId)&&vesselRef)
		{
//			if("Yes".equalsIgnoreCase(vesselOption))
			if("R".equalsIgnoreCase(getVesselReferralStatus(vesselName)))
				request.setAttribute("VesselReferal","referal");
		}
		/*else if(("Warehouse".equalsIgnoreCase(startPlace) || "Warehouse".equalsIgnoreCase(EndPlace) ) && "YES".equalsIgnoreCase(warehouse))
			{
				System.out.println("fromCityId --->"+transitFrom);
			if((transitFrom.trim()).length()<1)
			transitFrom=null;
			error=error.append(checkInputs(transitFrom,"CITY FOR TRANSIT FROM","93");
			if((finalDestination.trim()).length()<1)
			finalDestination=null;
			error=error.append(checkInputs(finalDestination,"CITY FOR FINAL DESTINATION","94");
			}*/
			/*if(!"admin".equalsIgnoreCase(user1))
					{
						if(("NO").equalsIgnoreCase(warehouse))
						transitFrom=" ";
						if(("NO").equalsIgnoreCase(warehouse1))
						finalDestination=" ";
					}*/
		/*else if("Warehouse".equalsIgnoreCase(EndPlace) && "YES".equalsIgnoreCase(warehouse))
		{
			System.out.println("fromCityId --->"+finalDestination);
		System.out.println("fromCityId --->"+transitFrom);
		if((finalDestination.trim()).length()<1)
		finalDestination=null;
		error=error.append(checkInputs(finalDestination,"CITY FOR FINAL DESTINATION");
		}
		else if((!"Warehouse".equalsIgnoreCase(startPlace) || !"Warehouse".equalsIgnoreCase(EndPlace)) && "YES".equalsIgnoreCase(warehouse))
		{
			error.append("<br><font color=red size=2><b>*We Cannot offer Warhouse </b></font><br>");	
		}*/
		
		/*else if(!"Warehouse".equalsIgnoreCase(EndPlace) && "YES".equalsIgnoreCase(warehouse))
		{
			error.append("<br><font color=red size=2><b>*Cannot provide Warhouse for final country.So please select 'NO' Option for Warehouse </b></font><br>");	
		}*/
		if(" ".equalsIgnoreCase(transitFrom))
			fromCityId="0";
		if(" ".equalsIgnoreCase(finalDestination))
			toCityId="0";
		
		if(" ".equalsIgnoreCase(finalVia))
			viaCityId="0";
		
		if(!"0".equalsIgnoreCase(fromCityId))
			fromCityId=com.maan.services.util.runner.singleSelection("select city_id from city_master where city_name='"+request.getParameter("transitFrom")+"' and country_id='"+fromCountryId+"'");
//		fromCityId=com.maan.services.util.runner.singleSelection("select city_id from city_master where city_name='"+request.getParameter("transit_from_city")+"' and country_id='"+fromCountryId+"'");
		
		if(!"0".equalsIgnoreCase(toCityId))
			toCityId=com.maan.services.util.runner.singleSelection("select city_id from city_master where city_name='"+request.getParameter("finalDestination")+"' and country_id='"+toCountryId+"'");
//		toCityId=com.maan.services.util.runner.singleSelection("select city_id from city_master where city_name='"+request.getParameter("transit_final_city")+"' and country_id='"+toCountryId+"'");
		if(viaCityId.length()>3)
			viaCityId=com.maan.services.util.runner.singleSelection("select city_id from city_master where city_name='"+request.getParameter("transit_via_city")+"' and country_id='"+viaCountryId+"'");

		request.setAttribute("frmCountry",fromCountryId);
		request.setAttribute("toCountry",toCountryId);
		request.setAttribute("viaCountry",viaCountryId);
		java.util.HashMap fullCommodity=null;
		fullCommodity=new java.util.HashMap();
		if(session.getAttribute("fullDetails")!=null){
			try{
				fullCommodity=(java.util.HashMap)session.getAttribute("fullDetails");
				String selectedCounts=null;
				selectedCounts=(String)fullCommodity.get("finalCount");
                for(int k=0;k<Integer.parseInt(selectedCounts);k++){
					if("51".equalsIgnoreCase((String)fullCommodity.get("commodityId"+(k+1)))){
						if(!"1".equalsIgnoreCase(modeOfTransport))
							error.append("<br><font color=red size=2><b>*"+com.maan.services.util.runner.singleSelection("select error_desc from  error_master where error_id=96")+"  </b></font><br>");	
						else if(!"2".equalsIgnoreCase(request.getParameter("cover")) && !"3".equalsIgnoreCase(request.getParameter("cover")))
							error.append("<br><font color=red size=2><b>*"+com.maan.services.util.runner.singleSelection("select error_desc from  error_master where error_id=97")+" </b></font><br>");	
					    }
				}
			}
			catch(Exception e){
				System.out.println("  ERROR WHEN FINDING  NEW COMMODITY CONCEPTS---->"+e.toString());
			}
		}
		/**Partial Shipment**/

		partialShip = request.getParameter("partialShip");
		partialShipmentAmt = request.getParameter("partialShipmentAmt")==null?"":request.getParameter("partialShipmentAmt");
		partialShip = partialShip == null ?"" : partialShip;
		if(partialShip.equalsIgnoreCase("on"))
			partialShip ="Y";
//		partialShipmentAmt = partialShipmentAmt ==null ?"0" :partialShipmentAmt;

		double partialAmtL=0.0;
		boolean ship=true;
		premiumTransaction.setPartialShip(partialShip);
		premiumTransaction.setPartialShipmentAmt(partialShipmentAmt);
		
		if(partialShip.equalsIgnoreCase("Y") || partialShip.equalsIgnoreCase("M")){
			ship = premiumTransaction.validPartialShipAmount(partialShipmentAmt);
			if(!ship)
				error.append("<font color=red size=2><br>* <b>Please Enter valid Shipment amount</b></font>");
			else if(partialShipmentAmt.equalsIgnoreCase(""))
				error.append("<font color=red size=2><br>* <b>Please Enter Shipment amount</b></font>");
			else if( partialShipmentAmt.length() > 0 && Double.parseDouble(partialShipmentAmt) == 0)
				error.append("<font color=red size=2><br>* <b>Shipment amount should not be zero.</b></font>");
			else
			{
				if(!"0".equalsIgnoreCase(exposureCurrency) && !"".equalsIgnoreCase(currencyValue)){
					partialAmtL=Double.parseDouble(partialShipmentAmt)*Double.parseDouble(exposureCurrencyValue);
					if( partialAmtL > (Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue))){
						error.append("<font color=red size=2><br>*<b>Shipment amount should not be greater than Total SumInsured</b></font>");
					}
				}
			}
			if("0".equalsIgnoreCase(exposureCurrency)){
				error.append("<font color=red size=2><br>* <b>Please Select Exposure Currency</b></font>");
			}
		}
		//com.maan.Home.Services.Utils.runner run = new com.maan.Home.Services.Utils.runner();

		/*if((agreeStatus.equalsIgnoreCase("No")||agreeStatus.length()<=0)&&!"admin".equalsIgnoreCase(user1))
			error.append("<font color=#cd0084 size=3><br>* <b>"+runner.getErrormsg("322")+"</b></font>");*/
		
		if(error.length()>0)
		{
			request.setAttribute("errorMessage",error);
			if("11".equalsIgnoreCase(productId))
			{
				request.setAttribute("actionPath","premium/QuotationOpen.jsp");
				dispatcher1=request.getRequestDispatcher(path+"/newquote.dos?applicationNo="+applicationNo+"&newModes=fresh");
				dispatcher1.include(request, response);
				dispatcher=request.getRequestDispatcher("/premium/QuotationOpen.jsp");
				dispatcher.forward(request, response);
			}
			else if ("3".equalsIgnoreCase(productId))
			{
				request.setAttribute("actionPath","premium/Quotation.jsp");
				dispatcher1=request.getRequestDispatcher(path+"/newquote.dos?applicationNo="+applicationNo+"&newModes=fresh");
				dispatcher1.include(request, response);
				dispatcher=request.getRequestDispatcher("/premium/Quotation.jsp");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			premiumTransaction.setCommodity(commodity);
			if(cover.equalsIgnoreCase("All")){
				cover="ALL RISKS";
			}
			else if(cover.equalsIgnoreCase("Basic")){
				cover="BASIC COVER";
			}
			else if(cover.equalsIgnoreCase("Total")){
				cover="TOTAL LOSS";
			}
			/*else if(cover==null || "".equals(cover) || "null".equals(cover)){				
				//cover="ICC(A)";				
			}else{				
				//cover="ICC(A)";				
			}*/
			out.println("the Nof o ITEMS"+noOfItems);			
			System.out.println("the Nof o WSRCC "+wsrcc);			
			/*if("YES".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==1){
				warStatus="1";
				wsrcc="";			
			}
			else if("YES".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==2){
				warStatus="2";
				wsrcc="";	
			}
			else if("YES".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==4){
				warStatus="4";
				wsrcc="";	
			}
			else{				
				warStatus="0";	
			}*/

			if("YES".equalsIgnoreCase(wsrcc)){
				warStatus=modeOfTransport;
				wsrcc="";			
			}else{				
				warStatus="0";	
			}
			
			premiumTransaction.setCover(cover);
			
			premiumTransaction.setCurrency(currency);
			premiumTransaction.setCurrencyValue(currencyValue);
			premiumTransaction.setExecutive(executive);
			premiumTransaction.setFinalDestination(finalDestination);
			premiumTransaction.setFinalVia(finalVia);
			premiumTransaction.setModeOfTransport(modeOfTransport);
			premiumTransaction.setNoOfItems(noOfItems);
			premiumTransaction.setTransitFrom(transitFrom);
			premiumTransaction.setPolicyDay(policyDay);
			premiumTransaction.setPolicyMonth(policyMonth);
			premiumTransaction.setPolicyYear(policyYear);
			premiumTransaction.setSaleTerm(saleTerm);
			premiumTransaction.setTolerance(tolerance);
			//For Open Cover Policy
			
			premiumTransaction.setVesselName(vesselName);
			premiumTransaction.setVesselOption(vesselOption);
			
			premiumTransaction.setBankName(bankName);
			premiumTransaction.setLcNumber(lcNumber);
			
			premiumTransaction.setWarehouse(warehouse);
			premiumTransaction.setWarehouse1(warehouse1);
			//premiumTransaction.setWsrcc(wsrcc);
			premiumTransaction.setWsrcc(warStatus);
			premiumTransaction.setTotalSumInsured(totalSumInsured);
			premiumTransaction.setFromCountryId(fromCountryId);
			premiumTransaction.setFromCityId(fromCityId);
			premiumTransaction.setToCountryId(toCountryId);
			premiumTransaction.setToCityId(toCityId);
			premiumTransaction.setViaCountryId(viaCountryId);
			premiumTransaction.setViaCityId(viaCityId);
			/** Partial Shipment **/
			premiumTransaction.setPartialShip(partialShip);
			premiumTransaction.setPartialShipmentAmt(partialShipmentAmt);
			premiumTransaction.setExposureCurrency(exposureCurrency);
			premiumTransaction.setVesselId(vesselId);
			/*if(partialShip.equalsIgnoreCase("Y"))
				ship = premiumTransaction.validPartialShipAmount(partialShipmentAmt);
	
			if(!ship)
				error.append("<font color=red size=2><br>* <b>Please Enter valid Partial Shipment amount</b></font>");
			else
			{
				if(partialShipmentAmt.equalsIgnoreCase(""))
					error.append("<font color=red size=2><br>* <b>Partial Shipment amount should not be Empty</b></font>");

				if( partialShip.equalsIgnoreCase("Y") && partialShipmentAmt.length() > 0 && Double.parseDouble(partialShipmentAmt) == 0)
					error.append("<font color=red size=2><br>* <b>Partial Shipment amount should not be zero.</b></font>");
			
				if( partialShipmentAmt.length() > 0 && (Double.parseDouble(partialShipmentAmt) > Double.parseDouble(totalSumInsured)) )
					error.append("<font color=red size=2><br>* <b>Partial Shipment amount should not be greater than Total SumInsured</b></font>");
			}*/
			
			if(error.length()>0)
			{
				request.setAttribute("errorMessage",error);
				if("11".equalsIgnoreCase(productId))
				{
					request.setAttribute("actionPath","premium/QuotationOpen.jsp");
					dispatcher1=request.getRequestDispatcher(path+"/newquote.dos");
					dispatcher1.include(request, response);
					dispatcher=request.getRequestDispatcher("/premium/QuotationOpen.jsp");
					dispatcher.forward(request, response);
				}
				else if ("3".equalsIgnoreCase(productId))
				{
					request.setAttribute("actionPath","premium/Quotation.jsp");
					dispatcher1=request.getRequestDispatcher(path+"/newquote.dos");
					dispatcher1.include(request, response);
					dispatcher=request.getRequestDispatcher("/premium/Quotation.jsp");
					dispatcher.forward(request, response);
				}
			}
			else
			{
				premiumTransaction.setApplicationNo(applicationNo);
				premiumTransaction.setSessionId(sessionId);
				premiumTransaction.setLoginCode(loginId);
				premiumTransaction.setCompanyId(companyId);
				premiumTransaction.setProductId(productId);
				premiumTransaction.setOpenCoverNo(openCoverNo);
				premiumTransaction.setStageId("2");
				String[][] openCoverMasterDatas=new String[0][0];
				if("11".equalsIgnoreCase(productId))
				{
					openCoverMasterDatas=premiumTransaction.getOpenCoverMasterDatas(openCoverNo);
					if(openCoverMasterDatas.length>0){
						openBrokerId=openCoverMasterDatas[0][0]==null?"":openCoverMasterDatas[0][0];//0
						print("openBrokerId",openBrokerId,"value");
						openBrokerUserId=openCoverMasterDatas[0][1]==null?"":openCoverMasterDatas[0][1];//1
						print("openBrokerUserId",openBrokerUserId,"value");
						openCoverStartDate=openCoverMasterDatas[0][2]==null?"":openCoverMasterDatas[0][2];//2
						print("openCoverStartDate",openCoverStartDate,"value");
						openCoverEndDate=openCoverMasterDatas[0][3]==null?"":openCoverMasterDatas[0][3];//3
						print("openCoverEndDate",openCoverEndDate,"value"); 
						openProductId=openCoverMasterDatas[0][4]==null?"":openCoverMasterDatas[0][4];//4
						print("openProductId",openProductId,"value"); 
						openCustomerId=openCoverMasterDatas[0][5]==null?"":openCoverMasterDatas[0][5];//5
						print("openCustomerId",openCustomerId,"value"); 
						openBranchCode=openCoverMasterDatas[0][6]==null?"":openCoverMasterDatas[0][6];//6
						print("openBranchCode",openBranchCode,"value"); 
						openEstimatedTurnOver=openCoverMasterDatas[0][7]==null?"":openCoverMasterDatas[0][7];//7
						print("openEstimatedTurnOver",openEstimatedTurnOver,"value"); 
						openCrossVoyageSumInsuredLimit=openCoverMasterDatas[0][8]==null?"":openCoverMasterDatas[0][8];//8
						print("openCrossVoyageSumInsuredLimit",openCrossVoyageSumInsuredLimit,"value"); 
						openBackDays=openCoverMasterDatas[0][9]==null?"":openCoverMasterDatas[0][9];//9
						print("openBackDays",openBackDays,"value"); 
						openCommission=openCoverMasterDatas[0][10]==null?"":openCoverMasterDatas[0][10];//10
						print("openCommission",openCommission,"value"); 
						openNoOfInsuranceCompany=openCoverMasterDatas[0][11]==null?"":openCoverMasterDatas[0][11];//11
						print("openNoOfInsuranceCompany",openNoOfInsuranceCompany,"value"); 
						openCrossVoyagePercentage=openCoverMasterDatas[0][12]==null?"":openCoverMasterDatas[0][12];//12
						print("openCrossVoyagePercentage",openCrossVoyagePercentage,"value"); 
						openRsaSharedPercentage=openCoverMasterDatas[0][13]==null?"":openCoverMasterDatas[0][13];//13
						print("openRsaSharedPercentage",openRsaSharedPercentage,"value"); 
						openCrossVoyage=openCoverMasterDatas[0][14]==null?"":openCoverMasterDatas[0][14];//14
						print("openCrossVoyage",openCrossVoyage,"value");
						openMinimumPremium=openCoverMasterDatas[0][15]==null?"0":openCoverMasterDatas[0][15];//14
						print("openMinPremium Amount",openMinimumPremium,"value");
					}
				}
				System.out.println("royal test user attribute from premium controller...."+user);
				System.out.println("royal test user1 attribute from premium controller...."+user1);
				String[][] commoditys=new String[0][0];
				if("admin".equalsIgnoreCase(user1)){
					commoditys=premiumTransaction.getCommoditysForAdmin(applicationNo,user);
				}
				System.out.println("royal test preCtrlr after if admin commoditys length..."+commoditys.length);
				double curr_rate=0.0;
				double convertedTotal=0.0;
				double exchangeRateSI=1;
				String totalSILimit="0";
				String[][] LimitedData=new String[0][0];
				//Block For SumInsured Referral
				if(!"admin".equalsIgnoreCase(user1)&&suninsuredRef)
				{
					if("3".equalsIgnoreCase(productId))
					{
						try{
							LimitedData=premiumTransaction.getLimitedData(userName,productId);
						}
						catch(Exception e){
							e.printStackTrace();
						}
					}
					else if("11".equalsIgnoreCase(productId)){
						LimitedData=premiumTransaction.getOpenCoverDetailDatas(modeOfTransport);
						String[][] currencyInfo=premiumTransaction.getCurrencyRates(LimitedData[0][3], cid);
						if(currencyInfo!=null && currencyInfo.length>0)
						{
							exchangeRateSI=Double.parseDouble(currencyInfo[0][0]==null?"":currencyInfo[0][0]);
						}
					}
					
					System.out.println("......Sum insured Block is......"); //Partail Shipment
					if(LimitedData.length > 0)
					{
						totalSILimit=LimitedData[0][0]==null?"0":LimitedData[0][0];
						System.out.println("the Currency Rate is "+currencyValue);
						System.out.println("the totalSILimit  is "+totalSILimit);
						System.out.println("the totalSumInsured  is "+totalSumInsured);
						convertedTotal=Double.parseDouble(totalSILimit)*exchangeRateSI;
						//convertedTotal=Double.parseDouble(totalSILimit);
						double ConvertedSumIns = 0.0;
						if((partialShip.equalsIgnoreCase("Y") || partialShip.equalsIgnoreCase("M")) && partialShipmentAmt.length() > 0 && productId.equalsIgnoreCase("11")){
							ConvertedSumIns=partialAmtL;
						}else{
							ConvertedSumIns = premiumTransaction.getConvertedTotalSumInsured(saleTerm,tolerance,totalSumInsured,currencyValue);
						}
						System.out.println("convertedTotal==>"+convertedTotal+" partialShipmentAmt==>"+partialShipmentAmt+ " exposureCurrency==>"+exposureCurrency+" partialAmtL: "+partialAmtL);
						//if(LimitedData.length > 0 && Double.parseDouble(totalSumInsured) > convertedTotal)
						if(LimitedData.length > 0 && ConvertedSumIns > convertedTotal)
						{
							if((partialShip.equalsIgnoreCase("Y") || partialShip.equalsIgnoreCase("M")) && partialShipmentAmt.length() > 0 && productId.equalsIgnoreCase("3"))
							{
								double ConvertedPartialSI = 0.0;
								ConvertedPartialSI = premiumTransaction.getConvertedTotalSumInsured(saleTerm,tolerance,partialShipmentAmt,currencyValue);
								System.out.println("partialShipmentAmt"+partialShipmentAmt);
								System.out.println("totalSILimit"+totalSILimit);
								//if( Double.parseDouble(partialShipmentAmt) > Double.parseDouble(totalSILimit)) //convertedTotal
								System.out.println(" .....convertedPartialTotal "+ConvertedPartialSI);
								System.out.println(" .....convertedTotal "+convertedTotal);
								//if( Double.parseDouble(partialShipmentAmt) > convertedTotal) //convertedTotal
								if( ConvertedPartialSI > convertedTotal) //convertedTotal
									request.setAttribute("sumInsuredReferal","Referal");
								else
									request.setAttribute("sumInsuredReferal","Not Referal Case");
							}
							else
							   request.setAttribute("sumInsuredReferal","Referal");
						}
						else
						{
							request.setAttribute("sumInsuredReferal","Not Referal Case");
						}
					}
					else{
						request.setAttribute("sumInsuredReferal","Not Referal Case");
					}
				}
				else
				{
					request.setAttribute("sumInsuredReferal","Not Referal Case");
				}
				
				/*String seasOptionValue="";
				out.println("<------OUTSIDE modeOfTransport OF PREMIUM @@@@@@------>"+modeOfTransport);
				//if(Integer.parseInt(modeOfTransport)==1){
					if("3".equalsIgnoreCase(productId)){
						if(request.getParameter("seasOption")!=null)
							seasOptionValue=request.getParameter("seasOption");
						else if(request.getParameter("seasOption2")!=null)
							seasOptionValue=request.getParameter("seasOption2");
						else if(request.getParameter("seasOption3")!=null)
							seasOptionValue=request.getParameter("seasOption3");
						else if(request.getParameter("seasOption4")!=null)
							seasOptionValue=request.getParameter("seasOption4");
					}
					else if ("11".equalsIgnoreCase(productId)){
						String[] seasOptionArray=new String[0];					
					if(request.getParameterValues("seasOption")!=null){
						seasOptionArray=request.getParameterValues("seasOption");
						System.out.println("the seasOptionArray Length is "+seasOptionArray.length);
						if(seasOptionArray.length > 0){
								for(int i=0;i<seasOptionArray.length;i++){
									System.out.println("<br>the Sea Option Array is"+i+"VAlues---"+seasOptionArray[i]);
									if("".equalsIgnoreCase(seasOptionArray[i]) || " ".equalsIgnoreCase(seasOptionArray[i] )){

									}
									else{
										seasOptionValue=seasOptionArray[i];
									}
								}
							}
						}
					}
					premiumTransaction.setSeaOption(seasOptionValue);
					request.setAttribute("seasOptionValue",seasOptionValue);
					System.out.println("-------INSIDE CONTROLLER OF PREMIUM @@@@@@------>"+seasOptionValue);
					if(!"admin".equalsIgnoreCase(user1))
					{
						seasOptionValue = findSeasReferal(seasOptionValue,productId,brokerBra);
						request.setAttribute("seasReferal",seasOptionValue);
					}*/
				/*}
				else
				{
					premiumTransaction.setSeaOption(seasOptionValue);
				}*/
				//Block For Coverages Referral
				String masg = getCoveragesReferralStatus(cover,brokerBra);
				if(masg.length()>0)
				{
					request.setAttribute("CoveragesReferral","Referal");
				}
				//
				//Rajesh For Freight Frowarder			
				
				premiumTransaction.setAgreeStatus(agreeStatus);
		
				premiumTransaction.setPartialShip(partialShip);
				premiumTransaction.setPartialShipmentAmt(partialShipmentAmt);
				System.out.println("Sum Check partialShip"+partialShip);
				System.out.println("Sum Check partialShipmentAmt"+partialShipmentAmt);
				System.out.println(" seasOptionValue    "+seasOptionValue+"  modeOfTransport   "+modeOfTransport);
				System.out.println("Before add marine tables APPLICATION NO is..."+applicationNo);
				/** For GHQ operation*/
				
				String userType = (session.getAttribute("user")==null)?"":session.getAttribute("user").toString();
				/**  * */
				premiumTransaction.insertOrUpdateMarineInfo(commoditys,freight,freightUser,userType);
				String actualBranch = (String)session.getAttribute("AdminBranchCode");
				premiumTransaction.calculateTax(user1,loginId,applicationNo,actualBranch,(String)request.getAttribute("minPremium"));
				
				if(nextMissing.equalsIgnoreCase("proceedToMissing"))
				{
					response.sendRedirect("../missing.jsp");
				}
				else{
						String referalCase=null;			
						//Block for Policy Date Referral
						if(!"admin".equalsIgnoreCase(user1)&&policyDateRef)
						{	
							referalCase=findReferalDate(policyDay,policyMonth,policyYear,productId,openBackDays,brokerBra,(String)session.getAttribute("user"), openCoverNo);
							request.setAttribute("policyDateReferal",referalCase);
						}
						
						System.out.println(" ****************** FIND ROAD REFERAL *****************");
						System.out.println("modeOfTransport   "+modeOfTransport);
						System.out.println("fromCountryId   "+fromCountryId);
						System.out.println("toCountryId   "+toCountryId);
						System.out.println(" ****************** FIND ROAD REFERAL *****************");
						//Block for Road and Same country Referral
						if(!"admin".equalsIgnoreCase(user1))
						{
							System.out.println(" ****************** FIND ROAD REFERAL *****************...defaultRoadRef..."+defaultRoadRef);
							if(defaultRoadRef)
							{
								if(Integer.parseInt(modeOfTransport)==3)
								{
									referalCase="Referal";
								}
								else
									referalCase="Not Referal Case";
							}
							else
							{
								if(Integer.parseInt(modeOfTransport)==3&&roadRef)
								{
									referalCase=findRoadReferal(fromCountryId,toCountryId,cid, brokerBra);
								}
								else
									referalCase="Not Referal Case";
							}

							request.setAttribute("roadReferal",referalCase);

							if(Integer.parseInt(modeOfTransport)!=3&&samecountryRef)
							{
								referalCase=findSameCountryReferal(fromCountryId,toCountryId);
								request.setAttribute("sameCountryReferal",referalCase);

							}		
						}	
						//Controller Changes Work
						HashMap commodityTypes = new HashMap();
						commodityTypes = premiumTransaction.getCommodityType(brokerBra);
						request.setAttribute("commodityTypes",commodityTypes);
						premiumLogic.setLoginCode(loginId);
						HashMap PremiumDetailsHash = premiumLogic.getPremiumDetails(applicationNo);
						request.setAttribute("fullDetails",PremiumDetailsHash);
						dispatcher=request.getRequestDispatcher("/premium/Premium.jsp");
						dispatcher.forward(request, response);
						
				}
			}
		}
		out.flush();
		out.close();
	}


	//Rajesh World work stared
	public String findDubai(String transitFrom,String transitFinal,String cids, String productId)
	{
		System.out.println(" findDubai transitFrom  "+transitFrom+"--transitFinal  "+transitFinal);
		String countryStatus=null;
		if(cids.equalsIgnoreCase(transitFrom) || cids.equalsIgnoreCase(transitFinal) || "11".equals(productId))
			countryStatus="valid";
		else
			countryStatus="invalid";
		
		if(countryStatus.equalsIgnoreCase("valid"))
		{
			String countryRef[][] = new String[0][0];
			countryRef = com.maan.services.util.runner.multipleSelection("select status from COUNTRY_MASTER_DETAIL where COUNTRY_ID in('"+transitFrom+"','"+transitFinal+"') and amend_id||COUNTRY_ID in(select max(amend_id||COUNTRY_ID) from COUNTRY_MASTER_DETAIL where COUNTRY_ID in('"+transitFrom+"','"+transitFinal+"') and BELONGING_COUNTRY_ID='"+cids+"' group by COUNTRY_ID) and BELONGING_COUNTRY_ID='"+cids+"'");
			
			if(countryRef.length>0)
			{
				if(countryRef.length>1)
				{
					if("R".equalsIgnoreCase(countryRef[0][0]))
						countryStatus="invalid";
					else if("R".equalsIgnoreCase(countryRef[1][0]))
						countryStatus="invalid";
				}
				else if(countryRef.length==1)
				{
					if("R".equalsIgnoreCase(countryRef[0][0]))
						countryStatus="invalid";
				}
			}
		}
		System.out.println("Senthil Country_referal..."+countryStatus);
		return countryStatus;
	}

	//public String findDubaiOpen(String transitFrom,String transitFinal,String crossVoyage)
	public String findDubaiOpen(String transitFrom,String transitFinal,String crossVoyage,String crossVoyageLimit,String openCoverNo,String cids, String totalSumInsured)
	{
		System.out.println(" findDubai transitFrom OPEN "+transitFrom+"--transitFinal  "+transitFinal);
		String countryStatus=null;
		String crossVoyagesSI="0"; 
		
		if(cids.equalsIgnoreCase(transitFrom) || cids.equalsIgnoreCase(transitFinal) ||"Y".equalsIgnoreCase(crossVoyage))	
		{
			if(!cids.equalsIgnoreCase(transitFrom) && !cids.equalsIgnoreCase(transitFinal))
			{
				
//				crossVoyagesSI = com.maan.services.util.runner.singleSelection("select sum(mrd.suminsuredlocal) from marine_data md,position_master pm,marine_result_details mrd,OPEN_COVER_POSITION_MASTER ocpm where md.application_no=pm.application_no and mrd.application_no=md.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.transit_from_country_id not in('"+cids+"') and md.final_destination_country_id not in('"+cids+"') and md.open_cover_no='"+openCoverNo+"' and ocpm.open_cover_no=pm.open_cover_no and pm.inception_date>=(select case when ocpm.expiry_Date-ocpm.INCEPTION_DATE>365 and add_months(ocpm.INCEPTION_DATE,12)<sysdate then (case when (ocpm.expiry_Date-ocpm.INCEPTION_DATE)/365>1 then (ocpm.expiry_Date-(floor((ocpm.expiry_Date-ocpm.INCEPTION_DATE)/365))*365) else ocpm.expiry_Date end)-(mod(ocpm.expiry_Date-ocpm.INCEPTION_DATE,365)) else ocpm.INCEPTION_DATE end from OPEN_COVER_POSITION_MASTER ocpm where ocpm.open_cover_no='"+openCoverNo+"')");
				crossVoyagesSI = com.maan.services.util.runner.singleSelection("select sum(mrd.suminsuredlocal) from marine_data md,position_master pm,marine_result_details mrd,OPEN_COVER_POSITION_MASTER ocpm where md.application_no=pm.application_no and mrd.application_no=md.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.transit_from_country_id not in('"+cids+"') and md.final_destination_country_id not in('"+cids+"') and md.open_cover_no='"+openCoverNo+"' and ocpm.open_cover_no=pm.open_cover_no");
 				crossVoyagesSI=crossVoyagesSI==null?"0":crossVoyagesSI;
				if(Double.parseDouble(crossVoyagesSI)+Double.parseDouble(new PremiumInputsBean().getRoundedValue(totalSumInsured, "1",""))> Double.parseDouble(crossVoyageLimit)){
					countryStatus="invalidCrossVoyageSIReferal";
				}
				else{
						countryStatus="valid";
					}
			}
			else{
				countryStatus="valid";
			}
		}
		else{
			if("N".equalsIgnoreCase(crossVoyage)){
				countryStatus="invalidCrossVoyageCountryReferal";
			}
			else{
				countryStatus="invalid";
			}
		}
		// MOC Country Referral - Restricted FOR AOIC
		/*if( countryStatus.equalsIgnoreCase("valid") && !cids.equalsIgnoreCase("14") && !cids.equalsIgnoreCase("153") )
		{
			String countryRef[][] = new String[0][0];
			countryRef = com.maan.services.util.runner.multipleSelection("select status from COUNTRY_MASTER_DETAIL where COUNTRY_ID in('"+transitFrom+"','"+transitFinal+"') and amend_id||COUNTRY_ID in(select max(amend_id||COUNTRY_ID) from COUNTRY_MASTER_DETAIL where COUNTRY_ID in('"+transitFrom+"','"+transitFinal+"') and BELONGING_COUNTRY_ID='"+cids+"' group by COUNTRY_ID) and BELONGING_COUNTRY_ID='"+cids+"'");
			
			if(countryRef.length>0)
			{
				if(countryRef.length>1)
				{
					if("R".equalsIgnoreCase(countryRef[0][0]))
						countryStatus="invalid";
					else if("R".equalsIgnoreCase(countryRef[1][0]))
						countryStatus="invalid";
				}
				else if(countryRef.length==1)
				{
					if("R".equalsIgnoreCase(countryRef[0][0]))
						countryStatus="invalid";
				}
			}
		}*/
		return countryStatus;
	}


	/*public String findRoadReferal(String mode,String branch)
	{
		String sql = "Select status from MODE_OF_TRANSPORT where MODE_TRANSPORT_ID=? and branch_code=?";
		String status = "";
		String res = "";
		try
		{
			String args[] = new String[2];
			args[0] = mode;
			args[0] = branch;
			status = runner.singleSelection(sql,args);
			if(status.equalsIgnoreCase("R"))
				res = "Referal";
			else
				res = "Not Referal Case";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}*/
	
	public String findRoadReferal(String fromCountryId,String toCountryId,String cids)
	{
		String referal=null;
		try{
					
				if((Integer.parseInt(fromCountryId)==Integer.parseInt(cids)) && (Integer.parseInt(toCountryId)==Integer.parseInt(cids)))
					referal="Not Referal Case";
				else
					referal="Referal";
		}
		catch(Exception e){
			System.out.println("ERROR in find Road Referal()   "+e.toString());
		}
		return referal;
	}
	public String findRoadReferal(String fromCountryId,String toCountryId,String cids, String branchCode)
	{
		String referal=null;
		boolean refStatus=false, fromNonGulf=false, toNonGulf=false;
		try{
			PremiumInputsBean bean=new PremiumInputsBean();
			fromNonGulf=bean.getLandReferralForNonGCC(fromCountryId, branchCode);
			toNonGulf=bean.getLandReferralForNonGCC(toCountryId, branchCode);
			if(fromNonGulf || toNonGulf)
			{
				refStatus=true;
			}
			if(!refStatus)	
				referal="Not Referal Case";
			else
				referal="Referal";
		}
		catch(Exception e){
			System.out.println("ERROR in find Road Referal()   "+e.toString());
		}
		return referal;
	}

	public String findRoadReferalOpen(String fromCountryId,String toCountryId,String cids)
	{
		String referal=null;
		try{
			if((Integer.parseInt(fromCountryId)==Integer.parseInt(cids)) && (Integer.parseInt(toCountryId)==Integer.parseInt(cids)))
				referal="Not Referal Case";
			else
				referal="Referal";
		}
		catch(Exception e){
			System.out.println("ERROR in findRoad ReferalOPEN()   "+e.toString());
		}
		return referal;
	}
	
	public StringBuffer checkInputs(String inputName,String inputDescription,String inputErrorId)
	{
		StringBuffer error = new StringBuffer();
		PremiumInputsBean premiumTransaction=new PremiumInputsBean();
		if(" ".equalsIgnoreCase(inputName) || "".equalsIgnoreCase(inputName) || "0".equalsIgnoreCase(inputName) || null==(inputName) || "null".equalsIgnoreCase(inputName) || "select".equalsIgnoreCase(inputName) || "DD".equalsIgnoreCase(inputName) || "MM".equalsIgnoreCase(inputName) || "YYYY".equalsIgnoreCase(inputName)){
			if(inputDescription.equalsIgnoreCase("TO COUNTRY")){
				error.append("<br><font color=red size=2><b>* Sorry !- Please Kindly Choose FINAL DESTINATION ONCE AGAIN</b></font><br>");	
			}
			else if(inputDescription.equalsIgnoreCase("FROM COUNTRY")){
				error.append("<br><font color=red size=2><b>* Sorry !- Please Kindly Choose TRANSIT FROM ONCE AGAIN</b></font><br>");
			} 
			else{
				error.append("<br><font color=red size=2><b>* "+premiumTransaction.getErrormsg(inputErrorId,inputDescription)+"</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("suminsured")){
			error.append("<br><font color=red size=2><b>* "+premiumTransaction.getErrormsg(inputErrorId,inputDescription)+"</b></font><br>");
		}

		if(inputDescription.equalsIgnoreCase("DATECHECK")){
			if("Invalid".equalsIgnoreCase(checkDate(inputName))){
			    error.append("<br><font color=red size=2><b>* "+premiumTransaction.getErrormsg(inputErrorId,inputDescription)+"</b></font><br>");
		     }
 	    }
		if(inputDescription.equalsIgnoreCase("BackDates")){
			error.append("<br><font color=red size=2><b>* "+inputName+"</b></font><br>");
		}
		/*else if(inputName.equalsIgnoreCase("totalSumInsured") )
		{
			System.out.println("comes Hre TOTAL SUM");
			try{
				Long.parseLong(inputName);
				//error.append("<br><font color=red size=2><b>* Please Provide Input for ---"+inputDescription
				//+"</b></font><br>");
			}
			catch(Exception exception){
				error.append("<br><font color=red size=2><b>* Please Provide VALID INPUT for ---"+inputDescription
						+"</b></font><br>");	
			}
		}*/
		return error;
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
	}

	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}
	
	public String checkDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MMM/yyyy");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);
		java.util.Date date = df.parse(strDate, pos);
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			System.out.println("Error: " + pos.getIndex());
			if (date == null) {
				System.out.println("Date is null");
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				return "Invalid";
			}	
			return "Invalid";
		}
		return returnVal;
	}
	
	public String findReferalDate(String Days,String months,String years,String productId,String openBackDays,String branch,String loginId, String openCoverNo)
	{
		String result=null;
		String resultConvertedDate="";
		String backDays="0";
		String hourSQL = "";
		String hour = "";
		String args[] = new String[0];
		PremiumInputsBean prem = new PremiumInputsBean();
		try
		{
			/** Get Hours To Add Sysdate**/
				args = new String[4];
				args[0] = "62";
				args[1] = "1";
				args[2] = "Y";
				args[3] = branch;
				hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?";
				 hour= runner.singleSelection(hourSQL,args);
				if(hour.length() > 0)
					hour = "sysdate" + "+" + hour;
				else
					hour = "sysdate";
			/** Get Hours To Add Sysdate**/

			if("3".equalsIgnoreCase(productId))	{ // One Off Policy - Policy date Referral....
				backDays = prem.getBackDatesAllowed(loginId,productId);
			}
			else if("11".equalsIgnoreCase(productId)){
				backDays=openBackDays;
			}
			result = runner.singleSelection("select to_date("+hour+",'DD-MM-YYYY')-to_date('"+Days+"/"+months+"/"+years+"','DD-MM-YYYY') from dual");
			
			if(Double.parseDouble(result) > Integer.parseInt(backDays))
				result="Referal";
			else
				result="Not Referal Case";
			if(!"Referal".equalsIgnoreCase(result) && "11".equalsIgnoreCase(productId))
			{
				String[][] diff = runner.multipleSelection("SELECT TO_DATE(A.INCEPTION_DATE,'DD-MM-YYYY')-TO_DATE('"+Days+"/"+months+"/"+years+"','DD-MM-YYYY'),TO_DATE('"+Days+"/"+months+"/"+years+"','DD-MM-YYYY')-TO_DATE(A.EXPIRY_DATE,'DD-MM-YYYY') FROM OPEN_COVER_POSITION_MASTER A WHERE A.OPEN_COVER_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_POSITION_MASTER WHERE A.OPEN_COVER_NO=OPEN_COVER_NO)", new String[]{openCoverNo});
				if(diff!=null && diff.length>0){
					if(Integer.parseInt(diff[0][0])>0 || Integer.parseInt(diff[0][1])>0)
						result="Referal";
					else
						result="Not Referal Case";
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}	
	
	public String findSameCountryReferal(String fromCountry,String toCountry)
	{
		String referals=null;
		System.out.println("fromCountry  "+fromCountry+"  toCountry  "+toCountry);
		try
		{
			if(Integer.parseInt(fromCountry)==Integer.parseInt(toCountry))
				referals="Referal";
			else
				referals="Not Referal Case";
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return referals;
	}
	
	public String findSeasReferal(String seasOption,String productId,String loginBra)
	{
		String referals="";
		String args[] = new String[2];
		String sql = "";
		try
		{
			if(seasOption.length() > 0 )
				args[0] = seasOption.toLowerCase();
			else
				args[0] = seasOption;

			args[1] = loginBra;
			sql = "select status from REFERAL_MASTER where lower(REFERAL_NAME)=? and branch_code=?";
			referals = runner.singleSelection(sql,args);
			
			if("3".equalsIgnoreCase(productId))
			{				
				if("Y".equalsIgnoreCase(referals))
					referals="Referal";
				else
					referals="Not Referal Case";
			}
			else if("11".equalsIgnoreCase(productId)){
				referals="Not Referal Case";
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return referals;
	}

	public String getCoveragesReferralStatus(String covers,String loginBra)
	{
		String referals="";
		String res="";
		String args[] = new String[2];
		String sql = "";
		try
		{
			sql = "select REFERAL_STATUS from COVER_MASTER where COVER_ID=? and branch_code=?";
			args[0] = covers;
			args[1] = loginBra;
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception is getCoverageReferalStatus .."+e);
			e.printStackTrace();
		}
		if("Y".equalsIgnoreCase(res))
			referals="Referal";
		return 	referals;	
	}
	public String getVesselReferralStatus(String vesselName)
	{
		String result="";
		try
		{
			result = runner.singleSelection("SELECT STATUS FROM VESSEL_MASTER WHERE VESSEL_NAME=?",new String[]{vesselName});
		}
		catch(Exception e)
		{
			System.out.println("Exception is getVesselReferralStatus .."+e);
		}
		return result;	
	}
	public void print(String methodName,String information,String type)
	{
		System.out.println(methodName+"<--OPEN COVER-->"+type+"<--OPEN COVER-->"+information);
	}
	public String getSaleTermId(String saleTerm, String percent, String branchCode)
	{
		logger.info("getSaleTermId - Enter || saleTerm: "+saleTerm+" percent: "+percent+" branchCode: "+branchCode);
		saleTerm=(saleTerm==null?"":saleTerm)+(percent==null || percent.length()<=0?"":"+"+percent);
		String result="";
		try{
			result = runner.singleSelection("SELECT SALE_TERM_ID FROM SALE_TERM_MASTER WHERE SALE_TERM_NAME=? AND BRANCH_CODE=?",new String[]{saleTerm,branchCode});
		}
		catch(Exception e){System.out.println("Exception in getSaleTermId .."+e);}
		logger.info("getSaleTermId - Enter || Result: "+result);
		return result;	
	}
} // Class
