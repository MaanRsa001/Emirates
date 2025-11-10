package rsa.pdf;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;


public class finalprint
{
	private static final Logger logger = LogUtil.getLogger(finalprint.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String YES = "YES";
	final static transient private String COUNTRY = "Country";
	final static transient private String QUOTENO = "QUOTENO";
	final static transient private String NOTHING = "NOTHING";
	private transient String coverId;
	private transient String extraCoverIdNew;
	private transient double totCommSumIns = 0.0;
	private transient double totSumLocal = 0.0;
	
	private String policyDate;
	private String quoteId;
	private String modeOfTransport;
	private String warehouse;
	private String warehouseFinal;
	private String transitFrom;
	private String finalDestination;
	private String totalSumInsured;
	private String noOfItems;
	private String currencyValue;
	private String policyDay;
	private String policyMonth;
	private String policyYear;
	private String saleTerm;
	private String cover;
	private String wsrcc;
	private String currencyId;
	private String exchangeId;
	private String premium;
	private String excessPremium;
	private String currency;
	private transient String transitFromId;
	private transient String finalDestId;
	private transient String transitCityId;
	private transient String finalDestCityId;
	private transient String destWarranties;
	private transient String startWarranties;
	private transient String transPortId;
	private transient String seaOption;
	private int countBackDays;
	
	public void setCountBackDays(final int countBackDays) 
	{
		this.countBackDays = countBackDays;
	}

	public int getCountBackDays() 
	{
		return this.countBackDays;
	}

	public void setPremium(final String premium) 
	{
		this.premium = premium;
	}

	public String getPremium() {
		return premium;
	}

	public void setPolicyDate(final String policyDate) {
		this.policyDate = policyDate;
	}

	public String getPolicyDate() {
		return policyDate;
	}

	public void setExcessPremium(final String excessPremium) {
		this.excessPremium = excessPremium;
	}

	public String getExcessPremium() {
		return excessPremium;
	}

	public void setSeaOptions(final String seaOption) {
		this.seaOption = seaOption;
	}

	public String getSeaOptions() {
		return seaOption;
	}

	/**
	 * @return Returns the currencyId.
	 */
	public String getCurrencyId() {
		return currencyId;
	}

	/**
	 * @param currencyId
	 *            The currencyId to set.
	 */
	public void setCurrencyId(final String currencyId) {
		this.currencyId = currencyId;
	}

	/**
	 * @return Returns the exchangeId.
	 */
	public String getExchangeId() {
		return exchangeId;
	}

	/**
	 * @param exchangeId
	 *            The exchangeId to set.
	 */
	public void setExchangeId(final String exchangeId) {
		this.exchangeId = exchangeId;
	}

	/**
	 * @return Returns the quoteId.
	 */
	public String getQuoteId() {
		return quoteId;
	}

	/**
	 * @param quoteId
	 *            The quoteId to set.
	 */
	public void setQuoteId(final String quoteId) {
		this.quoteId = quoteId;
	}

	/**
	 * @return Returns the totalSumInsured.
	 */
	public String getTotalSumInsured() {
		return totalSumInsured;
	}

	/**
	 * @param totalSumInsured
	 *            The totalSumInsured to set.
	 */
	public void setTotalSumInsured(final String totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}

	public void setTransitFromCountryId(final String transitFromId) {
		this.transitFromId = transitFromId;
	}

	public String getTransitFromCountryId() {
		return transitFromId;
	}

	public void setFinalDestinationCountryId(final String finalDestId) {
		this.finalDestId = finalDestId;
	}

	public String getFinalDestinationCountryId() {
		return finalDestId;
	}

	public void setTransitFromCityId(final String transitCityId) {
		this.transitCityId = transitCityId;
	}

	public String getTransitFromCityId() {
		return transitCityId;
	}

	public void setFinalDestinationCityId(final String finalDestCityId) {
		this.finalDestCityId = finalDestCityId;
	}

	public String getFinalDestinationCityId() {
		return finalDestCityId;
	}

	/**
	 * @return Returns the cover_.
	 */
	public String getCover() {
		return cover;
	}

	/**
	 * @param cover_
	 *            The cover_ to set.
	 */
	public void setCoverId(final String coverId) {
		this.coverId = coverId;
	}

	public String getCoverId() {
		return coverId;
	}

	/**
	 * @param cover_
	 *            The cover_ to set.
	 */
	public void setCover(final String cover) {
		this.cover = cover;
	}

	/**
	 * @return Returns the currency_.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency_
	 *            The currency_ to set.
	 */
	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	/**
	 * @return Returns the currencyValue_.
	 */
	public String getCurrencyValue() {
		return currencyValue;
	}

	/**
	 * @param currencyValue_
	 *            The currencyValue_ to set.
	 */
	public void setCurrencyValue(final String currencyValue) {
		this.currencyValue = currencyValue;
	}

	/**
	 * @return Returns the finalDestination_.
	 */
	public String getFinalDestination() {
		return finalDestination;
	}

	/**
	 * @param finalDestination_
	 *            The finalDestination_ to set.
	 */
	public void setFinalDestination(final String finalDestination) {
		this.finalDestination = finalDestination;
	}

	/**
	 * @return Returns the modeOfTransport_.
	 */
	public String getModeOfTransport() {
		return modeOfTransport;
	}

	/**
	 * @param modeOfTransport_
	 *            The modeOfTransport_ to set.
	 */
	public void setModeOfTransport(final String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	/**
	 * @return Returns the noOfItems_.
	 */
	public String getNoOfItems() {
		return noOfItems;
	}

	/**
	 * @param noOfItems_
	 *            The noOfItems_ to set.
	 */
	public void setNoOfItems(final String noOfItems) {
		this.noOfItems = noOfItems;
	}

	/**
	 * @return Returns the policyDay_.
	 */
	public String getPolicyDay() {
		return policyDay;
	}

	/**
	 * @param policyDay_
	 *            The policyDay_ to set.
	 */
	public void setPolicyDay(final String policyDay) {
		this.policyDay = policyDay;
	}

	/**
	 * @return Returns the policyMonth_.
	 */
	public String getPolicyMonth() {
		return policyMonth;
	}

	/**
	 * @param policyMonth_
	 *            The policyMonth_ to set.
	 */
	public void setPolicyMonth(final String policyMonth) {
		this.policyMonth = policyMonth;
	}

	/**
	 * @return Returns the policyYear_.
	 */
	public String getPolicyYear() {
		return policyYear;
	}

	/**
	 * @param policyYear_
	 *            The policyYear_ to set.
	 */
	public void setPolicyYear(final String policyYear) {
		this.policyYear = policyYear;
	}

	/**
	 * @return Returns the saleTerm_.
	 */
	public String getSaleTerm() {
		return saleTerm;
	}

	/**
	 * @param saleTerm_
	 *            The saleTerm_ to set.
	 */
	public void setSaleTerm(final String saleTerm) {
		this.saleTerm = saleTerm;
	}

	/**
	 * @return Returns the transitFrom_.
	 */
	public String getTransitFrom() {
		return transitFrom;
	}

	/**
	 * @param transitFrom_
	 *            The transitFrom_ to set.
	 */
	public void setTransitFrom(final String transitFrom) {
		this.transitFrom = transitFrom;
	}

	/**
	 * @return Returns the warehouse_.
	 */
	public String getWarehouse() {
		return warehouse;
	}

	/**
	 * @param warehouse_
	 *            The warehouse_ to set.
	 */
	public void setWarehouse(final String warehouse) {
		this.warehouse = warehouse;
	}

	/**
	 * @return Returns the warehouse_.
	 */
	public String getWarehouseFinal() {
		return warehouseFinal;
	}

	/**
	 * @param warehouse_
	 *            The warehouse_ to set.
	 */
	public void setWarehouseFinal(final String warehouseFinal) {
		this.warehouseFinal = warehouseFinal;
	}

	/**
	 * @return Returns the wsrcc_.
	 */
	public String getWsrcc() {
		return wsrcc;
	}

	/**
	 * @param wsrcc_
	 *            The wsrcc_ to set.
	 */
	public void setWsrcc(final String wsrcc) {
		this.wsrcc = wsrcc;
	}

	/**
	 * @return Returns the wsrcc_.
	 */
	public String getExtraCoverIdNew() {
		return extraCoverIdNew;
	}

	/**
	 * @param wsrcc_
	 *            The wsrcc_ to set.
	 */
	public void setExtraCoverIdNew(final String extraCoverIdNew) {
		this.extraCoverIdNew = extraCoverIdNew;
	}
	public String getLoginUserDetails(final String loginId) throws BaseException
	{
		logger.info("One Off finalPrint getLoginUserDetails method()");
		logger.debug(ENTER);
			final String args[] = {loginId};
			String result;
			result = runner.singleSelection("select first_name from personal_info where customer_id in (select customer_id from login_user_details where  login_id=?)",args);
		logger.debug(EXIT);
		
		return result;
	}
	public Map getFinalTotal(final String amtPay, final String commisP, final String miniPre,final String excessPre)throws BaseException
	{
		logger.info("One Off finalPrint getFinalTotal method()");
		logger.debug(ENTER);
			Map amountDetails;
			amountDetails = new HashMap();
			double commisA;
			double finalAmtPay;
			logger.info("the royal test commisP..." + commisP);
			commisA = (Double.parseDouble(amtPay) + Double.parseDouble(excessPre)) * (Double.parseDouble(commisP) / 100);
			finalAmtPay = (Double.parseDouble(amtPay) + Double.parseDouble(excessPre)) - commisA;
			amountDetails.put("FinalPayableAmountAfterDeduction", Double.toString(finalAmtPay));
			amountDetails.put("CommissionAmount", Double.toString(commisA));
			amountDetails.put("FinalPayableAmount", Double.toString(Double.parseDouble(amtPay)));
		logger.debug(EXIT);
		
		return amountDetails;
	}
	public int getMaximumAmendId(final String quoteId) throws BaseException
	{
		logger.info("One Off finalPrint getMaximumAmendId method()");
		logger.debug(ENTER);
			final String args[] = {quoteId};
			String amendId;
			amendId = runner.singleSelection("select nvl(max(amend_id),'0')+1 from draft_pdf_tracking where quote_no=?",args);
		logger.debug(EXIT);
		
		return Integer.parseInt(amendId);
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
	public void insertDraftTrackingDetails(final String quoteId) throws BaseException
	{
		logger.info("One Off finalPrint insertDraftTrackingDetails method()");
		logger.debug(ENTER);
		
			int amendId;
			amendId = getMaximumAmendId(quoteId);
			String sqlQuery;
			sqlQuery = "insert into draft_pdf_tracking (QUOTE_NO,MODE_TRANSPORT_ID,WAREHOUSE_TO_WAREHOUSE,TRANSIT_FROM,FINAL_DESTINATION, NO_OF_ITEMS,TOTAL_SUM_INSURED,PREMIUM,EXCESS_PREMIUM,CURRENCY_ID,EXCHANGE_RATE,SALE_TERM_ID,COVER_ID,EXTRA_COVER_ID,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,transit_from_city_id,transit_from_country_id,final_destination_city_id,final_destination_country_id,sea_options) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE('"
					+ policyDate
					+ "','dd-mm-yyyy hh24:mi:ss'),SYSDATE,SYSDATE,SYSDATE,?,?,?,?,?,?,?)";

			StringBuffer fromCityId;
			fromCityId = new StringBuffer();
			if (getTransitFromCityId() == null || "".equalsIgnoreCase(getTransitFromCityId())) {
				fromCityId.append('0');
			} else {
				fromCityId.append(getTransitFromCityId());
			}
			StringBuffer toCityId;
			toCityId = new StringBuffer();
			if ("".equalsIgnoreCase(getFinalDestinationCityId())) {
				toCityId.append('0');
			} else {
				toCityId.append(getFinalDestinationCityId());
			}
			final String args[] = {quoteId,getModeOfTransport(),getWarehouse(),getTransitFrom(),getFinalDestination(),
					isNull(getNoOfItems(),"0"),isNull(getTotalSumInsured(),"0"),isNull(getPremium(),"0"),isNull(getExcessPremium(),""),isNull(getCurrencyId(),"0"),
					isNull(getCurrencyValue(),"0"),isNull(getSaleTerm(),"0"),isNull(getCoverId(),"0"),isNull(getExtraCoverIdNew(),"0"),
					Integer.toString(amendId),"DRAFT COPY","Y",fromCityId.toString(),isNull(getTransitFromCountryId(),"0"),
					toCityId.toString(),isNull(getFinalDestinationCountryId(),"0"),isNull(getSeaOptions(),"")};
			
			runner.multipleInsertion(sqlQuery,args);

		logger.debug(EXIT);
		
	}
	public String[][] getPolicyLoginCode(final String policyNo, final String status) throws BaseException
	{
		logger.info("One Off finalPrint getPolicyLoginCode method()");
		logger.debug(ENTER);
			String sql;
			if ("policyNo".equalsIgnoreCase(status)){
				sql = "select login_id from position_master where policy_no=? and status='P'";
			}else{
				sql = "select login_id from position_master where quote_no=?";
			}
			final String args[] = {policyNo};
			String[][] userType;
			userType= runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return userType;
	}	
	public String freightUserCommission(final String policyNo,final String queryStatus, final String loginCode)throws BaseException
	{
		logger.info("One Off finalPrint freightUserCommission method()");
		logger.debug(ENTER);
		String qry;
			if ( queryStatus.equalsIgnoreCase(QUOTENO) )
			{
				qry = "select nvl(commission,'0') from login_user_details where login_id in (select login_id from position_master where quote_no=? and freight_status='F' and login_id=?)";
			}
			else
			{
				qry = "select nvl(commission,'0') from login_user_details where login_id in (select login_id from position_master where policy_no=? and freight_status='F' and login_id=?)";
			}
			final String args[] = {policyNo,loginCode};
			String freightComm;
			freightComm = runner.singleSelection(qry,args);
			
		logger.debug(EXIT);
		
		return freightComm;
	}
	public String getFreightQuoteLogin(final String quoteNo)throws BaseException
	{
		logger.info("One Off finalPrint getFreightQuoteLogin method()");
		logger.debug(ENTER);
			final String args[] = {quoteNo};
			String value;
			value = runner.singleSelection("select login_id from position_master where policy_no=? and freight_status='F'",args);
		logger.debug(EXIT);
		
		return value;
	}
	public String[][] getComExcessPre(final String qno,final String displayMode)throws BaseException
	{
		logger.info("One Off finalPrint getComExcessPre method()");
		logger.debug(ENTER);
			String sql;
			if("draftMode".equalsIgnoreCase(displayMode)){
				sql = "select nvl(COMMODITY_EXCESS_PREMIUM,'0'),DESCRIPTION from marine_result_details where application_no=(select application_no from position_master where quote_no=? and PDF_EXCESS_STATUS='YES') AND COMMODITY_EXCESS_PREMIUM>0";
			}else{
				sql = "select nvl(COMMODITY_EXCESS_PREMIUM,'0'),DESCRIPTION from marine_result_details where application_no=(select application_no from position_master where policy_no=? and PDF_EXCESS_STATUS='YES') AND COMMODITY_EXCESS_PREMIUM>0";
			}
			final String args[] = {qno};
			String result[][];
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}

	public String getProCommission(final String loginBra,final String pid,final String policyNo)throws BaseException
	{
		logger.info("One Off finalPrint getProCommission method()");
		logger.debug(ENTER);
			String sql;
			sql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=?" +
				" and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?))";
			
			final String args[] = {pid,policyNo,loginBra,policyNo,policyNo,pid,policyNo,loginBra,policyNo,policyNo};
			String result;
			result = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getToleranceDetails(final String qno,final String queryStatus,final String loginBra)throws BaseException
	{
		logger.info("One Off finalPrint getToleranceDetails method()");
		logger.debug(ENTER);
			String sql;
			if (queryStatus.equalsIgnoreCase(QUOTENO))
			{
				sql = "SELECT  tm.TOLERANCE_NAME,mrd.TOLERANCE_CHARGES,tm.TOLERANCE_VALUE from MARINE_DATA md,TOLERANCE_MASTER tm,MARINE_RESULT_DETAILS mrd where md.TOLERANCE_ID=tm.TOLERANCE_ID and md.APPLICATION_NO=(select APPLICATION_NO from position_master where quote_no=?) and md.APPLICATION_NO=mrd.APPLICATION_NO and tm.BRANCH_CODE=?";
			}
			else
			{
				sql = "SELECT  tm.TOLERANCE_NAME,mrd.TOLERANCE_CHARGES,tm.TOLERANCE_VALUE from MARINE_DATA md,TOLERANCE_MASTER tm,MARINE_RESULT_DETAILS mrd where md.TOLERANCE_ID=tm.TOLERANCE_ID and md.APPLICATION_NO=(select APPLICATION_NO from position_master where policy_no=?) and md.APPLICATION_NO=mrd.APPLICATION_NO and tm.BRANCH_CODE=?";
			}
			final String args[] = {qno,loginBra};
			String result[][];
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getPolictDetails(String qno,String queryStatus, String loginBra)throws BaseException
	{
		logger.info("getPolictDetails=====>");
		logger.debug(ENTER);
		String obj[]={};
			String sql;
			if (queryStatus.equalsIgnoreCase(QUOTENO))
			{
				obj=new String[]{qno,qno,qno};
				sql = "select REGEXP_SUBSTR(?,'+[^-]+',1,1) polno,REGEXP_SUBSTR(?,'[^-]+',1,2) endtno,REGEXP_SUBSTR(?,'[^-]+',1,2) certno FROM DUAL";
			}
			else
			{
				obj=new String[]{qno,qno,qno,qno};
				sql = "select REGEXP_SUBSTR(?,'+[^-]+',1,1)||'-'||REGEXP_SUBSTR(?,'[^-]+',1,2) polno,REGEXP_SUBSTR(?,'[^-]+',1,3) certno, REGEXP_SUBSTR(?,'[^-]+',1,4) endtno FROM DUAL";
			}
			String result[][];
			result = runner.multipleSelection(sql,obj);
		logger.debug(EXIT);
		
		return result;
	}
	public Map checkingvalues(final String policyNo, final String loginCode,final String queryStatus,final String loginBra, 
			final String cid,final String freightUser)throws BaseException
	{
		logger.info("One Off finalPrint checkingvalues method()");
		logger.debug(ENTER);
		final rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();
		String querySyntax;
		StringBuffer fragileMessage;
		fragileMessage= new StringBuffer();
		int finalCount = 1;

		String pdfDisplayStatus[][];
		
		Map premiumdetails;
		premiumdetails = new HashMap();
		Map commoditydetails;
		commoditydetails= new HashMap();
		String commodityIds="";
		String dateQry;
		if (queryStatus.equalsIgnoreCase(QUOTENO)) {
			querySyntax = "quote_no='" + policyNo + "'";
			pdfDisplayStatus = pdis.getPreBankOptions(policyNo, loginCode,"draft");
			dateQry = "pm.entry_date";
		} else {
			querySyntax = "policy_no='" + policyNo + "'";
			pdfDisplayStatus = pdis.getPreBankOptions(policyNo, loginCode,"policy");
			dateQry = "pm.expiry_date";
		}
		String qry;
			qry = "select  md.mode_of_transport as TransportID,initcap(trim(md.warehouse_to_warehouse)) "
					+ "as WarehouseOption,initcap(md.transit_from) as TransitFrom,initcap(md.final_destination) "
					+ "as FinalDestination,to_char(md.inception_date,'dd-MM-yyyy') as "
					+ "PolicyStartDate,md.cover_id as CoverId,md.extra_cover_id as "
					+ "ExtraCoverId,mrd.commodity_id as CommodityID,mrd.sum_insured as "
					+ "CommoditySumInsured,(mrd.description) as CommodityDescription,"
					+ "cm.cover_name as CoverName,ecm.extra_cover_name as ExtraCoverName,"
					+ "mt.transport_description as TransportName,(com.commodity_name) as "
					+ "CommodityName,md.currency_name as CurrencyName,md.exchange_rate as "
					+ "ExchangeRate,md.no_of_items as NoOfItems,to_char(md.inception_date,'dd-MM-yyyy')"
					+ " as policyDay,initcap(md.AC_EXECUTIVE_NAME) as ACExecutiveName,"
					+ "stm.sale_term_value as SaleTermValue,stm.sale_term_name as SaleTermName,"
					+ "md.transit_from_country_id as TransitFromCountryId,"
					+ "md.final_destination_country_id as FinalDestinationCountryId,"
					+ "com.exclusion_id as ExclusionId,com.warranty_id as WarrantyId,"
					+ "mrd.suminsuredlocal as SumInsuredLocal,pm.quote_no as QuoteNo,"
					+ "pm.policy_no as PolicyNo,pm.application_no as ApplicationNo,"
					+ "pm.customer_id as CustomerId,mpd.carrier_name as CarrierName,"
					+ "mpd.BL_AWB_NO as BlAwbNo,to_char(mpd.BL_AWB_DATE,'dd-MM-yyyy') as BlAwbDate,mpd.lc_number "
					+ "as LcNumber,to_char(mpd.lc_date,'dd-MM-yyyy') as LcDate,initcap(mpd.bank_name) as BankName,"
					+ "initcap(mpd.settling_agent_name) as SettlingAgentNameOthers,nvl(mpd.settling_agent_id,'0')  "
					+ "as SettlingAgentId,initcap(sam.settling_agent_name) as SettlingAgentName,"
					+ "initcap(sam.address1) as SettlingAgentAddressOne,initcap(sam.address2) as "
					+ "SettlingAgentAddressTwo,sam.telephone_no as SettlingAgentTelephoneNo,"
					+ "sam.fax_no as SettlingAgentFaxNo,initcap(pi.first_name) as CustomerFirstName,"
					+ "initcap(pi.last_name) as CustomerLastName,initcap(pi.address1) as CustomerAddressOne,"
					+ "initcap(pi.address2) as CustomerAddressTwo,pi.telephone as CustomerTelephone,"
					+ "replace(initcap(pi.country),'Select','') as CustomerCountry,replace(initcap(nvl(pi.city,pi.emirate)),'Select','') as CustomerEmirate,"
					+ "initcap(bcm.COMPANY_NAME) as BrokerName,initcap(bcm.address1) as Brokeraddress1,"
					+ "initcap(bcm.address2) as BrokerAddress2,initcap(bcm.city) as BrokerCity,initcap(bcm.country) as "
					+ "BrokerCountry,bcm.phone as BrokerPhone,bcm.pobox as BrokerPoBox,"
					+ "initcap(nvl(bcm.city,bcm.emirate)) as BrokerEmirate,nvl(pm.DISCOUNT_PREMIUM,lud.COMMISSION) as BrokerCommission,"
					+ "lud.min_premium_amount as MinPremiumAmount,lm.login_id as LoginID,"
					+ "lm.agency_code as LoginAgencyCode,lm.oa_code as LoginOaCode,"
					+ "lud.oa_code as BrokerOaCode,lud.agency_code as BrokerAgencyCode,"
					+ "(mrd.package_description),mr.amount_payable,md.premium,md.transit_from_city_id,md.final_destination_city_id,(pm.remarks),(pm.admin_remarks),mrd.fragile,md.excess_premium,pi.pobox,upper(pi.company_name),md.sale_term_id,md.currency_id,md.sea_options,md.transit_from_city_id,md.final_destination_city_id,md.total_sum_insured,to_char(md.inception_date,'dd-MM-yyyy hh24:mi:ss'),(pm.broker_remarks),initcap(bcm.company_name),(mrd.supplier_name),mrd.invoice_number,initcap(trim(md.WARE_TO_WARE_FINAL_DEST)),TRUNC(TO_NUMBER(SUBSTR((md.entry_date+8/24-md.inception_date),1,INSTR(md.entry_date+8/24-md.inception_date,' ')))) as backDay,TRUNC(TO_NUMBER(SUBSTR((md.inception_date-mpd.BL_AWB_DATE),1,INSTR(md.inception_date-mpd.BL_AWB_DATE,' ')))) as BackBillDay,nvl(to_char(pm.inception_date,'dd/MM/yyyy HH24:MI:SS'),'NoDate'),nvl(pdf_modify_backdate,'NOTHING'),nvl(pdf_backdate_id,'0'),nvl(mrd.TOLERANCE_CHARGES,'0'),nvl(md.SEA_OPTIONS,' '),nvl(mpd.PARTIAL_SHIPMENT_ALLOWED,'N'),nvl(pm.APPLICATION_ID,'1'),nvl(ADMIN_REFERRAL_REMARKS,' '),nvl(to_char(pm.EFFECTIVE_DATE,'dd/MM/yyyy HH24:MI:SS'),'NoDate'),nvl(pm.APPROVED_BY,'Nil') APPROVED_BY,nvl(md.VIA_CITY_NAME,' '),nvl(md.VIA_COUNTRY_ID,'0'),(to_char(pm.entry_date,'dd-mm-yyyy')||' Time: '|| to_char(pm.entry_date,'HH12:MI:SS')),MD.POLICY_FEE,MRD.RATE,MRD.PREMIUM,MRD.WARRATE,MRD.WAR_CHARGES,MD.TOTAL_WAR_CHARGES,(NVL(MRD.SUMINSUREDLOCAL,'0')+NVL(mrd.SALE_TERM_CHARGES,'0')+NVL(MRD.TOLERANCE_CHARGES,'0')),to_char(mpd.SAILING_DATE,'dd/MM/yyyy') as SAILING_DATE,md.CURRENCY_ID,PM.COMMISSION,PM.PAYMENT_MODE,PM.BASIS_VAL,PM.DEBIT_CUST_ID,MD.MARINE_PREMIUM,MD.WAR_PREMIUM,MD.MIN_PRE_YN,pm.PDF_CURRENCY_STATUS, "+
					"  MPD.CONSIGNEE_DET,MPD.SPECIAL_TERM,MPD.ADD_CUST_NAME from marine_data md,marine_result_details mrd,"
					+ "cover_master cm,extra_cover_master ecm,mode_of_transport mt,"
					+ "commoditymaster com,sale_term_master stm,position_master pm,"
					+ "marine_policy_details mpd,settling_agent_master sam,personal_info pi,"
					+ "login_master lm,broker_company_master bcm,login_user_details lud,marine_result mr  "
					+ "where pm."
					+ querySyntax
					+ " and lm.login_id=? and "
					+ "pm.application_no=md.application_no and mpd.quote_no=pm.quote_no and "
					+ "mr.quote_no=pm.quote_no and "
					+ "sam.settling_agent_id=(select nvl(settling_agent_id,'0') from marine_policy_details mpds,position_master pms "
					+ " where pms.quote_no=mpds.quote_no and pms."
					+ querySyntax
					+ ") and  "
					+

					"pi.customer_id=pm.customer_id AND lud.oa_code=lm.oa_code and "
					+ "lud.agency_code=bcm.agency_code and pm.product_id=lud.product_id and md.status='Y' and cm.status='Y' and mt.status='Y' and ecm.status='Y' and stm.status='Y' and "
					+ "md.application_no=mrd.application_no and md.cover_id=cm.cover_id "
					+ "and mt.mode_transport_id=md.mode_of_transport and "
					+ "com.commodity_id=mrd.commodity_id and "
					+ "md.extra_cover_id=ecm.extra_cover_id and "
					+ "stm.sale_term_id=md.sale_term_id and com.branch_code=cm.branch_code and com.branch_code=ecm.branch_code and com.branch_code=mt.branch_code and com.branch_code=stm.branch_code "
					//+ " and com.branch_code=? "
					+ " and com.branch_code=(select belonging_branch from branch_master where branch_code=?) "
					+ " and sam.BELONGING_COUNTRY_ID=?"
					 +" and com.amend_id=(select max(amend_id) from commoditymaster where commodity_id=com.commodity_id and branch_code=com.branch_code)";

			final String args1[] = {loginCode,loginBra,cid};
			String[][] returnval;
			returnval = runner.multipleSelection(qry,args1);
			
			System.out.println("royal test one off bankName from final pritn java..."+qry);
			
			String exclusions = "";
			String warrantyIds = "";
			String extraCoverId="";
			String amountPay="";
			String finalPremium="";
			String commis = "";
			String miniPrem = "";
			String ExcessPre = "";
			if (returnval.length > 0 && !(policyNo.equalsIgnoreCase("0"))&& !(loginCode.equalsIgnoreCase("0"))) 
			{
				for (int i = 0; i < returnval.length; i++) 
				{
					premiumdetails.put("TransportID",returnval[0][0] == null ? "" : returnval[0][0]);
					transPortId = returnval[0][0] == null ? "": returnval[0][0];
					setModeOfTransport(returnval[0][0] == null ? ""	: returnval[0][0]);
					premiumdetails.put("warehouseasWarehouseOption",returnval[0][1] == null ? "NO" : returnval[0][1]);
					setWarehouse(returnval[0][1] == null ? "NO"	: returnval[0][1].trim());
					premiumdetails.put("TransitFrom", (returnval[0][2]==null?"":(returnval[0][2].equalsIgnoreCase("Others")?"":returnval[0][2])));
					setTransitFrom(returnval[0][2]);
					premiumdetails.put("FinalDestination", (returnval[0][3]==null?"":(returnval[0][3].equalsIgnoreCase("Others")?"":returnval[0][3])));
					setFinalDestination(returnval[0][3]);
					premiumdetails.put("PolicyStartDate", returnval[0][4]);
					coverId = returnval[0][5] == null ? "0" : returnval[0][5];
					setCoverId(coverId);
					extraCoverId = returnval[0][6] == null ? "0": returnval[0][6];
					setExtraCoverIdNew(extraCoverId);
					premiumdetails.put("ExtraCoverId", extraCoverId);
					premiumdetails.put("CommodityID", returnval[0][7]);
					premiumdetails.put("CoverName", returnval[0][10]);
					premiumdetails.put("ExtraCoverName", returnval[0][11]);
					premiumdetails.put("TransportName", returnval[0][12]);
					premiumdetails.put("CurrencyName", returnval[0][14]);
					premiumdetails.put("ExchangeRate", returnval[0][15]);
					setCurrencyValue(returnval[0][15]);
					premiumdetails.put("NoOfItems", returnval[0][16]);
					setNoOfItems(returnval[0][16]);
					premiumdetails.put("AcExecutiveName", returnval[0][18]);
					premiumdetails.put("SaleTermValue", returnval[0][19]);
					premiumdetails.put("SaleTermName", returnval[0][20]);
					premiumdetails.put("TransitFromCountryId", returnval[0][21]);
					//premiumdetails.put("NetPremium", returnval[0][119]);
					
					premiumdetails.put("PDFCurrencyStatus", returnval[0][119]);
					
					setTransitFromCountryId(returnval[0][21] == null ? "0": returnval[0][21]);
					
					premiumdetails.put("TransitCountryName", getStartingPlace(returnval[0][21], COUNTRY,cid)[0][0]);
					premiumdetails.put("WareHouseFinalOption",returnval[0][87] == null ? "NO" : returnval[0][87]);
					setWarehouseFinal(returnval[0][87] == null ? "NO": returnval[0][87].trim());
					String[][] startArray;
					startArray = getStartingPlace(returnval[0][21],"StartingPlace",cid);
					//regarding via modification
					String viaCityName;
					viaCityName = returnval[0][100]==null?"":returnval[0][100];
					String viaCountryId;
					viaCountryId = returnval[0][101]==null?"0":returnval[0][101];
					String viaCountryName = "";
					if(!"0".equals(viaCountryId)){
						viaCountryName = getStartingPlace(viaCountryId, COUNTRY,cid)[0][0];
					}
					viaCityName = viaCityName.trim();
					if(viaCountryName.length()>0&&viaCityName.length()>0)
					{
						viaCountryName = viaCountryName +", "+ viaCityName;
					}
					if(viaCountryName.length()>0){
						premiumdetails.put("viaCountryName",viaCountryName);
					}
					//regarding via modification
					String starCountryPlace = "";
					if (startArray.length > 0) 
					{
							
						if ("NO".equalsIgnoreCase(getWarehouse().trim())|| " NO".equalsIgnoreCase(getWarehouse().trim())							|| "NO ".equalsIgnoreCase(getWarehouse().trim()) || " NO ".equalsIgnoreCase(getWarehouse()
										.trim())|| "NO".equalsIgnoreCase(getWarehouse())) 
						{
							starCountryPlace = "Any Port";
							startWarranties = startArray[0][1];
						} else if (YES
								.equalsIgnoreCase(getWarehouse().trim())
								|| " YES".equalsIgnoreCase(getWarehouse()
										.trim())
								|| "YES ".equalsIgnoreCase(getWarehouse()
										.trim())
								|| " YES ".equalsIgnoreCase(getWarehouse()
										.trim())
								|| YES.equalsIgnoreCase(getWarehouse())) {
							starCountryPlace = "Warehouse";
							startWarranties = "0";
						} else {
							starCountryPlace = startArray[0][0];
							startWarranties = startArray[0][1];
						}
					}
					premiumdetails.put("TransitStartingPlace",starCountryPlace);
					premiumdetails.put("FinalDestinationCountryId",returnval[0][22]);
					setFinalDestinationCountryId(returnval[0][22] == null ? "0"	: returnval[0][22]);
					premiumdetails.put("FinalDestinationCountryName",getStartingPlace(returnval[0][22],	COUNTRY,cid)[0][0]);
					String[][] destinationArray;
					destinationArray = getStartingPlace(returnval[0][22],"EndingPlace",cid);
					String destCountryPlace = "";
					
					if (destinationArray.length > 0) 
					{
						if ("NO".equalsIgnoreCase(getWarehouseFinal().trim())
								|| " NO".equalsIgnoreCase(getWarehouseFinal()
										.trim())
								|| "NO ".equalsIgnoreCase(getWarehouseFinal()
										.trim())
								|| " NO ".equalsIgnoreCase(getWarehouseFinal()
										.trim())
								|| "NO".equalsIgnoreCase(getWarehouseFinal())) {
							destCountryPlace = "Any Port";
							destWarranties = destinationArray[0][1];
						}
						else if (YES.equalsIgnoreCase(getWarehouseFinal().trim()) || " YES".equalsIgnoreCase(getWarehouseFinal().trim()) || "YES ".equalsIgnoreCase(getWarehouseFinal().trim())|| " YES ".equalsIgnoreCase(getWarehouseFinal().trim())|| YES.equalsIgnoreCase(getWarehouseFinal())) 
						{
							destCountryPlace = "Warehouse";
							destWarranties = "0";
						}
						else
						{
							destCountryPlace = destinationArray[0][0];
							destWarranties = destinationArray[0][1];
							if ("0".equalsIgnoreCase(destWarranties == null ? "0": destWarranties)) {
								premiumdetails.put("destWarranties", "NO");
							} else {
								premiumdetails.put("destWarranties", YES);
							}
							
						}
					}
					
					premiumdetails.put("FinalStartingPlace",destCountryPlace);
					premiumdetails.put("QuoteNo", returnval[0][26]);
					setQuoteId(returnval[0][26]);
					premiumdetails.put("PolicyNo", returnval[0][27]);
					premiumdetails.put("ApplicationNo", returnval[0][28]);
					premiumdetails.put("CustomerId", returnval[0][29]);
					premiumdetails.put("CarrierName", returnval[0][30]);
					premiumdetails.put("BlAwbNo", returnval[0][31]);
					premiumdetails.put("BlAwbDate", returnval[0][32]);
					premiumdetails.put("LcNumber", returnval[0][33]);
					premiumdetails.put("LcDate", returnval[0][34]);
					
					premiumdetails.put("BankName", (returnval[0][35]==null?"":returnval[0][35].trim()));
					premiumdetails.put("SettlingAgentNameOthers",returnval[0][36]);
					premiumdetails.put("SettlingAgentId", returnval[0][37]);
					premiumdetails.put("SettlingAgentName", returnval[0][38]);
					premiumdetails.put("SettlingAgentAddressOne",returnval[0][39]);
					premiumdetails.put("SettlingAgentAddressTwo",returnval[0][40]);
					premiumdetails.put("SettlingAgentTelephoneNo",returnval[0][41]);
					premiumdetails.put("SettlingAgentFaxNo", returnval[0][42]);
					premiumdetails.put("CustomerFirstName", returnval[0][43]);
					premiumdetails.put("CustomerLastName", returnval[0][44]);
					premiumdetails.put("CustomerAddressOne", returnval[0][45]==null?"":returnval[0][45]);
					premiumdetails.put("CustomerAddressTwo", returnval[0][46]);
					premiumdetails.put("CustomerTelephone", returnval[0][47]==null?"":returnval[0][47]);
					premiumdetails.put("CustomerCountry", returnval[0][48]);
					premiumdetails.put("CustomerEmirate", returnval[0][49]==null?"":returnval[0][49]);
					//premiumdetails.put("BrokerName",getLoginUserDetails(loginCode));
					premiumdetails.put("BrokerName",returnval[0][50]);
					premiumdetails.put("Brokeraddress1", returnval[0][51]);
					premiumdetails.put("BrokerAddress2", returnval[0][52]);
					premiumdetails.put("BrokerCity", returnval[0][53]);
					premiumdetails.put("BrokerCountry", returnval[0][54]);
					premiumdetails.put("BrokerPhone", returnval[0][55]);
					premiumdetails.put("BrokerPoBox", returnval[0][56]);
					premiumdetails.put("BrokerEmirate", returnval[0][57]);
					//here RSA Issuer
					String rsaissuer;
					rsaissuer = returnval[0][96]== null?"1":returnval[0][96];
					if(freightUser.equalsIgnoreCase("None")){
						commis = returnval[0][58] == null ? "0" : returnval[0][58];
						/*if(!rsaissuer.equalsIgnoreCase("1"))
						{
							double rsaDisCom = 0;
							rsaDisCom = getRSAIssuerCommission(loginCode,rsaissuer,"3",policyNo);
							commis = Double.toString(rsaDisCom);
						}*/
					}else if(freightUser.equalsIgnoreCase("Freight"))
					{
						commis = freightUserCommission(policyNo,queryStatus,loginCode);
					}
					premiumdetails.put("BrokerCommission",commis);
					premiumdetails.put("MinPremiumAmount",returnval[0][59] == null ? "0" : returnval[0][59]);
					premiumdetails.put("LoginID", returnval[0][60]);
					premiumdetails.put("LoginAgencyCode", returnval[0][61]);
					premiumdetails.put("LoginOaCode", returnval[0][62]);
					premiumdetails.put("BrokerOaCode", returnval[0][63]);
					premiumdetails.put("BrokerAgencyCode", returnval[0][64]);
					premiumdetails.put("AmountPayable",	returnval[0][66] == null ? "0" : returnval[0][66]);
					premiumdetails.put("Premium",returnval[0][67] == null ? "0" : returnval[0][67]);
					premiumdetails.put("Remarks",returnval[0][70]);
					premiumdetails.put("AdminRemarks", (returnval[0][71]==null?"":(returnval[0][71]+"\n"))+(returnval[0][97]==null?"":returnval[0][97]));
					premiumdetails.put("ExcessPremium",returnval[0][73] == null ? "0" : returnval[0][73]);
					premiumdetails.put("CustomerPoBox", returnval[0][74]);
					premiumdetails.put("CustomerCompanyName", returnval[0][75]);
					premiumdetails.put("BrokerRemarks",	returnval[0][83] == null ? "": returnval[0][83]);
					premiumdetails.put("BrokerCompany",	returnval[0][84] == null ? "" : returnval[0][84]);
					premiumdetails.put("SeaOption",returnval[0][94] == null ? "" : returnval[0][94]);
					premiumdetails.put("PartialShip",returnval[0][95] == null ? "" : returnval[0][95]);
					premiumdetails.put("BackDays",returnval[0][88] == null ? "0" : returnval[0][88]);
					
					if (Integer.parseInt(returnval[0][88] == null ? "0":returnval[0][88]) > countBackDays || Integer.parseInt(returnval[0][89] == null ? "0":returnval[0][89]) > countBackDays) 
					{
						premiumdetails.put("BackDaysOption", YES);
					} 
					else 
					{
						premiumdetails.put("BackDaysOption", "NO");
					}

					premiumdetails.put("PolicyGeneratedDate",returnval[0][90] == null ? "" : returnval[0][90]);
					premiumdetails.put("QuoteGeneratedDate",returnval[0][102]==null?"0":returnval[0][102]);
					premiumdetails.put("ActualPolicyGeneratedDate",returnval[0][98] == null ? "" : returnval[0][98]);
					premiumdetails.put("APPROVEDBY",returnval[0][99]==null?"":returnval[0][99]);
					premiumdetails.put("PdfSubjectKnownText",returnval[0][91] == null ? NOTHING:returnval[0][91]);
					premiumdetails.put("PdfSubjectKnownId",returnval[0][92] == null ? "0" : returnval[0][92]);
					premiumdetails.put("IssuanceFee",returnval[0][103] == null ? "0" : returnval[0][103]);
					premiumdetails.put("totalWarPremium",returnval[0][108] == null ? "0" : returnval[0][108]);
					setPremium(returnval[0][67] == null ? "0": returnval[0][67]);
					setExcessPremium(returnval[0][73] == null ? "0": returnval[0][73]);
					setSaleTerm(returnval[0][76]);
					setCurrencyId(returnval[0][77]);
					setSeaOptions(returnval[0][78] == null ? "0": returnval[0][78]);
					setFinalDestinationCityId(returnval[0][80] == null ? "0": returnval[0][80]);
					setTransitFromCityId(returnval[0][79] == null ? "0": returnval[0][79]);
					setTotalSumInsured(returnval[0][81] == null ? "0": returnval[0][81]);
					setPolicyDate(returnval[0][82]);
					
					amountPay = returnval[0][66] == null ? "0": returnval[0][66];
					finalPremium=returnval[0][67] == null ? "0": returnval[0][67];
					miniPrem = returnval[0][59] == null ? "0": returnval[0][59];
					ExcessPre = returnval[0][73] == null ? "0": returnval[0][73];
					String sumLocal;
					sumLocal = returnval[i][25] == null ? "0": returnval[i][25];
					String commSumIns;
					commSumIns = returnval[i][8] == null ? "0" : returnval[i][8];
					double saleTermValue;
					saleTermValue= Double.parseDouble(isNull(returnval[i][19],"0"));
					totCommSumIns = Double.parseDouble(commSumIns)+ totCommSumIns;
					totSumLocal = ((Double.parseDouble(sumLocal) * (saleTermValue / 100)) + (Double.parseDouble(sumLocal))) +Double.parseDouble(returnval[i][93] == null ? "0": returnval[i][93])+ totSumLocal;

					commoditydetails.put("CommoditySumInsured" + (finalCount),returnval[i][8]);
					commoditydetails.put("CommodityDescription" + (finalCount),returnval[i][9]);
					commoditydetails.put("PackageDescription_arr"+ (finalCount), returnval[i][65]);
					commoditydetails.put("CommodityName_arr" + (finalCount),returnval[i][13]);
					commoditydetails.put("SaleTermName_arr" + (finalCount),returnval[i][20]);
					commoditydetails.put("ExclusionId_arr" + (finalCount),returnval[i][23]);
					commoditydetails.put("Supplier_arr" + (finalCount),isNull(returnval[i][85],""));
					commoditydetails.put("Invoice_arr" + (finalCount),isNull(returnval[i][86],""));
					if (("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId))
							|| ("1".equalsIgnoreCase(transPortId) && ("2".equalsIgnoreCase(coverId) || "3"			.equalsIgnoreCase(coverId)))) 
					{
						exclusions = exclusions + "," + "0";
					}
					else
					{
						if(returnval[i][23]!=null)
						{
							exclusions = exclusions + "," + returnval[i][23];
						}
					}
					
					commoditydetails.put("WarrantyId_arr" + (finalCount),returnval[i][24]);

					if ("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId)) 
					{
						warrantyIds = warrantyIds + "," + "0";
					} 
					else
					{
						if(returnval[i][24]!=null)
						{
						warrantyIds = warrantyIds + "," + returnval[i][24];
						}
					}

					
					commoditydetails.put("SumInsuredLocal_arr" + (finalCount),returnval[i][25]);
					if ("on".equalsIgnoreCase(returnval[i][72])) 
					{
						fragileMessage.append("displayFragileMessage");
					}
					//Added by sathish Start
					commoditydetails.put("PremiumRate" + (finalCount),returnval[i][104]);
					commoditydetails.put("PremiumSingle" + (finalCount),returnval[i][105]);
					commoditydetails.put("WarRate" + (finalCount),returnval[i][106]);
					commoditydetails.put("WarPremium" + (finalCount),returnval[i][107]);
					commoditydetails.put("SumInsuredLocal" + (finalCount),returnval[0][109] == null ? "0" : returnval[0][109]);
					//Added by sathish End
					premiumdetails.put("sailDate",isNull(returnval[0][110],""));
					premiumdetails.put("currencyId",isNull(returnval[0][111],""));
					premiumdetails.put("commissionAmt",isNull(returnval[0][112],""));
					premiumdetails.put("paymentMode",isNull(returnval[0][113],""));
					premiumdetails.put("basisVal",isNull(returnval[0][114],""));
					premiumdetails.put("debitCustomerId",isNull(returnval[0][115],""));
					premiumdetails.put("MARINE_PREMIUM",isNull(returnval[0][116],""));
					premiumdetails.put("WAR_PREMIUM",isNull(returnval[0][117],""));
					premiumdetails.put("MIN_PRE_YN",isNull(returnval[0][118],""));
					premiumdetails.put("CONSIGNEE_DET",isNull(returnval[0][120],""));
					premiumdetails.put("SPECIAL_TERM",isNull(returnval[0][121],""));
					premiumdetails.put("ADD_CUST_NAME",isNull(returnval[0][122],""));
					commodityIds+=returnval[i][7]+",";
					finalCount = finalCount + 1;
				}
				
				String qry1;
				qry1 = "select round(sum(mrd.premium),2) ,round(sum(mrd.war_charges),2) from marine_result_details mrd,position_master pm where mrd.application_no=pm.application_no and pm.policy_no=?";
				final String args3[] = {policyNo};
				String[][] returnval1;
				returnval1 = runner.multipleSelection(qry1,args3);
				System.out.println("royal test one off bankName from final pritn java..."+qry1);
				
				for (int i = 0; i < returnval1.length; i++) 
					{
						premiumdetails.put("finalmarinepremium",isNull(returnval1[0][0],""));
						premiumdetails.put("finalwarpremium",isNull(returnval1[0][1],""));
					}
				
				// for
				
				//Begin block for Commodity wise Clauses, Exclusions, Warranties, War
				/*if(commodityIds.length()>1)
					commodityIds=commodityIds.substring(0, commodityIds.length()-1);
				String id[];
				String ids="",clauseIds="", extraCoverIds="", deductibleIds="";
				String coverName=runner.singleSelection("select description from cover_master where cover_id=? and status='Y'  and BRANCH_CODE=?",new String[]{coverId, loginBra});
				String[][] idList=runner.multipleSelection("SELECT A."+coverName+" FROM COMMODITYMASTER A WHERE A.BRANCH_CODE='"+loginBra+"' AND A.COMMODITY_ID IN ("+commodityIds+") AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM COMMODITYMASTER WHERE BRANCH_CODE='"+loginBra+"' AND COMMODITY_ID=A.COMMODITY_ID)");
				if(idList!=null && idList.length>0)
				{
					for (int j = 0; j < idList.length; j++) {
						ids=idList[j][0]==null?"":idList[j][0];
						if(ids.length()>0)
						{
							id=ids.split("~");
							if(id.length>0)
							{
								clauseIds+=id[0].length()>0?(","+id[0]):"";
								exclusions+=id[1].length()>0?(","+id[1]):"";
								warrantyIds+=id[2].length()>0?(","+id[2]):"";
								extraCoverIds+=id[3].length()>0?(","+id[3]):"";
								deductibleIds+=id[4].length()>0?(","+id[4]):"";
							}
						}
					}
				}
				if(clauseIds.length()>0){
					clauseIds=clauseIds.substring(1,clauseIds.length());
				}else{
					clauseIds="0";
				}
				if(extraCoverIds.length()>0){
					extraCoverIds=extraCoverIds.substring(1,extraCoverIds.length());
				}else{
					extraCoverIds="0";
				}
				if(deductibleIds.length()>0){
					deductibleIds=deductibleIds.substring(1,deductibleIds.length());
				}else {
					deductibleIds="0";
				}
				premiumdetails.put("deductible", deductibleIds);
				//End of Block
				
				String conClausesIds = "";
				String conExClausesIds = "";
				String conExtraIds = "";
				String conWarClaIds = "";
				
				String pdfClauses,pdfexClauses,pdfWars,pdfEx;
				pdfClauses = isNull(pdfDisplayStatus[0][2],NOTHING);
				pdfexClauses = isNull(pdfDisplayStatus[0][5],NOTHING);
				pdfWars = isNull(pdfDisplayStatus[0][3],NOTHING);
				pdfEx = isNull(pdfDisplayStatus[0][4],NOTHING);

				

				if (NOTHING.equalsIgnoreCase(pdfClauses)) {
					conClausesIds = "0";
					premiumdetails.put("editedClauses",  new String[0][0]);
				} else {
					String pdfDisplayClauses[][];
					pdfDisplayClauses = pdis.makeTwoDimArray(pdfClauses);
					for (int i = 0; i < pdfDisplayClauses.length; i++) {
						conClausesIds = conClausesIds	+ "," + isNull(pdfDisplayClauses[i][0],"0");
					}
					conClausesIds = conClausesIds.substring(1,conClausesIds.length());
					premiumdetails.put("editedClauses", pdfDisplayClauses);
				}
				if (NOTHING.equalsIgnoreCase(pdfexClauses)) {
					conExtraIds = "0";
					premiumdetails.put("editedExtraClauses",new String[0][0]);
				} else {
					String pdfExtraClauses[][];
					pdfExtraClauses = pdis.makeTwoDimArray(pdfexClauses);

					for (int i = 0; i < pdfExtraClauses.length; i++) 
					{
						conExtraIds = conExtraIds+ ","+ isNull(pdfExtraClauses[i][0],"0");
					}
					conExtraIds = conExtraIds.substring(1,conExtraIds.length());
					premiumdetails.put("editedExtraClauses",pdfExtraClauses);
				}

				if (NOTHING.equalsIgnoreCase(pdfWars)) {
					conWarClaIds = "0";
					premiumdetails.put("editedWarClauses", new String[0][0]);
				} else {
					String pdfWarranties[][];
					pdfWarranties = pdis.makeTwoDimArray(pdfWars);

					for (int i = 0; i < pdfWarranties.length; i++) {

						if ("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId)) {
							conWarClaIds = conWarClaIds + ","+ "0";
						} else {
							conWarClaIds = conWarClaIds	+ "," + isNull(pdfWarranties[i][0],"0");
						}
					}
					conWarClaIds = conWarClaIds.substring(1,conWarClaIds.length());
					premiumdetails.put("editedWarClauses", pdfWarranties);
				}
				if (NOTHING.equalsIgnoreCase(pdfEx)) {
					conExClausesIds = "0";
					premiumdetails.put("editedExClauses", new String[0][0]);
				} else {
					String pdfExclusions[][];
					pdfExclusions = pdis.makeTwoDimArray(pdfEx);
					for (int i = 0; i < pdfExclusions.length; i++) {
						if (("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId))
								|| ("1".equalsIgnoreCase(transPortId) && ("2".equalsIgnoreCase(coverId) || "3"
										.equalsIgnoreCase(coverId)))) {
							conExClausesIds = conExClausesIds + "," + "0";
						} else {
							conExClausesIds = conExClausesIds
									+ ","
									+ (pdfExclusions[i][0] == null ? "0": pdfExclusions[i][0]);
						}

					}
					conExClausesIds = conExClausesIds.substring(1,conExClausesIds.length());
					premiumdetails.put("editedExClauses", pdfExclusions);
				}
				if(exclusions.length()>0)
				{
				exclusions = exclusions.substring(1, exclusions.length());
				}else
				{
					exclusions="''";
				}
				warrantyIds = warrantyIds + "," + startWarranties;
				warrantyIds = warrantyIds + "," + destWarranties;
				warrantyIds = warrantyIds.substring(1, warrantyIds.length());
				
				String sqll;
				sqll = "select exclusion_description from exclusion_master where exclusion_id in "
						+ "("
						+ exclusions
						+ ") and exclusion_id not in ("
						+ conExClausesIds
						+ ") and status in ('Y','R') and branch_code='"+loginBra+"'   order by exclusion_id";

				String[][] resultExclusion;
				resultExclusion = runner.multipleSelection(sqll);
				commoditydetails.put("exclusionsDesc", resultExclusion);

				String sqll1;
				sqll1 = "select warranty_description from warranty_master where warranty_id in "
						+ "("
						+ warrantyIds
						+ ") and warranty_id not in ("
						+ conWarClaIds
						+ ") and status in ('Y','R') and branch_code='"+loginBra+"' order by WARRANTY_ID";

				String[][] resultWarranty;
				resultWarranty = runner.multipleSelection(sqll1);
				commoditydetails.put("warrantyDesc", resultWarranty);

				if (transPortId.equalsIgnoreCase("3")) {
					extraCoverId = "0";
				}
				if (extraCoverId.equalsIgnoreCase("0")) 
				{
					String sql23;
//					sql23 = "select clauses_description from clauses_master where cover_id=? and extra_cover_id='0' and clauses_id not in("+ conClausesIds+ ") and status in ('Y','R') and branch_code=? order by clauses_id";
					sql23 = "select clauses_description from clauses_master where cover_id=? and extra_cover_id='0' and rsacode in("+ clauseIds+ ") and clauses_id not in("+ conClausesIds+ ") and status in ('Y','R') and branch_code=? order by display_order";
					final String args[] = {coverId,loginBra};
					String[][] resultClauses;
					resultClauses = runner.multipleSelection(sql23,args);
					commoditydetails.put("clausesDesc", resultClauses);
					commoditydetails.put("extraClausesDesc", new String[0][0]);
				} 
				else 
				{
					String sql12;
//					sql12 = "select clauses_description from clauses_master where cover_id=? and clauses_id not in("+ conClausesIds+ ") and extra_cover_id='0' and status in ('Y','R') and branch_code=? order by clauses_id";
					sql12 = "select clauses_description from clauses_master where cover_id=? and extra_cover_id='0' and rsacode in("+ clauseIds+ ") and clauses_id not in("+ conClausesIds+ ") and status in ('Y','R') and branch_code=? order by display_order";
					final String args[] ={coverId,loginBra};
					String[][] resultClauses;
					resultClauses = runner.multipleSelection(sql12,args);
					commoditydetails.put("clausesDesc", resultClauses);
				
					String sql22;
//					sql22 = "select clauses_description from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id=?) and clauses_id not in("+conExtraIds+") and status in ('Y','R') and branch_code=? order by clauses_id";
					sql22 = "select clauses_description from clauses_master where (cover_id in('"+coverId+"') and  extra_cover_id=?) and rsacode in("+extraCoverIds+") and clauses_id not in("+conExtraIds+") and status in ('Y','R') and branch_code=? order by display_order";
					final String args2[] = {extraCoverId,loginBra};
					
					String[][] resExtraClause;
					resExtraClause = runner.multipleSelection(sql22,args2);
					commoditydetails.put("extraClausesDesc", resExtraClause);
				}*/
				Map<String, Object> conditions=new policyInfo().getConditions((String)premiumdetails.get("ApplicationNo"), loginBra);
				commoditydetails.putAll(conditions);
				premiumdetails.put("deductible", conditions.get("deductible"));
				finalCount = finalCount - 1;
				commoditydetails.put("finalCount", Integer.toString(finalCount));
				commoditydetails.put("TotalCommoditySI", Double.toString(totCommSumIns));
				commoditydetails.put("TotalSILocal", Double.toString(totSumLocal));
				commoditydetails.put("FragileMessage", fragileMessage.toString());
				premiumdetails.put("AmountDetails", getFinalTotal(amountPay,commis, miniPrem, ExcessPre));
				premiumdetails.put("commoditydetails", commoditydetails);
				premiumdetails.put("CheckPolicy", "DATAS");
				premiumdetails.put("FinalPremium", finalPremium);
				
				
				if (queryStatus.equalsIgnoreCase(QUOTENO)) 
				{
					insertDraftTrackingDetails(policyNo);
				}
			} 
			else 
			{
				premiumdetails.put("CheckPolicy", "NODATAS");
			}
		logger.debug(EXIT);
		
		return premiumdetails;
	}

	public String[][] getStartingPlace(final String CountryId, final String queryStatus,final String cid)throws BaseException
	{
		logger.info("One Off finalPrint getStartingPlace method()");
		logger.debug(ENTER);
			final String args[] = {cid,cid,CountryId};
			StringBuffer sql;
			sql = new StringBuffer();
			if (queryStatus.equalsIgnoreCase(COUNTRY)){
				sql.append("select initcap(cm.country_name),cm.COUNTRY_ID from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?");
			}else if (queryStatus.equalsIgnoreCase("StartingPlace")){
				sql.append("select initcap(cm.starting_place),nvl(cm.SP_WARRANTIES_CONDITIONS,'0') from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?");
			}else if (queryStatus.equalsIgnoreCase("EndingPlace")){
				sql.append("select initcap(cm.ENDING_PLACE),nvl(cm.EP_WARRANTIES_CONDITIONS,'0') from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||'-'||cm.country_id in(select max(amend_id)||'-'||country_id  from COUNTRY_MASTER_DETAIL where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and cm.country_id=?");
			}
			String[][] result;
			result = runner.multipleSelection(sql.toString(),args);
		logger.debug(EXIT);
		
		return result;
	}
	public String getIssuerName(final String qno,final String queryStatus)throws BaseException
	{
		logger.info("One Off finalPrint getIssuerName method()");
		logger.debug(ENTER);
			StringBuffer sql;
			sql = new StringBuffer();
			String result1;
			if (queryStatus.equalsIgnoreCase(QUOTENO))
			{
				sql.append("select nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO=?");
			}
			else if (queryStatus.equalsIgnoreCase("POLICYNO"))
			{
				sql.append("select nvl(APPLICATION_ID,'1') from POSITION_MASTER where POLICY_NO=?");
			}
			final String args[] = {qno};
			result1 = runner.singleSelection("SELECT NVL (USERNAME, '') FROM login_master where LOGIN_ID=("+sql.toString()+") and agency_code is not null and oa_code is not null",args);
		logger.debug(EXIT);
		
		return result1;
	}
	public double getRSAIssuerCommission(final String login,final String rsaIssuer,final String pid,final String polNo)throws BaseException
	{
		logger.info("One Off finalPrint getRSAIssuerCommission method()");
		logger.debug(ENTER);
			com.maan.services.policyInfo pol;
			pol = new com.maan.services.policyInfo();
			double res;
			res = pol.getRSAIssuerCommission(login,rsaIssuer,pid,polNo);
		logger.debug(EXIT);
		
		return res;
	}
	public String[][] getPlaceCodes(final String policyNo,final String option,final String pid,final String queryStatus)throws BaseException
	{
		logger.info("One Off finalPrint getPlaceCodes method()");
		logger.debug(ENTER);
			String sql;
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
					rsa.opencoverpdf.finalprint pdfBean;pdfBean = new rsa.opencoverpdf.finalprint();
					String[][] multiQuotes;
					multiQuotes = pdfBean.getPolicyQuotes(policyNo,queryStatus);
					querySyntax = "quote_no='"+multiQuotes[0][0]+"'";
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
			if(option.equalsIgnoreCase("Debit")){
				sql = "select BRANCH_NAME,header_img,footer_img,sign_img,stamp,Currency_name,CURRENCY_ABBREVIATION,nvl(CURRENCY_ACRONYM,' '),CURRENCY_DECIMAL_NAME,CURRENCY_DECIMAL_DIGIT,ADDRESS1,REMARKS from BRANCH_MASTER where " +
						"BRANCH_CODE=(select distinct branch_Code from broker_Company_master where agency_Code=(select oa_code from login_master " +
						"where login_id in(select login_id from position_master where "+querySyntax+")))";
			}else{
				
				sql = "select BRANCH_NAME,header_img,footer_img,sign_img,stamp,Currency_name,CURRENCY_ABBREVIATION,nvl(CURRENCY_ACRONYM,' '),ADDRESS1,REMARKS,ADDRESS2,CITY,COUNTRY,PHONE,FAX from BRANCH_MASTER where " +
						"BRANCH_CODE=(select distinct branch_Code from broker_Company_master where agency_Code=(select oa_code from login_master " +
						"where login_id in(select login_id from position_master where "+querySyntax+")))";
			}
			String result[][];
			result = runner.multipleSelection(sql);
		logger.debug(EXIT);
		
		return result;
	}
	public String getStampStatus(final String check,final String PolicyNo)throws BaseException
	{
		logger.info("One Off finalPrint getStampStatus method()");
		logger.debug(ENTER);
			final String args1[] = {PolicyNo};
			String result;
			result = runner.singleSelection("select nvl(home.PDF_Stamp_Status,' ') from POSITION_MASTER home where "+check,args1);
		logger.debug(EXIT);
		
		return result;
	}
	public boolean pdfCheckingStatus(final String policyNo, final String loginCode,final String queryStatus,
			final String loginBra,final String cid)throws BaseException
	{
			
		logger.info("One Off finalPrint PDFCheckingStatus method()");
		logger.debug(ENTER);
		String querySyntax;
		String dateQry;
		if (queryStatus.equalsIgnoreCase(QUOTENO)) {
			querySyntax = "quote_no='" + policyNo + "'";
			dateQry = "pm.entry_date";
		}
		else{ 
			querySyntax = "policy_no='" + policyNo + "'";
			dateQry = "pm.expiry_date";
		}
		boolean checkFlag;
		String qry;
			qry = "select  count(*) from marine_data md,marine_result_details mrd,"
					+ "cover_master cm,extra_cover_master ecm,mode_of_transport mt,"
					+ "commoditymaster com,sale_term_master stm,position_master pm,"
					+ "marine_policy_details mpd,settling_agent_master sam,personal_info pi,"
					+ "login_master lm,broker_company_master bcm,login_user_details lud,marine_result mr  "
					+ "where pm."
					+ querySyntax
					+ " and lm.login_id=? and "
					+ "pm.application_no=md.application_no and mpd.quote_no=pm.quote_no and "
					+ "mr.quote_no=pm.quote_no and "
					+ "sam.settling_agent_id=(select nvl(settling_agent_id,'0') from marine_policy_details mpds,position_master pms "
					+ " where pms.quote_no=mpds.quote_no and pms."
					+ querySyntax
					+ ") and  "
					+

					"pi.customer_id=pm.customer_id  and lud.oa_code=lm.oa_code and "
					+ "lud.agency_code=bcm.agency_code and pm.product_id=lud.product_id and md.status='Y' and cm.status='Y' and mt.status='Y' and ecm.status='Y' and stm.status='Y' and "
					+ "md.application_no=mrd.application_no and md.cover_id=cm.cover_id "
					+ "and mt.mode_transport_id=md.mode_of_transport and "
					+ "com.commodity_id=mrd.commodity_id and "
					+ "md.extra_cover_id=ecm.extra_cover_id and "
					+ "stm.sale_term_id=md.sale_term_id and com.branch_code=cm.branch_code and com.branch_code=ecm.branch_code and com.branch_code=mt.branch_code and com.branch_code=stm.branch_code "
					//+ " and com.branch_code=? "
					+ " and com.branch_code=(select belonging_branch from branch_master where branch_code=?) "
					+ " and sam.BELONGING_COUNTRY_ID=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where status in ('Y','R') "
					//+ " and branch_code=? "
					+ " and branch_code=(select belonging_branch from branch_master where branch_code=?) "
					+ " group by commodity_id)";
					//+ "stm.sale_term_id=md.sale_term_id and com.branch_code=cm.branch_code and com.branch_code=ecm.branch_code and com.branch_code=mt.branch_code and com.branch_code=stm.branch_code and com.branch_code=? and sam.BELONGING_COUNTRY_ID=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where effective_date <="+dateQry+" and status in ('Y','R') and branch_code=? group by commodity_id)";
			
			final String args1[] = {loginCode,loginBra,cid,loginBra};
			logger.info("royal test PDF checking status first for policyNo.."+policyNo+"....sql..is.."+qry);
			String returnval[][];
			returnval = runner.multipleSelection(qry,args1);
			if (returnval.length > 0 && !(policyNo.equalsIgnoreCase("0"))&& !(loginCode.equalsIgnoreCase("0"))) 
			{
				if("0".equals(returnval[0][0])){
					checkFlag = true;
				}else{
					checkFlag = false;
				}
			}
			else{
				checkFlag = true;
			}
		logger.debug(EXIT);
		
		return checkFlag;
	}
	public String updateCommission(final String broBra,final String pid,final String policyNo,final String comAmt,
			final double taxPercent) throws BaseException
	{
		logger.info("One Off finalPrint updateCommission method()");
		logger.debug(ENTER);
				String sql;
				String proPremium = "";
				double proAmt;
				double taxAmt = 0;
				String debitNoteNo = "";
				String args[] = {policyNo};
				sql = "select debit_note_no,(nvl(premium,'0')+nvl(excess_premium,'0')) from position_master where policy_no=?";
				String result[][];
				result = runner.multipleSelection(sql,args);
				if(result.length>0)
				{
					debitNoteNo = result[0][0];
					proPremium = result[0][1];
				}
				/*String proCommissions;
				proCommissions = getProCommission(broBra,pid,policyNo);
				if(proCommissions==null||proCommissions.length()<=0){
					proCommissions = "0";
				}
				proAmt = Double.parseDouble(proPremium)*Double.parseDouble(proCommissions)/100;
				if(taxPercent>0){
					taxAmt = Double.parseDouble(proPremium)*taxPercent/100;
				}
				String args1[] = {(comAmt != null ? comAmt.replaceAll(",", "") : "0"),Double.toString(proAmt),
						Double.toString(taxAmt),policyNo};
				runner.multipleUpdation("update position_master set commission=?,PRO_COMMISSION=?,TAX=? where policy_no=?",args1);*/
		logger.debug(EXIT);
		
		return debitNoteNo;
	}
	public String getCreditNoteNo(String policyNo)
	{
		logger.info("getCreditNoteNo - Enter || policyNo: "+policyNo);
		String result=runner.singleSelection("SELECT CREDIT_NOTE_NO FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
		logger.info("getCreditNoteNo - Enter || Result: "+result);
		return 	result;
	}
	public String[][] getComExcessPre(final String qno,final String displayMode, final String openCoverNo)throws BaseException
	{
		logger.info("One Off finalPrint getComExcessPre method()");
		logger.debug(ENTER);
			String sql;
			if("draftMode".equalsIgnoreCase(displayMode) || "NormalSupplement".equalsIgnoreCase(displayMode)){
				sql = "SELECT COMMODITY_EXCESS_PREMIUM, DESCRIPTION, (SELECT CURRENCY_NAME FROM OPEN_COVER_DEDUCTIBLE_MASTER A, OPEN_COVER_POSITION_MASTER B WHERE NVL (SUMINSUREDLOCAL, 0) + NVL (SALE_TERM_CHARGES, 0) + NVL (TOLERANCE_CHARGES, 0) BETWEEN A.START_RANGE AND A.END_RANGE AND A.OPEN_COVER_PROPOSAL_NO = B.PROPOSAL_NO AND B.OPEN_COVER_NO = ? AND A.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DEDUCTIBLE_MASTER WHERE OPEN_COVER_PROPOSAL_NO = A.OPEN_COVER_PROPOSAL_NO)) CURRENCY_NAME FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = (SELECT APPLICATION_NO FROM POSITION_MASTER WHERE QUOTE_NO = ? and PDF_EXCESS_STATUS='YES') AND COMMODITY_EXCESS_PREMIUM > 0 ";
			}else{
				sql = "SELECT COMMODITY_EXCESS_PREMIUM, DESCRIPTION, (SELECT CURRENCY_NAME FROM OPEN_COVER_DEDUCTIBLE_MASTER A, OPEN_COVER_POSITION_MASTER B WHERE NVL (SUMINSUREDLOCAL, 0) + NVL (SALE_TERM_CHARGES, 0) + NVL (TOLERANCE_CHARGES, 0) BETWEEN A.START_RANGE AND A.END_RANGE AND A.OPEN_COVER_PROPOSAL_NO = B.PROPOSAL_NO AND B.OPEN_COVER_NO = ? AND A.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DEDUCTIBLE_MASTER WHERE OPEN_COVER_PROPOSAL_NO = A.OPEN_COVER_PROPOSAL_NO)) CURRENCY_NAME FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = (SELECT APPLICATION_NO FROM POSITION_MASTER WHERE POLICY_NO = ? and PDF_EXCESS_STATUS='YES') AND COMMODITY_EXCESS_PREMIUM > 0 ";
			}
			final String args[] = {openCoverNo,qno};
			String result[][];
			result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	public String[][] getPdf(String clauses,String applicationNo,String branchCode)throws BaseException
	{
		logger.info("getPdf - Enter || applicationNo: "+applicationNo);
		String result[][];
		//String sql = "SELECT CODEDESC,CODESTATUS,CODE FROM TABLE(VIEWCLAUSES(?,?,?)) WHERE CODESTATUS IS NOT NULL";
		//final String args[] = {clauses,applicationNo,branchCode};
		String sql = "Select CODEDESC,CODESTATUS,CODE from (SELECT CODEDESC,CODESTATUS,CODE FROM TABLE(VIEWCLAUSES('Clauses',?,?)) union all SELECT CODEDESC,CODESTATUS,CODE FROM TABLE(VIEWCLAUSES('War',?,?))) WHERE CODESTATUS IS NOT NULL";
		final String args[] = {applicationNo,branchCode,applicationNo,branchCode};
		result = runner.multipleSelection(sql,args);
		logger.info("getPdf - Exit || Result: "+result);
		return result;
	}
	
	public String[][] getOpenCoverPdf(String proposalNo, String branchCode, String belongingBranch) throws BaseException 
	{
		logger.info("getOpenCoverPdf - Enter || proposalNo: "+proposalNo);
		String result[][];
		//String sql = "SELECT OCC.CLAUSES_DESCRIPTION CODEDESC,CM.PDF_LOCATION CODESTATUS,OCC.CLAUSES_ID CODE FROM OPEN_COVER_CLAUSES OCC, CLAUSES_MASTER CM WHERE PROPOSAL_NO =? AND OCC.CLAUSES_ID=CM.CLAUSES_ID AND OCC.EXTRA_COVER_ID = ? AND OCC.EXTRA_COVER_ID = CM.EXTRA_COVER_ID AND OCC.STATUS = ? AND CM.STATUS=? AND CM.BRANCH_CODE = ? AND CM.PDF_LOCATION IS NOT NULL AND AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_CLAUSES OCC1 WHERE OCC1.STATUS = OCC.STATUS AND OCC1.COVER_ID = OCC.COVER_ID AND OCC1.EXTRA_COVER_ID = OCC.EXTRA_COVER_ID AND OCC1.PROPOSAL_NO = OCC.PROPOSAL_NO)";
		//final String args[] = {proposalNo,"0","Y","Y",belongingBranch};
		String sql = "SELECT OCC.CLAUSES_DESCRIPTION CODEDESC,CM.PDF_LOCATION CODESTATUS,OCC.CLAUSES_ID CODE FROM OPEN_COVER_CLAUSES OCC, CLAUSES_MASTER CM WHERE PROPOSAL_NO =? AND OCC.CLAUSES_ID=CM.CLAUSES_ID AND OCC.EXTRA_COVER_ID = CM.EXTRA_COVER_ID AND OCC.STATUS = ? AND CM.STATUS=? AND CM.BRANCH_CODE = ? AND CM.PDF_LOCATION IS NOT NULL AND AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_CLAUSES OCC1 WHERE OCC1.STATUS = OCC.STATUS AND OCC1.COVER_ID = OCC.COVER_ID AND OCC1.EXTRA_COVER_ID = OCC.EXTRA_COVER_ID AND OCC1.PROPOSAL_NO = OCC.PROPOSAL_NO)";
		final String args[] = {proposalNo,"Y","Y",belongingBranch};
		result = runner.multipleSelection(sql,args);
		logger.info("getPdf - Exit || Result: "+result);
		return result;
	}
	
	public String getApplicationNo(String policyNo)
	{
		logger.info("getApplicationNo - Enter || policyNo: "+policyNo);
		String result=runner.singleSelection("SELECT APPLICATION_NO FROM POSITION_MASTER WHERE POLICY_NO=? AND STATUS IN ('P','C','D')",new String[]{policyNo});
		logger.info("getApplicationNo - Exit || Result: "+result);
		return 	result;
	}
	public double getnetPremium(String policyNo)
	{
		double res1;
		logger.info("getnetPremium - Enter || policyNo: "+policyNo);
		String result=runner.singleSelection("SELECT PREMIUM FROM POSITION_MASTER WHERE POLICY_NO=? AND STATUS IN ('P','C','D')",new String[]{policyNo});
		res1=Double.parseDouble(result);
		logger.info("getnetPremium - Exit || Result: "+result);
		return 	res1;
	}
	public String getsurveyClause(String getPolNo,String login,String brokerBra)
	{
		logger.info("Get SurveyClause - Enter || PolicyNo: "+getPolNo+" LoginId:"+login);
		String result=runner.singleSelection("SELECT REMARKS FROM CONSTANT_DETAIL WHERE DETAIL_NAME=? AND RSACODE=? AND STATUS='Y' AND CATEGORY_ID='148'",new String[]{getPolNo,login});
		logger.info("Get SurveyClause - Exit || Result: "+result);
		return 	result;
	}
	public String getApplicationNo1(String quoteNo)
	{
		logger.info("getApplicationNo - Enter || quoteNo: "+quoteNo);
		String result=runner.singleSelection("SELECT APPLICATION_NO FROM POSITION_MASTER WHERE quote_no=? AND STATUS in ('Y','E','D')",new String[]{quoteNo});
		logger.info("getApplicationNo - Exit || Result: "+result);
		return 	result;
	}
	public String getBelongingBranch(String branchCode) {
		logger.info("getBelongingBranch - Enter || branchCode: "+branchCode);
		String result=runner.singleSelection("SELECT BELONGING_BRANCH FROM BRANCH_MASTER WHERE BRANCH_CODE=?",new String[]{branchCode});
		logger.info("getBelongingBranch - Exit || Result: "+result);
		return 	result;
	}
	
}// Class

