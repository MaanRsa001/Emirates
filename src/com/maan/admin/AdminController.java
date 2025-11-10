package com.maan.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;

public class AdminController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	PrintWriter out = null;
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
	String deActivatedDate = "";
	public void init(ServletConfig config) throws ServletException {

		try {
		} catch (Exception e) {
			System.out.println("Exception in init method" + e.toString());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		HttpSession session = req.getSession(true);
		PrintWriter out = res.getWriter();
		com.maan.admin.AdminBean ab = new com.maan.admin.AdminBean();
		com.maan.admin.BrokerCreationBean bc = new com.maan.admin.BrokerCreationBean();
		com.maan.admin.ratingAdmin ra = new com.maan.admin.ratingAdmin();
		com.maan.admin.BranchCreationBean bcb=new com.maan.admin.BranchCreationBean();
		String loginPersonId = (String) session.getAttribute("loginPersonId");
		RequestDispatcher dispatcher1 = req.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		String requestFrom =req.getParameter("requestfrom")==null?"":req.getParameter("requestfrom");
		////String oldvalue=req.getParameter("oldvalue")==null?"":req.getParameter("oldvalue");
	
		System.out.println("requestFrom:"+requestFrom);
		if (dispatcher1 != null)
			dispatcher1.include(req, res);
		if (loginPersonId == null) {
			loginPersonId = (String) session.getAttribute("user");
		}

		if(requestFrom.equalsIgnoreCase("commodity"))
		{
			String iccclause[]=new String[12];		
			String iccexclusion[]=new String[12];
			String clause="";
			String iccwarrenty[]=new String[12];
			String iccwarcover[]=new String[12];
			String iccdeductive[]=new String[12];
			try{
			
			String commodityId = req.getParameter("COMMODITY_ID")==null?"":req.getParameter("COMMODITY_ID");
			String commodityName = req.getParameter("COMMODITY_NAME")==null?"":req.getParameter("COMMODITY_NAME");
			String COVERAGE_REFERAL= req.getParameter("COVERAGE_REFERAL")==null?"":req.getParameter("COVERAGE_REFERAL");
			//System.out.println("commodityName:"+commodityName);
			int j=0;
			
			try{
			for(int k=1;k<=12;k++)
			{
				
				clause=req.getParameter("txtchkICC_A_SEAClause$"+k)==null?"":req.getParameter("txtchkICC_A_SEAClause$"+k);
				System.out.println("clause:"+clause);
				
				iccclause[j]=clause;
			/////	System.out.println("iccclause[j]:"+iccclause[j]);
					///req.getParameter("txtchkICC_A_SEAClause$"+k)==null?"":req.getParameter("txtchkICC_A_SEAClause$"+k);
				iccexclusion[j]=req.getParameter("txtchkICC_A_SEAExclusive$"+k)==null?"":req.getParameter("txtchkICC_A_SEAExclusive$"+k);
			    iccwarrenty[j]=req.getParameter("txtchkICC_A_SEAWarranty$"+k)==null?"":req.getParameter("txtchkICC_A_SEAWarranty$"+k);
			    iccwarcover[j]=req.getParameter("txtchkICC_A_SEAWarCover$"+k)==null?"":req.getParameter("txtchkICC_A_SEAWarCover$"+k);
			    iccdeductive[j]=req.getParameter("radDetective$"+k)==null?"":req.getParameter("radDetective$"+k);
			   /// System.out.println("iccexclusion[j]:"+iccexclusion[j]);
		//// System.out.println("iccwarcover[j]:"+iccwarcover[j]);
		/// System.out.println("iccdeductive[j]:"+iccdeductive[j]);
		
		//// System.out.println("k--------->"+k);

		///// System.out.println("j--------->"+j);
		 j++;
		
			}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		
		    String commrate = req.getParameter("commrate")==null?"":req.getParameter("commrate");	
			String startDate = req.getParameter("startDate")==null?"":req.getParameter("startDate");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String amend_id = req.getParameter("AMEND_ID")==null?"":req.getParameter("AMEND_ID");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			String mode  = req.getParameter("mode") == null ? "" : req	.getParameter("mode");
			/*String fragile = req.getParameter("FRAGILE")==null?"":req.getParameter("FRAGILE");
			String rag  = req.getParameter("RAG")==null?"":req.getParameter("RAG");*/
			String commodityType = req.getParameter("commodityType")==null?"":req.getParameter("commodityType");
			String commodityExcessPremium	= req.getParameter("COMMODITY_EXCESS_PREMIUM")==null?"":req.getParameter("COMMODITY_EXCESS_PREMIUM");
			String exclusionId = req.getParameter("EXCLUSION_ID")==null?"":req.getParameter("EXCLUSION_ID");
			String warrantyId	= req.getParameter("WARRANTY_ID")==null?"":req.getParameter("WARRANTY_ID");
			String remarks	= req.getParameter("Remarks")==null?"":req.getParameter("Remarks");
			String coreAppCode= req.getParameter("coreAppCode")==null?"":req.getParameter("coreAppCode");			

			ra.setCommodityId(commodityId);
			ra.setCommodityName(commodityName);
			ra.setIccClause(iccclause);
			ra.setIccwarcover(iccwarcover);
			ra.setIccdeductive(iccdeductive);
			ra.setIccExclusion(iccexclusion);
			ra.setIccWarrenty(iccwarrenty);				
			ra.setStatus(status);
			ra.setCommrate(commrate);
			ra.setAmend_id(amend_id);			
			ra.setStartDate(startDate);
			System.out.println("hello");
			/*ra.setFragile(fragile);
			ra.setRag(rag);*/
			ra.setCommodityType(commodityType);
			ra.setCOMMODITY_EXCESS_PREMIUM(commodityExcessPremium);
			ra.setWarrantyId(warrantyId);
			ra.setExclusionId(exclusionId);
			ra.setRemarks(remarks);		
			ra.setCoreApplicationCode(coreAppCode);	
			ra.setCOVERAGE_REFERAL(COVERAGE_REFERAL);
			ra.validateSubDetails(branchCode,mode);
			String error = ra.getError();
			////out.println("error232343:"+error);

			
			if (error.length() > 0) {
				//////out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				req.setAttribute("startDate", startDate);
				req.setAttribute("commodity", commodityId);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/commodity.jsp?");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertCommodity(mode,branchCode);
				if (process.equalsIgnoreCase("YES")) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
                else {
					out.println("ERROR WHILE INSERTING DATAS IN COMMODITY MASTER");
					if (true)
						return;
				}
			}
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}
		}
		else if(requestFrom.equalsIgnoreCase("BaseRate"))
		{
			String transportType  = req.getParameter("transportType") == null ? "" : req	.getParameter("transportType");
			String coverageType  = req.getParameter("coverageType") == null ? "" : req	.getParameter("coverageType");
			String conveyanceType  = req.getParameter("conveyanceType") == null ? "" : req	.getParameter("conveyanceType");
			String countryType  = req.getParameter("countryType") == null ? "" : req	.getParameter("countryType");
			String commodityType  = req.getParameter("commodityType") == null ? "" : req	.getParameter("commodityType");
			String status  = req.getParameter("status") == null ? "" : req	.getParameter("status");
			String SumRate  = req.getParameter("SumRate") == null ? "" : req	.getParameter("SumRate");
					
			ra.setTransportType(transportType);
			ra.setCoverageType(coverageType);
			ra.setConveyanceType(conveyanceType);
			ra.setCountryType(countryType);
			ra.setCommodityType(commodityType);
			ra.setStatus(status);
			ra.setSumRate(SumRate);
			
			ra.validateBaseRateMaster();
			String error = ra.getError();
			
			if (error.length() > 0) {
				System.out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BaseRate.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
            	System.out.println("BeforMethod===>"+transportType+""+coverageType+""+conveyanceType+""+countryType+""+commodityType+""+status+""+Double.parseDouble(SumRate)+""+"01"+""+"07-JUN-2012 14:11:12");
            	String process = ra.getCommodityRate(transportType,coverageType,conveyanceType,countryType,commodityType,status,Double.parseDouble(SumRate),"01","07-JUN-2012 14:11:12");
            	System.out.println("Process===>"+process);
				String values[] = process.split("~");
				for(int i=0;i<values.length;i++)
				{
				System.out.println("===>"+values[i]);
				}
				req.setAttribute("values", values);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BaseRate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
			}

		}
//////////////////////////    CONVEY ENRTY    ////////////////////////////
		
		else if(requestFrom.equalsIgnoreCase("convey"))
		{
////////////////     Get the Values////////////////////////// 
			try{
			String ConveyId = req.getParameter("hidConveyId") == null ? "" : req.getParameter("hidConveyId");
			String ConveyName=req.getParameter("txtConveyName")==null?"":req.getParameter("txtConveyName");		
			String ConveyRate=req.getParameter("txtConveyRate")==null?"":req.getParameter("txtConveyRate");
			String ConveyRemarks=req.getParameter("txtareaRemarks")==null?"":req.getParameter("txtareaRemarks");
			String ConveyCoreAppCode=req.getParameter("txtCoreAppCode")==null?"":req.getParameter("txtCoreAppCode");
			String ConveyModeOfTransport=req.getParameter("selModeOfTransport")==null?"":req.getParameter("selModeOfTransport");
			String ConveyMEffectiveDate=req.getParameter("startDate")==null?"":req.getParameter("startDate");
			String ConveyStatus=req.getParameter("radStatus")==null?"":req.getParameter("radStatus");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			String mode  = req.getParameter("mode") == null ? "" : req	.getParameter("mode");
			System.out.println("mode:"+mode);
			String ConveyAmendId = req.getParameter("hidAmendId") == null ? "" : req	.getParameter("hidAmendId");
			
////////////////     Set the Values ///////////////////////
			ra.setConveyName(ConveyName);
			ra.setConveyModeOfTransport(ConveyModeOfTransport);
			ra.setConveyRate(ConveyRate);
			ra.setConveyRemarks(ConveyRemarks);
			ra.setConveyCoreAppCode(ConveyCoreAppCode);
			ra.setConveyMEffectiveDate(ConveyMEffectiveDate);
			ra.setConveyId(ConveyId);
			ra.setConveyAmendId(ConveyAmendId);
			ra.setConveyStatus(ConveyStatus);
			ra.validateConveyDetails(branchCode,mode);
			String error = ra.getError();
			
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				req.setAttribute("ConveyMEffectiveDate", ConveyMEffectiveDate);
				req.setAttribute("ConveyId", ConveyId);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/EmiratesConvey.jsp?");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertConveyDetails(mode,branchCode);
				if (process.equalsIgnoreCase("YES")) {					
						session.setAttribute("comFrom", "Convey");
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/ConveyConfirmation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
					System.out.println("Convey MAster Success fully Inserted");
				}
                else {
					out.println("ERROR WHILE INSERTING DATAS IN COMMODITY MASTER");
					if (true)
						return;
				}
			}
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}
			
		
}
////////////////////////     COMMODITY EXCESS PREMIUM   //////////////////////////////////////		
		
		else if(requestFrom.equalsIgnoreCase("CommodityExcess"))
		{
////////////////     Get the Commodity Excess Values////////////////////////// 
			try{
			String CommodExcessId = req.getParameter("hidCommoExcessId") == null ? "" : req.getParameter("hidCommoExcessId");
			System.out.println("CommodExcessId:"+CommodExcessId);
			String CommodExcessStartInsured=req.getParameter("txtStartInsured")==null?"":req.getParameter("txtStartInsured");		
			String CommodExcessEndInsured=req.getParameter("txtEndInsured")==null?"":req.getParameter("txtEndInsured");
			String CommodExcessDeductible=req.getParameter("txtDeductible")==null?"":req.getParameter("txtDeductible");
			String CommoExcessRate=req.getParameter("txtCommoExcessRate")==null?"":req.getParameter("txtCommoExcessRate");
			String CommodExcessEffectiveDate=req.getParameter("startDate")==null?"":req.getParameter("startDate");
			String CommodExcessStatus=req.getParameter("radStatus")==null?"":req.getParameter("radStatus");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			String mode  = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			System.out.println("mode:"+mode);
			String CommodExcessAmendId = req.getParameter("hidAmendId") == null ? "" : req.getParameter("hidAmendId");
			
////////////////     Set the Values ///////////////////////			
			ra.setCommodExcessStartInsured(CommodExcessStartInsured);
			ra.setCommodExcessEndInsured(CommodExcessEndInsured);
			ra.setCommodExcessDeductible(CommodExcessDeductible);
			ra.setCommoExcessRate(CommoExcessRate);
			ra.setCommodExcessEffectiveDate(CommodExcessEffectiveDate);
			ra.setCommodExcessId(CommodExcessId);
			ra.setCommodExcessStatus(CommodExcessAmendId);
			ra.setCommodExcessStatus(CommodExcessStatus);
			ra.validateCommodExcessDetails(branchCode);
			String error = ra.getError();
			System.out.println("error:"+error);
			
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				req.setAttribute("CommodExcessEffectiveDate", CommodExcessEffectiveDate);
				req.setAttribute("CommodExcessId", CommodExcessId);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityExcessEntry.jsp?");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertCommodExcessDetails(mode,branchCode);
				if (process.equalsIgnoreCase("YES")) {					
						session.setAttribute("comFrom", "CommodExcess");
					
					RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/ConveyConfirmation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
					System.out.println("Commodity Excess Master Success fully Inserted");
				}
                else {
					out.println("ERROR WHILE INSERTING DATAS IN COMMODITY MASTER");
					if (true)
						return;
				}
			}
			}
			catch(Exception e)
			{
				System.out.print(e.getMessage());
			}
			
		
}

		
		///////////////////      POLICY REPORT
		
		else if(requestFrom.equalsIgnoreCase("policyreport"))
		{
			String PRstartdate=req.getParameter("startDate") == null ? "" : req.getParameter("startDate");
			String PRenddate=req.getParameter("endDate") == null ? "" : req.getParameter("endDate");
			String PRproduct=req.getParameter("product") == null ? "" : req.getParameter("product");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			try{				
				
				ra.setPRstartdate(PRstartdate);
				ra.setPRenddate(PRenddate);
				ra.setPRproduct(PRproduct);
				System.out.println("PRproduct------->:"+PRproduct);
				ra.validatePolicyReportDetails(branchCode);
				String error = ra.getError();
				if (error.length() > 0) {
					out.println("error ifnfo " + error);
					req.setAttribute("error", error);				
					RequestDispatcher dispatcher = req.getRequestDispatcher("/reports/EmiratesPolicyReport.jsp?");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
				else{
					req.setAttribute("startdate",PRstartdate);
					req.setAttribute("enddate",PRenddate);
					req.setAttribute("product",PRproduct);
					req.setAttribute("branch",branchCode);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/reports/PolicyReport.jsp?");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
				
				}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		
		else if(requestFrom.equalsIgnoreCase("OpenCoverpolicy"))
		{
			String PRstartdate=req.getParameter("startDate") == null ? "" : req.getParameter("startDate");
			String PRenddate=req.getParameter("endDate") == null ? "" : req.getParameter("endDate");
			String PRproduct=req.getParameter("product") == null ? "" : req.getParameter("product");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			try{				
				
				ra.setPRstartdate(PRstartdate);
				ra.setPRenddate(PRenddate);
				ra.setPRproduct(PRproduct);
				System.out.println("PRproduct------->:"+PRproduct);
				//ra.validatePolicyReportDetails(branchCode);
				String error = ra.getError();
				if (error.length() > 0) {
					out.println("error ifnfo " + error);
					req.setAttribute("error", error);				
					RequestDispatcher dispatcher = req.getRequestDispatcher("/reports/OpenCoverPolicy.jsp?");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
				else{
					req.setAttribute("startdate",PRstartdate);
					req.setAttribute("enddate",PRenddate);
					req.setAttribute("product",PRproduct);
					req.setAttribute("branch",branchCode);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/reports/OpenCoverPolicyReport.jsp?");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
				
				}
			
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}

		else if(requestFrom.equalsIgnoreCase("BranchCreation")){
			System.out.println("BeanCreation------>");
			String branchCode = req.getParameter("BranchCode")==null?"":req.getParameter("BranchCode");
			String branchName = req.getParameter("BranchName")==null?"":req.getParameter("BranchName");
			String address1 = req.getParameter("Address1")==null?"":req.getParameter("Address1");
			String address2 = req.getParameter("Address2")==null?"":req.getParameter("Address2");
			String address3 = req.getParameter("Address3")==null?"":req.getParameter("Address3");
			String city = req.getParameter("City")==null?"":req.getParameter("City");
			String country = req.getParameter("Country")==null?"":req.getParameter("Country");
			String currencyAbbre = req.getParameter("CurrencyAbbreviation")==null?"":req.getParameter("CurrencyAbbreviation");
			String currencyAcronym = req.getParameter("CurrencyAcronym")==null?"":req.getParameter("CurrencyAcronym");
			String curDecimalDigit = req.getParameter("CurrencyDecimalDigit")==null?"":req.getParameter("CurrencyDecimalDigit");
			String curDecimalName = req.getParameter("CurrencyDecimalName")==null?"":req.getParameter("CurrencyDecimalName");
			String currencyName = req.getParameter("CurrencyName")==null?"":req.getParameter("CurrencyName");
			String decimalPlaces = req.getParameter("DecimalPlaces")==null?"":req.getParameter("DecimalPlaces");
			String desCountryId = req.getParameter("DestinationCountryId")==null?"":req.getParameter("DestinationCountryId");
			String email = req.getParameter("E-Mail")==null?"":req.getParameter("E-Mail");
			String fax = req.getParameter("Fax")==null?"":req.getParameter("Fax");
			String headerImage = req.getParameter("HeaderImg")==null?"":req.getParameter("HeaderImg");
			String footerImage = req.getParameter("FooterImg")==null?"":req.getParameter("FooterImg");
			String originCountryId = req.getParameter("OriginCountryId")==null?"":req.getParameter("OriginCountryId");
			String phone = req.getParameter("phone")==null?"":req.getParameter("phone");
			String stamp = req.getParameter("Stamp")==null?"":req.getParameter("Stamp");
			String tax = req.getParameter("Tax")==null?"":req.getParameter("Tax");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String signImage = req.getParameter("SignImage")==null?"":req.getParameter("SignImage");
			String remarks = req.getParameter("Remarks")==null?"":req.getParameter("Remarks");
			
			bcb.setBranchCode(branchCode);
			bcb.setBranchName(branchName);
			bcb.setAddress1(address1);
			bcb.setAddress2(address2);
			bcb.setAddress3(address3);
			bcb.setCity(city);
			bcb.setCountry(country);
			bcb.setCurrencyAbbre(currencyAbbre);
			bcb.setCurrencyAcronym(currencyAcronym);
			bcb.setCurDecimalDigit(curDecimalDigit);
			bcb.setCurDecimalName(curDecimalName);
			bcb.setCurrencyName(currencyName);
			bcb.setDecimalPlaces(decimalPlaces);
			bcb.setDesCountryId(desCountryId);
			bcb.setEmail(email);
			bcb.setFax(fax);
			bcb.setHeaderImage(headerImage);
			bcb.setFooterImage(footerImage);
			bcb.setOriginCountryId(originCountryId);
			bcb.setPhone(phone);
			bcb.setStamp(stamp);
			bcb.setTax(tax);
			bcb.setStatus(status);
			bcb.setSignImage(signImage);
			bcb.setRemarks(remarks);
			
			bcb.validateBranchDetails();
			String error="";
			error=bcb.getError();
			
			String mode=req.getParameter("mode");
			String process="";			
			if(error.length()>0){
				req.setAttribute("error", error);
				process="NO";
			}
			else {
				process=bcb.insertOrUpdateBranch(branchCode, branchName, address1, address2, address3, city, country, currencyAbbre, currencyAcronym, curDecimalDigit, curDecimalName, currencyName, decimalPlaces, desCountryId, email, fax, headerImage, footerImage, originCountryId, phone, stamp, tax, status, signImage, remarks, mode);
			}
			
			if(process.equalsIgnoreCase("YES")){
				session.setAttribute("comFrom", "BranchCreation");
				
				RequestDispatcher dispatchBranch=req.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
				dispatchBranch.forward(req, res);
			}
			else if(process.equalsIgnoreCase("NO")){
				RequestDispatcher dispatchBranch=req.getRequestDispatcher("/branch/BranchCreation.jsp");
				dispatchBranch.forward(req, res);
			}
			else{
				out.print("error--mode-->"+mode+"/"+remarks+"hh"+tax+status);
			}
			
		}

		else if(requestFrom.equalsIgnoreCase("sale"))
		{
			String saleTermName = req.getParameter("SALE_TERM_NAME")==null?"":req.getParameter("SALE_TERM_NAME");
			String saleTermValue = req.getParameter("SALE_TERM_VALUE")==null?"":req.getParameter("SALE_TERM_VALUE");
			String rsaCode = req.getParameter("CORE_APP_CODE")==null?"":req.getParameter("CORE_APP_CODE");
			String remarks = req.getParameter("Remarks")==null?"":req.getParameter("Remarks");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String saleTermId=req.getParameter("SALE_TERM_ID")==null?"":req.getParameter("SALE_TERM_ID");

			
			String mode  = req.getParameter("mode") == null ? "" : req	.getParameter("mode");

            String branchCode = (String)session.getAttribute("LoginBranchCode");;
           
            ra.setBranch_code(branchCode);
            ra.setCoreApplicationCode(rsaCode);
            ra.setSaleTermValue(saleTermValue);

            ra.validateSaleDetail(branchCode);

            String error=ra.getError();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/sale.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
            	String pro="";
            	if(mode.equals("add")){
            		System.out.println("------------ADD MODE CONTROLLER------>");
            		pro = ra.insertSaleTerm(saleTermName,saleTermValue,rsaCode,remarks,status,branchCode);

            	}
            	else if(mode.equals("edit")){
            		System.out.println("------------EDIT MODE CONTROLLER------>");
            	pro=ra.updateSaleDetails(saleTermName, saleTermValue, rsaCode, remarks, status, saleTermId, branchCode);

            	}
				if (pro.equalsIgnoreCase("YES")) {
					System.out.println("pro----------------..........................>"+pro);
					session.setAttribute("comFrom", "sale");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
                else {
					out.println("ERROR WHILE INSERTING DATAS IN SALE TERM MASTER");
					if (true)
						return;
				}
			}
		}

		else if (requestFrom.equalsIgnoreCase("country"))
		{
			String countryid = req.getParameter("COUNTRY_ID") == null ? "": req.getParameter("COUNTRY_ID");
			String countryname = req.getParameter("COUNTRY_NAME") == null ? "": req.getParameter("COUNTRY_NAME");
			String countryshortname = req.getParameter("COUNTRY_SHORT_NAME") == null ? "": req.getParameter("COUNTRY_SHORT_NAME");
			String warrate = req.getParameter("WAR_RATE") == null ? "" : req.getParameter("WAR_RATE");
			String startingplace = req.getParameter("STARTING_PLACE") == null ? "": req.getParameter("STARTING_PLACE");
			String spwarrantiesconditions = req.getParameter("SP_WARRANTIES_CONDITIONS1") == null ? "": req.getParameter("SP_WARRANTIES_CONDITIONS1");
			String endingplace = req.getParameter("ENDING_PLACE") == null ? "": req.getParameter("ENDING_PLACE");
			String epwarrantiesconditions = req.getParameter("EP_WARRANTIES_CONDITIONS1") == null ? "": req.getParameter("EP_WARRANTIES_CONDITIONS1");
			String status = req.getParameter("status") == null ? "" : req	.getParameter("status");
			String rsacode = req.getParameter("RSACODE") == null ? "" : req.getParameter("RSACODE");
			String nationalityname = req.getParameter("NATIONALITY_NAME") == null ? "": req.getParameter("NATIONALITY_NAME");
			String startDate = req.getParameter("startDate") == null ? "" : req.getParameter("startDate");
			String amendid = req.getParameter("AMEND_ID") == null ? "" : req	.getParameter("AMEND_ID");
			// String exclusionid
			// =req.getParameter("EXCLUSION_ID")==null?"":req.getParameter("EXCLUSION_ID");
			// String EFFECTIVE_DATE
			// =req.getParameter("EFFECTIVE_DATE")==null?"":req.getParameter("EFFECTIVE_DATE");
			String remarks = req.getParameter("Remarks") == null ? "" : req.getParameter("Remarks");
			String mode = req.getParameter("mode") == null ? "" : req	.getParameter("mode");

			out.println(">>>>>>>>>>>>>>" + startDate);
			out.println(">>>>>>>>>>>>>>hjkhj" + status);
			out.println(">>>>>>>>>>>>>>hjkhjk" + amendid);
			ra.setCountryid(countryid);
			ra.setCountryname(countryname);
			ra.setCountryshortname(countryshortname);
			ra.setWarrate(warrate);
			ra.setStartingplace(startingplace);
			ra.setSpwarrantiesconditions(spwarrantiesconditions);
			ra.setEndingplace(endingplace);
			ra.setEpwarrantiesconditions(epwarrantiesconditions);
			ra.setStatus1(status);
			ra.setRsacode(rsacode);
			ra.setNationalityname(nationalityname);
			ra.setAmendid1(amendid);
			ra.setRemarks1(remarks);
			ra.setStartDate1(startDate);

			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			String borganization = req.getParameter("borganization") == null ? "": req.getParameter("borganization");
			ra.validateCountryDetails();
			String error = ra.getError();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				req.setAttribute("startDate", startDate);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/country123.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else
			{
				String belongingCid = "";
				belongingCid = (String)session.getAttribute("belongingCid");
				String process = ra.insertCountryData(mode,belongingCid);
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("from", "country");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {
					out.println("ERROR WHILE INSERTING DATAS IN COMMODITY MASTER");
					if (true)
						return;
				}
			}

			String title1 = bc.getTitle();
		}
		else if (requestFrom.equalsIgnoreCase("policy")) {
			//String requestfrom = req.getRequestURI();
			//String requestfrom1 = requestFrom;
			//String amend_id       = req.getParameter("AMEND_ID");

			String type_id 		  = req.getParameter("TYPE_ID");
			String type_desc 	  = req.getParameter("TYPE_DESC");
			String branch_code    = req.getParameter("BRANCH_CODE");
			String agency_code    = req.getParameter("AGENCY_CODE");
			String policy_prefix  = req.getParameter("POLICY_PREFIX");
			String start_no 	  = req.getParameter("START_NO");
			String end_no 		  = req.getParameter("END_NO");
			String current_no     = req.getParameter("CURRENT_NO");
			String remarks        = req.getParameter("REMARKS");
			String status         = req.getParameter("STATUS");
			String login_id       = req.getParameter("LOGIN_ID");
			String description    = req.getParameter("DESCRIPTION");
			String product_id     = req.getParameter("PRODUCT_ID");
			String startDate      = req.getParameter("startDate");
			String mode           = req.getParameter("mode");


			type_id 		  = type_id==null?"":type_id;
			type_desc 	 	  = type_desc==null?"":type_desc;
			branch_code       = branch_code==null?"":branch_code;
			agency_code       = agency_code==null?"":agency_code;
			policy_prefix     = policy_prefix==null?"":policy_prefix;
			start_no 	      = start_no==null?"":start_no;
			end_no 		      = end_no==null?"":end_no;
			current_no        = current_no==null?"":current_no;
			remarks           = remarks==null?"":remarks;
			status            = status ==null?"":status;
			login_id          = login_id==null?"":login_id;
			description       = description==null?"":description;
			product_id        = product_id==null?"":product_id;
			startDate         = startDate==null?"":startDate;
			mode          	  = mode==null?"":mode;

			ra.setType_id(type_id);
			ra.setType_desc(type_desc);
			ra.setBranch_code(branch_code);
			ra.setAgency_code(agency_code);
			System.out.println("agency_code value "+agency_code);
			ra.setPolicy_prefix(policy_prefix);
			ra.setStart_no(start_no);
			ra.setEnd_no(end_no);
			ra.setCurrent_no(current_no);
			ra.setRemarks(remarks);
			ra.setStatus2(status);
			///ra.setPolicy_quote(policy_quote);
			ra.setLogin_id(login_id);
			ra.setDescription(description);
			ra.setProduct_id1(product_id);
			//ra.setAmend_id(amend_id);
			ra.setRemarks2(remarks);
			ra.setStartDate2(startDate);
			ra.setMode(mode);

			/*String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			String borganization = req.getParameter("borganization") == null ? "": req.getParameter("borganization");*/

			ra.validatePolicyDetails();
			String error = ra.getError();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				req.setAttribute("startDate", startDate);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/policy123.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else{
				System.out.println("Mode value in servlet  "+mode);
				ra.insertPolicyData(mode);
				req.setAttribute("from", "policy");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityConformation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
				return;
			}
		}
		else if (requestFrom.equalsIgnoreCase("commodityEntry"))
		{

			String commodity = req.getParameter("commodity") == null ? "" : req.getParameter("commodity");
			String error = "";
			if (commodity.equals("")|| commodity.trim().equalsIgnoreCase("select")) {
				error = "Select Commodity";
			}
			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityEntry.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				req.setAttribute("commodity", commodity);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/commodity.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (requestFrom.equalsIgnoreCase("conveyEntry"))
		{

			String ConveyId = req.getParameter("ConveyId") == null ? "" : req.getParameter("ConveyId");
			String error = "";
			System.out.println("ConveyId:"+ConveyId);
			System.out.println("ConveyId:"+ConveyId);
			if (ConveyId.equals("")|| ConveyId.trim().equalsIgnoreCase("select")) {
				error = "Select Convey";
			}
			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/ConveyEntry.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				req.setAttribute("ConveyId", ConveyId);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/EmiratesConvey.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (requestFrom.equalsIgnoreCase("CommodityExcessEntry"))
		{

			String CommoExcessId = req.getParameter("selCommoExcessId") == null ? "" : req.getParameter("selCommoExcessId");
			String error = "";
			System.out.println("CommoExcessId:"+CommoExcessId);
			System.out.println("CommoExcessId:"+CommoExcessId);
			if (CommoExcessId.equals("")|| CommoExcessId.trim().equalsIgnoreCase("select")) {
				error = "Select Commodity Excess";
			}
			if (error.length() > 0)
			{
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityExcessMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				req.setAttribute("CommoExcessId", CommoExcessId);			
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CommodityExcessEntry.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (requestFrom.equalsIgnoreCase("addCountryMaster"))
		{
			String countryID = req.getParameter("countryID") == null ? "" : req.getParameter("countryID");
			String countryName = req.getParameter("countryName") == null ? "" : req.getParameter("countryName");
			String countryShortName = req.getParameter("countryShortName") == null ? "" : req.getParameter("countryShortName");
			String nationalityName = req.getParameter("nationalityName") == null ? "" : req.getParameter("nationalityName");
			String geoRate = req.getParameter("geoRate") == null ? "" : req.getParameter("geoRate");
			String coreApplnCode = req.getParameter("coreApplnCode") == null ? "" : req.getParameter("coreApplnCode");
			String effectiveDate = req.getParameter("effectiveDate") == null ? "" : req.getParameter("effectiveDate");
			String remarks = req.getParameter("remarks") == null ? "" : req.getParameter("remarks");
			String status = req.getParameter("status") == null ? "" : req.getParameter("status");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			String msg = "";
			
			ra.setMode(mode);
			ra.setCountryid(countryID);
			ra.setCountryname(countryName);
			ra.setCountryshortname(countryShortName);
			ra.setNationalityname(nationalityName);
			ra.setGeoRate(geoRate);
			ra.setCoreApplicationCode(coreApplnCode);
			ra.setEffective_date(effectiveDate);
			ra.setRemarks(remarks);
			ra.setStatus(status);
			
			ra.validateCountryMaster(mode);
			String error = ra.getError();
			
			if (error.length() > 0) {
				System.out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CountryMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertCountryMaster();
				System.out.println("error ifnfo " + error);
				if (process.equalsIgnoreCase("success")) {
					if(mode.equalsIgnoreCase("add"))
						msg = "Country details has been added successfully";
					else if(mode.equalsIgnoreCase("edit"))
						msg = "Country details has been updated successfully";
					
					req.setAttribute("msg", msg);
				}
                else {
					if(mode.equalsIgnoreCase("add"))
						error = "Error has occured during the updation of country details";
					else if(mode.equalsIgnoreCase("edit"))
						error = "Error has occured during the updation of country details";

					req.setAttribute("error", error);
				}

				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CountryMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
            }
		}
		else if (requestFrom.equalsIgnoreCase("getCountryDtls"))
		{
			String countryid = req.getParameter("countryid") == null ? "" : req.getParameter("countryid");
			String[][] countryDtls = new String[0][0];
			
			countryDtls = ra.getCountryMasterDtls(countryid);
			req.setAttribute("countryDtls", countryDtls);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CountryMaster.jsp?requestfrom=addCountryMaster&mode=edit");
			if (dispatcher != null)
				dispatcher.forward(req, res);
		}else if (requestFrom.equalsIgnoreCase("addWarRateMaster"))
		{
			String branch_code = (String)session.getAttribute("LoginBranchCode");
			String warID = req.getParameter("warID") == null ? "" : req.getParameter("warID");
			String warDesc = req.getParameter("warDesc") == null ? "" : req.getParameter("warDesc");
			String warRate = req.getParameter("warRate") == null ? "" : req.getParameter("warRate");
			String transid = req.getParameter("transid") == null ? "" : req.getParameter("transid");
			String coreApplnCode = req.getParameter("coreApplnCode") == null ? "" : req.getParameter("coreApplnCode");
			String effectiveDate = req.getParameter("effectiveDate") == null ? "" : req.getParameter("effectiveDate");
			String remarks = req.getParameter("remarks") == null ? "" : req.getParameter("remarks");
			String status = req.getParameter("status") == null ? "" : req.getParameter("status");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			String msg = "";

			ra.setMode(mode);
			ra.setModeOfTransID(transid);
			ra.setBranch_code(branch_code);
			ra.setWarID(warID);
			ra.setWarrate(warRate);
			ra.setWarDesc(warDesc);
			ra.setCoreApplicationCode(coreApplnCode);
			ra.setEffective_date(effectiveDate);
			ra.setRemarks(remarks);
			ra.setStatus(status);
			
			ra.validateWarRateMaster(mode);
			String error = ra.getError();
			
			if (error.length() > 0) {
				System.out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/WarRateMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertWarRateDetails();
				System.out.println("error ifnfo " + error);
				if (process.equalsIgnoreCase("success")) {
					if(mode.equalsIgnoreCase("add"))
						msg = "War Rate details has been added successfully";
					else if(mode.equalsIgnoreCase("edit"))
						msg = "War Rate details has been updated successfully";
					
					req.setAttribute("msg", msg);
				}
                else {
					if(mode.equalsIgnoreCase("add"))
						error = "Error has occured during the addition of war rate details";
					else if(mode.equalsIgnoreCase("edit"))
						error = "Error has occured during the updation of war rate details";

					req.setAttribute("error", error);
				}

				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/WarRateMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
            }
		}
		else if (requestFrom.equalsIgnoreCase("getWarRateDtls"))
		{
			String[][] warRateDtls = new String[0][0];
			String transid = req.getParameter("transid")==null?"":req.getParameter("transid");
			String branchCode = (String)session.getAttribute("LoginBranchCode");
			warRateDtls = ra.getWarRateDtls(transid, branchCode);
			RequestDispatcher dispatcher = null;
			if(transid.equalsIgnoreCase("") || transid.equalsIgnoreCase("select"))
			{
				String error = "Please, Select Mode Of Transport";
				req.setAttribute("error", error);
				req.setAttribute("warRateDtls", warRateDtls);
				dispatcher = req.getRequestDispatcher("/ratingAdmin/WarRateMaster.jsp?requestfrom=show");
			} else {
				req.setAttribute("warRateDtls", warRateDtls);
				dispatcher = req.getRequestDispatcher("/ratingAdmin/WarRateMaster.jsp?requestfrom=addWarRateMaster&mode=edit");				
			}
			
			if (dispatcher != null)
				dispatcher.forward(req, res);
		}else if (requestFrom.equalsIgnoreCase("addMaterialTypeMaster"))
		{
			String matID = req.getParameter("matID") == null ? "" : req.getParameter("matID");
			String matDesc = req.getParameter("matDesc") == null ? "" : req.getParameter("matDesc");
			String matRate = req.getParameter("matRate") == null ? "" : req.getParameter("matRate");
			String branchCode = req.getParameter("branchCode") == null ? "" : req.getParameter("branchCode");
			String coreApplnCode = req.getParameter("coreApplnCode") == null ? "" : req.getParameter("coreApplnCode");
			String effectiveDate = req.getParameter("effectiveDate") == null ? "" : req.getParameter("effectiveDate");
			String remarks = req.getParameter("remarks") == null ? "" : req.getParameter("remarks");
			String status = req.getParameter("status") == null ? "" : req.getParameter("status");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			String msg = "";

			ra.setMode(mode);
			ra.setBranch_code(branchCode);
			ra.setMatID(matID);
			ra.setMatDesc(matDesc);
			ra.setMatRate(matRate);
			ra.setCoreApplicationCode(coreApplnCode);
			ra.setEffective_date(effectiveDate);
			ra.setRemarks(remarks);
			ra.setStatus(status);
			
			ra.validateMaterialTypeMaster(mode);
			String error = ra.getError();
			
			if (error.length() > 0) {
				System.out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/MaterialTypeMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
            else {
				String process = ra.insertMaterialTypeDetails(mode);
				System.out.println("error ifnfo " + error);
				if (process.equalsIgnoreCase("success")) {
					if(mode.equalsIgnoreCase("add"))
						msg = "Material Type details has been added successfully";
					else if(mode.equalsIgnoreCase("edit"))
						msg = "Material Type details has been updated successfully";
					
					req.setAttribute("msg", msg);
				}
                else {
					if(mode.equalsIgnoreCase("add"))
						error = "Error has occured during the addition of material type details";
					else if(mode.equalsIgnoreCase("edit"))
						error = "Error has occured during the updation of material type details";

					req.setAttribute("error", error);
				}

				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/MaterialTypeMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
            }
		}
		else if (requestFrom.equalsIgnoreCase("getMaterialTypeDtls"))
		{
			String[][] matTypeDtls = new String[0][0];
			String branchCode = req.getParameter("branchCode")==null?"":req.getParameter("branchCode");
			String matID = req.getParameter("matID")==null?"":req.getParameter("matID");
			String error = "";
			
			RequestDispatcher dispatcher = null;
			if(branchCode.equalsIgnoreCase("") || branchCode.equalsIgnoreCase("select"))
				error = "Please, Select Branch";
			
			if((!branchCode.equalsIgnoreCase("") && !branchCode.equalsIgnoreCase("select")) && (matID.equalsIgnoreCase("") || matID.equalsIgnoreCase("select")))
				error = error + "Please, Select Material Desc";

			if(error.length() > 0)
			{
				req.setAttribute("error", error);
				dispatcher = req.getRequestDispatcher("/ratingAdmin/MaterialTypeMaster.jsp?requestfrom=show");
			} else {
				matTypeDtls = ra.getmatTypeDtls(matID,branchCode);
				req.setAttribute("matTypeDtls", matTypeDtls);
				dispatcher = req.getRequestDispatcher("/ratingAdmin/MaterialTypeMaster.jsp?requestfrom=addMaterialTypeMaster&mode=edit");				
			}
			
			if (dispatcher != null)
				dispatcher.forward(req, res);
		}
		else if (requestFrom.equalsIgnoreCase("saleTerm"))
		{

			String[][] brokerDetails = new String[0][0];
			String saleTerm = req.getParameter("saleTerm") == null ? "" : req.getParameter("saleTerm");
			//String amendid = req.getParameter("amendid" + commodity) == null ? "": req.getParameter("amendid" + commodity);
			//String status = req.getParameter("radiobutton") == null ? "" : req.getParameter("radiobutton");
			String error = "";
			if (saleTerm.equals("")|| saleTerm.trim().equalsIgnoreCase("select")) {
				error = "Select Sale Term";
			}
			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/saleTerm.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				req.setAttribute("saleTerm", saleTerm);
				//req.setAttribute("amendid", amendid);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/sale.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		if (requestFrom.equalsIgnoreCase("countryEntry")) {

			String[][] brokerDetails = new String[0][0];
			String commodity = req.getParameter("commodity") == null ? "" : req.getParameter("commodity");
			String amendid = req.getParameter("amendid" + commodity) == null ? "": req.getParameter("amendid" + commodity);
			String status = req.getParameter("radiobutton") == null ? "" : req.getParameter("radiobutton");
			String error = "";
			if (commodity.equals("")|| commodity.trim().equalsIgnoreCase("select")) {
				error = "Select Country";
			}
			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/CountryEntry.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				req.setAttribute("commodity", commodity);
				req.setAttribute("amendid", amendid);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/ratingAdmin/country123.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (requestFrom.equalsIgnoreCase("policyEntry")) {

			String error = "";
			String concatValue = req.getParameter("ConcatValue");
			String agencyCode = req.getParameter("AgencyCode");
			String productId = req.getParameter("ProductId");

			agencyCode = agencyCode==null?"":agencyCode;
			productId =  productId==null?"":productId;
			concatValue = concatValue==null?"":concatValue;

			if(concatValue.equalsIgnoreCase("null") || concatValue.equalsIgnoreCase("") || concatValue.equalsIgnoreCase("select")){
				error = "Select Broker";
			}
			/*
			String[][] brokerDetails = new String[0][0];
			String commodity = req.getParameter("commodity") == null ? "" : req	.getParameter("commodity");
			String amendid = req.getParameter("amendid" + commodity) == null ? "": req.getParameter("amendid" + commodity);
			String status = req.getParameter("radiobutton") == null ? "" : req.getParameter("radiobutton");
			if (commodity.equals("") || commodity.trim().equalsIgnoreCase("select")) {
				error = "Select Type";
			}*/

			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/PolicyEntry123.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
				return;
			}
			else {
				/*req.setAttribute("commodity", commodity);
				req.setAttribute("amendid", amendid);*/

				req.setAttribute("AgencyCode",agencyCode);
				req.setAttribute("ProductId",productId);

				RequestDispatcher dispatcher = req.getRequestDispatcher("/ratingAdmin/policy123.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
				return;
			}
		}
		else if (requestFrom.equalsIgnoreCase("user1")) {
			String selectedBroker = req.getParameter("selectbroker") == null ? "": req.getParameter("selectbroker");
			String selectdiscarded = req.getParameter("selectdiscarded") == null ? "": req.getParameter("selectdiscarded");
			String status = "";
			String[][] allBrokers = new String[0][0];
			if (selectedBroker.equalsIgnoreCase("ALL")) {
				int noofupdations = 0;
				String brokerStatus = "";
				if (selectdiscarded.equalsIgnoreCase("A")) {
					allBrokers = bc.getBrokers(selectedBroker);
					selectdiscarded = "D";
				} else if (!selectdiscarded.equalsIgnoreCase("0")) {
					bc.setDisStatus(selectdiscarded);
					allBrokers = bc.getBrokers(selectedBroker);
				}
				String error = "";
				if (allBrokers.length > 0) {
					for (int i = 0; i < allBrokers.length; i++) {
						brokerStatus = req.getParameter("brokers"+ allBrokers[i][3]) == null ? "" : req.getParameter("brokers" + allBrokers[i][3]);
						if (brokerStatus.equalsIgnoreCase(allBrokers[i][3])) {
							status = "Y";

						}
						else {
							if (selectdiscarded.equals("Y"))
								selectdiscarded = "D";
							status = selectdiscarded;
						}
						if (bc.updateStatus(status, allBrokers[i][3]))
							noofupdations++;
					}
					if (noofupdations == allBrokers.length) {
						System.out.println("DATE    "+ com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd)));
						deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
						req.setAttribute("status", status);
						req.setAttribute("deActivatedDate", deActivatedDate);
						RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/conformDeactivate.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					}
				}
				else {
					if (selectdiscarded.equalsIgnoreCase("0")) {
						error = "Please Select Status";
					}
					else {
						error = "No Records With this Status";
					}
					req.setAttribute("error", error);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/users1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
			else if (selectedBroker.equalsIgnoreCase("0")) {
				String error = "Please Select The Broker/All";
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/users1.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else {
				String brokerStatus = "";
				if (selectedBroker.length() > 0)
					brokerStatus = req.getParameter("brokers" + selectedBroker) == null ? ""
							: req.getParameter("brokers" + selectedBroker);
				if (brokerStatus.equalsIgnoreCase(selectedBroker)) {
					status = "Y";
				} else {
					status = "D";
				}

					if (bc.updateStatus(status, selectedBroker))
					{

					deActivatedDate = com.maan.common.util.OracleDateConversion
							.ConvertDate("" + simpleFormatter.format(dd));
					req.setAttribute("status", status);
					req.setAttribute("deActivatedDate", deActivatedDate);
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/conformDeactivate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {
				}
			}
		} else if (requestFrom.equalsIgnoreCase(
				"ExchangeRate")) {

			String exchangeRate = req.getParameter("exchangeRate") == null ? ""
					: req.getParameter("exchangeRate");
			String effectDate = req.getParameter("effectDate") == null ? ""
					: req.getParameter("effectDate");
			String currencyType = req.getParameter("currencyType") == null ? ""
					: req.getParameter("currencyType");
			bc.setExchangeRate(exchangeRate);
			bc.setEffectDate(effectDate);
			bc.setCurrencyType(currencyType);
			String error = bc.validateExchange();
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/Exchange_Rate.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				String process = bc.insertExchangeData();
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("from", "exchange");
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}

		} /*else if (requestFrom.equalsIgnoreCase("Currency")) {
			String exchangeRate = req.getParameter("exchangeRate") == null ? ""
					: req.getParameter("exchangeRate");
			String effectDate = req.getParameter("effectDate") == null ? ""
					: req.getParameter("effectDate");
			String currencyType = req.getParameter("currencyType") == null ? ""
					: req.getParameter("currencyType");
			bc.setExchangeRate(exchangeRate);
			bc.setEffectDate(effectDate);
			bc.setCurrencyType(currencyType);	
			String error = bc.validateCurrencyType("edit");
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/Currency.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				String process = bc.insertExchangeData();
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("from", "exchange");
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}*/ else if (requestFrom.equalsIgnoreCase(
				"cutomerlist")) {
			String indexvalue = req.getParameter("indexvalue") == null ? ""
					: req.getParameter("indexvalue");
			String customername = req.getParameter("customername") == null ? ""
					: req.getParameter("customername");
			if (indexvalue != null && indexvalue.length() > 0
					&& !indexvalue.equals("")) {
			} else if (customername != null && customername.length() > 0
					&& !customername.equals("")) {
			} else {
				req.setAttribute("error", "Please Enter CustomerName");
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/CustomerList.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (requestFrom.equalsIgnoreCase(
				"BrokerLoginCreation")) {

			String bcode = req.getParameter("bcode") == null ? "" : req
					.getParameter("bcode");
			loginPersonId = req.getParameter("loginPersonId") == null ? ""
					: req.getParameter("loginPersonId");
			String brokerId = req.getParameter("BrokerId") == null ? "" : req
					.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req
					.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? ""
					: req.getParameter("RetypePassword");
			bc.setBrokerId(brokerId);
			bc.setPassword(password);
			bc.setRetypePassword(retypePassword);



			String error = bc.validateLoginCreation();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/BrokerLoginCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {

				bc.setLoginPersonId(loginPersonId);

				String process = bc.insertBrokerLogin(bcode, loginPersonId);
				if (process.equalsIgnoreCase("123")) {
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);

				}

			}
		} else if (requestFrom.trim().equalsIgnoreCase(
				"portfolio")) {
			try {
				// out.println("<br>readiobeforeeeee button value
				// is>>>>>>>>>>>>>>>>>");

				String error = "";
				String values = null;

				String[][] right = new String[0][0];

				RequestDispatcher rd = null;

				com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req
						.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req
						.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req
						.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req
						.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""
						: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req
						.getParameter("dobYear1"));
				cc.setCompany(req.getParameter("company") == null ? "" : req
						.getParameter("company"));
				cc.setRep(req.getParameter("rep") == null ? "" : req
						.getParameter("rep"));
				cc.setData1(req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-"
						+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-"
						+ req.getParameter("dobYear1"));
				String print123 = req.getParameter("print123") == null ? ""
						: req.getParameter("print123");
				String data1 = (req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-" + req
						.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-" + req
						.getParameter("dobYear1"));

				// out.println("<br>readiobeforeeeee button value is"+data1);
				// out.println("<br>readio beforeeeeeeeeeebutton value
				// is"+data2);
				if (!print123.equalsIgnoreCase("YES")) {
					error = cc.validateTrashFields();

				} else {
					data1 = req.getParameter("data1") == null ? "" : req
							.getParameter("data1");
					data2 = req.getParameter("data2") == null ? "" : req
							.getParameter("data2");
				}

				String comp = req.getParameter("company");
				String rep1 = req.getParameter("rep");

				out.println("<br>policy no is" + comp);
				out.println("<br>radio button value is" + rep1);

				out.println("<br>radio printttttttttttttttttt value is"
						+ print123);

				// System.out.println("-----------actionPath------"+req.getParameter("actionPath"));
				System.out.println("error" + error);
				out
						.println("in controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>ererere>>>>>"
								+ error);
				out
						.println("in controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>ererere>>>>>"
								+ error.length());

				if (error.length() > 0) {
					out
							.println("in controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
									+ error.length());
					req.setAttribute("errorDetail", error);
					rd = req.getRequestDispatcher("AdminPortfolio.jsp");

				} else {

					req.setAttribute("data1", data1);
					req.setAttribute("data2", data2);
					req.setAttribute("rep1", rep1);
					// req.setAttribute("company",comp);
					/*
					 * if(print123.equalsIgnoreCase("YES")) { rd =
					 * req.getRequestDispatcher("report123_print.jsp"); } else {
					 */
					rd = req
							.getRequestDispatcher("Approved_Policy_Portfolio.jsp");
					// rd =req.getRequestDispatcher("PortfolioReport.jsp");
					out.println("<br>readiobeforeeeee button value is" + data1);
					out.println("<br>readio  beforeeeeeeeeeebutton value is"
							+ data2);
					out.println("<br>readio  beforeeeeeeeeeebutton value is");
					out
							.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"
									+ rd);

					// }

				}

				out.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"
						+ rd);
				// out.println("<br>readiobeforeeeee button value is"+data1);
				// out.println("<br>readio beforeeeeeeeeeebutton value
				// is"+data2);
				// out.println("<br>readio beforeeeeeeeeeebutton value
				// is"+data2);
				out.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"
						+ rd);
				rd.forward(req, res);

				if (true)
					return;
				// out.println("in
				// controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+error);
				if (true)
					return;

			} catch (Exception e) {
				System.out.println("SKR TEST START " + e);
			}

		} else

		if (requestFrom.equalsIgnoreCase("reports")) {
			try {

				String error = "";
				String values = null;
				String[][] right = new String[0][0];
				RequestDispatcher rd = null;

				com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req
						.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req
						.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req
						.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req
						.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""
						: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req
						.getParameter("dobYear1"));
				cc.setCompany(req.getParameter("company") == null ? "" : req
						.getParameter("company"));
				cc.setRep(req.getParameter("rep") == null ? "" : req
						.getParameter("rep"));
				cc.setData1(req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-"
						+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-"
						+ req.getParameter("dobYear1"));
				String print123 = req.getParameter("print123") == null ? ""
						: req.getParameter("print123");
				String data1 = (req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-" + req
						.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-" + req
						.getParameter("dobYear1"));
				if (!print123.equalsIgnoreCase("YES")) {
					error = cc.validateFields(requestFrom);

				} else {
					data1 = req.getParameter("data1") == null ? "" : req
							.getParameter("data1");
					data2 = req.getParameter("data2") == null ? "" : req
							.getParameter("data2");
				}

				String comp = req.getParameter("company");
				String rep1 = req.getParameter("rep");

				// System.out.println("policy no is"+comp);
				// System.out.println("readio button value is"+rep1);
				// System.out.println("readio printttttttttttttttttt value
				// is"+print123);
				// System.out.println("-----------actionPath------"+req.getParameter("actionPath"));
				System.out.println("error" + error);
				out.println("in controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

				if (error.length() > 0) {
					out
							.println("in controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
									+ error.length());
					req.setAttribute("errorDetail", error);
					rd = req.getRequestDispatcher("policyreport.jsp");

				} else {

					String customername = req.getParameter("rep");

					if ("c".equalsIgnoreCase(customername)) {
						right = cc.customervalues(comp);

						req.setAttribute("wrong", right);
					} else if (customername.equals("p")) {

						right = cc.policyvalues(comp);
						req.setAttribute("wrong", right);
					}

					req.setAttribute("data1", data1);
					req.setAttribute("data2", data2);
					req.setAttribute("rep1", rep1);
					req.setAttribute("company", comp);
					if (print123.equalsIgnoreCase("YES")) {
						rd = req.getRequestDispatcher("report123_print.jsp");
					} else {
						rd = req.getRequestDispatcher("report123.jsp");
					}

				}

				rd.forward(req, res);

				if (true)
					return;

			} catch (Exception e) {
				System.out.println("SKR TEST START " + e);
			}
		} else if (requestFrom.equalsIgnoreCase(
				"policystatus"))

		{

			String str = req.getParameter("t1");
			String str1 = req.getParameter("PStatus");
			ra.setQuoteno(req.getParameter("t1") == null ? "" : req.getParameter("t1"));
			ra.setStat(req.getParameter("PStatus") == null ? "" : req.getParameter("PStatus"));
			String str2 = ra.getQuoteno();
			String str3 = ra.getStat();
			out.println(str2);
			out.println(str3);
			String errmsg = new String();
			errmsg = (String) ra.validations();
			RequestDispatcher rd = null;

			if (errmsg.length() > 0) {
				req.setAttribute("ErrorMsg", errmsg);

				rd = req
						.getRequestDispatcher("../ratingAdmin/PolicyStatus.jsp");
				rd.forward(req, res);

			}

			else {
				int result = (int) ra.updat();
				if (result != 0) {
					String err = " Record updated SuccessFully";
					req.setAttribute("ErrorMsg", err);
					rd = req
							.getRequestDispatcher("../ratingAdmin/PolicyStatus.jsp");
					rd.forward(req, res);
				} else

				{
					String err = "Record Not Found ";
					req.setAttribute("ErrorMsg", err);
					rd = req
							.getRequestDispatcher("../ratingAdmin/PolicyStatus.jsp");
					rd.forward(req, res);
				}

			}

			if (true)
				return;

		}
		else if("ToleranceSelect".equalsIgnoreCase(requestFrom)){



            String toleranceId = req.getParameter("tolerance");
            toleranceId = toleranceId==null?"":toleranceId;

            String error = "";
            if("".equalsIgnoreCase(toleranceId) || "null".equalsIgnoreCase(toleranceId) || "select".equalsIgnoreCase(toleranceId)){
                error = "* Please Select Tolerance to edit<br>";
            }
            System.out.println("Error Value in servlet for Tolerance master "+error);
            RequestDispatcher rd = null;
            if(error.length() > 0){
                req.setAttribute("error",error);
                rd = req.getRequestDispatcher("/ratingAdmin/ToleranceMaster.jsp");
            }
            else{
                 rd = req.getRequestDispatcher("/ratingAdmin/EditToleranceDetails.jsp");
            }

            if(rd != null){
                rd.forward(req,res);
            }
            return;
        }
        else if("ToleranceEditDetail".equalsIgnoreCase(requestFrom)){

           String toleranceId    = req.getParameter("ToleranceId");
           String toleranceName  = req.getParameter("ToleranceName");
           String toleranceValue = req.getParameter("ToleranceValue");
           String rsaCode        = req.getParameter("RSACode");
           String remarks        = req.getParameter("Remarks");
           String status         = req.getParameter("status");
           String mode           = req.getParameter("mode");
           String branch = (String)session.getAttribute("LoginBranchCode");

           toleranceId    = toleranceId==null?"":toleranceId;
           toleranceName  = toleranceName==null?"":toleranceName;
           toleranceValue = toleranceValue==null?"":toleranceValue;
           rsaCode        = rsaCode==null?"":rsaCode;
           remarks        = remarks==null?"":remarks;
           status         = status==null?"":status;
           mode           = mode==null?"":mode;
           com.maan.admin.ratingAdmin bean = new com.maan.admin.ratingAdmin();

           bean.setToleranceId(toleranceId);
           bean.setToleranceName(toleranceName);
           bean.setToleranceValue(toleranceValue);
           bean.setRsacode(rsaCode);
           bean.setRemarks(remarks);
           bean.setStatus(status);
           bean.setBranch_code(branch);
           String error =  bean.validateToleranceDetails();

           RequestDispatcher reqDis = null;

           if(error.length() >0){
                req.setAttribute("error",error);
                reqDis = req.getRequestDispatcher("/ratingAdmin/EditToleranceDetails.jsp");
           }
           else{
               bean.insertOrUpdateToleranceDetails(mode);
               reqDis = req.getRequestDispatcher("/ratingAdmin/ToleranceMaster.jsp");
           }
           if(reqDis != null){
                reqDis.forward(req,res);
           }
        }
        else if("VesselSelect".equalsIgnoreCase(requestFrom)){

             String error = "";
             String vesselId = req.getParameter("vessel");
             vesselId = (vesselId==null || "".equalsIgnoreCase(vesselId) || "null".equalsIgnoreCase(vesselId) || "select".equalsIgnoreCase(vesselId))?"":vesselId;

             if(vesselId.length() == 0){
                  error="Please select vessel to edit";
             }
             RequestDispatcher reqDis = null;
             if(error.length() > 0){
                  req.setAttribute("error",error);
                  reqDis = req.getRequestDispatcher("/ratingAdmin/VesselMaster.jsp");
             }
             else{
                 reqDis = req.getRequestDispatcher("/ratingAdmin/EditVesselDetails.jsp");
             }
             if(reqDis != null){
                   reqDis.forward(req,res);
             }
        }
        else if("VesselEditDetail".equalsIgnoreCase(requestFrom)){

           String vesselId       = req.getParameter("VesselId")==null?"":req.getParameter("VesselId");
           String vesselName     = req.getParameter("VesselName")==null?"":req.getParameter("VesselName");
           String rsaCode        = req.getParameter("RSACode")==null?"":req.getParameter("RSACode");
           String vesselClass	 = req.getParameter("VesselClass")==null?"":req.getParameter("VesselClass");
           int manufactureYear	 = req.getParameter("ManufactureYear")==null?0:Integer.parseInt(req.getParameter("ManufactureYear"));
           String remarks        = req.getParameter("Remarks")==null?"":req.getParameter("Remarks");
           String status         = req.getParameter("status")==null?"":req.getParameter("status");
           String branch         = req.getParameter("LoginBranchCode")==null?"":(String)session.getAttribute("LoginBranchCode");
           String mode           = req.getParameter("mode")==null?"":req.getParameter("mode");

           com.maan.admin.ratingAdmin bean = new com.maan.admin.ratingAdmin();

           bean.setVesselId(vesselId);
           bean.setVesselName(vesselName);
           bean.setRsacode(rsaCode);
           bean.setVesselClass(vesselClass);
           bean.setManufactureYear(manufactureYear);
           bean.setRemarks(remarks);
           bean.setStatus(status);
           bean.setBranch_code(branch);

           String error = bean.validateVesselDetails();
           RequestDispatcher reqDis = null;

           if(error.length() >0){
               reqDis = req.getRequestDispatcher("/ratingAdmin/EditVesselDetails.jsp");
               req.setAttribute("error",error.toString());
           }
           else{
        	   error = bean.insertOrUpdateVesselDetails(mode);
               reqDis = req.getRequestDispatcher("/ratingAdmin/VesselMaster.jsp");
               req.setAttribute("msg",error.toString());
           }
           if(reqDis != null){
                reqDis.forward(req,res);
           }
        }else if (requestFrom.equalsIgnoreCase("ReferralMail")){
			
			RequestDispatcher dispatcher=null;
			
			String emailNo=req.getParameter("emailNo")==null?"":(String)req.getParameter("emailNo");
			
			if(emailNo!=null){
				System.out.println("admincontroller....referralmails...");
				
				String pid=req.getParameter("pid")==null?"":(String)req.getParameter("pid");
				String branchCode=(String)session.getAttribute("LoginBranchCode");
				String mails[][]=(String[][])ab.getReferralMails(branchCode, emailNo);
				req.setAttribute("pid", pid);
				req.setAttribute("emails", mails);
				System.out.println("mais admin controller......."+mails);
			}
			dispatcher=req.getRequestDispatcher("../ratingAdmin/ReferralMails.jsp");
			if(dispatcher!=null){
				dispatcher.forward(req, res);
			}
			
			
		}
        else if(requestFrom.equalsIgnoreCase("procpol"))
        {
        	RequestDispatcher dispatcher=null;
        	String error="";
        	String startdate=req.getParameter("startdate")==null?"":req.getParameter("startdate");
        	System.out.println("startdate---->:"+startdate);
        	String enddate=req.getParameter("enddate")==null?"":req.getParameter("enddate");
        	String product=req.getParameter("product")==null?"":req.getParameter("product");
        	 ///String branch=request.getParameter("branch")==null?"":request.getParameter("branch").trim();
        	String status=req.getParameter("status")==null?"":req.getParameter("status");
        	String policyno=req.getParameter("policyno")==null?"":req.getParameter("policyno");
        	String branch=req.getParameter("branch")==null?"":req.getParameter("branch");
        	String partid=req.getParameter("partid")==null?"":req.getParameter("partid");
    		String Type=req.getParameter("Type")==null?"":req.getParameter("Type");
    		String ckey=req.getParameter("ckey")==null?"":req.getParameter("ckey");        	   
    		String cmsvalue=req.getParameter("txtCmsValue")==null?"":req.getParameter("txtCmsValue");
    		String ocidcode=req.getParameter("txtOcidCode")==null?"":req.getParameter("txtOcidCode");
    		String rownum=req.getParameter("rownum")==null?"":req.getParameter("rownum");
    		String firstr=req.getParameter("first")==null?"1":req.getParameter("first");
    		System.out.println("status:"+status);
    		int spage = 1;
    		int start = 1;
    			if (req.getParameter("spage") != null
    									&& !req.getParameter("spage").equalsIgnoreCase(""))
    								spage = req.getParameter("spage") == null ? 1 : Integer
    								.parseInt(req.getParameter("spage"));
    							if (req.getParameter("start") != null
    									&& !req.getParameter("start").equalsIgnoreCase(""))
    								start = req.getParameter("start") == null ? 1: Integer
    								.parseInt(req.getParameter("start"));
    		String process="";
     /////////////////////       PARTIALLY INTEGRATION PROCEDURE EXECUTE    
			if (status.equals("P") && !policyno.equals("1"))
{
				System.out.println("policyno:" + policyno);
				System.out.println("ckey:" + ckey);
				System.out.println("cmsvalue:" + cmsvalue);
				System.out.println("partid:" + partid);
				System.out.println("Type:" + Type);
				System.out.println("ocidcode:" + ocidcode);
				if (Type.equals("U")) {
					ra.setCmsvalue(cmsvalue);
					ra.setOcidcode(ocidcode);
					ra.validatePolicyIntegration(branch);
					error=ra.getError();
					System.out.println("error:"+error);
					if(error.length()>0)
					 {						
						req.setAttribute("error", error);
						dispatcher = req.getRequestDispatcher("../reports/PolicyIntegrationPopUp.jsp");
						
						if (dispatcher != null)
						{
							dispatcher.forward(req, res);
						}
					 }
					else
					{
						System.out.println("elsepartially");
					     process = ra.insertPolicyProc(policyno,ckey, cmsvalue,ocidcode);	
					     System.out.println("hello");
					     System.out.println("process:"+process);
					if (process.equals("Yes"))
					{
						error = "policyno:<font color=blue><b>" + policyno
								+ "</b></font>:successfully Integrated";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/PolicySuccessMessage.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
					}
					else
					{
						error = "policyno:<font color=blue><b>" + policyno
								+ "</b></font>:Integration failed";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/PolicyIntegrationPopUp.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
					}
				}
				}
				else
					{
					if(cmsvalue.equals(""))
					{
						cmsvalue="0";
					}
						System.out.println("elsepartially");
						System.out.println("policyno"+policyno);
						System.out.println("ckey"+ckey);
						System.out.println("cmsvalue"+cmsvalue);
						System.out.println("Type"+Type);
					     process = ra.insertPolicyProc(policyno,ckey, cmsvalue,Type);	
					     System.out.println("hello"+process);
					if (process.trim().equalsIgnoreCase("Y"))
					{
						error = "policyno:<font color=blue><b>" + policyno
								+ "</b></font>:successfully Integrated";
						req.setAttribute("error", error);
						System.out.println("error"+error);
						
						dispatcher = req
								.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
					}
					else if(process.trim().equalsIgnoreCase("P"))
					{  
						error = "<font color=red><b> Partailly Integrated With Old CKEY </b></font>";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
				  }
					else 
					{  
						error = "<font color=red><b> Partailly Integration Failed </b></font>";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
				  }
								
			}
}
				
 // /////////////////// BACK TO POLICY REPORT///////////////////////////////
		
else if (policyno.equals("1"))
{
				req.setAttribute("startdate", startdate);
				req.setAttribute("enddate", enddate);
				req.setAttribute("product", product);
				req.setAttribute("branch", branch);
				dispatcher = req
						.getRequestDispatcher("../reports/PolicyReport.jsp");
				if (dispatcher != null) {
					dispatcher.forward(req, res);
				}
}
			// /////////////////// INTEGRATION ERROR PROCEDURE EXECUTED   ////////////////////////////////////////////////
else
{
				 ra.setOldvalue(policyno);
				 process = ra.insertPolicyProc(policyno, ckey, cmsvalue,product);		
				 		////	System.out.println("process:------>"+process);
				if (process.equals("Y")) {
					error = "policyno:<font color=blue><b>" + policyno
							+ "</b></font>:successfully Integrated";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				} else if(process.equals("P")) {
					error = "<font color=red><b> Partially Integrated</b></font>";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				}
				else  {
					error = "<font color=red><b>Integration Failed</b></font>";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/PolicyReportDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				}
}

}
      
        else if(requestFrom.equalsIgnoreCase("OpenCover"))
        {
        	RequestDispatcher dispatcher=null;
        	String error="";
        	String startdate=req.getParameter("startdate")==null?"":req.getParameter("startdate");
        	System.out.println("startdate---->:"+startdate);
        	String enddate=req.getParameter("enddate")==null?"":req.getParameter("enddate");
        	String product=req.getParameter("product")==null?"":req.getParameter("product");
        	 ///String branch=request.getParameter("branch")==null?"":request.getParameter("branch").trim();
        	String status=req.getParameter("status")==null?"":req.getParameter("status");
        	String policyno=req.getParameter("policyno")==null?"":req.getParameter("policyno");
        	String branch=req.getParameter("branch")==null?"":req.getParameter("branch");
        	String partid=req.getParameter("partid")==null?"":req.getParameter("partid");
    		String Type=req.getParameter("Type")==null?"":req.getParameter("Type");
    		String ckey=req.getParameter("ckey")==null?"":req.getParameter("ckey");        	   
    		String cmsvalue=req.getParameter("txtCmsValue")==null?"":req.getParameter("txtCmsValue");
    		String ocidcode=req.getParameter("txtOcidCode")==null?"":req.getParameter("txtOcidCode");
    		String rownum=req.getParameter("rownum")==null?"":req.getParameter("rownum");
    		String firstr=req.getParameter("first")==null?"1":req.getParameter("first");
    		System.out.println("status:"+status);
    		int spage = 1;
    		int start = 1;
    			if (req.getParameter("spage") != null
    									&& !req.getParameter("spage").equalsIgnoreCase(""))
    								spage = req.getParameter("spage") == null ? 1 : Integer
    								.parseInt(req.getParameter("spage"));
    							if (req.getParameter("start") != null
    									&& !req.getParameter("start").equalsIgnoreCase(""))
    								start = req.getParameter("start") == null ? 1: Integer
    								.parseInt(req.getParameter("start"));
    		String process="";
     /////////////////////       PARTIALLY INTEGRATION PROCEDURE EXECUTE    
			if (status.equals("P") && !policyno.equals("1"))
{
				System.out.println("====PARTIAL BLOCK====");
				System.out.println("policyno:" + policyno);
				System.out.println("ckey:" + ckey);
				System.out.println("cmsvalue:" + cmsvalue);
				System.out.println("partid:" + partid);
				System.out.println("Type:" + Type);
				System.out.println("ocidcode:" + ocidcode);
				if (Type.equals("R")) 
					{
						System.out.println("policyno"+policyno);
						System.out.println("ckey"+ckey);
						System.out.println("cmsvalue"+cmsvalue);
						System.out.println("Type"+Type);
					    process = ra.insertOpenPolicyProc(policyno,ckey, cmsvalue,Type);	
					    System.out.println("hello"+process);
					if (process.trim().equalsIgnoreCase("Y"))
					{
						error = "policyno:<font color=blue><b>" + policyno
								+ "</b></font>:successfully Integrated";
						req.setAttribute("error", error);
						System.out.println("error"+error);
						
						dispatcher = req
								.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
					}
					else if(process.trim().equalsIgnoreCase("P"))
					{  
						error = "<font color=red><b> Partailly Integrated</b></font>";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
				  }
					else 
					{  
						error = "<font color=red><b> Partailly Integration Failed </b></font>";
						req.setAttribute("error", error);
						dispatcher = req
								.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
						if (dispatcher != null) 
						{
							dispatcher.forward(req, res);
						}
				  }
								
			}
}
				
 // /////////////////// BACK TO POLICY REPORT///////////////////////////////
		
else if (policyno.equals("1"))
{
				req.setAttribute("startdate", startdate);
				req.setAttribute("enddate", enddate);
				req.setAttribute("product", product);
				req.setAttribute("branch", branch);
				dispatcher = req
						.getRequestDispatcher("../reports/OpenCoverPolicyReport.jsp");
				if (dispatcher != null) {
					dispatcher.forward(req, res);
				}
}
			// /////////////////// INTEGRATION ERROR PROCEDURE EXECUTED   ////////////////////////////////////////////////
else
{
	             System.out.println("====ERROR BLOCK====");
				 ra.setOldvalue(policyno);
				 process = ra.insertOpenPolicyProc(policyno, ckey, cmsvalue,product);		
				 		////	System.out.println("process:------>"+process);
				if (process.equals("Y")) {
					error = "policyno:<font color=blue><b>" + policyno
							+ "</b></font>:successfully Integrated";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				} else if(process.equals("P")) {
					error = "<font color=red><b> Partially Integrated</b></font>";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				}
				else  {
					error = "<font color=red><b>Integration Failed</b></font>";
					req.setAttribute("error", error);
					dispatcher = req
							.getRequestDispatcher("../reports/OpenCoverDetails.jsp");
					if (dispatcher != null) {
						dispatcher.forward(req, res);
					}
				}
}

}
		
        else if (requestFrom.equalsIgnoreCase("ReferralMailSubmit")){
			
			RequestDispatcher dispatcher=null;
			int emailToNo=Integer.parseInt(req.getParameter("emailToNo")==null?"0":(String)req.getParameter("emailToNo"));
			int emailCCNo=Integer.parseInt(req.getParameter("emailCCNo")==null?"0":(String)req.getParameter("emailCCNo"));
			String emailNo=req.getParameter("emailNo")==null?"":(String)req.getParameter("emailNo");
			String addEmailTo=req.getParameter("addEmailTo")==null?"":(String)req.getParameter("addEmailTo");
			String addEmailcc=req.getParameter("addEmailcc")==null?"":(String)req.getParameter("addEmailcc");
			
			System.out.println("admincontroller mail submit block...."+emailNo);
			String emailTo="";
			String emailCC="";
			String errTest="";
			String mail="";
			System.out.println("emailToNo....admincontroller....."+emailToNo);
			System.out.println("emailCCNo....admincontroller....."+emailCCNo);
			for(int i=0;i<emailToNo;i++){
			mail=req.getParameter("emailTo"+String.valueOf(i))==null?"":(String)req.getParameter("emailTo"+String.valueOf(i));
			System.out.println("mail"+i+" : "+mail);
			//errTest=ab.valiadateMail(mail, "emailTo"+String.valueOf(i));
			System.out.println("error ....testt...variable---->"+errTest+"mail ID=====>"+mail+"....email box name====>"+"emailTo"+String.valueOf(i));
			System.out.println("mail...admincontroller...."+mail);
			if("".equalsIgnoreCase(mail)){
				continue;
			}
			
			emailTo=emailTo+mail+((i==(emailToNo-1))?"":",");
			System.out.println("admincontroller......referral mail submit block....individual mail.TO..."+emailTo);
			}
			//ab.valiadateMail(addEmailTo, "ExtraEmailTo");
			System.out.println("admincontroller......referral mail submit block....individual mail.TO..."+emailTo);
			emailTo=emailTo+(("".equalsIgnoreCase(addEmailTo))?"":(("".equalsIgnoreCase(emailTo))?addEmailTo:(","+addEmailTo)));
			System.out.println("admincontroller REferralMailSubmit block......merged email TO"+emailTo);
			
			for(int i=0;i<emailCCNo;i++){
				mail=req.getParameter("emailcc"+String.valueOf(i))==null?"":(String)req.getParameter("emailcc"+String.valueOf(i));
				//ab.valiadateMail(mail, "emailcc"+String.valueOf(i));
				if("".equalsIgnoreCase(mail)){
					continue;
				}
				
			emailCC=emailCC+mail+((i==(emailCCNo-1))?"":",");
			}
			//ab.valiadateMail(addEmailcc, "ExtraEmailcc");
			emailCC=emailCC+("".equalsIgnoreCase(addEmailcc)?"":("".equalsIgnoreCase(emailCC)?addEmailcc:(","+addEmailcc)));
			System.out.println("admincontroller REferralMailSubmit block......merged email CC"+emailCC);
			String mailForTest=(emailTo.length()>0 && emailCC.length()>0)?emailTo+","+emailCC:emailTo+emailCC;
			System.out.println("merged mails for validation========>"+mailForTest);
			String error=ab.valiadateMail(mailForTest);			//ab.getError();
			boolean b=false;
			if(error.length()>0){
				System.out.println("admincontroller REferralMailSubmit block.....==>"+error);
				req.setAttribute("errorDetail", error);
				b=false;
			}else{
				String branchCode=(String)session.getAttribute("LoginBranchCode");
				b=ab.updateMails(emailTo, emailCC, branchCode, emailNo);
			}
			System.out.println("b value........"+b);
			if(b==false){
				dispatcher=req.getRequestDispatcher("../ratingAdmin/ReferralMails.jsp");
			}else {
				dispatcher=req.getRequestDispatcher("../ratingAdmin/ReferralMails.jsp?isSuccess=yes");	
			}
			if(dispatcher!=null){
				dispatcher.forward(req, res);
			}	
		}

	}

	public void destroy() {

	}

}