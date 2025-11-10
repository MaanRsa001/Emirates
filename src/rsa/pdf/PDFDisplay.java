package rsa.pdf;


import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;
import java.util.*;

import org.apache.logging.log4j.Logger;


public class PDFDisplay 
{
	private static final Logger logger = LogUtil.getLogger(PDFDisplay.class);
	
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	final static transient private String PDF = "bankDetails";
	public Map getPDFStatus(final String policyNo, final String loginCode) throws BaseException
	{
		logger.info("One Off PDFDisplay getPDFStatus method()");
		logger.debug(ENTER);
			String pdfStatus;
			String userType;
			Map pdfHash;
			pdfHash = new HashMap();
			String sql;
			userType = getUserType(loginCode);
			if ("admin".equalsIgnoreCase(userType)){
				sql = "select pdf_admin_status from position_master where policy_no=? and status='P'";
			}else{
				sql = "select pdf_broker_status from position_master where policy_no=? and status='P'";
			}
			final String args[] = {policyNo};
			pdfStatus = runner.singleSelection(sql,args);
			pdfHash.put("PDFStatus", pdfStatus);
		logger.debug(EXIT);
		
		return pdfHash;
	}
	public String getUserType(final String loginCode) throws BaseException
	{
		logger.info("One Off PDFDisplay getUserType method()");
		logger.debug(ENTER);
			String userType;
			String sql;
			sql = "select usertype from login_master where login_id=? and Status='Y'";
			final String args[] = {loginCode};
			userType = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return userType;
	}
	public String getQuoteBasedPolicyNo(final String quoteNo) throws BaseException
	{
		logger.info("One Off PDFDisplay getQuoteBasedPolicyNo method()");
		logger.debug(ENTER);
			String quotePolicy;
			String sql;
			sql = "select policy_no from position_master where quote_no=? and status='P'  and open_cover_int_status in ('LINKED')";
			final String args[] = {quoteNo};
			quotePolicy = runner.singleSelection(sql,args);
		logger.debug(EXIT);
		
		return quotePolicy;
	}
	public void getUpdatePDFStatus(final String policyNo, final String loginCode,final String clickStatus) throws BaseException
	{
		logger.info("One Off PDFDisplay getUpdatePDFStatus method()");
		logger.debug(ENTER);
		String userType;
		String sql;
			userType = getUserType(loginCode);
			if ("admin".equalsIgnoreCase(userType))	{
				sql = "update position_master set pdf_admin_status=? where policy_no=? and status='P'";
			} else {
				sql = "update position_master set pdf_broker_status=? where policy_no=? and status='P'";
			}
			final String args[] = {clickStatus,policyNo};
			runner.multipleUpdation(sql,args);
		logger.debug(EXIT);
		
	}
	public String[][] getPreBankOptions(final String policyNo, final String loginCode,final String status)throws BaseException
	{
		logger.info("One Off PDFDisplay getPreBankOptions method()");
		logger.debug(ENTER);
			String sql;
			final String args[] = {policyNo};
			Map bank;
			bank = new HashMap();
			if ("policy".equalsIgnoreCase(status)) 
			{
				sql = "select PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS,PDF_EXCESS_STATUS from position_master where  policy_no=? and status='P'";
				String[][] bankDetails;
				bankDetails = runner.multipleSelection(sql,args);
				bank.put(PDF,bankDetails);
			}
			else if ("draft".equalsIgnoreCase(status)) 
			{
				sql = "select PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS from position_master where  quote_no=?";
				String[][] bankDetails;
				bankDetails = runner.multipleSelection(sql,args);
				bank.put(PDF,bankDetails);
			}
			else if("MultiQuotes".equalsIgnoreCase(status))
			{
				sql="select PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS " +
						"from position_master where  quote_no=? and status='P'";
				String[][] bankDetails;
				bankDetails =runner.multipleSelection(sql,args);
				bank.put(PDF,bankDetails);
			}
			else if("MultiQuotesDisplay".equalsIgnoreCase(status))
			{
				sql="select PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS,count(status) " +
						"from position_master where  quote_no in ("+policyNo+") group by PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS";
				String[][] bankDetails;
				bankDetails = runner.multipleSelection(sql);
				bank.put(PDF,bankDetails);
			}
			String[][] pdfOption;
			pdfOption = (String[][])bank.get(PDF);
		logger.debug(EXIT);
		
		return pdfOption;
	}
	public String[][] makeTwoDimArray(final String combined) throws BaseException
	{
		logger.info("One Off PDFDisplay makeTwoDimArray method()");
		logger.debug(ENTER);

			int count;
			count = 0;
			StringTokenizer arrayToken;
			arrayToken = new StringTokenizer(combined, "#");
			String[][] convertedArray;
			convertedArray = new String[arrayToken.countTokens()][3];
			while (arrayToken.hasMoreTokens())
			{
				int jcount;
				jcount = 0;
				String token;
				token = arrayToken.nextToken();
				StringTokenizer nextTokens;
				nextTokens = new StringTokenizer(token, "~");
				while (nextTokens.hasMoreTokens()) 
				{
					String finalToken;
					finalToken = nextTokens.nextToken();
					convertedArray[count][jcount++] = finalToken;
				}
				count++;
			}
		logger.debug(EXIT);
		
		return convertedArray;
	}
	public String decimalFormat(final double number, final int nos)throws BaseException {

		String digit;digit = "0.00";
		String noFmt = String.valueOf(number);
		switch (nos) {
		case 0:
			digit = "###,###";
			break;
		case 1:
			digit = "##,##0.0";
			break;
		case 2:
			digit = "##,##0.00";
			break;
		case 3:
			digit = "##,##0.000";
			break;
		case 4:
			digit = "##,##0.0000";
			break;
		case 5:
			digit = "##,##0.00000";
			break;
		case 6:
			digit = "##,##0.000000";
			break;
		default:
			digit = "0.00";
		}
		java.text.NumberFormat fmtNo;
		fmtNo = new java.text.DecimalFormat(digit);
		noFmt = fmtNo.format(number);
		return noFmt;
	}
	public String[][] getNoteGenerateStatus(final String policyNo) throws BaseException
	{
		return runner.multipleSelection("SELECT NVL(DN_GEN_STS,0),NVL(CN_GEN_STS,0) FROM POSITION_MASTER WHERE POLICY_NO=? AND STATUS='P'", new String[]{policyNo});
	}
}
