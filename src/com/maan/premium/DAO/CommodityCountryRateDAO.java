package com.maan.premium.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.services.util.runner;

public class CommodityCountryRateDAO 
{
	private static final Logger logger = LogUtil.getLogger(CommodityCountryRateDAO.class);
	private String modeOfTransport;
	private String coverageId;
	private String commodityId;
	private String rate;
	private String sno;
	private String originCountryId;
	private String destCountryId;
	private String modeOfTransportDesc;
	private String coverageName;
	private String commodityName;
	private String originCountryName;
	private String destCountryName;
	
	public String getModeOfTransportDesc() {
		return modeOfTransportDesc;
	}
	public void setModeOfTransportDesc(String modeOfTransportDesc) {
		this.modeOfTransportDesc = modeOfTransportDesc;
	}
	public String getCoverageName() {
		return coverageName;
	}
	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getOriginCountryName() {
		return originCountryName;
	}
	public void setOriginCountryName(String originCountryName) {
		this.originCountryName = originCountryName;
	}
	public String getDestCountryName() {
		return destCountryName;
	}
	public void setDestCountryName(String destCountryName) {
		this.destCountryName = destCountryName;
	}
	public String getOriginCountryId() {
		return originCountryId;
	}
	public void setOriginCountryId(String originCountryId) {
		this.originCountryId = originCountryId;
	}
	public String getDestCountryId() {
		return destCountryId;
	}
	public void setDestCountryId(String destCountryId) {
		this.destCountryId = destCountryId;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getCoverageId() {
		return coverageId;
	}
	public void setCoverageId(String coverageId) {
		this.coverageId = coverageId;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String[][] getBrokerList(String branch)
	{
		System.out.println("getBrokerList - Enter");
		String brokerList[][]=runner.multipleSelection("SELECT AGENCY_CODE, COMPANY_NAME FROM BROKER_COMPANY_MASTER WHERE BRANCH_CODE='"+branch+"'");
		System.out.println("getBrokerList - Exit || Result: "+brokerList.length);
		return brokerList;
	}
	public String[][] getModeList(String branch, String productId)
	{
		System.out.println("getModeList - Enter");
		String brokerList[][]=runner.multipleSelection("SELECT MODE_TRANSPORT_ID, TRANSPORT_DESCRIPTION FROM MODE_OF_TRANSPORT WHERE STATUS='Y' AND BRANCH_CODE='"+branch+"' ORDER BY MODE_TRANSPORT_ID");
		System.out.println("getModeList - Exit || Result: "+brokerList.length);
		return brokerList;
	}
	public String[][] getCoverList(String branch, String productId, String modeOfTransport)
	{
		System.out.println("getCoverList - Enter");
		String brokerList[][]=runner.multipleSelection("SELECT COVER_ID, COVER_NAME, MODE_TRANSPORT_ID FROM COVER_MASTER WHERE STATUS='Y' AND BRANCH_CODE='"+branch+"' AND MODE_TRANSPORT_ID='"+modeOfTransport+"' ORDER BY DISPLAY_ORDER");
		System.out.println("getCoverList - Exit || Result: "+brokerList.length);
		return brokerList;
	}
	public String[][] getCommodityList(String branch, String productId)
	{
		System.out.println("getCommodityList - Enter");
		String commodityList[][]=runner.multipleSelection("SELECT COMMODITY_ID,COMMODITY_NAME,FRAGILE,RAG,COMMODITY_TYPE_ID FROM COMMODITYMASTER CM WHERE CM.STATUS IN ('Y','R') AND BRANCH_CODE=? AND  CM.AMEND_ID||'-'||CM.COMMODITY_ID IN(SELECT MAX(AMEND_ID)||'-'||COMMODITY_ID FROM COMMODITYMASTER WHERE TO_DATE(EFFECTIVE_DATE,'dd-MM-YYYY') <=TO_DATE(SYSDATE,'dd-MM-YYYY') AND STATUS IN ('Y','R','N') AND BRANCH_CODE=? GROUP BY COMMODITY_ID) ORDER BY CM.COMMODITY_ID", new String[]{branch,branch});
		System.out.println("getCommodityList - Exit || Result: "+commodityList.length);
		return commodityList;
	}
	public String[][] getCountryList(final String branch)
    {
    	logger.info("getCountryList - Enter");
    	final String query="SELECT COUNTRY_ID,COUNTRY_NAME FROM COUNTRY_MASTER CM WHERE STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_ID=CM.COUNTRY_ID )";
    	final String[][] result=runner.multipleSelection(query);
    	logger.debug("getCountryList - Exit");
		
		return result;
    }
	public String[][] getClausesList(final String branch, final String coverid)
    {
    	logger.info("getClausesList - Enter");
    	final String query="SELECT CLAUSES_ID, CLAUSES_DESCRIPTION,STATUS FROM CLAUSES_MASTER WHERE COVER_ID = '"+coverid+"' AND EXTRA_COVER_ID='0' AND BRANCH_CODE IN ("+branch+") ORDER BY CLAUSES_ID";
    	final String[][] result=runner.multipleSelection(query);
    	logger.debug("getClausesList - Exit");
		
		return result;
    }
	public String[][] getWarrantyList(final String branch)
    {
    	logger.info("getWarrantyList - Enter");
    	final String query="SELECT WARRANTY_ID, WARRANTY_DESCRIPTION,STATUS FROM WARRANTY_MASTER WHERE BRANCH_CODE IN ("+branch+") ORDER BY WARRANTY_ID";
    	final String[][] result=runner.multipleSelection(query);
    	logger.debug("getWarrantyList - Exit || Result: "+result.length);
		
		return result;
    }
	public String addCommodityCountryRate(String mode, String brokerId, String productId, String modeOfTransport, String coverId, String commodityId, 
			String originCountryId, String destCountryId, String rate, String clauses, String warranty, String status, String effectiveDate, String openCoverNo)
	{
		logger.info("addCommodityCountryRate - Enter");
		String result="";
		String condition=openCoverNo.length()>0?" AND NVL(OPENCOVER_NO,'0')='"+openCoverNo+"'":"";
		try{
			String query="SELECT NVL(MAX(AMEND_ID)+1,'0') FROM COMMODITY_COUNTRY_RATE WHERE BROKER_ID=? AND PRODUCT_ID=? AND MODE_OF_TRANSPORT=? AND COVERAGE_ID=? AND COMMODITY_ID=? AND ORIGINATING_COUNTRY=? AND DESTINATION_COUNTRY=?"+condition;
			String args[]=new String[]{brokerId, productId, modeOfTransport, coverId, commodityId, 
					originCountryId, destCountryId};
			String countValue[][]=runner.multipleSelection(query, args);
			
			if(!(Integer.parseInt(countValue[0][0])>0 && mode.equalsIgnoreCase("addRateInfo")))
			{
				args=new String[]{brokerId, productId, modeOfTransport, coverId, commodityId, 
						originCountryId, destCountryId, rate, clauses, warranty, status, effectiveDate,countValue[0][0],openCoverNo};
				query="INSERT INTO COMMODITY_COUNTRY_RATE (BROKER_ID, PRODUCT_ID, MODE_OF_TRANSPORT, COVERAGE_ID, COMMODITY_ID, ORIGINATING_COUNTRY, DESTINATION_COUNTRY, RATE, CLAUSES, WARRANTY, STATUS, EFFECTIVE_DATE, AMEND_ID, OPENCOVER_NO) VALUES (?,?,?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YYYY'),?,?)";
				runner.multipleInsertion(query, args);
			}else
			result="Record Already Exists";	
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.info("addCommodityCountryRate - Exit || Result: "+result);
		return result;
	}
	
	public List<CommodityCountryRateDAO> getCommodityCountryRateList(String brokerId, String productId, String branchCode, String openCoverNo)
	{
		logger.info("getCommodityCountryRateList - Enter || brokerId: "+brokerId+" productId: "+productId+" openCoverNo: "+openCoverNo);
		String result[][]=new String[0][0];
		CommodityCountryRateDAO rate;
		List<CommodityCountryRateDAO> rateList=new ArrayList<CommodityCountryRateDAO>();
		String condition=openCoverNo.length()>0?" AND NVL(CCR.OPENCOVER_NO,'0') ='"+openCoverNo+"' AND NVL(CCR.OPENCOVER_NO,'0') = NVL(CCR1.OPENCOVER_NO,'0')":"";
		try{
			//String query="SELECT MODE_OF_TRANSPORT,COVERAGE_ID,COMMODITY_ID,RATE,ORIGINATING_COUNTRY,DESTINATION_COUNTRY FROM COMMODITY_COUNTRY_RATE WHERE PRODUCT_ID=? AND BROKER_ID=? AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM COMMODITY_COUNTRY_RATE WHERE PRODUCT_ID=? AND BROKER_ID=? AND STATUS='Y')";
			String query="SELECT MT.TRANSPORT_DESCRIPTION,CM.COVER_NAME,C.COMMODITY_NAME,CCR1.RATE,CTY.COUNTRY_NAME ORIGINATING_COUNTRY, CTY1.COUNTRY_NAME DESTINATION_COUNTRY,CCR.MODE_OF_TRANSPORT,CCR.COVERAGE_ID,CCR.COMMODITY_ID, CCR.ORIGINATING_COUNTRY,CCR.DESTINATION_COUNTRY FROM COMMODITY_COUNTRY_RATE CCR1,MODE_OF_TRANSPORT MT,COVER_MASTER CM,(SELECT BRANCH_CODE,COMMODITY_ID,COMMODITY_NAME,MAX(AMEND_ID) FROM COMMODITYMASTER WHERE STATUS='Y' GROUP BY BRANCH_CODE,COMMODITY_ID,COMMODITY_NAME) C, (SELECT COUNTRY_ID,COUNTRY_NAME,MAX(AMEND_ID) FROM COUNTRY_MASTER GROUP BY COUNTRY_ID,COUNTRY_NAME) CTY,(SELECT COUNTRY_ID,COUNTRY_NAME,MAX(AMEND_ID) FROM COUNTRY_MASTER GROUP BY COUNTRY_ID,COUNTRY_NAME) CTY1,(SELECT OPENCOVER_NO,MODE_OF_TRANSPORT,COVERAGE_ID,COMMODITY_ID,ORIGINATING_COUNTRY,DESTINATION_COUNTRY, PRODUCT_ID,BROKER_ID,MAX(AMEND_ID) AMEND_ID FROM COMMODITY_COUNTRY_RATE WHERE STATUS = 'Y' GROUP BY OPENCOVER_NO,MODE_OF_TRANSPORT,COVERAGE_ID,COMMODITY_ID,ORIGINATING_COUNTRY,DESTINATION_COUNTRY, PRODUCT_ID,BROKER_ID) CCR WHERE MT.MODE_TRANSPORT_ID=CCR.MODE_OF_TRANSPORT AND CM.COVER_ID=CCR.COVERAGE_ID AND C.COMMODITY_ID=CCR.COMMODITY_ID AND CTY.COUNTRY_ID=CCR.ORIGINATING_COUNTRY AND CTY1.COUNTRY_ID=CCR.DESTINATION_COUNTRY AND CCR.PRODUCT_ID = ? AND CCR.BROKER_ID = ? AND MT.BRANCH_CODE=CM.BRANCH_CODE AND MT.BRANCH_CODE=C.BRANCH_CODE AND C.BRANCH_CODE=? AND CCR.BROKER_ID=CCR1.BROKER_ID AND CCR.PRODUCT_ID=CCR1.PRODUCT_ID AND CCR.MODE_OF_TRANSPORT=CCR1.MODE_OF_TRANSPORT AND CCR.COMMODITY_ID=CCR1.COMMODITY_ID AND CCR.COVERAGE_ID=CCR1.COVERAGE_ID AND CCR.ORIGINATING_COUNTRY=CCR1.ORIGINATING_COUNTRY AND CCR.DESTINATION_COUNTRY=CCR1.DESTINATION_COUNTRY AND CCR.AMEND_ID=CCR1.AMEND_ID"+condition;
			String args[]=new String[]{productId, brokerId, branchCode};
			result=runner.multipleSelection(query, args);
			if(result!=null && result.length>0)
			{
				for(int i=0; i<result.length; i++)
				{
					rate=new CommodityCountryRateDAO();
					rate.setSno((i+1)+"");
					rate.setModeOfTransportDesc(result[i][0]);
					rate.setCoverageName(result[i][1]);
					rate.setCommodityName(result[i][2]);
					rate.setRate(result[i][3]);
					rate.setOriginCountryName((result[i][4]));
					rate.setDestCountryName((result[i][5]));
					rate.setModeOfTransport(result[i][6]);
					rate.setCoverageId(result[i][7]);
					rate.setCommodityId(result[i][8]);
					rate.setOriginCountryId((result[i][9]));
					rate.setDestCountryId((result[i][10]));
					rateList.add(rate);
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		logger.info("getCommodityCountryRateList - Exit || Result: "+result.length);
		return rateList;
	}
	public String[][] getRateInfo(String brokerId, String productId, String modeOfTransport, String coverId, String commodityId, String originCountryId, String destCountryId, String openCoverNo)
    {
    	logger.info("getRateInfo - Enter");
    	String condition=openCoverNo.length()>0?" AND NVL(B.OPENCOVER_NO,'0')='"+openCoverNo+"'":"";
    	final String query="SELECT B.MODE_OF_TRANSPORT, B.COVERAGE_ID, B.COMMODITY_ID, B.ORIGINATING_COUNTRY, B.DESTINATION_COUNTRY, B.RATE, B.CLAUSES, B.WARRANTY, B.STATUS, TO_CHAR (B.EFFECTIVE_DATE, 'DD-MM-YYYY') FROM COMMODITY_COUNTRY_RATE B WHERE B.BROKER_ID = ? AND B.PRODUCT_ID = ? AND B.MODE_OF_TRANSPORT = ? AND B.COVERAGE_ID = ? AND B.COMMODITY_ID = ? AND B.ORIGINATING_COUNTRY = ? AND B.DESTINATION_COUNTRY = ? AND B.AMEND_ID = (SELECT MAX (A.AMEND_ID) FROM COMMODITY_COUNTRY_RATE A WHERE A.BROKER_ID = B.BROKER_ID AND A.PRODUCT_ID = B.PRODUCT_ID AND A.MODE_OF_TRANSPORT = B.MODE_OF_TRANSPORT AND A.COVERAGE_ID = B.COVERAGE_ID AND A.COMMODITY_ID = B.COMMODITY_ID AND A.ORIGINATING_COUNTRY = B.ORIGINATING_COUNTRY AND A.DESTINATION_COUNTRY = B.DESTINATION_COUNTRY AND NVL(A.OPENCOVER_NO,'0') = NVL(B.OPENCOVER_NO,'0'))"+condition;
    	String args[]=new String[]{brokerId, productId, modeOfTransport, coverId, commodityId, 
				originCountryId, destCountryId};
		String result[][]=runner.multipleSelection(query, args);
    	logger.debug("getRateInfo - Exit || Result: "+result.length);
		
		return result;
    }
	public String getCommodityCountryRate(String loginId, String productId, String applicationNo, String modeOfTransport, String coverId, String commodityId, String originCountryId, String destCountryId, String openCoverNo)
    {
    	logger.info("getCommodityCountryRate - Enter || loginId: "+loginId+" productId: "+productId+" applicationNo: "+applicationNo);
    	String rate="";
    	String condition="";
    	String userType;
		try {
			condition=openCoverNo.length()>0?" AND NVL(B.OPENCOVER_NO,'0')='"+openCoverNo+"'":"";
			userType = new PremiumInputsBean().getUserType(loginId);
			if(userType.equalsIgnoreCase("admin"))
	    		loginId=runner.singleSelection("SELECT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_NO='"+applicationNo+"'");
	    	String query="SELECT B.RATE,B.CLAUSES,B.WARRANTY FROM COMMODITY_COUNTRY_RATE B WHERE B.BROKER_ID = (SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?) AND B.PRODUCT_ID = ? AND B.MODE_OF_TRANSPORT = ? AND B.COVERAGE_ID = ? AND B.COMMODITY_ID = ? AND B.ORIGINATING_COUNTRY = ? AND B.DESTINATION_COUNTRY = ? AND B.STATUS='Y' AND B.AMEND_ID = (SELECT MAX (A.AMEND_ID) FROM COMMODITY_COUNTRY_RATE A WHERE A.BROKER_ID = B.BROKER_ID AND A.PRODUCT_ID = B.PRODUCT_ID AND A.MODE_OF_TRANSPORT = B.MODE_OF_TRANSPORT AND A.COVERAGE_ID = B.COVERAGE_ID AND A.COMMODITY_ID = B.COMMODITY_ID AND A.ORIGINATING_COUNTRY = B.ORIGINATING_COUNTRY AND A.DESTINATION_COUNTRY = B.DESTINATION_COUNTRY AND NVL(A.OPENCOVER_NO,'0') = NVL(B.OPENCOVER_NO,'0'))"+condition;
	    	String[][] result=runner.multipleSelection(query, new String[]{loginId, productId, modeOfTransport, coverId, commodityId, originCountryId, destCountryId});
			if(result!=null && result.length>0)
				rate=result[0][0]==null?"":result[0][0];
		} catch (Exception e) {
			e.printStackTrace();
		}
    	logger.debug("getCommodityCountryRate - Exit || Rate: "+rate);
		
		return rate;
    }
	public String updateWarrantyClauses(String loginId, String productId, String applicationNo)
    {
    	logger.info("updateWarrantyClauses - Enter || loginId: "+loginId+" productId: "+productId+" applicationNo: "+applicationNo);
    	String updateStatus="";
    	String clauses="";
    	String warranty="";
    	String query="SELECT B.OA_CODE,A.MODE_OF_TRANSPORT,A.COVER_ID,C.COMMODITY_ID,A.TRANSIT_FROM_COUNTRY_ID,A.FINAL_DESTINATION_COUNTRY_ID,A.OPEN_COVER_NO FROM MARINE_DATA A, LOGIN_MASTER B, MARINE_RESULT_DETAILS C WHERE A.APPLICATION_NO=? AND C.APPLICATION_NO=A.APPLICATION_NO AND B.LOGIN_ID=(SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_NO=A.APPLICATION_NO)";
    	String[][] result=runner.multipleSelection(query, new String[]{applicationNo});
    	if(result!=null && result.length>0)
    	{
	    	query="SELECT B.CLAUSES,B.WARRANTY FROM COMMODITY_COUNTRY_RATE B WHERE B.BROKER_ID = ? AND B.PRODUCT_ID = ? AND B.MODE_OF_TRANSPORT = ? AND B.COVERAGE_ID = ? AND B.COMMODITY_ID = ? AND B.ORIGINATING_COUNTRY = ? AND B.DESTINATION_COUNTRY = ? AND B.OPENCOVER_NO=? AND B.AMEND_ID = (SELECT MAX (A.AMEND_ID) FROM COMMODITY_COUNTRY_RATE A WHERE A.BROKER_ID = B.BROKER_ID AND A.PRODUCT_ID = B.PRODUCT_ID AND A.MODE_OF_TRANSPORT = B.MODE_OF_TRANSPORT AND A.COVERAGE_ID = B.COVERAGE_ID AND A.COMMODITY_ID = B.COMMODITY_ID AND A.ORIGINATING_COUNTRY = B.ORIGINATING_COUNTRY AND A.DESTINATION_COUNTRY = B.DESTINATION_COUNTRY AND A.OPENCOVER_NO=B.OPENCOVER_NO)";
	    	result=runner.multipleSelection(query, new String[]{result[0][0], productId, result[0][1], result[0][2], result[0][3], result[0][4], result[0][5], result[0][6]});
			
			query="UPDATE POSITION_MASTER SET PDF_MODIFY_WARRANTY=?, PDF_MODIFY_CLAUSE=? WHERE APPLICATION_NO=?";
			if(result!=null && result.length>0)
	    	{
				clauses=result[0][0]==null?"":result[0][0];
				warranty=result[0][1]==null?"":result[0][1];
				updateStatus=runner.multipleUpdation(query, new String[]{warranty, clauses, applicationNo});
	    	}
    	}
    	logger.debug("updateWarrantyClauses - Exit || Result: "+updateStatus);
		
		return updateStatus;
    }
	public boolean getWarrantyClausesStatus(String loginId, String productId, String quoteNo)
    {
    	logger.info("getWarrantyClausesStatus - Enter || loginId: "+loginId+" productId: "+productId+" quoteNo: "+quoteNo);
    	boolean status=false;
    	String query="SELECT B.OA_CODE,A.MODE_OF_TRANSPORT,A.COVER_ID,C.COMMODITY_ID,A.TRANSIT_FROM_COUNTRY_ID,A.FINAL_DESTINATION_COUNTRY_ID,A.OPEN_COVER_NO FROM MARINE_DATA A, LOGIN_MASTER B, MARINE_RESULT_DETAILS C, POSITION_MASTER D WHERE D.QUOTE_NO=? AND A.APPLICATION_NO=D.APPLICATION_NO AND C.APPLICATION_NO=A.APPLICATION_NO AND B.LOGIN_ID=(SELECT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_NO=A.APPLICATION_NO)";
    	String[][] result=runner.multipleSelection(query, new String[]{quoteNo});
    	if(result!=null && result.length>0)
    	{
	    	query="SELECT B.CLAUSES,B.WARRANTY FROM COMMODITY_COUNTRY_RATE B WHERE B.BROKER_ID = ? AND B.PRODUCT_ID = ? AND B.MODE_OF_TRANSPORT = ? AND B.COVERAGE_ID = ? AND B.COMMODITY_ID = ? AND B.ORIGINATING_COUNTRY = ? AND B.DESTINATION_COUNTRY = ? AND B.OPENCOVER_NO=? AND B.AMEND_ID = (SELECT MAX (A.AMEND_ID) FROM COMMODITY_COUNTRY_RATE A WHERE A.BROKER_ID = B.BROKER_ID AND A.PRODUCT_ID = B.PRODUCT_ID AND A.MODE_OF_TRANSPORT = B.MODE_OF_TRANSPORT AND A.COVERAGE_ID = B.COVERAGE_ID AND A.COMMODITY_ID = B.COMMODITY_ID AND A.ORIGINATING_COUNTRY = B.ORIGINATING_COUNTRY AND A.DESTINATION_COUNTRY = B.DESTINATION_COUNTRY AND A.OPENCOVER_NO=B.OPENCOVER_NO)";
	    	result=runner.multipleSelection(query, new String[]{result[0][0], productId, result[0][1], result[0][2], result[0][3], result[0][4], result[0][5], result[0][6]});
	    	if(result!=null && result.length>0)
	    		status=true;
    	}
    	logger.debug("getWarrantyClausesStatus - Exit || Status: "+status);
		
		return status;
    }
	public String[][] getOpenCoverList(String brokerId)
	{
		logger.info("getOpenCoverList - Enter");
		String openCoverList[][]=runner.multipleSelection("SELECT DISTINCT (OCPM.OPEN_COVER_NO) FROM OPEN_COVER_POSITION_MASTER OCPM, OPEN_COVER_MASTER OCM WHERE OCM.BROKER_ID =(SELECT LOGIN_ID FROM LOGIN_MASTER WHERE AGENCY_CODE='"+brokerId+"') AND OCM.STATUS = 'Y' AND OCPM.STATUS = 'P' AND OCM.PROPOSAL_NO = OCPM.PROPOSAL_NO AND OCM.AMEND_ID=(SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = OCM.PROPOSAL_NO) ORDER BY OCPM.OPEN_COVER_NO DESC");
		logger.info("getOpenCoverList - Exit || Result: "+openCoverList.length);
		return openCoverList;
	}
}
