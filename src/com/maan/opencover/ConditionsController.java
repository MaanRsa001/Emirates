package com.maan.opencover;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.opencover.conditions;
import com.maan.common.util.dataCollection;
import proj.date.DateFunction;
import proj.sql.QueryBuilder;
import com.maan.services.util.runner;

public class ConditionsController extends HttpServlet
{

	private StringBuffer error = new StringBuffer();

	HttpSession session = null;

	private RequestDispatcher dispatcher;

	/**
	 * Constructor of the object.
	 */
	public ConditionsController() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		session = request.getSession(true);
		if (session.getAttribute("ses") == null) {
			response.sendRedirect("../login/error_messg_pdf.jsp");
			return;
		}

		String usrModeController = (String) session
				.getAttribute("userLoginMode") == null ? "" : (String) session
				.getAttribute("userLoginMode");
		if ("".equalsIgnoreCase(usrModeController)
				|| " ".equalsIgnoreCase(usrModeController)) {
			response.sendRedirect("../login/error_messg_pdf.jsp");
			return;
		}
		com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;

		String path = request.getRequestURI();
		path = path.substring(0, path.lastIndexOf("/") + 1);

		
		out.println("path--->" + path);

		int count = 0;

		String dateError = "";
		String effectDate = "";

		error = new StringBuffer();

		count = Integer.parseInt(request.getParameter("clausesSize") == null ? "0": request.getParameter("clausesSize"));

		out.println("the Size" + count);

		request.removeAttribute("fullDetails");

		HashMap fullDetails = new HashMap();

		String comm = "";
		String commStatus = "";

		String des = "No Description Provided";

		int finalCount = 1;
		int errorAllCount = 0;

		String productId = "";
		String loginId = "";
		String companyId = "";
		String sessionId = "";
		String applicationNoEDIT = "0";
		String clausesSummary = "";
		String proposalNo = "";

		dataCollection data = new dataCollection();

		// For Open Cover Policy on Aug 14 2007 by karthy

		String openCoverNo = "";

		String clausesIdOrg = "";

		productId = request.getParameter("productId") == null ? "0" : request
				.getParameter("productId");

		openCoverNo = request.getParameter("openCoverNo") == null ? "0"
				: request.getParameter("openCoverNo");

		loginId = request.getParameter("loginId") == null ? "" : request
				.getParameter("loginId");

		companyId = request.getParameter("companyId") == null ? "0" : request
				.getParameter("companyId");

		sessionId = request.getParameter("sessionId") == null ? "" : request
				.getParameter("sessionId");

		applicationNoEDIT = request.getParameter("applicationNoEDIT") == null ? "0"
				: request.getParameter("applicationNoEDIT");

		proposalNo = request.getParameter("proposalNo") == null ? "0" : request
				.getParameter("proposalNo");

		effectDate = request.getParameter("effectDate") == null ? "0" : request
				.getParameter("effectDate");

		if ("".equalsIgnoreCase(applicationNoEDIT)) {
			applicationNoEDIT = "0";

		}

		clausesSummary = request.getParameter("clausesSummary") == null ? "NOSUMMARY"
				: request.getParameter("clausesSummary");

		/*
		 * out.println("the Session Id is"+sessionId); out.println("the
		 * companyId is"+companyId); out.println("the loginId is"+loginId);
		 * out.println("the productId is"+productId); out.println("the
		 * applicationNoEDIT is"+applicationNoEDIT);
		 */

		// if(2>1)
		// return;

		for (int i = 0; i < count; i++) {
			// out.println("<br>");

			comm = request.getParameter("clauses" + (i + 1)) == null ? comm
					: request.getParameter("clauses" + (i + 1));
			out.println("the comm is " + comm);

			des = request.getParameter("description" + (i + 1)) == null ? des
					: request.getParameter("description" + (i + 1));



			if ("".equals(comm)) {
				comm = "off";

			}

			if ("".equals(des)) {
				des = "No Description Provided";
			}

			if (comm.length() > 0 && !("off".equalsIgnoreCase(comm))) {

				commStatus = "on";

				if ("No Description Provided".equalsIgnoreCase(des)) {

					error.append("<br><font color=red size=2><b>*"+ getErrormsg("135", "Clauses Description")
									+ " <i> "
									+ (i + 1)
									+ " </i><br>This is the original clause for the above : <font color=blue size=2>"
									+ comm + "</font></b></font>");

				}

			}


			if ("off".equalsIgnoreCase(comm))
			// if(error.length()>0)
			{


				comm = "";
				commStatus = "";
				errorAllCount = errorAllCount + 1;
			} else {

				

				clausesIdOrg = request.getParameter("clausesIdOrg" + (i + 1)) == null ? clausesIdOrg
						: request.getParameter("clausesIdOrg" + (i + 1));

				fullDetails.put("clausesId" + (finalCount), clausesIdOrg);

				

				fullDetails.put("description" + (finalCount), des);

				finalCount = finalCount + 1;



				comm = "";
				commStatus = "";
				// fr="";

			}

		}// End of For Loop


