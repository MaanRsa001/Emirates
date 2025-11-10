package com.maan.adminnew.AdminMgt;

import java.util.List;
import java.util.Map;

public class AdminMgtService
{	
	AdminMgtDAO dao=new AdminMgtDAO();
	
	public List<Map<String, Object>>UtypeList(String branchCode,String appId){
		return dao.UtypeList(branchCode, appId);
	}
	public List<Map<String, Object>>getMenuList(final AdminMgtBean bean,String branchCode){
		return dao.getMenuList(bean, branchCode);
	}
	public List<Map<String, Object>>adminList(final AdminMgtBean bean,String branchCode,String appId){
		return dao.adminList(bean, branchCode, appId);
	}
	public void getAdminInfo(final AdminMgtBean bean,String branchCode, String appId){
		dao.getAdminInfo(bean, branchCode, appId);
	}
	public void getMenuInfo(final AdminMgtBean bean,String branchCode){
		dao.getMenuInfo(bean, branchCode);
	}
	public int insNewMenu(final AdminMgtBean bean,String branchCode){
		return dao.insNewMenu(bean, branchCode);
	}
	public int updateMenu(final AdminMgtBean bean,String branchCode){
		return dao.updateMenu(bean, branchCode);
	}
	public int insNewAdmin(final AdminMgtBean bean,String branchCode, String appId, String login_id){
		return dao.insNewAdmin(bean, branchCode, appId, login_id);
	}
	public int updateAdmin(final AdminMgtBean bean,String branchCode, String appId, String login_id){
		return dao.updateAdmin(bean, branchCode, appId, login_id);
	}
	public String getBranch(String branchCode){
		return dao.getBranch(branchCode);
	}
	public List<Map<String, Object>> getDashBoard(String loginId,AdminMgtBean bean) {
		return dao.getDashBoard(loginId,bean);
	}
}