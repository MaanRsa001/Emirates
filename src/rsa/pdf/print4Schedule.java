package rsa.pdf;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;


public class print4Schedule extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(print4Schedule.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8566774016440734848L;
	
	
	
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String IMG = "/images/";
	
	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
	IOException,BaseException 
	{
		String reqFrom=request.getParameter("reqFrom")==null?"":request.getParameter("reqFrom");
		if("QuotePrint".equalsIgnoreCase(reqFrom)){
			logger.info("print4Schedule processRequest()-Enter");
			QutationSchedule creatorBean = new QutationSchedule();
			HttpSession session = request.getSession(false);
			String quoteNo=request.getParameter("quote_no")==null?"0":request.getParameter("quote_no");
			String filePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/" + quoteNo+".pdf");
			String filePath1="/quotepdf/" + quoteNo+".pdf";
			/*File pdfFile;
			pdfFile = new File(filePath);
			if (pdfFile.exists()){
				response.sendRedirect(request.getContextPath()+filePath1);
			}else
			{	*/
				creatorBean.setFilePath(filePath);
				creatorBean.setLoginBranch((String)session.getAttribute("LoginBranchCode"));
				creatorBean.setBelongingBranch((String)session.getAttribute("BelongingBranch"));
				creatorBean.setBrokerDetails((HashMap)session.getAttribute("BrokerDetails"));
				
				creatorBean.setOpenCoverNo(request.getParameter("openCoverNo") == null ? (String) session
						.getAttribute("openCoverNo") == null ? "0"
								: (String) session.getAttribute("openCoverNo")
								: request.getParameter("openCoverNo"));
				creatorBean.setProductId(request.getParameter("selectProd") == null ? (String) session
						.getAttribute("product_id") == null ? "0"
								: (String) session.getAttribute("product_id")
								: request.getParameter("selectProd"));
				creatorBean.setUser((String)session.getAttribute("user"));
				String[][] placeCode;
				finalprint finalBean = new finalprint();
				/*
				 * Removed for Jasper Reports By Aswin
				 * 
				 * 
				 * placeCode = finalBean.getPlaceCodes(quoteNo,"Schedule",creatorBean.getProductId(),"QuoteNo");
				creatorBean.setHeadImage(request.getSession().getServletContext().getRealPath("/" + IMG+finalBean.isNull(placeCode[0][1], "")));
				creatorBean.setFootImage(request.getSession().getServletContext().getRealPath("/" + IMG+finalBean.isNull(placeCode[0][2], "")));
				creatorBean.setSignImage(request.getSession().getServletContext().getRealPath("/" + IMG+finalBean.isNull(placeCode[0][3], "")));
				creatorBean.setStampImage(request.getSession().getServletContext().getRealPath("/" + IMG+finalBean.isNull(placeCode[0][4],"")));
				creatorBean.setFontPath(request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf"));
				creatorBean.setBulletImg(request.getSession().getServletContext().getRealPath("/" + IMG+"bullet.jpg"));
				creatorBean.writeQuoteSchedule(quoteNo);*/
				
				String applicationNo = finalBean.getApplicationNo1(quoteNo);
				
				com.maan.report.JasperReports js = new com.maan.report.JasperReports();
				js.certificatePrint(applicationNo, creatorBean.getLoginBranch(), creatorBean.getBelongingBranch(), filePath, creatorBean.getProductId());
				
				//response.sendRedirect(request.getContextPath()+filePath1);
				session.setAttribute("pdfFilePath", filePath1);
				response.sendRedirect(request.getContextPath()+"/pdfReport.action");
			//}
			logger.info("print4Schedule processRequest()-Exit");
		}else
		{
			logger.info("One Off print4Schedule processRequest method()");
			logger.debug(ENTER);
			finalprint finalBean;
			finalBean = new finalprint();
			PDFCreatorBean creatorBean;
			creatorBean = new PDFCreatorBean();
			try
			{
				// Some Common Operations
					String headImage;
					String footImage;
					String signImage;
					String stampImage;
					String stampStatus = "";
					String brokerBra;
					String belongingBranch;
					int decimalDigit = 0;
					String usrModeController;
					
					String premiumYes;
					String bankerOption;
					String bankerAssOption;
					String currencyOption;
					String excessOption;
					String viewStatus;
					String displayMode;
					String PolicyNoFour = "";
					String cid = "";
					HttpSession session;
					session = request.getSession(false);
					if (session.getAttribute("ses") == null) 
					{
						response.sendRedirect("login/error_messg_pdf.jsp");
						return;
					}
					usrModeController = (String) session.getAttribute("userLoginMode") == null ? "":(String) session.getAttribute("userLoginMode");
					if ("".equalsIgnoreCase(usrModeController)|| " ".equalsIgnoreCase(usrModeController)) 
					{
						response.sendRedirect("login/error_messg_pdf.jsp");
						return;
					}
					com.maan.DBCon.DBConnectionStatus.statusStatic = usrModeController;
					String displayText;
					displayText = finalBean.isNull(request.getParameter("displayText"),"");
					if ("Test".equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText)) 
					{
						displayText = "INVALID POLICY";
					}
					String PolicyNo;
					PolicyNo = finalBean.isNull(request.getParameter("policynumber"),"0");
					String quoteDraftNo;
					quoteDraftNo = PolicyNo;
					String loginId;
					loginId = finalBean.isNull(request.getParameter("loginid"),"");
					premiumYes = finalBean.isNull(request.getParameter("printoption"),"");
					bankerOption = finalBean.isNull(request.getParameter("bankerOption"),"");
					bankerAssOption = finalBean.isNull(request.getParameter("bankerAssuredOption"),"");
					currencyOption = finalBean.isNull(request.getParameter("currencyOption"),"");
					excessOption = finalBean.isNull(request.getParameter("excessOption"),"");
					viewStatus = finalBean.isNull(request.getParameter("viewStatus"),"");
					displayMode = finalBean.isNull(request.getParameter("displayMode"),"");
		
					logger.info("========loginId   is " + loginId);
					logger.info("========policynumber   is " + PolicyNo);
					logger.info("========premiumYes   is " + premiumYes);
					logger.info("========bankerOption   is " + bankerOption);
					logger.info("========currencyOption   is " + currencyOption);
					logger.info("========excessOption   is " + excessOption);
					logger.info("========bankerAssuredOption   is "	+ bankerAssOption);
					logger.info("========displayMode   is " + displayMode);
					logger.info("========viewStatus   is " + viewStatus);
					logger.info("========displayText   is " + displayText);
					
					String placeCode[][] = new String[0][0];
					brokerBra = (String)session.getAttribute("LoginBranchCode");
					belongingBranch = (String)session.getAttribute("BelongingBranch");
					HashMap brokerDetails;
					brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
					creatorBean.setBrokerBra(brokerBra);
					if(!brokerDetails.isEmpty())
					{
						cid = (String)brokerDetails.get("Orgination");
						creatorBean.setDcid((String)brokerDetails.get("Destination"));
						decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
						creatorBean.setTaxPercent(Double.parseDouble((String)brokerDetails.get("Tax")));
						creatorBean.setWebsite((String)brokerDetails.get("Site"));
					}
					creatorBean.setCid(cid);
					creatorBean.setDecimalDigit(decimalDigit);
					creatorBean.setUsrModeController(usrModeController);
					creatorBean.setCurrencyOption(currencyOption);
					creatorBean.setExcessOption(excessOption);
					creatorBean.setBankerAssuredOption(bankerAssOption);
					creatorBean.setBankerOption(bankerOption);
					creatorBean.setPremiumYes(premiumYes);
					
						String check;
						creatorBean.setFontPath(request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf"));
						rsa.opencoverpdf.finalprint finalpr = new rsa.opencoverpdf.finalprint();
						if(finalpr.isNumeric(PolicyNo)){
							check = "home.quote_no=?"; 
						}else{
							check = "home.policy_no=?";
						}
						if(check.length()>0){
							stampStatus = finalBean.getStampStatus(check,PolicyNo);
						}
					String rubberOption;
					rubberOption = request.getParameter("rubberOption")==null?"":request.getParameter("rubberOption");
					if(rubberOption.length()>0&&!rubberOption.equalsIgnoreCase("undefined")&&stampStatus.length()<=0)
					{
						stampStatus = rubberOption;
					}
					creatorBean.setStampStatus(stampStatus);
					creatorBean.setPolicyNo(PolicyNo);
					logger.info(" this is royal test new pdf from controller...displayMode..."+displayMode);
					creatorBean.setDisplayMode(displayMode);
					PolicyNoFour = PolicyNo;
					response.setContentType("application/pdf");
					boolean checkFlag = false;
					if(loginId == null||"null".equalsIgnoreCase(loginId)||"".equalsIgnoreCase(loginId)||PolicyNo == null 
							|| "null".equalsIgnoreCase(PolicyNo)||"0".equalsIgnoreCase(PolicyNo)){ 
						response.sendRedirect("login/error_messg_pdf.jsp");
					}else
					{
						if ("draftMode".equalsIgnoreCase(displayMode)){
						//	checkFlag = finalBean.pdfCheckingStatus(PolicyNo, loginId, "QuoteNo",brokerBra,cid);
							placeCode = finalBean.getPlaceCodes(PolicyNo,"Schedule","3","QuoteNo");
						}else{
						//	checkFlag = finalBean.pdfCheckingStatus(PolicyNo, loginId, "POLICYNO",brokerBra,cid);
							placeCode = finalBean.getPlaceCodes(PolicyNo,"Schedule","3","POLICYNO");
						}
					}
					if(checkFlag)
					{
						response.sendRedirect("Copy of information Admin.jsp?pdfStatus=NODATAS");
						if(true)return;
					}
						/*
						 * Removed For Jasper Reports By Aswin
						 * 
						 * 
						 * creatorBean.setPlace(finalBean.isNull(placeCode[0][0], ""));
						headImage  = finalBean.isNull(placeCode[0][1], "");
						footImage  = finalBean.isNull(placeCode[0][2], "");
						signImage  = finalBean.isNull(placeCode[0][3], "");
						stampImage = finalBean.isNull(placeCode[0][4], "");
						creatorBean.setCurrencyType1(finalBean.isNull(placeCode[0][5], ""));
						creatorBean.setCurrencyType(finalBean.isNull(placeCode[0][6], ""));
						creatorBean.setBraAddress(finalBean.isNull(placeCode[0][8], ""));
						creatorBean.setCols(finalBean.isNull(placeCode[0][9], ""));
						creatorBean.setBraPO(finalBean.isNull(placeCode[0][10], ""));
						creatorBean.setBraCity(finalBean.isNull(placeCode[0][11], ""));
						creatorBean.setBraCountry(finalBean.isNull(placeCode[0][12], ""));
						creatorBean.setBraPhone(finalBean.isNull(placeCode[0][13], ""));
						creatorBean.setBraFax(finalBean.isNull(placeCode[0][14], ""));
						creatorBean.setHeadImage(request.getSession().getServletContext().getRealPath("/" + IMG+headImage));
						creatorBean.setFootImage(request.getSession().getServletContext().getRealPath("/" + IMG+footImage));
						creatorBean.setSignImage(request.getSession().getServletContext().getRealPath("/" + IMG+signImage));
						creatorBean.setStampImage(request.getSession().getServletContext().getRealPath("/" + IMG+stampImage));
						if("1".equals(cid)){
							creatorBean.setSignStampImage(request.getSession().getServletContext().getRealPath("/" + IMG+signImage.replaceAll(".jpg", "")+stampImage));
						}
	
				String urlPath;
				urlPath = request.getSession().getServletContext().getRealPath("/" + IMG+headImage);
				creatorBean.setImagePath(urlPath);
				String urlPathFooter;
				urlPathFooter = request.getSession().getServletContext().getRealPath("/" + IMG+footImage);
				creatorBean.setFooterImagePath(urlPathFooter);
				creatorBean.setDisplayText(displayText);*/
				//   
				String extension;
				extension = ".pdf";
				String polino;
				String filePath;
				if ("draftMode".equalsIgnoreCase(displayMode)){
					String fileId;
					fileId = Integer.toString(((finalBean.getMaximumAmendId(quoteDraftNo)) - 1));
					polino = quoteDraftNo + "_" + fileId + "Draft" + extension;
				}else{
					polino = PolicyNoFour + "Schedule" + extension;
				}
	//			Block Added by Chinna
				System.out.println("polino: >>>>>>> bef"+polino);
				if(polino!=null && polino.length()>0 && polino.indexOf("/")!=-1)
				{
					polino=polino.replaceAll("/", "-");
					System.out.println("polino: >>>>>>>"+polino);
				}
				if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/OriginalCopyPdf/" + polino);
				} 
				else if ("COPY".equalsIgnoreCase(displayText)) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/duplicatecopypdf/" + polino);
				} 
				else if ("DRAFT".equalsIgnoreCase(displayText)) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/quotepdf/" +  polino);
				} 
				else if ("INVALID POLICY".equalsIgnoreCase(displayText)) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/testpolicypdf/" + polino);
				} 
				else if ("INVALID DRAFT".equalsIgnoreCase(displayText)) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/testquotepdf/" + polino);
				} 
				else if ((displayText.trim()).length()<=0) 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/" + polino);
				} 
				else 
				{
					filePath = request.getSession().getServletContext().getRealPath("/" + "/debitpdf/" +  polino);
				}
	
				logger.info("royal test pdf...the X FILE NAME IS " + filePath);
				File pdfFile;
				pdfFile = new File(filePath);
				String filePath1;
				/*if (pdfFile.exists() && ("Normal".equalsIgnoreCase(displayMode))){
					response.sendRedirect(request.getContextPath()+filePath);//need to check more while testing
				}
				else 
				{*/
					
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
						filePath1 = "/OriginalCopyPdf/"+polino;
					}
					else if ((displayText.trim()).length()<=0)
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/OriginalPdf/"+polino);
						filePath1 = "/OriginalPdf/"+polino;
					}
					else if ("COPY".equalsIgnoreCase(displayText))
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
						filePath1 = "/duplicatecopypdf/"+polino;
					}
					else if ("DRAFT".equalsIgnoreCase(displayText))
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
						filePath1 = "/quotepdf/"+polino;
					}
					else if ("INVALID POLICY".equalsIgnoreCase(displayText))
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+ polino);
						filePath1 = "/testpolicypdf/"+polino;
					}
					else if ("INVALID DRAFT".equalsIgnoreCase(displayText))
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
						filePath1 = "/testquotepdf/"+polino;
					}
					else
					{
						filePath = request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
						filePath1 = "/debitpdf/"+polino;
					}
						
					creatorBean.setFilePath(filePath);
					String freight;
					freight = finalBean.isNull((String)session.getAttribute("freight"),"");//need to check
					//Added by Chinna
					try {
						Image paidStampImage=Image.getInstance(request.getSession().getServletContext().getRealPath("/" + IMG+ "Paid_Stamp.gif"));
						creatorBean.setPaidStampImage(paidStampImage);
					} catch (BadElementException e) {
						
						e.printStackTrace();
					}//End
					/*
					 * Removed for Jasper Reports By Aswin
					 * 
					creatorBean.writePDF(brokerBra,cid,decimalDigit,freight,loginId,(String)session.getAttribute("product_id"),PolicyNo);
					*/
					String applicationNo = finalBean.getApplicationNo(PolicyNo);
					if(StringUtils.isBlank(applicationNo)){
						applicationNo = finalBean.getApplicationNo1(PolicyNo);
					}
					com.maan.report.JasperReports js = new com.maan.report.JasperReports();
					js.certificateSchedule(applicationNo, brokerBra, belongingBranch, filePath);
					
					//response.sendRedirect(request.getContextPath()+filePath1);
					session.setAttribute("pdfFilePath", filePath1);
					response.sendRedirect(request.getContextPath()+"/pdfReport.action");
				/*}*/
			}catch (BaseException e) {
				throw new BaseException(e, "Error");
			}
			logger.debug(EXIT);
			
		}
	}
	protected void doGet(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException 
	{
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException 
	{
		try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
	}
	public String getServletInfo() 
	{
		return "Short description";
	}
}
