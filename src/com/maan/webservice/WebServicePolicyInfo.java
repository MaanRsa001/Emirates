package com.maan.webservice;

public class WebServicePolicyInfo {
	private String refNo;
	private String applicationNo;
	private String quoteNo;
	private String policyNo;
	private String brokerCode;
	private String loginId;
	private String type;
	private String openCoverPolicyNo;
	private String customerId;
	private String clientCustomerId;
	private String customerName;
	private String custVatRegNo;
	private String custVatRegYn;
	private String address1;
	private String address2;
	private String poBox;
	private String country;
	private String emirate;
	private String email;
	private String mobileNo;
	private String modeOfTransport;
	private String originatingCountry;
	private String destinationCountry;
	private String originatingCountryCity;
	private String destinationCountryCity;
	private String originatingCountryCityName;
	private String destinationCountryCityName;
	private String originatingWareHouseCoverage;
	private String destinationWareHouseCoverage;
	private String saleTerm;
	private String saleTermValue;
	private String currency;
	private String settlingAgent;
	private String totalSumInsured;
	private String policyStartDate;
	private String sailingDate;
	private String vesselId;
	private String vesselName;
	private String lcNumber;
	private String issuingBank;
	private String lcDate;
	private String partialShipment;
	private String blAwbNumber;
	private String blAwbDate;
	private String premium;
	private String remarks;
	private String status;
	private String endorsement;
	private String clauses;
	private String warranties;
	private String exclusions;
	private String receivedDate;
	private String responseTime;
	private String policyFilePath;
	private String draftFilePath;
	private String coverages;
	private String seaCoverages;
	private String wsrcc;
	private String productId;
	private String companyId;
	private String branchCode;
	private String currencyValue;
	private String tolerance;
	private String toleranceValue;
	private String partialShipmentAmount;
	private String minimumPremium;
	private String excessPremium="0";
	private String referralStatus;
	private String brokerOriginatingCountryId;
	private String brokerDestinationCountryId;
	private String brokerDecimalPlace;
	private String brokerCurrencyType;
	private String brokerTax;
	private String brokerSite;
	private String pdfPremiumYN;
	private String pdfBankerYN;
	private String pdfCurrencyYN;
	private String pdfRubberStampYN;
	private String pdfBankerAssuredYN;
	private String basePath;
	private String noteType;
	private String paymentMode;
	private String basisVal;
	private String packageType;
	private String pdfGenerateStatus;
	private String custContractNo;
	private String custFmsCaseNo;
	private String custRefNo;
	private String issuer;
	private String custTitle;
	private String custCoreCode;
	private String settlingAgentName;
	private String exposureCurrency;
	private String executive;
	private String quoteScheduleFilePath;
	private String debitFilePath;
	private String creditFilePath;
	private String appMode;
	private String certClausesYN;
	private String adminReferralYN;
	private String adminReferralRemarks;
	private String custArNo;
	private String userType;
	private String exchangeId;
	private String currencyName;
	private String exposureCurrencyValue;
	private String totalCommodity;
	private String totalPremium="0";
	private String totalMarinePremium;
	private String totalWarPremium;
	private String totalSaleterm;
	private String totalTolerance;
	private String sessionId;
	private String brokerType;
	private String referralType;
	private String policyFee;
	private String govtTax;
	private String emergencyFund;
	private String debitCustomerId;
	private String openCoverBackDateDays;
	private String openCoverCrossVoyage;
	private String openCoverCrossVoyageLimit;
	private String sumInsuredLimit;
	private String businessType;
	private String amendId;
	private boolean endt;
	private String endtPremYN;
	private String endtClausesYN;
	private String pdfExcessYN;
	private String pdfRemarksYN;
	private String promotinalCode;
	private String promotinalDiscount;
	private String loginBranch;
	private String excessPrecentage;
	private String consigneeDetail;
	private String specialTerm;
	private String addCustomerName;
	private String coreCustomerName;
	/*private String lcAmount;
	private String lcExchangeValue;
	private String lcCurrency;
	private String lcId;*/
	private String channelType;
	private String vatApplicable;
	private String shipmentTransit;
	
