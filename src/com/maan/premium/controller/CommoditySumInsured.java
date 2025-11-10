package com.maan.premium.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.premium.DAO.CommodityTransaction;


public class CommoditySumInsured extends HttpServlet  
{
	private static final long serialVersionUID = 1L;
	public  void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{ 
        ProceesRequest(request,response);
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
         ProceesRequest(request,response);
    }
	public void ProceesRequest(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
         if(session.getAttribute("ses")==null)
		 {	
			response.sendRedirect(request.getContextPath()+"/login/error_messg.jsp");
			return;
		 }    

		String path = request.getContextPath();
          
        RequestDispatcher check = request.getRequestDispatcher(path+"login/sessionsCheckNormal.jsp");
        check.include(request,response);
   	
		//SESSION CHECK
        
		String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
        
		String applicationNo = request.getParameter("applicationNo");
		String branch = (String)session.getAttribute("LoginBranchCode");
		String productId=(String) session.getAttribute("product_id");

		applicationNo = applicationNo == null ? "" : applicationNo;
		branch = branch == null?"020":branch;
		productId = productId ==null?"":productId;
        
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
		CommodityTransaction comTran = new CommodityTransaction();
		comTran.setProductId(productId);
		HashMap sumInfo = new HashMap();
		String comName="";
		String sumIns="";
		String cnt="";
        try
		{
            if(applicationNo!=null && applicationNo.length() >0)
			{
				sumInfo = comTran.getCommoditySumInsuredInfo(applicationNo,branch);
				if(sumInfo.size() >0)
				{
					comName =(String) sumInfo.get("ComName");
					sumIns = (String)sumInfo.get("sumIns");
					cnt = (String)sumInfo.get("Count");
				}
				out.println("<input name=\"totalSumInsured\" readonly type=\"text\" size=\"20\" class=\"fdel\"  style=\"width:133px\" value="+sumIns+">");

			  //out.println("&nbsp;&nbsp;&nbsp;<input type=\"text\" name=\"ExpiryDate\" readonly=readonly class=\"form\" style=\"width:90px\" value =" +returnDate +">");
            }
         }
        catch(Exception ex)
		{
            out.println("Invalid Date format");
            ex.printStackTrace();
        }
	}

}  // Class   

