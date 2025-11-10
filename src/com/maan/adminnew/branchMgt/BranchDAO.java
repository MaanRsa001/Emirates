package com.maan.adminnew.branchMgt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;


public class BranchDAO extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(BranchDAO.class);
	public List<Map<String, Object>> getCountries() {
		List<Map<String, Object>> countryList=null;
		try{
			logger.info("Enter to The getCountries()");
			String sql=getQuery("BR_GET_COUNTRIES");
			countryList = this.mytemplate.queryForList(sql);		    	
			logger.info("Exit The getCountries()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in  The getCountries()");

		}
		return countryList;
	}
	public List<Map<String, Object>> getCurrencies() {
		List<Map<String, Object>> currencyList=null;
		try{
			logger.info("Enter to The getCurrencies()");
			String sql=getQuery("BR_GET_CURRENCIES");
			currencyList = this.mytemplate.queryForList(sql);		    	
			logger.info("Exit The getCountries()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in  The getCurrencies()");

		}
		return currencyList;
	}
	public List<Map<String, Object>> getCities(String countryAjax) {
		List<Map<String, Object>> cityList=null;
		try{
			logger.info("Enter to The getCities()");
			String sql=getQuery("BR_GET_CITIES");
			cityList = this.mytemplate.queryForList(sql,new Object[]{countryAjax});		    	
			logger.info("Exit The getCountries()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in  The getCities()");
		}
		return cityList;
	}
	public boolean saveBranch(BranchBean bean) {
		boolean result=false;
		try{
			logger.info("Enter to The saveBranch()");
			String sql=getQuery("BR_SAVE_BRANCH");
			Object []args={bean.getBranchName(),
					bean.getStatus(),bean.getAddress1()
					,bean.getAddress2(),bean.getAddress3(),
					bean.getCity(),bean.getCountry(),
					bean.getPhone(),bean.getFax(),bean.getEmail(),"0",
					bean.getEffectiveDate(),bean.getRemarks(),
					bean.getHeaderName(),bean.getFooterName(),
					bean.getSignImageName(),bean.getStampImageName(),
					bean.getCurrency(),bean.getCurrency(),bean.getCurrency(),bean.getOriginCountry(),
					bean.getDestiCountry(),bean.getCurrency(),"",
					bean.getDecimalPlaces(),bean.getTax(),
					bean.getBranchPrefix(),bean.getLang(),bean.getBelongingBranch()					
			};
			logger.info("Query:: "+sql);
			logger.info("Args:: "+StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
			result=true;
			logger.info("Exit to The saveBranch()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception in The saveBranch()");
		}
		return result;
	}
	public void loadLost(BranchBean bean) {
		logger.info("Enter into the loadLost()");
		try{
			String sql=getQuery("BR_LOAD_LIST");
			logger.info("Query >>"+sql);
			bean.setBranchList(this.mytemplate.queryForList(sql));

		}catch (Exception e) {
			logger.info("Execption into the loadLost()"+e);	 
		}
		logger.info("Exit into the loadLost()");

	}
	public void getSelectedBroker(BranchBean bean) {
		logger.info("Enter into the getSelectedBroker()");
		try{
			String sql=getQuery("BR_GET_SEL_BROKER");
			Object []args={bean.getBranchCode()};
			logger.info("Query::: "+sql);
			logger.info("Args:"+StringUtils.join(args,","));
			Map<String, Object> record = this.mytemplate.queryForMap(sql, args);
			bean.setBranchCode(record.get("BRANCH_CODE")==null?"":record.get("BRANCH_CODE").toString());
			bean.setBranchName(record.get("BRANCH_NAME")==null?"":record.get("BRANCH_NAME").toString());
			bean.setStatus(record.get("STATUS")==null?"":record.get("STATUS").toString());
			bean.setAddress1(record.get("ADDRESS1")==null?"":record.get("ADDRESS1").toString());
			bean.setAddress2(record.get("ADDRESS2")==null?"":record.get("ADDRESS2").toString());
			bean.setAddress3(record.get("ADDRESS3")==null?"":record.get("ADDRESS3").toString());
			bean.setCountry(record.get("COUNTRY")==null?"":record.get("COUNTRY").toString());
			bean.setCityList(getCities(bean.getCountry()));
			bean.setCity(record.get("CITY")==null?"":record.get("CITY").toString());
			bean.setPhone(record.get("PHONE")==null?"":record.get("PHONE").toString());
			bean.setFax(record.get("FAX")==null?"":String.valueOf(record.get("FAX")));
			bean.setEmail(record.get("EMAIL")==null?"":record.get("EMAIL").toString());
			bean.setEffectiveDate(record.get("EFFECTIVE_DATE")==null?"":String.valueOf(record.get("EFFECTIVE_DATE")));
			bean.setRemarks(record.get("REMARKS")==null?"":record.get("REMARKS").toString());
			bean.setHeaderName(record.get("HEADER_IMG")==null?"":record.get("HEADER_IMG").toString());
			bean.setFooterName(record.get("FOOTER_IMG")==null?"":record.get("FOOTER_IMG").toString());
			bean.setSignImageName(record.get("SIGN_IMG")==null?"":record.get("SIGN_IMG").toString());
			bean.setStampImageName(record.get("STAMP")==null?"":record.get("STAMP").toString());
			bean.setCurrency(record.get("CURRENCY_NAME")==null?"":record.get("CURRENCY_NAME").toString());
			bean.setOriginCountry(record.get("ORIGINATION_COUNTRY_ID")==null?"":String.valueOf(record.get("ORIGINATION_COUNTRY_ID")));
			bean.setDestiCountry(record.get("DESTINATION_COUNTRY_ID")==null?"":String.valueOf(record.get("DESTINATION_COUNTRY_ID")));
			bean.setDecimalPlaces(record.get("DECIMAL_PLACES")==null?"":String.valueOf(record.get("DECIMAL_PLACES")));
			bean.setTax(record.get("TAX")==null?"":String.valueOf(record.get("TAX")));
			bean.setBranchPrefix(record.get("BRANCH_PREFIX")==null?"":record.get("BRANCH_PREFIX").toString());
			bean.setLang(record.get("LANG_YN")==null?"":record.get("LANG_YN").toString());
			bean.setBelongingBranch(record.get("BELONGING_BRANCH")==null?"":record.get("BELONGING_BRANCH").toString());
			 
			logger.info("Exit into the getSelectedBroker()");
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception into the getSelectedBroker()"+e);
		}
	}
	public List<Map<String, Object>> getBelongingBranch() {
		List<Map<String, Object>> result=null;
		logger.info("Enter into the getBelongingBranch()");
		try{
			String sql=getQuery("BR_GET_BELONG_BRCH");
			result=this.mytemplate.queryForList(sql);
		logger.info("Exit into the getBelongingBranch()");
		}catch (Exception e) {
			e.printStackTrace();
		logger.info("Enter into the getBelongingBranch()"+e);	
		}
		return result;
	}
	public boolean updateBranch(BranchBean bean) {
		boolean result=false;
		logger.info("Enter into updateBranch()");
		try{
			String sql=getQuery("BR_UPDATE_BRANCH");
			Object []args={bean.getBranchName(),
					bean.getStatus(),bean.getAddress1()
					,bean.getAddress2(),bean.getAddress3(),
					bean.getCity(),bean.getCountry(),
					bean.getPhone(),bean.getFax(),bean.getEmail(),"0",
					bean.getEffectiveDate(),bean.getRemarks(),
					bean.getHeaderName()==null?"":bean.getHeaderName(),bean.getFooterName()==null?"":bean.getFooterName(),
					bean.getSignImageName()==null?"":bean.getSignImageName(),bean.getStampImageName()==null?"":bean.getStampImageName(),
					bean.getCurrency(),bean.getCurrency(),bean.getCurrency(),bean.getOriginCountry(),
					bean.getDestiCountry(),bean.getCurrency(),"",
					bean.getDecimalPlaces(),bean.getTax(),
					bean.getBranchPrefix(),bean.getLang(),bean.getBelongingBranch(),bean.getBranchCode()					
			};
			logger.info("Query:: "+sql);
			logger.info("Args:: "+StringUtils.join(args,","));
			this.mytemplate.update(sql,args);
			result=true;
		}catch (Exception e) {
			e.printStackTrace();
			logger.info("Exception into updateBranch()"+e);
		}
		return result;
	}

}
