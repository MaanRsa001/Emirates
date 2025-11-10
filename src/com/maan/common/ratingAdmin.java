package com.maan.common;

import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.DBCon.DBConnection;

import com.maan.common.error.ErrorInfo;
import com.maan.common.util.StringUtil;
import com.maan.services.util.dataCollection;
import com.maan.services.util.runner;

public class ratingAdmin extends ErrorInfo {
	private static final Logger logger = LogUtil.getLogger(ratingAdmin.class);

	private String coreApplicationCode = "";
	private String saleTermValue = "";

	private PrintWriter out = null;
	private Statement smt = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;
	public String selectBusi = "";
	public String countryid = "";
	public String countryname = "";
	public String countryshortname = "";
	public String geoRate = "";
	public String matID = "";
	public String matDesc = "";
	public String matRate = "";
	public String warID = "";
	public String warDesc = "";
	public String warrate = "";
	public String startingplace = "";
	public String spwarrantiesconditions = "";
	public String nationalityname = "";
	public String endingplace = "";
	public String epwarrantiesconditions = "";
	public String status1 = "";
	public String rsacode = "";
	public String amendid1 = "";
	public String remarks1 = "";
	public String startDate1 = "";
	public String commodity_id = "";
	public String commodity_name = "";
	public String fragile = "";
	public String rag = "";
	public String commodityType = "";
	public String icc_a = "";
	public String startDate = "";
	public String exclusion_id = "";
	public String warranty_id = "";
	public String remarks = "";
	public String status = "";
	public String amendid = "";
	public String type_id = "";
	public String type_desc = "";
	public String branch_code = "";
	public String agency_code = "";
	public String policy_prefix = "";
	public String start_no = "";
	public String end_no = "";
	public String current_no = "";
	public String policy_quote = "";
	public String login_id = "";
	public String description = "";
	public String product_id1 = "";
	public String startDate2 = "";
	public String remarks2 = "";
	public String status2 = "";
	public String amend_id = "";
	public String stat = "";
	public String quoteno = "";
	public String COMMODITY_EXCESS_PREMIUM = "0";
	public String COVERAGE_REFERAL="";

	



	// New Commodity Master
	public String commodityId = "";
	public String commodityName = "";
	public String icc_a_sea = "";
	public String icc_a_frozen_meat_sea = "";
	public String icc_a_frozen_food_sea = "";
	public String icc_c_frozen_meat_sea = "";
	public String icc_c_frozen_food_sea = "";
	public String icc_b_sea = "";
	public String af_icc_a_sea = "";
	public String icc_c_sea = "";
	public String icc_c_nd_sea = "";
	public String af_icc_c_nd_sea = "";
	public String af_icc_c_sea = "";
	public String limited_cover = "";
	public String frozen_cargo_sea = "";
	public String af_frozen_cargo_sea = "";

	public String af_icc_air = "";
	public String af_limited_cover_air = "";
	public String icc_air = "";
	public String limited_cover_air = "";
	public String frozen_cargo_air = "";
	public String af_frozen_cargo_air = "";

	public String af_all_risk_land = "";
	public String af_limited_cover_land = "";
	public String all_risk_land = "";
	public String limited_cover_land = "";
	public String frozen_cargo_land = "";
	public String af_frozen_cargo_land = "";

	public String af_all_risk_air_sea_land = "";
	public String all_risk_air_sea_land = "";
	public String frozen_cargo_air_sea_land = "";
	public String af_frozen_cargo_air_sea_land = "";
	public String exclusionId = "";
	public String warrantyId = "";
	public String amendId = "";
	public String icc_air_cargo_air = "";
	public String all_risks_parcel_post_air = "";
	public String all_risks_land_transit_land = "";
	public String land_transit_land = "";
	public String all_risks_sea_and_air = "";
	public String effective_date = "";
	public String commodity_type_id = "";
	public String  commrate="";
	public String  modeOfTransID="";
	
/////////////////////////  POLICY INTEGRATION REPORT //////////////////////////////
	
	public String cmsvalue="";
	public String ocidcode="";
public String getWarID() {
		return warID;
	}

	public void setWarID(String warID) {
		this.warID = warID;
	}

public String getCmsvalue() {
		return cmsvalue;
	}

	public void setCmsvalue(String cmsvalue) {
		this.cmsvalue = cmsvalue;
	}

	public String getOcidcode() {
		return ocidcode;
	}

	public void setOcidcode(String ocidcode) {
		this.ocidcode = ocidcode;
	}



// ******************************    POLICY REPORT BY NETHAJI S.P 13-06-2012 ****************************************
///////////////   POLICY REPORT Variable Declaration       //////////////////////////	
public String PRstartdate="";
public String PRenddate="";
public String PRproduct="";
public String oldvalue="";
////////////////////Policy Report Getter And Setter method  //////////////////////////////



public String getPRstartdate() {
	return PRstartdate;
}

public void setPRstartdate(String pRstartdate) {
	PRstartdate = pRstartdate;
}

public String getPRenddate() {
	return PRenddate;
}

public void setPRenddate(String pRenddate) {
	PRenddate = pRenddate;
}

public String getPRproduct() {
	return PRproduct;
}

public void setPRproduct(String pRproduct) {
	PRproduct = pRproduct;
}
public String getOldvalue() {
	return oldvalue;
}

public void setOldvalue(String oldvalue) {
	this.oldvalue = oldvalue;
}

//////////////////////   POLICY INTEGRATION Core Application VALIDATION   /////////////

public void validatePolicyIntegration(String branch)
{
	if(cmsvalue.equals(""))
	{
		setError("Enter the Core Application Value");
	}
	 if(!cmsvalue.equals(""))
	{
		if(cmsvalue.length()<14)
		setError("Core Application Value Should be 14 Character");
	}
	if(!cmsvalue.equals("") && cmsvalue.length()>=14)
	{
		int n=0;
    for (int i = 0; i < cmsvalue.length(); i++) {
       
        if (!Character.isDigit(cmsvalue.charAt(i)))
        	n++;        
    }        
    if(n>0)
    {
    	setError("Core Application Value Should Be Numeric");
     }
	}
	if(ocidcode.equals(""))
	{
		setError("Enter the OICD Value");
	}
	
	
}
//////    Policy Report Validation //////////////
public void validatePolicyReportDetails(String branch)
{
	System.out.println("PRproduct:"+PRproduct);
	if(PRproduct.equals(""))
	{
		setError("Select Product Name");
	}
}


	
//////////////////    Convey Entry Variable Declaration   /////////////////////////
	public String ConveyName= "";
	public String ConveyStatus="";
	public String ConveyRate= "";
	public String ConveyRemarks= "";
	public String ConveyCoreAppCode= "";
	public String ConveyModeOfTransport= "";
	public String ConveyMEffectiveDate= "";
	public String ConveyId= "";
	public String ConveyAmendId= "";
	
////////////////////  Convey Getter And Setter method  //////////////////////////////
	
	public String getMatID() {
		return matID;
	}

	public void setMatID(String matID) {
		this.matID = matID;
	}

	public String getMatDesc() {
		return matDesc;
	}

	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}

	public String getMatRate() {
		return matRate;
	}

	public void setMatRate(String matRate) {
		this.matRate = matRate;
	}

	public String getModeOfTransID() {
		return modeOfTransID;
	}

	public void setModeOfTransID(String modeOfTransID) {
		this.modeOfTransID = modeOfTransID;
	}
	
	public String getConveyId() {
		return ConveyId;
	}

	public void setConveyId(String conveyId) {
		ConveyId = conveyId;
	}
	public String getConveyAmendId() {
		return ConveyAmendId;
	}

	public void setConveyAmendId(String conveyAmendId) {
		ConveyAmendId = conveyAmendId;
	}
	
	public String getConveyName() {
		return ConveyName;
	}

	public void setConveyName(String conveyName) {
		ConveyName = conveyName;
	}

	public String getConveyRate() {
		return ConveyRate;
	}

	public void setConveyRate(String conveyRate) {
		ConveyRate = conveyRate;
	}

	public String getConveyRemarks() {
		return ConveyRemarks;
	}

	public void setConveyRemarks(String conveyRemarks) {
		ConveyRemarks = conveyRemarks;
	}

	public String getConveyCoreAppCode() {
		return ConveyCoreAppCode;
	}

	public void setConveyCoreAppCode(String conveyCoreAppCode) {
		ConveyCoreAppCode = conveyCoreAppCode;
	}

	public String getConveyModeOfTransport() {
		return ConveyModeOfTransport;
	}

	public void setConveyModeOfTransport(String conveyModeOfTransport) {
		ConveyModeOfTransport = conveyModeOfTransport;
	}

	public String getConveyMEffectiveDate() {
		return ConveyMEffectiveDate;
	}

	public void setConveyMEffectiveDate(String conveyMEffectiveDate) {
		ConveyMEffectiveDate = conveyMEffectiveDate;
	}
	public String getConveyStatus() {
		return ConveyStatus;
	}

	public void setConveyStatus(String conveyStatus) {
		ConveyStatus = conveyStatus;
	}
////////////////////////////////////////////////////////////////////////////
	
////////////////////    COMMODITY EXCESS VARIBLE DECLARATION    //////////////////////
	
	String CommodExcessId ="";
	String CommodExcessStartInsured="";		
	String CommodExcessEndInsured="";
	String CommodExcessDeductible="";
	String CommoExcessRate="";
	String CommodExcessEffectiveDate="";
	String CommodExcessStatus="";
	String CommodExcessAmendId ="";

	
////////////////////COMMODITY EXCESS Getter And Setter Method   //////////////////////	
	
	public String getCommodExcessId() {
		return CommodExcessId;
	}

	public void setCommodExcessId(String commodExcessId) {
		CommodExcessId = commodExcessId;
	}

	public String getCommodExcessStartInsured() {
		return CommodExcessStartInsured;
	}

	public void setCommodExcessStartInsured(String commodExcessStartInsured) {
		CommodExcessStartInsured = commodExcessStartInsured;
	}

	public String getCommodExcessEndInsured() {
		return CommodExcessEndInsured;
	}

	public void setCommodExcessEndInsured(String commodExcessEndInsured) {
		CommodExcessEndInsured = commodExcessEndInsured;
	}

	public String getCommodExcessDeductible() {
		return CommodExcessDeductible;
	}

	public void setCommodExcessDeductible(String commodExcessDeductible) {
		CommodExcessDeductible = commodExcessDeductible;
	}

	public String getCommoExcessRate() {
		return CommoExcessRate;
	}

	public void setCommoExcessRate(String commoExcessRate) {
		CommoExcessRate = commoExcessRate;
	}

	public String getCommodExcessEffectiveDate() {
		return CommodExcessEffectiveDate;
	}

	public void setCommodExcessEffectiveDate(String commodExcessEffectiveDate) {
		CommodExcessEffectiveDate = commodExcessEffectiveDate;
	}

	public String getCommodExcessStatus() {
		return CommodExcessStatus;
	}

	public void setCommodExcessStatus(String commodExcessStatus) {
		CommodExcessStatus = commodExcessStatus;
	}

	public String getCommodExcessAmendId() {
		return CommodExcessAmendId;
	}

	public void setCommodExcessAmendId(String commodExcessAmendId) {
		CommodExcessAmendId = commodExcessAmendId;
	}
