package com.maan.webservice.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import rsa.pdf.PDFCreatorBean;
import rsa.pdf.QutationSchedule;
import rsa.pdf.finalprint;

import com.iii.premia.maansarovar.services.endorsementprocess.EndorsementProcessResponseInfo;
import com.iii.premia.maansarovar.services.marinecertificate.MarineCertificateResponseInfo;
import com.iii.premia.maansarovar.services.marinedirect.MarineDirectResponseInfo;
import com.maan.Mail.DAO.MailInformation;
import com.maan.Mail.DAO.MailerBean;
import com.maan.Mail.controller.MailControllerNew;
import com.maan.Mail.controller.MailTriggerReferral;
import com.maan.admin.CommodityCountryRateDAO;
import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.integration.IntegrationService;
import com.maan.quotation.service.QuotationService;
import com.maan.webservice.ReferralValidation;
import com.maan.webservice.VatPremiumCalc;
import com.maan.webservice.WebServicePolicyInfo;
import com.maan.webservice.dao.CommodityRateCalculator;
import com.maan.webservice.dao.PolicyGenerationDAO;

public class PolicyGenerationService 
{
	private static final Logger logger = LogUtil.getLogger(PolicyGenerationService.class);
	QuotationService service=new QuotationService();
	PolicyGenerationDAO policyDao=new PolicyGenerationDAO();
	private WebServicePolicyInfo info;
	//Map<String, Object> session=ActionContext.getContext().getSession();
	//private String belongingBranch=(String)session.get("BelongingBranch");

