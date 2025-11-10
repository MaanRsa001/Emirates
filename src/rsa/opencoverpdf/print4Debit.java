


package rsa.opencoverpdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import rsa.pdf.GHQPDFCreatorBean;
import rsa.pdf.PDFCreatorBean;
import rsa.pdf.PdfPTableCreation;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Image;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class print4Debit extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(print4Debit.class);
    
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -2390807228159449789L;
	final private static transient String POLGENDATE = "PolicyGeneratedDate";
	final static transient private String HUND = "100";
	final static transient private String THOU = "1000";
	final static transient private String ONLY = " only";
	final static transient private String IMG = "/images/";
	final static transient private String DEBITS = "Debit";
	final static transient private String NORMAL = "NORMAL";
	final static transient private String NORMALSUP = "NormalSupplement";
	final static transient private String NORMALMUL = "NormalMultiple";
	
	 /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    	try{
    		if(new policyInfo().getGHQOACode(request.getParameter("loginid")==null?"":request.getParameter("loginid")))
		 		processRequestGHQ(request,response);
    		else
    			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    	try{
			processRequest(request,response);
		   }catch(Exception e){
			   logger.debug(e);
		   }
    }
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException, BaseException 
	{
    	HttpSession session;
    	//All the declaration and initialization satrt from here
    	String placeCode[][]=new String[0][0];
	    Map premiumdetails;
		String  Brokeraddress1="",BrokerAddress2="",BrokerPoBox="",BrokerCountry="",BrokerEmirate="",
		PolicyNo="";
		String BrokerCommission="0";
		String verNo = "";
		String BrokerCompany="";
		String ExcessPremium="";

		String displayModeDummy="";
		String DebitNoteNo="";
		String CustomerFirstName="",CustomerLastName="";
		String startDate="";
		String polGenDetes="";
		String PolicyNoFour="";
		String CustomerCompanyName="";
		String displayText="";
		String finalAmtRs="";
		int deductedAmtRs=0;
		String ConvertToWords="";
		String fontPath ="",urlPath="",urlPathFooter="";
		int checkProperCount=0;
		String openCoverNo="";
		String LoginID="";
		String displayMode="";
		String braAddress="";
		String freightBroker="",freightUser="";
		String finalDedAmt="";
		String finalPayAmt="";
		String deductAmtRound="";
		double multiTotalPremium=0.0;
		String openCoverCustomer="";
		String finalAmtTemp="";
		String currencyOption="";
		String viewStatus="";
		String usrModeController="";
		double taxPercent = 0;
       //All the declaration and initialization end from here
		premiumdetails=new HashMap();
		String brokerBra = "";
		String headImage;
		String footImage;
		String signImage = "";
		String currencyType = "";
		String currencyType1 = "";
		String fillsDigit = "";
		String fills = "";
		String cid="";
		int decimalDigit = 0;
		String QuoteNo="";
		finalprint finalBean;
		finalBean = new finalprint();	
		rsa.pdf.finalprint dataBean;
		dataBean = new rsa.pdf.finalprint();
		rsa.pdf.PDFDisplay disBean;
		disBean = new rsa.pdf.PDFDisplay();
		NumberToWordBean convertWord;
		convertWord = new NumberToWordBean();
		Map preDetails;preDetails = new HashMap();
		PDFCreatorBean creatorBean;
		creatorBean = new PDFCreatorBean();
		session=request.getSession(false);
		try
		{
     		if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
	        displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
			if("Test".equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText))
			{
				displayText="INVALID DEBIT";
			}
			
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
			Date date;
			date = new Date();
			startDate=dateFormat.format(date);

			PolicyNo=request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			LoginID=request.getParameter("loginid")==null?"0":request.getParameter("loginid");
			currencyOption=request.getParameter("currencyOption")==null?"":request.getParameter("currencyOption");
		    viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");
   		    displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
				
			logger.info("========LoginID   is "+LoginID);
			logger.info("========policynumber   is "+PolicyNo);
			logger.info("========displayMode   is "+displayMode);
			logger.info("========currencyOption   is "+currencyOption);
			logger.info("========viewStatus   is "+viewStatus);
			
			//rajesh world work stated
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			String cols="";
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
			}
			//End
			
            /*StringTokenizer token;
            token= new StringTokenizer(PolicyNo,"-");
		    while(token.hasMoreTokens())
			{
					 PolicyNoFour=token.nextToken();
			}*/
			PolicyNoFour = PolicyNo.replaceAll("/", "-");
		//DB operation and assigning modification block start here
		try
		{
			
		        if(NORMALMUL.equalsIgnoreCase(displayMode))
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLE",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","QUOTENOMULTIPLE");
				}
				else if(NORMALSUP.equalsIgnoreCase(displayMode))
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOSINGLE",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","QUOTENOSINGLE");
				}
				else
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"POLICYNO",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","POLICYNO");
				}
			    //For Getting Images
			    
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
			    fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
				urlPath = request.getSession().getServletContext().getRealPath("/" + IMG+headImage);
				urlPathFooter = request.getSession().getServletContext().getRealPath("/" + IMG+footImage);
				Map AmountDetails;
				AmountDetails =new HashMap();
				String checkPolicyDatas="NODATAS";
				checkPolicyDatas=(String)premiumdetails.get("CheckPolicy")==null?checkPolicyDatas:(String)premiumdetails.get("CheckPolicy");

				/*			if(checkPolicyDatas.equalsIgnoreCase("DATAS"))
		{
	        logger.info("premium details size"+premiumdetails.size());
			if(NORMALMUL.equalsIgnoreCase(displayMode))
			{
				int rowCount=0;
				Map AmountDetailsTemp;
				AmountDetailsTemp =new HashMap();
				String finalCountsString="";	
				polGenDetes=(String)premiumdetails.get(POLGENDATE)==null?"":(String)premiumdetails.get(POLGENDATE);//[0][4]);
				CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
				CustomerLastName=isNull((String)premiumdetails.get("CustomerLastName"),"");
				CustomerCompanyName=isNull((String)premiumdetails.get("CustomerCompanyName"),"");
				Brokeraddress1=isNull((String)premiumdetails.get("Brokeraddress1"),"");
				BrokerAddress2=isNull((String)premiumdetails.get("BrokerAddress2"),"");
				BrokerCountry=isNull((String)premiumdetails.get("BrokerCountry"),"");
				BrokerPoBox=isNull((String)premiumdetails.get("BrokerPoBox"),"0");
				BrokerEmirate=isNull((String)premiumdetails.get("BrokerEmirate"),"");
				BrokerCommission=isNull((String)premiumdetails.get("BrokerCommission"),"0");
				LoginID=(String)premiumdetails.get("LoginID");//[0][60]);
				BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
				finalCountsString=isNull((String)premiumdetails.get("finalCounts"),"0");
		
			String tempQuoteNo="";
			HashMap tempHash=new HashMap();
			String ExcessPremiumTemp="";
	for(int i=0;i<Integer.parseInt(finalCountsString);i++)
		{

			tempQuoteNo=(String)premiumdetails.get("QuoteNo"+i)==null?"0":(String)premiumdetails.get("QuoteNo"+i);//[0][84]);
			tempHash=(HashMap)premiumdetails.get(tempQuoteNo)==null?tempHash:(HashMap)premiumdetails.get(tempQuoteNo);//[0][84]);
			if(tempHash.size() > 2)
			{
				
				AmountDetailsTemp=(HashMap)tempHash.get("AmountDetails")==null?AmountDetailsTemp:(HashMap)tempHash.get("AmountDetails");
				ExcessPremiumTemp=(String)tempHash.get("ExcessPremium")==null?"0":(String)tempHash.get("ExcessPremium");
				finalAmtTemp = isNull((String)AmountDetailsTemp.get("FinalPayableAmount"),"0");
				finalAmtTemp=Double.toString((Double.parseDouble(finalAmtTemp)+(Double.parseDouble(ExcessPremiumTemp))));
				try
				{
					multiTotalPremium=Double.parseDouble(finalAmtTemp)+multiTotalPremium;
				}
				catch(Exception e)
				{
					logger.info("the Exception Occured is multiTotalPremium "+e.toString());logger.debug(e);
				}
				openCoverNo = isNull((String)tempHash.get("openCoverNo"),"");
				openCoverCustomer = isNull((String)tempHash.get("openCoverCustomer"),"");
			}
			else
			{
				checkProperCount=checkProperCount+1;
				logger.info("infoMissing"+i+"SOME INFORMATIONS MISSING FOR LINKED QUOTE NO\t"+tempQuoteNo);
			}
			rowCount=rowCount+1;
		}//End of For Loop
	}
	else
	{
				if(NORMALSUP.equalsIgnoreCase(displayMode))
				{
					PolicyNo = (String)premiumdetails.get("PolicyNo")==null?"0":(String)premiumdetails.get("PolicyNo");//[0][0]);
					displayMode="Normal";
					displayModeDummy=NORMALSUP;
				}
				AmountDetails=(HashMap)premiumdetails.get("AmountDetails");
				polGenDetes=(String)premiumdetails.get(POLGENDATE)==null?"":(String)premiumdetails.get(POLGENDATE);//[0][4]);
				QuoteNo=(String)premiumdetails.get("QuoteNo");//[0][26]);
				PolicyNo=(String)premiumdetails.get("PolicyNo");//[0][27]);
				openCoverNo = isNull((String)premiumdetails.get("openCoverNo"),"");
				openCoverCustomer =  isNull((String)premiumdetails.get("openCoverCustomer"),"");
				CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
				CustomerLastName = isNull((String)premiumdetails.get("CustomerLastName"),"");				
				CustomerCompanyName = isNull((String)premiumdetails.get("CustomerCompanyName"),"");
				Brokeraddress1 = isNull((String)premiumdetails.get("Brokeraddress1"),"");
				BrokerAddress2 = isNull((String)premiumdetails.get("BrokerAddress2"),"");
				BrokerCountry = isNull((String)premiumdetails.get("BrokerCountry"),"");
				BrokerPoBox = isNull((String)premiumdetails.get("BrokerPoBox"),"0");
				BrokerEmirate = isNull((String)premiumdetails.get("BrokerEmirate"),"");
				BrokerCommission = isNull((String)premiumdetails.get("BrokerCommission"),"0");
				
				BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
				LoginID=(String)premiumdetails.get("LoginID");//[0][60]);
				ExcessPremium=isNull((String)premiumdetails.get("ExcessPremium"),"0");
						
				finalDedAmt=(String)AmountDetails.get("FinalPayableAmountAfterDeduction")==null?"0":(String)AmountDetails.get("FinalPayableAmountAfterDeduction");
				deductAmtRound=disBean.decimalFormat(Double.parseDouble(finalDedAmt),decimalDigit);
				StringTokenizer token1;
				token1 = new StringTokenizer(deductAmtRound,".");
				finalAmtRs = (String)token1.nextToken();
				finalAmtRs = finalAmtRs.replaceAll(",","");
				deductedAmtRs=Integer.parseInt(finalAmtRs);
				ConvertToWords=convertWord.convertNumToWord(deductedAmtRs);
				finalPayAmt = isNull((String)AmountDetails.get("FinalPayableAmount"),"0");
				finalPayAmt=Double.toString((Double.parseDouble(finalPayAmt)+(Double.parseDouble(ExcessPremium))));
			}
		}*/
	}
	catch(Exception e)
	{
			logger.info("exception in print4"+e.toString());logger.debug(e);
	}
		if(displayModeDummy.equalsIgnoreCase(NORMALSUP)){
			DebitNoteNo = finalBean.getDebitNo(PolicyNo);
			preDetails = finalBean.getPreComDetails(QuoteNo,"11","QUOTENOSINGLE");
		}else {
			if(NORMALMUL.equalsIgnoreCase(displayMode)){
				Map multiInfo;
				multiInfo = finalBean.updateMultiCommission(BrokerCommission,openCoverNo,PolicyNo,"11",brokerBra,taxPercent);
				if(!multiInfo.isEmpty())
				{
					DebitNoteNo = (String)multiInfo.get("DebitNoteNo");
				}
				preDetails = finalBean.getPreComDetails(PolicyNo,"11","QUOTENOMULTIPLE");
			}else
			{
				DebitNoteNo = finalBean.updateOpenCommission(openCoverNo,PolicyNo,"11",brokerBra,BrokerCommission,taxPercent);
				preDetails = finalBean.getPreComDetails(PolicyNo,"11","POLICYNO");
			}
		}
		//DB operation and assigning modification block end here
    }
	catch (Exception ex) 
	{
		logger.debug(ex);
	}
	response.setContentType("application/pdf");
    try
	{
			String polino="";
			String fileNamePath="",fileNamePath1;
			
			if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && "Normal".equalsIgnoreCase(displayMode))
			{
				polino = PolicyNoFour+"QuoteDebitOpen.pdf";
			}
			else
			{
				polino = PolicyNoFour+"DebitOpen.pdf";
			}
			if("ORIGINAL COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
				fileNamePath1="/OriginalCopyPdf/"+polino;
			}
			else if("COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
				fileNamePath1="/duplicatecopypdf/"+polino;
			}
			else if("INVALID DEBIT".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
				fileNamePath1="/testpolicypdf/"+polino;
			}
			else
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
				fileNamePath1="/debitpdf/"+polino;
			}
			logger.info("the X FILE NAME IS "+fileNamePath);
			/*File filePath;
			filePath = new File(fileNamePath);
			if(filePath.exists() && (NORMAL.equalsIgnoreCase(displayMode) || (NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)) || NORMALMUL.equalsIgnoreCase(displayMode)))
			{
				response.sendRedirect(request.getContextPath()+fileNamePath1);
			}
			else*/
			{
				       /*try
					   {
						 String url=request.getSession().getServletContext().getRealPath("/"+ "/images/");
						 creatorBean.setDisplayMode(displayMode);
						 creatorBean.setUsrModeController(usrModeController);
						 creatorBean.setCurrencyOption(currencyOption);
						 if(NORMAL.equalsIgnoreCase(displayMode))
						 {
						 	double netPremium=0.0;
							netPremium=finalBean.getnetPremium(PolicyNo);
							if(netPremium>=0){
							creatorBean.writeDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,LoginID,PolicyNo,fileNamePath,url);
							}else{
							creatorBean.writeNewDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,LoginID,PolicyNo,fileNamePath,url);	 
							}
						 }else
						 {
							 creatorBean.writeDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,LoginID,PolicyNo,fileNamePath,url, premiumdetails, DebitNoteNo, placeCode, verNo);
						 }
						 response.sendRedirect(request.getContextPath()+fileNamePath1);
					}
					catch(Exception eb)
					{
						logger.info(eb);
					}*/
				
				com.maan.report.JasperReports jr= new com.maan.report.JasperReports() ;
				jr.debitNote(PolicyNo, brokerBra, fileNamePath);
				
				//response.sendRedirect(request.getContextPath()+fileNamePath1);
				session.setAttribute("pdfFilePath", fileNamePath1);
				response.sendRedirect(request.getContextPath()+"/pdfReport.action");
			}
            
        }catch (Exception ex) {
        	logger.debug(ex);
        } 
    }
    public static void print(final String message) {
        logger.info(message);
    }
    private String isNull(final String content,final String value)throws BaseException{
		StringBuffer contents;
		contents = new StringBuffer();
		if(content==null||content.length()<=0){
			contents.append(value);
		}
		else if(content.equalsIgnoreCase("select")){
			contents.append(value);
		}
		else{
			contents.append(content);
		}
		return contents.toString();
	}
    protected void processRequestGHQ(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException, BaseException 
	{
    	HttpSession session;
    	String placeCode[][]=new String[0][0];
	    Map premiumdetails;
		String PolicyNo="";
		String BrokerCommission="0";
		String verNo = "";
		String displayModeDummy="";
		String DebitNoteNo="";
		String PolicyNoFour="";
		String displayText="";
		String fontPath ="",urlPath="",urlPathFooter="";
		String openCoverNo="";
		String LoginID="";
		String displayMode="";
		String freightBroker="",freightUser="";
		String currencyOption="";
		String viewStatus="";
		String usrModeController="";
		String headImage;
		String footImage;
		String signImage = "";
		String currencyType = "";
		String currencyType1 = "";
		String fillsDigit = "";
		String fills = "";
		String cid="";
		double taxPercent = 0;
       //All the declaration and initialization end from here
		premiumdetails=new HashMap();
		String brokerBra = "";
		int decimalDigit = 0;
		String QuoteNo="";
		finalprint finalBean;
		finalBean = new finalprint();	
		rsa.pdf.finalprint dataBean;
		dataBean = new rsa.pdf.finalprint();
		rsa.pdf.PDFDisplay disBean;
		disBean = new rsa.pdf.PDFDisplay();
		GHQPDFCreatorBean creatorBean = new GHQPDFCreatorBean();

		try
		{
     		session=request.getSession(false);
			if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("login/error_messg_pdf.jsp");
				return;
			}
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
	        displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
			if("Test".equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText))
			{
				displayText="INVALID DEBIT";
			}
			
			DateFormat dateFormat;
			dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
			Date date;
			date = new Date();

			PolicyNo=request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			LoginID=request.getParameter("loginid")==null?"0":request.getParameter("loginid");
			currencyOption=request.getParameter("currencyOption")==null?"":request.getParameter("currencyOption");
		    viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");
   		    displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
				
			logger.info("========LoginID   is "+LoginID);
			logger.info("========policynumber   is "+PolicyNo);
			logger.info("========displayMode   is "+displayMode);
			logger.info("========currencyOption   is "+currencyOption);
			logger.info("========viewStatus   is "+viewStatus);
			
			//rajesh world work stated
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			String cols="";
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
			}
			//End
			
			PolicyNoFour=PolicyNo;
		//DB operation and assigning modification block start here
		try
		{
			
			 if(NORMALMUL.equalsIgnoreCase(displayMode))
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLE",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","QUOTENOMULTIPLE");
				}
				else if(NORMALSUP.equalsIgnoreCase(displayMode))
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOSINGLE",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","QUOTENOSINGLE");
				}
				else
				{
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"POLICYNO",brokerBra,cid,DEBITS);
					placeCode = dataBean.getPlaceCodes(PolicyNo,DEBITS,"11","POLICYNO");
				}
			    //For Getting Images
