
package com.maan.product;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.services.util.runner;
public class ProductSelection extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(ProductSelection.class);

	private String sql="";
	private String loginCode="";
	private String selectProd="";
	private String selectFloat="";
	private String error="";
	
	private String companyName="";
	private String phone="";
	private String fax="";
	private String loginId="";
	private String brokerCode="";
	
	private PrintWriter out = null;


	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public void setLoginCode(String loginCode)
	{
		this.loginCode = loginCode;
	}
	public String getLoginCode()
	{
		return loginCode;
	}
	public void setSelectProd(String selectProd)
	{
		this.selectProd=selectProd;
	}
	public String getSelectProd()
	{
		return selectProd;
	}

	public void setSelectFloat(String selectFloat)
	{
		this.selectFloat=selectFloat;
	}

	public String getSelectFloat()
	{
		return selectFloat;
	}

	public void setOut(PrintWriter out)
	{
		this.out = out;
	}

	public void setError(String error)
	{
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	public StringBuffer getProductDetails(String productIds)
	{
		String args[] = new String[1];
		sql = "select product_id, product_name from product_master where status='Y' and product_id in ("+productIds+") order by product_id";

		String alternative="row1";
		StringBuffer stb = new StringBuffer();
		try
		{
			String productdetails[][] = new String[0][0];
			productdetails = runner.multipleSelection(sql);
			String prodIdentifier="";
			for(int i = 0; i < productdetails.length;i++)
			{
				if (productdetails[i][0].equalsIgnoreCase(selectProd))
				{
					prodIdentifier = "checked";
				}
				else
				{
					prodIdentifier = "";
				}

				/** Modified By Panneer on 16-10-12006 **/

				if(productdetails[i][1].equalsIgnoreCase("ONE OFF POLICY"))
				{
					stb.append("<tr class='"+alternative+"'><td><input type='radio' name='selectProd' "+prodIdentifier+"  value='"+productdetails[i][0]+"' checked onClick='document.ChooseProduct.submit()' >"+productdetails[i][1]);
				}
				else if(productdetails[i][1].equalsIgnoreCase("OPEN COVER"))
				{
					stb.append("<tr class='"+alternative+"'><td><input type='radio' name='selectProd' "+prodIdentifier+"  value='"+productdetails[i][0]+"' onClick='document.ChooseProduct.submit()'>"+productdetails[i][1]);
				}
				else
				{
					stb.append("<tr class='"+alternative+"'><td><input type='radio' name='selectProd' "+prodIdentifier+"  value='"+productdetails[i][0]+"' disabled >"+productdetails[i][1]);
				}

				/** Modified By Panneer on 16-10-12006 **/

				if(alternative.equals("row1"))
				{
					alternative ="row2";
				}
				else
				{
					alternative ="row1";
				}
				stb.append("</td></tr>");
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getProductDetails() :"+e.toString());
		}
		return stb;
	}
	// on 29/12 for RSA Issuer modififctaion need to be integarte
	public String[][] getProductDetailsModified(String loginId,String branch,String rsaIssuer)
	{
		String productdetails[][]=new String[0][0];
		

		try
		{
			
			if(rsaIssuer==null)
			{
				String args[] = new String[2];
				args[0] = branch;
				args[1] = loginId;
				//sql = "select product_id, product_name from product_master where status='Y' and branch_code=? and product_id in (select product_id from login_user_details where agency_code=(select agency_code from login_master where  login_id=?) and status='Y') order by product_id";
				sql = "select product_id, product_name from product_master where status='Y' and branch_code=(SELECT   belonging_branch FROM   branch_master bm WHERE   branch_code = ? AND STATUS = 'Y'  AND amend_id =(SELECT   MAX (amend_id) FROM   branch_master bm1 WHERE   bm1.branch_code = bm.branch_code  AND bm1.status = bm.status)) and product_id in (select product_id from login_user_details where agency_code=(select agency_code from login_master where  login_id=?) and status='Y') order by product_id";
				productdetails = runner.multipleSelection(sql,args);
			}
			else
			{
	/*			String args[] = new String[6];
				args[0] = branch;
				args[1] = loginId;
				args[2] = rsaIssuer;
				args[3] = rsaIssuer;
				args[4] = loginId;
				args[5] = loginId;
				sql = "select product_id, product_name from product_master where status='Y' and branch_code=? and product_id in (select product_id from login_user_details where agency_code=(select agency_code from login_master where  login_id=?) and status='Y') and product_id in(select product_id from LOGIN_RSAUSER_DETAILS where login_id=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and (amend_id||broker_code||product_id) in(select max(amend_id)||broker_code||product_id from LOGIN_RSAUSER_DETAILS where login_id=? and START_DATE<=(select to_char(sysdate,'dd-mon-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-mon-yyyy') from dual) and broker_code=(select oa_code from login_master where login_id=?) group by broker_code,product_id) and broker_code=(select oa_code from login_master where login_id=?) and status='Y') order by product_id";
				productdetails = runner.multipleSelection(sql,args);*/

				String productIds = runner.singleSelection("select product_id from login_master where login_id=?", new String []{loginId});
				productIds=productIds.replaceAll(", ", ",");
				productIds=productIds.replaceAll(" ,", ",");
				productIds=productIds.replaceAll(",", "','");
				//sql = "select product_id, product_name from product_master where status='Y' and branch_code=? and product_id in ('"+productIds+"') order by product_id";
				sql = "select product_id, product_name from product_master where status='Y' and branch_code=(SELECT   belonging_branch FROM   branch_master bm WHERE   branch_code = ? AND STATUS = 'Y'  AND amend_id =(SELECT   MAX (amend_id) FROM   branch_master bm1 WHERE   bm1.branch_code = bm.branch_code  AND bm1.status = bm.status)) and product_id in ('"+productIds+"') order by product_id";
				productdetails = runner.multipleSelection(sql,new String[]{branch});

			}
			if(productdetails.length > 0)
			{
			}
			else
			{
				productdetails=new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in get ProductDetails Modified() :"+e.toString());
			e.printStackTrace();
		}
		return productdetails;
	}

	//Modified by Rajesh For Home
	public String[][] getHomeProductDetails()
	{
			String productdetails[][]=new String[0][0];
			sql = "select product_id from home_product_master where status='Y' and BROKER_ID is null order by product_id";
			System.out.println("RoyalTest getHomeProductDetails-->"+sql);
			try
			{
				productdetails = runner.multipleSelection(sql);
				if(productdetails.length > 0)
				{
				}
				else
				{
					productdetails=new String[0][0];
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception in getHomeProductDetails() :"+e.toString());
				e.printStackTrace();
			}
			return productdetails;
	}

	public String[][] getTravelProductDetails()
	{
			String productdetails[][]=new String[0][0];
			sql = "select product_id,BROKER_ID from home_product_master where status='Y' and BROKER_ID in('Travel') order by product_id";
			System.out.println("RoyalTest getHomeProductDetails-->"+sql);
			try
			{
				productdetails = runner.multipleSelection(sql);
				if(productdetails.length > 0)
				{
				}
				else
				{
					productdetails=new String[0][0];
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception in getHomeProductDetails() :"+e.toString());
				e.printStackTrace();
			}
			return productdetails;
	}

	public String getErrormsg(String errorCode,String description)
	{
		String result = "";
		String sql = "";
		String args[] = new String[1];
		runner run = new runner();
		try
		{
			args[0] = errorCode;
			sql = "select error_desc from error_master where error_id=?";
			result = run.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Error in ERROR INFO UNDER COMMON ERROR FOLDER ..."+e.toString());
			result="Please Provide Valid Input for "+description;
		}
		return result;
	}

	public String[][] getCreatedOpenCovers(String loginId)
	{

		String[][] openCovers=new String[0][0];
		String sql_openCover="select distinct(ocpm.open_cover_no),ocpm.proposal_no,ocm.customer_id,pi.first_name||pi.last_name,ocm.broker_id,ocm.broker_user_id from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where lower(ocm.broker_id)=lower('"+loginId+"') and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y'";
		System.out.println("getCreatedOpenCovers------>"+sql_openCover);
		try
		{
			openCovers = runner.multipleSelection(sql);
			if(openCovers.length > 0 )
			{
			}else
			{
			openCovers=new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getCreatedOpenCovers"+e.toString());
		}
		return openCovers;
	}

	public String[][] getMailOpenCoverInfo(String openCoverNo)
	{
		String openCoverInfo[][]=null;
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = openCoverNo;
			args[1] = openCoverNo;
			sql = "select ocm.MISSIPPI_OPENCOVER_NO,nvl(pi.COMPANY_NAME,pi.first_name||' '||pi.last_name),pi.company_name,pi.address1, pi.address2,pi.pobox, pi.country,ocm.amend_id from open_cover_master ocm,open_cover_position_master ocpm,personal_info pi where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and  pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
			openCoverInfo = runner.multipleSelection(sql,args);
		}
		catch(Exception ee)
		{
			System.out.println("Exception."+ee);
			ee.printStackTrace();
		}
		return openCoverInfo;
	}

	public String[][] getOpenCoverInfo(String openCoverNo)
	{
		String args[] = new String[2];
		String[][] openCoverInfo=new String[0][0];
		args[0] = openCoverNo;
		args[1] = openCoverNo;
		String sql_openCoverInfo= "";
		sql_openCoverInfo = "select ocm.MISSIPPI_OPENCOVER_NO,nvl(pi.COMPANY_NAME,pi.first_name||' '||pi.last_name),pi.company_name,ocm.amend_id,NVL(OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO) from open_cover_master ocm,open_cover_position_master ocpm,personal_info pi where ocpm.open_cover_no =? and ocpm.proposal_no=ocm.proposal_no and  pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id in (select max(ocms.amend_id) from open_cover_master ocms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY'))";
		
		try
		{
			openCoverInfo= runner.multipleSelection(sql_openCoverInfo,args);
			if(openCoverInfo.length > 0 )
			{
			}
			else
			{
				openCoverInfo=new String[0][0];
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in getOpenCoverInfo"+e.toString());
			e.printStackTrace();
		}
		return openCoverInfo;
	}

	//FREIGHT USER CHECKING 
	public boolean getFreightUserType(String user)
	{
		String query = "";
		String args[] = new String[1];
		String result = "";
		boolean retvalue = true;
		try
		{
			args[0] = user;
			query = "select FREIGHT_FORWARD_USER from personal_info where agency_code =(select agency_code from login_master where login_id = ? and status='Y')and application_id=3 and status='Y'";
			 result = runner.singleSelection(query,args);
			if(result != null)
			{
				 if(result.equalsIgnoreCase("FreightForward Without Policy")){
					  retvalue = false;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return retvalue;
	}

	//Royal Common Worid Work Started
	public Map getBrokerUserBranch(String user)
	{
		String query = "";
		String args[] = new String[1];
		String result = "";
		Map branchMap = new HashMap();
		try
		{
			args[0] = user;
			query = "select nvl(BRANCH_CODE,'020') from BROKER_COMPANY_MASTER where " +
					"AGENCY_CODE=(select oa_code FROM login_master where login_id=?) ";
			result = runner.singleSelection(query,args);
			if(result.length()<=0)
			{
				args[0] = user;
				query = "select nvl(BRANCH_CODE,'020') from LOGIN_master where login_id=?";
				result = runner.singleSelection(query,args);
			}
			args[0] = result;
			branchMap.put("actualBranch",result);
			result = runner.singleSelection("select DETAIL_NAME from CONSTANT_DETAIL where CATEGORY_ID='116' and CATEGORY_DETAIL_ID=1 and status='Y'" +
					" and branch_code=?",args);
			branchMap.put("defaultBranch",result);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return branchMap;
	}
	public HashMap getBrokerUserDetails(String branch)
	{
		String query ="";
		String args[] = new String[1];
		HashMap broDetails = new HashMap();
		try
		{
			args[0] = branch;
			query = "SELECT CURRENCY_ABBREVIATION,ORIGINATION_COUNTRY_ID,DESTINATION_COUNTRY_ID,CURRENCY_ABBREVIATION,DECIMAL_PLACES,nvl(tax,'0'),email,LANG_YN from BRANCH_MASTER where BRANCH_CODE=? ";
			String result[][] = new String[0][0];
			result = runner.multipleSelection(query,args);
			
			if(result.length>0)
			{
				broDetails.put("Branch",branch);
				broDetails.put("CurrencyName",(result[0][0]!=null?result[0][0]:"AED"));
				broDetails.put("Orgination",(result[0][1]!=null?result[0][1]:"1"));
				broDetails.put("Destination",(result[0][2]!=null?result[0][2]:"1"));
				broDetails.put("CurrencyAbb",(result[0][3]!=null?result[0][3]:"AED"));
				broDetails.put("CurrencyDecimal",(result[0][4]!=null?result[0][4]:"2"));
				broDetails.put("Tax",(result[0][5]!=null?result[0][5]:"0"));
				broDetails.put("Site",(result[0][6]!=null?result[0][6]:" "));
				broDetails.put("LANG",(result[0][7]!=null?result[0][7]:" "));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return broDetails;
	}
	
	public String getBetterStatus(String pid)
	{
		String args[] = new String[1];
		String query = "";
		String res = "";
		String result = "";
		try
		{
			args[0] = pid;
			query = "select PRODUCT_ID from BHOMEPRODUCT_MASTER where REMARKS='"+pid+"'";
			result = runner.singleSelection(query);
			if(result.length()>0)
				res = "Yes";
			else
				res = "No";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}

	//For RSAIssuer status checking
	public boolean getStatusRSAIssuer(String login)
	{
		boolean status = false;
		String args[] = new String[1];
		String sql = "";
		String result = "";
		try
		{
			args[0] = login;
			//sql = "select nvl(RIGHTS,'Nil') from LOGIN_MASTER where agency_code=(select oa_code from LOGIN_MASTER where LOGIN_ID=?)";
			sql = "select USERTYPE from LOGIN_MASTER where LOGIN_ID=?";
			result = runner.singleSelection(sql,args);
			if(result.length()>0)
			{
				if(result.equalsIgnoreCase("RSAIssuer"))
					status = true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			return status;
	}
	//on 29/12 for RSA Issuer modification
	public String[][] getBrokersList(String adminBranch,String login)
	{
		String[][] brokerid = new String[0][0];
		String sql = ""; 
		String args[] = new String[3];
		try
		{
			args[0] = adminBranch;
			args[1] = login;
			args[2] = login;
			sql = "SELECT l.login_id, b.company_name, b.agency_code, b.AC_EXECUTIVE_ID FROM login_master l, BROKER_COMPANY_MASTER b WHERE b.agency_code = l.agency_code AND l.login_id NOT IN ('NON', 'NONE') AND l.status IN ('Y','T') AND l.login_id IS NOT NULL AND b.agency_code IS NOT NULL AND b.branch_code = ? AND b.agency_code IN (SELECT DISTINCT broker_code FROM LOGIN_RSAUSER_DETAILS WHERE login_id = ?  AND trunc(START_DATE) <= TRUNC (SYSDATE) AND trunc(END_DATE) >= TRUNC (SYSDATE) AND (amend_id || broker_code) IN (SELECT MAX (amend_id) || broker_code FROM LOGIN_RSAUSER_DETAILS WHERE login_id =? AND trunc(START_DATE) <= trunc(sysdate) AND trunc(END_DATE) >=trunc(sysdate) GROUP BY broker_code) AND status = 'Y') ORDER BY b.company_name";
			brokerid = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting customer infoooooooooooooooo"+e.toString());
			e.printStackTrace();
		}
		 return brokerid;
	}

	public String getSharafStatus(String login)
	{
		String sql = "";
		String result = "";
		String args[] = new String[1];
		try
		{
			args[0] = login;
			sql = "select nvl(RIGHTS,'Nil') from LOGIN_MASTER where LOGIN_ID=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
		
	public String[][] getOfficeProductScheme(String login,String branch)
	{
		String sql = "";
		String officeScheme[][] = new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = branch;
			args[1] = login;
			///sql = "select s.scheme_id,(p.product_name||' - '||s.scheme_name) from product_master p,OFS_SCHEME_MASTER s where p.status=s.status and s.status='Y' and p.branch_code=s.branch_code and s.branch_code=? and s.product_id=p.product_id and s.scheme_id in (select scheme_id from login_user_details where scheme_id is not null and agency_code=(select agency_code from login_master where login_id=?) and status='Y') order by s.scheme_id";
			sql = "select s.scheme_id,(p.product_name||' - '||s.scheme_name), p.PRODUCT_ID from product_master p,OFS_SCHEME_MASTER s where p.status=s.status and s.status='Y' and p.branch_code=s.branch_code and s.branch_code=? and s.product_id=p.product_id and s.scheme_id in (select scheme_id from login_user_details where scheme_id is not null and agency_code=(select agency_code from login_master where login_id=?) and status='Y') order by s.scheme_id";
			officeScheme = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return officeScheme;
	}
	public void updateCargoTypeSp()
	{
		String res[][] = new String[0][0];
		String sql = "select quote_no,policy_no,product_id from position_master where status='P' and inception_Date>to_Date('11-04-2009','dd-mm-yyyy') order by quote_no";
		res = com.maan.services.util.runner.multipleSelection(sql);
		for(int i=0;i<res.length;i++)
		{
			StringTokenizer st = new StringTokenizer(res[i][1],"/");
			String temp3 = "";
			if(st.hasMoreTokens())
			{
				String temp1 = st.nextToken();
				String temp2 = st.nextToken();
				temp3 = st.nextToken();
				String temp4 = st.nextToken();
			}
			if(res[i][2].equalsIgnoreCase("3"))
			{
				com.maan.services.util.runner.updation("update T_TRN_POLICY1 set POL_COVER_NOTE_MIN=(select nvl(RSACODE,'0') from CONSTANT_DETAIL where CATEGORY_ID='42' and CATEGORY_DETAIL_ID in(select cm.COMMODITY_TYPE_ID from COMMODITYMASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no='"+res[i][0]+"' and cm.branch_Code='"+temp3+"' and cm.amend_id||cm.COMMODITY_ID in(select max(cm.amend_id)||cm.COMMODITY_ID from COMMODITYMASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no='"+res[i][0]+"' and cm.branch_Code='"+temp3+"' group by cm.COMMODITY_ID)) and branch_Code='"+temp3+"') where POL_POLICY_ID='"+res[i][0]+"'");
			}
			else if(res[i][2].equalsIgnoreCase("11"))
			{
				com.maan.services.util.runner.updation("update T_TRN_POLICY1 set POL_COVER_NOTE_MIN=(select nvl(RSACODE,'0') from CONSTANT_DETAIL where CATEGORY_ID='42' and CATEGORY_DETAIL_ID in(select cm.COMMODITY_TYPE_ID from OPEN_COVER_COMMODITY_MASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no='"+res[i][0]+"' and cm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from POSITION_MASTER where quote_no='"+res[i][0]+"')) and cm.amend_id||cm.COMMODITY_ID in(select max(cm.amend_id)||cm.COMMODITY_ID from OPEN_COVER_COMMODITY_MASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no='"+res[i][0]+"' and cm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from POSITION_MASTER where quote_no='"+res[i][0]+"')) group by cm.COMMODITY_ID)) and branch_Code='"+temp3+"') where POL_POLICY_ID='"+res[i][0]+"'");
			}
		}
	}
	//Added by Karthick for getting Type of product
	public String getProductType(final String productId,final String branch){
		return com.maan.services.util.runner.singleSelection("select product_category from product_master where product_id='"+productId+"' and branch_code='"+branch+"' and amend_id in (select max(amend_id) from product_master where product_id='"+productId+"' and branch_code='"+branch+"')");		
	}
	//Added bt chinna for getting schemeId
	public String getSchemeId(final String quoteNo,final String productId){
		return com.maan.services.util.runner.singleSelection("select scheme_id from home_position_master where quote_no='"+quoteNo+"' and product_id='"+productId+"'");		
	}
	public List<ProductSelection> getRecentlyUsedBrokerList(String branchCode, String loginId)
	{
		logger.info("getRecentlyUsedBrokerList - Enter || brachCode: "+branchCode+" loginId: "+loginId);
		String result[][]=new String[0][0];
		List<ProductSelection> brokerList=new ArrayList<ProductSelection>();
		ProductSelection brokerInfo=null;
		result=runner.multipleSelection("SELECT DISTINCT LOGIN_ID,COMPANY_NAME,BROKER_CODE,PHONE,FAX FROM (SELECT LM.LOGIN_ID,BCM.COMPANY_NAME,LRD.BROKER_CODE,BCM.PHONE,BCM.FAX,PM.ENTRY_DATE FROM LOGIN_MASTER LM, BROKER_COMPANY_MASTER BCM,LOGIN_RSAUSER_DETAILS LRD,POSITION_MASTER PM WHERE BCM.AGENCY_CODE = LM.AGENCY_CODE AND BCM.BRANCH_CODE = ? AND BCM.AGENCY_CODE=LRD.BROKER_CODE AND BCM.BRANCH_CODE=LRD.BRANCH_CODE AND LM.LOGIN_ID NOT IN ('NON', 'NONE') AND LM.STATUS = 'Y' AND PM.PRODUCT_ID=LRD.PRODUCT_ID AND PM.APPLICATION_ID=LRD.LOGIN_ID AND PM.LOGIN_ID=LM.LOGIN_ID AND LRD.LOGIN_ID=? AND LRD.START_DATE<=SYSDATE AND LRD.END_DATE>=SYSDATE AND LRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM LOGIN_RSAUSER_DETAILS WHERE LOGIN_ID=LRD.LOGIN_ID AND START_DATE=LRD.START_DATE AND END_DATE>=LRD.END_DATE) AND LRD.STATUS='Y' ORDER BY PM.ENTRY_DATE)", new String[]{branchCode, loginId});
		if(result!=null && result.length>0)
		{
			for (int i = 0; i < result.length; i++) {
				brokerInfo=new ProductSelection();
				brokerInfo.setLoginId(result[i][0]==null?"":result[i][0]);
				brokerInfo.setCompanyName(result[i][1]==null?"":result[i][1]);
				brokerInfo.setBrokerCode(result[i][2]==null?"":result[i][2]);
				brokerInfo.setPhone(result[i][3]==null?"":result[i][3]);
				brokerInfo.setFax(result[i][4]==null?"":result[i][4]);
				brokerList.add(brokerInfo);
			}
		}
		logger.info("getRecentlyUsedBrokerList - Enter || Result: "+result.length);
		return brokerList;
	}
	public String getDirectBroker(String branchCode)
	{
		String result="";
		logger.info("getDirectBroker - Enter || brachCode: "+branchCode);
		result=runner.singleSelection("SELECT REMARKS FROM CONSTANT_DETAIL WHERE BRANCH_CODE='"+branchCode+"' AND CATEGORY_ID='129'");
		logger.info("getDirectBroker - Enter || Result: "+result);
		return 	result;
	}
} // Class
