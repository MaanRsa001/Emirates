
package rsa.opencoverpdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import rsa.pdf.PdfPTableCreation;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class print4Schedule extends HttpServlet 
{
	private static final Logger logger = LogUtil.getLogger(print4Schedule.class);
    /**
	 * 
	 */
	private static final long serialVersionUID = -2909689830684630090L;
	final static transient private String SAUDI = "153";
	final static transient private String BHARAIN = "14";
	final static transient private String OMANI = "173";
	final static transient private String GULF = "1";
	final static transient private String TEST = "Test";
	final static transient private String LINEBREAK = "\n";
	final static transient private String INVPOLICY = "INVALID POLICY";
	final static transient private String CERTMODE = "certificateMode";
	final static transient private String NORMAL = "NORMAL";
	final static transient private String NORMALSUP = "NormalSupplement";
	final static transient private String DRFATMODE = "draftMode";
	final static transient private String QUOTENO = "QuoteNo";
	final static transient private String SCHEDULE = "schedule";
	final static transient private String DRFATMODEMUL = "draftModeMultiple";
	final static transient private String NORMALMUL = "NormalMultiple";
	final static transient private String POLICYNO = "POLICYNO";
	final static transient private String NULL = "null";
	final static transient private String SPACE = ":\b";
	final static transient private String ASPER = "As per annexure";
	final static transient private String YES = "YES";
	final static transient private String NODATE = "NoDate";
	final static transient private String IMG = "/images/";
	final static transient private String NOTHING = "NOTHING";
	final static transient private String INFO = "infoMissing";
	final static transient private String NONE = "None";
	final static transient private String EDIT = "EDIT";
	final static transient private String NEWLINE = "\n\n";
	final static transient private String DRAFTMODE = "draftMode";
	final static transient private String HUND = "100";
	final static transient private String THOU = "1000";
	final static transient private String TAB = "\t\t";
	static private String displayMode = "";
	
	String paidStampPath = "";//Added
	 protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
	    throws ServletException, IOException {
		 try{
			 	displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
			 	if(new policyInfo().getGHQOACode(request.getParameter("loginid")==null?"":request.getParameter("loginid")))
			 		processRequestGHQ(request,response);
			 	else if(DRFATMODEMUL.equalsIgnoreCase(displayMode)|| NORMALMUL.equalsIgnoreCase(displayMode))
			 		processRequest(request,response);
			 	else
			 		processRequestSingle(request,response);
			   }catch(Exception e){
				   logger.debug(e);
			   }
	    }
	    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
	    throws ServletException, IOException {
	    	try{
	    		displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
	    		if(new policyInfo().getGHQOACode(request.getParameter("loginid")==null?"":request.getParameter("loginid")))
	    			processRequestGHQ(request,response);
			 	else if(DRFATMODEMUL.equalsIgnoreCase(displayMode)|| NORMALMUL.equalsIgnoreCase(displayMode))
			 		processRequest(request,response);
			 	else
			 		processRequestSingle(request,response);
			   }catch(Exception e){
				   logger.debug(e);
			   }
	    }
    protected void processRequest(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException,BaseException 
	{
    	Document document;
    	Map premiumdetails;
        Map commoditydetails;
    	HttpSession session=null;
    	rsa.opencoverpdf.PageNumbersWatermarkNew pageWater=null;
        PdfWriter writer=null,writer1=null;
    	PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,quotedTable;
    	tableCreation = new PdfPTableCreation();
    	tableCreation1 = new PdfPTableCreation();
    	tableCreation2 = new PdfPTableCreation();
    	tableCreation3 = new PdfPTableCreation();
    	quotedTable = new PdfPTableCreation();
    	//All the Declaration and initialiation start from here
		String  certificateNo="",BrokerName="",PolicyNo="",CoverName="",extraCoverId="",CarrierName="",TransitFrom="",FinalDestination="",
		BlAwbNo="",BlAwbDate="",CoverIdTemp="",CoverIdSeaTemp="",ExtraCoverIdTemp="",issuerName="",policyNoDummy="";
		
		String polGenDate = "";
		String polGenTime = "";
		String stampPath = "";
		String signPath = "";
		String verNo = "";
		String BrokerCommission="";
		String PdfKnownText="";
		String Premium="";
		String ExcessPremium="";
		String BrokerRemarks="";
		String BrokerCompany="";
		String setAgentMul="";
		String brokerRemarkMul="";
		String adminRemarkMul="";
		String editexMultiple="";
		String exMultiple="";
		String warMultiple="";
		String editwarMultiple="";
		String clausesMultiple="";
		String exClausesMul="";
		String editExClausesMul="";
		String editclausesMul="";
		String freeTextMulSea="";
		String freeTextMultiple="";
		String place = "";
		String infoMissingStatus="";
		int checkProperCount=0;
		
		String check="";

		String[] ExclusionId_arr=new String[0];
		String[] Supplier_arr=new String[0];
		String[] Invoice_arr=new String[0];
		String[] commoditySumIns = new String[0];
		String[] WarrantyId_arr=new String[0];
		String BankName="";
		String CustomerFirstName="",CustomerLastName="",custAddress1="",
		CustomerCountry="",CustomerEmirate="";
		double exchange=0;
		boolean checkFlag=false;
		String totalSumRounds="";
		String insTotalRound="";
		String PremiumYes="";
		String PolicyNoFour="";
		String AdminStatus="";
		String AdminRemarks="";
		String BackDaysOption="";
		double multiTotalPremium=0.0;
		double mulTotalSumInsurL=0.0;
		String tolDetails[][] = new String[0][0];
		String displayModeDummy="";
		String certificateDate = "";
		String policyGenDate="";
		String quoteGenDate="";
		String approvedBy="";
		String CustomerPoBox="";
		String cusCompanyName="";
		HashMap hashAnnexure=new HashMap();
		String finalDestMul="";
		String transFromMul="",SailingDate="",
		SailingDateTemp="",VesselNameTemp="",PolicyNoTemp;
		String openCoverNo="";
		String openCoverMissiNo="";
		String openCoverCustomer="";
		StringBuffer concatAll=new StringBuffer();
		String watermarkText=INVPOLICY;
		String totalSumInsured="";
		String InsuredTotal_st="";
		String LoginID="";
		String CommodityName_arr[]=new String[0];
		String comDescription[]=new String[0];
		String sumInsuredLocal[]=new String[0];
		String sumInsuredLocalR[]=new String[0];
		String comSumInsRound[]=new String[0];
		String pkgDescription[]=new String[0];
		
		String SetAgentName="",SetAgentNameO="",SettlingAgentId="",
		setAgentAddress1="",setAgentAddress2="",setAgentFaxNo="",
		SetAgentTelPh="",ExchangeRate="",CurrencyName="",currencyShortName="",
		TransportName="",QuoteNo="",SaleTermValue="";
		int finalCount=0;
		String commissionAmount="";
		String finalPayAmounts="";
		String finalCountryName="";
		String transCountryName="";
		String PolicyDate="";
		String[] SaleTermName_arr=new String[0];
		String LcNumber="";
		String LcDate="";
		String commiAmtRound="";
		String transitStartPlace="";
		String finalStartPlace="";
		String TransitFromTemp="",finalDestinationT="",BlAwbNoTemp="",BlAwbDateTemp="",CarrierNameTemp="",
		PolicyDateTemp="",LcNumberTemp="",LcDateTemp="",polGenDateTemp="",TransportNameTemp="",
		CurrencyNameTemp="",QuoteNoTemp="",SaleTermValueTemp="",finalCountryNameT="",transCountryNameT="";
		int finalCountTemp=0;
		String payableAmountT="";
		String payableAmtRoundT="";
		String ExchangeRateTemp="";
		String[] Supplier_arrTemp=new String[0];
		String[] Invoice_arrTemp=new String[0];
		String[] CommSumInsTemp = new String[0];

		String commoNameTemp[]=new String[0];
		String commoDesTemp[]=new String[0];

		String suminsLocalTemp[]=new String[0];
		String sumLocalRoundT[]=new String[0];
		String commoSumRoundT[]=new String[0];

		String SaleTermName[]=new String[0];
		String PkgDescription[]=new String[0];
		String rate_arrTemp[]=new String[0];
		String seaValue_arrTemp[]=new String[0];
		String wareHouseTemp[]=new String[0];
		String warRate_arrTemp[]=new String[0];
		String rag_arrTemp[]=new String[0];

		String[][] extraClauses=new String[0][0];
		String[][] exclusions=new String[0][0];
		String[][] warranties=new String[0][0];
		String[][] clauses=new String[0][0];
		String[][] EditExtraClauses=new String[0][0];
		String[][] EditExClauses=new String[0][0];
		String[][] EditWarClauses=new String[0][0];
		String[][] EditClauses=new String[0][0];
		String[][] openFreeText=new String[0][0];
		String[][] seaFreeTextOpt=new String[0][0];
		String[][] insCompanines=new String[0][0];
		String[][] multiQuoteInfo=new String[0][0];
		String[][] multiQuotePremiumInfo=new String[0][0];
		String rsaSharedPercent="";
		String bankerOption="";
		String bankerAssOpt="";
		String currencyOption="";
		String excessOption="";
		String viewStatus="";
		String displayText="";
		String displayMode="";
		String quoteDraftNo="";
		String braAddress = "";
		String braPO = "";
		String braCity = "";
		String braCountry = "";
		String braPhone = "";
		String braFax = "";
		String cid="";
		String openCoverStartDate="";
		double taxPercent = 0;
	//All the Declaration and initialiation End from here
		premiumdetails=new HashMap();
		commoditydetails=new HashMap();
		String headImage = "";
		String footImage = "";
		String signImage = "";
		String stampImage = "";
		String stampStatus = "";
		String currencyType = "";
		String currencyType1 = "";
		String usrModeController="";
		String cusCode ="",createDate="";
		String brokerBra = "";
		int decimalDigit = 0;
		String website="";
		String fontPath ="";
		finalprint finalBean;
		finalBean = new finalprint();
		rsa.pdf.finalprint finalData;
		finalData = new rsa.pdf.finalprint();
		rsa.pdf.PDFDisplay pdfFormat;
		pdfFormat = new rsa.pdf.PDFDisplay();
		Font font,fontHead1,fontHead2,fontHead3,fontText,fontText1,fontHeadSP,fontHeadUl;
		
		Map taxDetails;taxDetails = new HashMap();
        try
		{
			session=request.getSession(true);
			if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("login/error_messg_pdf.jsp");	
				return;
			}
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
			displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
		   displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
		   if(TEST.equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText))
			{
				displayText=INVPOLICY;
			}
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
				website = (String)brokerDetails.get("Site");
				//currencyType = (String)brokerDetails.get("CurrencyAbb");
			}
			//End
			
			PolicyNo = request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			quoteDraftNo=PolicyNo;
			LoginID=request.getParameter("loginid")==null?"0":request.getParameter("loginid");
			PremiumYes=request.getParameter("printoption")==null?"":request.getParameter("printoption");
			bankerOption=request.getParameter("bankerOption")==null?"":request.getParameter("bankerOption");
			bankerAssOpt = isNull(request.getParameter("bankerAssuredOption"),"");
			currencyOption=request.getParameter("currencyOption")==null?"":request.getParameter("currencyOption");
			excessOption=request.getParameter("excessOption")==null?"":request.getParameter("excessOption");
			viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");
				
			logger.info("======== LoginID is "+LoginID);
			logger.info("======== policynumber is "+PolicyNo);
			logger.info("======== PremiumYes is "+PremiumYes);
			logger.info("======== bankerOption is "+bankerOption);
			logger.info("======== currencyOption is "+currencyOption);
			logger.info("======== excessOption0 is "+excessOption);
			logger.info("======== bankerAssOpt is "+bankerAssOpt);
			logger.info("======== displayMode is "+displayMode);
			logger.info("======== viewStatus is "+viewStatus);
			logger.info("======== displayText is "+displayText);
			
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				certificateNo = finalBean.getCertificateNo(brokerBra,(String)session.getAttribute("product_id"),PolicyNo );
			}

			if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)){
				check = "home.QUOTE_NO='" + PolicyNo + "'";
			}
			String placeCode[][] = new String[0][0];
			String cols="";
			
		try
		{
			//if(PolicyNo.indexOf('-')==-1){
			if(finalBean.isNumeric(PolicyNo)){
				check = "home.quote_no in("+PolicyNo+")";
			}else{
				 check = "home.policy_no='"+PolicyNo+"'";
			}
			//final String args[] = {brokerBra};
			if(check.length()>0){
				stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where "+check);
			}
			if(stampStatus.length()<=0)
			{
				String args1[] = new String[1];
				args1[0] = PolicyNo;
				stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where home.quote_no in(select quote_no from position_master where policy_no=?)",args1);
			}
		}
		catch (Exception e)
		{
			logger.debug(e);
		}
			
			String rubberOption;
			rubberOption = request.getParameter("rubberOption")==null?"":request.getParameter("rubberOption");
			if(rubberOption.length()>0){
				stampStatus = rubberOption;
			}
			/*StringTokenizer token = null;
			if(PolicyNo.indexOf("-")!=-1){
				token = new StringTokenizer(PolicyNo,"-");
			}else if(PolicyNo.indexOf("/")!=-1){
				token = new StringTokenizer(PolicyNo,"/");
			}
			String previousToken = "";
			while(token!=null && token.hasMoreTokens()){
				previousToken = PolicyNoFour;
				PolicyNoFour = token.nextToken();
			}
			if(PolicyNo.indexOf("-")!=-1){
				PolicyNoFour = previousToken + "-" + PolicyNoFour;					
			}else if(PolicyNo.indexOf("/")!=-1){
				PolicyNoFour = previousToken + "/" + PolicyNoFour;					
			}*/
			PolicyNoFour = PolicyNo;
			//DB operation and assigning modification block start here
			
			try
			{
				if(DRFATMODE.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this draftMode DRAFT BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,QUOTENO,brokerBra,cid,SCHEDULE);
					tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
					issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
					cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
					createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
					currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
				}
				else if(CERTMODE.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this certificateMode certificate BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,QUOTENO,brokerBra,cid,SCHEDULE);
					tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
					issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
					cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
					createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
					currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
				}
				else if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this MULTIPLE DRAFT BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLEDRAFT",brokerBra,cid,SCHEDULE);
					issuerName = finalBean.getIssuerName(PolicyNo,"QUOTENOMULTIPLEDRAFT");
					cusCode = finalBean.getCustomerCode(PolicyNo,"QUOTENOMULTIPLEDRAFT","11");
					createDate = finalBean.getForamattedDate(PolicyNo,"QUOTENOMULTIPLEDRAFT");
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11","QUOTENOMULTIPLEDRAFT");
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11","QUOTENOMULTIPLEDRAFT");
					currencyShortName = finalBean.getFCName(PolicyNo,"11","QUOTENOMULTIPLEDRAFT",brokerBra);
				}
				else
				{
					if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
					{
						logger.info("comes inside this QUOTENOMULTIPLE BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLE",brokerBra,cid,SCHEDULE);
						issuerName = finalBean.getIssuerName(PolicyNo,"QUOTENOMULTIPLE");
						cusCode = finalBean.getCustomerCode(PolicyNo,"QUOTENOMULTIPLE","11");
						createDate = finalBean.getForamattedDate(PolicyNo,"QUOTENOMULTIPLE");
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11","QUOTENOMULTIPLE");
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11","QUOTENOMULTIPLE");
						currencyShortName = finalBean.getFCName(PolicyNo,"11","QUOTENOMULTIPLE",brokerBra);
					}
					else if(NORMALSUP.equalsIgnoreCase(displayMode))
					{
						logger.info("comes inside this QUOTENOSINGLE BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOSINGLE",brokerBra,cid,SCHEDULE);
						tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
						issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
						cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
						createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
						currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
					}
					else
					{
						logger.info("comes inside this POLICYNO BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,POLICYNO,brokerBra,cid,SCHEDULE);
						tolDetails = finalBean.getToleranceDetails(PolicyNo,POLICYNO,brokerBra);
						issuerName = finalBean.getIssuerName(PolicyNo,POLICYNO);
						cusCode = finalBean.getCustomerCode(PolicyNo,POLICYNO,"11");
						createDate = finalBean.getForamattedDate(PolicyNo,POLICYNO);
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",POLICYNO);
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11",POLICYNO);
						currencyShortName = finalBean.getFCName(PolicyNo,"11",POLICYNO,brokerBra);
					}
				}
				if(placeCode.length>0)
				{
					place      = placeCode[0][0] == null ? "" : placeCode[0][0];
					headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
					footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
					signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
					stampImage = placeCode[0][4] == null ? "" : placeCode[0][4];
					currencyType1 = placeCode[0][5] == null ? "" : placeCode[0][5];
					currencyType = placeCode[0][6] == null ? "" : placeCode[0][6];
					braAddress = placeCode[0][8] == null ? "" : placeCode[0][8];
					cols = placeCode[0][9] == null ? "" : placeCode[0][9];
					braPO = placeCode[0][10] == null ? "" : placeCode[0][10];
					braCity = placeCode[0][11] == null ? "" : placeCode[0][11];
					braCountry = placeCode[0][12] == null ? "" : placeCode[0][12];
					braPhone = placeCode[0][13] == null ? "" : placeCode[0][13];
					braFax = placeCode[0][14] == null ? "" : placeCode[0][14];
				}
				
				HashMap AmountDetails=new HashMap();
				String checkPolicyDatas="NODATAS";
				checkPolicyDatas=(String)premiumdetails.get("CheckPolicy")==null?checkPolicyDatas:(String)premiumdetails.get("CheckPolicy");
				logger.info("royal test the CHECK POLICY DECLARED ONE "+checkPolicyDatas);
				if(checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
					{
						multiQuoteInfo=(String[][])premiumdetails.get("multiQuoteInfo");
						multiQuotePremiumInfo=(String[][])premiumdetails.get("multiQuotePremiumInfo");
						rsa.opencoverpdf.StringFunction strfun;
						strfun =new rsa.opencoverpdf.StringFunction();
						int rowCount=0;
						Map commoDetailsT;commoDetailsT=new HashMap();
						Map amtDetailsTemp;amtDetailsTemp=new HashMap();
						String finalCountsString="";	
						CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
						CustomerLastName = isNull((String)premiumdetails.get("CustomerLastName"),"");
						custAddress1 = isNull((String)premiumdetails.get("CustomerAddressOne"),"");
						CustomerCountry = isNull((String)premiumdetails.get("CustomerCountry"),"");
						CustomerEmirate = isNull((String)premiumdetails.get("CustomerEmirate"),"");
						CustomerPoBox = isNull((String)premiumdetails.get("CustomerPoBox"),"0");
						cusCompanyName = isNull((String)premiumdetails.get("CustomerCompanyName"),"");
						BrokerName = isNull((String)premiumdetails.get("BrokerName"),"");
						if(issuerName.length()>0){
							BrokerName = issuerName;
						}
						BrokerCommission = isNull((String)premiumdetails.get("BrokerCommission"),"0");
						LoginID = isNull((String)premiumdetails.get("LoginID"),"");
						BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
						finalCountsString = isNull((String)premiumdetails.get("finalCounts"),"0");

					String tempQuoteNo="";
					String setAgentOthersT="";
					String setAgentIdTemp="";
					String setAgentNameT="";
					String setAgentTelphT="";
					String setAgentFaxNoT="";
					String setAgentAddTwoT="";
					String setAgentAddOneT="";
					Map tempHash;tempHash=new HashMap();
					String BrokerRemarksTemp=LINEBREAK;
					String AdminRemarksTemp=LINEBREAK;
					String ExcessPremiumTemp="";
		
					int countCover=0;
					int countSeaCover=0;
					int countExtraCover=0;
					boolean checkCoverFlag=false;
					boolean checkSeaCoverFlag=false;
					boolean ckExtraCoverFlag=false;

					java.util.HashMap extraCoverIdT;extraCoverIdT =new java.util.HashMap();
					java.util.HashMap coverIdTemp;coverIdTemp =new java.util.HashMap();
					java.util.HashMap coverIdSeaTemp;coverIdSeaTemp =new java.util.HashMap();

			for(int i=0;i<Integer.parseInt(finalCountsString);i++)
			{

				StringBuffer voyageSB;voyageSB=new StringBuffer();
				StringBuffer dateOfIssueSB;dateOfIssueSB=new StringBuffer();
				StringBuffer declInvoiceNosSB;declInvoiceNosSB=new StringBuffer();
				StringBuffer BLCdetailsSB;BLCdetailsSB=new StringBuffer();
				StringBuffer packingDetailsSB;packingDetailsSB=new StringBuffer();
				StringBuffer vesselSailingSB;vesselSailingSB=new StringBuffer();
				StringBuffer interestsSB;interestsSB=new StringBuffer();
				StringBuffer sumInsDetailsSB;sumInsDetailsSB=new StringBuffer();
				StringBuffer premiumDetailsSB;premiumDetailsSB=new StringBuffer();
				StringBuffer rateDetailsSB;rateDetailsSB=new StringBuffer();
				StringBuffer marineOthersSB;marineOthersSB=new StringBuffer();
				String[][] extraClausesTemp=new String[0][0];
				String[][] exclusionsTemp=new String[0][0];
				String[][] warrantiesTemp=new String[0][0];
				String[][] clausesTemp=new String[0][0];
				String[][] editExtraClausesT=new String[0][0];
				String[][] editExClausesT=new String[0][0];
				String[][] editWarClausesT=new String[0][0];
				String[][] EditClausesTemp=new String[0][0];
				String[][] openFreeTextTemp=new String[0][0];
				String[][] seaFreeTextOptT=new String[0][0];
				insCompanines=new String[0][0];
				tempQuoteNo=(String)premiumdetails.get(QUOTENO+i)==null?"0":(String)premiumdetails.get(QUOTENO+i);//[0][84]);
				tempHash=(HashMap)premiumdetails.get(tempQuoteNo)==null?tempHash:(HashMap)premiumdetails.get(tempQuoteNo);//[0][84]);
				if(tempHash.size() > 2)
				{
					setAgentOthersT = isNull((String)tempHash.get("SettlingAgentNameOthers"),"");
					setAgentIdTemp = isNull((String)tempHash.get("SettlingAgentId"),"");
					setAgentNameT = isNull((String)tempHash.get("SettlingAgentName"),"");
					setAgentAddOneT = isNull((String)tempHash.get("SettlingAgentAddressOne"),"");
					setAgentAddTwoT = isNull((String)tempHash.get("SettlingAgentAddressTwo"),"");
					setAgentTelphT = isNull((String)tempHash.get("SettlingAgentTelephoneNo"),"");
					setAgentFaxNoT = isNull((String)tempHash.get("SettlingAgentFaxNo"),"");

					if("0".equalsIgnoreCase(setAgentIdTemp))
					{
						if("".equalsIgnoreCase(setAgentOthersT) || " ".equalsIgnoreCase(setAgentOthersT) ||  NULL.equalsIgnoreCase(setAgentOthersT) )
						{
							setAgentMul=setAgentMul+"\t\t Lloyd's Agent at Destination\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t or \n\t\t"+braAddress+","+braPO+",\t"+braCity+", "+braCountry+ "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Tel:\t"+braPhone+ "\tFax:\t"+ braFax;
						}
						else if(setAgentMul.trim().equals(""))
						{
							setAgentMul=setAgentMul+"\t\t"+setAgentOthersT+NEWLINE;
						}
					}else
					{
						if("".equalsIgnoreCase(setAgentNameT))
						{
							setAgentMul=setAgentMul+"\n\t\tSettling Agent not given for Quote No "+QuoteNoTemp+NEWLINE;				
						}
						else if(setAgentMul.trim().equals(""))
						{
								setAgentMul=setAgentMul+"\n\t\t"+setAgentNameT+".\n\t\t"+setAgentAddOneT+"\t"+setAgentAddTwoT+"\t Tel:\t"+setAgentTelphT+"\tFax:\t"+setAgentFaxNoT+NEWLINE;
						}

					}
					BrokerRemarksTemp = isNull((String)tempHash.get("BrokerRemarks"),"");
					String CommodityDE;CommodityDE = isNull((String)tempHash.get("CommodityDE"),"");
					if(BrokerRemarksTemp.length()>0)
					{
						brokerRemarkMul=LINEBREAK+brokerRemarkMul+BrokerRemarksTemp+LINEBREAK;
					}
					interestsSB.append(LINEBREAK+CommodityDE+"\n\n\n");
					AdminRemarksTemp = isNull((String)tempHash.get("AdminRemarks"),"");
					if(!"".equalsIgnoreCase(AdminRemarksTemp))
					{
						adminRemarkMul = LINEBREAK+adminRemarkMul+AdminRemarksTemp+LINEBREAK;
					}
					amtDetailsTemp = (HashMap)tempHash.get("AmountDetails");
					commoDetailsT = (HashMap)tempHash.get("commoditydetails");
			try
			{
				CoverIdTemp=(String)tempHash.get("CoverId")==null?"0":(String)tempHash.get("CoverId");
				CoverIdSeaTemp=(String)tempHash.get("seaCheckOptions")==null?"0":(String)tempHash.get("seaCheckOptions");
				ExtraCoverIdTemp=(String)tempHash.get("ExtraCoverId")==null?"0":(String)tempHash.get("ExtraCoverId");
				for(int checks=0;checks<countCover;checks++)
				{
					String checkCoverId;
					checkCoverId=(String)coverIdTemp.get(Integer.toString(checks))==null?"":(String)coverIdTemp.get(Integer.toString(checks));
					if(coverIdTemp.containsValue(CoverIdTemp))
					{
						checkCoverFlag=true;
					}else
					{
						if(!"".equalsIgnoreCase(checkCoverId))
						{
							checkCoverFlag=false;
							break;
						}
					}
				}
				if(!checkCoverFlag)
				{
					clausesTemp=(String[][])commoDetailsT.get("clausesDesc");
					clausesMultiple=LINEBREAK+clausesMultiple+strfun.toStringAppend(clausesTemp,LINEBREAK,NORMAL)+LINEBREAK;
					//EditClausesTemp=(String[][])tempHash.get("editedClauses");
					editclausesMul=LINEBREAK+editclausesMul+strfun.toStringAppend(EditClausesTemp,LINEBREAK,EDIT)+LINEBREAK;
					openFreeTextTemp=(String[][])tempHash.get("openFreeText");
					freeTextMultiple=LINEBREAK+freeTextMultiple+strfun.toStringAppend(openFreeTextTemp,LINEBREAK,NORMAL)+LINEBREAK;
					coverIdTemp.put(Integer.toString(countCover),CoverIdTemp);
					countCover=countCover+1;
				}
				for(int checkSea=0;checkSea<countSeaCover;checkSea++)
				{
					String checkSeaCoverId;
					checkSeaCoverId =(String)coverIdSeaTemp.get(Integer.toString(checkSea))==null?"":(String)coverIdSeaTemp.get(Integer.toString(checkSea));
					if(coverIdSeaTemp.containsValue(CoverIdSeaTemp))
					{
						checkSeaCoverFlag=true;
					}else
					{
						if(!("".equalsIgnoreCase(checkSeaCoverId) || "0".equalsIgnoreCase(checkSeaCoverId)))
						{
							checkSeaCoverFlag=false;
							break;
						}
					}
				}
				if(!checkSeaCoverFlag)
				{
					seaFreeTextOptT=(String[][])tempHash.get("openSeaFreeTextOptions");
					freeTextMulSea=LINEBREAK+freeTextMulSea+strfun.toStringAppend(seaFreeTextOptT,LINEBREAK,NORMAL)+LINEBREAK;
					coverIdSeaTemp.put(Integer.toString(countSeaCover),CoverIdSeaTemp);
					countSeaCover=countSeaCover+1;
				}
				for(int checkExtra=0;checkExtra<countExtraCover;checkExtra++)
				{
					String checkExCoverId;
					checkExCoverId =(String)extraCoverIdT.get(Integer.toString(checkExtra))==null?"":(String)extraCoverIdT.get(Integer.toString(checkExtra));
					if(extraCoverIdT.containsValue(ExtraCoverIdTemp))
					{
						ckExtraCoverFlag=true;
					}else
					{
						if(!("".equalsIgnoreCase(checkExCoverId) || "0".equalsIgnoreCase(checkExCoverId)))
						{
							ckExtraCoverFlag=false;
							break;
						}
					}
				}
				if(!ckExtraCoverFlag)
				{
					extraClausesTemp=(String[][])commoDetailsT.get("extraClausesDesc");
					exClausesMul=LINEBREAK+exClausesMul+strfun.toStringAppend(extraClausesTemp,LINEBREAK,NORMAL)+LINEBREAK;
					//editExtraClausesT=(String[][])tempHash.get("editedExtraClauses");
					editExClausesMul=LINEBREAK+editExClausesMul+strfun.toStringAppend(editExtraClausesT,LINEBREAK,EDIT)+LINEBREAK;
					extraCoverIdT.put(Integer.toString(countExtraCover),ExtraCoverIdTemp);
					countExtraCover=countExtraCover+1;
				}
			
			}catch(Exception ex)
			{
				logger.info("@@@@@@@@the EXception Occuring here IN MULTIPLE CLAUSES FREE TEXT PRINTING AREA "+i+"----"+ex.toString());
				logger.debug(ex);
			}
			if(i==checkProperCount)
			{
				warrantiesTemp=(String[][])commoDetailsT.get("warrantyDesc");
				warMultiple=LINEBREAK+warMultiple+strfun.toStringAppend(warrantiesTemp,LINEBREAK,NORMAL)+LINEBREAK;
				//editWarClausesT=(String[][])tempHash.get("editedWarClauses");
				editwarMultiple=LINEBREAK+editwarMultiple+strfun.toStringAppend(editWarClausesT,LINEBREAK,EDIT)+LINEBREAK;
				exclusionsTemp=(String[][])commoDetailsT.get("exclusionsDesc");
				exMultiple=LINEBREAK+exMultiple+strfun.toStringAppend(exclusionsTemp,LINEBREAK,NORMAL)+LINEBREAK;
				//editExClausesT=(String[][])tempHash.get("editedExClauses");
				editexMultiple=LINEBREAK+editexMultiple+strfun.toStringAppend(editExClausesT,LINEBREAK,EDIT)+LINEBREAK;
			}	
			finalCountryNameT = isNull((String)tempHash.get("FinalDestinationCountryName"),"");
			transCountryNameT = isNull((String)tempHash.get("TransitCountryName"),"");
			TransitFromTemp = isNull((String)tempHash.get("TransitFrom"),"");
			finalDestinationT = isNull((String)tempHash.get("FinalDestination"),"");
			TransitFromTemp=(TransitFromTemp==null||"Others".equalsIgnoreCase(TransitFromTemp))?"":TransitFromTemp;
			if(!"".equalsIgnoreCase(TransitFromTemp.trim()))
			{
				TransitFromTemp=TransitFromTemp+",";	
			}
			transFromMul=TransitFromTemp+transCountryNameT;
				
			voyageSB.append(LINEBREAK+transFromMul+"\n\n\n");
			finalDestinationT=(finalDestinationT==null||"Others".equalsIgnoreCase(finalDestinationT))?"":finalDestinationT;
			if(!"".equalsIgnoreCase(finalDestinationT.trim()))
			{
				finalDestinationT=finalDestinationT+",";
			}
			finalDestMul=finalDestinationT+finalCountryNameT+LINEBREAK;
			voyageSB.append(finalDestMul);	
			hashAnnexure.put("voyageSB"+i,voyageSB.toString());
				
			PolicyDateTemp = isNull((String)tempHash.get("PolicyStartDate"),"");
			quoteGenDate = isNull((String)tempHash.get("QuoteGeneratedDate"),"");				
			polGenDateTemp = isNull((String)tempHash.get("PolicyGeneratedDate"),"");
			policyGenDate = isNull((String)tempHash.get("ActualPolicyGeneratedDate"),"");
			approvedBy = (String)tempHash.get("APPROVEDBY")==null?"":(String)tempHash.get("APPROVEDBY");

			certificateDate = (String)tempHash.get("CertificateDate")==null?"":(String)tempHash.get("CertificateDate");

			if(DRFATMODEMUL.equalsIgnoreCase(displayMode)){
				dateOfIssueSB.append(LINEBREAK);dateOfIssueSB.append(PolicyDateTemp);
			}else{
				dateOfIssueSB.append(LINEBREAK);dateOfIssueSB.append(polGenDateTemp);
			}
			hashAnnexure.put("dateOfIssueSB"+i,dateOfIssueSB.toString());

			TransportNameTemp = isNull((String)tempHash.get("TransportName"),"");

			SailingDateTemp = isNull((String)tempHash.get("sailDate"),"");
			VesselNameTemp = isNull((String)tempHash.get("VesselName"),"");
			CarrierNameTemp = isNull((String)tempHash.get("CarrierName"),"");
			if("NO VESSEL NAME PROVIDED".equalsIgnoreCase(VesselNameTemp))
			{
				VesselNameTemp="";
			}
			vesselSailingSB.append(LINEBREAK+TransportNameTemp+NEWLINE);
			if(VesselNameTemp.equalsIgnoreCase(SailingDateTemp))
			{
				if(VesselNameTemp.equalsIgnoreCase(CarrierNameTemp)){
					vesselSailingSB.append(VesselNameTemp+NEWLINE);
				}else
				{
					vesselSailingSB.append(VesselNameTemp+NEWLINE);
					vesselSailingSB.append(CarrierNameTemp+LINEBREAK);
				}

			}
			else if(VesselNameTemp.equalsIgnoreCase(CarrierNameTemp))
			{
				vesselSailingSB.append(VesselNameTemp);vesselSailingSB.append(NEWLINE);
				vesselSailingSB.append(SailingDateTemp);vesselSailingSB.append(LINEBREAK);
			}
			else
			{
				vesselSailingSB.append(VesselNameTemp);vesselSailingSB.append(NEWLINE);
				vesselSailingSB.append(SailingDateTemp);vesselSailingSB.append(LINEBREAK);
				vesselSailingSB.append(CarrierNameTemp);vesselSailingSB.append(LINEBREAK);
			}
			hashAnnexure.put("modeVesselSailingSB"+i,vesselSailingSB.toString());
			hashAnnexure.put("interestsSB"+i,interestsSB.toString());
			CurrencyNameTemp = isNull((String)tempHash.get("CurrencyName"),"");
			ExchangeRateTemp = isNull((String)tempHash.get("ExchangeRate"),"0");
			SaleTermValueTemp = isNull((String)tempHash.get("SaleTermValue"),"0");
			ExcessPremiumTemp = isNull((String)tempHash.get("ExcessPremium"),"0");
			payableAmountT = isNull((String)amtDetailsTemp.get("FinalPayableAmount"),"0");
			payableAmountT = Double.toString(Double.parseDouble(payableAmountT)+(Double.parseDouble(ExcessPremiumTemp)));
			payableAmtRoundT=payableAmountT;
			try
			{
				multiTotalPremium=Double.parseDouble(payableAmountT)+multiTotalPremium;
			}catch(Exception exx)
				{
					logger.info("the Exception Occured is multiTotalPremium "+exx.toString());
					logger.debug(exx);
				}
			premiumDetailsSB.append(NEWLINE);
			premiumDetailsSB.append(payableAmtRoundT);
	 		hashAnnexure.put("premiumDetailsSB"+i,premiumDetailsSB.toString());
			QuoteNoTemp=(String)tempHash.get(QUOTENO)==null?"":(String)tempHash.get(QUOTENO);//[0][26]);
			PolicyNoTemp = isNull((String)tempHash.get("PolicyNo"),"");
			openCoverNo = isNull((String)tempHash.get("openCoverNo"),"");
			openCoverMissiNo = isNull((String)tempHash.get("openCoverMississippiNo"),"");
			openCoverCustomer = isNull((String)tempHash.get("openCoverCustomer"),"");
			rsaSharedPercent = isNull((String)tempHash.get("openRsaSharedPercentage"),"0");
			insCompanines = (String[][])tempHash.get("openInsuranceCompanines");
			String tolDetailsTemp[][] = new String[0][0];
			if(QuoteNoTemp.length()>0)
			{
				declInvoiceNosSB.append(QuoteNoTemp+LINEBREAK);
				tolDetailsTemp = finalBean.getToleranceDetails(QuoteNoTemp,"QUOTENO",brokerBra);
			}
			else if(PolicyNoTemp.length()>0)
			{
				declInvoiceNosSB.append(PolicyNoTemp+LINEBREAK);
				tolDetailsTemp = finalBean.getToleranceDetails(QuoteNoTemp,POLICYNO,brokerBra);
			}
			certificateNo=(String)tempHash.get("CertificateNo")==null?"":(String)tempHash.get("CertificateNo");
			if(certificateNo.length()>0){
				declInvoiceNosSB.append(certificateNo+NEWLINE);
			}
			CarrierNameTemp = isNull((String)tempHash.get("CarrierName"),"");
			BlAwbNoTemp = isNull((String)tempHash.get("BlAwbNo"),"");
			BlAwbDateTemp = isNull((String)tempHash.get("BlAwbDate"),"");
			if(BlAwbNoTemp.length()>0||BlAwbDateTemp.length()>0){
				BLCdetailsSB.append(LINEBREAK+BlAwbNoTemp+","+BlAwbDateTemp+LINEBREAK);
			}
			LcNumberTemp = isNull((String)tempHash.get("LcNumber"),"");
			LcDateTemp = isNull((String)tempHash.get("LcDate"),"");
			if("0".equalsIgnoreCase(LcNumberTemp) || NONE.equalsIgnoreCase(LcNumberTemp))
			{
				LcNumberTemp="";
				LcDateTemp="";
			}
			if(LcNumberTemp.length()>0||LcDateTemp.length()>0){
				BLCdetailsSB.append(LcNumberTemp+","+LcDateTemp+LINEBREAK);
			}
			BankName = isNull((String)tempHash.get("BankName"),"");
			if(NONE.equalsIgnoreCase(BankName))
			{
				BankName="";	
			}
			BLCdetailsSB.append(BankName+LINEBREAK);
			hashAnnexure.put("BLCdetailsSB"+i,BLCdetailsSB.toString());
			finalCountTemp=Integer.parseInt((String)commoDetailsT.get("finalCount")==null?"0":(String)commoDetailsT.get("finalCount"));
			CommSumInsTemp = new String[finalCountTemp];
			PkgDescription=new String[finalCountTemp];
			commoNameTemp=new String[finalCountTemp];
			commoDesTemp=new String[finalCountTemp];
			SaleTermName=new String[finalCountTemp];
			rate_arrTemp=new String[finalCountTemp];
			seaValue_arrTemp=new String[finalCountTemp];
			wareHouseTemp=new String[finalCountTemp];
			warRate_arrTemp=new String[finalCountTemp];

			rag_arrTemp=new String[finalCountTemp];

			Supplier_arrTemp=new String[finalCountTemp];
			Invoice_arrTemp=new String[finalCountTemp];
			suminsLocalTemp=new String[finalCountTemp];
			commoSumRoundT=new String[finalCountTemp];
			sumLocalRoundT=new String[finalCountTemp];
			for(int ii=0;ii<finalCountTemp;ii++)
			{
				CommSumInsTemp[ii] = isNull((String)commoDetailsT.get("CommoditySumInsured"+(ii+1)),"0");
				commoDesTemp[ii] = isNull((String)commoDetailsT.get("CommodityDescription"+(ii+1)),"");
				PkgDescription[ii] = isNull((String)commoDetailsT.get("PackageDescription_arr"+(ii+1)),"");
				commoNameTemp[ii]=isNull((String)commoDetailsT.get("CommodityName_arr"+(ii+1)),"");
				SaleTermName[ii] = isNull((String)commoDetailsT.get("SaleTermName_arr"+(ii+1)),"");
				rate_arrTemp[ii]=(String)commoDetailsT.get("rate"+(ii+1))==null?"0":(String)commoDetailsT.get("rate"+(ii+1));//[0][20]);
				seaValue_arrTemp[ii]=(String)commoDetailsT.get("seaValue"+(ii+1))==null?"0":(String)commoDetailsT.get("seaValue"+(ii+1));//[0][20]);
				wareHouseTemp[ii]=(String)commoDetailsT.get("wareHouseValue"+(ii+1))==null?"0":(String)commoDetailsT.get("wareHouseValue"+(ii+1));//[0][20]);
				warRate_arrTemp[ii]=(String)commoDetailsT.get("warRate"+(ii+1))==null?"0":(String)commoDetailsT.get("warRate"+(ii+1));//[0][20]);
				rag_arrTemp[ii]=(String)commoDetailsT.get("rag"+(ii+1))==null?"":(String)commoDetailsT.get("rag"+(ii+1));//[0][20]);
				packingDetailsSB.append(LINEBREAK+rag_arrTemp[ii]+LINEBREAK);
				packingDetailsSB.append(PkgDescription[ii]);
				packingDetailsSB.append(LINEBREAK);
				rateDetailsSB.append(LINEBREAK+(Double.parseDouble(rate_arrTemp[ii])+Double.parseDouble(seaValue_arrTemp[ii])+Double.parseDouble(wareHouseTemp[ii]))+LINEBREAK);
				rateDetailsSB.append(warRate_arrTemp[ii]);
				rateDetailsSB.append("\n\n\n");
				marineOthersSB.append("\nMarine\nWSRCC\n\nOthers\n");
				Supplier_arrTemp[ii]=isNull((String)commoDetailsT.get("Supplier_arr"+(ii+1)),"");
				packingDetailsSB.append(Supplier_arrTemp[ii]);
				packingDetailsSB.append(LINEBREAK);
				Invoice_arrTemp[ii] = isNull((String)commoDetailsT.get("Invoice_arr"+(ii+1)),"");
				if(Invoice_arrTemp[ii].length()>0){
					declInvoiceNosSB.append(Invoice_arrTemp[ii]);declInvoiceNosSB.append(LINEBREAK);
				}
				suminsLocalTemp[ii] = isNull((String)commoDetailsT.get("SumInsuredLocal_arr"+(ii+1)),"0");

				double salePercentTemp=0.0;
				double sumLocalTemp=0.0;
				double sumLocalSaleTemp=0.0;
				double commoSaleTemp;
				double toleranceval = 0.0;
				String toleranceName="";
				if(tolDetailsTemp.length>0)
				{
					toleranceName = isNull(tolDetailsTemp[ii][0],"");
					toleranceval = Double.parseDouble(isNull(tolDetailsTemp[ii][1],"0"));
				}
				salePercentTemp=Double.parseDouble(SaleTermValueTemp);
				sumLocalTemp=Double.parseDouble(suminsLocalTemp[ii]);
				sumLocalSaleTemp=sumLocalTemp+(sumLocalTemp*(salePercentTemp/100));
				sumLocalSaleTemp = sumLocalSaleTemp + toleranceval;
				commoSaleTemp=Double.parseDouble(CommSumInsTemp[ii]);
				sumLocalRoundT[ii]=pdfFormat.decimalFormat(sumLocalSaleTemp,decimalDigit);
				commoSumRoundT[ii]=pdfFormat.decimalFormat(commoSaleTemp,decimalDigit);
				sumInsDetailsSB.append(LINEBREAK+sumLocalRoundT[ii]+LINEBREAK);
				mulTotalSumInsurL=(sumLocalSaleTemp)+mulTotalSumInsurL;

				if(toleranceName.length()>0){
					sumInsDetailsSB.append(SaleTermName[ii]+LINEBREAK+toleranceName+LINEBREAK);
				}else{
					sumInsDetailsSB.append(SaleTermName[ii]);sumInsDetailsSB.append(LINEBREAK);
				}
				sumInsDetailsSB.append(CurrencyNameTemp+" "+commoSumRoundT[ii]+LINEBREAK);
				sumInsDetailsSB.append(ExchangeRateTemp);sumInsDetailsSB.append(LINEBREAK);
		
			}
			hashAnnexure.put("rateDetailsSB"+i,rateDetailsSB.toString());
			hashAnnexure.put("marineOthersSB"+i,marineOthersSB.toString());
			hashAnnexure.put("declCertiInvoiceNosSB"+i,declInvoiceNosSB.toString());
			hashAnnexure.put("packingDetailsSB"+i,packingDetailsSB.toString());
			hashAnnexure.put("sumInsuredDetailsSB"+i,sumInsDetailsSB.toString());
			hashAnnexure.put(INFO+i,"0");
		}else
		{
			checkProperCount=checkProperCount+1;
			hashAnnexure.put(INFO+i,"SOME INFORMATIONS MISSING FOR LINKED QUOTE NO\t"+tempQuoteNo);
		}
		rowCount=rowCount+1;
	}
		hashAnnexure.put("multiTotalPremium",pdfFormat.decimalFormat(multiTotalPremium,decimalDigit));
		hashAnnexure.put("multiTotalSumInsuredLocal",pdfFormat.decimalFormat(mulTotalSumInsurL,decimalDigit));
		hashAnnexure.put("rowCount",Integer.toString(rowCount));
	}else
	{
			if(NORMALSUP.equalsIgnoreCase(displayMode))
			{
				PolicyNo = isNull((String)premiumdetails.get("PolicyNo"),"0");
				logger.info("under NORMAL SUPPLEMENT IS PolicyNo"+PolicyNo);
				displayModeDummy=NORMALSUP;
				displayMode=NORMAL;
			}
			commoditydetails=(HashMap)premiumdetails.get("commoditydetails");
			AmountDetails=(HashMap)premiumdetails.get("AmountDetails");
			PdfKnownText = isNull((String)premiumdetails.get("PdfSubjectKnownText"),NOTHING);
			TransitFrom = isNull((String)premiumdetails.get("TransitFrom"),"");
			FinalDestination = isNull((String)premiumdetails.get("FinalDestination"),"");
			PolicyDate = isNull((String)premiumdetails.get("PolicyStartDate"),"");
			policyGenDate = isNull((String)premiumdetails.get("ActualPolicyGeneratedDate"),"");
			quoteGenDate = isNull((String) premiumdetails.get("QuoteGeneratedDate"),"");
			approvedBy = isNull((String)premiumdetails.get("APPROVEDBY"),"");
			certificateDate = isNull((String)premiumdetails.get("CertificateDate"),"");
			CoverName = isNull((String)premiumdetails.get("CoverName"),"");
			extraCoverId = isNull((String)premiumdetails.get("ExtraCoverId"),"");
			
			TransportName = isNull((String)premiumdetails.get("TransportName"),"");
			CurrencyName = isNull((String)premiumdetails.get("CurrencyName"),"");
			ExchangeRate = isNull((String)premiumdetails.get("ExchangeRate"),"0");
			SaleTermValue = isNull((String)premiumdetails.get("SaleTermValue"),"0");
			QuoteNo = isNull((String)premiumdetails.get(QUOTENO),"");
			PolicyNo = isNull((String)premiumdetails.get("PolicyNo"),"");
			openCoverNo = isNull((String)premiumdetails.get("openCoverNo"),"");
			openCoverMissiNo = isNull((String)premiumdetails.get("openCoverMississippiNo"),"");
			openCoverCustomer = isNull((String)premiumdetails.get("openCoverCustomer"),"");
			CarrierName = isNull((String)premiumdetails.get("CarrierName"),"");
			BlAwbNo = isNull((String)premiumdetails.get("BlAwbNo"),"");
			BlAwbDate = isNull((String)premiumdetails.get("BlAwbDate"),"");
			LcNumber = isNull((String)premiumdetails.get("LcNumber"),"");
			LcDate = isNull((String)premiumdetails.get("LcDate"),"");
			if("0".equalsIgnoreCase(LcNumber) || NONE.equalsIgnoreCase(LcNumber))
			{
				LcNumber="";
				LcDate="";
			}
			BankName=(String)premiumdetails.get("BankName")==null?"":(String)premiumdetails.get("BankName");//[0][35]);
			if(NONE.equalsIgnoreCase(BankName))
			{
				BankName="";	
			}
			SailingDate = (String)premiumdetails.get("sailDate")==null?"":(String)premiumdetails.get("sailDate");
			SetAgentNameO = isNull((String)premiumdetails.get("SettlingAgentNameOthers"),"");
			SettlingAgentId=(String)premiumdetails.get("SettlingAgentId");//[0][37]);
			SetAgentName = isNull((String)premiumdetails.get("SettlingAgentName"),"");
			setAgentAddress1 = isNull((String)premiumdetails.get("SettlingAgentAddressOne"),"");
			setAgentAddress2 = isNull((String)premiumdetails.get("SettlingAgentAddressTwo"),"");
			SetAgentTelPh = isNull((String)premiumdetails.get("SettlingAgentTelephoneNo"),"");
			setAgentFaxNo = isNull((String)premiumdetails.get("SettlingAgentFaxNo"),"");
			CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
			CustomerLastName = isNull((String)premiumdetails.get("CustomerLastName"),"");
			custAddress1 = isNull((String)premiumdetails.get("CustomerAddressOne"),"");
			CustomerCountry = isNull((String)premiumdetails.get("CustomerCountry"),"");
			CustomerEmirate = isNull((String)premiumdetails.get("CustomerEmirate"),"");
			CustomerPoBox = isNull((String)premiumdetails.get("CustomerPoBox"),"");
			cusCompanyName = isNull((String)premiumdetails.get("CustomerCompanyName"),"");
			BrokerName = isNull((String)premiumdetails.get("BrokerName"),"");
			if(issuerName.length()>0){
				BrokerName = issuerName;
			}
			BrokerCommission = isNull((String)premiumdetails.get("BrokerCommission"),"0");
			LoginID = isNull((String)premiumdetails.get("LoginID"),"");
			BrokerRemarks = isNull((String)premiumdetails.get("BrokerRemarks"),"");
			BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
			EditClauses=(String[][])premiumdetails.get("editedClauses");
			EditExtraClauses=(String[][])premiumdetails.get("editedExtraClauses");
			EditWarClauses=(String[][])premiumdetails.get("editedWarClauses");
			EditExClauses=(String[][])premiumdetails.get("editedExClauses");
			rsaSharedPercent = isNull((String)premiumdetails.get("openRsaSharedPercentage"),"0");
			openFreeText=(String[][])premiumdetails.get("openFreeText");
			seaFreeTextOpt=(String[][])premiumdetails.get("openSeaFreeTextOptions");
			insCompanines=(String[][])premiumdetails.get("openInsuranceCompanines");
			Premium = isNull((String)premiumdetails.get("Premium"),"0");
			ExcessPremium = isNull((String)premiumdetails.get("ExcessPremium"),"0");
			finalCount=Integer.parseInt((String)commoditydetails.get("finalCount"));
			transitStartPlace = isNull((String)premiumdetails.get("TransitStartingPlace"),"");
			finalStartPlace=isNull((String)premiumdetails.get("FinalStartingPlace"),"");
			AdminStatus=isNull((String)premiumdetails.get("Remarks"),"");
			AdminRemarks = isNull((String)premiumdetails.get("AdminRemarks"),"");
			BackDaysOption = isNull((String)premiumdetails.get("BackDaysOption"),"");
			commissionAmount = isNull((String)AmountDetails.get("CommissionAmount"),"0");
			commiAmtRound=pdfFormat.decimalFormat(Double.parseDouble(commissionAmount),decimalDigit);	
			finalPayAmounts = isNull((String)AmountDetails.get("FinalPayableAmount"),"0");
			finalPayAmounts=Double.toString(Double.parseDouble(finalPayAmounts)+(Double.parseDouble(ExcessPremium)));
			totalSumInsured=(String)commoditydetails.get("TotalCommoditySI")==null?"0":(String)commoditydetails.get("TotalCommoditySI");
			InsuredTotal_st=(String)commoditydetails.get("TotalSILocal")==null?"0":(String)commoditydetails.get("TotalSILocal");
			insTotalRound=pdfFormat.decimalFormat(Double.parseDouble(InsuredTotal_st),decimalDigit);
			totalSumRounds=pdfFormat.decimalFormat(Double.parseDouble(totalSumInsured),decimalDigit);	
			finalCountryName = isNull((String)premiumdetails.get("FinalDestinationCountryName"),"");
			transCountryName = isNull((String)premiumdetails.get("TransitCountryName"),"");
			commoditySumIns = new String[finalCount];
			pkgDescription=new String[finalCount];
			CommodityName_arr=new String[finalCount];
			comDescription=new String[finalCount];
			SaleTermName_arr=new String[finalCount];
			ExclusionId_arr=new String[finalCount];
			Supplier_arr=new String[finalCount];
			Invoice_arr=new String[finalCount];
			WarrantyId_arr=new String[finalCount];
			sumInsuredLocal=new String[finalCount];
			comSumInsRound=new String[finalCount];
			sumInsuredLocalR=new String[finalCount];
			 for(int i=0;i<finalCount;i++)
			{
				commoditySumIns[i]=isNull((String)commoditydetails.get("CommoditySumInsured"+(i+1)),"0");
				comDescription[i]= isNull((String)commoditydetails.get("CommodityDescription"+(i+1)),"");
				pkgDescription[i]= isNull((String)commoditydetails.get("PackageDescription_arr"+(i+1)),"");
				CommodityName_arr[i]= isNull((String)commoditydetails.get("CommodityName_arr"+(i+1)),"");
				SaleTermName_arr[i]= isNull((String)commoditydetails.get("SaleTermName_arr"+(i+1)),"");
				ExclusionId_arr[i]= isNull((String)commoditydetails.get("ExclusionId_arr"+(i+1)),"0");
				Supplier_arr[i]= isNull((String)commoditydetails.get("Supplier_arr"+(i+1)),"");
				Invoice_arr[i]= isNull((String)commoditydetails.get("Invoice_arr"+(i+1)),"");
				WarrantyId_arr[i]= isNull((String)commoditydetails.get("WarrantyId_arr"+(i+1)),"0");
				sumInsuredLocal[i] = isNull((String)commoditydetails.get("SumInsuredLocal_arr"+(i+1)),"0");
			
				double salePercent=0.0;
				double sumLocal=0.0;
				double sumLocalSale=0.0;
				double commoditySI=0.0;
				double commoditySISale=0.0;
				double toleranceval = 0.0;
				double tolValuePer = 0.0;
				if(tolDetails.length>0)
				{
					toleranceval = Double.parseDouble(isNull(tolDetails[i][1],"0"));
					tolValuePer = Double.parseDouble(isNull(tolDetails[0][2],"0"));
				}
				salePercent=Double.parseDouble(SaleTermValue);
				sumLocal=Double.parseDouble(sumInsuredLocal[i]);
				sumLocalSale=sumLocal+(sumLocal*(salePercent/100));
				sumLocalSale = sumLocalSale + toleranceval;
				commoditySI=Double.parseDouble(commoditySumIns[i]);
				commoditySISale=commoditySI+(commoditySI*(salePercent/100));
				commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
				sumInsuredLocalR[i]=pdfFormat.decimalFormat(sumLocalSale,decimalDigit);
				comSumInsRound[i]=pdfFormat.decimalFormat(commoditySISale,decimalDigit);
			}
			exclusions=(String[][])commoditydetails.get("exclusionsDesc");
			warranties=(String[][])commoditydetails.get("warrantyDesc");
			clauses=(String[][])commoditydetails.get("clausesDesc");
			extraClauses=(String[][])commoditydetails.get("extraClausesDesc");
		}
			if(DRFATMODE.equalsIgnoreCase(displayMode))
			{
				final String args1[] = {QuoteNo};	
				String sqlDraft;sqlDraft="update position_master set OPEN_COVER_INT_STATUS='"+"DRAFTED"+"'  where  quote_no=?";
				runner.multipleUpdation(sqlDraft,args1);	
			}
			checkFlag=false;
		}
		else
		{
			checkFlag=true;
		}
	}
	catch(Exception ex)
	{
			logger.info("exception in print4"+ex.toString());logger.debug(ex);
	}
	if(checkFlag)
	{
		response.sendRedirect("Copy of information Admin.jsp?pdfStatus=NODATAS");if(true)return;
	}
		if(!displayModeDummy.equalsIgnoreCase(NORMALSUP)&&!(DRFATMODE.equalsIgnoreCase(displayMode)) && 
				!(DRFATMODEMUL.equalsIgnoreCase(displayMode)) && !(CERTMODE.equalsIgnoreCase(displayMode)))
		{
				if(NORMALMUL.equalsIgnoreCase(displayMode)){
					finalBean.updateMultiCommission(BrokerCommission,openCoverNo,PolicyNo,"11",brokerBra,taxPercent);
					
				}else{
					finalBean.updateOpenCommission(openCoverNo,PolicyNo,"11",brokerBra,BrokerCommission,taxPercent);
				}
		}
			//DB operation and assigning modification block end here
			openCoverStartDate=finalBean.getOpenCoverStartDate(openCoverNo);
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
				policyNoDummy=new policyInfo().getPolicyDefault(new String[]{"11","11"}, PolicyNo, brokerBra, openCoverNo, "");
			fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
			try
			{
				pageWater=new rsa.opencoverpdf.PageNumbersWatermarkNew();
				String urlPath;urlPath=request.getSession().getServletContext().getRealPath("/"+IMG+headImage);
				pageWater.setImagePath(urlPath);
				String urlPathFooter;urlPathFooter=request.getSession().getServletContext().getRealPath("/"+IMG+footImage);
				pageWater.setFooterImagePath(urlPathFooter);
				pageWater.setCreateDate(createDate);
				//if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
					pageWater.setDisplayText(displayText);						
				/*}else{
					pageWater.setDisplayText("");
				}*/
				pageWater.setDisplayMode(displayMode);
				pageWater.setCols(cols);
				pageWater.setFontPath(fontPath);

				//For KSA SIGN STAMP
				if (!NODATE.equalsIgnoreCase(policyGenDate)) 
				{
					polGenDate = policyGenDate.substring(0,policyGenDate.indexOf(' '));
					polGenTime = policyGenDate.substring(policyGenDate.indexOf(' ')+1,policyGenDate.length());
				}
				stampPath = request.getSession().getServletContext().getRealPath("/" + IMG+ stampImage);
				signPath = request.getSession().getServletContext().getRealPath("/" + IMG+ signImage);
				polGenDate = polGenDate+" Time: "+polGenTime;
				pageWater.setUsrModeController(usrModeController);
				pageWater.setPolicyOn(polGenDate);
				pageWater.setPolicyAt(place);
				pageWater.setEntered(BrokerName);
				pageWater.setApproved(approvedBy);
				pageWater.setSignedImage(signPath);
				pageWater.setStampImage(stampPath);
				pageWater.setStampStatus(stampStatus);
				pageWater.setIssuerName(issuerName);
				pageWater.setBraAddress(braAddress);
				pageWater.setBrokerCompany(BrokerCompany);
				pageWater.setCid(cid);
			}
			catch(Exception e)
			{
				logger.info("the Exception Occured in HEADER IMAGE "+e.toString());
				logger.debug(e);
			}
        } 
		catch(Exception ex) 
		{
            logger.debug(ex);
        }
        response.setContentType("application/pdf");
       
        document = new Document(PageSize.A4, 50, 50, 50, 60);
        try
		{
           Font watermarkFont;
           watermarkFont = new Font(Font.TIMES_ROMAN,9,Font.UNDERLINE);
           watermarkFont=FontFactory.getFont(FontFactory.TIMES,30,Font.BOLD,new CMYKColor(25,0,0,64));
           Chunk watermark;
            watermark = new Chunk(watermarkText,watermarkFont);
            watermark.setSkew(20f,-20f);
			String polino="";
			String fileNamePath="";
			
			if(DRFATMODE.equalsIgnoreCase(displayMode))
			{
				 polino = quoteDraftNo+"_Draft.pdf";
            }
			/** CERTIFICATE PDF START **/
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_certificate.pdf";
            }
			/** CERTIFICATE PDF END **/
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_DraftMultiple.pdf";
        	}
			else if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode))
			{
				polino = PolicyNoFour+"QuoteScheduleOpen.pdf";
			}
			else
			{
				polino = PolicyNoFour+"ScheduleOpen.pdf";
			}
			
			//Block Added by Chinna
			System.out.println("polino: >>>>>>> bef"+polino);
			if(polino!=null && polino.length()>0 && polino.indexOf("/")!=-1)
			{
				polino=polino.replaceAll("/", "-");
				System.out.println("polino: >>>>>>>"+polino);
			}
			
			if("ORIGINAL COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
			}
			else if("COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
			}
			else if("DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
			}
			/** CERTIFICATE PDF START **/
			else if("CERTIFICATE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
			}
			/** CERTIFICATE PDF END **/
			else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino);
			}
			else if(INVPOLICY.equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
			}
			else if("INVALID DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
			}else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino);
			}
			else if ((displayText.trim()).length()<=0) 
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/" + polino);
			} 
			else
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
			}
			logger.info("the X FILE NAME IS 123131231"+fileNamePath);
			File filePath;
			filePath=new File(fileNamePath);
			System.out.println("displayMode: "+displayMode);
			if(filePath.exists() && (NORMAL.equalsIgnoreCase(displayMode) || (NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)) || NORMALMUL.equalsIgnoreCase(displayMode)))
			{
				System.out.println("writer1: "+writer1);
				InputStream certFile;
				certFile = new FileInputStream(new File(fileNamePath));
				OutputStream respOutStream;
				respOutStream = response.getOutputStream();
				byte[] buf;
				buf = new byte[1024];
				int len;
				writer1.setPageEvent(pageWater);
				len = certFile.read(buf);
				while (len > 0) {
					respOutStream.write(buf, 0, len);
					len = certFile.read(buf);
				}
				certFile.close();
				respOutStream.flush();
				respOutStream.close();
				writer1 = PdfWriter.getInstance(document, respOutStream);
				document.open();
			}
			else
			{
	            try
		        {
		      		logger.info("royaltest....open..displayText............"+displayText);
					if("ORIGINAL COPY".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino)));
					}
					else if("COPY".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino)));
					}
					else if("DRAFT".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino)));
					}
					/** CERTIFICATE PDF START **/
					else if("CERTIFICATE".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino)));
						
					}
					/** CERTIFICATE PDF END **/
					else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino)));
					}
					else if(INVPOLICY.equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino)));
					}
					else if("INVALID DRAFT".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new 	FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino)));
					}
					else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino)));
						
					}
					else if(NORMALMUL.equalsIgnoreCase(displayMode))
					{
						writer = PdfWriter.getInstance(document, new 	FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/LiveOriginalMultiplePdf/"+polino)));
					}
					else if ((displayText.trim()).length()<=0) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/OriginalPdf/"+polino)));
					} 
					else
					{
						writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino)));
					}
			}
			catch(Exception eb)
			{
				logger.info(eb); logger.debug(eb);
			}
			try
			{
				try
				{
					writer.setPageEvent(pageWater);
				}
				catch(Exception exEve)
				{
					logger.info("the Exception here 33 is "+exEve.toString()); 
					logger.debug(exEve);
				}
			writer1 = PdfWriter.getInstance(document, response.getOutputStream());
			try
			{
				writer1.setPageEvent(pageWater);
			}
			catch(Exception exEve2)
			{
				logger.info("the Exception here44 is "+exEve2.toString());
				logger.debug(exEve2);
			}
				document.open();
			}
			catch(Exception ebs)
			{
				logger.info(ebs);
				logger.debug(ebs);
				}
			}
			
			String ConvertToWords="";
			String fillsDigit="100";
			NumberToWordBean convertWord = new NumberToWordBean();
			rsa.pdf.PDFCreatorBean creatorBean=new rsa.pdf.PDFCreatorBean();
			Font fontHeadNormal, fontHeadBold, fontTextNormal, fontTextBold, fontTextUnderLine, fontTextFooter;
			tableCreation.setTable(8);
			BaseFont arialFont;
			arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);
			fontHeadBold = new Font(arialFont, 9, Font.BOLD);
			fontHeadNormal = new Font(arialFont, 9, Font.NORMAL);
			fontTextUnderLine = new Font(arialFont, 9, Font.UNDERLINE
					| Font.BOLD);
			fontTextBold = new Font(arialFont, 8, Font.BOLD);
			fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
			fontTextFooter = new Font(arialFont, 6, Font.NORMAL);
			font = new Font(arialFont, 8, Font.NORMAL);
			/*tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("MARINE CARGO INSURANCE POLICY", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Emirates INSURANCE CO.  hereby agrees in consideration of the payment to us by or on behalf of the Assured of the premium specified in the Schedule to insure against loss, damage or expense in the manner hereinafter provided.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("\nSCHEDULE", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);*/
			
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("MARINE CARGO", fontTextNormal, Rectangle.NO_BORDER,8, 2);
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode)){
				tableCreation.insertCell("QUOTE NO. "+PolicyNo, fontTextNormal, Rectangle.NO_BORDER,8, 2);
			}else{
				tableCreation.insertCell("INSURANCE CERTIFICATE NO. "+PolicyNo.substring(PolicyNo.lastIndexOf("-")+1,PolicyNo.length()), fontTextNormal, Rectangle.NO_BORDER,8, 2);
			}
			tableCreation.insertCell("POLICY PERIOD ALWAYS OPEN FROM: "+openCoverStartDate, fontTextNormal, Rectangle.NO_BORDER,8, 2);
