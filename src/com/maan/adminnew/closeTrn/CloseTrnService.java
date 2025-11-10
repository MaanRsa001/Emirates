package com.maan.adminnew.closeTrn;

import java.util.List;
import java.util.Map;

public class CloseTrnService{
	private CloseTrnDAO trndao= new CloseTrnDAO();
	
	public void viewTrnDetail(CloseTrnBean bean, String branchCode, String pid){
		trndao.viewTrnDetail(bean, branchCode, pid);
	}
	
	public int saveCLoseTrndetails(CloseTrnBean bean, String branchCode, String loginId, String cid){
		return trndao.saveCLoseTrndetails(bean, branchCode, loginId, cid);
	}
	
	public List<Map<String, Object>> listViewDetail(String branchCode){
		return trndao.listViewDetail(branchCode);
	}
	
}