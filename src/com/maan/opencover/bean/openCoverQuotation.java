package com.maan.opencover.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.common.LogUtil;
import com.maan.services.util.runner;

public class openCoverQuotation
{
	private static final Logger logger = LogUtil.getLogger(openCoverQuotation.class);
//	/private static final String String = null;
	public String startDay="";
	public String startMonth="";
	public String startYear="";
	public String endDay="";
	public String endMonth="";
	public String endYear="";
	public String effectiveDay="";
	public String effectiveMonth="";
	public String effectiveYear="";
	public String transit_countryId="";
	public String destination_countryId="";
	public String commodityIds="";
	public String proposalNo="";
	public String currencyName="";
	public String exchangeRate="";
	public String currencyId="";
	public String exchangeId="";
	public String transit_country="";
	public String destination_country="";
	public String extraCovers="";
	public String wsrc="";
	public String saleTermId = "";
	public String CountryRemarks="";
	public String toleranceId="";
	public String toleranceName="";
	public String additionalInsured="";
	public String additionalInsuredName="";
	public String proposalStatus="";
	
	
	public String getProposalStatus() {
		return proposalStatus;
	}
	public void setProposalStatus(String proposalStatus) {
		this.proposalStatus = proposalStatus;
	}
	/**
	 * @return the additionalInsuredName
	 */
	public String getAdditionalInsuredName() {
		return additionalInsuredName;
	}
	/**
	 * @param additionalInsuredName the additionalInsuredName to set
	 */
	public void setAdditionalInsuredName(String additionalInsuredName) {
		this.additionalInsuredName = additionalInsuredName;
	}
	/**
	 * @return the additionalInsured
	 */
	public String getAdditionalInsured() {
		return additionalInsured;
	}
	/**
	 * @param additionalInsured the additionalInsured to set
	 */
	public void setAdditionalInsured(String additionalInsured) {
		this.additionalInsured = additionalInsured;
	}
	public String getToleranceName() {
		return toleranceName;
	}
	public void setToleranceName(String toleranceName) {
		this.toleranceName = toleranceName;
	}
	public String getToleranceId() {
		return toleranceId;
	}
	public void setToleranceId(String toleranceId) {
		this.toleranceId = toleranceId;
	}
	public String getCountryRemarks() {
		return CountryRemarks;
	}
	public void setCountryRemarks(String countryRemarks) {
		CountryRemarks = countryRemarks;
	}
	public String getSaleTermId() {
		return saleTermId;
	}
	public void setSaleTermId(String saleTermId) {
		this.saleTermId = saleTermId;
	}
	public void setStartDay(String startDay){
		this.startDay=startDay;
	}
	public void setStartMonth(String startMonth){
		this.startMonth=startMonth;
	}
	public void setStartYear(String startYear){
		this.startYear=startYear;
	}
	public void setEndDay(String endDay){
		this.endDay=endDay;
	}
	public void setEndMonth(String endMonth){
		this.endMonth=endMonth;
	}
	public void setEndYear(String endYear){
		this.endYear=endYear;
	}
	public void setTransit_countryId(String transit_countryId){
		this.transit_countryId=transit_countryId;
	}
	public void setDestination_countryId(String destination_countryId){
		this.destination_countryId=destination_countryId;
	}
	public void setCommodityIds(String commodityIds){
		this.commodityIds=commodityIds;
	}
	public void setProposalNo(String proposalNo){
		this.proposalNo=proposalNo;
	}
	public void setCurrencyName(String currencyName){
		this.currencyName=currencyName;
	}
	public void setExchangeRate(String exchangeRate){
		this.exchangeRate=exchangeRate;
	}
	public void setCurrencyId(String currencyId){
		this.currencyId=currencyId;
	}
	public void setExchangeId(String exchangeId){
		this.exchangeId=exchangeId;
	}
	public void setTransit_country(String transit_country){
		this.transit_country=transit_country;
	}
	public void setDestination_country(String destination_country){
		this.destination_country=destination_country;
	}
	public void setExtraCovers(String extraCovers){
		this.extraCovers=extraCovers;
	}
	public void setEffectiveDay(String effectiveDay){
		this.effectiveDay=effectiveDay;
	}
	public void setEffectiveMonth(String effectiveMonth){
		this.effectiveMonth=effectiveMonth;
	}
	public void setEffectiveYear(String effectiveYear){
		this.effectiveYear=effectiveYear;
	}
	public void setWsrc(String wsrc){
		this.wsrc=wsrc;
	}
	public String getWsrc(){
		return this.wsrc;
	}
	public String getStartDay(){
		return startDay;
	}
	public String getStartMonth(){
		return startMonth;
	}
	public String getStartYear(){
		return startYear;
	}
	public String getEndDay(){
		return endDay;
	}
	public String getEndMonth(){
		return endMonth;
	}
	public String getEndYear(){
		return endYear;
	}
	public String getTransit_countryId(){
		return transit_countryId;
	}
	public String getDestination_countryId(){
		return destination_countryId;
	}
	public String getCommodityIds(){
		return commodityIds;
	}
	public String getProposalNo(){
		return proposalNo;
	}
	public String getCurrencyName(){
		return currencyName;
	}
	public String getExchangeRate(){
		return exchangeRate;
	}
	public String getCurrencyId(){
		return currencyId;
	}
	public String getExchangeId(){
		return exchangeId;
	}
	public String getTransit_country(){
		return transit_country;
	}
	public String getDestination_country(){
		return destination_country;
	}
	public String getExtraCovers(){
		return extraCovers;
	}
	public String getEffectiveDay(){
		return effectiveDay;
	}
	public String getEffectiveMonth(){
		return effectiveMonth;
	}
	public String getEffectiveYear(){
		return effectiveYear;
	}

