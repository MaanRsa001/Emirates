package com.maan.services.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.opencover.bean.openCoverQuotation;
public class cargoFlowController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out = null;
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
		HttpSession session=null;
		session=request.getSession(false);
		RequestDispatcher rd	= null;
		response.setContentType("text/html");
		if(session==null || session.getAttribute("ses")==null)
		{
			response.sendRedirect("login/error_messg.jsp");
			return;
		}
		try
		{
			String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;	
			
			if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController) )
			{
				response.sendRedirect("login/error_messg.jsp");
				return;
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ID from session_master controller.java "+e.toString());
			e.printStackTrace();
		}

		String actualFrom = "";
		out=response.getWriter();
		String path=request.getRequestURI();
		System.out.println("Services/Util/CargoFlowController.java---> path.. "+path);
		try
		{
			path=path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		}
		catch(Exception e)
		{
			path = "newquote";actualFrom="premium";
		}
		if("newquote".equalsIgnoreCase(path))
		{
			if("fresh".equalsIgnoreCase(request.getParameter("freshMode")))
			{
				if(session.getAttribute("fullDetailss") !=null)
					session.removeAttribute("fullDetailss");
			}
			if ("fresh".equalsIgnoreCase(request.getParameter("freshMode"))
					&& session.getAttribute("customer_id") != null) {
				session.removeAttribute("customer_id");
			}
					try
					{
							if(session.getAttribute("applicationNo") !=null)
								session.removeAttribute("applicationNo");
							if(session.getAttribute("application_no") !=null)
								session.removeAttribute("application_no");
							if(session.getAttribute("countryDetails") !=null)
								session.removeAttribute("countryDetails");
							
							if(session.getAttribute("quote_no") !=null)
								session.removeAttribute("quote_no");
							
							if(session.getAttribute("fullCommodity") !=null)
								session.removeAttribute("fullCommodity");
							

							if(session.getAttribute("fullDetails") !=null)
								session.removeAttribute("fullDetails");
							
							if(session.getAttribute("sessionCountry") !=null)
								session.removeAttribute("sessionCountry");
							
							if(session.getAttribute("fromCountryId") !=null)
								session.removeAttribute("fromCountryId");
							
							if(session.getAttribute("toCountryId") !=null)
								session.removeAttribute("toCountryId");
				   }
				   catch(Exception e)
					{e.printStackTrace();}
			
			com.maan.premium.DAO.PremiumLogic premiumSummary  = new com.maan.premium.DAO.PremiumLogic();
			String actPath = request.getParameter("actionPath");
			actPath = actPath==null?"":actPath;
			if(actPath.length()<=0&&request.getAttribute("actionPath")!=null)
			{
				actPath = (String)request.getAttribute("actionPath");

			}
			
			String cusIds = "";
			cusIds = request.getParameter("customers")==null?"":request.getParameter("customers");
			
			if(cusIds.length()<=0)
				cusIds = request.getParameter("openCustomerId")==null?"":request.getParameter("openCustomerId");
			
			if(cusIds.length()<=0)
				cusIds = (String)session.getAttribute("customer_id");
			else
				session.setAttribute("customer_id",cusIds);
			
			String userType = (String)session.getAttribute("user1");

			// Customer Screen New quote Button Validation
			/*if((cusIds != null && !cusIds.equalsIgnoreCase("null") && !cusIds.equals("") && cusIds.length() > 0)
					|| userType.equalsIgnoreCase("admin"))
			{*/
				System.out.println("request.getParameter(customers)"+request.getParameter("customers"));
				String applicationNo = "";
				
				if("fresh".equalsIgnoreCase(request.getParameter("newModes")))
				{
					applicationNo = request.getParameter("applicationNo");
					session.setAttribute("applicationNo",applicationNo);
					session.setAttribute("application_no",applicationNo);
				}
				else
				{
					applicationNo = (String)session.getAttribute("application_no");
					if(applicationNo==null)
						applicationNo = (String)session.getAttribute("applicationNo");
				}
				
	
				String quoteNo = request.getParameter("quote_no")==null?(String)session.getAttribute("quote_no"):request.getParameter("quote_no");
				String loginIds = (String)session.getAttribute("user");
				String cids = (String)session.getAttribute("company_id");
				String pids = (String)session.getAttribute("product_id");
				
				
				if(userType.equalsIgnoreCase("admin"))
					pids = request.getParameter("productId")==null?"":request.getParameter("productId");
	
				String brokerBra = (String)session.getAttribute("LoginBranchCode");
				HashMap brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
				String cid="";
				String dcid="";
				String currencyType = "";
				if(brokerDetails.size()>0)
				{
					cid = (String)brokerDetails.get("Orgination");
					dcid = (String)brokerDetails.get("Destination");
					currencyType = (String)brokerDetails.get("CurrencyAbb");
				}
				if("premium/Quotation.jsp".equalsIgnoreCase(actPath)||"premium/QuotationOpen.jsp".equalsIgnoreCase(actPath))
				{
						com.maan.premium.DAO.PremiumInputsBean premiumInputs = new com.maan.premium.DAO.PremiumInputsBean();
						String openCoverNo = request.getParameter("openCoverNo")==null?(String)session.getAttribute("openCoverNo")==null?"0":(String)session.
						getAttribute("openCoverNo"):request.getParameter("openCoverNo");
	
						premiumInputs.setLoginBra(brokerBra);
						premiumInputs.setCid(cid);
						premiumInputs.setProductId(pids);
						premiumInputs.setLoginCode(loginIds);
						premiumInputs.setCompanyId(cids);
						premiumInputs.setOpenCoverNo(openCoverNo);
	
						premiumSummary.setLoginBra(brokerBra);
						premiumSummary.setCid(cid);
						premiumSummary.setProductId(pids);
						premiumSummary.setOpenCoverNo(openCoverNo);
	
						//Mode Of Transport
						String modeDetails[][] = new String[0][0];
						modeDetails = premiumInputs.getModeTypes(brokerBra);
						request.setAttribute("modeDetails",modeDetails); 
						
						//Executives
						String executiveDetails[][] = new String[0][0];
						executiveDetails = premiumInputs.getExecutives(brokerBra);
						request.setAttribute("executiveDetails",executiveDetails); 
						//Covers
						String coverDetails[][] = new String[0][0];
						coverDetails = premiumInputs.getCoverTypes(brokerBra);
						request.setAttribute("coverDetails",coverDetails);
						request.setAttribute("ModeMasterId",premiumInputs.getModeMasterId());
						request.setAttribute("CoverId",premiumInputs.getCoverId());
						request.setAttribute("CoverName",premiumInputs.getCoverName());
						
						String packageDetails[][] = new String[0][0];
						packageDetails = premiumInputs.getPackageTypes(brokerBra);
						System.out.println("Package details:: "+packageDetails[0][0]);
						request.setAttribute("PackageDetails",packageDetails);
						request.setAttribute("ModePackageId",premiumInputs.getModePackageId());
						request.setAttribute("PackageId",premiumInputs.getPackageId());
						request.setAttribute("PackageName",premiumInputs.getPackageName());
						//----------//
						//Currency Details
						String currencyDetails[][] = new String[0][0];
						currencyDetails = premiumInputs.getCurrencyDetails(cid);
						request.setAttribute("currencyDetails",currencyDetails);
						
						// ToleranceDetails
						String toleranceDetails[][] = new String[0][0];
						toleranceDetails = premiumInputs.getToleranceDetails(brokerBra);
						request.setAttribute("toleranceDetails",toleranceDetails);
						// Cover Type Values
						String coverTypesValue[][] = new String[0][0];
						coverTypesValue = premiumInputs.getCoverTypesValue(brokerBra);
						request.setAttribute("coverTypesValue",coverTypesValue);
						
						//SeaOptionsVAlue
						String packageTypesValue[][] = new String[0][0];
						packageTypesValue = premiumInputs.getSeaCoverValues(brokerBra);
						request.setAttribute("packageTypesValue",packageTypesValue);
						// saleTermDetails
						String saleTermDetails[][] = new String[0][0];
						String saleTermPercents[][] = new String[0][0];
						
						String vesselInfo[][] = new String[0][0];
						vesselInfo = premiumInputs.getVesselMaster(brokerBra);
						String vesselMasterNames = premiumInputs.removeLastChar(premiumInputs.getStringFromArray(vesselInfo,1),',');
						request.setAttribute("vesselMasterNames",vesselMasterNames);
							
						if(!userType.equalsIgnoreCase("admin"))
							request.setAttribute("brokerType",premiumInputs.getBrokerType(loginIds, brokerBra, (String)session.getAttribute("RSAISSUER")));//Added New
						
						if(pids.equalsIgnoreCase("3"))
						{					
							//Orinating and Destinating countries
							String contryDetails[][] = new String[0][0];
							contryDetails = premiumSummary.getsCoutrys(cid,"belonging");
							request.setAttribute("contryDetails",contryDetails);	
							String seaCovers[][] = new String[0][0];
							seaCovers = premiumInputs.getSeaCoverValues(brokerBra);
							request.setAttribute("seaCovers",seaCovers);
							
							//saleTermDetails = premiumInputs.getSaleTermDetails(brokerBra);
							saleTermDetails = premiumInputs.getConstantInfo("126",brokerBra);
							saleTermPercents = premiumInputs.getConstantInfo("127",brokerBra);
							request.setAttribute("saleTermDetails",saleTermDetails);
							request.setAttribute("saleTermPercents",saleTermPercents);
						}
						else if(pids.equalsIgnoreCase("11"))
						{
							//For Bank Details
							String[][] bankDetails = new String[0][0];
							bankDetails  = premiumInputs.getBankNames(openCoverNo,cid);
							request.setAttribute("bankDetails",bankDetails);
	
							//Bank Details
							String coverTypesValueBank[][] = new String[0][0];
							coverTypesValueBank = premiumInputs.getCoverTypesValueBank();
							request.setAttribute("coverTypesValueBank",coverTypesValueBank);
	
							//For LC Details
							String[][] LCDetails = new String[0][0];
							LCDetails  = premiumInputs.getLCNumbers(openCoverNo);
							request.setAttribute("LCDetails",LCDetails);
							request.setAttribute("CoverBankId",premiumInputs.getCoverBankId());
							request.setAttribute("CategoryMainBankMasterId",premiumInputs.getCategoryMainBankMasterId());
							request.setAttribute("CategoryMasterBankName",premiumInputs.getCategoryMasterBankName());
							//------//
	
							//Sea options
							request.setAttribute("seaBasedOptions",premiumInputs.getSeaBasedOptions(brokerBra));
	
							//For orgination countries
							String orgCountries[][] = new String[0][0];
							orgCountries = premiumSummary.getsCoutrys("Origination");
							request.setAttribute("orgCountries",orgCountries);
							
							 //For Destianation Countries
							 String desCountries[][] = new String[0][0];
							 desCountries = premiumSummary.getsCoutrys("Destination");
							 request.setAttribute("desCountries",desCountries);
							 
							 saleTermDetails = premiumInputs.getMOCValueBasis(openCoverNo,brokerBra);
							 request.setAttribute("saleTermDetails",saleTermDetails);
							 
							 /*String vesselInfo[][] = new String[0][0];
							 vesselInfo = premiumInputs.getVesselMaster(brokerBra);
							 String vesselMasterNames = premiumInputs.removeLastChar(premiumInputs.getStringFromArray(vesselInfo,1),',');
							 request.setAttribute("vesselMasterNames",vesselMasterNames);*/
							 
							 request.setAttribute("toleranceInfo",premiumInputs.getOpenCoverToleranceInfo(openCoverNo, brokerBra));
							 //Block for Coverage Type
							 String coverTypeInfo[][]= premiumInputs.getOpenCoverCoverTypeInfo(openCoverNo, brokerBra);
							 if(coverTypeInfo!=null && coverTypeInfo.length>0)
							 {
								 request.setAttribute("coverIds",premiumInputs.removeLastChar(premiumInputs.getQuotedStringFromArray(coverTypeInfo,0),','));
								 request.setAttribute("coverTypeIds",premiumInputs.removeLastChar(premiumInputs.getQuotedStringFromArray(coverTypeInfo,1),','));
							 }
					}
					session.setAttribute("identify_Id",cusIds);
					String option =request.getParameter("mode")==null?"":request.getParameter("mode");
					/*System.out.println("first get quoteNo from req/ses...."+quoteNo);
					System.out.println("first get option from req...."+option);*/
					if(applicationNo==null&&option.equalsIgnoreCase("edit"))
					{
						session.setAttribute("quote_no",quoteNo);	
						applicationNo = premiumInputs.getQuoteBasedApplicationNo(quoteNo);
						session.setAttribute("applicationNo",applicationNo);
						session.setAttribute("application_no",applicationNo);
					}
				//	System.out.println("after get applicationNo...."+applicationNo);
					if(applicationNo!=null)
					{
						premiumSummary.setApplicationNo(applicationNo);
						if(pids.equalsIgnoreCase("11"))
						{
							premiumSummary.setProductId(pids);
							premiumSummary.setOpenCoverNo(openCoverNo);
						}
						HashMap fullDetails=new HashMap();
						fullDetails = premiumSummary.getPremiumDetails();
						request.setAttribute("fullDetails",fullDetails);
					}
				}
				
				if(!actualFrom.equalsIgnoreCase("Premium"))
					rd = getServletContext().getRequestDispatcher("/"+request.getParameter("actionPath"));
			/*}else
			{
				request.setAttribute("errorDetail",runner.getErrormsg("46"));
				rd = getServletContext().getRequestDispatcher("/ExistingCustomers_B2B.jsp");
			}*/// New Quote Button In Customer Screen 
		}
		
		if(rd!=null)
		{
			rd.forward(request, response);
		}
	}
}