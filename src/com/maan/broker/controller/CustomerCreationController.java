package com.maan.broker.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.ServletConfig;
import java.util.HashMap;

import com.maan.DBCon.DBConnectionStatus;

public class CustomerCreationController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out=null;
	java.util.Date dd=new java.util.Date();
	java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
	String deActivatedDate="";

	public void init(ServletConfig  config)throws ServletException
	{
	
	}

	public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		processResult(req,res);
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		processResult(req,res);
	}

	public void processResult(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		HttpSession session = req.getSession(true);
		String loginPersonId=(String)session.getAttribute("user");
		PrintWriter out = res.getWriter();
		com.maan.broker.DAO.UserCreationBean bc=new com.maan.broker.DAO.UserCreationBean();
		RequestDispatcher dispatcher1 = req.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		if(dispatcher1!=null)
			dispatcher1.include(req,res);
		out.println("ROYALTEST..."+req.getParameter("requestfrom"));
		//Rajesh For Db Checking
		String pathh = req.getContextPath();
		String usrModeSC="";
			usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		System.out.println("RoyalTest for databese mode checking in CustomerCreationController.."+usrModeSC);
		if(usrModeSC.length()>0)
			DBConnectionStatus.statusStatic=usrModeSC;
		else
		{
			res.sendRedirect(pathh+"/login/error_messg.jsp");
			return;
		}
		//		
		//For Customer Login Creation by Rajesh R on09/04/08 start
		if(req.getParameter("requestfrom").equalsIgnoreCase("CustomerCreationAdmin"))
		{
			com.maan.broker.DAO.CustomerCreationBean customer = new com.maan.broker.DAO.CustomerCreationBean();
			String customerId = req.getParameter("customerId");			
			String brokerId = req.getParameter("editbroker");
			
			customerId = customerId==null?"":customerId;
			brokerId = brokerId==null?"":brokerId;
			customer.setBrokerLoginId(brokerId);
			customer.setCustomerId(customerId);
			String logBranch = "";
			logBranch = (String) session.getAttribute("LoginBranchCode");	
			String process = customer.insertCustomerEntry(logBranch);
			if(process.equalsIgnoreCase("YES"))
			{
				req.setAttribute("ucode",customer.getUcode());
				session.setAttribute("brokerId",brokerId);
				session.setAttribute("ucode",customer.getUcode());
				RequestDispatcher dispatcher = req.getRequestDispatcher("../CustomerCreation/commissionforCustomer.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
		}
		
		else if(req.getParameter("requestfrom").equalsIgnoreCase("SubCustomer"))
		{
			com.maan.broker.DAO.CustomerCreationBean cust = new com.maan.broker.DAO.CustomerCreationBean();
			String logBranch = "";
			logBranch = (String) session.getAttribute("LoginBranchCode");
			String cusLoginId ="";
			String ucode = "";
			String custName = "";
			String brokerName = "";
			String brokerId = "";
			String fdCode = "";
			String mode = "";
			custName = req.getParameter("custName");
			brokerName = req.getParameter("brokerName");
			brokerId = req.getParameter("brokerId");
			fdCode = req.getParameter("fdCode");
			mode = req.getParameter("mode");
			
			custName = custName == null ? "" : custName;
			brokerName = brokerName == null ? "" : brokerName;
			brokerId = brokerId == null ? "" : brokerId;
			fdCode = fdCode == null ? "" : fdCode;
			mode = mode == null ? "" : mode;
			
			if(!mode.equalsIgnoreCase("edit")){
				ucode = cust.getSubCustomerAgencyCode(logBranch);
				ucode = "c"+ucode;
			}
			else{
				ucode = req.getParameter("agencyCode") == null ? "" : req.getParameter("agencyCode");
				cusLoginId = cust.getCustLoginByAgency(ucode);
			}
			req.setAttribute("cusLoginId", cusLoginId);
			req.setAttribute("brokerId", brokerId);
			req.setAttribute("masCustName", custName);
			req.setAttribute("brokerName", brokerName);
			req.setAttribute("ucode",ucode);
			req.setAttribute("fdCode",fdCode);
			RequestDispatcher dispatcher = req.getRequestDispatcher("../CustomerCreation/adminCreateUser.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
		}
		
		// Sub Customer Creation
		else if (req.getParameter("requestfrom").equalsIgnoreCase("SubCustomerCreation")) 
		{
			String cusLoginId=req.getParameter("cusLoginId")==null?"":req.getParameter("cusLoginId");
			String fdCode = req.getParameter("fdCode") == null ? "" : req.getParameter("fdCode");
			String title = req.getParameter("Title") == null ? "" : req.getParameter("Title");
			String firstName = req.getParameter("FirstName") == null ? "" : req.getParameter("FirstName");
			String gender = req.getParameter("Gender") == null ? "" : req.getParameter("Gender");
			String lastName = req.getParameter("LastName") == null ? "" : req.getParameter("LastName");
			String nationality = req.getParameter("Nationality") == null ? "" : req.getParameter("Nationality");
			String dobDay = req.getParameter("DOBDay") == null ? "" : req.getParameter("DOBDay");
			String dobMonth = req.getParameter("DOBMonth") == null ? "" : req.getParameter("DOBMonth");
			String dobYear = req.getParameter("DOBYear") == null ? "" : req.getParameter("DOBYear");
			String telephone = req.getParameter("Telephone") == null ? "" : req.getParameter("Telephone");
			String mobile = req.getParameter("Mobile") == null ? "" : req.getParameter("Mobile");
			String fax = req.getParameter("Fax") == null ? "" : req.getParameter("Fax");
			String email = req.getParameter("Email") == null ? "" : req.getParameter("Email");
			String address1 = req.getParameter("Address1") == null ? "" : req.getParameter("Address1");
			String address2 = req.getParameter("Address2") == null ? "" : req.getParameter("Address2");
			String occupation = req.getParameter("Occupation") == null ? "" : req.getParameter("Occupation");
			String emirate = req.getParameter("Emirate") == null ? "" : req.getParameter("Emirate");
			String city = req.getParameter("city") == null ? "" : req.getParameter("city");
			String country = req.getParameter("Country") == null ? "" : req.getParameter("Country");
			String post = req.getParameter("post") == null ? "" : req.getParameter("post");
			String brokerCompanyName = req.getParameter("BrokerCompanyName") == null ? "" : req.getParameter("BrokerCompanyName");
			String brokerId = req.getParameter("brokerId") == null ? "" : req.getParameter("brokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "": req.getParameter("RetypePassword");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			loginPersonId = req.getParameter("editbroker") == null ? "" : req.getParameter("editbroker");// (String)session.getAttribute("user");
			String provision = req.getParameter("provision") == null ? "" : req.getParameter("provision");
			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
			bc.setTitle(title);
			bc.setFirstName(firstName);
			bc.setGender(gender);
			bc.setLastName(lastName);
			bc.setNationality(nationality);
			bc.setTelephone(telephone);
			bc.setMobile(mobile);
			bc.setFax(fax);
			bc.setEmail(email);
			bc.setAddress1(address1);
			bc.setAddress2(address2);
			bc.setOccupation(occupation);
			bc.setEmirate(emirate);
			bc.setCity(city);
			bc.setCountry(country);
			bc.setPoBox(post);
			bc.setBrokerCompanyName(brokerCompanyName);
			bc.setBrokerId(brokerId);
			bc.setPassword(password);
			bc.setRetypePassword(retypePassword);
			bc.setDobDay(dobDay);
			bc.setDobMonth(dobMonth);
			bc.setDobYear(dobYear);
			bc.setMode(mode);
			bc.setProvision(provision);
			bc.setUcode(ucode);
			bc.setFdCode(fdCode);
			session.setAttribute("brokerId", brokerId);
			String error = bc.validate();
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../CustomerCreation/adminCreateUser.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				bc.setUserType("1");
				bc.setProvision(provision);
				String logBranch = "";
				logBranch = (String) session.getAttribute("LoginBranchCode");	
				String process = bc.insertBrokerEntry(logBranch,"");
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("cusLoginId", cusLoginId);
					req.setAttribute("ucode", ucode);
					req.setAttribute("provision", provision);
					RequestDispatcher dispatcher = req.getRequestDispatcher("../CustomerCreation/commissionforCustomer.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		// SubCustomer Creation -- JUly 11
		else if (req.getParameter("requestfrom").equalsIgnoreCase("commisionforCustomer")) 
		{
			String openCoverNos = req.getParameter("openCoverNos")==null?"": req.getParameter("openCoverNos");
			System.out.println("openCoverNos..........."+openCoverNos);
			com.maan.broker.DAO.CustomerCreationBean customer = new com.maan.broker.DAO.CustomerCreationBean();
			String[][] bdetails = new String[0][0];
			// String userid = (String)session.getAttribute("userid");
			String login_id = (String)session.getAttribute("brokerId");
			String branch ="";
			branch = (String) session.getAttribute("LoginBranchCode");
			String adminProductId="";
			adminProductId = (String)session.getAttribute("pro_Id");
			adminProductId = adminProductId==null?adminProductId:"";
			if(adminProductId.length()<=0)
			{
				com.maan.admin.DAO.AdminBean admin = new com.maan.admin.DAO.AdminBean();
				adminProductId = admin.getLoginProIds((String)session.getAttribute("user")); 
				session.setAttribute("pro_Id",adminProductId);
			}
			
			bdetails = customer.getProducts(login_id,branch,adminProductId);
			
			HashMap productsdetails = new HashMap();
			HashMap pros = new HashMap();
			
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			loginPersonId = req.getParameter("loginPersonId") == null ?"": req.getParameter("loginPersonId");
			String cusLoginId = req.getParameter("cusLoginId") == null ?"": req.getParameter("cusLoginId");
			
			String productsLength = req.getParameter("productsLength") == null ? "0": req.getParameter("productsLength");
			
			int j = 1;
			for (int i = 0; i < (Integer.parseInt(productsLength)); i++) 
			{
				if (req.getParameter("product" + bdetails[i][0]) != null) 
				{
					productsdetails.put("product" + j, req.getParameter("product"+ bdetails[i][0]) == null ? "" : req.getParameter("product" + bdetails[i][0]));
					productsdetails.put("commision" + j, req.getParameter("commision"+ bdetails[i][0]) == null ? "" : req.getParameter("commision" + bdetails[i][0]));
					productsdetails.put("suminsured" + j,req.getParameter("suminsured"+ bdetails[i][0]) == null ? "":req.getParameter("suminsured"+ bdetails[i][0]));
					productsdetails.put("premium" + j, req.getParameter("premium"+ bdetails[i][0]) == null ? "" : req.getParameter("premium" + bdetails[i][0]));
					productsdetails.put("discount" + j, req.getParameter("discount"+ bdetails[i][0]) == null ? "" : req.getParameter("discount" + bdetails[i][0]));
					productsdetails.put("bday" + j, req.getParameter("bday"+ bdetails[i][0]) == null ? "0" : req.getParameter("bday" + bdetails[i][0]));
					productsdetails.put("Quote" + j, req.getParameter("Quote"+ bdetails[i][0]) == null ? "NONE" : req.getParameter("Quote" + bdetails[i][0]));
					productsdetails.put("Schedule" + j, req.getParameter("Schedule"+ bdetails[i][0]) == null ? "N" : req.getParameter("Schedule" + bdetails[i][0]));
					productsdetails.put("Debit" + j, req.getParameter("Debit"+ bdetails[i][0]) == null ? "N" : req.getParameter("Debit" + bdetails[i][0]));
					productsdetails.put("CusDebit" + j, req.getParameter("CusDebit"+ bdetails[i][0]) == null ? "N" : req.getParameter("CusDebit" + bdetails[i][0]));
					productsdetails.put("CusCertificate" + j, req.getParameter("CusCertificate"+ bdetails[i][0]) == null ? "N" : req.getParameter("CusCertificate" + bdetails[i][0]));
					productsdetails.put("policy" + j, req.getParameter("policy" + bdetails[i][0]) == null ? "N" : req.getParameter("policy" + bdetails[i][0]));
					productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req.getParameter("referral" + j));
					pros.put("productsdetails" + j, productsdetails);
					j++;
				} 
			}
			customer.setProDetails(pros);
			String error = "";
			error = customer.validateCommision(login_id);
			if (error.length() > 0) 
			{
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/commissionforCustomer.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else 
			{
				String ucode = (String)session.getAttribute("ucode");
				if(ucode==null || ucode.equalsIgnoreCase("null")|| ucode.equals("") || ucode.length() ==0 )
				{
					ucode = req.getParameter("ucode");
				}
				String adminId = (String)session.getAttribute("user");
				String status = customer.insertOrUpdate(ucode, login_id,adminId,cusLoginId,openCoverNos);
				String status12 = customer.LoginIdStatus(ucode);
				if (status12 == null || status12.equals("")	|| status12.equalsIgnoreCase("null")|| status12.equalsIgnoreCase("NONE")) 
				{
					req.setAttribute("ucode", ucode);
					req.setAttribute("brokerId", login_id);
					session.setAttribute("brokerId",login_id);
					session.setAttribute("ucode",ucode);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/CustomerLoginCreation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} 
				else
				{
					req.setAttribute("ucode", ucode);
					req.setAttribute("brokerId", login_id);
					session.setAttribute("brokerId",login_id);
					session.setAttribute("ucode",ucode);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/CustomerLoginConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		} 

	    if(req.getParameter("requestfrom").equalsIgnoreCase("CustomerLoginCreation"))
		{
			 String ucode = req.getParameter("ucode")==null?"":req.getParameter("ucode");
			 String brokerLoginId = req.getParameter("brokerId") ==null?"":req.getParameter("brokerId");
			 String	brokerId = req.getParameter("BrokerId")==null?"":req.getParameter("BrokerId");
			 String	password = req.getParameter("Password")==null?"":req.getParameter("Password");
			 String	retypePassword = req.getParameter("RetypePassword")==null?"":req.getParameter("RetypePassword");
			 com.maan.broker.DAO.CustomerCreationBean customer = new com.maan.broker.DAO.CustomerCreationBean();
			 customer.setBrokerId(brokerId);
			 customer.setPassword(password);
			 customer.setRetypePassword(retypePassword);
			 String error = customer.validateLoginCreation();
			 //
				com.maan.admin.DAO.AdminBean cc = new com.maan.admin.DAO.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req	.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req	.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""	: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req.getParameter("dobYear1"));
				cc.setData1(req.getParameter("dobDay") + "-"	+ req.getParameter("dobMonth") + "-"+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-"+ req.getParameter("dobYear1"));
				cc.setRep("p");
				String error1 = cc.validateTrashFields();
				String data1 = (req.getParameter("dobDay") + "-"	+ req.getParameter("dobMonth") + "-" + req	.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-" + req.getParameter("dobYear1"));
				error = error+error1;
				out.println("ROtaLTEst.."+data1);
				out.println("<br>ROtaLTEst.."+data2);
			 //
			if(error.length()>0)
			{
				out.println("error ifnfo "+error);
				req.setAttribute("error",error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/CustomerLoginCreation.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else
			{
				customer.setLoginPersonId(brokerLoginId);
				//Rajesh Modified  For Common Db
				String process = customer.insertUserLogin(ucode,brokerLoginId,data1,data2);
				//String process = freight.insertUserLogin(ucode,loginPersonId,userType,usrModeSC);
				if(process.equalsIgnoreCase("123"))
				{
					RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/CustomerLoginConformation.jsp");
					if(dispatcher!=null)
						dispatcher.forward(req,res);
				}
			}
		}
	
		if(req.getParameter("requestfrom").equalsIgnoreCase("OpenCoverCertificate"))
		{
			String brokerLoginId = req.getParameter("brokerLoginId")==null?"":req.getParameter("brokerLoginId");
			String pid = req.getParameter("pid")==null?"":req.getParameter("pid");
			String cusId = req.getParameter("cusId")==null?"":req.getParameter("cusId");
			String opencoversLength = req.getParameter("opencoversLength")==null?"0":req.getParameter("opencoversLength");
			String ucode = req.getParameter("ucode")==null?"":(String)req.getParameter("ucode");
			if(ucode.length()<=0)
			{
				ucode = (String)session.getAttribute("ucode");
			}
			if(opencoversLength.length()<=0)
				opencoversLength = "0";
			int len = Integer.parseInt(opencoversLength);
			String openCoverPNos="";
			for(int i=0;i<(len+1);i++)
			{
				String openCoverPNo = req.getParameter("opencover"+(i+1))==null?"":req.getParameter("opencover"+(i+1));
				if(openCoverPNo.length()>0)
					openCoverPNos = openCoverPNos+"'"+openCoverPNo+"',";
			}
			if(openCoverPNos.length()>0)
				openCoverPNos = openCoverPNos.substring(0,(openCoverPNos.length()-1));
			
			com.maan.broker.DAO.CustomerCreationBean customer = new com.maan.broker.DAO.CustomerCreationBean();
			
			String masterSub = customer.masterSubCustomerChk(ucode);
			String result = "";
			
			if(masterSub.equals("1"))
			{
				result = customer.updateCetificateforCustomer(openCoverPNos,brokerLoginId,cusId,pid,ucode,"ROYAL");
				for(int i=0;i<(len+1);i++)
				{
					String openCoverNo = req.getParameter("OCNO"+i)==null?"":req.getParameter("OCNO"+i);
					//String Schedule = req.getParameter("Schedule"+i)==null?"":req.getParameter("Schedule"+i);
					//String Debit = req.getParameter("Debit"+i)==null?"":req.getParameter("Debit"+i);
					String CusDebit = req.getParameter("CusDebit"+i)==null?"":req.getParameter("CusDebit"+i);
					if(openCoverNo.length()>0)
					{
						/*if(Schedule.length()<=0)
							Schedule="N";
						if(Debit.length()<=0)
							Debit="N";*/
						if(CusDebit.length()<=0)
							CusDebit="N";
						result = customer.updateCetificateforCustomer(openCoverNo,"","",CusDebit,brokerLoginId);
					}
				}
			}
			else
			{
				result = "updated";
			}
			if(result.equalsIgnoreCase("Updated"))
			{
				req.setAttribute("openCoverNos",openCoverPNos);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/OpenCoverMessage.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else
			{
				RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/OpenCoverPopUp.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
		}
		
		if(req.getParameter("requestfrom").equalsIgnoreCase("CustomerLoginStatus"))
		{
			com.maan.broker.DAO.CustomerCreationBean customer = new com.maan.broker.DAO.CustomerCreationBean();
			
			String  curStatus = req.getParameter("curStatus")==null?"":req.getParameter("curStatus");
			String brokerLoginId = req.getParameter("loginPersonId")==null?"":req.getParameter("loginPersonId");
			String  len = req.getParameter("brokerCustomer")==null?"0":req.getParameter("brokerCustomer");
			int totlen=0;
			if(len.length()>0)
				totlen = Integer.parseInt(len);
			String accode="";
			for(int i=0;i<(totlen+1);i++)
			{
				String code = req.getParameter("users"+i)==null?"":req.getParameter("users"+i);
				if(code.length()>0)
					accode = accode+"'"+code+"',";
			}
			if(accode.length()>0)
				accode = accode.substring(0,(accode.length()-1));
			String results="";
			
			if(curStatus.equalsIgnoreCase("Y"))
				results = customer.updateCusLogStatus(accode,"Deactivate","");
			else if(curStatus.equalsIgnoreCase("D")||curStatus.equalsIgnoreCase("N")||curStatus.equalsIgnoreCase("L"))
				results = customer.updateCusLogStatus(accode,"Activate","");
			else if(curStatus.equalsIgnoreCase("E"))
			{
				for(int i=0;i<(totlen+1);i++)
				{
					String code = req.getParameter("users"+i)==null?"":req.getParameter("users"+i);
					String dobDay1=req.getParameter("dobDay1"+i)==null?"0":req.getParameter("dobDay1"+i);
					String dobMonth1=req.getParameter("dobMonth1"+i)==null?"0":req.getParameter("dobMonth1"+i);
					String dobYear1=req.getParameter("dobYear1"+i)==null?"0":req.getParameter("dobYear1"+i);
					String edate1 = dobDay1+"-"+dobMonth1+"-"+dobYear1;
					
					if(code.length()>0&&edate1.length()>7)
					{
						results = customer.updateCusLogStatus(code,"Date",edate1);
					}
				}
				
			}
			else if(curStatus.equalsIgnoreCase("DD"))
			{
				results = customer.updateCusLogStatus(accode,"Deleted","");
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/adminUsers1.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
			
		}
		//For Customer Login Creation by Rajesh R on09/04/08 start
	}
}// Class