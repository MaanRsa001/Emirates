package com.maan.adminnew.underwriterManagement;
import java.util.List;
import java.util.Map;

import com.maan.adminnew.userManagement.UserMgtBean;

public class UnderwriterMgtService{
	
	UnderwriterMgtDAO dao=new UnderwriterMgtDAO();
	
	public List<Map<String, Object>> getAdminUnderwriterList(UnderwriterMgtBean ba,String agencyCode, String mode1, String branchCode) {
		return dao.getAdminUnderwriterList(ba,agencyCode, mode1, branchCode);
	}
	
	public List <Map<String, Object>> getUnderwriterDetails(final UnderwriterMgtBean ba, String branchCode, String issurName){
    	return dao.getUnderwriterDetails(ba, branchCode, issurName);
    }
	
	public void updatebrokerDetails(final UnderwriterMgtBean ba, String issurName){
		dao.updatebrokerDetails(ba, issurName);
    }
	
	public List <Map<String, Object>> includeissuerDetails(final UnderwriterMgtBean ba, String type1){
    	return dao.includeissuerDetails(ba, type1);
    }
	
	public List <Map<String, Object>> excludeissuerDetails(final UnderwriterMgtBean ba, String type1){
    	return dao.excludeissuerDetails(ba, type1);
    }

	public void getRSABranches(UnderwriterMgtBean bean) {
		dao.getRSABranches(bean);		
	}

	public int insertUnderwriter(UnderwriterMgtBean bean, String sessionLoginId) {
		return dao.insertUnderwriter(bean, sessionLoginId);		
	}

	public void updateExcludedBrokers(UnderwriterMgtBean bean) {
		dao.updateExcludedBrokers(bean);
		
	}

	public void updateIncludeBrokers(UnderwriterMgtBean bean) {
		dao.updateIncludeBrokers(bean);
		
	}
	public int updateUnderwriter(UnderwriterMgtBean bean, String sessionLoginId){
		return dao.updateUnderwriter(bean, sessionLoginId);
	}
	public void changePassword(UnderwriterMgtBean bean){
		dao.changePassword(bean);
	}
}