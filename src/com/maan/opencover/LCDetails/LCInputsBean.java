package com.maan.opencover.LCDetails;

import com.maan.common.error.ErrorInfo;
import com.maan.common.util.StringUtil;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.HttpSession;
import proj.sql.QueryBuilder;
import com.maan.services.util.runner;

public class LCInputsBean extends ErrorInfo
{

    private String sqlQuery_;
    private String commoditys[][];
    private String policyDate;
    private String expDate;
    private String currency;
    private String currencyValue;
    private String policyDay;
    private String policyMonth;
    private String policyYear;
    private String expDay;
    private String expMonth;
    private String expYear;
    private String stageId;
    private String applicationNo;
    private String loginCode;
    private String sessionId;
    private String productId;
    private String companyId;
    private int application_no;
    private String openCoverNo;
    private String bankNameLc;
    private String lcNumber;
    private String lcAmount;
    private String lcCurrencyId;
    private String lcDate;
    private String lcEntryDate;
    private String lcEffectiveDate;
    private String lcId;
    private String lcStatus;
    private String lcOtherStatus;
    private String lcOtherBank;
    private String lcBankAddress;
    private String saleTerm;
	private double actualLcAmout = 0;
    public LCInputsBean()
    {
        sqlQuery_ = "";
        commoditys = (String[][])null;
        policyDate = "";
        expDate = "";
        currency = "";
        currencyValue = "";
        policyDay = "";
        policyMonth = "";
        policyYear = "";
        expDay = "";
        expMonth = "";
        expYear = "";
        stageId = "";
        applicationNo = "";
        loginCode = "";
        sessionId = "";
        productId = "";
        companyId = "";
        application_no = 0;
        openCoverNo = "";
        bankNameLc = "";
        lcNumber = "";
        lcAmount = "";
        lcCurrencyId = "";
        lcDate = "";
        lcEntryDate = "";
        lcEffectiveDate = "";
        lcId = "";
        lcStatus = "";
        lcOtherStatus = "";
        lcOtherBank = "";
        lcBankAddress = "";
        saleTerm = "";
		actualLcAmout = 0;
    }
	public void setActualLcAmout(double actualLcAmout)
	{
		this.actualLcAmout = actualLcAmout;
	}
	public double getActualLcAmout()
	{
		return this.actualLcAmout;
	}
    public void setOpenCoverNo(String s)
    {
        openCoverNo = s;
    }

    public String getOpenCoverNo()
    {
        return openCoverNo;
    }
	 public void setSaleTerm(String saleTerm)
    {
        this.saleTerm = saleTerm;
    }

    public String getSaleTerm()
    {
        return this.saleTerm;
    }

    public String getApplicationNo()
    {
        return applicationNo;
    }

    public void setApplicationNo(String s)
    {
        applicationNo = s;
    }

    public String getCompanyId()
    {
        return companyId;
    }

    public void setCompanyId(String s)
    {
        companyId = s;
    }

    public String getLoginCode()
    {
        return loginCode;
    }

    public void setLoginCode(String s)
    {
        loginCode = s;
    }

    public String getProductId()
    {
        return productId;
    }

    public void setProductId(String s)
    {
        productId = s;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String s)
    {
        sessionId = s;
    }

    public String getStageId()
    {
        return stageId;
    }

    public void setStageId(String s)
    {
        stageId = s;
    }

    public String getLcAmount()
    {
        return lcAmount;
    }

    public void setLcAmount(String s)
    {
        lcAmount = s;
    }

    public String getLcCurrencyId()
    {
        return lcCurrencyId;
    }

    public void setLcCurrencyId(String s)
    {
        lcCurrencyId = s;
    }

    public String getLcNumber()
    {
        return lcNumber;
    }

    public void setLcNumber(String s)
    {
        lcNumber = s;
    }

    public String getBankNameLc()
    {
        return bankNameLc;
    }

    public void setBankNameLc(String s)
    {
        bankNameLc = s;
    }

    public String getPolicyDay()
    {
        return policyDay;
    }

    public void setPolicyDay(String s)
    {
        policyDay = s;
    }

    public String getPolicyMonth()
    {
        return policyMonth;
    }

    public void setPolicyMonth(String s)
    {
        policyMonth = s;
    }