	public String[][] getModeMaster(String loginBra)
	{
		String[][] modeMaster = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginBra;
			sql = "select mode_transport_ID, transport_description from mode_of_transport where status='Y' and BRANCH_CODE=? order by DISPLAY_ORDER";
			modeMaster = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return modeMaster;
	}

	public String[][] getCoverMaster(String loginBra)
	{
		String[][] modeMaster = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginBra;
			//sql = "select mode_transport_ID,cover_id,cover_name from cover_master where status='Y' and BRANCH_CODE=? order by DISPLAY_ORDER";
			sql = "select mode_transport_ID,cover_id,cover_name from cover_master cm where status='Y' and BRANCH_CODE=? And Amend_id=(select max(amend_id) from cover_master C where C.cover_id=cm.cover_id and C.branch_code=cm.branch_code) order by DISPLAY_ORDER";
			modeMaster = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return modeMaster;
	}
	
	public String[][] getCommodity(String loginBra)
	{
		String[][] commodity = new String[0][0];
		String args[] = new String[2];
		String sql = "";
		try
		{
			 
			args[0] = loginBra;
			args[1] = loginBra;
			sql = "select commodity_id,commodity_name from commoditymaster cm where cm.status in ('Y','R') and BRANCH_CODE=? and cm.amend_id||'-'||cm.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where  BRANCH_CODE=? group by commodity_id) order by cm.commodity_name";
			commodity = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return commodity;
	}

	public String[][] getExtraCover(String adminBranch)
	{
		String[][] commodity = new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = adminBranch;
			sql = "select category_detail_id,detail_name from constant_detail where category_id=2 and status='Y' and branch_code=?";
			commodity = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return commodity;
	}
	

	public String[][] getExistingMode()
	{
		String[][] data = new String[0][0];
		String args[] = new String[3];
		String sql = "";
		try
		{
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = proposalNo;
			sql = "select a.mode_transport_id,a.cover_id,a.sea_options,to_char(a.effective_date,'dd') as days,to_char(a.effective_date,'mm') as months,to_char(a.effective_date,'YYYY') as years,nvl(b.sum_insured_limit,'0'),B.CURRENCY_ID,B.EXCHANGE_RATE,A.COVER_TYPE_ID from open_cover_sub_detail a,open_cover_detail b where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and b.proposal_no=a.proposal_no and  b.amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?) and b.mode_transport_id=a.mode_transport_id";
			data = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return data;
	}

	public String[] getExistingCountry(String branch)
	{
		String[][] dataValue=new String[0][0];
		String[][] datas=new String[0][0];
		String valueBasis[][] = new String[0][0];
		String[] data=new String[23];	
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			sql = "select to_char(open_cover_start_date,'dd') as days,to_char(open_cover_start_date,'mm') as months,to_char(open_cover_start_date,'YYYY') as years,to_char(open_cover_end_date,'dd') as days,to_char(open_cover_end_date,'mm') as days,to_char(open_cover_end_date,'YYYY') as years,to_char(effective_date,'dd') as days,to_char(effective_date,'mm') as months,to_char(effective_date,'YYYY') as years from open_cover_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?)";
			dataValue = runner.multipleSelection(sql,args);
			try
			{
				if(dataValue!=null && dataValue.length>0)
				{
					data[0]=""+Integer.parseInt(dataValue[0][0]);
					data[1]=""+Integer.parseInt(dataValue[0][1]);
					data[2]=""+Integer.parseInt(dataValue[0][2]);
					data[3]=""+Integer.parseInt(dataValue[0][3]);
					data[4]=""+Integer.parseInt(dataValue[0][4]);
					data[5]=""+Integer.parseInt(dataValue[0][5]);
					data[15]=""+Integer.parseInt(dataValue[0][6]);
					data[16]=""+Integer.parseInt(dataValue[0][7]);
					data[17]=""+Integer.parseInt(dataValue[0][8]);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			sql = "select nvl(open_cover_country_id_org,'0'),nvl(open_cover_country_id_dest,'0') from open_cover_country_master  where proposal_no=? and amend_id=(select max(amend_id) from open_cover_country_master where proposal_no=?)";
			dataValue = runner.multipleSelection(sql,args);
		
			if(dataValue!=null && dataValue.length>0)
			{
				sql = "select cm.country_id,cm.country_name from country_master cm where cm.country_id in("+dataValue[0][0]+") and  cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in('Y','R')  group by COUNTRY_ID) order by country_id";
				datas = runner.multipleSelection(sql);

				data[6]="";data[7]="";data[13]="";
				data[11]="";
				data[12]=""+datas.length;
				for(int i=0;i<datas.length;i++)
				{
					data[6]=data[6]+","+datas[i][1];
					data[11]=data[11]+","+datas[i][0];
				}
				if(datas.length>=1)
				{
					data[6]=data[6].substring(1,data[6].length());
					data[11]=data[11].substring(1,data[11].length());
				}
				sql = "select cm.country_id,cm.country_name from country_master cm where cm.country_id in("+dataValue[0][1]+") and  cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in('Y','R')  group by COUNTRY_ID) order by country_id"; 
				datas = runner.multipleSelection(sql);
				data[14]=""+datas.length;
				for(int i=0;i<datas.length;i++)
				{
						data[7]=data[7]+","+datas[i][1];
						data[13]=data[13]+","+datas[i][0];
				}
				if(datas.length>=1)
				{
					data[7]=data[7].substring(1,data[7].length());
					data[13]=data[13].substring(1,data[13].length());
				}
			}
			// Sale Term - Value Basis
			args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			sql = "select SALE_TERM_ID,TOLERANCE_ID,TOLERANCE_NAME from open_cover_sale_term_master where proposal_no=? and PROPOSAL_NO||amend_id in(select PROPOSAL_NO||max(amend_id) from open_cover_sale_term_master where PROPOSAL_NO=? group by PROPOSAL_NO)";
			dataValue = runner.multipleSelection(sql,args);
			
			if(dataValue!=null && dataValue.length>0)
			{
				args = new String[2];
				args[0] = "Y";
				args[1] = branch;
				System.out.println("dataValue[0][0]  " +dataValue[0][0]);
				System.out.println("branch.............."+branch);
				sql = "select stm.sale_term_id,stm.sale_term_name from sale_term_master stm where stm.sale_term_id in("+dataValue[0][0]+") and stm.status=? and stm.branch_code=?";
				System.out.println("sqllllllll"+sql);
				valueBasis = runner.multipleSelection(sql,args);
				String stN = "";
				String stId = ""; 
				for(int i=0;i<valueBasis.length;i++)
				{
					stN = stN+","+valueBasis[i][1]; // Sale Term Name
					stId = stId+","+valueBasis[i][0]; // Sale Term Id
				}
				if(valueBasis.length>=1)
				{
					data[18] = stN;
					data[19] = stId;
					data[18] = data[18].substring(1,data[18].length());
					data[19] = data[19].substring(1,data[19].length());
					System.out.println("data[18]]]]]]]]]]]]]]]"+data[18]);
					System.out.println("data[19]]]]]]]]]]]]]]]"+data[19]);
				}
				data[21]=dataValue[0][1];
				data[22]=dataValue[0][2];
			}
			String args2[]=new String[2];
			args2[0]=proposalNo;
			args2[1]=proposalNo;
			sql =  " select nvl(COUNTRY_REMARKS,'') from open_cover_master where proposal_no=? and amend_id=(select max(amend_id) "+ 
                   " from open_cover_master where proposal_no=? )";
			System.out.println("sqllllllll"+sql);
			valueBasis = runner.multipleSelection(sql,args2);			
			System.out.println("valueBasis.length-->"+valueBasis.length);
			 
			for(int i=0;i<valueBasis.length;i++)
			{
				data[20]=valueBasis[i][0]; // COUNTRY_REMARKS
			}
			System.out.println("data[20]]]]]]]]]]]]]]]"+data[20]);
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getExistingCountry "+e.toString());
			e.printStackTrace();
		}
		finally
		{
			if(data[0]==null || data[1]==null || data[2]==null)
			{
//				sql = "select to_char(sysdate,'dd') as days,to_char(sysdate,'mm') as months,to_char(sysdate,'YYYY') as years,to_char(sysdate-1,'dd') as days,to_char(sysdate-1,'mm') as months,to_char(sysdate-1,'YYYY') as years from dual";
				sql = "SELECT TO_CHAR(POLICY_START_DATE,'dd') AS DAYS,TO_CHAR(POLICY_START_DATE,'mm') AS MONTHS,TO_CHAR(POLICY_START_DATE,'YYYY') AS YEARS,TO_CHAR(POLICY_START_DATE,'dd') AS DAYS,TO_CHAR(POLICY_START_DATE,'mm') AS MONTHS,TO_CHAR(POLICY_START_DATE,'YYYY') AS YEARS FROM OPEN_COVER_MASTER A WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
				datas = runner.multipleSelection(sql, new String[]{proposalNo});
			}
			for(int i=0;i<data.length;i++)
			{
				if(i==0 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][0]);
				if(i==1 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][1]);
				if(i==2 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][2]);
				if(i==3 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][3]);
				if(i==4 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][4]);
				if(i==5 && data[i]==null)
					data[i]=""+(Integer.parseInt(datas[0][5])+1);
				if(i==15 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][0]);
				if(i==16 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][1]);
				if(i==17 && data[i]==null)
					data[i]=""+Integer.parseInt(datas[0][2]);
				data[i]=data[i]==null?"":data[i];
			}
		}
		return data;
	}

	public String[][] getCommoditys(String loginBra)
	{
		String datas[][] = new String[0][0];
		String args[] = new String[4];
		String sql = "";
		try
		{
			args[0] = proposalNo;
			args[1] = loginBra;
			args[2] = proposalNo;
			args[3] = loginBra;
			sql = "select a.commodity_id,nvl(a.commodity_name_desc,' '),a.fragile,b.commodity_name, a.commodity_type_id from open_cover_commodity_master a,commoditymaster b where a.proposal_no=? and b.BRANCH_CODE=? and a.amend_id=(select max(amend_id) from open_cover_commodity_master where proposal_no=?) and b.commodity_id=a.commodity_id and b.amend_id=(select max(amend_id) from commoditymaster where commodity_id=a.commodity_id and BRANCH_CODE=?) order by commodity_id";
			datas = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCommoditys " + e.toString());
			e.printStackTrace();
		}
		return datas;
	}
	
	public String storedValues(java.util.HashMap modeValues,String[][] commodities,String opencoverNo,String currencyType)
	{
		try
		{
			System.out.println("storedvalue");
			String effectiveDate = ""; 
			effectiveDate = ""+com.maan.common.util.OracleDateConversion.ConvertDate(effectiveDay+"-"+effectiveMonth+"-"+effectiveYear);
			String qry="0";
			java.sql.PreparedStatement pre=null;
			java.sql.Connection cont=null;
			int [] updateCounts = new int[0];
			cont = com.maan.DBCon.DBConnection.getInstance().getDBConnection();
			//String[][] count = new String[0][0];
			String count1="", count2="";
			
			String amendId = "";
			String args[] = new String[0];
			String sql = "";
			System.out.println("CountryRemarks:"+CountryRemarks);
		if("0".equalsIgnoreCase(qry) || "DIDN'T SELECTED".equalsIgnoreCase(qry))
		{
			try
			{
				String[][] date=runner.multipleSelection("SELECT TO_CHAR(POLICY_START_DATE,'dd') AS DAYS,TO_CHAR(POLICY_START_DATE,'mm') AS MONTHS,TO_CHAR(POLICY_START_DATE,'YYYY') AS YEARS,TO_CHAR(POLICY_END_DATE,'dd') AS DAYS,TO_CHAR(POLICY_END_DATE,'mm') AS DAYS,TO_CHAR(POLICY_END_DATE,'YYYY') AS YEARS FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)", new String[]{proposalNo, proposalNo});
				if(date!=null && date.length>0)
				{
					this.startDay=date[0][0];
					this.startMonth=date[0][1];
					this.startYear=date[0][2];
					this.endDay=date[0][3];
					this.endMonth=date[0][4];
					this.endYear=date[0][5];
				}
				runner.updation("update open_cover_master set policy_start_date='"+com.maan.common.util.OracleDateConversion.ConvertDate(startDay+"-"+startMonth+"-"+startYear)+"',policy_end_date='"+com.maan.common.util.OracleDateConversion.ConvertDate(endDay+"-"+endMonth+"-"+endYear)+"',wrsc_YN='"+wsrc+"',COUNTRY_REMARKS='"+CountryRemarks+"',ADDITIONAL_INSURED='"+additionalInsured+"',ADDITIONAL_INSURED_NAME='"+additionalInsuredName+"',PROPOSAL_STATUS='"+proposalStatus+"' where proposal_no='"+proposalNo+"' and amend_id=(select max(amend_id) from open_cover_master where proposal_no='"+proposalNo+"')");
				System.out.println("CountryRemarks:"+CountryRemarks);
				
				Map ModeDetails=getConveyance(proposalNo);
				Set set=ModeDetails.keySet();
				for(Object i:set){
					System.out.println("Key-->"+i+"   Value-->"+ModeDetails.get(i));
				}
				
				if(opencoverNo==null)
				{
					args = new String[1];
					args[0] = proposalNo;
					sql = "delete from open_cover_detail where proposal_no=?";
					runner.multipleUpdation(sql,args);

					sql = "delete from open_cover_sub_detail where proposal_no=?";
					runner.multipleUpdation(sql,args);

					sql = "delete from open_cover_commodity_master where proposal_no=?";
					runner.multipleUpdation(sql,args);
				}		
			
			qry="insert into open_cover_detail(open_cover_detail_id,proposal_no,amend_id,mode_transport_id,sum_insured_limit,exchange_id,currency_id,exchange_rate,currency_name,status,effective_date,open_cover_start_date,open_cover_end_date,CONVEYANCE)values(?,?,(select nvl(max(amend_id)+1,'0') from open_cover_detail where proposal_no='"+proposalNo+"'),?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println("Query===>"+qry);
			
			count1=runner.singleSelection("select nvl(max(open_cover_detail_id)+1,'1') from open_cover_detail");
			count2=runner.singleSelection("select nvl(max(open_cover_sub_detail_id)+1,'1') from open_cover_sub_detail");
			
			//count = runner.multipleSelection("select nvl(max(a.open_cover_detail_id)+1,'1'),nvl(max(b.open_cover_sub_detail_id)+1,'1') from open_cover_sub_detail b,open_cover_detail a");
			
			String Open_startDate = com.maan.common.util.OracleDateConversion.ConvertDate(startDay+"-"+startMonth+"-"+startYear);
			String Open_endDate = com.maan.common.util.OracleDateConversion.ConvertDate(endDay+"-"+endMonth+"-"+endYear);
			
			int specialCount=0;
		
			pre	=cont.prepareStatement(qry);
			for(int i=0;i<Integer.parseInt((String)modeValues.get("modeLength"));i++)
			{
					if(modeValues.get("mode-name"+i)!=null)
					{
						specialCount++;
						count1=""+(Integer.parseInt(count1)+1);
						
						pre.setString(1,""+(Integer.parseInt(count1)));
						pre.setString(2,proposalNo);
						pre.setString(3,(String)modeValues.get("mode-id"+i));
						System.out.println("===>"+(String)modeValues.get("mode-id"+i));
						pre.setString(4,(String)modeValues.get("perbottom-limit"+i));
						pre.setString(5,"1");//exchangeId
						pre.setString(6,(String)modeValues.get("currencyId"+i));//currencyId
						pre.setString(7,(String)modeValues.get("currencyValue"+i));//exchangeRate
						pre.setString(8,currencyType);//currencyName
						pre.setString(9,"Y");
						pre.setString(10,effectiveDate);
						pre.setString(11,Open_startDate);
						pre.setString(12,Open_endDate);
						//String res=runner.singleSelection("SELECT  REPLACE (REPLACE (OCD.CONVEYANCE, CHR (10), ' '), CHR (13), ' ') FROM   OPEN_COVER_DETAIL OCD WHERE   OCD.PROPOSAL_NO ='"+proposalNo+"' and OCD.MODE_TRANSPORT_ID='"+(String)modeValues.get("mode-id"+i)+"' AND OCD.AMEND_ID = (SELECT   MAX (AMEND_ID) FROM   OPEN_COVER_DETAIL WHERE   PROPOSAL_NO = OCD.PROPOSAL_NO AND MODE_TRANSPORT_ID=OCD.MODE_TRANSPORT_ID AND STATUS = 'Y' AND TO_DATE (EFFECTIVE_DATE, 'DD-mm-YY') <=TO_DATE (SYSDATE, 'DD-mm-YY'))  AND OCD.STATUS = 'Y' AND TO_DATE (OCD.EFFECTIVE_DATE, 'DD-mm-YY') <=TO_DATE (SYSDATE, 'DD-mm-YY')");
						//System.out.println("Result===>"+res);
						pre.setString(13,(String)ModeDetails.get((String)modeValues.get("mode-id"+i)));
						pre.addBatch();
					}
				}
				if(specialCount>0)
					updateCounts = pre.executeBatch();
				else
				{
					try
					{
						int i=0;
							pre.setString(1,""+Integer.parseInt(count1));
							pre.setString(2,proposalNo);
							pre.setString(3,(String)modeValues.get("mode-id"+i));
							System.out.println("===>"+(String)modeValues.get("mode-id"+i));
							pre.setString(4,(String)modeValues.get("perbottom-limit"+i));
							pre.setString(5,"1");//exchangeId
							pre.setString(6,(String)modeValues.get("currencyId"+i));//currencyId
							pre.setString(7,"1");//exchangeRate
							pre.setString(8,currencyType);//currencyName
							pre.setString(9,"Y");
							pre.setString(10,effectiveDate);
							pre.setString(11,Open_startDate);
							pre.setString(12,Open_endDate);
							//String res=runner.singleSelection("SELECT  REPLACE (REPLACE (OCD.CONVEYANCE, CHR (10), ' '), CHR (13), ' ') FROM   OPEN_COVER_DETAIL OCD WHERE   OCD.PROPOSAL_NO ='"+proposalNo+"' and OCD.MODE_TRANSPORT_ID='"+(String)modeValues.get("mode-id"+i)+"' AND OCD.AMEND_ID = (SELECT   MAX (AMEND_ID) FROM   OPEN_COVER_DETAIL WHERE   PROPOSAL_NO = OCD.PROPOSAL_NO AND MODE_TRANSPORT_ID=OCD.MODE_TRANSPORT_ID AND STATUS = 'Y' AND TO_DATE (EFFECTIVE_DATE, 'DD-mm-YY') <=TO_DATE (SYSDATE, 'DD-mm-YY'))  AND OCD.STATUS = 'Y' AND TO_DATE (OCD.EFFECTIVE_DATE, 'DD-mm-YY') <=TO_DATE (SYSDATE, 'DD-mm-YY')");
							//System.out.println("Result===>"+res);
							pre.setString(13,(String)ModeDetails.get((String)modeValues.get("mode-id"+i)));
							pre.executeUpdate();
					}
					catch(Exception e)
					{
						System.out.println(" ERROR in Single Insertion s"+e.toString());
						e.printStackTrace();
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("ERROR in MODE INSERTION------------->"+e.toString());
				e.printStackTrace();
			}

			try
			{
				args = new String[5];
				args[0] = proposalNo;
				args[1] = proposalNo;
				args[2] = proposalNo;
				args[3] = proposalNo;
				args[4] = proposalNo;
				
				sql = "update open_cover_position_master set inception_date=(select distinct(open_cover_start_date) from open_cover_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?)),expiry_date=(select distinct(open_cover_end_date) from open_cover_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_detail where proposal_no=?)) where proposal_no=?";
				runner.multipleUpdation(sql,args);
			}
			catch(Exception e)
			{
				System.out.println("<< Query for updating the inception date & expiry date >>");
				System.out.println(""+e.toString());
				e.printStackTrace();
			}
					
			//Cover Insertion
					
			try
			{
				pre.clearBatch();
				pre=null;
				qry="insert into open_cover_sub_detail(open_cover_sub_detail_id,proposal_no,amend_id,mode_transport_id,cover_id,sea_options,status,effective_date,COVER_TYPE_ID)values(?,?,(select nvl(max(amend_id)+1,'0') from open_cover_sub_detail where proposal_no='"+proposalNo+"'),?,?,?,?,?,?)";
				pre = cont.prepareStatement(qry);
				for(int i=0;i<Integer.parseInt((String)modeValues.get("coverLength"));i++)
				{
					if(modeValues.get("cover-id"+i)!=null)
					{
						count2=""+(Integer.parseInt(count2)+1);
						pre.setString(1,count2);
						pre.setString(2,proposalNo);
						pre.setString(3,(String)modeValues.get("mode-idd"+i));
						pre.setString(4,(String)modeValues.get("cover-id"+i));
						
						if("1".equalsIgnoreCase((String)modeValues.get("mode-idd"+i)))
							pre.setString(5,extraCovers);
						else
							pre.setString(5,"");

						pre.setString(6,"Y");
						pre.setString(7,effectiveDate);
						pre.setString(8,(String)modeValues.get("cover-type"+i));
						pre.addBatch();
					}
				}
				updateCounts = pre.executeBatch();
			}
			catch(Exception e1)
			{
				System.out.println("ddddddddddddd---ERROR----->"+e1.toString());
				e1.printStackTrace();
			}
			//Cover Insertion
			
			//Country Insertion 
			try
			{
					pre.clearBatch();
					pre=null;

					String war_rate=runner.singleSelection("select war_rate from open_cover_country_master where proposal_no='"+proposalNo+"' and amend_id=(select max(amend_id) from open_cover_country_master where proposal_no='"+proposalNo+"')");
					
					qry="insert into open_cover_country_master (open_cover_country_id,proposal_no,amend_id,open_cover_country_id_org,open_cover_country_id_dest,effective_date,status,war_rate)values((select nvl(max(open_cover_country_id)+1,'1') from open_cover_country_master),?,(select nvl(max(amend_id)+1,'0') from open_cover_country_master where proposal_no='"+proposalNo+"'),?,?,?,?,?)";
					pre=cont.prepareStatement(qry);
					pre.setString(1,proposalNo);
					pre.setString(2,transit_countryId);
					pre.setString(3,destination_countryId);
					pre.setString(4,effectiveDate);
					pre.setString(5,"Y");
					System.out.println("  war_rate-------->"+war_rate);
					pre.setString(6,war_rate);

					pre.executeUpdate();
					
			}
			catch(Exception e)
			{
				System.out.println(" ERROR in Country Insertion---->"+e.toString());
				e.printStackTrace();
			}
			//Country Insertion
			
			// Sale Term
			try
			{
					pre.clearBatch();
					pre=null;
					qry="insert into OPEN_COVER_SALE_TERM_MASTER (PROPOSAL_NO,SALE_TERM_ID,AMEND_ID,STATUS,EFFECTIVE_DATE,TOLERANCE_ID,TOLERANCE_NAME) values(?,?,(select nvl(max(amend_id)+1,'0') from open_cover_sale_term_master where proposal_no='"+proposalNo+"'),?,?,?,?)";
					pre=cont.prepareStatement(qry);
					pre.setString(1,proposalNo);
					pre.setString(2,saleTermId);
					pre.setString(3,"Y");
					pre.setString(4,effectiveDate);
					pre.setString(5,toleranceId);
					pre.setString(6,toleranceName);
					pre.executeUpdate();
			}
			catch(Exception e)
			{
				System.out.println(" ERROR in Sale Term Insertion---->"+e.toString());
				e.printStackTrace();
			}
			// Sale Term

			//Commodity  Insertion 
			try
			{
				qry="insert into open_cover_commodity_master(proposal_no,amend_id,commodity_id,commodity_name_desc,fragile,effective_date,status,commodity_type_id)values(?,(select nvl(max(amend_id)+1,'0') from open_cover_commodity_master where proposal_no='"+proposalNo+"'),?,?,?,?,?,?)";
			
				pre.clearBatch();
				pre = null;
					
				pre = cont.prepareStatement(qry);
				for(int i=0;i<commodities.length;i++)
				{
					if(commodities[i][0]!=null ||!"null".equalsIgnoreCase(commodities[i][0]))
					{
						pre.setString(1,proposalNo);
						pre.setString(2,commodities[i][0]);
						if(commodities[i][1]!=null ||commodities[i][1].length()>1)
							commodities[i][1].replaceAll("'","''");
						pre.setString(3,commodities[i][1]);
						pre.setString(4,("selected".equalsIgnoreCase(commodities[i][2])?"on":"off"));
						pre.setString(5,effectiveDate);
						pre.setString(6,"Y");
						pre.setString(7,commodities[i][3]);
						pre.addBatch();
					}
				}
				updateCounts = pre.executeBatch();
			}
			catch(Exception e)
			{
				System.out.println(" ERROR in Country Insertion---->"+e.toString());
				e.printStackTrace();
			}
			//Commodity Insertion
		finally
		{
			try
			{
				pre.clearBatch();
				if(pre!=null);
					pre.close();
				if(cont!=null)
					cont.close();
			}
			catch(Exception e)
			{
				System.out.println( "ERRROR IN FINALLY BLOCK  "+e.toString());
				e.printStackTrace();
			}
		 }
		} // if
		}
		catch(Exception e)
		{
			System.out.println(" ERRROR in storeValues----------->"+e.toString());
			e.printStackTrace();
		}
		return "success";
	}
	

	public String[][] getCommodityTypeId(String branch)
	{
		String commType[][] = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try
		{
			args[0] = branch;
			qry = "select CATEGORY_DETAIL_ID,DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID=42 and STATUS='Y' and branch_code=?";
			commType = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception is getCommodityTypeId "+e.toString());
			e.printStackTrace();
		}
		return commType;
	}
	
	public String getWSRCOpt(String proNo)
	{
		String sql= ""; 
		String args[] = new String[2];
		String res = "";
		try
		{
			args[0] = proNo;
			args[1] = proNo;
			sql = "select nvl(wrsc_YN,'N') from OPEN_COVER_MASTER where PROPOSAL_NO=? and amend_id=(select max(amend_id) from open_cover_master where proposal_no=?)";
			res="N";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getDefaultClauses(String proNo)
	{
		String sql= ""; 
		String args[] = new String[2];
		String res = "";
		try
		{
			args[0] = proNo;
			args[1] = proNo;
			sql = "select nvl(CLAUSES_YN,'N') from OPEN_COVER_MASTER where PROPOSAL_NO=? and amend_id=(select max(amend_id) from open_cover_master where proposal_no=?)";
			res="N";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getFreeTextStatus(String proNo)
	{
		String sql= ""; 
		String args[] = new String[2];
		String res = "";
		try
		{
			args[0] = proNo;
			args[1] = proNo;
			sql = "select nvl(FREE_TEXT_ALLOWED,'N') from OPEN_COVER_MASTER where PROPOSAL_NO=? and amend_id=(select max(amend_id) from open_cover_master where proposal_no=?)";
			res="N";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	
	public String getValueBasis(String proposalNo,String branch)
	{
//		String args[] = new String[0];
		String sql = "";
		String valueBasis = "";
//		String stId = "";
		try{
			/*args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			sql = "select sale_term_id from open_cover_sale_term_master where proposal_no=? and PROPOSAL_NO||amend_id in(select PROPOSAL_NO||max(amend_id) from open_cover_sale_term_master where PROPOSAL_NO=? group by PROPOSAL_NO)";
			stId = runner.singleSelection(sql, args);
			args = new String[2];
			args[0] = "Y";
			args[1] = branch;
			sql="SELECT SUBSTR (SYS_CONNECT_BY_PATH (SALE_TERM_NAME , ','), 2) csv FROM (SELECT SALE_TERM_NAME , ROW_NUMBER () OVER (ORDER BY SALE_TERM_NAME ) rn,COUNT (*) OVER () cnt  from sale_term_master where sale_term_id in("+stId+") and status=? and branch_code=? ) WHERE rn = cnt START WITH rn = 1 CONNECT BY rn = PRIOR rn + 1";
			valueBasis = runner.singleSelection(sql,args);*/
			sql="SELECT CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('basisofvaluation',?,?) FROM DUAL)";
			valueBasis = runner.singleSelection(sql,new String[]{proposalNo,branch});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return valueBasis;
	}
	public String[][] getOpenCoverDate(String proposalNo)
	{
		String[][] date=runner.multipleSelection("SELECT TO_CHAR(POLICY_START_DATE,'dd') AS DAYS,TO_CHAR(POLICY_START_DATE,'mm') AS MONTHS,TO_CHAR(POLICY_START_DATE,'YYYY') AS YEARS,TO_CHAR(POLICY_END_DATE,'dd') AS DAYS,TO_CHAR(POLICY_END_DATE,'mm') AS DAYS,TO_CHAR(POLICY_END_DATE,'YYYY') AS YEARS FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)", new String[]{proposalNo, proposalNo});
		return date;
	}
	public String[][] getAdditionalInsuredInfo(String proNo)
	{
		String sql= ""; 
		String args[] = new String[2];
		String result[][] = new String[0][0];
		try
		{
			args[0] = proNo;
			args[1] = proNo;
			sql = "SELECT BROKER_ID,ADDITIONAL_INSURED,ADDITIONAL_INSURED_NAME,CUSTOMER_ID FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public Map getConveyance(String proposalNo)
	{
		String sql= ""; 
		String result[][] = new String[0][0];
		Map ModeDetails = new HashMap();
		try
		{
			sql = "SELECT A.MODE_TRANSPORT_ID,A.CONVEYANCE FROM OPEN_COVER_DETAIL A WHERE A.PROPOSAL_NO=? AND A.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
			result = runner.multipleSelection(sql,new String[]{proposalNo});
			for(int i=0;i<result.length;i++){
				ModeDetails.put(result[i][0], result[i][1]);
			}
	        return ModeDetails;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ModeDetails;
	}
	public String[][] getEndtTypeInfo(String proposalNo)
	{
		String sql="SELECT ORIGINAL_POLICY_NO, ENDT_TYPE FROM OPEN_COVER_POSITION_MASTER WHERE PROPOSAL_NO=?";
		String result[][]=runner.multipleSelection(sql, new String[]{proposalNo});
		return result;
	}
	public static void updateEndtType(String proposalNo, String endtType)
	{
		logger.info("updateEndtType - Enter || proposalNo: "+proposalNo+" endtType: "+endtType);
		String sql="UPDATE OPEN_COVER_POSITION_MASTER SET ENDT_TYPE=? WHERE PROPOSAL_NO=?";
		runner.multipleUpdation(sql, new String[]{endtType,proposalNo});
		logger.info("updateEndtType - Exit");
	}
	public String[][] getProposalStatus(String proposalNo)
	{
		String sql =""; 
		String res[][] = new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = proposalNo;
			args[1] = proposalNo;
			sql = "SELECT NVL(PROPOSAL_STATUS,'Normal'),NVL(CONFIRM_STATUS,'NO') FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)";
			res = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public static void updateApprovedStatus(String proposalNo,String status)
	{
		logger.info("updateApprovedStatus - Enter || proposalNo: "+proposalNo);
		String sql="update open_cover_master set PROPOSAL_STATUS=? where proposal_no=? and amend_id=(select max(amend_id) from open_cover_master where proposal_no=?)";
		runner.multipleUpdation(sql, new String[]{status,proposalNo,proposalNo});
		logger.info("updateApprovedStatus - Exit");
	}
	public static void updateConfirmStatus(String proposalNo,String status)
	{
		logger.info("updateConfirmStatus - Enter || proposalNo: "+proposalNo);
		String sql="update open_cover_master set CONFIRM_STATUS=? where proposal_no=? and amend_id=(select max(amend_id) from open_cover_master where proposal_no=?)";
		runner.multipleUpdation(sql, new String[]{status,proposalNo,proposalNo});
		logger.info("updateConfirmStatus - Exit");
	}
	public String[][]  getSelCommodityTypeId(String propsalNo,String comIds)
	{
		String commType[][] = new String[0][4];
		String qry = "";
		String args[] = new String[1];
		List<String[]> result=null; 
		try
		{
			//qry = "select COMMODITY_TYPE_ID,FRAGILE,COMMODITY_NAME_DESC,COMMODITY_ID from open_cover_commodity_master where PROPOSAL_NO="+propsalNo+" and COMMODITY_ID in ("+comIds+")  and AMEND_ID=(  select max(AMEND_ID) from open_cover_commodity_master  where  PROPOSAL_NO="+propsalNo+" and COMMODITY_ID in ("+comIds+"))";
			qry="SELECT COMMODITY_ID,COMMODITY_NAME_DESC,FRAGILE,COMMODITY_TYPE_ID ,'OP' FROM OPEN_COVER_COMMODITY_MASTER OCCM WHERE  PROPOSAL_NO="+propsalNo+" and OCCM.Amend_id=(select max(amend_id) from OPEN_COVER_COMMODITY_MASTER OC where  occm.proposal_no=oc.proposal_no) and   COMMODITY_ID IN (SELECT COMMODITY_ID FROM COMMODITYMASTER  cm where cm.amend_id=(Select max(amend_id) from commoditymaster c where c.commodity_id=cm.commodity_id and status='Y')) union all SELECT COMMODITY_ID,COMMODITY_NAME,FRAGILE,COMMODITY_TYPE_ID,'COM' FROM commoditymaster CM where status='Y'and COMMODITY_ID not in (SELECT COMMODITY_ID FROM OPEN_COVER_COMMODITY_MASTER OCCM where PROPOSAL_NO="+propsalNo+" and OCCM.Amend_id=(select max(amend_id) from OPEN_COVER_COMMODITY_MASTER OC where occm.proposal_no=oc.proposal_no)) and cm.amend_id=(Select max(amend_id) from  commoditymaster c where c.commodity_id=cm.commodity_id and status='Y')";
			commType = runner.multipleSelection(qry); 
			 
		}
		catch(Exception e)
		{
			System.out.println("Exception is getCommodityTypeId "+e.toString());
			e.printStackTrace();
		}
		return commType;
	}
	public void saveCommodity(String proposalNo,String[][] commodities){
		String qry="0";
		java.sql.PreparedStatement pre=null;
		java.sql.Connection cont=null;
		int [] updateCounts = new int[0];
		cont = com.maan.DBCon.DBConnection.getInstance().getDBConnection();
		String effectiveDate = ""+com.maan.common.util.OracleDateConversion.ConvertDate(effectiveDay+"-"+effectiveMonth+"-"+effectiveYear);
		try
		{
			qry="insert into open_cover_commodity_master(proposal_no,amend_id,commodity_id,commodity_name_desc,fragile,effective_date,status,commodity_type_id)values(?,(select nvl(max(amend_id)+1,'0') from open_cover_commodity_master where proposal_no='"+proposalNo+"'),?,?,?,?,?,?)";
		
			pre.clearBatch();
			pre = null;
				
			pre = cont.prepareStatement(qry);
			for(int i=0;i<commodities.length;i++)
			{
				if(commodities[i][0]!=null ||!"null".equalsIgnoreCase(commodities[i][0]))
				{
					pre.setString(1,proposalNo);
					pre.setString(2,commodities[i][0]);
					if(commodities[i][1]!=null ||commodities[i][1].length()>1)
						commodities[i][1].replaceAll("'","''");
					pre.setString(3,commodities[i][1]);
					pre.setString(4,("selected".equalsIgnoreCase(commodities[i][2])?"on":"off"));
					pre.setString(5,effectiveDate);
					pre.setString(6,"Y");
					pre.setString(7,commodities[i][3]);
					pre.addBatch();
				}
			}
			updateCounts = pre.executeBatch();
		}
		catch(Exception e)
		{
			System.out.println(" ERROR in Country Insertion---->"+e.toString());
			e.printStackTrace();
		}finally {
			if(cont!=null) {
				try {
					cont.close();
					System.out.println("saveCommodity() connection is closed"+new Date());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public void insertDefaultClauses(String proposalNo, String branchCode) {
		CallableStatement cstmt = null;
		Connection con = null;
		try {
			con = DBConnection.getInstance().getDBConnection();
			cstmt = con.prepareCall("call CLAUSES_INSERT(?,?)");
			cstmt.setString(1, proposalNo);		
			cstmt.setString(2, branchCode);
			System.out.println("procedure execute");
			cstmt.execute();
			System.out.println("procedure execute success");
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
	}
}// Class