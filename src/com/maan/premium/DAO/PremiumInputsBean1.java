/**
 * @Program Author name 	---Rajesh R
 * @Creation Date & Time 	---28-04-2007 4 : 11 PM
 * @Company Name			---MaanSarovar Softwate P Limited
 * @Module					---Premium Inputs Bean
 */

package com.maan.premium.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import javax.servlet.http.HttpSession;

import proj.sql.QueryBuilder;

import com.maan.common.error.ErrorInfo;
import com.maan.common.util.OracleDateConversion;
import com.maan.common.util.StringUtil;
import com.maan.DBCon.DBConnection;
import com.maan.services.util.runner;

public class PremiumInputsBean1 extends ErrorInfo
{
	private ResultSet 				rs_			=null;
	private PreparedStatement 		pst_		=null;
	private Statement 				st_			=null;
	private String 					sqlQuery_	="";
	private String[][] commoditys=new String[0][0];
	HttpSession session;
	private String policyDate="";
	private String quoteId="";
	private String fromCountryId="";
	private String toCountryId="";
	private String fromCityId="";
	private String toCityId="";
	private HashMap commodityDetails;
	//Variables for Transit Details
	private String modeOfTransport="";
	private String warehouse="";
	private String warehouse1="";
	private String transitFrom="";
	private String finalDestination="";
	private String totalSumInsured="";
	//Variables for Commodity Details
	private String commodity="";
	private String noOfItems="";
	private String currency="";
	private String currencyValue="";
	private String policyDay="";
	private String policyMonth="";
	private String policyYear="";
	private String saleTerm="";
	private String tolerance					= "";
	//Variables for Commodity Details
	private String cover="";
	private String wsrcc="";
	private String executive="";
	private String applicationNo="";
	private String loginCode="";
	private String sessionId="";
	private String productId="";
	private String companyId="";
	private int application_no=0;
	private String stageId="";
	private String currencyId="";
	private String currencyName="";
	private String exchangeId="";
	private String referral="";
	private String totalSaleCharges="";
	private String totalTolCharges="";
	private String totalWarPremium="";
	private String seaOption="";
	private String cid						= "";
	private String loginBra						= "";
	
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
	public void setSeaOption(String seaOption)
	{
		this.seaOption=seaOption;
	}
	public String getSeaOption()
	{
		return seaOption;
	}
	public String getReferral() {
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

	public String getFinalDestination() {
		return finalDestination;
	}

	public void setFinalDestination(String finalDestination) {
		this.finalDestination = finalDestination;
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
	public String getTolerance() {
		return this.tolerance;
	}
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}
	
	public void setSaleTerm(String saleTerm) {
		this.saleTerm = saleTerm;
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
		public String getWarehouse1() {
		return warehouse1;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
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
		StringBuffer stb = new StringBuffer();
		String args[] = new String[2];
		try
		{
			sqlQuery_="select AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME from login_executive_details where status='Y' and (oa_code=(select agency_code from login_master where login_id=?) or oa_code=(select oa_code from login_master where login_id=?))";
			args[0] = loginId;
			args[1] = loginId;
			String channeldetails[][] = runner.multipleSelection(sqlQuery_,args);
			String channelIdentifier = "";
			stb.append("<select name='executive' class='scrolLet' style='width:133px'>>");
			stb.append("<option value ='0'>Select</option>");
			for(int i = 0; i < channeldetails.length;i++)
			{
				if (channeldetails[i][0].equalsIgnoreCase(executive))
				{
					channelIdentifier = "selected";
				}
				else
				{
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
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return stb;
	}

	public void insertOrUpdateMarineInfo(String[][] commoditys)
	{
		int rows	=	0;
		int totalCount=0;
		int quote_Id=0;
		double royalPremium=0.0;
		
		String args[] = new String[0];
		String res = "";
		String exChangeId="";
		String currencyName="";
		String currencyId="";
		String coverId="";
		String exeName="";
		String commodityId="";
		String fragile="";
		String description="";
		String sumInsured="";

		HashMap currencyCoverInformation=new HashMap();
		HashMap comDetails = getCommodityDetails();

		currencyCoverInformation=getCurrencyCoverInfo(currency,modeOfTransport,cover,executive,cid,loginBra);

		Calendar cal = new GregorianCalendar();

	    // Get the components of the time
	    int hour12 = cal.get(Calendar.HOUR);            // 0..11
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min = cal.get(Calendar.MINUTE);             // 0..59
	    int sec = cal.get(Calendar.SECOND);             // 0..59
	    int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	    int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM

	    policyDate	=	policyDay+"-"+policyMonth+"-"+policyYear+" "+hour24+":"+min+":"+sec;
		System.out.println("the SYSTEM DATE IS "+policyDate);
	
		exChangeId=(String)currencyCoverInformation.get("exChangeId");
		setExchangeId(exChangeId);
		currencyName=(String)currencyCoverInformation.get("currencyName");
		setCurrencyName(currencyName);
		currencyId=(String)currencyCoverInformation.get("currencyId");
		setCurrencyId(currencyId);
		coverId=(String)currencyCoverInformation.get("coverId");
		exeName=(String)currencyCoverInformation.get("executiveName");
	
		args = new String[1];
		args[0] = applicationNo;
		sqlQuery_ ="select count(*) from marine_data where application_No=?";

		try
		{
			res = runner.singleSelection(sqlQuery_,args);
			if(res.length()>0 && !res.equalsIgnoreCase("null"))
			{
				rows = Integer.parseInt(res);
				
			}
			else
			{
				rows = 0;
				
			}
		}
		catch(Exception e)
		{
			System.out.println("Premium InputsBean1 insertOrUpdateCompanyInfo() for Count :"+e.toString());
			e.printStackTrace();
		}

		try
		{
			royalPremium = getPremium(applicationNo,modeOfTransport,coverId,saleTerm,commoditys);
			System.out.println("RoyalTest final PRemium..."+royalPremium);
		}
		catch(Exception e)
		{
			System.out.println("RoyalTest getting finall premium...");e.printStackTrace();
		}

		if(rows<=0)
		{
			try
			{
				sqlQuery_ ="insert into marine_data (APPLICATION_NO,MODE_OF_TRANSPORT,WAREHOUSE_TO_WAREHOUSE,TRANSIT_FROM,FINAL_DESTINATION, NO_OF_ITEMS,TOTAL_SUM_INSURED,CURRENCY_ID,EXCHANGE_ID,CURRENCY_NAME,EXCHANGE_RATE,SALE_TERM_ID,COVER_ID,EXTRA_COVER_ID,AC_EXECUTIVE_ID,AC_EXECUTIVE_NAME,AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,EQUIVALENT_USD,COMPANY_ID,premium,transit_from_city_id,transit_from_country_id,final_destination_city_id,final_destination_country_id,referral,TOTAL_SALE_TERM_CHARGES,TOTAL_WAR_CHARGES,sea_options,ware_to_ware_final_dest),TOLERANCE_ID,TOTAL_TOLERANCE_CHARGES values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss'),SYSDATE,SYSDATE," +
										"SYSDATE,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				System.out.println("Before Conversion QuoteDate :"+policyDate);
				System.out.println("the Application No--"+applicationNo);
				System.out.println("the policyDay No--"+policyDay);
				System.out.println("the policyMonth No--"+policyMonth);
				System.out.println("the policyYear No--"+policyYear);
				System.out.println("getMaximumQuoteId :"+applicationNo);
				System.out.println("modeOfTransport :"+modeOfTransport);
				System.out.println("warehouse :"+warehouse);
				System.out.println("transitFrom :"+transitFrom);
				System.out.println("finalDestination :"+finalDestination);
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
				System.out.println("premium--- Before Insert");
				System.out.println("premium---AFTER Insert");
				System.out.println("fromCityId :"+fromCityId);
				System.out.println("fromCountryId :"+fromCountryId);
				System.out.println("toCityId :"+toCityId);
				System.out.println("toCountryId :"+toCountryId);
				System.out.println("getReferral() :"+getReferral());
				System.out.println("getTotalSaleCharges() :"+getTotalSaleCharges());
				System.out.println("getTotalWarPremium() :"+getTotalWarPremium());
				System.out.println("seaOption   "+seaOption);

				String[] qryvalues = new String[33];
				qryvalues[0] = ""+Integer.parseInt(applicationNo);
				qryvalues[1] = modeOfTransport;
				qryvalues[2] = warehouse;
				qryvalues[3] = transitFrom;
				qryvalues[4] = finalDestination;
				qryvalues[5] = ""+Integer.parseInt(noOfItems);
				qryvalues[6] = ""+Double.parseDouble(totalSumInsured);
				qryvalues[7] = ""+Integer.parseInt(currencyId);
				qryvalues[8] = ""+Integer.parseInt(exChangeId);
				qryvalues[9] = currencyName;
				qryvalues[10] = ""+Double.parseDouble(currencyValue);
				qryvalues[11] = ""+Integer.parseInt(saleTerm);
				qryvalues[12] = ""+Integer.parseInt(coverId);
				qryvalues[13] = ""+Integer.parseInt(getWsrcc());
				qryvalues[14] = ""+Integer.parseInt(executive);
				qryvalues[15] = exeName;
				qryvalues[16] = "0";
				qryvalues[17] = "Normal";
				qryvalues[18] = "Y";
				qryvalues[19] = ""+(Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue));
				qryvalues[20] = ""+Integer.parseInt(companyId);
				qryvalues[21] = Double.toString(Math.round(royalPremium));
				if(fromCityId.equalsIgnoreCase(""))
				{
					fromCityId="0";
				}
				qryvalues[22] = ""+Integer.parseInt(fromCityId);
				qryvalues[23] = ""+Integer.parseInt(fromCountryId);
				if(toCityId.equalsIgnoreCase(""))
				{
					toCityId="0";
				}
				qryvalues[24] = ""+Integer.parseInt(toCityId);
				qryvalues[25] = ""+Integer.parseInt(toCountryId);
				qryvalues[26] = getReferral();
				qryvalues[27] = ""+Double.parseDouble(getTotalSaleCharges());
				qryvalues[28] = Double.toString(Math.round(Double.parseDouble(getTotalWarPremium())));;
				qryvalues[29] = seaOption;
				qryvalues[30] = warehouse1;
				qryvalues[31] = tolerance;
				qryvalues[32] = ""+getTotalTolCharges();

				runner.multipleInsertion(sqlQuery_,qryvalues);

			}
			catch(Exception e)
			{
				System.out.println("Exception in insertOrUpdateCompanyInfo()  Insertion :"+e.toString());
				e.printStackTrace();
			}
			
			//This is for Tracking Master Update END TIME

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
			try
			{
				sqlQuery_= "update marine_data set APPLICATION_NO=?,MODE_OF_TRANSPORT=?,WAREHOUSE_TO_WAREHOUSE=?,TRANSIT_FROM=?,FINAL_DESTINATION=?,NO_OF_ITEMS=?,TOTAL_SUM_INSURED=?,CURRENCY_ID=?,EXCHANGE_ID=?,CURRENCY_NAME=?,EXCHANGE_RATE=?,SALE_TERM_ID=?,COVER_ID=?,EXTRA_COVER_ID=?,AC_EXECUTIVE_ID=?,AC_EXECUTIVE_NAME=?,AMEND_ID=?,INCEPTION_DATE=TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss'),EXPIRY_DATE=SYSDATE,EFFECTIVE_DATE=SYSDATE,ENTRY_DATE=SYSDATE,STATUS=?,EQUIVALENT_USD=?,COMPANY_ID=?,premium=?,transit_from_city_id=?,transit_from_country_id=?,final_destination_city_id=?,final_destination_country_id=?,referral=?,TOTAL_SALE_TERM_CHARGES=?,TOTAL_WAR_CHARGES=?,sea_options=?,ware_to_ware_final_dest=?   ,TOLERANCE_ID=?,TOTAL_TOLERANCE_CHARGES=? where APPLICATION_NO=? and status=?";

				String convertDate="TO_DATE('"+policyDate+"','dd-mm-yyyy hh24:mi:ss')";
				policyDate	=	policyYear+"-"+policyMonth+"-"+policyDay;
								
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
				System.out.println("the NO of coverId UPDATE is"+coverId);
				System.out.println("the NO of getWsrcc() UPDATE is"+getWsrcc());
				System.out.println("the NO of executive UPDATE is"+executive);
				System.out.println("the NO of exeName UPDATE is"+exeName);
				System.out.println("premium--- ");
				System.out.println("premium---AFTER UPDATE");
				System.out.println("fromCityId UPDATE:"+fromCityId);
				System.out.println("fromCountryId UPDATE:"+fromCountryId);
				System.out.println("toCityId UPDATE:"+toCityId);
				System.out.println("toCountryId UPDATE:"+toCountryId);
				System.out.println("getReferral() UPDATE:"+getReferral());
				System.out.println("getTotalSaleCharges() UPDATE:"+getTotalSaleCharges());
				System.out.println("getTotalWarPremium() UPDATE:"+getTotalWarPremium());
				System.out.println(" update warehouse1 -----"+warehouse1);
				System.out.println("getTotalTolCharges   "+getTotalTolCharges());

				String qryvalues[] = new String[34];
				qryvalues[0] = ""+applicationNo;
				qryvalues[1] = modeOfTransport;
				qryvalues[2] = warehouse;
				qryvalues[3] = transitFrom;
				qryvalues[4] = finalDestination;
				qryvalues[5] = ""+Integer.parseInt(noOfItems);
				qryvalues[6] = ""+Double.parseDouble(totalSumInsured);
				qryvalues[7] = ""+Integer.parseInt(currencyId);
				qryvalues[8] = ""+Integer.parseInt(exChangeId);
				qryvalues[9] = currencyName;
				qryvalues[10] = ""+Double.parseDouble(currencyValue);
				qryvalues[11] = ""+Integer.parseInt(saleTerm);
				qryvalues[12] = ""+Integer.parseInt(coverId);
				qryvalues[13] = ""+Integer.parseInt(getWsrcc());
				qryvalues[14] = ""+Integer.parseInt(executive);
				qryvalues[15] = exeName;
				qryvalues[16] = ""+Integer.parseInt("0");
				qryvalues[17] = "Y";
				qryvalues[18] = ""+(Double.parseDouble(totalSumInsured)*Double.parseDouble(currencyValue));
				qryvalues[19] = ""+Integer.parseInt(companyId);
				qryvalues[20] = Double.toString(Math.round(royalPremium));

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
				qryvalues[26] =	getTotalSaleCharges();//sale Term Charges
				qryvalues[27] =	Double.toString(Math.round(Double.parseDouble(getTotalWarPremium())));//Total War charges
				qryvalues[28] = seaOption;
				qryvalues[29] = warehouse1;
				qryvalues[30] = tolerance;
				qryvalues[31] = getTotalTolCharges();
				qryvalues[32] = ""+Double.parseDouble(applicationNo);
				qryvalues[33] = "Y";
				
				runner.multipleUpdation(sqlQuery_,qryvalues);

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
		System.out.println(".....RotalTest admin side insertOrUpdateMarineInfo end........");
	}

	public int getMaximumQuoteId()
	{
		int quote_id=1;
		String res = "";
		sqlQuery_ ="select max(quote_no) from marine_data" ;
		try
		{
			res = runner.singleSelection(sqlQuery_);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
				quote_id = Integer.parseInt(res);
		}
		catch(Exception e)
		{
			System.out.println("the EXCEPTION IN Maximum Quote Id Generation"+e.toString());
			e.printStackTrace();
		}
		
		if(quote_id==0)
		{
			quote_id=1;
		}
		return quote_id;
	}

	

	public  String getCountryCityInfo(String countryId,String cityName)
	{
		String countryCityInfo="";
		runner run = new runner();
		String args[] = new String[1];
		String result[][] = new String[0][0];
		try
		{
			args[0] = cityName.toLowerCase();
			sqlQuery_="select ct.country_id,ct.city_id,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=? and ct.country_id=cm.country_id";

			print("countryCityOTHERSInfo",sqlQuery_,"QUERY");

			result = runner.multipleSelection(sqlQuery_,args);
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
		
		try
		{
			args[0] = cityName.toLowerCase();
			sqlQuery_="select ct.country_id,ct.city_id as cityId,ct.city_name,cm.country_name from city_master ct,country_master cm where lower(ct.city_name)=? and cm.country_id='"+countryId+"' and ct.country_id=cm.country_id";
			result = runner.multipleSelection(sqlQuery_,args);
			  if(result.length > 0)
			  {
				countryCityInfo = result[0][1];
			  }
		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in countryCityInfo--"+exception.toString());
			exception.printStackTrace();
		}
		return countryCityInfo;
	}


	private StringBuffer coverName=new StringBuffer();
	private StringBuffer categoryMasterName=new StringBuffer();
	private StringBuffer coverId=new StringBuffer();
	private StringBuffer modeMasterId=new StringBuffer();
	private StringBuffer categoryMainMasterId=new StringBuffer();


	public StringBuffer getModeMasterId()
	{
		return modeMasterId;
	}

	public void setModeMasterId(StringBuffer modeMasterId)
	{
		this.modeMasterId = modeMasterId;
	}

	public void setCoverId(StringBuffer coverId)
	{
		this.coverId = coverId;
	}
	public StringBuffer getCoverId()
	{
		return coverId;
	}

	public void setCoverName(StringBuffer coverName)
	{
		this.coverName = coverName;
	}

	public StringBuffer getCoverName()
	{
		return coverName;
	}

	public int getMaximumApplicationNo()
	{
		int application_id=1;
		String res = "";
		sqlQuery_ ="select max(application_no) from marine_data" ;
		try
		{
			res = runner.singleSelection(sqlQuery_);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
				application_id = Integer.parseInt(res);
		}
		catch(Exception e)
		{
			System.out.println("the EXCEPTION IN getMaximumApplicationNo"+e.toString());
			e.printStackTrace();
		}
		if(application_id==0)
		{
			application_id=1;
		}
		return application_id;
	}


	public double getPremium(String applicationNo,String modeOfTransport,String coverId,String saleTerm,String [][] commoditys)
	{
		double finalPremium=0.0;
		HashMap commRates=new HashMap();
		commRates=getPremiumDetails(applicationNo,modeOfTransport,coverId,saleTerm,commoditys);
		finalPremium=Double.parseDouble((String)commRates.get("totalPremium"));
		return finalPremium;
	}

	public double getSaleTermValue(String saleTerm)
	{
		String saleTermLocal="1";
		String args[] = new String[2];
		String result = "";
		runner run = new runner();
		try
		{
			args[0] = saleTerm;
			args[1] = loginBra;
			sqlQuery_=	"select sale_term_value from sale_term_master where sale_term_id=? and branch_code=? and status='Y'";
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

	public String getGeneralValue(String selectName,String tableName,String valueColumn,String value)
	{
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		String valueFetched="";
		try
		{
			sqlQuery_="select "+selectName+"  from "+tableName+" where "+valueColumn+"='"+value+"'";
			print("getGeneralValue",sqlQuery_,"QUERY");
			st_=con.createStatement();
			rs_=st_.executeQuery(sqlQuery_);
			while(rs_.next())
			{
				valueFetched=rs_.getString(selectName);
			}
	    }
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getGeneralValue--"+exception.toString());
			exception.printStackTrace();
		}
		finally
		{
			sqlQuery_="";
			try
			{
				if(rs_!=null)
					rs_.close();
				if(st_!=null)
					st_.close();
				if(con!=null)
					con.close();
			}
			catch(Exception exception)
			{
				System.out.println("The Exception occured While Closing getGeneralValue--"+	exception.toString());
				exception.printStackTrace();
			}
		}
		return valueFetched;
	}

	public double getToleranceValue(String tolerance)
	{
		String saleTermLocal="0";
		String args[] = new String[2];
		String result = "";
		runner run = new runner();
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

	public  HashMap getPremiumDetails(String applicationNo,String modeOfTransport,String coverId,String saleTermValue,String[][] commoditys)
	{
		HashMap commodityRates=new HashMap();
		String[][] commodityRatesArray = new String[0][0];

		print("@@@@@@","PREMIUM COMPUTATION STARTS","@@@@@@@@@@@");
		print("applicationNo",applicationNo,"applicationNo");
		print("modeOfTransport",modeOfTransport,"modeOfTransport");
		print("coverId",coverId,"coverId");
		print("saleTerm",saleTermValue,"saleTerm");

		String ratingFactorSubId="";
		String ratingFactorFirstId="";//mode of Transport
		String ratingFactorSecondId=""; //Commodity_Name
		String secondDataName="";//CommodityID
		String firstDataName="";//CoverId
		String axis="";//axis
		String rate="";

		double salePercentage=0.0;
		double tolPercentage          = 0.0;
		double conSI=0.0;
		double pre=0.0;

		double totalConSI=0.0;
		double totalPre=0.0;

		String SI="";
		String SIAED="";
		String saleValue="";
		String warPremium="";
		String warRate="";
		String seaValue="0";
		String warehouseValue="0";
		String referralStatus="Normal";
		String commExcessPremium="";

		double dou_SI=0.0;
		double dou_SIAED=0.0;
		double dou_saleValue=0.0;
		double dou_totSaleValue=0.0;
		double dou_warPremium=0.0;
		double dou_totWarPremium=0.0;
		double dou_warRate=0.0; // 0.05/100
		double saleFactor=0.0;
		double dou_totTolValue        = 0.0;
		double tolFactor              = 0.0;
	    double dou_tolValue           = 0.0;
		System.out.println("  WSRCC OPTION    "+wsrcc);
		java.text.NumberFormat fmt1=new java.text.DecimalFormat("0.00000") ;
		java.text.NumberFormat fmtPremium=new java.text.DecimalFormat("0.0000") ;


		salePercentage=getSaleTermValue(saleTermValue);
		tolPercentage   = getToleranceValue(tolerance);
		if(modeOfTransport.equalsIgnoreCase("1"))
		{
			//getTwoDimDisDisValue("1","2",coverId,"1","CommodityId","X");
			ratingFactorSubId="4";
			ratingFactorFirstId="2";
			ratingFactorSecondId="1";
			firstDataName=coverId;
			axis="X";
		}
		else if(modeOfTransport.equalsIgnoreCase("2"))
		{
			//getTwoDimDisDisValue("2","3",coverId,"1","CommodityId","X");
			ratingFactorSubId="2";
			ratingFactorFirstId="3";
			ratingFactorSecondId="1";
			firstDataName=coverId;
			axis="X";
		}
		else if(modeOfTransport.equalsIgnoreCase("3"))
		{
			//getTwoDimDisDisValue("3","4",coverId,"1","CommodityId","X");
			ratingFactorSubId="3";
			ratingFactorFirstId="4";
			ratingFactorSecondId="1";
			firstDataName=coverId;
			axis="X";
		}
		else if(modeOfTransport.equalsIgnoreCase("4"))
		{
			//getTwoDimDisDisValue("2","3",coverId,"1","CommodityId","X");
			ratingFactorSubId="2";
			ratingFactorFirstId="3";
			ratingFactorSecondId="1";
			firstDataName=coverId;
			axis="X";
		}	
		try
		{

//			sqlQuery_="select mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile from marine_result_details mrd where mrd.application_no='"+applicationNo+"' and  Status='Y'";
			sqlQuery_="select mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile from marine_result_details mrd where mrd.application_no='"+applicationNo+"' and  Status='Y' order by mrd.commodity_id";

			print("getCommodityRates",sqlQuery_,"QUERY");
			commodityRatesArray= com.maan.services.util.runner.multipleSelection(sqlQuery_);
			
				if(commodityRatesArray.length>0)
				{

					for(int i=0;i<commodityRatesArray.length;i++)
					{
						secondDataName=commodityRatesArray[i][1];
						print("secondDataName",secondDataName,"VALUE");
						try
						{
							System.out.println("********************GETTING RATE FROM RATING  ***********");
							System.out.println("commodity id    "+commodityRatesArray[i][1]);
							System.out.println("WSRCC Rate    "+commoditys[i][2]);
							System.out.println("warehouse Rate    "+commoditys[i][3]);
							System.out.println("SEA RATE    "+commoditys[i][4]);
							System.out.println("commExcessPremium    "+commoditys[i][5]);
							System.out.println("firstDataName   "+firstDataName);
						if(commoditys!=null && commoditys.length>0)
						{
								System.out.println("commoditys[i][0])))  "+commoditys[i][1]);
								System.out.println("Commodity length  "+commoditys.length);
							//rate=commoditys[i][1];
							
							rate=""+Double.parseDouble(commoditys[i][1]);
							
							//warehouseValue=""+Double.parseDouble(commoditys[i][3]);
							//seaValue=""+Double.parseDouble(commoditys[i][4]);
						
						System.out.println("wsrcc   "+wsrcc);
						if(Integer.parseInt(wsrcc)==0)
							dou_warRate=0.0;
						else
							dou_warRate=Double.parseDouble(commoditys[i][2]);
				
						System.out.println("dou_warRate  "+dou_warRate);
						System.out.println("warehouse  "+warehouse);
						System.out.println("warehouse1  "+warehouse1);
						System.out.println("modeOfTransport  "+modeOfTransport);
					
						if("YES".equalsIgnoreCase(warehouse) && "YES".equalsIgnoreCase(warehouse1))
						{
							if(Integer.parseInt(modeOfTransport)==1 || Integer.parseInt(modeOfTransport)==2)
								warehouseValue=""+Double.parseDouble(commoditys[i][3]);
						}
						System.out.println("warehouseValue  "+warehouseValue);
						System.out.println("seaOption  "+seaOption);
						seaValue=""+Double.parseDouble(commoditys[i][4]);
						System.out.println("seaValue  "+seaValue);
						commExcessPremium = commoditys[i][5];
						}
				
						if(commoditys.length==0)
						{
							String rateCoverName =totalValue("select description from cover_master where  cover_id='"+firstDataName+"' and status='Y' ");
							System.out.println("  cover_nameEEE "+rateCoverName);
	

							rate = totalValue("select nvl("+rateCoverName+",'0') from commoditymaster where commodity_id='"+commodityRatesArray[i][1]+"' and BRANCH_CODE='"+loginBra+"' and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and BRANCH_CODE='"+loginBra+"' group by commodity_id)");

							System.out.println(" rate Value   "+rate);
							rate = ""+Double.parseDouble(rate);
						}
						System.out.println("SEAOPTION   "+seaOption);
						System.out.println("wsrcc     "+wsrcc);
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
						dou_SIAED=dou_SI*Double.parseDouble(currencyValue);
						System.out.println("dou_SIAED   "+dou_SIAED);
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

						dou_warPremium=(conSI*dou_warRate)/100;
						dou_warPremium=Double.parseDouble(fmtPremium.format(dou_warPremium));
						dou_totWarPremium=dou_totWarPremium+dou_warPremium;
						print("secondDataName",""+conSI,"CONVERTED SI AED");
						totalConSI=	totalConSI+conSI;
						print("getWsrcc()",getWsrcc(),"WAR STATUS");
							//	if("1".equalsIgnoreCase(getWsrcc()))
								//{

								print("rate FINAL STAGE-@@@-",rate,"--@@@@-rate FINAL STAGE");

								//pre=(conSI*(Double.parseDouble(rate)));
								//COMMENTED AND MODIFIED BY KARTHY ON 16TH JUNE 2007
							//For Freight Forwarder Rajesh
							//For Freight Forwarder Rajesh
							/*	String freightPreStr="0";
								double freightPre=0.0;
								double freightRate = Double.parseDouble(rate);
								freightPreStr = getLoadingofPremiumFreightBroker(applicationNo);
								if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
								{
									System.out.println("ROyal Test original rate in admin.."+freightRate);
									freightPre = Double.parseDouble(freightPreStr);
									freightRate = freightRate+(freightRate*(freightPre/100));
									System.out.println("ROyal Test after adding rate.."+freightRate);
									rate = ""+freightRate;
								}*/
								//
		try
		{
			pre=((conSI*(Double.parseDouble(rate)+Double.parseDouble(warehouseValue)+Double.parseDouble(seaValue))/100));
			pre=Double.parseDouble(fmtPremium.format(pre));
		}
		catch(Exception e)
		{
			System.out.println("RoyalTest Exception in premiumInputs bean regaring rate parsing in admin side...."+e.toString());
			e.printStackTrace();
		}
		
		print("secondDataName",""+pre,"PREMIUM");
		totalPre=	totalPre+pre;
		commodityRates.put(commodityRatesArray[i][1],rate);
		commodityRates.put("commodityId"+commodityRatesArray[i][1],commodityRatesArray[i][1]);
		commodityRates.put("sumInsured"+commodityRatesArray[i][1],commodityRatesArray[i][2]);
		commodityRates.put("convertedSumInsured"+commodityRatesArray[i][1],""+conSI);
		commodityRates.put("premium"+commodityRatesArray[i][1],""+pre);

		if("0".equalsIgnoreCase(rate) || "0.0".equalsIgnoreCase(rate))
		{
			referralStatus="Refferal";
			setReferral(referralStatus);
			pre=0.0;
			dou_saleValue=0.0;
			dou_tolValue=0.0;
			dou_warPremium=0.0;
			dou_warRate=0.0;
			dou_SIAED=0.0;

			print("REFERRAL CHECK--",""+rate,commodityRatesArray[i][1]);

		}else
		{
			referralStatus="Normal";
			setReferral(referralStatus);

		}
		//This is for Updating
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
			System.out.println("warehouseValue -------  "+warehouseValue);
			System.out.println("seaValue -------  "+seaValue);

			System.out.println("********END GETTING RATE FROM RATING  ****************");

			updatePremiumRates(applicationNo,rate,""+pre,commodityRatesArray[i][1],""+dou_saleValue,		""+dou_warPremium,referralStatus,""+dou_warRate,""+dou_SIAED,warehouseValue,seaValue,commExcessPremium,""+dou_tolValue);
		}
		catch(Exception ec)
		{
			System.out.println("the EXCEPTION ISSSS  "+ec.toString());
		}

		}
	}
			//For Freight Forwarder Rajesh
			/*	String freightPreStr="0";
				double freightPre=0.0;
				freightPreStr = getLoadingofPremiumFreightBroker(applicationNo);
				if(freightPreStr!=null&&!freightPreStr.equals("null")&&freightPreStr.length()>0)
				{
					freightPre = Double.parseDouble(freightPreStr);
					totalPre = totalPre+(totalPre*(freightPre/100));
				}*/
				//
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
		print("@@@@@@","PREMIUM COMPUTATION ENDS","@@@@@@@@@@@");
		return commodityRates;


	}

public String getTwoDimDisDisValue(String rating_factor_sub_id,String rating_factor_id_first,String firstDataNameInput,String rating_factor_id_two,String secondDataNameInput,String secondAxis)

	{
			String[][] firstDetails=new String[0][0];
			String[][] secondDetails=new String[0][0];
			String value="";

		if(secondAxis.equalsIgnoreCase("Y"))
		{

				System.out.println("teh Y AXIS is");
			firstDetails=queryForGettingTwoDimensionDiscreteGroupDiscreteNew(rating_factor_id_first,firstDataNameInput,rating_factor_sub_id);

		secondDetails=queryForGettingTwoDimensionDiscreteGroupDiscreteNew(rating_factor_id_two, secondDataNameInput,rating_factor_sub_id,secondAxis);

		value=queryForGettingTwoDimDisDisDetailsNew(firstDetails[0][3],secondDetails[0][4],firstDetails[0][2])[0][3];

		}else
		{
		firstDetails=queryForGettingTwoDimensionDiscreteGroupDiscreteNew(rating_factor_id_first,firstDataNameInput,rating_factor_sub_id,secondAxis);

		secondDetails=queryForGettingTwoDimensionDiscreteGroupDiscreteNew(rating_factor_id_two,secondDataNameInput,rating_factor_sub_id);

		value=queryForGettingTwoDimDisDisDetailsNew(secondDetails[0][3],firstDetails[0][4],firstDetails[0][2])[0][3];

		}



		return value;


	}

public String[][] queryForGettingTwoDimensionDiscreteGroupDiscreteNew(String rating_factor_id,String rating_factor_one,String rating_factor_sub_id)

{

	//String queryForGettingTwoDimensionRangeDiscrete="select x_data_name,y_data_name,ran_dis_id,x_id,y_id from two_dim_range_discrete where rating_factor_id='"+rating_factor_id+"' and  rating_factor_sub_id='"+rating_factor_sub_id+"' and lower(x_data_from)='"+rating_factor_one.toLowerCase()+"' and lower(x_data_to)='"++"'";

	String queryForGettingTwoDimensionDiscreteGroupDiscrete="select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id='"+rating_factor_id+"' and  rating_factor_sub_id='"+rating_factor_sub_id+"' and lower(x_data_name)='"+rating_factor_one.toLowerCase()+"'";

   //setError("queryForGettingTwoDimensionDiscreteGroupDiscrete...."+queryForGettingTwoDimensionDiscreteGroupDiscrete);
	System.out.println("3248 @@@@@@@@@@ x_data_name queryForGettingTwoDimensionRangeGroupDiscreteis NEW"+queryForGettingTwoDimensionDiscreteGroupDiscrete);
	String[][] claimResult = new String[0][0];
		try
		{
			claimResult= com.maan.services.util.runner.multipleSelection(queryForGettingTwoDimensionDiscreteGroupDiscrete);
		}catch(Exception e){}
				return (claimResult);

}

public synchronized  String[][] queryForGettingTwoDimensionDiscreteGroupDiscreteNew(String rating_factor_id,String rating_factor_one,String rating_factor_sub_id,String axis)
{

		String queryForGettingTwoDimensionDiscrete="select x_data_name,y_data_name,dis_dis_id,x_id,y_id from two_dim_discrete_discrete where rating_factor_id='"+rating_factor_id+"'  and rating_factor_sub_id='"+rating_factor_sub_id+"' and lower(y_data_name)='"+rating_factor_one.toLowerCase()+"'";
		System.out.println("985 @@@@@@ y_data_name queryForGettingTwoDimensionDiscrete NEW"+queryForGettingTwoDimensionDiscrete);
		//setError("9899 error is..."+queryForGettingTwoDimensionDiscrete);
		String[][] claimResult = new String[0][0];
		try
		{
			claimResult= com.maan.services.util.runner.multipleSelection(queryForGettingTwoDimensionDiscrete);


		}catch(Exception e){}
				return (claimResult);
}
public synchronized  String[][] queryForGettingTwoDimDisDisDetailsNew(String x_id,String y_id,String disdisid)
{
	//modified By Brahmaiah on 24-01-07
	//	String queryForGettingTwoDimDisDisDetails="select x_id,y_id,dis_dis_id,data_value from two_dim_dis_dis_details where dis_dis_id='"+disdisid+"' and x_id ='"+x_id+"' and y_id ='"+y_id+"' and effective_date <='"+OracleDateConversion.ConvertDate(insuranceDate)+"'";

	//String queryForGettingTwoDimDisDisDetails="select x_id,y_id,dis_dis_id,data_value from two_dim_dis_dis_details where dis_dis_id='"+disdisid+"' and x_id ='"+x_id+"' and y_id ='"+y_id+"' and effective_date <='"+OracleDateConversion.ConvertDate(getInsuranceDate())+"' and amend_id in (select max(amend_id) from two_dim_dis_dis_details where dis_dis_id='"+disdisid+"' and x_id ='"+x_id+"' and y_id ='"+y_id+"' and effective_date <='"+OracleDateConversion.ConvertDate(getInsuranceDate())+"')";

	String queryForGettingTwoDimDisDisDetails="select x_id,y_id,dis_dis_id,data_value from" +
			" two_dim_dis_dis_details where dis_dis_id='"+disdisid+"' " +
					"and x_id ='"+x_id+"' and y_id ='"+y_id+"' and " +
							"effective_date " +
							"<='"+OracleDateConversion.ConvertDate("20-05-2010")+"' " +
									"and amend_id in (select max(amend_id) " +
									"from two_dim_dis_dis_details where " +
									"dis_dis_id='"+disdisid+"' and x_id ='"+x_id+"' " +
											"and y_id ='"+y_id+"'" +
					" and effective_date <='"+OracleDateConversion.ConvertDate("20-05-2010")+"'" +
					"and effective_date in (select max(effective_date) from two_dim_dis_dis_details where" +
					" dis_dis_id='"+disdisid+"' and x_id ='"+x_id+"' and y_id ='"+y_id+"' and " +
					"effective_date <='"+OracleDateConversion.ConvertDate("20-05-2010")+"'))";

	System.out.println("queryForGettingTwoDimDisDisDetails NEW------------->"+queryForGettingTwoDimDisDisDetails);
		//setError("736 query is...."+queryForGettingTwoDimDisDisDetails);
		String[][] claimResult=new String[0][0];
		try
		{
				claimResult= com.maan.services.util.runner.multipleSelection(queryForGettingTwoDimDisDisDetails);
		}catch(Exception e){}
		return (claimResult);
}

public String[][] getOtherRatingNames(String ratingGroupName,String xName,String yName) throws Exception
{
String queryFlexaFactors = "";
String[][] getFlexaFactors = new String[0][0];

String getOtherName="";
String[][] otherFactors=new String[0][0];

try{

	if(ratingGroupName.equalsIgnoreCase(xName))
	{
	getOtherName="select dg.data_from,dg.data_to,dg.data_name,pm.rating_factor_id,gm.group_id,pm.table_name,gm.table_column_name from group_master_new gm,product_field_master_new pm,data_group_details dg where trim(lower(gm.group_name)) like trim(lower('"+yName+"')) and gm.group_id=pm.group_id and pm.status='Y' and pm.status=gm.status and gm.status=dg.status and dg.rating_factor_id=pm.rating_factor_id ";
	}
	else
	{
	getOtherName="select dg.data_from,dg.data_to,dg.data_name,pm.rating_factor_id,gm.group_id,pm.table_name,gm.table_column_name from group_master_new gm,product_field_master_new pm,data_group_details dg where trim(lower(gm.group_name)) like trim(lower('"+xName+"')) and gm.group_id=pm.group_id and pm.status='Y' and pm.status=gm.status and gm.status=dg.status and dg.rating_factor_id=pm.rating_factor_id ";
	}

	System.out.println("get RATING getOtherRatingNames OTHER NAMES "+getOtherName);

	otherFactors = com.maan.services.util.runner.multipleSelection(getOtherName);


}catch(Exception e){

	System.out.println("The Error here RATING getOtherRatingNames "+e.toString());
	//setError(e.toString());

 }
 return otherFactors;
}

public void updatePremiumRates(String appNo,String rate,String pre,String comId,String saleCharges,String warCharges,String referStatus,String wsrccRate,String sumLocal,String warehouseValue,String seaValue,String commExcessPremium,String tolValue)
	{
		runner run = new runner();
  	    String[] qryvalues = new String[18];
		
		try
		{
			sqlQuery_ ="update marine_result_details set rate=?,premium=?,currency_id=?,exchange_id=?,currency_name=?,exchange_rate=?,sale_term_charges=?,war_charges=?,referral=?,warrate=?,SUMINSUREDLOCAL=?,rag=(select rag from commoditymaster where commodity_id='"+comId+"' and branch_code='"+loginBra+"' and amend_id||'-'||commodity_id  in(select max(amend_id)||'-'||commodity_id from commoditymaster where  to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and  status in ('Y','R') and branch_code='"+loginBra+"' group by commodity_id)),sea_option_lcl=?,warehouse_warehouse=?,commodity_Excess_Premium=?,TOLERANCE_CHARGES=?  where  APPLICATION_NO=? and commodity_id=? and status=?";

			System.out.println("updatePremiumRates   "+sqlQuery_);
			System.out.println("UPDATE  appNo    "+appNo);
			System.out.println("seaValue  "+seaValue);
			System.out.println("warehouseValue  "+warehouseValue);
			System.out.println("UDATE pre   "+pre);
				
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

			qryvalues[13] = commExcessPremium;

			qryvalues[14] = tolValue;
			qryvalues[15] = appNo;
			qryvalues[16] = comId;
			qryvalues[17] = "Y";

			runner.multipleInsertion(sqlQuery_, qryvalues);
		}
		catch(Exception upExcep)
		{
			System.out.println("Exception in updatePremiumRates InforRRR :"+upExcep.toString());
			upExcep.toString();
		}
	}

	 public String getQuoteBasedApplicationNo(String quoteId)
   {
	    String quoteBasedAppNo = "";
	    String getApplication = "";
		String args[] = new String[2];
		runner run = new runner();
	    try
		{
			args[0] = ""+Integer.parseInt(quoteId);
			args[1] = "Y";
			sqlQuery_ ="select pm.application_no as appNo from position_master pm where pm.quote_no=? and pm.status=?";
		    getApplication = runner.singleSelection(sqlQuery_,args);
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


	public void print(String methodName,String information,String type){
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
	public String getFromCityId() {
		return fromCityId;
	}
	public void setFromCityId(String fromCityId) {
		this.fromCityId = fromCityId;
	}
	public String getToCityId() {
		return toCityId;
	}
	public void setToCityId(String toCityId) {
		this.toCityId = toCityId;
	}

public void updateTrackingDetails()
	{
		runner run = new runner();
		String[] qryvalues = new String[5];
		try
		{
			System.out.println("Inside updateTrackingDetails PREMIUM SUMMARY of TRACKING_MASTER");
			System.out.println("stageId :"+stageId);
			System.out.println("Application No :"+applicationNo);
			System.out.println("sessionId No :"+sessionId);
			System.out.println("loginCode No :"+loginCode);

			String trackQry =	"update TRACKING_MASTER set STAGE_ID=?,END_TIME=SYSDATE where APPLICATION_NO=? and STATUS=? and session_id=? and login_id=? ";
			
			qryvalues[0] = stageId;
			qryvalues[1] = applicationNo;
			qryvalues[2] = "Y";
			qryvalues[3] = sessionId;
			qryvalues[4] = loginCode;
			synchronized (this) 
			{
				runner.multipleInsertion(trackQry, qryvalues);
			}
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
		runner run = new runner();
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
		runner run = new runner();
		try
		{
			serialNoMax = getMaximumSerialNO();
			System.out.println("Inside Insertion of TRACKING_MASTER");	
			System.out.println("SESSION_ID :"+sessionId);
			System.out.println("loginCode :"+loginCode);
			System.out.println("STATUS IS :"+insertStatus);
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
			synchronized (this) 
			{
				runner.multipleInsertion(insTrack, qryvalues);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public int getMaximumSerialNO()
	{
		int s_id=1;
		String res = "";
		sqlQuery_ ="select max(serial_no) from tracking_master" ;
		try
		{
			res = runner.singleSelection(sqlQuery_);
			if(res.length() > 0 && !res.equalsIgnoreCase("null"))
				s_id = Integer.parseInt(res);
		}
		catch(Exception e)
		{
			System.out.println("the EXCEPTION IN getMaximumSNO"+e.toString());
			e.printStackTrace();
		}
		if(s_id==0)
		{
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
		runner run = new runner();
		try
		{
			args[0] = premium;
			args[1] = total_war_premium;
			args[2] = toal_sale_term;
			args[3] = appNo;
			sql="update marine_data set premium=?,total_war_charges=?,total_sale_term+charges=?, where application_no=?";
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
		Statement stmt=null;
		ResultSet rs=null;
		Connection con=null;
		con=DBConnection.getInstance().getDBConnection();
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next())
				returnVal=rs.getString(1);
		}
		catch(Exception e)
		{
			System.out.println("Error in totalvalue "+e.toString());

		}
		finally
		{
			try
			{
				if(rs_!=null)
					rs_.close();
				if(st_!=null)
					st_.close();
				if(con!=null)
					con.close();
			}
			catch(Exception exception)
			{
				System.out.println("The Exception totalValue PremiumInputsBean1.java"+	exception.toString());
				exception.printStackTrace();
			}
		}
		System.out.println("  Return value   "+returnVal);
		return returnVal;
	}

	public String[][] getLimitedData(String loggedPersonId)
	{
		String[][] data	=	new String[0][0];
		String args[] = new String[1];
		String sql="";
		args[0] = loggedPersonId;
		runner run = new runner();
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

	public String[][] getMinPrem(String loggedPersonId,String pid)
	{
		String[][] data =	new String[0][0];
		String sql = "";
		String args[] = new String[2];
		sql = "select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS  where agency_code=(select oa_code from login_master where login_id=?) and product_id=?";
		runner run = new runner();
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

	
	public void setExtraPremium(String appno,String extra_amount)
	{
		String args[] = new String[2];
		runner run = new runner();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getLogins(String applicationNo)
	{
	   String args[] = new String[1];
	   String sql = "";
	   String loginId = "";
	   runner run = new runner();
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

	//For Freight FOrwarder Rajesh
	public String getLoadingofPremiumFreightBroker(String appno)
	{
		String args[] = new String[1];
        String query ="";
		args[0] = appno;
		query = "select LOADING_OF_PREMIUM from LOGIN_USER_DETAILS where LOGIN_ID in(select f.login_id from FREIGHT_POSITION_MASTER f,POSITION_MASTER p where f.APPLICATION_ID=? and f.quote_no=p.quote_no and p.FREIGHT_STATUS='F') and product_id='3' and ((PROVISION_FOR_PREMIUM='D' and FREIGHT_RATE_OPTION='Y') or FREIGHT_AUOTO_OPTION='Y')";
           runner run = new runner();
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

	public String[][] getMinPremiumFreightBroker(String appno)
	{
		String sql = "";
        String query = "";
		String args[] = new String[1];
		runner run = new runner();
        String result[][] = new String[0][0];
        try
        {
			args[0] = appno;
			query ="select INSURANCE_END_LIMIT,MIN_PREMIUM_AMOUNT from LOGIN_USER_DETAILS where LOGIN_ID in(select f.login_id from FREIGHT_POSITION_MASTER f,POSITION_MASTER p where p.APPLICATION_NO=? and f.quote_no=p.quote_no and p.FREIGHT_STATUS='F')";
            result = runner.multipleSelection(query,args);
		    System.out.println("Loading premium percentage ...."+result);
		}
        catch(Exception ex)
		{
             ex.printStackTrace();
        }
        return result;
    }

	public String[][] getOpenCoverMiniPremium(String openCoverNoLocal)
	{
		String[][] getOpenCoverMasterDatas		=	new String[0][0];
		String getOpenCoverMasterDatas_Query	=	"";
		String args[] = new String[2];
		runner run = new runner();
		try
		{
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			getOpenCoverMasterDatas_Query="select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				
			System.out.println("the getOpenCoverMasterDatas_Query getOpenCoverMiniPremium is "+getOpenCoverMasterDatas_Query);
			getOpenCoverMasterDatas = runner.multipleSelection(getOpenCoverMasterDatas_Query,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getOpenCoverMasterDatas;
	}

	public String getOpenCover(String application)
    {
        String result = "";
		String args[] = new String[1];
		runner run = new runner();
		String sql = "";
        try
        {
			args[0] = application;
			sql = "select distinct(open_cover_no) from MARINE_DATA where application_no=?";
            result = runner.singleSelection(sql,args);
        }
        catch(Exception exception)
        {
            System.out.println(" ERROR in getOpenCover() in premiumInputsBean1.java  " + exception.toString());
        }
        return result;
    }

	/*** Import Export Minimum Premium ***/
	
	public String[][] getOpenCoverExportMiniPremium(String openCoverNoLocal)
	{
		String[][] ExportMiniPremium		=	new String[0][0];
		String ExportMiniPremiumQry	=	"";
		String args[] = new String[2];
		runner run = new runner();
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
		runner run = new runner();
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
		runner run = new runner();
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

	//Royal World Work Started
	public HashMap getCurrencyCoverInfo(String exRate,String modeId,String coverName,String executiveId,String cid,String loginBra)
	{
		HashMap currencyCoverInfo=new HashMap();
		String args[] = new String[0];
		String res[][] = new String[0][0];
		//exchange_master ----> 13
		//currency_master ----> 12
		//Total 26
		try
		{
			args = new String[4];
			args[0] = cid;
			args[1] = exRate;
			args[2] = exRate;
			args[3] = cid;
			sqlQuery_=  "select * from exchange_master em,currency_master cm where em.currency_id=cm.currency_id and cm.country_id=em.country_id and cm.country_id=? and em.status=cm.status and em.status='Y' and cm.status='Y' and em.currency_id=? and em.amend_id=(select max(amend_id) from exchange_master where currency_id=? and country_id=? and status='Y')";
			
			res = runner.multipleSelection(sqlQuery_,args);
			for(int i=0;i<res.length;i++)
			{
				currencyCoverInfo.put("exChangeId",	res[i][0]);
				currencyCoverInfo.put("currencyId",	res[i][2]);
				currencyCoverInfo.put("currencyName",res[i][15]);
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
			sqlQuery_="select cm.cover_id,cm.cover_name,cm.mode_transport_id from cover_master cm where cm.mode_transport_id=? and cm.cover_id=? and  cm.status='Y' and cm.BRANCH_CODE=?";
			res = runner.multipleSelection(sqlQuery_,args);
			for(int i=0;i<res.length;i++)
			{
				currencyCoverInfo.put("coverId",res[i][0]);
			}
		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getModeCoverInfo--"+	exception.toString());
			exception.printStackTrace();
		}
		
		try
		{

			sqlQuery_="select * from login_executive_details where ac_executive_id=? and Status='Y'" ;

			args = new String[1];
			args[0] = executiveId;
			res = runner.multipleSelection(sqlQuery_,args);
			for(int i=0;i<res.length;i++)
			{
				currencyCoverInfo.put("executiveName",res[i][1]);
			}

		}
		catch(Exception exception)
		{
			System.out.println("The Exception occured in getModeCoverInfo--"+exception.toString());
			exception.printStackTrace();
		}
		return currencyCoverInfo;
	}
	
	public StringBuffer getCurrencyDetails(String countryId)	
	{
			StringBuffer stb = new StringBuffer();
			String args[] = new String[2];
			try
			{
				sqlQuery_ = "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id)  and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' order by a.DISPLAY_ORDER";
				args[0] = countryId;
				args[1] = countryId;
				String channeldetails[][] = runner.multipleSelection(sqlQuery_);
				String channelIdentifier = "";
				stb.append("<select name='currency' id='currency' class='scrolLet'  style='width:133px' ONCHANGE='return showValue(this.value)'> ");
				stb.append("<option value ='0'>Select</option>");
				for(int i = 0; i < channeldetails.length;i++)
				{
					if (channeldetails[i][4].equalsIgnoreCase(currency))
					{
						channelIdentifier = "selected";
					}
					else
					{
						channelIdentifier = "";
					}
					stb.append("<option value = '"+channeldetails[i][4]+"'"+channelIdentifier+">"+StringUtil.upperFirstChar(channeldetails[i][1])+"</option>");
				 }
				 stb.append("</select>");					
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return stb;
	}
	public StringBuffer getCurrencyDetailsHidden(String countryId)	
	{		
			StringBuffer stb = new StringBuffer();
			String args[] = new String[2];
			try
			{
				sqlQuery_ = "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE+10/24 and COUNTRY_ID=? and status='Y' group by exchange_id,currency_id)  and a.effective_date <=SYSDATE+10/24 and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' order by b.display_order";
				args[0] = countryId;
				args[1] = countryId;
				String channeldetails[][] = runner.multipleSelection(sqlQuery_);
				for(int i = 0; i < channeldetails.length;i++)
				{
					stb.append("<input type='hidden' name='"+channeldetails[i][4]+"' id='"+channeldetails[i][4]+"' value='"+channeldetails[i][0]+"'/>");
				}
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return stb;
	}

   public String[][] getCurrencyRates(String currenId,String countryId)
   {
		Statement stmt_Curr  = null;		
		String[][] currRates = new String[0][0];
		String sql_Curr = ""; 
		String args[] = new String[3];
		try
		{
			args[0] = countryId;
			args[1] = countryId;
			args[2] = currenId;
			sql_Curr = "select a.exchange_rate,b.currency_name,a.amend_id,a.effective_date,a.currency_id from exchange_master a, currency_master b where a.currency_id=b.currency_id and a.COUNTRY_ID=b.COUNTRY_ID and a.COUNTRY_ID=? and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id)  and a.effective_date <=SYSDATE and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y' and b.currency_id=?";
			currRates= runner.multipleSelection(sql_Curr,args);
			if(currRates.length > 0 ){
			}
			else{
				currRates=new String[0][0];
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
			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,oplm.lc_date,cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em  where oplm.status='Y' and lc_id=? and bm.BELONGING_COUNTRY_ID=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and cm.COUNTRY_ID=em.COUNTRY_ID and cm.COUNTRY_ID=? and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where to_char(cms.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') and cms.COUNTRY_ID=? group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where to_date(ocbms.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocbms.BELONGING_COUNTRY_ID=? group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where to_char(ems.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') and em.COUNTRY_ID=? group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";
			args[0] = lcNo;
			args[1] = cid;
			args[2] = bankId;
			args[3] = cid;
			args[4] = openCoverNo;
			args[5] = cid;
			args[6] = cid;
			args[7] = cid;
			getsCountry = runner.multipleSelection(lcQuery,args);
			if(getsCountry.length>0)
			{
			}
			else
			{
				getsCountry = new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("The Get getsLCDetails Value Is --------------->>>>"+e);
			e.printStackTrace();
		}
		return getsCountry;
	}

	public String getStartingPlace(String countryId,String cid)
	{
		String[][] result = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		
		try
		{
			args[0] = countryId;
			args[1] = cid;
			sql = "select starting_place,ending_place from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getEndingPlace...."+ e);
			e.printStackTrace();
		}

		if(result.length>0)
		{
		   return result[0][0];
		}
		else
		{
		   return "";
		}
	}
	
	public String getEndingPlace(String countryId,String cid)
	{
		String[][] result = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = countryId;
			args[1] = cid;
			sql = "select starting_place,ending_place from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getEndingPlace...."+ e);
			e.printStackTrace();
		}

		if(result.length>0)
		{
			return result[0][1];
		}
		else
		{
			return "";
		}
	}

}// Class