    public String getPolicyYear()
    {
        return policyYear;
    }

    public void setPolicyYear(String s)
    {
        policyYear = s;
    }

    public String getExpDay()
    {
        return expDay;
    }

    public void setExpDay(String s)
    {
        expDay = s;
    }

    public String getExpMonth()
    {
        return expMonth;
    }

    public void setExpMonth(String s)
    {
        expMonth = s;
    }

    public String getExpYear()
    {
        return expYear;
    }

    public void setExpYear(String s)
    {
        expYear = s;
    }
	public String getLcStatus()
    {
        return this.lcStatus;
    }

    public void setLcStatus(String lcStatus)
    {
        this.lcStatus = lcStatus;
    }

	public String getLcOtherStatus() {
		return lcOtherStatus;
	}
	public void setLcOtherStatus(String lcOtherStatus) {
		this.lcOtherStatus = lcOtherStatus;
	}
	public String getLcOtherBank() {
		return lcOtherBank;
	}
	public void setLcOtherBank(String lcOtherBank) {
		this.lcOtherBank = lcOtherBank;
	}
	public String getLcBankAddress() {
		return lcBankAddress;
	}
	public void setLcBankAddress(String lcBankAddress) {
		this.lcBankAddress = lcBankAddress;
	}
	public StringBuffer getBankDetails(String cid)
    {
    	String args[] = new String[2];
		StringBuffer stringbuffer = new StringBuffer();
        try
        {
			args[0] = cid;
			args[1] = cid;

			sqlQuery_ = "select bank_id,bank_name,amend_id from open_cover_bank_master where amend_id||'-'||bank_id in (select max(amend_id)||'-'||bank_id from open_cover_bank_master where to_date(effective_date,'dd-mm-yyyy')<=to_date(SYSDATE,'dd-mm-yyyy') and status in('N','Y') and BELONGING_COUNTRY_ID=? group by bank_id) and BELONGING_COUNTRY_ID=? and status='Y' and bank_id not in ('99999') order by bank_name";
        
            String as[][] = runner.multipleSelection(sqlQuery_,args);
            stringbuffer.append("<select name='bankNameLc' class='scrolLet' style='width:300px'>");
            stringbuffer.append("<option value ='0'>Select</option>");
            for(int i = 0; i < as.length; i++)
            {
                String s1;
                if(as[i][0].equalsIgnoreCase(bankNameLc))
                {
                    s1 = "selected";
                } else
                {
                    s1 = "";
                }
                stringbuffer.append("<option value = '" + as[i][0] + "'" + s1 + ">" + StringUtil.upperFirstChar(as[i][1]) + "</option>");
            }

            stringbuffer.append("</select>");
        }
        catch(Exception exception)
		{
			System.out.println("getBankDetails....."+exception);
			exception.printStackTrace();
		}
        return stringbuffer;
    }

