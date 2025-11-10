package com.maan.adminnew.rating;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RatingEngine extends ActionSupport {
	private static final Logger logger = LogUtil.getLogger(RatingEngine.class);
	private static final long serialVersionUID = 1L;
    RatingEngineService rservice=new RatingEngineService();
    RatingEngineBean bean=new RatingEngineBean();
    //private List <Map<String, Object>> transport_description;
    Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	String appId=(String)session.get("AppId");
	String login_id=(String)session.get("user");
    private List<Map<String, Object>> transportList;
    
    
	public String engine()
     {
		logger.info("ENTER-->Method to getTList");
    	transportList=rservice.getAdminTransportList(branchCode);
		logger.info("getTList() - Exit"+login_id);
    	return "transportList";
	  
	  
     }
	public List<Map<String, Object>> getTransports()
	{
	  return rservice.getTransports(branchCode);
		
	}
	public List<Map<String, Object>> getCategorties()
	{
		return rservice.getCategories(branchCode);
	}
	
	public List<Map<String, Object>> getMaterials()
	{
	  return rservice.getMaterials(branchCode);
		
	}
	public List<Map<String, Object>> getTransportList()
	{
		return transportList;
	}
	public List<Map<String, Object>> getCountries()
	{
		return rservice.getCountries(branchCode);
	}
	public List<Map<String, Object>> getProductsDET()
	{
		return rservice.getProductsDET(branchCode);
	}
	public List <Map<String, Object>> getConveyance()
	{
		return rservice.getConveyance(branchCode);
	}
	public List<Map<String, Object>> getStage()
	{
		return rservice.getStage();
	}
	public String getTList(){
    	logger.info("ENTER-->Method to getTList");
    	transportList=rservice.getAdminTransportList(branchCode);
		logger.info("getTList() - Exit"+login_id);
    	return "transportList";
    }
	public String getTransportListAjax(){
    	if("transportList".equals(bean.getReqFrom())){
    		transportList=rservice.getTransportListAjax(branchCode, bean.getSearchBy(), bean.getSearchValue());
    	}
    	return "transportList";
    }
}
