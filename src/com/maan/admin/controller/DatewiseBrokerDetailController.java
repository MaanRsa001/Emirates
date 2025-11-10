package com.maan.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DatewiseBrokerDetailController extends HttpServlet {
	PrintWriter out = null;

	public void init(ServletConfig config) throws ServletException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse res)
			throws IOException, ServletException {
		String error = "";
		com.maan.admin.DAO.DatewiseBrokerDetails broRpt = new com.maan.admin.DAO.DatewiseBrokerDetails();
		HttpSession session = request.getSession(true);
		PrintWriter out = res.getWriter();
		String loginPersonId = (String) session.getAttribute("loginPersonId");
		RequestDispatcher dispatcher1 = request
				.getRequestDispatcher("/login/sessionsCheckNormal.jsp");

		if (dispatcher1 != null)
			dispatcher1.include(request, res);
		if (loginPersonId == null)
			loginPersonId = (String) session.getAttribute("user");

		if (request.getParameter("requestfrom").equalsIgnoreCase("brokerRpt")) {
			String dobDay = request.getParameter("dobDay") == null ? "0"
					: request.getParameter("dobDay");
			String dobMonth = request.getParameter("dobMonth") == null ? "0"
					: request.getParameter("dobMonth");
			String dobYear = request.getParameter("dobYear") == null ? "0"
					: request.getParameter("dobYear");
			String dobDay1 = request.getParameter("dobDay1") == null ? "0"
					: request.getParameter("dobDay1");
			String dobMonth1 = request.getParameter("dobMonth1") == null ? "0"
					: request.getParameter("dobMonth1");
			String dobYear1 = request.getParameter("dobYear1") == null ? "0"
					: request.getParameter("dobYear1");
			String sdate = dobDay + "-" + dobMonth + "-" + dobYear;
			String edate = dobDay1 + "-" + dobMonth1 + "-" + dobYear1;
			String brokerIds = request.getParameter("brokerIds") == null ? "": request.getParameter("brokerIds");
			String information[][] = new String[0][0];
			String info[][] = new String[0][0];

			broRpt.setDobDay(dobDay);
			broRpt.setDobMonth(dobMonth);
			broRpt.setDobYear(dobYear);
			broRpt.setDobDay1(dobDay1);
			broRpt.setDobMonth1(dobMonth1);
			broRpt.setDobYear1(dobYear1);
			broRpt.setData1(sdate);
			broRpt.setData2(edate);
			broRpt.setbrokerIds(brokerIds);
			error = broRpt.dateValidation();
			String admBranch = "";
			String totLogin = "";
			admBranch = (String)session.getAttribute("adminBranch");
			admBranch = admBranch == null ? "" : admBranch;
			if(brokerIds.equalsIgnoreCase("all"))
			{
				totLogin = broRpt.getBrokerLoginId(admBranch);
			}
			System.out.println("LoginIds..."+totLogin);
			if (error.length() > 0) {
				request.setAttribute("errorDetail", error);
				RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOpenCoverDatewiseRpt.jsp");
				if (dispatcher != null){
					dispatcher.forward(request, res);
				    return;
				}
			}
			else 
			{
				if(!brokerIds.equalsIgnoreCase("all"))
				{
					information = broRpt.datewiseBrokerOpenCoverDetails();
					
					request.setAttribute("information", information);
					//request.setAttribute("inf", info);
					request.setAttribute("sdate", sdate);
					request.setAttribute("edate", edate);
					RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOpenCoverReport.jsp");
					if (dispatcher != null){
							dispatcher.forward(request, res);
							return;
					}
				}
				else
				{
					
					information = broRpt.datewiseBrokerOpenCoverDetailsAll(totLogin);
					request.setAttribute("information", information);
					//request.setAttribute("info", info);
					request.setAttribute("sdate", sdate);
					request.setAttribute("edate", edate);
					RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOpenCoverReport.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, res);
						return;
					}
				}
			}
		}
	}

	
}