package com.maan.opencover;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import proj.date.DateFunction;
import com.maan.opencover.LCDetails.LCInputsBean;
import com.maan.services.util.runner;

public class LCDetailsController extends HttpServlet
{
	private ServletContext context;
	private RequestDispatcher dispatcher;
	private String lcNumber="";
	private String lcAmount="";
	private String lcCurrencyId="";
	private String policyDay="";
	private String policyMonth=""; 
	private String policyYear="";
	private String expDay="";
	private String expMonth="";
	private String expYear="";

	String productId="";
	String loginId="";
	String companyId="";
	String sessionId="";
	String applicationNo="";
	String openCoverNo="";
	String bankNameLc="";
	String lcOtherBank="";
	String lcOtherStatus="";
	String lcBankAddress="";

	private StringBuffer error=new StringBuffer();
	
	HttpSession session=null;
	
	LCInputsBean premiumTransaction = new LCInputsBean();

		
	public LCDetailsController() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
	}

	public void doDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		session=request.getSession(true);
			
			if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("../login/error_messg.jsp");	
				return;
			}

		String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController) )
		{
			response.sendRedirect("../login/error_messg.jsp");	
			return;
		}
		com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
		
		String[][] sessionMaster_id= new String[0][0];
		String[][] tracking_id=new String[0][0];
		
		sessionMaster_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from session_master where session_id='"+(String)session.getAttribute("ses")+"'");
		
		tracking_id=com.maan.services.util.runner.multipleSelection("select session_id,login_id from tracking_master where session_id='"+(String)session.getAttribute("ses")+"'");
				
		/*try
		{
			if(sessionMaster_id.length>0)
			{
				if(!((String)session.getAttribute("user")).equalsIgnoreCase(sessionMaster_id[0][1]))
				{
					response.sendRedirect("../login/error_messg.jsp");
					return;
				}
			}
			if(tracking_id.length>0)
			{
				if(!((String)session.getAttribute("user")).equalsIgnoreCase(tracking_id[0][1]))
				{
					response.sendRedirect("../login/error_messg.jsp");
					return;
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR NOT AVAILABLE SESSION ID\n "+e.toString());
			e.printStackTrace();
		}*/

	
		String path=request.getRequestURI();
		path=path.substring(0,path.lastIndexOf("/")+1);
		String nextMissing="NOTHING";
		nextMissing=request.getParameter("next")==null?nextMissing:request.getParameter("next").equals("")?nextMissing:			request.getParameter("next");
		productId=request.getParameter("productId")==null?(String)session.getAttribute("productId")==null?"0":(String)session.getAttribute("productId"):request.getParameter("productId").equals("")?"0":					request.getParameter("productId");
		
		loginId=request.getParameter("loginId")==null?"":request.getParameter("loginId");
		
		companyId=request.getParameter("companyId")==null?"0":request.getParameter("companyId").equals("")?"0":				request.getParameter("companyId");
		
		sessionId=request.getParameter("sessionId")==null?"":request.getParameter("sessionId");

		String from=request.getParameter("from")==null?"":request.getParameter("from");
		String  lcNos=request.getParameter("lcNos")==null?"":request.getParameter("lcNos");
		HashMap brokerDetails1 = new HashMap();
		brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		if(brokerDetails1.size()>0)
		{
			cid = (String)brokerDetails1.get("Orgination");
		}
		String brokerBranch = (String)session.getAttribute("LoginBranchCode");
		if(from.equalsIgnoreCase("AdminLCMaster"))
		{
			String selBroker = request.getParameter("lcBroker")!=null?request.getParameter("lcBroker"):"";
			String valCheck = request.getParameter("valCheck")!=null?request.getParameter("valCheck"):"";
			if(selBroker.length()<=0&&valCheck.equalsIgnoreCase("valid"))
			{
				request.setAttribute("errorDetail","Please select any one Broker");
				RequestDispatcher rd = request.getRequestDispatcher("../LCCreation/LCCreationBrokerList.jsp");
				if(rd!=null)
					rd.forward(request,response);
			}
			else
			{
				if(!valCheck.equalsIgnoreCase("valid"))
					request.setAttribute("lcBroker",valCheck);
				RequestDispatcher rd = request.getRequestDispatcher("../LCCreation/LCOpenCovers.jsp");
				if(rd!=null)
					rd.forward(request,response);
				//if(true)return;
				
			}
		}
		else if(from.equalsIgnoreCase("AdminLCMasterDelete"))
		{
			String opencover = request.getParameter("opencover")!=null?request.getParameter("opencover"):"";
			String lcid = request.getParameter("lcNos")!=null?request.getParameter("lcNos"):"";
			premiumTransaction.deleteLCDetails(opencover,lcid);
			request.setAttribute("Delete","Yes");
			RequestDispatcher rd = request.getRequestDispatcher("../LCCreation/LcDetailsMsgAdmin.jsp");
			if(rd!=null)
				rd.forward(request,response);
		}
		else if(from.equalsIgnoreCase("AdminLCDCreation"))
		{
			String mode=request.getParameter("option")==null?"":request.getParameter("option");
			String bal = request.getParameter("availableBalance")==null?"0":request.getParameter("availableBalance");
			String usedamt = request.getParameter("usedamt")==null?"0":request.getParameter("usedamt");
			double availableBalance = Double.parseDouble(bal);
			openCoverNo = request.getParameter("openCoverNo")==null?(String)session.getAttribute("openCoverNo")==null?"0":(String)session.getAttribute("openCoverNo"):request.getParameter("openCoverNo");
			
			error=new StringBuffer();
			String lcStatus = request.getParameter("lcStatus");
			lcStatus = lcStatus==null?"Y":lcStatus;
			
			lcOtherStatus = request.getParameter("lcOtherStatus");
			lcOtherStatus = lcOtherStatus==null?"N":lcOtherStatus;
			lcOtherBank=request.getParameter("lcOtherBank")==null?lcOtherBank:
				request.getParameter("lcOtherBank");
			error=checkInputs(lcOtherBank,"OTHER BANK NAME","");
			lcBankAddress=request.getParameter("lcBankAddress")==null?lcBankAddress:
				request.getParameter("lcBankAddress");
			
			lcNumber=request.getParameter("lcNumber")==null?lcNumber:
			request.getParameter("lcNumber");
			error=checkInputs(lcNumber,"LC Number","");

			bankNameLc=request.getParameter("bankNameLc")==null?bankNameLc:
			request.getParameter("bankNameLc");
			error=checkInputs(bankNameLc,"BANK NAME","");
		
			String clno=""+checkLcNumber(bankNameLc,lcNumber);
			
			if(!mode.equalsIgnoreCase("edit")&&!lcNumber.equals("")&&clno.equals("1"))
				error=checkInputs(clno,"lcnumber","1");

			lcCurrencyId="0";
			lcCurrencyId=request.getParameter("lcCurrencyId")==null?lcCurrencyId:
			request.getParameter("lcCurrencyId");
			out.println("the Lc Currency Id is "+lcCurrencyId);
			error=checkInputs(lcCurrencyId,"LC Currency","");
			lcAmount=request.getParameter("lcAmount")==null?lcAmount:
			request.getParameter("lcAmount");
			error=checkInputs(lcAmount,"LC AMOUNT","");
			
			
			policyDay=request.getParameter("policyDay")==null?policyDay:
			request.getParameter("policyDay");
			error=checkInputs(policyDay,"LC DAY","");
		
			policyMonth=request.getParameter("policyMonth")==null?policyMonth:
			request.getParameter("policyMonth");
			error=checkInputs(policyMonth,"LC MONTH","");
		
			policyYear=request.getParameter("policyYear")==null?policyYear:
			request.getParameter("policyYear");
			error=checkInputs(policyYear,"LC YEAR","");
				
			String p=policyDay+"/"+policyMonth+"/"+policyYear;
			error=checkInputs((p),"LC DATE","");
			error=checkInputs((p),"LCDate","");
		
			expDay=request.getParameter("expDay")==null?expDay:
			request.getParameter("expDay");
			error=checkInputs(expDay,"Expiry DAY","");
		
			expMonth=request.getParameter("expMonth")==null?expMonth:
			request.getParameter("expMonth");
			error=checkInputs(expMonth,"Expiry MONTH","");
		
			expYear=request.getParameter("expYear")==null?expYear:
			request.getParameter("expYear");
			error=checkInputs(expYear,"Expiry YEAR","");
			String e=expDay+"/"+expMonth+"/"+expYear;
		
			error=checkInputs(e,"Expiry DATE","");
			error=checkInputs(e,"ExpiryDate","");
			
			String saleTerm=request.getParameter("saleTerm")==null?"0":request.getParameter("saleTerm");
			error=checkInputs(saleTerm,"saleTerm","");
			
			long diff=0;

			String pm="";
			String em="";
			String[][] mon={{"01","Jan"},{"02","Feb"},{"03","Mar"},{"04","Apr"},{"05","May"},{"06","Jun"},{"07","Jul"},{"08","Aug"},{"09","Sep"},{"10","Oct"},{"11","Nov"},{"12","Dec"}};
			try
			{
				DateFunction dbr=new DateFunction();
				//String str=today.getDay+"/"+today.getMonth+"/"+today.getYear;
				// diff1=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));

				pm=policyMonth;
				em=expMonth;
				for(int i=0;i<mon.length;i++)
				{
					if(mon[i][1].equalsIgnoreCase(pm))
					{
						pm=mon[i][0];
					}
					if(mon[i][1].equalsIgnoreCase(em))
					{
						em=mon[i][0];
					}
				}
				diff=dbr.getDayDifference(dbr.getCalendar(expDay+"/"+em+"/"+expYear),dbr.getCalendar(policyDay+"/"+pm+"/"+policyYear));
			}
			catch(Exception exception)
			{
				System.out.println("Exception in geeting difference"+exception.toString());
				exception.printStackTrace();
			}
			
			if(diff>0)
			{
				error=checkInputs("Diffrence","DATE DIFF","");
			}
			
			if(error.length()>0)
			{
				request.setAttribute("errorMessage",error);
				dispatcher=request.getRequestDispatcher("../LCCreation/lcDetailsEntryAdmin.jsp");
				dispatcher.forward(request, response);
				
			}
			else
			{
				com.maan.premium.DAO.PremiumInputsBean premiumInputs= new com.maan.premium.DAO.PremiumInputsBean();
				premiumInputs.setLoginBra(brokerBranch);
				double exRate = premiumTransaction.getExchangeRate(lcCurrencyId,cid);
				double lcAmtSale = 0;
				lcAmtSale = Double.parseDouble(lcAmount)+(Double.parseDouble(lcAmount) * ((premiumInputs.getSaleTermValue(saleTerm))/100));
				double actualLcAmout = lcAmtSale*exRate;
				
				error=checkInputs(""+actualLcAmout,"LCAMOUNTAVAIL",""+usedamt);
				
				

				premiumTransaction.setLcNumber(lcNumber);
				premiumTransaction.setLcAmount(lcAmount);
				premiumTransaction.setBankNameLc(bankNameLc);
				premiumTransaction.setLcCurrencyId(lcCurrencyId);
				premiumTransaction.setSaleTerm(saleTerm);
				premiumTransaction.setPolicyDay(policyDay);
				premiumTransaction.setPolicyMonth(policyMonth);
				premiumTransaction.setPolicyYear(policyYear);
	
				premiumTransaction.setExpDay(expDay);
				premiumTransaction.setExpMonth(expMonth);
				premiumTransaction.setExpYear(expYear);
	
				premiumTransaction.setOpenCoverNo(openCoverNo);
				
				premiumTransaction.setLcOtherStatus(lcOtherStatus);
				premiumTransaction.setLcOtherBank(lcOtherBank);
				premiumTransaction.setLcBankAddress(lcBankAddress);
			
				if(error.length()>0)
				{
					request.setAttribute("errorMessage",error);
					dispatcher=request.getRequestDispatcher("../LCCreation/lcDetailsEntryAdmin.jsp");
					dispatcher.forward(request, response);
					//dispatcher.include(request, response);
				}
				else
				{
					double actualBalance = actualLcAmout - Double.parseDouble(usedamt);
					//if(true)return;
					premiumTransaction.setApplicationNo(applicationNo);
					premiumTransaction.setSessionId(sessionId);
					premiumTransaction.setLoginCode(loginId);
					premiumTransaction.setCompanyId(companyId);
					premiumTransaction.setProductId(productId);
					premiumTransaction.setOpenCoverNo(openCoverNo);
					premiumTransaction.setStageId("2");
					premiumTransaction.setLcNumber(lcNumber);
					premiumTransaction.setLcAmount(lcAmount);
					premiumTransaction.setBankNameLc(bankNameLc);
					premiumTransaction.setLcCurrencyId(lcCurrencyId);
					premiumTransaction.setPolicyDay(policyDay);
					premiumTransaction.setPolicyMonth(policyMonth);
					premiumTransaction.setPolicyYear(policyYear);

					premiumTransaction.setExpDay(expDay);
					premiumTransaction.setExpMonth(expMonth);
					premiumTransaction.setExpYear(expYear);
					premiumTransaction.setLcStatus(lcStatus);
					premiumTransaction.setActualLcAmout(actualBalance);
					premiumTransaction.insertOrUpdateLCDetails(lcNos,cid,brokerBranch);
					
					premiumTransaction.setLcOtherStatus(lcOtherStatus);
					premiumTransaction.setLcOtherBank(lcOtherBank);
					premiumTransaction.setLcBankAddress(lcBankAddress);
					
		
					request.setAttribute("openCoverNo",openCoverNo);
					dispatcher=request.getRequestDispatcher("../LCCreation/LcDetailsMsgAdmin.jsp");
					dispatcher.forward(request, response);
				}
			}
			out.flush();
			out.close();
		}
		else if(from.equalsIgnoreCase("AdminLCD"))
		{
			String mode=request.getParameter("option")==null?"":request.getParameter("option");
			openCoverNo=request.getParameter("openCoverNo")==null?(String)session.getAttribute("openCoverNo")==null?"0":(String)session.getAttribute("openCoverNo"):request.getParameter("openCoverNo");
			//String  lcNos=request.getParameter("lcNos")==null?"":request.getParameter("lcNos");
			out.println("The OpenCoverNo is "+openCoverNo);
			out.println("The productId is "+productId);
	
			if(("0".equalsIgnoreCase(openCoverNo) && "11".equalsIgnoreCase(productId)) || "0".equalsIgnoreCase(productId))
			{
				response.sendRedirect("login/error_messg.jsp");
				return;
			}

			error=new StringBuffer();
		
			lcNumber=request.getParameter("lcNumber")==null?lcNumber:
			request.getParameter("lcNumber");
			error=checkInputs(lcNumber,"LC Number","");

			bankNameLc=request.getParameter("bankNameLc")==null?bankNameLc:
			request.getParameter("bankNameLc");
			error=checkInputs(bankNameLc,"BANK NAME","");
		
			String clno=""+checkLcNumber(bankNameLc,lcNumber);
			out.println("<br>clno>>>>>>>>>>>>>"+clno);
			out.println("<br>bankNameLc>>>>>>>>>>>>>"+bankNameLc);
			if(!mode.equalsIgnoreCase("edit")&&!lcNumber.equals("")&&clno.equals("1"))
				error=checkInputs(clno,"lcnumber","1");

			lcCurrencyId="0";
			lcCurrencyId=request.getParameter("lcCurrencyId")==null?lcCurrencyId:
			request.getParameter("lcCurrencyId");
			out.println("the Lc Currency Id is "+lcCurrencyId);
			error=checkInputs(lcCurrencyId,"LC Currency","");
			lcAmount=request.getParameter("lcAmount")==null?lcAmount:
			request.getParameter("lcAmount");
			error=checkInputs(lcAmount,"LC AMOUNT","");
			/*try
			{
				if(!lcAmount.equals(""))
				{
					double checkNumber=Double.parseDouble(lcAmount);	
					if(checkNumber<=0)
					{
						lcAmount="";
						error=checkInputs(lcAmount,"LC AMOUNT","");
					}
				}
			}
			catch(Exception e)
			{
				lcAmount="";
				error=checkInputs(lcAmount,"LC AMOUNT","");
			}*/
			out.println("totola lcAmount insured >>>>>>>>>>>>>>>>>>>>>>>>>>"+lcAmount);
			policyDay=request.getParameter("policyDay")==null?policyDay:
			request.getParameter("policyDay");
			error=checkInputs(policyDay,"LC DAY","");
		
			policyMonth=request.getParameter("policyMonth")==null?policyMonth:
			request.getParameter("policyMonth");
			error=checkInputs(policyMonth,"LC MONTH","");
		
			policyYear=request.getParameter("policyYear")==null?policyYear:
			request.getParameter("policyYear");
			error=checkInputs(policyYear,"LC YEAR","");
				
			String p=policyDay+"/"+policyMonth+"/"+policyYear;
			error=checkInputs((p),"LC DATE","");
			error=checkInputs((p),"LCDate","");
		
			expDay=request.getParameter("expDay")==null?expDay:
			request.getParameter("expDay");
			error=checkInputs(expDay,"Expiry DAY","");
		
			expMonth=request.getParameter("expMonth")==null?expMonth:
			request.getParameter("expMonth");
			error=checkInputs(expMonth,"Expiry MONTH","");
		
			expYear=request.getParameter("expYear")==null?expYear:
			request.getParameter("expYear");
			error=checkInputs(expYear,"Expiry YEAR","");
			String e=expDay+"/"+expMonth+"/"+expYear;
		
			error=checkInputs(e,"Expiry DATE","");
			error=checkInputs(e,"ExpiryDate","");


			long diff=0;

			String pm="";
			String em="";
			String[][] mon={{"01","Jan"},{"02","Feb"},{"03","Mar"},{"04","Apr"},{"05","May"},{"06","Jun"},{"07","Jul"},{"08","Aug"},{"09","Sep"},{"10","Oct"},{"11","Nov"},{"12","Dec"}};
			try
			{
				DateFunction dbr=new DateFunction();
				//String str=today.getDay+"/"+today.getMonth+"/"+today.getYear;
				// diff1=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));

				pm=policyMonth;
				em=expMonth;
				for(int i=0;i<mon.length;i++)
				{
					if(mon[i][1].equalsIgnoreCase(pm))
					{
						pm=mon[i][0];
					}
					if(mon[i][1].equalsIgnoreCase(em))
					{
						em=mon[i][0];
					}
				}
				diff=dbr.getDayDifference(dbr.getCalendar(expDay+"/"+em+"/"+expYear),dbr.getCalendar(policyDay+"/"+pm+"/"+policyYear));
			}
			catch(Exception exception)
			{
				System.out.println("Exception in geeting difference"+exception.toString());
				exception.printStackTrace();
			}
			
			if(diff>0)
			{
				error=checkInputs("Diffrence","DATE DIFF","");
			}

			if(error.length()>0)
			{
				request.setAttribute("errorMessage",error);
				//context.getRequestDispatcher("/Quotation.jsp").forward(request, response);
				//piB.setModeOfTransport("0");

				dispatcher=request.getRequestDispatcher("/premium/lcDetailsEntryAdmin.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				premiumTransaction.setLcNumber(lcNumber);
				premiumTransaction.setLcAmount(lcAmount);
				premiumTransaction.setBankNameLc(bankNameLc);
				premiumTransaction.setLcCurrencyId(lcCurrencyId);
				premiumTransaction.setPolicyDay(policyDay);
				premiumTransaction.setPolicyMonth(policyMonth);
				premiumTransaction.setPolicyYear(policyYear);
	
				premiumTransaction.setExpDay(expDay);
				premiumTransaction.setExpMonth(expMonth);
				premiumTransaction.setExpYear(expYear);
	
				premiumTransaction.setOpenCoverNo(openCoverNo);
			
				if(error.length()>0)
				{
					request.setAttribute("errorMessage",error);
					dispatcher=request.getRequestDispatcher("/premium/lcDetailsEntryAdmin.jsp");
					dispatcher.forward(request, response);
					//dispatcher.include(request, response);
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
					premiumTransaction.setLcNumber(lcNumber);
					premiumTransaction.setLcAmount(lcAmount);
					premiumTransaction.setBankNameLc(bankNameLc);
					premiumTransaction.setLcCurrencyId(lcCurrencyId);
					premiumTransaction.setPolicyDay(policyDay);
					premiumTransaction.setPolicyMonth(policyMonth);
					premiumTransaction.setPolicyYear(policyYear);

					premiumTransaction.setExpDay(expDay);
					premiumTransaction.setExpMonth(expMonth);
					premiumTransaction.setExpYear(expYear);
					premiumTransaction.insertOrUpdateLCDetails(lcNos,cid,brokerBranch);
					out.println("asdasdas>>>>>>>>>>>>>>>"+from);
					out.println("the fff sddsd APPLICATION NO"+applicationNo);
		
					request.setAttribute("openCoverNo",openCoverNo);
					dispatcher=request.getRequestDispatcher("/premium/LcDetailsMsgAdmin.jsp");
					dispatcher.forward(request, response);
				}
			}
			out.flush();
			out.close();
		}
		else
		{
			openCoverNo=request.getParameter("openCoverNo")==null?(String)session.getAttribute("openCoverNo")==null?"0":(String)session.getAttribute("openCoverNo"):request.getParameter("openCoverNo");
			out.println("The OpenCoverNo is "+openCoverNo);
			out.println("The productId is "+productId);
			if(("0".equalsIgnoreCase(openCoverNo) && "11".equalsIgnoreCase(productId))|| "0".equalsIgnoreCase(productId))
			{
				response.sendRedirect("login/error_messg.jsp");
				return;
			}
			error=new StringBuffer();
			lcNumber=request.getParameter("lcNumber")==null?lcNumber:
			request.getParameter("lcNumber");
			error=checkInputs(lcNumber,"LC Number","");
			//Open Cover POlicy
			bankNameLc=request.getParameter("bankNameLc")==null?bankNameLc:
			request.getParameter("bankNameLc");
			error=checkInputs(bankNameLc,"BANK NAME","");
			lcAmount=request.getParameter("lcAmount")==null?lcAmount:
			request.getParameter("lcAmount");
			error=checkInputs(lcAmount,"LC AMOUNT","");
			
			out.println("totola lcAmount insured >>>>>>>>>>>>>>>>>>>>>>>>>>"+lcAmount);
			
			lcCurrencyId=request.getParameter("lcCurrencyId")==null?lcCurrencyId:
			request.getParameter("lcCurrencyId");
			out.println("the Lc Currency Id is "+lcCurrencyId);
			error=checkInputs(lcCurrencyId,"LC Currency","");
			policyDay=request.getParameter("policyDay")==null?policyDay:
			request.getParameter("policyDay");
			error=checkInputs(policyDay,"LC DAY","");
			policyMonth=request.getParameter("policyMonth")==null?policyMonth:
			request.getParameter("policyMonth");
			error=checkInputs(policyMonth,"LC MONTH","");
			policyYear=request.getParameter("policyYear")==null?policyYear:
			request.getParameter("policyYear");
			error=checkInputs(policyYear,"LC YEAR","");
			error=checkInputs((policyDay+"/"+policyMonth+"/"+policyYear),"LC DATE","");
			if(error.length()>0)
			{
				request.setAttribute("errorMessage",error);
				//context.getRequestDispatcher("/Quotation.jsp").forward(request, response);
				//piB.setModeOfTransport("0");
		
			
				dispatcher=request.getRequestDispatcher("/premium/lcDetailsEntry.jsp");
				dispatcher.forward(request, response);
			}
			else
			{
				premiumTransaction.setLcNumber(lcNumber);
				premiumTransaction.setLcAmount(lcAmount);
				premiumTransaction.setBankNameLc(bankNameLc);
				premiumTransaction.setLcCurrencyId(lcCurrencyId);
				premiumTransaction.setPolicyDay(policyDay);
				premiumTransaction.setPolicyMonth(policyMonth);
				premiumTransaction.setPolicyYear(policyYear);
				premiumTransaction.setOpenCoverNo(openCoverNo);
				if(error.length()>0)
				{
					request.setAttribute("errorMessage",error);
					dispatcher=request.getRequestDispatcher("/premium/lcDetailsEntry.jsp");
					dispatcher.forward(request, response);
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
					premiumTransaction.setLcNumber(lcNumber);
					premiumTransaction.setLcAmount(lcAmount);
					premiumTransaction.setBankNameLc(bankNameLc);
					premiumTransaction.setLcCurrencyId(lcCurrencyId);
					premiumTransaction.setPolicyDay(policyDay);
					premiumTransaction.setPolicyMonth(policyMonth);
					premiumTransaction.setPolicyYear(policyYear);
					premiumTransaction.insertOrUpdateLCDetails(lcNos,cid,brokerBranch);
					out.println("the fff sddsd APPLICATION NO"+applicationNo);
					dispatcher=request.getRequestDispatcher("/premium/LcDetailsMsg.jsp");
					dispatcher.forward(request, response);
				}
			}
			out.flush();
			out.close();
		}
	}

	public StringBuffer checkInputs(String inputName,String inputDescription,String inputErrorId)
	{
		
		if(inputDescription.equalsIgnoreCase("BANK NAME"))
		{
			if(("0".equalsIgnoreCase(inputName)||"select".equalsIgnoreCase(inputName)) &&  lcOtherStatus.equalsIgnoreCase("N"))
			{
				error.append("<br><font color=red size=2><b>* "+getErrormsg("159",inputDescription)+"</b></font><br>");
			}
		}
		if(inputDescription.equalsIgnoreCase("OTHER BANK NAME"))
		{
			if(lcOtherBank.length()<=0 &&  lcOtherStatus.equalsIgnoreCase("Y"))
			{
				error.append("<br><font color=red size=2><b>* "+getErrormsg("159",inputDescription)+"</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("LC Number"))
		{
			
			if("".equalsIgnoreCase(inputName))
			{
				error.append("<br><font color=red size=2><b>* "+getErrormsg("130",inputDescription)+"</b></font><br>");
			}
		}
		else if("lcnumber".equalsIgnoreCase(inputDescription)&&inputName.equals("1"))
		{
			error.append("<br><font color=red size=2><b>* LC Number already existed</b></font><br>");
			
		}
		else if(inputDescription.equalsIgnoreCase("LCDate"))
		{
			if("Invalid".equalsIgnoreCase(checkDate(inputName)))
			{
				error.append("<br><font color=red size=2><b>* "+getErrormsg("62",inputDescription)+"</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("ExpiryDate"))
		{
			if("Invalid".equalsIgnoreCase(checkDate(inputName)))
			{
				error.append("<br><font color=red size=2><b>* "+getErrormsg("63",inputDescription)+"</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("DATE DIFF"))
		{
			error.append("<br><font color=red size=2><b>* LC Date should be less than expiry date </b></font><br>");
		}
		else if(inputDescription.equalsIgnoreCase("LC Currency"))
		{
			if("0".equalsIgnoreCase(inputName)||"select".equalsIgnoreCase(inputName))
			{
				error.append("<br><font color=red size=2><b>* Please select Curreny</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("LC AMOUNT"))
		{
			if(inputName.length() > 0)
			{
				if(!validAmount(inputName))
				{
					error.append("<br><font color=red size=2><b>* Please enter valid LC Amount</b></font><br>");
				}
				else if(inputName.equals("0"))
				{
					error.append("<br><font color=red size=2><b>* Please enter valid LC Amount</b></font><br>");
				}
			}
			else
			{
				error.append("<br><font color=red size=2><b>* Please enter LC Amount</b></font><br>");
			}
		}
		else if(inputDescription.equalsIgnoreCase("LCAMOUNTAVAIL"))
		{
			
			if(Double.parseDouble(inputName)<Double.parseDouble(inputErrorId))
				error.append("<br><font color=red size=2><b>* LC amount should be greater than used amount, please refer used amount</b></font><br>");
			else if(IsNegativeValidationFormat(inputErrorId))
				error.append("<br><font color=red size=2><b>* LC amount should be greater than used amount, please refer used amount</b></font><br>");
		}
		else if(inputDescription.equalsIgnoreCase("saleTerm"))
		{
			if(inputName.equalsIgnoreCase("0"))
				error.append("<br><font color=red size=2><b>* Please select Sale Term</b></font><br>");
		}
		return error;
	}

	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		
		java.util.Date date = df.parse(strDate, pos);
		if ((date == null) || (pos.getErrorIndex() != -1))
		{
			if(date == null) 
			{
				return "Invalid";
			}
			if(pos.getErrorIndex() != -1)
			{
				return "Invalid";
			}	
			return "Invalid";
		}
		return returnVal;
	}

	public String getErrormsg(String errorCode,String description)
	{
		String result = "";
		String sql = "";
		String args[] = new String[1];
		runner run = new runner();
		try
		{
			args[0] = errorCode;
			sql = "select error_desc from error_master where error_id=?";
			result = run.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Error in ERROR INFO UNDER COMMON ERROR FOLDER ..."+e.toString());
			result="Please Provide Valid Input for "+description;
		}
		return result;
	}

	public int checkLcNumber(String bankID,String lc_number)
	{
		int  result=0;
		String args[] = new String[2];
		String sql = "";
		String res = "";
		try
		{
			args[0] = bankID;
			args[1] = lc_number;

			sql="select count(*) from open_cover_lc_master where bank_id=? and lc_number=? and status='Y'";
			res = runner.singleSelection(sql,args);
			if(res.length()>0 && !res.equalsIgnoreCase("null"))
			{
				result = Integer.parseInt(res);
			}
			else
			{
				result = 0;
				System.out.println("RoyalTest rows are.."+result);
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in ERROR INFO UNDER COMMON ERROR FOLDER ..."+e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	public void print(String methodName,String information,String type)
	{
		System.out.println(methodName+"<--OPEN COVER-->"+type+"<--OPEN COVER-->"+information);
	}

	public boolean IsDigitValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		for (int i = 0; i < Value.length(); ++i) 
		{
			char c = Value.charAt(i);
			if (!Character.isDigit(c))
				b = false;
		}
		return b;
	}

	public boolean IsNegativeValidationFormat(String Value)
	{
		boolean b = true;
		Value = Value.trim();
		if(!Value.substring(0,1).equalsIgnoreCase("-"))
			b	=	false;
		return b;
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
			System.out.println("Valid Amount in OfficeShiledBean.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in OSB  "+ flag);
		return flag;
	}

} // Class
