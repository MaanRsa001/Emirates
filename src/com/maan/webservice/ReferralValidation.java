package com.maan.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.CollectionUtils;
import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.maan.quotation.service.QuotationService;
import com.opensymphony.xwork2.ActionContext;

public class ReferralValidation extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(ReferralValidation.class);

	QuotationService service=new QuotationService();
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();
	private static String sql;
	Map<String, Object> session=ActionContext.getContext().getSession();
	//private String belongingBranch=(String)session.get("BelongingBranch");
	
	@SuppressWarnings("unchecked")
	public String getReferralType(WebServicePolicyInfo info, String belongingBranch) {
		String referralType = "", referralId = "", status = "", startPlace = "", endPlace = "";
		boolean countryRef = false, policyDateRef = false, landRef = false, sumInsuredRef = false, crossVoyageRef = false, crossVoyageSIRef = false, warehouseCountryRef = false,
				sameCountryRef = false, lcSIRef = false, vesselRef = false, defaultLandRef = false, commodityRef = false,conveyanceRef = false,shimenttransitRef = false;
		Set<String> referalList=new HashSet<String>();
		List<Map<String, Object>> list = getReferralMasterInfo(belongingBranch);
		if (list != null && !list.isEmpty()) {
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> map = (Map<String, Object>) iterator.next();
				referralId = map.get("REFERAL_ID").toString();
				status = map.get("STATUS").toString();
				if ("Y".equalsIgnoreCase(status)) {
					if ("1".equals(referralId)) {
						countryRef = true;
					} else if ("2".equals(referralId)) {
						policyDateRef = true;
					} else if ("4".equals(referralId)) {
						landRef = true;
					} else if ("11".equals(referralId)) {
						sumInsuredRef = true;
					} else if ("12".equals(referralId)) {
						crossVoyageSIRef = true;
					} else if ("13".equals(referralId)) {
						crossVoyageRef = true;
					} else if ("17".equals(referralId)) {
						warehouseCountryRef = true;
					} else if ("18".equals(referralId)) {
						sameCountryRef = true;
					} else if ("19".equals(referralId)) {
						lcSIRef = true;
					} else if ("20".equals(referralId)) {
						vesselRef = true;
					} else if ("35".equals(referralId)) {
						defaultLandRef = true;
					}else if("40".equals(referralId)) {
						conveyanceRef = true;
					}else if("41".equals(referralId)) {
						shimenttransitRef = true;
					}
					
				}
			}
		}

		startPlace = getCountryStartPlace(info.getOriginatingCountry(), info.getBrokerOriginatingCountryId());
		endPlace = getCountryEndPlace(info.getDestinationCountry(), info.getBrokerOriginatingCountryId());
		if (warehouseCountryRef	
				//&& "3".equals(info.getProductId())
				&& !"Warehouse".equalsIgnoreCase(startPlace)
				&& "YES".equalsIgnoreCase(info
						.getOriginatingWareHouseCoverage())) {
			//referralType += "Country Referral";
			referalList.add("Warehouse Country Referral");
			
		} 
		if (warehouseCountryRef
				//&& "3".equals(info.getProductId())
				&& !"Warehouse".equalsIgnoreCase(endPlace)
				&& "YES".equalsIgnoreCase(info
						.getDestinationWareHouseCoverage())) {
			//referralType += "Country Referral";
			referalList.add("Warehouse Country Referral");
		}
		if (countryRef && isCountryReferral(info.getOriginatingCountry(), info.getDestinationCountry(), info.getBrokerOriginatingCountryId(), info.getProductId())) {
			//referralType += "Country Referral";
			referalList.add("Country Referral");
		} 
		if (sameCountryRef
				&& isSameCountryReferral(info.getOriginatingCountry(), info
						.getDestinationCountry())) {
			//referralType += "Country Referral";
			referalList.add("Country Referral");
		}
		if (!info.isEndt()
				&& policyDateRef
				&& isPolicyDateReferral(info.getBranchCode(),
						info.getLoginId(), info.getProductId(), info
								.getOpenCoverPolicyNo(), info
								.getPolicyStartDate(), info
								.getOpenCoverBackDateDays())) {
			//referralType += "Policy Date Referral";
			referalList.add("Policy Date Referral");
		} 
		if (crossVoyageSIRef
				&& isCrossVoyageSIReferral(info.getOriginatingCountry(), info
						.getDestinationCountry(), info
						.getOpenCoverCrossVoyage(), info
						.getOpenCoverCrossVoyageLimit(), info
						.getOpenCoverPolicyNo(), info
						.getBrokerOriginatingCountryId(), info
						.getTotalSumInsured(), info.getBrokerType(), info
						.getBranchCode())) {
			//referralType += "Cross Voyage Suminsured Referral";
			referalList.add("Cross Voyage Suminsured Referral");
		} 
		if (crossVoyageRef
				&& isCrossVoyageCountryReferral(info.getOriginatingCountry(),
						info.getDestinationCountry(), 
						info.getOpenCoverCrossVoyage(), 
						info.getBrokerOriginatingCountryId(), 
						info.getProductId(),info.getOriginatingWareHouseCoverage(),info.getDestinationWareHouseCoverage())) {
			//referralType += "Cross Voyage Country Referral";
			referalList.add("Cross Voyage Country Referral");
		} 
		if (vesselRef && StringUtils.isNotEmpty(info.getVesselId())
				&& isVesselReferral(info.getVesselId())) {
			//referralType += "Vessel Referral";
			referalList.add("Vessel Referral");
		}
		if (isCoverageReferral(info.getCoverages(), info.getBranchCode())) {
			//referralType += "Coverages Referral";
			referalList.add("Coverages Referral");
			/*
			 * }else if( && "3".equals(info.getModeOfTransport())){
			 * referralType="Land Referral"; else if(landRef &&
			 * "3".equals(info.getModeOfTransport()) &&
			 * isLandReferral(info.getOriginatingCountry(),
			 * info.getDestinationCountry(),
			 * info.getBrokerOriginatingCountryId(), info.getBranchCode(),
			 * info.getProductId())){
			 */
		}
		if ((landRef || defaultLandRef)
				&& isLandReferral(info.getOriginatingCountry(), info
						.getDestinationCountry(), info
						.getBrokerOriginatingCountryId(), info.getBranchCode(),
						info.getProductId(), belongingBranch)
				&& "3".equals(info.getModeOfTransport())) {
			//referralType += "Land Referral";
			referalList.add("Land Referral"); 
		} 
		if (sumInsuredRef
				&& isSumInsuredReferral(info.getLoginId(),
						info.getBranchCode(), info.getProductId(), info
								.getModeOfTransport(), info
								.getPartialShipment(), info
								.getPartialShipmentAmount(), info
								.getExposureCurrencyValue(), info
								.getOpenCoverPolicyNo(), info
								.getTotalSumInsured(), info.getSaleTermValue(),
						info.getToleranceValue(), info.getCurrencyValue(), info
								.getSumInsuredLimit())) {
			//referralType += "Sum Insured Referral";
			referalList.add("Sum Insured Referral"); 
		}
		if (lcSIRef && "11".equals(info.getProductId()) && isLCSumInsuredReferral(info.getBranchCode(), info.getLcNumber(), info.getIssuingBank(), info.getOpenCoverPolicyNo(), info.getSaleTermValue(), info.getToleranceValue(), info.getTotalSumInsured(), info.getCurrencyValue(), info.getQuoteNo(), info.isEndt())) {
			//referralType += "LC Sum Insured Referral"; info.getSeaCoverages()
			referalList.add("LC Sum Insured Referral"); 
		}
		if(conveyanceRef && isconveyanceReferral(info.getSeaCoverages(),info.getBranchCode()))
			referalList.add("Conveyance Referral");
		if (commodityRef)
			referalList.add("Commodity Referral"); 
		if(getExchageRate(info,belongingBranch))
			referalList.add("Exchange Rate Referral");
		
		if(shimenttransitRef && getShipmentTransitReferal(info.getShipmentTransit(),info.getBranchCode()))
			referalList.add("Shipment Transit Referal");
		
			referralType=StringUtils.join(referalList,"~");
		return referralType;		 
	}
	
	private boolean getShipmentTransitReferal(String shipmentTransit, String branchCode) {
		logger.info("getShipmentTransitReferal - Enter || shipmentTransit: "+shipmentTransit+" branchCode: 01");//branchCode
		boolean referral=false;
		String result="";
		try{
			result = policyDAO.getValue("GET_SHIPMENT_TRANSIT",new String[]{shipmentTransit,"01"});
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		referral="R".equalsIgnoreCase(result);
		logger.info("getShipmentTransitReferal - Exit || Result: "+referral);
		return 	referral;	
	}

	private boolean isconveyanceReferral(String seaCoverages,String branchCode) {
		logger.info("isconveyanceReferral - Enter || conveyanceId: "+seaCoverages+" branchCode: 01");//branchCode
		boolean referral=false;
		String result="";
		try{
			result = policyDAO.getValue(DBConstants.REFERRAL_CONVEYANCE,new String[]{seaCoverages,"01"});
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		referral="R".equalsIgnoreCase(result);
		logger.info("isconveyanceReferral - Exit || Result: "+referral);
		return 	referral;	
	}
	public boolean isCoverageReferral(String coverId,String branchCode)
	{
		logger.info("isCoverageReferral - Enter || coverId: "+coverId+" branchCode: "+branchCode);
		boolean referral=false;
		String result="";
		try{
			result = policyDAO.getValue(DBConstants.REFERRAL_COVERAGE,new String[]{coverId, branchCode});
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		referral="Y".equalsIgnoreCase(result);
		logger.info("isCoverageReferral - Exit || Result: "+referral);
		return 	referral;	
	}
	public boolean isVesselReferral(String vesselId)
	{
		logger.info("isVesselReferral - Enter || vesselId: "+vesselId);
		boolean referral=false;
		String result="";
		try{
			sql = getQuery(DBConstants.REFERRAL_VESSEL);
			result = policyDAO.getValue(DBConstants.REFERRAL_VESSEL,new String[]{vesselId});
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		referral="R".equalsIgnoreCase(result);
		logger.info("isVesselReferral - Exit || Result: "+referral);
		return 	referral;	
	}
	public boolean isSameCountryReferral(String fromCountry, String toCountry)
	{
		logger.info("isSameCountryReferral - Enter || fromCountry: "+fromCountry+" toCountry: "+toCountry);
		boolean referral=fromCountry.equalsIgnoreCase(toCountry);
		logger.info("isSameCountryReferral - Exit || Result: "+referral);
		return 	referral;	
	}
	public boolean isPolicyDateReferral(String branchCode, String loginId, String productId,String openCoverNo, String policyStartDate, String openCoverBackDays)
	{
		logger.info("isPolicyDateReferral - Enter");
		boolean referral=false;
		String result="",backDays="0";
		try
		{
			if("3".equalsIgnoreCase(productId))	{
				backDays = getBackDatesAllowed(loginId,productId);
			}else if("11".equalsIgnoreCase(productId)){
				backDays=openCoverBackDays;
			}
			sql = getQuery(DBConstants.REFERRAL_POLICYDATE_DIFF);
			result=(String)this.mytemplate.queryForObject(sql, new String[]{policyStartDate}, String.class);
			referral=Double.parseDouble(result) > Integer.parseInt(backDays);
			if(!referral && "11".equalsIgnoreCase(productId))
			{
				sql = getQuery(DBConstants.REFERRAL_POLICYDATE_OCDIFF);
				result = (String)this.mytemplate.queryForObject(sql, new String[]{policyStartDate,openCoverNo}, String.class);
				referral=Integer.parseInt(result)>0;
			}
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		logger.info("isPolicyDateReferral - Exit || Result: "+referral);
		return 	referral;	
	}
	public boolean isLandReferral(String fromCountryId,String toCountryId,String originCountryId, String branchCode,String productId, String belongingBranch)
	{
		logger.info("isLandReferral - Enter");
		boolean referral=false;
		boolean fromNonGulf=false, toNonGulf=false;
		try{
			if("3".equals(productId) || "11".equals(productId)){
				//fromNonGulf=getLandReferralForNonGCC(fromCountryId, branchCode);
				//toNonGulf=getLandReferralForNonGCC(toCountryId, branchCode);
				fromNonGulf=getLandReferralForNonGCC(fromCountryId, belongingBranch);
				toNonGulf=getLandReferralForNonGCC(toCountryId, belongingBranch);
				referral=fromNonGulf || toNonGulf;
			}
		}
		catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		logger.info("isLandReferral - Exit || Result: "+referral);
		return referral;
	}
	@SuppressWarnings("unchecked")
	public boolean isCountryReferral(String fromCountry,String toCountry,String originCountry, String productId)
	{
		logger.info("isCountryReferral - Enter");
		boolean referral=false;//!((originCountry.equalsIgnoreCase(fromCountry) || originCountry.equalsIgnoreCase(toCountry) || "11".equals(productId)));
		//boolean referral=false;
		if(!referral){
			sql = getQuery(DBConstants.REFERRAL_COUNTRY);
			/*NamedParameterJdbcTemplate template=new NamedParameterJdbcTemplate(this.mytemplate.getDataSource());
			MapSqlParameterSource parameters=new MapSqlParameterSource();
			List<Object> list=new ArrayList<Object>();
			list.add(fromCountry);
			list.add(toCountry);
			parameters.addValue("id", list);
			parameters.addValue("orgCountryId", originCountry);
			List<Object> countryRef=template.queryForList(sql, parameters);*/
			List<Map<String, Object>> countryRef=this.mytemplate.queryForList(sql, new Object[]{fromCountry,fromCountry,toCountry,toCountry});
			if(countryRef!=null){
				if(countryRef.size()>1){
					referral="R".equalsIgnoreCase((countryRef.get(0)).get("STATUS").toString())||"R".equalsIgnoreCase((countryRef.get(1)).get("STATUS").toString());
				}else if(countryRef.size()==1){
					referral="R".equalsIgnoreCase((countryRef.get(0)).get("STATUS").toString());
				}
			}
		}
		logger.info("isCountryReferral - Exit || Result: "+referral);
		return referral;
	}

	public boolean isCrossVoyageCountryReferral(String fromCountry,String toCountry,String crossVoyage,String originCountry)
	{
		boolean referral = !originCountry.equals(fromCountry)&&!originCountry.equals(toCountry)&&"N".equals(crossVoyage);
		return referral;
	}
	public boolean isCrossVoyageCountryReferral(String fromCountry,String toCountry,String crossVoyage,String originCountry, String productId, String startPlace, String endPlace) {
		boolean referral = false;
		if("11".equals(productId) && !originCountry.equals(fromCountry)&&!originCountry.equals(toCountry) && "N".equals(crossVoyage)) {
			referral = true;
		}
		else if("3".equals(productId) && !originCountry.equals(fromCountry)&&!originCountry.equals(toCountry)) {
			referral = true;
		}
		
		if(referral) {
			 startPlace="YES".equalsIgnoreCase(startPlace)?"Warehouse":"Any Port";
			 endPlace="YES".equalsIgnoreCase(endPlace)?"Warehouse":"Any Port";
			String orginCount= policyDAO.getValue("GET_COUNT_CRS_ORGIN", new String[]{fromCountry,startPlace,startPlace});
			String destCount= policyDAO.getValue("GET_COUNT_CRS_DEST", new String[]{toCountry,endPlace,endPlace});
			if(Integer.parseInt(orginCount)>0 && Integer.parseInt(destCount)>0)
				referral=false;
		/*	if(Integer.parseInt(destCount)>0)
				referral=false;*/
		}
		return referral;
	}
	public boolean isCrossVoyageSIReferral(String fromCountry,String toCountry,String crossVoyage,String crossVoyageLimit,String openCoverNo,String originCountry, String totalSumInsured, String brokerType, String branchCode)
	{
		String branchWiseCountry="";
   		branchWiseCountry=service.getbranchWiseCountry(branchCode);
		logger.info("isCrossVoyageSIReferral - Enter");
		boolean referral=false;
		String crossVoyagesSI="0"; 
		try{
			if(originCountry.equalsIgnoreCase(fromCountry) || originCountry.equalsIgnoreCase(toCountry) ||"Y".equalsIgnoreCase(crossVoyage))	
			{
				if(!originCountry.equalsIgnoreCase(fromCountry) && !originCountry.equalsIgnoreCase(toCountry))
				{
					sql = getQuery(DBConstants.REFERRAL_CROSSVOYAGESI);
					crossVoyagesSI = (String)this.mytemplate.queryForObject(sql, new String[]{originCountry, originCountry, openCoverNo}, String.class);
					referral=Double.parseDouble(StringUtils.defaultIfEmpty(crossVoyagesSI, "0"))+Double.parseDouble(PolicyGenerationDAO.round(totalSumInsured, "1", brokerType,branchWiseCountry))> Double.parseDouble(crossVoyageLimit);
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");e.printStackTrace();
		}
		logger.info("isCrossVoyageSIReferral - Exit || Result: "+referral);
		return referral;
	}
	public boolean isSumInsuredReferral(String loginId, String branchCode, String productId,String modeOfTransport,String partialShip,String partialShipAmt,String exposureCurrency, String openCoverNo, String totalSumInsured, String saleTermValue, String toleranceValue, String currencyValue,String sumInsuredLimit)
	{
		logger.info("isSumInsuredReferral - Enter");
		boolean referral=false;
		try{
			double SILimit=Double.parseDouble(sumInsuredLimit);
			double totalSI = 0.0;
			if(partialShip.equalsIgnoreCase("Y") && productId.equalsIgnoreCase("11")){
				totalSI=Double.parseDouble(partialShipAmt)*Double.parseDouble(exposureCurrency);
			}else{
				totalSI = getConvertedTotalSumInsured(Double.parseDouble(saleTermValue),Double.parseDouble(toleranceValue),totalSumInsured,currencyValue);
			}
			referral=totalSI > SILimit;
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");e.printStackTrace();
		}
		logger.info("isSumInsuredReferral - Exit || Result: "+referral);
		return referral;
	}
	public boolean isLCSumInsuredReferral(String branchCode, String lcId, String bankId, String openCoverNo, String saleTermValue, String toleranceValue, String totalSumInsured, String currencyValue, String quoteNo, boolean endt)
	{
		logger.info("isLCSumInsuredReferral - Enter");
		boolean referral=false;
		try{
			Map<String, Object> limitInfo=getLCSuminsuredLimitInfo(lcId, bankId, openCoverNo);
			double totalSI=0.0, lcAmount=0.0,lcTotalSumInsured=0.0;
			if(!CollectionUtils.isEmpty(limitInfo)){
				lcTotalSumInsured = Double.parseDouble(StringUtils.defaultIfEmpty(limitInfo.get("SUM_INSURED_LIMIT").toString(), "0"));
			}
			totalSI = getConvertedTotalSumInsured(Double.parseDouble(saleTermValue),Double.parseDouble(toleranceValue),totalSumInsured,currencyValue);
			if(endt){
				totalSumInsured=StringUtils.defaultIfEmpty(policyDAO.getValue("GET_ENDT_SI_DIFF_REF", new String[]{String.valueOf(totalSI),quoteNo}), "0");
				totalSI = Double.parseDouble(totalSumInsured)+lcTotalSumInsured;
			}else{
				totalSI += lcTotalSumInsured;
			}
			if(Double.parseDouble(totalSumInsured)>0){
				Map<String, Object> lcInfo=getLCMasterInfo(lcId,bankId,openCoverNo);
				if(!CollectionUtils.isEmpty(lcInfo)){
					lcAmount=Double.parseDouble(StringUtils.defaultIfEmpty(lcInfo.get("LC_AMOUNT").toString(), "0"));
					referral=Math.round(totalSI) > Math.round(lcAmount);
				}
			}
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");e.printStackTrace();
		}
		logger.info("isLCSumInsuredReferral - Exit || Result: "+referral);
		return referral;
	}
	public boolean getLandReferralForNonGCC(String countryId, String branchCode)
    {
		sql = getQuery(DBConstants.REFERRAL_LAND);
        int count=this.mytemplate.queryForObject(sql, Integer.class, new String[] {branchCode, countryId});
        return count==0;
    }
	public String getBackDatesAllowed(String loginId, String productId)
	{
		String result="0";
		try{
			sql = getQuery(DBConstants.REFERRAL_POLICYDATE_BACKDATE);
			result = policyDAO.getValue(DBConstants.REFERRAL_POLICYDATE_BACKDATE,new String[]{loginId, productId});
		}catch(Exception e){
			logger.debug("Exception @ {"+e+"}");
		}
		result =StringUtils.defaultIfEmpty(result, "0");
		return result;
     }
	public List<Map<String, Object>> getReferralMasterInfo(String branchCode)
	{
		List<Map<String, Object>> list=null;
		try
		{
			sql = getQuery(DBConstants.REFERRAL_MASTER);
			list=this.mytemplate.queryForList(sql, new String[]{branchCode});
		}
		catch(Exception e)
		{
			logger.debug("Exception @ {"+e+"}");
		}
		return list;
		
	}
	
	public Map<String, Object> getLCSuminsuredLimitInfo(String lcId,String bankId, String openCoverNo) throws Exception
	{
		Map<String, Object> map=null;
		try
		{
			sql = getQuery(DBConstants.REFERRAL_LCSUMINSURED);
			map=(Map<String, Object>)this.mytemplate.queryForMap(sql, new String[]{lcId, bankId, openCoverNo});
		}
		catch(Exception e)
		{
			logger.debug("Exception @ {"+e+"}");
		}
		return map;
	}
	public Map<String, Object> getLCMasterInfo(String lcId, String bankId,String openCoverNo)
	{
		Map<String, Object> map=null;
		try
		{
			sql = getQuery("GET_LC_MASTER_INFO");
			map=(Map<String, Object>)this.mytemplate.queryForMap(sql, new String[]{lcId, bankId, openCoverNo});
		}
		catch(Exception e)
		{
			logger.debug("Exception @ {"+e+"}");
		}
		return map;
	}
	public String getCountryStartPlace(String countryId,String originCountry)
	{
		sql = getQuery(DBConstants.REFERRAL_COUNTRY_STARTPLACE);
		String result=(String)this.mytemplate.queryForObject(sql, new String[]{countryId, originCountry},String.class);
		return result;
	}
	public String getCountryEndPlace(String countryId,String originCountry)
	{
		sql = getQuery(DBConstants.REFERRAL_COUNTRY_ENDPLACE);
		String result=(String)this.mytemplate.queryForObject(sql, new String[]{countryId, originCountry},String.class);
		return result;
	}
	public double getConvertedTotalSumInsured(double salePercentage,double tolPercentage,String sumIns,String currencyValue)
	{
		double dou_SI                  = 0.0;
		double dou_SIAED               = 0.0;
		double dou_saleValue           = 0.0;
		double dou_tolValue            = 0.0;
		double dou_totSaleValue        = 0.0;
		double dou_totTolValue         = 0.0;
		double saleFactor              = 0.0;
		double tolFactor               = 0.0;
		double conSI                   = 0.0;
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
	
	private boolean getExchageRate(WebServicePolicyInfo info, String belongingBranch){
		boolean result=false;
		Map<String, Object> exchangeLimitInfo = getExchangeLimitInfo(belongingBranch,info.getCurrency());
	if (!CollectionUtils.isEmpty(exchangeLimitInfo)) {
		if (Double.parseDouble(info.getCurrencyValue()) < Double .parseDouble(exchangeLimitInfo.get("MIN_LIMIT").toString()) || Double.parseDouble(info.getCurrencyValue()) > Double.parseDouble(exchangeLimitInfo.get("MAX_LIMIT").toString())) {
			result=true;			
		}
	}
	return result;
	}
	public Map<String, Object> getExchangeLimitInfo(String branchCode,String currencyId) {
		Map<String, Object> currencyInfo = new HashMap<String, Object>();
		currencyInfo = (Map<String, Object>) policyDAO.getResultMap(DBConstants.MARINE_EXCHANGELIMIT, new String[] { branchCode,currencyId });
		return currencyInfo;
	}
}
