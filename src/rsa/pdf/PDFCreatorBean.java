package rsa.pdf;

//import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import rsa.opencoverpdf.finalprint;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
//import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
//import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
//import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.Home.MasterController.NumberToWord.AbstractProcessor;
import com.maan.Home.MasterController.NumberToWord.DefaultProcessor;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;
//import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;

public class PDFCreatorBean
{
	private static final Logger logger = LogUtil.getLogger(PDFCreatorBean.class);
	static public AbstractProcessor processor;
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String DRAFTMODE = "draftMode";
	final static transient private String QUOTENO = "QuoteNo";
	final static transient private String YES = "YES";
	final static transient private String SAUDI = "153";
	final static transient private String BHARAIN = "14";
	//final static transient private String OMANI = "173";
	final static transient private String GULF = "1";
	final static transient private String TEST = "Test";
	//final static transient private String AMTINS = "Amount Insured: ";
	//final static transient private String SPACE = "\b\b\b\b\b\b\b\b";
	//final static transient private String ENAPPROVBY = "Entered/Approved By: ";
	//final static transient private String LINEBREAK = "\n";
	//final static transient private String HUND = "100";
	//final static transient private String THOU = "1000";
	final static transient private String TAB = "\t\t";
	final static transient private String DEBIT = "Debit";
	final static transient private String POLICYNO = "POLICYNO";
	final static transient private String SCHEDULE = "schedule";
	final static transient private String NORMALSUP = "NormalSupplement";
	private String filePath = "";
	//Added by Chinna
	Image paidStampImage;
	
	private String loginId;
	private String freightUser;
	private String freightBroker;
	private String url;
	private String verNo;
	private String openCoverNo;
	private String adnicIssuer;
	
	
	public String getAdnicIssuer() {
		return adnicIssuer;
	}

	public void setAdnicIssuer(String adnicIssuer) {
		this.adnicIssuer = adnicIssuer;
	}

