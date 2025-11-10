package com.maan.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;
import com.maan.admin.AdminBean;
import com.maan.admin.CommodityCountryRateDAO;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.services.util.runner;

public class CommodityCountryRateController extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(CommodityCountryRateController.class);

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (Exception e) {
			logger.debug(e);
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BaseException 
	{
		logger.info("CommodityCountryRateController - Enter || display: "+request.getParameter("display"));
		CommodityCountryRateDAO bean=new CommodityCountryRateDAO();
		PremiumInputsBean premium=new PremiumInputsBean();
		HttpSession session=request.getSession(false);
		PrintWriter out=response.getWriter();
		if(session==null)
		{
			response.sendRedirect("login/error_messg.jsp");
			return;
		}
		RequestDispatcher dispatcher = null;
		String error="";
		String user = (String)session.getAttribute("user")==null?"":(String)session.getAttribute("user");
		String adminBranch = new AdminBean().getAdminBranch(user);
		String display = request.getParameter("display") == null ? "" : request.getParameter("display");
		String brokerId=request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
		String productId=request.getParameter("productId")==null?"":request.getParameter("productId");
		String modeOfTransport=request.getParameter("modeOfTransport")==null?"":request.getParameter("modeOfTransport");
		String coverId=request.getParameter("coverId")==null?"":request.getParameter("coverId");
		String commodityId=request.getParameter("commodityId")==null?"":request.getParameter("commodityId");
		String originCountryId=request.getParameter("originCountryId")==null?"":request.getParameter("originCountryId");
		String destCountryId=request.getParameter("destCountryId")==null?"":request.getParameter("destCountryId");
		String rate=request.getParameter("rate")==null?"":request.getParameter("rate");
		String clauses=request.getParameter("clauses")==null?"":request.getParameter("clauses");
		String warranty=request.getParameter("warranty")==null?"":request.getParameter("warranty");
		String status=request.getParameter("status")==null?"":request.getParameter("status");
		String effectiveDate=request.getParameter("effectiveDate")==null?"":request.getParameter("effectiveDate");
		String openCoverNo=request.getParameter("openCoverNo")==null?"":request.getParameter("openCoverNo");
		
		if ("".equalsIgnoreCase(display)) {
			request.setAttribute("brokerList", bean.getBrokerList(adminBranch));
			if(!"".equals(brokerId))
				request.setAttribute("openCoverList", bean.getOpenCoverList(brokerId));
			request.setAttribute("display", "");
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		}else if ("rateList".equalsIgnoreCase(display)) {
			if(brokerId.equalsIgnoreCase(""))
				error+="Please Select Broker <br/>";
			if(productId.equalsIgnoreCase(""))
				error+="Please Select Product <br/>";
			if(error.length()<=0)
			{
				request.setAttribute("rateList", bean.getCommodityCountryRateList(brokerId, productId, adminBranch, openCoverNo));
				request.setAttribute("display", display);
			}else
			{
				request.setAttribute("brokerList", bean.getBrokerList(adminBranch));
				request.setAttribute("display", "");
				request.setAttribute("error", error);
			}
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		}else if ("brokerAndProduct".equalsIgnoreCase(display)) {
			request.setAttribute("brokerList", bean.getBrokerList(adminBranch));
			request.setAttribute("display", "");
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		} else if ("addRate".equalsIgnoreCase(display)) {
			premium.setProductId(productId);
			request.setAttribute("modeList",bean.getModeList(adminBranch, productId)); 
			request.setAttribute("commodityList",bean.getCommodityList(adminBranch, productId));
			request.setAttribute("countryList",bean.getCountryList(adminBranch));
			request.setAttribute("display", display);
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		}else if("warranty".equalsIgnoreCase(display))
		{
			request.setAttribute("list", bean.getWarrantyList(adminBranch));
			dispatcher = request.getRequestDispatcher("/admin/warrantyClausesList.jsp");
		}else if("clauses".equalsIgnoreCase(display))
		{
			request.setAttribute("list", bean.getClausesList(adminBranch,coverId));
			dispatcher = request.getRequestDispatcher("/admin/warrantyClausesList.jsp");
		}else if("coverList".equalsIgnoreCase(display))
		{
			String [][] coverList=new String[0][0];
			if(!modeOfTransport.equals(""))
			coverList=bean.getCoverList(adminBranch, productId, modeOfTransport);
			out.print("&nbsp;&nbsp;<select name=\"coverId\" style=\"width:200px\" >");
			out.print("<option value=''>--select--</option>");
			if(coverList != null && coverList.length>0)
			{
				for(int i=0; i<coverList.length; i++)
				{
					out.print("<option value='"+coverList[i][0]+"'>"+coverList[i][1]+"</option>");
				}
			}
			out.print("</select>");
		}else if("addRateInfo".equalsIgnoreCase(display) || "editRateInfo".equalsIgnoreCase(display))
		{
			error="";
			if(modeOfTransport.equals(""))
				error+="Please Select Mode of Transport<br/>";
			if(coverId.equals(""))
				error+="Please Select Cover<br/>";
			if(commodityId.equals(""))
				error+="Please Select Commodity<br/>";
			if(originCountryId.equals(""))
				error+="Please Select Origin Coutry<br/>";
			if(destCountryId.equals(""))
				error+="Please Select Destination Country<br/>";
			if(rate.equals(""))
				error+="Please Enter Rate<br/>";
			if(clauses.equals(""))
				error+="Please Select Clause<br/>";
			if(warranty.equals(""))
				error+="Please Select Warranty<br/>";
			if(status.equals(""))
				error+="Please Select Status<br/>";
			if(effectiveDate.equals(""))
				error+="Please Select Effective Date<br/>";
			else
			{
				long diff=0;
				try
				{
					String sysDate = runner.singleSelection("SELECT TO_CHAR(SYSDATE+8/24,'dd-MM-YYYY') FROM DUAL");
					DateFunction dbr = new DateFunction();
					if (sysDate.length() > 0) 
						diff = dbr.getDayDifference(dbr.getCalendar(sysDate), dbr.getCalendar(effectiveDate));
				}catch (Exception e) {e.printStackTrace();}
				if (diff != 0)
					error+="Please Select Valid Effective Date<br/>";
			}
			if(error.equals(""))
				error=bean.addCommodityCountryRate(display, brokerId, productId, modeOfTransport, coverId, commodityId, originCountryId, destCountryId, rate, clauses, warranty, status, effectiveDate, openCoverNo);
			if(error.length()>0)
			{
				request.setAttribute("modeList",bean.getModeList(adminBranch, productId)); 
				if(!modeOfTransport.equals(""))
				request.setAttribute("coverList",bean.getCoverList(adminBranch, productId, modeOfTransport));
				request.setAttribute("commodityList",bean.getCommodityList(adminBranch, productId));
				request.setAttribute("countryList",bean.getCountryList(adminBranch));
				request.setAttribute("display", display.replaceAll("Info", ""));
				request.setAttribute("error", error);
			}else
			{
				request.setAttribute("rateList", bean.getCommodityCountryRateList(brokerId, productId, adminBranch, openCoverNo));
				request.setAttribute("display", "rateList");
			}
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		}else if("editRate".equalsIgnoreCase(display))
		{
			request.setAttribute("modeList",bean.getModeList(adminBranch, productId)); 
			if(!modeOfTransport.equals(""))
			request.setAttribute("coverList",bean.getCoverList(adminBranch, productId, modeOfTransport));
			request.setAttribute("commodityList",bean.getCommodityList(adminBranch, productId));
			request.setAttribute("countryList",bean.getCountryList(adminBranch));
			request.setAttribute("rateInfo", bean.getRateInfo(brokerId, productId, modeOfTransport, coverId, commodityId, originCountryId, destCountryId, openCoverNo));
			request.setAttribute("display", "editRate");
			dispatcher = request.getRequestDispatcher("/admin/CommodityCountryRateSetup.jsp");
		}else if("openCoverList".equalsIgnoreCase(display))
		{
			String [][] openCoverList=bean.getOpenCoverList(brokerId);
			out.print("&nbsp;&nbsp;<select name=\"openCoverNo\" style=\"width:200px\" >");
			out.print("<option value=''>--select--</option>");
			if(openCoverList != null && openCoverList.length>0)
			{
				for(int i=0; i<openCoverList.length; i++)
				{
					out.print("<option value='"+openCoverList[i][0]+"'>"+openCoverList[i][0]+"</option>");
				}
			}
			out.print("</select>");
		}
		if (dispatcher != null)
			dispatcher.forward(request, response);
		logger.info("CommodityCountryRateController - Exit");
	}
}
