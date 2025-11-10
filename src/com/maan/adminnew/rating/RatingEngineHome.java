package com.maan.adminnew.rating;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RatingEngineHome extends ActionSupport implements ModelDriven<RatingEngineBean>{
	private static final Logger logger = LogUtil.getLogger(RatingEngineHome.class);
	private static final long serialVersionUID = 1L;
	RatingEngineService rservice=new RatingEngineService();
	RatingEngineBean bean=new RatingEngineBean();
	Map<String, Object> session=ActionContext.getContext().getSession();
	String branchCode=(String)session.get("BranchCode");
	private List<Map<String, Object>> transportList;
	private List<Map<String, Object>> conveyanceList;
	private List<Map<String, Object>> countryList;
	private List<Map<String, Object>> bankList;
	private List<Map<String, Object>> materialList;
	private List<Map<String, Object>> warrateList;
	private List<Map<String, Object>> saletermList;
	private List<Map<String, Object>> toleranceList;
	private List<Map<String, Object>> comExList;
	private List<Map<String, Object>> vesselList;
	private List<Map<String, Object>> extraCoverList;
	private List<Map<String, Object>> cityList;
	private List<Map<String, Object>> warrantyList;
	private List<Map<String, Object>> constantList;
	private List<Map<String, Object>> countryDetList;
	private List<Map<String, Object>> coverages;
	private List<Map<String, Object>> covey;
	private List<Map<String, Object>> commodityList;
	private List<Map<String, Object>> currencyList;
	private List<Map<String, Object>> exchangeList;
	private List<Map<String, Object>> coverList;
	private List<Map<String, Object>> exclusionList;
	private List<Map<String, Object>> errorList;
    private List<Map<String, Object>> clauseIDList;
	public List<Map<String, Object>> getExclusionList()
	{
		return exclusionList;
	}
public String getExList()
	{
		exclusionList=rservice.getExclusionList(branchCode);
		return "exclusion";
	}
public String exclusion()
	{
		exclusionList=rservice.getExclusionList(branchCode);
		return "exclusion";
	}

	public List<Map<String, Object>> getCoverList() {
		return coverList;
	}
	public List<Map<String, Object>> getExchangeList() {
		return exchangeList;
	}
	public List<Map<String, Object>> getCurrencyList() {
		return currencyList;
	}
	public List<Map<String, Object>> getCommodityList() {
		return commodityList;
	}
	public List<Map<String, Object>> getCovey() {
		return covey;
	}
	public List getBaserateCountry() {
		return rservice.getBaserateCountry();
	}
	public List getBaserateCommodity() {
		return rservice.getBaserateCommodity(branchCode);	
	}
	public List<Map<String, Object>> getCoverages() {
		return coverages;
	}
	
	public List<Map<String, Object>> getCountryDetList() {
		return countryDetList;
	}
	public List<Map<String, Object>> getConstantList() {
		return constantList;
	}
	public List<Map<String, Object>> getCityList()
	{
		return cityList;
	}
	public List<Map<String, Object>> getExtraCoverList()
	{
		return extraCoverList;
	}
	
	public List<Map<String, Object>> getWarrantyList() {
		return warrantyList;
	}
	public List<Map<String, Object>> getVesselList() {
		return vesselList;
	}
	
	public List<Map<String, Object>> getComExList() {
		return comExList;
	}
	
	public List<Map<String, Object>> getToleranceList() {
		return toleranceList;
	}
	
	public List<Map<String, Object>> getSaletermList() {
		return saletermList;
	}
	
	public List<Map<String, Object>> getWarrateList() {
		return warrateList;
	}
	
    public List<Map<String, Object>> getBankList()
    {
    	return bankList;
    }
    public List<Map<String, Object>> getMaterialList()
    {
    	return materialList;
    }
	
	public List<Map<String, Object>> getCountryList()
	{
		return countryList;
	}
	
	public List<Map<String, Object>> getConveyanceList() {
		return conveyanceList;
	}
	
	public List<Map<String, Object>> getTransportList()
	{
		return transportList;
	}
	public List <Map<String, Object>> getConveyance()
	{
		return rservice.getConveyance(branchCode);
	}
	public List <Map<String, Object>> getTransports()
	{
		return rservice.getTransports(branchCode);
	}
	
	public List <Map<String, Object>> getCategories()
	
	{
		return rservice.getCategories(branchCode);
	}
	public List <Map<String, Object>> getCountries()
	{
		return rservice.getCountries(branchCode);
	}
	
	public List<Map<String, Object>> getBank()
	{
		return rservice.getBank();
	}
	public List<Map<String, Object>> getBranch()
	{
		return rservice.getBranch();
	}
	public List<Map<String, Object>> getWarrates()
	{
		return rservice.getWarrates(branchCode);
	}
	public List<Map<String, Object>> getMaterials()
	{
		return rservice.getMaterials(branchCode);
	}
	public List<Map<String, Object>> getSaleterms()
	{
		return rservice.getSaleterms(branchCode);
	}
	public String conveyance()
	{
		conveyanceList=rservice.getConveyList(branchCode);
		return "conveyanceList";
	}
	public String countrymas()
	{
		countryList=rservice.getCountryList();
		return "countryList";
	}
	public String country()
	{
		countryDetList=rservice.getCountryDetList();
		return "country";
	}
	public String commodity()
	{
		Object[] val={branchCode,branchCode};
		commodityList=rservice.getCommodityList(val);
		return "commodity";
	}
	public String bank()
	{
		bankList=rservice.getBankList();
		return "bankList";
	}
	public String material()
	{
		materialList=rservice.getMaterialList(branchCode);
		return "material";
	}
	public String warrate()
	{
		warrateList=rservice.getWarrateList(branchCode);
		return "warrate";
	}
	public String saleterm()
	{
		saletermList=rservice.getSaletermList(branchCode);
		return "saleterm";
	}
	public String tolerance()
	{
		toleranceList=rservice.getToleranceList(branchCode);
		return "tolerance";
	}
	public String commodityexcess()
	{
		comExList=rservice.getComExList(branchCode);
		return "commodityexcess";
	}
	public String vessel()
	{
		vesselList=rservice.getVesselList();
		return "vessel";
	}
	public String setting()
	{
		agentList=rservice.getAgentList(branchCode);
		return "setting";
	}
	public String exchange()
	{
		exchangeList=rservice.getExchangeList();
		return "exchange";
	}
	public String baserate()
	{
		return "baserate";
	}
	public String currency()
	{
		currencyList=rservice.getCurrencyList();
		return "currency";
	}
	public String extracover()
	{
		extraCoverList=rservice.getExtraCoverList(branchCode);
		return "extracover";
	}
	
	public String transport()
	{
		transportList=rservice.getAdminTransportList(branchCode);
		return "transport";
	}
	public String warranty()
	{
		warrantyList=rservice.getWarrantyList(branchCode);
		return "warranty";
	}
	public String constant()
	{
		constantList=rservice.getConstantList(branchCode);
		return "constant";
	}
	
	public String city()
	{
		cityList=rservice.getCityList(bean);
		return "city";
	}
	public String clauseid()
	{
		clauseIDList=rservice.getClauseIDList(branchCode);
		return "clauseid";
	}
	public String cover()
	{
		Object[] val={branchCode,branchCode};
		coverList=rservice.getCoverList(val);
		return "cover";
	}
	public String error()
	{
		errorList=rservice.getErrorList(branchCode);
		return "error";
	}
	public String editTransport()
	{
		return "editTransport";
	}
	public String addError()
	{
		return "addError";
	}
	public String getTransportListAjax(){
    	if("transportLists".equals(bean.getReqFrom())){
    		transportList=rservice.getTransportListAjax(branchCode, bean.getSearchBy(), bean.getSearchValue());
    	}
    	return "adminAjax";
    }
	public String getTList(){
    	logger.info("ENTER-->Method to getTList");
    	transportList=rservice.getAdminTransportList(branchCode);
		
    	return "transportList";
    }
	public String getConveyList()
	{
		conveyanceList=rservice.getConveyList(branchCode);
		return "conveyanceList";
	}
	public String getCList()
	{
		countryList=rservice.getCountryList();
		return "countryList";
	}
	public String getBList()
	{
		bankList=rservice.getBankList();
		return "bankList";
	}
	public String getMList()
	{
		materialList=rservice.getMaterialList(branchCode);
		return "material";
	}
	public String getWList()
	{
		warrateList=rservice.getWarrateList(branchCode);
		return "warrate";
	}
	public String getSaleList()
	{
		saletermList=rservice.getSaletermList(branchCode);
		return "saleterm";
	}
	public String getToleList()
	{
		toleranceList=rservice.getToleranceList(branchCode);
		return "tolerance";
	}
	public String getCEList()
	{
		comExList=rservice.getComExList(branchCode);
		return "commodityexcess";
	}
	public String getVList()
	{
		vesselList=rservice.getVesselList();
		return "vessel";
	}
	public String getECoverList()
	{
		extraCoverList=rservice.getExtraCoverList(branchCode);
		return "extracover";
	}
	public String getERRList()
	{
		errorList=rservice.getErrorList(branchCode);
		return "error";
	}
	public String getCitysList()
	{
		cityList=rservice.getCityList(bean);
		return "city";
	}
	public String getWRList()
	{
		warrantyList=rservice.getWarrantyList(branchCode);
		return "warranty";
	}
	public String getCONList()
	{
		constantList=rservice.getConstantList(branchCode);
		return "constant";
	}
	public String getCDList()
	{
		countryDetList=rservice.getCountryDetList();
		return "country";
	}
	public String getComList()
	{
		Object[] val={branchCode,branchCode};
		commodityList=rservice.getCommodityList(val);
		return "commodity";
	}
	public String getCURList()
	{
		currencyList=rservice.getCurrencyList();
		return "currency";
	}
	public String getEXList()
	{
		exchangeList=rservice.getExchangeList();
		return "exchange";
	}
	public String getCOVList()
	{
		Object[] val={branchCode,branchCode};
		coverList=rservice.getCoverList(val);
		return "cover";
	}
private List<Map<String, Object>> agentList;
	
	public List<Map<String, Object>> getAgentList()
	{
		return agentList;
	}


public String getSAgentList()
	{
		agentList=rservice.getAgentList(branchCode);
		return "setting";
	}
	
	public RatingEngineBean getModel() {
		
		return bean;
	}
	public void setClauseIDList(List clauseIDList)
    {
        this.clauseIDList = clauseIDList;
    }

    public List getCoverName()
    {
        throw new Error("Unresolved compilation problem: \n\tType mismatch: cannot convert from List<String> to List<Map<String, Object>>\n");
    }

    public List getClauseIDList()
    {
        return clauseIDList;
    }
    
    public List<Map<String, Object>> getErrorList()
    {
        return errorList;
    }
	

}
