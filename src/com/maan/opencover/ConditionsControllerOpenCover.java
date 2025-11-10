package com.maan.opencover;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.common.util.dataCollection;
import com.maan.services.util.runner;

public class ConditionsControllerOpenCover extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private StringBuffer error=new StringBuffer();
	 HttpSession session=null;
	 private RequestDispatcher dispatcher;

	public ConditionsControllerOpenCover() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
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
		if((String)session.getAttribute("product_id")==null || !"11".equalsIgnoreCase((String)session.getAttribute("product_id")))
		{
			response.sendRedirect("../login/error_messg.jsp");
			return;
		}
		try
		{
			String usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			if("".equalsIgnoreCase(usrModeController) || " ".equalsIgnoreCase(usrModeController) )
			{
				response.sendRedirect("../login/error_messg.jsp");
				return;
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		try {
		String path=request.getRequestURI();
		path = path.substring(0,path.lastIndexOf("/")+1);

		int count=0;
		String dateError="";
		String effectDate="";
		error=new StringBuffer();
		request.removeAttribute("fullDetails");
		HashMap fullDetails=new HashMap();
		String comm="";
		String des="No Description Provided";
		int finalCount=1;
		int errorAllCount=0;
		String productId="";
		String loginId="";
		String companyId="";
		String sessionId="";
		String applicationNoEDIT="0";
		String warrantyNoEDIT = "0";
		String exclusionNoEDIT = "0";
		String textNoEDIT = "0";
		String condNoEDIT = "0";
		String wsrccNoEDIT = "0";
		String openCoverNo = "";
		String clausesSummary="";
		String proposalNo="";
		String coverNo="";
		int status = 0;
		
		dataCollection data = new dataCollection();

		String clausesIdOrg="";
		String coverId="";

		productId=request.getParameter("productId")==null?"0":request.getParameter("productId");
		openCoverNo=request.getParameter("openCoverNo")==null?"0":request.getParameter("openCoverNo");
		loginId=request.getParameter("loginId")==null?"":request.getParameter("loginId");
		companyId=request.getParameter("companyId")==null?"0":request.getParameter("companyId");
		sessionId=request.getParameter("sessionId")==null?"":request.getParameter("sessionId");
		applicationNoEDIT=request.getParameter("applicationNoEDIT")==null?"0":request.getParameter("applicationNoEDIT");
		warrantyNoEDIT = request.getParameter("warrantyNoEDIT") == null?"0":request.getParameter("warrantyNoEDIT");
		condNoEDIT = request.getParameter("conditionOpen")==null?"0":request.getParameter("conditionOpen");
		exclusionNoEDIT =request.getParameter("exclusionNoEDIT")==null?"0":request.getParameter("exclusionNoEDIT");
		textNoEDIT=request.getParameter("textNoEDIT")==null?"0":request.getParameter("textNoEDIT");
		proposalNo=request.getParameter("proposalNo")==null?(String)session.getAttribute("proposalNo"):request.getParameter("proposalNo");
		coverNo=request.getParameter("coverNo")==null?(String)session.getAttribute("coverNo"):request.getParameter("coverNo");
		wsrccNoEDIT = request.getParameter("wsrccNoEDIT")==null?"0":request.getParameter("wsrccNoEDIT");
		count=Integer.parseInt(request.getParameter("clausesSize")==null?"0":request.getParameter("clausesSize"));
		effectDate=request.getParameter("effectDate")==null?"0":request.getParameter("effectDate");
		System.out.println("count " + count);
		if("".equalsIgnoreCase(applicationNoEDIT))
		{
			applicationNoEDIT="0";
		}
		if("".equalsIgnoreCase(warrantyNoEDIT))
		{
			warrantyNoEDIT="0";
		}
	    if("".equalsIgnoreCase(textNoEDIT))
        {
			textNoEDIT = "0";
		}
		if("".equalsIgnoreCase(wsrccNoEDIT))
	    {
			 wsrccNoEDIT = "0";
		}

		clausesSummary=request.getParameter("clausesSummary")==null?"NOSUMMARY":request.getParameter("clausesSummary");

		for(int i=0;i<count;i++)
		{
			comm=request.getParameter("clauses"+(i+1))==null?comm:request.getParameter("clauses"+(i+1));
			des=request.getParameter("description"+(i+1))==null?des:request.getParameter("description"+(i+1));
//			out.println("the description  "+des);
			if("".equals(comm))
			{
				comm="off";
			}
			if("".equals(des) && Integer.parseInt(textNoEDIT) !=9)
			{
				des="No Description Provided";
			}
			if("".equals(des) && Integer.parseInt(textNoEDIT) ==9)
			{
				des="No Clauses";
			}
			if(comm.length()>0 && !("off".equalsIgnoreCase(comm)))
			{
				if("No Description Provided".equalsIgnoreCase(des))
				{
					error.append("<br><font color=red size=2><b>*"+getErrormsg("135","Clauses Description")+" <i> "+(i+1)+" </i><br>This is the original clause for the above : <font color=blue size=2>"+comm+"</font></b></font>");
				}
			}

			if("off".equalsIgnoreCase(comm))
			{
				comm="";
				errorAllCount=errorAllCount+1;
			}
			else
			{
				clausesIdOrg=request.getParameter("clausesIdOrg"+(i+1));//==null?clausesIdOrg:
				coverId=request.getParameter("coverId"+(i+1));
				String clsize = request.getParameter("clausesSize");
				fullDetails.put("clausesId"+(finalCount),clausesIdOrg);
				
				if(coverId != null)
					fullDetails.put("coverId"+(finalCount),coverId);
			
				fullDetails.put("description"+(finalCount),des);
				finalCount=finalCount+1;
				comm="";
			}

		}//End of For Loop

		if(errorAllCount==count)
		{
			if(Integer.parseInt(textNoEDIT) !=9)  // for free text 
			{
				error.append("<br><font color=red size=2><b>*"+getErrormsg("164","clauses")+"<i></i></b></font>");
			}
			errorAllCount=0;
		}

		dateError = data.checkPickerDate(effectDate);
		if(Integer.parseInt(condNoEDIT) ==3)   // for clauses and conditions
		{
			status = 3;
			if("Invalid".equalsIgnoreCase(dateError))
			{
				error.append("<br><font color=red size=2><b>*"+getErrormsg("49","Effective Date")+"<i></i></b></font>");
			}
			if("Invalid".equalsIgnoreCase(getDateStatus(proposalNo,effectDate)))
			{
				error.append("<br><font color=red size=2><b>*"+"Enter valid Date"+"<i></i></b></font>");
			}
		}
		if(Integer.parseInt(warrantyNoEDIT) ==1)  // for warranties
		{
			status = 1;
			if("Invalid".equalsIgnoreCase(dateError))
			{
				error.append("<br><font color=red size=2><b>*"+getErrormsg("49","Effective Date")+"<i></i></b></font>");
			}
			if("Invalid".equalsIgnoreCase(getDateStatus(proposalNo,effectDate)))
			{
				error.append("<br><font color=red size=2><b>*"+"Enter valid Date"+"<i></i></b></font>");
			}
		}
		if(Integer.parseInt(exclusionNoEDIT) ==2)  // for exclusion
		{
			status = 2;
			if("Invalid".equalsIgnoreCase(dateError))
			{
				error.append("<br><font color=red size=2><b>*"+getErrormsg("49","Effective Date")+"<i></i></b></font>");
			}

			if("Invalid".equalsIgnoreCase(getDateStatus(proposalNo,effectDate)))
			{
				error.append("<br><font color=red size=2><b>*"+"Enter valid Date"+"<i></i></b></font>");
			}
		}

		if(Integer.parseInt(wsrccNoEDIT) ==7)  //for wsrcc
		{
			status = 7;
			if("Invalid".equalsIgnoreCase(dateError))
			{
				error.append("<br><font color=red size=2><b>*"+getErrormsg("49","Effective Date")+"<i></i></b></font>");
			}

			if("Invalid".equalsIgnoreCase(getDateStatus(proposalNo,effectDate)))
			{
				error.append("<br><font color=red size=2><b>*"+"Enter valid Date"+"<i></i></b></font>");
			}
		}
		if(error.length()>0)
		{
			if(Integer.parseInt(condNoEDIT)==3)
			{
				request.setAttribute("errorMessageClauses",error);
				dispatcher=request.getRequestDispatcher("/premiumOpenCover/conditionsOpenEdit.jsp");
				dispatcher.forward(request, response);
			}
			if(Integer.parseInt(warrantyNoEDIT)==1)
			{
				request.setAttribute("errorMessageClauses",error);
				dispatcher=request.getRequestDispatcher("/premiumOpenCover/warrantiesShow.jsp");
				dispatcher.forward(request, response);
			}
			if(Integer.parseInt(exclusionNoEDIT)==2)
			{
				request.setAttribute("errorMessageClauses",error);
				dispatcher=request.getRequestDispatcher("/premiumOpenCover/exclusionShow.jsp");
				dispatcher.forward(request, response);
			}
		    if(Integer.parseInt(wsrccNoEDIT) == 7)
		    {
			   request.setAttribute("errorMessageClauses", error);
			   dispatcher = request.getRequestDispatcher("/premiumOpenCover/WSRCC.jsp");
			   dispatcher.forward(request, response);
		    }
		    if(Integer.parseInt(textNoEDIT) == 9)
		    {
		    	request.setAttribute("errorMessageClauses", error);
		    	dispatcher = request.getRequestDispatcher("/premiumOpenCover/freetext.jsp");
		    	dispatcher.forward(request, response);
		    }
		}
		else
		{
			finalCount=finalCount-1;
			fullDetails.put("finalCount",""+finalCount);
			com.maan.opencover.ConditionsOpenCover cT=new  ConditionsOpenCover();
			if(clausesSummary.equalsIgnoreCase("NOSUMMARY"))
			{
				cT.setSessionId(sessionId);
				cT.setLoginCode(loginId);
				cT.setCompanyId(companyId);
				cT.setProductId(productId);
				cT.setProposalNo(proposalNo);
				cT.setModeOfTransport("0");
				cT.setCoverId(coverNo);
				cT.setExtraCoverId("0");
				cT.setOpenCoverNo(openCoverNo);
				cT.setEffectDate(effectDate);
				cT.setStageId("2");
				if((Integer.parseInt(exclusionNoEDIT) == 0) && (Integer.parseInt(warrantyNoEDIT) == 0) &&(Integer.parseInt(textNoEDIT) == 0) && (Integer.parseInt(wsrccNoEDIT) == 0))
				{
					if(Integer.parseInt(applicationNoEDIT)>0)  
					{
						System.out.println("EDIT MODE clauses");
						request.setAttribute("applicationNo",applicationNoEDIT);
						cT.setApplicationNo(applicationNoEDIT);
						cT.insertUpdateConditions(fullDetails);
						request.setAttribute("fullDetails",fullDetails);
						dispatcher=request.getRequestDispatcher("/premiumOpenCover/conditionsOpenEdit1.jsp");
					}
					else
					{
						cT.insertUpdateConditions(fullDetails);
						request.setAttribute("fullDetails",fullDetails);
						dispatcher=request.getRequestDispatcher("/premiumOpenCover/conditionsOpenEdit1.jsp");
					}
				}
				if(Integer.parseInt(exclusionNoEDIT)==2)
				{
					request.setAttribute("applicationNo",exclusionNoEDIT);
					cT.setApplicationNo(exclusionNoEDIT);
					cT.insertUpdateExclusions(fullDetails);
					request.setAttribute("fullDetails",fullDetails);
					dispatcher=request.getRequestDispatcher("/premiumOpenCover/exclusionShow1.jsp");
				}
				if(Integer.parseInt(textNoEDIT) == 9 )
				{
				  cT.setApplicationNo(exclusionNoEDIT);
				  cT.insertUpdateFreeText(fullDetails);
				  request.setAttribute("fullDetails",fullDetails);
				  dispatcher=request.getRequestDispatcher("/premiumOpenCover/freetext1.jsp");
			    }

				if(Integer.parseInt(wsrccNoEDIT) == 7)
				{
					String sea = request.getParameter("transportSea");
					String air = request.getParameter("transportAir");
					String multimode = request.getParameter("transportMultimode");
					cT.setApplicationNo(wsrccNoEDIT);
					String adminBranch = (String) session.getAttribute("AdminBranchCode");
					cT.insertUpdateWSRCCText(fullDetails,adminBranch);
					request.setAttribute("fullDetails", fullDetails);
					dispatcher = request.getRequestDispatcher("/premiumOpenCover/WSRCC1.jsp");
				}
				/*else
				{
					System.out.println("Exclusion block executed");
					cT.insertUpdateExclusions(fullDetails);
					request.setAttribute("fullDetails",fullDetails);
					dispatcher=request.getRequestDispatcher("/premiumOpenCover/conditionsOpenEdit1.jsp");
				}*/

				if(Integer.parseInt(warrantyNoEDIT)==1)
				{
					request.setAttribute("applicationNo",warrantyNoEDIT);
					cT.setApplicationNo(warrantyNoEDIT);
					cT.insertUpdateWarranties(fullDetails);
					request.setAttribute("fullDetails",fullDetails);
					dispatcher=request.getRequestDispatcher("/premiumOpenCover/warrantiesShow1.jsp");
				}
				/*else
				{
					System.out.println("warranty block executed");
					cT.insertUpdateWarranties(fullDetails);
					request.setAttribute("fullDetails",fullDetails);
					dispatcher=request.getRequestDispatcher("/premiumOpenCover/conditionsOpenEdit1.jsp");
				}*/
				dispatcher.forward(request, response);
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	} // DoPost

	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Put your code here
	}

	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

	public void init() throws ServletException {
		// Put your code here
	}

	public String getErrormsg(String errorCode,String description)
	{
		String result = "";
		String args[] = new String[1];
		String sql = "";
		runner run = new runner();
		try
		{
			args[0] = errorCode;
			sql = "select error_desc from error_master where error_id=?";
			result = run.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return result;
	}

	public String getDateStatus(String proNo,String effDate)   // updated marimuthu   04-09
	{
		String result = ""; 
		String results = ""; 
		String sql ="";
		runner run = new runner();
		try
		{
			Date date=new Date();
			String stdate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			sql = "select to_date('"+stdate+"','DD-MM-yyyy')-to_date('"+stdate+"','DD-MM-yyyy') from dual";
			result = run.singleSelection(sql);
			if(result==null)
			{
			  results =  "Please select Date";
		    }
			
			if(Double.parseDouble(result) > 0)
				results =  "INVALID";
			else
				results =  "VALID";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return results;
	}

} // Class

