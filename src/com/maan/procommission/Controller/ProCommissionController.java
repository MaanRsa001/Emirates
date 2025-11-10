package com.maan.procommission.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class ProCommissionController extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(ProCommissionController.class);
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	final static transient private String ENTER = "- Enter";
	
	public void doPost(final HttpServletRequest request,final HttpServletResponse response)throws ServletException, IOException
	{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}
	
	public void doGet(final HttpServletRequest request,final  HttpServletResponse response)throws ServletException, IOException
	{
		try{
			processResult(request, response);
		} catch(BaseException e) {
			logger.debug(e);
		}
	}
	
	public void processResult(final HttpServletRequest request,final HttpServletResponse res)throws ServletException, IOException, BaseException 
	{
		try
		{
			logger.info("processResult ProCommissionController Controller ");
			logger.debug(ENTER);
			String error = "";
			com.maan.procommission.DAO.ProCommissionBean proCom = null; proCom = new com.maan.procommission.DAO.ProCommissionBean();
			HttpSession session = request.getSession(true);
			out = res.getWriter();
			String loginPersonId = (String) session.getAttribute("loginPersonId");
			RequestDispatcher dispatcher1 = null; 
			dispatcher1 = request.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
	
			if (dispatcher1 != null){
				dispatcher1.include(request, res);
			}
			if (loginPersonId == null){
				loginPersonId = (String) session.getAttribute("user");
			}
			String admBranch = "";
			admBranch = (String)session.getAttribute("LoginBranchCode");
			admBranch = admBranch == null ? "" : admBranch;
			if(admBranch.indexOf("'")!=-1){
				admBranch = admBranch.replaceAll("'","");
			}
			if (request.getParameter("requestfrom").equalsIgnoreCase("OCProCommissionInputs")) 
			{
				String brokerIds = request.getParameter("brokerId") == null ? "": request.getParameter("brokerId");
				String temp = request.getParameter("CountNos") == null ? "0": request.getParameter("CountNos");
				int CountNos = Integer.parseInt(temp);
				//String pid = request.getParameter("productId") == null ? "": request.getParameter("productId");
				int countSelBro = 0;
				for(int i=0;i<CountNos;i++)
				{
					String proCommission = request.getParameter("proCommission"+i) == null ? "": request.getParameter("proCommission"+i);
					proCommission = proCommission.trim();
					String selBro = request.getParameter("selBro"+i) == null ? "": request.getParameter("selBro"+i);
					if(selBro.equalsIgnoreCase("Yes"))
					{	
						String dobDay = request.getParameter("dobDay"+i) == null ? "0": request.getParameter("dobDay"+i);
						String dobMonth = request.getParameter("dobMonth"+i) == null ? "0": request.getParameter("dobMonth"+i);
						String dobYear = request.getParameter("dobYear"+i) == null ? "0": request.getParameter("dobYear"+i);
						String dobDay1 = request.getParameter("dobDay1"+i) == null ? "0": request.getParameter("dobDay1"+i);
						String dobMonth1 = request.getParameter("dobMonth1"+i) == null ? "0" : request.getParameter("dobMonth1"+i);
						String dobYear1 = request.getParameter("dobYear1"+i) == null ? "0" : request.getParameter("dobYear1"+i);
						String dobDay2 = request.getParameter("dobDay2"+i) == null ? "0": request.getParameter("dobDay2"+i);
						String dobMonth2 = request.getParameter("dobMonth2"+i) == null ? "0": request.getParameter("dobMonth2"+i);
						String dobYear2 = request.getParameter("dobYear2"+i) == null ? "0": request.getParameter("dobYear2"+i);
						String cdate = "";
						cdate = dobDay2 + "-" + dobMonth2 + "-" + dobYear2;
						String sdate = dobDay + "-" + dobMonth + "-" + dobYear;
						String edate = dobDay1 + "-" + dobMonth1 + "-" + dobYear1;
						
						String openCoverNo = request.getParameter("openCoverNo"+i) == null ? "": request.getParameter("openCoverNo"+i);
						String proStaus = request.getParameter("proStaus"+i) == null ? "": request.getParameter("proStaus"+i);
						proCom.setDobDay(dobDay);
						proCom.setDobMonth(dobMonth);
						proCom.setDobYear(dobYear);
						proCom.setDobDay1(dobDay1);
						proCom.setDobMonth1(dobMonth1);
						proCom.setDobYear1(dobYear1);
						proCom.setDobDay2(dobDay2);
						proCom.setDobMonth2(dobMonth2);
						proCom.setDobYear2(dobYear2);
						proCom.setData1(sdate);
						proCom.setData2(edate);
						proCom.setData3(cdate);
						proCom.setProCom(proCommission);
						proCom.setProStaus(proStaus);
						proCom.setbrokerIds(brokerIds);
						proCom.setOpenCoverNo(openCoverNo);
						proCom.setPid("11");
						proCom.setBranch(admBranch);
						
						error = error+proCom.dateValidation(""+(i+1));
						error = error.replaceAll("end","Expiry");
						error = error.replaceAll("End","Expiry");
					}
					else
					{
						countSelBro++;
					}
				}
				if(countSelBro == CountNos){
					error=error+"<br>* Please select any one MOC to do further";
				}
				if (error.length() > 0) 
				{
					error = "<font color=red>"+error+"</font>";
					request.setAttribute("errorDetail", error);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Open_Cover_PC_Inputs.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, res);
					}
				}
				else 
				{	
					for(int i=0;i<CountNos;i++)
					{
						String proCommission = request.getParameter("proCommission"+i) == null ? "": request.getParameter("proCommission"+i);
						proCommission = proCommission.trim();
						String selBro = request.getParameter("selBro"+i) == null ? "": request.getParameter("selBro"+i);
						if(selBro.equalsIgnoreCase("Yes"))
						{
							String dobDay = request.getParameter("dobDay"+i) == null ? "0": request.getParameter("dobDay"+i);
							String dobMonth = request.getParameter("dobMonth"+i) == null ? "0": request.getParameter("dobMonth"+i);
							String dobYear = request.getParameter("dobYear"+i) == null ? "0": request.getParameter("dobYear"+i);
							String dobDay1 = request.getParameter("dobDay1"+i) == null ? "0": request.getParameter("dobDay1"+i);
							String dobMonth1 = request.getParameter("dobMonth1"+i) == null ? "0" : request.getParameter("dobMonth1"+i);
							String dobYear1 = request.getParameter("dobYear1"+i) == null ? "0" : request.getParameter("dobYear1"+i);
							String dobDay2 = request.getParameter("dobDay2"+i) == null ? "0": request.getParameter("dobDay2"+i);
							String dobMonth2 = request.getParameter("dobMonth2"+i) == null ? "0": request.getParameter("dobMonth2"+i);
							String dobYear2 = request.getParameter("dobYear2"+i) == null ? "0": request.getParameter("dobYear2"+i);
							String cdate = "";
							if(!dobDay2.equals("0")&&!dobMonth2.equals("0")&&!dobYear2.equals("0")){
								cdate = dobDay2 + "-" + dobMonth2 + "-" + dobYear2;
							}
							String sdate = dobDay + "-" + dobMonth + "-" + dobYear;
							String edate = dobDay1 + "-" + dobMonth1 + "-" + dobYear1;
							String openCoverNo = request.getParameter("openCoverNo"+i) == null ? "": request.getParameter("openCoverNo"+i);
							String proStaus = request.getParameter("proStaus"+i) == null ? "": request.getParameter("proStaus"+i);
							
							HashMap proHash = new HashMap();
							proHash.put("sdate",sdate);
							proHash.put("edate",edate);
							proHash.put("cdate",cdate);
							proHash.put("openCoverNo",openCoverNo);
							proHash.put("proCommission",proCommission);
							proHash.put("proStaus",proStaus);
							proHash.put("admBranch",admBranch);
							proHash.put("brokerIds",brokerIds);
							proHash.put("pid","11");
							proCom.insertProCommissionDetails(proHash);
							
						}
					}
						RequestDispatcher dispatcher = request.getRequestDispatcher("PromotionConfirmation.jsp");
						if (dispatcher != null){
							dispatcher.forward(request, res);
						}
				}
			}
			else if (request.getParameter("requestfrom").equalsIgnoreCase("OFProCommissionInputs")) 
			{
				String temp = request.getParameter("CountNos") == null ? "0": request.getParameter("CountNos");
				int CountNos = Integer.parseInt(temp);
				String pid = request.getParameter("productId") == null ? "": request.getParameter("productId");
				int countSelBro = 0;
				for(int i=0;i<CountNos;i++)
				{
					String proCommission = request.getParameter("proCommission"+i) == null ? "": request.getParameter("proCommission"+i);
					proCommission = proCommission.trim();
					String selBro = request.getParameter("selBro"+i) == null ? "": request.getParameter("selBro"+i);
					
					if(selBro.equalsIgnoreCase("Yes"))
					{
						String dobDay = request.getParameter("dobDay"+i) == null ? "0": request.getParameter("dobDay"+i);
						String dobMonth = request.getParameter("dobMonth"+i) == null ? "0": request.getParameter("dobMonth"+i);
						String dobYear = request.getParameter("dobYear"+i) == null ? "0": request.getParameter("dobYear"+i);
						
						String dobDay1 = request.getParameter("dobDay1"+i) == null ? "0": request.getParameter("dobDay1"+i);
						String dobMonth1 = request.getParameter("dobMonth1"+i) == null ? "0" : request.getParameter("dobMonth1"+i);
						String dobYear1 = request.getParameter("dobYear1"+i) == null ? "0" : request.getParameter("dobYear1"+i);
						
						String dobDay2 = request.getParameter("dobDay2"+i) == null ? "0": request.getParameter("dobDay2"+i);
						String dobMonth2 = request.getParameter("dobMonth2"+i) == null ? "0": request.getParameter("dobMonth2"+i);
						String dobYear2 = request.getParameter("dobYear2"+i) == null ? "0": request.getParameter("dobYear2"+i);
	
						String sdate = dobDay + "-" + dobMonth + "-" + dobYear;
						String edate = dobDay1 + "-" + dobMonth1 + "-" + dobYear1;
						String cdate = "";
						//if(!dobDay2.equals("0")&&!dobMonth2.equals("0")&&!dobDay2.equals("0"))
						cdate = dobDay2 + "-" + dobMonth2 + "-" + dobYear2;
						
						String agencyCode = request.getParameter("agencyCode"+i) == null ? "": request.getParameter("agencyCode"+i);
						String proStaus = request.getParameter("proStaus"+i) == null ? "": request.getParameter("proStaus"+i);
	
						proCom.setDobDay(dobDay);
						proCom.setDobMonth(dobMonth);
						proCom.setDobYear(dobYear);
	
						proCom.setDobDay1(dobDay1);
						proCom.setDobMonth1(dobMonth1);
						proCom.setDobYear1(dobYear1);
						
						proCom.setDobDay2(dobDay2);
						proCom.setDobMonth2(dobMonth2);
						proCom.setDobYear2(dobYear2);
	
						proCom.setData1(sdate);
						proCom.setData2(edate);
						proCom.setData3(cdate);
						
						proCom.setProCom(proCommission);
						proCom.setProStaus(proStaus);
	
						proCom.setbrokerIds(agencyCode);
						proCom.setOpenCoverNo("0");
						proCom.setPid("3");
						proCom.setBranch(admBranch);
	
						error = error+proCom.dateValidation(""+(i+1));
						error = error.replaceAll("end","Expiry");
						error = error.replaceAll("End","Expiry");
					}
					else
					{
						countSelBro++;
					}
				}
				if(countSelBro == CountNos){
					error=error+"<br>* Please select any one Broker to do further";
				}
				if (error.length() > 0) 
				{
					error = "<font color=red>"+error+"</font>";
					request.setAttribute("errorDetail", error);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Oneoff_PC_Inputs.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, res);
					}
				}
				else 
				{	
					for(int i=0;i<CountNos;i++)
					{
						String proCommission = request.getParameter("proCommission"+i) == null ? "": request.getParameter("proCommission"+i);
						proCommission = proCommission.trim();
						String selBro = request.getParameter("selBro"+i) == null ? "": request.getParameter("selBro"+i);
						if(selBro.equalsIgnoreCase("Yes"))
						{
							String dobDay = request.getParameter("dobDay"+i) == null ? "0": request.getParameter("dobDay"+i);
							String dobMonth = request.getParameter("dobMonth"+i) == null ? "0": request.getParameter("dobMonth"+i);
							String dobYear = request.getParameter("dobYear"+i) == null ? "0": request.getParameter("dobYear"+i);
							String dobDay1 = request.getParameter("dobDay1"+i) == null ? "0": request.getParameter("dobDay1"+i);
							String dobMonth1 = request.getParameter("dobMonth1"+i) == null ? "0" : request.getParameter("dobMonth1"+i);
							String dobYear1 = request.getParameter("dobYear1"+i) == null ? "0" : request.getParameter("dobYear1"+i);
							String dobDay2 = request.getParameter("dobDay2"+i) == null ? "0": request.getParameter("dobDay2"+i);
							String dobMonth2 = request.getParameter("dobMonth2"+i) == null ? "0": request.getParameter("dobMonth2"+i);
							String dobYear2 = request.getParameter("dobYear2"+i) == null ? "0": request.getParameter("dobYear2"+i);
							String cdate = "";
							if(!dobDay2.equals("0")&&!dobMonth2.equals("0")&&!dobYear2.equals("0")){
								cdate = dobDay2 + "-" + dobMonth2 + "-" + dobYear2;
							}
							String sdate = dobDay + "-" + dobMonth + "-" + dobYear;
							String edate = dobDay1 + "-" + dobMonth1 + "-" + dobYear1;
							String agencyCode = request.getParameter("agencyCode"+i) == null ? "": request.getParameter("agencyCode"+i);
							String proStaus = request.getParameter("proStaus"+i) == null ? "": request.getParameter("proStaus"+i);
								
							Map proHash = null; proHash = new HashMap();
							proHash.put("sdate",sdate);
							proHash.put("edate",edate);
							proHash.put("cdate",cdate);
							proHash.put("openCoverNo","0");
							proHash.put("proCommission",proCommission);
							proHash.put("proStaus",proStaus);
							proHash.put("admBranch",admBranch);
							proHash.put("agencyCode",agencyCode);
							proHash.put("pid",pid);
							proCom.insertProCommissionDetailsOthers(proHash);
						}
					}
					RequestDispatcher dispatcher = request.getRequestDispatcher("PromotionConfirmationOneOff.jsp");
					if (dispatcher != null){
						dispatcher.forward(request, res);
					}
				}
			}
		}
		catch(BaseException e){
			throw new BaseException (e,"Error");
		}
	}
} // Class