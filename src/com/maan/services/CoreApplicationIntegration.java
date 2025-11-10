package com.maan.services;


import java.util.HashMap;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.services.util.runner;
public class CoreApplicationIntegration 
{
	private static final Logger logger = LogUtil.getLogger(CoreApplicationIntegration.class);

	
	private String productId = null;
	private String loginBra	= "";
	private String bcid	= "";
	//Marine Declaration
	private String process = "";
	private String policy_sys_id="";
	private String shipmentSysid="";
	private String shipGoodSysid="";
	
	public String getPolicy_sys_id() {
		return policy_sys_id;
	}
	public void setPolicy_sys_id(String policy_sys_id) {
		this.policy_sys_id = policy_sys_id;
	}
	public void setLoginBra(String loginBra){
		this.loginBra=loginBra;
	}
	public String getLoginBra(){
		return this.loginBra;
	}
	public void setBcid(String bcid){
		this.bcid=bcid;
	}
	public String getBcid(){
		return this.bcid;
	}
	//Marine Declaration - Start
	public void setProcess(String process) {
		this.process = process;
	}
	public String getProcess()
	{
		return this.process;
	}
	//End
	public String PTshipgood(final String quoteNo) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[3];
		values[0]=quoteNo;
		values[1]=quoteNo;
		values[2]=quoteNo;
		
		String qry1="select ship_good_seq.nextval,com.rsacode,com.COMMODITY_NAME,cur.rsacode,mrd.SUM_INSURED," +
				"mrd.SUMINSUREDLOCAL,(mrd.SUM_INSURED+(mrd.SALE_TERM_CHARGES/md.EXCHANGE_RATE)+(mrd.TOLERANCE_CHARGES/md.EXCHANGE_RATE)) SC_FC_SI,(mrd.SUMINSUREDLOCAL+mrd.SALE_TERM_CHARGES+mrd.TOLERANCE_CHARGES) SG_LC_SI,(mrd.RATE) SG_PREM_RATE," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE,pos.quote_no from " +
				"MARINE_RESULT_DETAILS mrd,position_master pos,marine_data md,broker_company_master bcm," +
				"login_master log,COMMODITYMASTER com,CURRENCY_MASTER cur where  pos.quote_no=? and " +
				"pos.application_no=mrd.application_no and mrd.application_no=md.application_no	and " +
				"pos.login_id=log.login_id and log.oa_code=bcm.agency_code and com.COMMODITY_ID=mrd.COMMODITY_ID " +
				"and cur.CURRENCY_ID=md.CURRENCY_ID and com.amend_id||com.COMMODITY_ID in(select max(com.amend_id)||" +
				"com.commodity_id from MARINE_RESULT_DETAILS mrd,position_master pos,COMMODITYMASTER com where " +
				"pos.quote_no=? and pos.application_no=mrd.application_no and com.COMMODITY_ID=mrd.COMMODITY_ID " +
				"group by com.commodity_id) and cur.amend_id||cur.CURRENCY_ID in(select max(cur.amend_id)||" +
				"cur.CURRENCY_ID from position_master pos,marine_data md,CURRENCY_MASTER cur where  " +
				"pos.quote_no=? and pos.application_no=md.application_no and cur.CURRENCY_ID=md.CURRENCY_ID " +
				"group by cur.CURRENCY_ID)";
		
		updatedValues=runner.multipleSelection(qry1, values);
		
		
		
		finalqry="insert into PT_SHIP_GOOD(SG_SHIP_SYS_ID,SG_SYS_ID,SG_CODE,SG_DESC,SG_CURR_CODE,SG_FC_INV_AMT,SG_LC_INV_AMT,SG_FC_SI,SG_LC_SI," +
				"SG_PREM_RATE,SG_CR_DT,SG_CR_UID,SG_OP_SYS_ID";
		
		updatedqryvalues=this.shipmentSysid+",";
		
