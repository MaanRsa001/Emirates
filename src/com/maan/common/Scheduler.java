package com.maan.common;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class Scheduler extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private SendMail timer;
	/**
	 * Constructor of the object.
	 */
	public Scheduler() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		if (timer != null) {
	           timer.getTimer().cancel();
	           timer = null;
	      }
		super.destroy();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
        SendMail.sendMail();
	}

}
