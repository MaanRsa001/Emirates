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

public class UserDiplayController extends HttpServlet {

	/**
	 * 
	 */
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

	public void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		transfer(request, response);
	}

	private void transfer(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		String requestForm = request.getParameter("from");

		if (requestForm.equalsIgnoreCase("broker")) {
			
			final BrokerCreationBean brokerBean = new BrokerCreationBean();

			final AdminBean admin = new AdminBean();
			final String loginId = (String) request.getSession().getAttribute(
					"user");
			final String adminBranch = admin.getAdminBranch(loginId);
			request.getSession().setAttribute("adminBranch", adminBranch);
			final ArrayList list = new ArrayList();
			String[][] brokerList = brokerBean.getBrokersList(adminBranch);

			for (int i = 0; i < brokerList.length; ++i) {

				BrokerDisplayBean bean = new BrokerDisplayBean();
				bean.setSno(i + 1);
				bean.setBrokerName(brokerList[i][2]);
				bean.setAgenyID(brokerList[i][3]);
				bean.setMissippi(brokerList[i][4]);
				bean.setCutID(brokerList[i][0]);
				bean.setDate(brokerList[i][5]);
				String noofUser = brokerBean.getUserCount(brokerList[i][3],
						"User");
				String acNoofUser = brokerBean.getUserCount(brokerList[i][3],
						"agent");
				noofUser = noofUser.length() == 0 ? "0" : noofUser;
				acNoofUser = acNoofUser.length() == 0 ? "0" : acNoofUser;
				bean.setNoOfUser(noofUser);
				bean.setAcNoOfUser(acNoofUser);
				bean.setLoginID(brokerList[i][6]);

				list.add(bean);
			}
			request.setAttribute("result", list);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/admin/AdminUserList.jsp");
			dispatcher.forward(request, response);
			
		} else if (requestForm.equalsIgnoreCase("userList")) {
			
			final BrokerCreationBean brokerBean = new BrokerCreationBean();
			final String userType = request.getParameter("type");
			final String id = request.getParameter("id");
			
			TreeMap map = new TreeMap();
			map.put("Y", "Active");
			map.put("N", "DeActive");
			map.put("D", "Delete");
			map.put("L", "Lock");
			
			System.out.println(map.get("Y"));
			
			String type ;
			final ArrayList list = new ArrayList();
			String[][] brokerList = brokerBean.getBrokerInDetails(id,userType);
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
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/admin/AdminUserSpecList.jsp");
			dispatcher.forward(request, response);
		}

	}
}
;