//			tableCreation.insertCell((bankName.length()>0?bankName+", ":"")+(custCompany.length()>0?custCompany:(CustomerFirstName+CustomerLastName)), fontTextNormal, Rectangle.NO_BORDER,4, 0);
			if(YES.equalsIgnoreCase(bankerAssOpt) && "NO".equalsIgnoreCase(bankerOption))
			{
				if(cusCompanyName==null || cusCompanyName.equalsIgnoreCase(""))
				{
					
					tableCreation.insertCell("NAME OF ASSURED\t\t\t\t\t\t\t\t\t\t"+(BankName.length()>0?BankName+" / ":"")+CustomerFirstName+CustomerLastName, fontTextNormal, Rectangle.NO_BORDER,4, 0);
				}
				else
				{
					tableCreation.insertCell("NAME OF ASSURED\t\t\t\t\t\t\t\t\t\t"+(BankName.length()>0?BankName+" / ":"")+cusCompanyName, fontTextNormal, Rectangle.NO_BORDER,4, 0);
				}
			}
			else if(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankerAssOpt) )
			{
				tableCreation.insertCell("NAME OF ASSURED\t\t\t\t\t\t\t\t\t\t"+BankName, fontTextNormal, Rectangle.NO_BORDER,4, 0);
			}
			else
			{
				if(cusCompanyName==null || cusCompanyName.equalsIgnoreCase(""))
				{
					tableCreation.insertCell("NAME OF ASSURED\t\t\t\t\t\t\t\t\t\t"+CustomerFirstName+CustomerLastName, fontTextNormal, Rectangle.NO_BORDER,4, 0);
				}
				else
				{
					tableCreation.insertCell("NAME OF ASSURED\t\t\t\t\t\t\t\t\t\t"+cusCompanyName, fontTextNormal, Rectangle.NO_BORDER,4, 0);
				}
			}
			tableCreation.insertCell("\nDate:\t\t\t\t\t\t\t\t\t\t"+ASPER, fontTextNormal, Rectangle.NO_BORDER,4, 1);
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode)){
				tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+policyNoDummy.substring(0,policyNoDummy.lastIndexOf("-"))+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}else{
				tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+PolicyNo.substring(0,PolicyNo.lastIndexOf("-"))+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			
			/*if(DRAFTMODE.equalsIgnoreCase(displayMode) ||TEST.equalsIgnoreCase(usrModeController) || DRFATMODEMUL.equalsIgnoreCase(displayMode))
			{
				tableCreation.insertCell("QUOTE NUMBER", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			}
			else
			{	tableCreation.insertCell("BORDEREAUX NUMBER", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			}
			logger.info("royal pdf test for multiple PolicyNo..."+PolicyNo);
			logger.info("royal pdf test for multiple QuoteNo..."+QuoteNo);
			if(DRAFTMODE.equalsIgnoreCase(displayMode)||TEST.equalsIgnoreCase(usrModeController)){
				tableCreation.insertCell(QuoteNo+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDATE OF POLICY\t\t\t\t\t\t\t\t"+PolicyDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}else{
				tableCreation.insertCell((PolicyNo.length()<=0?QuoteNo:PolicyNo)+"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDATE OF POLICY\t\t\t\t\t\t\t\t"+ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}*/
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("VOYAGE", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("From: "+ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("To: "+ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("CONVEYANCE", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			for(int print=0;print<finalCountTemp;print++){
				tableCreation.insertCell("SUBJECT-MATTER INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				if(YES.equalsIgnoreCase(currencyOption)){
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("BASIS OF VALUATION", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					String fileb = "";
					String finalPre = "";
					if (totalSumInsured.length() > 0&& totalSumInsured.indexOf('.') != -1) 
					{
						fileb = CommSumInsTemp[print].substring(CommSumInsTemp[print].indexOf('.') + 1,	CommSumInsTemp[print].length());
						if(fileb.length() >= 3)
						{
							if(fillsDigit.equalsIgnoreCase(HUND)){
								fileb = fileb.substring(0,2);
							}else if(fillsDigit.equalsIgnoreCase(THOU)){
								fileb = fileb.substring(0,3);
							}
						}
						else if(fileb.length() == 1)
						{
							if(fillsDigit.equalsIgnoreCase(HUND)){
								fileb = fileb+"0";
							}else if(fillsDigit.equalsIgnoreCase(THOU)){
								fileb = fileb+"00";
							}
						}
						finalPre = CommSumInsTemp[print].substring(0, CommSumInsTemp[print].indexOf('.'));
					} 
					else
					{
						fileb = "0";
						finalPre = "0";
					}
					finalPre = finalPre.replaceAll(",","");
					ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
					tableCreation.insertCell("SUM INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					
				}else
				{
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("BASIS OF VALUATION", fontTextNormal, Rectangle.NO_BORDER,3, 0);					
					//tableCreation.insertCell(currencyType+". "+currencyType1+" "+localSumround[print], fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					String fileb = "";
					String finalPre = "";
					if (suminsLocalTemp[print].length() > 0&& suminsLocalTemp[print].indexOf('.') != -1) 
					{
						fileb = suminsLocalTemp[print].substring(suminsLocalTemp[print].indexOf('.') + 1,	suminsLocalTemp[print].length());
						if(fileb.length() >= 3)
						{
							if(fillsDigit.equalsIgnoreCase(HUND)){
								fileb = fileb.substring(0,2);
							}else if(fillsDigit.equalsIgnoreCase(THOU)){
								fileb = fileb.substring(0,3);
							}
						}
						else if(fileb.length() == 1)
						{
							if(fillsDigit.equalsIgnoreCase(HUND)){
								fileb = fileb+"0";
							}else if(fillsDigit.equalsIgnoreCase(THOU)){
								fileb = fileb+"00";
							}
						}
						finalPre = suminsLocalTemp[print].substring(0,suminsLocalTemp[print].indexOf('.'));
					} 
					else
					{
						fileb = "0";
						finalPre = "0";
					}
					finalPre = finalPre.replaceAll(",","");
					ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
					tableCreation.insertCell("SUM INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}
			}
			//Premium table
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("PREMIUM", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			if(YES.equalsIgnoreCase(PremiumYes.trim())){
				tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}else{
				tableCreation.insertCell(ASPER, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
			tableCreation.insertCell("", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("CLAUSES, ENDORSEMENTS, SPECIAL CONDITIONS AND WARRANTIES:", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCellPad("TERMS, CONDITIONS, WARRANTIES AND EXCLUSIONS AS PER THE OPEN COVER", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);			
			//Table5 for clauses and conditions//
			ArrayList warrenties;
			warrenties = new ArrayList();
			ArrayList excess;
			excess = new ArrayList();
			int prc = 0;
			tableCreation1.setTable(8);
			if (EditClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("CLAUSES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a22 = 0; a22 < EditClauses.length; a22++) 
				{
					EditClauses[a22][1]=EditClauses[a22][1]==null?"":EditClauses[a22][1];
					if(EditClauses[a22][1].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((EditClauses[a22][1]==null?"":EditClauses[a22][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (clauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("CLAUSES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a1 = 0; a1 < clauses.length; a1++) 
				{
					clauses[a1][0]=clauses[a1][0]==null?"":clauses[a1][0];
					if(clauses.length>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((clauses[a1][0]==null?"":clauses[a1][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (EditExtraClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("W&SRCC CLAUSES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a222 = 0; a222 < EditExtraClauses.length; a222++) 
				{
					EditExtraClauses[a222][1]=EditExtraClauses[a222][1]==null?"":EditExtraClauses[a222][1];
					if(EditExtraClauses[a222][1].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((EditExtraClauses[a222][1]==null?"":EditExtraClauses[a222][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (extraClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("W&SRCC CLAUSES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a2 = 0; a2 < extraClauses.length; a2++) 
				{
					extraClauses[a2][0]=extraClauses[a2][0]==null?"":extraClauses[a2][0];
					if(extraClauses[a2][0].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((extraClauses[a2][0]==null?"":extraClauses[a2][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (EditExClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("EXCLUSIONS", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a333 = 0; a333 < EditExClauses.length; a333++) 
				{
					EditExClauses[a333][1]=EditExClauses[a333][1]==null?"":EditExClauses[a333][1];
					if(EditExClauses[a333][1].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((EditExClauses[a333][1]==null?"":EditExClauses[a333][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (exclusions.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("EXCLUSIONS", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a3 = 0; a3 < exclusions.length; a3++) 
				{
					exclusions[a3][0]=exclusions[a3][0]==null?"":exclusions[a3][0];
					if(exclusions[a3][0].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((exclusions[a3][0]==null?"":exclusions[a3][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			
			if (!("NO BROKER REMARKS CAME".equalsIgnoreCase(BrokerRemarks))) 
			{
				warrenties.add((prc++),BrokerRemarks);
			}
			if ("admin".equalsIgnoreCase(AdminStatus)) 
			{
				if ("NoDate".equalsIgnoreCase(PolicyDate)) 
				{
					if (!("NOTHING".equalsIgnoreCase(PdfKnownText)||"null".equalsIgnoreCase(PdfKnownText)||	"".equalsIgnoreCase(PdfKnownText))) 
					{
						warrenties.add((prc++),PdfKnownText);
					}
				} 
				else 
				{
		
					if (!("NOTHING".equalsIgnoreCase(PdfKnownText)||"null".equalsIgnoreCase(PdfKnownText)||	"".equalsIgnoreCase(PdfKnownText)))
					{				
						warrenties.add((prc++),PdfKnownText);
					}
				}
			} 
			else
			{
				warrenties.add((prc++),creatorBean.getPolicysFreshBackDesc(brokerBra,PolicyNo,QuoteNo));
			}
			if(!(AdminRemarks == null||"".equalsIgnoreCase(AdminRemarks))) 
			{
				warrenties.add((prc++),AdminRemarks);
			}
			//Warrenties
			if (EditWarClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("WARRANTIES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a3334 = 0; a3334 < EditWarClauses.length; a3334++) 
				{
					EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
					if(EditWarClauses[a3334][1].length()>0)
					{
					EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
					if(EditWarClauses[a3334][1].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
					}
				}
				for(int pl=0;pl<prc;pl++)
				{
					if(((String)warrenties.get(pl)).length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			if (warranties.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("WARRANTIES", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for (int a4 = 0; a4 < warranties.length; a4++) 
				{
					warranties[a4][0]=warranties[a4][0]==null?"":warranties[a4][0];
					if(warranties[a4][0].length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((warranties[a4][0]==null?"":warranties[a4][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				for(int pl=0;pl<prc;pl++)
				{
					if(((String)warrenties.get(pl)).length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			//Excess
			prc=0;
			if(excess.size()>0){
				tableCreation1.setTable(8);
				tableCreation1.insertCell("EXCESS", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				for(int pl=0;pl<prc;pl++)
				{
					if(((String)excess.get(pl)).length()>0)
					{
						tableCreation1.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
						tableCreation1.insertCellPad((String)excess.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
					}
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
//			tableCreation.setTableSpacing(getPdfSpacing(cid,5f,3f));
			//document.add(tableCreation.getTable());
			//tableCreation.setTable(8);
			//document.newPage();
			/*tableCreation.insertCell("SURVEY CLAUSE", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCellPad("In the event of loss or damage which may give rise to a claim under this policy , notice must be given immediately to the under noted Agent(s) so that he/they may appoint a Surveyor if he/they so desire.", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);*/
			//tableCreation.insertCellPad("Agents at Emirates are THE COMPANY\'S OFFICE", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
			/*tableCreation2.setTable(8);
			if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
			{
				tableCreation2.insertCellPad(setAgentMul, fontTextNormal, Rectangle.BOX,8, 0);
			}
			else
			{
				if(SettlingAgentId.equalsIgnoreCase("0")){
					if("".equalsIgnoreCase(SetAgentNameO) || " ".equalsIgnoreCase(SetAgentNameO) ||  NULL.equalsIgnoreCase(SetAgentNameO) ){
						tableCreation2.insertCellPad(braAddress+", "+braPO+", "+braCity+", "+braCountry+" Tel: "+braPhone+"   Fax: "+ braFax, fontTextNormal, Rectangle.BOX,8, 0);
					}else{
						tableCreation2.insertCellPad(SetAgentNameO, fontTextNormal, Rectangle.BOX,8, 0);
					}
					//tableCreation2.insertCellPad("ARAB ORIENT INSURANCE (P.S.C.), P.O. BOX 27966, DUBAI - U.A.E., Phone:04-2953425, Fax:04-2955701, Contact :", fontHead3, Rectangle.BOX,8, 0);
				}else{
						tableCreation2.insertCellPad(SetAgentName+"\b"+setAgentAddress1+"\b"+setAgentAddress2+"\b Tel:\b"+SetAgentTelPh+"\bFax:\b"+setAgentFaxNo, fontTextNormal, Rectangle.BOX,8, 0);
				}
			}
			tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0,10,10);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("CLAIMS", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCellPad("In the event of a claim arising under this policy it is agreed that it shall be settled in accordance with English Law and Practice and shall be so settled in Emirates or at", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCellPad("In Witness whereof this policy has been signed for and on behalf of", fontTextNormal, Rectangle.NO_BORDER,8, 0,10);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);*/
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("SURVEY CLAUSE", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("In the event of loss or damage for which the Company is presumed to be liable, immediate notice must be given to Emirates Insurance Co, HEAD OFFICE and no claim for loss or damage will be entertained by the Company unless it is substantiated by a certificate from the above-mentioned agent.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("CLAIMS", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("For the resolution of any dispute and of and contestation which may arise regarding the application or interpretation of a clause of the present contract, and for anything which is not expressly foreseen, the parties declare to refer to the disposition of the English Laws governing this matter with the exception of all questions imperatively governed by the National Law.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			Image signImageRoyal = null;
			Image stampImageRoyal = null;
			try
			{
				signImageRoyal  = Image.getInstance(signPath);
				stampImageRoyal  = Image.getInstance(stampPath);
//				signImageRoyal.scaleToFit(70,54);
				signImageRoyal.scaleToFit(130,130);
				//stampImageRoyal.scaleToFit(60,54);
				//stampImageRoyal.scaleToFit(130,130);
				
			}
			catch(Exception e)
			{
				logger.debug(e);
			}
			if(!DRFATMODEMUL.equalsIgnoreCase(displayMode)){
				tableCreation1.insertCell("Signed on: "+ polGenDate, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("Signed at "+place, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			if(approvedBy.equalsIgnoreCase("Nil"))
				tableCreation1.insertCell("Entered/Approved By: "+BrokerName, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			else
				tableCreation1.insertCell("Entered/Approved By: "+ BrokerName+"/"+approvedBy, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,4, 0,0);
			if(stampStatus.equalsIgnoreCase("Y")){
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,4, 1);
			}
			else{
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,4, 1);
			}
			tableCreation.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			if(issuerName.length()<=0){
				tableCreation.insertCell("This certificate is issued by "+ BrokerCompany+ " on behalf of "+braAddress, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			document.add(tableCreation.getTable());
			
			
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
				document.setPageSize(PageSize.A4.rotate());
				TableHeader endPage1;
				PdfPTable stable=null;
				try
				{
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					/*tableCreation.setTable(3);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					tableCreation.insertCell("", font, Rectangle.NO_BORDER, 3, 2);
					stable = tableCreation.getTable();
					document.add(stable);*/

					/////////Loop///////
					/*String countRows="";
					String voyageS;
					String dateOfIssueS;
					String declInvoiceNosS;
					String declCertiQno="";
					String BLCdetailsS;
					String packingDetailsS;
					String vesselSailingS;
					String interestsS;
					String sumInsuredS;
					String premiumDetailsS;
					String rateDetailsS;
					String marineOthersS;

					String mulTotalPremium;
					String mulTotalSumLocal;
					countRows=(String)hashAnnexure.get("rowCount")==null?"0":(String)hashAnnexure.get("rowCount");			
					mulTotalPremium=(String)hashAnnexure.get("multiTotalPremium")==null?"0":(String)hashAnnexure.get("multiTotalPremium");			
					mulTotalSumLocal = isNull((String)hashAnnexure.get("multiTotalSumInsuredLocal"),"0");*/			
				
				document.newPage();
				//document.add(stable);
				try
				{
					logger.info("openCoverMissiNo..."+openCoverMissiNo);
					logger.info("PolicyNo..."+PolicyNo);
					logger.info("openCoverCustomer..."+openCoverCustomer);
					endPage1=new TableHeader();
					endPage1.setOpenCoverNo(openCoverMissiNo);
					endPage1.setCertificateNo(PolicyNo);
					endPage1.setCurrency(currencyType1);
					endPage1.setFontPath(fontPath);
					endPage1.setDisplayMode(displayMode);
					endPage1.setCurrencyType(currencyType);
					endPage1.setClientName(openCoverCustomer);
					String empty;
					empty= request.getSession().getServletContext().getRealPath("/"+"/images/EmptyImage.jpg");
					endPage1.setImagePath(empty);
					writer.setPageEvent(endPage1);
					writer1.setPageEvent(endPage1);
				}
				catch(Exception e)
				{
					logger.info("third the Exception is "+e.toString());logger.debug(e);
				}
				//String qnos[] = new String[Integer.parseInt(countRows)];
				/*String qnos[] = null;
				PDFCreatorBean pdfMul;
				pdfMul = new PDFCreatorBean();
				if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
				{
					if(PolicyNo.length()>0)
					{
						StringTokenizer quoToken;
						quoToken = new StringTokenizer(PolicyNo,",");
						int loopQ=0;
						qnos = new String[quoToken.countTokens()];
						while(quoToken.hasMoreTokens())
						{
							qnos[loopQ++] = quoToken.nextToken();
						}
					}
				}
				else if(NORMALMUL.equalsIgnoreCase(displayMode))
				{
					String multiQuotes[][] = new String[0][0];
					multiQuotes = pdfMul.getMultiQuotes(PolicyNo);
					qnos = new String[multiQuotes.length];
					for(int q=0;q<multiQuotes.length;q++){
						qnos[q] = multiQuotes[q][0]==null?"0":multiQuotes[q][0];
					}
				}
				for(int i=0; i<Integer.parseInt(countRows); i++)
				{	
					infoMissingStatus=(String)hashAnnexure.get(INFO+i)==null?"0":(String)hashAnnexure.get(INFO+i);
					
					voyageS = isNull((String)hashAnnexure.get("voyageSB"+i),"");
					dateOfIssueS = isNull((String)hashAnnexure.get("dateOfIssueSB"+i),"");
					declInvoiceNosS = isNull((String)hashAnnexure.get("declCertiInvoiceNosSB"+i),"");
					BLCdetailsS = isNull((String)hashAnnexure.get("BLCdetailsSB"+i),"");
					packingDetailsS = isNull((String)hashAnnexure.get("packingDetailsSB"+i),"");
					vesselSailingS = isNull((String)hashAnnexure.get("modeVesselSailingSB"+i),"");
					interestsS = isNull((String)hashAnnexure.get("interestsSB"+i),"");
					sumInsuredS = isNull((String)hashAnnexure.get("sumInsuredDetailsSB"+i),"");
					premiumDetailsS = isNull((String)hashAnnexure.get("premiumDetailsSB"+i),"");
					rateDetailsS = isNull((String)hashAnnexure.get("rateDetailsSB"+i),"");
					marineOthersS = isNull((String)hashAnnexure.get("marineOthersSB"+i),"");
					
					if(infoMissingStatus.length() > 0 && !("0".equalsIgnoreCase(infoMissingStatus)))
					{
						if(i==0)
						{
							tableCreation.setTable(11);
							tableCreation.insertCell(infoMissingStatus, font, Rectangle.BOX, 11, 0);
							tableCreation.setTableSpacingBefore(51f);
							document.add(tableCreation.getTable());
						}
						else
						{
							tableCreation.setTable(11);
							tableCreation.insertCell(infoMissingStatus, font, Rectangle.BOX, 11, 0);
							document.add(tableCreation.getTable());
						}
					}
					else
					{
						if(i<=qnos.length)
						{
							declCertiQno = qnos[i];
						}
						if(i==0)
						{
							//
							String preQuote = declCertiQno;
							premiumDetailsS = pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit)
							+LINEBREAK+pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("WarPremium"+preQuote)),decimalDigit);
							if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
								if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("GovTax"+preQuote)),decimalDigit);
								}
								if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fund"+preQuote)),decimalDigit);
								}if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit);
								}
							}
							//
							tableCreation.setTable(11);
							tableCreation.insertCell(dateOfIssueS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(declInvoiceNosS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(BLCdetailsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(packingDetailsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(vesselSailingS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(voyageS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(interestsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(sumInsuredS, font, Rectangle.BOX, 1, 1);
							tableCreation.insertCell(marineOthersS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(rateDetailsS, font, Rectangle.BOX, 1, 1);
							tableCreation.insertCell(premiumDetailsS, font, Rectangle.BOX, 1, 1);
							tableCreation.setTableSpacingBefore(51f);
							document.add(tableCreation.getTable());
						}
						else
						{
							tableCreation.setTable(11);
							tableCreation.insertCell(dateOfIssueS, font, Rectangle.BOX, 1, 0);
							//
							String preQuote = declCertiQno;
							premiumDetailsS = pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit)
							+LINEBREAK+pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("WarPremium"+preQuote)),decimalDigit);
							if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
								if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("GovTax"+preQuote)),decimalDigit);
								}
								if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fund"+preQuote)),decimalDigit);
								}if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
									premiumDetailsS = premiumDetailsS+LINEBREAK+
									pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit);
								}
							}
							//
							tableCreation.insertCell(declInvoiceNosS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(BLCdetailsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(packingDetailsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(vesselSailingS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(voyageS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(interestsS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(sumInsuredS, font, Rectangle.BOX, 1, 1);
							tableCreation.insertCell(marineOthersS, font, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(rateDetailsS, font, Rectangle.BOX, 1, 1);
							tableCreation.insertCell(premiumDetailsS, font, Rectangle.BOX, 1, 1);
							document.add(tableCreation.getTable());
						}
					}
				}*/
				if(multiQuoteInfo!=null && multiQuoteInfo.length>0){
						//document.newPage();
						tableCreation.setTable(15);
					for (int i = 0; i < multiQuoteInfo.length; i++) {
						tableCreation.insertCell(isNull(multiQuoteInfo[i][0],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][1],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][2],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][3],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][4],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][5],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][6],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][7],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][8],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuoteInfo[i][9],"0")), decimalDigit), font, Rectangle.BOX, 1, 1);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][10],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][11],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][12],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(isNull(multiQuoteInfo[i][13],""), font, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuoteInfo[i][14],"0")), decimalDigit), font, Rectangle.BOX, 1, 1);
					}
					try {
						tableCreation.getTable().setWidths(new float[] {5f, 10f, 10f, 15f, 10f, 10f, 12f, 12f, 10f, 12f, 12f, 12f, 5f, 10f, 12f});
					} catch (DocumentException e) {
						
						e.printStackTrace();
					}
					tableCreation.setTableSpacingBefore(45f);
					document.add(tableCreation.getTable());
					if(multiQuotePremiumInfo!=null && multiQuotePremiumInfo.length>0){
						tableCreation.setTable(2);
						tableCreation.insertCell("\n\n", fontTextBold, Rectangle.NO_BORDER, 2, 0);
						tableCreation.insertCell("PREMIUM CALCULATION ("+currencyType+")", fontTextBold, Rectangle.BOX, 2, 2);
						tableCreation.insertCell("MARINE", fontTextBold, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuotePremiumInfo[0][0],"0")), decimalDigit), fontTextBold, Rectangle.BOX, 1, 1);
						if(multiQuotePremiumInfo[0][1]!=null && !"0".equals(multiQuotePremiumInfo[0][1])){
							tableCreation.insertCell("WAR", fontTextBold, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuotePremiumInfo[0][1],"0")), decimalDigit), fontTextBold, Rectangle.BOX, 1, 1);
						}
						if(multiQuotePremiumInfo[0][2]!=null && !"0".equals(multiQuotePremiumInfo[0][2])){
							tableCreation.insertCell("EXCESS PREMIUM", fontTextBold, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuotePremiumInfo[0][2],"0")), decimalDigit), fontTextBold, Rectangle.BOX, 1, 1);
						}
						if(multiQuotePremiumInfo[0][3]!=null && !"0".equals(multiQuotePremiumInfo[0][3])){
							tableCreation.insertCell("ISSUANCE FEE", fontTextBold, Rectangle.BOX, 1, 0);
							tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuotePremiumInfo[0][3],"0")), decimalDigit), fontTextBold, Rectangle.BOX, 1, 1);
						}
						tableCreation.insertCell("TOTAL PREMIUM", fontTextBold, Rectangle.BOX, 1, 0);
						tableCreation.insertCell(pdfFormat.decimalFormat(Double.parseDouble(isNull(multiQuotePremiumInfo[0][4],"0")), decimalDigit), fontTextBold, Rectangle.BOX, 1, 1);
						try {
							tableCreation.getTable().setWidths(new float[] {10f, 10f});
							tableCreation.getTable().setWidthPercentage(30);
							tableCreation.getTable().setKeepTogether(true);
							tableCreation.getTable().setHorizontalAlignment(tableCreation.getTable().ALIGN_RIGHT);
						} catch (DocumentException e) {
							
							e.printStackTrace();
						}
					}
				}
				/*logger.info("Before Printing mulTotalSumLocal in PDF "+mulTotalSumLocal);
				logger.info("Before mulTotalPremium in PDF "+mulTotalPremium);
				tableCreation.setTable(11);
				tableCreation.insertCell("", font, Rectangle.BOX, 7, 0);
				tableCreation.insertCell(mulTotalSumLocal, font, Rectangle.BOX, 1, 1);
				tableCreation.insertCell("Grand Total", font, Rectangle.BOX, 2, 2);
				tableCreation.insertCell(pdfFormat.decimalFormat(Math.round(Double.parseDouble(mulTotalPremium.replaceAll(",", ""))),decimalDigit), font, Rectangle.BOX, 1, 1);*/
				document.add(tableCreation.getTable());
				document.close();
			}
			catch(DocumentException de) 
			{
				logger.debug(de);
			}
		}//End if If Condition
//			}
        }
		catch (BadElementException ex) 
		{
            logger.info("ROyal Test first catch.."+ex.toString());
			logger.debug(ex);ex.printStackTrace();
            document=print(ex,document);
        }
		catch (DocumentException ex) 
		{
            logger.info("ROyal Test second catch.."+ex.toString());
			logger.debug(ex);ex.printStackTrace();
            document=print(ex,document);
        }
        catch (Exception ex) 
		{
			logger.info("ROyal Test third catch.."+ex.toString());
            ex.printStackTrace();
			logger.debug(ex);
            document=print(ex,document);
        } 
		finally
		{
            try
            {
            	document.close();
            }
            catch (Exception e)
            {
            	logger.debug(e);
            }
        }
    }
    private Document print(final Exception exc,final Document document) {
        try 
		{
            logger.info("Exception Class : name   "+exc.toString());
			logger.debug(exc);
			document.add(new Paragraph("\n\n\n\n\n"));
            document.add(new Paragraph("Exception Class : name    "+exc.toString()));
        }
		catch (Exception ex1) 
		{
            logger.debug(ex1);
        }
        return document;
    }
	private float getPdfSpacing(final String cid,final float value1,final float value2) {
		return (SAUDI.equalsIgnoreCase(cid)||BHARAIN.equalsIgnoreCase(cid))?value1:value2;
	}
	private PdfPTableCreation addPdfSpaceLine(final String cid,final PdfPTableCreation tableCreation,
			final Font fontText,final int len)throws BaseException {
		if (SAUDI.equalsIgnoreCase(cid)||BHARAIN.equalsIgnoreCase(cid)){
			for(int i=0;i<len;i++){
				tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			}
		}
		return tableCreation;
	}
	public String isNull(final String content,final String value)throws BaseException{
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
	protected void processRequestSingle(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException,BaseException 
	{
    	Map premiumdetails;
        Map commoditydetails;
    	HttpSession session=null;
    	rsa.opencoverpdf.PageNumbersWatermarkNew pageWater=null;
    	//All the Declaration and initialiation start from here
		String  certificateNo="",BrokerName="",PolicyNo="",CoverName="",extraCoverId="",CarrierName="",TransitFrom="",FinalDestination="",
		BlAwbNo="",BlAwbDate="",CoverIdTemp="",CoverIdSeaTemp="",ExtraCoverIdTemp="",issuerName="";

		String verNo = "";
		String BrokerCommission="";
		String PdfKnownText="";
		String Premium="";
		String ExcessPremium="";
		String BrokerRemarks="";
		String BrokerCompany="";
		String setAgentMul="";
		String brokerRemarkMul="";
		String adminRemarkMul="";
		String editexMultiple="";
		String exMultiple="";
		String warMultiple="";
		String editwarMultiple="";
		String clausesMultiple="";
		String exClausesMul="";
		String editExClausesMul="";
		String editclausesMul="";
		String freeTextMulSea="";
		String freeTextMultiple="";
		String place = "";
		String infoMissingStatus="";
		String freight="";
		int checkProperCount=0;
		
		String check="";

		String[] ExclusionId_arr=new String[0];
		String[] Supplier_arr=new String[0];
		String[] Invoice_arr=new String[0];
		String[] commoditySumIns = new String[0];
		String[] WarrantyId_arr=new String[0];
		String BankName="";
		String CustomerFirstName="",CustomerLastName="",custAddress1="",
		CustomerCountry="",CustomerEmirate="";
		double exchange=0;
		boolean checkFlag=false;
		String totalSumRounds="";
		String insTotalRound="";
		String PremiumYes="";
		String PolicyNoFour="";
		String AdminStatus="";
		String AdminRemarks="";
		String BackDaysOption="";
		double multiTotalPremium=0.0;
		double mulTotalSumInsurL=0.0;
		String tolDetails[][] = new String[0][0];
		String displayModeDummy="";
		String certificateDate = "";
		String policyGenDate="";
		String quoteGenDate="";
		String approvedBy="";
		String CustomerPoBox="";
		String cusCompanyName="";
		HashMap hashAnnexure=new HashMap();
		String finalDestMul="";
		String transFromMul="",SailingDate="",
		SailingDateTemp="",VesselNameTemp="",PolicyNoTemp;
		String openCoverNo="";
		String openCoverMissiNo="";
		String openCoverCustomer="";
		StringBuffer concatAll=new StringBuffer();
		String watermarkText=INVPOLICY;
		String totalSumInsured="";
		String InsuredTotal_st="";
		String LoginID="";
		String CommodityName_arr[]=new String[0];
		String comDescription[]=new String[0];
		String sumInsuredLocal[]=new String[0];
		String sumInsuredLocalR[]=new String[0];
		String comSumInsRound[]=new String[0];
		String pkgDescription[]=new String[0];
		
		String SetAgentName="",SetAgentNameO="",SettlingAgentId="",
		setAgentAddress1="",setAgentAddress2="",setAgentFaxNo="",
		SetAgentTelPh="",ExchangeRate="",CurrencyName="",currencyShortName="",
		TransportName="",QuoteNo="",SaleTermValue="";
		int finalCount=0;
		String commissionAmount="";
		String finalPayAmounts="";
		String finalCountryName="";
		String transCountryName="";
		String PolicyDate="";
		String[] SaleTermName_arr=new String[0];
		String LcNumber="";
		String LcDate="";
		String commiAmtRound="";
		String transitStartPlace="";
		String finalStartPlace="";
		String TransitFromTemp="",finalDestinationT="",BlAwbNoTemp="",BlAwbDateTemp="",CarrierNameTemp="",
		PolicyDateTemp="",LcNumberTemp="",LcDateTemp="",polGenDateTemp="",TransportNameTemp="",
		CurrencyNameTemp="",QuoteNoTemp="",SaleTermValueTemp="",finalCountryNameT="",transCountryNameT="";
		int finalCountTemp=0;
		String payableAmountT="";
		String payableAmtRoundT="";
		String ExchangeRateTemp="";
		String[] Supplier_arrTemp=new String[0];
		String[] Invoice_arrTemp=new String[0];
		String[] CommSumInsTemp = new String[0];

		String commoNameTemp[]=new String[0];
		String commoDesTemp[]=new String[0];

		String suminsLocalTemp[]=new String[0];
		String sumLocalRoundT[]=new String[0];
		String commoSumRoundT[]=new String[0];

		String SaleTermName[]=new String[0];
		String PkgDescription[]=new String[0];
		String rate_arrTemp[]=new String[0];
		String seaValue_arrTemp[]=new String[0];
		String wareHouseTemp[]=new String[0];
		String warRate_arrTemp[]=new String[0];
		String rag_arrTemp[]=new String[0];

		String[][] extraClauses=new String[0][0];
		String[][] exclusions=new String[0][0];
		String[][] warranties=new String[0][0];
		String[][] clauses=new String[0][0];
		String[][] EditExtraClauses=new String[0][0];
		String[][] EditExClauses=new String[0][0];
		String[][] EditWarClauses=new String[0][0];
		String[][] EditClauses=new String[0][0];
		String[][] openFreeText=new String[0][0];
		String[][] seaFreeTextOpt=new String[0][0];
		String[][] insCompanines=new String[0][0];
		String rsaSharedPercent="";
		String bankerOption="";
		String bankerAssOpt="";
		String currencyOption="";
		String excessOption="";
		String viewStatus="";
		String displayText="";
		String displayMode="";
		String quoteDraftNo="";
		String braAddress = "";
		String braPO = "";
		String braCity = "";
		String braCountry = "";
		String braPhone = "";
		String braFax = "";
		String cid="";
		double taxPercent = 0;
	//All the Declaration and initialiation End from here
		premiumdetails=new HashMap();
		commoditydetails=new HashMap();
		String headImage = "";
		String footImage = "";
		String signImage = "";
		String stampImage = "";
		String stampStatus = "";
		String currencyType = "";
		String currencyType1 = "";
		String usrModeController="";
		String cusCode ="",createDate="";
		String brokerBra = "";
		int decimalDigit = 0;
		String website="";
		String fontPath ="";
		finalprint finalBean;
		finalBean = new finalprint();
		rsa.pdf.finalprint finalData;
		finalData = new rsa.pdf.finalprint();
		rsa.pdf.PDFDisplay pdfFormat;
		pdfFormat = new rsa.pdf.PDFDisplay();
		Map taxDetails;taxDetails = new HashMap();
		rsa.pdf.PDFCreatorBean creatorBean;
		creatorBean = new rsa.pdf.PDFCreatorBean();
		
		//added for jasper report
		String draftNo="",belongingBranch="";
		try{
			session=request.getSession(true);
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			belongingBranch = (String)session.getAttribute("BelongingBranch");
			
			PolicyNo = request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			draftNo=PolicyNo;
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
			quoteDraftNo=PolicyNo;
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
			displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
			displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
			PolicyNoFour = PolicyNo;
		}catch (Exception e) {
			e.printStackTrace();
		}
		 /*
         * 
         * Removed for Jasper Report By Aswin
         * 
         * 
         * 
         * 
       try
		{
			session=request.getSession(true);
			
			if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("login/error_messg_pdf.jsp");	
				return;
			}
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
			displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
		   displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
		   if(TEST.equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText))
			{
				displayText=INVPOLICY;
			}
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			creatorBean.setBrokerBra(brokerBra);
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				creatorBean.setDcid((String)brokerDetails.get("Destination"));
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
				website = (String)brokerDetails.get("Site");
				creatorBean.setTaxPercent(taxPercent);
				creatorBean.setWebsite(website);
			}
			
			PolicyNo = request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			quoteDraftNo=PolicyNo;
			LoginID=request.getParameter("loginid")==null?"0":request.getParameter("loginid");
			PremiumYes=request.getParameter("printoption")==null?"":request.getParameter("printoption");
			bankerOption=request.getParameter("bankerOption")==null?"":request.getParameter("bankerOption");
			bankerAssOpt = isNull(request.getParameter("bankerAssuredOption"),"");
			currencyOption=request.getParameter("currencyOption")==null?"":request.getParameter("currencyOption");
			excessOption=request.getParameter("excessOption")==null?"":request.getParameter("excessOption");
			viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");

			
			creatorBean.setCid(cid);
			creatorBean.setDecimalDigit(decimalDigit);
			creatorBean.setUsrModeController(usrModeController);
			creatorBean.setCurrencyOption(currencyOption);
			creatorBean.setExcessOption(excessOption);
			creatorBean.setBankerAssuredOption(bankerAssOpt);
			creatorBean.setBankerOption(bankerOption);
			creatorBean.setPremiumYes(PremiumYes);
			
			logger.info("======== LoginID is "+LoginID);
			logger.info("======== policynumber is "+PolicyNo);
			logger.info("======== PremiumYes is "+PremiumYes);
			logger.info("======== bankerOption is "+bankerOption);
			logger.info("======== currencyOption is "+currencyOption);
			logger.info("======== excessOption1 is "+excessOption);
			logger.info("======== bankerAssOpt is "+bankerAssOpt);
			logger.info("======== displayMode is "+displayMode);
			logger.info("======== viewStatus is "+viewStatus);
			logger.info("======== displayText is "+displayText);
			
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				certificateNo = finalBean.getCertificateNo(brokerBra,(String)session.getAttribute("product_id"),PolicyNo );
			}

			if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)){
				check = "home.QUOTE_NO='" + PolicyNo + "'";
			}
			String placeCode[][] = new String[0][0];
			String cols="";
			
		try
		{
			//if(PolicyNo.indexOf('-')==-1){
			if(finalBean.isNumeric(PolicyNo)){
				check = "home.quote_no in("+PolicyNo+")";
			}else{
				 check = "home.policy_no='"+PolicyNo+"'";
			}
			//final String args[] = {brokerBra};
			if(check.length()>0){
				stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where "+check);
			}
			if(stampStatus.length()<=0)
			{
				String args1[] = new String[1];
				args1[0] = PolicyNo;
				stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where home.quote_no in(select quote_no from position_master where policy_no=?)",args1);
			}
		}
		catch (Exception e)
		{
			logger.debug(e);
		}
			
			String rubberOption;
			rubberOption = request.getParameter("rubberOption")==null?"":request.getParameter("rubberOption");
			if(rubberOption.length()>0){
				stampStatus = rubberOption;
			}
			creatorBean.setStampStatus(stampStatus);
			creatorBean.setPolicyNo(PolicyNo);
			creatorBean.setDisplayMode(displayMode);
			
			PolicyNoFour = PolicyNo;
			
			try
			{
				if(DRFATMODE.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this draftMode DRAFT BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,QUOTENO,brokerBra,cid,SCHEDULE);
					tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
					issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
					cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
					createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
					currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
				}
				else if(CERTMODE.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this certificateMode certificate BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,QUOTENO,brokerBra,cid,SCHEDULE);
					tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
					issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
					cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
					createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
					currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
				}
				else if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
				{
					logger.info("comes inside this MULTIPLE DRAFT BLOCK");
					premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLEDRAFT",brokerBra,cid,SCHEDULE);
					issuerName = finalBean.getIssuerName(PolicyNo,"QUOTENOMULTIPLEDRAFT");
					cusCode = finalBean.getCustomerCode(PolicyNo,"QUOTENOMULTIPLEDRAFT","11");
					createDate = finalBean.getForamattedDate(PolicyNo,"QUOTENOMULTIPLEDRAFT");
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11","QUOTENOMULTIPLEDRAFT");
					taxDetails = finalBean.getTaxDetails(PolicyNo,"11","QUOTENOMULTIPLEDRAFT");
					currencyShortName = finalBean.getFCName(PolicyNo,"11","QUOTENOMULTIPLEDRAFT",brokerBra);
				}
				else
				{
					if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
					{
						logger.info("comes inside this QUOTENOMULTIPLE BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOMULTIPLE",brokerBra,cid,SCHEDULE);
						issuerName = finalBean.getIssuerName(PolicyNo,"QUOTENOMULTIPLE");
						cusCode = finalBean.getCustomerCode(PolicyNo,"QUOTENOMULTIPLE","11");
						createDate = finalBean.getForamattedDate(PolicyNo,"QUOTENOMULTIPLE");
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11","QUOTENOMULTIPLE");
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11","QUOTENOMULTIPLE");
						currencyShortName = finalBean.getFCName(PolicyNo,"11","QUOTENOMULTIPLE",brokerBra);
					}
					else if(NORMALSUP.equalsIgnoreCase(displayMode))
					{
						logger.info("comes inside this QUOTENOSINGLE BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,"QUOTENOSINGLE",brokerBra,cid,SCHEDULE);
						tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
						issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
						cusCode = finalBean.getCustomerCode(PolicyNo,QUOTENO,"11");
						createDate = finalBean.getForamattedDate(PolicyNo,QUOTENO);
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11",QUOTENO);
						currencyShortName = finalBean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
					}
					else
					{
						logger.info("comes inside this POLICYNO BLOCK");
						premiumdetails=finalBean.checkingvalues(PolicyNo,LoginID,POLICYNO,brokerBra,cid,SCHEDULE);
						tolDetails = finalBean.getToleranceDetails(PolicyNo,POLICYNO,brokerBra);
						issuerName = finalBean.getIssuerName(PolicyNo,POLICYNO);
						cusCode = finalBean.getCustomerCode(PolicyNo,POLICYNO,"11");
						createDate = finalBean.getForamattedDate(PolicyNo,POLICYNO);
						placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",POLICYNO);
						taxDetails = finalBean.getTaxDetails(PolicyNo,"11",POLICYNO);
						currencyShortName = finalBean.getFCName(PolicyNo,"11",POLICYNO,brokerBra);
					}
				}
				if(placeCode.length>0)
				{
					creatorBean.setPlace(finalBean.isNull(placeCode[0][0], ""));
					headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
					footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
					signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
					stampImage = placeCode[0][4] == null ? "" : placeCode[0][4];
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
				}
				

				HashMap AmountDetails=new HashMap();
				String checkPolicyDatas="NODATAS";
				checkPolicyDatas=(String)premiumdetails.get("CheckPolicy")==null?checkPolicyDatas:(String)premiumdetails.get("CheckPolicy");
				logger.info("royal test the CHECK POLICY DECLARED ONE "+checkPolicyDatas);
				if(checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
					{
						rsa.opencoverpdf.StringFunction strfun;
						strfun =new rsa.opencoverpdf.StringFunction();
						int rowCount=0;
						Map commoDetailsT;commoDetailsT=new HashMap();
						Map amtDetailsTemp;amtDetailsTemp=new HashMap();
						String finalCountsString="";	
						CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
						CustomerLastName = isNull((String)premiumdetails.get("CustomerLastName"),"");
						custAddress1 = isNull((String)premiumdetails.get("CustomerAddressOne"),"");
						CustomerCountry = isNull((String)premiumdetails.get("CustomerCountry"),"");
						CustomerEmirate = isNull((String)premiumdetails.get("CustomerEmirate"),"");
						CustomerPoBox = isNull((String)premiumdetails.get("CustomerPoBox"),"0");
						cusCompanyName = isNull((String)premiumdetails.get("CustomerCompanyName"),"");
						BrokerName = isNull((String)premiumdetails.get("BrokerName"),"");
						if(issuerName.length()>0){
							BrokerName = issuerName;
						}
						BrokerCommission = isNull((String)premiumdetails.get("BrokerCommission"),"0");
						LoginID = isNull((String)premiumdetails.get("LoginID"),"");
						BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
						finalCountsString = isNull((String)premiumdetails.get("finalCounts"),"0");

					String tempQuoteNo="";
					String setAgentOthersT="";
					String setAgentIdTemp="";
					String setAgentNameT="";
					String setAgentTelphT="";
					String setAgentFaxNoT="";
					String setAgentAddTwoT="";
					String setAgentAddOneT="";
					Map tempHash;tempHash=new HashMap();
					String BrokerRemarksTemp=LINEBREAK;
					String AdminRemarksTemp=LINEBREAK;
					String ExcessPremiumTemp="";
		
					int countCover=0;
					int countSeaCover=0;
					int countExtraCover=0;
					boolean checkCoverFlag=false;
					boolean checkSeaCoverFlag=false;
					boolean ckExtraCoverFlag=false;

					java.util.HashMap extraCoverIdT;extraCoverIdT =new java.util.HashMap();
					java.util.HashMap coverIdTemp;coverIdTemp =new java.util.HashMap();
					java.util.HashMap coverIdSeaTemp;coverIdSeaTemp =new java.util.HashMap();

			for(int i=0;i<Integer.parseInt(finalCountsString);i++)
			{

				StringBuffer voyageSB;voyageSB=new StringBuffer();
				StringBuffer dateOfIssueSB;dateOfIssueSB=new StringBuffer();
				StringBuffer declInvoiceNosSB;declInvoiceNosSB=new StringBuffer();
				StringBuffer BLCdetailsSB;BLCdetailsSB=new StringBuffer();
				StringBuffer packingDetailsSB;packingDetailsSB=new StringBuffer();
				StringBuffer vesselSailingSB;vesselSailingSB=new StringBuffer();
				StringBuffer interestsSB;interestsSB=new StringBuffer();
				StringBuffer sumInsDetailsSB;sumInsDetailsSB=new StringBuffer();
				StringBuffer premiumDetailsSB;premiumDetailsSB=new StringBuffer();
				StringBuffer rateDetailsSB;rateDetailsSB=new StringBuffer();
				StringBuffer marineOthersSB;marineOthersSB=new StringBuffer();
				String[][] extraClausesTemp=new String[0][0];
				String[][] exclusionsTemp=new String[0][0];
				String[][] warrantiesTemp=new String[0][0];
				String[][] clausesTemp=new String[0][0];
				String[][] editExtraClausesT=new String[0][0];
				String[][] editExClausesT=new String[0][0];
				String[][] editWarClausesT=new String[0][0];
				String[][] EditClausesTemp=new String[0][0];
				String[][] openFreeTextTemp=new String[0][0];
				String[][] seaFreeTextOptT=new String[0][0];
				insCompanines=new String[0][0];
				tempQuoteNo=(String)premiumdetails.get(QUOTENO+i)==null?"0":(String)premiumdetails.get(QUOTENO+i);//[0][84]);
				tempHash=(HashMap)premiumdetails.get(tempQuoteNo)==null?tempHash:(HashMap)premiumdetails.get(tempQuoteNo);//[0][84]);
				if(tempHash.size() > 2)
				{
					setAgentOthersT = isNull((String)tempHash.get("SettlingAgentNameOthers"),"");
					setAgentIdTemp = isNull((String)tempHash.get("SettlingAgentId"),"");
					setAgentNameT = isNull((String)tempHash.get("SettlingAgentName"),"");
					setAgentAddOneT = isNull((String)tempHash.get("SettlingAgentAddressOne"),"");
					setAgentAddTwoT = isNull((String)tempHash.get("SettlingAgentAddressTwo"),"");
					setAgentTelphT = isNull((String)tempHash.get("SettlingAgentTelephoneNo"),"");
					setAgentFaxNoT = isNull((String)tempHash.get("SettlingAgentFaxNo"),"");

					if("0".equalsIgnoreCase(setAgentIdTemp))
					{
						if("".equalsIgnoreCase(setAgentOthersT) || " ".equalsIgnoreCase(setAgentOthersT) ||  NULL.equalsIgnoreCase(setAgentOthersT) )
						{
							setAgentMul=setAgentMul+"\t\t Lloyd's Agent at Destination\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t or \n\t\t"+braAddress+","+braPO+",\t"+braCity+", "+braCountry+ "\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t Tel:\t"+braPhone+ "\tFax:\t"+ braFax;
						}
						else if(setAgentMul.trim().equals(""))
						{
							setAgentMul=setAgentMul+"\t\t"+setAgentOthersT+NEWLINE;
						}
					}else
					{
						if("".equalsIgnoreCase(setAgentNameT))
						{
							setAgentMul=setAgentMul+"\n\t\tSETTLING AGENT NOT GIVEN FOR QUOTE NO"+QuoteNoTemp+NEWLINE;				
						}
						else if(setAgentMul.trim().equals(""))
						{
								setAgentMul=setAgentMul+"\n\t\t"+setAgentNameT+".\n\t\t"+setAgentAddOneT+"\t"+setAgentAddTwoT+"\t Tel:\t"+setAgentTelphT+"\tFax:\t"+setAgentFaxNoT+NEWLINE;
						}

					}
					BrokerRemarksTemp = isNull((String)tempHash.get("BrokerRemarks"),"");
					String CommodityDE;CommodityDE = isNull((String)tempHash.get("CommodityDE"),"");
					if(BrokerRemarksTemp.length()>0)
					{
						brokerRemarkMul=LINEBREAK+brokerRemarkMul+BrokerRemarksTemp+LINEBREAK;
					}
					interestsSB.append(LINEBREAK+CommodityDE+"\n\n\n");
					AdminRemarksTemp = isNull((String)tempHash.get("AdminRemarks"),"");
					if(!"".equalsIgnoreCase(AdminRemarksTemp))
					{
						adminRemarkMul = LINEBREAK+adminRemarkMul+AdminRemarksTemp+LINEBREAK;
					}
					amtDetailsTemp = (HashMap)tempHash.get("AmountDetails");
					commoDetailsT = (HashMap)tempHash.get("commoditydetails");
			try
			{
				CoverIdTemp=(String)tempHash.get("CoverId")==null?"0":(String)tempHash.get("CoverId");
				CoverIdSeaTemp=(String)tempHash.get("seaCheckOptions")==null?"0":(String)tempHash.get("seaCheckOptions");
				ExtraCoverIdTemp=(String)tempHash.get("ExtraCoverId")==null?"0":(String)tempHash.get("ExtraCoverId");
				for(int checks=0;checks<countCover;checks++)
				{
					String checkCoverId;
					checkCoverId=(String)coverIdTemp.get(Integer.toString(checks))==null?"":(String)coverIdTemp.get(Integer.toString(checks));
					if(coverIdTemp.containsValue(CoverIdTemp))
					{
						checkCoverFlag=true;
					}else
					{
						if(!"".equalsIgnoreCase(checkCoverId))
						{
							checkCoverFlag=false;
							break;
						}
					}
				}
				if(!checkCoverFlag)
				{
					clausesTemp=(String[][])commoDetailsT.get("clausesDesc");
					clausesMultiple=LINEBREAK+clausesMultiple+strfun.toStringAppend(clausesTemp,LINEBREAK,NORMAL)+LINEBREAK;
					EditClausesTemp=(String[][])tempHash.get("editedClauses");
					editclausesMul=LINEBREAK+editclausesMul+strfun.toStringAppend(EditClausesTemp,LINEBREAK,EDIT)+LINEBREAK;
					openFreeTextTemp=(String[][])tempHash.get("openFreeText");
					freeTextMultiple=LINEBREAK+freeTextMultiple+strfun.toStringAppend(openFreeTextTemp,LINEBREAK,NORMAL)+LINEBREAK;
					coverIdTemp.put(Integer.toString(countCover),CoverIdTemp);
					countCover=countCover+1;
				}
				for(int checkSea=0;checkSea<countSeaCover;checkSea++)
				{
					String checkSeaCoverId;
					checkSeaCoverId =(String)coverIdSeaTemp.get(Integer.toString(checkSea))==null?"":(String)coverIdSeaTemp.get(Integer.toString(checkSea));
					if(coverIdSeaTemp.containsValue(CoverIdSeaTemp))
					{
						checkSeaCoverFlag=true;
					}else
					{
						if(!("".equalsIgnoreCase(checkSeaCoverId) || "0".equalsIgnoreCase(checkSeaCoverId)))
						{
							checkSeaCoverFlag=false;
							break;
						}
					}
				}
				if(!checkSeaCoverFlag)
				{
					seaFreeTextOptT=(String[][])tempHash.get("openSeaFreeTextOptions");
					freeTextMulSea=LINEBREAK+freeTextMulSea+strfun.toStringAppend(seaFreeTextOptT,LINEBREAK,NORMAL)+LINEBREAK;
					coverIdSeaTemp.put(Integer.toString(countSeaCover),CoverIdSeaTemp);
					countSeaCover=countSeaCover+1;
				}
				for(int checkExtra=0;checkExtra<countExtraCover;checkExtra++)
				{
					String checkExCoverId;
					checkExCoverId =(String)extraCoverIdT.get(Integer.toString(checkExtra))==null?"":(String)extraCoverIdT.get(Integer.toString(checkExtra));
					if(extraCoverIdT.containsValue(ExtraCoverIdTemp))
					{
						ckExtraCoverFlag=true;
					}else
					{
						if(!("".equalsIgnoreCase(checkExCoverId) || "0".equalsIgnoreCase(checkExCoverId)))
						{
							ckExtraCoverFlag=false;
							break;
						}
					}
				}
				if(!ckExtraCoverFlag)
				{
					extraClausesTemp=(String[][])commoDetailsT.get("extraClausesDesc");
					exClausesMul=LINEBREAK+exClausesMul+strfun.toStringAppend(extraClausesTemp,LINEBREAK,NORMAL)+LINEBREAK;
					editExtraClausesT=(String[][])tempHash.get("editedExtraClauses");
					editExClausesMul=LINEBREAK+editExClausesMul+strfun.toStringAppend(editExtraClausesT,LINEBREAK,EDIT)+LINEBREAK;
					extraCoverIdT.put(Integer.toString(countExtraCover),ExtraCoverIdTemp);
					countExtraCover=countExtraCover+1;
				}
			
			}catch(Exception ex)
			{
				logger.info("@@@@@@@@the EXception Occuring here IN MULTIPLE CLAUSES FREE TEXT PRINTING AREA "+i+"----"+ex.toString());
				logger.debug(ex);
			}
			if(i==checkProperCount)
			{
				warrantiesTemp=(String[][])commoDetailsT.get("warrantyDesc");
				warMultiple=LINEBREAK+warMultiple+strfun.toStringAppend(warrantiesTemp,LINEBREAK,NORMAL)+LINEBREAK;
				editWarClausesT=(String[][])tempHash.get("editedWarClauses");
				editwarMultiple=LINEBREAK+editwarMultiple+strfun.toStringAppend(editWarClausesT,LINEBREAK,EDIT)+LINEBREAK;
				exclusionsTemp=(String[][])commoDetailsT.get("exclusionsDesc");
				exMultiple=LINEBREAK+exMultiple+strfun.toStringAppend(exclusionsTemp,LINEBREAK,NORMAL)+LINEBREAK;
				editExClausesT=(String[][])tempHash.get("editedExClauses");
				editexMultiple=LINEBREAK+editexMultiple+strfun.toStringAppend(editExClausesT,LINEBREAK,EDIT)+LINEBREAK;
			}	
			finalCountryNameT = isNull((String)tempHash.get("FinalDestinationCountryName"),"");
			transCountryNameT = isNull((String)tempHash.get("TransitCountryName"),"");
			TransitFromTemp = isNull((String)tempHash.get("TransitFrom"),"");
			finalDestinationT = isNull((String)tempHash.get("FinalDestination"),"");
			TransitFromTemp=(TransitFromTemp==null||"Others".equalsIgnoreCase(TransitFromTemp))?"":TransitFromTemp;
			if(!"".equalsIgnoreCase(TransitFromTemp.trim()))
			{
				TransitFromTemp=TransitFromTemp+",";	
			}
			transFromMul=TransitFromTemp+transCountryNameT;
				
			voyageSB.append(LINEBREAK+transFromMul+"\n\n\n");
			finalDestinationT=(finalDestinationT==null||"Others".equalsIgnoreCase(finalDestinationT))?"":finalDestinationT;
			if(!"".equalsIgnoreCase(finalDestinationT.trim()))
			{
				finalDestinationT=finalDestinationT+",";
			}
			finalDestMul=finalDestinationT+finalCountryNameT+LINEBREAK;
			voyageSB.append(finalDestMul);	
			hashAnnexure.put("voyageSB"+i,voyageSB.toString());
				
			PolicyDateTemp = isNull((String)tempHash.get("PolicyStartDate"),"");
			quoteGenDate = isNull((String)tempHash.get("QuoteGeneratedDate"),"");				
			polGenDateTemp = isNull((String)tempHash.get("PolicyGeneratedDate"),"");
			policyGenDate = isNull((String)tempHash.get("ActualPolicyGeneratedDate"),"");
			approvedBy = (String)tempHash.get("APPROVEDBY")==null?"":(String)tempHash.get("APPROVEDBY");

			certificateDate = (String)tempHash.get("CertificateDate")==null?"":(String)tempHash.get("CertificateDate");

			if(DRFATMODEMUL.equalsIgnoreCase(displayMode)){
				dateOfIssueSB.append(LINEBREAK);dateOfIssueSB.append(PolicyDateTemp);
			}else{
				dateOfIssueSB.append(LINEBREAK);dateOfIssueSB.append(polGenDateTemp);
			}
			hashAnnexure.put("dateOfIssueSB"+i,dateOfIssueSB.toString());

			TransportNameTemp = isNull((String)tempHash.get("TransportName"),"");

			SailingDateTemp = isNull((String)tempHash.get("sailDate"),"");
			VesselNameTemp = isNull((String)tempHash.get("VesselName"),"");
			CarrierNameTemp = isNull((String)tempHash.get("CarrierName"),"");
			if("NO VESSEL NAME PROVIDED".equalsIgnoreCase(VesselNameTemp))
			{
				VesselNameTemp="";
			}
			vesselSailingSB.append(LINEBREAK+TransportNameTemp+NEWLINE);
			if(VesselNameTemp.equalsIgnoreCase(SailingDateTemp))
			{
				if(VesselNameTemp.equalsIgnoreCase(CarrierNameTemp)){
					vesselSailingSB.append(VesselNameTemp+NEWLINE);
				}else
				{
					vesselSailingSB.append(VesselNameTemp+NEWLINE);
					vesselSailingSB.append(CarrierNameTemp+LINEBREAK);
				}

			}
			else if(VesselNameTemp.equalsIgnoreCase(CarrierNameTemp))
			{
				vesselSailingSB.append(VesselNameTemp);vesselSailingSB.append(NEWLINE);
				vesselSailingSB.append(SailingDateTemp);vesselSailingSB.append(LINEBREAK);
			}
			else
			{
				vesselSailingSB.append(VesselNameTemp);vesselSailingSB.append(NEWLINE);
				vesselSailingSB.append(SailingDateTemp);vesselSailingSB.append(LINEBREAK);
				vesselSailingSB.append(CarrierNameTemp);vesselSailingSB.append(LINEBREAK);
			}
			hashAnnexure.put("modeVesselSailingSB"+i,vesselSailingSB.toString());
			hashAnnexure.put("interestsSB"+i,interestsSB.toString());
			CurrencyNameTemp = isNull((String)tempHash.get("CurrencyName"),"");
			ExchangeRateTemp = isNull((String)tempHash.get("ExchangeRate"),"0");
			SaleTermValueTemp = isNull((String)tempHash.get("SaleTermValue"),"0");
			ExcessPremiumTemp = isNull((String)tempHash.get("ExcessPremium"),"0");
			payableAmountT = isNull((String)amtDetailsTemp.get("FinalPayableAmount"),"0");
			payableAmountT = Double.toString(Double.parseDouble(payableAmountT)+(Double.parseDouble(ExcessPremiumTemp)));
			payableAmtRoundT=payableAmountT;
			try
			{
				multiTotalPremium=Double.parseDouble(payableAmountT)+multiTotalPremium;
			}catch(Exception exx)
				{
					logger.info("the Exception Occured is multiTotalPremium "+exx.toString());
					logger.debug(exx);
				}
			premiumDetailsSB.append(NEWLINE);
			premiumDetailsSB.append(payableAmtRoundT);
	 		hashAnnexure.put("premiumDetailsSB"+i,premiumDetailsSB.toString());
			QuoteNoTemp=(String)tempHash.get(QUOTENO)==null?"":(String)tempHash.get(QUOTENO);//[0][26]);
			PolicyNoTemp = isNull((String)tempHash.get("PolicyNo"),"");
			openCoverNo = isNull((String)tempHash.get("openCoverNo"),"");
			creatorBean.setOpenCoverNo(openCoverNo);
			openCoverMissiNo = isNull((String)tempHash.get("openCoverMississippiNo"),"");
			openCoverCustomer = isNull((String)tempHash.get("openCoverCustomer"),"");
			rsaSharedPercent = isNull((String)tempHash.get("openRsaSharedPercentage"),"0");
			insCompanines = (String[][])tempHash.get("openInsuranceCompanines");
			String tolDetailsTemp[][] = new String[0][0];
			if(QuoteNoTemp.length()>0)
			{
				declInvoiceNosSB.append(QuoteNoTemp+LINEBREAK);
				tolDetailsTemp = finalBean.getToleranceDetails(QuoteNoTemp,"QUOTENO",brokerBra);
			}
			else if(PolicyNoTemp.length()>0)
			{
				declInvoiceNosSB.append(PolicyNoTemp+LINEBREAK);
				tolDetailsTemp = finalBean.getToleranceDetails(QuoteNoTemp,POLICYNO,brokerBra);
			}
			certificateNo=(String)tempHash.get("CertificateNo")==null?"":(String)tempHash.get("CertificateNo");
			if(certificateNo.length()>0){
				declInvoiceNosSB.append(certificateNo+NEWLINE);
			}
			CarrierNameTemp = isNull((String)tempHash.get("CarrierName"),"");
			BlAwbNoTemp = isNull((String)tempHash.get("BlAwbNo"),"");
			BlAwbDateTemp = isNull((String)tempHash.get("BlAwbDate"),"");
			if(BlAwbNoTemp.length()>0||BlAwbDateTemp.length()>0){
				BLCdetailsSB.append(LINEBREAK+BlAwbNoTemp+","+BlAwbDateTemp+LINEBREAK);
			}
			LcNumberTemp = isNull((String)tempHash.get("LcNumber"),"");
			LcDateTemp = isNull((String)tempHash.get("LcDate"),"");
			if("0".equalsIgnoreCase(LcNumberTemp) || NONE.equalsIgnoreCase(LcNumberTemp))
			{
				LcNumberTemp="";
				LcDateTemp="";
			}
			if(LcNumberTemp.length()>0||LcDateTemp.length()>0){
				BLCdetailsSB.append(LcNumberTemp+","+LcDateTemp+LINEBREAK);
			}
			BankName = isNull((String)tempHash.get("BankName"),"");
			if(NONE.equalsIgnoreCase(BankName))
			{
				BankName="";	
			}
			BLCdetailsSB.append(BankName+LINEBREAK);
			hashAnnexure.put("BLCdetailsSB"+i,BLCdetailsSB.toString());
			finalCountTemp=Integer.parseInt((String)commoDetailsT.get("finalCount")==null?"0":(String)commoDetailsT.get("finalCount"));
			CommSumInsTemp = new String[finalCountTemp];
			PkgDescription=new String[finalCountTemp];
			commoNameTemp=new String[finalCountTemp];
			commoDesTemp=new String[finalCountTemp];
			SaleTermName=new String[finalCountTemp];
			rate_arrTemp=new String[finalCountTemp];
			seaValue_arrTemp=new String[finalCountTemp];
			wareHouseTemp=new String[finalCountTemp];
			warRate_arrTemp=new String[finalCountTemp];

			rag_arrTemp=new String[finalCountTemp];

			Supplier_arrTemp=new String[finalCountTemp];
			Invoice_arrTemp=new String[finalCountTemp];
			suminsLocalTemp=new String[finalCountTemp];
			commoSumRoundT=new String[finalCountTemp];
			sumLocalRoundT=new String[finalCountTemp];
			for(int ii=0;ii<finalCountTemp;ii++)
			{
				CommSumInsTemp[ii] = isNull((String)commoDetailsT.get("CommoditySumInsured"+(ii+1)),"0");
				commoDesTemp[ii] = isNull((String)commoDetailsT.get("CommodityDescription"+(ii+1)),"");
				PkgDescription[ii] = isNull((String)commoDetailsT.get("PackageDescription_arr"+(ii+1)),"");
				commoNameTemp[ii]=isNull((String)commoDetailsT.get("CommodityName_arr"+(ii+1)),"");
				SaleTermName[ii] = isNull((String)commoDetailsT.get("SaleTermName_arr"+(ii+1)),"");
				rate_arrTemp[ii]=(String)commoDetailsT.get("rate"+(ii+1))==null?"0":(String)commoDetailsT.get("rate"+(ii+1));//[0][20]);
				seaValue_arrTemp[ii]=(String)commoDetailsT.get("seaValue"+(ii+1))==null?"0":(String)commoDetailsT.get("seaValue"+(ii+1));//[0][20]);
				wareHouseTemp[ii]=(String)commoDetailsT.get("wareHouseValue"+(ii+1))==null?"0":(String)commoDetailsT.get("wareHouseValue"+(ii+1));//[0][20]);
				warRate_arrTemp[ii]=(String)commoDetailsT.get("warRate"+(ii+1))==null?"0":(String)commoDetailsT.get("warRate"+(ii+1));//[0][20]);
				rag_arrTemp[ii]=(String)commoDetailsT.get("rag"+(ii+1))==null?"":(String)commoDetailsT.get("rag"+(ii+1));//[0][20]);
				packingDetailsSB.append(LINEBREAK+rag_arrTemp[ii]+LINEBREAK);
				packingDetailsSB.append(PkgDescription[ii]);
				packingDetailsSB.append(LINEBREAK);
				rateDetailsSB.append(LINEBREAK+(Double.parseDouble(rate_arrTemp[ii])+Double.parseDouble(seaValue_arrTemp[ii])+Double.parseDouble(wareHouseTemp[ii]))+LINEBREAK);
				rateDetailsSB.append(warRate_arrTemp[ii]);
				rateDetailsSB.append("\n\n\n");
				marineOthersSB.append("\nMarine\nWSRCC\n\nOthers\n");
				Supplier_arrTemp[ii]=isNull((String)commoDetailsT.get("Supplier_arr"+(ii+1)),"");
				packingDetailsSB.append(Supplier_arrTemp[ii]);
				packingDetailsSB.append(LINEBREAK);
				Invoice_arrTemp[ii] = isNull((String)commoDetailsT.get("Invoice_arr"+(ii+1)),"");
				if(Invoice_arrTemp[ii].length()>0){
					declInvoiceNosSB.append(Invoice_arrTemp[ii]);declInvoiceNosSB.append(LINEBREAK);
				}
				suminsLocalTemp[ii] = isNull((String)commoDetailsT.get("SumInsuredLocal_arr"+(ii+1)),"0");

				double salePercentTemp=0.0;
				double sumLocalTemp=0.0;
				double sumLocalSaleTemp=0.0;
				double commoSaleTemp;
				double toleranceval = 0.0;
				String toleranceName="";
				if(tolDetailsTemp.length>0)
				{
					toleranceName = isNull(tolDetailsTemp[ii][0],"");
					toleranceval = Double.parseDouble(isNull(tolDetailsTemp[ii][1],"0"));
				}
				salePercentTemp=Double.parseDouble(SaleTermValueTemp);
				sumLocalTemp=Double.parseDouble(suminsLocalTemp[ii]);
				sumLocalSaleTemp=sumLocalTemp+(sumLocalTemp*(salePercentTemp/100));
				sumLocalSaleTemp = sumLocalSaleTemp + toleranceval;
				commoSaleTemp=Double.parseDouble(CommSumInsTemp[ii]);
				sumLocalRoundT[ii]=pdfFormat.decimalFormat(sumLocalSaleTemp,decimalDigit);
				commoSumRoundT[ii]=pdfFormat.decimalFormat(commoSaleTemp,decimalDigit);
				sumInsDetailsSB.append(LINEBREAK+sumLocalRoundT[ii]+LINEBREAK);
				mulTotalSumInsurL=(sumLocalSaleTemp)+mulTotalSumInsurL;

				if(toleranceName.length()>0){
					sumInsDetailsSB.append(SaleTermName[ii]+LINEBREAK+toleranceName+LINEBREAK);
				}else{
					sumInsDetailsSB.append(SaleTermName[ii]);sumInsDetailsSB.append(LINEBREAK);
				}
				sumInsDetailsSB.append(CurrencyNameTemp+" "+commoSumRoundT[ii]+LINEBREAK);
				sumInsDetailsSB.append(ExchangeRateTemp);sumInsDetailsSB.append(LINEBREAK);
		
			}
			hashAnnexure.put("rateDetailsSB"+i,rateDetailsSB.toString());
			hashAnnexure.put("marineOthersSB"+i,marineOthersSB.toString());
			hashAnnexure.put("declCertiInvoiceNosSB"+i,declInvoiceNosSB.toString());
			hashAnnexure.put("packingDetailsSB"+i,packingDetailsSB.toString());
			hashAnnexure.put("sumInsuredDetailsSB"+i,sumInsDetailsSB.toString());
			hashAnnexure.put(INFO+i,"0");
		}else
		{
			checkProperCount=checkProperCount+1;
			hashAnnexure.put(INFO+i,"SOME INFORMATIONS MISSING FOR LINKED QUOTE NO\t"+tempQuoteNo);
		}
		rowCount=rowCount+1;
	}
		hashAnnexure.put("multiTotalPremium",pdfFormat.decimalFormat(multiTotalPremium,decimalDigit));
		hashAnnexure.put("multiTotalSumInsuredLocal",pdfFormat.decimalFormat(mulTotalSumInsurL,decimalDigit));
		hashAnnexure.put("rowCount",Integer.toString(rowCount));
	}else
	{
			if(NORMALSUP.equalsIgnoreCase(displayMode))
			{
				PolicyNo = isNull((String)premiumdetails.get("PolicyNo"),"0");
				logger.info("under NORMAL SUPPLEMENT IS PolicyNo"+PolicyNo);
				displayModeDummy=NORMALSUP;
				displayMode=NORMAL;
			}
			commoditydetails=(HashMap)premiumdetails.get("commoditydetails");
			AmountDetails=(HashMap)premiumdetails.get("AmountDetails");
			PdfKnownText = isNull((String)premiumdetails.get("PdfSubjectKnownText"),NOTHING);
			TransitFrom = isNull((String)premiumdetails.get("TransitFrom"),"");
			FinalDestination = isNull((String)premiumdetails.get("FinalDestination"),"");
			PolicyDate = isNull((String)premiumdetails.get("PolicyStartDate"),"");
			policyGenDate = isNull((String)premiumdetails.get("ActualPolicyGeneratedDate"),"");
			quoteGenDate = isNull((String) premiumdetails.get("QuoteGeneratedDate"),"");
			approvedBy = isNull((String)premiumdetails.get("APPROVEDBY"),"");
			certificateDate = isNull((String)premiumdetails.get("CertificateDate"),"");
			CoverName = isNull((String)premiumdetails.get("CoverName"),"");
			extraCoverId = isNull((String)premiumdetails.get("ExtraCoverId"),"");
			
			TransportName = isNull((String)premiumdetails.get("TransportName"),"");
			CurrencyName = isNull((String)premiumdetails.get("CurrencyName"),"");
			ExchangeRate = isNull((String)premiumdetails.get("ExchangeRate"),"0");
			SaleTermValue = isNull((String)premiumdetails.get("SaleTermValue"),"0");
			QuoteNo = isNull((String)premiumdetails.get(QUOTENO),"");
			PolicyNo = isNull((String)premiumdetails.get("PolicyNo"),"");
			openCoverNo = isNull((String)premiumdetails.get("openCoverNo"),"");
			creatorBean.setOpenCoverNo(openCoverNo);
			openCoverMissiNo = isNull((String)premiumdetails.get("openCoverMississippiNo"),"");
			openCoverCustomer = isNull((String)premiumdetails.get("openCoverCustomer"),"");
			CarrierName = isNull((String)premiumdetails.get("CarrierName"),"");
			BlAwbNo = isNull((String)premiumdetails.get("BlAwbNo"),"");
			BlAwbDate = isNull((String)premiumdetails.get("BlAwbDate"),"");
			LcNumber = isNull((String)premiumdetails.get("LcNumber"),"");
			LcDate = isNull((String)premiumdetails.get("LcDate"),"");
			if("0".equalsIgnoreCase(LcNumber) || NONE.equalsIgnoreCase(LcNumber))
			{
				LcNumber="";
				LcDate="";
			}
			BankName=(String)premiumdetails.get("BankName")==null?"":(String)premiumdetails.get("BankName");//[0][35]);
			if(NONE.equalsIgnoreCase(BankName))
			{
				BankName="";	
			}
			SailingDate = (String)premiumdetails.get("sailDate")==null?"":(String)premiumdetails.get("sailDate");
			SetAgentNameO = isNull((String)premiumdetails.get("SettlingAgentNameOthers"),"");
			SettlingAgentId=(String)premiumdetails.get("SettlingAgentId");//[0][37]);
			SetAgentName = isNull((String)premiumdetails.get("SettlingAgentName"),"");
			setAgentAddress1 = isNull((String)premiumdetails.get("SettlingAgentAddressOne"),"");
			setAgentAddress2 = isNull((String)premiumdetails.get("SettlingAgentAddressTwo"),"");
			SetAgentTelPh = isNull((String)premiumdetails.get("SettlingAgentTelephoneNo"),"");
			setAgentFaxNo = isNull((String)premiumdetails.get("SettlingAgentFaxNo"),"");
			CustomerFirstName = isNull((String)premiumdetails.get("CustomerFirstName"),"");
			CustomerLastName = isNull((String)premiumdetails.get("CustomerLastName"),"");
			custAddress1 = isNull((String)premiumdetails.get("CustomerAddressOne"),"");
			CustomerCountry = isNull((String)premiumdetails.get("CustomerCountry"),"");
			CustomerEmirate = isNull((String)premiumdetails.get("CustomerEmirate"),"");
			CustomerPoBox = isNull((String)premiumdetails.get("CustomerPoBox"),"");
			cusCompanyName = isNull((String)premiumdetails.get("CustomerCompanyName"),"");
			BrokerName = isNull((String)premiumdetails.get("BrokerName"),"");
			if(issuerName.length()>0){
				BrokerName = issuerName;
			}
			BrokerCommission = isNull((String)premiumdetails.get("BrokerCommission"),"0");
			LoginID = isNull((String)premiumdetails.get("LoginID"),"");
			BrokerRemarks = isNull((String)premiumdetails.get("BrokerRemarks"),"");
			BrokerCompany = isNull((String)premiumdetails.get("BrokerCompany"),"");
			EditClauses=(String[][])premiumdetails.get("editedClauses");
			EditExtraClauses=(String[][])premiumdetails.get("editedExtraClauses");
			EditWarClauses=(String[][])premiumdetails.get("editedWarClauses");
			EditExClauses=(String[][])premiumdetails.get("editedExClauses");
			rsaSharedPercent = isNull((String)premiumdetails.get("openRsaSharedPercentage"),"0");
			openFreeText=(String[][])premiumdetails.get("openFreeText");
			seaFreeTextOpt=(String[][])premiumdetails.get("openSeaFreeTextOptions");
			insCompanines=(String[][])premiumdetails.get("openInsuranceCompanines");
			Premium = isNull((String)premiumdetails.get("Premium"),"0");
			ExcessPremium = isNull((String)premiumdetails.get("ExcessPremium"),"0");
			finalCount=Integer.parseInt((String)commoditydetails.get("finalCount"));
			transitStartPlace = isNull((String)premiumdetails.get("TransitStartingPlace"),"");
			finalStartPlace=isNull((String)premiumdetails.get("FinalStartingPlace"),"");
			AdminStatus=isNull((String)premiumdetails.get("Remarks"),"");
			AdminRemarks = isNull((String)premiumdetails.get("AdminRemarks"),"");
			BackDaysOption = isNull((String)premiumdetails.get("BackDaysOption"),"");
			commissionAmount = isNull((String)AmountDetails.get("CommissionAmount"),"0");
			commiAmtRound=pdfFormat.decimalFormat(Double.parseDouble(commissionAmount),decimalDigit);	
			finalPayAmounts = isNull((String)AmountDetails.get("FinalPayableAmount"),"0");
			finalPayAmounts=Double.toString(Double.parseDouble(finalPayAmounts)+(Double.parseDouble(ExcessPremium)));
			totalSumInsured=(String)commoditydetails.get("TotalCommoditySI")==null?"0":(String)commoditydetails.get("TotalCommoditySI");
			InsuredTotal_st=(String)commoditydetails.get("TotalSILocal")==null?"0":(String)commoditydetails.get("TotalSILocal");
			insTotalRound=pdfFormat.decimalFormat(Double.parseDouble(InsuredTotal_st),decimalDigit);
			totalSumRounds=pdfFormat.decimalFormat(Double.parseDouble(totalSumInsured),decimalDigit);	
			finalCountryName = isNull((String)premiumdetails.get("FinalDestinationCountryName"),"");
			transCountryName = isNull((String)premiumdetails.get("TransitCountryName"),"");
			commoditySumIns = new String[finalCount];
			pkgDescription=new String[finalCount];
			CommodityName_arr=new String[finalCount];
			comDescription=new String[finalCount];
			SaleTermName_arr=new String[finalCount];
			ExclusionId_arr=new String[finalCount];
			Supplier_arr=new String[finalCount];
			Invoice_arr=new String[finalCount];
			WarrantyId_arr=new String[finalCount];
			sumInsuredLocal=new String[finalCount];
			comSumInsRound=new String[finalCount];
			sumInsuredLocalR=new String[finalCount];
			 for(int i=0;i<finalCount;i++)
			{
				commoditySumIns[i]=isNull((String)commoditydetails.get("CommoditySumInsured"+(i+1)),"0");
				comDescription[i]= isNull((String)commoditydetails.get("CommodityDescription"+(i+1)),"");
				pkgDescription[i]= isNull((String)commoditydetails.get("PackageDescription_arr"+(i+1)),"");
				CommodityName_arr[i]= isNull((String)commoditydetails.get("CommodityName_arr"+(i+1)),"");
				SaleTermName_arr[i]= isNull((String)commoditydetails.get("SaleTermName_arr"+(i+1)),"");
				ExclusionId_arr[i]= isNull((String)commoditydetails.get("ExclusionId_arr"+(i+1)),"0");
				Supplier_arr[i]= isNull((String)commoditydetails.get("Supplier_arr"+(i+1)),"");
				Invoice_arr[i]= isNull((String)commoditydetails.get("Invoice_arr"+(i+1)),"");
				WarrantyId_arr[i]= isNull((String)commoditydetails.get("WarrantyId_arr"+(i+1)),"0");
				sumInsuredLocal[i] = isNull((String)commoditydetails.get("SumInsuredLocal_arr"+(i+1)),"0");
			
				double salePercent=0.0;
				double sumLocal=0.0;
				double sumLocalSale=0.0;
				double commoditySI=0.0;
				double commoditySISale=0.0;
				double toleranceval = 0.0;
				double tolValuePer = 0.0;
				if(tolDetails.length>0)
				{
					toleranceval = Double.parseDouble(isNull(tolDetails[i][1],"0"));
					tolValuePer = Double.parseDouble(isNull(tolDetails[0][2],"0"));
				}
				salePercent=Double.parseDouble(SaleTermValue);
				sumLocal=Double.parseDouble(sumInsuredLocal[i]);
				sumLocalSale=sumLocal+(sumLocal*(salePercent/100));
				sumLocalSale = sumLocalSale + toleranceval;
				commoditySI=Double.parseDouble(commoditySumIns[i]);
				commoditySISale=commoditySI+(commoditySI*(salePercent/100));
				commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
				sumInsuredLocalR[i]=pdfFormat.decimalFormat(sumLocalSale,decimalDigit);
				comSumInsRound[i]=pdfFormat.decimalFormat(commoditySISale,decimalDigit);
			}
			exclusions=(String[][])commoditydetails.get("exclusionsDesc");
			warranties=(String[][])commoditydetails.get("warrantyDesc");
			clauses=(String[][])commoditydetails.get("clausesDesc");
			extraClauses=(String[][])commoditydetails.get("extraClausesDesc");
		}
			if(DRFATMODE.equalsIgnoreCase(displayMode))
			{
				final String args1[] = {QuoteNo};	
				String sqlDraft;sqlDraft="update position_master set OPEN_COVER_INT_STATUS='"+"DRAFTED"+"'  where  quote_no=?";
				runner.multipleUpdation(sqlDraft,args1);	
			}
			checkFlag=false;
		}
		else
		{
			checkFlag=true;
		}
	}
	catch(Exception ex)
	{
			logger.info("exception in print4"+ex.toString());logger.debug(ex);
	}
	if(checkFlag)
	{
		response.sendRedirect("Copy of information Admin.jsp?pdfStatus=NODATAS");if(true)return;
	}
		if(!displayModeDummy.equalsIgnoreCase(NORMALSUP)&&!(DRFATMODE.equalsIgnoreCase(displayMode)) && 
				!(DRFATMODEMUL.equalsIgnoreCase(displayMode)) && !(CERTMODE.equalsIgnoreCase(displayMode)))
		{
				if(NORMALMUL.equalsIgnoreCase(displayMode)){
					finalBean.updateMultiCommission(BrokerCommission,openCoverNo,PolicyNo,"11",brokerBra,taxPercent);
					
				}else{
					finalBean.updateOpenCommission(openCoverNo,PolicyNo,"11",brokerBra,BrokerCommission,taxPercent);
				}
		}
			//DB operation and assigning modification block end here
			fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
			creatorBean.setFontPath(fontPath);
			try
			{
				pageWater=new rsa.opencoverpdf.PageNumbersWatermarkNew();
				String urlPath;
				urlPath=request.getSession().getServletContext().getRealPath("/"+IMG+headImage);
				creatorBean.setImagePath(urlPath);
				pageWater.setImagePath(urlPath);
				String urlPathFooter;
				urlPathFooter=request.getSession().getServletContext().getRealPath("/"+IMG+footImage);
				creatorBean.setFooterImagePath(urlPathFooter);
				creatorBean.setDisplayText(displayText);
				pageWater.setFooterImagePath(urlPathFooter);
//				if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
					pageWater.setDisplayText(displayText);						
				}else{
					pageWater.setDisplayText("");
				}
				pageWater.setDisplayMode(displayMode);
				pageWater.setCols(cols);
				pageWater.setFontPath(fontPath);

				//For KSA SIGN STAMP
				String polGenDate = "";
				String polGenTime = "";
				String stampPath = "";
				String signPath = "";
				if (!NODATE.equalsIgnoreCase(policyGenDate)) 
				{
					polGenDate = policyGenDate.substring(0,policyGenDate.indexOf(' '));
					polGenTime = policyGenDate.substring(policyGenDate.indexOf(' ')+1,policyGenDate.length());
				}
				stampPath = request.getSession().getServletContext().getRealPath("/" + IMG+ stampImage);
				signPath = request.getSession().getServletContext().getRealPath("/" + IMG+ signImage);
				
				
				polGenDate = polGenDate+" Time: "+polGenTime;
				pageWater.setUsrModeController(usrModeController);
				pageWater.setPolicyOn(polGenDate);
				pageWater.setPolicyAt(place);
				pageWater.setEntered(BrokerName);
				pageWater.setApproved(approvedBy);
				pageWater.setSignedImage(signPath);
				pageWater.setStampImage(stampPath);
				pageWater.setStampStatus(stampStatus);
				pageWater.setIssuerName(issuerName);
				pageWater.setBraAddress(braAddress);
				pageWater.setBrokerCompany(BrokerCompany);
				pageWater.setCid(cid);
			}
			catch(Exception e)
			{
				logger.info("the Exception Occured in HEADER IMAGE "+e.toString());
				logger.debug(e);
			}
        } 
		catch(Exception ex) 
		{
            logger.debug(ex);
        }
        
        *
        *
        *  Removed for Jasper Report By Aswin
        *
        *
        */
		
		
        response.setContentType("application/pdf");
       
        try
		{
			String polino="";
			String fileNamePath="",fileNamePath1="";
			
			if(DRFATMODE.equalsIgnoreCase(displayMode))
			{
				 polino = quoteDraftNo+"_Draft.pdf";
            }
			/** CERTIFICATE PDF START **/
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_certificate.pdf";
            }
			/** CERTIFICATE PDF END **/
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_DraftMultiple.pdf";
        	}
			else if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode))
			{
				polino = PolicyNoFour+"QuoteScheduleOpen.pdf";
			}
			else
			{
				polino = PolicyNoFour+"ScheduleOpen.pdf";
			}
			
			//Block Added by Chinna
			System.out.println("polino: >>>>>>> bef"+polino);
			if(polino!=null && polino.length()>0 && polino.indexOf("/")!=-1)
			{
				polino=polino.replaceAll("/", "_");
				System.out.println("polino: >>>>>>>"+polino);
			}
			
			if("ORIGINAL COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
				fileNamePath1="/OriginalCopyPdf/"+polino;
			}
			else if("COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
				fileNamePath1="/duplicatecopypdf/"+polino;
			}
			else if("DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
				fileNamePath1="/quotepdf/"+polino;
			}
			/** CERTIFICATE PDF START **/
			else if("CERTIFICATE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
				fileNamePath1="/certificatePDF/"+polino;
			}
			/** CERTIFICATE PDF END **/
			else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino);
				fileNamePath1="/multiplequotepdf/"+polino;
			}
			else if(INVPOLICY.equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
				fileNamePath1="/testpolicypdf/"+polino;
			}
			else if("INVALID DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
				fileNamePath1="/testquotepdf/"+polino;
			}else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino);
				fileNamePath1="/testmultiplequotepdf/"+polino;
			}
			else if ((displayText.trim()).length()<=0) 
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/" + polino);
				fileNamePath1="/OriginalPdf/"+polino;
			} 
			else
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
				fileNamePath1="/debitpdf/"+polino;
			}
			creatorBean.setFilePath(fileNamePath);
			logger.info("the X FILE NAME IS 123131231"+fileNamePath);
			File filePath;
			filePath=new File(fileNamePath);
			System.out.println("displayMode: "+displayMode);
			if(filePath.exists() && (NORMAL.equalsIgnoreCase(displayMode) || (NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)) || NORMALMUL.equalsIgnoreCase(displayMode)))
			{
				response.sendRedirect(request.getContextPath()+fileNamePath1);//need to check more while testing
			}
			else
			{
	            try
		        {
		      		logger.info("royaltest....open..displayText............"+displayText);
					if("ORIGINAL COPY".equalsIgnoreCase(displayText))
					{	
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
						fileNamePath1="/OriginalCopyPdf/"+polino;
					}
					else if("COPY".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
						fileNamePath1="/duplicatecopypdf/"+polino;
					}
					else if("DRAFT".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
						fileNamePath1="/quotepdf/"+polino;
					}
					/** CERTIFICATE PDF START **/
					else if("CERTIFICATE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
						fileNamePath1="/certificatePDF/"+polino;
					}
					/** CERTIFICATE PDF END **/
					else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino);
						fileNamePath1="/multiplequotepdf/"+polino;
					}
					else if(INVPOLICY.equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
						fileNamePath1="/testpolicypdf/"+polino;
					}
					else if("INVALID DRAFT".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
						fileNamePath1="/testquotepdf/"+polino;
					}
					else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino);
						fileNamePath1="/testmultiplequotepdf/"+polino;
					}
					else if(NORMALMUL.equalsIgnoreCase(displayMode))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/LiveOriginalMultiplePdf/"+polino);
						fileNamePath1="/LiveOriginalMultiplePdf/"+polino;
					}
					else if ((displayText.trim()).length()<=0) 
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/OriginalPdf/"+polino);
						fileNamePath1="/OriginalPdf/"+polino;
					} 
					else
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
						fileNamePath1="/OriginalPdf/"+polino;
					}
			}
			catch(Exception eb)
			{
				logger.info(eb); logger.debug(eb);
			}
			/*
			 * Removed for Jasper Report
			 * 
			 * Image paidStampImage=Image.getInstance(request.getSession().getServletContext().getRealPath("/" + IMG+ "Paid_Stamp.gif"));
			creatorBean.setPaidStampImage(paidStampImage);
			creatorBean.setVerNo(verNo);
			creatorBean.writePDF(brokerBra,cid,decimalDigit,freight,LoginID,(String)session.getAttribute("product_id"),PolicyNo);
			**
			*/
			
			String applicationNo = finalBean.getApplicationNo(draftNo);
			if(StringUtils.isBlank(applicationNo)){
				applicationNo = finalBean.getApplicationNo1(draftNo);
			}
			com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
			jr.certificateSchedule(applicationNo, brokerBra, belongingBranch, fileNamePath);
			
			//response.sendRedirect(request.getContextPath()+fileNamePath1);
			session.setAttribute("pdfFilePath", fileNamePath1);
			response.sendRedirect(request.getContextPath()+"/pdfReport.action");
        }
		}
        catch (Exception ex) 
		{
			logger.info("ROyal Test third catch.."+ex.toString());
            ex.printStackTrace();
			logger.debug(ex);
        } 
    }
	protected void processRequestGHQ(final HttpServletRequest request, final HttpServletResponse response)throws ServletException, IOException,BaseException 
	{
		Map premiumdetails;
		Map commoditydetails;
		HttpSession session=null;
		rsa.opencoverpdf.PageNumbersWatermarkNew pageWater=null;
		//All the Declaration and initialiation start from here
		String  certificateNo="",BrokerName="",PolicyNo="",CoverName="",extraCoverId="",CarrierName="",TransitFrom="",FinalDestination="",
		BlAwbNo="",BlAwbDate="",CoverIdTemp="",CoverIdSeaTemp="",ExtraCoverIdTemp="",issuerName="";
		
		String verNo = "";
		String BrokerCommission="";
		String PdfKnownText="";
		String Premium="";
		String ExcessPremium="";
		String BrokerRemarks="";
		String BrokerCompany="";
		String place = "";
		String freight="";
		int checkProperCount=0;
		String check="";
		String BankName="";
		String CustomerFirstName="",CustomerLastName="",custAddress1="",
		CustomerCountry="",CustomerEmirate="";
		double exchange=0;
		boolean checkFlag=false;
		String totalSumRounds="";
		String insTotalRound="";
		String PremiumYes="";
		String PolicyNoFour="";
		String AdminStatus="";
		String AdminRemarks="";
		String BackDaysOption="";
		double multiTotalPremium=0.0;
		double mulTotalSumInsurL=0.0;
		String tolDetails[][] = new String[0][0];
		String displayModeDummy="";
		String certificateDate = "";
		String policyGenDate="";
		String quoteGenDate="";
		String approvedBy="";
		String CustomerPoBox="";
		String cusCompanyName="";
		HashMap hashAnnexure=new HashMap();
		String finalDestMul="";
		String transFromMul="",SailingDate="",
		SailingDateTemp="",VesselNameTemp="",PolicyNoTemp;
		String openCoverNo="";
		String openCoverMissiNo="";
		String openCoverCustomer="";
		StringBuffer concatAll=new StringBuffer();
		String watermarkText=INVPOLICY;
		String totalSumInsured="";
		String InsuredTotal_st="";
		String LoginID="";
		String CommodityName_arr[]=new String[0];
		String comDescription[]=new String[0];
		String sumInsuredLocal[]=new String[0];
		String sumInsuredLocalR[]=new String[0];
		String comSumInsRound[]=new String[0];
		String pkgDescription[]=new String[0];
		
		String SetAgentName="",SetAgentNameO="",SettlingAgentId="",
		setAgentAddress1="",setAgentAddress2="",setAgentFaxNo="",
		SetAgentTelPh="",ExchangeRate="",CurrencyName="",currencyShortName="",
		TransportName="",QuoteNo="",SaleTermValue="";
		String rsaSharedPercent="";
		String bankerOption="";
		String bankerAssOpt="";
		String currencyOption="";
		String excessOption="";
		String viewStatus="";
		String displayText="";
		String displayMode="";
		String quoteDraftNo="";
		String braAddress = "";
		String braPO = "";
		String braCity = "";
		String braCountry = "";
		String braPhone = "";
		String braFax = "";
		String cid="";
		double taxPercent = 0;
		//All the Declaration and initialiation End from here
		premiumdetails=new HashMap();
		commoditydetails=new HashMap();
		String headImage = "";
		String footImage = "";
		String signImage = "";
		String stampImage = "";
		String stampStatus = "";
		String currencyType = "";
		String currencyType1 = "";
		String usrModeController="";
		String cusCode ="",createDate="";
		String brokerBra = "";
		int decimalDigit = 0;
		String website="";
		String fontPath ="";
		finalprint finalBean;
		finalBean = new finalprint();
		rsa.pdf.finalprint finalData;
		finalData = new rsa.pdf.finalprint();
		rsa.pdf.PDFDisplay pdfFormat;
		pdfFormat = new rsa.pdf.PDFDisplay();
		rsa.pdf.GHQPDFCreatorBean creatorBean = new rsa.pdf.GHQPDFCreatorBean();
		try
		{
			session=request.getSession(true);
			if(session.getAttribute("ses")==null)
			{
				response.sendRedirect("login/error_messg_pdf.jsp");	
				return;
			}
			usrModeController=(String)session.getAttribute("userLoginMode")==null?"":(String)session.getAttribute("userLoginMode");
			com.maan.DBCon.DBConnectionStatus.statusStatic=usrModeController;
			displayMode=request.getParameter("displayMode")==null?"":request.getParameter("displayMode");
			displayText=request.getParameter("displayText")==null?"":request.getParameter("displayText");
			if(TEST.equalsIgnoreCase(usrModeController)&&"".equalsIgnoreCase(displayText))
			{
				displayText=INVPOLICY;
			}
			brokerBra = (String)session.getAttribute("LoginBranchCode");
			creatorBean.setBrokerBra(brokerBra);
			HashMap brokerDetails;
			brokerDetails = (HashMap)session.getAttribute("BrokerDetails");
			
			if(!brokerDetails.isEmpty())
			{
				cid = (String)brokerDetails.get("Orgination");
				creatorBean.setDcid((String)brokerDetails.get("Destination"));
				decimalDigit = Integer.parseInt((String)brokerDetails.get("CurrencyDecimal"));
				taxPercent = Double.parseDouble((String)brokerDetails.get("Tax"));
				website = (String)brokerDetails.get("Site");
				creatorBean.setTaxPercent(taxPercent);
				creatorBean.setWebsite(website);
			}
			
			PolicyNo = request.getParameter("policynumber")==null?"0":request.getParameter("policynumber");
			verNo = request.getParameter("verNo")==null?"0":request.getParameter("verNo");
			quoteDraftNo=PolicyNo;
			LoginID=request.getParameter("loginid")==null?"0":request.getParameter("loginid");
			PremiumYes=request.getParameter("printoption")==null?"":request.getParameter("printoption");
			bankerOption=request.getParameter("bankerOption")==null?"":request.getParameter("bankerOption");
			bankerAssOpt = isNull(request.getParameter("bankerAssuredOption"),"");
			currencyOption=request.getParameter("currencyOption")==null?"":request.getParameter("currencyOption");
			excessOption=request.getParameter("excessOption")==null?"":request.getParameter("excessOption");
			viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");
			
			
			creatorBean.setCid(cid);
			creatorBean.setDecimalDigit(decimalDigit);
			creatorBean.setUsrModeController(usrModeController);
			creatorBean.setCurrencyOption(currencyOption);
			creatorBean.setBankerAssuredOption(bankerAssOpt);
			creatorBean.setBankerOption(bankerOption);
			creatorBean.setPremiumYes(PremiumYes);
			
			logger.info("======== LoginID is "+LoginID);
			logger.info("======== policynumber is "+PolicyNo);
			logger.info("======== PremiumYes is "+PremiumYes);
			logger.info("======== bankerOption is "+bankerOption);
			logger.info("======== currencyOption is "+currencyOption);
			logger.info("======== excessOption2 is "+excessOption);
			logger.info("======== bankerAssOpt is "+bankerAssOpt);
			logger.info("======== displayMode is "+displayMode);
			logger.info("======== viewStatus is "+viewStatus);
			logger.info("======== displayText is "+displayText);
			
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				certificateNo = finalBean.getCertificateNo(brokerBra,(String)session.getAttribute("product_id"),PolicyNo );
			}
			
			if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)){
				check = "home.QUOTE_NO='" + PolicyNo + "'";
			}
			String placeCode[][] = new String[0][0];
			String cols="";
			
			try
			{
				//if(PolicyNo.indexOf('-')==-1){
				if(finalBean.isNumeric(PolicyNo)){
					check = "home.quote_no in("+PolicyNo+")";
				}else{
					check = "home.policy_no='"+PolicyNo+"'";
				}
				//final String args[] = {brokerBra};
				if(check.length()>0){
					stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where "+check);
				}
				if(stampStatus.length()<=0)
				{
					String args1[] = new String[1];
					args1[0] = PolicyNo;
					stampStatus = runner.singleSelection("select distinct nvl(home.PDF_Stamp_Status,'N') from POSITION_MASTER home where home.quote_no in(select quote_no from position_master where policy_no=?)",args1);
				}
			}
			catch (Exception e)
			{
				logger.debug(e);
			}
			
			String rubberOption;
			rubberOption = request.getParameter("rubberOption")==null?"":request.getParameter("rubberOption");
			if(rubberOption.length()>0){
				stampStatus = rubberOption;
			}
			creatorBean.setStampStatus(stampStatus);
			creatorBean.setPolicyNo(PolicyNo);
			creatorBean.setDisplayMode(displayMode);
			
			PolicyNoFour = PolicyNo;
			
			try
			{
				logger.info("comes inside this draftMode DRAFT BLOCK");
				if(DRFATMODE.equalsIgnoreCase(displayMode))
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
				else
					placeCode = finalData.getPlaceCodes(PolicyNo,"Schedule","11",POLICYNO);
				if(placeCode.length>0)
				{
					creatorBean.setPlace(finalBean.isNull(placeCode[0][0], ""));
					headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
					footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
					signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
					stampImage = placeCode[0][4] == null ? "" : placeCode[0][4];
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
				}
			}
			catch(Exception ex)
			{
				logger.info("exception in print4"+ex.toString());logger.debug(ex);
			}
			if(checkFlag)
			{
				response.sendRedirect("Copy of information Admin.jsp?pdfStatus=NODATAS");if(true)return;
			}
			/*if(!displayModeDummy.equalsIgnoreCase(NORMALSUP)&&!(DRFATMODE.equalsIgnoreCase(displayMode)) && 
					!(DRFATMODEMUL.equalsIgnoreCase(displayMode)) && !(CERTMODE.equalsIgnoreCase(displayMode)))
			{
				if(NORMALMUL.equalsIgnoreCase(displayMode)){
					finalBean.updateMultiCommission(BrokerCommission,openCoverNo,PolicyNo,"11",brokerBra,taxPercent);
					
				}else{
					finalBean.updateOpenCommission(openCoverNo,PolicyNo,"11",brokerBra,BrokerCommission,taxPercent);
				}
			}*/
			//DB operation and assigning modification block end here
			fontPath = request.getSession().getServletContext().getRealPath("/" + "ScheduleFont/arial.ttf");
			creatorBean.setFontPath(fontPath);
			try
			{
				pageWater=new rsa.opencoverpdf.PageNumbersWatermarkNew();
				String urlPath;
				urlPath=request.getSession().getServletContext().getRealPath("/"+IMG+headImage);
				creatorBean.setImagePath(urlPath);
				pageWater.setImagePath(urlPath);
				String urlPathFooter;
				urlPathFooter=request.getSession().getServletContext().getRealPath("/"+IMG+footImage);
				creatorBean.setFooterImagePath(urlPathFooter);
				creatorBean.setDisplayText(displayText);
				pageWater.setFooterImagePath(urlPathFooter);
				pageWater.setDisplayMode(displayMode);
				pageWater.setCols(cols);
				pageWater.setFontPath(fontPath);
				
				//For KSA SIGN STAMP
				String polGenDate = "";
				String polGenTime = "";
				String stampPath = "";
				String signPath = "";
				/*if (!NODATE.equalsIgnoreCase(policyGenDate)) 
				{
					polGenDate = policyGenDate.substring(0,policyGenDate.indexOf(' '));
					polGenTime = policyGenDate.substring(policyGenDate.indexOf(' ')+1,policyGenDate.length());
				}*/
				stampPath = request.getSession().getServletContext().getRealPath("/" + IMG+ stampImage);
				signPath = request.getSession().getServletContext().getRealPath("/" + IMG+ signImage);
				
				
				polGenDate = polGenDate+" Time: "+polGenTime;
				pageWater.setUsrModeController(usrModeController);
				pageWater.setPolicyOn(polGenDate);
				pageWater.setPolicyAt(place);
				pageWater.setEntered(BrokerName);
				pageWater.setApproved(approvedBy);
				pageWater.setSignedImage(signPath);
				pageWater.setStampImage(stampPath);
				pageWater.setStampStatus(stampStatus);
				pageWater.setIssuerName(issuerName);
				pageWater.setBraAddress(braAddress);
				pageWater.setBrokerCompany(BrokerCompany);
				pageWater.setCid(cid);
			}
			catch(Exception e)
			{
				logger.info("the Exception Occured in HEADER IMAGE "+e.toString());
				logger.debug(e);
			}
		} 
		catch(Exception ex) 
		{
			logger.debug(ex);
		}
		response.setContentType("application/pdf");
		
		try
		{
			String polino="";
			String fileNamePath="",fileNamePath1="";
			
			if(DRFATMODE.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_Draft.pdf";
			}
			/** CERTIFICATE PDF START **/
			if(CERTMODE.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_certificate.pdf";
			}
			/** CERTIFICATE PDF END **/
			if(DRFATMODEMUL.equalsIgnoreCase(displayMode))
			{
				polino = quoteDraftNo+"_DraftMultiple.pdf";
			}
			else if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode))
			{
				polino = PolicyNoFour+"QuoteScheduleOpen.pdf";
			}
			else
			{
				polino = PolicyNoFour+"ScheduleOpen.pdf";
			}
			
			//Block Added by Chinna
			System.out.println("polino: >>>>>>> bef"+polino);
			if(polino!=null && polino.length()>0 && polino.indexOf("/")!=-1)
			{
				polino=polino.replaceAll("/", "_");
				System.out.println("polino: >>>>>>>"+polino);
			}
			
			if("ORIGINAL COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
				fileNamePath1="/OriginalCopyPdf/"+polino;
			}
			else if("COPY".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
				fileNamePath1="/duplicatecopypdf/"+polino;
			}
			else if("DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
				fileNamePath1="/quotepdf/"+polino;
			}
			/** CERTIFICATE PDF START **/
			else if("CERTIFICATE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
				fileNamePath1="/certificatePDF/"+polino;
			}
			/** CERTIFICATE PDF END **/
			else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino);
				fileNamePath1="/multiplequotepdf/"+polino;
			}
			else if(INVPOLICY.equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
				fileNamePath1="/testpolicypdf/"+polino;
			}
			else if("INVALID DRAFT".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
				fileNamePath1="/testquotepdf/"+polino;
			}else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino);
				fileNamePath1="/testmultiplequotepdf/"+polino;
			}
			else if ((displayText.trim()).length()<=0) 
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/" + "/OriginalPdf/" + polino);
				fileNamePath1="/OriginalPdf/"+polino;
			} 
			else
			{
				fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
				fileNamePath1="/debitpdf/"+polino;
			}
			creatorBean.setFilePath(fileNamePath);
			logger.info("the X FILE NAME IS 123131231"+fileNamePath);
			File filePath;
			filePath=new File(fileNamePath);
			System.out.println("displayMode: "+displayMode);
			if(filePath.exists() && (NORMAL.equalsIgnoreCase(displayMode) || (NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode)) || NORMALMUL.equalsIgnoreCase(displayMode)))
			{
				response.sendRedirect(request.getContextPath()+fileNamePath1);//need to check more while testing
			}
			else
			{
				try
				{
					logger.info("royaltest....open..displayText............"+displayText);
					if("ORIGINAL COPY".equalsIgnoreCase(displayText))
					{	
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/OriginalCopyPdf/"+polino);
						fileNamePath1="/OriginalCopyPdf/"+polino;
					}
					else if("COPY".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
						fileNamePath1="/duplicatecopypdf/"+polino;
					}
					else if("DRAFT".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
						fileNamePath1="/quotepdf/"+polino;
					}
					/** CERTIFICATE PDF START **/
					else if("CERTIFICATE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
						fileNamePath1="/certificatePDF/"+polino;
					}
					/** CERTIFICATE PDF END **/
					else if("DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/multiplequotepdf/"+polino);
						fileNamePath1="/multiplequotepdf/"+polino;
					}
					else if(INVPOLICY.equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testpolicypdf/"+polino);
						fileNamePath1="/testpolicypdf/"+polino;
					}
					else if("INVALID DRAFT".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testquotepdf/"+polino);
						fileNamePath1="/testquotepdf/"+polino;
					}
					else if("INVALID DRAFT MULTIPLE".equalsIgnoreCase(displayText))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/testmultiplequotepdf/"+polino);
						fileNamePath1="/testmultiplequotepdf/"+polino;
					}
					else if(NORMALMUL.equalsIgnoreCase(displayMode))
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/LiveOriginalMultiplePdf/"+polino);
						fileNamePath1="/LiveOriginalMultiplePdf/"+polino;
					}
					else if ((displayText.trim()).length()<=0) 
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/OriginalPdf/"+polino);
						fileNamePath1="/OriginalPdf/"+polino;
					} 
					else
					{
						fileNamePath=request.getSession().getServletContext().getRealPath("/"+"/debitpdf/"+polino);
						fileNamePath1="/OriginalPdf/"+polino;
					}
				}
				catch(Exception eb)
				{
					logger.info(eb); logger.debug(eb);
				}
				Image paidStampImage=Image.getInstance(request.getSession().getServletContext().getRealPath("/" + IMG+ "Paid_Stamp.gif"));
				creatorBean.setPaidStampImage(paidStampImage);
				creatorBean.setVerNo(verNo);
				creatorBean.writePDF(brokerBra,cid,decimalDigit,freight,LoginID,(String)session.getAttribute("product_id"),PolicyNo);
				response.sendRedirect(request.getContextPath()+fileNamePath1);
			}
		}
		catch (Exception ex) 
		{
			logger.info("ROyal Test third catch.."+ex.toString());
			ex.printStackTrace();
			logger.debug(ex);
		} 
	}
}
