package com.maan.opencover.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.Home.DataManipualtion.DataSelectCustomer;
import com.maan.common.LogUtil;
import com.maan.services.util.runner;

public class opencoverSummary {
	private static final Logger logger = LogUtil.getLogger(opencoverSummary.class);
	private String sqlQuery = "";
	private String proposalNo = "";
	private String CountyId = "";
	private String coverId = "";
	private String productId = "";
	Connection con;

	public opencoverSummary() {
		System.out.println("opencoverSummary............");
	}

	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	public String getProposalNo() {
		return proposalNo;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}

	public String getCoverId() {
		return coverId;
	}

	public void setCountyId(String CountyId) {
		this.CountyId = CountyId;
	}

	public String getCountyId() {
		return CountyId;
	}

	public HashMap getBrokerDetail() /* 21st Aug */
	{
		HashMap ht = new HashMap();
		String args[] = new String[2];
		try {
			args[0] = proposalNo;
			args[1] = proposalNo;
			sqlQuery = " select nvl(company_name,'0'), nvl(contact_person,'0') from broker_company_master where agency_code = (select oa_code from login_master where login_id = (select broker_id from open_cover_master where proposal_no= ?  and amend_id = (select max(amend_id) from open_cover_master where proposal_no= ? )))";

			String as2[][] = runner.multipleSelection(sqlQuery, args);
			ht.put("BrInf", as2);
			if (as2.length > 0) {
				for (int k = 0; k < as2.length; k++) {
					ht.put("com_name", as2[0][0]);
					ht.put("con_per", as2[0][1]);
				}
			}
		} catch (Exception evx) {
			System.out.println(evx);
			evx.printStackTrace();
		}
		return ht;
	}

