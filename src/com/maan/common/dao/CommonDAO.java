package com.maan.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.exception.BaseException;


public class CommonDAO extends MyJdbcTemplate 
{
	private static final Logger logger = LogUtil.getLogger(CommonDAO.class);
	public String sql="";

	public List getTypeInfo(String typeId,String campaignId, String partnerId)
	{
		logger.info("getTypeInfo - Enter");
		List list=null;

		try {
			sql = "SELECT DESTINATION_TABLE FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID='1' and product_id=? and campaign_id= ? and partner_id=? and status='Y'";
			list=this.mytemplate.queryForList(sql, new String[]{typeId,campaignId,partnerId});
		} catch (Exception e) {

			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getTypeInfo - Exit || Result: "+list.size());
		
		return list;
	}



	public String insertTransactionDetails(String loginId, String fileName, String folderPath, String movementDetailId, String tranId2,String openCoverNo, String transactionDesc, String issuer) 
	{
		String resultTranId = "";
		int count = this.mytemplate.queryForObject("select count(*) from XL_TRANSACTION_DETAILS where transaction_id ='"+tranId2+"'", Integer.class);
		if(count==0){
			//int tranId=this.mytemplate.queryForInt("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL");

			String args[]=new String[8];
			args[0] = tranId2+"";
			args[1] = loginId;
			args[2] = fileName;
			args[3] = folderPath;
			args[4] = movementDetailId;
			args[5] = openCoverNo;
			args[6] = transactionDesc;
			args[7] = issuer;
			sql="INSERT INTO XL_TRANSACTION_DETAILS (TRANSACTION_ID,TRANSACTION_DATE,USER_NAME,FILE_NAME,PATH,ACTIVE,MOVEMENT_DETAIL_ID,OPENCOVER_POLICY_NUMBER,TRANSACTION_DESCRIPTION,ISSUER) " +
			" VALUES(?,SYSDATE,?,?,?,'Y',?,?,?,?)";
			if(!tranId2.equalsIgnoreCase("")){
				int result=this.mytemplate.update(sql,args);
				System.out.println("insertTransactionDetails() - Exit || Result: "+result+" Tran Id: "+tranId2);

			}
			resultTranId=tranId2+"";
		}
		else
		{
			//sql= "delete from XL_OPENCOVER_RAW WHERE TRANSACTION_ID=?";
			//this.mytemplate.update(sql,new Object[]{tranId2});
			resultTranId=tranId2;
		}
		return resultTranId;
	}


	public List getMovements(String product, String partner, String campaign) {

		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID<>1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' AND MOVEMENT_TYPE='A' ORDER BY PRIORITY ASC";
		movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
		return movements;
	}
	public List getExcelMovement(String product, String partner, String campaign) {

		//logger.info("getExcelMovement");
		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID=1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' ORDER BY PRIORITY ASC";
		movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
		//logger.info("getExcelMovement query "+ sql);
		return movements;
	}
	/*public synchronized String getPolicyNo(String branchCode,String productId,String type) throws BaseException 
	{
		logger.info("getPolicyNo method() Enter");
		String seqNo = null;
		try{
			Object obj[] = new Object[]{branchCode, productId, branchCode, productId};
			String sql=getQuery("GET_SEQ_ID1")+" " +type+" "+getQuery("GET_SEQ_ID2");
			logger.info("Query=>"+sql);
			logger.info("obj[] ==> "+StringUtils.join(obj, ","));
			Map seqMap =this.mytemplate.queryForMap(sql, obj);
			String maxNo="";
			if(seqMap!=null && seqMap.size()>0)
			{
				sql=getQuery("GET_MAX_SEQ_ID1")+" "+seqMap.get("SEQ_NAME").toString()+getQuery("GET_MAX_SEQ_ID2");
				logger.info("Query=>"+sql);
				seqNo=(seqMap.get("POLICY_PREFIX")==null?"":seqMap.get("POLICY_PREFIX").toString())+((String)this.mytemplate.queryForObject(sql, String.class));
				logger.info("SeqNo=>"+seqNo);
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getPolicyNo method() Exit || Sequence NO=>"+seqNo);
		return seqNo;
	}*/
	public synchronized String getPolicyNo(String branchCode,String productId,String type) throws BaseException
	{
		logger.info("getPolicyNo method() Enter");
		String maxId = null;
		String preFix = null;
		try{
			Object obj[] = new Object[]{branchCode, productId, branchCode, productId};
			String sql=getQuery("GET_MAX_ID1")+" " +type+" "+getQuery("GET_MAX_ID2");
			logger.info("Query=>"+sql);
			logger.info("obj[] ==> "+StringUtils.join(obj, ","));
			Map polMap=this.mytemplate.queryForMap(sql, obj);
			preFix=(polMap.get("PREFIX")==null?"":polMap.get("PREFIX").toString()).trim();
			maxId =(polMap.get("MAXNO")==null?"":polMap.get("MAXNO").toString()).trim(); 
			logger.info("Result=>"+maxId);
			sql=getQuery("UPD_MAX_ID1")+" " +type+" "+getQuery("UPD_MAX_ID2");
			logger.info("Query=>"+sql);
			obj = new Object[]{maxId,maxId,branchCode, productId, branchCode, productId};
			logger.info("obj[] ==> "+StringUtils.join(obj, ","));
			int res=this.mytemplate.update(sql,obj);
			logger.info("Result=>"+res);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getPolicyNo method() Exit || maxId=>"+preFix+maxId);
		return preFix+maxId;
	}

	public synchronized String getMinimumPremium(String applicationNo) throws BaseException
	{
		logger.info("CommonDAO getMinimumPremium() Enter");
		String minPremium = null;
		try{
			sql=getQuery("GET_MINIUM_PREMIUM");
			Object[] obj=new Object[2];
			obj[0]=applicationNo;
			obj[1]=applicationNo;
			logger.info("Query=>"+sql);
			logger.info("Obj[0-2]=>"+StringUtils.join(obj,","));
			minPremium=(String)this.mytemplate.queryForObject(sql,obj,String.class);
			logger.info("Minimum Premium=>"+minPremium);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("CommonDAO getMinimumPremium() Exit || minPremium=>"+minPremium);
		return minPremium;
	}

	public synchronized double getPolicyFee(String applicationNo,String branchCode) throws BaseException
	{
		logger.info("CommonDAO getPolicyFee() Enter");
		double policyFee = 0.0;
		try{
			Object[] obj=new Object[2];
			obj[0]=applicationNo;
			obj[1]=branchCode;
			sql=getQuery("GET_POLICY_FEE");
			logger.info("Query=>"+sql);
			logger.info("Obj[0-1]=>"+StringUtils.join(obj,","));
			List li=this.mytemplate.queryForList(sql,obj);
			if(li!=null && li.size()>0)
			{
				Map map=(Map)li.get(0);
				policyFee=Double.parseDouble(map.get("POLICY_FEE").toString());
			}
			logger.info("policyFee=>"+policyFee);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("CommonDAO getPolicyFee() Exit || policyFee=>"+policyFee);
		return policyFee;
	}
	public String getCommision(final double premium,final String appNo)throws BaseException {
		logger.info("getCommision method() Enter||");
		double commision=0; 
		try{
			String sql=getQuery("GET_LOGIN_PROD_ID");
			logger.info("Query=>"+sql);
			logger.info("Obj[0]=>"+appNo);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{appNo});
			logger.info("Map Size=>"+map.size());
			sql=getQuery("GET_COMM");
			Object obj[] = {map.get("LOGIN_ID"),map.get("PROD_ID")};
			logger.info("Query=>"+sql);
			logger.info("Obj[0]=>"+map.get("LOGIN_ID"));
			logger.info("Obj[1]=>"+map.get("PROD_ID"));
			String comPer =(String)this.mytemplate.queryForObject(sql, obj,String.class);
			logger.info("Result=>"+comPer);
			if (0==Double.parseDouble(comPer)){
				commision = 0.0;
			}
			else{
				commision = premium * (Double.parseDouble(comPer) / 100.0);
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("getCommision method() Exit|| Commision=>"+commision);
		return commision+"";
	}

	public synchronized String getReferralYN(String loginId) throws BaseException
	{
		logger.info("getPolicyNo getReferralYN() Enter");
		String refYN = "";
		try{
			sql=getQuery("GET_REFERAL_YN");
			Object[] obj=new Object[1];
			obj[0]=loginId;
			logger.info("Query=>"+sql);
			logger.info("Obj[0]=>"+loginId);
			refYN=(String)this.mytemplate.queryForObject(sql, obj,String.class);
			logger.info("Result=>"+refYN);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("getPolicyNo getReferralYN() Exit || ReferralYN=>"+refYN);
		return refYN;
	}

	public List<Map<String, Object>> getOptionsList(String option, Object[] objects)
	{
		logger.info("getOptionsList - Enter || option: "+option);
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		try
		{
			sql=getQuery(DBConstants.OPTION);
			objects[0]=option;
			logger.info("sql==>"+sql);
			for (int i = 0; i < objects.length; i++) {
				if(objects[i]==null){
					objects[i]="";
				}
			}
			logger.info("args[] ==> "+StringUtils.join(objects, ","));
			list = this.mytemplate.queryForList(sql, objects);
		}catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("getOptionsList - Exit || Result: "+list.size());
		return list;	
	}
	public int getCalculatedAge(String date)
	{
		logger.info("getCalculatedAge - Enter || Date=>"+date);
		int age=0;
		try
		{
			String sql=getQuery("GET_AGE_CALC");
			logger.info("Obj[0-3]=>"+date);
			Map map=this.mytemplate.queryForMap(sql,new Object[]{date,date,date,date});
			age=Integer.parseInt(map.get("YEARS").toString());
			logger.info("Query=>"+sql);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("getCalculatedAge - Exit Age=>"+age);
		return age;
	}
	public String getBackDatesAllowed(final String user,final String userType,final String productId, final String branchCode ,final String schemeId)
	{
		logger.info("getBackDatesAllowed - Enter");
		String result = "0";
		if (userType != null)
		{
			String sql="";
			if ("RSAUSER".equalsIgnoreCase(userType)||"Admin".equalsIgnoreCase(userType)){
				sql = getQuery("GET_CONSTRANCT_DTLS");
				logger.info("Query=>"+sql);
				logger.info("Obj[0]=>152"+152);
				logger.info("Obj[1]=>"+branchCode);
				result = (String)this.mytemplate.queryForObject(sql,new Object[]{"152",branchCode}, String.class);
			}
			else {
				sql = getQuery("GET_BACK_DATE_ALLOWED");
				logger.info("Query=>"+sql);
				logger.info("Obj[0]=>"+user);
				logger.info("Obj[1]=>"+productId);
				if("30".equalsIgnoreCase(productId)){
					sql+=" and scheme_id=?";
					result =(String) this.mytemplate.queryForObject(sql, new Object[]{user,productId, schemeId},String.class);
				}else
					result =(String) this.mytemplate.queryForObject(sql, new Object[]{user,productId},String.class);
			}
		}
		if (result == null){
			result = "0";
		}
		logger.info("getBackDatesAllowed - Exit");
		return result;
	}
	public synchronized String getSumInsuredLimit(String appNo,String loginId,String productId) throws BaseException
	{
		logger.info("CommonDAO getSumInsuredLimit() Enter");
		String siLimit = null;
		Object[] obj=new Object[2];
		try{
			if(StringUtils.isBlank(appNo))
			{
				sql=getQuery("GET_SI_LIMIT");
				obj[0]=loginId;
				obj[1]=productId;
			}else
			{
				sql=getQuery("GET_SI_LIMIT_APPNO");
				obj[0]=appNo;
				obj[1]=appNo;
			}
			logger.info("Query=>"+sql);
			logger.info("Obj[0-2]=>"+StringUtils.join(obj,","));
			siLimit=(String)this.mytemplate.queryForObject(sql,obj,String.class);
			logger.info("SI Limit=>"+siLimit);
		}catch(Exception e)
		{
			logger.debug(e);
		}
		
		logger.info("CommonDAO getSumInsuredLimit() Exit || SI Limit=>"+siLimit);
		return siLimit;
	}
	public Map getTodaysDate()
	{
		logger.info("getTodaysDate - Enter");
		Map todayDate=null;
		try{
			final String sql = getQuery("GET_TODAY_DT");
			logger.info("Query=>"+sql);
			todayDate =this.mytemplate.queryForMap(sql);
			logger.info("Map Size=>"+todayDate.size());
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("getTodaysDate - Exit");
		return todayDate;
	}
	public void closeTrnDateCalculation(final String quoteNo,final String loginBranch,final String policy)throws BaseException{
		logger.info("closeTrnDateCalculation method() Enter");
		try{
			Date sysDates;
			Date sysDates1;
			Date closeDate;
			Date openDate;
			List closeDates;
			closeDates = getCloseDate(loginBranch,quoteNo,policy);
			sysDates = new Date();
			if(closeDates.size()>0){
				Map closeDatesMap=(Map)closeDates.get(0);
				sysDates1 = new Date(sysDates.getYear(), sysDates.getMonth(),sysDates.getDate());
				sysDates = sysDates1;
				closeDate = new Date(Integer.parseInt(closeDatesMap.get("CLOSE_YEARS").toString()) - 1900,Integer.parseInt(closeDatesMap.get("CLOSE_MONTHS").toString()) - 1, Integer
						.parseInt(closeDatesMap.get("CLOSE_DAYS").toString()));
				openDate = new Date(Integer.parseInt(closeDatesMap.get("OPEN_YEARS").toString()) - 1900,Integer.parseInt(closeDatesMap.get("OPEN_MONTHS").toString()) - 1, Integer
						.parseInt(closeDatesMap.get("OPEN_DAYS").toString()));
				if (!(sysDates.compareTo(openDate) >= 0&& sysDates.compareTo(closeDate) <= 0)) {
					String sql="";
					Object obj[] = new Object[3];
					obj[0] =closeDatesMap.get("OPEN_DAYS").toString()+ "/"+ (closeDatesMap.get("OPEN_MONTHS").toString()) + "/"+closeDatesMap.get("OPEN_YEARS").toString()+ " 01:01:01";
					obj[1] =closeDatesMap.get("OPEN_DAYS").toString()+ "/"+ (closeDatesMap.get("OPEN_MONTHS").toString()) + "/"+closeDatesMap.get("OPEN_YEARS").toString()+ " 01:01:01";

					if("HTOS".equalsIgnoreCase(policy)){
						sql=this.getQuery("UPD_HPMDATE_QUOTENO");
						obj[2] = quoteNo;
					}else{
						sql=this.getQuery("UPD_HPMDATE_POLICYNO");
						obj[2] = policy;
					}
					logger.info("sql=>"+sql);
					logger.info("obj[]=>"+StringUtils.join(obj,","));
					this.mytemplate.update(sql,obj);
				}
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("closeTrnDateCalculation method() Exit");
	}
	private List getCloseDate(final String loginBranch,final String quoteNo,final String policy)throws BaseException{
		logger.info("getCloseDate method() Enter");
		List closeDates=new ArrayList();
		try{
			String sql="";
			String pdtCoreCode = "";
			Object obj[] = new Object[2];
			obj[0] = quoteNo;
			obj[1] = loginBranch;
			sql=getQuery("GET_RSACODE");
			logger.info("Query=>"+sql);
			logger.info("obj[0]=>"+quoteNo);
			logger.info("obj[1]=>"+loginBranch);
			pdtCoreCode =(String)this.mytemplate.queryForObject(sql,obj,String.class);
			logger.info("Result=>"+pdtCoreCode);
			if("HTOS".equalsIgnoreCase(policy)){
				obj = new Object[3];
				obj[0] = loginBranch;
				obj[1] = pdtCoreCode;
				obj[2] = quoteNo;
				sql=getQuery("GET_CLOSE_TRNRSACODE");
				logger.info("Query=>"+sql);
				logger.info("Obj[0]=>"+StringUtils.join(obj,","));
				closeDates = this.mytemplate.queryForList(sql,obj);
			}else{
				obj = new Object[1];
				obj[0] = loginBranch;
				sql=getQuery("GET_CLOSE_TRN");
				logger.info("Query=>"+sql);
				logger.info("Obj[0]=>"+loginBranch);
				closeDates = this.mytemplate.queryForList(sql,obj);
			}
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("getCloseDate method() Exit List Size=>"+closeDates.size());
		return closeDates;
	}
	public String getBrokerLoginId(String brokerCode)
	{
		logger.info("getBrokerLoginId - Enter||");
		String result=null;
		String sql="";
		logger.info("getBrokerLoginId");
		try{	
			sql=getQuery("quotation.brokerloginId");
			logger.info("Sql=>" + sql+"\nArgs =>"+brokerCode);				
			result=(String)this.mytemplate.queryForObject(sql,String.class,new String[]{brokerCode});			
		}
		catch (Exception e) {			
			logger.debug(e);
		}		
		logger.info("getBrokerLoginId - Exit||"+ result);
				
		return result;
	}
	public List<Map<String, Object>> getCustomerSelectionList(String loginId,String searchBy,String searchValue,String openCoverNo)
	{
		logger.info("getCustomerSelectionList - Enter || "+loginId+","+searchValue);
		List<Map<String, Object>> customerList=new ArrayList<Map<String, Object>>();		
		searchValue=searchValue==null?"":searchValue;
		String sql="";
		try{
			if("".equalsIgnoreCase(searchValue)){
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery("customer.opencover");
					sql+=" ORDER BY PI.FIRST_NAME";
					logger.info("SQL=>" + sql);
					logger.info("Args[]=>"+openCoverNo);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo});
				}else{
					sql=getQuery("travel.customer.selection");
					sql+=" ORDER BY PI.FIRST_NAME";
					logger.info("SQL=>" + sql);
					logger.info("Args[]=>"+loginId);
					customerList=this.mytemplate.queryForList(sql,new String[]{loginId});}
			}else{
				if(StringUtils.isNotEmpty(openCoverNo) && !"0".equals(openCoverNo)){
					sql=getQuery("customer.opencover.byname");
					logger.info("SQL=>" + sql);
					logger.info("Args[]=>"+openCoverNo+","+searchValue);
					customerList=this.mytemplate.queryForList(sql,new String[]{openCoverNo,"%"+searchValue+"%"});
				}else{
					Object[] obj=new Object[]{loginId};
					sql=getQuery("customer.selection.common");
					if("custName".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.byname");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("coreAppCode".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.coreAppCode");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("mobileNo".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.mobileNo");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}else if("email".equalsIgnoreCase(searchBy)){
						sql+=" "+getQuery("customer.selection.email");
						obj=new Object[]{loginId,"%"+searchValue+"%"};
					}
					logger.info("SQL=>" + sql);
					logger.info("Obj[]=>"+StringUtils.join(obj,","));
					customerList=this.mytemplate.queryForList(sql,obj);
				}
			}	
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getCustomerSelectionList() - Exit Result=>"+ customerList.size() );
				
		return customerList;
	}
	public String getSingleInfo(String option, String[] args)
	{
		logger.info("getSingleInfo - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		String result="";					
		try{
			sql=getQuery(option);
			result=(String)this.mytemplate.queryForObject(sql,args, String.class);			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("Query==>" + sql);
		logger.info("getSingleInfo() - Exit || Result: "+result );
				
		return result;
	}
	
	public String getProductIdByAppNo(String applicationNo) {
		String productId = "";
		try {
			String query = "SELECT PRODUCT_ID FROM position_master PM WHERE APPLICATION_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM POSITION_MASTER PM1 WHERE PM1.APPLICATION_NO=PM.APPLICATION_NO)";
			productId = (String) this.mytemplate.queryForObject(query, new Object[]{applicationNo}, String.class);
		}
		catch(Exception exception) {
			logger.debug(exception);
		}
		return productId;
	}
	
	public String getUserMail(String loginId) {
		String userMail = "";
		try {
			String query = "SELECT USER_MAIL FROM LOGIN_MASTER WHERE LOGIN_ID=?";
			userMail = (String) this.mytemplate.queryForObject(query, new Object[]{loginId}, String.class);
		}
		catch(Exception exception) {
			logger.debug("Exception @ { " + exception + " } ");
		}
		return userMail;
	}
	


}


