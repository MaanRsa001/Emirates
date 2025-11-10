package com.maan.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maan.admin.DAO.AdminBean;
import com.maan.admin.DAO.BrokerCreationBean;
import com.maan.admin.DAO.BrokerDisplayBean;

public class BrokerDisplayContoller extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		transfer(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		transfer(request, response);
	}

	public void transfer(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		final BrokerCreationBean broker = new BrokerCreationBean();
		final AdminBean admin = new AdminBean();

		String logBranch = (String) request.getSession().getAttribute(
				"LoginBranchCode");
		final String loginId = (String) request.getSession().getAttribute(
				"user");
		final String adminBranch = admin.getAdminBranch(loginId);

		request.getSession().setAttribute("adminBranch", adminBranch);
		final ArrayList list = new ArrayList();

		String[][] brokerList = broker.getBrokersList(adminBranch);
		
		String brokerName;
		String missippiCode;
		String createdDate;
		String customerID;
		String agentID;
		System.out.println("brokerName....."+brokerList.length);
		for (int i = 0; i < brokerList.length; ++i) {
			brokerName = brokerList[i][2];
			missippiCode = brokerList[i][4];
			createdDate = brokerList[i][5];
			customerID = brokerList[i][0];
			agentID = brokerList[i][3];
			System.out.println("brokerName....."+brokerName);
			BrokerDisplayBean bean = new BrokerDisplayBean();
			bean.setSno(i + 1);
			bean.setBrokerName(brokerName);
			bean.setAgenyID(agentID);
			bean.setMissippi(missippiCode);
			bean.setCutID(customerID);
			bean.setDate(createdDate);
			list.add(bean);
		}
		System.out.println("List =========="+list.size());
		request.setAttribute("result", list);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/AdminBrokerList.jsp");
		dispatcher.forward(request, response);
	}
}
