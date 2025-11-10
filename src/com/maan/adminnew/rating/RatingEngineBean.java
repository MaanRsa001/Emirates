package com.maan.adminnew.rating;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;


public class RatingEngineBean{

	RatingEngineService rservice = new RatingEngineService();
	Map<String, Object> session = ActionContext.getContext().getSession();
	String branchCode = (String) session.get("BranchCode");
	private String transDesc;
	private String transRSA;
	private String transDO;
	private String transID;
	private String productID;
	private String productname;
	private String stagename;
	private String errorDesc;
	private String remarks;
	private String conveyName;
	private String conveyRate;
	private String code;
	private String eff_date;
	private String exp_date;
	private String status="N";
	private String conveyID;
	private String countryName;
	private String countryShortName;
	private String countryNat;
	private String geoRate;
	private String countryID;
	private String bankName;
	private String bankID;
	private String branchID;
	private String materialName;
	private String materialRate;
	private String materialID;
	private String warName;
	private String warRate;
	private String warID;
	private String saleName;
	private String saleValue;
	private String reqFrom;
	private String searchBy;
	private String searchValue;
	private String mode;
	private String saleID;
	private String toleName;
	private String toleValue;
	private String toleID;
	private String startAmt;
	private String endAmt;
	private String deductible;
	private String comExRate;
	private String comExID;
	private String vesselID;
	private String vesselName;
	private String vesselClass;
	private String vesselsYear;
	private String vesselType;
	private String vesselRate;
	private String warrantyDesc;
	private String warrantyID;
	private String categoryID;
	private String detailName;
	private String percentage;
	private String category_detail_id;
	private String constantID;
	private String countryDetID;
	private String countryDetSNO;
	private String startPlace;
	private String endPlace;
	private String sp;
	private String ep;
	private String agentID;
	private String agentName;
	private String shortName;
	private String address1;
	private String address2;
	private String address3;
	private String zipCode;
	private String teleNo;
	private String faxNo;
	private String mobileNo;
	private String email;
	private String coverageID;
	private String coveyID;
	private String baserateCountryID;
	private String baserateCommodityID;
	private String fragile;
	private String sumrate;
	private String transportRate;
	private String sumRRate;
	private String fragileRate;
	private String countryRate;
	private String conveyanceRate;
	private String commodityRate;
	private String wsrccRate;
	private String finalRate;
	private String txtchkICC_A_SEAClause_1;
	private String txtchkICC_A_SEAExclusive_1;
	private String txtchkICC_A_SEAWarranty_1;
	private String txtchkICC_A_SEAWarCover_1;
	private String radDetective_1="N";
	private String txtchkICC_A_SEAClause_2;
	private String txtchkICC_A_SEAExclusive_2;
	private String txtchkICC_A_SEAWarranty_2;
	private String txtchkICC_A_SEAWarCover_2;
	private String radDetective_2="N";
	private String txtchkICC_A_SEAClause_3;
	private String txtchkICC_A_SEAExclusive_3;
	private String txtchkICC_A_SEAWarranty_3;
	private String txtchkICC_A_SEAWarCover_3;
	private String radDetective_3="N";
	private String txtchkICC_A_SEAClause_8;
	private String txtchkICC_A_SEAExclusive_8;
	private String txtchkICC_A_SEAWarranty_8;
	private String txtchkICC_A_SEAWarCover_8;
	private String radDetective_8="N";
	private String txtchkICC_A_SEAClause_9;
	private String txtchkICC_A_SEAExclusive_9;
	private String txtchkICC_A_SEAWarranty_9;
	private String txtchkICC_A_SEAWarCover_9;
	private String radDetective_9="N";
	private String txtchkICC_A_SEAClause_10;
	private String txtchkICC_A_SEAExclusive_10;
	private String txtchkICC_A_SEAWarranty_10;
	private String txtchkICC_A_SEAWarCover_10;
	private String radDetective_10="N";
	private String txtchkICC_A_SEAClause_11;
	private String txtchkICC_A_SEAExclusive_11;
	private String txtchkICC_A_SEAWarranty_11;
	private String txtchkICC_A_SEAWarCover_11;
	private String radDetective_11="N";
	private String txtchkICC_A_SEAClause_12;
	private String txtchkICC_A_SEAExclusive_12;
	private String txtchkICC_A_SEAWarranty_12;
	private String txtchkICC_A_SEAWarCover_12;
	private String radDetective_12="N";

	private String txtchkICC_A_SEAClause_4;
	private String txtchkICC_A_SEAExclusive_4;
	private String txtchkICC_A_SEAWarranty_4;
	private String txtchkICC_A_SEAWarCover_4;
	private String radDetective_4="N";

	private String txtchkICC_A_SEAClause_5;
	private String txtchkICC_A_SEAExclusive_5;
	private String txtchkICC_A_SEAWarranty_5;
	private String txtchkICC_A_SEAWarCover_5;
	private String radDetective_5="N";

	private String txtchkICC_A_SEAClause_6;
	private String txtchkICC_A_SEAExclusive_6;
	private String txtchkICC_A_SEAWarranty_6;
	private String txtchkICC_A_SEAWarCover_6;
	private String radDetective_6="N";

	private String txtchkICC_A_SEAClause_7;
	private String txtchkICC_A_SEAExclusive_7;
	private String txtchkICC_A_SEAWarranty_7;
	private String txtchkICC_A_SEAWarCover_7;
	private String radDetective_7="N";

	private String txtchkICC_A_SEAClause_13;
	private String txtchkICC_A_SEAExclusive_13;
	private String txtchkICC_A_SEAWarranty_13;
	private String txtchkICC_A_SEAWarCover_13;
	private String radDetective_13="N";

