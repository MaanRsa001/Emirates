package rsa.opencoverpdf;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.Home.MasterController.NumberToWordBean;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;
public class finalprint 
{
	private static final Logger logger = LogUtil.getLogger(finalprint.class);
		

	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String QUOTENO = "QUOTENO";
	final static transient private String PREDTS = "premiumDetails";
	final static transient private String CKPOLICY = "CheckPolicy";
	final static transient private String DEBIT = "Debit";
	final static transient private String YES = "YES";
	final static transient private String NOTHING = "NOTHING";
	final static transient private String OPENDATA = "openDatas";

	private String coverId="";
	private String extraCoverIdNew="";
	private String policyDate="";
	private String quoteId="";
	private String modeOfTransport="";
	private String warehouse="";
	private String warehouseFinal="";
	private String transitFrom="";
	private String finalDestination="";
	private String totalSumInsured="";
	private String noOfItems="";
	private String currencyValue="";
	private String policyDay="";
	private String policyMonth="";
	private String policyYear="";
	private String saleTerm="";
	private String cover="";
	private String currencyId="";
	private String exchangeId="";
	private String premium="";
	private String excessPremium="";
	private String currency="";
	private transient String fromCountryId="";
	private transient String destCountryId="";
	private String transitFromCityId="";
	private transient String destCityId="";
	private transient String seaOption="";