	public String[][] getDatas() {
		String[][] result = new String[0][0];
		String args[] = new String[3];
		try {
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = productId;
			sqlQuery = "select distinct(to_char(md.INCEPTION_DATE,'DD-MM-YYYY')), to_char(md.EXPIRY_DATE,'DD-MM-YYYY'), mc.cross_voyage, pc.product_name,mc.min_premium, mc.IMPORT_MIN_PREMIUM_AMOUNT,mc.EXPORT_MIN_PREMIUM_AMOUNT,nvl(mc.CROSS_MIN_PREMIUM_AMOUNT,'0'), mc.TYPE, nvl(mc.COUNTRY_REMARKS,''), md.OPEN_COVER_NO,NVL (MD.RATE_PRINT_STATUS,'N'),NVL (MD.CANCELLATION_CLAUSE,''),NVL(MD.AMENDED_CLAUSE_PRINT_STATUS,'N'),RENEWAL_STATUS,ORIGINAL_POLICY_NO,ENDT_TYPE from OPEN_COVER_POSITION_MASTER md, open_cover_master mc, product_master pc where md.proposal_no =? and mc.amend_id = (select max(amend_id) from open_cover_master where proposal_no = ?) and md.proposal_no = mc.proposal_no and pc.product_id =?";
			result = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e1) {
			System.out.println("Error in selection" + e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getConveyance(String loginBra, String proposalNo) {
		String[][] result = new String[0][0];
		String args[] = new String[2];
		try {
			args[0] = proposalNo;
			args[1] = loginBra;
			sqlQuery = " SELECT OCD.MODE_TRANSPORT_ID,MOT.TRANSPORT_DESCRIPTION,REPLACE(REPLACE(OCD.CONVEYANCE,CHR(10),' '), "
					+ " CHR(13),' ')  FROM OPEN_COVER_DETAIL OCD,MODE_OF_TRANSPORT MOT WHERE OCD.PROPOSAL_NO=? "
					+ " AND OCD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO=OCD.PROPOSAL_NO "
					+ " AND STATUS='Y' AND TO_DATE(EFFECTIVE_DATE,'DD-MM-YY') <= TO_DATE(SYSDATE,'DD-MM-YY')) "
					+ " AND OCD.MODE_TRANSPORT_ID=MOT.MODE_TRANSPORT_ID AND MOT.BRANCH_CODE=? AND OCD.STATUS='Y' "
					+ " AND TO_DATE(OCD.EFFECTIVE_DATE,'DD-MM-YY') <= TO_DATE(SYSDATE,'DD-MM-YY') ";
			result = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e1) {
			System.out.println("Error in selection" + e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getModeMaster(String loginBra) {
		String[][] modeMaster = null;
		String args[] = new String[3];
		try {
			modeMaster = new String[0][0];
			args[0] = proposalNo;
			args[1] = loginBra;
			args[2] = proposalNo;
			sqlQuery = " select mp.mode_transport_ID, md.transport_description,(mp.SUM_INSURED_LIMIT * mp.EXCHANGE_RATE) from mode_of_transport md, open_cover_detail mp where mp.status='Y' and mp.proposal_no = ? and md.BRANCH_CODE=? and mp.amend_id = (select max(amend_id) from open_cover_detail where proposal_no = ?) and md.mode_transport_ID=mp.mode_transport_ID order by mp.mode_transport_ID";
			modeMaster = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modeMaster;
	}

	public String[][] getCoverMaster(String loginBra) {
		String[][] coverMaster = null;
		String args[] = new String[3];
		try {
			coverMaster = new String[0][0];
			args[0] = loginBra;
			args[1] = proposalNo;
			args[2] = proposalNo;
			sqlQuery = "select a.mode_transport_id,a.cover_id,b.cover_name from open_cover_sub_detail a,cover_master b  where b.cover_id=a.cover_id and b.BRANCH_CODE=? and a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no =?) ";
			coverMaster = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coverMaster;
	}

	public String[][] getExtraCover() {
		String[][] commodity = new String[0][0];
		String args[] = new String[1];
		try {
			args[0] = proposalNo;
			sqlQuery = "select md.sea_options,md.mode_transport_id, md.cover_id from open_cover_sub_detail md  where  md.status='Y' and md.proposal_no = ? and md.sea_options != null";
			commodity = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commodity;
	}

	public String[][] getcheckInsuranceId() {
		String[][] chInsId = new String[0][0];
		String args[] = new String[2];
		try {
			sqlQuery = "select no_of_insurance_company from open_cover_master where amend_id = (select max(amend_id) from open_cover_master where proposal_no=?) and proposal_no = ? ";
			args[0] = proposalNo;
			args[1] = proposalNo;
			chInsId = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chInsId;
	}

	public String[][] getInsuranceCompanyId() {
		String[][] comp_Id = new String[0][0];
		String args[] = new String[2];
		try {
			args[0] = proposalNo;
			args[1] = proposalNo;
			sqlQuery = "select  a.insurance_company_id,a.proposal_no, a.share_percentage from open_cover_share_percentage a where amend_id = (select max(amend_id) from open_cover_share_percentage a where a.proposal_no = ? ) and a.proposal_no=? ";
			comp_Id = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comp_Id;
	}

	public String[][] getInsuranceCompanyName(String id) {
		String[][] comp_Name = new String[0][0];
		try {
			sqlQuery = "select insurance_company_name from open_cover_insurance where insurance_company_id in ("
					+ id + ")";
			comp_Name = runner.multipleSelection(sqlQuery);
		} catch (Exception ek) {
			ek.printStackTrace();
		}
		return comp_Name;
	}

	public String[][] getModeofTransport(String loginBra, String proposalNo) {
		String[][] moT = new String[0][0];
		try {
			// sqlQuery =
			// "select  md.transport_description, cc.cover_name,oc.sum_insured_limit from open_cover_detail oc, mode_of_transport md, cover_master cc where proposal_no = ? and oc.amend_id = (select max(amend_id) from open_cover_detail where proposal_no = ?) and md.BRANCH_CODE=cc.BRANCH_CODE and cc.BRANCH_CODE=? and oc.mode_transport_id = md.mode_transport_id and cc.cover_id = oc.mode_transport_id ";
			sqlQuery = "SELECT  MD.TRANSPORT_DESCRIPTION, CC.COVER_NAME,(OC.SUM_INSURED_LIMIT*OC.EXCHANGE_RATE) FROM OPEN_COVER_DETAIL OC, "
					+ "MODE_OF_TRANSPORT MD, COVER_MASTER CC,OPEN_COVER_SUB_DETAIL OCS WHERE OC.PROPOSAL_NO = "
					+ proposalNo
					+ " AND "
					+ "OC.AMEND_ID = (SELECT MAX(AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = "
					+ proposalNo
					+ ") AND "
					+ "MD.BRANCH_CODE=CC.BRANCH_CODE AND CC.BRANCH_CODE=? AND OC.MODE_TRANSPORT_ID = MD.MODE_TRANSPORT_ID AND "
					+ "CC.COVER_ID = OCS.COVER_ID AND OCS.PROPOSAL_NO=OC.PROPOSAL_NO AND OCS.MODE_TRANSPORT_ID=OC.MODE_TRANSPORT_ID "
					+ "AND OCS.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_SUB_DETAIL WHERE PROPOSAL_NO =OC.PROPOSAL_NO)  ORDER BY MD.MODE_TRANSPORT_ID,CC.COVER_ID";

			moT = runner.multipleSelection(sqlQuery, new String[] { loginBra });
		} catch (Exception e1) {
			System.out.println("Error in selection getModeofTransport method"
					+ e1);
			e1.printStackTrace();
		}
		return moT;
	}

	public String[][] getOpenCoverInformation() {
		String coverInf[][] = null;
		String args[] = new String[4];
		try {
			coverInf = new String[0][0];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = proposalNo;
			args[3] = proposalNo;

			sqlQuery = "select a.title, a.first_name, nvl(a.company_name,0), b.back_date_days,b.cross_voyage_turnover, b.rsa_shared_percentage,b.no_of_insurance_company,b.cross_voyage,b.cross_voyage_percentage, b.commission,b.MISSISSIPI_OPEN_POLICY_ID,b.BUSINESS_TYPE,b.MISSIPPI_OPENCOVER_NO from personal_info a, open_cover_master b where a.customer_id =  ( select b.customer_id from open_cover_master b where b.proposal_no = ? and b.amend_id = (select max(b.amend_id) from open_cover_master b where b.proposal_no =?))  and b.proposal_no = ? and b.amend_id = (select max(b.amend_id) from open_cover_master b where b.proposal_no =?) and a.customer_id = b.customer_id";
			coverInf = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e8) {
			System.out.println("Error in getOpenCoverInformation method" + e8);
			e8.printStackTrace();
		}
		return coverInf;
	}

	public String[][] getOpenCoverDepostDetails(String branchCode) {
		String[][] depositDetails = new String[0][0];
		try {
			String query = "SELECT "
							+ " DECODE(OCM.EXISTING_DEPOSIT_YN,'Y','Old','Yes'), "
							+ " (select detail_name from constant_detail cd where cd.category_id='139' and cd.branch_code=? and cd.CATEGORY_DETAIL_ID=ocm.DEPOSIT_TYPE), "
							+ " OCM.DEBIT_NOTE_NO, OCM.CREDIT_NOTE_NO, OCM.MARINE_PREMIUM, OCM.WAR_PREMIUM, OCM.REMARKS "
							+ " FROM OPEN_COVER_MASTER OCM WHERE OCM.PROPOSAL_NO=? AND OCM.DEPOSIT_PREMIUM_YN='Y' " 
							+ " AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER OCM1 WHERE OCM1.PROPOSAL_NO=OCM.PROPOSAL_NO)";
			String args[] = new String[]{branchCode ,proposalNo};
			depositDetails = runner.multipleSelection(query, args);
		} catch (Exception e8) {
			System.out
					.println("Error in getOpenCoverDepostDetails method" + e8);
			e8.printStackTrace();
		}
		return depositDetails;
	}

	public String getAdditionalCustomer() {
		String args[] = new String[1];
		String additionalInsured = "";
		String query = "";
		try {
			args[0] = proposalNo;

			String insured = new DataSelectCustomer()
					.getOpenCoverAdditionalInsured(proposalNo);
			query = "SELECT REPLACE((PI.TITLE||'. '||PI.FIRST_NAME||', '||'PO BOX '||PI.POBOX||', '||PI.EMIRATE||', '||PI.ADDRESS1||', '||PI.ADDRESS2),', ,',NULL) FROM PERSONAL_INFO PI WHERE PI.CUSTOMER_ID IN ("
					+ insured
					+ ") ORDER BY INSTR('"
					+ insured
					+ "', PI.CUSTOMER_ID)";
			String[][] insuredList = runner.multipleSelection(query);
			if (insuredList != null && insuredList.length > 1) {
				for (int i = 0; i < insuredList.length; i++) {
					if (i < insuredList.length - 1) {
						additionalInsured += insuredList[i][0] + " and/or<br/>";
					} else {
						additionalInsured += insuredList[i][0];
					}
				}
			}
		} catch (Exception e8) {
			System.out.println("Error in getOpenCoverInformation method" + e8);
			e8.printStackTrace();
		}
		return additionalInsured;
	}

	public String[][] getColumnWise(String loginBra) {
		String[][] colWise = null;
		String args[] = new String[3];
		try {
			colWise = new String[0][0];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = loginBra;
			sqlQuery = "select b.transport_description,c.cover_name, a.mode_transport_id,a.cover_id from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and a.status='Y' and c.cover_id=a.cover_id and b.BRANCH_CODE=? and b.BRANCH_CODE=c.BRANCH_CODE and b.mode_transport_id=a.mode_transport_id order by a.cover_id";
			colWise = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e2) {
			System.out.println("Error in selection getColumnWise method" + e2);
			e2.printStackTrace();
		}
		return colWise;
	}

	public String[][] getCountryList() {
		String[][] countryList = null;
		try {
			countryList = new String[0][0];
			sqlQuery = "select country_name from country_master where country_id in ("
					+ CountyId + ")";
			countryList = runner.multipleSelection(sqlQuery);
		} catch (Exception e3) {
			System.out.println("Error in selection getCountryList Method" + e3);
			e3.printStackTrace();
		}
		return countryList;
	}

	public String[][] getCommodityId() {
		String[][] CommodityId = null;
		String args[] = new String[1];
		try {
			CommodityId = new String[0][0];
			sqlQuery = "select  max(amend_id),commodity_id from open_cover_commodity_master where commodity_id in(select distinct(commodity_id) from open_cover_commodity_master  where proposal_no=? group by commodity_id) group by commodity_id";
			args[0] = proposalNo;
			CommodityId = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e5) {
			System.out.println("Error in Commodity Id method" + e5);
			e5.printStackTrace();
		}
		return CommodityId;
	}

	public String[][] getCoverId(String CommList, String loginBra) {
		String[][] CoverId = null;
		String args[] = new String[2];
		try {
			CoverId = new String[0][0];
			args[0] = proposalNo;
			args[1] = proposalNo;

			// sqlQuery =
			// "select max(a.amend_id),a.cover_id from open_cover_commodity_detail a where a.proposal_no=?  and a.commodity_id in ("+CommList+") and a.cover_id in(select   md.cover_id from mode_of_transport mx, open_cover_sub_detail md, cover_master co where proposal_no = ? and md.cover_id = co.cover_id and mx.mode_transport_id = md.mode_transport_id and co.BRANCH_CODE=mx.BRANCH_CODE and co.BRANCH_CODE=?) group by a.cover_id";

			// Above one Hided and below created on 10/02/2009 by rajesh for
			// showing correct current cluases in print quote screen
			sqlQuery = "select distinct a.amend_id,a.cover_id from open_cover_commodity_detail a where a.proposal_no=? and  a.amend_id in(select max(amend_id) from open_cover_commodity_detail a where a.proposal_no=?)";

			CoverId = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e7) {
			System.out.println("Error in Get Cover_id" + e7);
			e7.printStackTrace();
		}
		return CoverId;
	}

	public String[][] getCommodityName(String loginBra) {
		String[][] commodityName = null;
		String args[] = new String[4];
		try {
			commodityName = new String[0][0];

			sqlQuery = " select a.commodity_id,b.commodity_name,a.commodity_name_desc from open_cover_commodity_master a,commoditymaster b where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_commodity_master where proposal_no=? )  and a.status='Y' and b.commodity_id=a.commodity_id and  b.commodity_id=a.commodity_id and b.BRANCH_CODE=? and b.amend_id=(select max(amend_id) from commoditymaster where commodity_id=a.commodity_id and BRANCH_CODE=?)";
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = loginBra;
			args[3] = loginBra;
			commodityName = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e5) {
			System.out.println("Error in selection getCommodityName" + e5);
			e5.printStackTrace();
		}
		return commodityName;
	}

	public java.util.ArrayList getCommodityList(String loginBra) {
		java.util.ArrayList rateModify = new java.util.ArrayList();
		String[][] getsDetail = null;
		String sql = "";
		String args[] = new String[0];
		try {
			sql = "select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name,A.COVER_TYPE_ID from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and a.status='Y' and c.cover_id=a.cover_id and b.BRANCH_CODE=c.BRANCH_CODE and c.BRANCH_CODE=? and b.mode_transport_id=a.mode_transport_id group by a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name,A.COVER_TYPE_ID order by a.mode_transport_id,a.cover_id";
			args = new String[3];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = loginBra;
			getsDetail = runner.multipleSelection(sql, args);
			rateModify.add(0, getsDetail);

			getsDetail = null;
			sql = "select a.commodity_id,a.commodity_name_desc,b.commodity_name from open_cover_commodity_master a,commoditymaster b where a.proposal_no=? and b.BRANCH_CODE=? and a.amend_id=(select max(amend_id) from open_cover_commodity_master where proposal_no=?) and a.status='Y' and b.commodity_id=a.commodity_id and  b.commodity_id=a.commodity_id and b.amend_id=(select max(amend_id) from commoditymaster where commodity_id=a.commodity_id and BRANCH_CODE=?)";
			args = new String[4];
			args[0] = proposalNo;
			args[1] = loginBra;
			args[2] = proposalNo;
			args[3] = loginBra;
			getsDetail = runner.multipleSelection(sql, args);
			rateModify.add(1, getsDetail);

			getsDetail = null;
			sql = "select commodity_id,cover_id,commodity_base_rate,COVER_TYPE_ID from open_cover_commodity_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_commodity_detail where proposal_no=?) order by commodity_id";
			args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			getsDetail = runner.multipleSelection(sql, args);

			rateModify.add(2, getsDetail);
		} catch (Exception e) {
			System.out
					.println("  ERROR in When we getting REecords for Summary Page-->"
							+ e.toString());
			e.printStackTrace();
		}
		return rateModify;
	}

	public String[][] getCommodityList1() {
		String[][] commodityList = null;
		String args[] = new String[2];
		try {
			commodityList = new String[0][0];
			args[0] = proposalNo;
			args[1] = proposalNo;
			sqlQuery = "select commodity_base_rate,amend_id,cover_id from open_cover_commodity_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_commodity_detail where proposal_no=?) order by commodity_id";
			commodityList = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e4) {
			System.out.println("Error in selection getCommodityList method"
					+ e4);
			e4.printStackTrace();
		}
		return commodityList;
	}

	public HashMap getCountryDetails() {
		HashMap countryDetails = new HashMap();
		String args[] = new String[2];
		try {
			sqlQuery = "select open_cover_country_id_dest,  war_rate from open_cover_country_master where proposal_no =? and amend_id = (select max(amend_id) from open_cover_country_master where proposal_no = ?) ";
			args[0] = proposalNo;
			args[1] = proposalNo;
			String[][] countryDet = runner.multipleSelection(sqlQuery, args);
			countryDetails.put("Country", countryDet);
			if (countryDet.length > 0) {
				for (int i = 0; i < countryDet.length; i++) {
					countryDet[0][0] = countryDet[0][0] == null ? ""
							: countryDet[0][0];
					countryDet[0][1] = countryDet[0][1] == null ? "0"
							: countryDet[0][1];
					countryDetails.put("dest_id", countryDet[0][0]);
					countryDetails.put("warrate", countryDet[0][1]);
				}
			}
		} catch (Exception e3) {
			System.out.println("Error in selection getCountryDetails" + e3);
			e3.printStackTrace();
		}
		return countryDetails;
	}

	public String[][] getClausesInformation() {
		String[][] clauseInf = new String[0][0];
		String args[] = new String[4];
		try {
			sqlQuery = "select clauses_id,clauses_description from open_cover_clauses where status='Y' and cover_id=? and proposal_no = ?  and amend_id = (select max(amend_id) from open_cover_clauses where status='Y' and cover_id=? and proposal_no = ?)";
			args[0] = coverId;
			args[1] = proposalNo;
			args[2] = coverId;
			args[3] = proposalNo;
			clauseInf = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e10) {
			System.out
					.println("Error in selection getClausesInformation" + e10);
			e10.printStackTrace();
		}
		return clauseInf;
	}

	public String[][] getCoverName(String loginBra) {
		String[][] coverName = new String[0][0];
		String args[] = new String[3];
		try {
			sqlQuery = "select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and a.status='Y' and  c.cover_id=a.cover_id and b.BRANCH_CODE = c.BRANCH_CODE and c.BRANCH_CODE=? and b.mode_transport_id=a.mode_transport_id group by a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name order by a.mode_transport_id,a.cover_id";
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = loginBra;
			coverName = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e8) {
			System.out.println("Error in selection getCoverName method" + e8);
			e8.printStackTrace();
		}
		return coverName;
	}

	public String[][] getWSRCC(int a, int b, String loginBra)// ,int c)
	{
		String[][] wsrccValue = null;
		try {
			sqlQuery = "select clauses_id, clauses_description, extra_cover_id from clauses_master where extra_cover_id in ("
					+ a
					+ ","
					+ b
					+ ") and BRANCH_CODE='"
					+ loginBra
					+ "' order by extra_cover_id desc";
			wsrccValue = runner.multipleSelection(sqlQuery);
		} catch (Exception e) {
			System.out.println("Error in getting getWSRCC method" + e);
			e.printStackTrace();
		}
		return wsrccValue;
	}

	public String[][] getWSRCC(String proposalNo) {
		String wsrcc[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			sql = "select warranty_id,warranty_description,extra_cover_id from open_cover_warranties where proposal_no=? and amend_id=(select max(amend_id) from open_cover_warranties where proposal_no=?)";
			args[0] = proposalNo;
			args[1] = proposalNo;
			wsrcc = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrcc;
	}

	public String[][] getExclusions(String proposalNo) {
		String wsrcc[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			sql = "select exclusion_id,exclusion_description,extra_cover_id from open_cover_exclusions where proposal_no=? and amend_id=(select max(amend_id) from open_cover_exclusions where proposal_no=?)";
			args[0] = proposalNo;
			args[1] = proposalNo;
			wsrcc = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrcc;
	}

	public String[][] getFreeText(String proposalNo, String coverNos) {
		String wsrcc[][] = new String[0][0];
		String sql = "";
		String args[] = new String[4];
		try {
			sql = "select free_text_id,nvl(free_text_description,'9999') from open_cover_free_text where proposal_no=? and cover_id=? and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=? and cover_id=?)";
			args[0] = proposalNo;
			args[1] = coverNos;
			args[2] = proposalNo;
			args[3] = coverNos;
			wsrcc = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrcc;
	}

	public String getopenCoverNumber(String loginBra, String proposalNo) {
		String current_no = "", sequence = "";
		String args[] = new String[0];
		String sql = "";
		try {
			if (loginBra.indexOf("'") != -1)
				loginBra = loginBra.replaceAll("'", "");

			/*
			 * try { args = new String[2]; args[0] = loginBra; args[1] =
			 * loginBra; sql =
			 * "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select OPENCOVER_NO_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and status='Y' and BRANCH_CODE=?"
			 * ; current_no = runner.singleSelection(sql,args);
			 * 
			 * args = new String[4]; args[0] = current_no; args[1] = current_no;
			 * args[2] = loginBra; args[3] = loginBra; sql =
			 * "update policyno_generate set current_no=?,remarks=? where type_id=(select OPENCOVER_NO_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and status='Y' and BRANCH_CODE=?"
			 * ; runner.multipleUpdation(sql,args); } catch(Exception e) {
			 * System
			 * .out.println("ERROR in OpenCover No in OpenCoverSummary  "+e
			 * .toString()); e.printStackTrace(); }
			 */
			/*
			 * sql="SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=146 AND CATEGORY_DETAIL_ID=1 AND STATUS='Y' AND BRANCH_CODE='"
			 * +loginBra+"'"; sequence=runner.singleSelection(sql);sql=
			 * "SELECT A.BRANCH_PREFIX || B. BUSINESS_TYPE || C.RSACODE || D.DETAIL_NAME||LPAD("
			 * +sequence+
			 * ".NEXTVAL,D.REMARKS,0)||'-0' FROM BRANCH_MASTER A, OPEN_COVER_MASTER B, PRODUCT_MASTER C, CONSTANT_DETAIL D WHERE A.BRANCH_CODE=? AND B.PROPOSAL_NO=? AND B.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=B.PROPOSAL_NO) AND C.PRODUCT_ID='11' AND C.BRANCH_CODE=A.BRANCH_CODE AND D.CATEGORY_ID=144 AND D.BRANCH_CODE=A.BRANCH_CODE"
			 * ; current_no=runner.singleSelection(sql, new String[]{loginBra,
			 * proposalNo});
			 * System.out.println("   OpenCoverSummary  "+current_no);
			 */
			sql = "SELECT Policy_Generate (?,?,?) FROM DUAL";
			current_no = runner.singleSelection(sql, new String[] { "",
					loginBra, "Opencover" });
			return current_no;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return current_no;
	}

	public String[][] getFreeTextSummary(String proposalNo) {
		String wsrcc[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try {
			sql = "select free_text_id,free_text_description from open_cover_free_text where proposal_no=?  and amend_id=(select max(amend_id) from open_cover_free_text where proposal_no=?)";
			args[0] = proposalNo;
			args[1] = proposalNo;
			wsrcc = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsrcc;
	}

	public String openCoverNumber(String proposalNo, String loginBra,
			String ratesYN, String cacelClaus, String amendedClauseYN,
			String appMode, String renewalYN, String endtYN) {
		String count = "", renNo = "", ckey = "", status = "P";
		String result = "";
		String args[] = new String[0];
		String sql = "";
		try {
			args = new String[1];
			args[0] = proposalNo;
			sql = "select open_cover_no from open_cover_position_master where proposal_no=?";
			count = runner.singleSelection(sql, args);
			//if (count == null || ("").equalsIgnoreCase(count)
				//	|| ("DIDN'T SELECTED").equalsIgnoreCase(count)) {
				String openCoverNo = runner
						.singleSelection(
								"SELECT A.MISSIPPI_OPENCOVER_NO FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)",
								args);
				if (openCoverNo == null || openCoverNo.trim().length() <= 0) {
					status = "Y";
					count = getopenCoverNumber(loginBra, proposalNo);
				} else {
					count = openCoverNo;
					if (!"Y".equalsIgnoreCase(renewalYN)
							&& !"Y".equalsIgnoreCase(endtYN)) {
						ckey = getOpenCoverCkey(openCoverNo);
					}
				}
				if ("Y".equalsIgnoreCase(renewalYN)) {
					/*// status="Y";
					count = count.indexOf("-") != -1 ? (count.substring(0,
							count.indexOf("-"))) : count;
					// renNo=runner.singleSelection("SELECT NVL(MAX(RENNO),0)+1 FROM POLICY@ECARGO_"+("Test".equalsIgnoreCase(appMode)?"DEV":"PROD")+" WHERE POLNO=?",new
					// String[]{count});
					renNo = runner
							.singleSelection(
									"SELECT NVL(MAX(RENNO),0)+1 FROM POLICY WHERE POLNO=?",
									new String[] { count });
					count = count + "-" + ("".equals(renNo) ? "0" : renNo);*/
				}
				if ("Y".equalsIgnoreCase(endtYN)) {
					// status="Y";
				}
				runner
						.multipleUpdation(
								"UPDATE OPEN_COVER_MASTER A SET A.MISSIPPI_OPENCOVER_NO=? WHERE A.PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)",
								new String[] { count, proposalNo });
				sql = "update open_cover_position_master set admin_status='',open_cover_no=?,inception_date=(select distinct(open_cover_start_date) from open_cover_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?)),expiry_date=(select distinct(open_cover_end_date) from open_cover_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?)),status=?,RATE_PRINT_STATUS=?,CANCELLATION_CLAUSE=?,AMENDED_CLAUSE_PRINT_STATUS=?,CKEY=? where proposal_no=?";
				args = new String[11];
				args[0] = count;
				args[1] = proposalNo;
				args[2] = proposalNo;
				args[3] = proposalNo;
				args[4] = proposalNo;
				args[5] = status;
				args[6] = ratesYN;
				args[7] = cacelClaus;
				args[8] = amendedClauseYN;
				args[9] = ckey;
				args[10] = proposalNo;
				result = runner.multipleUpdation(sql, args);

				if ("UPDATED".equalsIgnoreCase(result))
					insertintoLcMaster(count);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void updateConveyance(String proposalNo, String transportId,
			String conveyance) {
		String sql = "";
		String args[] = new String[3];
		try {
			sql = "UPDATE OPEN_COVER_DETAIL OCD SET OCD.CONVEYANCE=? WHERE OCD.PROPOSAL_NO=? AND OCD.MODE_TRANSPORT_ID=? "
					+ " AND OCD.STATUS='Y' AND TO_DATE(OCD.EFFECTIVE_DATE,'DD-MM-YY')<= TO_DATE(SYSDATE,'DD-MM-YY') AND OCD.AMEND_ID= "
					+ " (SELECT MAX(AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO=OCD.PROPOSAL_NO AND MODE_TRANSPORT_ID= "
					+ " OCD.MODE_TRANSPORT_ID AND STATUS='Y'AND TO_DATE(EFFECTIVE_DATE,'DD-MM-YY')<= TO_DATE(SYSDATE,'DD-MM-YY')) ";
			args[0] = conveyance;
			args[1] = proposalNo;
			args[2] = transportId;
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			System.out.println("   Error in insert into LcMaster "
					+ e.toString());
			e.printStackTrace();
		}

	}

	public void updaterateYN(String proposalNo, String ratesYN,
			String cacelClaus, String amendedClauseYN) {
		String sql = "";
		String args[] = new String[4];
		try {
			sql = "update open_cover_position_master set RATE_PRINT_STATUS=?,CANCELLATION_CLAUSE=?,AMENDED_CLAUSE_PRINT_STATUS=? where proposal_no=?";
			args[0] = ratesYN;
			args[1] = cacelClaus;
			args[2] = amendedClauseYN;
			args[3] = proposalNo;
			runner.multipleUpdation(sql, args);
		} catch (Exception e) {
			System.out
					.println("   Error in updatoin open_cover_position_master "
							+ e.toString());
			e.printStackTrace();
		}
	}

	public String[][] showCoverages(String proposalNo, String loginBra) {
		String showcovers[][] = new String[0][0];
		String sql = "";
		String args[] = new String[3];
		try {
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = loginBra;
			// sql =
			// "select a.amend_id,a.cover_id,a.sea_options,b.cover_name,a.mode_transport_id from open_cover_sub_detail a,cover_master b where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?)and b.cover_id=a.cover_id and b.BRANCH_CODE=? order by a.mode_transport_id";
			sql = "select a.amend_id,a.cover_id,a.sea_options,b.cover_name,a.mode_transport_id from open_cover_sub_detail a,cover_master b where a.proposal_no=? and b.amend_id=(select max(amend_id) from cover_master c where b.cover_id=c.cover_id and c.branch_code=b.branch_code) and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and b.cover_id=a.cover_id and b.BRANCH_CODE=? order by a.mode_transport_id";

			showcovers = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return showcovers;
	}

	public void insertintoLcMaster(String opencoverNo) {
		String sql = "";
		String args[] = new String[8];
		try {
			sql = "insert into open_cover_lc_master (lc_number,lc_amount,bank_id,AMEND_ID,LC_DATE,EFFECTIVE_DATE,Expiry_DATE,REMARKS,STATUS,open_cover_no,lc_id)values(?,?,?,?,SYSDATE,SYSDATE,SYSDATE,?,?,?,?)";
			args[0] = "NONE";
			args[1] = "0";
			args[2] = "99999";
			args[3] = "0";
			args[4] = "Normal";
			args[5] = "Y";
			args[6] = opencoverNo;
			args[7] = "99999";

			runner.multipleInsertion(sql, args);
		} catch (Exception e) {
			System.out.println("   Error in insert into LcMaster "
					+ e.toString());
			e.printStackTrace();
		}
	}

	public String[][] getBranchFullDetail(String branch) {
		String branchDet[][] = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try {
			args[0] = branch;
			sql = "select address1,address2,address3,city,country from branch_master where branch_code=?";
			branchDet = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchDet;
	}

	public String getMOCGoodsName(String branch) {
		String goodsName = "";
		String sql = "";
		String args[] = new String[4];
		try {
			args[0] = proposalNo;
			args[1] = branch;
			args[2] = proposalNo;
			args[3] = branch;
			sql = "SELECT SUBSTR (SYS_CONNECT_BY_PATH (commodity_name_desc , ','), 2) csv FROM (SELECT a.commodity_name_desc , ROW_NUMBER () OVER (ORDER BY b.commodity_id ) rn,COUNT (*) OVER () cnt from open_cover_commodity_master a,commoditymaster b where a.proposal_no=? and b.BRANCH_CODE=? and a.amend_id=(select max(amend_id) from open_cover_commodity_master where proposal_no=?) and a.status='Y' and b.commodity_id=a.commodity_id and  b.commodity_id=a.commodity_id and b.amend_id= (select max(amend_id) from commoditymaster where commodity_id=a.commodity_id and BRANCH_CODE=?) ) WHERE rn = cnt START WITH rn = 1 CONNECT BY rn = PRIOR rn + 1";
			goodsName = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goodsName;
	}

	public Map<String, String> openCoverPolicyIntegration(final String policyNo) {
		logger.info("openCoverPolicyIntegration - Enter || openCoverNo: "
				+ policyNo);
		Map<String, String> map = new HashMap<String, String>();
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBConnection();
			cstmt = con.prepareCall("CALL INTEGRATION_OPENCOVER(?,?,?,?,?)");
			cstmt.setString(1, policyNo);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(3, java.sql.Types.CHAR);
			cstmt.registerOutParameter(4, java.sql.Types.VARCHAR);
			cstmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			cstmt.execute();
			map.put("ERROR", cstmt.getString(2));
			map.put("STATUS", cstmt.getString(3));
			map.put("DN", cstmt.getString(4));
			map.put("CN", cstmt.getString(5));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("openCoverPolicyIntegration - Exit || ERROR: "
				+ map.get("ERROR") + " STATUS: " + map.get("STATUS") + "DN: "
				+ map.get("DN") + " CN: " + map.get("CN"));
		return map;
	}

	public String[][] getDebitDetails(String branchCode) {
		String DebitDetails[][] = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		sql = "select category_detail_id,detail_name from constant_detail where category_id=? and branch_code=?";

		System.out.println("the GET DEBIT DETAILS" + sql);
		try {
			args[0] = "139";
			args[1] = branchCode;
			DebitDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DebitDetails;
	}

	public String[][] getInstallmentDetails(String branchCode) {
		String InstallDetails[][] = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		sql = "select category_detail_id,detail_name from constant_detail where category_id=? and branch_code=?";
		System.out.println("the GET Installment DETAILS" + sql);

		try {
			args[0] = "140";
			args[1] = branchCode;
			InstallDetails = runner.multipleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return InstallDetails;
	}

	public String getCommission(String proposalNo) {
		String result = "";
		String sql = "SELECT NVL(A.COMMISSION,0) FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
		result = runner.singleSelection(sql, new String[] { proposalNo });
		return result;
	}

	public String[][] getNoteNo(String openCoverNo) {
		String sql = "SELECT A.DEBIT_NOTE_NO, A.CREDIT_NOTE_NO FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
		String result[][] = runner.multipleSelection(sql,
				new String[] { openCoverNo });
		return result;
	}

	public List<HashMap<String, Object>> getDepositInfo(String proposalNo) {
		String sql = "SELECT A.AMEND_ID,(SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=139 AND CATEGORY_DETAIL_ID=A.DEPOSIT_TYPE AND BRANCH_CODE=A.BRANCH_CODE) DEPOSIT_TYPE,(SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=140 AND CATEGORY_DETAIL_ID=A.INSTALLMENT_TYPE AND BRANCH_CODE=A.BRANCH_CODE) INSTALLMENT_TYPE,A.DEPOSIT_AMOUNT,A.ISSUANCE_FEE_DEBIT,A.DEBIT_NOTE_NO, A.CREDIT_NOTE_NO,TO_CHAR(A.DEBIT_NOTE_DATE,'DD/MM/YYYY') DEBIT_NOTE_DATE,TO_CHAR(A.CREDIT_NOTE_DATE,'DD/MM/YYYY') CREDIT_NOTE_DATE,A.EXISTING_DEPOSIT_YN FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND A.DEPOSIT_PREMIUM_YN='Y' ORDER BY A.AMEND_ID";
		List<HashMap<String, Object>> list = runner.getResultList(sql,
				new String[] { proposalNo });
		return list;
	}

	public static List<HashMap<String, Object>> getOpenCoverPolicyInfo(
			String openCoverNo, String loginId, String amendId) {
		amendId = (amendId == null || "".equals(amendId)) ? "(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=OCPM.PROPOSAL_NO)"
				: amendId;
		String sql = "SELECT BM.BRANCH_CODE,BM.BRANCH_NAME,NVL(BM.ORIGINATION_COUNTRY_ID,0) ORIGINATION_COUNTRY_ID,BM.CURRENCY_ABBREVIATION,"
				+ "NVL(BM.DECIMAL_PLACES,0) DECIMAL_PLACES,BM.HEADER_IMG,BM.FOOTER_IMG,BM.SIGN_IMG,BM.STAMP,BM.CURRENCY_NAME,"
				+ "BM.CURRENCY_ABBREVIATION,NVL(BM.CURRENCY_ACRONYM,' ') CURRENCY_ACRONYM,BM.CURRENCY_DECIMAL_NAME,BM.CURRENCY_DECIMAL_DIGIT,"
				+ "BM.ADDRESS1,BM.REMARKS,OCM.DEBIT_NOTE_NO,OCM.CREDIT_NOTE_NO,TO_CHAR(OCM.DEBIT_NOTE_DATE,'DD/MM/YYYY') DEBIT_NOTE_DATE,"
				+ "TO_CHAR(OCM.CREDIT_NOTE_DATE,'DD/MM/YYYY') CREDIT_NOTE_DATE, OCPM.OPEN_COVER_NO,PM.PRODUCT_NAME,UPPER(PI.TITLE||' '||PI.FIRST_NAME) CUST_FIRST_NAME,TO_CHAR(OCPM.INCEPTION_DATE,'DD/MM/YYYY') INCEPTION_DATE,"
				+ "TO_CHAR(OCPM.EXPIRY_DATE,'DD/MM/YYYY') EXPIRY_DATE,NVL(OCM.COMMISSION,0) COMMISSION, ROUND(NVL(OCM.DEPOSIT_AMOUNT,0),NVL(CM.RFACTOR,0)) DEPOSIT_AMOUNT,OCM.DEBIT_NOTE_NAME,"
				+ "ROUND((NVL(OCM.COMMISSION,0)*NVL(OCM.DEPOSIT_AMOUNT,0)/100),NVL(CM.RFACTOR,0)) COMMISSION_AMOUNT, NVL(LM.CORE_LOGIN_ID,LM.LOGIN_ID) CORE_LOGIN_ID,"
				+ "(SELECT UPPER(DETAIL_NAME) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=139 AND CATEGORY_DETAIL_ID=OCM.DEPOSIT_TYPE AND "
				+ "BRANCH_CODE=OCM.BRANCH_CODE) DEPOSIT_TYPE,ROUND(NVL(OCM.ISSUANCE_FEE_DEBIT,0),NVL(CM.RFACTOR,0)) ISSUANCE_FEE_DEBIT,OCM.MIN_PREMIUM_ISSUANCE_FEE,PI.CUSTOMER_ID,PI.POBOX CUST_POBOX,"
				+ "UPPER(PI.COMPANY_NAME) CUST_COMPANY,UPPER(PI.LAST_NAME) CUST_LAST_NAME,CM.CURRENCY_ID,CM.SUB_CURRENCY, UPPER(PI.ADDRESS1) CUST_ADDR1,"
				+ "UPPER(PI.ADDRESS2) CUST_ADDR2,PI.TELEPHONE CUST_TEL,UPPER(PI.COUNTRY) CUST_COUNTRY,"
				+ "UPPER(NVL(PI.CITY,PI.EMIRATE)) CUST_CITY,PI.CUST_AR_NO,(SELECT CUST_AR_NO FROM PERSONAL_INFO WHERE CUSTOMER_ID=BCM.CUSTOMER_ID)BROKER_AR_NO,BCM.CONTACT_PERSON,UPPER(BCM.ADDRESS1) BROKER_ADDR1,"
				+ "UPPER(BCM.ADDRESS2) BROKER_ADDR2,UPPER(BCM.CITY) BROKER_CITY,UPPER(BCM.COUNTRY) BROKER_COUNTRY,"
				+ "BCM.PHONE BROKER_PHONE,BCM.POBOX BROKER_POBOX,UPPER(NVL(BCM.CITY,BCM.EMIRATE)) BROKER_CITY,"
				+ "UPPER(BCM.COMPANY_NAME) BROKER_COMPANY,ROUND((NVL(OCM.DEPOSIT_AMOUNT,0)+NVL(OCM.ISSUANCE_FEE_DEBIT,0)),NVL(CM.RFACTOR,0)) NET_AMOUNT,"
				+ "OCM.MIN_PREMIUM_ISSUANCE_FEE ISSUANCE_FEE,OCM.TYPE OPEN_COVER_TYPE,OCM.BROKER_ID FROM  "
				+ "BRANCH_MASTER BM, OPEN_COVER_POSITION_MASTER OCPM, PRODUCT_MASTER PM, OPEN_COVER_MASTER OCM,"
				+ "LOGIN_MASTER LM,PERSONAL_INFO PI,CURRENCY_MASTER CM, BROKER_COMPANY_MASTER BCM "
				+ "WHERE BM.BRANCH_CODE=OCM.BRANCH_CODE AND OCPM.OPEN_COVER_NO=? "
				+ "AND PM.PRODUCT_ID=OCM.PRODUCT_ID AND OCM.PROPOSAL_NO=OCPM.PROPOSAL_NO AND LM.LOGIN_ID=? "
				+ "AND PI.CUSTOMER_ID=OCM.CUSTOMER_ID AND OCM.AMEND_ID="
				+ amendId
				+ " AND PM.BRANCH_CODE=BM.BRANCH_CODE AND OCPM.AMEND_ID="
				+ "(SELECT MAX(AMEND_ID) FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=OCPM.OPEN_COVER_NO) AND "
				+ "CM.CURRENCY_ID=1 AND BCM.AGENCY_CODE=(SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=OCM.BROKER_ID) AND CM.COUNTRY_ID=BM.ORIGINATION_COUNTRY_ID";
		List<HashMap<String, Object>> list = runner.getResultList(sql,
				new String[] { openCoverNo, loginId });
		return list;
	}

	public static List<HashMap<String, Object>> getOpenCoverCommodityInfo(
			String openCoverNo) {
		String sql = "SELECT OCCM.COMMODITY_ID,UPPER(OCCM.COMMODITY_NAME_DESC) COMMODITY_NAME_DESC FROM OPEN_COVER_COMMODITY_MASTER OCCM, OPEN_COVER_POSITION_MASTER OCPM WHERE OCCM.PROPOSAL_NO=OCPM.PROPOSAL_NO AND OCCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=OCCM.PROPOSAL_NO) AND OCPM.OPEN_COVER_NO=? ORDER BY OCCM.COMMODITY_ID";
		List<HashMap<String, Object>> list = runner.getResultList(sql,
				new String[] { openCoverNo });
		return list;
	}

	public String updateDepositInfo(String proposalNo, String loginBra,
			String deposit, String depositType, String installType,
			String debitNoteName, String issuanceFee, String remarks,
			String depositDate, String debitNoteNo, String creditNoteNo,
			String marinePremium, String warPremium) {
		String count = "", debitNoteDate = "", creditNoteDate = "", rfactor = "", feePercent = "", minFee = "", existingDeposit = "N", depositAmount = "0";
		try {
			loginBra = loginBra.replaceAll("'", "");
			/*
			 * if("Y".equalsIgnoreCase(deposit)) { debitNoteNo =
			 * runner.singleSelection("SELECT '"+loginBra+
			 * "'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(DEBIT_NUMBER_SEQ_"
			 * +loginBra+
			 * ".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=136 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"
			 * +loginBra+"'"); String commission=getCommission(proposalNo);
			 * if(Double.parseDouble(commission)>0) { creditNoteNo =
			 * runner.singleSelection("SELECT '"+loginBra+
			 * "'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(CREDIT_NUMBER_SEQ_"
			 * +loginBra+
			 * ".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=137 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"
			 * +loginBra+"'"); } debitNoteDate=(debitNoteNo!=null &&
			 * debitNoteNo.length()>0)?",A.DEBIT_NOTE_DATE=SYSDATE":"";
			 * creditNoteDate=(debitNoteNo!=null &&
			 * debitNoteNo.length()>0)?",A.CREDIT_NOTE_DATE=SYSDATE":""; }else
			 */if ("O".equalsIgnoreCase(deposit)) {
				/*
				 * debitNoteDate=(debitNoteNo!=null &&
				 * debitNoteNo.length()>0)?",A.DEBIT_NOTE_DATE=TO_DATE('"
				 * +depositDate+"','DD/MM/YYYY')":"";
				 * creditNoteDate=(debitNoteNo!=null &&
				 * debitNoteNo.length()>0)?",A.CREDIT_NOTE_DATE=TO_DATE('"
				 * +depositDate+"','DD/MM/YYYY')":"";
				 */

				/*
				 * debitNoteDate=(debitNoteNo!=null &&
				 * debitNoteNo.length()>0)?",A.DEBIT_NOTE_DATE=TO_DATE('"
				 * +depositDate+"','YYYY-MM-DD')":"";
				 * creditNoteDate=(debitNoteNo!=null &&
				 * debitNoteNo.length()>0)?",A.CREDIT_NOTE_DATE=TO_DATE('"
				 * +depositDate+"','YYYY-MM-DD')":"";
				 */
				existingDeposit = "Y";
				deposit = "Y";
			} else if ("N".equalsIgnoreCase(deposit)) {
				marinePremium = "";
				warPremium = "";
				depositType = "";
				installType = "";
				remarks = "";
				debitNoteName = "";
			}
			String[][] result = runner
					.multipleSelection(
							"SELECT NVL(A.RFACTOR,0), B.ISSUANCE_FEE, B.MIN_PREMIUM_ISSUANCE_FEE FROM CURRENCY_MASTER A, OPEN_COVER_MASTER B, BRANCH_MASTER C WHERE A.CURRENCY_ID=1 AND A.COUNTRY_ID=C.ORIGINATION_COUNTRY_ID AND C.BRANCH_CODE=B.BRANCH_CODE AND B.PROPOSAL_NO=? AND B.AMEND_ID=(SELECT MAX(AMEND_ID)FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=B.PROPOSAL_NO)",
							new String[] { proposalNo });
			if (result != null && result.length > 0) {
				rfactor = result[0][0] == null ? "" : result[0][0];
				feePercent = result[0][1] == null ? "" : result[0][1];
				minFee = result[0][2] == null ? "" : result[0][2];
			}
			if ("Y".equalsIgnoreCase(deposit)) {
				marinePremium = marinePremium.trim().length() <= 0 ? "0"
						: marinePremium;
				warPremium = warPremium.trim().length() <= 0 ? "0" : warPremium;
				depositAmount = String.valueOf(Double
						.parseDouble(marinePremium)
						+ Double.parseDouble(warPremium));
			}
			count = runner
					.multipleUpdation(
							"UPDATE OPEN_COVER_MASTER A SET A.DEPOSIT_PREMIUM_YN=?,A.DEPOSIT_TYPE=?,A.INSTALLMENT_TYPE=?,A.DEPOSIT_AMOUNT=ROUND(?,"
									+ rfactor
									+ "),A.DEBIT_NOTE_NO=?"
									+ debitNoteDate
									+ ",A.CREDIT_NOTE_NO=?"
									+ creditNoteDate
									+ ",A.DEBIT_NOTE_NAME=?,A.ISSUANCE_FEE_DEBIT=(SELECT CASE WHEN ("
									+ depositAmount
									+ "*"
									+ feePercent
									+ ")/100<="
									+ minFee
									+ " THEN "
									+ minFee
									+ " ELSE ROUND(("
									+ depositAmount
									+ "*"
									+ feePercent
									+ ")/100,"
									+ rfactor
									+ ") END FROM DUAL),A.REMARKS=?,A.EXISTING_DEPOSIT_YN=?,A.MARINE_PREMIUM=?, A.WAR_PREMIUM=? WHERE A.PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)",
							new String[] { deposit, depositType, installType,
									depositAmount, debitNoteNo, creditNoteNo,
									debitNoteName, remarks, existingDeposit,
									marinePremium, warPremium, proposalNo });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public String replaceNull(String value, String replacewith) {
		return value == null || value.equals("null") ? replacewith : value;
	}

	public String[][] getDeductible(String loginBra) {
		String[][] deductible = null;
		String args[] = new String[1];
		try {
			deductible = new String[0][0];
			args[0] = proposalNo;
			// sqlQuery =
			// "SELECT CASE WHEN odm.deductible_id >100 THEN (SELECT detail_name FROM constant_detail WHERE category_id=138 AND category_detail_id=odm.deductible_id) ELSE (SELECT transport_description FROM mode_of_transport WHERE branch_code=? AND mode_transport_id=odm.deductible_id) END transport,odm.sum_insured_percent,odm.deductible_amount_for_loss,cm.currency_name FROM open_cover_deductible_master odm,currency_master cm WHERE cm.currency_id=odm.currency_id AND  cm.country_id=1 AND odm.open_cover_proposal_no=? AND odm.amend_id=(SELECT MAX(amend_id) FROM open_cover_deductible_master WHERE open_cover_proposal_no=?) order by odm.rowid";
			sqlQuery = " SELECT OCCM.START_RANGE,OCCM.END_RANGE,OCCM.EXCESS_AMOUNT,OCCM.CURRENCY_NAME,OCCM.EXCESS_PERCENT "
					+ " FROM OPEN_COVER_DEDUCTIBLE_MASTER OCCM WHERE OCCM.STATUS='Y' AND OCCM.OPEN_COVER_PROPOSAL_NO=? AND TO_DATE(OCCM.effective_date,'dd-MM-YY') <= TO_DATE(SYSDATE,'dd-MM-YY') AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_DEDUCTIBLE_MASTER WHERE OPEN_COVER_PROPOSAL_NO=OCCM.OPEN_COVER_PROPOSAL_NO AND TO_DATE(effective_date,'dd-MM-YY') <= TO_DATE(SYSDATE,'dd-MM-YY') )";
			System.out.println("Deductibles" + sqlQuery);
			deductible = runner.multipleSelection(sqlQuery, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deductible;
	}

	public String getOpenCoverCkey(String policyNo) {
		logger.info("getOpenCoverCkey - Enter || policyNo: " + policyNo);
		String ckey = "";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBConnection();
			cstmt = con.prepareCall("CALL GET_OPENCOVER_CKEY(?,?,?)");
			cstmt.setString(1, policyNo);
			cstmt.setString(2, "C02");
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
			System.out.println("procedure execute");
			cstmt.execute();
			System.out.println("procedure execute success");
			ckey = cstmt.getString(3);
			System.out.println("ckey:-------->" + ckey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("getOpenCoverCkey - Exit || ckey: " + ckey);
		return ckey;
	}

	public String openCoverRenewal(String proposalNo, String type) {
		logger.info("openCoverRenewal - Enter || proposalNo: " + proposalNo
				+ " type: " + type);
		String newProposalNo = "";
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBConnection();
			// cstmt =
			// con.prepareCall("CALL RENEWAL_OPENCOVER_newversion(?,?)");
			cstmt = con.prepareCall("CALL RENEWAL_OPENCOVER(?,?)");
			cstmt.setString(1, type);
			cstmt.setString(2, proposalNo);
			cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			cstmt.execute();
			newProposalNo = cstmt.getString(2);
			System.out.println("newProposalNo:-------->" + newProposalNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		logger.info("openCoverRenewal - Exit || newProposalNo: "
				+ newProposalNo);
		return newProposalNo;
	}

	public String getEndtTypeDescInfo(String endtType) {
		String sql = "SELECT RTRIM(XMLAGG(XMLELEMENT(E,ENDT_TYPE || ', ')).EXTRACT('//text()'),', ') ENDT_TYPE FROM ENDT_TYPE_MASTER WHERE ENDT_TYPE_ID IN ("
				+ endtType + ")";
		return runner.singleSelection(sql);
	}
	public String[][] getDepositeInfo(String proposalNo){
		String result[][]=null;
		try{
			result=runner.multipleSelection("select DEPOSIT_PREMIUM_YN,DEPOSIT_TYPE,INSTALLMENT_TYPE,DEBIT_NOTE_NO,CREDIT_NOTE_NO,MARINE_PREMIUM,WAR_PREMIUM,to_char(DEBIT_NOTE_DATE,'yyyy-MM-dd'),REMARKS ,EXISTING_DEPOSIT_YN from open_cover_master where proposal_no=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)",new String[]{proposalNo,proposalNo});
			}catch(Exception e){
				e.printStackTrace();
			}
		
		return result;
		
		
	}

	public boolean updateExtend(String proposalNo2, String openCoverEndDate) {
		try { 
				runner.multipleUpdation("update open_cover_commodity_detail set END_DATE=to_date(?,'DD/mm/YYYY') where PROPOSAL_NO=?",new String[] { openCoverEndDate, proposalNo2});
				runner.multipleUpdation("update open_cover_detail set OPEN_COVER_END_DATE=to_date(?,'DD/mm/YYYY') where PROPOSAL_NO=?",new String[] { openCoverEndDate, proposalNo2});
				runner.multipleUpdation("update open_cover_lc_master set EXPIRY_DATE=to_date(?,'DD/mm/YYYY') where OPEN_COVER_NO=?",new String[] { openCoverEndDate, proposalNo2});
				runner.multipleUpdation("update open_cover_master set POLICY_END_DATE=to_date(?,'DD/mm/YYYY') where PROPOSAL_NO=?",new String[] { openCoverEndDate, proposalNo2});
				runner.multipleUpdation("update open_cover_position_master set EXPIRY_DATE=to_date(?,'DD/mm/YYYY'),admin_status=null where PROPOSAL_NO=?",new String[] { openCoverEndDate, proposalNo2});
				runner.multipleUpdation("update open_cover_share_percentage set END_DATE=to_date(?,'DD/mm/YYYY') where PROPOSAL_NO=?",new String[] { openCoverEndDate, proposalNo2});
	  		return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateAnnualEstimated(String proposalNo2, String estimateAmount) {
		try { 
			runner.multipleUpdation("update open_Cover_master set FOREIGN_TURNOVER=NVL(?,'0'),CROSS_VOYAGE_TURNOVER=NVL(?,'0')*EXCHANGE_RATE where proposal_no=?",new String[] { estimateAmount,estimateAmount, proposalNo2});
			
  		return true;
	}catch (Exception e) {
		e.printStackTrace();
	}
	return false;
	}
} // Class

