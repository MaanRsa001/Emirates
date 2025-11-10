package com.maan.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.maan.DBCon.DBConnectionStatus;
import com.maan.admin.BrokerCreationBean;
import com.maan.common.util.OracleDateConversion;

public class BrokerCreationController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
	String deActivatedDate = "";
	public void init(ServletConfig config) throws ServletException 
	{
		try 
		{
		}
        catch (Exception e) 
		{
			System.out.println("Exception in init method" + e.toString());
		}
    }

	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws IOException, ServletException 
	{
	    HttpSession session = req.getSession(false);
		PrintWriter out = res.getWriter();
		com.maan.admin.AdminBean ab = new com.maan.admin.AdminBean();
		com.maan.admin.BrokerCreationBean bc = new com.maan.admin.BrokerCreationBean();
		String loginPersonId = (String) session.getAttribute("loginPersonId");
		RequestDispatcher dispatcher1 = req.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		if (dispatcher1 != null)
			dispatcher1.include(req, res);
		
		// Rajesh For Db Checking
		
		String pathh = req.getContextPath(), reqFrom ="", usrModeSC = "";
		reqFrom = req.getParameter("requestfrom")==null?"":req.getParameter("requestfrom");
		usrModeSC = (String) session.getAttribute("userLoginMode") == null ? "": (String) session.getAttribute("userLoginMode");
		System.out.println("RoyalTest for databese mode checking in brokercreationController.."+ usrModeSC);
		System.out.println("Request From:"+reqFrom);
		if (usrModeSC.length() > 0)
			DBConnectionStatus.statusStatic = usrModeSC;
		else 
		{
			res.sendRedirect(pathh + "/login/error_messg.jsp");
			return;
		}

		if (loginPersonId == null)
		{
			loginPersonId = (String) session.getAttribute("user");
		}
		if (reqFrom.equalsIgnoreCase("BrokerCreation")) 
		{			
			String requestfrom = req.getRequestURI();
			String requestfrom1 = reqFrom;
			String title = req.getParameter("Title") == null ? "" : req.getParameter("Title");
			String firstName = req.getParameter("FirstName") == null ? "" : req.getParameter("FirstName");
			String gender = req.getParameter("Gender") == null ? "" : req.getParameter("Gender");
			String lastName = req.getParameter("LastName") == null ? "" : req.getParameter("LastName");
			String nationality = req.getParameter("Nationality") == null ? "":req.getParameter("Nationality");
			String dobDay = req.getParameter("DOBDay") == null ? "" : req.getParameter("DOBDay");
			String dobMonth = req.getParameter("DOBMonth") == null ? "":req.getParameter("DOBMonth");
			String dobYear = req.getParameter("DOBYear") == null ? "" : req.getParameter("DOBYear");
			String telephone = req.getParameter("Telephone") == null ? "" : req.getParameter("Telephone");
			String sno = req.getParameter("sno") == null ? "" : req.getParameter("sno");
			String eno = req.getParameter("eno") == null ? "" : req.getParameter("eno");
			String mobile = req.getParameter("Mobile") == null ? "" : req.getParameter("Mobile");
			String fax = req.getParameter("Fax") == null ? "" : req.getParameter("Fax");
			String email = req.getParameter("Email") == null ? "" : req.getParameter("Email");
			String address1 = req.getParameter("Address1") == null ? "" : req.getParameter("Address1");
			String address2 = req.getParameter("Address2") == null ? "" : req.getParameter("Address2");
			String address3 = req.getParameter("Address3") == null ? "" : req.getParameter("Address3");
			String occupation = req.getParameter("Occupation") == null ? "": req.getParameter("Occupation");
			String emirate = req.getParameter("Emirate") == null ? "" : req.getParameter("Emirate");
			String country = req.getParameter("Country") == null ? "" : req.getParameter("Country");
			String post = req.getParameter("post") == null ? "" : req.getParameter("post");
			String brokerCompanyName = req.getParameter("BrokerCompanyName") == null ? "":req.getParameter("BrokerCompanyName");
			String brokerId = req.getParameter("BrokerId") == null ? "" : req.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "":req.getParameter("RetypePassword");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			String city = req.getParameter("city") == null ? "" : req.getParameter("city");
			String branch = req.getParameter("branch") == null ? "" : req.getParameter("branch");
			String rsa_broker_code = req.getParameter("rsa_broker_code") == null ? "": req.getParameter("rsa_broker_code");
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			String mcode = req.getParameter("mcode") == null ? "" : req.getParameter("mcode");
			String approvedby = req.getParameter("approvedby") == null ? "": req.getParameter("approvedby");
			String borganization = req.getParameter("borganization") == null ? "": req.getParameter("borganization");
			String executiveId = req.getParameter("executiveId") == null ? "": req.getParameter("executiveId");
			String brokerName = req.getParameter("brokerName") == null ? "": req.getParameter("brokerName");
			String arNo = req.getParameter("arNo") == null ? "": req.getParameter("arNo");
			
			String flowBranch = (String) session.getAttribute("AdminBranchCode"); 
			bc.setFlowBranch(flowBranch);
			
			bc.setTitle(title);
			bc.setFirstName(firstName);
			bc.setGender(gender);
			bc.setLastName(lastName);
			bc.setNationality(nationality);
			// bc.setBrokerDate(brokerDate);
			bc.setTelephone(telephone);
			bc.setMobile(mobile);
			bc.setFax(fax);
			bc.setEmail(email);
			bc.setAddress1(address1);
			bc.setAddress2(address2);
			bc.setOccupation(occupation);
			bc.setEmirate(emirate);
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
			bc.setBCode(bcode);
			bc.setRsa_broker_code(rsa_broker_code);
			bc.setMCode(mcode);
			bc.setApprovedby(approvedby);
			bc.setBOrganization(borganization);
			bc.setCity(city);
			bc.setBranch(branch);
			bc.setSno(sno);
			bc.setEno(eno);
			bc.setExecutiveId(executiveId);
			bc.setBrokerName(brokerName);
			bc.setArNo(arNo);

			String error = bc.validate();
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BrokerCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				String process = bc.insertBrokerEntry();
				if (process.equalsIgnoreCase("onetwo"))
				{
					req.setAttribute("bcode", bcode);
					req.setAttribute("loginPersonId", loginPersonId);
					req.setAttribute("borganization", borganization);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/commissionforBroker.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		// / Start For BankMaster  ////
		else if (reqFrom.equalsIgnoreCase("BankMaster")) {

			
			String bankName = req.getParameter("IdList") == null ? "" : req.getParameter("IdList");
			String belongingCountry = ""; 
            belongingCountry = (String)session.getAttribute("AdminCountryId");
			bc.setBankName(bankName);
			bc.setBelongingCountry(belongingCountry);
			
			String bankError = bc.validateBankMaster123();
			if (bankError.length() > 0)
			{
				req.setAttribute("error", bankError);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BankMasterCombo.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} 
			else
			{
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BankMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			
			}
		} 

		else if (reqFrom.equalsIgnoreCase("BankMasterDetails")) 
		{
			String bankError = "";
			String mode = req.getParameter("mode");
			mode = mode == null ? "" : mode;
			String bankId = req.getParameter("bankId") == null?"":req.getParameter("bankId");
			String bankName = req.getParameter("bankName") == null?"":req.getParameter("bankName");
			String effectDate = req.getParameter("effectDate")==null?"":req.getParameter("effectDate");
			String remarkText = req.getParameter("remarkText")==null?"":req.getParameter("remarkText");
			String status = req.getParameter("status")==null?"":req.getParameter("status");
			String IdList = req.getParameter("IdList")==null?"":req.getParameter("IdList");
			String rsaCode = req.getParameter("rsaCode")==null?"":req.getParameter("rsaCode");
            String belongingCountry = ""; //req.getParameter("BelongingCountry");
            belongingCountry = (String)session.getAttribute("AdminCountryId");
            System.out.println("Country Id from session-->"+belongingCountry);
            belongingCountry = (belongingCountry==null || belongingCountry.equalsIgnoreCase("null") || belongingCountry.equalsIgnoreCase("select"))?"":belongingCountry; 
			
			bc.setRsaCode(rsaCode);
			bc.setBankId(bankId);
			bc.setBankName(bankName);
			bc.setEffectDate(effectDate);
			bc.setRemarkText(remarkText);
			bc.setStatus(status);
            bc.setBelongingCountry(belongingCountry);
			// bc.setRsaCode(rsacode); 
			if(mode.equalsIgnoreCase("add"))
				 bankError = bc.validateBankMaster();

			if(bankError.length() > 0) 
			{	
				req.setAttribute("error", bankError);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BankMaster.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {

				String ins_res = null;
				try {	
					ins_res = bc.insertBankMasterData();
				} catch (Exception e) {
					System.out.println("Insertion cont error" + e.toString());
					System.out.println("Insertion cont error" + e.getMessage());

				}
				if (ins_res.equalsIgnoreCase("Yes")) {
					String statusYes = "Record Is Inserted Successfully";
					req.setAttribute("status", statusYes);
					req.setAttribute("from", "BankMaster");
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/BankConfirmation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {
					String statusNo = "Sorry!!! Your record is not inserted, Please try again with valid datas";
					req.setAttribute("status", statusNo);
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/BankConfirmation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}

			}

		}
		// / End for Bank Master  ///
		// OfficeShield
		else if (reqFrom.equalsIgnoreCase("commisionforOfficeScheme")) 
		{
			String userid = "" + session.getAttribute("userid");
			String adminPid = (String)session.getAttribute("AdminPid");
			String adminBranch = "";
			adminBranch = (String)session.getAttribute("AdminBranchCode");
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			HashMap schemeDetails = new HashMap();
			
			HashMap pros = new HashMap();
			String commision = "";
			String suminsured = "";
			String premium = "";
			String loading = "";
			String discount = "";
			String bday = "";
			int s = 0;
			String officeScheme[][] = new String[0][0];
			officeScheme = bc.getOfficeProductScheme(adminBranch);
			for(int i=0;i<officeScheme.length;i++)
			{
				if (req.getParameter("product"+officeScheme[i][0]) != null) 
				{
					HashMap schemeDetails1 = new HashMap();
					commision = req.getParameter("commision"+ officeScheme[i][0]);
					suminsured = req.getParameter("suminsured"+ officeScheme[i][0]);
					premium = req.getParameter("premium"+ officeScheme[i][0]);
					loading = req.getParameter("loading"+ officeScheme[i][0]);
					discount = req.getParameter("discount"+ officeScheme[i][0]);
					bday = req.getParameter("bday"+ officeScheme[i][0]);
					
					commision = commision == null ? "" : commision;
					suminsured = suminsured == null ? "" : suminsured;
					premium = premium == null ? "" : premium;
					loading = loading == null ? "" : loading;
					discount = discount == null ? "" : discount;
					bday = bday == null ? "" : bday;

					/*schemeDetails.put("scheme"+(s+1),officeScheme[i][0]);
					schemeDetails.put("commision"+(s+1),commision);
					schemeDetails.put("suminsured"+(s+1),suminsured);
					schemeDetails.put("premium"+(s+1),premium);
					schemeDetails.put("discount"+(s+1),discount);
					schemeDetails.put("loading"+(s+1),loading);
					schemeDetails.put("bday"+(s+1),bday);
					pros.put("productsdetails"+(s+1),schemeDetails);
					s++;*/
					
					String schemeId = officeScheme[i][0];
					schemeDetails.put("scheme"+s,"s"+schemeId);
					schemeDetails.put("product"+s,"30"+schemeId);
					
					schemeDetails.put("commision"+schemeId,commision);
					schemeDetails.put("suminsured"+schemeId,suminsured);
					schemeDetails.put("premium"+schemeId,premium);
					schemeDetails.put("discount"+schemeId,discount);
					schemeDetails.put("loading"+schemeId,loading);
					schemeDetails.put("bday"+schemeId,bday);
					
					schemeDetails1.put("product"+s,"30"+schemeId);
					schemeDetails1.put("commision"+s,commision);
					schemeDetails1.put("suminsured"+s,suminsured);
					schemeDetails1.put("premium"+s,premium);
					schemeDetails1.put("discount"+s,discount);
					schemeDetails1.put("loading"+s,loading);
					schemeDetails1.put("bday"+s,bday);
					
					pros.put("productsdetails"+s, schemeDetails1);
					s++;
				}
			}
			
			schemeDetails.put("OSLength",""+s);
			if(session.getAttribute("schemeDetails")!=null)
			{
				session.removeAttribute("schemeDetails");
				session.setAttribute("schemeDetails",schemeDetails);
			}
			else
				session.setAttribute("schemeDetails",schemeDetails);
			bc.setProDetails(pros);
			String error = "";
			error = bc.validateCommisionOffice(adminBranch);
			
			if (error.length() > 0) 
			{
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/OfficeSchemePopUp.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else
			{
				HashMap senHash = new HashMap();
				senHash = (HashMap) session.getAttribute("schemeDetails");
				
				res.setContentType("text/html");
				out = res.getWriter();
				out.println("<HTML>");
				out.println("<HEAD><script type=\"text/javascript\">function fnSubmit(){window.close();}</script></HEAD>");
				out.println("<BODY onload=\"fnSubmit()\">");
				out.println("</BODY>");
				out.println("</HTML>");
				out.flush();
				out.close();
			}
		}

		else if (reqFrom.equalsIgnoreCase("commisionforBroker")) 
		{
			String[][] bdetails = new String[0][0];
			String userid = "" + session.getAttribute("userid");
			String adminPid = (String)session.getAttribute("AdminPid");
			String adminBranch = "";
			adminBranch = (String)session.getAttribute("AdminBranchCode");
			bdetails = bc.getProducts(adminPid,adminBranch);

			HashMap officeHash = new HashMap();
			Map usrAgencyPdtMap = new HashMap();
			if(session.getAttribute("schemeDetails")!=null)
				officeHash = (HashMap)session.getAttribute("schemeDetails");
			if(officeHash != null && officeHash.size() > 0 )
				bc.setOfficeHash(officeHash);
			String officeScheme[][] = new String[0][0];
			//officeScheme = bc.getOfficeProductScheme(adminBranch);

			HashMap productsdetails = new HashMap();
			HashMap pros = new HashMap();
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			loginPersonId = req.getParameter("loginPersonId") == null ?"": req.getParameter("loginPersonId");
			String productsLength = "";
			productsLength = req.getParameter("productsLength") == null ? "0": req.getParameter("productsLength");
			int j = 1;boolean offFlag = false;
			for (int i = 0; i < (Integer.parseInt(productsLength)); i++) 
			{
				if (req.getParameter("product" + bdetails[i][0]) != null) 
				{
					String prod = "";
					prod = req.getParameter("product"+ bdetails[i][0]) == null ? "" : req.getParameter("product" + bdetails[i][0]);
					if(prod.equals("30"))
					{
						if(session.getAttribute("schemeDetails")==null)
						{
							officeHash = bc.getExistingOfficeDetails(bcode);
							if(officeHash != null && officeHash.size() > 0 )
							{
								bc.setOfficeHash(officeHash);
								session.setAttribute("schemeDetails",officeHash);
							}
							else
								offFlag = true;
							
						}
						for(int s=0;s<officeScheme.length;s++)
						{
							String OFSCH = "";
							OFSCH = officeScheme[s][0];
							OFSCH = OFSCH == null ? "" :OFSCH ;
							
							if(officeHash.size() > 0 )
							{
								if(officeHash.containsValue("s"+officeScheme[s][0]))
								{
									productsdetails.put("product"+j,(prod+officeScheme[s][0]));								
																		
									productsdetails.put("user" + j, req.getParameter("user" + j) == null ? "" : req.getParameter("user" + j));
									productsdetails.put("account" + j, req.getParameter("account" + j) == null ? "" : req					.getParameter("account" + j));
									productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req						.getParameter("referral" + j));
									productsdetails.put("payReceipt" + j, req.getParameter("payReceipt" + bdetails[i][0]) == null ? "N" : req.getParameter("payReceipt" + bdetails[i][0]));
									productsdetails.put("debit" + j, req.getParameter("debit"+bdetails[i][0])==null ?"N" :req.getParameter("debit" + bdetails[i][0]));
									productsdetails.put("loadDis" + j, req.getParameter("loadDis"+bdetails[i][0])==null ?"N" :req.getParameter("loadDis" + bdetails[i][0]));
								
									String proCommission = req.getParameter("proCommission" + j) == null ? "0" : req.getParameter("proCommission" + j);
									proCommission = proCommission==null?"":proCommission;
	
									productsdetails.put("proCommission" + j, proCommission);
									String sd = req.getParameter("dobDay" + j) == null ? "" : req.getParameter("dobDay" + j);
									String sm = req.getParameter("dobMonth" + j) == null ? "" : req.getParameter("dobMonth" + j);
									String sy = req.getParameter("dobYear" + j) == null ? "" : req.getParameter("dobYear" + j);
									String sd1 = req.getParameter("dobDay1" + j) == null ? "" : req.getParameter("dobDay1" + j);
									String sm1 = req.getParameter("dobMonth1" + j) == null ? "" : req.getParameter("dobMonth1" + j);
									String sy1 = req.getParameter("dobYear1" + j) == null ? "" : req.getParameter("dobYear1" + j);
	
									String sdate = "";
									if(sd.length() > 0)
										sdate = sd+"-"+sm+"-"+sy;
									
									String edate = "";
									if(sd1.length() > 0)
										edate = sd1+"-"+sm1+"-"+sy1;
									
									sdate = sdate!=null?sdate:"";
									edate = edate!=null?edate:"";
									productsdetails.put("sdate" + j, sdate);
									productsdetails.put("edate" + j, edate);
									pros.put("productsdetails" + j, productsdetails);
									j++;
								}
							}
						}
					}
					else
					{
						
						productsdetails.put("product" + j,prod);

						productsdetails.put("commision" + j, req.getParameter("commision"+ bdetails[i][0]) == null ? "" : req.getParameter("commision" + bdetails[i][0]));
						productsdetails.put("suminsured" + j,req.getParameter("suminsured"+bdetails[i][0]) == null ? "":req.getParameter("suminsured"+bdetails[i][0]));
						productsdetails.put("premium" + j, req.getParameter("premium"+ bdetails[i][0]) == null ? "" : req	.getParameter("premium" + bdetails[i][0]));
						productsdetails.put("discount" + j, req.getParameter("discount"+ bdetails[i][0]) == null ? "" : req.getParameter("discount" + bdetails[i][0]));
						productsdetails.put("loading" + j, req.getParameter("loading"+ bdetails[i][0]) == null ? "" : req.getParameter("loading" + bdetails[i][0]));
						productsdetails.put("bday" + j, req.getParameter("bday"+bdetails[i][0]) == null ? "0" : req.getParameter("bday" + bdetails[i][0]));
					
						productsdetails.put("user" + j, req.getParameter("user" + j) == null ? "" : req.getParameter("user" + j));
						productsdetails.put("account" + j, req.getParameter("account" + j) == null ? "" : req.getParameter("account" + j));
						productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req.getParameter("referral" + j));
						productsdetails.put("payReceipt" + j, req.getParameter("payReceipt" + bdetails[i][0]) == null ? "N" : req.getParameter("payReceipt" + bdetails[i][0]));
						productsdetails.put("debit" + j, req.getParameter("debit"+bdetails[i][0])==null ?"N" :req.getParameter("debit" + bdetails[i][0]));
						if("11".equalsIgnoreCase(prod)){
							productsdetails.put("debit" + j, req.getParameter("debit3")==null ?"N" :req.getParameter("debit3"));
						}
						productsdetails.put("loadDis" + j, req.getParameter("loadDis"+bdetails[i][0])==null ?"N" :req.getParameter("loadDis" + bdetails[i][0]));
						
						String proCommission = req.getParameter("proCommission" + j) == null ? "0" : req.getParameter("proCommission" + j);
						proCommission = proCommission==null?"":proCommission;

						productsdetails.put("proCommission" + j, proCommission);
						String sd = req.getParameter("dobDay" + j) == null ? "" : req.getParameter("dobDay" + j);
						String sm = req.getParameter("dobMonth" + j) == null ? "" : req.getParameter("dobMonth" + j);
						String sy = req.getParameter("dobYear" + j) == null ? "" : req.getParameter("dobYear" + j);
						String sd1 = req.getParameter("dobDay1" + j) == null ? "" : req.getParameter("dobDay1" + j);
						String sm1 = req.getParameter("dobMonth1" + j) == null ? "" : req.getParameter("dobMonth1" + j);
						String sy1 = req.getParameter("dobYear1" + j) == null ? "" : req.getParameter("dobYear1" + j);

						String sdate = "";
						if(sd.length() > 0)
							sdate = sd+"-"+sm+"-"+sy;
						
						String edate = "";
						if(sd1.length() > 0)
							edate = sd1+"-"+sm1+"-"+sy1;
						
						String userAgencyList = req.getParameter("userAgencyList" + prod) == null ? "" : req.getParameter("userAgencyList" + prod);
						usrAgencyPdtMap.put("PROD"+prod, userAgencyList);
						System.out.println("userAgencyList..............."+userAgencyList);
						sdate = sdate!=null?sdate:"";
						edate = edate!=null?edate:"";
						productsdetails.put("sdate" + j, sdate);
						productsdetails.put("edate" + j, edate);
						pros.put("productsdetails" + j, productsdetails);
						j++;
					}
			     } 
				 else {
					// i++;
				}
			}
			bc.setUsrAgencyPdtMap(usrAgencyPdtMap);
			bc.setProDetails(pros);
			String error = "";
			error = bc.validateCommision();
			if(offFlag)
				error = error+"<br>* Please select any one of E-Office Scheme to get E-Office product";
			if (error.length() > 0) 
			{
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/commissionforBroker.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else 
			{
				String status = bc.insertOrUpdate(bcode, loginPersonId); 
				String status12 = bc.LoginIdStatus(bcode);
				if (status12 == null || status12.equals("")	|| status12.equalsIgnoreCase("null")|| status12.equalsIgnoreCase("NONE")) 
				{
					req.setAttribute("bcode", bcode);
					req.setAttribute("loginPersonId", loginPersonId);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/BrokerLoginCreation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} 
				else
				{
					req.setAttribute("bcode", bcode);
					req.setAttribute("loginPersonId", loginPersonId);
					bc.pdtAssignToUsr(bcode);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/NewBrokerConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		} 
		
		else if (reqFrom.equalsIgnoreCase("BrokerDeactivation")) 
		{
			String[][] brokerDetails = new String[0][0];
			String brokerId = req.getParameter("brokerId") == null ? "" : req.getParameter("brokerId");
			String status = req.getParameter("radiobutton") == null ? "" : req.getParameter("radiobutton");

			String error = "";
			if (brokerId.equals("")) 
			{

				error = "Enter Broker Id";
			}
			else if (!bc.checkCode(brokerId)) 
			{
				error = "Enter Valid BrokerId";
			}

			if (status.length() > 0) 
			{
				//if (bc.updateStatus(status, brokerId, con)) {
					if (bc.updateStatus(status, brokerId)) 
					{

					System.out.println("DATE"+com.maan.common.util.OracleDateConversion	.ConvertDate(""+simpleFormatter.format(dd)));
					deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
					req.setAttribute("status", status);
					req.setAttribute("deActivatedDate", deActivatedDate);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/conformDeactivate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {
					req.setAttribute("error", error);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/deActivation1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			} else {

				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/deActivation1.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}

		}

		else if (reqFrom.equalsIgnoreCase("user1")) 
		{
			String selectedBroker = req.getParameter("selectbroker") == null ? "": req.getParameter("selectbroker");
			String status = req.getParameter("brokerStatus") == null ? "": req.getParameter("brokerStatus");
				if(bc.updateStatus(status,selectedBroker))
				{
					deActivatedDate = OracleDateConversion.ConvertDate(""+simpleFormatter.format(dd));
					req.setAttribute("status", status);
					req.setAttribute("deActivatedDate", deActivatedDate);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/conformDeactivate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} 
				else
				{
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/users1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
		} 


		else if (reqFrom.equalsIgnoreCase("RSAusers1")) 
		{
			
			String selectedBroker = req.getParameter("selectbroker") == null ? "": req.getParameter("selectbroker");
			String status = req.getParameter("brokerStatus") == null ? "": req.getParameter("brokerStatus");
			
				boolean result= bc.updateStatus(status, selectedBroker,"RoyalRSA");

				if (result)
				{
					deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));

					req.setAttribute("status", status);
					req.setAttribute("deActivatedDate", deActivatedDate);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/RSAconformDeactivate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} 
				else
				{
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/RSAusers1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
		}



		else if (reqFrom.equalsIgnoreCase("ExchangeRate")) 
		{
			String adminCountryId ="";
			adminCountryId = (String)session.getAttribute("AdminCountryId"); 
			String exchangeRate = req.getParameter("exchangeRate") == null ? "": req.getParameter("exchangeRate");
			String effectDate = req.getParameter("effectDate") == null ? "": req.getParameter("effectDate");
			String currencyType = req.getParameter("currencyType") == null ? "": req.getParameter("currencyType");
			String remarkText = req.getParameter("remarkText") == null ? "": req.getParameter("remarkText");
			String status = req.getParameter("status") == null ? "" : req.getParameter("status");
			String rsacode = req.getParameter("rsacode") == null ? "" : req	.getParameter("rsacode"); // marimtuhu

			bc.setExchangeRate(exchangeRate);
			bc.setEffectDate(effectDate);
			bc.setCurrencyType(currencyType);
			bc.setRemarkText(remarkText);
			bc.setStatus(status);
			bc.setRsaCode(rsacode); 
			bc.setadminCountryId(adminCountryId); 
			
//			 Effective Dtae Validation
			String branch = (String) session.getAttribute("LoginBranchCode");
			bc.setBranch(branch);
			
			String error = bc.validateExchange();

			if (error.length() > 0) 
			{
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/Exchange_Rate.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else 
			{
				// bc.setLoginPersonId(loginPersonId);
				String process = bc.insertExchangeData();
				if (process.equalsIgnoreCase("YES")) 
				{
					req.setAttribute("from", "exchange");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}

		} 
		else if (reqFrom.equalsIgnoreCase("Currency")) 
		{
			String adminCountryId ="";
			adminCountryId = (String)session.getAttribute("AdminCountryId"); 
			String remarkText = req.getParameter("remarkText") == null ? "": req.getParameter("remarkText");
			String status = req.getParameter("status") == null ? "" : req.getParameter("status");
			String effectDate = req.getParameter("effectDate") == null ? "": req.getParameter("effectDate");
			String currencyType = req.getParameter("currencyType") == null ? "": req.getParameter("currencyType");
			String currencyNo = req.getParameter("currencyNo") == null ? "": req.getParameter("currencyNo");
			String rfactor = req.getParameter("rfactor") == null ? "": req.getParameter("rfactor");
			String subcurrency = req.getParameter("subcurrency") == null ? "": req.getParameter("subcurrency");
			String shortname = req.getParameter("shortname") == null ? "": req.getParameter("shortname");
			String rsacode = req.getParameter("rsacode") == null ? "" : req.getParameter("rsacode"); // marimuthu
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode"); // marimuthu
			
			out.println("Currency BrokerCreationController "+ effectDate);
			out.println("Currency BrokerCreationController "+ currencyType);
			out.println("Currency BrokerCreationController "+ currencyNo);

			bc.setEffectDate(effectDate);
			bc.setCurrencyType(currencyType);
			bc.setCurrencyNo(currencyNo);
			bc.setRemarkText(remarkText);
			bc.setStatus(status);
			bc.setRsaCode(rsacode); 
			bc.setRfactor(rfactor); 
			bc.setSubcurrency(subcurrency); 
			bc.setShortname(shortname); 
			bc.setadminCountryId(adminCountryId); 

			String error = bc.validateCurrencyType(mode);

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/Currency.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				// bc.setLoginPersonId(loginPersonId);
				String process = bc.insertCurrencyData();
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("from", "exchange");
					RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		
		/***/
		else if (reqFrom.equalsIgnoreCase("CurrencySelection")) 
		{
			String adminCountryId ="";
			String error ="";
			adminCountryId = (String)session.getAttribute("AdminCountryId"); 
			String currencyNo = req.getParameter("currencyNo")==null?"":req.getParameter("currencyNo");
			if(currencyNo.trim().equalsIgnoreCase("0")||currencyNo==null)
				error="Please Select The CurrencyType";
			if (error.length() > 0) 
			{
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/CurrencySelection.jsp");
				if (dispatcher != null)
				dispatcher.forward(req, res);
			}
			else
			{
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/Currency.jsp");
				if (dispatcher != null)
				dispatcher.forward(req, res);
			}
		}
		/***/

		else if (reqFrom.equalsIgnoreCase("cutomerlist")) 
		{
			String indexvalue = req.getParameter("indexvalue") == null ? ""	: req.getParameter("indexvalue");
			String customername = req.getParameter("customername") == null ? ""	: req.getParameter("customername");
			if (indexvalue != null && indexvalue.length() > 0 && !indexvalue.equals("")) 
			{
				// ab.getCostomerData(indexvalue);
			}
			else if (customername != null && customername.length() > 0 && !customername.equals("")) 
			{

			} 
			else {
				req.setAttribute("error", "Please Enter CustomerName");
				RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/CustomerList.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
		}
		else if (reqFrom.equalsIgnoreCase("BrokerLoginCreation")) 
		{
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			loginPersonId = req.getParameter("loginPersonId") == null ? "": req.getParameter("loginPersonId");
			String brokerId = req.getParameter("BrokerId") == null ? "" : req.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "":req.getParameter("RetypePassword");
			bc.setBrokerId(brokerId);
			bc.setPassword(password);
			bc.setRetypePassword(retypePassword);

			// session.setAttribute("brokerId",brokerId);

			String error = bc.validateLoginCreation();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/admin/BrokerLoginCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {

				bc.setLoginPersonId(loginPersonId);

				String process = bc.insertBrokerLogin(bcode, loginPersonId);
				// Rajesh Common DB
				// String
				// process=bc.insertBrokerLogin(bcode,loginPersonId,usrModeSC);
				if (process.equalsIgnoreCase("123")) {
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/admin/commissionconformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		} 
		else if (reqFrom.trim().equalsIgnoreCase("policy_search"))
		{
			try {
				String error = "";
				String values = null;
				String[][] right = new String[0][0];

				RequestDispatcher rd = null;

				String comp = req.getParameter("policy_no");

				// String rep1=req.getParameter("rep");

				out.println("<br>policy no is" + comp);
				req.setAttribute("sts", "policy_search");
				req.setAttribute("policy_no", comp);
				req.setAttribute("search_option", "policy");
				rd = req.getRequestDispatcher("Portfolio_ByPolicy.jsp");

				out.println("<br>readio  beforeeeeeeeeeebutton value is");
				out.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"+ rd);
				out.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"+ rd);
				out.println("<br>RequestDispatcher rd>>>>>>>>>>>>>>>>>>>>.s"+ rd);
				rd.forward(req, res);

				if (true)
					return;
				// out.println("in
				// controller>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+error);
				if (true)
					return;

			} catch (Exception e) {
				System.out.println("SKR TEST START " + e);
			}

		} 
		//rajesh modified on 16/05
		else if (reqFrom.trim().equalsIgnoreCase("policyCancel")) 
		{
			String nlens = req.getParameter("normalLen")!=null?req.getParameter("normalLen"):"0";
			String mlens = req.getParameter("multipleLen")!=null?req.getParameter("multipleLen"):"0";
			String status="";
			com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
			int nlen = Integer.parseInt(nlens);
			int mlen = Integer.parseInt(mlens);

			for(int l=0;l<nlen;l++)
			{
				status="";
				String cps = req.getParameter("active"+l);
				cps = cps==null?"":cps;
				
				String npno = req.getParameter("CancelPNO"+l);
				npno = npno==null?"":npno;
				if(cps.length()>0)
					status = cps;
				
				if(status.length()>0 && status.equalsIgnoreCase("D"))
						cc.updatePolicyStatus(npno,status);

			}
			
			for(int l=0;l<mlen;l++)
			{
				status="";
				String cps = req.getParameter("mactive"+l);
				cps = cps==null?"":cps;
				String mpno = req.getParameter("mCancelPNO"+l);
				mpno = mpno==null?"":mpno;
				if(cps.length()>0)
					status = cps;
				if(status.length()>0 && status.equalsIgnoreCase("D"))
						cc.updatePolicyStatus(mpno,status);
			}
			RequestDispatcher rd = null;
			rd = req.getRequestDispatcher("conformPolicyDeactivate.jsp");
			rd.forward(req, res);
			if (true)
				return;
		}
		else if (reqFrom.trim().equalsIgnoreCase("portfolio")) 
		{
				try {
					String error = "";
					String values = null;
					// String data1 = null;
					// String data2 = null;
					// String[][] values1=new String[500][500];
					String[][] right = new String[0][0];

					RequestDispatcher rd = null;
					//Rajesh Modified on 12/05
				String royalRep = req.getParameter("rep") == null ? "" : req
						.getParameter("rep");
				if(royalRep.equalsIgnoreCase("pc"))
				{
					royalRep = "p";
					req.setAttribute("PolicyCancel", "Yes");
				}
				com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req	.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req	.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""	: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req.getParameter("dobYear1"));
				cc.setCompany(req.getParameter("company") == null ? "" : req.getParameter("company"));
				cc.setRep(royalRep);
				cc.setData1(req.getParameter("dobDay") + "-"	+ req.getParameter("dobMonth") + "-"+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-"+ req.getParameter("dobYear1"));
				String print123 = req.getParameter("print123") == null ? "": req.getParameter("print123");
				String data1 = (req.getParameter("dobDay") + "-"	+ req.getParameter("dobMonth") + "-" + req	.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"+ req.getParameter("dobMonth1") + "-" + req.getParameter("dobYear1"));
				String productId = req.getParameter("pid") == null ? "" : req.getParameter("pid");

				if (!print123.equalsIgnoreCase("YES")) {
					error = cc.validateTrashFields();
				} else {
					data1 = req.getParameter("data1") == null ? "" : req
							.getParameter("data1");
					data2 = req.getParameter("data2") == null ? "" : req
							.getParameter("data2");
				}
				String comp = req.getParameter("company");
				String rep1 = royalRep;

				if (error.length() > 0) {
					out.println("in controller>>>>>>"+ error.length());
					req.setAttribute("errorDetail", error);
					rd = req.getRequestDispatcher("AdminPortfolio.jsp");
				}
				else 
				{
					req.setAttribute("data1", data1);
					req.setAttribute("data2", data2);
					req.setAttribute("rep1", rep1);
					req.setAttribute("pid", productId);
						
					rd = req.getRequestDispatcher("Approved_Policy_Portfolio.jsp");
					
				}
				
				
				rd.forward(req, res);
				if (true)
					return;
				
				if (true)
					return;
			} catch (Exception e) {
				System.out.println("SKR TEST START " + e);
			}

		} 
		else if (reqFrom.equalsIgnoreCase("reports")) 
		{
			try {
				String individual = "";
				String error = "";
				String[][] right = new String[0][0];
				RequestDispatcher rd = null;

				com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req
						.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req
						.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req
						.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req
						.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""
						: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req
						.getParameter("dobYear1"));
				cc.setCompany(req.getParameter("company") == null ? "" : req
						.getParameter("company"));
				cc.setRep(req.getParameter("rep") == null ? "" : req
						.getParameter("rep"));
				cc.setData1(req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-"
						+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-"
						+ req.getParameter("dobYear1"));
				String print123 = req.getParameter("print123") == null ? ""
						: req.getParameter("print123");
				String data1 = (req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-" + req
						.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-" + req
						.getParameter("dobYear1"));
				String productId = req.getParameter("pid") == null ? "" : req.getParameter("pid");
				String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
				
				cc.setUserType(userType);
				
				if (!print123.equalsIgnoreCase("YES")) 
				{
					error = cc.validateFields(reqFrom);
				} 
				else 
				{
					data1 = req.getParameter("data1") == null ? "" : req.getParameter("data1");
					data2 = req.getParameter("data2") == null ? "" : req.getParameter("data2");
				}

				String comp = req.getParameter("company");
				String rep1 = req.getParameter("rep");
				System.out.println("error" + error);
				out.println("in controller>>>>>");

				if (error.length() > 0) 
				{
					out.println("in controller>>>>"+ error.length());
					req.setAttribute("errorDetail", error);
					rd = req.getRequestDispatcher("policyreport.jsp");
				} 
				else 
				{
					String customername = req.getParameter("rep");
					System.out.println("customer name value is" + customername);

					if ("c".equalsIgnoreCase(customername)) 
					{
						right = cc.customervalues(comp);
						req.setAttribute("wrong", right);
					} 
					
					if (customername.equals("p")) 
					{
						right = cc.policyvalues(comp);
						req.setAttribute("wrong", right);
					} 

					req.setAttribute("data1", data1);
					req.setAttribute("data2", data2);
					req.setAttribute("rep1", rep1);
					req.setAttribute("pid", productId);
					req.setAttribute("company", comp);

					if (print123.equalsIgnoreCase("YES")) 
					{
						rd = req.getRequestDispatcher("report123_print.jsp");
					} 
					else 
					{
						String brokerLogin = "", RSAIssuer="";
						brokerLogin = req.getParameter("selectBrokers") == null ? "" : req.getParameter("selectBrokers");
						System.out.println("Broker Login ......"+brokerLogin);
						
						if(userType.equalsIgnoreCase("RSAIssuer")){
							RSAIssuer = brokerLogin;
						}
						
						if (!"All".equalsIgnoreCase(brokerLogin))
						{
							individual = cc.getBrokerAgencyCodeFromLogin(brokerLogin);
							req.setAttribute("individual",individual);
							rd = req.getRequestDispatcher("report123.jsp?RSAIssuer="+RSAIssuer);
						}
						else
							rd = req.getRequestDispatcher("report123.jsp?RSAIssuer="+RSAIssuer);

						/*if ("All".equalsIgnoreCase(req.getParameter("selectBrokers")))
							rd = req.getRequestDispatcher("report123.jsp");
						else
							rd = req.getRequestDispatcher("report1.jsp");*/
					}
				}

				rd.forward(req, res);
				if (true)
					return;
			} 
			catch (Exception e) 
			{
				System.out.println("SKR TEST START " + e);
				e.printStackTrace();
			}
		}
		else if (reqFrom.equalsIgnoreCase("reports1")) 
		{
			try {
				String login_idx = (String) session.getAttribute("user");
				String error = "";
				String[][] right = new String[0][0];
				java.util.HashMap right123 = new java.util.HashMap();
				RequestDispatcher rd = null;
				com.maan.admin.AdminBean cc = new com.maan.admin.AdminBean();
				cc.setDobDay(req.getParameter("dobDay") == null ? "" : req
						.getParameter("dobDay"));
				cc.setDobMonth(req.getParameter("dobMonth") == null ? "" : req
						.getParameter("dobMonth"));
				cc.setDobYear(req.getParameter("dobYear") == null ? "" : req
						.getParameter("dobYear"));
				cc.setDobDay1(req.getParameter("dobDay1") == null ? "" : req
						.getParameter("dobDay1"));
				cc.setDobMonth1(req.getParameter("dobMonth1") == null ? ""
						: req.getParameter("dobMonth1"));
				cc.setDobYear1(req.getParameter("dobYear1") == null ? "" : req
						.getParameter("dobYear1"));
				cc.setCompany(req.getParameter("company") == null ? "" : req
						.getParameter("company"));
				cc.setRep(req.getParameter("rep") == null ? "" : req
						.getParameter("rep"));
				cc.setData1(req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-"
						+ req.getParameter("dobYear"));
				cc.setData2(req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-"
						+ req.getParameter("dobYear1"));

				String print123 = req.getParameter("print123") == null ? ""
						: req.getParameter("print123");
				String data1 = (req.getParameter("dobDay") + "-"
						+ req.getParameter("dobMonth") + "-" + req
						.getParameter("dobYear"));
				String data2 = (req.getParameter("dobDay1") + "-"
						+ req.getParameter("dobMonth1") + "-" + req
						.getParameter("dobYear1"));
				String data3 = (req.getParameter("selectBrokers")
						.equalsIgnoreCase("All") ? "" + login_idx : req
						.getParameter("selectBrokers"));
				cc.setData3(data3);
				session.setAttribute("Brokers", data3);
				if (!print123.equalsIgnoreCase("YES")) {
					error = cc.validateFields(reqFrom);
				} else {
					data1 = req.getParameter("data1") == null ? "" : req
							.getParameter("data1");
					data2 = req.getParameter("data2") == null ? "" : req
							.getParameter("data2");
					data3 = (req.getParameter("selectBrokers"));
				}
				String comp = req.getParameter("company");
				String rep1 = req.getParameter("rep");
				if (error.length() > 0) {
					req.setAttribute("errorDetail", error);
					if (reqFrom.equalsIgnoreCase(
							"reports1")) {
						rd = req.getRequestDispatcher("finalreports.jsp");
					} else {
						rd = req.getRequestDispatcher("finalreports.jsp");
					}

				} else {

					String customername = req.getParameter("rep");
				
					if ("c".equalsIgnoreCase(customername)) {
						
						right = cc.customervalues(comp);
						
						
						req.setAttribute("wrong", right);
					} else if (customername.equals("p")) {
						
						right = cc.policyvalues(comp);
						
						
						req.setAttribute("wrong", right);
					} else {
						
						right123 = cc.checkingvalues1234(data1, data2,loginPersonId);
						req.setAttribute("wrong", right123);
					}

					req.setAttribute("data1", data1);
					req.setAttribute("data2", data2);
					req.setAttribute("rep1", rep1);
					req.setAttribute("company", comp);

					String product_id = "";
					if (session.getAttribute("product_id") != null) {

						product_id = (String) session
								.getAttribute("product_id");
					}
					if (print123.equalsIgnoreCase("YES")) {
						rd = req.getRequestDispatcher("finalreport123_print.jsp");
					} 
					/*else {
						if ("All".equalsIgnoreCase(req.getParameter("selectBrokers"))) 
						{
							rd = req.getRequestDispatcher("finalreport123.jsp");
						}*/ 
						else
						{
							rd = req.getRequestDispatcher("finalreport2.jsp");
						}
						//rd = req.getRequestDispatcher("finalreport123.jsp");
					//}
				}
				rd.forward(req, res);
			} catch (Exception e) {
				System.out.println("SKR TEST START  This is add xxxxxxxxx " + e);
				e.printStackTrace();
			}
		} 
		/*else if (reqFrom.equalsIgnoreCase("RAllowed"))
		{
			RequestDispatcher rd = null;
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			String brokerBra = req.getParameter("brokerBra") == null ? "" : req.getParameter("brokerBra");
			String pid = req.getParameter("pid") == null ? "" : req.getParameter("pid");
			String[][] refferals = bc.getRefferals(brokerBra);

			String ref = "";
			for (int i = 0; i < refferals.length; i++) {
				if (req.getParameter("refferals" + (i + 1)) != null) 
				{
					ref = ref + "," + req.getParameter("refferals" + (i + 1));
					System.out.println("ref data issssssssssssssssss" + ref);
				}
			}
			if (ref.length() > 0) {
				ref = ref.substring(1, ref.length());
			}
			if (ref.length() > 0 && ref != null) {
				bc.insertRAllowed(bcode, pid, ref);
			} 
			else 
			{
				ref = "0";
				bc.insertRAllowed(bcode, pid, ref);
			}
			rd = req.getRequestDispatcher("RefferalAllowedMessage.jsp");
			rd.forward(req, res);
			System.out.println("#:::Referral is:::#:" + ref);
		}*/
		else if (reqFrom.equalsIgnoreCase("Brokersreport"))
		{
			com.maan.admin.BrokerCreationBean cc = new com.maan.admin.BrokerCreationBean();
			String broAgency = req.getParameter("selectBrokers");
			broAgency = broAgency==null?"":broAgency;
			HashMap finalList = new HashMap();
			if(broAgency.equalsIgnoreCase("All"))
			{
				String allAgency = req.getParameter("allAgency");
				allAgency = allAgency==null?"":allAgency;
				req.setAttribute("allAgency",allAgency);
				finalList = cc.getBrokersClientList(allAgency);
				req.setAttribute("finalList",finalList);
			}
			else
			{
				String broAgencyCode = "'"+broAgency+"'";
				finalList = cc.getBrokersClientList(broAgencyCode);
				req.setAttribute("finalList",finalList);
			}
			RequestDispatcher brord = req.getRequestDispatcher("BrokersDetailsReports.jsp");
			if(brord!=null)
				brord.forward(req,res);
		}

		else if (reqFrom.equalsIgnoreCase("newuser"))
		{
			String error="";
			RequestDispatcher rd =null;
			com.maan.admin.BrokerCreationBean brokerBean = new com.maan.admin.BrokerCreationBean();
			String user_Type = req.getParameter("user_Type") == null?"0":req.getParameter("user_Type");
			String user_Name = req.getParameter("user_Name") == null?"0":req.getParameter("user_Name");
			String login_Id = req.getParameter("login_Id") == null?"0":req.getParameter("login_Id");
			String user_Pass = req.getParameter("user_Pass") == null?"0":req.getParameter("user_Pass");
			String branch=req.getParameter("branch")==null?"0":req.getParameter("branch");
			String mode=req.getParameter("mode")==null?"":req.getParameter("mode");
			String prodType=req.getParameter("prodType")==null?"":req.getParameter("prodType");
			String loadUserType=req.getParameter("loadUserType")==null?"":req.getParameter("loadUserType");
			String loginCountry ="";
			loginCountry = (String)session.getAttribute("AdminCountryId");

			brokerBean.setUser_Type(user_Type);
			brokerBean.setUser_Name(user_Name);
			brokerBean.setLogin_Id(login_Id);
			brokerBean.setUser_Pass(user_Pass);
			brokerBean.setUser_Branch(branch);
			brokerBean.setUser_prodType(prodType);

			error = brokerBean.validateNewAdmin(mode,loadUserType,"No"); 
			if(error.length() >0)
			{
				req.setAttribute("error",error);
				rd = req.getRequestDispatcher("newUser.jsp");
			}
			else
				rd = req.getRequestDispatcher("newUserMenuSelection.jsp");

			if(rd!=null)
				rd.forward(req,res);
		}
		else if (reqFrom.equalsIgnoreCase("newUserMenuSelection"))
		{
			boolean flag=false;
			String error="";
			RequestDispatcher rd =null;
			com.maan.admin.BrokerCreationBean brokerBean = new com.maan.admin.BrokerCreationBean();
			String user_Type = req.getParameter("user_Type") == null?"0":req.getParameter("user_Type");
			String user_Name = req.getParameter("user_Name") == null?"0":req.getParameter("user_Name");
			String login_Id = req.getParameter("login_Id") == null?"0":req.getParameter("login_Id");
			String user_Pass = req.getParameter("user_Pass") == null?"0":req.getParameter("user_Pass");
			String branch=req.getParameter("branch")==null?"0":req.getParameter("branch");
			String mode=req.getParameter("mode")==null?"":req.getParameter("mode");
			String prodType=req.getParameter("prodType")==null?"":req.getParameter("prodType");
			String admTyp=req.getParameter("admTyp")==null?"":req.getParameter("admTyp");
			String prodId ="";
			String menuId ="";
			String temp="";
			String brokers[] = req.getParameterValues("brokers");
			
			String loginCountry ="";
			loginCountry = (String)session.getAttribute("AdminCountryId");
			String [][]menu = (String[][])session.getAttribute("menuLink");
			String [][]products = (String[][])session.getAttribute("product");

			for(int i=0;i<menu.length;i++)
			{
				temp ="";
				if(req.getParameter("menuLink"+menu[i][0]) != null)
				{
					temp = req.getParameter("menuLink"+menu[i][0]) == null ?"":req.getParameter("menuLink"+menu[i][0]);
					menuId = menuId+temp+",";
				}
			}
			if(menuId.length() >0)
				menuId = menuId.substring(0,menuId.length()-1);
			System.out.println("Menu Id in BrokerCreation Controller ..."+menuId);
			
			for(int i=0;i<products.length;i++)
			{	
				temp ="";
				if(req.getParameter("products"+products[i][0]) != null)
				{
				temp = req.getParameter("products"+products[i][0]) == null ?"":req.getParameter("products"+products[i][0]);
					prodId = prodId+temp+",";
				}
			}
			if(prodId.length() > 0)
				prodId = prodId.substring(0,prodId.length()-1);
			
			
			brokerBean.setUser_Type(user_Type);
			brokerBean.setUser_Name(user_Name);
			brokerBean.setLogin_Id(login_Id);
			brokerBean.setUser_Pass(user_Pass);
			brokerBean.setUser_Branch(branch);
			brokerBean.setUser_menuIds(menuId);
			brokerBean.setUser_proIds(prodId);
			brokerBean.setUser_brokerCodes(brokers); 
			brokerBean.setAdmType(admTyp);
			brokerBean.setUser_Country(loginCountry);

			
			error = brokerBean.validateNewAdmin(); 
			if(error.length() >0)
			{
				req.setAttribute("error",error);
				rd = req.getRequestDispatcher("newUserMenuSelection.jsp");
			}
			else
			{	
				flag = brokerBean.performInsertion(mode);
				if(!flag)
				{
					error = "Error While Inserting Record.";
					req.setAttribute("error",error);
					rd = req.getRequestDispatcher("newUserMenuSelection.jsp");
				}
				else
					rd = req.getRequestDispatcher("NewAdminConformation.jsp");
			}

			if(rd!=null)
				rd.forward(req,res);
		}
		else if (reqFrom.equalsIgnoreCase("newRSAuser"))
		{
			String error="";
			RequestDispatcher rd =null;
			com.maan.admin.BrokerCreationBean brokerBean = new com.maan.admin.BrokerCreationBean();
			String user_Type = req.getParameter("user_Type") == null?"0":req.getParameter("user_Type");
			String user_Name = req.getParameter("user_Name") == null?"0":req.getParameter("user_Name");
			String email_id = req.getParameter("email_id") == null?"0":req.getParameter("email_id");
			String login_Id = req.getParameter("login_Id") == null?"0":req.getParameter("login_Id");
			String coreLoginId = req.getParameter("coreLoginId") == null?"0":req.getParameter("coreLoginId");
			String user_Pass = req.getParameter("user_Pass") == null?"0":req.getParameter("user_Pass");
			String branch=req.getParameter("branch")==null?"0":req.getParameter("branch");
			String mode=req.getParameter("mode")==null?"":req.getParameter("mode");
			String prodType=req.getParameter("prodType")==null?"":req.getParameter("prodType");
			String loadUserType=req.getParameter("loadUserType")==null?"":req.getParameter("loadUserType");
			String productId[] = new String [2];
			productId[0] = req.getParameter("oneOff")==null?"":req.getParameter("oneOff");
			productId[1] = req.getParameter("openCover")==null?"":req.getParameter("openCover");			
			
			String loginCountry ="";
			loginCountry = (String)session.getAttribute("AdminCountryId");

			brokerBean.setUser_Name(user_Name);
			brokerBean.setEmail_Id(email_id);
			brokerBean.setLogin_Id(login_Id);
			brokerBean.setCoreLoginId(coreLoginId);
			brokerBean.setUser_Pass(user_Pass);
			brokerBean.setUser_Branch(branch);
			brokerBean.setUser_prodType(prodType);
			brokerBean.setProductId(productId);
			
			error = brokerBean.validateNewAdmin(mode,loadUserType,"Yes"); 
			if(error.length() >0)
			{
				req.setAttribute("error",error);
				rd = req.getRequestDispatcher("newRSAUser.jsp");
			}
			else
				rd = req.getRequestDispatcher("newRSAUserBroSelection.jsp");

			if(rd!=null)
				rd.forward(req,res);
		}
		else if(reqFrom.equalsIgnoreCase("OpenCoverCertificate"))
		{
			String brokerCode = req.getParameter("brokerCode")==null?"":req.getParameter("brokerCode");
			String pid = req.getParameter("pid")==null?"":req.getParameter("pid");
			String userLogId = req.getParameter("userLogId")==null?"":req.getParameter("userLogId");
			String opencoversLength = req.getParameter("opencoversLength")==null?"0":req.getParameter("opencoversLength");
			
			if(opencoversLength.length()<=0)
				opencoversLength = "0";
			int len = Integer.parseInt(opencoversLength);
			String openCoverNos="";
			for(int i=0;i<(len+1);i++)
			{
				
				String openCoverNo = req.getParameter("opencover"+(i+1))==null?"":req.getParameter("opencover"+(i+1));
				if(openCoverNo.length()>0)
					openCoverNos = openCoverNos+openCoverNo+",";
			}
			if(openCoverNos.length()>0)
				openCoverNos = openCoverNos.substring(0,(openCoverNos.length()-1));
			
			if(session.getAttribute("OpenCoverNos"+brokerCode+pid)!=null)
			{
				session.removeAttribute("OpenCoverNos"+brokerCode+pid);
			}
			
			session.setAttribute("OpenCoverNos"+brokerCode+pid,openCoverNos);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/CustomerCreation/OpenCoverMessage.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
			
		}
		else if (reqFrom.equalsIgnoreCase("newRSAUserBroSelection"))
		{
			boolean flag=false;
			String error="";
			RequestDispatcher rd =null;
			com.maan.admin.BrokerCreationBean brokerBean = new com.maan.admin.BrokerCreationBean();
			String user_Name = req.getParameter("user_Name") == null?"0":req.getParameter("user_Name");
			String email_id = req.getParameter("email_id") == null?"0":req.getParameter("email_id");
			String login_Id = req.getParameter("login_Id") == null?"0":req.getParameter("login_Id");
			String coreLoginId = req.getParameter("coreLoginId") == null?"0":req.getParameter("coreLoginId");
			String user_Pass = req.getParameter("user_Pass") == null?"0":req.getParameter("user_Pass");
			String branch=req.getParameter("branch")==null?"0":req.getParameter("branch");
			String mode=req.getParameter("mode")==null?"":req.getParameter("mode");
			String prodType=req.getParameter("prodType")==null?"":req.getParameter("prodType");
			String admTyp=req.getParameter("admTyp")==null?"":req.getParameter("admTyp");
			String productId[] = new String [2];
			productId[0] = req.getParameter("oneOff")==null?"":req.getParameter("oneOff");
			productId[1] = req.getParameter("openCover")==null?"":req.getParameter("openCover");			
			String loginCountry ="";
			loginCountry = (String)session.getAttribute("AdminCountryId");
			
		    String temp = req.getParameter("length")==null?"0":req.getParameter("length");
			int length=0;
			if(temp.length()>0)
				length = Integer.parseInt(temp);
			
			brokerBean.setUser_Name(user_Name);
			brokerBean.setEmail_Id(email_id);
			brokerBean.setLogin_Id(login_Id);
			brokerBean.setUser_Pass(user_Pass);
			brokerBean.setUser_Branch(branch);
			brokerBean.setProductId(productId);
						
			for(int i=0;i<length;i++)
			{	
				if(req.getParameter("sel"+i) != null)
				{
					String commission = req.getParameter("comm"+i)!=null?req.getParameter("comm"+i):"";
					String startDay = req.getParameter("startDay"+i) == null ? "0": req.getParameter("startDay"+i);
					String startMonth = req.getParameter("startMonth"+i) == null ? "0": req.getParameter("startMonth"+i);
					String startYear = req.getParameter("startYear"+i) == null ? "0": req.getParameter("startYear"+i);
					String endDay = req.getParameter("endDay"+i) == null ? "0": req.getParameter("endDay"+i);
					String endMonth = req.getParameter("endMonth"+i) == null ? "0" : req.getParameter("endMonth"+i);
					String endYear = req.getParameter("endYear"+i) == null ? "0" : req.getParameter("endYear"+i);
					String deactiveDay = req.getParameter("deactiveDay"+i) == null ? "0": req.getParameter("deactiveDay"+i);
					String deactiveMonth = req.getParameter("deactiveMonth"+i) == null ? "0": req.getParameter("deactiveMonth"+i);
					String deactiveYear = req.getParameter("deactiveYear"+i) == null ? "0": req.getParameter("deactiveYear"+i);
					String cdate = "";
					cdate = deactiveDay + "-" + deactiveMonth + "-" + deactiveYear;				
					String sdate = startDay + "-" + startMonth + "-" + startYear;
					String edate = endDay + "-" + endMonth + "-" + endYear;
					
					String activeStatus = req.getParameter("activeStatus"+i) == null ? "": req.getParameter("activeStatus"+i);
					String brokerIds = req.getParameter("brokerIds"+i) == null ? "": req.getParameter("brokerIds"+i);
					String pids = req.getParameter("pids"+i) == null ? "": req.getParameter("pids"+i);
					
					
					brokerBean.setStartDay(startDay);
					brokerBean.setStartMonth(startMonth);
					brokerBean.setStartYear(startYear);
					brokerBean.setEndDay(endDay);
					brokerBean.setEndMonth(endMonth);
					brokerBean.setEndYear(endYear);
					brokerBean.setDeactiveDay(deactiveDay);
					brokerBean.setDeactiveMonth(deactiveMonth);
					brokerBean.setDeactiveYear(deactiveYear);
					brokerBean.setData1(sdate);
					brokerBean.setData2(edate);
					brokerBean.setData3(cdate);
					brokerBean.setCommission(commission);
					brokerBean.setActiveStaus(activeStatus);
					brokerBean.setbrokerIds(brokerIds);
					brokerBean.setPid(pids);
					error = error+brokerBean.validateRSAIssuer(""+(i+1),mode); 		
				}
			}
						
			if(error.length() >0)
			{
				req.setAttribute("error",error);
				rd = req.getRequestDispatcher("newRSAUserBroSelection.jsp");
			}
			else
			{	
				brokerBean.setUser_Name(user_Name);
				brokerBean.setEmail_Id(email_id);
				brokerBean.setLogin_Id(login_Id);
				brokerBean.setCoreLoginId(coreLoginId);
				brokerBean.setUser_Pass(user_Pass);
				brokerBean.setUser_Branch(branch);
				brokerBean.setUser_Country(loginCountry);
				brokerBean.insertRSAIssuer(mode);
				for(int i=0;i<length;i++)
				{	
					if(req.getParameter("sel"+i) != null)
					{
						String commission = req.getParameter("comm"+i)!=null?req.getParameter("comm"+i):"";
						String startDay = req.getParameter("startDay"+i) == null ? "0": req.getParameter("startDay"+i);
						String startMonth = req.getParameter("startMonth"+i) == null ? "0": req.getParameter("startMonth"+i);
						String startYear = req.getParameter("startYear"+i) == null ? "0": req.getParameter("startYear"+i);
						String endDay = req.getParameter("endDay"+i) == null ? "0": req.getParameter("endDay"+i);
						String endMonth = req.getParameter("endMonth"+i) == null ? "0" : req.getParameter("endMonth"+i);
						String endYear = req.getParameter("endYear"+i) == null ? "0" : req.getParameter("endYear"+i);
						String deactiveDay = req.getParameter("deactiveDay"+i) == null ? "0": req.getParameter("deactiveDay"+i);
						String deactiveMonth = req.getParameter("deactiveMonth"+i) == null ? "0": req.getParameter("deactiveMonth"+i);
						String deactiveYear = req.getParameter("deactiveYear"+i) == null ? "0": req.getParameter("deactiveYear"+i);
						String cdate = "";
						//cdate = deactiveDay + "-" + deactiveMonth + "-" + deactiveYear;				
						String sdate = startDay + "-" + startMonth + "-" + startYear;
						String edate = endDay + "-" + endMonth + "-" + endYear;
						
						String activeStatus = req.getParameter("activeStatus"+i) == null ? "": req.getParameter("activeStatus"+i);
						String brokerIds = req.getParameter("brokerIds"+i) == null ? "": req.getParameter("brokerIds"+i);
						String pids = req.getParameter("pids"+i) == null ? "": req.getParameter("pids"+i);
						
						String openNo = "";
						if(session.getAttribute("OpenCoverNos"+brokerIds+pids)!=null)
						{
							openNo = (String)session.getAttribute("OpenCoverNos"+brokerIds+pids);
							session.removeAttribute("OpenCoverNos"+brokerIds+pids);
						}

						brokerBean.setStartDay(startDay);
						brokerBean.setStartMonth(startMonth);
						brokerBean.setStartYear(startYear);
						brokerBean.setEndDay(endDay);
						brokerBean.setEndMonth(endMonth);
						brokerBean.setEndYear(endYear);
						brokerBean.setDeactiveDay(deactiveDay);
						brokerBean.setDeactiveMonth(deactiveMonth);
						brokerBean.setDeactiveYear(deactiveYear);
						brokerBean.setData1(sdate);
						brokerBean.setData2(edate);
						brokerBean.setData3(cdate);
						brokerBean.setCommission(commission);
						brokerBean.setActiveStaus(activeStatus);
						brokerBean.setbrokerIds(brokerIds);
						brokerBean.setPid(pids);
						brokerBean.setOpenNo(openNo);
						brokerBean.insertRSAIssuerDetails();
					}
				}
				req.setAttribute("rsaissuer","yes");
				rd = req.getRequestDispatcher("NewAdminConformation.jsp");
			}
			if(rd!=null)
				rd.forward(req,res);
		}else if (reqFrom.equalsIgnoreCase("brokerMaster")){
			String loginId = (String)session.getAttribute("loginId");
			String feeStatus="";
			String policFee="";
			String taxStatus="";
			String govtTax="";
			String fundStatus="";
			String emergencyfund="";
			String effectiveDate="";
			feeStatus = req.getParameter("feeStatus")==null?"":req.getParameter("feeStatus");
			policFee = req.getParameter("policFee")==null?"":req.getParameter("policFee");
			taxStatus = req.getParameter("taxStatus")==null?"":req.getParameter("taxStatus");
			govtTax = req.getParameter("govtTax")==null?"":req.getParameter("govtTax");
			fundStatus = req.getParameter("fundStatus")==null?"":req.getParameter("fundStatus");
			emergencyfund = req.getParameter("emergencyfund")==null?"":req.getParameter("emergencyfund");
			effectiveDate = req.getParameter("effectiveDate")==null?"":req.getParameter("effectiveDate");
			String bcode = req.getParameter("bcode")==null?"":req.getParameter("bcode");
			String branchCode = req.getParameter("branchCode")==null?"":req.getParameter("branchCode");
			String isApplicableFor = req.getParameter("isApplicableFor")==null?"":req.getParameter("isApplicableFor");
			
			Map broDetails = new HashMap();
			broDetails.put("feeStatus",feeStatus);
			broDetails.put("policFee",policFee);
			broDetails.put("taxStatus",taxStatus);
			broDetails.put("govtTax",govtTax);
			broDetails.put("fundStatus",fundStatus);
			broDetails.put("emergencyfund",emergencyfund);
			broDetails.put("effectiveDate",effectiveDate);
			broDetails.put("bcode",bcode);
			broDetails.put("branchCode",branchCode);
			broDetails.put("loginId",loginId);
			broDetails.put("isApplicableFor",isApplicableFor);
			BrokerCreationBean bcBean = new BrokerCreationBean();
			String errorMas = bcBean.brokerMasterValidation(broDetails);
			RequestDispatcher dispatch=null;
			if(errorMas.length()>0){
				req.setAttribute("errorMas",errorMas);
				dispatch = req.getRequestDispatcher("taxInformation.jsp");
			}else{
				bcBean.brokerMasterInsert(broDetails);
				dispatch = req.getRequestDispatcher("../userCreation/OpenCoverMessage.jsp");
			}
			if(dispatch!=null){
				dispatch.forward(req, res);
			}
		}
	} // DoPost
	
	public void destroy() 
	{
		
	}

}