package com.maan.premium.DAO;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class PremiumLogic extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(PremiumLogic.class);

	private String sqlQuery_ = "";
	private String applicationNo = "";
	private String loginCode = "";
	private String sessionId = "";
	private String productId = "";
	private String companyId = "";
	private String stageId = "";
	private String fromCountryName = "";
	private String fromCityName = "";
	private String toCountryName = "";
	private String viaCountryName = "";
	private String toCityName = "";
	private String viaCityName = "";
	private String openCoverNo = "";
	private String cid = "";
	private String loginBra	= "";
   
	public void setLoginBra(String loginBra){
		this.loginBra=loginBra;
	}
	public String getLoginBra(){
		return this.loginBra;
	}
	public void setCid(String cid){
		this.cid=cid;
	}
	public String getCid(){
		return this.cid;
	}

    public void setOpenCoverNo(String openCoverNo)
	{
		this.openCoverNo=openCoverNo;
	}

	public String getOpenCoverNo()

	{
		return openCoverNo;
	}

	public void print(String methodName,String information,String type)
	{
		System.out.println(methodName+"<--->"+type+"<---->"+information);
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

	/**
	 * @return Returns the applicationNo.
	 */
	public String getApplicationNo() {
		return applicationNo;
	}



	/**
	 * @param applicationNo The applicationNo to set.
	 */
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}



	/**
	 * @return Returns the companyId.
	 */
	public String getCompanyId() {
		return companyId;
	}



	/**
	 * @param companyId The companyId to set.
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}



	/**
	 * @return Returns the loginCode.
	 */
	public String getLoginCode() {
		return loginCode;
	}



	/**
	 * @param loginCode The loginCode to set.
	 */
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}



	/**
	 * @return Returns the productId.
	 */
	public String getProductId() {
		return productId;
	}



	/**
	 * @param productId The productId to set.
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}



	/**
	 * @return Returns the sessionId.
	 */
	public String getSessionId() {
		return sessionId;
	}



	/**
	 * @param sessionId The sessionId to set.
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}



	/**
	 * @return Returns the stageId.
	 */
	public String getStageId() {
		return stageId;
	}



	/**
	 * @param stageId The stageId to set.
	 */
	public void setStageId(String stageId) 
	{
		this.stageId = stageId;
	}
	public String totalValue(String query)
	{
		String returnVal="";
		try
		{
			returnVal = runner.singleSelection(query);
		}
		catch(Exception e)
		{
			System.out.println("Error in "+e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String totalValue(String query,String[] argss)
	{
		String returnVal="";
		try
		{
			returnVal = runner.singleSelection(query,argss);
		}
		catch(Exception e)
		{
			System.out.println("Error in "+e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public void updateMarineDatas(String premium,String total_war_premium,String toal_sale_term,String remarks,String discounts,String excess)
	{
		String qry="";
		
		if("discount".equalsIgnoreCase(discounts))
		{
			qry=" , DISCOUNT_PREMIUM='"+excess+"'";
		}
		else if("loading".equalsIgnoreCase(discounts))
		{
			qry=", EXCESS_PREMIUM='"+excess+"'";
		}
		try
		{
			// Rounding the premium
			/*premium = Double.toString(Math.round(Double.parseDouble(premium)));
			total_war_premium = Double.toString(Math.round(Double.parseDouble(total_war_premium)));*/
			policyInfo pol =new policyInfo();
			boolean GHQOACode = pol.getGHQOACode(loginCode);
						
			premium = new PremiumInputsBean().getRoundedValue(premium, "1",GHQOACode);
			total_war_premium = new PremiumInputsBean().getRoundedValue(total_war_premium, "1",GHQOACode);
			
			System.out.println("Premium Logic - Rounding Option premium  "+premium);
			System.out.println("Premium Logic - Rounding Option total_war_premium  "+total_war_premium);
			String sql="";
			if("admin".equalsIgnoreCase(remarks))
				sql="update marine_data set premium=round('"+premium+"'+(nvl(POLICY_FEE,'0')+nvl(GOVT_TAX,'0')+nvl(EMERGENCY_FUND,'0')),2),total_war_charges='"+total_war_premium+"'," +
						"total_sale_term_charges='"+toal_sale_term+"'  "+qry+" where application_no='"+applicationNo+"'";
			else
				sql="update marine_data set premium=round('"+premium+"'+(nvl(POLICY_FEE,'0')+nvl(GOVT_TAX,'0')+nvl(EMERGENCY_FUND,'0')),2)," +
						"remarks='"+remarks+"',total_war_charges='"+total_war_premium+"',total_sale_term_charges='"+toal_sale_term+"' " +
								"where application_no='"+applicationNo+"'";
			
			runner.updation(sql);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getUSDValue()
	{
		String args[] = new String[2];
		String temp = "";
		String sql="";
		
		args[0] = cid;
		args[1] = cid;
		sql = "select exchange_rate from exchange_master where currency_id=4 and status='Y' and COUNTRY_ID=? and amend_id=(select max(amend_id) from exchange_master  where currency_id=4 and status='Y' and COUNTRY_ID=?)";

		try
		{
			temp = runner.singleSelection(sql,args);
			if(temp.length()<=0)
				temp="0";
			sql=""+Double.parseDouble(temp);
		}
		catch(Exception e)
		{
			sql=""+0;e.printStackTrace();
		}
		return sql;
	}

	public String getCityNameT(String applicationNo,String countryIdValue)
	{
		String CityName ="";
		String args[] = new String[1];
		
		try
		{
			args[0] = applicationNo;
			String ss="select nvl(transit_from,' ') from marine_data where application_no=?";
			CityName = runner.singleSelection(ss,args);
			if(CityName.length() <= 0)
				CityName="";
		}
		catch(Exception e)
		{
			System.out.println("getCityNameT "+e);
			e.printStackTrace();
		}
		return CityName;
	}

	public String getCityNameF(String applicationNo)
	{
		String CityName ="";
		
		String args[] = new String[1];
		try
		{
			args[0] = applicationNo;
			String ss="select nvl(final_destination,' ') from marine_data where  application_no=?";
			CityName = runner.singleSelection(ss,args);
			if(CityName.length()<=0)
				CityName="";
		}
		catch(Exception e)
		{
			System.out.println("getCityNameF>>>"+e);
			e.printStackTrace();
		}
		return CityName;
	}

	public void updatePositionMaster(String adminRemarks,String discounts,String percentage,String excess)
	{
		
		if(!"discount".equalsIgnoreCase(discounts) && !"loading".equalsIgnoreCase(discounts))
		{	
			discounts="";percentage="0";
		}
		try
		{
			System.out.println("  "+Double.parseDouble(percentage));
		}
		catch(Exception e)
		{
			percentage="0";
		}
		String qry="";
		if("discount".equalsIgnoreCase(discounts))
		{
			qry="DISCOUNT_PREMIUM='"+excess+"'";
		}
		else if("loading".equalsIgnoreCase(discounts))
		{
			qry="EXCESS_PREMIUM='"+excess+"'";
		}
		runner.updation("update position_master set admin_remarks='"+(adminRemarks.replaceAll("'","''"))+"' where application_no='"+applicationNo+"'");
		//hidded by Rajesh becuase if we entered referralpremium page it self it is approved boz of below code
		//runner.updation("update marine_data set admin_referral_status='N' where application_no='"+applicationNo+"'"); // ramrks='admin'
	}

	public String getLoginId()
	{
		String sql = "";
		String loginId = "";
		
		String args[] = new String[1];
		try
		{
			args[0] = applicationNo;
			sql = "select nvl(login_id,' ') from position_master where application_no=?";
			loginId = runner.singleSelection(sql,args);

		}
		catch(Exception e)
		{
			System.out.println("getLoginId...."+e);
				e.printStackTrace();
		}
		return loginId;
	}

	public String getNames(String cusId)
	{
		String[][] result=new String[0][0];
		String resultFinal="";
		
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = cusId;
			sql = "select first_name ,last_name from personal_info where customer_id=?";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getNames...."+e);
			e.printStackTrace();
		}
		if(result.length>0)
			resultFinal = result[0][0]+" "+result[0][1];
		return resultFinal;
	}

	public String[][] getsCoutrys()
	{
		String[][] getsCountry=new String[0][0];
		
		String sql = "";
		try
		{
			sql = "select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			getsCountry = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("exception in coutnry Master"+e.toString());
			e.printStackTrace();
		}
		return getsCountry;
	}

	// new version pendings are below.....

	public String[][] getsCoutrys(String CountryStatus)
	{
		String[][] getsCountry=new String[0][0];
		HashMap finalList=new HashMap();
		String finalStringList="";
		
		try
		{
			finalList=(HashMap)getCountries(CountryStatus);
			if(finalList.size()>0)
			{
				finalStringList=(String)finalList.get("finalCountries")==null?"0":(String)finalList.get("finalCountries");
			}
			else
			{
				finalStringList="0";
			}
			if("11".equalsIgnoreCase(productId))
			{
				getsCountry = runner.multipleSelection("select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm,open_cover_country_master occm where cm.status in ('Y','R') and occm.status='Y' and  cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')  and cm.country_id in("+finalStringList+") group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME");
			}
			else if("3".equalsIgnoreCase(productId))
			{
				getsCountry = runner.multipleSelection("select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME");
			}
		}
		catch(Exception e)
		{
			System.out.println("exception in coutnry Master EEEE"+e.toString());
		}
		return getsCountry;
	}

public HashMap getCountries(String CountryStatus)
{
		String[][] getsCheckCountry=new String[0][0];
		String[][] getsCountry=new String[0][0];
		String countryNewlyAdded=""; 
		String countryAdded=""; 
		String countryAddedRemoved=""; 
		String countryFinalList=""; 
		String countryCheckQuery=""; 
		String countryQuery=""; 
		HashMap getConstantCountriesList=new HashMap();
		
		String[] countryChecking={"65","121"};
		String opensCountry[][] = new String[0][0];
		
		opensCountry = getOpenCoverCountry(CountryStatus);
		if(opensCountry.length>0)
			countryAdded=opensCountry[0][0]!=null?opensCountry[0][0]:"";

		getConstantCountriesList.put("fromOpenCoverMaster",countryAdded);
		
		try
		{
			for (int i=0;i<countryChecking.length ;i++ )
			{
				countryCheckQuery="select count(*) from country_master_detail where country_id in("+countryAdded+") and country_id in("+countryChecking[i]+") and BELONGING_COUNTRY_ID='"+cid+"'";
				getsCheckCountry = runner.multipleSelection(countryCheckQuery);
				if(getsCheckCountry.length > 0)
				{
					if(Integer.parseInt(getsCheckCountry[0][0]==null?"0":getsCheckCountry[0][0]) > 0)
					{	
						getsCountry=new String[0][0];
						countryQuery="select rsacode,detail_name from constant_detail where category_id=(select category_id from constant_master where rsacode=('"+countryChecking[i]+"') and branch_code='"+loginBra+"') and branch_code='"+loginBra+"' order by rsacode";
						getsCountry = runner.multipleSelection(countryQuery);
						if(getsCountry.length > 0)
						{
							//For Removing the Country from List Starts
							StringTokenizer stRemover=new StringTokenizer(countryAdded,",");
							String removeCheck="";
							while(stRemover.hasMoreTokens())
							{
								removeCheck=stRemover.nextToken();
								if(removeCheck.equalsIgnoreCase(countryChecking[i]))
								{
									System.out.println("the Coountry Id Removed is "+removeCheck);
								}else
								{
									countryAddedRemoved=countryAddedRemoved+removeCheck+",";
								}
							}

							for (int k=0;k<getsCountry.length ;k++ )
							{
								countryNewlyAdded=countryNewlyAdded+getsCountry[k][0]+",";	
								getConstantCountriesList.put(countryChecking[i],getsCountry[k][0]);
							}
						}
					}
				}
			}
			if(countryAddedRemoved.length() > 0 )
			{
				countryAddedRemoved=countryAddedRemoved.substring(0,countryAddedRemoved.length()-1);
				countryAdded=countryAddedRemoved;
			}
			if("65".equalsIgnoreCase(countryAdded) || "121".equalsIgnoreCase(countryAdded) ||"65,121".equalsIgnoreCase(countryAdded) || "121,65".equalsIgnoreCase(countryAdded))
			{
				countryAdded="0";
			}
			countryFinalList=countryNewlyAdded+countryAdded;
			
			System.out.println("The Final List FINALLY IS--------------->>>>"+countryFinalList);

			if(countryNewlyAdded.length() > 0 )
			{
				countryNewlyAdded=countryNewlyAdded.substring(0,countryNewlyAdded.length()-1);
			}else
			{
				countryNewlyAdded="0";
			}
			getConstantCountriesList.put("fromConstantCountries",countryNewlyAdded);
			getConstantCountriesList.put("finalCountries",countryFinalList);
		}
		catch(Exception e)
		{
			System.out.println("The geting Countries -->>"+e);
			e.printStackTrace();
		}
		return getConstantCountriesList;
	}

	public String[][] getOpenCoverCountry(String status)
	{
		String[][] getOpenCoverCountry=new String[0][0];
		String openCoverCountryQuery="";
		
		String args[] = new String[2];
		try
		{
			if("Origination".equalsIgnoreCase(status))
			{
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				openCoverCountryQuery="select occmss.OPEN_COVER_COUNTRY_ID_ORG from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			}
			else if ("Destination".equalsIgnoreCase(status))
			{
				args[0] = openCoverNo;
				args[1] = openCoverNo;
				openCoverCountryQuery="select occmss.OPEN_COVER_COUNTRY_ID_DEST from open_cover_country_master occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no =? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_country_master occms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			}

			System.out.println("comes here "+openCoverNo+" openCoverCountryQuery TWO @@@@@"+openCoverCountryQuery);
			getOpenCoverCountry = runner.multipleSelection(openCoverCountryQuery,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in  get OpenCoverCountry--------------->>>>"+e);
			e.printStackTrace();
		}
		return getOpenCoverCountry;
	}

	public String[][] getsCitys(String countryId)
	{
		String[][] getsCountry=new String[0][0];
		String args[] = new String[1];
		String sql = "";
		
		try
		{
			args[0] = countryId;
			sql = "select city_id,city_name from city_master where country_id=? and status='Y' and city_name not in ('others') order by city_name";
			getsCountry = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The Get City Value Is ->"+e);
			e.printStackTrace();
		}
		return getsCountry;
	}

	public String[][] getsLCDetails(String bankId,String lcNo,String openCoverNo)
	{
		String[][] getsCountry=new String[0][0];
		String lcQuery="";
		String args[] = new String[3];
		
		try
		{
			args[0] = lcNo;
			args[1] = bankId;
			args[2] = openCoverNo;

			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,to_char(oplm.lc_date,'dd-mm-yyyy'),cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id,to_char(oplm.expiry_date,'dd-mm-yyyy') from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em where oplm.status='Y' and lc_id=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where to_char(cms.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where to_date(ocbms.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where to_char(ems.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";
			
			getsCountry = runner.multipleSelection(lcQuery,args);
			if(getsCountry.length>0)
			{

			}
			else
			{
				getsCountry=new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("The Get getsLCDetails Value Is -->>"+e);
			e.printStackTrace();
		}
		return getsCountry;
	}

	public String getStartingPlace(String cid)
	{
		String[][] result=new String[0][0];
		String res="";
		String args[] = new String[3];
		String sql = "";
		
		try
		{
			args[0] = applicationNo;
			args[1] = cid;
			args[2] = cid;
			sql = "select cm.starting_place,cm.ending_place from COUNTRY_MASTER_DETAIL cm where cm.country_id=(select transit_from_country_id from marine_data where application_no=?) and cm.BELONGING_COUNTRY_ID=? and cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') and BELONGING_COUNTRY_ID=? group by COUNTRY_ID) and 	to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')   order by cm.COUNTRY_NAME";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getStartingPlace...."+e);
			e.printStackTrace();
		}
		
		if(result.length>0)
			res = result[0][0]!=null?result[0][0]:"";
		return res;
	}
	
	public String getEndingPlace()
	{
		String[][] result=new String[0][0];
		String res="";
		
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = applicationNo;
			sql = "select cm.ending_place from country_master cm where cm.country_id=(select final_destination_country_id from marine_data where application_no=?) and cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')   order by cm.COUNTRY_NAME";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getEndingPlace .."+e);
			e.printStackTrace();
		}
		if(result.length>0)
			res = result[0][0]!=null?result[0][0]:"";
		return res;
	}
	
	public String[][] getStatusRemarks(String applicationNo)
	{
		String[][] statusRemarks=new String[0][0];
		
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = applicationNo;
			sql = "select STATUS,ADMIN_REMARKS from position_master where APPLICATION_NO=?";
			statusRemarks = runner.multipleSelection(sql,args);
		}catch(Exception e)
		{
			System.out.println("The Get City Value Is --------------->>>>"+e);
			e.printStackTrace();
		}
		return statusRemarks;
	}

	public String[][] getQuotePolicy(String applicationNo,String pid)
	{
		String[][] statusRemarks=new String[0][0];
		String args[] = new String[0];
		
		try
		{
			String sql = "";
			if("3".equalsIgnoreCase(pid))
			{
				args = new String[2];
				args[0] = applicationNo;
				args[1] = applicationNo;
				sql = "select QUOTE_NO,nvl(POLICY_NO,' '),nvl(pos.DISCOUNT_PREMIUM,lud.COMMISSION) from login_user_details lud,position_master pos where pos.product_id=lud.product_id and pos.application_no=? and lud.agency_code=(select oa_code from login_master where login_id=(select login_id from position_master where application_no=?) and status='Y')";
					statusRemarks = runner.multipleSelection(sql,args);
			}
			else
			{
				args = new String[3];
				args[0] = applicationNo;
				args[1] = applicationNo;
				args[2] = applicationNo;
				sql = "select pos.QUOTE_NO,nvl(pos.POLICY_NO,' '),nvl(pos.DISCOUNT_PREMIUM,ocm.commission) from open_cover_master ocm,open_cover_position_master ocpm,position_master pos where ocpm.open_cover_no =(select open_cover_no from position_master where application_no=?) and ocpm.proposal_no=ocm.proposal_no and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =(select open_cover_no from position_master where application_no=?) and pos.open_cover_no=ocpm.open_cover_no and pos.application_no=? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
				statusRemarks = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("The Get City Value Is -->>"+e);
			e.printStackTrace();
		}
		return statusRemarks;
	}

	public String getBName(String acNo)
	{
		String args[] = new String[1];
		String sql = "";
		String result = "";
		
		try
		{
			args[0] = acNo;
			sql = "select nvl(first_name,' ') from personal_info where agency_code=(select oa_code from LOGIN_EXECUTIVE_DETAILS where AC_EXECUTIVE_ID=?)";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get Name...."+e);
			e.printStackTrace();
		}
		return result;
	}	

	public String getCName(String appNo)
	{
		String args[] = new String[1];
		String sql = "";
		String result = "";
		
		try
		{
			args[0] = appNo;
			sql = "select nvl(first_name,COMPANY_NAME) from personal_info where customer_id=(select customer_id from position_master where application_no=?)";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get Name...."+e);
			e.printStackTrace();
		}
		return result;
	}

	public String setReject(String remarks)
	{
		String args[] = new String[2];
		String sql = "";
		String result = "";
		
		try
		{
			args[0] = remarks;
			args[1] = applicationNo;
			sql = "update position_master set admin_remarks=?,status='R',ENTRY_DATE=(select sysdate from dual) where application_no=?";
			result = runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("get Name...."+e);
			e.printStackTrace();
		}
		return result;
	}

	public String[][] getSeaTotal()
	{
		String getSeas[][]=new String[0][0];
		String sql = "";
		
		try
		{
			sql = "select category_detail_id,detail_name from constant_detail where category_id=2";
			getSeas = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getSeas;
	}

	public void updateRemarks(String remarks)
	{
		String args[] = new String[2];
		String sql = "";
		
		try
		{
			args[0] = remarks;
			args[1] = applicationNo;
			sql = "update position_master set remarks=? where application_no=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("updateRemarks..."+e);
			e.printStackTrace();
		}
	}

	public String getExcessDis(String quoteNo)
  	{
		String args[] = new String[1];
		String sql = "";
		
		String result = "";
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(EXCESS_PREMIUM,'0') from position_master where APPLICATION_NO=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getExcessDis...."+e);
			e.printStackTrace();
		}
         return result;
    }	

	public String getCustomerOrganization(String quoteNo)
   	{
		String args[] = new String[1];
		String sql = "";
		
		String result = "";
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(company_name,' ') from personal_info where customer_id=(select customer_id from position_master where quote_no=?)";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getCustomerOrganization...."+e);
			e.printStackTrace();
		}
         return result;
    }	

	public String[][] getPolicyDetails(String quoteNo)
	{
		String[][] PolicyDetails=new String[0][0];
		String sql = "";
		String args[] = new String[1];
		
		try
		{
			args[0] = quoteNo;
			sql = "select quote_no,BL_AWB_NO,to_char(BL_AWB_DATE,'dd-mm-yyyy'),settling_agent_id,carrier_name,LC_NUMBER,to_char(LC_DATE,'dd-mm-yyyy'),BANK_name from MARINE_POLICY_DETAILS where quote_no=?";
			PolicyDetails = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The Get City Value Is -->>"+e);
			e.printStackTrace();
		}
		return PolicyDetails;
	}

	public String getSettilingAgent(String no,String qno)
    {
		String sql = "";
		String args[] = new String[0];
		
		String name = "";
		try
		{
			if(no.length()>0&&!no.equals("0"))
			{
			   //args[0] = no;
			   sql = "select SETTLING_AGENT_NAME from SETTLING_AGENT_MASTER where SETTLING_AGENT_ID='"+no+"' and status='Y' ";
			}
			else
			{
				//args[0] = qno;
			  sql = "select SETTLING_AGENT_NAME from MARINE_POLICY_DETAILS where  quote_no='"+qno+"'";
			}
			name = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The getSettilingAgent -->>"+e);
			e.printStackTrace();
		}
		return name;
	}

	public String getAdminRemarks()
	{
		String sql = "";
		String args[] = new String[1];
		
		String remarks = "";
		try
		{
			args[0] = applicationNo;
			sql = "select nvl(admin_remarks,' ') from position_master where application_no=?";
			remarks = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The getSettilingAgent -->>"+e);
			e.printStackTrace();
		}
		return remarks;
	}
	
	public String[][] getLoadingAndDiscount(String quoteNo)
	{
		String sql = "";
		String args[] = new String[1];
		
		String[][] LDData=new String[0][0];
		try
		{
			args[0] = quoteNo;
			sql = "select LOADING_DISCOUNT,LOADING_DISCOUNT_VALUE,status from position_master where quote_no=?";
			LDData = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The Get getLoadingAndDiscount Value Is --------------->>>>"+e);
			e.printStackTrace();
		}
		return LDData;
	}	

	public String[][] getStatus(String quoteNo)
	{
		String sql = "";
		String args[] = new String[1];
		
		String as[][] = new String[0][0];
		try
		{
			args[0] = quoteNo;
			sql = "select STATUS,ADMIN_REMARKS from position_master where quote_no=?";
			as = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The Get getStatus ->>"+e);
			e.printStackTrace();
		}
        return as;
	}

	public String getDiscountLimit(String s)
	{
		String sql = "";
		String args[] = new String[1];
		
		String percent = "";
		int str=0;
		if(s.equals("-"))
		{
			str=2;
		}
		try
		{
			args[0] = ""+str;
			sql = "select percent from CONSTANT_DETAIL where CATEGORY_ID=(select CATEGORY_ID from CONSTANT_MAster where CATEGORY_NAME='PORTFOLIO') and CATEGORY_DETAIL_ID=?";
			percent = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The getDiscountLimit ->>"+e);
			e.printStackTrace();
		}
		return percent;
	}

	public String[][] getAdminReferalStatus()
    {
		String sql = "";
		String args[] = new String[1];
		String as[][] = new String[0][0];
        try
		{
			args[0] = applicationNo;
			sql = "select admin_referral_status,remarks from marine_data where application_no=?";
			as = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The getAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
        return as;
    }

	public void updateAdminReferalStatus()
    {
		String args[] = new String[1];
		String updateQuery="";
		try
		{
			args[0] = applicationNo;
			updateQuery = "update marine_data set admin_referral_status='N' where application_no=?";
			runner.multipleUpdation(updateQuery,args);
		}
		catch(Exception e)
		{
			System.out.println("The updateAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
 	 }

	public String[][] getBrokerName(String quoteNo)
    {
		String sql = "";
		String args[] = new String[1];
		
        String as[][] = new String[0][0];
		try
		{
			args[0] = quoteNo;
			sql = "SELECT a.customer_id,a.company_name,a.contact_person,a.address1,pi.email,a.phone,a.pobox from broker_company_master a,personal_info pi  where a.agency_code in (select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and a.customer_id=pi.customer_id";
			as = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The updateAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
        return as;
    }

	public String getReferalStatus(String quoteNo)
	{
		String sql = "";
		String args[] = new String[1];
		
		String remarks = "";
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(remarks,'Admin Referral') from marine_data where application_no=(select application_no from position_master where quote_no=?)";
			remarks = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("The updateAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
		return remarks;
	}

	//BELOW CODING IS FOR FREIGHT USERS ONLY

	public boolean getFreightForwardUser(String user,String applicationNo )
	{
		String query = "";
		String mess  = "";
		String args[] = new String[1];
		boolean result1 = false;
		String[][] result = new String[0][0];
		try
		{
			args[0] = user;
			query = "select FREIGHT_FORWARD_USER from personal_info where agency_code =(select agency_code from login_master where login_id = ? and status='Y')and application_id=3 and status='Y'";
			result = runner.multipleSelection(query,args);   
			args[0] = applicationNo;
			query = "select freight_status from position_master where  application_no =?";
			mess = runner.singleSelection(query,args);
			mess=mess==null?"":mess;
			if(result != null && result.length > 0 )
			{
				String value = result[0][0];
				value= value == null?"":value;
				if(value.equalsIgnoreCase("FreightForward without Policy") && (mess.length() == 0  || mess.equalsIgnoreCase("F")))
					 result1 = true;
				else
					result1 = false;
			}      
		}
		catch(Exception e)
		{
			System.out.println("The updateAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
		return result1;
	}

	//For Freight Forwarder
	public boolean getFreightForwardUserold(String user)
	{
		
		String query = "";
		String[][] result = new String[0][0];
		String args[] = new String[1];
		boolean result1 = false;
		try
		{
			args[0] = user;
			query = "select FREIGHT_FORWARD_USER from personal_info where agency_code =(select agency_code from login_master where login_id = ? and status='Y')and application_id=3 and status='Y'";
			result = runner.multipleSelection(query,args);  
		}
		catch(Exception e)
		{
			System.out.println("The updateAdminReferalStatus ->>"+e);
			e.printStackTrace();
		}
	  
		if(result != null && result.length > 0)
		{
			String value = result[0][0];
			value= value == null?"":value;
			if(value.equalsIgnoreCase("FreightForward without Policy"))
				  result1 = true;
		}      
		return result1;
	}

	public void updateFreightStatus(String appNum)
	{
		String query = "";
		
		String args[] = new String[1];
		try
		{
			args[0] = appNum;
			query = "update position_master set FREIGHT_STATUS = 'F' where quote_NO=?";
			runner.multipleUpdation(query,args);
		}
		catch(Exception e)
		{
			System.out.println("updateFreightStatus   " + e);
			e.printStackTrace();
		}
	}

	public String getFreight()
	{
		
		String args[] = new String[1];
		String as="";
		String sql="";
		try
		{
			args[0] = applicationNo;
			sql = "select FREIGHT_STATUS from position_master where application_no=?";
			as = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("getFreight   " + e);
			e.printStackTrace();
		}
		return as;
	}

	public boolean getIsFreightUserQuote(String applicationNo )
	{
		 String mess = "";
		 String sql = "";
		 boolean result1 = false;
		 
		 String args[] = new String[1];
		 try
		 {
			 args[0] = applicationNo;
			 sql = "select freight_status from position_master where  application_no = ?";
			 mess = runner.singleSelection(sql,args);
		 }
		 catch(Exception e)
		 {
			System.out.println("getFreight   " + e);
			e.printStackTrace();
		 }
		 mess=mess==null?"":mess;
		 if(mess.equalsIgnoreCase("F"))
		 	  result1 = true;
		 else
		 	result1 = false;
		 
		 return result1;
	}
	
	public void updateFreightStatusAdmin(String qno,String status)
	{
		
		String query = "";
		String args[] = new String[2];
		try
		{
			args[0] = status;
			args[1] = qno;
			query = "update freight_position_master set STATUS = ? where quote_NO=?";
			runner.multipleUpdation(query,args);
		}
		catch(Exception e)
		{
			System.out.println("getFreight   " + e);
			e.printStackTrace();
		}
	}

	public String getFreightStatusAdmin(String qno)
	{
		String freight_staus = "";
		String query ="";
		String args[] = new String[1];
		
		try
		{
			args[0] = qno;
			query = "select freight_STATUS from position_master where quote_NO=?";
			freight_staus = runner.singleSelection(query,args);
		}
		catch(Exception e)
		{
			System.out.println("getFreightStatusAdmin   " + e);
			e.printStackTrace();
		}
		if(freight_staus==null)
			freight_staus = "";
		return freight_staus;
	}

	public String getFreightStatusAdminAppNo(String appno)
	{
		String freight_staus = "";
		String query = "";
		String args[] = new String[1];
		
		try
		{
			args[0] =appno;
			query = "select freight_STATUS from position_master where quote_NO=(select quote_NO from freight_position_master where application_id=?)";
			freight_staus = runner.singleSelection(query,args);
		}
		catch(Exception e)
		{
			System.out.println("getFreightStatusAdminAppNo   " + e);
			e.printStackTrace();
		}
		if(freight_staus==null)
			freight_staus = "";
		return freight_staus;
	}

	public void updateUnapproved(String appno)
	{
		String query = "";
		String remarksStatus = "";
		
		String args[] = new String[1];
		try
		{
			args[0] = appno;
			query = "select remarks from MARINE_DATA where application_no=? and remarks like '%Referal'";
			remarksStatus = runner.singleSelection(query,args);
			if(remarksStatus.length()<=0)
			{
				args[0] = appno;
				query = "update MARINE_DATA set ADMIN_REFERRAL_STATUS='Y' where application_no=?";
				runner.multipleUpdation(query,args);
				query = "update position_master set remarks='Normal' where application_no=?";
				runner.multipleUpdation(query,args);
			}
			else
			{
				args[0] = appno;
				query = "update position_master set remarks='Referal' where application_no=?";
				runner.multipleUpdation(query,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("updateUnapproved   " + e);
			e.printStackTrace();
		}
	}
//END OF FREIGHT CODING 

	public void updateAdminRefRemarks(String appno,String adminRefRemarks,String adminCommis,String adminLogin)
	{
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[4];
			args[0] = adminRefRemarks;
			args[1] = adminCommis;
			args[2] = adminLogin;
			args[3] = appno;
			sql = "update POSITION_MASTER set ADMIN_REFERRAL_REMARKS=?,DISCOUNT_PREMIUM=?,remarks='Admin',APPROVED_BY=(select nvl(username,' ') from login_master where login_id=?) where application_no=?";
			runner.multipleUpdation(sql,args);
			args = new String[1];
			args[0] = applicationNo;
			sql = "update marine_data set admin_referral_status='N' where application_no=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("update AdminRef Remarks   " + e);
			e.printStackTrace();
		}
	}

	public String getAdminRefRemarks(String appno)
	{
		String res="";
		String args[] = new String[1];
		String sql = "";
		
		try
		{
			args[0] = appno;
			sql = "select nvl(ADMIN_REFERRAL_REMARKS, ' ') from POSITION_MASTER where application_no=?";
			res = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("update Admin Ref Remarks   " + e);
			e.printStackTrace();
		}
		return res;
	}
	
	public void rejectRejectedStatus(String qno,String status)
	{
		String sql="";
		String args[] = new String[1];
		
		try
		{
			if(status.equalsIgnoreCase("accept"))
			{
				sql = "update position_master set status='Y',remarks='Admin' where quote_no=?";
			}
			else if(status.equalsIgnoreCase("reject"))
			{
				sql = "update position_master set status='R',remarks='Admin' where quote_no=?";
			}
			else if(status.equalsIgnoreCase("none"))
			{
				sql = "update position_master set status='Y',remarks='Referal' where quote_no=?";
			}
			args[0] = qno;
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("rejectRejectedStatus   " + e);
			e.printStackTrace();
		}
	}

	//Rajesh World work Started
	public String[][] getsCoutrys(String belongingId,String royal)
	{
		String[][] getsCountry=new String[0][0];
		
		String sql = "";
		try
		{

			sql = "select cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R') group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			getsCountry = runner.multipleSelection(sql);

		}
		catch(Exception e)
		{
			System.out.println("exception in coutnry Master"+e.toString());
			e.printStackTrace();
		}
		return getsCountry;
	}

	public HashMap getPremiumDetails(String applicationNo)
	{
		System.out.println(" 123getPremiumDetails  with application method ");
		HashMap premiumDetails=new HashMap();
		int finalCount=1;
		HashMap commodityDetails=new HashMap();
		HashMap countryDetails=new HashMap();
		String commNameCombined="";
		String commIdCombined="";
		String[][] premiumDetailsArray=new String[0][0];
		String argss[] = new String[3];
		
		try
		{
			argss[0] = applicationNo;
			argss[1] = loginBra;
			argss[2] = loginBra;
			sqlQuery_="select md.mode_of_transport,md.warehouse_to_warehouse,md.transit_from,md.final_destination,to_char(md.inception_date,'dd-MM-yyyy'),md.cover_id,md.extra_cover_id,md.total_sum_insured,md.equivalent_usd,md.premium,mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile,mrd.rate,mrd.premium,cm.cover_name,ecm.extra_cover_name,mt.transport_description,com.commodity_name, md.currency_name,md.exchange_rate,md.no_of_items,md.sale_term_id,md.AC_EXECUTIVE_ID,md.company_id,md.currency_id,to_char(md.inception_date,'dd') as policyDay,to_char(md.inception_date,'MM') as policyMonth,to_char(md.inception_date,'YYYY') as policyYear,md.AC_EXECUTIVE_NAME,stm.sale_term_value,stm.sale_term_name,md.transit_from_city_id,md.transit_from_country_id,md.final_destination_city_id,md.final_destination_country_id,mrd.sale_term_charges,mrd.war_charges,com.rag,com.exclusion_id,com.warranty_id,md.excess_premium,mrd.warrate,md.sea_options,md.ware_to_ware_final_dest ,md.discount_premium,nvl(mrd.sea_option_lcl,'0'),nvl(mrd.warehouse_warehouse,'0'),md.product_id,md.open_cover_no,md.open_bank_id,md.open_lc_id,md.open_vessel_name,md.open_vessel_option,mrd.COMMODITY_TYPE_ID,mrd.commodity_Excess_Premium,md.TOLERANCE_ID,mrd.TOLERANCE_CHARGES,md.PARTIAL_SHIPPING,md.PARTIAL_SUM_INSURED_AMOUNT,nvl(md.VIA_CITY_NAME,''),nvl(md.VIA_CITY_ID,'0'),nvl(md.VIA_COUNTRY_ID,'0'),nvl(md.remarks,'Normal'),com.status from marine_data md,marine_result_details mrd,cover_master cm,extra_cover_master ecm,mode_of_transport mt,commoditymaster com,sale_term_master stm where md.application_no=? and md.status='Y' and cm.status='Y' and mt.status='Y' and stm.status='Y' and md.application_no=mrd.application_no and md.cover_id=cm.cover_id and mt.mode_transport_id=md.mode_of_transport and com.commodity_id=mrd.commodity_id and md.extra_cover_id=ecm.extra_cover_id and stm.sale_term_id=md.sale_term_id and  com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and branch_code=? group by commodity_id) and cm.branch_code=ecm.branch_code and mt.branch_code=com.branch_code and com.branch_code=ecm.branch_code and com.branch_code=stm.branch_code and com.branch_code=? order by com.commodity_id";

			print("getPremiumDetails",sqlQuery_,"QUERY");
			try
			{
				premiumDetailsArray = runner.multipleSelection(sqlQuery_,argss);
				premiumDetails.put("premiumDetails",premiumDetailsArray);
			}
			catch(Exception e)
			{
				System.out.println("Exception in premiumLogin getpremiumdetails 1"+e.toString());
				e.printStackTrace();
			}
			if(premiumDetailsArray.length>0)
			{
				fromCountryName=getGeneralValue("country_name","country_master","country_id",premiumDetailsArray[0][35]);
				fromCityName=getGeneralValue("city_name","city_master","city_id",premiumDetailsArray[0][34]);
				toCountryName=getGeneralValue("country_name","country_master","country_id",premiumDetailsArray[0][37]);
				toCityName=getGeneralValue("city_name","city_master","city_id",premiumDetailsArray[0][36]);
				
				viaCountryName=getGeneralValue("country_name","country_master","country_id",(premiumDetailsArray[0][64]!=null?premiumDetailsArray[0][64]:"0"));
				viaCityName = premiumDetailsArray[0][62]!=null?premiumDetailsArray[0][62]:"";

				for(int i=0;i<premiumDetailsArray.length;i++)
				{
					premiumDetails.put("modeOfTransport",premiumDetailsArray[0][0]==null?"":premiumDetailsArray[0][0]);
					premiumDetails.put("coverId",premiumDetailsArray[0][5]==null?"":premiumDetailsArray[0][5]);
					premiumDetails.put("extraCoverId",premiumDetailsArray[0][6]==null?"":premiumDetailsArray[0][6]);
					premiumDetails.put("noOfItems",premiumDetailsArray[0][23]!=null?premiumDetailsArray[0][23]:"");
					premiumDetails.put("saleTermId",premiumDetailsArray[0][24]!=null?premiumDetailsArray[0][24]:"");
					premiumDetails.put("toleranceId",(premiumDetailsArray[i][58]!=null?premiumDetailsArray[i][58]:"0"));
					premiumDetails.put("accExecutiveId",premiumDetailsArray[0][25]!=null?premiumDetailsArray[0][25]:"");
					premiumDetails.put("companyId",premiumDetailsArray[0][26]!=null?premiumDetailsArray[0][26]:"");
					premiumDetails.put("currencyId",premiumDetailsArray[0][27]!=null?premiumDetailsArray[0][27]:"");
					premiumDetails.put("policyDay",premiumDetailsArray[0][28]!=null?premiumDetailsArray[0][28]:"");
					premiumDetails.put("policyMonth",premiumDetailsArray[0][29]!=null?premiumDetailsArray[0][29]:"");
					premiumDetails.put("policyYear",premiumDetailsArray[0][30]!=null?premiumDetailsArray[0][30]:"");
					premiumDetails.put("excessPremium",premiumDetailsArray[0][43]==null?"0":premiumDetailsArray[0][43]);
					premiumDetails.put("warRate",premiumDetailsArray[0][44]==null?"0":premiumDetailsArray[0][44]);
					premiumDetails.put("seaOptions",premiumDetailsArray[0][45]==null?"":premiumDetailsArray[0][45]);
					premiumDetails.put("wareHouse1",premiumDetailsArray[0][46]==null?"":premiumDetailsArray[0][46]);
					premiumDetails.put("discountPremium",premiumDetailsArray[0][47]==null?"":premiumDetailsArray[0][47]);
					premiumDetails.put("seaValue",premiumDetailsArray[0][48]==null?"0":premiumDetailsArray[0][48]);
					premiumDetails.put("warehouseValue",premiumDetailsArray[0][49]==null?"0":premiumDetailsArray[0][49]);
					premiumDetails.put("productId",premiumDetailsArray[0][50]==null?"0":premiumDetailsArray[0][50]);
					premiumDetails.put("openCoverNo",premiumDetailsArray[0][51]==null?"0":premiumDetailsArray[0][51]);
					premiumDetails.put("bankName",premiumDetailsArray[0][52]==null?"0":premiumDetailsArray[0][52]);
					premiumDetails.put("lcNumber",premiumDetailsArray[0][53]==null?"0":premiumDetailsArray[0][53]);
					premiumDetails.put("vesselName",premiumDetailsArray[0][54]==null?"":premiumDetailsArray[0][54]);
					premiumDetails.put("vesselOption",premiumDetailsArray[0][55]==null?"0":premiumDetailsArray[0][55]);
					premiumDetails.put("wsrccOption",premiumDetailsArray[0][6]==null?"0":premiumDetailsArray[0][6]);
					premiumDetails.put("Remarks",premiumDetailsArray[0][65]);
					try
					{
						premiumDetails.put("executiveName",premiumDetailsArray[0][31]!=null?premiumDetailsArray[0][31]:"None");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					premiumDetails.put("saleTermValue",premiumDetailsArray[0][32]!=null?premiumDetailsArray[0][32]:"");
					premiumDetails.put("saleTermName",premiumDetailsArray[0][33]!=null?premiumDetailsArray[0][33]:"None");
					premiumDetails.put("fromCountryName",fromCountryName);
					premiumDetails.put("fromCityName",fromCityName);
					premiumDetails.put("toCountryName",toCountryName);
					premiumDetails.put("toCityName",toCityName);
					
					premiumDetails.put("viaCountryName",viaCountryName);
					premiumDetails.put("viaCityName",viaCityName);
					
					countryDetails.put("transitCityId",premiumDetailsArray[0][34]!=null?premiumDetailsArray[0][34]:"");
					countryDetails.put("transitCountryId",premiumDetailsArray[0][35]!=null?premiumDetailsArray[0][35]:"");
					countryDetails.put("finalCityId",premiumDetailsArray[0][36]!=null?premiumDetailsArray[0][36]:"");
					countryDetails.put("finalCountryId",premiumDetailsArray[0][37]!=null?premiumDetailsArray[0][37]:"");
					countryDetails.put("viaCityId",premiumDetailsArray[0][63]!=null?premiumDetailsArray[0][63]:"0");
					countryDetails.put("viaCountryId",premiumDetailsArray[0][64]!=null?premiumDetailsArray[0][64]:"0");
					premiumDetails.put("fromCityId",((String)countryDetails.get("transitCityId")!=null?(String)countryDetails.get("transitCityId"):""));
					premiumDetails.put("tocityId",((String)countryDetails.get("finalCityId")!=null?(String)countryDetails.get("finalCityId"):""));
					premiumDetails.put("viaCityId",((String)countryDetails.get("viaCityId")!=null?(String)countryDetails.get("viaCityId"):""));
					premiumDetails.put("fromCountry",((String)countryDetails.get("transitCountryId")!=null?(String)countryDetails.get("transitCountryId"):""));
					premiumDetails.put("toCountry",((String)countryDetails.get("finalCountryId")!=null?(String)countryDetails.get("finalCountryId"):""));
					premiumDetails.put("viaCountryId",((String)countryDetails.get("viaCountryId")!=null?(String)countryDetails.get("viaCountryId"):""));
					premiumDetails.put("viacity",viaCityName);
					
					String fromcityyyyyyyy=(String)countryDetails.get("transitCityId");
					
					if(!fromcityyyyyyyy.equalsIgnoreCase("0"))
					{
						 String args1[] = new String[2];
						 args1[0] = (String)countryDetails.get("transitCityId");
						 args1[1] = (String)countryDetails.get("transitCountryId");
						String FCN = totalValue("select nvl(initcap(city_name),' ') from city_master where city_id=? and  country_id=?",args1);

						if(FCN.length()<=0)
							FCN = "";
						premiumDetails.put("fromcity",FCN);
						
					}
					else
					{
						String args1[] = new String[1];
						args1[0] = applicationNo;
						String ss=totalValue("select nvl(initcap(transit_from),' ') from marine_data where  application_no=? and TRANSIT_FROM_CITY_ID='0'",args1);
						if(ss.length()<=0)
							ss="";			
						premiumDetails.put("fromcity",ss);
					}
					String tocityyyyyy=(String)countryDetails.get("finalCityId");
					
					if(!tocityyyyyy.equalsIgnoreCase("0"))
					{
						String args1[] = new String[2];
						args1[0] = (String)countryDetails.get("finalCityId");
						args1[1] = (String)countryDetails.get("finalCountryId");

						String TC = totalValue("select nvl(initcap(city_name),' ') from city_master where city_id=? and country_id=?",args1);
						if(TC.length()<=0)
							TC = "";
						premiumDetails.put("tocity",TC);

					}
					else
					{
						String args1[] = new String[1];
						args1[0] = applicationNo;
						String ss=totalValue("select nvl(initcap(final_destination),' ') from marine_data where  application_no=? and FINAL_DESTINATION_CITY_ID='0'",args1);
						if(ss.length()<=0)
							ss="";
						premiumDetails.put("tocity",ss);
					}
				String args[] = new String[2];
				args[0] = (String)countryDetails.get("transitCountryId");
				args[1] = cid;
				
				String FN = totalValue("select nvl(initcap(country_name),' ') from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?",args);
				
				if(FN.length()<=0)
					FN = "";
				premiumDetails.put("fromcountryNames",FN);
				args = new String[2];
				args[0] = (String)countryDetails.get("finalCountryId");
				args[1] = cid;
				String CN = totalValue("select initcap(country_name) from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?",args);
				if(CN.length()<=0)
					CN = "";
				premiumDetails.put("tocountryNames",CN);
							
				countryDetails.put("transitFrom",fromCountryName+"#"+(premiumDetailsArray[0][35]!=null?premiumDetailsArray[0][35]:"")+"$"+fromCityName+"*"+(premiumDetailsArray[0][34]!=null?premiumDetailsArray[0][34]:"")+"~"+(premiumDetailsArray[0][2]!=null?premiumDetailsArray[0][2]:""));
				countryDetails.put("finalDestination",toCountryName+"#"+(premiumDetailsArray[0][37]!=null?premiumDetailsArray[0][37]:"")+"$"+toCityName+"*"+(premiumDetailsArray[0][36]!=null?premiumDetailsArray[0][36]:"")+"~"+(premiumDetailsArray[0][3]!=null?premiumDetailsArray[0][3]:""));

				premiumDetails.put("wareHouse",premiumDetailsArray[0][1]!=null?premiumDetailsArray[0][1]:"");
				premiumDetails.put("transitFrom",premiumDetailsArray[0][2]!=null?premiumDetailsArray[0][2]:"");
				premiumDetails.put("finalDestination",premiumDetailsArray[0][3]!=null?premiumDetailsArray[0][3]:"");
				premiumDetails.put("finalVia",premiumDetailsArray[0][62]!=null?premiumDetailsArray[0][62]:"");
				premiumDetails.put("inceptionDate",(premiumDetailsArray[0][4]!=null?premiumDetailsArray[0][4]:""));
				premiumDetails.put("totalSumInsured",premiumDetailsArray[0][7]);
				premiumDetails.put("equivalentUSD",(premiumDetailsArray[0][8]!=null?premiumDetailsArray[0][8]:""));
				premiumDetails.put("premium",(premiumDetailsArray[0][9]!=null?premiumDetailsArray[0][9]:"0"));
				premiumDetails.put("coverName",(premiumDetailsArray[0][17]!=null?premiumDetailsArray[0][17]:""));
				premiumDetails.put("extraCoverName",(premiumDetailsArray[0][18]!=null?premiumDetailsArray[0][18]:""));
				premiumDetails.put("transportName",(premiumDetailsArray[0][19]!=null?premiumDetailsArray[0][19]:""));


				premiumDetails.put("currencyName",(premiumDetailsArray[0][21]!=null?premiumDetailsArray[0][21]:""));
				policyInfo pol = new policyInfo();
				System.out.println("Login Code test:"+this.loginCode);
				boolean GHQOACode = pol.getGHQOACode(this.loginCode);
				System.out.println("GHQ Yes::"+GHQOACode);
				/*("Test Currency::"+ premiumDetails.get("currencyName").toString());
				if(premiumDetails.get("currencyName").toString().equalsIgnoreCase("USD DOLLAR") && GHQOACode)
				{
				  String exchangeRate = runner.singleSelection("select percent from constant_detail where category_id='134' and status='Y' and branch_code='"+loginBra+"'");
				  System.out.println("exchangeRate"+exchangeRate);
				  premiumDetails.put("exchangeRate",(exchangeRate!=null?exchangeRate:(premiumDetailsArray[0][22]!=null?premiumDetailsArray[0][22]:"0")));
				  
				}else{*/
				premiumDetails.put("exchangeRate",premiumDetailsArray[0][22]!=null?premiumDetailsArray[0][22]:"0");
				//}
				args = new String[1];
				args[0] = applicationNo;
				
				String CP = totalValue("select nvl(sum(sale_term_charges),'0') from marine_result_details where application_no=?",args);
				if(CP.length()<=0)
					CP = "0";
				premiumDetails.put("commodityPremium",CP);
				args = new String[1];
				args[0] = applicationNo;
				String WP = totalValue("select nvl(sum(war_charges),'0') from marine_result_details where application_no=?",args);
				if(WP.length()<=0)
					WP = "0";
				premiumDetails.put("WarPremium",new PremiumInputsBean().getRoundedValue(WP,"1",GHQOACode));
				args = new String[1];
				args[0] = applicationNo;
				String sil = totalValue("select nvl(sum(suminsuredlocal),'0') from marine_result_details where application_no=?",args);
				if(sil.length()<=0)
					sil = "0";
				premiumDetails.put("Total_AEDS",sil);
				
				String sumInsLF[][] = new String[0][0];
				String sumInsLocal = "";
				sumInsLocal = runner.singleSelection("select sum(nvl(SUMINSUREDLOCAL,0)) from marine_result_details where application_no=?", args);
				sumInsLF = runner.multipleSelection("select ("+sumInsLocal+")+nvl(md.TOTAL_TOLERANCE_CHARGES,0)+nvl(md.TOTAL_SALE_TERM_CHARGES,0), ((("+sumInsLocal+")+nvl(md.TOTAL_TOLERANCE_CHARGES,0)+nvl(md.TOTAL_SALE_TERM_CHARGES,0))/nvl(md.exchange_rate,1))from marine_result_details mrd,marine_data md where md.application_no=mrd.application_no and md.application_no=?", args);
				if(sumInsLF.length > 0 ){
					/*premiumDetails.put("sumInsL",sumInsLF[0][0]==null?"0":sumInsLF[0][0]);
					premiumDetails.put("sumInsF", sumInsLF[0][1]==null?"0":sumInsLF[0][1]);*/
					
					premiumDetails.put("sumInsL",new PremiumInputsBean().getRoundedValue((sumInsLF[0][0]==null?"0":sumInsLF[0][0]),"1",GHQOACode));
					premiumDetails.put("sumInsF", new PremiumInputsBean().getRoundedValue((sumInsLF[0][1]==null?"0":sumInsLF[0][1]),(String)premiumDetails.get("currencyId"),GHQOACode));
				}
				
				commNameCombined=commNameCombined+(premiumDetailsArray[i][20]!=null?premiumDetailsArray[i][20]:"")+",";
				commIdCombined=commIdCombined+(premiumDetailsArray[i][11]!=null?premiumDetailsArray[i][11]:"")+",";

				commodityDetails.put("commodityId"+(finalCount),(premiumDetailsArray[i][11]!=null?premiumDetailsArray[i][11]:""));	
				commodityDetails.put("commodity"+(finalCount),(premiumDetailsArray[i][20]!=null?premiumDetailsArray[i][20]:""));
				commodityDetails.put("sumInsured"+(finalCount),(premiumDetailsArray[i][12]!=null?premiumDetailsArray[i][12]:"0"));
				commodityDetails.put("description"+(finalCount),(premiumDetailsArray[i][13]!=null?premiumDetailsArray[i][13]:""));
				commodityDetails.put("fragile"+(finalCount),(premiumDetailsArray[i][14]!=null?premiumDetailsArray[i][14]:""));
				commodityDetails.put("saleTermCharges"+(finalCount),(premiumDetailsArray[i][38]!=null?premiumDetailsArray[i][38]:"0"));
				commodityDetails.put("tolCharges"+(finalCount),(premiumDetailsArray[i][59]!=null?premiumDetailsArray[i][59]:"0"));
				commodityDetails.put("warCharges"+(finalCount),(premiumDetailsArray[i][39]!=null?premiumDetailsArray[i][39]:"0"));
				commodityDetails.put("ragName"+(finalCount),(premiumDetailsArray[i][40]!=null?premiumDetailsArray[i][40]:""));
				commodityDetails.put("warRate"+(finalCount),(premiumDetailsArray[i][44]!=null?premiumDetailsArray[i][44]:"0"));
				commodityDetails.put("seaValue"+(finalCount),(premiumDetailsArray[i][48]!=null?premiumDetailsArray[i][48]:"0"));
				commodityDetails.put("warehouseValue"+(finalCount),(premiumDetailsArray[i][49]!=null?premiumDetailsArray[i][49]:"0"));
				commodityDetails.put("comType"+(finalCount),(premiumDetailsArray[i][56]!=null?premiumDetailsArray[i][56]:"0"));
				commodityDetails.put("EP"+(finalCount),(premiumDetailsArray[i][57]!=null?premiumDetailsArray[i][57]:"0"));
				
				finalCount=finalCount+1;
			}
				premiumDetails.put("commNameCombined",commNameCombined);
				premiumDetails.put("commIdCombined",commIdCombined);
				finalCount=finalCount-1;
				commodityDetails.put("finalCount",""+finalCount);
				premiumDetails.put("commodityDetails",commodityDetails);
				premiumDetails.put("countryDetails",countryDetails);
			}
		}
		catch(Exception exception)
		{
				System.out.println("Royal Test The Exception occured in premium.jsp--"+exception.toString());
				exception.printStackTrace();
		}
		System.out.println("premiumDetails......PremiumLOgic..."+premiumDetails.size());
		return premiumDetails;
	}

	public HashMap getPremiumDetails() // Quotation.jsp
	{
		System.out.println(" 123getPremiumDetails  without application method ");
		HashMap premiumDetails=new HashMap();
		int finalCount=1;
		HashMap commodityDetails=new HashMap();
		HashMap countryDetails=new HashMap();
		String commNameCombined="";
		String commIdCombined="";
		String[][] premiumDetailsArray=new String[0][0];
		
		String argss[] = new String[3];
		try
		{
			argss[0] = applicationNo;
			argss[1] = loginBra;
			argss[2] = loginBra;

//			sqlQuery_="select md.mode_of_transport,md.warehouse_to_warehouse,md.transit_from,md.final_destination,to_char(md.inception_date,'dd-mm-yyyy'),md.cover_id,md.extra_cover_id,md.total_sum_insured,md.equivalent_usd,md.premium,mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile,mrd.rate,mrd.premium,cm.cover_name,ecm.extra_cover_name,mt.transport_description,com.commodity_name, md.currency_name,md.exchange_rate,md.no_of_items,md.sale_term_id,md.AC_EXECUTIVE_ID,md.company_id,md.currency_id,to_char(md.inception_date,'dd') as policyDay,to_char(md.inception_date,'MON') as policyMonth,to_char(md.inception_date,'YYYY') as policyYear,md.AC_EXECUTIVE_NAME,stm.sale_term_value,stm.sale_term_name,md.transit_from_city_id,md.transit_from_country_id,md.final_destination_city_id,md.final_destination_country_id,mrd.sale_term_charges,mrd.war_charges,com.rag,com.exclusion_id,com.warranty_id,md.excess_premium,mrd.warrate,md.sea_options,md.ware_to_ware_final_dest ,md.discount_premium,nvl(mrd.sea_option_lcl,'0'),nvl(mrd.warehouse_warehouse,'0'),md.product_id,md.open_cover_no,md.open_bank_id,md.open_lc_id,md.open_vessel_name,md.open_vessel_option,mrd.COMMODITY_TYPE_ID,mrd.commodity_Excess_Premium,md.TOLERANCE_ID,mrd.TOLERANCE_CHARGES,md.PARTIAL_SHIPPING,md.PARTIAL_SUM_INSURED_AMOUNT,nvl(md.VIA_CITY_NAME,''),nvl(md.VIA_CITY_ID,'0'),nvl(md.VIA_COUNTRY_ID,'0'),nvl(md.remarks,'Nil'),com.status from marine_data md,marine_result_details mrd,cover_master cm,extra_cover_master ecm,mode_of_transport mt,commoditymaster com,sale_term_master stm where md.application_no=? and md.status='Y' and cm.status='Y' and mt.status='Y' and stm.status='Y' and md.application_no=mrd.application_no and md.cover_id=cm.cover_id and mt.mode_transport_id=md.mode_of_transport and com.commodity_id=mrd.commodity_id and md.extra_cover_id=ecm.extra_cover_id and stm.sale_term_id=md.sale_term_id and  com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and status in ('Y','R') and branch_code=? group by commodity_id) and cm.branch_code=ecm.branch_code and mt.branch_code=com.branch_code and com.branch_code=ecm.branch_code and com.branch_code=stm.branch_code and com.branch_code=? order by com.commodity_id";
			sqlQuery_="select md.mode_of_transport,md.warehouse_to_warehouse,md.transit_from,md.final_destination,to_char(md.inception_date,'dd-MM-yyyy'),md.cover_id,md.extra_cover_id,md.total_sum_insured,md.equivalent_usd,md.premium,mrd.application_no,mrd.commodity_id,mrd.sum_insured,mrd.description,mrd.fragile,mrd.rate,mrd.premium,cm.cover_name,ecm.extra_cover_name,mt.transport_description,com.commodity_name, md.currency_name,md.exchange_rate,md.no_of_items,md.sale_term_id,md.AC_EXECUTIVE_ID,md.company_id,md.currency_id,to_char(md.inception_date,'dd') as policyDay,to_char(md.inception_date,'MM') as policyMonth,to_char(md.inception_date,'YYYY') as policyYear,md.AC_EXECUTIVE_NAME,stm.sale_term_value,stm.sale_term_name,md.transit_from_city_id,md.transit_from_country_id,md.final_destination_city_id,md.final_destination_country_id,mrd.sale_term_charges,mrd.war_charges,com.rag,com.exclusion_id,com.warranty_id,md.excess_premium,mrd.warrate,md.sea_options,md.ware_to_ware_final_dest ,md.discount_premium,nvl(mrd.sea_option_lcl,'0'),nvl(mrd.warehouse_warehouse,'0'),md.product_id,md.open_cover_no,md.open_bank_id,md.open_lc_id,md.open_vessel_name,md.open_vessel_option,mrd.COMMODITY_TYPE_ID,mrd.commodity_Excess_Premium,md.TOLERANCE_ID,mrd.TOLERANCE_CHARGES,md.PARTIAL_SHIPPING,md.PARTIAL_SUM_INSURED_AMOUNT,nvl(md.VIA_CITY_NAME,''),nvl(md.VIA_CITY_ID,'0'),nvl(md.VIA_COUNTRY_ID,'0'),nvl(md.remarks,'Normal'),com.status,MD.EXPOSURE_CURRENCY,MD.OC_VESSEL_ID from marine_data md,marine_result_details mrd,cover_master cm,extra_cover_master ecm,mode_of_transport mt,commoditymaster com,sale_term_master stm where md.application_no=? and md.status='Y' and cm.status='Y' and mt.status='Y' and stm.status='Y' and md.application_no=mrd.application_no and md.cover_id=cm.cover_id and mt.mode_transport_id=md.mode_of_transport and com.commodity_id=mrd.commodity_id and md.extra_cover_id=ecm.extra_cover_id and stm.sale_term_id=md.sale_term_id and  com.amend_id||'-'||com.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-MM-YYYY') <=to_date(SYSDATE,'dd-MM-YYYY') and status in ('Y','R') and branch_code=? group by commodity_id) and cm.branch_code=ecm.branch_code and mt.branch_code=com.branch_code and com.branch_code=ecm.branch_code and com.branch_code=stm.branch_code and com.branch_code=? order by com.commodity_id";

			print("getPremiumDetails",sqlQuery_,"QUERY");
			try
			{
				premiumDetailsArray = runner.multipleSelection(sqlQuery_,argss);
				premiumDetails.put("premiumDetails",premiumDetailsArray);
			}
			catch(Exception e)
			{
				System.out.println("Exception in premiumLogin getpremiumdetails"+e.toString());
				e.printStackTrace();
			}
			if(premiumDetailsArray.length>0)
			{
				fromCountryName=getGeneralValue("country_name","country_master","country_id",premiumDetailsArray[0][35]);
				fromCityName=getGeneralValue("city_name","city_master","city_id",premiumDetailsArray[0][34]);
				toCountryName=getGeneralValue("country_name","country_master","country_id",premiumDetailsArray[0][37]);
				toCityName=getGeneralValue("city_name","city_master","city_id",premiumDetailsArray[0][36]);
				viaCountryName=getGeneralValue("country_name","country_master","country_id",(premiumDetailsArray[0][64]!=null?premiumDetailsArray[0][64]:"0"));
				viaCityName = premiumDetailsArray[0][62]!=null?premiumDetailsArray[0][62]:"";

				for(int i=0;i<premiumDetailsArray.length;i++)
				{
					
					premiumDetails.put("modeOfTransport",premiumDetailsArray[0][0]==null?"":premiumDetailsArray[0][0]);
					premiumDetails.put("coverId",premiumDetailsArray[0][5]==null?"":premiumDetailsArray[0][5]);
					premiumDetails.put("extraCoverId",premiumDetailsArray[0][6]==null?"":premiumDetailsArray[0][6]);
					premiumDetails.put("noOfItems",premiumDetailsArray[0][23]!=null?premiumDetailsArray[0][23]:"");
					premiumDetails.put("saleTermId",premiumDetailsArray[0][24]!=null?premiumDetailsArray[0][24]:"");
					premiumDetails.put("toleranceId",(premiumDetailsArray[i][58]!=null?premiumDetailsArray[i][58]:"0"));
					premiumDetails.put("accExecutiveId",premiumDetailsArray[0][25]!=null?premiumDetailsArray[0][25]:"");
					premiumDetails.put("companyId",premiumDetailsArray[0][26]!=null?premiumDetailsArray[0][26]:"");
					premiumDetails.put("currencyId",premiumDetailsArray[0][27]!=null?premiumDetailsArray[0][27]:"");
					premiumDetails.put("policyDay",premiumDetailsArray[0][28]!=null?premiumDetailsArray[0][28]:"");
					premiumDetails.put("policyMonth",premiumDetailsArray[0][29]!=null?premiumDetailsArray[0][29]:"");
					premiumDetails.put("policyYear",premiumDetailsArray[0][30]!=null?premiumDetailsArray[0][30]:"");
					premiumDetails.put("excessPremium",premiumDetailsArray[0][43]==null?"0":premiumDetailsArray[0][43]);
					premiumDetails.put("warRate",premiumDetailsArray[0][44]==null?"0":premiumDetailsArray[0][44]);
					premiumDetails.put("seaOptions",premiumDetailsArray[0][45]==null?"":premiumDetailsArray[0][45]);
					premiumDetails.put("wareHouse1",premiumDetailsArray[0][46]==null?"":premiumDetailsArray[0][46]);
					premiumDetails.put("discountPremium",premiumDetailsArray[0][47]==null?"":premiumDetailsArray[0][47]);
					premiumDetails.put("seaValue",premiumDetailsArray[0][48]==null?"0":premiumDetailsArray[0][48]);
					premiumDetails.put("warehouseValue",premiumDetailsArray[0][49]==null?"0":premiumDetailsArray[0][49]);
					premiumDetails.put("productId",premiumDetailsArray[0][50]==null?"0":premiumDetailsArray[0][50]);
					premiumDetails.put("openCoverNo",premiumDetailsArray[0][51]==null?"0":premiumDetailsArray[0][51]);
					premiumDetails.put("bankName",premiumDetailsArray[0][52]==null?"0":premiumDetailsArray[0][52]);
					premiumDetails.put("lcNumber",premiumDetailsArray[0][53]==null?"0":premiumDetailsArray[0][53]);
					premiumDetails.put("vesselName",premiumDetailsArray[0][54]==null?"":premiumDetailsArray[0][54]);
					premiumDetails.put("vesselOption",premiumDetailsArray[0][55]==null?"0":premiumDetailsArray[0][55]);
					premiumDetails.put("wsrccOption",premiumDetailsArray[0][6]==null?"0":premiumDetailsArray[0][6]);
					premiumDetails.put("Remarks",premiumDetailsArray[0][65]);
					premiumDetails.put("exposureCurrency",premiumDetailsArray[0][67]);
					premiumDetails.put("vesselId",premiumDetailsArray[0][68]);
					
					try
					{
						premiumDetails.put("executiveName",premiumDetailsArray[0][31]!=null?premiumDetailsArray[0][31]:"None");
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					premiumDetails.put("saleTermValue",premiumDetailsArray[0][32]!=null?premiumDetailsArray[0][32]:"");
					premiumDetails.put("saleTermName",premiumDetailsArray[0][33]!=null?premiumDetailsArray[0][33]:"None");
					premiumDetails.put("fromCountryName",fromCountryName);
					premiumDetails.put("fromCityName",fromCityName);
					premiumDetails.put("toCountryName",toCountryName);
					premiumDetails.put("toCityName",toCityName);
					
					premiumDetails.put("viaCountryName",viaCountryName);
					premiumDetails.put("viaCityName",viaCityName);

					countryDetails.put("transitCityId",premiumDetailsArray[0][34]!=null?premiumDetailsArray[0][34]:"");
					countryDetails.put("transitCountryId",premiumDetailsArray[0][35]!=null?premiumDetailsArray[0][35]:"");
					countryDetails.put("finalCityId",premiumDetailsArray[0][36]!=null?premiumDetailsArray[0][36]:"");
					countryDetails.put("finalCountryId",premiumDetailsArray[0][37]!=null?premiumDetailsArray[0][37]:"");
					
					countryDetails.put("viaCityId",premiumDetailsArray[0][63]!=null?premiumDetailsArray[0][63]:"0");
					countryDetails.put("viaCountryId",premiumDetailsArray[0][64]!=null?premiumDetailsArray[0][64]:"0");
					premiumDetails.put("fromCityId",((String)countryDetails.get("transitCityId")!=null?(String)countryDetails.get("transitCityId"):""));
					premiumDetails.put("tocityId",((String)countryDetails.get("finalCityId")!=null?(String)countryDetails.get("finalCityId"):""));
					premiumDetails.put("fromCountry",((String)countryDetails.get("transitCountryId")!=null?(String)countryDetails.get("transitCountryId"):""));
					premiumDetails.put("toCountry",((String)countryDetails.get("finalCountryId")!=null?(String)countryDetails.get("finalCountryId"):""));
					
					premiumDetails.put("viaCountryId",((String)countryDetails.get("viaCountryId")!=null?(String)countryDetails.get("viaCountryId"):""));
					premiumDetails.put("viacity",viaCityName);
					
					

					String fromcityyyyyyyy=(String)countryDetails.get("transitCityId");
					
					if(!fromcityyyyyyyy.equalsIgnoreCase("0"))
					{
						String args1[] = new String[2];
						args1[0] = (String)countryDetails.get("transitCityId");
						args1[1] = (String)countryDetails.get("transitCountryId");

						String FCN = totalValue("select nvl(initcap(city_name),' ') from city_master where city_id=? and  country_id=?",args1);
						if(FCN.length()<=0)
							FCN = "";
						premiumDetails.put("fromcity",FCN);
					}
					else
					{
						String args1[] = new String[1];
						args1[0] = applicationNo;
						String ss=totalValue("select nvl(initcap(transit_from),' ') from marine_data where  application_no=? and TRANSIT_FROM_CITY_ID='0'",args1);
						if(ss.length()<=0)
							ss="";			
						premiumDetails.put("fromcity",ss);
					}
					String tocityyyyyy=(String)countryDetails.get("finalCityId");
					
					if(!tocityyyyyy.equalsIgnoreCase("0"))
					{
						String args1[] = new String[2];
						args1[0] = (String)countryDetails.get("finalCityId");
						args1[1] = (String)countryDetails.get("finalCountryId");
						String TC = totalValue("select nvl(initcap(city_name),' ') from city_master where city_id=? and country_id=?",args1);
						if(TC.length()<=0)
							TC = "";
						premiumDetails.put("tocity",TC);

					}
					else
					{
						String args1[] = new String[1];
						args1[0] = applicationNo;
						String ss=totalValue("select nvl(initcap(final_destination),' ') from marine_data where  application_no=? and FINAL_DESTINATION_CITY_ID='0'",args1);
						if(ss.length()<=0)
							ss="";
						premiumDetails.put("tocity",ss);
					}
				String args[] = new String[2];
				args[0] = (String)countryDetails.get("transitCountryId");
				args[1] = cid;

				String FN = totalValue("select nvl(initcap(country_name),' ') from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?",args);
				if(FN.length()<=0)
					FN = "";
				premiumDetails.put("fromcountryNames",FN);
				
				args = new String[2];
				args[0] = (String)countryDetails.get("finalCountryId");
				args[1] = cid;
				String CN = totalValue("select initcap(country_name) from country_master_detail where country_id=? and BELONGING_COUNTRY_ID=?",args);
				if(CN.length()<=0)
					CN = "";
				premiumDetails.put("tocountryNames",CN);
							
				countryDetails.put("transitFrom",fromCountryName+"#"+(premiumDetailsArray[0][35]!=null?premiumDetailsArray[0][35]:"")+"$"+fromCityName+"*"+(premiumDetailsArray[0][34]!=null?premiumDetailsArray[0][34]:"")+"~"+(premiumDetailsArray[0][2]!=null?premiumDetailsArray[0][2]:""));
				countryDetails.put("finalDestination",toCountryName+"#"+(premiumDetailsArray[0][37]!=null?premiumDetailsArray[0][37]:"")+"$"+toCityName+"*"+(premiumDetailsArray[0][36]!=null?premiumDetailsArray[0][36]:"")+"~"+(premiumDetailsArray[0][3]!=null?premiumDetailsArray[0][3]:""));

				premiumDetails.put("wareHouse",premiumDetailsArray[0][1]!=null?premiumDetailsArray[0][1]:"");
				premiumDetails.put("transitFrom",premiumDetailsArray[0][2]!=null?premiumDetailsArray[0][2]:"");
				premiumDetails.put("finalDestination",premiumDetailsArray[0][3]!=null?premiumDetailsArray[0][3]:"");
				premiumDetails.put("finalVia",premiumDetailsArray[0][62]!=null?premiumDetailsArray[0][62]:"");
				premiumDetails.put("inceptionDate",(premiumDetailsArray[0][4]!=null?premiumDetailsArray[0][4]:""));
				premiumDetails.put("totalSumInsured",premiumDetailsArray[0][7]);
				premiumDetails.put("equivalentUSD",(premiumDetailsArray[0][8]!=null?premiumDetailsArray[0][8]:""));
				premiumDetails.put("premium",(premiumDetailsArray[0][9]!=null?premiumDetailsArray[0][9]:"0"));
				premiumDetails.put("coverName",(premiumDetailsArray[0][17]!=null?premiumDetailsArray[0][17]:""));
				premiumDetails.put("extraCoverName",(premiumDetailsArray[0][18]!=null?premiumDetailsArray[0][18]:""));
				premiumDetails.put("transportName",(premiumDetailsArray[0][19]!=null?premiumDetailsArray[0][19]:""));
				premiumDetails.put("currencyName",(premiumDetailsArray[0][21]!=null?premiumDetailsArray[0][21]:""));
				policyInfo pol = new policyInfo();
				boolean GHQOACode = pol.getGHQOACode(loginCode);
				/*System.out.println("Test Currency::"+ premiumDetails.get("currencyName").toString());
				if(premiumDetails.get("currencyName").toString().equalsIgnoreCase("USD DOLLAR") && GHQOACode)
				{
				  String exchangeRate = runner.singleSelection("select percent from constant_detail where category_id='134' and status='Y' and branch_code='"+loginBra+"'");
				  premiumDetails.put("exchangeRate",(exchangeRate!=null?exchangeRate:(premiumDetailsArray[0][22]!=null?premiumDetailsArray[0][22]:"0")));
				  
				}else{*/
				premiumDetails.put("exchangeRate",premiumDetailsArray[0][22]!=null?premiumDetailsArray[0][22]:"0");
				//}
				premiumDetails.put("partialShip",(premiumDetailsArray[0][60]!=null?premiumDetailsArray[0][60]:""));
				premiumDetails.put("partialShipmentAmt",(premiumDetailsArray[0][61]!=null?premiumDetailsArray[0][61]:""));
				
				args = new String[1];
				args[0] =applicationNo;
				String CP = totalValue("select nvl(sum(sale_term_charges),'0') from marine_result_details where application_no=?",args);
				if(CP.length()<=0)
					CP = "0";
				premiumDetails.put("commodityPremium",CP);
				String WP = totalValue("select nvl(sum(war_charges),'0') from marine_result_details where application_no=?",args);
				if(WP.length()<=0){
					WP = "0";
				}
				premiumDetails.put("WarPremium",new PremiumInputsBean().getRoundedValue(WP,"1",GHQOACode));
				String sil = totalValue("select nvl(sum(suminsuredlocal),'0') from marine_result_details where application_no=?",args);
				if(sil.length()<=0)
					sil = "0";
				premiumDetails.put("Total_AEDS",sil);
				
				String sumInsLF[][] = new String[0][0];
				String sumInsLocal = "";
				sumInsLocal = runner.singleSelection("select sum(nvl(SUMINSUREDLOCAL,0)) from marine_result_details where application_no=?", args);
				sumInsLF = runner.multipleSelection("select ("+sumInsLocal+")+nvl(md.TOTAL_TOLERANCE_CHARGES,0)+nvl(md.TOTAL_SALE_TERM_CHARGES,0), ((("+sumInsLocal+")+nvl(md.TOTAL_TOLERANCE_CHARGES,0)+nvl(md.TOTAL_SALE_TERM_CHARGES,0))/nvl(md.exchange_rate,1))from marine_result_details mrd,marine_data md where md.application_no=mrd.application_no and md.application_no=?", args);
				if(sumInsLF.length > 0 ){
					/*premiumDetails.put("sumInsL",sumInsLF[0][0]==null?"0":sumInsLF[0][0]);
					premiumDetails.put("sumInsF", sumInsLF[0][1]==null?"0":sumInsLF[0][1]);*/
					
					premiumDetails.put("sumInsL",new PremiumInputsBean().getRoundedValue((sumInsLF[0][0]==null?"0":sumInsLF[0][0]),"1",GHQOACode));
					premiumDetails.put("sumInsF", new PremiumInputsBean().getRoundedValue((sumInsLF[0][1]==null?"0":sumInsLF[0][1]),(String)premiumDetails.get("currencyId"),GHQOACode));
				}
				
				commNameCombined=commNameCombined+(premiumDetailsArray[i][20]!=null?premiumDetailsArray[i][20]:"")+",";
				commIdCombined=commIdCombined+(premiumDetailsArray[i][11]!=null?premiumDetailsArray[i][11]:"")+",";
				commodityDetails.put("commodityId"+(finalCount),(premiumDetailsArray[i][11]!=null?premiumDetailsArray[i][11]:""));	
				commodityDetails.put("commodity"+(finalCount),(premiumDetailsArray[i][20]!=null?premiumDetailsArray[i][20]:""));
				commodityDetails.put("sumInsured"+(finalCount),(premiumDetailsArray[i][12]!=null?premiumDetailsArray[i][12]:"0"));
				commodityDetails.put("description"+(finalCount),(premiumDetailsArray[i][13]!=null?premiumDetailsArray[i][13]:""));
				commodityDetails.put("fragile"+(finalCount),(premiumDetailsArray[i][14]!=null?premiumDetailsArray[i][14]:""));
				commodityDetails.put("saleTermCharges"+(finalCount),(premiumDetailsArray[i][38]!=null?premiumDetailsArray[i][38]:"0"));
				commodityDetails.put("tolCharges"+(finalCount),(premiumDetailsArray[i][59]!=null?premiumDetailsArray[i][59]:"0"));
				commodityDetails.put("warCharges"+(finalCount),(premiumDetailsArray[i][39]!=null?premiumDetailsArray[i][39]:"0"));
				commodityDetails.put("ragName"+(finalCount),(premiumDetailsArray[i][40]!=null?premiumDetailsArray[i][40]:""));
				commodityDetails.put("warRate"+(finalCount),(premiumDetailsArray[i][44]!=null?premiumDetailsArray[i][44]:"0"));
				commodityDetails.put("seaValue"+(finalCount),(premiumDetailsArray[i][48]!=null?premiumDetailsArray[i][48]:"0"));
				commodityDetails.put("warehouseValue"+(finalCount),(premiumDetailsArray[i][49]!=null?premiumDetailsArray[i][49]:"0"));
				commodityDetails.put("comType"+(finalCount),(premiumDetailsArray[i][56]!=null?premiumDetailsArray[i][56]:"0"));
				commodityDetails.put("EP"+(finalCount),(premiumDetailsArray[i][57]!=null?premiumDetailsArray[i][57]:"0"));
				finalCount=finalCount+1;
			}
			
			premiumDetails.put("commNameCombined",commNameCombined);
			premiumDetails.put("commIdCombined",commIdCombined);
			finalCount=finalCount-1;
			commodityDetails.put("finalCount",""+finalCount);
			premiumDetails.put("commodityDetails",commodityDetails);
			premiumDetails.put("countryDetails",countryDetails);
			}
		}
		catch(Exception exception)
		{
			System.out.println("Royal Test The Exception occured in premium.jsp--"+exception.toString());
			exception.printStackTrace();
		}
		return premiumDetails;
	}

	public String getSettilingAgent(String no,String qno,String cid)
    {
		
		String args[] = new String[0];
		String sql = "";
		String name = "";
		try
		{
			if(no.length()>0&&!no.equals("0"))
			{
				args = new String[2];
				args[0] = no;
				args[1] = cid;
				sql = "select SETTLING_AGENT_NAME from SETTLING_AGENT_MASTER where SETTLING_AGENT_ID=? and BELONGING_COUNTRY_ID=? and status='Y' ";
				name = runner.singleSelection(sql,args);      
			}
	        else
			{
			  args = new String[1];
			  args[0] = qno;
		      sql = "select SETTLING_AGENT_NAME from MARINE_POLICY_DETAILS where  quote_no=?";
			  name = runner.singleSelection(sql,args);  
			}
		}
		catch(Exception exception)
		{
			System.out.println("Royal getSettilingAgent -"+exception.toString());
			exception.printStackTrace();
		}
		return name;
	}

	public String getEndingPlace(String cid)
	{
		
		String args[] = new String[3];
		String sql = "";
		String[][] result=new String[0][0];
		String res="";
		try
		{
			args[0] = applicationNo;
			args[1] = cid;
			args[2] = cid;
			sql = "select cm.ending_place from COUNTRY_MASTER_DETAIL cm where cm.country_id=(select final_destination_country_id from marine_data where application_no=?) and cm.status in ('Y','R') and cm.BELONGING_COUNTRY_ID=? and cm.amend_id||cm.COUNTRY_ID in (select max(amend_id)||COUNTRY_ID from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') and status in('Y','R')  and BELONGING_COUNTRY_ID=? group by COUNTRY_ID) and to_date(cm.effective_date,'dd-MM-YYYY')<=to_date(SYSDATE,'dd-MM-YYYY') order by cm.COUNTRY_NAME";
			result = runner.multipleSelection(sql,args);
		}
		catch(Exception exception)
		{
			System.out.println("Royal getEndingPlace -"+exception.toString());
			exception.printStackTrace();
		}
		if(result.length>0)
			res = result[0][0]!=null?result[0][0]:"";
		return res;
	}

	public String[][] getsLCDetails(String bankId,String lcNo,String openCoverNo,String cid)
	{
		String[][] getsCountry=new String[0][0];
		String lcQuery="";
		String args[] = new String[8];
		
		try
		{
			args[0] = cid;
			args[1] = cid;
			args[2] = lcNo;
			args[3] = bankId;
			args[4] = openCoverNo;
			args[5] = cid;
			args[6] = cid;
			args[7] = cid;
			lcQuery="select bm.bank_name,oplm.lc_number,oplm.lc_amount,to_char(oplm.lc_date,'dd-MM-yyyy'),cm.currency_name,oplm.lc_currency_id,em.exchange_rate,bm.bank_id,to_char(oplm.expiry_date,'dd-MM-yyyy') from open_cover_lc_master oplm,open_cover_bank_master bm,currency_master cm,exchange_master em where oplm.status='Y' and cm.COUNTRY_ID=em.COUNTRY_ID and em.COUNTRY_ID=? and bm.BELONGING_COUNTRY_ID=? and lc_id=? and oplm.bank_id=? and oplm.bank_id=bm.bank_id and cm.currency_id=nvl(oplm.lc_currency_id,'0') and open_cover_no=? and cm.amend_id||'-'||cm.currency_id in (select max(cms.amend_id)||'-'||cms.currency_id from currency_master cms where to_char(cms.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') and cms.COUNTRY_ID=? group by cms.currency_id ) and bm.amend_id||'-'||bm.bank_id in (select max(ocbms.amend_id)||'-'||ocbms.bank_id from open_cover_bank_master ocbms where to_date(ocbms.effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and ocbms.BELONGING_COUNTRY_ID=? group by ocbms.bank_id) and em.amend_id||'-'||em.currency_id in (select max(ems.amend_id)||'-'||ems.currency_id from exchange_master ems where to_char(ems.effective_date,'dd-mm-YYYY') <= to_char(SYSDATE,'dd-mm-YYYY') and ems.COUNTRY_ID=? group by ems.currency_id ) and em.currency_id=oplm.lc_currency_id order by oplm.lc_id";
			
			getsCountry = runner.multipleSelection(lcQuery,args);
			if(getsCountry.length>0)
			{

			}
			else
			{
				getsCountry=new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("The Get getsLCDetails Value Is -->>"+e);
			e.printStackTrace();
		}
		return getsCountry;
	}

	public String getProCommission(String QuoteNo,String productId,String loginBra)
	{
		String res = "";
		try
		{
			String prosql = "";
			if(productId.equalsIgnoreCase("3"))
			{
				String args[] = new String[10];
				args[0] = productId;
				args[1] = QuoteNo;
				args[2] = loginBra;
				args[3] = QuoteNo;
				args[4] = QuoteNo;
				args[5] = productId;
				args[6] = QuoteNo;
				args[7] = loginBra;
				args[8] = QuoteNo;
				args[9] = QuoteNo;
				prosql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and status='Y' and START_DATE<=(select (entry_date) from position_master where quote_no=?) and END_DATE>=(select (entry_date) from position_master where quote_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and status='Y' and START_DATE<=(select (entry_date) from position_master where quote_no=?) and END_DATE>=(select (entry_date) from position_master where quote_no=?))";
				res = runner.singleSelection(prosql,args);
			}
			else
			{
				String args[] = new String[12];
				args[0] = productId;
				args[1] = QuoteNo;
				args[2] = loginBra;
				args[3] = QuoteNo;
				args[4] = QuoteNo;
				args[5] = QuoteNo;
				args[6] = productId;
				args[7] = QuoteNo;
				args[8] = loginBra;
				args[9] = QuoteNo;
				args[10] = QuoteNo;
				args[11] = QuoteNo;
				prosql = "select nvl(PRO_COMMISSION,0) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and status='Y' and START_DATE<=(select (entry_date) from position_master where quote_no=?) and END_DATE>=(select (entry_date) from position_master where quote_no=?) and open_cover_no=(select open_cover_no from position_master where quote_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and status='Y' and START_DATE<=(select (entry_date) from position_master where quote_no=?) and END_DATE>=(select (entry_date) from position_master where quote_no=?) and open_cover_no=(select open_cover_no from position_master where quote_no=?))";
				res = runner.singleSelection(prosql,args);
			}
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public int getDiscountPremium(String quoteNo)
	{
		int res=0;
		String args[] = new String[1];
		String sql = "";
		
		try
		{
			args[0] = quoteNo;
			sql = "select nvl(discount_premium,0) from position_master where quote_no = ?";
			res = Integer.parseInt(runner.singleSelection(sql,args));
		}
		catch(Exception e)
		{
			System.out.println("get Discount Premium value form position master " + e);
			e.printStackTrace();
		}
		return res;
	}
	public String getToleranceName(String toleranceId, String branchCode)
	{
		String result="";
		try
		{
			result = runner.singleSelection("SELECT TOLERANCE_NAME FROM TOLERANCE_MASTER WHERE TOLERANCE_ID=? AND BRANCH_CODE=?",new String[]{toleranceId, branchCode});
		}
		catch(Exception e)
		{
			logger.info("Exception in getToleranceName .."+e);
		}
		return result;	
	}
	public String getBrokerLoginId(String applicationNo)
	{
		String result="";
		try{
			result = runner.singleSelection("SELECT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_NO=?",new String[]{applicationNo});
		}
		catch(Exception e){}
		return result;	
	}
	
} // Class

	
