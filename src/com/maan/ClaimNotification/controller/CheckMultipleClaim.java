package com.maan.ClaimNotification.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.maan.ClaimNotification.DAO.ClaimNotificationBean;

public class CheckMultipleClaim extends HttpServlet
{
     public void doPost(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException
    {
    	 String modeCheck = request.getContextPath();
            modeCheck = modeCheck == null?"":modeCheck;
            HttpSession session = request.getSession(true);
            if(session.getAttribute("user") == null)
            {
                if(modeCheck.equalsIgnoreCase("/E-Cargo")){
                   modeCheck = "Live";
                }
   	            else{
                   modeCheck = "Test";
                }
   	            com.maan.DBCon.DBConnectionStatus.statusStatic = modeCheck;
            }
            else{
                com.maan.DBCon.DBConnectionStatus.statusStatic = (String)session.getAttribute("userLoginMode");
            }

          String policy_no = request.getParameter("policy_no");
          String status      = request.getParameter("status");
          RequestDispatcher rd = null;
          ClaimNotificationBean cb = new ClaimNotificationBean();
          int count =  cb.getNumberOfClaim(policy_no,status);
          System.out.println("Value of count "+count);
          if( count == 1){
                  String claim_id = "";// cb.getClaimID(policy_no,status);
                  String claimNO = "";
                  String[][] result = cb.getClaimIDAndNo(policy_no, status);
                  if(result != null && result.length > 0){
                	  claim_id = result[0][0];
                	  claimNO = result[0][1];
                  }
                  rd = getServletContext().getRequestDispatcher("/ClaimNotification/UpdateStatus.jsp?policy_no="+policy_no+"&claim_id="+claim_id+"&ClaimNumber="+claimNO);
                  if(rd != null)
                      rd.forward(request,response);
                  return;
          }
          else{
                  rd = getServletContext().getRequestDispatcher("/ClaimNotification/ShowAllClaim.jsp?policy_no="+policy_no+"&status="+status);
                    if(rd != null)
                      rd.forward(request,response);
                  return;
          }
    }
    public void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException,IOException
    {
         doPost(request,response);
    }
}