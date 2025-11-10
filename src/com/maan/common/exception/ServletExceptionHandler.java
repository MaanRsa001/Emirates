package com.maan.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;



public class ServletExceptionHandler extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(ServletExceptionHandler.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info("royal test common exception from JSP doGet method()");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String exceptionType = getRequestAttribute(req,
				"javax.servlet.error.exception_type",
				"status code not supplied");
		String errorMsg = getRequestAttribute(req,
				"javax.servlet.error.message", "error msg not supplied");

		Object exception = req.getAttribute("javax.servlet.error.exception");
		logger.info("Exception object printed by kanaka --> "+exception);
		if (exception != null) {
			((Exception)exception).printStackTrace();
		}
		
		
		RequestDispatcher dispatcher = req
				.getRequestDispatcher("../login/error_page.jsp");
		dispatcher.forward(req, res);

		// exception attribute available as of servlet 2.3 API
		

		pw.println("<h1>Custom Exception Handler</h1>");
		pw.println("<h2>Exception type: " + exceptionType + "</h1>");
		pw.println("<h2>Exception Message: " + errorMsg + "</h1>");
		pw.println("<p>Problem accessing: " + req.getRequestURI() + "</p>");

		// if we have the exception dump it out to the page
		if (exception != null) {
			ByteArrayOutputStream bos = null;
			try {
				pw.println("<p><hr noshade><p><pre>");
				bos = new ByteArrayOutputStream();
				PrintWriter writer = new PrintWriter(bos, true);
				((Exception) exception).printStackTrace(writer);
				pw.println(bos.toString());
			} finally {
				pw.println("</pre>");
				if (bos != null)
					bos.close();
			}
		}
		pw.println("<p><hr><p>");
		pw.println(new java.util.Date(System.currentTimeMillis()));
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		logger.info("royal test common exception from JSP doPost method()");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		System.out.println(req.getAttribute("globalerror"));
		Object exception = req.getAttribute("javax.servlet.error.exception");
		logger.info("Exception object printed by kanaka --> "+exception);
		if (exception != null) {
			((Exception)exception).printStackTrace();
		}
		
		RequestDispatcher dispatcher = req
				.getRequestDispatcher("../login/error_page.jsp");
		dispatcher.forward(req, res);

		String exceptionType = getRequestAttribute(req,
				"javax.servlet.error.exception_type",
				"status code not supplied");
		String errorMsg = getRequestAttribute(req,
				"javax.servlet.error.message", "error msg not supplied");

		// exception attribute available as of servlet 2.3 API
		

		pw.println("<h1>Custom Exception Handler</h1>");
		pw.println("<h2>Exception type: " + exceptionType + "</h1>");
		pw.println("<h2>Exception Message: " + errorMsg + "</h1>");
		pw.println("<p>Problem accessing: " + req.getRequestURI() + "</p>");

		// if we have the exception dump it out to the page
		if (exception != null) {
			ByteArrayOutputStream bos = null;
			try {
				pw.println("<p><hr noshade><p><pre>");
				bos = new ByteArrayOutputStream();
				PrintWriter writer = new PrintWriter(bos, true);
				((Exception) exception).printStackTrace(writer);				
				pw.println(bos.toString());
				((Exception)exception).printStackTrace();
			} finally {
				pw.println("</pre>");
				if (bos != null)
					bos.close();
			}
		}
		pw.println("<p><hr><p>");
		pw.println(new java.util.Date(System.currentTimeMillis()));
	}

	private String getRequestAttribute(HttpServletRequest req, String name,
			String defaultValue) {
		Object value = req.getAttribute(name);
		return (value != null) ? value.toString() : defaultValue;
	}

}