///////////////////////////////////////////////////////////////////////////////////////	

	public String getCommrate() {
		return commrate;
	}

	public void setCommrate(String commrate) {
		this.commrate = commrate;
	}
	
	public String iccClause[]=null;
	public String iccExclusion[]=null;
	public String iccWarrenty[]=null;
	public String iccwarcover[]=null;
	public String iccdeductive[]=null;
	
	
	public String[] getIccdeductive() {
		return iccdeductive;
	}

	public void setIccdeductive(String[] iccdeductive) {
		this.iccdeductive = iccdeductive;
	}

	public String[] getIccwarcover() {
		return iccwarcover;
	}

	public void setIccwarcover(String[] iccwarcover) {
		this.iccwarcover = iccwarcover;
	}

	public String[] getIccClause() {
		return iccClause;
	}

	public void setIccClause(String[] iccClause) {
		this.iccClause = iccClause;
	}

	
	public String[] getIccExclusion() {
		return iccExclusion;
	}

	public void setIccExclusion(String[] iccExclusion) {
		this.iccExclusion = iccExclusion;
	}
	public String[] getIccWarrenty() {
		return iccWarrenty;
	}

	public void setIccWarrenty(String[] iccWarrenty) {
		this.iccWarrenty = iccWarrenty;
	}

	public String getWarrantyId() {
		return warrantyId;
	}
	public void setWarrantyId(String warrantyId) {
		this.warrantyId = warrantyId;
	}

	public String getGeoRate() {
		return geoRate;
	}
	public void setGeoRate(String geoRate) {
		this.geoRate = geoRate;
	}
	
	public String getIcc_air_cargo_air() {
		return icc_air_cargo_air;
	}

	public void setIcc_air_cargo_air(String iccAirCargoAir) {
		icc_air_cargo_air = iccAirCargoAir;
	}

	public String getAll_risks_parcel_post_air() {
		return all_risks_parcel_post_air;
	}

	public void setAll_risks_parcel_post_air(String allRisksParcelPostAir) {
		all_risks_parcel_post_air = allRisksParcelPostAir;
	}

	public String getAll_risks_land_transit_land() {
		return all_risks_land_transit_land;
	}

	public void setAll_risks_land_transit_land(String allRisksLandTransitLand) {
		all_risks_land_transit_land = allRisksLandTransitLand;
	}

	public String getLand_transit_land() {
		return land_transit_land;
	}

	public void setLand_transit_land(String landTransitLand) {
		land_transit_land = landTransitLand;
	}

	public String getAll_risks_sea_and_air() {
		return all_risks_sea_and_air;
	}

	public void setAll_risks_sea_and_air(String allRisksSeaAndAir) {
		all_risks_sea_and_air = allRisksSeaAndAir;
	}
	
	public String getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(String effectiveDate) {
		effective_date = effectiveDate;
	}

	public String getCommodity_type_id() {
		return commodity_type_id;
	}

	public void setCommodity_type_id(String commodityTypeId) {
		commodity_type_id = commodityTypeId;
	}

	public String getCoreApplicationCode() {
		return coreApplicationCode;
	}

	public void setCoreApplicationCode(String coreApplicationCode) {
		this.coreApplicationCode = coreApplicationCode;
	}

	public String getSaleTermValue() {
		return saleTermValue;
	}

	public void setSaleTermValue(String saleTermValue) {
		this.saleTermValue = saleTermValue;
	}

	/*public String getAf_all_risk_air_sea_land() {
		return af_all_risk_air_sea_land;
	}

	public void setAf_all_risk_air_sea_land(String af_all_risk_air_sea_land) {
		this.af_all_risk_air_sea_land = af_all_risk_air_sea_land;
	}

	public String getAf_all_risk_land() {
		return af_all_risk_land;
	}

	public void setAf_all_risk_land(String af_all_risk_land) {
		this.af_all_risk_land = af_all_risk_land;
	}

	public String getAf_frozen_cargo_air() {
		return af_frozen_cargo_air;
	}

	public void setAf_frozen_cargo_air(String af_frozen_cargo_air) {
		this.af_frozen_cargo_air = af_frozen_cargo_air;
	}

	public String getAf_frozen_cargo_air_sea_land() {
		return af_frozen_cargo_air_sea_land;
	}

	public void setAf_frozen_cargo_air_sea_land(
			String af_frozen_cargo_air_sea_land) {
		this.af_frozen_cargo_air_sea_land = af_frozen_cargo_air_sea_land;
	}

	public String getAf_frozen_cargo_land() {
		return af_frozen_cargo_land;
	}

	public void setAf_frozen_cargo_land(String af_frozen_cargo_land) {
		this.af_frozen_cargo_land = af_frozen_cargo_land;
	}

	public String getAf_frozen_cargo_sea() {
		return af_frozen_cargo_sea;
	}

	public void setAf_frozen_cargo_sea(String af_frozen_cargo_sea) {
		this.af_frozen_cargo_sea = af_frozen_cargo_sea;
	}

	public String getAf_icc_a_sea() {
		return af_icc_a_sea;
	}

	public void setAf_icc_a_sea(String af_icc_a_sea) {
		this.af_icc_a_sea = af_icc_a_sea;
	}

	public String getAf_icc_air() {
		return af_icc_air;
	}

	public void setAf_icc_air(String af_icc_air) {
		this.af_icc_air = af_icc_air;
	}

	public String getAf_icc_c_nd_sea() {
		return af_icc_c_nd_sea;
	}

	public void setAf_icc_c_nd_sea(String af_icc_c_nd_sea) {
		this.af_icc_c_nd_sea = af_icc_c_nd_sea;
	}

	public String getAf_icc_c_sea() {
		return af_icc_c_sea;
	}

	public void setAf_icc_c_sea(String af_icc_c_sea) {
		this.af_icc_c_sea = af_icc_c_sea;
	}

	public String getAf_limited_cover_air() {
		return af_limited_cover_air;
	}

	public void setAf_limited_cover_air(String af_limited_cover_air) {
		this.af_limited_cover_air = af_limited_cover_air;
	}

	public String getAf_limited_cover_land() {
		return af_limited_cover_land;
	}

	public void setAf_limited_cover_land(String af_limited_cover_land) {
		this.af_limited_cover_land = af_limited_cover_land;
	}

	public String getAll_risk_air_sea_land() {
		return all_risk_air_sea_land;
	}

	public void setAll_risk_air_sea_land(String all_risk_air_sea_land) {
		this.all_risk_air_sea_land = all_risk_air_sea_land;
	}

	public String getAll_risk_land() {
		return all_risk_land;
	}

	public void setAll_risk_land(String all_risk_land) {
		this.all_risk_land = all_risk_land;
	}*/

	public String getAmendId() {
		return amendId;
	}

	public void setAmendId(String amendId) {
		this.amendId = amendId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getExclusionId() {
		return exclusionId;
	}

	public void setExclusionId(String exclusionId) {
		this.exclusionId = exclusionId;
	}

	
	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getStat() {
		return stat;
	}

	public void setQuoteno(String quoteno) {
		this.quoteno = quoteno;
	}

	public String getQuoteno() {
		return quoteno;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	

	public String getType_id() {
		return type_id;
	}

	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

	public String getType_desc() {
		return type_desc;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setAgency_code(String agency_code) {
		this.agency_code = agency_code;
	}

	public String getAgency_code() {
		return agency_code;
	}

	public void setPolicy_prefix(String policy_prefix) {
		this.policy_prefix = policy_prefix;
	}

	public String getPolicy_prefix() {
		return policy_prefix;
	}

	public void setStart_no(String start_no) {
		this.start_no = start_no;
	}

	public String getStart_no() {
		return start_no;
	}

	public void setEnd_no(String end_no) {
		this.end_no = end_no;
	}

	public String getEnd_no() {
		return end_no;
	}

	public void setCurrent_no(String current_no) {
		this.current_no = current_no;
	}

	public String getCurrent_no() {
		return current_no;
	}

	public void setRemarks2(String remarks) {
		this.remarks2 = remarks;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setStatus2(String status2) {
		this.status2 = status2;
	}

	public String getStatus2() {
		return status2;
	}

	public void setPolicy_quote(String policy_quote) {
		this.policy_quote = policy_quote;
	}

	public String getPolicy_quote() {
		return policy_quote;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String setDescription() {
		return description;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setProduct_id1(String product_id1) {
		this.product_id1 = product_id1;
	}

	public String getProduct_id1() {
		return product_id1;
	}

	public void setAmend_id(String amend_id) {
		this.amend_id = amend_id;
	}

	public String getAmend_id() {
		return amend_id;
	}

	public void setStartDate2(String startDate2) {
		this.startDate2 = startDate2;
	}

	public String getStartDate2() {
		return startDate2;
	}

	public void setCountryid(String countryid) {
		this.countryid = countryid;
	}

	public String getCountryid() {
		return countryid;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryshortname(String countryshortname) {
		this.countryshortname = countryshortname;
	}

	public String getCountryshortname() {
		return countryshortname;
	}
	
	public void setWarDesc(String warDesc) {
		this.warDesc = warDesc;
	}

	public String getWarDesc() {
		return warDesc;
	}

	public void setWarrate(String warrate) {
		this.warrate = warrate;
	}

	public String getWarrate() {
		return warrate;
	}

	public void setStartingplace(String startingplace) {
		this.startingplace = startingplace;
	}

	public String getStartingplace() {
		return startingplace;
	}

	public void setSpwarrantiesconditions(String spwarrantiesconditions) {
		this.spwarrantiesconditions = spwarrantiesconditions;
	}

	public String getSpwarrantiesconditions() {
		return spwarrantiesconditions;
	}

	public void setEndingplace(String endingplace) {
		this.endingplace = endingplace;
	}

	public String getEndingplace() {
		return endingplace;
	}

	public void setEpwarrantiesconditions(String epwarrantiesconditions) {
		this.epwarrantiesconditions = epwarrantiesconditions;
	}

	public String getEpwarrantiesconditions() {
		return epwarrantiesconditions;
	}

	public void setStatus1(String status1) {
		this.status1 = status1;
	}

	public String getStatus1() {
		return status1;
	}

	public void setRsacode(String rsacode) {
		this.rsacode = rsacode;
	}

	public String getRsacode() {
		return rsacode;
	}

	public void setNationalityname(String nationalityname) {
		this.nationalityname = nationalityname;
	}

	public String getNationalityname() {
		return nationalityname;
	}

	public void setAmendid1(String amendid1) {
		this.amendid1 = amendid1;
	}

	public String getAmendid1() {
		return amendid1;
	}

	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}

	public String getStartDate1() {
		return startDate1;
	}

	public void setRemarks1(String remarks) {
		this.remarks1 = remarks;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setAmendid(String amendid) {
		this.amendid = amendid;
	}

	public String getAmendid() {
		return amendid;
	}

	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}

	public String getCommodity_name() {
		return commodity_name;
	}

	public void setFragile(String fragile) {
		this.fragile = fragile;
	}

	public String getFragile() {
		return fragile;
	}

	public void setRag(String rag) {
		this.rag = rag;
	}

	public String getRag() {
		return rag;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setWarranty_id(String warranty_id) {
		this.warranty_id = warranty_id;
	}

	public String getWarranty_id() {
		return warranty_id;
	}

	public void setExclusion_id(String exclusion_id) {
		this.exclusion_id = exclusion_id;
	}

	public String getExclusion_id() {
		return exclusion_id;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	public void setSelectBusi(String selectBusi) {
		this.selectBusi = selectBusi;
	}

	public String getSelectBusi() {
		return selectBusi;
	}

	public void setCOMMODITY_EXCESS_PREMIUM(String COMMODITY_EXCESS_PREMIUM) {
		this.COMMODITY_EXCESS_PREMIUM = COMMODITY_EXCESS_PREMIUM;
	}

	public String getCOMMODITY_EXCESS_PREMIUM() {
		return COMMODITY_EXCESS_PREMIUM;
	}
	public String getCOVERAGE_REFERAL() {
		return COVERAGE_REFERAL;
	}

	public void setCOVERAGE_REFERAL(String cOVERAGEREFERAL) {
		COVERAGE_REFERAL = cOVERAGEREFERAL;
	}

	// NEW SET AND GET METHOD WRITTEN BY ME
	String icc_b = "";
	String icc_a_air = "";
	String frozenFoodAllRisks = "";
	String frozenFoodAr = "";
	String frozenFoodC = "";
	String frozenMeatAr = "";
	String frozenMeatC = "";
	String frozenMeatFoodAr = "";

	public void setIcc_b(String icc_b) {
		this.icc_b = icc_b;
	}

	public String getIcc_b() {
		return icc_b;
	}

	public void setIcc_a_air(String icc_a_air) {
		this.icc_a_air = icc_a_air;
	}

	public String getIcc_a_Air() {
		return icc_a_air;
	}

	public void setFrozenFoodAllRisks(String frozenFoodAllRisks) {
		this.frozenFoodAllRisks = frozenFoodAllRisks;
	}

	public String getFrozenFoodAllRisks() {
		return this.frozenFoodAllRisks;
	}

	public void setFrozenFoodAr(String frozenFoodAr) {
		this.frozenFoodAr = frozenFoodAr;
	}

	public String getFrozenFoodAr() {
		return frozenFoodAr;
	}

	public void setFrozenFoodC(String frozenFoodC) {
		this.frozenFoodC = frozenFoodC;
	}

	public String getFrozenFoodC() {
		return frozenFoodC;
	}

	public void setFrozenMeatAr(String frozenMeatAr) {
		this.frozenMeatAr = frozenMeatAr;
	}

	public String getFrozenMeatAr() {
		return frozenMeatAr;
	}

	public void setFrozenMeatC(String frozenMeatC) {
		this.frozenMeatC = frozenMeatC;
	}

	public String getFrozenMeatC() {
		return frozenMeatC;
	}

	public void setFrozenMeatFoodAr(String frozenMeatFoodAr) {
		this.frozenMeatFoodAr = frozenMeatFoodAr;
	}

	public String getFrozenMeatFoodAr() {
		return frozenMeatFoodAr;
	}

	// END

	public String[][] getCoverName() {

		String[][] getCoverId = new String[0][0];
		String sql = "select distinct COVER_ID,COVER_NAME from COVER_MASTER order by COVER_ID";
		try {
			getCoverId = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCoverId;
	}

	public String[][] getCountryName() {

		String[][] constant = new String[0][0];
		String sql = "";
		try {
			sql = "select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			constant = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public String[][] getcitydesc(String cityId) {

		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			sql = "select CITY_ID,CITY_NAME,status from CITY_MASTER where COUNTRY_ID=? order by CITY_NAME";
			args[0] = cityId;
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public void updatecityDesc(String cityname, String status, String country,
			String cityid) {

		String sql = "";
		String args[] = new String[4];
		try {
			sql = "update CITY_MASTER set CITY_NAME=?,STATUS=? where COUNTRY_ID=? and CITY_ID=?";
			args[0] = cityname;
			args[1] = status;
			args[2] = country;
			args[3] = cityid;
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertcityDesc(String country, String cityname, String rsacode) {

		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			query = "select max(SNO__) from CITY_MASTER";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[6];
			sql = "insert into CITY_MASTER(SNO__, CITY_ID, CITY_NAME,COUNTRY_ID,status,rsacode) values(?,?,?,?,?,?)";

			args[0] = "" + count;
			args[1] = "" + count;
			args[2] = cityname;
			args[3] = country;
			args[4] = "Y";
			args[5] = rsacode;
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getwarranty(String branch) {

		String[][] warranty = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			sql = "select WARRANTY_ID,WARRANTY_DESCRIPTION from WARRANTY_MASTER where status='Y' and branch_code=? ";
			warranty = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warranty;
	}

	public String[][] getwarrantydesc(String warrantyId, String branch) {
		String[][] warranty = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = warrantyId;
			args[1] = branch;
			sql = "select warranty_ID,WARRANTY_DESCRIPTION,status from WARRANTY_MASTER where warranty_ID=? and branch_code=? and status='Y'";
			warranty = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return warranty;
	}

	public boolean updatewarrantyDesc(String warrantydesc, String status,
			String warrantyid, String branch) {
		boolean f = false;
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = warrantydesc;
			args[1] = status;
			args[2] = warrantyid;
			args[3] = branch.replaceAll("'", "");
			sql = "update warranty_MASTER set WARRANTY_DESCRIPTION=?,STATUS=? where  warranty_ID=? and branch_code=? ";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			f = true;
			e.printStackTrace();
			return f;
		}
		return f;
	}

	public boolean insertwarrantyDesc(String warrantyDesc, String war, String branch) {
		boolean f = false;
		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			args = new String[1];
			branch = branch.replaceAll("'", "");
			args[0] = branch;
			query = "select max(warranty_ID) from warranty_MASTER where branch_code=?";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[6];
			sql = "insert into warranty_MASTER(SNO__, warranty_ID, WARRANTY_DESCRIPTION,status,branch_code,RSACODE)  values(?,?,?,?,?,?)";
			args[0] = "" + count;
			args[1] = "" + count;
			args[2] = warrantyDesc;
			args[3] = "Y";
			args[4] = branch;
			args[5] = war;
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			f = true;
			e.printStackTrace();
			return f;
		}
		return f;
	}

	public String[][] getConstant(String branch) {
		String[][] constant = new String[0][0];
		String sql = "";
		try {
			sql = "select CATEGORY_ID,CATEGORY_NAME from CONSTANT_MASTER where status='Y' and branch_code in("
					+ branch + ") order by CATEGORY_NAME";
			constant = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public String[][] getconstantdesc(String constantId, String branch) {
		String[][] constant = new String[0][0];
		String sql = "";
		try {
			sql = "select CATEGORY_ID,CATEGORY_NAME,status from CONSTANT_MASTER where CATEGORY_ID='"
					+ constantId + "' and branch_code in(" + branch + ")";
			constant = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public void updateconstantDesc(String constantdesc, String status,
			String constantid, String branch) {
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = constantdesc;
			args[1] = status;
			args[2] = constantid;
			args[4] = branch;
			sql = "update CONSTANT_MASTER set CATEGORY_NAME=?,STATUS=? where  CATEGORY_ID=? and branch_code=?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertconstantDesc(String constantDesc, String branch) {
		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			args = new String[1];
			args[0] = branch;
			query = "select max(CATEGORY_ID) from CONSTANT_MASTER where branch_code=?";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[4];
			args[0] = "" + count;
			args[1] = constantDesc;
			args[2] = "Y";
			args[3] = branch;
			sql = "insert into CONSTANT_MASTER(CATEGORY_ID, CATEGORY_NAME,status,branch_code) values(?,?,?,?)";
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getconstantdet(String constantdetId, String branch) {
		String[][] constantdet = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		branch = branch.replaceAll("'", "");
		try {
			args[0] = constantdetId;
			args[1] = branch;

			sql = "select CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS,STATUS,PERCENT from CONSTANT_DETAIL where CATEGORY_ID=? and status='Y' and branch_code =? ";
			constantdet = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constantdet;
	}

	public void updateconstantdetDesc(String constantId, String detailid,
			String detailName, String percent, String remarks, String status,
			String branch) {
		String sql = "";
		String args[] = new String[7];
		try {
			sql = "update CONSTANT_DETAIL set DETAIL_NAME=?,PERCENT=?,REMARKS=?,STATUS=? where  CATEGORY_ID=? and CATEGORY_DETAIL_ID=? and branch_code=?";
			branch = branch.replaceAll("'", "");
			args[0] = detailName;
			args[1] = percent;
			args[2] = remarks;
			args[3] = status;
			args[4] = constantId;
			args[5] = detailid;
			args[6] = branch;

			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertconstantdetDesc(String constantId, String detailName,
			String percent, String remarks, String branch) {
		String query = "";
		int count = 0;
		String res = "";
		String args[] = new String[0];
		branch = branch.replaceAll("'", "");
		try {

			args = new String[2];
			args[0] = constantId;
			args[1] = branch;
			query = "select max(CATEGORY_DETAIL_ID) from CONSTANT_DETAIL where CATEGORY_ID=? and BRANCH_CODE =?";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[7];
			args[0] = constantId;
			args[1] = "" + count;
			args[2] = detailName;
			args[3] = remarks;
			args[4] = "Y";
			args[5] = percent;
			args[6] = branch;
			sql = "insert into CONSTANT_DETAIL(CATEGORY_ID,CATEGORY_DETAIL_ID,DETAIL_NAME,REMARKS,STATUS,PERCENT,BRANCH_CODE) values(?,?,?,?,?,?,?)";
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getsettlingAgent(String countryId, String cityId) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = countryId;
			args[1] = cityId;
			sql = "select SETTLING_AGENT_ID,SETTLING_AGENT_NAME,status,COUNTRY_ID,CITY_ID from SETTLING_AGENT_MASTER where COUNTRY_ID=? and CITY_ID=? order by SETTLING_AGENT_NAME";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String[][] getProductName(String branch) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			sql = "select PRODUCT_ID,PRODUCT_NAME from product_master where status='Y' and branch_code=? order by PRODUCT_NAME";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String[][] getStageName(String prodId) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = prodId;
			sql = "select STAGE_ID,STAGE_DESC from STAGE_MASTER where PRODUCT_ID=? order by STAGE_DESC";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String[][] getErrorDet(String prodId, String stageId) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = prodId;
			args[1] = stageId;
			sql = "select ERROR_ID,ERROR_DESC,REMARKS,STATUS from ERROR_MASTER where PRODUCT_ID=? and STAGE_ID=?";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Error in getErrorDet = " + e);
			e.printStackTrace();
		}
		return city;
	}

	public String[][] getRatingErrorDet(String prodId, String stageId) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = prodId;
			args[1] = stageId;
			sql = "select ERROR_ID,ERROR_DESC,REMARKS,STATUS from RATING_ERROR_MASTER where PRODUCT_ID=? and STAGE_ID=?";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public void updateError(String product, String stage, String errorDesc,
			String errorid, String remarks, String status) {
		String sql = "";
		String args[] = new String[6];
		try {
			args[0] = errorDesc;
			args[1] = remarks;
			args[2] = status;
			args[3] = errorid;
			args[4] = product;
			args[5] = stage;
			sql = "update ERROR_MASTER set ERROR_DESC=?,REMARKS=?,STATUS=? where  ERROR_ID=? and PRODUCT_ID=? and STAGE_ID=?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertError(String product, String stage, String errorDesc,
			String remarks) {
		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			query = "select max(ERROR_ID) from ERROR_MASTER";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[6];
			args[0] = "" + count;
			args[1] = errorDesc;
			args[2] = stage;
			args[3] = product;
			args[4] = remarks;
			args[5] = "Y";
			sql = "insert into ERROR_MASTER(ERROR_ID,ERROR_DESC,STAGE_ID,PRODUCT_ID, REMARKS,STATUS)  values(?,?,?,?,?,?)";
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getCountryDet(String country) {
		String[][] city = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		try {
			args[0] = country;
			args[1] = country;
			sql = "select COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,WAR_RATE, STARTING_PLACE,SP_WARRANTIES_CONDITIONS,ENDING_PLACE,EP_WARRANTIES_CONDITIONS, STATUS,RSACODE,NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE,REMARKS from COUNTRY_MASTER_DETAIL where COUNTRY_ID=? and BELONGING_COUNTRY_ID='1' and amend_id =(select max(amend_id) from COUNTRY_MASTER_DETAIL where country_id=? and BELONGING_COUNTRY_ID='1')";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public void updateCountry(String product, String stage, String errorDesc,
			String errorid, String remarks, String status) {
		String sql = "";
		String args[] = new String[6];
		try {
			args[0] = errorDesc;
			args[1] = remarks;
			args[2] = status;
			args[3] = errorid;
			args[4] = product;
			args[5] = stage;

			sql = "update ERROR_MASTER set ERROR_DESC=?,REMARKS=?,STATUS=? where ERROR_ID=? and PRODUCT_ID=? and STAGE_ID=?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertCountry(String product, String stage, String errorDesc,
			String remarks) {
		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			query = "select max(ERROR_ID) from ERROR_MASTER";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[5];
			args[0] = "" + count;
			args[1] = errorDesc;
			args[2] = stage;
			args[3] = product;
			args[4] = remarks;
			args[5] = "Y";
			sql = "insert into ERROR_MASTER(ERROR_ID,ERROR_DESC,STAGE_ID,PRODUCT_ID, REMARKS,STATUS)  values(?,?,?,?,?,?)";
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String page1 = "";
	private String sql = "";
	private String mode = "";
	private String tableId = "";
	private String fieldId = "";
	private String tableName = "";
	private String fieldName = "";
	private String selectRecord = "";
	private String checking = "";
	private int viewColumnCount = 0;
	StringBuffer tabId = new StringBuffer();
	StringBuffer tabName = new StringBuffer();
	public String[] dynamicFields = new String[0];
	private String[] viewColumnName = new String[0];
	String[][] fieldsTotal = new String[0][0];
	StringBuffer stbuf = new StringBuffer();
	StringBuffer stbuf1 = new StringBuffer();
	StringBuffer stbuf2 = new StringBuffer();
	StringBuffer stbuf3 = new StringBuffer();
	private Vector result = new Vector();
	private String[][] tableDetail = new String[0][0];
	private String[][] details = new String[0][0];
	public String[] tableFieldsOnly = new String[0];
	private String[] fieldValue = new String[0];
	private String transportType = "";
	private String coverageType = "";
	private String conveyanceType = "";
	private String countryType = "";
	private String exchangeRate="";
	private String SumRate="";
	
	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setPage1(String page1) {
		this.page1 = page1;
	}

	public String getPage1() {
		return page1;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getFieldId() {
		return fieldId;
	}

	public void setTabName(StringBuffer tabName) {
		this.tabName = tabName;
	}

	public StringBuffer getTabName() {
		return tabName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setTableFieldsOnly(String[] tableFieldsOnly) {
		this.tableFieldsOnly = tableFieldsOnly;
	}

	public String[] getTableFieldsOnly() {
		return tableFieldsOnly;
	}

	public void setTableDetail(String[][] tableDetail) {
		this.tableDetail = tableDetail;
	}

	public String[][] getTableDetail() {
		return tableDetail;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName.trim();
	}

	public String getTableName() {
		return tableName;
	}

	public void setSelectRecord(String selectRecord) {
		this.selectRecord = selectRecord.trim();
	}

	public String getSelectRecord() {
		return selectRecord;
	}

	public void setDetails(String[][] details) {
		this.details = details;
	}

	public String[][] getDetails() {
		return details;
	}

	public void setStbuf(StringBuffer stbuf) {
		this.stbuf = stbuf;
	}

	public StringBuffer getStbuf() {
		return stbuf;
	}

	public void setStbuf1(StringBuffer stbuf1) {
		this.stbuf1 = stbuf1;
	}

	public StringBuffer getStbuf1() {
		return stbuf1;
	}

	public void setStbuf2(StringBuffer stbuf2) {
		this.stbuf2 = stbuf2;
	}

	public StringBuffer getStbuf2() {
		return stbuf2;
	}

	public void setStbuf3(StringBuffer stbuf3) {
		this.stbuf3 = stbuf3;
	}

	public StringBuffer getStbuf3() {
		return stbuf3;
	}

	public void setResult(Vector result) {
		this.result = result;
	}

	public Vector getResult() {
		return result;
	}

	public void setFieldsTotal(String[][] fieldsTotal) {
		this.fieldsTotal = fieldsTotal;
	}

	public String[][] getFieldsTotal() {
		return fieldsTotal;
	}

	public void setDynamicFields(String[] dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	public String[] getDynamicFields() {
		return dynamicFields;
	}

	public void setFieldValue(String[] fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String[] getFieldValue() {
		return fieldValue;
	}

	public void setChecking(String checking) {
		this.checking = checking;
	}

	public String getChecking() {
		return checking;
	}

	public void setViewColumnCount(int viewColumnCount) {
		this.viewColumnCount = viewColumnCount;
	}

	public int getViewColumnCount() {
		return viewColumnCount;
	}

	public void setViewColumnName(String[] viewColumnName) {
		this.viewColumnName = viewColumnName;
	}

	public String[] getViewColumnName() {
		return viewColumnName;
	}

	public String getSumRate() {
		return SumRate;
	}

	public void setSumRate(String sumRate) {
		SumRate = sumRate;
	}

	public String getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(String coverageType) {
		this.coverageType = coverageType;
	}

	public String getConveyanceType() {
		return conveyanceType;
	}

	public void setConveyanceType(String conveyanceType) {
		this.conveyanceType = conveyanceType;
	}

	public String getCountryType() {
		return countryType;
	}

	public void setCountryType(String countryType) {
		this.countryType = countryType;
	}

	public StringBuffer getTableDetails() {
		String sql = "select new_table_id, new_table_name from created_table_master where status='Y' order by new_table_id";
		StringBuffer stb = new StringBuffer();
		int calc = 0;
		String tableDetails[][] = new String[0][0];
		try {
			tableDetails = runner.multipleSelection(sql);
			tableDetail = tableDetails;
		} catch (Exception e) {
			setError("codeError : getTableDetails()1 : " + e);
		}

		stb.append("<select name='tableId'>");
		stb.append("<option value = '0'>SelectTable</option>");
		for (int i = 0; i < tableDetails.length; i++) {
			int col = 0;
			try {
				stb.append("<option value = '" + tableDetails[i][0] + "'>"
						+ tableDetails[i][1] + "</option>");
			} catch (Exception e) {
				setError("codeError : getTableDetails()2 : " + e);
			}
		}
		stb.append("</select>");
		return stb;
	}

	public StringBuffer getTableDetails1() {
		Connection con = null;
		String sql = "select new_table_id, new_table_name from created_table_master where status='Y'";
		setError("sql : " + sql);
		String subsql = "";
		StringBuffer stb = new StringBuffer();
		StringBuffer stbuf = new StringBuffer();
		Statement smt1 = null;
		ResultSet rs1 = null;
		ResultSetMetaData rsmd = null;

		int calc = 0;
		String tableDetails[][] = new String[0][0];
		try {
			tableDetails = runner.multipleSelection(sql);
			tableDetail = tableDetails;
			con = DBConnection.getInstance().getDBConnection();
		} catch (Exception e) {
			setError("codeError : getTableDetails()1 : " + e);
			e.printStackTrace();
		}
		String tableIdentifier = "";
		// stb.append("<select name='tableId' onChange='changeValue()' >");
		stb.append("<select name='tableId'>");
		stb.append("<option value = '0'>SelectTable</option>");
		stbuf.append("<select name='fieldId'>");
		stbuf.append("<option value = '0'>SelectField</option>");
		for (int i = 0; i < tableDetails.length; i++) {
			int col = 0;
			try {
				tabId.append("'" + tableDetails[i][0] + "',");
				tabName.append("'" + tableDetails[i][1] + "',");
				/*
				 * if (tableDetails[i][0].equalsIgnoreCase(tableId)) {
				 * tableIdentifier = "selected"; } else { tableIdentifier = "";
				 * }
				 */
				stb.append("<option value = '" + tableDetails[i][0] + "' "
						+ tableIdentifier + ">" + tableDetails[i][1]
						+ "</option>");
				subsql = "select * from " + tableDetails[i][1];
				// 'setError("subsql : "+subsql);
				smt1 = con.createStatement();
				rs1 = smt1.executeQuery(subsql);
				rsmd = rs1.getMetaData();
				col = rsmd.getColumnCount();
			} catch (Exception e) {
				setError("codeError : getTableDetails()2 : " + e);
				e.printStackTrace();
			}
			String record[] = new String[col];
			try {
				if (rs1.next()) {
					for (int k = 0; k < col; k++) {
						try {
							// record[i] =
							// rsmd.getColumnName(i+1).toLowerCase();
							stbuf1.append("'" + tableDetails[i][0] + "',");
							stbuf2.append("'" + k + "',");
							stbuf3.append("'"
									+ rsmd.getColumnName(k + 1).toLowerCase()
									+ "',");
							stbuf.append("<option value = '"
									+ rsmd.getColumnName(k + 1).toLowerCase()
									+ "'>"
									+ rsmd.getColumnName(k + 1).toLowerCase()
									+ "</option>");
						} catch (Exception e) {
							setError(tableDetails[i][1]
									+ " : codeError : getTableDetails()3 : "
									+ e);
						}
					}
				}
			} catch (Exception e) {
				setError("codeError : getTableDetails()4 : " + e);
				e.printStackTrace();
			}
			// result.addElement(record);
			calc += col;
		}
		// convert the vector into two dimension string array.
		/*
		 * String finalResult[][] = new String[result.size()][calc]; for(int
		 * h=0; h < result.size(); h++) { try { finalResult[h] =
		 * (String[])result.elementAt(h); } catch(Exception e) {
		 * setError("codeError : getTableDetails()5 : "+e); } } details =
		 * finalResult;
		 */
		stbuf.append("</select>");
		stb.append("</select>");
		/*
		 * try { smt.close(); } catch (Exception e) {
		 * setError("codeError : getTableDetails()6 : " + e); }
		 */
		tabId.deleteCharAt(tabId.length() - 1);
		tabName.deleteCharAt(tabName.length() - 1);
		stbuf1.deleteCharAt(stbuf1.length() - 1);
		stbuf2.deleteCharAt(stbuf2.length() - 1);
		stbuf3.deleteCharAt(stbuf3.length() - 1);

		try {
			if (rs1 != null)
				rs1.close();
		} catch (Exception ex) {
			setError("codeError : getTableDetails()7 : " + ex);
			ex.printStackTrace();
		}
		try {
			if (smt1 != null)
				smt1.close();
		} catch (Exception e) {
			setError("codeError : getTableDetails()7 : " + e);
			e.printStackTrace();
		}
		try {
			if (con != null)
				con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return stb;
	} /* getTableDetails() funtion End */

	// TO LIST SELECTED FIELD NAME DATAS IN COMBO BOX
	public StringBuffer dataList() {
		findTableName();
		StringBuffer strbuff = new StringBuffer();
		sql = "select " + fieldId + " from " + tableName + " order by "
				+ fieldId;
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection(sql);
			result = result == null ? new String[0][0] : result;
			strbuff.append("<select name ='fieldName'>");
			for (int i = 0; i < result.length; i++) {
				strbuff.append("<option value='" + result[i][0] + "' > "
						+ result[i][0] + "</option>");
			}
			strbuff.append("</select>");
		} catch (Exception e) {
			setError("codeError : dataList() : " + e);
			e.printStackTrace();
		}
		return strbuff;
	}

	public boolean checkDataList() {
		boolean err = false;
		findTableName();
		if (null == tableName || tableName.equals("")) {
			// setErrorId("47");undatabasify();setError(getErrorDesc());
			err = true;
		}
		if (mode.equals("edit")
				&& ((null != tableName) && !(tableName.equals("")))) {
			// this for edit purpose checking only
			/*
			 * sql="select "+fieldId+" from "+tableName+" order by "+fieldId;
			 * //setError("sql : "+sql); try { psmt = con.prepareStatement(sql);
			 * rs = psmt.executeQuery(); if(rs.next()){} else { err = true; } }
			 * catch(Exception e){err=true;}
			 */
		}
		return err;
	}

	// TO GET PRIMARY KEY FIELD
	public StringBuffer checkPrimary() {
		StringBuffer arr = new StringBuffer();
		if (tableName.equals("constant_master")) {
			arr.append("category_id");
		} else {
			arr.append("sno__");
		}
		return arr;
	}

	// TO GET PRIMARY KEY FIELD
	public StringBuffer checkPrimary1() {
		String[][] result = new String[0][0];
		StringBuffer arr = new StringBuffer();
		String[] args = new String[1];
		args[0] = tableId;

		sql = "select description_name from structure_maintenance where index_value='P' and new_table_id =  ?";

		try {
			result = runner.multipleSelection(sql, args);
			result = result == null ? new String[0][0] : result;

			for (int i = 0; i < result.length; i++) {
				arr.append(result[i][0] + ",");
			}
			if (arr.length() > 0) {
				arr.deleteCharAt(arr.length() - 1);
			}
		} catch (Exception e) {
			setError("codeError: checkPrimary() : " + e);
		}
		return arr;
	}

	// TO GET PRIMARY KEY DATAS FOR DYNAMICALLY CREATED TABLE TO ASSIGNE IN
	// RADIO BUTTON VALUE
	public String[][] getPrimaryData() {
		StringBuffer arr = checkPrimary();
		sql = "select  " + arr + "  from " + tableName + " where " + fieldId
				+ "='" + fieldName + "'";
		String createList[][] = new String[0][0];
		try {
			createList = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeError :  getRecords() : " + e);
			e.printStackTrace();
		}
		return createList;
	}

	// THIS FUNCTION IS USED TO VIEW DYAMIC TABLE DATA ENTRY FOR MODICIFACTION
	// PURPOSE ONLY MULTIPLE [PARTICULAR] RECORDS
	public String[][] getRecords() {
		findTableColumnName();
		StringBuffer checktablefield = new StringBuffer();
		for (int i = 0; i < tableFieldsOnly.length; i++) {
			checktablefield.append(tableFieldsOnly[i] + ",");
		}
		checktablefield.deleteCharAt(checktablefield.length() - 1);
		sql = "select  " + checktablefield + "  from " + tableName
				+ " where sno__='" + fieldName + "'";
		String createList[][] = new String[0][0];
		try {
			createList = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeError :  getRecords() : " + e);
			e.printStackTrace();
		}
		return createList;
	}

	// THIS FUNCTION IS USED TO VIEW DYAMIC TABLE DATA ENTRY FOR MODICIFACTION
	// PURPOSE ONLY SINGLE [PARTICULAR] RECORDS
	public void viewDynamicEntry() {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		ResultSetMetaData rsmd = null;
		StringBuffer arr = new StringBuffer();
		// used to find columnName type and length also
		tableFields();
		StringBuffer checktablefield = new StringBuffer();
		for (int i = 0; i < fieldsTotal.length; i++) {
			if (fieldsTotal[i][1].trim().equalsIgnoreCase("date")) {
				checktablefield.append("to_char(" + fieldsTotal[i][0]
						+ ",'DD-MM-YYYY')" + ",");
			} else {
				checktablefield.append(fieldsTotal[i][0] + ",");
			}
		}
		checktablefield.deleteCharAt(checktablefield.length() - 1);
		String whereCond = "";

		arr = checkPrimary();
		whereCond = arr + " = '" + selectRecord + "'";

		sql = "select  " + checktablefield + "  from " + tableName + " where "
				+ whereCond;// +fieldId+"='"+fieldName+"' "+whereCond;
		int col = 0;
		try {
			rs = smt.executeQuery(sql);
			rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();
		} catch (Exception e) {
			setError("codeError : viewDynamicEntry1 : " + e);
			e.printStackTrace();
		}

		String record[] = new String[col];
		int start = 0;
		if (tableName.equals("constant_master")) {
			start = 1;
		} else {
			start = 0;
		}
		try {
			if (rs.next()) {
				for (int k = 0; k < col; k++) {
					if ((rs.getString(k + 1)) == null)
						continue;
					System.out.println("<br>Stringcol----->"
							+ rs.getString(k + 1));
					record[k] = rs.getString(k + 1).trim();
					System.out.println("record[k]--->" + record[k]);
				}
			}
		} catch (Exception e) {
			setError("codeError : viewDynamicEntry2 : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (smt != null)
					smt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fieldValue = record;
	}

	public String getCommodityRate( String modeOfTransport,
			 String coverage,
			 String conveyanceType,
			 String destCountryId,
			 String commodity,
			 String fragile,
			 double localSumInsured,
			 String branch,
			 String policyStartDate	)
			{
			    double baseRate = 0, factor1 = 0, factor2 = 0;
			    double factor3 = 0;
			    double factor4 = 0;
			    double factor5 = 0;
			    double wsrccRates = 0;
			    double result=0;
			    String returnVal = "", query ="", value = "";
				try
				{
					{
					fragile = fragile.equalsIgnoreCase("on")?"1":"2";
					System.out.println("Fragile:"+fragile);
					System.out.println("commodity:"+commodity);
					System.out.println("Date:"+policyStartDate);
					String commodityReferal = runner.singleSelection("select status from commoditymaster where  COMMODITY_ID = "+commodity+" and branch_code = "+branch +" and amend_id= (select max(amend_id) from commoditymaster where branch_code = "+branch +" and commodity_id="+commodity+" )");
					System.out.println("REFEREAL::"+commodityReferal);
					if(!commodityReferal.equalsIgnoreCase("R")){
						
						String baseVal = runner.singleSelection("SELECT COVER_RATE FROM COVER_MASTER WHERE COVER_ID ='"+coverage+"' AND BRANCH_CODE='"+branch+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from COVER_MASTER where cover_id='"+coverage+"'  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						baseRate = Double.parseDouble(baseVal.equalsIgnoreCase("")?"0":baseVal);
						
						String fact1Val = runner.singleSelection("SELECT RATE from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="+localSumInsured+" and  END_SUM_INSURED>="+localSumInsured+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from COMMODITY_EXCESS_PREMIUM where START_SUM_INSURED<="+localSumInsured+" and  END_SUM_INSURED>="+localSumInsured+"  AND BRANCH_CODE='"+branch+"' ) AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						factor1 = Double.parseDouble(fact1Val.equalsIgnoreCase("")?"0":fact1Val);
						
						String fact2Val = runner.singleSelection("SELECT MATERIAL_RATE FROM MATERIAL_TYPE_MASTER WHERE MATERIAL_ID ='"+fragile+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from MATERIAL_TYPE_MASTER where MATERIAL_ID='"+fragile+"'  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						factor2 = Double.parseDouble(fact2Val.equalsIgnoreCase("")?"0":fact2Val);
						
						String fact3Val = runner.singleSelection("SELECT GEO_RATE  FROM  country_master where country_id='"+destCountryId+"'  AND AMEND_ID=(SELECT max(amend_id) from country_master where country_id='"+destCountryId+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						factor3 = Double.parseDouble(fact3Val.equalsIgnoreCase("")?"0":fact3Val);
						
						String fact4Val = runner.singleSelection("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+"  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						System.out.println("SELECT CONV_RATE FROM CONVEYAN_MASTER WHERE CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+" AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from CONVEYAN_MASTER where CONVDESC ='"+conveyanceType+"' AND MODE_TRANSPORT_ID = "+modeOfTransport+"  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						factor4 = Double.parseDouble(fact4Val.equalsIgnoreCase("")?"0":fact4Val);
						
						String fact5Val = runner.singleSelection("SELECT commodity_rate FROM commoditymaster WHERE commodity_id ='"+commodity+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from commoditymaster where  commodity_id ='"+commodity+"'  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						System.out.println("SELECT commodity_rate FROM commoditymaster WHERE commodity_id ='"+commodity+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from commoditymaster where  commodity_id ='"+commodity+"'  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						factor5 = Double.parseDouble(fact5Val.equalsIgnoreCase("")?"0":fact5Val);
						
						String wsrcVal = runner.singleSelection("SELECT WAR_RATE FROM WAR_RATE_MASTER WHERE MODE_TRANSPORT_ID ='"+modeOfTransport+"' AND BRANCH_CODE='"+branch+"' AND AMEND_ID=(SELECT max(amend_id) from WAR_RATE_MASTER where MODE_TRANSPORT_ID='"+modeOfTransport+"'  AND BRANCH_CODE='"+branch+"') AND STATUS='Y' AND TRUNC(EFFECTIVE_DATE) <= TRUNC(SYSDATE)" );
						wsrccRates = Double.parseDouble(wsrcVal.equalsIgnoreCase("")?"0":wsrcVal);
						
						System.out.println("baseRate:"+baseRate+"factor1(sum insured):"+factor1+"factor2(fragile/non fragile):"+factor2+"factor3(dest country):"+factor3+"factor4(Conveyance):"+factor4+"factor5(commodity):"+factor5+"wsrccRates:"+wsrccRates);
						
						}
					result =( baseRate*factor1*factor2*factor3*factor4*factor5)/10;
					DecimalFormat df = new DecimalFormat("#.####");
					returnVal  = df.format(result);
					}
					System.out.println("baseRate:"+baseRate+"factor1(sum insured):"+factor1+"factor2(fragile/non fragile):"+factor2+"factor3(dest country):"+factor3+"factor4(Conveyance):"+factor4+"factor5(commodity):"+factor5+"wsrccRates:"+wsrccRates);
					//returnVal=Double.toString(result);
					returnVal = baseRate+"~"+factor1+"~"+factor2+"~"+factor3+"~"+factor4+"~"+factor5+"~"+wsrccRates+"~"+returnVal;
				}
				catch(Exception e)
				{
					logger.info("Exception in getCommodityRate .."+e);
				}
				return returnVal;	
		}
	
	public void validateBaseRateMaster() {
		try {

			if(transportType.equalsIgnoreCase("") || transportType.equalsIgnoreCase("0"))
				error = "Please, Select Form of Transport<br>";
			
			if(coverageType.equalsIgnoreCase("") || coverageType.equalsIgnoreCase("0"))
				error = error + "Please, Select Coverage Type<br>";
			
			/*if(conveyanceType.equalsIgnoreCase("") || conveyanceType.equalsIgnoreCase("0"))
				error = error + "Please, Select Conveyance Type<br>";*/
			
			if(countryType.equalsIgnoreCase("") || countryType.equalsIgnoreCase("0"))
				error = error + "Please, Select Country Type<br>";
			
			if(commodityType.equalsIgnoreCase("") || commodityType.equalsIgnoreCase("0"))
				error = error + "Please, Select Commodity Type<br>";

			if(status.equalsIgnoreCase(""))
				error = error + "Please, Select Fragile<br>";
			
			if(SumRate.equalsIgnoreCase(""))
				error = error + "Please, Enter Sum Insured<br>";
			
			if(error.length()>0)
				setError(error);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public String[][] getConveyances(String transId, String braCode) 
	{
		String[][] conveyance = new String[0][0];
		String sql = ""; 
		
		try 
		{
	     sql="SELECT CONVDESC,CONVDESC FROM CONVEYAN_MASTER WHERE MODE_TRANSPORT_ID=" + transId + 
	     " AND BRANCH_CODE= " + braCode + " AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM CONVEYAN_MASTER WHERE BRANCH_CODE= " + braCode + 
	     " AND MODE_TRANSPORT_ID= " + transId + ") ORDER BY CONV_MEAN";
	     conveyance = runner.multipleSelection(sql);
		 System.out.println("Result Lenth()===>"+conveyance.length);

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCurrencies in BrokeCreation Bean" + e.toString());
			e.printStackTrace();
		}
		return conveyance;
	}
	
	public String[][] getCoverages(String transId, String braCode) 
	{
		String[][] coverage = new String[0][0];
		String sql = ""; 
		try 
		{
		     sql="SELECT CM.COVER_ID,CM.COVER_NAME FROM COVER_MASTER CM WHERE mode_transport_id = " + transId + " and CM.AMEND_ID=" +
		     "(SELECT MAX(AMEND_ID) FROM COVER_MASTER WHERE mode_transport_id = " + transId + " and COVER_ID=CM.COVER_ID AND BRANCH_CODE= " + 
		     braCode + ") AND CM.BRANCH_CODE= " + braCode + " ORDER BY CM.COVER_ID";
		     coverage = runner.multipleSelection(sql);
		     System.out.println("Result Lenth()===>"+coverage.length);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCurrencies in BrokeCreation Bean" + e.toString());
			e.printStackTrace();
		}
		return coverage;
	}
	
	public String[][] getCountrie() 
	{
		String[][] country = new String[0][0];
		String sql = ""; 
		
		try 
		{
		
	     sql="SELECT CM.COUNTRY_ID,CM.COUNTRY_NAME FROM COUNTRY_MASTER CM WHERE CM.AMEND_ID=(SELECT MAX(AMEND_ID) " +
	     		"FROM COUNTRY_MASTER WHERE COUNTRY_ID=CM.COUNTRY_ID)ORDER BY COUNTRY_NAME";
	     country = runner.multipleSelection(sql);
		 System.out.println("Result Lenth()===>"+country.length);

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCurrencies in BrokeCreation Bean" + e.toString());
			e.printStackTrace();
		}
		return country;
	}
	
	public String[][] getCommodityList(String branchCode) 
	{
		String[][] commodity = new String[0][0];
		String sql = ""; 
		
		try 
		{
					
	     sql="SELECT CM.COMMODITY_ID,CM.COMMODITY_NAME FROM COMMODITYMASTER CM WHERE CM.AMEND_ID=(SELECT " +
	     		"MAX(AMEND_ID) FROM COMMODITYMASTER WHERE COMMODITY_ID=CM.COMMODITY_ID AND BRANCH_CODE=" + branchCode + 
	     		")AND CM.BRANCH_CODE=" + branchCode + " ORDER BY CM.COMMODITY_NAME";
	     commodity = runner.multipleSelection(sql);
	     System.out.println("Result Lenth()===>"+commodity.length+sql);

		} 
		catch (Exception e) 
		{
			System.out.println("Exception in getCurrencies in BrokeCreation Bean" + e.toString());
			e.printStackTrace();
		}
		return commodity;
	}
	
	// TO GET PARTICULAR TABLE FIELD NAME , DATA TYPE AND LENGTH OF FIELD
	public void tableFields() {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		ResultSetMetaData rsmd = null;
		String[] args = new String[1];
		args[0] = tableId;
		sql = "select new_table_name from created_table_master where new_table_id = ?";
		try {
			tableName = runner.singleSelection(sql, args);
		} catch (Exception e) {
			setError("codeError : tableFields()1 : " + e);
			e.printStackTrace();
		}
		sql = "select * from " + tableName;
		int col = 0;
		try {
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();
		} catch (Exception e) {
			setError("codeError : tableFields()2 : " + e);
			e.printStackTrace();
		}
		Vector colDesc = new Vector();
		try {
			for (int i = 1; i < col; i++) {
				String fields[] = new String[3];
				if (tableName.equals("constant_master")) {
					fields[0] = rsmd.getColumnName(i).toLowerCase();
					fields[1] = rsmd.getColumnTypeName(i).toLowerCase();
					fields[2] = "" + rsmd.getPrecision(i);
				} else {
					fields[0] = rsmd.getColumnName(i + 1).toLowerCase();
					fields[1] = rsmd.getColumnTypeName(i + 1).toLowerCase();
					fields[2] = "" + rsmd.getPrecision(i + 1);
				}
				colDesc.addElement(fields);
				System.out.println(" <br>columnName : " + fields[0]
						+ " columnType : " + fields[1] + "columnLength : "
						+ fields[2]);
			}
		} catch (Exception e) {
			setError("codeError: tableFields()3 : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (smt != null)
					smt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String[][] fields_total = new String[colDesc.size()][col];
		for (int h = 0; h < colDesc.size(); h++) {
			fields_total[h] = (String[]) colDesc.elementAt(h);
		}
		fieldsTotal = fields_total;
	}

	/* tableFields() funtion End */
	public void validator() {
		boolean err = false;
		if (page1.equals("first")) {
			err = checkDataList();
			if (err == true) {
				setErrorId("47");
				undatabasify();
				setError(getErrorDesc());
			}
		}
	}

	public void validator(String[][] tableField, String[] dynamicFields) {
		if (page1.equals("second")) {
			StringBuffer buff = checkPrimary1();
			boolean splCheck = false;
			for (int i = 0; i < dynamicFields.length; i++) {
				String fieldBuff = "" + buff;
				if ((fieldBuff.trim().equalsIgnoreCase(tableField[i][0].trim()))
						&& !(tableField[i][0].equalsIgnoreCase("sno__"))) {
					if (null == dynamicFields[i].trim()
							|| dynamicFields[i].trim().equals("")) {
						setError(tableField[i][0] + " Can't be empty");
					}
					if (null != dynamicFields[i].trim()
							&& !(dynamicFields[i].trim().equals(""))) {
						if (mode.equals("add")) {
							boolean err = checkExistingRec(tableField[i][0],
									dynamicFields[i]);
							if (err == true) {
								setError(fieldBuff
										+ " value is already exist test");
							}
						}
					}
				} else {
					String attValue = "";
					attValue = checkNotNull(tableField[i][0]);
					if (null != attValue && attValue.equals("N")) {
						if (null == dynamicFields[i]
								|| dynamicFields[i].equals("")) {
							setError(tableField[i][0] + " Can't be empty");
						}
					}
				}
				if (tableField[i][1].equalsIgnoreCase("NUMBER")
						|| tableField[i][1].equalsIgnoreCase("LONG")) {
					if (((null != dynamicFields[i]) && !(dynamicFields[i]
							.equals("")))) {
						try {
							Float.parseFloat(dynamicFields[i]);
						} catch (Exception e) {
							setError(tableField[i][0] + " must be Numerical ");
						}
					}
				}
			}
		}
	}

	public String checkNotNull(String fieldName) {
		// Connection con = null;
		// con = DBConnection.getInstance().getDBConnection();
		String[] args = new String[2];
		args[0] = fieldName;
		args[1] = tableId;
		sql = "select  attribute_value from structure_maintenance where description_name = ?  and new_table_id = ?";
		String check = "";
		try {
			check = runner.singleSelection(sql, args);

			// smt = con.createStatement();
			// rs = smt.executeQuery(sql);
			// if (rs.next()) {
			// check = rs.getString(1);
			// }
		} catch (Exception e) {
			setError("codeError checkNotNull : " + e);
			e.printStackTrace();
		}
		return check;
	}

	public boolean checkSpecial(String str) {
		boolean splChar = false;
		if (!(str.trim().length() == 0)) {
			String value = str.trim();
			char[] valuechar1 = value.toCharArray();
			for (int k = 0; k < value.length(); k++) {
				if (valuechar1[k] != '_' && valuechar1[k] != '-'
						&& valuechar1[k] != ' ') {
					int number = Character.getNumericValue(valuechar1[k]);
					int val = valuechar1[0];
					// char under='_';
					if (number < 0) {
						splChar = true;
						break;
					} // end of numer if
				}
			} //
		} else {
			splChar = false;
		}
		return splChar;
	}

	// THIS IS FOR INSERT TIME CHECKING
	public boolean checkExistingRec(String fieldName, String fieldValue) {
		// Connection con = null;
		// con = DBConnection.getInstance().getDBConnection();
		boolean error = false;
		String editCondition = "";
		String count = "";
		int c = 0;
		if (mode.equals("edit")) {
			editCondition = " and sno__ !='" + selectRecord + "'";
		}
		sql = "select count(*) from " + tableName + " where " + fieldName
				+ "='" + fieldValue + "' " + editCondition;
		try {
			// psmt = con.prepareStatement(sql);
			// rs = psmt.executeQuery();

			count = runner.singleSelection(sql);
			c = Integer.parseInt(count);
		} catch (Exception e) {
			c = 0;
			e.printStackTrace();
		}
		try {
			/*
			 * while (rs.next()) { int val = rs.getInt(1); if (val > 0) { error
			 * = true; } }
			 */
			if (c > 0) {
				error = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return error;
	}

	// TO INSERT DYNAMICALLY CREATED TABLE i.e, TABLE NAME FIELD NAME EVERYTINGS
	// DYNAMICAL

	public void insertDynamicEntry(String[][] tableField, String[] dynamicFields) {
		String res = "";
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		int temp = dynamicFields.length - 1;
		int maxSno__ = 0;
		if (tableName.equals("constant_master")) {
			maxSno__ = maxNo();
		} else {
			String maxCheck = "select max(sno__)  from " + tableName;
			try {
				// smt = con.createStatement();
				// rs = smt.executeQuery(maxCheck);
				// if (rs.next()) {
				// maxSno__ = rs.getInt(1);
				// }

				res = runner.singleSelection(maxCheck);
				if (res == null || res.equalsIgnoreCase("null")
						|| res.equalsIgnoreCase("")
						|| res.equalsIgnoreCase("0")) {
					maxSno__ = 0;
				} else {
					maxSno__ = Integer.parseInt(res);
				}
			} catch (Exception e) {
				setError("codeError 2insertDynamicEntry : " + e);
				e.printStackTrace();
			}
			if (maxSno__ == 0) {
				maxSno__ = 1;
			} else {
				maxSno__ = maxSno__ + 1;
			}
		}
		int tablelen = 0;
		/*
		 * if(tableName.equals("commoditymaster")) {
		 * tablelen=dynamicFields.length-1; } else {
		 */
		tablelen = dynamicFields.length;
		// }
		System.out.println("<br>tableName->" + tableName);
		System.out.println("<br>dynamicFields->" + dynamicFields.length);
		System.out.println("<br>tablelen->" + tablelen);
		String query = "";
		if (tableName.equals("constant_master")) {
			query = "insert into " + tableName + " ( ";
		} else {
			query = "insert into " + tableName + " ( sno__,";
		}
		for (int i = 0; i < tablelen; i++) {
			query += tableField[i][0];
			if (i < temp) {
				query += ",";
			} else {
				query += ") values ( '" + maxSno__ + "',";
			}
		}
		int start = 0;
		if (tableName.equals("constant_master")) {
			start = 1;
		} else {
			start = 0;
		}
		for (int i = start; i < tablelen; i++) {
			if (tableField[i][1].equalsIgnoreCase("DateTime")
					|| tableField[i][1].equalsIgnoreCase("Date")
					|| tableField[i][1].equalsIgnoreCase("Time")
					|| tableField[i][1].equalsIgnoreCase("TimeStamp")
					|| tableField[i][1].equalsIgnoreCase("Year")) {
				dynamicFields[i] = ConvertDate(dynamicFields[i]);
			} else {
				if (tableField[i][1].equalsIgnoreCase("NUMBER"))
					if (null == dynamicFields[i] || dynamicFields[i].equals("")) {
						dynamicFields[i] = "0";
					}
			}
			query += "'" + dynamicFields[i].replaceAll("'", "''") + "'";
			System.out.println("insertDynamicEntry : query : " + query);
			if (i < temp) {
				query += ",";
			} else {
				query += ")";
			}
		}
		System.out.println("insertDynamicEntry : query : " + query);
		try {
			psmt = con.prepareStatement(query);
			psmt.executeUpdate();
		} catch (Exception e) {
			setError("codeError :  insertDynamicEntry(String[][] tableFields) : "
					+ e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteEntry() {
		// Connection con = null;
		// con = DBConnection.getInstance().getDBConnection();
		StringBuffer arr = checkPrimary();
		String delRec = "delete from  " + tableName + " where " + arr + " = '"
				+ selectRecord.trim() + "'";
		setError("delRec  : " + delRec);
		try {
			// psmt = con.prepareStatement(delRec);
			// psmt.executeUpdate();
			runner.updation(delRec);
		} catch (Exception e) {
			setError("codeError deleteEntry : " + e);
			e.printStackTrace();
		}
	}

	public void editDynamicEntry(String[][] tableField, String[] dynamicFields) {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		StringBuffer arr = checkPrimary();
		StringBuffer sqlBuff = new StringBuffer();
		if (checking.equals("delete")) {
			sqlBuff.append("delete from  " + tableName + " ");
		} else {
			sqlBuff.append("update " + tableName + " set  ");
			for (int i = 0; i < dynamicFields.length; i++) {
				if (tableField[i][1].equalsIgnoreCase("DateTime")
						|| tableField[i][1].equalsIgnoreCase("Date")
						|| tableField[i][1].equalsIgnoreCase("Time")
						|| tableField[i][1].equalsIgnoreCase("TimeStamp")
						|| tableField[i][1].equalsIgnoreCase("Year")) {
					dynamicFields[i] = ConvertDate(dynamicFields[i]);
				}
				sqlBuff.append(tableField[i][0].trim() + " = '"
						+ (dynamicFields[i].trim()).replaceAll("'", "''")
						+ "',");
				System.out.println("sqlBuff--->" + sqlBuff);
			}
			sqlBuff.deleteCharAt(sqlBuff.length() - 1);
		}
		System.out.println("sqlBuff--->1" + sqlBuff);
		sqlBuff.append(" where " + arr + " = '" + selectRecord.trim() + "'");
		try {
			psmt = con.prepareStatement("" + sqlBuff);
			psmt.executeUpdate();
		} catch (Exception e) {
			if (e.getMessage() != null && !(e.getMessage().equals(""))) {
				// ORA-00001: unique constraint (RATING.SYS_C002306) violated
				// setError("This Record is already exist");
				setError(arr + " value is already exist");
			}
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// THIS IS USED TO LIST THE COLUMN NAME ONLY
	public void findTableColumnName() {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		ResultSetMetaData rsmd = null;
		sql = "select * from " + tableName;
		int col = 0;
		try {
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			rsmd = rs.getMetaData();
			col = rsmd.getColumnCount();
		} catch (Exception e) {
			setError("codeError : findTableColumnName() : " + e);
			e.printStackTrace();
		}

		String record[] = new String[col];
		try {
			if (rs.next()) {
				for (int k = 0; k < col; k++) {
					record[k] = rsmd.getColumnName(k + 1).toLowerCase();
				}
			}
		} catch (Exception e) {
			setError("codeError : findTableColumnName() : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (smt != null)
					smt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		tableFieldsOnly = record;
	}

	// THIS FUNCTION IS USED TO FIND DATABASE TABLE NAME
	public void findTableName() {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		String[] args = new String[1];
		args[0] = tableId;
		sql = "select new_table_name from created_table_master where new_table_id = ?";
		try {
			tableName = runner.singleSelection(sql, args);

			/*
			 * psmt = con.prepareStatement(sql); rs = psmt.executeQuery(); if
			 * (rs.next()) { tableName = rs.getString(1); }
			 */
		} catch (Exception e) {
			setError("codeError: findTableName() " + e);
			e.printStackTrace();
		}
	}

	// TO LIST THE ENTERED DATAS IN DYNAMIC TABLES
	public String[][] dataEntryView(String tableName) {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		ResultSetMetaData rsmd = null;
		sql = "select * from " + tableName;
		String tableDetails[][] = new String[0][0];
		try {
			// smt = con.createStatement();
			// QueryBuilder qry = new QueryBuilder(smt);
			// tableDetails = qry.getResultSet(sql);

			tableDetails = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeError : getTableDetails()1 : " + e);
			e.printStackTrace();
		}
		try {
			smt = con.createStatement();
			rs = smt.executeQuery(sql);
			rsmd = rs.getMetaData();
			viewColumnCount = rsmd.getColumnCount();
			System.out.println("rsmd.getColumnCount() " + viewColumnCount);
		} catch (Exception e) {
			setError("codeError : 1dateEntryView : " + e);
			e.printStackTrace();
		}
		String viewColumnName1[] = new String[viewColumnCount];
		try {
			for (int i = 0; i < viewColumnCount; i++) {
				// System.out.println("For Loop");
				// System.out.println("rsmd.getColumnName(i+1)"+rsmd.getColumnName(i+1));
				viewColumnName1[i] = rsmd.getColumnName(i + 1).toLowerCase();
				System.out.println("viewColumnName1..." + viewColumnName1[i]);
			}
		} catch (Exception e) {
			setError("codeError: 2dateEntryView : " + e);
			e.printStackTrace();
		}
		try {
			viewColumnName = viewColumnName1;
		} catch (Exception e) {
			setError("codeError: 3dateEntryView : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (smt != null)
					smt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return tableDetails;
	}

	public int maxNo() {
		Connection con = null;
		con = DBConnection.getInstance().getDBConnection();
		int temp = dynamicFields.length - 1;
		int maxSno__ = 0;
		String maxCheck = "";
		String res = "";
		if (tableName.equals("constant_master")) {
			maxCheck = "select nvl(max(category_id),'0') +1  from " + tableName;
		} else {
			maxCheck = "select nvl(max(sno__),'0')+1  from " + tableName;
		}
		try {
			res = runner.singleSelection(maxCheck);
			maxSno__ = Integer.parseInt(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maxSno__;
	}

	public String[][] getCommodity(String branch) {
		String args[] = new String[2];
		String[][] constant = new String[0][0];
		String sql = "";
		try {
			args[0] = branch;
			args[1] = branch;
			// sql =
			sql = "select cm.sno__,cm.commodity_id,cm.commodity_name,cm.amend_id from commoditymaster cm where cm.status in ('Y','N','R') and branch_code=? and cm.sno__||cm.amend_id||cm.commodity_id in (select max(sno__)||max(amend_id)||commodity_id from commoditymaster where status in('Y','N','R') and branch_code=? group by commodity_id) group by cm.commodity_id,cm.commodity_name,cm.amend_id,cm.sno__ order by cm.commodity_name";
			constant = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public String[][] getSaleTerm(String branch) {
		String args[] = new String[1];
		String[][] constant = new String[0][0];
		String sql = "";
		try {
			args[0] = branch;
			// args[1] = branch;
			sql = "select stm.sale_term_id,stm.sale_term_name from sale_term_master stm where stm.branch_code=?";
			// sno__,cm.commodity_id,cm.commodity_name,cm.amend_id from
			// commoditymaster cm where cm.status in ('Y','R') and branch_code=?
			// and cm.sno__||cm.amend_id||cm.commodity_id in (select
			// max(sno__)||max(amend_id)||commodity_id from commoditymaster
			// where
			// to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')
			// and status in('Y','R') and branch_code=? group by commodity_id)
			// and
			// to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')
			// group by cm.commodity_id,cm.commodity_name,cm.amend_id,cm.sno__
			// order by cm.commodity_name";
			constant = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public String[][] getCountry(String belongingCid) {
		String[][] constant = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = belongingCid;
			args[1] = belongingCid;
			// sql =
			// "select cm.sno__,cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and cm.status in('Y','R') and BELONGING_COUNTRY_ID=? group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			sql = "select cm.sno__,cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id from COUNTRY_MASTER_DETAIL cm where cm.status in ('Y','R') and BELONGING_COUNTRY_ID=? and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER_DETAIL where cm.status in('Y','R') and BELONGING_COUNTRY_ID=? group by COUNTRY_ID) group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			constant = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	
	
	public String[][] getConveyDetails(String conveyId, String branch) {
		String[][] conveyDetails = new String[0][0];
		String args[] = new String[4];
		String sql = "";
		try {
			args[0] = conveyId;
			args[1] = conveyId;
			args[2] = branch;
			args[3] = branch;
			sql =  " select CONV_MEAN,CONVDESC,MODE_TRANSPORT_ID,EFFECTIVE_DATE,STATUS,AMEND_ID,REMARKS,CORE_APP_ID,CONV_RATE FROM CONVEYAN_MASTER WHERE CONV_MEAN = ? " +
					" AND AMEND_ID = (select max(AMEND_ID) from CONVEYAN_MASTER where CONV_MEAN = ? AND branch_code = ? ) AND branch_code = ? ";
			conveyDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conveyDetails;
	}

///////////////////////////    RETREIVE COMMODITY EXCESS DEATILS    //////////////////////////	
	
	
	public String[][] getCommodExcessDetails(String CommoExcessId, String branch) {
		String[][] CommodExcess = new String[0][0];
		String args[] = new String[4];
		String sql = "";
		try {
			args[0] = CommoExcessId;
			args[1] = CommoExcessId;
			args[2] = branch;
			args[3] = branch;
			
			sql =  " SELECT SNO,START_SUM_INSURED,END_SUM_INSURED, DEDUCTIBLE,RATE,AMEND_ID ,"+                                       
					"EFFECTIVE_DATE, STATUS  FROM  COMMODITY_EXCESS_PREMIUM A WHERE A.SNO=? AND  A.AMEND_ID||A.SNO in "+
					"(SELECT MAX(B.AMEND_ID)||SNO FROM COMMODITY_EXCESS_PREMIUM B WHERE B.SNO=? " +
					"AND  B.BRANCH_CODE =? "+				
					"GROUP BY B.SNO  ) AND BRANCH_CODE =?  ";
			System.out.println("sql"+sql);
			CommodExcess = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return CommodExcess;
		
	}
	
	
	public String[][] getAmendedComDetails(String comId, String branch) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = comId;
			args[1] = branch;
			//1sql = "select COMMODITY_ID,COMMODITY_NAME,ICC_A_SEA,AF_ICC_A_SEA,ICC_C_SEA,ICC_C_ND_SEA,AF_ICC_C_ND_SEA,AF_ICC_C_SEA,LIMITED_COVER,FROZEN_CARGO_SEA,AF_FROZEN_CARGO_SEA,AF_ICC_AIR,AF_LIMITED_COVER_AIR,ICC_AIR,LIMITED_COVER_AIR,FROZEN_CARGO_AIR,AF_FROZEN_CARGO_AIR,AF_ALL_RISK_LAND,AF_LIMITED_COVER_LAND,ALL_RISK_LAND,LIMITED_COVER_LAND,FROZEN_CARGO_LAND,AF_FROZEN_CARGO_LAND,AF_ALL_RISK_AIR_SEA_LAND,ALL_RISK_AIR_SEA_LAND,FROZEN_CARGO_AIR_SEA_LAND,AF_FROZEN_CARGO_AIR_SEA_LAND,FRAGILE,RAG,EXCLUSION_ID,WARRANTY_ID,REMARKS,STATUS,to_char(effective_date,'DD-MM-YYYY'),amend_id from commoditymaster where commodity_id=? and branch_code=?";
			//2sql = "select COMMODITY_ID, COMMODITY_NAME, ICC_A_SEA, ICC_B_SEA, ICC_C_SEA, ICC_AIR_CARGO_AIR, ALL_RISKS_PARCEL_POST_AIR, ALL_RISKS_LAND_TRANSIT_LAND, LAND_TRANSIT_LAND,ALL_RISKS_SEA_AND_AIR, TO_CHAR(EFFECTIVE_DATE,'DD-MM-YYYY'), COMMODITY_TYPE_ID, STATUS, AMEND_ID ,FRAGILE,RAG,EXCLUSION_ID,WARRANTY_ID,REMARKS from commoditymaster where commodity_id=? and branch_code=?";
			sql = "select COMMODITY_ID, COMMODITY_NAME, ICC_A_SEA, ICC_B_SEA, ICC_C_SEA, ICC_AIR_CARGO_AIR, ALL_RISKS_PARCEL_POST_AIR, ALL_RISKS_LAND_TRANSIT_LAND, LAND_TRANSIT_LAND,ALL_RISKS_SEA_AND_AIR, TO_CHAR(EFFECTIVE_DATE,'DD-MM-YYYY'), FRAGILE,RAG,EXCLUSION_ID,WARRANTY_ID,REMARKS, STATUS, AMEND_ID from commoditymaster where commodity_id=? and branch_code=?";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public String[][] getCountryDetails(String cId, String belongingCid) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = cId;
			args[1] = belongingCid;
			args[2] = cId;
			args[3] = belongingCid;
			
			sql = "select COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,WAR_RATE,STARTING_PLACE,SP_WARRANTIES_CONDITIONS," +
					"ENDING_PLACE,EP_WARRANTIES_CONDITIONS, STATUS,RSACODE,NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE," +
					"REMARKS from COUNTRY_MASTER_DETAIL where COUNTRY_ID=? and BELONGING_COUNTRY_ID=? and amend_id =" +
					"(select max(amend_id) from COUNTRY_MASTER_DETAIL where country_id=? and BELONGING_COUNTRY_ID=?)";

			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public String[][] getAmededCountryDetails(String cuid, String cid) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = cuid;
			args[1] = cid;
			sql = "select COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,WAR_RATE,STARTING_PLACE,SP_WARRANTIES_CONDITIONS,ENDING_PLACE,EP_WARRANTIES_CONDITIONS,STATUS,RSACODE,NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS from COUNTRY_MASTER_DETAIL where COUNTRY_ID = ? and BELONGING_COUNTRY_ID=?";

			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public String[][] getAmededPolicyDetails(String tId) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = tId;

			sql = "select TYPE_ID,TYPE_DESC,BRANCH_CODE,AGENCY_CODE,POLICY_PREFIX,START_NO,END_NO,CURRENT_NO,REMARKS,STATUS,POLICY_QUOTE,LOGIN_ID,DESCRIPTION,PRODUCT_ID,AMEND_ID,EFFECTIVE_DATE from POLICYNO_GENERATE where TYPE_ID = ?";

			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public String[][] getAmededPolicyDetailsCommision(String brokeragency) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = brokeragency;
			sql = "select START_NO,END_NO,CURRENT_NO,EFFECTIVE_DATE from POLICYNO_GENERATE where AGENCY_CODE = ?";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	public String[][] getPolicyDetailsInCommision(String brokerCode) {
		String[][] comDetails = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		try {
			args[0] = brokerCode;
			args[1] = brokerCode;

			sql = "select TYPE_ID,TYPE_DESC,BRANCH_CODE,AGENCY_CODE,POLICY_PREFIX,START_NO,END_NO,CURRENT_NO,REMARKS,STATUS,POLICY_QUOTE,LOGIN_ID,DESCRIPTION,PRODUCT_ID,AMEND_ID,EFFECTIVE_DATE from POLICYNO_GENERATE where AGENCY_CODE = ? and amend_id=(select max(amend_id) from policyno_generate where AGENCY_CODE = ? and status='Y')";

			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	// OLD COMMENTED FOR CUSTOMER DEBIT SERIES GENERATION FOR BROKERS
	/*
	 * public String[][] getPolicyDetails(String tId, String amendId) {
	 * String[][] comDetails = new String[0][0]; String args[] = new String[2];
	 * String sql = ""; try { args[0] = tId; args[1] = amendId;
	 * 
	 * sql =
	 * "select TYPE_ID,TYPE_DESC,BRANCH_CODE,AGENCY_CODE,POLICY_PREFIX,START_NO,END_NO,CURRENT_NO,REMARKS,STATUS,POLICY_QUOTE,LOGIN_ID,DESCRIPTION,PRODUCT_ID,AMEND_ID,EFFECTIVE_DATE from POLICYNO_GENERATE where TYPE_ID = ? and AMEND_ID = ?"
	 * ;
	 * 
	 * comDetails = runner.multipleSelection(sql,args); } catch (Exception e) {
	 * e.printStackTrace(); } return comDetails; }
	 */

	public String[][] getPolicyDetails(String agencyCode, String productId,
			String branchCode) {
		String[][] comDetails = new String[0][0];
		String args[] = new String[3];
		String sql = "";
		try {
			if (branchCode != null && branchCode.length() > 0) {
				branchCode = branchCode.replaceAll("'", "");
			}
			args[0] = agencyCode;
			args[1] = branchCode;
			args[2] = productId;

			// sql =
			// "select TYPE_ID,TYPE_DESC,BRANCH_CODE,AGENCY_CODE,POLICY_PREFIX,START_NO,END_NO,CURRENT_NO,REMARKS,STATUS,POLICY_QUOTE,LOGIN_ID,DESCRIPTION,PRODUCT_ID,AMEND_ID,EFFECTIVE_DATE from POLICYNO_GENERATE where TYPE_ID = ? and AMEND_ID = ?";
			sql = " select  AGENCY_CODE,AMEND_ID,BRANCH_CODE,CURRENT_NO,DESCRIPTION,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),END_NO,LOGIN_ID,POLICY_PREFIX,POLICY_QUOTE,PRODUCT_ID,REMARKS,SNO__,START_NO,STATUS,TYPE_DESC,TYPE_ID from policyno_generate where "
					+ " AGENCY_CODE = ?  AND branch_code = ? AND product_id = ? ";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}
	

	public void validateSubDetails(String branch,String mode) {
		
		if (null == commodityName || commodityName.equals("")) 
			setError("Enter valid commodity_name");
		if(!commodityName.equals("") && mode.equals("add"))
			   
		   {
			String comm=(commodityName.toUpperCase()).trim();
			   String[] cols=new String[2];
			   cols[0]=branch;
			   cols[1]=comm;
			  //// System.out.println("CommodityName--->"+comm);
			   String appcode="SELECT COUNT(*) from commoditymaster where branch_code=? and upper(COMMODITY_NAME)=? ";
			   String a=runner.singleSelection(appcode, cols);
			  ////// System.out.println("RSACODEFOR CONVEY"+a);
			   if(Integer.parseInt(a)>0)
			   {
				   setError("Commodity Name is already exists ");   
			   }
		   }
		
	/*	if (null == rag || rag.equals("")) 
			setError("Enter valid rag"); */
		  
		if(commodityType == null || commodityType.equalsIgnoreCase("select") || commodityType.equalsIgnoreCase(""))
			setError("Enter valid commodity Type"); 
		
		if (commrate == null || commrate.equals(""))
		{ 
			setError("Enter Valid  commodity Rate ");
		}
		else{
			try{
			float f=Float.parseFloat(commrate);
			//////System.out.println("fffffffffff===>"+f);
			}catch(Exception e)
			{
				/////System.out.println(e.getMessage());
				setError("Commodity Rate  Should be Numeric ");	
			}
		}
		int len=iccClause.length;
		System.out.println("len-----------********>"+len);
		for(int i=0;i<len;i++)
		{
			if(i>=3 && i<=6 )
			{
			System.out.println("3-6*************:"+iccClause[i]+i);
			}
			else {
				  System.out.println("cluase*************:"+i+"-----"+iccClause[i]);
				  if(iccClause[i].equals("") && i==0)
				  {
					  setError("Select ICC A SEA RATE Clause");
				  }
				  if(iccClause[i].equals("") && i==1)
				  {
					  setError("Select ICC B SEA RATE  Clause ");
				  }
				  if(iccClause[i].equals("") && i==2)
				  {
					  setError("Select ICC C SEA RATE Clause");
				  }
				  if(iccClause[i].equals("") && i==7)
				  {
					  setError("Select ICC AIR CARGO AIR Clause");
				  }
				  if(iccClause[i].equals("") && i==8)
				  {
					  setError("Select ALL RISKS LAND TRANSIT Clause");
				  }
				  if(iccClause[i].equals("") && i==9)
				  {
					  setError("Select LAND TRANSIT LAND Clause");
				  }
				  if(iccClause[i].equals("") && i==10)
				  {
					  setError("Select ALL RISKS PARCEL POST AIR Clause");
				  }
				  /*if(iccClause[i].equals("") && i==11)
				  {
					  setError("Select ALL RISKS SEA AND AIR Clause");
				  }*/
			 }
		}
		

	
		if (null == startDate || startDate.equals("")) {
			setError("Enter valid startDate");
		}

		String existedDate = "";
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		sf.setTimeZone(new java.util.SimpleTimeZone(14400000, "GMT"));
		existedDate = sf.format(new java.util.Date());

		long diff = 0;
		try {
			DateFunction dbr = new DateFunction();
			diff = dbr.getDayDifference(dbr.getCalendar(existedDate), dbr
					.getCalendar(startDate));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (existedDate != null && diff < 0 && !existedDate.equals("")) {
			setError("Enter valid Date");
		}
		
/*		commodityRateValidation(icc_a_sea,"1",branch);
		commodityRateValidation(af_icc_a_sea,"2",branch);
		commodityRateValidation(icc_c_sea,"3",branch);
		commodityRateValidation(icc_c_nd_sea,"4",branch);
		commodityRateValidation(af_icc_c_nd_sea,"5",branch);
		commodityRateValidation(af_icc_c_sea,"6",branch);
		commodityRateValidation(limited_cover,"7",branch);
		commodityRateValidation(frozen_cargo_sea,"8",branch);
		commodityRateValidation(af_frozen_cargo_sea,"9",branch);

		commodityRateValidation(af_icc_air,"10",branch);
		commodityRateValidation(af_limited_cover_air,"11",branch);
		commodityRateValidation(icc_air,"12",branch);
		commodityRateValidation(limited_cover_air,"13",branch);
		commodityRateValidation(frozen_cargo_air,"20",branch);
		commodityRateValidation(af_frozen_cargo_air,"21",branch);

		commodityRateValidation(af_all_risk_land,"14",branch);
		commodityRateValidation(af_limited_cover_land,"15",branch);
		commodityRateValidation(all_risk_land,"16",branch);
		commodityRateValidation(limited_cover_land,"17",branch);
		commodityRateValidation(frozen_cargo_land,"22",branch);
		commodityRateValidation(af_frozen_cargo_land,"23",branch);

		commodityRateValidation(af_all_risk_air_sea_land,"18",branch);
		commodityRateValidation(all_risk_air_sea_land,"19",branch);
		commodityRateValidation(frozen_cargo_air_sea_land,"24",branch);
		commodityRateValidation(af_frozen_cargo_air_sea_land,"25",branch);

		if (null == startDate || startDate.equals("")) {
			setError("Enter valid startDate");
		}
		//String existedDate = getExistedDate(commodityId, amendId);
		String existedDate = "";
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
    	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
    	existedDate = sf.format(new java.util.Date());
        
		long diff = 0;
		try {
			DateFunction dbr = new DateFunction();
			diff = dbr.getDayDifference(dbr.getCalendar(existedDate), dbr.getCalendar(startDate));
		}catch (Exception e) {
			e.printStackTrace();
		}

		if (existedDate != null && diff < 0 && !existedDate.equals("")) {
			setError("Enter valid Date");
		}*/
		if(coreApplicationCode.equals(""))
		{
			setError("Enter Core Application Code");
		}
		
		else if(!coreApplicationCode.equals("") )
		{
			System.out.println("mode:*************"+mode);
			 String a="0";
			 String b="0";
			if(mode.equals("add"))
					{
				   String[] cols=new String[2];
				   cols[0]=branch;
				   cols[1]=coreApplicationCode;
				   String appcode="SELECT COUNT(*) from COMMODITYMASTER where branch_code=? and RSACODE=? ";
				    a=runner.singleSelection(appcode, cols);				   
				   if(Integer.parseInt(a)>0)
				   {
					   setError("Core Application Code is already exists ");   
				   }
			   }
			if(mode.equals("edit"))
			{
				 String appcode="";
				  
		   String[] cols1=new String[3];
		   cols1[0]=branch;
		   cols1[1]=coreApplicationCode;
		   cols1[2]=commodityId;
		   appcode="SELECT COUNT(*) from COMMODITYMASTER where branch_code=? and RSACODE=? and COMMODITY_ID not in (?) ";
		    b=runner.singleSelection(appcode, cols1);				   
		   if(Integer.parseInt(b)>0)
		   {
			   setError("Core Application Code is already exists ");   
		   }
	   }
		}
		/*else if(!coreApplicationCode.equals("") && mode.equals("add"))
		{
			{
				String a="0";
				   String[] cols=new String[3];
				   cols[0]=branch;
				   cols[1]=coreApplicationCode;
				   cols[2]=commodityId;
				   String appcode="SELECT COUNT(*) from COMMODITYMASTER where branch_code=? and RSACODE=? and COMMODITY_ID=? ";
				    a=runner.singleSelection(appcode, cols);				   
				   if(Integer.parseInt(a)>0)
				   {
					   setError("Core Application Code is already exists ");   
				   }
			   }	
		}*/
			
	}
public void validateConveyDetails(String branch,String mode) {
		
		if (null == ConveyName || ConveyName.equals("")) 
			setError("Enter valid Convey Name");
		if(!ConveyName.equals("") && mode.equals("add"))
			   
		   {
			String conv=(ConveyName.toUpperCase()).trim();
			   String[] cols=new String[2];
			   cols[0]=branch;
			   cols[1]=conv;
			  			   String appcode="SELECT COUNT(*) from conveyan_master where branch_code=? and upper(CONVDESC)=? ";
			   String a=runner.singleSelection(appcode, cols);
			 
			   if(Integer.parseInt(a)>0)
			   {
				   setError("Convey Name is already exists ");   
			   }
		   }
		  
		if(ConveyModeOfTransport == null || ConveyModeOfTransport.equalsIgnoreCase("") || ConveyModeOfTransport.equalsIgnoreCase(""))
			setError("Select Mode Of Transport"); 
		
		if (ConveyRate == null || ConveyRate.equals(""))
		{ 
			setError("Enter Valid  Convey Rate ");
		}
		
			else{
				try{
				float f=Float.parseFloat(ConveyRate);
				//////System.out.println("fffffffffff===>"+f);
				}catch(Exception e)
				{
					/////System.out.println(e.getMessage());
					setError("Convey Rate  Should be Numeric ");	
				}
	System.out.println("conveymode:"+mode);			
   if(!ConveyCoreAppCode.equals(""))
	   
   {
	   String a="0";
	   String b="0";
   
	   if(mode.equals("add"))
	   {
	   String[] cols=new String[2];
	   cols[0]=branch;
	   cols[1]=ConveyCoreAppCode;
	   String appcode="SELECT COUNT(*) from conveyan_master where branch_code=? and CORE_APP_ID=? ";
	    a=runner.singleSelection(appcode, cols);
	   System.out.println("RSACODEFOR CONVEY"+a);
	   if(Integer.parseInt(a)>0)
	   {
		   setError("Core Application Code is already exists ");   
	   }
	   }
	   
	   if(mode.equals("edit"))
		{
		 String appcode="";	  		  
	   String[] cols1=new String[3];
	   cols1[0]=branch;
	   cols1[1]=ConveyCoreAppCode;
	   cols1[2]=ConveyId;
	   appcode=" SELECT COUNT(*) from conveyan_master where branch_code=? and CORE_APP_ID=? and CONV_MEAN not in(?) ";
	    b=runner.singleSelection(appcode, cols1);
	    System.out.println("b----->"+b);
	   if(Integer.parseInt(b)>0)
	   {
		   setError("Core Application Code is already exists ");   
	   }
	   
   }
   }
}
}
public void validateCommodExcessDetails(String branch)
{
	System.out.println("CommodExcessStartInsured"+CommodExcessStartInsured);
	
	if (null == CommodExcessStartInsured.trim() || CommodExcessStartInsured.trim().equals("")) 
		setError("Enter valid Staring Amount");
	else{
		int n=0;
    for (int i = 0; i < CommodExcessStartInsured.length(); i++) {
       
        if (!Character.isDigit(CommodExcessStartInsured.charAt(i)))
        	n++;        
    }        
    if(n>0)
    {
    	setError("Starting Amount Should Be Numeric");
    }
	}
	////System.out.println("CommodExcessStartInsuredlong---->"+Long.parseLong(CommodExcessStartInsured));
    if (null == CommodExcessEndInsured.trim() || CommodExcessEndInsured.trim().equals("")) 
		setError("Enter valid Ending Amount");
	else{
		int k=0;

    for (int i = 0; i < CommodExcessEndInsured.length(); i++) {

        //If we find a non-digit character we return false.
        if (!Character.isDigit(CommodExcessEndInsured.charAt(i)))
        	k++;
        
    }        
    if(k>0)
    {
    	setError("Ending Amount Should Be Numeric");
    }
	}
   /// System.out.println("hai"+Long.parseLong(CommodExcessEndInsured));
    if(!CommodExcessEndInsured.equals("") && !CommodExcessEndInsured.equals(""))
    {
    if(Long.parseLong(CommodExcessEndInsured) <= Long.parseLong(CommodExcessStartInsured))
    {
    	setError("Starting  Amount Should Be Less Than Ending Amount");	
    }
    System.out.println("CommodExcessId:&&&&&&&&&&"+CommodExcessId);
    if(CommodExcessId.length()<=0)
    {
   
    	String a="0";
    	String argse[]=new String[2];
    	argse[0]=branch;
    	argse[1]=CommodExcessEndInsured;
    	String sqlamount="select count(*) from  COMMODITY_EXCESS_PREMIUM where branch_code=?  having max(END_SUM_INSURED)>? ";
    	a=runner.singleSelection(sqlamount,argse);
    	a=StringUtils.isEmpty(a)==true?"0":a;
    	
    	if(Integer.parseInt(a)>0)
    	{
    	System.out.println("aaaaaaaaaaaaaaa-->"+a);
    	setError("Starting  Amount and Ending Amount Range Already Exists");	
    	}
    	
    }
    }
    if (null == CommodExcessDeductible || CommodExcessDeductible.equals("")) 
		setError("Enter valid Deductible");
	else{
		int d=0;

    for (int i = 0; i < CommodExcessDeductible.length(); i++) {    
        if (!Character.isDigit(CommodExcessDeductible.charAt(i)))
        	d++;        
    }        
    if(d>0)
    {
    	setError("Deductible Should Be Numeric");
    }
	}
    
    if (null == CommoExcessRate || CommoExcessRate.equals("")) 
		setError("Enter valid rate");
	/*else{
		int r=0;

    for (int i = 0; i < CommoExcessRate.length(); i++) {      
        if (!Character.isDigit(CommoExcessRate.charAt(i)) || !CommoExcessRate.charAt(i).equals(".") )
        	
        	r++;        
    }        
    if(r>0)
    {
    	setError("Rate Should Be Numeric");
    }
	
	}*/	
	
}
	
	public void validateWarRateMaster(String mode) {
		try {

			if(modeOfTransID.equalsIgnoreCase("") || modeOfTransID.equalsIgnoreCase("select"))
				error = "Please, Select Mode Of Transport<br>";
			
			if(warDesc.equalsIgnoreCase(""))
				error = error + "Please, Enter War Description<br>";
			
			if(warrate.equalsIgnoreCase(""))
				error = error + "Please, Enter War Rate<br>";
			
			if(coreApplicationCode.equalsIgnoreCase(""))
				error = error + "Please, Enter Core Application Code<br>";
			else {
				if(mode.equalsIgnoreCase("add"))
				{
					String res = isCoreAppCodeAvailable( branch_code, coreApplicationCode, "war_rate_master", "core_app_id", "" );
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
				
				if(mode.equalsIgnoreCase("edit"))
				{
					String res = isCoreAppCodeAvailable( branch_code, coreApplicationCode, "war_rate_master", "core_app_id", " and war_id != " + warID);
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
			}

			if(effective_date.equalsIgnoreCase(""))
				error = error + "Please, Enter Effective Date<br>";
			
			if(status.equalsIgnoreCase(""))
				error = error + "Please, Select the status";

			if(error.length()>0)
				setError(error);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void validateMaterialTypeMaster(String mode) {
		try {

			if(branch_code.equalsIgnoreCase("") || branch_code.equalsIgnoreCase("select"))
				error = "Please, Select Branch Code<br>";
			
			if(mode.equalsIgnoreCase("edit") && (matID.equalsIgnoreCase("") || matID.equalsIgnoreCase("select")))
				error = error + "Please, Select Material Description<br>";
			
			if(matDesc.equalsIgnoreCase(""))
				error = error + "Please, Enter Material Description<br>";
			
			if(!matDesc.equalsIgnoreCase("") && mode.equalsIgnoreCase("add")){
				String res = isMaterialDescAvailable( branch_code, matDesc );
				if(res.equalsIgnoreCase("yes"))
					error = error + "Given Material Description is already exists. Enter another one.<br>";
			}
				
			if(matRate.equalsIgnoreCase(""))
				error = error + "Please, Enter Material Rate<br>";
			
			if(coreApplicationCode.equalsIgnoreCase(""))
				error = error + "Please, Enter Core Application Code<br>";
			else {
				if(mode.equalsIgnoreCase("add"))
				{
					String res = isCoreAppCodeAvailable( branch_code, coreApplicationCode, "material_type_master", "core_app_id", "" );
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
				
				if(mode.equalsIgnoreCase("edit"))
				{
					String res = isCoreAppCodeAvailable( branch_code, coreApplicationCode, "material_type_master", "core_app_id", " and material_id != " + matID );
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
			}
			
			if(effective_date.equalsIgnoreCase(""))
				error = error + "Please, Enter Effective Date<br>";
			
			if(status.equalsIgnoreCase(""))
				error = error + "Please, Select the status";

			if(error.length()>0)
				setError(error);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void validateCountryMaster(String mode) {
		try {
			if(countryname.equalsIgnoreCase(""))
				error = "Please, Enter Country Name<br>";
			
			if(!countryname.equalsIgnoreCase("") && mode.equalsIgnoreCase("add") && !isCountryNameAvail(countryname).equalsIgnoreCase(""))
				error = "Given country name is already available.<br>Please, Give another one<br>";
			
			if(nationalityname.equalsIgnoreCase(""))
				error = error + "Please, Enter Nationality Name<br>";
			
			if(geoRate.equalsIgnoreCase(""))
				error = error + "Please, Enter Geo Rate<br>";
			
			if(coreApplicationCode.equalsIgnoreCase(""))
				error = error + "Please, Enter Core Application Code<br>";
			else {
				if(mode.equalsIgnoreCase("add"))
				{
					String res = isCoreAppCodeAvailable( "", coreApplicationCode, "country_master", "rsacode", "" );
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
				
				if(mode.equalsIgnoreCase("edit"))
				{
					String res = isCoreAppCodeAvailable( "", coreApplicationCode, "country_master", "rsacode", " and country_id != " + countryid );
					if(res.equalsIgnoreCase("yes"))
						error = error + "Given Core Application ID is already exists. Enter another one.<br>";
				}
			}
			
			if(effective_date.equalsIgnoreCase(""))
				error = error + "Please, Enter Effective Date<br>";
			
			if(status.equalsIgnoreCase(""))
				error = error + "Please, Select the status";

			if(error.length()>0)
				setError(error);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validatePolicyDetails() {
		if (type_desc.equals("") || type_desc.equalsIgnoreCase("null")) {
			setError("Please Enter  Type Description");
		}
		if (branch_code.equalsIgnoreCase("")
				|| branch_code.equalsIgnoreCase("null")
				|| branch_code.equalsIgnoreCase("Select")) {
			setError("Please Select valid Branch");
		}
		if (agency_code.equalsIgnoreCase("")
				|| agency_code.equalsIgnoreCase("null")
				|| agency_code.equalsIgnoreCase("select")) {
			setError("Please Select Broker");
		}
		if (product_id1.equals("") || product_id1.equalsIgnoreCase("Select")
				|| product_id1.equalsIgnoreCase("null")) {
			setError("Please Select  Product");
		}
		if (null == start_no.trim() || start_no.trim().equals("")) {
			setError("Please Enter valid StartNo");
		}
		if ((!start_no.trim().equalsIgnoreCase(""))
				&& (!StringUtil.isNumber(start_no.trim()))) {
			setError("Start Number Should Be Numeric");
		}
		/*
		 * if (!start_no.trim().equals("") && getMaxEndNo() != null&&
		 * Long.parseLong(getMaxEndNo()) >= Long.parseLong(start_no.trim())) {
		 * // New setError("Start Number Should Be Greater Than " +
		 * getMaxEndNo()); }
		 */
		if (null == end_no.trim() || end_no.trim().equals("")) {
			setError("Please Enter valid EndNo");
		}
		try {
			if ((!end_no.trim().equalsIgnoreCase(""))
					&& (!StringUtil.isNumber(end_no.trim()))) {
				setError("End Number Should Be Numeric");
			} else if (Long.parseLong(start_no.trim()) >= Long.parseLong(end_no
					.trim())) {
				setError("End No Should Be Greater Than Start No");
			}
		} catch (Exception e) {
			System.out.println("Exception in no compare" + e.toString());
		}
		if (null == current_no.trim() || current_no.trim().equals("")) {
			setError("Enter valid CurrentNo");
		}
		if ((!current_no.trim().equalsIgnoreCase(""))
				&& (!StringUtil.isNumber(current_no.trim()))) {
			setError("Current Number Should Be Numeric");
		}
		try {
			/*
			 * if (product_id1.trim().equals("11") &&
			 * Long.parseLong(start_no.trim()) <= 9000000)
			 * setError("Start Number Should Be Greater Than 9000000");
			 */
			if (Long.parseLong(current_no.trim()) < Long.parseLong(start_no
					.trim())
					|| Long.parseLong(current_no.trim()) > Long
							.parseLong(end_no.trim())) {
				setError("Current Number Must  be Between  StartNo and EndNo");
			}
		} catch (Exception e) {
			System.out.println("Exception in " + e.toString());
			e.printStackTrace();
		}
		if (null == description || description.equals("")) {
			setError("Enter Valid Description");
		}
		if (null == startDate2 || startDate2.equals("")) {
			setError("Enter valid startDate");
		}
		String existedDate = getExistedPolicyDate(type_id, amend_id);
		long diff = 0;
		try {
			DateFunction dbr = new DateFunction();
			diff = dbr.getDayDifference(dbr.getCalendar(existedDate), dbr
					.getCalendar(startDate2));
		} catch (Exception e) {
		}
		if (diff <= 0 && existedDate != null && !existedDate.equals("")) {
			setError("Enter valid Date");
		} else {
			// this.existedDate=startDate;
		}
		// alreadySubBusiness();

		if (remarks2.length() > 100) {
			remarks2 = remarks2.substring(0, 100);
		}
	}

	// validation for poliy generation table
	public void validateCountryDetails() {
		String building = "";
		String content = "";
		String tariffb = "";
		String tariffc = "";
		String loading = "";
		String trsmd = "";
		String tstfi = "";
		char ch = '.';
		/*
		 * if(null==commodity_id || commodity_id.equals("")) {
		 * //setErrorId("43");undatabasify();setError(getErrorDesc());
		 * setError("Enter valid SubTradeGroupId"); }
		 */
		if (null == countryname || countryname.equals("")
				|| countryname.equalsIgnoreCase("Select")) {
			setError("Enter  Country Name");
		}
		/*
		 * if(null==countryshortname || countryshortname.equals("")) {
		 * setError("Select valid countryshortname"); }
		 */
		if (null == warrate || warrate.equals("")) {
			setError("Enter valid War Rate");
		}
		building = warrate;
		int in = building.indexOf(ch);
		if ((!warrate.equalsIgnoreCase("")) && (!StringUtil.isNumber(warrate))) {
			setError("War rate Should be Numeric");
		}
		/*
		 * else if ((in > 3 || in == -1) && icc_a.length() > 3) {
		 * setError("Precision accepts in warrate upto 3 digits only"); }
		 */
		if (null == startingplace || startingplace.equals("")
				|| startingplace.equalsIgnoreCase("Select")) {
			setError("Select Starting place");
		}
		if (null == endingplace || endingplace.equals("")
				|| endingplace.equalsIgnoreCase("Select")) {
			setError("Select Ending Place");
		}
		if (null == nationalityname || nationalityname.equals("")) {
			setError("Enter valid Nationality Name");
		}
		if (null == startDate1 || startDate1.equals("")) {
			setError("Enter valid startDate");
		}
		/*
		 * if(null==endDate || endDate.equals("")) { setError("Enter valid
		 * endDate"); }
		 */

		String existedDate = getExistedCuntryDate(countryid, amendid1);
		long diff = 0;
		try {
			DateFunction dbr = new DateFunction();
			diff = dbr.getDayDifference(dbr.getCalendar(existedDate), dbr
					.getCalendar(startDate1));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (diff <= 0 && existedDate != null && !existedDate.equals("")) {
			setError("Enter valid Date");
			// err=true;
		} else {
			// this.existedDate=startDate;
		}
		// alreadySubBusiness();
	}
///////////////////////            COMMODITY DETAILS                ////////////////////////////////////////////	

	public String insertCommodity(String mode, String branch) 
	{
		///////System.out.println("*********************************************");
		String process = "";
		String args[] = new String[0];
		String argss[] = new String[1];
		String res = "";
		int amend_id = 0;
		int sno = 0;
		int subBusinessId = 0;
		try {
		if (commodityId.length() > 0 && !commodityId.equals("")) {
			subBusinessId = Integer.parseInt(commodityId);
		}
		if (mode.equalsIgnoreCase("add")) {
			argss[0] = branch;
			sql = "select max(COMMODITY_ID+1) from COMMODITYMASTER where branch_code=?";
			////System.out.println("sql:"+sql);
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					subBusinessId = Integer.parseInt(res);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		} else {
			argss = new String[2];
			argss[0] = commodityId;
			argss[1] = branch;
			subBusinessId = Integer.parseInt(commodityId);
			sql = "select max(nvl(AMEND_ID,'0'))+1 from COMMODITYMASTER where COMMODITY_ID=? and branch_code=?";
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					amend_id = Integer.parseInt(res);
				////System.out.println("amend_id:"+amend_id);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		}
		////System.out.println("lengthwarconever"+iccwarcover.length);
	///	System.out.println("length:"+getIccClause().length);
		sql = "select max(SNO__+1) from COMMODITYMASTER";
		////System.out.println("comm:"+sql);
		try {
			res = runner.singleSelection(sql);
			sno = Integer.parseInt(res);
			System.out.println("test sno:"+sno);
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()1 : " + e);
			e.printStackTrace();
		}
		if (subBusinessId == 0) {
			subBusinessId = 1;
		}
		
			sql =    " INSERT INTO COMMODITYMASTER (SNO__,COMMODITY_ID,COMMODITY_NAME,ICC_A_SEA,ICC_B_SEA,ICC_C_SEA," +
					 "ICC_A_FROZEN_MEAT_SEA,ICC_A_FROZEN_FOOD_SEA," +
					 "ICC_C_FROZEN_MEAT_SEA,ICC_C_FROZEN_FOOD_SEA,ICC_AIR_CARGO_AIR, "+
					 "ALL_RISKS_LAND_TRANSIT_LAND,LAND_TRANSIT_LAND,ALL_RISKS_PARCEL_POST_AIR,ALL_RISKS_SEA_AND_AIR," +
					 "STATUS,EFFECTIVE_DATE,AMEND_ID,COMMODITY_TYPE_ID," +
					 "BRANCH_CODE,FRAGILE,RAG,COMMODITY_EXCESS_PREMIUM,EXCLUSION_ID,WARRANTY_ID,REMARKS,RSACODE,COMMODITY_RATE,COVERAGE_REFERAL) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?)";
			commodity_name = commodity_name.replaceAll("'", "''");
			if (remarks != null && remarks.length() > 0)
				remarks = remarks.replaceAll("'", "''");
		////System.out.println("sql:"+sql);
		///System.out.println("COVERAGE_REFERAL:"+COVERAGE_REFERAL);
			
			String[] argsI = new String[29];
			
			argsI[0] = Integer.toString(sno).trim();
		/////System.out.println("argsI[0]"+argsI[0]);
			
			argsI[1] = Integer.toString(subBusinessId).trim();
			////System.out.println("args[1]"+argsI[1]);
			argsI[2] = commodityName;		
			int h=3;
		////System.out.println("iccClause.length--->"+iccClause.length);
		///System.out.println("iccClause[i]:"+iccClause[1]);
			for(int i=0;i<iccClause.length;i++)
			{
				///System.out.println("iccClause[i]:"+iccClause[i]);
				///System.out.println("iccExclusion[i]:"+iccExclusion[i]);
				////System.out.println("iccWarrenty[i]:"+iccWarrenty[i]);
				if(iccClause[i].equals(""))
				{
					iccClause[i]="";	
				}
				if(iccExclusion[i].equals(""))
				{
					iccExclusion[i]="";	
				}
				if(iccWarrenty[i].equals(""))
				{
					iccWarrenty[i]="";	
				}
				if(iccwarcover[i].equals("") )
				{
					iccwarcover[i]="";	
				}
				if(iccdeductive[i].equals("")  )
				{
					iccdeductive[i]="Y";	
				}
				
				argsI[h]=iccClause[i]+"~"+iccExclusion[i]+"~"+iccWarrenty[i]+"~"+iccwarcover[i]+"~"+iccdeductive[i];
				///argsI[h]=iccClause[i]+"~"+iccExclusion[i]+"~"+iccWarrenty[i];
				///System.out.println("args[h]"+h);
				///System.out.println("args[h]"+h);
				h++;
			}
			///System.out.println("args[h]sdsadsafsdfsdf"+h);
			
			argsI[15] = status;
			argsI[16] = startDate;
			argsI[17] = Integer.toString(amend_id).trim();
			argsI[18] = commodityType;
			argsI[19] = branch;
			argsI[20] = fragile;
			argsI[21] = rag;
			argsI[22] = "";
			argsI[23] = exclusionId;
			argsI[24] = warrantyId;
			argsI[25] = remarks;
			argsI[26] = coreApplicationCode;
			argsI[27] = commrate;
			argsI[28]=COVERAGE_REFERAL;
			//runner.multipleUpdation(sql, argsI);
			System.out.println("?Tst");
         runner.insertion(sql, argsI);
			process = "YES";
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()2 : " + e);
			System.out.println("exception in commdity master" + e.toString());
			e.printStackTrace();
		}
		return process;
	}
/////////////////////     INSERT CONVEY DETAILS  /////////////////////////////////////////////////////////////////
	
	public String insertConveyDetails(String mode, String branch) 
	{
		String process = "";
		String argss[] = new String[1];
		String res = "";
		int amend_id = 0;
		int sno = 0;
		int subBusinessId = 0;
		try {
		if (ConveyId.length() > 0 && !ConveyId.equals("")) {
			subBusinessId = Integer.parseInt(ConveyId);
		}
		if (mode.equalsIgnoreCase("add")) {
			argss[0] = branch;
			sql = "select max(CONV_MEAN+1) from CONVEYAN_MASTER where branch_code=?";
			////System.out.println("sql:"+sql);
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					subBusinessId = Integer.parseInt(res);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		} else {
			argss = new String[2];
			argss[0] = ConveyId;
			argss[1] = branch;
			subBusinessId = Integer.parseInt(ConveyId);
			sql = "select max(nvl(AMEND_ID,'0'))+1 from CONVEYAN_MASTER where CONV_MEAN=? and branch_code=?";
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					amend_id = Integer.parseInt(res);
				////System.out.println("amend_id:"+amend_id);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		}		
		sql = "select max(SNO+1) from CONVEYAN_MASTER";	
		try {
			res = runner.singleSelection(sql);
			sno = Integer.parseInt(res);
			System.out.println("test sno:"+sno);
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()1 : " + e);
			e.printStackTrace();
		}
		if (subBusinessId == 0) {
			subBusinessId = 1;
		}
		String convtype="";
		
			sql = " INSERT INTO CONVEYAN_MASTER (SNO,CONV_MEAN,CONVCD , CONVDESC,CONV_RATE," +
					" MODE_TRANSPORT_ID,EFFECTIVE_DATE,AMEND_ID,BRANCH_CODE,CORE_APP_ID ," +
					" REMARKS,STATUS) VALUES (?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?)";
			ConveyName = ConveyName.replaceAll("'", "''");
			if (ConveyRemarks != null && ConveyRemarks.length() > 0)
				ConveyRemarks = ConveyRemarks.replaceAll("'", "''");
		  System.out.println("sql:"+sql);
			///System.out.println("commodityName:"+commodityName);
			
			String[] argsConvey = new String[12];			
			argsConvey[0] = Integer.toString(sno).trim();			
			argsConvey[1] = Integer.toString(subBusinessId).trim();	
			
			if(ConveyModeOfTransport.equals("1"))
			{
				convtype="S";	
			}
			if(ConveyModeOfTransport.equals("2"))
			{
				convtype="A";	
			}
			if(ConveyModeOfTransport.equals("3"))
			{
				convtype="L";	
			}
			if(ConveyModeOfTransport.equals("4"))
			{
				convtype="P";	
			}
			if(ConveyModeOfTransport.equals("5"))
			{
				convtype="C";	
			}
			argsConvey[2] = convtype;
			argsConvey[3] = ConveyName;
			argsConvey[4] = ConveyRate;		
			argsConvey[5] = ConveyModeOfTransport;
			argsConvey[6] = ConveyMEffectiveDate;
			argsConvey[7] = Integer.toString(amend_id).trim();
			argsConvey[8] = branch;
			argsConvey[9] = ConveyCoreAppCode;
			argsConvey[10] = ConveyRemarks;
			argsConvey[11] = ConveyStatus;
			//runner.multipleUpdation(sql, argsI);
			System.out.println("?Tst");
			System.out.println("argsConvey:"+argsConvey);
			
			runner.insertion(sql, argsConvey);
			process = "YES";
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()2 : " + e);
			System.out.println("exception in convey master" + e.toString());
			e.printStackTrace();
		}
		return process;
	}
	
	public String insertPolicyProc(String policyno,String ckey,String cmsvalue,String product)
	{
		String status="";
		System.out.println("policyno"+policyno);
		String process="";
		int g=0;
		logger.info("getCertificateNo - Enter || policyno: "+policyno);		
		CallableStatement cstmt = null;
		Connection con = null;
		try {
		con = DBConnection.getInstance().getDBConnection();
		if(product.equals("3"))
		{
		System.out.println("Interation error ONE OFF");
		cstmt = con.prepareCall("call INTEGRATION_TYPE(?)");	
		cstmt.setString(1, policyno);
		cstmt.execute();
		status=runner.singleSelection("SELECT nvl(INTEGRATION_STATUS,'F')  from position_master where POLICY_NO ='"+policyno+"'");
			
		process=status;
		
		}
		else if(product.equals("11"))
		{
			System.out.println("Interation error OPEN COVER");
			cstmt = con.prepareCall("call INTEGRATION_TYPE(?)");			
			cstmt.setString(1, policyno);
			cstmt.execute();
			status=runner.singleSelection("SELECT nvl(INTEGRATION_STATUS,'F')  from position_master where POLICY_NO ='"+policyno+"'");
			System.out.println("Interation error*************************** ONE OFF");		
			process=status;
		}
		else if(product.equals("R"))
		{
			System.out.println("Partially ");
			cstmt = con.prepareCall("call INTEGRATION_TYPE(?)");			
			cstmt.setString(1, policyno);
			cstmt.execute();
			status=runner.singleSelection("SELECT INTEGRATION_STATUS from position_master where POLICY_NO ='"+policyno+"'");
			System.out.println("Partially INtegration ************************** ONE OFF");		
			process=status;
		}else{
			System.out.println("partially Submit");
			cstmt = con.prepareCall("call PARTIAL_INTEGRATION_SUBMIT(?,?,?,?)");			
			cstmt.setString(1, policyno);
			cstmt.setString(2, ckey);
			cstmt.setString(3, cmsvalue);
			cstmt.setString(4, product);
			cstmt.execute();
			process="Yes";
			System.out.println("policyno:"+policyno);
			System.out.println("ckey:"+ckey);
			System.out.println("cmsvalue:"+cmsvalue);
			System.out.println("product:"+product);
		}		
		
		System.out.println("success fully procedure executed");
		}catch(Exception e)
		{
			e.printStackTrace();
			process="No";
			System.out.println("procedure executed failed");
		}
		finally {
	         
	          try {
	                if (cstmt != null)
		             cstmt.close();
	                 } catch (Exception e) { e.printStackTrace();} 
	           try {		
	               if (con != null && !con.isClosed())
	              con.close();
		                 } catch (Exception e) { e.printStackTrace(); }
	              }
		logger.info("policynoificateNo - Exit || policyno: "+policyno);
		return 	process;
		
		
	}
	
	public String insertOpenPolicyProc(String policyno,String ckey,String cmsvalue,String product)
	{
		String status="";
		System.out.println("policyno"+policyno);
		String process="";
		int g=0;
		logger.info("getCertificateNo - Enter || policyno: "+policyno);		
		CallableStatement cstmt = null;
		Connection con = null;
		try {
		con = DBConnection.getInstance().getDBConnection();
		if(product.equals("R"))
		{
			System.out.println("Partially ");
			cstmt = con.prepareCall("call INTEGRATION_OPENCOVER(?)");			
			cstmt.setString(1, policyno);
			cstmt.execute();
			status=runner.singleSelection("SELECT INTEGRATION_ERROR from open_cover_position_master where OPEN_COVER_NO ='"+policyno+"'");
			System.out.println("Partially INtegration **************************"+policyno);		
			process=status;
		}else{
			System.out.println("Error");
			cstmt = con.prepareCall("call INTEGRATION_OPENCOVER(?)");			
			cstmt.setString(1, policyno);
			cstmt.execute();
			status=runner.singleSelection("SELECT INTEGRATION_ERROR from open_cover_position_master where OPEN_COVER_NO ='"+policyno+"'");
			System.out.println("Error INtegration **************************"+policyno);		
			process=status;
		}		
		
		System.out.println("success fully procedure executed");
		}catch(Exception e)
		{
			e.printStackTrace();
			process="No";
			System.out.println("procedure executed failed");
		}
		finally {
	         
	          try {
	                if (cstmt != null)
		             cstmt.close();
	                 } catch (Exception e) { e.printStackTrace();} 
	           try {		
	               if (con != null && !con.isClosed())
	              con.close();
		                 } catch (Exception e) { e.printStackTrace(); }
	              }
		logger.info("policynoificateNo - Exit || policyno: "+policyno);
		return 	process;
		
		
	}
	
	public String insertCommodExcessDetails(String mode, String branch) 
	{
		String process = "";
		String argss[] = new String[1];
		String res = "";
		int amend_id = 0;
		int sno = 0;
		int subBusinessId = 0;
		try {
			System.out.println("mode:**************"+mode);
			System.out.println("CommodExcessId:*************"+CommodExcessId);
			if(mode.equals("edit"))
			{
				String args7[]=new  String[2];
				args7[0] =CommoExcessRate;			
				args7[1] =CommodExcessId;				
				sql=" UPDATE COMMODITY_EXCESS_PREMIUM SET RATE=? where SNO=?";
				runner.multipleInsertion(sql, args7);
				System.out.println("sql:"+sql);
				System.out.println("CommoExcessRate:----------->"+CommoExcessRate);
				System.out.println("CommodExcessId:-------------->"+CommodExcessId);
				process = "YES";	
			}
			else{
			
		if (CommodExcessId.length() > 0 && !CommodExcessId.equals("")) {
			subBusinessId = Integer.parseInt(CommodExcessId);
		}
		if (mode.equalsIgnoreCase("add")) {
			argss[0] = branch;
			sql = "select max(SNO+1) from COMMODITY_EXCESS_PREMIUM where branch_code=?";
			System.out.println("sql:"+sql);
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					subBusinessId = Integer.parseInt(res);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		} else {
			argss = new String[2];
			argss[0] = CommodExcessId;
			argss[1] = branch;
			System.out.println("CommodExcessId----->:"+CommodExcessId);
			subBusinessId =Integer.parseInt(CommodExcessId);
			System.out.println("subBusinessId:"+subBusinessId);
			sql = "select max(nvl(AMEND_ID,'0'))+1 from COMMODITY_EXCESS_PREMIUM where SNO=? and branch_code=?";
			try {
				res = runner.singleSelection(sql, argss);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					amend_id = Integer.parseInt(res);
				////System.out.println("amend_id:"+amend_id);
				
				
				if (subBusinessId == 0) {
					subBusinessId = 1;
				}

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
			
		}		
		
		if (subBusinessId == 0) {
			subBusinessId = 1;
		}
		                
		
		
			sql = " INSERT INTO COMMODITY_EXCESS_PREMIUM (SNO,START_SUM_INSURED,END_SUM_INSURED,DEDUCTIBLE," +
					" RATE,AMEND_ID,EFFECTIVE_DATE,BRANCH_CODE,STATUS) VALUES (?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?)";
			 
		      System.out.println("sql:"+sql);
			System.out.println("CommodExcessStatus:"+CommodExcessStatus);
			System.out.println("amend_id:"+amend_id);
			System.out.println("subBusinessId:"+subBusinessId);
			
			String[] argsCommodExcess = new String[9];			
			argsCommodExcess[0] = Integer.toString(subBusinessId).trim();			
			argsCommodExcess[1] =CommodExcessStartInsured.trim();				
			argsCommodExcess[2] = CommodExcessEndInsured.trim();
			argsCommodExcess[3] = CommodExcessDeductible;
			argsCommodExcess[4] = CommoExcessRate;
			argsCommodExcess[5] = Integer.toString(amend_id).trim();
			argsCommodExcess[6] = CommodExcessEffectiveDate;
			argsCommodExcess[7] = branch;		
			argsCommodExcess[8] = CommodExcessStatus;
			//runner.multipleUpdation(sql, argsI);
			System.out.println("?Tst");
			
			System.out.println("argsConvey:"+argsCommodExcess);
			
			///runner.multipleInsertion(sql, argsCommodExcess);
			process = "YES";
		}
		}catch (Exception e) {
			setError("codeErr:  insertSubDetails()2 : " + e);
			System.out.println("exception in Commodity Excess master" + e.toString());
			e.printStackTrace();
		}
		return process;
	}
	
	
	public boolean isNumeric(final String value){
		boolean result = false;
		try{
			Long.parseLong(value);
			result = true;
		}catch(Exception e){
			result = false;
		}
		return result;
	}

	public String insertPolicyData(String mode) {
		String args[] = new String[0];
		String process = "";
		String query = "";
		String sno = "";

		try {
			System.out.println("mode  value " + mode);
			if (mode.equalsIgnoreCase("add")) {
				args = new String[16];
				type_id = runner
						.singleSelection("select nvl(max(TYPE_ID+1),1) from POLICYNO_GENERATE");
				sno = runner
						.singleSelection("select nvl(max(SNO__+1),1) from POLICYNO_GENERATE");
				type_id = type_id == null ? "1" : type_id;
				sno = sno == null ? "" : sno;
				branch_code = branch_code.replaceAll("'", "");
				query = " insert into POLICYNO_GENERATE(TYPE_ID,TYPE_DESC,BRANCH_CODE,AGENCY_CODE,POLICY_PREFIX,"
						+ " START_NO,END_NO,CURRENT_NO,REMARKS,STATUS,LOGIN_ID, DESCRIPTION,PRODUCT_ID,AMEND_ID,"
						+ " EFFECTIVE_DATE,SNO__) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?)";
				args[0] = type_id;
				args[1] = type_desc;
				args[2] = branch_code;
				args[3] = agency_code;
				args[4] = policy_prefix;
				args[5] = start_no;
				args[6] = end_no;
				args[7] = current_no;
				args[8] = remarks2;
				args[9] = status2;
				args[10] = login_id;
				args[11] = description;
				args[12] = product_id1;
				args[13] = "0";
				args[14] = startDate2;
				args[15] = sno;

				runner.multipleInsertion(query, args);
			} else if (mode.equalsIgnoreCase("edit")) {

				query = " update POLICYNO_GENERATE set POLICY_PREFIX = ? , START_NO = ? ,END_NO = ? ,CURRENT_NO = ? , REMARKS =  ? ,STATUS = ? , "
						+ " EFFECTIVE_DATE = to_date(?,'dd-mm-yyyy') where BRANCH_CODE = ? and  AGENCY_CODE = ? and PRODUCT_ID = ? ";

				args = new String[10];
				args[0] = policy_prefix;
				args[1] = start_no;
				args[2] = end_no;
				args[3] = current_no;
				args[4] = remarks2;
				args[5] = status2;
				args[6] = startDate2;
				args[7] = branch_code;
				args[8] = agency_code;
				args[9] = product_id1;

				runner.multipleUpdation(query, args);
			}
			process = "YES";
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return process;
	}
	
	

	// Insert Country Master Details...
	public String insertCountryData(String mode, String belongingCid) {
		String process = "";
		String args[] = new String[0];
		String res = "";
		startDate = ConvertDate(startDate1);
		int amend_id = 0;
		int sno = 0;
		int subBusinessId = 0;
		if (countryid.length() > 0 && !countryid.equals(""))
			subBusinessId = Integer.parseInt(countryid);
		if (mode.equalsIgnoreCase("add")) {
			args = new String[1];
			args[0] = belongingCid;
			sql = "select max(COUNTRY_ID+1) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID=?";
			try {
				res = runner.singleSelection(sql, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					subBusinessId = Integer.parseInt(res);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		} else {
			try {
				args = new String[2];
				args[0] = countryid;
				args[1] = belongingCid;
				sql = "select max(AMEND_ID+1) from COUNTRY_MASTER_DETAIL where COUNTRY_ID=? and BELONGING_COUNTRY_ID=?";
				res = runner.singleSelection(sql, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					amend_id = Integer.parseInt(res);

			} catch (Exception e) {
				setError("codeErr:  insertSubDetails()1 : " + e);
				e.printStackTrace();
			}
		}

		try {
			args = new String[1];
			args[0] = belongingCid;
			sql = "select max(SNO__+1) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID=?";
			res = runner.singleSelection(sql, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				sno = Integer.parseInt(res);
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()1 : " + e);
			e.printStackTrace();
		}
		if (subBusinessId == 0) {
			subBusinessId = 1;
		}
		// String effective_date = ConvertDate(effectiveDate);
		try {
			sql = "insert into COUNTRY_MASTER_DETAIL (SNO__,COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,WAR_RATE,STARTING_PLACE,SP_WARRANTIES_CONDITIONS,ENDING_PLACE,EP_WARRANTIES_CONDITIONS,STATUS, RSACODE,NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE,REMARKS,BELONGING_COUNTRY_ID) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			countryname = countryname.replaceAll("'", "''");
			remarks1 = remarks1.replaceAll("'", "''");

			args = new String[16];
			args[0] = "" + sno;
			args[1] = "" + subBusinessId;
			args[2] = countryname;
			args[3] = countryshortname;
			args[4] = "" + Double.parseDouble(warrate);
			args[5] = startingplace;
			args[6] = spwarrantiesconditions;
			args[7] = endingplace;
			args[8] = epwarrantiesconditions;
			args[9] = status1;
			args[10] = "" + rsacode;
			args[11] = nationalityname;
			args[12] = "" + amend_id;
			args[13] = startDate;
			args[14] = remarks1;
			args[15] = belongingCid;

			runner.multipleInsertion(sql, args);
			process = "YES";
		} catch (Exception e) {
			setError("codeErr:  insertSubDetails()2 : " + e);
			System.out.println("exception in commdity master" + e.toString());
			e.printStackTrace();
		}
		return process;
	}

	public String isCountryNameAvail(String countryName) {
		String result = "",res = "";
		int countryCount = 0;
				
		try {
			sql = "select count(country_name) from COUNTRY_MASTER where lower(country_name) = lower('" + countryName + "')";
			res = runner.singleSelection(sql);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				countryCount = Integer.parseInt(res);
			if(countryCount>0)
				result = "yes";
		} catch (Exception e) {
			setError("codeErr:  isCountryNameAvail() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	public String isCoreAppCodeAvailable(String branchCode, String coreAppCode, String tableName, String fieldName, String condition ) {
		String result = "",res = "", branchCodeCond = "";
		int count = 0;
				
		try {
			
			if(!branchCode.equalsIgnoreCase(""))
				branchCodeCond = " and branch_code = " + branchCode; 
				
				sql = "select count(*) from " + tableName + " wrm where " + fieldName + " = '" + coreAppCode + "'" + condition + branchCodeCond;
			
			res = runner.singleSelection(sql);
			
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				count = Integer.parseInt(res);
			
			if(count>0)
				result = "yes";
		} catch (Exception e) {
			setError("codeErr:  isCoreAppCodeAvailable() : " + e);
			System.out.println("exception in isCoreAppCodeAvailable " + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	public String isMaterialDescAvailable(String branchCode, String matDesc) {
		String result = "",res = "";
		int count = 0;
				
		try {
			sql = "select count(material_desc) from material_type_master mm where branch_code = " + branchCode + 
			" and material_desc = '" + matDesc + "' and amend_id = (select max(amend_id) from material_type_master mtm " +
			"where branch_code = " + branchCode + " and mtm.material_id = mm.material_id)";
			res = runner.singleSelection(sql);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				count = Integer.parseInt(res);
			
			if(count>0)
				result = "yes";
		} catch (Exception e) {
			setError("codeErr:  isCountryNameAvail() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getCountryMasterDtls(String countryID) {
		
		String[][] countryDtls = new String[0][0];
				
		try {
			sql = "select COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,STATUS,RSACODE,NATIONALITY_NAME," +
					"to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),GEO_RATE,REMARKS from COUNTRY_MASTER where country_id = " + countryID;
				countryDtls = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeErr:  getCountryMasterDtls() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		return countryDtls;
	}

	public String[][] getWarRateDtls(String transid, String branchCode) {
		
		String[][] countryDtls = new String[0][0];
				
		try {
			sql = "select WAR_ID,WAR_DESC,WAR_RATE,to_char(EFFECTIVE_DATE,'dd-mm-yyyy'),CORE_APP_ID,STATUS,REMARKS from " +
					"war_rate_master where mode_transport_id = " + transid + " and branch_code = " + branchCode +  
				" and amend_id = (select max(amend_id) from war_rate_master where mode_transport_id = " + transid + " and branch_code = " + branchCode + ")";
				countryDtls = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeErr:  getCountryMasterDtls() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		return countryDtls;
	}

	public String[][] getmatTypeDtls(String matID, String branchCode) {
		
		String[][] matTypeDtls = new String[0][0];
		try {
			sql = "SELECT MATERIAL_ID,MATERIAL_DESC,MATERIAL_RATE,TO_CHAR(EFFECTIVE_DATE,'dd-mm-yyyy'),CORE_APP_ID,STATUS,REMARKS " +
				"FROM MATERIAL_TYPE_MASTER MM WHERE MATERIAL_ID = " + matID + " AND BRANCH_CODE = " + branchCode +
				" AND AMEND_ID = (SELECT MAX(AMEND_ID) FROM MATERIAL_TYPE_MASTER MTM WHERE BRANCH_CODE = " + branchCode + 
				" AND MTM.MATERIAL_ID = MM.MATERIAL_ID)";
			matTypeDtls = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeErr:  getmatTypeDtls() : " + e);
			System.out.println("exception in material type master" + e.toString());
			e.printStackTrace();
		}
		return matTypeDtls;
	}
	
	public String[][] getmatDesc(String branchCode) {
		
		String[][] matTypeDtls = new String[0][0];
		try {
			sql = "SELECT MATERIAL_ID, MATERIAL_DESC FROM MATERIAL_TYPE_MASTER MM WHERE BRANCH_CODE = " + branchCode + " AND AMEND_ID = " + 
			" (SELECT MAX(AMEND_ID) FROM MATERIAL_TYPE_MASTER MTM WHERE BRANCH_CODE = " + branchCode + " AND MTM.MATERIAL_ID = MM.MATERIAL_ID)" ;  
			matTypeDtls = runner.multipleSelection(sql);
		} catch (Exception e) {
			setError("codeErr:  getmatDesc() : " + e);
			System.out.println("exception in material desc " + e.toString());
			e.printStackTrace();
		}
		return matTypeDtls;
	}

	// Insert Country Master...
	public String insertCountryMaster() {
		String process = "";
		String args[] = new String[0];
		String res = "";
				
		try {
			
			if (countryid.length() <= 0 && countryid.equals("")) {
				sql = "select max(COUNTRY_ID) + 1 from COUNTRY_MASTER ";
				res = runner.singleSelection(sql);
				if (res.length() > 0 && !res.equalsIgnoreCase("null"))
					countryid = res;				
			}
			sql = "insert into COUNTRY_MASTER (SNO__,COUNTRY_ID,COUNTRY_NAME,COUNTRY_SHORT_NAME,STATUS,RSACODE," +
					"NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE,GEO_RATE,REMARKS)" +
					"values((select max(SNO__)+1 from COUNTRY_MASTER),?,?,?,?,?,?,(select nvl(max(amend_id)+1,0) from COUNTRY_MASTER where country_id = ?),to_date(?,'dd-mm-yyyy'),?,?)";

			remarks = remarks.replaceAll("'", "''");

			args = new String[10];
			args[0] = countryid;
			args[1] = countryname;
			args[2] = countryshortname;
			args[3] = status;
			args[4] = coreApplicationCode;
			args[5] = nationalityname;
			args[6] = countryid;
			args[7] = effective_date;
			args[8] = geoRate;
			args[9] = remarks;

			runner.multipleInsertion(sql, args);
			process = "success";
		} catch (Exception e) {
			setError("codeErr:  insertCountryMaster() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		return process;
	}

	public String insertWarRateDetails() {
		String process = "";
		String args[] = new String[0];
		
		try {
			
			if(mode.equalsIgnoreCase("add"))
			{
				sql = "select nvl(max(WAR_ID),0) + 1 " + "from war_rate_master where mode_transport_id = ? and branch_code = " + branch_code;
				warID = runner.singleSelection(sql); 
			}
			
			sql = "insert into WAR_RATE_MASTER (SNO,WAR_ID,WAR_DESC,WAR_RATE,MODE_TRANSPORT_ID,EFFECTIVE_DATE,AMEND_ID," +
					"BRANCH_CODE,CORE_APP_ID,STATUS,REMARKS) values((select max(SNO)+1 from WAR_RATE_MASTER),?,?,?,?,to_date(?,'dd-mm-yyyy')," +
					"nvl((select max(amend_id) + 1 from war_rate_master where mode_transport_id = ? and branch_code = ?),0),?,?,?,?)";

			remarks = remarks.replaceAll("'", "''");
			args = new String[11];
			args[0] = warID;
			args[1] = warDesc;
			args[2] = warrate;
			args[3] = modeOfTransID; 
			args[4] = effective_date;
			args[5] = modeOfTransID;
			args[6] = branch_code;
			args[7] = branch_code;
			args[8] = coreApplicationCode; 
			args[9] = status;
			args[10] = remarks;
			
			runner.multipleInsertion(sql, args);
			process = "success";
		} catch (Exception e) {
			setError("codeErr:  insertCountryMaster() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		
		return process;
	}
	
	public String insertMaterialTypeDetails(String mode) {
		String process = "";
		String args[] = new String[0];
		if(mode.equalsIgnoreCase("add"))
		{
			sql = "select nvl(max(MATERIAL_ID),0) + 1 " + "from MATERIAL_TYPE_MASTER where branch_code = " + branch_code;
			matID = runner.singleSelection(sql); 
		}
		try {
			sql = "insert into MATERIAL_TYPE_MASTER (SNO,MATERIAL_ID,MATERIAL_DESC,MATERIAL_RATE,EFFECTIVE_DATE,AMEND_ID," +
					"BRANCH_CODE,CORE_APP_ID,STATUS,REMARKS) values((select max(SNO)+1 from MATERIAL_TYPE_MASTER),?,?,?,to_date(?,'dd-mm-yyyy')," +
					"nvl((select max(amend_id) + 1 from MATERIAL_TYPE_MASTER where MATERIAL_ID = ? and branch_code = ?),0),?,?,?,?)";

			remarks = remarks.replaceAll("'", "''");
			args = new String[10];
			args[0] = matID ;
			args[1] = matDesc ;
			args[2] = matRate;
			args[3] = effective_date;
			args[4] = matID; 
			args[5] = branch_code;
			args[6] = branch_code;
			args[7] = coreApplicationCode;
			args[8] = status;
			args[9] = remarks;
			
			runner.multipleInsertion(sql, args);
			process = "success";
		} catch (Exception e) {
			setError("codeErr:  insertCountryMaster() : " + e);
			System.out.println("exception in country master" + e.toString());
			e.printStackTrace();
		}
		
		return process;
	}
	
	public String[][] getWarranties(String branch) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			sql = "select WARRANTY_ID,WARRANTY_DESCRIPTION from WARRANTY_MASTER where status='Y' and branch_code=? order by WARRANTY_ID";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}
	public String[][] getCoverageRef(String branch) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			sql = " select COVER_ID,COVER_NAME from cover_master where BRANCH_CODE=? order by COVER_ID";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}
	
	public String[][] getExclusion(String branch) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			sql = "select EXCLUSION_ID,EXCLUSION_DESCRIPTION from EXCLUSION_MASTER where status in ('Y','R') and branch_code=? order by EXCLUSION_ID";
			
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}
	public String[][] getClause(String branch,String clauseid,String clausevalue) {
		String[][] comDetails = new String[0][0];
		System.out.println("clausevalue"+clausevalue);
		String sql = "";
		String args[] = new String[2];
		String args1[]=new String[1];
		try {
			
			if(clausevalue.equalsIgnoreCase("C"))
			{
				args[0] = branch;
				args[1] = clauseid;
			sql = "select rsacode,clauses_description from CLAUSES_MASTER where  branch_code=?  and  EXTRA_COVER_ID=0 and COVER_ID=? ";
			comDetails = runner.multipleSelection(sql, args);
			}
			else if(clausevalue.equalsIgnoreCase("W"))
			{
				args1[0] = branch;
				sql = "select WARRANTY_ID,WARRANTY_DESCRIPTION from WARRANTY_MASTER where status='Y' and branch_code=? order by WARRANTY_ID";
				comDetails = runner.multipleSelection(sql, args1);	
			}
			else if(clausevalue.equalsIgnoreCase("E"))
			{
				args1[0] = branch;
				
				sql = "select EXCLUSION_ID,EXCLUSION_DESCRIPTION from EXCLUSION_MASTER where status in ('Y','R') and branch_code=? order by EXCLUSION_ID";
				comDetails = runner.multipleSelection(sql, args1);
			}
			else 
			{
				args[0] = branch;
				args[1] = clauseid;
				sql = "select rsacode,clauses_description from CLAUSES_MASTER where branch_code=? and COVER_ID=? and  EXTRA_COVER_ID<>0 order by COVER_ID";
				comDetails = runner.multipleSelection(sql, args);
			}
			System.out.println("hello"+sql+"clausevalue"+clausevalue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}


	public String[][] getCountryBaseFields(String countryId, String belogingCid) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = countryId;
			args[1] = belogingCid;
			args[2] = countryId;
			args[3] = belogingCid;
			sql = "select COUNTRY_ID,AMEND_ID from COUNTRY_MASTER_DETAIL where status in('Y','R') and country_id=? and BELONGING_COUNTRY_ID=? and amend_id=(select max(amend_id) from COUNTRY_MASTER_DETAIL where country_id=? and BELONGING_COUNTRY_ID=?)";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Error in getwarranty = " + e);
			e.printStackTrace();
		}
		return comDetails;
	}

	// need to work
	public String[][] getPolicyBaseFields(String sno) {
		String[][] comDetails = new String[0][0];
		String sql = "";
		sql = "select TYPE_ID,AMEND_ID from POLICYNO_GENERATE where status='Y' and Sno__='"
				+ sno + "'";

		try {
			comDetails = runner.multipleSelection(sql);
		} catch (Exception e) {
			System.out.println("Error in getwarranty = " + e);
		}
		return comDetails;
	}

	public String getExistedCuntryDate(String id, String amendid) {
		String date = "";
		String sql = "";
		String args[] = new String[1];
		String res = "";
		try {
			args[0] = id;
			// args[1] = amendid;
			// sql =
			// "select to_char(EFFECTIVE_DATE,'dd-mm-yyyy') from COUNTRY_MASTER where status='Y' and COUNTRY_ID=? and AMEND_ID=?";
			sql = "select to_char(EFFECTIVE_DATE,'dd-mm-yyyy') from COUNTRY_MASTER where status='Y' and COUNTRY_ID=?";
			res = runner.singleSelection(sql, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				date = res;
		} catch (Exception e) {
			System.out.println("Error in getwarranty = " + e);
			e.printStackTrace();
		}
		return date;
	}

	public String getExistedPolicyDate(String id, String amendid) {
		// Connection con = null;
		// con = DBConnection.getInstance().getDBConnection();
		String date = "";
		String[] args = new String[2];
		args[0] = id;
		args[1] = amendid;

		String sql = "select to_char(EFFECTIVE_DATE,'dd-mm-yyyy') from POLICYNO_GENERATE where status='Y' and TYPE_ID =  ? and AMEND_ID = ?";

		try {
			// Statement smt = con.createStatement();
			// ResultSet rs = smt.executeQuery(sql);
			// if (rs.next())
			// date = rs.getString(1);

			date = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String getExistedDate(String id, String amendid) {
		String date = "";
		String args[] = new String[2];
		String sql = "";
		String res = "";
		try {
			args[0] = id;
			args[1] = amendid;
			sql = "select to_char(EFFECTIVE_DATE,'dd-mm-yyyy') from COMMODITYMASTER where status='Y' and COMMODITY_ID=? and AMEND_ID=?";
			res = runner.singleSelection(sql, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				date = res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public void insertcountry123(String country, String shortname,
			String warRate, String startingplace, String spwarranty,
			String endingplace, String epwarranty, String status,
			String rsacode, String nationalname, String amendid,
			String effectivedate, String remarks) {
		String args[] = new String[0];
		String res = "";
		String query = "select max(SNO__) from COUNTRY_MASTER";
		int count = 0;
		try {
			res = runner.singleSelection(query);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[15];
			args[0] = "" + count;
			args[1] = "" + count;
			args[2] = country;
			args[3] = shortname;
			args[4] = warRate;
			args[5] = startingplace;
			args[6] = spwarranty;
			args[7] = endingplace;
			args[8] = epwarranty;
			args[9] = status;
			args[10] = rsacode;
			args[11] = nationalname;
			args[12] = amendid;
			args[13] = effectivedate;
			args[14] = remarks;

			sql = "insert into COUNTRY_MASTER(SNO__, COUNTRY_ID, COUNTRY_NAME, COUNTRY_SHORT_NAME,WAR_RATE,STARTING_PLACE,SP_WARRANTIES_CONDITIONS,ENDING_PLACE,EP_WARRANTIES_CONDITIONS , STATUS,RSACODE,NATIONALITY_NAME,AMEND_ID,EFFECTIVE_DATE,REMARKS)  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getcountries() {
		String[][] comDetails = new String[0][0];
		String sql = "select COUNTRY_ID,country_name from COUNTRY_MASTER where status='Y' order by country_name";
		try {
			comDetails = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comDetails;
	}

	/*
	 * public String[][] getPolicyGenerastion() { String[][] constant = new
	 * String[0][0]; String sql =
	 * "select cm.sno__,cm.TYPE_ID,cm.DESCRIPTION,cm.amend_id,cm.product_id,cm.agency_code from POLICYNO_GENERATE cm where cm.status in ('Y','R') and cm.amend_id||cm.TYPE_ID in (select max(amend_id)||TYPE_ID from POLICYNO_GENERATE where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') group by TYPE_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') group by cm.TYPE_ID,cm.DESCRIPTION,cm.amend_id,cm.sno__,cm.product_id,cm.agency_code order by cm.DESCRIPTION"
	 * ; try { constant = runner.multipleSelection(sql); } catch (Exception e) {
	 * e.printStackTrace(); } return constant; }
	 */

	public String[][] getPolicyGenerastion(String adminProducts,
			String branchCode) {
		// (String)session.getAttribute("AdminPid")
		String[][] constant = new String[0][0];
		// String sql =
		// "select cm.sno__,cm.TYPE_ID,cm.DESCRIPTION,cm.amend_id,cm.product_id,cm.agency_code from POLICYNO_GENERATE cm where cm.status in ('Y','R') and cm.amend_id||cm.TYPE_ID in (select max(amend_id)||TYPE_ID from POLICYNO_GENERATE where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') group by TYPE_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') group by cm.TYPE_ID,cm.DESCRIPTION,cm.amend_id,cm.sno__,cm.product_id,cm.agency_code order by cm.DESCRIPTION";
		String sql = " select distinct policy.agency_code,broker.COMPANY_NAME,product.PRODUCT_NAME,product.product_id from POLICYNO_GENERATE policy, "
				+ " BROKER_COMPANY_MASTER broker, product_master product where  policy.agency_code is not null and "
				+ " policy.agency_code = broker.agency_code and broker.BRANCH_CODE = "
				+ branchCode
				+ " and policy.BRANCH_CODE = "
				+ branchCode
				+ " and "
				+ " policy.product_id in ("
				+ adminProducts
				+ ") and product.BRANCH_CODE = "
				+ branchCode
				+ "  and policy.product_id = product.product_id";
		try {
			constant = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	public String[][] getBranches() {
		String[][] constant = new String[0][0];
		String sql = "select branch_code,BRANCH_NAME from branch_master where status='Y' order by BRANCH_NAME";
		try {
			constant = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return constant;
	}

	/*public String[][] getBranchesToAdd() {
		String[][] branches = new String[0][0];
		String sql = "select branch_code,BRANCH_NAME from branch_master where status='Y' and branch_code in " + 
			"(select distinct branch_code from branch_master where status='Y' minus " +
			"select distinct branch_code from material_type_master) order by BRANCH_NAME";
		try {
			branches = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branches;
	}*/
	
	public String[][] getBrokers(String branchCode) {
		String[][] brokers = new String[0][0];

		// String sql =
		// "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,bcm.AGENCY_CODE,pi.LOGIN_ID from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and APPLICATION_ID='2' order by COMPANY_NAME";
		String sql = "select bcm.CUSTOMER_ID,bcm.CONTACT_PERSON,bcm.COMPANY_NAME,pi.AGENCY_CODE from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code and  bcm.branch_code in("
				+ branchCode
				+ ") and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("
				+ branchCode + "))) order by lower(bcm.COMPANY_NAME)";

		try {
			brokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getProducts() {
		String[][] brokers = new String[0][0];
		String sql = "select distinct product_id,product_name from product_master where status='Y' order by product_name";
		try {
			brokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brokers;
	}

	public String[][] getProducts(String branchCode, String admPid) {
		String[][] brokers = new String[0][0];
		String sql = "select product_id,product_name from product_master where status='Y' and branch_code = "
				+ branchCode
				+ " and product_id in ("
				+ admPid
				+ ")  order by product_name";
		try {
			brokers = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brokers;
	}

	public String getAgencyCode(String login_id) {
		String date = "";
		String args[] = new String[1];
		String sql = "";
		try {
			args[0] = login_id;
			sql = "select a.agency_code from login_master a,BROKER_COMPANY_MASTER b where a.login_id=? and a.AGENCY_CODE=b.AGENCY_CODE";
			date = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public void updatesettlingNew(String country, String agent, String status) {
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = status;
			args[1] = country;
			args[2] = agent;
			sql = "update SETTLING_AGENT_MASTER set STATUS=? where COUNTRY_ID=? and SETTLING_AGENT_ID = ?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getsettlingAgentNew(String countryId) {
		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = countryId;
			sql = "select s.SETTLING_AGENT_ID,s.SETTLING_AGENT_NAME,s.status,c.city_name,s.country_id from SETTLING_AGENT_MASTER s, city_master c  where s.COUNTRY_ID=?  and s.city_id=c.city_id order by s.SETTLING_AGENT_NAME";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public void updateLCDetails(String opencoverid, String LCID,
			String lcdammount, String status) {
		String[][] getCoverId = new String[0][0];
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = lcdammount;
			args[1] = status;
			args[2] = opencoverid;
			args[3] = LCID;
			sql = "update OPEN_COVER_LC_MASTER set LC_AMOUNT=?,status=? where OPEN_COVER_NO=? and LC_ID=?";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBname(String bankid) {
		String args[] = new String[1];
		String sql = "";
		String check = "";
		try {
			args[0] = bankid;
			sql = "select bank_name from open_cover_bank_master where bank_id=? and status='Y'";
			check = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	public String getMaxEndNo() {
		// Connection con = null;
		// con = DBConnection.getInstance().getDBConnection();
		String date = "0";
		String sql = "select  MAX(END_NO)  from POLICYNO_GENERATE where  product_id='11' and status='Y'";
		try {
			// Statement smt = con.createStatement();
			// ResultSet rs = smt.executeQuery(sql);
			// if (rs.next())
			// date = rs.getString(1);
			date = runner.singleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public String validations() {
		String s = "";
		int n = 0;
		if (stat.equals("select")) {
			s = " * Please Select Status" + "<br>";
		}
		if ("".equals(quoteno) || "null".equals(quoteno)) {
			s = s + "* Please Select Quteno" + "<br>";
		}
		if (!("".equals(quoteno))) {
			try {
				n = (int) Integer.parseInt(quoteno);
			} catch (NumberFormatException e) {
				s = s + " * Please Enter Valid Quoteno";
			}
		}
		return s;
	}

	public int updat() {
		int in = 0;
		String[] args = new String[2];
		args[0] = stat;
		args[1] = quoteno;
		String str = "update position_master set status = ? where quote_no = ?";
		try {
			runner.multipleUpdation(str, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return in;
	}

	public String[][] getCommodityTypeId(String branch) {
		String commType[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try {
			args[0] = branch;
			qry = "select CATEGORY_DETAIL_ID,DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID=42 and STATUS='Y' and branch_code=?";
			commType = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commType;
	}
	public String[][] getConveyTypeId(String branch) {
		String commType[][] = new String[0][0];
		String qry = "";
		String args[] = new String[2];
		try {
			args[0] = branch;
			args[1] = branch;
			qry = "select  CONV_MEAN,CONVDESC from CONVEYAN_MASTER where AMEND_ID||CONV_MEAN "+
						 " in (select max(AMEND_ID)||CONV_MEAN from CONVEYAN_MASTER where branch_code = ? "+
						 " group by CONV_MEAN ) AND branch_code = ? order by CONVDESC";
			System.out.println("conveydetails:"+qry);
			commType = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commType;
	}
	
	public String[][] getCommodExcessId(String branch) {
		String commExcess[][] = new String[0][0];
		String qry = "";
		String args[] = new String[2];
		try {
			args[0] = branch;
			args[1] = branch;
			qry = " select SNO,START_SUM_INSURED||'-'||END_SUM_INSURED from COMMODITY_EXCESS_PREMIUM A WHERE "+ 
					"A.AMEND_ID||A.SNO in (SELECT MAX(B.AMEND_ID)||SNO FROM COMMODITY_EXCESS_PREMIUM B WHERE  B.BRANCH_CODE =? "+
					" GROUP BY B.SNO  ) AND BRANCH_CODE =? order by SNO ";
			System.out.println("CommodityExcess:"+qry);
			commExcess = runner.multipleSelection(qry, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commExcess;
	}

	// Rajesh world work start
	public String[][] getModeofTrans(String braCode) {
		String[][] getCoverId = new String[0][0];
		String sql = "select distinct MODE_TRANSPORT_ID,TRANSPORT_DESCRIPTION from MODE_OF_TRANSPORT where BRANCH_CODE in("
				+ braCode + ") order by TRANSPORT_DESCRIPTION";
		try {
			getCoverId = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCoverId;
	}

	public String[][] getModeofTransToAdd(String braCode) {
		String[][] getModeOfTrans = new String[0][0];
		String sql = "select MODE_TRANSPORT_ID, TRANSPORT_DESCRIPTION from MODE_OF_TRANSPORT where MODE_TRANSPORT_ID " +
		"in (select distinct MODE_TRANSPORT_ID from MODE_OF_TRANSPORT where BRANCH_CODE = " + braCode + 
		" minus select distinct MODE_TRANSPORT_ID from WAR_RATE_MASTER where BRANCH_CODE = " + braCode + ")";
		try {
			getModeOfTrans = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getModeOfTrans;
	}
	
	public String[][] getModeOfTransdesc(String transId, String braCode) {
		String[][] TransportId = new String[0][0];
		String sql = "select TRANSPORT_DESCRIPTION,status,RSACODE,DISPLAY_ORDER from MODE_OF_TRANSPORT where MODE_TRANSPORT_ID='"
				+ transId + "' and BRANCH_CODE in(" + braCode + ")";
		try {
			TransportId = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TransportId;
	}

	public void updateModeOfTransDesc(String transid, String desc,
			String status, String rsa, String dis, String braCode) {
		String sql = "";
		String args[] = new String[5];
		try {
			args[0] = desc;
			args[1] = status;
			args[2] = rsa;
			args[3] = dis;
			args[4] = transid;

			sql = "update MODE_OF_TRANSPORT set TRANSPORT_DESCRIPTION=?,status=?,RSACODE=?,DISPLAY_ORDER=? where MODE_TRANSPORT_ID=? and BRANCH_CODE in("
					+ braCode + ")";
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertModeOfTransDesc(String desc, String rsa, String dis,
			String braCode) {
		String query = "";
		int count = 0;
		int maxid=0;
		String args[] = new String[0];
		String res = "";
		braCode=braCode.replaceAll("'", "");
		try {
			query = "select max(SNO__) from MODE_OF_TRANSPORT where BRANCH_CODE ="+braCode+ "";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
			query = "select max(MODE_TRANSPORT_ID) from MODE_OF_TRANSPORT where BRANCH_CODE ="+braCode+ "";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				maxid = Integer.parseInt(res);
				maxid=maxid+1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[7];
			args[0] = "" + count;
			args[1] = "" + maxid;
			args[2] = desc;
			args[3] = "Y";
			args[4] = braCode;
			args[5] = rsa;
			args[6] = dis;

			sql = "insert into MODE_OF_TRANSPORT(SNO__, MODE_TRANSPORT_ID, TRANSPORT_DESCRIPTION,status,BRANCH_CODE,RSACODE,DISPLAY_ORDER) values(?,?,?,?,?,?,?)";
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getCoverId(String transId, String braCode) {

		String[] args = new String[2];
		args[0] = transId;
		args[1] = transId;
		String[][] getCoverId = new String[0][0];

		String sql = "select distinct cover_id, COVER_NAME,rowid from  COVER_MASTER  where mode_transport_id = ? and branch_code in ("
				+ braCode
				+ ") union select distinct EXTRA_COVER_ID,EXTRA_COVER_NAME,rowid from  EXTRA_COVER_MASTER where mode_transport_id = ? and branch_code in ("
				+ braCode + ")";
		try {
			getCoverId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCoverId;
	}

	public String[][] getCauseDesc(String coverid, String rowid, String braCode) {
		String[] args = new String[1];
		args[0] = rowid;
		String sql1 = "select rowid from Cover_MASTER where rowid = ?";
		String sql2 = "select rowid from EXTRA_COVER_MASTER  where rowid = ?";
		boolean availcover = false;
		boolean availex = false;
		String[][] getCoverId = new String[0][0];
		String sql = "";
		String res = "";
		try {
			try {
				res = runner.singleSelection(sql1, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availcover = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				res = runner.singleSelection(sql2, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availex = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (availcover) {
				args[0] = coverid;
				sql = "select clauses_id, clauses_description,status from CLAUSES_MASTER where COVER_ID = ? and extra_cover_id='0' and branch_code in ("
						+ braCode + ") order by clauses_id";
			} else if (availex) {
				args[0] = coverid;
				sql = "select clauses_id, clauses_description,status from CLAUSES_MASTER where EXTRA_COVER_ID =  ? and branch_code in("
						+ braCode + ") order by clauses_id";
			}
			getCoverId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCoverId;
	}
	public String[][] getCauseDescComm(String coverid, String rowid, String braCode) {
		String[] args = new String[1];
		args[0] = rowid;
		String sql1 = "select rowid from Cover_MASTER where rowid = ?";
		String sql2 = "select rowid from EXTRA_COVER_MASTER  where rowid = ?";
		boolean availcover = false;
		boolean availex = false;
		String[][] getCoverId = new String[0][0];
		String sql = "";
		String res = "";
		try {
			try {
				res = runner.singleSelection(sql1, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availcover = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				res = runner.singleSelection(sql2, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availex = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (availcover) {
				args[0] = coverid;
				sql = "select clauses_id, clauses_description,status from CLAUSES_MASTER where COVER_ID = ? and extra_cover_id='0' and branch_code in ("
						+ braCode + ") order by clauses_id";
			} else if (availex) {
				args[0] = coverid;
				sql = "select clauses_id, clauses_description,status from CLAUSES_MASTER where EXTRA_COVER_ID =  ? and branch_code in("
						+ braCode + ") order by clauses_id";
			}
			getCoverId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getCoverId;
	}

	public void updateCauseDesc(String coverid, String clauseId, String desc,
			String status, String rowid, String braCode) {
		String sql1 = "select rowid from Cover_MASTER where rowid=?";
		String sql2 = "select rowid from EXTRA_COVER_MASTER  where rowid=?";
		boolean availcover = false;
		boolean availex = false;
		String args[] = new String[1];
		args[0] = rowid;
		String[][] getCoverId = new String[0][0];
		String sql = "";
		String res = "";
		try {
			try {
				res = runner.singleSelection(sql1, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availcover = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				res = runner.singleSelection(sql2, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availex = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (availcover) {
				args = new String[4];
				args[0] = desc;
				args[1] = status;
				args[2] = coverid;
				args[3] = clauseId;
				sql = "update CLAUSES_MASTER set CLAUSES_DESCRIPTION=?,STATUS=? where COVER_ID=? and CLAUSES_ID=? and branch_code in("
						+ braCode + ")";
				runner.multipleUpdation(sql, args);
			} else if (availex) {
				args = new String[4];
				args[0] = desc;
				args[1] = status;
				args[2] = coverid;
				args[3] = clauseId;
				sql = "update CLAUSES_MASTER set CLAUSES_DESCRIPTION=?,STATUS=? where EXTRA_COVER_ID=? and CLAUSES_ID=? and branch_code in("
						+ braCode + ")";

				runner.multipleUpdation(sql, args);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertCauseDesc(String coverid, String desc, String transid,
			String rowid, String braCode) {
		String args[] = new String[0];
		String res = "";
		args = new String[1];
		args[0] = rowid;
		String sql1 = "select rowid from Cover_MASTER where rowid=?";
		String sql2 = "select rowid from EXTRA_COVER_MASTER  where rowid=?";
		boolean availcover = false;
		boolean availex = false;
		String[][] getCoverId = new String[0][0];
		String query = "select max(SNO__) from CLAUSES_MASTER where branch_code in("
				+ braCode + ")";
		int count = 0;
		String cover = "";
		String extra = "";
		try {
			try {
				res = runner.singleSelection(sql1, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availcover = true;
					cover = coverid;
					extra = "0";
				}
			} catch (Exception e) {
				System.out.println("royal test exception is here1..");
				e.printStackTrace();
			}
			try {
				res = runner.singleSelection(sql2, args);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					availex = true;
					cover = "0";
					extra = coverid;
				}
			} catch (Exception e) {
				System.out.println("royal test exception is here2..");
				e.printStackTrace();
			}

			try {
				res = runner.singleSelection(query);
				if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
					count = Integer.parseInt(res);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			count = count + 1;
			String sql = "";

			try {
				if (braCode.indexOf("'") != -1)
					braCode = braCode.replaceAll("'", "");
				args = new String[8];
				args[0] = "" + count;
				args[1] = cover;
				args[2] = "" + count;
				args[3] = desc;
				args[4] = extra;
				args[5] = "" + count;
				args[6] = "Y";
				args[7] = braCode;
				sql = "insert into CLAUSES_MASTER(SNO__, COVER_ID, CLAUSES_ID, CLAUSES_DESCRIPTION,EXTRA_COVER_ID,RSACODE,status,BRANCH_CODE) values(?,?,?,?,?,?,?,?)";
				runner.multipleInsertion(sql, args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Error in updateCauseDesc = " + e);
			e.printStackTrace();
		}
	}

	/*public void updateCoverDesc(String coverid, String desc, String covername,
			String status, String rsacode, String braCode) {

		String args[] = new String[5];
		String[][] getCoverId = new String[0][0];
		String sql = "";
		try {
			sql = "update COVER_MASTER set COVER_NAME=?, DESCRIPTION=?, status=?,rsacode=? where COVER_ID=? and branch_code in("
					+ braCode + ")";
			args[0] = covername;
			args[1] = desc;
			args[2] = status;
			args[3] = rsacode;
			args[4] = coverid;

			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			System.out.println("Error in updateCoverDesc = " + e);
			e.printStackTrace();
		}
	}*/

	public void insertCoverDesc(String coverId,String covername, String trans, String desc,
			String rsacode, String braCode, String coverRate, String status,String remarks,String effectiveDate) {

		String res = "", amend_id = "";
		String args[] = new String[0];
		int count = 0, amendID = 0;
		
		try {
			sql = "select max(SNO__) from COVER_MASTER where branch_code in("
					+ braCode + ")";
			res = runner.singleSelection(sql);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
			
			if(!coverId.equalsIgnoreCase("")) {
				sql = "select max(nvl(amend_id,0)) + 1 from cover_master where cover_id = " + coverId;
				amend_id = runner.singleSelection(sql);
				if (amend_id.length() > 0 && !amend_id.equalsIgnoreCase("null")) {
					amendID = Integer.parseInt(amend_id);
				}	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		System.out.println("before braCode " + braCode);
		braCode = braCode.replaceAll("'", "");
		System.out.println("after braCode " + braCode);
		
		if(coverId.equalsIgnoreCase(""))
			coverId = count + "";
		
		try {
			sql = "insert into COVER_MASTER(SNO__,COVER_ID,COVER_NAME,MODE_TRANSPORT_ID,DESCRIPTION,STATUS,RSACODE,branch_code,COVER_RATE,amend_id,REMARKS,EFFECTIVE_DATE,DISPLAY_ORDER) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?)";
			args = new String[13];
			args[0] = "" + count;
			args[1] = coverId;
			args[2] = covername;
			args[3] = trans;
			args[4] = desc;
			args[5] = status;
			args[6] = "" + rsacode;
			args[7] = "" + braCode;
			args[8] = coverRate;
			args[9] = "" + amendID;
			args[10] = remarks;
			args[11] = effectiveDate;
			args[12] = coverId;
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getCoverDesc(String coverid, String braCode) {
		dataCollection data = new com.maan.services.util.dataCollection();
		String[][] getCoverId = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try {
			args[0] = coverid;
			sql = "select COVER_NAME, DESCRIPTION,status,rsacode,cover_rate,REMARKS,to_char(EFFECTIVE_DATE,'dd-mm-yyyy') from " +
				"COVER_MASTER cm where COVER_ID=? and branch_code = " + braCode + " and amend_id = (select max(amend_id) from " +
				"COVER_MASTER cmm where branch_code = " + braCode + " and cm.cover_id = cmm.cover_id )";
			getCoverId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Error in getCoverDesc = " + e);
			e.printStackTrace();
		}
		return getCoverId;
	}

	public String[][] getExtraCoverTrans(String transId, String braCode) {

		String[][] getCoverId = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = transId;
			sql = "select distinct cover_id, COVER_NAME,nvl(rsacode,''),rowid from  COVER_MASTER cm where " +
				"mode_transport_id=? and branch_code in(" + braCode + ") and amend_id = (select max(amend_id) from COVER_MASTER " +
				"cmm where mode_transport_id=" + transId + " and branch_code = " + braCode + " and cm.cover_id = cmm.cover_id )";
			getCoverId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			out.println("Error in getExtraCoverTrans = " + e);
			e.printStackTrace();
		}
		return getCoverId;
	}

	public String[][] getextracover(String extracoverId, String braCode) {

		String[][] extracover = new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try {
			args[0] = extracoverId;
			sql = "select EXTRA_COVER_ID,EXTRA_COVER_NAME,REMARKS,status  from EXTRA_COVER_MASTER where MODE_TRANSPORT_ID=? and branch_code in("
					+ braCode + ")";
			extracover = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return extracover;
	}

	public void updateextracoverDesc(String extracoverId, String transID,
			String detailName, String remarks, String status, String braCode) {

		String sql = "";
		String args[] = new String[5];
		try {
			sql = "update EXTRA_COVER_MASTER set EXTRA_COVER_NAME=?,REMARKS=?,STATUS=? where  EXTRA_COVER_ID=? and MODE_TRANSPORT_ID=? and branch_code in("
					+ braCode + ")";
			args[0] = detailName;
			args[1] = remarks;
			args[2] = status;
			args[3] = extracoverId;
			args[4] = transID;

			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertextracoverDesc(String transportId, String extraname,
			String remarks, String rsacode, String braCode) {
braCode=braCode.replaceAll("'","");
		String query = "";
		int count = 0;
		String args[] = new String[0];
		String res = "";
		try {
			query = "select max(SNO__) from EXTRA_COVER_MASTER where branch_code="+ braCode + "";
			res = runner.singleSelection(query);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		count = count + 1;

		String query1 = "";
		int count1 = 0;
		try {
			query1 = "select max(EXTRA_COVER_ID) from EXTRA_COVER_MASTER where branch_code="+braCode+"";
			res = runner.singleSelection(query1);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count1 = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count1 = count1 + 1;

		String sql = "";
		try {
			sql = "insert into EXTRA_COVER_MASTER(SNO__,EXTRA_COVER_ID,EXTRA_COVER_NAME,MODE_TRANSPORT_ID,REMARKS,STATUS,RSACODE,branch_code) values(?,?,?,?,?,?,?,?)";
			args = new String[8];
			args[0] = "" + count;
			args[1] = "" + count1;
			args[2] = extraname;
			args[3] = transportId;
			args[4] = remarks;
			args[5] = "Y";
			args[6] = rsacode;
			args[7] = braCode;

			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getModeOfTrans(String braCode) {

		String[][] TransportId = new String[0][0];
		String sql = "";
		try {
			sql = "select MODE_TRANSPORT_ID,TRANSPORT_DESCRIPTION from MODE_OF_TRANSPORT where branch_code ="
					+ braCode + " order by TRANSPORT_DESCRIPTION";
			System.out.println("sql:"+sql);
			TransportId = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TransportId;
	}

	public String[][] getExclusionId(String braCode) {

		String[][] ExclusionId = new String[0][0];
		String sql = "";
		try {
			sql = "select EXCLUSION_ID,EXCLUSION_DESCRIPTION from EXCLUSION_MASTER where status='Y' and branch_code in("
					+ braCode + ")";
			ExclusionId = runner.multipleSelection(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExclusionId;
	}

	public String[][] getExclusiondesc(String exId, String braCode) {

		String[][] ExclusionId = new String[0][0];
		String args[] = new String[1];
		String sql = "select EXCLUSION_DESCRIPTION,status,RSACODE from EXCLUSION_MASTER where EXCLUSION_ID=? and branch_code in("
				+ braCode + ")";
		try {
			args[0] = exId;
			ExclusionId = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ExclusionId;
	}

	public boolean updateExclusionDesc(String exclusionid, String desc,
			String status, String rsa, String braCode) {
		boolean f = false;

		String sql = "";
		String args[] = new String[4];
		try {
			sql = "update EXCLUSION_MASTER set EXCLUSION_DESCRIPTION=?,status=?,RSACODE=? where EXCLUSION_ID=? and branch_code in("
					+ braCode + ")";
			args[0] = desc;
			args[1] = status;
			args[2] = rsa;
			args[3] = exclusionid;
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			f = true;
			e.printStackTrace();
			return f;
		}
		return f;
	}

	public boolean insertExclusionDesc(String desc, String rsa, String braCode) // Aug1
	{
		boolean f = false;
		String query = "";
		String res = "";
		String sql = "";
		int count = 0;
		if (braCode.indexOf("'") != -1)
			braCode = braCode.replaceAll("'", "");
		String args[] = new String[6];
		try {
			query = "select nvl(max(SNO__),0) from EXCLUSION_MASTER where branch_code =?";// ("+braCode+")";
			String args1[] = { braCode };
			res = runner.singleSelection(query, args1);
			if (res.length() > 0 && !res.equalsIgnoreCase("null"))
				count = Integer.parseInt(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		try {
			sql = "insert into EXCLUSION_MASTER(SNO__, EXCLUSION_ID, EXCLUSION_DESCRIPTION,status,branch_code,RSACODE)  values(?,?,?,?,?,?)";
			args[0] = "" + count;
			args[1] = "" + count;
			args[2] = desc;
			args[3] = "Y";

			args[4] = braCode;
			args[5] = rsa;

			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
			f = true;
			return f;
		}
		return f;
	}

	public String[][] getsettlingAgent(String countryId, String cityId,
			String cid) {

		String[][] city = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			args[0] = countryId;
		  //args[1] = cityId;
		    args[1] = cid;
		  //sql = "select SETTLING_AGENT_ID,SETTLING_AGENT_NAME,status,COUNTRY_ID,CITY_ID from SETTLING_AGENT_MASTER where COUNTRY_ID=? and CITY_ID=? and BELONGING_COUNTRY_ID=? order by SETTLING_AGENT_NAME";
		    sql = "select SETTLING_AGENT_ID,SETTLING_AGENT_NAME,status,COUNTRY_ID,CITY_ID from SETTLING_AGENT_MASTER where COUNTRY_ID=? and BELONGING_COUNTRY_ID=? order by SETTLING_AGENT_NAME";
		    System.out.println("==========>"+sql);
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String[][] getsettlingAgentDet(String countryId, String cityId,
			String agentId, String cid) {

		String[][] city = new String[0][0];
		String sql = "";
		String res = "";
		String args[] = new String[3];
		try {
			args[0] = countryId;
			
			args[1] = agentId;
			args[2] = cid;
			sql = "select SETTLING_AGENT_ID,SETTLING_AGENT_NAME,SHORT_NAME,ADDRESS1,ADDRESS2,ADDRESS3,ZIP_CODE,TELEPHONE_NO,FAX_NO,MOBILE_NO,EMAIL,REMARKS,STATUS,RSACODE from SETTLING_AGENT_MASTER where COUNTRY_ID=? and  SETTLING_AGENT_ID=? and BELONGING_COUNTRY_ID=? and STATUS='Y'";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			System.out.println("Error in getsettlingAgentDet = " + e);
			e.printStackTrace();
		}
		return city;
	}

	public void updatesettlingAgent(String country, String city,
			String agentname, String name, String shortname, String Address1,
			String Address2, String Address3, String zipcode, String telephone,
			String faxno, String mobile, String email, String remarks,
			String status, String rsacode, String cid) {

		String sql = "";
		String res = "";
		String args[] = new String[0];
		try {
			sql = "update SETTLING_AGENT_MASTER set SETTLING_AGENT_NAME=?,SHORT_NAME=?,ADDRESS1=?,ADDRESS2=?,ADDRESS3=?,ZIP_CODE=?,TELEPHONE_NO=?,FAX_NO=?,MOBILE_NO=?,EMAIL=?,REMARKS=?,STATUS=?,RSACODE =? where  COUNTRY_ID=? and CITY_ID=? and SETTLING_AGENT_ID=? and BELONGING_COUNTRY_ID=?";

			args = new String[17];
			args[0] = name;
			args[1] = shortname;
			args[2] = Address1;
			args[3] = Address2;
			args[4] = Address3;
			args[5] = zipcode;
			args[6] = telephone;
			args[7] = faxno;
			args[8] = mobile;
			args[9] = email;
			args[10] = remarks;
			args[11] = status;
			args[12] = rsacode;
			args[13] = country;
			args[14] = "0";
			args[15] = agentname;
			args[16] = cid;

			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertsettlingAgent(String country, String city, String name,
			String shortname, String Address1, String Address2,
			String Address3, String zipcode, String telephone, String faxno,
			String mobile, String email, String remarks, String rsacode,
			String cid, String adminBra) {

		String query = "";
		int count = 0;
		String res = "";
		String args[] = new String[0];
		try {
			args = new String[1];
			args[0] = cid;
			query = "select max(SNO__) from SETTLING_AGENT_MASTER where BELONGING_COUNTRY_ID =?";
			res = runner.singleSelection(query, args);
			if (res.length() > 0 && !res.equalsIgnoreCase("null")) {
				count = Integer.parseInt(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		count = count + 1;
		String sql = "";
		try {
			args = new String[19];
			sql = "insert into SETTLING_AGENT_MASTER(SNO__, SETTLING_AGENT_ID, SETTLING_AGENT_NAME, COUNTRY_ID,CITY_ID,SHORT_NAME,ADDRESS1,ADDRESS2,ADDRESS3 , ZIP_CODE,TELEPHONE_NO,FAX_NO,MOBILE_NO,EMAIL,REMARKS,STATUS,RSACODE,BELONGING_COUNTRY_ID,BRANCH_CODE) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			args[0] = "" + count;
			args[1] = "" + count;
			args[2] = name;
			args[3] = country;
			args[4] = city;
			args[5] = shortname;
			args[6] = Address1;
			args[7] = Address2;
			args[8] = Address3;
			args[9] = zipcode;
			args[10] = telephone;
			args[11] = faxno;
			args[12] = mobile;
			args[13] = email;
			args[14] = remarks;
			args[15] = "Y";
			args[16] = rsacode;
			args[17] = cid;
			args[18] = adminBra;
			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String[][] getsettlingAgentNew(String countryId, String cid) {

		String args[] = new String[2];
		String[][] city = new String[0][0];
		String sql = "";
		try {
			args[0] = countryId;
			args[1] = cid;
			sql = "select s.SETTLING_AGENT_ID,s.SETTLING_AGENT_NAME,s.status,c.city_name,s.country_id from SETTLING_AGENT_MASTER s, city_master c where s.COUNTRY_ID=? and s.city_id=c.city_id and BELONGING_COUNTRY_ID=? order by s.SETTLING_AGENT_NAME";
			city = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return city;
	}

	public String getAdminCountryId(String loginPerson) {
		String sql = "";
		String admCountry = "";
		String args[] = new String[1];
		try {
			args[0] = loginPerson;
			sql = "select country_id from login_master where login_id=? and status='Y'";
			admCountry = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admCountry;
	}

	public void commodityRateValidation(final String coverName,
			final String coverId, final String branch) {
		String building = "";
		int in = 0;
		char ch = '.';
		String args[] = { coverId, branch };
		String coverDesc = runner
				.singleSelection(
						"select description from cover_master where cover_id=? and branch_code=? and status='Y'",
						args);
		/*if (null == coverName || coverName.equals("")) {
			setError("Enter valid " + coverDesc + " Rate");
		}
		building = coverName;
		in = building.indexOf(ch);
		System.out.println("icc_a_sea................." + coverName);
		if ((!coverName.equalsIgnoreCase(""))
				&& (!StringUtil.isNumber(coverName))) {
			setError(coverDesc + " Rate Should be Numeric");
		} else if ((in > 3 || in == -1) && coverName.length() > 3) {
			setError("Precision accepts in " + coverDesc
					+ " upto 3 digits only");
		}*/
	}

	public String insertSaleTerm(String saleTermName, String saleTermValue,
			String rsaCode, String remarks, String status, String branchCode) {
		String flag = "NO";
		try {
			System.out.print("------------>from insertSaleTerm");
			String sno = runner
					.singleSelection("select max(sno__)+1 from sale_term_master");
			String args[] = { branchCode };
			String saleTermId = runner
					.singleSelection(
							"select max(sale_term_id)+1 from sale_term_master where BRANCH_CODE=?",
							args);
			String displayOrder = saleTermId;
			String arg[] = { sno, saleTermId, saleTermName, saleTermValue,
					rsaCode, remarks, status, displayOrder, branchCode };
			System.out.println(arg[0] + arg[1] + arg[2] + arg[3]);
			System.out.print("------------>from insertSaleTerm after");
			String q = "insert into SALE_TERM_MASTER(SNO__,SALE_TERM_ID,SALE_TERM_NAME,SALE_TERM_VALUE,RSACODE,REMARKS,STATUS,DISPLAY_ORDER,BRANCH_CODE) values(?,?,?,?,?,?,?,?,?)";
			runner.multipleInsertion(q, arg);
			flag = "YES";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String updateSaleDetails(String saleTermName, String saleTermValue,
			String rsaCode, String remarks, String status, String saleTermId,
			String branchCode) {
		String flag = "NO";
		String arg[] = { saleTermName, saleTermValue, rsaCode, remarks, status,
				saleTermId, branchCode };
		String q = "update sale_term_master set SALE_TERM_NAME=?,SALE_TERM_VALUE=?,RSACODE=?,REMARKS=?,STATUS=? where SALE_TERM_ID=? and BRANCH_CODE=?";

		try {
			runner.multipleUpdation(q, arg);
			flag = "YES";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public String[][] getSaleDetails(String saleTermId, String branchCode) {
		String saleDetails[][] = new String[0][0];
		String arg[] = { saleTermId, branchCode };
		String q = "select stm.SALE_TERM_NAME,stm.SALE_TERM_VALUE,stm.RSACODE,stm.REMARKS,stm.STATUS from SALE_TERM_MASTER stm where stm.SALE_TERM_ID=? and stm.BRANCH_CODE=?";
		try {
			saleDetails = runner.multipleSelection(q, arg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleDetails;
	}

	public void validateSaleDetail(String branchCode) {

		// saleTermValue=saleTermValue.trim();
		if (saleTermValue.length() > 7) {
			setError("Sale Term Value should not exceeded to 7 digits");
		}

		for (int i = 0; i < saleTermValue.length(); i++) {

			char ch = saleTermValue.charAt(i);
			System.out.println("validate saledetais----saleTermValue---->" + ch
					+ "-->" + saleTermValue.length() + "----->"
					+ saleTermValue.charAt(0));
			System.out.println(ch >= '0');
			System.out.println(ch <= '9');
			System.out.println(ch != '.');
			System.out.println(!((ch >= '0') && (ch <= '9')) || (ch != '.'));
			if (!Character.isDigit(ch)) {
				if (ch != '.') {
					setError("Sale Term Value must be number");
					break;
				} else {
					continue;
				}

			}

		}
		if(coreApplicationCode.trim().equals("") || coreApplicationCode.trim().equals("0") )

		{
				setError("Enter Core Application Code ");
				
		}

	}

	public String[][] getToleranceDropDown(final String branch) {
		String[][] result = new String[0][0];
		try {

			result = runner
					.multipleSelection("select tolerance_id,tolerance_name from tolerance_master where BRANCH_CODE = '"
							+ branch + "'");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getToleranceDetails(final String toleranceId) {
		String[][] result = new String[0][0];
		try {
			result = runner
					.multipleSelection("select TOLERANCE_ID,TOLERANCE_NAME,TOLERANCE_VALUE,RSACODE,REMARKS,STATUS from tolerance_master where TOLERANCE_ID = "
							+ toleranceId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getMaxToleranceId() {
		String id = "";
		try {
			id = runner
					.singleSelection("select nvl(max(TOLERANCE_ID)+1,1) from tolerance_master");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}

	private String toleranceId = "";
	private String toleranceName = "";
	private String toleranceValue = "";

	public String getToleranceId() {
		return toleranceId;
	}

	public void setToleranceId(String toleranceId) {
		this.toleranceId = toleranceId;
	}

	public String getToleranceName() {
		return toleranceName;
	}

	public void setToleranceName(String toleranceName) {
		this.toleranceName = toleranceName;
	}

	public String getToleranceValue() {
		return toleranceValue;
	}

	public void setToleranceValue(String toleranceValue) {
		this.toleranceValue = toleranceValue;
	}

	public String validateToleranceDetails() {
		StringBuffer error = new StringBuffer();
		if ("".equalsIgnoreCase(toleranceId)
				|| "null".equalsIgnoreCase(toleranceId)
				|| "select".equalsIgnoreCase(toleranceId)) {
			error.append("* Error Tolerance Id<br>");
		}
		if ("".equalsIgnoreCase(toleranceName)
				|| "null".equalsIgnoreCase(toleranceName)
				|| "select".equalsIgnoreCase(toleranceName)) {
			error.append("* Please Enter Valid Tolerance Name<br>");
		}
		if ("".equalsIgnoreCase(toleranceValue)
				|| "null".equalsIgnoreCase(toleranceValue)
				|| "select".equalsIgnoreCase(toleranceValue)) {
			error.append("* Please enter valid Tolerance Value<br>");
		} else {
			try {
				Double.parseDouble(toleranceValue);
			} catch (Exception ex) {
				error.append("* Please enter valid Tolerance Value<br>");
			}
		}
		if ("".equalsIgnoreCase(rsacode) || "null".equalsIgnoreCase(rsacode)
				|| "select".equalsIgnoreCase(rsacode)) {
			error.append("* Please Enter a valid Core Application Code<br>");
		}
		if ("".equalsIgnoreCase(status) || "null".equalsIgnoreCase(status)
				|| "select".equalsIgnoreCase(status)) {
			error.append("* Please Enter status<br>");
		}
		return error.toString();
	}

	public void insertOrUpdateToleranceDetails(String mode) {
		String query = "";
		String[] args = null;
		System.out.println("Value of mode in insert or update " + mode);
		try {
			if ("new".equalsIgnoreCase(mode)) {
				query = "insert into TOLERANCE_MASTER(SNO__,TOLERANCE_ID,TOLERANCE_NAME,TOLERANCE_VALUE,RSACODE,REMARKS,STATUS,BRANCH_CODE,DISPLAY_ORDER) values(?,?,?,?,?,?,?,?,?) ";
				args = new String[9];
				args[0] = toleranceId;
				args[1] = toleranceId;
				args[2] = toleranceName;
				args[3] = toleranceValue;
				args[4] = rsacode;
				args[5] = remarks;
				args[6] = status;
				args[7] = branch_code;
				args[8] = toleranceId;
				runner.multipleInsertion(query, args);
			} else if ("edit".equalsIgnoreCase(mode)) {
				query = "update TOLERANCE_MASTER set TOLERANCE_NAME = ? , TOLERANCE_VALUE = ? , RSACODE = ? , REMARKS = ?, STATUS = ? where TOLERANCE_ID = ?";
				args = new String[6];
				args[0] = toleranceName;
				args[1] = toleranceValue;
				args[2] = rsacode;
				args[3] = remarks;
				args[4] = status;
				args[5] = toleranceId;
				runner.multipleUpdation(query, args);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String[][] getVesselMasterDropDown(final String branch) {
		String[][] result = new String[0][0];
		try {
			result = runner.multipleSelection("select VESSEL_ID,VESSEL_NAME from VESSEL_MASTER order by VESSEL_NAME");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String[][] getVesselDetails(final String vesselId) {
		String[][] result = new String[0][0];
		try {
			result = runner
					.multipleSelection("select VESSEL_ID,VESSEL_NAME,RSACODE,VESSEL_CLASS,MANUFACTURE_YEAR,REMARKS,STATUS from VESSEL_MASTER where vessel_id =  "
							+ vesselId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public String getMaxVesselId() {
		String id = "";
		try {
			id = runner
					.singleSelection("select nvl(max(vessel_id)+1,1) from VESSEL_MASTER");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return id;
	}

	private String vesselId = "";
	private String vesselName = "";
	private String vesselClass = "";
	private int manufactureYear = 0;
	
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
	public String getVesselClass() {
		return vesselClass;
	}

	public void setVesselClass(String vesselClass) {
		this.vesselClass = vesselClass;
	}

	public int getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	
	public String validateVesselDetails() {
		StringBuffer error = new StringBuffer();
		if ("".equalsIgnoreCase(vesselId) || "null".equalsIgnoreCase(vesselId)
				|| "select".equalsIgnoreCase(vesselId)) {
			error.append("* Error Vessel Id<br>");
		}
		if ("".equalsIgnoreCase(vesselName)
				|| "null".equalsIgnoreCase(vesselName)
				|| "select".equalsIgnoreCase(vesselName)) {
			error.append("* Please enter the vessel name<br>");
		}
		if ("".equalsIgnoreCase(rsacode) || "null".equalsIgnoreCase(rsacode)
				|| "select".equalsIgnoreCase(rsacode)) {
			error.append("* Please enter the  Core Application Code<br>");
		}
		if ("".equalsIgnoreCase(status) || "null".equalsIgnoreCase(status)
				|| "select".equalsIgnoreCase(status)) {
			error.append("* Please select the status<br>");
		}
		if (remarks.length() > 100) {
			remarks = remarks.substring(0, 100);
		}
		return error.toString();
	}

	public String insertOrUpdateVesselDetails(String mode) {
		String query = "", result = "";
		String[] args = null;
		try {
			if ("new".equalsIgnoreCase(mode)) {
				query = "insert into VESSEL_MASTER(SNO__,VESSEL_ID,VESSEL_NAME,RSACODE,VESSEL_CLASS,MANUFACTURE_YEAR,REMARKS,STATUS,DISPLAY_ORDER) values (?,?,?,?,?,?,?,?,?)";
				args = new String[9];
				args[0] = vesselId;
				args[1] = vesselId;
				args[2] = vesselName;
				args[3] = rsacode;
				args[4] = vesselClass;
				args[5] = String.valueOf(manufactureYear);
				args[6] = remarks;
				args[7] = status;
				args[8] = vesselId;
				runner.multipleInsertion(query, args);
				result = "Vessel Details inserted Succesfully.";
			} else if ("edit".equalsIgnoreCase(mode)) {
				query = "update VESSEL_MASTER set VESSEL_NAME = ?, RSACODE = ?, VESSEL_CLASS = ?, MANUFACTURE_YEAR = ?, REMARKS = ?,STATUS = ?,DISPLAY_ORDER = ? ,SNO__ = ? where VESSEL_ID = ?";
				//query = "update VESSEL_MASTER set VESSEL_NAME = ?, RSACODE = ?, VESSEL_CLASS = ?, MANUFACTURE_YEAR = ?, REMARKS = ?,STATUS = ?, DISPLAY_ORDER = ? ,SNO__ = ? where VESSEL_ID = ? and BRANCH_CODE = ?";
				args = new String[9];
				args[0] = vesselName;
				args[1] = rsacode;
				args[2] = vesselClass;
				args[3] = String.valueOf(manufactureYear);
				args[4] = remarks;
				args[5] = status;
				args[6] = vesselId;
				args[7] = vesselId;
				args[8] = vesselId;
				runner.multipleUpdation(query, args);
				result = "Vessel Details updated Succesfully.";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	//     GET THE POLICY DETAILS
	
	
	public String[][] getComDetails(String comId, String branch) {
		String[][] comDetails = new String[0][0];
		String args[] = new String[4];
		String sql = "";
		try {
			args[0] = comId;
			args[1] = comId;
			args[2] = branch;
			args[3] = branch;
			sql = " SELECT COMMODITY_ID, COMMODITY_NAME,COMMODITY_TYPE_ID,COMMODITY_RATE," +
					"ICC_A_SEA,ICC_B_SEA,ICC_C_SEA," +
					"ICC_A_FROZEN_MEAT_SEA,ICC_A_FROZEN_FOOD_SEA,ICC_C_FROZEN_MEAT_SEA," +
					"ICC_C_FROZEN_FOOD_SEA,ICC_AIR_CARGO_AIR,ALL_RISKS_LAND_TRANSIT_LAND," +
					"LAND_TRANSIT_LAND,ALL_RISKS_PARCEL_POST_AIR,ALL_RISKS_SEA_AND_AIR," +
					"COMMODITY_EXCESS_PREMIUM,EXCLUSION_ID,WARRANTY_ID,REMARKS,RSACODE," +
					"TO_CHAR(EFFECTIVE_DATE,'DD-MM-YYYY'),STATUS,AMEND_ID,COVERAGE_REFERAL" +
					" FROM COMMODITYMASTER WHERE commodity_id = ? " +
					"AND AMEND_ID = (select max(AMEND_ID) from COMMODITYMASTER where commodity_id = ? AND branch_code = ? ) AND branch_code = ?";
			comDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return comDetails;
	}
	public String[][] getProductDetails(String startdate,String enddate,String product,String branch)
	{
		String[][] policydetails=new String[0][0];		
		try{
	String args[]=new String[4];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=product;
	args[3]=branch;
	String selpolicy="SELECT NVL(INTEGRATION_STATUS,'F') INTEGRATION_STATUS,COUNT(*) FROM POSITION_MASTER PM,LOGIN_MASTER LM,BROKER_COMPANY_MASTER BCM WHERE TRUNC(PM.INCEPTION_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(PM.INCEPTION_DATE)<=TO_DATE(?,'dd-mm-yyyy') AND PM.LOGIN_ID=LM.LOGIN_ID AND LM.OA_CODE=BCM.AGENCY_CODE AND PM.PRODUCT_ID=? AND BCM.BRANCH_CODE=? GROUP BY NVL(INTEGRATION_STATUS,'F') ORDER BY INTEGRATION_STATUS DESC";
	policydetails=runner.multipleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policydetails;
	}
	public String[][] getOpenCoverPolicyDetails(String startdate,String enddate,String product,String branch)
	{
		String[][] policydetails=new String[0][0];		
		try{
	String args[]=new String[3];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=branch;
	String selpolicy="select integration_error,count(*) from ( select case when ckey is not null and integration_error is not null and integration_error = 'P' then 'P' when ckey is not null and integration_error is not null and integration_error = 'Y' then 'Y' when ckey is null and integration_error is not null then 'F' when ckey is not null and integration_error is not null and integration_error not in ('Y','P') then 'P' when ckey is null and integration_error is null then 'F' else integration_error end integration_error FROM open_cover_position_master ocpm, open_cover_master ocm where OCPM.PROPOSAL_NO = OCM.PROPOSAL_no and ocm.amend_id = ( select max(amend_id) from open_cover_master where proposal_no=ocm.proposal_no ) and TRUNC(OCM.EFFECTIVE_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(OCM.EFFECTIVE_DATE)<=TO_DATE(?,'dd-mm-yyyy') and OCM.BRANCH_CODE=? and ocpm.status='P' and ocpm.OPEN_COVER_NO is not null) group by integration_error";
	policydetails=runner.multipleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policydetails;
	}
	public String getPolicyCnt(String startdate,String enddate,String product,String branch,String status)
	{
	System.out.println ("status:"+status);
		String policycnt="";
		String where="";
		
		try{
	String args[]=new String[5];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=product;
	args[3]=branch;
	args[4]=status;	
	String selpolicy="";
	if(status.equals("F"))
	{
		where="OR P.INTEGRATION_STATUS IS NULL";	
	}
	

	 selpolicy=" select ceil(count(*)/15) "+
		" from position_master P INNER JOIN LOGIN_MASTER L using(LOGIN_ID) "+
		" INNER JOIN PERSONAL_INFO I USING(CUSTOMER_ID) "+
		" INNER JOIN BROKER_COMPANY_MASTER B ON L.OA_CODE=B.AGENCY_CODE where "+
		" to_char(P.INCEPTION_DATE,'dd-mm-yyyy')>=? "+
		" and to_char(P.INCEPTION_DATE,'dd-mm-yyyy')<=? "+
		" and P.PRODUCT_ID=?  AND B.BRANCH_CODE=?   AND (P.INTEGRATION_STATUS=? "+where+" ) ";

	 policycnt=runner.singleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policycnt;

		
	}
	public String getOpenPolicyCnt(String startdate,String enddate,String product,String branch,String status)
	{
	System.out.println ("status:"+status);
		String policycnt="";
		String where="";
		
		try{
	String args[]=new String[4];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=branch;
	args[3]=status;	
	String selpolicy="";
	
	selpolicy="SELECT CEIL (COUNT ( * ) / 15) FROM ( SELECT OCPM.OPEN_COVER_NO,CASE WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'P' THEN 'P' WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'Y' THEN 'Y' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NOT NULL THEN 'F' WHEN CKEY IS NOT NULL  AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR NOT IN ('Y','P') THEN 'P' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NULL  THEN 'F'  ELSE INTEGRATION_ERROR  END INTEGRATION_ERROR FROM OPEN_COVER_POSITION_MASTER OCPM, OPEN_COVER_MASTER OCM WHERE OCPM.PROPOSAL_NO = OCM.PROPOSAL_NO AND OCM.AMEND_ID = ( SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=OCM.PROPOSAL_NO ) AND TRUNC(OCM.EFFECTIVE_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(OCM.EFFECTIVE_DATE)<=TO_DATE(?,'dd-mm-yyyy') AND OCM.BRANCH_CODE=? AND OCPM.STATUS='P' AND OCPM.OPEN_COVER_NO IS NOT NULL ORDER BY TRUNC(OCM.EFFECTIVE_DATE) DESC) RPT WHERE INTEGRATION_ERROR = ?";

	policycnt=runner.singleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policycnt;

		
	}
	
	public String[][] getPolicy(String startdate,String enddate,String product,String branch,String status,int end,String cnt)
	{
		String[][] policydetails=new String[0][0];	
		int endval=end;
		int strow=0;
		int endrow=0;
		System.out.println("cnt:"+cnt);
		if(cnt.equals("1"))
				{
			strow=1;
			endrow=15;
			
		}
		else {
			int c=Integer.parseInt(cnt);			
			endrow=c*15;
			strow=(endrow-15)+1;
			
		}
		
		try{
	String args[]=new String[7];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=product;
	args[3]=branch;
	args[4]=status;
	args[5]=new Integer(endrow).toString();
	args[6]=new Integer(strow).toString();
	System.out.println("strow"+strow);
	System.out.println("endrow"+endrow);
	String selpolicy="";
	selpolicy=   "SELECT * FROM (SELECT A.*, ROWNUM RNUM FROM (SELECT PM.POLICY_NO,PM.QUOTE_NO,TO_CHAR(PM.INCEPTION_DATE,'dd-mm-yyyy'),(SELECT FIRST_NAME FROM PERSONAL_INFO WHERE CUSTOMER_ID=PM.CUSTOMER_ID ) FIRST_NAME,NVL(PM.INTEGRATION_ERROR,NVL(PM.CKEY,'-')),ROWNUM FROM POSITION_MASTER PM,LOGIN_MASTER LM,BROKER_COMPANY_MASTER BCM WHERE TRUNC(PM.INCEPTION_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(PM.INCEPTION_DATE)<=TO_DATE(?,'dd-mm-yyyy') AND PM.LOGIN_ID=LM.LOGIN_ID AND LM.OA_CODE=BCM.AGENCY_CODE AND PM.PRODUCT_ID=? AND BCM.BRANCH_CODE=? AND NVL(INTEGRATION_STATUS,'F')=? ORDER BY TRUNC(PM.INCEPTION_DATE) DESC ) A WHERE ROWNUM <=? ) WHERE RNUM >=?";

	 policydetails=runner.multipleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policydetails;
	}
	
	public String[][] getOpenPolicy(String startdate,String enddate,String product,String branch,String status,int end,String cnt)
	{
		String[][] policydetails=new String[0][0];	
		int endval=end;
		int strow=0;
		int endrow=0;
		System.out.println("cnt:"+cnt);
		if(cnt.equals("1"))
				{
			strow=1;
			endrow=15;
			
		}
		else {
			int c=Integer.parseInt(cnt);			
			endrow=c*15;
			strow=(endrow-15)+1;
			
		}
		
		try{
	String args[]=new String[6];
	args[0]=startdate;
	args[1]=enddate;
	args[2]=branch;
	args[3]=status;
	args[4]=new Integer(endrow).toString();
	args[5]=new Integer(strow).toString();
	System.out.println("strow"+strow);
	System.out.println("endrow"+endrow);
	String selpolicy="";
	//selpolicy=   "SELECT * FROM (SELECT R2.OPEN_COVER_NO,R2.INCEPTION_DATE,R2.EXPIRY_DATE,R2.EFFECTIVE_DATE,R2.INTEGRATION_ERROR,R2.RNM, ROWNUM RNUM FROM (SELECT R1.*,ROWNUM RNM FROM (SELECT OCPM.OPEN_COVER_NO,OCPM.INCEPTION_DATE,OCPM.EXPIRY_DATE,OCM.EFFECTIVE_DATE, CASE WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'P' THEN 'P' WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'Y' THEN 'Y' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NOT NULL THEN 'F' WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR NOT IN ('Y','P') THEN 'P' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NULL THEN 'F' ELSE INTEGRATION_ERROR END INTEGRATION_ERROR FROM OPEN_COVER_POSITION_MASTER OCPM, OPEN_COVER_MASTER OCM WHERE OCPM.PROPOSAL_NO = OCM.PROPOSAL_NO AND OCM.AMEND_ID = (SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=OCM.PROPOSAL_NO ) AND TRUNC(OCM.EFFECTIVE_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(OCM.EFFECTIVE_DATE)<=TO_DATE(?,'dd-mm-yyyy') AND OCM.BRANCH_CODE=? AND OCPM.STATUS='P' AND OCPM.OPEN_COVER_NO IS NOT NULL ORDER BY TRUNC(OCM.EFFECTIVE_DATE) DESC) R1 WHERE INTEGRATION_ERROR = ?) R2 WHERE R2.RNM <= ? AND R2.RNM >= ?)";
	
	selpolicy=   "SELECT * FROM (SELECT R2.OPEN_COVER_NO,R2.INCEPTION_DATE,R2.EXPIRY_DATE,R2.ckey,R2.INTEGRATION_ERROR,R2.RNM, ROWNUM RNUM FROM (SELECT R1.*,ROWNUM RNM FROM (SELECT OCPM.OPEN_COVER_NO,OCPM.INCEPTION_DATE,OCPM.EXPIRY_DATE,OCPM.CKEY,OCPM.INTEGRATION_ERROR,CASE WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'P' THEN 'P' WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR = 'Y' THEN 'Y' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NOT NULL THEN 'F' WHEN CKEY IS NOT NULL AND INTEGRATION_ERROR IS NOT NULL AND INTEGRATION_ERROR NOT IN ('Y','P') THEN 'P' WHEN CKEY IS NULL AND INTEGRATION_ERROR IS NULL THEN 'F' ELSE INTEGRATION_ERROR END INTEGRATION_STATUS FROM OPEN_COVER_POSITION_MASTER OCPM, OPEN_COVER_MASTER OCM WHERE OCPM.PROPOSAL_NO = OCM.PROPOSAL_NO AND OCM.AMEND_ID = (SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=OCM.PROPOSAL_NO ) AND TRUNC(OCM.EFFECTIVE_DATE)>=TO_DATE(?,'dd-mm-yyyy') AND TRUNC(OCM.EFFECTIVE_DATE)<=TO_DATE(?,'dd-mm-yyyy') AND OCM.BRANCH_CODE=? AND OCPM.STATUS='P' AND OCPM.OPEN_COVER_NO IS NOT NULL ORDER BY TRUNC(OCM.EFFECTIVE_DATE) DESC) R1 WHERE INTEGRATION_STATUS = ?) R2 WHERE R2.RNM <= ? AND R2.RNM >= ?)";

	 policydetails=runner.multipleSelection(selpolicy, args);

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		return policydetails;
	}
	
	
	

} // Class