	public String getOpenCoverNo() {
		return openCoverNo;
	}

	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}

	public String getVerNo() {
		return verNo;
	}

	public void setVerNo(String verNo) {
		this.verNo = verNo;
	}

	public String getFreightBroker() {
		return freightBroker;
	}

	public void setFreightBroker(String freightBroker) {
		this.freightBroker = freightBroker;
	}

	public String getFreightUser() {
		return freightUser;
	}

	public void setFreightUser(String freightUser) {
		this.freightUser = freightUser;
	}

	public Image getPaidStampImage() {
		return paidStampImage;
	}

	public void setPaidStampImage(Image paidStampImage) {
		this.paidStampImage = paidStampImage;
	}
	//End
	public void setFilePath(final String filePath) 
	{
		this.filePath = filePath;
	}

	public String getFilePath() 
	{
		return filePath;
	}
	private String dcid="";
	public void setDcid(final String dcid)
	{
		this.dcid = dcid;
	}
	public String getDcid()
	{
		return this.dcid;
	}
	private String cols="";
	public void setCols(final String cols)
	{
		this.cols = cols;
	}
	public String getCols()
	{
		return this.cols;
	}
	private String currencyType = "";
	private String currencyType1 = "";
	public void setCurrencyType(final String currencyType)
	{
		this.currencyType = currencyType;
	}
	public String getCurrencyType()
	{
		return this.currencyType;
	}
	private String place = "";
	public void setPlace(final String place)
	{
		this.place = place;
	}
	public String getPlace()
	{
		return this.place;
	}
	private String headImage = "";
	public void setHeadImage(final String headImage)
	{
		this.headImage = headImage;
	}
	public String getHeadImage()
	{
		return this.headImage;
	}
	private String footImage = "";
	public void setFootImage(final String footImage)
	{
		this.footImage = footImage;
	}
	public String getFootImage()
	{
		return this.footImage;
	}
	private String signImage = "";
	public void setSignImage(final String signImage)
	{
		this.signImage = signImage;
	}
	public String getSignImage()
	{
		return this.signImage;
	}
	private String signStampImage = "";
	private String stampImage = "";
	public void setStampImage(final String stampImage)
	{
		this.stampImage = stampImage;
	}
	public String getStampImage()
	{
		return this.stampImage;
	}
	private String stampStatus = "";
	public void setStampStatus(final String stampStatus)
	{
		this.stampStatus = stampStatus;
	}
	public String getStampStatus()
	{
		return this.stampStatus;
	}
	private String braAddress = "";
	public void setBraAddress(final String braAddress)
	{
		this.braAddress = braAddress;
	}
	public String getBraAddress()
	{
		return this.braAddress;
	}
	private String braPO = "";
	public void setBraPO(final String braPO)
	{
		this.braPO = braPO;
	}
	public String getBraPO()
	{
		return this.braPO;
	}
	private String braCity = "";
	public void setBraCity(final String braCity)
	{
		this.braCity = braCity;
	}
	public String getBraCity()
	{
		return this.braCity;
	}
	private String braCountry = "";
	public void setBraCountry(final String braCountry)
	{
		this.braCountry = braCountry;
	}
	public String getBraCountry()
	{
		return this.braCountry;
	}
	private String braPhone = "";
	public void setBraPhone(final String braPhone)
	{
		this.braPhone = braPhone;
	}
	public String getBraPhone()
	{
		return this.braPhone;
	}
	private String braFax = "";
	public void setBraFax(final String braFax)
	{
		this.braFax = braFax;
	}
	public String getBraFax()
	{
		return this.braFax;
	}
	private String brokerBra = "";
	public void setBrokerBra(final String brokerBra)
	{
		this.brokerBra = brokerBra;
	}
	public String getBrokerBra()
	{
		return this.brokerBra;
	}
	private int decimalDigit = 0;
	public void setDecimalDigit(final int decimalDigit)
	{
		this.decimalDigit = decimalDigit;
	}
	public int getDecimalDigit()
	{
		return this.decimalDigit;
	}
	private double taxPercent = 0;
	public void setTaxPercent(final double taxPercent)
	{
		this.taxPercent = taxPercent;
	}
	public double getTaxPercent()
	{
		return this.taxPercent;
	}
	private String website="";
	public void setWebsite(final String website)
	{
		this.website = website;
	}
	public String getWebsite()
	{
		return this.website;
	}
	private String cid = "";
	public void setCid(final String cid)
	{
		this.cid = cid;
	}
	public String getCid()
	{
		return this.cid;
	}
	private String imagePath = "";
	public void setImagePath(final String imagePath)
	{
		this.imagePath = imagePath;
	}
	public String getImagePath()
	{
		return  this.imagePath;
	}
	private String footerImagePath = "";
	public void setFooterImagePath(final String footerImagePath)
	{
		this.footerImagePath = footerImagePath;
	}
	public String getFooterImagePath()
	{
		return this.footerImagePath;
	}
	private String displayText = "";
	public void setDisplayText(final String displayText)
	{
		this.displayText = displayText;
	}
	public String getDisplayText()
	{
		return this.displayText;
	}
	private String usrModeController = "";
	public void setUsrModeController(final String usrModeController)
	{
		this.usrModeController = usrModeController;
	}
	public String  getUsrModeController()
	{
		return this.usrModeController;
	}
	PremiumInputsBean premiumBean=new PremiumInputsBean();
	public void writePDF(final String broBra,final String cids,final int decimalDigit,final String freightUser,
			final String login,final String pid,final String Policy_No)throws BaseException	{
		logger.info("One Off PDFCreatorBean getPolicysFreshBackDesc method()");
		logger.debug(ENTER);
		NumberToWordBean convertWord;
		convertWord = new NumberToWordBean();
		PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
		tableCreation = new PdfPTableCreation();
		tableCreation1 = new PdfPTableCreation();
		tableCreation2 = new PdfPTableCreation();
		tableCreation3 = new PdfPTableCreation();
		tableQuote = new PdfPTableCreation();
		PdfWriter writer=null;
		PDFDisplay pdis;
		pdis = new PDFDisplay();
		rsa.pdf.finalprint finalBean;
		finalBean = new rsa.pdf.finalprint();
		
		Map  premiumdetails = new HashMap();
		Map  commoditydetails = new HashMap();
		String 	BrokerName = "",coverName = "",	
		carrierName = "", TransitFrom = "", finalDestination = "",
		blAwbNo = "", blAwbDate = "", issuerName="",extraCoverId="", 
		sailDate="",basisVal="",certClausesYN="";

		//String finalAmtRs = "";
		//String finalAmtPs = "";
		//int finalRsConversion = 0;
		//String ConvertToWords;
		String transportId = "";
		String warehouseOption = "";
		String wareFinalOpt = "";
		String subjectKnown = "";
		String premium = "";
		String excessPremium = "";
		String brokerRemarks = "";
		String brokerCompany = "";
		String seaOption = "";
		String partialShip = "";
		String fillsDigit="100";
		
		String[] supplier = new String[0];
		String[] invoice = new String[0];
		String[] commoditySumIns = new String[0];
		String tolDetails[][] = new String[0][0];
		String bankName = "";
		String fromCountryId = "";
		String CustomerFirstName = "", CustomerLastName = "",custAddress1 = "", 
			CustomerCountry = "", CustomerEmirate = "",custAddress2="";
		
		String AdminStatus = "";
		String AdminRemarks = "";
		String FragileMessage = "";
		String policyDate = "";
		String approvedBy = "";
		String CustomerPoBox = "";
		String custCompany = "";
		
		String totalSumIns = "";
		String insuredTotal = "";
		String commodityName[] = new String[0];
		String commodityDes[] = new String[0];
		String localSumIns[] = new String[0];
		String localSumround[] = new String[0];
		String commoditySum[] = new String[0];
		String packageDes[] = new String[0];
		String setAgentName = "",
				setAgentOthers = "", setAgentId = "",
				setAgentAdd1 = "", setAgentAdd2 = "",
				setAgentFax = "", setAgentTel = "",
				 ExchangeRate = "", CurrencyName = "",
				 currencyShortName="",
				 TransportName = "",SaleTermValue = "",SaleTermName = "", destCountryId = "",currencyId="";
		int finalCount = 0, warrantyCount=0, exclusionCount=0;
		String commissionAmount = "";
		String finalCountryName = "";
		String transCountryName = "";
		String viaCountryName = "";
		String PolicyDate = "" ;
		String quotedDate = "";
		String[] saleTermName = new String[0];
		String lcNumber = "";
		String lcDate = "";
		String startingPlace = "";
		String finalPlace = "";
		String destWarStatus = "";
		String openCoverStartDate="";
		String toleranceName="";
		String[][] extraClauses = new String[0][0];
		String[][] exclusions = new String[0][0];
		String[][] warranties = new String[0][0];
		String[][] clauses = new String[0][0];
		String[][] EditExtraClauses = new String[0][0];
		String[][] EditExClauses = new String[0][0];
		String[][] EditWarClauses = new String[0][0];
		String[][] EditClauses = new String[0][0];
		String[][] openFreeText = new String[0][0];
		String getPolictDetails[][] = new String[0][0];
		String getPolNo="";
		String getCertNo="";
		String getEndtNo="";
		String consigneeDet="";
		String specialTerm="";
		String addCustName="";
		String cusCode ="",createDate="", policyNoDummy="";
		Map taxDetails;taxDetails = new HashMap();
		String[][] custInfo=new String[0][0];
		//getting values modification
		String DubaiTradeStatus="";
		DubaiTradeStatus=getDubaiTradeStatus(login, brokerBra);	
		try 
		{
			if("11".equals(pid)){
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
			}else{
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);	
			}
		    if(getPolictDetails.length>0)
			{
				getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
				getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
				getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
			}
			com.maan.premium.DAO.PremiumInputsBean premiumBean = new com.maan.premium.DAO.PremiumInputsBean();
			int countBackDays = 0;
			if(freightUser.equalsIgnoreCase("freight"))
			{
				String temp;
				temp= premiumBean.getBackDatesAllowed(login,"3");//check this method
				if(temp!=null&&temp.length()>0){
					countBackDays = Integer.parseInt(temp);
				}
			}else{
				countBackDays=0;
			}	
			finalprint dbBean = new finalprint();
			rsa.opencoverpdf.finalprint bean=new rsa.opencoverpdf.finalprint();
			if (DRAFTMODE.equalsIgnoreCase(displayMode)) 
			{
				finalBean.setCountBackDays(countBackDays);
				if("11".equals(pid))
				{
					premiumdetails = bean.checkingvalues(PolicyNo, login, QUOTENO,brokerBra,cid,"None");
					policyNoDummy=new policyInfo().getPolicyDefault(new String[]{pid,pid}, PolicyNo, brokerBra, openCoverNo, "");
				}
				else
					premiumdetails = finalBean.checkingvalues(PolicyNo, login, QUOTENO,brokerBra,cid,"None");
				tolDetails = finalBean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
				issuerName = finalBean.getIssuerName(PolicyNo,QUOTENO);
				cusCode = dbBean.getCustomerCode(PolicyNo,QUOTENO,"3");
				createDate = dbBean.getForamattedDate(PolicyNo,QUOTENO);
				currencyShortName = dbBean.getFCName(PolicyNo,"3",QUOTENO,brokerBra);
				taxDetails = dbBean.getTaxDetails(PolicyNo,"3",QUOTENO);
				
			}else if(NORMALSUP.equalsIgnoreCase(displayMode))
			{
				logger.info("comes inside this QUOTENOSINGLE BLOCK");
				premiumdetails=bean.checkingvalues(PolicyNo,login,"QUOTENOSINGLE",brokerBra,cid,SCHEDULE);
				tolDetails = bean.getToleranceDetails(PolicyNo,QUOTENO,brokerBra);
				issuerName = bean.getIssuerName(PolicyNo,QUOTENO);
				cusCode = bean.getCustomerCode(PolicyNo,QUOTENO,"11");
				createDate = bean.getForamattedDate(PolicyNo,QUOTENO);
				//placeCode = finalBean.getPlaceCodes(PolicyNo,"Schedule","11",QUOTENO);
				taxDetails = bean.getTaxDetails(PolicyNo,"11",QUOTENO);
				currencyShortName = bean.getFCName(PolicyNo,"11",QUOTENO,brokerBra);
			} 
			else 
			{
				finalBean.setCountBackDays(countBackDays);
				if("11".equals(pid))
					premiumdetails = bean.checkingvalues(PolicyNo, login,"POLICYNO",brokerBra,cid,"None");
				else
					premiumdetails = finalBean.checkingvalues(PolicyNo, login,"POLICYNO",brokerBra,cid,"None");
				tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
				issuerName = finalBean.getIssuerName(PolicyNo,"POLICYNO");
				cusCode = dbBean.getCustomerCode(PolicyNo,"POLICYNO","3");
				createDate = dbBean.getForamattedDate(PolicyNo,"POLICYNO");
				currencyShortName = dbBean.getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				taxDetails = dbBean.getTaxDetails(PolicyNo,"3","POLICYNO");
			}
			if("11".equals(pid))
				openCoverStartDate=dbBean.getOpenCoverStartDate(openCoverNo);
			HashMap AmountDetails = new HashMap();

			String checkPolicyDatas = "NODATAS";
			
			checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");

			if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
			{
				certClausesYN = (String) premiumdetails.get("certClausesYN") == null ? ""
						: (String) premiumdetails.get("certClausesYN");
				basisVal = (String) premiumdetails.get("basisVal") == null ? ""
						: (String) premiumdetails.get("basisVal");
				basisVal=basisVal.replaceAll("ACTUAL AMOUNT", "");
				sailDate = (String) premiumdetails.get("sailDate") == null ? ""
						: (String) premiumdetails.get("sailDate");// [0][2);
				commoditydetails = (HashMap) premiumdetails.get("commoditydetails");
				AmountDetails = (HashMap) premiumdetails.get("AmountDetails");
				transportId = (String) premiumdetails.get("TransportID") == null ? "0": (String) premiumdetails.get("TransportID");// [0][0]);
				warehouseOption = (String) premiumdetails.get("warehouseasWarehouseOption") == null ? "NO": (String) premiumdetails.get("warehouseasWarehouseOption");// [0][1]);
				wareFinalOpt = (String) premiumdetails.get("WareHouseFinalOption") == null ? "NO":(String) premiumdetails.get("WareHouseFinalOption");// [0][87]);
				subjectKnown = (String) premiumdetails.get("PdfSubjectKnownText") == null ? "NOTHING": (String) premiumdetails.get("PdfSubjectKnownText");// [0][91]);
				
				TransitFrom = (String) premiumdetails.get("TransitFrom") == null ? ""
						: (String) premiumdetails.get("TransitFrom");// [0][2);
				
				finalDestination = (String) premiumdetails.get("FinalDestination") == null ? ""
						: (String) premiumdetails.get("FinalDestination");// [0][3]);
				
				PolicyDate = (String) premiumdetails.get("PolicyStartDate") == null ? ""
						: (String) premiumdetails.get("PolicyStartDate");// [0][4]);
				quotedDate = (String) premiumdetails.get("QuoteGeneratedDate") == null ? "":(String)premiumdetails.get("QuoteGeneratedDate");
				//policyDate = (String) premiumdetails.get("ActualPolicyGeneratedDate") == null ? "":(String) premiumdetails.get("ActualPolicyGeneratedDate");
				policyDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "":(String) premiumdetails.get("PolicyGeneratedDate");
				approvedBy = (String) premiumdetails.get("APPROVEDBY")==null?"":(String) premiumdetails.get("APPROVEDBY");
				coverName = (String) premiumdetails.get("CoverName") == null ? ""	: (String) premiumdetails.get("CoverName");
				extraCoverId = (String) premiumdetails.get("ExtraCoverId") == null ? ""	: (String) premiumdetails.get("ExtraCoverId");
				TransportName = (String) premiumdetails.get("TransportName") == null ? "": (String) premiumdetails.get("TransportName");// [0][12]);
				CurrencyName = (String) premiumdetails.get("CurrencyName") == null ? ""
						: (String) premiumdetails.get("CurrencyName");// [0][14]);
				currencyId = (String) premiumdetails.get("currencyId") == null ? ""
						: (String) premiumdetails.get("currencyId");// [0][14]);
				
				ExchangeRate = (String) premiumdetails.get("ExchangeRate") == null ? "0"
						: (String) premiumdetails.get("ExchangeRate");// [0][15]);
				SaleTermValue = (String) premiumdetails.get("SaleTermValue") == null ? "0"
						: (String) premiumdetails.get("SaleTermValue");// [0][19]);
				SaleTermName = (String) premiumdetails.get("SaleTermName") == null ? ""
						: (String) premiumdetails.get("SaleTermName");// [0][19]);
				fromCountryId = (String) premiumdetails.get("TransitFromCountryId") == null ? "0"
						: (String) premiumdetails.get("TransitFromCountryId");// [0][21]);
				
				destCountryId = (String) premiumdetails.get("FinalDestinationCountryId") == null ? "0"
						: (String) premiumdetails
								.get("FinalDestinationCountryId");// [0][22]);
				
				QuoteNo = (String) premiumdetails.get(QUOTENO);// [0][26]);
				PolicyNo = (String) premiumdetails.get("PolicyNo")==null?"":(String) premiumdetails.get("PolicyNo");// [0][27]);
				
				carrierName = (String) premiumdetails.get("CarrierName") == null ? ""
						: (String) premiumdetails.get("CarrierName");// [0][30]);
				
				blAwbNo = (String) premiumdetails.get("BlAwbNo") == null ? ""
						: (String) premiumdetails.get("BlAwbNo");// [0][31]);
				
				blAwbDate = (String) premiumdetails.get("BlAwbDate") == null ? ""
						: (String) premiumdetails.get("BlAwbDate");// [0][32]);
				
				lcNumber = (String) premiumdetails.get("LcNumber") == null ? ""
						: (String) premiumdetails.get("LcNumber");// [0][33]);

				
				lcDate = (String) premiumdetails.get("LcDate")==null?"":(String) premiumdetails.get("LcDate");
				bankName = (String) premiumdetails.get("BankName") == null ? "": (String) premiumdetails.get("BankName");// [0][35]);
				bankName=bankName.equalsIgnoreCase("None")?"":bankName;
				
				consigneeDet = (String) premiumdetails.get("CONSIGNEE_DET") == null ? "": (String) premiumdetails.get("CONSIGNEE_DET");
				specialTerm = (String) premiumdetails.get("SPECIAL_TERM") == null ? "": (String) premiumdetails.get("SPECIAL_TERM");
				addCustName = (String) premiumdetails.get("ADD_CUST_NAME") == null ? "": (String) premiumdetails.get("ADD_CUST_NAME");
				
				setAgentOthers = (String) premiumdetails.get("SettlingAgentNameOthers") == null ? ""
						: (String) premiumdetails.get("SettlingAgentNameOthers");// [0][36]);

				setAgentId = (String) premiumdetails.get("SettlingAgentId");// [0][37]);

				setAgentName = (String) premiumdetails.get("SettlingAgentName") == null ? ""
						: (String) premiumdetails.get("SettlingAgentName");
			
				setAgentAdd1 = (String) premiumdetails.get("SettlingAgentAddressOne") == null ? ""
						: (String) premiumdetails.get("SettlingAgentAddressOne");// [0][39]);
				
				setAgentAdd2 = (String) premiumdetails.get("SettlingAgentAddressTwo") == null ? ""
						: (String) premiumdetails.get("SettlingAgentAddressTwo");// [0][41]);
				
				setAgentTel = (String) premiumdetails.get("SettlingAgentTelephoneNo") == null ? ""
						: (String) premiumdetails.get("SettlingAgentTelephoneNo");// [0][41]);
				
				setAgentFax = (String) premiumdetails.get("SettlingAgentFaxNo") == null ? "0"
						: (String) premiumdetails.get("SettlingAgentFaxNo");// [0][42]);
				
				CustomerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? ""
						: (String) premiumdetails.get("CustomerFirstName");// [0][43]);
				
				CustomerLastName = (String) premiumdetails.get("CustomerLastName") == null ? ""
						: (String) premiumdetails.get("CustomerLastName");// [0][44]);
				
				custAddress1 = (String) premiumdetails.get("CustomerAddressOne") == null ? ""
						: (String) premiumdetails.get("CustomerAddressOne");// [0][45]);
				
				CustomerCountry = (String) premiumdetails
						.get("CustomerCountry") == null ? ""
						: (String) premiumdetails.get("CustomerCountry");// [0][48]);
				
				CustomerEmirate = (String) premiumdetails.get("CustomerEmirate") == null ? ""
						: (String) premiumdetails.get("CustomerEmirate");// [0][49]);
				CustomerPoBox = (String) premiumdetails.get("CustomerPoBox") == null ? ""
						: (String) premiumdetails.get("CustomerPoBox");// [0][49]);

				//Added new to display Core Customer Information
				custInfo=getCustomerInfo((String) premiumdetails.get("CustomerId"), brokerBra);
				if(custInfo!=null && custInfo.length>0){
					if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
						CustomerFirstName=(custInfo[0][0]==null?"":custInfo[0][0])+" "+(custInfo[0][1]==null?"":custInfo[0][1]);
					}else{
						CustomerFirstName=(custInfo[0][1]==null?"":custInfo[0][1]);	
					}
					CustomerLastName="";
					custAddress1=custInfo[0][2]==null?"":custInfo[0][2];
					custAddress2=custInfo[0][3]==null?"":custInfo[0][3];
					CustomerCountry=custInfo[0][4]==null?"":custInfo[0][4];
					CustomerEmirate="";
					CustomerPoBox=custInfo[0][5]==null?"":custInfo[0][5];
				}
				//End 
				custCompany = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);

				BrokerName = (String) premiumdetails.get("BrokerName") == null ? ""	: (String) premiumdetails.get("BrokerName");// [0][50]);
				
				if(issuerName.length()>0){
					BrokerName = issuerName;
				}
				
				brokerRemarks = (String) premiumdetails.get("BrokerRemarks") == null ? "NO BROKER REMARKS CAME"
						: (String) premiumdetails.get("BrokerRemarks");// [0][83]);

				brokerCompany = (String) premiumdetails.get("BrokerCompany") == null ? "": (String) premiumdetails.get("BrokerCompany");// [0][84]);
				
				seaOption = (String) premiumdetails.get("SeaOption") == null ? "": (String) premiumdetails.get("SeaOption");// [0][94]);
				partialShip = (String) premiumdetails.get("PartialShip") == null ? "": (String) premiumdetails.get("PartialShip");// [0][95]);

				EditClauses = (String[][]) premiumdetails.get("editedClauses") == null ? EditClauses
						: (String[][]) premiumdetails.get("editedClauses");// [0][83]);
				openFreeText=(String[][])premiumdetails.get("openFreeText");
				
				EditExtraClauses = (String[][]) premiumdetails
						.get("editedExtraClauses") == null ? EditClauses
						: (String[][]) premiumdetails.get("editedExtraClauses");// [0][83]);

				EditWarClauses = (String[][]) premiumdetails
						.get("editedWarClauses") == null ? EditClauses
						: (String[][]) premiumdetails.get("editedWarClauses");// [0][83]);

				
				EditExClauses = (String[][]) premiumdetails
						.get("editedExClauses") == null ? EditClauses
						: (String[][]) premiumdetails.get("editedExClauses");// [0][83]);
				
				premium = (String) premiumdetails.get("Premium") == null ? "0"
						: (String) premiumdetails.get("Premium");
					logger.info("Premium in print4schedule royaltest..."+premium);
				excessPremium = (String) premiumdetails.get("ExcessPremium") == null ? "0"
						: (String) premiumdetails.get("ExcessPremium");

				finalCount = Integer.parseInt((String) commoditydetails.get("finalCount"));
				
				startingPlace = (String) premiumdetails.get("TransitStartingPlace") == null ? "": (String) premiumdetails.get("TransitStartingPlace");
				
				finalPlace = (String) premiumdetails.get("FinalStartingPlace") == null ? ""
						: (String) premiumdetails.get("FinalStartingPlace");

				
				destWarStatus = (String) premiumdetails.get("destinationCountryWarranties") == null ? YES
						: (String) premiumdetails
								.get("destinationCountryWarranties");

				AdminStatus = (String) premiumdetails.get("Remarks") == null ? ""
						: (String) premiumdetails.get("Remarks");
				AdminRemarks = (String) premiumdetails.get("AdminRemarks") == null ? ""
						: (String) premiumdetails.get("AdminRemarks");
				
				FragileMessage = (String) commoditydetails.get("FragileMessage");
				
				commissionAmount = (String) AmountDetails
						.get("CommissionAmount") == null ? "0"
						: (String) AmountDetails.get("CommissionAmount");
				
				totalSumIns = (String) commoditydetails.get("TotalCommoditySI") == null ? "0"
						: (String) commoditydetails.get("TotalCommoditySI");
				insuredTotal = (String) commoditydetails.get("TotalSILocal") == null ? "0"
						: (String) commoditydetails.get("TotalSILocal");

				currencyOption=(String) premiumdetails .get("PDFCurrencyStatus")==null?"NO":(String) premiumdetails .get("PDFCurrencyStatus");
				finalCountryName = (String)premiumdetails.get("FinalDestinationCountryName")==null?"":(String)premiumdetails.get("FinalDestinationCountryName");
				transCountryName = (String) premiumdetails.get("TransitCountryName")==null?"":(String) premiumdetails.get("TransitCountryName");
				viaCountryName = (String)premiumdetails.get("viaCountryName")==null?"":(String)premiumdetails.get("viaCountryName");

				commoditySumIns = new String[finalCount];
				packageDes = new String[finalCount];
				commodityName = new String[finalCount];
				commodityDes = new String[finalCount];
				saleTermName = new String[finalCount];

				
				supplier = new String[finalCount];
				invoice = new String[finalCount];

				localSumIns = new String[finalCount];
				commoditySum = new String[finalCount];
				localSumround = new String[finalCount];

				for (int i = 0; i < finalCount; i++) 
				{
					commoditySumIns[i] = (String) commoditydetails.get("CommoditySumInsured" + (i + 1)) == null ? "0"
							: (String) commoditydetails
									.get("CommoditySumInsured" + (i + 1));// [0][8]);
					commodityDes[i] = (String) commoditydetails.get("CommodityDescription" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("CommodityDescription" + (i + 1));// [0][9]);
					
					packageDes[i] = (String) commoditydetails.get("PackageDescription_arr" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("PackageDescription_arr" + (i + 1));// [i][65]);
					
					commodityName[i] = (String) commoditydetails.get("CommodityName_arr" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("CommodityName_arr"+ (i + 1));// [0][13]);
					
					saleTermName[i] = (String) commoditydetails.get("SaleTermName_arr" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("SaleTermName_arr"+ (i + 1));// [0][20]);
					
					supplier[i] = (String) commoditydetails.get("Supplier_arr" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("Supplier_arr"+ (i + 1));// [0][85]);
					invoice[i] = (String) commoditydetails.get("Invoice_arr" + (i + 1)) == null ? ""
							: (String) commoditydetails.get("Invoice_arr"+ (i + 1));// [0][86]);
					localSumIns[i] = (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1)) == null ? "0"
							: (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1));// [0][25]);

					
					double salePercent = 0.0;
					double sumLocal = 0.0;
					
					double sumLocalSale = 0.0;
					double toleranceval = 0.0;
					double tolValuePer = 0.0;
					double commoditySI = 0.0;
					double commoditySISale = 0.0;
					if(tolDetails.length>0)
					{
						toleranceName = tolDetails[i][0]==null?"0":tolDetails[i][0];
						toleranceval = Double.parseDouble(tolDetails[i][1]==null?"0":tolDetails[i][1]);
						tolValuePer = Double.parseDouble(tolDetails[0][2]==null?"0":tolDetails[0][2]);
					}
					
					salePercent = Double.parseDouble(SaleTermValue);
					sumLocal = Double.parseDouble(localSumIns[i]);
					sumLocalSale = sumLocal + (sumLocal * (salePercent / 100));
					sumLocalSale = sumLocalSale + toleranceval;
					commoditySI = Double.parseDouble(commoditySumIns[i]);
					commoditySISale = commoditySI	+ (commoditySI * (salePercent / 100));
					commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
					localSumround[i] = pdis.decimalFormat(sumLocalSale, decimalDigit);
					commoditySum[i] = pdis.decimalFormat(commoditySISale, decimalDigit);
				}
				exclusions = (String[][]) commoditydetails.get("exclusionsDesc");
				warranties = (String[][]) commoditydetails.get("warrantyDesc");
				clauses = (String[][]) commoditydetails.get("clausesDesc");
				extraClauses = (String[][]) commoditydetails.get("extraClausesDesc") == null ? extraClauses
						: (String[][]) commoditydetails.get("extraClausesDesc");
			} 
		}
		catch (Exception e) 
		{
			logger.info("exception in print4" + e.toString());logger.debug(e);
		}
		logger.info(" this is royal test new pdf from inits values...displayMode..."+displayMode);
		if (!(DRAFTMODE.equalsIgnoreCase(displayMode)) && !(NORMALSUP.equalsIgnoreCase(displayMode))) 
		{
			finalBean.updateCommission(broBra,pid,Policy_No,pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit),taxPercent);
		}
		
		Font font,fontHead1,fontHead2,fontHead3,fontText,fontText1,fontHeadSP,fontHeadUl;
		try
		{
			Document document = null;
			document = new Document(PageSize.A4, 50, 50, 50, 60);
			
			try 
			{
				if ("ORIGINAL COPY".equalsIgnoreCase(displayText)){ 
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else if ((displayText.trim()).length()<=0) {
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else if ("COPY".equalsIgnoreCase(displayText)){ 
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else if ("DRAFT".equalsIgnoreCase(displayText)){ 
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else if ("INVALID POLICY".equalsIgnoreCase(displayText)){ 
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else if ("INVALID DRAFT".equalsIgnoreCase(displayText)) {
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}else{
					writer = PdfWriter.getInstance(document,new FileOutputStream(getFilePath()));
				}
			} 
			catch (Exception eb) 
			{
				logger.info(eb);
			}
			document.open();
			//Added by sathish for Emirates PDF==>Start
			
			HeaderFooterImage pageWater;
			pageWater = new HeaderFooterImage();pageWater.onOpenDocument(writer, document);
			pageWater.setImagePath(imagePath);
			pageWater.setFooterImagePath(footerImagePath);
			pageWater.setCreateDate(createDate);
			//if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
				pageWater.setDisplayText(displayText);						
			/*}else{
				pageWater.setDisplayText("");
			}*/
			pageWater.setCols(cols);
			
//			For KSA
			String polGenDate = "";
			String polGenTime = "";
			if(!displayMode.equalsIgnoreCase(DRAFTMODE)&&!"NoDate".equalsIgnoreCase(policyDate))
			{
				polGenDate = policyDate.substring(0,policyDate.indexOf(' '));
				polGenTime = policyDate.substring((policyDate.indexOf(' '))+1,policyDate.length());
			}
			
			polGenDate = polGenDate+" Time: "+polGenTime;
			pageWater.setPolicyOn(polGenDate);
			pageWater.setPolicyAt(place);
			pageWater.setEntered(BrokerName);
			pageWater.setApproved(approvedBy);
			pageWater.setStampStatus(stampStatus);
			pageWater.setIssuerName(issuerName);
			pageWater.setBraAddress(braAddress);
			pageWater.setBrokerCompany(brokerCompany);
			pageWater.setCid(cid);
			pageWater.setUsrModeController(usrModeController);
			pageWater.setSignedImage(signImage);
			pageWater.setStampImage(stampImage);
			pageWater.setFontPath(fontPath);
			writer.setPageEvent(pageWater);
			
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
			
			String DubaiTradeAgentName="";
			DubaiTradeAgentName=getDubaiTradeAgentName(destCountryId, brokerBra);	
			
			if("3".equals(pid))
			{
				tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("Marine Cargo Insurance Policy", fontHeadBold, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Emirates Insurance Co.  hereby agrees in consideration of the payment to us by or on behalf of the Assured of the premium specified in the Schedule to insure against loss, damage or expense in the manner hereinafter provided.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\nSchedule", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}else if("11".equals(pid))
			{
				tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("Marine Cargo", fontTextNormal, Rectangle.NO_BORDER,8, 2);
				if(DRAFTMODE.equalsIgnoreCase(displayMode)){
					tableCreation.insertCell("Quote No. "+QuoteNo, fontTextNormal, Rectangle.NO_BORDER,8, 2);
				}else if(NORMALSUP.equalsIgnoreCase(displayMode)){
					tableCreation.insertCell("Insurance Certificate No. "+getCertNo+"/"+verNo, fontTextNormal, Rectangle.NO_BORDER,8, 2);
				}else{
					tableCreation.insertCell("Insurance Certificate No. "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,8, 2);
				}
				tableCreation.insertCell("Policy Period always open from: "+openCoverStartDate, fontTextNormal, Rectangle.NO_BORDER,8, 2);
//				tableCreation.insertCell((bankName.length()>0?bankName+", ":"")+(custCompany.length()>0?custCompany:(CustomerFirstName+CustomerLastName)), fontTextNormal, Rectangle.NO_BORDER,4, 0);
				if(YES.equalsIgnoreCase(bankAssOpt) && "NO".equalsIgnoreCase(bankerOption))
				{
					if(custCompany==null || custCompany.equalsIgnoreCase(""))
					{
						
						tableCreation.insertCell("Name of Assured\t\t\t\t\t\t\t\t\t\t"+(bankName.length()>0?bankName+" / ":"")+CustomerFirstName+CustomerLastName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
					else
					{
						tableCreation.insertCell("Name of Assured\t\t\t\t\t\t\t\t\t\t"+(bankName.length()>0?bankName+" / ":"")+custCompany+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
				}
				else if(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankAssOpt) )
				{
					tableCreation.insertCell("Name of Assured\t\t\t\t\t\t\t\t\t\t"+bankName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}
				else
				{
					if(custCompany==null || custCompany.equalsIgnoreCase(""))
					{
						tableCreation.insertCell("Name of Assured\t\t\t\t\t\t\t\t\t\t"+CustomerFirstName+CustomerLastName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
					else
					{
						tableCreation.insertCell("Name of Assured\t\t\t\t\t\t\t\t\t\t"+custCompany+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
				}
				tableCreation.insertCell("Date:\t\t\t\t\t\t\t\t\t\t"+PolicyDate, fontTextNormal, Rectangle.NO_BORDER,8, 1);
				if(DRAFTMODE.equalsIgnoreCase(displayMode)){
					tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+policyNoDummy.substring(0,policyNoDummy.lastIndexOf("-")-2)+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}else{
					tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+getPolNo+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			
			if(DRAFTMODE.equalsIgnoreCase(displayMode))
			{
				/*if("11".equals(pid))
				{		
					tableCreation.insertCell("OPEN COVER NO", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(openCoverNo,fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}*/
				if("3".equals(pid))
					tableCreation.insertCell("Quote Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			}
			else
			{
				/*if("11".equals(pid))
				{		
					tableCreation.insertCell("OPEN COVER NO", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(openCoverNo,fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}*/
				
				if("3".equals(pid))
				{
					tableCreation.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					
				}/*else if("11".equals(pid))
				{
					tableCreation.insertCell("CERTIFICATE NUMBER", fontTextNormal, Rectangle.NO_BORDER,3, 0);	
				}*/
			}
			logger.info("royal pdf test for multiple PolicyNo..."+PolicyNo);
			logger.info("royal pdf test for multiple QuoteNo..."+QuoteNo);
			if("3".equals(pid))
			{
				if(DRAFTMODE.equalsIgnoreCase(displayMode)){
					tableCreation.insertCell(QuoteNo+"\t\t\t\t\t\t\t\t\t\t\t\t\t         DATE \t\t\t\t\t\t\t\t"+PolicyDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}else if(NORMALSUP.equalsIgnoreCase(displayMode)){
					tableCreation.insertCell((PolicyNo.length()<=0?QuoteNo:PolicyNo+"/"+verNo)+"\t\t\t\t\t\t\t\t\t\t         DATE \t\t\t\t\t\t\t\t"+PolicyDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}else{
					tableCreation.insertCell((PolicyNo.length()<=0?QuoteNo:PolicyNo)+"\t\t\t\t\t\t\t\t\t\t\t\t         DATE \t\t\t\t\t\t\t\t"+PolicyDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Name of Assured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				if(YES.equalsIgnoreCase(bankAssOpt) && "NO".equalsIgnoreCase(bankerOption))
				{
					if(custCompany==null || custCompany.equalsIgnoreCase(""))
					{
						
						tableCreation.insertCell((bankName.length()>0?bankName+" / ":"")+CustomerFirstName+CustomerLastName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					}
					else
					{
						tableCreation.insertCell((bankName.length()>0?bankName+" / ":"")+custCompany+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					}
				}
				else if(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankAssOpt) )
				{
					tableCreation.insertCell(bankName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}
				else
				{
					if(custCompany==null || custCompany.equalsIgnoreCase(""))
					{
						tableCreation.insertCell(CustomerFirstName+CustomerLastName+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					}
					else
					{
						tableCreation.insertCell(custCompany+"  "+addCustName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					}
				}
				tableCreation.insertCell("", fontTextNormal, 0, 3, 0);
                tableCreation.insertCell((new StringBuilder(String.valueOf(custAddress1.trim().length() <= 0 ? "" : ((Object) ((new StringBuilder(String.valueOf(custAddress1))).append(", ").toString()))))).append(custAddress2.trim().length() <= 0 ? "" : (new StringBuilder(String.valueOf(custAddress2))).append(", ").toString()).append(CustomerPoBox.trim().length() <= 0 ? "" : (new StringBuilder("P.O. Box : "+String.valueOf(CustomerPoBox))).append(", ").toString()).append(CustomerEmirate.trim().length() <= 0 ? "" : (new StringBuilder(String.valueOf(CustomerEmirate))).append(", ").toString()).append(CustomerCountry.trim().length() <= 0 ? "" : (new StringBuilder(String.valueOf(CustomerCountry))).toString()).toString(), fontTextNormal, 0, 5, 0);
			}
			
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Voyage", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			if(!"".equalsIgnoreCase(TransitFrom.trim()))
			{
				TransitFrom=TransitFrom+", ";
			}
			finalDestination=(finalDestination==null||"Others".equalsIgnoreCase(finalDestination))?"":finalDestination;
			if(!"".equalsIgnoreCase(finalDestination.trim()))
			{
				finalDestination=finalDestination+",";
			}
			tableCreation.insertCell("From: "+TransitFrom+transCountryName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("To: "+finalDestination+finalCountryName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Conveyance", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell((carrierName.trim().length()>0?carrierName+", ":"")+ TransportName + (sailDate.trim().length()>0?", Sail Date: "+sailDate:""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
			if(blAwbNo.trim().length()>0){
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("AWB / BOL NO.", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(blAwbNo+(blAwbDate.length()>0?("\t\t\t\t\t\tDate: "+blAwbDate):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			
			tableCreation.insertCell("Subject-Matter Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			if(finalCount==1){
				StringBuffer goods = new StringBuffer();
				goods.append(commodityDes[0]);
				goods.append(" ");
				if(invoice[0]!=null&&invoice[0].length()>0){
					goods.append("INV# ");
					goods.append(invoice[0]);
				}
				tableCreation.insertCell(goods.toString(), fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}else{
				tableCreation.insertCell("As per list attached", fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			boolean saleFlag=false;
			boolean tolFlag=false;
			if(SaleTermName.indexOf("+0%")!=-1)
				SaleTermName=SaleTermName.substring(0, SaleTermName.indexOf("+0%"));
			if((SaleTermName.length()>0 && !SaleTermName.equalsIgnoreCase("Declared Value")))
				saleFlag=true;
			if((!toleranceName.equalsIgnoreCase("NONE") && toleranceName.trim().length()>0))
				tolFlag=true;
			if(YES.equalsIgnoreCase(currencyOption)){
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Basis of Valuation", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				//totalSumIns=String.valueOf(Double.parseDouble(totalSumIns)/Double.parseDouble(ExchangeRate));
				totalSumIns=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(totalSumIns, currencyId, brokerBra)), decimalDigit);
				tableCreation.insertCell(currencyShortName+" "+totalSumIns+(!"0".equals(SaleTermValue)?(" + "+SaleTermValue+"%"):"")+(tolFlag?(" + "+toleranceName):"")+(saleFlag || tolFlag?"; ":"")+(saleFlag?(SaleTermName):"")+(tolFlag?((saleFlag?" + ":"")+toleranceName):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Sum Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				insuredTotal=String.valueOf(Double.parseDouble(insuredTotal)/Double.parseDouble(ExchangeRate));
				insuredTotal=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(insuredTotal,currencyId, brokerBra)), decimalDigit);
				tableCreation.insertCell((basisVal.length()>0?(basisVal+" "):"")+currencyShortName +" "+insuredTotal, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}else
			{
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Basis of Valuation", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				totalSumIns=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(totalSumIns, currencyId, brokerBra)), decimalDigit);
				tableCreation.insertCell(currencyShortName+" "+totalSumIns+(!"0".equals(SaleTermValue)?(" + "+SaleTermValue+"%"):"")+(tolFlag?(" + "+toleranceName):"")+(saleFlag || tolFlag?"; ":"")+(saleFlag?(SaleTermName):"")+(tolFlag?((saleFlag?" + ":"")+toleranceName):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell("Exchange Rate 1 "+currencyShortName+" = "+ExchangeRate+" "+currencyType, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				System.out.println("totalSumIns=======>"+totalSumIns);
				tableCreation.insertCell("Sum Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				insuredTotal=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(insuredTotal,"1", brokerBra)), decimalDigit);
				tableCreation.insertCell((basisVal.length()>0?(basisVal+" "):"")+currencyType +" "+insuredTotal, fontTextNormal, Rectangle.NO_BORDER,5, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			//Premium table
			if(bankName.length()>0 && !bankName.equals("NONE"))
			{
				tableCreation.insertCell("Bank Name ", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(bankName+(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE")?(",\t\tL/C NO. "+lcNumber+",\t\tL/C DATE. "+lcDate):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}else if(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE"))
			{
				tableCreation.insertCell("L/C No. ", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(lcNumber, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			if(consigneeDet.trim().length()>0 )
			{
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Consignee Details", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(consigneeDet, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			if(specialTerm.trim().length()>0 )
			{
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Terms & Condition", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(specialTerm, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			/*if(blAwbNo.trim().length()>0 && !blAwbNo.equals("0"))
			{
				tableCreation.insertCell("BL/ AWB No. ", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(blAwbNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}*/
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("Premium", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			if(YES.equalsIgnoreCase(PremiumYes.trim())){
				if(YES.equalsIgnoreCase(currencyOption)){
					String netPremium=new PremiumInputsBean().getRoundedValue(((Double.parseDouble(premium)+Double.parseDouble(excessPremium))/Double.parseDouble(ExchangeRate))+"", currencyId, brokerBra);
					tableCreation.insertCell(currencyShortName+" "+pdis.decimalFormat(Double.parseDouble(netPremium), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}else {
					tableCreation.insertCell(currencyType+" "+pdis.decimalFormat((Double.parseDouble(premium)+Double.parseDouble(excessPremium)), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,5, 0);
				}
			}else{
				tableCreation.insertCell("AS AGREED", fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
			tableCreation.insertCell("", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			System.out.println("Product Id===>"+pid);
			ArrayList<String> list = new ArrayList<String>();
			ArrayList warrenties = new ArrayList();
			ArrayList excess = new ArrayList();
			int prc = 0;
			if("11".equals(pid))//one of policy
			{
				if("Y".equalsIgnoreCase(certClausesYN)){
					if (EditClauses.length > 0)
					{
						for (int a22 = 0; a22 < EditClauses.length; a22++) 
						{
							EditClauses[a22][1]=EditClauses[a22][1]==null?"":EditClauses[a22][1];
							if(EditClauses[a22][1].length()>0)
							{
								list.add(EditClauses[a22][1]);
							}
						}
					}
					if (clauses.length > 0)
					{
						for (int a1 = 0; a1 < clauses.length; a1++) 
						{
							clauses[a1][0]=clauses[a1][0]==null?"":clauses[a1][0];
							if(clauses.length>0)
							{
								list.add(clauses[a1][0]);
							}
						}
					}
					if (openFreeText.length > 0)
					{
						for (int a1 = 0; a1 < openFreeText.length; a1++) 
						{
							openFreeText[a1][0]=openFreeText[a1][0]==null?"":openFreeText[a1][0];
							if(openFreeText[a1][0].length()>0)
							{
								list.add(openFreeText[a1][0]);
							}
						}
					}
					if (EditExtraClauses.length > 0)
					{
						for (int a222 = 0; a222 < EditExtraClauses.length; a222++) 
						{
							EditExtraClauses[a222][1]=EditExtraClauses[a222][1]==null?"":EditExtraClauses[a222][1];
							if(EditExtraClauses[a222][1].length()>0)
							{
								list.add(EditExtraClauses[a222][1]);
							}
						}
					}
					if (extraClauses.length > 0)
					{
						for (int a2 = 0; a2 < extraClauses.length; a2++) 
						{
							extraClauses[a2][0]=extraClauses[a2][0]==null?"":extraClauses[a2][0];
							if(extraClauses[a2][0].length()>0)
							{
								list.add(extraClauses[a2][0]);
							}
						}
					}
					if(!list.isEmpty())
					{
						tableCreation.insertCell("Clauses, Endorsements, Special Conditions and Warranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
						tableCreation1.setTable(8);
						prc=list.size();
						int prcLeftLen = prc/2;
						for(int pl=0;pl<prcLeftLen;pl++)
						{
							tableCreation1.insertCell((String)list.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
						}
						tableCreation2.setTable(8);
						for(int pl=prcLeftLen;pl<prc;pl++)
						{
							tableCreation2.insertCell((String)list.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
						}
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,4, 0);
						tableCreation.insertCell(tableCreation2.getTable(),Rectangle.BOX,4, 0);
						/*for (int i = 0; i < list.size(); i++) 
						{
							tableCreation1.insertCell(list.get(i), fontTextNormal, Rectangle.NO_BORDER,8, 0);
						}*/
					}
					prc=0;
					if (EditExClauses.length > 0)
					{
						tableCreation1.setTable(8);
						tableCreation1.insertCell("Exclusions", fontHeadBold, Rectangle.NO_BORDER,8, 0);
						for (int a333 = 0; a333 < EditExClauses.length; a333++) 
						{
							EditExClauses[a333][1]=EditExClauses[a333][1]==null?"":EditExClauses[a333][1];
							if(EditExClauses[a333][1].trim().length()>0)
							{
								tableCreation1.insertCell((EditExClauses[a333][1]==null?"":EditExClauses[a333][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								exclusionCount++;
							}
						}
						if (extraClauses.length <= 0 && EditExtraClauses.length <= 0)
						{
							tableCreation1.insertCell("WAR & SRCC Cover Excluded", fontTextNormal, Rectangle.NO_BORDER,8, 0);
							exclusionCount++;
						}
						if(exclusionCount>0){
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
						}
					}
					if (exclusions.length >= 0)
					{
						tableCreation1.setTable(8);
						tableCreation1.insertCell("Exclusions", fontHeadBold, Rectangle.NO_BORDER,8, 0);
						for (int a3 = 0; a3 < exclusions.length; a3++) 
						{
							exclusions[a3][0]=exclusions[a3][0]==null?"":exclusions[a3][0];
							if(exclusions[a3][0].length()>0)
							{
								tableCreation1.insertCell((exclusions[a3][0]==null?"":exclusions[a3][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								exclusionCount++;
							}
						}
						if (extraClauses.length <= 0 && EditExtraClauses.length <= 0)
						{
							tableCreation1.insertCell("WAR & SRCC Cover Excluded", fontTextNormal, Rectangle.NO_BORDER,8, 0);
							exclusionCount++;
						}
						if(exclusionCount>0){
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
						}
					}
					
					if (YES.equalsIgnoreCase(wareFinalOpt)&& YES.equalsIgnoreCase(warehouseOption)) 
					{
						warrenties.add((prc++),"Cover from Warehouse to Warehouse");
					}
					if (("NO".equalsIgnoreCase(wareFinalOpt)&& "Any Port".equalsIgnoreCase(finalPlace) && finalDestination.length() > 2)&& "NO".equalsIgnoreCase(destWarStatus)) 
					{
						warrenties.add((prc++),"No risk after port of discharge");
					}
					if (!("NO BROKER REMARKS CAME".equalsIgnoreCase(brokerRemarks))) 
					{
						warrenties.add((prc++),brokerRemarks);
					}
					if ("admin".equalsIgnoreCase(AdminStatus)) 
					{
						if ("NoDate".equalsIgnoreCase(policyDate)) 
						{
							if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown))) 
							{
								warrenties.add((prc++),subjectKnown);
							}
						} 
						else 
						{
				
							if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown)))
							{				
								warrenties.add((prc++),subjectKnown);
							}
						}
					} 
					else
					{
						warrenties.add((prc++),getPolicysFreshBackDesc(brokerBra,PolicyNo,QuoteNo));
					}
					if ((seaOption.trim()).length()>0) 
					{
						if ("LCL".equalsIgnoreCase(seaOption)) 
						{
							warrenties.add((prc++),"Warranted cargo's are in less than container load only");
						}
						else if ("FCL".equalsIgnoreCase(seaOption))
						{
							warrenties.add((prc++),"Warranted cargo's are in full container load only");
						}
					}
					if ((partialShip.trim()).length()>0&&"Y".equalsIgnoreCase(partialShip)) 
					{
						warrenties.add((prc++),"Partial shipments are allowed");
					}
					/*if(!(AdminRemarks == null||"".equalsIgnoreCase(AdminRemarks))) 
					{
						warrenties.add((prc++),AdminRemarks);
					}*/
					//Warrenties
					if (EditWarClauses.length > 0)
					{
						tableCreation1.setTable(8);
						tableCreation1.insertCell("Warranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
						for (int a3334 = 0; a3334 < EditWarClauses.length; a3334++) 
						{
							EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
							if(EditWarClauses[a3334][1].length()>0)
							{
							EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
							if(EditWarClauses[a3334][1].trim().length()>0)
							{
								tableCreation1.insertCell((EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								warrantyCount++;
							}
							}
						}
						for(int pl=0;pl<prc;pl++)
						{
							if(((String)warrenties.get(pl)).trim().length()>0)
							{
								tableCreation1.insertCell((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								warrantyCount++;
							}
						}
						if(warrantyCount>0){
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
						}
					}
					if (warranties.length > 0)
					{
						tableCreation1.setTable(8);
						tableCreation1.insertCell("Warranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
						for (int a4 = 0; a4 < warranties.length; a4++) 
						{
							warranties[a4][0]=warranties[a4][0]==null?"":warranties[a4][0];
							if(warranties[a4][0].length()>0)
							{
								tableCreation1.insertCell((warranties[a4][0]==null?"":warranties[a4][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								warrantyCount++;
							}
						}
						for(int pl=0;pl<prc;pl++)
						{
							if(((String)warrenties.get(pl)).trim().length()>0)
							{
								tableCreation1.insertCell((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
								warrantyCount++;
							}
						}
						if(warrantyCount>0){
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
						}
					}
					//Excess
					prc=0;
					if ("displayFragileMessage".equalsIgnoreCase(FragileMessage)) 
					{
						excess.add((prc++),"0.50% of the Sum Insured subject to minimum of US$ 250/- Each and Every Claim for Fragile Commodity");
					}
					int index=0;
					String comExcess[][] = new String[0][0];
					if(DRAFTMODE.equalsIgnoreCase(displayMode)){
						comExcess = finalBean.getComExcessPre(QuoteNo,displayMode, openCoverNo);
					}else if("NormalSupplement".equalsIgnoreCase(displayMode)){
						comExcess = finalBean.getComExcessPre(QuoteNo,displayMode, openCoverNo);
					}else{
						comExcess = finalBean.getComExcessPre(PolicyNo,displayMode, openCoverNo);
					}
					if(comExcess.length>1)
					{
						tableCreation1.setTable(4);
						tableCreation1.insertCell("Excess", fontHeadBold, Rectangle.NO_BORDER,4, 0);
						tableCreation1.insertCell("Claims, if any, are payable subject to an excess for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
						tableCreation1.insertCell("S.NO", fontTextNormal, Rectangle.BOX, 1, 2);
						tableCreation1.insertCell("Subject-Matter Insured", fontTextNormal, Rectangle.BOX, 1, 2);
						tableCreation1.insertCell("Excess", fontTextNormal, Rectangle.BOX, 1, 2);
						tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX, 1, 0);
						for(int e=0;e<comExcess.length;e++)
						{
							String temp = comExcess[e][0]==null?"0":comExcess[e][0];
							if(!"0".equals(temp))
							{
								tableCreation1.insertCell(String.valueOf(++index), fontTextNormal, Rectangle.BOX, 1, 2);
								tableCreation1.insertCell(comExcess[e][1], fontTextNormal, Rectangle.BOX, 1, 0);
								tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(temp), decimalDigit), fontTextNormal, Rectangle.BOX, 1, 1);
								//tableCreation1.insertCell(comExcess[e][2], fontTextNormal, Rectangle.BOX, 1, 0);
								if(YES.equalsIgnoreCase(excessOption)){
									tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.BOX, 1, 0);
									}else{
									tableCreation1.insertCell(currencyType, fontTextNormal, Rectangle.BOX, 1, 0);	
									}
							}
						}
					}else if(comExcess.length>0)
					{
						tableCreation1.setTable(4);
						for(int e=0;e<comExcess.length;e++)
						{
							String temp = comExcess[e][0]==null?"0":comExcess[e][0];
							if(!"0".equals(temp))//&&eccount<=1)
							{
								tableCreation1.insertCell("Excess", fontHeadBold, Rectangle.NO_BORDER,4, 0);
								//tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+comExcess[e][2]+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
								if(YES.equalsIgnoreCase(excessOption)){
									tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+currencyShortName+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
									}else {
									tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+currencyType+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
								}
								++index;
							}
						}
					}
					if(index>0){
						tableCreation1.getTable().setWidths(new float[] {10f, 30f, 20f, 10f});
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
				}else{
					tableCreation.insertCell("Clauses, Endorsements, Special Conditions and Warranties as per Open Cover", fontHeadBold, Rectangle.NO_BORDER,8, 0);
				}
			}else
			{
				if (EditClauses.length > 0)
				{
					for (int a22 = 0; a22 < EditClauses.length; a22++) 
					{
						EditClauses[a22][1]=EditClauses[a22][1]==null?"":EditClauses[a22][1];
						if(EditClauses[a22][1].length()>0)
						{
							list.add(EditClauses[a22][1]);
						}
					}
				}
				if (clauses.length > 0)
				{
					for (int a1 = 0; a1 < clauses.length; a1++) 
					{
						clauses[a1][0]=clauses[a1][0]==null?"":clauses[a1][0];
						if(clauses.length>0)
						{
							list.add(clauses[a1][0]);
						}
					}
				}
				if (EditExtraClauses.length > 0)
				{
					for (int a222 = 0; a222 < EditExtraClauses.length; a222++) 
					{
						EditExtraClauses[a222][1]=EditExtraClauses[a222][1]==null?"":EditExtraClauses[a222][1];
						if(EditExtraClauses[a222][1].length()>0)
						{
							list.add(EditExtraClauses[a222][1]);
						}
					}
				}
				if (extraClauses.length > 0)
				{
					for (int a2 = 0; a2 < extraClauses.length; a2++) 
					{
						extraClauses[a2][0]=extraClauses[a2][0]==null?"":extraClauses[a2][0];
						if(extraClauses[a2][0].length()>0)
						{
							list.add(extraClauses[a2][0]);
						}
					}
				}
				
				if(!list.isEmpty())
				{
					tableCreation.insertCell("Clauses, Endorsements, Special Conditions and Warranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					prc=list.size();
					int prcLeftLen = prc/2;
					for(int pl=0;pl<prcLeftLen;pl++)
					{
						tableCreation1.insertCell((String)list.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
					tableCreation2.setTable(8);
					for(int pl=prcLeftLen;pl<prc;pl++)
					{
						tableCreation2.insertCell((String)list.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,4, 0);
					tableCreation.insertCell(tableCreation2.getTable(),Rectangle.BOX,4, 0);
					/*for (int i = 0; i < list.size(); i++) 
					{
						tableCreation1.insertCell(list.get(i), fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}*/
				}
				prc=0;
				if (EditExClauses.length > 0)
				{
					tableCreation1.setTable(8);
					tableCreation1.insertCell("Exclusions", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					for (int a333 = 0; a333 < EditExClauses.length; a333++) 
					{
						EditExClauses[a333][1]=EditExClauses[a333][1]==null?"":EditExClauses[a333][1];
						if(EditExClauses[a333][1].trim().length()>0)
						{
							tableCreation1.insertCell((EditExClauses[a333][1]==null?"":EditExClauses[a333][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							exclusionCount++;
						}
					}
					if (extraClauses.length <= 0 && EditExtraClauses.length <= 0)
					{
						tableCreation1.insertCell("WAR & SRCC Cover Excluded", fontTextNormal, Rectangle.NO_BORDER,8, 0);
						exclusionCount++;
					}
					if(exclusionCount>0){
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
				}
				if (exclusions.length > 0)
				{
					tableCreation1.setTable(8);
					tableCreation1.insertCell("Exclusions", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					for (int a3 = 0; a3 < exclusions.length; a3++) 
					{
						exclusions[a3][0]=exclusions[a3][0]==null?"":exclusions[a3][0];
						if(exclusions[a3][0].length()>0)
						{
							tableCreation1.insertCell((exclusions[a3][0]==null?"":exclusions[a3][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							exclusionCount++;
						}
					}
					if (extraClauses.length <= 0 && EditExtraClauses.length <= 0)
					{
						tableCreation1.insertCell("WAR & SRCC Cover Excluded", fontTextNormal, Rectangle.NO_BORDER,8, 0);
						exclusionCount++;
					}
					if(exclusionCount>0){
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
				}
				
				if (YES.equalsIgnoreCase(wareFinalOpt)&& YES.equalsIgnoreCase(warehouseOption)) 
				{
					warrenties.add((prc++),"Cover from Warehouse to Warehouse");
				}
				if (("NO".equalsIgnoreCase(wareFinalOpt)&& "Any Port".equalsIgnoreCase(finalPlace) && finalDestination.length() > 2)&& "NO".equalsIgnoreCase(destWarStatus)) 
				{
					warrenties.add((prc++),"No risk after port of discharge");
				}
				if (!("NO BROKER REMARKS CAME".equalsIgnoreCase(brokerRemarks))) 
				{
					warrenties.add((prc++),brokerRemarks);
				}
				if ("admin".equalsIgnoreCase(AdminStatus)) 
				{
					if ("NoDate".equalsIgnoreCase(policyDate)) 
					{
						if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown))) 
						{
							warrenties.add((prc++),subjectKnown);
						}
					} 
					else 
					{
			
						if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown)))
						{				
							warrenties.add((prc++),subjectKnown);
						}
					}
				} 
				else
				{
					warrenties.add((prc++),getPolicysFreshBackDesc(brokerBra,PolicyNo,QuoteNo));
				}
				if ((seaOption.trim()).length()>0) 
				{
					if ("LCL".equalsIgnoreCase(seaOption)) 
					{
						warrenties.add((prc++),"Warranted cargo's are in less than container load only");
					}
					else if ("FCL".equalsIgnoreCase(seaOption))
					{
						warrenties.add((prc++),"Warranted cargo's are in full container load only");
					}
				}
				if ((partialShip.trim()).length()>0&&"Y".equalsIgnoreCase(partialShip)) 
				{
					warrenties.add((prc++),"Partial shipments are allowed");
				}
				/*if(!(AdminRemarks == null||"".equalsIgnoreCase(AdminRemarks))) 
				{
					warrenties.add((prc++),AdminRemarks);
				}*/
				//Warrenties
				if (EditWarClauses.length > 0)
				{
					tableCreation1.setTable(8);
					tableCreation1.insertCell("\nWarranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					for (int a3334 = 0; a3334 < EditWarClauses.length; a3334++) 
					{
						EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
						if(EditWarClauses[a3334][1].length()>0)
						{
						EditWarClauses[a3334][1]=EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1];
						if(EditWarClauses[a3334][1].trim().length()>0)
						{
							tableCreation1.insertCell((EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							warrantyCount++;
						}
						}
					}
					for(int pl=0;pl<prc;pl++)
					{
						if(((String)warrenties.get(pl)).trim().length()>0)
						{
							tableCreation1.insertCell((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							warrantyCount++;
						}
					}
					if(warrantyCount>0){
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
				}
				if (warranties.length > 0)
				{
					tableCreation1.setTable(8);
					tableCreation1.insertCell("\nWarranties", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					for (int a4 = 0; a4 < warranties.length; a4++) 
					{
						warranties[a4][0]=warranties[a4][0]==null?"":warranties[a4][0];
						if(warranties[a4][0].length()>0)
						{
							tableCreation1.insertCell((warranties[a4][0]==null?"":warranties[a4][0]), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							warrantyCount++;
						}
					}
					for(int pl=0;pl<prc;pl++)
					{
						if(((String)warrenties.get(pl)).trim().length()>0)
						{
							tableCreation1.insertCell((String)warrenties.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
							warrantyCount++;
						}
					}
					if(warrantyCount>0){
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
				}
				//Excess
				prc=0;
				if ("displayFragileMessage".equalsIgnoreCase(FragileMessage)) 
				{
					excess.add((prc++),"0.50% of the Sum Insured subject to minimum of US$ 250/- Each and Every Claim for FRAGILE Commodity");
				}
			//	if((String)premiumdetails.get("deductible")!=null && premiumdetails.get("deductible").toString().indexOf("Y")!=-1)
			//	{
					int index=0;
					String comExcess[][] = new String[0][0];
					if(DRAFTMODE.equalsIgnoreCase(displayMode)){
						comExcess = finalBean.getComExcessPre(QuoteNo,displayMode);
					}else{
						comExcess = finalBean.getComExcessPre(PolicyNo,displayMode);
					}
					if(comExcess.length>1)
					{
						tableCreation1.setTable(3);
						tableCreation1.insertCell("Excess", fontHeadBold, Rectangle.NO_BORDER,3, 0);
						tableCreation1.insertCell("Claims, if any, are payable subject to an excess for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 3, 0);
						tableCreation1.insertCell("S.No", fontTextNormal, Rectangle.BOX, 1, 2);
						tableCreation1.insertCell("Subject-Matter Insured", fontTextNormal, Rectangle.BOX, 1, 2);
						//tableCreation1.insertCell("EXCESS ("+currencyType+")", fontTextNormal, Rectangle.BOX, 1, 2);
						if(YES.equalsIgnoreCase(excessOption)){
							tableCreation1.insertCell("Excess ("+currencyShortName+")", fontTextNormal, Rectangle.BOX, 1, 2);
							}else{
							tableCreation1.insertCell("Excess ("+currencyType+")", fontTextNormal, Rectangle.BOX, 1, 2);	
							}
						for(int e=0;e<comExcess.length;e++)
						{
							String temp = comExcess[e][0]==null?"0":comExcess[e][0];
							if(!"0".equals(temp))
							{
								tableCreation1.insertCell(String.valueOf(++index), fontTextNormal, Rectangle.BOX, 1, 2);
								tableCreation1.insertCell(comExcess[e][1], fontTextNormal, Rectangle.BOX, 1, 0);
								tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(temp), decimalDigit), fontTextNormal, Rectangle.BOX, 1, 1);
							}
						}
					}else if(comExcess.length>0)
					{
						tableCreation1.setTable(3);
						for(int e=0;e<comExcess.length;e++)
						{
							String temp = comExcess[e][0]==null?"0":comExcess[e][0];
							if(!"0".equals(temp))//&&eccount<=1)
							{
								tableCreation1.insertCell("Excess", fontHeadBold, Rectangle.NO_BORDER,3, 0);
								//tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+currencyType+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 3, 0);
								if(YES.equalsIgnoreCase(excessOption)){
									tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+currencyShortName+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 3, 0);
									}else {
									tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+currencyType+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 3, 0);
								}
								++index;
							}
						}
					}
					if(index>0){
						tableCreation1.getTable().setWidths(new float[] {10f, 30f, 20f});
						tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					}
			//	}
				/*else if (!"displayFragileMessage".equalsIgnoreCase(FragileMessage))
				{
					String comExcess[][] = new String[0][0];
					if(transportId.equalsIgnoreCase(GULF) || transportId.equalsIgnoreCase("2") )
					{
						if(!destCountryId.equalsIgnoreCase(cid) && fromCountryId.equalsIgnoreCase(cid))
						{
							if(DRAFTMODE.equalsIgnoreCase(displayMode)){
								comExcess = finalBean.getComExcessPre(QuoteNo,displayMode);
							}else{
								comExcess = finalBean.getComExcessPre(PolicyNo,displayMode);
							}
							if(comExcess.length>0)
							{
								for(int e=0;e<comExcess.length;e++)
								{
									String temp;
									temp= comExcess[e][0]==null?"0":comExcess[e][0];
									if(!"0".equals(temp))//&&eccount<=1)
									{
										excess.add((prc++),"EXCESS : "+temp+" each and every claim");
									}
									if(!temp.equals("0")&&eccount>1)
									{
										tableCreation2.insertCell("Excess : "+temp+" each and every claim for commoditity "+(e+1), fontText, Rectangle.NO_BORDER,8, 0);
									}
								}
							}
						}
					}//Export Time Only
				}*///IF NOT FRAGILE SELECTED
				
				/*if(excess.size()>0){
					tableCreation1.setTable(8);
					tableCreation1.insertCell("EXCESS", fontHeadBold, Rectangle.NO_BORDER,8, 0);
					for(int pl=0;pl<prc;pl++)
					{
						if(((String)excess.get(pl)).length()>0)
						{
							tableCreation1.insertCell((String)excess.get(pl), fontTextNormal, Rectangle.NO_BORDER,8, 0);
						}
					}
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				}*/
			}
			if("11".equals(pid))
			{
				if(YES.equalsIgnoreCase(remarksOption)){
					if(AdminRemarks!=null && AdminRemarks.trim().length()>0){
						tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
						tableCreation.insertCell(AdminRemarks, fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
				}
				/*tableCreation.insertCell("CONDITIONS OF COVER", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCellPad("TERMS, CONDITIONS, WARRANTIES AND EXCLUSIONS AS PER THE OPEN COVER", fontTextNormal, Rectangle.NO_BORDER,8, 0);*/
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Survey Clause", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
				String surveyClause="";
				surveyClause = finalBean.getsurveyClause(openCoverNo,login,brokerBra);
				if(surveyClause!=null && surveyClause.trim().length()>0){
				tableCreation.insertCell(surveyClause, fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}else{
			    tableCreation.insertCell("In the event of loss or damage for which the Company is presumed to be liable, immediate notice must be given to Emirates Insurance Co., Head Office and no claim for loss or damage will be entertained by the Company unless it is substantiated by a certificate from the above-mentioned agent.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				//tableCreation.insertCell("In the event of loss or damage which may give rise to claim under this Policy, notice must be given immediately to the under noted Agent(s) so that he/they may appoint a surveyor if he/they so desire. In the event that the claim amount likely to be over and above USD 2,500/-.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}			
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation2.setTable(8);
				tableCreation2.insertCell((setAgentId.equalsIgnoreCase("0")?setAgentOthers:setAgentName), fontTextNormal, Rectangle.BOX,8, 0);
				tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0);
				
				if((warrantyCount > 2 || exclusionCount > 2) && list!=null && list.size()>4){
					tableCreation.insertCell("Continued", fontTextNormal, Rectangle.NO_BORDER,8, 1);
				}
				document.add(tableCreation.getTable());
				tableCreation.setTable(8);
				if((warrantyCount > 2 || exclusionCount > 2) && list!=null && list.size()>4){
					document.newPage();
					if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
						tableCreation.insertCell("Part of Quote No. "+QuoteNo, fontTextNormal, Rectangle.NO_BORDER,8, 1);
					}else{
						tableCreation.insertCell("Part of Policy No. "+PolicyNo, fontTextNormal, Rectangle.NO_BORDER,8, 1);
					}
				}
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\nNotification of Claims", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("For the resolution of any dispute and of any contestation which may arise regarding the application or interpretation of a clause of the present contract, and for anything which is not expressly foreseen, the parties declare to refer to the disposition of the English Laws governing this matter with the exception of all questions imperatively governed by the National Law.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				if((bankName!=null && bankName.trim().length()>0) && (YES.equalsIgnoreCase(bankerOption) || YES.equalsIgnoreCase(bankAssOpt)))
				tableCreation.insertCell("Warranted claims, if any, to be payable to the order of "+bankName+" unless the certificate is endorsed by the bank.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
//			tableCreation.setTableSpacing(getPdfSpacing(cid,5f,3f));
			//document.add(tableCreation.getTable());
			//tableCreation.setTable(8);
			//document.newPage();
			if("3".equals(pid))
			{
			if(YES.equalsIgnoreCase(remarksOption)){
				if(AdminRemarks!=null && AdminRemarks.trim().length()>0){
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell(AdminRemarks, fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}
			}
			/*tableCreation.insertCell("SURVEY CLAUSE", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCellPad("In the event of loss or damage which may give rise to a claim under this policy , notice must be given immediately to the under noted Agent(s) so that he/they may appoint a Surveyor if he/they so desire.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			//tableCreation.insertCellPad("Agents at Emirates Insurance Co.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation2.setTable(8);
			if(setAgentId.equalsIgnoreCase("0")){
				if("".equalsIgnoreCase(setAgentOthers) || " ".equalsIgnoreCase(setAgentOthers) ||  "null".equalsIgnoreCase(setAgentOthers) ){
					tableCreation2.insertCellPad(braAddress+", "+braPO+", "+braCity+", "+braCountry+" Tel: "+braPhone+"   Fax: "+ braFax, fontTextNormal, Rectangle.BOX,8, 0);
				}else{
					tableCreation2.insertCellPad(setAgentOthers, fontTextNormal, Rectangle.BOX,8, 0);
				}
			}else{
				tableCreation2.insertCellPad(setAgentName+"\b"+setAgentAdd1+"\b"+setAgentAdd2+"\b Tel:\b"+setAgentTel+"\bFax:\b"+setAgentFax, fontTextNormal, Rectangle.BOX,8, 0);
			}
			tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0,10,10);*/
//			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
//			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("\nSurvey Clause", fontHeadBold, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("In the event of loss or damage which may give rise to a claim under this policy, notice must be given immediately to the under noted Agent(s) so that he/they may appoint a Surveyor if he/they so desire.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			/*tableCreation2.setTable(8);
			tableCreation2.insertCellPad("Agents at \t\t\t\t\t\t\t Emirates Insurance Co.", fontTextNormal, Rectangle.BOX,4, 0);
			tableCreation2.insertCellPad("are", fontTextNormal, Rectangle.BOX,2, 0);
			tableCreation2.insertCellPad("THE COMPANY�S OFFICE", fontTextNormal, Rectangle.BOX,2, 0);
			tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0,10,10);*/
			tableCreation2.setTable(8);
			if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
			tableCreation2.insertCell((setAgentId.equalsIgnoreCase("0")?setAgentOthers:setAgentName), fontTextNormal, Rectangle.BOX,8, 0);
			}else{
			tableCreation2.insertCell(DubaiTradeAgentName, fontTextNormal, Rectangle.BOX,8, 0);
			}
			tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0);
			if((warrantyCount > 2 || exclusionCount > 2) && list!=null && list.size()>4){
				tableCreation.insertCell("Continued", fontTextNormal, Rectangle.NO_BORDER,8, 1);
			}
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			if((warrantyCount > 2 || exclusionCount > 2) && list!=null && list.size()>4){
				document.newPage();
				if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
					tableCreation.insertCell("Part of Quote No. "+Policy_No, fontTextNormal, Rectangle.NO_BORDER,8, 1);
				}else{
					tableCreation.insertCell("Part of Policy No. "+Policy_No, fontTextNormal, Rectangle.NO_BORDER,8, 1);
				}
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("\nClaims", fontHeadBold, Rectangle.NO_BORDER,8, 0);
			//tableCreation.insertCell("In the event of a claim arising under this policy it is agreed that it shall be settled in accordance with English Law and Practice and shall be so settled in Emirates, "+place.trim()+", "+braCity+".", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("In the event of a claim arising under this policy it is agreed that it shall be settled in accordance with English Law and Practice and shall be so settled in Emirates,UAE. ", fontTextNormal, Rectangle.NO_BORDER,8, 0);	
			/*tableCreation2.setTable(8);
			if(setAgentId.equalsIgnoreCase("0")){
				if("".equalsIgnoreCase(setAgentOthers) || " ".equalsIgnoreCase(setAgentOthers) ||  "null".equalsIgnoreCase(setAgentOthers) ){
					tableCreation2.insertCellPad(braAddress+", "+braPO+", "+braCity+", "+braCountry+" Tel: "+braPhone+"   Fax: "+ braFax, fontTextNormal, Rectangle.BOX,8, 0);
				}else{
					tableCreation2.insertCellPad(setAgentOthers, fontTextNormal, Rectangle.BOX,8, 0);
				}
				//tableCreation2.insertCellPad("ARAB ORIENT INSURANCE (P.S.C.), P.O. BOX 27966, DUBAI - U.A.E., Phone:04-2953425, Fax:04-2955701, Contact :", fontHead3, Rectangle.BOX,8, 0);
			}else{
				tableCreation2.insertCellPad(setAgentName+"\b"+setAgentAdd1+"\b"+setAgentAdd2+"\b Tel:\b"+setAgentTel+"\b Fax:\b"+setAgentFax, fontTextNormal, Rectangle.BOX,8, 0);
			}
			tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER, 8, 0,10,10);*/
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
//			tableCreation.insertCell("For the resolution of any dispute and of and contestation which may arise regarding the application or interpretation of a clause of the present contract, and for anything which is not expressly foreseen, the parties declare to refer to the disposition of the English Laws governing this matter with the exception of all questions imperatively governed by the National Law.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			if((bankName!=null && bankName.trim().length()>0) && (YES.equalsIgnoreCase(bankerOption) || YES.equalsIgnoreCase(bankAssOpt)))
				tableCreation.insertCell("Warranted claims, if any, to be payable to the order of "+bankName+" unless the certificate is endorsed by the bank.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("In Witness whereof this policy has been signed for and on behalf of", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			tableCreation.insertCell("For and on behalf of", fontTextNormal, Rectangle.NO_BORDER,8, 5);
			tableCreation.insertCell(braAddress, fontTextNormal, Rectangle.NO_BORDER,8, 5);
			
			Image signImageRoyal = null;
			Image stampImageRoyal = null;
			try
			{
				signImageRoyal  = Image.getInstance(signImage);
				stampImageRoyal  = Image.getInstance(stampImage);
//				signImageRoyal.scaleToFit(70,54);
				signImageRoyal.scaleToFit(156,107);
				//stampImageRoyal.scaleToFit(60,54);
				//stampImageRoyal.scaleToFit(130,130);
				
			}
			catch(Exception e)
			{
				logger.debug(e);
			}
			tableCreation1.setTable(8);
			if(!DRAFTMODE.equalsIgnoreCase(displayMode)){
				tableCreation1.insertCell("Signed on: "+ polGenDate, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
				tableCreation1.insertCell("Signed at "+place, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				}else{
				tableCreation1.insertCell("Signed at  Issued Online", fontTextNormal,Rectangle.NO_BORDER, 8, 0);	
				}
				tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			if(!stampStatus.equalsIgnoreCase("Y")){
				tableCreation1.insertCell("\n\n\n\n", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
			if(approvedBy.equalsIgnoreCase("Nil"))
				tableCreation1.insertCell("Entered/Approved By: "+BrokerName, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			else
				tableCreation1.insertCell("Entered/Approved By: "+ BrokerName+"/"+approvedBy, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,4, 0,0);
			if(stampStatus.equalsIgnoreCase("Y")){
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,8, 1);
			}
			else{
				tableCreation.insertCell("Emirates Insurance Co.", fontTextNormal, Rectangle.NO_BORDER,4, 5);
			}
			tableCreation.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			
			if(issuerName.length()<=0 && !"Y".equalsIgnoreCase(DubaiTradeStatus)){
				tableCreation.insertCell("This certificate is issued by "+ brokerCompany+ " on behalf of "+braAddress, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			document.add(tableCreation.getTable());
			if(finalCount>1){
				document.newPage();
				tableCreation.setTable(13);
				if("DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
					tableCreation.insertCell("List attaching to and forming part of Quote No "+QuoteNo, fontHeadNormal,Rectangle.NO_BORDER, 10, 0);
				}else if("11".equalsIgnoreCase(pid) && Policy_No!=null && !"".equals(Policy_No)){
					tableCreation.insertCell("List attaching to and forming part of Policy No "+Policy_No.substring(0,Policy_No.lastIndexOf("-"))+". Cert. No. "+Policy_No.substring(Policy_No.lastIndexOf("-")+1,Policy_No.length()), fontHeadNormal,Rectangle.NO_BORDER, 10, 0);
				}else if(Policy_No!=null && !"".equals(Policy_No)){
					tableCreation.insertCell("List attaching to and forming part of Policy No "+Policy_No, fontHeadNormal,Rectangle.NO_BORDER, 10, 0);
				}
				tableCreation.insertCell("\n\n", fontHeadNormal,Rectangle.NO_BORDER, 13, 0);
				
				tableCreation.insertCell("S. No", fontHeadNormal,Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Order / Ref.  No.", fontHeadNormal,Rectangle.BOX, 2, 0);
				tableCreation.insertCell("Merchandise", fontHeadNormal,Rectangle.BOX, 2, 0);
				
				tableCreation1.setTable(14);
				tableCreation1.insertCell("Value", fontHeadNormal,Rectangle.BOX, 14, 2);
				tableCreation1.insertCell("Currency", fontTextNormal,Rectangle.BOX, 4, 0);
				tableCreation1.insertCell("Amount", fontTextNormal,Rectangle.BOX, 10, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX, 2, 0);
				
				tableCreation.insertCell("BOV", fontHeadNormal,Rectangle.BOX, 3, 0);
				tableCreation.insertCell("ROE", fontHeadNormal,Rectangle.BOX, 1, 0);
				tableCreation.insertCell("Sum Insured ("+(YES.equalsIgnoreCase(currencyOption)?currencyShortName:currencyType)+")", fontHeadNormal,Rectangle.BOX, 2, 0);
				
				
				for(int print=0;print<finalCount;print++){
					tableCreation.insertCell(String.valueOf(print+1), fontHeadNormal,Rectangle.BOX, 1, 0);
					tableCreation.insertCell(invoice[print], fontHeadNormal,Rectangle.BOX, 2, 0);
					tableCreation.insertCell(commodityDes[print], fontHeadNormal,Rectangle.BOX, 2, 0);
					
					tableCreation1.setTable(14);
					tableCreation1.insertCell(currencyShortName, fontTextNormal,Rectangle.BOX, 4, 0);
					commoditySumIns[print]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySumIns[print].replaceAll(",",""),currencyId, brokerBra)), decimalDigit);
					tableCreation1.insertCell(commoditySumIns[print], fontTextNormal,Rectangle.BOX, 10, 1);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX, 2, 0);
					tableCreation.insertCell((saleFlag?(SaleTermName):"")+(tolFlag?((saleFlag?" + ":"")+toleranceName):""), fontHeadNormal,Rectangle.BOX, 3, 0);
					
					if(YES.equalsIgnoreCase(currencyOption)){
						tableCreation.insertCell("1", fontHeadNormal,Rectangle.BOX, 1, 1);
						commoditySum[print]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySum[print].replaceAll(",",""),currencyId, brokerBra)), decimalDigit);
						tableCreation.insertCell(commoditySum[print], fontHeadNormal,Rectangle.BOX, 2, 1);
					}else{
						tableCreation.insertCell(ExchangeRate, fontHeadNormal,Rectangle.BOX, 1, 1);
						localSumround[print]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(localSumround[print].replaceAll(",",""),"1", brokerBra)), decimalDigit);
						tableCreation.insertCell(localSumround[print], fontHeadNormal,Rectangle.BOX, 2, 1);
					}
				}
				tableCreation.insertCell("Total", fontHeadNormal,Rectangle.NO_BORDER, 10, 1);
				tableCreation.insertCell("", fontHeadNormal,Rectangle.NO_BORDER, 1, 1);
				tableCreation.insertCell(insuredTotal, fontHeadNormal,Rectangle.BOX, 2, 1);
				document.add(tableCreation.getTable());
			}
			//Added by sathish for Emirates PDF==>End
			
			
			
			
			
			
			// ///////Header and Footer/////////
					/*HeaderFooterImage pageWater;
					pageWater = new HeaderFooterImage();
					pageWater.setImagePath(imagePath);
					pageWater.setFooterImagePath(footerImagePath);
					if("13".equalsIgnoreCase(brokerBra.trim()) || "DRAFT".equalsIgnoreCase(displayText) || "INVALID DRAFT".equalsIgnoreCase(displayText)){
						pageWater.setDisplayText(displayText);						
					}else{
						pageWater.setDisplayText("");
					}
					pageWater.setCols(cols);
					
//					For KSA
					String polGenDate = "";
					String polGenTime = "";
					if(!displayMode.equalsIgnoreCase(DRAFTMODE)&&!"NoDate".equalsIgnoreCase(policyDate))
					{
						polGenDate = policyDate.substring(0,policyDate.indexOf(' '));
						polGenTime = policyDate.substring((policyDate.indexOf(' '))+1,policyDate.length());
					}
					polGenDate = polGenDate+" Time: "+polGenTime;
					pageWater.setPolicyOn(polGenDate);
					pageWater.setPolicyAt(place);
					pageWater.setEntered(BrokerName);
					pageWater.setApproved(approvedBy);
					pageWater.setStampStatus(stampStatus);
					pageWater.setIssuerName(issuerName);
					pageWater.setBraAddress(braAddress);
					pageWater.setBrokerCompany(brokerCompany);
					pageWater.setCid(cid);
					pageWater.setUsrModeController(usrModeController);
					pageWater.setSignedImage(signImage);
					pageWater.setStampImage(stampImage);
					pageWater.setFontPath(fontPath);
					writer.setPageEvent(pageWater);
			
			// ////////// Font Declaration ////////////
			
			BaseFont arialFont = null;
			try
			{
				arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			}
			catch (Exception e) 
			{
				logger.debug(e);
			}
			float fontSize=0.0f;
			fontHead3=new Font(Font.TIMES_ROMAN,8+fontSize,Font.NORMAL);
            fontHead2=new Font(Font.TIMES_ROMAN,9+fontSize,Font.BOLD);
            fontHeadSP=new Font(Font.TIMES_ROMAN,12+fontSize,Font.BOLD);
            fontHead1=new Font(Font.TIMES_ROMAN,9+fontSize,Font.NORMAL);
            fontHeadUl =new Font(Font.TIMES_ROMAN,9,Font.BOLD);
            fontHeadUl.setStyle(Font.UNDERLINE);
            font = new Font(Font.TIMES_ROMAN,9,Font.NORMAL);
            fontText=new Font(arialFont,7+fontSize,Font.NORMAL);
			fontText1=new Font(arialFont,7+fontSize,Font.BOLD);
			
////////	/ Table 1 only for schedule ///////
			tableCreation.setTable(8);
//			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			tableCreation.insertCell(Rectangle.NO_BORDER, 8);
			if(!DRAFTMODE.equalsIgnoreCase(displayMode))
			{
				tableCreation.insertCell(Rectangle.NO_BORDER, 8);
				tableCreation.insertCell("MARINE INSURANCE POLICY (CARGO)", fontHeadSP, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("We, The "+braAddress.toUpperCase()+", hereby agree, in consideration of payment to us by or on behalf of" +
						" the Assured of the premium specified in the schedule, " +
						"to insure against loss, damage, liability or expenses in proportions & manner hereinafter provided.", fontHead1, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			}else
			{
				tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,2);
				tableCreation.insertCell("MARINE INSURANCE POLICY (CARGO)", fontHeadSP, Rectangle.NO_BORDER,8, 2);
				tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
				tableCreation = addPdfSpaceLine(cid,tableCreation,fontText,3);
			}
//			tableCreation.setTableSpacing(3f); // 5F Second Page issue May 20
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 2);
			if(DRAFTMODE.equalsIgnoreCase(displayMode) ||TEST.equalsIgnoreCase(usrModeController))
			{
				tableCreation.insertCellPad("Quote No", fontHead2, Rectangle.NO_BORDER,2, 0);
			}
			else
			{
				tableCreation.insertCellPad("Policy No", fontHead2, Rectangle.NO_BORDER,2, 0);
			}
			logger.info("royal pdf test for multiple PolicyNo..."+PolicyNo);
			logger.info("royal pdf test for multiple QuoteNo..."+QuoteNo);
			if(DRAFTMODE.equalsIgnoreCase(displayMode)||TEST.equalsIgnoreCase(usrModeController)){
				tableCreation.insertCell(" : "+QuoteNo, fontHead2, Rectangle.NO_BORDER,2, 0);
			}else{
				tableCreation.insertCell(" : "+(PolicyNo.length()<=0?QuoteNo:PolicyNo), fontHead2, Rectangle.NO_BORDER,2, 0);
			}
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
			
			tableCreation.insertCellPad("Broker Code", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+cusCode, fontHead1, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
			
			tableCreation.insertCellPad("Name of Insured(s)", fontHead2, Rectangle.NO_BORDER,2, 0);
			
			if(YES.equalsIgnoreCase(bankAssOpt) && "NO".equalsIgnoreCase(bankerOption))
			{
				if(custCompany==null || custCompany.equalsIgnoreCase(""))
				{
					tableCreation.insertCell(" : "+(bankName.length()>0?bankName+", ":"")+CustomerFirstName+CustomerLastName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+(bankName.length()>0?bankName+", ":"")+custCompany, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
			else if(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankAssOpt) )
			{
				tableCreation.insertCell(" : "+bankName, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
			else
			{
				if(custCompany==null || custCompany.equalsIgnoreCase(""))
				{
					tableCreation.insertCell(" : "+CustomerFirstName+CustomerLastName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				else
				{
					tableCreation.insertCell(" : "+custCompany, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
			if(!(YES.equalsIgnoreCase(bankerOption) && "NO".equalsIgnoreCase(bankAssOpt)))
			{
				tableCreation.insertCellPad("Address", fontHead2, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : P.O.BOX NO. "+CustomerPoBox, fontHead1, Rectangle.NO_BORDER,6, 0);
				if(custAddress1.length()>0){
					tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("  "+custAddress1, fontHead1, Rectangle.NO_BORDER,6, 0);
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
			tableCreation.insertCell(" : "+TransitFrom+transCountryName, fontHead1, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("Voyage  To", fontHead2, Rectangle.NO_BORDER,1, 0);
			finalDestination=(finalDestination==null||"Others".equalsIgnoreCase(finalDestination))?"":finalDestination;
			if(!"".equalsIgnoreCase(finalDestination.trim()))
			{
				finalDestination=finalDestination+",";
			}
			tableCreation.insertCell(" : "+finalDestination+finalCountryName, fontHead1, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCellPad("Vessel/Conveyance", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+carrierName, fontHead1, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("By", fontHead2, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(" : "+TransportName, fontHead1, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell("Date", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(PolicyDate, fontHead1, Rectangle.NO_BORDER,2, 0);
			if (!("YES".equalsIgnoreCase(bankerOption)|| "YES".equalsIgnoreCase(bankAssOpt))) 
			{
				if (bankName != null && bankName.length()>0)
				{
					tableCreation.insertCellPad("Bank Interest", fontHead2, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell(" : "+bankName, fontHead1, Rectangle.NO_BORDER,6, 0);
				}
			}
			tableCreation.insertCellPad("LC Number", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+(lcNumber.length()>1?lcNumber:"NIL"), fontHead1, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("LC Date", fontHead2, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(" : "+(lcDate.length()>0?lcDate:"NIL"), fontHead1, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCellPad("Date", fontHead2, Rectangle.NO_BORDER,2, 0);
			tableCreation.insertCell(" : "+PolicyDate, fontHead1, Rectangle.NO_BORDER,3, 0);
			
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,4, 0);
			tableCreation.insertCell("", fontHead1, Rectangle.TOP,8, 0);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
			
			for(int print=0;print<finalCount;print++){
				tableCreation.insertCellPad("Goods Description", fontHead2, Rectangle.NO_BORDER,2, 0);
				StringBuffer goods = new StringBuffer();
				goods.append(commodityDes[print]);
				goods.append(" ");
				goods.append(packageDes[print]);
				goods.append(" ");
				if(invoice[print]!=null&&invoice[print].length()>0){
					goods.append("INV# ");
					goods.append(invoice[print]);
				}
				tableCreation.insertCell(" : "+goods.toString(), fontHead1, Rectangle.NO_BORDER,6, 0);
				tableCreation.insertCellPad("Value Basis", fontHead2, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : "+saleTermName[print], fontHead1, Rectangle.NO_BORDER,6, 0);
				if(tolDetails.length>0){
					//tableCreation.insertCellPad("Tolerance", fontHead2, Rectangle.NO_BORDER,2, 0);
					//tableCreation.insertCell(" : "+tolDetails[0][0], fontHead1, Rectangle.NO_BORDER,6, 0);
				}
				tableCreation.insertCellPad("FC Sum Insured", fontHead2, Rectangle.NO_BORDER,2, 0);
				//tableCreation.insertCell(" : "+currencyShortName+". "+CurrencyName, fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(" : "+currencyShortName, fontHead1, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell(commoditySum[print], fontHead1, Rectangle.NO_BORDER,1, 1);
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
					tableCreation.insertCell(localSumround[print], fontHead1, Rectangle.NO_BORDER,1, 1);
					tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,3, 0);
				}
			}
			tableCreation.insertCellPad("Cover", fontHead2, Rectangle.NO_BORDER,2, 0);
			if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
				tableCreation.insertCell(" : "+coverName+"+ WAR & STRIKES", fontHead1, Rectangle.NO_BORDER,6, 0);
			}else{
				tableCreation.insertCell(" : "+coverName, fontHead1, Rectangle.NO_BORDER,6, 0);
			}
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,8, 0);
			//Premium table
			tableCreation2.setTable(8);
			if(YES.equalsIgnoreCase(PremiumYes.trim())){
				//tax
				String preQuote = (String)taxDetails.get("QuoteNo0");
				
				tableCreation2.insertCellPad("Premium", fontHead2, Rectangle.NO_BORDER,8, 0);
				
				if(!(extraCoverId.equals("0")||extraCoverId.equals("3"))){
					tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
					tableCreation2.insertCellPad("     War Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("WarPremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("MarineWar"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				}else{
					tableCreation2.insertCellPad("     Marine Premium", fontHead2, Rectangle.NO_BORDER,3, 0);
					tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("MarinePremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("MarineWar"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
				}
				//tax things
				if(((String)taxDetails.get("TaxOption"+preQuote)).equalsIgnoreCase("Yes")){
					if(((String)taxDetails.get("TaxStatus"+preQuote)).equalsIgnoreCase("Yes")){
						//tax
						tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
						tableCreation2.insertCellPad("     Government Tax", fontHead2, Rectangle.NO_BORDER,3, 0);
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
						tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("GovTax"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
						if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
							tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,2, 1);
						}else{
							tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("TaxSum"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
						}
					}if(((String)taxDetails.get("FundStatus"+preQuote)).equalsIgnoreCase("Yes")){
						//Emragency
						tableCreation2.insertCellPad("     Emergency Fund", fontHead2, Rectangle.NO_BORDER,3, 0);
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
						tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("Fund"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
						tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("TaxSum"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					}
					if(((String)taxDetails.get("FeeStatus"+preQuote)).equalsIgnoreCase("Yes")){
						//Fee
						tableCreation2.insertCellPad("Add", fontHeadUl, Rectangle.NO_BORDER,8, 0);
						tableCreation2.insertCellPad("     Policy Fee", fontHead2, Rectangle.NO_BORDER,3, 0);
						tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
						tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
						tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("Fee"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,2, 1);
					}
				}
				tableCreation2.insertCell("", fontHead1, Rectangle.NO_BORDER,6, 0);
				tableCreation2.insertCell("", fontHead1, Rectangle.BOTTOM,2, 1);
				
				tableCreation2.insertCellPad("     Total", fontHead2, Rectangle.NO_BORDER,3, 0);
				tableCreation2.insertCell("     : "+currencyType, fontHead1, Rectangle.NO_BORDER,1, 0);
				tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble((String)taxDetails.get("FinalPremium"+preQuote)),decimalDigit), fontHead1, Rectangle.NO_BORDER,4, 1);
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
			tableCreation.insertCellPad("TERMS, CONDITIONS, WARRANTIES AND EXCLUSIONS AS PER THE ATTACHMENT", fontHead1, Rectangle.NO_BORDER,8, 0,25);
			if(YES.equalsIgnoreCase(BackDaysOption) && !( "admin".equalsIgnoreCase(AdminStatus) || 
					"NORMAL_EXCESS_PRICE".equalsIgnoreCase(AdminStatus)))
			{
				tableCreation.insertCellPad("NO KNOWN OR REPORTED LOSS TILLL "+("NoDate".equalsIgnoreCase(policyDate)?quotedDate:policyDate), fontHead2, Rectangle.NO_BORDER,8, 0);
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
			if(setAgentId.equalsIgnoreCase("0")){
				if("".equalsIgnoreCase(setAgentOthers) || " ".equalsIgnoreCase(setAgentOthers) ||  "null".equalsIgnoreCase(setAgentOthers) ){
					tableCreation2.insertCellPad(braAddress+", "+braPO+", "+braCity+", "+braCountry+" Tel: "+braPhone+"   Fax: "+ braFax, fontHead2, Rectangle.BOX,8, 0);
				}else{
					tableCreation2.insertCellPad(setAgentOthers, fontHead2, Rectangle.BOX,8, 0);
				}
				//tableCreation2.insertCellPad("ARAB ORIENT INSURANCE (P.S.C.), P.O. BOX 27966, DUBAI - U.A.E., Phone:04-2953425, Fax:04-2955701, Contact :", fontHead3, Rectangle.BOX,8, 0);
			}else{
				tableCreation2.insertCellPad(setAgentName+"\b"+setAgentAdd1+"\b"+setAgentAdd2+"\b Tel:\b"+setAgentTel+"\bFax:\b"+setAgentFax, fontHead2, Rectangle.BOX,8, 0);
			}
			Image signImageRoyal = null;
			Image stampImageRoyal = null;
			try
			{
				signImageRoyal  = Image.getInstance(signImage);
				stampImageRoyal  = Image.getInstance(stampImage);
//				signImageRoyal.scaleToFit(70,54);
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
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("for and on behalf of", fontHead2, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,5, 0);
			if(stampStatus.equalsIgnoreCase("Y")){
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,1, 0);
				tableCreation.insertCell(stampImageRoyal, Rectangle.NO_BORDER,1, 0);
			}
			else{
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,2, 2);
			}
			tableCreation.insertCell("", fontHead1, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(createDate, fontHead1, Rectangle.NO_BORDER,5, 0);
			//tableCreation.insertCellPad("", fontHead1, Rectangle.NO_BORDER,1, 0);
			tableCreation.insertCell(braAddress, fontHead2, Rectangle.NO_BORDER,3, 0);
			tableCreation1.setTable(8);
			tableCreation1.insertCell(tableCreation.getTable(), Rectangle.BOX, 8, 0);
			tableCreation1.setTableSpacing(1f);
			document.add(tableCreation1.getTable());
			//Table5 for clauses and conditions//
			tableCreation.setTable(8);
			tableCreation.insertCell("Clauses, Endorsements, Special conditions and Warranties", fontHeadSP, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
			
			document.newPage();
			ArrayList warrenties;
			warrenties = new ArrayList();
			ArrayList excess;
			excess = new ArrayList();
			int prc = 0;
			tableCreation1.setTable(8);
			if (EditClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Clauses :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a22 = 0; a22 < EditClauses.length; a22++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((EditClauses[a22][1]==null?"":EditClauses[a22][1]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (clauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Clauses :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a1 = 0; a1 < clauses.length; a1++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((clauses[a1][0]==null?"":clauses[a1][0]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (EditExtraClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("W&SRCC Clauses :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a222 = 0; a222 < EditExtraClauses.length; a222++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((EditExtraClauses[a222][1]==null?"":EditExtraClauses[a222][1]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (extraClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("W&SRCC Clauses :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a2 = 0; a2 < extraClauses.length; a2++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((extraClauses[a2][0]==null?"":extraClauses[a2][0]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (EditExClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Exclusions :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a333 = 0; a333 < EditExClauses.length; a333++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((EditExClauses[a333][1]==null?"":EditExClauses[a333][1]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (exclusions.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Exclusions :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a3 = 0; a3 < exclusions.length; a3++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((exclusions[a3][0]==null?"":exclusions[a3][0]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			
			if (YES.equalsIgnoreCase(wareFinalOpt)&& YES.equalsIgnoreCase(warehouseOption)) 
			{
				warrenties.add((prc++),"Cover from Warehouse to Warehouse");
			}
			if (("NO".equalsIgnoreCase(wareFinalOpt)&& "Any Port".equalsIgnoreCase(finalPlace) && finalDestination.length() > 2)&& "NO".equalsIgnoreCase(destWarStatus)) 
			{
				warrenties.add((prc++),"No risk after port of discharge");
			}
			if (!("NO BROKER REMARKS CAME".equalsIgnoreCase(brokerRemarks))) 
			{
				warrenties.add((prc++),brokerRemarks);
			}
			if ("admin".equalsIgnoreCase(AdminStatus)) 
			{
				if ("NoDate".equalsIgnoreCase(policyDate)) 
				{
					if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown))) 
					{
						warrenties.add((prc++),subjectKnown);
					}
				} 
				else 
				{
		
					if (!("NOTHING".equalsIgnoreCase(subjectKnown)||"null".equalsIgnoreCase(subjectKnown)||	"".equalsIgnoreCase(subjectKnown)))
					{				
						warrenties.add((prc++),subjectKnown);
					}
				}
			} 
			else
			{
				warrenties.add((prc++),getPolicysFreshBackDesc(brokerBra,PolicyNo,QuoteNo));
			}
			if ((seaOption.trim()).length()>0) 
			{
				if ("LCL".equalsIgnoreCase(seaOption)) 
				{
					warrenties.add((prc++),"Warranted cargo's are in less than container load only");
				}
				else if ("FCL".equalsIgnoreCase(seaOption))
				{
					warrenties.add((prc++),"Warranted cargo's are in full container load only");
				}
			}
			if ((partialShip.trim()).length()>0&&"Y".equalsIgnoreCase(partialShip)) 
			{
				warrenties.add((prc++),"Partial shipments are allowed");
			}
			if(!(AdminRemarks == null||"".equalsIgnoreCase(AdminRemarks))) 
			{
				warrenties.add((prc++),AdminRemarks);
			}
			//Warrenties
			if (EditWarClauses.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Warranties :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a3334 = 0; a3334 < EditWarClauses.length; a3334++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((EditWarClauses[a3334][1]==null?"":EditWarClauses[a3334][1]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				for(int pl=0;pl<prc;pl++)
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((String)warrenties.get(pl), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			if (warranties.length > 0)
			{
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Warranties :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for (int a4 = 0; a4 < warranties.length; a4++) 
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((warranties[a4][0]==null?"":warranties[a4][0]), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				for(int pl=0;pl<prc;pl++)
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((String)warrenties.get(pl), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			//Excess
			prc=0;
			if ("displayFragileMessage".equalsIgnoreCase(FragileMessage)) 
			{
				excess.add((prc++),"0.50% of the Sum Insured subject to minimum of US$ 250/- Each and Every Claim for FRAGILE Commodity");
			}
			else if (!"displayFragileMessage".equalsIgnoreCase(FragileMessage))
			{
				String comExcess[][] = new String[0][0];
				if(transportId.equalsIgnoreCase(GULF) || transportId.equalsIgnoreCase("2") )
				{
					if(!destCountryId.equalsIgnoreCase(cid) && fromCountryId.equalsIgnoreCase(cid))
					{
						if(DRAFTMODE.equalsIgnoreCase(displayMode)){
							comExcess = finalBean.getComExcessPre(QuoteNo,displayMode);
						}else{
							comExcess = finalBean.getComExcessPre(PolicyNo,displayMode);
						}
						if(comExcess.length>0)
						{
							for(int e=0;e<comExcess.length;e++)
							{
								String temp;
								temp= comExcess[e][0]==null?"0":comExcess[e][0];
								if(!"0".equals(temp))//&&eccount<=1)
								{
									excess.add((prc++),"Excess : "+temp+" each and every claim");
								}
								if(!temp.equals("0")&&eccount>1)
								{
									tableCreation2.insertCell("Excess : "+temp+" each and every claim for commoditity "+(e+1), fontText, Rectangle.NO_BORDER,8, 0);
								}
							}
						}
					}
				}//Export Time Only
			}//IF NOT FRAGILE SELECTED
			if(excess.size()>0){
				tableCreation1.setTable(8);
				tableCreation1.insertCell("Excess :", fontHead1, Rectangle.NO_BORDER,8, 0);
				for(int pl=0;pl<prc;pl++)
				{
					tableCreation1.insertCellPad("", fontText, Rectangle.NO_BORDER,8, 0,10);
					tableCreation1.insertCellPad((String)excess.get(pl), fontText, Rectangle.NO_BORDER,8, 0,10);
				}
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
			}
			tableCreation.setTableSpacing(getPdfSpacing(cid,5f,3f));
			document.add(tableCreation.getTable());*/
			document.close();
		}
		catch (DocumentException e) 
		{
			logger.debug(e);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		logger.debug(EXIT);
		
	}
	
	//Changed by by sathish for Debit pdf creation from Controller to bean for web service - Start
	
	public void writeNewCreditPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map premiumdetails;
			Map commoditydetails;
			premiumdetails = new HashMap();
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			double netPremium=0.0;
			double Amt=0.00;
			double Amt1=0.00;
			double Amt2=0.00;
			double Amt3=0.00;
			double Amt4=0.00;
			double Amt5=0.00;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "", arAccountNo="", paymentMode="",minPreYN="";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "";
			//String DebitNoteNo = "";
			String creditNoteNo = "";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			String extraCoverId ="";
			String commodityDes[] = new String[0];
			String[] commoditySumIns = new String[0];
			String localSumIns[] = new String[0];
			String packageDes[] = new String[0];
			String[] invoice = new String[0];
			String[] premiumRate=new String[0];
			String[] premiumSingle=new String[0];
			String[] warRate=new String[0];
			String[] warPremium=new String[0];
			String[] commoditySum = new String[0];
			String[] localSumround = new String[0];	
			String tolDetails[][] = new String[0][0];
			String getPolictDetails[][] = new String[0][0];
			String getPolNo="";
			String getCertNo="";
			String getEndtNo="";
			premiumdetails = new HashMap();
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="";
			String commissionStatus = "Y"; // Debit Commission....
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			boolean fileExists=new File(filePath).exists();
			
			String issuanceFee="";
			String totalMarinePremium="", totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="", lcNumber="", bankName="";
			double exchangeRate=0.0;
			//rajesh world work stated
			String noteType=new policyInfo().getNoteType(PolicyNo);
			String productInfo[][]=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				logger.info("currency type"+currencyType+"-"+currencyType1);
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST CREDIT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "CN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				productInfo=getProductInfo(brokerBra, PolicyNo);
				if(productInfo!=null && productInfo.length>0)
				{
					productId=productInfo[0][0];
					productName=productInfo[0][1];
				}
					
				if("11".equals(productId))
				{
					rsa.opencoverpdf.finalprint dataBean=new rsa.opencoverpdf.finalprint();
					premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"POLICYNO",brokerBra,cid,"");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
				}
				else{
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);
				}
				if(getPolictDetails.length>0)
				{
					getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
					getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
					getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
				}
				currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				Map AmountDetails;
				AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					lcNumber = (String) premiumdetails.get("LcNumber") == null ? ""
							: (String) premiumdetails.get("LcNumber");// [0][33]);
					bankName = (String) premiumdetails.get("BankName") == null ? "": (String) premiumdetails.get("BankName");// [0][35]);
					bankName=bankName.equalsIgnoreCase("None")?"":bankName;
					SaleTermValue = (String) premiumdetails.get("SaleTermValue") == null ? "0"
							: (String) premiumdetails.get("SaleTermValue");// [0][19]);
					currencyId = (String) premiumdetails.get("currencyId") == null ? ""
							: (String) premiumdetails.get("currencyId");// [0][14]);
					exchangeRate = Double.parseDouble((String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate"));// [0][15]);
					extraCoverId = (String)premiumdetails.get("ExtraCoverId");
					commoditydetails = (HashMap) premiumdetails.get("commoditydetails");
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails.get("CustomerLastName") == null ? "": (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					/*Brokeraddress1 = (String) premiumdetails.get("Brokeraddress1") == null ? ""	: (String) premiumdetails.get("Brokeraddress1");// [0][51]);
					BrokerAddress2 = (String) premiumdetails.get("BrokerAddress2") == null ? "": (String) premiumdetails.get("BrokerAddress2");// [0][52]);
					BrokerCountry = (String) premiumdetails.get("BrokerCountry") == null ? "": (String) premiumdetails.get("BrokerCountry");// [0][54]);
					BrokerPoBox = (String) premiumdetails.get("BrokerPoBox") == null ? "0":("PO Box " +(String)premiumdetails.get("BrokerPoBox"));// [0][56]);
					BrokerEmirate = (String) premiumdetails.get("BrokerEmirate") == null ? "": (String) premiumdetails.get("BrokerEmirate");// [0][57]);*/
					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
//					BrokerCompany = (String) premiumdetails.get("BrokerCompany") == null ? "": (String) premiumdetails.get("BrokerCompany");// [0][84]);
					String [][] sourceInfo=new String[0][0];
					paymentMode = (String) premiumdetails.get("paymentMode") == null ? ""	: (String) premiumdetails.get("paymentMode");// [0][58]);
					if("CR".equalsIgnoreCase(paymentMode))
		        	  sourceInfo=getCustomerInfo((String) premiumdetails.get("debitCustomerId"), brokerBra);
		           else
		        	  sourceInfo=getSourceInfo(loginId, brokerBra);
					        	  
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
						arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
					}
					premium = (String) premiumdetails.get("Premium") == null ? "0"
							: (String) premiumdetails.get("Premium");
					issuanceFee= (String) premiumdetails.get("IssuanceFee") == null ? "0"
							: (String) premiumdetails.get("IssuanceFee");
					totalMarinePremium=(String) premiumdetails.get("MARINE_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("MARINE_PREMIUM");
					totalWarPremium=(String) premiumdetails.get("WAR_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("WAR_PREMIUM");
					totalSumIns = (String) commoditydetails.get("TotalCommoditySI") == null ? "0"
							: (String) commoditydetails.get("TotalCommoditySI");
					minPreYN = (String) premiumdetails.get("MIN_PRE_YN") == null ? "N"
							: (String) premiumdetails.get("MIN_PRE_YN");
					
					finalCount = Integer.parseInt((String) commoditydetails.get("finalCount"));
					commodityDes = new String[finalCount];
					commoditySumIns = new String[finalCount];
					packageDes = new String[finalCount];
					invoice=new String[finalCount];
					localSumIns = new String[finalCount];
					premiumRate=new String[finalCount];
					premiumSingle=new String[finalCount];
					warRate=new String[finalCount];
					warPremium=new String[finalCount];
					commoditySum = new String[finalCount];
					localSumround = new String[finalCount];
					for (int i = 0; i < finalCount; i++) 
					{
						
						commodityDes[i] = (String) commoditydetails.get("CommodityDescription" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("CommodityDescription" + (i + 1));// [0][9]);
						commoditySumIns[i] = (String) commoditydetails.get("CommoditySumInsured" + (i + 1)) == null ? "0"
								: (String) commoditydetails
										.get("CommoditySumInsured" + (i + 1));// [0][8]);
						packageDes[i] = (String) commoditydetails.get("PackageDescription_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("PackageDescription_arr" + (i + 1));// [i][65]);
						invoice[i] = (String) commoditydetails.get("Invoice_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("Invoice_arr"+ (i + 1));// [0][86]);
						localSumIns[i] = (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1)) == null ? "0"
								: (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1));// [0][25]);
						//Added by sathish Start
						premiumRate[i]=(String)commoditydetails.get("PremiumRate" + (i+1))==null?"0":(String)commoditydetails.get("PremiumRate" + (i+1));
						premiumSingle[i]=(String)commoditydetails.get("PremiumSingle" + (i+1))==null?"0":(String)commoditydetails.get("PremiumSingle" + (i+1));
						warRate[i]=(String)commoditydetails.get("WarRate" + (i+1))==null?"0":(String)commoditydetails.get("WarRate" + (i+1));
						warPremium[i]=(String)commoditydetails.get("WarPremium" + (i+1))==null?"0":(String)commoditydetails.get("WarPremium" + (i+1));
						//Added by sathish End
						
						//Added New
						double salePercent = 0.0;
						double sumLocal = 0.0;
						
						double sumLocalSale = 0.0;
						double toleranceval = 0.0;
						double tolValuePer = 0.0;
						double commoditySI = 0.0;
						double commoditySISale = 0.0;
						if(tolDetails.length>0)
						{
							toleranceval = Double.parseDouble(tolDetails[i][1]==null?"0":tolDetails[i][1]);
							tolValuePer = Double.parseDouble(tolDetails[0][2]==null?"0":tolDetails[0][2]);
						}
						salePercent = Double.parseDouble(SaleTermValue);
						sumLocal = Double.parseDouble(localSumIns[i]);
						sumLocalSale = sumLocal + (sumLocal * (salePercent / 100));
						sumLocalSale = sumLocalSale + toleranceval;
						commoditySI = Double.parseDouble(commoditySumIns[i]);
						commoditySISale = commoditySI	+ (commoditySI * (salePercent / 100));
						commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
						localSumround[i] = pdis.decimalFormat(sumLocalSale, decimalDigit);
						commoditySum[i] = pdis.decimalFormat(commoditySISale, decimalDigit);
						/*localSumIns[i] = pdis.decimalFormat(Double.parseDouble(localSumIns[i].replaceAll(",","")), decimalDigit);
						commoditySumIns[i]=pdis.decimalFormat(Double.parseDouble(commoditySumIns[i].replaceAll(",","")), decimalDigit);*/
						//End
					}
					
					
					//loginId = (String) premiumdetails.get("LoginID");// [0][60]);
					ExcessPremium = (String) premiumdetails.get("ExcessPremium") == null ? "0": (String) premiumdetails.get("ExcessPremium");
					
					finalDeductedAmt = (String) AmountDetails.get("FinalPayableAmountAfterDeduction") == null ? "0"	: (String) AmountDetails.get("FinalPayableAmountAfterDeduction");
					finalAmtRound = pdis.decimalFormat(Double.parseDouble(finalDeductedAmt), decimalDigit);
			
					StringTokenizer token1;
					token1 = new StringTokenizer(finalDeductedAmt, ".");
					finalAmtRs = (String) token1.nextToken();
					finalAmtPs = (String) token1.nextToken();
					logger.info("finalAmtRs"+ finalAmtRs);
					logger.info("finalAmtPs>>>>>>>>>>>>."	+ finalAmtPs);
					finalAmtRs = finalAmtRs.replaceAll(",","");
					finalRsConversion = Integer.parseInt(finalAmtRs);
					ConvertToWords = convertWord.convertNumToWord(finalRsConversion);
					commissionAmount = (String) AmountDetails.get("CommissionAmount") == null ? "0"
					: (String) AmountDetails.get("CommissionAmount");
					commissAmtRound = pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit);
					finalPayableAmt = Double.toString((Double.parseDouble(finalBean.isNull((String) AmountDetails.get("FinalPayableAmount"),"0")) + (Double.parseDouble(ExcessPremium))));
					royalfinal = finalPayableAmt;
					payableRound = pdis.decimalFormat(Double.parseDouble(finalPayableAmt), decimalDigit);					
			}
		//DebitNoteNo = finalBean.updateCommission(brokerBra,"3",PolicyNo,commissAmtRound,taxPercent);
		creditNoteNo = finalBean.getCreditNoteNo(PolicyNo);
		document = new Document(PageSize.A4, 50, 50, 80, 120);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					//DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					creditNoteNo=creditNoteNo.length()>0?creditNoteNo.substring(2):"";
					creditNoteNo=creditNoteNo.substring(0,2)+"-"+creditNoteNo.substring(2,4)+"-"+creditNoteNo.substring(4,5)+"-"+creditNoteNo.substring(5,creditNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(creditNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					/*tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);*/
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Credit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}else
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have credited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					StringBuffer goods = new StringBuffer();
					//goods.append("\nPREMIUM DUE IN RESPECT OF MARINE INSURANCE \n");
					tableCreation1.insertCell("\n   Premium due in respect of Marine Insurance", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.TOP| Rectangle.RIGHT,2, 5);
					double totalPremiumRate=0.0;
					double totalWarRate=0.0;
					String marinePremiumRate="";
					String warPremiumRate="";
					for(int i=0;i<finalCount;i++){
						goods.append("\n"+commodityDes[i]);
						goods.append(" ");
						/*goods.append("   "+packageDes[i]);
						goods.append(" ");*/
						if(invoice[i]!=null&&invoice[i].length()>0){
							goods.append("INV# "); 
							goods.append(invoice[i]);
							goods.append(" ");
						}
						tableCreation1.insertCellPad(goods.toString(), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						
						if(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE")){
							tableCreation1.insertCellPad("L/C No. "+lcNumber, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(bankName.length()>0 && !bankName.equals("NONE")){
							tableCreation1.insertCellPad("Bank Name : "+bankName, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(YES.equalsIgnoreCase(currencyOption))
						{
							commoditySum[i]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySum[i].replaceAll(",",""),currencyId, brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyShortName+" "+commoditySum[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						else
						{
							localSumround[i] = pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(localSumround[i].replaceAll(",",""),"1", brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyType1+" "+localSumround[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						//totalPremiumRate+=Double.parseDouble(premiumRate[i]);
						//totalWarRate+=Double.parseDouble(warRate[i]);
						/*tableCreation1.insertCell("   "+"MARINE PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(premiumSingle[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						vr=vr+(Double.parseDouble(premiumSingle[i]));
						tableCreation1.insertCell("   "+"WAR PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(warRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(warPremium[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
						vr=vr+(Double.parseDouble(warPremium[i]));*/
						if(finalCount==1)
						{
							marinePremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),6)+"%";
							warPremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(warRate[i]),6)+"%";
						}
						goods = new StringBuffer();
					}
					
					if(YES.equalsIgnoreCase(currencyOption))
					{
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(((Double.parseDouble(totalMarinePremium)/exchangeRate)+""), currencyId, brokerBra);
						Amt=Double.parseDouble(totalMarinePremium);
						Amt=Amt*-1;
						totalWarPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(totalWarPremium)/exchangeRate)+"", currencyId, brokerBra);
						Amt1=Double.parseDouble(totalWarPremium);
						Amt1=Amt1*-1;
						ExcessPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId, brokerBra);
						Amt2=Double.parseDouble(issuanceFee);
						Amt3=Double.parseDouble(issuanceFee);
						Amt2=Amt2*-1;
					}else
					{
						currencyId="1";
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(totalMarinePremium+"", currencyId, brokerBra);
						Amt=Double.parseDouble(totalMarinePremium);
						Amt=Amt*-1;
						totalWarPremium=new PremiumInputsBean().getRoundedValue(totalWarPremium, currencyId, brokerBra);
						Amt1=Double.parseDouble(totalWarPremium);
						Amt1=Amt1*-1;
						ExcessPremium=new PremiumInputsBean().getRoundedValue(ExcessPremium+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue(issuanceFee+"", currencyId, brokerBra);
						Amt2=Double.parseDouble(issuanceFee);
						Amt3=Double.parseDouble(issuanceFee);
						Amt2=Amt2*-1;
					}
					netPremium=(Amt+(Double.parseDouble(ExcessPremium))+Amt1+Amt2);
					if("N".equalsIgnoreCase(minPreYN))
					{
						tableCreation1.insertCell("   "+"Marine Premium "+marinePremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("("+pdis.decimalFormat(Amt, decimalDigit)+")"+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);

						tableCreation1.insertCell("   "+"War Premium "+warPremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("("+pdis.decimalFormat(Amt1, decimalDigit)+")"+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
					}else {
						tableCreation1.insertCell("   "+"Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("("+pdis.decimalFormat(Amt+Amt1, decimalDigit)+")"+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					}
//					vr=vr+(Double.parseDouble(pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit).replaceAll(",","")));
					
					//goods.append("\nTOTAL MARINE PREMIUM @"+pdis.decimalFormat(totalPremiumRate,4)+"\n");
					//goods.append("\nTOTAL WAR PREMIUM @"+pdis.decimalFormat(totalWarRate,4)+"\n");
					//tableCreation1.insertCell(goods.toString(), fontTextNormal, Rectangle.RECTANGLE,5, 0);
					//tableCreation1.insertCell("", fontTextNormal, Rectangle.RECTANGLE,1, 0);
					if(Double.parseDouble(ExcessPremium)!=0){
						tableCreation1.insertCell("   "+"Additional Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(ExcessPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,2, 5);
//					vr=vr+(Double.parseDouble(ExcessPremium));
					}
					
					//premium=pdis.decimalFormat(Double.parseDouble(premium)+(Double.parseDouble(ExcessPremium)), decimalDigit);
					//tableCreation1.insertCell(premium+TAB+TAB+TAB+TAB+"\n\n"+pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit), fontTextNormal, Rectangle.RECTANGLE,2, 5);
					if(commissionStatus.equalsIgnoreCase("Y") && "N".equalsIgnoreCase(noteType)) // Debit Commission Status
					{
						if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0 && !"0.0".equals(BrokerCommission))
						{
							tableCreation1.insertCell("   "+"Less Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							commissAmtRound=(Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
							Amt4=Double.parseDouble(commissAmtRound);
							Amt4=Amt4*-1;
							tableCreation1.insertCell("("+pdis.decimalFormat(Amt4, decimalDigit)+")"+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							netPremium=((Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))-Double.parseDouble(commissAmtRound))+(Double.parseDouble(issuanceFee));
							netPremium=netPremium*-1;
						}
					}
					tableCreation1.insertCell("\n"+"   "+"Issuance Fee\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
//					vr=vr+(Double.parseDouble(issuanceFee));
					issuanceFee=pdis.decimalFormat(Double.parseDouble(issuanceFee), decimalDigit);
					tableCreation1.insertCell("\n"+pdis.decimalFormat(Amt3, decimalDigit)+"\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
					String DubaiTradeStatus="";
					DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);
					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}else
					{
						tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}
//					logger.info("VR"+pdis.decimalFormat(vr, decimalDigit));
				/*	String vrword=pdis.decimalFormat(vr, decimalDigit).replaceAll(",","");
					String fileb = "";
					String finalPre = "";
					
					if (vrword.length() > 0&& vrword.indexOf('.') != -1) 
					{
						fileb = vrword.substring(vrword.indexOf('.') + 1,	vrword.length());
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
						finalPre = vrword.substring(0, vrword.indexOf('.'));
					} 
					else
					{
						fileb = "0";
						finalPre = "0";
					}
					finalPre = finalPre.replaceAll(",","");
					ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));*/
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " + fileb + "/"+fillsDigit+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " +convertWord.convertNumToWord(Integer.parseInt(fileb))  + " Fils only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					processor = new DefaultProcessor();
//					tableCreation1.insertCell(currencyType1+" "+processor.getName(pdis.decimalFormat(netPremium, decimalDigit).replaceAll(",","")) + " "+fills+" Only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107); 
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1); 
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Important Client Notice: The premium shown above is due for payment within a maximum period of 30 days from the date of this Debit Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					document.add(tableCreation.getTable());
					
/*					table = new PdfPTable(1);
					table.setWidthPercentage(100f);
					BaseFont arialFont;
					arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
					
					fontHead2 = new Font(arialFont, 8, Font.BOLD);
					fontHead1 = new Font(arialFont, 8, Font.NORMAL);
					font = new Font(arialFont, 7, Font.NORMAL);
					fontText = new Font(arialFont, 7, Font.NORMAL);
	
			String url;
			url = getUrl()+"\\logo_royal-sunalliance.jpg";
			logger.info("url is====" + url);
			PdfPTable headerTop;
			headerTop = new PdfPTable(2);
			PdfPTable headerLogo;
			headerLogo = new PdfPTable(1);
	
			cell = new PdfPCell(getHyperLinkedImageCell(150, 75, url));
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
			headerTop.addCell(cell);
	
			// FOR ADDRESS IMAGE
			headerLogo = new PdfPTable(1);
			url = getUrl()+"\\RoyalAddress.jpg";
			cell = new PdfPCell(getHyperLinkedImageCell(250, 150, url));
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
			
	
			Paragraph headerMain;
			headerMain = new Paragraph(new Phrase("", fontHead2));
			Paragraph header1,header2,header3,header4;
			header1 = new Paragraph(new Phrase("", font));
			header2 = new Paragraph(new Phrase("", font));
			header3 = new Paragraph(new Phrase("", font));
			header4 = new Paragraph(new Phrase("", font));
	
			headertable = new PdfPTable(4);
			headerLogo = new PdfPTable(1);
	
			cell = new PdfPCell(headerMain);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header1);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header2);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header3);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header4);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
	
	
			cell = new PdfPCell(headerTop);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
	
			blanktable = new PdfPTable(1);
			Paragraph blank;
			blank = new Paragraph("\t", fontHead2);
			cell = new PdfPCell(blank);
			cell.setBorderWidth(0);
			blanktable.addCell(cell);
	
			cell = new PdfPCell(blanktable);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debittable = new PdfPTable(1);
	
			debit = new Paragraph(new Phrase("Debit Note", fontHead2));
			cell = new PdfPCell(debit);
			cell.setHorizontalAlignment(1);
			cell.setBorderWidth(0);
			debittable.addCell(cell);
	
			cell = new PdfPCell(debittable);
			cell.setBorderWidth(1);
	
			cS4(cell);
			headertable.addCell(cell);
	
			debittablesplit = new PdfPTable(4);
			debittable1 = new PdfPTable(1);
	
			debit1 = new Paragraph(new Phrase("TO", fontHead1));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
			
			debit1 = new Paragraph(new Phrase("\n\n" + BrokerCompany, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(Brokeraddress1, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(BrokerAddress2, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(BrokerPoBox, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);

			debit1 = new Paragraph(new Phrase(BrokerEmirate, fontText));
			cell = new PdfPCell(debit1);
	
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
			debit1 = new Paragraph(new Phrase(BrokerCountry, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
	
			debittablesplit.addCell(cell);
			debittable1 = new PdfPTable(1);
			debit1 = new Paragraph(new Phrase("", fontHead2));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
			debittablesplit.addCell(cell);
	
			debittable1 = new PdfPTable(1);
	
			debit1 = new Paragraph(new Phrase("", fontHead2));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
			debittablesplit.addCell(cell);
			debittable1 = new PdfPTable(1);
			
			debit1 = new Paragraph(new Phrase("Debit Note No:"	+ DebitNoteNo, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			
			if(PolicyGenDate.indexOf(' ')!=-1){
				PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
			}
			debit1 = new Paragraph(new Phrase("Debit Note Date:"+ PolicyGenDate, fontText));
			cell = new PdfPCell(debit1);
	
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
	
			debittablesplit.addCell(cell);
	
			cell = new PdfPCell(debittablesplit);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
			{
				debit1 = new Paragraph("\n\n\n\nInsured:" + customerFirstName+ CustomerLastName, fontHead2);
			} 
			else 
			{
				debit1 = new Paragraph("\n\n\n\nInsured:" + cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName,fontHead2);
			}
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("\n\nIn accordance with your instructions we have issued the attached documentation and debited your account as per details as shown hereunder\n\n",fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Description", fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS3(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Amount in " + currencyType1, fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
			
			//For Tax Calculation
			String oriPre="0";
			String oriPremium="0";
			double finalPremium=0.00;
			double amtRoundTax=0.00;
			double amtRoundWithTax=0.00;
			oriPremium = payableRound;
			if(taxPercent>0)
			{
				if(payableRound.length() > 0 && payableRound.indexOf(',') == -1){
					oriPre = payableRound;
				}else{
					oriPre = payableRound.replaceAll(",","");
				}
				amtRoundTax = (Double.parseDouble(oriPre)*taxPercent)/100;
				amtRoundWithTax = Double.parseDouble(oriPre) + amtRoundTax;
				payableRound = Double.toString(amtRoundWithTax);
				if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
					commissAmtRound = commissAmtRound.replaceAll(",","");
				}
				finalPremium = amtRoundWithTax - Double.parseDouble(commissAmtRound);
				finalAmtRound = Double.toString(finalPremium);
			}
			//For Tax Calculation
	
			if ("Test".equalsIgnoreCase(usrModeController))	{
				debit1 = new Paragraph("Being the premium due on marine cargo insurance quote  no:\t\t"+ QuoteNo, fontHead1);
			}else 
			{
				debit1 = new Paragraph("Being the premium due on marine cargo insurance policy no:\t\t"+ PolicyNo, fontHead1);
			}
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS3(cell);
			headertable.addCell(cell);
			
			if(payableRound.length() >0 && payableRound.indexOf(',')!=-1){
				payableRound = payableRound.replaceAll(",","");
			}
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(payableRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS1(cell);
			headertable.addCell(cell);
	
			//For Tax New
			if(taxPercent>0)
			{
				String payAmtRounds;
				payAmtRounds="0";
				if(oriPremium.length() >0 && oriPremium.indexOf(',')!=-1){
					payAmtRounds = oriPremium.replaceAll(",","");
				}else{
					payAmtRounds=oriPremium;
				}
				debit1 = new Paragraph("Premium       :\t\t"+pdis.decimalFormat(Double.parseDouble(payAmtRounds),decimalDigit), fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
				
				debit1 = new Paragraph("", fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
	
				debit1 = new Paragraph("Premium Tax :\t\t"+pdis.decimalFormat(amtRoundTax,decimalDigit), fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
	
				debit1 = new Paragraph("", fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
			}
			//For Tax New
			String proCommission = "";
			String proCommissionAmt="";
			String fileb = "";
			String finalPre = "";
		if(commissionStatus.equalsIgnoreCase("Y")) // Debit Commission Status
		{
			if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0)
			{
				debit1 = new Paragraph("Less Commission @" + BrokerCommission,	fontText);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
				if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
					commissAmtRound = commissAmtRound.replaceAll(",","");
				}
				debit1 = new Paragraph("(" + pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+")",fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS1(cell);
				headertable.addCell(cell);
			}
			
			proCommission = finalBean.getProCommission(brokerBra,"3",PolicyNo);
			proCommissionAmt="";
			
			if(!"0".equals(proCommission)&&proCommission.length()>0)
			{
					debit1 = new Paragraph("Less Promotional Commission @" + proCommission,		fontText);
					cell = new PdfPCell(debit1);
					cell.setBorderWidth(0);
					cS3(cell);
					headertable.addCell(cell);
					
					proCommissionAmt = Double.toString((Double.parseDouble(royalfinal)*Double.parseDouble(proCommission)/100));
					if(!"0".equals(proCommissionAmt)&&proCommissionAmt.length()>0){
						finalAmtRound = Double.toString((Double.parseDouble(payableRound)-(Double.parseDouble(commissAmtRound)+Double.parseDouble(proCommissionAmt))));
					}
	
					debit1 = new Paragraph("("+pdis.decimalFormat(Double.parseDouble(proCommissionAmt),decimalDigit) + ")",
						fontHead1);
					cell = new PdfPCell(debit1);
					cell.setBorderWidth(0);
					cS1(cell);
					headertable.addCell(cell);
			}
				
			if (finalAmtRound.length() > 0&& finalAmtRound.indexOf('.') != -1) 
			{
				fileb = finalAmtRound.substring(finalAmtRound.indexOf('.') + 1,	finalAmtRound.length());
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
				finalPre = finalAmtRound.substring(0,finalAmtRound.indexOf('.'));
			} 
			else
			{
				fileb = "0";
				finalPre = "0";
			}
	
			finalPre = finalPre.replaceAll(",","");
			ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
			debit1 = new Paragraph(currencyType + TAB + ConvertToWords+ "\t\t and "+fills+TAB + fileb + "/"+fillsDigit+" only", fontText);
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS2(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Total", fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS1(horizontal(cell));
			headertable.addCell(cell);
	
			finalAmtRound = finalAmtRound.replaceAll(",","");
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(finalAmtRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
		}// IF Commission Status
		else
		{
			if (payableRound.length() > 0&& payableRound.indexOf('.') != -1) 
			{
				fileb = payableRound.substring(payableRound.indexOf('.') + 1,payableRound.length());
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
				finalPre = payableRound.substring(0,payableRound.indexOf('.'));
			} 
			else
			{
				fileb = "0";
				finalPre = "0";
			}
			finalPre = finalPre.replaceAll(",","");
			ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
			
			debit1 = new Paragraph(currencyType + TAB + ConvertToWords + "\t\t and "+fills+TAB + fileb + "/"+fillsDigit+" only", fontText);
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS2(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Total", fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS1(horizontal(cell));
			headertable.addCell(cell);
	
			payableRound = payableRound.replaceAll(",","");
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(payableRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
		}// Else Debit Commisssion
	
			debit1 = new Paragraph(
					"\n\nPLEASE NOTE:\n\n\nYour remittance in respect of the above transaction should be forwarded to us in order to ensure continuity of cover.We would appreciate you contacting us immediately if you have any queries relating to the above details of the attached documents", fontHead1);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
		
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph(
					"\n\n\n\nFor: "+braAddress+"\n",
					fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
	
			cS4(cell);
			headertable.addCell(cell);
	
			
			if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
			{
				String urlSign;
				urlSign = getUrl()+"\\"+signImage;
				Image signatureImage;
				signatureImage = Image.getInstance(urlSign);
				if("153".equals(cid)){
					signatureImage.scaleToFit(70,40);
				}else if("14".equals(cid)){
					signatureImage.scaleToFit(100,70);
				}else{
					signatureImage.scaleAbsolute(70, 54);
				}
				cell = new PdfPCell(signatureImage);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
				debit1 = new Paragraph("Authorised Signatory\n\n\n\n\n\n",fontHead2);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
			}
			else 
			{
				String urlSign;
				urlSign = getUrl()+"\\"+signImage;
				Image signatureImage;
				signatureImage = Image.getInstance(urlSign);
				if("153".equals(cid)){
					signatureImage.scaleToFit(70,40);
				}else if("14".equals(cid)){
					signatureImage.scaleToFit(100,70);
				}else{
					signatureImage.scaleAbsolute(70, 54);
				}
				cell = new PdfPCell(signatureImage);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
				debit1 = new Paragraph("Authorised Signatory\n\n\n\n\n\n",fontHead2);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
			}
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			startDate = dateFormat.format(date);
			
			debit1 = new Paragraph("Printed on\t\t\t" + startDate, fontHead1);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
			debit1 = new Paragraph("\n\n\n\n\n\n\n\n\n\n", fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
			cell = new PdfPCell(headertable);
			cell.setBorderWidth(0);
			table.addCell(cell);
			document.add(table);*/
	
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	
	public void writeDebitPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map premiumdetails;
			Map commoditydetails;
			premiumdetails = new HashMap();
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			double netPremium=0.0;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "", arAccountNo="", paymentMode="",minPreYN="";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "";
			String DebitNoteNo = "";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			String extraCoverId ="";
			String commodityDes[] = new String[0];
			String[] commoditySumIns = new String[0];
			String localSumIns[] = new String[0];
			String packageDes[] = new String[0];
			String[] invoice = new String[0];
			String[] premiumRate=new String[0];
			String[] premiumSingle=new String[0];
			String[] warRate=new String[0];
			String[] warPremium=new String[0];
			String[] commoditySum = new String[0];
			String[] localSumround = new String[0];	
			String tolDetails[][] = new String[0][0];
			String getPolictDetails[][] = new String[0][0];
			String getPolNo="";
			String getCertNo="";
			String getEndtNo="";
			premiumdetails = new HashMap();
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="";
			String commissionStatus = "Y"; // Debit Commission....
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			boolean fileExists=new File(filePath).exists();
			
			String issuanceFee="";
			String totalMarinePremium="", totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="", lcNumber="", bankName="";
			double exchangeRate=0.0;
			//rajesh world work stated
			String noteType=new policyInfo().getNoteType(PolicyNo);
			String productInfo[][]=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				logger.info("currency type"+currencyType+"-"+currencyType1);
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST DEBIT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "DN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				productInfo=getProductInfo(brokerBra, PolicyNo);
				if(productInfo!=null && productInfo.length>0)
				{
					productId=productInfo[0][0];
					productName=productInfo[0][1];
				}
					
				if("11".equals(productId))
				{
					rsa.opencoverpdf.finalprint dataBean=new rsa.opencoverpdf.finalprint();
					premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"POLICYNO",brokerBra,cid,"");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
				}
				else{
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);
				}
				if(getPolictDetails.length>0)
				{
					getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
					getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
					getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
				}
				currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				Map AmountDetails;
				AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					lcNumber = (String) premiumdetails.get("LcNumber") == null ? ""
							: (String) premiumdetails.get("LcNumber");// [0][33]);
					bankName = (String) premiumdetails.get("BankName") == null ? "": (String) premiumdetails.get("BankName");// [0][35]);
					bankName=bankName.equalsIgnoreCase("None")?"":bankName;
					SaleTermValue = (String) premiumdetails.get("SaleTermValue") == null ? "0"
							: (String) premiumdetails.get("SaleTermValue");// [0][19]);
					currencyId = (String) premiumdetails.get("currencyId") == null ? ""
							: (String) premiumdetails.get("currencyId");// [0][14]);
					exchangeRate = Double.parseDouble((String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate"));// [0][15]);
					extraCoverId = (String)premiumdetails.get("ExtraCoverId");
					commoditydetails = (HashMap) premiumdetails.get("commoditydetails");
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails.get("CustomerLastName") == null ? "": (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					/*Brokeraddress1 = (String) premiumdetails.get("Brokeraddress1") == null ? ""	: (String) premiumdetails.get("Brokeraddress1");// [0][51]);
					BrokerAddress2 = (String) premiumdetails.get("BrokerAddress2") == null ? "": (String) premiumdetails.get("BrokerAddress2");// [0][52]);
					BrokerCountry = (String) premiumdetails.get("BrokerCountry") == null ? "": (String) premiumdetails.get("BrokerCountry");// [0][54]);
					BrokerPoBox = (String) premiumdetails.get("BrokerPoBox") == null ? "0":("PO Box " +(String)premiumdetails.get("BrokerPoBox"));// [0][56]);
					BrokerEmirate = (String) premiumdetails.get("BrokerEmirate") == null ? "": (String) premiumdetails.get("BrokerEmirate");// [0][57]);*/
					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
//					BrokerCompany = (String) premiumdetails.get("BrokerCompany") == null ? "": (String) premiumdetails.get("BrokerCompany");// [0][84]);
					String [][] sourceInfo=new String[0][0];
					paymentMode = (String) premiumdetails.get("paymentMode") == null ? ""	: (String) premiumdetails.get("paymentMode");// [0][58]);
					if("CR".equalsIgnoreCase(paymentMode))
		        	  sourceInfo=getCustomerInfo((String) premiumdetails.get("debitCustomerId"), brokerBra);
		           else
		        	  sourceInfo=getSourceInfo(loginId, brokerBra);
					        	  
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
						arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
					}
					premium = (String) premiumdetails.get("Premium") == null ? "0"
							: (String) premiumdetails.get("Premium");
					issuanceFee= (String) premiumdetails.get("IssuanceFee") == null ? "0"
							: (String) premiumdetails.get("IssuanceFee");
					minPreYN = (String) premiumdetails.get("MIN_PRE_YN") == null ? "N"
							: (String) premiumdetails.get("MIN_PRE_YN");
					
					//if("Y".equalsIgnoreCase(minPreYN)){
					totalMarinePremium=(String) premiumdetails.get("MARINE_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("MARINE_PREMIUM");
					totalWarPremium=(String) premiumdetails.get("WAR_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("WAR_PREMIUM");
				/*	} else {
					totalMarinePremium=(String) premiumdetails.get("finalmarinepremium") == null ? "0"
							: (String) premiumdetails.get("finalmarinepremium");
					totalWarPremium=(String) premiumdetails.get("finalwarpremium") == null ? "0"
							: (String) premiumdetails.get("finalwarpremium");
					}*/
					
					totalSumIns = (String) commoditydetails.get("TotalCommoditySI") == null ? "0"
							: (String) commoditydetails.get("TotalCommoditySI");
									
					finalCount = Integer.parseInt((String) commoditydetails.get("finalCount"));
					commodityDes = new String[finalCount];
					commoditySumIns = new String[finalCount];
					packageDes = new String[finalCount];
					invoice=new String[finalCount];
					localSumIns = new String[finalCount];
					premiumRate=new String[finalCount];
					premiumSingle=new String[finalCount];
					warRate=new String[finalCount];
					warPremium=new String[finalCount];
					commoditySum = new String[finalCount];
					localSumround = new String[finalCount];
					for (int i = 0; i < finalCount; i++) 
					{
						
						commodityDes[i] = (String) commoditydetails.get("CommodityDescription" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("CommodityDescription" + (i + 1));// [0][9]);
						commoditySumIns[i] = (String) commoditydetails.get("CommoditySumInsured" + (i + 1)) == null ? "0"
								: (String) commoditydetails
										.get("CommoditySumInsured" + (i + 1));// [0][8]);
						packageDes[i] = (String) commoditydetails.get("PackageDescription_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("PackageDescription_arr" + (i + 1));// [i][65]);
						invoice[i] = (String) commoditydetails.get("Invoice_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("Invoice_arr"+ (i + 1));// [0][86]);
						localSumIns[i] = (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1)) == null ? "0"
								: (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1));// [0][25]);
						//Added by sathish Start
						premiumRate[i]=(String)commoditydetails.get("PremiumRate" + (i+1))==null?"0":(String)commoditydetails.get("PremiumRate" + (i+1));
						premiumSingle[i]=(String)commoditydetails.get("PremiumSingle" + (i+1))==null?"0":(String)commoditydetails.get("PremiumSingle" + (i+1));
						warRate[i]=(String)commoditydetails.get("WarRate" + (i+1))==null?"0":(String)commoditydetails.get("WarRate" + (i+1));
						warPremium[i]=(String)commoditydetails.get("WarPremium" + (i+1))==null?"0":(String)commoditydetails.get("WarPremium" + (i+1));
						//Added by sathish End
						
						//Added New
						double salePercent = 0.0;
						double sumLocal = 0.0;
						
						double sumLocalSale = 0.0;
						double toleranceval = 0.0;
						double tolValuePer = 0.0;
						double commoditySI = 0.0;
						double commoditySISale = 0.0;
						if(tolDetails.length>0)
						{
							toleranceval = Double.parseDouble(tolDetails[i][1]==null?"0":tolDetails[i][1]);
							tolValuePer = Double.parseDouble(tolDetails[0][2]==null?"0":tolDetails[0][2]);
						}
						salePercent = Double.parseDouble(SaleTermValue);
						sumLocal = Double.parseDouble(localSumIns[i]);
						sumLocalSale = sumLocal + (sumLocal * (salePercent / 100));
						sumLocalSale = sumLocalSale + toleranceval;
						commoditySI = Double.parseDouble(commoditySumIns[i]);
						commoditySISale = commoditySI	+ (commoditySI * (salePercent / 100));
						commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
						localSumround[i] = pdis.decimalFormat(sumLocalSale, decimalDigit);
						commoditySum[i] = pdis.decimalFormat(commoditySISale, decimalDigit);
						/*localSumIns[i] = pdis.decimalFormat(Double.parseDouble(localSumIns[i].replaceAll(",","")), decimalDigit);
						commoditySumIns[i]=pdis.decimalFormat(Double.parseDouble(commoditySumIns[i].replaceAll(",","")), decimalDigit);*/
						//End
					}
					
					
					//loginId = (String) premiumdetails.get("LoginID");// [0][60]);
					ExcessPremium = (String) premiumdetails.get("ExcessPremium") == null ? "0": (String) premiumdetails.get("ExcessPremium");
					
					finalDeductedAmt = (String) AmountDetails.get("FinalPayableAmountAfterDeduction") == null ? "0"	: (String) AmountDetails.get("FinalPayableAmountAfterDeduction");
					finalAmtRound = pdis.decimalFormat(Double.parseDouble(finalDeductedAmt), decimalDigit);
			
					StringTokenizer token1;
					token1 = new StringTokenizer(finalDeductedAmt, ".");
					finalAmtRs = (String) token1.nextToken();
					finalAmtPs = (String) token1.nextToken();
					logger.info("finalAmtRs"+ finalAmtRs);
					logger.info("finalAmtPs>>>>>>>>>>>>."	+ finalAmtPs);
					finalAmtRs = finalAmtRs.replaceAll(",","");
					finalRsConversion = Integer.parseInt(finalAmtRs);
					ConvertToWords = convertWord.convertNumToWord(finalRsConversion);
					commissionAmount = (String) AmountDetails.get("CommissionAmount") == null ? "0"
					: (String) AmountDetails.get("CommissionAmount");
					commissAmtRound = pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit);
					finalPayableAmt = Double.toString((Double.parseDouble(finalBean.isNull((String) AmountDetails.get("FinalPayableAmount"),"0")) + (Double.parseDouble(ExcessPremium))));
					royalfinal = finalPayableAmt;
					payableRound = pdis.decimalFormat(Double.parseDouble(finalPayableAmt), decimalDigit);					
			}
		DebitNoteNo = finalBean.updateCommission(brokerBra,"3",PolicyNo,commissAmtRound,taxPercent);
		document = new Document(PageSize.A4, 50, 50, 50, 60);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					//DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					//DebitNoteNo=DebitNoteNo.substring(0,2)+"-"+DebitNoteNo.substring(2,4)+"-"+DebitNoteNo.substring(4,5)+"-"+DebitNoteNo.substring(5,DebitNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(DebitNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					/*tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);*/
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Debit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}else
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have debited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					StringBuffer goods = new StringBuffer();
					//goods.append("\nPREMIUM DUE IN RESPECT OF MARINE INSURANCE \n");
					tableCreation1.insertCell("\n   Premium due in respect of Marine Insurance", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.TOP| Rectangle.RIGHT,2, 5);
					double totalPremiumRate=0.0;
					double totalWarRate=0.0;
					String marinePremiumRate="";
					String warPremiumRate="";
					for(int i=0;i<finalCount;i++){
						goods.append("\n"+commodityDes[i]);
						goods.append(" ");
						/*goods.append("   "+packageDes[i]);
						goods.append(" ");*/
						if(invoice[i]!=null&&invoice[i].length()>0){
							goods.append("INV# "); 
							goods.append(invoice[i]);
							goods.append(" ");
						}
						tableCreation1.insertCellPad(goods.toString(), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						
						if(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE")){
							tableCreation1.insertCellPad("L/C No. "+lcNumber, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(bankName.length()>0 && !bankName.equals("NONE")){
							tableCreation1.insertCellPad("Bank Name : "+bankName, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(YES.equalsIgnoreCase(currencyOption))
						{
							commoditySum[i]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySum[i].replaceAll(",",""),currencyId, brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyShortName+" "+commoditySum[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						else
						{
							localSumround[i] = pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(localSumround[i].replaceAll(",",""),"1", brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyType1+" "+localSumround[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						//totalPremiumRate+=Double.parseDouble(premiumRate[i]);
						//totalWarRate+=Double.parseDouble(warRate[i]);
						/*tableCreation1.insertCell("   "+"MARINE PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(premiumSingle[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						vr=vr+(Double.parseDouble(premiumSingle[i]));
						tableCreation1.insertCell("   "+"WAR PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(warRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(warPremium[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
						vr=vr+(Double.parseDouble(warPremium[i]));*/
						if(finalCount==1)
						{
							marinePremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),6)+"%";
							warPremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(warRate[i]),6)+"%";
						}
						goods = new StringBuffer();
					}
					
					if(YES.equalsIgnoreCase(currencyOption))
					{
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(((Double.parseDouble(totalMarinePremium)/exchangeRate)+""), currencyId, brokerBra);
						totalWarPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(totalWarPremium)/exchangeRate)+"", currencyId, brokerBra);
						ExcessPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId, brokerBra);
					}else
					{
						currencyId="1";
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(totalMarinePremium+"", currencyId, brokerBra);
						totalWarPremium=new PremiumInputsBean().getRoundedValue(totalWarPremium, currencyId, brokerBra);
						ExcessPremium=new PremiumInputsBean().getRoundedValue(ExcessPremium+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue(issuanceFee+"", currencyId, brokerBra);
					}
					netPremium=(Double.parseDouble(totalMarinePremium)+(Double.parseDouble(ExcessPremium))+(Double.parseDouble(totalWarPremium))+(Double.parseDouble(issuanceFee)));
					if("N".equalsIgnoreCase(minPreYN))
					{
						tableCreation1.insertCell("   "+"Marine Premium "+marinePremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalMarinePremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);

						tableCreation1.insertCell("   "+"War Premium "+warPremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
					}else {
						tableCreation1.insertCell("   "+"Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalMarinePremium)+Double.parseDouble(totalWarPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					}
//					vr=vr+(Double.parseDouble(pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit).replaceAll(",","")));
					
					//goods.append("\nTOTAL MARINE PREMIUM @"+pdis.decimalFormat(totalPremiumRate,4)+"\n");
					//goods.append("\nTOTAL WAR PREMIUM @"+pdis.decimalFormat(totalWarRate,4)+"\n");
					//tableCreation1.insertCell(goods.toString(), fontTextNormal, Rectangle.RECTANGLE,5, 0);
					//tableCreation1.insertCell("", fontTextNormal, Rectangle.RECTANGLE,1, 0);
					if(Double.parseDouble(ExcessPremium)!=0){
						tableCreation1.insertCell("   "+"Additional Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(ExcessPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,2, 5);
//					vr=vr+(Double.parseDouble(ExcessPremium));
					}
					
					//premium=pdis.decimalFormat(Double.parseDouble(premium)+(Double.parseDouble(ExcessPremium)), decimalDigit);
					//tableCreation1.insertCell(premium+TAB+TAB+TAB+TAB+"\n\n"+pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit), fontTextNormal, Rectangle.RECTANGLE,2, 5);
					if(commissionStatus.equalsIgnoreCase("Y") && "N".equalsIgnoreCase(noteType)) // Debit Commission Status
					{
						if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0 && !"0.0".equals(BrokerCommission))
						{
							tableCreation1.insertCell("   "+"Less Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							commissAmtRound=(Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							netPremium=((Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))-Double.parseDouble(commissAmtRound))+(Double.parseDouble(issuanceFee));
						}
					}
					tableCreation1.insertCell("\n"+"   "+"Issuance Fee\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
//					vr=vr+(Double.parseDouble(issuanceFee));
					issuanceFee=pdis.decimalFormat(Double.parseDouble(issuanceFee), decimalDigit);
					tableCreation1.insertCell("\n"+issuanceFee+"\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
					String DubaiTradeStatus="";
					DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);
					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}else
					{
						tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}
//					logger.info("VR"+pdis.decimalFormat(vr, decimalDigit));
				/*	String vrword=pdis.decimalFormat(vr, decimalDigit).replaceAll(",","");
					String fileb = "";
					String finalPre = "";
					
					if (vrword.length() > 0&& vrword.indexOf('.') != -1) 
					{
						fileb = vrword.substring(vrword.indexOf('.') + 1,	vrword.length());
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
						finalPre = vrword.substring(0, vrword.indexOf('.'));
					} 
					else
					{
						fileb = "0";
						finalPre = "0";
					}
					finalPre = finalPre.replaceAll(",","");
					ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));*/
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " + fileb + "/"+fillsDigit+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " +convertWord.convertNumToWord(Integer.parseInt(fileb))  + " Fils only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					processor = new DefaultProcessor();
//					tableCreation1.insertCell(currencyType1+" "+processor.getName(pdis.decimalFormat(netPremium, decimalDigit).replaceAll(",","")) + " "+fills+" Only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107);  
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107);  
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Important Client Notice: The premium shown above is due for payment within a maximum period of 30 days from the date of this Debit Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					document.add(tableCreation.getTable());
					
/*					table = new PdfPTable(1);
					table.setWidthPercentage(100f);
					BaseFont arialFont;
					arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
					
					fontHead2 = new Font(arialFont, 8, Font.BOLD);
					fontHead1 = new Font(arialFont, 8, Font.NORMAL);
					font = new Font(arialFont, 7, Font.NORMAL);
					fontText = new Font(arialFont, 7, Font.NORMAL);
	
			String url;
			url = getUrl()+"\\logo_royal-sunalliance.jpg";
			logger.info("url is====" + url);
			PdfPTable headerTop;
			headerTop = new PdfPTable(2);
			PdfPTable headerLogo;
			headerLogo = new PdfPTable(1);
	
			cell = new PdfPCell(getHyperLinkedImageCell(150, 75, url));
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
			headerTop.addCell(cell);
	
			// FOR ADDRESS IMAGE
			headerLogo = new PdfPTable(1);
			url = getUrl()+"\\RoyalAddress.jpg";
			cell = new PdfPCell(getHyperLinkedImageCell(250, 150, url));
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
			
	
			Paragraph headerMain;
			headerMain = new Paragraph(new Phrase("", fontHead2));
			Paragraph header1,header2,header3,header4;
			header1 = new Paragraph(new Phrase("", font));
			header2 = new Paragraph(new Phrase("", font));
			header3 = new Paragraph(new Phrase("", font));
			header4 = new Paragraph(new Phrase("", font));
	
			headertable = new PdfPTable(4);
			headerLogo = new PdfPTable(1);
	
			cell = new PdfPCell(headerMain);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header1);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header2);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header3);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
			cell = new PdfPCell(header4);
			cell.setBorderWidth(0);
			headerLogo.addCell(cell);
	
	
			cell = new PdfPCell(headerLogo);
			cell.setBorderWidth(0);
	
	
			cell = new PdfPCell(headerTop);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
	
			blanktable = new PdfPTable(1);
			Paragraph blank;
			blank = new Paragraph("\t", fontHead2);
			cell = new PdfPCell(blank);
			cell.setBorderWidth(0);
			blanktable.addCell(cell);
	
			cell = new PdfPCell(blanktable);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debittable = new PdfPTable(1);
	
			debit = new Paragraph(new Phrase("Debit Note", fontHead2));
			cell = new PdfPCell(debit);
			cell.setHorizontalAlignment(1);
			cell.setBorderWidth(0);
			debittable.addCell(cell);
	
			cell = new PdfPCell(debittable);
			cell.setBorderWidth(1);
	
			cS4(cell);
			headertable.addCell(cell);
	
			debittablesplit = new PdfPTable(4);
			debittable1 = new PdfPTable(1);
	
			debit1 = new Paragraph(new Phrase("TO", fontHead1));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
			
			debit1 = new Paragraph(new Phrase("\n\n" + BrokerCompany, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(Brokeraddress1, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(BrokerAddress2, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			debit1 = new Paragraph(new Phrase(BrokerPoBox, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);

			debit1 = new Paragraph(new Phrase(BrokerEmirate, fontText));
			cell = new PdfPCell(debit1);
	
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
			debit1 = new Paragraph(new Phrase(BrokerCountry, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
	
			debittablesplit.addCell(cell);
			debittable1 = new PdfPTable(1);
			debit1 = new Paragraph(new Phrase("", fontHead2));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
			debittablesplit.addCell(cell);
	
			debittable1 = new PdfPTable(1);
	
			debit1 = new Paragraph(new Phrase("", fontHead2));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
			debittablesplit.addCell(cell);
			debittable1 = new PdfPTable(1);
			
			debit1 = new Paragraph(new Phrase("Debit Note No:"	+ DebitNoteNo, fontText));
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			
			if(PolicyGenDate.indexOf(' ')!=-1){
				PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
			}
			debit1 = new Paragraph(new Phrase("Debit Note Date:"+ PolicyGenDate, fontText));
			cell = new PdfPCell(debit1);
	
			cell.setBorderWidth(0);
			debittable1.addCell(cell);
	
			cell = new PdfPCell(debittable1);
			cell.setBorderWidth(0);
	
			debittablesplit.addCell(cell);
	
			cell = new PdfPCell(debittablesplit);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
			{
				debit1 = new Paragraph("\n\n\n\nInsured:" + customerFirstName+ CustomerLastName, fontHead2);
			} 
			else 
			{
				debit1 = new Paragraph("\n\n\n\nInsured:" + cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName,fontHead2);
			}
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("\n\nIn accordance with your instructions we have issued the attached documentation and debited your account as per details as shown hereunder\n\n",fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Description", fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS3(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Amount in " + currencyType1, fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
			
			//For Tax Calculation
			String oriPre="0";
			String oriPremium="0";
			double finalPremium=0.00;
			double amtRoundTax=0.00;
			double amtRoundWithTax=0.00;
			oriPremium = payableRound;
			if(taxPercent>0)
			{
				if(payableRound.length() > 0 && payableRound.indexOf(',') == -1){
					oriPre = payableRound;
				}else{
					oriPre = payableRound.replaceAll(",","");
				}
				amtRoundTax = (Double.parseDouble(oriPre)*taxPercent)/100;
				amtRoundWithTax = Double.parseDouble(oriPre) + amtRoundTax;
				payableRound = Double.toString(amtRoundWithTax);
				if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
					commissAmtRound = commissAmtRound.replaceAll(",","");
				}
				finalPremium = amtRoundWithTax - Double.parseDouble(commissAmtRound);
				finalAmtRound = Double.toString(finalPremium);
			}
			//For Tax Calculation
	
			if ("Test".equalsIgnoreCase(usrModeController))	{
				debit1 = new Paragraph("Being the premium due on marine cargo insurance quote  no:\t\t"+ QuoteNo, fontHead1);
			}else 
			{
				debit1 = new Paragraph("Being the premium due on marine cargo insurance policy no:\t\t"+ PolicyNo, fontHead1);
			}
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS3(cell);
			headertable.addCell(cell);
			
			if(payableRound.length() >0 && payableRound.indexOf(',')!=-1){
				payableRound = payableRound.replaceAll(",","");
			}
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(payableRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS1(cell);
			headertable.addCell(cell);
	
			//For Tax New
			if(taxPercent>0)
			{
				String payAmtRounds;
				payAmtRounds="0";
				if(oriPremium.length() >0 && oriPremium.indexOf(',')!=-1){
					payAmtRounds = oriPremium.replaceAll(",","");
				}else{
					payAmtRounds=oriPremium;
				}
				debit1 = new Paragraph("Premium       :\t\t"+pdis.decimalFormat(Double.parseDouble(payAmtRounds),decimalDigit), fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
				
				debit1 = new Paragraph("", fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
	
				debit1 = new Paragraph("Premium Tax :\t\t"+pdis.decimalFormat(amtRoundTax,decimalDigit), fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
	
				debit1 = new Paragraph("", fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
			}
			//For Tax New
			String proCommission = "";
			String proCommissionAmt="";
			String fileb = "";
			String finalPre = "";
		if(commissionStatus.equalsIgnoreCase("Y")) // Debit Commission Status
		{
			if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0)
			{
				debit1 = new Paragraph("Less Commission @" + BrokerCommission,	fontText);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS3(cell);
				headertable.addCell(cell);
				if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
					commissAmtRound = commissAmtRound.replaceAll(",","");
				}
				debit1 = new Paragraph("(" + pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+")",fontHead1);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS1(cell);
				headertable.addCell(cell);
			}
			
			proCommission = finalBean.getProCommission(brokerBra,"3",PolicyNo);
			proCommissionAmt="";
			
			if(!"0".equals(proCommission)&&proCommission.length()>0)
			{
					debit1 = new Paragraph("Less Promotional Commission @" + proCommission,		fontText);
					cell = new PdfPCell(debit1);
					cell.setBorderWidth(0);
					cS3(cell);
					headertable.addCell(cell);
					
					proCommissionAmt = Double.toString((Double.parseDouble(royalfinal)*Double.parseDouble(proCommission)/100));
					if(!"0".equals(proCommissionAmt)&&proCommissionAmt.length()>0){
						finalAmtRound = Double.toString((Double.parseDouble(payableRound)-(Double.parseDouble(commissAmtRound)+Double.parseDouble(proCommissionAmt))));
					}
	
					debit1 = new Paragraph("("+pdis.decimalFormat(Double.parseDouble(proCommissionAmt),decimalDigit) + ")",
						fontHead1);
					cell = new PdfPCell(debit1);
					cell.setBorderWidth(0);
					cS1(cell);
					headertable.addCell(cell);
			}
				
			if (finalAmtRound.length() > 0&& finalAmtRound.indexOf('.') != -1) 
			{
				fileb = finalAmtRound.substring(finalAmtRound.indexOf('.') + 1,	finalAmtRound.length());
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
				finalPre = finalAmtRound.substring(0,finalAmtRound.indexOf('.'));
			} 
			else
			{
				fileb = "0";
				finalPre = "0";
			}
	
			finalPre = finalPre.replaceAll(",","");
			ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
			debit1 = new Paragraph(currencyType + TAB + ConvertToWords+ "\t\t and "+fills+TAB + fileb + "/"+fillsDigit+" only", fontText);
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS2(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Total", fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS1(horizontal(cell));
			headertable.addCell(cell);
	
			finalAmtRound = finalAmtRound.replaceAll(",","");
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(finalAmtRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
		}// IF Commission Status
		else
		{
			if (payableRound.length() > 0&& payableRound.indexOf('.') != -1) 
			{
				fileb = payableRound.substring(payableRound.indexOf('.') + 1,payableRound.length());
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
				finalPre = payableRound.substring(0,payableRound.indexOf('.'));
			} 
			else
			{
				fileb = "0";
				finalPre = "0";
			}
			finalPre = finalPre.replaceAll(",","");
			ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));
			
			debit1 = new Paragraph(currencyType + TAB + ConvertToWords + "\t\t and "+fills+TAB + fileb + "/"+fillsDigit+" only", fontText);
	
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS2(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph("Total", fontText);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			
			cS1(horizontal(cell));
			headertable.addCell(cell);
	
			payableRound = payableRound.replaceAll(",","");
			debit1 = new Paragraph(pdis.decimalFormat(Double.parseDouble(payableRound),decimalDigit), fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(1);
			cS1(cell);
			headertable.addCell(cell);
		}// Else Debit Commisssion
	
			debit1 = new Paragraph(
					"\n\nPLEASE NOTE:\n\n\nYour remittance in respect of the above transaction should be forwarded to us in order to ensure continuity of cover.We would appreciate you contacting us immediately if you have any queries relating to the above details of the attached documents", fontHead1);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
		
			cS4(cell);
			headertable.addCell(cell);
	
			debit1 = new Paragraph(
					"\n\n\n\nFor: "+braAddress+"\n",
					fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
	
			cS4(cell);
			headertable.addCell(cell);
	
			
			if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
			{
				String urlSign;
				urlSign = getUrl()+"\\"+signImage;
				Image signatureImage;
				signatureImage = Image.getInstance(urlSign);
				if("153".equals(cid)){
					signatureImage.scaleToFit(70,40);
				}else if("14".equals(cid)){
					signatureImage.scaleToFit(100,70);
				}else{
					signatureImage.scaleAbsolute(70, 54);
				}
				cell = new PdfPCell(signatureImage);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
				debit1 = new Paragraph("Authorised Signatory\n\n\n\n\n\n",fontHead2);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
			}
			else 
			{
				String urlSign;
				urlSign = getUrl()+"\\"+signImage;
				Image signatureImage;
				signatureImage = Image.getInstance(urlSign);
				if("153".equals(cid)){
					signatureImage.scaleToFit(70,40);
				}else if("14".equals(cid)){
					signatureImage.scaleToFit(100,70);
				}else{
					signatureImage.scaleAbsolute(70, 54);
				}
				cell = new PdfPCell(signatureImage);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
				debit1 = new Paragraph("Authorised Signatory\n\n\n\n\n\n",fontHead2);
				cell = new PdfPCell(debit1);
				cell.setBorderWidth(0);
				cS4(cell);
				headertable.addCell(cell);
			}
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			startDate = dateFormat.format(date);
			
			debit1 = new Paragraph("Printed on\t\t\t" + startDate, fontHead1);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
			debit1 = new Paragraph("\n\n\n\n\n\n\n\n\n\n", fontHead2);
			cell = new PdfPCell(debit1);
			cell.setBorderWidth(0);
			cS4(cell);
			headertable.addCell(cell);
			cell = new PdfPCell(headertable);
			cell.setBorderWidth(0);
			table.addCell(cell);
			document.add(table);*/
	
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	
	public void writeReceiptPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map premiumdetails;
			Map commoditydetails;
			premiumdetails = new HashMap();
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			double netPremium=0.0;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "", arAccountNo="", paymentMode="",minPreYN="";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "";
			String DebitNoteNo = "";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			//String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			//String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			//String extraCoverId ="";
			String commodityDes[] = new String[0];
			String[] commoditySumIns = new String[0];
			String localSumIns[] = new String[0];
			String packageDes[] = new String[0];
			String[] invoice = new String[0];
			String[] premiumRate=new String[0];
			String[] premiumSingle=new String[0];
			String[] warRate=new String[0];
			String[] warPremium=new String[0];
			String[] commoditySum = new String[0];
			String[] localSumround = new String[0];	
			String tolDetails[][] = new String[0][0];
			String getPolictDetails[][] = new String[0][0];
			String getPolNo="";
			String getCertNo="";
			String getEndtNo="";
			premiumdetails = new HashMap();
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="";
			String commissionStatus = "Y"; // Debit Commission....
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			boolean fileExists=new File(filePath).exists();
			
			String issuanceFee="";
			String totalMarinePremium="", totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="", lcNumber="", bankName="";
			double exchangeRate=0.0;
			//rajesh world work stated
			String noteType=new policyInfo().getNoteType(PolicyNo);
			String productInfo[][]=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				logger.info("currency type"+currencyType+"-"+currencyType1);
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST RECEIPT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "RN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				//PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				productInfo=getProductInfo(brokerBra, PolicyNo);
				if(productInfo!=null && productInfo.length>0)
				{
					productId=productInfo[0][0];
					productName=productInfo[0][1];
				}
					
				if("11".equals(productId))
				{
					rsa.opencoverpdf.finalprint dataBean=new rsa.opencoverpdf.finalprint();
					premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"POLICYNO",brokerBra,cid,"");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
				}
				else{
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
					getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);
				}
				if(getPolictDetails.length>0)
				{
					getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
					getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
					getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
				}
				currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				Map AmountDetails;
				AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					lcNumber = (String) premiumdetails.get("LcNumber") == null ? ""
							: (String) premiumdetails.get("LcNumber");// [0][33]);
					bankName = (String) premiumdetails.get("BankName") == null ? "": (String) premiumdetails.get("BankName");// [0][35]);
					bankName=bankName.equalsIgnoreCase("None")?"":bankName;
					SaleTermValue = (String) premiumdetails.get("SaleTermValue") == null ? "0"
							: (String) premiumdetails.get("SaleTermValue");// [0][19]);
					currencyId = (String) premiumdetails.get("currencyId") == null ? ""
							: (String) premiumdetails.get("currencyId");// [0][14]);
					exchangeRate = Double.parseDouble((String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate"));// [0][15]);
					//extraCoverId = (String)premiumdetails.get("ExtraCoverId");
					commoditydetails = (HashMap) premiumdetails.get("commoditydetails");
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails.get("CustomerLastName") == null ? "": (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					/*Brokeraddress1 = (String) premiumdetails.get("Brokeraddress1") == null ? ""	: (String) premiumdetails.get("Brokeraddress1");// [0][51]);
					BrokerAddress2 = (String) premiumdetails.get("BrokerAddress2") == null ? "": (String) premiumdetails.get("BrokerAddress2");// [0][52]);
					BrokerCountry = (String) premiumdetails.get("BrokerCountry") == null ? "": (String) premiumdetails.get("BrokerCountry");// [0][54]);
					BrokerPoBox = (String) premiumdetails.get("BrokerPoBox") == null ? "0":("PO Box " +(String)premiumdetails.get("BrokerPoBox"));// [0][56]);
					BrokerEmirate = (String) premiumdetails.get("BrokerEmirate") == null ? "": (String) premiumdetails.get("BrokerEmirate");// [0][57]);*/
					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
//					BrokerCompany = (String) premiumdetails.get("BrokerCompany") == null ? "": (String) premiumdetails.get("BrokerCompany");// [0][84]);
					String [][] sourceInfo=new String[0][0];
					paymentMode = (String) premiumdetails.get("paymentMode") == null ? ""	: (String) premiumdetails.get("paymentMode");// [0][58]);
					/*if("CR".equalsIgnoreCase(paymentMode))
		        	  sourceInfo=getCustomerInfo((String) premiumdetails.get("debitCustomerId"), brokerBra);
		           else*/
		        	  sourceInfo=getReceiptCustomerInfo((String) premiumdetails.get("CustomerId"), (String) premiumdetails.get("CustomerId"));
					        	  
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
					}
					premium = (String) premiumdetails.get("Premium") == null ? "0"
							: (String) premiumdetails.get("Premium");
					issuanceFee= (String) premiumdetails.get("IssuanceFee") == null ? "0"
							: (String) premiumdetails.get("IssuanceFee");
					minPreYN = (String) premiumdetails.get("MIN_PRE_YN") == null ? "N"
							: (String) premiumdetails.get("MIN_PRE_YN");
					
					//if("Y".equalsIgnoreCase(minPreYN)){
					totalMarinePremium=(String) premiumdetails.get("MARINE_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("MARINE_PREMIUM");
					totalWarPremium=(String) premiumdetails.get("WAR_PREMIUM") == null ? "0"
							: (String) premiumdetails.get("WAR_PREMIUM");
					/*} else {
					totalMarinePremium=(String) premiumdetails.get("finalmarinepremium") == null ? "0"
							: (String) premiumdetails.get("finalmarinepremium");
					totalWarPremium=(String) premiumdetails.get("finalwarpremium") == null ? "0"
							: (String) premiumdetails.get("finalwarpremium");
					}*/
					
					//totalSumIns = (String) commoditydetails.get("TotalCommoditySI") == null ? "0"
					//		: (String) commoditydetails.get("TotalCommoditySI");
					
					
					finalCount = Integer.parseInt((String) commoditydetails.get("finalCount"));
					commodityDes = new String[finalCount];
					commoditySumIns = new String[finalCount];
					packageDes = new String[finalCount];
					invoice=new String[finalCount];
					localSumIns = new String[finalCount];
					premiumRate=new String[finalCount];
					premiumSingle=new String[finalCount];
					warRate=new String[finalCount];
					warPremium=new String[finalCount];
					commoditySum = new String[finalCount];
					localSumround = new String[finalCount];
					for (int i = 0; i < finalCount; i++) 
					{
						
						commodityDes[i] = (String) commoditydetails.get("CommodityDescription" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("CommodityDescription" + (i + 1));// [0][9]);
						commoditySumIns[i] = (String) commoditydetails.get("CommoditySumInsured" + (i + 1)) == null ? "0"
								: (String) commoditydetails
										.get("CommoditySumInsured" + (i + 1));// [0][8]);
						packageDes[i] = (String) commoditydetails.get("PackageDescription_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("PackageDescription_arr" + (i + 1));// [i][65]);
						invoice[i] = (String) commoditydetails.get("Invoice_arr" + (i + 1)) == null ? ""
								: (String) commoditydetails.get("Invoice_arr"+ (i + 1));// [0][86]);
						localSumIns[i] = (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1)) == null ? "0"
								: (String) commoditydetails.get("SumInsuredLocal_arr" + (i + 1));// [0][25]);
						//Added by sathish Start
						premiumRate[i]=(String)commoditydetails.get("PremiumRate" + (i+1))==null?"0":(String)commoditydetails.get("PremiumRate" + (i+1));
						premiumSingle[i]=(String)commoditydetails.get("PremiumSingle" + (i+1))==null?"0":(String)commoditydetails.get("PremiumSingle" + (i+1));
						warRate[i]=(String)commoditydetails.get("WarRate" + (i+1))==null?"0":(String)commoditydetails.get("WarRate" + (i+1));
						warPremium[i]=(String)commoditydetails.get("WarPremium" + (i+1))==null?"0":(String)commoditydetails.get("WarPremium" + (i+1));
						//Added by sathish End
						
						//Added New
						double salePercent = 0.0;
						double sumLocal = 0.0;
						
						double sumLocalSale = 0.0;
						double toleranceval = 0.0;
						double tolValuePer = 0.0;
						double commoditySI = 0.0;
						double commoditySISale = 0.0;
						if(tolDetails.length>0)
						{
							toleranceval = Double.parseDouble(tolDetails[i][1]==null?"0":tolDetails[i][1]);
							tolValuePer = Double.parseDouble(tolDetails[0][2]==null?"0":tolDetails[0][2]);
						}
						salePercent = Double.parseDouble(SaleTermValue);
						sumLocal = Double.parseDouble(localSumIns[i]);
						sumLocalSale = sumLocal + (sumLocal * (salePercent / 100));
						sumLocalSale = sumLocalSale + toleranceval;
						commoditySI = Double.parseDouble(commoditySumIns[i]);
						commoditySISale = commoditySI	+ (commoditySI * (salePercent / 100));
						commoditySISale = commoditySISale+ (commoditySISale * (tolValuePer / 100));
						localSumround[i] = pdis.decimalFormat(sumLocalSale, decimalDigit);
						commoditySum[i] = pdis.decimalFormat(commoditySISale, decimalDigit);
						/*localSumIns[i] = pdis.decimalFormat(Double.parseDouble(localSumIns[i].replaceAll(",","")), decimalDigit);
						commoditySumIns[i]=pdis.decimalFormat(Double.parseDouble(commoditySumIns[i].replaceAll(",","")), decimalDigit);*/
						//End
					}
					
					
					//loginId = (String) premiumdetails.get("LoginID");// [0][60]);
					ExcessPremium = (String) premiumdetails.get("ExcessPremium") == null ? "0": (String) premiumdetails.get("ExcessPremium");
					
					finalDeductedAmt = (String) AmountDetails.get("FinalPayableAmountAfterDeduction") == null ? "0"	: (String) AmountDetails.get("FinalPayableAmountAfterDeduction");
					finalAmtRound = pdis.decimalFormat(Double.parseDouble(finalDeductedAmt), decimalDigit);
			
					StringTokenizer token1;
					token1 = new StringTokenizer(finalDeductedAmt, ".");
					finalAmtRs = (String) token1.nextToken();
					finalAmtPs = (String) token1.nextToken();
					logger.info("finalAmtRs"+ finalAmtRs);
					logger.info("finalAmtPs>>>>>>>>>>>>."	+ finalAmtPs);
					finalAmtRs = finalAmtRs.replaceAll(",","");
					finalRsConversion = Integer.parseInt(finalAmtRs);
					ConvertToWords = convertWord.convertNumToWord(finalRsConversion);
					commissionAmount = (String) AmountDetails.get("CommissionAmount") == null ? "0"
					: (String) AmountDetails.get("CommissionAmount");
					commissAmtRound = pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit);
					finalPayableAmt = Double.toString((Double.parseDouble(finalBean.isNull((String) AmountDetails.get("FinalPayableAmount"),"0")) + (Double.parseDouble(ExcessPremium))));
					royalfinal = finalPayableAmt;
					payableRound = pdis.decimalFormat(Double.parseDouble(finalPayableAmt), decimalDigit);					
			}
		DebitNoteNo = finalBean.updateCommission(brokerBra,"3",PolicyNo,commissAmtRound,taxPercent);
		document = new Document(PageSize.A4, 50, 50, 80, 120);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID RECEIPT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					//DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					//DebitNoteNo=DebitNoteNo.substring(0,2)+"-"+DebitNoteNo.substring(2,4)+"-"+DebitNoteNo.substring(4,5)+"-"+DebitNoteNo.substring(5,DebitNoteNo.length());
					tableCreation1.insertCell(""+TAB, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					//if(PolicyGenDate.indexOf(' ')!=-1){
					//	PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					//}
					tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(""+TAB, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(""+TAB, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Receipt Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}else
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have received your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					StringBuffer goods = new StringBuffer();
					//goods.append("\nPREMIUM DUE IN RESPECT OF MARINE INSURANCE \n");
					tableCreation1.insertCell("\n   Premium due in respect of Marine Insurance", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.TOP| Rectangle.RIGHT,2, 5);
					double totalPremiumRate=0.0;
					double totalWarRate=0.0;
					String marinePremiumRate="";
					String warPremiumRate="";
					for(int i=0;i<finalCount;i++){
						goods.append("\n"+commodityDes[i]);
						goods.append(" ");
						/*goods.append("   "+packageDes[i]);
						goods.append(" ");*/
						if(invoice[i]!=null&&invoice[i].length()>0){
							goods.append("INV# "); 
							goods.append(invoice[i]);
							goods.append(" ");
						}
						tableCreation1.insertCellPad(goods.toString(), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						
						if(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE")){
							tableCreation1.insertCellPad("L/C No. "+lcNumber, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(bankName.length()>0 && !bankName.equals("NONE")){
							tableCreation1.insertCellPad("Bank Name : "+bankName, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						}
						if(YES.equalsIgnoreCase(currencyOption))
						{
 							commoditySum[i]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySum[i].replaceAll(",",""),currencyId, brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyShortName+" "+commoditySum[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						else
						{
							localSumround[i] = pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(localSumround[i].replaceAll(",",""),"1", brokerBra)), decimalDigit);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyType1+" "+localSumround[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						}
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						//totalPremiumRate+=Double.parseDouble(premiumRate[i]);
						//totalWarRate+=Double.parseDouble(warRate[i]);
						/*tableCreation1.insertCell("   "+"MARINE PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(premiumSingle[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						vr=vr+(Double.parseDouble(premiumSingle[i]));
						tableCreation1.insertCell("   "+"WAR PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(warRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(warPremium[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
						vr=vr+(Double.parseDouble(warPremium[i]));*/
						if(finalCount==1)
						{
							marinePremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),6)+"%";
							warPremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(warRate[i]),6)+"%";
						}
						goods = new StringBuffer();
					}
					
					if(YES.equalsIgnoreCase(currencyOption))
					{
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(((Double.parseDouble(totalMarinePremium)/exchangeRate)+""), currencyId, brokerBra);
						totalWarPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(totalWarPremium)/exchangeRate)+"", currencyId, brokerBra);
						ExcessPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId, brokerBra);
					}else
					{
						currencyId="1";
						totalMarinePremium=new PremiumInputsBean().getRoundedValue(totalMarinePremium+"", currencyId, brokerBra);
						totalWarPremium=new PremiumInputsBean().getRoundedValue(totalWarPremium, currencyId, brokerBra);
						ExcessPremium=new PremiumInputsBean().getRoundedValue(ExcessPremium+"", currencyId, brokerBra);
						issuanceFee=new PremiumInputsBean().getRoundedValue(issuanceFee+"", currencyId, brokerBra);
					}
					netPremium=(Double.parseDouble(totalMarinePremium)+(Double.parseDouble(ExcessPremium))+(Double.parseDouble(totalWarPremium))+(Double.parseDouble(issuanceFee)));
					if("N".equalsIgnoreCase(minPreYN))
					{
						tableCreation1.insertCell("   "+"Marine Premium "+marinePremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalMarinePremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);

						tableCreation1.insertCell("   "+"War Premium "+warPremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
					}else {
						tableCreation1.insertCell("   "+"Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalMarinePremium)+Double.parseDouble(totalWarPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					}
//					vr=vr+(Double.parseDouble(pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit).replaceAll(",","")));
					
					//goods.append("\nTOTAL MARINE PREMIUM @"+pdis.decimalFormat(totalPremiumRate,4)+"\n");
					//goods.append("\nTOTAL WAR PREMIUM @"+pdis.decimalFormat(totalWarRate,4)+"\n");
					//tableCreation1.insertCell(goods.toString(), fontTextNormal, Rectangle.RECTANGLE,5, 0);
					//tableCreation1.insertCell("", fontTextNormal, Rectangle.RECTANGLE,1, 0);
					if(Double.parseDouble(ExcessPremium)!=0){
						tableCreation1.insertCell("   "+"Additional Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(ExcessPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,2, 5);
//					vr=vr+(Double.parseDouble(ExcessPremium));
					}
					
					//premium=pdis.decimalFormat(Double.parseDouble(premium)+(Double.parseDouble(ExcessPremium)), decimalDigit);
					//tableCreation1.insertCell(premium+TAB+TAB+TAB+TAB+"\n\n"+pdis.decimalFormat(Double.parseDouble(totalWarPremium),decimalDigit), fontTextNormal, Rectangle.RECTANGLE,2, 5);
					if(commissionStatus.equalsIgnoreCase("Y") && "N".equalsIgnoreCase(noteType)) // Debit Commission Status
					{
						if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0 && !"0.0".equals(BrokerCommission))
						{
							tableCreation1.insertCell("   "+"Less Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							commissAmtRound=(Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							netPremium=((Double.parseDouble(totalMarinePremium)+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))-Double.parseDouble(commissAmtRound))+(Double.parseDouble(issuanceFee));
						}
					}
					tableCreation1.insertCell("\n"+"   "+"Issuance Fee\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
//					vr=vr+(Double.parseDouble(issuanceFee));
					issuanceFee=pdis.decimalFormat(Double.parseDouble(issuanceFee), decimalDigit);
					String DubaiTradeStatus="";
					DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);	
					tableCreation1.insertCell("\n"+issuanceFee+"\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}else
					{
						tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}
//					logger.info("VR"+pdis.decimalFormat(vr, decimalDigit));
				/*	String vrword=pdis.decimalFormat(vr, decimalDigit).replaceAll(",","");
					String fileb = "";
					String finalPre = "";
					
					if (vrword.length() > 0&& vrword.indexOf('.') != -1) 
					{
						fileb = vrword.substring(vrword.indexOf('.') + 1,	vrword.length());
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
						finalPre = vrword.substring(0, vrword.indexOf('.'));
					} 
					else
					{
						fileb = "0";
						finalPre = "0";
					}
					finalPre = finalPre.replaceAll(",","");
					ConvertToWords = convertWord.convertNumToWord(Integer.parseInt(finalPre));*/
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " + fileb + "/"+fillsDigit+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					//tableCreation1.insertCell(currencyType1+" "+ConvertToWords+ " and " +convertWord.convertNumToWord(Integer.parseInt(fileb))  + " Fils only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					processor = new DefaultProcessor();
//					tableCreation1.insertCell(currencyType1+" "+processor.getName(pdis.decimalFormat(netPremium, decimalDigit).replaceAll(",","")) + " "+fills+" Only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Important Client Notice: The premium shown above is due for payment within a maximum period of 30 days from the date of this Receipt Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	
	private static PdfPCell horizontal(final PdfPCell cell) {
		cell.setHorizontalAlignment(2);

		return cell;
	}

	private static void cS1(final PdfPCell cell) {
		cell.setColspan(1);
	}

	private static void cS2(final PdfPCell cell) {
		cell.setColspan(2);
	}

	private static void cS3(final PdfPCell cell) {
		cell.setColspan(3);
	}

	private static void cS4(final PdfPCell cell) {
		cell.setColspan(4);
	}
	private Image getHyperLinkedImageCell(final int scaleWidth, final int scaleHeight,
			final String imageSource) throws Exception {
		Image image;
		image = Image.getInstance(imageSource);
		image.scaleToFit(scaleWidth, scaleHeight);
		return image;
	}
	
	//Changed by by sathish for Debit pdf creation from Controller to bean for web service - End

	
	public String getPolicysFreshBackDesc(final String branch, final String PolicyNo,final String quoteNo) 
	throws BaseException
	{
		logger.info("One Off PDFCreatorBean getPolicysFreshBackDesc method()");
		logger.debug(ENTER);
			com.maan.services.policyInfo pol;
			pol= new com.maan.services.policyInfo();
			String res = "";
			String pno;
			pno = PolicyNo==null?quoteNo:PolicyNo;
			String checking;
			if(pno.indexOf('-')==-1){
				checking=GULF;
			}
			else
			{
				String check;
				check = "policy_no='"+pno+"'";
				checking = runner.singleSelection("select count(*) from position_master where expiry_date>to_Date('19-12-2008','dd-mm-yyyy') and "+check);
			}
			if(!"0".equals(checking))
			{
				res = pol.getPolicysFreshBackDesc(branch);
			}
		logger.debug(EXIT);
		
		return res;
	}
	
	private String displayMode = "";
	private String PolicyNo = "";
	private String QuoteNo = "";
	public String getDisplayMode() {
		return displayMode;
	}
	public void setDisplayMode(final String displayMode) {
		this.displayMode = displayMode;
	}
	public String getQuoteNo() {
		return QuoteNo;
	}
	public void setQuoteNo(final String quoteNo) {
		QuoteNo = quoteNo;
	}
	public String getPolicyNo() {
		return PolicyNo;
	}
	public void setPolicyNo(final String policyNo) {
		PolicyNo = policyNo;
	}
	private String bankerOption = "";
	private transient String bankAssOpt = "";
	private transient String currencyOption = "";
	private transient String excessOption = "";
	private transient String remarksOption = "";
	public String getCurrencyOption() {
		return currencyOption;
	}

	public void setCurrencyOption(final String currencyOption) {
		this.currencyOption = currencyOption;
	}
	public String getExcessOption() {
		return excessOption;
	}
	
	public void setExcessOption(final String excessOption) {
		this.excessOption = excessOption;
	}
	public String getRemarksOption() {
		return remarksOption;
	}

	public void setRemarksOption(final String remarksOption) {
		this.remarksOption = remarksOption;
	}
	public String getBankerAssuredOption() {
		return bankAssOpt;
	}
	public void setBankerAssuredOption(final String bankAssOpt) {
		this.bankAssOpt = bankAssOpt;
	}
	public String getBankerOption() {
		return bankerOption;
	}
	public void setBankerOption(final String bankerOption) {
		this.bankerOption = bankerOption;
	}
	private String PremiumYes = "";
	public String getPremiumYes() {
		return PremiumYes;
	}
	public void setPremiumYes(final String premiumYes) {
		PremiumYes = premiumYes;
	}
	private String fontPath = ""; 
	public String getFontPath() {
		return fontPath;
	}
	public void setFontPath(final String fontPath) {
		this.fontPath = fontPath;
	}
	public String getSignStampImage() {
		return signStampImage;
	}
	public void setSignStampImage(final String signStampImage) {
		this.signStampImage = signStampImage;
	}
	private float getPdfSpacing(final String cid,final float value1,final float value2) {
		return (SAUDI.equalsIgnoreCase(cid)||BHARAIN.equalsIgnoreCase(cid))?value1:value2;
	}
	private PdfPTableCreation addPdfSpaceLine(final String cid,final PdfPTableCreation tableCreation,
			final Font fontText)throws BaseException {
		if (SAUDI.equalsIgnoreCase(cid)||BHARAIN.equalsIgnoreCase(cid)){
			tableCreation.insertCell("", fontText, Rectangle.NO_BORDER,8, 0);
		}
		return tableCreation;
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

	public String getCurrencyType1() {
		return currencyType1;
	}

	public void setCurrencyType1(String currencyType1) {
		this.currencyType1 = currencyType1;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public void writeDebitPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,String PolicyNo,final String filePath,final String url, Map premiumdetails, String DebitNoteNo,String placeCode[][], String verNo)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map commoditydetails;
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "0", totalPremiumMultiple="0";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			//String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			//String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "0";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			String finalCountsString="", currencyId="1", noteType="";
			double multiTotalPremium=0.0, exchangeRate=0;
			
			String commodityDes[] = new String[0];
			String[] commoditySumIns = new String[0];
			String localSumIns[] = new String[0];
			String packageDes[] = new String[0];
			String[] invoice = new String[0];
			String[] premiumRate=new String[0];
			String[] premiumSingle=new String[0];
			String[] warRate=new String[0];
			String[] warPremium=new String[0];
			String[][] multiQuotePremiumInfo=new String[0][0];
			
			
			
	
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="",extraCoverId="", arAccountNo="";
			String commissionStatus = "Y"; // Debit Commission....
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			String issuanceFee="", productName="", productId="";
			boolean fileExists=new File(filePath).exists();
			//rajesh world work stated
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST DEBIT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "DN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				//PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				String[][] productInfo=getProductInfo(brokerBra, PolicyNo);
				if(productInfo!=null && productInfo.length>0)
				{
					productId=productInfo[0][0];
					productName=productInfo[0][1];
				}
				Map AmountDetails;
				AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				//Ezhumalai
				String getPolictDetails[][] = new String[0][0];
				String getPolNo="";
				String getCertNo="";
				String getEndtNo="";
				if("11".equals(productId)){
				getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
				}else{
				getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);	
				}
			    if(getPolictDetails.length>0)
				{
					getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
					getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
					getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
				}
				
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					commoditydetails = (HashMap) premiumdetails.get("commoditydetails");
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails
					.get("CustomerLastName") == null ? ""
					: (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					/*Brokeraddress1 = (String) premiumdetails.get("Brokeraddress1") == null ? ""	: (String) premiumdetails.get("Brokeraddress1");// [0][51]);
					BrokerAddress2 = (String) premiumdetails.get("BrokerAddress2") == null ? "": (String) premiumdetails.get("BrokerAddress2");// [0][52]);
					BrokerCountry = (String) premiumdetails.get("BrokerCountry") == null ? "": (String) premiumdetails.get("BrokerCountry");// [0][54]);
					BrokerPoBox = (String) premiumdetails.get("BrokerPoBox") == null ? "0":("PO Box " +(String)premiumdetails.get("BrokerPoBox"));// [0][56]);
					BrokerEmirate = (String) premiumdetails.get("BrokerEmirate") == null ? "": (String) premiumdetails.get("BrokerEmirate");// [0][57]);
*/					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
//					BrokerCompany = (String) premiumdetails.get("BrokerCompany") == null ? "": (String) premiumdetails.get("BrokerCompany");// [0][84]);
					String [][] sourceInfo=new String[0][0];
					String paymentMode = (String) premiumdetails.get("paymentMode") == null ? ""	: (String) premiumdetails.get("paymentMode");// [0][58]);
					if("CR".equalsIgnoreCase(paymentMode))
		        	  sourceInfo=getCustomerInfo((String) premiumdetails.get("debitCustomerId"), brokerBra);
		           else
		        	  sourceInfo=getSourceInfo(loginId, brokerBra);
					        	  
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
						BrokerEmirate="";
						arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
					}
					premium = (String) premiumdetails.get("Premium") == null ? "0"
							: (String) premiumdetails.get("Premium");
					issuanceFee= (String) premiumdetails.get("IssuanceFee") == null ? "0"
							: (String) premiumdetails.get("IssuanceFee");
					if(!"NormalMultiple".equalsIgnoreCase(displayMode))
					{
						//extraCoverId = (String)premiumdetails.get("ExtraCoverId");
						PolicyNo = (String) premiumdetails.get("PolicyNo")==null?"":(String) premiumdetails.get("PolicyNo");// [0][27]);
						finalCount = Integer.parseInt((String) commoditydetails.get("finalCount"));
						commodityDes = new String[finalCount];
						commoditySumIns = new String[finalCount];
						packageDes = new String[finalCount];
						invoice=new String[finalCount];
						localSumIns = new String[finalCount];
						premiumRate=new String[finalCount];
						premiumSingle=new String[finalCount];
						warRate=new String[finalCount];
						warPremium=new String[finalCount];
						for (int i = 0; i < finalCount; i++) 
						{
							
							commodityDes[i] = (String) commoditydetails.get("CommodityDescription" + (i + 1)) == null ? ""
									: (String) commoditydetails.get("CommodityDescription" + (i + 1));// [0][9]);
							commoditySumIns[i] = (String) commoditydetails.get("CommoditySumInsured" + (i + 1)) == null ? "0"
									: (String) commoditydetails
											.get("CommoditySumInsured" + (i + 1));// [0][8]);
							commoditySumIns[i]=pdis.decimalFormat(Double.parseDouble(commoditySumIns[i].replaceAll(",","")), decimalDigit);
							packageDes[i] = (String) commoditydetails.get("PackageDescription_arr" + (i + 1)) == null ? ""
									: (String) commoditydetails.get("PackageDescription_arr" + (i + 1));// [i][65]);
							invoice[i] = (String) commoditydetails.get("Invoice_arr" + (i + 1)) == null ? ""
									: (String) commoditydetails.get("Invoice_arr"+ (i + 1));// [0][86]);
							localSumIns[i] = (String) commoditydetails.get("SumInsuredLocal" + (i + 1)) == null ? "0"
									: (String) commoditydetails.get("SumInsuredLocal" + (i + 1));// [0][25]);
							localSumIns[i]=pdis.decimalFormat(Double.parseDouble(localSumIns[i].replaceAll(",","")), decimalDigit);
							//Added by sathish Start
							premiumRate[i]=(String)commoditydetails.get("PremiumRate" + (i+1))==null?"0":(String)commoditydetails.get("PremiumRate" + (i+1));
							premiumSingle[i]=(String)commoditydetails.get("PremiumSingle" + (i+1))==null?"0":(String)commoditydetails.get("PremiumSingle" + (i+1));
							warRate[i]=(String)commoditydetails.get("WarRate" + (i+1))==null?"0":(String)commoditydetails.get("WarRate" + (i+1));
							warPremium[i]=(String)commoditydetails.get("WarPremium" + (i+1))==null?"0":(String)commoditydetails.get("WarPremium" + (i+1));
							//Added by sathish End
						}
						//loginId = (String) premiumdetails.get("LoginID");// [0][60]);
						ExcessPremium = (String) premiumdetails.get("ExcessPremium") == null ? "0": (String) premiumdetails.get("ExcessPremium");
						
						finalDeductedAmt = (String) AmountDetails.get("FinalPayableAmountAfterDeduction") == null ? "0"	: (String) AmountDetails.get("FinalPayableAmountAfterDeduction");
						finalAmtRound = pdis.decimalFormat(Double.parseDouble(finalDeductedAmt), decimalDigit);
				
						StringTokenizer token1;
						token1 = new StringTokenizer(finalDeductedAmt, ".");
						finalAmtRs = (String) token1.nextToken();
						finalAmtPs = (String) token1.nextToken();
						logger.info("finalAmtRs"+ finalAmtRs);
						logger.info("finalAmtPs>>>>>>>>>>>>."	+ finalAmtPs);
						finalAmtRs = finalAmtRs.replaceAll(",","");
						finalRsConversion = Integer.parseInt(finalAmtRs);
						ConvertToWords = convertWord.convertNumToWord(finalRsConversion);
						commissionAmount = (String) AmountDetails.get("CommissionAmount") == null ? "0"
						: (String) AmountDetails.get("CommissionAmount");
						commissAmtRound = pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit);
						finalPayableAmt = Double.toString((Double.parseDouble(finalBean.isNull((String) AmountDetails.get("FinalPayableAmount"),"0")) + (Double.parseDouble(ExcessPremium))));
						royalfinal = finalPayableAmt;
						payableRound = pdis.decimalFormat(Double.parseDouble(finalPayableAmt), decimalDigit);
					}else
					{
						currencyId = (String) premiumdetails.get("ocMulCurrId") == null ? "1": (String) premiumdetails.get("ocMulCurrId");// [0][15]);
						exchangeRate = Double.parseDouble((String) premiumdetails.get("ocMulCurrValue") == null ? "0": (String) premiumdetails.get("ocMulCurrValue"));// [0][15]);
						currencyType1 = new rsa.opencoverpdf.finalprint().getMulFCName(PolicyNo,"3","QUOTENOMULTIPLE",brokerBra);
						finalCountsString=(String)premiumdetails.get("finalCounts");
						multiQuotePremiumInfo=(String[][])premiumdetails.get("multiQuotePremiumInfo");
						noteType=new policyInfo().getNoteType(PolicyNo);
						/*String tempQuoteNo="";
						HashMap tempHash=new HashMap();
						String ExcessPremiumTemp="";
						String finalAmtTemp="";
						for(int i=0;i<Integer.parseInt(finalCountsString);i++)
						{

							tempQuoteNo=(String)premiumdetails.get("QuoteNo"+i)==null?"0":(String)premiumdetails.get("QuoteNo"+i);//[0][84]);
							tempHash=(HashMap)premiumdetails.get(tempQuoteNo)==null?tempHash:(HashMap)premiumdetails.get(tempQuoteNo);//[0][84]);
							if(tempHash.size() > 2)
							{

								AmountDetails=(HashMap)tempHash.get("AmountDetails")==null?AmountDetails:(HashMap)tempHash.get("AmountDetails");
								ExcessPremiumTemp=(String)tempHash.get("ExcessPremium")==null?"0":(String)tempHash.get("ExcessPremium");
								finalAmtTemp = (String)AmountDetails.get("FinalPayableAmount")==null?"":(String)AmountDetails.get("FinalPayableAmount");
								finalAmtTemp=Double.toString((Double.parseDouble(finalAmtTemp)+(Double.parseDouble(ExcessPremiumTemp))));
								try
								{
									multiTotalPremium=Double.parseDouble(finalAmtTemp)+multiTotalPremium;
								}
								catch(Exception e)
								{
									logger.info("the Exception Occured is multiTotalPremium "+e.toString());logger.debug(e);
								}
								//openCoverNo = isNull((String)tempHash.get("openCoverNo"),"");
								//openCoverCustomer = isNull((String)tempHash.get("openCoverCustomer"),"");
							}
							else
							{
								//checkProperCount=checkProperCount+1;
								logger.info("infoMissing"+i+"SOME INFORMATIONS MISSING FOR LINKED QUOTE NO\t"+tempQuoteNo);
							}
							//rowCount=rowCount+1;
						}
						premium=multiTotalPremium+"";*/
						if(multiQuotePremiumInfo!=null && multiQuotePremiumInfo.length>0){
							premium=multiQuotePremiumInfo[0][5]==null?"0":multiQuotePremiumInfo[0][5];
							ExcessPremium=multiQuotePremiumInfo[0][2]==null?"0":multiQuotePremiumInfo[0][2];
							issuanceFee=multiQuotePremiumInfo[0][3]==null?"0":multiQuotePremiumInfo[0][3];
							totalPremiumMultiple=multiQuotePremiumInfo[0][4]==null?"0":multiQuotePremiumInfo[0][4];
						}
					}
			}
		document = new Document(PageSize.A4, 50, 50, 80, 120);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					DebitNoteNo=DebitNoteNo.substring(0,2)+"-"+DebitNoteNo.substring(2,4)+"-"+DebitNoteNo.substring(4,5)+"-"+DebitNoteNo.substring(5,DebitNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(DebitNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					/*tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);*/
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Debit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if(!"NormalSupplement".equalsIgnoreCase(displayMode))
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+"Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					else
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+"Cert#: "+getCertNo+"/"+verNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ customerFirstName+ CustomerLastName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have debited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					StringBuffer goods = new StringBuffer();
					goods.append("\n   Premium due in respect of Marine Insurance \n");
					double totalPremiumRate=0.0;
					double totalWarRate=0.0;
					double totalPremium=0.0;
					double totalWarPremium=0.0;
					double netPremium=0.0;
					String marinePremiumRate="";
					String warPremiumRate="";
					
					if(!"NormalMultiple".equalsIgnoreCase(displayMode))
					{
						for(int i=0;i<finalCount;i++){
							goods.append("\n"+commodityDes[i]);
							goods.append(" ");
							/*goods.append(packageDes[i]);
							goods.append(" ");*/
							if(invoice[i]!=null&&invoice[i].length()>0){
								goods.append("INV# "); 
								goods.append(invoice[i]);
								goods.append(" ");
							}	
							tableCreation1.insertCellPad(goods.toString(), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0, 9);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							tableCreation1.insertCell("   "+"Sum Insured = "+currencyType1+" "+localSumIns[i], fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							//totalPremiumRate+=Double.parseDouble(premiumRate[i]);
							//totalWarRate+=Double.parseDouble(warRate[i]);
							/*tableCreation1.insertCell("   "+"MARINE PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(premiumSingle[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							vr=vr+(Double.parseDouble(premiumSingle[i]));
							tableCreation1.insertCell("   "+"WAR PREMIUM : @"+pdis.decimalFormat(Double.parseDouble(warRate[i]),4)+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(warPremium[i]),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
							vr=vr+(Double.parseDouble(warPremium[i]));*/
							goods = new StringBuffer();						
							//goods.append("SUM INSURED AED. "+commoditySumIns[i]+"\n");
						/*	goods.append("\n\nSUM INSURED AED. "+localSumIns[i]+"\n");
							goods.append("\nMARINE PREMIUM @"+premiumRate[i]+"%="+premiumSingle[i]+"\n");
							goods.append("\nWAR PREMIUM @"+warRate[i]+"%="+warPremium[i]+"\n");		*/
							if(finalCount==1)
							{
								marinePremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),6)+"%";
								warPremiumRate+="@ "+pdis.decimalFormat(Double.parseDouble(warRate[i]),6)+"%";
							}
							goods = new StringBuffer();
							totalPremium=totalPremium+(Double.parseDouble(premiumSingle[i]));
							totalWarPremium=totalWarPremium+(Double.parseDouble(warPremium[i]));
						}
						netPremium=netPremium+(totalPremium+(Double.parseDouble(ExcessPremium))+totalWarPremium+(Double.parseDouble(issuanceFee)));
//						netPremium=Math.round(netPremium);
						tableCreation1.insertCell("   "+"Marine Premium "+marinePremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(totalPremium,decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
						tableCreation1.insertCell("   "+"War Premium "+warPremiumRate, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(totalWarPremium,decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 5);
						if(Double.parseDouble(ExcessPremium)!=0){
							tableCreation1.insertCell("   "+"Additional Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(ExcessPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							}
							tableCreation1.insertCell("\n"+"   "+"Issuance Fee\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
							tableCreation1.insertCell("\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
							issuanceFee=pdis.decimalFormat(Double.parseDouble(issuanceFee), decimalDigit);
							tableCreation1.insertCell("\n"+issuanceFee+"\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
							tableCreation1.insertCell("   "+loginId.toUpperCase(), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
							tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
							tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
							processor = new DefaultProcessor();

							String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
							String word1="",word2="",filsWord="";
							if(net.indexOf(".")!=-1)
							{
								word1=net.substring(0, net.indexOf("."));
								word2=net.substring(net.indexOf(".")+1, net.length());
								filsWord=processor.getName(word2);
							}else
							{
								word1=net;
							}
							filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
							tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
							
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
							tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}else{
					premium=premiumBean.getRoundedValue(((Double.parseDouble(premium)/exchangeRate)+""), currencyId, brokerBra);
					ExcessPremium=premiumBean.getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId, brokerBra);
					issuanceFee=premiumBean.getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId, brokerBra);	
					totalPremiumMultiple=premiumBean.getRoundedValue((Double.parseDouble(totalPremiumMultiple)/exchangeRate)+"", currencyId, brokerBra);	
					goods.append("\n   Premium \n");		
					tableCreation1.insertCell(goods.toString(), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("", fontTextNormal,  Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(premium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					if(ExcessPremium.length()>0)
					{
					if(Double.parseDouble(ExcessPremium)!=0){
						tableCreation1.insertCell("   "+"Additional Premium", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
						tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
						tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(ExcessPremium),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					}
					}
					if(commissionStatus.equalsIgnoreCase("Y") && "N".equalsIgnoreCase(noteType)) // Debit Commission Status
					{
						if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0 && !"0.0".equals(BrokerCommission))
						{
							tableCreation1.insertCell("   "+"Less Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
							tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
							commissAmtRound=(Double.parseDouble(premium)+Double.parseDouble(ExcessPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
							tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
							totalPremiumMultiple=((Double.parseDouble(premium)+Double.parseDouble(ExcessPremium))-Double.parseDouble(commissAmtRound))+(Double.parseDouble(issuanceFee))+"";
						}
					}
					tableCreation1.insertCell("\n   Issuance Fee", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					issuanceFee=pdis.decimalFormat(Double.parseDouble(issuanceFee), decimalDigit);
					tableCreation1.insertCell("\n"+issuanceFee, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,1, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);
					String DubaiTradeStatus="";
					DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);
					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.RECTANGLE,5, 0);
					tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.RECTANGLE,1, 2);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(totalPremiumMultiple), decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal, Rectangle.RECTANGLE,2, 5);
					processor = new DefaultProcessor();
					String net=pdis.decimalFormat(Double.parseDouble(totalPremiumMultiple), decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					}
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(156, 107); 
						}else{
							//signatureImage.scaleAbsolute(70, 54);
							//signatureImage.scaleAbsolute(105, 90);
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Important Client Notice: The premium shown above is due for payment within a maximum period of 30 days from the date of this Debit Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	public String[][] getProductInfo(String branchCode, String policyNo)
	{
		String productInfo[][]=runner.multipleSelection("SELECT DISTINCT PRODUCT_ID, NVL(PRODUCT_NAME,' ') FROM PRODUCT_MASTER WHERE BRANCH_CODE=(SELECT BELONGING_BRANCH FROM BRANCH_MASTER WHERE BRANCH_CODE=?) AND PRODUCT_ID IN (SELECT DISTINCT PRODUCT_ID FROM POSITION_MASTER WHERE POLICY_NO=?)",new String[]{branchCode, policyNo});
		return productInfo;
	}
	public String getSubCurrencyName(String currencyId, String countryId)
	{
		String result=runner.singleSelection("SELECT SUB_CURRENCY FROM CURRENCY_MASTER WHERE CURRENCY_ID=? AND COUNTRY_ID=?",new String[]{currencyId, countryId});
		return result;
	}
	public String getCoreLoginId(String policyNo)
	{
		String coreLogin=runner.singleSelection("SELECT NVL(CORE_LOGIN_ID,LOGIN_ID) FROM LOGIN_MASTER WHERE LOGIN_ID=(SELECT DISTINCT CASE WHEN APPLICATION_ID='1' THEN LOGIN_ID ELSE APPLICATION_ID END FROM POSITION_MASTER WHERE POLICY_NO=?)",new String[]{policyNo});
		return coreLogin;
	}
	public String getNoteWaterMarkText(String policyNo,String noteType, boolean exists)
	{
		String text="";
		if(exists){
			text=runner.singleSelection("SELECT CASE WHEN "+noteType+"_GEN_STS>0 THEN 'COPY' ELSE '' END TEXT FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
		}
		runner.multipleUpdation("UPDATE POSITION_MASTER SET "+noteType+"_GEN_STS=NVL("+noteType+"_GEN_STS,0)+1 WHERE POLICY_NO=?",new String[]{policyNo});
		return text;
	}
	public String[][] getCustomerInfo(String customerId, String branchCode)
	{
		String coreCustomerCode=runner.singleSelection("SELECT MISSIPPI_CUSTOMER_CODE FROM PERSONAL_INFO WHERE CUSTOMER_ID=?",new String[]{customerId});
		String[][] sourceInfo=runner.multipleSelection("SELECT initcap(CUSTTITL),initcap(CUSTNAME),initcap(CUSTADD1),initcap(CUSTADD2),initcap(CUSTADD3),initcap(CUSTADD4),(CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) ARACC FROM C_CUST@ECARGO_"+(TEST.equalsIgnoreCase(usrModeController)?"DEV":"PROD")+" WHERE CIMSNO=? AND BRCODE=?",new String[]{coreCustomerCode,branchCode});
		return sourceInfo;
	}
	public String[][] getSourceInfo(String loginId, String branchCode)
	{
		String coreBrokerCode=runner.singleSelection("SELECT MISSIPPI_ID FROM BROKER_COMPANY_MASTER WHERE AGENCY_CODE=(SELECT DISTINCT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?) AND BRANCH_CODE=?",new String[]{loginId,branchCode});
		String[][] sourceInfo=runner.multipleSelection("SELECT initcap(CUSTTITL),initcap(CUSTNAME),initcap(CUSTADD1),initcap(CUSTADD2),initcap(CUSTADD3),initcap(CUSTADD4),(CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) ARACC FROM C_CUST@ECARGO_"+(TEST.equalsIgnoreCase(usrModeController)?"DEV":"PROD")+" WHERE CIMSNO=? AND BRCODE=?",new String[]{coreBrokerCode,branchCode});
		return sourceInfo;
	}
	public String[][] getSourceInfodebit(String getPolNo, String branchCode)
	{
		String coreBrokerCode=runner.singleSelection("SELECT DEBIT_CORE_APP_CODE FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{getPolNo});
		String[][] sourceInfo=runner.multipleSelection("SELECT initcap(CUSTTITL),initcap(CUSTNAME),initcap(CUSTADD1),initcap(CUSTADD2),initcap(CUSTADD3),initcap(CUSTADD4),(CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) ARACC FROM C_CUST@ECARGO_"+(TEST.equalsIgnoreCase(usrModeController)?"DEV":"PROD")+" WHERE CIMSNO=? AND BRCODE=?",new String[]{coreBrokerCode,branchCode});
		return sourceInfo;
	}
	public String[][] getSourceInfocredit(String getPolNo, String branchCode)
	{
		String coreBrokerCode=runner.singleSelection("SELECT CREDIT_CORE_APP_CODE FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{getPolNo});
		String[][] sourceInfo=runner.multipleSelection("SELECT initcap(CUSTTITL),initcap(CUSTNAME),initcap(CUSTADD1),initcap(CUSTADD2),initcap(CUSTADD3),initcap(CUSTADD4),(CUSTCODE ||'-'|| CUSTGRP ||'-'|| CUSTTYPE ||'-'||CUSTCLAS) ARACC FROM C_CUST@ECARGO_"+(TEST.equalsIgnoreCase(usrModeController)?"DEV":"PROD")+" WHERE CIMSNO=? AND BRCODE=?",new String[]{coreBrokerCode,branchCode});
		return sourceInfo;
	}
	public String getDirectStatus(String loginId, String branchCode)
	{
		String DirectStatus=runner.singleSelection("SELECT DECODE(REMARKS,NULL,'N','Y') DIRECT_STATUS FROM CONSTANT_DETAIL WHERE REMARKS=? AND CATEGORY_ID=129 AND BRANCH_CODE=? AND STATUS='Y'",new String[]{loginId,branchCode});
		return DirectStatus;
	}
	public String getDubaiTradeStatus(String loginId, String branchCode)
	{
		String DubaiTradeStatus=runner.singleSelection("SELECT DECODE(REMARKS,NULL,'N','Y') DUBAI_TRADE_STATUS FROM CONSTANT_DETAIL WHERE REMARKS =(SELECT DISTINCT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?) AND CATEGORY_ID=149 AND BRANCH_CODE=? AND STATUS='Y'",new String[]{loginId,branchCode});
		return DubaiTradeStatus;
	}
	public String getDubaiTradeAgentName(String destCountryId, String branchCode)
	{
		String DubaiTradeAgentName=runner.singleSelection("SELECT SETTLING_AGENT_NAME FROM SETTLING_AGENT_MASTER WHERE COUNTRY_ID =? AND STATUS='Y'",new String[]{destCountryId});
		return DubaiTradeAgentName;
	}
	public String getppwDays(String policyNo)
	{
		String ppw=runner.singleSelection("SELECT NVL(PPW_DAYS,'30') FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
		return ppw;
	}
	public String[][] getReceiptCustomerInfo(String customerId, String branchCode)
	{
		String[][] sourceInfo=runner.multipleSelection("SELECT initcap(FIRST_NAME),initcap(LAST_NAME),initcap(ADDRESS1),initcap(ADDRESS2),initcap(COUNTRY),POBOX,initcap(NVL(CITY,EMIRATE)) FROM PERSONAL_INFO WHERE CUSTOMER_ID=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=?)",new String[]{customerId,customerId});
		return sourceInfo;
	}
	public void writeNewDebitPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map premiumdetails;
			Map commoditydetails;
			premiumdetails = new HashMap();
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			double totalPremium=0.0;
			double netPremium=0.0;
			double Amt=0.00;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "", arAccountNo="";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "";
			//String creditNoteNo = "";
			String DebitNoteNo = "";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			//String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			//String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			//String extraCoverId ="";
			
			premiumdetails = new HashMap();
			Map preDetails = new HashMap();
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="";
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			boolean fileExists=new File(filePath).exists();
			String issuanceFee="";
			String totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="";
			double exchangeRate=0.0;
			//Ezhumalai
			String getPolictDetails[][] = new String[0][0];
			String getPolNo="";
			String getCertNo="";
			String getEndtNo="";
			
			String DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);
			
			String[][] productInfo=getProductInfo(brokerBra, PolicyNo);
			if(productInfo!=null && productInfo.length>0)
			{
				productId=productInfo[0][0];
				productName=productInfo[0][1];
			}
			
			if("11".equals(productId)){
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
			}else{
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);	
			}
		    if(getPolictDetails.length>0)
			{
				getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
				getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
				getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
			}
			
			//rajesh world work stated
			String[][] multiQuotePremiumInfo=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				logger.info("currency type"+currencyType+"-"+currencyType1);
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST DEBIT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "DN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				//PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				//String[][] productInfo=getProductInfo(brokerBra, PolicyNo);
				//if(productInfo!=null && productInfo.length>0)
				//{
				//	productId=productInfo[0][0];
				//	productName=productInfo[0][1];
				//}
				if("11".equals(productId))
				{
					rsa.opencoverpdf.finalprint dataBean=new rsa.opencoverpdf.finalprint();
					if("NormalMultiple".equalsIgnoreCase(displayMode))
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"QUOTENOMULTIPLE",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(PolicyNo,"11","QUOTENOMULTIPLE");
					}
					else if(NORMALSUP.equalsIgnoreCase(displayMode))
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"QUOTENOSINGLE",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(QuoteNo,"11","QUOTENOSINGLE");
					}
					else
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"POLICYNO",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(PolicyNo,"11","POLICYNO");
					}
					premiumdetails.put("commissionAmt", preDetails.get("Commission"));
				}else
				{
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
				}
				if("NormalMultiple".equalsIgnoreCase(displayMode)){
					currencyShortName = new rsa.opencoverpdf.finalprint().getMulFCName(PolicyNo,"3","POLICYNO",brokerBra);
				}else{
					currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				}
				Map AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					exchangeRate = Double.parseDouble((String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate"));// [0][15]);
					currencyId = (String) premiumdetails.get("currencyId") == null ? ""
							: (String) premiumdetails.get("currencyId");// [0][14]);
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					commissionAmount = (String) premiumdetails.get("commissionAmt") == null ? "0"
					: (String) premiumdetails.get("commissionAmt");
					commissAmtRound = commissionAmount;
					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails
					.get("CustomerLastName") == null ? ""
					: (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					
					if("NormalMultiple".equalsIgnoreCase(displayMode)){
						currencyId = (String) premiumdetails.get("ocMulCurrId") == null ? "1": (String) premiumdetails.get("ocMulCurrId");// [0][15]);
						exchangeRate = Double.parseDouble((String) premiumdetails.get("ocMulCurrValue") == null ? "0": (String) premiumdetails.get("ocMulCurrValue"));// [0][15]);
						multiQuotePremiumInfo=(String[][])premiumdetails.get("multiQuotePremiumInfo");
						if(multiQuotePremiumInfo!=null && multiQuotePremiumInfo.length>0){
							premium=multiQuotePremiumInfo[0][5]==null?"0":multiQuotePremiumInfo[0][5];
							ExcessPremium=multiQuotePremiumInfo[0][2]==null?"0":multiQuotePremiumInfo[0][2];
							commissAmtRound=(Double.parseDouble(premium)+Double.parseDouble(ExcessPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
						}
						currencyOption="YES";
					}
					
					String [][] sourceInfo=getSourceInfo(loginId, brokerBra);
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
						arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
					}
			}
				//creditNoteNo = finalBean.getCreditNoteNo(PolicyNo);
				DebitNoteNo = finalBean.updateCommission(brokerBra,"3",PolicyNo,commissAmtRound,taxPercent);
		document = new Document(PageSize.A4, 50, 50, 80, 120);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					DebitNoteNo=DebitNoteNo.substring(0,2)+"-"+DebitNoteNo.substring(2,4)+"-"+DebitNoteNo.substring(4,5)+"-"+DebitNoteNo.substring(5,DebitNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(DebitNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					/*tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);*/
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Debit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						if(!"NormalSupplement".equalsIgnoreCase(displayMode))
							if(getEndtNo.equalsIgnoreCase("")){
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
							}else{
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
							}
						else
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+"/"+verNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}else
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have debited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);

					if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
						tableCreation1.insertCell("   "+"Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					}else{
						tableCreation1.insertCell("   "+"Fees @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					}
					if(YES.equalsIgnoreCase(currencyOption)){
						commissAmtRound=new PremiumInputsBean().getRoundedValue((Double.parseDouble(commissAmtRound)/exchangeRate)+"", currencyId, brokerBra);
						Amt=Double.parseDouble(commissAmtRound);
						Amt=Amt*-1;
					}
					else{
						commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", "1", brokerBra);
						Amt=Double.parseDouble(commissAmtRound);
						Amt=Amt*-1;
					}
					tableCreation1.insertCell("("+pdis.decimalFormat(Amt, decimalDigit)+")"+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					netPremium=Amt;
					//tableCreation1.insertCell("\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					

					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}else
					{
						tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}
					processor = new DefaultProcessor();
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	
	public void writeCreditPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			
			HeaderFooterImage pageWater;
			Document document;
			NumberToWordBean convertWord;
			convertWord = new NumberToWordBean();
			PdfPCell cell;
			Map premiumdetails;
			Map commoditydetails;
			premiumdetails = new HashMap();
			PDFDisplay pdis;
			pdis = new PDFDisplay();
			PdfPTable debittable,table,headertable, debittablesplit, debittable1,blanktable;
			Paragraph  debit1, debit;
			Font font,  fontHead1, fontHead2, fontText;
			String ConvertToWords;
			HttpSession session;
			double totalPremium=0.0;
			double netPremium=0.0;
			
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "",
			BrokerPoBox = "", arAccountNo="";
			
			String BrokerCommission = "";
			String BrokerCompany = "";
			String ExcessPremium = "";
			String creditNoteNo = "";
			String royalfinal = "";
	
			int finalCount = 0;
			String customerFirstName = "", CustomerLastName = "",QuoteNo = "";
			String watermarkText;
			watermarkText = "INVALID POLICY";
			//String totalSumIns = "";
			String startDate = "";//need to fix Rajesh
			String PolicyGenDate = "";
			//String PolicyNoFour = "";
			String cusCompanyName = "";
			String displayText = "";
			String finalAmtRs = "";
			String finalAmtPs = "";
			int finalRsConversion = 0;
			String finalDeductedAmt = "";
			String commissionAmount = "";
			String finalPayableAmt = "0";
			String finalAmtRound = "";
			String commissAmtRound = "";
			String payableRound = "0";
			String braAddress;
			String premium = "";
			//String extraCoverId ="";
			
			premiumdetails = new HashMap();
			Map preDetails = new HashMap();
			// Some Common Operations
			String currencyType;
			String currencyType1;
			String fillsDigit;
			String fills;
			String braName;
			String headImage;
			String footImage;
			String signImage;
			String cols="";
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			logger.info("========loginId   is " + loginId);
			logger.info("========policynumber   is " + PolicyNo);
			boolean fileExists=new File(filePath).exists();
			String issuanceFee="";
			String totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="";
			double exchangeRate=0.0;
			//Ezhumalai
			String getPolictDetails[][] = new String[0][0];
			String getPolNo="";
			String getCertNo="";
			String getEndtNo="";
			String DubaiTradeStatus=getDubaiTradeStatus(loginId, brokerBra);
			
			String[][] productInfo=getProductInfo(brokerBra, PolicyNo);
			if(productInfo!=null && productInfo.length>0)
			{
				productId=productInfo[0][0];
				productName=productInfo[0][1];
			}
			
			if("11".equals(productId)){
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"POLICYNO",brokerBra);
			}else{
			getPolictDetails = finalBean.getPolictDetails(PolicyNo,"QUOTENO",brokerBra);	
			}
		    if(getPolictDetails.length>0)
			{
				getPolNo = getPolictDetails[0][0]==null?"":getPolictDetails[0][0];
				getCertNo = getPolictDetails[0][1]==null?"":getPolictDetails[0][1];
				getEndtNo = getPolictDetails[0][2]==null?"":getPolictDetails[0][2];
			}
			
			//rajesh world work stated
			String[][] multiQuotePremiumInfo=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"3",POLICYNO);
				braName = placeCode[0][0] == null ? "" : placeCode[0][0];
				headImage  = placeCode[0][1] == null ? "" : placeCode[0][1];
				footImage  = placeCode[0][2] == null ? "" : placeCode[0][2];
				signImage  = placeCode[0][3] == null ? "" : placeCode[0][3];
				currencyType  = placeCode[0][5] == null ? "" : placeCode[0][5];
				currencyType1  = placeCode[0][6] == null ? "" : placeCode[0][6];
				fills  = placeCode[0][8] == null ? "" : placeCode[0][8];
				fillsDigit  = placeCode[0][9] == null ? "" : placeCode[0][9];
				braAddress = placeCode[0][10] == null ? "" : placeCode[0][10];
				cols = placeCode[0][11] == null ? "" : placeCode[0][11];
				logger.info("currency type"+currencyType+"-"+currencyType1);
				pageWater = new rsa.pdf.HeaderFooterImage();
				pageWater.setImagePath(urlPath);
				pageWater.setFooterImagePath(urlPathFooter);
				if(TEST.equalsIgnoreCase(usrModeController))
					pageWater.setDisplayText("TEST CREDIT");
				else 
					pageWater.setDisplayText(getNoteWaterMarkText(PolicyNo, "CN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				//PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				//String[][] productInfo=getProductInfo(brokerBra, PolicyNo);
				//if(productInfo!=null && productInfo.length>0)
				//{
				//	productId=productInfo[0][0];
				//	productName=productInfo[0][1];
				//}
				if("11".equals(productId))
				{
					rsa.opencoverpdf.finalprint dataBean=new rsa.opencoverpdf.finalprint();
					if("NormalMultiple".equalsIgnoreCase(displayMode))
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"QUOTENOMULTIPLE",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(PolicyNo,"11","QUOTENOMULTIPLE");
					}
					else if(NORMALSUP.equalsIgnoreCase(displayMode))
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"QUOTENOSINGLE",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(QuoteNo,"11","QUOTENOSINGLE");
					}
					else
					{
						premiumdetails=dataBean.checkingvalues(PolicyNo,loginId,"POLICYNO",brokerBra,cid,"");
						preDetails = dataBean.getPreComDetails(PolicyNo,"11","POLICYNO");
					}
					premiumdetails.put("commissionAmt", preDetails.get("Commission"));
				}else
				{
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
				}
				if("NormalMultiple".equalsIgnoreCase(displayMode)){
					currencyShortName = new rsa.opencoverpdf.finalprint().getMulFCName(PolicyNo,"3","POLICYNO",brokerBra);
				}else{
					currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				}
				Map AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					exchangeRate = Double.parseDouble((String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate"));// [0][15]);
					currencyId = (String) premiumdetails.get("currencyId") == null ? ""
							: (String) premiumdetails.get("currencyId");// [0][14]);
					AmountDetails = (HashMap)premiumdetails.get("AmountDetails");
					PolicyGenDate = (String) premiumdetails.get("PolicyGeneratedDate") == null ? "": (String) premiumdetails.get("PolicyGeneratedDate");// [0][4]);
					commissionAmount = (String) premiumdetails.get("commissionAmt") == null ? "0"
					: (String) premiumdetails.get("commissionAmt");
					commissAmtRound = commissionAmount;
					BrokerCommission = (String) premiumdetails.get("BrokerCommission") == null ? "0"	: (String) premiumdetails.get("BrokerCommission");// [0][58]);
					customerFirstName = (String) premiumdetails.get("CustomerFirstName") == null ? "": (String) premiumdetails.get("CustomerFirstName");// [0][43]);
					CustomerLastName = (String) premiumdetails
					.get("CustomerLastName") == null ? ""
					: (String) premiumdetails.get("CustomerLastName");// [0][44]);
					cusCompanyName = (String) premiumdetails.get("CustomerCompanyName") == null ? "": (String) premiumdetails.get("CustomerCompanyName");// [0][75]);
					
					if("NormalMultiple".equalsIgnoreCase(displayMode)){
						currencyId = (String) premiumdetails.get("ocMulCurrId") == null ? "1": (String) premiumdetails.get("ocMulCurrId");// [0][15]);
						exchangeRate = Double.parseDouble((String) premiumdetails.get("ocMulCurrValue") == null ? "0": (String) premiumdetails.get("ocMulCurrValue"));// [0][15]);
						multiQuotePremiumInfo=(String[][])premiumdetails.get("multiQuotePremiumInfo");
						if(multiQuotePremiumInfo!=null && multiQuotePremiumInfo.length>0){
							premium=multiQuotePremiumInfo[0][5]==null?"0":multiQuotePremiumInfo[0][5];
							ExcessPremium=multiQuotePremiumInfo[0][2]==null?"0":multiQuotePremiumInfo[0][2];
							commissAmtRound=(Double.parseDouble(premium)+Double.parseDouble(ExcessPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId, brokerBra);
						}
						currencyOption="YES";
					}
					
					String [][] sourceInfo=getSourceInfo(loginId, brokerBra);
					if(sourceInfo!=null && sourceInfo.length>0)
					{
						BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
						Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
						BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
						BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
						BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
						arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
					}
			}
				creditNoteNo = finalBean.getCreditNoteNo(PolicyNo);
		document = new Document(PageSize.A4, 50, 50, 80, 120);
		try {
			Font watermarkFont;
			watermarkFont = new Font(Font.TIMES_ROMAN, 9, Font.UNDERLINE);
			watermarkFont = FontFactory.getFont(FontFactory.TIMES, 30,Font.BOLD, new CMYKColor(25, 0, 0, 64));
			Chunk watermark;
			watermark = new Chunk(watermarkText, watermarkFont);
			watermark.setSkew(20f, -20f);
			PdfWriter writer=null, writer1=null;
					if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
					{
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("COPY".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else if ("INVALID DEBIT".equalsIgnoreCase(displayText)) {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					} else {
						writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
					}
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableQuote;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					tableCreation3 = new PdfPTableCreation();
					tableQuote = new PdfPTableCreation();
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
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+"P.O. Box : "+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					//creditNoteNo=creditNoteNo.length()>0?creditNoteNo.substring(2):"";
					//creditNoteNo=creditNoteNo.substring(0,2)+"-"+creditNoteNo.substring(2,4)+"-"+creditNoteNo.substring(4,5)+"-"+creditNoteNo.substring(5,creditNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(creditNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					/*tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);*/
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Credit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						if(!"NormalSupplement".equalsIgnoreCase(displayMode))
							if(getEndtNo.equalsIgnoreCase("")){
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
							}else{
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
							}
						else
							tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Cert#: "+getCertNo+"/"+verNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}else
					{
						if(getEndtNo.equalsIgnoreCase(""))
						{
						tableCreation1.insertCell(":"+TAB+getPolNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}else{
						tableCreation1.insertCell(":"+TAB+getPolNo+TAB+TAB+TAB+TAB+" Endt No#: "+getEndtNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
						}
					}
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have credited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);

					if(!"Y".equalsIgnoreCase(DubaiTradeStatus)){
						tableCreation1.insertCell("   "+"Brokerage/Commission @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					}else{
						tableCreation1.insertCell("   "+"Fees @ "+BrokerCommission+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
						tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					}
					if(YES.equalsIgnoreCase(currencyOption))
						commissAmtRound=new PremiumInputsBean().getRoundedValue((Double.parseDouble(commissAmtRound)/exchangeRate)+"", currencyId, brokerBra);
					else
						commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", "1", brokerBra);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(commissAmtRound),decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					netPremium=Double.parseDouble(commissAmtRound);
					//tableCreation1.insertCell("\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
					tableCreation1.insertCell("   "+("Y".equalsIgnoreCase(DubaiTradeStatus)?"Total" :getCoreLoginId(PolicyNo)), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}else
					{
						tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
						tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					}
					processor = new DefaultProcessor();
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+processor.getName(word1.replaceAll(",","")) + " and "+getSubCurrencyName(currencyId, cid)+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					if ("Test".equalsIgnoreCase(usrModeController)&&("153".equals(cid)||"14".equals(cid))) 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					else 
					{
						String urlSign;
						urlSign = url+"\\"+signImage;
						Image signatureImage;
						signatureImage = Image.getInstance(urlSign);
						if("153".equals(cid)){
							signatureImage.scaleToFit(70,40);
						}else if("14".equals(cid)){
							signatureImage.scaleToFit(100,70);
						}else{
							signatureImage.scaleToFit(156, 107);
						}
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					}
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	
	 public static void concatPDFs(List<InputStream> streamOfPDFFiles,
	            OutputStream outputStream, boolean paginate, String PolicyNo) {
		    
		    logger.info("========Enter into concatPDFs method()"); 
	        Document document = new Document();
	        try {
	        	PolicyNo = PolicyNo.split("-")[0];
	            List<InputStream> pdfs = streamOfPDFFiles;
	            List<PdfReader> readers = new ArrayList<PdfReader>();
	            int totalPages = 0;
	            Iterator<InputStream> iteratorPDFs = pdfs.iterator();
	 
	            // Create Readers for the pdfs.
	            while (iteratorPDFs.hasNext()) {
	                InputStream pdf = iteratorPDFs.next();
	                PdfReader pdfReader = new PdfReader(pdf);
	                readers.add(pdfReader);
	                totalPages += pdfReader.getNumberOfPages();
	            }
	            // Create a writer for the outputstream
	            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
	 
	            document.open();
	            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
	                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
	            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF
	            // data
	 
	            PdfImportedPage page;
	            int currentPageNumber = 0;
	            int pageOfCurrentReaderPDF = 0;
	            Iterator<PdfReader> iteratorPDFReader = readers.iterator();
	 
	            // Loop through the PDF files and add to the output.
	            while (iteratorPDFReader.hasNext()) {
	                PdfReader pdfReader = iteratorPDFReader.next();
	 
	                // Create a new page in the target for each source page.
	                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
	                    document.newPage();
	                    pageOfCurrentReaderPDF++;
	                    currentPageNumber++;
	                    page = writer.getImportedPage(pdfReader,
	                            pageOfCurrentReaderPDF);
	                    cb.addTemplate(page, 0, 0);
	 
	                    // Code for pagination.
	                    if (paginate) {
	                        cb.beginText();
	                        cb.setFontAndSize(bf, 9);
	                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, "Policy No:"+PolicyNo+"  "
	                                + currentPageNumber + " of " + totalPages, 500/*520*/,
	                                5, 0);
	                        cb.endText();
	                    }
	                }
	                pageOfCurrentReaderPDF = 0;
	            }
	            outputStream.flush();
	            document.close();
	            outputStream.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (document.isOpen())
	                document.close();
	            try {
	                if (outputStream != null)
	                    outputStream.close();
	            } catch (IOException ioe) {
	                ioe.printStackTrace();
	            }
	        }
	    }
}