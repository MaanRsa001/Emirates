package rsa.pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import rsa.opencoverpdf.finalprint;

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
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.Home.MasterController.NumberToWord.AbstractProcessor;
import com.maan.Home.MasterController.NumberToWord.DefaultProcessor;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.services.policyInfo;

public class GHQPDFCreatorBean
{
	private static final Logger logger = LogUtil.getLogger(GHQPDFCreatorBean.class);
	static public AbstractProcessor processor;
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String DRAFTMODE = "draftMode";
	final static transient private String QUOTENO = "QuoteNo";
	final static transient private String YES = "YES";
	final static transient private String TEST = "Test";
	final static transient private String LINEBREAK = "\n";
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
	PDFCreatorBean pdfBean=new PDFCreatorBean();
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
		
		Map premiumdetails;
		premiumdetails = new HashMap();
		Map commoditydetails;
		commoditydetails = new HashMap();
		String 	BrokerName = "", 
		coverName = "",	carrierName = "", TransitFrom = "", finalDestination = "",
		blAwbNo = "", blAwbDate = "", issuerName="",extraCoverId="", sailDate="", contractNo="", fmsCaseNo="", refNo="";

		String finalAmtRs = "";
		String finalAmtPs = "";
		int finalRsConversion = 0;
		String ConvertToWords;
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
			CustomerCountry = "", CustomerEmirate = "";
		
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
				 ExchangeRate = "", CurrencyName = "",currencyShortName="",
				 TransportName = "",SaleTermValue = "",SaleTermName = "", destCountryId = "";
		int finalCount = 0;
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
		String[] premiumRate=new String[0];
		String[] premiumSingle=new String[0];
		String[] warRate=new String[0];
		String[] warPremium=new String[0];
		String cusCode ="",createDate="", policyNoDummy="", basisVal="", currencyId="";
		double exchangeRate=0.0;
		Map taxDetails;taxDetails = new HashMap();
		//getting values modification]
		finalprint dbBean = new finalprint();
		com.maan.premium.DAO.PremiumInputsBean premiumBean = new com.maan.premium.DAO.PremiumInputsBean();
		try 
		{
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
			rsa.opencoverpdf.finalprint bean=new rsa.opencoverpdf.finalprint();
			if (DRAFTMODE.equalsIgnoreCase(displayMode)) 
			{
				finalBean.setCountBackDays(countBackDays);
				if("11".equals(pid))
				{
					premiumdetails = bean.checkingvalues(PolicyNo, login, QUOTENO,brokerBra,cid,"None");
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
			
			HashMap AmountDetails = new HashMap();

			String checkPolicyDatas = "NODATAS";
			
			checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");

			if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
			{
				currencyId = (String) premiumdetails.get("currencyId") == null ? ""
						: (String) premiumdetails.get("currencyId");// [0][14]);
				basisVal = (String) premiumdetails.get("basisVal") == null ? ""
						: (String) premiumdetails.get("basisVal");
				basisVal=basisVal.replaceAll("ACTUAL AMOUNT", "");
				openCoverNo=(String)premiumdetails.get("openCoverNo")==null ?"":(String)premiumdetails.get("openCoverNo");
				contractNo = (String) premiumdetails.get("contractNo") == null ? ""
						: (String) premiumdetails.get("contractNo");// [0][2);
				fmsCaseNo = (String) premiumdetails.get("fmsCaseNo") == null ? ""
						: (String) premiumdetails.get("fmsCaseNo");// [0][2);
				refNo = (String) premiumdetails.get("refNo") == null ? ""
						: (String) premiumdetails.get("refNo");// [0][2);
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
				policyDate = (String) premiumdetails.get("ActualPolicyGeneratedDate") == null ? "":(String) premiumdetails.get("ActualPolicyGeneratedDate");
				approvedBy = (String) premiumdetails.get("APPROVEDBY")==null?"":(String) premiumdetails.get("APPROVEDBY");
				coverName = (String) premiumdetails.get("CoverName") == null ? ""	: (String) premiumdetails.get("CoverName");
				extraCoverId = (String) premiumdetails.get("ExtraCoverId") == null ? ""	: (String) premiumdetails.get("ExtraCoverId");
				TransportName = (String) premiumdetails.get("TransportName") == null ? "": (String) premiumdetails.get("TransportName");// [0][12]);
				CurrencyName = (String) premiumdetails.get("CurrencyName") == null ? ""
						: (String) premiumdetails.get("CurrencyName");// [0][14]);
				
				if("2".equals(currencyId)){
					premiumBean.setLoginBra(broBra);
					ExchangeRate = premiumBean.getGHQExchangeRate(login);
				}else{
					ExchangeRate = (String) premiumdetails.get("ExchangeRate") == null ? "0"
							: (String) premiumdetails.get("ExchangeRate");// [0][15]);
				}
				exchangeRate=Double.parseDouble(ExchangeRate);
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
				
				premiumRate=new String[finalCount];
				premiumSingle=new String[finalCount];
				warRate=new String[finalCount];
				warPremium=new String[finalCount];
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
					premiumRate[i]=(String)commoditydetails.get("PremiumRate" + (i+1))==null?"0":(String)commoditydetails.get("PremiumRate" + (i+1));
					premiumSingle[i]=(String)commoditydetails.get("PremiumSingle" + (i+1))==null?"0":(String)commoditydetails.get("PremiumSingle" + (i+1));
					warRate[i]=(String)commoditydetails.get("WarRate" + (i+1))==null?"0":(String)commoditydetails.get("WarRate" + (i+1));
					warPremium[i]=(String)commoditydetails.get("WarPremium" + (i+1))==null?"0":(String)commoditydetails.get("WarPremium" + (i+1));
					
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
					localSumIns[i] = pdis.decimalFormat(Double.parseDouble(localSumIns[i].replaceAll(",","")), decimalDigit);
					commoditySumIns[i]=pdis.decimalFormat(Double.parseDouble(commoditySumIns[i].replaceAll(",","")), decimalDigit);
					
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
		openCoverStartDate=dbBean.getOpenCoverStartDate(openCoverNo);
		policyNoDummy=new policyInfo().getPolicyDefault(new String[]{pid,pid}, PolicyNo, brokerBra, openCoverNo, "");
		if (!(DRAFTMODE.equalsIgnoreCase(displayMode)) && !(NORMALSUP.equalsIgnoreCase(displayMode))) 
		{
			finalBean.updateCommission(broBra,pid,Policy_No,pdis.decimalFormat(Double.parseDouble(commissionAmount), decimalDigit),taxPercent);
		}
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
			HeaderFooterImage pageWater;
			pageWater = new HeaderFooterImage();pageWater.onOpenDocument(writer, document);
			pageWater.setImagePath(imagePath);
			pageWater.setFooterImagePath(footerImagePath);
			pageWater.setCreateDate(createDate);
			pageWater.setDisplayText(displayText);
			pageWater.setCols(cols);
			
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
			tableCreation.insertCell("\n\n\n\n\n\n\n\n", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			if(DRAFTMODE.equalsIgnoreCase(displayMode)){
				tableCreation.insertCell("QUOTE NO. "+QuoteNo, fontTextNormal, Rectangle.NO_BORDER,8, 2);
			}else{
				tableCreation.insertCell("MARINE CARGO OPEN POLICY CERTIFICATE NO. "+PolicyNo.substring(PolicyNo.lastIndexOf("-")+1,PolicyNo.length()), fontTextNormal, Rectangle.NO_BORDER,8, 2);
			}
			tableCreation.insertCell("DATED "+sailDate, fontTextNormal, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("TO General Headquarters, UAE ARMED FORCES, General Purchasing Directorate", fontTextNormal, Rectangle.NO_BORDER,8, 2);
			if(DRAFTMODE.equalsIgnoreCase(displayMode)||TEST.equalsIgnoreCase(usrModeController)){
				tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+policyNoDummy.substring(0,policyNoDummy.lastIndexOf("-"))+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}else{
				tableCreation.insertCell("According to your demand, we certify that we have insured as per the conditions of your Policy No "+PolicyNo.substring(0,PolicyNo.lastIndexOf("-"))+", the following: ", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			logger.info("PolicyNo..."+PolicyNo);
			logger.info("QuoteNo..."+QuoteNo);
			tableCreation.insertCell("\n\n", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("CONTRACT NO.", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(contractNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("\n\n\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("FMS CASE NO.", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(fmsCaseNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("REF.", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell(refNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("VOYAGE", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			if(!"".equalsIgnoreCase(TransitFrom.trim()))
			{
				TransitFrom=TransitFrom+", ";
			}
			finalDestination=(finalDestination==null||"Others".equalsIgnoreCase(finalDestination))?"":finalDestination;
			if(!"".equalsIgnoreCase(finalDestination.trim()))
			{
				finalDestination=finalDestination+",";
			}
			tableCreation.insertCell("From: "+TransitFrom+transCountryName+" To: "+finalDestination+finalCountryName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("CONVEYANCE", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			tableCreation.insertCell((carrierName.trim().length()>0?carrierName+", ":"")+ TransportName + (sailDate.trim().length()>0?", Sail Date: "+sailDate:""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
			if(blAwbNo.trim().length()>0){
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("AWB / BOL NO.", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(blAwbNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			
			for(int print=0;print<finalCount;print++){
				tableCreation.insertCell("SUBJECT-MATTER INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				StringBuffer goods = new StringBuffer();
				goods.append(commodityDes[print]);
				//goods.append(" ");
				if(invoice[print]!=null&&invoice[print].length()>0){
					goods.append("INV# ");
					goods.append(invoice[print]);
				}
				tableCreation.insertCell(goods.toString(), fontTextNormal, Rectangle.NO_BORDER,5, 0);
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
					tableCreation.insertCell("BASIS OF VALUATION", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(currencyShortName+" "+commoditySumIns[print]+(!"0".equals(SaleTermValue)?(" + "+SaleTermValue+"%"):"")+(tolFlag?(" + "+toleranceName):"")+(saleFlag || tolFlag?"; ":"")+(saleFlag?(SaleTermName):"")+(tolFlag?((saleFlag?" + ":"")+toleranceName):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("SUM INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					commoditySum[print]=pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(commoditySum[print].replaceAll(",",""),currencyId,true)), decimalDigit);
					tableCreation.insertCell((basisVal.length()>0?(basisVal+" "):"")+currencyShortName +" "+commoditySum[print], fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}else
				{
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("BASIS OF VALUATION", fontTextNormal, Rectangle.NO_BORDER,3, 0);					
					tableCreation.insertCell(currencyShortName+" "+commoditySumIns[print]+(!"0".equals(SaleTermValue)?(" + "+SaleTermValue+"%"):"")+(tolFlag?(" + "+toleranceName):"")+(saleFlag || tolFlag?"; ":"")+(saleFlag?(SaleTermName):"")+(tolFlag?((saleFlag?" + ":"")+toleranceName):""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell("EXCHANGE RATE 1 "+currencyShortName+" = "+ExchangeRate+" "+currencyType, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("SUM INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					localSumround[print] = pdis.decimalFormat(Double.parseDouble(premiumBean.getRoundedValue(localSumround[print].replaceAll(",",""),"1",true)), decimalDigit);
					tableCreation.insertCell((basisVal.length()>0?(basisVal+" "):"")+currencyType +" "+localSumround[print], fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				}
			}
			//Premium table
			if(lcNumber.length()>0 && !lcNumber.equals("0") && !lcNumber.equals("NONE"))
			{
				tableCreation.insertCell("L/C No. ", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell(lcNumber, fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("\n\n", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			if(YES.equalsIgnoreCase(PremiumYes.trim())){
				tableCreation.insertCell("PREMIUM", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				double totalPremium=0.0,totalWarPremium=0.0,netPremium=0.0;
				String marinePremiumRate="", warPremiumRate="",ExcessPremium="0", issuanceFee="0";
				for(int i=0;i<finalCount;i++){
					if(finalCount==1)
					{
						marinePremiumRate+=" @ "+pdis.decimalFormat(Double.parseDouble(premiumRate[i]),6)+"%";
						warPremiumRate+="      @ "+pdis.decimalFormat(Double.parseDouble(warRate[i]),6)+"%";
					}
					totalPremium=totalPremium+(Double.parseDouble(premiumSingle[i]));
					totalWarPremium=totalWarPremium+(Double.parseDouble(warPremium[i]));
				}
				netPremium=netPremium+(totalPremium+(Double.parseDouble(ExcessPremium))+totalWarPremium+(Double.parseDouble(issuanceFee)));
				if(Double.parseDouble(premium)==0)
				{
					totalPremium=0.0;
					totalWarPremium=0.0;
					netPremium=0.0;
					issuanceFee="0.0";
					ExcessPremium="0.0";
				}
				if(YES.equalsIgnoreCase(currencyOption))
				{
					totalPremium=Double.parseDouble(premiumBean.getRoundedValue(((totalPremium/exchangeRate)+""), currencyId,true));
					totalWarPremium=Double.parseDouble(premiumBean.getRoundedValue(((totalWarPremium/exchangeRate)+""), currencyId,true));
					ExcessPremium=premiumBean.getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId,true);
					issuanceFee=premiumBean.getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId,true);
				}else
				{
					currencyId="1";
					totalPremium=Double.parseDouble(premiumBean.getRoundedValue(totalPremium+"", currencyId,true));
					totalWarPremium=Double.parseDouble(premiumBean.getRoundedValue(totalWarPremium+"", currencyId,true));
					ExcessPremium=premiumBean.getRoundedValue(ExcessPremium+"", currencyId,true);
					issuanceFee=premiumBean.getRoundedValue(issuanceFee+"", currencyId,true);
				}
				netPremium=totalPremium+(Double.parseDouble(ExcessPremium))+totalWarPremium+(Double.parseDouble(issuanceFee));
				
				tableCreation.insertCellPad("Marine : "+marinePremiumRate, fontTextNormal, Rectangle.NO_BORDER,3, 0, 15);
				tableCreation1.setTable(3);
				tableCreation1.insertCell((YES.equalsIgnoreCase(currencyOption)?currencyShortName:currencyType), fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell(pdis.decimalFormat(totalPremium,decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.getTable().setWidths(new float[]{10,40,80});
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,5, 0,0);
				
				tableCreation.insertCellPad("War : "+warPremiumRate, fontTextNormal, Rectangle.NO_BORDER,3, 0, 15);
				tableCreation1.setTable(3);
				tableCreation1.insertCell((YES.equalsIgnoreCase(currencyOption)?currencyShortName:currencyType), fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell(pdis.decimalFormat(totalWarPremium,decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.getTable().setWidths(new float[]{10,40,80});
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,5, 0,0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,4, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCellPad("Total : ", fontTextNormal, Rectangle.NO_BORDER,3, 0, 15);
				tableCreation1.setTable(3);
				tableCreation1.insertCell((YES.equalsIgnoreCase(currencyOption)?currencyShortName:currencyType), fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell(pdis.decimalFormat(netPremium,decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 1);
				tableCreation1.getTable().setWidths(new float[]{10,40,80});
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,5, 0,0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,4, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,3, 0);
			}else{
				tableCreation.insertCell("PREMIUM", fontTextNormal, Rectangle.NO_BORDER,3, 0);
				tableCreation.insertCell("AS AGREED", fontTextNormal, Rectangle.NO_BORDER,5, 0);
			}
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.BOTTOM,8, 0);
			tableCreation.insertCell("", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
			
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("For the resolution of any dispute and of and contestation which may arise regarding the application or interpretation of a clause of the present contract, and for anything which is not expressly foreseen, the parties declare to refer to the disposition of the English Laws governing this matter with the exception of all questions imperatively governed by the National Law.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("In the event of loss or damage for which the Company is presumed to be liable, immediate letter of Reservation must be given to the Ship Agent in the UAE, with a copy to the "+braAddress+". Any claim for loss or damage should be substantiated by a certificate for the above mentioned Agent.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			Image signImageRoyal = null;
			Image stampImageRoyal = null;
			try
			{
				signImageRoyal  = Image.getInstance(signImage);
				stampImageRoyal  = Image.getInstance(stampImage);
				signImageRoyal.scaleToFit(130,130);
			}
			catch(Exception e)
			{
				logger.debug(e);
			}
			tableCreation1.setTable(8);
			tableCreation.insertCell("\n\n\n\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation1.insertCell("Issued to Insured three original Certificates each of this tenor and date one of which being accomplished the others to stand null and void.", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			if(!DRAFTMODE.equalsIgnoreCase(displayMode)){
				tableCreation1.insertCell("\n", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("Signed on: "+ polGenDate, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("\n", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("Signed at "+place, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
				tableCreation1.insertCell("\n", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			if(approvedBy.equalsIgnoreCase("Nil"))
				tableCreation1.insertCell("Entered/Approved By: "+BrokerName, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			else
				tableCreation1.insertCell("Entered/Approved By: "+ BrokerName+"/"+approvedBy, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			if(stampStatus.equalsIgnoreCase("Y")){
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,3, 0,0);
				tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,5, 1);
			}else
			{
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0,0);
			}
			
			tableCreation.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			if(issuerName.length()<=0){
				tableCreation.insertCell("This certificate is issued by "+ brokerCompany+ " on behalf of "+braAddress, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			}
			document.add(tableCreation.getTable());
		
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
	
	public void writeDebitPDF(final String urlPath,final String urlPathFooter,final String fontPath,final String cid,final int decimalDigit,final double taxPercent,final String brokerBra,final String freightBroker,final String freightUser,
			final String loginId,final String PolicyNo,final String filePath,final String url)throws BaseException	{

		try{
			PremiumInputsBean premiumBean=new PremiumInputsBean();
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
			BrokerPoBox = "", arAccountNo="", paymentMode="", contractNo="", fmsCaseNo="", refNo="";
			
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
			
			String issuanceFee="";
			String totalWarPremium="";
			String currencyShortName="", currencyId="", SaleTermValue="", productId="", productName="";
			double exchangeRate=0.0;
			boolean fileExists=new File(filePath).exists();
			//rajesh world work stated
			String noteType=new policyInfo().getNoteType(PolicyNo);
			String productInfo[][]=new String[0][0];
			String placeCode[][];
			placeCode = finalBean.getPlaceCodes(PolicyNo,DEBIT,"11",POLICYNO);
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
					pageWater.setDisplayText(pdfBean.getNoteWaterMarkText(PolicyNo, "DN",fileExists));
				pageWater.setCols(cols);
				pageWater.setCid(cid);
				pageWater.setFontPath(fontPath);
				pageWater.setDebitPDF(DEBIT);
				
				PolicyNoFour = PolicyNo;
				
				//DB operation and assigning called from here start
				pdfBean.setUsrModeController(usrModeController);
				productInfo=pdfBean.getProductInfo(brokerBra, PolicyNo);
				if(productInfo!=null && productInfo.length>0)
				{
					productId=productInfo[0][0];
					productName=productInfo[0][1];
				}
					
				if(freightUser.equalsIgnoreCase("freight")){
					premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"Freight");
				}else if (freightBroker.equalsIgnoreCase("freightBroker"))
				{
					String freLog;
					freLog = finalBean.getFreightQuoteLogin(PolicyNo);
					if(freLog.length() > 0){
						premiumdetails = finalBean.checkingvalues(PolicyNo, freLog, POLICYNO,brokerBra,cid,"Freight");
					}else{
						premiumdetails = finalBean.checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
					}
				}
				else{
					premiumdetails = new rsa.opencoverpdf.finalprint().checkingvalues(PolicyNo, loginId, POLICYNO,brokerBra,cid,"None");
					tolDetails = finalBean.getToleranceDetails(PolicyNo,"POLICYNO",brokerBra);
				}
				currencyShortName = new rsa.opencoverpdf.finalprint().getFCName(PolicyNo,"3","POLICYNO",brokerBra);
				Map AmountDetails;
				AmountDetails = new HashMap();
				String checkPolicyDatas;
				checkPolicyDatas = "NODATAS";
				checkPolicyDatas = (String) premiumdetails.get("CheckPolicy");
				if (checkPolicyDatas.equalsIgnoreCase("DATAS"))
				{
					openCoverNo=(String)premiumdetails.get("openCoverNo")==null ?"":(String)premiumdetails.get("openCoverNo");
					contractNo = (String) premiumdetails.get("contractNo") == null ? ""
							: (String) premiumdetails.get("contractNo");// [0][2);
					fmsCaseNo = (String) premiumdetails.get("fmsCaseNo") == null ? ""
							: (String) premiumdetails.get("fmsCaseNo");// [0][2);
					refNo = (String) premiumdetails.get("refNo") == null ? ""
							: (String) premiumdetails.get("refNo");// [0][2);
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
		        	  sourceInfo=pdfBean.getCustomerInfo((String) premiumdetails.get("debitCustomerId"), brokerBra);
		           else
		        	  sourceInfo=pdfBean.getSourceInfo(loginId, brokerBra);
					        	  
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
					totalWarPremium=(String) premiumdetails.get("WarPremium") == null ? "0"
							: (String) premiumdetails.get("WarPremium");
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
						totalWarPremium=totalWarPremium+(Double.parseDouble(warPremium[i]));
						System.out.println("TOTAL WAR PREMIUM:"+totalWarPremium);
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
					}
					
					
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
					
					tableCreation2.insertCell(TAB+BrokerCompany+"-CONT "+contractNo, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("BRANCH", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("DOC. NO", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					DebitNoteNo=DebitNoteNo.length()>0?DebitNoteNo.substring(2):"";
					DebitNoteNo=DebitNoteNo.substring(0,2)+"-"+DebitNoteNo.substring(2,4)+"-"+DebitNoteNo.substring(4,5)+"-"+DebitNoteNo.substring(5,DebitNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(DebitNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(PolicyGenDate.indexOf(' ')!=-1){
						PolicyGenDate = PolicyGenDate.substring(0,PolicyGenDate.indexOf(' '));
					}
					tableCreation1.insertCell("DOC. DATE", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+PolicyGenDate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.insertCell("A/R NO", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("DEBIT NOTE", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("POLICY NUMBER", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if("11".equalsIgnoreCase(productId))
					{
						tableCreation1.insertCell(":"+TAB+PolicyNo.substring(0,PolicyNo.lastIndexOf("-"))+TAB+TAB+TAB+TAB+"CERT#: "+PolicyNo.substring(PolicyNo.lastIndexOf("-")+1,PolicyNo.length()), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}else
					{
						tableCreation1.insertCell(":"+TAB+PolicyNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("POLICY TYPE", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+productName, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("ORIGINAL INSURED", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (cusCompanyName == null||"".equalsIgnoreCase(cusCompanyName)) 
					{
						tableCreation1.insertCell(":"+TAB+ (customerFirstName+ CustomerLastName+"-CONT "+contractNo), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(cusCompanyName	+ ",\t\t\t" + customerFirstName + CustomerLastName+"-CONT "+contractNo), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have DEBITED your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("\nDESCRIPTION", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("\nCUR", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("\nAMOUNT", fontTextNormal, Rectangle.BOX,2, 2);
					StringBuffer goods = new StringBuffer();
					tableCreation1.insertCell("\n   PREMIUM DUE IN RESPECT OF MARINE INSURANCE", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.TOP| Rectangle.RIGHT,2, 5);
					double totalPremiumRate=0.0;
					double totalWarRate=0.0;
					String marinePremiumRate="";
					String warPremiumRate="";
					for(int i=0;i<finalCount;i++){
						totalPremium=totalPremium+(Double.parseDouble(premiumSingle[i]));
					}
					tableCreation1.insertCell("   CONTRACT NO.:"+contractNo, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("   REF : "+refNo, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("   FMS CASE NO.:"+fmsCaseNo, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					
					tableCreation1.insertCell("\n\n\n\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("\n\n\n\n\n\n", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("\n\n\n\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					
					netPremium=netPremium+(totalPremium+(Double.parseDouble(ExcessPremium))+(Double.parseDouble(totalWarPremium))+(Double.parseDouble(issuanceFee)));
					if(Double.parseDouble(premium)==0)
					{
						totalPremium=0.0;
						totalWarPremium="0.0";
						netPremium=0.0;
						issuanceFee="0.0";
						ExcessPremium="0.0";
						marinePremiumRate="";
						warPremiumRate="";
					}
					if(YES.equalsIgnoreCase(currencyOption))
					{
						totalPremium=Double.parseDouble(new PremiumInputsBean().getRoundedValue(((totalPremium/exchangeRate)+""), currencyId,true));
						totalWarPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(totalWarPremium)/exchangeRate)+"", currencyId,true);
						ExcessPremium=new PremiumInputsBean().getRoundedValue((Double.parseDouble(ExcessPremium)/exchangeRate)+"", currencyId,true);
						issuanceFee=new PremiumInputsBean().getRoundedValue((Double.parseDouble(issuanceFee)/exchangeRate)+"", currencyId,true);
					}else
					{
						currencyId="1";
						totalPremium=Double.parseDouble(new PremiumInputsBean().getRoundedValue(totalPremium+"", currencyId,true));
						totalWarPremium=new PremiumInputsBean().getRoundedValue(totalWarPremium, currencyId,true);
						ExcessPremium=new PremiumInputsBean().getRoundedValue(ExcessPremium+"", currencyId,true);
						issuanceFee=new PremiumInputsBean().getRoundedValue(issuanceFee+"", currencyId,true);
					}
					netPremium=(totalPremium+(Double.parseDouble(ExcessPremium))+(Double.parseDouble(totalWarPremium))+(Double.parseDouble(issuanceFee)));
					/*if(commissionStatus.equalsIgnoreCase("Y") && "N".equalsIgnoreCase(noteType)) // Debit Commission Status
					{
						if(!"0".equals(BrokerCommission)&&BrokerCommission.length()>0 && !"0.0".equals(BrokerCommission))
						{
							commissAmtRound=(totalPremium+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))* (Double.parseDouble(BrokerCommission) / 100)+"";
							if(commissAmtRound.length() > 0 && commissAmtRound.indexOf(',') != -1){
								commissAmtRound = commissAmtRound.replaceAll(",","");
							}
							commissAmtRound=new PremiumInputsBean().getRoundedValue(Double.parseDouble(commissAmtRound)+"", currencyId);
							netPremium=((totalPremium+Double.parseDouble(ExcessPremium)+Double.parseDouble(totalWarPremium))-Double.parseDouble(commissAmtRound))+(Double.parseDouble(issuanceFee));
						}
					}*/
					//tableCreation1.insertCell("\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);					
					tableCreation1.insertCell("   "+pdfBean.getCoreLoginId(PolicyNo), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					if(YES.equalsIgnoreCase(currencyOption))
					{
						tableCreation1.insertCell(currencyShortName, fontTextNormal, Rectangle.BOX,1, 2);
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
					word1=processor.getName(word1.replaceAll(",",""));
					word1=word1.trim().length()<=0?"Zero":word1;
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					if(YES.equalsIgnoreCase(currencyOption))
						tableCreation1.insertCell("   "+currencyShortName+" "+word1+ " And "+pdfBean.getSubCurrencyName(currencyId, cid)+" "+filsWord+" Only", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					else
						tableCreation1.insertCell("   "+currencyType1+" "+word1+ " And "+fills+" "+filsWord+" Only", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.insertCell("\n\n\n\n\n\n\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOX,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation1.setTable(8);
					tableCreation1.insertCellPad("BANKING DETAILS", fontTextUnderLine,Rectangle.NO_BORDER, 8, 0, 10);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
					tableCreation1.insertCell("   Beneficiary", fontTextNormal,Rectangle.NO_BORDER, 2, 0);
					tableCreation1.insertCell(": "+braAddress, fontTextNormal,Rectangle.NO_BORDER, 6, 0);
					tableCreation1.insertCell("   Account No.: ", fontTextNormal,Rectangle.NO_BORDER, 2, 0);
					tableCreation1.insertCell(": 18 500 6032 5", fontTextNormal,Rectangle.NO_BORDER, 6, 0);
					tableCreation1.insertCell("   With       ", fontTextNormal,Rectangle.NO_BORDER, 2, 0);
					tableCreation1.insertCell(": Emirates Insurance Co.", fontTextNormal,Rectangle.NO_BORDER, 6, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 2, 0);
					tableCreation1.insertCell("  Khalidiya Branch", fontTextNormal,Rectangle.NO_BORDER, 6, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 2, 0);
					tableCreation1.insertCell("  P.O.Box 46175, Emirates Insurance Co.", fontTextNormal,Rectangle.NO_BORDER, 6, 0);
					tableCreation1.insertCell("\n\n\n\n", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
					
					String urlSign;
					urlSign = url+"\\"+signImage;
					Image signatureImage;
					signatureImage = Image.getInstance(urlSign);
					signatureImage.scaleToFit(156, 107);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.BOX,4, 1);
					tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,4, 1);
					
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("IMPORTANT CLIENT NOTICE: The premium shown above is due for payment within a maximum period of 30 days from the date of this Debit Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
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
	public String getCurrencyOption() {
		return currencyOption;
	}

	public void setCurrencyOption(final String currencyOption) {
		this.currencyOption = currencyOption;
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
}