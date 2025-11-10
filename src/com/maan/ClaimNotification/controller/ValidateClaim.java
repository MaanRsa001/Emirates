package com.maan.ClaimNotification.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.*;

import com.maan.ClaimNotification.DAO.ClaimNotificationBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateClaim extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		String requestFrom = request.getParameter("requestFrom");
		requestFrom = requestFrom == null ? "" : requestFrom;
		// Data Base Mode check
		String modeCheck = request.getContextPath();
		modeCheck = modeCheck == null ? "" : modeCheck;
		if (session.getAttribute("user") == null) {
			if (modeCheck.equalsIgnoreCase("/E-Cargo")) {
				modeCheck = "Live";
			} else {
				modeCheck = "Live";
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = modeCheck;
		}
		else {
			com.maan.DBCon.DBConnectionStatus.statusStatic = (String) session.getAttribute("userLoginMode");
		}
		// End

		if (requestFrom.equalsIgnoreCase("FirstPageWithOutLogin"))
		{
			Vector error = new Vector();
			RequestDispatcher rd = null;
			ClaimNotificationBean cb = new ClaimNotificationBean();
			String first = request.getParameter("firstpart");
			String second = request.getParameter("secondpart");
			String third = request.getParameter("thirdpart");
			String fourth = request.getParameter("fourthpart");

			first = first == null ? "0" : first;
			second = second == null ? "0" : second;
			third = third == null ? "0" : third;
			fourth = fourth == null ? "0" : fourth;

			if (first.equals("0") || second.equals("0") || third.equals("0") || fourth.equals("0")) {
				error.add(new String("Please Enter a Valid Policy Number"));
			}
			try {
				Integer.parseInt(first);
				Integer.parseInt(second);
				Integer.parseInt(third);
				Long.parseLong(fourth);
			}
			catch (Exception ex) {
				if (!error.contains("Please Enter a Valid Policy Number"))
					error.add(new String("Please Enter a Valid Policy Number"));
			}
			if (error.size() > 0) {
				request.setAttribute("Error", error);
				rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationEntry.jsp");
				if (rd != null)
					rd.forward(request, response);
				return;
			}
			else 
			{
				String policy_no = first + "/" + second + "/" + third + "/"	+ fourth;
				String message = "";
				System.out.println("constructed Policy number     " + policy_no);
				String result = cb.validatePolicyExpiryDate(policy_no);
				System.out.println("result          " + result);
				result = result == null ? "" : result;
				if (result.equalsIgnoreCase("NO_POLICY_EXIST")) 
				{
					message = "No Policy Exist for given Number";
					request.setAttribute("message", message);
					rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationEnd.jsp");
				}
				else if (result.equalsIgnoreCase("VALID")) 
				{
					rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationDetails.jsp");
					request.setAttribute("policynumber", policy_no);
				}
				else if (result.equals("CLAIM_ALREADY_PENDING")) {
					message = "Claim Made for policy number " + policy_no+ " is already pending.Cannot make further Claims ";
					request.setAttribute("message", message);
					rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationEnd.jsp");
				}
				if (rd != null)
					rd.forward(request, response);
				return;
			}
		}
		else if (requestFrom.equalsIgnoreCase("ClaimUserPolicySearch"))
		{
			StringBuffer error = new StringBuffer();
			RequestDispatcher rd = null;
			ClaimNotificationBean cb = new ClaimNotificationBean();

			String loginId = (String) session.getAttribute("user");
			String branchCode = (String) session
					.getAttribute("LoginBranchCode");

			String first = request.getParameter("firstpart");
			String second = request.getParameter("secondpart");
			String third = request.getParameter("thirdpart");
			String fourth = request.getParameter("fourthpart");

			String policynumber = request.getParameter("policy_no")!=null?request.getParameter("policy_no"):"";
			
			if(policynumber.length()>0)
			{
				StringTokenizer st = new StringTokenizer(policynumber,"/");
				while(st.hasMoreTokens())
				{
					first = st.nextToken();
					second = st.nextToken();
					third = st.nextToken();
					fourth = st.nextToken();
				}
			}
			first = first == null ? "0" : first;
			second = second == null ? "0" : second;
			third = third == null ? "0" : third;
			fourth = fourth == null ? "0" : fourth;

			error = cb.claimUserValidation(first, second, third, fourth,loginId, branchCode);

			if (error.length() > 0) {
				request.setAttribute("error", error);
				rd = request.getRequestDispatcher("/admin/ClaimPolicySearch.jsp");
				if (rd != null)
					rd.forward(request, response);
				return;
			}
			else {
				String currencyType = "";
				String decimalPlace = "";
				String policy_no = first + "/" + second + "/" + third + "/"+ fourth;
				String[][] result = cb.getDetailsForPolicyNumberSearch(policy_no, branchCode);
				String[][] openCover = cb.getPortfolioByDateMultiSearch(policy_no, branchCode);
				HashMap brokDetails = (HashMap) session.getAttribute("BrokerDetails");
				if (brokDetails.size() > 0) {
					currencyType = (String) brokDetails.get("CurrencyName");
					decimalPlace = (String) brokDetails.get("CurrencyDecimal");
					decimalPlace = decimalPlace == null ? "2" : decimalPlace;
				}
				result = result == null ? new String[0][0] : result;
				openCover = openCover == null ? new String[0][0] : openCover;

				request.setAttribute("currencyType", currencyType);
				request.setAttribute("decimalPlace", decimalPlace);
				request.setAttribute("PolicySearchDetails", result);
				request.setAttribute("getPortfolioByDateMultiSearch", openCover);

				rd = request.getRequestDispatcher("/ClaimNotification/SearchResult.jsp");
				if (rd != null)
					rd.forward(request, response);
				return;
			}
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}