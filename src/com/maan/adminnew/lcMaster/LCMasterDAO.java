package com.maan.adminnew.lcMaster;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class LCMasterDAO extends MyJdbcTemplate
{
	private static final Logger logger = LogUtil.getLogger(LCMasterDAO.class);
	private String query="";
	private Object[] obj=null;
	
	public List<Map<String, Object>> getLcList(String branchCode){
    	List <Map<String, Object>> lcList=null;
    	obj=new Object[]{branchCode, branchCode};
    	try{
    		query=getQuery("GET_LCNUMBER_OC_COUNT");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			lcList=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return lcList;
    }
	
	public List<Map<String, Object>>getLcDetail(String broker, String branchCode){
		List <Map<String, Object>> lcDetail=null;
    	obj=new Object[]{broker, branchCode};
    	try{
    		query=getQuery("GET_LCDETAILS_LIST");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			lcDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return lcDetail;
	}
	
	public List<Map<String, Object>>getOcDetail(String broker, String branchCode){
		List <Map<String, Object>> ocDetail=null;
    	obj=new Object[]{broker, branchCode};
    	try{
    		query=getQuery("GET_OCDETAILS_LIST");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return ocDetail;
	}
	
	public List<Map<String, Object>>getLCOCDetail(String branchCode, final LCMasterBean bean, String from){
		List <Map<String, Object>> ocDetail=null;
    	try{
    		query=getQuery("GET_LCOC_DETAILS_LIST");
    		if("add".equals(from)){
    			query+=" and LC_number=? and LC_ID=?";
    			obj=new Object[]{branchCode, branchCode, branchCode, bean.getOpenCover(), bean.getLcNum(), bean.getLcId()};
    		}
    		else { 
    			obj=new Object[]{branchCode, branchCode, branchCode, bean.getOpenCover()};
    		}
    		logger.info("Query===>" + query);
			logger.info("Args==> " + StringUtils.join(obj,","));
			ocDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return ocDetail;
	}
	public List<Map<String, Object>> getSaleList(String branchCode){
		List <Map<String, Object>> ocDetail=null;
    	obj=new Object[]{branchCode};
    	try{
    		query=getQuery("GET_SALE_LIST");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return ocDetail;
	}
	public List<Map<String, Object>> getCurrencyList(String branchCode){
		List <Map<String, Object>> ocDetail=null;
    	obj=new Object[]{branchCode};
    	try{
    		query=getQuery("GET_CURRENCY_LIST");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return ocDetail;
	}
	public List<Map<String, Object>> getBankList(String branchCode){
		List <Map<String, Object>> ocDetail=null;
    	obj=new Object[]{branchCode, branchCode};
    	try{
    		query=getQuery("GET_Bank_List");
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			ocDetail=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
    	return ocDetail;
	}
	public int getLCSave(final LCMasterBean bean, String branchCode){
		int saveId=0;
		try{
			Double lcAmtSale = Double.parseDouble(bean.getLcAmt())+(Double.parseDouble(bean.getLcAmt()) * ((double)(getSaletermValue(bean.getSale(), branchCode))/100));
			query=getQuery("GET_COUNTRY_ID");
			logger.info("Query===>" + query);
			obj=new Object[]{branchCode};
			for(Object a:obj)
				logger.info("args===>" + a);
			int countryId=this.mytemplate.queryForObject(query,Integer.class,new Object[]{branchCode});
			
			Double excValue=Double.parseDouble(getExchangeValue(bean.getLcCurrency(), countryId));
			
			double balance = lcAmtSale * excValue;
			
			query=getQuery("INS_LCNEW_VALUE");
			obj=new Object[]{bean.getLcNum(), lcAmtSale, bean.getBank(), "0", bean.getStartDate(), bean.getExpDate(),"Normal", bean.getLcNumYN()==null?"Y":bean.getLcNumYN(), bean.getOpenCover(), bean.getOpenCover(), bean.getLcCurrency(), balance, Double.parseDouble(bean.getLcAmt()),  bean.getSale()};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			saveId=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return saveId;
	}
	
	public int getLCUpdate(final LCMasterBean bean, String branchCode){
		int saveId=0;
		try{
			Double lcAmtSale = Double.parseDouble(bean.getLcAmt())+(Double.parseDouble(bean.getLcAmt()) * ((double)(getSaletermValue(bean.getSale(), branchCode))/100));
			query=getQuery("GET_COUNTRY_ID");
			logger.info("Query===>" + query);
			obj=new Object[]{branchCode};
			logger.info("Args "+StringUtils.join(obj,","));
			int countryId=this.mytemplate.queryForObject(query,Integer.class,obj);
			
			Double excValue=Double.parseDouble(getExchangeValue(bean.getLcCurrency(), countryId));
			
			double balance =(lcAmtSale * excValue)-Double.parseDouble(StringUtils.isBlank(bean.getUsedAmt())?"0":bean.getUsedAmt());
			query=getQuery("UPD_LCNEW_VALUE");
			obj=new Object[]{lcAmtSale, bean.getBank(), "0", bean.getStartDate(), bean.getExpDate(),bean.getLcCurrency(), "Y", balance, Double.parseDouble(bean.getLcAmt()),  bean.getSale(), bean.getOpenCover(), bean.getLcNum(), bean.getLcId()};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			saveId=this.mytemplate.update(query,obj);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return saveId;
	}
	
	public int getSaletermValue(String sale, String branchCode){
		int saleTerm=0;
		try{
			query=getQuery("GET_SALETERM_VALUE");
			Object obj[]=new Object[]{sale, branchCode};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			saleTerm=this.mytemplate.queryForObject(query,Integer.class,obj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return saleTerm;
	}
	public String getExchangeValue(String currencyID, int countryID){
		String exchangeVal="";
		try{
			query=getQuery("GET_EXCHANGE_RATE");
			obj=new Object[]{currencyID, countryID, currencyID, countryID};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			exchangeVal=this.mytemplate.queryForObject(query,obj, String.class).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return exchangeVal;
	}
	public void getlcInfo(final LCMasterBean bean, String branchCode){
		List <Map<String, Object>> lcInfo=null;
    	try{
    		query=getQuery("GET_LCINFO_LIST");
    		obj=new Object[]{bean.getOpenCover(), bean.getLcId(), bean.getLcNum()};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			lcInfo=this.mytemplate.queryForList(query,obj);
			if(lcInfo!=null && lcInfo.size()>0){
				Map map=(Map)lcInfo.get(0);
				bean.setLcNum(map.get("lc_number")==null?"":map.get("lc_number").toString());
				bean.setBank(map.get("bank_id")==null?"":map.get("bank_id").toString());
				bean.setStartDate(map.get("LC_DATE")==null?"":map.get("LC_DATE").toString());
				bean.setExpDate(map.get("Expiry_DATE")==null?"":map.get("Expiry_DATE").toString());
				bean.setLcId(map.get("lc_id")==null?"":map.get("lc_id").toString());
				bean.setLcCurrency(map.get("lc_currency_id")==null?"":map.get("lc_currency_id").toString());
				bean.setSale(map.get("LC_SALE_TERM_ID")==null?"":map.get("LC_SALE_TERM_ID").toString());
				bean.setLcNumYN(map.get("status")==null?"":map.get("status").toString());
				//bean.setLcAmt(map.get("LC_AMOUNT")==null?"":map.get("LC_AMOUNT").toString());
				bean.setLcAmt(map.get("LC_ACTUAL_AMOUNT")==null?"":map.get("LC_ACTUAL_AMOUNT").toString());
			}
		}catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	public void lcDelete(final LCMasterBean bean){
		try{
			query=getQuery("DEL_LC_NUMBER");
			obj=new Object[]{bean.getOpenCover(), bean.getLcNum(), bean.getLcId()};
			logger.info("Query===>" + query);
			for(Object a:obj)
				logger.info("args===>" + a);
			this.mytemplate.update(query,obj);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<Map<String, Object>> getLcAmtDetails(LCMasterBean bean) {
		List<Map<String, Object>> result=null;
		try{
			logger.info("Enter in getLcAmtDetails()");
			query="select pm.policy_no policy_No,nvl(per.first_name,per.company_name) NAME,to_char(pm.inception_date,'dd-mm-yyyy') Policy_Issue_Date,to_char(md.inception_Date,'dd-mm-yyyy') Policy_Start_Date,round((md.total_sum_insured*md.exchange_rate)+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES,2) SUM_INSURED,round(pm.premium+pm.excess_premium,2) TOTAL_AMT from marine_data md,position_master pm,personal_info per where md.application_no=pm.application_no and md.open_cover_no=pm.MISSIPPI_OPENCOVER_NO and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=? and per.customer_id = pm.customer_id order by substr(pm.policy_no,9,16) desc"; 
			obj=new Object[]{
					bean.getLcId(),bean.getBank(),bean.getOpenCover()
			};
			result=this.mytemplate.queryForList(query, obj);
			logger.info("Exit From getLcAmtDetails()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in getLcAmtDetails()");
		}
		return result;
	}

	public String getLcAmt(LCMasterBean bean) {
		 
		return null;
	}
}
