package com.maan.admin.DAO;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.services.util.runner;

public class TravelConfigDAO {
	private static final Logger logger = LogUtil.getLogger(TravelConfigDAO.class);
	final private transient static String EDIT = "Edit";
	
	 private HttpSession sessionInfo;
	 private String scheme_id;
	 private String scheme_name;
	 private String branch_code;
	 private String status;
	 private String coreapp_code;
 	 private String rowid;
	 private String option_id;
	 private String option_name;
	 private String coverages_id;
	 private String coverages_name;
	 private String coverages_value;
	 private String rownum;
	 private String rate_id;
	 private String rate_value;
	 private String type;
	 private String age_start;
	 private String age_end;
	 private String discount_id;
	 private String discount_start;
	 private String discount_end;
	 private String relation_type;
	 private String remarks;	
	 private String premium;
	 private String travel_premium_id;
	 private String no_of_days;
	 private String productId;
	 private String effDate;
	 
	 
	 
	 
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getDiscount_end() {
		return discount_end;
	}
	public void setDiscount_end(String discount_end) {
		this.discount_end = discount_end;
	}
	public String getDiscount_id() {
		return discount_id;
	}
	public void setDiscount_id(String discount_id) {
		this.discount_id = discount_id;
	}
	public String getDiscount_start() {
		return discount_start;
	}
	public void setDiscount_start(String discount_start) {
		this.discount_start = discount_start;
	}
	public String getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCoverages_id() {
		return coverages_id;
	}
	public void setCoverages_id(String coverages_id) {
		this.coverages_id = coverages_id;
	}
	public String getCoverages_name() {
		return coverages_name;
	}
	public void setCoverages_name(String coverages_name) {
		this.coverages_name = coverages_name;
	}
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	public String getOption_id() {
		return option_id;
	}
	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getCoreapp_code() {
		return coreapp_code;
	}
	public void setCoreapp_code(String coreapp_code) {
		this.coreapp_code = coreapp_code;
	}
	public String getRowid() {
		return rowid;
	}
	public void setRowid(String rowid) {
		this.rowid = rowid;
	}
	public String getScheme_id() {
		return scheme_id;
	}
	public void setScheme_id(String scheme_id) {
		this.scheme_id = scheme_id;
	}
	public String getScheme_name() {
		return scheme_name;
	}
	public void setScheme_name(String scheme_name) {
		this.scheme_name = scheme_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCoverages_value() {
		return coverages_value;
	}
	public String getAge_end() {
		return age_end;
	}
	public void setAge_end(String age_end) {
		this.age_end = age_end;
	}
	public String getAge_start() {
		return age_start;
	}
	public void setAge_start(String age_start) {
		this.age_start = age_start;
	}
	public String getRate_id() {
		return rate_id;
	}
	public void setRate_id(String rate_id) {
		this.rate_id = rate_id;
	}
	public String getRate_value() {
		return rate_value;
	}
	public void setRate_value(String rate_value) {
		this.rate_value = rate_value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setCoverages_value(String coverages_value) {
		this.coverages_value = coverages_value;
	}
	public List getOptionList(String branchCode){
		
		String result[][]=new String[0][0];
	    String sql="select option_id,option_name,case when(status='Y') then 'Active' else 'InActive' end status ,coreapp_code from TRAVEL_OPTION_COVERS where branch_code=? ORDER BY option_id ASC";
	    System.out.println("Query===>"+sql);
		final List display=new ArrayList();
		result=runner.multipleSelection(sql,new String[]{branchCode});
		 
		if(result.length>0)
		{
			for(int i=0;i<result.length;i++)
			{
				TravelConfigDAO tDAO=new TravelConfigDAO();
				tDAO.setOption_id(result[i][0]);
				tDAO.setOption_name(result[i][1]);
				tDAO.setStatus(result[i][2]);
				tDAO.setCoreapp_code(result[i][3]);
				display.add(tDAO);
			}
			
		}
		
		return display;
	}
	public String[][] getTypeDropDown(String rate_id){
		String result[][] = new String[0][0];
	String query="select distinct(type) from TRAVEL_DISCOUNT_RATE where rate_id=?";
	System.out.println("Query===>"+query);
	String args[]=new String[1];
	args[0]=rate_id;
	result=runner.multipleSelection(query);
	return result;
		
	}
	
	public String[][] getSchemeDropDown(String branchCode,String productId){
		logger.info("**************now i am in getSchemeDropDown method of travel link coverages*********");
		String result[][] = new String[0][0];
		logger.info("Product Code==>"+productId);
		final String query="select scheme_id,scheme_name from TRAVEL_SCHEME_COVERS where status='Y' and PRODUCT_CODE='"+productId+"' and branch_code='"+branchCode+"' ";
		System.out.println("Query===>"+query);
		result=runner.multipleSelection(query);
		logger.info("**************i***********t goes to the getschemeDropDown method of dao"+result);
		return result;
		
	}
	public String[][] getNoofdays(String productIdx){
		logger.info("now i am in getnoofdays block of the premium rate ");
		String result[][]=new String[0][0];
		logger.info("Product Code==>"+productIdx);
		 String query="select NOOFDAYS FROM NO_OF_DAYS WHERE PRODUCT_CODE='"+productIdx+"'";  //WHERE PRODUCT_CODE='"+productid+"'
		 System.out.println("Query===>"+query);
		 result=runner.multipleSelection(query);
		return result;
		
		
	}
	public String[][] getSchemeOptionDropDown( String branchCode,String productId){
		logger.info("**************now i am in getOptionSchemeDropDown method of travel link coverages*********");
		String result[][] = new String[0][0];
		logger.info("product Code==>"+productId);
		final String query="select scheme_id,scheme_name from TRAVEL_SCHEME_COVERS where status='Y' and PRODUCT_CODE='"+productId+"' and branch_code='"+branchCode+"'";
		System.out.println("Query===>"+query);
		result=runner.multipleSelection(query);
		
		return result;
		
	}
	
	public String[][] getOptionDropDown( String schemeId, String branchCode,String productCode){
		String result[][] = new String[0][0];
		final String query="select option_id,option_name from TRAVEL_OPTION_COVERS where status='Y' and branch_code='"+branchCode+"' AND OPTION_ID IN (select option_id from TRAVEL_SCHEME_OPTION_COVERS where scheme_id="+schemeId+" and status='Y' and PRODUCT_CODE="+productCode+" and branch_code='"+branchCode+"') ";
		System.out.println("Query===>"+query);
		result=runner.multipleSelection(query);
		return result;
	}
	public String[][] getActiveCoveragesList( String branchCode){
		String result[][] = new String[0][0];
		String query="SELECT COVERAGES_ID,COVERAGES_NAME FROM TRAVEL_MAS_COVERAGES WHERE STATUS='Y' AND BRANCH_CODE='"+branchCode+"'";
		System.out.println("Query===>"+query);
		result=runner.multipleSelection(query);

		return result; 
	}
	public String[][] getLinkOptionTravel(final String branchCode){
		String result[][] = new String[0][0];
		String query="SELECT OPTION_ID,OPTION_NAME FROM TRAVEL_OPTION_COVERS WHERE STATUS='Y' and branch_code='"+branchCode+"'";
		System.out.println("Query===>"+query);
		result=runner.multipleSelection(query);
		return result; 
	}
public String[][] getApplicableCoverId( String schemeId, String optionId, String branchCode,String productCode){
	logger.info("*****************now i am in getApplicableCoverId in dao ,it representing the link coverages******************");	
	String result[][] = new String[0][0];
 //String query="SELECT COVERAGES_ID,( SELECT RATE FROM TRAVEL_LINK_COVERAGES WHERE COVER_ID= ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? AND STATUS='Y' ) AND COVERAGES_ID=TM.COVERAGES_ID ) RATE FROM TRAVEL_MAS_COVERAGES TM WHERE COVERAGES_ID IN( SELECT COVERAGES_ID FROM TRAVEL_LINK_COVERAGES WHERE COVER_ID IN ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? AND STATUS='Y' ) )  AND STATUS='Y'";
	String query="SELECT tmc.COVERAGES_ID,tlc.RATE,to_char(tlc.eff_date,'dd/mm/yyyy') FROM TRAVEL_MAS_COVERAGES tmc ,TRAVEL_LINK_COVERAGES tlc ,TRAVEL_SCHEME_OPTION_COVERS tsoc WHERE  tlc.COVER_ID = tsoc.COVER_ID AND tsoc.SCHEME_ID=?  AND tsoc.OPTION_ID=?  AND tsoc.PRODUCT_CODE=? AND tsoc.BRANCH_CODE=?  AND tsoc.STATUS='Y' AND tsoc.COVER_ID = tlc.COVER_ID  AND tmc.COVERAGES_ID = tlc.COVERAGES_ID AND tmc.STATUS = 'Y' AND tlc.STATUS = 'Y' AND tlc.branch_code = ? and tlc.branch_code = ? AND tlc.product_code = ? AND tlc.eff_date < sysdate AND tlc.amend_id = (SELECT MAX(amend_id) FROM TRAVEL_LINK_COVERAGES WHERE product_code=tlc.product_code AND cover_id = tlc.cover_id AND branch_code = tlc.branch_code AND eff_date <= sysdate)";
	System.out.println("Query===>"+query);
	String args[]=new String[7];
	args[0]=schemeId;
	args[1]=optionId;
	args[2]=productCode;
	args[3]=branchCode;
	args[4]=branchCode;
	args[5]=branchCode;
	args[6]=productCode;
	result=runner.multipleSelection(query,args);
	logger.info("Length: "+result.length);
    return result; 
	}
public String[][] getOptionData( String schemeId, String optionId, String branchCode,String productCode){
	String result[][] = new String[0][0];
	String query="SELECT OPTION_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE STATUS='Y' AND SCHEME_ID=? AND PRODUCT_CODE=? AND BRANCH_CODE=?";
	//String query="SELECT OPTION_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE BRANCH_CODE=? AND STATUS='Y' AND PRODUCT_CODE=?";
	System.out.println("Query===>"+query);
	String args[]=new String[3];
		args[0]=schemeId;
		args[1]=productCode;
		args[2]=branchCode;
		logger.info("Branch Code==>"+branchCode);
		logger.info("Product Code==>"+productCode);
		result=runner.multipleSelection(query,args);
		return result; 
}

public List getSchemeList(String branchCode){
	String result[][]=new String[0][0];
	final List display=new ArrayList();
	try{
		String sql="select scheme_id,scheme_name,case when(status='Y') then 'Active' else 'InActive' end status ,coreapp_code from TRAVEL_SCHEME_COVERS where branch_code=?";
		System.out.println("Query===>"+sql);
		result=runner.multipleSelection(sql,new String[]{branchCode});
		
		if(result.length>0){
			for(int i=0;i<result.length;i++){
				TravelConfigDAO tDAO=new TravelConfigDAO();
				tDAO.setScheme_id(result[i][0]);
				tDAO.setScheme_name(result[i][1]);
				tDAO.setStatus(result[i][2]);
				tDAO.setCoreapp_code(result[i][3]);
				display.add(tDAO);
			}
		}
		}
		catch(Exception e){
		logger.fatal(e);
		}
		return display;
		}
public List getPremiumList(String branchCode,String productCode){
	
	String result[][]=new String[0][0];
	final List display=new ArrayList();
try{
	 //String sql="select TRAVEL_PREMIUM_ID,NO_OF_DAYS,(select scheme_name from TRAVEL_SCHEME_COVERS where scheme_id=tp.scheme_id),(select option_name from TRAVEL_OPTION_COVERS where option_id=tp.option_id),PREMIUM FROM TRAVEL_PREMIUM_RATE tp WHERE PRODUCT_CODE=? AND TP.EFF_DATE>=sysdate AND BRANCH_CODE=? ORDER BY tp.scheme_id,tp.option_id";
	
	String sql="SELECT TPR.TRAVEL_PREMIUM_ID,TPR.NO_OF_DAYS,TSC.SCHEME_NAME,TOC.OPTION_NAME,TPR.PREMIUM,to_char(TPR.EFF_DATE,'dd/mm/yyyy')  FROM TRAVEL_PREMIUM_RATE TPR,TRAVEL_OPTION_COVERS TOC,TRAVEL_SCHEME_COVERS TSC WHERE TPR.OPTION_ID=TOC.OPTION_ID AND TPR.SCHEME_ID = TSC.SCHEME_ID AND TSC.STATUS = 'Y' AND TOC.STATUS = 'Y' AND TPR.PRODUCT_CODE = ? AND TPR.BRANCH_CODE = ? AND TSC.BRANCH_CODE =TPR.BRANCH_CODE and TOC.BRANCH_CODE=TPR.BRANCH_CODE AND (TPR.EFF_DATE >= sysdate OR (TPR.EFF_DATE <= sysdate AND TPR.AMEND_ID = ( SELECT MAX(AMEND_ID) FROM TRAVEL_PREMIUM_RATE WHERE PRODUCT_CODE=TPR.PRODUCT_CODE  AND SCHEME_ID=TPR.SCHEME_ID AND OPTION_ID=TPR.OPTION_ID AND NO_OF_DAYS=TPR.NO_OF_DAYS AND BRANCH_CODE= ?  AND EFF_DATE <= sysdate))) ORDER BY 3,4,2";
	System.out.println("Query===>"+sql);
	String cols[]=new String[3];
	cols[0]=productCode;
	cols[1]=branchCode;
	cols[2]=branchCode;
	result=runner.multipleSelection(sql,cols);

	if(result.length>0){
		for(int i=0;i<result.length;i++){
			TravelConfigDAO tDAO=new TravelConfigDAO();
			tDAO.setTravel_premium_id(result[i][0]);
			tDAO.setNo_of_days(result[i][1]);
			tDAO.setScheme_id(result[i][2]);
			tDAO.setOption_id(result[i][3]);
			tDAO.setPremium(result[i][4]);
			tDAO.setEffDate(result[i][5]);
			
			display.add(tDAO);
		}
		}
	
}catch(Exception e){
	logger.fatal(e);
	
}return display;

}
	public List getRateList(String branchCode,String productCode){
		
		String result[][]=new String[0][0];
		final List display=new ArrayList();
		
		try{
		 //String sql="select rate_id,type,age_start,age_end,rate_value from TRAVEL_DISCOUNT_RATE where product_code=?";
		 String sql="SELECT rate_id,type,age_start,age_end,rate_value,to_char(eff_date,'dd/mm/yyyy') FROM TRAVEL_DISCOUNT_RATE tdr WHERE tdr.product_code=? AND  tdr.branch_code= ? and (tdr.eff_date>= sysdate or (tdr.eff_date< sysdate and tdr.amend_id = (SELECT MAX(amend_id) FROM travel_discount_rate WHERE product_code=? AND age_start=tdr.age_start AND age_end=tdr.age_end AND type=tdr.type AND branch_code= ? AND eff_date < sysdate))) order by 3,4";
		 System.out.println("Query===>"+sql);
		 String cols[]=new String[4];
		cols[0]=productCode;
		cols[1]=branchCode;
		cols[2]=productCode;
		cols[3]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		if(result.length>0){
			for(int i=0;i<result.length;i++){
			   TravelConfigDAO tDAO=new TravelConfigDAO();
				tDAO.setRate_id(result[i][0]);
				tDAO.setType(result[i][1]);
				tDAO.setAge_start(result[i][2]);
				tDAO.setAge_end(result[i][3]);
				tDAO.setRate_value(result[i][4]);
				tDAO.setEffDate(result[i][5]);
				display.add(tDAO);
			}
			}
		
		}
		catch(Exception e){
			logger.fatal(e);
		}
		return display;
	}
	public List getRelationDiscount(String branchCode,String productId){
		String result[][]=new String[0][0];
		final List display=new ArrayList();
		try{
			String sql="select discount_id,discount_start,discount_end,rate_value,relation_type,to_char(eff_Date,'dd/mm/yyyy') from TRAVEL_RELATION_DISCOUNT trd where trd.product_code=? and trd.BRANCH_CODE=? and (trd.eff_date>= sysdate or (trd.eff_date< sysdate and trd.amend_id = (SELECT MAX(amend_id) FROM TRAVEL_RELATION_DISCOUNT WHERE product_code=? AND discount_start=trd.discount_start AND discount_end=trd.discount_end AND relation_type=trd.relation_type AND branch_code= ? AND eff_date < sysdate))) order by 2,3";
			System.out.println("Query===>"+sql);
			String cols[]=new String[4];
			
			cols[0]=productId;
			cols[1]=branchCode;
			cols[2]=productId;
			cols[3]=branchCode;
			result=runner.multipleSelection(sql,cols);
		
			if(result.length>0){
				for(int i=0;i<result.length;i++){
					TravelConfigDAO tDAO=new TravelConfigDAO();
					tDAO.setDiscount_id(result[i][0]);
					tDAO.setDiscount_start(result[i][1]);
					tDAO.setDiscount_end(result[i][2]);
					tDAO.setRate_value(result[i][3]);
					tDAO.setRelation_type(result[i][4]);
					tDAO.setEffDate(result[i][5]);
					display.add(tDAO);
				}
				}
			
		}catch(Exception e){
			logger.fatal(e);
			
		}
		
		return display;
	}
	public List getCoveragesList(final String branchCode){
		String result[][]=new String[0][0];
		final List display=new ArrayList();
		
		try{
		 String sql="select coverages_id,coverages_name,coverages_value,case when(status='Y') then 'Active' else 'InActive' end status ,coreapp_code from TRAVEL_MAS_COVERAGES where branch_code=?";
		 System.out.println("Query===>"+sql);
		 result=runner.multipleSelection(sql,new String[]{branchCode});
		
		if(result.length>0){
			for(int i=0;i<result.length;i++){
				TravelConfigDAO tDAO=new TravelConfigDAO();
				tDAO.setCoverages_id(result[i][0]);
				tDAO.setCoverages_name(result[i][1]);
				tDAO.setCoverages_value(result[i][2]);
				tDAO.setStatus(result[i][3]);
				tDAO.setCoreapp_code(result[i][4]);
				display.add(tDAO);
			}
			
		}
		
		}
		catch(Exception e){
		logger.fatal(e);
		}
		return display;
		
	}
	public String[][] getTravelEditOption(String branchCode, String option_id){
		
		String result[][]=new String[0][0];
		logger.info("***********************now i am in gettravelEditoption block************************");
		try{
			 String sql="select OPTION_ID,OPTION_NAME,STATUS,COREAPP_CODE FROM TRAVEL_OPTION_COVERS WHERE OPTION_ID=? and branch_code=?";
			 System.out.println("Query===>"+sql);
			 String cols[]=new String[2];
			cols[0]=option_id;
			cols[1]=branchCode;
			result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e){
			logger.fatal(e);
			}
		
		return result;
	}
	public String[][] getTravelEditCoverages( String branchCode, String coverages_id){
		
		String result[][]=new String[0][0];
		logger.info("****************now i am in gettraveleditcoverages method*************");
		try{
			
			final String sql="select COVERAGES_NAME,COVERAGES_VALUE,STATUS,COREAPP_CODE FROM TRAVEL_MAS_COVERAGES WHERE BRANCH_CODE='"+branchCode+"' AND COVERAGES_ID='"+coverages_id+"'";
			System.out.println("Query===>"+sql);
			String cols[]=new String[2];
			cols[0]=branchCode;
			cols[1]=coverages_id;
			result=runner.multipleSelection(sql,cols);
			}
		catch(Exception e){
				logger.fatal(e);
				}
		return result;
	}
	
	public String[][] getTravelEdit( String branchCode,String scheme_id){
		logger.info("*************now i am in travelEdit method*******************");
		String result[][]=new String[0][0];
		try{
	
			String sql="select scheme_id,scheme_name,status,coreapp_code from TRAVEL_SCHEME_COVERS where scheme_id=? AND BRANCH_CODE=? ";
			System.out.println("Query===>"+sql);
			String cols[]=new String[2];
		cols[0]=scheme_id;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e){
		logger.fatal(e);
		
		}
		return result;
		}
	public String[][] getDiscountRelationEdit( String branchCode, String discount_id){
		String result[][]=new String[0][0];
		try{
			logger.info("now i am in edit method of dao in travel relation discount **");
			String query="select discount_id,discount_start,discount_end,rate_value,relation_type,remarks,to_char(eff_Date,'dd/mm/yyyy') from TRAVEL_RELATION_DISCOUNT where discount_id=? and branch_code=?";
			System.out.println("Query===>"+query);
			String cols[]=new String[2];
			cols[0]=discount_id;
			cols[1]=branchCode;
			result=runner.multipleSelection(query,cols);
			
		}catch(Exception e){
			
			logger.fatal(e);
		}
		return result;
	}
	public String[][] getTravelPremiumEdit( String branchCode, String travel_premium_id,String productCode){
		String result[][]=new String[0][0];
		try{
			
			
		
			String sql="select travel_premium_id,no_of_days,scheme_id,option_id,premium,to_char(EFF_DATE,'dd/mm/yyyy') from TRAVEL_PREMIUM_RATE where travel_premium_id=? and product_code=? and branch_code=?";
			System.out.println("Query===>"+sql);
			String cols[]=new String[3];
			cols[0]=travel_premium_id;
			cols[1]=productCode;
			cols[2]=branchCode;
			result=runner.multipleSelection(sql,cols);
			
		}catch(Exception e){
			
		logger.fatal(e);
			
		}
		return result;
	}
public String[][] getTravelRateEdit(String branchCode, String rate_id,String productCode){
	String result[][]=new String[0][0];
	try{
		
		 String sql="select rate_id,type,age_start,age_end,rate_value,to_char(EFF_DATE,'dd/mm/yyyy') from TRAVEL_DISCOUNT_RATE where product_code=? and rate_id=? AND BRANCH_CODE=?";
		 System.out.println("Query===>"+sql);
		 String cols[]=new String[3];
		cols[0]=productCode;
		cols[1]=rate_id;
		cols[2]=branchCode;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e){
			logger.fatal(e);
		}
		return result;
		
	}
	public boolean validate( String scheme_id, String scheme_name, String mode){
		logger.info("it comes to validate method*********************");
		boolean result = false;
		try{
			if(!EDIT.equalsIgnoreCase(mode)){
				scheme_id="0";
			}
			
		String query="select count(*) from  TRAVEL_SCHEME_COVERS where upper(SCHEME_NAME)=upper(?) and scheme_id not in(?)";
		System.out.println("Query===>"+query);
		String args[]=new String[2];  
		args[0]=scheme_name;
		args[1]=scheme_id;
		
		final int check=Integer.parseInt(runner.singleSelection(query,args));
		
	if(check>0){
		result=true;
		}
}catch(Exception e){
			logger.fatal(e);
			
		}
		return result;
		}
	public String relationValidate(String discount_id, String discount, String branchCode,String productId){
		String result[][]=new String[0][0];
		String relation="";
		
		if("".equalsIgnoreCase(discount_id))
		discount_id="0";
		
		try{
			String query="SELECT RELATION_TYPE FROM TRAVEL_RELATION_DISCOUNT WHERE ? BETWEEN DISCOUNT_START AND DISCOUNT_END AND DISCOUNT_ID !=? AND PRODUCT_CODE=?";
			System.out.println("Query===>"+query);
			String args[]=new String[3];
			args[0]=discount;
			args[1]=discount_id;
			args[2]=productId;
			
			result=runner.multipleSelection(query,args);
			if(result.length>0)
			{
				relation=result[0][0];
			}
			
		}catch(Exception e){
			logger.fatal(e);
			
		}
		return relation;
	}
	
	public int relationValidate1(String discount_id, String relationType, String branchCode,String productId){
		int result=0;
		if("".equalsIgnoreCase(discount_id))
			discount_id="0";
		
		try{
			String query="SELECT COUNT(*) FROM TRAVEL_RELATION_DISCOUNT WHERE UPPER(RELATION_TYPE)=UPPER(?) AND DISCOUNT_ID !=? AND PRODUCT_CODE=?";
			System.out.println("Query===>"+query);
			String args[]=new String[3];
			args[0]=relationType;
			args[1]=discount_id;
			args[2]=productId;
			
			result=Integer.parseInt(runner.singleSelection(query,args));
			
		}catch(Exception e){
			logger.fatal(e);
			
		}
		return result;
	}
	
public int recordAlreadyExists(String schemeId,String optionId,String noofDays,String travel_premium_id,String branchCode,String productCode)
{
	if("".equalsIgnoreCase(travel_premium_id))
	travel_premium_id="0";
	
	int count=0;
	String query="SELECT COUNT(*) FROM TRAVEL_PREMIUM_RATE WHERE SCHEME_ID=? AND OPTION_ID=? AND NO_OF_DAYS=? AND TRAVEL_PREMIUM_ID!=? AND PRODUCT_CODE=?";
	System.out.println("Query===>"+query);
	String[] cols=new String[5];
	cols[0]=schemeId;
	cols[1]=optionId;
	cols[2]=noofDays;
	cols[3]=travel_premium_id;
	cols[4]=productCode;
	
	count=Integer.parseInt(runner.singleSelection(query,cols));
	
	return count;
}
	
public String rateValidate( String rate_id, String age, String branchCode,String productCode){
		String result[][]=new String[0][0];
		String returnValue="";
		
		if("".equalsIgnoreCase(rate_id))
		rate_id="0";
		
	try{
	String query="SELECT TYPE FROM TRAVEL_DISCOUNT_RATE WHERE ? BETWEEN AGE_START AND AGE_END AND RATE_ID! =? AND PRODUCT_CODE=?";
	System.out.println("Query===>"+query);
	String args[]=new String[3];
	args[0]=age;
	args[1]=rate_id;
	args[2]=productCode;
	
	result=runner.multipleSelection(query,args);
	
	if(result.length>0)
	{
		returnValue=result[0][0];
	}
	
	
}catch(Exception e){
			logger.fatal(e);			
		}
return returnValue;
}
	
public int rateValidate1( String rate_id, String type, String branchCode,String productCode){
		int result=0;
		
		if("".equalsIgnoreCase(rate_id))
		rate_id="0";
		
	try{
	String query="SELECT COUNT(*) FROM TRAVEL_DISCOUNT_RATE WHERE UPPER(TYPE)=UPPER(?) AND RATE_ID !=? AND PRODUCT_CODE=?";
	System.out.println("Query===>"+query);
	String args[]=new String[3];
	args[0]=type;
	args[1]=rate_id;
	args[2]=productCode;
	
	result=Integer.parseInt(runner.singleSelection(query,args));
	
	
}catch(Exception e){
			logger.fatal(e);			
		}
return result;
}
	
public boolean optionValidate( String option_id, String option_name, String mode){
		
		boolean result = false;
		try{
			if(!EDIT.equalsIgnoreCase(mode)){
				option_id="0";
			}
			
		 String query="select count(*) from  TRAVEL_OPTION_COVERS where upper(OPTION_NAME)=upper(?) and OPTION_ID not in(?)";
		 System.out.println("Query===>"+query);
		 String args[]=new String[2];  
		args[0]=option_name;
		args[1]=option_id;
		
		final int check=Integer.parseInt(runner.singleSelection(query,args));
		
	if(check>0){
		result=true;
		}
}catch(Exception e){
			logger.fatal(e);
			
		}
		return result;
		}
public boolean coveragesValidate(String coverages_id,String coverages_value, String mode){
	logger.info("it come to coveragesvalidate method");
	boolean result = false;
	try{
		if(!EDIT.equalsIgnoreCase(mode)){
			coverages_id="0";
		}
		
	 String query="select count(*) from  TRAVEL_MAS_COVERAGES where upper(COVERAGES_VALUE)=upper(?) and COVERAGES_ID not in(?)";
	 System.out.println("Query===>"+query);
	 String args[]=new String[2];  
	args[0]=coverages_value;
	args[1]=coverages_id;
	
	final int check=Integer.parseInt(runner.singleSelection(query,args));
	
	if(check>0){
	result=true;
	}
	}catch(Exception e){
		logger.fatal(e);
		
	}
	return result;
	}
public boolean coveragesNameValidate(String coverages_id, String coverages_name, String mode){
	logger.info("it come to coveragesvalidate method");
	boolean result = false;
	try{
		if(!EDIT.equalsIgnoreCase(mode)){
			coverages_id="0";
		}
		
	 String query="select count(*) from  TRAVEL_MAS_COVERAGES where upper(COVERAGES_NAME)=upper(?) and COVERAGES_ID not in(?)";
	 System.out.println("Query===>"+query);
	 String args[]=new String[2];  
	args[0]=coverages_name;
	args[1]=coverages_id;
	
	final int check=Integer.parseInt(runner.singleSelection(query,args));
	
	if(check>0){
	result=true;
	}
	}catch(Exception e){
	logger.fatal(e);
		
	}
	return result;
	}
public String insertTravelPremiumRate(String travel_premium_id, String mode,String branchCode,String productCode,String effDate,String brCode){
	String result=null;
	String cols[];
	String sql;
	try{
		
		/*if("Edit".equalsIgnoreCase(mode))
		{
		//sql="UPDATE TRAVEL_PREMIUM_RATE SET PREMIUM=?,EFF_DATE=sysdate WHERE TRAVEL_PREMIUM_ID=? ";
		sql="INSERT INTO TRAVEL_PREMIUM_RATE(TRAVEL_PREMIUM_ID,NO_OF_DAYS,SCHEME_ID,OPTION_ID,EFF_DATE,PREMIUM,PRODUCT_CODE) VALUES((SELECT nvl(MAX(TRAVEL_PREMIUM_ID),0)+1 FROM TRAVEL_PREMIUM_RATE),?,?,?,sysdate,?,?)";
		cols=new String[2];
		cols[0]=premium;
		cols[1]=travel_premium_id;
		//cols[2]=no_of_days;
		//logger.info("No_of_Days===>"+cols[2]);
		result=runner.multipleUpdation(sql, cols);
		}
		else{*/
			sql="INSERT INTO TRAVEL_PREMIUM_RATE(TRAVEL_PREMIUM_ID,NO_OF_DAYS,SCHEME_ID,OPTION_ID,EFF_DATE,PREMIUM,PRODUCT_CODE,AMEND_ID,BRANCH_CODE) VALUES((SELECT nvl(MAX(TRAVEL_PREMIUM_ID),0)+1 FROM TRAVEL_PREMIUM_RATE WHERE BRANCH_CODE=?),?,?,?,CONVERT(DATETIME,?,'dd/mm/yyyy'),?,?,(select nvl(max(amend_id),'-1')+1 FROM TRAVEL_PREMIUM_RATE WHERE SCHEME_ID=? AND OPTION_ID=? AND BRANCH_CODE=?),?)";
			System.out.println("Query===>"+sql);
			cols=new String[11];
			cols[0]=brCode;
			cols[1]=no_of_days;
			cols[2]=scheme_id;
			cols[3]=option_id;
			cols[4]=effDate;
			cols[5]=premium;
			cols[6]=productCode;
			cols[7]=scheme_id;
			cols[8]=option_id;
			cols[9]=brCode;
			cols[10]=brCode;
			System.out.println("Sql=>"+sql);
			System.out.println("obj[]=>"+StringUtils.join(cols,","));
			System.out.println("Result=>"+runner.multipleUpdation(sql, cols));
			
		//}	
	}catch(Exception e){
		
		logger.fatal(e);
		
	}
	 return result;	
}
public String insertTravelDiscountRelation(String discount_id,String mode,String branchCode,String productId,String effDate){
	String result=null;
	String cols[];
 String sql="";
	try{
		
		System.out.println("insertTravelDiscountRelation => Mode: "+mode);
	
		/*if("Edit".equalsIgnoreCase(mode))
		{
		sql="UPDATE TRAVEL_RELATION_DISCOUNT SET DISCOUNT_START=?,DISCOUNT_END=?,RATE_VALUE=?,REMARKS=?,RELATION_TYPE=? WHERE DISCOUNT_ID=?";
		
		 cols=new String[6];
		 cols[0]=discount_start;
		 cols[1]=discount_end;
		 cols[2]=rate_value;
		 cols[3]=remarks;
		 cols[4]=relation_type;
		 cols[5]=discount_id;
		 result=runner.multipleUpdation(sql, cols);
		}
		else{*/
		
			sql="INSERT INTO TRAVEL_RELATION_DISCOUNT(DISCOUNT_ID,DISCOUNT_START,DISCOUNT_END,RATE_VALUE,RELATION_TYPE,REMARKS,PRODUCT_CODE,AGENCY_CODE,BRANCH_CODE,AMEND_ID,EFF_DATE) VALUES((SELECT nvl(MAX(DISCOUNT_ID),0)+1 FROM TRAVEL_RELATION_DISCOUNT),?,?,?,?,?,?,'0',?,(select nvl(MAX(amend_id),'-1')+1 from TRAVEL_RELATION_DISCOUNT where DISCOUNT_START=? and DISCOUNT_END=? and RELATION_TYPE=? and PRODUCT_CODE=? and BRANCH_CODE=?),convert(datetime,?,'dd/mm/yyyy'))";
			cols=new String[13];
			 cols[0]=discount_start;
			 cols[1]=discount_end;
			 cols[2]=rate_value;
			 cols[3]=relation_type;
			 cols[4]=remarks;
			 cols[5]=productId;
			 cols[6]=branchCode;
			 cols[7]=discount_start;
			 cols[8]=discount_end;
			 cols[9]=relation_type;
			 cols[10]=productId;
			 cols[11]=branchCode;
			 cols[12]=effDate;
			 System.out.println("Sql=>"+sql);
			 System.out.println("obj[]=>"+StringUtils.join(cols,","));
			 System.out.println("Result=>"+runner.multipleUpdation(sql, cols));
		//}
		 
		 
	}catch(Exception e){
	logger.fatal(e);
	
		} return result;	
	
}
public String insertTravelRateEdit(String rate_id, String mode,String branchCode,String productCode,String effDate){
	String result=null;
	String cols[];
	String sql="";
	try{
		
	/*if("Edit".equalsIgnoreCase(mode))
	{
	sql="UPDATE TRAVEL_DISCOUNT_RATE SET TYPE=?,RATE_VALUE=?,AGE_START=?,AGE_END=? WHERE RATE_ID=?";
	 cols=new String[5];
	 
	 cols[0]=type;
	 cols[1]=rate_value;
	 cols[2]=age_start;
	 cols[3]=age_end;
	 cols[4]=rate_id;
	
	 result=runner.multipleUpdation(sql, cols);
	}else{*/
	 sql="INSERT INTO TRAVEL_DISCOUNT_RATE(RATE_ID,AGE_START,AGE_END,TYPE,RATE_VALUE,PRODUCT_CODE,AMEND_ID,BRANCH_CODE,EFF_DATE)VALUES((SELECT nvl(MAX(RATE_ID),0)+1 FROM TRAVEL_DISCOUNT_RATE WHERE BRANCH_CODE=? AND PRODUCT_CODE=?),?,?,?,?,?,(select nvl(max(amend_id),'-1')+1 FROM TRAVEL_DISCOUNT_RATE WHERE TYPE=? AND AGE_START=? AND AGE_END=? AND BRANCH_CODE=? AND PRODUCT_CODE=?),?,CONVERT(DATETIME,?,'dd/mm/yyyy'))";
	 cols=new String[14];
	 
	 cols[0]=branchCode;
	 cols[1]=productCode;
	 cols[2]=age_start;
	 cols[3]=age_end;
	 cols[4]=type;
	 cols[5]=rate_value;
	 cols[6]=productCode;
	 cols[7]=type;
	 cols[8]=age_start;
	 cols[9]=age_end;
	 cols[10]=branchCode;
	 cols[11]=productCode;
	 cols[12]=branchCode;
	 cols[13]=effDate;
	 System.out.println("Sql=>"+sql);
	 System.out.println("obj[]=>"+StringUtils.join(cols,","));
   	 System.out.println("Result=>"+runner.multipleUpdation(sql, cols));
	//}
		
	}catch(Exception e){
logger.fatal(e);

} return result;	
}

	public String insertTravelSchemeEdit( String branchcode, String scheme_id, String mode){
			logger.info("************it come to insertTravelSchemeEdit method****************");
			String result=null;
			String cols[];
			String sql="";
			
			System.out.println("Session Info Start: "+getSessionVariables("BlockDuplicateSubmit"));
			
			if(!"".equalsIgnoreCase(getSessionVariables("BlockDuplicateSubmit")))
			{
				
			 try{
				 if(!EDIT.equalsIgnoreCase(mode)) {
				 
				 sql="INSERT INTO TRAVEL_SCHEME_COVERS(SCHEME_ID,SCHEME_NAME,STATUS,COREAPP_CODE,PRODUCT_CODE,BRANCH_CODE) VALUES ((SELECT nvl(MAX(SCHEME_ID)+1,0) FROM TRAVEL_SCHEME_COVERS),?,?,?,?,?)";
				 System.out.println("Query==>"+sql);
				 cols=new String[5];
				 cols[0]=getScheme_name();
				 cols[1]=getStatus();
				 cols[2]=getCoreapp_code();
				 cols[3]=getProductId();
				 cols[4]=branchcode;
				 
		    	 result=runner.multipleInsertion(sql,cols);
			
				 }
				 else{
					 sql="UPDATE TRAVEL_SCHEME_COVERS SET SCHEME_NAME=?,STATUS=?,COREAPP_CODE=? WHERE PRODUCT_CODE=? AND BRANCH_CODE=? AND SCHEME_ID=?";
					 System.out.println("Query==>"+sql);
					 cols=new String[6];
					 cols[0]=getScheme_name();
					 cols[1]=getStatus();
					 cols[2]=getCoreapp_code();
					 cols[3]=getProductId();
					 cols[4]=branchcode;
					 cols[5]=scheme_id;
					 result=runner.multipleUpdation(sql, cols);
				 }
				
		     	}catch(Exception e){
				 logger.fatal(e);
				
		    }
				
		     	removeSessionInfo("BlockDuplicateSubmit");
		}
			
			System.out.println("Session Info End: "+getSessionVariables("BlockDuplicateSubmit"));
			
			return result;
}
	
	public String insertTravelOptionEdit( String branchcode, String option_id, String mode){
		    logger.info("************it come to insertTravelOptionEdit method****************"+mode);
			 String result=null;
			
			String cols[];
			String sql="";
			
			 try{
				 if(!EDIT.equalsIgnoreCase(mode)) {
				 
				 
				 sql="INSERT INTO TRAVEL_OPTION_COVERS(OPTION_ID,OPTION_NAME,STATUS,COREAPP_CODE,Branch_code) VALUES ((SELECT nvl(MAX(OPTION_ID)+1,0) FROM TRAVEL_OPTION_COVERS),?,?,?,?)";
				 System.out.println("Query==>"+sql);
				 cols=new String[4];
			
				
				 cols[0]=getOption_name();
				 cols[1]=getStatus();
				 cols[2]=getCoreapp_code();
				 cols[3]=branchcode;
		    
				 result=runner.multipleInsertion(sql,cols);
				
				
				
				 }
				 else{
					 sql="UPDATE TRAVEL_OPTION_COVERS SET OPTION_NAME=?,STATUS=?,COREAPP_CODE=? WHERE OPTION_ID=?";
					 System.out.println("Query==>"+sql);
					 cols=new String[4];
					 
					 cols[0]=getOption_name();
					 cols[1]=getStatus();
					 cols[2]=getCoreapp_code();
					 cols[3]=option_id;
					 
					 result=runner.multipleUpdation(sql, cols);
				 }
				    logger.info("Sql: "+sql);
				   	
		     }catch(Exception e){
				    logger.fatal(e);
				  
			 }
		     return result;	
	}
	    
	
	/*public List getTravelCoverages( String schemeId, String optionId, String branchCode){
		logger.info("now i am in getTravelCoverages method from DAO*********************88");
		String result[][]=new String[0][0];
		String query="SELECT ROWNUM,COVERAGES_ID,COVERAGES_NAME,COVERAGES_VALUE,CASE WHEN (STATUS='Y') THEN 'Active' ELSE 'InActive' END STATUS,COREAPP_CODE FROM TRAVEL_MAS_COVERAGES WHERE COVERAGES_ID IN( SELECT COVERAGES_ID FROM TRAVEL_LINK_COVERAGES WHERE COVER_ID IN ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND BRANCH_CODE=? AND STATUS='Y' ) ) AND BRANCH_CODE=? AND STATUS='Y'";
		
		String args[]=new String[4];
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=branchCode;
		args[3]=branchCode;
		
		result=runner.multipleSelection(query, args);
		
		if(result.length>0){
			
			    logger.info("now i am in if condition of result in getTravelCoverages()************************88");
			   
			    for(int i=0;i<result.length;i++){
				logger.info("now i am in for loop of retriveing and storing to object*********************88");
				 TravelConfigDAO tDAO=new TravelConfigDAO();
				tDAO.setRownum(result[i][0]);
				tDAO.setCoverages_id(result[i][1]);
				tDAO.setCoverages_name(result[i][2]);
				tDAO.setCoverages_value(result[i][3]);
				tDAO.setStatus(result[i][4]);
				tDAO.setCoreapp_code(result[i][5]);
				display.add(tDAO);
			}
		}
		return display;
	}*/
	    
	public String[][] getTravelCoveragesMasterEdit( String branchCode, String coverages_id){
			logger.info("*************now i am in getTravelCoveragesMasterEdit method*******************");
			String result[][]=new String[0][0];
			try{
			
			final String sql="select coverages_id,coverages_name,coverages_value,status,coreapp_code from TRAVEL_MAS_COVERAGES where coverages_id=? and branch_code=?";
			System.out.println("Query==>"+sql);
			String cols[]=new String[2];
			cols[0]=coverages_id;
			cols[1]=branchCode;
			result=runner.multipleSelection(sql,cols);
			}
			catch(Exception e){
				logger.fatal(e);
			}
			return result;
			}
	   
	public String insertTravelMasterCoveragesEdit( String branchcode, String coverages_id, String mode){
				logger.info("************it come to insertTravelSchemeEdit method****************");
				String result=null;
				String cols[];
				String sql="";
				try{
					 if(!EDIT.equalsIgnoreCase(mode)) {
					 
					 sql="INSERT INTO TRAVEL_MAS_COVERAGES(COVERAGES_ID,COVERAGES_NAME,COVERAGES_VALUE,STATUS,COREAPP_CODE,BRANCH_CODE) VALUES ((SELECT nvl(MAX(COVERAGES_ID)+1,0) FROM TRAVEL_MAS_COVERAGES),?,?,?,?,?)";
					 System.out.println("Query==>"+sql);
					 cols=new String[5];
					 cols[0]=getCoverages_name();
					 cols[1]=getCoverages_value();
					 cols[2]=getStatus();
					 cols[3]=getCoreapp_code();
					 cols[4]=branchcode;
			    	 result=runner.multipleInsertion(sql,cols);
				
					 }
					 else{
						 sql="UPDATE TRAVEL_MAS_COVERAGES SET COVERAGES_NAME=?,COVERAGES_VALUE=?,STATUS=?,COREAPP_CODE=? WHERE COVERAGES_ID=? and branch_code=?";
						 System.out.println("Query==>"+sql);
						 cols=new String[6];
						 cols[0]=getCoverages_name();
						 cols[1]=getCoverages_value();
						 cols[2]=getStatus();
						 cols[3]=getCoreapp_code();
						 cols[4]=coverages_id;
						 cols[5]=branchcode;
						 result=runner.multipleUpdation(sql, cols);
					 }
					
					
			     	}catch(Exception e){
					 logger.fatal(e);
				
				 }
			     	 return result;	
		}
	
	

	public int getAlreadyExistCount( String schemeId, String optionId, String coverageId, String branchCode,String productCode)
	{
		int returnValue=0;
		 String query="SELECT count(*) from travel_link_coverages where cover_id=(Select cover_id from travel_scheme_option_covers where scheme_id=? and option_id=?  and product_code=? and branch_code=? ) and coverages_id=? and product_code=? and branch_code=?";
		 System.out.println("Query==>"+query);
		 String args[]=new String[7];
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=productCode;
		args[3]=branchCode;
		args[4]=coverageId;
		args[5]=productCode;
		args[6]=branchCode;
		try{
			returnValue=Integer.parseInt(runner.singleSelection(query,args));
		}catch(Exception e)
		{
			logger.info("Exception In getAlreadyExistCount(): "+e);
		}
			
		logger.info("getAlreadyExistCount: "+returnValue);
		
		return returnValue;
	}
	public int getAlreadyOptionLinkCount( String schemeId, String optionId, String branchCode,String productCode){
		logger.info("now i am in getAlreadyOptionLinkCount");
		int value=0;
		String query="select count(*) from TRAVEL_SCHEME_OPTION_COVERS where scheme_id=? and option_id=? and product_code=?";
		 System.out.println("Query==>"+query);
		String args[]=new String[3];
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=productCode;
		
		try{
			value=Integer.parseInt(runner.singleSelection(query,args));
		}catch(Exception e)
		{
			logger.info("Exception In getAlreadyOptionLinkCount(): "+e);
		}
		logger.info("getAlreadyExistCount: "+value);
		return value;
	}
	
	public void insertTravelLinkCoverages(String coverageId, String schemeId, String optionId,String rate,String branchCode,String productCode,String effDate)
	{
 //	String query="INSERT INTO TRAVEL_LINK_COVERAGES (SNO,COVERAGES_ID,COVER_ID,RATE,STATUS,PRODUCT_CODE) VALUES ((SELECT nvl(MAX(SNO)+1,0) FROM TRAVEL_LINK_COVERAGES),?,( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? ),?,'Y',?) ";
		String query="INSERT INTO TRAVEL_LINK_COVERAGES (SNO,COVERAGES_ID,COVER_ID,RATE,STATUS,PRODUCT_CODE,BRANCH_CODE,eff_date,amend_id) VALUES ((SELECT nvl(MAX(SNO)+1,0) FROM TRAVEL_LINK_COVERAGES),?,(SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? and BRANCH_CODE=?),?,'Y',?,?,CONVERT(datetime,?,'dd/mm/yyyy'),(select nvl(MAX(amend_id),'-1')+1 from TRAVEL_LINK_COVERAGES where COVERAGES_ID=? and COVER_ID=((SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? and BRANCH_CODE=?)) and PRODUCT_CODE=? and BRANCH_CODE=?))";
		 System.out.println("Query==>"+query);
		String args[]=new String[16];
		args[0]=coverageId;
		args[1]=schemeId;
		args[2]=optionId;
		args[3]=productCode;
		args[4]=branchCode;
		args[5]=rate;
		args[6]=productCode;
		args[7]=branchCode;
		args[8]=effDate;
		args[9]=coverageId;
		args[10]=schemeId;
		args[11]=optionId;
		args[12]=productCode;
		args[13]=branchCode;
		args[14]=productCode;
		args[15]=branchCode;
		 System.out.println("Sql=>"+query);
		 System.out.println("obj[]=>"+StringUtils.join(args,","));
	   	 System.out.println("Result=>"+runner.multipleUpdation(query, args));
	}
	public void insertOptionLink(String schemeId, String optionId, String  branchCode,String productCode){
		logger.info("now i am in  insertOptionLink");
		 String query="INSERT INTO TRAVEL_SCHEME_OPTION_COVERS (COVER_ID,SCHEME_ID,OPTION_ID,STATUS,PRODUCT_CODE,BRANCH_CODE) VALUES ((SELECT nvl(MAX(COVER_ID)+1,0) FROM TRAVEL_SCHEME_OPTION_COVERS),?,?,'Y',?,?)";
		String args[]=new String[4];
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=productCode;
		args[3]=branchCode;
		runner.multipleInsertion(query, args);
	}
	public void updateTravelLinkCoverages( String coverageId, String schemeId, String optionId, String rate, String branchCode,String status,String productCode)
	{
		 String query="UPDATE TRAVEL_LINK_COVERAGES SET RATE=?,STATUS=? WHERE COVERAGES_ID=? AND COVER_ID= ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? ) AND PRODUCT_CODE=?";
		 System.out.println("Query==>"+query);
		 String args[]=new String[7];
		args[0]=rate;
		args[1]=status;
		args[2]=coverageId;
		args[3]=schemeId;
		args[4]=optionId;
		args[5]=productCode;
		args[6]=productCode;
		
		runner.multipleUpdation(query, args);
	}
	public void updateOptionLink( String schemeId, String  optionId, String branchCode,String productCode){
		logger.info("now i am in updateOptionLink");
		 String query="UPDATE TRAVEL_SCHEME_OPTION_COVERS SET STATUS='Y' WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? AND BRANCH_ID=?";
		 System.out.println("Query==>"+query);
		 String args[]=new String[4];
		
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=productCode;
		args[3]=branchCode;
		
	runner.multipleUpdation(query, args);	
	}
	
	
	public void deleteTravelLinkCoverages( String coverageId, String schemeId, String optionId, String branchCode,String productCode)
	{
		logger.info("now i am in deleteTravelLinkCoverages ");
		String query="DELETE FROM TRAVEL_LINK_COVERAGES WHERE COVERAGES_ID=? AND COVER_ID= ( SELECT COVER_ID FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=? and branch_code=?) AND PRODUCT_CODE=? and branch_code=?";
		System.out.println("Query==>"+query);
		String args[]=new String[7];
		args[0]=coverageId;
		args[1]=schemeId;
		args[2]=optionId;
		args[3]=productCode;
		args[4]=branchCode;
		args[5]=productCode;
		args[6]=branchCode;
		
		runner.multipleUpdation(query, args);
	}
	public void deleteOptionLink( String  schemeId, String optionId, String branchCode,String productCode){
		logger.info("now i am in deleteOptionLink ");
	 	String query="DELETE FROM TRAVEL_SCHEME_OPTION_COVERS WHERE SCHEME_ID=? AND OPTION_ID=? AND PRODUCT_CODE=?";
	 	System.out.println("Query==>"+query);
	 	String args[]=new String[3];
		args[0]=schemeId;
		args[1]=optionId;
		args[2]=productCode;
		
		runner.multipleUpdation(query, args);
	}
	public String getTravel_premium_id() {
		return travel_premium_id;
	}
	public void setTravel_premium_id(String travel_premium_id) {
		this.travel_premium_id = travel_premium_id;
	}
	public HttpSession getSessionInfo() {
		return sessionInfo;
	}
	public void setSessionInfo(HttpSession sessionInfo) {
		this.sessionInfo = sessionInfo;
	}
	
	private String getSessionVariables(final String param)
	{
		String result="";
		HttpSession session=getSessionInfo();
		result=session.getAttribute(param)==null?"":(String)session.getAttribute(param);
		return result;
	}
	
	private void removeSessionInfo(final String param)
	{
		HttpSession session=getSessionInfo();
		session.removeAttribute(param);
	}
	
	public String[][] getTravelProducts(String branchCode)
	{   
		final String[][] result;
		final String query="select product_id,product_name from PRODUCT_MASTER where branch_Code=? and product_category='T' and status='Y' order by display_order asc";
		 System.out.println("Query==>"+query);
		String args[]=new String[1];
		args[0]=branchCode;
		logger.info("BranchCode===>"+branchCode);
		result=runner.multipleSelection(query,args);
		return result;
	}
	
}
