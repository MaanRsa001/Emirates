package com.maan.broker.controller;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import com.maan.DBCon.DBConnectionStatus;

import com.maan.broker.DAO.UserCreationBean;
import com.maan.broker.DAO.FreightCreationBean;
import com.maan.common.util.OracleDateConversion;
import com.maan.broker.DAO.CustomerCreationBean;

public class FreightCreationController extends HttpServlet
{	
	Date dd=new Date();
	SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd-MM-yyyy");
	String deActivatedDate="";	
	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		HttpSession session = req.getSession(true);
		String  loginPersonId=(String)session.getAttribute("user");		
		UserCreationBean bc=new UserCreationBean();
		RequestDispatcher dispatcher1 = req.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		if(dispatcher1!=null)
			dispatcher1.include(req,res);
        
		//Rajesh For Db Checking
		String pathh = req.getContextPath();
		String usrModeSC="";
			usrModeSC=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
		//System.out.println("RoyalTest for databese mode checking in brokercreationController.."+usrModeSC);
		if(usrModeSC.length()>0)
			DBConnectionStatus.statusStatic=usrModeSC;
		else{
			res.sendRedirect(pathh+"/login/error_messg.jsp");
			return;
		}
		
    	//For Frieght Login Creation by Rajesh R on 05/01/08 start
		if(req.getParameter("requestfrom").equalsIgnoreCase("FreightCreationAdmin"))
		{
			FreightCreationBean freight = new FreightCreationBean();
			String requestfrom = req.getRequestURI();
			String requestfrom1 = req.getParameter("requestfrom");	
			String loginCreationStatus = req.getParameter("loginCreationStatus"); // Multi Login FreightCreation
			String masterFreightAgency = req.getParameter("masterFreightAgency"); // Multi Login FreightCreation
			String title = req.getParameter("Title")==null?"":req.getParameter("Title");
			String firstName = req.getParameter("FirstName")==null?"":req.getParameter("FirstName");
			String gender = req.getParameter("Gender")==null?"":req.getParameter("Gender");
			String lastName = req.getParameter("LastName")==null?"":req.getParameter("LastName");
			String nationality = req.getParameter("Nationality")==null?"":req.getParameter("Nationality");
			String dobDay = req.getParameter("DOBDay")==null?"":req.getParameter("DOBDay");
			String dobMonth = req.getParameter("DOBMonth")==null?"":req.getParameter("DOBMonth");
			String dobYear = req.getParameter("DOBYear")==null?"":req.getParameter("DOBYear");
			String telephone = req.getParameter("Telephone")==null?"":req.getParameter("Telephone");
			String mobile = req.getParameter("Mobile")==null?"":req.getParameter("Mobile");
			String fax = req.getParameter("Fax")==null?"":req.getParameter("Fax");
			String email = req.getParameter("Email")==null?"":req.getParameter("Email");
			String address1 = req.getParameter("Address1")==null?"":req.getParameter("Address1");
			String address2 = req.getParameter("Address2")==null?"":req.getParameter("Address2");
			String occupation = req.getParameter("Occupation")==null?"":req.getParameter("Occupation");
			String emirate = req.getParameter("Emirate")==null?"":req.getParameter("Emirate");
			String country = req.getParameter("Country")==null?"":req.getParameter("Country");
			String post = req.getParameter("post")==null?"":req.getParameter("post");	
			String city = req.getParameter("city")==null?"":req.getParameter("city");	
			String brokerCompanyName = req.getParameter("BrokerCompanyName")==null?"":req.getParameter("BrokerCompanyName");
			String brokerId = req.getParameter("BrokerId")==null?"":req.getParameter("BrokerId");
			String password = req.getParameter("Password")==null?"":req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword")==null?"":req.getParameter("RetypePassword");
			String mode = req.getParameter("mode")==null?"":req.getParameter("mode");
			loginPersonId = req.getParameter("editbroker")==null?"":req.getParameter("editbroker");//(String)session.getAttribute("user");
			String userType = req.getParameter("userType")==null?"":req.getParameter("userType");
			String provision = req.getParameter("provision")==null?"":req.getParameter("provision");
			String ucode = req.getParameter("ucode")==null?"":req.getParameter("ucode");

			freight.setTitle(title);
			freight.setFirstName(firstName);
			freight.setGender(gender);
			freight.setLastName(lastName);
			freight.setNationality(nationality);
			//freight.setBrokerDate(brokerDate);
			freight.setTelephone(telephone);
			freight.setMobile(mobile);
			freight.setFax(fax);
			freight.setEmail(email);
			freight.setAddress1(address1);
			freight.setAddress2(address2);
			freight.setOccupation(occupation);
			freight.setEmirate(emirate);
			freight.setCountry(country);
			freight.setPoBox(post);
			freight.setCity(city);
			freight.setBrokerCompanyName(brokerCompanyName);
			freight.setBrokerId(brokerId);
			freight.setPassword(password);
			freight.setRetypePassword(retypePassword);
			freight.setDobDay(dobDay);
			freight.setDobMonth(dobMonth);
			freight.setDobYear(dobYear);
			freight.setMode(mode);
			freight.setUserType(userType);
			freight.setProvision(provision);
			freight.setUcode(ucode);
			freight.setFdCode(masterFreightAgency);
			session.setAttribute("brokerId",brokerId);
			
			String error = freight.validate();
			if(error.length()>0){				
				req.setAttribute("error",error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminCreateFreight.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else{
					freight.setLoginPersonId(loginPersonId);
					freight.setUserType(userType);
					freight.setProvision(provision);
					String logBranch = "";
					logBranch = (String) session.getAttribute("LoginBranchCode");
					String process = freight.insertBrokerEntry(logBranch,loginCreationStatus); //loginCreationStatus
					if(process.equalsIgnoreCase("YES")){
						req.setAttribute("userType",userType);
						req.setAttribute("ucode",ucode);
						req.setAttribute("provision",provision);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/commissionforFreight.jsp");
						if(dispatcher!=null)
							dispatcher.forward(req,res);
					}
			}		
		}

		if(req.getParameter("requestfrom").equalsIgnoreCase("commisionforFreight")){
			String ucode = req.getParameter("ucode")==null?"":req.getParameter("ucode");
			String userType = req.getParameter("userType")==null?"":req.getParameter("userType");
			String provision = req.getParameter("provision")==null?"":req.getParameter("provision");
			FreightCreationBean freight = new FreightCreationBean();
			
			String loginCreationStatus = req.getParameter("loginCreationStatus"); // Multi Login FreightCreation
			String masterFreightAgency = req.getParameter("masterFreightAgency"); // Multi Login FreightCreation
			
			//String[][] pdetails=freight.getProducts();
			String 	login_id = (String)session.getAttribute("bcode");
			HashMap productsdetails = new HashMap();
			HashMap pros = new HashMap();
			String productsLength = req.getParameter("productsLength")==null?"0":req.getParameter("productsLength");
			String pid = req.getParameter("product")==null?"":req.getParameter("product");
			
			if(pid==null||pid.equals("null"))
				pid = "";					

			productsdetails.put("product",pid);
			productsdetails.put("commision",req.getParameter("commision")==null?"":req.getParameter("commision"));
			productsdetails.put("suminsured",req.getParameter("suminsured")==null?"":req.getParameter("suminsured"));
			productsdetails.put("minpremium",req.getParameter("minpremium")==null?"":req.getParameter("minpremium"));
			productsdetails.put("backdates",req.getParameter("backdates")==null?"":req.getParameter("backdates"));
			//productsdetails.put("loadingSign",req.getParameter("loadingSign")==null?"":req.getParameter("loadingSign"));
			productsdetails.put("loading",req.getParameter("loading")==null?"":req.getParameter("loading"));
			productsdetails.put("provisionPre",req.getParameter("provisionPre")==null?"":req.getParameter("provisionPre"));
			productsdetails.put("discount",req.getParameter("discount")==null?"":req.getParameter("discount"));
			productsdetails.put("rateOption",req.getParameter("rateOption")==null ? "N" : req.getParameter("rateOption"));
			productsdetails.put("scheduleOption",req.getParameter("scheduleOption")==null ? "N" : req.getParameter("scheduleOption"));		
			productsdetails.put("debitOption",req.getParameter("debitOption")==null ? "N" : req.getParameter("debitOption"));		
			productsdetails.put("adminOption",req.getParameter("adminOption")==null ? "N" : req.getParameter("adminOption"));		
			productsdetails.put("autoLoad",req.getParameter("autoLoad")==null ? "N" : req.getParameter("autoLoad"));		
			pros.put("productsdetails",productsdetails);	
		
			freight.setProDetails(pros);
			freight.setUcode(ucode);
			freight.setUserType(userType);
			freight.setLoginPersonId(login_id);		
			freight.setFdCode(masterFreightAgency);
			
			String error = freight.validateCommision();
			
			if(error.length()>0){
				req.setAttribute("error",error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/commissionforFreight.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else{
				String status = freight.insertOrUpdate(ucode,userType,login_id,provision,loginCreationStatus);
				String status12 = freight.LoginIdStatus(ucode);
				if(status12==null||status12.equals("")||status12.equalsIgnoreCase("null")||status12.equalsIgnoreCase("NONE"))	{	
					  req.setAttribute("ucode",ucode);
					  req.setAttribute("loginPersonId",login_id);
					  RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminFreightLoginCreation.jsp");
					  if(dispatcher!=null)
							dispatcher.forward(req,res);
				}
				else{
						req.setAttribute("ucode",ucode);
						req.setAttribute("loginPersonId",login_id);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminNewFreightConformation.jsp");
						if(dispatcher!=null)
							dispatcher.forward(req,res);
				}
			}
		}
	    if(req.getParameter("requestfrom").equalsIgnoreCase("FreightLoginCreationAdmin"))
		{
			 String ucode = req.getParameter("ucode")==null?"":req.getParameter("ucode");
			 String userType = req.getParameter("userType")==null?"":req.getParameter("userType");
			 loginPersonId = req.getParameter("loginPersonId") ==null?"":req.getParameter("loginPersonId");
			 String	brokerId = req.getParameter("BrokerId")==null?"":req.getParameter("BrokerId");
			 String	password = req.getParameter("Password")==null?"":req.getParameter("Password");
			 String	retypePassword = req.getParameter("RetypePassword")==null?"":req.getParameter("RetypePassword");
			FreightCreationBean freight = new FreightCreationBean();

			freight.setBrokerId(brokerId);
			freight.setPassword(password);
			freight.setRetypePassword(retypePassword);
			String error=freight.validateLoginCreation();
			if(error.length()>0){				
				req.setAttribute("error",error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/freightCreation/adminFreightLoginCreation.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else{
					freight.setLoginPersonId(loginPersonId);
					//Rajesh Modified  For Common Db
					String process = freight.insertUserLogin(ucode,loginPersonId,userType);
					//String process=freight.insertUserLogin(ucode,loginPersonId,userType,usrModeSC);
					if(process.equalsIgnoreCase("123")){
						RequestDispatcher dispatcher = req.getRequestDispatcher("/freightCreation/adminNewFreightConformation.jsp");
						if(dispatcher!=null)
							dispatcher.forward(req,res);	
					}
			}
		}
		if(req.getParameter("requestfrom").equalsIgnoreCase("FreightAdminStatus")){
			String  brokerStatus = "";
			String error = "";
			String selectedBroker = "";
			int noofupdations = 0;
			FreightCreationBean freight = new FreightCreationBean();
			String  selectedType = req.getParameter("selecttype")==null?"":req.getParameter("selecttype");
			String  selectdiscarded = req.getParameter("selectdiscarded")==null?"":req.getParameter("selectdiscarded");
			loginPersonId = req.getParameter("loginPersonId")==null?"":req.getParameter("loginPersonId");
			String status="";
			String[][] allBrokers = new String[0][0];
			String[][] user=new String[0][0];
			String[][] account=new String[0][0];
			boolean flag=false;			
			
			if(selectedType.equalsIgnoreCase("0"))	{				
				error="Please Select the Type";
				req.setAttribute("error",error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminUsers1.jsp");
				if(dispatcher!=null)
					dispatcher.forward(req,res);
			}
			else{
				String  userStatus="";
				String  accountStatus="";
				if(selectedType.equalsIgnoreCase("5"))	{
					if(selectdiscarded.equalsIgnoreCase("0"))	{
						error="Please Select Status";
						req.setAttribute("error",error);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminUsers1.jsp");
						if(dispatcher!=null)
							dispatcher.forward(req,res);
					}
					else{
						user = freight.getSelected(loginPersonId,selectedType,selectdiscarded);
						if(user.length>0)	{
							for(int i=0;i<user.length;i++){
								brokerStatus=req.getParameter("users"+user[i][3].trim())==null?"":req.getParameter("users"+user[i][3].trim());								
								if(brokerStatus.trim().equalsIgnoreCase(user[i][3].trim())){									
									status="Y";
								}
								else{
									if(selectdiscarded.equals("Y")||selectdiscarded.equals("A"))
										selectdiscarded="D";
									status=selectdiscarded;
								}						
								if(freight.updateStatus(status,user[i][3],selectedType))
									noofupdations++;
							}							
							if(noofupdations==user.length){								
								deActivatedDate = OracleDateConversion.ConvertDate(""+simpleFormatter.format(dd));								
								req.setAttribute("status",status);
								req.setAttribute("deActivatedDate",deActivatedDate);
								RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminConformDeactivate.jsp");
								if(dispatcher!=null)
									dispatcher.forward(req,res);
							}
						}
						else{
								error="No Records With this Status";
								req.setAttribute("error",error);
								RequestDispatcher dispatcher = req.getRequestDispatcher("../freightCreation/adminUsers1.jsp");
								if(dispatcher!=null)
									dispatcher.forward(req,res);
						}
					}
				}				
			}
		}	
		/*if(req.getParameter("requestfrom").equalsIgnoreCase("FreightLoginStatus"))
		{
			CustomerCreationBean customer = new CustomerCreationBean();			
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
			else if(curStatus.equalsIgnoreCase("DD")){
				results = customer.updateCusLogStatus(accode,"Deleted","");
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/freightCreation/adminUsers1.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);			
		}*/
		if(req.getParameter("requestfrom").equalsIgnoreCase("FreightLoginStatus"))
		{
			final CustomerCreationBean customer = new CustomerCreationBean();
			final String  curStatus = req.getParameter("selectdiscarded")==null?"":req.getParameter("selectdiscarded");
			final String accode=req.getParameter("brokerId")==null?"":req.getParameter("brokerId");
			final String result =customer.updateCusLogStatus(accode,curStatus);
			String msg = result +" User Status ";
			if (curStatus.equalsIgnoreCase("Y"))
				msg += "Active";
			else if (curStatus.equalsIgnoreCase("D")
			|| curStatus.equalsIgnoreCase("E"))
				msg += "DeActive";
			else if (curStatus.equalsIgnoreCase("DD"))
				msg += "Do Delete";
			else if (curStatus.equalsIgnoreCase("L"))
				msg += "Locked";
			else if (curStatus.equalsIgnoreCase("N"))
				msg += "Deleted";
			
			req.setAttribute("message", msg);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/freightCreation/StatusConform.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
			
		}
		//For Frieght Login Creation by Rajesh R on 05/01/08 End
	}	
}