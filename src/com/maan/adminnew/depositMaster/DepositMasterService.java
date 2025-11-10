package com.maan.adminnew.depositMaster;

import java.util.List;
import java.util.Map;

public class DepositMasterService{
	DepositMasterDAO dao=new DepositMasterDAO();
	
	public List<DepositMasterBean>getCbcList(String branchCode, String searchField, String searchString, String searchOper, String mode, DepositMasterBean bean){
		return dao.getCbcList(branchCode, searchField, searchString, searchOper, mode, bean);
	}
	public List<Map<String,Object>> getCustomerList(String branchCode, String agencyCode){
		return dao.getCustomerList(branchCode, agencyCode);
	}
	public List<Map<String,Object>> getBrokerList(String branchCode){
		return dao.getBrokerList(branchCode);
	}
	public List<Map<String,Object>> getOpenCoverList(String customerId, String branchCode){
		return dao.getOpenCoverList(customerId, branchCode);
	}
	public List<DepositMasterBean>getDepositList(String branchCode, String searchField, String searchString, String searchOper, String mode, DepositMasterBean bean){
		return dao.getDepositList(branchCode, searchField, searchString, searchOper, mode, bean);
	}
	public List<Map<String, Object>> getChequeDetails(String depositNo, String branchCode){
		return dao.getChequeDetails(depositNo, branchCode);
	}
	public int saveCBC(DepositMasterBean bean, DepositMasterAction dma){
		return dao.saveCBC(bean, dma);
	}
	public int saveCheque(DepositMasterBean bean, DepositMasterAction dma){
		return dao.saveCheque(bean, dma);
	}
	public String getCbcValid(DepositMasterAction dma, DepositMasterBean bean){
		return dao.getCbcValid(dma, bean);
	}
}