		if (errorAllCount == count) {


			// Newly Modified By Karthy
			error.append("<br><font color=red size=2><b>*"
					+ getErrormsg("136", "clauses") + "<i></i></b></font>");

			errorAllCount = 0;

		}

		// This is Newly Added For Date Checking

		dateError = data.checkPickerDate(effectDate);
		if ("Invalid".equalsIgnoreCase(dateError)) {
			error.append("<br><font color=red size=2><b>*"
					+ getErrormsg("49", "Effective Date")
					+ "<i></i></b></font>");
		}

		if ("Invalid".equalsIgnoreCase(getDateStatus(proposalNo, effectDate))) {
			// error.append("<br><font color=red
			// size=2><b>*"+getErrormsg("49","Effective
			// Date")+"<i></i></b></font>");
			error.append("<br><font color=red size=2><b>*" + "Enter valid Date"
					+ "<i></i></b></font>");
		}

		if (error.length() > 0) {
			// out.println("<br>FINAL MODE ERROE---"+finalCount+"<br>");

			request.setAttribute("errorMessageClauses", error);

			dispatcher = request.getRequestDispatcher("/premium/conditionsOpenEdit.jsp");

			dispatcher.forward(request, response);
			// out.println("COmes here ERROR");

		} else {
			finalCount = finalCount - 1;
			fullDetails.put("finalCount", "" + finalCount);

			

			com.maan.opencover.conditions cT = new conditions();

			if (clausesSummary.equalsIgnoreCase("NOSUMMARY")) {
			
				cT.setSessionId(sessionId);
				cT.setLoginCode(loginId);
				cT.setCompanyId(companyId);
				cT.setProductId(productId);
				cT.setProposalNo("7000000");
				cT.setModeOfTransport("1");
				cT.setCoverId("2");
				cT.setExtraCoverId("1");
				// Nelwy Added by karthy for OPEN COVER POLICY on AUG 2007
				cT.setOpenCoverNo(openCoverNo);

				cT.setEffectDate(effectDate);

				cT.setStageId("2");

				// request.setAttribute("selectedCount",""+finalCount);
				if (Integer.parseInt(applicationNoEDIT) > 0) {
					System.out.println("EDIT MODE clauses");
					request.setAttribute("applicationNo", applicationNoEDIT);
					cT.setApplicationNo(applicationNoEDIT);
					cT.insertUpdateConditions(fullDetails);
					request.setAttribute("fullDetails", fullDetails);
					dispatcher = request.getRequestDispatcher("/premium/conditionsOpenEdit1.jsp");

				} else {
					cT.insertUpdateConditions(fullDetails);
					request.setAttribute("fullDetails", fullDetails);
					dispatcher = request.getRequestDispatcher("/premium/conditionsOpenEdit1.jsp");
				}
				dispatcher.forward(request, response);
			} 
		}
	}

	/**
	 * The doPut method of the servlet. <br>
	 * 
	 * This method is called when a HTTP put request is received.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Put your code here
	}

	/**
	 * Returns information about the servlet, such as author, version, and
	 * copyright.
	 * 
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occure
	 */
	public void init() throws ServletException {
		// Put your code here
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

	public String getDateStatus(String proNo, String effDate) 
	{
		String result = new String();
		String[][] DateAndTime = new String[0][0];
		String sqlQuery = "";
		java.util.Date systime = new java.util.Date();
		System.out.println("System Date is" + systime.getDate());
		String args[] = new String[2];
		try
		{
			args[0] = proNo;
			args[1] = proNo;

			sqlQuery = "select to_char(effective_Date-8/24,'DD-MM-YYYY')as date1,to_char(effective_Date-8/24,'HH24')as HOUR1,to_char(effective_Date,'MI')as MIN1,to_char(effective_Date,'SS')as SECOND1 from open_cover_clauses where amend_id=(select max(amend_id) from open_cover_clauses where proposal_no='"+proNo+"') and proposal_no='"+proNo+"'";

			System.out.println("getDateStatus. @@@@......" + sqlQuery);
			DateAndTime = runner.multipleSelection(sqlQuery,args);

			if(DateAndTime.length > 0 && DateAndTime != null)
			{
				long diff = 0;
				try
				{
					DateFunction dbr = new DateFunction();
					System.out.println("startDate---@@@@---->"+ DateAndTime[0][0]);
					System.out.println("startDate1--@@@@---->"+ effDate);
					diff = dbr.getDayDifference(dbr.getCalendar(DateAndTime[0][0]), dbr.getCalendar(effDate));
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
				System.out.println("diff----@@@@--------->" + diff);
				if (diff <= 0) 
				{
					result = "INVALID";
				}
			}
		} 
		catch (Exception e) 
		{
			System.out.println("getDateStatus ..." + e.toString());
			result = "INVALID";
		}
		return result;
	}
} // Class