//For Getting Images
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
			    fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
				urlPath = request.getSession().getServletContext().getRealPath("/" + IMG+headImage);
				urlPathFooter = request.getSession().getServletContext().getRealPath("/" + IMG+footImage);
	}
	catch(Exception e)
	{
			logger.info("exception in print4"+e.toString());logger.debug(e);
	}
		if(displayModeDummy.equalsIgnoreCase(NORMALSUP)){
			DebitNoteNo = finalBean.getDebitNo(PolicyNo);
		}else {
			if(NORMALMUL.equalsIgnoreCase(displayMode)){
				Map multiInfo;
				multiInfo = finalBean.updateMultiCommission(BrokerCommission,openCoverNo,PolicyNo,"11",brokerBra,taxPercent);
				if(!multiInfo.isEmpty())
				{
					DebitNoteNo = (String)multiInfo.get("DebitNoteNo");
				}
			}else
			{
				DebitNoteNo = finalBean.updateOpenCommission(openCoverNo,PolicyNo,"11",brokerBra,BrokerCommission,taxPercent);
			}
		}
		//DB operation and assigning modification block end here
    }
	catch (Exception ex) 
	{
		logger.debug(ex);
	}
	response.setContentType("application/pdf");
    try
	{
			String polino="";
			String fileNamePath="",fileNamePath1;
			
			if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && "Normal".equalsIgnoreCase(displayMode))
			{
				polino = PolicyNoFour+"QuoteDebitOpen.pdf";
			}
			else
			{
				polino = PolicyNoFour+"DebitOpen.pdf";
			}
			if("ORIGINAL COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
				fileNamePath1="/OriginalCopyPdf/"+polino;
			}
			else if("COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
				fileNamePath1="/duplicatecopypdf/"+polino;
			}
			else if("INVALID DEBIT".equalsIgnoreCase(displayText))
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
				fileNamePath1="/testpolicypdf/"+polino;
			}
			else
			{
				fileNamePath =request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
				fileNamePath1="/debitpdf/"+polino;
			}
			logger.info("the X FILE NAME IS "+fileNamePath);
			/*File filePath;
			filePath = new File(fileNamePath);
			if(filePath.exists() && (NORMAL.equalsIgnoreCase(displayMode) || (NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)) || NORMALMUL.equalsIgnoreCase(displayMode)))
			{
				response.sendRedirect(request.getContextPath()+fileNamePath1);
			}
			else*/
			{
				       try
					   {
						 String url=request.getSession().getServletContext().getRealPath("/"+ "/images/");
						 creatorBean.setDisplayMode(displayMode);
						 creatorBean.setUsrModeController(usrModeController);
						 creatorBean.setCurrencyOption(currencyOption);
						 if(NORMAL.equalsIgnoreCase(displayMode))
						 {
							 creatorBean.writeDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,LoginID,PolicyNo,fileNamePath,url);
						 }/*else
						 {
							 creatorBean.writeDebitPDF(urlPath,urlPathFooter,fontPath,cid,decimalDigit,taxPercent,brokerBra,freightBroker,freightUser,LoginID,PolicyNo,fileNamePath,url, premiumdetails, DebitNoteNo, placeCode, verNo);
						 }*/
						 response.sendRedirect(request.getContextPath()+fileNamePath1);
					}
					catch(Exception eb)
					{
						logger.info(eb);
					}
			}
            
        }catch (Exception ex) {
        	logger.debug(ex);
        } 
    }
}