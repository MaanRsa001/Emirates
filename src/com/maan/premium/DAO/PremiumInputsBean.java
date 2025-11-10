package com.maan.premium.DAO;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.premium.DAO.CommodityCountryRateDAO;
import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.common.util.OracleDateConversion;
import com.maan.common.util.StringUtil;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class PremiumInputsBean extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(PremiumInputsBean.class);
	private String sqlQuery_				= "";
	private String policyDate				= "";
	private String quoteId					= "";
	private String fromCountryId			= "";
	private String toCountryId				= "";
	private String viaCountryId				= "";
	private String fromCityId				= "";
	private String toCityId					= "";
	private String viaCityId				= "";
	private HashMap commodityDetails;
	private String modeOfTransport			= "";
	private String warehouse				= "";
	private String warehouse1				= "";
	private String transitFrom				= "";
	private String finalDestination			= "";
	private String finalVia					= "";
	private String totalSumInsured			= "";
	private String commodity				= "";
	private String noOfItems				= "";
	private String currency					= "";
	private String currencyValue			= "";
	private String policyDay				= "";
	private String policyMonth				= "";
	private String policyYear				= "";
	private String saleTerm					= "";
	private String tolerance				= "";
	private String cover					= "";
	private String wsrcc					= "";
	private String executive				= "";
	private String applicationNo			= "";
	private String loginCode				= "";
	private String sessionId				= "";
	private String productId				= "";
	private String companyId				= "";
	private int application_no				= 0;
	private String stageId					= "";
	private String currencyId				= "";
	private String currencyName				= "";
	private String exchangeId               = "";
	private String referral                 = "";
	private String totalSaleCharges         = "";
	private String totalTolCharges          = "";
	private String totalWarPremium          = "";
	private String seaOption                = "";
	private String openCoverNo				= "";
	private String vesselName				= "";
	private String vesselOption				= "";
	private String bankName					= "";
	private String lcNumber					= "";
	private String lcAmount					= "";
	private String lcDate					= "";
	private String lcEntryDate				= "";
	private String lcEffectiveDate			= "";
	private String lcId						= "";
	private String cid						= "";
	private String loginBra					= "";
	private String partialShip				= "";
	private String partialShipmentAmt		= "";
	private String agreeStatus				= "";
	private String exposureCurrency			= "";
	private String vesselId					= "";
	
	/**
	 * @return the vesselId
	 */
	public String getVesselId() {
		return vesselId;
	}

	/**
	 * @param vesselId the vesselId to set
	 */
	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
	}

	/**
	 * @return the exposureCurrency
	 */
	public String getExposureCurrency() {
		return exposureCurrency;
	}

	/**
	 * @param exposureCurrency the exposureCurrency to set
	 */
	public void setExposureCurrency(String exposureCurrency) {
		this.exposureCurrency = exposureCurrency;
	}

	public void setAgreeStatus(String agreeStatus)
	{
		this.agreeStatus = agreeStatus;
	}
	
	public String getAgreeStatus()
	{
		return this.agreeStatus;
	}

	public void setPartialShip(String partialShip)
	{
		this.partialShip = partialShip;
	}
	
	public void setPartialShipmentAmt(String partialShipmentAmt)
	{
		this.partialShipmentAmt = partialShipmentAmt;
	}

	public String getPartialShip()
	{
		return partialShip;
	}

	public String getPartialShipmentAmt()
	{
		return partialShipmentAmt;
	}
	
	public void setLoginBra(String loginBra){
		this.loginBra=loginBra;
	}
	
	public String getLoginBra(){
		return this.loginBra;
	}
	
	public void setCid(String cid){
		this.cid=cid;
	}
	public String getCid(){
		return this.cid;
	}

	public void setOpenCoverNo(String openCoverNo){
		this.openCoverNo=openCoverNo;
	}
	public String getOpenCoverNo(){
		return openCoverNo;
	}

	public void setSeaOption(String seaOption){
		this.seaOption=seaOption;
	}
	
	public String getSeaOption(){
		return seaOption;
	}
	
	public String getReferral(){
		return referral;
	}
	
	public void setReferral(String referral) {
		this.referral = referral;
	}

	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyName() {
		return currencyName;
	}
	
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getExchangeId() {
		return exchangeId;
	}
	
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getApplicationNo() {
		return applicationNo;
	}
	
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getLoginCode() {
		return loginCode;
	}
	
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getProductId() {
		return productId;
	}
	
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getStageId() {
		return stageId;
	}
	
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	public HashMap getCommodityDetails() {
		return commodityDetails;
	}
	
	public void setCommodityDetails(HashMap commodityDetails) {
		this.commodityDetails = commodityDetails;
	}

	public String getQuoteId() {
		return quoteId;
	}
	
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getTotalSumInsured() {
		return totalSumInsured;
	}
	
	public void setTotalSumInsured(String totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}
	
	public String getCommodity() {
		return commodity;
	}
	
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	
	public String getCover() {
		return cover;
	}
	
	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getCurrencyValue() {
		return currencyValue;
	}
	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}

	public String getExecutive() {
		return executive;
	}
	
	public void setExecutive(String executive) {
		this.executive = executive;
	}

	public String getVesselName() {
		return vesselName;
	}
	
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}

	public String getVesselOption() {
		return vesselOption;
	}
	
	public void setVesselOption(String vesselOption) {
		this.vesselOption = vesselOption;
	}
	
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}

	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getFinalDestination() {
		return finalDestination;
	}
	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
	}
	public String getFinalVia()
	{
		return finalVia;
	}
	public void setFinalVia(String finalVia) 
	{
		this.finalVia = finalVia;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}
	
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public String getNoOfItems() {
		return noOfItems;
	}
	public void setNoOfItems(String noOfItems) {
		this.noOfItems = noOfItems;
	}

	public String getPolicyDay() {
		return policyDay;
	}
	
	public void setPolicyDay(String policyDay) {
		this.policyDay = policyDay;
	}

	public String getPolicyMonth() {
		return policyMonth;
	}
	
	public void setPolicyMonth(String policyMonth) {
		this.policyMonth = policyMonth;
	}

	public String getPolicyYear() {
		return policyYear;
	}
	
	public void setPolicyYear(String policyYear) {
		this.policyYear = policyYear;
	}

	public String getSaleTerm() {
		return saleTerm;
	}
	
	public void setSaleTerm(String saleTerm) {
		this.saleTerm = saleTerm;
	}
	
	public String getTolerance() {
		return this.tolerance;
	}
	
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}

	public String getTransitFrom() {
		return transitFrom;
	}
	
	public void setTransitFrom(String transitFrom) {
		this.transitFrom = transitFrom;
	}

	public String getWarehouse() {
		return warehouse;
	}
	
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouse1() {
		return warehouse1;
	}
	
	public void setWarehouse1(String warehouse1) {
		this.warehouse1 = warehouse1;
	}

	public String getWsrcc() {
		return wsrcc;
	}
	
	public void setWsrcc(String wsrcc) {
		this.wsrcc = wsrcc;
	}
	
	public StringBuffer getLoginExecutiveDetails(String loginId)
	{
		String sql="select AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME from login_executive_details where status='Y' and (oa_code=(select agency_code from login_master where login_id=?) or oa_code=(select oa_code from login_master where login_id=?))";
		String args[] = new String[2];
		args[0] = loginId;
		args[1] = loginId;
		StringBuffer stb = new StringBuffer();
			
			try
			{
				 String channeldetails[][] = runner.multipleSelection(sql,args);
				 String channelIdentifier = "";
				 stb.append("<select name='executive' class='scrolLet' style='width:133px'>>");
				 stb.append("<option value ='0'>Select</option>");
				 for(int i = 0; i < channeldetails.length;i++){
				    	if (channeldetails[i][0].equalsIgnoreCase(executive)){
							channelIdentifier = "selected";
						}
						else{
							channelIdentifier = "";
						}
						stb.append("<option value = '"+channeldetails[i][0]+"'"+channelIdentifier+">"+StringUtil.upperFirstChar(channeldetails[i][1])+"</option>");
				    }
					if("99999".equalsIgnoreCase(executive))
						 stb.append("<option value ='99999' selected>None</option>");
					else
					     stb.append("<option value ='99999'>None</option>");
					stb.append("</select>");
			}
			catch(Exception e){
                e.printStackTrace();
			}
			return stb;
	}
	
    public void insertOrUpdateMarineInfo(String[][] commoditys,String freight, String frieghtuser, String userType)
	{
		int rows            				= 0;
		String exChangeId   				= "";
		String currencyName 				= "";
		String currencyId   				= "";
		String coverId      				= "";
		String exeName      				= "";
		HashMap currencyCoverInformation	= new HashMap();
		currencyCoverInformation = getCurrencyCoverInfo(currency,modeOfTransport,cover,executive,cid,loginBra);
		Calendar cal 						= new GregorianCalendar();
		String args[] = new String[1];
		policyInfo pol =new policyInfo();
		boolean GHQOACode = pol.getGHQOACode(userType);
		// Get the components of the time
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min    = cal.get(Calendar.MINUTE);          // 0..59
	    int sec    = cal.get(Calendar.SECOND);          // 0..59
		double db_premium=0.0;
   		String sqlCheck ="";

		policyDate	=	policyDay+"-"+policyMonth+"-"+policyYear+" "+hour24+":"+min+":"+sec;		
		
		exChangeId		= currencyCoverInformation.get("exChangeId")!=null?(String)currencyCoverInformation.get("exChangeId"):"0";
		setExchangeId(exChangeId);
		currencyName    = currencyCoverInformation.get("currencyName")!=null?(String)currencyCoverInformation.get("currencyName"):"";
		setCurrencyName(currencyName);
		currencyId      = currencyCoverInformation.get("currencyId")!=null?(String)currencyCoverInformation.get("currencyId"):"0";
		currencyId = currencyId!=null?currencyId:"0";
		setCurrencyId(currencyId);
		coverId         = currencyCoverInformation.get("coverId")!=null?(String)currencyCoverInformation.get("coverId"):"0";
		exeName         = currencyCoverInformation.get("executiveName")!=null?(String)currencyCoverInformation.get("executiveName"):"";
		

		sqlCheck = "select count(*) from marine_data where application_No=?";
		args[0] = ""+applicationNo;
		try
		{
			String res = runner.singleSelection(sqlCheck,args);
			if(res.length()>0)
			{
				rows = Integer.parseInt(res);
				System.out.println("RoyalTest rows true are.."+rows);
			}
			else
			{
				rows = 0;
				System.out.println("RoyalTest rows are.."+rows);
			}
		}
		catch(Exception e){
			System.out.println("Exception in insertOrUpdateCompanyInfo() for Count :"+e.toString());
			e.printStackTrace();
		}
	
		try
		{
			db_premium =  getPremium(applicationNo,modeOfTransport,coverId,saleTerm,freight,frieghtuser,commoditys);//premium
			/** Modified for GHQ User Code **/
			db_premium =  Double.parseDouble(getRoundedValue(db_premium+"", "1",GHQOACode));
			
		}
		catch(Exception e)
		{
			System.out.println("RoyalTest getting finall premium...");e.printStackTrace();
		}
		if("3".equalsIgnoreCase(productId)){
			openCoverNo = "0";
		}
		if(rows<=0)
		{
			try
			{
				System.out.println("Inside Insertion");			
				System.out.println(" Before Conversion QuoteDate :"+policyDate);
				System.out.println("the Application No--"+applicationNo);
				System.out.println("the policyDay No--"+policyDay);
				System.out.println("the policyMonth No--"+policyMonth);
				System.out.println("the policyYear No--"+policyYear);
				System.out.println("applicationNo :"+applicationNo);
				System.out.println("modeOfTransport :"+modeOfTransport);
				System.out.println("warehouse :"+warehouse);
				System.out.println("transitFrom :"+transitFrom);
				System.out.println("finalDestination :Insert"+finalDestination);
				System.out.println("noOfItems :"+noOfItems);
				System.out.println("totalSumInsured :"+totalSumInsured);
				System.out.println("currencyId :"+currencyId);
				System.out.println("exChangeId :"+exChangeId);
				System.out.println("currencyName :"+currencyName);
				System.out.println("currencyValue :"+currencyValue);
				System.out.println("saleTerm :"+saleTerm);
				System.out.println("tolerance :"+tolerance);
				System.out.println("cover :"+coverId);
				System.out.println("getWsrcc() :"+getWsrcc());
				System.out.println("executive :"+executive);
				System.out.println("the CONVERTED DATE TIME IS "+OracleDateConversion.ConvertDateTime(policyDate));
				System.out.println("companyId "+companyId);
				System.out.println("premium--- Insert royal Test..."+db_premium);
				System.out.println("fromCityId :"+fromCityId);
				System.out.println("fromCountryId :"+fromCountryId);
				System.out.println("toCityId :"+toCityId);
				System.out.println("toCountryId :"+toCountryId);
				System.out.println("viaCountryId :"+viaCountryId);
				System.out.println("getReferral() :"+getReferral());
				System.out.println("getTotalWarPremium() :"+getTotalWarPremium());
				System.out.println("seaOption   "+seaOption);
				System.out.println("the Marine Data productId is "+productId);
				System.out.println("the Marine Data OpenCoverNo is "+openCoverNo);
				System.out.println("the Marine Data bankName ID is "+bankName);
				System.out.println("the Marine Data lcNumber is "+lcNumber);
				System.out.println("the Marine Data vesselName is "+vesselName);
				System.out.println("the Marine Data vesselOption is "+vesselOption);
				System.out.println("the Marine Data getTotalTolCharges if is "+getTotalTolCharges());
				System.out.println("The Value Is Correctly Executed");
				
				String[] qryvalues = new String[46];
				qryvalues[0] = applicationNo;
				qryvalues[1] = modeOfTransport;
				qryvalues[2] = warehouse;
				qryvalues[3] = transitFrom;
				qryvalues[4] = finalDestination;
				qryvalues[5] = noOfItems;
				qryvalues[6] = totalSumInsured;
				qryvalues[7] = currencyId;
				qryvalues[8] = exChangeId;
				qryvalues[9] = currencyName;
				qryvalues[10] = currencyValue;
				qryvalues[11] = saleTerm;
				qryvalues[12] = coverId;
				qryvalues[13] = getWsrcc();
				qryvalues[14] = executive;
				qryvalues[15] = exeName;
				qryvalues[16] = "0";
				qryvalues[17] = "Normal";
				qryvalues[18] = "Y";
				qryvalues[19] = ""+(Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue));
				qryvalues[20] = companyId;
//				qryvalues[21] = Double.toString(Math.round(db_premium));
				qryvalues[21] = Double.toString(db_premium);
						
				if(fromCityId.equalsIgnoreCase(""))
				{
					fromCityId="0";
				}
				qryvalues[22] = fromCityId;
				qryvalues[23] = fromCountryId;
				if(toCityId.equalsIgnoreCase(""))
				{
					toCityId="0";
				}
				qryvalues[24] = toCityId;
				qryvalues[25] = toCountryId;
				qryvalues[26] = getReferral();
				qryvalues[27] = getTotalSaleCharges();
//				qryvalues[28] = Double.toString(Math.round(Double.parseDouble(getTotalWarPremium())));
				qryvalues[28] = Double.toString(Double.parseDouble(getTotalWarPremium()));
				qryvalues[29] = seaOption;
				qryvalues[30] = warehouse1;
				qryvalues[31] = productId;
				qryvalues[32] = openCoverNo;
				qryvalues[33] = bankName;
				qryvalues[34] = lcNumber;
				qryvalues[35] = vesselName;
				qryvalues[36] = vesselOption;
				qryvalues[37] = tolerance;
				qryvalues[38] = getTotalTolCharges();
				qryvalues[39] = partialShip;
				qryvalues[40] = partialShipmentAmt;
				qryvalues[41] = finalVia;
				qryvalues[42] = viaCityId;
				qryvalues[43] = viaCountryId;
				qryvalues[44] = exposureCurrency;
				qryvalues[45] = vesselId;

				String sqlIns ="insert into marine_data (APPLICATION_NO,MODE_OF_TRANSPORT,WAREHOUSE_TO_WAREHOUSE,TRANSIT_FROM,FINAL_DESTINATION, NO_OF_ITEMS,TOTAL_SUM_INSURED,CURRENCY_ID,EXCHANGE_ID,CURRENCY_NAME,EXCHANGE_RATE,SALE_TERM_ID,COVER_ID,EXTRA_COVER_ID,AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,EQUIVALENT_USD,COMPANY_ID,premium,transit_from_city_id,transit_from_country_id,final_destination_city_id,final_destination_country_id,referral,TOTAL_SALE_TERM_CHARGES,TOTAL_WAR_CHARGES,sea_options,ware_to_ware_final_dest,product_id,open_cover_no,open_bank_id,open_lc_id,open_vessel_name,open_vessel_option,TOLERANCE_ID,TOTAL_TOLERANCE_CHARGES,PARTIAL_SHIPPING,PARTIAL_SUM_INSURED_AMOUNT,VIA_CITY_NAME,VIA_CITY_ID,VIA_COUNTRY_ID,EXPOSURE_CURRENCY,OC_VESSEL_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss'),SYSDATE,SYSDATE,SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				runner.multipleInsertion(sqlIns, qryvalues);
			}
			catch(Exception e)
			{
				System.out.println("Exception in insertOrUpdateCompanyInfo()  Insertion :"+e.toString());
				e.printStackTrace();
			}
			
			try
			{
			    updateTrackingDetails();
			}
			catch(Exception ex)
			{
				System.out.println("ERROR OCCURED WHILE UPDATING END TIME IN MARINE"+ex.toString());
				ex.printStackTrace();
			}
		}
		else
		{
			System.out.println("Inside Udpdate");
			try
			{
				String convertDate="TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss')";
				System.out.println("the DATE IS "+policyDate);				
				System.out.println("the NO of applicationNo UPDATE is"+applicationNo);
				System.out.println("the NO of modeOfTransport UPDATE is"+modeOfTransport);
				System.out.println("the NO of warehouse UPDATE is"+warehouse);
				System.out.println("the NO of transitFrom UPDATE is"+transitFrom);
				System.out.println("the NO of finalDestination UPDATE is"+finalDestination);
				System.out.println("the NO of noOfItems UPDATE is"+noOfItems);
				System.out.println("the NO of totalSumInsured UPDATE is"+totalSumInsured);
				System.out.println("the NO of currencyId UPDATE is"+currencyId);
				System.out.println("the NO of exChangeId UPDATE is"+exChangeId);
				System.out.println("the NO of currencyName UPDATE is"+currencyName);
				System.out.println("the NO of currencyValue UPDATE is"+currencyValue);
				System.out.println("the NO of saleTerm UPDATE is"+saleTerm);
				System.out.println("the NO of tolerance UPDATE is"+tolerance);
				System.out.println("the NO of coverId UPDATE is"+coverId);
				System.out.println("the NO of getWsrcc() UPDATE is"+getWsrcc());
				System.out.println("the NO of executive UPDATE is"+executive);
				System.out.println("the NO of exeName UPDATE is"+exeName);
				System.out.println("premium---AFTER update royal Test..."+db_premium);
				System.out.println("fromCityId UPDATE:"+fromCityId);
				System.out.println("fromCountryId UPDATE:"+fromCountryId);
				System.out.println("toCityId UPDATE:"+toCityId);
				System.out.println("toCountryId UPDATE:"+toCountryId);
				System.out.println("viaCountryId UPDATE:"+viaCountryId);
				System.out.println("getReferral() UPDATE:"+getReferral());
				System.out.println("getTotalWarPremium() UPDATE:"+getTotalWarPremium());
				System.out.println(" update warehouse1 -----"+warehouse1);
				System.out.println("the UPDATE productId is "+productId);
				System.out.println("the UPDATE OpenCoverNo is "+openCoverNo);
				System.out.println("the UPDATE bankName is "+bankName);
				System.out.println("the UPDATE lcNumber is "+lcNumber);
				System.out.println("the UPDATE vesselName is "+vesselName);
				System.out.println("the UPDATE vesselOption is "+vesselOption);
				System.out.println("the Marine Data getTotalTolCharges else is "+getTotalTolCharges());

				String[] qryvalues = new String[47];
				qryvalues[0] = applicationNo;
				qryvalues[1] = modeOfTransport;
				qryvalues[2] = warehouse;
				qryvalues[3] = transitFrom;
				qryvalues[4] = finalDestination;
				qryvalues[5] = noOfItems;
				qryvalues[6] = totalSumInsured;
				qryvalues[7] = currencyId;
				qryvalues[8] = exChangeId;
				qryvalues[9] = currencyName;
				qryvalues[10] = currencyValue;
				qryvalues[11] = saleTerm;
				qryvalues[12] = coverId;
				qryvalues[13] = getWsrcc();
				qryvalues[14] = executive;
				qryvalues[15] = exeName;
				qryvalues[16] = "0";
				qryvalues[17] = "Y";
				qryvalues[18] = ""+(Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue));
		    	qryvalues[19] = companyId;
//				qryvalues[20] = Double.toString(Math.round(db_premium));
				qryvalues[20] = Double.toString(db_premium);
				
				if(fromCityId.equalsIgnoreCase(""))
				{
					fromCityId="0";
				}
				qryvalues[21] = fromCityId;
				qryvalues[22] = fromCountryId;
				if(toCityId.equalsIgnoreCase(""))
				{
						toCityId="0";
				}
				qryvalues[23] = toCityId;
				qryvalues[24] = toCountryId;
				qryvalues[25] = getReferral();
				qryvalues[26] = getTotalSaleCharges();
//				qryvalues[27] = Double.toString(Math.round(Double.parseDouble(getTotalWarPremium())));
				qryvalues[27] = Double.toString(Double.parseDouble(getTotalWarPremium()));
				qryvalues[28] = seaOption;
				qryvalues[29] = warehouse1;
				qryvalues[30] = productId;
				qryvalues[31] = openCoverNo;
				qryvalues[32] = bankName;
				qryvalues[33] = lcNumber;
				qryvalues[34] = vesselName;
				qryvalues[35] = vesselOption;
				qryvalues[36] = tolerance;
				qryvalues[37] = getTotalTolCharges();
				qryvalues[38] = partialShip;
				qryvalues[39] = partialShipmentAmt;
				qryvalues[40] = finalVia;
				qryvalues[41] = viaCityId;
				qryvalues[42] = viaCountryId;
				qryvalues[43] = exposureCurrency; //38
				qryvalues[44] = vesselId; //38
				qryvalues[45] = applicationNo; //38
				qryvalues[46] = "Y";//39
				
				String sqlUps =
					"update marine_data set APPLICATION_NO=?,MODE_OF_TRANSPORT=?,WAREHOUSE_TO_WAREHOUSE=?,TRANSIT_FROM=?,FINAL_DESTINATION=?,NO_OF_ITEMS=?,TOTAL_SUM_INSURED=?,CURRENCY_ID=?,EXCHANGE_ID=?,CURRENCY_NAME=?,EXCHANGE_RATE=?,SALE_TERM_ID=?,COVER_ID=?,EXTRA_COVER_ID=?,AC_EXECUTIVE_ID=?,AC_EXECUTIVE_NAME=?,AMEND_ID=?,INCEPTION_DATE=TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss'),EXPIRY_DATE=SYSDATE,EFFECTIVE_DATE=SYSDATE,ENTRY_DATE=SYSDATE,STATUS=?,EQUIVALENT_USD=?,COMPANY_ID=?,premium=?,transit_from_city_id=?,transit_from_country_id=?,final_destination_city_id=?,final_destination_country_id=?,referral=?,TOTAL_SALE_TERM_CHARGES=?,TOTAL_WAR_CHARGES=?,sea_options=?,ware_to_ware_final_dest=?,product_id=?,open_cover_no=?,open_bank_id=?,open_lc_id=?,open_vessel_name=?,open_vessel_option=?,TOLERANCE_ID=?,TOTAL_TOLERANCE_CHARGES=?,PARTIAL_SHIPPING=?,PARTIAL_SUM_INSURED_AMOUNT=?,VIA_CITY_NAME=?,VIA_CITY_ID=?,VIA_COUNTRY_ID=?,EXPOSURE_CURRENCY=?,OC_VESSEL_ID=?,DEC_EXCESS_PREMIUM='' where APPLICATION_NO=? and status=?";
					runner.multipleInsertion(sqlUps, qryvalues);
					runner.multipleUpdation("UPDATE POSITION_MASTER A SET a.OPEN_COVER_INT_STATUS='' WHERE A.APPLICATION_NO=?", new String[]{applicationNo});
			}
			catch(Exception upExcep)
			{
				System.out.println("Exception in UpdateCompany Infor :"+upExcep.toString());
				upExcep.printStackTrace();
			}
			try
			{

				  if(getExistingTrackingStatus()>0)
				  {
						updateTrackingDetails();
				  }
				  else
				  {
						insertTrackingDetails("insideUpdate");
				  }
			}
			catch(Exception ex)
			{
				System.out.println("the ERROR in Update traking Details"+ex.toString());
				ex.printStackTrace();
			}			
		}
	}

    public  HashMap getCurrencyCoverInfo(String exRate,String modeId,String coverName,String executiveId)
	{
		HashMap currencyCoverInfo=new HashMap();
		String result[][] = new String[0][0];
		String args[] = new String[0];
		
		try
		{
			try
			{
				//sqlQuery_=  "select * from exchange_master em,currency_master cm where em.currency_id=cm.currency_id and em.status=cm.status and em.status='Y' and cm.status='Y' and em.currency_id=? and em.amend_id=(select max(amend_id) from exchange_master where currency_id=? and status='Y')";

				sqlQuery_=  "select em.exchange_id,em.currency_id,cm.currency_name from exchange_master em,currency_master cm where em.currency_id=cm.currency_id and em.status=cm.status and em.status='Y' and cm.status='Y' and em.currency_id=? and em.amend_id=(select max(amend_id) from exchange_master where currency_id=? and status='Y')";
				
				print("RoyalTest getCurrencyInfo",sqlQuery_,"QUERY");			
				args = new String[2];		
				args[0] = exRate;
				args[1] = exRate;
				result = runner.multipleSelection(sqlQuery_,args);

				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("exChangeId",result[i][0]);
					currencyCoverInfo.put("currencyId",result[i][1]);
					currencyCoverInfo.put("currencyName",result[i][2]);
				}
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getCurrencyInfo--"+exception.toString());
				exception.printStackTrace();
			}		
			try
			{
				args = new String[2];
				args[0] = modeId;
				args[1] = coverName;

				//sqlQuery_=	"select cm.cover_id,cm.cover_name,cm.mode_transport_id from cover_master cm where cm.mode_transport_id='"+modeId+"' and cm.cover_id='"+coverName+"' and  cm.status='Y'";
				
				sqlQuery_=	"select cm.cover_id,cm.cover_name,cm.mode_transport_id from cover_master cm where cm.mode_transport_id=? and cm.cover_id=? and  cm.status='Y'";
				print("getModeCoverInfo",sqlQuery_,"QUERY");
				
				result = runner.multipleSelection(sqlQuery_,args);
				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("coverId",result[i][0]);
				}
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getModeCoverInfo--"+exception.toString());
				exception.printStackTrace();
			}
			try
			{
				args= new String[1];
				args[0] = executiveId;
				sqlQuery_=	"select * from login_executive_details where ac_executive_id=? and Status='Y'" ;
				print("getExecutiveInfo",sqlQuery_,"QUERY");
				result = runner.multipleSelection(sqlQuery_,args);	
				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("executiveName",result[i][1]);
				}
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getModeCoverInfo--"+exception.toString());
				exception.printStackTrace(); 
			}
		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getCurrencyCoverInfo--"+exception.toString());
			exception.printStackTrace(); 
		}
		return currencyCoverInfo;
	}
	
	//to be checked
    public  String getCountryCityInfo(String countryId,String cityName)
    {
    	String countryCityInfo="";
		String args[] = new String[0];
		
		String result[][] = new String[0][0];
    	try
		{
			args[0] = cityName.toLowerCase();
    		//sqlQuery_="select ct.country_id,ct.city_id,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=lower('"+cityName+"') and ct.country_id=cm.country_id";
    		sqlQuery_="select ct.country_id,ct.city_id,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=? and ct.country_id=cm.country_id";
			result = runner.multipleSelection(sqlQuery_,args);
    		print("countryCityOTHERSInfo",sqlQuery_,"QUERY");
    		if(result.length > 0)
			{
			}
			else
			{
				cityName="Others";
			}
	  }
      catch(Exception exception)
	  {
	    System.out.println("The Exception occured in countryCityOTHERSInfo--"+exception.toString());
	    exception.printStackTrace();
	  }      
	  //Others Id
	  try
	  {
		  args[0] = cityName.toLowerCase();
		 // sqlQuery_=  "select ct.country_id,ct.city_id as cityId,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=lower('"+cityName+"') and cm.country_id='"+countryId+"' and ct.country_id=cm.country_id";
		  sqlQuery_=  "select ct.country_id,ct.city_id as cityId,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=lower('"+cityName+"') and cm.country_id='"+countryId+"' and ct.country_id=cm.country_id";
		  print("countryCityInfo",sqlQuery_,"QUERY");
		  result = runner.multipleSelection(sqlQuery_,args);
		  for(int i=0;i<result.length;i++)
		  {
			countryCityInfo = result[i][1];
		  }
	  }
	  catch(Exception exception)
	  {
		System.out.println("The Exception occured in countryCityInfo--"+exception.toString());
        exception.printStackTrace();
	  }
	  return countryCityInfo;
    }

	private StringBuffer coverName			      = new StringBuffer();
	private StringBuffer categoryMasterName       = new StringBuffer();
	private StringBuffer coverId			      = new StringBuffer();
	private StringBuffer modeMasterId		      = new StringBuffer();
	private StringBuffer categoryMainMasterId     = new StringBuffer();
	private StringBuffer packageId                = new StringBuffer();
	private StringBuffer packageName			  = new StringBuffer();
	private StringBuffer modePackageId			  = new StringBuffer();
	//Open Cover Policy
	private StringBuffer coverBankName            = new StringBuffer();
	
	private StringBuffer coverBankId			  = new StringBuffer();
	private StringBuffer modeMasterBankId		  = new StringBuffer();
	private StringBuffer categoryMainBankMasterId = new StringBuffer();
	private StringBuffer categoryMasterBankName   = new StringBuffer();
	
	
    public String[][] getLCNumbers(String openCoverNo)
    {
		String args[] = new String[1];
		args[0] = openCoverNo;
   		
		sqlQuery_ = "select bank_id,lc_id,lc_number from open_cover_lc_master where status='Y' and open_cover_no=? order by lc_id";
   		String businessSubList[][]	=	new String[0][0];
		try
		{
			businessSubList = runner.multipleSelection(sqlQuery_,args);
			for(int i = 0; i < businessSubList.length;i++)
			{
				businessSubList[i][2]=StringUtil.upperFirstChar(businessSubList[i][2].length()>50?businessSubList[i][2].substring(0,50):businessSubList[i][2]);
				modeMasterBankId.append("'"+businessSubList[i][0]+"',");
				coverBankId.append("'"+businessSubList[i][1]+"',");
				coverBankName.append("'"+businessSubList[i][2]+"',");//dou
			}
			if(modeMasterBankId.length()>0)
				modeMasterBankId.deleteCharAt(modeMasterBankId.length()-1);
			if(coverBankId.length()>0)
				coverBankId.deleteCharAt(coverBankId.length()-1);
			if(coverBankName.length()>0)
				coverBankName.deleteCharAt(coverBankName.length()-1);
		}
		catch(Exception e)
		{
			System.out.println("545 Exception getLCNumbers : "+e);
			e.printStackTrace();		
		}
		return businessSubList;
   }

   public StringBuffer getModeMasterId(){
	return modeMasterId;
   }

   public void setModeMasterId(StringBuffer modeMasterId){
	this.modeMasterId = modeMasterId;
   }

   public void setCoverId(StringBuffer coverId){
	this.coverId = coverId;
   }
   
   public StringBuffer getCoverId(){
	return coverId;
   }

   public void setCoverName(StringBuffer coverName){
	this.coverName = coverName;
   }

   public StringBuffer getCoverName(){
	return coverName;
   }

   public StringBuffer getPackageId() {
	return packageId;
}

