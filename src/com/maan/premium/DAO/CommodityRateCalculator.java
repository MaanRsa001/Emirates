package com.maan.premium.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.services.util.runner;


public class CommodityRateCalculator extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(CommodityRateCalculator.class);
   

	public String getCommodityRate( String modeOfTransport,
		 String coverage,
		 String conveyanceType,
		 String destCountryId,
		 String commodity,
		 String fragile,
		 double localSumInsured,
		 String branch,
		 String policyStartDate	,String applicationNo)
		{
		    double baseRate = 0;
		    double factor1 = 0;
		    double factor2 = 0;
		    double factor3 = 0;
		    double factor4 = 0;
		    double factor5 = 0;
		    double wsrccRates = 0;
		    double result=0;
		    String returnVal = "";
			try
			{
				{
				fragile = fragile.equalsIgnoreCase("on")?"1":"2";
				System.out.println("Fragile:"+fragile);
				System.out.println("commodity:"+commodity);
				System.out.println("Date:"+policyStartDate);
				String coverageValue =runner.singleSelection( "select coverage_referal from commoditymaster  where COMMODITY_ID = "+commodity+" and branch_code = "+branch +" and amend_id=(select max(amend_id) from commoditymaster where branch_code ="+branch +" and commodity_id="+commodity+")");
				String coverReferal = runner.singleSelection("select count(commodity_id) from commoditymaster where COMMODITY_ID = "+commodity+" and '"+coverage+"' in ("+coverageValue+") and branch_code = "+branch +"  and amend_id=(select max(amend_id) from commoditymaster where branch_code = '"+branch +"' and commodity_id="+commodity+" )");
				String commodityReferal = runner.singleSelection("select status from commoditymaster where  COMMODITY_ID = "+commodity+" and branch_code = "+branch +" and amend_id= (select max(amend_id) from commoditymaster where branch_code = "+branch +" and commodity_id="+commodity+" )");
				System.out.println("commodityReferal::"+commodityReferal);
				System.out.println("coverReferal::"+coverReferal);
				System.out.println("localSumInsured::"+localSumInsured);
				
				String coverageReferal = runner.singleSelection("select count(*) from commoditymaster where  COMMODITY_ID = "+commodity+" and coverage_referal in( '"+coverage+"') and branch_code = "+branch +" and amend_id= (select max(amend_id) from commoditymaster where branch_code = "+branch +" and commodity_id="+commodity+" )");
				System.out.println("coverageReferal::"+coverageReferal);
				if(!commodityReferal.equalsIgnoreCase("R") && coverReferal.equalsIgnoreCase("0")){
				
				String baseVal = runner.singleSelection("SELECT COVER_RATE FROM COVER_MASTER WHERE COVER_ID ='"+coverage+"' AND BRANCH_CODE='"+branch+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from COVER_MASTER where cover_id='"+coverage+"'  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				baseRate = Double.parseDouble(baseVal.equalsIgnoreCase("")?"0":baseVal);
				
				String fact1Val = runner.singleSelection("SELECT RATE from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="+localSumInsured+" and  END_SUM_INSURED>="+localSumInsured+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="+localSumInsured+" and  END_SUM_INSURED>="+localSumInsured+"  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS') ) AND STATUS='Y' " );
				factor1 = Double.parseDouble(fact1Val.equalsIgnoreCase("")?"0":fact1Val);
				
				String fact2Val = runner.singleSelection("SELECT MATERIAL_RATE FROM MATERIAL_TYPE_MASTER WHERE MATERIAL_ID ='"+fragile+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from MATERIAL_TYPE_MASTER where MATERIAL_ID='"+fragile+"'  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				factor2 = Double.parseDouble(fact2Val.equalsIgnoreCase("")?"0":fact2Val);
				
				String fact3Val = runner.singleSelection("SELECT GEO_RATE  FROM  country_master where country_id='"+destCountryId+"'  AND AMEND_ID=(SELECT max(amend_id) from country_master where country_id='"+destCountryId+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS') ) AND STATUS='Y' " );
				factor3 = Double.parseDouble(fact3Val.equalsIgnoreCase("")?"0":fact3Val);

//				String fact4Val = runner.singleSelection("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+"  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				String fact4Val = runner.singleSelection("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE SNO ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where SNO ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+"  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				factor4 = Double.parseDouble(fact4Val.equalsIgnoreCase("")?"0":fact4Val);
				
				String fact5Val = runner.singleSelection("SELECT commodity_rate FROM commoditymaster WHERE commodity_id ='"+commodity+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from commoditymaster where  commodity_id ='"+commodity+"'  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				factor5 = Double.parseDouble(fact5Val.equalsIgnoreCase("")?"0":fact5Val);
				
				String wsrcVal = runner.singleSelection("SELECT WAR_RATE FROM WAR_RATE_MASTER WHERE MODE_TRANSPORT_ID ='"+modeOfTransport+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from WAR_RATE_MASTER where MODE_TRANSPORT_ID='"+modeOfTransport+"'  AND BRANCH_CODE='"+branch+"' AND EFFECTIVE_DATE <= TO_DATE('"+policyStartDate+"','DD-mm-YYYY HH24:MI:SS')) AND STATUS='Y' " );
				wsrccRates = Double.parseDouble(wsrcVal.equalsIgnoreCase("")?"0":wsrcVal);
				
				System.out.println("baseRate:"+baseRate+"factor1(sum insured):"+factor1+"factor2(fragile/non fragile):"+factor2+"factor3(dest country):"+factor3+"factor4(Conveyance):"+factor4+"factor5(commodity):"+factor5+"wsrccRates:"+wsrccRates);
				
				}
				 
			    //String selDeductible = "select "
				result =( baseRate*factor1*factor2*factor3*factor4*factor5)/10;
				String insertFactors = "insert into COMMODITY_RATE_FACTOR(AMEND_ID, COMMODITY, COVER_RATE, SUM_INSURED_RATE, MATERIAL_RATE, GEO_RATE, CONV_RATE, COMMODITY_RATE, WAR_RATE,RATE,APPLICATION_NO) " +
				"values((select nvl((max(amend_id)+1),'0') from COMMODITY_RATE_FACTOR where commodity='"+commodity+"' )," +
						"'"+commodity+"','"+baseRate+"','"+factor1+"','"+factor2+"','"+factor3+"','"+factor4+"','"+factor5+"','"+wsrccRates+"','"+result+"','"+applicationNo+"')";
				runner.inserion(insertFactors);
				DecimalFormat df = new DecimalFormat("#.####");
				returnVal  = df.format(result);
				}
				System.out.println("baseRate:"+baseRate+"factor1(sum insured):"+factor1+"factor2(fragile/non fragile):"+factor2+"factor3(dest country):"+factor3+"factor4(Conveyance):"+factor4+"factor5(commodity):"+factor5+"wsrccRates:"+wsrccRates);
				//returnVal=Double.toString(result);
				
			}
			catch(Exception e)
			{
				logger.info("Exception in getCommodityRate .."+e);
			}
			return returnVal;	
	}
	public void updateExcessPremium( String modeOfTransport,
			 String coverage,
			 String commodity,
			 String fragile,
			 double localSumInsured, String applicationNo, String productId, String openCoverNo, String branchCode)
			{
			   try{
				    String deductYes="";
				    if("3".equals(productId)){
					    String coverdesc = runner.singleSelection("select distinct description from cover_master where cover_id='"+coverage+"'");
					    String selQry = " SELECT SUBSTR("+coverdesc+",INSTR("+coverdesc+",'~',1,4)+1,LENGTH("+coverdesc+")) FROM COMMODITYMASTER A WHERE A.BRANCH_CODE=? AND A.COMMODITY_ID=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM COMMODITYMASTER WHERE BRANCH_CODE=A.BRANCH_CODE AND COMMODITY_ID=A.COMMODITY_ID)";
						deductYes = runner.singleSelection(selQry, new String[]{branchCode, commodity});
						System.out.println("selQry::"+selQry);
				    }else{
				    	deductYes="Y";
				    }
					if("Y".equalsIgnoreCase(deductYes)){
						System.out.println("Yes");
					
					String sql="", deduct="";
					if("11".equals(productId)){
						sql = "SELECT NVL (A.EXCESS_AMOUNT, '0') FROM OPEN_COVER_DEDUCTIBLE_MASTER A, OPEN_COVER_POSITION_MASTER B WHERE ? BETWEEN A.START_RANGE AND A.END_RANGE AND A.OPEN_COVER_PROPOSAL_NO = B.PROPOSAL_NO AND B.OPEN_COVER_NO = ? " +
								" AND A.STATUS = 'Y' AND TO_DATE(A.effective_date,'dd-mm-YY') <= TO_DATE(SYSDATE,'dd-mm-YY') AND A.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DEDUCTIBLE_MASTER WHERE TO_DATE(effective_date,'dd-mm-YY') <= TO_DATE(SYSDATE,'dd-mm-YY') AND OPEN_COVER_PROPOSAL_NO = A.OPEN_COVER_PROPOSAL_NO)";
						deduct = runner.singleSelection(sql, new String[]{String.valueOf(localSumInsured), openCoverNo});
					}else{
						sql = "SELECT NVL(DEDUCTIBLE,'0') FROM COMMODITY_EXCESS_PREMIUM WHERE ? BETWEEN START_SUM_INSURED AND END_SUM_INSURED AND STATUS='Y'";
						deduct = runner.singleSelection(sql, new String[]{String.valueOf(localSumInsured)});
					}
					System.out.println("Deductible::"+deduct);
					
					String update = "update marine_result_details set COMMODITY_EXCESS_PREMIUM='"+deduct+"' where commodity_id ='"+commodity+"' and application_no='"+applicationNo+"' ";
					runner.updation(update);
					}
					else{
					String update = "update marine_result_details set COMMODITY_EXCESS_PREMIUM='0' where commodity_id ='"+commodity+"' and application_no='"+applicationNo+"' ";
					runner.updation(update);
					}
				}
				catch(Exception e)
				{
					logger.info("Exception in updateExcessPremium .."+e);
				}
					
		}
	
   
} 

	
