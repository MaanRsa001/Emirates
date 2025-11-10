package com.maan.adminnew.branchMgt;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.maan.adminnew.common.CommonService;
import com.maan.common.StringHelper;

public class BranchService {
	CommonService cservice=new CommonService();
	BranchDAO dao=new BranchDAO();
	 
	public void setDropDown(BranchBean bean){
		bean.setCountryList(dao.getCountries());
		bean.setCurrrencyList(dao.getCurrencies());
		bean.setBelongingBranchList(dao.getBelongingBranch());
		
	}
	public List<Map<String, Object>> getCities(String countryAjax) {
		return dao.getCities(countryAjax);
	}
	public boolean saveBranch(BranchBean bean,String filePath) {
		boolean result=false;
		List<String> errorList=new ArrayList<String>();
		
			if("new".equalsIgnoreCase(bean.getMode())){
				uploadImages(bean,filePath);
				if(dao.saveBranch(bean))
					
					result=true;
			}else{
				//uploadImages(bean,filePath);
				if(dao.updateBranch(bean))
					result=true;
			}		  
		return result;	
	}
	public boolean uploadImages(BranchBean bean,String destPath){
		boolean result=false;
		try{
			bean.setHeaderName(bean.getBranchPrefix()+"-Header.jpg");
			bean.setFooterName(bean.getBranchPrefix()+"-Footer.jpg");
			bean.setSignImageName(bean.getBranchPrefix()+"-Sign.jpg");
			bean.setStampImageName(bean.getBranchPrefix()+"-Stamp.jpg");
			FileUtils.copyFile(bean.getHeaderImage(),new File(destPath,bean.getHeaderName()));
			FileUtils.copyFile(bean.getFooterImage(),new File(destPath,bean.getFooterName()));
			FileUtils.copyFile(bean.getSignImage(),new File(destPath,bean.getSignImageName()));
			FileUtils.copyFile(bean.getStampImage(),new File(destPath,bean.getStampImageName()));
			result=true;
		}catch (Exception e) {
			result=false;
		}		
		return result;
	}
	public void loadList(BranchBean bean) {
		dao.loadLost(bean);		
	}
	public void getSelectedBroker(BranchBean bean) {
		dao.getSelectedBroker(bean);	
	}	 
}

