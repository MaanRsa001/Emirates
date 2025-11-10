package com.maan.premium.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.services.util.ValidationFormat;
import com.maan.premium.DAO.CommodityTransaction;

public class CommodityController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
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
		StringBuffer error=new StringBuffer();
		HttpSession session=null;
		
		RequestDispatcher dispatcher;
		ValidationFormat validate = new ValidationFormat();
		com.maan.premium.DAO.CommodityTransaction cT=new CommodityTransaction(); 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		session=request.getSession(false);
		if(session.getAttribute("ses")==null){
			response.sendRedirect("../login/error_messg_pdf.jsp");	
			return;
		}
		String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
		System.out.println("RoyalTest for databese mode checking in CommodityController.."+usrModeController);
		if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController))
		{
		        response.sendRedirect("../login/error_messg_pdf.jsp");	
		        return;
		}
		String reqFrom = request.getParameter("reqFrom")==null?"CommodityInsert":request.getParameter("reqFrom");

		String path=request.getRequestURI();
		path=path.substring(0,path.lastIndexOf("/")+1);		
		out.println("path--->"+path);
		if(reqFrom.equalsIgnoreCase("CommodityInsert"))
		{
			int count=0;
			error=new StringBuffer();
			count=Integer.parseInt(request.getParameter("commoditySize")==null?"0":request.getParameter("commoditySize"));
			out.println("the Size"+count);
			request.removeAttribute("fullDetails");
			
			HashMap commodity		=	new HashMap();
			HashMap sumInsured		=	new HashMap();
			HashMap description		=	new HashMap();
			HashMap fragile			=	new HashMap();
			HashMap fullDetails		=	new HashMap();
			String comm				=	"";
			String commStatus		=	"";
			String si				=	"0";
			String des				=	"No Description Provided";
			String fr				=	"";
			int finalCount			=	1;
			int errorAllCount		=	0;
			String productId		=	"";
			String loginId			=	"";
			String companyId		=	"";
			String sessionId		=	"";
			String applicationNoEDIT=	"0";
			String commoditySummary	=	"";
			//For Open Cover Policy on Aug 14 2007 by karthy
			String openCoverNo		=	"";
			String commodityIdOrg	=	"";
			String commTypeId	=	"";
			String commodityExcessPremium	=	"";
			boolean flag=false;
			productId		 =request.getParameter("productId")==null?"0":request.getParameter("productId");
			openCoverNo		 =request.getParameter("openCoverNo")==null?"0":request.getParameter("openCoverNo");
			loginId			 =request.getParameter("loginId")==null?"":request.getParameter("loginId");
			companyId		 =request.getParameter("companyId")==null?"0":request.getParameter("companyId");
			sessionId		 =request.getParameter("sessionId")==null?"":request.getParameter("sessionId");
			applicationNoEDIT=request.getParameter("applicationNoEDIT")==null?"0":request.getParameter("applicationNoEDIT");
			
			if("".equalsIgnoreCase(applicationNoEDIT))
			{
				applicationNoEDIT="0";
			}
			commoditySummary=request.getParameter("commoditySummary")==null?"NOSUMMARY":request.getParameter("commoditySummary");
			for(int i=0;i<count;i++)
			{
					comm=request.getParameter("commodity"+(i+1))==null?comm:request.getParameter("commodity"+(i+1));
					out.println("the comm is "+comm);
					si=request.getParameter("sumInsured"+(i+1))==null?si:request.getParameter("sumInsured"+(i+1));		
					des=request.getParameter("description"+(i+1))==null?des:request.getParameter("description"+(i+1));
					fr=request.getParameter("fragile"+(i+1))==null?fr:request.getParameter("fragile"+(i+1));		
					commTypeId = request.getParameter("commodityTypeId"+(i+1)) == null ? commTypeId : request.getParameter("commodityTypeId"+(i+1));
					commodityExcessPremium = request.getParameter("commodityExcessPremium"+(i+1)) == null ? commodityExcessPremium : request.getParameter("commodityExcessPremium"+(i+1));

					if("".equals(comm)){
						comm="off";	
					}
					
					if(si.length() >0)
					{
						flag = validate.IsNegativeValidationFormat(si);
						if(flag)
							error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("87","Commodity Sum Insured")+" <i> "+comm+" </i></b></font>");	
					}

					if("".equals(si)){
						si="0";				
					}
					//if("".equals(des))
					if("".equals(des) || " ".equals(des) || "".equals(des.trim()) ){
						des="No Description Provided";				
					}
					if("".equals(fr)){
						fr="off";	
						//	out.println("<br>INSIDE OFF Fragile"+(i+1)+" Status AFTER CONVERSION---"+fr);
					}
					if(comm.length()>0 && !("off".equalsIgnoreCase(comm))){
							commStatus="on";
							if("0".equalsIgnoreCase(si))
							{
								//Newly Added
								error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("87","Commodity Sum Insured")+" <i> "+comm+" </i></b></font>");
							}
							else{
								try{
									Double.parseDouble(si);
								}
								catch(Exception exception)
								{						
									error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("87","Commodity Sum Insured")+" <i> "+comm+" </i></b></font>");	
								}
								if(error.length() == 0 && Double.parseDouble(si) == 0.0)
								{	
									error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("87","Commodity Sum Insured")+" <i> "+comm+" </i></b></font>");
								}
							}
							if("No Description Provided".equalsIgnoreCase(des))
							{
								error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("88","Commodity Description")+" <i> "+comm+" </i></b></font>");
							}
					}
					if("off".equalsIgnoreCase(comm)){
						comm="";
						commStatus="";
						errorAllCount=errorAllCount+1;
					}
					else
					{
						commodityIdOrg=request.getParameter("commodityIdOrg"+(i+1))==null?commodityIdOrg:request.getParameter("commodityIdOrg"+(i+1));
						fullDetails.put("commodityId"+(finalCount),commodityIdOrg);	
						fullDetails.put("commodity"+(finalCount),comm);
						fullDetails.put("sumInsured"+(finalCount),si);
						fullDetails.put("description"+(finalCount),des);
						fullDetails.put("fragile"+(finalCount),fr);
						fullDetails.put("commodityTypeId"+(commodityIdOrg),commTypeId);  // CommodityTypeId
						fullDetails.put("commodityExcessPremium"+(commodityIdOrg),commodityExcessPremium);  // CommodityExcessPremium
						finalCount=finalCount+1;
						comm="";
						commStatus="";
						fr="";
					}
			}//End of For Loop
			if(errorAllCount==count)
			{		
					error.append("<br><font color=red size=2><b>*"+cT.getErrormsg("95","Commodity")+"<i></i></b></font>");
					errorAllCount=0;
			}
			if(error.length()>0)
			{
				request.setAttribute("errorMessageCommodity",error);
				dispatcher=request.getRequestDispatcher("/premium/Commodity.jsp");
				dispatcher.forward(request, response);
			}
			else{
				finalCount=finalCount-1;
				fullDetails.put("finalCount",""+finalCount);
				if(commoditySummary.equalsIgnoreCase("NOSUMMARY"))
				{
						cT.setSessionId(sessionId);
						cT.setLoginCode(loginId);
						cT.setCompanyId(companyId);
						cT.setProductId(productId);
						cT.setOpenCoverNo(openCoverNo);
						cT.setStageId("2");
						if("admin".equalsIgnoreCase((String)session.getAttribute("user1")))
							cT.setAdminStatus("admin");
						else
							cT.setAdminStatus("user");
						if(Integer.parseInt(applicationNoEDIT)>0)
						{
							
							request.setAttribute("applicationNo",applicationNoEDIT);
							
							cT.setApplicationNo(applicationNoEDIT);
							cT.insertUpdateCommodity(fullDetails);
							request.setAttribute("fullDetails",fullDetails);
							dispatcher=request.getRequestDispatcher("/premium/Commodity1.jsp");								
						}
						else
						{	
								int appNo=0;
								appNo = cT.getMaximumApplicationNo((String) session.getAttribute("product_id"),
										(String)session.getAttribute("AdminBranchCode"));
								System.out.println("NEW MODE COMMODITY application no is..."+appNo);
								cT.setApplicationNo(""+appNo);
								request.setAttribute("applicationNo",""+appNo);
								cT.insertUpdateCommodity(fullDetails);
								request.setAttribute("fullDetails",fullDetails);
								dispatcher=request.getRequestDispatcher("/premium/Commodity1.jsp");
						}
						dispatcher.forward(request, response);
				}
			}
		}
		else if(reqFrom.equalsIgnoreCase("CommodityDelete"))
		{
			String delAppNo = request.getParameter("delAppNo")==null?"0":request.getParameter("delAppNo");
			boolean flag = cT.deleteApplicationNo(delAppNo);
			if(flag)
			{
				if(session.getAttribute("applicationNo")!=null)
					session.removeAttribute("applicationNo");
				if(session.getAttribute("fullDetailss")!=null)
					session.removeAttribute("fullDetailss");
			}
			dispatcher=request.getRequestDispatcher("/premium/Commodity.jsp");
			dispatcher.forward(request, response);
		}
	}
	public void doPut(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException 
	{
           doPost(request,response);
	}

	public String getServletInfo()
	{
		return "This is my default servlet created by Eclipse";
	}

	public void init() throws ServletException {
	}
}
