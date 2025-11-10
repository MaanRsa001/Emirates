package com.maan.broker.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.admin.DAO.BrokerCreationBean;
import com.maan.broker.DAO.CustomerCreationBean;


public class UserCreationController extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out = null;
	java.util.Date dd = new java.util.Date();
	java.text.SimpleDateFormat simpleFormatter = new java.text.SimpleDateFormat("dd-MM-yyyy");
	String deActivatedDate = "";

	public void init(ServletConfig config) throws ServletException {
		try {
		} catch (Exception e) {
			System.out.println("Exception in init method" + e.toString());
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		String loginPersonId = (String) session.getAttribute("user");
		PrintWriter out = res.getWriter();
		com.maan.broker.DAO.UserCreationBean bc = new com.maan.broker.DAO.UserCreationBean();
		RequestDispatcher dispatcher1 = req
				.getRequestDispatcher("/login/sessionsCheckNormal.jsp");
		if (dispatcher1 != null)
			dispatcher1.include(req, res);

		// Rajesh For Db Checking
		String pathh = req.getContextPath();
		String usrModeSC = "";
		usrModeSC = (String) session.getAttribute("userLoginMode") == null ? "" : (String) session.getAttribute("userLoginMode");
		if (usrModeSC.length() > 0)
			DBConnectionStatus.statusStatic = usrModeSC;
		else {
			res.sendRedirect(pathh + "/login/error_messg.jsp");
			return;
		}
		
		if (req.getParameter("requestfrom").equalsIgnoreCase("BrokerCreation")) 
		{
			String requestfrom = req.getRequestURI();
			String requestfrom1 = req.getParameter("requestfrom");
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
			String city = req.getParameter("city") == null ? "" : req.getParameter("city");
			String fax = req.getParameter("Fax") == null ? "" : req.getParameter("Fax");
			String email = req.getParameter("Email") == null ? "" : req.getParameter("Email");
			String address1 = req.getParameter("Address1") == null ? "" : req.getParameter("Address1");
			String address2 = req.getParameter("Address2") == null ? "" : req.getParameter("Address2");
			String occupation = req.getParameter("Occupation") == null ? "" : req.getParameter("Occupation");
			String emirate = req.getParameter("Emirate") == null ? "" : req.getParameter("Emirate");
			String country = req.getParameter("Country") == null ? "" : req.getParameter("Country");
			String post = req.getParameter("post") == null ? "" : req.getParameter("post");
			String brokerCompanyName = req.getParameter("BrokerCompanyName") == null ? "" : req.getParameter("BrokerCompanyName");
			String brokerId = req.getParameter("BrokerId") == null ? "" : req.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "" : req.getParameter("RetypePassword");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			loginPersonId = (String) session.getAttribute("user");
			String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
			String provision = req.getParameter("provision") == null ? "" : req.getParameter("provision");
			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
			bc.setTitle(title);
			bc.setFirstName(firstName);
			bc.setGender(gender);
			bc.setLastName(lastName);
			bc.setNationality(nationality);
			// bc.setBrokerDate(brokerDate);
			bc.setTelephone(telephone);
			bc.setMobile(mobile);
			bc.setCity(city);
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
			bc.setUserType(userType);
			bc.setProvision(provision);
			bc.setUcode(ucode);

			session.setAttribute("brokerId", brokerId);
			String error = bc.validate();

			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/UserCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				bc.setUserType(userType);
				bc.setProvision(provision);
				String logBranch = "";
				logBranch = (String) session.getAttribute("LoginBranchCode");	
				String process = bc.insertBrokerEntry(logBranch,(String)(session.getAttribute("b2c")==null?"":session.getAttribute("b2c")));
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("userType", userType);
					req.setAttribute("ucode", ucode);
					req.setAttribute("provision", provision);
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/broker/commissionforUser.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
			// bc.setTitle(req.getParameter("Title"));
			out.println("Hai  welcome  ");
			String title1 = bc.getTitle();
			
		} else if (req.getParameter("requestfrom").equalsIgnoreCase("commisionforBroker")) {

			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
			String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
			String provision = req.getParameter("provision") == null ? "" : req.getParameter("provision");
			String brokerLogin = "";
			if (session.getAttribute("loginPersonId") != null)
				brokerLogin = (String) session.getAttribute("loginPersonId");
			String branch ="";
				branch = (String) session.getAttribute("LoginBranchCode");

			String[][] pdetails = bc.getProducts(brokerLogin,branch);
			String login_id = (String) session.getAttribute("brokerId");
			HashMap productsdetails = new HashMap();
			HashMap pros = new HashMap();

			String productsLength = req.getParameter("productsLength") == null ? "0" : req.getParameter("productsLength");
			

			int j = 1;
			for (int i = 0; i < (pdetails.length); i++) {
				if (req.getParameter("product" + pdetails[i][0]) != null) {
					productsdetails.put("product" + j, req.getParameter("product"+ pdetails[i][0]) == null ? "" : req.getParameter("product" + pdetails[i][0]));
					productsdetails.put("commision" + j, req.getParameter("commision"+ pdetails[i][0]) == null ? "" : req.getParameter("commision" + pdetails[i][0]));
					productsdetails.put("suminsured" + j, req.getParameter("suminsured"+ pdetails[i][0]) == null ? "" : req.getParameter("suminsured" + pdetails[i][0]));
					productsdetails.put("discount" + j, req.getParameter("discount"+ pdetails[i][0]) == null ? "" : req.getParameter("discount" + pdetails[i][0]));

					productsdetails.put("user" + j, req.getParameter("user" + j) == null ? "" : req.getParameter("user" + j));
					productsdetails.put("account" + j, req.getParameter("account" + j) == null ? "" : req.getParameter("account" + j));
					productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req.getParameter("referral" + j));

					pros.put("productsdetails" + j, productsdetails);
					j++;
				}
			
			}
			out.println("length of pros are" + pros.size());
			bc.setProDetails(pros);
			bc.setUcode(ucode);
			bc.setUserType(userType);
			bc.setLoginPersonId(loginPersonId);
			
			String error = bc.validateCommision();

			out.println("request from >>>>>>>>>>" + error);
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/broker/commissionforUser.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				// String status=bc.insertOrUpdate(con,login_id);
				String status = bc.insertOrUpdate( ucode, userType, loginPersonId, provision,"royal");

				String status12 = bc.LoginIdStatus(ucode);
				if (status12 == null || status12.equals("") || status12.equalsIgnoreCase("null") || status12.equalsIgnoreCase("NONE")) {
					if (userType.equals("4")) {
						if (provision.equals("YES")) {
							req.setAttribute("ucode", ucode);
							req.setAttribute("userType", userType);
							req.setAttribute("loginPersonId", loginPersonId);
							RequestDispatcher dispatcher = req
									.getRequestDispatcher("/broker/UserLoginCreation.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						} else {
							req.setAttribute("ucode", ucode);
							req.setAttribute("loginPersonId", loginPersonId);
							RequestDispatcher dispatcher = req
									.getRequestDispatcher("/broker/NewUserConformation.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					} else {
						req.setAttribute("ucode", ucode);
						req.setAttribute("userType", userType);
						req.setAttribute("loginPersonId", loginPersonId);
						RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/UserLoginCreation.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					}
				} else
				{
					req.setAttribute("ucode", ucode);
					req.setAttribute("loginPersonId", loginPersonId);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/NewUserConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}

		}
		else if (req.getParameter("requestfrom").equalsIgnoreCase("BrokerDeactivation")) 
		{
			String[][] brokerDetails = new String[0][0];
			String brokerId = req.getParameter("brokerId") == null ? "" : req.getParameter("brokerId");
			String status = req.getParameter("radiobutton") == null ? "" : req.getParameter("radiobutton");
			String error = "";
			if (brokerId.equals("")) {
				error = "Enter User/AccountExcutive Code";
			} else if (!bc.checkCode(brokerId, (String) session.getAttribute("user"))) {
				error = "Enter Valid Code";
			}
			if (status.length() > 0) 
			{
				if (bc.updateStatus(status, brokerId, "3")) 
				{
					System.out.println("DATE    " + com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd)));
					deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));

					out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
					out.println("dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsd" + deActivatedDate);
					req.setAttribute("status", status);
					req.setAttribute("from", "deactivation");
					req.setAttribute("deActivatedDate", deActivatedDate);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/conformDeactivate.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} 
				else 
				{
					req.setAttribute("error", error);
					RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/deActivation1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			} else {

				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/deActivation1.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}

		}
		else if (req.getParameter("requestfrom").equalsIgnoreCase("user1")) {
			String brokerStatus = "";
			String error = "";
			String selectedBroker = "";
			int noofupdations = 0;
			String selectedType = req.getParameter("selecttype") == null ? "" : req.getParameter("selecttype");
			String selectdiscarded = req.getParameter("selectdiscarded") == null ? "" : req.getParameter("selectdiscarded");
			loginPersonId = req.getParameter("loginPersonId") == null ? "" : req.getParameter("loginPersonId");
			String status = "";
			String[][] allBrokers = new String[0][0];
			String[][] user = new String[0][0];
			String[][] account = new String[0][0];
			boolean flag = false;
			if (selectedType.equalsIgnoreCase("ALL")) {
				if (selectdiscarded.equalsIgnoreCase("0")) {
					error = "Please Select Status";
					req.setAttribute("error", error);
					RequestDispatcher dispatcher = req
							.getRequestDispatcher("/broker/users1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {
					selectedType = "3";
					user = bc.getSelected(loginPersonId, selectedType,
							selectdiscarded);
					if (user.length > 0) {
						for (int i = 0; i < user.length; i++) {
							brokerStatus = req.getParameter("users" + user[i][3]) == null ? "" : req.getParameter("users" + user[i][3]);
							if (brokerStatus.trim().equalsIgnoreCase(user[i][3].trim())) {
								status = "Y";
							} else {
								if (selectdiscarded.equals("Y") || selectdiscarded.equals("A")) {
									status = "D";
								} else {
									status = selectdiscarded;
								}
								out.println("brokerStatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								out.println("status>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
							}
							if (bc.updateStatus(status, user[i][3], selectedType))
								noofupdations++;
						}

						if (noofupdations == user.length) {
							flag = true;
						}

					} else {
						error = "No Records With this Status";
						/*
						 * req.setAttribute("error",error); RequestDispatcher
						 * dispatcher =
						 * req.getRequestDispatcher("/broker/users1.jsp");
						 * if(dispatcher!=null) dispatcher.forward(req,res);
						 * 
						 */
					}
					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4>>>>>>>>>>>>>>>>>"+ brokerStatus);
					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4>>>>>>>>>>>>>>>>>"+ selectdiscarded);
					selectedType = "4";
					account = bc.getSelected(loginPersonId, selectedType, selectdiscarded);
					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4fghfghfghfgh>>>>>>>>>>>>>>>>" + account.length);
					if (account.length > 0) {
						out.println("stgatus>>>>>>>>>>>>inside account>>>>>>>>>>>>>>>>>" + brokerStatus);
						noofupdations = 0;
						for (int i = 0; i < account.length; i++) {
							brokerStatus = req.getParameter("account" + account[i][0]) == null ? "" : req.getParameter("account" + account[i][0].trim());
							out.println("brokerStatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
							out.println("1>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + account[i][0]);
							out.println("2>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + account[i][3]);
							if (brokerStatus.trim().equalsIgnoreCase(
									account[i][3].trim())) {
								out.println("brokerStatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>"+ brokerStatus);
								status = "Y";
							} else {
								if (selectdiscarded.equals("Y")
										|| selectdiscarded.equals("A")) {
									status = "D";
								} else {
									status = selectdiscarded;
								}
							}
							if (bc.updateStatus(status, account[i][3], selectedType))
								noofupdations++;
						}

						if (noofupdations == account.length || flag) {
							deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
							req.setAttribute("status", status);
							req.setAttribute("deActivatedDate", deActivatedDate);
							RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/conformDeactivate.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
						out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4fghfghfghfgh>>>>>>>>>>>>>>>>" + brokerStatus);
					} else {
						if (error.length() > 0) {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/users1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						} else if (flag) {
							deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
							req.setAttribute("status", status);
							req.setAttribute("deActivatedDate", deActivatedDate);
							RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/conformDeactivate.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				}
			} else if (selectedType.equalsIgnoreCase("0")) {

				out.println("allBrokers>>>>>>inside usser1>>length" + allBrokers.length);
				error = "Please Select The Type/All";
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/users1.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				String userStatus = "";
				String accountStatus = "";
				if (selectedType.equalsIgnoreCase("3")) {
					if (selectdiscarded.equalsIgnoreCase("0")) {
						error = "Please Select Status";
						req.setAttribute("error", error);
						RequestDispatcher dispatcher = req
								.getRequestDispatcher("/broker/users1.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					} else {
						user = bc.getSelected(loginPersonId, selectedType, selectdiscarded);
						if (user.length > 0) {
							for (int i = 0; i < user.length; i++) {
								brokerStatus = req.getParameter("users" + user[i][3].trim()) == null ? "" : req.getParameter("users" + user[i][3].trim());
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user[i][3]);
								if (brokerStatus.trim().equalsIgnoreCase(
										user[i][3].trim())) {
									out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
									status = "Y";
								} else {
									if (selectdiscarded.equals("Y")
											|| selectdiscarded.equals("A"))
										selectdiscarded = "D";
									status = selectdiscarded;
								}
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectdiscarded);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								if (bc.updateStatus(status, user[i][3], selectedType))
									noofupdations++;
							}
							if (noofupdations == user.length) {
								System.out.println("DATE    " + com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd)));
								deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ status);
								out.println("dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsd"+ deActivatedDate);
								req.setAttribute("status", status);
								req.setAttribute("deActivatedDate", deActivatedDate);
								RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/conformDeactivate.jsp");
								if (dispatcher != null)
									dispatcher.forward(req, res);
							}
						} else {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/users1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				} else if (selectedType.equalsIgnoreCase("4")) {
					if (selectdiscarded.equalsIgnoreCase("0")) {
						error = "Please Select Status";
						req.setAttribute("error", error);
						RequestDispatcher dispatcher = req
								.getRequestDispatcher("/broker/users1.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					} else {
						account = bc.getSelected(loginPersonId, selectedType, selectdiscarded);
						if (account.length > 0) {
							for (int i = 0; i < account.length; i++) {
								brokerStatus = req.getParameter("account" + account[i][0].trim()) == null ? "" : req.getParameter("account"+ account[i][0].trim());
								if (brokerStatus.trim().equalsIgnoreCase(
										account[i][3].trim())) {
									status = "Y";
								} else {
									if (selectdiscarded.equals("Y") || selectdiscarded.equals("A"))
										selectdiscarded = "D";
									status = selectdiscarded;
								}
								if (bc.updateStatus(status, account[i][3], selectedType))
									noofupdations++;
							}
							if (noofupdations == account.length) {
								System.out.println("DATE    " + com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd)));
								deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								out.println("dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsd" + deActivatedDate);
								req.setAttribute("status", status);
								req.setAttribute("deActivatedDate", deActivatedDate);
								RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/conformDeactivate.jsp");
								if (dispatcher != null)
									dispatcher.forward(req, res);
							}
						} else {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/users1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				}
			}
		}
		if (req.getParameter("requestfrom").equalsIgnoreCase(
				"UserLoginCreation")) {

			String ucode = req.getParameter("ucode") == null ? "" : req
					.getParameter("ucode");
			String userType = req.getParameter("userType") == null ? "" : req
					.getParameter("userType");
			if(!"b2c".equalsIgnoreCase((String)session.getAttribute("b2c")))
			{
				loginPersonId = req.getParameter("loginPersonId") == null ? ""
					: req.getParameter("loginPersonId");
			}else
			{
				loginPersonId=(String)(session.getAttribute("user")==null?"":session.getAttribute("user"));
			}
			String brokerId = req.getParameter("BrokerId") == null ? "" : req
					.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req
					.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? ""
					: req.getParameter("RetypePassword");
			bc.setBrokerId(brokerId);
			bc.setPassword(password);
			bc.setRetypePassword(retypePassword);
			// session.setAttribute("brokerId",brokerId);
			out.println("<br>broker code>>>>>>>>>>>>>>>>>>>>>>" + ucode);
			out.println("<br>login person code>>>>>>>>>>>>>>>>>>>>>>" + loginPersonId);
			out.println("<br>login person code>>>>>>>>>>>>>>>>>>>>>>" + userType);
			String error = bc.validateLoginCreation();
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/UserLoginCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				String process = bc.insertUserLogin(ucode, loginPersonId, userType);
				if (process.equalsIgnoreCase("123")) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/broker/NewUserConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		if (req.getParameter("requestfrom").equalsIgnoreCase("UserCreationAdmin")) {
			String requestfrom = req.getRequestURI();
			String requestfrom1 = req.getParameter("requestfrom");
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
			String brokerId = req.getParameter("BrokerId") == null ? "" : req.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "": req.getParameter("RetypePassword");
			String mode = req.getParameter("mode") == null ? "" : req.getParameter("mode");
			//loginPersonId = req.getParameter("editbroker") == null ? "" : req.getParameter("editbroker");// (String)session.getAttribute("user");
			if(!"b2c".equalsIgnoreCase((String)session.getAttribute("b2c")))
			{
				loginPersonId = req.getParameter("editbroker") == null ? "" : req.getParameter("editbroker");// (String)session.getAttribute("user");
			}else
			{
				loginPersonId=(String)(session.getAttribute("user")==null?"":session.getAttribute("user"));
			}
			String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
			String provision = req.getParameter("provision") == null ? "" : req.getParameter("provision");
			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
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
			bc.setUserType(userType);
			bc.setProvision(provision);
			bc.setUcode(ucode);

			session.setAttribute("brokerId", brokerId);
			System.out.println("Mode isssssssssssssssssssssssssssssss>" + mode);
			if(mode.equalsIgnoreCase("edit") ){//&& session.getAttribute("UserOpenCoverNos")==null){
				String openCoverNos = bc.getOpenCoverCertificatesUser(ucode);
				//session.removeAttribute("UserOpenCoverNos");
				session.setAttribute("UserOpenCoverNos",openCoverNos);
			}
			String error = bc.validate();
			if (error.length() > 0) {
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("../userCreation/adminCreateUser.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				bc.setUserType(userType);
				bc.setProvision(provision);
				String logBranch = "";
				logBranch = (String) session.getAttribute("LoginBranchCode");
				String b2c=(String)(session.getAttribute("b2c")==null?"":session.getAttribute("b2c"));
				String process = bc.insertBrokerEntry(logBranch,b2c);
				if (process.equalsIgnoreCase("YES")) {
					req.setAttribute("userType", userType);
					req.setAttribute("ucode", ucode);
					req.setAttribute("provision", provision);
					RequestDispatcher dispatcher=null;
					if("b2c".equalsIgnoreCase(b2c))
						dispatcher = req.getRequestDispatcher("/userCreation/adminUserLoginCreation.jsp");
					else
						dispatcher = req.getRequestDispatcher("../userCreation/adminCommissionforUser.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
			String title1 = bc.getTitle();
		}
		else if (req.getParameter("requestfrom").equalsIgnoreCase("commisionforOfficeSchemeUser")) 
		{
			String userid = "" + session.getAttribute("userid");
			String adminPid = (String)session.getAttribute("AdminPid");
			String adminBranch = "";
			adminBranch = (String)session.getAttribute("adminBranch");
			String bcode = req.getParameter("bcode") == null ? "" : req.getParameter("bcode");
			String officeScheme[][] = new String[0][0];
			HashMap schemeDetails = new HashMap();
			HashMap pros = new HashMap();
			String loginpersonId = "";
			loginpersonId = req.getParameter("loginpersonId") == null ? "" : req.getParameter("loginpersonId");
			String commision = "";
			String suminsured = "";
			String premium = "";
			String loading = "";
			String discount = "";
			String bday = "";
			int s = 0;
			officeScheme = bc.getOfficeProductSchemeForBroker(loginpersonId,adminBranch);
			for(int i=0;i<officeScheme.length;i++)
			{
				if (req.getParameter("product"+officeScheme[i][0]) != null) 
				{
					HashMap schemeDetails1 = new HashMap();
					suminsured = req.getParameter("suminsured"+ officeScheme[i][0]);
					discount = req.getParameter("discount"+ officeScheme[i][0]);
					
					suminsured = suminsured == null ? "" : suminsured;
					discount = discount == null ? "" : discount;
	
					String schemeId = officeScheme[i][0];
					/*schemeDetails.put("scheme"+(s+1),officeScheme[i][0]);
					schemeDetails.put("suminsured"+(s+1),suminsured);
					schemeDetails.put("discount"+(s+1),discount);
					
					pros.put("productsdetails"+(s+1), schemeDetails);
					s++;*/
					schemeDetails.put("scheme"+s,"s"+schemeId);
					schemeDetails.put("suminsured"+schemeId,suminsured);
					schemeDetails.put("discount"+schemeId,discount);
					
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
				RequestDispatcher dispatcher = req.getRequestDispatcher("/userCreation/OfficeSchemeUserPopUp.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			}
			else
			{
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

		// User creation Product Assigning Screen
		if (req.getParameter("requestfrom").equalsIgnoreCase("commisionforBrokerAdmin")) 
		{
			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
			String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
			String provision = req.getParameter("provision") == null ? "" : req.getParameter("provision");
			String brokerLogin = "";
			if (session.getAttribute("loginPersonId") != null)
				brokerLogin = (String) session.getAttribute("loginPersonId");
			String branch ="";
				branch = (String) session.getAttribute("LoginBranchCode");
			
			HashMap officeHash = new HashMap();
			if(session.getAttribute("schemeDetails")!=null)
				officeHash = (HashMap) session.getAttribute("schemeDetails");
			
			
			if(officeHash != null && officeHash.size() > 0 )
				bc.setOfficeHash(officeHash);
			String officeScheme[][] = new String[0][0];
			officeScheme = bc.getOfficeProductSchemeForBroker(brokerLogin,branch);
			
			String[][] pdetails = bc.getProducts(brokerLogin,branch);
			String login_id = (String) session.getAttribute("bcode");
			HashMap productsdetails = new HashMap();
			HashMap pros = new HashMap();
			String productsLength = req.getParameter("productsLength") == null ? "0" : req.getParameter("productsLength");
			boolean offFlag = false;
			int j = 1;
			for (int i = 0; i < (Integer.parseInt(productsLength)); i++) 
			{
				if(req.getParameter("product" + pdetails[i][0]) != null) 
				{
					String prod = "";
					prod = req.getParameter("product"+ pdetails[i][0]) == null ? "" : req.getParameter("product" + pdetails[i][0]);
					
					if(prod.equals("30"))
					{
						for(int s=0;s<officeScheme.length;s++)
						{
							String OFSCH = "";
							OFSCH = officeScheme[s][0];
							OFSCH = OFSCH == null ? "" :OFSCH ;
							if(session.getAttribute("schemeDetails")==null)
							{
								officeHash = bc.getExistingOfficeDetails(ucode);
								if(officeHash != null && officeHash.size() > 0 )
								{
									bc.setOfficeHash(officeHash);
									session.setAttribute("schemeDetails",officeHash);
								}
								else
									offFlag = true;
								
							}
							if(officeHash.size() > 0 )
							{
								if(officeHash.containsValue("s"+officeScheme[s][0]))
								{
									System.out.println("royal test controller office...id..."+officeScheme[s][0]);
									System.out.println("royal test office..schemeId.id..."+prod);
									productsdetails.put("product" + j,(prod+officeScheme[s][0]) );
									productsdetails.put("commision" + j, req.getParameter("commision" + pdetails[i][0]) == null ? "" : req.getParameter("commision" + pdetails[i][0]));
									productsdetails.put("user" + j, req.getParameter("user" + j) == null ? "" : req.getParameter("user" + j));
									productsdetails.put("account" + j, req.getParameter("account" + j) == null ? "" : req.getParameter("account" + j));
									productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req.getParameter("referral" + j));
									productsdetails.put("debit" + j, req.getParameter("debit"+pdetails[i][0])==null ?"N" :req.getParameter("debit" + pdetails[i][0]));
									pros.put("productsdetails" + j, productsdetails);
									j++;
								}
							}
						}
					}
					else
					{
						productsdetails.put("product" + j,prod);
						productsdetails.put("commision" + j, req.getParameter("commision" + pdetails[i][0]) == null ? "" : req.getParameter("commision" + pdetails[i][0]));
						productsdetails.put("suminsured" + j,req.getParameter("suminsured" + pdetails[i][0]) == null ? "" : req.getParameter("suminsured" + pdetails[i][0]));
						productsdetails.put("discount" + j, req.getParameter("discount" + pdetails[i][0]) == null ? "" : req.getParameter("discount" + pdetails[i][0]));
						productsdetails.put("user" + j, req.getParameter("user" + j) == null ? "" : req.getParameter("user" + j));
						productsdetails.put("account" + j, req.getParameter("account" + j) == null ? "" : req.getParameter("account" + j));
						productsdetails.put("referral" + j, req.getParameter("referral" + j) == null ? "" : req.getParameter("referral" + j));
						productsdetails.put("debit" + j, req.getParameter("debit"+pdetails[i][0])==null ?"N" :req.getParameter("debit" + pdetails[i][0]));
						pros.put("productsdetails" + j, productsdetails);
						j++;
					}
				}
			}
			bc.setProDetails(pros);
			bc.setUcode(ucode);
			bc.setUserType(userType);
			bc.setLoginPersonId(login_id);
			
			String error = bc.validateCommision();
			if(offFlag)
				error = error+"<br>* Please select any one of E-Office Scheme to get E-Office product";
			if (error.length() > 0) 
			{
				out.println("error ifnfo " + error);
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminCommissionforUser.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} 
			else 
			{
				String status = bc.insertOrUpdate( ucode,userType,login_id,provision,(String)session.getAttribute("UserOpenCoverNos"));
				String status12 = bc.LoginIdStatus(ucode);
				if (status12 == null || status12.equals("") || status12.equalsIgnoreCase("null") || status12.equalsIgnoreCase("NONE")) {
					if (userType.equals("4")) {
						if (provision.equals("YES")) {
							req.setAttribute("ucode", ucode);
							req.setAttribute("userType", userType);
							req.setAttribute("loginPersonId", login_id);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUserLoginCreation.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						} else {
							req.setAttribute("ucode", ucode);
							req.setAttribute("loginPersonId", login_id);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminNewUserConformation.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					} else {
						req.setAttribute("ucode", ucode);
						req.setAttribute("userType", userType);
						req.setAttribute("loginPersonId", login_id);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUserLoginCreation.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					}
				} else {
					req.setAttribute("ucode", ucode);
					req.setAttribute("loginPersonId", login_id);
					RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminNewUserConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		if (req.getParameter("requestfrom").equalsIgnoreCase("user1Admin")) {
			String brokerStatus = "";
			String error = "";
			String selectedBroker = "";
			int noofupdations = 0;

			String selectedType = req.getParameter("selecttype") == null ? "" : req.getParameter("selecttype");
			String selectdiscarded = req.getParameter("selectdiscarded") == null ? "" : req.getParameter("selectdiscarded");

			loginPersonId = req.getParameter("loginPersonId") == null ? "" : req.getParameter("loginPersonId");
			String status = "";
			String[][] allBrokers = new String[0][0];
			String[][] user = new String[0][0];
			String[][] account = new String[0][0];
			boolean flag = false;
			if (selectedType.equalsIgnoreCase("ALL")) {
				if (selectdiscarded.equalsIgnoreCase("0")) {
					error = "Please Select Status";
					req.setAttribute("error", error);
					RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				} else {

					selectedType = "3";
					user = bc.getSelected(loginPersonId, selectedType, selectdiscarded);
					if (user.length > 0) {
						for (int i = 0; i < user.length; i++) {
							brokerStatus = req.getParameter("users" + user[i][3]) == null ? "" : req.getParameter("users" + user[i][3]);
							if (brokerStatus.trim().equalsIgnoreCase(
									user[i][3].trim())) {
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								status = "Y";

							} else {
								if (selectdiscarded.equals("Y") || selectdiscarded.equals("A")) {
									status = "D";
								} else {
									status = selectdiscarded;
								}
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
							}
							if (bc.updateStatus(status, user[i][3], selectedType))
								noofupdations++;
						}
						if (noofupdations == user.length) {
							flag = true;
						}
					} else {
						error = "No Records With this Status";
					}

					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4>>>>>>>>>>>>>>>>>" + brokerStatus);
					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4>>>>>>>>>>>>>>>>>" + selectdiscarded);

					selectedType = "4";
					account = bc.getSelected(loginPersonId, selectedType,
							selectdiscarded);
					out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4fghfghfghfgh>>>>>>>>>>>>>>>>" + account.length);
					if (account.length > 0) {
						out.println("stgatus>>>>>>>>>>>>inside account>>>>>>>>>>>>>>>>>" + brokerStatus);
						noofupdations = 0;
						for (int i = 0; i < account.length; i++) {
							brokerStatus = req.getParameter("account" + account[i][0]) == null ? "" : req.getParameter("account" + account[i][0].trim());
							out.println("stgatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
							out.println("stgatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + account[i][0]);
							out.println("stgatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + account[i][3]);
							if (brokerStatus.trim().equalsIgnoreCase(
									account[i][3].trim())) {
								out.println("stgatus>>>>>>>>yyyyyyyyyy>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								status = "Y";
							} else {
								if (selectdiscarded.equals("Y") || selectdiscarded.equals("A")) {
									status = "D";
								} else {
									status = selectdiscarded;
								}
							}
							if (bc.updateStatus(status, account[i][3], selectedType))
								noofupdations++;
						}
						if (noofupdations == account.length || flag) {
							deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
							req.setAttribute("status", status);
							req.setAttribute("deActivatedDate", deActivatedDate);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminConformDeactivate.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
						out.println("stgatus>>>>>>>>>>>>beoreeeeeeeee4fghfghfghfgh>>>>>>>>>>>>>>>>" + brokerStatus);
					} else {
						if (error.length() > 0) {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						} else if (flag) {
							deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
							req.setAttribute("status", status);
							req.setAttribute("deActivatedDate", deActivatedDate);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminConformDeactivate.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				}
			} else if (selectedType.equalsIgnoreCase("0")) {
				out.println("allBrokers>>>>>>inside usser1>>length" + allBrokers.length);
				error = "Please Select The Type/All";
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				String userStatus = "";
				String accountStatus = "";
				if (selectedType.equalsIgnoreCase("3")) {
					if (selectdiscarded.equalsIgnoreCase("0")) {
						error = "Please Select Status";
						req.setAttribute("error", error);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					} else {
						user = bc.getSelected(loginPersonId, selectedType, selectdiscarded);

						if (user.length > 0) {
							for (int i = 0; i < user.length; i++) {
								brokerStatus = req.getParameter("users" + user[i][3].trim()) == null ? "" : req.getParameter("users" + user[i][3].trim());
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + user[i][3]);
								if (brokerStatus.trim().equalsIgnoreCase(
										user[i][3].trim())) {
									out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
									status = "Y";
								} else {
									if (selectdiscarded.equals("Y")
											|| selectdiscarded.equals("A"))
										selectdiscarded = "D";
									status = selectdiscarded;
								}

								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectdiscarded);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								if (bc.updateStatus(status, user[i][3], selectedType))
									noofupdations++;
							}
							if (noofupdations == user.length) {
								System.out.println("DATE    " + com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd)));
								deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate("" + simpleFormatter.format(dd));
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								out.println("dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsd" + deActivatedDate);
								req.setAttribute("status", status);
								req.setAttribute("deActivatedDate", deActivatedDate);
								RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminConformDeactivate.jsp");
								if (dispatcher != null)
									dispatcher.forward(req, res);
							}
						} else {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				} else if (selectedType.equalsIgnoreCase("4")) {
					if (selectdiscarded.equalsIgnoreCase("0")) {
						error = "Please Select Status";
						req.setAttribute("error", error);
						RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
						if (dispatcher != null)
							dispatcher.forward(req, res);
					} else {
						account = bc.getSelected(loginPersonId, selectedType, selectdiscarded);
						if (account.length > 0) {
							for (int i = 0; i < account.length; i++) {
								brokerStatus = req.getParameter("account" + account[i][0].trim()) == null ? "" : req.getParameter("account" + account[i][0].trim());
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + account[i][3]);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + account[i][0]);
								if (brokerStatus.trim().equalsIgnoreCase(
										account[i][3].trim())) {
									out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
									status = "Y";
								} else {
									if (selectdiscarded.equals("Y") || selectdiscarded.equals("A"))
										selectdiscarded = "D";
									status = selectdiscarded;
								}
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectdiscarded);
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								if (bc.updateStatus(status, account[i][3], selectedType))
									noofupdations++;
							}

							if (noofupdations == account.length) {
								System.out.println("DATE    " + com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd)));
								deActivatedDate = com.maan.common.util.OracleDateConversion.ConvertDate(""+ simpleFormatter.format(dd));
								out.println("stgatus>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + status);
								out.println("dfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsd" + deActivatedDate);
								req.setAttribute("status", status);
								req.setAttribute("deActivatedDate", deActivatedDate);
								RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminConformDeactivate.jsp");
								if (dispatcher != null)
									dispatcher.forward(req, res);
							}
						} else {
							error = "No Records With this Status";
							req.setAttribute("error", error);
							RequestDispatcher dispatcher = req.getRequestDispatcher("../userCreation/adminUsers1.jsp");
							if (dispatcher != null)
								dispatcher.forward(req, res);
						}
					}
				}
				out.println("selectged broker name is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + selectedBroker);
				out.println("selectged broker name is>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + brokerStatus);
			}
		}
		if (req.getParameter("requestfrom").equalsIgnoreCase("UserLoginCreationAdmin")) {
			String ucode = req.getParameter("ucode") == null ? "" : req.getParameter("ucode");
			String userType = req.getParameter("userType") == null ? "" : req.getParameter("userType");
			if(!"b2c".equalsIgnoreCase((String)session.getAttribute("b2c")))
			{
				loginPersonId = req.getParameter("loginPersonId") == null ? "" : req.getParameter("loginPersonId");
			}else
			{
				loginPersonId=(String)(session.getAttribute("user")==null?"":session.getAttribute("user"));
			}
			String brokerId = req.getParameter("BrokerId") == null ? "" : req.getParameter("BrokerId");
			String password = req.getParameter("Password") == null ? "" : req.getParameter("Password");
			String retypePassword = req.getParameter("RetypePassword") == null ? "" : req.getParameter("RetypePassword");
			bc.setBrokerId(brokerId);
			bc.setPassword(password);
			bc.setRetypePassword(retypePassword);
			String error = bc.validateLoginCreation();
			if (error.length() > 0) {
				req.setAttribute("error", error);
				RequestDispatcher dispatcher = req
						.getRequestDispatcher("/userCreation/adminUserLoginCreation.jsp");
				if (dispatcher != null)
					dispatcher.forward(req, res);
			} else {
				bc.setLoginPersonId(loginPersonId);
				String process = bc.insertUserLogin(ucode, loginPersonId,
						userType);
//				Added by Chinna
				String branchCode = (String) session.getAttribute("adminBranch");
				new BrokerCreationBean().updateLoginId(brokerId, branchCode,ucode);
				//End
				// Rajesh Modified for Common Db
				// process=bc.insertUserLogin(ucode,loginPersonId,userType,usrModeSC);
				if (process.equalsIgnoreCase("123")) {
					RequestDispatcher dispatcher = req.getRequestDispatcher("/userCreation/adminNewUserConformation.jsp");
					if (dispatcher != null)
						dispatcher.forward(req, res);
				}
			}
		}
		if(req.getParameter("requestfrom").equalsIgnoreCase("UserLoginStatus"))
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
			RequestDispatcher dispatcher = req.getRequestDispatcher("/userCreation/StatusConform.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
			
		}
		/*if(req.getParameter("requestfrom").equalsIgnoreCase("UserLoginStatus"))
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
			RequestDispatcher dispatcher = req.getRequestDispatcher("/userCreation/adminUsers1.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
			
		}*/
		//For Customer Login Creation by Rajesh R on09/04/08 start
		//For User Open COver restriction
		if(req.getParameter("requestfrom").equalsIgnoreCase("OpenCoverCertificate"))
		{
			String brokerLoginId = req.getParameter("brokerLoginId")==null?"":req.getParameter("brokerLoginId");
			String pid = req.getParameter("pid")==null?"":req.getParameter("pid");
			String userAcode = req.getParameter("userAcode")==null?"":req.getParameter("userAcode");
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
			
			if(session.getAttribute("UserOpenCoverNos")!=null)
			{
				session.removeAttribute("UserOpenCoverNos");
			}
			
			session.setAttribute("UserOpenCoverNos",openCoverNos);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/userCreation/OpenCoverMessage.jsp");
			if(dispatcher!=null)
				dispatcher.forward(req,res);
		}else if (req.getParameter("requestfrom").equalsIgnoreCase("UserPolicyType"))
		{
			System.out.println(">>> UserPolicyType Block - Enter");
			BrokerCreationBean brokerBean=new BrokerCreationBean();
			HttpServletRequest request=req;
			String bcode = session.getAttribute("adminBranch")==null?"":(String) session.getAttribute("adminBranch");
			String proId = req.getParameter("pid")==null?"":req.getParameter("pid");
			String brokerId = request.getParameter("brokerId")==null?"":request.getParameter("brokerId");
			String userId = request.getParameter("userId")==null?"":request.getParameter("userId");
			String values="";
			String error="";
			String amendId="";
			boolean msgsts=false;
			java.util.ArrayList val=new java.util.ArrayList();
			String [][] PolicyType = req.getAttribute("PolicyType")==null?new String[0][0]:(String[][])req.getAttribute("PolicyType");
			if(PolicyType==null || PolicyType.length<=0)
			{
				PolicyType =  brokerBean.getUserPolicyTypeDetails(bcode, proId, brokerId, userId);
			}
			if(PolicyType!=null && PolicyType.length>0)
			{
				for(int k=0;k<PolicyType.length;k++)
				{
					values+=",'"+PolicyType[k][0]+"'";
					values+=",'"+(request.getParameter("status"+PolicyType[k][0])==null?"":request.getParameter("status"+PolicyType[k][0]))+"'";
					PolicyType[k][2]=request.getParameter("status"+PolicyType[k][0])==null?"":request.getParameter("status"+PolicyType[k][0]);
					val.add(values);
					values="";
				}
			}
			RequestDispatcher dispatch=null;
			System.out.println("error: "+error.length()+"error: "+error);
			request.setAttribute("PolicyType", PolicyType);
			if (error.length() > 0) {
				request.setAttribute("error", error);
				dispatch = request.getRequestDispatcher("/userCreation/UserMotorPolicyTypeDetails.jsp?pid="+proId+"&bcode="+brokerId+"&ucode="+userId);
			} else {
				amendId=brokerBean.getMaxAmendId(proId, bcode, userId);
				for(int k=0; k<val.size(); k++)
				{
					brokerBean.insertUserPolicyTypeDetails(proId, bcode, userId, amendId,(String)val.get(k));
					msgsts=true;
				}
				if(msgsts)
				request.setAttribute("msg", "Saved Successfully");
				dispatch = request.getRequestDispatcher("/userCreation/UserMotorPolicyTypeDetails.jsp?pid="+proId+"&bcode="+brokerId+"&ucode="+userId);
			}
			System.out.println(">>> UserPolicyType Block - Exit");
			if(dispatch!=null){
				dispatch.forward(req, res);
			}
		}
	}
} // Class