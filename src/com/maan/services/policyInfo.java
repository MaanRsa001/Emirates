package com.maan.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.premium.DAO.CommodityCountryRateDAO;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;
import com.maan.premium.DAO.PremiumInputsBean;
import com.maan.services.util.runner;

public class policyInfo {
	private static final Logger logger = LogUtil.getLogger(policyInfo.class);
	private String blNo = "";
	private String blDay = "";
	private String blMonth = "";
	private String blYear = "";
	private String agentId = "";
	private String agentName = "";
	private String carrieerName = "";
	private String lcNo = "";
	private String bankName = "";
	private String lcDay = "";
	private String lcMonth = "";
	private String lcYear = "";
	private String partialAllowed = "";
	private String sdiscount = "";
	private String quoteNo = "";
	private String customerId = "";
	private String brokerRemarks = "";
	private String sailingDay = "";
	private String sailingMonth = "";
	private String sailingYear = "";
	private String sailingDate = "";
	private String lcDate = "";
	private String blDate = "";
	private String packType = "";
	private String fmsCaseNo = "";
	private String referenceNo = "";
	private String contractNo = "";

	HashMap commodityValidation = new HashMap();

	public String getSailingDate() {
		return sailingDate;
	}

	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}

	public String getLcDate() {
		return lcDate;
	}

	public void setLcDate(String lcDate) {
		this.lcDate = lcDate;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public String getFmsCaseNo() {
		return fmsCaseNo;
	}

	public void setFmsCaseNo(String fmsCaseNo) {
		this.fmsCaseNo = fmsCaseNo;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getBlDate() {
		return blDate;
	}

	public void setBlDate(String blDate) {
		this.blDate = blDate;
	}

	private String cid = "";
	private String loginBra = "";

	public String getSailingDay() {
		return sailingDay;
	}

	public void setSailingDay(String sailingDay) {
		this.sailingDay = sailingDay;
	}

	public String getSailingMonth() {
		return sailingMonth;
	}

	public void setSailingMonth(String sailingMonth) {
		this.sailingMonth = sailingMonth;
	}

	public String getSailingYear() {
		return sailingYear;
	}

	public void setSailingYear(String sailingYear) {
		this.sailingYear = sailingYear;
	}

	public void setLoginBra(String loginBra) {
		this.loginBra = loginBra;
	}

	public String getLoginBra() {
		return this.loginBra;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCid() {
		return this.cid;
	}

	public void setBrokerRemarks(String brokerRemarks) {
		this.brokerRemarks = brokerRemarks;
	}

	public String getBrokerRemarks() {
		return brokerRemarks;
	}

	public void setCommodityValidation(HashMap commodityValidation) {
		this.commodityValidation = commodityValidation;
	}

	public HashMap getCommodityValidation() {
		return commodityValidation;
	}

	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}

	public void setBlDay(String blDay) {
		this.blDay = blDay;
	}

	public void setBlMonth(String blMonth) {
		this.blMonth = blMonth;
	}

	public void setBlYear(String blYear) {
		this.blYear = blYear;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public void setCarrieerName(String carrieerName) {
		this.carrieerName = carrieerName;
	}

	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setLcDay(String lcDay) {
		this.lcDay = lcDay;
	}

	public void setLcMonth(String lcMonth) {
		this.lcMonth = lcMonth;
	}

	public void setLcYear(String lcYear) {
		this.lcYear = lcYear;
	}

	public void setPartialAllowed(String partialAllowed) {
		this.partialAllowed = partialAllowed;
	}

	public void setSdiscount(String sdiscount) {
		this.sdiscount = sdiscount;
	}

	public String getQuoteNo() {
		return quoteNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public String getBlNo() {
		return blNo;
	}

	public String getBlDay() {
		return blDay;
	}

	public String getBlMonth() {
		return blMonth;
	}

	public String getBlYear() {
		return blYear;
	}

	public String getAgentId() {
		return agentId;
	}

	public String getCarrieerName() {
		return carrieerName;
	}

	public String getLcNo() {
		return lcNo;
	}

	public String getBankName() {
		return bankName;
	}

	public String getLcDay() {
		return lcDay;
	}

	public String getLcMonth() {
		return lcMonth;
	}

	public String getLcYear() {
		return lcYear;
	}

	public String getPartialAllowed() {
		return partialAllowed;
	}

	public String getSdiscount() {
		return sdiscount;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getCurrencyName(String Quote) {
		String res = "";
		try {
			String args[] = new String[1];
			args[0] = Quote;
			res = runner
					.singleSelection(
							"select currency_name from marine_data where application_no=(select application_no from position_master where quote_no=?)",
							args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	public String getapplicationNo(String quoteNo) {
		String app = "";
		String sql = "select application_no from position_master where quote_no=?";
		try {
			String args[] = new String[1];
			args[0] = quoteNo;
			app = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return app;
	}

	public String getCustomerId(String quoteNo) {
		String app = "";
		String sql = "select customer_id from position_master where quote_no=?";
		try {
			String args[] = new String[1];
			args[0] = quoteNo;
			app = runner.singleSelection(sql, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return app;

	}

	public void settingValues(String quoteId, String customerId,
			String branchCode) {
		try {
			String args[] = new String[1];
			args[0] = quoteId;
			// String qry =
			// "select nvl(a.bl_awb_no,' '),to_char(a.bl_awb_date,'dd') as days,to_char(a.bl_awb_date,'MM') as months,to_char(a.bl_awb_date,'YYYY') as years,a.settling_agent_id,nvl(a.carrier_name,' '),nvl(a.lc_number,'0'),nvl(a.bank_name,' '),to_char(a.lc_date,'dd') as days,to_char(a.lc_date,'MM') as months,to_char(a.lc_date,'YYYY') as years,a.partial_shipment_allowed,nvl(a.special_discount,'0'),a.SETTLING_AGENT_NAME,b.broker_remarks,to_char(a.sailing_date,'dd') as sailing_days,to_char(a.sailing_date,'MM') as sailing_months,to_char(a.sailing_date,'YYYY') as sailing_years from marine_policy_details a,position_master b where a.quote_no=? and b.quote_no=a.quote_no";
			String qry = "select nvl(a.bl_awb_no,' '),to_char(a.bl_awb_date,'dd') as days,to_char(a.bl_awb_date,'MM') as months,to_char(a.bl_awb_date,'YYYY') as years,a.settling_agent_id,nvl(a.carrier_name,' '),nvl(a.lc_number,'0'),nvl(a.bank_name,' '),to_char(a.lc_date,'dd') as days,to_char(a.lc_date,'MM') as months,to_char(a.lc_date,'YYYY') as years,a.partial_shipment_allowed,nvl(a.special_discount,'0'),a.SETTLING_AGENT_NAME,b.broker_remarks,to_char(a.sailing_date,'dd') as sailing_days,to_char(a.sailing_date,'MM') as sailing_months,to_char(a.sailing_date,'YYYY') as sailing_years,to_char(a.bl_awb_date,'dd/MM/yyyy'),to_char(a.lc_date,'dd/MM/yyyy'),to_char(a.sailing_date,'dd/MM/yyyy'),package_code,ghq_contract_no,ghq_fms_case_no,ghq_reference_no from marine_policy_details a,position_master b where a.quote_no=? and b.quote_no=a.quote_no";
			String[][] getData = runner.multipleSelection(qry, args);
			/*
			 * String mot[][] =runner.multipleSelection(
			 * "select nvl(md.MODE_OF_TRANSPORT,'0'),nvl(mpd.company_id,'0') from marine_Data md, MARINE_POLICY_DETAILS mpd, position_master pos "
			 * +
			 * "where pos.application_no=md.application_no and mpd.quote_no=pos.quote_no and pos.quote_no=?"
			 * ,args);
			 */
			if (getData.length > 0) {
				blNo = getData[0][0];
				/*
				 * blDay = "" + Integer.parseInt(getData[0][1] == null ? "0" :
				 * getData[0][1]); blMonth = "" + Integer.parseInt(getData[0][2]
				 * == null ? "0" : getData[0][2]); blYear = "" +
				 * Integer.parseInt(getData[0][3] == null ? "0" :
				 * getData[0][3]);
				 */
				agentId = getData[0][4] == null ? "others" : getData[0][4];

				/*
				 * if(mot[0][0].equals(mot[0][1])){ carrieerName = getData[0][5]
				 * == null ? "" : getData[0][5]; } else{ carrieerName =
				 * getCarrierName(mot[0][0],quoteId); }
				 */
				carrieerName = getCarrierName(quoteId, branchCode);

				lcNo = getData[0][6] == null ? "0" : getData[0][6];
				bankName = getData[0][7] == null ? "" : getData[0][7];
				/*
				 * lcDay = "" + Integer.parseInt(getData[0][8] == null ? "0" :
				 * getData[0][8]); lcMonth = "" + Integer.parseInt(getData[0][9]
				 * == null ? "0" : getData[0][9]); lcYear = "" +
				 * Integer.parseInt(getData[0][10] == null ? "0" :
				 * getData[0][10]);
				 */
				partialAllowed = getData[0][11] == null ? "" : getData[0][11];
				sdiscount = getData[0][12] == null ? "0" : getData[0][12];
				agentName = getData[0][13] == null ? "" : getData[0][13];
				brokerRemarks = getData[0][14] == null ? "" : getData[0][14];
				brokerRemarks.replaceAll("''", "'");
				/*
				 * sailingDay = "" + Integer.parseInt(getData[0][15] == null ?
				 * "0" : getData[0][15]); sailingMonth = "" +
				 * Integer.parseInt(getData[0][16] == null ? "0" :
				 * getData[0][16]); sailingYear = "" +
				 * Integer.parseInt(getData[0][17] == null ? "0" :
				 * getData[0][17]);
				 */
				blDate = getData[0][18] == null ? "" : getData[0][18];
				lcDate = getData[0][19] == null ? "" : getData[0][19];
				sailingDate = getData[0][20] == null ? "" : getData[0][20];
				packType = getData[0][21] == null ? "" : getData[0][21];
				contractNo = getData[0][22] == null ? "" : getData[0][22];
				fmsCaseNo = getData[0][23] == null ? "" : getData[0][23];
				referenceNo = getData[0][24] == null ? "" : getData[0][24];
				
			}
		} catch (Exception e) {
			System.out.println("  ERROR  in policyinfo.java " + e.toString());
			e.printStackTrace();
			blNo = " ";
			blDay = " ";
			agentId = "";
			carrieerName = " ";
			lcNo = "0";
			lcDay = " ";
			bankName = " ";
			partialAllowed = "";
		}
	}

	public String validateFields(String appno, String ProductId,String adminRef, String qno,String loginId) 
	{
		com.maan.services.util.dataCollection data = new com.maan.services.util.dataCollection();
		String error = "";
		String values = null;
		
		// Invoice Number Validations June 14
		String invoiceNoAll [][] = new String[0][0];
		String invoiceQry = "";
		if(ProductId.equalsIgnoreCase("1"))//it is hidded
		{
			try
			{
				String args[] = new String[2];
				args[0] =  appno;
				args[1] = qno;
				invoiceQry ="select invoice_number,application_no from marine_result_details where application_no in(select application_no from position_master where customer_id=(select customer_id from position_master where application_no=?) and quote_no!=?)";
				invoiceNoAll = runner.multipleSelection(invoiceQry,args);
			}
			catch(Exception e)
			{
				System.out.println("Exception for getting application number based on cutomer id in policy info.java"+e.toString());
				e.printStackTrace();
			}

			for(int i=0;i<invoiceNoAll.length;i++)
			{
				invoiceNoAll[i][0] = invoiceNoAll[i][0] == null ? " " : invoiceNoAll[i][0];
				invoiceNoAll[i][1] = invoiceNoAll[i][1] == null ? " " : invoiceNoAll[i][1];
			}
		}

		if (commodityValidation.size() > 0)
		{
			String invNumber = "";
			HashMap cv = new HashMap();
			for (int i = 0; i < commodityValidation.size(); i++) 
			{
				
				cv = (HashMap) commodityValidation.get("commodity" + (i + 1));
				
				if (!"Y".equalsIgnoreCase(adminRef)) 
				{
					values = data.validString((String) cv.get("desc" + (i + 1)), true);
					if ("needed".equalsIgnoreCase(values))
						error = error + "<br>*" + runner.getErrormsg("65")+ " for goods " + (i + 1);
				}

				
				if(ProductId.equalsIgnoreCase("1"))//it is hidded
				{
					invNumber =(String) cv.get("invoiceno" +(i+1));
					if(!invNumber.equalsIgnoreCase(""))
					{
						for (int j=0;j<invoiceNoAll.length;j++)
						{
							if(invNumber.equalsIgnoreCase(invoiceNoAll[j][0]))
								error = error + "<br>* Invoice Number is already exist "+invNumber;
						}
					}
				}
				
			}
		}
		
		/*if (!ProductId.equalsIgnoreCase("11") && (!"DD".equalsIgnoreCase(lcDay) || !"MMM".equalsIgnoreCase(lcMonth)
				|| !"YYYY".equalsIgnoreCase(lcYear))) {
			values = data.checkDate(lcDay + "/" + lcMonth + "/" + lcYear);
			if ("Invalid".equalsIgnoreCase(values)) {
				error = error + "<br>*" + runner.getErrormsg("40");
			}
			else*/
		   if(!ProductId.equalsIgnoreCase("11") && lcDate!=null && lcDate.length()>0)
			{
				String result = null;
				String args[] = new String[2];
				args[0] =  lcDate;
				args[1] = appno;
				result = runner.singleSelection("select months_between(add_months(inception_date+9/24,-6),to_date(?,'dd/MM/yyyy')) from marine_data where application_no=?",args);
				if (result != null && Double.parseDouble(result) > 0)
				{
					error = error + "<br>*" + runner.getErrormsg("102");
				}
			}
//		}
		
		/*if (!"DD".equalsIgnoreCase(sailingDay) || !"MMM".equalsIgnoreCase(sailingMonth)
				|| !"YYYY".equalsIgnoreCase(sailingYear)) {
			values = data.checkDate(sailingDay + "/" + sailingMonth + "/" + sailingYear);
			if ("Invalid".equalsIgnoreCase(values)) {
					error = error + "<br>*" + runner.getErrormsg("168");
			}*/
		   if(getGHQOACode(loginId) && ProductId.equalsIgnoreCase("11"))
		   {
			   if(sailingDate==null || sailingDate.length()<=0 )
			   {
				   error = error + "<br>*Please select Sailing Date ";
			   }
		   }
		   if(sailingDate!=null && sailingDate.length()>0)
			{
				String result = null;
				String args[] = new String[2];
				args[0] =  sailingDate;
				args[1] = appno;
				result = runner.singleSelection("select months_between(add_months(inception_date+9/24,-6),to_date(?,'dd/MM/yyyy')) from marine_data where application_no=?",args);
				if (result != null && Double.parseDouble(result) > 0)
				{
					error = error + "<br>*" + runner.getErrormsg("169");
				}
			}
//		}

		/*if (!"DD".equalsIgnoreCase(blDay) || !"MMM".equalsIgnoreCase(blMonth)
				|| !"YYYY".equalsIgnoreCase(blYear)) {
			values = data.checkDate(blDay + "/" + blMonth + "/" + blYear);
			if ("Invalid".equalsIgnoreCase(values))
				error = error + "<br>*" + runner.getErrormsg("32");
			else */
		   if(blDate!=null && blDate.length()>0)
			{
				String result = null;
				String args[] = new String[2];
				args[0] =  blDate;
				args[1] = appno;
				result = runner.singleSelection("select months_between(add_months(inception_date+9/24,-6),to_date(?,'dd/MM/yyyy')) from marine_data where application_no=?",args);

				if (result != null && Double.parseDouble(result) > 0)
					error = error + "<br>*" + runner.getErrormsg("101");

			}
//		}
		
	  if(ProductId.equalsIgnoreCase("3"))
	  {
		  if(!lcNo.equalsIgnoreCase(""))
			{
				String result = null;
				int count =0;
				String args[] = new String[2];
				args[0] =  lcNo;
				args[1] =  qno;
				result = runner.singleSelection("select count(lc_number) from marine_policy_details where upper(lc_number)=upper(?) and quote_no!=? ",args);
				count = Integer.parseInt(result);
				if (count > 0)
					error = error + "<br>*" + runner.getErrormsg("463");
			}   
	  }
	 
	   
		if (!"Y".equalsIgnoreCase(adminRef)) {
			if ("0".equalsIgnoreCase(agentId))
				error = error + "<br>*" + runner.getErrormsg("33");

			if (agentId.equalsIgnoreCase("others")) {
				values = data.validString(agentName, false);
				if ("needed".equalsIgnoreCase(values))
					error = error + "<br>*" + runner.getErrormsg("367");
			}
		}
		 if(getGHQOACode(loginId) && ProductId.equalsIgnoreCase("11"))
		   {
			if(contractNo==null || contractNo.length()<=0 )
			   {
				   error = error + "<br>*Please enter Contract No ";
			   }
			if(fmsCaseNo==null || fmsCaseNo.length()<=0 )
			   {
				   error = error + "<br>*Please enter FMS Case No ";
			   }
			if(referenceNo==null || referenceNo.length()<=0 )
			   {
				   error = error + "<br>*Please enter Reference No ";
			   }
		   }
		String chkArgs[] = new String[1];
		chkArgs[0] = appno;
		String commodityCheck = "";
		commodityCheck = runner.singleSelection("select count(*) from marine_result_details where sale_term_charges is null and war_charges is null and premium is null and currency_id is null and currency_name is null and SUMINSUREDLOCAL is null and APPLICATION_NO=?",chkArgs);
		commodityCheck = commodityCheck==null?"1":commodityCheck;
		if(commodityCheck.equals("0")){}
		else
		{
			error = error + "<br>* Please edit the Quote once again properly in Edit mode";
		}
		/*String premiumCheck = "";
		premiumCheck = runner.singleSelection("select count(*) from marine_result_details mrd,marine_result mr where mr.APPLICATION_NO=mrd.APPLICATION_NO and round((mrd.premium+mrd.WAR_CHARGES))>round(mr.premium) " +
		"and mr.APPLICATION_NO=?",chkArgs);
		if(premiumCheck.equals("0")){}
		else
		{
			error = error + "<br>* Please edit the Quote once again properly in Edit mode";
		}*/
		return error;
	}


	public String storeCommodity(String quoteId, String id,	String supplier, String desc, String no) 
	{
		String result = "";
		try
		{
			StringBuffer sb = new StringBuffer(desc);
			desc = sb.toString().replaceAll("\n", " ");

			StringBuffer sb1 = new StringBuffer(supplier);	//Mississippi - Senthil
			supplier = sb1.toString().replaceAll("\n"," ");
			
			StringBuffer sb2 = new StringBuffer(no);	//Mississippi - Senthil
			no = sb2.toString().replaceAll("\n"," ");

			String args[] = new String[5];
			args[0] = supplier;
			args[1] = desc;
			args[2] = no;
			args[3] = quoteId;
			args[4] = id;
			String qry = "update marine_result_details set supplier_name=?,package_description=?,invoice_number=? where application_no=? and commodity_id=?";
			result = runner.multipleUpdation(qry,args);
			
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return result;
	}

	// Rajesh Modified for throws error if not operation suceess
	public String storeValues(String quoteId, String customerId,String adminLevel) 
	{
		String flag = "false";
		String result = "";

		StringBuffer sb1 = new StringBuffer(carrieerName); //Mississippi - Senthil
		carrieerName = sb1.toString().replaceAll("\n"," ");
		System.out.println("Store Values::");
		try 
		{
			String argsMot[] = {quoteId};
			String mot = runner.singleSelection("select MODE_OF_TRANSPORT from marine_Data where application_no=(select application_no from position_master where quote_no=?)",argsMot);
			String qry = "select count(*) from marine_policy_details where quote_no=?";
			try
			{
				String args[] = new String[1];
				args[0] = quoteId;
				qry = runner.singleSelection(qry,args);
			}
			catch (Exception e) 
			{
				System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
			}
			if ("0".equalsIgnoreCase(qry)|| "DIDN'T SELECTED".equalsIgnoreCase(qry)) 
			{
				try
				{
					String args[] = new String[18];
					args[0] = quoteId;
					args[1] = customerId;
					args[2] = blNo;
					args[3] = blDate;
					args[4] = agentId.equalsIgnoreCase("others")?"0" : agentId;
					args[5] = (carrieerName);
					args[6] = (lcNo.equals("") ? "0" : (lcNo));
					args[7] = lcDate;
					args[8] = (bankName);
					args[9] = partialAllowed;
					args[10] = (sdiscount.equals("") ? "0" : sdiscount);
					args[11] = (mot);
					args[12] = (agentName);
					args[13] = sailingDate;
					args[14] = packType;
					args[15] = contractNo;
					args[16] = fmsCaseNo;
					args[17] = referenceNo;
					qry = "insert into marine_policy_details(quote_no,customer_id,bl_awb_no,bl_awb_date,settling_agent_id,carrier_name,lc_number,lc_date,bank_name,partial_shipment_allowed,special_discount,company_id,amend_id,entry_date,inception_date,SETTLING_AGENT_NAME,status,sailing_date,package_code,ghq_contract_no,ghq_fms_case_no,ghq_reference_no)values(?,?,?,?,?,?,?,?,?,?,?,?,'0',sysdate,sysdate,?,'Y',?,?,?,?,?)";
					System.out.println("Test Details::"+qry);
					result = runner.multipleInsertion(qry,args);
				}
				catch (Exception e) 
				{
					System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
				}
			
			}
			else
			{
			
				try
				{
					String args[] = new String[18];
					args[0] = blNo;
					args[1] = blDate;
					args[2] = agentId.equalsIgnoreCase("others") ? "0" : agentId;
					args[3] = (carrieerName);
					args[4] = (lcNo.equals("") ? "0" : (lcNo));
					args[5] = lcDate;
					args[6] = (bankName);
					args[7] = partialAllowed;
					args[8] = (sdiscount.equals("") ? "0" : sdiscount);
					args[9] = (agentName);
					args[10] = customerId;
					args[11] = mot;
					args[12] = sailingDate;
					args[13] = packType;
					args[14] = contractNo;
					args[15] = fmsCaseNo;
					args[16] = referenceNo;
					args[17] = quoteId;
					qry = "update marine_policy_details set bl_awb_no=?,bl_awb_date=?,settling_agent_id=?,carrier_name=?,lc_number=?,lc_date=?,bank_name=?,partial_shipment_allowed=?,special_discount=?,amend_id='0',SETTLING_AGENT_NAME=?,customer_id=?,company_id=?,sailing_date=?,package_code=?,ghq_contract_no=?,ghq_fms_case_no=?,ghq_reference_no=? where quote_no=?";
					result = runner.multipleUpdation(qry,args);
				}
				catch (Exception e) 
				{
					System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
				}

				
			}
			try 
			{
				if (adminLevel != null) 
				{
									
					StringBuffer sb3 = new StringBuffer(brokerRemarks);	//Mississippi - Senthil
					brokerRemarks = sb3.toString().replaceAll("\n"," ");

					String newValue = null;
					String args[] = new String[1];
					args[0] = quoteId; 
					newValue = runner.singleSelection("select remarks from position_master where quote_no=?",args);

					if ("Admin".equalsIgnoreCase(newValue)&& "Y".equalsIgnoreCase(adminLevel)) 
					{
						args = new String[1];
						args[0] = quoteId; 
						runner.multipleUpdation("update marine_data set referal_status='Both' where application_no=(select application_no from position_master where quote_no=?)",args);
						
						runner.multipleUpdation("update position_master set remarks = 'Normal' where quote_no =?",args);
						
						args = new String[2];
						args[0] = (brokerRemarks); 
						args[1] = quoteId; 
						runner.multipleUpdation("update marine_data set admin_referral_status='Y',remarks=? where application_no=(select application_no from position_master where quote_no=?)",args);
						flag = "true";
						// For Freight Forwarder Rajesh
						args = new String[1];
						args[0] = quoteId; 
						runner.multipleUpdation("update freight_position_master set STATUS = 'T' where quote_no = ?",args);
						
					} 
					else if ("Normal".equalsIgnoreCase(newValue) && "Y".equalsIgnoreCase(adminLevel)) 
					{
						args = new String[1];
						args[0] = quoteId; 
						runner.multipleUpdation("update position_master set remarks ='Normal' where quote_no=?",args);
						
						runner.multipleUpdation("update marine_data set admin_referral_status='Y' where application_no=(select application_no from position_master where quote_no=?)",args);
						flag = "true";
					
						// For Freight Forwarder Rajesh
						runner.multipleUpdation("update freight_position_master set STATUS='T' where quote_no =?",args);
					} 
					else if ("Y".equalsIgnoreCase(newValue)) 
					{
						args = new String[1];
						args[0] = quoteId; 
						runner.multipleUpdation("update position_master set remarks = 'Normal' where quote_no =?",args);
					}

				}
			} 
			catch (Exception e) 
			{
				System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
			}
			
			if (!"No".equalsIgnoreCase(brokerRemarks))
			{
				String args[] = new String[2];
				args[0] = (brokerRemarks); 
				args[1] = quoteId; 
				runner.multipleUpdation("update position_master set broker_remarks=? where quote_no=?",args);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		if (result.equalsIgnoreCase("DIDN'T UPDATE")|| result.equalsIgnoreCase("DIDN'T INSERTED") || result == null
				|| result.length() <= 0)
			flag = "DBEroor";

		return flag;
	}

	public String[][] getPolicy(String app_no)
	{
		String[][] returnVal = new String[0][0];
		try 
		{
			app_no = app_no==null?"0":app_no;
			String qry = "select application_no,premium,to_char(entry_date,'dd') as dates,to_char(entry_date,'MM') as months,to_char(entry_date,'YYYY') as years," +
					"excess_premium,total_sum_insured,to_char(inception_date,'dd/MM/YYYY'),premium-nvl(TOTAL_WAR_CHARGES,'0')-(nvl(GOVT_TAX,'0')+nvl(EMERGENCY_FUND,'0')+nvl(POLICY_FEE,'0')) as BasicPremium,premium-(nvl(GOVT_TAX,'0')+nvl(EMERGENCY_FUND,'0')+nvl(POLICY_FEE,'0')),nvl(DEC_EXCESS_PREMIUM,0) from marine_data where application_no=?";
			String args[] = new String[1];
			args[0] = app_no;
			returnVal = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}

	public String[][] getQuotesForCertificate(String openCoverNo,String login,String rsaissuer) 
	{
		String[][] returnVal = new String[0][0];
		try 
		{ 
			if(rsaissuer==null)
			{
				String args[] = new String[4];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = login;
				args[3] = login;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.quote_no not in(select quote_no from webservice_marine_quote where quote_no is not null and transaction_id is not null) and  pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.APPLICATION_ID='1' and pm.DISCOUNT_PREMIUM is null and pm.login_id in(select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}
			else
			{
				String args[] = new String[3];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = rsaissuer;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.quote_no not in(select quote_no from webservice_marine_quote where quote_no is not null and transaction_id is not null) and pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.login_id=? and pm.APPLICATION_ID=? and pm.DISCOUNT_PREMIUM is null and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}

		} catch (Exception e) {System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String[][] getQuotesForTransaction(String openCoverNo,String login,String rsaissuer,String tranId) 
	{
		String[][] returnVal = new String[0][0];
		try 
		{ 
			if(rsaissuer==null)
			{
				String args[] = new String[5];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = login;
				args[3] = login;
				args[4] = tranId;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.APPLICATION_ID='1' and pm.DISCOUNT_PREMIUM is null and pm.login_id in(select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) and pm.quote_no in(select quote_no from webservice_marine_quote where transaction_id=?) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}
			else
			{
				String args[] = new String[4];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = rsaissuer;
				args[3] = tranId;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.login_id=? and pm.APPLICATION_ID=? and pm.DISCOUNT_PREMIUM is null and pm.quote_no in(select quote_no from webservice_marine_quote where transaction_id=?)and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}

		} catch (Exception e) {System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String[][] getCustomerQuotesForCertificate(String openCoverNo,String cusLogin) 
	{
		String[][] returnVal = new String[0][0];
		try 
		{

			//String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and (pm.customer_id=(select customer_id from personal_info where customer_login_id=?) or pm.login_id=?) and pm.DISCOUNT_PREMIUM is null and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) order by pm.quote_no";
			
			String  condition="((pm.customer_id in (select distinct customer_id from login_user_details  where login_id=?) or pm.login_id=?) and (pm.login_id=? or pm.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id=?) and usertype not in('Customer'))))";

			String qry = "select pm.quote_no,pi.first_name,pm.login_id from position_master pm,personal_info pi where pm.quote_no not in(select quote_no from webservice_marine_quote where quote_no is not null and transaction_id is not null) and  pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and "+condition+" and pm.DISCOUNT_PREMIUM is null and (pm.CERTIFICATE_NO is null and pm.CERTIFICATE_DATE is null) order by pm.quote_no";
			String args[] = new String[5];
			args[0] = openCoverNo;
			args[1] = cusLogin;
			args[2] = cusLogin;
			args[3] = cusLogin;
			args[4] = cusLogin;
			returnVal = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String setcutomerName(String quote) 
	{
		String names = "";
		try 
		{

			String qry = "select nvl(first_name,company_name),last_name from personal_info where customer_id=(select customer_id from position_master where quote_no=?)";
			String args[] = new String[1];
			args[0] = quote;
			String[][] returnVal = runner.multipleSelection(qry,args);
			if(returnVal.length>0)
				names = returnVal[0][0] == null ? "" : returnVal[0][0] + " "+ returnVal[0][1] == null ? "" : returnVal[0][0];

		} catch (Exception e) {System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return names;
	}

	public java.util.HashMap getResults(String quoteId,String customerId, String app_no, String companyId, String login_id)  // Insurance Start Date changes ClausesEdit.jsp
	{
		java.util.HashMap results = null;
		try 
		{
			results = new java.util.HashMap();
			customerId = customerId==null?"0":customerId;
			app_no = app_no==null?"0":app_no;
			String qry = "select to_char(b.INCEPTION_DATE,'dd') as days,to_char(b.INCEPTION_DATE,'MM') as months,to_char(b.INCEPTION_DATE,'YYYY') as years,nvl(b.total_sum_insured,0),nvl(b.premium,0),nvl(a.special_discount,0),nvl(b.excess_premium,0) from marine_policy_details a,marine_data b,position_master pm where a.customer_id=? and a.quote_no=? and  b.application_no=? and pm.quote_no=a.quote_no and  pm.application_no=b.application_no";
			System.out.println("test qry::"+qry);
			String args[] = new String[3];
			args[0] = customerId;
			args[1] = quoteId;
			args[2] = app_no;

			String[][] returnVal = runner.multipleSelection(qry,args);

			String ID = "";
			String SI = "0";
			String pre = "0";
			String EP = "0";
			String SD = "0";
			if(returnVal.length>0)
			{
				ID = returnVal[0][0] + "/" 	+ returnVal[0][1] + "/" + returnVal[0][2];
				SI = returnVal[0][3]!=null?returnVal[0][3]:"0";
				pre = returnVal[0][4]!=null?returnVal[0][4]:"0";
				EP = returnVal[0][6]!=null?returnVal[0][6]:"0";
				SD = returnVal[0][5]!=null?returnVal[0][5]:"0";
			}
			results.put("insuranceDate", ID);
			results.put("suminsured", SI);
			results.put("premium", pre);
			results.put("excessPremium", EP);
			String GHQcount = runner.singleSelection("select count(*) from constant_detail where detail_name in (select oa_code from login_master where login_id = '"+login_id+"')");
			System.out.println("GHQ USER?::"+"select count(*) from constatnt_detail where detail_name in (select oa_code from login_master where login_id = '"+login_id+"')");;
			if (GHQcount.equalsIgnoreCase("0") && Double.parseDouble((String) results.get("premium") == null ? "0":(String) results.get("premium")) >0) 
			{
				results.put("insurance_fee", "" + 0);
				String discount = "" + Double.parseDouble(pre) * Double.parseDouble(SD) / 100;
				results.put("discount", discount);
				results.put("netpremium", ""+(Double.parseDouble(pre) - Double.parseDouble(discount)));
				storeMarineResults(companyId, customerId, app_no, quoteId,results);
			}
			else if (!(GHQcount.equalsIgnoreCase("0")) && Double.parseDouble((String) results.get("premium") == null ? "0":(String) results.get("premium")) >=0) 
			{
				results.put("insurance_fee", "" + 0);
				String discount = "" + Double.parseDouble(pre) * Double.parseDouble(SD) / 100;
				results.put("discount", discount);
				results.put("netpremium", ""+(Double.parseDouble(pre) - Double.parseDouble(discount)));
				storeMarineResults(companyId, customerId, app_no, quoteId,results);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return results;
	}

	public void storeMarineResults(String companyId,String customerId, String app_no, String quoteId,java.util.HashMap results) 
	{
		try
		{
			String args1[] = new String[2];
			args1[0] = quoteId;
			//args1[1] = customerId;
			args1[1] = app_no;
			
			String sql = "select count(*) from marine_result where quote_no=? and application_no=?";
			sql = runner.singleSelection(sql,args1);
			if ("0".equalsIgnoreCase(sql)|| "DIDN'T SELECTED".equalsIgnoreCase(sql)) 
			{
				String args[] = new String[10];
				args[0] = app_no;
				args[1] = quoteId;
				args[2] = companyId==null?"1":companyId;
				args[3] = customerId;
				args[4] = (String) results.get("suminsured");
				
				args[5] = (String) results.get("premium");
				args[6] = (String) results.get("insurance_fee");
				args[7] = (String) results.get("netpremium");
				args[9] = (String) results.get("netpremium");
				// Rounding the Premium
				/*args[5] = Double.toString(Math.round(Double.parseDouble((String) results.get("premium"))));
				args[6] = Double.toString(Math.round(Double.parseDouble((String) results.get("insurance_fee"))));
				args[7] = Double.toString(Math.round(Double.parseDouble((String) results.get("netpremium"))));
				args[9] = Double.toString(Math.round(Double.parseDouble((String) results.get("netpremium"))));*/
				
				args[8] = (String) results.get("discount");
				sql = "insert into marine_result(application_no,quote_no,company_id,amend_id,customer_id,total_sum_insured,premium,insurance_fee,net_premium,special_discount,amount_payable,entry_date) values(?,?,?,'0',?,?,?,?,?,?,?,(select sysdate from dual))";
				System.out.println("sql marine result::"+sql);
				runner.multipleInsertion(sql,args);
			}
			else 
			{
				String args[] = new String[9];
				
				args[0] = (String) results.get("suminsured");
				
				/*args[1] = (String) results.get("premium");
				args[2] = (String) results.get("insurance_fee");
				args[3] = (String) results.get("netpremium");
				args[5] = (String) results.get("netpremium");*/
				// Rounding the Premium 
				args[1] = Double.toString(Math.round(Double.parseDouble((String) results.get("premium"))));
				args[2] = Double.toString(Math.round(Double.parseDouble((String) results.get("insurance_fee"))));
				args[3] = Double.toString(Math.round(Double.parseDouble((String) results.get("netpremium"))));
				args[5] = Double.toString(Math.round(Double.parseDouble((String) results.get("netpremium"))));
				
				args[4] = (String) results.get("discount");
				args[6] = customerId;
				args[7] = quoteId;
				args[8] = app_no;
				sql = "update marine_result set total_sum_insured=?,premium=?,insurance_fee=?,net_premium=?,special_discount=?,amount_payable=? where customer_id=? and quote_no=? and application_no=?";
				runner.multipleUpdation(sql,args);
		  }
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
	}
	public String draftStatus(String quoteId, String codes,	String preOptions, String bankOptions, String generateOptions,
			String bankAssuredOptions, String currencyOptions,String productTypeId, String openCoverNo) 
	{

		String current_no = null;
		String referal = null;
		String policyNo = null;
		try 
		{
			String args1[] = new String[1];
			args1[0] = quoteId;
			String sql = "select count(*) from position_master where quote_no=? and remarks in('Refferal')";
			referal = runner.singleSelection(sql,args1);
			if (Integer.parseInt(referal) > 0) 
			{
				policyNo = "referal";
				return policyNo;
			}
			sql = "select status,policy_no from position_master where quote_no=?";
			String[][] fromPosition = runner.multipleSelection(sql,args1);
			fromPosition[0][0] = fromPosition[0][0] == null ? "Y": fromPosition[0][0];
			if (fromPosition[0][0].equalsIgnoreCase("Y")) 
			{
				String args[] = new String[1];
				args[0] = quoteId;
				sql = "update position_master set OPEN_COVER_INT_STATUS='"+ "DRAFTED" + "' where  quote_no=?";
				runner.multipleUpdation(sql,args);
			} 
			else{
				current_no = fromPosition[0][1];
			}
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return current_no;

	}
	public void UpdatePDFStatusofPremiumBanker(String quoteNo,String loginCode, String preOptions, String bankOptions,
			String generateOptions, String bankAssuredOptions,String currencyOptions, String noteType, String paymentMode, String basisVal, String certClausesYN, String excessStatus) 
	{
		String sql = "";
		try
		{

			String args[] = new String[9];
			args[0] = preOptions;
			args[1] = bankOptions;
			args[2] = generateOptions;
			args[3] = bankAssuredOptions;
			args[4] = currencyOptions;
			args[5] = basisVal;
			args[6] = certClausesYN;
			args[7] = excessStatus;
			args[8] = quoteNo;
			sql = "update position_master set pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,BASIS_VAL=?,CERT_CLAUSES_YN=?,PDF_EXCESS_STATUS=? where  quote_no=? and status='Y'";
			runner.multipleUpdation(sql,args);
			sql = "";

		} catch (Exception updateEX) {

			System.out.println("the Exception is UpdatePDFStatusofPremiumBanker "+ updateEX.toString());

		}
	}
	public void UpdatePDFStatusofPremiumBankerMultiple(String quoteNo,String loginCode, String preOptions, String bankOptions,
			String generateOptions, String bankAssuredOptions,String currencyOptions,String stamp,String commissionOption) 
	{
		String pdfStatus = "";
		String sql = "";

		try 
		{
			String args[] = new String[7];
			args[0] = preOptions;
			args[1] = bankOptions;
			args[2] = generateOptions;
			args[3] = bankAssuredOptions;
			args[4] = currencyOptions;
			args[5] = stamp;
			args[6] = commissionOption;
			
			
			sql = "update position_master set pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,PDF_Stamp_Status=? ,PDF_COMMISSION_STATUS=? where  quote_no in("+quoteNo+") and status='Y'";
			pdfStatus = runner.multipleUpdation(sql,args);

			sql = "";

		}
		catch (Exception updateEX) 
		{

			System.out.println("the Exception is UpdatePDFStatusofPremiumBanker "+ updateEX.toString());
		}
	}
	public String[][] getPDFStatusofPremiumBanker(String quoteNo,String loginCode) 
	{
		String[][] pdfStatus = new String[0][0];
		String sql = "";

		try 
		{
			sql = "select pdf_pre_show_status,pdf_banker_status,pdf_generate_status,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS,PDF_STAMP_STATUS,PDF_COMMISSION_STATUS,NOTE_TYPE,PAYMENT_MODE,BASIS_VAL,CERT_CLAUSES_YN from position_master where quote_no=? and status='Y'";
			String args[] = new String[1];
			args[0] = quoteNo;
			pdfStatus = runner.multipleSelection(sql,args);
			sql = "";
		}
		catch (Exception updateEX) 
		{
			System.out.println("the Exception is getPDFStatusofPremiumBanker "+ updateEX.toString());
			updateEX.printStackTrace();
		}
		return pdfStatus;
	}
	public String[][] getPDFStatusofPremiumBankernew(String quoteNo,String loginCode, String brokerBra) 
	{
		String[][] pdfStatus = new String[0][0];
		String sql = "";

		try 
		{
			sql = "select pdf_pre_show_status,pdf_banker_status,pdf_generate_status,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS,PDF_STAMP_STATUS,PDF_COMMISSION_STATUS,NOTE_TYPE,PAYMENT_MODE,BASIS_VAL,CERT_CLAUSES_YN, decode(cd.remarks,null,'N','Y') direct_status,PDF_EXCESS_STATUS FROM position_master pm left outer join constant_detail cd on CD.REMARKS=PM.LOGIN_ID and CD.CATEGORY_ID=129 and cd.branch_code=? and cd.status='Y' WHERE pm.quote_no= ? AND pm.status = 'Y'";
			String args[] = new String[2];
			args[0] = brokerBra;
			args[1] = quoteNo;
			pdfStatus = runner.multipleSelection(sql,args);
			sql = "";
		}
		catch (Exception updateEX) 
		{
			System.out.println("the Exception is getPDFStatusofPremiumBanker "+ updateEX.toString());
			updateEX.printStackTrace();
		}
		return pdfStatus;
	}
	public String getBank(String appNo) 
	{
		String bankName = "";
		String sql = "";

		try 
		{
			sql = "select open_bank_id from marine_data where amend_id = (select max(amend_id) from marine_data where application_no =?) and application_no =?";
			
			String args[] = new String[2];
			args[0] = appNo;
			args[1] = appNo;
			bankName = runner.singleSelection(sql, args);
			sql = "";
		}
		catch (Exception updateEX) 
		{
			System.out.println("the Exception is getPDFStatusofPremiumBanker "+ updateEX.toString());
			updateEX.printStackTrace();
		}
		return bankName;
	}
	
	public String[][] getDeclarations(String loginIds, String productId,String multiPolicyNo,String type) 
	{

		java.util.HashMap getsTotal = null;
		String[][] ss = null;
		try 
		{
			if(!type.equalsIgnoreCase("Customer"))
			{
				String[][] valuess = new String[0][0];
				String loginAllIds = "";
				getsTotal = new java.util.HashMap();
				String args[] = new String[3];
				args[0] = loginIds;
				args[1] = loginIds;
				args[2] = loginIds;
				String sql = "select login_id from login_master where oa_code=? or created_by=? or login_id=?";
				valuess = runner.multipleSelection(sql,args);

				for (int i = 0; i < valuess.length; i++)
				{
					loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
				}
				loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));

				getsTotal.put("total", "" + valuess.length);
				getsTotal.put("getCus", valuess);

				ss = new String[0][0];
		
				String args1[] = new String[3];
				args1[0] = multiPolicyNo;
				args1[1] = multiPolicyNo;
				args1[2] = productId;

				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in (select login_id from position_master where policy_no=?) and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') and a.policy_no=? and b.customer_id=a.customer_id and a.product_id=? order by a.quote_no";

				ss = runner.multipleSelection(sql,args1);
			}
			else if(type.equalsIgnoreCase("Customer"))
			{
				String args1[] = new String[6];
				args1[0] = loginIds;
				args1[1] = loginIds;
				args1[2] = loginIds;
				args1[3] = loginIds;
				args1[4] = multiPolicyNo;
				args1[5] = productId;
				ss = new String[0][0];
				
				String  condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id=?) or a.login_id=?) and (a.login_id=? or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id=?) and usertype not in('Customer'))))";

				String sql="";
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') and a.policy_no=? and b.customer_id=a.customer_id and a.product_id=? order by a.quote_no";
				ss = runner.multipleSelection(sql,args1);
			}

		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());
			e.printStackTrace();
		}
		return ss;
	}
	
	public HashMap getTotalPolicy123(String loginIds, String searchOption,	String searchValue, String productId, String displayStatus,String openCoverNo,String rsaissuer)  // July 04 PortFolio Sorting openCoverNo restriction
	{

		java.util.HashMap getsTotal = null;
		java.util.HashMap getFullDetails = new java.util.HashMap();
		String[][] ss = null;
		String[][] ssMultiple = null;
		String sqlMultiple = "";
		
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			//syntax = " and a.open_cover_no='"+openCoverNo+"' ";
			syntax = " and a.open_cover_no like '%"+policyInfo.getOriginalOpenCoverPolicyNo(openCoverNo)+"%'";
		else
			syntax = " ";
		String  sql = "";
		try 
		{
			if(rsaissuer==null)
			{
				String[][] valuess = new String[0][0];

				String loginAllIds = "";
				getsTotal = new java.util.HashMap();
				String args1[] = new String[3];
				args1[0] = loginIds;
				args1[1] = loginIds;
				args1[2] = loginIds;
				sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
				valuess = runner.multipleSelection(sql,args1);

				for (int i = 0; i < valuess.length; i++)
				{
					loginAllIds = "'" + valuess[i][0] + "'," + loginAllIds;
				}
				loginAllIds = loginAllIds.substring(0, loginAllIds.lastIndexOf(','));
				getsTotal.put("total", "" + valuess.length);
				getsTotal.put("getCus", valuess);

				
				ss = new String[0][0];
				if ("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption)) 
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ("+ loginAllIds+ ") "+syntax+" and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and a.product_id='"+ productId + "' order by substr(a.policy_no,9,16) desc";


					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ("+ loginAllIds+ ") "+syntax+" and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') and a.product_id='"+ productId+ "' group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql);
					ssMultiple = runner.multipleSelection(sqlMultiple);
				}
				else if ("FIRST_NAME".equalsIgnoreCase(searchOption))
				{

					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ("+ loginAllIds+ ") and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=?  order by substr(a.policy_no,9,16) desc";
					String args[] = new String[3];
					args[0] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
					args[1] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
					args[2] = productId;
					
					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a,personal_info b where a.login_id in ("+loginAllIds+") and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) in ('LINKED') and (lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc ";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("quote_nos".equalsIgnoreCase(searchOption))
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ("+loginAllIds+") and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.quote_no like ?) and a.product_id=? order by substr(a.policy_no,9,16) desc";
					String args[] = new String[2];
					args[0] = "%"+(searchValue.trim())+"%";
					args[1] = productId;

					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ("+loginAllIds+") and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.quote_no like ?) and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("policy_nos".equalsIgnoreCase(searchOption))
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ("+loginAllIds+") and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.policy_no like ?) and a.product_id=? order by substr(a.policy_no,9,16) desc";
					String args[] = new String[2];
					args[0] = "%"+(searchValue.trim())+"%";
					args[1] = productId;
					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ("+loginAllIds+") and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.policy_no like ?) and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("DateWise".equalsIgnoreCase(searchOption))
				{

					String searchValue1 = null;
					String searchValue2 = null;
					String searchValue3 = null;

					searchValue1 = searchValue.substring(0, searchValue.indexOf("/"));
					searchValue2 = searchValue.substring(searchValue.indexOf("/") + 1, searchValue.lastIndexOf("/"));
					searchValue3 = searchValue.substring(searchValue.lastIndexOf("/") + 1, searchValue.length());
					try 
					{
						if (Integer.parseInt(searchValue2) < 10)
							searchValue = searchValue1 + "/" + "0"+Integer.parseInt(searchValue2) + "/"+searchValue3;
						else
							searchValue = searchValue1 + "/"+Integer.parseInt(searchValue2) + "/"+searchValue3;

					} catch (Exception e) {
						System.out.println("EXCEPTION IN VALIDATE MONTH   "	+ e.toString());
					}
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ("+loginAllIds+ ") and a.status='P' and b.customer_id=a.customer_id "+syntax+" and nvl(a.open_cover_int_status,0) not in ('LINKED') and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? order by substr(a.policy_no,9,16) desc";
					String args[] = new String[2];
					args[0] = (searchValue.trim());
					args[1] = productId;
					sqlMultiple = "select(a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ("+ loginAllIds+ ") and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') "+syntax+" and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
			}
			else
			{
				ss = new String[0][0];
				if ("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption)) 
				{
					//sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') "+syntax+" and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and a.product_id=? and a.APPLICATION_ID=? order by substr(a.policy_no,9,16) desc";
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') "+syntax+" and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and a.product_id=? and a.APPLICATION_ID=? order by TO_NUMBER (SUBSTR (A.POLICY_NO, 6, 6)) DESC,TO_NUMBER (NVL (REGEXP_SUBSTR (A.POLICY_NO,'[^-]+',1,2), 0)) DESC,TO_NUMBER (NVL (REGEXP_SUBSTR (A.POLICY_NO,'[^-]+',1,3), 0)) DESC";
					String args[] = new String[2];
					args[0] = productId;
					args[1] = rsaissuer;

					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ('"+loginIds+"') "+syntax+" and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') and a.product_id=? and a.APPLICATION_ID=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("FIRST_NAME".equalsIgnoreCase(searchOption))
				{

					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? and a.APPLICATION_ID=? order by substr(a.policy_no,9,16) desc";
					String args[] = new String[4];
					args[0] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
					args[1] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
					args[2] = productId;
					args[3] = rsaissuer;
					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) in ('LINKED') and (lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? and a.APPLICATION_ID=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc ";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("quote_nos".equalsIgnoreCase(searchOption))
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.quote_no like ?) and a.product_id=? and a.APPLICATION_ID=? order by substr(a.policy_no,9,16) desc";

					String args[] = new String[3];
					args[0] = "%"+(searchValue.trim())+"%";
					args[1] = productId;
					args[2] = rsaissuer;

					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.quote_no like ?) and a.product_id=? and a.APPLICATION_ID=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("policy_nos".equalsIgnoreCase(searchOption))
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.policy_no like ?) and a.product_id=? and a.APPLICATION_ID=? order by substr(a.policy_no,9,16) desc";
					
					String args[] = new String[3];
					args[0] = "%"+(searchValue.trim())+"%";
					args[1] = productId;
					args[2] = rsaissuer;

					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.policy_no like ?) and a.product_id=? and a.APPLICATION_ID=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
				else if ("DateWise".equalsIgnoreCase(searchOption))
				{

					String searchValue1 = null;
					String searchValue2 = null;
					String searchValue3 = null;

					searchValue1 = searchValue.substring(0, searchValue.indexOf("/"));
					searchValue2 = searchValue.substring(searchValue.indexOf("/") + 1, searchValue.lastIndexOf("/"));
					searchValue3 = searchValue.substring(searchValue.lastIndexOf("/") + 1, searchValue.length());
					try 
					{
						if (Integer.parseInt(searchValue2) < 10)
							searchValue = searchValue1 + "/" + "0"+Integer.parseInt(searchValue2) + "/"+searchValue3;
						else
							searchValue = searchValue1 + "/"+Integer.parseInt(searchValue2) + "/"+searchValue3;

					} catch (Exception e) {
						System.out.println("EXCEPTION IN VALIDATE MONTH   "	+ e.toString());
					}
					
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' and b.customer_id=a.customer_id "+syntax+" and nvl(a.open_cover_int_status,0) not in ('LINKED') and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? and a.APPLICATION_ID=? order by substr(a.policy_no,9,16) desc";
					
					String args[] = new String[3];
					args[0] = (searchValue.trim());
					args[1] = productId;
					args[2] = rsaissuer;

					sqlMultiple = "select(a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ('"+loginIds+"') and a.status='P' and nvl(a.open_cover_int_status,0) in ('LINKED') "+syntax+" and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? and a.APPLICATION_ID=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}else if ("OtherUsers".equalsIgnoreCase(searchOption))
				{
					sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.quote_no like ?) and a.product_id=? and a.APPLICATION_ID not in ('"+rsaissuer+"','1') order by substr(a.policy_no,9,16) desc";

					String args[] = new String[2];
					args[0] = "%"+(searchValue.trim())+"%";
					args[1] = productId;

					sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in ('"+loginIds+"') and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.quote_no like ?) and a.product_id=? and a.APPLICATION_ID not in ('"+rsaissuer+"','1') group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
					ss = runner.multipleSelection(sql,args);
					ssMultiple = runner.multipleSelection(sqlMultiple,args);
				}
			}

			getFullDetails.put("singlePolicys", ss);
			getFullDetails.put("multiplePolicys", ssMultiple);
			
		} catch (Exception e) {
		}
		return getFullDetails;
	}
	//for direct Client - July 15th
	public HashMap getTotalPolicy123(String loginIds, String searchOption,	String searchValue, String productId, String displayStatus,String openCoverNo) //July 04 PortFolio Sorting restriction Based on opencoverNo
	{
		java.util.HashMap getFullDetails = new java.util.HashMap();
		String[][] ss = null;
		String[][] ssMultiple = null;
		String sqlMultiple = "";
		
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			//syntax = " and a.open_cover_no='"+openCoverNo+"' ";
			syntax = " and a.open_cover_no like '%"+policyInfo.getOriginalOpenCoverPolicyNo(openCoverNo)+"%'";
		else
			syntax = " ";

		try 
		{
			String sql = "";
			//String  condition="(a.customer_id in (select customer_id from personal_info where customer_login_id='"+loginIds+"') or a.login_id='"+loginIds+"')";
			String  condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";
	
			ss = new String[0][0];

			if ("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption)) 
			{
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and a.product_id='"+ productId + "' order by substr(a.policy_no,9,16) desc";

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where "+condition+" and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and a.product_id='"+productId+"' group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
				ss = runner.multipleSelection(sql);
				ssMultiple = runner.multipleSelection(sqlMultiple);
			}
			else if ("FIRST_NAME".equalsIgnoreCase(searchOption))
			{

				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) and a.product_id=? order by substr(a.policy_no,9,16) desc";

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a,personal_info b where "+condition+" and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) in ('LINKED') and(lower(trim(b.company_name)) like ? or lower(trim(b.first_name)) like ?) "+syntax+" and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
				String args[] = new String[3];
				args[0] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
				args[1] = "%"+ (searchValue.trim()).toLowerCase()+ "%";
				args[2] = productId;
				ss = runner.multipleSelection(sql,args);
				ssMultiple = runner.multipleSelection(sqlMultiple,args);

			}
			else if ("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.quote_no like ?) and a.product_id=? order by substr(a.policy_no,9,16) desc";

				String args[] = new String[3];
				args[0] = "%"+ (searchValue.trim())+ "%";
				args[1] = productId;

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where "+condition+" and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and (a.quote_no like ?) and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
				ss = runner.multipleSelection(sql,args);
				ssMultiple = runner.multipleSelection(sqlMultiple,args);
			}
			else if ("policy_nos".equalsIgnoreCase(searchOption))
			{
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and (a.policy_no like ?) and a.product_id=? order by substr(a.policy_no,9,16) desc";
				
				String args[] = new String[3];
				args[0] = "%"+ (searchValue.trim())+ "%";
				args[1] = productId;

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where "+condition+" and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED')  and (a.policy_no like ?) and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
				ss = runner.multipleSelection(sql,args);
				ssMultiple = runner.multipleSelection(sqlMultiple,args);
			}
			else if ("DateWise".equalsIgnoreCase(searchOption))
			{

				String searchValue1 = null;
				String searchValue2 = null;
				String searchValue3 = null;

				searchValue1 = searchValue.substring(0, searchValue.indexOf("/"));
				searchValue2 = searchValue.substring(searchValue.indexOf("/") + 1, searchValue.lastIndexOf("/"));
				searchValue3 = searchValue.substring(searchValue.lastIndexOf("/") + 1, searchValue.length());
				try 
				{
					if (Integer.parseInt(searchValue2) < 10)
						searchValue = searchValue1 + "/" + "0"+ Integer.parseInt(searchValue2) + "/"+ searchValue3;
					else
						searchValue = searchValue1 + "/"+ Integer.parseInt(searchValue2) + "/"+ searchValue3;

				} 
				catch (Exception e) 
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+ e.toString());
				}
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where "+condition+" and a.status='P' "+syntax+" and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,0) not in ('LINKED') and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? order by substr(a.policy_no,9,16) desc";

				sqlMultiple = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in "+condition+" and a.status='P' "+syntax+" and nvl(a.open_cover_int_status,0) in ('LINKED') and to_char(a.inception_date,'dd/MM/YYYY')=? and a.product_id=? group by a.policy_no,a.login_id,a.open_cover_no order by substr(a.policy_no,9,16) desc";
				
				String args[] = new String[3];
				args[0] = searchValue.trim();
				args[1] = productId;
				ss = runner.multipleSelection(sql,args);
				ssMultiple = runner.multipleSelection(sqlMultiple,args);
			}
			getFullDetails.put("singlePolicys", ss);
			getFullDetails.put("multiplePolicys", ssMultiple);
	
		} 
		catch (Exception e) 
		{
		
		}
		return getFullDetails;
	}
	// Policy Number Generation
	public java.util.HashMap getTotalPolicy(String loginIds)
	{
		java.util.HashMap getsTotal = null;
		try 
		{
			String[][] valuess = new String[0][0];
			String[][] ss = null;
			getsTotal = new java.util.HashMap();
			String args1[] = new String[3];
			args1[0] = loginIds;
			args1[1] = loginIds;
			args1[2] = loginIds;
			String sql = "select login_id from login_master where oa_code=? or created_by=? or login_id=?";
			valuess = runner.multipleSelection(sql,args1);

			
			getsTotal.put("total", "" + valuess.length);
			getsTotal.put("getCus", valuess);

			for (int i = 0; i < valuess.length; i++) 
			{
				ss = new String[0][0];
			
				sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,b.first_name,b.last_name,a.login_id,a.quote_no from position_master a,personal_info b where a.login_id=? and a.status='P' and b.customer_id=a.customer_id";
				String args2[] = new String[1];
				args2[0] = valuess[i][0];

				ss = runner.multipleSelection(sql,args2);

				getsTotal.put("totsub" + i, "" + ss.length);

				for (int j = 0; j < ss.length; j++)
				{
					getsTotal.put("Policy_" + i + "_" + j, ss[j][0]);
					getsTotal.put("Id_" + i + "_" + j, ss[j][1]);
					getsTotal.put("Premium_" + i + "_" + j, ss[j][2]);
					getsTotal.put("Edate_" + i + "_" + j, ss[j][3] + "/"
							+ ss[j][4] + "/" + ss[j][5]);
					getsTotal.put("name_" + i + "_" + j, ss[j][6] + " "
							+ ss[j][7]);
					getsTotal.put("Login_" + i + "_" + j, ss[j][8]);
					getsTotal.put("quoteno_" + i + "_" + j, ss[j][9]);

				}
			}
		}
		catch (Exception e)
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return getsTotal;
	}

	public String[][] getTotalPolicys(String loginId) {
		String sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,b.first_name,b.last_name from position_master a,personal_info b where a.login_id='"
				+ loginId
				+ "' and a.status='P' and b.customer_id=a.customer_id";
		String[][] totalPolicys = runner.multipleSelection(sql);
		return totalPolicys;
	}
	/*public String getCarrierNames(String mot,String quoteId) 
	{
		String appno = "";
		String modeOfTransport = "";
		String args1[] = new String[1];
		args1[0] = quoteId;
		String sql = "select APPLICATION_NO from POSITION_MASTER where QUOTE_NO=?";
		appno = runner.singleSelection(sql,args1);
		if (mot.equals("1")) 
		{
			try 
			{
				String args[] = new String[1];
				args[0] = appno;
				sql = "select nvl(open_vessel_name,'Any Approved Vessel as per classification') from MARINE_DATA " +
						"where APPLICATION_NO=?";
				modeOfTransport = runner.singleSelection(sql,args);
			} 
			catch (Exception ex) 
			{
				System.out.println("the Exception COmes Here in CARRIER NAME"
						+ ex.toString());
				modeOfTransport = "Any Approved Vessel as per classification";
			}

		} 
		else if (modeOfTransport.equals("2")) 
		{
			modeOfTransport = "Any Scheduled Airline";
		}
		else 
		{
			modeOfTransport = "";
		}
		setCarrieerName(modeOfTransport);
		return modeOfTransport;
	}*/
	public String getCarrierName(String quoteId, String branchCode) 
	{
		String carName="", qry="";
		try{
//			String args1[] = {branchCode,branchCode,branchCode,quoteId};
			String args1[] = {quoteId};
			/*carName = runner.singleSelection("select case when MODE_OF_TRANSPORT=1 then nvl(open_vessel_name,'Any Approved Vessel') else case when MODE_OF_TRANSPORT=2 then nvl(open_vessel_name,'Any Scheduled Airline') else case when MODE_OF_TRANSPORT=4 then nvl(open_vessel_name,'') else nvl(open_vessel_name,'') end end end" +
						" from MARINE_DATA where APPLICATION_NO=(select APPLICATION_NO from POSITION_MASTER where QUOTE_NO=?)",args1);*/
			carName = runner.singleSelection("SELECT CASE WHEN MODE_OF_TRANSPORT = 1 THEN NVL (OPEN_VESSEL_NAME, 'Any Approved Vessel')WHEN MODE_OF_TRANSPORT = 2 THEN NVL (OPEN_VESSEL_NAME, 'Any Scheduled Airline')WHEN MODE_OF_TRANSPORT = 3 THEN NVL (OPEN_VESSEL_NAME, 'Any Approved Carrier') WHEN MODE_OF_TRANSPORT = 5 THEN NVL (OPEN_VESSEL_NAME, 'Any Approved Courier') WHEN MODE_OF_TRANSPORT = 6 THEN NVL (OPEN_VESSEL_NAME, 'Any Approved Vessel/Scheduled Airline ') ELSE NVL (OPEN_VESSEL_NAME, '') END FROM MARINE_DATA WHERE APPLICATION_NO = (SELECT APPLICATION_NO FROM POSITION_MASTER WHERE QUOTE_NO = ?)",args1);
			/*qry = "SELECT CASE WHEN PM.PRODUCT_ID = '11' THEN (SELECT NVL ( open_vessel_name, (SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND CATEGORY_DETAIL_ID = MD.MODE_OF_TRANSPORT) ) FROM MARINE_DATA MD WHERE MD.APPLICATION_NO = PM.APPLICATION_NO) WHEN PM.PRODUCT_ID = '3' THEN (nvl((SELECT NVL ( CARRIER_NAME, (SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND CATEGORY_DETAIL_ID = MD1.MODE_OF_TRANSPORT) ) FROM MARINE_POLICY_DETAILS MPD WHERE MPD.QUOTE_NO = PM.QUOTE_NO),(SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND CATEGORY_DETAIL_ID = MD1.MODE_OF_TRANSPORT))) ELSE NULL END FROM POSITION_MASTER PM, MARINE_DATA MD1 WHERE PM.QUOTE_NO = ? AND PM.APPLICATION_NO = MD1.APPLICATION_NO";
			qry = "SELECT CASE WHEN PM.PRODUCT_ID = '11' THEN (SELECT NVL ( open_vessel_name, (SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND " +
					"CATEGORY_DETAIL_ID = MD.MODE_OF_TRANSPORT AND BRANCH_CODE=?) ) FROM MARINE_DATA MD WHERE MD.APPLICATION_NO = PM.APPLICATION_NO) WHEN PM.PRODUCT_ID = '3' THEN " +
					"(nvl((SELECT NVL ( CARRIER_NAME, (SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND CATEGORY_DETAIL_ID = MD1.MODE_OF_TRANSPORT AND BRANCH_CODE=?) ) " +
					"FROM MARINE_POLICY_DETAILS MPD WHERE MPD.QUOTE_NO = PM.QUOTE_NO),(SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID = 128 AND " +
					"CATEGORY_DETAIL_ID = MD1.MODE_OF_TRANSPORT AND BRANCH_CODE=?))) ELSE NULL END FROM POSITION_MASTER PM, MARINE_DATA MD1 WHERE PM.QUOTE_NO = ? AND " +
					"PM.APPLICATION_NO = MD1.APPLICATION_NO";*/
			//carName = runner.singleSelection(qry,args1);
			if(carName==null||carName.equalsIgnoreCase("null")){
				carName = "";
			}
			System.out.println("qry " + qry);
		}catch(Exception e){
			e.printStackTrace();
		}
		setCarrieerName(carName);
		return carName;
	}
	public String getStatus(String quoteNo) 
	{
		String app = "";
		String sql = "select status from position_master where quote_no=?";
		String args[] = new String[1];
		args[0] = quoteNo;
		try
		{
			app = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return app;

	}

	public String getStatusMultiple(String quoteNo) 
	{
		String app = "";
		String sql = "select distinct(status) from position_master where quote_no in ("+ quoteNo+")";
		try
		{
			app = runner.singleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return app;

	}
	public String getStatusInfo(String Application_No)
	{
		String app = "";
		String sql = "select status from position_master where application_no=?";
		String args[] = new String[1];
		args[0] = Application_No;
		try
		{
			app = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return app;
	}
	
	public String getPremiumAmount(String policyQuoteNo) 
	{

		String app = "";
		String sql = "";
		sql = "select premium from position_master where policy_no=?";
		String args[] = new String[1];
		args[0] = policyQuoteNo;
		try
		{
			app = runner.singleSelection(sql);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return app;

	}
	public HashMap getExclusionWarrantyClauses(String quoteNo) 
	{
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";
		String[][] dummy = new String[0][0];
		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		// MODIFIED NEWLY FOR AMEND IDS
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items from commoditymaster com,marine_result_details mrd,position_master pm,marine_data md where pm.quote_no=? and com.commodity_id=mrd.commodity_id and pm.application_no=mrd.application_no and  md.application_no=mrd.application_no and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') group by commodity_id)";

		String args[] = new String[1];
		
		try 
		{
			args[0] = quoteNo;
			str_exWar = runner.multipleSelection(commodityDetailsQry,args);

			if (str_exWar.length > 0) {

				for (int i = 0; i < str_exWar.length; i++) {

					exId = exId + "," + (str_exWar[i][1] == null ? "0" : str_exWar[i][1]);
					warId = warId + ","	+ (str_exWar[i][2] == null ? "0" : str_exWar[i][2]);

				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);

				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);

				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				// For getting Exclusion Description
				exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
						+ "("
						+ exId
						+ ")"
						+ " and status in ('Y','R') order by exclusion_id";
				
				str_exDesc = runner.multipleSelection(exDetailsQry);
				exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
						+ "("
						+ warId
						+ ")"
						+ " and status in ('Y','R') order by warranty_id";
				
				str_warDesc = runner.multipleSelection(warDetailsQry);
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description - and extra_cover_id=0
				if ("0".equalsIgnoreCase(extraCoverId))
				{
					clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0  and status in ('Y','R') order by clauses_id";
					
					args = new String[1];
					args[0] = coverId;
					str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);
					clausesCoverQry = "";
				} 
				else 
				{
					
					clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0  and status in ('Y','R') order by clauses_id";
					args = new String[1];
					args[0] = coverId;
					
					str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					exWarCla.put("clausesDesc", str_coverDesc);
					
					
					clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id='"+extraCoverId+"') and status in ('Y','R') order by clauses_id";
					
					
					str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);
					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				}
				exWarCla.put("status", "RESULTS");
			} 
			else
			{
				exWarCla.put("status", "NO RESULTS");
			}
		} catch (Exception ex) 
		  {
			System.out.println("the Exception occured in GET getExclusionWarrantyClauses IS "+ ex.toString());
		  }

		return exWarCla;

	}

	public HashMap getExclusionWarrantyClauses(String quoteNo,String loginCode,String editStatus) throws BaseException
	{
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";

		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();

		// /Newly Coding
		String pdfDisplayStatus[][] = new String[0][0];
		String pdfDisplayClauses[][] = new String[0][0];
		String pdfDisplayExtraClauses[][] = new String[0][0];
		String pdfDisplayWarranties[][] = new String[0][0];
		String pdfDisplayExclusions[][] = new String[0][0];

		String pdfClauses = "NOTHING";
		String pdfexClauses = "NOTHING";
		String pdfWars = "NOTHING";
		String pdfEx = "NOTHING";
		String concatClausesIds = "";
		String concatClasusesDesc = "";

		String concatExClausesIds = "";
		String concatExClasusesDesc = "";

		String concatExtraClausesIds = "";
		String concatExtraClasusesDesc = "";

		String concatWarClausesIds = "";
		String concatWarClasusesDesc = "";

		HashMap premiumdetails = new HashMap();

		String sqlCheck = "";
		String sqlUpdate = "";
		String sqlUpdateEx = "";

		String exisOldCoverId = "0";
		String exisOldExCoverId = "0";
		String exisOldTransportId = "0";

		String exisCoverId = "0";
		String exisExCoverId = "0";
		String exisTransportId = "0";
		String[][] dummy = new String[0][0];
		String[][] existingModes = new String[0][0];
		
		String branchQry ="";
		String branchCode ="";

		try
		{
			String args1[] = new String[1];
			args1[0] = loginCode;
			branchQry = "select nvl(BRANCH_CODE,'020') from BROKER_COMPANY_MASTER where AGENCY_CODE=(select oa_code FROM login_master where login_id=?)";
			branchCode = runner.singleSelection(branchQry,args1);
		}
		catch(Exception e)
		{
			System.out.println("Premium Preint123 Policy Info..."+e.toString());
			e.printStackTrace();
		}

		sqlCheck = "select PDF_MODE_TRANSPORT_ID,PDF_COVER_ID,PDF_EXTRA_COVER_ID,mode_of_transport,cover_id,extra_cover_id from marine_data md,position_master pm where  md.application_no=pm.application_no and pm.quote_no=?";
		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			existingModes = runner.multipleSelection(sqlCheck,args);
			if (existingModes.length > 0) 
			{
				exisCoverId = existingModes[0][4] == null ? "0": existingModes[0][4];
				exisExCoverId = existingModes[0][5] == null ? "0": existingModes[0][5];
				exisTransportId = existingModes[0][3] == null ? "0": existingModes[0][3];
				exisOldCoverId = existingModes[0][1] == null ? ("0"): existingModes[0][1];
				exisOldExCoverId = existingModes[0][2] == null ? ("0"): existingModes[0][2];
				exisOldTransportId = existingModes[0][0] == null ? ("0"): existingModes[0][0];
			}
		} 
		catch (Exception e) 
		{
			System.out.println(".....RoyalTest Exception first block in Cluses policyInfo.java..."+e.toString());
			e.printStackTrace();
		}

		// Condition Checkiing Starts
		if (!(exisCoverId.equalsIgnoreCase(exisOldCoverId))	|| !(exisTransportId.equalsIgnoreCase(exisOldTransportId))) 
		{

			sqlUpdate = "update position_master set  PDF_MODIFY_CLAUSE='"
					+ "NOTHING" + "',PDF_MODIFY_WARRANTY='" + "NOTHING"
					+ "',PDF_MODIFY_EXCLUSION='" + "NOTHING"
					+ "',PDF_MODIFY_EXTRACLAUSES='" + "NOTHING"
					+ "' where  quote_no=?";

			String args[] = new String[1];
			args[0] = quoteNo;

			runner.multipleUpdation(sqlUpdate,args);

			sqlUpdate = "";

		} 
		else if (!(exisExCoverId.equalsIgnoreCase(exisOldExCoverId))) 
		{
			pdfexClauses = "NOTHING";

			sqlUpdateEx = "update position_master set PDF_MODIFY_EXTRACLAUSES='"
					+ "NOTHING" + "' where  quote_no=?";

			String args[] = new String[1];
			args[0] = quoteNo;

			runner.multipleUpdation(sqlUpdateEx,args);

			sqlUpdateEx = "";

		}

		pdfDisplayStatus = pdis.getPreBankOptions(quoteNo, loginCode, "draft");
        System.out.println("===========>1");
		if (pdfDisplayStatus.length > 0) {

			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses
					: pdfDisplayStatus[0][2];
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses
					: pdfDisplayStatus[0][5];
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars
					: pdfDisplayStatus[0][3];
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx
					: pdfDisplayStatus[0][4];

		} else {

		}

		if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
		{
			concatClausesIds = "0";
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		else
		{
			pdfDisplayClauses = makeTwoDimArray(pdfClauses);
		

			for (int i = 0; i < pdfDisplayClauses.length; i++) {
				concatClausesIds = concatClausesIds
						+ ","
						+ (pdfDisplayClauses[i][0] == null ? "0"
								: pdfDisplayClauses[i][0]);
			}

			concatClausesIds = concatClausesIds.substring(1, concatClausesIds
					.length());

		

			exWarCla.put("editedClauses", pdfDisplayClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
			concatExtraClausesIds = "0";
			premiumdetails.put("editedExtraClauses", pdfDisplayExtraClauses);
		} else {
			pdfDisplayExtraClauses = makeTwoDimArray(pdfexClauses);

			

			for (int i = 0; i < pdfDisplayExtraClauses.length; i++) {
				concatExtraClausesIds = concatExtraClausesIds
						+ ","
						+ (pdfDisplayExtraClauses[i][0] == null ? "0"
								: pdfDisplayExtraClauses[i][0]);
			}

			concatExtraClausesIds = concatExtraClausesIds.substring(1,
					concatExtraClausesIds.length());

			

			exWarCla.put("editedExtraClauses", pdfDisplayExtraClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfWars)) {
			concatWarClausesIds = "0";
			premiumdetails.put("editedWarClauses", pdfDisplayWarranties);
		} else {
			pdfDisplayWarranties = makeTwoDimArray(pdfWars);

			

			for (int i = 0; i < pdfDisplayWarranties.length; i++) {
				concatWarClausesIds = concatWarClausesIds
						+ ","
						+ (pdfDisplayWarranties[i][0] == null ? "0"
								: pdfDisplayWarranties[i][0]);
			}

			concatWarClausesIds = concatWarClausesIds.substring(1,
					concatWarClausesIds.length());

			

			exWarCla.put("editedWarClauses", pdfDisplayWarranties);

		}

		if ("NOTHING".equalsIgnoreCase(pdfEx)) 
		{
			concatExClausesIds = "0";
			premiumdetails.put("editedExClauses", pdfDisplayExclusions);
			
		} 
		else 
		{
			pdfDisplayExclusions = makeTwoDimArray(pdfEx);

			

			for (int i = 0; i < pdfDisplayExclusions.length; i++) 
			{
				
				concatExClausesIds = concatExClausesIds	+ ","+ (pdfDisplayExclusions[i][0] == null ? "0": pdfDisplayExclusions[i][0]);
				
			}

			

			concatExClausesIds = concatExClausesIds.substring(1,
					concatExClausesIds.length());

			exWarCla.put("editedExClauses", pdfDisplayExclusions);
		}

		
		//Country Work 
		
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items,md.mode_of_transport from commoditymaster com,marine_result_details mrd,position_master pm,marine_data md where pm.quote_no=? and com.commodity_id=mrd.commodity_id and com.branch_code=? and pm.application_no=mrd.application_no and  md.application_no=mrd.application_no and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and branch_code=? group by commodity_id)";

		try 
		{
			String args1[] = new String[3];
			args1[0] = quoteNo;
			args1[1] = branchCode;
			args1[2] = branchCode;

			String transPortId = "0";
			String coverIdCheck = "0";
			str_exWar = runner.multipleSelection(commodityDetailsQry,args1);

			if (str_exWar.length > 0) {
				
				transPortId = str_exWar[0][6] == null ? "0" : str_exWar[0][6];
				coverIdCheck = str_exWar[0][3] == null ? "0" : str_exWar[0][3];

				for (int i = 0; i < str_exWar.length; i++) {
					
					if (("2".equalsIgnoreCase(transPortId) && "5" .equalsIgnoreCase(coverIdCheck))
							|| ("1".equalsIgnoreCase(transPortId) && ("2"
									.equalsIgnoreCase(coverIdCheck) || "3"
									.equalsIgnoreCase(coverIdCheck)))) {
						exId = exId + "," + "0";
					} else {
						exId = exId	+ "," + (str_exWar[i][1] == null ? "0" : str_exWar[i][1]);
					}
					if ("2".equalsIgnoreCase(transPortId)&& "5".equalsIgnoreCase(coverIdCheck)) 
					{
						warId = warId + "," + "0";

					} else {
						warId = warId+ ","+ (str_exWar[i][2] == null ? "0": str_exWar[i][2]);

					}

				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);

				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);

				
				exWarCla.put("transportId", transPortId);

				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				// For getting Exclusion Description
				if ("NOTHING".equalsIgnoreCase(pdfEx)) 
				{
					exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in ("+exId+")"+" and status in ('Y','R') and branch_code='"+branchCode+"' order by exclusion_id";
					str_exDesc = runner.multipleSelection(exDetailsQry);
				} 
				else 
				{
					exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in ("+exId	+") and exclusion_id not in ("+concatExClausesIds+") and status in ('Y','R') and branch_code='"+branchCode+"'order by exclusion_id";
					str_exDesc = runner.multipleSelection(exDetailsQry);
				}
			
				exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				if ("NOTHING".equalsIgnoreCase(pdfWars)) 
				{
					warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in ("+warId+") and status in ('Y','R') and branch_code='"+branchCode+"'order by warranty_id";
					str_warDesc = runner.multipleSelection(warDetailsQry);
				} 
				else 
				{
					warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in ("+warId+")and warranty_id not in ("+concatWarClausesIds+") and status in ('Y','R') and branch_code='"+branchCode+"'order by warranty_id";
					str_warDesc = runner.multipleSelection(warDetailsQry);
				}
				
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description and extra_cover_id=0
				if ("0".equalsIgnoreCase(extraCoverId)) 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and status in ('Y','R') and branch_code=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = branchCode;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					}
					else //and extra_cover_id=0
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and clauses_id not in("+ concatClausesIds + ") and branch_code=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = branchCode;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					}
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);
					clausesCoverQry = "";
				}
				else //and extra_cover_id=0
				 {
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and status in ('Y','R') and branch_code=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = branchCode;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					} 
					else //and extra_cover_id=0
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and clauses_id not in("+ concatClausesIds + ") and branch_code=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = branchCode;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
					}
					exWarCla.put("clausesDesc", str_coverDesc);


					if ("NOTHING".equalsIgnoreCase(pdfexClauses)) //newone
					{
						clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and extra_cover_id='"+extraCoverId+"') and status in ('Y','R') and branch_code='"+branchCode+"'order by clauses_id";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
					} 
					else 
					{
						
						clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and extra_cover_id='"+extraCoverId+"') and clauses_id not in("+concatExtraClausesIds+") and status in ('Y','R') and branch_code='"+branchCode+"'order by clauses_id";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
					}
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);
					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				}
				exWarCla.put("status", "RESULTS");
			}
			else 
			{
				exWarCla.put("status", "NO RESULTS");
			}
			
		}
		catch (Exception e) 
		{
			System.out.println(".....RoyalTest Exception last block in Cluses policyInfo.java..."+e.toString());
			e.printStackTrace();
		}
		return exWarCla;
	}
	//need
	//Modified by Rajesh For Customer Mail With Clauses and Exclusion
	public HashMap getExclusionWarrantyClauses(String appNo,String loginCode)
	{
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";
		String[][] dummy = new String[0][0];
		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();

		// /Newly Coding
		String pdfDisplayStatus[][] = new String[0][0];
		String pdfDisplayClauses[][] = new String[0][0];
		String pdfDisplayExtraClauses[][] = new String[0][0];
		String pdfDisplayWarranties[][] = new String[0][0];
		String pdfDisplayExclusions[][] = new String[0][0];

		String pdfClauses = "NOTHING";
		String pdfexClauses = "NOTHING";
		String pdfWars = "NOTHING";
		String pdfEx = "NOTHING";
		String concatClausesIds = "";
		String concatClasusesDesc = "";

		String concatExClausesIds = "";
		String concatExClasusesDesc = "";

		String concatExtraClausesIds = "";
		String concatExtraClasusesDesc = "";

		String concatWarClausesIds = "";
		String concatWarClasusesDesc = "";

		HashMap premiumdetails = new HashMap();

		String sqlCheck = "";
		String sqlUpdate = "";
		String sqlUpdateEx = "";

		String exisOldCoverId = "0";
		String exisOldExCoverId = "0";
		String exisOldTransportId = "0";

		String exisCoverId = "0";
		String exisExCoverId = "0";
		String exisTransportId = "0";

		String[][] existingModes = new String[0][0];

		try 
		{
			sqlCheck = "select mode_of_transport,cover_id,extra_cover_id from marine_data  where application_no='"+appNo+"'";
			System.out.println("Royalk test the Checking Existing query si " + sqlCheck);
			existingModes = runner.multipleSelection(sqlCheck);
			if (existingModes.length > 0) 
			{
					exisTransportId = existingModes[0][0] == null ?"0":existingModes[0][0];
					exisCoverId = existingModes[0][1] == null ? "0":existingModes[0][1];
					exisExCoverId = existingModes[0][2] == null ?"0":existingModes[0][2];
					exisOldCoverId = "0";
					exisOldExCoverId = "0";
					exisOldTransportId = "0";
			}
		}catch (Exception ex) {ex.printStackTrace();	}

		// Condition Checkiing Starts
		
		
		
		if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
		{
			concatClausesIds = "0";
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		else
		{
			pdfDisplayClauses = makeTwoDimArray(pdfClauses);
			for (int i = 0; i < pdfDisplayClauses.length; i++) 
			{
				concatClausesIds = concatClausesIds	+ ","+ (pdfDisplayClauses[i][0] == null ? "0": pdfDisplayClauses[i][0]);
			}
			concatClausesIds = concatClausesIds.substring(1, concatClausesIds.length());
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
			concatExtraClausesIds = "0";
			premiumdetails.put("editedExtraClauses", pdfDisplayExtraClauses);
		} 
		else
		{
			pdfDisplayExtraClauses = makeTwoDimArray(pdfexClauses);
			
			for (int i = 0; i < pdfDisplayExtraClauses.length; i++) 
			{
				concatExtraClausesIds = concatExtraClausesIds+ ","+ (pdfDisplayExtraClauses[i][0] == null ? "0"	: pdfDisplayExtraClauses[i][0]);
			}

			concatExtraClausesIds = concatExtraClausesIds.substring(1,concatExtraClausesIds.length());

			exWarCla.put("editedExtraClauses", pdfDisplayExtraClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfWars)) {
			concatWarClausesIds = "0";
			premiumdetails.put("editedWarClauses", pdfDisplayWarranties);
		} else {
			pdfDisplayWarranties = makeTwoDimArray(pdfWars);
			
			for (int i = 0; i < pdfDisplayWarranties.length; i++) 
			{
				concatWarClausesIds = concatWarClausesIds+ ","+ (pdfDisplayWarranties[i][0] == null ? "0": pdfDisplayWarranties[i][0]);
			}
			concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
			exWarCla.put("editedWarClauses", pdfDisplayWarranties);
		}

		if ("NOTHING".equalsIgnoreCase(pdfEx)) {
			concatExClausesIds = "0";
			premiumdetails.put("editedExClauses", pdfDisplayExclusions);
			
		} else {
			pdfDisplayExclusions = makeTwoDimArray(pdfEx);
			
			for (int i = 0; i < pdfDisplayExclusions.length; i++) 
			{
				concatExClausesIds = concatExClausesIds	+ ","+ (pdfDisplayExclusions[i][0] == null ? "0":pdfDisplayExclusions[i][0]);
			}
			concatExClausesIds = concatExClausesIds.substring(1,concatExClausesIds.length());
			exWarCla.put("editedExClauses", pdfDisplayExclusions);
		}
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items,md.mode_of_transport from commoditymaster com,marine_result_details mrd,marine_data md where md.application_no='"+appNo+"' and com.commodity_id=mrd.commodity_id and com.BRANCH_CODE='"+loginBra+"' and  md.application_no=mrd.application_no and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and BRANCH_CODE='"+loginBra+"' and status in ('Y','R') group by commodity_id)";
		try 
		{
			String transPortId = "0";
			String coverIdCheck = "0";
			str_exWar = runner.multipleSelection(commodityDetailsQry);

			if (str_exWar.length > 0) 
			{
				
				transPortId = str_exWar[0][6] == null ? "0" : str_exWar[0][6];
				coverIdCheck = str_exWar[0][3] == null ? "0" : str_exWar[0][3];

				for (int i = 0; i < str_exWar.length; i++) 
				{

					if (("2".equalsIgnoreCase(transPortId) && "5"
							.equalsIgnoreCase(coverIdCheck))
							|| ("1".equalsIgnoreCase(transPortId) && ("2"
									.equalsIgnoreCase(coverIdCheck) || "3"
									.equalsIgnoreCase(coverIdCheck)))) {
						exId = exId + "," + "0";
					} else {
						exId = exId	+ ","+ (str_exWar[i][1] == null ? "0": str_exWar[i][1]);
					}
					if ("2".equalsIgnoreCase(transPortId)&& "5".equalsIgnoreCase(coverIdCheck)) {
						warId = warId + "," + "0";

					} else {
						warId = warId	+ ","+ (str_exWar[i][2] == null ? "0": str_exWar[i][2]);
					}

				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);

				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);
				exWarCla.put("transportId", transPortId);
				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				String coverDesc = "", coverDescQry = "";
				coverDescQry = "select distinct description from cover_master where branch_code = " + loginBra + " and cover_id = " + coverId + "";
				coverDesc = runner.singleSelection(coverDescQry);
				System.out.println("coverDesc " + coverDesc);
				
				// For getting Exclusion Description
				/*if ("NOTHING".equalsIgnoreCase(pdfEx)) {
					exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
							+ "("
							+ exId
							+ ")"
							+ " and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by exclusion_id";
					
					exDetailsQry = "SELECT DISTINCT EM.EXCLUSION_DESCRIPTION,EM.STATUS,EM.EXCLUSION_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,EXCLUSION_MASTER EM " +
					"WHERE EM.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + 
					coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + 
					coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					"AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					appNo + ") AND CM.BRANCH_CODE=EM.BRANCH_CODE ORDER BY EM.EXCLUSION_ID";
					
					str_exDesc = runner.multipleSelection(exDetailsQry);
				} else {

					exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
							+ "("
							+ exId
							+ ") and exclusion_id not in ("
							+ concatExClausesIds
							+ ") and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by exclusion_id";
					
					exDetailsQry = "SELECT DISTINCT EM.EXCLUSION_DESCRIPTION,EM.STATUS,EM.EXCLUSION_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,EXCLUSION_MASTER EM " +
					"WHERE EM.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
					"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + 
					",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + 
					",'~',1,1))), ',', ''))+ 1) AND EM.EXCLUSION_ID NOT IN (" + concatExClausesIds + ")	AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					"AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					appNo + ") AND CM.BRANCH_CODE=EM.BRANCH_CODE ORDER BY EM.EXCLUSION_ID";
					
					str_exDesc = runner.multipleSelection(exDetailsQry);
				}*/
				String sql="SELECT CODEDESC,CODESTATUS,CODE FROM TABLE(SELECT VIEWCLAUSES(?,?,?) FROM DUAL)";
				str_exDesc=runner.multipleSelection(sql, new String[]{"Exclusion",appNo, loginBra});
				 exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				/*if ("NOTHING".equalsIgnoreCase(pdfWars))
				{
					warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
							+ "("
							+ warId
							+ ")"
							+ " and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by warranty_id";
					str_warDesc = runner.multipleSelection(warDetailsQry);
					
					warDetailsQry = "SELECT DISTINCT WM.WARRANTY_DESCRIPTION,WM.STATUS,WM.WARRANTY_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,WARRANTY_MASTER WM " +
					"WHERE WM.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
					"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + 
					",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) " +
					"AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE " +
					"AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					appNo + ") AND CM.BRANCH_CODE=WM.BRANCH_CODE ORDER BY WM.WARRANTY_ID";
					str_warDesc = runner.multipleSelection(warDetailsQry);
					
				} 
				else {

					warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
							+ "("
							+ warId
							+ ") and warranty_id not in ("
							+ concatWarClausesIds
							+ ") and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by warranty_id";
					str_warDesc = runner.multipleSelection(warDetailsQry);
					
					warDetailsQry = "SELECT DISTINCT WM.WARRANTY_DESCRIPTION,WM.STATUS,WM.WARRANTY_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,WARRANTY_MASTER WM " +
					"WHERE WM.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + 
					coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
					",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) " +
					"AND WM.WARRANTY_ID NOT IN (" + concatWarClausesIds + ") AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					appNo + ") AND CM.BRANCH_CODE=WM.BRANCH_CODE ORDER BY WM.WARRANTY_ID";
					str_warDesc = runner.multipleSelection(warDetailsQry);
				}*/
				 
				 str_warDesc=runner.multipleSelection(sql, new String[]{"Warranty",appNo, loginBra});
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description and extra_cover_id=0
				/*if("0".equalsIgnoreCase(extraCoverId)) 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) //and extra_cover_id=0
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id=0 and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
						"application_no = " + appNo + ") ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid0if " + clausesCoverQry);

					} 
					else //and extra_cover_id=0
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id=0 and clauses_id not in("+ concatClausesIds + ") and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
						"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
						") AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
						"application_no = " + appNo + ") ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid0else " + clausesCoverQry);
					}
		
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);
					clausesCoverQry = "";
				}
				else 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) //and extra_cover_id=0
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id=0 and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
						"application_no = " + appNo + ") ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid!0if " + clausesCoverQry);

					} 
					else 
					{
						clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId	+ "' and extra_cover_id=0  and clauses_id not in("+ concatClausesIds + ") and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
						"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
						") AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
						"application_no =  " + appNo + ") ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid!0else " + clausesCoverQry);
						
					}
					exWarCla.put("clausesDesc", str_coverDesc);
					if ("NOTHING".equalsIgnoreCase(pdfexClauses)) 
					{
						clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id='"+extraCoverId+"') and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						
						clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
						"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
						+ ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
						+ ",'~',1,3))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID = " + extraCoverId + " AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
						appNo + ") ORDER BY CM.CLAUSES_ID";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						System.out.println("coverid!=0 if " + clausesExtraCoverQry);
					} 
					else 
					{
						clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id='"+extraCoverId+"')  and clauses_id not in("+ concatExtraClausesIds+ ") and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by clauses_id";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);	
						
						clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
						"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
						coverDesc + ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
						coverDesc + ",'~',1,3))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN ( " + concatExtraClausesIds + " ) AND CM.EXTRA_COVER_ID = " + extraCoverId + "  AND CM.STATUS IN ('Y','R') " +
						"AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
						appNo + ") ORDER BY CM.CLAUSES_ID";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						System.out.println("coverid!=0 else " + clausesExtraCoverQry);
					}
					
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);
					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				}*/
			 
			 
				 str_warDesc=runner.multipleSelection(sql, new String[]{"Clauses",appNo, loginBra});
				 exWarCla.put("clausesDesc", str_warDesc);	
				 str_warDesc= runner.multipleSelection(sql, new String[]{"War",appNo, loginBra});
				 exWarCla.put("extraClausesDesc", str_warDesc);
					
				exWarCla.put("status", "RESULTS");
			} else {
				exWarCla.put("status", "NO RESULTS");
			}
		} catch (Exception ex) {

			System.out.println("the WERT Exception occured in GET getExclusionWarrantyClauses IS "+ ex.toString());
		}
		return exWarCla;
	}
	
	public HashMap getExclusionWarrantyClauses(String quoteNo,String loginCode, String editStatus, String productId,
			String openCoverNo) throws BaseException {
		
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";

		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();

		// /Newly Coding
		String pdfDisplayStatus[][] = new String[0][0];
		String pdfDisplayClauses[][] = new String[0][0];
		String pdfDisplayExtraClauses[][] = new String[0][0];
		String pdfDisplayWarranties[][] = new String[0][0];
		String pdfDisplayExclusions[][] = new String[0][0];

		String pdfClauses = "NOTHING";
		String pdfexClauses = "NOTHING";
		String pdfWars = "NOTHING";
		String pdfEx = "NOTHING";
		String concatClausesIds = "";
		String concatClasusesDesc = "";

		String concatExClausesIds = "";
		String concatExClasusesDesc = "";

		String concatExtraClausesIds = "";
		String concatExtraClasusesDesc = "";

		String concatWarClausesIds = "";
		String concatWarClasusesDesc = "";

		HashMap premiumdetails = new HashMap();

		String sqlCheck = "";
		String sqlUpdate = "";
		String sqlUpdateEx = "";

		String exisOldCoverId = "0";
		String exisOldExCoverId = "0";
		String exisOldTransportId = "0";

		String exisCoverId = "0";
		String exisExCoverId = "0";
		String exisTransportId = "0";

		String[][] existingModes = new String[0][0];
		String[][] dummy = new String[0][0];


		sqlCheck = "select PDF_MODE_TRANSPORT_ID,PDF_COVER_ID,PDF_EXTRA_COVER_ID,mode_of_transport,cover_id,extra_cover_id from marine_data md,position_master pm where  md.application_no=pm.application_no and pm.quote_no='"+ quoteNo + "'";


		try {
			existingModes = runner.multipleSelection(sqlCheck);

			if (existingModes.length > 0) {
				exisCoverId = existingModes[0][4] == null ? "0": existingModes[0][4];
				exisExCoverId = existingModes[0][5] == null ? "0": existingModes[0][5];
				exisTransportId = existingModes[0][3] == null ? "0": existingModes[0][3];
				exisOldCoverId = existingModes[0][1] == null ? ("0"): existingModes[0][1];
				exisOldExCoverId = existingModes[0][2] == null ? ("0"): existingModes[0][2];
				exisOldTransportId = existingModes[0][0] == null ? ("0"): existingModes[0][0];
			}
		} catch (Exception ex) 
			{
		}

		// Condition Checkiing Starts
		if (!(exisCoverId.equalsIgnoreCase(exisOldCoverId))|| !(exisTransportId.equalsIgnoreCase(exisOldTransportId))) 
		{
			sqlUpdate = "update position_master set  PDF_MODIFY_CLAUSE='"+ "NOTHING" + "',PDF_MODIFY_WARRANTY='" + "NOTHING"+ "',PDF_MODIFY_EXCLUSION='" + "NOTHING"+ "',PDF_MODIFY_EXTRACLAUSES='" + "NOTHING"+ "' where  quote_no='" + quoteNo + "'";
			runner.updation(sqlUpdate);
			sqlUpdate = "";
		}
		else if (!(exisExCoverId.equalsIgnoreCase(exisOldExCoverId)))
		{
			pdfexClauses = "NOTHING";
			sqlUpdateEx = "update position_master set PDF_MODIFY_EXTRACLAUSES='"+ "NOTHING" + "' where  quote_no='" + quoteNo + "'";
			runner.updation(sqlUpdateEx);
			sqlUpdateEx = "";
		}

		pdfDisplayStatus = pdis.getPreBankOptions(quoteNo, loginCode, "draft");
		System.out.println("===========>2");

		if (pdfDisplayStatus.length > 0) {

			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses: pdfDisplayStatus[0][2];
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses: pdfDisplayStatus[0][5];
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars: pdfDisplayStatus[0][3];
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx: pdfDisplayStatus[0][4];

		} else {

		}
		if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
		{
			concatClausesIds = "0";
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		else 
		{
			pdfDisplayClauses = makeTwoDimArray(pdfClauses);
			
			for (int i = 0; i < pdfDisplayClauses.length; i++) 
			{
				concatClausesIds = concatClausesIds	+ ","+ (pdfDisplayClauses[i][0] == null ? "0": pdfDisplayClauses[i][0]);
			}

			concatClausesIds = concatClausesIds.substring(1, concatClausesIds.length());
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}

		if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
			concatExtraClausesIds = "0";
			premiumdetails.put("editedExtraClauses", pdfDisplayExtraClauses);
		} else {
			pdfDisplayExtraClauses = makeTwoDimArray(pdfexClauses);
			for (int i = 0; i < pdfDisplayExtraClauses.length; i++) {
				concatExtraClausesIds = concatExtraClausesIds
						+ ","
						+ (pdfDisplayExtraClauses[i][0] == null ? "0"
								: pdfDisplayExtraClauses[i][0]);
			}

			concatExtraClausesIds = concatExtraClausesIds.substring(1,
					concatExtraClausesIds.length());
			exWarCla.put("editedExtraClauses", pdfDisplayExtraClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfWars)) 
		{
			concatWarClausesIds = "0";
			premiumdetails.put("editedWarClauses", pdfDisplayWarranties);
		} 
		else 
		{
			pdfDisplayWarranties = makeTwoDimArray(pdfWars);
			
			for (int i = 0; i < pdfDisplayWarranties.length; i++) {
				concatWarClausesIds = concatWarClausesIds + ","	+ (pdfDisplayWarranties[i][0] == null ? "0": pdfDisplayWarranties[i][0]);
			}
			concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
		
			exWarCla.put("editedWarClauses", pdfDisplayWarranties);
		}

		if("NOTHING".equalsIgnoreCase(pdfEx)) 
		{
			concatExClausesIds = "0";
			premiumdetails.put("editedExClauses", pdfDisplayExclusions);
			
		} else {
			pdfDisplayExclusions = makeTwoDimArray(pdfEx);
			

			for (int i = 0; i < pdfDisplayExclusions.length; i++) {
				
				concatExClausesIds = concatExClausesIds
						+ ","
						+ (pdfDisplayExclusions[i][0] == null ? "0"
								: pdfDisplayExclusions[i][0]);
				
			}

			

			concatExClausesIds = concatExClausesIds.substring(1,
					concatExClausesIds.length());

			

			exWarCla.put("editedExClauses", pdfDisplayExclusions);
		}

		

		
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items,md.mode_of_transport from commoditymaster com,marine_result_details mrd,position_master pm,marine_data md where pm.quote_no='"
				+ quoteNo
				+ "' and com.commodity_id=mrd.commodity_id and pm.application_no=mrd.application_no and  md.application_no=mrd.application_no and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') group by commodity_id)";

		

		try {
			
			String transPortId = "0";
			String coverIdCheck = "0";
			str_exWar = runner.multipleSelection(commodityDetailsQry);

			if (str_exWar.length > 0) {
				
				transPortId = str_exWar[0][6] == null ? "0" : str_exWar[0][6];
				coverIdCheck = str_exWar[0][3] == null ? "0" : str_exWar[0][3];

				for (int i = 0; i < str_exWar.length; i++) 
				{
					
					if (("2".equalsIgnoreCase(transPortId) && "5"
							.equalsIgnoreCase(coverIdCheck))
							|| ("1".equalsIgnoreCase(transPortId) && ("2"
									.equalsIgnoreCase(coverIdCheck) || "3"
									.equalsIgnoreCase(coverIdCheck)))) {
						exId = exId + "," + "0";
					} else {
						exId = exId
								+ ","
								+ (str_exWar[i][1] == null ? "0"
										: str_exWar[i][1]);
					}

					if ("2".equalsIgnoreCase(transPortId)
							&& "5".equalsIgnoreCase(coverIdCheck)) {

						warId = warId + "," + "0";

					} else {
						warId = warId
								+ ","
								+ (str_exWar[i][2] == null ? "0"
										: str_exWar[i][2]);

					}

				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);

				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);

				exWarCla.put("transportId", transPortId);

				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				// For getting Exclusion Description
				if ("NOTHING".equalsIgnoreCase(pdfEx)) {

					if ("11".equalsIgnoreCase(productId)) {

						exDetailsQry = "select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"
								+ openCoverNo
								+ "'    and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"
								+ openCoverNo
								+ "'  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";

					} else if ("3".equalsIgnoreCase(productId)) {
						exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
								+ "("
								+ exId
								+ ")"
								+ " and status in ('Y','R') order by exclusion_id";
					}

					
				}

				else {

					if ("11".equalsIgnoreCase(productId)) {
						

						exDetailsQry = "select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"
								+ openCoverNo
								+ "'  and occmss.exclusion_id not in("
								+ concatExClausesIds
								+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"
								+ openCoverNo
								+ "' and (occms.exclusion_id not in("
								+ concatExClausesIds
								+ ") and occms.amend_id in( select max(occmsss.amend_id) from open_cover_exclusions occmsss,open_cover_position_master ocpmsss where ocpmsss.open_cover_no ='"
								+ openCoverNo
								+ "' and ocpmsss.proposal_no=occmsss.proposal_no and to_date(occmsss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')))  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";

					} else if ("3".equalsIgnoreCase(productId)) {
						exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
								+ "("
								+ exId
								+ ") and exclusion_id not in ("
								+ concatExClausesIds
								+ ") and status in ('Y','R') order by exclusion_id";
					}

					

				}

				str_exDesc = runner.multipleSelection(exDetailsQry);
				exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				if ("NOTHING".equalsIgnoreCase(pdfWars)) {

					if ("11".equalsIgnoreCase(productId)) {
						warDetailsQry = "select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"
								+ openCoverNo
								+ "'   and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"
								+ openCoverNo
								+ "'  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";

					} else if ("3".equalsIgnoreCase(productId)) {

						warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
								+ "("
								+ warId
								+ ")"
								+ " and status in ('Y','R') order by warranty_id";

					}

				} else {

					// NEWLY ADDED CODING

					if ("11".equalsIgnoreCase(productId)) {
						

						warDetailsQry = "select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"
								+ openCoverNo
								+ "'   and occmss.warranty_id not in("
								+ concatWarClausesIds
								+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"
								+ openCoverNo
								+ "'  and (occms.warranty_id not in("
								+ concatWarClausesIds
								+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"
								+ openCoverNo
								+ "' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) ) and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";

					} else if ("3".equalsIgnoreCase(productId)) {
						warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
								+ "("
								+ warId
								+ ") and warranty_id not in ("
								+ concatWarClausesIds
								+ ") and status in ('Y','R') order by warranty_id";
					}
					
				}
				str_warDesc = runner.multipleSelection(warDetailsQry);
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description
				if ("0".equalsIgnoreCase(extraCoverId)) //and extra_cover_id='0'
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) {
						if ("11".equalsIgnoreCase(productId)) {
							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and occmss.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0' and occmss.status in ('Y') and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+ openCoverNo+ "' and occms.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0' and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						} else if ("3".equalsIgnoreCase(productId)) 
						 {
							clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0' and status in ('Y','R') order by clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						}
						
					} else {

						if ("11".equalsIgnoreCase(productId)) {
							

							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and occmss.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0'  and occmss.clauses_id not in("+ concatClausesIds+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"	+ openCoverNo+ "' and occms.cover_id='"	+ coverId+ "' and occmss.extra_cover_id='0' and (occms.clauses_id not in("					+ concatClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"	+ openCoverNo+ "' and occmss.cover_id='"+ coverId	+ "' and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);

						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") order by clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						}
						
					}

					
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);

					clausesCoverQry = "";

				} 
				else 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
					{
						
						if ("11".equalsIgnoreCase(productId)) 
						{
							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and occmss.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0' and occmss.status in ('Y') and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+ openCoverNo+ "' and occms.cover_id='"+ coverId+ "' and occms.extra_cover_id='0' and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						} 
						else if ("3".equalsIgnoreCase(productId)) 
						{
							clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0'  and status in ('Y','R') order by clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						}

						
					} 
					else
					{
						if ("11".equalsIgnoreCase(productId)) 
						{
							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and occmss.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0' and occmss.clauses_id not in("+ concatClausesIds+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+ openCoverNo+ "' and occms.cover_id='"+ coverId+ "' and occmss.extra_cover_id='0' and (occms.clauses_id not in("+ concatClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and occmss.cover_id='"+ coverId+"' and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))  order by occmss.clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") order by clauses_id";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
						}

						
					}

					exWarCla.put("clausesDesc", str_coverDesc);
					
					if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
						
						if ("11".equalsIgnoreCase(productId))
						{	
							clausesExtraCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"') and occmss.status in ('Y') and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and  ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+ openCoverNo+ "' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"') and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						} 
						else if ("3".equalsIgnoreCase(productId)) 
						{
							//clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where extra_cover_id='"+ extraCoverId	+ "' and status in ('Y','R') order by clauses_id";
							clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where extra_cover_id='"+extraCoverId+"' and cover_id in('"+coverId+"') and status in ('Y','R') order by clauses_id";
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						}

						

					}
					else 
					{
						if ("11".equalsIgnoreCase(productId)) 
						{	
							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"')  and occmss.clauses_id not in("+ concatExtraClausesIds+ ") and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+ openCoverNo+ "'  and (occms.clauses_id not in("+ concatExtraClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+ openCoverNo+ "' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"') and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))  order by occmss.clauses_id";
							str_extraCoverDesc = runner.multipleSelection(clausesCoverQry);
						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id='"+extraCoverId+"')  and clauses_id not in("+ concatExtraClausesIds+ ") and status in ('Y','R') order by clauses_id";
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						}
					}
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);
					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				}

				exWarCla.put("status", "RESULTS");

			} else {

				exWarCla.put("status", "NO RESULTS");

			}

		} catch (Exception ex) 
		  {
			System.out.println("the WERT Exception occured in GET getExclusionWarrantyClauses IS "+ ex.toString());
		}
		return exWarCla;
	}


	public void updatePDFWarrantiesStatus(String policyNo, String loginCode,
			String PDF_MODIFY_CLAUSE, String PDF_MODIFY_WARRANTY,
			String PDF_MODIFY_EXCLUSION, String PDF_MODIFY_EXTRACLAUSES,
			String clickStatus, String exisCoverId, String exisTransportId,
			String exisExCoverId, String policyBackId, String PolicyBackDesc) 
	{
		String pdfStatus = "";
		String sql = "";
		if(PDF_MODIFY_CLAUSE!=null)
			PDF_MODIFY_CLAUSE = PDF_MODIFY_CLAUSE.replaceAll("'","''");
		if(PDF_MODIFY_WARRANTY!=null)
			PDF_MODIFY_WARRANTY = PDF_MODIFY_WARRANTY.replaceAll("'","''");
		if(PDF_MODIFY_EXCLUSION!=null)
			PDF_MODIFY_EXCLUSION = PDF_MODIFY_EXCLUSION.replaceAll("'","''");
		if(PDF_MODIFY_EXTRACLAUSES!=null)
			PDF_MODIFY_EXTRACLAUSES = PDF_MODIFY_EXTRACLAUSES.replaceAll("'","''");
		if(PolicyBackDesc!=null)
			PolicyBackDesc = PolicyBackDesc.replaceAll("'","''");
		
		try 
		{
			if ("policy".equalsIgnoreCase(clickStatus)) 
			{

				
				sql = "update position_master set  PDF_MODIFY_CLAUSE='"
						+ PDF_MODIFY_CLAUSE + "',PDF_MODIFY_WARRANTY='"
						+ PDF_MODIFY_WARRANTY + "',PDF_MODIFY_EXCLUSION='"
						+ PDF_MODIFY_EXCLUSION + "',PDF_MODIFY_EXTRACLAUSES='"
						+ PDF_MODIFY_EXTRACLAUSES + "',PDF_COVER_ID='"
						+ exisCoverId + "',PDF_MODE_TRANSPORT_ID='"
						+ exisTransportId + "',PDF_EXTRA_COVER_ID='"
						+ exisExCoverId + "',pdf_backdate_id='" + policyBackId
						+ "',pdf_modify_backdate='" + PolicyBackDesc
						+ "' where  policy_no='" + policyNo + "'";

			} 
			else 
			{
				sql = "update position_master set  PDF_MODIFY_CLAUSE='"
						+ PDF_MODIFY_CLAUSE + "',PDF_MODIFY_WARRANTY='"
						+ PDF_MODIFY_WARRANTY + "',PDF_MODIFY_EXCLUSION='"
						+ PDF_MODIFY_EXCLUSION + "',PDF_MODIFY_EXTRACLAUSES='"
						+ PDF_MODIFY_EXTRACLAUSES + "',PDF_COVER_ID='"
						+ exisCoverId + "',PDF_MODE_TRANSPORT_ID='"
						+ exisTransportId + "',PDF_EXTRA_COVER_ID='"
						+ exisExCoverId + "',pdf_backdate_id='" + policyBackId
						+ "',pdf_modify_backdate='" + PolicyBackDesc
						+ "' where  quote_no='" + policyNo + "' ";

			}
			pdfStatus = runner.updation(sql);

			sql = "";

		} catch (Exception updateEX) {

			System.out.println("the Exception is update PDFWarrantiesStatus "
					+ updateEX.toString());

		}
	}

	public String[][] makeTwoDimArray(String combined, String firstLimiter,String secondLimiter) 
	{

		int count = 0;
		int jcount = 0;
		int arrCount = 0;
		int arrJCount = 0;

		StringTokenizer st1 = new StringTokenizer(combined, secondLimiter);

		String[][] convertedArray = new String[st1.countTokens()][3];
		// String[][] convertedArray=new String[st1.countTokens()][100];

		while (st1.hasMoreTokens())
		{
			String w1 = st1.nextToken();
			StringTokenizer st2 = new StringTokenizer(w1, firstLimiter);
			while (st2.hasMoreTokens()) 
			{

				String w2 = st2.nextToken();

				convertedArray[count][jcount] = w2;

				jcount = jcount + 1;
			}
			jcount = 0;
			count = count + 1;

		}

		return convertedArray;
	}

	public String[][] makeTwoDimArray(String combined) 
	{
		int count = 0;
		int jcount = 0;
		int arrCount = 0;
		int arrJCount = 0;
		StringTokenizer st1 = new StringTokenizer(combined, "#");
		String[][] convertedArray = new String[st1.countTokens()][3];
		while (st1.hasMoreTokens())
		{
			String w1 = st1.nextToken();
			StringTokenizer st2 = new StringTokenizer(w1, "~");
			while (st2.hasMoreTokens()) 
			{
				String w2 = st2.nextToken();
				convertedArray[count][jcount] = w2;
				jcount = jcount + 1;
			}
			jcount = 0;
			count = count + 1;
		}
		return convertedArray;
	}
	public String getAdminRemarksPosition(String quoteNo) 
	{
		String app = "";
		String sql = "select nvl(admin_remarks,'NIL') from position_master where quote_no=?";
		try
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			app = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return app;
	}
	public String getRemarksPosition(String quoteNo) 
	{
		String app = "";
		String sql = "select nvl(remarks,'NIL') from position_master where quote_no=?";
		try
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			app = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return app;
	}
	public String[][] getBackDatedStatus(String quoteNo) 
	{

		String[][] app = new String[0][0];
		String sql = "SELECT TRUNC(TO_NUMBER(SUBSTR((md.entry_date+9/24-md.inception_date),1,INSTR(md.entry_date+9/24-md.inception_date,' ')))),TRUNC(TO_NUMBER(SUBSTR((md.inception_date-mpd.BL_AWB_DATE),1,INSTR(md.inception_date-mpd.BL_AWB_DATE,' ')))),md.mode_of_transport,md.cover_id,md.sea_options FROM marine_data md,marine_policy_details mpd,position_master pmm where md.application_no=(select pm.application_no from position_master pm where pm.quote_no=?) and mpd.quote_no=pmm.quote_no and md.application_no=pmm.application_no";
		try
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			app = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if (app.length <= 0) 
		{
			
			sql = "SELECT TRUNC(TO_NUMBER(SUBSTR((md.entry_date+9/24-md.inception_date),1,INSTR(md.entry_date+9/24-md.inception_date,' ')))),TRUNC(TO_NUMBER(SUBSTR((md.entry_date+9/24-md.inception_date),1,INSTR(md.entry_date+9/24-md.inception_date,' ')))) FROM marine_data md,position_master pmm where md.application_no=(select pm.application_no from position_master pm where pm.quote_no=?)  and md.application_no=pmm.application_no";

			try
			{
				String args[] = new String[1];
				args[0] = quoteNo;
				app = runner.multipleSelection(sql,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if (app.length > 0) 
		{
			return app;
		} 
		else 
		{
			//return new String[0][0];
			
			return app;
		}

	}
	public String getBackDatedStatusOpencover(String openCoverNo) 
	{

		String app = "";
		String sql = "select ocm.back_date_days from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
		try
		{
			String args[] = new String[2];
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			app = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return app;

	}

	public String getBrokerRemarksStatus(String login) 
	{
		try 
		{
			String args[] = new String[1];
			args[0] = login;
			return runner.singleSelection("select referal from login_master where agency_code=(select oa_code from login_master where login_id=?) and usertype='Broker'",args);
		} 
		catch (Exception e) 
		{
			return "N";
		}
	}

	public String getPolicyBackStatus(String quote_no) 
	{
		String remarks = "0";
		try 
		{
			String args[] = new String[1];
			args[0] = quote_no;
			remarks = runner.singleSelection("select count(1) from marine_data where application_no in (select application_no from position_master where quote_no=?) and remarks like '%Policy Date Referal%'",args);
			if (Integer.parseInt(remarks) == 0)
				remarks = "0";
			else
				remarks = "1";
		} catch (Exception e) {
			remarks = "0";
			System.out.println("  ERROR IN POLICYBACKSTATUS  " + e.toString());e.printStackTrace();
		}
		return remarks;

	}
	public String getPolicysFreshBackDesc(String branch) 
	{
		String res = "";
		try
		{
			String args[] = new String[1];
			args[0] = branch;
			res = runner.singleSelection("select nvl(WARRANTY_DESCRIPTION,' ') from WARRANTY_MASTER where WARRANTY_ID='999' and BRANCH_CODE=?",args);
			res = res.trim();
		}
		catch (Exception e)
		{
			System.out.println("exception in policy info..."+e.toString());e.printStackTrace();
		}
		return res;
	}
	public String getPolicysBackDesc(String quote_no) 
	{
		String res = "";
		String args[] = new String[1];
		args[0] = quote_no;
		String result[][] = new String[0][0];
		try
		{
			result =  runner.multipleSelection("select nvl(pdf_modify_backdate,'NOTHING'),nvl(PDF_BACKDATE_ID,'0') from position_master where quote_no=?",args);
			if(result.length>0)
			{
				res = result[0][0];
				res = res.equalsIgnoreCase("null")?"":res;
				if(result[0][1].equalsIgnoreCase("1000")&&res.equalsIgnoreCase("NOTHING"))
					res = "Admin";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public String[][] getOpenCoverMasterDatas(String openCoverNoLocal) {
		String[][] getOpenCoverMasterDatas = null;
		String getOpenCoverMasterDatas_Query = "";

		try 
		{
			getOpenCoverMasterDatas_Query = "select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";

			String args[] = new String[2];
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;

			getOpenCoverMasterDatas = runner.multipleSelection(getOpenCoverMasterDatas_Query,args);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return getOpenCoverMasterDatas;

	}

	public String[][] getOpenCoverDetailDatas(String conveyanceId,String openCoverNoLocal) 
	{
		String[][] getOpenCoverDetailDatas = null;
		String getOpenCoverDetailDatas_Query = "";

		try {

			getOpenCoverDetailDatas_Query = "select  ocd.sum_insured_limit,ocd.mode_transport_id,ocd.exchange_id,ocd.currency_id,ocd.exchange_rate,ocd.currency_name,ocd.amend_id from open_cover_detail ocd,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocd.proposal_no and ocd.amend_id in (select max(ocds.amend_id) from open_cover_detail ocds,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocds.proposal_no and to_date(ocds.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) and ocd.mode_transport_id=?";

			String args[] = new String[3];
			args[0] = openCoverNoLocal;
			args[1] = openCoverNoLocal;
			args[2] = conveyanceId;
		
			getOpenCoverDetailDatas = runner.multipleSelection(getOpenCoverDetailDatas_Query,args);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return getOpenCoverDetailDatas;

	}

	public String getOpenCoverNo(String quoteNo) 
	{
		String opencover = null;
		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			opencover = runner.singleSelection("select distinct(open_cover_no) from marine_data where application_no=?",args);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return opencover;

	}
	// For Freight Forwarder
	public boolean getFreightQuoteStatus(String QuoteNo)
	{
		
		String res = "";
		try 
		{
			String args[] = new String[1];
			args[0] = QuoteNo;
			res = runner.singleSelection("select FREIGHT_STATUS from POSITION_MASTER where QUOTE_NO=?",args);
			if (res != null) 
			{
				if (res.equalsIgnoreCase("F"))
					return true;
				else
					return false;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean getSettlingPackageDetails(String QuoteNo) 
	{
		String res[][] = new String[0][0];
		int PacakgeFlag = 0;
		int SettlingFlag = 0;
		try 
		{
			String args[] = new String[2];
			args[0] = QuoteNo;
			args[1] = QuoteNo;
			res = runner
					.multipleSelection("select nvl(mrd.PACKAGE_DESCRIPTION,0),nvl(mpd.SETTLING_AGENT_ID,0),nvl(mpd.SETTLING_AGENT_NAME,0) from MARINE_RESULT_DETAILS mrd, MARINE_POLICY_DETAILS  mpd, position_master pos where mrd.APPLICATION_NO=(select APPLICATION_NO from position_master where quote_no=?) and mrd.APPLICATION_NO=pos.APPLICATION_NO and pos.quote_no=mpd.quote_no and pos.quote_no=?",args);

			for (int i = 0; i < res.length; i++) 
			{
				if (res[i][0].equalsIgnoreCase("0")) 
				{
					PacakgeFlag = 1;
				}
			}
			if (res.length > 0) 
			{
				if (res[0][1].equalsIgnoreCase("0")) {
					if (res[0][2].equalsIgnoreCase("0"))
						SettlingFlag = 1;
				}
			} else
				SettlingFlag = 1;

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		if (SettlingFlag == 0 && PacakgeFlag == 0)
			return true;
		else
			return false;
	}

	public String getFreightBroker(String user)
	{
		
		String res = "";
		try 
		{
			String args[] = new String[1];
			args[0] = user;
			res = runner.singleSelection("select nvl(company_name,' ') from BROKER_COMPANY_MASTER where AGENCY_CODE=(select OA_CODE from LOGIN_MASTER where login_id=?)",args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}

	public HashMap getQuoteInfo(String qnos) 
	{
		
		String res[][] = new String[0][0];
		HashMap result = new HashMap();
		try 
		{
			res = runner.multipleSelection("select nvl(mrd.PACKAGE_DESCRIPTION,0),nvl(mpd.SETTLING_AGENT_ID,0),nvl(mpd.SETTLING_AGENT_NAME,0),nvl(pos.quote_no,0),nvl(mr.PREMIUM,0) from MARINE_RESULT_DETAILS mrd, MARINE_POLICY_DETAILS  mpd, position_master pos, MARINE_RESULT mr where mrd.APPLICATION_NO in(select APPLICATION_NO from position_master where quote_no in ("+ qnos	+ ")) and mrd.APPLICATION_NO=pos.APPLICATION_NO and pos.quote_no=mpd.quote_no and pos.quote_no in("+ qnos + ") and mr.quote_no=pos.quote_no");
			StringTokenizer st = new StringTokenizer(qnos, ",");
			String quote = "";
			while (st.hasMoreTokens()) 
			{
				quote = st.nextToken();
				int flag = 0;
				for (int i = 0; i < res.length; i++) 
				{
					if (res[i][3].equalsIgnoreCase(quote)){
						flag = 1;
						if (res[i][4].equalsIgnoreCase("0"))
							flag = 0;
					}
				}
				if (flag == 0)
					result.put("q" + quote, "Missing");
				else if (flag == 1)
					result.put("q" + quote, "Completed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String getFreightScheduleOption(String qno)
	{
		String res = "";
		try 
		{
			String args[] = new String[1];
			args[0] = qno;
			res = runner.singleSelection("select nvl(ALLOW_TO_GENERATE_POLICY_STS,' ') from FREIGHT_POSITION_MASTER where QUOTE_NO=? and status='A'",args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	public String getDirectScheduleProvision(String loginId)
	{
		
		String res = "";
		try 
		{
			String args[] = new String[1];
			args[0] = loginId;
			res = runner.singleSelection("select nvl(FREIGHT_SCHEDULE_OPTION,' ') from login_user_details where login_id=? and product_id='3'",args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return res;
	}
	public String[][] getDirectScheduleDebitProvision(String loginId)
	{
		
		String result[][] = new String[0][0];
		try 
		{
			String args[] = new String[1];
			args[0] = loginId;
			result = runner.multipleSelection("select nvl(FREIGHT_SCHEDULE_OPTION,' '),nvl(FREIGHT_DEBIT_OPTION,' ') from login_user_details where login_id=? and product_id='3'",args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public String getDirectDebitProvision(String loginId)
	{
		
		String result="";
		try 
		{
			String args[] = new String[1];
			args[0] = loginId;
			result = runner.singleSelection("select nvl(FREIGHT_DEBIT_OPTION,'N') from login_user_details where login_id=? and product_id='3'",args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	public String[][] getCustomerRights(String openCoverNo)
	{
		String results[][] = new String[0][0];
		
		String sql = "select CUSTOMER_SCHEDULE,CUSTOMER_DEBIT,CUSTOMER_CUSTOMERDEBIT from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=?";
		try 
		{
			String args[] = new String[1];
			args[0] = openCoverNo;
			results = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return results;
	}
	public void updateRubberStmp(String qno,String stampStatus)
	{
		String sql = "update POSITION_MASTER set PDF_Stamp_Status=? where quote_no=?";
		try 
		{
			stampStatus = stampStatus == null?"N":stampStatus;
			if(stampStatus.equalsIgnoreCase("Undefined")||stampStatus.equalsIgnoreCase("null")||stampStatus.equalsIgnoreCase(" ")||stampStatus.length()<=0)
				stampStatus ="N";
			String args[] = new String[2];
			args[0] = stampStatus;
			args[1] = qno;
			runner.multipleUpdation(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public void updateDebitCommissionOption(String qno,String commissionOption) //Debit Commission
	{
		String sql = "update POSITION_MASTER set PDF_COMMISSION_STATUS=? where quote_no=?";
		try 
		{
			commissionOption = commissionOption == null?"N":commissionOption;
			if(commissionOption.equalsIgnoreCase("Undefined")||commissionOption.equalsIgnoreCase("null")||commissionOption.equalsIgnoreCase(" ")||commissionOption.length()<=0)
				commissionOption ="N";
			String args[] = new String[2];
			args[0] = commissionOption;
			args[1] = qno;
			runner.multipleUpdation(sql,args);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	//Rajesh World work Started
	public String[][] getAgentList(String quoteNo,String cid) 
	{
		String[][] agents = new String[0][0];
		/*try 
		{
			String qry = "select distinct sam.settling_agent_id, sam.settling_agent_name,cm.city_name,count(sam.settling_agent_id) from settling_agent_master sam,city_master cm where sam.COUNTRY_ID=(select  FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA where application_no=(select application_no from position_master where quote_no=?)) and cm.country_id=(select  FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA where application_no=(select application_no from position_master where quote_no=?)) and  cm.country_id=sam.country_id  and cm.city_id=sam.city_id and cm.status='Y' and sam.status='Y' and sam.BELONGING_COUNTRY_ID=? group by sam.settling_agent_id, sam.settling_agent_name,cm.city_name order by cm.city_name";
			String args[] = new String[3];
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = cid;
			agents = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());
			e.printStackTrace();
		}*/
		
		try{
			String qry = "select distinct sam.settling_agent_id, sam.settling_agent_name,sam.SHORT_NAME,count(sam.settling_agent_id) from settling_agent_master sam where sam.COUNTRY_ID=(select FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA md,position_master pos where md.application_no=pos.application_no and pos.quote_no=?) and sam.status='Y' and sam.BELONGING_COUNTRY_ID=? group by sam.settling_agent_id, sam.settling_agent_name,sam.SHORT_NAME order by sam.settling_agent_name";
			String args[] = new String[2];
			args[0] = quoteNo;
			args[1] = cid;
			agents = runner.multipleSelection(qry,args);
		}catch(Exception e){
			System.out.println("Get Settling agent in policy info .."+e.toString());
			e.printStackTrace();
		}
		return agents;
	}
	public String[][] getPackList(String quoteNo,String cid) 
	{
		String[][] agents = new String[0][0];
		/*try 
		{
			String qry = "select distinct sam.settling_agent_id, sam.settling_agent_name,cm.city_name,count(sam.settling_agent_id) from settling_agent_master sam,city_master cm where sam.COUNTRY_ID=(select  FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA where application_no=(select application_no from position_master where quote_no=?)) and cm.country_id=(select  FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA where application_no=(select application_no from position_master where quote_no=?)) and  cm.country_id=sam.country_id  and cm.city_id=sam.city_id and cm.status='Y' and sam.status='Y' and sam.BELONGING_COUNTRY_ID=? group by sam.settling_agent_id, sam.settling_agent_name,cm.city_name order by cm.city_name";
			String args[] = new String[3];
			args[0] = quoteNo;
			args[1] = quoteNo;
			args[2] = cid;
			agents = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());
			e.printStackTrace();
		}*/
		
		try{
			String qry = "select distinct sam.settling_agent_id, sam.settling_agent_name,sam.SHORT_NAME,count(sam.settling_agent_id) from settling_agent_master sam where sam.COUNTRY_ID=(select FINAL_DESTINATION_COUNTRY_ID from MARINE_DATA md,position_master pos where md.application_no=pos.application_no and pos.quote_no=?) and sam.status='Y' and sam.BELONGING_COUNTRY_ID=? group by sam.settling_agent_id, sam.settling_agent_name,sam.SHORT_NAME order by sam.settling_agent_name";
			String args[] = new String[2];
			args[0] = quoteNo;
			args[1] = cid;
			agents = runner.multipleSelection(qry,args);
		}catch(Exception e){
			System.out.println("Get Settling agent in policy info .."+e.toString());
			e.printStackTrace();
		}
		return agents;
	}
	public String[][] getCommodity(String app_no,String loginBra) 
	{
		String[][] commodities = new String[0][0];
		try 
		{
//			String qry = "select a.commodity_id,a.sum_insured,com.commodity_name,nvl(a.supplier_name,' '),nvl(a.package_description,' '),nvl(a.invoice_number,' '),a.description from marine_result_details a,commoditymaster com where a.application_no=? and com.commodity_id=a.commodity_id and BRANCH_CODE=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BRANCH_CODE=? group by commodity_id)";
			String qry = "SELECT  A.COMMODITY_ID, A.SUM_INSURED, COM.COMMODITY_NAME,NVL(A.SUPPLIER_NAME,' '), NVL(A.PACKAGE_DESCRIPTION,' '), NVL(A.INVOICE_NUMBER,' '),NVL(A.DESCRIPTION,' ') FROM MARINE_RESULT_DETAILS A, COMMODITYMASTER COM WHERE  A.APPLICATION_NO = ? AND COM.COMMODITY_ID = A.COMMODITY_ID AND COM.BRANCH_CODE = ? AND COM.AMEND_ID=(  SELECT   MAX (AMEND_ID) FROM   COMMODITYMASTER WHERE   TO_DATE (EFFECTIVE_DATE, 'dd-MM-YYYY') <= TO_DATE (SYSDATE, 'dd-MM-YYYY') AND STATUS IN ('Y', 'R') AND BRANCH_CODE =? AND COMMODITY_ID=COM.COMMODITY_ID) ORDER BY A.COMMODITY_ID";
			String args[] = new String[3];
			args[0] = app_no;
			args[1] = loginBra;
			args[2] = loginBra;
			commodities = runner.multipleSelection(qry,args);

		} catch (Exception e) {System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return commodities;
	}
	//This for broker side clausesEdit.jsp - opencover admin side also
	public HashMap getExclusionWarrantyClauses(String quoteNo,String loginCode, String editStatus, String productId,String openCoverNo, String loginBra) throws BaseException
	{
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";

		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();

		// /Newly Coding
		String pdfDisplayStatus[][] = new String[0][0];
		String pdfDisplayClauses[][] = new String[0][0];
		String pdfDisplayExtraClauses[][] = new String[0][0];
		String pdfDisplayWarranties[][] = new String[0][0];
		String pdfDisplayExclusions[][] = new String[0][0];

		String pdfClauses = "NOTHING";
		String pdfexClauses = "NOTHING";
		String pdfWars = "NOTHING";
		String pdfEx = "NOTHING";
		String concatClausesIds = "";
		String concatClasusesDesc = "";

		String concatExClausesIds = "";
		String concatExClasusesDesc = "";

		String concatExtraClausesIds = "";
		String concatExtraClasusesDesc = "";

		String concatWarClausesIds = "";
		String concatWarClasusesDesc = "";

		HashMap premiumdetails = new HashMap();

		String sqlCheck = "";
		String sqlUpdate = "";
		String sqlUpdateEx = "";

		String exisOldCoverId = "0";
		String exisOldExCoverId = "0";
		String exisOldTransportId = "0";

		String exisCoverId = "0";
		String exisExCoverId = "0";
		String exisTransportId = "0";

		String[][] existingModes = new String[0][0];
		String[][] dummy = new String[0][0];
		

		sqlCheck = "select PDF_MODE_TRANSPORT_ID,PDF_COVER_ID,PDF_EXTRA_COVER_ID,mode_of_transport,cover_id,extra_cover_id from marine_data md,position_master pm where  md.application_no=pm.application_no and pm.quote_no=?";

		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			existingModes = runner.multipleSelection(sqlCheck,args);

			if (existingModes.length > 0) {
				exisCoverId = existingModes[0][4] == null ? "0": existingModes[0][4];
				exisExCoverId = existingModes[0][5] == null ? "0": existingModes[0][5];
				exisTransportId = existingModes[0][3] == null ? "0": existingModes[0][3];
				exisOldCoverId = existingModes[0][1] == null ? ("0"): existingModes[0][1];
				exisOldExCoverId = existingModes[0][2] == null ? ("0"): existingModes[0][2];
				exisOldTransportId = existingModes[0][0] == null ? ("0"): existingModes[0][0];
			}
		} catch (Exception ex) 
			{
		}

		// Condition Checkiing Starts
		boolean warrantyClausesStatus=new CommodityCountryRateDAO().getWarrantyClausesStatus(loginCode, productId, quoteNo);
		if ((!warrantyClausesStatus) && (!(exisCoverId.equalsIgnoreCase(exisOldCoverId))|| !(exisTransportId.equalsIgnoreCase(exisOldTransportId)))) 
		{
			sqlUpdate = "update position_master set  PDF_MODIFY_CLAUSE='"+ "NOTHING" + "',PDF_MODIFY_WARRANTY='" + "NOTHING"+ "',PDF_MODIFY_EXCLUSION='" + "NOTHING"+ "',PDF_MODIFY_EXTRACLAUSES='" + "NOTHING"+ "' where  quote_no=?";
			String args[] = new String[1];
			args[0] = quoteNo;
			runner.multipleUpdation(sqlUpdate,args);
			sqlUpdate = "";
		}
		else if (!(exisExCoverId.equalsIgnoreCase(exisOldExCoverId)))
		{
			pdfexClauses = "NOTHING";
			sqlUpdateEx = "update position_master set PDF_MODIFY_EXTRACLAUSES='"+ "NOTHING" + "' where  quote_no=?";
			String args[] = new String[1];
			args[0] = quoteNo;
			runner.multipleUpdation(sqlUpdateEx,args);
			sqlUpdateEx = "";
		}

		pdfDisplayStatus = pdis.getPreBankOptions(quoteNo, loginCode, "draft");
		System.out.println("===========>3");

		if (pdfDisplayStatus.length > 0) {

			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses: pdfDisplayStatus[0][2];
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses: pdfDisplayStatus[0][5];
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars: pdfDisplayStatus[0][3];
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx: pdfDisplayStatus[0][4];

		} 
		
		if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
		{
			concatClausesIds = "0";
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		else 
		{
			pdfDisplayClauses = makeTwoDimArray(pdfClauses);
			for (int i = 0; i < pdfDisplayClauses.length; i++) 
			{
				concatClausesIds = concatClausesIds	+ ","+ (pdfDisplayClauses[i][0] == null ? "0": pdfDisplayClauses[i][0]);
			}

			concatClausesIds = concatClausesIds.substring(1, concatClausesIds.length());
			exWarCla.put("editedClauses", pdfDisplayClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
			concatExtraClausesIds = "0";
			premiumdetails.put("editedExtraClauses", pdfDisplayExtraClauses);
		} else {
			pdfDisplayExtraClauses = makeTwoDimArray(pdfexClauses);

			
			for (int i = 0; i < pdfDisplayExtraClauses.length; i++) {
				concatExtraClausesIds = concatExtraClausesIds
						+ ","
						+ (pdfDisplayExtraClauses[i][0] == null ? "0"
								: pdfDisplayExtraClauses[i][0]);
			}

			concatExtraClausesIds = concatExtraClausesIds.substring(1,
					concatExtraClausesIds.length());

			

			exWarCla.put("editedExtraClauses", pdfDisplayExtraClauses);

		}

		if ("NOTHING".equalsIgnoreCase(pdfWars)) 
		{
			concatWarClausesIds = "0";
			premiumdetails.put("editedWarClauses", pdfDisplayWarranties);
		} 
		else 
		{
			pdfDisplayWarranties = makeTwoDimArray(pdfWars);
			
			for (int i = 0; i < pdfDisplayWarranties.length; i++) {
				concatWarClausesIds = concatWarClausesIds + ","	+ (pdfDisplayWarranties[i][0] == null ? "0": pdfDisplayWarranties[i][0]);
			}
			concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
			
			exWarCla.put("editedWarClauses", pdfDisplayWarranties);
		}

		if("NOTHING".equalsIgnoreCase(pdfEx)) 
		{
			concatExClausesIds = "0";
			premiumdetails.put("editedExClauses", pdfDisplayExclusions);
			
		} else {
			pdfDisplayExclusions = makeTwoDimArray(pdfEx);
			
			for (int i = 0; i < pdfDisplayExclusions.length; i++) {
				
				concatExClausesIds = concatExClausesIds
						+ ","
						+ (pdfDisplayExclusions[i][0] == null ? "0"
								: pdfDisplayExclusions[i][0]);
				
			}
			concatExClausesIds = concatExClausesIds.substring(1,concatExClausesIds.length());


			exWarCla.put("editedExClauses", pdfDisplayExclusions);
		}

		

		
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items,md.mode_of_transport from commoditymaster com,marine_result_details mrd,position_master pm,marine_data md where pm.quote_no=? and com.commodity_id=mrd.commodity_id and pm.application_no=mrd.application_no and  md.application_no=mrd.application_no and com.BRANCH_CODE=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BRANCH_CODE=? group by commodity_id)";

		

		try 
		{
			
			String transPortId = "0";
			String coverIdCheck = "0";
			String args1[] = new String[3];
			args1[0] = quoteNo;
			args1[1] = loginBra;
			args1[2] = loginBra;
			str_exWar = runner.multipleSelection(commodityDetailsQry,args1);

			if (str_exWar.length > 0) {
				
				transPortId = str_exWar[0][6] == null ? "0" : str_exWar[0][6];
				coverIdCheck = str_exWar[0][3] == null ? "0" : str_exWar[0][3];

				for (int i = 0; i < str_exWar.length; i++) {
					
					if (("2".equalsIgnoreCase(transPortId) && "5"
							.equalsIgnoreCase(coverIdCheck))
							|| ("1".equalsIgnoreCase(transPortId) && ("2"
									.equalsIgnoreCase(coverIdCheck) || "3"
									.equalsIgnoreCase(coverIdCheck)))) {
						exId = exId + "," + "0";
					} else {
						exId = exId
								+ ","
								+ (str_exWar[i][1] == null ? "0"
										: str_exWar[i][1]);
					}

					if ("2".equalsIgnoreCase(transPortId)
							&& "5".equalsIgnoreCase(coverIdCheck)) {

						warId = warId + "," + "0";

					} else {
						warId = warId
								+ ","
								+ (str_exWar[i][2] == null ? "0"
										: str_exWar[i][2]);
					}
				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);
				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);
				exWarCla.put("transportId", transPortId);
				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				String coverDesc = "";
				clausesCoverQry = "select distinct description from cover_master where branch_code = " + loginBra + " and cover_id = " + coverId + "";
				coverDesc = runner.singleSelection(clausesCoverQry);

				if ("NOTHING".equalsIgnoreCase(pdfEx)) 
				{
					if ("11".equalsIgnoreCase(productId)) 
					{
						//exDetailsQry = "select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
						exDetailsQry = "SELECT  DISTINCT occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_exclusions occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM  WHERE ocpmss.open_cover_no = ?  AND CM.BRANCH_CODE=" + loginBra + " AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + " AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND ocpmss.proposal_no = occmss.proposal_no AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_exclusions WHERE   (proposal_no =ocpmss.proposal_no AND OCPMSS.OPEN_COVER_NO=? ) AND TO_DATE (effective_date,'dd-MM-YYYY') <=TO_DATE (SYSDATE,'dd-MM-YYYY'))";
						String args[] = new String[2];
						args[0] = openCoverNo;
						args[1] = openCoverNo;
						str_exDesc = runner.multipleSelection(exDetailsQry,args);
					} 
					else if ("3".equalsIgnoreCase(productId)) 
					{
						/*exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
								+ "("
								+ exId
								+ ")"
								+ " and status in ('Y','R') and BRANCH_CODE=? order by exclusion_id";
						String args[] = new String[1];
						args[0] = loginBra;
						str_exDesc = runner.multipleSelection(exDetailsQry,args);*/
						
						exDetailsQry = "SELECT DISTINCT EM.EXCLUSION_DESCRIPTION,EM.STATUS,EM.EXCLUSION_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,EXCLUSION_MASTER EM " +
						"WHERE EM.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + 
						coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + 
						coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
						"AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = (select application_no from " +
						"position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=EM.BRANCH_CODE ORDER BY EM.EXCLUSION_ID";
						str_exDesc = runner.multipleSelection(exDetailsQry);
					}
				}
				else 
				{
					if ("11".equalsIgnoreCase(productId)) 
					{
						//exDetailsQry = "select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =?  and occmss.exclusion_id not in("+ concatExClausesIds+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and (occms.exclusion_id not in("
							//	+ concatExClausesIds
								//+ ") and occms.amend_id in( select max(occmsss.amend_id) from open_cover_exclusions occmsss,open_cover_position_master ocpmsss where ocpmsss.open_cover_no =? and ocpmsss.proposal_no=occmsss.proposal_no and to_date(occmsss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')))  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
						exDetailsQry = "SELECT  DISTINCT occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_exclusions occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM  WHERE ocpmss.open_cover_no = ?  AND occmss.exclusion_id NOT IN ("+ concatExClausesIds+ ") AND CM.BRANCH_CODE=" + loginBra + " AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + " AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND ocpmss.proposal_no = occmss.proposal_no AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_exclusions WHERE   (proposal_no =ocpmss.proposal_no or ocpmss.open_cover_no=?) and TO_DATE (effective_date,'dd-MM-YYYY') <=TO_DATE (SYSDATE,'dd-MM-YYYY'))";
						String args[] = new String[3];
						args[0] = openCoverNo;
						args[1] = openCoverNo;
					  //args[2] = openCoverNo;
						str_exDesc = runner.multipleSelection(exDetailsQry,args);
					} 
					else if ("3".equalsIgnoreCase(productId)) 
					{
						exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
								+ "("
								+ exId
								+ ") and exclusion_id not in ("
								+ concatExClausesIds
								+ ") and status in ('Y','R') and BRANCH_CODE=? order by exclusion_id";
							String args[] = new String[1];
							args[0] = loginBra;
							str_exDesc = runner.multipleSelection(exDetailsQry,args);
					}
				}	
				exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				if ("NOTHING".equalsIgnoreCase(pdfWars)) 
				{
					if ("11".equalsIgnoreCase(productId)) 
					{
						//warDetailsQry = "select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no =?  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
						warDetailsQry = "SELECT DISTINCT  occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_warranties occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM WHERE ocpmss.open_cover_no =? AND occmss.warranty_id NOT IN (0) AND ocpmss.proposal_no = occmss.proposal_no AND CM.BRANCH_CODE = " + loginBra + " AND CM.COMMODITY_ID IN (SELECT   MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD, POSITION_MASTER PM WHERE PM.QUOTE_NO = " + quoteNo + " AND PM.APPLICATION_NO = MRD.APPLICATION_NO AND MRD.AMEND_ID =(SELECT MAX (AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MRD.APPLICATION_NO)) AND OCCMSS.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_warranties WHERE ocpmss.open_cover_no =? and proposal_no=ocpmss.proposal_no AND TO_DATE ( effective_date,'dd-MM-YYYY') <=TO_DATE ( SYSDATE,'dd-MM-YYYY'))";
						String args[] = new String[2];
						args[0] = openCoverNo;
						args[1] = openCoverNo;
						str_warDesc = runner.multipleSelection(warDetailsQry,args);
					}
					else if ("3".equalsIgnoreCase(productId)) 
					{
						/*warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
								+ "("
								+ warId
								+ ")"
								+ " and status in ('Y','R') and BRANCH_CODE=? order by warranty_id";
						String args[] = new String[1];
						args[0] = loginBra;
						str_warDesc = runner.multipleSelection(warDetailsQry,args);*/
						
						warDetailsQry = "SELECT DISTINCT WM.WARRANTY_DESCRIPTION,WM.STATUS,WM.WARRANTY_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,WARRANTY_MASTER WM " +
						"WHERE WM.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
						"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + 
						",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) " +
						"AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE " +
						"AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
						"(select application_no from position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=WM.BRANCH_CODE ORDER BY WM.WARRANTY_ID";
						str_warDesc = runner.multipleSelection(warDetailsQry);
					}

				}
				else 
				{
		
					if ("11".equalsIgnoreCase(productId)) 
					{						

						/*warDetailsQry = "select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.warranty_id not in("
								+ concatWarClausesIds
								+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and (occms.warranty_id not in("
								+ concatWarClausesIds
								+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) ) and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
						String args[] = new String[3];
						args[0] = openCoverNo;
						args[1] = openCoverNo;
						args[2] = openCoverNo;*/
						warDetailsQry = "SELECT DISTINCT  occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,TO_CHAR (occmss.EFFECTIVE_DATE, 'dd-mm-yyyy') AS effectDate FROM open_cover_warranties occmss, open_cover_position_master ocpmss,COMMODITYMASTER CM WHERE ocpmss.open_cover_no =? AND occmss.warranty_id NOT IN ("+concatWarClausesIds+") AND ocpmss.proposal_no = occmss.proposal_no AND CM.BRANCH_CODE = " + loginBra + " AND CM.COMMODITY_ID IN (SELECT   MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD, POSITION_MASTER PM WHERE PM.QUOTE_NO = " + quoteNo + " AND PM.APPLICATION_NO = MRD.APPLICATION_NO AND MRD.AMEND_ID =(SELECT MAX (AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MRD.APPLICATION_NO)) AND OCCMSS.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) AND occmss.amend_id =(SELECT MAX (amend_id) FROM open_cover_warranties WHERE ocpmss.open_cover_no =? and proposal_no=ocpmss.proposal_no AND TO_DATE ( effective_date,'dd-MM-YYYY') <=TO_DATE ( SYSDATE,'dd-MM-YYYY'))";
						str_warDesc = runner.multipleSelection(warDetailsQry,new String[]{openCoverNo,openCoverNo});
					} 
					else if ("3".equalsIgnoreCase(productId)) 
					{
						warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
								+ "("
								+ warId
								+ ") and warranty_id not in ("
								+ concatWarClausesIds
								+ ") and status in ('Y','R') and BRANCH_CODE=? order by warranty_id";
						String args[] = new String[1];
						args[0] = loginBra;
						str_warDesc = runner.multipleSelection(warDetailsQry,args);
					}
				}
				
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description
				if ("0".equalsIgnoreCase(extraCoverId)) //and extra_cover_id='0'
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses))
					{
						if ("11".equalsIgnoreCase(productId)) 
						{
						  //clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.status in ('Y') and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and occms.cover_id=? and occms.extra_cover_id='0' and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
							clausesCoverQry = "select DISTINCT occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' AND CM.BRANCH_CODE=" + loginBra + "  AND occmss.status IN ('Y') AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + "  AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = ( select max(amend_id) from open_cover_clauses where OCPMSS.OPEN_COVER_NO=? and cover_id=? and extra_cover_id='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[4];
							args[0] = openCoverNo;
							args[1] = coverId;
							args[2] = openCoverNo;
							args[3] = coverId;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
						} 
						else if ("3".equalsIgnoreCase(productId)) 
						{
							/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id='0' and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = coverId;
							args[1] = loginBra;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
							
							clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
							",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
							",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
							" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
							"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
							System.out.println("coverid0if " + clausesCoverQry);
						}
						
					} 
					else 
					{

						if ("11".equalsIgnoreCase(productId)) 
						{
							
						  //clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0'  and occmss.clauses_id not in("+ concatClausesIds+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and occms.cover_id=? and occmss.extra_cover_id='0' and (occms.clauses_id not in("+ concatClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
						    clausesCoverQry = "select DISTINCT occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.clauses_id not in("+ concatClausesIds+ ") AND CM.BRANCH_CODE=" + loginBra + "  AND occmss.status IN ('Y') AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + "  AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = ( select max(amend_id) from open_cover_clauses where OCPMSS.OPEN_COVER_NO=? and cover_id=? and extra_cover_id='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[6];
							args[0] = openCoverNo;
							args[1] = coverId;
							args[2] = openCoverNo;
							args[3] = coverId;
							/*args[4] = openCoverNo;
							args[5] = coverId;*/
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = coverId;
							args[1] = loginBra;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
							
							clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
							"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
							") AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
							" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
							"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
							System.out.println("coverid0else " + clausesCoverQry);
						}
						
					}
		
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);

					clausesCoverQry = "";

				} 
				else 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) {
						

						if ("11".equalsIgnoreCase(productId)) 
						{
							//clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.status in ('Y') and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and occms.cover_id=? and occms.extra_cover_id='0' and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							clausesCoverQry = "select DISTINCT occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=? and occmss.extra_cover_id='0' AND CM.BRANCH_CODE=" + loginBra + "  AND occmss.status IN ('Y') AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + "  AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = ( select max(amend_id) from open_cover_clauses where OCPMSS.OPEN_COVER_NO=? and cover_id=? and extra_cover_id='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[4];
							args[0] = openCoverNo;
							args[1] = coverId;
							args[2] = openCoverNo;
							args[3] = coverId;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
						} 
						else if ("3".equalsIgnoreCase(productId)) 
						{
							/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id='0'  and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = coverId;
							args[1] = loginBra;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
							
							clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
							",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
							",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
							" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
							"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
							System.out.println("coverid!0if " + clausesCoverQry);

						}
			
					} 
					else
					{

						if ("11".equalsIgnoreCase(productId)) {
							clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0' and occmss.clauses_id not in("+ concatClausesIds+ ") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and occms.cover_id=? and occmss.extra_cover_id='0' and (occms.clauses_id not in("+ concatClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and occmss.cover_id=? and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))  order by occmss.clauses_id";
							String args[] = new String[6];
							args[0] = openCoverNo;
							args[1] = coverId;
							args[2] = openCoverNo;
							args[3] = coverId;
							args[4] = openCoverNo;
							args[5] = coverId;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);
						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = coverId;
							args[1] = loginBra;
							str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
							
							clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
							"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
							",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
							") AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
							" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
							"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_coverDesc = runner.multipleSelection(clausesCoverQry);
							System.out.println("coverid!0else " + clausesCoverQry);
						}

						
					}

					exWarCla.put("clausesDesc", str_coverDesc);
					
					if ("NOTHING".equalsIgnoreCase(pdfexClauses))
					{
						if ("11".equalsIgnoreCase(productId))
						{	
							/*clausesExtraCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id=?) and occmss.status in ('Y') and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and  ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and (occms.cover_id in('"+coverId+"') and  occms.extra_cover_id=?) and occms.status in ('Y') and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[4];
							args[0] = openCoverNo;
							args[1] = extraCoverId;
							args[2] = openCoverNo;
							args[3] = extraCoverId;
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);*/
							
							clausesExtraCoverQry = "select DISTINCT occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=" + coverId + " and occmss.extra_cover_id!='0' and occmss.clauses_id not in(0) AND CM.BRANCH_CODE= " + loginBra + " AND occmss.status IN ('Y')  AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + "  AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = (select max(amend_id) from open_cover_clauses where cover_id=" + coverId + " and extra_cover_id!='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[1];
							args[0] = openCoverNo;
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);
							
						} 
						else if ("3".equalsIgnoreCase(productId)) 
						{
							
							/*clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where extra_cover_id=? and cover_id in('"+coverId+"') and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = extraCoverId;
							args[1] = loginBra;
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);*/
							
							clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
							",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
							"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
							+ ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
							+ ",'~',1,3))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID = " + extraCoverId + " AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
							" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
							"(select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
							System.out.println("coverid!=0 if " + clausesExtraCoverQry);

						}
					}
					else 
					{
						if ("11".equalsIgnoreCase(productId)) 
						{	
							/*clausesCoverQry = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id=?)  and occmss.clauses_id not in("+ concatExtraClausesIds+ ") and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no =?  and (occms.clauses_id not in("+ concatExtraClausesIds+ ") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id=?) and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))  order by occmss.clauses_id";
							String args[] = new String[5];
							args[0] = openCoverNo;
							args[1] = extraCoverId;
							args[2] = openCoverNo;
							args[3] = openCoverNo;
							args[4] = extraCoverId;
							str_extraCoverDesc = runner.multipleSelection(clausesCoverQry,args);*/
							
							clausesCoverQry="select DISTINCT occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss,COMMODITYMASTER CM,CLAUSES_MASTER CLM where ocpmss.open_cover_no=? and occmss.cover_id=" + coverId + "  and occmss.clauses_id not in("+ concatExtraClausesIds+ ")  and occmss.extra_cover_id!='0' and occmss.clauses_id not in(0) AND CM.BRANCH_CODE= " + loginBra + " AND occmss.status IN ('Y')  AND CM.COMMODITY_ID IN (SELECT MRD.COMMODITY_ID FROM MARINE_RESULT_DETAILS MRD,POSITION_MASTER PM WHERE PM.QUOTE_NO=" + quoteNo + "  AND PM.APPLICATION_NO=MRD.APPLICATION_NO AND MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO)) AND OCCMSS.CLAUSES_ID=CLM.CLAUSES_ID AND CLM.BRANCH_CODE=CM.BRANCH_CODE AND CLM.COVER_ID=OCCMSS.COVER_ID AND CLM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + " ,INSTR(" + coverDesc + " ,'~',1,3)+1,(INSTR(" + coverDesc + " ,'~',1,4)-1)-INSTR(" + coverDesc + " ,'~',1,3))), ',', ''))+ 1) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id = (select max(amend_id) from open_cover_clauses where cover_id=" + coverId + " and extra_cover_id!='0' and proposal_no=ocpmss.proposal_no and to_date(effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) order by occmss.clauses_id";
							String args[] = new String[1];
							args[0] = openCoverNo;
							str_extraCoverDesc = runner.multipleSelection(clausesCoverQry,args);
						}
						else if ("3".equalsIgnoreCase(productId)) 
						{
							/*clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id=?) and clauses_id not in("+ concatExtraClausesIds+ ") and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
							String args[] = new String[2];
							args[0] = extraCoverId;
							args[1] = loginBra;
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);*/
							clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
							"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
							",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
							"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
							coverDesc + ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
							coverDesc + ",'~',1,3))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN ( " + concatExtraClausesIds + " ) AND CM.EXTRA_COVER_ID = " + extraCoverId + "  AND CM.STATUS IN ('Y','R') " +
							"AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no " +
							"= (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
							str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
							System.out.println("coverid!=0 else " + clausesExtraCoverQry);
						}
					}
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);
					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				}

				exWarCla.put("status", "RESULTS");

			} else {

				exWarCla.put("status", "NO RESULTS");

			}

		} catch (Exception ex) 
		  {
			System.out.println("the WERT Exception occured in GET  IS "+ ex.toString());
		}
		return exWarCla;
	}
	//This for admin sdie clauseadminedit.jsp for one off policy
	public HashMap getExclusionWarrantyClauses(String quoteNo,	String loginCode, String cid,String loginBra) throws BaseException 
	{
		String commodityDetailsQry = "";
		String exDetailsQry = "";
		String warDetailsQry = "";
		String clausesCoverQry = "";
		String clausesExtraCoverQry = "";
		String exId = "";
		String warId = "";
		String coverId = "";
		String extraCoverId = "";

		HashMap exWarCla = new HashMap();
		String[][] str_exWar = new String[0][0];

		String[][] str_exDesc = new String[0][0];
		String[][] str_warDesc = new String[0][0];
		String[][] str_coverDesc = new String[0][0];
		String[][] str_extraCoverDesc = new String[0][0];

		rsa.pdf.PDFDisplay pdis = new rsa.pdf.PDFDisplay();

		// /Newly Coding
		String pdfDisplayStatus[][] = new String[0][0];
		String pdfDisplayClauses[][] = new String[0][0];
		String pdfDisplayExtraClauses[][] = new String[0][0];
		String pdfDisplayWarranties[][] = new String[0][0];
		String pdfDisplayExclusions[][] = new String[0][0];

		String pdfClauses = "NOTHING";
		String pdfexClauses = "NOTHING";
		String pdfWars = "NOTHING";
		String pdfEx = "NOTHING";
		String concatClausesIds = "";
		String concatClasusesDesc = "";

		String concatExClausesIds = "";
		String concatExClasusesDesc = "";

		String concatExtraClausesIds = "";
		String concatExtraClasusesDesc = "";

		String concatWarClausesIds = "";
		String concatWarClasusesDesc = "";

		HashMap premiumdetails = new HashMap();

		String sqlCheck = "";
		String sqlUpdate = "";
		String sqlUpdateEx = "";

		String exisOldCoverId = "0";
		String exisOldExCoverId = "0";
		String exisOldTransportId = "0";

		String exisCoverId = "0";
		String exisExCoverId = "0";
		String exisTransportId = "0";
		String[][] dummy = new String[0][0];
		String[][] existingModes = new String[0][0];

		
		sqlCheck = "select PDF_MODE_TRANSPORT_ID,PDF_COVER_ID,PDF_EXTRA_COVER_ID,mode_of_transport,cover_id,extra_cover_id from marine_data md,position_master pm where  md.application_no=pm.application_no and pm.quote_no=?";
		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			existingModes = runner.multipleSelection(sqlCheck,args);
			if (existingModes.length > 0) 
			{
				exisCoverId = existingModes[0][4] == null ? "0": existingModes[0][4];
				exisExCoverId = existingModes[0][5] == null ? "0": existingModes[0][5];
				exisTransportId = existingModes[0][3] == null ? "0": existingModes[0][3];
				exisOldCoverId = existingModes[0][1] == null ? ("0"): existingModes[0][1];
				exisOldExCoverId = existingModes[0][2] == null ? ("0"): existingModes[0][2];
				exisOldTransportId = existingModes[0][0] == null ? ("0"): existingModes[0][0];
			}
		} 
		catch (Exception e) 
		{
			System.out.println(".....RoyalTest Exception first block in Cluses policyInfo.java..."+e.toString());
			e.printStackTrace();
		}

		// Condition Checkiing Starts
		String productId=runner.singleSelection("SELECT PRODUCT_ID FROM POSITION_MASTER WHERE QUOTE_NO='"+quoteNo+"'");
		boolean warrantyClausesStatus=new CommodityCountryRateDAO().getWarrantyClausesStatus(loginCode, productId, quoteNo);
		
		if (!warrantyClausesStatus && (!(exisCoverId.equalsIgnoreCase(exisOldCoverId))	|| !(exisTransportId.equalsIgnoreCase(exisOldTransportId)))) 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			sqlUpdate = "update position_master set  PDF_MODIFY_CLAUSE='"
					+ "NOTHING" + "',PDF_MODIFY_WARRANTY='" + "NOTHING"
					+ "',PDF_MODIFY_EXCLUSION='" + "NOTHING"
					+ "',PDF_MODIFY_EXTRACLAUSES='" + "NOTHING"
					+ "' where  quote_no=?";
			runner.multipleUpdation(sqlUpdate,args);

			sqlUpdate = "";

		} 
		else if (!(exisExCoverId.equalsIgnoreCase(exisOldExCoverId))) 
		{
			pdfexClauses = "NOTHING";

			sqlUpdateEx = "update position_master set PDF_MODIFY_EXTRACLAUSES='"
					+ "NOTHING" + "' where  quote_no=?";
			String args[] = new String[1];
			args[0] = quoteNo;
			runner.multipleUpdation(sqlUpdateEx,args);
			sqlUpdateEx = "";

		}

		pdfDisplayStatus = pdis.getPreBankOptions(quoteNo, loginCode, "draft");
		System.out.println("===========>4");

		if (pdfDisplayStatus.length > 0) {

			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses: pdfDisplayStatus[0][2];
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses: pdfDisplayStatus[0][5];
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars: pdfDisplayStatus[0][3];
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx: pdfDisplayStatus[0][4];
		}
		if ("NOTHING".equalsIgnoreCase(pdfClauses)) {
			concatClausesIds = "0";
			exWarCla.put("editedClauses", pdfDisplayClauses);
		} else {
			pdfDisplayClauses = makeTwoDimArray(pdfClauses);
			
			for (int i = 0; i < pdfDisplayClauses.length; i++) {
				concatClausesIds = concatClausesIds	+ ","+ (pdfDisplayClauses[i][0] == null ? "0"
								: pdfDisplayClauses[i][0]);
			}
			concatClausesIds = concatClausesIds.substring(1, concatClausesIds.length());
			exWarCla.put("editedClauses", pdfDisplayClauses);
		}
		if ("NOTHING".equalsIgnoreCase(pdfexClauses)) {
			concatExtraClausesIds = "0";
			premiumdetails.put("editedExtraClauses", pdfDisplayExtraClauses);
		} else {
			pdfDisplayExtraClauses = makeTwoDimArray(pdfexClauses);
			for (int i = 0; i < pdfDisplayExtraClauses.length; i++) {
				concatExtraClausesIds = concatExtraClausesIds
						+ ","
						+ (pdfDisplayExtraClauses[i][0] == null ? "0"
								: pdfDisplayExtraClauses[i][0]);
			}
			concatExtraClausesIds = concatExtraClausesIds.substring(1,concatExtraClausesIds.length());
			exWarCla.put("editedExtraClauses", pdfDisplayExtraClauses);
		}
		if ("NOTHING".equalsIgnoreCase(pdfWars)) {
			concatWarClausesIds = "0";
			premiumdetails.put("editedWarClauses", pdfDisplayWarranties);
		} else {
			pdfDisplayWarranties = makeTwoDimArray(pdfWars);
			for (int i = 0; i < pdfDisplayWarranties.length; i++) {
				concatWarClausesIds = concatWarClausesIds
						+ ","
						+ (pdfDisplayWarranties[i][0] == null ? "0"
								: pdfDisplayWarranties[i][0]);
			}
			concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
			exWarCla.put("editedWarClauses", pdfDisplayWarranties);

		}

		if ("NOTHING".equalsIgnoreCase(pdfEx)) {
			concatExClausesIds = "0";
			premiumdetails.put("editedExClauses", pdfDisplayExclusions);
			
		} else {
			pdfDisplayExclusions = makeTwoDimArray(pdfEx);
			for (int i = 0; i < pdfDisplayExclusions.length; i++) 
			{
				concatExClausesIds = concatExClausesIds	+ ","+ (pdfDisplayExclusions[i][0] == null ? "0": pdfDisplayExclusions[i][0]);
			}
			concatExClausesIds = concatExClausesIds.substring(1,concatExClausesIds.length());
			exWarCla.put("editedExClauses", pdfDisplayExclusions);
		}
		commodityDetailsQry = "select com.commodity_id,com.exclusion_id,com.warranty_id,md.cover_id,md.extra_cover_id,md.no_of_items,md.mode_of_transport from commoditymaster com,marine_result_details mrd,position_master pm,marine_data md where pm.quote_no=? and com.commodity_id=mrd.commodity_id and pm.application_no=mrd.application_no and  md.application_no=mrd.application_no and com.BRANCH_CODE=? and com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and BRANCH_CODE=? group by commodity_id)";

		try
		{
			String args1[] = new String[3];
			args1[0] = quoteNo;
			args1[1] = loginBra;
			args1[2] = loginBra;
			
			String transPortId = "0";
			String coverIdCheck = "0";
			str_exWar = runner.multipleSelection(commodityDetailsQry,args1);

			if (str_exWar.length > 0) 
			{
				
				transPortId = str_exWar[0][6] == null ? "0" : str_exWar[0][6];
				coverIdCheck = str_exWar[0][3] == null ? "0" : str_exWar[0][3];

				for (int i = 0; i < str_exWar.length; i++) {
					
					if (("2".equalsIgnoreCase(transPortId) && "5"
							.equalsIgnoreCase(coverIdCheck))
							|| ("1".equalsIgnoreCase(transPortId) && ("2"
									.equalsIgnoreCase(coverIdCheck) || "3"
									.equalsIgnoreCase(coverIdCheck)))) {
						exId = exId + "," + "0";
					} else {
						exId = exId
								+ ","
								+ (str_exWar[i][1] == null ? "0"
										: str_exWar[i][1]);
					}

					
					if ("2".equalsIgnoreCase(transPortId)
							&& "5".equalsIgnoreCase(coverIdCheck)) {

						warId = warId + "," + "0";

					} else {
						warId = warId+ ","	+ (str_exWar[i][2] == null ? "0": str_exWar[i][2]);
					}
				}

				coverId = (str_exWar[0][3] == null ? "0" : str_exWar[0][3]);
				extraCoverId = (str_exWar[0][4] == null ? "0" : str_exWar[0][4]);

				exId = exId.substring(1, exId.length());
				warId = warId.substring(1, warId.length());

				exWarCla.put("coverId", coverId);
				exWarCla.put("extraCoverId", extraCoverId);
				exWarCla.put("transportId", transPortId);
				exWarCla.put("exclusionsIds", exId);
				exWarCla.put("warrantyIds", warId);

				String coverDesc = "", coverDescQry = "";
				coverDescQry = "select distinct description from cover_master where branch_code = " + loginBra + " and cover_id = " + coverId + "";
				coverDesc = runner.singleSelection(coverDescQry);
				System.out.println("coverDesc " + coverDesc);
				
				// For getting Exclusion Description
				if ("NOTHING".equalsIgnoreCase(pdfEx)) {
					/*exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
							+ "("
							+ exId
							+ ")"
							+ " and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by exclusion_id";*/
					exDetailsQry = "SELECT DISTINCT EM.EXCLUSION_DESCRIPTION,EM.STATUS,EM.EXCLUSION_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,EXCLUSION_MASTER EM " +
					"WHERE EM.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + 
					coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + 
					coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), ',', ''))+ 1) AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					"AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = (select application_no from " +
					"position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=EM.BRANCH_CODE ORDER BY EM.EXCLUSION_ID";
				} else {

					/*exDetailsQry = "select exclusion_description,status,exclusion_id from exclusion_master where exclusion_id in "
							+ "("
							+ exId
							+ ") and exclusion_id not in ("
							+ concatExClausesIds
							+ ") and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by exclusion_id";*/
					
					exDetailsQry = "SELECT DISTINCT EM.EXCLUSION_DESCRIPTION,EM.STATUS,EM.EXCLUSION_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,EXCLUSION_MASTER EM " +
					"WHERE EM.EXCLUSION_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + ",'~',1,1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
					"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + 
					",'~',1,1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,1)+1,(INSTR(" + coverDesc + ",'~',1,2)-1)-INSTR(" + coverDesc + 
					",'~',1,1))), ',', ''))+ 1) AND EM.EXCLUSION_ID NOT IN (" + concatExClausesIds + ")	AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					"AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					"(select application_no from position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=EM.BRANCH_CODE ORDER BY EM.EXCLUSION_ID";
				}
				str_exDesc = runner.multipleSelection(exDetailsQry);
				exWarCla.put("exclusionsDesc", str_exDesc);

				// For getting Warranty Description
				if ("NOTHING".equalsIgnoreCase(pdfWars)) 
				{
					/*warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
							+ "("
							+ warId
							+ ")"
							+ " and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by warranty_id";*/
					warDetailsQry = "SELECT DISTINCT WM.WARRANTY_DESCRIPTION,WM.STATUS,WM.WARRANTY_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,WARRANTY_MASTER WM " +
					"WHERE WM.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
					"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + 
					",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) " +
					"AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE " +
					"AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					"(select application_no from position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=WM.BRANCH_CODE ORDER BY WM.WARRANTY_ID";
					str_warDesc = runner.multipleSelection(warDetailsQry);
				} 
				else 
				{				
					/*warDetailsQry = "select warranty_description,status,warranty_id from warranty_master where warranty_id in "
							+ "("
							+ warId
							+ ") and warranty_id not in ("
							+ concatWarClausesIds
							+ ") and status in ('Y','R') and BRANCH_CODE='"+loginBra+"' order by warranty_id";*/
					warDetailsQry = "SELECT DISTINCT WM.WARRANTY_DESCRIPTION,WM.STATUS,WM.WARRANTY_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C,WARRANTY_MASTER WM " +
					"WHERE WM.WARRANTY_ID IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
					",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + 
					coverDesc + ",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
					",INSTR(" + coverDesc + ",'~',1,2)+1,(INSTR(" + coverDesc + ",'~',1,3)-1)-INSTR(" + coverDesc + ",'~',1,2))), ',', ''))+ 1) " +
					"AND WM.WARRANTY_ID NOT IN (" + concatWarClausesIds + ") AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
					" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
					"(select application_no from position_master where quote_no = " + quoteNo + ")) AND CM.BRANCH_CODE=WM.BRANCH_CODE ORDER BY WM.WARRANTY_ID";
					str_warDesc = runner.multipleSelection(warDetailsQry);

				}
				str_warDesc = runner.multipleSelection(warDetailsQry);
				exWarCla.put("warrantyDesc", str_warDesc);

				// For Getting Clauses Description and extra_cover_id=0
				if ("0".equalsIgnoreCase(extraCoverId)) 
				{
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
					{
						/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = loginBra;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
						"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid0if " + clausesCoverQry);
					}
					else //and extra_cover_id=0
					{
						/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and clauses_id not in("+ concatClausesIds + ") and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = loginBra;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
						"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
						") AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
						"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid0else " + clausesCoverQry);
					}

					
					exWarCla.put("clausesDesc", str_coverDesc);
					exWarCla.put("extraClausesDesc", dummy);

					clausesCoverQry = "";

				}
				else //and extra_cover_id=0
				 {
					if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
					{
						/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = loginBra;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);**/
						
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + 
						",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN ( select commodity_id from marine_result_details where " +
						"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid!0if " + clausesCoverQry);
					} 
					else //and extra_cover_id=0
					{
						/*clausesCoverQry = "select clauses_description,status,clauses_id from clauses_master where cover_id=? and extra_cover_id=0 and clauses_id not in("+ concatClausesIds + ") and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = coverId;
						args[1] = loginBra;
						str_coverDesc = runner.multipleSelection(clausesCoverQry,args);*/
												
						clausesCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH " +
						"((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + 
						",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN (" + concatClausesIds + 
						") AND CM.EXTRA_COVER_ID= 0 AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra + 
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where " + 
						"application_no = (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_coverDesc = runner.multipleSelection(clausesCoverQry);
						System.out.println("coverid!0else " + clausesCoverQry);
					}
					exWarCla.put("clausesDesc", str_coverDesc);
					
					if ("NOTHING".equalsIgnoreCase(pdfexClauses)) //newone
					{
						/*clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id=?)  and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = extraCoverId;
						args[1] = loginBra;
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);*/
						
						clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
						"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
						+ ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc 
						+ ",'~',1,3))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID = " + extraCoverId + " AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE= " + loginBra +
						" AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no = " +
						"(select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						System.out.println("coverid!=0 if " + clausesCoverQry);
					} 
					else 
					{
						
						/*clausesExtraCoverQry = "select clauses_description,status,clauses_id from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id=?)  and clauses_id not in("+ concatExtraClausesIds+ ") and status in ('Y','R') and BRANCH_CODE=? order by clauses_id";
						String args[] = new String[2];
						args[0] = extraCoverId;
						args[1] = loginBra;
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry,args);*/
						
						clausesExtraCoverQry = "SELECT DISTINCT CM.CLAUSES_DESCRIPTION,CM.STATUS,CM.CLAUSES_ID FROM CLAUSES_MASTER CM,COMMODITYMASTER C " +
						"WHERE CM.COVER_ID= " + coverId + " AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + 
						",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + coverDesc + ",'~',1,3))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL " +
						"CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
						coverDesc + ",'~',1,3))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",INSTR(" + coverDesc + ",'~',1,3)+1,(INSTR(" + coverDesc + ",'~',1,4)-1)-INSTR(" + 
						coverDesc + ",'~',1,3))), ',', ''))+ 1) AND CM.CLAUSES_ID NOT IN ( " + concatExtraClausesIds + " ) AND CM.EXTRA_COVER_ID = " + extraCoverId + "  AND CM.STATUS IN ('Y','R') " +
						"AND CM.BRANCH_CODE= " + loginBra + " AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (select commodity_id from marine_result_details where application_no " +
						"= (select application_no from position_master where quote_no = " + quoteNo + ")) ORDER BY CM.CLAUSES_ID";
						str_extraCoverDesc = runner.multipleSelection(clausesExtraCoverQry);
						System.out.println("coverid!=0 else " + clausesCoverQry);
						
					}
					exWarCla.put("extraClausesDesc", str_extraCoverDesc);

					clausesCoverQry = "";
					clausesExtraCoverQry = "";
				//Rajesh on Sep 7th
					String wareId = "";
					String ware[][] = new String[0][0];
					String wareHouse[][] = new String[0][0];
				if ("NOTHING".equalsIgnoreCase(pdfexClauses)&&!("3".equalsIgnoreCase(transPortId)))
				{
					String warSql = "select nvl(b.WAREHOUSE_TO_WAREHOUSE,'No'),nvl(b.WARE_TO_WARE_FINAL_DEST,'No'),nvl(c.SP_WARRANTIES_CONDITIONS,0),nvl(c.ep_warranties_conditions,0) from  marine_result_details a,marine_data b, COUNTRY_MASTER_DETAIL c  where  a.application_no=(select application_no from position_master where quote_no=?) and b.application_no=a.application_no and c.country_id=b.final_destination_country_id and c.BELONGING_COUNTRY_ID=? and c.amend_id||c.country_id in (select max(amend_id)|| country_id from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and to_date (c.effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') group by b.WAREHOUSE_TO_WAREHOUSE,b.WARE_TO_WARE_FINAL_DEST,c.ep_warranties_conditions,c.SP_WARRANTIES_CONDITIONS";
					String args[] = new String[3];
					args[0] = quoteNo;
					args[1] = cid;
					args[2] = cid;
					ware = runner.multipleSelection(warSql,args);
					if(ware.length>0)
					{
						if("NO".equalsIgnoreCase((ware[0][0]).trim()))
						{
							wareId = wareId+ware[0][2]+",";
							
						}	
						else
							wareId = wareId+"0,";
						if("NO".equalsIgnoreCase((ware[0][1]).trim()))
						{
							wareId = wareId+ware[0][3]+",";
							
						}
						else
							wareId = wareId+"0,";
						if(wareId.length()>0)
						{
							wareId = wareId.substring(0,(wareId.length()-1));
							
							String wareSql = "select WARRANTY_ID,status,WARRANTY_DESCRIPTION from  WARRANTY_MASTER where WARRANTY_ID in("+wareId+") and branch_code='"+loginBra+"' and status='Y' order by WARRANTY_ID desc";
							wareHouse = runner.multipleSelection(wareSql);
						}
						
					}
				}
				exWarCla.put("WareClausesDesc", wareHouse);


				}

				exWarCla.put("status", "RESULTS");

			} else {

				exWarCla.put("status", "NO RESULTS");

			}
			
		} catch (Exception e) {

			System.out.println(".....RoyalTest Exception last block in Cluses policyInfo.java..."+e.toString());
			e.printStackTrace();

		}

		return exWarCla;

	}
	public String[][] getOpenCoverRelatedDatas(String openCoverNo,String conveyanceId, String coverTypeId, String statusQuery,String loginBra) 
	{
		String[][] openCoverDatas = new String[0][0];
		String sql = "";
		try 
		{

			if ("OPENSHAREDPERCENTAGE".equalsIgnoreCase(statusQuery)) 
			{

				sql = "select ocsp.insurance_company_id,ocsp.share_percentage,oci.insurance_company_name,oci.amend_id from open_cover_share_percentage ocsp,open_cover_insurance oci,open_cover_position_master ocpm where ocsp.status='Y'  and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsp.proposal_no and oci.insurance_company_id=ocsp.insurance_company_id and ocsp.amend_id = (select max(ocsps.amend_id) from open_cover_share_percentage ocsps,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsps.proposal_no and to_date(ocsps.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY')) and oci.amend_id||'-'||oci.insurance_company_id in (select max(ocis.amend_id)||'-'||ocis.insurance_company_id from open_cover_insurance ocis  where to_date(ocis.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') group by ocis.insurance_company_id) ";
				String args[] = new String[2];
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				openCoverDatas = runner.multipleSelection(sql,args);

			} 
			else if ("OPENFREETEXT".equalsIgnoreCase(statusQuery)) 
			{

				sql = "select ocft.free_text_description,ocft.cover_id from open_cover_free_text ocft,open_cover_position_master ocpm where ocft.status='Y'  and ocpm.open_cover_no=? and  ocpm.proposal_no=ocft.proposal_no and to_date(ocft.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocft.amend_id = (select max(ocfts.amend_id) from open_cover_free_text ocfts,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocfts.proposal_no and to_date(ocfts.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocfts.cover_id=?) and ocft.cover_id=?";
				String args[] = new String[4];
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				args[2] = coverTypeId;
				args[3] = coverTypeId;

				openCoverDatas = runner.multipleSelection(sql,args);

			} 
			else if ("OPENSEAOPTIONS".equalsIgnoreCase(statusQuery)) 
			{
				

				sql = "select distinct(cm.mode_transport_ID),nvl(ocsd.sea_options,'NO OPTIONS'),ocsd.amend_id from cover_master cm,open_cover_sub_detail ocsd,open_cover_position_master ocpm where cm.status='Y' and cm.BRANCH_CODE=? and cm.mode_transport_Id=ocsd.mode_transport_id and cm.cover_id=ocsd.cover_id and ocsd.status='Y' and ocpm.open_cover_no =? and  ocpm.proposal_no=ocsd.proposal_no and to_date(ocsd.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocsd.amend_id = (select max(ocsds.amend_id) from open_cover_sub_detail ocsds,open_cover_position_master ocpms where ocpms.open_cover_no=? and ocpms.proposal_no=ocsds.proposal_no and to_date(ocsds.effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and ocsds.mode_transport_id=?) and ocsd.mode_transport_id=?";
				String args[] = new String[5];
				args[0] = loginBra;
				args[1] = openCoverNo;
				args[2] = openCoverNo;
				args[3] = conveyanceId;
				args[4] = conveyanceId;
				openCoverDatas = runner.multipleSelection(sql,args);
			} 
			else if ("OPENDETAILS".equalsIgnoreCase(statusQuery)) 
			{

				sql = "select  ocd.sum_insured_limit,ocd.mode_transport_id,ocd.exchange_id,ocd.currency_id,ocd.exchange_rate,ocd.currency_name,ocd.amend_id from open_cover_detail ocd,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocd.proposal_no and ocd.amend_id in (select max(ocds.amend_id) from open_cover_detail ocds,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocds.proposal_no and to_date (ocds.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY') and ocds.mode_transport_id=?) and ocd.mode_transport_id=?";
				String args[] = new String[4];
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				args[2] = conveyanceId;
				args[3] = conveyanceId;
				openCoverDatas = runner.multipleSelection(sql,args);
			} 
			else if ("OPENMASTER".equalsIgnoreCase(statusQuery)) 
			{
				

				sql = "select ocm.broker_id,ocm.broker_user_id,ocm.policy_start_date,ocm.policy_end_date,ocm.product_id,ocm.customer_id,ocm.branch_code,ocm.cross_voyage_turnover,ocm.cross_voyage_suminsured_limit,ocm.back_date_days,ocm.commission,ocm.no_of_insurance_company,ocm.cross_voyage_percentage,ocm.rsa_shared_percentage,ocm.cross_voyage,ocm.min_premium,ocm.free_text_allowed,ocm.MISSIPPI_OPENCOVER_NO,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
				String args[] = new String[2];
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				openCoverDatas = runner.multipleSelection(sql,args);
			}

			

			if (openCoverDatas.length > 0) 
			{

			} else {

				openCoverDatas = new String[0][0];

			}

			sql = "";

		} catch (Exception e) {

			System.out.println("the Exception is POLICY INFO openCoverDatas "
					+ e.toString());e.printStackTrace();

		}

		return openCoverDatas;

	}
	public String getPolicyDefault(String[] args,String quoteId,String defBranch,String openCoverNo,String coverexist){
		String result="", sql="", productPrefix="", sequence="";
		System.out.println("Inside getPolicyDefault");
		try{
			/*if("11".equalsIgnoreCase(args[1]) && "13".equalsIgnoreCase(defBranch) && !"0".equalsIgnoreCase(openCoverNo)){
				String sql = "select policy_prefix,branch_code,agency_Code,(select MISSIPPI_OPENCOVER_NO from open_cover_master ocm where proposal_no=(select proposal_no from open_cover_position_master where OPEN_COVER_NO='"+openCoverNo+"') and amend_id=(select max(amend_id) from open_cover_master where proposal_no=ocm.proposal_no)) from policyno_generate pg where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' " +
									"and branch_code=? "+coverexist+" and amend_id=(select max(amend_id) from policyno_generate where " +
									"type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? "+coverexist+")";
				String[][] res = runner.multipleSelection(sql,args);
				if(res.length>0){
					sql = "select detail_name from Constant_detail where category_id=? and category_detail_id=? and branch_code=?";
					String cols[] = {res[0][1].trim(),res[0][1].trim(),res[0][1].trim()};
					String code = runner.singleSelection(sql, cols);
					//result = res[0][0].trim()+"/"+code.trim()+"/"+res[0][2].trim()+"/"+res[0][3].trim()+"/";
					result = res[0][3].trim()+"/";
				}
				System.out.println("Inside Egypt Branch");				
			}else{
				System.out.println("Inside Other Branch");
				String sql = "select branch_code,agency_Code||'-'||POLICY_Prefix from policyno_generate where type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' " +
								"and branch_code=? "+coverexist+" and amend_id=(select max(amend_id) from policyno_generate where " +
								"type_id=(select POLICY_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and branch_code=? "+coverexist+")";
				String[][] res = runner.multipleSelection(sql,args);
				sql = "select RSACODE from COVER_MASTER where COVER_ID in(select COVER_ID from MARINE_DATA where APPLICATION_NO=(select APPLICATION_NO from position_master" +
						" where quote_no=?)) and BRANCH_CODE=?";
				String cols[] = {quoteId,defBranch};
				String code = runner.singleSelection(sql, cols);
				if(res.length>0){
					result = res[0][0].trim()+"-"+code.trim()+"-"+res[0][1].trim()+"-";
				}
				System.out.println("Inside Other Branch End "+result);				
			}*/
			if("3".equals(args[1])){
				sql="SELECT RSACODE FROM PRODUCT_MASTER WHERE PRODUCT_ID='"+args[1]+"' AND BRANCH_CODE='"+defBranch+"'";
				productPrefix=runner.singleSelection(sql);
				sql="SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=145 AND CATEGORY_DETAIL_ID=1 AND STATUS='Y' AND BRANCH_CODE='"+defBranch+"'";
				sequence=runner.singleSelection(sql);
				sql="SELECT A.BRANCH_PREFIX||B.REMARKS||'"+productPrefix+"'||C.DETAIL_NAME||LPAD("+sequence+".NEXTVAL,C.REMARKS,0) FROM BRANCH_MASTER A, CONSTANT_DETAIL B, CONSTANT_DETAIL C  WHERE A.BRANCH_CODE='"+defBranch+"' AND B.BRANCH_CODE=A.BRANCH_CODE AND B.CATEGORY_ID=124 AND B.CATEGORY_DETAIL_ID=1 AND C.CATEGORY_ID=143 AND C.BRANCH_CODE=A.BRANCH_CODE";
			}else{
				sql="SELECT '"+getOriginalOpenCoverPolicyNo(openCoverNo)+"'||'-' FROM BRANCH_MASTER A, CONSTANT_DETAIL B, CONSTANT_DETAIL C  WHERE A.BRANCH_CODE='"+defBranch+"' AND B.BRANCH_CODE=A.BRANCH_CODE AND C.BRANCH_CODE=A.BRANCH_CODE AND B.CATEGORY_ID=124 AND B.CATEGORY_DETAIL_ID=1 AND C.CATEGORY_ID=125 AND C.CATEGORY_DETAIL_ID=1";
			}
			result=runner.singleSelection(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public String PolicyGeneration(String quoteId, String codes,
			String preOptions, String bankOptions, String generateOptions,
			String bankAssuredOptions, String currencyOptions,
			String productTypeId, String openCoverNo,String loginBra,String defBranch, String noteType, String paymentMode, String basisVal, String debitCustomerId, String certClausesYN,String issuer, String issueDate, String excessStatus) 
	{

		Map<String, String> map=new HashMap<String, String>();
		String current_no = "", error="";
		String current_debit_no = null;
		String referal = null;
		String creditNoteNo="";

		if ("3".equalsIgnoreCase(productTypeId)){
			openCoverNo = "0";
		}

		try 
		{
			String args3[] = new String[1];
			args3[0] = quoteId;
			String sql = "select count(*) from position_master where quote_no=? and remarks in('Refferal')";
			referal = runner.singleSelection(sql,args3);
			if (Integer.parseInt(referal) > 0){
				return "referal";
			}
			String args2[] = new String[1];
			args2[0] = quoteId;
			sql = "select status,policy_no,PRODUCT_ID from position_master where quote_no=?";
			String[][] fromPosition = runner.multipleSelection(sql,args2);
			fromPosition[0][0] = fromPosition[0][0] == null ? "Y": fromPosition[0][0];
			fromPosition[0][1] = fromPosition[0][1] == null ? "": fromPosition[0][1];
			productTypeId = fromPosition[0][2] == null ? "Y": fromPosition[0][2];
			if (fromPosition[0][0].equalsIgnoreCase("Y")) 
			{
				String branchCode = null;
				String countryCode = null;
				branchCode = loginBra;
				String argsS[] = new String[6];
				String coverexist = "";
				if(fromPosition[0][1].length()<=0){
					try
					{
						argsS[0] = loginBra;
						argsS[1] = productTypeId;
						argsS[2] = branchCode;
						argsS[3] = loginBra;
						argsS[4] = productTypeId;
						argsS[5] = branchCode;
						if("11".equals(productTypeId))
						{
							String ckey=runner.singleSelection("SELECT CKEY FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=?", new String[]{openCoverNo});
							if(ckey !=null && ckey.length()>0)
							{
								current_no=getCertificateNo(ckey);
							}
							if(ckey==null || "".equals(ckey) || current_no==null || "".equals(current_no)){
								return "invalid";
							}
						}
					}catch (Exception e) {e.printStackTrace();}
					countryCode = getPolicyDefault(argsS,quoteId,defBranch,openCoverNo,coverexist);
					//current_no = countryCode + current_no;
					String result=runner.singleSelection("SELECT COUNT(*) FROM POSITION_MASTER WHERE POLICY_NO=?", new String[]{current_no});
					if(!result.equals("0")){
						return "invalid";
					}else{
						runner.multipleUpdation("UPDATE POSITION_MASTER SET POLICY_NO=? WHERE QUOTE_NO=?", new String[]{current_no,quoteId});
					}
				}else{
					current_no=fromPosition[0][1];
				}
					boolean noteStatus=true;
					if("11".equalsIgnoreCase(productTypeId))
					{
						noteStatus=getOpenCoverNoteStatus(openCoverNo);
					}
					if(noteStatus)
					{
						current_debit_no = runner.singleSelection("SELECT '"+defBranch+"'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(DEBIT_NUMBER_SEQ_"+defBranch+".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=136 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"+defBranch+"'");
						if("G".equalsIgnoreCase(noteType)&& getCommissionStatus(codes, productTypeId, openCoverNo))
							creditNoteNo = runner.singleSelection("SELECT '"+defBranch+"'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(CREDIT_NUMBER_SEQ_"+defBranch+".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=137 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"+defBranch+"'");
					}
					String prevMonthYN="";
					if("".equalsIgnoreCase(issueDate) || "NO".equalsIgnoreCase(issueDate)){
						issueDate="SYSDATE";
						prevMonthYN="N";
					}else{
						issueDate="TO_DATE('"+issueDate+"','DD-MM-YYYY')";
						prevMonthYN="Y";
					}
					String args[] = new String[15];
					args[0] = quoteId;
					args[1] = preOptions;
					args[2] = bankOptions;
					args[3] = generateOptions;
					args[4] = bankAssuredOptions;
					args[5] = currencyOptions;
					args[6] = openCoverNo;
					args[7] = noteType;
					args[8] = paymentMode;
					args[9] = basisVal;
					args[10] = debitCustomerId;
					args[11] = certClausesYN;
					args[12] = prevMonthYN;
					args[13] = excessStatus;
					args[14] = quoteId;
					runner.multipleUpdation("UPDATE POSITION_MASTER SET PREMIUM=(SELECT PREMIUM FROM MARINE_RESULT WHERE QUOTE_NO=?),POLICY_TERM='1 year',PDF_PRE_SHOW_STATUS=?,PDF_BANKER_STATUS=?,PDF_GENERATE_STATUS=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,OPEN_COVER_NO=?,NOTE_TYPE=?,PAYMENT_MODE=?,BASIS_VAL=?,DEBIT_CUST_ID=?,CERT_CLAUSES_YN=?,PREV_MONTH_YN=?,PDF_EXCESS_STATUS=? WHERE  QUOTE_NO=?", args);
					updateCommission(productTypeId,current_no,codes,loginBra,openCoverNo,issuer);
					map=policyIntegration(current_no);
					if(!map.isEmpty()){
						error=(map.get("ERROR")==null?"":map.get("ERROR")).toUpperCase();
						if(error!=null && error.length()>0){
							return error;
						}else if(map.get("STATUS")==null || !"Y".equals(map.get("STATUS").toString().trim())){
							return "invalid";
						}else{
							current_debit_no=map.get("DN")==null?"":map.get("DN");
							creditNoteNo=map.get("CN")==null?"":map.get("CN");
						}
					}else{
						return "invalid"; 
					}
				try
				{
						/** Get Hours To Add Sysdate**/
						/*String hourSQL = "";
						String hour = "";
						String argss[] = new String[4];
						argss[0] = "62";
						argss[1] = "1";
						argss[2] = "Y";
						argss[3] = defBranch;
						 hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? " +
						 		"and branch_code=?";
						 hour= runner.singleSelection(hourSQL,argss);
						if(hour.length() > 0)
							hour = "sysdate" + "+" + hour;
						else
							hour = "sysdate";*/
						/** Get Hours To Add Sysdate**/
						/*args = new String[16];
						args[0] = quoteId;
						args[1] = current_no;
						args[2] = preOptions;
						args[3] = bankOptions;
						args[4] = generateOptions;
						args[5] = bankAssuredOptions;
						args[6] = currencyOptions;
						args[7] = openCoverNo;
						args[8] = current_debit_no;
						args[9] = noteType;
						args[10] = paymentMode;
						args[11] = creditNoteNo;
						args[12] = basisVal;
						args[13] = debitCustomerId;
						args[14] = certClausesYN;
						args[15] = quoteId;*/
						//sql = "update position_master set premium=(select premium from marine_result where quote_no=?),status='P',policy_term='1 year',policy_no=?,inception_date=(select sysdate+9/24 from dual),expiry_date=(select sysdate+9/24 from dual),pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,open_cover_no=?,debit_note_no=? where  quote_no=?";
						/*sql = "update position_master set premium=(select premium from marine_result where quote_no=?),status='P',policy_term='1 year'," +
								"policy_no=?,inception_date=(select "+hour+" from dual),expiry_date=(select "+hour+" from dual),pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,open_cover_no=?,debit_note_no=?,NOTE_TYPE=?,PAYMENT_MODE=?,CREDIT_NOTE_NO=?,BASIS_VAL=?,DEBIT_CUST_ID=?,CERT_CLAUSES_YN=? where  quote_no=?";*/
						runner.multipleUpdation("UPDATE POSITION_MASTER SET STATUS='P',POLICY_NO=?,INCEPTION_DATE="+issueDate+",EXPIRY_DATE="+issueDate+",DEBIT_NOTE_DATE="+issueDate+",CREDIT_NOTE_DATE="+issueDate+",MISSIPPI_TRANFER_DATE="+issueDate+",DEBIT_NOTE_NO=?,CREDIT_NOTE_NO=?,EFFECTIVE_DATE=SYSDATE WHERE  QUOTE_NO=?",new String[]{current_no, current_debit_no, creditNoteNo, quoteId});
				}
				catch (Exception e) 
				{
					System.out.println("royal test multiple policy updation... "+e.toString());
					e.printStackTrace();
				}
				if("11".equalsIgnoreCase(productTypeId))
				{
					String lcCheck[][] = new String[0][0];
					String argslc[] = new String[1];
					argslc[0] = quoteId;
					lcCheck = runner.multipleSelection("select md.OPEN_LC_ID,md.OPEN_BANK_ID,pos.OPEN_COVER_NO from MARINE_DATA md, position_master pos where md.application_no=pos.application_no and pos.quote_no=?",argslc);
					if(lcCheck.length>0)
					{
						try
						{
							String usedSums="";
							double usedSum = 0;
							double lcBal = 0;
							double available = 0;
							String argslc1[] = new String[4];
							argslc1[0] = lcCheck[0][0];
							argslc1[1] = lcCheck[0][1];
							argslc1[2] = lcCheck[0][2];
							argslc1[3] = quoteId;
							usedSums = runner.singleSelection("select sum(mrd.SUMINSUREDLOCAL+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES) from marine_data md,MARINE_RESULT_DETAILS mrd,position_master pm where md.application_no=pm.application_no and md.application_no=mrd.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=? and pm.quote_no=?",argslc1);
							usedSums = usedSums==null?"":usedSums;
							if(usedSums.length()>0)
								usedSum = Double.parseDouble(usedSums);
							argslc1 = new String[3];
							argslc1[0] = lcCheck[0][0];
							argslc1[1] = lcCheck[0][1];
							argslc1[2] = lcCheck[0][2];
							String lcBals = runner.singleSelection("select LC_BALANCE_AMOUNT from OPEN_COVER_LC_MASTER where LC_ID=? and BANK_ID=? and OPEN_COVER_NO=?",argslc1);
							lcBals = lcBals==null?"":lcBals;
							if(lcBals.length()>0)
								lcBal = Double.parseDouble(lcBals);
							available = lcBal - usedSum;
							argslc1 = new String[4];
							argslc1[0] = ""+available;
							argslc1[1] = lcCheck[0][0];
							argslc1[2] = lcCheck[0][1];
							argslc1[3] = lcCheck[0][2];
							runner.multipleUpdation("update OPEN_COVER_LC_MASTER set LC_BALANCE_AMOUNT=? where LC_ID=? and BANK_ID=? and OPEN_COVER_NO=?",argslc1);
						}
						catch (Exception e) {e.printStackTrace();}
					}	

				}
				// This is for TRN_CLOSING DATE on 25-9-2007 - removelock
				/*if ("3".equalsIgnoreCase(productTypeId)	|| "11".equalsIgnoreCase(productTypeId)) 
				{
					try 
					{
						String args[] = new String[1];
						args[0] = loginBra;
						String closeDates[][] = runner.multipleSelection("select to_char(clo_date_closed,'YYYY') as years,to_char(clo_date_closed,'mm') as months,to_char(clo_date_closed,'dd') as days,to_char(clo_date_opened,'YYYY') as years,to_char(clo_date_opened,'mm') as months,to_char(clo_date_opened,'dd') as days from t_trn_closing where BRANCH_CODE=?",args);
						
						
						String closeDates[][] = new String[0][0];
						String argsn[]= new String[2];
						argsn[0] = loginBra;
						argsn[1] = quoteId;
						closeDates = runner.multipleSelection("select to_char(trn.clo_date_closed,'YYYY') as years,to_char(trn.clo_date_closed,'mm') as months,to_char(trn.clo_date_closed,'dd') as days,to_char(trn.clo_date_opened,'YYYY') as years," +
							"to_char(trn.clo_date_opened,'mm') as months,to_char(trn.clo_date_opened,'dd') as days from t_trn_closing trn,product_master pm,position_master hpm " +
							"where pm.BRANCH_CODE=trn.branch_code and trn.branch_code=? and pm.product_id=hpm.product_id and trn.product_core_code='1' and hpm.quote_no=?",argsn);
						
						java.util.Date sysDates = new java.util.Date();

						boolean policyFlag = true;
						try 
						{
						
							java.util.Date sysDates1 = new java.util.Date(sysDates.getYear(), sysDates.getMonth(),
									sysDates.getDate());

							sysDates = sysDates1;

							java.util.Date closeDate = new java.util.Date(Integer.parseInt(closeDates[0][0]) - 1900,
									Integer.parseInt(closeDates[0][1]) - 1,
									Integer.parseInt(closeDates[0][2]));
							java.util.Date openDate = new java.util.Date(Integer.parseInt(closeDates[0][3]) - 1900,Integer.parseInt(closeDates[0][4]) - 1,
									Integer.parseInt(closeDates[0][5]));

							// if(sysDates.compareTo(openDate)<0)
							if (sysDates.compareTo(openDate) >= 0) 
							{
								if (sysDates.compareTo(closeDate) <= 0) 
								{
									sysDates = sysDates;
									policyFlag = false;
								}
								else
									sysDates = openDate;
							} 
							else
								sysDates = openDate;


						}
						catch (Exception e1) 
						{
							e1.printStackTrace();
						}
						if (policyFlag) 
						{
								String args1[] = new String[5];
								args1[0] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900));
								args1[1] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900));
								args1[2] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900))+" 01.01.01.000000 AM";
								args1[3] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900));
								args1[4] = quoteId;
								runner.multipleUpdation("update position_master set debit_note_date=?,missippi_tranfer_date=?,inception_date=?,CREDIT_NOTE_DATE=?  where  quote_no=?",args1);
						} 
						else
						{
							*//** Get Hours To Add Sysdate**//*
							String hourSQL = "";
							String hour = "";
							String argss[] = new String[4];
							argss[0] = "62";
							argss[1] = "1";
							argss[2] = "Y";
							argss[3] = defBranch;
							 hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? and status=? " +
							 		"and branch_code=?";
							 hour= runner.singleSelection(hourSQL,argss);
							if(hour.length() > 0)
								hour = "sysdate" + "+" + hour;
							else
								hour = "sysdate";
							*//** Get Hours To Add Sysdate**//*

							String args1[] = new String[1];
							args1[0] = quoteId;
							runner.multipleUpdation("update position_master set debit_note_date=(select "+hour+" from dual),missippi_tranfer_date=(select "+hour+" from dual),inception_date=(select "+hour+" from dual),CREDIT_NOTE_DATE=(SELECT "+hour+" FROM DUAL) where  quote_no=?",args1);
						}
					} 
					catch (Exception e) 
					{
						System.out.println("  ERROR OCCURED WHEN  WE CALL CLOSING DATE   "+ e.toString());e.printStackTrace();
					}
				}*/
			}
			else
				current_no = fromPosition[0][1];
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in getMaxQuote in DATACOLLECTION  "+ e.toString());e.printStackTrace();
		}
		return current_no;

	}
	public String PolicyGenerationMulti(String quoteId,
			String codes, String preOptions, String bankOptions,
			String generateOptions, String bankAssuredOptions,
			String currencyOptions, String productTypeId, String openCoverNo,
			HashMap multiQuotes,String loginBra,String cid,String commissionOption,String rubberOption,String defBranch, String noteType, String paymentMode, String currencyId, String debitCustomerId, String currencyValue, String issuer) 
	{

		String current_no = null;
		String current_debit_no = null;
		String referal = null;
		String policyNo = null;
		String creditNoteNo = "", error="";
		Map<String, String> map=new HashMap<String, String>();
		HashMap mutliInner = new HashMap();
		mutliInner = (HashMap) multiQuotes;

		int count = 0;
		String tempQuoteNo = "";

		String conCatQuotes = "";
		if(rubberOption.equalsIgnoreCase("Undefined")||rubberOption.equalsIgnoreCase("null")||rubberOption.equalsIgnoreCase(" ")||rubberOption.length()<=0||rubberOption.length()>=3)
			rubberOption ="N";
		if (mutliInner.size() > 0) 
		{
			count = Integer.parseInt((String) mutliInner.get("count") == null ? "0"	: (String) mutliInner.get("count"));
			conCatQuotes = (String) mutliInner.get("conCatQuotes") == null ? "0": (String) mutliInner.get("conCatQuotes");
		}else{
			return "HashCountNilFirst";
		}
	
		//missippiEngine eng = null;
		
		try 
		{
			String sql = "select count(*) from position_master where quote_no in ("+conCatQuotes+") and remarks in('Refferal')";
			referal = runner.singleSelection(sql);
			if (Integer.parseInt(referal) > 0) 
			{
				policyNo = "referal";
				return policyNo;
			}

			sql = "select count(*) from position_master where quote_no in ("+conCatQuotes+") and nvl(status,'Y')='Y' and status !='P'";

			String[][] fromPosition = runner.multipleSelection(sql);
			fromPosition[0][0] = fromPosition[0][0] == null ? "0": fromPosition[0][0];

			if (Integer.parseInt(fromPosition[0][0]) == count) 
			{
				String branchCode = null;
				String countryCode = null;
				branchCode = loginBra;
				String argsS[] = new String[6];
				String coverexist = "";				
					try
					{
						//String ckey=runner.singleSelection("SELECT CKEY FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=?", new String[]{openCoverNo});
						//if(ckey !=null && ckey.length()>0)
						{
							current_no=getCertificateNo(defBranch);
						}
//						if(ckey==null || "".equals(ckey) || current_no==null || "".equals(current_no)){
//							return "invalid";
//						}
						countryCode = getPolicyDefault(argsS,tempQuoteNo,defBranch,openCoverNo, coverexist);
						///current_no = countryCode + current_no;
						String result=runner.singleSelection("SELECT COUNT(*) FROM POSITION_MASTER WHERE POLICY_NO=?", new String[]{current_no});
						if(!result.equals("0")){
							return "invalid";
						}else{
							runner.multipleUpdation("UPDATE POSITION_MASTER SET POLICY_NO=? WHERE QUOTE_NO in ('"+conCatQuotes.replaceAll(",", "','")+"')", new String[]{current_no});
						}
					}
					catch (Exception e) 
					{
						System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
					}
				boolean noteStatus=true;
				if("11".equalsIgnoreCase(productTypeId))
				{
					noteStatus=getOpenCoverNoteStatus(openCoverNo);
				}
				if(noteStatus)
				{
					current_debit_no = runner.singleSelection("SELECT '"+defBranch+"'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(DEBIT_NUMBER_SEQ_"+defBranch+".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=136 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"+defBranch+"'");
					if("G".equalsIgnoreCase(noteType)&& getCommissionStatus(codes, productTypeId, openCoverNo))
						creditNoteNo = runner.singleSelection("SELECT '"+defBranch+"'||DETAIL_NAME||TO_CHAR(SYSDATE, 'YY')||REMARKS||lpad(CREDIT_NUMBER_SEQ_"+defBranch+".NEXTVAL,5,0) FROM CONSTANT_DETAIL WHERE CATEGORY_ID=137 AND CATEGORY_DETAIL_ID=1 AND BRANCH_CODE='"+defBranch+"'");
				}
					String args[] = new String[14];
					args[0] = current_no;
					args[1] = preOptions;
					args[2] = bankOptions;
					args[3] = generateOptions;
					args[4] = bankAssuredOptions;
					args[5] = currencyOptions;
					args[6] = openCoverNo;
					args[7] = commissionOption;
					args[8] = rubberOption;
					args[9] = noteType;
					args[10] = paymentMode;
					args[11] = currencyId==null?"":currencyId;
					args[12] = debitCustomerId==null?"":debitCustomerId;
					args[13] = currencyValue==null?"":currencyValue;
					sql = "UPDATE POSITION_MASTER A SET A.PREMIUM=(SELECT PREMIUM FROM MARINE_RESULT WHERE QUOTE_NO=A.QUOTE_NO),A.POLICY_TERM='1 year',A.POLICY_NO=?,A.PDF_PRE_SHOW_STATUS=?,A.PDF_BANKER_STATUS=?,A.PDF_GENERATE_STATUS=?,A.PDF_BANKER_ASSURED_STATUS=?,A.PDF_CURRENCY_STATUS=?,A.OPEN_COVER_NO=?,A.OPEN_COVER_INT_STATUS='LINKED',A.PDF_COMMISSION_STATUS=?, A.PDF_STAMP_STATUS=?,A.NOTE_TYPE=?,A.PAYMENT_MODE=?,A.OC_MUL_CURR_ID=?,A.DEBIT_CUST_ID=?,A.OC_MUL_CURR_VALUE=? WHERE A.QUOTE_NO IN ("+conCatQuotes+")";
					runner.multipleUpdation(sql, args);
					updateCommission(productTypeId,current_no,codes,loginBra,openCoverNo,issuer,"Declaration");
					map=policyIntegration(current_no);
					if(!map.isEmpty()){
						error=(map.get("ERROR")==null?"":map.get("ERROR")).toUpperCase();
						if(error!=null && error.length()>0){
							return error;
						}else if(map.get("STATUS")==null || !"Y".equals(map.get("STATUS").toString().trim())){
							return "invalid";
						}else{
							current_debit_no=map.get("DN")==null?"":map.get("DN");
							creditNoteNo=map.get("CN")==null?"":map.get("CN");
						}
					}else{
						return "invalid"; 
					}	
				for (int i = 0; i < count; i++)
				{

					tempQuoteNo = (String) mutliInner.get("" + (i + 1)) == null ? "0": (String) mutliInner.get("" + (i + 1));
					/*if(i==0){
						countryCode = getPolicyDefault(argsS,tempQuoteNo,defBranch,openCoverNo, coverexist);
						current_no = countryCode + current_no;
						String result=runner.singleSelection("SELECT COUNT(*) FROM POSITION_MASTER WHERE POLICY_NO=?", new String[]{current_no});
						if(!result.equals("0")){
							return "invalid";
						}
					}*/
					try
					{

						/** Get Hours To Add Sysdate**/
						String hourSQL = "";
						String hour = "";
						String argss[] = new String[4];
						argss[0] = "62";
						argss[1] = "1";
						argss[2] = "Y";
						argss[3] = defBranch;
						hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? " +
								"and status=? and branch_code=?";
						hour= runner.singleSelection(hourSQL,argss);
						if(hour.length() > 0)
							hour = "sysdate" + "+" + hour;
						else
							hour = "sysdate";
						/** Get Hours To Add Sysdate**/
						System.out.println("royal test multiple policy tempQuoteNo..."+tempQuoteNo);
						System.out.println("royal test multiple policy preOptions..."+preOptions);
						System.out.println("royal test multiple policy bankOptions..."+bankOptions);
						System.out.println("royal test multiple policy bankAssuredOptions..."+bankAssuredOptions);
						System.out.println("royal test multiple policy currencyOptions..."+currencyOptions);
						System.out.println("royal test multiple policy commissionOption..."+commissionOption);
						System.out.println("royal test multiple policy rubberOption..."+rubberOption);
						/*args = new String[16];
						args[0] = tempQuoteNo;
						args[1] = current_no;
						args[2] = preOptions;
						args[3] = bankOptions;
						args[4] = generateOptions;
						args[5] = bankAssuredOptions;
						args[6] = currencyOptions;
						args[7] = openCoverNo;
						args[8] = commissionOption;
						args[9] = rubberOption;
						args[10] = noteType;
						args[11] = paymentMode;
						args[12] = currencyId;
						args[13] = debitCustomerId;
						args[14] = currencyValue;
						args[15] = tempQuoteNo;*/
						

					//	sql = "update position_master set premium=(select premium from marine_result where quote_no=?),status='P',policy_term='1 year',policy_no=?,inception_date=(select sysdate+9/24 from dual),expiry_date=(select sysdate+9/24 from dual),pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,open_cover_no=?,open_cover_int_status='LINKED',debit_note_no=?, pdf_commission_status=?, pdf_stamp_status=? where quote_no=?";
//						sql = "update position_master set premium=(select premium from marine_result where quote_no=?),status='P',policy_term='1 year',policy_no=?,inception_date=(select "+hour+" from dual),expiry_date=(select "+hour+" from dual),pdf_pre_show_status=?,pdf_banker_status=?,pdf_generate_status=?,PDF_BANKER_ASSURED_STATUS=?,PDF_CURRENCY_STATUS=?,open_cover_no=?,open_cover_int_status='LINKED',debit_note_no=?, pdf_commission_status=?, pdf_stamp_status=?,NOTE_TYPE=?,PAYMENT_MODE=?,CREDIT_NOTE_NO=?,OC_MUL_CURR_ID=?,DEBIT_CUST_ID=?,OC_MUL_CURR_VALUE=? where quote_no=?";
						runner.multipleUpdation("UPDATE POSITION_MASTER SET STATUS='P',POLICY_NO=?,INCEPTION_DATE=(SELECT "+hour+" FROM DUAL),EXPIRY_DATE=(SELECT "+hour+" FROM DUAL),DEBIT_NOTE_NO=?,CREDIT_NOTE_NO=? WHERE  QUOTE_NO=?",new String[]{current_no, current_debit_no, creditNoteNo, tempQuoteNo});	
					}
					catch (Exception e1) 
					{
						System.out.println("  Error in finding OPEN AND CLOSE DATE  "+ e1.toString());e1.printStackTrace();
					}
					//THis is for update lc balnace amount for opon cover if lc used
				if("11".equalsIgnoreCase(productTypeId))
				{
					String lcCheck[][] = new String[0][0];
					String argslc[] = new String[1];
					argslc[0] = tempQuoteNo;
					lcCheck = runner.multipleSelection("select md.OPEN_LC_ID,md.OPEN_BANK_ID,pos.OPEN_COVER_NO from MARINE_DATA md, position_master pos where md.application_no=pos.application_no and pos.quote_no=?",argslc);
					if(lcCheck.length>0)
					{
						try
						{
							String usedSums="";
							double usedSum = 0;
							double lcBal = 0;
							double available = 0;
							String argslc1[] = new String[4];
							argslc1[0] = lcCheck[0][0];
							argslc1[1] = lcCheck[0][1];
							argslc1[2] = lcCheck[0][2];
							argslc1[3] = tempQuoteNo;
							usedSums = runner.singleSelection("select sum(mrd.SUMINSUREDLOCAL+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES) from marine_data md,MARINE_RESULT_DETAILS mrd,position_master pm where md.application_no=pm.application_no and md.application_no=mrd.application_no and md.open_cover_no=pm.open_cover_no and pm.status='P' and md.status='Y' and md.open_lc_id=? and md.open_bank_id=? and md.open_cover_no=? and pm.quote_no=?",argslc1);
							usedSums = usedSums==null?"":usedSums;
							if(usedSums.length()>0)
								usedSum = Double.parseDouble(usedSums);
							argslc1 = new String[3];
							argslc1[0] = lcCheck[0][0];
							argslc1[1] = lcCheck[0][1];
							argslc1[2] = lcCheck[0][2];
							String lcBals = runner.singleSelection("select LC_BALANCE_AMOUNT from OPEN_COVER_LC_MASTER where LC_ID=? and BANK_ID=? and OPEN_COVER_NO=?",argslc1);
							lcBals = lcBals==null?"":lcBals;
							if(lcBals.length()>0)
								lcBal = Double.parseDouble(lcBals);
							available = lcBal - usedSum;
							argslc1 = new String[4];
							argslc1[0] = ""+available;
							argslc1[1] = lcCheck[0][0];
							argslc1[2] = lcCheck[0][1];
							argslc1[3] = lcCheck[0][2];
							runner.multipleUpdation("update OPEN_COVER_LC_MASTER set LC_BALANCE_AMOUNT=? where LC_ID=? and BANK_ID=? and OPEN_COVER_NO=?",argslc1);
						}
						catch (Exception e) {e.printStackTrace();}
					}	

				}
					// This is for TRN_CLOSING DATE on 25-9-2007
					if ("3".equalsIgnoreCase(productTypeId)|| "11".equalsIgnoreCase(productTypeId)) 
					{
						try 
						{
							/*String args[] = new String[1];
							args[0] = loginBra;
							String closeDates[][] = runner.multipleSelection("select to_char(clo_date_closed,'YYYY') as years,to_char(clo_date_closed,'mm') as months,to_char(clo_date_closed,'dd') as days,to_char(clo_date_opened,'YYYY') as years,to_char(clo_date_opened,'mm') as months,to_char(clo_date_opened,'dd') as days from t_trn_closing where BRANCH_CODE=?",args);
							 */
							
							String closeDates[][] = new String[0][0];
							String argsn[]= new String[2];
							argsn[0] = loginBra;
							argsn[1] = tempQuoteNo;
							closeDates = runner.multipleSelection("select to_char(trn.clo_date_closed,'YYYY') as years,to_char(trn.clo_date_closed,'mm') as months,to_char(trn.clo_date_closed,'dd') as days,to_char(trn.clo_date_opened,'YYYY') as years," +
								"to_char(trn.clo_date_opened,'mm') as months,to_char(trn.clo_date_opened,'dd') as days from t_trn_closing trn,product_master pm,position_master hpm " +
								"where pm.BRANCH_CODE=trn.branch_code and trn.branch_code=? and pm.product_id=hpm.product_id and trn.product_core_code='1' and hpm.quote_no=?",argsn);
							java.util.Date sysDates = new java.util.Date();
							// For Inception Date - Rajesh
							boolean policyFlag = true;
							try {
								
								java.util.Date sysDates1 = new java.util.Date(sysDates.getYear(),sysDates.getMonth(), sysDates.getDate());
								sysDates = sysDates1;
								java.util.Date closeDate = new java.util.Date(Integer.parseInt(closeDates[0][0]) - 1900,Integer.parseInt(closeDates[0][1]) - 1,Integer.parseInt(closeDates[0][2]));
								java.util.Date openDate = new java.util.Date(Integer.parseInt(closeDates[0][3]) - 1900,Integer.parseInt(closeDates[0][4]) - 1,Integer.parseInt(closeDates[0][5]));

								if (sysDates.compareTo(openDate) >= 0) {
									if (sysDates.compareTo(closeDate) <= 0) {
										sysDates = sysDates;
										policyFlag = false;
									} else
										sysDates = openDate;
								} else
									sysDates = openDate;

								
							} catch (Exception e1) {
								System.out.println("  Error in finding OPEN AND CLOSE DATE  "+ e1.toString());
							}
							if (policyFlag)
							{
								String args1[] = new String[5];
								args1[0] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900));
								args1[1] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900));
								args1[2] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900))+" 01.01.01.000000 AM";
								args1[3] = com.maan.common.util.OracleDateConversion.ConvertDate(sysDates.getDate()+ "-"+ (sysDates.getMonth() + 1)+ "-"+ (sysDates.getYear() + 1900))+" 01.01.01.000000 AM";
								args1[4] = tempQuoteNo;
								runner.multipleUpdation("update position_master set debit_note_date=?,missippi_tranfer_date=?,inception_date=?,credit_note_date=?  where  quote_no=?",args1);
							}
							else 
							{
								/** Get Hours To Add Sysdate**/
								String hourSQL = "";
								String hour = "";
								String argss[] = new String[4];
								argss[0] = "62";
								argss[1] = "1";
								argss[2] = "Y";
								argss[3] = defBranch;
								hourSQL = "select DETAIL_NAME from constant_detail where category_id=? and category_detail_id=? " +
										"and status=? and branch_code=?";
								hour= runner.singleSelection(hourSQL,argss);
								if(hour.length() > 0)
									hour = "sysdate" + "+" + hour;
								else
									hour = "sysdate";
								/** Get Hours To Add Sysdate**/

								String args1[] = new String[1];
								args1[0] = tempQuoteNo;
								runner.multipleUpdation("update position_master set debit_note_date=(select "+hour+" from dual),missippi_tranfer_date=(select "+hour+" from dual),inception_date=(select "+hour+" from dual),credit_note_date=(select "+hour+" from dual) where  quote_no=?",args1);
							}

						}
						catch (Exception e) 
						{
							System.out.println("  ERROR OCCURED WHEN  WE CALL CLOSING DATE   "+ e.toString());e.printStackTrace();
						}
					}
					// This is for TRN_CLOSING DATE on 25-9-2007
				/*	try 
					{
						System.out.println("THIS IS MULTIQUOTE POLICY. WE ARE GOING TO MISSIPPI ENGINE ");
						eng = new missippiEngine();
						eng.setProcess("Multiple");
						eng.setLoginBra(loginBra);
						eng.setBcid(cid);
						if(i == 0)
							eng.setMultipleCheck(true);
						else
							eng.setMultipleCheck(false);
						eng.LoadingData(tempQuoteNo);
					}
					catch (Exception e) 
					{
						System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
					}*/
				}
			} 
			else 
			{
				sql = "select count(*),policy_no from position_master where quote_no in ("+conCatQuotes+") and status='P'  group by policy_no";

				String[][] fromPositionCheck = runner.multipleSelection(sql);
				if (fromPositionCheck.length > 1) 
				{
					return "HashCountNilSecond";
				} 
				else 
				{
					fromPositionCheck[0][1] = fromPositionCheck[0][1] == null ? "0":fromPositionCheck[0][1];
					fromPositionCheck[0][0] = fromPositionCheck[0][0] == null ? "0":fromPositionCheck[0][0];
					if ("0".equalsIgnoreCase(fromPositionCheck[0][1]) || "0".equalsIgnoreCase(fromPositionCheck[0][0])) 
					{
						return "HashCountNilThird";
					} 
					else 
					{
						System.out.println("fromPosition Check  Policy No "+ fromPositionCheck[0][1]);
						return fromPositionCheck[0][1];
					}
				}
			}
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
			e.printStackTrace();
		}

		//eng.processDeclaration(conCatQuotes);

		return current_no;
	}
	
	public String[][] getInvoiceDetails(String appno,String qno)
	{
		String invoiceNoAll [][] = new String[0][0];
		String invoiceQry = "";
		try
		{
			invoiceQry ="select nvl(invoice_number,' '),application_no from marine_result_details where application_no in(select application_no from position_master where customer_id=(select customer_id from position_master where application_no=?) and quote_no!=?)";
			String args[] = new String[2];
			args[0] = appno;
			args[1] = qno;
			invoiceNoAll = runner.multipleSelection(invoiceQry,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception for getting application number based on cutomer id in policy info.java"+e.toString());
			e.printStackTrace();
		}
		return invoiceNoAll;
	}
	public void updateCommission(String pid,String Policy_No,String login,String broBra,String open,String rsaissuer,String royal)throws BaseException
	{
		
		double taxPercent = getTaxPercent(broBra);
		String commis = "";
		if(rsaissuer==null)
			rsaissuer = "1";
		/*if(rsaissuer.equalsIgnoreCase("1"))
		{*/
			String commisSql = "select ocm.commission from open_cover_master ocm,open_cover_position_master ocpm where ocpm.open_cover_no =(select distinct open_cover_no from position_master where policy_no=?) and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =(select distinct open_cover_no from position_master where policy_no=?) and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY'))";
			try
			{
				String args[] = new String[2];
				args[0] = Policy_No;
				args[1] = Policy_No;
				commis = runner.singleSelection(commisSql,args);
			}
			catch (Exception e)
			{
				System.out.println("exception in"+e.toString());e.printStackTrace();
			}
		/*}
		else
		{
			commis = ""+(getRSAIssuerCommission(login,rsaissuer,pid,Policy_No));
		}*/
		if(commis.equalsIgnoreCase("null") || commis == null || commis.equals(""))
			commis = "0";
		
		rsa.opencoverpdf.finalprint cc = new rsa.opencoverpdf.finalprint();

		String res[][] = new String[0][0];
		//String sql= "select quote_no,((nvl(premium,'0')+nvl(excess_premium,'0'))*"+commis+"/100),((nvl(premium,'0')+nvl(excess_premium,'0'))),((nvl(premium,'0')+nvl(excess_premium,'0'))*"+taxPercent+"/100) from position_master where policy_no=? order by quote_no";
		String sql;
		sql = "select pos.quote_no,(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+commis+"/100)," +
		"(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0')))," +
		"(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+taxPercent+"/100) " +
		"from position_master pos,marine_Data md where pos.policy_no=? and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
		try
		{
			String args[] = new String[1];
			args[0] = Policy_No;
			res = runner.multipleSelection(sql,args);
		}
		catch (Exception e)
		{
			System.out.println("exception in"+e.toString());e.printStackTrace();
		}
		
		for(int i=0;i<res.length;i++)
		{
			String proCommissions =	cc.getProCommission(open,res[i][0],pid,broBra,"QuoteBased");
			if(proCommissions==null)
				proCommissions = "0";
			else if(proCommissions.length()<=0)
				proCommissions = "0";
			double proCommiss =  Double.parseDouble((res[i][2]!=null?res[i][2]:"0"))*Double.parseDouble(proCommissions)/100;
			String tempSql = "update position_master set COMMISSION = ?,PRO_COMMISSION=?, tax=? where quote_no=?";
			
			try
			{
				String args[] = new String[4];
				args[0] = (res[i][1]!=null?res[i][1]:"0");
				args[1] = ""+proCommiss;
				args[2] = (res[i][3]!=null?res[i][3]:"0");
				args[3] = res[i][0];
				runner.multipleUpdation(tempSql,args);
			}
			catch (Exception e)
			{
				System.out.println("exception in"+e.toString());e.printStackTrace();
			}
		}
	}
	public double getTaxPercent(String loginBra)
	{
		String query = "SELECT nvl(tax,'0') from BRANCH_MASTER where BRANCH_CODE=?";
		String result="";
		double tax = 0;
		try
		{
			String args[] = new String[1];
			args[0] = loginBra;
			result = runner.singleSelection(query,args);
			if(result.length()>0)
				tax = Double.parseDouble(result);
		}
		catch (Exception e)
		{
			System.out.println("exception in"+e.toString());e.printStackTrace();
		}
		return tax;
	}
	public void updateCommission(String pid,String Policy_No,String login,String loginBra,String openNo,
			String rsaissuer)
	{
		String proCommissions = "";
		String commis = "";
		String pno = Policy_No;
		double taxPercent = getTaxPercent(loginBra);
		if(rsaissuer==null)
			rsaissuer = "1";
		
		if(pid.equalsIgnoreCase("3"))
		{
			/*if(rsaissuer.equalsIgnoreCase("1"))
			{*/
				String commisSql = "select nvl(pos.DISCOUNT_PREMIUM,lud.COMMISSION) from login_user_details lud,position_master pos where " +
						"lud.agency_code=(select oa_code from login_master where login_id=? and status='Y') and " +
						"lud.status='Y' and lud.product_id=? and pos.Policy_No=?";
				try
				{
					String args[] = new String[3];
					args[0] = login;
					args[1] = pid;
					args[2] = Policy_No;
					commis = runner.singleSelection(commisSql,args);
				}
				catch (Exception e)
				{
					System.out.println("exception in"+e.toString());e.printStackTrace();
				}
			/*}
			else
			{
				commis = ""+(getRSAIssuerCommission(login,rsaissuer,pid,Policy_No));
			}*/
			String procommisSql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?))";
			try
			{
				String args[] = new String[10];
				args[0] = pid;
				args[1] = Policy_No;
				args[2] = loginBra;
				args[3] = Policy_No;
				args[4] = Policy_No;
				args[5] = pid;
				args[6] = Policy_No;
				args[7] = loginBra;
				args[8] = Policy_No;
				args[9] = Policy_No;
				proCommissions = runner.singleSelection(procommisSql,args);
			}
			catch (Exception e)
			{
				System.out.println("exception in"+e.toString());e.printStackTrace();
			}
		}
		else if(pid.equalsIgnoreCase("11"))
		{
			/*if(rsaissuer.equalsIgnoreCase("1"))
			{*/
				String commisSql = "select nvl(pos.DISCOUNT_PREMIUM,ocm.commission) from open_cover_master ocm,open_cover_position_master ocpm,position_master pos where ocpm.open_cover_no =(select distinct open_cover_no from position_master where policy_no=?) and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =(select distinct open_cover_no from position_master where policy_no=?) and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY')) and pos.open_cover_no=ocpm.open_cover_no and pos.policy_no=?";
				try
				{
					String args[] = new String[3];
					args[0] = Policy_No;
					args[1] = Policy_No;
					args[2] = Policy_No;
					commis = runner.singleSelection(commisSql,args);
				}
				catch (Exception e)
				{
					System.out.println("exception in"+e.toString());e.printStackTrace();
				}
			/*}
			else
			{
				commis = ""+(getRSAIssuerCommission(login,rsaissuer,pid,Policy_No));
			}*/
			String procommisSql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and open_cover_no=? and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where policy_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-MM-yyyy') from position_master where policy_no=?) and END_DATE>=(select to_char(inception_date,'dd-MM-yyyy') from position_master where policy_no=?) and open_cover_no=?)";
			
			try
			{
				String args[] = new String[12];
				args[0] = pid;
				args[1] = Policy_No;
				args[2] = loginBra;
				args[3] = Policy_No;
				args[4] = Policy_No;
				args[5] = openNo;
				args[6] = pid;
				args[7] = Policy_No;
				args[8] = loginBra;
				args[9] = Policy_No;
				args[10] = Policy_No;
				args[11] = openNo;
				proCommissions = runner.singleSelection(procommisSql,args);
			}
			catch (Exception e)
			{
				System.out.println("exception in"+e.toString());e.printStackTrace();
			}
		}

		if(proCommissions.length()==0 || proCommissions.equalsIgnoreCase(""))
				proCommissions="0";
		proCommissions = proCommissions == null ? "0" :proCommissions;
		
		if(commis.length()==0 || commis.equalsIgnoreCase(""))
				commis="0";
		
		//String sql= "select quote_no,((nvl(premium,'0')+nvl(excess_premium,'0'))*"+commis+"/100),((nvl(premium,'0')+nvl(excess_premium,'0'))*"+proCommissions+"/100),((nvl(premium,'0')+nvl(excess_premium,'0'))*"+taxPercent+"/100) from position_master where policy_no=? order by quote_no";
		String sql;
		sql = "select pos.quote_no,(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+commis+"/100)," +
				"(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+proCommissions+"/100)," +
				"(((nvl(pos.premium,'0')-round((nvl(md.POLICY_FEE,'0')+nvl(md.GOVT_TAX,'0')+nvl(md.EMERGENCY_FUND,'0')),0))+nvl(pos.excess_premium,'0'))*"+taxPercent+"/100) " +
				"from position_master pos,marine_Data md where pos.policy_no=? and md.APPLICATION_NO=pos.application_no order by pos.quote_no";
		String res[][] = new String[0][0];
		try
		{
			String args[] = new String[1];
			args[0] = pno;
			System.out.println("Update Commission...."+sql);
			res = runner.multipleSelection(sql,args);
		}
		catch (Exception e)
		{
			System.out.println("exception in"+e.toString());e.printStackTrace();
		}
		
		for(int i=0;i<res.length;i++)
		{

			String tempSql = "update position_master set COMMISSION =?,PRO_COMMISSION=?,tax=? where quote_no=?";
			
			try
			{
				String args[] = new String[4];
				args[0] = (res[i][1]!=null?res[i][1]:"0");
				args[1] = (res[i][2]!=null?res[i][2]:"0");
				args[2] = (res[i][3]!=null?res[i][3]:"0");
				args[3] = res[i][0];

				runner.multipleUpdation(tempSql,args);
			}
			catch (Exception e)
			{
				System.out.println("exception in"+e.toString());e.printStackTrace();
			}
		}
	}
	public double getRSAIssuerCommission(String login,String rsaIssuer,String pid,String polNo)
	{
		String args[] = new String[7];
		args[0] = rsaIssuer;
		args[1] = pid;
		args[2] = rsaIssuer;
		args[3] = pid;
		args[4] = login;
		args[5] = login;
		args[6] = polNo;
		String check = "";
//		if(polNo.indexOf("/")!=-1)
			 check = "pos.policy_no=?";
		/*else
			check = "pos.quote_no=?";*/
		String sql = "select nvl(pos.DISCOUNT_PREMIUM, lrd.COMMISSION) from LOGIN_RSAUSER_DETAILS lrd, position_master pos where lrd.login_id=? and lrd.product_id=? and lrd.START_DATE<=(select to_char(sysdate,'dd-MM-yyyy') from dual) and lrd.END_DATE>=(select to_char(sysdate,'dd-MM-yyyy') from dual)  and lrd.amend_id=(select max(amend_id) from LOGIN_RSAUSER_DETAILS where login_id=? and product_id=? and START_DATE<=(select to_char(sysdate,'dd-MM-yyyy')  from dual) and END_DATE>=(select to_char(sysdate,'dd-mm-yyyy') from dual) and broker_code=(select oa_code from login_master where login_id=?)) and lrd.status='Y' and lrd.broker_code=(select oa_code from login_master where login_id=?) and lrd.login_id=pos.application_id and "+check;
		
		String result="";
		double commissionDiscount=0;
		try
		{
			result = runner.singleSelection(sql,args);
			if(result.length()>0)
				commissionDiscount = Double.parseDouble(result);
		}
		catch (Exception e) 
		{
			System.out.println("exception getRSAIssuerDiscountCommission...." + e);e.printStackTrace();
		}
		return commissionDiscount;
	}
	public String[][] getInfoForFileUpload(String pqno,String proId)
	{
		String args[] = new String[2];
		String result[][] = new String[0][0];
		String sql = "";
		//String sysntax = "and nvl(a.open_cover_int_status,0) not in ('LINKED')";
		try
		{
			args[0] = proId;
			args[1] = pqno;

			sql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where b.customer_id=a.customer_id and a.product_id=? and a.quote_no=? order by substr(a.policy_no,9,16) desc";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
 
	public String[][] getInfoForFileDownload(String pqno,String proId,String branch)
	{
		String args[] = new String[2];
		String result[][] = new String[0][0];
		String sql = "";

		try
		{
			if(pqno.indexOf("/") == -1)
			{
				args = new String[4];
				args[0] = pqno;
				args[1] = proId;
				args[2] = "Y";
				args[3] = branch;
				sql ="select ofs.quote_no,ofs.customer_file_name,ofs.system_file_name,ofs.remarks,ofs.uploaded_id,mam.content_name from ofs_uploaded_data ofs,marine_upload_master mam where ofs.quote_no=? and ofs.product_id=mam.product_id and ofs.product_id=? and ofs.status=? and ofs.coverages_id=mam.content_id and mam.branch_code=? order by ofs.amend_id";
			}
			else
			{
				args = new String[6];
				args[0] = proId;
				args[1] = "Y";
				args[2] = branch;
				args[3] = pqno;
				args[4] = proId;
				args[5] = "P";
				sql ="select ofs.quote_no,ofs.customer_file_name,ofs.system_file_name,ofs.remarks,ofs.uploaded_id,mam.content_name from ofs_uploaded_data ofs,marine_upload_master mam where ofs.product_id=mam.product_id and ofs.product_id=? and ofs.status=? and ofs.coverages_id= mam.content_id and mam.branch_code=? and ofs.quote_no=(select quote_no from position_master where policy_no=? and product_id=? and status=? and nvl(open_cover_int_status,0) not in ('LINKED')) order by ofs.amend_id";
			}
			System.out.println("getInfoForFileDownload..."+sql);
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getMarineUploadMasterDetails(String proId,String branch)
	{
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[0];
		try
		{
			args = new String[3];
			args[0] = "Y";
			args[1] = branch;
			args[2] = proId;
			sql = "select content_id,content_name from marine_upload_master where status=? and branch_code=? and product_id=? order by content_id";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public String getOpenCoverNo(String proId,String quote)
	{
		String res = "";
		String sql = "";
		String args[] = new String[3];
		try
		{
			args[0] = quote;
			args[1] = proId;
			args[2] = "P";
			sql = "select open_cover_no from position_master where quote_no=? and product_id=? and status=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	public String[][] getCustCertificateForDeclaration(String openCoverNo,String cusLogin) // Customer Certificates Dec-26
	{
		String[][] returnVal = new String[0][0];
		try 
		{
			String  condition="((pm.customer_id in (select distinct customer_id from login_user_details  where login_id=?) or pm.login_id=?) and (pm.login_id=? or pm.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id=?) and usertype not in('Customer'))))";

			String qry = "select pm.quote_no,pi.first_name,pm.login_id,pm.CERTIFICATE_NO from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and "+condition+" and pm.DISCOUNT_PREMIUM is null and (pm.CERTIFICATE_NO is not null and pm.CERTIFICATE_DATE is not null) order by pm.quote_no";
			String args[] = new String[5];
			args[0] = openCoverNo;
			args[1] = cusLogin;
			args[2] = cusLogin;
			args[3] = cusLogin;
			args[4] = cusLogin;
			returnVal = runner.multipleSelection(qry,args);
		}
		catch (Exception e) 
		{
			System.out.println("  ERROR  in policyinfo.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}

	public String[][] getCertificateForDeclaration(String openCoverNo,String login,String rsaissuer) 
	{
		String[][] returnVal = new String[0][0];
		try 
		{ 
			if(rsaissuer==null)
			{
				String args[] = new String[4];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = login;
				args[3] = login;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id,pm.certificate_no from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.APPLICATION_ID='1' and pm.DISCOUNT_PREMIUM is null and pm.login_id in(select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and (pm.CERTIFICATE_NO is not null and pm.CERTIFICATE_DATE is not null) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}
			else
			{
				String args[] = new String[3];
				args[0] = openCoverNo;
				args[1] = login;
				args[2] = rsaissuer;
				String qry = "select pm.quote_no,pi.first_name,pm.login_id,pm.certificate_no from position_master pm,personal_info pi where pm.open_cover_no=? and pm.status='Y' and pm.remarks in('Normal','Admin') and pi.customer_id=pm.customer_id and pm.effective_date > (select sysdate from dual) and pm.login_id=? and pm.APPLICATION_ID=? and pm.DISCOUNT_PREMIUM is null and (pm.CERTIFICATE_NO is not null and pm.CERTIFICATE_DATE is not null) order by pm.quote_no";
				returnVal = runner.multipleSelection(qry,args);
			}
		}
		catch (Exception e)
		{
			System.out.println("ERROR in policyinfo.java " + e.toString());
			e.printStackTrace();
		}
		return returnVal;
	}
	
	public boolean checkingForPolicyGeneration(String quoteNo) // Befor generate policy no, neead to chk some important tables got entries or not.
	{
		String sql = "";
		String args[] = new String[0];
		boolean flag = false;
		String mr = "0"; String mrd = "0"; String md = "0"; String mpd = "0"; String pm = "0"; String mrPremium = "0";
		
		try
		{
			/*args = new String[2];
			args[0] = quoteNo;
			args[1] = quoteNo;
			sql = "select count(*) from marine_result where quote_no=? and application_no=(select application_no from position_master where quote_no=?)";*/
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select count(*) from marine_result mr,marine_policy_details mrp,position_master pos where mr.application_no = pos.application_no and mr.quote_no=pos.quote_no and mr.customer_id=pos.customer_id and mrp.quote_no=mr.quote_no and mrp.customer_id=mr.customer_id and pos.quote_no=?";
			mr = runner.singleSelection(sql,args);	
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select count(*) from marine_result_details where application_no=(select application_no from position_master where quote_no=?)";
			mrd = runner.singleSelection(sql, args);
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select count(*) from marine_data md,position_master pos where pos.application_no=md.application_no and  nvl(md.open_cover_no,'0')=nvl(pos.open_cover_no,'0') and md.product_id=pos.product_id and pos.quote_no=?";
			md = runner.singleSelection(sql, args);
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select count(*) from marine_policy_details where quote_no=?";
			mpd = runner.singleSelection(sql,args);
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select count(*) from position_master where quote_no=?";
			pm = runner.singleSelection(sql, args);
			
			args = new String[1];
			args[0] = quoteNo;
			sql = "select nvl(premium,'0') from marine_result where quote_no=?";
			mrPremium = runner.singleSelection(sql, args);
			
			String loginId=runner.singleSelection("SELECT LOGIN_ID FROM POSITION_MASTER WHERE QUOTE_NO=?", new String[]{quoteNo});
			boolean GHQ=getGHQOACode(loginId);  
			
			mrPremium = mrPremium == null ? "0" : mrPremium;
			mr = mr == null ? "0" : mr;
			mrd = mrd == null ? "0" : mrd;
			md = md == null ? "0" : md;
			mpd = mpd == null ? "0" : mpd;
			pm = pm == null ? "0" : pm;
			
			if( GHQ && Integer.parseInt(mr) >= 1 && Integer.parseInt(mrd) >= 1 && Integer.parseInt(md) >= 1 && Integer.parseInt(mpd) >= 1 && Integer.parseInt(pm) >= 1 )
				flag = true;
			else if( Integer.parseInt(mr) >= 1 && Integer.parseInt(mrd) >= 1 && Integer.parseInt(md) >= 1 && Integer.parseInt(mpd) >= 1 && Integer.parseInt(pm) >= 1 && Double.parseDouble(mrPremium) > 0)
				flag = true;
			else
				flag = false;
		}
		catch(Exception e)
		{
			System.out.println("checkingForPolicyGeneration......"+quoteNo);
			System.out.println("checkingForPolicyGeneration......"+e);
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	public boolean checkingForMarineResult(String quoteNo)
	{
		boolean flag = false;
		String sql = "";
		String result = "0";
		String args[] = new String[0];
		try
		{
			args = new String[2];
			args[0] = quoteNo;
			args[1] = quoteNo;
			sql = "select count(*) from marine_result where quote_no=? and application_no=(select application_no from position_master where quote_no=?)";
			result = runner.singleSelection(sql, args);
			
			if(Integer.parseInt(result) > 0)
				flag = true;
			else
				flag = false;
		}
		catch(Exception e)
		{
			System.out.println("Checking For Marine Result ...."+quoteNo);
			System.out.println("Checking For Marine Result Exception...."+e);
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	public String[][] getSettlingAgentIdFromQuote(final String quoteNo) 
	{
		String agentId[][] = new String[0][0];
		try 
		{
			final String qry = "select SETTLING_AGENT_ID,SETTLING_AGENT_NAME from marine_policy_details where quote_no=?";
			final String args[] = {quoteNo};
			agentId = runner.multipleSelection(qry,args);
		} 
		catch (Exception e) {
			System.out.println("  getSettlingAgentIdFromQuote policyinfo.java " + e.toString());
			e.printStackTrace();
		}
		return agentId;
	}
	
	public String getBrokerUserFreeText(String quoteNo){
		String arg[]={quoteNo};
		String results="";
		String qry="select nvl(broker_remarks,' ') from position_master where QUOTE_NO=?";
		
		try {
			results=runner.singleSelection(qry, arg);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return results;
	}
	public String insertOFSUploadedData(String quoteNo, String branchCode, String proId, String loginId, String custFileName, String systemFileName, String uploadRemarks, String fileType)
	{
		String temp = "sysdate";
		String insertQry = "";
		String sqlQuery_ = "";
		String amendId = "";
		String uploadId = "";
		int rows = 0;
		String args[] = new String[0];	
		try
		{
			args = new String[1];
			args[0] = quoteNo;
			sqlQuery_ ="select count(*) from OFS_UPLOADED_DATA where quote_no=?";	
			
			String res = runner.singleSelection(sqlQuery_,args);
			if(res.length()>0 && !res.equalsIgnoreCase("null"))
			{
				rows = Integer.parseInt(res);
				System.out.println("insertOFSUploadedData rows true are.."+rows);
			}
			else
			{
				rows = 0;
				System.out.println("insertOFSUploadedData rows are.."+rows);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in insertOFSUploadedData for Count :"+e.toString());
			e.printStackTrace();
		}
				
		System.out.println("OFSUploadedData  QuoteNo No count ..."+rows);
		
		if(rows<=0)
			amendId ="0";
		else
			amendId = getMaxAmendIdUpload(quoteNo,proId);

			try
			{
				uploadId = getMaxUploadId(quoteNo);

				insertQry = "insert into OFS_UPLOADED_DATA(UPLOADED_ID,AMEND_ID,QUOTE_NO,COVERAGES_ID,CUSTOMER_FILE_NAME,SYSTEM_FILE_NAME,LOGIN_ID,UPLOADED_DATE,START_DATE,END_DATE,PRODUCT_ID,STATUS,SCHEME_ID,CONTENT_TYPE_ID,REMARKS) values(?,?,?,?,?,?,?,sysdate,sysdate,sysdate,?,?,?,?,?)"; 
				
				args = new String[12];
				//args[0] = ""+Integer.parseInt(uploadId);
				args[0] = Integer.toString(Integer.parseInt(uploadId));
				
				args[1] = ""+Integer.parseInt(amendId);
				args[2] = ""+Integer.parseInt(quoteNo);
				args[3] = fileType;
				args[4] = custFileName;
				args[5] = systemFileName;
				args[6] = loginId;
				args[7] = ""+Integer.parseInt(proId);
				args[8] = "Y";
				args[9] = "";
				args[10] = fileType;
				args[11] = uploadRemarks;
					
				runner.multipleInsertion(insertQry,args);
			} 
			catch (Exception e) 
			{
				System.out.println("InsertOfficeShieldUploadData ..."+ insertQry);
				System.out.println("Error in insertOFShieldUploadData values" + e.toString());
				e.printStackTrace();
			}
			return quoteNo;
	}	

	public String getMaxAmendIdUpload(String quoteNo,String proId)
	{
		String sql = "";
		String amendId ="";
		String args[] = new String[0];
		try
		{
			
			if("30".equalsIgnoreCase(proId))
			{
				args = new String[5];
				args[0] = quoteNo;
				args[1] = "";
				args[2] = "";
				args[3] = proId;
				args[4] = "";
				sql = "select max(amend_id)+1 from OFS_UPLOADED_DATA where quote_no=? and CONTENT_TYPE_ID=? and coverages_id=? and product_id=? and scheme_id=?";
			}
			else
			{
				args = new String[3];
				args[0] = quoteNo;
				args[1] = proId;
				args[2] = "";
				sql = "select max(amend_id)+1 from OFS_UPLOADED_DATA where quote_no=? and product_id=? and scheme_id=?";
			}
			amendId = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getMaxAmendId OFS_UPLOADED_DATA Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxAmendIdUpload OFSUpload Data...."+amendId);
		return amendId;
	}

	public String getMaxUploadId(String quoteNo)
	{
		String sql = "";
		String amendId ="";
		try
		{
			sql = "select nvl(max(uploaded_id),0)+1 from OFS_UPLOADED_DATA";
			amendId = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("getMaxUploadId OFS_UPLOADED_DATA Bean"+e.toString());
			e.printStackTrace();
		}
		amendId = amendId == null ?"0": amendId;
		System.out.println("getMaxUploadId OFSUpload Data...."+amendId);
		return amendId;
	}
	public void getCustFileDelete(String uploadId)
	{
		String sqlQuery_ = "";
		String args[] = new String[2];
			
		try
		{
			args[0] = "N";
			args[1] = uploadId;
			//sqlQuery_ ="delete from OFS_UPLOADED_DATA where UPLOADED_ID = ?";	
			sqlQuery_ ="update OFS_UPLOADED_DATA set status=? where UPLOADED_ID = ?";	
			runner.multipleUpdation(sqlQuery_,args);	
		}
		catch(Exception e)
		{
			System.out.println("Exception in getCustFileDelete : "+e.toString());
			e.printStackTrace();
		}
	}
	public Map<String, String> policyIntegration(final String policyNo)
	{
		logger.info("policyIntegration - Enter || policyNo: "+policyNo);
		Map<String, String> map=new HashMap<String, String>();
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBConnection();
			cstmt = con.prepareCall("CALL INTEGRATION_TYPE(?,?,?,?,?)");
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
		}catch(Exception e){
			e.printStackTrace();
		}finally {
          try {
                if (cstmt != null)
	             cstmt.close();
                 } catch (Exception e) { e.printStackTrace();} 
           try {		
               if (con != null && !con.isClosed())
              con.close();
	                 } catch (Exception e) { e.printStackTrace(); }
              }
		logger.info("ERROR: "+map.get("ERROR")+" STATUS: "+map.get("STATUS")+" DN: "+map.get("DN")+" CN: "+map.get("CN"));
		logger.info("policyIntegration - Exit ");
		return 	map;
	}
public String[][] getPackList(String branch) {
	String[][] seaCovers = new String[0][0];
	String args[] = new String[1];
	String qry = "";
	try
	{
		args[0] = branch;
		qry = "select category_detail_id,detail_name from constant_detail where category_id='115' and branch_code=? order by category_detail_id";
		//qry = "select percent,detail_name,detail_name from constant_detail where category_id='132' and branch_code=? order by category_detail_id";
		seaCovers = runner.multipleSelection(qry, args);
	}
	catch(Exception e)
	{
		System.out.println("getPackList Value Is -->>"+e);
		e.printStackTrace();
	}
	return seaCovers;
}

public String[][] getPackageList(String branch,String modeOfTransport) {
	String[][] seaCovers = new String[0][0];
	String args[] = new String[2];
	String qry = "";
	try
	{
		args[0] = branch;
		args[1] = modeOfTransport;
		qry = "select PACKAGE_ID,PACKAGE_DESC from PACKAGE_MASTER where  branch_code=? and  MODE_TRANSPORT_ID =? and status='Y' order by PACKAGE_DESC";
		//qry = "select percent,detail_name,detail_name from constant_detail where category_id='132' and branch_code=? order by category_detail_id";
		seaCovers = runner.multipleSelection(qry, args);
	}
	catch(Exception e)
	{
		System.out.println("getPackList Value Is -->>"+e);
		e.printStackTrace();
	}
	return seaCovers;
}
public boolean getGHQOACode(String loginId) {
	boolean GHQOACode = false;
	String args[] = new String[1];
	String qry = "";
	try
	{
		args[0] = loginId;
		System.out.println("LOGIN::"+loginId);
		qry = "select count(*) from LOGIN_USER_DETAILS where login_id = '"+loginId+"' and  OA_CODE in (select detail_name from constant_detail where category_id=133)";
		String value = runner.singleSelection(qry);
		if(!value.equalsIgnoreCase("0"))
			GHQOACode = true;
		//

	}
	catch(Exception e)
	{
		System.out.println("getPackList Value Is -->>"+e);
		e.printStackTrace();
	}
	return GHQOACode;
}
public String  getModeOfTransport(String appNo) {
	String mode = "";
	String args[] = new String[2];
	String qry = "";
	String value = "";
	try
	{
		args[0] = appNo;
		args[1] = appNo;
		qry = "select MODE_OF_TRANSPORT from MARINE_DATA where APPLICATION_NO = ? and  amend_id=(select max(amend_id) from MODE_OF_TRANSPORT where  APPLICATION_NO = ?)";
		value = runner.singleSelection(qry,args);
		
	}
	catch(Exception e)
	{
		System.out.println("getPackList Value Is -->>"+e);
		e.printStackTrace();
	}
	return value;
}
public String getCertificateNo(String ckey) throws BaseException,SQLException
{
	logger.info("getCertificateNo - Enter || ckey: "+ckey);
	String certificateNo="";
	CallableStatement cstmt = null;
	Connection con = null;
	try {
	con = DBConnection.getInstance().getDBConnection();
	//cstmt = con.prepareCall("call GET_CERTIFNO(?,?,?)");
	//cstmt = con.prepareCall("Select Policy_Generate('',?,?) from dual");
	PreparedStatement pstmt = con.prepareStatement("Select Policy_Generate('',?,?) from dual");
	
	pstmt.setString(1, ckey);
	pstmt.setString(2, "Multiple");
	//cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);
	  ResultSet executeQuery = pstmt.executeQuery();
	  executeQuery.next();
	  
	certificateNo=executeQuery.getString(1);
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
	logger.info("getCertificateNo - Exit || certificateNo: "+certificateNo);
	return 	certificateNo;
}
public boolean getCreditNoteStatus(String policyNo)
{
	String result=runner.singleSelection("SELECT CREDIT_NOTE_NO FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
	if(result!=null && result.length()>0)
		return true;
	else return false;
}
public boolean getCommissionStatus(String loginId, String productId,String openCoverNo)
{
	String commission="";
	if("3".equals(productId))
	{
		commission=runner.singleSelection("SELECT NVL(COMMISSION,'0') FROM LOGIN_USER_DETAILS WHERE LOGIN_ID=? AND PRODUCT_ID=?",new String[]{loginId, productId});
	}else if("11".equals(productId))
	{
		commission=runner.singleSelection("SELECT OCM.COMMISSION FROM OPEN_COVER_MASTER OCM,OPEN_COVER_POSITION_MASTER OCPM WHERE OCPM.OPEN_COVER_NO=? AND OCPM.PROPOSAL_NO=OCM.PROPOSAL_NO AND OCM.AMEND_ID IN (SELECT MAX(OCMS.AMEND_ID) FROM OPEN_COVER_MASTER OCMS,OPEN_COVER_POSITION_MASTER OCPMS WHERE OCPMS.OPEN_COVER_NO =? AND OCPMS.PROPOSAL_NO=OCMS.PROPOSAL_NO AND TO_DATE(OCMS.EFFECTIVE_DATE,'dd-MM-YYYY') <= TO_DATE(SYSDATE,'DD-MM-YYYY'))",new String[]{openCoverNo, openCoverNo});
	}
	if(!"0".equals(commission))
		return true;
	else return false;
}
public boolean getARAccountStatus(String customerId)
{
	String arNo=runner.singleSelection("SELECT NVL(CUST_AR_NO,'0') FROM PERSONAL_INFO WHERE CUSTOMER_ID=?",new String[]{customerId});
	if(!"0".equals(arNo))
		return true;
	else return false;
}
public String[][] getBasisValList(String branchCode)
{
	String[][] list=runner.multipleSelection("SELECT CATEGORY_DETAIL_ID, REMARKS FROM CONSTANT_DETAIL WHERE CATEGORY_ID=135 AND BRANCH_CODE=? AND STATUS='Y' ORDER BY CATEGORY_DETAIL_ID",new String[]{branchCode});
	return list;
}
public String[][] getNoteNo(String policyNo)
{
	String[][] list=runner.multipleSelection("SELECT DEBIT_NOTE_NO, CREDIT_NOTE_NO FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
	return list;
}
public String getNoteType(String policyNo)
{
	String noteType=runner.singleSelection("SELECT NOTE_TYPE FROM POSITION_MASTER WHERE POLICY_NO=?",new String[]{policyNo});
	return noteType;
}
public boolean getOpenCoverNoteStatus(String openCoverNo)
{
	String result=runner.singleSelection("SELECT COUNT(1) FROM OPEN_COVER_MASTER A, OPEN_COVER_POSITION_MASTER B WHERE A.PROPOSAL_NO=B.PROPOSAL_NO AND B.OPEN_COVER_NO=? AND A.DEPOSIT_PREMIUM_YN='Y'",new String[]{openCoverNo});
	if(!"0".equals(result))
	{
		return false;
	}
	else
	{
		return true;
	}
}
public String[][] getConditions(String branchCode,String coverId, String type, String ids)
{
	logger.info("getConditions - Exit || type: "+type+" ids: "+ids);
	String result[][]=new String[0][0];
	String sql="";
	ids="".equals(ids)?"0":ids;
	if("1".equals(type))
	{
		sql="SELECT CLAUSES_ID,CLAUSES_DESCRIPTION FROM CLAUSES_MASTER WHERE COVER_ID=? AND CLAUSES_ID NOT IN("+ids+") AND EXTRA_COVER_ID=0 AND STATUS IN ('Y','R') AND BRANCH_CODE=? ORDER BY CLAUSES_ID";
		result=runner.multipleSelection(sql,new String[]{coverId,branchCode});
	}else if("2".equals(type))
	{
		sql="SELECT CLAUSES_ID,CLAUSES_DESCRIPTION FROM CLAUSES_MASTER WHERE COVER_ID=? AND CLAUSES_ID NOT IN("+ids+") AND EXTRA_COVER_ID!=0 AND STATUS IN ('Y','R') AND BRANCH_CODE=? ORDER BY CLAUSES_ID";
		result=runner.multipleSelection(sql,new String[]{coverId,branchCode});
	}else if("3".equals(type))
	{
		sql="SELECT EXCLUSION_ID,EXCLUSION_DESCRIPTION FROM EXCLUSION_MASTER WHERE EXCLUSION_ID NOT IN("+ids+") AND STATUS IN ('Y','R') AND BRANCH_CODE=? ORDER BY EXCLUSION_ID";
		result=runner.multipleSelection(sql,new String[]{branchCode});
	}else if("4".equals(type))
	{
		sql="SELECT WARRANTY_ID,WARRANTY_DESCRIPTION FROM WARRANTY_MASTER WHERE WARRANTY_ID NOT IN("+ids+") AND STATUS IN ('Y','R') AND BRANCH_CODE=? ORDER BY WARRANTY_ID";
		result=runner.multipleSelection(sql,new String[]{branchCode});
	}
	logger.info("getConditions - Exit || result: "+result.length);
	return result;
}
public void updateMinimumPremium(String openCoverNo, String[] quotes){
	logger.info("updateMinimumPremium - Enter");
	try{
		String sql="", minPreType="", totalPre="", currencyId="1", applicationNo="", appNos="", rFactor="";
		double total=0.0, minPre=0.0, diff=0.0;
		PremiumInputsBean pBean=new PremiumInputsBean();
		sql="SELECT A.MIN_PRE_MUL_TYPE,NVL(A.MIN_PRE_MUL,0) FROM OPEN_COVER_MASTER A, OPEN_COVER_POSITION_MASTER B WHERE A.PROPOSAL_NO=B.PROPOSAL_NO AND B.OPEN_COVER_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
		String[][] minPreInfo=runner.multipleSelection(sql, new String[]{openCoverNo});
		if(minPreInfo!=null && minPreInfo.length>0){
			minPreType=minPreInfo[0][0];
			minPre=Double.parseDouble(minPreInfo[0][1]);
		}
		if("M".equalsIgnoreCase(minPreType) && minPre>0){
			rFactor=getCurrencyRoundFactor(currencyId);
			if(quotes!=null && quotes.length>0){
				for (int i = 0; i < quotes.length; i++) {
					applicationNo=getapplicationNo(quotes[i]);
					appNos+=applicationNo+"','";
					runner.multipleUpdation("UPDATE MARINE_DATA A SET A.PREMIUM=(SELECT ROUND(SUM(NVL(PREMIUM,0))+SUM(NVL(WAR_CHARGES,0)),?) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=A.APPLICATION_NO),A.DEC_EXCESS_PREMIUM=0 WHERE A.APPLICATION_NO=?", new String[]{rFactor,applicationNo});
				}
				appNos=appNos.lastIndexOf("','")!=-1?appNos.substring(0, appNos.lastIndexOf("','")):appNos;
				totalPre=runner.singleSelection("SELECT SUM(PREMIUM) FROM MARINE_DATA WHERE APPLICATION_NO IN ('"+appNos+"')");
				total=Double.parseDouble(pBean.getRoundedValue(totalPre, currencyId,""));
				if(minPre>total){
					diff=minPre-total;
					for (int i = 0; i < quotes.length; i++) {
						applicationNo=getapplicationNo(quotes[i]);
						runner.multipleUpdation("UPDATE MARINE_DATA SET DEC_EXCESS_PREMIUM=ROUND(((PREMIUM/?)*?),?) WHERE APPLICATION_NO=?", new String[]{totalPre,String.valueOf(diff),rFactor,applicationNo});
						runner.multipleUpdation("UPDATE MARINE_DATA SET PREMIUM=PREMIUM+NVL(POLICY_FEE,0) WHERE APPLICATION_NO=?", new String[]{applicationNo});
						runner.multipleUpdation("UPDATE POSITION_MASTER SET OPEN_COVER_INT_STATUS='LINKED' WHERE APPLICATION_NO=?", new String[]{applicationNo});
					}
				}
			}
		}
	}catch(Exception e){
		logger.debug("Exception @ "+e);
	}
	logger.info("updateMinimumPremium - Exit");
}
public static String getCurrencyRoundFactor(String currencyId)
{
	logger.info("getCurrencyRoundFactor - Enter || currencyId: "+currencyId);
	String result = "";
		try{
			result = runner.singleSelection("SELECT NVL(RFACTOR,'0') FROM CURRENCY_MASTER WHERE CURRENCY_ID=?", new String[]{currencyId});
		}catch(Exception e){
			logger.debug("Exception @ "+e);
		}
	logger.info("getCurrencyRoundFactor - Exit || Result: "+result);
	return result;
} 
public String[][] getOpenCoverCustomerInfo(String openCoverNo)
{
	String[][] result=runner.multipleSelection("SELECT OCM.CUSTOMER_ID,NVL(PI.CUST_AR_NO,'0') FROM OPEN_COVER_MASTER OCM,OPEN_COVER_POSITION_MASTER OCPM, PERSONAL_INFO PI WHERE OCPM.OPEN_COVER_NO=? AND PI.CUSTOMER_ID=OCM.CUSTOMER_ID AND OCPM.PROPOSAL_NO=OCM.PROPOSAL_NO AND OCM.AMEND_ID IN (SELECT MAX(OCMS.AMEND_ID) FROM OPEN_COVER_MASTER OCMS,OPEN_COVER_POSITION_MASTER OCPMS WHERE OCPMS.OPEN_COVER_NO =OCPM.OPEN_COVER_NO AND OCPMS.PROPOSAL_NO=OCMS.PROPOSAL_NO AND TO_DATE(OCMS.EFFECTIVE_DATE,'dd-MM-YYYY') <= TO_DATE(SYSDATE,'dd-MM-YYYY'))",new String[]{openCoverNo});
	return result;
}
public String[][] getMultipleCustomerId(String quoteNo) 
{
	String result[][] = runner.multipleSelection("SELECT DISTINCT(CUSTOMER_ID) FROM POSITION_MASTER WHERE QUOTE_NO IN ("+ quoteNo+")");
	return result;

}
public void updatePremiumInfo(String quoteNo) throws BaseException,SQLException
{
	logger.info("updatePremiumInfo - Enter || quoteNo: "+quoteNo);
	String certificateNo="";
	CallableStatement cstmt = null;
	Connection con = null;
	try {
		con = DBConnection.getInstance().getDBConnection();
		cstmt = con.prepareCall("call UPDATE_PREMIUM(?)");
		cstmt.setString(1, quoteNo);
		cstmt.execute();
	}catch(Exception e){
		e.printStackTrace();
	}finally {
          try {
                if (cstmt != null)
	             cstmt.close();
              } catch (Exception e) { e.printStackTrace();} 
           try {		
               if (con != null && !con.isClosed())
            	   con.close();
           	   } catch (Exception e) { e.printStackTrace(); }
	}
	logger.info("updatePremiumInfo - Exit");
}
public Map<String, Object> getConditions(String applicationNo,String branchCode)
{
	logger.info("getConditions - Enter || applicationNo: "+applicationNo+" branchCode: "+branchCode);
	Map<String, Object> list=new HashMap<String, Object>();
	String result[][]=new String[0][0];
	try{
		branchCode = runner.singleSelection("select belonging_branch from branch_master where branch_code=?", new String[]{branchCode});
		String sql="SELECT CODEDESC,CODESTATUS,CODE FROM TABLE(SELECT VIEWCLAUSES(?,?,?) FROM DUAL)";
		result=runner.multipleSelection(sql, new String[]{"Clauses",applicationNo, branchCode});
		list.put("clausesDesc", result);
		result=runner.multipleSelection(sql, new String[]{"War",applicationNo, branchCode});
		list.put("extraClausesDesc", result);
		result=runner.multipleSelection(sql, new String[]{"Warranty",applicationNo, branchCode});
		list.put("warrantyDesc", result);
		result=runner.multipleSelection(sql, new String[]{"Exclusion",applicationNo, branchCode});
		list.put("exclusionsDesc", result);
		result=runner.multipleSelection(sql, new String[]{"Deductible",applicationNo, branchCode});
		if(result.length>0){
			list.put("deductible", result[0][0]);
		}
		result=runner.multipleSelection("SELECT COVER_ID, EXTRA_COVER_ID, MODE_OF_TRANSPORT FROM MARINE_DATA WHERE APPLICATION_NO=?", new String[]{applicationNo});
		if(result!=null && result.length>0){
			list.put("coverId", result[0][0]);
			list.put("extraCoverId", result[0][1]);
			list.put("transportId", result[0][2]);
		}
	}catch(Exception e){e.printStackTrace();}
	logger.info("getConditions - Exit || result: "+result.length);
	return list;
}
public static String getOriginalOpenCoverPolicyNo(String openCoverNo)
{
	logger.info("getOriginalOpenCoverPolicyNo - Enter || openCoverNo: "+openCoverNo);
	String result = "";
		try{
			result = runner.singleSelection("SELECT NVL(ORIGINAL_POLICY_NO, OPEN_COVER_NO) FROM OPEN_COVER_POSITION_MASTER WHERE OPEN_COVER_NO=?", new String[]{openCoverNo});
		}catch(Exception e){
			logger.debug("Exception @ "+e);
		}
	logger.info("getOriginalOpenCoverPolicyNo - Exit || Result: "+result);
	return result;
}
public String[][] getMonthEndClosingInfo(String branchCode, String appMode)
{
	String[][] closingInfo=runner.multipleSelection("SELECT END_FLAG,TO_CHAR(END_DATE,'DD-MM-YYYY') FROM CLOSING_PARAM@ECARGO_"+("Test".equalsIgnoreCase(appMode)?"DEV":"PROD")+" WHERE INBIZTYP=(SELECT DETAIL_NAME FROM CONSTANT_DETAIL WHERE CATEGORY_ID=147) AND PCKEY=(SELECT PCKEY_CLOSE_TRN FROM BRANCH_MASTER WHERE BRANCH_CODE=?)",new String[]{branchCode});
	return closingInfo;
}
} // Class