	private String commodityName;
	private String commodityTypeID;
	private String commodityRates;
	private String coverage_Referal;
	private String commodityID;
	private String amendID;
	private String currencyShortName;
	private String rfactor;
	private String subCurrency;
	private String currencyType;
	private String currencyID;
	private String exchangeRate;
	private String exchangeID;
	private String coverID;
	private String coverName;
	private String coverDesc;
	private String coverRate;
	private String exclusionName;
	private String exclusionID;
	private String stageID;
	private String errorID;
	private String clauseID;
	private String clauseDesc;
	private String modeOfTransport;
	private String covername;
	private String displayorder;
	private String type;
	private String rsacode;
	private String displayorder1;
	private String modeTransportId;
	private String prevdate;
	private String wsrccid;
	private String wsrccdesc;
	private String edit;
	private String add;
	private String catname;
	private String catrate;
	private String catID;
	private String sno;
	private String referralStatus;
	private String coverArab;
	private double catrate1;
	private double coverRate1;
	//List
	private List<Map<String, Object>> transportList;
	private List<Map<String, Object>> conveyanceList;
	private List<Map<String, Object>> bankList;
	private List<Map<String, Object>> materialList;
	private List<Map<String, Object>> warrateList;
	private List<Map<String, Object>> saletermList;
	private List<Map<String, Object>> toleranceList;
	private List<Map<String, Object>> comExList;
	private List<Map<String, Object>> vesselList;
	private List<Map<String, Object>> extraCoverList;
	private List<Map<String, Object>> constantList;
	private List<Map<String, Object>> countryDetList;
	private List<Map<String, Object>> commodityList;
	private List<Map<String, Object>> currencyList;
	private List<Map<String, Object>> exchangeList;
	private List<Map<String, Object>> coverList;
	private List<Map<String, Object>> coverList1;
	private String display;
	private String title;
	private String startDate;
	private String endDate;
	private String categoryName;
	private String categoryId;
	private String addDetail;

	private String txtchkICC_A_SEAClause_14;
	private String txtchkICC_A_SEAExclusive_14;
	private String txtchkICC_A_SEAWarranty_14;
	private String txtchkICC_A_SEAWarCover_14;
	private String radDetective_14="N";
	
	private String exmexecutiveId;
	private String executiveName;
	private String otherpartyCode;
	private String exmProduct;
	private String exmEffectiveDate;
	private String exmStatus;
	private String exmRemarks;
	private String exmCommission;
	private String exmOpenCoverCommission;
	private String wordingFileName;
	private String wordingYN;
	private String commoditygroupId;
	
	public String getCommoditygroupId() {
		return commoditygroupId;
	}
	public void setCommoditygroupId(String commoditygroupId) {
		this.commoditygroupId = commoditygroupId;
	}

	List<RatingEngineBean> gridJsonList=new ArrayList<RatingEngineBean>();

