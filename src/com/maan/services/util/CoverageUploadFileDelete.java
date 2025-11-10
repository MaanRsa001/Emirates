package com.maan.services.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.services.policyInfo;

public class CoverageUploadFileDelete extends HttpServlet  
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
       /*   
        RequestDispatcher check = request.getRequestDispatcher("login/sessionsCheckNormal.jsp");
        check.include(request,response);*/
   	
		//SESSION CHECK
        
		String usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
        DBConnectionStatus.statusStatic=usrModeSC;
        //END OF SESSION CHECK
        
		String branch = (String)session.getAttribute("LoginBranchCode");
		String productId=(String) session.getAttribute("product_id");
		
		branch = branch == null?"020":branch;
		productId = productId ==null?"":productId;
        
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
		policyInfo OSB = new policyInfo();
		
		String uploadId = "";
		uploadId = request.getParameter("uploadId");
		uploadId = uploadId == null ? "" : uploadId;	
		
        try
		{
			OSB.getCustFileDelete(uploadId);
			out.println("<font color=\"red\" size=\"1\"> deleted </font>");
        }
        catch(Exception ex)
		{
            out.println("CoverageUploadFileDelete OfficeShield Controller");
            ex.printStackTrace();
        }
	}

}  // Class   

