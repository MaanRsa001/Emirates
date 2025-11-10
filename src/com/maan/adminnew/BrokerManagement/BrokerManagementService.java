package com.maan.adminnew.BrokerManagement;

import java.util.List;
import java.util.Map;

import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.maan.integration.CustXmlBean;
import com.maan.integration.Customer;
import com.maan.integration.IntegrationService;

public class BrokerManagementService
{	
	BrokerManagementDAO dao=new BrokerManagementDAO();

	public List <String> getBrokerDetails(final BrokerMgmBean ba, String agencyCode,String branchCode){
		return dao.getBrokerDetails(ba, agencyCode,branchCode);
	}
	public List <Map<String, Object>> getCommisionData(String agencyCode){
		return dao.getCommisionData(agencyCode);
	}
	public List <Map<String, Object>> getProducts(final BrokerMgmBean ba){
		return dao.getProducts(ba);
	}
	public List <Map<String, Object>> getBranchDetails(String branchCode){
		return dao.getBranchDetails(branchCode);
	}
	public List <Map<String, Object>> getBrokerCode(){
		return dao.getBrokerCode();
	}
	public List <Map<String, Object>> getExecutives(){
		return dao.getExecutives();
	}
	public List <Map<String, Object>> getcoreCustomererInfo(String cust_name, String bcode, String userLoginMode){
		return dao.getcoreCustomererInfo(cust_name,bcode, userLoginMode);
	}
	public List <String> getBrokerTaxInfo(final BrokerMgmBean ba, String[] args){
		return dao.getBrokerTaxInfo(ba,args);
	}
	public void newBrokerCreation(Object[] args, Object[] info ){
		dao.newBrokerCreation(args,info);
	}
	public int getBroke_Code(){
		return dao.getBroke_Code();
	}
	public int getCustomer_Id(String agencyCode, String branchCode){
		return dao.getCustomer_Id(agencyCode, branchCode);
	}
	public void getmax_Broke_Code(Object[] args){
		dao.getmax_Broke_Code(args);
	}
	public void update_Broker(Object[] args){
		dao.update_Broker(args);
	}
	public void update_PersonalInfo(Object[] arg){
		dao.update_PersonalInfo(arg);
	}
	public int getMax_amend_Id(String branchCode, int broker_Code){
		return dao.getMax_amend_Id(branchCode,broker_Code);
	}
	public void insTaxInfo(Object[] val){
		dao.insTaxInfo(val);
	}
	public void updTaxInfo(Object[] val){
		dao.updTaxInfo(val);
	}
	public List <Map<String, Object>> getCommissionDET(String agencyCode, String branchCode){
		return dao.getCommissionDET(agencyCode,branchCode);
	}

	public List <String> getCompDet(final BrokerMgmBean ba,String agencyCode){
		return dao.getCompDet(ba,agencyCode);
	}

	public void updateProduct(Object[] pId){
		dao.updateProduct(pId);
	}
	public List  <String> getLogInId(final BrokerMgmBean ba,String agencyCode){
		return dao.getLogInId(ba,agencyCode);
	}
	public void insertLogInDet(String[] args){
		dao.insertLogInDet(args);
	}
	public void updatePersonalLogin(Object[] params){
		dao.updatePersonalLogin(params);
	}
	public String getBrokerStatus(String agencyCode){
		return dao.getBrokerStatus(agencyCode);
	}
	public void updateBrokerStatus(String[] obj){
		dao.updateBrokerStatus(obj);
	}
	/* public List <Map<String, Object>> getProductStatus(String[] obj){
	  return dao.getProductStatus(obj);
	}*/
	public List <Map<String, Object>> getProductStatuss(String[] obj){
		return dao.getProductStatuss(obj);
	}
	public void updateUserIdCreation(String[] obj){
		dao.updateUserIdCreation(obj);
	}
	public List<Map<String, Object>> getBrokerListAjax(String branchCode, String searchBy, String searchValue, String appID){
		return dao.getBrokerListAjax(branchCode, searchBy, searchValue, appID);
	}
	public void deleteProduct(BrokerMgmBean bean) {
		dao.deleteProduct(bean);
	}
	public boolean validateBcode(String brokerCode) {
		boolean result=false;
		try {
			IntegrationService intg = new IntegrationService();
			CustomerDataResponseInfo responseInfo=intg.customerDataService(brokerCode,"search");
			CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
			Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
			if(responseInfo.getOutPut()==null|| custXmlBean==null ||custBean==null){
				result=false;
			}else{
				result=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	public void deleteBroLogo(String agencyCode ){
		dao.deleteBroLogo(agencyCode);
	}
	public int valBraWiseBcode(String brokerCode,String branchCode) {
		return dao.valBraWiseBcode(brokerCode,branchCode);
	}
}