		this.shipGoodSysid=updatedValues[0][0];
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<12;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//updatedqryvalues+=("'"+tempvalue+"'")+",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_SHIP_GOOD","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+") values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		//System.out.println("finalqry"+finalqry);		
		return finalqry;
	}
	
	public String PTshipment(final String quoteNo,final String pid) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[1];
		values[0]=quoteNo;
		
		String qry1="";
		if(pid.equalsIgnoreCase("11")){
			qry1="select distinct case when mpd.LC_DATE is null then '' else ('to_date('''||to_char(mpd.LC_DATE,'dd/mm/yyyy')||''',''dd/mm/yyyy'')') end,cmd.rsacode tfromcountry, cmd1.rsacode fdest," +
				"'BY '||mot.TRANSPORT_DESCRIPTION,mot.rsacode,(case when mpd.lc_number='0' then '' else mpd.lc_number end),mpd.BL_AWB_NO,('to_date('''||to_char(mpd.BL_AWB_DATE,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),mrd.INVOICE_NUMBER," +
				"replace(oclm.lc_number,'NONE',''),replace(ocbm.bank_name,'NONE',''),('to_date('''||to_char(md.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),stm.rsacode stmrsa,(select rsacode from SETTLING_AGENT_MASTER where SETTLING_AGENT_ID=mpd.SETTLING_AGENT_ID and BRANCH_CODE=bcm.BRANCH_CODE)," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE,stm.SALE_TERM_value,ocbm.rsacode," +
				"(case when md.TRANSIT_FROM is not null and length(trim(md.TRANSIT_FROM))>0 then md.TRANSIT_FROM||', ' end)||cmd.country_name," +
				"(case when md.FINAL_DESTINATION is not null and length(trim(md.FINAL_DESTINATION))>0 then md.FINAL_DESTINATION||', ' end)||cmd1.country_name,(SELECT RSACODE FROM VESSEL_MASTER WHERE md.OPEN_VESSEL_NAME=VESSEL_NAME and branch_code=bcm.branch_code),md.OPEN_VESSEL_NAME " +
				"from MARINE_POLICY_DETAILS mpd,MARINE_DATA md,MARINE_RESULT_DETAILS mrd,position_master pos," +
				"COUNTRY_MASTER_detail cmd,COUNTRY_MASTER_detail cmd1,MODE_OF_TRANSPORT mot,OPEN_COVER_LC_MASTER oclm," +
				"OPEN_COVER_BANK_MASTER ocbm,SALE_TERM_MASTER stm,broker_company_master bcm,login_master log where " +
				"pos.quote_no=? and mpd.quote_no=pos.quote_no and mrd.APPLICATION_NO=pos.APPLICATION_NO and " +
				"md.application_no=mrd.APPLICATION_NO and cmd.country_id=md.TRANSIT_FROM_COUNTRY_ID and " +
				"cmd1.country_id=md.FINAL_DESTINATION_COUNTRY_ID and mot.MODE_TRANSPORT_id=md.MODE_OF_TRANSPORT and " +
				"oclm.lc_id=md.open_lc_id and oclm.open_cover_no=md.open_cover_no and ocbm.bank_id=md.OPEN_BANK_ID " +
				"and stm.sale_term_id=md.SALE_TERM_ID and log.login_id=pos.login_id and bcm.agency_code=log.oa_code ";
			finalqry="insert into PT_SHIPMENT(SHIP_POL_SYS_ID,SHIP_SYS_ID,SHIP_SAIL_DT,SHIP_VOYAGE_FM,SHIP_VOYAGE_TO,SHIP_CONV_DESC," +
				"SHIP_CONV_TYPE,SHIP_VOYAGE_NO,SHIP_BL_NO,SHIP_BL_DT,SHIP_ORDER_NO,SHIP_LC_NO,SHIP_BANK_NAME," +
				"SHIP_ARRIVED_DT,SHIP_VAL_BASIS,SHIP_CLM_NOTIFY_CUST,SHIP_CR_DT,SHIP_CR_UID,SHIP_VAL_PERC," +
				"SHIP_BANK_CODE,SHIP_PORT_FM_NAME,SHIP_PORT_TO_NAME,SHIP_VES_CODE,SHIP_VES_NAME";	
		}else{
			qry1="select distinct '',cmd.rsacode tfromcountry, cmd1.rsacode fdest," +
				"'BY '||mot.TRANSPORT_DESCRIPTION,mot.rsacode,'',mpd.BL_AWB_NO,('to_date('''||to_char(mpd.BL_AWB_DATE,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),mrd.INVOICE_NUMBER," +
				"(case when mpd.lc_number='0' then '' else mpd.lc_number end),trim(nvl(mpd.BANK_NAME,' ')),('to_date('''||to_char(md.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),stm.rsacode stmrsa,(select rsacode from SETTLING_AGENT_MASTER where SETTLING_AGENT_ID=mpd.SETTLING_AGENT_ID and BRANCH_CODE=bcm.BRANCH_CODE)," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE,stm.SALE_TERM_value,'',cmd.country_name,cmd1.country_name " +
				"from MARINE_POLICY_DETAILS mpd,MARINE_DATA md,MARINE_RESULT_DETAILS mrd,position_master pos," +
				"COUNTRY_MASTER_detail cmd,COUNTRY_MASTER_detail cmd1,MODE_OF_TRANSPORT mot,SALE_TERM_MASTER stm,broker_company_master bcm,login_master log where " +
				"pos.quote_no=? and mpd.quote_no=pos.quote_no and mrd.APPLICATION_NO=pos.APPLICATION_NO and " +
				"md.application_no=mrd.APPLICATION_NO and cmd.country_id=md.TRANSIT_FROM_COUNTRY_ID and " +
				"cmd1.country_id=md.FINAL_DESTINATION_COUNTRY_ID and mot.MODE_TRANSPORT_id=md.MODE_OF_TRANSPORT and " +
				" stm.sale_term_id=md.SALE_TERM_ID and log.login_id=pos.login_id and bcm.agency_code=log.oa_code ";
			
			finalqry="insert into PT_SHIPMENT(SHIP_POL_SYS_ID,SHIP_SYS_ID,SHIP_SAIL_DT,SHIP_VOYAGE_FM,SHIP_VOYAGE_TO,SHIP_CONV_DESC," +
				"SHIP_CONV_TYPE,SHIP_VOYAGE_NO,SHIP_BL_NO,SHIP_BL_DT,SHIP_ORDER_NO,SHIP_LC_NO,SHIP_BANK_NAME," +
				"SHIP_ARRIVED_DT,SHIP_VAL_BASIS,SHIP_CLM_NOTIFY_CUST,SHIP_CR_DT,SHIP_CR_UID,SHIP_VAL_PERC," +
				"SHIP_BANK_CODE,SHIP_PORT_FM_NAME,SHIP_PORT_TO_NAME";
	
		}
		String seqId = runner.singleSelection("select shipment__seq.nextval from dual");
		updatedValues=runner.multipleSelection(qry1, values);		
		
		
		updatedqryvalues=this.policy_sys_id+",";
		//String arg[] = new String[(pid.equalsIgnoreCase("11")?23:21)];
		this.shipmentSysid=seqId;
		//update value qry forming
		if(updatedValues.length>0)
		{
			updatedqryvalues += seqId+",";
			for(int i=0;i<(pid.equalsIgnoreCase("11")?22:20);i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{					
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//arg[i] = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					//updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":tempvalue) + ",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_SHIPMENT",pid);
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+")values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		//System.out.println("finalqry"+finalqry);		
		return finalqry;
	}
	
	public String PTpolicy(final String quoteNo) 
	{
		
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[1];
		values[0]= quoteNo;
		
		
		String qry1="select  pos.quote_no,bcm.branch_code,pm.RSACODE,POS.POLICY_NO," +
				"('to_date('''||to_char(pos.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"to_char(pos.inception_date,'YYYY')," +
				"(case when pm.product_id='3' then '' else pos.MISSIPPI_OPENCOVER_NO end),('to_date('''||to_char(pos.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"('to_date('''||to_char(add_months(pos.inception_date,12),'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),(case when pm.product_id='3' then bcm.missippi_id else pi.MISSIPPI_CUSTOMER_CODE end)," +
				"(pi.FIRST_NAME||pi.COMPANY_NAME),'P O BOX '||pi.pobox,(pi.ADDRESS1||pi.EMIRATE),pi.COUNTRY," +
				"mrd.description||' '||mrd.PACKAGE_DESCRIPTION||(case when mrd.INVOICE_NUMBER is not null and mrd.INVOICE_NUMBER not in('0') then 'INV'||mrd.INVOICE_NUMBER end)," +
				"((md.TOTAL_SUM_INSURED*md.EXCHANGE_RATE)+md.TOTAL_SALE_TERM_CHARGES+md.TOTAL_TOLERANCE_CHARGES),((pos.premium+pos.excess_premium)/md.EXCHANGE_RATE)" +
				",(pos.premium+pos.excess_premium),('to_date('''||to_char(pos.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"bcm.approved_prepared_by,('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"bcm.RSA_BROKER_CODE,(case when pm.product_id='3' then bcm.missippi_id else pi.MISSIPPI_CUSTOMER_CODE end),pos.quote_no,pos.quote_no,bcm.RSA_BROKER_CODE from BROKER_COMPANY_MASTER bcm,PRODUCT_MASTER pm,POSITION_MASTER pos," +
				"PERSONAL_INFO pi,MARINE_RESULT_DETAILS mrd,MARINE_DATA md,login_master log where pos.quote_no=? and " +
				"pi.CUSTOMER_ID=pos.CUSTOMER_ID and mrd.APPLICATION_NO=pos.APPLICATION_NO and md.application_no=mrd.APPLICATION_NO " +
				"and log.login_id=pos.login_id and bcm.agency_code=log.oa_code and pm.product_id=pos.product_id";		
		updatedValues=runner.multipleSelection(qry1, values);
		
		finalqry="insert into PT_POLICY(POL_SYS_ID,POL_DIVN_CODE,POL_SC_CODE,POL_NO,POL_ISSUE_DT,POL_UW_YEAR,POL_REF_NO," +
				"POL_FM_DT,POL_TO_DT,POL_CUST_CODE,POL_ASSURED_NAME,POL_ADDR1,POL_ADDR2,POL_ADDR3,POL_INTEREST,POL_LC_SI," +
				"POL_FC_PREM,POL_LC_PREM,POL_APPRV_DATE,POL_APPRV_UID,POL_CR_DT,POL_CR_UID,POL_ASSR_CODE,POL_REF_SYS_ID,POL_PROPOSAL_NO,POL_BROK_CODE";
		
		this.policy_sys_id=updatedValues[0][0];
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<26;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";					
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_POLICY","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+") values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		return finalqry;
	}
	
	public String PT_POLICY_APPL_CURR(final String quoteNo) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[3];
		values[0]=quoteNo;
		values[1]=quoteNo;
		values[2]=quoteNo;
		
		
		String qry1="select PT_POLICY_APPL_CURR_seq.nextval,cur.rsacode,exm.exchange_rate," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE from " +
				"position_master pos,marine_data md,broker_company_master bcm,login_master log,CURRENCY_MASTER cur," +
				"EXCHANGE_MASTER exm where  pos.quote_no=? and md.application_no=pos.application_no and " +
				"pos.login_id=log.login_id and log.oa_code=bcm.agency_code and cur.CURRENCY_ID=md.CURRENCY_ID " +
				"and exm.exchange_id=md.exchange_id and cur.amend_id||cur.CURRENCY_ID in(select max(cur.amend_id)" +
				"||cur.CURRENCY_ID from position_master pos,marine_data md,CURRENCY_MASTER cur where  pos.quote_no=? " +
				"and pos.application_no=md.application_no and cur.CURRENCY_ID=md.CURRENCY_ID group by cur.CURRENCY_ID) " +
				"and exm.amend_id||exm.exchange_ID in(select max(exm.amend_id)||exm.exchange_id from position_master " +
				"pos,marine_data md,exchange_MASTER exm where  pos.quote_no=? and pos.application_no=" +
				"md.application_no and exm.exchange_id=md.exchange_id group by exm.exchange_id)";		
		updatedValues=runner.multipleSelection(qry1, values);
		
		
		
		finalqry="insert into PT_POLICY_APPL_CURR(PAC_POL_SYS_ID,PAC_SYS_ID,PAC_CURR_CODE,PAC_CURR_RATE,PAC_CR_DT,PAC_CR_UID";
		
		updatedqryvalues=this.policy_sys_id+",";
		
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<5;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":("'"+tempvalue+"'"))+",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_POLICY_APPL_CURR","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+")values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		//System.out.println("finalqry"+finalqry);		
		return finalqry;
	}
	public String PtPolicyApplLocalCur(final String quoteNo) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[3];
		values[0]=quoteNo;
		values[1]=quoteNo;
		values[2]=quoteNo;
		
		
		String qry1="select PT_POLICY_APPL_CURR_seq.nextval,cur.rsacode,exm.exchange_rate," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE from " +
				"position_master pos,marine_data md,broker_company_master bcm,login_master log,CURRENCY_MASTER cur," +
				"EXCHANGE_MASTER exm where  pos.quote_no=? and md.application_no=pos.application_no and " +
				"pos.login_id=log.login_id and log.oa_code=bcm.agency_code and cur.CURRENCY_ID=1 " +
				"and exm.exchange_id=1 and cur.amend_id||cur.CURRENCY_ID in(select max(cur.amend_id)" +
				"||cur.CURRENCY_ID from position_master pos,marine_data md,CURRENCY_MASTER cur where  pos.quote_no=? " +
				"and pos.application_no=md.application_no and cur.CURRENCY_ID=1 group by cur.CURRENCY_ID) " +
				"and exm.amend_id||exm.exchange_ID in(select max(exm.amend_id)||exm.exchange_id from position_master " +
				"pos,marine_data md,exchange_MASTER exm where  pos.quote_no=? and pos.application_no=" +
				"md.application_no and exm.exchange_id=1 group by exm.exchange_id)";		
		updatedValues=runner.multipleSelection(qry1, values);
		
		
		
		finalqry="insert into PT_POLICY_APPL_CURR(PAC_POL_SYS_ID,PAC_SYS_ID,PAC_CURR_CODE,PAC_CURR_RATE,PAC_CR_DT,PAC_CR_UID";
		
		updatedqryvalues=this.policy_sys_id+",";
		
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<5;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":("'"+tempvalue+"'"))+",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_POLICY_APPL_CURR","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+")values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		return finalqry;
	}
	public String PT_CONDITION(final String quoteNo) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[1];
		values[0]=quoteNo;
				
		String qry1="select PT_CONDITION_seq.nextval,('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||" +
				"''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE from position_master pos,marine_data md,broker_company_master bcm," +
				"login_master log where  pos.quote_no=? and md.application_no=pos.application_no and " +
				"pos.login_id=log.login_id and log.oa_code=bcm.agency_code ";		
		updatedValues=runner.multipleSelection(qry1, values);
		
		finalqry="insert into PT_CONDITION(COND_POL_SYS_ID,COND_SYS_ID,COND_CR_DT,COND_CR_UID";
		
		updatedqryvalues=this.policy_sys_id+",";
				
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<3;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":("'"+tempvalue+"'"))+",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_CONDITION","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+")values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		return finalqry;
	}
	public String PT_POL_COVER(final String quoteNo) 
	{
		String[][] updatedValues=new String[0][0];
		String contValue[]=new String[2];
		String finalqry="",updatedqryvalues="",constantqry="",consqryvalues="",tempvalue="";
		
		String[] values=new String[2];
		values[0]=quoteNo;
		values[1]=quoteNo;
				
		String qry1="select PT_POL_COVER_seq.nextval,com.rsacode,com.cover_name,cur.rsacode," +
				"(mrd.SUM_INSURED+(mrd.SALE_TERM_CHARGES/md.EXCHANGE_RATE)+(mrd.TOLERANCE_CHARGES/md.EXCHANGE_RATE)) SC_FC_SI," +
				"(mrd.SUMINSUREDLOCAL+mrd.SALE_TERM_CHARGES+mrd.TOLERANCE_CHARGES) SG_LC_SI,mrd.rate," +
				"((pos.premium+pos.excess_premium)/md.EXCHANGE_RATE) PCVR_FC_PREM,(pos.premium+pos.excess_premium) PCVR_LC_PREM," +
				"('to_date('''||to_char(pos.entry_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')'),bcm.RSA_BROKER_CODE," +
				"('to_date('''||to_char(pos.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"('to_date('''||to_char(add_months(pos.inception_date,12),'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"('to_date('''||to_char(pos.inception_date,'dd/mm/yyyy')||''',''dd/mm/yyyy'')')," +
				"('to_date('''||to_char(add_months(pos.inception_date,12),'dd/mm/yyyy')||''',''dd/mm/yyyy'')') " +
				"from MARINE_RESULT_DETAILS mrd,position_master pos,marine_data md,broker_company_master bcm," +
				"login_master log,CURRENCY_MASTER cur,cover_master com where pos.quote_no=? and " +
				"pos.application_no=mrd.application_no and mrd.application_no=md.application_no and " +
				"pos.login_id=log.login_id and log.oa_code=bcm.agency_code and cur.CURRENCY_ID=md.CURRENCY_ID  " +
				"and cur.amend_id||cur.CURRENCY_ID in(select max(cur.amend_id)||cur.CURRENCY_ID from " +
				"position_master pos,marine_data md,CURRENCY_MASTER cur where  pos.quote_no=? and " +
				"pos.application_no=md.application_no and cur.CURRENCY_ID=md.CURRENCY_ID group by cur.CURRENCY_ID) " +
				"and com.cover_id=md.cover_id";	
		updatedValues=runner.multipleSelection(qry1, values);
		
		finalqry="insert into PT_POL_COVER(PCVR_POL_SYS_ID,PCVR_REF_SYS_ID,PCVR_SYS_ID,PCVR_CODE,PCVR_DESC,PCVR_SI_CURR_CODE,PCVR_FC_SI," +
				"PCVR_LC_SI,PCVR_PREM_RATE,PCVR_FC_PREM,PCVR_LC_PREM,PCVR_CR_DT,PCVR_CR_UID,PCVR_FM_DT,PCVR_TO_DT," +
				"PCVR_ENDT_EFF_FM_DT,PCVR_ENDT_EFF_TO_DT";
		
		updatedqryvalues=this.policy_sys_id+","+this.shipGoodSysid+",";
				
		//update value qry forming
		if(updatedValues.length>0)
		{
			for(int i=0;i<15;i++)
			{
				tempvalue=updatedValues[0][i]==null?"null":updatedValues[0][i];
				
				if(tempvalue.indexOf("to_date")==0)
				{
					updatedqryvalues+=tempvalue+",";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//updatedqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":("'"+tempvalue+"'"))+",";
				}
				
				
			}
			updatedqryvalues=updatedqryvalues.substring(0, updatedqryvalues.length()-1);
			
		}
		
		contValue=constantDetails("PT_POL_COVER","3");
		constantqry=contValue[0];
		consqryvalues=contValue[1];
		
		finalqry+=(constantqry.length()>0?(","+constantqry):"")+")values("+updatedqryvalues+(constantqry.length()>0?(","+consqryvalues):"")+")";
		return finalqry;
	}
	
	public String[] constantDetails(String table_name,String product_id)
	{
		String[][] constantValues=new String[0][0];
		String contValue[]=new String[2];
		String constantqry="",consqryvalues="",tempvalue="",status="";
		
		String[] values=new String[8];
		values[0]="3";//product id
		values[1]=loginBra;//branch code
		values[2]=bcid;//country id
		values[3]=table_name;//table name
		values[4]="3";//product id
		values[5]=loginBra;//branch code
		values[6]=bcid;//country id
		values[7]=table_name;//table name
				
		String qry="select field_name_display,constant_value,table_name_display,remarks from MISSIPPI_CONSTANT_VALUES where " +
				"product_id=? and branch_code=? and country_id=? and table_name_display=? and " +
				"field_name_display||amend_id in (select field_name_display||max(amend_id) from " +
				"MISSIPPI_CONSTANT_VALUES where product_id=? and branch_code=? and country_id=? " +
				"and table_name_display=? group by field_name_display) and "+(product_id.equalsIgnoreCase("11")?"status='Y'":"status in ('Y','S')"); 
		constantValues=runner.multipleSelection(qry, values);
		
//		constant values query forming
		if(constantValues.length>0)
		{
			for(int i=0;i<constantValues.length;i++)
			{
				constantqry+=constantValues[i][0]==null?"":(constantValues[i][0]+",");
				//consqryvalues+=(constantValues[i][1]==null?"null":"'"+constantValues[i][1]+"'")+",";
				
                tempvalue=constantValues[i][1]==null?"null":constantValues[i][1];
                status=constantValues[i][3]==null?"null":constantValues[i][3];
				
				if(status.equalsIgnoreCase("D")&& !tempvalue.equalsIgnoreCase("null"))
				{
					consqryvalues+="to_date('"+tempvalue+"','dd/mm/yyyy'),";
				}else
				{
					tempvalue = tempvalue.equalsIgnoreCase("null")?"null":tempvalue;
					tempvalue = tempvalue.replaceAll("&", "");
					tempvalue = tempvalue.replaceAll("'", "");
					tempvalue = tempvalue.replaceAll("\n", "");
					tempvalue = tempvalue.replaceAll("\t", "");
					consqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":"'"+tempvalue+"'")+",";
					//consqryvalues+=(tempvalue.equalsIgnoreCase("null")?"null":("'"+tempvalue+"'"))+",";
				}
			}
			constantqry=constantqry.substring(0, constantqry.length()-1);
			consqryvalues=consqryvalues.substring(0, consqryvalues.length()-1);
		}
		contValue[0]=constantqry;
		contValue[1]=consqryvalues;
		
		return contValue;
	}
	
	
	public boolean createMasterQry(final String quoteNo)
	{   
		logger.info("royal test createMasterQry core quote_no is..."+quoteNo);
		try{
			String query="";
			String cols[] = {quoteNo};
			String pid = runner.singleSelection("select nvl(product_id,'0') from position_master where status='P' and quote_no=?", cols);
			if(pid.length()>0){
				String result = runner.singleSelection("select count(*) from PT_POLICY where POL_SYS_ID=?",cols);
				if(Integer.parseInt(result)<=0){
					HashMap coreIntegration = new HashMap();
					query=PTpolicy(quoteNo);
					coreIntegration.put("Query0", query);
					query=PT_POLICY_APPL_CURR(quoteNo);
					coreIntegration.put("Query1", query);
					query=PtPolicyApplLocalCur(quoteNo);
					coreIntegration.put("Query2", query);
					query=PT_CONDITION(quoteNo);
					coreIntegration.put("Query3", query);
					query=PTshipment(quoteNo,pid);
					coreIntegration.put("Query4", query);
					query=PTshipgood(quoteNo);
					coreIntegration.put("Query5", query);
					query=PT_POL_COVER(quoteNo);
					coreIntegration.put("Query6", query);
					coreIntegration.put("Count", "7");
					String finalResult = runner.insertionTransaction(coreIntegration);
					System.out.println("royal test Core Integration finalResult..."+finalResult+" for quote no"+quoteNo);
				}
			}
		}catch(Exception e){e.printStackTrace();return false;}
		return true;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
}
