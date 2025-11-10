/**
 * 
 *  Author  : Rajesh.R  [23/08/2007]
 *	Company : MaanSarovar Software Private Limited  Chennai-1
 *	Project : E-Cargo
 *  Purpose : This Bean To ManipulateSELECT The Date
 * 
 */
package com.maan.Home.DataManipualtion;

import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import com.maan.common.CommonHelp;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class HomePdfDataSelect
{
	private static final Logger logger = LogUtil.getLogger(HomePdfDataSelect.class);
	/*
	 * Used to retrieve values from HOME_COVERAGE_BUILDING_TRANSACTION table
	 * based on the Quote_No passing
	 */
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	
	public String[][] homeCoverageBuildingTransactionSelect(final String Quote_No) throws BaseException 
	{
		logger.info("homeCoverageBuildingTransactionSelect method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No };
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_BUILDING where QUOTE_NO=?", args);
		logger.debug("- Exit");
		
		return result;
	}
	/*
	 * Used to retrieve values from HOME_COVERAGE_BUILDING_TRANSACTION table
	 * based on the Quote_No and Cover_Id passing
	 */
	public String[][] homeCoverageTotalTransactionSelect(final String Quote_No,	final String CoverId) throws BaseException 
	{
		logger.info("homeCoverageTotalTransactionSelect method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No, CoverId };
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and COVER_ID=? and home_sno not in(1001,1002,1003) and lower(PRODUCT_SERIAL_NO) not in ('select')", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String[][] homeCoverageTransationSuminsuredSelect(final String Quote_No) throws BaseException 
	{
		logger.info("homeCoverageTransationSuminsuredSelect method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No };
		final String result[][] = runner.multipleSelection("select * from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and home_sno between "+ 1001 + " and " + 1003, args);
		logger.debug("- Exit");
		
		return result;
	}

	// Single Selection Method //
	public String[][] singleSelection(final String Quote_No)	throws BaseException 
	{
		logger.info("singleSelection method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No };
		final String result[][] = runner.multipleSelection("select cover_id,product_suminsured from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and home_sno between "+1001+" and " +1003, args);
		logger.debug("- Exit");
		
		return result;
	}

	public String[][] getSummary(final String quoteNo) throws BaseException 
	{
		logger.info("getSummary method()");
		logger.debug("- Enter");
		final String args[] = { quoteNo };
		final String[][] summary = runner.multipleSelection("select home_sno,cover_id,Product_description,product_suminsured,premium,status,amend_id from home_coverage_transaction  where quote_no=? and amend_id='0'", args);
		logger.debug("- Exit");
		
		return summary;
	}

	public HashMap getLessThenRow(final String[][] pdfDetails)
	{
		final HashMap homePdfDetails = new HashMap();
		homePdfDetails.put("per.title", (pdfDetails[0][0] == null ? ""
				: pdfDetails[0][0]));
		homePdfDetails.put("per.first_name",
				(pdfDetails[0][1] == null ? "" : pdfDetails[0][1]));
		homePdfDetails.put("per.last_name",
				(pdfDetails[0][2] == null ? "" : pdfDetails[0][2]));
		homePdfDetails.put("per.dob", (pdfDetails[0][3] == null ? ""
				: pdfDetails[0][3]));
		homePdfDetails.put("per.gender", (pdfDetails[0][4] == null ? ""
				: pdfDetails[0][4]));
		homePdfDetails.put("per.telephone",
				(pdfDetails[0][5] == null ? "" : pdfDetails[0][5]));
		homePdfDetails.put("per.mobile", (pdfDetails[0][6] == null ? ""
				: pdfDetails[0][6]));
		homePdfDetails.put("per.fax", (pdfDetails[0][7] == null ? ""
				: pdfDetails[0][7]));
		homePdfDetails.put("per.email", (pdfDetails[0][8] == null ? ""
				: pdfDetails[0][8]));
		homePdfDetails.put("per.address1",
				(pdfDetails[0][9] == null ? "" : pdfDetails[0][9]));
		homePdfDetails.put("per.address2",
				(pdfDetails[0][10] == null ? "" : pdfDetails[0][10]));
		homePdfDetails.put("per.occupation",
				(pdfDetails[0][11] == null ? "" : pdfDetails[0][11]));
		homePdfDetails.put("per.pobox", (pdfDetails[0][12] == null ? ""
				: pdfDetails[0][12]));
		homePdfDetails.put("per.country",
				(pdfDetails[0][13] == null ? "" : pdfDetails[0][13]));
		homePdfDetails.put("per.emirate",
				(pdfDetails[0][14] == null ? "" : pdfDetails[0][14]));
		homePdfDetails.put("per.nationality",
				(pdfDetails[0][15] == null ? "" : pdfDetails[0][15]));
		homePdfDetails.put("pos.POLICY_NO",
				(pdfDetails[0][16] == null ? "" : pdfDetails[0][16]));
		homePdfDetails.put("pos.PREMIUM",
				(pdfDetails[0][17] == null ? "" : pdfDetails[0][17]));
		homePdfDetails.put("pos.EXPIRY_DATE",
				(pdfDetails[0][18] == null ? "" : pdfDetails[0][18]));
		homePdfDetails.put("pos.EFFECTIVE_DATE",
				(pdfDetails[0][19] == null ? "" : pdfDetails[0][19]));
		homePdfDetails.put("pos.DEBIT_NOTE_NO",
				(pdfDetails[0][20] == null ? "" : pdfDetails[0][20]));
		homePdfDetails.put("pos.DEBIT_NOTE_DATE",
				(pdfDetails[0][21] == null ? "" : pdfDetails[0][21]));
		homePdfDetails.put("bro.COMPANY_NAME",
				(pdfDetails[0][26] == null ? "" : pdfDetails[0][26]));
		homePdfDetails.put("bro.CONTACT_PERSON",
				(pdfDetails[0][27] == null ? "" : pdfDetails[0][27]));
		homePdfDetails.put("bro.ADDRESS1",
				(pdfDetails[0][28] == null ? "" : pdfDetails[0][28]));
		homePdfDetails.put("bro.ADDRESS2",
				(pdfDetails[0][29] == null ? "" : pdfDetails[0][29]));
		homePdfDetails.put("bro.ADDRESS3",
				(pdfDetails[0][30] == null ? "" : pdfDetails[0][30]));
		homePdfDetails.put("bro.CITY", (pdfDetails[0][31] == null ? ""
				: pdfDetails[0][31]));
		homePdfDetails.put("bro.COUNTRY",
				(pdfDetails[0][32] == null ? "" : pdfDetails[0][32]));
		homePdfDetails.put("bro.POBOX", (pdfDetails[0][33] == null ? ""
				: pdfDetails[0][33]));
		homePdfDetails.put("pos.PRODUCT_ID",
				(pdfDetails[0][34] == null ? "" : pdfDetails[0][34]));
		homePdfDetails.put("pos.ncd", (pdfDetails[0][35] == null ? "0"
				: pdfDetails[0][35]));
		homePdfDetails.put("pos.ncdval",
				(pdfDetails[0][36] == null ? "0" : pdfDetails[0][36]));
		homePdfDetails.put("per.companyName",
				(pdfDetails[0][37] == null ? "" : pdfDetails[0][37]));
		homePdfDetails.put("bro.AGENCY_CODE",
				(pdfDetails[0][38] == null ? "" : pdfDetails[0][38]));
		homePdfDetails.put("pos.status",
				(pdfDetails[0][39] == null ? "" : pdfDetails[0][39]));
		homePdfDetails.put("pos.bedrooms",
				(pdfDetails[0][40] == null ? "" : pdfDetails[0][40]));
		homePdfDetails.put("pos.loginId",
				(pdfDetails[0][42] == null ? "" : pdfDetails[0][42]));
		homePdfDetails.put("pos.APPROVED_BY",(pdfDetails[0][43] == null ? "Nil"	: pdfDetails[0][43]));
		homePdfDetails.put("pos.OVERALL_PREMIUM",(pdfDetails[0][44] == null ? "0"	: pdfDetails[0][44]));
		homePdfDetails.put("pos.EXCESS_PREMIUM",(pdfDetails[0][45] == null ? "0"	: pdfDetails[0][45]));
		homePdfDetails.put("pos.EXCESS_SIGN",(pdfDetails[0][46] == null ? "0"	: pdfDetails[0][46]));
		homePdfDetails.put("pos.cshIdTypCode",(pdfDetails[0][47]==null?"Nil":pdfDetails[0][47]));
		homePdfDetails.put("pos.AirmilesNo",(pdfDetails[0][48]==null?"Nil":pdfDetails[0][48]));
		int index;
		for (index = 0; index < pdfDetails.length; index++) {
			homePdfDetails
					.put("covtra.cover_id" + index,
							(pdfDetails[index][22] == null ? ""
									: pdfDetails[index][22]));
			homePdfDetails
					.put("covtra.Product_description" + index,
							(pdfDetails[index][23] == null ? ""
									: pdfDetails[index][23]));
			homePdfDetails
					.put("covtra.product_suminsured" + index,
							(pdfDetails[index][24] == null ? ""
									: pdfDetails[index][24]));
			homePdfDetails
					.put("covtra.premium" + index,
							(pdfDetails[index][25] == null ? ""
									: pdfDetails[index][25]));
		}
		homePdfDetails.put("covtra.length", Integer.toString(index));
		
		return homePdfDetails;
	}
	
	public HashMap getGreaterThenRow(final String[][] pdfDetails) throws BaseException
	{
		final HashMap homePdfDetails = new HashMap();
		homePdfDetails.put("per.title", (pdfDetails[0][0] == null ? ""
				: pdfDetails[0][0]));
		homePdfDetails.put("per.first_name",
				(pdfDetails[0][1] == null ? "" : pdfDetails[0][1]));
		homePdfDetails.put("per.last_name",
				(pdfDetails[0][2] == null ? "" : pdfDetails[0][2]));
		homePdfDetails.put("per.dob", (pdfDetails[0][3] == null ? ""
				: pdfDetails[0][3]));
		homePdfDetails.put("per.gender", (pdfDetails[0][4] == null ? ""
				: pdfDetails[0][4]));
		homePdfDetails.put("per.telephone",
				(pdfDetails[0][5] == null ? "" : pdfDetails[0][5]));
		homePdfDetails.put("per.mobile", (pdfDetails[0][6] == null ? ""
				: pdfDetails[0][6]));
		homePdfDetails.put("per.fax", (pdfDetails[0][7] == null ? ""
				: pdfDetails[0][7]));
		homePdfDetails.put("per.email", (pdfDetails[0][8] == null ? ""
				: pdfDetails[0][8]));
		homePdfDetails.put("per.address1",
				(pdfDetails[0][9] == null ? "" : pdfDetails[0][9]));
		homePdfDetails.put("per.address2",
				(pdfDetails[0][10] == null ? "" : pdfDetails[0][10]));
		homePdfDetails.put("per.occupation",
				(pdfDetails[0][11] == null ? "" : pdfDetails[0][11]));
		homePdfDetails.put("per.pobox", (pdfDetails[0][12] == null ? ""
				: pdfDetails[0][12]));
		homePdfDetails.put("per.country",
				(pdfDetails[0][13] == null ? "" : pdfDetails[0][13]));
		homePdfDetails.put("per.emirate",
				(pdfDetails[0][14] == null ? "" : pdfDetails[0][14]));
		homePdfDetails.put("per.nationality",
				(pdfDetails[0][15] == null ? "" : pdfDetails[0][15]));
		homePdfDetails.put("pos.POLICY_NO",
				(pdfDetails[0][16] == null ? "" : pdfDetails[0][16]));
		homePdfDetails.put("pos.PREMIUM",
				(pdfDetails[0][17] == null ? "" : pdfDetails[0][17]));
		homePdfDetails.put("pos.EXPIRY_DATE",
				(pdfDetails[0][18] == null ? "" : pdfDetails[0][18]));
		homePdfDetails.put("pos.EFFECTIVE_DATE",
				(pdfDetails[0][19] == null ? "" : pdfDetails[0][19]));
		homePdfDetails.put("pos.DEBIT_NOTE_NO",
				(pdfDetails[0][20] == null ? "" : pdfDetails[0][20]));
		homePdfDetails.put("pos.DEBIT_NOTE_DATE",
				(pdfDetails[0][21] == null ? "" : pdfDetails[0][21]));
		homePdfDetails.put("bro.COMPANY_NAME",
				(pdfDetails[0][22] == null ? "" : pdfDetails[0][22]));
		homePdfDetails.put("bro.CONTACT_PERSON",
				(pdfDetails[0][23] == null ? "" : pdfDetails[0][23]));
		homePdfDetails.put("bro.ADDRESS1",
				(pdfDetails[0][24] == null ? "" : pdfDetails[0][24]));
		homePdfDetails.put("bro.ADDRESS2",
				(pdfDetails[0][25] == null ? "" : pdfDetails[0][25]));
		homePdfDetails.put("bro.ADDRESS3",
				(pdfDetails[0][26] == null ? "" : pdfDetails[0][26]));
		homePdfDetails.put("bro.CITY", (pdfDetails[0][27] == null ? ""
				: pdfDetails[0][27]));
		homePdfDetails.put("bro.COUNTRY",
				(pdfDetails[0][28] == null ? "" : pdfDetails[0][28]));
		homePdfDetails.put("bro.POBOX", (pdfDetails[0][29] == null ? ""
				: pdfDetails[0][29]));
		homePdfDetails.put("pos.PRODUCT_ID",
				(pdfDetails[0][30] == null ? "" : pdfDetails[0][30]));
		homePdfDetails.put("per.companyName",
				(pdfDetails[0][31] == null ? "" : pdfDetails[0][31]));
		homePdfDetails.put("bro.AGENCY_CODE",
				(pdfDetails[0][32] == null ? "" : pdfDetails[0][32]));
		homePdfDetails.put("pos.status",
				(pdfDetails[0][33] == null ? "" : pdfDetails[0][33]));
		homePdfDetails.put("pos.bedrooms",
				(pdfDetails[0][34] == null ? "" : pdfDetails[0][34]));
		return homePdfDetails;
	}
	
	public HashMap getHomePdfDetails(final String quoteNo, final String loginId)throws BaseException 
	{
		logger.info("getHomePdfDetails method()");
		logger.debug("- Enter");
		String[][] pdfDetails;
		HashMap homePdfDetails = new HashMap();
		String sql;
		int rows = 0;
		final String args1[] = { loginId };
		final String temp = runner.singleSelection("select count(*) from HOME_POSITION_MASTER where login_id=? and quote_no in("+quoteNo + ") and scheme_id is not null", args1);
		if (temp.length() > 0){
			rows = Integer.parseInt(temp);
		}
		
		if (rows <= 0) {
			sql = "select per.title,per.first_name,nvl(per.last_name,' '),to_char(per.dob,'DD-MM-YYYY'),per.gender,per.telephone,per.mobile,per.fax,per.email,per.address1,per.address2,initcap(per.occupation),per.pobox,initcap(per.country),per.emirate,initcap(per.nationality),pos.POLICY_NO,pos.PREMIUM,to_char(pos.EXPIRY_DATE,'dd/mm/yyyy')," +
					"to_char(pos.EFFECTIVE_DATE,'dd/mm/yyyy'),pos.DEBIT_NOTE_NO,to_char(pos.DEBIT_NOTE_DATE,'dd/mm/yyyy'),covtra.cover_id,covtra.Product_description,covtra.product_suminsured,covtra.premium,bro.COMPANY_NAME," +
					"bro.CONTACT_PERSON,bro.ADDRESS1,bro.ADDRESS2,bro.ADDRESS3,bro.CITY,bro.EMIRATE,bro.POBOX,pos.PRODUCT_ID,pos.NO_CLAIM_DISCOUNT,pos.NO_CLAIM_DISCOUNT_VALUE,per.COMPANY_NAME ," +
					"bro.AGENCY_CODE,pos.status,pos.BED_ROOM,nvl(bro.PHONE,'0'),pos.login_id,nvl(pos.APPROVED_BY,'Nil'),nvl(OVERALL_PREMIUM,'0'),EXCESS_PREMIUM,EXCESS_SIGN,nvl(pos.csh_id_typ_code,'0'),nvl(pos.airmiles_no,' ') from PERSONAL_INFO per, HOME_POSITION_MASTER pos, HOME_COVERAGE_TRANSACTION covtra,BROKER_COMPANY_MASTER bro where per.customer_id = pos.customer_id  and pos.quote_no=? and covtra.amend_id='0' and covtra.home_sno between "
					+ 1001
					+ " and "
					+ 1006
					+ " and covtra.quote_no=? and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=?) and covtra.product_suminsured!=0 order by covtra.cover_id";
			final String args[] = { quoteNo, quoteNo, loginId};
			pdfDetails = runner.multipleSelection(sql, args);
		
		} else {
			sql = "select per.title,(per.first_name||per.company_name),nvl(per.last_name,' '),to_char(per.dob,'DD-MM-YYYY'),per.gender,per.telephone,per.mobile,per.fax,per.email,per.address1,per.address2,initcap(per.occupation),per.pobox,initcap(per.country),per.emirate,initcap(per.nationality),pos.POLICY_NO," +
					"pos.PREMIUM,to_char(pos.EXPIRY_DATE,'dd/mm/yyyy'),to_char(pos.EFFECTIVE_DATE,'dd/mm/yyyy'),pos.DEBIT_NOTE_NO,to_char(pos.DEBIT_NOTE_DATE,'dd/mm/yyyy'),bro.COMPANY_NAME,bro.CONTACT_PERSON,bro.ADDRESS1,bro.ADDRESS2,bro.ADDRESS3,bro.CITY,bro.EMIRATE,bro.POBOX,pos.PRODUCT_ID,per.COMPANY_NAME ," +
					"bro.AGENCY_CODE,pos.status,nvl(bro.PHONE,'0'),pos.login_id,nvl(pos.APPROVED_BY,'Nil'),nvl(OVERALL_PREMIUM,'0'),EXCESS_PREMIUM,EXCESS_SIGN,nvl(pos.csh_id_typ_code,'0'),nvl(pos.airmiles_no,' ') from PERSONAL_INFO per, HOME_POSITION_MASTER pos,BROKER_COMPANY_MASTER bro where per.customer_id = pos.customer_id and pos.quote_no in("
					+ quoteNo
					+ ") and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=?)";
			final String args[] ={ loginId };
			pdfDetails = runner.multipleSelection(sql, args);
		
		}
		if (rows <= 0 && pdfDetails.length > 0) {
			homePdfDetails = getLessThenRow(pdfDetails);
		} // Rows if
		else if (pdfDetails.length > 0) {
			homePdfDetails = getGreaterThenRow(pdfDetails);
		}
		
		logger.debug("- Exit");
		
		return homePdfDetails;
	}

	public HashMap getOfficePdfDetails(final String quoteNo,final String loginId) throws BaseException 
	{
		logger.info("getOfficePdfDetails method()");
		logger.debug("- Enter");
		final HashMap homePdfDetails = new HashMap();
		final String args[] = { loginId };
		String sql;
		String[][] pdfDetails;
		double totPremium = 0.0;
		sql = "select per.title,(per.first_name||per.company_name),nvl(per.last_name,' '),to_char(per.dob,'DD-MM-YYYY'),per.gender,per.telephone,per.mobile,per.fax,per.email,per.address1,per.address2,initcap(per.occupation),per.pobox,initcap(per.country),per.emirate,initcap(per.nationality),pos.POLICY_NO,pos.PREMIUM,to_char(pos.EXPIRY_DATE,'dd/mm/yyyy'),to_char(pos.EFFECTIVE_DATE,'dd/mm/yyyy'),pos.DEBIT_NOTE_NO,to_char(pos.DEBIT_NOTE_DATE,'dd/mm/yyyy'),bro.COMPANY_NAME,bro.CONTACT_PERSON,bro.ADDRESS1,bro.ADDRESS2,bro.ADDRESS3,bro.CITY,bro.EMIRATE,bro.POBOX,pos.PRODUCT_ID,per.COMPANY_NAME ,bro.AGENCY_CODE,pos.status,nvl(bro.PHONE,'0') from PERSONAL_INFO per, HOME_POSITION_MASTER pos,BROKER_COMPANY_MASTER bro where per.customer_id = pos.customer_id and pos.policy_no in(select policy_no from home_position_master where quote_no in ("
				+ quoteNo
				+ ") )and bro.AGENCY_CODE in(select log.oa_code from LOGIN_MASTER log where log.login_id=?)";
		pdfDetails = runner.multipleSelection(sql, args);
		if (pdfDetails.length > 0) {
			homePdfDetails.put("per.title", (pdfDetails[0][0] == null ? ""
					: pdfDetails[0][0]));
			homePdfDetails.put("per.first_name", (pdfDetails[0][1] == null ? ""
					: pdfDetails[0][1]));
			homePdfDetails.put("per.last_name", (pdfDetails[0][2] == null ? ""
					: pdfDetails[0][2]));
			homePdfDetails.put("per.dob", (pdfDetails[0][3] == null ? ""
					: pdfDetails[0][3]));
			homePdfDetails.put("per.gender", (pdfDetails[0][4] == null ? ""
					: pdfDetails[0][4]));
			homePdfDetails.put("per.telephone", (pdfDetails[0][5] == null ? ""
					: pdfDetails[0][5]));
			homePdfDetails.put("per.mobile", (pdfDetails[0][6] == null ? ""
					: pdfDetails[0][6]));
			homePdfDetails.put("per.fax", (pdfDetails[0][7] == null ? ""
					: pdfDetails[0][7]));
			homePdfDetails.put("per.email", (pdfDetails[0][8] == null ? ""
					: pdfDetails[0][8]));
			homePdfDetails.put("per.address1", (pdfDetails[0][9] == null ? ""
					: pdfDetails[0][9]));
			homePdfDetails.put("per.address2", (pdfDetails[0][10] == null ? ""
					: pdfDetails[0][10]));
			homePdfDetails.put("per.occupation",
					(pdfDetails[0][11] == null ? "" : pdfDetails[0][11]));
			homePdfDetails.put("per.pobox", (pdfDetails[0][12] == null ? ""
					: pdfDetails[0][12]));
			homePdfDetails.put("per.country", (pdfDetails[0][13] == null ? ""
					: pdfDetails[0][13]));
			homePdfDetails.put("per.emirate", (pdfDetails[0][14] == null ? ""
					: pdfDetails[0][14]));
			homePdfDetails.put("per.nationality",
					(pdfDetails[0][15] == null ? "" : pdfDetails[0][15]));
			homePdfDetails.put("pos.POLICY_NO", (pdfDetails[0][16] == null ? ""
					: pdfDetails[0][16]));

			String temp;
			for (int j = 0; j < pdfDetails.length; j++) {
				temp = pdfDetails[j][17] == null ? "0" : pdfDetails[j][17];
				totPremium = totPremium + Double.parseDouble(temp);
			}
			homePdfDetails.put("pos.PREMIUM", Double.toString(totPremium));

			homePdfDetails.put("pos.EXPIRY_DATE",
					(pdfDetails[0][18] == null ? "" : pdfDetails[0][18]));
			homePdfDetails.put("pos.EFFECTIVE_DATE",
					(pdfDetails[0][19] == null ? "" : pdfDetails[0][19]));
			homePdfDetails.put("pos.DEBIT_NOTE_NO",
					(pdfDetails[0][20] == null ? "" : pdfDetails[0][20]));
			homePdfDetails.put("pos.DEBIT_NOTE_DATE",
					(pdfDetails[0][21] == null ? "" : pdfDetails[0][21]));
			homePdfDetails.put("bro.COMPANY_NAME",
					(pdfDetails[0][22] == null ? "" : pdfDetails[0][22]));
			homePdfDetails.put("bro.CONTACT_PERSON",
					(pdfDetails[0][23] == null ? "" : pdfDetails[0][23]));
			homePdfDetails.put("bro.ADDRESS1", (pdfDetails[0][24] == null ? ""
					: pdfDetails[0][24]));
			homePdfDetails.put("bro.ADDRESS2", (pdfDetails[0][25] == null ? ""
					: pdfDetails[0][25]));
			homePdfDetails.put("bro.ADDRESS3", (pdfDetails[0][26] == null ? ""
					: pdfDetails[0][26]));
			homePdfDetails.put("bro.CITY", (pdfDetails[0][27] == null ? ""
					: pdfDetails[0][27]));
			homePdfDetails.put("bro.COUNTRY", (pdfDetails[0][28] == null ? ""
					: pdfDetails[0][28]));
			homePdfDetails.put("bro.POBOX", (pdfDetails[0][29] == null ? ""
					: pdfDetails[0][29]));
			homePdfDetails.put("pos.PRODUCT_ID",
					(pdfDetails[0][30] == null ? "" : pdfDetails[0][30]));
			homePdfDetails.put("per.companyName",
					(pdfDetails[0][31] == null ? "" : pdfDetails[0][31]));
			homePdfDetails.put("bro.AGENCY_CODE",
					(pdfDetails[0][32] == null ? "" : pdfDetails[0][32]));
			homePdfDetails.put("pos.status", (pdfDetails[0][33] == null ? ""
					: pdfDetails[0][33]));
			homePdfDetails.put("pos.bedrooms", (pdfDetails[0][34] == null ? ""
					: pdfDetails[0][34]));
		}
		logger.debug("- Exit");
		
		return homePdfDetails;
	}

	public String getCommision(final String loginId, final String pid)	throws BaseException 
	{
		logger.info("getCommision method()");
		logger.debug("- Enter");
		final String args[] = { loginId, pid };
		String commision = runner.singleSelection("select commission  from login_user_details where agency_code=(select oa_code from login_master where login_id=? and status='Y') and status='Y' and product_id=?", args);
		String result;
		if (commision.length() > 0){
			result = commision;
		}else{
			result =  "0";
		}
		logger.debug("- Exit");
		
		return result;
	}

	public int updateBrokerStatus(final String quoteNo) throws BaseException {
		logger.info("updateBrokerStatus method()");
		logger.debug("- Enter");
		int status;
		final String args[] = { quoteNo };
		String temp = runner.singleSelection("select nvl(max(Pdf_Broker_Status),0) from Home_Position_Master where QUOTE_NO=?", args);
		if (temp.length() > 0){
			status = Integer.parseInt(temp);
		}else{
			status = 0;
		}
		final String args1[] = { Integer.toString((status + 1)), quoteNo };
		runner.multipleUpdation("update Home_Position_Master set Pdf_Broker_Status=? where QUOTE_NO=?", args1);
		logger.debug("- Exit");
		
		return status;
	}

	public String getBuildingLocation(final String quoteNo)	throws BaseException 
	{
		logger.info("getBuildingLocation method()");
		logger.debug("- Enter");
		String buildLoc = "";
		String result[][];
		final String args[] = { quoteNo };
		result = runner
				.multipleSelection(
						"select FLAT_VILLA_NO,BUILDING_NAME,STREET_NAME,EMIRATE from HOME_COVERAGE_BUILDING where quote_no=? and cover_id=1",
						args);
		if (result.length <= 0){
			buildLoc = "";
		} else if (result.length > 0) {
			buildLoc = CommonHelp.notNullCheck(result[0][0])+ ", "
					+ CommonHelp.notNullCheck(result[0][1]) + ", "
					+ CommonHelp.notNullCheck(result[0][2]) + ", "
					+ CommonHelp.notNullCheck(result[0][3]) + ", U A E.";
			buildLoc = buildLoc.trim();
		}
		logger.debug("- Exit");
		
		return buildLoc;
	}

	public String[][] getDomesticInfo(final String quoteNo)	throws BaseException 
	{
		logger.info("getDomesticInfo method()");
		logger.debug("- Enter");
		String domestic[][];
		final String args[] = { quoteNo };
		domestic = runner.multipleSelection("select NAME,to_char(DATE_OF_BIRTH,'DD-MM-YYYY') from HOME_DOMESTIC_STAFF where quote_no=? order by DOMESTIC_STAFF_SNO",args);
		logger.debug("- Exit");
		
		return domestic;
	}

	public boolean getDomesticStatus(final String quoteNo, final String covId)throws BaseException 
	{
		logger.info("getDomesticStatus method()");
		logger.debug("- Enter");
		final String args[] = { quoteNo, covId };
		final String result = runner.singleSelection("select PRODUCT_SUMINSURED from HOME_COVERAGE_TRANSACTION where QUOTE_NO =? and COVER_ID = ?", args);
		boolean returnResult = false;
		if (result.equalsIgnoreCase("1")){
			returnResult = true;
		}else if (result.equalsIgnoreCase("0")){
			returnResult = false;
		}
		logger.debug("- Exit");
		
		return returnResult;
	}

	public boolean getCoverageStatus(final String quoteNo, final String covId)throws BaseException 
	{
		logger.info("getCoverageStatus method()");
		logger.debug("- Enter");
		final String args[] = { quoteNo, covId };
		final String result = runner.singleSelection("select count(*) from HOME_COVERAGE_TRANSACTION where QUOTE_NO =? and COVER_ID = ?", args);
		boolean returnResult;
		if (result.equalsIgnoreCase("0")){
			returnResult =  false;
		}else{
			returnResult = true;
		}
		logger.debug("- Exit");
		
		return returnResult;
	}

	public String[][] getExcessandSign(final String qno) throws BaseException 
	{
		logger.info("getExcessandSign method()");
		logger.debug("- Enter");
		final String[][] result = runner.multipleSelection("select EXCESS_PREMIUM,EXCESS_SIGN from HOME_POSITION_MASTER where QUOTE_NO in ("+qno+")");
		logger.debug("- Exit");
		
		return result;
	}

	public String getClauses(final String qno) throws BaseException 
	{
		logger.info("getClauses method()");
		logger.debug("- Enter");
		final String args[] = { qno };
		String res = runner.singleSelection("select SUMMARY_CLAUSES from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		if (res == null){
			res = "";
		}else{
			res = res.trim();
		}
		logger.debug("- Exit");
		
		return res;
	}

	public String[][] getClauses(final String qno, final String user)throws BaseException 
	{
		logger.info("getClauses method()");
		logger.debug("- Enter");
		final String args[] = { qno, user };
		final String res[][] = runner.multipleSelection("select pos.SUMMARY_CLAUSES,log.referal,pos.ADMIN_REMARKS,nvl(pos.SUMMARY_REMARKS,' '),nvl(pos.ADMIN_SUMMARY_STATUS,' ') from HOME_POSITION_MASTER pos, login_master log where pos.QUOTE_NO=? and log.agency_code=(select oa_code from login_master where login_id=?)", args);
		logger.debug("- Exit");
		
		return res;
	}

	public String getRemarkss(final String qno) throws BaseException 
	{
		logger.info("getRemarkss method()");
		logger.debug("- Enter");
		final  String args[] = { qno };
		String res = runner.singleSelection("select SUMMARY_REMARKS from HOME_POSITION_MASTER where QUOTE_NO=?", args);
		if (res == null){
			res = "";
		}else{
			res = res.trim();
		}
		logger.debug("- Exit");
		
		return res;
	}

	public String[][] getFinanceDetails(final String qno) throws BaseException 
	{
		logger.info("getFinanceDetails method()");
		logger.debug("- Enter");
		final String args[] = { qno };
		final String[][] result = runner.multipleSelection("select FINANCE_COMPANY_NAME,LOAN_AMOUNT,FINANCE_TELEPHONE,POBOX from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID not in(1) and (FINANCE_COMPANY_NAME is not null or LOAN_AMOUNT is not null)", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String[][] getFinanceBankName(final String qno) throws BaseException
	{
		logger.info("getFinanceBankName method()");
		logger.debug("- Enter");
		final String[][] result = runner.multipleSelection("select FINANCE_COMPANY_NAME from HOME_COVERAGE_BUILDING where QUOTE_NO in ("+qno+") and COVER_ID not in(1) and FINANCE_COMPANY_NAME is not null");
		logger.debug("- Exit");
		
		
		return result;
	}

	public String getBankName(final String qno) throws BaseException {
		logger.info("getBankName method()");
		logger.debug("- Enter");
		final String args[] = { qno };
		final String result = runner.singleSelection("select FINANCE_COMPANY_NAME from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID=2 and FINANCE_COMPANY_NAME is not null", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String getBrokerMailId(final String agency_code) throws BaseException {
		logger.info("getBrokerMailId method()");
		logger.debug("- Enter");
		final String args[] = { agency_code, agency_code };
		final String result = runner.singleSelection("select email from personal_info where agency_code=? and oa_code =?", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String getAdminBrokerMailId(final String qno) throws BaseException {
		logger.info("getAdminBrokerMailId method()");
		logger.debug("- Enter");
		String broker;
		String admin;
		String mailIds;
		final String args[] = { qno };
		broker = runner.singleSelection("select distinct(email) from personal_info where agency_code = (select agency_code from login_master where login_id = (select login_id from home_position_master where quote_no = ?))", args);
		admin = runner.singleSelection("select email_to from mail_details where mail_no =14");
		mailIds = admin + "," + broker;
		logger.debug("- Exit");
		
		return mailIds;
	}

	public String getPDFStatus(final String quoteNo) throws BaseException {
		logger.info("getPDFStatus method()");
		logger.debug("- Enter");
		final int pdfStatus = updateBrokerStatus(quoteNo);
		String resStatus;
		if (pdfStatus == 0){
			resStatus = "";
		}else if (pdfStatus == 1){
			resStatus = "Original Copy";
		}else if (pdfStatus == 2){
			resStatus = "Copy";
		}else{
			resStatus = "Copy";
		}
		logger.debug("- Exit");
		
		return resStatus;
	}

	public String getBrokerBranchName(final String qno) throws BaseException 
	{
		logger.info("getBrokerBranchName method()");
		logger.debug("- Enter");
		String broker;
		final String args[] = { qno };
		broker = runner.singleSelection("select BRANCH_NAME from BRANCH_MASTER where BRANCH_CODE=(select BRANCH_CODE from BROKER_COMPANY_MASTER where AGENCY_CODE in(select log.OA_CODE from LOGIN_MASTER log,HOME_POSITION_MASTER home where quote_no=? and log.LOGIN_ID=home.LOGIN_ID))", args);
		logger.debug("- Exit");
		
		return broker;
	}

	public String[][] brokerCompanyMasterSelectForMail(final String loginId)throws BaseException {
		logger.info("brokerCompanyMasterSelectForMail method()");
		logger.debug("- Enter");
		final String args[] = { loginId };
		final String result[][] = runner.multipleSelection("select COMPANY_NAME,ADDRESS1,ADDRESS2,ADDRESS3,CITY,initcap(COUNTRY),POBOX,CONTACT_PERSON,PHONE,agency_code from BROKER_COMPANY_MASTER where AGENCY_CODE in(select oa_code from LOGIN_MASTER where login_id=?)", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String[][] paymentDetails(final String qno, final String proId) throws BaseException 
	{
		logger.info("paymentDetails method()");
		logger.debug("- Enter");
		String args[] = { qno, proId };
		final String result[][] = runner.multipleSelection("select MERCHTXNREF_POLICY_NO,AMOUNT_PREMIUM,RECEIPTNO,TRANSACTIONNO,CARDTYPE from payment where MERCHTXNREF_POLICY_NO=? and product_id=?", args);
		logger.debug("- Exit");
		
		return result;
	}

	public String getQuotedName(final String login) throws BaseException {
		logger.info("getQuotedName method()");
		logger.debug("- Enter");
		final String args[] = { login , login };
		String res = "";
		final String result[][] = runner.multipleSelection("select nvl(company_name,first_name),application_id from personal_info where login_id=? and agency_code=(select agency_code from login_master where login_id=?)", args);
		if (result.length > 0) {
			if (result[0][1].equalsIgnoreCase("2")){
				res = "";
			}else{
				res = result[0][0];
			}
		}
		logger.debug("- Exit");
		
		return res;
	}

	public String getReceiptNoNew(final String quoteno, final String ProductId,final String branch, final String paymentmode)throws BaseException 
	{
		logger.info("getReceiptNo method()");
		logger.debug("- Enter");
		final String[] args = {quoteno};
		com.maan.common.Customer.DataSelect dataSelection = new com.maan.common.Customer.DataSelect();
		com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
		final String hour = data.getSysdateTime(quoteno, "QuoteNo");
		String receiptNo;
		receiptNo = runner.singleSelection("select nvl(receipt_no,' ') from home_position_master where quote_no= ? " +
				"and receipt_no is not null",args);
		if (receiptNo.trim().equalsIgnoreCase("") || receiptNo.length() <= 0) {
			receiptNo = dataSelection.getMaxReceiptNo(ProductId,branch);
			String[] args1 = { receiptNo, paymentmode,quoteno };
			runner.multipleUpdation("update home_position_master set receipt_no = ? ,receipt_date=(select "
							+ hour + " from dual),payment_mode=? where quote_no = ?", args1);
		}
		logger.debug("- Exit");
		
		return receiptNo;
	}
	
	public String getReceiptNo(final String quoteno, final String ProductId,final String branch)throws BaseException 
	{
		logger.info("getReceiptNo method()");
		logger.debug("- Enter");
		final String[] args = {quoteno};
		com.maan.common.Customer.DataSelect dataSelection = new com.maan.common.Customer.DataSelect();
		com.maan.Home.DataManipualtion.DataSelect data = new com.maan.Home.DataManipualtion.DataSelect();
		final String hour = data.getSysdateTime(quoteno, "QuoteNo");
		String receiptNo;
		receiptNo = runner.singleSelection("select nvl(receipt_no,' ') from home_position_master where quote_no= ? " +
				"and receipt_no is not null",args);
		if (receiptNo.trim().equalsIgnoreCase("") || receiptNo.length() <= 0) {
			receiptNo = dataSelection.getMaxReceiptNo(ProductId,branch);
			String[] args1 = { receiptNo, quoteno };
			runner.multipleUpdation("update home_position_master set receipt_no = ? ,receipt_date=(select "
							+ hour + " from dual) where quote_no = ?", args1);
		}
		logger.debug("- Exit");
		
		return receiptNo;
	}
	public String[][] getReceiptDetails(final String quoteno)throws BaseException{
		logger.info("getReceiptDetails method()");
		logger.debug("- Enter");
		final String args[] = {quoteno};
		final String result[][] = runner.multipleSelection("select RECEIPT_NO,to_char(RECEIPT_DATE,'dd-mm-yyyy'),nvl(PAYMENT_MODE,'N'),nvl(OVERALL_PREMIUM,'0') from HOME_POSITION_MASTER where quote_no=?", args);
		logger.debug("- Exit");
		
		return result;
	}
	public String[][] getPlaceCodes(final String brokerBra, final String pid)
			throws BaseException {
		logger.info("getPlaceCodes method()");
		logger.debug("- Enter");
		final String args[] ={ brokerBra , pid};
		final String result[][] = runner
				.multipleSelection(
						"select bm.BRANCH_NAME,bd.header_img,bd.footer_img,bd.sign_img,bd.stamp_img,bm.Currency_name,bm.CURRENCY_ABBREVIATION,nvl(bm.CURRENCY_ACRONYM,' '),bm.ADDRESS1,bm.REMARKS,bm.ADDRESS2,bm.CITY,bm.COUNTRY,bm.PHONE,bm.FAX,bm.ADDRESS3 from BRANCH_MASTER bm,BRANCH_DETAIL bd where bm.BRANCH_CODE=? and bd.product_id=? and bm.BRANCH_CODE=bd.BRANCH_CODE",
						args);
		logger.debug("- Exit");
		
		return result;
	}
	
	public String[][] getPDFDetails(final String qno) throws BaseException {
		logger.info("getPDFDetails method()");
		logger.debug(ENTER);
		final String args[] = {qno};
		final String result[][] = runner.multipleSelection("select to_char(EFFECTIVE_DATE,'dd'),to_char(EFFECTIVE_DATE,'mm'),to_char(EFFECTIVE_DATE,'yyyy'),to_char(EXPIRY_DATE,'dd-mm-yyyy') from HOME_POSITION_MASTER where quote_no=?",
						args);
		logger.debug(EXIT);
			
		return result;
	}
	public String[][] getAdminPlaceCodes(final String brokerBra,final String pid) throws BaseException {
		logger.info("getAdminPlaceCodes method()");
		logger.debug(ENTER);
			String result[][] = new String[0][0];
			String args[] = new String[1];
			args[0] = brokerBra;
			result = runner.multipleSelection("select bm.BRANCH_NAME,bd.header_img,bd.footer_img,bd.sign_img,bd.stamp_img,bm.Currency_name,bm.CURRENCY_ABBREVIATION,nvl(bm.CURRENCY_ACRONYM,' '),bm.ADDRESS1,bm.REMARKS,bm.ADDRESS2,bm.CITY,bm.COUNTRY,bm.PHONE,bm.FAX,bm.ADDRESS3 from BRANCH_MASTER bm,BRANCH_DETAIL bd where bm.BRANCH_CODE=? and product_id not in(3,11) and bm.BRANCH_CODE=bd.BRANCH_CODE",args);
		logger.debug(EXIT);
			
		return result;
	}
	
	public String getmodeOfPurchase(String qno)throws BaseException {
		logger.info("getmodeOfPurchase method()");
		logger.debug(ENTER);
		final String sql = "select nvl(payment_mode,'Cheque') from HOME_POSITION_MASTER where quote_no='"+qno+"'";
		String args[] = {qno};
		final String mode = runner.singleSelection(sql,args);
		logger.debug(EXIT);
			
		return mode;
	}
}// Class
