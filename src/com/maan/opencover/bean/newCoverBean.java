package com.maan.opencover.bean;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Locale;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.LocalizedTextProvider;

public class newCoverBean
{
	private static final Logger logger = LogUtil.getLogger(newCoverBean.class);
	public String brokerId="";
	public String type="";
	public String customerName="";
	public String customerId="";
	public String backDays="";
	public String estimateAmount="";
	public String rsaValue="";
	public String no_ofCompany="";
	public String crossVoyage="";
	public String voyageValue="";
	public String freeText="";
	public String commission="";
	public String miniPremium="";
	public String missippiCode="";
	public String proposalNo="";
	public String businessType="0";
	public String missippiOpenPolicyId ="0";
	public String impMiniPremium ="0";
	public String expMiniPremium ="0";
	public String crossMiniPremium ="0";
	public String proCommission ="0";
	public String sdate ="";
	public String edate ="";
	public String wsrc ="";
	public String userId ="";
	//added by hari 16/7/2012
	public String currency = "";
	public String exchangeRate = "";
	public String issuanceFee = "";
	public String minPremiumIssuance = "";
	public String foreignTurnOver = "";
	public String minPreMulType = "";
	public String minPreMul = "";
	public String lossDetail = "";
	public String claimRatio = "";
	public String additionalInfo = "";
	public String guestLogin = "";
	public String proposalStatus = "";
	public String confirmStatus = "";
	public String defaultCluases = "";
	public String executiveId = "";
	public String debitType = "";
	public String utilizedAmount = "";
	
