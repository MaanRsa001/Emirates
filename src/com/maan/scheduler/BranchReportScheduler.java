package com.maan.scheduler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BranchReportScheduler extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BranchReportTimerTask timer;

	/**
	 * Constructor of the object.
	 */
	public BranchReportScheduler() {
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
	public void init() throws ServletException {
		BranchReportTimerTask.initTimerTask();
	}
}