	public String getShipmentTransit() {
		return shipmentTransit;
	}
	public void setShipmentTransit(String shipmentTransit) {
		this.shipmentTransit = shipmentTransit;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	public String getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getPolicyNo() {
		return policyNo;
	}
	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOpenCoverPolicyNo() {
		return openCoverPolicyNo;
	}
	public void setOpenCoverPolicyNo(String openCoverPolicyNo) {
		this.openCoverPolicyNo = openCoverPolicyNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getClientCustomerId() {
		return clientCustomerId;
	}
	public void setClientCustomerId(String clientCustomerId) {
		this.clientCustomerId = clientCustomerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmirate() {
		return emirate;
	}
	public void setEmirate(String emirate) {
		this.emirate = emirate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public String getOriginatingCountry() {
		return originatingCountry;
	}
	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}
	public String getDestinationCountry() {
		return destinationCountry;
	}
	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}
	public String getOriginatingCountryCity() {
		return originatingCountryCity;
	}
	public void setOriginatingCountryCity(String originatingCountryCity) {
		this.originatingCountryCity = originatingCountryCity;
	}
	public String getDestinationCountryCity() {
		return destinationCountryCity;
	}
	public void setDestinationCountryCity(String destinationCountryCity) {
		this.destinationCountryCity = destinationCountryCity;
	}
	public String getOriginatingCountryCityName() {
		return originatingCountryCityName;
	}
	public void setOriginatingCountryCityName(String originatingCountryCityName) {
		this.originatingCountryCityName = originatingCountryCityName;
	}
	public String getDestinationCountryCityName() {
		return destinationCountryCityName;
	}
	public void setDestinationCountryCityName(String destinationCountryCityName) {
		this.destinationCountryCityName = destinationCountryCityName;
	}
	public String getOriginatingWareHouseCoverage() {
		return originatingWareHouseCoverage;
	}
	public void setOriginatingWareHouseCoverage(String originatingWareHouseCoverage) {
		this.originatingWareHouseCoverage = originatingWareHouseCoverage;
	}
	public String getDestinationWareHouseCoverage() {
		return destinationWareHouseCoverage;
	}
	public void setDestinationWareHouseCoverage(String destinationWareHouseCoverage) {
		this.destinationWareHouseCoverage = destinationWareHouseCoverage;
	}
	public String getSaleTerm() {
		return saleTerm;
	}
	public void setSaleTerm(String saleTerm) {
		this.saleTerm = saleTerm;
	}
	public String getSaleTermValue() {
		return saleTermValue;
	}
	public void setSaleTermValue(String saleTermValue) {
		this.saleTermValue = saleTermValue;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getSettlingAgent() {
		return settlingAgent;
	}
	public void setSettlingAgent(String settlingAgent) {
		this.settlingAgent = settlingAgent;
	}
	public String getTotalSumInsured() {
		return totalSumInsured;
	}
	public void setTotalSumInsured(String totalSumInsured) {
		this.totalSumInsured = totalSumInsured;
	}
	public String getPolicyStartDate() {
		return policyStartDate;
	}
	public void setPolicyStartDate(String policyStartDate) {
		this.policyStartDate = policyStartDate;
	}
	public String getSailingDate() {
		return sailingDate;
	}
	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}
	public String getVesselId() {
		return vesselId;
	}
	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getLcNumber() {
		return lcNumber;
	}
	public void setLcNumber(String lcNumber) {
		this.lcNumber = lcNumber;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getLcDate() {
		return lcDate;
	}
	public void setLcDate(String lcDate) {
		this.lcDate = lcDate;
	}
	public String getPartialShipment() {
		return partialShipment;
	}
	public void setPartialShipment(String partialShipment) {
		this.partialShipment = partialShipment;
	}
	public String getBlAwbNumber() {
		return blAwbNumber;
	}
	public void setBlAwbNumber(String blAwbNumber) {
		this.blAwbNumber = blAwbNumber;
	}
	public String getBlAwbDate() {
		return blAwbDate;
	}
	public void setBlAwbDate(String blAwbDate) {
		this.blAwbDate = blAwbDate;
	}
	public String getPremium() {
		return premium;
	}
	public void setPremium(String premium) {
		this.premium = premium;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndorsement() {
		return endorsement;
	}
	public void setEndorsement(String endorsement) {
		this.endorsement = endorsement;
	}
	public String getClauses() {
		return clauses;
	}
	public void setClauses(String clauses) {
		this.clauses = clauses;
	}
	public String getWarranties() {
		return warranties;
	}
	public void setWarranties(String warranties) {
		this.warranties = warranties;
	}
	public String getExclusions() {
		return exclusions;
	}
	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	public String getPolicyFilePath() {
		return policyFilePath;
	}
	public void setPolicyFilePath(String policyFilePath) {
		this.policyFilePath = policyFilePath;
	}
	public String getDraftFilePath() {
		return draftFilePath;
	}
	public void setDraftFilePath(String draftFilePath) {
		this.draftFilePath = draftFilePath;
	}
	public String getCoverages() {
		return coverages;
	}
	public void setCoverages(String coverages) {
		this.coverages = coverages;
	}
	public String getSeaCoverages() {
		return seaCoverages;
	}
	public void setSeaCoverages(String seaCoverages) {
		this.seaCoverages = seaCoverages;
	}
	public String getWsrcc() {
		return wsrcc;
	}
	public void setWsrcc(String wsrcc) {
		this.wsrcc = wsrcc;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getCurrencyValue() {
		return currencyValue;
	}
	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}
	public String getTolerance() {
		return tolerance;
	}
	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}
	public String getToleranceValue() {
		return toleranceValue;
	}
	public void setToleranceValue(String toleranceValue) {
		this.toleranceValue = toleranceValue;
	}
	public String getPartialShipmentAmount() {
		return partialShipmentAmount;
	}
	public void setPartialShipmentAmount(String partialShipmentAmount) {
		this.partialShipmentAmount = partialShipmentAmount;
	}
	public String getMinimumPremium() {
		return minimumPremium;
	}
	public void setMinimumPremium(String minimumPremium) {
		this.minimumPremium = minimumPremium;
	}
	public String getExcessPremium() {
		return excessPremium;
	}
	public void setExcessPremium(String excessPremium) {
		this.excessPremium = excessPremium;
	}
	public String getReferralStatus() {
		return referralStatus;
	}
	public void setReferralStatus(String referralStatus) {
		this.referralStatus = referralStatus;
	}
	public String getBrokerOriginatingCountryId() {
		return brokerOriginatingCountryId;
	}
	public void setBrokerOriginatingCountryId(String brokerOriginatingCountryId) {
		this.brokerOriginatingCountryId = brokerOriginatingCountryId;
	}
	public String getBrokerDestinationCountryId() {
		return brokerDestinationCountryId;
	}
	public void setBrokerDestinationCountryId(String brokerDestinationCountryId) {
		this.brokerDestinationCountryId = brokerDestinationCountryId;
	}
	public String getBrokerDecimalPlace() {
		return brokerDecimalPlace;
	}
	public void setBrokerDecimalPlace(String brokerDecimalPlace) {
		this.brokerDecimalPlace = brokerDecimalPlace;
	}
	public String getBrokerCurrencyType() {
		return brokerCurrencyType;
	}
	public void setBrokerCurrencyType(String brokerCurrencyType) {
		this.brokerCurrencyType = brokerCurrencyType;
	}
	public String getBrokerTax() {
		return brokerTax;
	}
	public void setBrokerTax(String brokerTax) {
		this.brokerTax = brokerTax;
	}
	public String getBrokerSite() {
		return brokerSite;
	}
	public void setBrokerSite(String brokerSite) {
		this.brokerSite = brokerSite;
	}
	public String getPdfPremiumYN() {
		return pdfPremiumYN;
	}
	public void setPdfPremiumYN(String pdfPremiumYN) {
		this.pdfPremiumYN = pdfPremiumYN;
	}
	public String getPdfBankerYN() {
		return pdfBankerYN;
	}
	public void setPdfBankerYN(String pdfBankerYN) {
		this.pdfBankerYN = pdfBankerYN;
	}
	public String getPdfCurrencyYN() {
		return pdfCurrencyYN;
	}
	public void setPdfCurrencyYN(String pdfCurrencyYN) {
		this.pdfCurrencyYN = pdfCurrencyYN;
	}
	public String getPdfRubberStampYN() {
		return pdfRubberStampYN;
	}
	public void setPdfRubberStampYN(String pdfRubberStampYN) {
		this.pdfRubberStampYN = pdfRubberStampYN;
	}
	public String getPdfBankerAssuredYN() {
		return pdfBankerAssuredYN;
	}
	public void setPdfBankerAssuredYN(String pdfBankerAssuredYN) {
		this.pdfBankerAssuredYN = pdfBankerAssuredYN;
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getBasisVal() {
		return basisVal;
	}
	public void setBasisVal(String basisVal) {
		this.basisVal = basisVal;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getPdfGenerateStatus() {
		return pdfGenerateStatus;
	}
	public void setPdfGenerateStatus(String pdfGenerateStatus) {
		this.pdfGenerateStatus = pdfGenerateStatus;
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
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getCustTitle() {
		return custTitle;
	}
	public void setCustTitle(String custTitle) {
		this.custTitle = custTitle;
	}
	public String getCustCoreCode() {
		return custCoreCode;
	}
	public void setCustCoreCode(String custCoreCode) {
		this.custCoreCode = custCoreCode;
	}
	public String getSettlingAgentName() {
		return settlingAgentName;
	}
	public void setSettlingAgentName(String settlingAgentName) {
		this.settlingAgentName = settlingAgentName;
	}
	public String getExposureCurrency() {
		return exposureCurrency;
	}
	public void setExposureCurrency(String exposureCurrency) {
		this.exposureCurrency = exposureCurrency;
	}
	public String getExecutive() {
		return executive;
	}
	public void setExecutive(String executive) {
		this.executive = executive;
	}
	public String getQuoteScheduleFilePath() {
		return quoteScheduleFilePath;
	}
	public void setQuoteScheduleFilePath(String quoteScheduleFilePath) {
		this.quoteScheduleFilePath = quoteScheduleFilePath;
	}
	public String getDebitFilePath() {
		return debitFilePath;
	}
	public void setDebitFilePath(String debitFilePath) {
		this.debitFilePath = debitFilePath;
	}
	public String getCreditFilePath() {
		return creditFilePath;
	}
	public void setCreditFilePath(String creditFilePath) {
		this.creditFilePath = creditFilePath;
	}
	public String getAppMode() {
		return appMode;
	}
	public void setAppMode(String appMode) {
		this.appMode = appMode;
	}
	public String getCertClausesYN() {
		return certClausesYN;
	}
	public void setCertClausesYN(String certClausesYN) {
		this.certClausesYN = certClausesYN;
	}
	public String getAdminReferralYN() {
		return adminReferralYN;
	}
	public void setAdminReferralYN(String adminReferralYN) {
		this.adminReferralYN = adminReferralYN;
	}
	public String getAdminReferralRemarks() {
		return adminReferralRemarks;
	}
	public void setAdminReferralRemarks(String adminReferralRemarks) {
		this.adminReferralRemarks = adminReferralRemarks;
	}
	public String getCustArNo() {
		return custArNo;
	}
	public void setCustArNo(String custArNo) {
		this.custArNo = custArNo;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getExchangeId() {
		return exchangeId;
	}
	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}
	public String getCurrencyName() {
		return currencyName;
	}
	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
	public String getExposureCurrencyValue() {
		return exposureCurrencyValue;
	}
	public void setExposureCurrencyValue(String exposureCurrencyValue) {
		this.exposureCurrencyValue = exposureCurrencyValue;
	}
	public String getTotalCommodity() {
		return totalCommodity;
	}
	public void setTotalCommodity(String totalCommodity) {
		this.totalCommodity = totalCommodity;
	}
	public String getTotalPremium() {
		return totalPremium;
	}
	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}
	public String getTotalMarinePremium() {
		return totalMarinePremium;
	}
	public void setTotalMarinePremium(String totalMarinePremium) {
		this.totalMarinePremium = totalMarinePremium;
	}
	public String getTotalWarPremium() {
		return totalWarPremium;
	}
	public void setTotalWarPremium(String totalWarPremium) {
		this.totalWarPremium = totalWarPremium;
	}
	public String getTotalSaleterm() {
		return totalSaleterm;
	}
	public void setTotalSaleterm(String totalSaleterm) {
		this.totalSaleterm = totalSaleterm;
	}
	public String getTotalTolerance() {
		return totalTolerance;
	}
	public void setTotalTolerance(String totalTolerance) {
		this.totalTolerance = totalTolerance;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getBrokerType() {
		return brokerType;
	}
	public void setBrokerType(String brokerType) {
		this.brokerType = brokerType;
	}
	public String getReferralType() {
		return referralType;
	}
	public void setReferralType(String referralType) {
		this.referralType = referralType;
	}
	public String getPolicyFee() {
		return policyFee;
	}
	public void setPolicyFee(String policyFee) {
		this.policyFee = policyFee;
	}
	public String getGovtTax() {
		return govtTax;
	}
	public void setGovtTax(String govtTax) {
		this.govtTax = govtTax;
	}
	public String getEmergencyFund() {
		return emergencyFund;
	}
	public void setEmergencyFund(String emergencyFund) {
		this.emergencyFund = emergencyFund;
	}
	public String getDebitCustomerId() {
		return debitCustomerId;
	}
	public void setDebitCustomerId(String debitCustomerId) {
		this.debitCustomerId = debitCustomerId;
	}
	public String getOpenCoverBackDateDays() {
		return openCoverBackDateDays;
	}
	public void setOpenCoverBackDateDays(String openCoverBackDateDays) {
		this.openCoverBackDateDays = openCoverBackDateDays;
	}
	public String getOpenCoverCrossVoyage() {
		return openCoverCrossVoyage;
	}
	public void setOpenCoverCrossVoyage(String openCoverCrossVoyage) {
		this.openCoverCrossVoyage = openCoverCrossVoyage;
	}
	public String getOpenCoverCrossVoyageLimit() {
		return openCoverCrossVoyageLimit;
	}
	public void setOpenCoverCrossVoyageLimit(String openCoverCrossVoyageLimit) {
		this.openCoverCrossVoyageLimit = openCoverCrossVoyageLimit;
	}
	public String getSumInsuredLimit() {
		return sumInsuredLimit;
	}
	public void setSumInsuredLimit(String sumInsuredLimit) {
		this.sumInsuredLimit = sumInsuredLimit;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}
	public boolean isEndt() {
		return endt;
	}
	public void setEndt(boolean endt) {
		this.endt = endt;
	}
	public String getEndtPremYN() {
		return endtPremYN;
	}
	public void setEndtPremYN(String endtPremYN) {
		this.endtPremYN = endtPremYN;
	}
	public String getEndtClausesYN() {
		return endtClausesYN;
	}
	public void setEndtClausesYN(String endtClausesYN) {
		this.endtClausesYN = endtClausesYN;
	}
	public String getPdfExcessYN() {
		return pdfExcessYN;
	}
	public void setPdfExcessYN(String pdfExcessYN) {
		this.pdfExcessYN = pdfExcessYN;
	}
	public String getPdfRemarksYN() {
		return pdfRemarksYN;
	}
	public void setPdfRemarksYN(String pdfRemarksYN) {
		this.pdfRemarksYN = pdfRemarksYN;
	}
	public String getPromotinalCode() {
		return promotinalCode;
	}
	public void setPromotinalCode(String promotinalCode) {
		this.promotinalCode = promotinalCode;
	}
	public String getPromotinalDiscount() {
		return promotinalDiscount;
	}
	public void setPromotinalDiscount(String promotinalDiscount) {
		this.promotinalDiscount = promotinalDiscount;
	}
	public String getLoginBranch() {
		return loginBranch;
	}
	public void setLoginBranch(String loginBranch) {
		this.loginBranch = loginBranch;
	}
	public String getExcessPrecentage() {
		return excessPrecentage;
	}
	public void setExcessPrecentage(String excessPrecentage) {
		this.excessPrecentage = excessPrecentage;
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
	/*public String getLcAmount() {
		return lcAmount;
	}
	public void setLcAmount(String lcAmount) {
		this.lcAmount = lcAmount;
	}
	public String getLcExchangeValue() {
		return lcExchangeValue;
	}
	public void setLcExchangeValue(String lcExchangeValue) {
		this.lcExchangeValue = lcExchangeValue;
	}
	public String getLcCurrency() {
		return lcCurrency;
	}
	public void setLcCurrency(String lcCurrency) {
		this.lcCurrency = lcCurrency;
	}
	public String getLcId() {
		return lcId;
	}
	public void setLcId(String lcId) {
		this.lcId = lcId;
	}*/
	public String getCoreCustomerName() {
		return coreCustomerName;
	}
	public void setCoreCustomerName(String coreCustomerName) {
		this.coreCustomerName = coreCustomerName;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
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

}
