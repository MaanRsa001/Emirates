package com.maan.quotation;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.maan.common.DBConstants;
import com.maan.common.LogUtil;
import com.maan.common.Validation;
import com.maan.integration.CustXmlBean;
import com.maan.integration.Customer;
import com.maan.integration.IntegrationService;
import com.maan.quotation.service.QuotationService;
import com.maan.webservice.dao.PolicyGenerationDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QuotationAction extends ActionSupport{
	private static final Logger logger = LogUtil.getLogger(QuotationAction.class);
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	QuotationService service=new QuotationService();
	Validation validation=new Validation();
	private static final long serialVersionUID = 1L;
	private static final String DROPDOWN = "dropdown";
	private static final String STREAM = "stream";
	private static final String COMMODITY = "commodity";
	private static final String FIELD = "ELEMENT_NAME";
	private String ADMIN=getText("admin");
	private String oneOff=getText("ONE_OFF");
	private String openCover=getText("OPEN_COVER");
	private String brokerOne=getText("BROKER_ONE");
	private String branchCode=(String)session.get("LoginBranchCode");
	private String belongingBranch=(String)session.get("BelongingBranch");
	private String actualBranch=(String)session.get("adminBranch");
	private String productId=(String) session.get("product_id");
	private String brokerCode=(String) session.get("brokerCode");
	private String loginId=(String)session.get("user");
	private String issuer=(String)session.get("RSAISSUER");
	private String userType=(String)session.get("user1");
	private String orgUserType=(String)session.get("usertype");
	private String applicationNo;
	private String refNo;
	private String openCoverNo=(String)session.get("openCoverNo");
	private String title;
	private String customerName;
	private String coreAppCode;
	private String city;
	private String poBox;
	private String mobileNo;
	private String modeOfTransport;
	private String cover;
	private String conveyance;
	private String originCountry;
	private String originCity;
	private String originWarehouse;
	private String destCountry;
	private String destCity;
	private String destWarehouse;
	private String policyStartDate;
	private String currency;
	private String warSrcc;
	private String commodity;
	private String totalCommodity;
	private String totalSI;
	private String saleTerm;
	private String saleTermPercent;
	private String saleTermDecVal;
	private String tolerance;
	private String lcNo;
	private String lcBank;
	private String lcAmount;
	private String lcDate;
	/*private String lcCurrency;
	private String lcexchageValue;
	private String lcId;*/
	private String blNo;
	private String blDate;
	private String vesselName;
	public String getVesselImoNo() {
		return vesselImoNo;
	}
	public void setVesselImoNo(String vesselImoNo) {
		this.vesselImoNo = vesselImoNo;
	}
	private String vesselImoNo;
	private String partialShipment;
	private String packageCode;
	private String settlingAgent;
	private String agentOthers;
	private String sailingDate;
	private String brokerOriginCountryId;
	private String quoteType;
	private String originCityName;
	private String destCityName;
	private String quoteNo;
	private String option;
	private String address1;
	private String address2;
	private String email;
	private String customerId;
	private String executive;
	private String custContractNo;
	private String custFmsCaseNo;
	private String custRefNo;
	private String brokerType;
	private String quoteStatus;
	public String getCommoditygroupId() {
		return commoditygroupId;
	}
	public void setCommoditygroupId(String commoditygroupId) {
		this.commoditygroupId = commoditygroupId;
	}
	private String commoditygroupId;
	private InputStream inputStream;
	private List<String> selectCommodity;
	private List<String> commodityId;
	private List<String> commodityDesc;
	private List<String> insuredValue;
	private List<String> supplierName;
	private List<String> commodityPackDesc;
	private List<String> invoiceNo;
	private List<String> invoiceDate;
	private Map<Integer, Boolean> fragile;
	private List<Map<String, Object>> commodityList;
	private List<Map<String, Object>> chosenCommList;
	
	private String countryId;
	private String countrySelect;
	private String searchValue;
	private String openCustomerId;
	private String shipmentExposure;
	private String exposureCurrency;
	private String brokerName;
	private String custArNo;
	private String vesselId;
	private String endTypeId;
	private String fields;
	private String policyNo;
	private String custName;
	private String brokerCompany;
	private String endtType;
	private String searcyFrom;
	private String searchBy;
	private String pid;
	private String consigneeDetail;
	private String specialTerm;
	private String addCustomerName;
	private String promotionalCode;
	private String coreCustomerName;
	private String nameAndCode;
	private String status1;
	private String status2;
	private String commodityCode;
	private String category;
	private String channelType;
	private String coreSearchValue;
	private String exchageValue;
	private String custVatRegNo;
	private String custVatRegYn;
	private String vatApplicable;
	private String vatApplicableNone;
	
	private String commSelTotalCommodity;
	private String commSelTotalSI;
	private String commSelcommodity;
	private String shipmenttransit;
	private List<Map<String, Object>> vesselList;
	
	List<Map<String, Object>> lcList = new ArrayList<Map<String, Object>>();
	private List<Map<String, Object>> viewList = new ArrayList<Map<String,Object>>();
	
	public String getCoreCustomerName() {
		return coreCustomerName;
	}
	public void setCoreCustomerName(String coreCustomerName) {
		this.coreCustomerName = coreCustomerName;
	}
	public String getNameAndCode() {
		return nameAndCode;
	}
	public void setNameAndCode(String nameAndCode) {
		this.nameAndCode = nameAndCode;
	}
	public String getConsigneeDetail() {
		return consigneeDetail;
	}
	public void setConsigneeDetail(String consigneeDetail) {
		this.consigneeDetail = consigneeDetail;
	}
	public String getSpecialTerm() {
		return specialTerm;
	}
	public void setSpecialTerm(String specialTerm) {
		this.specialTerm = specialTerm;
	}
	public String getAddCustomerName() {
		return addCustomerName;
	}
	public void setAddCustomerName(String addCustomerName) {
		this.addCustomerName = addCustomerName;
	}
	public String getPromotionalCode() {
		return promotionalCode;
	}
	public void setPromotionalCode(String promotionalCode) {
		this.promotionalCode = promotionalCode;
	}
	public String getSearcyFrom() {
		return searcyFrom;
	}
	public void setSearcyFrom(String searcyFrom) {
		this.searcyFrom = searcyFrom;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	/*public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}*/
	public String getEndtType() {
		return endtType;
	}
	public String getBrokerCompany() {
		return brokerCompany;
	}
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getShipmenttransit() {
		return shipmenttransit;
	}
	public void setShipmenttransit(String shipmenttransit) {
		this.shipmenttransit = shipmenttransit;
	}
	public String getFields() {
		if(StringUtils.isNotEmpty(endTypeId)){
			String[] ids=endTypeId.split(",");
			if(ArrayUtils.isNotEmpty(ids)){
				fields=getText("endt")+",";
				for (int i = 0; i < ids.length; i++) {
					fields+=getText("endt"+ids[i])+",";
				}
				fields=fields.substring(0, fields.lastIndexOf(","));
			}
		}
		return fields;
	}
	public String getEndTypeId() {
		return endTypeId;
	}
	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}
	public String getCustArNo() {
		return custArNo;
	}
	public void setCustArNo(String custArNo) {
		this.custArNo = custArNo;
	}
	public String getVesselId() {
		return vesselId;
	}
	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getShipmentExposure() {
		return shipmentExposure;
	}
	public void setShipmentExposure(String shipmentExposure) {
		this.shipmentExposure = shipmentExposure;
	}
	public String getExposureCurrency() {
		return exposureCurrency;
	}
	public void setExposureCurrency(String exposureCurrency) {
		this.exposureCurrency = exposureCurrency;
	}
	public String getOpenCustomerId() {
		return openCustomerId;
	}
	public void setOpenCustomerId(String openCustomerId) {
		this.openCustomerId = openCustomerId;
		session.put("customer_id", openCustomerId);
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getQuoteStatus() {
		return quoteStatus;
	}
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountrySelect() {
		return countrySelect;
	}
	public void setCountrySelect(String countrySelect) {
		this.countrySelect = countrySelect;
	}
	public String getBrokerOne() {
		return brokerOne;
	}
	public String getIssuer() {
		return issuer;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getCustContractNo() {
		return custContractNo;
	}
	public void setCustContractNo(String custContractNo) {
		this.custContractNo = custContractNo;
	}
	public String getCustFmsCaseNo() {
		return custFmsCaseNo;
	}
	public void setCustFmsCaseNo(String custFmsCaseNo) {
		this.custFmsCaseNo = custFmsCaseNo;
	}
	public String getCustRefNo() {
		return custRefNo;
	}
	public void setCustRefNo(String custRefNo) {
		this.custRefNo = custRefNo;
	}
	public void setLoginId(String loginId) {
		if(!StringUtils.isBlank(loginId)){
			this.loginId = loginId;
		}
	}
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public List<Map<String, Object>> getCustomerSelection() {
		if(StringUtils.isNotBlank(coreSearchValue)) {
			return getCoreCustomerSearch(coreSearchValue);
		}
		else if(StringUtils.isNotEmpty(brokerName)){
			return service.getCustomerSelectionList(brokerName,searchValue,openCoverNo);
		}else{
			return service.getCustomerSelectionList(loginId,searchValue,openCoverNo);
		}
	}
	private List<Map<String, Object>> getCoreCustomerSearch(String coreSearchValue) {
		List<Map<String, Object>> customerSearchList=null;
		IntegrationService intg = new IntegrationService();
		CustomerDataResponseInfo responseInfo=intg.customerDataService(coreSearchValue,"search");
		if(responseInfo.getOutPut()!=null){
			CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
			Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
			customerSearchList=new ArrayList<Map<String, Object>>();
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("MISSIPPI_CUSTOMER_CODE", coreSearchValue);
			map.put("CUST_NAME", custBean.getCustName());
			map.put("FIRST_NAME", custBean.getCustName());
			map.put("ADDRESS1", custBean.getCustResAddress1());
			map.put("ADDRESS2", custBean.getCustResAddress2());
			map.put("VAT_REG_NO", custBean.getCustVatRegNo()==null?"":custBean.getCustVatRegNo());
			map.put("CUST_VAT_YN", custBean.getCustVatApplYn()==null?"N":"1".equalsIgnoreCase(custBean.getCustVatApplYn())?"Y":"N");
			map.put("CUSTOMER_ID", "0");
			//setCustVatRegNo(custBean.getCustVatRegNo()==null?"":custBean.getCustVatRegNo());
			//setCustVatRegYn(custBean.getCustVatRegNo()==null?"":custBean.getCustVatRegNo());
			customerSearchList.add(map);
		}
			
		
		return customerSearchList;
	}
	public List<Map<String, Object>> getLcSelectionList() {
		return service.lcSelectionList((String)session.get("openCoverNo"), searchValue,"");
	}
	/*public String getLcexchageValue() {
		return lcexchageValue;
	}
	public void setLcexchageValue(String lcexchageValue) {
		this.lcexchageValue = lcexchageValue;
	}*/
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getSaleTermDecVal() {
		return saleTermDecVal;
	}
	public void setSaleTermDecVal(String saleTermDecVal) {
		this.saleTermDecVal = saleTermDecVal;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	/*public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}*/
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getOneOff() {
		return oneOff;
	}
	public String getOpenCover() {
		return openCover;
	}
	public String getSaleTermPercent() {
		return saleTermPercent;
	}
	public void setSaleTermPercent(String saleTermPercent) {
		this.saleTermPercent = saleTermPercent;
	}
	public String getTolerance() {
		return tolerance;
	}
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getOriginCityName() {
		return originCityName;
	}
	public void setOriginCityName(String originCityName) {
		this.originCityName = originCityName;
	}
	public String getDestCityName() {
		return destCityName;
	}
	public void setDestCityName(String destCityName) {
		this.destCityName = destCityName;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public void setCommodityList(List<Map<String, Object>> commodityList) {
		this.commodityList = commodityList;
	}
	public List<String> getSelectCommodity() {
		return selectCommodity;
	}
	public void setSelectCommodity(List<String> selectCommodity) {
		this.selectCommodity = selectCommodity;
	}
	public List<String> getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(List<String> commodityId) {
		this.commodityId = commodityId;
	}
	public List<String> getInsuredValue() {
		return insuredValue;
	}
	public void setInsuredValue(List<String> insuredValue) {
		this.insuredValue = insuredValue;
	}
	public List<String> getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(List<String> supplierName) {
		this.supplierName = supplierName;
	}
	public List<String> getCommodityPackDesc() {
		return commodityPackDesc;
	}
	public void setCommodityPackDesc(List<String> commodityPackDesc) {
		this.commodityPackDesc = commodityPackDesc;
	}
	public List<String> getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(List<String> invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public Map<Integer, Boolean> getFragile() {
		return fragile;
	}
	public void setFragile(Map<Integer, Boolean> fragile) {
		this.fragile = fragile;
	}
	public List<String> getCommodityDesc() {
		return commodityDesc;
	}
	public void setCommodityDesc(List<String> commodityDesc) {
		this.commodityDesc = commodityDesc;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public String getPackageCode() {
		return packageCode;
	}
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	public String getBrokerOriginCountryId() {
		return brokerOriginCountryId;
	}
	public void setBrokerOriginCountryId(String brokerOriginCountryId) {
		this.brokerOriginCountryId = brokerOriginCountryId;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
		if(StringUtils.isNotEmpty(openCoverNo)){
			session.put("openCoverNo", openCoverNo);
		}
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public String getProductId() {
		return productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCoreAppCode() {
		return coreAppCode;
	}
	public void setCoreAppCode(String coreAppCode) {
		this.coreAppCode = coreAppCode;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getConveyance() {
		return conveyance;
	}
	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}
	public String getOriginCountry() {
		return originCountry;
	}
	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}
	public String getOriginCity() {
		return originCity;
	}
	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}
	public String getOriginWarehouse() {
		return originWarehouse;
	}
	public void setOriginWarehouse(String originWarehouse) {
		this.originWarehouse = originWarehouse;
	}
	public String getDestCountry() {
		return destCountry;
	}
	public void setDestCountry(String destCountry) {
		this.destCountry = destCountry;
	}
	public String getDestCity() {
		return destCity;
	}
	public void setDestCity(String destCity) {
		this.destCity = destCity;
	}
	public String getDestWarehouse() {
		return destWarehouse;
	}
	public void setDestWarehouse(String destWarehouse) {
		this.destWarehouse = destWarehouse;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getWarSrcc() {
		return warSrcc;
	}
	public void setWarSrcc(String warSrcc) {
		this.warSrcc = warSrcc;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getTotalCommodity() {
		return totalCommodity;
	}
	public void setTotalCommodity(String totalCommodity) {
		this.totalCommodity = totalCommodity;
	}
	public String getTotalSI() {
		return totalSI;
	}
	public void setTotalSI(String totalSI) {
		this.totalSI = StringUtils.isBlank(totalSI)?"":totalSI.replaceAll(",", "");
	}
	public String getSaleTerm() {
		return saleTerm;
	}
	public void setSaleTerm(String saleTerm) {
		this.saleTerm = saleTerm;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getLcBank() {
		return lcBank;
	}
	public void setLcBank(String lcBank) {
		this.lcBank = lcBank;
	}
	public String getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(String lcAmount) {
		this.lcAmount = lcAmount;
	}
	public String getLcDate() {
		return lcDate;
	}
	public void setLcDate(String lcDate) {
		this.lcDate = lcDate;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getBlDate() {
		return blDate;
	}
	public void setBlDate(String blDate) {
		this.blDate = blDate;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getPartialShipment() {
		return partialShipment;
	}
	public void setPartialShipment(String partialShipment) {
		this.partialShipment = partialShipment;
	}
	public String getSettlingAgent() {
		return settlingAgent;
	}
	public void setSettlingAgent(String settlingAgent) {
		this.settlingAgent = settlingAgent;
	}
	public String getAgentOthers() {
		return agentOthers;
	}
	public void setBrokerType(String brokerType) {
		this.brokerType = brokerType;
	}
	public void setAgentOthers(String agentOthers) {
		this.agentOthers = agentOthers;
	}
	public String getSailingDate() {
		return sailingDate;
	}
	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setLcList(List<Map<String, Object>> lcList) {
		this.lcList = lcList;
	}
	public String getWarOption() {
		if(openCover.equals(productId)){
			return service.getSingleInfo("warOption",new String[]{openCoverNo,openCoverNo});
		}else{
			return "Y";
		}
	}
	public String getBrokerType() {
		return service.getSingleInfo("brokerType",new String[]{loginId});
	}
	
	public Object[] getParams()
	{
	Object[] objects=new String[]{option,productId,belongingBranch,openCoverNo,modeOfTransport,cover,originCountry,
			destCountry,saleTerm,saleTermPercent,lcBank,issuer,brokerCode};
		return objects;
	}
	public List<Map<String, Object>> getTitleList()
	{
		return service.getOptionsList("title", getParams());
	}
	public List<Map<String, Object>> getCityList()
	{
		return service.getOptionsList("city", getParams());
	}
	public List<Map<String, Object>> getOrgCityList()
	{
		return service.getOptionsList("orgCity", getParams());
	}
	public List<Map<String, Object>> getDestCityList()
	{
		return service.getOptionsList("destCity", getParams());
	}
	public List<Map<String, Object>> getModeList()
	{
		return service.getOptionsList("mode", getParams());
	}
	public List<Map<String, Object>> getCoverList()
	{
		return service.getOptionsList("cover", getParams());
	}
	public List<Map<String, Object>> getConveyanceList()
	{
		return service.getOptionsList("conveyance", getParams());
	}
	public List<Map<String, Object>> getOriginList()
	{
		return service.getOptionsList("originCountry", getParams());
	}
	public List<Map<String, Object>> getDestList()
	{
		return service.getOptionsList("destCountry", getParams());
	}
	public List<Map<String, Object>> getCurrencyList()
	{
		return service.getOptionsList("currency", getParams());
	}
	public List<Map<String, Object>> getShipmentTransitList()
	{
		return service.getOptionsList("shipmentTransit", getParams());
	}
	public List<Map<String, Object>> getSaleTermList()
	{
		return service.getOptionsList("saleTerm", getParams());
	}
	public List<Map<String, Object>> getPercentList()
	{
		return service.getOptionsList("saleTermPercent", getParams());
	}
	public List<Map<String, Object>> getToleranceList()
	{
		return service.getOptionsList("tolerance", getParams());
	}
	public List<Map<String, Object>> getLcBankList()
	{
		return service.getOptionsList("lcBank", getParams());
	}
	public List<Map<String, Object>> getPackageList()
	{
		return service.getOptionsList("package", getParams());
	}
	public List<Map<String, Object>> getAgentList()
	{
		String userId = issuer==null?loginId:issuer;
		String qStatus = "0".equals(settlingAgent)?"RA":"";
		Object[] objects=new String[]{option,productId,belongingBranch,openCoverNo,modeOfTransport,cover,originCountry,
				destCountry,saleTerm,saleTermPercent,qStatus,userId,brokerCode};
		return service.getOptionsList("agent", objects);
	}
	public List<Map<String, Object>> getCommodityList()
	{
		return this.commodityList;
	}
	public List<Map<String, Object>> getLcList()
	{
		return service.getOptionsList("lcNo", getParams());
	}
	public List<Map<String, Object>> getBrokerList()
	{
		Object[] objects=new String[]{option,productId,actualBranch,openCoverNo,modeOfTransport,cover,originCountry,
			destCountry,saleTerm,saleTermPercent,lcBank,issuer,brokerCode};
		String option ="broker";
		if(StringUtils.isNotBlank(issuer) && "3".equals(productId)) {
			if(StringUtils.isBlank(channelType)) {
				option = "";
			}
			else if("broker".equalsIgnoreCase(channelType)) {
				option = "BrokerList";
			}
			else {
				option = "WalkinList";
			}
		}
		return service.getOptionsList(option, objects);
	}
	public boolean isDubaiTradeStatus(){
		//return service.getDubaiTradeStatus(brokerCode, branchCode);
		return service.getDubaiTradeStatus(loginId, branchCode);
	}
	/*public List<Map<String, Object>> getBrokerList()
	{
		  return service.getOptionsList("broker", new Object[]{actualBranch,issuer,belongingBranch,productId});
	
	}*/
	public List<Map<String, Object>> getExecutiveList()
	{
		Object[] objects=new String[]{option,productId,actualBranch,openCoverNo,modeOfTransport,cover,originCountry,
				destCountry,saleTerm,saleTermPercent,lcBank,loginId,brokerCode};
			
		return service.getOptionsList("executive", objects);
	}
	public Map<String, Object> getPolicyEndtInfo()
	{
		return service.getPolicyEndtInfo(quoteNo);	
	}
	public Map<String, Object> getOpenCoverInfo()
	{
		return service.getOpenCoverInfo(openCoverNo);	
	}
	public String getDirectStatus() {
		String direct="";
		direct=service.getDirectStatus(applicationNo,branchCode);
		return direct;
	}
	public String newQuote()
	{
		String branchWiseCountry="";
		branchWiseCountry=service.getbranchWiseCountry(actualBranch);
		//originCountry=destCountry="1";	
		if(issuer!=null){
			vatApplicableNone=service.getSingleInfo("GET_VAT_NONE", new String[]{issuer});
		}
		custVatRegYn="NO";
		originCountry=destCountry=branchWiseCountry;	
		if(oneOff.equals(productId))
		{
		brokerCode="";
		}else{
			brokerCode=service.getSingleInfo("broker", new String[]{loginId});
			if("11".equalsIgnoreCase(productId))
				executive=service.getSingleInfo("executive.oc", new String[]{openCoverNo});
			else
				executive=service.getSingleInfo("executive", new String[]{brokerCode});
			
			//Default customer information population
			service.setOpenCustomerInfo(this,openCoverNo);
		}
		return INPUT;
	}
	public String editQuote()
	{
		if(!(quoteNo==null || "".equals(quoteNo) || applicationNo==null || "".equals(applicationNo) || belongingBranch==null || "".equals(belongingBranch))){
			service.updateWSMarineQuote(new String[]{quoteNo, applicationNo, belongingBranch});
		}
		if(issuer!=null){
			vatApplicableNone=service.getSingleInfo("GET_VAT_NONE", new String[]{issuer});
		}
		
		if("admin".equalsIgnoreCase(session.get("user1").toString())&&StringUtils.isNotBlank(pid)){
			session.put("product_id",pid);
			productId=pid;
		}
		service.getQuoteInfo(this);
		endtType=service.getPolicyEndtType(applicationNo);
		return INPUT;
	}
	public String getQuote()
	{
		if(StringUtils.isBlank(saleTermPercent)){
			saleTermPercent=saleTermDecVal;
		}
		if(StringUtils.isBlank(tolerance)){
			tolerance=getText("quotation.toleranceDecVal");
		}
		if(StringUtils.isBlank(coreAppCode) || "0".equals(coreAppCode)) {
			if("Broker".equalsIgnoreCase(orgUserType) || "User".equalsIgnoreCase(orgUserType)) {
				coreAppCode = service.getSingleInfo("GET_RSABROKER_CODE", new String[]{loginId});
			}
			else if("RSAIssuer".equalsIgnoreCase(orgUserType)) {
				String loginId = service.getSingleInfo("loginId", new String[]{brokerCode});
				coreAppCode = service.getSingleInfo("GET_RSABROKER_CODE", new String[]{loginId});
			}
			
		}
		validateQuoteInfo();
		if(!hasActionErrors())
		{
			String args[]=new String[0];
			quoteType=service.getSingleInfo(DBConstants.QUOTE_COUNT, new String[]{StringUtils.defaultIfEmpty(applicationNo, ""),StringUtils.defaultIfEmpty(quoteNo, "")});
			if("admin".equalsIgnoreCase((String)session.get("user1"))){
			
			}else if(StringUtils.isBlank(issuer)){
				brokerCode=service.getSingleInfo("broker", new String[]{loginId});
				if(StringUtils.isBlank(executive)){
					if("11".equalsIgnoreCase(productId))
						executive=service.getSingleInfo("executive.oc", new String[]{openCoverNo});
					else
						executive=service.getSingleInfo("executive", new String[]{brokerCode});
				}
			}else{
				loginId=service.getSingleInfo("loginId", new String[]{brokerCode});
			}
			if(StringUtils.isBlank(vesselName)){
				vesselName=getText("quotation.vessel.mode"+modeOfTransport);
			}
			if("F".equalsIgnoreCase(quoteType)){
//				args = new String[]{brokerCode,applicationNo,quoteType,openCoverNo,customerName,poBox,city,mobileNo,
//						modeOfTransport,originCountry,destCountry,originCity,destCity,originCityName,destCityName,originWarehouse,
//						destWarehouse,saleTermPercent,currency,settlingAgent,policyStartDate,sailingDate,vesselName,lcNo,lcBank,lcDate,
//						partialShipment,blNo,blDate,cover,conveyance,warSrcc,tolerance,branchCode,productId,packageCode,
//						saleTerm,address1,address2,email,customerId,title,coreAppCode,custContractNo,custFmsCaseNo,custRefNo,
//						loginId,issuer,agentOthers,executive,shipmentExposure,exposureCurrency,custArNo,vesselId,applicationNo,
//						promotionalCode, consigneeDetail, specialTerm, addCustomerName, coreCustomerName/*lcAmount, lcCurrency, lcexchageValue*/};
				args = new String[]{brokerCode,applicationNo,quoteType,openCoverNo,customerName,poBox,city,mobileNo,
						modeOfTransport,originCountry,destCountry,originCity,destCity,originCityName,destCityName,originWarehouse,
						destWarehouse,saleTermPercent,currency,settlingAgent,policyStartDate,sailingDate,vesselName,vesselImoNo,lcNo,lcBank,lcDate,
						partialShipment,blNo,blDate,cover,conveyance,warSrcc,tolerance,belongingBranch,productId,packageCode,
						saleTerm,address1,address2,email,customerId,title,coreAppCode,custContractNo,custFmsCaseNo,custRefNo,
						loginId,issuer,agentOthers,executive,shipmentExposure,exposureCurrency,custArNo,vesselId,applicationNo,
						promotionalCode, consigneeDetail, specialTerm, addCustomerName, coreCustomerName/*lcAmount, lcCurrency, lcexchageValue*/,issuer==null?"broker":channelType,exchageValue,custVatRegNo,custVatRegYn==null?"":custVatRegYn,vatApplicable==null?"":vatApplicable,shipmenttransit};
			}else if("M".equalsIgnoreCase(quoteType)){
				if(ADMIN.equalsIgnoreCase(userType)){
					issuer=service.getQuoteIssuer(applicationNo);
				}
//				args = new String[]{quoteType,customerName,poBox,city,mobileNo,
//						modeOfTransport,originCountry,destCountry,originCity,destCity,originCityName,destCityName,originWarehouse,
//						destWarehouse,saleTermPercent,currency,settlingAgent,policyStartDate,sailingDate,vesselName,lcNo,lcBank,lcDate,
//						partialShipment,blNo,blDate,cover,conveyance,warSrcc,tolerance,packageCode,
//						saleTerm,address1,address2,email,customerId,title,coreAppCode,custContractNo,custFmsCaseNo,
//						custRefNo,agentOthers,shipmentExposure,exposureCurrency,executive,custArNo,vesselId,issuer,
//						promotionalCode, consigneeDetail, specialTerm, addCustomerName, coreCustomerName,/*lcAmount,lcCurrency, lcexchageValue*/ applicationNo};
				args = new String[]{quoteType,customerName,poBox,city,mobileNo,
						modeOfTransport,originCountry,destCountry,originCity,destCity,originCityName,destCityName,originWarehouse,
						destWarehouse,saleTermPercent,currency,settlingAgent,policyStartDate,sailingDate,vesselName,vesselImoNo,lcNo,lcBank,lcDate,
						partialShipment,blNo,blDate,cover,conveyance,warSrcc,tolerance,packageCode,
						saleTerm,address1,address2,email,customerId,title,coreAppCode,custContractNo,custFmsCaseNo,
						custRefNo,agentOthers,shipmentExposure,exposureCurrency,executive,custArNo,vesselId,issuer,
						promotionalCode, consigneeDetail, specialTerm, addCustomerName, coreCustomerName,exchageValue,issuer==null?"broker":channelType,custVatRegNo,custVatRegYn==null?"":custVatRegYn,vatApplicable,shipmenttransit,StringUtils.isBlank(quoteNo)?"":quoteNo,/*lcAmount,lcCurrency, lcexchageValue*/ applicationNo};
			}
			service.getQuote(applicationNo, args, quoteType, quoteStatus, userType, StringUtils.defaultIfEmpty(refNo, applicationNo));
			return SUCCESS;
		}else
		{
			return INPUT;
		}
	}
	
	public String coverList()
	{
		request.setAttribute(FIELD, "cover");
		return DROPDOWN;
	}
	public String conveyanceList()
	{
		request.setAttribute(FIELD, "conveyance");
		return DROPDOWN;
	}
	public String packageList()
	{
		request.setAttribute(FIELD, "package");
		return DROPDOWN;
	}
	public String agentList()
	{
		request.setAttribute(FIELD, "agent"); 
		return DROPDOWN;
	}
	public String percentList()
	{
		request.setAttribute(FIELD, "saleTermPercent"); 
		return DROPDOWN;
	}
	public String toleranceList()
	{
		request.setAttribute(FIELD, "tolerance"); 
		return DROPDOWN;
	}
	public String lcList()
	{
		request.setAttribute(FIELD, "lcNo"); 
		return DROPDOWN;
	}
	public String brokersList()
	{
		request.setAttribute(FIELD, "brokersList"); 
		return DROPDOWN;
	}
	public String executiveList()
	{
		request.setAttribute(FIELD, "executive"); 
		return DROPDOWN;
	}
	public String promotionalList(){
		request.setAttribute(FIELD, "promotionalList"); 
		return DROPDOWN;
	} 
	public String vesselList()
	{
		vesselList = service.getVesselList(vesselName);
		return "vesselList";	
	}
	public List<Map<String, Object>> getGoodsCategoryList()
	{
		if (StringUtils.isNotBlank(commoditygroupId) || "11".equals(productId)) {
			Object[] objects = new String[] { option, productId, belongingBranch, openCoverNo, modeOfTransport, cover,
					originCountry, destCountry, saleTerm, saleTermPercent, lcBank, issuer, commoditygroupId };
			return service.getOptionsList("goodsCategory", objects);
		} else {
			return new ArrayList<Map<String, Object>>();
		}
	}
	public String countryCityList()
	{
		return "citySelection";
	}
	public String vessel()
	{	
		return "vesselSelection";	
	}
	public List<Map<String,Object>> getGoodsCategoryGroupList(){
		return service.goodsCategoryGroupList();
	}
	/*public String commodityList()
	{
		Object params[]=null;
		if(openCover.equals(productId)){
			params=new Object[]{applicationNo, openCoverNo};
		}else{
			params=new Object[]{applicationNo, belongingBranch};
		}
		this.commodityList=service.getOptionsList(openCover.equals(productId)?"commodity_oc":COMMODITY, params);
		return COMMODITY;
	}*/
	public String commodityList()
	{
		/*Object params[]=null;
		if(openCover.equals(productId)){
			params=new Object[]{applicationNo, openCoverNo};
		}else{
			params=new Object[]{applicationNo, branchCode};
		}
		this.commodityList=service.getOptionsList(openCover.equals(productId)?"commodity_oc":COMMODITY, params);
		return COMMODITY;*/


		/*Object params[]=null;
		if(openCover.equals(productId)){
			params=new Object[]{applicationNo, openCoverNo};
		}else{
			params=new Object[]{applicationNo, branchCode};
		}*/
		if(StringUtils.isBlank(applicationNo)){
			this.commodityList=new ArrayList<Map<String, Object>>();
		}else{
			viewList=service.searchList(applicationNo,belongingBranch);
			this.commodityList=new ArrayList<Map<String, Object>>();
			//this.commodityList=service.getOptionsList(openCover.equals(productId)?"commodity_oc":COMMODITY, params);
		}
		return COMMODITY;
	}
	public String addCommodity()
	{
		String uploadStatus="";
		String errorShowStatus=getText("error.show.status");
		try {
			if("submit".equalsIgnoreCase(status1) || "addmore".equalsIgnoreCase(status1))
				validateGoods();
		} catch (Exception e) {
			logger.debug("Exception @ "+e);
			e.printStackTrace();
		}
		if(!hasActionErrors())
		{
			if("addmore".equalsIgnoreCase(status1)){
				try{
					if(applicationNo==null || "".equals(applicationNo))
					{
						applicationNo = service.getApplicationNo();
					}
					if(!"RA".equalsIgnoreCase(quoteStatus)){
						uploadStatus=service.addCommodity(applicationNo, StringUtils.defaultIfEmpty(refNo, applicationNo), chosenCommList, branchCode);
						status2="";
					}else{
						uploadStatus=service.updateCommodity(applicationNo, chosenCommList);
						status2="";
					}
					if("Fail".equalsIgnoreCase(uploadStatus) && "YES".equalsIgnoreCase(errorShowStatus)){
						addActionError(getText("error.commodity.change"));
					}
					viewList=service.searchList(applicationNo,belongingBranch);
					this.commodityList=new ArrayList<Map<String, Object>>();
				}catch(Exception e){
					e.printStackTrace();
				}
				return COMMODITY;
			}else if("edit".equalsIgnoreCase(status1)){
				try{
					Object params[]=null;
					if(openCover.equals(productId)){
						params=new Object[]{applicationNo,openCoverNo, commodityCode};
					}else{
						params=new Object[]{applicationNo, commodityCode,belongingBranch};
					}
					if(StringUtils.isBlank(applicationNo)){
						this.commodityList=new ArrayList<Map<String, Object>>();
					}else{
						this.commodityList=new ArrayList<Map<String, Object>>();
						this.commodityList=service.getOptionsList(openCover.equals(productId)?"commodity_oc":COMMODITY, params);
						if(!commodityList.isEmpty())
							setCommoditygroupId(commodityList.get(0).get("COMMODITY_GROUP_ID")==null?"":commodityList.get(0).get("COMMODITY_GROUP_ID").toString());


						//category=((Map)commodityList.get(0)).get("commodity_type_id")==null?"":((Map)commodityList.get(0)).get("commodity_type_id").toString();
					}
					viewList=service.searchList(applicationNo,belongingBranch);
					/***Multiple Commodity Removed -- Start ****/
					
					status2 = "edit";
					
					/***Multiple Commodity Removed -- End ****/
				}catch(Exception e){
					e.printStackTrace();
				}
				return COMMODITY;
			}else if("delete".equalsIgnoreCase(status1)){
				try{
					service.deleteCommodity(applicationNo, commodityCode);
					viewList=service.searchList(applicationNo,belongingBranch);
					this.commodityList=new ArrayList<Map<String, Object>>();
				}catch(Exception e){
					e.printStackTrace();
				}
				return COMMODITY;
			}else{
				String forward=COMMODITY;
				try{
					if(applicationNo==null || "".equals(applicationNo))
					{
						applicationNo = service.getApplicationNo();
					}
					if(!"RA".equalsIgnoreCase(quoteStatus) || ADMIN.equalsIgnoreCase(userType)){
						uploadStatus=service.addCommodity(applicationNo, StringUtils.defaultIfEmpty(refNo, applicationNo), chosenCommList, branchCode);
						status2="";
					}else{
						uploadStatus=service.updateCommodity(applicationNo, chosenCommList);
						status2="";
					}
					viewList=service.searchList(applicationNo,belongingBranch);
					Map<String, Object> commInfo=null;
					String commodityName="";
					double commoditySi=0.0;
					double totalImportSI=0.0;
					int commodityNo=viewList.size();
					if (viewList != null && !viewList.isEmpty()) {
						for (int i = 0; i < viewList.size(); i++) {
							commInfo=(Map<String, Object>)viewList.get(i);
							//commodityName+=(commInfo.get("COMMODITY_NAME")==null?"":commInfo.get("COMMODITY_NAME").toString())+',';
							//commodityName+= commInfo.get("COMMODITY_DESCRIPTION")==null?"":commInfo.get("COMMODITY_DESCRIPTION").toString()+',';
							commoditySi+=Double.parseDouble(commInfo.get("SUMINSURED")==null?"0.0":commInfo.get("SUMINSURED").toString());
							totalImportSI=totalImportSI+Double.parseDouble(commInfo.get("IMPORT_DUTY_SUMINSURED")==null?"0.0":commInfo.get("IMPORT_DUTY_SUMINSURED").toString());
						}
						PolicyGenerationDAO policyDAO = new PolicyGenerationDAO();
						Map<String,Object> resultMap = policyDAO.getResultMap(DBConstants.COMMODITY_TOTAL, new String[]{StringUtils.defaultIfEmpty(refNo, applicationNo)});
						commodityName = resultMap.get("COMMODITY_DESCRIPTION")==null?"":resultMap.get("COMMODITY_DESCRIPTION").toString().replaceAll("\\n", " ");
					}
					if("Fail".equalsIgnoreCase(uploadStatus) && "YES".equalsIgnoreCase(errorShowStatus)){
						addActionError(getText("error.commodity.change"));
						forward=COMMODITY;
					}
					setCommSelTotalCommodity((commodityNo==0?"":String.valueOf(commodityNo)));
					setCommSelTotalSI(("0".equals(new DecimalFormat("#.##").format(commoditySi))?"":new DecimalFormat("#.##").format(commoditySi)));
					setCommSelcommodity(commodityName.replace("\n", " ").replace("\r", " "));
					
					/*String value="<script type='text/javascript'>window.opener.quotation.applicationNo.value='"+applicationNo+"';window.opener.quotation.totalCommodity.value='"+(commodityNo==0?"":commodityNo)+"';window.opener.quotation.totalSI.value='"+("0".equals(new DecimalFormat("#.##").format(commoditySi))?"":new DecimalFormat("#.##").format(commoditySi))+"';window.opener.quotation.commodity.value='"+commodityName.replace("\n", " ").replace("\r", " ")+"';window.close();</script>";
					byte[] byteArray = value.getBytes();
					inputStream=new ByteArrayInputStream(byteArray);*/
				}catch(Exception e){
					e.printStackTrace();
				}
				return forward;
			}
		}
		else {
			viewList=service.searchList(applicationNo,belongingBranch);
			return COMMODITY;
		}
	}
	/*public String addCommodity()
	{
		try {
			validateGoods();
		} catch (Exception e) {
			logger.debug("Exception @ "+e);
			e.printStackTrace();
		}
		if(!hasActionErrors())
		{
			if(applicationNo==null || "".equals(applicationNo))
			{
				applicationNo = service.getApplicationNo();
			}
			if(!"RA".equalsIgnoreCase(quoteStatus)){
				service.addCommodity(applicationNo, StringUtils.defaultIfEmpty(refNo, applicationNo), chosenCommList);
			}else{
				service.updateCommodity(applicationNo, chosenCommList);
			}
			String value="<script type='text/javascript'>window.opener.document.getElementById('applicationNo').value='"+applicationNo+"';window.close();</script>";
			byte[] byteArray = value.getBytes();
			inputStream=new ByteArrayInputStream(byteArray);
			return STREAM;
		}else
		{
			return COMMODITY;
		}
	}*/
	public String coreCustomerSearch()
	{
		return "coreCustomer";
	}
	public String customerSelection()
	{
		logger.info("brokerCode"+brokerCode);		
		if(StringUtils.isNotEmpty(brokerCode)){
			brokerName=service.getBrokerLoginId(brokerCode);
		}	
		if(searchValue==null && coreSearchValue==null)
		{
			return "customerSelection";
		}
		else{
			return "customerSearchList";
		}	
	}
	public String lcSelection() {
		logger.info("Enter Method lcSelection");
		if(StringUtils.isBlank(searchValue))
			return "lcSelection";
		else
			return "lcSearchList";
	}
	public String customerPopulate() {
		logger.info("city:"+city);	
		String value="<script type='text/javascript'>window.close();</script>";
		byte[] byteArray = value.getBytes();
		inputStream=new ByteArrayInputStream(byteArray);
		return STREAM;
	}
	
	/*public void validateGoods() throws Exception {
			double total=0.0;
			this.commodityList=new ArrayList<Map<String, Object>>();
			chosenCommList=new ArrayList<Map<String, Object>>();
			Map<String, String> commInfo=null;
			if (selectCommodity==null || selectCommodity.isEmpty() || ((String)selectCommodity.get(0)).equals("false")) {
				addActionError(getText("error.commodity.oneitem"));
			}
			if (commodityId != null && !commodityId.isEmpty()) {
				for (int i = 0; i < commodityId.size(); i++) {
					commInfo=new LinkedHashMap<String, String>();
					commInfo.put("SELECTED", "false");
					commInfo.put("COMMODITY_ID", commodityId.get(i));
					commInfo.put("COMMODITY_NAME", commodityDesc.get(i));
					commInfo.put("SUM_INSURED", insuredValue.get(i));
					commInfo.put("SUPPLIER_NAME", supplierName.get(i));
					commInfo.put("PACKAGE_DESC", commodityPackDesc.get(i));
					commInfo.put("INVOICE_NUMBER", invoiceNo.get(i));
					commInfo.put("FRAGILE", fragile!=null && "true".equals(fragile.get(i)+"")?"on":"off");
					if (selectCommodity!=null && !selectCommodity.isEmpty()) {
						for (int j = 0; j < selectCommodity.size(); j++) {
							if (commodityId.get(i).equals(selectCommodity.get(j))) {
								commInfo.put("SELECTED", "true");
								commInfo.put("FRAGILE_SELECTED", fragile.get(i)+"");
								if(StringUtils.isBlank(commodityDesc.get(i)))
									addActionError(getText("error.commodity.commodityDesc")+" "+(i+1));
								else if(!Validation.nameValidate(commodityDesc.get(i)))
									addActionError(getText("error.commodity.commodityDesc.invalid")+" "+(i+1));
								
								if(insuredValue.get(i).equals("")){
									addActionError(getText("error.commodity.insuredValue")+" "+(i+1));
								}else if(!Validation.decimalValidate(insuredValue.get(i))){
									addActionError(getText("error.commodity.insuredValue.valid")+" "+(i+1));
								}else{
									total+=Double.parseDouble(insuredValue.get(i));
								}if(StringUtils.isNotEmpty(invoiceNo.get(i)) && oneOff.equals(productId) && "TRUE".equals(service.checkExist(invoiceNo.get(i),"INVOICE",applicationNo))){
									addActionError(getText("error.commodity.invoiceNo.exist")+" "+(i+1));
								}
								if(StringUtils.isNotBlank(supplierName.get(i)) && !Validation.nameValidate(supplierName.get(i)))
									addActionError(getText("error.commodity.supplierName.invalid")+" "+(i+1));
								if(StringUtils.isNotBlank(commodityPackDesc.get(i)) && !Validation.addressValidate(commodityPackDesc.get(i)))
									addActionError(getText("error.commodity.commodityPackDesc.invalid")+" "+(i+1));
								if(StringUtils.isNotBlank(invoiceNo.get(i)) && !StringUtils.isNumeric(invoiceNo.get(i)))
									addActionError(getText("error.commodity.invoiceNo.invalid")+" "+(i+1));
								
								chosenCommList.add(commInfo);
							}
						}
					}
					this.commodityList.add(commInfo);
				}
				this.totalSI=total+"";
		}
	}*/
	public void validateGoods() throws Exception
	{
		double total=0.0;
		this.commodityList=new ArrayList<Map<String, Object>>();
		chosenCommList=new ArrayList<Map<String, Object>>();
		Map<String, Object> commInfo=null;
		if (selectCommodity==null || selectCommodity.isEmpty() || ((String)selectCommodity.get(0)).equals("false")) {
			addActionError(getText("error.commodity.oneitem"));
		}
		if (commodityId != null && !commodityId.isEmpty()) {
			for (int i = 0; i < commodityId.size(); i++) {
				commInfo=new LinkedHashMap<String, Object>();
				commInfo.put("SELECTED", "false");
				commInfo.put("COMMODITY_ID", commodityId.get(i));
				commInfo.put("COMMODITY_NAME", commodityDesc.get(i));
				//commInfo.put("COMMODITY_TYPE_ID", category);
				//commInfo.put("PACKAGE_DESC", commodityPackDesc.get(i));
				commInfo.put("SUM_INSURED", insuredValue.get(i));
				commInfo.put("SUPPLIER_NAME", supplierName.get(i));
				commInfo.put("INVOICE_NUMBER", invoiceNo.get(i));
				commInfo.put("INVOICE_DATE", invoiceDate.get(i));
				commInfo.put("FRAGILE", fragile!=null && "true".equals(fragile.get(i)+"")?"on":"off");
				commInfo.put("COMMODITY_GROUP_ID", commoditygroupId);
				if(StringUtils.isBlank(commoditygroupId) && !"11".equalsIgnoreCase(productId)) {
					addActionError("Please select commodity group");
				}
				if (selectCommodity!=null && !selectCommodity.isEmpty()) {
					for (int j = 0; j < selectCommodity.size(); j++) {
						if (commodityId.get(i).equals(selectCommodity.get(j))) {
							commInfo.put("SELECTED", "true");
							commInfo.put("FRAGILE_SELECTED", fragile==null?"false":fragile.get(i)+"");
							if(commodityId.get(i).equals(""))
								addActionError(getText("error.commodity.commodity"));

							if(applicationNo!=null && !"".equals(applicationNo) && !"edit".equals(status2)){
								if(service.getCommodityExist(commodityId.get(i), applicationNo)!=0){
									addActionError(getText("error.commodity.exist"));
								}
							}
							if(StringUtils.isBlank(commodityDesc.get(i)))
								addActionError(getText("error.commodity.commodityDesc"));
							/*	if(StringUtils.isBlank(commodityPackDesc.get(i)))
									addActionError(getText("error.commodity.commodityPackDesc"));
								if(StringUtils.isBlank(commodityPackDesc.get(i)))
									addActionError(getText("error.commodity.commodityPackDesc"));
							 */
							if(insuredValue.get(i).equals(""))
								addActionError(getText("error.commodity.insuredValue"));
							else if(!validation.decimalValidate(insuredValue.get(i))  || 0>=Double.valueOf(insuredValue.get(i)))
								addActionError(getText("error.commodity.insuredValue.valid"));
							else
								total+=Double.parseDouble("".equals(insuredValue.get(i))?"0":insuredValue.get(i));
							/*if(StringUtils.isBlank(invoiceNo.get(i))) {
								addActionError(getText("error.commodity.invoiceNo")); 
							}*/
							/*else if(!StringUtils.isAlphanumeric(invoiceNo.get(i))) {
								addActionError(getText("error.commodity.validInvoiceNo")); 
							}*/
							/*else if(StringUtils.isNotEmpty(invoiceNo.get(i)) && oneOff.equals(productId) && "TRUE".equals(service.checkExist(invoiceNo.get(i),"INVOICE",applicationNo)) && StringUtils.isBlank(endTypeId)){
								addActionError(getText("error.commodity.invoiceNo.exist"));
							}*/
							/*if(StringUtils.isBlank(invoiceDate.get(i)) && StringUtils.isBlank(endTypeId)) {
								addActionError(getText("error.commodity.invoiceDate")); 
							}*/
							chosenCommList.add(commInfo);
						}
					}
					/*if (selectCommodity!=null && !selectCommodity.isEmpty()) {
						for (int j = 0; j < selectCommodity.size(); j++) {
							if (commodityId.get(i).equals(selectCommodity.get(j))) {
								commInfo.put("SELECTED", "true");
								commInfo.put("FRAGILE_SELECTED", fragile.get(i)+"");
								if(StringUtils.isBlank(commodityDesc.get(i)))
									addActionError(getText("error.commodity.commodityDesc")+" "+(i+1));
								else if(!Validation.nameValidate(commodityDesc.get(i)))
									addActionError(getText("error.commodity.commodityDesc.invalid")+" "+(i+1));
								if(insuredValue.get(i).equals("")){
									addActionError(getText("error.commodity.insuredValue")+" "+(i+1));
								}else if(!StringUtils.isNumeric(insuredValue.get(i))){
									addActionError(getText("error.commodity.insuredValue.valid")+" "+(i+1));
								}else{
									total+=Double.parseDouble(insuredValue.get(i));
								}if(StringUtils.isNotEmpty(invoiceNo.get(i)) && oneOff.equals(productId) && "TRUE".equals(service.checkExist(invoiceNo.get(i),"INVOICE",applicationNo))){
									addActionError(getText("error.commodity.invoiceNo.exist")+" "+(i+1));
								}
								if(StringUtils.isNotBlank(supplierName.get(i)) && !Validation.nameValidate(supplierName.get(i)))
									addActionError(getText("error.commodity.supplierName.invalid")+" "+(i+1));
								if(StringUtils.isNotBlank(commodityPackDesc.get(i)) && !Validation.addressValidate(commodityPackDesc.get(i)))
									addActionError(getText("error.commodity.commodityPackDesc.invalid")+" "+(i+1));
								if(StringUtils.isNotBlank(invoiceNo.get(i)) && !StringUtils.isAlphanumeric(invoiceNo.get(i)))
									addActionError(getText("error.commodity.invoiceNo.invalid")+" "+(i+1));


								chosenCommList.add(commInfo);
							}
						}*/
				}
				this.commodityList.add(commInfo);
			}
			this.totalSI=total+"";
		}
	}
	public void validateQuoteInfo()
	{
		if(StringUtils.isBlank(title))
			addActionError(getText("error.quotation.title"));
		if(StringUtils.isBlank(customerName))
			addActionError(getText("error.quotation.customerName"));
		/*else if(!Validation.nameValidate(customerName)) {
			addActionError(getText("error.quotation.customerName.invalid"));
		}*/
		//else if(StringUtils.isNotBlank(channelType) && "customer".equalsIgnoreCase(channelType) && StringUtils.isBlank(coreAppCode)) {
		else if(StringUtils.isBlank(coreAppCode) && !"cash".equalsIgnoreCase(channelType)) {
			addActionError(getText("error.quotation.customer.invalid"));
		}
		if(StringUtils.isNotBlank(address1) && !Validation.addressValidate(address1))
			addActionError(getText("error.quotation.address1.invalid"));
		if(StringUtils.isNotBlank(address2) && !Validation.addressValidate(address2))
			addActionError(getText("error.quotation.address2.invalid"));
		if(StringUtils.isBlank(city))
			addActionError(getText("error.quotation.city"));
		if(StringUtils.isBlank(poBox))
			addActionError(getText("error.quotation.poBox"));
		else 
		if(!StringUtils.isNumeric(poBox))
			addActionError(getText("error.quotation.poBox.valid"));
		
		if(StringUtils.isNotBlank(address2) && !StringUtils.isNumeric(mobileNo))
			addActionError(getText("error.quotation.mobileNo.valid"));
		if(StringUtils.isNotBlank(email) && "invalid".equalsIgnoreCase(validation.emailValidate(email)))
			addActionError(getText("error.quotation.valid.email"));
		if(StringUtils.isBlank(modeOfTransport))
			addActionError(getText("error.quotation.modeOfTransport"));
		if(StringUtils.isBlank(cover))
			addActionError(getText("error.quotation.cover"));
		if(StringUtils.isBlank(conveyance))
			addActionError(getText("error.quotation.conveyance"));
		if(StringUtils.isBlank(originCountry))
			addActionError(getText("error.quotation.originCountry"));
		if(StringUtils.isBlank(originCity) && "YES".equalsIgnoreCase(originWarehouse))
			addActionError(getText("error.quotation.originCity"));
		if(StringUtils.isBlank(destCountry))
			addActionError(getText("error.quotation.destCountry"));
		if(StringUtils.isBlank(destCity) && "YES".equalsIgnoreCase(destWarehouse))
			addActionError(getText("error.quotation.destCity"));
		if("1".equals(modeOfTransport)) {
			if("YES".equals(warSrcc) && StringUtils.isBlank(shipmenttransit)) {
				addActionError("Please Select Shipment Transit");
			}
		}
		if(StringUtils.isBlank(policyStartDate))
			addActionError(getText("error.quotation.policyStartDate"));
		if(StringUtils.isBlank(currency))
			addActionError(getText("error.quotation.currency"));
		if(StringUtils.isBlank(commodity))
			addActionError(getText("error.quotation.commodity"));
		if(StringUtils.isBlank(saleTerm))
			addActionError(getText("error.quotation.saleTerm"));
		if(StringUtils.isBlank(saleTermPercent))
			addActionError(getText("error.quotation.percent"));
		if(StringUtils.isNotBlank(saleTerm) && StringUtils.isNotBlank(saleTermPercent)) {
			try {
				double salePercentValue = service.getSaleTermValue(saleTermPercent,belongingBranch);
				double toleranceValue = service.getToleranceValue(tolerance,belongingBranch);
				if(salePercentValue+toleranceValue>30) {
					addActionError(getText("error.quotation.IncotermsLimit"));
				}
			}
			catch(Exception exception) {
				logger.debug(exception);
			}
		}
		/*if(StringUtils.isBlank(lcBank) 
			&& ( openCover.equals(productId) 
				|| (StringUtils.isBlank(lcBank) && StringUtils.isNotBlank(lcNo)
					) 
				) 
			) {
			addActionError(getText("error.quotation.lcBank"));
		}
		if(StringUtils.isBlank(lcNo) 
			&& ( openCover.equals(productId) 
				|| (StringUtils.isBlank(lcNo) && StringUtils.isNotBlank(lcBank)
					) 
				) 
			) {
			addActionError(getText("error.quotation.lcNo"));
		}*/
		/*if(StringUtils.isBlank(lcDate) && openCover.equals(productId))
			addActionError(getText("error.quotation.lcDate"));
		if(StringUtils.isBlank(lcCurrency) && openCover.equals(productId))
			addActionError(getText("error.quotation.lcCurrency"));
		if(StringUtils.isBlank(lcAmount) && openCover.equals(productId))
			addActionError(getText("error.quotation.lcAmount"));
		else if(StringUtils.isNotBlank(lcAmount) && !Validation.decimalValidate(lcAmount) && openCover.equals(productId))
			addActionError(getText("error.quotation.invalid.lcAmount"));
		if(StringUtils.isNotBlank(lcNo) && openCover.equals(productId)){
			//String existLcId=service.lcNoExist(lcNo);
			List lcList=service.lcSelectionList((String)session.get("openCoverNo"), lcNo,"Y");
			if(lcList!=null && lcList.size()>0){
				Map map=(Map)lcList.get(0);
				if(StringUtils.isNotBlank(lcAmount) && StringUtils.isNotBlank(lcexchageValue) && (Double.parseDouble(lcAmount)*Double.parseDouble(lcexchageValue)<Double.parseDouble(map.get("LC_BALANCE_AMOUNT").toString())))
					addActionError("LC Amount should be greater than "+map.get("LC_BALANCE_AMOUNT").toString());
			}
			else if(service.lcNoExist(lcNo, openCoverNo)>0)
				addActionError(getText("error.quotation.lcNo.exist"));
		}
		if(StringUtils.isNotEmpty(lcNo) && oneOff.equals(productId) && StringUtils.isBlank(endTypeId) && "TRUE".equals(service.checkExist(lcNo,"LCNO",applicationNo)))	
			addActionError(getText("error.quotation.lcNo.exist"));*/
		if(("Y".equalsIgnoreCase(partialShipment) || "M".equalsIgnoreCase(partialShipment)) && openCover.equals(productId)){
			Double partialAmt;
			if (StringUtils.isBlank(shipmentExposure))
				addActionError(getText("error.quotation.shipmentExposure"));
			else if(!StringUtils.isNumeric(shipmentExposure))
				addActionError(getText("error.quotation.shipmentExposure.number"));
			else if(shipmentExposure.length() > 0 && ( Double.parseDouble( shipmentExposure ) == 0)){
				addActionError(getText("error.quotation.shipmentExposure.zero"));
				if(!"".equalsIgnoreCase(exposureCurrency) && !"".equalsIgnoreCase(currency)){
					partialAmt=Double.parseDouble(shipmentExposure)*Double.parseDouble(exposureCurrency);
					if(partialAmt > (Double.parseDouble(totalSI)*Double.parseDouble(currency)))
						addActionError(getText("error.quotation.shipmentGrater"));
				}
			}
			if("".equalsIgnoreCase(exposureCurrency))
				addActionError(getText("error.quotation.exposureCurrency"));
		}
		if(StringUtils.isNotBlank(issuer) && "3".equals(productId) && StringUtils.isBlank(channelType))
			addActionError(getText("error.quotation.channel"));
		if(StringUtils.isNotBlank(issuer) && StringUtils.isBlank(brokerCode))
			addActionError(getText("error.quotation.broker"));
		if(brokerOne.equals(brokerType) && openCover.equals(productId))
		{
			if(StringUtils.isBlank(custContractNo))
				addActionError(getText("error.quotation.contractNo"));
			if(StringUtils.isBlank(custFmsCaseNo))
				addActionError(getText("error.quotation.fmsCaseNo"));
			if(StringUtils.isBlank(custRefNo))
				addActionError(getText("error.quotation.brokerRefNo"));
		}
		/*if(StringUtils.isNotBlank(lcBank) && !Validation.nameValidate(lcBank)&& openCover.equals(productId))
			addActionError(getText("error.quotation.lcBank.invalid"));*/
		/*if(StringUtils.isNotBlank(lcNo) && !Validation.addressValidate(lcNo)&& openCover.equals(productId))
			addActionError(getText("error.quotation.lcNo.invalid"));*/
		if(StringUtils.isNotBlank(blNo) && !Validation.addressValidate(blNo))
			addActionError(getText("error.quotation.blNo.invalid"));
		//else if(!StringUtils.isAlpha(agentOthers))
		
		if(StringUtils.isBlank(settlingAgent))
			addActionError(getText("Please select Settling Agent"));
		else if(StringUtils.isBlank(agentOthers) && "0".equalsIgnoreCase(settlingAgent))
				addActionError(getText("error.quotation.agentOthers"));
		/*else if(!StringUtils.isAlphaSpace(agentOthers))
			addActionError(getText("error.quotation.agentOthers.invalid"));*/
		if(StringUtils.isNotBlank(issuer) && StringUtils.isBlank(executive))
			addActionError(getText("error.quotation.executive"));
		if(StringUtils.isNotBlank(promotionalCode) && service.checkValidPromotionalCode(promotionalCode, branchCode)<=0)
			addActionError("Please enter valid Promotional Code");
		if(StringUtils.isBlank(exchageValue))
			addActionError("Please Enter the Exchange Value");
		if("1".equalsIgnoreCase(modeOfTransport)) {
			if(StringUtils.isBlank(vesselName))
				addActionError("Please Enter the Vessel Name");
			if(StringUtils.isBlank(vesselImoNo))
				addActionError("Please Enter Vessel IMO Number");
		}
		if(openCover.equalsIgnoreCase(productId) && StringUtils.isNotBlank(policyStartDate) &&  StringUtils.isBlank(endTypeId)){
			String openCoverExpDate = service.getSingleInfo("GET_OPEN_COVER_EXPIRTY_DATE",new String[]{openCoverNo});
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date1 = sdf.parse(openCoverExpDate);
				Date date2 = sdf.parse(policyStartDate);
				if(date1.compareTo(date2)<0){
					addActionError(getText("policy.start.date.invalid"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			String openCoverStartDate=service.getSingleInfo("GET_OPEN_COVER_START_DATE",new String[]{openCoverNo});
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date date1 = sdf.parse(openCoverStartDate);
				Date date2 = sdf.parse(policyStartDate);
				if(date1.compareTo(date2)>0){
					addActionError(getText("policy.start.date.invalid.withstart"));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(hasActionErrors()){
			policyStartDate=StringUtils.isNotBlank(policyStartDate)?dateFormat(policyStartDate):"";
			blDate=StringUtils.isNotBlank(blDate)?dateFormat(blDate):"";
			sailingDate=StringUtils.isNotBlank(sailingDate)?dateFormat(sailingDate):"";
			lcDate=StringUtils.isNotBlank(lcDate)?dateFormat(lcDate):"";
		}
	}
	public String dateFormat(String name){
		String arr[]=name.split("/");
		 return arr[1]+"/"+arr[0]+"/"+arr[2];
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public String getStatus2() {
		return status2;
	}
	public void setStatus2(String status2) {
		this.status2 = status2;
	}
	public List<Map<String, Object>> getViewList() {
		return viewList;
	}
	public void setViewList(List<Map<String, Object>> viewList) {
		this.viewList = viewList;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getFrgile(){
		String result=service.getFragile(getCategory(),openCoverNo);
		 if("off".equalsIgnoreCase(result)){
			request.setAttribute(FIELD, "fragileoff");		
		}else{
			request.setAttribute(FIELD, "fragileon");			
		}		
		return DROPDOWN;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
	}
	public List<String> getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(List<String> invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getCoreSearchValue() {
		return coreSearchValue;
	}
	public void setCoreSearchValue(String coreSearchValue) {
		this.coreSearchValue = coreSearchValue;
	}
	public void setExchageValue(String exchageValue) {
		this.exchageValue = exchageValue;
	}
	public String getExchageValue() {
		return exchageValue;
	}
	public void setCustVatRegNo(String custVatRegNo) {
		this.custVatRegNo = custVatRegNo;
	}
	public String getCustVatRegNo() {
		return custVatRegNo;
	}
	public void setCustVatRegYn(String custVatRegYn) {
		this.custVatRegYn = custVatRegYn;
	}
	public String getCustVatRegYn() {
		return custVatRegYn;
	}
	public void setVatApplicable(String vatApplicable) {
		this.vatApplicable = vatApplicable;
	}
	public String getVatApplicable() {
		return vatApplicable;
	}
	public void setVatApplicableNone(String vatApplicableNone) {
		this.vatApplicableNone = vatApplicableNone;
	}
	public String getVatApplicableNone() {
		return vatApplicableNone;
	}
	public String getCommSelTotalCommodity() {
		return commSelTotalCommodity;
	}
	public void setCommSelTotalCommodity(String commSelTotalCommodity) {
		this.commSelTotalCommodity = commSelTotalCommodity;
	}
	public String getCommSelTotalSI() {
		return commSelTotalSI;
	}
	public void setCommSelTotalSI(String commSelTotalSI) {
		this.commSelTotalSI = commSelTotalSI;
	}
	public String getCommSelcommodity() {
		return commSelcommodity;
	}
	public void setCommSelcommodity(String commSelcommodity) {
		this.commSelcommodity = commSelcommodity;
	}
	public List<Map<String, Object>> getVesselList() {
		return vesselList;
	}
	public void setVesselList(List<Map<String, Object>> vesselList) {
		this.vesselList = vesselList;
	}
	
	public String getComList() {
		request.setAttribute(FIELD, "comList");
		return DROPDOWN;
	}
	
}