    public void insertOrUpdateLCDetails(String s,String cid,String branch)
    {
		String args[] = new String[2];
		String res = "";

    	int i = 0;
        String s1 = "";
        GregorianCalendar gregoriancalendar = new GregorianCalendar();
        int j = gregoriancalendar.get(10);
        int k = gregoriancalendar.get(11);
        int l = gregoriancalendar.get(12);
        int i1 = gregoriancalendar.get(13);
        int j1 = gregoriancalendar.get(14);
        int k1 = gregoriancalendar.get(9);

		policyDate = policyDay + "-" + policyMonth + "-" + policyYear + " " + k + ":" + l + ":" + i1;
        expDate = expDay + "-" + expMonth + "-" + expYear + " " + k + ":" + l + ":" + i1;

        
		args[0] = openCoverNo;
		args[1] = s;
		sqlQuery_ = "select count(*) from open_cover_lc_master where open_cover_No=? and lc_Id=?";
     
        try
        {
			res = runner.singleSelection(sqlQuery_,args);
        	if(res.length()>0 && res!=null && !res.equalsIgnoreCase("null"))
			{
				i = Integer.parseInt(res);
				
			}
			else
			{
				i = 0;
				
			}
        }
        catch(Exception exception)
        {
            System.out.println("Exception in insertOrUpdateLCDetails() for Count :" + exception.toString());
			exception.printStackTrace();
        }
        com.maan.premium.DAO.PremiumInputsBean premiumInputs= new com.maan.premium.DAO.PremiumInputsBean();
		premiumInputs.setLoginBra(branch);
        if(i <= 0)
        {
            try
            {
				String queryValues[] = new String[17];
            	sqlQuery_ = "insert into open_cover_lc_master (lc_number,lc_amount,bank_id,AMEND_ID,LC_DATE,EFFECTIVE_DATE,ENTRY_DATE,Expiry_DATE,REMARKS,STATUS,open_cover_no,lc_id,lc_currency_id,LC_BALANCE_AMOUNT,LC_ACTUAL_AMOUNT,LC_SALE_TERM_ID,OTHER_BANK_STATUS,OTHER_BANK_NAME,LC_BANK_ADDRESS) values(?,?,?,?,TO_DATE(?,'dd-mm-yyyy hh24:mi:ss'),SYSDATE,SYSDATE,TO_DATE(?,'dd-mm-yyyy hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?)";
            
                double lcAmtSale = 0;
				lcAmtSale = Double.parseDouble(lcAmount)+(Double.parseDouble(lcAmount) * ((premiumInputs.getSaleTermValue(saleTerm))/100));

				String s2 = "" + getMaximumId(openCoverNo, "LCID");
				double exRate = getExchangeRate(lcCurrencyId,cid);
				double balance = lcAmtSale * exRate;
				lcStatus = lcStatus==null?"Y":lcStatus;
				queryValues[0] = lcNumber;
				queryValues[1] = "" + lcAmtSale;
				queryValues[2] = bankNameLc.equals("0")?"999999":bankNameLc;
				queryValues[3] = "0";
				queryValues[4] = policyDate;
				queryValues[5] = expDate;
				queryValues[6] = "Normal";
				queryValues[7] = lcStatus;
				queryValues[8] = openCoverNo;
                queryValues[9] = ""+Integer.parseInt(s2);
				queryValues[10] = ""+Integer.parseInt(lcCurrencyId);
				queryValues[11] = ""+balance;
				queryValues[12] = "" + Double.parseDouble(lcAmount);
				queryValues[13] = saleTerm;
				queryValues[14] = lcOtherStatus;
				queryValues[15] = lcOtherBank;
				queryValues[16] = lcBankAddress;
				
				runner.multipleInsertion(sqlQuery_,queryValues);
            }
            catch(Exception exception1)
            {
                System.out.println("Exception in insertOrUpdateLCDetails()  Insertion :" + exception1.toString());
				exception1.printStackTrace();
            }
        }
		else
        {
            try
            {
				String queryValues[] = new String[15];
				sqlQuery_ ="update open_cover_lc_master set lc_amount=?,bank_id=?,AMEND_ID=?,LC_DATE=TO_DATE(?,'dd-mm-yyyy hh24:mi:ss'),EXPIRY_DATE=TO_DATE(?,'dd-mm-yyyy hh24:mi:ss'),EFFECTIVE_DATE=SYSDATE,ENTRY_DATE=SYSDATE,lc_currency_id=?,status=?,LC_BALANCE_AMOUNT=?,LC_ACTUAL_AMOUNT=?,LC_SALE_TERM_ID=?,OTHER_BANK_STATUS=?,OTHER_BANK_NAME=?,LC_BANK_ADDRESS=? where open_cover_NO=? and lc_id=?";

                String s3 = "TO_DATE('" + policyDate + "','dd-mon-yyyy hh24:mi:ss')";
                double lcAmtSale = 0;
				lcAmtSale = Double.parseDouble(lcAmount)+(Double.parseDouble(lcAmount) * ((premiumInputs.getSaleTermValue(saleTerm))/100));

				lcStatus = lcStatus==null?"Y":lcStatus;
				queryValues[0] = ""+lcAmtSale;
				queryValues[1] = bankNameLc.equals("0")?"999999":bankNameLc;
				queryValues[2] = "0";
				queryValues[3] = policyDate;
				queryValues[4] = expDate;
				queryValues[5] = ""+Integer.parseInt(lcCurrencyId);
				queryValues[6] = lcStatus;
				queryValues[7] = ""+actualLcAmout;
				queryValues[8] = "" + Double.parseDouble(lcAmount);
				queryValues[9] = saleTerm;
				queryValues[10] = lcOtherStatus;
				queryValues[11] = lcOtherBank;
				queryValues[12] = lcBankAddress;
				queryValues[13] = openCoverNo;
				queryValues[14] = s;

				
				
                runner.multipleUpdation(sqlQuery_,queryValues);
            }
            catch(Exception exception2)
            {
                System.out.println("Exception in UpdateCompany Infor :" + exception2.toString());
				exception2.printStackTrace();
            }
        }
    }
	public String getGeneralValue(String selectName,String tableName,String valueColumn,String value)
	{
		String valueFetched="";
		try
		{
			sqlQuery_="select "+selectName+"  from "+tableName+" where "+valueColumn+"='"+value+"'";
			valueFetched = runner.singleSelection(sqlQuery_);
		}
		catch(Exception e)
		{
			System.out.println("The Exception occured in getGeneralValue--"+e.toString());e.printStackTrace();
		}
		return valueFetched;
	}		
    public String getApplicationNoBasedOpenCoverNo(String appNo)
    {
        String openCoverNo = "";
        try
        {
            String sql = "select md.open_cover_no as openCoverNo from marine_data md where md.application_no=? and md.status=?";
            String args[] = new String[1];
			args[0] = appNo;
			openCoverNo = runner.singleSelection(sql,args);
        }
        catch(Exception e)
        {
            System.out.println("Exception in getApplicationNoBasedOpenCoverNo Infor :" + e.toString());e.printStackTrace();
        }
        return openCoverNo;
    }

