package com.maan.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.admin.DAO.AdminPortBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class PortfolioHomeTravelController extends HttpServlet
{
	private static final Logger logger = LogUtil.getLogger(PortfolioHomeTravelController.class);
	transient private RequestDispatcher reqDis 		= null;
	final static transient private String REQFROM   = "requestfrom";
	final static transient private String DOBDAY    = "dobDay";
	final static transient private String PID       = "pid";
	final static transient private String DOBMONTH  = "dobMonth";
	final static transient private String DOBYEAR   = "dobYear";
	final static transient private String DOBDAY1   = "dobDay1";
	final static transient private String DOBMONTH1 = "dobMonth1";
	final static transient private String DOBYEAR1  = "dobYear1";
	final static transient private String REP       =  "rep";

	private static final long serialVersionUID = 1L;

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}

	public void processResult(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException,BaseException 
	{
		try{
				HttpSession session = request.getSession(true);
				String loginPersonId = (String) session.getAttribute("loginPersonId");
				final RequestDispatcher dispatcher1 = request.getRequestDispatcher("/login/sessionsCheckNormal.jsp");

				if (dispatcher1 != null){
					dispatcher1.include(request, response);
				}
				if (loginPersonId == null){
					loginPersonId = (String) session.getAttribute("user");
				}

				if (request.getParameter(REQFROM).trim().equalsIgnoreCase("portfolioHomeTravel")) {

				String error = "";


				AdminPortBean ccBean = new AdminPortBean();
				ccBean.setDobDay(isNull(request.getParameter(DOBDAY)));
				ccBean.setDobMonth(isNull(request.getParameter(DOBMONTH)));
				ccBean.setDobYear(isNull(request.getParameter(DOBYEAR)));
				ccBean.setDobDay1(isNull(request.getParameter(DOBDAY1)));
				ccBean.setDobMonth1(isNull(request.getParameter(DOBMONTH1)));
				ccBean.setDobYear1(isNull(request.getParameter(DOBYEAR1)));
				ccBean.setRep(isNull(request.getParameter(REP)));
				ccBean.setData1(request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/"+ request.getParameter(DOBYEAR));
				ccBean.setData2(request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/"+ request.getParameter(DOBYEAR1));

				String data1 = (request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/" + request.getParameter(DOBYEAR));
				String data2 = (request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/" + request.getParameter(DOBYEAR1));
				String productId = isNull(request.getParameter(PID));
				String rep1 = isNull(request.getParameter(REP));
				error = ccBean.validateTrashFields();
				if ("select".equalsIgnoreCase(productId)){
					error = error + "<br>* " + runner.getErrormsg("288");
				}
				if (error.length() > 0) {
					request.setAttribute("errorDetail", error);
					reqDis = request.getRequestDispatcher("../admin/HomeAdminPortfolio.jsp");
				}
				else {
					request.setAttribute("data1", data1);
					request.setAttribute("data2", data2);
					request.setAttribute("rep1", rep1);
					request.setAttribute(PID, productId);
					reqDis = request.getRequestDispatcher("../admin/Approved_Policy_Portfolio_HomeTravel.jsp");
				}
				reqDis.forward(request, response);
		}
		else if ("policyCancel".equalsIgnoreCase(request.getParameter(REQFROM).trim()))
		{
			String nlens     = request.getParameter("normalLen")==null?"0":request.getParameter("normalLen");
			String status    = "";
			AdminPortBean ccBean = new AdminPortBean();
			//int nlen 		 = Integer.parseInt(nlens);

			for(int l=0;l<Integer.parseInt(nlens);l++){
				String cps = isNull(request.getParameter("cancelPol"+l));
				if(cps.length()>0){
					status = status+"'"+cps+"',";
				}
			}
			if(status.length()>0){
				status = status.substring(0,(status.length()-1));
				ccBean.updatePolicyStatus(status,(String)session.getAttribute("user"));
			}
			reqDis = request.getRequestDispatcher("conformTravelPolicyDeactivate.jsp");
			reqDis.forward(request, response);
		}
		else if ("HomeTravelReports".equalsIgnoreCase(request.getParameter(REQFROM)))
		{
				String error = "";

				AdminPortBean ccBean = new AdminPortBean();
				ccBean.setDobDay(isNull(request.getParameter(DOBDAY)));
				ccBean.setDobMonth(isNull(request.getParameter(DOBMONTH)));
				ccBean.setDobYear(isNull(request.getParameter(DOBYEAR)));
				ccBean.setDobDay1(isNull(request.getParameter(DOBDAY1)));
				ccBean.setDobMonth1(isNull(request.getParameter(DOBMONTH1)));
				ccBean.setDobYear1(isNull(request.getParameter(DOBYEAR1)));
				ccBean.setRep(isNull(request.getParameter(REP)));
				ccBean.setData1(request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/"+ request.getParameter(DOBYEAR));
				ccBean.setData2(request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/"+ request.getParameter(DOBYEAR1));
				//String data1 = (request.getParameter(DOBDAY) + "-"+ request.getParameter(DOBMONTH) + "-" + request.getParameter(DOBYEAR));
				//String data2 = (request.getParameter(DOBDAY1) + "-"+ request.getParameter(DOBMONTH1) + "-" + request.getParameter(DOBYEAR1));
				String productId = isNull(request.getParameter(PID));
				//String rep1 = isNull(request.getParameter(REP));
				error = ccBean.validateTrashFields();
				if ("select".equalsIgnoreCase(productId)) {
					error = error + "<br>* " + runner.getErrormsg("288");
				}
				if (error.length() > 0) {
					request.setAttribute("errorDetail", error);
					reqDis = request.getRequestDispatcher("../admin/HomePolicyReport.jsp");
				}
				else {
					request.setAttribute("data1", (request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/" + request.getParameter(DOBYEAR)));
					request.setAttribute("data2", (request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/" + request.getParameter(DOBYEAR1)));
					request.setAttribute("rep1", isNull(request.getParameter(REP)));
					request.setAttribute(PID, productId);
					String pids = isNull(request.getParameter(PID));
					session.setAttribute(PID, pids);
					reqDis = request.getRequestDispatcher("../admin/finalreportHome1.jsp");
				}
				reqDis.forward(request, response);
		}
		else if (request.getParameter(REQFROM).equalsIgnoreCase("HomeTravelProductReports")) {

				String error = "";
				AdminPortBean ccBean = new AdminPortBean();
				ccBean.setDobDay(isNull(request.getParameter(DOBDAY)));
				ccBean.setDobMonth(isNull(request.getParameter(DOBMONTH)));
				ccBean.setDobYear(isNull(request.getParameter(DOBYEAR)));
				ccBean.setDobDay1(isNull(request.getParameter(DOBDAY1)));
				ccBean.setDobMonth1(isNull(request.getParameter(DOBMONTH1)));
				ccBean.setDobYear1(isNull(request.getParameter(DOBYEAR1)));
				ccBean.setData1(request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/"+ request.getParameter(DOBYEAR));
				ccBean.setData2(request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/"+ request.getParameter(DOBYEAR1));
				//String data1 = (request.getParameter(DOBDAY) + "-"+ request.getParameter(DOBMONTH) + "-" + request.getParameter(DOBYEAR));
				//String data2 = (request.getParameter(DOBDAY1) + "-"+ request.getParameter(DOBMONTH1) + "-" + request.getParameter(DOBYEAR1));

				String productId = "";
				productId = request.getParameter(PID) == null ? "" : request.getParameter(PID);
				if ("".equals(productId)){
					productId = (String) session.getAttribute("product_id");
				}

				error = ccBean.validateTrashFields();

				if (error.length() > 0) {
					request.setAttribute("errorDetail", error);
					reqDis = request.getRequestDispatcher("../admin/HomeFinalReports.jsp");
				}
				else{
					//String rep1 = isNull(request.getParameter(REP)) ;
					request.setAttribute("data1", (request.getParameter(DOBDAY) + "/"+ request.getParameter(DOBMONTH) + "/" + request.getParameter(DOBYEAR)));
					request.setAttribute("data2", (request.getParameter(DOBDAY1) + "/"+ request.getParameter(DOBMONTH1) + "/" + request.getParameter(DOBYEAR1)));
					request.setAttribute("rep1", isNull(request.getParameter(REP)));
					request.setAttribute(PID, productId);
					session.setAttribute("pid", productId);
					reqDis = request.getRequestDispatcher("../HomeInsurance/finalreportHome1.jsp");
				}

				if (reqDis != null) {
					reqDis.forward(request, response);
				}
		}
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