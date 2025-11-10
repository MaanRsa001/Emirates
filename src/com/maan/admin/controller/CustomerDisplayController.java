package com.maan.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.maan.admin.DAO.AdminBean;
import com.maan.admin.DAO.BrokerCreationBean;
import com.maan.admin.DAO.CustomerDisplayBean;
import com.maan.broker.DAO.CustomerCreationBean;

public class CustomerDisplayController extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request, final HttpServletResponse response)	throws ServletException, IOException {
		processResult(request,response);
	}

	public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
			processResult(request,response);
	}
	
	public void processResult(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,	IOException 
	{
		final CustomerCreationBean cust = new CustomerCreationBean();
		final AdminBean admin = new AdminBean();
		final String loginId = (String) request.getSession().getAttribute("user");
		final String adminBranch = admin.getAdminBranch(loginId);

		request.getSession().setAttribute("adminBranch", adminBranch);
		final ArrayList list = new ArrayList();
		String requestfrom = request.getParameter("requestfrom");
		System.out.println("Customer Display Controller................. "+requestfrom);
		HttpSession session = request.getSession(false);
		if(requestfrom.equalsIgnoreCase("SelectProduct"))
        {
            String customerPID = request.getParameter("customerPID") != null ? request.getParameter("customerPID") : session.getAttribute("customerPID") != null ? (String)session.getAttribute("customerPID") : "11";
            if(customerPID != null && customerPID.length() > 0)
            {
                session.setAttribute("customerPID", customerPID);
                requestfrom = "BrokerMOCList";
            }
        }
		if(requestfrom.equalsIgnoreCase("BrokerMOCList"))
		{		
			String[][] brokerMOCList = cust.getBrokerMOCList(adminBranch);
			String brokerName;
			String noOfMOC;
			String brokerLogin;
			for (int i = 0; i < brokerMOCList.length; ++i) 
			{
				brokerName = brokerMOCList[i][0];
				noOfMOC = brokerMOCList[i][1];
				brokerLogin = brokerMOCList[i][2];
				CustomerDisplayBean bean = new CustomerDisplayBean();
				bean.setSno(i + 1);
				bean.setBrokerName(brokerName);
				bean.setNoOfMOC(noOfMOC);
				bean.setBrokerLogin(brokerLogin);
				
				list.add(bean);
			}
			System.out.println("BrokerMOCList List =========="+list.size());
			request.setAttribute("result", list);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerCreation/BrokerMOCList.jsp");
			dispatcher.forward(request, response);
		}
		
		if(requestfrom.equalsIgnoreCase("CustomerMOCList"))
		{
			final BrokerCreationBean brokerBean = new BrokerCreationBean();
			String brokerId = request.getParameter("brokerId");
			String[][] custMOCList = cust.getCustomerMOCListForBroker(brokerId);
			final String brokerInfo[][] = brokerBean.getBrokerLoginId(brokerId,"Broker","BrokerLogin");
			String custName;
			String missOpenCover;
			String openCoverNo;
			String brokerLogin;
			String customerId;
			String custLoginId;
			String noOfSubCustomer;
			String fdCode;
			String agencyCode;
			for (int i = 0; i < custMOCList.length; ++i) 
			{
				openCoverNo = custMOCList[i][1];
				missOpenCover = custMOCList[i][2];
				custName = custMOCList[i][3];
				brokerLogin = custMOCList[i][4];
				customerId = custMOCList[i][5];
				custLoginId = custMOCList[i][6]==null?"":custMOCList[i][6];
				fdCode = custMOCList[i][8];
				agencyCode = custMOCList[i][7];
				
				CustomerDisplayBean bean = new CustomerDisplayBean();
				bean.setSno(i + 1);
				bean.setBrokerLogin(brokerLogin);
				bean.setCustName(custName);
				bean.setMissOpenCover(missOpenCover);
				bean.setOpenCoverNo(openCoverNo);
				bean.setCustomerId(customerId);
				bean.setCustLoginId(custLoginId);
				bean.setFdCode(fdCode);
				bean.setAgencyCode(agencyCode);
				if(custLoginId.length() == 0){
					bean.setCustLoginStaus("Fresh");
					bean.setNoOfSubCustomer("0");
				}
				else{
					bean.setCustLoginStaus("Edit");
					noOfSubCustomer = brokerBean.getMultiUserCount(custMOCList[i][7],"Customer");
					if(noOfSubCustomer.length() > 0){
						bean.setNoOfSubCustomer(noOfSubCustomer);
					}else{
						bean.setNoOfSubCustomer("0");
					}
				}
				list.add(bean);
			}
			System.out.println("CustomerMOCListForBroker =========="+list.size());
			request.setAttribute("result", list);
			request.setAttribute("brokerLogin", brokerId);
			if(brokerInfo.length > 0 ){
				request.setAttribute("brokerAgencyCode", brokerInfo[0][0]);
				request.setAttribute("brokerOrgName", brokerInfo[0][1]);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerCreation/CustomerMOCListForBroker.jsp");
			dispatcher.forward(request, response);
		}
		
		if(requestfrom.equalsIgnoreCase("CustomerMOCListStatus"))
		{
			final BrokerCreationBean brokerBean = new BrokerCreationBean();
			String brokerId = request.getParameter("brokerId");
			String custLoginId = request.getParameter("custLoginId");
			String customerStatus = request.getParameter("customerStatus");
			String fdCode = request.getParameter("fdCode");
			TreeMap map = new TreeMap();
			map.put("Y", "Active");
			map.put("N", "DeActive");
			map.put("D", "Delete");
			map.put("L", "Lock");
			final String brokerInfo[][] = brokerBean.getBrokerLoginId(brokerId,"Customer","LoginId");
			final String agency = cust.getAgencyCode(custLoginId);
			final String[][] custMOCList = brokerBean.getSubFreightCustomerInDetails(agency,"Customer",customerStatus);
			final String masterCustomer[][] = brokerBean.getMasterFreightName(agency,"Customer");
			String statusType = "";
			String custName;
			String agencyCode;
			for (int i = 0; i < custMOCList.length; ++i) 
			{
				custName = custMOCList[i][0];
				statusType = map.get(custMOCList[i][1]).toString();
				agencyCode = custMOCList[i][4];
				CustomerDisplayBean bean = new CustomerDisplayBean();
				bean.setSno(i + 1);
				bean.setCustName(custName);
				bean.setStatus(statusType);
				bean.setAgencyCode(agencyCode);
				list.add(bean);
			}
			System.out.println("CustomerMOCListStatus =========="+list.size());
			
			if(customerStatus.equalsIgnoreCase("SubCustomer")){
				request.setAttribute("subCount", new Integer(1));
			}
			if(masterCustomer.length > 0 ){
				request.setAttribute("MasterCustomerName", masterCustomer[0][1]);
			}
			
			request.setAttribute("result", list);
			request.setAttribute("fdCode", fdCode);
			request.setAttribute("brokerLogin", brokerId);
			if(brokerInfo.length > 0 ){
				request.setAttribute("brokerAgencyCode", brokerInfo[0][0]);
				request.setAttribute("brokerOrgName", brokerInfo[0][1]);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerCreation/CustomerMOCListStatus.jsp");
			dispatcher.forward(request, response);
		}
	}
} // Class
