/**
 *
 *  Author  : Rajesh R  [13/08/2007]
 *	Company : MaanSarovar Software Private Limited  Chennai-1
 *	Project : E-MarineInsurance
 *  Purpose : This Bean To Manipulate The Date
 *
 */
package com.maan.Home.DataManipualtion;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class DataManipualtion 
{
	private static final Logger logger = LogUtil.getLogger(DataManipualtion.class);
	public void homeCoverageTotalTransactionDelete(final String quoteNo,final String CoverId) throws BaseException 
	{
		logger.info("homeCoverageTotalTransactionDelete method()");
		logger.debug("- Enter");
		final String args[] = { quoteNo, CoverId };
		runner.multipleUpdation("delete from HOME_COVERAGE_TRANSACTION where quote_no=? and home_sno<999 and cover_id=?", args);
		logger.debug("- Exit");
		
	}

	public void homeCoverageTotalTransaction(final String quoteNo,final String CoverId, final HashMap SumDetails) throws BaseException 
	{
		logger.info("homeCoverageTotalTransaction method()");
		logger.debug("- Enter");
		String sql;
		String homeSno;
		final String REMARKS = "";
		final String STATUS = "Y";
		String updateOrInsert = "0";
		final int countval = (SumDetails.size() + 1) / 3;
		for (int i = 0; i < countval; i++) {
			homeSno = Integer.toString(i);
			String SNo = (String) SumDetails.get("SNo" + i);
			String Item = (String) SumDetails.get("Item" + i);
			String SumInsured = (String) SumDetails.get("SumInsured" + i);
			final String args1[] = { quoteNo, homeSno, CoverId };
			updateOrInsert = runner.singleSelection("select count(*) from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and HOME_SNO=? and COVER_ID=?", args1);

			if (updateOrInsert.equals("0"))
			{
				sql = "insert into HOME_COVERAGE_TRANSACTION(QUOTE_NO,HOME_SNO,COVER_ID,PRODUCT_SERIAL_NO,PRODUCT_DESCRIPTION,PRODUCT_SUMINSURED,REMARKS,STATUS,AMEND_ID) values (?,?,?,?,?,?,?,?,'1')";
				final String args[] = { quoteNo, homeSno, CoverId, SNo, Item,
						SumInsured, REMARKS, STATUS };
				runner.multipleInsertion(sql, args);
			} else {
				sql = "update  HOME_COVERAGE_TRANSACTION set   PRODUCT_SERIAL_NO=?,PRODUCT_DESCRIPTION=?, PRODUCT_SUMINSURED=?, REMARKS=?, STATUS=? where QUOTE_NO=? and HOME_SNO=? and COVER_ID=?";
				final String args[] = { SNo, Item, SumInsured, REMARKS, STATUS,
						quoteNo, homeSno, CoverId };
				runner.multipleUpdation(sql, args);
			}
		}
		logger.debug("- Exit");
		
	}

	public String checkDate(final String strDate) throws BaseException
	{
		logger.info("checkDate method()");
		logger.debug("- Enter");
		String returnVal = null;
		final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		final ParsePosition pos = new ParsePosition(0);
		// String strDate = " 12/13/2003";
		Date date = df.parse(strDate, pos);
		// Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			logger.info("Error: " + pos.getIndex());
			if (date == null) {
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				// logger.info("Error index: " + pos.getErrorIndex());
				return "Invalid";
			}
			return "Invalid";
		}
		logger.debug("- Exit");
		
		return returnVal;
	}

	public void homeCoverageBuildingInitial(final String Quote_No,final String Amend_Id, final HashMap Coverage) throws BaseException 
	{
		logger.info("homeCoverageBuildingInitial method()");
		logger.debug("- Enter");
		String CoverageId;
		String CoverageName;
		String CoverageValue;
		String sql;
		final String STATUS = "Y";
		String CheckUpdateOrInsert = "0";
		String s = "";
		int covId = 0;
		for (int i = 0; i < Coverage.size(); i = i + 3) {
			CoverageId = (String) Coverage.get("CoverageDetails" + i);
			CoverageName = (String) Coverage.get("CoverageDetails" + (i + 1));

			final String option = (String) Coverage.get("CoverageDetails"
					+ (i + 2));
			covId = Integer.parseInt(CoverageId);

			if (covId <= 3)
				CoverageValue = (String) Coverage.get("CoverageDetails"
						+ (i + 2));
			else if (covId > 3 && covId < 7) {

				if (option.equalsIgnoreCase("N"))
					CoverageValue = "0";
				else if (option.equalsIgnoreCase("Y"))
					CoverageValue = "1";
				else
					CoverageValue = "";
			} else if (covId == 7) {
				if (option.equalsIgnoreCase("Rented"))
					CoverageValue = "1";
				else if (option.equalsIgnoreCase("Owner"))
					CoverageValue = "2";
				else
					CoverageValue = "0";
			} else
				CoverageValue = (String) Coverage.get("CoverageDetails"
						+ (i + 2));
			s = s + "," + CoverageId;

			int coverIdNew = 1000;
			coverIdNew = coverIdNew + Integer.parseInt(CoverageId);

			String args1[] = { Quote_No, CoverageId, Amend_Id };
			CheckUpdateOrInsert = runner.singleSelection("select count(*) from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and COVER_ID=? and AMEND_ID=?", args1);

			if (CheckUpdateOrInsert.equals("0")) {
				sql = "insert into HOME_COVERAGE_TRANSACTION(QUOTE_NO,HOME_SNO,COVER_ID,PRODUCT_DESCRIPTION,PRODUCT_SUMINSURED,STATUS,AMEND_ID) values (?,?,?,?,?,?,?)";

				String args[] = { Quote_No, Integer.toString(coverIdNew),
						CoverageId, CoverageName, CoverageValue, STATUS,
						Amend_Id };
				runner.multipleInsertion(sql, args);
			} else {
				sql = "update  HOME_COVERAGE_TRANSACTION set PRODUCT_DESCRIPTION=?, PRODUCT_SUMINSURED=?, STATUS=? where QUOTE_NO=? and COVER_ID=? and AMEND_ID=?";
				String args[] = { CoverageName, CoverageValue, STATUS,
						Quote_No, CoverageId, Amend_Id };
				runner.multipleUpdation(sql, args);
			}
		}

		if (s.length() > 0){
			s = s.substring(1, s.length());
		}
		String args[] = { Quote_No, Amend_Id };
		runner.multipleUpdation("delete from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and COVER_ID not in ("+s+") and AMEND_ID=?", args);
		logger.debug("- Exit");
		
	}

	public static String getMaxCustomerId(final String loginBra,final String pids) throws BaseException 
	{
		logger.info("getMaxCustomerId method()");
		logger.debug("- Enter");
		String current_no = null;
		String args[] = { loginBra, pids, loginBra, pids };
		current_no = runner
				.singleSelection(
						"select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and BRANCH_CODE=? and PRODUCT_ID=?",
						args);

		String args1[] = { current_no, current_no, loginBra, pids, loginBra, pids };
		runner
				.multipleUpdation(
						"update policyno_generate set current_no=?,remarks=? where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and BRANCH_CODE=? and PRODUCT_ID=?",
						args1);
		logger.debug("- Exit");
		
		return current_no;
	}

	public String validString(String value, final boolean format)throws BaseException 
	{
		logger.info("validString method()");
		logger.debug("- Enter");
		String returnval = null;
		value = value.trim();
		if (value.length() <= 0){
			returnval = "needed";
		}
		logger.debug("- Exit");
		
		return returnval;
	}

	public String validLength(String value, final int len) throws BaseException 
	{
		logger.info("validLength method()");
		logger.debug("- Enter");
		String returnval = null;
		value = value.trim();
		if (value.length() < len) {
			returnval = "needed";
		}
		logger.debug("- Exit");
		
		return returnval;
	}

	// For Insured Location Details
	public void homeCoverageBuildingTransaction(final String Quote_No,final String buildingName, final String buildingStreet,final String buildingFlat, final String buildingEmirate)	throws BaseException 
	{
		logger.info("homeCoverageBuildingTransaction method()");
		logger.debug("- Enter");
		String sql;
		String REMARKS = "";
		String STATUS = "Y";
		String CheckUpdateOrInsert = "0";
		String Cover_Id = "1";
		String args1[] = { Quote_No };
		CheckUpdateOrInsert = runner.singleSelection("select count(*) from HOME_COVERAGE_BUILDING where QUOTE_NO=? and cover_id='1'", args1);

		if (CheckUpdateOrInsert.equals("0")) {
			sql = "insert into HOME_COVERAGE_BUILDING(QUOTE_NO,COVER_ID,REMARKS,STATUS,BUILDING_NAME,STREET_NAME,FLAT_VILLA_NO,EMIRATE) values (?,?,?,?,?,?,?,?)";
			String args[] = { Quote_No, Cover_Id, REMARKS, STATUS,
					buildingName, buildingStreet, buildingFlat, buildingEmirate };
			runner.multipleInsertion(sql, args);

		} else {
			sql = "update  HOME_COVERAGE_BUILDING set REMARKS=?, STATUS=?, BUILDING_NAME=?,STREET_NAME=?,FLAT_VILLA_NO=?,EMIRATE=? where QUOTE_NO=? and COVER_ID=?";
			String args[] = { REMARKS, STATUS, buildingName, buildingStreet,
					buildingFlat, buildingEmirate, Quote_No, Cover_Id };
			runner.multipleUpdation(sql, args);
		}
		logger.debug("- Exit");
		
	}

	// For Finance Details
	public void homeCoverageBuildingDetletion(final String Quote_No)throws BaseException 
	{
		logger.info("homeCoverageBuildingDetletion method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No };
		runner.multipleUpdation("delete from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID not in (1)", args);
		logger.debug("- Exit");
		
	}

	public void homeCoverageTransactionDetletion(final String Quote_No,final String covId) throws BaseException 
	{
		logger.info("homeCoverageTransactionDetletion method()");
		logger.debug("- Enter");
		final String args[] = { Quote_No, covId };
		runner.multipleUpdation("delete from HOME_COVERAGE_TRANSACTION where QUOTE_NO=? and COVER_ID=? and AMEND_ID=1 and Home_sno not between 1001 and 1006", args);
		logger.debug("- Exit");
		
	}

	public void homeCoverageBuildingTransaction(final String Quote_No,final HashMap financeDetails) throws BaseException 
	{
		logger.info("homeCoverageBuildingTransaction individual screen method()");
		logger.debug("- Enter");
		final String totalFin = (String) financeDetails.get("totalFinance");
		int totalFinance = 0;
		if (totalFin != null && !totalFin.equals("")){
			totalFinance = Integer.parseInt(totalFin);
		}
		String FinancerName;
		String TelePhone;
		String LoanAmount;
		String pobox;
		String coverId;
		String coverCheck;
		String insSql;
		String upSql;
		String delCovId = "1,";
		logger.debug("royal building test..."+totalFinance);
		for (int f = 0; f < totalFinance; f++) {
			FinancerName = (String) financeDetails.get("FinancerName" + f);
			TelePhone = (String) financeDetails.get("TelePhone" + f);
			LoanAmount = (String) financeDetails.get("LoanAmount" + f);
			pobox = (String) financeDetails.get("pobox" + f);

			if (FinancerName == null || FinancerName.equals(""))
				FinancerName = "";
			if (TelePhone == null || TelePhone.equals(""))
				TelePhone = "";
			if (LoanAmount == null || LoanAmount.equals(""))
				LoanAmount = "";
			if (pobox == null || pobox.equals(""))
				pobox = "";

			coverId = "" + (f + 2);
			delCovId = delCovId + coverId + ",";
			String args[] = new String[2];
			args[0] = Quote_No;
			args[1] = coverId;
			coverCheck = runner.singleSelection("select count(*) from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID=?", args);

			if (coverCheck.equals("0")) {

				insSql = "insert into HOME_COVERAGE_BUILDING(QUOTE_NO,COVER_ID,FINANCE_COMPANY_NAME,LOAN_AMOUNT,FINANCE_TELEPHONE,POBOX) values(?,?,?,?,?,?)";
				String args1[] = { Quote_No, coverId, FinancerName, LoanAmount,
						TelePhone, pobox };
				runner.multipleInsertion(insSql, args1);
			} else {
				upSql = "update HOME_COVERAGE_BUILDING set FINANCE_COMPANY_NAME=?, LOAN_AMOUNT=?, FINANCE_TELEPHONE=?,POBOX=? where QUOTE_NO=? and COVER_ID=?";
				String args1[] = { FinancerName, LoanAmount, TelePhone, pobox,
						Quote_No, coverId };
				runner.multipleUpdation(upSql, args1);
			}
		}
		if (delCovId.length() > 0){
			delCovId = delCovId.substring(0, delCovId.length() - 1);
		}
		String args1[] = { Quote_No };
		runner.multipleUpdation("delete from HOME_COVERAGE_BUILDING where QUOTE_NO=? and COVER_ID not in ("+delCovId+")", args1);
		logger.debug("- Exit");
		
	}

	// For Nakeel Scheme
	public void IndividualPremiumUpdate(final String Quote_No,final String CoverId, final HashMap premiums) throws BaseException 
	{
		logger.info("IndividualPremiumUpdate method()");
		logger.debug("- Enter");
		final int loop = Integer.parseInt((String) premiums.get("IndPreSize"));
		double totPre = 0;
		String totalSNo = "";
		for (int i = 0; i < loop; i++) {
			String SNo = (String) premiums.get("SNo" + i);
			double pre = Math.round(Double.parseDouble((String) premiums
					.get("IndPre" + i)));
			totPre = totPre + pre;
			totalSNo = totalSNo + "'" + SNo + "',";
			String upSql = "update HOME_COVERAGE_TRANSACTION set PREMIUM=? where QUOTE_NO=? and HOME_SNO=? and COVER_ID=?";
			String args1[] = { "" + pre, Quote_No, SNo, CoverId };
			runner.multipleUpdation(upSql, args1);
		}
		if (totalSNo.length() > 0){
			totalSNo = totalSNo.substring(0, (totalSNo.length() - 1));
		}
		final String upSql = "update HOME_COVERAGE_TRANSACTION set PREMIUM='0' where QUOTE_NO=? and HOME_SNO not in("+totalSNo + ") and HOME_SNO<1000 and COVER_ID=?";
		String args1[] = { Quote_No, CoverId };
		runner.multipleUpdation(upSql, args1);
		logger.debug("- Exit");
		
	}

	public void IndividualPremiumUpdate(final String Quote_No,final String CoverId) throws BaseException 
	{
		logger.info("IndividualPremiumUpdate method()");
		logger.debug("- Enter");
		String args1[] = { Quote_No, CoverId };
		runner.multipleUpdation("update HOME_COVERAGE_TRANSACTION set PREMIUM='0' where QUOTE_NO=? and HOME_SNO<1000 and COVER_ID=?", args1);
		logger.debug("- Exit");
		
	}

	public void IndividualPremiumUpdatePos(final String Quote_No)throws BaseException 
	{
		logger.info("IndividualPremiumUpdatePos method()");
		logger.debug("- Enter");
		DataSelect cover = new DataSelect();
		String traPre = "0";
		double totPre = 0;
		String args1[] = { Quote_No };
		traPre = runner.singleSelection("select sum(nvl(PREMIUM,0)) from HOME_COVERAGE_TRANSACTION where QUOTE_NO=?", args1);
		totPre = Math.round(Double.parseDouble(traPre));
		// Calculate Commision
		double commis = 0;
		double totcom = 0;
		String result1[][] = new String[0][0];
		result1 = cover.getLoginProId(Quote_No);
		if (result1.length > 0){
			commis = Double.parseDouble(cover.getCommision((result1[0][0] == null ? "0" : result1[0][0]),(result1[0][1] == null ? "0" : result1[0][1])));
		}
		if (commis != 0){
			totcom = totPre * (commis / 100);
		}
		// totPre = totPre + Math.round(Double.parseDouble(posPre));
		String args2[] = { Double.toString(totPre), Double.toString(totPre),Double.toString(totcom), Quote_No };
		runner.multipleUpdation("update HOME_POSITION_MASTER set PREMIUM=?,OVERALL_PREMIUM =?,COMMISSION=? where QUOTE_NO=?", args2);
		logger.debug("- Exit");
		
	}
}