public void setPackageId(StringBuffer packageId) {
	this.packageId = packageId;
}

public StringBuffer getPackageName() {
	return packageName;
}

public void setPackageName(StringBuffer packageName) {
	this.packageName = packageName;
}

public StringBuffer getModePackageId() {
	return modePackageId;
}

public void setModePackageId(StringBuffer modePackageId) {
	this.modePackageId = modePackageId;
}

//For OPen Cover Policy
   public StringBuffer getModeMasterBankId(){
	return modeMasterBankId;
   }

   public void setModeMasterBankId(StringBuffer modeMasterBankId){
	this.modeMasterBankId = modeMasterBankId;
   }
   public StringBuffer getCategoryMainBankMasterId()
   {
		return this.categoryMainBankMasterId;
   }

   public void setCategoryMainBankMasterId(StringBuffer categoryMainBankMasterId)
   {
		this.categoryMainBankMasterId = categoryMainBankMasterId;
   }
   public StringBuffer getCategoryMasterBankName()
   {
		return this.categoryMasterBankName;
   }

   public void setCategoryMasterBankName(StringBuffer categoryMasterBankName)
   {
		this.categoryMasterBankName = categoryMasterBankName;
   }
   public void setCoverBankId(StringBuffer coverBankId){
	this.coverBankId = coverBankId;
   }



   public StringBuffer getCoverBankId(){
	return coverBankId;
   }

   public void setCoverBankName(StringBuffer coverBankName){
	this.coverBankName = coverBankName;
   }

   public StringBuffer getCoverBankName(){
	return coverBankName;
   }

	public String[][] getBankNames(String openCoverNo,String cid)
	{
		String args[] = new String[3];
		sqlQuery_="select bank_id,bank_name,amend_id from open_cover_bank_master where amend_id||'-'||bank_id in (select max(amend_id)||'-'||bank_id from open_cover_bank_master where to_date(effective_date,'dd-mm-yyyy')<=to_date(SYSDATE,'dd-mm-yyyy') and status in('N','Y') and BELONGING_COUNTRY_ID=? and bank_id in(select bank_id from OPEN_COVER_LC_MASTER where open_cover_no =?) group by bank_id) and status='Y' and BELONGING_COUNTRY_ID=? order by bank_name";
		args[0] = cid;
		args[1] = openCoverNo;
		args[2] = cid; 
		
		String businessList[][]	=	new String[0][0];
		StringBuffer busi = new StringBuffer();
		try
		{
			businessList	= runner.multipleSelection(sqlQuery_,args);
			for(int i = 0; i < businessList.length;i++)
			{
				categoryMainBankMasterId.append("'"+businessList[i][0]+"',");
				categoryMasterBankName.append("'"+businessList[i][1]+"',");//doublescript code end
			}
			if(categoryMainBankMasterId.length()>0)
				categoryMainBankMasterId.deleteCharAt(categoryMainBankMasterId.length()-1);
			if(categoryMasterBankName.length()>0)
				categoryMasterBankName.deleteCharAt(categoryMasterBankName.length()-1);
		}
		catch(Exception e)
		{
			System.out.println("2746 Exception getBankName : "+e);
			e.printStackTrace();
		}
		return businessList;
	}

   public int getMaximumApplicationNo()
   {
	  int application_id=1;
	  String sql ="select nvl(max(application_no),'0')+1 from marine_data" ;
	  try
	  {
			String temp = runner.singleSelection(sql);		
			if(temp.length()>0)
				application_id = Integer.parseInt(temp);
	  }
	  catch(Exception e)
	  {
		  System.out.println("the EXCEPTION IN "+e.toString());
	  }
	 if(application_id==0)
		application_id=1;
	return application_id;
  }

  //NEW TWO DIMENSION
  // For Sea
  //"1","2","CoverId","1","CommodityId","X"
  //For Air
  //"2","3","CoverId","1","CommodityId","X"
  //For Road
  //"3","4","CoverId","1","CommodityId","X"
  //Rajesh for Freight Forwarder
  
  public double getPremium(String applicationNo,String modeOfTransport,String coverId,String saleTerm,String freight,String frieghtuser,String [][] commoditys)
  {
	double finalPremium=0.0;
	HashMap commRates = new HashMap();
	commRates=getPremiumDetails(applicationNo,modeOfTransport,coverId,saleTerm,freight,frieghtuser,commoditys);
	finalPremium=Double.parseDouble((String)commRates.get("totalPremium"));
	System.out.println("RoyalTest verylast fianl premium..."+finalPremium);
	return finalPremium;
  }

  public double getSaleTermValue(String saleTerm)
  {
	String saleTermLocal="1";
	String args[] = new String[2];
	String result = "";
	
		try
		{
			args[0] = saleTerm;
			args[1] = loginBra;
			sqlQuery_=	"select sale_term_value from sale_term_master where sale_term_id=? and branch_code=? and status='Y'";
			print("getSaleTermValue",sqlQuery_,"QUERY");
			result = runner.singleSelection(sqlQuery_,args);
			if(result.length() > 0)
			{
				saleTermLocal = result;
			}
		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getSaleTermValue--"+exception.toString());
			exception.printStackTrace();
		}
		return Double.parseDouble(saleTermLocal);
  }
 
	public double getToleranceValue(String tolerance)
	{
		String saleTermLocal="0";
		String args[] = new String[2];
		String result = "";
		
		try
		{
			args[0] = tolerance;
			args[1] = loginBra;
			sqlQuery_=	"select TOLERANCE_VALUE from TOLERANCE_MASTER where TOLERANCE_ID=? and branch_code=? and status='Y'";
			print("getToleranceValue",sqlQuery_,"QUERY");
			result  = runner.singleSelection(sqlQuery_,args);
			if(result.length() > 0)
			{
				saleTermLocal = result;
			}
		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getSaleTermValue--"+exception.toString());
			exception.printStackTrace();
		}
		return Double.parseDouble(saleTermLocal);
	}

    public String getGeneralValue(String selectName,String tableName,String valueColumn,String value)
    {
	    String valueFetched="";
		try
		{
			sqlQuery_="select "+selectName+"  from "+tableName+" where "+valueColumn+"='"+value+"'";
			valueFetched = runner.singleSelection(sqlQuery_);
		}
		catch(Exception e)
		{
			System.out.println("The Exception occured in getGeneralValue--"+e.toString());e.printStackTrace();
		}
	    return valueFetched;
     }
    
	 // Rajesh For Frieght Forwarder
     public  HashMap getPremiumDetails(String applicationNo,String modeOfTransport,String coverId,String saleTermValue, String freight, String frieghtuser,String [][] commoditys)
     {
	          HashMap commodityRates       = new HashMap();
	          String[][] commodityRatesArray = new String[0][0];
	          print("@@@@@@","PREMIUM COMPUTATION STARTS","@@@@@@@@@@@");
	          print("applicationNo",applicationNo,"applicationNo");
	          print("modeOfTransport",modeOfTransport,"modeOfTransport");
	          print("coverId",coverId,"coverId");
	          print("saleTerm",saleTermValue,"saleTerm");
	          String ratingFactorSubId       = "";
	          String ratingFactorFirstId     = "";//mode of Transport
	          String ratingFactorSecondId    = "";//Commodity_Name
	          String secondDataName          = "";//CommodityID
	          String firstDataName           = "";//CoverId
	          String axis                    = "";//axis
	          String rate                    = "";	          
	          double salePercentage          = 0.0;
	          double tolPercentage          = 0.0;
	          double conSI                   = 0.0;
	          double pre                     = 0.0;
	          double totalConSI              = 0.0;
	          double totalPre                = 0.0;
	          
	          String warehouseValue          = "0";
	          String seaValue                = "0";
	          String referralStatus          = "Normal";
	          double dou_SI                  = 0.0;
	          double dou_SIAED               = 0.0;
	          double dou_saleValue           = 0.0;
	          double dou_tolValue            = 0.0;
	          double dou_totSaleValue        = 0.0;
	          double dou_totTolValue         = 0.0;
	          double dou_warPremium          = 0.0;
	          double dou_totWarPremium       = 0.0;
	          double dou_warRate             = 0.0; // 0.05/100
	          double saleFactor              = 0.0;
	          double tolFactor               = 0.0;
	          boolean rateStatus             = false;
	          boolean warehouseStatus        = false;
	          boolean seaStatus              = false;
	          System.out.println("  WSRCC OPTION    "+wsrcc);
	          java.text.NumberFormat fmt1    = new java.text.DecimalFormat("0.00000") ;
	          java.text.NumberFormat fmtPremium = new java.text.DecimalFormat("0.0000") ;
	          HashMap fullWarOptions          = new HashMap();
	          HashMap warRateOptions          = new HashMap();
	          salePercentage                    = getSaleTermValue(saleTermValue);
	          tolPercentage                    = getToleranceValue(tolerance);
	          
	          String mocWarRate = "";
	          if("11".equalsIgnoreCase(productId))
			  {
//	        	  mocWarRate = getWarRateForMOC(toCountryId,openCoverNo);
	        	  mocWarRate = getWarRateForMOC(modeOfTransport,openCoverNo);
 			 		if(mocWarRate == null || mocWarRate.equalsIgnoreCase("null") || mocWarRate.length() == 0 ){
 			 			mocWarRate = "0";
 			 		}
			  }
	          
	          /*if("11".equalsIgnoreCase(productId)) //Donot remove - June - 03
			  {
	        	  	fullWarOptions=getWarRelatedOptions();
	        	  	warRateOptions=(HashMap)fullWarOptions.get("warRateHash");
	          }*/
	          if(Integer.parseInt(wsrcc)==0)
			  {
	        	  System.out.println("  WSRCC integer    "+Integer.parseInt(wsrcc));
	        	  dou_warRate=0.0;
	          }
	          else
			  {
	        	  System.out.println("  WSRCC integer    "+Integer.parseInt(wsrcc));
	        	  try
				  {
	        		 if("3".equalsIgnoreCase(productId))
					 {
	        			 // dou_warRate=Double.parseDouble(runner.singleSelection("select war_rate from country_master_detail where country_id='"+toCountryId+"' and BELONGING_COUNTRY_ID='"+cid+"'"));
//	        			String oneOffWarRae = oneOffWarRate(toCountryId,cid);
	        			String oneOffWarRae = oneOffWarRate(modeOfTransport,loginBra);
			            System.out.println("oneOffWarRae....................11111....."+oneOffWarRae);
			            dou_warRate=Double.parseDouble(oneOffWarRae);
	        		 }
	        		 else if ("11".equalsIgnoreCase(productId))
					 {		
	        			 dou_warRate=Double.parseDouble(mocWarRate);
	        			 //dou_warRate=Double.parseDouble((String)warRateOptions.get(toCountryId)==null?"0":(String)warRateOptions.get(toCountryId));
	        		  }
	        	  }
	        	  catch(Exception e)
				  {
	        		  dou_warRate=0.0;
		          }
	         }
	         
	         /*if(modeOfTransport.equalsIgnoreCase("1")){
	        	 //getTwoDimDisDisValue("1","2",coverId,"1","CommodityId","X");
	        	 ratingFactorSubId="4";
	        	 ratingFactorFirstId="2";
	        	 ratingFactorSecondId="1";
	        	 firstDataName=coverId;
	        	 axis="X";
	         }
	         else if(modeOfTransport.equalsIgnoreCase("2")){
	        	 //getTwoDimDisDisValue("2","3",coverId,"1","CommodityId","X");
	        	 ratingFactorSubId="2";
	        	 ratingFactorFirstId="3";
	        	 ratingFactorSecondId="1";
	        	 firstDataName=coverId;
	        	 axis="X";
	         }
	         else if(modeOfTransport.equalsIgnoreCase("3")){
	        	 //getTwoDimDisDisValue("3","4",coverId,"1","CommodityId","X");
	        	 ratingFactorSubId="3";
	        	 ratingFactorFirstId="4";
	        	 ratingFactorSecondId="1";
	        	 firstDataName=coverId;
	        	 axis="X";
	         }
			  else if(modeOfTransport.equalsIgnoreCase("4")){
	        	 //getTwoDimDisDisValue("3","4",coverId,"1","CommodityId","X");
	        	 ratingFactorSubId="2";
	        	 ratingFactorFirstId="3";
	        	 ratingFactorSecondId="1";
	        	 firstDataName=coverId;
	        	 axis="X";
	         }*/
	          firstDataName=coverId;
	         try{
	        	 sqlQuery_="select mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile,mrd.COMMODITY_TYPE_ID,mrd.COMMODITY_EXCESS_PREMIUM from marine_result_details mrd where mrd.application_no='"+applicationNo+"' and  Status='Y'";
	        	 print("getCommodityRates",sqlQuery_,"QUERY");
	        	 commodityRatesArray = runner.multipleSelection(sqlQuery_);
       	 
	        	if(commodityRatesArray.length>0)
				{
	        		for(int i=0;i<commodityRatesArray.length;i++)
					{
	        			 secondDataName=commodityRatesArray[i][1];
	        			 print("secondDataName",secondDataName,"VALUE");
	        			try
						{
	        				 System.out.println("commodity id    "+commodityRatesArray[i][1]);
	        				 System.out.println("firstDataName   "+firstDataName);
	        				if(commoditys!=null && commoditys.length>0)
							{      					 	
	        					 rateStatus=true;
	        					 rate=new CommodityCountryRateDAO().getCommodityCountryRate(loginCode, productId, applicationNo, modeOfTransport, coverId, commodityRatesArray[i][1], fromCountryId, toCountryId, openCoverNo);
								 if(rate.equals(""))
									 rate=""+Double.parseDouble(commoditys[i][1]==null?"0":commoditys[i][1]);	        					 
						         if(Integer.parseInt(wsrcc)==0)
							        dou_warRate=0.0;
						         else{	
							            dou_warRate=Double.parseDouble(commoditys[i][2]==null?"0":commoditys[i][2]);							
							            if("3".equalsIgnoreCase(productId))
										{
							            	//dou_warRate=Double.parseDouble(runner.singleSelection("select war_rate from country_master_detail where country_id='"+toCountryId+"' and BELONGING_COUNTRY_ID='"+cid+"'"));
//							            	String oneOffWarRae = oneOffWarRate(toCountryId,cid);
							            	String oneOffWarRae = oneOffWarRate(modeOfTransport,loginBra);
							            	System.out.println("oneOffWarRae........................."+oneOffWarRae);
							            	dou_warRate=Double.parseDouble(oneOffWarRae);
							            }
							            else if ("11".equalsIgnoreCase(productId))
										{
							            	dou_warRate=Double.parseDouble(mocWarRate);
							            	//dou_warRate=Double.parseDouble((String)warRateOptions.get(toCountryId)==null?"0":(String)warRateOptions.get(toCountryId));
							            }
						         }
						         if("YES".equalsIgnoreCase(warehouse) && "YES".equalsIgnoreCase(warehouse1))
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
						        System.out.println("dou_warRate  --- "+dou_warRate);
						        
						        if(rate!=null && Double.parseDouble(rate)==-1)
								{
						        	 rateStatus=false;
									 String args1[] = new String[2];
									 args1[0] = firstDataName;
									 args1[1] = loginBra;
						        	String rateCoverName =totalValue("select description from cover_master where cover_id=? and status='Y'  and BRANCH_CODE=?",args1);
						        	 CommodityRateCalculator calculator = new CommodityRateCalculator();
									 double dou_SITmp=Double.parseDouble(commodityRatesArray[i][2]);
									System.out.println("dou_SI   "+dou_SITmp);
									
									//This is SUM INSURED LOCAL
									double dou_SIAEDTmp=dou_SITmp*Double.parseDouble(currencyValue);
									System.out.println("dou_SIAED   "+dou_SIAEDTmp);
									//This is Sale Term Percentage
									double saleFactorTmp=(salePercentage/100);
									System.out.println("saleFactor   "+saleFactorTmp);
									 System.out.println("Mode:"+modeOfTransport);
									 System.out.println("Cover:"+coverId);
									 System.out.println("conveyance:"+seaOption);
									 System.out.println("toCountry:"+toCountryId);
									 double dou_saleValueTmp=(dou_SIAEDTmp*saleFactorTmp);
									 System.out.println("sumInsL:"+(dou_saleValueTmp));
									 System.out.println("Login branch::"+loginBra);
									 /*** Corrected the value for SumInsuredLocal Parameter for rate calculation*/
									 /*** Hari 29Jun2012 Start**/
									double  conSItemp=dou_SIAEDTmp+dou_saleValueTmp;
									double tolFactorTEmp = (tolPercentage/100);
										//This is tolerance Charges
									double 	dou_tolValueTemp=(conSItemp*tolFactorTEmp);
									conSItemp=conSItemp+dou_tolValueTemp;
						        	if("3".equalsIgnoreCase(productId))
									{
										 String args[] = new String[3];
										 args[0] = commodityRatesArray[i][1];
										 args[1] = loginBra;
										 args[2] = loginBra;
						        		// rate=totalValue("select case when status='R' then 0 else nvl("+rateCoverName+",'0') end from commoditymaster where commodity_id=? and BRANCH_CODE=? and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and BRANCH_CODE=? group by commodity_id)",args);
										 //added by hari to get values from commodityrate calculator
										
											System.out.println(" dou_tolValue   "+dou_tolValueTemp);
										   System.out.println(" b4 add tolerance conSI   "+conSItemp);
//										    conSItemp=conSItemp+dou_tolValueTemp;
											System.out.println(" after add tolerance conSI   "+conSItemp);
											 /*** Corrected the value for SumInsuredLocal Parameter for rate calculation*/
											 /*** Hari 29Jun2012  End **/
										 rate = calculator.getCommodityRate(modeOfTransport, coverId,seaOption , toCountryId, commodityRatesArray[i][1],commodityRatesArray[i][4],(conSItemp==0)?dou_SIAEDTmp:conSItemp, loginBra,policyDate,applicationNo);
										 calculator.updateExcessPremium(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo, loginBra);
											
										 System.out.println("Rate for Commodity"+i+"===>"+rate);
						        	}
						        	else if ("11".equalsIgnoreCase(productId))
									{
										 String args[] = new String[5];
										 args[0] = openCoverNo;
										 args[1] = openCoverNo;
										 args[2] = commodityRatesArray[i][1];
										 args[3] = firstDataName;
										 args[4] = seaOption;

						        		 String sql="select nvl(occd.commodity_base_rate,0) from open_cover_commodity_detail occd,open_cover_position_master ocpm where ocpm.open_cover_no=? and occd.proposal_no=ocpm.proposal_no and occd.amend_id in (select max(occds.amend_id) from open_cover_commodity_detail occds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=occds.proposal_no and to_date(occds.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) and occd.commodity_id=? and occd.cover_id=? and occd.COVER_TYPE_ID=?";
						        		 calculator.updateExcessPremium(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo,loginBra);
											
						        		 rate=totalValue(sql,args);
						        		 
						        	 }
						        }
					        }
					        if(commoditys.length==0)
							{
					        	String args1[] = new String[2];
					        	args1[0] = firstDataName;
					        	args1[1] = loginBra;
					        	String rateCoverName =totalValue("select description from cover_master where cover_id=? and status='Y' and BRANCH_CODE=?",args1);
					        	rate=new CommodityCountryRateDAO().getCommodityCountryRate(loginCode, productId, applicationNo, modeOfTransport, coverId, commodityRatesArray[i][1], fromCountryId, toCountryId,openCoverNo);
					        	if(rate.equals(""))
					        	{
					        		 double dou_SITmp=Double.parseDouble(commodityRatesArray[i][2]);
										System.out.println("dou_SI   "+dou_SITmp);
										
					        		//This is SUM INSURED LOCAL
									double dou_SIAEDTmp=dou_SITmp*Double.parseDouble(currencyValue);
									System.out.println("dou_SIAED   "+dou_SIAEDTmp);
									//This is Sale Term Percentage
									double saleFactorTmp=(salePercentage/100);
									System.out.println("saleFactor   "+saleFactorTmp);
									 System.out.println("Mode:"+modeOfTransport);
									 System.out.println("Cover:"+coverId);
									 System.out.println("conveyance:"+seaOption);
									 System.out.println("toCountry:"+toCountryId);
									 double dou_saleValueTmp=(dou_SIAEDTmp*saleFactorTmp);
									 System.out.println("sumInsL:"+(dou_saleValueTmp));
									 System.out.println("sumInsLAIED:"+(dou_SIAEDTmp));
									 System.out.println("Login branch::"+loginBra);
									 /*** Corrected the value for SumInsuredLocal Parameter for rate calculation*/
									 /*** Hari 29Jun2012 Start**/
									double  conSItemp=dou_SIAEDTmp+dou_saleValueTmp;
									double tolFactorTEmp = (tolPercentage/100);
										//This is tolerance Charges
									double 	dou_tolValueTemp=(conSItemp*tolFactorTEmp);
									 CommodityRateCalculator calculator = new CommodityRateCalculator();
									 conSItemp=conSItemp+dou_tolValueTemp;	
					        		if("3".equalsIgnoreCase(productId))
					        		{
					        			String args[] = new String[3];
					        			args[0] = commodityRatesArray[i][1];
					        			args[1] = loginBra;
					        			args[2] = loginBra;
					        			//rate=totalValue("select case when status='R' then 0 else nvl("+rateCoverName+",'0') end from commoditymaster where commodity_id=? and BRANCH_CODE=? and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and BRANCH_CODE=? group by commodity_id)",args);
					        			 //added by hari to get values from commodityrate calculator
										
										

											System.out.println(" dou_tolValue   "+dou_tolValueTemp);
											System.out.println(" dou_totTolValue   "+dou_totTolValue);
											System.out.println(" b4 add tolerance conSI   "+conSItemp);
											//conSItemp=conSItemp+dou_tolValueTemp;
											System.out.println(" after add tolerance conSI   "+conSItemp);
											 /*** Corrected the value for SumInsuredLocal Parameter for rate calculation*/
											 /*** Hari 29Jun2012  End **/
											 rate = calculator.getCommodityRate(modeOfTransport, coverId,seaOption , toCountryId, commodityRatesArray[i][1],commodityRatesArray[i][4],(conSItemp==0)?dou_SIAEDTmp:conSItemp, loginBra,policyDate,applicationNo);
							        	     calculator.updateExcessPremium(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo,loginBra);
											 System.out.println("Rate for Commodity"+i+"===>"+rate);
					        		}
					        		else if ("11".equalsIgnoreCase(productId))
					        		{
					        			String args[] = new String[5];
					        			args[0] = openCoverNo;
					        			args[1] = openCoverNo;
					        			args[2] = commodityRatesArray[i][1];
					        			args[3] = firstDataName;
					        			args[4] = seaOption;
					        			String rateFetchingQuery="select nvl(occd.commodity_base_rate,0) from open_cover_commodity_detail occd,open_cover_position_master ocpm where ocpm.open_cover_no=? and occd.proposal_no=ocpm.proposal_no and occd.amend_id in (select max(occds.amend_id) from open_cover_commodity_detail occds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=occds.proposal_no and to_date(occds.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) and occd.commodity_id=? and occd.cover_id=? and occd.COVER_TYPE_ID=?";

					        			rate=totalValue(rateFetchingQuery,args);
					        			 calculator.updateExcessPremium(modeOfTransport, coverId, commodityRatesArray[i][1], commodityRatesArray[i][4], (conSItemp==0)?dou_SIAEDTmp:conSItemp,applicationNo,productId,openCoverNo,loginBra);
											
					        			rateFetchingQuery="";
					        		}
					        	}
						        if(rate.length()<=0||rate.equals(""))
									rate="0";
						        rate=""+Double.parseDouble(rate);
						   }
					        //rate=""+fmt1.format(Double.parseDouble(rate));
							try
							{
					        	if(!warehouseStatus)
								{
					        		if("YES".equalsIgnoreCase(warehouse) && "YES".equalsIgnoreCase(warehouse1))
									{
										if(Integer.parseInt(modeOfTransport)==1 || Integer.parseInt(modeOfTransport)==2)
										{
											 String args[] = new String[1];
											 args[0] = loginBra;
											String wareRates = totalValue("select nvl(LOAD_VALUE,'0') from REFERAL_MASTER where referal_id='17' and branch_code=?",args); 

											warehouseValue=""+(Double.parseDouble(rate)*(Double.parseDouble(wareRates)));
											System.out.println("RATE cheanges     "+warehouseValue);
											rate=""+fmt1.format(Double.parseDouble(rate));
										}
					        		}
					        	}
					        	System.out.println("AFTER  WAREHOUSE %"+rate);
					        }
					        catch(Exception e)
							{
					        	System.out.println("ERROR in WAREHOUSE %  "+e.toString());
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
								                 String LCLRate=null;
												 String args[] = new String[1];
												 args[0] = loginBra;
								                 LCLRate=totalValue("select nvl(LOAD_VALUE,'0') from REFERAL_MASTER where referal_id='16' and branch_code=?",args); 
								                 seaValue=""+(Double.parseDouble(rate)*(Double.parseDouble(LCLRate)/100));
								                 if("11".equalsIgnoreCase(productId))
												 {
								                 	LCLRate="0";
								                 	seaValue="0";
								                 }
								                 //rate=""+fmt1.format(Double.parseDouble(rate));
								                 System.out.println(" ROUND OF RATE AFTER LCL PERCENTAGE  "+seaValue);
							                }
							                System.out.println("RATE LCL   "+rate);
							           }
					        	}
						}
						catch(Exception e)
						{
							System.out.println(" Error Final Rate /100   "+e.toString());
							e.printStackTrace();
						}
						print("RATE",rate,"BEFORE WSRCC RATE");						
	        		  }
	        		  catch(Exception exception)
					  {
						rate="1";
						System.out.println("The Error is FETCHING PREMIUM COMPUTATION RATING ENGINE"+exception.toString());
						exception.printStackTrace();
					  }
	        		print("RATE",rate,"AFTER WSRCC RATE");					
	        		dou_SI=Double.parseDouble(commodityRatesArray[i][2]);
					System.out.println("dou_SI   "+dou_SI);
					
					//This is SUM INSURED LOCAL
					dou_SIAED=dou_SI*Double.parseDouble(currencyValue);
					System.out.println("dou_SIAED   "+dou_SIAED);
					
					//This is Sale Term Percentage
					saleFactor=(salePercentage/100);
					System.out.println("saleFactor   "+saleFactor);
					//This is Tolerance Percentage
					tolFactor=(tolPercentage/100);
					System.out.println("tolFactor   "+tolFactor);
					//This is Sale Term Charges
					dou_saleValue=(dou_SIAED*saleFactor);

					System.out.println(" dou_saleValue   "+dou_saleValue);
					dou_totSaleValue=dou_totSaleValue+dou_saleValue;
					System.out.println(" dou_totSaleValue   "+dou_totSaleValue);
					conSI=dou_SIAED+dou_saleValue;

					//This is tolerance Charges
					dou_tolValue=(conSI*tolFactor);

					System.out.println(" dou_tolValue   "+dou_tolValue);
					dou_totTolValue=dou_totTolValue+dou_tolValue;
					System.out.println(" dou_totTolValue   "+dou_totTolValue);
					System.out.println(" b4 add tolerance conSI   "+conSI);
					conSI=conSI+dou_tolValue;
					System.out.println(" after add tolerance conSI   "+conSI);

					dou_warPremium=(conSI*dou_warRate)/100;
					dou_warPremium=Double.parseDouble(fmtPremium.format(dou_warPremium));
					dou_totWarPremium=dou_totWarPremium+dou_warPremium;
					print("secondDataName",""+conSI,"CONVERTED SI AED");
					totalConSI=	totalConSI+conSI;
					print("getWsrcc()",getWsrcc(),"WAR STATUS");
					print("rate FINAL STAGE-@@@-",rate,"--@@@@-rate FINAL STAGE");
				
				    String freightPreStr="0";
			        double freightPre=0.0;
			        double freightRate = Double.parseDouble(rate);
			        if(freight.equalsIgnoreCase("Freight"))
					{
			        	freightPreStr = getLoadingofPremium(frieghtuser);
			        	if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
						{
			        		freightPre = Double.parseDouble(freightPreStr);
							freightRate = freightRate+(freightRate*(freightPre/100));
			        		rate = ""+freightRate;
			        	}
			        }
			        else if(getBrokerHasFreight(frieghtuser))
					{
						//	freightPreStr = getLoadingofPremium(getFreightLoginAppNo(applicationNo));
						freightPreStr = getLoadingofPremiumFreightBroker(applicationNo);
						if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
						{
							freightPre = Double.parseDouble(freightPreStr);
							freightRate = freightRate+(freightRate*(freightPre/100));
							rate = ""+freightRate;
						}
			        }

					// Freight Laoding Premium Commended by Rajesh //

					
					pre=((conSI*(Double.parseDouble(rate)+Double.parseDouble(warehouseValue)+Double.parseDouble(seaValue))/100));
					System.out.println("ROyal Test after cal pre.."+pre);
					pre=Double.parseDouble(fmtPremium.format(pre));
					print("secondDataName",""+pre,"PREMIUM");
					//pre=pre*Double.parseDouble(currencyValue);
					//print("secondDataName",""+pre,"PREMIUM AED");
					totalPre=	totalPre+pre;
					commodityRates.put(commodityRatesArray[i][1],rate);
					commodityRates.put("commodityId"+commodityRatesArray[i][1],commodityRatesArray[i][1]);
					commodityRates.put("sumInsured"+commodityRatesArray[i][1],commodityRatesArray[i][2]);
					commodityRates.put("convertedSumInsured"+commodityRatesArray[i][1],""+conSI);
					commodityRates.put("premium"+commodityRatesArray[i][1],""+pre);
//					if("0".equalsIgnoreCase(rate) || "0.0".equalsIgnoreCase(rate))
					if("0".equalsIgnoreCase(rate) || "0.0".equalsIgnoreCase(rate) || (Integer.parseInt(wsrcc)!=0 && dou_warRate==0))
					{
						referralStatus="Refferal";
						setReferral(referralStatus);
						pre=0.0;
						dou_saleValue=0.0;
						dou_tolValue=0.0;
						dou_warPremium=0.0;
						dou_warRate=0.0;
						//dou_SIAED=0.0;
						print("REFERRAL CHECK--",""+rate,commodityRatesArray[i][1]);
					}
					else
					{
						referralStatus="Normal";
						setReferral(referralStatus);
					}
					try
					{
						System.out.println("applicationNo -----------  "+applicationNo);
						System.out.println("rate --------------  "+rate);
						System.out.println("pre Final----------  "+pre);
						System.out.println("comId ----------  "+commodityRatesArray[i][1]);
						System.out.println("saleCharges ---------  "+dou_warPremium);
						System.out.println("referStatus -------  "+referralStatus);
						System.out.println("wsrccRate -------  "+dou_warRate);
						System.out.println("sumLocal -------  "+dou_SIAED);
						System.out.println("********END GETTING RATE FROM RATING  ****************");

						updatePremiumRates(applicationNo,rate,""+pre,commodityRatesArray[i][1],""+dou_saleValue,
								""+dou_warPremium,referralStatus,""+dou_warRate,""+dou_SIAED,warehouseValue,seaValue,""+dou_tolValue);
					}
					catch(Exception ec)
					{
						System.out.println("the EXCEPTION ISSSS  "+ec.toString());
						ec.printStackTrace();
					}
	        	  }
			   }
				//Rajesh For Freight Forwarder --this one old
				/*	String freightPreStr="0";
					double freightPre=0.0;
					if(freight.equalsIgnoreCase("Freight"))
					{
						freightPreStr = getLoadingofPremium(frieghtuser);
						if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
						{
							freightPre = Double.parseDouble(freightPreStr);
							totalPre = totalPre+(totalPre*(freightPre/100));
						}
					}
					else if(getBrokerHasFreight(frieghtuser))
					{
						//freightPreStr = getLoadingofPremium(getFreightLoginAppNo(applicationNo));
						freightPreStr = getLoadingofPremiumFreightBroker(applicationNo);
						if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
						{
							freightPre = Double.parseDouble(freightPreStr);
							totalPre = totalPre+(totalPre*(freightPre/100));
						}
					}*/
			commodityRates.put("totalCommodity",""+commodityRatesArray.length);
			commodityRates.put("totalConvertedSI",""+totalConSI);
			commodityRates.put("totalPremium",""+totalPre);
			setTotalWarPremium(""+dou_totWarPremium);
			setTotalSaleCharges(""+dou_totSaleValue);
			setTotalTolCharges(""+dou_totTolValue);
			print("totalPremium",""+totalPre,"totalPremium");
			print("totalWARPremium",""+dou_totWarPremium,"totalPremium");
			print("totalSALECHARGES",""+dou_totSaleValue,"totalPremium");
			print("setTotalTolCharges",""+dou_totTolValue,"totalPremium");
 	 }
	catch(Exception exception)
	{
		System.out.println("The Exception occured in PREMIUM COMPUTATION--"+exception.toString());
		exception.printStackTrace();
	}
	return commodityRates;
  }
  public void updatePremiumRates(String appNo,String rate,String pre,String comId,String saleCharges,String warCharges,String referStatus,String wsrccRate,String sumLocal,String warehouseValue,String seaValue,String tolValue)
   {
	   
		 String[] qryvalues = new String[17];
    	 try
		 {
			if(agreeStatus.equalsIgnoreCase("Yes"))
				agreeStatus = "I Agreed";
			else
				agreeStatus = "I Agreed";

			 String marineUpsql ="update marine_result_details set rate=?,premium=?,currency_id=?,exchange_id=?,currency_name=?,exchange_rate=?,sale_term_charges=?,war_charges=?,referral=?,warrate=?,SUMINSUREDLOCAL=?,rag=(select rag from commoditymaster where commodity_id='"+comId+"' and branch_code='"+loginBra+"' and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and branch_code='"+loginBra+"' group by commodity_id)),sea_option_lcl=?,warehouse_warehouse=?,TOLERANCE_CHARGES=?,agreed_status='"+agreeStatus+"' where  APPLICATION_NO=? and commodity_id=? and status=?";
				
			qryvalues[0] = rate;
			qryvalues[1] = pre;
			qryvalues[2] = currencyId;
			qryvalues[3] = exchangeId;
			qryvalues[4] = currencyName;
			qryvalues[5] = currencyValue;
			qryvalues[6] = saleCharges;
			qryvalues[7] = warCharges;
			qryvalues[8] = referStatus;
			qryvalues[9] = wsrccRate;
			qryvalues[10] = sumLocal;
			qryvalues[11] = seaValue;
			qryvalues[12] = warehouseValue;
			qryvalues[13] = tolValue;
			qryvalues[14] = appNo;
			qryvalues[15] = comId;
			qryvalues[16] = "Y";
			
			synchronized (this)
			{
				runner.multipleInsertion(marineUpsql, qryvalues);
			}
	  }
	  catch(Exception upExcep)
	  {
		System.out.println("Exception in update PremiumRates InforRRR :"+upExcep.toString());
		upExcep.printStackTrace();
	  }
   }
                  
   public String getQuoteBasedApplicationNo(String quoteId)
   {
	    String quoteBasedAppNo = "";
	    String getApplication = "";
		String args[] = new String[2];
		
	    try
		{
			/*args[0] = ""+Integer.parseInt(quoteId);
			args[1] = "Y";*/
			sqlQuery_ ="select pm.application_no as appNo from position_master pm where pm.quote_no=? and pm.status in ('Y','E')";
		    getApplication = runner.singleSelection(sqlQuery_,new String[]{quoteId});
		    print("getQuoteBasedApplicationNo",sqlQuery_,"QUERY");
		    if(getApplication.length() > 0)
			{
			  quoteBasedAppNo = getApplication;
		    }		      
	    }
	    catch(Exception upExcep)
		{
	    	System.out.println("Exception in getQuoteBasedApplicationNo Infor :"+upExcep.toString());
	    	upExcep.printStackTrace();
	    }
	    return quoteBasedAppNo;
   }

   public String getApplicationNoBasedOpenCoverNo(String appNo)
   {
	    String appNoBasedOpenCoverNo  = "";
	    String getOpenCoverNo  = "";
	    String args[] = new String[2];
	    String result[][] = new String[0][0];
		
		try
		{
			args[0]=""+Integer.parseInt(appNo);
			args[1] = "Y";

			sqlQuery_ ="select md.open_cover_no as openCoverNo from marine_data md where md.application_no=? and md.status=?";
			getOpenCoverNo = runner.singleSelection(sqlQuery_,args);
			print("getApplicationNoBasedOpenCoverNo",sqlQuery_,"QUERY");
			if(getOpenCoverNo.length() > 0)
			{
				appNoBasedOpenCoverNo = getOpenCoverNo;
			}
			print("appNoBasedOpenCoverNo",appNoBasedOpenCoverNo,"value");
			print("appNo",appNo,"value");
		}
		catch(Exception upExcep)
		{
			System.out.println("Exception in getApplicationNoBasedOpenCoverNo Infor :"+upExcep.toString());
			upExcep.printStackTrace();
		}
		return appNoBasedOpenCoverNo;
   }

    public void print(String methodName,String information,String type) {
	   	System.out.println(methodName+"<--->"+type+"<---->"+information);
    }

	public String getFromCountryId() {
		return fromCountryId;
	}
	public void setFromCountryId(String fromCountryId) {
		this.fromCountryId = fromCountryId;
	}

	public String getToCountryId() {
		return toCountryId;
	}
	public void setToCountryId(String toCountryId) {
		this.toCountryId = toCountryId;
	}
	public String getViaCountryId() 
	{
		return viaCountryId;
	}
	public void setViaCountryId(String viaCountryId) 
	{
		this.viaCountryId = viaCountryId;
	}
	public String getFromCityId() {
		return fromCityId;
	}
	public void setFromCityId(String fromCityId) {
		this.fromCityId = fromCityId;
	}

	public String getToCityId() {
		return toCityId;
	}	
	public void setToCityId(String toCityId) 
	{
		this.toCityId = toCityId;
	}
	public String getViaCityId() 
	{
		return viaCityId;
	}	
	public void setViaCityId(String viaCityId) {
		this.viaCityId = viaCityId;
	}
	public void updateTrackingDetails()
	{
		
		String[] qryvalues = new String[5];
		try
		{
			System.out.println("Application No :"+applicationNo);
			System.out.println("sessionId No :"+sessionId);
			System.out.println("loginCode No :"+loginCode);

			String trackQry =	"update TRACKING_MASTER set STAGE_ID=?,END_TIME=SYSDATE where APPLICATION_NO=? and STATUS=? and session_id=? and login_id=? ";
			
			qryvalues[0] = stageId;
			qryvalues[1] = applicationNo;
			qryvalues[2] = "Y";
			qryvalues[3] = sessionId;
			qryvalues[4] = loginCode;
			
			runner.multipleInsertion(trackQry, qryvalues);
			
		}
		catch(Exception e){
			System.out.println("Exception in updateTrackingDetails() PREMIUM SUMMARY " +"Insertion :"+e.toString());
			e.printStackTrace();
		}
	}

	public int getExistingTrackingStatus()
	{	
		int rows = 0;	
		String args[] = new String[3];
		String result = "";
		
		sqlQuery_ = "select count(*) from tracking_master where application_no=? and login_id=? and session_id=? and status='Y'";

		args[0] = applicationNo;
		args[1] = loginCode;
		args[2] = sessionId;
		try
		{
			result = runner.singleSelection(sqlQuery_,args);
			if(result.length()>0 && !result.equalsIgnoreCase("") && result != null && !result.equalsIgnoreCase("null") )
			{
				rows	=	Integer.parseInt(result);
			}
			else
			{
				rows	=	0;
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return rows;
	}

	public void insertTrackingDetails(String insertStatus)
	{
		int serialNoMax=0;
		String[] qryvalues = new String[8];
		
		try
		{
			serialNoMax = getMaximumSerialNO();
			
			System.out.println("loginCode :"+loginCode);
			System.out.println("Application No :"+applicationNo);
			System.out.println("Product ID :"+productId);
			String insTrack = "insert into TRACKING_MASTER (SERIAL_NO,SESSION_ID,LOGIN_ID,STAGE_ID,APPLICATION_NO,PRODUCT_ID,REMARKS,STATUS,START_TIME) values(?,?,?,?,?,?,?,?,SYSDATE)";
		
			qryvalues[0] = ""+serialNoMax;
			qryvalues[1] = sessionId;
			qryvalues[2] = loginCode;
			qryvalues[3] = "2";
			if("insideUpdate".equalsIgnoreCase(insertStatus))
				qryvalues[4] = applicationNo;
			else
				qryvalues[4] = ""+application_no;
			qryvalues[5] = productId;
			qryvalues[6] = "Remarks";
			qryvalues[7] = "Y";
			
			runner.multipleInsertion(insTrack, qryvalues);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getMaximumSerialNO()
	{
		int s_id = 1;	
		String sql ="select nvl(max(serial_no),'0')+1 from tracking_master" ;
		 try
		  {
				String temp = runner.singleSelection(sql);		
				if(temp.length()>0)
					s_id = Integer.parseInt(temp);
		  }
		  catch(Exception e)
		  {
			  System.out.println("the EXCEPTION IN "+e.toString());
		  }
		if(s_id==0){
			s_id=1;
		}
		return s_id;
	}

	public String getTotalSaleCharges() {
		return totalSaleCharges;
	}
	public void setTotalSaleCharges(String totalSaleCharges) {
		this.totalSaleCharges = totalSaleCharges;
	}
	public String getTotalTolCharges() {
		return totalTolCharges;
	}

	public void setTotalTolCharges(String totalTolCharges) {
		this.totalTolCharges = totalTolCharges;
	}

	public String getTotalWarPremium() {
		return totalWarPremium;
	}
	public void setTotalWarPremium(String totalWarPremium) {
		this.totalWarPremium = totalWarPremium;
	}
	
	public void updateMarineData(String appNo,String premium,String total_war_premium,String toal_sale_term)
	{
		String args[] = new String[4];
		String sql = "";
		
		try
		{
			args[0] = premium;
			args[1] = total_war_premium;
			args[2] = toal_sale_term;
			args[3] = appNo;
			sql="update marine_data set premium=?,total_war_charges=?,total_sale_term_charges=?, where application_no=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String totalValue(String query)
	{
		String returnVal="";
		try
		{
			returnVal = runner.singleSelection(query);
		}
		catch(Exception e)
		{
			System.out.println("Error in "+e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String totalValue(String query,String[] argss)
	{
		String returnVal="";
		try
		{
			returnVal = runner.singleSelection(query,argss);
		}
		catch(Exception e)
		{
			System.out.println("Error in "+e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String[][] getLimitedData(String loggedPersonId)
	{
		String[][] data	=	new String[0][0];
		String args[] = new String[1];
		String sql="";
		args[0] = loggedPersonId;
		
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select agency_code from login_master where login_id=?)";
		try
		{
			data = runner.multipleSelection(sql,args);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	public String[][] getLimitedData(String loggedPersonId,String pid) throws Exception
	{
		String[][] data =	new String[0][0];
		String sql ="";
		String args[] = new String[2];
		
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select agency_code from login_master where login_id=?) and product_id=?";
		try
		{
			args[0] = loggedPersonId;
			args[1] = pid;
			data = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
	      e.printStackTrace();
		}
		return data;
	}
	
	public String[][] getMinPrem(String loggedPersonId)
	{
		String[][] data =	new String[0][0];
		String sql = "";	
		String args[] = new String[1];
		
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select oa_code from login_master where login_id=?)";
		try
		{
			args[0] = loggedPersonId;
			data = runner.multipleSelection(sql,args);
		}
		catch(Exception e){
           e.printStackTrace();
		}
		return data;
	}
	
	public String[][] getMinPrem(String loggedPersonId,String pid)
	{
		String[][] data =	new String[0][0];
		String sql = "";
		String args[] = new String[2];
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select oa_code from login_master where login_id=?) and product_id=?";
		
		try
		{
			args[0] = loggedPersonId;
			args[1] = pid;
			data = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
           e.printStackTrace();
		}
		return data;
	}
	
	public String[][] getOpenCoverMiniPremium(String openCoverNoLocal)
	{
		String[][] getOpenCoverMasterDatas		=	new String[0][0];
		String getOpenCoverMasterDatas_Query	=	"";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			getOpenCoverMasterDatas_Query="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				
			
			getOpenCoverMasterDatas = runner.multipleSelection(getOpenCoverMasterDatas_Query,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getOpenCoverMasterDatas;
	}
	
	public void setExtraPremium(String appno,String extra_amount)
	{
		String args[] = new String[2];
		
		try
		{
			extra_amount = extra_amount==null?"0":extra_amount;
			System.out.println("seTTING EXTRA PREIMUM  ");
			String s = "";
			String sql = "";
			args[0] = extra_amount;
			args[1] = appno;
			sql = "update marine_data set excess_premium=? where application_no=?";
			s = runner.multipleUpdation(sql,args);
			args[0] = extra_amount;
			args[1] = appno;
			sql = "update position_master set excess_premium=? where application_no=?";
			s = runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getSeaBasedOptions()
	{	
		String modeIdValue   = "1";
		String modeIdOptions = "";	
		String args[] = new String[3];
		
		args[0] = openCoverNo;
		args[1] = openCoverNo;
		args[2] = modeIdValue;

		sqlQuery_ = "select distinct(cm.mode_transport_ID),nvl(ocsd.sea_options,'NO OPTIONS'),ocsd.amend_id from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY')) and ocsd.mode_transport_id=?";
		String businessSubList[][]	= new String[0][0];
		try
		{
			businessSubList = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e)
		{
			System.out.println("The Exception SeaBasedOptions==============>>>>"+e);
			e.printStackTrace();			
		}
		if(businessSubList.length>0)
		{
			modeIdOptions=businessSubList[0][1]==null?"NO OPTIONS":businessSubList[0][1];
		}
		else
		{
			modeIdOptions="NO OPTIONS";
		}
		return modeIdOptions;
	}

	public String[][] getCoverTypesValueBank()
	{
		String args[] = new String[1];
		args[0] = openCoverNo;
		
		sqlQuery_ = "select status,lc_id,lc_number,bank_id from open_cover_lc_master where status='Y' and open_cover_no=? order by lc_id";
		String businessSubList[][]	= new String[0][0];
		try
		{
			businessSubList = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e)
		{
			System.out.println("The Exception Occured==getCoverTypesValueBank==>>"+e);
			e.printStackTrace();
		}
		return businessSubList;
	}

	public String getLogins(String applicationNo)
	{
	   String args[] = new String[1];
	   String sql = "";
	   String loginId = "";
	   
	   args[0] = applicationNo;
	   sql = "select login_id from position_master where application_no=?";
	    try
		{
		   loginId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return loginId;
	}
	
	public String[][] getOpenCoverCountryWarOptions()
	{
		String[][] getOpenCoverCountry = new String[0][0];
		String openCoverCountryQuery   = "";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			openCoverCountryQuery="select occmss.OPEN_COVER_COUNTRY_ID_ORG,occmss.OPEN_COVER_COUNTRY_ID_DEST,occmss.open_cover_wsrcc_org,occmss.open_cover_wsrcc_dest,occmss.OPEN_COVER_WAREHOUSE_ORG,occmss.OPEN_COVER_WAREHOUSE_dest,occmss.war_rate from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			
			getOpenCoverCountry = runner.multipleSelection(openCoverCountryQuery,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in  getOpenCoverCountryWarOptions--------------->>>>"+e);
			e.printStackTrace();			
		}
		return getOpenCoverCountry;
	}

	public String[][] getOpenCoverMasterDatas(String openCoverNoLocal)
	{
		String[][] getOpenCoverMasterDatas   = new String[0][0];
		String getOpenCoverMasterDatas_Query = "";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
		    getOpenCoverMasterDatas_Query="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
		    getOpenCoverMasterDatas = runner.multipleSelection(getOpenCoverMasterDatas_Query,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in  getOpenCoverMasterDatas--------------->>>>"+e);
			e.printStackTrace();
		}
		return getOpenCoverMasterDatas;
	}

	public String[][] getOpenCoverDetailDatas(String conveyanceId)
	{
		String[][] getOpenCoverDetailDatas   = new String[0][0];
		String getOpenCoverDetailDatas_Query = "";
		String args[] = new String[3];
		
		try
		{
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = conveyanceId;
			getOpenCoverDetailDatas_Query="select  ocd.sum_insured_limit,ocd.mode_transport_id,ocd.exchange_id,ocd.currency_id,ocd.exchange_rate,ocd.currency_name,ocd.amend_id from open_cover_detail ocd,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocd.proposal_no and ocd.amend_id in (select max(ocds.amend_id) from open_cover_detail ocds,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocds.proposal_no and to_date(ocds.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) and ocd.mode_transport_id=?";
			getOpenCoverDetailDatas = runner.multipleSelection(getOpenCoverDetailDatas_Query,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in  getOpenCoverDetailDatas--------------->>>>"+e);
			e.printStackTrace();
		}
		return getOpenCoverDetailDatas;
	}

	public HashMap getWarRelatedOptions()
	{
		HashMap fullWarOptionsHash  =	new HashMap();
		HashMap wsrccOrgHash		=	new HashMap();
		HashMap wsrccDestHash		=	new HashMap();
		HashMap wareHouseOrgHash	=	new HashMap();
		HashMap wareHouseDestHash	=	new HashMap();
		HashMap warRateHash		    =	new HashMap();

		String[][] wsrccOrg			=	new String[0][0];
		String[][] wsrccDest		=	new String[0][0];
		String[][] wareHouseOrg		=	new String[0][0];
		String[][] wareHouseDest	=	new String[0][0];
		String[][] warRate			=	new String[0][0];
		String[][] fullWarOptions	=	new String[0][0];
		fullWarOptions				=	getOpenCoverCountryWarOptions();
		
		if(fullWarOptions.length>0)
		{
			wsrccOrg=makeTwoDimArray(fullWarOptions[0][2]==null?"0~0#":fullWarOptions[0][2]);
			if(wsrccOrg.length>0)
			{
				fullWarOptionsHash.put("wsrccOrg",wsrccOrg);
				for(int i=0;i<wsrccOrg.length;i++)
				{
					wsrccOrgHash.put(wsrccOrg[i][0]==null?"0":wsrccOrg[i][0],wsrccOrg[i][1]==null?"0":wsrccOrg[i][1]);
				}
				fullWarOptionsHash.put("wsrccOrgHash",wsrccOrgHash);
			}
			wsrccDest=makeTwoDimArray(fullWarOptions[0][3]==null?"0~0#":fullWarOptions[0][3]);
			if(wsrccDest.length>0)
			{
				fullWarOptionsHash.put("wsrccDest",wsrccDest);
				for(int i=0;i<wsrccDest.length;i++)
				{
					wsrccDestHash.put(wsrccDest[i][0]==null?"0":wsrccDest[i][0],wsrccDest[i][1]==null?"0":wsrccDest[i][1]);
				}
				fullWarOptionsHash.put("wsrccDestHash",wsrccDestHash);
			}
			wareHouseOrg=makeTwoDimArray(fullWarOptions[0][4]==null?"0~0#":fullWarOptions[0][4]);
			if(wareHouseOrg.length>0)
			{
				fullWarOptionsHash.put("wareHouseOrg",wareHouseOrg);
				for(int i=0;i<wareHouseOrg.length;i++)
				{
				wareHouseOrgHash.put(wareHouseOrg[i][0]==null?"0":wareHouseOrg[i][0],wareHouseOrg[i][1]==null?"0":wareHouseOrg[i][1]);
				}
				fullWarOptionsHash.put("wareHouseOrgHash",wareHouseOrgHash);
			}
			wareHouseDest=makeTwoDimArray(fullWarOptions[0][5]==null?"0~0#":fullWarOptions[0][5]);
			if(wareHouseDest.length>0)
			{
				fullWarOptionsHash.put("wareHouseDest",wareHouseDest);
				for(int i=0;i<wareHouseDest.length;i++)
				{
			wareHouseDestHash.put(wareHouseDest[i][0]==null?"0":wareHouseDest[i][0],wareHouseDest[i][1]==null?"0":wareHouseDest[i][1]);
				}
				fullWarOptionsHash.put("wareHouseDestHash",wareHouseDestHash);
			}
			warRate=makeTwoDimArray(fullWarOptions[0][6]==null?"0~0#":fullWarOptions[0][6]);
			HashMap warConstants=new HashMap();
			String[][] warRateConstants	= new String[0][0];
			String warRateConstantsString="";
			if(warRate.length>0)
			{
				fullWarOptionsHash.put("warRate",warRate);
				System.out.println("Royal Test in getWarRelatedOptions.."+warRate.length);
				for(int i=0;i<warRate.length;i++)
				{
					warRateHash.put(warRate[i][0]==null?"0":warRate[i][0],warRate[i][1]==null?"0":warRate[i][1]);
					String[] countryChecking={"65","121"};
					warConstants=(HashMap)getCountriesRate("destination");
					if(warConstants.size()>0)
					{
						for(int k=0;k<countryChecking.length;k++)
						{
							if((warRate[i][0]==null?"0":warRate[i][0]).equalsIgnoreCase(countryChecking[k]))
							{
								warRateConstantsString=(String)warConstants.get(countryChecking[k])==null?"0~0#":(String)warConstants.get(countryChecking[k]);
								warRateConstants=makeTwoDimArray(warRateConstantsString);
								for(int j=0;j<warRateConstants.length;j++)
								{
									System.out.println("the Country from Constants is "+warRateConstants[j][0]+"the Rate is "+warRate[i][1]);
									warRateHash.put(warRateConstants[j][0]==null?"0":warRateConstants[j][0],warRate[i][1]==null?"0":warRate[i][1]);
								}
							}
						}
					}
				}
				fullWarOptionsHash.put("warRateHash",warRateHash);
			}
		}
		return fullWarOptionsHash;
	}

	public HashMap getCountriesRate(String CountryStatus)
	{
		String[][] getsCheckCountry			=	new String[0][0];
		String[][] getsCountry				=	new String[0][0];
		String countryNewlyAdded			=	""; 
		String countryNewlyAddedWar			=	""; 
		String countryAdded					=	"0"; 
		String countryFinalList				=	""; 
		String countryCheckQuery			=	""; 
		String countryQuery					=	""; 
		HashMap getConstantCountriesList	=	new HashMap();		
		String[] countryChecking			=	{"65","121"};
		String openCoverCountry[][] = new String[0][0];
		openCoverCountry = getOpenCoverCountry(CountryStatus);
		
		if(openCoverCountry.length>0)
			countryAdded = openCoverCountry[0][0];
		getConstantCountriesList.put("fromOpenCoverMaster",countryAdded);
		try
		{
			 	for (int i=0;i<countryChecking.length ;i++ )
				{
					try
					{
						countryCheckQuery="select count(*) from country_master_detail where country_id in("+countryAdded+") and country_id in("+countryChecking[i]+") and BELONGING_COUNTRY_ID='"+cid+"'";
						getsCheckCountry = runner.multipleSelection(countryCheckQuery);
						if(getsCheckCountry.length > 0)
						{
							if(Integer.parseInt(getsCheckCountry[0][0]==null?"0":getsCheckCountry[0][0]) > 0)
							{
								getsCountry=null;
								countryQuery="select rsacode,detail_name from constant_detail where branch_code='"+loginBra+"' and  category_id=(select category_id from constant_master where rsacode=('"+countryChecking[i]+"') and branch_code='"+loginBra+"') order by rsacode";
								getsCountry = runner.multipleSelection(countryQuery);
								if(getsCountry.length > 0)
								{
									for (int k=0;k<getsCountry.length ;k++ )
									{
										countryNewlyAdded=countryNewlyAdded+getsCountry[k][0]+",";	
										countryNewlyAddedWar=countryNewlyAddedWar+getsCountry[k][0]+"~0#";	
										getConstantCountriesList.put(countryChecking[i],getsCountry[k][0]);
									}
								}
							}
						}
						if(countryNewlyAddedWar.length() > 0 )
						{
							countryNewlyAddedWar=countryNewlyAddedWar.substring(0,countryNewlyAddedWar.length()-1);
						}
						else
						{
							countryNewlyAddedWar="0~0#";
						}
						getConstantCountriesList.put(countryChecking[i],countryNewlyAddedWar);
						countryNewlyAddedWar="";
					}
					catch(Exception e)
					{
						System.out.println("The getCountries --------------->>>>"+e);
						e.printStackTrace();
					}
				}
				
				countryFinalList=countryNewlyAdded+countryAdded;
				if(countryNewlyAdded.length() > 0 )
				{
					countryNewlyAdded = countryNewlyAdded.substring(0,countryNewlyAdded.length()-1);
				}
				else
				{
					countryNewlyAdded="0";
				}
				getConstantCountriesList.put("fromConstantCountries",countryNewlyAdded);
				getConstantCountriesList.put("finalCountries",countryFinalList);
		}
		catch(Exception e)
		{
			System.out.println("The getCountries --------------->>>>"+e);
			e.printStackTrace();
		}
		return getConstantCountriesList;
	}

	public String[][] getOpenCoverCountry(String status)
	{
			String[][] getOpenCoverCountry	=	new String[0][0];
			String openCoverCountryQuery	=	"";
			String args[] = new String[2];
			
			try
			{
				if("Origination".equalsIgnoreCase(status))
				{				
					openCoverCountryQuery="select occmss.OPEN_COVER_COUNTRY_ID_ORG from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				}
				else if ("Destination".equalsIgnoreCase(status))
				{
					openCoverCountryQuery="select occmss.OPEN_COVER_COUNTRY_ID_DEST from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				}
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				getOpenCoverCountry = runner.multipleSelection(openCoverCountryQuery,args);
			}
			catch(Exception e)
			{
				System.out.println("Exception in  getOpenCoverCountry -->>"+e);
				e.printStackTrace();
			}
			return getOpenCoverCountry;	
	}

	public String[][] makeTwoDimArray(String combined)
	{
		int count  		=	0;
		int jcount		=	0;
		int arrCount	=	0;
		int arrJCount	=	0;		
		
		StringTokenizer st1=new StringTokenizer(combined,"#");		
		String[][] convertedArray=new String[st1.countTokens()][3];		
		while (st1.hasMoreTokens()){
			String w1 = st1.nextToken();
			
			StringTokenizer st2=new StringTokenizer(w1,"~");
			while (st2.hasMoreTokens()){
				String w2 = st2.nextToken();
				
				convertedArray[count][jcount]=w2;
				jcount=jcount+1;
			}
			jcount=0;
			count=count+1;		
		}
		return convertedArray;
	}

	public String[][] makeTwoDimArray(String combined,String firstLimiter,String secondLimiter)
	{
		int count		=	0;
		int jcount		=	0;
		int arrCount	=	0;
		int arrJCount	=	0;		
		
		StringTokenizer st1=new StringTokenizer(combined,secondLimiter);
		String[][] convertedArray=new String[st1.countTokens()][3];
		while (st1.hasMoreTokens()){
					String w1 = st1.nextToken();
				
					StringTokenizer st2=new StringTokenizer(w1,firstLimiter);
					while (st2.hasMoreTokens()){
						String w2 = st2.nextToken();
						convertedArray[count][jcount]=w2;
						jcount=jcount+1;
					}
					jcount=0;
					count=count+1;
		}
		return convertedArray;
	}
	//For Open Cover Only
	public void insertLcDetails(String insertStatus)
	{
		String lcId			=	"";
		try
		{
			lcId = ""+getMaximumId(openCoverNo,"LCID");
			String sql ="insert into open_cover_Lc_MASTER (open_cover_no,bank_ID,lc_id,lc_number,lc_date,lc_amount,amend_id,entry_date,effective_date,remarks,status) values(?,?,?,?,?,?,?,?,?,?,?)";
			String args[] = new String[11];
			args[0] = openCoverNo;
			args[1] = bankName;
			args[2] = lcId;
			args[3] = lcNumber;
			args[4] = lcDate;
			args[5] = lcAmount;
			args[6] = "0";
			args[7] = lcEntryDate;
			args[8] = lcEffectiveDate;
			args[9] = "Remarks";
			args[10] = "Y";
			runner.multipleInsertion(sql,args);
			
		}
		catch(Exception e)
		{
			System.out.println("Exception in insertLcDetails()  Insertion :"+e.toString());
			e.printStackTrace();
		}
	}
	public int getMaximumId(String openNo,String queryStatus)
	{
		int  s_id =	1;
		String sql = "";

		if("LCID".equalsIgnoreCase(queryStatus))
		{
			sql ="select nvl(max(lc_id),'0')+1 from open_cover_lc_master where open_cover_No=?";
		  try
		  {
			  String args[] = new String[1];
			  args[0] = openNo;
			  String temp = runner.singleSelection(sql,args);		
			  if(temp.length()>0)
				s_id = Integer.parseInt(temp);
		  }
		  catch(Exception e)
		  {
			  System.out.println("the EXCEPTION IN "+e.toString());
		  }
			if(s_id==0)
			{
				s_id=1;
			}
		}
		return s_id;
	}
	
	public String[][] getLCBasedTotalSumInsured(String bankId,String lcNo,String openCoverNo)
	{
		String[][] getLcBasedSumInsured=new String[0][0];	
		String args[] = new String[3];
		String sql = "";
		
		try
		{
			args[0] = lcNo;
			args[1] = bankId;
			args[2] = openCoverNo;
			
			sql = "select sum(mrd.SUMINSUREDLOCAL+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES) from marine_data md,MARINE_RESULT_DETAILS mrd,position_master pm where md.application_no=pm.application_no and md.application_no=mrd.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=?";
			getLcBasedSumInsured = runner.multipleSelection(sql,args);
			
			if(getLcBasedSumInsured == null)
				getLcBasedSumInsured = new String[0][0];
		}
		catch(Exception e)
		{
			System.out.println("The getLCBased TotalSumInsured --------------->>>>"+e);
			e.printStackTrace();
		}
		return getLcBasedSumInsured;
	}

	public String[][] getsLCDetails(String bankId,String lcNo,String openCoverNo)
	{
		String[][] getsCountry=new String[0][0];
		String lcQuery = "";
		String args[] = new String[3];
		
		try
		{
			args[0] = lcNo;
			args[1] = bankId;
			args[2] = openCoverNo;

			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,oplm.lc_date,cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em where oplm.status='Y' and lc_id=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where to_char(cms.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where to_date(ocbms.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where to_char(ems.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";

			getsCountry = runner.multipleSelection(lcQuery,args);
			if(getsCountry.length>0){
			}
			else
			{
				getsCountry=new String[0][0];
			}
		}
		catch(Exception e){
			System.out.println("The Get getsLCDetails Value Is --------------->>>>"+e);
		}
		return getsCountry;
	}
	
	//Rajesh For Freight Forwarder
	public String getLoadingofPremium(String user)
	{
		String args[] = new String[1];
	    String query = "";
		args[0] = user;
		query = "select LOADING_OF_PREMIUM from LOGIN_USER_DETAILS where LOGIN_ID=? and product_id='3' and ((PROVISION_FOR_PREMIUM='D' and FREIGHT_RATE_OPTION='Y') or FREIGHT_AUOTO_OPTION='Y')";
        
        String result="";
        try
		{
             result = runner.singleSelection(query,args);
			 System.out.println("Loading premium percentage ...."+result);
		}
        catch(Exception ex) {
               ex.printStackTrace();
        }
        return result;
    }
	
	public String getLoadingofPremiumFreightBroker(String appno)
	{
		String args[] = new String[1];
        String query ="";
		args[0] = appno;
		query = "select LOADING_OF_PREMIUM from LOGIN_USER_DETAILS where LOGIN_ID in(select f.login_id from FREIGHT_POSITION_MASTER f,POSITION_MASTER p where f.APPLICATION_ID=? and f.quote_no=p.quote_no and p.FREIGHT_STATUS='F') and product_id='3' and ((PROVISION_FOR_PREMIUM='D' and FREIGHT_RATE_OPTION='Y') or FREIGHT_AUOTO_OPTION='Y')";
           
           String result	=	"";
           try 
		   {        	   
        	   result = runner.singleSelection(query,args);
        	   System.out.println("Loading premium percentage ...."+result);
           }
           catch(Exception ex){
               ex.printStackTrace();
           }
           return result;
    }
	
	public String getPremiumProvision(String user)
	{
		String args[] = new String[1];
        String query =""; 
		query ="select PROVISION_FOR_PREMIUM from LOGIN_USER_DETAILS where LOGIN_ID=? and product_id='3'";
		args[0] = user;
		
		String result="";
		try
		{
			result = runner.singleSelection(query,args);
			System.out.println("Loading premium provision ...."+result);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	   return result;
    }

	public String getDirectPremiumProvision(String user)
	{
          String query = "";
		  String args[] = new String[1];
		  args[0] = user;
		  query = "select FREIGHT_RATE_OPTION from LOGIN_USER_DETAILS where LOGIN_ID=? and product_id='3'";
          
          String result="";
          try
		  {
             result = runner.singleSelection(query,args);
			 System.out.println("Loading premium provision ...."+result);
		  }
          catch(Exception ex)
		  {
               ex.printStackTrace();
          }
           return result;
    }

	public String[][] getTodaysDate()
	{
	   String query = "";
	   query = "select extract(year from sysdate) ,extract(month from sysdate),extract(day from sysdate) from dual";
		
		String[][] result=new String[0][0];
	   try
	   {
		  result = runner.multipleSelection(query);
	   }
	   catch(Exception ex){
		   ex.printStackTrace();
	   }
	   return result;
    }
	
	public String getBackDatesAllowed(String user, String pid)
	{
		
		String result 		= "0";
		String backDateQry 	= "";
		String args[] = new String[2];
		try
		{
			args[0] = user;
			args[1] = pid;
			backDateQry = "select BACK_DATE_ALLOWED from login_user_details where agency_code in(select agency_code from login_master where login_id=?) and product_id=?";
			result = runner.singleSelection(backDateQry,args);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		if(result==null)
			result = "0";
		return result;
     }
		 
	 public String getUserType(String user) throws Exception
	 {
       
	   String res="";
	   String args[] = new String[1] ;
	   args[0] = user;
	   String sql = "";
	   sql = "select usertype from login_master where login_id = ?";
		try{
		 res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		 {
			e.printStackTrace();
		 }
       return res;
	 }
	 
	 public boolean getFreightPolicyStatus(String qno) throws Exception
	 {
       
	   String res="";
	   String sql = "";
	   String args[] = new String[1];
	   args[0] = qno;
	   sql = "select ALLOW_TO_GENERATE_POLICY_STS from FREIGHT_POSITION_MASTER where QUOTE_NO=? and status='A'";
	   try
	   {
		   res = runner.singleSelection(sql,args);
		   if(res==null)
			   res = "";
		   if(res.equalsIgnoreCase("Y"))
			   return true;
		   else if(res.equalsIgnoreCase("N"))
			   return false;
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}  
	 
	public boolean getBrokerHasFreight(String user) throws Exception
	{
       
	   String res = "";
	   String args[] = new String[1];
	   String sql = "";
	   try{
		   args[0] = user;
		   sql = "select count(*) from login_master where OA_CODE in(select AGENCY_CODE from LOGIN_USER_DETAILS where LOGIN_ID=?) and usertype='Freight'";
			res = runner.singleSelection(sql,args);
			if(res!=null)
			{
				if(Integer.parseInt(res)>0)
					return true;
				else
					return false;
		    }
		    else
			   return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public String[][] getFreightMinPrem(String loginId)
	{
		String[][] data=new String[0][0];
		String args[] = new String[1];
		args[0] = loginId;
		
		String sql="select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select agency_code from login_master where login_id=?)";
		try{
			data= runner.multipleSelection(sql,args);
		}
		catch(Exception e){
			System.out.println("Exception in gettin limited data"+e.toString());
			e.printStackTrace();
		}
		return data;
	}
	
	public boolean getFreightQuoteStatus(String QuoteNo) throws Exception
	{
       
	   String res="";
	   String args[] = new String[1];
	   String sql = "";
	   try{
		   args[0] = QuoteNo;
		   sql = "select FREIGHT_STATUS from POSITION_MASTER where QUOTE_NO=?";
			res = runner.singleSelection(sql,args);
		    if(res!=null){
				if(res.equalsIgnoreCase("F"))
					return true;
				else
					return false;
		   }
		   else
			   return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;			
		}
	}
	
	public String[][] getFreightMinPremQuoteNo(String qno) throws Exception
	{
		String[][] data=new String[0][0];
		String args[] = new String[1];
		String sql="";
		
		
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?)";
		try{
			args[0] = qno;
			data= runner.multipleSelection(sql,args);
		}
		catch(Exception e){
			System.out.println("Exception in gettin limited data"+e.toString());
			e.printStackTrace();
		}
		return data;
	}
	
	public String getFreightStatusQuoteNo(String qno)
	{
		String data = "";
		String args[] = new String[1];
		String sql = "";
		args[0] = qno;
		
		sql = "select nvl(PROVISION_FOR_PREMIUM,'') from LOGIN_USER_DETAILS where login_id=(select login_id from POSITION_MASTER where QUOTE_NO=?) and product_id='3'";
		try{
			System.out.println("Admin side Freight Provision Selection"+sql);
			data= runner.singleSelection(sql,args);
		}
		catch(Exception e){
			System.out.println("Exception in gettin getFreightStatusQuoteNo data"+e.toString());
			e.printStackTrace();
		}
		return data;
	}
	
	public String getErrormsg(String errorCode,String description)
	{
		String result = "";
		String sql = "";
		String args[] = new String[1];
		
		try
		{
			args[0] = errorCode;
			sql = "select error_desc from error_master where error_id=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Error in ERROR INFO UNDER COMMON ERROR FOLDER ..."+e.toString());
			result="Please Provide Valid Input for "+description;
		}
		return result;
	}

	public String[][] getFreightLoadDiscount(String appNo)
	{
		String values[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		
		try
		{
			args[0] = appNo;
			sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.LOADING_OF_PREMIUM,lud.DISCOUNT_OF_PREMIUM from login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and lud.login_id=lm.login_id and lud.login_id=(select login_id from FREIGHT_POSITION_MASTER where APPLICATION_ID=?) and lud.product_id='3'";
			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getFreightLoadDiscount.. "+e.toString());
			e.printStackTrace();
		}
		return values;
	}
	
	/*** Import Export Minimum Cross Premium ***/
	
	public String[][] getOpenCoverExportMiniPremium(String openCoverNoLocal)
	{
		String[][] ExportMiniPremium		=	new String[0][0];
		String ExportMiniPremiumQry	=	"";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			ExportMiniPremiumQry="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.EXPORT_MIN_PREMIUM_AMOUNT,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			ExportMiniPremium = runner.multipleSelection(ExportMiniPremiumQry,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ExportMiniPremium;
	}
	
	public String[][] getOpenCoverCrossMiniPremium(String openCoverNoLocal)
	{
		String[][] ExportMiniPremium		=	new String[0][0];
		String ExportMiniPremiumQry	=	"";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			ExportMiniPremiumQry="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.CROSS_MIN_PREMIUM_AMOUNT,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			ExportMiniPremium = runner.multipleSelection(ExportMiniPremiumQry,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ExportMiniPremium;
	}
	
	public String[][] getOpenCoverImportMiniPremium(String openCoverNoLocal)
	{
		String[][] ImportMiniPremium		=	new String[0][0];
		String ImportMiniPremiumQry	=	"";
		String args[] = new String[2];
		
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			ImportMiniPremiumQry="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.IMPORT_MIN_PREMIUM_AMOUNT,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				ImportMiniPremium = runner.multipleSelection(ImportMiniPremiumQry,args);
		}
		catch(Exception e)
		{
			System.out.println("getOpenCoverImportMiniPremium "+e.toString());
			e.printStackTrace();
		}
		return ImportMiniPremium;
	}

	public HashMap getCommodityType(String branch)
	{
		HashMap comType = new HashMap();
		String[][] values = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try
		{
			args[0] = branch;
			qry = "select category_detail_id,detail_name from constant_detail where category_id ='42' and branch_code=?";
			values = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error "+e.toString());
		}

		if(values.length > 0)
		{
			for(int i=0;i<values.length;i++)
				comType.put(""+values[i][0],values[i][1]);
		}
		return comType;
	}
	
	public String getComExcesPremOption(String appNo,String cid)
	{
		String qry="";
		String values[][] = new String[0][0];
		String comExcesPremOption="";
		String args[] = new String[1];
		
		try
		{
			args[0] = appNo;
			qry = "select MODE_OF_TRANSPORT,TRANSIT_FROM_COUNTRY_ID,FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA where application_no=?";
			values = runner.multipleSelection(qry,args);
			System.out.println("getComExcesPremOption "+qry);
		}
		catch(Exception e)
		{
			System.out.println("getComExcesPremOption"+e.toString());
			e.printStackTrace();
		}
		if(values.length >0)
		{
			if(values[0][0].equalsIgnoreCase("1") || values[0][0].equalsIgnoreCase("2") )
			{
				if( values[0][1].equalsIgnoreCase(cid) && !values[0][2].equalsIgnoreCase(cid) )
					comExcesPremOption = "Y";
				else
					comExcesPremOption="N";
			}
			else
				comExcesPremOption="N";
		}
		System.out.println("getComExcesPremOption final Status"+comExcesPremOption);
		return comExcesPremOption;
	}

	public String getFreightRedirection(String login)
	{
		String sql = "";
		String args[] = new String[1];
		args[0] = login;
		
		sql = "select nvl(FREIGHT_ADMIN_OPTION,'N') from LOGIN_USER_DETAILS where login_id=?";
		String result="";
		result = runner.singleSelection(sql,args);
		return result;
	}
	
	public String getFreightRedirectionQuoteNo(String qno)
	{
		String sql = "";
		String args[] = new String[1];
		args[0] = qno;
		
		sql = "select nvl(FREIGHT_ADMIN_OPTION,'N') from LOGIN_USER_DETAILS where login_id=(select login_id from position_master where quote_no=?)";
		String result="";
		result = runner.singleSelection(sql,args);
		return result;
	}
	
	public void updatePolicyStatus(String qno)
	{
		String sql = "";
		String args[] = new String[1];
		args[0] = qno;
		
		sql = "update FREIGHT_POSITION_MASTER set ALLOW_TO_GENERATE_POLICY_STS='Y' where QUOTE_NO=?";
		runner.multipleUpdation(sql,args);
	}
	
	public String getWSRCOpt(String proNo)
	{
		String sql = "";
		String args[] = new String[2];
		args[0] = proNo;
		args[1] = proNo;
		
		sql = "select nvl(wrsc_YN,'Y') from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=?) and amend_id=(select max(amend_id) from open_cover_master where proposal_no=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=?))";
		String res="N";
		res = runner.singleSelection(sql,args);
		return res;
	}
		
	public String[][] getModeTypes(String loginBra)
	{	
	   String businessList[][]	=	new String[0][0];
	   StringBuffer busi = new StringBuffer();
	   String args[] = new String[0];
	   
	   try
	   {
			if("11".equalsIgnoreCase(productId))
			{
			  sqlQuery_="select md.mode_transport_ID, md.transport_description,ocd.amend_id,ocd.effective_date from mode_of_transport md,open_cover_detail ocd,open_cover_position_master ocpm where md.status='Y' and  md.mode_transport_Id=ocd.mode_transport_id and ocd.status='Y' and ocpm.open_cover_no=? and  ocpm.proposal_no=ocd.proposal_no and to_date(ocd.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY') and md.BRANCH_CODE=? and ocd.amend_id = (select max(ocds.amend_id) from open_cover_detail ocds, open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocds.proposal_no and to_date(ocds.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) order by md.display_order ";
			  args = new String[3];
			  args[0] = openCoverNo;
			  args[1] = loginBra;
			  args[2] = openCoverNo;
			  businessList	= runner.multipleSelection(sqlQuery_,args);
		   }
		   else if("3".equalsIgnoreCase(productId))
		   {
			  sqlQuery_="select mode_transport_ID, transport_description from mode_of_transport where status='Y' and BRANCH_CODE=? order by mode_transport_id  ";
			  args = new String[1];
			  args[0] = loginBra;
			  businessList	=runner.multipleSelection(sqlQuery_,args);
		   }
			
			for(int i = 0; i < businessList.length;i++)
			{
				categoryMainMasterId.append("'"+businessList[i][0]+"',");
				categoryMasterName.append("'"+businessList[i][1]+"',");//doublescript code end
			}
		if(categoryMainMasterId.length()>0)
			categoryMainMasterId.deleteCharAt(categoryMainMasterId.length()-1);
		if(categoryMasterName.length()>0)
			categoryMasterName.deleteCharAt(categoryMasterName.length()-1);
	}
	catch(Exception e){
		System.out.println("2746 Exception BusinessList : "+e);
		e.printStackTrace();
	}
	return businessList;
   }

  public String[][] getCoverTypes(String loginBra)
  {
	  System.out.println("Inside get Covers:");
	     String businessSubList[][]	=	new String[0][0];
		 String args[] = new String[0];
		 
	     try
		 {
			if("11".equalsIgnoreCase(productId))
			{
				sqlQuery_ = "select cm.mode_transport_ID, cm.cover_id,cm.cover_name,ocsd.amend_id,ocsd.effective_date from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and cm.BRANCH_CODE=? and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY'))";
				 args = new String[3];
				 args[0] = openCoverNo;
				 args[1] = loginBra;
				 args[2] = openCoverNo;
				 businessSubList = runner.multipleSelection(sqlQuery_,args);
			 }
			 else if("3".equalsIgnoreCase(productId))
			 {
				 sqlQuery_ = "select mode_transport_ID,cover_id,cover_name from cover_master where status='Y' and BRANCH_CODE=? order by DISPLAY_ORDER";
				 args = new String[1];
				 args[0] = loginBra;
			 	 businessSubList = runner.multipleSelection(sqlQuery_,args);
			 }
	    	 for(int i = 0; i < businessSubList.length;i++)
			 {
			 	businessSubList[i][2]=StringUtil.upperFirstChar(businessSubList[i][2].length()>50?businessSubList[i][2].substring(0,50):businessSubList[i][2]);
	    		 	modeMasterId.append("'"+businessSubList[i][0]+"',");
	    		 	coverId.append("'"+businessSubList[i][1]+"',");
	    		 	coverName.append("'"+businessSubList[i][2]+"',");//dou
	    	 }
			 if(modeMasterId.length()>0)
	    		modeMasterId.deleteCharAt(modeMasterId.length()-1);
			 if(coverId.length()>0)
	    		coverId.deleteCharAt(coverId.length()-1);
			 if(coverName.length()>0)
	    		coverName.deleteCharAt(coverName.length()-1);
	    }
	    catch(Exception e)
		{
		     System.out.println("545 Exception BusinessList : "+e);
		     e.printStackTrace();
	    }
	    return businessSubList;
    }
  public String[][] getPackageTypes(String loginBra)
  {
	  System.out.println("Inside get Packages:" +loginBra);
	    
		 String args[] = new String[0];
		 String seaCovers[][] = new String[0][0];
	     try
		 {
			if("11".equalsIgnoreCase(productId))
			{
				 sqlQuery_ ="select MODE_TRANSPORT_ID,SNO con1,CONVDESC  con2 from CONVEYAN_MASTER a where  amend_id= (select max(amend_id) from CONVEYAN_MASTER b where branch_code=? and b.CONVDESC= a.CONVDESC) and branch_code=? and status='Y' order by CONVDESC";
				 args = new String[2];
				 args[0] = loginBra;
				 args[1] = loginBra;
				 seaCovers = runner.multipleSelection(sqlQuery_,args);
			 }
			 else if("3".equalsIgnoreCase(productId))
			 {
				 sqlQuery_ ="select MODE_TRANSPORT_ID,SNO con1,CONVDESC  con2 from CONVEYAN_MASTER a where  amend_id= (select max(amend_id) from CONVEYAN_MASTER b where branch_code=? and b.CONVDESC= a.CONVDESC) and branch_code=? and status='Y'  order by CONVDESC";
				 args = new String[2];
				 args[0] = loginBra;
				 args[1] = loginBra;
				 seaCovers = runner.multipleSelection(sqlQuery_,args);
			 }
	    	 for(int i = 0; i < seaCovers.length;i++)
			 {
	    		 seaCovers[i][2]=StringUtil.upperFirstChar(seaCovers[i][2].length()>50?seaCovers[i][2].substring(0,50):seaCovers[i][2]);
	    		 	modePackageId.append("'"+seaCovers[i][0]+"',");
	    		 	packageId.append("'"+seaCovers[i][1]+"',");
	    		 	packageName.append("'"+seaCovers[i][2]+"',");//dou
	    	 }
			 if(modePackageId.length()>0)
				 modePackageId.deleteCharAt(modePackageId.length()-1);
			 if(packageId.length()>0)
				 packageId.deleteCharAt(packageId.length()-1);
			 if(packageName.length()>0)
				 packageName.deleteCharAt(packageName.length()-1);
	    }
	    catch(Exception e)
		{
		     System.out.println("545 Exception BusinessList : "+e);
		     e.printStackTrace();
	    }
	    return seaCovers;
    }
	public String[][] getCoverTypesValue(String loginBra)
    {
		String businessSubList[][]	=	new String[0][0];
		String args[] = new String[0];
		
		try
		{
			if("3".equalsIgnoreCase(productId))
			{
				sqlQuery_ = "select * from cover_master where status='Y' and BRANCH_CODE=? order by DISPLAY_ORDER";
				args = new String[1];
				args[0] = loginBra;
				businessSubList = runner.multipleSelection(sqlQuery_,args);
			}
			else if ("11".equalsIgnoreCase(productId))
			{
				sqlQuery_ = "select ocsd.amend_id,cm.cover_id,cm.cover_name,cm.mode_transport_ID,ocsd.effective_date from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and cm.BRANCH_CODE=? and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY'))";
				 args = new String[3];
				 args[0] = openCoverNo;
				 args[1] = loginBra;
				 args[2] = openCoverNo;
				 businessSubList = runner.multipleSelection(sqlQuery_,args);
			}
		}
		catch(Exception e){
			System.out.println("The Exception Occured==============>>>>"+e);
			e.printStackTrace();
		}
		return businessSubList;
    }

	public String[][] getSaleTermDetails(String loginBra)
	{
		//sqlQuery_  = "select sale_term_id, sale_term_name from sale_term_master where status='Y' and BRANCH_CODE=? order by SNO__";
		sqlQuery_  = "select sale_term_id, sale_term_name from sale_term_master where status='Y' and BRANCH_CODE=? order by sale_term_name";
		String args[] = new String[1];
		args[0] = loginBra;
		
		String channeldetails[][] = new String[0][0];
		try
		{
			channeldetails = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e){
           e.printStackTrace();
		}
		return channeldetails;
	}

	public String[][] getToleranceDetails(String loginBra)
	{
		sqlQuery_ = "select TOLERANCE_ID, TOLERANCE_NAME from TOLERANCE_MASTER where status='Y' and BRANCH_CODE=? order by SNO__";
		String args[] = new String[1];
		args[0] = loginBra;
		
		String channeldetails[][] = new String[0][0];
		try
		{
			channeldetails = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e)
		{
           e.printStackTrace();
		}
		return channeldetails;
	}

	public String[][] getCurrencyDetails(String countryId)	
	{
			String channeldetails[][] = new String[0][0];
			String args[] = new String[1];
			
			//sqlQuery_ = "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID='"+countryId+"' and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID='"+countryId+"' group by exchange_id,currency_id)  and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' order by a.DISPLAY_ORDER";
//			sqlQuery_ ="select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id) and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' and  b.amend_id||b.currency_id in(select max(amend_id)||currency_id from currency_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by currency_id) order by a.DISPLAY_ORDER";
			//sqlQuery_ ="select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where SYSDATE + 10 / 24 between effective_date and expiry_date and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id) and SYSDATE + 10 / 24 between a.effective_date and a.expiry_date and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' and  b.amend_id||b.currency_id in(select max(amend_id)||currency_id from currency_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by currency_id) order by b.currency_name";
			sqlQuery_ ="SELECT em.exchange_rate,cm.currency_name, em.amend_id, em.effective_date, em.currency_id FROM currency_master cm,exchange_master em WHERE cm.country_id=? and cm.status='Y' AND cm.amend_id=(SELECT MAX(amend_id) FROM currency_master WHERE status='Y' and currency_id=cm.currency_id AND TRUNC(effective_date)<=TRUNC(SYSDATE)) AND TRUNC(cm.effective_date)<=TRUNC(SYSDATE) AND cm.currency_id=em.currency_id and cm.country_id=em.country_id AND em.amend_id=(SELECT MAX(amend_id) FROM exchange_master WHERE currency_id=em.currency_id and country_id=em.country_id AND TRUNC(effective_date)<=TRUNC(SYSDATE)) AND TRUNC(em.effective_date)<=TRUNC(SYSDATE) order by  cm.currency_name";
			System.out.println("the GET CURRENCY DETAILS"+sqlQuery_);
		
			try
			{
				args[0] = countryId;
				//args[1] = countryId;
				//args[2] = countryId;
				channeldetails = runner.multipleSelection(sqlQuery_,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return channeldetails;
	}

	public String[][] getCurrencyDetailsHidden(String countryId)	
	{
			String channeldetails[][] = new String[0][0];
			String args[] = new String[3];
			
			//sqlQuery_ = "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID='"+countryId+"' and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and COUNTRY_ID='"+countryId+"' and status='Y' group by exchange_id,currency_id)  and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' order by b.display_order";
			sqlQuery_ ="select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id) and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' and  b.amend_id||b.currency_id in(select max(amend_id)||currency_id from currency_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by currency_id) order by a.DISPLAY_ORDER";
			System.out.println("the GET CURRENCY DETAILS"+sqlQuery_);
			try
			{
				args[0] = countryId;
				args[1] = countryId;
				args[2] = countryId;
				channeldetails = runner.multipleSelection(sqlQuery_,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return channeldetails;
	}
	
   public String[][] getCurrencyRates(String currenId,String countryId)
   {

		String[][] currRates = new String[0][0];
		String sql_Curr  ="";
		String args[] = new String[4];
		
		sql_Curr= "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id)  and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' and b.currency_id=? and  b.amend_id||b.currency_id in(select max(amend_id)||currency_id from currency_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by currency_id)";
		try
		{
			args[0] = countryId;
			args[1] = countryId;
			args[2] = currenId;
			args[3] = countryId;

			currRates = runner.multipleSelection(sql_Curr,args);
			if(currRates.length > 0 ){
			}
			else{
				currRates = new String[0][0];
			}
		}
		catch(Exception e){
			System.out.println("Exception in getCurrencyRates"+e.toString());
			e.printStackTrace();
		}
		return currRates;
	}

	public String[][] getsLCDetails(String bankId,String lcNo,String openCoverNo,String cid)
	{
		String[][] getsCountry=new String[0][0];
		String lcQuery="";
		String args[] = new String[8];
		
		try
		{
			args[0] = lcNo;
			args[1] = cid;
			args[2] = bankId;
			args[3] = cid;
			args[4] = openCoverNo;
			args[5] = cid;
			args[6] = cid;
			args[7] = cid;

//			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,oplm.lc_date,cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em  where oplm.status='Y' and lc_id=? and bm.BELONGING_COUNTRY_ID=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and cm.COUNTRY_ID=em.COUNTRY_ID and cm.COUNTRY_ID=? and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where to_char(cms.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') and cms.COUNTRY_ID=? group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where to_date(ocbms.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocbms.BELONGING_COUNTRY_ID=? group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where to_char(ems.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE+10/24,'dd-mm-YYYY') and ems.COUNTRY_ID=? group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";
			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,oplm.lc_date,cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em  where oplm.status='Y' and lc_id=? and bm.BELONGING_COUNTRY_ID=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and cm.COUNTRY_ID=em.COUNTRY_ID and cm.COUNTRY_ID=? and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where trunc(cms.effective_date)<=trunc(SYSDATE) and cms.COUNTRY_ID=? group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where trunc(ocbms.effective_date) <=trunc(SYSDATE) and ocbms.BELONGING_COUNTRY_ID=? group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where trunc(ems.effective_date)<=trunc(SYSDATE) and ems.COUNTRY_ID=? group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";
			getsCountry = runner.multipleSelection(lcQuery,args);
			if(getsCountry.length>0){
			}
			else{
				getsCountry=new String[0][0];
			}
		}
		catch(Exception e){
			System.out.println("The Get getsLCDetails Value Is --------------->>>>"+e);
		}
		return getsCountry;
	}

	public String getStartingPlace(String countryId,String cid)
	{
		String[][] result=new String[0][0];
		String args[] = new String[2];
		String sql = "";
//		sql = "select starting_place,ending_place from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?";
		sql = "SELECT STARTING_PLACE,ENDING_PLACE FROM COUNTRY_MASTER_DETAIL A WHERE A.COUNTRY_ID=? AND A.BELONGING_COUNTRY_ID=? AND A.AMEND_ID=(SELECT MAX(B.AMEND_ID) FROM COUNTRY_MASTER_DETAIL B WHERE B.COUNTRY_ID=A.COUNTRY_ID AND A.BELONGING_COUNTRY_ID=A.BELONGING_COUNTRY_ID)";
		args[0] = countryId;
		args[1] = cid;
		result = runner.multipleSelection(sql,args);
		if(result.length>0){
		       return result[0][0]==null?"":result[0][0];
		}
		else{
		       return "";
		}
	}
	
	public String getEndingPlace(String countryId,String cid)
	{
		String[][] result=new String[0][0];
		String args[] = new String[2];
		String sql = "";
		
//		sql = "select starting_place,ending_place from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?";
		sql = "SELECT STARTING_PLACE,ENDING_PLACE FROM COUNTRY_MASTER_DETAIL A WHERE A.COUNTRY_ID=? AND A.BELONGING_COUNTRY_ID=? AND A.AMEND_ID=(SELECT MAX(B.AMEND_ID) FROM COUNTRY_MASTER_DETAIL B WHERE B.COUNTRY_ID=A.COUNTRY_ID AND A.BELONGING_COUNTRY_ID=A.BELONGING_COUNTRY_ID)";
		args[0] = countryId;
		args[1] = cid;
		result = runner.multipleSelection(sql,args);
		if(result.length>0){
			return result[0][1]==null?"":result[0][1];
		}
		else{
			return "";
		}
	}

	public HashMap getCurrencyCoverInfo(String exRate,String modeId,String coverName,String executiveId,String cid,String loginBra)
	{
		HashMap currencyCoverInfo=new HashMap();
		String args[] = new String[0];
		String result[][] = new String[0][0];
		
		try
		{
			try
			{
				args = new String[4];
				args[0] = cid;
				args[1] = exRate;
				args[2] = exRate;
				args[3] = cid;

				sqlQuery_=  "select * from exchange_master em,currency_master cm where em.currency_id=cm.currency_id and cm.country_id=em.country_id and cm.country_id=? and em.status=cm.status and em.status='Y' and cm.status='Y' and em.currency_id=? and em.amend_id=(select max(amend_id) from exchange_master where currency_id=? and country_id=? and status='Y')";
				result = runner.multipleSelection(sqlQuery_,args);

				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("exChangeId",result[i][0]);
					print("Exchange Id",result[i][0],"info");
					currencyCoverInfo.put("currencyId",result[i][2]);
					print("cm.currency_id",result[i][2],"info");
					currencyCoverInfo.put("currencyName",result[i][15]);
					print("cm.currency_name",result[i][15],"info");
				}
			} 
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getCurrencyInfo--"+exception.toString());
				exception.printStackTrace();
			}		
			try
			{
				args = new String[3];
				args[0] = modeId;
				args[1] = coverName;
				args[2] = loginBra;

				sqlQuery_=	"select cm.cover_id,cm.cover_name,cm.mode_transport_id from cover_master cm where cm.mode_transport_id=? and cm.cover_id=? and cm.status='Y' and cm.BRANCH_CODE=?";
				
				result = runner.multipleSelection(sqlQuery_,args);
				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("coverId",result[i][0]);
				}
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getModeCoverInfo--"+exception.toString());
				exception.printStackTrace();
			}
			try
			{
				args = new String[1];
				args[0] = executiveId;
				sqlQuery_=	"select * from login_executive_details where ac_executive_id=? and Status='Y'" ;
				result = runner.multipleSelection(sqlQuery_,args);
				for(int i=0;i<result.length;i++)
				{
					currencyCoverInfo.put("executiveName",result[i][1]);
				}
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured in getModeCoverInfo--"+exception.toString());
				exception.printStackTrace(); 
			}
		}
		catch(Exception exception)
		{
				System.out.println("The Exception occured in getCurrencyCoverInfo--"+exception.toString());
				exception.printStackTrace(); 
		}
		return currencyCoverInfo;
	}

	public String getSeaBasedOptions(String loginBra)
	{	
		String modeIdValue   = "1";
		String modeIdOptions = "";	
		String args[] = new String[4];
		
		args[0] = loginBra;
		args[1] = openCoverNo;
		args[2] = openCoverNo;
		args[3] = modeIdValue;

		sqlQuery_ = "select distinct(cm.mode_transport_ID),nvl(ocsd.sea_options,'NO OPTIONS'),ocsd.amend_id from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.branch_code=? and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY')) and ocsd.mode_transport_id=?";
		String businessSubList[][]	=	new String[0][0];
		try
		{
			businessSubList = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e){
			System.out.println("The Exception SeaBasedOptions==>>"+e);
			e.printStackTrace();			
		}
		if(businessSubList.length>0){
			modeIdOptions=businessSubList[0][1]==null?"NO OPTIONS":businessSubList[0][1];
		}
		else{
			modeIdOptions="NO OPTIONS";
		}
		return modeIdOptions;
	}

	public HashMap getToleranceMaster(String loginBra)
	{
		String args[] = new String[1];
		args[0] = loginBra;
		sqlQuery_  = "select TOLERANCE_ID,TOLERANCE_NAME from TOLERANCE_MASTER where status='Y' and BRANCH_CODE=? order by SNO__";
		String channeldetails[][] = new String[0][0];
		HashMap tolerance = new HashMap();
		
		try
		{
			channeldetails = runner.multipleSelection(sqlQuery_,args);
		}
		catch(Exception e)
		{
		   System.out.println("getToleranceMaster"+e.toString());
           e.printStackTrace();
		}
		for(int i=0;i<channeldetails.length;i++)
		{
			channeldetails[i][0] = channeldetails[i][0] == null?"":channeldetails[i][0];
			channeldetails[i][1] = channeldetails[i][1] == null?"":channeldetails[i][1];
			tolerance.put(channeldetails[i][0],channeldetails[i][1]);
		}
		return tolerance;
	}

	public String[][] getReferralDetailsMaster(String loginBra)
	{
		//String sql = "select REFERAL_ID,status from REFERAL_MASTER where branch_code='"+loginBra+"' and EFFECTIVE_START_DATE<(select sysdate from dual) and EFFECTIVE_END_DATE>(select sysdate from dual) and amend_id=(select max(amend_id) from REFERAL_MASTER where branch_code='"+loginBra+"' and EFFECTIVE_START_DATE<(select sysdate from dual) and EFFECTIVE_END_DATE>(select sysdate from dual)) order by REFERAL_ID";
		String args[] = new String[2];
		String sql ="";
		
		args[0] = loginBra;
		args[1] = loginBra;
		sql = "select REFERAL_ID,status from REFERAL_MASTER where branch_code=? and EFFECTIVE_START_DATE<(select sysdate from dual) and amend_id=(select max(amend_id) from REFERAL_MASTER where branch_code=? and EFFECTIVE_START_DATE<(select sysdate from dual)) order by REFERAL_ID";

		String result[][] = new String[0][0];
		try
		{
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception E)
		{
			System.out.println("getReferralDetailsMaster "+E.toString());
			E.printStackTrace();
		}
		return result;
		
	}

	public String[][] getBrokerLoadDiscount(String loginId)
	{
		String values[][] = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		
		try
		{
			args[0] = loginId;

			sql = "select lud.PRODUCT_ID,lud.COMMISSION,lud.LOADING_OF_PREMIUM,lud.DISCOUNT_OF_PREMIUM,lud.MIN_PREMIUM_AMOUNT from login_master lm,login_user_details lud where lm.agency_code=lud.agency_code and lud.login_id=lm.login_id and lud.agency_code =(select oa_code from login_master where login_id=?) and lud.product_id='3'";
			values = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getFreightLoadDiscount.. "+e.toString());
			e.printStackTrace();
		}
		return values;
	}

	public boolean validPartialShipAmount(String value)
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890.";
				for(int i=0;i<value.length();i++)
				{
					if(validChar.indexOf(value.charAt(i))== -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("Premium Inputs Bean Partial Shipment Value "+value);
			return flag;
		}
		System.out.println("Premium Inputs Bean Partial Shipment Value "+ flag);
		return flag;
	}

	// SumInsured Referral
	public double getConvertedTotalSumInsured(String saleTerm,String tolerance,String sumIns,String currencyValue)
	{
		double dou_SI                  = 0.0;
		double dou_SIAED               = 0.0;
		double dou_saleValue           = 0.0;
		double dou_tolValue            = 0.0;
		double dou_totSaleValue        = 0.0;
		double dou_totTolValue         = 0.0;
		double saleFactor              = 0.0;
		double tolFactor               = 0.0;
		double salePercentage          = 0.0;
		double tolPercentage           = 0.0;
		double conSI                   = 0.0;
		double pre                     = 0.0;
		double totalConSI              = 0.0;
		
		salePercentage = getSaleTermValue(saleTerm);
		tolPercentage  = getToleranceValue(tolerance);

		dou_SI = Double.parseDouble(sumIns);
		dou_SIAED = dou_SI*Double.parseDouble(currencyValue);
		saleFactor = (salePercentage/100);
		tolFactor = (tolPercentage/100);
		dou_saleValue = (dou_SIAED*saleFactor);
		dou_totSaleValue = dou_totSaleValue+dou_saleValue;
		conSI = dou_SIAED+dou_saleValue;
		dou_tolValue = (conSI*tolFactor);
		dou_totTolValue = dou_totTolValue+dou_tolValue;
		conSI = conSI+dou_tolValue;

		return conSI;
	}
	//The below methods are from opencovercopyBean, those all methods are called from premium_porint_123.jsp
	public String[][] getPrintPage(String openCoverNo,String branch)
   {
   	 String[][] printCover  = new String[0][0];
   		try
		{
   		  
   		  String sqlQuery = "select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no= (select proposal_no from open_cover_position_master where open_cover_no=?) and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no= (select proposal_no from open_cover_position_master where open_cover_no=?)) and a.status='Y' and  c.cover_id=a.cover_id and b.mode_transport_id=a.mode_transport_id and (b.branch_code=? and c.branch_code=?)order by a.cover_id";
   			String args[] = new String[4];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = branch;
			args[3] = branch;
		   printCover = runner.multipleSelection(sqlQuery,args);
   	 }
   	 catch(Exception e81){
   		 System.out.println("Error in selection getPrintPage method"+e81);
   		 e81.printStackTrace();
   	 }   	 
   	 return printCover;
    }
	public String[][] getWSRCC(String openCoverNo)
    {
    	String wsrcc[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[2];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			wsrcc = runner.multipleSelection("select warranty_id,warranty_description,extra_cover_id from open_cover_warranties where	 proposal_no=(select proposal_no from open_cover_position_master where open_cover_no =?) and amend_id=(select max(amend_id) from open_cover_warranties where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=?))",args);
		}
		catch(Exception e81){
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   	 }   
    	return wsrcc;
    }
	public String[][] getCommodityClauses(String openCoverNo,String brokerBra,int sea,int air,int multimode)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[3];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = brokerBra;
			commodities = runner.multipleSelection("select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occmss.amend_id) from open_cover_clauses occmss where occmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?)  and occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0) ) and ocpmss.proposal_no=occmss.proposal_no and occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0 and branch_code=?) and occmss.extra_cover_id in ("+sea+","+air+","+multimode+")",args);
		}
		catch(Exception e81){
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   	 }   
    	return commodities;
    }
	public String[][] getFreeText(String openCoverNo,String seaoption)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[3];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = seaoption;
			commodities = runner.multipleSelection("select free_text_id,free_text_description from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=?) and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=?) and cover_id = ? and free_text_description != '9999')",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
    public String[][] getFreeTextIds(String openCoverNo,String coverId)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[3];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = coverId;
			commodities = runner.multipleSelection("select free_text_id,free_text_description,cover_id from open_cover_free_text where proposal_no=  (select proposal_no from open_cover_position_master where open_cover_no=?)  and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=?) and cover_id=?) and free_text_description != '9999'",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getExCoverIds(String quote_no,String brokerBra)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[2];
			args[0] = quote_no;
			args[1] = brokerBra;
			commodities = runner.multipleSelection("select md.cover_id,md.open_cover_no,md.extra_cover_id, ecm.extra_cover_name from marine_data md, extra_cover_master ecm where md.application_no= (select application_no from position_master where quote_no= ?)and md.extra_cover_id=ecm.extra_cover_id and ecm.branch_code=?",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getCoverClauses(String openCoverNo,String cover,String brokerBra,String extraCover)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[6];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = cover;
			args[3] = cover;
			args[4] = brokerBra;
			args[5] = cover;
			commodities = runner.multipleSelection("select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occmss.amend_id) from open_cover_clauses occmss where occmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and occmss.cover_id=? and occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0 and cover_id=? and branch_code=?)) and ocpmss.proposal_no=occmss.proposal_no and occmss.extra_cover_id in('"+extraCover+"') and occmss.cover_id=?",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getCoverClausesDetails(String quote_no,String brokerBra)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[2];
			args[0] = quote_no;
			args[1] = brokerBra;
			commodities = runner.multipleSelection("select md.cover_id,md.open_cover_no,md.extra_cover_id,md.mode_of_transport, md.sea_options, ecm.extra_cover_name  from marine_data md, extra_cover_master ecm where md.application_no= (select application_no from position_master where quote_no=?)and md.extra_cover_id=ecm.extra_cover_id and branch_code=?",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getExclusionDetails(String openCoverNo)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[2];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			commodities = runner.multipleSelection("select occmss.exclusion_id,occmss.exclusion_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.proposal_no =(select proposal_no from open_cover_position_master where open_cover_no=?) and ocpms.proposal_no=occms.proposal_no)",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getOpenDetails(String openCoverNo,String cover)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[3];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			args[2] = cover;
			commodities = runner.multipleSelection("select clauses_id,clauses_description from open_cover_clauses where status='Y' and extra_cover_id='0' and proposal_no = (select proposal_no from open_cover_position_master where open_cover_no=?) and amend_id = (select max(amend_id) from open_cover_clauses where status='Y' and proposal_no =  (select proposal_no from open_cover_position_master where open_cover_no=?) and cover_id =?)",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String[][] getOpenIdDetails(String quote_no)
    {
    	String commodities[][]= new String[0][0];    	
    	
		try
		{
			String args[] = new String[1];
			args[0] = quote_no;
			commodities = runner.multipleSelection("select md.cover_id,md.open_cover_no,md.extra_cover_id from marine_data md where md.application_no= (select application_no from position_master where quote_no=?)",args);
		}
		catch(Exception e81)
		{
   		 System.out.println("Error in selection ..."+e81);
   		 e81.printStackTrace();
   		}   
    	return commodities;
    }
	public String getLcDateCheck(String policyDay,String policyMonth,String policyYear,String bankName,String lcNumber,String openCoverNo)
	{
		String sql = "select count(*) from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and BANK_ID=? and LC_ID=? and LC_DATE<=to_date(?,'dd-mm-yyyy')+1";
		String error="";
		String res="";
		try
		{
			String result[][] = new String[0][0];
			String args[] = new String[4];
			args[0] = openCoverNo;
			args[1] = bankName;
			args[2] = lcNumber;
			args[3] = (policyDay+"-"+policyMonth+"-"+policyYear);
			res = runner.singleSelection(sql,args);
			if(res.equals("0"))
			{
				String args1[] = new String[3];
				args1[0] = openCoverNo;
				args1[1] = bankName;
				args1[2] = lcNumber;
				sql = "select to_char(EXPIRY_DATE,'dd'),to_char(EXPIRY_DATE,'mm'),to_char(EXPIRY_DATE,'YYYY'),to_char(LC_DATE,'dd'),to_char(LC_DATE,'mm'),to_char(LC_DATE,'YYYY') from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and BANK_ID=? and LC_ID=?";
				result = runner.multipleSelection(sql,args1);
				if(result.length>0)
				{					
					String lcDay1 = result[0][3]!=null?result[0][3]:"0";
					String lcMonth1 = result[0][4]!=null?result[0][4]:"0";
					String lcYear1 = result[0][5]!=null?result[0][5]:"0";

					//error="<br><font color=red size=2><b>* Certificate Start Date can not be greater than LC Expiry Date "+lcDay+"/"+lcMonth+"/"+lcYear+" and can not be less than LC Start Date "+lcDay1+"/"+lcMonth1+"/"+lcYear1+"</b></font><br>";
					error="<br><font color=red size=2><b>* Certificate Start Date can not be less than LC Start Date "+lcDay1+"/"+lcMonth1+"/"+lcYear1+"</b></font><br>";
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return error;
	}
	public boolean getViaStatus(String branchCode)
	{
		boolean status = false;
		String sql = "select count(*) from CONSTANT_DETAIL where CATEGORY_ID='61' and CATEGORY_DETAIL_ID='1' and status='Y' and branch_code=?";
		String result = "";
		try
		{
			String args[] = new String[1];
			args[0] = branchCode;
			result = runner.singleSelection(sql,args);
			if(result.length()>0)
			{
				if(result.equals("0"))
					status = false;
				else
					status = true;
			}
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}

	public String[][] getTodaysDate(String branch)
	{
        String query = "";
        String[][] result = new String[0][0];
		String temp = "sysdate";
		String sql = "";
		String hour = "";
		String args[] = new String[0];
        try
		{
			args = new String[4];
			args[0] = "62";
			args[1] = "1";
			args[2] = "Y";
			args[3] = branch;
			sql = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? and branch_code=?";
			hour = runner.singleSelection(sql,args);
			if(hour.length() > 0)
				temp = temp + "+" + hour;
			//query = "select extract(day from sysdate+8/24),extract(month from sysdate),extract(year from sysdate) from dual";
			query = "select extract(day from "+temp+"),to_char("+temp+",'Mon'),extract(year from "+temp+") from dual";
            result = runner.multipleSelection(query);
        }
        catch(Exception ex)
		{
			System.out.println("getTodaysDate..."+ex);
            ex.printStackTrace();
        }
        return result;
    }
	
	public boolean commodityValidation(String applicationNo,String openCoverNo,String proId,String branch)
	{
		String args[] = new String[0];
		boolean comFlag = false;
		String sql = "";
		String appComId[][] = new String[0][0];
		String totComId[][] = new String[0][0];
		System.out.println("applicationNo..."+applicationNo);
		System.out.println("openCoverNo..."+openCoverNo);
		System.out.println("proId..."+proId);
		System.out.println("branch..."+branch);
		try
		{
			args = new String[1];
			args[0] = applicationNo;
			sql = "select commodity_id from marine_result_details where application_no=? order by commodity_id";
			appComId = runner.multipleSelection(sql,args);
			System.out.println("appComId..."+appComId.length);
			if(appComId.length > 0)
			{
				if(proId.equalsIgnoreCase("3"))
				{
					args = new String[3];
					args[0] = applicationNo;
					args[1] = branch;
					args[2] = branch;
					sql = "select commodity_id from commoditymaster where commodity_id in (select commodity_id from marine_result_details where application_no=?) and BRANCH_CODE=? and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and BRANCH_CODE=? group by commodity_id) order by commodity_id ";
					totComId = runner.multipleSelection(sql,args);
				}
				else if(proId.equalsIgnoreCase("11"))
				{
					args = new String[3];
					args[0] = openCoverNo;
					args[1] = openCoverNo;
					args[2] = applicationNo;
					sql = "select distinct occd.commodity_id from open_cover_commodity_detail occd,open_cover_position_master ocpm where ocpm.open_cover_no=? and occd.proposal_no=ocpm.proposal_no and occd.amend_id in (select max(occds.amend_id) from open_cover_commodity_detail occds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=occds.proposal_no and to_date(occds.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) and occd.commodity_id in(select commodity_id from marine_result_details where application_no=?) order by occd.commodity_id";
					totComId = runner.multipleSelection(sql,args);
				}
				System.out.println("appComId..."+totComId.length);
				if(appComId.length > 0 && totComId.length > 0 && appComId.length == totComId.length )
				{
					for(int i=0;i<appComId.length;i++)
					{
						if(appComId[i][0].equalsIgnoreCase(totComId[i][0]))
								comFlag = true;
						else
						{
							comFlag = false;
							break;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			comFlag = false;
			System.out.println("commodityValidation ....."+e);
			e.printStackTrace();
			return comFlag;
		}
		System.out.println("commodityValidation  Flag is "+comFlag);
		return comFlag;
	}
	public String[][] getCommoditysForAdmin(String appNo,String login)
	{
		String result[][] = new String[0][0];
		com.maan.session.LoginSession logSess = new  com.maan.session.LoginSession();
		logSess.setUser(login);
		if(logSess.isAdminType()){
			try{
				String args[] = {appNo};
				result = runner.multipleSelection("select commodity_id,rate,warrate,warehouse_warehouse,sea_option_lcl from marine_result_details where application_no=?",args);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getWarRateForMOC(String destCountryId,final String openCoverNo)
	{
		String warRate ="";
		String destCountryIdTemp = "";
		
		try{
			/*final String argss[] = {loginBra,destCountryId,"8"};
			final String qry = "select count(*) from constant_detail where branch_code=? and rsacode=? and category_id=?";
			final String counter = runner.singleSelection(qry,argss);
			System.out.println("Original Country Id ..."+destCountryId);
			if (counter.equals("1")){
				destCountryId ="65";
			}*/
			if(destCountryId.length() > 0){
				destCountryIdTemp = destCountryId+"~";
			}
			final String args[] = {destCountryIdTemp,destCountryIdTemp,destCountryIdTemp,openCoverNo,openCoverNo};
			final String sql ="select substr(occmss.war_rate||'#',instr(occmss.war_rate||'#','~',instr(occmss.war_rate,?))+1,instr(occmss.war_rate||'#','#',instr(occmss.war_rate,?))-instr(occmss.war_rate||'#','~',instr(occmss.war_rate||'#',?))-1) from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no=? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			System.out.println("getWarRateForMOC......"+sql);
			System.out.println("getWarRateForMOC destCountryId......"+destCountryId);
			System.out.println("getWarRateForMOC openCoverNo......"+openCoverNo);
			warRate = runner.singleSelection(sql,args);
			
			/*if(warRate == null || warRate.equals("") || warRate.length()==0 || warRate.equals("null"))
			{
				final String argss[] = {loginBra,destCountryId,"8"};
				final String qry = "select count(*) from constant_detail where branch_code=? and rsacode=? and category_id=?";
				final String counter = runner.singleSelection(qry,argss);
				System.out.println("Original Country Id GCC Checking ..."+destCountryId);
				if (counter.equals("1")){
					destCountryId ="65";
				}
				if(destCountryId.length() > 0){
					destCountryIdTemp = destCountryId+"~";
				}
				final String args1[] = {destCountryIdTemp,destCountryIdTemp,destCountryIdTemp,openCoverNo,openCoverNo};
				System.out.println("getWarRateForMOC GCC......"+sql);
				System.out.println("getWarRateForMOC destCountryId GCC......"+destCountryId);
				System.out.println("getWarRateForMOC openCoverNo GCC......"+openCoverNo);
				warRate = runner.singleSelection(sql,args1);
			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("getWarRateForMOC openCoverNo..warRate ...."+warRate);
		return warRate; 
	}
	
	public String[][] getSeaCoverValues(String branch)
	{
		String[][] seaCovers = new String[0][0];
		String args[] = new String[2];
		String qry = "";
		try
		{
			
			 args[0] = branch;
			 args[1] = branch;
			
//			qry = "select category_detail_id,detail_name from constant_detail where category_id='115' and branch_code=? order by category_detail_id";
			//qry = "select percent,detail_name,detail_name from constant_detail where category_id='132' and branch_code=? order by category_detail_id";
//			 qry ="select MODE_TRANSPORT_ID,CONVDESC con1,CONVDESC  con2 from CONVEYAN_MASTER a where  amend_id= (select max(amend_id) from CONVEYAN_MASTER b where branch_code=? and b.CONVDESC= a.CONVDESC) and branch_code=? and status='Y' order by CONVDESC";
			 qry ="select MODE_TRANSPORT_ID,SNO con1,CONVDESC  con2 from CONVEYAN_MASTER a where  amend_id= (select max(amend_id) from CONVEYAN_MASTER b where branch_code=? and b.CONVDESC= a.CONVDESC) and branch_code=? and status IN ('Y','R') order by CONVDESC";
			 seaCovers = runner.multipleSelection(qry, args);
			
		}
		catch(Exception e)
		{
			System.out.println("getSeaCoverValues Value Is -->>"+e);
			e.printStackTrace();
		}
		return seaCovers;
	}
	
	public String[][] getMOCValueBasis(String mocNo, String branch)
	{
		String valueBasis[][] = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		String stId = "";
		try{
			args = new String[2];
			args[0] = mocNo;
			args[1] = mocNo;
			sql = "select sale_term_id from open_cover_sale_term_master ocstm,open_cover_position_master ocpm where ocpm.proposal_no=ocstm.proposal_no and to_date(ocstm.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocpm.open_cover_no=? and ocstm.PROPOSAL_NO||ocstm.amend_id in(select ocstm.PROPOSAL_NO||max(ocstm.amend_id) from open_cover_sale_term_master ocstm,open_cover_position_master ocpm where ocstm.PROPOSAL_NO=ocpm.proposal_no and ocpm.open_cover_no=? and to_date(ocstm.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by ocstm.PROPOSAL_NO) ";
			stId = runner.singleSelection(sql,args);
			if(stId.length() > 0)
			{
				args = new String[2];
				args[0] = "Y";
				args[1] = branch;
				sql = "select sale_term_id,sale_term_name from sale_term_master where status=? and branch_code=? and sale_term_id in("+stId+")";
				valueBasis = runner.multipleSelection(sql, args);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return valueBasis;
	}
	//For Tax Calculation Process start from here
	public void calculateTax(final String userType,final String loginId,final String appNo,final String branchCode,final String minPre){
		try{
			String sql;
			String taxDetails[][] = new String[0][0];
			System.out.println("User type"+userType);
			String userType2 = runner.singleSelection("select usertype from login_master where " +
			"login_id=(select login_id from session_master where session_id=(" +
			" select session_id from tracking_master where login_id='"+loginId+"' " +
			"and serial_no =(select max(serial_no) from tracking_master" +
			" where login_id='"+loginId+"')))");
			if(userType.equalsIgnoreCase("Admin")){
				String cols[] = {branchCode,appNo,appNo,branchCode,appNo,appNo}; 
				sql="select (case when POLICY_FEE_STATUS='Y' then nvl(POLICY_FEE,'0') else 0 end),(case when GOVT_TAX_STATUS='Y' then nvl(GOVT_TAX,'0') else 0 end)," +
					"(case when EMERGENCY_FUND_STATUS='Y' then nvl(EMERGENCY_FUND,'0') else 0 end),POLICY_FEE_STATUS from BROKER_MASTER where BRANCH_CODE=? " +
					"and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where application_no=?)) and to_date(effective_date,'dd-mm-YYYY') <=(select entry_date from position_master where application_no=?) " +
					"and amend_id||AGENCY_CODE in(select max(amend_id)||agency_code from BROKER_MASTER where BRANCH_CODE=? " +
					"and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where application_no=?)) and to_date(effective_date,'dd-mm-YYYY') <=(select entry_date from position_master where application_no=?) group by AGENCY_CODE)";
					taxDetails = runner.multipleSelection(sql,cols);
			}
			
			else if (userType2.equalsIgnoreCase("RSAIssuer")){
				System.out.println("RSAIssuer login");
				String cols[] = {branchCode,loginId,branchCode,loginId}; 
				sql="select (case when (POLICY_FEE_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH'" +
				" OR upper(TAX_APPLICABLE) = 'USER')) then nvl(POLICY_FEE,'0') else 0 end),(case when " +
				"(GOVT_TAX_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH' OR upper(TAX_APPLICABLE) = 'USER')) then nvl(GOVT_TAX,'0') else 0 end)," +
				"(case when (EMERGENCY_FUND_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH'  OR upper(TAX_APPLICABLE) = 'USER')) then nvl(EMERGENCY_FUND,'0') else 0 end),POLICY_FEE_STATUS from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') " +
				"and amend_id||AGENCY_CODE in(select max(amend_id)||agency_code from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by AGENCY_CODE)";
				taxDetails = runner.multipleSelection(sql,cols);
				
			/*	System.out.println("RSAIssuer login");
				String cols[] = {branchCode,loginId,branchCode,loginId}; 
				sql="select (case when POLICY_FEE_STATUS='Y' then nvl(POLICY_FEE,'0') else 0 end),(case when GOVT_TAX_STATUS='Y' then nvl(GOVT_TAX,'0') else 0 end)," +
				"(case when EMERGENCY_FUND_STATUS='Y' then nvl(EMERGENCY_FUND,'0') else 0 end) from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') " +
				"and amend_id||AGENCY_CODE in(select max(amend_id)||agency_code from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by AGENCY_CODE)";
				taxDetails = runner.multipleSelection(sql,cols);
			*/}
			else{
				String cols[] = {branchCode,loginId,branchCode,loginId}; 
				sql="select (case when (POLICY_FEE_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH'" +
						" OR upper(TAX_APPLICABLE) = 'BROKER')) then nvl(POLICY_FEE,'0') else 0 end),(case when " +
						"(GOVT_TAX_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH' OR upper(TAX_APPLICABLE) = 'BROKER')) then nvl(GOVT_TAX,'0') else 0 end)," +
				"(case when (EMERGENCY_FUND_STATUS='Y' AND (upper(TAX_APPLICABLE) = 'BOTH'  OR upper(TAX_APPLICABLE) = 'BROKER')) then nvl(EMERGENCY_FUND,'0') else 0 end),POLICY_FEE_STATUS from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') " +
				"and amend_id||AGENCY_CODE in(select max(amend_id)||agency_code from BROKER_MASTER where BRANCH_CODE=? " +
				"and AGENCY_CODE=(select oa_code from login_master where login_id=?) and to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by AGENCY_CODE)";
				taxDetails = runner.multipleSelection(sql,cols);
			}
			if(taxDetails.length>0 && "Y".equalsIgnoreCase(taxDetails[0][3])){
				String cols[] = {getPolicyFee(branchCode,appNo),taxDetails[0][1],taxDetails[0][2],appNo};
				String updateSql = "update MARINE_data set POLICY_FEE=?,GOVT_TAX_Percent=?,EMERGENCY_FUND_Percent=?" +
						",GOVT_TAX=(case when (PREMIUM+TOTAL_WAR_CHARGES)<"+minPre+" then round(("+minPre+"*"+taxDetails[0][1]+"/100),3) else round((PREMIUM+TOTAL_WAR_CHARGES)*"+taxDetails[0][1]+"/100,3) end)," +
						"EMERGENCY_FUND=(case when (PREMIUM+TOTAL_WAR_CHARGES)<"+minPre+" then round(("+minPre+"*"+taxDetails[0][2]+"/100),3) else round((PREMIUM+TOTAL_WAR_CHARGES)*"+taxDetails[0][2]+"/100,3) end)" +
								"  where application_no=?";
				runner.multipleUpdation(updateSql, cols);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String[][] getTaxDetails(final String appNo){
		String result[][] = new String[0][0];
		try{
			String sql; sql = "select case when (nvl(POLICY_FEE,'0')+nvl(GOVT_TAX_Percent,'0')+nvl(EMERGENCY_FUND_Percent,'0'))>0 then 'Yes' else 'No' end,nvl(POLICY_FEE,'0'),nvl(GOVT_TAX_Percent,'0'),nvl(EMERGENCY_FUND_Percent,'0')," +
					"nvl(GOVT_TAX,'0'),nvl(EMERGENCY_FUND,'0')," +
					"(nvl(EMERGENCY_FUND,'0')+nvl(GOVT_TAX,'0')),(nvl(POLICY_FEE,'0')+nvl(GOVT_TAX,'0')+nvl(EMERGENCY_FUND,'0'))" +
					" from MARINE_data where application_no=?";
			String cols[] = {appNo};
			result = runner.multipleSelection(sql, cols);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//For Tax Calculation Process end from here
	
	// Vessel Popup Screen
	
	public String[][] getVesselMaster(String loginBra)
	{
//		String sql = "select vessel_id,vessel_name,RSACODE from vessel_master where status='Y' order by vessel_name";
		String sql = "select vessel_id,vessel_name,RSACODE from vessel_master where status in ('Y','R') order by vessel_name";
		String args[] = new String[1];
		args[0] = loginBra;
		
		String vesselInfo[][] = new String[0][0];
		try
		{
			vesselInfo = runner.multipleSelection(sql);
		}
		catch(Exception e){
           e.printStackTrace();
		}
		return vesselInfo;
	}
	
	public HashMap getYetToStratMOC(String mocNos)
	{
		String sql = "select ocpms.open_cover_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpms.inception_date,'dd-MM-YYYY') > to_date(sysdate,'dd-MM-YYYY') and ocpms.status='P' and (ocpms.admin_status is null or ocpms.admin_status='Y') and ocpms.open_cover_no in("+mocNos+") group by ocpms.open_cover_no";
		Map yetStartMOC = new HashMap();
		String mocList[][] = new String[0][0];
		try
		{
			mocList = runner.multipleSelection(sql);
			for(int i=0;i<mocList.length;i++){
				mocList[i][0] = mocList[i][0] == null ? "" : mocList[i][0];
				yetStartMOC.put("moc"+mocList[i][0], mocList[i][0]);
			}
		}
		catch(Exception e){
           e.printStackTrace();
		}
		return (HashMap)yetStartMOC;
	}
	
	public String getStringFromArray(final String[][] arrayString,final int index) {
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append(arrayString[i][index]);
			makeStr.append(",");
		}
		return makeStr.toString();
	}
	
	public String getQuotedStringFromArray(final String[][] arrayString,final int index) {
		final StringBuffer makeStr = new StringBuffer();
		for (int i = 0; i < arrayString.length; i++) {
			makeStr.append("'"+arrayString[i][index]);
			makeStr.append("',");
		}
		return makeStr.toString();
	}
	
	public String removeLastChar(final String content, final char delimeter){
		final StringBuffer contents = new StringBuffer();
		if (content.length() > 0){
			contents.append(content.substring(0, content.lastIndexOf(delimeter)));
		}
		return contents.toString();
	}
	
	/*public String oneOffWarRate(final String countryId,final String belongingCId)
	{
		String sql = "";
		String warRate = "";
		String args[] = new String[4];
		try{
			args[0] = countryId;
			args[1] = countryId;
			args[2] = belongingCId;
			args[3] = belongingCId;
			sql = "select nvl(war_rate,0) from country_master_detail where country_id=? and country_id||amend_id in (select country_id||max(amend_id) from country_master_detail where country_id =? and BELONGING_COUNTRY_ID=? group by country_id) and BELONGING_COUNTRY_ID=?";
			warRate = runner.singleSelection(sql, args);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("One Off Policy War Rate...."+warRate);
		return warRate;
	}*/
	public String[][] getConstantInfo(String categoryId, String branchCode)
	{
		System.out.println("getConstantInfo - Enter");
		String constantList[][]=runner.multipleSelection("SELECT CATEGORY_DETAIL_ID, DETAIL_NAME, REMARKS FROM CONSTANT_DETAIL WHERE BRANCH_CODE=? AND CATEGORY_ID=? AND STATUS='Y'", new String[]{branchCode, categoryId});
		System.out.println("getConstantInfo - Exit || Result: "+constantList.length);
		return constantList;
	}
	public String getSaleTermName(String saleTermId, String branchCode)
	{
		String result="";
		try
		{
			result = runner.singleSelection("SELECT SALE_TERM_NAME FROM SALE_TERM_MASTER WHERE SALE_TERM_ID=? AND BRANCH_CODE=?",new String[]{saleTermId, branchCode});
		}
		catch(Exception e)
		{
			System.out.println("Exception in getSaleTermId .."+e);
		}
		return result;	
	}
	public String getCityName(String cityId)
	{
		String result="";
		try
		{
			if(StringUtil.isNumberValue(cityId))
				result = runner.singleSelection("SELECT CITY_NAME FROM CITY_MASTER WHERE CITY_ID=? ",new String[]{cityId});
			else result=cityId;
		}
		catch(Exception e)
		{
			System.out.println("Exception in getSaleTermId .."+e);
		}
		return result;	
	}
	public String getMonth(String month)
	{
		if(month!=null)
		{
			String[][] monthArr={{"01","Jan"},{"02","Feb"},{"03","Mar"},{"04","Apr"},{"05","May"},{"06","Jun"},{"07","Jul"},{"08","Aug"},{"09","Sep"},{"10","Oct"},{"11","Nov"},{"12","Dec"}};
			for(int k=0;k<monthArr.length;k++)
			{
				if(month.equalsIgnoreCase(monthArr[k][1]))
				{
					month	=	monthArr[k][0];
				}
			}
		}
		return month;
	}
	public String oneOffWarRate(final String modeOfTransport,final String branchCode)
	{
		logger.info("oneOffWarRate - Enter || modeOfTransport: "+modeOfTransport+" branchCode: "+branchCode);
		String sql = "";
		String warRate = "";
		try{
			//sql = "SELECT PERCENT FROM CONSTANT_DETAIL WHERE CATEGORY_DETAIL_ID=? AND BRANCH_CODE=? AND CATEGORY_ID=130 AND STATUS='Y'";
			//Added by Hari
			sql = "SELECT WAR_RATE FROM WAR_RATE_MASTER WHERE MODE_TRANSPORT_ID ='"+modeOfTransport+"' AND BRANCH_CODE='"+branchCode+"' AND AMEND_ID=(SELECT max(amend_id) from WAR_RATE_MASTER where MODE_TRANSPORT_ID='"+modeOfTransport+"'  AND BRANCH_CODE='"+branchCode+"') AND STATUS='Y' ";
			warRate = runner.singleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("oneOffWarRate - Exit || War Rate: "+warRate);
		return warRate;
	}
	public String getPolicyFee(final String branchCode, final String appNo)
	{
		logger.info("getPolicyFee - Enter || branchCode: "+branchCode+" applicationNo: "+appNo);
		String sql = "";
		String result = "0";
		try{
			//sql = "SELECT NVL((CASE WHEN A.PRODUCT_ID='3' THEN B.PERCENT ELSE (CASE WHEN (A.PREMIUM * 0.5/100)<=B.PERCENT THEN B.PERCENT ELSE (A.PREMIUM * 0.5/100) END) END),'0') POLICY_FEE FROM MARINE_DATA A, CONSTANT_DETAIL B WHERE B.CATEGORY_DETAIL_ID=1 AND B.BRANCH_CODE=? AND B.CATEGORY_ID=131 AND B.STATUS='Y' AND A.APPLICATION_NO=?";
			String sel = "SELECT PRODUCT_ID FROM MARINE_DATA where application_no = '"+appNo+"'";
			String product = runner.singleSelection(sel);	
			if(product.equalsIgnoreCase("3")){
				sql = "SELECT NVL((CASE WHEN ((A.PREMIUM+A.TOTAL_WAR_CHARGES) * 0.5/100)<=B.PERCENT THEN B.PERCENT ELSE round((A.PREMIUM+A.TOTAL_WAR_CHARGES) * 0.5/100) END),'0') POLICY_FEE FROM MARINE_DATA A, CONSTANT_DETAIL B WHERE B.CATEGORY_DETAIL_ID=1 AND B.BRANCH_CODE=? AND B.CATEGORY_ID=131 AND B.STATUS='Y' AND A.APPLICATION_NO=?";
			}else{
//				sql = "SELECT NVL((CASE WHEN ((A.PREMIUM+A.TOTAL_WAR_CHARGES) * B.ISSUANCE_FEE/100)<=B.MIN_PREMIUM_ISSUANCE_FEE THEN B.MIN_PREMIUM_ISSUANCE_FEE ELSE round((A.PREMIUM+A.TOTAL_WAR_CHARGES) * B.ISSUANCE_FEE/100) END),'0') POLICY_FEE FROM MARINE_DATA A, OPEN_COVER_MASTER B WHERE B.MISSIPPI_OPENCOVER_NO=A.OPEN_COVER_NO AND B.BRANCH_CODE=? AND B.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=A.OPEN_COVER_NO) AND A.APPLICATION_NO=?";
				sql = "SELECT CASE WHEN C.PROPOSAL_NO IS NOT NULL THEN 0 ELSE NVL ( (CASE WHEN ( (A.PREMIUM + A.TOTAL_WAR_CHARGES) * B.ISSUANCE_FEE / 100) <= B.MIN_PREMIUM_ISSUANCE_FEE THEN B.MIN_PREMIUM_ISSUANCE_FEE ELSE ROUND( (A.PREMIUM + A.TOTAL_WAR_CHARGES) * B.ISSUANCE_FEE / 100) END), '0' ) END POLICY_FEE FROM MARINE_DATA A JOIN OPEN_COVER_MASTER B ON B.MISSIPPI_OPENCOVER_NO = A.OPEN_COVER_NO LEFT OUTER JOIN (SELECT DISTINCT PROPOSAL_NO,DEPOSIT_PREMIUM_YN FROM OPEN_COVER_MASTER WHERE DEPOSIT_PREMIUM_YN = 'Y') C ON B.PROPOSAL_NO = C.PROPOSAL_NO WHERE B.BRANCH_CODE = ? AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO = A.OPEN_COVER_NO) AND A.APPLICATION_NO = ?";
			}
			System.out.println("POLICY Fee::"+sql);
			result = runner.singleSelection(sql, new String[]{branchCode, appNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getPolicyFee - Exit || Result: "+result);
		return result;
	}
	public String[][] getPolicyFeeConst(final String branchCode,final String productId,final String openCoverNo)
	{
		logger.info("getPolicyFeeConst - Enter || branchCode: "+branchCode);
		String sql = "";
		String result[][] =new String [1][2];
		try{
			//sql = "SELECT NVL((CASE WHEN A.PRODUCT_ID='3' THEN B.PERCENT ELSE (CASE WHEN (A.PREMIUM * 0.5/100)<=B.PERCENT THEN B.PERCENT ELSE (A.PREMIUM * 0.5/100) END) END),'0') POLICY_FEE FROM MARINE_DATA A, CONSTANT_DETAIL B WHERE B.CATEGORY_DETAIL_ID=1 AND B.BRANCH_CODE=? AND B.CATEGORY_ID=131 AND B.STATUS='Y' AND A.APPLICATION_NO=?";
			if(productId.equalsIgnoreCase("11")){
				sql = "SELECT MIN_PREMIUM_ISSUANCE_FEE,ISSUANCE_FEE FROM OPEN_COVER_MASTER  WHERE MISSIPPI_OPENCOVER_NO='"+openCoverNo+"' AND BRANCH_CODE=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO='"+openCoverNo+"') ";
			System.out.println(sql);
			}
			else{
				sql = "SELECT PERCENT ,'0.5' FROM CONSTANT_DETAIL  WHERE CATEGORY_DETAIL_ID=1 AND BRANCH_CODE=? AND CATEGORY_ID=131 AND STATUS='Y' ";
			}
			result = runner.multipleSelection(sql, new String[]{branchCode});
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getPolicyFeeConst - Exit || Result: "+result);
		return result;
	}
	public String updatePolicyFee(final String applicationNo, final String policyFee)
	{
		logger.info("updatePolicyFee - Enter || applicationNo: "+applicationNo+" policyFee: "+policyFee);
		String sql = "";
		String result = "0";
		try{
			sql = "UPDATE MARINE_DATA SET POLICY_FEE=? WHERE APPLICATION_NO=?";
			result = runner.multipleUpdation(sql, new String[]{policyFee, applicationNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("updatePolicyFee - Exit || Result: "+result);
		return result;
	}
	public String updatePremium(final String applicationNo, final String premium)
	{
		logger.info("updatePremium - Enter || applicationNo: "+applicationNo+" policyFee: "+premium);
		String sql = "";
		String result = "0";
		try{
			sql = "UPDATE MARINE_DATA SET PREMIUM=? WHERE APPLICATION_NO=?";
			result = runner.multipleUpdation(sql, new String[]{premium, applicationNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("updatePremium - Exit || Result: "+result);
		return result;
	}

	public String[][] getExecutives(String brokerBra) {	
		   String list[][]	=	new String[0][0];
		   //StringBuffer busi = new StringBuffer();
		   String args[] = new String[0];
		   
		   try
		   { 
				sqlQuery_="select distinct b.AC_EXECUTIVE_ID, b.AC_EXECUTIVE_NAME from LOGIN_EXECUTIVE_DETAILS b where b.status='Y'  and amend_id =(select max(a.amend_id)  from LOGIN_EXECUTIVE_DETAILS a where  a.AC_EXECUTIVE_ID=b.AC_EXECUTIVE_ID)order by b.AC_EXECUTIVE_NAME ";
				list	=runner.multipleSelection(sqlQuery_);
		   }
		catch(Exception e){
			System.out.println("2746 Exception BusinessList : "+e);
			e.printStackTrace();
		}
		return list;
	   }
	public String getCurrencyRoundFactor(final String currencyId)
	{
		logger.info("getCurrencyRoundFactor - Enter || currencyId: "+currencyId);
		String sql = "";
		String result = "0";
		try{
			sql = "SELECT NVL(RFACTOR,'0') FROM CURRENCY_MASTER WHERE CURRENCY_ID=?";
			result = runner.singleSelection(sql, new String[]{currencyId});
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getCurrencyRoundFactor - Exit || Result: "+result);
		return result;
	}
	public String getRoundedValue(String value, String currencyId, boolean GHQ)
	{
		logger.info("getRoundedValueGHQ - Enter || currencyId: "+currencyId+" Value: "+value);
		String sql = "";
		String result = "0";
		try{
			if(value!=null && value.trim().length()>0 && Double.parseDouble(value)!=0){
				if(GHQ){
					value = Double.toString((Double.parseDouble(value)- Double.parseDouble("0.1")));
					logger.info("GHQ Value=>"+value);
				}
				sql = "SELECT ROUND("+value+",NVL(RFACTOR,'0')) FROM CURRENCY_MASTER WHERE CURRENCY_ID=?";
				result = runner.singleSelection(sql, new String[]{currencyId});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getRoundedValueGHQ - Exit || Result: "+result);
		return result;
	}
	public String getRoundedValue(String value, String currencyId, String brokerBra)
	{
		logger.info("getRoundedValue - Enter || currencyId: "+currencyId+" Value: "+value);
		String sql = "";
		String result = "0";
		try{
			if(brokerBra==null || "".equals(brokerBra))
				brokerBra="01";
			if(value!=null && value.trim().length()>0 && Double.parseDouble(value)!=0){
				sql = "SELECT ROUND("+value+",NVL(DECIMAL_PLACES,'0')) FROM BRANCH_MASTER WHERE BRANCH_CODE=? and amend_id=(select max(amend_id) FROM BRANCH_MASTER WHERE BRANCH_CODE=?)";
				result = runner.singleSelection(sql, new String[]{brokerBra, brokerBra});
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getRoundedValue - Exit || Result: "+result);
		return result;
	}
	public String getBrokerType(String loginId, String branchCode, String issuer)
	{
		String result="";
		logger.info("getDirectBroker - Enter || brachCode: "+branchCode+" loginId: "+loginId);
		result=runner.singleSelection("SELECT COUNT(1) FROM CONSTANT_DETAIL WHERE BRANCH_CODE='"+branchCode+"' AND CATEGORY_ID='129' AND REMARKS='"+loginId+"'");
		if(issuer!=null && !"0".equalsIgnoreCase(result))
			result="direct";
		else result=runner.singleSelection("SELECT AC_EXECUTIVE_ID FROM BROKER_COMPANY_MASTER WHERE AGENCY_CODE=(SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID='"+loginId+"')");
		logger.info("getDirectBroker - Enter || Result: "+result);
		return 	result;
	}
	
	public String getGHQExchangeRate(String loginId){
		String exchangeRate = runner.singleSelection("SELECT PERCENT FROM CONSTANT_DETAIL WHERE CATEGORY_ID='134' AND STATUS='Y' AND BRANCH_CODE=? AND DETAIL_NAME=(SELECT DISTINCT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?)", new String[]{loginBra, loginId});
		System.out.println("exchangeRate"+exchangeRate);
		return exchangeRate;
	}
	public boolean getLandReferralForNonGCC(String countryId, String branchCode)
    {
        String count = runner.singleSelection("SELECT COUNT(*) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=8 AND BRANCH_CODE=? AND CATEGORY_DETAIL_ID=? AND STATUS='Y'", new String[] {branchCode, countryId});
        if("0".equals(count))
        {
            return true;
        } else
        {
            return false;
        }
    }
	public String getOpenCoverToleranceInfo(String mocNo, String branch)
	{
		String result = "";
		String sql = "";
		String args[] = new String[0];
		try{
			args = new String[2];
			args[0] = mocNo;
			args[1] = mocNo;
			sql = "SELECT TOLERANCE_ID FROM OPEN_COVER_SALE_TERM_MASTER OCSTM,OPEN_COVER_POSITION_MASTER OCPM WHERE OCPM.PROPOSAL_NO=OCSTM.PROPOSAL_NO AND TO_DATE(OCSTM.EFFECTIVE_DATE,'dd-mm-YYYY') <=TO_DATE(SYSDATE,'dd-mm-YYYY') AND OCPM.OPEN_COVER_NO=? AND OCSTM.PROPOSAL_NO||OCSTM.AMEND_ID IN(SELECT OCSTM.PROPOSAL_NO||MAX(OCSTM.AMEND_ID) FROM OPEN_COVER_SALE_TERM_MASTER OCSTM,OPEN_COVER_POSITION_MASTER OCPM WHERE OCSTM.PROPOSAL_NO=OCPM.PROPOSAL_NO AND OCPM.OPEN_COVER_NO=? AND TO_DATE(OCSTM.EFFECTIVE_DATE,'dd-mm-YYYY') <=TO_DATE(SYSDATE,'dd-mm-YYYY') GROUP BY OCSTM.PROPOSAL_NO)";
			result = runner.singleSelection(sql,args);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getOpenCoverCoverTypeInfo(String openCoverNo, String branchCode)
	{
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try{
			sql = "SELECT A.COVER_ID,A.COVER_TYPE_ID FROM OPEN_COVER_SUB_DETAIL A,OPEN_COVER_POSITION_MASTER B WHERE B.OPEN_COVER_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_SUB_DETAIL WHERE PROPOSAL_NO=A.PROPOSAL_NO) AND A.STATUS='Y' AND A.PROPOSAL_NO=B.PROPOSAL_NO ORDER BY A.COVER_ID";
			result = runner.multipleSelection(sql,new String[]{openCoverNo});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String getOpenCoverEndDate(final String openCoverNo){
		logger.info("getOpenCoverDate - Enter || openCoverNo: "+openCoverNo);
			String result = runner.singleSelection("SELECT TO_CHAR(EXPIRY_DATE,'DD/MM/YYYY') FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=?",new String[]{openCoverNo});
		logger.info("getOpenCoverDate - Exit || Result: "+result);
		return result;
	}
	public String[][] getVesselList(String vesselName)
	{
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try{
			sql = "SELECT VESSEL_ID,VESSEL_NAME,VESSEL_TYPE,VESSEL_CLASS,MANUFACTURE_YEAR FROM VESSEL_MASTER WHERE STATUS IN ('Y','R') AND UPPER(VESSEL_NAME) LIKE UPPER(?) ORDER BY VESSEL_NAME";
			result = runner.multipleSelection(sql,new String[]{"%"+vesselName+"%"});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getBrokersInfo(String loginId)
	{
		String result[][] = new String[0][0];
		String sql = "";
		try{
			sql = "SELECT A.LOGIN_ID, B.COMPANY_NAME FROM LOGIN_MASTER A, BROKER_COMPANY_MASTER B  WHERE A.OA_CODE = B.AGENCY_CODE and A.LOGIN_ID IN("+loginId+")";
			result = runner.multipleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}// Class
