package com.maan.ClaimNotification.controller;

import java.io.IOException;
import java.util.Vector;

import com.maan.ClaimNotification.DAO.ClaimNotificationBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateStatus extends HttpServlet
{
   public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
   {
	    //For Connection purpose
   	      HttpSession session = request.getSession(true);
   	      String modeCheck = request.getContextPath();
            modeCheck = modeCheck == null?"":modeCheck;
            if(session.getAttribute("user") == null)
            {
                if(modeCheck.equalsIgnoreCase("/E-Cargo")){
                   modeCheck = "Live";
                }
   	            else{
                   //modeCheck = "Test";
                    modeCheck = "Live";
                }
   	            com.maan.DBCon.DBConnectionStatus.statusStatic = modeCheck;
            }
            else{
                com.maan.DBCon.DBConnectionStatus.statusStatic = (String)session.getAttribute("userLoginMode");
            }
   	    //END
   	      String multiple = request.getParameter("multiple");
	    RequestDispatcher rd = null;
	    Vector error = new Vector();
	    String policy_no = request.getParameter("policy_no");
	    String claim_id = request.getParameter("claim_id");
	    String claimNumber = request.getParameter("ClaimNumber");

	    policy_no = policy_no==null?"":policy_no;
	    claim_id = claim_id==null?"":claim_id;
        multiple = multiple==null?"":multiple;
        claimNumber = claimNumber==null?"":claimNumber;

	    request.setAttribute("policy_no",policy_no);
	    request.setAttribute("claim_id",claim_id);
	    request.setAttribute("ClaimNo",claimNumber);

	    String remarks = request.getParameter("rsaremarks");
	    String status = request.getParameter("selectStatus");

	    System.out.println("REMARKS ---------------->>>>> "+remarks);
	    System.out.println("status  ---------------------->>>> "+status);

	    if(remarks.length() == 0){
	     error.add("Please Enter Remarks<br>");
	    }
	    if(status.equalsIgnoreCase("0")){
	    	error.add("Please Select Status");
	    }
	    request.setAttribute("Status",status);
	    request.setAttribute("Remarks",remarks);

	    if(error.size()>0){
	        rd = getServletContext().getRequestDispatcher("/ClaimNotification/UpdateStatus.jsp?multiple="+multiple);
	        request.setAttribute("ERROR",error);
	        if(rd != null)
	        	rd.forward(request, response);
	        return;
	    }
	    else{
	    	ClaimNotificationBean cb = new ClaimNotificationBean();
	    	String result = "";
	    	result = cb.UpdateClaimStatus(claim_id,policy_no,remarks,status);
	    	if(result != null){
	    		request.setAttribute("PolicyNumber",policy_no);
	    		request.setAttribute("ClaimNumber",claimNumber);
	    		request.setAttribute("Status",status);
	    	    request.setAttribute("Remarks",remarks);
	    	    RequestDispatcher rdis = request.getRequestDispatcher("/claim/SendMail");
	    	    System.out.println("UserType in updateStatus.java"+(String)session.getAttribute("usertype"));
	    	    if(rdis != null){
	    	       rdis.include(request, response);
	    	    }
	    		String str  = "Status Updated for Policy Number  <font color=''>"+policy_no+"</font>";
	    		request.setAttribute("message",str);
	    		request.setAttribute("path","fromadmin");
	    	}
	    	rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationEnd.jsp");
	    	if(rd != null)
	    		rd.forward(request,response);
	    	return;
	    }
   }
   public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
   {
          doPost(request,response);
   }
}