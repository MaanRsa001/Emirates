package com.maan.adminnew.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;



public class CommonService {
	private static final Logger logger = LogUtil.getLogger(CommonService.class);
	CommonDAO dao=new CommonDAO();
	 public List<Map<String, Object>> getAdminInfo(final String user){
    	return dao.getAdminInfo(user);
    }
    public List<Map<String, Object>> getMenuList(final String menuIds, final String bCode, final String catCode){
        return dao.getMenuList(menuIds, bCode, catCode);
    }
	public List <Map<String, Object>> getCountries(String branchCode){
    	return dao.getCountries(branchCode);
    }
	public int getExist(Object[] val,String str)
	{
		return dao.getExist(val,str);
	}
    public List <Map<String, Object>> getEmirates(String branchCode){
    	return dao.getEmirates(branchCode);
    }
    public List <Map<String, Object>> getNationalities(){
    	return dao.getNationalities();
    }
    public List <Map<String, Object>> getTitles(String branchCode){
    	return dao.getTitles(branchCode);
    }
    public List <Map<String,Object>> getProductsDET(String branchCode, String agencyCode){
   	 return dao.getProductsDET(branchCode, agencyCode);
   }
   
    public int getCustomerId(String branchCode){
    	return dao.getCustomerId(branchCode);
    }
    public String getUserCode(String branchCode){
    	return dao.getUserCode(branchCode);
    }
    public int getMaxUserId(){
    	return dao.getMaxUserId();
   }
    public void checkPassword(String[] args){
		dao.checkPassword(args);
	}
    public void updateCommission(Object[] info, Object[] obj1){
        dao.updateCommission(info, obj1);
    }
    public void insertCommission(Object[] info, Object[] obj1){
        dao.insertCommission(info, obj1);
    }
    public int checkExistProduct(String productId, String agencyCode){
        return dao.checkExistProduct(productId, agencyCode);
    }
    public List <Map<String, Object>> getAdminBrokerList(String branchCode, String appId){
    	return dao.getAdminBrokerList(branchCode, appId);
    }
    public List<Map<String, Object>> getCurrencyList() {
		return dao.getCurrencyList();
    }
    public List<Map<String, Object>> getBusinessTypeList(final String branchCode) {
		return dao.getBusinessTypeList(branchCode);
    }
    public HashMap getBrokerUserDetails(String branch){
    	return dao.getBrokerUserDetails(branch);
    }
    public List<Map<String, Object>> isBetweenTwo(Object[] val)
    {
    	return dao.isBetweenTwo(val);
    }
    
    
    public static String getApplicationPath() {
		String applicationPath = "";
		try {
			final String path=(CommonService.class).getProtectionDomain().getCodeSource().getLocation().getPath();
			if(SystemUtils.IS_OS_LINUX) {
				applicationPath = path.substring(0, path.indexOf("WEB-INF"));
			}
			else if(SystemUtils.IS_OS_WINDOWS) {
				applicationPath = path.substring(1, path.indexOf("WEB-INF"));
			}
			applicationPath = applicationPath.replaceAll("%20", " ");
			logger.info("Application Path==> " + applicationPath);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return applicationPath;
	}
    
    public static String getAppMode() {
		String appMode = "";
		try {
			final String applicationPath = getApplicationPath();
			appMode = (applicationPath.indexOf("test")!=-1||applicationPath.indexOf("Test")!=-1)?"Test":"Live";
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return appMode;
	}
    
    public static String getAppEnvironment() {
		String appEnvironment = "";
		try {
			final String applicationPath = getApplicationPath();
			appEnvironment = (applicationPath.indexOf("test")!=-1||applicationPath.indexOf("Test")!=-1)?" - FROM TEST ENVIRONMENT":"";
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return appEnvironment;
	}
}
