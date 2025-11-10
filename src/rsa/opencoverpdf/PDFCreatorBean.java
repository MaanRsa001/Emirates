package rsa.opencoverpdf;

//import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
import java.text.NumberFormat;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

//import javax.servlet.http.HttpSession;

import rsa.pdf.PdfPTableCreation;

//import com.lowagie.text.BadElementException;
//import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
//import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
//import com.lowagie.text.pdf.BaseFont;
//import com.lowagie.text.pdf.CMYKColor;
//import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;


public class PDFCreatorBean
{
	private static final Logger logger = LogUtil.getLogger(PDFCreatorBean.class);
	private String QuoteNo = "";
	private transient String urlPath = "";
	private transient String urlPathFooter = "";
	private String loginId = "";
	private String filePath = "";
	private String signedImagePath = "";
	private String fontPath = "";
	private String userModeSC = "";
	private String branch = "";
	private String currencyType = "";
	private String disPremium = "";
	private int decimalDigit = 0;
	
	/*private String cid;
	private String usrModeController;
	private String currencyOption;
	private String bankerAssOption;
	private String bankerOption;
	private String premiumYes;*/
	
	//final static transient private String INVPOLICY = "INVALID POLICY";
	
	public void setLoginId(final String loginId) {
		this.loginId = loginId;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setQuoteNo(final String QuoteNo) {
		this.QuoteNo = QuoteNo;
	}

	public String getQuoteNo() {
		return this.QuoteNo;
	}

	public void setUserModeSC(final String usrModeSC) {
		this.userModeSC = usrModeSC;
	}

	public String getUserModeSC() {
		return this.userModeSC;
	}

	public String getHeaderImagePath() {
		return urlPath;
	}

	public void setHeaderImagePath(final String urlPath) {
		this.urlPath = urlPath;
	}

	public String getFooterImagePath() {
		return urlPathFooter;
	}

	public void setFooterImagePath(final String urlPathFooter) {
		this.urlPathFooter = urlPathFooter;
	}

	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setSignedImagePath(final String signedImagePath) {
		this.signedImagePath = signedImagePath;
	}

	public String getSignedImagePath() {
		return this.signedImagePath;
	}

	public void setBranch(final String branch) {
		this.branch = branch;
	}

	public String getBranch() {
		return this.branch;
	}

	public void setCurrencyType(final String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setDecimalDigit(final int decimalDigit) {
		this.decimalDigit = decimalDigit;
	}

	public int getDecimalDigit() {
		return this.decimalDigit;
	}

	public void setDisPremium(final String disPremium) {
		this.disPremium = disPremium;
	}

	public String getDisPremium() {
		return this.disPremium;
	}
/*public void write4SchedulePDF()
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
		viewStatus=request.getParameter("viewStatus")==null?"":request.getParameter("viewStatus");
			
		logger.info("======== LoginID is "+LoginID);
		logger.info("======== policynumber is "+PolicyNo);
		logger.info("======== PremiumYes is "+PremiumYes);
		logger.info("======== bankerOption is "+bankerOption);
		logger.info("======== currencyOption is "+currencyOption);
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
		StringTokenizer token = null;
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
		}
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

		SailingDateTemp = isNull((String)tempHash.get("SailingDate"),"");
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
		SailingDate = (String)premiumdetails.get("SailingDate")==null?"":(String)premiumdetails.get("SailingDate");
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
		try
		{
			pageWater=new rsa.opencoverpdf.PageNumbersWatermarkNew();
			String urlPath;urlPath=request.getSession().getServletContext().getRealPath("/"+IMG+headImage);
			pageWater.setImagePath(urlPath);
			String urlPathFooter;urlPathFooter=request.getSession().getServletContext().getRealPath("/"+IMG+footImage);
			pageWater.setFooterImagePath(urlPathFooter);
			if("13".equalsIgnoreCase(brokerBra.trim()) || "DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
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
    response.setContentType("application/pdf");
   
    document = new Document(PageSize.A4, 50, 50, 90, 60);
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
		*//** CERTIFICATE PDF START **//*
		if(CERTMODE.equalsIgnoreCase(displayMode))
		{
			polino = quoteDraftNo+"_certificate.pdf";
        }
		*//** CERTIFICATE PDF END **//*
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
		}
		else if("COPY".equalsIgnoreCase(displayText))
		{
			fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/duplicatecopypdf/"+polino);
		}
		else if("DRAFT".equalsIgnoreCase(displayText))
		{
			fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/quotepdf/"+polino);
		}
		*//** CERTIFICATE PDF START **//*
		else if("CERTIFICATE".equalsIgnoreCase(displayText))
		{
			fileNamePath = request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino);
		}
		*//** CERTIFICATE PDF END **//*
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
				*//** CERTIFICATE PDF START **//*
				else if("CERTIFICATE".equalsIgnoreCase(displayText))
				{
					writer = PdfWriter.getInstance(document, new FileOutputStream(request.getSession().getServletContext().getRealPath("/"+"/certificatePDF/"+polino)));
					
				}
				*//** CERTIFICATE PDF END **//*
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
		BaseFont arialFont;
		arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
		float fontSize=0.0f;
		fontHead3=new Font(Font.TIMES_ROMAN,8+fontSize,Font.NORMAL);
        fontHead2=new Font(Font.TIMES_ROMAN,9+fontSize,Font.BOLD);
        fontHeadSP=new Font(Font.TIMES_ROMAN,12+fontSize,Font.BOLD);
        fontHead1=new Font(Font.TIMES_ROMAN,9+fontSize,Font.NORMAL);
        fontHeadUl =new Font(Font.TIMES_ROMAN,9,Font.BOLD);
        fontHeadUl.setStyle(Font.UNDERLINE);
        font = new Font(Font.TIMES_ROMAN,9,Font.NORMAL);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			if (SAUDI.equalsIgnoreCase(cid)||BHARAIN.equalsIgnoreCase(cid)){
				fontText=new Font(arialFont,8+fontSize,Font.NORMAL);
			}else{
				fontText=new Font(arialFont,7+fontSize,Font.NORMAL);
			}
		}
		else{
			fontText=new Font(arialFont,7+fontSize,Font.NORMAL);
		}
		fontText1=new Font(arialFont,7+fontSize,Font.BOLD);
		///////// Table 1 only for schedule ///////
		tableCreation.setTable(8);
		//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		//tableCreation.insertCell(Rectangle.NO_BORDER, 8);
		if(!(DRFATMODE.equalsIgnoreCase(displayMode) || DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell("CERTIFICATE OF MARINE INSURANCE (CARGO)", fontHeadSP, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("We, The "+braAddress.toUpperCase()+", hereby agree, in consideration of payment to us by or on behalf of" +
					" the Assured of the premium specified in the schedule, " +
					"to insure against loss, damage, liability or expenses in proportions & manner hereinafter provided.", fontHead1, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
		}
		else if(NORMALSUP.equalsIgnoreCase(displayModeDummy) && NORMAL.equalsIgnoreCase(displayMode))
		{
			tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,3);
			tableCreation.insertCell("THE SCHEDULE (Supplement Copy for quote  no: "+QuoteNo+")", fontHeadSP, Rectangle.NO_BORDER,8, 2);
			tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,3);
		}else
		{
			tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,2);
			tableCreation.insertCell("CERTIFICATE OF MARINE INSURANCE (CARGO)", fontHeadSP, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
			tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,3);
		}
		//tableCreation.setTableSpacing(3f); // 5F Second Page issue May 20
		document.add(tableCreation.getTable());
		tableCreation.setTable(8);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 2);
		tableCreation.insertCellPad("Open Cover No", fontHead2, Rectangle.NO_BORDER,2, 0);
		tableCreation.insertCell(" : "+openCoverMissiNo, fontHead2, Rectangle.NO_BORDER,2, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
		if((DRFATMODE.equalsIgnoreCase(displayMode) || DRFATMODEMUL.equalsIgnoreCase(displayMode)) && (TEST.equalsIgnoreCase(usrModeController)))
		{
			tableCreation.insertCellPad("Quote No", fontHead2, Rectangle.NO_BORDER,2, 0);
		}
		else
		{
			if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
			{
				tableCreation.insertCellPad("Bordereaux No", fontHead2, Rectangle.NO_BORDER,2, 0);
			}
			else
			{
				tableCreation.insertCellPad("Certificate No", fontHead2, Rectangle.NO_BORDER,2, 0);
			}
		}
		logger.info("royal pdf test for multiple PolicyNo..."+PolicyNo);
		logger.info("royal pdf test for multiple QuoteNo..."+QuoteNo);
		if((DRFATMODE.equalsIgnoreCase(displayMode) || DRFATMODEMUL.equalsIgnoreCase(displayMode)) && (TEST.equalsIgnoreCase(usrModeController)))
		{
			String quotes;quotes = isNull(request.getParameter("multipleQuotes"),"");
			if(NORMALMUL.equalsIgnoreCase(displayMode) || (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
			{
				tableCreation.insertCell(" : "+(quotes.length()<=0?PolicyNo:quotes), fontHead2, Rectangle.NO_BORDER,2, 0);
			}			
			else
			{
				tableCreation.insertCell(" : "+QuoteNo, fontHead2, Rectangle.NO_BORDER,2, 0);
			}
		}
		else
		{
			if(NORMALSUP.equalsIgnoreCase(displayModeDummy)){
				tableCreation.insertCell(" : "+PolicyNo+"/"+verNo, fontHead2, Rectangle.NO_BORDER,2, 0);
			}else if(CERTMODE.equalsIgnoreCase(displayMode)){
				tableCreation.insertCell(" : "+certificateNo, fontHead2, Rectangle.NO_BORDER,2, 0);
			}else{
				tableCreation.insertCell(" : "+(PolicyNo.length()<=0?QuoteNo:PolicyNo), fontHead2, Rectangle.NO_BORDER,2, 0);
			}
		}
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
		
		tableCreation.insertCellPad("Customer Code", fontHead2, Rectangle.NO_BORDER,2, 0);
		tableCreation.insertCell(" : "+cusCode, fontHead1, Rectangle.NO_BORDER,2, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
		
		tableCreation.insertCellPad("Name of Insured(s)", fontHead2, Rectangle.NO_BORDER,2, 0);
		logger.info("BankName BEFORE "+BankName);
		logger.info("@@@@openCoverCustomer BEFORE"+openCoverCustomer);
		logger.info("displayMode BEFORE"+displayMode);
		logger.info("cusCompanyName BEFORE"+cusCompanyName);
		logger.info("CustomerLastName BEFORE"+CustomerLastName);
		logger.info("CustomerFirstName BEFORE"+CustomerFirstName);
		logger.info("bankerAssOpt BEFORE"+bankerAssOpt);
		logger.info("bankerOption BEFORE"+bankerOption);
		logger.info("BankName AFTER"+BankName);

		if(YES.equalsIgnoreCase(bankerAssOpt) && "NO".equalsIgnoreCase(bankerOption))
		{
			logger.info("royal test insured name BEFORE"+cusCompanyName.length());
			if(cusCompanyName==null || cusCompanyName.equalsIgnoreCase(""))
			{
				if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
				{
					tableCreation.insertCell(" : "+openCoverCustomer, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+(BankName.length()>0?BankName+", ":"")+CustomerFirstName+CustomerLastName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
			else
			{
				if(NORMALMUL.equalsIgnoreCase(displayMode) || (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
				{
					tableCreation.insertCell(" : "+openCoverCustomer, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					
					tableCreation.insertCell(" : "+(BankName.length()>0?BankName+", ":"")+cusCompanyName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
		}
		else if(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankerAssOpt) )
		{
			tableCreation.insertCell(" : "+BankName, fontHead1, Rectangle.NO_BORDER,6, 0);
		}
		else
		{
			logger.info("royal test cusCompanyName BEFORE"+cusCompanyName.length());
			
			if(cusCompanyName==null || cusCompanyName.equalsIgnoreCase(""))
			{
				 if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
				{
					tableCreation.insertCell(" : "+openCoverCustomer, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+CustomerFirstName+CustomerLastName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
			else
			{
				if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
				{
					tableCreation.insertCell(" : "+openCoverCustomer, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+cusCompanyName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
		}
		if(!(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankerAssOpt)))
		{
			tableCreation.insertCellPad("Address", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+custAddress1, fontHead1, Rectangle.NO_BORDER,6, 0);
			if(CustomerPoBox.length()>0){
				tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell("   P.O.BOX NO. "+CustomerPoBox, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
			if(CustomerEmirate.length()>0){
				tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell("   "+CustomerEmirate, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
			if(CustomerCountry.length()>0){
				tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell("   "+CustomerCountry, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
		}
		tableCreation.insertCell("", fontHead1, Rectangle.TOP,8, 0);
		tableCreation.insertCellPad("Voyage  From", fontHead2, Rectangle.NO_BORDER,2, 0);
		if(!"".equalsIgnoreCase(TransitFrom.trim()))
		{
			TransitFrom=TransitFrom+", ";
		}
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,3, 0);
		}
		else
		{
			tableCreation.insertCell(" : "+TransitFrom+transCountryName, fontHead1, Rectangle.NO_BORDER,3, 0);
		}
		tableCreation.insertCell("Voyage  To", fontHead2, Rectangle.NO_BORDER,1, 0);
		FinalDestination=(FinalDestination==null||"Others".equalsIgnoreCase(FinalDestination))?"":FinalDestination;
		if(!"".equalsIgnoreCase(FinalDestination.trim()))
		{
			FinalDestination=FinalDestination+",";
		}
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,2, 0);
		}
		else
		{
			tableCreation.insertCell(" : "+FinalDestination+finalCountryName, fontHead1, Rectangle.NO_BORDER,2, 0);
		}
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCellPad("Vessel/Conveyance", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,6, 0);
		}
		else
		{
			tableCreation.insertCellPad("Vessel/Conveyance", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+CarrierName, fontHead1, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("By", fontHead2, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(" : "+TransportName, fontHead1, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell("Date", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(PolicyDate, fontHead1, Rectangle.NO_BORDER,2, 0);
		}
		if(!("YES".equalsIgnoreCase(bankerOption) || "YES".equalsIgnoreCase(bankerAssOpt)))
		{
			if(BankName!=null && BankName.length()>0)
			{
				tableCreation.insertCellPad("Bank Name", fontHead2, Rectangle.NO_BORDER,2, 0);
				if("NormalMultiple".equalsIgnoreCase(displayMode) ||  ("draftModeMultiple".equalsIgnoreCase(displayMode)))
				{
					tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+BankName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
		}
		if(!(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode))))
		{
				tableCreation.insertCellPad("LC Number", fontHead2, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : "+(LcNumber.length()>0?LcNumber:"NIL"), fontHead1, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell("LC Date", fontHead2, Rectangle.NO_BORDER,1, 0);
				tableCreation.insertCell(" : "+(LcDate.length()>0?LcDate:"NIL"), fontHead1, Rectangle.NO_BORDER,2, 0);
		}	
		tableCreation.insertCellPad("Sailing Date", fontHead2, Rectangle.NO_BORDER,2, 0);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,3, 0);
		}
		else
		{
			tableCreation.insertCell(" : "+(SailingDate.length()>0?SailingDate:"NIL"), fontHead1, Rectangle.NO_BORDER,3, 0);
		}
		tableCreation.insertCell("Date", fontHead2, Rectangle.NO_BORDER,1, 0);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,2, 0);
		}
		else
		{
			tableCreation.insertCell(" : "+(PolicyDate), fontHead1, Rectangle.NO_BORDER,2, 0);
		}
		tableCreation.insertCell("", fontHead1, Rectangle.TOP,8, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode))){
			tableCreation.insertCellPad("Goods Description", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation.insertCellPad("Value Basis", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation.insertCellPad("FC Sum Insured", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+ASPER, fontHead1, Rectangle.NO_BORDER,6, 0);
		}else{
			for(int print=0;print<finalCount;print++){
				tableCreation.insertCellPad("Goods Description", fontHead2, Rectangle.NO_BORDER,2, 0);
				StringBuffer goods = new StringBuffer();
				goods.append(comDescription[print]);
				goods.append(" ");
				goods.append(pkgDescription[print]);
				goods.append(" ");
				if(Invoice_arr[print]!=null&&Invoice_arr[print].length()>0){
					goods.append("INV# ");
					goods.append(Invoice_arr[print]);
				}
				tableCreation.insertCell(" : "+goods.toString(), fontHead1, Rectangle.NO_BORDER,6, 0);
				tableCreation.insertCellPad("Value Basis", fontHead2, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : "+SaleTermName_arr[print], fontHead1, Rectangle.NO_BORDER,6, 0);
				if(tolDetails.length>0){
					//tableCreation.insertCellPad("Tolerance", fontHead2, Rectangle.NO_BORDER,2, 0);
					//tableCreation.insertCell(" : "+tolDetails[0][0], fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				tableCreation.insertCellPad("FC Sum Insured", fontHead2, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : "+currencyShortName+". "+CurrencyName, fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(comSumInsRound[print], fontHead1, Rectangle.NO_BORDER,1, 1);
				try{
					if(Double.parseDouble(ExchangeRate)!=1){
						tableCreation.insertCell("Exchange Rate", fontHead2, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCell(" : 1 "+currencyShortName+" = "+ExchangeRate+" "+currencyType, fontHead1, Rectangle.NO_BORDER,2, 0);
					}else{
						tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,3, 0);
					}
				}catch(Exception e){
					tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,3, 0);
				}
				if(!YES.equalsIgnoreCase(currencyOption)){
					tableCreation.insertCellPad("LC Sum Insured", fontHead2, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell(" : "+currencyType+". "+currencyType1, fontHead1, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell(sumInsuredLocalR[print], fontHead1, Rectangle.NO_BORDER,1, 1);
					tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,3, 0);
				}
			}
			tableCreation.insertCellPad("Cover", fontHead2, Rectangle.NO_BORDER,2, 0);
			if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
				tableCreation.insertCell(" : "+CoverName+"+ WAR & STRIKES", fontHead1, Rectangle.NO_BORDER,6, 0);
			}else{
				tableCreation.insertCell(" : "+CoverName, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
		}
		//Premium table
		tableCreation2.setTable(8);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode))){
			tableCreation2.insertCellPad("Premium", fontHead2, Rectangle.NO_BORDER,8, 0);
			tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
			tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
			tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
			if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
				tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
				tableCreation2.insertCellPad("     War Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
			}
			String preQuote = (String)taxDetails.get("QuoteNo0");
			//tax things
			if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
				if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//tax
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Government Tax", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
				}if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Emragency
					tableCreation2.insertCellPad("     Emergency Fund", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
				}
				if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Fee
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Policy Fee", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
				}
			}
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHead1, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCellPad("     Total", fontHead2, Rectangle.NO_BORDER,3, 0);
			tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
			tableCreation2.insertCell(ASPER, fontHead1, Rectangle.NO_BORDER,4, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
		}else if(YES.equalsIgnoreCase(PremiumYes.trim())){
			
			//tax
			String preQuote = (String)taxDetails.get("QuoteNo0");
			
			tableCreation2.insertCellPad("Premium", fontHead2, Rectangle.NO_BORDER,8, 0);
			
			if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
				tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 1);
				tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
				tableCreation2.insertCellPad("     War Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("WarPremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarineWar"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
			}else{
				tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("MarineWar"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
			}
			//tax things
			if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
				if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//tax
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Government Tax", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("GovTax"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
						tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 1);
					}else{
						tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("TaxSum"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					}
				}if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Emragency
					tableCreation2.insertCellPad("     Emergency Fund", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fund"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("TaxSum"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				}
				if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Fee
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Policy Fee", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				}
			}
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHead1, Rectangle.BOTTOM,2, 1);
			
			tableCreation2.insertCellPad("     Total", fontHead2, Rectangle.NO_BORDER,3, 0);
			tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
			tableCreation2.insertCell(pdfFormat.decimalFormat(Double.parseDouble((String)taxDetails.get("FinalPremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,4, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
			
		}else{
			tableCreation2.insertCellPad("Premium", fontHead2, Rectangle.NO_BORDER,8, 0);
			tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
			if(!"13".equalsIgnoreCase(brokerBra.trim())){
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
			}else{
				tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
			}
			tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
			if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
				tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
				tableCreation2.insertCellPad("     War Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
				if(!"13".equalsIgnoreCase(brokerBra.trim())){
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				}else{
					tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
				}
				tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
			}
			
			String preQuote = (String)taxDetails.get("QuoteNo0");
			//tax things
			if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
				if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//tax
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Government Tax", fontHead2, Rectangle.NO_BORDER,3, 0);
					if(!"13".equalsIgnoreCase(brokerBra.trim())){
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					}else{
						tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
					}
					tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
				}if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Emragency
					tableCreation2.insertCellPad("     Emergency Fund", fontHead2, Rectangle.NO_BORDER,3, 0);
					if(!"13".equalsIgnoreCase(brokerBra.trim())){
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					}else{
						tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
					}
					tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
				}if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
					//Fee
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     Policy Fee", fontHead2, Rectangle.NO_BORDER,3, 0);
					if(!"13".equalsIgnoreCase(brokerBra.trim())){
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					}else{
						tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
					}
					tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
				}
			}
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHead1, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCellPad("     Total", fontHead2, Rectangle.NO_BORDER,3, 0);
			if(!"13".equalsIgnoreCase(brokerBra.trim())){
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
			}else{
				tableCreation2.insertCell("     : ", fontHead1, Rectangle.NO_BORDER,1, 0);
			}
			tableCreation2.insertCell("As Agreed", fontHead1, Rectangle.NO_BORDER,4, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
			tableCreation2.insertCell("", fontHeadUl, Rectangle.BOTTOM,2, 1);
			tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
		}
		
		
		
		//Imp Table
		tableCreation3.setTable(8);
		tableCreation3.insertCell("IMPORTANT NOTICE", fontHeadUl, Rectangle.NO_BORDER,8, 2);
		tableCreation3.insertCell("Insured's attention is especially drawn to policy's exceptions and exclusions as stated herein. The attention of the insured/claimant is drawn to the " +
				"IMPORTANT NOTICE as attached in respect of liability of Carriers,Bailees or other " +
				"Third Parties and claims procedures", font, Rectangle.NO_BORDER,8, 0);
		//Added by Chinna for Paid Stamp
		Image paidStampImage=Image.getInstance(request.getSession().getServletContext().getRealPath("/" + IMG+ "Paid_Stamp.gif"));
		if("13".equalsIgnoreCase(brokerBra.trim()))
		{
			tableCreation3.insertCell(paidStampImage, Rectangle.NO_BORDER,8, 2);
		}//End
		tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 5, 0);
		tableCreation.insertCell(tableCreation3.getTable(), Rectangle.NO_BORDER, 3, 0);
		tableCreation.insertCellPad("", fontHead2, Rectangle.NO_BORDER,8, 0);
		tableCreation.setTableSpacing(3f);
		tableCreation1.setTable(8);
		tableCreation1.insertCell(tableCreation.getTable(), Rectangle.BOX, 8, 0);
		document.add(tableCreation1.getTable());
		
		tableCreation.setTable(8);
		tableCreation.insertCellPad("Conditions of Cover", fontHead2, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCellPad("TERMS, CONDITIONS, WARRANTIES AND EXCLUSIONS AS PER THE OPEN COVER", fontHead1, Rectangle.NO_BORDER,8, 0,25);
		if(YES.equalsIgnoreCase(BackDaysOption) && !( "admin".equalsIgnoreCase(AdminStatus) || 
				"NORMAL_EXCESS_PRICE".equalsIgnoreCase(AdminStatus)))
		{
			//tableCreation.insertCellPad("NO KNOWN OR REPORTED LOSS TILLL "+(NODATE.equalsIgnoreCase(policyGenDate)?quoteGenDate:policyGenDate), fontHead2, Rectangle.NO_BORDER,8, 0);
		}
		if((NORMALMUL.equalsIgnoreCase(displayMode) || DRFATMODEMUL.equalsIgnoreCase(displayMode))){
			if(freeTextMulSea.trim().length()>2){
				tableCreation.insertCellPad(freeTextMulSea.trim(), fontHead1, Rectangle.NO_BORDER,8, 0);
			}if(freeTextMultiple.trim().length()>2){
				tableCreation.insertCellPad(freeTextMultiple.trim(), fontHead1, Rectangle.NO_BORDER,8, 0);
			}
			if(brokerRemarkMul.trim().length()>2){
				tableCreation.insertCellPad(brokerRemarkMul, fontHead1, Rectangle.NO_BORDER,8, 0);
			}
		}else{
			if(seaFreeTextOpt.length>0)
			{
				for(int ot=0;ot<seaFreeTextOpt.length;ot++)
				{
					tableCreation.insertCellPad((seaFreeTextOpt[ot][0]!=null?seaFreeTextOpt[ot][0]:""), fontHead1, Rectangle.NO_BORDER,8, 0);
				}
			}
			if(openFreeText.length>0)
			{
				for(int ot=0;ot<openFreeText.length;ot++)
				{
					tableCreation.insertCellPad((openFreeText[ot][0]!=null?openFreeText[ot][0]:""), fontHead1, Rectangle.NO_BORDER,8, 0);
				}
			}
			if(BrokerRemarks.trim().length()>2){
				tableCreation.insertCellPad(BrokerRemarks, fontHead1, Rectangle.NO_BORDER,8, 0);
			}
		}
		tableCreation.insertCellPad("", fontHead2, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCellPad("The attached Clauses and Endorsements form part of this Policy.", fontHead2, Rectangle.NO_BORDER,8, 0);
		tableCreation1.setTable(8);
		tableCreation1.insertCell(tableCreation.getTable(), Rectangle.BOX, 8, 0);
		tableCreation1.setTableSpacing(2f);
		document.add(tableCreation1.getTable());
		tableCreation.setTable(8);
		tableCreation.insertCellPad("Claim intimation & Survey :", fontHead2, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCell("                       	     In the event of loss or damage which may involve a claim under the Insurance, no claim shall be payable unless immediate notice is given to and survey report obtained from Insurer's or Agent's office named below.", fontHead1, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCellPad("", fontHead1, Rectangle.NO_BORDER,8, 0);
		tableCreation2.setTable(8);
		if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		{
			tableCreation2.insertCellPad(setAgentMul, fontHead2, Rectangle.BOX,8, 0);
		}
		else
		{
			if(SettlingAgentId.equalsIgnoreCase("0")){
				if("".equalsIgnoreCase(SetAgentNameO) || " ".equalsIgnoreCase(SetAgentNameO) ||  NULL.equalsIgnoreCase(SetAgentNameO) ){
					tableCreation2.insertCellPad(braAddress+", "+braPO+", "+braCity+", "+braCountry+" Tel: "+braPhone+"   Fax: "+ braFax, fontHead2, Rectangle.BOX,8, 0);
				}else{
					tableCreation2.insertCellPad(SetAgentNameO, fontHead2, Rectangle.BOX,8, 0);
				}
				//tableCreation2.insertCellPad("ARAB ORIENT INSURANCE (P.S.C.), P.O. BOX 27966, DUBAI - U.A.E., Phone:04-2953425, Fax:04-2955701, Contact :", fontHead3, Rectangle.BOX,8, 0);
			}else{
				tableCreation2.insertCellPad(SetAgentName+"\b"+setAgentAddress1+"\b"+setAgentAddress2+"\b Tel:\b"+SetAgentTelPh+"\bFax:\b"+setAgentFaxNo, fontHead2, Rectangle.BOX,8, 0);
			}
		}
		Image signImageRoyal = null;
		Image stampImageRoyal = null;
		try
		{
			String stampPath;
			stampPath = request.getSession().getServletContext().getRealPath("/" + IMG+ stampImage);
			String signPath;
			signPath = request.getSession().getServletContext().getRealPath("/" + IMG+ signImage);
			signImageRoyal  = Image.getInstance(signPath);
			stampImageRoyal  = Image.getInstance(stampPath);
			//signImageRoyal.scaleToFit(70,54);
			signImageRoyal.scaleToFit(130,130);
			stampImageRoyal.scaleToFit(60,54);
			
		}
		catch(Exception e)
		{
			logger.debug(e);
		}
		
		tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0,10,10);
		if("13".equalsIgnoreCase(brokerBra.trim())){
			tableCreation.insertCell("Subject to no known or reported losses as the date of issuance of the certificate.", fontHead2, Rectangle.NO_BORDER,8, 0);
		}
		tableCreation.insertCellPad("", fontHead1, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCell("In witness whereof this policy is signed for and on behalf of the Company at "+braCity, fontHead1, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
		tableCreation.insertCell("for and on behalf of "+braAddress, fontHead2, Rectangle.NO_BORDER,4, 0);
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,5, 0);
		if(stampStatus.equalsIgnoreCase("Y")){
			tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(stampImageRoyal, Rectangle.NO_BORDER,1, 0);
		}else{
			tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,2, 2);
		}
		tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,1, 0);
		tableCreation.insertCell(createDate, fontHead1, Rectangle.NO_BORDER,5, 0);
		//tableCreation.insertCellPad("", fontHead1, Rectangle.NO_BORDER,1, 0);
		tableCreation.insertCell("", fontHead2, Rectangle.NO_BORDER,3, 0);
		tableCreation1.setTable(8);
		tableCreation1.insertCell(tableCreation.getTable(), Rectangle.BOX, 8, 0);
		tableCreation1.setTableSpacing(1f);
		document.add(tableCreation1.getTable());
	
	//if(!(DRFATMODE.equalsIgnoreCase(displayMode) || DRFATMODEMUL.equalsIgnoreCase(displayMode)))
		if(2<1)
		{	
		//Customer Certificates new one
		String cusCertificate[][];
		cusCertificate = finalBean.getDirectCustomerCertificateStatus(openCoverNo,PolicyNo);
		if(cusCertificate.length>0)
		{
			String slnoRun;
			slnoRun = finalBean.getMaxsno((String)session.getAttribute("product_id"),brokerBra,PolicyNo);
			StringBuffer missi;
			missi = new StringBuffer();
			String shipDate = "";
			double premiumdue = 0;
			StringBuffer blno;blno = new StringBuffer();
			com.maan.product.ProductSelection products;
			products = new com.maan.product.ProductSelection();
			String openCoverInfo[][];
			openCoverInfo = products.getOpenCoverInfo(openCoverNo);
			if(openCoverInfo.length>0){
				missi.append(openCoverInfo[0][0]);
			}
			document.newPage();
			Font bufont,bbufont,sfont;
			bufont = new Font(arialFont, 12+fontSize, Font.BOLD);
			bbufont = new Font(arialFont, 14+fontSize, Font.BOLD);
			sfont = new Font(arialFont, 11+fontSize);
			StringBuffer pers;
			pers = new StringBuffer();
			pers.append("M/s. ");	pers.append(cusCertificate[0][4]);
			pers.append(", P.O.BOX ");	pers.append(cusCertificate[0][5]);
			pers.append(", ");	pers.append(cusCertificate[0][6]);
			pers.append(',');
			if(cusCertificate[0][7].equalsIgnoreCase("select")){
				pers.append(" U.A.E.");
			}else{
				pers.append(' ');	pers.append(cusCertificate[0][7]);
				pers.append('.');
			}
			if(cusCertificate.length>1)
			{
				for(int c=0;c<cusCertificate.length;c++)
				{
					premiumdue = premiumdue + Double.parseDouble(cusCertificate[c][1]);
					if((cusCertificate[c][0].trim()).length()>0){
						blno.append(cusCertificate[c][0]);blno.append(',');
					}
				}
				shipDate = cusCertificate[0][2];
				missi.append('/');
				missi.append(cusCertificate[0][3]);
				blno.deleteCharAt(blno.indexOf(","));
			}
			else
			{
				blno.append(cusCertificate[0][0]);
				premiumdue = Double.parseDouble(cusCertificate[0][1]);
				shipDate = cusCertificate[0][2];
				missi.append('/');
				missi.append(cusCertificate[0][3]);
			}
			
			tableCreation.setTable(6);
			tableCreation.insertCell("", bufont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.setTableSpacingBefore(20f);
			document.add(tableCreation.getTable());
			tableCreation.setTable(6);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
			tableCreation.insertCell("DATE", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell(SPACE+shipDate, sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
			tableCreation.insertCell("SL NO", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell(SPACE+slnoRun, sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.setTableSpacingBefore(50f);
			document.add(tableCreation.getTable());
			tableCreation.setTable(6);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("TO WHOMSOEVER IT MAY CONCERN", bbufont, Rectangle.NO_BORDER, 4, 2);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.setTableSpacingBefore(50f);
			document.add(tableCreation.getTable());
			tableCreation.setTable(6);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("This is to certify that shipment", bbufont, Rectangle.NO_BORDER, 5, 0);
			tableCreation.setTableSpacingBefore(30f);
			document.add(tableCreation.getTable());
			tableCreation.setTable(6);
			if(blno.length()>1)
			{
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
				tableCreation.insertCell("B/L NO.", sfont, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell(SPACE+blno.toString(), sfont, Rectangle.NO_BORDER, 3, 0);
			}
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("Marine Open Cover Policy No", sfont, Rectangle.NO_BORDER, 2, 0);
			tableCreation.insertCell(SPACE+missi.toString(), bufont, Rectangle.NO_BORDER, 3, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell("Premium Due", sfont, Rectangle.NO_BORDER, 2, 0);
			tableCreation.insertCell(SPACE+currencyType+"\b"+pdfFormat.decimalFormat(premiumdue,2), sfont, Rectangle.NO_BORDER, 3, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.setTableSpacingBefore(50f);
			document.add(tableCreation.getTable());
			tableCreation.setTable(6);
			tableCreation.insertCell("This certificate issued upon the request of the Assured "+pers.toString(), sfont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.setTableSpacingBefore(50f);
			document.add(tableCreation.getTable());

			tableCreation.setTable(6);
			String urlSigns;
			urlSigns = request.getSession().getServletContext().getRealPath("/"+IMG+signImage);
			Image signatureImages;
			Image signStampImg = null;
			signatureImages = Image.getInstance(urlSigns);
			signStampImg = Image.getInstance(request.getSession().getServletContext().getRealPath("/" + IMG+ signImage.replaceAll(".jpg", "")+stampImage));
			if(cid.equals(SAUDI)||cid.equals(BHARAIN))
			{
				signatureImages.scaleToFit(70,40);
			}
			else
			{
				signatureImages.scaleToFit(70,54);
			}
			String urlStamps;
			urlStamps = request.getSession().getServletContext().getRealPath("/" + IMG+ stampImage);
			Image stampImagess;
			stampImagess = Image.getInstance(urlStamps);
			signStampImg.scaleToFit(130,54);			
			if(cid.equals(SAUDI)||cid.equals(BHARAIN))
			{
				stampImagess.scaleToFit(60,74);
			}
			else
			{
				stampImagess.scaleToFit(60,54);
				stampImagess.scalePercent(25,25);
			}
			tableCreation.insertCell("For:"+braAddress, bufont, Rectangle.NO_BORDER, 6, 0);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 6, 0);
			if(!TEST.equalsIgnoreCase(usrModeController))
			{	
				if(cid.equals(GULF)){
					if(stampStatus.equalsIgnoreCase("Y")){
	            		tableCreation.insertCell(signStampImg,Rectangle.NO_BORDER, 2, 2);
					}
					else{
						tableCreation.insertCell(signatureImages,Rectangle.NO_BORDER, 1, 0);
						tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 2);
					}
				}else{
					tableCreation.insertCell(signatureImages,Rectangle.NO_BORDER, 1, 0);
					if(stampStatus.equalsIgnoreCase("Y")){
	            		tableCreation.insertCell(stampImagess,Rectangle.NO_BORDER, 1, 2);
					}else{
						tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 1, 2);
					}
				}
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
				tableCreation.insertCell("(Authorised Signatory)", bufont, Rectangle.NO_BORDER, 2, 0);
				tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 4, 0);
				if(stampStatus.equalsIgnoreCase("Y"))
				{
					tableCreation.insertCell("This is electronically generated certificate and hence no need for actual signature and stamp", sfont, Rectangle.NO_BORDER, 6, 0);
				}
				else
				{
					tableCreation.insertCell("This is electronically generated policy schedule and hence no need for actual signature", sfont, Rectangle.NO_BORDER, 6, 0);
				}
			}
			tableCreation.setTableSpacingBefore(50f);
			document.add(tableCreation.getTable());
		}
	}
	//Customer Certificates new one
	if(NORMALMUL.equalsIgnoreCase(displayMode) ||  (DRFATMODEMUL.equalsIgnoreCase(displayMode)))
	{
			document.setPageSize(PageSize.A4.rotate());
			TableHeader endPage1;
			PdfPTable stable=null;
			try
			{
				tableCreation = new PdfPTableCreation();
				tableCreation1 = new PdfPTableCreation();
				tableCreation.setTable(3);
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
				//document.add(stable);

				/////////Loop///////
				String countRows="";
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
				mulTotalSumLocal = isNull((String)hashAnnexure.get("multiTotalSumInsuredLocal"),"0");			
			
			document.newPage();
			document.add(stable);
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
			String qnos[] = null;
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
			}
			logger.info("Before Printing mulTotalSumLocal in PDF "+mulTotalSumLocal);
			logger.info("Before mulTotalPremium in PDF "+mulTotalPremium);
			tableCreation.setTable(11);
			tableCreation.insertCell("", font, Rectangle.BOX, 7, 0);
			tableCreation.insertCell(mulTotalSumLocal, font, Rectangle.BOX, 1, 1);
			tableCreation.insertCell("Grand Total", font, Rectangle.BOX, 2, 2);
			tableCreation.insertCell(pdfFormat.decimalFormat(Math.round(Double.parseDouble(mulTotalPremium.replaceAll(",", ""))),decimalDigit), font, Rectangle.BOX, 1, 1);
			document.add(tableCreation.getTable());
			document.close();
		}
		catch(DocumentException de) 
		{
			logger.debug(de);
		}
	}//End if If Condition

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
	
}*/
	public void writePDF(final String option)throws BaseException {
		
		String premium;
		String finalPremium;
		String loadDis;
		String debitNo;
		String debitDate;
		String broName;
		StringBuffer wordPre;
		wordPre = new StringBuffer();
		String fileb = "";
		String fills, fillsDigit,currAbr;

		StringBuffer ConvertToWords;
		ConvertToWords = new StringBuffer();
		NumberFormat fmt;
		fmt = getNumberFormat(decimalDigit);
		
		PdfPTableCreation tableCreation;
		tableCreation = new PdfPTableCreation();
		NumberToWordBean numword;
		numword = new NumberToWordBean();
		Document document;
		document = new Document(PageSize.A4, 50, 50, 50, 50);
		try {
			
			String result[][];
			rsa.opencoverpdf.finalprint dataBean;
			dataBean = new rsa.opencoverpdf.finalprint();
			result = dataBean.getPreCusDetails(getQuoteNo());
			
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			String[][] placeCode;
			placeCode = finalBean.getPlaceCodes(getQuoteNo(),"Debit","11","QuoteNo");
			//currencyType = placeCode[0][5] == null ? "" : placeCode[0][5];
			fills = placeCode[0][8] == null ? "" : placeCode[0][8];
			fillsDigit = placeCode[0][9] == null ? "" : placeCode[0][9];
			currAbr = placeCode[0][6] == null ? "" : placeCode[0][6];
			StringBuffer cusAdd;
			cusAdd= new StringBuffer();
			
				cusAdd.append(result[0][0] == null ? "" : (result[0][0] + "\n"));
				cusAdd.append(result[0][1]==null || (result[0][1].trim()).length() <= 0 ? "": (result[0][1] + "\n"));
				cusAdd.append(result[0][2]==null || (result[0][2].trim()).length() <= 0 ?  "" : (result[0][2] + "\n"));
				cusAdd.append(result[0][3]==null || (result[0][3].trim()).length() <= 0 ?  "":(result[0][3] + "\n"));
				cusAdd.append(result[0][4]==null || (result[0][4].trim()).length() <= 0 ?  "":(result[0][4] + "\n"));
				cusAdd.append(result[0][5]==null || (result[0][5].trim()).length()<=0 ?  "":(result[0][5] + "\n"));
				
				premium = result[0][7]==null?"":result[0][7];
				loadDis = result[0][8]==null?"":result[0][8];
				debitNo = result[0][9] == null ? "":result[0][9];
				debitDate = result[0][10] == null ?"":result[0][10];
				finalPremium = result[0][11]==null?"0":result[0][11];
				
				String multiDeductWord;
				multiDeductWord = finalPremium.replaceAll(",", "");
				if ((multiDeductWord.length() > 0)
						&& (multiDeductWord.indexOf('.') != -1)) {
					multiDeductWord = multiDeductWord.substring(0,multiDeductWord.indexOf('.'));
					ConvertToWords.append(numword.convertNumToWord(Integer.parseInt(multiDeductWord)));
					fileb = finalPremium.substring(finalPremium.indexOf('.') + 1, finalPremium.length());
				}
				if (finalPremium.indexOf('.') == -1) {
					multiDeductWord = multiDeductWord.substring(0,multiDeductWord.length());
					ConvertToWords.append(numword.convertNumToWord(Integer.parseInt(multiDeductWord)));
					fileb = "0";
				}
				
				if (fileb.length() >= 3) 
				{
					if (fillsDigit.equalsIgnoreCase("100")){
						fileb = fileb.substring(0, 2);
					}else if (fillsDigit.equalsIgnoreCase("1000")){
						fileb = fileb.substring(0, 3);
					}
				}
				else if(fileb.length() == 1)
				{
					if(fillsDigit.equalsIgnoreCase("100")){
						fileb = fileb+"0";
					}else if(fillsDigit.equalsIgnoreCase("1000")){
						fileb = fileb+"00";
					}
				}
			
				wordPre.append(currencyType);
				wordPre.append(" \t\t ");
				wordPre.append(ConvertToWords.toString());
				wordPre.append(" \t\t and ");
				wordPre.append(fills);
				wordPre.append("  \t\t");
				wordPre.append(fileb);
				wordPre.append('/');
				wordPre.append(fillsDigit);
				wordPre.append(" only");
				
			PdfWriter writer=null;
			try {
				writer = PdfWriter.getInstance(document, new FileOutputStream(getFilePath()));
			} catch (Exception e) {
				logger.debug(e);
				e.printStackTrace();
			}
			document.open();
			
				// ///////Header and Footer/////////
				HeaderFooterImage endPage;
				endPage = new HeaderFooterImage();
				writer.setPageEvent(endPage);
				endPage.setHeaderImagePath(getHeaderImagePath()+"/"+(placeCode[0][1] == null ? "spacer.gif" : placeCode[0][1]));
				endPage.setFooterImagePath(getFooterImagePath()+"/"+(placeCode[0][2] == null ? "spacer.gif" : placeCode[0][2]));
				endPage.setFontPath(getFontPath());
				endPage.setOption(option);
				
				
			// ////////// Font Declaration ////////////
			Font sfont,sfont1,bfont,font1,sfont2;
			sfont = new Font(Font.HELVETICA, 8);
			sfont1 = new Font(Font.HELVETICA, 9, Font.BOLD);
			bfont = new Font(Font.HELVETICA, 8, Font.BOLD);
			font1 = new Font(Font.HELVETICA, 12, Font.BOLD);
			font1.setStyle(Font.UNDERLINE);
			sfont2 = new Font(Font.HELVETICA, 8, Font.UNDERLINE);
			// //////// Table 10 --Third Page-- //////
			// document.newPage();
			tableCreation.setTable(8);
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			document.add(tableCreation.getTable());
			// /////// Table 11 ///////
			tableCreation.setTable(7);
			tableCreation.insertCell(Rectangle.TOP, 7);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.insertCell("Debit Note", sfont2, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.insertCell(Rectangle.BOTTOM, 7);
			tableCreation.setTableSpacing(30f);
			document.add(tableCreation.getTable());
			
			// //////// Table 12 ////////
			tableCreation.setTable(7);
			tableCreation.insertCell("To", sfont, Rectangle.NO_BORDER, 5, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 2);
			tableCreation.insertCell(Rectangle.NO_BORDER, 5);
			tableCreation.insertCell("Debit Note No   : " + debitNo, sfont,Rectangle.NO_BORDER, 2, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 5);
			tableCreation.insertCell("Debit Note Date : " + debitDate, sfont,Rectangle.NO_BORDER, 2, 0);
			tableCreation.insertCell(cusAdd.toString(), sfont, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 2);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 2);
			tableCreation.insertCell("", sfont, Rectangle.NO_BORDER, 5, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 2);
			tableCreation.setTableSpacing(5f);
			document.add(tableCreation.getTable());
			
			// ///////// Table 13 //////////
			tableCreation.setTable(7);
			tableCreation.insertCell("In accordance with your instructions we have issued the attached documentation and debited your account as per details\nshown hereunder :",sfont, Rectangle.NO_BORDER, 7, 0);
			tableCreation.setTableSpacing(50f);
			document.add(tableCreation.getTable());

			// ///////// Table 14 /////////
			tableCreation.setTable(7);
			tableCreation.insertCell("Description", bfont, Rectangle.TOP, 6, 0);
			tableCreation.insertCell("Amount in " + currAbr, bfont,	Rectangle.TOP, 1, 0);
			if (userModeSC.equalsIgnoreCase("Test")){
				tableCreation.insertCell("Being the premium due on\bmarine cargo insurance\bPolicy No : " + getQuoteNo(), sfont,Rectangle.TOP, 6, 0);
			}else{
				tableCreation.insertCell("Being the premium due on\bmarine cargo insurance\bPolicy No : " + dataBean.isNull(result[0][6],""), sfont, Rectangle.TOP, 6,0);
			}
			tableCreation.insertCell(fmt.format(Double.parseDouble(premium)), sfont,Rectangle.TOP, 1, 1);
			tableCreation.insertCell(Rectangle.NO_BORDER, 7);
			tableCreation.insertCell("Less Discount Premium (if any)", sfont, Rectangle.BOTTOM,6, 0);
			tableCreation.insertCell(fmt.format(Double.parseDouble(loadDis)), sfont,Rectangle.BOTTOM, 1, 1);
			tableCreation.insertCell(Rectangle.NO_BORDER, 7);
			tableCreation.insertCell(wordPre.toString(), sfont, Rectangle.NO_BORDER, 5, 0);
			tableCreation.insertCell("Total:", sfont, Rectangle.NO_BORDER, 1, 0);
			tableCreation.insertCell(fmt.format(Double.parseDouble(finalPremium)), sfont,	Rectangle.NO_BORDER, 1, 1);
			tableCreation.insertCell(Rectangle.BOTTOM, 7);
			tableCreation.setTableSpacing(10f);
			document.add(tableCreation.getTable());
			
			// ///// Table 15 ////////
			tableCreation.setTable(7);
			tableCreation.insertCell("Please Note :", sfont1,Rectangle.NO_BORDER, 2, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 5);
			tableCreation.insertCell("Your remittance in respect of the above transaction should be forwarded to us in order to ensure continuity of cover. We would appreciate you contacting us immediately if you have any queries relating to the above details or the attached documents",sfont, Rectangle.NO_BORDER, 7, 0);
			tableCreation.setTableSpacing(10f);
			document.add(tableCreation.getTable());
			
			
			// Vinoth /////// Table 16 ////////////
			//broName = runner.singleSelection("select nvl(company_name,' ') from broker_company_master where agency_code=(select oa_Code from login_master where login_id='"+ loginId + "')");
			broName=(placeCode[0][10] == null ? "" : placeCode[0][10]);
			
			
			tableCreation.setTable(7);
			tableCreation.insertCell("\b\b\b\b\b\bFor : " + broName, sfont1,Rectangle.NO_BORDER, 4, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			Image jpg1=null;
			try {
				jpg1 = Image.getInstance(getSignedImagePath()+"/"+(placeCode[0][3] == null ? "spacer.gif" : placeCode[0][3]));
				jpg1.scalePercent(30, 30);
			}
			catch(Exception de) {
				logger.debug(de);
				de.printStackTrace();
			}
			
			tableCreation.setTableSpacing(25f);
			tableCreation.insertCell(Rectangle.NO_BORDER, 4);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.insertCell(jpg1, Rectangle.NO_BORDER, 4, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.insertCell(Rectangle.NO_BORDER, 4);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.insertCell(Rectangle.NO_BORDER, 7);
			tableCreation.insertCell(Rectangle.NO_BORDER, 7);
			tableCreation.insertCell("\b\b\b\b\b\bAuthorised Signatory",sfont1,Rectangle.NO_BORDER, 4, 0);
			tableCreation.insertCell(Rectangle.NO_BORDER, 3);
			tableCreation.setTableSpacing(55f);
			document.add(tableCreation.getTable());
			
		}
		catch (DocumentException de) {
			logger.debug(de);
			de.printStackTrace();
		}
		try{
			document.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	public NumberFormat getNumberFormat(final int decimalPlace){
		NumberFormat fmt;
		switch(decimalPlace){
		case 2:
			fmt = new java.text.DecimalFormat("##,##0.00");break;
		case 3:
			fmt = new java.text.DecimalFormat("##,##0.000");break;
		case 4:
			fmt = new java.text.DecimalFormat("##,##0.0000");break;
		default:
			fmt = new java.text.DecimalFormat("##,##0.00");
		}
		return fmt;
	}
	public String getWarPremium(final String quoteNo,final int decimalDigit)throws BaseException
	{
		logger.info("OpenCover finalPrint getWarPremium method()");
		logger.debug("- Enter");
			String war;
			final String args[] = {quoteNo.trim()};
			String check = quoteNo.indexOf('-')==-1?"quote_no":"policy_no";
			war = runner.singleSelection("select sum(nvl(ROUND(war_charges,"+decimalDigit+"),0)) from marine_result_details where application_no=(select application_no " +
					"from position_master where "+check+"=?)",args);
		logger.debug("EXIT");
		
		return war;
	}
	public String[][] getMultiQuotes(final String policyNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getWarPremium method()");
		logger.debug("- Enter");
			String sql;
			sql = "select quote_no from position_master where policy_no=? and status='P' and open_cover_int_status in('LINKED') order by quote_no";
			String result[][]; 
			final String args[] = {policyNo};
			result = runner.multipleSelection(sql,args);
		logger.debug("EXIT");
		
		return result;
	}

	public String getFontPath() {
		return fontPath;
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}