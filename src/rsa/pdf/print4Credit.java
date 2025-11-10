package rsa.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class print4Credit extends HttpServlet 
{

	private static final Logger logger = LogUtil.getLogger(print4Credit.class);
	/**
	 * 
	 */
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	private static final long serialVersionUID = 1030498627115204350L;
	final static transient private String CREDIT = "Credit";
	final static transient private String IMG = "/images/";
	final static transient private String POLICYNO = "POLICYNO";
	final static transient private String HUND = "100";
	final static transient private String THOU = "1000";
	final static transient private String TAB = "\t\t";

	protected void processRequest(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException,BaseException 
	{
		logger.info("One Off print4Credit processRequest method()");
		logger.debug(ENTER);
		finalprint finalBean;
		finalBean = new finalprint();
		PDFCreatorBean creatorBean;
		creatorBean = new PDFCreatorBean();
		
		try{
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			HttpSession session;
			double taxPercent = 0;
			String PolicyNoFour = "";
			String displayText = "";
			String braAddress;
			String usrModeController = "";
			String displayMode="";
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String headImage;
			String footImage;
			String signImage;
			String brokerBra;
			String cid="";
			String cols="";
			int decimalDigit = 0;
			//
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
			com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;
			logger.info("the user Mode is" + usrModeController);
			displayText = request.getParameter("displayText") == null ? "": request.getParameter("displayText");
			if ("Test".equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText)) {
				displayText = "INVALID CREDIT";
			}
			
			String PolicyNo;
			String loginId;
			PolicyNo = finalBean.isNull(request.getParameter("policynumber"),"0");
			loginId = finalBean.isNull(request.getParameter("loginid"),"");
			String currencyOption = finalBean.isNull(request.getParameter("currencyOption"),"");
			displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
			logger.info("========loginId is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			logger.info("========currencyOption   is " + currencyOption);
			
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
				//currencyType = (String)brokerDetails.get("CurrencyAbb");
			}
				String placeCode[][];
				placeCode = finalBean.getPlaceCodes(PolicyNo,CREDIT,"3",POLICYNO);
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				
				String fontPath;
				fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
				String urlPath;
				String urlPathFooter;
				urlPath = request.getSession().getServletContext().getRealPath("/" + IMG+headImage);
				urlPathFooter = request.getSession().getServletContext().getRealPath("/" + IMG+footImage);
				PolicyNoFour = PolicyNo.replaceAll("/", "-");
				String freightUser;
				String freightBroker;
				freightUser = finalBean.isNull((String)session.getAttribute("freight"),"");
				freightBroker = finalBean.isNull((String)session.getAttribute("freightBroker"),"");
				response.setContentType("application/pdf");
				String fileName,fileName1;
				if("3".equals((String)session.getAttribute("product_id"))){
					PolicyNoFour = PolicyNoFour+"Credit.pdf";
				}else{
					if("NormalMultiple".equalsIgnoreCase(displayMode))
					{
						PolicyNoFour = PolicyNoFour+"QuoteCreditOpen.pdf";
					}
					else
					{
						PolicyNoFour = PolicyNoFour+"CreditOpen.pdf";
					}
				}
				if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) {
				fileName = request.getSession().getServletContext().getRealPath("/" + "/OriginalCopyPdf/"  + PolicyNoFour);
				fileName1="/OriginalCopyPdf/"  + PolicyNoFour;
				} else if ("COPY".equalsIgnoreCase(displayText)) {
					fileName = request.getSession().getServletContext().getRealPath("/" + "/duplicatecopypdf/" + PolicyNoFour);
					fileName1="/duplicatecopypdf/" + PolicyNoFour;
				} else if ("INVALID CREDIT".equalsIgnoreCase(displayText) || "TEST CREDIT".equalsIgnoreCase(displayText)) {
					fileName = request.getSession().getServletContext().getRealPath("/" + "/testpolicypdf/"  + PolicyNoFour);
					fileName1="/testpolicypdf/"  + PolicyNoFour;
				} else {
					fileName = request.getSession().getServletContext().getRealPath("/" + "/creditpdf/"  + PolicyNoFour);
					fileName1="/creditpdf/"  + PolicyNoFour;
				}
				logger.info("the X FILE NAME IS " + fileName);
				/*
				 * 
				 * Removed for Jasper Reports 
				 * 
				 * 
				File pdfFile;
				pdfFile = new File(fileName);
				String url=request.getSession().getServletContext().getRealPath("/"+ "/images/");
				creatorBean.setCurrencyOption(currencyOption);
				creatorBean.setAdnicIssuer((String)session.getAttribute("RSAISSUER"));
				creatorBean.setUsrModeController(usrModeController);
				creatorBean.setDisplayMode(displayMode);
				
				double netPremium=0.0;
				netPremium=finalBean.getnetPremium(PolicyNo);
				if(netPremium>=0){
				creatorBean.writeCreditPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,loginId,PolicyNo,fileName,url);
				}else{
				creatorBean.writeNewCreditPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,loginId,PolicyNo,fileName,url);
				}*/
				
				com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
				jr.creditNote(PolicyNo, brokerBra, fileName);
				//response.sendRedirect(request.getContextPath()+fileName1);
				session.setAttribute("pdfFilePath", fileName1);
				response.sendRedirect(request.getContextPath()+"/pdfReport.action");
	
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