	public void setPremium(final String premium)
	{
		this.premium=premium;
	}
	public String getPremium()
	{
		return premium;
	}
	public void setPolicyDate(final String policyDate)
	{
		this.policyDate=policyDate;
	}
	public String getPolicyDate()
	{
		return policyDate;
	}
	public void setExcessPremium(final String excessPremium)
	{
		this.excessPremium=excessPremium;
	}
	public String getExcessPremium()
	{
		return excessPremium;
	}
	public void setSeaOptions(final String seaOption)
	{
		this.seaOption=seaOption;
	}
	public String getSeaOptions()
	{
		return seaOption;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(final String currencyId) {
		this.currencyId = currencyId;
	}
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(final String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(final String quoteId) {
		this.quoteId = quoteId;
	}
	public String getTotalSumInsured() {
		return totalSumInsured;
	}
	public void setTotalSumInsured(final String totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}
	public void setTransitFromCountryId(final String fromCountryId) {
		this.fromCountryId = fromCountryId;
	}
	public String getTransitFromCountryId() {
		return fromCountryId;
	}
	public void setFinalDestinationCountryId(final String destCountryId) {
		this.destCountryId = destCountryId;
	}
	public String getFinalDestinationCountryId() {
		return destCountryId;
	}
	public void setTransitFromCityId(final String transitFromCityId) {
		this.transitFromCityId = transitFromCityId;
	}
	public String getTransitFromCityId() {
		return transitFromCityId;
	}
	public void setFinalDestinationCityId(final String destCityId) {
		this.destCityId = destCityId;
	}
	public String getFinalDestinationCityId() {
		return destCityId;
	}
	public String getCover() {
		return cover;
	}
	public void setCoverId(final String coverId) {
		this.coverId = coverId;
	}
	public String getCoverId() {
		return coverId;
	}
	public void setCover(final String cover) {
		this.cover = cover;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(final String currency) {
		this.currency = currency;
	}
	public String getCurrencyValue() {
		return currencyValue;
	}
	public void setCurrencyValue(final String currencyValue) {
		this.currencyValue = currencyValue;
	}
	public String getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(final String finalDestination) {
		this.finalDestination = finalDestination;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(final String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(final String noOfItems) {
		this.noOfItems = noOfItems;
	}
	public String getPolicyDay() {
		return policyDay;
	}
	public void setPolicyDay(final String policyDay) {
		this.policyDay = policyDay;
	}
	public String getPolicyMonth() {
		return policyMonth;
	}
	public void setPolicyMonth(final String policyMonth) {
		this.policyMonth = policyMonth;
	}
	public String getPolicyYear() {
		return policyYear;
	}
	public void setPolicyYear(final String policyYear) {
		this.policyYear = policyYear;
	}
	public String getSaleTerm() {
		return saleTerm;
	}
	public void setSaleTerm(final String saleTerm) {
		this.saleTerm = saleTerm;
	}
	public String getTransitFrom() {
		return transitFrom;
	}
	public void setTransitFrom(final String transitFrom) {
		this.transitFrom = transitFrom;
	}

	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(final String warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouseFinal() {
		return warehouseFinal;
	}

	public void setWarehouseFinal(final String warehouseFinal) {
		this.warehouseFinal = warehouseFinal;
	}
	public String getExtraCoverIdNew() {
		return extraCoverIdNew;
	}
	public void setExtraCoverIdNew(final String extraCoverIdNew) {
		this.extraCoverIdNew = extraCoverIdNew;
	}
	
	public String[][] getMultiQuoteDetails(final String policyNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getMultiQuoteDetails method()");
		logger.debug(ENTER);
			String[][] multiQuotes;
			String sql;
			sql="select  pm.quote_no as QuoteNo,pm.policy_no as PolicyNo,pm.application_no as ApplicationNo,pm.customer_id as CustomerId,initcap(mpd.settling_agent_name) as SettlingAgentNameOthers,nvl(mpd.settling_agent_id,'0')  as SettlingAgentId,initcap(sam.settling_agent_name) as SettlingAgentName,initcap(sam.address1) as SettlingAgentAddressOne,initcap(sam.address2) as SettlingAgentAddressTwo,sam.telephone_no as SettlingAgentTelephoneNo,sam.fax_no as SettlingAgentFaxNo,initcap(pi.first_name) as CustomerFirstName,initcap(pi.last_name) as CustomerLastName,initcap(pi.address1) as CustomerAddressOne,initcap(pi.address2) as CustomerAddressTwo,pi.telephone as CustomerTelephone,initcap(pi.country) as CustomerCountry,initcap(nvl(pi.city,pi.emirate)) as CustomerEmirate,initcap(bcm.contact_person) as BrokerName,initcap(bcm.address1) as Brokeraddress1,initcap(bcm.address2) as BrokerAddress2,initcap(bcm.city) as BrokerCity,initcap(bcm.country) as BrokerCountry,bcm.phone as BrokerPhone,bcm.pobox as BrokerPoBox,initcap(nvl(bcm.city,bcm.emirate)) as BrokerEmirate,lud.commission as BrokerCommission,lud.min_premium_amount as MinPremiumAmount,lm.login_id as LoginID,lm.agency_code as LoginAgencyCode,lm.oa_code as LoginOaCode,lud.oa_code as BrokerOaCode,lud.agency_code as BrokerAgencyCode,mr.amount_payable,md.premium,initcap(pm.remarks),initcap(pm.admin_remarks),md.excess_premium,pi.pobox,initcap(pi.company_name),initcap(pm.broker_remarks),initcap(bcm.company_name)  from marine_data md,position_master pm,marine_policy_details mpd,settling_agent_master sam,personal_info pi,login_master lm,broker_company_master bcm,login_user_details lud,marine_result mr  where pm.policy_no=? and lm.login_id='opencover' and pm.application_no=md.application_no and mpd.quote_no=pm.quote_no and mr.quote_no=pm.quote_no and sam.settling_agent_id in (select nvl(settling_agent_id,'0') from marine_policy_details mpds,position_master pms  where pms.quote_no=mpds.quote_no and pms.policy_no=?) and sam.settling_agent_id not in ('0') and  pi.customer_id=pm.customer_id  and lud.oa_code=lm.oa_code and lud.agency_code=bcm.agency_code and md.status='Y' and pm.product_id=lud.product_id";
			final String args[] = {policyNo, policyNo};
			multiQuotes = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return multiQuotes;
	}

	public String getLoginUserDetails(final String loginId)throws BaseException
	{
		logger.info("OpenCover finalPrint getLoginUserDetails method()");
		logger.debug(ENTER);
			String result;
			final String args[] = {loginId};
			result=runner.singleSelection("select first_name from personal_info where customer_id in (select customer_id from login_user_details where  login_id=?)",args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getOpenCoverCustomer(final String customerId)throws BaseException
	{
		logger.info("OpenCover finalPrint getOpenCoverCustomer method()");
		logger.debug(ENTER);
			String result;
			result = runner.singleSelection("select distinct(nvl(pi.COMPANY_NAME,pi.first_name)) from personal_info pi where pi.customer_id in ('"+customerId+"')");
		logger.debug(EXIT);
		
		return result;
	}
	public Map getFinalTotal(final String amtPay,String commisP,final String miniPre,final String excessPre)throws BaseException
	{
		logger.info("OpenCover finalPrint getFinalTotal method()");
		logger.debug(ENTER);
		Map amountDetails=new HashMap();
		try{
			double commisA;
			double finalAmtPay;
			commisP=StringUtils.isBlank(commisP)?"0":commisP;
			logger.info("amtPay==>"+amtPay+"	excessPre==>"+excessPre+"	commisP==>"+commisP+"	miniPre==>"+miniPre);
				commisA=(Double.parseDouble(amtPay==null?"0":amtPay)+Double.parseDouble(excessPre==null?"0":excessPre))*(Double.parseDouble(commisP==null?"0":commisP)/100);
				finalAmtPay=(Double.parseDouble(amtPay)+Double.parseDouble(excessPre))-commisA;
				amountDetails.put("FinalPayableAmountAfterDeduction",Double.toString(finalAmtPay));
				amountDetails.put("CommissionAmount",Double.toString(commisA));
				amountDetails.put("FinalPayableAmount",amtPay);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.debug(EXIT);
		
		return amountDetails;
	}
	public int getMaximumAmendId(final String quoteId) throws BaseException
	{
		logger.info("openCover finalPrint getMaximumAmendId method()");
		logger.debug(ENTER);
			final String args[] = {quoteId};
			String amendId;
			amendId = runner.singleSelection("select nvl(max(amend_id),'0')+1 from draft_pdf_tracking where quote_no=?",args);
		logger.debug(EXIT);
		
		return Integer.parseInt(amendId);
	}
	public void insertDraftTrackingDetails(final String quoteId) throws BaseException
	{
		logger.info("openCover finalPrint insertDraftTrackingDetails method()");
		logger.debug(ENTER);
			int amendId;
			amendId = getMaximumAmendId(quoteId);
			String sqlQuery;
			sqlQuery = "insert into draft_pdf_tracking (QUOTE_NO,MODE_TRANSPORT_ID,WAREHOUSE_TO_WAREHOUSE," +
					"TRANSIT_FROM,FINAL_DESTINATION, NO_OF_ITEMS,TOTAL_SUM_INSURED,PREMIUM,EXCESS_PREMIUM,CURRENCY_ID," +
					"EXCHANGE_RATE,SALE_TERM_ID,COVER_ID,EXTRA_COVER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,transit_from_city_id,transit_from_country_id,final_destination_city_id,final_destination_country_id,sea_options) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss'),SYSDATE,SYSDATE,SYSDATE,?,?,?,?,?,?,?)";
			
			final String args[] = {quoteId,getModeOfTransport(),getWarehouse(),getTransitFrom(),getFinalDestination(),
					isNull(getNoOfItems(),"0"),isNull(getTotalSumInsured(),"0"),isNull(getPremium(),"0"),isNull(getExcessPremium(),"0"),
					isNull(getCurrencyId(),"0"),isNull(getCurrencyValue(),"0"),isNull(getSaleTerm(),"0"),
					isNull(getCoverId(),"0"),isNull(getExtraCoverIdNew(),"0"),Integer.toString(amendId),
					"DRAFT COPY","Y",isNull(getTransitFromCityId(),"0"),isNull(getTransitFromCountryId(),"0"),isNull(getFinalDestinationCityId(),"0"),isNull(getFinalDestinationCountryId(),"0"),
					isNull(getSeaOptions(),"")};
			runner.multipleInsertion(sqlQuery,args);
		logger.debug(EXIT);
		
	}
	public String[][] getPolicyQuotes(final String policyNo,final String status) throws BaseException
	{
		logger.info("openCover finalPrint getPolicyQuotes method()");
		logger.debug(ENTER);
			String[][] policyQuotes;
			if("QUOTENOMULTIPLE".equalsIgnoreCase(status))
			{
				String sql;
				sql="select quote_no from position_master where policy_no=? and status='P' and open_cover_int_status in('LINKED') order by quote_no";
				final String args[] = {policyNo};
				policyQuotes = runner.multipleSelection(sql,args);
			}else{
				String sql;
				sql="select quote_no from position_master where quote_no in ("+policyNo+") and status='Y' order by quote_no";
				policyQuotes = runner.multipleSelection(sql);
			}
		logger.debug(EXIT);
		
		return policyQuotes;
	}
	public String getProCommission(final String openNo,final String policyNo,final String pid,final String loginBra) throws BaseException
	{
		logger.info("openCover finalPrint getProCommission method1()");
		logger.debug(ENTER);
			String sql;
			String result;
			sql = "select nvl(PRO_COMMISSION,0) from LOGIN_PRO_COMMISSION where PRODUCT_ID=(select PRODUCT_ID from position_master where policy_no=?) and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and open_cover_no=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=(select PRODUCT_ID from position_master where policy_no=?) and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and open_cover_no=?)";
			final String args[] = {policyNo,policyNo,loginBra,policyNo,policyNo,openNo,policyNo,policyNo,loginBra,
					policyNo,policyNo,openNo};
			result = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getProCommission(final String openNo,final String policyNo,final String pid,final String loginBra,
			final String royal) throws BaseException
	{
		logger.info("openCover finalPrint getProCommission method2()");
		logger.debug(ENTER);
			String sql;
			String result;
			sql = "select nvl(PRO_COMMISSION,0) from LOGIN_PRO_COMMISSION where PRODUCT_ID=(select PRODUCT_ID from position_master where quote_no=?) and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where quote_no=?) and open_cover_no=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=(select PRODUCT_ID from position_master where quote_no=?) and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where quote_no=?) and open_cover_no=?)";
			final String args[] = {policyNo,policyNo,loginBra,policyNo,policyNo,openNo,policyNo,policyNo,
						loginBra,policyNo,policyNo,openNo};
			result = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getProCommission(final String policyNo)throws BaseException
	{
		logger.info("openCover finalPrint getProCommission method3()");
		logger.debug(ENTER);
			String result;
			String check;
			//if(policyNo.indexOf('-')==-1){
			if(isNumeric(policyNo)){
				check = "select sum(PRO_COMMISSION) from position_master where quote_no=?";
			}else{
				 check = "select sum(PRO_COMMISSION) from position_master where policy_no=?";
			}
			final String args[] = {policyNo};
			result = runner.singleSelection(check,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getToleranceDetails(final String qno,final String queryStatus,final String loginBra)throws BaseException
	{
		logger.info("openCover finalPrint getToleranceDetails method3()");
		logger.debug(ENTER);
			String sql;
			String result[][];
			final String args[] = {qno,loginBra};
			if (queryStatus.equalsIgnoreCase(QUOTENO)){
				sql = "SELECT tm.TOLERANCE_NAME,mrd.TOLERANCE_CHARGES,tm.TOLERANCE_VALUE from MARINE_DATA md,TOLERANCE_MASTER tm,MARINE_RESULT_DETAILS mrd where md.TOLERANCE_ID=tm.TOLERANCE_ID and md.APPLICATION_NO=(select APPLICATION_NO from position_master where quote_no=?) and md.APPLICATION_NO=mrd.APPLICATION_NO  and tm.BRANCH_CODE=? order by COMMODITY_ID desc";
			}else{
				sql = "SELECT  tm.TOLERANCE_NAME,mrd.TOLERANCE_CHARGES,tm.TOLERANCE_VALUE from MARINE_DATA md,TOLERANCE_MASTER tm,MARINE_RESULT_DETAILS mrd where md.TOLERANCE_ID=tm.TOLERANCE_ID and md.APPLICATION_NO=(select APPLICATION_NO from position_master where policy_no=?) and md.APPLICATION_NO=mrd.APPLICATION_NO  and tm.BRANCH_CODE=? order by COMMODITY_ID desc";
			}
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public Map checkingvalues(final String policyNo,final String loginCode,final String queryStatus,final String loginBra,
			final String cid,final String option)throws BaseException
	{
		logger.info("openCover finalPrint checkingvalues method3()");
		logger.debug(ENTER);
	
				String querySyntax;
				String pdfDisplayStatus[][];
				rsa.pdf.PDFDisplay pdis;
				pdis = new rsa.pdf.PDFDisplay();
			Map openDetails;
			openDetails = new HashMap();
			if(queryStatus.equalsIgnoreCase(QUOTENO))
			{
				querySyntax = "quote_no='"+policyNo+"'";
				pdfDisplayStatus = pdis.getPreBankOptions(policyNo,loginCode,"draft");
				openDetails.put(PREDTS, getFullDetails(policyNo,querySyntax,loginCode,pdfDisplayStatus,queryStatus,loginBra,cid,option));
			}
			else if(queryStatus.equalsIgnoreCase("QUOTENOSINGLE"))
			{
				querySyntax = "quote_no='"+policyNo+"'";
				pdfDisplayStatus=pdis.getPreBankOptions(policyNo,loginCode,"MultiQuotes");
				openDetails.put(PREDTS, getFullDetails(policyNo,querySyntax,loginCode,pdfDisplayStatus,queryStatus,loginBra,cid,option));
			} 
			else if(queryStatus.equalsIgnoreCase("QUOTENOMULTIPLE") || queryStatus.equalsIgnoreCase("QUOTENOMULTIPLEDRAFT"))
			{
				Map combinedDetails;String quoteNos="";
				combinedDetails = new HashMap();
				String[][] multiQuotes;
				multiQuotes = getPolicyQuotes(policyNo,queryStatus);
				if(multiQuotes.length>0)
				{
					for(int i=0;i<multiQuotes.length;i++)
					{
						querySyntax = "quote_no='"+isNull(multiQuotes[i][0],"0")+"'";
						quoteNos+=",'"+isNull(multiQuotes[i][0],"0")+"'";
						if(queryStatus.equalsIgnoreCase("QUOTENOMULTIPLEDRAFT")){
							pdfDisplayStatus = pdis.getPreBankOptions(isNull(multiQuotes[i][0],"0"),loginCode,"MultiQuotesDisplay");
						}else
						{
							pdfDisplayStatus = pdis.getPreBankOptions(isNull(multiQuotes[i][0],"0"),loginCode,"MultiQuotes");
						}
						final Map premiumdetails = getFullDetails(isNull(multiQuotes[i][0],"0"),querySyntax,loginCode,pdfDisplayStatus,queryStatus,loginBra,cid,option);
						
						combinedDetails.put("QuoteNo"+i,isNull(multiQuotes[i][0],"0"));
						combinedDetails.put(isNull(multiQuotes[i][0],"0"),premiumdetails);
						combinedDetails.put("QuoteGeneratedDate",isNull((String)premiumdetails.get("QuoteGeneratedDate"),""));
						combinedDetails.put("PolicyGeneratedDate",isNull((String)premiumdetails.get("PolicyGeneratedDate"),""));
						combinedDetails.put("CustomerFirstName",isNull((String)premiumdetails.get("CustomerFirstName"),""));
						combinedDetails.put("CustomerLastName",isNull((String)premiumdetails.get("CustomerLastName"),""));
						combinedDetails.put("CustomerAddressOne",isNull((String)premiumdetails.get("CustomerAddressOne"),""));
						combinedDetails.put("CustomerAddressTwo",isNull((String)premiumdetails.get("CustomerAddressTwo"),""));
						combinedDetails.put("CustomerCountry",isNull((String)premiumdetails.get("CustomerCountry"),""));
						combinedDetails.put("CustomerEmirate",isNull((String)premiumdetails.get("CustomerEmirate"),""));
						combinedDetails.put("CustomerPoBox",isNull((String)premiumdetails.get("CustomerPoBox"),"0"));
						combinedDetails.put("CustomerCompanyName",isNull((String)premiumdetails.get("CustomerCompanyName"),""));
						combinedDetails.put("BrokerName",isNull((String)premiumdetails.get("BrokerName"),""));
						combinedDetails.put("Brokeraddress1",isNull((String)premiumdetails.get("Brokeraddress1"),""));
						combinedDetails.put("BrokerAddress2",isNull((String)premiumdetails.get("BrokerAddress2"),""));
						combinedDetails.put("BrokerCountry",isNull((String)premiumdetails.get("BrokerCountry"),""));
						combinedDetails.put("BrokerPoBox",isNull((String)premiumdetails.get("BrokerPoBox"),"0"));
						combinedDetails.put("BrokerEmirate",isNull((String)premiumdetails.get("BrokerEmirate"),""));
						combinedDetails.put("BrokerCommission",isNull((String)premiumdetails.get("BrokerCommission"),"0"));
						combinedDetails.put("MinPremiumAmount",isNull((String)premiumdetails.get("MinPremiumAmount"),"0"));
						combinedDetails.put("LoginID",isNull((String)premiumdetails.get("LoginID"),""));
						combinedDetails.put("LoginAgencyCode",isNull((String)premiumdetails.get("LoginAgencyCode"),""));
						combinedDetails.put("LoginOaCode",isNull((String)premiumdetails.get("LoginOaCode"),""));
						combinedDetails.put("BrokerOaCode",isNull((String)premiumdetails.get("BrokerOaCode"),""));
						combinedDetails.put("BrokerAgencyCode",isNull((String)premiumdetails.get("BrokerAgencyCode"),""));
						combinedDetails.put("BrokerCompany",isNull((String)premiumdetails.get("BrokerCompany"),""));
						combinedDetails.put("paymentMode",isNull((String)premiumdetails.get("paymentMode"),""));
						combinedDetails.put("debitCustomerId",isNull((String)premiumdetails.get("debitCustomerId"),""));
						combinedDetails.put("ocMulCurrId",isNull((String)premiumdetails.get("ocMulCurrId"),""));
						combinedDetails.put("ocMulCurrValue",isNull((String)premiumdetails.get("ocMulCurrValue"),""));
					}
					combinedDetails.put("finalCounts",Integer.toString(multiQuotes.length));
					combinedDetails.put(CKPOLICY,"DATAS");
					quoteNos=quoteNos.length()>0?quoteNos.substring(1,quoteNos.length()):quoteNos;
					combinedDetails.put("multiQuoteInfo", getMultipleQuoteInfo(loginBra, quoteNos));
					combinedDetails.put("multiQuotePremiumInfo", getMultipleQuotePremiumInfo(quoteNos));
				}else{
					combinedDetails.put(CKPOLICY,"NODATAS");
				}
				openDetails.put(PREDTS, combinedDetails);
			}else{
				querySyntax = "policy_no='"+policyNo+"'";
				pdfDisplayStatus = pdis.getPreBankOptions(policyNo,loginCode,"policy");
				openDetails.put(PREDTS, getFullDetails(policyNo,querySyntax,loginCode,pdfDisplayStatus,queryStatus,loginBra,cid,option));
			}
		logger.debug(EXIT);
		
		return (Map)openDetails.get(PREDTS);
	}
	public String[][] getStartingPlace(final String countryId, final String queryStatus, final String cid)throws BaseException
	{
		logger.info("openCover finalPrint getStartingPlace method3()");
		logger.debug(ENTER);
			String[][] result;
			final String args[] = {cid,cid,countryId};
			String sql;
			if(queryStatus.equalsIgnoreCase("Country")){
				sql = "select initcap(cm.country_name),cm.COUNTRY_ID  from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?";
			}else if(queryStatus.equalsIgnoreCase("StartingPlace"))	{
				sql = "select initcap(cm.starting_place),nvl(cm.SP_WARRANTIES_CONDITIONS,'0') from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?";
			}else{
				sql = "select initcap(cm.ENDING_PLACE),nvl(cm.EP_WARRANTIES_CONDITIONS,'0') from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?";
			}	
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public Map getFullDetails(final String policyNo,final String querySyntax,final String loginCode,final String[][] pdfDisplayStatus,
			final String queryStatus,final String loginBra,final String cid,final String option)throws BaseException
	{
		logger.info("openCover finalPrint getFullDetails method3()");
		logger.debug(ENTER);
			int finalCount=1;
	        String returnval[][];
	        String extraCoverId="";
	        String openCoverNo="";
	        String destWarranties="";
	        String startWarranties="";
	        String transPortId="";
  			HashMap premiumdetails;
  			premiumdetails = new HashMap();
			Map commoditydetails;
			commoditydetails = new HashMap();
			
				String qry;
				qry="select cm.cover_name as CoverName,  md.mode_of_transport as TransportID,initcap(trim(md.warehouse_to_warehouse)) " +
        		"as WarehouseOption,initcap(md.transit_from) as TransitFrom,initcap(md.final_destination) " +
        		"as FinalDestination,to_char(md.inception_date,'dd-MM-yyyy') as " +
        		"PolicyStartDate,md.cover_id as CoverId,md.extra_cover_id as " +
        		"ExtraCoverId,mrd.commodity_id as CommodityID,mrd.sum_insured as " +
        		"CommoditySumInsured,(mrd.description) as CommodityDescription," +
        		"ecm.extra_cover_name as ExtraCoverName," +
        		"mt.transport_description as TransportName,(com.commodity_name) as " +
        		"CommodityName,md.currency_name as CurrencyName,md.exchange_rate as " +
        		"ExchangeRate,md.no_of_items as NoOfItems,to_char(md.inception_date,'dd-MM-yyyy')" +
        		" as policyDay,initcap(md.AC_EXECUTIVE_NAME) as ACExecutiveName," +
        		"stm.sale_term_value as SaleTermValue,stm.sale_term_name as SaleTermName," +
        		"md.transit_from_country_id as TransitFromCountryId," +
        		"md.final_destination_country_id as FinalDestinationCountryId," +
        		"com.exclusion_id as ExclusionId,com.warranty_id as WarrantyId," +
        		"mrd.suminsuredlocal as SumInsuredLocal,pm.quote_no as QuoteNo," +
        		"pm.policy_no as PolicyNo,pm.application_no as ApplicationNo," +
        		"pm.customer_id as CustomerId,mpd.carrier_name as CarrierName," +
        		"mpd.BL_AWB_NO as BlAwbNo,to_char(mpd.BL_AWB_DATE,'dd-MM-yyyy') as BlAwbDate,mpd.lc_number " +
        		"as LcNumber,to_char(mpd.lc_date,'dd-MM-yyyy') as LcDate,initcap(mpd.bank_name) as BankName," +
        		"initcap(mpd.settling_agent_name) as SettlingAgentNameOthers,nvl(mpd.settling_agent_id,'0')  " +
        		"as SettlingAgentId,initcap(sam.settling_agent_name) as SettlingAgentName," +
        		"initcap(sam.address1) as SettlingAgentAddressOne,initcap(sam.address2) as " +
        		"SettlingAgentAddressTwo,sam.telephone_no as SettlingAgentTelephoneNo," +
        		"sam.fax_no as SettlingAgentFaxNo,initcap(pi.first_name) as CustomerFirstName," +
        		"initcap(pi.last_name) as CustomerLastName,initcap(pi.address1) as CustomerAddressOne," +
        		"initcap(pi.address2) as CustomerAddressTwo,pi.telephone as CustomerTelephone," +
        		"initcap(pi.country) as CustomerCountry,initcap(nvl(pi.city,pi.emirate)) as CustomerEmirate," +
        		"initcap(bcm.contact_person) as BrokerName,initcap(bcm.address1) as Brokeraddress1," +
        		"initcap(bcm.address2) as BrokerAddress2,initcap(bcm.city) as BrokerCity,initcap(bcm.country) as " +
        		"BrokerCountry,bcm.phone as BrokerPhone,bcm.pobox as BrokerPoBox," +
        		"initcap(nvl(bcm.city,bcm.emirate)) as BrokerEmirate,lud.commission as BrokerCommission," +
        		"lud.min_premium_amount as MinPremiumAmount,lm.login_id as LoginID," +
        		"lm.agency_code as LoginAgencyCode,lm.oa_code as LoginOaCode," +
        		"lud.oa_code as BrokerOaCode,lud.agency_code as BrokerAgencyCode," +
        		"(mrd.package_description),mr.amount_payable,md.premium,md.transit_from_city_id,md.final_destination_city_id,initcap(pm.remarks),initcap(pm.admin_remarks),mrd.fragile,md.excess_premium,pi.pobox,upper(pi.company_name),md.sale_term_id,md.currency_id,md.sea_options,md.transit_from_city_id,md.final_destination_city_id,md.total_sum_insured,to_char(md.inception_date,'dd-MM-yyyy hh24:mi:ss'),initcap(pm.broker_remarks),initcap(bcm.company_name),(mrd.supplier_name)," +
        		"mrd.invoice_number,initcap(trim(md.WARE_TO_WARE_FINAL_DEST)),TRUNC(TO_NUMBER(SUBSTR((md.entry_date+8/24-md.inception_date),1,INSTR(md.entry_date+8/24-md.inception_date,' ')))) as backDay,TRUNC(TO_NUMBER(SUBSTR((md.inception_date-mpd.BL_AWB_DATE),1,INSTR(md.inception_date-mpd.BL_AWB_DATE,' ')))) as BackBillDay,nvl(to_char(pm.inception_date,'dd/MM/yyyy HH24:MI:SS'),'NoDate')," +
        		"nvl(pdf_modify_backdate,'NOTHING'),nvl(pdf_backdate_id,'0'),nvl(md.open_cover_no,'0'),nvl(md.product_id,'0'),bm.bank_name,lm.lc_number,to_char(lm.lc_date,'dd-MM-yyyy'),lm.lc_amount,nvl(md.open_vessel_name,'NO VESSEL NAME PROVIDED'),nvl(md.open_vessel_option,'NO VESSEL OPTION')," +
        		"mrd.rate,nvl(mrd.sea_option_lcl,'0'),nvl(mrd.warehouse_warehouse,'0'),mrd.warrate,mrd.rag,nvl(md.open_bank_id,'0'),nvl(md.open_lc_id,'0'),lm.usertype,nvl(mrd.TOLERANCE_CHARGES,'0'),nvl(pm.APPLICATION_ID,'1'),nvl(ADMIN_REFERRAL_REMARKS,' ')," +
        		"nvl(to_char(pm.EFFECTIVE_DATE,'dd/MM/yyyy HH24:MI:SS'),'NoDate'),nvl(APPROVED_BY,'Nil'),nvl(to_char(pm.CERTIFICATE_DATE,'dd/MM/yyyy HH24:MI:SS'),'NoDate'),nvl(CERTIFICATE_NO,' ')," +
        		"(to_char(pm.entry_date,'dd-mm-yyyy')||' Time: '|| to_char(pm.entry_date,'HH12:MI:SS')),MD.POLICY_FEE,MRD.RATE,MRD.PREMIUM,MRD.WARRATE,MRD.WAR_CHARGES,MD.TOTAL_WAR_CHARGES,(NVL(MRD.SUMINSUREDLOCAL,'0')+NVL(mrd.SALE_TERM_CHARGES,'0')+NVL(MRD.TOLERANCE_CHARGES,'0')),to_char(mpd.SAILING_DATE,'dd/MM/yyyy') as SAILING_DATE, MPD.GHQ_CONTRACT_NO, MPD.GHQ_FMS_CASE_NO, MPD.GHQ_REFERENCE_NO,PM.COMMISSION,PM.PAYMENT_MODE,PM.BASIS_VAL,PM.DEBIT_CUST_ID,PM.OC_MUL_CURR_ID,PM.OC_MUL_CURR_VALUE,CERT_CLAUSES_YN,MD.MARINE_PREMIUM,MD.WAR_PREMIUM,MD.MIN_PRE_YN, pm.PDF_CURRENCY_STATUS, " +
        		" MPD.CONSIGNEE_DET,MPD.SPECIAL_TERM,MPD.ADD_CUST_NAME from marine_data md,open_cover_bank_master bm,open_cover_lc_master lm,marine_result_details mrd," +
        		"cover_master cm,extra_cover_master ecm,mode_of_transport mt," +
        		"commoditymaster com,sale_term_master stm,position_master pm," +
        		"marine_policy_details mpd,settling_agent_master sam,personal_info pi," +
        		"login_master lm,broker_company_master bcm,login_user_details lud,marine_result mr  " +
        		"where pm."+querySyntax+" and lm.login_id=?  and " +
        		"pm.application_no=md.application_no and mpd.quote_no=pm.quote_no and " +
        		"mr.quote_no=pm.quote_no and " +
			"sam.settling_agent_id=(select nvl(settling_agent_id,'0') from marine_policy_details mpds,position_master pms " +
			" where pms.quote_no=mpds.quote_no and pms."+querySyntax+") and  " +
        		
        		"pi.customer_id=pm.customer_id  and lud.oa_code=lm.oa_code and " +
        		"lud.agency_code=bcm.agency_code and md.status='Y' and md.open_lc_id=lm.lc_id and md.open_bank_id=lm.bank_id and md.open_cover_no=lm.open_cover_no and pm.product_id=lud.product_id and md.open_bank_id=bm.bank_id and bm.BELONGING_COUNTRY_ID=? and cm.status='Y' and mt.status='Y' and stm.status='Y' and " +
        		"md.application_no=mrd.application_no and md.cover_id=cm.cover_id " +
        		"and mt.mode_transport_id=md.mode_of_transport and " +
        		"com.commodity_id=mrd.commodity_id and " +
        		"md.extra_cover_id=ecm.extra_cover_id and " +
        		"stm.sale_term_id=md.sale_term_id and com.branch_code=cm.branch_code and com.branch_code=ecm.branch_code and com.branch_code=mt.branch_code and com.branch_code=stm.branch_code "
        		+ " and com.branch_code=(select belonging_branch from branch_master where branch_code=?) "
        		+ " and sam.BELONGING_COUNTRY_ID=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') "
        		+ " and branch_code=(select belonging_branch from branch_master where branch_code=?) "
        		+ " group by commodity_id) and bm.amend_id||'-'||bm.bank_id in (select max(amend_id)||'-'||bank_id from open_cover_bank_master where to_date(effective_date,'dd-mm-yyyy')<=to_date(SYSDATE,'dd-mm-yyyy') and BELONGING_COUNTRY_ID=? and status='Y' group by bank_id) order by bm.bank_name";
					
				final String args[] = {loginCode,cid,loginBra,cid,loginBra,cid};
				returnval = runner.multipleSelection(qry,args);
				logger.info("&&&&&&&&&&&&&===>"+qry);
				logger.info("&&&&&&&&&&&&&===>"+args);
			
		String exclusions="";
		String warrantyIds="";
		String CommodityDE="";
		double totCommSI=0.0;
		double totSumLocal=0.0;
		String amountPay="";
		String commis="";
		String miniPrem="";
		String ExcessPre="";
		String[][] destArray=new String[0][0];
		String[][] startArray=new String[0][0];
		if(returnval.length >0)
		{
			for(int i=0;i<returnval.length;i++)
			{
				transPortId = isNull(returnval[0][1],"");
				premiumdetails.put("TransportID",transPortId);
				modeOfTransport = isNull(returnval[0][1],"");
				warehouse = isNull(returnval[0][2],"NO");
				premiumdetails.put("warehouseasWarehouseOption",warehouse);
				transitFrom = isNull(returnval[0][3],"");
				premiumdetails.put("TransitFrom",transitFrom);
				finalDestination = isNull(returnval[0][4],"");
				premiumdetails.put("FinalDestination",finalDestination);
				premiumdetails.put("PolicyStartDate",isNull(returnval[0][5],""));
				coverId = isNull(returnval[0][6],"0");
				extraCoverId=isNull(returnval[0][7],"0");
				premiumdetails.put("CoverId",coverId);
				premiumdetails.put("ExtraCoverId",extraCoverId);
				premiumdetails.put("CommodityID",returnval[0][8]);
				
				CommodityDE = CommodityDE+","+isNull(returnval[i][10],"");
				
				premiumdetails.put("CoverName",isNull(returnval[0][0],""));
				premiumdetails.put("ExtraCoverName",isNull(returnval[0][11],""));
				premiumdetails.put("TransportName",isNull(returnval[0][12],""));
				premiumdetails.put("CurrencyName",isNull(returnval[0][14],""));
				premiumdetails.put("ExchangeRate",isNull(returnval[0][15],"0"));
				setCurrencyValue(isNull(returnval[0][15],"0"));
				premiumdetails.put("NoOfItems",isNull(returnval[0][16],"0"));
				setNoOfItems(isNull(returnval[0][16],"0"));
				premiumdetails.put("AcExecutiveName",isNull(returnval[0][18],""));
				premiumdetails.put("SaleTermValue",isNull(returnval[0][19],"0"));
				premiumdetails.put("SaleTermName", returnval[0][20]);
				premiumdetails.put("TransitFromCountryId",isNull(returnval[0][21],""));
				setTransitFromCountryId(isNull(returnval[0][21],"0"));
				premiumdetails.put("WareHouseFinalOption",isNull(returnval[0][87],"NO"));
				setWarehouseFinal(isNull(returnval[0][87],"NO"));
				if(!option.equalsIgnoreCase(DEBIT))
				{
					premiumdetails.put("TransitCountryName",getStartingPlace(returnval[0][21],"Country",cid)[0][0]);
					startArray = getStartingPlace(returnval[0][21],"StartingPlace",cid);
				}
				String startPlace="";
				if(startArray.length >0)
				{
					if("NO".equalsIgnoreCase(getWarehouse().trim()) || " NO".equalsIgnoreCase(getWarehouse().trim()) ||  "NO ".equalsIgnoreCase(getWarehouse().trim()) || " NO ".equalsIgnoreCase(getWarehouse().trim()) || "NO".equalsIgnoreCase(getWarehouse()))
					{
						startPlace="Any Port";
						startWarranties=startArray[0][1];
					}
					else if(YES.equalsIgnoreCase(getWarehouse().trim()) || " YES".equalsIgnoreCase(getWarehouse().trim()) ||  "YES ".equalsIgnoreCase(getWarehouse().trim()) || " YES ".equalsIgnoreCase(getWarehouse().trim()) || YES.equalsIgnoreCase(getWarehouse()))
					{
						startPlace="Warehouse";
						startWarranties="0";
					}
					else
					{
						startPlace=startArray[0][0];
						startWarranties=startArray[0][1];
					}
				}
				premiumdetails.put("TransitStartingPlace",startPlace);
				premiumdetails.put("FinalDestinationCountryId",isNull(returnval[0][22],"0"));
				setFinalDestinationCountryId(isNull(returnval[0][22],"0"));
				if(!option.equalsIgnoreCase(DEBIT))
				{
					premiumdetails.put("FinalDestinationCountryName",getStartingPlace(returnval[0][22],"Country",cid)[0][0]);
					destArray=getStartingPlace(returnval[0][22],"EndingPlace",cid);
				}
				String destPlace="";
				if(destArray.length >0)
				{
					if("NO".equalsIgnoreCase(getWarehouseFinal().trim()) || " NO".equalsIgnoreCase(getWarehouseFinal().trim()) ||  "NO ".equalsIgnoreCase(getWarehouseFinal().trim()) || " NO ".equalsIgnoreCase(getWarehouseFinal().trim()) || "NO".equalsIgnoreCase(getWarehouseFinal()))
					{
						destPlace="Any Port";
						destWarranties=destArray[0][1];
					}
					else if(YES.equalsIgnoreCase(getWarehouseFinal().trim()) || " YES".equalsIgnoreCase(getWarehouseFinal().trim()) ||  "YES ".equalsIgnoreCase(getWarehouseFinal().trim()) || " YES ".equalsIgnoreCase(getWarehouseFinal().trim()) || YES.equalsIgnoreCase(getWarehouseFinal()))
					{
						destPlace="Warehouse";
						destWarranties="0";
					}
					else
					{
						destPlace=destArray[0][0];
						destWarranties=destArray[0][1];
						if("0".equalsIgnoreCase(isNull(destWarranties,"0")))
						{
							premiumdetails.put("destinationCountryWarranties","NO");
						}
						else
						{
							premiumdetails.put("destinationCountryWarranties",YES);
						}
					}
				}
				premiumdetails.put("FinalStartingPlace",destPlace);
	
				premiumdetails.put("QuoteNo",isNull(returnval[0][26],""));
				setQuoteId(isNull(returnval[0][26],"0"));
				premiumdetails.put("PolicyNo",isNull(returnval[0][27],""));
				premiumdetails.put("ApplicationNo",isNull(returnval[0][28],""));
				premiumdetails.put("CustomerId",isNull(returnval[0][29],""));
				premiumdetails.put("CarrierName",isNull(returnval[0][30],""));
				premiumdetails.put("BlAwbNo",isNull(returnval[0][31],""));
				premiumdetails.put("BlAwbDate",isNull(returnval[0][32],""));
				//For Open Cover Policy Starts
				premiumdetails.put("VoyageNumber",isNull(returnval[0][33],""));
				premiumdetails.put("SailingDate",isNull(returnval[0][34],""));
				premiumdetails.put("PortOfTranshipMent",isNull(returnval[0][35],""));
				//For Open Cover Policy Ends
				premiumdetails.put("SettlingAgentNameOthers",isNull(returnval[0][36],""));
				premiumdetails.put("SettlingAgentId",isNull(returnval[0][37],""));
				premiumdetails.put("SettlingAgentName",isNull(returnval[0][38],""));
				premiumdetails.put("SettlingAgentAddressOne",isNull(returnval[0][39],""));
				premiumdetails.put("SettlingAgentAddressTwo",isNull(returnval[0][40],""));
				premiumdetails.put("SettlingAgentTelephoneNo",isNull(returnval[0][41],""));
				premiumdetails.put("SettlingAgentFaxNo",isNull(returnval[0][42],""));
				premiumdetails.put("CustomerFirstName",isNull(returnval[0][43],""));
				premiumdetails.put("CustomerLastName",isNull(returnval[0][44],""));
				premiumdetails.put("CustomerAddressOne",isNull(returnval[0][45],""));
				premiumdetails.put("CustomerAddressTwo",isNull(returnval[0][46],""));
				premiumdetails.put("CustomerTelephone",isNull(returnval[0][47],""));
				premiumdetails.put("CustomerCountry",isNull(returnval[0][48],""));
				premiumdetails.put("CustomerEmirate",isNull(returnval[0][49],""));
				premiumdetails.put("BrokerName",getLoginUserDetails(loginCode));
				premiumdetails.put("Brokeraddress1",isNull(returnval[0][51],""));
				premiumdetails.put("BrokerAddress2",isNull(returnval[0][52],""));
				premiumdetails.put("BrokerCity",isNull(returnval[0][53],""));
				premiumdetails.put("BrokerCountry",isNull(returnval[0][54],""));
				premiumdetails.put("BrokerPhone",isNull(returnval[0][55],""));
				premiumdetails.put("BrokerPoBox",isNull(returnval[0][56],""));
				premiumdetails.put("BrokerEmirate",isNull(returnval[0][57],""));
				premiumdetails.put("MinPremiumAmount",isNull(returnval[0][59],""));
				premiumdetails.put("LoginID",returnval[0][60]);
				premiumdetails.put("LoginAgencyCode",returnval[0][61]);
				premiumdetails.put("LoginOaCode",returnval[0][62]);
				premiumdetails.put("BrokerOaCode",returnval[0][63]);
				premiumdetails.put("BrokerAgencyCode",returnval[0][64]);
				premiumdetails.put("AmountPayable",isNull(returnval[0][66],"0"));
				premiumdetails.put("Premium",isNull(returnval[0][67],"0"));
				premiumdetails.put("Remarks",isNull(returnval[0][70],""));
				premiumdetails.put("AdminRemarks",(returnval[0][71]==null?"":(returnval[0][71]+"\n"))+returnval[0][111]);

				premiumdetails.put("ExcessPremium",isNull(returnval[0][73],"0"));
				premiumdetails.put("CustomerPoBox",returnval[0][74]);
				premiumdetails.put("CustomerCompanyName",isNull(returnval[0][75],""));
				premiumdetails.put("BrokerRemarks",returnval[0][83]==null?"":returnval[0][83]);
				premiumdetails.put("BrokerCompany",isNull(returnval[0][84],""));
				premiumdetails.put("PDFCurrencyStatus",isNull(returnval[0][138],""));
				String seaCheckOptions;
				seaCheckOptions=isNull(returnval[0][78],"0");

				premiumdetails.put("seaCheckOptions",seaCheckOptions);
				
				openCoverNo=isNull(returnval[0][93],"0");
			
				String[][] openFreeText = new String[0][0];
				String[][] openMaster = new String[0][0];
				if(!option.equalsIgnoreCase(DEBIT))
				{
					openFreeText=getOpenCoverRelatedDatas(openCoverNo,transPortId,coverId,"OPENFREETEXT",querySyntax);
				}
				openMaster=getOpenCoverRelatedDatas(openCoverNo,transPortId,coverId,"OPENMASTER",querySyntax);

				int backDaysAllowed=0;
				String coInsCompany="", freeTextYN="";

			if(openMaster.length > 0)
			{
				backDaysAllowed=Integer.parseInt(openMaster[0][9]==null?"0":openMaster[0][9]);
	
				premiumdetails.put("openBackDaysAllowed",Integer.toString(backDaysAllowed));
				commis=StringUtils.isBlank(openMaster[0][10])?"0":openMaster[0][10];
				//commis=StringUtils.isBlank(openMaster[0][10].trim())?"0":openMaster[0][10];
				//here RSA Issuer
					/*String rsaissuer;
					rsaissuer = returnval[0][110]== null?"1":returnval[0][110];
					if(!rsaissuer.equalsIgnoreCase("1"))
					{
						double rsaDisCom;
						//rsaDisCom = getRSAIssuerDiscountCommission(loginBra);
						com.maan.services.policyInfo pol;
						pol = new com.maan.services.policyInfo();
						rsaDisCom = pol.getRSAIssuerCommission(loginCode,rsaissuer,"11",policyNo);
//						commis = ""+(Double.parseDouble(commis)-rsaDisCom);
						commis = Double.toString(rsaDisCom);
						
					}*/
				premiumdetails.put("BrokerCommission",commis);
				premiumdetails.put("openRsaSharedPercentage",isNull(openMaster[0][13],"0"));
				coInsCompany=isNull(openMaster[0][11],"0");
				premiumdetails.put("openCoverMississippiNo",isNull(openMaster[0][17],""));
				premiumdetails.put("openCoverCustomer",getOpenCoverCustomer(isNull(openMaster[0][5],"0")));
				freeTextYN=isNull(openMaster[0][16],"");
			}
			
			if("0".equalsIgnoreCase(coInsCompany) || "".equalsIgnoreCase(coInsCompany))
			{
				premiumdetails.put("openInsuranceCompanines",new String[0][0]);
			}
			else if(!option.equalsIgnoreCase(DEBIT))
			{
				String[][] insureCompany;
				insureCompany = getOpenCoverRelatedDatas(openCoverNo,transPortId,coverId,"OPENSHAREDPERCENTAGE",querySyntax);
				premiumdetails.put("openInsuranceCompanines",insureCompany);
			}
			if(openFreeText.length > 0 && "Y".equalsIgnoreCase(freeTextYN))
			{
				premiumdetails.put("openFreeText",openFreeText);
			}else{
				premiumdetails.put("openFreeText",new String[0][0]);
			}
	
			
			if("1".equalsIgnoreCase(transPortId))
			{
				if("FCL".equalsIgnoreCase(seaCheckOptions))
				{
					seaCheckOptions="101";	
				}
				else if("LCL".equalsIgnoreCase(seaCheckOptions))
				{
					seaCheckOptions="102";	
				}
				else if("BREAK BULK".equalsIgnoreCase(seaCheckOptions))
				{
					seaCheckOptions="103";	
				}
				else if("BULK".equalsIgnoreCase(seaCheckOptions))
				{
					seaCheckOptions="104";	
				}
				else
				{
					seaCheckOptions="0";	
				}

				if("0".equalsIgnoreCase(seaCheckOptions) || "".equalsIgnoreCase(seaCheckOptions))
				{
					premiumdetails.put("openSeaFreeTextOptions",new String[0][0]);
				}
				else if(!option.equalsIgnoreCase(DEBIT))
				{
					String[][] freeText;
					freeText=getOpenCoverRelatedDatas(openCoverNo,transPortId,seaCheckOptions,"OPENFREETEXT",querySyntax);
					premiumdetails.put("openSeaFreeTextOptions",freeText);
				}
			}
			else
			{
				premiumdetails.put("openSeaFreeTextOptions",new String[0][0]);
			}
			premiumdetails.put("BackDays",returnval[0][88]==null?"0":returnval[0][88]);
			
				if(Integer.parseInt(returnval[0][88]==null?"0":returnval[0][88]) > backDaysAllowed || Integer.parseInt(returnval[0][89]==null?"0":returnval[0][89]) > backDaysAllowed )
				{
					premiumdetails.put("BackDaysOption",YES);
				}
				else
				{
					premiumdetails.put("BackDaysOption","NO");
				}
				premiumdetails.put("PolicyGeneratedDate",isNull(returnval[0][90],""));
				premiumdetails.put("ActualPolicyGeneratedDate",isNull(returnval[0][112],""));
				premiumdetails.put("QuoteGeneratedDate",isNull(returnval[0][116],""));
				premiumdetails.put("APPROVEDBY",isNull(returnval[0][113],""));
				premiumdetails.put("CertificateDate",isNull(returnval[0][114],"")); // Certificate Issue date
				premiumdetails.put("CertificateNo",isNull(returnval[0][115],"")); // Certificate No
			
				premiumdetails.put("PdfSubjectKnownText",isNull(returnval[0][91],NOTHING));
				premiumdetails.put("PdfSubjectKnownId",isNull(returnval[0][92],"0"));
				
				premiumdetails.put("IssuanceFee",returnval[0][117] == null ? "0" : returnval[0][117]);
				premiumdetails.put("totalWarPremium",returnval[0][122] == null ? "0" : returnval[0][122]);
				
				premiumdetails.put("openCoverNo",isNull(returnval[0][93],"0"));
				premiumdetails.put("productId",isNull(returnval[0][94],"0"));
				premiumdetails.put("BankName",isNull(returnval[0][95],"0"));
				premiumdetails.put("LcNumber",isNull(returnval[0][96],"0"));
				premiumdetails.put("LcDate",isNull(returnval[0][97],"0"));
				premiumdetails.put("LcAmount",isNull(returnval[0][98],"0"));
				premiumdetails.put("VesselName",isNull(returnval[0][99],"0"));

				setPremium(isNull(returnval[0][67],"0"));
				setExcessPremium(isNull(returnval[0][73],"0"));
				setSaleTerm(returnval[0][76]);
				setCurrencyId(returnval[0][77]);
				setSeaOptions(isNull(returnval[0][78],"0"));

				setFinalDestinationCityId(isNull(returnval[0][80],"0"));
				setTransitFromCityId(isNull(returnval[0][79],"0"));
				setTotalSumInsured(isNull(returnval[0][81],"0"));
				setPolicyDate(returnval[0][82]);
	
				amountPay = isNull(returnval[0][66],"0");
				miniPrem = isNull(returnval[0][59],"0");
				ExcessPre = isNull(returnval[0][73],"0");
				
				totCommSI = Double.parseDouble(isNull(returnval[i][9],"0"))+totCommSI;
				totSumLocal = ((Double.parseDouble(isNull(returnval[i][25],"0"))*(Double.parseDouble(isNull(returnval[i][19],"0"))/100))
						+(Double.parseDouble(isNull(returnval[i][25],"0"))))+Double.parseDouble(isNull(returnval[i][109],"0"))+totSumLocal;
				commoditydetails.put("CommoditySumInsured"+(finalCount),returnval[i][9]);
	
				commoditydetails.put("CommodityDescription"+(finalCount),returnval[i][10]);
				String pkgDes = isNull(returnval[i][65],"0");
				pkgDes = pkgDes.replaceAll("''","'");
				pkgDes = pkgDes.replaceAll("''","'");
				pkgDes = pkgDes.replaceAll("''","'");
				pkgDes = pkgDes.replaceAll("''","'");
				
				commoditydetails.put("PackageDescription_arr"+(finalCount),pkgDes);
				commoditydetails.put("CommodityName_arr"+(finalCount),isNull(returnval[i][13],""));
				commoditydetails.put("SaleTermName_arr"+(finalCount),isNull(returnval[i][20],""));
				commoditydetails.put("ExclusionId_arr"+(finalCount),isNull(returnval[i][23],"0"));
				commoditydetails.put("Supplier_arr"+(finalCount),returnval[i][85]==null?"":returnval[i][85]);
				commoditydetails.put("Invoice_arr"+(finalCount),returnval[i][86]==null?"":returnval[i][86]);
				commoditydetails.put("rate"+(finalCount),returnval[i][101]==null?"":returnval[i][101]);
				commoditydetails.put("seaValue"+(finalCount),returnval[i][102]==null?"":returnval[i][102]);
				commoditydetails.put("wareHouseValue"+(finalCount),returnval[i][103]==null?"":returnval[i][103]);
				commoditydetails.put("warRate"+(finalCount),returnval[i][104]==null?"":returnval[i][104]);

				commoditydetails.put("rag"+(finalCount),returnval[i][105]==null?"":returnval[i][105]);
				exclusions=exclusions+","+returnval[i][23];
				commoditydetails.put("WarrantyId_arr"+(finalCount),isNull(returnval[i][24],"0"));
				warrantyIds=warrantyIds+","+returnval[i][24];
				commoditydetails.put("SumInsuredLocal_arr"+(finalCount),isNull(returnval[i][25],"0"));
		
				if("on".equalsIgnoreCase(returnval[i][72]))	{
					commoditydetails.put("FragileMessage","displayFragileMessage");
				}
				//Added by sathish Start
				commoditydetails.put("PremiumRate" + (finalCount),returnval[i][118]);
				commoditydetails.put("PremiumSingle" + (finalCount),returnval[i][119]);
				commoditydetails.put("WarRate" + (finalCount),returnval[i][120]);
				commoditydetails.put("WarPremium" + (finalCount),returnval[i][121]);
				commoditydetails.put("SumInsuredLocal" + (finalCount),returnval[0][123] == null ? "0" : returnval[0][123]);
				//Added by sathish End
				premiumdetails.put("sailDate",isNull(returnval[0][124],""));
				premiumdetails.put("currencyId",isNull(returnval[0][77],""));
				//Begin GHQ info 
				premiumdetails.put("contractNo",isNull(returnval[0][125],""));
				premiumdetails.put("fmsCaseNo",isNull(returnval[0][126],""));
				premiumdetails.put("refNo",isNull(returnval[0][127],""));
				//End GHQ info
				premiumdetails.put("commissionAmt",isNull(returnval[0][128],""));
				premiumdetails.put("paymentMode",isNull(returnval[0][129],""));
				premiumdetails.put("basisVal",isNull(returnval[0][130],""));
				premiumdetails.put("debitCustomerId",isNull(returnval[0][131],""));
				premiumdetails.put("ocMulCurrId",isNull(returnval[0][132],""));
				premiumdetails.put("ocMulCurrValue",isNull(returnval[0][133],""));
				premiumdetails.put("certClausesYN",isNull(returnval[0][134],""));
				premiumdetails.put("MARINE_PREMIUM",isNull(returnval[0][135],""));
				premiumdetails.put("WAR_PREMIUM",isNull(returnval[0][136],""));
				premiumdetails.put("MIN_PRE_YN",isNull(returnval[0][137],""));
				//premiumdetails.put("NetPremium",isNull(returnval[0][138],""));
				premiumdetails.put("CONSIGNEE_DET",isNull(returnval[0][139],""));
				premiumdetails.put("SPECIAL_TERM",isNull(returnval[0][140],""));
				premiumdetails.put("ADD_CUST_NAME",isNull(returnval[0][141],""));
				
				finalCount=finalCount+1;
			}
			
			String qry1;
			qry1 = "select round(sum(mrd.premium),2) ,round(sum(mrd.war_charges),2) from marine_result_details mrd,position_master pm where mrd.application_no=pm.application_no and pm.policy_no=?";
			final String args3[] = {policyNo};
			String[][] returnval1;
			returnval1 = runner.multipleSelection(qry1,args3);
			System.out.println("royal test one off bankName from final pritn java..."+qry1);
			
			for (int i = 0; i < returnval1.length; i++) {
				premiumdetails.put("finalmarinepremium",isNull(returnval1[0][0],""));
				premiumdetails.put("finalwarpremium",isNull(returnval1[0][1],""));
			}
			
			premiumdetails.put("CommodityDE",CommodityDE.substring(1,CommodityDE.length()));
			
			String concatClausesIds="";
			String exClausesIds="";
			String extraClausesIds="";
			String warClausesIds="";
			
		if(!option.equalsIgnoreCase(DEBIT))
		{
			/*String pdfClauses,pdfexClauses,pdfWars,pdfEx;
			pdfClauses = isNull(pdfDisplayStatus[0][2],NOTHING);
			pdfexClauses = isNull(pdfDisplayStatus[0][5],NOTHING);
			pdfWars = isNull(pdfDisplayStatus[0][3],NOTHING);
			pdfEx = isNull(pdfDisplayStatus[0][4],NOTHING);
			
			rsa.pdf.PDFDisplay pdis;
			pdis=new rsa.pdf.PDFDisplay();
			if(NOTHING.equalsIgnoreCase(pdfClauses))
			{
				concatClausesIds="0";
				premiumdetails.put("editedClauses",new String[0][0]);
			}
			else
			{
				String pdfDisplayClauses[][];
				pdfDisplayClauses = pdis.makeTwoDimArray(pdfClauses);
				for(int i=0;i<pdfDisplayClauses.length;i++)
				{
					concatClausesIds=concatClausesIds+","+(pdfDisplayClauses[i][0]==null?"0":pdfDisplayClauses[i][0]);
				}
				concatClausesIds=concatClausesIds.substring(1,concatClausesIds.length());	
				premiumdetails.put("editedClauses",pdfDisplayClauses);
			}

			if(NOTHING.equalsIgnoreCase(pdfexClauses))
			{
				extraClausesIds="0";
				premiumdetails.put("editedExtraClauses",new String[0][0]);
			}
			else
			{
				String pdfExtraClauses[][];
				pdfExtraClauses = pdis.makeTwoDimArray(pdfexClauses);
				for(int i=0;i<pdfExtraClauses.length;i++)
				{
					extraClausesIds=extraClausesIds+","+(pdfExtraClauses[i][0]==null?"0":pdfExtraClauses[i][0]);
				}
				extraClausesIds=extraClausesIds.substring(1,extraClausesIds.length());	
				premiumdetails.put("editedExtraClauses",pdfExtraClauses);
			}
			if(NOTHING.equalsIgnoreCase(pdfWars))
			{
				warClausesIds="0";
				premiumdetails.put("editedWarClauses", new String[0][0]);
			}
			else
			{
				String pdfWarranties[][];
				pdfWarranties = pdis.makeTwoDimArray(pdfWars);
				for(int i=0;i<pdfWarranties.length;i++)
				{
					warClausesIds=warClausesIds+","+(pdfWarranties[i][0]==null?"0":pdfWarranties[i][0]);
				}
				warClausesIds=warClausesIds.substring(1,warClausesIds.length());	
				premiumdetails.put("editedWarClauses",pdfWarranties);
			}
			if(NOTHING.equalsIgnoreCase(pdfEx))
			{
				exClausesIds="0";
				premiumdetails.put("editedExClauses",new String[0][0]);
			}
			else
			{
				String pdfExclusions[][];
				pdfExclusions=pdis.makeTwoDimArray(pdfEx);
				for(int i=0;i<pdfExclusions.length;i++)
				{
					exClausesIds=exClausesIds+","+(pdfExclusions[i][0]==null?"0":pdfExclusions[i][0]);
				}
				exClausesIds=exClausesIds.substring(1,exClausesIds.length());	
				premiumdetails.put("editedExClauses",pdfExclusions);
			}
			exclusions=exclusions.substring(1,exclusions.length());
			warrantyIds=warrantyIds+","+startWarranties;
			warrantyIds=warrantyIds+","+destWarranties;
			warrantyIds=warrantyIds.substring(1,warrantyIds.length());
				String sqll;
			  //sqll="select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.exclusion_id not in("+exClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and (occms.exclusion_id not in("+exClausesIds+") and occms.amend_id in( select max(occmsss.amend_id) from open_cover_exclusions occmsss,open_cover_position_master ocpmsss where ocpmsss.open_cover_no=? and ocpmsss.proposal_no=occmsss.proposal_no and to_date(occmsss.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')))  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				String coverDesc = "", coverDescQry = "";
				coverDescQry = "select distinct description from cover_master where branch_code = " + loginBra + " and cover_id = " + coverId + "";
				coverDesc = runner.singleSelection(coverDescQry);
				String quote = returnval[0][26]==null?"":returnval[0][26];
				System.out.println("Quote No Test:"+quote);
				sqll="SELECT DISTINCT occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_exclusions occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM  WHERE ocpmss.open_cover_no =? AND occmss.exclusion_id NOT IN ("+exClausesIds+") AND CM.BRANCH_CODE="+loginBra+" AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO='"+quote+"' AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND ocpmss.proposal_no = occmss.proposal_no AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_exclusions WHERE   proposal_no =ocpmss.proposal_no AND TO_DATE (effective_date,'dd-mm-YYYY') <=TO_DATE (SYSDATE,'dd-mm-YYYY'))";
				String[][] resultExclusion;
				final String args1[] = {openCoverNo};
				resultExclusion=runner.multipleSelection(sqll,args1);
				final String args1[] = {openCoverNo,openCoverNo,openCoverNo};
				resultExclusion=runner.multipleSelection(sqll,args1);
				commoditydetails.put("exclusionsDesc",resultExclusion);

				String sqll1;
				//sqll1="select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.warranty_id not in("+warClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and (occms.warranty_id not in("+warClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) ) and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				sqll1="SELECT DISTINCT  occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_warranties occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM WHERE ocpmss.open_cover_no =? AND occmss.warranty_id NOT IN ("+warClausesIds+") AND ocpmss.proposal_no = occmss.proposal_no AND CM.BRANCH_CODE ="+loginBra+" AND CM.COMMODITY_ID IN (SELECT   MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD, POSITION_MASTER PM WHERE PM.QUOTE_NO='"+quote+"' AND PM.APPLICATION_NO = MRD.APPLICATION_NO AND MRD.AMEND_ID =(SELECT MAX (AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MRD.APPLICATION_NO)) AND OCCMSS.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_warranties WHERE proposal_no=ocpmss.proposal_no AND TO_DATE ( effective_date,'dd-mm-YYYY') <=TO_DATE ( SYSDATE,'dd-mm-YYYY'))";
				String[][] resultWarranty;
				resultWarranty = runner.multipleSelection(sqll1,args1);
				commoditydetails.put("warrantyDesc",resultWarranty);
	
				if(transPortId.equalsIgnoreCase("3"))
				{
					extraCoverId="0";
				}
				if(extraCoverId.equalsIgnoreCase("0"))
				{
					String sql23;
			      //sql23="select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.clauses_id not in("+concatClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and occms.cover_id=? and occmss.extra_cover_id='0' and (occms.clauses_id not in("+concatClausesIds+") and occms.amend_id in(select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
					System.out.println("=======PDF>");
					sql23="select distinct occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.clauses_id not in("+concatClausesIds+") AND CM.BRANCH_CODE="+loginBra+" AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO='"+quote+"' AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = ( select max(amend_id) from open_cover_clauses where cover_id='"+coverId+"' and extra_cover_id='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
					String[][] resultClauses;
					final String args2[] = {openCoverNo,coverId};
					resultClauses = runner.multipleSelection(sql23,args2);
					final String args2[] = {openCoverNo,coverId,openCoverNo,coverId,openCoverNo,coverId};
					resultClauses = runner.multipleSelection(sql23,args2);
					commoditydetails.put("clausesDesc",resultClauses);
					commoditydetails.put("extraClausesDesc",new String[0][0]);
				}
				else
				{
					String sql12;
			      //sql12="select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0'  and occmss.clauses_id not in("+concatClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and occms.cover_id=? and occmss.extra_cover_id='0' and (occms.clauses_id not in("+concatClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
					sql12="select distinct occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.clauses_id not in("+concatClausesIds+") AND CM.BRANCH_CODE="+loginBra+" AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO='"+quote+"' AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = ( select max(amend_id) from open_cover_clauses where cover_id='"+coverId+"' and extra_cover_id='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
					String[][] resultClauses;
					final String args2[] = {openCoverNo,coverId};
					resultClauses=runner.multipleSelection(sql12,args2);
					final String args2[] = {openCoverNo,coverId,openCoverNo,coverId,openCoverNo,coverId};
					resultClauses=runner.multipleSelection(sql12,args2);
					commoditydetails.put("clausesDesc",resultClauses);
					String sql22;
				  //sql22="select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id=?)  and occmss.clauses_id not in("+extraClausesIds+") and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no=?  and (occms.clauses_id not in("+extraClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id=?) and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))  order by occmss.clauses_id";
					sql22="select distinct occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id!='0' and occmss.clauses_id not in("+extraClausesIds+") AND CM.BRANCH_CODE="+loginBra+" AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO='"+quote+"' AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = (select max(amend_id) from open_cover_clauses where cover_id='"+coverId+"' and extra_cover_id!='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by occmss.clauses_id";
					String[][] resultExtraClauses;
					final String args3[] =  {openCoverNo,coverId};
					resultExtraClauses=runner.multipleSelection(sql22,args3);
					final String args3[] =  {openCoverNo,extraCoverId,openCoverNo,openCoverNo,extraCoverId};
					resultExtraClauses=runner.multipleSelection(sql22,args3);
					commoditydetails.put("extraClausesDesc",resultExtraClauses);
				}*/
			Map<String, Object> conditions=new policyInfo().getConditions((String)premiumdetails.get("ApplicationNo"), loginBra);
			commoditydetails.putAll(conditions);
		   }
			finalCount=finalCount-1;
			commoditydetails.put("finalCount",Integer.toString(finalCount));
			commoditydetails.put("TotalCommoditySI",Double.toString(totCommSI));
			commoditydetails.put("TotalSILocal",Double.toString(totSumLocal));
			premiumdetails.put("AmountDetails",getFinalTotal(amountPay,commis,miniPrem,ExcessPre));
			premiumdetails.put("commoditydetails",commoditydetails);
			premiumdetails.put(CKPOLICY,"DATAS");

			if(queryStatus.equalsIgnoreCase(QUOTENO))
			{
				insertDraftTrackingDetails(policyNo);
			}
		}
		else
		{
			premiumdetails.put("commoditydetails",commoditydetails);
			premiumdetails.put(CKPOLICY,"NODATAS");
		}
		logger.debug(EXIT);
		
		return premiumdetails;
	}
	public String[][] getDirectCustomerCertificateStatus(final String opneCoverNo,final String policyNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getDirectCustomerCertificateStatus method()");
		logger.debug(ENTER);
			String sql;
			sql = "select nvl(CUSTOMER_CUSTOMERDEBIT,'N') from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=?";
			String status;
			String result[][] = new String[0][0];
			final String args[] = {opneCoverNo};
			status = runner.singleSelection(sql,args);
			if(status.equalsIgnoreCase("Y")){
				String check;
				//if(policyNo.indexOf('-')==-1){
				if(isNumeric(policyNo)){
					check = "pos.quote_no=?";
				}else{
					check = "pos.policy_no=?";
				}
				String detSql;
				detSql = "select nvl(mp.BL_AWB_NO,' '),nvl(pos.premium,'0')+nvl(pos.excess_premium,'0'),to_char(md.INCEPTION_DATE,'dd-mm-yyyy'),nvl(substr(pos.policy_no,9,16),'0'),nvl(per.first_name,per.company_name),per.pobox,nvl(per.city,per.emirate),per.country from MARINE_POLICY_DETAILS mp,position_master pos, MARINE_DATA md,personal_info per where "+check+" and pos.quote_no=mp.quote_no and md.application_no = pos.application_no and per.customer_id in(select CUSTOMER_ID from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=?) and amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')))";
				final String args1[] = {policyNo,opneCoverNo,opneCoverNo};
				result = runner.multipleSelection(detSql,args1);
			}
		logger.debug(EXIT);
		
		return result;
	}

	public String getMaxsno(final String productTypeId,final String loginBra,final String policyNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getMaxsno method()");
		logger.debug(ENTER);
			String currentNo;
			final String args[] = {policyNo};
			String checksql;
			StringBuffer upSql;
			upSql = new StringBuffer();
			//if(policyNo.indexOf('-')==-1){
			if(isNumeric(policyNo)){
				checksql = "select nvl(certificate_slno,'0') from POSITION_MASTER where quote_no=?";
				upSql.append("update POSITION_MASTER set certificate_slno=? where quote_no=?");
			}else{
				checksql = "select nvl(certificate_slno,'0') from POSITION_MASTER where policy_no=?";
				upSql.append("update POSITION_MASTER set certificate_slno=? where policy_no=?");
			}
			currentNo = runner.singleSelection(checksql,args);
			if(currentNo.length()<=0||"0".equals(currentNo))
			{
				currentNo = getMaxCertificateNo(loginBra,"CERTIFICATE_SLNO_TYPE_ID");
				final String args2[] = {currentNo,policyNo};
				runner.multipleUpdation(upSql.toString(),args2);
			}
		logger.debug(EXIT);
		
		return currentNo;
	}
	//For RSA ISSUER
	public String getIssuerAppId(final String queryStatus,final String qno)throws BaseException
	{
		logger.info("OpenCover finalPrint getProCommission method()");
		logger.debug(ENTER);
			String result;
			StringBuffer check;
			check = new StringBuffer();
			if (queryStatus.equalsIgnoreCase(QUOTENO)){
				check.append("select distinct nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO=");
				check.append("'"+qno+"'");
			}else if (queryStatus.equalsIgnoreCase("POLICYNO"))
			{
				check.append("select distinct nvl(APPLICATION_ID,'1') from POSITION_MASTER where POLICY_NO=");
				check.append("'"+qno+"'");
			}else if (queryStatus.equalsIgnoreCase("QUOTENOMULTIPLE"))
			{
				check.append("select distinct nvl(APPLICATION_ID,'1') from POSITION_MASTER where POLICY_NO=");
				check.append("'"+qno+"'");
			}else{
				check.append("select distinct nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO in(");
				check.append(qno);
				check.append(')');
			}
			result = runner.singleSelection(check.toString());
		logger.debug(EXIT);
		
		return  result;
	}
	public String getIssuerName(final String qno,final String queryStatus)throws BaseException
	{
		logger.info("OpenCover finalPrint getProCommission method()");
		logger.debug(ENTER);
			String result;
			String result1="";
			result = getIssuerAppId(queryStatus,qno);
			if(result.length()>0&&!result.equalsIgnoreCase("1"))
			{
					String nameSql;
					nameSql = "select nvl(COMPANY_NAME,FIRST_NAME) from PERSONAL_INFO where LOGIN_ID=? and agency_code is not null and oa_code is not null";
					final String args[] = {result};
					result1 = runner.singleSelection(nameSql,args);
			}
		logger.debug(EXIT);
		
		return result1;
	}
	public String[][] getOpenCoverRelatedDatas(final String openCoverNo,final String conveyanceId,final String coverTypeId,
			final String statusQuery,final String querySyntax)throws BaseException{
		logger.info("OpenCover finalPrint getOpenCoverRelatedDatas method()");
		logger.debug(ENTER);
			Map openData;
			openData = new HashMap();
			if("OPENSHAREDPERCENTAGE".equalsIgnoreCase(statusQuery))
			{
				String sql;
				sql="select ocsp.insurance_company_id,nvl(ocsp.share_percentage,'0'),nvl(oci.insurance_company_name,' '),oci.amend_id from open_cover_share_percentage ocsp,open_cover_insurance oci,open_cover_position_master ocpm where ocsp.status='Y'  and ocpm.open_cover_no=? and  ocpm.proposal_no=ocsp.proposal_no and oci.insurance_company_id=ocsp.insurance_company_id and ocsp.amend_id = (select max(ocsps.amend_id) from open_cover_share_percentage ocsps,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsps.proposal_no and to_date(ocsps.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY')) and oci.amend_id||'-'||oci.insurance_company_id in (select max(ocis.amend_id)||'-'||ocis.insurance_company_id from open_cover_insurance ocis  where to_date(ocis.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') group by ocis.insurance_company_id) ";
				final String args[] = {openCoverNo,openCoverNo};
				String[][] openCoverDatas;
				openCoverDatas = runner.multipleSelection(sql,args);
				openData.put(OPENDATA, openCoverDatas);
			}
			else if("OPENFREETEXT".equalsIgnoreCase(statusQuery))
			{
				String sql;
				sql="select ocft.free_text_description,ocft.cover_id from open_cover_free_text ocft,open_cover_position_master ocpm where ocft.status='Y'  and ocpm.open_cover_no =? and  ocpm.proposal_no=ocft.proposal_no and to_date(ocft.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocft.amend_id = (select max(ocfts.amend_id) from open_cover_free_text ocfts,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocfts.proposal_no and to_date(ocfts.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocfts.cover_id=?) and ocft.cover_id=?";
				final String args[] = {openCoverNo,openCoverNo,coverTypeId,coverTypeId};
				String[][] openCoverDatas;
				openCoverDatas=runner.multipleSelection(sql,args);
				openData.put(OPENDATA, openCoverDatas);
			}
			else if("OPENSEAOPTIONS".equalsIgnoreCase(statusQuery))
			{
				String sql;
				sql="select distinct(cm.mode_transport_ID),nvl(ocsd.sea_options,'NO OPTIONS'),ocsd.amend_id from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocsds.mode_transport_id=?) and ocsd.mode_transport_id=?";
				final String args[] = {openCoverNo,openCoverNo,conveyanceId,conveyanceId};
				String[][] openCoverDatas;
				openCoverDatas = runner.multipleSelection(sql,args);
				openData.put(OPENDATA, openCoverDatas);
			}
			else if("OPENDETAILS".equalsIgnoreCase(statusQuery))
			{
				String sql;
				sql="select  ocd.sum_insured_limit,ocd.mode_transport_id,ocd.exchange_id,ocd.currency_id,ocd.exchange_rate,ocd.currency_name,ocd.amend_id from open_cover_detail ocd,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocd.proposal_no and ocd.amend_id in (select max(ocds.amend_id) from open_cover_detail ocds,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocds.proposal_no and to_date (ocds.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY') and ocds.mode_transport_id=?) and ocd.mode_transport_id=?";
				final String args[] = {openCoverNo,openCoverNo,conveyanceId,conveyanceId};
				String[][] openCoverDatas;
				openCoverDatas = runner.multipleSelection(sql,args);
				openData.put(OPENDATA, openCoverDatas);
			}
			else if("OPENMASTER".equalsIgnoreCase(statusQuery))
			{
				String sql;
				sql="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,nvl(pos.DISCOUNT_PREMIUM,ocm.commission),ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.MISSIPPI_OPENCOVER_NO,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm, position_master pos where ocpm.open_cover_no=? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) and ocpm.open_cover_no=pos.MISSIPPI_OPENCOVER_NO and pos."+querySyntax;
				final String args[] = {openCoverNo,openCoverNo};
				String[][] openCoverDatas;
				openCoverDatas = runner.multipleSelection(sql,args);
				openData.put(OPENDATA, openCoverDatas);
			}	
		logger.debug(EXIT);
		
		return (String[][])openData.get(OPENDATA);
	}
	public String[][] getPreCusDetails(final String quoteNo) throws BaseException{
		logger.info("OpenCover finalPrint getPreCusDetails method()");
		logger.debug(ENTER);
			String sql;
			/*sql = " select nvl(per.company_name,per.first_name),nvl(per.address1,' '),nvl(per.address2,' '),nvl(per.POBOX,' '),replace(nvl(per.EMIRATE,' '),'select',''),replace(nvl(per.COUNTRY,' '),'select',''),nvl(pos.policy_no,' '),nvl(pos.premium,'0'),nvl(DISCOUNT_Premium,'0'),nvl(customer_DEBIT_NOTE_NO,'0'),to_char(customer_DEBIT_NOTE_DATE,'dd-mm-yyyy'), "
					+ " (nvl(net_Premium,0)) from MARINE_CUSTOMER_DEBIT_MASTER pos,PERSONAL_INFO per where pos.customer_id=per.customer_id and " +
							"pos.quote_no  = ?";*/
			sql = " select initcap(nvl(per.company_name,per.first_name)),initcap(nvl(per.address1,' ')),initcap(nvl(per.address2,' ')),initcap(nvl(per.POBOX,' ')),initcap(replace(nvl(per.EMIRATE,' '),'select','')),initcap(replace(nvl(per.COUNTRY,' '),'select','')),nvl(pos.policy_no,' '),nvl(pos.premium,'0'),nvl(pos.DISCOUNT_Premium,'0'),nvl(customer_DEBIT_NOTE_NO,'0'),to_char(customer_DEBIT_NOTE_DATE,'dd-mm-yyyy'), "
				+ " (nvl(net_Premium,0)) from MARINE_CUSTOMER_DEBIT_MASTER pos,PERSONAL_INFO per,position_master mc where pos.customer_id=per.customer_id and " +
						"pos.quote_no  = ? and pos.quote_no=mc.quote_no";
			String result[][];
			final String[] args ={quoteNo};
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getCustomerDebitDetails(final String policyNumber,final String quoteNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getCustomerDebitDetails method()");
		logger.debug(ENTER);
			String[][] result;
			String query;
	    	String[] args = new String[1];
			if(quoteNo.length() >0){
				query = "select DISCOUNT_PREMIUM,CUSTOMER_DEBIT_NOTE_NO,NET_PREMIUM from MARINE_CUSTOMER_DEBIT_MASTER where QUOTE_NO = ?";
				args[0] = quoteNo;
			}else{
				query = "select DISCOUNT_PREMIUM,CUSTOMER_DEBIT_NOTE_NO,NET_PREMIUM from MARINE_CUSTOMER_DEBIT_MASTER where QUOTE_NO in (select quote_no from position_master where policy_no = ?) ";
				 args[0] =policyNumber;
			}
			result = runner.multipleSelection(query,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getPremiumValue(final String quoteNumber)throws BaseException
	{
		logger.info("OpenCover finalPrint getPremiumValue method()");
		logger.debug(ENTER);
			String preValue;
			String  query;
			query = "select (nvl(premium,'0')+nvl(excess_premium,'0'))  from position_master where quote_no = ?";
			final String[] args = {quoteNumber};
			preValue = runner.singleSelection(query,args);
		logger.debug(EXIT);
		
		return preValue;
	}
	public String getQuoteProductId(final String quoteNumber)throws BaseException
	{
		logger.info("OpenCover finalPrint getQuoteProductId method()");
		logger.debug(ENTER);
           String productId;
           final String[] args = {quoteNumber};
           productId = runner.singleSelection("select product_id from position_master where quote_no = ? ",args);
        logger.debug(EXIT);
   		
        return productId;
	}
	public boolean getDebitStatus(final String login, final String pid)throws BaseException
	{
		logger.info("OpenCover finalPrint getDebitStatus method()");
		logger.debug(ENTER);
			final String[] args = {login,pid};
			String sql;
			sql = "select nvl(FREIGHT_DEBIT_OPTION,'N') from login_user_details where login_id =  ? and product_id= ?";
	        String res;
			boolean result;
			res = runner.singleSelection(sql,args);
			if(res!=null&&res.length()>0)
			{
				if(res.equalsIgnoreCase("Y")){
					result = true;
				}else{
					result = false;
				}
			}else{
				result = false;
			}
		logger.debug(EXIT);
		
		return result;
	}

	public String getQuoteNumber(final String policyNumber)throws BaseException
	{
		logger.info("OpenCover finalPrint getQuoteNumber method()");
		logger.debug(ENTER);
	        String quoteNumber;
	        final String[] args = {policyNumber};
            quoteNumber = runner.singleSelection("select nvl(quote_no,' ') from marine_customer_debit_master where policy_no = ?",args);
        logger.debug(EXIT);
		
        return quoteNumber;
	}
	public String getCertificateNo(final String loginBra,final String productId,final String quoteNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getCertificateNo method()");
		logger.debug(ENTER);
			String certNo;
			final String args[] = {quoteNo};
			certNo = runner.singleSelection("select nvl(certificate_no,'0') from POSITION_MASTER where quote_no=?",args);
			if(certNo.length()<=0||"0".equals(certNo))
			{
				certNo = getMaxCertificateNo(loginBra,"CERTIFICATE_NO_TYPE_ID");
				final String args2[] = {certNo,quoteNo};
				runner.multipleUpdation("update POSITION_MASTER set certificate_no=? ,certificate_date=sysdate+9/24 where quote_no=?",args2);
			}
		logger.debug(EXIT);
		
		return certNo;
	}
	public String getMaxCertificateNo(final String loginBra,final String typeIdName)throws BaseException{
		logger.info("OpenCover finalPrint getMaxCertificateNo method()");
		logger.debug(ENTER);
			String sql,currentNo;
			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select "+typeIdName+" from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and status='Y' and BRANCH_CODE=?";
			final String args[] = {loginBra,loginBra};
			currentNo=runner.singleSelection(sql,args);
			
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select "+typeIdName+" from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and status='Y' and BRANCH_CODE=?";
			final String args1[] = {currentNo,currentNo,loginBra,loginBra};
			runner.multipleUpdation(sql,args1);
			
		logger.debug(EXIT);
		
		return currentNo;
	}
	public String getMaxCustomerDebit(final String loginId,final String branch,final String pid)throws BaseException
	{
		logger.info("OpenCover finalPrint getMaxCustomerDebit method()");
		logger.debug(ENTER);
			String acode;
			final String args[] = {loginId};
			acode = runner.singleSelection("select oa_code from login_master where agency_code=(select oa_code from login_master where login_id = ?)",args);
			final String args1[] = {branch,pid,acode,branch,branch,pid,acode,branch,pid,acode};
			String maxSql;
			maxSql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=" +
		  		"(select type_id from policyno_generate where BRANCH_CODE=? and PRODUCT_ID= ? and agency_code = ?) and status='Y' and branch_code = ? and amend_id=(select max(amend_id) from policyno_generate where type_id=(select type_id from " +
		  		" policyno_generate where BRANCH_CODE =? and PRODUCT_ID=? and agency_code=?) and branch_code=?) and PRODUCT_ID=? and agency_code = ?";
			String custDebitNote;  
			custDebitNote = runner.singleSelection(maxSql,args1);
			
			final String args2[] = {custDebitNote,custDebitNote,branch,pid,acode,branch,branch,pid,acode,branch,pid,acode};

			  runner.multipleUpdation("update policyno_generate set current_no =?," +
			  "remarks=? where type_id=(select type_id from policyno_generate " +
			  " where BRANCH_CODE=? and PRODUCT_ID=? and agency_code=?) " +
			  " and status='Y' and branch_code=? and amend_id=(select max(amend_id) from  " +
			  " policyno_generate where type_id=(select type_id from policyno_generate where " +
			  " BRANCH_CODE=? and PRODUCT_ID=? and agency_code=?) " +
			  " and branch_code=?) and PRODUCT_ID=? and agency_code=?",args2);
		logger.debug(EXIT);
		
		return custDebitNote;
	}
	public void updateBrokerDebitNo(final String quoteNo, final String loginId, final String pid,
			final String branch, final String disPremium)throws BaseException
	{
		logger.info("OpenCover finalPrint updateBrokerDebitNo method()");
		logger.debug(ENTER);
			String debitCheck;
			final String[] args = {quoteNo};
			debitCheck = runner.singleSelection("select nvl(CUSTOMER_DEBIT_NOTE_NO,'0') from MARINE_CUSTOMER_DEBIT_MASTER where  QUOTE_NO = ?",args);

			if (debitCheck.length() <= 0 || debitCheck.equalsIgnoreCase("0")	|| debitCheck.equalsIgnoreCase("") || debitCheck.equalsIgnoreCase("null")) {
				String custDebitNote;
				//custDebitNote = getMaxCustomerDebit(loginId,branch,pid);
				custDebitNote = getDebitNo(quoteNo);
				String upsql;
				upsql = "insert into MARINE_CUSTOMER_DEBIT_MASTER (QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,POLICY_NO,PREMIUM,DISCOUNT_PREMIUM,NET_PREMIUM,TAX, "
						+ "CUSTOMER_DEBIT_NOTE_NO,CUSTOMER_DEBIT_NOTE_DATE,STATUS) values ("+ quoteNo
						+ ",(select customer_id from position_master where  QUOTE_NO = '"+ quoteNo
						+ "'), "+ "(select LOGIN_ID from POSITION_MASTER where  QUOTE_NO = '"
						+ quoteNo+ "'),(select PRODUCT_ID from position_master where QUOTE_NO = '"
						+ quoteNo+ "'), "+ "(select POLICY_NO from  position_master where   QUOTE_NO = '"
						+ quoteNo+ "'),(select nvl(premium,'0')+nvl(excess_premium,'0') from position_master "
						+ "where quote_no = '"+ quoteNo+ "'),"+ disPremium+ ",(select (nvl(premium,'0')+nvl(excess_premium,'0'))-"
						+ disPremium+ " from position_master where quote_no='"+ quoteNo+ "'),0, "
						+ custDebitNote+ ",sysdate+9/24,'Y') ";

				runner.updation(upsql);
			}
			else {
				String upsql;
				upsql = "update MARINE_CUSTOMER_DEBIT_MASTER set PREMIUM = (select nvl(premium,'0')+nvl(excess_premium,'0') from position_master "
						+ "where quote_no = '"+ quoteNo+ "'), DISCOUNT_PREMIUM = "+ disPremium+ " ,NET_PREMIUM = (select (nvl(premium,'0')+nvl(excess_premium,'0'))-"
						+ disPremium+ " from position_master where quote_no='"+ quoteNo+ "') where CUSTOMER_DEBIT_NOTE_NO = "
						+ debitCheck;
				runner.updation(upsql);
			}
		logger.debug(EXIT);
		
	}
    public String updateBrokerDebitNoDeclaration(final String loginId, final String pid,final String branch, 
    		final String disPremium,final String policyNumber)throws BaseException
	{
		logger.info("OpenCover finalPrint updateBrokerDebitNoDeclaration method()");
		logger.debug(ENTER);
			String quoteNo;
			String debitCheck;
			final String[] args = {policyNumber};
			debitCheck = runner.singleSelection( "select nvl(CUSTOMER_DEBIT_NOTE_NO,'0') from MARINE_CUSTOMER_DEBIT_MASTER where  POLICY_NO = ?",args);
			quoteNo = runner.singleSelection("select max(quote_no) from position_master where policy_no = '"+policyNumber+"'");
			if (debitCheck.length() <= 0 || debitCheck.equalsIgnoreCase("0")	|| debitCheck.equalsIgnoreCase("") || debitCheck.equalsIgnoreCase("null")) {
				
				String custDebitNote;
				//custDebitNote = getMaxCustomerDebit(loginId,branch,pid);
				custDebitNote = getDebitNo(policyNumber);
				String customerId;
				customerId = runner.singleSelection("select customer_id from OPEN_COVER_MASTER where PROPOSAL_NO=(select  PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where open_cover_no=(Select distinct open_Cover_no from position_master where policy_no='"+policyNumber+"')) and amend_id=(select max(amend_id) from OPEN_COVER_MASTER where PROPOSAL_NO=(select  PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where open_cover_no=(Select distinct open_Cover_no from position_master where policy_no='"+policyNumber+"')))");
				
				String Upsql;
				Upsql = "insert into MARINE_CUSTOMER_DEBIT_MASTER (QUOTE_NO,CUSTOMER_ID,LOGIN_ID,PRODUCT_ID,POLICY_NO,PREMIUM,DISCOUNT_PREMIUM,NET_PREMIUM,TAX, "
				+ "CUSTOMER_DEBIT_NOTE_NO,CUSTOMER_DEBIT_NOTE_DATE,STATUS,POLICY_TYPE) values ("+ quoteNo
				+ ","+customerId+", "+ "(select LOGIN_ID from POSITION_MASTER where  QUOTE_NO = '"
				+ quoteNo+ "'),(select PRODUCT_ID from position_master where QUOTE_NO = '"
				+ quoteNo+ "'),'"+policyNumber+"' ,(select sum(nvl(premium,'0')+nvl(excess_premium,'0')) from position_master "
				+ "where policy_no = '"+ policyNumber+ "'),"+ disPremium+ ",(select sum((nvl(premium,'0')+nvl(excess_premium,'0')))-"
				+ disPremium+ " from position_master where policy_no ='"+ policyNumber+ "'),0, "
				+ custDebitNote+ ",sysdate+9/24,'Y','Multiple') ";

				runner.insertion(Upsql);
			}
			else {
				String Upsql;
				Upsql = "update MARINE_CUSTOMER_DEBIT_MASTER set PREMIUM = (select sum(nvl(premium,'0')+nvl(excess_premium,'0')) from position_master "
						+ "where policy_no = '"+ policyNumber+ "'), DISCOUNT_PREMIUM = "+ disPremium+ " ,NET_PREMIUM = (select sum(nvl(premium,'0')+nvl(excess_premium,'0'))-"
						+ disPremium+ " from position_master where policy_no = '"+ policyNumber+ "'),POLICY_TYPE = 'Multiple' where CUSTOMER_DEBIT_NOTE_NO = "
						+ debitCheck;
				runner.updation(Upsql);
			}
		logger.debug(EXIT);
		
		return quoteNo;
	}
	public String getPolicyNumber(final String quoteNumbers)throws BaseException
	{
		logger.info("openCover finalPrint getPolicyNumber method()");
		logger.debug(ENTER);
		   String policyNumber;
    	   policyNumber = runner.singleSelection("select policy_no from position_master where quote_no in ("+quoteNumbers+")");
        logger.debug(EXIT);
       	
        return policyNumber;
    }
	public String getMaxQuoteNum(final String policyNumber)throws BaseException
	{
		logger.info("openCover finalPrint getMaxQuoteNum method()");
		logger.debug(ENTER);
			String quoteNumber;
			final String args[] = {policyNumber};
			quoteNumber = runner.singleSelection("select quote_no from MARINE_CUSTOMER_DEBIT_MASTER where policy_no = ?",args);
		logger.debug(EXIT);
		
		return quoteNumber;
	}
	public String getPolicyProductId(final String policyNumber)throws BaseException
	{
		logger.info("openCover finalPrint getPolicyProductId method()");
		logger.debug(ENTER);
			String productId;
			final String args[] = {policyNumber};
			productId = runner.singleSelection("select product_id from position_master where policy_no = ?",args);
		logger.debug(EXIT);
		
		return productId;
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
	public String updateOpenCommission(final String open,final String policyNo,final String pid,final String broBra,
			final String comAmt,final double taxPercent)throws BaseException
	{
		logger.info("OpenCover finalPrint updateOpenCommission method()");
		logger.debug(ENTER);
			String sql;
			double taxAmt = 0;
			String proPremium = "";
			String debitNoteNo = "";
			double proAmt;
             //if(policyNo.indexOf('-')==-1){
			if(isNumeric(policyNo)){
            	 sql="select pos.debit_note_no,((nvl(pos.premium,'0')+nvl(pos.excess_premium,'0'))),(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+comAmt+"/100) " +
     	 		"from position_master pos,marine_Data md where pos.quote_no=? and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
             }else{
            	 sql="select pos.debit_note_no,((nvl(pos.premium,'0')+nvl(pos.excess_premium,'0'))),(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+comAmt+"/100) " +
            	 		"from position_master pos,marine_Data md where pos.policy_no=? and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
             }
			final String args[] = {policyNo};
			String result[][];
			result = runner.multipleSelection(sql,args);
			if(result.length>0){
				debitNoteNo = result[0][0];
				proPremium = result[0][1];
			}
			/*String proCommissions;
			proCommissions = getProCommission(open,policyNo,pid,broBra);
			if(proCommissions==null||proCommissions.length()<=0){
				proCommissions = "0";
			}
			proAmt = Double.parseDouble(proPremium)*Double.parseDouble(proCommissions)/100;
			if(taxPercent>0){
					taxAmt = Double.parseDouble(proPremium)*taxPercent/100;
			}
			String upSql;
			upSql = "update position_master set commission=?,PRO_COMMISSION=?,TAX=? where policy_no=?";
			final String args1[] = {result[0][2],Double.toString(proAmt),Double.toString(taxAmt),policyNo};
			runner.multipleUpdation(upSql,args1);*/
		logger.debug(EXIT);
		
		return debitNoteNo;
	}
	public Map updateMultiCommission(final String commis,final String open,final String policyNo,final String pid,
			final String broBra,final double taxPercent)throws BaseException
	{
		logger.info("OpenCover finalPrint updateMultiCommission method()");
		logger.debug(ENTER);	
			Map multiInfos;
			multiInfos = new HashMap();
			String debitNoteNo = "";
			String res[][];
			String sql;
			//sql = "select quote_no,((nvl(premium,'0')+nvl(excess_premium,'0'))*"+commis+"/100),((nvl(premium,'0')+nvl(excess_premium,'0'))),debit_note_no from position_master where policy_no=? order by quote_no";
			sql = "select pos.quote_no,(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+commis+"/100)," +
					"((nvl(pos.premium,'0')+nvl(pos.excess_premium,'0'))),pos.debit_note_no from position_master pos,marine_Data md " +
					"where pos.policy_no=? and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
			final String args[] = {policyNo};
			res = runner.multipleSelection(sql,args);
			double tempCount = 0;
			String proCommisPercent = "";
			/*for(int i=0;i<res.length;i++)
			{
				String proCommissions;
				proCommissions = getProCommission(open,res[i][0],pid,broBra,"QuoteBased");
				double taxAmt = 0;
				if(proCommissions==null||proCommissions.length()<=0){
					proCommissions = "0";
				}
				if (!"0".equals(proCommissions))
				{
					proCommisPercent = proCommissions;
				}
				double proCommiss =  Double.parseDouble((res[i][2]==null?"0":res[i][2]))*Double.parseDouble(proCommissions)/100;
				tempCount = tempCount+Double.parseDouble((res[i][2]==null?"0":res[i][2]));
				if(taxPercent>0){
					taxAmt =  Double.parseDouble((res[i][2]==null?"0":res[i][2]))*taxPercent/100;
				}
				String tempSql;
				tempSql = "update position_master set COMMISSION =?,PRO_COMMISSION=?,TAX=? where quote_no=?";
				debitNoteNo = res[0][3]==null?"":res[0][3];
				final String args1[] = {(res[i][1]==null?"0":res[i][1]),Double.toString(proCommiss),Double.toString(taxAmt),res[i][0]};
				runner.multipleUpdation(tempSql,args1);
			}*/
			if(res!=null && res.length>0){
				debitNoteNo = res[0][3]==null?"":res[0][3];
			}
			multiInfos.put("DebitNoteNo",debitNoteNo);
			multiInfos.put("proCommissionPercent",proCommisPercent);
			multiInfos.put("multiTotalPremium",Double.toString(tempCount));
		logger.debug(EXIT);
		
		return multiInfos;
	}
	public String getDebitNo(final String policyNo)throws BaseException
	{
		logger.info("OpenCover finalPrint getDebitNo method()");
		logger.debug(ENTER);	
			String debitNoteNo = "";
			String sql;
	         //if(policyNo.indexOf('-')==-1){
			if(isNumeric(policyNo)){
	        	 sql="select debit_note_no from position_master where quote_no=?";
	         }else{
	        	 sql="select debit_note_no from position_master where policy_no=?";
	         }
			final String args[] = {policyNo};
			debitNoteNo = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return debitNoteNo;
	}
	public String getCustomerCode(String policyNo,String queryStatus,String pid)throws BaseException{
		logger.info("OpenCover/One Off finalPrint getCustomerCode method()");
		logger.debug(ENTER);	
			String mcc="";
			String querySyntax="";
			if(pid.equalsIgnoreCase("11"))
			{
				if(queryStatus.equalsIgnoreCase(QUOTENO)){
					querySyntax = "quote_no='"+policyNo+"'";
				}else if(queryStatus.equalsIgnoreCase("QUOTENOSINGLE")){
					querySyntax = "quote_no='"+policyNo+"'";
				} 
				else if(queryStatus.equalsIgnoreCase("QUOTENOMULTIPLE") || queryStatus.equalsIgnoreCase("QUOTENOMULTIPLEDRAFT"))
				{
					String[][] multiQuotes;
					multiQuotes = getPolicyQuotes(policyNo,queryStatus);
					querySyntax = "quote_no='"+multiQuotes[0][0]+"'";
				}else{
					querySyntax = "policy_no='"+policyNo+"'";
				}
				mcc = runner.singleSelection("select nvl(b.missippi_customer_code,'0') from open_cover_master a , personal_info b  where a.customer_id=b.customer_id and  a.proposal_no=(select proposal_no from open_cover_position_master " +
					"where open_cover_no in(select open_cover_no from position_master where "+querySyntax+")) and a.amend_id=(select max(amend_id) from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where " +
					"open_cover_no in(select open_cover_no from position_master where "+querySyntax+")))");
		}else{
				if(queryStatus.equalsIgnoreCase(QUOTENO)){
					querySyntax = "quote_no='"+policyNo+"'";
				}else{
					querySyntax = "policy_no='"+policyNo+"'";
				} 
				mcc = runner.singleSelection("select nvl(b.missippi_customer_code,'0') from position_master a, personal_info b where a.customer_id=b.customer_id " +
						"and a."+querySyntax);
				if(mcc.length()<=0||mcc.equals("0"))
				{
					if(queryStatus.equalsIgnoreCase(QUOTENO)){
						querySyntax = "quote_no='"+policyNo+"'";
					}else{
						querySyntax = "policy_no='"+policyNo+"'";
					} 
					mcc = runner.singleSelection("select nvl(missippi_id,'0') from broker_company_master where agency_code=(select oa_code from login_master " +
								"where login_id =(select login_id from position_master where "+querySyntax+"))");
				}
		}
		logger.debug(EXIT);
		
		return mcc;
	}
	public String getForamattedDate(String policyNo,String queryStatus)throws BaseException{
		logger.info("OpenCover finalPrint getCustomerCode method()");
		logger.debug(ENTER);	
			String dateForm[][] = new String[0][0];
			String sql="";
			if(queryStatus.equalsIgnoreCase(QUOTENO)||queryStatus.equalsIgnoreCase("QUOTENOSINGLE")
					||queryStatus.equalsIgnoreCase("QUOTENOMULTIPLEDRAFT")){
				sql = "select initcap(to_char(sysdate+8/24,'day')),to_char(sysdate+8/24,'dd'),initcap(to_char(sysdate+8/24,'month')),initcap(to_char(sysdate+8/24,'YYYY')),to_char(sysdate+8/24,'hh24:MI:SS') from dual";
			}else if(queryStatus.equalsIgnoreCase("QUOTENOMULTIPLE"))
			{
				String[][] multiQuotes;
				multiQuotes = getPolicyQuotes(policyNo,queryStatus);
				sql = "select initcap(to_char(expiry_Date,'day')),to_char(expiry_Date,'dd'),initcap(to_char(expiry_Date,'month')),initcap(to_char(expiry_Date,'YYYY')),to_char(expiry_Date,'hh24:MI:SS') from position_master where quote_no='"+multiQuotes[0][0]+"'";
			}else{
				sql = "select initcap(to_char(expiry_Date,'day')),to_char(expiry_Date,'dd'),initcap(to_char(expiry_Date,'month')),initcap(to_char(expiry_Date,'YYYY')),to_char(expiry_Date,'hh24:MI:SS') from position_master where policy_no='"+policyNo+"'";
			}
			dateForm = runner.multipleSelection(sql);
			StringBuffer dateFormat= new StringBuffer();
			if(dateForm.length>0){
				NumberToWordBean numWord = new NumberToWordBean();
				dateFormat.append("this ");
				dateFormat.append(dateForm[0][0].trim());
				dateFormat.append(" the ");
				dateFormat.append(numWord.convertDateNumToWord(Integer.parseInt(dateForm[0][1])));
				dateFormat.append(" of ");
				dateFormat.append(dateForm[0][2].trim());
				dateFormat.append(" ");
				dateFormat.append(dateForm[0][3].trim());
				dateFormat.append(" Time : ");
				dateFormat.append(dateForm[0][4].trim());
			}
		logger.debug(EXIT);
		
		return dateFormat.toString();
	}
	public Map getTaxDetails(final String policyNo,final String pid,final String queryStatus)throws BaseException{
		logger.info("OpenCover finalPrint getPremiumTaxDetails method()");
		logger.debug(ENTER);	
			String result[][] = new String[0][0];
			String sql; 
			logger.info("queryStatus...."+queryStatus);
			String querySyntax = getQuerySyntax(pid,queryStatus,policyNo);
			sql = "select pos.quote_no,case when (nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX_Percent,'0')+nvl(md.EMERGENCY_FUND_Percent,'0'))>0 then 'Yes' else 'No' end," +
					"round((md.premium+nvl(md.excess_premium,0))-(md.TOTAL_WAR_CHARGES+round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),2)),2)," +
					"round(md.TOTAL_WAR_CHARGES,2),round(nvl(md.GOVT_TAX,'0'),2),round(nvl(md.EMERGENCY_FUND,'0'),2),round(nvl(md.EMERGENCY_FUND,'0')+" +
					"(nvl(md.GOVT_TAX,'0')),2)," +
					"round(nvl(md.POLICY_FEE,'0'),2),round(md.premium+nvl(md.excess_premium,0),2),round(md.premium+nvl(md.excess_premium,2)-(round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),2)),2) " +
					",case when (nvl(md.GOVT_TAX_Percent,'0'))>0 then 'Yes' else 'No' end,case when (nvl(md.EMERGENCY_FUND_Percent,'0'))>0 then 'Yes' else 'No' end," +
					"case when (nvl(md.POLICY_FEE,'0'))>0 then 'Yes' else 'No' end from MARINE_data md,position_master pos where md.application_no=pos.application_no and  pos."+querySyntax+" order by pos.quote_no";
			result = runner.multipleSelection(sql);
			Map taxDetails = new HashMap();
			for(int i=0;i<result.length;i++){
				
				taxDetails.put("QuoteNo"+i, result[i][0]);
				taxDetails.put(result[i][0], result[i][0]);
				taxDetails.put("TaxOption"+result[i][0], result[i][1]);
				taxDetails.put("MarinePremium"+result[i][0], result[i][2]);
				taxDetails.put("WarPremium"+result[i][0], result[i][3]);
				taxDetails.put("GovTax"+result[i][0], result[i][4]);
				taxDetails.put("Fund"+result[i][0], result[i][5]);
				taxDetails.put("TaxSum"+result[i][0], result[i][6]);
				taxDetails.put("Fee"+result[i][0], result[i][7]);
				taxDetails.put("FinalPremium"+result[i][0], result[i][8]);
				taxDetails.put("MarineWar"+result[i][0], result[i][9]);
				taxDetails.put("TaxStatus"+result[i][0], result[i][10]);
				taxDetails.put("FundStatus"+result[i][0], result[i][11]);
				taxDetails.put("FeeStatus"+result[i][0], result[i][12]);
			}
			taxDetails.put("PreSize", Integer.toString(result.length));
		logger.debug(EXIT);
		
		return taxDetails;
	}
	public String getQuerySyntax(String pid,String queryStatus,String policyNo)throws BaseException{
		logger.info("OpenCover finalPrint getQuerySyntax method()");
		logger.debug(ENTER);	
		String querySyntax = "";
		if(pid.equalsIgnoreCase("11"))
		{
			if(queryStatus.equalsIgnoreCase(QUOTENO)){
				querySyntax = "quote_no='"+policyNo+"'";
			}else if(queryStatus.equalsIgnoreCase("QUOTENOSINGLE")){
				querySyntax = "quote_no='"+policyNo+"'";
			} 
			else if(queryStatus.equalsIgnoreCase("QUOTENOMULTIPLE") || queryStatus.equalsIgnoreCase("QUOTENOMULTIPLEDRAFT"))
			{
				String[][] multiQuotes;
				multiQuotes = getPolicyQuotes(policyNo,queryStatus);
				String mulQuotes="";
				for(int i=0;i<multiQuotes.length;i++){
					mulQuotes = mulQuotes+"'"+multiQuotes[i][0]+"',";
				}
				if(mulQuotes.length()>0){
					mulQuotes = mulQuotes.substring(0,(mulQuotes.length()-1));
				}
				querySyntax = "quote_no in("+mulQuotes+")";
			}else{
				querySyntax = "policy_no='"+policyNo+"'";
			}
		}else{
				if(queryStatus.equalsIgnoreCase(QUOTENO)){
					querySyntax = "quote_no='"+policyNo+"'";
				}else{
					querySyntax = "policy_no='"+policyNo+"'";
				} 
		}
		logger.info("querySyntax.........2"+querySyntax);
		logger.info("queryStatus.........2"+queryStatus);
		logger.debug(EXIT);
		
		return querySyntax;
	}
	public String getFCName(final String policyNo,final String pid,final String queryStatus,final String branchCode)throws BaseException{
		logger.info("OpenCover finalPrint getFCName method()");
		logger.debug(ENTER);	
			String fcName;
			String querySyntax = getQuerySyntax(pid,queryStatus,policyNo);
			String sql = "select nvl(cm.short_name,cm.currency_name) from CURRENCY_MASTER cm,marine_Data md,position_master pos,branch_master bm " +
					"where md.application_no=pos.application_no and cm.amend_id||cm.currency_id in(select max(cm.amend_id)||cm.currency_id from " +
					"CURRENCY_MASTER cm where cm.CURRENCY_ID=md.CURRENCY_ID AND cm.country_id=bm.ORIGINATION_COUNTRY_ID group by cm.currency_id)" +
					" and md.currency_id=cm.currency_id AND bm.branch_code='"+branchCode+
					"' and bm.amend_id=(select max(amend_id) from branch_master where branch_code='"+branchCode+
					"') and cm.country_id = bm.ORIGINATION_COUNTRY_ID and pos."+querySyntax;
			System.out.println("Query==>"+sql);
			fcName = runner.singleSelection(sql);
			System.out.println("FC Name==>"+fcName);
		return fcName;
	}
	public Map getPreComDetails(String policyNo,String pid,String queryStatus)throws BaseException{
		logger.info("OpenCover finalPrint getPreComDetails method()");
			Map preDetails;preDetails = new HashMap();
			String querySyntax = getQuerySyntax(pid,queryStatus,policyNo);
			String sql;
			sql = "select pos.quote_no,(nvl(pos.premium,'0')+nvl(pos.excess_premium,'0')) as TotalPremium,nvl(pos.COMMISSION,0) as commissionAmt," +
					"((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0')) as BasicPremium,pos.debit_note_no," +
					"case when (nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX_Percent,'0')+nvl(md.EMERGENCY_FUND_Percent,'0'))>0 then 'Yes' else 'No' end," +
					"((nvl(pos.premium,'0')+nvl(pos.excess_premium,'0'))-pos.COMMISSION), md.PREMIUM from position_master pos,marine_Data md" +
					" where pos."+querySyntax+" and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
			String result[][] = new String[0][0];
			result = runner.multipleSelection(sql);
			double totPre=0,basePre=0,commission=0,payablePre=0;
			String qnos="";
			if(result.length>0){
				for(int i=0;i<result.length;i++){
					qnos = qnos+result[i][0]+",";
					totPre = totPre+Double.parseDouble(result[i][1]);
					commission = commission + Double.parseDouble(result[i][2]);
					basePre = basePre+Double.parseDouble(result[i][3]);
					payablePre = payablePre+Double.parseDouble(result[i][6]);
				}
				preDetails.put("QuoteNos",qnos);
				preDetails.put("DebitNos",result[0][4]);
				preDetails.put("TaxStatus",result[0][5]);
				preDetails.put("NetPremium",result[0][7]);
				preDetails.put("TotalPremium",Double.toString(totPre));
				preDetails.put("Commission",Double.toString(commission));
				preDetails.put("BasePremium",Double.toString(basePre));
				preDetails.put("PayblePremium",Double.toString(payablePre));
			}
		return preDetails;
	}
	
	public boolean isNumeric(final String value){
		boolean result = false;
		try{
			Long.parseLong(value);
			result = true;
		}catch(Exception e){
			result = false;
		}
		return result;
	}
	public String getOpenCoverStartDate(final String openCoverNo)throws BaseException{
		logger.info("getOpenCoverDate - Enter || openCoverNo: "+openCoverNo);
		logger.debug(ENTER);	
			String result = runner.singleSelection("SELECT TO_CHAR(INCEPTION_DATE,'DD/MM/YYYY') FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=?",new String[]{openCoverNo});
		logger.info("getOpenCoverDate - Exit || Result: "+openCoverNo);
		return result;
	}
	public String[][] getMultipleQuoteInfo(String branchCode, String quoteNo)throws BaseException{
		logger.info("getMultipleQuoteInfo - Enter || quoteNo: "+quoteNo);
		logger.debug(ENTER);	
		String[][] result = runner.multipleSelection("SELECT distinct(pm.application_no),(SELECT REF_NO FROM WEBSERVICE_MARINE_QUOTE WHERE QUOTE_NO=PM.QUOTE_NO) REF_NO, (SELECT ORDER_NO FROM WEBSERVICE_MARINE_QUOTE WHERE QUOTE_NO=PM.QUOTE_NO) ORDER_NO, (SELECT REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,DESCRIPTION || ', ')).EXTRACT('//text()'),', '),'&amp;','&') DESCRIPTION FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO AND AMEND_ID=MRD.AMEND_ID) DESCRIPTION , CASE WHEN OLM.LC_NUMBER = 'NONE' THEN NULL ELSE OLM.LC_NUMBER || ' / ' || OBM.BANK_NAME END LC_NUMBER, MOT.TRANSPORT_DESCRIPTION CONVEYANCE, CASE WHEN MPD.SAILING_DATE IS NOT NULL THEN MPD.CARRIER_NAME||' / '||MPD.SAILING_DATE ELSE MPD.CARRIER_NAME END VESSEL_NAME , TRIM(MD.TRANSIT_FROM||' '||FN_GET_CODE('CNAME',MD.TRANSIT_FROM_COUNTRY_ID,'CNAME','')) VOYAGE_FROM, TRIM(MD.FINAL_DESTINATION||' '||FN_GET_CODE('CNAME',MD.FINAL_DESTINATION_COUNTRY_ID,'CNAME','')) VOYAGE_TO, MRD.SUM_INSURED INVOICE_VALUE, MD.CURRENCY_NAME CURRENCY, SM.SALE_TERM_NAME INCOTERMS, MD.EXCHANGE_RATE ROE, CASE WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') = '0' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME,'[^+]+', 1, 1) WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') = 'NONE' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1,1) WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') != 'NONE' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 1) || '+' || TM.TOLERANCE_NAME WHEN SM.SALE_TERM_VALUE > 0 AND NVL (TM.TOLERANCE_NAME, '0') = 'NONE' THEN SM.SALE_TERM_NAME WHEN SM.SALE_TERM_VALUE > 0 AND NVL (TM.TOLERANCE_NAME, '0') != 'NONE' AND NVL (TM.TOLERANCE_NAME, '0') != '0' THEN SM.SALE_TERM_NAME || '+' || TM.TOLERANCE_NAME ELSE SM.SALE_TERM_NAME END BASIS_OF_VALUATION, ((MRD.SUM_INSURED*MD.EXCHANGE_RATE)+ NVL(MD.TOTAL_SALE_TERM_CHARGES,0)+NVL(MD.TOTAL_TOLERANCE_CHARGES,0)) SUM_INSURED FROM POSITION_MASTER PM,MARINE_POLICY_DETAILS MPD, MARINE_DATA MD,MODE_OF_TRANSPORT MOT,SALE_TERM_MASTER SM,TOLERANCE_MASTER TM, (SELECT MRD.APPLICATION_NO,MAX(MRD.AMEND_ID) AMEND_ID,SUM(MRD.SUM_INSURED) SUM_INSURED FROM MARINE_RESULT_DETAILS MRD, POSITION_MASTER PM WHERE PM.APPLICATION_NO=MRD.APPLICATION_NO AND PM.QUOTE_NO IN ("+quoteNo+") GROUP BY MRD.APPLICATION_NO) MRD,OPEN_COVER_LC_MASTER OLM, OPEN_COVER_BANK_MASTER OBM WHERE PM.QUOTE_NO IN ("+quoteNo+") AND MPD.QUOTE_NO=PM.QUOTE_NO AND MPD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_POLICY_DETAILS WHERE QUOTE_NO=MPD.QUOTE_NO) AND MRD.APPLICATION_NO=PM.APPLICATION_NO AND MD.APPLICATION_NO=PM.APPLICATION_NO AND MOT.BRANCH_CODE=? AND MOT.MODE_TRANSPORT_ID=MD.MODE_OF_TRANSPORT AND SM.BRANCH_CODE=MOT.BRANCH_CODE AND SM.SALE_TERM_ID=MD.SALE_TERM_ID AND TM.BRANCH_CODE=SM.BRANCH_CODE AND TM.TOLERANCE_ID=MD.TOLERANCE_ID AND OLM.OPEN_COVER_NO=PM.OPEN_COVER_NO AND OLM.LC_ID=MD.OPEN_LC_ID AND OLM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_LC_MASTER WHERE OPEN_COVER_NO=OLM.OPEN_COVER_NO) AND OBM.BANK_ID=MD.OPEN_BANK_ID",new String[]{branchCode});
		logger.info("getMultipleQuoteInfo - Exit || Result: "+result.length);
		return result;
	}
	public String[][] getMultipleQuotePremiumInfo(String quoteNo)throws BaseException{
		logger.info("getMultipleQuotePremiumInfo - Enter || quoteNo: "+quoteNo);
		logger.debug(ENTER);	
		String[][] result = runner.multipleSelection("SELECT SUM(PREMIUM),SUM(TOTAL_WAR_CHARGES),SUM(EXCESS_PREMIUM),SUM(POLICY_FEE), SUM(PREMIUM)+SUM(TOTAL_WAR_CHARGES)+SUM(EXCESS_PREMIUM)+SUM(POLICY_FEE) AS NET_PREMIUM,SUM(PREMIUM)+SUM(TOTAL_WAR_CHARGES) AS TOTAL_PREMIUM FROM ( SELECT CASE WHEN MD.PREMIUM<=0 THEN MD.PREMIUM WHEN MD.PREMIUM>0 THEN MD.PREMIUM-NVL(MD.POLICY_FEE,0)-NVL(MD.TOTAL_WAR_CHARGES,0) ELSE 0 END PREMIUM, NVL(MD.TOTAL_WAR_CHARGES,0) TOTAL_WAR_CHARGES, MD.EXCESS_PREMIUM ,MD.POLICY_FEE FROM POSITION_MASTER PM,MARINE_DATA MD WHERE PM.QUOTE_NO IN ("+quoteNo+") AND MD.APPLICATION_NO=PM.APPLICATION_NO)");
		logger.info("getMultipleQuotePremiumInfo - Exit || Result: "+result.length);
		return result;
	}
	public String getMulFCName(final String policyNo,final String pid,final String queryStatus,final String branchCode)throws BaseException{
		logger.info("OpenCover finalPrint getFCName method()");
		logger.debug(ENTER);	
			String fcName;
			String querySyntax = getQuerySyntax(pid,queryStatus,policyNo);
			String sql = "select nvl(cm.short_name,cm.currency_name) from CURRENCY_MASTER cm,position_master pos,branch_master bm " +
					"where cm.amend_id||cm.currency_id in(select max(cm.amend_id)||cm.currency_id from " +
					"CURRENCY_MASTER cm where cm.CURRENCY_ID=pos.OC_MUL_CURR_ID AND cm.country_id=bm.ORIGINATION_COUNTRY_ID group by cm.currency_id)" +
					" and pos.OC_MUL_CURR_ID=cm.currency_id AND bm.branch_code='"+branchCode+
					"' and bm.amend_id=(select max(amend_id) from branch_master where branch_code='"+branchCode+
					"') and cm.country_id = bm.ORIGINATION_COUNTRY_ID and pos."+querySyntax;
			System.out.println("Query==>"+sql);
			fcName = runner.singleSelection(sql);
			System.out.println("FC Name==>"+fcName);
		return fcName;
	}
	public double getnetPremium(String policyNo)
	{
		double res1;
		logger.info("getnetPremium - Enter || policyNo: "+policyNo);
		String result=runner.singleSelection("SELECT PREMIUM FROM POSITION_MASTER WHERE POLICY_NO=? AND STATUS IN('P','C')",new String[]{policyNo});
		res1=Double.parseDouble(result);
		logger.info("getnetPremium - Exit || Result: "+result);
		return 	res1;
	}
	public String getApplicationNo(String policyNo)
	{
		logger.info("getApplicationNo - Enter || policyNo: "+policyNo);
		String result=runner.singleSelection("SELECT APPLICATION_NO FROM POSITION_MASTER WHERE POLICY_NO=? AND STATUS in ('P','D')",new String[]{policyNo});
		logger.info("getApplicationNo - Exit || Result: "+result);
		return 	result;
	}
	public String getApplicationNo1(String quoteNo)
	{
		logger.info("getApplicationNo - Enter || quoteNo: "+quoteNo);
		String result=runner.singleSelection("SELECT APPLICATION_NO FROM POSITION_MASTER WHERE quote_no=? AND STATUS in ('Y','E','D')",new String[]{quoteNo});
		logger.info("getApplicationNo - Exit || Result: "+result);
		return 	result;
	}
} // Class