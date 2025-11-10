package com.maan.premium.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.premium.DAO.PremiumInputsBean1;
import com.maan.services.util.runner;

public class referalModificationController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private RequestDispatcher dispatcher;
	
	private StringBuffer error=new StringBuffer();
	
	PremiumInputsBean1 premiumTransaction=new PremiumInputsBean1();

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		HttpSession session=null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		session=request.getSession(true);
		if(session.getAttribute("ses")==null){
			response.sendRedirect("login/error_messg.jsp");	
			return;
		}
		String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		com.maan.DBCon.DBConnectionStatus.statusStatic	=	usrModeController;
		System.out.println("RoyalTest for databese mode checking in referalModificationController.."+usrModeController);
		if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController)){
			response.sendRedirect("login/error_messg.jsp");	
			return;
		}
		
		String path	=	request.getRequestURI();
		path	=	path.substring(0,path.lastIndexOf("/")+1);
		
		String modeOfTransport  = "";
		String warehouse        = "";
		String warehouse1       = "";
		String transitFrom      = "";
		String finalDestination ="";
		String commodity        = "";
		String noOfItems        = "";
		String totalSumInsured  = "";
		String currency         = "";
		String currencyValue    = "";
		String policyDay        = "";
		String policyMonth      = "";
		String policyYear       = "";
		String saleTerm         = "";
		String tolerance         = "";
		String cover            = "";
		String wsrcc            = "";
		String executive        = "";
		String fromCountryId    = "0";
		String toCountryId      = "0";
		
		String fromCityId       = "0";
		String toCityId         = "0";
		
		String productId	 =	"";
		String loginId		 =	"";
		String companyId	 =	"";
		String sessionId	 =	"";
		String applicationNo =	"";
		String warStatus	 =	"";
		String nextMissing	 =	"NOTHING";
		
		nextMissing		=	request.getParameter("next")==null?nextMissing:request.getParameter("next").equals("")?nextMissing:request.getParameter("next");
		productId		=	request.getParameter("productId")==null?(String)session.getAttribute("product_id"):request.getParameter("productId");
		loginId			=	request.getParameter("loginId")==null?"":request.getParameter("loginId");
		companyId		=	request.getParameter("companyId")==null?"111":request.getParameter("companyId").equals("")?"111":request.getParameter("companyId");
		sessionId		=	request.getParameter("sessionId")==null?"":request.getParameter("sessionId");
		applicationNo	=	request.getParameter("applicationNo")==null?((String)session.getAttribute("applicationNo")==null?applicationNo:(String)session.getAttribute("applicationNo")):request.getParameter("applicationNo").equals("")?((String)session.getAttribute("applicationNo")==null?applicationNo:(String)session.getAttribute("applicationNo")):request.getParameter("applicationNo");
		
		error			=	new StringBuffer();
		modeOfTransport	=	request.getParameter("modeOfTransport");
		error			=	checkInputs(modeOfTransport,"MODE OF TRANSPORT");
		warehouse		=	request.getParameter("warehouse");
		warehouse1		=	request.getParameter("warehouse1");
		error			=	checkInputs(warehouse,"WAREHOUSE");
		transitFrom		=	request.getParameter("transitFrom");
		request.setAttribute("transitFrom",transitFrom);
		error			=	checkInputs(transitFrom,"TRANSIT STARTING PLACE");
		finalDestination=	request.getParameter("finalDestination");
		request.setAttribute("finalDestination",finalDestination);
		error			=	checkInputs(finalDestination,"FINAL DESTINATION");
		commodity		=	request.getParameter("commodity");
		error			=	checkInputs(commodity,"COMMODITY");
		noOfItems		=	request.getParameter("noOfItems");
		error			=	checkInputs(noOfItems,"NO OF ITEMS");
		totalSumInsured	=	request.getParameter("totalSumInsured");
		
		if(request.getParameter("loginId")==null)
			return;
		
		String origin ="";
		String destination ="";

		origin = request.getParameter("transit_from_country");
		destination = request.getParameter("transit_final_country");
		
		out.println("origin  "+ origin);
		out.println("destination  "+ destination); 
		origin = origin == null ? "" : origin;
		destination = destination == null ? "" : destination;   
		
		//rajesh world work stated
		String currencyType = "";
		String brokerBra = (String)session.getAttribute("LoginBranchCode");
		HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		if(brokerDetails.size()>0)
		{
			cid = (String)brokerDetails.get("Orgination");
			dcid = (String)brokerDetails.get("Destination");
			currencyType = (String)brokerDetails.get("CurrencyAbb");
		}
	//End

		String comExcesPremOption="Y";
		/*if("3".equalsIgnoreCase(productId))
		{
			if(modeOfTransport.equalsIgnoreCase("1") || modeOfTransport.equalsIgnoreCase("2") )
			{
				if( origin.equalsIgnoreCase(cid) && !destination.equalsIgnoreCase(dcid) )
					comExcesPremOption = "Y";
				else
					comExcesPremOption="N";
			}
			else
				comExcesPremOption="N";
		}*/
		request.setAttribute("comExcesPremOption",comExcesPremOption);
		String[][] minPrem=new String[0][0];
		if("11".equalsIgnoreCase(productId))
		{
				String openCoverNo="";
				/*String origin = request.getParameter("transit_from_country");
				String destination = request.getParameter("transit_final_country");*/
				System.out.println("Referal Modification Controller In Admin side");
				System.out.println("modeOfTransport"+modeOfTransport);
				System.out.println("origin"+origin);
				System.out.println("destination"+destination);
				openCoverNo=premiumTransaction.getOpenCover(applicationNo);
				if(modeOfTransport.equalsIgnoreCase("1") || modeOfTransport.equalsIgnoreCase("2") )
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
				//For Freight Forwarder Rajesh
				minPrem = premiumTransaction.getMinPremiumFreightBroker(applicationNo);
				if(minPrem.length<=0)
					minPrem = premiumTransaction.getMinPrem(request.getParameter("loginId"),productId);
		}
		if(minPrem.length > 0){
			if("11".equalsIgnoreCase(productId)){
					request.setAttribute("minPremium",(minPrem[0][15]==null?"0":minPrem[0][15]));
			}
			else if ("3".equalsIgnoreCase(productId)){
					request.setAttribute("minPremium",(minPrem[0][1]==null?"0":minPrem[0][1]));
			}
		}
		else{
			request.setAttribute("minPremium","0");
		}
		
		if ("3".equalsIgnoreCase(productId)){
			String[][] LimitedData=premiumTransaction.getLimitedData(request.getParameter("loginId"));
			if(LimitedData!=null && LimitedData.length>0){
				if(LimitedData.length>0&&Double.parseDouble(totalSumInsured)>Double.parseDouble(LimitedData[0][0])){
					
					error=checkInputs(totalSumInsured,"suminsured");
				}
			}
		}
	
		error			=	checkInputs(totalSumInsured,"TOTAL SUM INSURED");
		currency		=	request.getParameter("currency");
		error			=	checkInputs(currency,"CURRENCY");
		currencyValue	=	request.getParameter("currencyValue");
		error			=	checkInputs(currencyValue,"CURRENCY VALUE");
		policyDay		=	request.getParameter("policyDay");
		error			=	checkInputs(policyDay,"POLICY DAY");
		policyMonth		=	request.getParameter("policyMonth");
		error			=	checkInputs(policyMonth,"POLICY MONTH");
		policyYear		=	request.getParameter("policyYear");
		error			=	checkInputs(policyYear,"POLICY YEAR");
		error			=	checkInputs((policyDay+"/"+policyMonth+"/"+policyYear),"DATECHECK");
		saleTerm		=	request.getParameter("saleTerm");
		tolerance		=	request.getParameter("tolerance")==null?tolerance:request.getParameter("tolerance");
		error			=	checkInputs(saleTerm,"SALE TERM");
		cover			=	request.getParameter("cover");
		error			=	checkInputs(cover,"COVERAGES");
		wsrcc			=	request.getParameter("wsrcc");
		System.out.println("the WSRCC ISSS--"+wsrcc);
	
		executive		=	request.getParameter("executive");		
		error			=	checkInputs(executive,"A/C Executive");
		try{
				if(request.getParameter("referal_status")==null)
						error.append("<br><font color=red size=2><b>*Select Referral Status</b></font><br>");	
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{
			if("discount".equalsIgnoreCase(request.getParameter("adminDiscount")) || "loading".equalsIgnoreCase(request.getParameter("adminDiscount"))){
			}
			else if(request.getParameter("percentageValue")!=null && "select".equalsIgnoreCase(request.getParameter("adminDiscount")))
			     error.append("<br><font color=red size=2><b>*Select Discount/Loading Option</b></font><br>");
		}
		catch(Exception e){
			error.append("<br><font color=red size=2><b>*Invalid Percentage</b></font><br>");	
		}	
		
		String validMsg=null;
		validMsg=findDubai(request.getParameter("transit_from_country"),request.getParameter("transit_final_country"));
		
		fromCountryId =	request.getParameter("transit_from_country");
		fromCityId	  =	runner.singleSelection("select distinct(city_id) from city_master where city_name='"+request.getParameter("transit_from_city")+"' and country_id='"+fromCountryId+"'");
		toCountryId	  = request.getParameter("transit_final_country");
		toCityId	  = runner.singleSelection("select distinct(city_id) from city_master where city_name='"+request.getParameter("transit_final_city")+"' and country_id='"+toCountryId+"'");
		
		String quoteStatus=request.getParameter("quoteStatus")==null?"":request.getParameter("quoteStatus");
		
		request.setAttribute("frmCountry",fromCountryId);
		request.setAttribute("toCountry",toCountryId);
		System.out.println("<br> productId       "+productId);
		System.out.println("<br> from session  ---"+(String)session.getAttribute("productId"));
		System.out.println("<br> applicationNo       "+applicationNo);
		
		String[][] commoditys=null;
		commoditys=new String[0][5];
		if(request.getParameter("totalCommodity")!=null)
		{
			commoditys=new String[Integer.parseInt(request.getParameter("totalCommodity"))][7];
			try{
				for(int i=0;i<Integer.parseInt(request.getParameter("totalCommodity"));i++){
						try{
							commoditys[i][0]=request.getParameter("commodityId"+i);
							commoditys[i][1]=request.getParameter("commodityRate"+i);
							commoditys[i][2]=request.getParameter("warRate"+i)==null?"0":request.getParameter("warRate"+i);
							commoditys[i][3]=request.getParameter("warehouseRate"+i)==null?"0":request.getParameter("warehouseRate"+i);
							commoditys[i][4]=request.getParameter("seaRate"+i)==null?"0":request.getParameter("seaRate"+i);				
							commoditys[i][5]=request.getParameter("commExcessPremium"+i)==null?"0":request.getParameter("commExcessPremium"+i);				
							out.println("<br> "+i+" commoditys[0]   "+commoditys[i][0]);
							out.println("<br>  commoditys[1]   "+commoditys[i][1]);
							out.println("<br>  commoditys[2]   "+commoditys[i][2]);
							out.println("<br>  commoditys[3]   "+commoditys[i][3]);
							out.println("<br>  commoditys[4]   "+commoditys[i][4]);
							out.println("<br>  commoditys[5]   "+commoditys[i][5]);
						}
						catch(Exception e){
						}						
						try{
							if(Double.parseDouble(commoditys[i][1])>100 || Double.parseDouble(commoditys[i][1])<=0)
								error.append("<br> Please give percentage Value\n");
						}
						catch(Exception e){
							error.append("<br>Please give Integer Value");
						}
				}
			}
			catch(Exception e){
				
			}
		}		
	
		if(error.length()>0)
		{
			request.setAttribute("comExcesPremOption",comExcesPremOption);
		     request.setAttribute("errorMessage",error);	
			dispatcher=request.getRequestDispatcher("/admin/referalModification.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("comExcesPremOption",comExcesPremOption);
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
			  }
			  else{
				//cover="ICC(A)";
			}*/
			//out.println("the Nof o ITEMS"+noOfItems);
			/*if("1".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==1){
				warStatus="1";
				wsrcc="";			
			}
			else if("1".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==2){
				warStatus="2";
				wsrcc="";	
				//warStatus="0";	
			}
			else if("1".equalsIgnoreCase(wsrcc) && Integer.parseInt(modeOfTransport)==4){
				warStatus="4";
				wsrcc="";	
				//warStatus="0";	
			}
			else{
				warStatus="0";	
			}*/
			
			if("1".equalsIgnoreCase(wsrcc)){
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
			premiumTransaction.setModeOfTransport(modeOfTransport);
			premiumTransaction.setNoOfItems(noOfItems);
			premiumTransaction.setTransitFrom(transitFrom);
			premiumTransaction.setPolicyDay(policyDay);
			premiumTransaction.setPolicyMonth(policyMonth);
			premiumTransaction.setPolicyYear(policyYear);
			premiumTransaction.setSaleTerm(saleTerm);
			//out.println("RoyalTest..."+tolerance);if(true)return;
			premiumTransaction.setTolerance(tolerance);
			premiumTransaction.setWarehouse(warehouse);
			premiumTransaction.setWarehouse1(warehouse1);
			premiumTransaction.setWsrcc(warStatus);
			premiumTransaction.setTotalSumInsured(totalSumInsured);
			premiumTransaction.setFromCountryId(fromCountryId);
			premiumTransaction.setFromCityId(fromCityId);
			premiumTransaction.setToCountryId(toCountryId);
			premiumTransaction.setToCityId(toCityId);
			premiumTransaction.setProductId(productId);
			premiumTransaction.setSeaOption(request.getParameter("seaOption"));
			
			
			if(error.length()>0)
			{
				request.setAttribute("errorMessage",error);
				dispatcher=request.getRequestDispatcher("/premium/Quotation.jsp");
				dispatcher.forward(request, response);
			}
			else{
				premiumTransaction.setApplicationNo(applicationNo);
				premiumTransaction.setSessionId(sessionId);
				premiumTransaction.setLoginCode(loginId);
				premiumTransaction.setCompanyId(companyId);
				premiumTransaction.setProductId(productId);
				premiumTransaction.setStageId("2");
				premiumTransaction.setLoginBra(brokerBra);
				premiumTransaction.setCid(cid);
				premiumTransaction.insertOrUpdateMarineInfo(commoditys);
				PremiumInputsBean premiumBean = new PremiumInputsBean();
				String actualBranch = (String)session.getAttribute("AdminBranchCode");
				premiumBean.calculateTax("Admin",loginId,applicationNo,actualBranch,(String)request.getAttribute("minPremium"));
				if(nextMissing.equalsIgnoreCase("proceedToMissing"))
				{
					response.sendRedirect("../missing.jsp");
				}
				else
				{
					String referalCase=null;
					if(request.getParameter("totalCommodity")==null){
						referalCase=findReferalDate(policyDay,policyMonth,policyYear);
						request.setAttribute("policyDateReferal",referalCase);
						System.out.println(" ****************** FINDROADREFERAL *****************");
						System.out.println("modeOfTransport   "+modeOfTransport);
						System.out.println("fromCountryId   "+fromCountryId);
						System.out.println("toCountryId   "+toCountryId);
						System.out.println(" ****************** FINDROADREFERAL *****************");
						if(Integer.parseInt(modeOfTransport)==3)
							referalCase=findRoadReferal(fromCountryId,toCountryId);
						else
							referalCase="Not Referal Case";
						request.setAttribute("roadReferal",referalCase);
					}
					System.out.println(" <br>     Controller completer now referalPremium.jsp");
					String str=request.getParameter("flag")==null?"":request.getParameter("flag");
					if(!str.equalsIgnoreCase("adminplusreferal"))
					{	
						request.setAttribute("comExcesPremOption",comExcesPremOption);
						request.setAttribute("quoteStatus",quoteStatus);
						dispatcher=request.getRequestDispatcher("/admin/referalPremium.jsp");
					}
					else
					{
						request.setAttribute("comExcesPremOption",comExcesPremOption);
						request.setAttribute("quoteStatus",quoteStatus);
						dispatcher=request.getRequestDispatcher("/admin/referalPremium.jsp");
					}
					dispatcher.forward(request, response);
				}
			}			
		}
		out.flush();
		out.close();
	}
	
	public String findDubai(String transitFrom,String transitFinal)
	{
		System.out.println(" findDubai transitFrom  "+transitFrom+"--transitFinal  "+transitFinal);
		if("1".equalsIgnoreCase(transitFrom) || "1".equalsIgnoreCase(transitFinal))
			return "valid";
		else
			return "invalid";
	}
	
	public StringBuffer checkInputs(String inputName,String inputDescription)
	{
		if("".equalsIgnoreCase(inputName) || "0".equalsIgnoreCase(inputName) || null==(inputName) 
		|| "null".equalsIgnoreCase(inputName) || "select".equalsIgnoreCase(inputName) || "DD".equalsIgnoreCase(inputName)
		|| "MM".equalsIgnoreCase(inputName) || "YYYY".equalsIgnoreCase(inputName))
		{
			if(inputDescription.equalsIgnoreCase("TO COUNTRY"))	{
				error.append("<br><font color=red size=2><b>* Sorry !- Please Kindly Choose FINAL DESTINATION ONCE AGAIN</b></font><br>");	
			}
			else if(inputDescription.equalsIgnoreCase("FROM COUNTRY")){
				error.append("<br><font color=red size=2><b>* Sorry !- Please Kindly Choose TRANSIT FROM ONCE AGAIN</b></font><br>");
			}
			else{
					error.append("<br><font color=red size=2><b>* Please Provide Input for ---"+inputDescription
					+"</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("suminsured")){
               //    error.append("<br><font color=red size=2><b>* SumInsured Value is Exceeded"
				//	+"</b></font><br>");
		}
		if(inputDescription.equalsIgnoreCase("DATECHECK")){
			if("Invalid".equalsIgnoreCase(checkDate(inputName))){
					error.append("<br><font color=red size=2><b>* Please Enter Valid Date </b></font><br>");
			}			
		}		
		/*else if(inputName.equalsIgnoreCase("totalSumInsured") )
		{		
			System.out.println("comes Hre TOTAL SUM");			
			try{				
				Long.parseLong(inputName);				
				//error.append("<br><font color=red size=2><b>* Please Provide Input for ---"+inputDescription
						//+"</b></font><br>");									
			}catch(Exception exception)	{				
				error.append("<br><font color=red size=2><b>* Please Provide VALID INPUT for ---"+inputDescription
						+"</b></font><br>");
			}
		}*/
		return error;
	}
	
	public void doPut(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doPost(request,response);
	}

	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	public void init() throws ServletException {
	}

	public String checkDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MMM/yyyy");
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
	
	public String findRoadReferal(String fromCountryId,String toCountryId)
	{
		String referal=null;
		try{
			int a[]={1,153,14,147,99};
			if((Integer.parseInt(fromCountryId)==a[0] || Integer.parseInt(fromCountryId)==a[1] || Integer.parseInt(fromCountryId)==a[2] || Integer.parseInt(fromCountryId)==a[3] || Integer.parseInt(fromCountryId)==a[4]) && (Integer.parseInt(toCountryId)==a[0] || Integer.parseInt(toCountryId)==a[1] || Integer.parseInt(toCountryId)==a[2] || Integer.parseInt(toCountryId)==a[3] || Integer.parseInt(toCountryId)==a[4]))
				referal="Not Referal Case";
			else
				referal="Referal";
		}
		catch(Exception e){
			System.out.println("ERROR in findRoadReferal()   "+e.toString());
		}
		return referal;
	}
	
	public String findReferalDate(String Days,String months,String years)
	{
		String args[] = new String[1];
		String result=null;
		String sql = "";
		try
		{
			args[0] = months+"/"+Days+"/"+years;
			//result=com.maan.services.util.runner.singleSelection("select months_between(sysdate,to_date('"+months+"/"+Days+"/"+years+"','MON/DD/YYYY'))from dual");
			sql = "select months_between(sysdate,to_date(?,'MON/DD/YYYY'))from dual";
			result = runner.singleSelection(sql,args);
			if(Double.parseDouble(result)>=0 && Double.parseDouble(result)<=0.21)
				result="Not Referal Case";
			else
				result="Referal";
			System.out.println("REsultsDays   "+result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}	

	public boolean validAmount(String value) 
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890.";
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
			System.out.println("Valid Amount in DataCollection.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in DataCollection Java  "+ flag);
		return flag;
	}

}// Class
