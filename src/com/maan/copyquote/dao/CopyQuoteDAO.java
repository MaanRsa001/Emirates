package com.maan.copyquote.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

import oracle.jdbc.OracleTypes;

public class CopyQuoteDAO  extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(CopyQuoteDAO.class);
	public String sql="";
	
	public List<Map<String, Object>> getCopyQuoteSearch(String type,String value,String openCoverNo, String productId)
	{
		List<Map<String, Object>> list=null;	
		try
		{
		if(type.equals("quoteNo"))
		{ 
			sql=getQuery(DBConstants.COPYQUOTE_QUOTENO);		
		}
		else if(type.equals("policyNo")){
			sql=getQuery(DBConstants.COPYQUOTE_POLICYNO);
		}
		else
		{
			sql=getQuery(DBConstants.COPYQUOTE_CUSTNAME);
		}		
		list=this.mytemplate.queryForList(sql, new String[]{value,productId});		
		logger.info("args[] ==> "+type+","+value);
		logger.info("Qurey ==> "+sql);
		}
		catch(Exception e)
		{
			logger.debug("Exception @ "+e);
		}
		
		logger.info("getOptionsList - Exit || Result: "+list.size());
		return list;		
	}
	public Map<String, Object> copyQuote(String loginId,String quoteNo, String type, String typeId, String issuer)
	{
		logger.info("copyQuote - Enter || loginId: "+loginId+" quoteNo: "+quoteNo+" type: "+type+" typeId: "+typeId);
		logger.info("args[]"+loginId+","+quoteNo);
		//CopyQuoteStroredProcedure sp=new CopyQuoteStroredProcedure(this.mytemplate.getDataSource(), "CopyQuote_newversion");
		CopyQuoteStroredProcedure sp=new CopyQuoteStroredProcedure(this.mytemplate.getDataSource(), "CopyQuote");
		Map<String, String> inParams=new HashMap<String, String>();
		inParams.put("TYPE", type);
		inParams.put("LOGIN_ID", loginId);
		inParams.put("ISSUER_ID", issuer);
		inParams.put("END_TYPE", typeId);
		inParams.put("QUOTE_NO", quoteNo);	
		inParams.put("CUSTOMER_NAME", "");	
		inParams.put("ERROR_STATUS", "");
		logger.info("copyQuote - Exit");
		return sp.executeProc(inParams);
	}
}
class CopyQuoteStroredProcedure extends StoredProcedure 
{
	public CopyQuoteStroredProcedure(DataSource ds, String spName)
	{
		super(ds,spName);
		declareParameter(new SqlParameter("TYPE",OracleTypes.VARCHAR));		
		declareParameter(new SqlParameter("LOGIN_ID",OracleTypes.VARCHAR));
		declareParameter(new SqlParameter("ISSUER_ID",OracleTypes.VARCHAR));		
		declareParameter(new SqlParameter("END_TYPE",OracleTypes.VARCHAR));
		declareParameter(new SqlInOutParameter("QUOTE_NO",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("CUSTOMER_NAME",OracleTypes.VARCHAR));
		declareParameter(new SqlOutParameter("ERROR_STATUS",OracleTypes.VARCHAR));		
		super.compile();
	}
	public Map<String, Object> executeProc(Map<String, String> inParams)
	{
		return super.execute(inParams);
	}
}
