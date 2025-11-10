package com.maan.common.CopyQuote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;
import com.maan.services.util.ValidationFormat;
import com.maan.product.ProductSelection;

public class MasterController extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(MasterController.class);
	private static final long serialVersionUID = 1L;
	final static transient private String STR239    = "239";
	final static transient private String STR284    = "284";
	final static transient private String STR285    = "285";
	final static transient private String STR241    = "241";
	final static transient private String STR237    = "237";
	final static transient private String STR296    = "296";
	final static transient private String POLQSEL   = "PolicyQuoteSelect";
	final static transient private String POLQTEXT  = "PolicyQuoteText";
	final static transient private String CHKQNO    = "CheckQuoteNo";
	final static transient private String PROID     = "product_id";
	final static transient private String RADIOQNO  = "RadioQuoteNo";
	final static transient private String ERRORMSG  = "ErrorMsg";
	final static transient private String NEWCUSID  = "newCusId";
	final static transient private String OLDID     = "oldId";
	final static transient private String TRAVELQNO = "TravelQuoteNumber";
	final static transient private String QUOTENO   = "quoteno";
	final static transient private String PRODUCTID = "productid";
	final static transient private String NEWID     = "newId";


	public void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException
	{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void doGet(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException
	{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void processResult(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException, BaseException
	{

		try{
			response.setContentType("text/html");
			HttpSession session = request.getSession(true);

			RequestDispatcher dispatcher = null;

			//SESSION CHECK
			String pathh = request.getContextPath();
			String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			DBConnectionStatus.statusStatic=usrModeSC;
			//END OF SESSION CHECK

			//For Session Check
			if(session.getAttribute("ses") == null)
			{
				response.sendRedirect(pathh+"/login/error_messg.jsp");
			}

			String path=request.getRequestURI();

			String loginid = (String)session.getAttribute("user");
			String branch = (String)session.getAttribute("LoginBranchCode");

			ValidationFormat	validationformat = new ValidationFormat();
			DataSelect	dataSelect				 = new DataSelect();

			//String quoteid				= request.getParameter("quoteid")!=null?request.getParameter("quoteid"):"";
			//String cusid				= (String)session.getAttribute("customer_id");
	        String productid			= (String)session.getAttribute(PROID);
			String rsaissuer = (String)session.getAttribute("RSAISSUER");
			if(path.indexOf('.') == -1){
				path = path.substring(path.lastIndexOf('/')+1,path.length());
			}
			else{
				path=path.substring(path.lastIndexOf('/')+1,path.lastIndexOf('.'));
		        logger.info("path--->"+path);
			}

			String allTraProdIds="";
			String travelProductId="";
			if(session.getAttribute("AllTravelProductIds")!=null)
			{
				allTraProdIds = (String)session.getAttribute("AllTravelProductIds");
				productid = allTraProdIds;
			}
			if(session.getAttribute("TravelProductId")!=null)
			{
				travelProductId = (String)session.getAttribute("TravelProductId");
			}
			/////////////////getting product Id Start
			String pid = isNull(request.getParameter("selectProd"));
			if(pid == null){
				pid = (String)session.getAttribute(PROID);
			}
			////////////////getting product id ending

		if("SearchQuote".equalsIgnoreCase(path))
		{
				StringBuffer	ErrorMsg	=	new StringBuffer(1000);
				String	PolicyQuoteSelect	=	isNull(request.getParameter(POLQSEL));
				String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
				PolicyQuoteText = PolicyQuoteText.trim();

				if("1".equalsIgnoreCase(PolicyQuoteSelect))
				{
					ErrorMsg.append(runner.getErrormsg("283"));
					ErrorMsg.append(',');
				}
				else if("2".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText)){
						ErrorMsg.append(runner.getErrormsg(STR237));
						ErrorMsg.append(',');
					}
					else{
						String CheckQuoteNo		=	dataSelect.getPolicyExistOrNot(PolicyQuoteText,productid,loginid);
						if("".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(STR239));
							ErrorMsg.append(',');
						}
						else if("299".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
							ErrorMsg.append(',');
						}
						else
						{
							request.setAttribute(CHKQNO,CheckQuoteNo);
						}
					}
				}
				else if("4".equalsIgnoreCase(PolicyQuoteSelect))
				{
									String CheckQuoteNo1  = "";
									if("".equalsIgnoreCase(PolicyQuoteText))
									{
										ErrorMsg.append(runner.getErrormsg(STR284));
										ErrorMsg.append(',');
									}
								else
								{

									CheckQuoteNo1		=	dataSelect.getCustomerQuoteName(PolicyQuoteText.trim(),productid,loginid);
									if(CheckQuoteNo1.equalsIgnoreCase(""))
									{
										ErrorMsg.append(runner.getErrormsg(STR285));
										ErrorMsg.append(',');
									}
									else
									{
											request.setAttribute(CHKQNO,CheckQuoteNo1);
									}
								}
					}

				else if("3".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText)){
						ErrorMsg.append(runner.getErrormsg(STR241));
						ErrorMsg.append(',');
					}
					else{
						if(validationformat.isDigitValidationFormat(PolicyQuoteText)){

							String CheckQuoteNo		=	dataSelect.getQuoteExistOrNot(PolicyQuoteText,productid,loginid);

							if("".equalsIgnoreCase(CheckQuoteNo))
							{
								ErrorMsg.append(runner.getErrormsg("238"));
								ErrorMsg.append(',');
							}
							else if("294".equalsIgnoreCase(CheckQuoteNo) || "295".equalsIgnoreCase(CheckQuoteNo) || "297".equalsIgnoreCase(CheckQuoteNo) || "298".equalsIgnoreCase(CheckQuoteNo))
							{
								ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
								ErrorMsg.append(',');
							}
							else
							{
								request.setAttribute(CHKQNO,CheckQuoteNo);
							}
						}
						else
						{
							ErrorMsg.append(runner.getErrormsg(STR296));
							ErrorMsg.append(',');
						}
					}
				}
				if(ErrorMsg.length()<=0){
					ErrorMsg.append(" Please see the below results");
				}
				request.setAttribute(ERRORMSG,ErrorMsg);

				if(travelProductId.length()>0)
				{
					dispatcher=request.getRequestDispatcher("/CopyQuote/TravelCopyQuote.jsp");
				}
				else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/CopyQuote.jsp");
				}
				dispatcher.forward(request, response);
		}
		if("CopyQuote".equalsIgnoreCase(path))
		{
			StringBuffer	ErrorMsg	=	new StringBuffer(1000);
			String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
			String RadioQuoteNo	        =   isNull(request.getParameter(RADIOQNO));
			if("".equalsIgnoreCase(RadioQuoteNo))
			{
			  ErrorMsg.append(runner.getErrormsg("286"));
			  ErrorMsg.append(',');
			  request.setAttribute(CHKQNO,PolicyQuoteText);
			  request.setAttribute(ERRORMSG,ErrorMsg);

				if(travelProductId.length()>0){
					dispatcher = request.getRequestDispatcher("/CopyQuote/TravelCopyQuote.jsp");
				}
				else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/CopyQuote.jsp");
				}
			  dispatcher.forward(request, response);
			}
			else{
				request.setAttribute("ReqFrom","CopyQuote");

				if(travelProductId.length()>0){
					//dispatcher=request.getRequestDispatcher("/TravelInsurance/ExistingCustomers_B2B.jsp");
					dispatcher = request.getRequestDispatcher("/TravelDisplayController?DisplayValue=customer");
				}
				else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/copyQuoteCustomers_B2B1.jsp");
				}
				dispatcher.forward(request, response);
			}
		}
		if("CopyQuote1".equalsIgnoreCase(path))
		{
			StringBuffer	ErrorMsg	=	new StringBuffer();
			String RadioQuoteNo	= isNull(request.getParameter(RADIOQNO));

			logger.info("radiidid"+RadioQuoteNo);
			String newId = request.getParameter(NEWCUSID)==null?"":request.getParameter(NEWCUSID);

			if("".equalsIgnoreCase(newId) || "null".equalsIgnoreCase(newId))
			{
			   ErrorMsg.append(runner.getErrormsg("287"));
			   request.setAttribute(ERRORMSG,ErrorMsg.toString());

			   if(travelProductId.length()>0){
					dispatcher=request.getRequestDispatcher("/TravelDisplayController?DisplayValue=customer");
			   }
			   else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/copyQuoteCustomers_B2B1.jsp");
			   }
			  dispatcher.forward(request, response);
			}
			else
			{
				String Return = "";
				if(travelProductId.length()>0){
					Return = dataSelect.copyQuote(RadioQuoteNo, travelProductId,isNull(request.getParameter(OLDID)),request.getParameter(NEWCUSID),"royal",branch);
				}
				else{
					Return = dataSelect.copyQuote(RadioQuoteNo, productid,isNull(request.getParameter(OLDID)),request.getParameter(NEWCUSID),"royalHome",branch);
				}
				session.setAttribute(TRAVELQNO,Return);
				session.setAttribute(QUOTENO,RadioQuoteNo);
				session.setAttribute(PRODUCTID,productid);
				session.setAttribute(OLDID,isNull(request.getParameter(OLDID)));
				session.setAttribute(NEWID,isNull(request.getParameter(NEWCUSID)));
				if(travelProductId.length()>0){
					response.sendRedirect(request.getContextPath()+"/CopyQuote/TravelShowQuote.jsp");
				}
				else{
					response.sendRedirect(request.getContextPath()+"/CopyQuote/showQuote.jsp");
				}
			}
		}
		if("PolicyReIssue".equalsIgnoreCase(path))
		{
				String CurQuoteNo = isNull(request.getParameter("canQno"));
				String CurPolicyNo = isNull(request.getParameter("canPno"));
				String reason = isNull(request.getParameter("reason"));
				pid = (String)session.getAttribute(PROID);
				loginid = (String)session.getAttribute("user");
				boolean checkStatus = true;
				if(checkStatus)
				{
					String Return = dataSelect.copyQuote(CurQuoteNo, pid,isNull(request.getParameter(OLDID)),request.getParameter(NEWCUSID),CurPolicyNo,branch);
					session.setAttribute(TRAVELQNO,Return);
					session.setAttribute(QUOTENO,CurQuoteNo);
					session.setAttribute(PRODUCTID,productid);
					session.setAttribute(OLDID,isNull(request.getParameter(OLDID)));
					session.setAttribute(NEWID,isNull(request.getParameter(NEWCUSID)));
					dataSelect.changePolicyStatus(CurPolicyNo,"I",reason,loginid,Return);
					if(travelProductId.length()>0)
					{
						dispatcher=request.getRequestDispatcher("CopyQuote/TravelShowQuote.jsp?ReIssue=Yes&CurPolicyNo="+CurPolicyNo);
						dispatcher.forward(request, response);
					}
					else{
						response.sendRedirect("../CopyQuote/showQuote.jsp");
					}
				}
				else{
					if(travelProductId.length()>0){
						RequestDispatcher dispatcher1=request.getRequestDispatcher("CopyQuote/TravelShowQuote.jsp?ReIssue=No&CurPolicyNo="+CurPolicyNo);
						dispatcher1.forward(request, response);
					}
				}
		}
		if("PolicyCancel".equalsIgnoreCase(path))
		{
				String CurQuoteNo = isNull(request.getParameter("canQno"));
				String CurPolicyNo = isNull(request.getParameter("canPno"));
				pid = (String)session.getAttribute(PROID);
				String reason = isNull(request.getParameter("reason"));
				pid = (String)session.getAttribute(PROID);
				loginid = (String)session.getAttribute("user");


				session.setAttribute(TRAVELQNO,CurQuoteNo);
				session.setAttribute(QUOTENO,CurQuoteNo);
				session.setAttribute(PRODUCTID,productid);
				session.setAttribute(OLDID,isNull(request.getParameter(OLDID)));
				session.setAttribute(NEWID,isNull(request.getParameter(NEWCUSID)));
				boolean checkStatus = dataSelect.getReIssueStatus(CurQuoteNo);
				if(checkStatus)
				{
					dataSelect.changePolicyStatus(CurPolicyNo,"C",reason,loginid,"None");
					if(travelProductId.length()>0)
					{
						RequestDispatcher dispatcher2=request.getRequestDispatcher("CopyQuote/TravelShowQuote.jsp?ReIssue=Cancel&CurPolicyNo="+CurPolicyNo);
						dispatcher2.forward(request, response);
					}
					else{
						response.sendRedirect("../CopyQuote/showQuote.jsp");
					}
				}else{
					if(travelProductId.length()>0)
					{
						RequestDispatcher dispatcher3=request.getRequestDispatcher("CopyQuote/TravelShowQuote.jsp?ReIssue=cancelNo&CurPolicyNo="+CurPolicyNo);
						dispatcher3.forward(request, response);
						
					}
				}
		}
		//Marine Copy Quotes//////
		if("MarineSearchQuote".equalsIgnoreCase(path))
		{
				StringBuffer	ErrorMsg	=	new StringBuffer();
				String	PolicyQuoteSelect	=	isNull(request.getParameter(POLQSEL));
				String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
				PolicyQuoteText = PolicyQuoteText.trim();

				if("1".equalsIgnoreCase(PolicyQuoteSelect))
				{
					ErrorMsg.append(runner.getErrormsg("283"));
					ErrorMsg.append(',');
				}
				else if("2".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{
						ErrorMsg.append(runner.getErrormsg(STR237));
						ErrorMsg.append(',');
					}
					else
					{
						String CheckQuoteNo		=	dataSelect.getMarinePolicyExistOrNot(PolicyQuoteText,productid,loginid,rsaissuer);
						System.out.println("Test checkQuoteNo"+CheckQuoteNo);
						if("".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(STR239));
							ErrorMsg.append(',');
						}
						else if("299".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
							ErrorMsg.append(',');
						}
						else
						{
							request.setAttribute(CHKQNO,CheckQuoteNo);
						}
					}
				}
				else if("4".equalsIgnoreCase(PolicyQuoteSelect))
				{
						String CheckQuoteNo1  = "";
						if("".equalsIgnoreCase(PolicyQuoteText))
						{
							ErrorMsg.append(runner.getErrormsg(STR284));
							ErrorMsg.append(',');
						}
						else
						{

							CheckQuoteNo1		=	dataSelect.getMarineCustomerQuoteName(PolicyQuoteText.trim(),productid,loginid,rsaissuer);
							if("".equalsIgnoreCase(CheckQuoteNo1))
							{
								ErrorMsg.append(runner.getErrormsg(STR285));
								ErrorMsg.append(',');
							}
							else
							{
								request.setAttribute(CHKQNO,CheckQuoteNo1);
							}
						}
				}
				else if("3".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{
						ErrorMsg.append(runner.getErrormsg(STR241));
						ErrorMsg.append(',');
					}
					else
					{
						if(validationformat.isDigitValidationFormat(PolicyQuoteText))
						{
							String CheckQuoteNo		=	dataSelect.getMarineQuoteExistOrNot(PolicyQuoteText,productid,loginid,rsaissuer);
							if("".equalsIgnoreCase(CheckQuoteNo))
							{
								ErrorMsg.append(runner.getErrormsg("238"));
								ErrorMsg.append(',');
							}
							else if("294".equalsIgnoreCase(CheckQuoteNo)||"295".equalsIgnoreCase(CheckQuoteNo)||"297".equalsIgnoreCase(CheckQuoteNo)||"298".equalsIgnoreCase(CheckQuoteNo))
							{
								ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
								ErrorMsg.append(',');
							}
							else
							{
								request.setAttribute(CHKQNO,CheckQuoteNo);
							}
						}
						else
						{
							ErrorMsg.append(runner.getErrormsg(STR296));
							ErrorMsg.append(',');
						}
					}
				}
				request.setAttribute(ERRORMSG,ErrorMsg);
				dispatcher=request.getRequestDispatcher("/CopyQuote/MarineCopyQuote.jsp");
				dispatcher.forward(request, response);
		}
		if("MarineCopyQuote".equalsIgnoreCase(path))
		{
			StringBuffer	ErrorMsg	=	new StringBuffer(1000);
			//String PolicyQuoteSelect	=	request.getParameter(POLQSEL)==null?"":request.getParameter(POLQSEL);
			String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
			String RadioQuoteNo	=
			request.getParameter(RADIOQNO)==null?"":request.getParameter(RADIOQNO);
			if("".equalsIgnoreCase(RadioQuoteNo))
			{
			  ErrorMsg.append(runner.getErrormsg("286"));
			  ErrorMsg.append(',');
			  request.setAttribute(CHKQNO,PolicyQuoteText);
			  request.setAttribute(ERRORMSG,ErrorMsg);
			  dispatcher=request.getRequestDispatcher("/CopyQuote/MarineCopyQuote.jsp");
			  dispatcher.forward(request, response);
			}
			else
			{
				logger.info("<br>Quote NO----"+RadioQuoteNo);
				logger.info("<br> Product Id---"+productid);
				logger.info("<br> Customer Id---"+request.getParameter("customer_id"));
				request.setAttribute("ReqFrom","CopyQuote");
				//dispatcher=request.getRequestDispatcher("/ExistingCustomers_B2B.jsp");
				//dispatcher = request.getRequestDispatcher("/MarineDisplayController?DisplayValue=customer");
				//dispatcher.forward(request, response);
				String brokerBra = (String)session.getAttribute("LoginBranchCode");
				String quoteNo = dataSelect.marineCopyQuote(RadioQuoteNo, productid,isNull(request.getParameter(OLDID)),request.getParameter(NEWCUSID),brokerBra,rsaissuer);
				session.setAttribute(TRAVELQNO,quoteNo);
				request.setAttribute("Marine","Marine");
				session.setAttribute(QUOTENO,RadioQuoteNo);
				session.setAttribute(PRODUCTID,productid);
				if(session.getAttribute("ReqFrom") != null ){
					session.removeAttribute("ReqFrom");
				}
				response.sendRedirect(request.getContextPath()+"/CopyQuote/MarineShowQuote.jsp");
				return;
			}
		}
		if("MarineCopyQuotes".equalsIgnoreCase(path))
		{
			StringBuffer	ErrorMsg	=	new StringBuffer(1000);
			//String PolicyQuoteSelect	=	request.getParameter(POLQSEL)==null?"":request.getParameter(POLQSEL);
			//String PolicyQuoteText		=	request.getParameter(POLQTEXT)==null?"":request.getParameter(POLQTEXT);
			String RadioQuoteNo	=isNull(request.getParameter(RADIOQNO));
			String paths=request.getContextPath();

			String newId = isNull(request.getParameter(NEWCUSID));

			if("".equalsIgnoreCase(newId) || "null".equalsIgnoreCase(newId))
			{
			   ErrorMsg.append(runner.getErrormsg("287"));
			   request.setAttribute(ERRORMSG,ErrorMsg);
			  // dispatcher=request.getRequestDispatcher("/ExistingCustomers_B2B.jsp");
			   dispatcher = request.getRequestDispatcher("/MarineDisplayController?DisplayValue=customer");
			   dispatcher.forward(request, response);
			}
			else
			{
				String brokerBra = (String)session.getAttribute("LoginBranchCode");
				String Return = dataSelect.marineCopyQuote(RadioQuoteNo, productid,isNull(request.getParameter(OLDID)),request.getParameter(NEWCUSID),brokerBra,rsaissuer);
				session.setAttribute(TRAVELQNO,Return);
				request.setAttribute("Marine","Marine");
				session.setAttribute(QUOTENO,RadioQuoteNo);
				session.setAttribute(PRODUCTID,productid);
				session.setAttribute(OLDID,isNull(request.getParameter(OLDID)));
				session.setAttribute(NEWID,isNull(request.getParameter(NEWCUSID)));
				if(session.getAttribute("ReqFrom") != null ){
					session.removeAttribute("ReqFrom");
				}
				response.sendRedirect(paths+"/CopyQuote/MarineShowQuote.jsp");
				return;
			}
		}
		//Marine Copy Quotes//////

		/** B TO C Search Quote **/
		/*if("BtoCSearchQuote".equalsIgnoreCase(path))
		{
				StringBuffer	ErrorMsg	=	new StringBuffer(1000);
				String	PolicyQuoteSelect	=	isNull(request.getParameter(POLQSEL));
				String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
				PolicyQuoteText = PolicyQuoteText.trim();

				if("1".equalsIgnoreCase(PolicyQuoteSelect))
				{
					ErrorMsg.append(runner.getErrormsg("321"));
					ErrorMsg.append(',');
				}
				else if("2".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{
						ErrorMsg.append(runner.getErrormsg(STR237));
						ErrorMsg.append(',');
					}
					else
					{
						String CheckQuoteNo		=	dataSelect.getPolicyExistOrNot(PolicyQuoteText,productid,loginid);
						if("".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(STR239));
							ErrorMsg.append(',');
						}
						else if("299".equalsIgnoreCase(CheckQuoteNo))
						{
							ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
							ErrorMsg.append(',');
						}
						else
						{
							request.setAttribute(CHKQNO,CheckQuoteNo);
						}
					}
				}
				else if("4".equalsIgnoreCase(PolicyQuoteSelect))
				{
									String CheckQuoteNo1  = "";
									if("".equalsIgnoreCase(PolicyQuoteText))
									{
										ErrorMsg.append(runner.getErrormsg(STR284));
										ErrorMsg.append(',');
									}
								else
								{

									CheckQuoteNo1		=	dataSelect.getCustomerQuoteName(PolicyQuoteText.trim(),productid,loginid);
									if("".equalsIgnoreCase(CheckQuoteNo1))
									{
										ErrorMsg.append(runner.getErrormsg(STR285));
										ErrorMsg.append(',');
									}
									else
									{
											request.setAttribute(CHKQNO,CheckQuoteNo1);
									}
								}
					}

				else if("3".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{
						ErrorMsg.append(runner.getErrormsg(STR241));
						ErrorMsg.append(',');												
					}else{
						if(validationformat.isDigitValidationFormat(PolicyQuoteText) || validationformat.eMailValidation(emailId))
						{

							String CheckQuoteNo		=	dataSelect.getQuoteExistOrNotB2C(PolicyQuoteText,productid,loginid);
							if("".equalsIgnoreCase(CheckQuoteNo)){
								ErrorMsg.append(runner.getErrormsg("238"));
								ErrorMsg.append(',');
							}
							else if("294".equalsIgnoreCase(CheckQuoteNo) || "297".equalsIgnoreCase(CheckQuoteNo) || "298".equalsIgnoreCase(CheckQuoteNo)){
								ErrorMsg.append(runner.getErrormsg(CheckQuoteNo));
								ErrorMsg.append(',');
							}
							else if("295".equalsIgnoreCase(CheckQuoteNo)){
								ErrorMsg.append("The requested Quote Number is Refferal. Please call our Customer Contact Center on 800-772 for further assistance.,");
							}
							else if("4491".equalsIgnoreCase(CheckQuoteNo)){
								request.setAttribute(CHKQNO,PolicyQuoteText);
								request.setAttribute("ApprovalStatus","Yes");
							}
							else
							{
								request.setAttribute(CHKQNO,CheckQuoteNo);								
							}
						}
						else if(validationformat.eMailValidation(emailId))
						{
							ErrorMsg.append("Please enter the valid Email ID");
							ErrorMsg.append(',');
						}else{
							ErrorMsg.append(runner.getErrormsg(STR296));
							ErrorMsg.append(',');
						}
					}
				}
				if(travelProductId.length()>0){
					dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchQuote.jsp");
				}else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchQuote.jsp");
				}
				dispatcher.forward(request, response);
		}*/
		if("BtoCSearchQuote".equalsIgnoreCase(path))
		{
			StringBuffer	ErrorMsg	=	new StringBuffer(1000);
			String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
			String emailId	= isNull(request.getParameter("emailId"));
			com.maan.common.Customer.DataSelect ds = new com.maan.common.Customer.DataSelect();
			boolean referalStatus = false;
			String applicationNo = "";
			String quoteNo = "";
			String customerId = "";
			String prodId = "";
			emailId = emailId.trim();
			PolicyQuoteText = PolicyQuoteText.trim();

			logger.info("Quote No==>"+PolicyQuoteText);
			logger.info("Email Id==>"+emailId);
			if("".equalsIgnoreCase(PolicyQuoteText) || "".equalsIgnoreCase(emailId))
			{
				if("".equalsIgnoreCase(PolicyQuoteText)){
					ErrorMsg.append(runner.getErrormsg(STR241));
					ErrorMsg.append(',');
				}
				if("".equalsIgnoreCase(emailId))
					ErrorMsg.append("Please enter the Email ID,");						
			}else{
				if(validationformat.isDigitValidationFormat(PolicyQuoteText) || validationformat.eMailValidation(emailId))
				{

					String[] CheckQuoteNo		=	dataSelect.getQuoteExistOrNotB2C(PolicyQuoteText,emailId,loginid);
					prodId = "".equalsIgnoreCase(CheckQuoteNo[1])?productid:CheckQuoteNo[1];
					customerId = CheckQuoteNo[2];
					applicationNo=CheckQuoteNo[3];
					session.setAttribute("customer_id",customerId);
					session.setAttribute("product_id",prodId);
					session.setAttribute("productid",prodId);
					if("".equalsIgnoreCase(CheckQuoteNo[0])){
						ErrorMsg.append(runner.getErrormsg("238"));
						ErrorMsg.append(',');
					}
					else if("294".equalsIgnoreCase(CheckQuoteNo[0])){
						ErrorMsg.append("This Quote has been converted into Policy.,");
					}
					else if("297".equalsIgnoreCase(CheckQuoteNo[0]) || "298".equalsIgnoreCase(CheckQuoteNo[0])){
						ErrorMsg.append((runner.getErrormsg(CheckQuoteNo[0])).replaceAll("Sorry it cannot be copied", ""));
						ErrorMsg.append(',');
					}
					else if("295".equalsIgnoreCase(CheckQuoteNo[0])){
						ErrorMsg.append("The requested Quote Number is Refferal. Please call our Customer Contact Center on 800-772 for further assistance.,");
					}
					else if("4491".equalsIgnoreCase(CheckQuoteNo[0])){
						referalStatus = true;
						quoteNo = PolicyQuoteText;						
					}
					else
					{
						quoteNo = CheckQuoteNo[0];								
					}
				}
				else if(validationformat.eMailValidation(emailId))
				{
					ErrorMsg.append("Please enter the valid Email ID");
					ErrorMsg.append(',');
				}else{
					ErrorMsg.append(runner.getErrormsg(STR296));
					ErrorMsg.append(',');
				}
			}
			if("".equals(ErrorMsg.toString().trim())){
				com.maan.product.ProductSelection prod = new com.maan.product.ProductSelection();
				String typeOfProduct = session.getAttribute("typeOfProduct")==null?prod.getProductType(prodId,branch):(String)session.getAttribute("typeOfProduct");
				logger.info("prodId==>"+prodId);
				logger.info("CustomerID==>"+customerId);
				if("30".equalsIgnoreCase(prodId) || "O".equalsIgnoreCase(typeOfProduct)){
					String schemeId=prod.getSchemeId(quoteNo, prodId);
					if(schemeId!=null && schemeId.length()>0)
						session.setAttribute("scheme_id",schemeId);
					logger.info("scheme_id==>"+schemeId);
					/*if(referalStatus){
						dispatcher=request.getRequestDispatcher("/OfficeInsurance/UploadSummary.jsp?reqFrom=Referral&mode=edit&QuoteNo="+quoteNo+"&ProId="+prodId+"&customerid="+customerId+"&customer="+customerId);
					}else{
						dispatcher=request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&QuoteNo="+quoteNo+"&qid="+quoteNo+"&customer="+customerId);
					}*/
					response.sendRedirect(request.getContextPath()+"/initHome.action?quoteStatus=Edit&quoteNo="+quoteNo+"&applicationNo="+applicationNo+"&customerId="+customerId+"&display=getQuote&contenTypeId=0");
				}else if("31".equalsIgnoreCase(prodId) ||"33".equalsIgnoreCase(prodId) ||"41".equalsIgnoreCase(prodId) || "T".equalsIgnoreCase(typeOfProduct)){
					session.setAttribute("TravelProductId",prodId);
					session.setAttribute("AllTravelProductIds", ds.getAllTravelPids(prodId, branch));
					if(referalStatus){
						dispatcher=request.getRequestDispatcher("/TravelInsurance/premium_summary.jsp?reqFrom=Referral&mode=edit&QuoteNo="+quoteNo+"&ProId="+prodId+"&customerid="+customerId+"&customer="+customerId);
					}else{
						//dispatcher=request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&QuoteNo="+quoteNo+"&qid="+quoteNo+"&customer="+customerId);
						//dispatcher=request.getRequestDispatcher("/editCustomer.action?quoteStatus=Edit&quoteNo="+quoteNo+"&applicationNo="+applicationNo+"&customerId="+customerId+"&display=getQuote");
						response.sendRedirect(request.getContextPath()+"/editCustomer.action?quoteStatus=Edit&quoteNo="+quoteNo+"&applicationNo="+applicationNo+"&customerId="+customerId+"&display=getQuote");
					}
				}else if("65".equalsIgnoreCase(prodId)){
					response.sendRedirect(request.getContextPath()+"/editQuoteMotor.action?quoteStatus=Edit&quoteNo="+quoteNo+"&applicationNo="+applicationNo+"&customerId="+customerId+"&display=getQuote");
				} else if("23".equalsIgnoreCase(prodId) || "H".equalsIgnoreCase(typeOfProduct)){
					if(referalStatus){
						dispatcher=request.getRequestDispatcher("/HomeInsurance/summary.jsp?reqFrom=Referral&mode=edit&QuoteNo="+quoteNo+"&ProId="+prodId+"&customerid="+customerId+"&customer="+customerId);
					}else{
						dispatcher=request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&QuoteNo="+quoteNo+"&reqPath=ViewQuote_B2B.jsp&customer="+customerId);
					}
				}else if("M".equalsIgnoreCase(typeOfProduct)){
					if(referalStatus){
						dispatcher=request.getRequestDispatcher("/servlet/InsertMotorInfo?status=edit&quoteNo="+quoteNo+"&QuoteNo="+quoteNo+"&premiumStatus=active&mode=edit&reqFrom=Referral");
					}else{
						dispatcher=request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&QuoteNo="+quoteNo+"&qid="+quoteNo+"&customer="+customerId);
					}
				}else{
					dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchQuote.jsp");
				}
			}else{
				request.setAttribute(ERRORMSG,ErrorMsg);
				dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchQuote.jsp");
			}
			if(dispatcher!=null)
			{
				dispatcher.forward(request, response);
			}
		}
		if("BtoCSearchPolicy".equalsIgnoreCase(path))
		{
				StringBuffer	ErrorMsg	=	new StringBuffer(1000);
				String	PolicyQuoteSelect	=	isNull(request.getParameter(POLQSEL));
				String PolicyQuoteText		=	isNull(request.getParameter(POLQTEXT));
				String reqFrom = request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
				PolicyQuoteText = PolicyQuoteText.trim();
				ProductSelection prod = new ProductSelection();
				if("1".equalsIgnoreCase(PolicyQuoteSelect))
				{
					ErrorMsg.append(runner.getErrormsg("321"));
					ErrorMsg.append(',');
				}
				else if("2".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{
						ErrorMsg.append(runner.getErrormsg(STR237));
						ErrorMsg.append(',');
					}
					else
					{
						String[] CheckQuoteNo		=	dataSelect.getPolicyExistOrNotB2C(PolicyQuoteText,productid,loginid);
						if(CheckQuoteNo==null && CheckQuoteNo.length<=0)
						{
							ErrorMsg.append(runner.getErrormsg(STR239));
							ErrorMsg.append(',');
						}
						else if("".equalsIgnoreCase(CheckQuoteNo[0]) || CheckQuoteNo[0].length()<=0)
						{
							ErrorMsg.append(runner.getErrormsg(STR239));
							ErrorMsg.append(',');
						}
						else
						{
							String prodId = "".equalsIgnoreCase(CheckQuoteNo[1])?productid:CheckQuoteNo[1];
							if(("M".equalsIgnoreCase(prod.getProductType(prodId, branch)) && "renew".equalsIgnoreCase(reqFrom)) || "".equalsIgnoreCase(reqFrom)){
								session.setAttribute("product_id",prodId);
								session.setAttribute("productid",prodId);
								request.setAttribute(CHKQNO,CheckQuoteNo[0]);
							}else{
								ErrorMsg.append("There is no Policy for the requested Policy no");
								ErrorMsg.append(',');
							}
						}
					}
				}
				else if("3".equalsIgnoreCase(PolicyQuoteSelect))
				{
					if("".equalsIgnoreCase(PolicyQuoteText))
					{

						ErrorMsg.append(runner.getErrormsg(STR241));
						ErrorMsg.append(',');
					}
					else
					{
						if(validationformat.isDigitValidationFormat(PolicyQuoteText))
						{

							String[] CheckQuoteNo		=	dataSelect.getQuotePolicyExistOrNotB2C(PolicyQuoteText,productid,loginid);

							if(CheckQuoteNo==null && CheckQuoteNo.length<=0){
									ErrorMsg.append("There is no Policy for the requested Quote no,");
							}
							else if("".equalsIgnoreCase(CheckQuoteNo[0]) || CheckQuoteNo[0].length()<=0){
								ErrorMsg.append("There is no Policy for the requested Quote no,");
							}
							else{								
								String prodId = "".equalsIgnoreCase(CheckQuoteNo[1])?productid:CheckQuoteNo[1];								
								if(("M".equalsIgnoreCase(prod.getProductType(prodId, branch)) && "renew".equalsIgnoreCase(reqFrom)) || "".equalsIgnoreCase(reqFrom)){
									session.setAttribute("product_id",prodId);
									session.setAttribute("productid",prodId);
									request.setAttribute(CHKQNO,CheckQuoteNo[0]);
								}else{
									ErrorMsg.append("There is no Policy for the requested Quote no");
									ErrorMsg.append(',');
								}
							}
						}
						else{
							ErrorMsg.append(runner.getErrormsg(STR296));
							ErrorMsg.append(',');
						}
					}
				}
				else if("4".equalsIgnoreCase(PolicyQuoteSelect))
				{
							String CheckQuoteNo1  = "";
							if("".equalsIgnoreCase(PolicyQuoteText)){
										ErrorMsg.append(runner.getErrormsg(STR284));
										ErrorMsg.append(',');
							}
							else{
									CheckQuoteNo1		=	dataSelect.getCustomerQuoteName(PolicyQuoteText.trim(),productid,loginid);
									if("".equalsIgnoreCase(CheckQuoteNo1))
									{
										ErrorMsg.append(runner.getErrormsg(STR285));
										ErrorMsg.append(',');
									}
									else
									{
											request.setAttribute(CHKQNO,CheckQuoteNo1);
									}
							}
				}

				request.setAttribute(ERRORMSG,ErrorMsg);

				/*if(travelProductId.length()>0){
					dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchPolicy.jsp");
				}
				else{*/
					dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchPolicy.jsp");
				//}
				dispatcher.forward(request, response);
		}
		if("BtoCSearchFlowQuote".equalsIgnoreCase(path))
		{
			StringBuffer ErrorMsg	=	new StringBuffer(1000);
			String quote =	isNull(request.getParameter("Quote_No"));
			String cust	= isNull(request.getParameter("customer_id"));
			String RadioQuoteNo	= isNull(request.getParameter(RADIOQNO));
			String ApprovalStatus = isNull(request.getParameter("approvedStatus"));

			if("".equalsIgnoreCase(RadioQuoteNo))
			{
			  ErrorMsg.append(runner.getErrormsg("321"));//286
			  ErrorMsg.append(',');
			  request.setAttribute(ERRORMSG,ErrorMsg);
			  request.setAttribute(CHKQNO,quote); //oct-09
			  request.setAttribute("ApprovalStatus",ApprovalStatus); //oct-09

			  dispatcher=request.getRequestDispatcher("/CopyQuote/BtoCSearchQuote.jsp");
			  dispatcher.forward(request, response);
			}
			else
			{
				session.setAttribute("b2c","b2c");

				if("Yes".equalsIgnoreCase(ApprovalStatus)){
					dispatcher=request.getRequestDispatcher("../HomeInsurance/summary.jsp");
				}
				else{
					dispatcher=request.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp?mode=Edit&customer="+cust+"&QuoteNo="+quote+"&reqPath=ViewQuote_B2B.jsp");
				}
				if(dispatcher!=null)
				{
					dispatcher.forward(request, response);
				}
			}
		}

		if(path.equalsIgnoreCase("BtoCSearchCancel"))
		{
			response.sendRedirect(pathh+"/CopyQuote/BtoCSearchQuote.jsp");
		}
		/** B TO C Search Quote **/
		}
		catch(BaseException e){
			throw new BaseException(e, "Error");
		}
		catch(Exception e){
			throw new BaseException(e, "Error");
		}
	}

	public String isNull(final String content)throws BaseException{
		final StringBuffer contents = new StringBuffer(1000);
		if(content==null){
			contents.append("");
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}


}