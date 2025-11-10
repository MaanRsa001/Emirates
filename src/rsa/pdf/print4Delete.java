package rsa.pdf;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;




public class print4Delete extends HttpServlet {
	private static final Logger logger = LogUtil.getLogger(print4Delete.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 2288752023477522859L;
	
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String FAIL = "Failed to Delete - ";
	final static transient private String DEL = "Delete: write protected: ";
	final static transient private String SCOPEN = "ScheduleOpen";
	final static transient private String DEBITPDF = "/debitpdf/";
	final static transient private String SCHEDULE = "Schedule";
	final static transient private String LOGMODE = "userLoginMode";
	final static transient private String CREDITPDF = "/creditpdf/";
	private transient HttpSession session = null;
	private transient String originalCopy, duplicate, debit, debits, original, multi, credit, credits;
	private transient File fileOne, fileTwo, fileThree, fileFour, fileFive, fileSix, fileSeven, fileEight;
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
	try{
		logger.info("One Off print4Delete doPost method()");
		logger.debug(ENTER);
			response.setContentType("text/html");
			String PolicyNoFour = "";
			String extension;
			extension = ".pdf";
			session = request.getSession(true);
			if (session.getAttribute("ses") == null) {
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = (String) session.getAttribute(LOGMODE) == null ? ""
					: (String) session.getAttribute(LOGMODE);
			String displayText;
			displayText = request.getParameter("displayText") == null ? "": request.getParameter("displayText");
			String PolicyNo;
			PolicyNo = request.getParameter("policynumber") == null ? "0": request.getParameter("policynumber");
			String productId;
			productId = request.getParameter("productID") == null ? "": request.getParameter("productID");
			/*StringTokenizer token;
			token = new StringTokenizer(PolicyNo, "-");
			while (token.hasMoreTokens()) {
				PolicyNoFour = token.nextToken();
			}*/

			PolicyNoFour = PolicyNo;
//			Block Added by Chinna
			if(PolicyNoFour!=null && PolicyNoFour.length()>0 && PolicyNoFour.indexOf("/")!=-1)
			{
				PolicyNoFour=PolicyNoFour.replaceAll("/", "_");
				System.out.println("polino: >>>>>>>"+PolicyNoFour);
			}
			
			if ("DELETE".equalsIgnoreCase(displayText)) 
			{
				if ("3".equals(productId)) 
				{
					original = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/"  + PolicyNoFour + SCHEDULE + extension);
					multi = request.getSession().getServletContext().getRealPath("/" + "/LiveOriginalMultiplePdf/"  + PolicyNoFour + SCHEDULE + extension);
					originalCopy = request.getSession().getServletContext().getRealPath("/" + "/OriginalCopyPdf/"  + PolicyNoFour + SCHEDULE + extension);
					duplicate = request.getSession().getServletContext().getRealPath("/" + "/duplicatecopypdf/"  + PolicyNoFour + SCHEDULE + extension);
					debit = request.getSession().getServletContext().getRealPath("/" + DEBITPDF  + PolicyNoFour + "Debit" + extension);
					debits = request.getSession().getServletContext().getRealPath("/" + DEBITPDF  + PolicyNoFour + SCHEDULE + extension);
					credit = request.getSession().getServletContext().getRealPath("/" + CREDITPDF  + PolicyNoFour + "Credit" + extension);
					credits = request.getSession().getServletContext().getRealPath("/" + CREDITPDF  + PolicyNoFour + SCHEDULE + extension);
				} 
				else  
				{
					original = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/"  + PolicyNoFour + SCOPEN + extension);
					multi = request.getSession().getServletContext().getRealPath("/" + "/LiveOriginalMultiplePdf/"  + PolicyNoFour + SCOPEN + extension);
					originalCopy = request.getSession().getServletContext().getRealPath("/" + "/OriginalCopyPdf/"  + PolicyNoFour + SCOPEN + extension);
					duplicate = request.getSession().getServletContext().getRealPath("/" + "/duplicatecopypdf/"  + PolicyNoFour + SCOPEN + extension);
					debit = request.getSession().getServletContext().getRealPath("/" + DEBITPDF  + PolicyNoFour + "DebitOpen" + extension);
					debits = request.getSession().getServletContext().getRealPath("/" + DEBITPDF  + PolicyNoFour + SCOPEN + extension);
					credit = request.getSession().getServletContext().getRealPath("/" + CREDITPDF  + PolicyNoFour + "CreditOpen" + extension);
					credits = request.getSession().getServletContext().getRealPath("/" + CREDITPDF  + PolicyNoFour + SCOPEN + extension);
				}
				fileOne = new File(originalCopy);
				fileTwo = new File(duplicate);
				fileThree = new File(debit);
				fileFour = new File(debits);
				fileFive = new File(original);
				fileSix = new File(multi);
				fileSeven = new File(credit);
				fileEight = new File(credits);
	
				if (fileOne.exists()) {
					request.setAttribute("A1", originalCopy);
				}
				if (fileTwo.exists()) {
					request.setAttribute("A2", duplicate);
				}
				if (fileThree.exists()) {
					request.setAttribute("A3", debit);
				}
				if (fileFour.exists()) {
					request.setAttribute("A4", debits);
				}
				if (fileFive.exists()) {
					request.setAttribute("A5", original);
				}
				if (fileSix.exists()) {
					request.setAttribute("A6", multi);
				}
				if (fileSeven.exists()) {
					request.setAttribute("A7", credit);
				}
				if (fileEight.exists()) {
					request.setAttribute("A8", credits);
				}
			}
			logger.debug(EXIT);
			
			RequestDispatcher dispatcher;
			dispatcher = request.getRequestDispatcher("/admin/deleteinfo.jsp?user=admin");
			if (dispatcher != null){
				dispatcher.forward(request, response);
			}
	 }catch(Exception e){
		   logger.debug(e);
	   }
	}

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
	try{	
		logger.info("One Off print4Delete doGet method()");
		logger.debug(ENTER);
			response.setContentType("text/html");
			
			session = request.getSession(false);
			if (session.getAttribute("ses") == null) {
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			com.maan.DBCon.DBConnectionStatus.statusStatic = (String) session.getAttribute(LOGMODE) == null ? ""
					: (String) session.getAttribute(LOGMODE);
			StringBuffer error;
			error = new StringBuffer();
			String[] res;
			res = request.getParameterValues("del") == null ? new String[0]: request.getParameterValues("del");
			if (res.length > 0) 
			{
				for (int i = 0; i < res.length; i++) 
				{
					if ("A1".equalsIgnoreCase(res[i])) 
					{
						if (fileOne.exists()) {
							if (fileOne.canWrite()){
								fileOne.delete();
							}else{
								error.append(DEL);
								error.append(originalCopy);
							}
						}else{
							error.append(FAIL);
							error.append(originalCopy);
						}
					}
					if ("A2".equalsIgnoreCase(res[i])) 
					{
						if (fileTwo.exists()) {
							if (fileTwo.canWrite()){
								fileTwo.delete();
							}else{
								error.append(DEL);
								error.append(duplicate);
							}
						} else{
							error.append(FAIL);
							error.append(duplicate);
						}
					}
					if ("A3".equalsIgnoreCase(res[i])) 
					{
						if (fileThree.exists()) {
							if (fileThree.canWrite()){
								fileThree.delete();
							}else{
								error.append(DEL);
								error.append(debit);
							}	
						}else{
							error.append(FAIL);
							error.append(debit);
						}
					}
					if ("A4".equalsIgnoreCase(res[i])) 
					{
						if (fileFour.exists()) {
							if (fileFour.canWrite()){
								fileFour.delete();
							}else{
								error.append(DEL);
								error.append(debits);
							}	
						}else{
							error.append(FAIL);
							error.append(debits);
						}
					}
					if ("A5".equalsIgnoreCase(res[i])) 
					{
						if (fileFive.exists()) {
							if (fileFive.canWrite()){
								fileFive.delete();
							}else{
								error.append(DEL);
								error.append(debits);
							}	
						}else{
							error.append(FAIL);
							error.append(debits);
						}
					}
					if ("A6".equalsIgnoreCase(res[i])) 
					{
						if (fileSix.exists()) {
							if (fileSix.canWrite()){
								fileSix.delete();
							}else{
								error.append(DEL);
								error.append(debits);
							}	
						} else{
							error.append(FAIL);
							error.append(debits);
						}
					}
					if ("A7".equalsIgnoreCase(res[i])) 
					{
						if (fileSeven.exists()) {
							if (fileSeven.canWrite()){
								fileSeven.delete();
							}else{
								error.append(DEL);
								error.append(credit);
							}	
						} else{
							error.append(FAIL);
							error.append(credit);
						}
					}
					if ("A8".equalsIgnoreCase(res[i])) 
					{
						if (fileEight.exists()) {
							if (fileEight.canWrite()){
								fileEight.delete();
							}else{
								error.append(DEL);
								error.append(credits);
							}	
						} else{
							error.append(FAIL);
							error.append(credits);
						}
					}
				}
	
			}

		if (error.length()<=0 && (res.length > 0)){
			request.setAttribute("error", "Succesfully Deleted");
		}else{
			request.setAttribute("error", error.toString());
		}
		logger.debug(EXIT);
		
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("/admin/deleteinfo.jsp?user=admin");
		if (dispatcher != null){
			dispatcher.forward(request, response);
		}
	 }catch(Exception e){
		   logger.debug(e);
	   }
	}
}