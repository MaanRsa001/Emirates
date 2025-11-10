package com.maan.quotation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.CollectionUtils;

import com.iii.premia.maansarovar.services.common.CustomerDataResponseInfo;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.integration.CustXmlBean;
import com.maan.integration.Customer;
import com.maan.integration.CyberSouceIntegration;
import com.maan.integration.IntegrationService;
import com.maan.quotation.service.PremiumService;
import com.maan.quotation.service.QuotationService;
import com.maan.webservice.service.PolicyGenerationService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class PremiumAction extends ActionSupport{
	private static final Logger logger = LogUtil.getLogger(PremiumAction.class);
	
	HttpServletRequest request=ServletActionContext.getRequest();
	Map<String, Object> session=ActionContext.getContext().getSession();
	PremiumService service=new PremiumService();
	PolicyGenerationService policyService=new PolicyGenerationService();
	private static final long serialVersionUID = 1L;
	private String openCover=getText("OPEN_COVER");
	private String ADMIN=getText("admin");
	private String adminLoginId=(String)session.get("user");
	private String userType=(String)session.get("user1");
	private String applicationNo;	
	private String refNo;	
	private String quoteNo;
	private String policyNo;
	private String premiumYN;
	private String banker;
	private String both;
	private String foreign;
	private String excess;
	private String rubberStamp;
	private String branchCode=(String)session.get("LoginBranchCode");
	private String belongingBranch=(String)session.get("BelongingBranch");
	private String generatePolicy;
	private String referralComment;
	private String referralUpdate;
	private String quoteType;
	private String referralStatus;
	private String commissionYN;
	private String commission;
	private String adminRemarks;
	private String additionalPremium;
	private String totalPremium;
	private String totalWarPremium;
	private String premiumOption;
	private String policyFee;
	private String policyFeeStatus;
	private String editPolicyFee;
	private String loginId;
	private String issuer;
	private String productId=(String) session.get("product_id");
	private String openCoverNo=(String)session.get("openCoverNo");
	private String status;
	private String customerId;
	private String noteType;
	private String paymentMode;
	private String basisValDesc;
	private String coverId;
	private String warCoverId;
	private String modeOfTransport;
	private String settlingAgent;
	private String packDesc;
	private String quoteStatus;
	private String conditionType;
	private String clauses;
	private String warClauses;
	private String exclusions;
	private String warranties;
	private String addClauses;
	private String addWarClauses;
	private String addExclusions;
	private String addWarranties;
	private String certClausesYN;
	private List<Object> commodityId;
	private List<Object> commodityRate;
	private List<Object> commodityWarRate;
	private List<Object> warehouseRate;
	private List<Object> policyExcess;
	private Map<String, Object> conditionsList;
	private Map<String, Object> taxInfo;
	private List<Object> clausesId;
	private List<Object> warId;
	private List<Object> exclusionId;
	private List<Object> warrantyId;
	private List<Object> clausesDesc;
	private List<Object> warDesc;
	private List<Object> exclusionDesc;
	private List<Object> warrantyDesc;
	private List<Map<String, Object>> conditions;
	private String endTypeId;
	private String fields;
	private String custName;
	private String brokerCompany;
	private String updateClauses;
	private String searcyFrom;
	private String searcyBy;
	private String searcyValue;
	private String showpremiumYN;
	private String printClausesYN;
	private String channelType;
	private String loginUserType=(String)session.get("usertype");
	private String vatTax;
	private String paymentMethod;
	private Map<String, String> cyberSourceInfo;
	private String merchantRefno;
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getVatTax() {
		return vatTax;
	}

	public void setVatTax(String vatTax) {
		this.vatTax = vatTax;
	}

	/**
	 * @return the excess
	 */
	public String getExcess() {
		return excess;
	}

	/**
	 * @param excess the excess to set
	 */
	public void setExcess(String excess) {
		this.excess = excess;
	}

	/**
	 * @return the showpremiumYN
	 */
	public String getShowpremiumYN() {
		return showpremiumYN;
	}

	/**
	 * @param showpremiumYN the showpremiumYN to set
	 */
	public void setShowpremiumYN(String showpremiumYN) {
		this.showpremiumYN = showpremiumYN;
	}

	/**
	 * @return the printClausesYN
	 */
	public String getPrintClausesYN() {
		return printClausesYN;
	}

	/**
	 * @param printClausesYN the printClausesYN to set
	 */
	public void setPrintClausesYN(String printClausesYN) {
		this.printClausesYN = printClausesYN;
	}

	/**
	 * @return the searcyFrom
	 */
	public String getSearcyFrom() {
		return searcyFrom;
	}

	/**
	 * @param searcyFrom the searcyFrom to set
	 */
	public void setSearcyFrom(String searcyFrom) {
		this.searcyFrom = searcyFrom;
	}

	/**
	 * @return the searcyBy
	 */
	public String getSearcyBy() {
		return searcyBy;
	}

	/**
	 * @param searcyBy the searcyBy to set
	 */
	public void setSearcyBy(String searcyBy) {
		this.searcyBy = searcyBy;
	}

	/**
	 * @return the searcyValue
	 */
	public String getSearcyValue() {
		return searcyValue;
	}

	/**
	 * @param searcyValue the searcyValue to set
	 */
	public void setSearcyValue(String searcyValue) {
		this.searcyValue = searcyValue;
	}

	/**
	 * @return the updateClauses
	 */
	public String getUpdateClauses() {
		return updateClauses;
	}

	/**
	 * @param updateClauses the updateClauses to set
	 */
	public void setUpdateClauses(String updateClauses) {
		this.updateClauses = updateClauses;
	}


	/**
	 * @return the isEndt
	 */
	public boolean isEndt() {
		return service.isEndt(applicationNo);
	}
	
	/**
	 * @return the isFinancial
	 */
	public boolean isFinancial() {
		return service.isFinancial(applicationNo);
	}

	
	/**
	 * @return the brokerCompany
	 */
	public String getBrokerCompany() {
		return brokerCompany;
	}

	/**
	 * @param brokerCompany the brokerCompany to set
	 */
	public void setBrokerCompany(String brokerCompany) {
		this.brokerCompany = brokerCompany;
	}

	/**
	 * @return the custName
	 */
	public String getCustName() {
		return custName;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/**
	 * @return the fields
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	/**
	 * @return the endTypeId
	 */
	public String getEndTypeId() {
		return endTypeId;
	}

	/**
	 * @param endTypeId the endTypeId to set
	 */
	public void setEndTypeId(String endTypeId) {
		this.endTypeId = endTypeId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @return the refNo
	 */
	public String getRefNo() {
		return refNo;
	}

	/**
	 * @param refNo the refNo to set
	 */
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	/**
	 * @return the totalWarPremium
	 */
	public String getTotalWarPremium() {
		return totalWarPremium;
	}

	/**
	 * @param totalWarPremium the totalWarPremium to set
	 */
	public void setTotalWarPremium(String totalWarPremium) {
		this.totalWarPremium = totalWarPremium;
	}

	/**
	 * @return the issuer
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * @param issuer the issuer to set
	 */
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	/**
	 * @return the certClausesYN
	 */
	public String getCertClausesYN() {
		return certClausesYN;
	}

	/**
	 * @param certClausesYN the certClausesYN to set
	 */
	public void setCertClausesYN(String certClausesYN) {
		this.certClausesYN = certClausesYN;
	}

	/**
	 * @return the addClauses
	 */
	public String getAddClauses() {
		return addClauses;
	}

	/**
	 * @param addClauses the addClauses to set
	 */
	public void setAddClauses(String addClauses) {
		this.addClauses = addClauses;
	}

	/**
	 * @return the addWarClauses
	 */
	public String getAddWarClauses() {
		return addWarClauses;
	}

	/**
	 * @param addWarClauses the addWarClauses to set
	 */
	public void setAddWarClauses(String addWarClauses) {
		this.addWarClauses = addWarClauses;
	}

	/**
	 * @return the addExclusions
	 */
	public String getAddExclusions() {
		return addExclusions;
	}

	/**
	 * @param addExclusions the addExclusions to set
	 */
	public void setAddExclusions(String addExclusions) {
		this.addExclusions = addExclusions;
	}

	/**
	 * @return the addWarranties
	 */
	public String getAddWarranties() {
		return addWarranties;
	}

	/**
	 * @param addWarranties the addWarranties to set
	 */
	public void setAddWarranties(String addWarranties) {
		this.addWarranties = addWarranties;
	}

	/**
	 * @return the conditions
	 */
	public List<Map<String, Object>> getConditions() {
		return conditions;
	}

	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * @return the openCover
	 */
	public String getOpenCover() {
		return openCover;
	}

	/**
	 * @return the quoteStatus
	 */
	public String getQuoteStatus() {
		return quoteStatus;
	}

	/**
	 * @param quoteStatus the quoteStatus to set
	 */
	public void setQuoteStatus(String quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	/**
	 * @return the settlingAgent
	 */
	public String getSettlingAgent() {
		return settlingAgent;
	}

	/**
	 * @param settlingAgent the settlingAgent to set
	 */
	public void setSettlingAgent(String settlingAgent) {
		this.settlingAgent = settlingAgent;
	}

	/**
	 * @return the packDesc
	 */
	public String getPackDesc() {
		return packDesc;
	}

	/**
	 * @param packDesc the packDesc to set
	 */
	public void setPackDesc(String packDesc) {
		this.packDesc = packDesc;
	}

	/**
	 * @return the coverId
	 */
	public String getCoverId() {
		return coverId;
	}

	/**
	 * @param coverId the coverId to set
	 */
	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}

	/**
	 * @return the warCoverId
	 */
	public String getWarCoverId() {
		return warCoverId;
	}

	/**
	 * @param warCoverId the warCoverId to set
	 */
	public void setWarCoverId(String warCoverId) {
		this.warCoverId = warCoverId;
	}

	/**
	 * @return the modeOfTransport
	 */
	public String getModeOfTransport() {
		return modeOfTransport;
	}

	/**
	 * @param modeOfTransport the modeOfTransport to set
	 */
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	/**
	 * @return the noteType
	 */
	public String getNoteType() {
		if(isEndt())
			return service.getNoteTypeInfo(applicationNo);
		else
			return noteType;
	}

	/**
	 * @param noteType the noteType to set
	 */
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		if(isEndt())
		    return service.getPaymentModeInfo(applicationNo);
		else
			return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the basisValDesc
	 */
	public String getBasisValDesc() {
		return basisValDesc;
	}

	/**
	 * @param basisValDesc the basisValDesc to set
	 */
	public void setBasisValDesc(String basisValDesc) {
		this.basisValDesc = basisValDesc;
	}

	/**
	 * @return the clauses
	 */
	public String getClauses() {
		return clauses;
	}

	/**
	 * @param clauses the clauses to set
	 */
	public void setClauses(String clauses) {
		this.clauses = clauses;
	}

	/**
	 * @return the warClauses
	 */
	public String getWarClauses() {
		return warClauses;
	}

	/**
	 * @param warClauses the warClauses to set
	 */
	public void setWarClauses(String warClauses) {
		this.warClauses = warClauses;
	}

	/**
	 * @return the exclusions
	 */
	public String getExclusions() {
		return exclusions;
	}

	/**
	 * @param exclusions the exclusions to set
	 */
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	/**
	 * @return the warranties
	 */
	public String getWarranties() {
		return warranties;
	}

	/**
	 * @param warranties the warranties to set
	 */
	public void setWarranties(String warranties) {
		this.warranties = warranties;
	}

	/**
	 * @return the clausesId
	 */
	public List<Object> getClausesId() {
		return clausesId;
	}

	/**
	 * @param clausesId the clausesId to set
	 */
	public void setClausesId(List<Object> clausesId) {
		this.clausesId = clausesId;
	}

	/**
	 * @return the warId
	 */
	public List<Object> getWarId() {
		return warId;
	}

	/**
	 * @param warId the warId to set
	 */
	public void setWarId(List<Object> warId) {
		this.warId = warId;
	}

	/**
	 * @return the exclusionId
	 */
	public List<Object> getExclusionId() {
		return exclusionId;
	}

	/**
	 * @param exclusionId the exclusionId to set
	 */
	public void setExclusionId(List<Object> exclusionId) {
		this.exclusionId = exclusionId;
	}

	/**
	 * @return the warrantyId
	 */
	public List<Object> getWarrantyId() {
		return warrantyId;
	}

	/**
	 * @param warrantyId the warrantyId to set
	 */
	public void setWarrantyId(List<Object> warrantyId) {
		this.warrantyId = warrantyId;
	}

	/**
	 * @return the clausesDesc
	 */
	public List<Object> getClausesDesc() {
		return clausesDesc;
	}

	/**
	 * @param clausesDesc the clausesDesc to set
	 */
	public void setClausesDesc(List<Object> clausesDesc) {
		this.clausesDesc = clausesDesc;
	}

	/**
	 * @return the warDesc
	 */
	public List<Object> getWarDesc() {
		return warDesc;
	}

	/**
	 * @param warDesc the warDesc to set
	 */
	public void setWarDesc(List<Object> warDesc) {
		this.warDesc = warDesc;
	}

	/**
	 * @return the exclusionDesc
	 */
	public List<Object> getExclusionDesc() {
		return exclusionDesc;
	}

	/**
	 * @param exclusionDesc the exclusionDesc to set
	 */
	public void setExclusionDesc(List<Object> exclusionDesc) {
		this.exclusionDesc = exclusionDesc;
	}

	/**
	 * @return the warrantyDesc
	 */
	public List<Object> getWarrantyDesc() {
		return warrantyDesc;
	}

	/**
	 * @param warrantyDesc the warrantyDesc to set
	 */
	public void setWarrantyDesc(List<Object> warrantyDesc) {
		this.warrantyDesc = warrantyDesc;
	}

	/**
	 * @return the openCoverNo
	 */
	public String getOpenCoverNo() {
		return openCoverNo;
	}

	/**
	 * @param openCoverNo the openCoverNo to set
	 */
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}

	/**
	 * @return the policyNo
	 */
	public String getPolicyNo() {
		return policyNo;
	}

	/**
	 * @param policyNo the policyNo to set
	 */
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	/**
	 * @return the policyInfo
	 */
	public List<Map<String, Object>> getPolicyInformation() {
		return service.getPolicyInformation(applicationNo);
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the commissionStatus
	 */
	public boolean getCommissionStatus() {
		return service.getCommissionStatus(service.getLoginId(applicationNo), productId, openCoverNo, issuer, applicationNo, branchCode);
	}
	/**
	 * @return the accountStatus
	 */
	public boolean getAccountStatus() {
		return service.getCustAccountStatus(applicationNo);
	}

	/**
	 * @return the conditionsList
	 */
	public Map<String, Object> getConditionsList() {
		return conditionsList;
	}
	
	/**
	 * @return the basisValList
	 */
	public List<Map<String, Object>> getBasisValList() {
		return service.getBasisValList(belongingBranch);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return loginId;
	}


	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	/**
	 * @return the editPolicyFee
	 */
	public String getEditPolicyFee() {
		return editPolicyFee;
	}


	/**
	 * @param editPolicyFee the editPolicyFee to set
	 */
	public void setEditPolicyFee(String editPolicyFee) {
		this.editPolicyFee = editPolicyFee;
	}


	/**
	 * @return the totalPremium
	 */
	public String getTotalPremium() {
		return totalPremium;
	}


	/**
	 * @param totalPremium the totalPremium to set
	 */
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}


	/**
	 * @return the policyFee
	 */
	public String getPolicyFee() {
		return policyFee;
	}


	/**
	 * @param policyFee the policyFee to set
	 */
	public void setPolicyFee(String policyFee) {
		this.policyFee = policyFee;
	}


	/**
	 * @return the premiumOption
	 */
	public String getPremiumOption() {
		return premiumOption;
	}


	/**
	 * @param premiumOption the premiumOption to set
	 */
	public void setPremiumOption(String premiumOption) {
		this.premiumOption = premiumOption;
	}


	/**
	 * @return the referralStatus
	 */
	public String getReferralStatus() {
		return referralStatus;
	}


	/**
	 * @param referralStatus the referralStatus to set
	 */
	public void setReferralStatus(String referralStatus) {
		this.referralStatus = referralStatus;
	}


	/**
	 * @return the commissionYN
	 */
	public String getCommissionYN() {
		return commissionYN;
	}


	/**
	 * @param commissionYN the commissionYN to set
	 */
	public void setCommissionYN(String commissionYN) {
		this.commissionYN = commissionYN;
	}


	/**
	 * @return the commission
	 */
	public String getCommission() {
		return commission;
	}


	/**
	 * @param commission the commission to set
	 */
	public void setCommission(String commission) {
		this.commission = commission;
	}


	/**
	 * @return the adminRemarks
	 */
	public String getAdminRemarks() {
		return adminRemarks;
	}


	/**
	 * @param adminRemarks the adminRemarks to set
	 */
	public void setAdminRemarks(String adminRemarks) {
		this.adminRemarks = adminRemarks;
	}


	/**
	 * @return the additionalPremium
	 */
	public String getAdditionalPremium() {
		return additionalPremium;
	}


	/**
	 * @param additionalPremium the additionalPremium to set
	 */
	public void setAdditionalPremium(String additionalPremium) {
		this.additionalPremium = additionalPremium;
	}


	/**
	 * @return the commodityId
	 */
	public List<Object> getCommodityId() {
		return commodityId;
	}


	/**
	 * @param commodityId the commodityId to set
	 */
	public void setCommodityId(List<Object> commodityId) {
		this.commodityId = commodityId;
	}


	/**
	 * @return the commodityRate
	 */
	public List<Object> getCommodityRate() {
		return commodityRate;
	}


	/**
	 * @param commodityRate the commodityRate to set
	 */
	public void setCommodityRate(List<Object> commodityRate) {
		this.commodityRate = commodityRate;
	}


	/**
	 * @return the commodityWarRate
	 */
	public List<Object> getCommodityWarRate() {
		return commodityWarRate;
	}


	/**
	 * @param commodityWarRate the commodityWarRate to set
	 */
	public void setCommodityWarRate(List<Object> commodityWarRate) {
		this.commodityWarRate = commodityWarRate;
	}


	/**
	 * @return the policyExcess
	 */
	public List<Object> getPolicyExcess() {
		return policyExcess;
	}


	/**
	 * @param policyExcess the policyExcess to set
	 */
	public void setPolicyExcess(List<Object> policyExcess) {
		this.policyExcess = policyExcess;
	}


	/**
	 * @return the warehouseRate
	 */
	public List<Object> getWarehouseRate() {
		return warehouseRate;
	}


	/**
	 * @param warehouseRate the warehouseRate to set
	 */
	public void setWarehouseRate(List<Object> warehouseRate) {
		this.warehouseRate = warehouseRate;
	}


	/**
	 * @return the quoteType
	 */
	public String getQuoteType() {
		return quoteType;
	}


	/**
	 * @param quoteType the quoteType to set
	 */
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}


	public String getReferralUpdate() {
		return referralUpdate;
	}


	public void setReferralUpdate(String referralUpdate) {
		this.referralUpdate = referralUpdate;
	}


	public String getGeneratePolicy() {
		return generatePolicy;
	}


	public void setGeneratePolicy(String generatePolicy) {
		this.generatePolicy = generatePolicy;
	}

	public String getReferralComment() {
		return referralComment;
	}

	public void setReferralComment(String referralComment) {
		this.referralComment = referralComment;
	}


	/**
	 * @return the premiumYN
	 */
	public String getPremiumYN() {
		return premiumYN;
	}

	/**
	 * @param premiumYN the premiumYN to set
	 */
	public void setPremiumYN(String premiumYN) {
		this.premiumYN = premiumYN;
	}

	public String getBanker() {
		return banker;
	}


	public void setBanker(String banker) {
		this.banker = banker;
	}


	public String getBoth() {
		return both;
	}


	public void setBoth(String both) {
		this.both = both;
	}


	public String getForeign() {
		return foreign;
	}


	public void setForeign(String foreign) {
		this.foreign = foreign;
	}


	public String getRubberStamp() {
		return rubberStamp;
	}

	public void setRubberStamp(String rubberStamp) {
		this.rubberStamp = rubberStamp;
	}

	public String getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getQuoteNo() {
		return quoteNo;
	}


	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}


	public String getApplicationNo() {
		return applicationNo;
	}


	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}


	public List<Map<String, Object>> getQuotationInfo() {
		//return service.getQuotationInformation(applicationNo,branchCode);
		return service.getQuotationInformation(applicationNo,belongingBranch);
	}
	
	public List<Map<String, Object>> getInsuredGoodsInfo() {
		//return service.getQuotationInsuredValue(applicationNo,branchCode);
		return service.getQuotationInsuredValue(applicationNo,belongingBranch);
	}
	
	/**
	 * @return the endtType
	 */
	public String getEndtType() {
		return new QuotationService().getPolicyEndtType(applicationNo);
	}
	
	public String getViewClausesOption() {
		if(openCover.equals(productId)){
			return new QuotationService().getSingleInfo("VIEW_CLAUSES_OPTION",new String[]{openCoverNo,openCoverNo});
		}else{
			return "N";
		}
	}
	
	/**
	 * @return the endtPremiumInfo
	 */
	public Map<String, Object> getEndtPremiumInfo() {
		return service.getEndtPremiumInfo(quoteNo);
	}
	
	public Map<String, Object> getPolicyEndtInfo()
	{
		return new QuotationService().getPolicyEndtInfo(quoteNo);	
	}
	public Map<String, Object> getOpenCoverInfo()
	{
		return new QuotationService().getOpenCoverInfo(openCoverNo);	
	}
	public boolean getArInsuredStatus() {
		boolean status=false;
		if(openCover.equals(productId)){
			status=service.getOpenCoverCustomer(openCoverNo);
		}	
		return status;
	}
	
	public String getDirectStatus() {
		return new QuotationService().getDirectStatus(applicationNo,branchCode);
	}
	public String init(){
		return SUCCESS;
	}
	public String clauses()
	{
		try{
			conditionsList=service.getConditionsInfo(applicationNo, belongingBranch);
		}catch (Exception e){
			logger.debug("Exception @ "+e);e.printStackTrace();  
		}	
		return INPUT;
	}
	public String updateConditions(){
		
		try{
			clauses=service.convertConditions(clausesId, clausesDesc);
			warClauses=service.convertConditions(warId, warDesc);
			exclusions=service.convertConditions(exclusionId, exclusionDesc);
			warranties=service.convertConditions(warrantyId, warrantyDesc);
			service.updateClausesInfo(new String[]{clauses,warranties,exclusions,warClauses,applicationNo});
			addActionMessage(getText("conditions.update.success"));
		}catch (Exception e){
			logger.debug("Exception @ "+e);
		}
		return INPUT;
	}
	
	public String update()
	{
		String result="";
		String forward=SUCCESS;
		boolean endt=service.isEndt(applicationNo);
		try
		{
			if(ADMIN.equalsIgnoreCase(userType)){
				calculate();
				if(!hasActionErrors()){
					service.updateReferralInfo(applicationNo, referralStatus, adminRemarks, commission, getAdminLoginId(), service.isEndt(applicationNo)?"E":"Y");
					if("Y".equalsIgnoreCase(updateClauses)){
						updateConditionsInfo();
					}
					String policyExcessVal=(String) (policyExcess.get(0)==null?"0":policyExcess.get(0)==null?"0":policyExcess.get(0));
					service.updateExcess(applicationNo,policyExcessVal);
					service.addConditionsInfo(new String[]{addClauses,addWarranties,addExclusions,addWarClauses,quoteNo});
					//service.sendMail(StringUtils.defaultIfEmpty(refNo, applicationNo), userType, referralStatus);
					service.referralResponseMail(StringUtils.defaultIfEmpty(refNo, applicationNo), userType, referralStatus);
					forward="referralStatusInfo";
				}
			}else if("N".equals(status)){
				validateFields();
				validateExcessPremium();
				validateSumInsured();
		      	if (!hasActionErrors()){
		      		taxInfo=policyService.getTaxInfo(branchCode, loginId,issuer);
		      		if(taxInfo!=null && !taxInfo.isEmpty()){
		      			policyFeeStatus=(String)taxInfo.get("POLICY_FEE_STATUS");
		      		}
		      		if(!service.isEndt(applicationNo)){
		      			calculatePolicyFee();
		      			vatTax="0";
		      		}
		      		
		      		service.updatePremiumInfo(applicationNo, totalPremium, additionalPremium, policyFee,totalWarPremium,vatTax);
		      		if(!"Y".equalsIgnoreCase(generatePolicy)&&!endt){
		      			service.updateMarineVatinfo(quoteNo,branchCode,productId);
		      		}
		      		service.updatePolicyInformation(this);
		      		if("Y".equalsIgnoreCase(generatePolicy)){
		      			
		      			if("O".equals(paymentMethod)) {
		      				merchantRefno	 = service.createMerchantRefNoWithStatus(quoteNo,applicationNo,"P",paymentMethod,productId,this);
		      					/*CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
		      				cyberSourceInfo=cyberIntg.createPay(quoteNo,branchCode,productId,applicationNo,merchantRefno);
		      				forward="cybersource";*/
		      				forward="policyInfo";
		      				//forward="onlinePaymentReq";
		      			}else if("C".equals(paymentMethod)){
		      				service.createMerchantRefNoWithStatus(quoteNo,applicationNo,"C",paymentMethod,productId,this);
		      				result=service.policyGeneration(StringUtils.defaultIfEmpty(refNo, applicationNo));
		      				if(StringUtils.isEmpty(result) || "invalid".equalsIgnoreCase(result)){
		      					addActionError(getText("error.premiumInfo.policy.invalid"));
		      				}else if(result.indexOf("ERROR")!=-1){
		      					addActionError(result);
		      				}else{
		      					policyNo=result;
		      					forward="policyInfo";
		      				}
		      			}
			      	}
			      	else{
			      		forward="policyInfo";
			      	}
			    }
			}else if("Y".equals(status)){
				forward="policyInfo";
			}
		}
		catch (Exception e){
			logger.debug("Exception @ "+e);
			e.printStackTrace();
		}
		return forward;
	}
	public String calculate()
	{
		Map<String, Object>premiumInfo=null;
		boolean endt=service.isEndt(applicationNo);
		try {
			taxInfo=policyService.getTaxInfo(branchCode, loginId,issuer);
      		if(taxInfo!=null && !taxInfo.isEmpty()){
      			policyFeeStatus=(String)taxInfo.get("POLICY_FEE_STATUS");
      		}
			if(ADMIN.equalsIgnoreCase(userType)){
				String commodityList[][]=validateGoods();
				if(!hasActionErrors()){
					premiumInfo=service.calculatePremium(StringUtils.defaultIfEmpty(refNo, applicationNo), commodityList);
					if(premiumInfo!=null && !premiumInfo.isEmpty()){
						totalPremium=(String)premiumInfo.get("totalPremium");
						totalWarPremium=(String)premiumInfo.get("totalWarPremium");
						if(StringUtils.isBlank(additionalPremium) || "0".equals(additionalPremium))
							additionalPremium= String.valueOf(premiumInfo.get("discountPremium"));
					}
					validateExcessPremium();
					if(!hasActionErrors()){
						if("N".equals(editPolicyFee) && !endt){
							calculatePolicyFee();
						}else if("N".equals(editPolicyFee) && endt){
							policyFee="0";
						}
						if(!endt){
							vatTax="0";
						}
						service.updatePremiumInfo(applicationNo, totalPremium, additionalPremium, policyFee,totalWarPremium,vatTax);
						//if(!endt){
							service.updateMarineVatinfo(quoteNo,branchCode,productId);
						//}
						if(endt){
							service.updateEndtPremium(quoteNo);
							if("N".equals(editPolicyFee) && service.endtPolicyFee(applicationNo)){
								service.updateEndtPolicyFee(applicationNo, calculatePolicyFee());
							}
						}
						else {
							policyService.updateMarineAndWarPremium(quoteNo);
						}
					}
				}
			}else{
				validateExcessPremium();
		      	if (!hasActionErrors()){
		      		if(!endt){
		      			calculatePolicyFee();
		      			vatTax="0";
		      		}
		      		service.updatePremiumInfo(applicationNo, totalPremium, additionalPremium, policyFee,totalWarPremium,vatTax);
		      		if(!endt){
		      			service.updateMarineVatinfo(quoteNo,branchCode,productId);
		      		}
			    }
			}
		}catch (Exception e){
			logger.debug("Exception @ "+e);e.printStackTrace();
		}
		return SUCCESS;	
	}
	public String calculatePolicyFee()
	{
		taxInfo=policyService.getTaxInfo(branchCode, loginId, issuer);
		if(!CollectionUtils.isEmpty(taxInfo)){
			String brokerType=service.getBrokerType(loginId);
			policyFee=policyService.calculatePolicyFee(branchCode, productId, openCoverNo, totalPremium, additionalPremium,policyFeeStatus, brokerType);
		}
		return policyFee; 
	}
	public String policyInfo()
	{
		return "policyInfo"; 
	}
	public void validateFields(){
		if ("Y".equalsIgnoreCase(referralUpdate) && StringUtils.isEmpty(referralComment)) {	
			addActionError(getText("error.premiumInfo.comment"));
		}else if("Y".equalsIgnoreCase(generatePolicy)){
			if(StringUtils.isEmpty(settlingAgent)){
				addActionError(getText("error.premiumInfo.agent"));
			}
			/*if(StringUtils.isEmpty(packDesc) || !"0".equals(packDesc)){
				addActionError(getText("error.premiumInfo.packDesc"));
			}*/
			//if(StringUtils.isNotBlank(channelType) && "customer".equalsIgnoreCase(channelType)) {
			/* sample if(StringUtils.isNotBlank(getQuoteNo())) {
				 try {
						
						IntegrationService intg = new IntegrationService();
						CustomerDataResponseInfo responseInfo=intg.brokerDataService(getQuoteNo(),"Normal");
						
							CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
							Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
							if(responseInfo.getOutPut()!=null){
							
							String freezeStatus = custBean.getCustFrzFlage();
							String creditStatus = custBean.getCustCreditChkYN();
							if(!"N".equalsIgnoreCase(freezeStatus)) {
								addActionError(getText("error.existingbroker.blocked"));
							} 

						}else{
							addActionError(getText("error.existingbroker.invalid"));
						}

					}
					catch(Exception exception) {
						addActionError(getText("error.existingbroker.invalid"));
						logger.debug(exception);
					}
					
				try {
	
					IntegrationService intg = new IntegrationService();
					CustomerDataResponseInfo responseInfo=intg.customerDataService(getQuoteNo(),"Normal");
					
						CustXmlBean custXmlBean =intg.setCustXmlBean(responseInfo.getOutPut());
						Customer   custBean=custXmlBean.getCustQueryDtls().getCustomer();
						//Map<String,Object> existingCustInfo = service.getExistingCustInfo(customerId);
						if(responseInfo.getOutPut()!=null){
						//if(existingCustInfo!=null){
							//String freezeStatus = existingCustInfo.get("CUSTFRZFLAGE")==null?"":existingCustInfo.get("CUSTFRZFLAGE").toString();
							//String creditStatus = existingCustInfo.get("CUSTCREDITCHKYN")==null?"":existingCustInfo.get("CUSTCREDITCHKYN").toString();
							//String osAmt  = existingCustInfo.get("CUSTCREDITBALANCE")==null?"":existingCustInfo.get("CUSTCREDITBALANCE").toString();
							//String creditLimit = existingCustInfo.get("CUSTCREDITLIMIT")==null?"":existingCustInfo.get("CUSTCREDITLIMIT").toString();
						String freezeStatus = custBean.getCustFrzFlage();
						String creditStatus = custBean.getCustCreditChkYN();
						if(!"N".equalsIgnoreCase(freezeStatus)) {
							addActionError(getText("error.existingCustomer.blocked"));
						}
						else if("Y".equalsIgnoreCase(creditStatus)) {
							String osAmt =custBean.getCustCreditBalance()==null?"0":custBean.getCustCreditBalance();
							String creditLimit =custBean.getCustCreditLimit();
							if((Double.valueOf(totalPremium)>(Double.valueOf(creditLimit)+Double.valueOf("".equalsIgnoreCase(osAmt)?"0":osAmt)))) {
								addActionError(getText("error.existingCustomer.limitExceeds"));
							}
						}


					}else{
						addActionError(getText("error.existingCustomer.invalid"));
					}

				}
				catch(Exception exception) {
					addActionError("error.existingCustomer.invalid");
					logger.debug(exception);
				}
			}*/
		}
	}
	public String[][] validateGoods() throws BaseException
	{
		String commodityList[][]=new String[commodityId.size()][7];
		for (int i = 0; i < commodityList.length; i++) {
			commodityList[i][0]=(String)commodityId.get(i);
			commodityList[i][1]=(String)commodityRate.get(i);
			commodityList[i][2]=(String)commodityWarRate.get(i);
			if(warehouseRate!=null){
				commodityList[i][3]=(String)warehouseRate.get(i);
			}else{
				commodityList[i][3]="0";
			}
			commodityList[i][4]="0";
			commodityList[i][5]=(String)policyExcess.get(i);
			if(!("R".equals(referralStatus) || "N".equals(referralStatus))){
				if(StringUtils.isNotEmpty(commodityList[i][5]) && !NumberUtils.isNumber(commodityList[i][5])){
					addActionError(getText("error.premiumInfo.policyExcess.valid")+(i+1));
				}if(!NumberUtils.isNumber(commodityList[i][1])){
					addActionError(getText("error.premiumInfo.rate.valid")+(i+1));
				}else if(Double.parseDouble(commodityList[i][1])>100 || Double.parseDouble(commodityList[i][1])<=0){
					addActionError(getText("error.premiumInfo.rate.valid")+(i+1));
				}if(!NumberUtils.isNumber(commodityList[i][2])){
					addActionError(getText("error.premiumInfo.warRate.valid")+(i+1));
				}
				else if((!"0".equalsIgnoreCase(policyService.getValue("GET_WAR_OPTION",new String[]{applicationNo}))) && (Double.parseDouble(commodityList[i][2])>100 || Double.parseDouble(commodityList[i][2])<=0)) {
					addActionError(getText("error.premiumInfo.warRate.valid")+(i+1));
				}
			}
		}
		return commodityList;
	}
	public void validateExcessPremium()throws BaseException
	{
		boolean endt=service.isEndt(applicationNo);
		double excessPercent=0.0,loadPercent=0.0,disPercent=0.0,minPre=0.0;
		Map<String, Object> brokerLoadDis=null;
		if(StringUtils.isNotEmpty(additionalPremium) && !NumberUtils.isNumber(additionalPremium)){
			addActionError(getText("error.premiumInfo.excessPremium.valid"));
		}else if(!ADMIN.equalsIgnoreCase(userType) && !"RA".equalsIgnoreCase(quoteStatus) && !endt){
			if(Double.parseDouble(StringUtils.isBlank(additionalPremium)?"0":additionalPremium)!=0)
				excessPercent= (Double.parseDouble(additionalPremium)/Double.parseDouble(totalPremium))*100;
			else
				excessPercent=0;
			if(!openCover.equals(productId)){
				brokerLoadDis = service.getPremiumLoadDisc(loginId);
				if(brokerLoadDis!=null && !brokerLoadDis.isEmpty()){
					loadPercent = Double.parseDouble(StringUtils.defaultIfEmpty(brokerLoadDis.get("LOADING_OF_PREMIUM").toString(), "0"));
					disPercent = Double.parseDouble(StringUtils.defaultIfEmpty(brokerLoadDis.get("DISCOUNT_OF_PREMIUM").toString(), "0"));
					minPre = Double.parseDouble(StringUtils.defaultIfEmpty(brokerLoadDis.get("MIN_PREMIUM_AMOUNT").toString(), "0"));
				}
			}
			if("+".equals(premiumOption) && excessPercent>loadPercent){
				addActionError(getText("error.premiumInfo.excessPremium.exceeds.loading"));
			}else if("-".equals(premiumOption) ){
				if(Double.parseDouble(additionalPremium)>Double.parseDouble(totalPremium)){
					addActionError(getText("error.premiumInfo.excessPremium.exceeds.totalPre"));
				}else if(minPre==Double.parseDouble(totalPremium) && excessPercent>0){
					addActionError("Final premium less than Minimum premium");
				}else if(Double.parseDouble(totalPremium)-Double.parseDouble(additionalPremium)<minPre){
					addActionError("Final premium less than Minimum premium");
				}else if(excessPercent>disPercent){
					addActionError(getText("error.premiumInfo.excessPremium.exceeds.discount"));
				}
			}
		}else if(ADMIN.equalsIgnoreCase(userType) || "RA".equalsIgnoreCase(quoteStatus)){
			double total=0.0;
			total= (Double.parseDouble(totalPremium)+Double.parseDouble(policyFee));
			if("-".equals(premiumOption) ){
				/*if(Double.parseDouble(additionalPremium)>total){
					addActionError(getText("error.premiumInfo.excessPremium.exceeds.limit"));
				}*/
				if(Double.parseDouble(additionalPremium)>Double.parseDouble(totalPremium)){
					addActionError(getText("error.premiumInfo.excessPremium.exceeds.totalPre"));
				}
			}
		}
		if(!hasActionErrors() && "-".equals(premiumOption)){
			additionalPremium=premiumOption+additionalPremium.replaceAll("-", "");
			//additionalPremium=premiumOption+additionalPremium;
			
		}
	}
	private void validateSumInsured(){
		if(openCover.equalsIgnoreCase(productId) && !hasActionErrors() && "Y".equalsIgnoreCase(generatePolicy)){
			   String status=service.validateSumInsured(applicationNo,openCoverNo);
			   if("false".equalsIgnoreCase(status)){
	        	   this.addActionError(getText("sum.insured.limit.validate"));
	           }
		 }
	}
	@SuppressWarnings("unchecked")
	public String addClauses(){
		
		try{
			clauses();
			List<Object> tempList=new ArrayList<Object>();
			ArrayList<String> list=new ArrayList<String>();
			if(conditionsList!=null && !conditionsList.isEmpty())
			{
				if("1".equals(conditionType)){
					tempList=(List<Object>)conditionsList.get("clausesDesc");
					for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
						list.add(((Map<String, Object>)iterator.next()).get("CODE").toString());
					}
				}else if("2".equals(conditionType)){
					tempList=(List<Object>)conditionsList.get("extraClausesDesc");
					for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
						list.add(((Map<String, Object>)iterator.next()).get("CODE").toString());
					}
				}else if("3".equals(conditionType)){
					tempList=(List<Object>)conditionsList.get("exclusionsDesc");
					for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
						list.add(((Map<String, Object>)iterator.next()).get("CODE").toString());
					}
				}else if("4".equals(conditionType)){
					tempList=(List<Object>)conditionsList.get("warrantyDesc");
					for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
						list.add(((Map<String, Object>)iterator.next()).get("CODE").toString());
					}
				}
				if(list.isEmpty()){
					list.add(0, "0");
				}
				conditions=service.getExistingConditions(conditionType, belongingBranch, coverId, list);
			}
		}catch (Exception e)
		{
			logger.debug("Exception @ "+e);e.printStackTrace(); 
		}
		return "addConditions";
	}
	@SuppressWarnings("unchecked")
	public void updateConditionsInfo()
	{
		clauses();
		List<Object> tempList=new ArrayList<Object>();
		Map<String, Object> map=null;
		if(conditionsList!=null && !conditionsList.isEmpty()){
			clausesId=new ArrayList<Object>();
			clausesDesc=new ArrayList<Object>();
			warId=new ArrayList<Object>();
			warDesc=new ArrayList<Object>();
			warrantyId=new ArrayList<Object>();
			warrantyDesc=new ArrayList<Object>();
			exclusionId=new ArrayList<Object>();
			exclusionDesc=new ArrayList<Object>();
			tempList=(List<Object>)conditionsList.get("clausesDesc");
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				map=(Map<String, Object>)iterator.next();
				clausesId.add(map.get("CODE").toString());
				clausesDesc.add(map.get("CODEDESC").toString());
			}
			tempList=(List<Object>)conditionsList.get("extraClausesDesc");
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				map=(Map<String, Object>)iterator.next();
				warId.add(map.get("CODE").toString());
				warDesc.add(map.get("CODEDESC").toString());
			}
			tempList=(List<Object>)conditionsList.get("exclusionsDesc");
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				map=(Map<String, Object>)iterator.next();
				exclusionId.add(map.get("CODE").toString());
				exclusionDesc.add(map.get("CODEDESC").toString());	
			}
			tempList=(List<Object>)conditionsList.get("warrantyDesc");
			for (Iterator iterator = tempList.iterator(); iterator.hasNext();) {
				map=(Map<String, Object>)iterator.next();
				warrantyId.add(map.get("CODE").toString());
				warrantyDesc.add(map.get("CODEDESC").toString());
			}
			updateConditions();
		}
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getCreditNoteStatus(){
		if("User".equalsIgnoreCase(loginUserType)) {
			return service.getCreditNoteStatus(getAdminLoginId(),productId);
		}
		else {
			return "Y";
		}
	}

	public Map<String, String> getCyberSourceInfo() {
		return cyberSourceInfo;
	}

	public void setCyberSourceInfo(Map<String, String> cyberSourceInfo) {
		this.cyberSourceInfo = cyberSourceInfo;
	}
	public String onlinePay() {
		CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
		cyberSourceInfo=cyberIntg.createPay(merchantRefno);
		return "cybersource";
	}
	public String onlinepaymentRes() {
		String forward = "";
		try {	
			CyberSouceIntegration cyberIntg=new CyberSouceIntegration();
			Boolean isDone=cyberIntg.checkPayment(quoteNo);
			if(isDone) {
				String result=service.policyGeneration(StringUtils.defaultIfEmpty(refNo, applicationNo));
				if(StringUtils.isEmpty(result) || "invalid".equalsIgnoreCase(result)){
					addActionError(getText("error.premiumInfo.policy.invalid"));
				}else if(result.indexOf("ERROR")!=-1){
					addActionError(result);
				}else{
					policyNo=result;
					forward="policyInfo";
				}
				return forward;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "policyInfo";
	}

	public String getMerchantRefno() {
		return merchantRefno;
	}

	public void setMerchantRefno(String merchantRefno) {
		this.merchantRefno = merchantRefno;
	}
	public String regeneratePay() {
		 service.createMerchantRefNoWithStatus(quoteNo,applicationNo,"P","O",productId,this);
		 return "retJson";
	}

	public String getAdminLoginId() {
		return adminLoginId;
	}

	public void setAdminLoginId(String adminLoginId) {
		this.adminLoginId = adminLoginId;
	}
	public String deactivatePay() {
		 service.deactivatePay(quoteNo,applicationNo);
		 return "retJson";
	}
}
