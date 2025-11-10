package com.maan.webservice.dao;


import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.common.dao.CommonDAO;
import com.maan.webservice.dao.PolicyGenerationDAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
public class VatPremiumDAO extends MyJdbcTemplate  {
	private static final Logger logger = LogUtil.getLogger(VatPremiumDAO.class);
	
	public boolean getVatApplicableForGCC(String countryId)
    {
		 int count=0;
		try {
			String sql = getQuery("GET_VAT_YN");
			 count=this.mytemplate.queryForObject(sql, Integer.class , new String[] {countryId});
		} catch (DataAccessException e) {
			logger.debug("Exception @ {"+e+"}");
		}
        return count>0;
    }
	
	public String getVatPercentage(String policyStartDt)
	{
		String result="";
		try {
			String sql = getQuery("GET_VAT_PERCENT");
			
			result=(String) this.mytemplate.queryForObject(sql, new Object[]{policyStartDt},String.class);
		} catch (DataAccessException e) {
			result="0";
			logger.debug("Exception @ {"+e+"}");
		}
		return result;
	}
	public void insertVatInfo(String option, Object[] args)
	{
		try
		{
			String sql = getQuery(option);
			this.mytemplate.update(sql, args);
		}
		catch(Exception e)
		{
			logger.debug("Exception @ {"+e+"}");
		}
		
	}
	public Map<String,Object> getResultMap(String option, String[] args)
	{
		Map<String,Object> map=null;
		try
		{
			logger.info("getValue - Enter || "+option+" args: "+ StringUtils.join(args, ","));
			String sql = getQuery(option);
			map=this.mytemplate.queryForMap(sql, args);
			logger.info("sql=>"+sql);
		}
		catch(Exception e)
		{
			logger.debug("Exception @ {"+e+"}");
		}
		return map;

	}
	public String getValue(String option, String[] args)
	{
		logger.info("getValue - Enter || "+option+" args: "+ StringUtils.join(args, ","));
		String result="";					
		try{
			String sql=getQuery(option);
			result=(String)this.mytemplate.queryForObject(sql,args, String.class);			
		}
		catch (Exception e) {			
			result=StringUtils.isBlank(result)?"":result;
		}		
		logger.info("getValue() - Exit || Result: "+result );
				
		return result;
	}
}