	public double getCatrate1() {
		return catrate1;
	}
	public void setCatrate1(double catrate1) {
		this.catrate1 = catrate1;
	}
	public double getCoverRate1() {
		return coverRate1;
	}
	public void setCoverRate1(double coverRate1) {
		this.coverRate1 = coverRate1;
	}
	public String getCoverArab() {
		return coverArab;
	}
	public void setCoverArab(String coverArab) {
		this.coverArab = coverArab;
	}
	public String getReferralStatus() {
		return referralStatus;
	}
	public void setReferralStatus(String referralStatus) {
		this.referralStatus = referralStatus;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getCatID() {
		return catID;
	}
	public void setCatID(String catID) {
		this.catID = catID;
	}
	public String getExp_date() {
		return exp_date;
	}
	public void setExp_date(String expDate) {
		exp_date = expDate;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public String getCatrate() {
		return catrate;
	}
	public void setCatrate(String catrate) {
		this.catrate = catrate;
	}
	public String getAdd() {
		return add;
	}
	public void setAdd(String add) {
		this.add = add;
	}
	public String getEdit() {
		return edit;
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	public String getPrevdate() {
		return prevdate;
	}
	public void setPrevdate(String prevdate) {
		this.prevdate = prevdate;
	}
	public String getModeTransportId() {
		return modeTransportId;
	}
	public void setModeTransportId(String modeTransportId) {
		this.modeTransportId = modeTransportId;
	}
	public String getExclusionName() {
		return exclusionName;
	}
	public void setExclusionName(String exclusionName) {
		this.exclusionName = exclusionName;
	}
	public String getExclusionID() {
		return exclusionID;
	}
	public void setExclusionID(String exclusionID) {
		this.exclusionID = exclusionID;
	}
	public String getCoverID() {
		return coverID;
	}
	public void setCoverID(String coverID) {
		this.coverID = coverID;
	}
	public String getCoverName() {
		return coverName;
	}
	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}
	public String getCoverDesc() {
		return coverDesc;
	}
	public void setCoverDesc(String coverDesc) {
		this.coverDesc = coverDesc;
	}
	public String getCoverRate() {
		return coverRate;
	}
	public void setCoverRate(String coverRate) {
		this.coverRate = coverRate;
	}
	public String getExchangeID() {
		return exchangeID;
	}
	public void setExchangeID(String exchangeID) {
		this.exchangeID = exchangeID;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}

	public String getCurrencyShortName() {
		return currencyShortName;
	}
	public void setCurrencyShortName(String currencyShortName) {
		this.currencyShortName = currencyShortName;
	}
	public String getRfactor() {
		return rfactor;
	}
	public void setRfactor(String rfactor) {
		this.rfactor = rfactor;
	}
	public String getSubCurrency() {
		return subCurrency;
	}
	public void setSubCurrency(String subCurrency) {
		this.subCurrency = subCurrency;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getAmendID() {
		return amendID;
	}
	public void setAmendID(String amendID) {
		this.amendID = amendID;
	}


	public String getCommodityID() {
		return commodityID;
	}
	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}

	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityTypeID() {
		return commodityTypeID;
	}
	public void setCommodityTypeID(String commodityTypeID) {
		this.commodityTypeID = commodityTypeID;
	}
	public String getCommodityRates() {
		return commodityRates;
	}
	public void setCommodityRates(String commodityRates) {
		this.commodityRates = commodityRates;
	}
	public String getCoverage_Referal() {
		return coverage_Referal;
	}
	public void setCoverage_Referal(String coverage_Referal) {
		this.coverage_Referal = coverage_Referal;
	}
	public String getTxtchkICC_A_SEAClause_1() {
		return txtchkICC_A_SEAClause_1;
	}
	public void setTxtchkICC_A_SEAClause_1(String txtchkICC_A_SEAClause_1) {
		this.txtchkICC_A_SEAClause_1 = txtchkICC_A_SEAClause_1;
	}
	public String getTxtchkICC_A_SEAExclusive_1() {
		return txtchkICC_A_SEAExclusive_1;
	}
	public void setTxtchkICC_A_SEAExclusive_1(String txtchkICC_A_SEAExclusive_1) {
		this.txtchkICC_A_SEAExclusive_1 = txtchkICC_A_SEAExclusive_1;
	}
	public String getTxtchkICC_A_SEAWarranty_1() {
		return txtchkICC_A_SEAWarranty_1;
	}
	public void setTxtchkICC_A_SEAWarranty_1(String txtchkICC_A_SEAWarranty_1) {
		this.txtchkICC_A_SEAWarranty_1 = txtchkICC_A_SEAWarranty_1;
	}
	public String getTxtchkICC_A_SEAWarCover_1() {
		return txtchkICC_A_SEAWarCover_1;
	}
	public void setTxtchkICC_A_SEAWarCover_1(String txtchkICC_A_SEAWarCover_1) {
		this.txtchkICC_A_SEAWarCover_1 = txtchkICC_A_SEAWarCover_1;
	}
	public String getRadDetective_1() {
		return radDetective_1;
	}
	public void setRadDetective_1(String radDetective_1) {
		this.radDetective_1 = radDetective_1;
	}
	public String getTxtchkICC_A_SEAClause_2() {
		return txtchkICC_A_SEAClause_2;
	}
	public void setTxtchkICC_A_SEAClause_2(String txtchkICC_A_SEAClause_2) {
		this.txtchkICC_A_SEAClause_2 = txtchkICC_A_SEAClause_2;
	}
	public String getTxtchkICC_A_SEAExclusive_2() {
		return txtchkICC_A_SEAExclusive_2;
	}
	public void setTxtchkICC_A_SEAExclusive_2(String txtchkICC_A_SEAExclusive_2) {
		this.txtchkICC_A_SEAExclusive_2 = txtchkICC_A_SEAExclusive_2;
	}
	public String getTxtchkICC_A_SEAWarranty_2() {
		return txtchkICC_A_SEAWarranty_2;
	}
	public void setTxtchkICC_A_SEAWarranty_2(String txtchkICC_A_SEAWarranty_2) {
		this.txtchkICC_A_SEAWarranty_2 = txtchkICC_A_SEAWarranty_2;
	}
	public String getTxtchkICC_A_SEAWarCover_2() {
		return txtchkICC_A_SEAWarCover_2;
	}
	public void setTxtchkICC_A_SEAWarCover_2(String txtchkICC_A_SEAWarCover_2) {
		this.txtchkICC_A_SEAWarCover_2 = txtchkICC_A_SEAWarCover_2;
	}
	public String getRadDetective_2() {
		return radDetective_2;
	}
	public void setRadDetective_2(String radDetective_2) {
		this.radDetective_2 = radDetective_2;
	}
	public String getTxtchkICC_A_SEAClause_3() {
		return txtchkICC_A_SEAClause_3;
	}
	public void setTxtchkICC_A_SEAClause_3(String txtchkICC_A_SEAClause_3) {
		this.txtchkICC_A_SEAClause_3 = txtchkICC_A_SEAClause_3;
	}
	public String getTxtchkICC_A_SEAExclusive_3() {
		return txtchkICC_A_SEAExclusive_3;
	}
	public void setTxtchkICC_A_SEAExclusive_3(String txtchkICC_A_SEAExclusive_3) {
		this.txtchkICC_A_SEAExclusive_3 = txtchkICC_A_SEAExclusive_3;
	}
	public String getTxtchkICC_A_SEAWarranty_3() {
		return txtchkICC_A_SEAWarranty_3;
	}
	public void setTxtchkICC_A_SEAWarranty_3(String txtchkICC_A_SEAWarranty_3) {
		this.txtchkICC_A_SEAWarranty_3 = txtchkICC_A_SEAWarranty_3;
	}
	public String getTxtchkICC_A_SEAWarCover_3() {
		return txtchkICC_A_SEAWarCover_3;
	}
	public void setTxtchkICC_A_SEAWarCover_3(String txtchkICC_A_SEAWarCover_3) {
		this.txtchkICC_A_SEAWarCover_3 = txtchkICC_A_SEAWarCover_3;
	}
	public String getRadDetective_3() {
		return radDetective_3;
	}
	public void setRadDetective_3(String radDetective_3) {
		this.radDetective_3 = radDetective_3;
	}
	public String getTxtchkICC_A_SEAClause_8() {
		return txtchkICC_A_SEAClause_8;
	}
	public void setTxtchkICC_A_SEAClause_8(String txtchkICC_A_SEAClause_8) {
		this.txtchkICC_A_SEAClause_8 = txtchkICC_A_SEAClause_8;
	}
	public String getTxtchkICC_A_SEAExclusive_8() {
		return txtchkICC_A_SEAExclusive_8;
	}
	public void setTxtchkICC_A_SEAExclusive_8(String txtchkICC_A_SEAExclusive_8) {
		this.txtchkICC_A_SEAExclusive_8 = txtchkICC_A_SEAExclusive_8;
	}
	public String getTxtchkICC_A_SEAWarranty_8() {
		return txtchkICC_A_SEAWarranty_8;
	}
	public void setTxtchkICC_A_SEAWarranty_8(String txtchkICC_A_SEAWarranty_8) {
		this.txtchkICC_A_SEAWarranty_8 = txtchkICC_A_SEAWarranty_8;
	}
	public String getTxtchkICC_A_SEAWarCover_8() {
		return txtchkICC_A_SEAWarCover_8;
	}
	public void setTxtchkICC_A_SEAWarCover_8(String txtchkICC_A_SEAWarCover_8) {
		this.txtchkICC_A_SEAWarCover_8 = txtchkICC_A_SEAWarCover_8;
	}
	public String getRadDetective_8() {
		return radDetective_8;
	}
	public void setRadDetective_8(String radDetective_8) {
		this.radDetective_8 = radDetective_8;
	}
	public String getTxtchkICC_A_SEAClause_9() {
		return txtchkICC_A_SEAClause_9;
	}
	public void setTxtchkICC_A_SEAClause_9(String txtchkICC_A_SEAClause_9) {
		this.txtchkICC_A_SEAClause_9 = txtchkICC_A_SEAClause_9;
	}
	public String getTxtchkICC_A_SEAExclusive_9() {
		return txtchkICC_A_SEAExclusive_9;
	}
	public void setTxtchkICC_A_SEAExclusive_9(String txtchkICC_A_SEAExclusive_9) {
		this.txtchkICC_A_SEAExclusive_9 = txtchkICC_A_SEAExclusive_9;
	}
	public String getTxtchkICC_A_SEAWarranty_9() {
		return txtchkICC_A_SEAWarranty_9;
	}
	public void setTxtchkICC_A_SEAWarranty_9(String txtchkICC_A_SEAWarranty_9) {
		this.txtchkICC_A_SEAWarranty_9 = txtchkICC_A_SEAWarranty_9;
	}
	public String getTxtchkICC_A_SEAWarCover_9() {
		return txtchkICC_A_SEAWarCover_9;
	}
	public void setTxtchkICC_A_SEAWarCover_9(String txtchkICC_A_SEAWarCover_9) {
		this.txtchkICC_A_SEAWarCover_9 = txtchkICC_A_SEAWarCover_9;
	}
	public String getRadDetective_9() {
		return radDetective_9;
	}
	public void setRadDetective_9(String radDetective_9) {
		this.radDetective_9 = radDetective_9;
	}
	public String getTxtchkICC_A_SEAClause_10() {
		return txtchkICC_A_SEAClause_10;
	}
	public void setTxtchkICC_A_SEAClause_10(String txtchkICC_A_SEAClause_10) {
		this.txtchkICC_A_SEAClause_10 = txtchkICC_A_SEAClause_10;
	}
	public String getTxtchkICC_A_SEAExclusive_10() {
		return txtchkICC_A_SEAExclusive_10;
	}
	public void setTxtchkICC_A_SEAExclusive_10(String txtchkICC_A_SEAExclusive_10) {
		this.txtchkICC_A_SEAExclusive_10 = txtchkICC_A_SEAExclusive_10;
	}
	public String getTxtchkICC_A_SEAWarranty_10() {
		return txtchkICC_A_SEAWarranty_10;
	}
	public void setTxtchkICC_A_SEAWarranty_10(String txtchkICC_A_SEAWarranty_10) {
		this.txtchkICC_A_SEAWarranty_10 = txtchkICC_A_SEAWarranty_10;
	}
	public String getTxtchkICC_A_SEAWarCover_10() {
		return txtchkICC_A_SEAWarCover_10;
	}
	public void setTxtchkICC_A_SEAWarCover_10(String txtchkICC_A_SEAWarCover_10) {
		this.txtchkICC_A_SEAWarCover_10 = txtchkICC_A_SEAWarCover_10;
	}
	public String getRadDetective_10() {
		return radDetective_10;
	}
	public void setRadDetective_10(String radDetective_10) {
		this.radDetective_10 = radDetective_10;
	}
	public String getTxtchkICC_A_SEAClause_11() {
		return txtchkICC_A_SEAClause_11;
	}
	public void setTxtchkICC_A_SEAClause_11(String txtchkICC_A_SEAClause_11) {
		this.txtchkICC_A_SEAClause_11 = txtchkICC_A_SEAClause_11;
	}
	public String getTxtchkICC_A_SEAExclusive_11() {
		return txtchkICC_A_SEAExclusive_11;
	}
	public void setTxtchkICC_A_SEAExclusive_11(String txtchkICC_A_SEAExclusive_11) {
		this.txtchkICC_A_SEAExclusive_11 = txtchkICC_A_SEAExclusive_11;
	}
	public String getTxtchkICC_A_SEAWarranty_11() {
		return txtchkICC_A_SEAWarranty_11;
	}
	public void setTxtchkICC_A_SEAWarranty_11(String txtchkICC_A_SEAWarranty_11) {
		this.txtchkICC_A_SEAWarranty_11 = txtchkICC_A_SEAWarranty_11;
	}
	public String getTxtchkICC_A_SEAWarCover_11() {
		return txtchkICC_A_SEAWarCover_11;
	}
	public void setTxtchkICC_A_SEAWarCover_11(String txtchkICC_A_SEAWarCover_11) {
		this.txtchkICC_A_SEAWarCover_11 = txtchkICC_A_SEAWarCover_11;
	}
	public String getRadDetective_11() {
		return radDetective_11;
	}
	public void setRadDetective_11(String radDetective_11) {
		this.radDetective_11 = radDetective_11;
	}
	public String getTxtchkICC_A_SEAClause_12() {
		return txtchkICC_A_SEAClause_12;
	}
	public void setTxtchkICC_A_SEAClause_12(String txtchkICC_A_SEAClause_12) {
		this.txtchkICC_A_SEAClause_12 = txtchkICC_A_SEAClause_12;
	}
	public String getTxtchkICC_A_SEAExclusive_12() {
		return txtchkICC_A_SEAExclusive_12;
	}
	public void setTxtchkICC_A_SEAExclusive_12(String txtchkICC_A_SEAExclusive_12) {
		this.txtchkICC_A_SEAExclusive_12 = txtchkICC_A_SEAExclusive_12;
	}
	public String getTxtchkICC_A_SEAWarranty_12() {
		return txtchkICC_A_SEAWarranty_12;
	}
	public void setTxtchkICC_A_SEAWarranty_12(String txtchkICC_A_SEAWarranty_12) {
		this.txtchkICC_A_SEAWarranty_12 = txtchkICC_A_SEAWarranty_12;
	}
	public String getTxtchkICC_A_SEAWarCover_12() {
		return txtchkICC_A_SEAWarCover_12;
	}
	public void setTxtchkICC_A_SEAWarCover_12(String txtchkICC_A_SEAWarCover_12) {
		this.txtchkICC_A_SEAWarCover_12 = txtchkICC_A_SEAWarCover_12;
	}
	public String getRadDetective_12() {
		return radDetective_12;
	}
	public void setRadDetective_12(String radDetective_12) {
		this.radDetective_12 = radDetective_12;
	}
	public String getTransportRate() {
		return transportRate;
	}
	public void setTransportRate(String transportRate) {
		this.transportRate = transportRate;
	}
	public String getSumRRate() {
		return sumRRate;
	}
	public void setSumRRate(String sumRRate) {
		this.sumRRate = sumRRate;
	}
	public String getFragileRate() {
		return fragileRate;
	}
	public void setFragileRate(String fragileRate) {
		this.fragileRate = fragileRate;
	}
	public String getCountryRate() {
		return countryRate;
	}
	public void setCountryRate(String countryRate) {
		this.countryRate = countryRate;
	}
	public String getConveyanceRate() {
		return conveyanceRate;
	}
	public void setConveyanceRate(String conveyanceRate) {
		this.conveyanceRate = conveyanceRate;
	}
	public String getCommodityRate() {
		return commodityRate;
	}
	public void setCommodityRate(String commodityRate) {
		this.commodityRate = commodityRate;
	}
	public String getWsrccRate() {
		return wsrccRate;
	}
	public void setWsrccRate(String wsrccRate) {
		this.wsrccRate = wsrccRate;
	}
	public String getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(String finalRate) {
		this.finalRate = finalRate;
	}
	public String getCoverageID() {
		return coverageID;
	}
	public void setCoverageID(String coverageID) {
		this.coverageID = coverageID;
	}
	public String getCoveyID() {
		return coveyID;
	}
	public void setCoveyID(String coveyID) {
		this.coveyID = coveyID;
	}
	public String getBaserateCountryID() {
		return baserateCountryID;
	}
	public void setBaserateCountryID(String baserateCountryID) {
		this.baserateCountryID = baserateCountryID;
	}
	public String getBaserateCommodityID() {
		return baserateCommodityID;
	}
	public void setBaserateCommodityID(String baserateCommodityID) {
		this.baserateCommodityID = baserateCommodityID;
	}
	public String getFragile() {
		return fragile;
	}
	public void setFragile(String fragile) {
		this.fragile = fragile;
	}
	public String getSumrate() {
		return sumrate;
	}
	public void setSumrate(String sumrate) {
		this.sumrate = sumrate;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getTeleNo() {
		return teleNo;
	}
	public void setTeleNo(String teleNo) {
		this.teleNo = teleNo;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAgentID() {
		return agentID;
	}
	public void setAgentID(String agentID) {
		this.agentID = agentID;
	}

	public String getStartPlace() {
		return startPlace;
	}
	public void setStartPlace(String startPlace) {
		this.startPlace = startPlace;
	}


	public String getEndPlace() {
		return endPlace;
	}
	public void setEndPlace(String endPlace) {
		this.endPlace = endPlace;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public String getEp() {
		return ep;
	}
	public void setEp(String ep) {
		this.ep = ep;
	}
	public String getCountryDetID() {
		return countryDetID;
	}
	public void setCountryDetID(String countryDetID) {
		this.countryDetID = countryDetID;
	}
	public String getCountryDetSNO() {
		return countryDetSNO;
	}
	public void setCountryDetSNO(String countryDetSNO) {
		this.countryDetSNO = countryDetSNO;
	}
	public String getConstantID() {
		return constantID;
	}
	public void setConstantID(String constantID) {
		this.constantID = constantID;
	}

	public String getCategory_detail_id() {
		return category_detail_id;
	}
	public void setCategory_detail_id(String categoryDetailId) {
		category_detail_id = categoryDetailId;
	}

	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getDetailName() {
		return detailName;
	}
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public String getWarrantyID() {
		return warrantyID;
	}
	public void setWarrantyID(String warrantyID) {
		this.warrantyID = warrantyID;
	}
	public String getWarrantyDesc() {
		return warrantyDesc;
	}
	public void setWarrantyDesc(String warrantyDesc) {
		this.warrantyDesc = warrantyDesc;
	}

	private String extraCoverName;
	private String extraCoverId;
	private String cityName;
	private String cityID;

	public String getVesselType() {
		return vesselType;
	}
	public void setVesselType(String vesselType) {
		this.vesselType = vesselType;
	}
	public void setVesselsYear(String vesselsYear) {
		this.vesselsYear = vesselsYear;
	}
	public String getVesselsYear() {
		return vesselsYear;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getVesselClass() {
		return vesselClass;
	}
	public void setVesselClass(String vesselClass) {
		this.vesselClass = vesselClass;
	}
	public String getVesselID() {
		return vesselID;
	}
	public void setVesselID(String vesselID) {
		this.vesselID = vesselID;
	}

	public String getVesselRate() {
		return vesselRate;
	}
	public void setVesselRate(String vesselRate) {
		this.vesselRate = vesselRate;
	}

	public String getComExID() {
		return comExID;
	}
	public void setComExID(String comExID) {
		this.comExID = comExID;
	}

	public String getStartAmt() {
		return startAmt;
	}
	public void setStartAmt(String startAmt) {
		this.startAmt = startAmt;
	}
	public String getEndAmt() {
		return endAmt;
	}
	public void setEndAmt(String endAmt) {
		this.endAmt = endAmt;
	}
	public String getDeductible() {
		return deductible;
	}
	public void setDeductible(String deductible) {
		this.deductible = deductible;
	}
	public String getComExRate() {
		return comExRate;
	}
	public void setComExRate(String comExRate) {
		this.comExRate = comExRate;
	}

	public String getToleID() {
		return toleID;
	}
	public void setToleID(String toleID) {
		this.toleID = toleID;
	}

	public String getToleName() {
		return toleName;
	}
	public void setToleName(String toleName) {
		this.toleName = toleName;
	}
	public String getToleValue() {
		return toleValue;
	}
	public void setToleValue(String toleValue) {
		this.toleValue = toleValue;
	}
	public String getSaleID() {
		return saleID;
	}
	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

	public String getSaleName() {
		return saleName;
	}
	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}
	public String getSaleValue() {
		return saleValue;
	}
	public void setSaleValue(String saleValue) {
		this.saleValue = saleValue;
	}

	public String getWarID() {
		return warID;
	}
	public void setWarID(String warID) {
		this.warID = warID;
	}
	public String getWarName() {
		return warName;
	}
	public void setWarName(String warName) {
		this.warName = warName;
	}
	public String getWarRate() {
		return warRate;
	}
	public void setWarRate(String warRate) {
		this.warRate = warRate;
	}

	public String getMaterialID() {
		return materialID;
	}
	public void setMaterialID(String materialID) {
		this.materialID = materialID;
	}

	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialRate() {
		return materialRate;
	}
	public void setMaterialRate(String materialRate) {
		this.materialRate = materialRate;
	}

	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}

	public String getBankID() {
		return bankID;
	}
	public void setBankID(String bankID) {
		this.bankID = bankID;
	}

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCountryID() {
		return countryID;
	}
	public void setCountryID(String countryID) {
		this.countryID = countryID;
	}


	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryNat() {
		return countryNat;
	}
	public void setCountryNat(String countryNat) {
		this.countryNat = countryNat;
	}
	public String getCountryShortName() {
		return countryShortName;
	}
	public void setCountryShortName(String countryShortName) {
		this.countryShortName = countryShortName;
	}
	public String getGeoRate() {
		return geoRate;
	}
	public void setGeoRate(String geoRate) {
		this.geoRate = geoRate;
	}


	public String getConveyID() {
		return conveyID;
	}
	public void setConveyID(String conveyID) {
		this.conveyID = conveyID;
	}

	public String getConveyName() {
		return conveyName;
	}
	public void setConveyName(String conveyName) {
		this.conveyName = conveyName;
	}
	public String getConveyRate() {
		return conveyRate;
	}
	public void setConveyRate(String conveyRate) {
		this.conveyRate = conveyRate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getEff_date() {
		return eff_date;
	}
	public void setEff_date(String eff_date) {
		this.eff_date = eff_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getStagename() {
		return stagename;
	}
	public void setStagename(String stagename) {
		this.stagename = stagename;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getTransID() {
		return transID;
	}
	public void setTransID(String transID) {
		this.transID = transID;
	}
	public String getTransDesc() {
		return transDesc;
	}
	public void setTransDesc(String transDesc) {
		this.transDesc = transDesc;
	}
	public String getTransRSA() {
		return transRSA;
	}
	public void setTransRSA(String transRSA) {
		this.transRSA = transRSA;
	}
	public String getTransDO() {
		return transDO;
	}
	public void setTransDO(String transDO) {
		this.transDO = transDO;
	}
	public String getExtraCoverName() {
		return extraCoverName;
	}
	public void setExtraCoverName(String extraCoverName) {
		this.extraCoverName = extraCoverName;
	}
	public String getExtraCoverId() {
		return extraCoverId;
	}
	public void setExtraCoverId(String extraCoverId) {
		this.extraCoverId = extraCoverId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	public String getErrorID()
	{
		return errorID;
	}

	public void setErrorID(String errorID)
	{
		this.errorID = errorID;
	}



	public void setProductName(String productName)
	{
		productname = productName;
	}

	public void setstageName(String stageName)
	{
		stagename = stageName;
	}

	public Object getStageID()
	{
		return stageID;
	}

	public String getClauseID()
	{
		return clauseID;
	}

	public void setClauseID(String clauseID)
	{
		this.clauseID = clauseID;
	}

	public String getClauseDesc()
	{
		return clauseDesc;
	}

	public void setClauseDesc(String clauseDesc)
	{
		this.clauseDesc = clauseDesc;
	}

	public String getCovername()
	{
		return covername;
	}

	public void setCovername(String covername)
	{
		this.covername = covername;
	}

	public String getModeOfTransport()
	{
		return modeOfTransport;
	}

	public void setModeOfTransport(String modeOfTransport)
	{
		this.modeOfTransport = modeOfTransport;
	}

	public String gettransID()
	{
		return transID;
	}

	public void settransID(String transID)
	{
		this.transID = transID;
	}

	public String getDisplayorder()
	{
		return displayorder;
	}

	public void setDisplayorder(String displayorder)
	{
		this.displayorder = displayorder;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getRsacode()
	{
		return rsacode;
	}

	public void setRsacode(String rsacode)
	{
		this.rsacode = rsacode;
	}

	public String getDisplayorder1()
	{
		return displayorder1;
	}

	public void setDisplayorder1(String displayorder1)
	{
		this.displayorder1 = displayorder1;
	}
	public String getWsrccid()
	{
		return wsrccid;
	}

	public void setWsrccid(String wsrccid)
	{
		this.wsrccid = wsrccid;
	}
	public String getWsrccdesc()
	{
		return wsrccdesc;
	}

	public void setWsrccdesc(String wsrccdesc)
	{
		this.wsrccdesc = wsrccdesc;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCoverList() {
		return coverList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String,Object>> getCoverList1() {
		return rservice.getCoverList1(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getExchangeList() {
		return exchangeList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCurrencyList() {
		return currencyList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCommodityList() {
		return commodityList;
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBaserateCountry() {
		return rservice.getBaserateCountry();

	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBaserateCommodity() {		
		return rservice.getBaserateCommodity(branchCode);		
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getTransports() {
		return rservice.getTransports(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCountries() {
		return rservice.getCountries(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List <Map<String, Object>> getCountriesDetail()
	{
		return rservice.getCountriesDetail();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List <Map<String, Object>> getNewcountriesDetail()
	{
		return rservice.getNewcountriesDetail();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List <Map<String, Object>> getCountriesDet1()
	{
		return rservice.getCountriesDet1();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getConveyance() {
		return rservice.getConveyance(branchCode);
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCountryDetList() {
		return countryDetList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getConstantList() {
		return constantList;
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getExtraCoverList() {
		return extraCoverList;
	}

	private List<Map<String, Object>> vesselYear;

	private List<Map<String, Object>> warrantyList;
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getWarrantyList() {
		return warrantyList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getVesselYear() {
		return vesselYear;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getVesselList() {
		return vesselList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getComExList() {
		return comExList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getToleranceList() {
		return toleranceList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getSaletermList() {
		return saletermList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getWarrateList() {
		return warrateList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBankList() {
		return bankList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getMaterialList() {
		return materialList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCategories() {
		return rservice.getCategories(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getCommodityType() {
		return rservice.getCommodityType(branchCode);
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)

	public List<Map<String, Object>> getExchanges() {
		return rservice.getExchanges(branchCode);
	}

	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getProductsDET() {
		return rservice.getProductsDET(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getMaterials() {
		return rservice.getMaterials(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getStage() {
		return rservice.getStage();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBank() {
		return rservice.getBank();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getBranch() {
		return rservice.getBranch();
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getWarrates() {
		return rservice.getWarrates(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getSaleterms() {
		return rservice.getSaleterms(branchCode);
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getTransportList() {
		return transportList;
	}
	@org.apache.struts2.json.annotations.JSON(serialize = false)
	public List<Map<String, Object>> getConveyanceList() {
		return conveyanceList;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getTxtchkICC_A_SEAClause_13() {
		return txtchkICC_A_SEAClause_13;
	}
	public void setTxtchkICC_A_SEAClause_13(String txtchkICCASEAClause_13) {
		txtchkICC_A_SEAClause_13 = txtchkICCASEAClause_13;
	}
	public String getTxtchkICC_A_SEAExclusive_13() {
		return txtchkICC_A_SEAExclusive_13;
	}
	public void setTxtchkICC_A_SEAExclusive_13(String txtchkICCASEAExclusive_13) {
		txtchkICC_A_SEAExclusive_13 = txtchkICCASEAExclusive_13;
	}
	public String getTxtchkICC_A_SEAWarranty_13() {
		return txtchkICC_A_SEAWarranty_13;
	}
	public void setTxtchkICC_A_SEAWarranty_13(String txtchkICCASEAWarranty_13) {
		txtchkICC_A_SEAWarranty_13 = txtchkICCASEAWarranty_13;
	}
	public String getTxtchkICC_A_SEAWarCover_13() {
		return txtchkICC_A_SEAWarCover_13;
	}
	public void setTxtchkICC_A_SEAWarCover_13(String txtchkICCASEAWarCover_13) {
		txtchkICC_A_SEAWarCover_13 = txtchkICCASEAWarCover_13;
	}
	public String getRadDetective_13() {
		return radDetective_13;
	}
	public void setRadDetective_13(String radDetective_13) {
		this.radDetective_13 = radDetective_13;
	}
	public String getTxtchkICC_A_SEAClause_4() {
		return txtchkICC_A_SEAClause_4;
	}
	public void setTxtchkICC_A_SEAClause_4(String txtchkICCASEAClause_4) {
		txtchkICC_A_SEAClause_4 = txtchkICCASEAClause_4;
	}
	public String getTxtchkICC_A_SEAExclusive_4() {
		return txtchkICC_A_SEAExclusive_4;
	}
	public void setTxtchkICC_A_SEAExclusive_4(String txtchkICCASEAExclusive_4) {
		txtchkICC_A_SEAExclusive_4 = txtchkICCASEAExclusive_4;
	}
	public String getTxtchkICC_A_SEAWarranty_4() {
		return txtchkICC_A_SEAWarranty_4;
	}
	public void setTxtchkICC_A_SEAWarranty_4(String txtchkICCASEAWarranty_4) {
		txtchkICC_A_SEAWarranty_4 = txtchkICCASEAWarranty_4;
	}
	public String getTxtchkICC_A_SEAWarCover_4() {
		return txtchkICC_A_SEAWarCover_4;
	}
	public void setTxtchkICC_A_SEAWarCover_4(String txtchkICCASEAWarCover_4) {
		txtchkICC_A_SEAWarCover_4 = txtchkICCASEAWarCover_4;
	}
	public String getRadDetective_4() {
		return radDetective_4;
	}
	public void setRadDetective_4(String radDetective_4) {
		this.radDetective_4 = radDetective_4;
	}
	public String getTxtchkICC_A_SEAClause_5() {
		return txtchkICC_A_SEAClause_5;
	}
	public void setTxtchkICC_A_SEAClause_5(String txtchkICCASEAClause_5) {
		txtchkICC_A_SEAClause_5 = txtchkICCASEAClause_5;
	}
	public String getTxtchkICC_A_SEAExclusive_5() {
		return txtchkICC_A_SEAExclusive_5;
	}
	public void setTxtchkICC_A_SEAExclusive_5(String txtchkICCASEAExclusive_5) {
		txtchkICC_A_SEAExclusive_5 = txtchkICCASEAExclusive_5;
	}
	public String getTxtchkICC_A_SEAWarranty_5() {
		return txtchkICC_A_SEAWarranty_5;
	}
	public void setTxtchkICC_A_SEAWarranty_5(String txtchkICCASEAWarranty_5) {
		txtchkICC_A_SEAWarranty_5 = txtchkICCASEAWarranty_5;
	}
	public String getTxtchkICC_A_SEAWarCover_5() {
		return txtchkICC_A_SEAWarCover_5;
	}
	public void setTxtchkICC_A_SEAWarCover_5(String txtchkICCASEAWarCover_5) {
		txtchkICC_A_SEAWarCover_5 = txtchkICCASEAWarCover_5;
	}
	public String getRadDetective_5() {
		return radDetective_5;
	}
	public void setRadDetective_5(String radDetective_5) {
		this.radDetective_5 = radDetective_5;
	}
	public String getTxtchkICC_A_SEAClause_6() {
		return txtchkICC_A_SEAClause_6;
	}
	public void setTxtchkICC_A_SEAClause_6(String txtchkICCASEAClause_6) {
		txtchkICC_A_SEAClause_6 = txtchkICCASEAClause_6;
	}
	public String getTxtchkICC_A_SEAExclusive_6() {
		return txtchkICC_A_SEAExclusive_6;
	}
	public void setTxtchkICC_A_SEAExclusive_6(String txtchkICCASEAExclusive_6) {
		txtchkICC_A_SEAExclusive_6 = txtchkICCASEAExclusive_6;
	}
	public String getTxtchkICC_A_SEAWarranty_6() {
		return txtchkICC_A_SEAWarranty_6;
	}
	public void setTxtchkICC_A_SEAWarranty_6(String txtchkICCASEAWarranty_6) {
		txtchkICC_A_SEAWarranty_6 = txtchkICCASEAWarranty_6;
	}
	public String getTxtchkICC_A_SEAWarCover_6() {
		return txtchkICC_A_SEAWarCover_6;
	}
	public void setTxtchkICC_A_SEAWarCover_6(String txtchkICCASEAWarCover_6) {
		txtchkICC_A_SEAWarCover_6 = txtchkICCASEAWarCover_6;
	}
	public String getRadDetective_6() {
		return radDetective_6;
	}
	public void setRadDetective_6(String radDetective_6) {
		this.radDetective_6 = radDetective_6;
	}
	public String getTxtchkICC_A_SEAClause_7() {
		return txtchkICC_A_SEAClause_7;
	}
	public void setTxtchkICC_A_SEAClause_7(String txtchkICCASEAClause_7) {
		txtchkICC_A_SEAClause_7 = txtchkICCASEAClause_7;
	}
	public String getTxtchkICC_A_SEAExclusive_7() {
		return txtchkICC_A_SEAExclusive_7;
	}
	public void setTxtchkICC_A_SEAExclusive_7(String txtchkICCASEAExclusive_7) {
		txtchkICC_A_SEAExclusive_7 = txtchkICCASEAExclusive_7;
	}
	public String getTxtchkICC_A_SEAWarranty_7() {
		return txtchkICC_A_SEAWarranty_7;
	}
	public void setTxtchkICC_A_SEAWarranty_7(String txtchkICCASEAWarranty_7) {
		txtchkICC_A_SEAWarranty_7 = txtchkICCASEAWarranty_7;
	}
	public String getTxtchkICC_A_SEAWarCover_7() {
		return txtchkICC_A_SEAWarCover_7;
	}
	public void setTxtchkICC_A_SEAWarCover_7(String txtchkICCASEAWarCover_7) {
		txtchkICC_A_SEAWarCover_7 = txtchkICCASEAWarCover_7;
	}
	public String getRadDetective_7() {
		return radDetective_7;
	}
	public void setRadDetective_7(String radDetective_7) {
		this.radDetective_7 = radDetective_7;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getAddDetail() {
		return addDetail;
	}
	public void setAddDetail(String addDetail) {
		this.addDetail = addDetail;
	}

	public String getTxtchkICC_A_SEAClause_14() {
		return txtchkICC_A_SEAClause_14;
	}
	public void setTxtchkICC_A_SEAClause_14(String txtchkICCASEAClause_14) {
		txtchkICC_A_SEAClause_14 = txtchkICCASEAClause_14;
	}
	public String getTxtchkICC_A_SEAExclusive_14() {
		return txtchkICC_A_SEAExclusive_14;
	}
	public void setTxtchkICC_A_SEAExclusive_14(String txtchkICCASEAExclusive_14) {
		txtchkICC_A_SEAExclusive_14 = txtchkICCASEAExclusive_14;
	}
	public String getTxtchkICC_A_SEAWarranty_14() {
		return txtchkICC_A_SEAWarranty_14;
	}
	public void setTxtchkICC_A_SEAWarranty_14(String txtchkICCASEAWarranty_14) {
		txtchkICC_A_SEAWarranty_14 = txtchkICCASEAWarranty_14;
	}
	public String getTxtchkICC_A_SEAWarCover_14() {
		return txtchkICC_A_SEAWarCover_14;
	}
	public void setTxtchkICC_A_SEAWarCover_14(String txtchkICCASEAWarCover_14) {
		txtchkICC_A_SEAWarCover_14 = txtchkICCASEAWarCover_14;
	}
	public String getRadDetective_14() {
		return radDetective_14;
	}
	public void setRadDetective_14(String radDetective_14) {
		this.radDetective_14 = radDetective_14;
	}
	public String getExmexecutiveId() {
		return exmexecutiveId;
	}
	public void setExmexecutiveId(String exmexecutiveId) {
		this.exmexecutiveId = exmexecutiveId;
	}
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
	public String getExmProduct() {
		return exmProduct;
	}
	public void setExmProduct(String exmProduct) {
		this.exmProduct = exmProduct;
	}
	public String getExmEffectiveDate() {
		return exmEffectiveDate;
	}
	public void setExmEffectiveDate(String exmEffectiveDate) {
		this.exmEffectiveDate = exmEffectiveDate;
	}
	public String getExmStatus() {
		return exmStatus;
	}
	public void setExmStatus(String exmStatus) {
		this.exmStatus = exmStatus;
	}
	public String getOtherpartyCode() {
		return otherpartyCode;
	}
	public void setOtherpartyCode(String otherpartyCode) {
		this.otherpartyCode = otherpartyCode;
	}
	public String getExmRemarks() {
		return exmRemarks;
	}
	public void setExmRemarks(String exmRemarks) {
		this.exmRemarks = exmRemarks;
	}
	public String getExmCommission() {
		return exmCommission;
	}
	public void setExmCommission(String exmCommission) {
		this.exmCommission = exmCommission;
	}
	public String getExmOpenCoverCommission() {
		return exmOpenCoverCommission;
	}
	public void setExmOpenCoverCommission(String exmOpenCoverCommission) {
		this.exmOpenCoverCommission = exmOpenCoverCommission;
	}
	public String getWordingFileName() {
		return wordingFileName;
	}
	public void setWordingFileName(String wordingFileName) {
		this.wordingFileName = wordingFileName;
	}
	public String getWordingYN() {
		return wordingYN;
	}
	public void setWordingYN(String wordingYN) {
		this.wordingYN = wordingYN;
	}
	public List<RatingEngineBean> getGridJsonList() {
		return gridJsonList;
	}
	public void setGridJsonList(List<RatingEngineBean> gridJsonList) {
		this.gridJsonList = gridJsonList;
	}
	
	private String crossvoyageyn;
	private String crossvoyageOrgin;
	private String crossvoyageDestn;

	public String getCrossvoyageyn() {
		return crossvoyageyn;
	}
	public void setCrossvoyageyn(String crossvoyageyn) {
		this.crossvoyageyn = crossvoyageyn;
	}
	public String getCrossvoyageOrgin() {
		return crossvoyageOrgin;
	}
	public void setCrossvoyageOrgin(String crossvoyageOrgin) {
		this.crossvoyageOrgin = crossvoyageOrgin;
	}
	public String getCrossvoyageDestn() {
		return crossvoyageDestn;
	}
	public void setCrossvoyageDestn(String crossvoyageDestn) {
		this.crossvoyageDestn = crossvoyageDestn;
	}
}