package com.maan.claim;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class ClaimDao extends MyJdbcTemplate {
	private static final Logger logger = LogUtil.getLogger(ClaimDao.class);
	@SuppressWarnings("unchecked")
	public void getclaimDetails(ClaimBean bean, String policyNo) {
		try {
			logger.info("Method to getclaimDetails()");
			String query = getQuery("GET_CLAIM_APP");
			logger.info("Query=> " + query);
			Object obj[] = {policyNo};
			Map<String, Object> rs1 = this.mytemplate.queryForMap(query,obj);
			String appId = String.valueOf(rs1.get("APPLICATION_NO"));
			String branchCode = String.valueOf(rs1.get("BRANCH_CODE"));
			bean.setInceptionDate(String.valueOf(rs1.get("INCEPTION_DATE")));
			String query2 = getQuery("GET_CLAIM_SCHEDULE");
			logger.info("Query => " + query2);
			Object obj1[] = { appId, branchCode };
			Map<String, Object> rs2 = this.mytemplate.queryForMap(query2,obj1);
			bean.setPolicyNo(String.valueOf(rs2.get("POLICYNO")));
			bean.setStartDate(String.valueOf(rs2.get("POLICYSTARTDATE")));
			String assuredName = (String
					.valueOf((rs2.get("CUSTOMERFIRSTNAME") == null ? "" : (rs2
							.get("CUSTOMERFIRSTNAME")))
							+ " "
							+ (rs2.get("CUSTOMERLASTNAME") == null ? "" : (rs2
									.get("CUSTOMERLASTNAME")))));
			bean.setAssuredName(assuredName);
			
			String address = String
					.valueOf((rs2.get("CUSTOMERADDRESSONE") == null || rs2
							.get("CUSTOMERADDRESSONE") == "") ? "" : (rs2
							.get("CUSTOMERADDRESSONE") + ", "))
					+ ""
					+ ((rs2.get("CUSTOMERADDRESSTWO") == null || rs2
							.get("CUSTOMERADDRESSTWO") == "") ? "" : (""
							+ rs2.get("CUSTOMERADDRESSTWO") + ", "))
					+ ""
					+ String.valueOf(rs2.get("POBOX"))== null ? " " : ("P.O Box No: " +String.valueOf(rs2
							.get("POBOX")))
					+ ""
					+ (rs2.get("CUSTOMEREMIRATE") == null ? "" : (", " + rs2
							.get("CUSTOMEREMIRATE")))
					+ ""
					+ (rs2.get("CUSTOMERCOUNTRY") == null ? "" : (", " + rs2
							.get("CUSTOMERCOUNTRY")));
			bean.setAddress(address);
			String voyaFrom = String
					.valueOf((rs2.get("WAREHOUSEYN") == null ? "" : (rs2
							.get("WAREHOUSEYN") + ", "))
							+ (rs2.get("VOYAGE_FROM_CITY") == null ? "" : (rs2
									.get("VOYAGE_FROM_CITY") + ", "))
							+ ""
							+ (rs2.get("VOYAGE_ORGIN_COUNTRY") == null ? ""
									: (rs2.get("VOYAGE_ORGIN_COUNTRY"))));
			
			bean.setVoyageFrom(voyaFrom);
			String voyaTo = String.valueOf((rs2.get("ANYPORTYN") == null ? ""
					: (rs2.get("ANYPORTYN") + ", "))
					+ (rs2.get("DESITINATION") == null ? "" : (rs2
							.get("DESITINATION") + ", "))
					+ ""
					+ (rs2.get("TO_DEST_COUNTRY") == null ? "" : (rs2
							.get("TO_DEST_COUNTRY"))));
			bean.setVoyageTo(voyaTo);
			String saleTerm = String
					.valueOf((rs2.get("SHORT_NAME") == null ? "" : (rs2
							.get("SHORT_NAME")))
							+ " "
							+ (rs2.get("TOTAL_SUM_INSURED") == null ? "" : (rs2
									.get("TOTAL_SUM_INSURED")))
							+ ""
							+ (rs2.get("SALETERMVALUE") == null ? "" : (rs2
									.get("SALETERMVALUE")))
							+ " ; "
							+ (rs2.get("SALETERMNAME") == null ? "" : (rs2
									.get("SALETERMNAME"))));
			bean.setSaleTerm(saleTerm);
			bean.setModeOfTransport(String.valueOf((rs2.get("TRANSITFROM")==null?"":(rs2.get("TRANSITFROM")))));
			bean.setCoverName(String.valueOf((rs2.get("COVERNAME")==null?"":(rs2.get("COVERNAME")))));
			bean.setCurrencyName(String.valueOf((rs2.get("CURRENCYNAME")==null?"":(rs2.get("CURRENCYNAME")))));
			bean.setModeOfCarriage(String.valueOf((rs2.get("CONVEYAN_NAME")==null?"":(rs2.get("CONVEYAN_NAME")))));
			bean.setSubjectMatterInsured(String.valueOf((rs2.get("COMMODITYNAME")==null?"":(rs2.get("COMMODITYNAME")))));
			bean.setSubjMatter(String.valueOf((rs2.get("SUBJ_MATTER")==null?"":(rs2.get("SUBJ_MATTER")))));
			bean.setSupplierName(String.valueOf((rs2.get("SUPLIER_NAME")==null?"":(rs2.get("SUPLIER_NAME")))));
			bean.setExchangeRate(String.valueOf((rs2.get("EXCHANGERATE")==null?"":(rs2.get("EXCHANGERATE")))));
			bean.setInvoiceNo(String.valueOf((rs2.get("INVOICE_NO")==null?"":(rs2.get("INVOICE_NO")))));
			bean.setLcNumber(String.valueOf((rs2.get("LCNUMBER")==null?"":(rs2.get("LCNUMBER")))));
			bean.setLcDate(String.valueOf((rs2.get("LCDATE")==null?"":(rs2.get("LCDATE")))));
			bean.setAwbBillNumber(String.valueOf((rs2.get("BILL_NUMBER")==null?"":(rs2.get("BILL_NUMBER")))));
			String sumInsured = String
					.valueOf((rs2.get("BASIS_VAL") == null ? "" : (rs2
							.get("BASIS_VAL")))
							+ " "
							+ (rs2.get("CURRENCY_ACCROYNM") == null ? "" : (rs2
									.get("CURRENCY_ACCROYNM")))
							+ " "
							+ (rs2.get("SUM_INSURED") == null ? "" : (rs2
									.get("SUM_INSURED"))));
			bean.setSumInsured(sumInsured);
			bean.setCommodityExcess(String.valueOf((rs2.get("COMMODITY_EXCESS")==null?"":(rs2.get("COMMODITY_EXCESS")))+" for each and every loss or occurrence."));
			String query3=getQuery("GET_BELONGING_BRANCH");
			Object[] obj2={branchCode};
			Map<String,Object>list=this.mytemplate.queryForMap(query3,obj2);
			String belongingBranch=String.valueOf(list.get("BELONGING_BRANCH"));
			getConditions(appId,belongingBranch,bean);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}

	public Integer getCountPolicyNo(ClaimBean bean) {
		
		int count=0;
		try {
			logger.info("Method to getCountPolicyNo()");
			String query=getQuery("GET_COUNT_POLICYNO");
			logger.info("Query => "+query);
			Object[] obj={bean.getPolicyNo()};
			count=this.mytemplate.queryForObject(query,Integer.class,obj);
			
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
	@SuppressWarnings("unchecked")
	public void getConditions(String applicationNo,String branchCode,ClaimBean bean)
	 {
	 	
	 	@SuppressWarnings("unused")
		List<String> clauseList=new ArrayList<String>();
	 	List<String> warList=new ArrayList<String>();
	 	List<String> warrentyList=new ArrayList<String>();
	 	List<String> exclusionList=new ArrayList<String>();
	 	List<Map<String,Object>> result=null;
	 	List<Map<String,Object>> result2=null;
	 	List<Map<String,Object>> result3=null;
	 	List<Map<String,Object>> result4=null;
	 	
	 	try{
	 		logger.info("getConditions - Enter || applicationNo: "+applicationNo+" branchCode: "+branchCode);
	 		String sql=getQuery("GET_CLAIM_CONDITION");
		 	logger.info("Query==> " + sql);
	 		result=this.mytemplate.queryForList(sql, new String[]{"Clauses",applicationNo, branchCode});
	 		for (int i=0; i<result.size(); i++){
	 			clauseList.add(result.get(i).get("CODEDESC").toString());
	 		}
	 		bean.setClauseId(clauseList);
	 		result2=this.mytemplate.queryForList(sql, new String[]{"War",applicationNo, branchCode});
	 		for (int i=0; i<result2.size(); i++){
	 			clauseList.add (result2.get(i).get("CODEDESC").toString());
	 		}
	 		bean.setWarId(warList);
	 		result3=this.mytemplate.queryForList(sql, new String[]{"Warranty",applicationNo, branchCode});
	 		for (int i=0; i<result3.size(); i++){
	 			warrentyList.add(result3.get(i).get("CODEDESC").toString());
	 		}
	 		bean.setWarrentyId(warrentyList);
	 		result4=this.mytemplate.queryForList(sql, new String[]{"Exclusion",applicationNo, branchCode});
	 		for (int i=0; i<result4.size(); i++){
	 			exclusionList.add((result4.get(i).get("CODEDESC").toString()));
	 		}
	 		bean.setExclusionId(exclusionList);
	 		logger.info("getConditions - Exit || result");
	 	}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
	 	}
	 }

	public void saveLossDetails(ClaimBean bean) {
		
		try {
			logger.info("saveLossDetails - Enter");
			String query =getQuery("SAVE_CLAIM");
			logger.info("Query =>"+ query);
			String sql="SELECT COUNT(SNO) FROM CLAIM_INTIMATION";
			int count=this.mytemplate.queryForObject(sql,Integer.class);
			SimpleDateFormat format=new SimpleDateFormat("dd-MM-yy");
			Date lossDate=(Date)format.parse(bean.getLossDate());
			Object[] obj={bean.getPolicyNo(),lossDate,bean.getLossDescription(),"Pending"," ",count+1,bean.getAssuredName()};
			int insert=this.mytemplate.update(query,obj);
			System.out.println(insert);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClaimIntimation(ClaimBean bean) {
		
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try {
			logger.info("ClaimIntimation - Enter");
			String query =getQuery("GET_CLAIM_INTIMATION");
			logger.info("Query =>"+ query);
			Object[] obj={"Pending"};
			result=this.mytemplate.queryForList(query,obj);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getClaimPendingDetails(ClaimBean bean) {
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		try{
			logger.info("ClaimPendingDetails - Enter");
			String query=getQuery("GET_CLAIM_PENDING");
			logger.info("Query=>"+query);
			Object[] obj={bean.getRdate(),"Pending"};
			result=this.mytemplate.queryForList(query,obj);
		}catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void getclaimIntimationDetails(ClaimBean bean) {
		try {
			String policyNo=bean.getPolicyNo();
			getclaimDetails(bean,policyNo);
			logger.info("ClaimIntimationDetails - Enter");
			String query=getQuery("GET_CLAIM_EXTRA_DETAIL");
			logger.info("Query=>"+query);
			Object[] obj={bean.getPolicyNo(),"Pending"};
			Map<String,Object> list=this.mytemplate.queryForMap(query,obj);
			bean.setLossDate(String.valueOf(list.get("LOSS_DATE")));
			bean.setIntimationDate(String.valueOf(list.get("INTIMATION_DATE")));
			bean.setLossDescription(list.get("LOSS_DESCRIPTION")==null?"":list.get("LOSS_DESCRIPTION").toString());
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
	}

	public int updateClaim(ClaimBean bean) {
		int update=0;
		try {
			logger.info("updateClaim - Enter");
			String query=getQuery("UPDATE_CLAIM");
			logger.info("Query=>"+query);
			Object[] obj={bean.getRemarks(),"Completed",bean.getPolicyNo()};
			update=this.mytemplate.update(query,obj);
			System.out.println(update);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return update;
	}

	public int countClaimPolicyNo(ClaimBean bean) {
		int count=0;
		try {
			logger.info("Method to getCountPolicyNo()");
			String query=getQuery("COUNT_CLAIM_POLICYNO");
			logger.info("Query => "+query);
			Object[] obj={bean.getPolicyNo(),"Pending"};
			count=this.mytemplate.queryForObject(query,Integer.class,obj);
			
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { " + e + " }");
		}
		return count;
	}
}
