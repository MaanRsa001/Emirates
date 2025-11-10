package rsa.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class print4Clauses extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(print4Clauses.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	private static final long serialVersionUID = 1030498627115204350L;

	protected void processRequest(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException,BaseException 
	{
		logger.info("========One Off print4Clauses processRequest method()");
		logger.debug(ENTER);
		finalprint finalBean;
		finalBean = new finalprint();
		PDFCreatorBean creatorBean;
		creatorBean = new PDFCreatorBean();

		try{
			HttpSession session;
			String PolicyNoFour = "";
			String usrModeController = "";
			session = request.getSession(false);
			if (session.getAttribute("ses") == null) 
			{
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			usrModeController = (String) session.getAttribute("userLoginMode") == null ? ""	: (String) session.getAttribute("userLoginMode");
			if ("".equalsIgnoreCase(usrModeController)	|| " ".equalsIgnoreCase(usrModeController)) {
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			String PolicyNo;
			String loginId;
			String branchCode;
			String belongingBranch;
			String applicationNo;
			String proposalNo;
			PolicyNo = finalBean.isNull(request.getParameter("policynumber"),"0");
			proposalNo = finalBean.isNull(request.getParameter("proposalNo"),"0");
			loginId = finalBean.isNull(request.getParameter("loginid"),"");
			branchCode = (String)session.getAttribute("LoginBranchCode");
			belongingBranch = (String)session.getAttribute("BelongingBranch");
			applicationNo = finalBean.getApplicationNo(PolicyNo);

			logger.info("========loginId is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			logger.info("========applicationNo   is " + applicationNo);
			logger.info("========branchCode   is " + branchCode);
			logger.info("========belongingBranch   is " + belongingBranch);
			
			PolicyNoFour = PolicyNo.replaceAll("/", "-");
			String fileName,fileName1;
			if("3".equals((String)session.getAttribute("product_id"))){
				PolicyNoFour = PolicyNoFour+"Clauses.pdf";
			}else{
				PolicyNoFour = PolicyNoFour+"ClausesOpen.pdf";
			}
			fileName = request.getSession().getServletContext().getRealPath("/" + "/clausespdf/"  + PolicyNoFour);
			fileName1="/clausespdf/"  + PolicyNoFour;
			
			File pdfFileTest = new File(fileName);
			
			logger.info("========FILE NAME IS " + fileName);
			logger.info("========FILE Exists IS " + pdfFileTest.exists());
			if(pdfFileTest.exists()) {
				session.setAttribute("pdfFilePath", fileName1);
				response.sendRedirect(request.getContextPath()+"/pdfReport.action");
			}
			else {
				List<InputStream> pdfs = new ArrayList<InputStream>();
	
				String pdfFile[][] = new String[0][0];
				if("3".equals((String)session.getAttribute("product_id"))) {
					pdfFile = finalBean.getPdf("Clauses",applicationNo,belongingBranch);
				}
				else if("11".equals((String)session.getAttribute("product_id"))) {
					pdfFile = finalBean.getOpenCoverPdf(proposalNo,branchCode,belongingBranch);
				}
				if(pdfFile.length>0) {
					for(int e=0;e<pdfFile.length;e++)
					{
						File file = new File(request.getSession().getServletContext().getRealPath("/" + "/clauses/"  + pdfFile[e][1]));
						if(file.exists()) {
							pdfs.add(new FileInputStream(file));
						}
					}
	
					//pdfs.add(new FileInputStream("d:\\1.pdf"));
					//pdfs.add(new FileInputStream("d:\\2.pdf"));
					OutputStream output = new FileOutputStream(request.getSession().getServletContext().getRealPath("/" + "/clausespdf/"  + PolicyNoFour));
					creatorBean.concatPDFs(pdfs, output, true,PolicyNo);
	
					//response.sendRedirect(request.getContextPath()+fileName1);
					session.setAttribute("pdfFilePath", fileName1);
					response.sendRedirect(request.getContextPath()+"/pdfReport.action");
				}
				else {
					response.sendRedirect("Copy of information Admin.jsp?pdfStatus=NODATAS");
				}
			}
		}catch (Exception e) 
		{
			throw new BaseException(e, "Error");
		}
		logger.debug(EXIT);
		

	}

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		try{
			processRequest(request,response);
		}catch(Exception e){
			logger.debug(e);
		}
	}
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException, IOException {
		try{
			processRequest(request,response);
		}catch(Exception e){
			logger.debug(e);
		}
	}
	public String getServletInfo() {
		return "Short description";
	}
}