	private String debitToNo = "";
	private String creditToNo = "";
	private String depositeYn="";
	private String existDepositeYn="";
	private String depositeType="";
	private String depositeInsType="";
	private String marinePre="";
	private String warPre="";
	private String debitDate="";
	private String endorsementFee="";
	private String haulierType="";
	private String remarks="";
	
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	public String getGuestLogin() {
		return guestLogin;
	}
	public void setGuestLogin(String guestLogin) {
		this.guestLogin = guestLogin;
	}
	public String getLossDetail() {
		return lossDetail;
	}
	public void setLossDetail(String lossDetail) {
		this.lossDetail = lossDetail;
	}
	public String getClaimRatio() {
		return claimRatio;
	}
	public void setClaimRatio(String claimRatio) {
		this.claimRatio = claimRatio;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	/**
	 * @return the minPreMulType
	 */
	public String getMinPreMulType() {
		return minPreMulType;
	}
	/**
	 * @param minPreMulType the minPreMulType to set
	 */
	public void setMinPreMulType(String minPreMulType) {
		this.minPreMulType = minPreMulType;
	}
	/**
	 * @return the minPreMul
	 */
	public String getMinPreMul() {
		return minPreMul;
	}
	/**
	 * @param minPreMul the minPreMul to set
	 */
	public void setMinPreMul(String minPreMul) {
		this.minPreMul = minPreMul;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public String getIssuanceFee() {
		return issuanceFee;
	}
	public void setIssuanceFee(String issuanceFee) {
		this.issuanceFee = issuanceFee;
	}
	
	public String getMinPremiumIssuance() {
		return minPremiumIssuance;
	}
	public void setMinPremiumIssuance(String minPremiumIssuance) {
		this.minPremiumIssuance = minPremiumIssuance;
	}
	
	public String ckey ="";
	
	public String getForeignTurnOver() {
		return foreignTurnOver;
	}
	public void setForeignTurnOver(String foreignTurnOver) {
		this.foreignTurnOver = foreignTurnOver;
	}
	//added by hari
	public String getCkey() {
		return ckey;
	}
	public void setCkey(String ckey) {
		this.ckey = ckey;
	}
	public void setBrokerId(String brokerId)
	{
		this.brokerId=brokerId;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName=customerName;
	}
	public void setCustomerId(String customerId)
	{
		this.customerId=customerId;
	}
	public void setBackDays(String backDays)
	{
		this.backDays=backDays;
	}
	public void setEstimateAmount(String estimateAmount)
	{
		this.estimateAmount=estimateAmount;
	}
	public void setRsaValue(String rsaValue)
	{
		this.rsaValue=rsaValue;
	}
	public void setNo_ofCompany(String no_ofCompany)
	{
		this.no_ofCompany=no_ofCompany;
	}
	public void setCrossVoyage(String crossVoyage)
	{
		this.crossVoyage=crossVoyage;
	}
	public void setVoyageValue(String voyageValue)
	{
		this.voyageValue=voyageValue;
	}
	public void setFreeText(String freeText)
	{
		this.freeText=freeText;
	}
	public void setCommission(String commission)
	{
		this.commission=commission;
	}
	public void setMiniPremium(String miniPremium)
	{
		this.miniPremium=miniPremium;
	}
	public void setMissippiCode(String missippiCode)
	{
		this.missippiCode=missippiCode;
	}
	public void setMissippiOpenPolicyId(String missippiOpenPolicyId)
	{
		this.missippiOpenPolicyId=missippiOpenPolicyId;
	}
	public void setProposalNo(String proposalNo)
	{
		this.proposalNo=proposalNo;
	}
	public void setBusinessType(String businessType)
	{
		this.businessType=businessType;
	}
	public void setProCommission(String proCommission)
	{
		this.proCommission=proCommission;
	}
	public String getProCommission()
	{
		return this.proCommission;
	}
	public void setSdate(String sdate)
	{
		this.sdate=sdate;
	}
	public String getSdate()
	{
		return sdate;
	}
	public void setEdate(String edate)
	{
		this.edate=edate;
	}
	public String getEdate()
	{
		return edate;
	}
	public String getBrokerId()
	{
		return brokerId;
	}
	public String getType()
	{
		return type;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public String getCustomerId()
	{
		return customerId;
	}
	public String getBackDays()
	{
		return backDays;
	}
	public String getEstimateAmount()
	{
		return estimateAmount;
	}
	public String getRsaValue()
	{
		return rsaValue;
	}

	public String getNo_ofCompany()
	{
		return no_ofCompany;
	}
	public String getCrossVoyage()
	{
		return crossVoyage;
	}
	public String getVoyageValue()
	{
		return voyageValue;
	}
	public String getFreeText()
	{
		return freeText;
	}
	public String getCommission()
	{
		return commission;
	}
	public String getMiniPremium()
	{
		return miniPremium;
	}
	public String getMissippiCode()
	{
		return missippiCode;
	}
	public String getMissippiOpenPolicyId()
	{
		return missippiOpenPolicyId;
	}
	public String getProposalNo()
	{
		return proposalNo;
	}
	public String bussinessType()
	{
		return businessType;
	}
	
	public void setExpMiniPremium(String expMiniPremium)
	{
		this.expMiniPremium = expMiniPremium;
	}
	public void setImpMiniPremium(String impMiniPremium)
	{
		this.impMiniPremium = impMiniPremium;
	}
	public void setCrossMiniPremium(String crossMiniPremium)
	{
		this.crossMiniPremium = crossMiniPremium;
	}
	public String getExpMiniPremium()
	{
		return expMiniPremium;
	}
	public String getImpMiniPremium()
	{
		return impMiniPremium;
	}
	public String getCrossMiniPremium()
	{
		return this.crossMiniPremium;
	}
	public void setWsrc(String wsrc)
	{
		this.wsrc = wsrc;
	}
	public String getWsrc()
	{
		return this.wsrc;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getUserId()
	{
		return this.userId;
	}

	public String[][] getBrokers()
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		try
		{
			sql = "select a.company_name,b.login_id,a.agency_code from broker_company_master a,login_master b where b.agency_code=a.agency_code order by a.company_name";
			getBrokerName = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}

	public String[][] getBrokersQuoteRegister()
	{
		String[][] getBroName =  new String[0][0];
		String sql = "";
		try
		{
			sql = "select distinct a.company_name,b.login_id from broker_company_master a,login_master b,open_cover_master c where b.agency_code=a.agency_code and c.broker_id=b.login_id and c.proposal_no in (select proposal_no from open_cover_position_master where open_cover_no is  null and status='Y') order by a.company_name";
			getBroName=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBroName;
	}

	 public String[][] getBrokersHasCover()
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		try
		{
			sql = "select distinct a.company_name,b.login_id from broker_company_master a,login_master b,open_cover_master c where b.agency_code=a.agency_code and c.broker_id=b.login_id and c.proposal_no in (select proposal_no from open_cover_position_master where open_cover_no is not null and status='P') order by a.company_name";
			getBrokerName = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}

	public String[][] getCountrys()
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		try
		{
			sql = "select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			getBrokerName = runner.multipleSelection(sql);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}
	public String[][] getContientList(String branchCode) {
		String[][] contientList = new String[0][0];
		try {
			String sql = "select * from constant_detail where CATEGORY_ID=? AND BRANCH_CODE="+branchCode+" AND STATUS=?";
			contientList = runner.multipleSelection(sql,new String[]{"155","Y"});
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return contientList;
	}
	public String[][] getTransports()
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		try
		{
			sql = "select distinct TRANSPORT_DESCRIPTION ,MODE_TRANSPORT_ID from MODE_OF_TRANSPORT";
			getBrokerName = runner.multipleSelection(sql);

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}
	public String storedValues(String ProductId,String opencoverNo,String loginBra, String login_id)
	{
		String results="";
		String args[] = new String[0];
		String sql = "";
		try
		{
			if(loginBra.indexOf("'")!=-1)
				loginBra = loginBra.replaceAll("'","");
			String sumInsuredLmit="0";
			if("Y".equalsIgnoreCase(crossVoyage))
			{
				try
				{
					sumInsuredLmit=""+(Double.parseDouble(estimateAmount)*(Double.parseDouble(voyageValue)/100));
				}catch(Exception e)
				{
					System.out.println(" ERROR While calculating CrossVoyage Limit-->"+e.toString());
				}
			}

				//Insertion for Open_cover_master
			String countyr="",insured="", insuredName=""; 
			String[][] info=runner.multipleSelection("select nvl(COUNTRY_REMARKS,''),ADDITIONAL_INSURED,ADDITIONAL_INSURED_NAME,DEPOSIT_PREMIUM_YN ,EXISTING_DEPOSIT_YN,nvl2(CREDIT_NOTE_DATE,to_char(CREDIT_NOTE_DATE,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')) ,nvl(DEBIT_NOTE_NO,''),nvl(CREDIT_NOTE_NO,''),DEPOSIT_TYPE ,MARINE_PREMIUM,WAR_PREMIUM,REMARKS,Installment_Type,DEPOSIT_AMOUNT from open_cover_master where proposal_no="+proposalNo+" and amend_id=(select max(amend_id)  from open_cover_master where proposal_no="+proposalNo+" )");
			if(info!=null && info.length>0){
				countyr=info[0][0];
				insured=info[0][1];
				insuredName=info[0][2];
				 debitDate=info[0][5];
				if("Y".equalsIgnoreCase(info[0][3])){
					 debitToNo = info[0][6];
					 creditToNo =info[0][7];
					 depositeYn=info[0][3];
					 existDepositeYn=info[0][4];
					 depositeType=info[0][8];
					 depositeInsType=info[0][12];
					 marinePre=info[0][9];
					 warPre=info[0][10];
					 remarks=info[0][11];
				}
			}
				
			if(opencoverNo==null)
			{
				args = new String[2];
				args[0] = proposalNo;
				args[1] = ProductId;
				sql = "delete from open_cover_master where proposal_no=? and product_id=?";
				runner.multipleUpdation(sql,args);
			}
			
			String amendId ="";
			sql = "select nvl(max(amend_id)+1,'0') from open_cover_master where proposal_no=?";
			args = new String[1];
			args[0] = proposalNo;
			amendId = runner.singleSelection(sql,args);

			sql = "insert into open_cover_master(proposal_no,amend_id,product_id,broker_id,broker_user_id,customer_id,branch_code,cross_voyage,cross_voyage_turnover,cross_voyage_suminsured_limit,back_date_days,commission,free_text_allowed,no_of_insurance_company,status,cross_voyage_percentage,effective_date,rsa_shared_percentage,type,min_premium,missippi_opencover_no,business_type,MISSISSIPI_OPEN_POLICY_ID,IMPORT_MIN_PREMIUM_AMOUNT,EXPORT_MIN_PREMIUM_AMOUNT,WRSC_YN,CROSS_MIN_PREMIUM_AMOUNT,POLICY_START_DATE,POLICY_END_DATE,COUNTRY_REMARKS,CURRENCY_ID,EXCHANGE_RATE,ISSUANCE_FEE,MIN_PREMIUM_ISSUANCE_FEE,FOREIGN_TURNOVER,MIN_PRE_MUL_TYPE,MIN_PRE_MUL,ADDITIONAL_INSURED,ADDITIONAL_INSURED_NAME,LOSS_DETAIL,CLAIM_RATIO,ADDITIONAL_INFO,PROPOSAL_STATUS,CONFIRM_STATUS,login_id,CLAUSES_YN,EXECUTIVE_ID,DEBIT_NOTE,UTILIZED_AMOUNT,DEPOSIT_PREMIUM_YN ,EXISTING_DEPOSIT_YN,DEBIT_NOTE_DATE,DEBIT_NOTE_NO,CREDIT_NOTE_NO,DEPOSIT_TYPE ,MARINE_PREMIUM,WAR_PREMIUM,REMARKS,Installment_Type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'dd-mm-yyyy'),?,?,?,?,?,?,?)";

			args = new String[58];
			args[0] = proposalNo;
			args[1] = amendId;
			args[2] = ProductId;
			args[3] = brokerId;
			args[4] = userId;
			args[5] = customerId;
			args[6] = loginBra;
			args[7] = crossVoyage;
			args[8] = estimateAmount;
			args[9] = sumInsuredLmit;
			args[10] = backDays;
			args[11] = commission;
			args[12] = freeText;
			args[13] = no_ofCompany;
			args[14] = "Y";
			args[15] = voyageValue;
			args[16] = "100";
			args[17] = type;
			args[18] = miniPremium;
			args[19] = missippiCode;
			args[20] = businessType;
			args[21] = missippiOpenPolicyId;
			args[22] = miniPremium;
			args[23] = miniPremium;
			args[24] = wsrc;
			args[25] = miniPremium;	
			args[26] = sdate;	
			args[27] = edate;
			args[28] = countyr;	
			args[29] = currency;	
			args[30] = exchangeRate;	
			args[31] = issuanceFee;	
			args[32] = minPremiumIssuance;	
			args[33] = foreignTurnOver;	
			args[34] = minPreMulType;	
			args[35] = minPreMul;	
			args[36] = insured;	
			args[37] = insuredName;	
			args[38] = lossDetail;	
			args[39] = claimRatio;	
			args[40] = additionalInfo;	
			args[41] = proposalStatus;	
			args[42] = confirmStatus;	
			args[43] = login_id;
			args[44] = defaultCluases;
			args[45] = executiveId;
			args[46] = debitType;
			args[47] = utilizedAmount;
			args[48] = depositeYn;	
			args[49] = existDepositeYn;	
			args[50] = debitDate;	
			args[51] = debitToNo;	
			args[52] = creditToNo;	
			args[53] = depositeType;
			args[54] = marinePre;
			args[55] = warPre;
			args[56] = remarks;	
			args[57] = depositeInsType;
			
			
			
		
			
			
	
			String result = runner.multipleInsertion(sql,args);
			if(guestLogin.equalsIgnoreCase("GUEST")){
				runner.multipleUpdation("UPDATE OPEN_COVER_MASTER SET GUEST_LOGIN_STATUS=? WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)", new String[]{guestLogin, proposalNo, proposalNo});
			}	
			runner.multipleUpdation("UPDATE OPEN_COVER_DETAIL SET OPEN_COVER_START_DATE=TO_DATE(?,'dd-mm-yyyy'), OPEN_COVER_END_DATE=TO_DATE(?,'dd-mm-yyyy') WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO=?)", new String[]{sdate, edate, proposalNo, proposalNo});
			if(!"INSERTED".equalsIgnoreCase(result))
				results = result;
		}
		catch(Exception e)
		{
			results=e.toString();
			System.out.println("  ERROR in    "+e.toString());
			e.printStackTrace();
		}
		if(results.length()<1)
			openCoverNumber(proposalNo, ckey, sdate, edate, opencoverNo);
		return results;
	}
	public String checkmisscodeProce(String missicode)
	{
		logger.info("getExistingPolicyNoCkey - Enter || "+missicode);
		String ckey="";	
		CallableStatement cstmt = null;
		Connection con = null;
		try {
		con = DBConnection.getInstance().getDBConnection();
		cstmt = con.prepareCall("call VALIDATE_CIMS_POLICY(?,?)");
		cstmt.setString(1, missicode);		
		cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
		System.out.println("procedure execute");
		cstmt.execute();
		System.out.println("procedure execute success");
		ckey=cstmt.getString(2);
		System.out.println("ckey:-------->"+ckey);
		}catch(Exception e)
		{
			e.printStackTrace();
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
		logger.info("getExistingPolicyNoCkey - Exit || ckey: "+ckey);
		return 	ckey;
	}
		




	public void openCoverNumber(String proposalNo, String ckey, String startDate, String endDate, String openCoverNo)
	{
		String count = "";
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[1];
			args[0] = proposalNo;
			sql = "select count(*) from open_cover_position_master where proposal_no=?";
			count = runner.singleSelection(sql,args);
			if(Integer.parseInt(count)<=0)
			{
				args = new String[6];
				args[0] = proposalNo;
				args[1] = "0";
				args[2] = "Y"; 
				args[3] = ckey; 
				args[4] = startDate; 
				args[5] = endDate; 
				sql = "insert into open_cover_position_master(proposal_no,amend_id,status,ckey,INCEPTION_DATE,EXPIRY_DATE)values(?,?,?,?,TO_DATE(?,'dd-mm-yyyy'),TO_DATE(?,'dd-mm-yyyy'))";
				runner.multipleInsertion(sql,args);
			}else if(openCoverNo==null)
			{
				runner.multipleUpdation("UPDATE OPEN_COVER_POSITION_MASTER SET INCEPTION_DATE=TO_DATE(?,'dd-mm-yyyy'), EXPIRY_DATE=TO_DATE(?,'dd-mm-yyyy'),CKEY=? WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_POSITION_MASTER WHERE PROPOSAL_NO=?)", new String[]{sdate, edate,ckey, proposalNo, proposalNo});
			}else
			{
				runner.multipleUpdation("UPDATE OPEN_COVER_POSITION_MASTER SET INCEPTION_DATE=TO_DATE(?,'dd-mm-yyyy'), EXPIRY_DATE=TO_DATE(?,'dd-mm-yyyy') WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_POSITION_MASTER WHERE PROPOSAL_NO=?)", new String[]{sdate, edate, proposalNo, proposalNo});
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String[] getEditModeValue(String proposal_no)
	{
		String[] datas = new String[45];
		String[][] result = new String[0][0];
		String sql = "";
		String args[]= new String[2];
		try
		{
			args[0] = proposal_no;
			args[1] = proposal_no;
			 sql = "select a.broker_id,nvl(b.first_name,b.company_name),a.type,a.customer_id,a.back_date_days,a.FOREIGN_TURNOVER,a.RSA_SHARED_PERCENTAGE,a.NO_OF_INSURANCE_COMPANY,a.cross_voyage,a.cross_voyage_percentage,a.free_text_allowed,a.commission,nvl(min_premium,'0'),nvl(missippi_opencover_no,''),nvl(business_type,'0'),nvl(MISSISSIPI_OPEN_POLICY_ID,''),nvl(IMPORT_MIN_PREMIUM_AMOUNT,'0'),nvl(EXPORT_MIN_PREMIUM_AMOUNT,'0'),nvl(Pro_commission,'0'),to_char(pro_start_date,'dd-mm-yyyy'),to_char(pro_expiry_date,'dd-mm-yyyy'),nvl(WRSC_YN,'N'),a.BROKER_USER_ID,nvl(a.CROSS_MIN_PREMIUM_AMOUNT,0),TO_CHAR(POLICY_START_DATE,'dd') AS DAYS,TO_CHAR(POLICY_START_DATE,'mm') AS MONTHS,TO_CHAR(POLICY_START_DATE,'YYYY') AS YEARS,TO_CHAR(POLICY_END_DATE,'dd') AS DAYS,TO_CHAR(POLICY_END_DATE,'mm') AS DAYS,TO_CHAR(POLICY_END_DATE,'YYYY') AS YEARS,CURRENCY_ID,EXCHANGE_RATE,ISSUANCE_FEE,MIN_PREMIUM_ISSUANCE_FEE,MIN_PRE_MUL_TYPE,MIN_PRE_MUL,C.RENEWAL_STATUS,C.ENDT_STATUS,A.LOSS_DETAIL,A.CLAIM_RATIO,A.ADDITIONAL_INFO,A.GUEST_LOGIN_STATUS,A.EXECUTIVE_ID,A.DEBIT_NOTE,A.UTILIZED_AMOUNT from open_cover_master a ,personal_info b,OPEN_COVER_POSITION_MASTER C where a.proposal_no=? and a.amend_id=(select max(a.amend_id) from open_cover_master a where a.proposal_no=?) and b.customer_id=a.customer_id AND C.PROPOSAL_NO=A.PROPOSAL_NO";
			 //CURRENCY_ID,EXCHANGE_RATE,ISSUANCE_FEE,MIN_PREMIUM_ISSUANCE_FEE,FOREIGN_TURNOVER
			result = runner.multipleSelection(sql,args);
			int i=0;
			for(i=0;i<45;i++)
			{
				if(i==2)
				{
					datas[i]=(result[0][i]==null?"":result[0][i]).replaceAll("\"","&#34;");
					
				}
				else
				{
					datas[i]=result[0][i]==null?"":result[0][i];
					
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("ERROR WHILE SELECTING RECORDS "+e.toString());
			e.printStackTrace();
		}
		return datas;
	}
	public int CoreMissippiCheck() {
		int count = 0;
		try {
		    LocalizedTextProvider ltp = ActionContext.getContext().getInstance(LocalizedTextProvider.class);
			String qry = ltp.findDefaultText("CHK_OPENCOVER_NO", Locale.ENGLISH);
			count = Integer.valueOf(runner.singleSelection(qry,new String[]{missippiCode}));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public boolean checkingMissippiCode(String proposal)
	{
		String value = "";
		try
		{
			value = runner.singleSelection("select count(*) from open_cover_master where missippi_opencover_no in ('"+missippiCode+"') and proposal_no not in ('"+proposal+"')");
			if(Integer.parseInt(value)<=0)
				return false;
			else
				return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return true;
		}
	}

	public synchronized String getProposalNos(String loginBra)
	{
		String current_no = "";
		String sql = "";
		String args[] = new String[0];
		try
		{
			if(loginBra.indexOf("'")!=-1)
				loginBra = loginBra.replaceAll("'","");
			try
			{
				/*args = new String[2];
				args[0] = loginBra;
				args[1] = loginBra;
				sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select PROPOSAL_NO_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11')" +
						" and status='Y' and BRANCH_CODE=? ";
				current_no = runner.singleSelection(sql,args);
				
				args = new String[4];
				args[0] = current_no;
				args[1] = current_no;
				args[2] = loginBra;
				args[3] = loginBra;
				sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select PROPOSAL_NO_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') " +
						"and status='Y' and BRANCH_CODE=?";
				runner.multipleUpdation(sql,args);*/
				current_no = runner.singleSelection("SELECT PROPOSAL_NUMBER_SEQ.NEXTVAL FROM DUAL");
			}
			catch(Exception e)
			{
				System.out.println("ERROR in getMaxQuote in DATACOLLECTION  "+e.toString());
				e.printStackTrace();
			}
			System.out.println("   New Cover Bean Proposal No...."+current_no);
			return current_no;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return current_no;
	}

	public String[][] getLCs(String openID)
	{
		String[][] getBrokerName = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = openID;
			sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,LC_CURRENCY_ID from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO=? and status='Y' and LC_NUMBER not in ('None','NONE')";
			getBrokerName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}
		return getBrokerName;
	}


	public String getBname(String bankid,String cid)
	{
		String check="";
		String sql = "";
		String args[] = new String[2];
		try
		{	
			sql = "select bank_name from open_cover_bank_master where bank_id=? and status='Y' and BELONGING_COUNTRY_ID=?";
			args[0] = bankid;
			args[1] = cid;
			check = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
	}


	public String[][] getLcDetails(String openID,String lcNo)
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = openID;
			args[1] = lcNo;
			sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,to_char(LC_DATE,'DD') DAY,to_char(LC_DATE,'MM') MONTH,to_char(LC_DATE,'YYYY') YEAR,LC_CURRENCY_ID,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,to_char(EXPIRY_DATE,'DD') DAY,to_char(EXPIRY_DATE,'MM') MONTH,to_char(EXPIRY_DATE,'YYYY') from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO=? and  LC_ID=?";
			getBrokerName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}


	public String getCName(String cid,String countryID)
	{
		String check="";
		String sql = "";
		String args[] = new String[3];
		try
		{	
			args[0] = cid;
			args[1] = cid;
			args[2] = countryID;
			sql = "select currency_name from currency_master where currency_id=? and status='Y' and amend_id=(select max(amend_id) from currency_master where currency_id=? and COUNTRY_ID=? and status='Y')";
			check = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
	}
	public String[][] getsLCDetailsByOpenCover(String opencover,String lcNo)
	{
		String[][] getsCountry = new String[0][0];
		String lcQuery = "";
		String args[] = new String[2];
		try
		{
			args[0] = lcNo;
			args[1] = opencover;
			lcQuery = "select  OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID from open_cover_lc_master where lc_id=? and open_Cover_no=?";
			getsCountry = runner.multipleSelection(lcQuery,args);
		}
		catch(Exception e)
		{
			System.out.println("The Get getsLCDetails Value Is --->>"+e);
			e.printStackTrace();
		}
		return getsCountry;
	}
	
	public String[][] getCertificates(String openID,String lcnumber)
	{
		String[][] getBrokerName = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = openID;
			args[1] = lcnumber;
			sql = "select a.open_cover_no,a.policy_no,b.total_sum_insured,b.exchange_rate,b.total_sum_insured*b.exchange_rate suminsured,b.open_lc_id,a.login_id,c.lc_number,c.LC_AMOUNT from position_master a,marine_Data b,open_Cover_lc_master c where a.application_no=b.application_no and b.open_lc_id=c.lc_id and a.open_cover_no=c.open_cover_no and a.open_cover_no=? and c.lc_number=? and  a.status='P'";
			getBrokerName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getBrokerName;
	}
	
	public String[][] getBrokers(String branchCode)
	{
		String[][] getBrokerName= new String[0][0];
		String sql="";
		try
		{
			if(!branchCode.contains("'"))
				branchCode = "'" + branchCode + "'";
			
//			sql = "select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where status!='N' and oa_code in(select oa_code from login_user_details where product_id='11' and status='Y' and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+")))) order by lower(bcm.COMPANY_NAME)";
			sql = "SELECT BCM.COMPANY_NAME,PI.LOGIN_ID FROM BROKER_COMPANY_MASTER BCM,PERSONAL_INFO PI,LOGIN_MASTER LM,LOGIN_USER_DETAILS LUD WHERE BCM.AGENCY_CODE=PI.AGENCY_CODE AND BCM.STATUS='Y' AND PI.STATUS=BCM.STATUS AND PI.APPLICATION_ID = '2' AND PI.LOGIN_ID=LM.LOGIN_ID AND LM.STATUS!='N' AND LM.OA_CODE=LUD.OA_CODE AND LUD.PRODUCT_ID='11' AND LUD.AGENCY_CODE=BCM.AGENCY_CODE AND BCM.BRANCH_CODE="+branchCode;
			getBrokerName = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting from getBrokers ...."+e.toString());
			e.printStackTrace();
		}
		return getBrokerName;
	}
	
	public String getDirectLoginId(String branchCode) {
		String loginId = "";
		try {
			String query = "select remarks from constant_detail where category_id='129' and branch_code="+branchCode;
			loginId = runner.singleSelection(query);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return loginId;
	}
	
	public String[][] getexecutiveIdList() {
		String[][] resultList = new String[0][0];
		try {
			String query = "SELECT AC_EXECUTIVE_ID, AC_EXECUTIVE_NAME FROM LOGIN_EXECUTIVE_DETAILS WHERE STATUS='Y' ORDER BY AC_EXECUTIVE_NAME";
			resultList = runner.multipleSelection(query);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return resultList;
		
	}

	public String[][] getBrokersQuoteRegister(String branchCode)
	{
		String[][] getBroName=null;
		String sql = "";
		try
		{
			sql = "select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' " +
					"and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where" +
					" ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from " +
					"broker_company_master where branch_code in("+branchCode+"))) and ocpm.STATUS='Y' and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO) " +
							"order by lower(bcm.COMPANY_NAME)";
			getBroName=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getBrokersQuoteRegister"+e.toString());
			e.printStackTrace();
		}
			return getBroName;
	}

	public String[][] getBrokersHasCover(String branchCode)
	{
		String[][] getBrokerName=null;
		String sql="";
		try
		{
			//sql = "select  bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) and ocpm.STATUS in('P','D') and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and ocpm.EXPIRY_DATE>(select sysdate from dual)) order by lower(bcm.COMPANY_NAME) ";
			sql = "select  bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) and ocpm.STATUS in('P','D') and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO) order by lower(bcm.COMPANY_NAME) ";
			getBrokerName=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getBrokersHasCover"+e.toString());
			e.printStackTrace();
		}
		return getBrokerName;
	}
	
	public String[][] getDeactivateOpencCoverBrokersHasCover(String branchCode)
	{
		String[][] getBrokerName=null;
		String sql="";
		try
		{
			sql = "select  bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code " +
					" and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER" +
					" ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' " +
					"and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) " +
							"and ocpm.STATUS in('D','P') and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO) order by lower(bcm.COMPANY_NAME) ";
			getBrokerName=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getBrokersHasCover"+e.toString());
			e.printStackTrace();
		}
		return getBrokerName;
	}

	public String[][] getCurrencyName(String loginBra)
	{
		String sql =""; 
		String res[][] = new String[0][0];
		String args[] = new String[1];
		try
		{
			loginBra = "01";
			//loginBra = loginBra.replaceAll("'","");
			args[0] = loginBra;
			sql = "SELECT CURRENCY_ABBREVIATION,DECIMAL_PLACES from BRANCH_MASTER where BRANCH_CODE=?";
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	// Sale Term Loading
	
	public String[][] getSaleTerms(String branch)
	{
		String[][] valueBasis = new String[0][0];
		try
		{
			String args[] = new String[2];
			args[0] = branch;
			args[1] = "Y";
			String sql = "select sale_term_id,sale_term_name from sale_term_master where branch_code=? and status=? order by sale_term_name";
			valueBasis = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valueBasis;
	}
	public String[][] getSaleTermList(String branch, String saleTermIds)
	{
		String[][] valueBasis = new String[0][0];
		try
		{
			String args[] = new String[2];
			args[0] = branch;
			args[1] = "Y";
			String sql = "select sale_term_id,sale_term_name from sale_term_master where branch_code=? and status=? and sale_term_id in ("+saleTermIds+") and sale_term_id not in (205) order by sale_term_name";
			valueBasis = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valueBasis;
	}
	public String getAnnualTurnover(String proposalNo)
	{
		String result=runner.singleSelection("SELECT NVL(A.CROSS_VOYAGE_TURNOVER,0) FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)", new String[]{proposalNo});
		return result;
	}
	public boolean isCkeyExists(String proposalNo, String ckey)
	{
		String result=runner.singleSelection("SELECT COUNT(*) FROM OPEN_COVER_POSITION_MASTER WHERE PROPOSAL_NO!=? AND CKEY=?", new String[]{proposalNo,ckey});
		return !"0".equals(result);
	}
	public String getDefaultCluases() {
		return defaultCluases;
	}
	public void setDefaultCluases(String defaultCluases) {
		this.defaultCluases = defaultCluases;
	}
	public String getExecutiveId() {
		return executiveId;
	}
	public void setExecutiveId(String executiveId) {
		this.executiveId = executiveId;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public String getUtilizedAmount() {
		return utilizedAmount;
	}
	public void setUtilizedAmount(String utilizedAmount) {
		this.utilizedAmount = utilizedAmount;
	}
	public String getDebitToNo() {
		return debitToNo;
	}
	public void setDebitToNo(String debitToNo) {
		this.debitToNo = debitToNo;
	}
	public String getCreditToNo() {
		return creditToNo;
	}
	public void setCreditToNo(String creditToNo) {
		this.creditToNo = creditToNo;
	}
	public String getDepositeYn() {
		return depositeYn;
	}
	public void setDepositeYn(String depositeYn) {
		this.depositeYn = depositeYn;
	}
	public String getDepositeType() {
		return depositeType;
	}
	public void setDepositeType(String depositeType) {
		this.depositeType = depositeType;
	}
	public String getDepositeInsType() {
		return depositeInsType;
	}
	public void setDepositeInsType(String depositeInsType) {
		this.depositeInsType = depositeInsType;
	}
	public String getMarinePre() {
		return marinePre;
	}
	public void setMarinePre(String marinePre) {
		this.marinePre = marinePre;
	}
	public String getWarPre() {
		return warPre;
	}
	public void setWarPre(String warPre) {
		this.warPre = warPre;
	}
	public String getDebitDate() {
		return debitDate;
	}
	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}
	public String getEndorsementFee() {
		return endorsementFee;
	}
	public void setEndorsementFee(String endorsementFee) {
		this.endorsementFee = endorsementFee;
	}
	public String getHaulierType() {
		return haulierType;
	}
	public void setHaulierType(String haulierType) {
		this.haulierType = haulierType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public void setExistDepositeYn(String existDepositeYn) {
		this.existDepositeYn = existDepositeYn;
	}
	public String getExistDepositeYn() {
		return existDepositeYn;
	}
}// Class