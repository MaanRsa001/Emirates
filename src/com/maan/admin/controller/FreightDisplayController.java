package com.maan.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.admin.DAO.AdminBean;
import com.maan.admin.DAO.BrokerCreationBean;
import com.maan.admin.DAO.BrokerDisplayBean;
import com.maan.admin.DAO.UserDisplayBean;

public class FreightDisplayController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		transfer(request, response);
	}

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		transfer(request, response);
	}

	private void transfer(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		
		String requestForm = request.getParameter("from");
		System.out.println("requestForm.........Multi...."+requestForm);
		if (requestForm.equalsIgnoreCase("list")) {
			
			final ArrayList list = new ArrayList();
			final BrokerCreationBean broker = new BrokerCreationBean();
			final AdminBean admin = new AdminBean();
			final String loginId = (String) request.getSession().getAttribute(
					"user");
			final String adminBranch = admin.getAdminBranch(loginId);
			
			request.getSession().setAttribute("adminBranch", adminBranch);
			String[][] brokerList = broker.getBrokersList(adminBranch);

			for (int i = 0; i < brokerList.length; ++i) {

				BrokerDisplayBean bean = new BrokerDisplayBean();
				bean.setSno(i + 1);
				bean.setBrokerName(brokerList[i][2]);
				bean.setAgenyID(brokerList[i][3]);
				bean.setMissippi(brokerList[i][4]);
				bean.setCutID(brokerList[i][0]);
				bean.setDate(brokerList[i][5]);
				
				String noofUser = broker.getUserCount(brokerList[i][3],
						"Freight");
				
				noofUser = noofUser.length() == 0 ? "0" : noofUser;
				bean.setNoOfUser(noofUser);
				bean.setLoginID(brokerList[i][6]);

				list.add(bean);
			}
			request.setAttribute("result", list);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/freightCreation/FreightDisplayList.jsp");
			dispatcher.forward(request, response);
			
		}else if (requestForm.equalsIgnoreCase("userList")) {
			
			final BrokerCreationBean brokerBean = new BrokerCreationBean();
			final String userType = request.getParameter("type");
			final String id = request.getParameter("id");
			final String brokerInfo[][] = brokerBean.getBrokerLoginId(id,userType,"agencyCode");
			TreeMap map = new TreeMap();
			map.put("Y", "Active");
			map.put("N", "DeActive");
			map.put("D", "Delete");
			map.put("L", "Lock");
			
			
			String type ;
			final ArrayList list = new ArrayList();
			final String[][] brokerList = brokerBean.getBrokerInDetails(id,userType);
			for (int i = 0; i < brokerList.length; ++i) {
				String noofUser = brokerBean.getMultiUserCount(brokerList[i][4],"Freight");
				noofUser = noofUser.length() == 0 ? "0" : noofUser;
				
				UserDisplayBean bean = new UserDisplayBean();
				bean.setNoOfUser(noofUser);
				bean.setSno(i + 1);
				bean.setBrokerName(brokerList[i][0]);
				type = map.get(brokerList[i][1]).toString();
				bean.setStatus(type);
				bean.setUserType(brokerList[i][2]);
				bean.setAgencyCode(brokerList[i][4]);
				list.add(bean);
			}
			request.setAttribute("result", list);
			request.setAttribute("subCount", new Integer(1));
			if(brokerInfo.length > 0 ){
				request.setAttribute("brokerLoginId", brokerInfo[0][0]);
				request.setAttribute("brokerOrgName", brokerInfo[0][1]);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/freightCreation/FreightUserDisplayList.jsp");
			dispatcher.forward(request, response);
			
		}else if (requestForm.equalsIgnoreCase("SubFreightList")) {
			final BrokerCreationBean brokerBean = new BrokerCreationBean();
			final String userType = request.getParameter("type");
			final String id = request.getParameter("agencyID");
			
			TreeMap map = new TreeMap();
			map.put("Y", "Active");
			map.put("N", "DeActive");
			map.put("D", "Delete");
			map.put("L", "Lock");
			
			
			String type ;
			final ArrayList list = new ArrayList();
			final String[][] brokerList = brokerBean.getSubFreightCustomerInDetails(id,userType,"");
			final String brokerInfo[][] = brokerBean.getBrokerLoginId(id,userType,"agencyCode");
			final String masterFreight[][] = brokerBean.getMasterFreightName(id,userType);
			for (int i = 0; i < brokerList.length; ++i) {
				UserDisplayBean bean = new UserDisplayBean();
				bean.setSno(i + 1);
				bean.setBrokerName(brokerList[i][0]);
				type = map.get(brokerList[i][1]).toString();
				bean.setStatus(type);
				bean.setUserType(brokerList[i][2]);
				bean.setAgencyCode(brokerList[i][4]);
				list.add(bean);
			}
			request.setAttribute("result", list);
			request.setAttribute("subCount", new Integer(0));
			request.setAttribute("masterFreightAgency", id);
			request.setAttribute("LoginCreationStatus", "SubFreight");
		
			if(masterFreight.length > 0 ){
				request.setAttribute("masterFreightName", masterFreight[0][1]);
			}
			
			if(brokerInfo.length > 0 ){
				request.setAttribute("brokerLoginId", brokerInfo[0][0]);
				request.setAttribute("brokerOrgName", brokerInfo[0][1]);
			}
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/freightCreation/FreightUserDisplayList.jsp");
			dispatcher.forward(request, response);
		}
	}

}