    public void print(String s, String s1, String s2)
    {
        System.out.println(s + "<--->" + s2 + "<---->" + s1);
    }
    public int getMaximumId(String openNo,String queryStatus)
	{
		int  s_id =	1;
		String sql = "";

		if("LCID".equalsIgnoreCase(queryStatus))
		{
			sql ="select nvl(max(lc_id),'0')+1 from open_cover_lc_master where open_cover_No=?";
		  try
		  {
			  String args[] = new String[1];
			  args[0] = openNo;
			  String temp = runner.singleSelection(sql,args);		
			  if(temp.length()>0)
				s_id = Integer.parseInt(temp);
		  }
		  catch(Exception e)
		  {
			  System.out.println("the EXCEPTION IN "+e.toString());
		  }
			if(s_id==0)
			{
				s_id=1;
			}
		}
		return s_id;
	}

   
    public StringBuffer getCurrencyDetails(String cid)
    {
    	String args[] = new String[2];
		String as[][] = new String[0][0];
        sqlQuery_ = "select b.currency_id,b.currency_name,a.amend_id,a.effective_date,a.exchange_rate from exchange_master a, currency_master b where a.COUNTRY_ID=b.COUNTRY_ID and b.COUNTRY_ID=? and a.currency_id=b.currency_id and a.amend_id||a.exchange_id||a.currency_id in(select max(amend_id)||exchange_id||currency_id from exchange_master where effective_date <=SYSDATE and status='Y' and COUNTRY_ID=? group by exchange_id,currency_id)  and a.effective_date <=SYSDATE and a.status='Y' and b.status='Y' and b.status='Y' and a.status='Y'";

        StringBuffer stringbuffer = new StringBuffer();
        try
        {
			args[0] = cid;
			args[1] = cid;
            as = runner.multipleSelection(sqlQuery_,args);
            String s = "";
            stringbuffer.append("<select name='lcCurrencyId' class='scrolLet' style='width:133px'>");
            stringbuffer.append("<option value ='0'>Select</option>");
            for(int i = 0; i < as.length; i++)
            {
                String s1;
                if(as[i][0].equalsIgnoreCase(lcCurrencyId))
                {
                    s1 = "selected";
                } else
                {
                    s1 = "";
                }
                stringbuffer.append("<option value = '" + as[i][0] + "'" + s1 + ">" + StringUtil.upperFirstChar(as[i][1]) + "</option>");
            }

            stringbuffer.append("</select>");
        }
        catch(Exception e) 
		{
			System.out.println(" getCurrencyDetails  "+e);
			e.printStackTrace();
		}
        return stringbuffer;
    }
	//Admin side LC Master Creation
	public String[][] getBrokersHasCover(String branchCode)
	{
		String[][] getBrokerName=null;
		String sql="";
		try
		{
			sql = "select  bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and pi.APPLICATION_ID='2' and pi.login_id in(select distinct ocm.broker_id from OPEN_COVER_MASTER ocm,OPEN_COVER_POSITION_MASTER ocpm where ocm.broker_id in(select login_id from login_master where status!='N' and oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) and ocpm.STATUS='P' and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and ocpm.EXPIRY_DATE>(select sysdate from dual)) order by lower(bcm.COMPANY_NAME) ";
			getBrokerName=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getBrokersHasCover"+e.toString());
			e.printStackTrace();
		}
		return getBrokerName;
	}
	public String[][] getLCs(String openID)
	{
		String[][] getBrokerName = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = openID;
			sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,LC_CURRENCY_ID from OPEN_COVER_LC_MASTER  where  OPEN_COVER_NO=? and LC_NUMBER not in ('None','NONE')";
			getBrokerName = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();		
		}
		return getBrokerName;
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
			sql = "select a.open_cover_no,a.policy_no,b.total_sum_insured,b.exchange_rate,(b.total_sum_insured*b.exchange_rate)+b.TOTAL_SALE_TERM_CHARGES+b.TOTAL_TOLERANCE_CHARGES suminsured,b.open_lc_id,a.login_id,c.lc_number,c.LC_AMOUNT from position_master a,marine_Data b,open_Cover_lc_master c where a.application_no=b.application_no and b.open_lc_id=c.lc_id and a.open_cover_no=c.open_cover_no and a.open_cover_no=? and c.lc_number=? and  a.status='P'";
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
	public String getOtherBname(String openNo,String bankid,String lcNo)
	{
		String check="";
		String sql = "";
		String args[] = new String[2];
		try
		{	
			sql = "select OTHER_BANK_NAME from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and status='Y' and LC_NUMBER=?";
			args[0] = openNo;
			args[1] = lcNo;
			check = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return check;
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
	//select  bcm.company_name,pi.login_id,ocpm.open_cover_no,oclm.LC_NUMBER from BROKER_COMPANY_MASTER bcm,personal_info pi,OPEN_COVER_MASTER ocm, OPEN_COVER_POSITION_MASTER ocpm,OPEN_COVER_LC_MASTER oclm where bcm.agency_code=pi.agency_code  and pi.APPLICATION_ID='2' and ocm.broker_id=pi.login_id and ocm.proposal_no=ocpm.proposal_no and ocpm.STATUS='P' and bcm.branch_code='020' and oclm.open_cover_no=ocpm.open_cover_no order by bcm.company_name,ocpm.open_cover_no
	public HashMap getLCBrokerDetailReports(String loginBranch)
	{
		String sql = "select  bcm.company_name,pi.login_id,ocpm.open_cover_no,oclm.LC_NUMBER from BROKER_COMPANY_MASTER bcm,personal_info pi,OPEN_COVER_MASTER ocm, OPEN_COVER_POSITION_MASTER ocpm,OPEN_COVER_LC_MASTER oclm where bcm.agency_code=pi.agency_code  and pi.APPLICATION_ID='2' and ocm.broker_id=pi.login_id and ocm.proposal_no=ocpm.proposal_no and ocpm.STATUS not in('Y') and bcm.branch_code=? and oclm.open_cover_no=ocpm.open_cover_no and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no group by ocms.proposal_no) order by bcm.company_name,ocpm.open_cover_no";
		String args[] = new String[1];
		args[0] = loginBranch;
		String result[][] = new String[0][0];
		try
		{
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		int b=0;
		int o =0;
		int openCount =0;
		int lcCount =0;
		HashMap bnameMap = new HashMap();
		String lastBname = "";
		try
		{
			
			for(int i=0;i<result.length;i++)
			{
				boolean flag = false;
				if(!bnameMap.containsValue(result[i][0]))
				{
					bnameMap.put("bro"+b,result[i][0]);
					bnameMap.put("login"+b,result[i][1]);
					b++;
					flag = true;
					lastBname = result[i][0];
				}
				if(i!=0&&flag)
				{
					bnameMap.put("openCount"+result[i-1][0],""+openCount);
					bnameMap.put("lcCount"+result[i-1][0],""+lcCount);
					openCount = 0;lcCount=0;
				}
				if(bnameMap.containsValue(result[i][0]))
				{
					if(!bnameMap.containsValue(result[i][2]))
					{
						bnameMap.put("open"+o,result[i][2]);
						o++;
						openCount++;
					}
					if(!result[i][3].equalsIgnoreCase("NONE"))
					{
						lcCount++;
					}
				}
			}
			bnameMap.put("openCount"+lastBname,""+openCount);
			bnameMap.put("lcCount"+lastBname,""+lcCount);
			bnameMap.put("size",""+b);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bnameMap;
	}
	//admin side
	public String[][] getOpenCoverNos(String login)
	{
		String result[][] = new String[0][0];
		try
		{
			String sql = "select distinct(ocpm.open_cover_no),ocm.MISSIPPI_OPENCOVER_NO,nvl(pi.company_name,pi.first_name) from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id=? and ocm.status='Y' and ocpm.status not in('Y') and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id  and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			String args[] = new String[1];
			args[0] = login;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	//Broker user side
	public String[][] getOpenCoverNos(String login,String openNo)
	{
		String result[][] = new String[0][0];
		try
		{
			String sql = "select distinct(ocpm.open_cover_no),ocm.MISSIPPI_OPENCOVER_NO,nvl(pi.company_name,pi.first_name) from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where ocm.broker_id=? and ocm.status='Y' and ocpm.status not in('Y') and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id  and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no group by ocms.proposal_no) and ocpm.open_cover_no=? order by ocpm.open_cover_no  DESC";
			String args[] = new String[2];
			args[0] = login;
			args[1] = openNo;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getsLCDetailsByOpenCover(String opencover, String lcNo) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select  OPEN_COVER_NO,BANK_ID,LC_NUMBER,LC_DATE,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID from open_cover_lc_master where LC_ID=? and open_Cover_no=?";
		try 
		{
			String args[] = new String[2];
			args[0] = lcNo;
			args[1] = opencover;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;
	}
	public String[][] getLcDetails(String openID, String lcNo) 
	{
		String[][] brokerDetails = new String[0][0];
		String sql = "select OPEN_COVER_NO,BANK_ID,LC_NUMBER,to_char(LC_DATE,'DD') DAY,to_char(LC_DATE,'MM') MONTH,to_char(LC_DATE,'YYYY') YEAR,LC_CURRENCY_ID,LC_AMOUNT,ENTRY_DATE,EXPIRY_DATE,EFFECTIVE_DATE,AMEND_ID,REMARKS,STATUS,LC_ID,to_char(EXPIRY_DATE,'DD') DAY,to_char(EXPIRY_DATE,'MM') MONTH,to_char(EXPIRY_DATE,'YYYY'),nvl(LC_BALANCE_AMOUNT,'0'),nvl(LC_SALE_TERM_ID,'0'),nvl(LC_ACTUAL_AMOUNT,'0'),LC_BANK_ADDRESS,OTHER_BANK_NAME,OTHER_BANK_STATUS from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and LC_ID=?";
		try 
		{
			String args[] = new String[2];
			args[0] = openID;
			args[1] = lcNo;
			brokerDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return brokerDetails;

	}
	public double getExchangeRate(String currencyId,String cid)
	{
		String args[] = new String[4];
		String sql="";
		double exRate = 0;
		args[0] = currencyId;
		args[1] = cid;
		args[2] = currencyId;
		args[3] = cid;
		sql = "select nvl(exchange_rate,'0') from exchange_master where currency_id=? and status='Y' and COUNTRY_ID=? and amend_id=(select max(amend_id) from exchange_master where currency_id=? and status='Y' and COUNTRY_ID=?)";

		try
		{
			String temp = runner.singleSelection(sql,args);
			if(temp.length()<=0)
				temp="0";
			exRate = Double.parseDouble(temp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return exRate;
	}
	public void deleteLCDetails(String openNo,String lcid)
	{
		String args[] = new String[2];
		args[0]= openNo;
		args[1]= lcid;
		String sql = "delete from OPEN_COVER_LC_MASTER where OPEN_COVER_NO=? and LC_ID=?";
		try
		{
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String[][] getLCDetailsReports(String lcId,String bid,String openNo,String roundOpt)
	{
		String sql = "select pm.policy_no,nvl(per.first_name,per.company_name),to_char(pm.inception_date,'dd-mm-yyyy'),to_char(md.inception_Date,'dd-mm-yyyy'),round((md.total_sum_insured*md.exchange_rate)+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES,"+roundOpt+"),round(pm.premium+pm.excess_premium,"+roundOpt+") from marine_data md,position_master pm,personal_info per where md.application_no=pm.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=? and per.customer_id = pm.customer_id order by substr(pm.policy_no,9,16) desc";
		String args[] = new String[3];
		String result[][] = new String[0][0];
		try
		{
			args[0] = lcId;
			args[1] = bid;
			args[2] = openNo;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public HashMap getLcAmtDetails(String lcId,String bid,String openNo,double usedAmt,String cid)
	{
		String sql = "select LC_AMOUNT,LC_CURRENCY_ID,nvl(LC_BALANCE_AMOUNT,'0'),to_char(LC_DATE,'dd-mm-yyyy') from OPEN_COVER_LC_MASTER  where LC_NUMBER not in ('None','NONE') and lc_id=? and bank_id=? and OPEN_COVER_NO=?";
		String args[] = new String[3];
		String result[][] = new String[0][0];
		HashMap detailAmt = new HashMap();
		try
		{
			args[0] = lcId;
			args[1] = bid;
			args[2] = openNo;
			result = runner.multipleSelection(sql,args);
			if(result.length>0)
			{
				double exRate = getExchangeRate(result[0][1],cid);
				double balance = 0;
				double lcActualAmt = Double.parseDouble(result[0][0])*exRate;
				if(result[0][2].equals("0"))
				{
					balance = lcActualAmt - usedAmt;
				}
				else
					balance = Double.parseDouble(result[0][2]);
				detailAmt.put("LCAmoutGiven",result[0][0]);
				detailAmt.put("LCAmout",""+lcActualAmt);
				detailAmt.put("usedAmt",""+usedAmt);
				detailAmt.put("balance",""+balance);
				detailAmt.put("exRate",""+exRate);
				detailAmt.put("LCDate",result[0][3]);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return detailAmt;
	}
	public String[][] getBcName(String login)
	{
		String result[][] = new String[0][0];
		String sql = "select nvl(bro.company_name,''),log.login_id from BROKER_COMPANY_MASTER bro,login_master log where bro.AGENCY_CODE=(select oa_code from login_master where login_id=?) and bro.AGENCY_CODE = log.AGENCY_CODE";
		try
		{
			String args[]= new String[1];
			args[0] = login;
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getLCReportsold(String openNos,String cid)
	{
		String sql = "select lm.OPEN_COVER_NO,bm.BANK_NAME,lm.LC_NUMBER,nvl(lm.LC_BALANCE_AMOUNT,'0'),lm.bank_id,lm.lc_id from OPEN_COVER_LC_MASTER lm,OPEN_COVER_BANK_MASTER bm where lm.OPEN_COVER_NO in("+openNos+") and lm.LC_NUMBER not in ('None','NONE') and bm.bank_id=lm.bank_id and BELONGING_COUNTRY_ID=? and bm.amend_id||bm.bank_id in(select max(amend_id)||bank_id from OPEN_COVER_BANK_MASTER where BELONGING_COUNTRY_ID=? group by bank_id)";
		String result[][] = new String[0][0];
		HashMap hashLCR = new HashMap();
		try
		{
			String args[]= new String[2];
			args[0] = cid;
			args[1] = cid;
			result = runner.multipleSelection(sql,args);
			/*for(int i=0;i<result.length;i++)
			{
				System.out.println("royal test LC reports..."+result[i][0]);
				hashLCR.put("BN"+result[i][0],result[i][1]);
				hashLCR.put("LN"+result[i][0],result[i][2]);
				hashLCR.put("LAmt"+result[i][0],result[i][3]);
			}*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getBranchDetails(String brokerBra)
	{
		String result[][] = new String[0][0]; 
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = brokerBra;
			sql = "select BRANCH_NAME,header_img,footer_img,sign_img,stamp,Currency_name,CURRENCY_ABBREVIATION,nvl(CURRENCY_ACRONYM,' '),ADDRESS1,REMARKS,ADDRESS2,CITY,COUNTRY,PHONE,FAX from BRANCH_MASTER where BRANCH_CODE=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get Branch Details "+ e.toString());
			e.printStackTrace();
		}
		return result;
	}
	public HashMap getLCReports(String bcode,String cid)
	{
		String sql = "select  bcm.company_name,ocm.MISSIPPI_OPENCOVER_NO,pi.customer_id,bm.bank_name,oclm.LC_NUMBER,oclm.LC_BALANCE_AMOUNT,pi.login_id,ocpm.open_cover_no,to_char(oclm.LC_DATE,'dd/mm/yyyy'),to_char(oclm.EXPIRY_DATE,'dd/mm/yyyy'),oclm.LC_AMOUNT,oclm.bank_id,oclm.lc_id,oclm.LC_CURRENCY_ID from BROKER_COMPANY_MASTER bcm,personal_info pi,OPEN_COVER_MASTER ocm, OPEN_COVER_POSITION_MASTER ocpm,OPEN_COVER_LC_MASTER oclm,OPEN_COVER_BANK_MASTER bm where bcm.agency_code=pi.agency_code  and pi.APPLICATION_ID='2' and ocm.broker_id=pi.login_id and ocm.proposal_no=ocpm.proposal_no and ocpm.STATUS not in('Y') and bcm.branch_code=? and oclm.open_cover_no=ocpm.open_cover_no and oclm.LC_NUMBER not in('NONE') and ocm.MISSIPPI_OPENCOVER_NO is not null and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no group by ocms.proposal_no) and bm.bank_id=oclm.bank_id and bm.BELONGING_COUNTRY_ID=? and bm.amend_id||bm.bank_id in(select max(amend_id)||bank_id from OPEN_COVER_BANK_MASTER where BELONGING_COUNTRY_ID=? group by bank_id) order by bcm.company_name,ocpm.open_cover_no";
		String result[][] = new String[0][0];
		String result1[][] = new String[0][0];
		HashMap cusHash = new HashMap();
		HashMap LCHash = new HashMap();
		try
		{
			String args[]= new String[3];
			args[0] = bcode;
			args[1] = cid;
			args[2] = cid;
			result = runner.multipleSelection(sql,args);
			String cusId = "";
			if(result.length>0)
			{
				for(int i=0;i<result.length;i++)
				{
					cusId = cusId + (result[i][2]!=null?result[i][2]:"0")+",";
				}
				LCHash.put("LCAll",result);
			}
			if(cusId.length()>0)
			{
				cusId = cusId.substring(0,(cusId.length()-1));
				result1 = runner.multipleSelection("select customer_id,nvl(company_name,FIRST_NAME) from PERSONAL_INFO where customer_id in("+cusId+")");
				for(int i=0;i<result1.length;i++)
				{
					if(!cusHash.containsKey(""+result1[i][0]))
						cusHash.put(""+result1[i][0],result1[i][1]);
				}
				LCHash.put("cusDetails",cusHash);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return LCHash;
	}
	public double getLCUsedSumInsured(String bankId,String lcNo,String openCoverNo)
	{
		String result="";	
		String args[] = new String[3];
		String sql = "";
		double usedAmt = 0;
		try
		{
			args[0] = lcNo;
			args[1] = bankId;
			args[2] = openCoverNo;
			
			sql = "select sum(mrd.SUMINSUREDLOCAL+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES) from marine_data md,MARINE_RESULT_DETAILS mrd,position_master pm where md.application_no=pm.application_no and md.application_no=mrd.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=?";

			result = runner.singleSelection(sql,args);
			result = result==null?"0":result;
			if(result.length()>0)
			{
				usedAmt = Double.parseDouble(result); 
			}
		}
		catch(Exception e)
		{
			System.out.println("The getLCBased getLCUsedSumInsured --------------->>>>"+e);
			e.printStackTrace();
		}
		return usedAmt;
	}

}