	public WebServicePolicyInfo getInfo() {
		return info;
	}
	public void setInfo(WebServicePolicyInfo info) {
		this.info = info;
	}
	public StringBuffer error=new StringBuffer();
	public WebServicePolicyInfo getPolicyInfo(String refNo)
	{
		return policyDao.getPolicyInfo(refNo);
	}
	public String[][] getCommodityInfo(String refNo, String branchCode)
	{
		return policyDao.getCommodityInfo(refNo, branchCode);
	}
	public void insertUpdateCommodityInfo(String refNo, String[][] commodityInfo,String belongingBranch)
	{
		String applicationNo=info.getApplicationNo();
		if(StringUtils.isEmpty(applicationNo)){
			applicationNo=policyDao.getMaxApplicationNo();
			info.setApplicationNo(applicationNo);
		}
		policyDao.updateApplicaitonNo(info);
		policyDao.addCommodityInfo(info,belongingBranch);
	}
	public String quoteGeneration(String refNo, WebServicePolicyInfo info, String[][] commodityInfo,String belongingBranch)
	{
		ReferralValidation validation=new ReferralValidation();
		String[][] commoditys=new String[0][0];
		Map<String, Object> premiumInfo=null;
		Map<String, Object> taxInfo=null;
		Map<String, String> commodityMap=getTotalCommodityInfo(commodityInfo);
		boolean dubaiTradeStatus=service.getDubaiTradeStatus(info.getLoginId(), info.getBranchCode());
		if(commodityMap!=null && !commodityMap.isEmpty()){
			info.setTotalCommodity((String)commodityMap.get("totalItems"));
			info.setTotalSumInsured((String)commodityMap.get("totalSum"));
		}
		info.setReferralStatus("Normal");
		//if(!info.isEndt() || policyDao.isFinancial(info.getApplicationNo())){
		
		System.out.println("info.getUserType()"+info.getUserType());
		if(info.isEndt() || "admin".equalsIgnoreCase(info.getUserType())){
			commoditys=policyDao.getCommodityRateInfo(info.getApplicationNo());
		}
		String referralType=validation.getReferralType(info,belongingBranch);
		/*premiumInfo=calculatePremium(info.getApplicationNo(), info.getModeOfTransport(), info.getCoverages(), info.getSaleTermValue(),
					commoditys, info.getToleranceValue(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getBranchCode(), info.getWsrcc(), 
					info.getLoginId(), info.getOriginatingWareHouseCoverage(), info.getDestinationWareHouseCoverage(), info.getOriginatingCountry(), 
					info.getDestinationCountry(), info.getSeaCoverages(), info.getCurrency(), info.getExchangeId(), info.getCurrencyName(), 
					info.getCurrencyValue(), info.getPolicyStartDate(), info.getBrokerType(), info.getMinimumPremium(), info.isEndt(),
					info.getBrokerCode(), info.getLoginBranch(), info.getPromotinalCode());*/
		premiumInfo=calculatePremium(info.getApplicationNo(), info.getModeOfTransport(), info.getCoverages(), info.getSaleTermValue(),
				commoditys, info.getToleranceValue(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getBranchCode(), info.getWsrcc(), 
				info.getLoginId(), info.getOriginatingWareHouseCoverage(), info.getDestinationWareHouseCoverage(), info.getOriginatingCountry(), 
				info.getDestinationCountry(), info.getSeaCoverages(), info.getCurrency(), info.getExchangeId(), info.getCurrencyName(), 
				info.getCurrencyValue(), info.getPolicyStartDate(), info.getBrokerType(), info.getMinimumPremium(), info.isEndt(),
				belongingBranch, info.getLoginBranch(), info.getPromotinalCode(), belongingBranch);
		if(StringUtils.isNotEmpty(referralType)){
			info.setReferralType(referralType);
			info.setReferralStatus("Referal");
		}

		if(premiumInfo!=null && !premiumInfo.isEmpty()){
			info.setTotalPremium((String)premiumInfo.get("totalPremium"));
			info.setTotalWarPremium((String)premiumInfo.get("totalWarPremium"));
			info.setTotalSaleterm((String)premiumInfo.get("totalSaleterm"));
			info.setTotalTolerance((String)premiumInfo.get("totalTolerance"));
			if(dubaiTradeStatus)
				info.setExcessPremium(premiumInfo.get("discountPremium").toString());
			if("Referal".equalsIgnoreCase((String)premiumInfo.get("referralStatus"))){
				info.setReferralStatus("Referal");
				referralType+=(StringUtils.isEmpty(referralType)?"":"~")+"Commodity Referral";						
				info.setReferralType(referralType);
				//info.setReferralType("Commodity Referral");
			}
		}
		//}
		//taxInfo=getTaxInfo(info.getBranchCode(), info.getLoginId(), info.getIssuer());
		taxInfo=getTaxInfo(info.getBranchCode(), info.getLoginId(), info.getIssuer());
		String endtType=policyDao.getEndtType(info.getApplicationNo());
		//if(!StringUtils.contains(endtType, "41")){
			
		if(!info.isEndt() && !CollectionUtils.isEmpty(taxInfo)){
			//info.setPolicyFee(calculatePolicyFee(info.getBranchCode(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getTotalPremium(), info.getExcessPremium(), (String)taxInfo.get("POLICY_FEE_STATUS"), info.getBrokerType()));
			info.setPolicyFee(calculatePolicyFee(info.getBranchCode(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getTotalPremium(), info.getExcessPremium(), (String)taxInfo.get("POLICY_FEE_STATUS"), info.getBrokerType()));
			info.setTotalPremium(String.valueOf(Double.parseDouble(info.getTotalPremium())+Double.parseDouble(info.getPolicyFee())));
		}
		if(info.isEndt()){
			//String endtType= policyDao.getEndtType(info.getApplicationNo());
			if(StringUtils.contains(endtType, "41")){
				info.setReferralStatus("");
				info.setReferralType("");
			}else {
				boolean financialEndt=policyDao.checkFinancialEndtCategory(endtType);
				if(!financialEndt){
					info.setReferralStatus("");
					info.setReferralType("");
				}
			}
		}
		if("Referal".equalsIgnoreCase(info.getReferralStatus())){
			info.setAdminReferralYN("N");
		}
		if("Y".equalsIgnoreCase(info.getAdminReferralYN())){
			//info.setReferralStatus("Referal");
			info.setReferralType(info.getAdminReferralRemarks());
		}
		
		policyDao.insertOrUpdateMarineInfo(commoditys, info.getUserType(), info, premiumInfo);
		
		info.setQuoteNo(policyDao.insertOrUpdateQuoteInfo(info));
		if(info.isEndt()){
			//String policyFee=calculatePolicyFee(info.getBranchCode(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getTotalPremium(), info.getExcessPremium(), (String)taxInfo.get("POLICY_FEE_STATUS"), info.getBrokerType());
			//policyFee=String.valueOf(-1*(Double.parseDouble(policyFee)));
			new VatPremiumCalc().updateMarineVatinfo(info.getQuoteNo(), info.getBranchCode(), info.getProductId());
			//policyDao.updateEndtPolicyFee(info.getApplicationNo(), policyFee);
		}

		if(dubaiTradeStatus){
			policyDao.updateDiscountPercentage(premiumInfo.get("discountPercentage").toString(),info.getPromotinalCode(),info.getApplicationNo());
		}

		if(info.isEndt()){
			if(policyDao.endtPolicyFee(info.getApplicationNo()) && !CollectionUtils.isEmpty(taxInfo)){
				String policyFee=calculatePolicyFee(info.getBranchCode(), info.getProductId(), info.getOpenCoverPolicyNo(), info.getTotalPremium(), info.getExcessPremium(), (String)taxInfo.get("POLICY_FEE_STATUS"), info.getBrokerType());
				 endtType=policyDao.getEndtType(info.getApplicationNo());
				 if(!StringUtils.contains(endtType, "41"))
					policyDao.updateEndtPolicyFee(info.getApplicationNo(), policyFee);
			}
			policyDao.updateEndtPremium(info.getQuoteNo());

		}else{
			policyDao.updateMarineAndWarPremium(info.getQuoteNo());
			new VatPremiumCalc().updateMarineVatinfo(info.getQuoteNo(), info.getBranchCode(), info.getProductId());

		}
		
		return info.getQuoteNo();
	}
	public Map<String, String> getTotalCommodityInfo(String[][] commodityInfo)
	{
		Map<String, String> commodityMap=new HashMap<String, String>();
		String commodities="";
		double sumInsured=0.0;
		double totalSumInsured=0.0;
		if(commodityInfo!=null && commodityInfo.length>0)
		{
			for (int i = 0; i < commodityInfo.length; i++) {
				commodities+=commodityInfo[i][1]+",";
				sumInsured=Double.parseDouble(commodityInfo[i][2]==null?"0":commodityInfo[i][2]);
				totalSumInsured+=sumInsured;
			}
			commodityMap.put("items", commodities);
			commodityMap.put("totalSum", totalSumInsured+"");
			commodityMap.put("totalItems", commodityInfo.length+"");
		}
		return commodityMap;
	}
	public String customerGeneration(WebServicePolicyInfo info)
	{
		String customerId = info.getCustomerId();
		if(!"".equals(customerId) || "".equals(customerId) || info.getRefNo().equals(info.getApplicationNo())){
			customerId=policyDao.insertOrUpdateCustomerInfo(info);
		}
		return customerId;
	}

	/*public void saveLcInfo(WebServicePolicyInfo info) {
		policyDao.insertOrUpdateLcInfo(info);
	}*/

	public String policyGeneration(WebServicePolicyInfo info,String belongingBranch)
	{
		String policyNo="";
		policyNo=policyDao.generatePolicy(info,belongingBranch);
		info.setStatus("P");
		return policyNo;
	}
	public void insertOrUpdateMarinePolicyInfo(WebServicePolicyInfo info)
	{
		policyDao.insertOrUpdateMarinePolicyInfo(info);
		policyDao.insertOrUpdateMarineResultInfo(info);
		policyDao.updateQuoteInfo(info);
	}
	public void insertOrUpdateMarinePolicyInfo(WebServicePolicyInfo info, String[][] commodityInfo)
	{
		policyDao.insertOrUpdateMarinePolicyInfo(info);
		policyDao.updateMarineCommodityInfo(info.getApplicationNo(), commodityInfo);
	}
	public String updateQuoteInfo(WebServicePolicyInfo info)
	{
		return policyDao.updateQuoteInfo(info);
	}
	public String updatePolicyInfo(WebServicePolicyInfo info)
	{
		return policyDao.updatePolicyInfo(info);
	}
	public void updateCommodityInfo(WebServicePolicyInfo info)
	{
		policyDao.updateCommodityInfo(info);
	}
	public String updateDraftInfo(WebServicePolicyInfo info)
	{
		return policyDao.updateDraftInfo(info);
	}
	@SuppressWarnings("unchecked")
	public String mailGeneration(WebServicePolicyInfo info, String mailFrom, String refStatus)
	{
		logger.info("processRequest mailcontroller method()");
		try{
			String emailSubjectTxt;
			String toId = "";
			String ccId = "";
			String mess = "";
			String emailSub = "";
			final MailerBean mailer =new MailerBean();
			final Map<String, String> freDetails = new HashMap<String, String>();
			String quoteNo = info.getQuoteNo();
			String loginId;
			String productId;
			String applicationNo = info.getApplicationNo();
			String openCoverNo;
			if(applicationNo == null)
			{
				mailer.setQuoteNo(quoteNo);
				mailer.getQuoteDetails("Quote");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				openCoverNo = mailer.getOpenCoverNo();
			}else{
				mailer.setApplicationNo(applicationNo);
				mailer.getQuoteDetails("Application");
				applicationNo = mailer.getApplicationNo();
				loginId = mailer.getLoginId();
				productId = mailer.getProductId();
				openCoverNo = mailer.getOpenCoverNo();
				quoteNo =mailer.getQuoteNo();
			}

			final String brokerBra = info.getBranchCode();
			String cid="";
			String decimalPlace ="";
			String CurrencyType="";
			CurrencyType=info.getBrokerCurrencyType();
			cid = info.getBrokerOriginatingCountryId();
			decimalPlace = mailer.isNull(info.getBrokerDecimalPlace(),"2");
			final java.text.NumberFormat fmt1 = new java.text.DecimalFormat(mailer.getFormatDigit(decimalPlace));
			mailer.setQuoteNo(quoteNo);
			mailer.setLoginId(loginId);
			mailer.setApplicationNo(applicationNo);

			String scrnFrom  = "None";
			logger.info("royal test..mailFrom......."+mailFrom);

			final MailInformation mailBean = new MailInformation();
			final StringBuffer emailMsgTxt = new StringBuffer();
			emailMsgTxt.append(mailBean.getEmailMsgTxt(quoteNo,mailFrom,refStatus,mess));
			final Map<String, String> mailInfo = (Map<String, String>)mailBean.getDetailedTable(brokerBra,cid,quoteNo,loginId,applicationNo,CurrencyType,fmt1,mailFrom,freDetails,"");
			emailMsgTxt.append((String)mailInfo.get("content"));
			emailMsgTxt.append(mailBean.getCluausesDetails(brokerBra,cid,quoteNo,loginId,applicationNo,productId,"",openCoverNo));		
			final String admrem = (String)mailInfo.get("admrem");
			final String remarks = ((String)mailInfo.get("remarks")).replace("~", ",");
			final String adminid =mailer.getAdminMail(brokerBra);

			final String basePaths = info.getBasePath()+"MailServerInfo/MailServerInfo.xml"; 
			emailSubjectTxt = mailBean.getEmailSubjectTxt(remarks,mailFrom,emailSub,productId,quoteNo,(String)mailInfo.get("ClientName"));
			final String mailId = (String)mailInfo.get("id");
			String mailIds = "";

			String fromName = (String)mailInfo.get("brokerName");
			final String[] emailList = mailBean.getEmailList(remarks,"",admrem,adminid,mailId,"None",mailIds,toId);
			final String cc1Address[] = mailBean.getCcAddress(mailFrom,brokerBra,ccId,scrnFrom,loginId);
			//final mailController smtpMailSender = new mailController();
			//smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Runnable hello = new MailTriggerReferral(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			Thread thread1 = new Thread(hello);
			thread1.setDaemon(true);
			thread1.setName("ReferralMail");
			System.out.println("Started Mail Trigger...");
			thread1.start();
		}catch(Exception e){
			logger.debug(e);
		}
		
		return error.toString();
	}

	public String formatNumber(String value, String decimalPlace)
	{
		try {
			if (StringUtils.isNumeric(value)) {
				java.text.NumberFormat fmt = null;
				if (decimalPlace.equalsIgnoreCase("2")) {
					fmt = new java.text.DecimalFormat("##,##0.00");
				} else if (decimalPlace.equalsIgnoreCase("3")) {
					fmt = new java.text.DecimalFormat("##,##0.000");
				} else if (decimalPlace.equalsIgnoreCase("4"))
					fmt = new java.text.DecimalFormat("##,##0.0000");
				else {
					fmt = new java.text.DecimalFormat("##,##0.00");
				}
				value = fmt.format(Double.parseDouble(value));
			}
		} catch (Exception e) {
			logger.debug(e);
		}
		return value;
	}
	public String generatePdf(WebServicePolicyInfo info, String displayText, String displayMode)throws BaseException
	{
		logger.info("One Off print4Schedule processRequest method()");
		String IMG="images/";
		String filePath="";
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
			int decimalDigit = 0;

			String premiumYes;
			String bankerOption;
			String bankerAssOption;
			String currencyOption;
			String excessOption;
			String remarksOption;
			String PolicyNoFour = "";
			String cid = "";
			String applicationNo = "";
			String belongingBranch = "";
			String PolicyNo;
			if ("draftMode".equalsIgnoreCase(displayMode)) {
				PolicyNo = info.getQuoteNo();
				applicationNo = finalBean.getApplicationNo1(PolicyNo);
			}
			else {
				PolicyNo = info.getPolicyNo();
				applicationNo = finalBean.getApplicationNo(PolicyNo);
			}

			brokerBra = info.getBranchCode();
			belongingBranch = finalBean.getBelongingBranch(brokerBra);
			/* Removed For jasper Report
			 * By Aswin
			 * 
			 * String loginId;
				loginId = info.getLoginId();
				premiumYes = info.getPdfPremiumYN();
				bankerOption = info.getPdfBankerYN();
				bankerAssOption = info.getPdfBankerAssuredYN();
				currencyOption =info.getPdfCurrencyYN();
				excessOption =info.getPdfExcessYN();
				remarksOption =info.getPdfRemarksYN();
				String placeCode[][] = new String[0][0];
				brokerBra = info.getBranchCode();
				creatorBean.setBrokerBra(brokerBra);
				cid = info.getBrokerOriginatingCountryId();
				creatorBean.setDcid(info.getBrokerDestinationCountryId());
				decimalDigit = Integer.parseInt(info.getBrokerDecimalPlace());
				creatorBean.setTaxPercent(Double.parseDouble(info.getBrokerTax()));
				creatorBean.setWebsite(info.getBrokerSite());
				creatorBean.setCid(cid);
				creatorBean.setDecimalDigit(decimalDigit);
				creatorBean.setUsrModeController(info.getAppMode());
				//Final Screen option setter 
				creatorBean.setCurrencyOption(currencyOption);
				creatorBean.setExcessOption(excessOption);
				creatorBean.setRemarksOption(remarksOption);
				creatorBean.setBankerAssuredOption(bankerAssOption);
				creatorBean.setBankerOption(bankerOption);
				creatorBean.setPremiumYes(premiumYes);

				String check;
				creatorBean.setFontPath(info.getBasePath()+"ScheduleFont/arial.ttf");
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
				rubberOption = info.getPdfRubberStampYN();
				if(rubberOption.length()>0&&!rubberOption.equalsIgnoreCase("undefined")&&stampStatus.length()<=0)
				{
					stampStatus = rubberOption;
				}
				creatorBean.setStampStatus(stampStatus);
				creatorBean.setPolicyNo(PolicyNo);
				creatorBean.setOpenCoverNo(info.getOpenCoverPolicyNo());
				creatorBean.setDisplayMode(displayMode);
				PolicyNoFour = PolicyNo.replaceAll("/", "-");
				boolean checkFlag = false;
				if ("draftMode".equalsIgnoreCase(displayMode)){
					checkFlag = finalBean.pdfCheckingStatus(PolicyNo, loginId, "QuoteNo",brokerBra,cid);
					placeCode = finalBean.getPlaceCodes(PolicyNo,"Schedule","3","QuoteNo");
				}else{
					checkFlag = finalBean.pdfCheckingStatus(PolicyNo, loginId, "POLICYNO",brokerBra,cid);
					placeCode = finalBean.getPlaceCodes(PolicyNo,"Schedule","3","POLICYNO");
				}
				if(checkFlag)
					return filePath;
				creatorBean.setPlace(finalBean.isNull(placeCode[0][0], ""));
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


			creatorBean.setHeadImage(info.getBasePath() + IMG+headImage);
			creatorBean.setFootImage(info.getBasePath() + IMG+footImage);
			creatorBean.setSignImage(info.getBasePath() + IMG+signImage);
			creatorBean.setStampImage(info.getBasePath() + IMG+stampImage);
			if("1".equals(cid)){
				creatorBean.setSignStampImage(info.getBasePath() + IMG+signImage.replaceAll(".jpg", "")+stampImage);
			}
			String urlPath;
			urlPath = info.getBasePath() + IMG+headImage;
			creatorBean.setImagePath(urlPath);
			String urlPathFooter;
			urlPathFooter = info.getBasePath() + IMG+footImage;
			creatorBean.setFooterImagePath(urlPathFooter);
			creatorBean.setDisplayText(displayText);*/

			PolicyNoFour = PolicyNo.replaceAll("/", "-");
			String extension;
			extension = ".pdf";
			String polino;
			if ("draftMode".equalsIgnoreCase(displayMode)){
				String fileId;
				fileId = Integer.toString(((finalBean.getMaximumAmendId(PolicyNo)) - 1));
				polino = PolicyNoFour + "_" + fileId + "Draft" + extension;
			}else{
				polino = PolicyNoFour + "Schedule" + extension;
			}
			if(polino!=null && polino.length()>0 && polino.indexOf("/")!=-1)
			{
				polino=polino.replaceAll("/", "_");
			}
			if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
			{
				filePath = info.getBasePath() + "OriginalCopyPdf/" + polino;
			} 
			else if ("COPY".equalsIgnoreCase(displayText)) 
			{
				filePath = info.getBasePath() + "duplicatecopypdf/" + polino;
			} 
			else if ("DRAFT".equalsIgnoreCase(displayText)) 
			{
				filePath = info.getBasePath() + "quotepdf/" +  polino;
			} 
			else if ("TEST POLICY".equalsIgnoreCase(displayText)) 
			{
				filePath = info.getBasePath() + "testpolicypdf/" + polino;
			} 
			else if ("TEST DRAFT".equalsIgnoreCase(displayText)) 
			{
				filePath = info.getBasePath() + "testquotepdf/" + polino;
			} 
			else if ("TEST DEBIT".equalsIgnoreCase(displayText)) {
				filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Debit" + extension;
			}else if ("DEBIT".equalsIgnoreCase(displayText)){
				filePath = info.getBasePath() + "debitpdf/"  + PolicyNoFour + "Debit" + extension;
			}else if ("TEST CREDIT".equalsIgnoreCase(displayText)) {
				filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Credit" + extension;
			}else if ("CREDIT".equalsIgnoreCase(displayText)){
				filePath = info.getBasePath() + "creditpdf/"  + PolicyNoFour + "Credit" + extension;
			}else if ("TEST RECEIPT".equalsIgnoreCase(displayText)) {
				filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Receipt" + extension;
			}else if ("RECEIPT".equalsIgnoreCase(displayText)){
				filePath = info.getBasePath() + "receiptpdf/"  + PolicyNoFour + "Receipt" + extension;
			}else if ((displayText.trim()).length()<=0) 
			{
				filePath = info.getBasePath() + "OriginalPdf/" + polino;
			}

			logger.info("royal test pdf...the X FILE NAME IS " + filePath);
			File pdfFile;
			pdfFile = new File(filePath);
			//if (!pdfFile.exists() && !("Normal".equalsIgnoreCase(displayMode)))
			if (!("Normal".equalsIgnoreCase(displayMode)))
			{

				if ("ORIGINAL COPY".equalsIgnoreCase(displayText)) 
				{
					filePath = info.getBasePath()+"OriginalCopyPdf/"+polino;
				}
				else if ((displayText.trim()).length()<=0)
				{
					filePath = info.getBasePath()+"OriginalPdf/"+polino;
				}
				else if ("COPY".equalsIgnoreCase(displayText))
				{
					filePath = info.getBasePath()+"duplicatecopypdf/"+polino;
				}
				else if ("DRAFT".equalsIgnoreCase(displayText))
				{
					filePath = info.getBasePath()+"quotepdf/"+polino;
				}
				else if ("TEST POLICY".equalsIgnoreCase(displayText))
				{
					filePath = info.getBasePath()+"testpolicypdf/"+ polino;
				}
				else if ("TEST DRAFT".equalsIgnoreCase(displayText))
				{
					filePath = info.getBasePath()+"testquotepdf/"+polino;
				}else if ("TEST DEBIT".equalsIgnoreCase(displayText)) {
					filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Debit" + extension;
				}else if ("DEBIT".equalsIgnoreCase(displayText)){
					filePath = info.getBasePath() + "debitpdf/"  + PolicyNoFour + "Debit" + extension;
				}else if ("TEST CREDIT".equalsIgnoreCase(displayText)) {
					filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Credit" + extension;
				}else if ("CREDIT".equalsIgnoreCase(displayText)){
					filePath = info.getBasePath() + "creditpdf/"  + PolicyNoFour + "Credit" + extension;
				}
				/*else if ("TEST RECEIPT".equalsIgnoreCase(displayText)) {
					filePath = info.getBasePath()+ "testpolicypdf/"  + PolicyNoFour + "Receipt" + extension;
				}
				else if ("RECEIPT".equalsIgnoreCase(displayText)){
					filePath = info.getBasePath() + "creditpdf/"  + PolicyNoFour + "Receipt" + extension;
				}*/
				else if ("TEST RECEIPT".equalsIgnoreCase(displayText)) {
					filePath = info.getBasePath()+ "debitpdf/"  + PolicyNoFour + "Receipt" + extension;
				}
				else if ("RECEIPT".equalsIgnoreCase(displayText)){
					filePath = info.getBasePath() + "debitpdf/"  + PolicyNoFour + "Receipt" + extension;
				}
				else {
					filePath = info.getBasePath()+"debitpdf/"+polino;
				}

				/* Removed For jasper Report
				 * By Aswin
				 * 
				 * creatorBean.setFilePath(filePath);
				String freight;
				freight = "";
				try {
					Image paidStampImage=Image.getInstance(info.getBasePath()+IMG+"Paid_Stamp.gif");
					creatorBean.setPaidStampImage(paidStampImage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if("TEST DEBIT".equalsIgnoreCase(displayText) || "DEBIT".equalsIgnoreCase(displayText)){
					creatorBean.writeDebitPDF(urlPath,urlPathFooter,info.getBasePath()+"ScheduleFont/arial.ttf",cid,decimalDigit,Double.parseDouble(info.getBrokerTax()),brokerBra,"","",info.getLoginId(),info.getPolicyNo(),filePath,info.getBasePath()+ "/images/");
				}else if("TEST CREDIT".equalsIgnoreCase(displayText) || "CREDIT".equalsIgnoreCase(displayText)){
					creatorBean.writeCreditPDF(urlPath,urlPathFooter,info.getBasePath()+"ScheduleFont/arial.ttf",cid,decimalDigit,Double.parseDouble(info.getBrokerTax()),brokerBra,"","",info.getLoginId(),info.getPolicyNo(),filePath,info.getBasePath()+ "/images/");
				}else if("TEST RECEIPT".equalsIgnoreCase(displayText) || "RECEIPT".equalsIgnoreCase(displayText)){
					creatorBean.writeReceiptPDF(urlPath,urlPathFooter,info.getBasePath()+"ScheduleFont/arial.ttf",cid,decimalDigit,Double.parseDouble(info.getBrokerTax()),brokerBra,"","",info.getLoginId(),info.getPolicyNo(),filePath,info.getBasePath()+ "/images/");
				}else{
					creatorBean.writePDF(brokerBra,cid,decimalDigit,freight,loginId,info.getProductId(),PolicyNo);
				}*/
				com.maan.report.JasperReports jr = new com.maan.report.JasperReports();
				if("DEBIT".equalsIgnoreCase(displayText) || "DEBIT".contains(displayText)) {
					jr.debitNote(PolicyNo, brokerBra, filePath);
				}
				else if("CREDIT".equalsIgnoreCase(displayText) || "CREDIT".contains(displayText)) {
					jr.creditNote(PolicyNo,  brokerBra, filePath);
				}
				else if("RECEIPT".equalsIgnoreCase(displayText) || "RECEIPT".contains(displayText)) {
					jr.debitNote(PolicyNo, brokerBra, filePath);
				}
				else {
					jr.certificateSchedule(applicationNo, brokerBra, belongingBranch, filePath);
				}
			}
		}catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		
		return filePath;
	}
	public String generateQuoteSchedule(WebServicePolicyInfo info)
	{
		logger.info("print4Schedule processRequest()-Enter");
		QutationSchedule creatorBean = new QutationSchedule();
		String filePath="";
		try{
			String quoteNo=info.getQuoteNo();
			filePath = info.getBasePath()+"quotepdf/" + quoteNo+".pdf";
			/*
			 * Commented for Jasper Report By Aswin
			 * 
			 * creatorBean.setFilePath(filePath);
			creatorBean.setLoginBranch(info.getBranchCode());
			creatorBean.setBrokerDetails(policyDao.getResultMap("GET_BRANCH_INFO", new String[]{info.getBranchCode()}));
			creatorBean.setOpenCoverNo(info.getOpenCoverPolicyNo());
			creatorBean.setProductId(info.getProductId());
			creatorBean.setUser(info.getLoginId());
			finalprint finalBean = new finalprint();
			Map<String, Object> branchInfo=new HashMap<String, Object>();
			branchInfo.put("CurrencyAbb", info.getBrokerCurrencyType());
			branchInfo.put("CurrencyDecimal", info.getBrokerDecimalPlace());
			creatorBean.setBrokerDetails(branchInfo);
			String[][] placeCode = finalBean.getPlaceCodes(quoteNo,"Schedule",creatorBean.getProductId(),"QuoteNo");
			creatorBean.setHeadImage(info.getBasePath() + "/images/"+finalBean.isNull(placeCode[0][1], ""));
			creatorBean.setFootImage(info.getBasePath()+ "/images/"+finalBean.isNull(placeCode[0][2], ""));
			creatorBean.setSignImage(info.getBasePath() + "/images/"+finalBean.isNull(placeCode[0][3], ""));
			creatorBean.setStampImage(info.getBasePath() + "/images/"+finalBean.isNull(placeCode[0][4],""));
			creatorBean.setFontPath(info.getBasePath()+ "ScheduleFont/arial.ttf");
			creatorBean.setBulletImg(info.getBasePath() + "/images/"+"bullet.jpg");
			creatorBean.writeQuoteSchedule(quoteNo);*/

			String applicationNo = creatorBean.getApplicationNo1(quoteNo);
			String branchCode = info.getBranchCode();
			String belongingBranch = creatorBean.getBelongingBranch(branchCode);
			com.maan.report.JasperReports js = new com.maan.report.JasperReports();
			js.certificateSchedule(applicationNo, branchCode, belongingBranch, filePath);

		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("Exception @ {"+e.getMessage()+"}");
		}
		logger.info("print4Schedule processRequest()-Exit");
		return filePath;
	}
	public void updateDocInfo(WebServicePolicyInfo info, String docType, String filePath)
	{
		if(docType.equalsIgnoreCase("draft")){
			info.setDraftFilePath(filePath);
			policyDao.updateDraftInfo(info);
		}else if(docType.equalsIgnoreCase("debit")){
			info.setDebitFilePath(filePath);
			policyDao.updateDebitInfo(info);
		}else if(docType.equalsIgnoreCase("credit")){
			info.setCreditFilePath(filePath);
			policyDao.updateCreditInfo(info);
		}else if(docType.equalsIgnoreCase("quote")){
			info.setQuoteScheduleFilePath(filePath);
			policyDao.updateQuoteScheduleInfo(info);
		}
	}
	public void updateAdminReferralInfo(WebServicePolicyInfo info)
	{
		policyDao.updateAdminReferralInfo(info);
	}
	public HashMap<String, Object> calculatePremium(String applicationNo,String modeOfTransport,String coverId,
			String saleTermValue, String [][] commoditys, String toleranceValue,
			String productId,String openCoverNo,String branchCode,String wsrcc,String loginId,String orgWarehouse,
			String destWarehouse, String fromCountryId, String toCountryId, String seaOption, String currencyId, 
			String exchangeId, String currencyName, String currencyValue, String policyDate, String brokerType, String minimumPremium, boolean endt,
			String brokerCode, String loginBranch, String promotinalCode, String belongingBranch)
			{
		HashMap<String, Object> commodityRates = new HashMap<String, Object>();
		String[][] commodityRatesArray = new String[0][0];
		String firstDataName = "";// CoverId
		String baseRate="", rate = "", warRate="", discountPercentage="", totalPremium="", totalMarine="", totalWar="", totalSaleterm="", totalTolerance="", policyExcess="";
		double salePercentage = 0.0, discountPremium=0;
		double tolPercentage = 0.0;
		double conSI = 0.0;
		double pre = 0.0;
		double totalConSI = 0.0;
		double totalPre = 0.0;
		String warehouseValue = "0";
		String seaValue = "0";
		String referralStatus = "Normal", commodityReferral="";;
		double dou_SI = 0.0;
		double dou_SIAED = 0.0;
		double dou_saleValue = 0.0;
		double dou_tolValue = 0.0;
		double dou_totSaleValue = 0.0;
		double dou_totTolValue = 0.0;
		double dou_warPremium = 0.0;
		double dou_totWarPremium = 0.0;
		double dou_warRate = 0.0, dou_baseWarRate = 0.0;
		double saleFactor = 0.0;
		double tolFactor = 0.0;
		boolean warehouseStatus = false;
		boolean seaStatus = false;
		java.text.NumberFormat fmt1 = new java.text.DecimalFormat("0.00000");
		java.text.NumberFormat fmtPremium = new java.text.DecimalFormat("0.0000");
		DecimalFormat df = new DecimalFormat("#.####");
		salePercentage = Double.parseDouble(saleTermValue);
		tolPercentage = Double.parseDouble(toleranceValue);
		String endtType="";
		String  endtWarYn= "NO";
		if(endt) {
			endtType= policyDao.getEndtYype(applicationNo);
			endtWarYn=policyDao.getValue("GET_ENDT_WARSRCC", new String[]{applicationNo});
		}
		/*if(endt) {
			endtType= policyDao.getEndtYype(applicationNo);
		}*/
		if(Integer.parseInt(wsrcc)==0){
			dou_baseWarRate=0.0;
			dou_warRate=0.0;
			if(endt && endtType.contains("9")&&"YES".equalsIgnoreCase(endtWarYn)){
		   		 try{
		   			 warRate=policyDao.getConveyanceWarRate(belongingBranch, productId, openCoverNo, modeOfTransport);
		   			dou_baseWarRate=Double.parseDouble(warRate);
		   		 	}catch(Exception e){dou_baseWarRate=0.0;dou_warRate=0.0;}
		   		}
		}else{
			try{
				warRate=policyDao.getConveyanceWarRate(belongingBranch, productId, openCoverNo, modeOfTransport);
				dou_baseWarRate=Double.parseDouble(warRate);
			}catch(Exception e){dou_baseWarRate=0.0;dou_warRate=0.0;}
		}
		firstDataName=coverId;
		try{
			commodityRatesArray = policyDao.getCommodityList(applicationNo);
			if(commodityRatesArray.length>0)
			{
				for(int i=0;i<commodityRatesArray.length;i++)
				{
					try
					{
						if(commoditys!=null && commoditys.length>0)
						{      					 	
							rate=new CommodityCountryRateDAO().getCommodityCountryRate(loginId, productId, applicationNo, modeOfTransport, coverId, commodityRatesArray[i][1], fromCountryId, toCountryId, openCoverNo);
							if(rate.equals("")){
								rate=""+Double.parseDouble(commoditys[i][1]==null?"0":commoditys[i][1]);
							}
							if(Integer.parseInt(wsrcc)==0){
								dou_warRate=0.0;
							}else{	
								dou_warRate=Double.parseDouble(commoditys[i][2]==null?"0":commoditys[i][2]);							
							}
							policyExcess=StringUtils.defaultIfEmpty(commoditys[i][5], "");
							if("YES".equalsIgnoreCase(orgWarehouse) && "YES".equalsIgnoreCase(destWarehouse))
							{
								if(Integer.parseInt(modeOfTransport)==1 || Integer.parseInt(modeOfTransport)==2){
									warehouseValue=""+Double.parseDouble(commoditys[i][3]==null?"0":commoditys[i][3]);
									if(warehouseValue!=null && Double.parseDouble(warehouseValue)==-1)
										warehouseStatus=false;
									else
										warehouseStatus=true;
								}
							}
							if(Integer.parseInt(modeOfTransport)==1)
							{
								if("LCL".equalsIgnoreCase(seaOption) && Double.parseDouble(rate)!=0){
									seaValue=""+Double.parseDouble(commoditys[i][4]==null?"0":commoditys[i][4]);
									if(seaValue!=null && Double.parseDouble(seaValue)==-1)
										seaStatus=false;
									else
										seaStatus=true;
								}
							}

							if(rate!=null && Double.parseDouble(rate)==-1)
							{
								String args1[] = new String[2];
								args1[0] = firstDataName;
								args1[1] = belongingBranch;
								CommodityRateCalculator calculator = new CommodityRateCalculator();
								double dou_SITmp=Double.parseDouble(commodityRatesArray[i][2]);
								double dou_SIAEDTmp=dou_SITmp*Double.parseDouble(currencyValue);
								//This is Sale Term Percentage
								double saleFactorTmp=(salePercentage/100);
								double dou_saleValueTmp=(dou_SIAEDTmp*saleFactorTmp);
								double  conSItemp=dou_SIAEDTmp+dou_saleValueTmp;
								double tolFactorTEmp = (tolPercentage/100);
								//This is tolerance Charges
								double dou_tolValueTemp=(conSItemp*tolFactorTEmp);
								conSItemp=conSItemp+dou_tolValueTemp;
								if("3".equalsIgnoreCase(productId)){
									baseRate = calculator.getCommodityRate(modeOfTransport, coverId,seaOption , toCountryId, commodityRatesArray[i][1],commodityRatesArray[i][4],(conSItemp==0)?dou_SIAEDTmp:conSItemp, belongingBranch,policyDate,applicationNo);
									rate = df.format(Double.valueOf(baseRate)*((100-dou_baseWarRate)/100));
									dou_warRate=Double.valueOf(df.format(Double.parseDouble(baseRate)-Double.parseDouble(rate)));
								}else if ("11".equalsIgnoreCase(productId)){
									rate=policyDao.getValue(DBConstants.MARINE_COMMODITY_OCRATE, new String[]{openCoverNo, commodityRatesArray[i][1],firstDataName,seaOption});
									dou_warRate = dou_baseWarRate;
								}
								policyExcess=calculator.getPolicyExcess(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo,belongingBranch,seaOption);
							}
						}
						if(commoditys.length==0)
						{
							String args1[] = new String[2];
							args1[0] = firstDataName;
							args1[1] = belongingBranch;
							rate=new CommodityCountryRateDAO().getCommodityCountryRate(loginId, productId, applicationNo, modeOfTransport, coverId, commodityRatesArray[i][1], fromCountryId, toCountryId,openCoverNo);
							if(rate.equals(""))
							{
								double dou_SITmp=Double.parseDouble(commodityRatesArray[i][2]);
								//This is SUM INSURED LOCAL
								double dou_SIAEDTmp=dou_SITmp*Double.parseDouble(currencyValue);
								//This is Sale Term Percentage
								double saleFactorTmp=(salePercentage/100);
								double dou_saleValueTmp=(dou_SIAEDTmp*saleFactorTmp);
								double  conSItemp=dou_SIAEDTmp+dou_saleValueTmp;
								double tolFactorTEmp = (tolPercentage/100);
								//This is tolerance Charges
								double 	dou_tolValueTemp=(conSItemp*tolFactorTEmp);
								CommodityRateCalculator calculator = new CommodityRateCalculator();
								conSItemp=conSItemp+dou_tolValueTemp;	
								if("3".equalsIgnoreCase(productId)){
									baseRate = calculator.getCommodityRate(modeOfTransport, coverId,seaOption , toCountryId, commodityRatesArray[i][1],commodityRatesArray[i][4],(conSItemp==0)?dou_SIAEDTmp:conSItemp, belongingBranch,policyDate,applicationNo);
									rate = df.format(Double.valueOf(baseRate)*((100-dou_baseWarRate)/100));
									dou_warRate=Double.valueOf(df.format(Double.parseDouble(baseRate)-Double.parseDouble(rate)));
								}else if ("11".equalsIgnoreCase(productId)){
									rate=policyDao.getValue(DBConstants.MARINE_COMMODITY_OCRATE, new String[]{openCoverNo, commodityRatesArray[i][1],firstDataName,seaOption});
									dou_warRate = dou_baseWarRate;
								}
								policyExcess=calculator.getPolicyExcess(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo,belongingBranch,seaOption);
							}
							rate=(rate.length()<=0||rate.equals(""))?"0":rate;
							rate=""+Double.parseDouble(rate);
						}
						try
						{
							if(!warehouseStatus)
							{
								if("YES".equalsIgnoreCase(orgWarehouse) && "YES".equalsIgnoreCase(destWarehouse))
								{
									if(Integer.parseInt(modeOfTransport)==1 || Integer.parseInt(modeOfTransport)==2)
									{
										String wareRates = policyDao.getValue(DBConstants.MARINE_LOAD_VALUE_WTH, new String[]{belongingBranch}); 
										warehouseValue=""+(Double.parseDouble(rate)*(Double.parseDouble(wareRates)));
										rate=""+fmt1.format(Double.parseDouble(rate));
									}
								}
							}
						}
						catch(Exception e){
							e.printStackTrace();
						}
						try
						{
							if(!seaStatus)
							{	
								if(Integer.parseInt(modeOfTransport)==1)
								{
									if("LCL".equalsIgnoreCase(seaOption) && Double.parseDouble(rate)!=0)
									{
										String LCLRate=policyDao.getValue(DBConstants.MARINE_LOAD_VALUE_LCL, new String[]{belongingBranch});  
										seaValue=""+(Double.parseDouble(rate)*(Double.parseDouble(LCLRate)/100));
										if("11".equalsIgnoreCase(productId))
										{
											LCLRate="0";
											seaValue="0";
										}
									}
								}
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					catch(Exception exception)
					{
						rate="1";
						System.out.println("The Error is FETCHING PREMIUM COMPUTATION RATING ENGINE"+exception.toString());
						exception.printStackTrace();
					}
					dou_SI=Double.parseDouble(commodityRatesArray[i][2]);

					//This is SUM INSURED LOCAL
					dou_SIAED=dou_SI*Double.parseDouble(currencyValue);
					//This is Sale Term Percentage
					saleFactor=(salePercentage/100);
					//This is Tolerance Percentage
					tolFactor=(tolPercentage/100);
					//This is Sale Term Charges
					dou_saleValue=(dou_SIAED*saleFactor);
					dou_totSaleValue=dou_totSaleValue+dou_saleValue;
					conSI=dou_SIAED+dou_saleValue;
					//This is tolerance Charges
					dou_tolValue=(conSI*tolFactor);
					dou_totTolValue=dou_totTolValue+dou_tolValue;
					conSI=conSI+dou_tolValue;
					if(endt && endtType.contains("9")&&"YES".equalsIgnoreCase(endtWarYn)&&Integer.parseInt(wsrcc)==0){
						dou_warRate=0.0;
					}
					dou_warPremium=(conSI*dou_warRate)/100;
					dou_warPremium=Double.parseDouble(fmtPremium.format(dou_warPremium));					
					dou_totWarPremium=dou_totWarPremium+dou_warPremium;
					
					totalConSI=totalConSI+conSI;
					pre=((conSI*(Double.parseDouble(rate)+Double.parseDouble(warehouseValue)+Double.parseDouble(seaValue))/100));
					pre=Double.parseDouble(fmtPremium.format(pre));
					totalPre = totalPre+pre;
					commodityRates.put(commodityRatesArray[i][1],rate);
					commodityRates.put("commodityId"+commodityRatesArray[i][1],commodityRatesArray[i][1]);
					commodityRates.put("sumInsured"+commodityRatesArray[i][1],commodityRatesArray[i][2]);
					commodityRates.put("convertedSumInsured"+commodityRatesArray[i][1],""+conSI);
					commodityRates.put("premium"+commodityRatesArray[i][1],""+pre);
					//if("0".equalsIgnoreCase(rate) || "0.0".equalsIgnoreCase(rate) || (Integer.parseInt(wsrcc)!=0 && dou_warRate==0))
					//if(Double.parseDouble(rate)==0.0 || (Integer.parseInt(wsrcc)!=0 && dou_warRate==0))
					if(Double.parseDouble(rate)==0.0)
					{
						referralStatus="Referal";
						commodityReferral+="Y";
						pre=0.0;
						dou_saleValue=0.0;
						dou_tolValue=0.0;
						dou_totWarPremium-=dou_warPremium;
						dou_warPremium=0.0;
						//dou_warRate=0.0;
					}
					else
					{
						referralStatus="Normal";
						commodityReferral+="N";
					}
					try
					{
						String[] args=new String[20];
						args[0] = rate;
						args[1] = String.valueOf(pre);
						args[2] = currencyId;
						args[3] = exchangeId;
						args[4] = currencyName;
						args[5] = currencyValue;
						args[6] = String.valueOf(dou_saleValue);
						args[7] = String.valueOf(dou_warPremium);
						args[8] = referralStatus;
						args[9] = String.valueOf(dou_warRate);
						args[10] = String.valueOf(dou_SIAED);
						args[11] = commodityRatesArray[i][1];
						args[12] = seaValue;
						args[13] = warehouseValue;
						args[14] = String.valueOf(dou_tolValue);
						args[15] = "I Agreed";
						args[16] = policyExcess;
						args[17] = applicationNo;
						args[18] = commodityRatesArray[i][1];
						args[19] = "Y";
						policyDao.update(DBConstants.MARINE_UPDATE_PREMIUM_RATE,args);

						// Updating Commission 
						String brokerCommission=policyDao.getCommissionPercent(info.getLoginId(), info.getProductId(), info.getOpenCoverPolicyNo(),info.getIssuer(),info.getApplicationNo(),info.getBranchCode());
						/*if("3".equals(productId)){
	   						String directStatus = new QuotationDAO().getDirectStatus(info.getApplicationNo(),info.getBranchCode());
							if("Y".equalsIgnoreCase(directStatus) && !"5".equals(info.getExecutive())) {
								brokerCommission = policyDao.getExecutiveCommission(info.getExecutive(),info.getProductId(),info.getOpenCoverPolicyNo());
							}
							else {
								brokerCommission = policyDao.getBrokerCommission(applicationNo,productId);
							}
						}else if("11".equals(productId)){
							Map<String, Object> feeInfo = policyDao.getPolicyFeeConst(branchCode, productId, openCoverNo);
							if(feeInfo!=null && feeInfo.size()>0){
								brokerCommission = feeInfo.get("COMMISSION")==null?"0":feeInfo.get("COMMISSION").toString();
							}
						}*/
						policyDao.updateCommission(brokerCommission,applicationNo);
					}
					catch(Exception ec)
					{
						ec.printStackTrace();
					}
				}
			}
			String branchWiseCountry="";
			branchWiseCountry=service.getbranchWiseCountry(belongingBranch);

			totalMarine=PolicyGenerationDAO.roundGHQ(String.valueOf(totalPre), "1", brokerType,branchWiseCountry); 
			//totalWar=PolicyGenerationDAO.roundGHQ(String.valueOf(dou_totWarPremium), "1", brokerType,branchWiseCountry);
			if(totalPre>0)
				totalWar=PolicyGenerationDAO.round(String.valueOf(dou_totWarPremium), "1", brokerType,branchWiseCountry);
			else
				totalWar=PolicyGenerationDAO.round(String.valueOf(0), "1", brokerType,branchWiseCountry);

			if (service.getDubaiTradeStatus(loginId, branchCode)){
				double netPremium=Double.parseDouble(totalMarine)+Double.parseDouble(totalWar);
				discountPercentage=policyDao.getPromationalPremium(promotinalCode, brokerCode, branchCode);
				if(netPremium>Double.parseDouble(minimumPremium)){
					discountPremium= (netPremium*(Double.parseDouble(StringUtils.isBlank(discountPercentage)?"0":discountPercentage)/100));
					discountPremium=-Double.parseDouble(PolicyGenerationDAO.round(String.valueOf(discountPremium), "1", brokerType,branchWiseCountry));

					double minPremiumCheck=netPremium+discountPremium;
					if(minPremiumCheck<Double.parseDouble(minimumPremium)){
						discountPremium=netPremium-Double.parseDouble(minimumPremium);
						discountPremium=-Double.parseDouble(PolicyGenerationDAO.round(String.valueOf(discountPremium), "1", brokerType,branchWiseCountry));
					}
					if(discountPremium==-0){
						discountPremium=0;
					}
				}
			}

			totalSaleterm=PolicyGenerationDAO.round(String.valueOf(dou_totSaleValue), "1", brokerType,branchWiseCountry); 
			totalTolerance=PolicyGenerationDAO.round(String.valueOf(dou_totTolValue), "1", brokerType, branchWiseCountry); 
			totalPremium=String.valueOf(Double.parseDouble(totalMarine)+Double.parseDouble(totalWar)); 
			if("11".equals(productId) && "GHQ".equalsIgnoreCase(brokerType) && Double.parseDouble(totalPremium)<Double.parseDouble(minimumPremium)){
				totalPremium="0";
				totalWar="0";
			}else{
				//if(!endt)
				if(!(endt &&"41".equals(endtType))) {
					if(Double.parseDouble(totalPremium)<=0 && "Referal".equals(referralStatus))
						totalPremium="0.00";
					else
						totalPremium=Double.parseDouble(totalPremium)>Double.parseDouble(minimumPremium)?totalPremium:minimumPremium;

						if(StringUtils.contains(commodityReferral, "N"))
							totalPremium=Double.parseDouble(totalPremium)>Double.parseDouble(minimumPremium)?totalPremium:minimumPremium;
				}
			}
			if(StringUtils.contains(commodityReferral, "Y"))
				referralStatus="Referal";
		
			commodityRates.put("totalCommodity",""+commodityRatesArray.length);
			commodityRates.put("totalConvertedSI",""+totalConSI);
			commodityRates.put("totalPremium",totalPremium);
			commodityRates.put("totalWarPremium",totalWar);
			commodityRates.put("totalSaleterm",totalSaleterm);
			commodityRates.put("totalTolerance",totalTolerance);
			commodityRates.put("referralStatus",referralStatus);
			commodityRates.put("discountPremium",discountPremium);
			commodityRates.put("discountPercentage",discountPercentage);

		}catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return commodityRates;
			}
	public String calculatePolicyFee(String branchCode,String productId, String openCoverNo, String totalPremium, String additionalPremium, String policyFeeStatus, String brokerType)
	{
		double policyFeeDef=0.0, policyFeePer=0.0,policyFeeCal=0.0;
		String branchWiseCountry="";
		branchWiseCountry=service.getbranchWiseCountry(branchCode);
		Map<String, Object> feeInfo=null;
		String policyFee="0";
		try {
			if(("3".equals(productId) && "Y".equalsIgnoreCase(policyFeeStatus))||("11".equals(productId) && !policyDao.isOpenCoverDeposit(openCoverNo))){
				feeInfo=policyDao.getPolicyFeeConst(branchCode, productId, openCoverNo);
				if(feeInfo!=null && !feeInfo.isEmpty()){
					policyFeeDef=Double.parseDouble(StringUtils.defaultIfEmpty(feeInfo.get("POLICY_FEE_DEFAULT").toString(), "0"));
					policyFeePer=Double.parseDouble(StringUtils.defaultIfEmpty(feeInfo.get("POLICY_FEE_PERCENT").toString(), "0"));
					policyFeeCal=(Double.parseDouble(totalPremium==null?"0":totalPremium)+Double.parseDouble(additionalPremium))*policyFeePer/100;
					policyFee=String.valueOf(policyFeeCal>policyFeeDef?policyFeeCal:policyFeeDef);
					policyFee=PolicyGenerationDAO.round(policyFee, "1", brokerType,branchWiseCountry);
				}
			}
		}catch (Exception e){
			logger.debug("Exception @ {"+e+"}");e.printStackTrace();
		}
		return policyFee;	
	}
	public Map<String, Object> getTaxInfo(String branchCode, String loginId, String issuer)
	{
		Map<String, Object> taxInfo=new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(issuer)){
			taxInfo=(Map<String,Object>)policyDao.getResultMap(DBConstants.MARINE_FEE_ISSUER, new String[]{branchCode,loginId,branchCode,loginId});
		}else{
			taxInfo=(Map<String,Object>)policyDao.getResultMap(DBConstants.MARINE_FEE_BROKER, new String[]{branchCode,loginId,branchCode,loginId});
		}
		return taxInfo;
	}
	public void updateMarineAndWarPremium(final String quoteNo)
	{
		policyDao.updateMarineAndWarPremium(quoteNo);
	}
	public String getBelongingBranch(String branchCode) {
		return policyDao.getBelongingBranch(branchCode);
	}
	public String getValue(String option) {
		return policyDao.getValue(option);
	}
	public String getValue(String option, String[] args) {
		return policyDao.getValue(option,args);
	}
	public String callIntegrationWebservice(String applicationNo,String productId,String policyNum) {
		String result="";
		try{
		
			boolean endt = policyDao.isEndtIntg(applicationNo);
			IntegrationService intg = new IntegrationService();
			String resStatus="FAILED";
			if(endt){
				String[] policyNo=policyNum.split("-");
				EndorsementProcessResponseInfo endtRes=intg.endorsementProcessService(policyNo[0],policyNum);
				if(endtRes!=null){
					resStatus=endtRes.getStatus();
				}
				result="Status: "+endtRes.getStatus()+" Remarks:"+endtRes.getStatusMessage();
			}
			else if("3".equalsIgnoreCase(productId)){
				MarineDirectResponseInfo directRes = intg.marineDirectService(policyNum);
				if(directRes!=null){
					resStatus=directRes.getStatus();
					result="Status: "+directRes.getStatus()+" Remarks:"+directRes.getStatusMessage();
				}
			}
				
			else
			{
				MarineCertificateResponseInfo certRes=intg.marineCertificateService(policyNum);
				if(certRes!=null){
					resStatus=certRes.getStatus();
					result="Status: "+certRes.getStatus()+" Remarks:"+certRes.getStatusMessage();
				}
			}
			if("FAILED".equalsIgnoreCase(resStatus)||resStatus==null){
			    new MailControllerNew().referralIntgMail(policyNum,"","none");
			    
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void reIntegrate(String quoteNo){
		Map<String, String> map = (Map<String, String>)policyDao.policyIntegrationNormal(quoteNo);
	}

	/*class MailTriggerReferral implements Runnable{
		String emailList[], cc1Address[];
		String emailSubjectTxt, emailMsgTxt, fromName, basePaths, brokerBra;

		public MailTriggerReferral(final String recipients[], final String ccreceipients[], final String subject, final String message,
				  final String broCompanyName, final String path, final String branch){
//			this.emailList=emailList;
//			this.cc1Address=cc1Address;
//			this.emailSubjectTxt=emailSubjectTxt;
//			this.emailMsgTxt=emailMsgTxt;
//			this.fromName=fromName;
//			this.basePaths=basePaths;
//			this.brokerBra=brokerBra;

			this.emailList=recipients;
			this.cc1Address=ccreceipients;
			this.emailSubjectTxt=subject;
			this.emailMsgTxt=message;
			this.fromName=broCompanyName;
			this.basePaths=path;
			this.brokerBra=branch;
		}
		public void run(){
			try{
				System.out.println("Thread started");
				new com.maan.Mail.controller.mailController().postMail(emailList,cc1Address,emailSubjectTxt, emailMsgTxt.toString(), fromName,basePaths,brokerBra);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}*/
}
