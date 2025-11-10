package com.maan.common.Customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;

import com.maan.DBCon.DBConnectionStatus;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class HomeCustomerController extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(HomeCustomerController.class);
	private static final long serialVersionUID = 1L;

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try {
			processResult(request, response);
		} catch (BaseException e) {
			logger.debug(e);
		}
	}

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		try {
			processResult(request, response);
		} catch (BaseException e) {
			logger.debug(e);
		}
	}

	public String isNull(final String content) throws BaseException {
		final StringBuffer contents = new StringBuffer();
		if (content == null) {
			contents.append("");
		} else {
			contents.append(content);
		}
		return contents.toString();
	}

	public String isNull(final String content, final String value)
			throws BaseException {
		final StringBuffer contents = new StringBuffer();
		if (content == null) {
			contents.append(value);
		} else {
			contents.append(content);
		}
		return contents.toString();
	}

	public void processResult(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException, BaseException {
		try {

			response.setContentType("text/html");
			HttpSession session = null;
			session = request.getSession(false);
			// String productId = (String)session.getAttribute("product_id");
			String pathh = request.getContextPath();
			String usrModeSC = (String) session.getAttribute("userLoginMode") == null ? ""
					: (String) session.getAttribute("userLoginMode");
			DBConnectionStatus.statusStatic = usrModeSC;
			// For Session Check
			if (session.getAttribute("ses") == null) {
				response.sendRedirect(pathh + "/login/error_messg.jsp");
			}

			String branch = (String) session.getAttribute("LoginBranchCode");
			String pids = (String) session.getAttribute("product_id");
			String path = request.getRequestURI();

			/** B2C* */
			final String b2c = isNull((String) session.getAttribute("b2c"));
			/** B2C* */

			if (path.indexOf('.') == -1) {
				path = path.substring(path.lastIndexOf('/') + 1, path.length());
			} else {
				path = path.substring(path.lastIndexOf('/') + 1, path
						.lastIndexOf('.'));
			}
			// ValidationFormat validationFormat = new ValidationFormat();
			if ("HomeCustomerController".equalsIgnoreCase(path)) {
				HomeCustomerBean cusBean = new HomeCustomerBean();
				String cusId = "";
				cusId = request.getParameter("cusId") == null ? "" : request
						.getParameter("cusId");
				if (cusId.equalsIgnoreCase("0")) {
					cusBean.setTitle(isNull(request.getParameter("title")));
					cusBean.setFirstName(isNull(request
							.getParameter("firstName")));
					cusBean.setNationality(isNull(request
							.getParameter("nationality")));
					cusBean.setDobDay(isNull(request.getParameter("dobDay")));
					cusBean.setDobMonth(isNull(request.getParameter("dobMonth")));
					cusBean.setDobYear(isNull(request.getParameter("dobYear")));
					cusBean.setTelephone(isNull(request
							.getParameter("telephone")));
					cusBean.setMobile(isNull(request.getParameter("mobile")));
					cusBean.setFax(isNull(request.getParameter("fax")));
					cusBean.setEmail(isNull(request.getParameter("email")));
					cusBean
							.setAddress1(isNull(request
									.getParameter("address1")));
					cusBean.setOccupation(isNull(request
							.getParameter("occupation")));
					cusBean.setOtherSource(isNull(request
							.getParameter("otherSource")));
					cusBean.setSource(isNull(request.getParameter("source")));
					cusBean.setEmirate(isNull(request.getParameter("emirate")));
					cusBean.setCountry(isNull(request.getParameter("country")));
					cusBean.setPoBox(isNull(request.getParameter("poBox")));
					cusBean.setOrgName(isNull(request.getParameter("orgName")));
					cusBean.setInsuredLocation(isNull(request
							.getParameter("insuredLocation")));

					if (b2c.equalsIgnoreCase("b2c")) {
						cusBean.setBTOC(b2c);
					}
				} else if (!"0".equalsIgnoreCase(cusId) && cusId.length() > 1) {
					cusBean.settingValuesForCustomer(cusId);
				}
				StringBuffer ErrorMsg = new StringBuffer(1000);
				String CheckCustomerWay = isNull(request
						.getParameter("CheckCustomerWay"));
				if ("SearchOnly".equalsIgnoreCase(CheckCustomerWay)) {
					ErrorMsg = cusBean.validateFieldsSearch();
				} else {
					ErrorMsg = cusBean.validateFields();
				}
				RequestDispatcher reqDis = null;
				if (ErrorMsg.length() > 0) {
					request.setAttribute("ErrorMsg", ErrorMsg);
					request.setAttribute("qid", (String) session
							.getAttribute("TravelQuoteNumber"));
					request.setAttribute("cusid", (String) session
							.getAttribute("customer_id"));
					reqDis = request
							.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
					if ("EditSaveCus".equalsIgnoreCase(CheckCustomerWay)) {
						reqDis = request
								.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
						request.setAttribute("errorDetail", ErrorMsg);
					}
					if (reqDis != null) {
						reqDis.forward(request, response);
					}
				} else {
					if ("SearchOnly".equalsIgnoreCase(CheckCustomerWay)) {
						HomeCustomerBean bean = new HomeCustomerBean();
						bean.setFirstName(isNull(request
								.getParameter("firstName")));
						bean.setLastName(isNull(request
								.getParameter("lastName")));
						bean.setDobDay(isNull(request.getParameter("dobDay")));
						bean.setDobMonth(isNull(request
								.getParameter("dobMonth")));
						bean
								.setDobYear(isNull(request
										.getParameter("dobYear")));
						bean.setTelephone(isNull(request
								.getParameter("telephone")));
						bean.setMobile(isNull(request.getParameter("mobile")));
						bean.setEmail(isNull(request.getParameter("email")));
						bean
								.setOrgName(isNull(request
										.getParameter("orgName")));
						String login = "";
						if (session.getAttribute("user") != null) {
							login = (String) session.getAttribute("user");
						}
						final String returnval[][] = (String[][]) bean
								.searchValues(login);
						request.setAttribute("Search_Result", "SearchResult");
						request.setAttribute("result", returnval);
						 request.setAttribute("applicationNo", request.getParameter("applicationNo"));
						  request.setAttribute("quoteNo", request.getParameter("quoteNo"));
						  logger.info("quote no is home controlerr---------"+request.getParameter("applicationNo"));
						reqDis = request
								.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
					} else {

						String customers = isNull(request
								.getParameter("customer"));
						String cusIDs = isNull((String) session
								.getAttribute("customer_id"));
						if (customers.length() > 0) {
							cusIDs = customers;
						}
						String cusVal = cusBean.storedValues(cusIDs,
								(String) session.getAttribute("user"), branch,
								pids);
						session.setAttribute("customer_id", cusVal == null ? ""
								: cusVal);
						if ("SaveAndContinue".equalsIgnoreCase(CheckCustomerWay)) {
							//reqDis = request.getRequestDispatcher("/HomeInsurance/CoverInformationA.jsp");
							//request.setAttribute("customerIDD", cusVal);
							
							 if(!pids.equals("65"))
							  {
								 reqDis = request.getRequestDispatcher("/HomeInsurance/CoverInformationA.jsp");
	                             request.setAttribute("customerIDD",cusVal);
	                          }
							  else
							  {
								  request.setAttribute("applicationNo", request.getParameter("applicationNo"));
								  request.setAttribute("quoteNo", request.getParameter("quoteNo"));
								  logger.info("quote no is home controlerr---------"+request.getParameter("applicationNo"));
								  request.setAttribute("customerIDD",cusVal);
								  request.setAttribute("requestFrom","ProposersDetails");
								  reqDis = request.getRequestDispatcher("/servlet/motorQuote?linkFrom=customers");
							  }
							 
						} else if ("SaveAndExit"
								.equalsIgnoreCase(CheckCustomerWay)) {
							ErrorMsg.append("Customer Added Successfully");
							request.setAttribute("ErrorMsg", ErrorMsg);
							request.setAttribute("FromSave", "FromSave");
							reqDis = request
									.getRequestDispatcher("/CustomerInfo/CustomerInfoHome.jsp");
						} else if ("SaveOnly"
								.equalsIgnoreCase(CheckCustomerWay)) {
							reqDis = request
									.getRequestDispatcher("/HomeInsurance/generalMsg.jsp");
						} else if ("EditSaveCus"
								.equalsIgnoreCase(CheckCustomerWay)) {
							logger.info(request.getParameter("quoteNos"));
							if ("".equalsIgnoreCase(request
									.getParameter("quoteNos"))) {
								reqDis = request
										.getRequestDispatcher("/HomeInsurance/ExistingCustomers_B2B.jsp");
							} else {
								reqDis = request
										.getRequestDispatcher("/HomeInsurance/final.jsp");
							}
						}
					}
					if (reqDis != null) {
						reqDis.forward(request, response);
					}
				}
			}
		}catch (BaseException e) {
			throw new BaseException((Exception) e,
					"Exception In HomeCustomer Controller");
		}

	}
}