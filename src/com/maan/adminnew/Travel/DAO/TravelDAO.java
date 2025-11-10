package com.maan.adminnew.Travel.DAO;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.adminnew.Travel.DAO.TravelBean;
import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class TravelDAO extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(TravelDAO.class);
	public List<Map<String, Object>> getSchemeValue(TravelBean travelBean) {
		List<Map<String, Object>> schemeValue=null;		
		logger.info("getSchemeValue - Enter");
		try{
			String sql=getQuery("GET_SCHEME_VALUE");
			logger.info("SQL=>"+sql);
			schemeValue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getSchemeValue - Exit || Result: " + schemeValue.size());
		return schemeValue;
	}
	public List<Map<String, Object>> getOptionValue(TravelBean travelBean) {
		List<Map<String, Object>> optionvalue=null;		
		logger.info("getOptionValue - Enter");
		try{
			String sql=getQuery("GET_OPTION_VALUE");
			logger.info("SQL=>"+sql);
			optionvalue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getOptionValue - Exit || Result: " + optionvalue.size());
		return optionvalue;
	}
	public List<Map<String, Object>> getCoverValue(TravelBean travelBean) {
		List<Map<String, Object>> coverValue=null;		
		logger.info("getCoverValue - Enter");
		try{
			String sql=getQuery("GET_COVER_VALUE");
			logger.info("SQL=>"+sql);
			coverValue=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode(),travelBean.getSchemeId(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getCoverValue - Exit || Result: " + coverValue.size());
		return coverValue;
	}
	public List<Map<String, Object>> getScheme(TravelBean travelBean) {
		List<Map<String, Object>> scheme=null;		
		logger.info("getScheme - Enter");
		try{
			String sql=getQuery("GET_SCHEME");
			logger.info("SQL=>"+sql);
			scheme=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getScheme - Exit || Result: " + scheme.size());
		return scheme;
	}
	public List<Map<String, Object>> getSchemeHistory(TravelBean travelBean) {
		List<Map<String, Object>> scheme=null;		
		logger.info("getSchemeHistory - Enter");
		try{
			String sql=getQuery("GET_SCHEME_HISTORY");
			logger.info("SQL=>"+sql);
			scheme=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getSchemeHistory - Exit || Result: " + scheme.size());
		return scheme;
	}
	public String editScheme(TravelBean travelBean) {
		logger.info("editScheme - Enter");
		String result="";
		try{
			String sql=getQuery("GET_SCHEME_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setSchemeId(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
				travelBean.setSchemeName(map.get("SCHEME_NAME")==null?"":map.get("SCHEME_NAME").toString());
				travelBean.setSchemeCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setSchemeDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setSchemeProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setSchemeStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editScheme - Exit || Result: ");
		return result;
	}
	public String insertScheme(TravelBean travelBean) {
		logger.info("insertScheme - Enter");
		String sql,result="false";
		int res=0,amendId=0,schemeId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_SCHEME_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getSchemeCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_SCHEME_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_SCHEME_DTLS");
				this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId});
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_SCHEME_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				schemeId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("SCHEME_ID=>"+schemeId);
				sql=getQuery("INS_SCHEME_DTLS");
				this.mytemplate.update(sql,new Object[]{schemeId,travelBean.getSchemeName(),travelBean.getBranchCode(),travelBean.getSchemeStatus(),travelBean.getSchemeCode(),travelBean.getProductId(),travelBean.getSchemeDate(),amendId});
				travelBean.setDisplay("new");
				result="success";
			}
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertScheme - Exit || Result: ");
		return result;
	}

	public List<Map<String, Object>> getOption(TravelBean travelBean) {
		List<Map<String, Object>> option=null;		
		logger.info("getOption - Enter");
		try{
			String sql=getQuery("GET_OPTION");
			logger.info("SQL=>"+sql);
			option=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getOption - Exit || Result: " + option.size());
		return option;
	}
	public List<Map<String, Object>> getOptionHistory(TravelBean travelBean) {
		List<Map<String, Object>> option=null;		
		logger.info("getOptionHistory - Enter");
		try{
			String sql=getQuery("GET_OPTION_HISTORY");
			logger.info("SQL=>"+sql);
			option=this.mytemplate.queryForList(sql,new String[]{travelBean.getOptionId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getOptionHistory - Exit || Result: " + option.size());
		return option;
	}
	
	public String editOption(TravelBean travelBean) {
		logger.info("editOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_OPTION_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getOptionId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setOptionId(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
				travelBean.setOptionName(map.get("OPTION_NAME")==null?"":map.get("OPTION_NAME").toString());
				travelBean.setOptionCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setOptionDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setOptionProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setOptionStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editOption - Exit || Result: ");
		return result;
	}
	public String insertOption(TravelBean travelBean) {
		logger.info("insertOption - Enter");
		String sql,result="false";
		int res=0,amendId=0,optionId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_OPTION_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getOptionCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
				if("update".equalsIgnoreCase(travelBean.getDisplay())){
					sql=getQuery("GET_OPTION_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getOptionId();
					obj[1]=travelBean.getBranchCode();
					logger.info("Query=>"+sql);
					amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
					logger.info("AMENDID=>"+amendId);
					amendId=amendId+1;
					sql=getQuery("INS_OPTION_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getOptionId(),travelBean.getOptionName(),travelBean.getBranchCode(),travelBean.getOptionStatus(),travelBean.getOptionCode(),travelBean.getProductId(),travelBean.getOptionDate(),amendId});
					result="success";
					travelBean.setDisplay("edit");
				}else{
					sql=getQuery("GET_OPTION_ID");
					obj=new Object[1];
					obj[0]=travelBean.getBranchCode();
					optionId=this.mytemplate.queryForObject(sql,Integer.class,obj);
					logger.info("OPTION_ID=>"+optionId);
					sql=getQuery("INS_OPTION_DTLS");
					this.mytemplate.update(sql,new Object[]{optionId,travelBean.getOptionName(),travelBean.getBranchCode(),travelBean.getOptionStatus(),travelBean.getOptionCode(),travelBean.getProductId(),travelBean.getOptionDate(),amendId});
					result="success";
					travelBean.setDisplay("new");
				}
				}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertOption - Exit || Result: ");
		return result;
	}
	
	public List<Map<String, Object>> getCoverage(TravelBean travelBean) {
		List<Map<String, Object>> cover=null;		
		logger.info("getCoverage - Enter");
		try{
			String sql=getQuery("GET_MASTER_COVERAGE");
			logger.info("SQL=>"+sql);
			cover=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode()});			
		   }
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getCoverage - Exit || Result: " + cover.size());
		return cover;
	}
	public List<Map<String, Object>> getCoverageHistory(TravelBean travelBean) {
		List<Map<String, Object>> cover=null;		
		logger.info("getCoverageHistory - Enter");
		try{
			String sql=getQuery("GET_COVERAGE_HISTORY");
			logger.info("SQL=>"+sql);
			cover=this.mytemplate.queryForList(sql,new String[]{travelBean.getCoverageId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getCoverageHistory - Exit || Result: " + cover.size());
		return cover;
	}
	public String editCoverage(TravelBean travelBean) {
		logger.info("editCoverage - Enter");
		String result="";
		try{
			String sql=getQuery("GET_COVERAGE_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getCoverageId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	
				travelBean.setCoverageId(map.get("COVERAGES_ID")==null?"":map.get("COVERAGES_ID").toString());
				travelBean.setCoverageName(map.get("COVERAGES_NAME")==null?"":map.get("COVERAGES_NAME").toString());
				travelBean.setCoverageValue(map.get("COVERAGES_VALUE")==null?"":map.get("COVERAGES_VALUE").toString());
				travelBean.setCoverageCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setCoverageDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setCoverageProduct(map.get("PRODUCT_CODE")==null?"":map.get("PRODUCT_CODE").toString());
				travelBean.setCoverageStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editCoverage - Exit || Result: ");
		return result;
	}
	public String insertCoverage(TravelBean travelBean) {
		logger.info("insertCoverage - Enter");
		String sql,result="false";
		int res=0,amendId=0,coverageId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_COVERAGE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getCoverageCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
				if("update".equalsIgnoreCase(travelBean.getDisplay())){
					sql=getQuery("GET_COVERAGE_AMEND_ID");
					obj=new Object[2];
					obj[0]=travelBean.getCoverageId();
					obj[1]=travelBean.getBranchCode();
					logger.info("Query=>"+sql);
					amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
					logger.info("AMENDID=>"+amendId);
					amendId=amendId+1;
					sql=getQuery("INS_COVERAGE_DTLS");
					this.mytemplate.update(sql,new Object[]{travelBean.getCoverageId(),travelBean.getCoverageName(),travelBean.getCoverageValue(),travelBean.getBranchCode(),travelBean.getCoverageStatus(),travelBean.getCoverageCode(),travelBean.getProductId(),travelBean.getCoverageDate(),amendId});
					result="success";
					travelBean.setDisplay("edit");
				}else{
					sql=getQuery("GET_COVERAGE_ID");
					obj=new Object[1];
					obj[0]=travelBean.getBranchCode();
					coverageId=this.mytemplate.queryForObject(sql,Integer.class,obj);
					logger.info("COVERAGE_ID=>"+coverageId);
					sql=getQuery("INS_COVERAGE_DTLS");
					this.mytemplate.update(sql,new Object[]{coverageId,travelBean.getCoverageName(),travelBean.getCoverageValue(),travelBean.getBranchCode(),travelBean.getCoverageStatus(),travelBean.getCoverageCode(),travelBean.getProductId(),travelBean.getCoverageDate(),amendId});
					result="success";
					travelBean.setDisplay("new");
				}
				}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertCoverage - Exit || Result: ");
		return result;
	}
	
	//DISCOUNT RATE BLOCK
	
	public List<Map<String, Object>> getDiscountRate(TravelBean travelBean) {
		List<Map<String, Object>> discountrate=null;		
		logger.info("getDiscountRate - Enter");
		try{
			String sql=getQuery("GET_DISCOUNT_RATE");
			logger.info("SQL=>"+sql);
			discountrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getDiscountRate - Exit || Result: " + discountrate.size());
		return discountrate;
	}
	public List<Map<String, Object>> getDiscountRateHistory(TravelBean travelBean) {
		List<Map<String, Object>> discountrate=null;		
		logger.info("getDiscountRateHistory - Enter");
		try{
			String sql=getQuery("GET_DISCOUNT_RATE_HISTORY");
			logger.info("SQL=>"+sql);
			discountrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(), travelBean.getDisId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getDiscountRateHistory - Exit || Result: " + discountrate.size());
		return discountrate;
	}
	public String editDiscountRate(TravelBean travelBean) {
		logger.info("editDiscountRate - Enter");
		String result="";
		try{
			String sql=getQuery("GET_DISCOUNT_RATE_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDrateId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				travelBean.setDrateId(map.get("RATE_ID")==null?"":map.get("RATE_ID").toString());
				travelBean.setTypeList(map.get("TYPE")==null?"":map.get("TYPE").toString());
				travelBean.setSageList(map.get("AGE_START")==null?"":map.get("AGE_START").toString());
				travelBean.setEageList(map.get("AGE_END")==null?"":map.get("AGE_END").toString());
				travelBean.setRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDrateDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDrateCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDrateStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editDiscountRate - Exit || Result: ");
		return result;
	}
	public String insertDiscountRate(TravelBean travelBean) {
		logger.info("insertDiscountRate - Enter");
		String sql,result="false";
		int res=0,amendId=0,drateId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_DISCOUNT_RATE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getDrateCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_DISCOUNT_RATE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDrateId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_DISCOUNT_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{travelBean.getDrateId(),travelBean.getTypeList(),travelBean.getSageList(),travelBean.getEageList(),travelBean.getRateValue(),travelBean.getBranchCode(),travelBean.getDrateDate(),amendId,travelBean.getDrateStatus(),travelBean.getDrateCode(),travelBean.getProductId()});
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_DISCOUNT_RATE_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				drateId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("RATE_ID=>"+drateId);
				sql=getQuery("INS_DISCOUNT_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{drateId,travelBean.getTypeList(),travelBean.getSageList(),travelBean.getEageList(),travelBean.getRateValue(),travelBean.getBranchCode(),travelBean.getDrateDate(),amendId,travelBean.getDrateStatus(),travelBean.getDrateCode(),travelBean.getProductId()});
				travelBean.setDisplay("new");
				result="success";
			}
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertDiscountRate - Exit || Result: ");
		return result;
	}
	
	//RELATION DISCOUNT
	
	public List<Map<String, Object>> getRelationDiscount(TravelBean travelBean) {
		List<Map<String, Object>> relationdis=null;		
		logger.info("RelationDiscount - Enter");
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT");
			logger.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("RelationDiscount - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public List<Map<String, Object>> getRelationDiscountHistory(TravelBean travelBean) {
		List<Map<String, Object>> relationdis=null;		
		logger.info("getRelationDiscountHistory - Enter");
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT_HISTORY");
			logger.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getRelationDiscountHistory - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public String editRelationDiscount(TravelBean travelBean) {
		logger.info("editRelationDiscount - Enter");
		String result="";
		try{
			String sql=getQuery("GET_RELATION_DISCOUNT_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				travelBean.setDisId(map.get("DISCOUNT_ID")==null?"":map.get("DISCOUNT_ID").toString());
				travelBean.setDisStart(map.get("DISCOUNT_START")==null?"":map.get("DISCOUNT_START").toString());
				travelBean.setDisEnd(map.get("DISCOUNT_END")==null?"":map.get("DISCOUNT_END").toString());
				travelBean.setDisRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDisType(map.get("RELATION_TYPE")==null?"":map.get("RELATION_TYPE").toString());
				travelBean.setDisDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDisCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDisStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editRelationDiscount - Exit || Result: ");
		return result;
	}
	public String insertRelationDiscount(TravelBean travelBean) {
		logger.info("insertRelationDiscount - Enter");
		String sql,result="false";
		int res=0,amendId=0,disId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_RELATION_DISCOUNT_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getDisCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_RELATION_DISCOUNT_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDisId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_RELATION_DISCOUNT_DTLS");
				this.mytemplate.update(sql,new Object[]{travelBean.getDisId(),travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getDisType(),travelBean.getDisDate(),travelBean.getDisCode(),amendId,travelBean.getDisStatus(),travelBean.getBranchCode(),travelBean.getProductId()});
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_RELATION_DISCOUNT_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				disId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("DISCOUNT_ID=>"+disId);
				sql=getQuery("INS_RELATION_DISCOUNT_DTLS");
				this.mytemplate.update(sql,new Object[]{disId,travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getDisType(),travelBean.getDisDate(),travelBean.getDisCode(),amendId,travelBean.getDisStatus(),travelBean.getBranchCode(),travelBean.getProductId()});
				travelBean.setDisplay("new");
				result="success";
			}
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertRelationDiscount - Exit || Result: ");
		return result;
	}
	
	//PREMIUM RATE
	public List<Map<String, Object>> getPremiumRate(TravelBean travelBean) {
		List<Map<String, Object>> premiumrate=null;		
		logger.info("getPremiumRate - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE");
			logger.info("SQL=>"+sql);
			premiumrate=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getPremiumRate - Exit || Result: " + premiumrate.size());
		return premiumrate;
	}
	public List<Map<String, Object>> getPremiumRateHistory(TravelBean travelBean) {
		List<Map<String, Object>> relationdis=null;		
		logger.info("getPremiumRateHistory - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE_HISTORY");
			logger.info("SQL=>"+sql);
			relationdis=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getPremiumRateHistory - Exit || Result: " + relationdis.size());
		return relationdis;
	}
	public String editPremiumRate(TravelBean travelBean) {
		logger.info("editPremiumRate - Enter");
		String result="";
		try{
			String sql=getQuery("GET_PREMIUM_RATE_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getProductId(),travelBean.getDisId(),travelBean.getBranchCode()};
			Map map=this.mytemplate.queryForMap(sql,obj);
			if(map!=null&& map.size()>0)
			{	 
				travelBean.setDisId(map.get("DISCOUNT_ID")==null?"":map.get("DISCOUNT_ID").toString());
				travelBean.setDisStart(map.get("DISCOUNT_START")==null?"":map.get("DISCOUNT_START").toString());
				travelBean.setDisEnd(map.get("DISCOUNT_END")==null?"":map.get("DISCOUNT_END").toString());
				travelBean.setDisRateValue(map.get("RATE_VALUE")==null?"":map.get("RATE_VALUE").toString());
				travelBean.setDisType(map.get("RELATION_TYPE")==null?"":map.get("RELATION_TYPE").toString());
				travelBean.setDisDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setDisCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setDisStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editPremiumRate - Exit || Result: ");
		return result;
	}
	public String insertPremiumRate(TravelBean travelBean) {
		logger.info("insertPremiumRate - Enter");
		String sql,result="false";
		int res=0,amendId=0,disId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_PREMIUM_RATE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getDisCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
			if("update".equalsIgnoreCase(travelBean.getDisplay())){
				sql=getQuery("GET_PREMIUM_RATE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getDisId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_PREMIUM_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{travelBean.getDisId(),travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getDisType(),travelBean.getDisDate(),travelBean.getDisCode(),amendId,travelBean.getDisStatus(),travelBean.getBranchCode(),travelBean.getProductId()});
				travelBean.setDisplay("edit");
				result="success";
			}else{
				sql=getQuery("GET_PREMIUM_RATE_ID");
				obj=new Object[1];
				obj[0]=travelBean.getBranchCode();
				disId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("DISCOUNT_ID=>"+disId);
				sql=getQuery("INS_PREMIUM_RATE_DTLS");
				this.mytemplate.update(sql,new Object[]{disId,travelBean.getDisStart(),travelBean.getDisEnd(),travelBean.getDisRateValue(),travelBean.getDisType(),travelBean.getDisDate(),travelBean.getDisCode(),amendId,travelBean.getDisStatus(),travelBean.getBranchCode(),travelBean.getProductId()});
				travelBean.setDisplay("new");
				result="success";
			}
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertPremiumRate - Exit || Result: ");
		return result;
	}
	//LINK OPTION
	public List<Map<String, Object>> getLinkOption(TravelBean travelBean) {
		List<Map<String, Object>> linkoption=null;		
		logger.info("getLinkOption - Enter");
		try{
			String sql=getQuery("GET_LINK_OPTION");
			logger.info("SQL=>"+sql);
			linkoption=this.mytemplate.queryForList(sql,new String[]{travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getLinkOption - Exit || Result: " + linkoption.size());
		return linkoption;
	}
	public List<Map<String, Object>> getLinkOptionHistory(TravelBean travelBean) {
		List<Map<String, Object>> linkoption=null;		
		logger.info("getLinkOptionHistory - Enter");
		try{
			String sql=getQuery("GET_LINK_OPTION_HISTORY");
			logger.info("SQL=>"+sql);
			linkoption=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getLinkOptionHistory - Exit || Result: " + linkoption.size());
		return linkoption;
	}
	public String editLinkOption(TravelBean travelBean) {
		logger.info("editLinkOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_LINK_OPTION_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getProductId(),travelBean.getBranchCode()};
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				travelBean.setSchemeId(map.get("SCHEME_ID")==null?"":map.get("SCHEME_ID").toString());
				travelBean.setOptionId(map.get("OPTION_ID")==null?"":map.get("OPTION_ID").toString());
				travelBean.setLinkOptionDate(map.get("EFFECTIVE_DATE")==null?"":map.get("EFFECTIVE_DATE").toString());
				travelBean.setLinkOptionCode(map.get("COREAPP_CODE")==null?"":map.get("COREAPP_CODE").toString());
				travelBean.setLinkOptionStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editLinkOption - Exit || Result: ");
		return result;
	}
	public String insertLinkOption(TravelBean travelBean) {
		logger.info("insertLinkOption - Enter");
		String sql,result="false";
		int res=0,amendId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_LINK_OPTION_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getLinkOptionCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
				sql=getQuery("GET_LINK_OPTION_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_LINK_OPTION_DTLS");
				for(int i=0;i<travelBean.getLinkOption().length;i++)
				{
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getLinkOption()[i],travelBean.getLinkOptionStatus(),travelBean.getBranchCode(),travelBean.getLinkOptionCode(),travelBean.getProductId(),travelBean.getLinkOptionDate(),amendId});
				}
				travelBean.setDisplay("");
				result="success";
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertLinkOption - Exit || Result: ");
		return result;
	}
	//LINK COVERAGE
	public List<Map<String, Object>> getLinkCoverage(TravelBean travelBean) {
		List<Map<String, Object>> linkcoverage=null;		
		logger.info("getLinkCoverage - Enter");
		try{
			String sql=getQuery("GET_LINK_COVERAGE");
			logger.info("SQL=>"+sql);
			linkcoverage=this.mytemplate.queryForList(sql,new String[]{travelBean.getBranchCode(),travelBean.getProductId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getLinkCoverage - Exit || Result: " + linkcoverage.size());
		return linkcoverage;
	}
	public List<Map<String, Object>> getLinkCoverageHistory(TravelBean travelBean) {
		List<Map<String, Object>> linkcoverage=null;		
		logger.info("getLinkCoverageHistory - Enter");
		try{
			String sql=getQuery("GET_PREMIUM_RATE_HISTORY");
			logger.info("SQL=>"+sql);
			linkcoverage=this.mytemplate.queryForList(sql,new String[]{travelBean.getSchemeId(),travelBean.getBranchCode()});			
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("getLinkCoverageHistory - Exit || Result: " + linkcoverage.size());
		return linkcoverage;
	}
	public String editLinkCoverage(TravelBean travelBean) {
		logger.info("editLinkOption - Enter");
		String result="";
		try{
			String sql=getQuery("GET_LINK_COVERAGE_DATA");
			logger.info("SQL=>"+sql);
			Object obj[] = new Object[]{travelBean.getSchemeId(),travelBean.getOptionId(),travelBean.getProductId(),travelBean.getBranchCode(),travelBean.getBranchCode(),travelBean.getProductId()};
			List list=this.mytemplate.queryForList(sql,obj);
			if(list!=null && list.size()>0)
			{
				Map map=(Map)list.get(0);
				travelBean.setLinkCoverageId(map.get("COVERAGES_ID")==null?"":map.get("COVERAGES_ID").toString());
				travelBean.setLinkCoverageRate(map.get("RATE")==null?"":map.get("RATE").toString());
				travelBean.setLinkCoverageDate(map.get("EFF_DATE")==null?"":map.get("EFF_DATE").toString());
				travelBean.setLinkCoverageCode(map.get("CORE_APP_CODE")==null?"":map.get("CORE_APP_CODE").toString());
				travelBean.setLinkCoverageStatus(map.get("STATUS")==null?"":map.get("STATUS").toString());
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("editLinkOption - Exit || Result: ");
		return result;
	}
	public String insertLinkCoverage(TravelBean travelBean) {
		logger.info("insertLinkCoverage - Enter");
		String sql,result="false";
		int res=0,amendId=0;
		Object[] obj=null;
		try{
			sql=getQuery("GET_LINK_COVERAGE_CORE_CODE");
			obj=new Object[1];					  
			obj[0]=travelBean.getLinkOptionCode();
			res=this.mytemplate.queryForObject(sql,Integer.class,obj);
			
			if(res==0){
				sql=getQuery("GET_LINK_COVERAGE_AMEND_ID");
				obj=new Object[2];
				obj[0]=travelBean.getSchemeId();
				obj[1]=travelBean.getBranchCode();
				logger.info("Query=>"+sql);
				amendId=this.mytemplate.queryForObject(sql,Integer.class,obj);
				logger.info("AMENDID=>"+amendId);
				amendId=amendId+1;
				sql=getQuery("INS_LINK_COVERAGE_DTLS");
				for(int i=0;i<travelBean.getLinkOption().length;i++)
				{
					this.mytemplate.update(sql,new Object[]{travelBean.getSchemeId(),travelBean.getLinkOption()[i],travelBean.getLinkOptionStatus(),travelBean.getBranchCode(),travelBean.getLinkOptionCode(),travelBean.getProductId(),travelBean.getLinkOptionDate(),amendId});
				}
				travelBean.setDisplay("");
				result="success";
			}
		}
		catch (Exception e) {			
			logger.debug("EXCEPTION @ { " + e + " }");
		}		
		logger.info("insertLinkCoverage - Exit || Result: ");
		return result;
	}
}
