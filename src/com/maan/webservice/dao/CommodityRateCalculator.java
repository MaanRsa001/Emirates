package com.maan.webservice.dao;
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.services.util.runner;

public class CommodityRateCalculator extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(CommodityRateCalculator.class);
	PolicyGenerationDAO policyDAO=new PolicyGenerationDAO();	
	public String getCommodityRate( String modeOfTransport,String coverage,String conveyanceType,String destCountryId,
		 String commodity,String fragile,double localSumInsured,String branch,String policyStartDate,String applicationNo)
		{
		    double baseRate = 0,factor1 = 0,factor2 = 0,factor3 = 0,factor4 = 0,factor5 = 0,wsrccRates=0,result=0;
		    String returnVal = "";
			try
			{
				fragile = fragile.equalsIgnoreCase("on")?"1":"2";
				String coverageValue = policyDAO.getValue(DBConstants.COVERAGE_VALUE,new String[]{commodity,branch,branch,commodity});
				String coverReferal = policyDAO.getCoverReferral(coverage,StringUtils.defaultIfEmpty(coverageValue, ""),new String[]{commodity,branch,branch,commodity});
				String commodityReferal = policyDAO.getValue(DBConstants.COMMODITY_REFERRAL,new String[]{commodity,branch,branch,commodity});
				//String coverageReferal = policyDAO.getValue(DBConstants.COVERAGE_REFERRAL,new String[]{commodity,coverage,branch,branch,commodity});
				java.text.NumberFormat fmt = new java.text.DecimalFormat("0.00");
				
				if(!commodityReferal.equalsIgnoreCase("R") && coverReferal.equalsIgnoreCase("0")){
					String baseVal = policyDAO.getValue(DBConstants.BASE_VALUE,new String[]{coverage,branch,coverage,branch,policyStartDate});
					baseRate = Double.parseDouble(baseVal.equalsIgnoreCase("")?"0":baseVal);
					
					String fact1Val = policyDAO.getValue(DBConstants.FACT1_VALUE,new String[]{fmt.format(localSumInsured),fmt.format(localSumInsured),branch,fmt.format(localSumInsured),fmt.format(localSumInsured),branch,policyStartDate});
					factor1 = Double.parseDouble(fact1Val.equalsIgnoreCase("")?"0":fact1Val);
					
					String fact2Val = policyDAO.getValue(DBConstants.FACT2_VALUE,new String[]{fragile,branch,coverage,fragile,branch,coverage,policyStartDate});
					factor2 = Double.parseDouble(fact2Val.equalsIgnoreCase("")?"0":fact2Val);
					
					String fact3Val = policyDAO.getValue(DBConstants.FACT3_VALUE,new String[]{destCountryId,destCountryId,policyStartDate});
					factor3 = Double.parseDouble(fact3Val.equalsIgnoreCase("")?"0":fact3Val);
	
					String fact4Val = policyDAO.getValue(DBConstants.FACT4_VALUE,new String[]{conveyanceType,modeOfTransport,branch,conveyanceType,modeOfTransport,branch,policyStartDate});
					factor4 = Double.parseDouble(fact4Val.equalsIgnoreCase("")?"0":fact4Val);
	
					String fact5Val = policyDAO.getValue(DBConstants.FACT5_VALUE,new String[]{commodity,branch,commodity,branch,policyStartDate});
					factor5 = Double.parseDouble(fact5Val.equalsIgnoreCase("")?"0":fact5Val);
	
					String wsrcVal = policyDAO.getValue(DBConstants.WSRCC_VALUE,new String[]{modeOfTransport,branch,modeOfTransport,branch,policyStartDate});
					wsrccRates = Double.parseDouble(wsrcVal.equalsIgnoreCase("")?"0":wsrcVal);
				}
				//result =(baseRate*factor1*factor2*factor3*factor4*factor5)/10;
				result =(baseRate*factor1*factor2*factor3*factor4*factor5);
				
				String marineRate = String.valueOf(Double.valueOf(result)*((100-wsrccRates)/100));
				wsrccRates=result-Double.parseDouble(marineRate);
				
				this.mytemplate.update(getQuery(DBConstants.INSERT_FACTOR),new String[]{commodity,commodity,String.valueOf(baseRate),String.valueOf(factor1),String.valueOf(factor2),String.valueOf(factor3),String.valueOf(factor4),String.valueOf(factor5),String.valueOf(wsrccRates),String.valueOf(marineRate),applicationNo});
				DecimalFormat df = new DecimalFormat("#.####");
				returnVal  = df.format(result);
				logger.info("baseRate:"+baseRate+"factor1(sum insured):"+factor1+"factor2(fragile/non fragile):"+factor2+"factor3(dest country):"+factor3+"factor4(Conveyance):"+factor4+"factor5(commodity):"+factor5+"wsrccRates:"+wsrccRates+"marineRates:"+marineRate);
			}catch(Exception e){
				logger.info("Exception in getCommodityRate .."+e);
			}
			return returnVal;	
	}
	public String getPolicyExcess( String modeOfTransport,String coverage,String commodity,String fragile,double localSumInsured, String applicationNo, String productId, String openCoverNo, String branchCode, String conveyanceId)
	{
		String deductYes="",deduct="0";
		try{
			if("3".equals(productId)){
				String coverdesc = policyDAO.getValue(DBConstants.COVER_DESC,new String[]{coverage});
				
				String selQry = " SELECT SUBSTR("+coverdesc+",INSTR("+coverdesc+",'~',1,4)+1,LENGTH("+coverdesc+")) FROM COMMODITYMASTER A WHERE A.BRANCH_CODE=? AND A.COMMODITY_ID=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM COMMODITYMASTER WHERE BRANCH_CODE=A.BRANCH_CODE AND COMMODITY_ID=A.COMMODITY_ID)";
				deductYes = runner.singleSelection(selQry, new String[]{branchCode, commodity});
				System.out.println("selQry::"+selQry);
				
				//deductYes = policyDAO.getValue(DBConstants.SELECTCOVER_DESC,new String[]{coverdesc,coverdesc,coverdesc,branchCode,commodity});
			}else{
				deductYes="Y";
			}
			if("Y".equalsIgnoreCase(deductYes)){
				if("11".equals(productId)){
				 
					/*
					 * Modified for individual commodity based policy excess
					 * 
					 * Map<String, Object> excessDetails = policyDAO.getResultMap(DBConstants.EXCESS_AMT,new String[]{String.valueOf(localSumInsured), openCoverNo});
					String excessPercent =String.valueOf(excessDetails.get("EXCESS_PERCENT")==null?"0":excessDetails.get("EXCESS_PERCENT"));
					double excessPercentAmount=localSumInsured*((double) Double.parseDouble(excessPercent)/100);
					double excessAmount = Double.parseDouble(String.valueOf(excessDetails.get("EXCESS_AMOUNT")));*/
					String excessPercent = "0";
					double excessPercentAmount = 0;
					double excessAmount = 0;
					Map<String,Object> excessDetails = policyDAO.getResultMap("GET_OPENCOVER_EXCESS_DETAILS", new String[]{openCoverNo,coverage,commodity,conveyanceId}); 
					if(excessDetails!=null){
						excessPercent = excessDetails.get("DISCOUNT_PERCENT")==null?"0":excessDetails.get("DISCOUNT_PERCENT").toString();
						excessPercentAmount = localSumInsured*((double) Double.parseDouble(excessPercent)/100);
						excessAmount = excessDetails.get("DISCOUNT_VALUE")==null?0:Double.valueOf(excessDetails.get("DISCOUNT_VALUE").toString());
					}
					if(excessAmount>=excessPercentAmount)
						deduct=String.valueOf(excessAmount);
					else
						deduct=String.valueOf(excessPercentAmount);
					
				}else{
					String sql = "GET_DEDUCT";
					//deduct = policyDAO.getValue(DBConstants.SELECT_DEDUCTIBLES,new String[]{String.valueOf(localSumInsured)});
					deduct = policyDAO.getValue(sql, new String[]{String.valueOf(localSumInsured),String.valueOf(localSumInsured)});
				}
			}
		}catch(Exception e){
			logger.info("Exception in updateExcessPremium .."+e);
		}
		return deduct;
	}
	public String getLoginId(String applicationNo)
	{
		String getLoginId=runner.singleSelection("SELECT NVL(LOGIN_ID,'') FROM WEBSERVICE_MARINE_QUOTE WHERE APPLICATION_NO=?",new String[]{applicationNo});
		return getLoginId;
	}
} 

	
