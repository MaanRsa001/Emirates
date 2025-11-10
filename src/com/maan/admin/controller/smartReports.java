package com.maan.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class smartReports extends HttpServlet 
{
	PrintWriter out = null;
	String error = "";
	int count = 0;

	java.util.Date dd = new java.util.Date();

	String datcheck = "";

	java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat(
			"dd-MM-yyyy");

	String deActivatedDate = "";

	// HttpSession session=null;
	public void init(ServletConfig config) throws ServletException {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
		HttpSession session = req.getSession(true);
		ArrayList Alist = new ArrayList();
		StringTokenizer str, str1;
		StringBuffer headlines = new StringBuffer();
		StringBuffer headlines1 = new StringBuffer();
		headlines.append("DATEWISE");
		headlines1.append("REPORTS");
		PrintWriter out = res.getWriter();
		int flag = 0;
		int i = 0;
		String loginPersonId = "";
		loginPersonId = req.getParameter("loginPersonId") == null ? "" : req
				.getParameter("loginPersonId");
		System.out.println("Login id" + loginPersonId);
		RequestDispatcher rd = req
				.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		if (rd != null)
			rd.include(req, res);
		String brokers[] = new String[0];
		String commodity[] = new String[0];
		String mode[] = new String[0];
		//
		String mode1[] = new String[1];
		mode1[0] = "";
		//
		String trans[] = new String[0];
		//
		String trans1[] = new String[1];
		trans1[0] = "";
		//
		String data1 = "", data2 = "";
		String product_id = "";
		String rag1 = "", rag2 = "", rag3 = "";
		String origin = "", destin = "";
		String busTy = "", busTy1 = "";
		StringBuffer errorinfo = new StringBuffer("");

		if ((loginPersonId == null) || (loginPersonId == "")) {
			loginPersonId = (String) session.getAttribute("user");
		}
		if (req.getParameter("requestfrom").equalsIgnoreCase("smartReports")) {
			try {
				String values = null;
				String[][] right = new String[0][0];
				String mon1 = "", yea1 = "", day = "";

				com.maan.admin.DAO.ReportBean cc = new com.maan.admin.DAO.ReportBean();
				cc.setLoginPersonId(loginPersonId);
				datcheck = req.getParameter("dat1");
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
				cc.setProduct(req.getParameter("Products") == null ? "" : req
						.getParameter("Products"));
				cc.setBroker(req.getParameterValues("Brokers"));
				cc.setCommodityAll(req.getParameterValues("commodity"));
				cc.setBusinessType(req.getParameter("c1") == null ? "" : req
						.getParameter("c1"));

				mode = req.getParameterValues("s1");
				if (mode == null)
					mode = mode1;
				trans = req.getParameterValues("s2");
				if (trans == null)
					trans = trans1;

				// if(true)
				// return;
				cc.setModeType(mode);
				cc.setTransType(trans);
				cc.setRag1(req.getParameter("r1") == null ? "" : req
						.getParameter("r1"));
				cc.setRag2(req.getParameter("r2") == null ? "" : req
						.getParameter("r2"));
				cc.setRag3(req.getParameter("r3") == null ? "" : req
						.getParameter("r3"));
				cc.setOrigination(req.getParameter("origination") == null ? ""
						: req.getParameter("origination"));
				cc.setDestination(req.getParameter("destination") == null ? ""
						: req.getParameter("destination"));
				busTy = req.getParameter("c1") == null ? "" : req
						.getParameter("c1");
				
				error = cc.validateFields1(busTy);
				if (error.length() > 0) {
					req.setAttribute("errorDetail", error);
					rd = req
							.getRequestDispatcher("../reports/smartReports.jsp");
					rd.forward(req, res);
				} else {
					
					data1 = (req.getParameter("dobDay") + "-"
							+ req.getParameter("dobMonth") + "-" + req
							.getParameter("dobYear"));
					data2 = (req.getParameter("dobDay1") + "-"
							+ req.getParameter("dobMonth1") + "-" + req
							.getParameter("dobYear1"));
					brokers = req.getParameterValues("Brokers");
					// commodity = req.getParameterValues("commodity");
					String comm1 = req.getParameter("transit_country"); // values
					String ccc = req.getParameter("transit_countryId"); // 6,7
					
					if ((ccc.length() > 0) && (ccc != null)) {
						ccc = ccc.trim();
						str = new StringTokenizer(ccc, ",");
						while (str.hasMoreElements()) {
							String k2 = (String) str.nextElement();
							count++;
						}
						commodity = new String[count];
						str1 = new StringTokenizer(ccc, ",");
						while (str1.hasMoreElements()) {
							// System.out.println("value
							// is"+str1.nextElement());
							commodity[i] = (String) str1.nextElement();
							
							i++;
						}
					}

					

					rag1 = req.getParameter("r1") == null ? "" : req
							.getParameter("r1");
					rag2 = req.getParameter("r2") == null ? "" : req
							.getParameter("r2");
					rag3 = req.getParameter("r3") == null ? "" : req
							.getParameter("r3");
					origin = req.getParameter("origination") == null ? "" : req
							.getParameter("origination");
					destin = req.getParameter("destination") == null ? "" : req
							.getParameter("destination");
					product_id = req.getParameter("Products") == null ? ""
							: req.getParameter("Products");
					busTy = req.getParameter("c1") == null ? "" : req
							.getParameter("c1");
					

					if (brokers != null) {
						headlines.append(", BROKER WISE");
					}
					if ("3".equalsIgnoreCase(product_id)|| "11".equalsIgnoreCase(product_id)||"FF".equalsIgnoreCase(product_id)) 
					{
						headlines.append(", PRODUCT WISE");
						if ("3".equalsIgnoreCase(product_id)||"FF".equalsIgnoreCase(product_id)) 
						{
							headlines.append(" [One off Policy]");
						} 
						else
						{
							headlines.append(" [Open Cover]");
						}
					}
					

					if ("0".equalsIgnoreCase(busTy)) {
						headlines.append(", EXISTING BUSINESS");
					}
					if ("1".equalsIgnoreCase(busTy)) {
						headlines.append(", NEW BUSINESS");
					}
					

					if ((mode != null) || (trans != null)) {
						headlines.append(",<br> COVERAGE WISE [");
						for (i = 0; i < mode.length; i++) {
							if ("1".equalsIgnoreCase(mode[i])) {
								headlines.append(" SEA, ");
							}
							if ("2".equalsIgnoreCase(mode[i])) {
								headlines.append(" AIR, ");
							}
							if ("3".equalsIgnoreCase(mode[i])) {
								headlines.append(" ROAD, ");
							}
							if ("4".equalsIgnoreCase(mode[i])) {
								headlines.append(" MULTI MODE, ");
							}
							// headlines.replace(headlines.length()-1,kk,",");
						}
						int kk = headlines.lastIndexOf(",");
						
						headlines.replace(kk, headlines.length(), " ");
						
					}
					if ((rag1 != "") || (rag2 != "") || (rag3 != "")) {
						headlines.append(", RAG WISE [");
						if ("R".equalsIgnoreCase(rag1)) {
							headlines.append(" R, ");
						}
						if ("A".equalsIgnoreCase(rag2)) {
							headlines.append(" A,");
						}
						if ("G".equalsIgnoreCase(rag3)) {
							headlines.append(" G, ");
						}
						int kk = headlines.lastIndexOf(",");
						
						headlines.replace(kk, headlines.length(), " ");
						headlines.append("]");
						
					}
					if (!"All".equalsIgnoreCase(origin)
							|| !"All".equalsIgnoreCase(destin)) {
						headlines.append(", COUNTRY WISE");
					}
					if ((commodity != null) && (commodity.length > 0)) {
						headlines.append(", COMMODITY WISE");
						// headlines = "DATEWISE, RAGWISE";
					} else {
						// headlines.append(", COMMODITY WISE");
					}
					Alist.add(0, data1);
					Alist.add(1, data2);
					Alist.add(2, brokers);
					Alist.add(3, commodity);
					Alist.add(4, mode);
					Alist.add(5, trans);
					Alist.add(6, rag1);
					Alist.add(7, rag2);
					Alist.add(8, rag3);
					Alist.add(9, origin);
					Alist.add(10, destin);
					Alist.add(11, product_id);
					Alist.add(12, busTy);
					if (Alist.size() > 0) {
						req.setAttribute("Alist", Alist);
					}
					if ((commodity != null) && (commodity.length > 0)) {
						for (i = 0; i < commodity.length; i++) {
							if ("All".equalsIgnoreCase(commodity[i])) {
								flag = 1;
							} else {
								flag = 2;
							}
						}
					} else {
						flag = 1;
					}
					if(flag == 2) 
					{
						req.setAttribute("mode", "noInter");
						req.setAttribute("headlines", headlines + " "+ headlines1);
						rd = req.getRequestDispatcher("../reports/smartReportIntermediate.jsp");
					} 
					else 
						{
						req.setAttribute("headlines", headlines + " "+ headlines1);
						rd = req.getRequestDispatcher("../reports/smartReportIntermediate.jsp");
					}
					rd.forward(req, res);
				}

			} catch (Exception eee) {
				System.out.println("Errior==" + eee);
			}
		}
	}

	public void destroy() {
		
	}

}