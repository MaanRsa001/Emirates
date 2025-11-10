package com.maan.opencover;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;

import com.maan.services.util.runner;
import com.maan.common.error.ErrorInfo;
import com.maan.common.util.OracleDateConversion;


public class conditions extends ErrorInfo 
{

	private String sqlQuery_ = "";
	private String[][] existingCommodity = new String[0][0];
	private String quoteId = "";
	private String applicationNo = "";
	private String loginCode = "";
	private String sessionId = "";
	private String productId = "";
	private String companyId = "";
	private String proposalNo = "";
	private int application_no = 0;
	private String stageId = "";
	private String ratess = null;
	private String warrate = null;
	private String seaValue = null;
	private String warehouseValue = null;
	private String adminStatus = null;
	private String openCoverNo = "";
	private String coverId = "";
	private String extraCoverId = "";
	private String clausesId = "";
	private String amendId = "";
	private String modeOfTransport = "";
	private String remarks = "";
	private String effectDate = "";
	/**
	 * @return Returns the referral
	 */

	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}

	public String getOpenCoverNo()

	{
		return openCoverNo;
	}

	/**
	 * @return Returns the applicationNo.
	 */
	public String getApplicationNo() {
		return applicationNo;
	}

	/**
	 * @param applicationNo
	 *            The applicationNo to set.
	 */
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	/**
	 * @return Returns the loginCode.
	 */
	public String getLoginCode() {
		return loginCode;
	}

	/**
	 * @param loginCode
	 *            The loginCode to set.
	 */
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	/**
	 * @return Returns the loginCode.
	 */
	public String getEffectDate() {
		return effectDate;
	}

	/**
	 * @param loginCode
	 *            The loginCode to set.
	 */
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	/**
	 * @return Returns the quoteId.
	 */
	public String getQuoteId() {
		return quoteId;
	}

	/**
	 * @param quoteId
	 *            The quoteId to set.
	 */
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	/**
	 * @return Returns the sessionId.
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId
	 *            The sessionId to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * @return Returns the companyId.
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId
	 *            The companyId to set.
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return Returns the productId.
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            The productId to set.
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return Returns the stageId.
	 */
	public String getStageId() {
		return stageId;
	}

	/**
	 * @param stageId
	 *            The stageId to set.
	 */
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	/**
	 * @return Returns the proposalNo.
	 */
	public String getProposalNo() {
		return proposalNo;
	}

	/**
	 * @param proposalNo
	 *            The proposalNo to set.
	 */
	public void setProposalNo(String proposalNo) {
		this.proposalNo = proposalNo;
	}

	/**
	 * @return Returns the proposalNo.
	 */
	public String getCoverId() {
		return coverId;
	}

	/**
	 * @param proposalNo
	 *            The proposalNo to set.
	 */
	public void setExtraCoverId(String extracoverId) {
		this.extraCoverId = extraCoverId;
	}

	/**
	 * @return Returns the proposalNo.
	 */
	public String getExtraCoverId() {
		return extraCoverId;
	}

	/**
	 * @param proposalNo
	 *            The proposalNo to set.
	 */
	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}

	/**
	 * @param modeOfTransport_
	 *            The modeOfTransport_ to set.
	 */
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	/**
	 * @return
	 */
	public HashMap getConditionsFromMaster()
	{
		HashMap totalConditions = new HashMap();
		HashMap conditionsList = new HashMap();
		HashMap conditionsIdsList = new HashMap();
		String args[] = new String[1];
		String result[][] = new String[0][0];
		int count = 0;
		try 
		{
			args[0] = coverId;
			sqlQuery_ = "select clauses_id,clauses_description from clauses_master where status='Y' and cover_id=?";
			print("getConditionsFromMaster THIS IS WHAT HAPPENENING",sqlQuery_, "QUERY");
			
			result = runner.multipleSelection(sqlQuery_,args);
			for(int i=0;i<result.length;i++)
			{
				conditionsList.put("" + result[i][0], result[i][1]);
				conditionsIdsList.put("" + count, "" + result[i][0]);
				count = count + 1;
			}
			conditionsIdsList.put("effectDate", "null");
			totalConditions.put("conditionsList", conditionsList);
			totalConditions.put("conditionsIdsList", conditionsIdsList);
		}
		catch (Exception exception)
		{
			System.out.println("The Exception getConditionsFromMaster--"+exception.toString());
			exception.printStackTrace();
		} 
		return totalConditions;
	}

	public HashMap getConditionsFromOpenCoverMaster()
	{
		HashMap conditionsListExisting = new HashMap();
		int countExis = 1;
		String exisDate = "";
		String args[] = new String[2];
		String result[][] = new String[0][0];
		try 
		{
			args[0] = proposalNo;
			args[1] = proposalNo;

			sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.proposal_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') < to_date(sysdate,'dd-mm-YYYY'))";

			print("getConditionsFromOpenCoverMaster THIS IS WHAT getConditionsFromOpenCoverMaster",	sqlQuery_, "QUERY");
				
			result = runner.multipleSelection(sqlQuery_,args);

			for(int i=0;i<result.length;i++)
			{
				conditionsListExisting.put("clausesId" + (countExis), ""+ result[i][0]);
				conditionsListExisting.put("description" + (countExis), result[i][1]);
				exisDate = result[i][2];
				countExis = countExis + 1;
			}
			countExis = countExis - 1;
			conditionsListExisting.put("finalCount", "" + countExis);
			conditionsListExisting.put("effectDate", exisDate);
		} 
		catch (Exception exception) 
		{
			System.out.println("The Exception occured in getConditionsFromOpenCoverMaster--"+ exception.toString());
			exception.printStackTrace();
		} 
		
		return conditionsListExisting;
	}

	public void insertUpdateConditions(HashMap comDetails) 
	{
		int rows = 0;
		String policyDate = "";
		int totalCount = 0;
		String commodityId = "";
		String description = "";
		String eDate = "";
		Calendar cal = new GregorianCalendar();
		String args[] = new String[10];

		int hour12 = cal.get(Calendar.HOUR); // 0..11
		int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
		int min = cal.get(Calendar.MINUTE); // 0..59
		int sec = cal.get(Calendar.SECOND); // 0..59
		int ms = cal.get(Calendar.MILLISECOND); // 0..999
		int ampm = cal.get(Calendar.AM_PM); // 0=AM, 1=PM
		
		policyDate = "SYSDATE";
		System.out.println("the SYSTEM DATE IS " + policyDate);
	
		eDate = OracleDateConversion.ConvertDate(getEffectDate())+ " " + hour24 + ":" + min + ":" + sec;
				
		amendId = "" + getMaximumAmendId(proposalNo);
		try 
		{
			
			sqlQuery_ = "insert into open_cover_clauses (proposal_no,mode_transport_id,cover_id,extra_cover_id,clauses_id,clauses_description,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE(?,'dd/mon/yyyy HH24:MI:SS')+8/24,?,?)";

			totalCount = Integer.parseInt((String) comDetails.get("finalCount"));
			for (int i = 0; i < totalCount; i++) 
			{
				clausesId = (String) comDetails.get("clausesId" + (i + 1));
				description = (String) comDetails.get("description" + (i + 1));
						
				args[0] = proposalNo;
				args[1] = ""+Integer.parseInt(modeOfTransport);
				args[2] = ""+Integer.parseInt(coverId);
				args[3] = "0";
				args[4] = ""+Integer.parseInt(clausesId);
				args[5] = description;
				args[6] = ""+Integer.parseInt(amendId);
				args[7] = eDate;
				args[8] = remarks;
				args[9] = "Y";
				
				runner.multipleUpdation(sqlQuery_,args);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in insertUpdateConditions()  Insertion :"	+ e.toString());
			e.printStackTrace();
		} 
	}

	public int getMaximumProposalNo()
	{
		int application_id = 1;
		String sql = "select nvl(max(proposal_no),'0')+1 from open_cover_clauses";

		try
		{
			  String temp = runner.singleSelection(sql);		
			  if(temp.length()>0)
				application_id = Integer.parseInt(temp);
		}
		catch(Exception e)
		{
			  System.out.println("the EXCEPTION IN "+e.toString());
		}
		if(application_id == 0)
		{
			application_id = 1;
		}
		return application_id;
	}

	public void print(String methodName, String information, String type)
	{
		System.out.println(methodName + "<--->" + type + "<---->" + information);
	}

	public int getMaximumAmendId(String proposalNo) 
	{
		int s_id = 1;
		String sqlQuery_ = "";
		String args[] = new String[1];
		
		sqlQuery_ = "select nvl(max(amend_id),0)+1 from open_cover_clauses where proposal_No=?";
		args[0] = proposalNo;
		try 
		{
			String result = runner.singleSelection(sqlQuery_,args);	
			s_id = Integer.parseInt(result);
		}
		catch (Exception e)
		{
			System.out.println("the EXCEPTION IN open_cover_clauses @@@@ getMaximumAmendId"	+ e.toString());
			e.printStackTrace();
		} 
		if(s_id == 0)
		{
			s_id = 1;
		}
		return s_id;
	}
} // Class
