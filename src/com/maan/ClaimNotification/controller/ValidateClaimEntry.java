package com.maan.ClaimNotification.controller;

import java.io.IOException;
import java.util.Vector;
import java.util.HashMap;

import com.maan.ClaimNotification.DAO.ClaimNotificationBean;
import com.maan.DBCon.DBConnectionStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidateClaimEntry extends HttpServlet
{
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
    	//For Connection purpose
		String path = request.getContextPath();
    	HttpSession session = request.getSession(true);
        //    	com.maan.DBCon.DBConnectionStatus.statusStatic = (String)session.getAttribute("userLoginMode");

    	  //new
    	  String modeCheck = request.getContextPath();
 	      modeCheck = modeCheck == null?"":modeCheck;
          if(session.getAttribute("user") == null){
              if(modeCheck.equalsIgnoreCase("/E-Cargo")){
                   modeCheck = "Live";
              }
              else{
                  // modeCheck = "Test";
                  modeCheck = "Live";
              }
              com.maan.DBCon.DBConnectionStatus.statusStatic = modeCheck;
          }
          else{
              com.maan.DBCon.DBConnectionStatus.statusStatic = (String)session.getAttribute("userLoginMode");
          }
    	  //END

        String policy_no       = request.getParameter("policy_no");
        String day             = request.getParameter("day");
        String month           = request.getParameter("month");
        String year            = request.getParameter("year");
        String claimdesc       = request.getParameter("claimdescription");
        String claimtype       = request.getParameter("TypeOfClaim");
        String quote_no        = request.getParameter("quote_no");
        String application_no  = request.getParameter("application_no");
        String frompath        = request.getParameter("from");
        String user            = (String)session.getAttribute("user");
        String usertype        = (String)session.getAttribute("usertype");
        String mode 		   = request.getParameter("mode");
        String claimNumber     = request.getParameter("ClaimNumber");

        frompath = frompath==null?"":frompath;
        request.setAttribute("path",frompath);
        System.out.println("frompath        ----------->"+frompath);

        Vector  error              = new Vector();
        RequestDispatcher rd       = null;
        ClaimNotificationBean cb   = new ClaimNotificationBean();

        day       = day==null?"0":day;
        month     = month==null?"0":month;
        year      = year==null?"0":year;
        claimdesc = claimdesc==null?"":claimdesc;
        claimtype = claimtype==null?"":claimtype;
        user = (user == null || user.equalsIgnoreCase("null") || user.equals(""))?"":user;
        usertype = (usertype == null || usertype.equalsIgnoreCase("null") || usertype.equals(""))?"":usertype;
        mode = mode==null?"":mode;
        claimNumber = claimNumber==null?"":claimNumber;

        if(day.equals("0") || month.equals("0") || year.equals("0")){
            error.add("Please specify a valid Claim Occured Date");
        }

        claimdesc = claimdesc.trim();
        if(claimdesc.length() == 0){
            error.add("Please Enter a Valid description about Claim");
        }

        if(claimtype.equals("0")){
            error.add("Please select Claim Type");
        }
        try{
    	   int i=0;
    	   if(month.equalsIgnoreCase("Jan"))  i = 1;
           if(month.equalsIgnoreCase("Feb"))  i = 2;
           if(month.equalsIgnoreCase("Mar"))  i = 3;
           if(month.equalsIgnoreCase("Apr"))  i = 4;
           if(month.equalsIgnoreCase("May"))  i = 5;
           if(month.equalsIgnoreCase("Jun"))  i = 6;
           if(month.equalsIgnoreCase("Jul"))  i = 7;
           if(month.equalsIgnoreCase("Aug"))  i = 8;
           if(month.equalsIgnoreCase("Sep"))  i = 9;
           if(month.equalsIgnoreCase("Oct"))  i = 10;
           if(month.equalsIgnoreCase("Nov"))  i = 11;
           if(month.equalsIgnoreCase("Dec"))  i = 12;
           String dateValidate = validateClaimDate(Integer.parseInt(year),i,Integer.parseInt(day));
           if(dateValidate != null && dateValidate.length() > 0){
        	     if(!error.contains("Please specify a valid Claim Occured Date"))
        	    	  error.add(new String("Please specify a valid Claim Occured Date"));
           }
        }
        catch(Exception ex){
    	   if(!error.contains("Please specify a valid Claim Occured Date"))
 	    	  error.add(new String("Please specify a valid Claim Occured Date"));
        }

        HashMap ht = new HashMap();
        if(day != null)
             ht.put("day",day);
        if(month != null)
             ht.put("month",month);
        if(year != null)
             ht.put("year",year);
        if(claimdesc != null)
             ht.put("claimdesc",claimdesc);
        if(claimtype != null)
             ht.put("claimtype",claimtype);
        if(policy_no != null)
             ht.put("policy_no",policy_no);
        if(quote_no != null)
             ht.put("quote_no",quote_no);
        if(application_no != null)
             ht.put("application_no",application_no);
        if(user != null)
            ht.put("user",user);
        if(usertype != null)
        	ht.put("usertype",usertype);

        request.setAttribute("ClaimNo",claimNumber);

        if(error.size() > 0){
             rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationDetails.jsp");
             request.setAttribute("policynumber",policy_no);
             request.setAttribute("Error",error);
             request.setAttribute("from",frompath);
             request.setAttribute("EnteredDetails",ht);
             System.out.println("Value oof mode before setting  "+mode);
             request.setAttribute("OPT",mode);

             if(rd != null)
                 rd.forward(request,response);
             return;
        }
        else{
    	     if(!day.equals("0") && !month.equals("0") && !year.equals("0")){
                  String dateresult  = cb.validateClaimOccuredDate(day,month,year,policy_no);
                  dateresult = dateresult==null?"":dateresult;
                  if(!dateresult.equalsIgnoreCase("VALIDDATE")){
                     if(dateresult.equalsIgnoreCase("INVALID_CLAIM_OCCURED_DATE")){
                    	       error.add("INVALID CLAIM OCCURED DATE");
                     }
                     else if(dateresult.equalsIgnoreCase("GREATER_THAN_TODAYS_DATE")){
                    	 error.add("Claim Occured Date cannot be greater than todays date");
                     }
                     request.setAttribute("OPT",mode);
                     rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationDetails.jsp");
                     request.setAttribute("EnteredDetails",ht);
                     request.setAttribute("Error",error);
                     if(rd != null)
                         rd.forward(request,response);
                     return;
                 }
          }

          if(!mode.equalsIgnoreCase("edit")){
        	  String result = cb.getStatus(policy_no);
              System.out.println("RESULT VALUE       "+result);
		          if(result != null && result.equals("CLAIM_ALREADY_PENDING")){
		                   String  message = "Claim Made for policy number "+policy_no+" is already pending.Cannot make further Claims ";
		                   System.out.println("message skv          "+message);
		                   request.setAttribute("message",message);
		                   rd = getServletContext().getRequestDispatcher("/ClaimNotification/ClaimNotificationEnd.jsp");
		                   if(rd != null)
		                     rd.forward(request,response);
		                   return;
		          }
          }
          String startnumber  = "";
          if(mode.equalsIgnoreCase("add")){
        	  	startnumber  = cb.insertClaim(ht);
          }
          else if(mode.equalsIgnoreCase("edit")){
        	  startnumber  = cb.UpdateClaim(ht,policy_no,claimNumber);
          }
          session.setAttribute("ClaimNumber",startnumber);
          session.setAttribute("claimpolicy_no",policy_no);
          session.setAttribute("frompath",frompath);
          // response.sendRedirect("/E-Cargo/ClaimNotification/ClaimEnd.jsp");
          response.sendRedirect(path+"/ClaimNotification/ClaimEnd.jsp");
          return;
      }
    }
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
    {
        doPost(request,response);
    }

    public String validateClaimDate(int year,int month,int day){
        String mess = "";
        if(month == 2 && year%4 == 0){
             if(day == 30 || day == 31){
                mess ="Please Select  a Valid  Effective Date";
             }
        }
        else if(month == 2 && year%4 != 0){
            if(day == 29 || day == 30 || day ==31){
                mess ="Please Select  a Valid  Effective Date";
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11){
            if(day == 31){
                mess ="Please Select  a Valid  Effective Date";
            }
        }
        return mess;
    }
}
