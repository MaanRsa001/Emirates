package com.maan.services;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;
public class missippiEngine 
{

	public boolean status = false;
	public boolean multipleCheck = true;
	public boolean changeStatus = false;
	private String quoteNo = null;
	private String paths = null;
	private String url = null;
	private String productId = null;
	private String premiumSum = "0";
	private String loginBra	= "";
	private String bcid	= "";
	//Marine Declaration
	private String process = "";
	
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
	public void setQuoteNo(String quoteNo) {
		this.quoteNo = quoteNo;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean LoadingData(String QuoteNo) 
	{

		/* insert into T_TRN_POLICY1 */
		try {
			String marineExcess = null;
			String positionPremium = null;
			String OverallPremium = "0";
			String missippi_openCover = "0";
			String missippi_openCover1 = "0";
			String missippi_openPolicyId = "0";
			String Pos_BROKER_REMARKS = "";
			double saleTermValue = 0.0;
			double misipi_customer_code = 0.0;
			String commissionpercentage="";
			String cargoType[][] = new String[0][0];
			boolean cargoTypeFlag = true;
			String args1[] = new String[1];
			args1[0] = QuoteNo;
			String selection = runner.singleSelection("select count(rowid) from T_TRN_POLICY1 where POL_POLICY_ID=?",args1);
			String nextValidation = "0";
			if(process.equalsIgnoreCase("Multiple"))
				nextValidation = runner.singleSelection("select count(*) from t_trn_marine_header1 where MH_E_TRANSHIPMENT=?",args1);
			else
				multipleCheck = true;
			
			if (Integer.parseInt(selection) <= 0&&Integer.parseInt(nextValidation) <= 0) 
			{

				try
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					productId = runner.singleSelection("select distinct(product_id) from position_master where quote_no=?",args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					Pos_BROKER_REMARKS = runner.singleSelection("select (nvl(BROKER_REMARKS,' ')||' '||nvl(ADMIN_REMARKS,' ')) from position_master where quote_no=?",args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					premiumSum = runner.singleSelection("select ROUND(sum(nvl(a.war_charges,0) + nvl(a.premium,0)),2) from marine_result_details a, marine_data b where a.application_no=b.application_no and a.application_no=(select application_no from position_master where quote_no=?)",args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				try
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					marineExcess = runner.singleSelection("select nvl(excess_premium,0) from marine_data  where application_no=(select application_no from position_master where quote_no=?)",args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				String royalSettlingId	= "";
				try
				{
					String args[] = new String[2];
					args[0] = bcid;
					args[1] = QuoteNo;
					String settlingSql = "select nvl(b.rsacode,17362) from marine_policy_details a, settling_agent_master b where a.settling_agent_id=b.settling_agent_id and b.BELONGING_COUNTRY_ID=? and a.quote_no=?";
					royalSettlingId = runner.singleSelection(settlingSql,args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				if(royalSettlingId!=null)
				{
					if(royalSettlingId.length()<=0)
						royalSettlingId = "17362";
				}
				else
					royalSettlingId = "17362";
				//Marine Declaration
				String mh_e_transhipment=null;
				if(process.equalsIgnoreCase("Multiple"))
					 mh_e_transhipment = QuoteNo;
				//Marine Declaration
			
			//For RSA Issuer
				String rsaissuer = "";
				try
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					rsaissuer = runner.singleSelection("select nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO=?",args);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				// For Open Cover Missipi code added with Customer ID
				if (productId.equalsIgnoreCase("3"))
				{

					String mcc="";
					try
					{
						String args[] = new String[1];
						args[0] = QuoteNo;
						mcc = runner.singleSelection("select nvl(b.missippi_customer_code,'0') from position_master a , personal_info b  where a.customer_id=b.customer_id and a.quote_no=(select quote_no from position_master where quote_no=?)",args);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					if(mcc.length()>1&&!mcc.equals("0"))
					{
						misipi_customer_code = Double.parseDouble(mcc);
					}
					else
					{
						try
						{
							String args[] = new String[1];
							args[0] = QuoteNo;
							misipi_customer_code = Double.parseDouble(runner.singleSelection("select nvl(missippi_id,'0') from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no=?))",args));
						}
						catch (Exception e)
						{
							System.out.println("exception in"+e.toString());e.printStackTrace();
						}
					}
					String commissionpercentages = "";
					try
					{
						if(rsaissuer.equalsIgnoreCase("1"))
						{
							String args[] = new String[4];
							args[0] = productId;
							args[1] = QuoteNo;
							args[2] = productId;
							args[3] = QuoteNo;
							commissionpercentages = runner.singleSelection("select nvl(pos.DISCOUNT_PREMIUM,lud.commission) from login_user_details lud,position_master pos where lud.status='Y' and lud.product_id=? and lud.agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no=? and product_id=?)) and pos.quote_no=? and pos.product_id=lud.product_id",args);
						}
						else
						{
							commissionpercentages = ""+(getRSAIssuerCommission(QuoteNo,rsaissuer,productId));
						}
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					
					String procommissionpercentage ="0";

					String prosql = "select PRO_COMMISSION from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-mon-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=? and START_DATE<=(select to_char(entry_date,'dd-mon-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no=?))";
					try
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
						procommissionpercentage = runner.singleSelection(prosql,args);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					

					double comSen=0;
					if(procommissionpercentage==null)
						procommissionpercentage = "0";
					else if(procommissionpercentage.length()<=0)
						procommissionpercentage = "0";
					comSen = Double.parseDouble(commissionpercentages) + Double.parseDouble(procommissionpercentage);
					commissionpercentage = ""+comSen;
					
					try
					{
						String argsC[] = new String[4];
						argsC[0] = QuoteNo;
						argsC[1] = loginBra;
						argsC[2] = QuoteNo;
						argsC[3] = loginBra;
						String sql = "select cm.COMMODITY_TYPE_ID from COMMODITYMASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no=? and cm.branch_Code=? and cm.amend_id||cm.COMMODITY_ID in(select max(cm.amend_id)||cm.COMMODITY_ID from COMMODITYMASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no=? and cm.branch_Code=? group by cm.COMMODITY_ID)";
						cargoType = com.maan.services.util.runner.multipleSelection(sql,argsC);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
				}
				if (productId.equalsIgnoreCase("11"))
				{
					
					String mcc="";
					try
					{
						String args[] = new String[2];
						args[0] = QuoteNo;
						args[1] = QuoteNo;
						mcc = runner.singleSelection("select nvl(b.missippi_customer_code,'0') from open_cover_master a , personal_info b  where a.customer_id=b.customer_id and  a.proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no=?)) and a.amend_id=(select max(amend_id) from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no= ?)))",args);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					if(mcc.length()>0)
						misipi_customer_code = Double.parseDouble(mcc);

					String commissionpercentages = "";
					try
					{
						if(rsaissuer.equalsIgnoreCase("1"))
						{
							commissionpercentages = runner.singleSelection("select nvl(pos.DISCOUNT_PREMIUM,ocm.COMMISSION) from OPEN_COVER_MASTER ocm,position_master pos where ocm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select distinct OPEN_COVER_NO from POSITION_MASTER where quote_no in('"+QuoteNo+"'))) and ocm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select distinct OPEN_COVER_NO from POSITION_MASTER where quote_no in('"+QuoteNo+"')))) and pos.quote_no in('"+QuoteNo+"')");
						}
						else
						{
							commissionpercentages = ""+(getRSAIssuerCommission(QuoteNo,rsaissuer,productId));
						}
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					String procommissionpercentage= "0";
					String prosql = "select nvl(PRO_COMMISSION,0) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=?  and START_DATE<=(select to_char(entry_date,'dd-mon-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no=?) and open_cover_no=(select open_cover_no from position_master where quote_no=?) and amend_id=(select max(amend_id) from LOGIN_PRO_COMMISSION where PRODUCT_ID=? and AGENCY_CODE=(select oa_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and branch_code=?  and START_DATE<=(select to_char(entry_date,'dd-mon-yyyy') from position_master where quote_no=?) and END_DATE>=(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no=?) and open_cover_no=(select open_cover_no from position_master where quote_no=?))";

					try
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
						procommissionpercentage = runner.singleSelection(prosql,args);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					
					double comSen=0;
					if(procommissionpercentage==null)
						procommissionpercentage = "0";
					else if(procommissionpercentage.length()<=0)
						procommissionpercentage = "0";
					comSen = Double.parseDouble(commissionpercentages) + Double.parseDouble(procommissionpercentage);
					commissionpercentage = ""+comSen;
					
					try
					{
						String argsC[] = new String[4];
						argsC[0] = QuoteNo;
						argsC[1] = QuoteNo;
						argsC[2] = QuoteNo;
						argsC[3] = QuoteNo;
						String sqlC = "select cm.COMMODITY_TYPE_ID from OPEN_COVER_COMMODITY_MASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no=? and cm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from POSITION_MASTER where quote_no=?)) and cm.amend_id||cm.COMMODITY_ID in(select max(cm.amend_id)||cm.COMMODITY_ID from OPEN_COVER_COMMODITY_MASTER cm,MARINE_RESULT_DETAILS mrd,POSITION_MASTER pm where cm.COMMODITY_ID=mrd.COMMODITY_ID and mrd.APPLICATION_NO=pm.APPLICATION_NO and pm.quote_no=? and cm.PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from POSITION_MASTER where quote_no=?)) group by cm.COMMODITY_ID)";
						cargoType = com.maan.services.util.runner.multipleSelection(sqlC,argsC);
					}
					catch (Exception e)
					{
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
				}
				if(cargoType.length<=0)
				{
					cargoTypeFlag = false;
				}
				
				try
				{
					String args[] = new String[2];
					args[0] = QuoteNo;
					args[1] = QuoteNo;
					missippi_openCover = runner.singleSelection("select nvl(missippi_opencover_no,'0') from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no=?)) and amend_id=(select max(amend_id) from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no=?)))",args);

					
					missippi_openCover1 = missippi_openCover;
					if(missippi_openCover.length()>0)
						missippi_openCover = missippi_openCover.substring(missippi_openCover.length() - 3, missippi_openCover.length());
					else
						missippi_openCover = "0";
					

				} catch (Exception e) {
					missippi_openCover = "0";
					System.out.println("  missippi_openCover  ERROR   "
							+ e.toString());
				}
				try 
				{
					String args[] = new String[2];
					args[0] = QuoteNo;
					args[1] = QuoteNo;
					missippi_openPolicyId = runner.singleSelection("select nvl(MISSISSIPI_OPEN_POLICY_ID,'0') from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no=?)) and amend_id=(select max(amend_id) from open_cover_master where proposal_no=(select proposal_no from open_cover_position_master where open_cover_no=(select open_cover_no from position_master where quote_no=?)))",args);

				}
				catch (Exception e) 
				{
					missippi_openPolicyId = "0";
					System.out.println("  missippi_openPolicyId  ERROR   "+ e.toString());e.printStackTrace();
				}
				try 
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					positionPremium = runner.singleSelection("select nvl(premium,0)+nvl(excess_premium,0) from position_master where quote_no=?",args);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}

				try 
				{
					marineExcess = ""+ (Double.parseDouble(premiumSum) + Double.parseDouble(marineExcess));

					if (Double.parseDouble(marineExcess) <= Double.parseDouble(positionPremium))
						OverallPremium = ""+ (Double.parseDouble(positionPremium) - Double.parseDouble(marineExcess));

				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}

				String firstId = getMaxId(productId,loginBra,"DataTransfer");
				String secondId = getMaxId(productId,loginBra,"Transfer");
				//firstId = "" + (Integer.parseInt(firstId) + 1);
				//secondId = "" + (Integer.parseInt(secondId) + 1);
				
				System.out.println("----firstId--------->" + firstId);
				System.out.println("----secondId--------->" + secondId);

				String agency_code = "";
				try 
				{
					String args[] = new String[1];
					args[0] = QuoteNo;
					agency_code = runner.singleSelection("select substr(nvl(rsa_broker_code,'0'),1,4) from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no=?))",args);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				
				String bcode = loginBra;
				String acode = agency_code.equals("206") ? "16" : agency_code;
				//String bcd = bcode.equals("020") ? "56" : "20";
				String bcd = getBranchValues(loginBra,bcid);
	
				//For Commodites 
				String[][] commodities = new String[0][0];
				try 
				{
					String args[] = new String[5];
					args[0] = QuoteNo;
					args[1] = loginBra;
					args[2] = loginBra;
					args[3] = bcid;
					args[4] = bcid;
					commodities = runner.multipleSelection("select cm.commodity_id, nvl(a.warrate,'0'), nvl(a.rate,0), nvl(a.sum_insured,0), nvl(a.premium,0), substr(a.description,1,99), b.exchange_rate, substr(nvl(a.package_description,' '),1,99), nvl(cm.warranty_id,0), nvl(cm.exclusion_id,0), nvl(c.ep_warranties_conditions,0), b.cover_id, b.extra_cover_id, cm.rsacode, cm.RAG,b.mode_of_transport,nvl(c.SP_WARRANTIES_CONDITIONS,0),nvl(b.SEA_OPTIONS,' '),nvl(b.WAREHOUSE_TO_WAREHOUSE,'No'),nvl(b.WARE_TO_WARE_FINAL_DEST,'No') from commoditymaster cm, marine_result_details a,marine_data b, COUNTRY_MASTER_DETAIL c  where cm.status in ('Y','R') and a.application_no=(select application_no from position_master where quote_no=?) and b.application_no=a.application_no and cm.commodity_id=a.commodity_id and cm.BRANCH_CODE=? and cm.amend_id||cm.commodity_id in (select max(cm.amend_id)||cm.commodity_id from commoditymaster cm where to_date(cm.effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY') and cm.status in('Y','R') and cm.commodity_id=a.commodity_id and cm.BRANCH_CODE=? group by cm.commodity_id) and to_date(cm.effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY') and c.country_id=b.final_destination_country_id and c.BELONGING_COUNTRY_ID=? and c.amend_id||c.country_id in (select max(amend_id)|| country_id from COUNTRY_MASTER_DETAIL where to_date(effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY') and status in('Y','R') and BELONGING_COUNTRY_ID=? group by country_id) and to_date (c.effective_date,'dd-MON-YYYY')<=to_date(SYSDATE,'dd-MON-YYYY') group by cm.commodity_id,cm.commodity_name,cm.amend_id,cm.sno__,cm.rsacode,a.warrate,a.rate,a.sum_insured,a.premium,a.description,b.exchange_rate,a.package_description,nvl(cm.warranty_id,0),nvl(cm.exclusion_id,0),c.ep_warranties_conditions,b.cover_id,b.extra_cover_id,cm.RAG,b.mode_of_transport,c.SP_WARRANTIES_CONDITIONS,b.SEA_OPTIONS,b.WAREHOUSE_TO_WAREHOUSE,b.WARE_TO_WARE_FINAL_DEST order by cm.commodity_name",args);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				


				String warrantyId = "";
				
				try 
				{
					String args[] = new String[2];
					args[0] = loginBra;
					args[1] = QuoteNo;
					//For Sale term
					saleTermValue = Double.parseDouble(runner.singleSelection("select b.sale_term_value  from marine_data a, sale_term_master b where a.sale_term_id=b.sale_term_id and b.BRANCH_CODE=? and a.application_no = (select application_no from position_master where quote_no=?)",args));
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}

				//FOr Seetilng Agent
				    String settilingJames = "";
					String james1 = "";
					String james2 = "";
					try 
					{
						String args[] = new String[1];
						args[0] = QuoteNo;
						james1 = runner.singleSelection("(select nvl(settling_agent_name,' ') from marine_policy_details where quote_no=?)",args);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					try 
					{
						String args[] = new String[2];
						args[0] = bcid;
						args[1] = QuoteNo;
						james2 = runner.singleSelection("(select substr((select nvl(b.settling_agent_name,'') || ', ' || nvl(b.address1,'') || nvl(b.address2,'') from marine_policy_details a,settling_agent_master b where a.settling_agent_id=b.settling_agent_id and b.BELONGING_COUNTRY_ID=? and a.quote_no=?),1,299)from dual)",args);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					if(james1 == null)
						james1 = "";
					else
						james1 = james1.replaceAll("'","''");
					if(james2 == null)
						james2 = "";
					settilingJames = james1 + james2;
					
					if (settilingJames.length() > 299)
						settilingJames = settilingJames.substring(1, 299);
					if (settilingJames.length() <= 5)
					{
						String settilingRoyal[][] = new String[0][0];
						try 
						{
							String args[] = new String[1];
							args[0] = loginBra;
							settilingRoyal = runner.multipleSelection("select ADDRESS1,ADDRESS2,CITY,COUNTRY from BRANCH_MASTER where BRANCH_CODE=?",args);
						}
						catch (Exception e) 
						{
							e.printStackTrace();
						}
						if(settilingRoyal.length>0)
							settilingJames = settilingRoyal[0][0]+", "+settilingRoyal[0][1]+", "+settilingRoyal[0][2]+", "+settilingRoyal[0][3];
						if (settilingJames.length() > 299)
							settilingJames = settilingJames.substring(1, 299);
					}
					//For Carrier Name
					String s1 = "";
					try 
					{
						String args[] = new String[1];
						args[0] = QuoteNo;
						s1 = runner.singleSelection("select nvl(substr(carrier_name,1,20),'') from marine_policy_details where quote_no=?",args);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					s1 = s1 == null ? "" : s1;
					
					//For Customer Address - t_mas_cash_customer1
					String cusAdd[][] = new String[0][0];
					String fullCusAdd = "PO Box: ";
					try 
					{
						String args[] = new String[1];
						args[0] = QuoteNo;
						cusAdd = runner.multipleSelection("select a.pobox,a.address1,a.address2,a.emirate,a.country from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no=?",args);
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					if(cusAdd.length>0)
					{
						if(cusAdd[0][0]!=null)
							fullCusAdd = fullCusAdd +cusAdd[0][0]+", ";
						if(cusAdd[0][1]!=null)
							fullCusAdd = fullCusAdd +cusAdd[0][1]+", ";
						if(cusAdd[0][2]!=null)
							fullCusAdd = fullCusAdd +cusAdd[0][2]+", ";
						if(cusAdd[0][3]!=null)
							fullCusAdd = fullCusAdd +cusAdd[0][3]+",";
						if(cusAdd[0][4]!=null)
							fullCusAdd = fullCusAdd +cusAdd[0][4]+".";
					}
					if(fullCusAdd.length()>199)
						fullCusAdd = fullCusAdd.substring(0,199);
					

				//For Customer Address - t_mas_cash_customer1
				//For Not standard text
				//
				String finalClauses = "";
				for (int i = 0; i < commodities.length; i++)
				{
					if (productId.equals("3"))
						finalClauses = finalClauses+getAllClausesOne(QuoteNo,loginBra,bcid,commodities[i][11],commodities[i][15],commodities[i][9],commodities[i][16],commodities[i][10],commodities[i][8],commodities[i][17],commodities[i][18],commodities[i][19],commodities[i][12]);
					else if (productId.equals("11"))
						finalClauses = finalClauses+getAllClausesOpenCover(QuoteNo,loginBra,bcid,commodities[i][11],commodities[i][15],commodities[i][9],commodities[i][16],commodities[i][10],commodities[i][8],commodities[i][17],commodities[i][12]);
					
				}

				setQuoteNo(QuoteNo);
				
				//Block For getting Constant values for all tables start
				HashMap trnPolicy = new HashMap();
				HashMap trnPremium1 = new HashMap();
				HashMap trnPremium2 = new HashMap();
				HashMap trnMarine = new HashMap();
				HashMap trnMHeader = new HashMap();
				HashMap trnWar = new HashMap();
				HashMap trnCon = new HashMap();
				HashMap trnExc = new HashMap();
				HashMap trnTrans = new HashMap();
				HashMap trnCar = new HashMap();
				HashMap trnText = new HashMap();
				HashMap trnLc = new HashMap();
				HashMap trnHdr = new HashMap();
				HashMap trnDtl = new HashMap();
				HashMap trnDtl1 = new HashMap();
				HashMap tmas = new HashMap();
				HashMap tmascus = new HashMap();
				 trnPolicy = getMissippiConsatnt("T_TRN_POLICY1","1",bcid);
				 trnPremium1 = getMissippiConsatnt("t_trn_premium1","1",bcid);
				 trnPremium2 = getMissippiConsatnt("t_trn_premium1","2",bcid);
				 trnMarine = getMissippiConsatnt("t_trn_marine_detail1","1",bcid);
				 trnMHeader = getMissippiConsatnt("t_trn_marine_header1","1",bcid);
				 trnWar = getMissippiConsatnt("t_trn_policy_warranty","1",bcid);
				 trnCon = getMissippiConsatnt("t_trn_policy_condition1","1",bcid);
				 trnExc = getMissippiConsatnt("t_trn_policy_exclusion","1",bcid);
				 trnTrans = getMissippiConsatnt("t_trn_marine_transit1","1",bcid);
				 trnCar = getMissippiConsatnt("t_trn_marine_carrier1","1",bcid);
				 trnText = getMissippiConsatnt("T_TRN_NON_STD_TEXT1","1",bcid);
				 trnLc = getMissippiConsatnt("t_trn_marine_lc1","1",bcid);
				 trnHdr = getMissippiConsatnt("T_TRN_HDR_DEBIT_NOTE1","1",bcid);
				 trnDtl = getMissippiConsatnt("T_TRN_DTL_DEBIT_NOTE1","1",bcid);
				 trnDtl1 = getMissippiConsatnt("T_TRN_DTL_DEBIT_NOTE1","2",bcid);
				 tmas = getMissippiConsatnt("t_mas_cash_customer1","1",bcid);
				 tmascus = getMissippiConsatnt("t_mas_customer1","1",bcid);
				//Block For getting Constant values for all tables end
				 HashMap queryCollection = new HashMap();
				 queryCollection.put("Count","0");
				 String cargoValue = ((String)trnPolicy.get("POL_COVER_NOTE_MIN"));
				 if(cargoTypeFlag)
				 {
					 cargoValue = getCargoRSACode(cargoType,loginBra);
				 }
				 queryCollection = insertionMissippiEngine("insert into T_TRN_POLICY1( POL_POLICY_ID,POL_LOCATION_CODE, POL_CLASS_CODE,POL_DOCUMENT_CODE, POL_POLICY_NO, POL_POLICY_YEAR, POL_REF_POLICY_NO, POL_REF_POLICY_YEAR, POL_POLICY_TYPE, POL_PROPOSAL_NO, POL_PROPOSAL_DATE, POL_ISSUE_DATE, POL_EXPIRY_DATE, POL_CUSTOMER_ID, POL_SOURCE_OF_BUSINESS, POL_AGENT_ID, POL_COMMISION_ID, POL_COINSURANCE_INDICATOR, POL_COINSURANCE_TYPE, POL_COVER_NOTE_NO, POL_COVER_NOTE_DATE, POL_PREMIUM, POL_GOVERNMENT_TAX, POL_POLICY_FEES, POL_CURRENCY_CODE, POL_EXCHANGE_RATE, POL_PROCESSED_DATE, POL_STATUS, POL_FINANCIAL_INT_IND, POL_APPROVED_BY, POL_USER_ID, POL_EFFECTIVE_DATE, POL_PRINT_DATE, POL_CUT_CODE, POL_SPECIAL_TYPE, POL_COVER_NOTE_HOUR, POL_COVER_NOTE_MIN, POL_ISSUE_HOUR, POL_ISSUE_MIN, POL_EFFECTIVE_HOUR, POL_EFFECTIVE_MIN, POL_EXPIRY_HOUR, POL_EXPIRY_MIN, POL_CERTIFICATE_NO, POL_CERTIFICATE_DATE, POL_VALIDITY_START_DATE, POL_VALIDITY_EXPIRY_DATE, POL_ENDT_ID, POL_ENDT_EFFECTIVE_DATE, POL_ENDT_EXPIRY_DATE, POL_RATING_TYPE, POL_CCG_CODE, POL_ENDT_NO, POL_CTY_CODE, POL_REG_CODE, POL_POLICY_TERM, POL_REF_POLICY_ID, POL_ACCEXEC_CODE, POL_INSURED_ID, POL_QUOTATION_NO, POL_QUOTATION_DATE, POL_BR_CODE, POL_DCT_CODE, POL_PREPARED_BY, POL_PREPARED_DT, POL_MODIFIED_BY, POL_MODIFIED_DT, POL_POSTED_DATE, POL_REFRESH_DATE, POL_CONC_POLICY_NO)values ((select quote_no from position_master where quote_no='"
										+ QuoteNo
										+ "'),'"+loginBra+"',"+((String)trnPolicy.get("POL_CLASS_CODE"))+","+((String)trnPolicy.get("POL_DOCUMENT_CODE"))+",(select to_number(substr(policy_no,9,15)) from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select to_char(inception_date,'YYYY') from position_master where quote_no='"
										+ QuoteNo
										+ "'),"+((String)trnPolicy.get("POL_REF_POLICY_NO"))+","+((String)trnPolicy.get("POL_REF_POLICY_YEAR"))+",(select rsacode from product_master where product_id='"	+ productId + "' and BRANCH_CODE='"+loginBra+"'),(select nvl(BUSINESS_TYPE,'1') from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select inception_date from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select inception_date from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select add_months(inception_date,12)-1 from position_master where quote_no='"
										+ QuoteNo
										+ "'),"
										+ misipi_customer_code
										+ ","+((String)trnPolicy.get("POL_SOURCE_OF_BUSINESS"))+","+((String)trnPolicy.get("POL_AGENT_ID"))+","+commissionpercentage+","+((String)trnPolicy.get("POL_COINSURANCE_INDICATOR"))+","+((String)trnPolicy.get("POL_COINSURANCE_TYPE"))+","+((String)trnPolicy.get("POL_COVER_NOTE_NO"))+","+((String)trnPolicy.get("POL_COVER_DATE"))+",(select ROUND(sum(nvl(excess_premium,0)+'"
										+ (Double.parseDouble(premiumSum) + Double
												.parseDouble(OverallPremium))
										+ "'),2) from marine_data  where application_no=(select application_no from position_master where quote_no='"
										+ QuoteNo
										+ "')),"+((String)trnPolicy.get("POL_GOVERNMENT_TAX"))+","+((String)trnPolicy.get("POL_POLICY_FEES"))+","+((String)trnPolicy.get("POL_CURRENCY_CODE"))+","+((String)trnPolicy.get("POL_EXCHANGE_RATE"))+",(select inception_date from position_master where quote_no='"
										+ QuoteNo
										+ "'),"+((String)trnPolicy.get("POL_STATUS"))+","+((String)trnPolicy.get("POL_FINANCIAL_INT_IND"))+",(select nvl(approved_prepared_by,'0') from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no='"
										+ QuoteNo
										+ "'))),"
										+ bcd
										+ ",(select inception_date from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select nvl(debit_note_date,'') from position_master where quote_no='"
										+ QuoteNo
										+ "'),"+((String)trnPolicy.get("POL_CUT_CODE"))+","+((String)trnPolicy.get("POL_SPECIAL_TYPE"))+","+((String)trnPolicy.get("POL_COVER_NOTE_HOUR"))+",'"+cargoValue+"',"+((String)trnPolicy.get("POL_ISSUE_HOUR"))+","+((String)trnPolicy.get("POL_ISSUE_MIN"))+","+((String)trnPolicy.get("POL_EFFECTIVE_HOUR"))+","+((String)trnPolicy.get("POL_EFFECTIVE_MIN"))+",'"
										+ ("3".equalsIgnoreCase(productId) ? ""
												: (missippi_openCover))
										+ "','"
										+ ("3".equalsIgnoreCase(productId) ? ""
												: (missippi_openPolicyId))
										+ "',"+((String)trnPolicy.get("POL_CERTIFICATE_NO"))+",to_date('"+(((String)trnPolicy.get("POL_CERTIFICATE_DATE")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_CERTIFICATE_DATE"))+"','dd-mm-yyyy'),(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
										+ QuoteNo
										+ "'),'"+((String)trnPolicy.get("POL_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnPolicy.get("POL_ENDT_ID"))+",to_date('"+(((String)trnPolicy.get("POL_ENDT_EFFECTIVE_DATE")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_ENDT_EFFECTIVE_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnPolicy.get("POL_ENDT_EXPIRY_DATE")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_ENDT_EXPIRY_DATE"))+"','dd-mm-yyyy'),"+((String)trnPolicy.get("POL_RATING_TYPE"))+","+((String)trnPolicy.get("POL_CCG_CODE"))+","+((String)trnPolicy.get("POL_ENDT_NO"))+","+((String)trnPolicy.get("POL_CTY_CODE"))+","+((String)trnPolicy.get("POL_REG_CODE"))+","+((String)trnPolicy.get("POL_POLICY_TERM"))+","+((String)trnPolicy.get("POL_REF_POLICY_ID"))+","+((String)trnPolicy.get("POL_ACCEXEC_CODE"))+",'"
										+ Integer.parseInt(firstId)
										+ "',(select quote_no from position_master where quote_no='"
										+ QuoteNo
										+ "'),(select nvl(entry_date,'') from marine_data where application_no=(select application_no from position_master where quote_no='"
										+ QuoteNo
										+ "')),"
										+ acode
										+ ","
										+ ("3".equalsIgnoreCase(productId) ? "0"
												: ("1"))
										+ ",(select nvl(approved_prepared_by,'0') from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no='"
										+ QuoteNo
										+ "'))),(select inception_date from position_master where quote_no='"
										+ QuoteNo
										+ "'),"+((String)trnPolicy.get("POL_MODIFIED_BY"))+",to_date('"+(((String)trnPolicy.get("POL_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_MODIFIED_DT"))+"','dd-mm-yyyy'),to_date('"+(((String)trnPolicy.get("POL_POSTED_DATE")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_POSTED_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnPolicy.get("POL_REFRESH_DATE")).equalsIgnoreCase("null")?"":(String)trnPolicy.get("POL_REFRESH_DATE"))+"','dd-mm-yyyy'),(select policy_no from position_master where quote_no='"
										+ QuoteNo + "'))",QuoteNo,"T_TRN_POLICY1",queryCollection);
			
			String commoditiesCollection = "";
			for (int i = 0; i < commodities.length; i++) 
			{
					commoditiesCollection = commoditiesCollection+commodities[i][5]+",";
					if (i != 0) 
					{
						secondId = getMaxId(productId,loginBra,"Transfer");;
						//setMaxId(productId,loginBra,"Transfer", secondId);
					}
					commodities[i][3] = ""+ (Double.parseDouble(commodities[i][3]) + (Double.parseDouble(commodities[i][3]) * (saleTermValue / 100)));
	

					queryCollection = insertionMissippiEngine("insert into t_trn_premium1 (PRM_POLICY_ID,PRM_CL_CODE,PRM_PT_CODE,PRM_BASIC_RSK_CODE,PRM_BASIC_RSK_ID,PRM_COV_CODE,PRM_CT_CODE,PRM_CST_CODE,PRM_RSK_CODE,PRM_RT_CODE,PRM_RC_CODE,PRM_RSC_CODE,PRM_RSK_ID,PRM_SUM_INSURED,PRM_RATE,PRM_PREMIUM,PRM_COMPULSORY_EXCESS,PRM_VOLUNTARY_EXCESS,PRM_VALIDITY_START_DATE,PRM_VALIDITY_EXPIRY_DATE,PRM_ENDT_ID,PRM_EXCESS_RATE,PRM_RI_RSK_CODE,PRM_SI_IND,PRM_STATUS,PRM_EFFECTIVE_DATE,PRM_EXPIRY_DATE,PRM_POL_SUM_INSURED,PRM_SITYPE_CODE,PRM_FN_CODE,PRM_SUM_INSURED_CURR,PRM_PREMIUM_CURR,PRM_PREPARED_BY,PRM_PREPARED_DT,PRM_MODIFIED_BY,PRM_MODIFIED_DT,PRM_RI_LOC_CODE,PRM_RATE_TYPE,PRM_OLD_PREMIUM,PRM_OLD_SUM_INSURED)values ((select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnPremium1.get("PRM_CL_CODE"))+", (select rsacode from product_master where product_id='"	+ productId + "' and BRANCH_CODE='"+loginBra+"'),"+((String)trnPremium1.get("PRM_BASIC_RSK_CODE"))+",'"
							+ Integer.parseInt(firstId)
							+ "',"+((String)trnPremium1.get("PRM_COV_CODE"))+",(select nvl(rsacode,1) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id=(select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "'))),(select nvl(rsacode,0) from cover_master where BRANCH_CODE='"+loginBra+"' and cover_id=(select cover_id from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "'))),"+((String)trnPremium1.get("PRM_RSK_CODE"))+",'"
							+ commodities[i][13]
							+ "',"+((String)trnPremium1.get("PRM_RC_CODE"))+","+((String)trnPremium1.get("PRM_RSC_CODE"))+",'"
							+ (Integer.parseInt(secondId))
							+ "',(select (nvl(exchange_rate,0)*nvl(ROUND("
							+ commodities[i][3]
							+ ",2),0))+nvl(ROUND(total_tolerance_charges,2),0) from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')),'"
							+ commodities[i][2]
							+ "',(select nvl("
							+ (i == 0 ? "excess_premium" : "0")
							+ ",0)+nvl(ROUND("
							+ commodities[i][4]
							+ ",2),0)+nvl("
							+ (i == 0 ? OverallPremium : "0")
							+ ",0) from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnPremium1.get("PRM_COMPULSORY_EXCESS"))+","+((String)trnPremium1.get("PRM_VOLUNTARY_EXCESS"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
							+ QuoteNo
							+ "'),'"+((String)trnPremium1.get("PRM_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnPremium1.get("PRM_ENDT_ID"))+","+((String)trnPremium1.get("PRM_EXCESS_RATE"))+","+((String)trnPremium1.get("PRM_RI_RSK_CODE"))+","+((String)trnPremium1.get("PRM_SI_IND"))+","+((String)trnPremium1.get("PRM_STATUS"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select add_months(inception_date,12)-1 from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnPremium1.get("PRM_POL_SUM_INSURED"))+","+((String)trnPremium1.get("PRM_SITYPE_CODE"))+","+((String)trnPremium1.get("PRM_FN_CODE"))+","+((String)trnPremium1.get("PRM_SUM_INSURED_CURR"))+","+((String)trnPremium1.get("PRM_PREMIUM_CURR"))+","+((String)trnPremium1.get("PRM_PREPARED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnPremium1.get("PRM_MODIFIED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo + "'),"+((String)trnPremium1.get("PRM_RI_LOC_CODE"))+","+((String)trnPremium1.get("PRM_RATE_TYPE"))+","+((String)trnPremium1.get("PRM_OLD_PREMIUM"))+","+((String)trnPremium1.get("PRM_OLD_SUM_INSURED"))+")",QuoteNo,"t_trn_premium1",queryCollection);

				
					if (commodities[i][1] != null&& Double.parseDouble(commodities[i][1]) != 0) 
					{

						queryCollection = insertionMissippiEngine("insert into t_trn_premium1 (PRM_POLICY_ID,PRM_CL_CODE,PRM_PT_CODE,PRM_BASIC_RSK_CODE,PRM_BASIC_RSK_ID,PRM_COV_CODE,PRM_CT_CODE,PRM_CST_CODE,PRM_RSK_CODE,PRM_RT_CODE,PRM_RC_CODE,PRM_RSC_CODE,PRM_RSK_ID,PRM_SUM_INSURED,PRM_RATE,PRM_PREMIUM,PRM_COMPULSORY_EXCESS,PRM_VOLUNTARY_EXCESS,PRM_VALIDITY_START_DATE,PRM_VALIDITY_EXPIRY_DATE,PRM_ENDT_ID,PRM_EXCESS_RATE,PRM_RI_RSK_CODE,PRM_SI_IND,PRM_STATUS,PRM_EFFECTIVE_DATE,PRM_EXPIRY_DATE,PRM_POL_SUM_INSURED,PRM_SITYPE_CODE,PRM_FN_CODE,PRM_SUM_INSURED_CURR,PRM_PREMIUM_CURR,PRM_PREPARED_BY,PRM_PREPARED_DT,PRM_MODIFIED_BY,PRM_MODIFIED_DT,PRM_RI_LOC_CODE,PRM_RATE_TYPE,PRM_OLD_PREMIUM,PRM_OLD_SUM_INSURED)values ((select quote_no from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnPremium2.get("PRM_CL_CODE"))+",(select rsacode from product_master where product_id='"	+ productId + "' and BRANCH_CODE='"+loginBra+"'),"+((String)trnPremium2.get("PRM_BASIC_RSK_CODE"))+",'"
								+ Integer.parseInt(firstId)
								+ "',"+((String)trnPremium2.get("PRM_COV_CODE"))+",(select nvl(rsacode,1) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id=(select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "'))),"+((String)trnPremium2.get("PRM_CST_CODE"))+","+((String)trnPremium2.get("PRM_RSK_CODE"))+",'"
								+ commodities[i][13]
								+ "',"+((String)trnPremium2.get("PRM_RC_CODE"))+","+((String)trnPremium2.get("PRM_RSC_CODE"))+",'"
								+ (Integer.parseInt(secondId))
								+ "',"+((String)trnPremium2.get("PRM_SUM_INSURED"))+",(select nvl(warrate,'0') from marine_result_details where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "') and commodity_id='"
								+ commodities[i][0]
								+ "'),(select nvl(ROUND(war_charges,2),0) from marine_result_details where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "') and commodity_id='"
								+ commodities[i][0]
								+ "'),"+((String)trnPremium2.get("PRM_COMPULSORY_EXCESS"))+","+((String)trnPremium2.get("PRM_VOLUNTARY_EXCESS"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
								+ QuoteNo
								+ "'),'"+((String)trnPremium2.get("PRM_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnPremium2.get("PRM_ENDT_ID"))+","+((String)trnPremium2.get("PRM_EXCESS_RATE"))+","+((String)trnPremium2.get("PRM_RI_RSK_CODE"))+","+((String)trnPremium2.get("PRM_SI_IND"))+","+((String)trnPremium2.get("PRM_STATUS"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo
								+ "'),(select add_months(inception_date,12)-1 from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnPremium2.get("PRM_POL_SUM_INSURED"))+","+((String)trnPremium2.get("PRM_SITYPE_CODE"))+","+((String)trnPremium2.get("PRM_FN_CODE"))+","+((String)trnPremium2.get("PRM_SUM_INSURED_CURR"))+","+((String)trnPremium2.get("PRM_PREMIUM_CURR"))+","+((String)trnPremium2.get("PRM_PREPARED_BY"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnPremium2.get("PRM_MODIFIED_BY"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo + "'),"+((String)trnPremium2.get("PRM_RI_LOC_CODE"))+","+((String)trnPremium2.get("PRM_RATE_TYPE"))+","+((String)trnPremium2.get("PRM_OLD_PREMIUM"))+","+((String)trnPremium2.get("PRM_OLD_SUM_INSURED"))+")",QuoteNo,"t_trn_premium1",queryCollection);

					}

					
					commodities[i][7] = commodities[i][7] == null ? "":commodities[i][7];
					// Insert t_trn_marine_detail1
					String cmdjames = (commodities[i][5].replaceAll("'", "''").replaceAll("\"", "&#34;"))+ " in "
							+ (commodities[i][7].replaceAll("'", "''").replaceAll("\"", "&#34;"));
					String cType =  cargoTypeFlag ? getCargoType(cargoType[i][0],loginBra) : "" ;
					queryCollection = insertionMissippiEngine("insert into t_trn_marine_detail1(MD_POLICY_ID,MD_ENDT_ID,MD_DECLARATION_ID,MD_COMMODITY_ID,MD_SERIAL_NO,MD_COMMODITY_GROUP_CODE,MD_COMMODITY_CODE,MD_A_COMMODITY_DESC,MD_E_COMMODITY_DESC,MD_SUM_INSURED,MD_FC_SUM_INSURED,MD_SUM_INSURED_LOADING,MD_ADDL_LOADING,MD_A_PACKING_DESC,MD_E_PACKING_DESC,MD_PACKING_NO,MD_NO_OF_PACKAGES,MD_A_CONTAINER_NO,MD_E_CONTAINER_NO,MD_MI_INVOICE_ID,MD_GEO_CODE,MD_BASIC_RISK_CODE,MD_RISK_CODE,MD_RI_RSK_CODE,MD_VALIDITY_START_DATE,MD_VALIDITY_EXPIRY_DATE,MD_STATUS,MD_VALUATION_BASIS,MD_PREPARED_BY,MD_PREPARED_DT,MD_MODIFIED_BY,MD_MODIFIED_DT,MD_START_DATE,MD_END_DATE,MD_CARGO_TYPE) values((select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnMarine.get("MD_ENDT_ID"))+",'"
							+ Integer.parseInt(firstId)
							+ "','"
							+ Integer.parseInt(secondId)
							+ "',"+(i+1)+","+((String)trnMarine.get("MD_COMMODITY_GROUP_CODE"))+",'"
							+ commodities[i][13]
							+ "',('"+(((String)trnMarine.get("MD_A_COMMODITY_DESC")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_A_COMMODITY_DESC"))+"'),'"
							+ cmdjames
							+ "',(select (nvl(exchange_rate,0)*nvl(ROUND("
							+ commodities[i][3]
							+ ",2),0)+nvl(ROUND(total_tolerance_charges,2),0)) from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')),(select nvl(ROUND(sum(sum_insured),2),0) from marine_result_details where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')),(select nvl(sale_term_value,0) from sale_term_master where BRANCH_CODE='"+loginBra+"' and sale_term_id=(select sale_term_id from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "'))),(select tolerance_value from TOLERANCE_MASTER a , marine_data b where a.tolerance_id=b.tolerance_id and a.BRANCH_CODE='"+loginBra+"' and b.application_no=(select application_no from position_master where quote_no='"+QuoteNo+"')),('"+(((String)trnMarine.get("MD_A_PACKING_DESC")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_A_PACKING_DESC"))+"'),'"
							+ commodities[i][14]
							+ "',('"+(((String)trnMarine.get("MD_PACKING_NO")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_PACKING_NO"))+"'),"+((String)trnMarine.get("MD_NO_OF_PACKAGES"))+",('"+(((String)trnMarine.get("MD_A_CONTAINER_NO")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_A_CONTAINER_NO"))+"'),('"+(((String)trnMarine.get("MD_E_CONTAINER_NO")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_E_CONTAINER_NO"))+"'),"+((String)trnMarine.get("MD_MI_INVOICE_ID"))+","+((String)trnMarine.get("MD_GEO_CODE"))+","+((String)trnMarine.get("MD_BASIC_RISK_CODE"))+","+((String)trnMarine.get("MD_RISK_CODE"))+", "+((String)trnMarine.get("MD_RI_RSK_CODE"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
							+ QuoteNo
							+ "'),'"+((String)trnMarine.get("MD_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnMarine.get("MD_STATUS"))+",(select nvl(rsacode,0) from sale_term_master where BRANCH_CODE='"+loginBra+"' and sale_term_id=(select sale_term_id from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "'))),"+((String)trnMarine.get("MD_PREPARED_BY"))+",to_date('"+(((String)trnMarine.get("MD_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnMarine.get("MD_MODIFIED_BY"))+",to_date('"+(((String)trnMarine.get("MD_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnMarine.get("MD_MODIFIED_DT"))+"','dd-mm-yyyy'),(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select add_months(inception_date,12)-1 from position_master where quote_no='"
							+ QuoteNo + "'),'"+cType+"')",QuoteNo,"t_trn_marine_detail1",queryCollection);

					// Insert t_trn_marine_header1
					java.util.StringTokenizer tokens = new java.util.StringTokenizer(commodities[i][8], ",");
					while (tokens.hasMoreTokens()) 
					{

						warrantyId = "" + tokens.nextElement();
						queryCollection = insertionMissippiEngine("insert into t_trn_policy_warranty (TPW_POLICY_ID,TPW_ENDT_ID,TPW_CL_CODE,TPW_PT_CODE,TPW_CODE,TPW_COV_CODE,TPW_CT_CODE,TPW_CST_CODE,TPW_VALIDITY_START_DATE,TPW_VALIDITY_END_DATE,TPW_RSK_ID,TPW_STATUS,TPW_PREPARED_BY,TPW_PREPARED_DT,TPW_MODIFIED_BY,TPW_MODIFIED_DT,TPW_AMOUNT,TPW_PRINT_FLAG)values((select quote_no from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnWar.get("TPW_ENDT_ID"))+","+((String)trnWar.get("TPW_CL_CODE"))+",(select nvl(rsacode,0) from product_master where BRANCH_CODE='"+loginBra+"' and product_id=(select product_id from position_master where quote_no='"
								+ QuoteNo
								+ "')),'"
								+ warrantyId
								+ "',"+((String)trnWar.get("TPW_COV_CODE"))+",(select nvl(rsacode,0) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "')))),"+((String)trnWar.get("TPW_CST_CODE"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo
								+ "'),'"+((String)trnWar.get("TPW_VALIDITY_END_DATE"))+"','"
								+ commodities[i][13]
								+ "',"+((String)trnWar.get("TPW_STATUS"))+","+((String)trnWar.get("TPW_PREPARED_BY"))+",to_date('"+(((String)trnWar.get("TPW_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnWar.get("TPW_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnWar.get("TPW_MODIFIED_BY"))+",to_date('"+(((String)trnWar.get("TPW_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnWar.get("TPW_MODIFIED_DT"))+"','dd-mm-yyyy'),"+((String)trnWar.get("TPW_AMOUNT"))+","+((String)trnWar.get("TPW_PRINT_FLAG"))+")",QuoteNo,"t_trn_policy_warranty",queryCollection);
						

						queryCollection = insertionMissippiEngine("insert into  t_trn_policy_condition1(TPC_POLICY_ID,TPC_ENDT_ID,TPC_CL_CODE,TPC_PT_CODE,TPC_CODE,TPC_COV_CODE,TPC_CT_CODE,TPC_CST_CODE,TPC_VALIDITY_START_DATE,TPC_VALIDITY_END_DATE,TPC_RSK_ID,TPC_STATUS,TPC_PREPARED_BY,TPC_PREPARED_DT,TPC_MODIFIED_BY,TPC_MODIFIED_DT,TPC_AMOUNT,TPC_PRINT_FLAG)values((select quote_no from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnCon.get("TPC_ENDT_ID"))+","+((String)trnCon.get("TPC_CL_CODE"))+",(select nvl(rsacode,0) from product_master where BRANCH_CODE='"+loginBra+"' and product_id=(select product_id from position_master where quote_no='"
								+ QuoteNo
								+ "')),'"
								+ warrantyId
								+ "',"+((String)trnCon.get("TPC_COV_CODE"))+",(select nvl(rsacode,0) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "')))),"+((String)trnCon.get("TPC_CST_CODE"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo
								+ "'),'"+((String)trnCon.get("TPC_VALIDITY_END_DATE"))+"','"
								+ commodities[i][13]
								+ "',"+((String)trnCon.get("TPC_STATUS"))+","+((String)trnCon.get("TPC_PREPARED_BY"))+",(select entry_date from position_master where quote_no='"
								+ QuoteNo + "'),"+((String)trnCon.get("TPC_MODIFIED_BY"))+",to_date('"+(((String)trnCon.get("TPC_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnCon.get("TPC_MODIFIED_DT"))+"','dd-mm-yyyy'),"+((String)trnCon.get("TPC_AMOUNT"))+","+((String)trnCon.get("TPC_PRINT_FLAG"))+")",QuoteNo,"t_trn_policy_condition1",queryCollection);
					}
					tokens = null;

					tokens = new java.util.StringTokenizer(commodities[i][9],",");
					while (tokens.hasMoreTokens()) 
					{
						
						queryCollection = insertionMissippiEngine("insert into t_trn_policy_exclusion(TPE_POLICY_ID,TPE_ENDT_ID,TPE_CL_CODE,TPE_PT_CODE,TPE_CODE,TPE_COV_CODE,TPE_CT_CODE,TPE_CST_CODE,TPE_VALIDITY_START_DATE,TPE_VALIDITY_END_DATE,TPE_RSK_ID,TPE_STATUS,TPE_PREPARED_BY,TPE_PREPARED_DT,TPE_MODIFIED_BY,TPE_MODIFIED_DT,TPE_AMOUNT,TPE_PRINT_FLAG)values((select quote_no from position_master where quote_no='"
								+ QuoteNo
								+ "'),"+((String)trnExc.get("TPE_ENDT_ID"))+","+((String)trnExc.get("TPE_CL_CODE"))+",(select nvl(rsacode,0) from product_master where BRANCH_CODE='"+loginBra+"' and product_id=(select product_id from position_master where quote_no='"
								+ QuoteNo
								+ "')),'"
								+ tokens.nextElement()
								+ "',"+((String)trnExc.get("TPE_COV_CODE"))+",(select nvl(rsacode,0) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
								+ QuoteNo
								+ "')))),"+((String)trnExc.get("TPE_CST_CODE"))+",(select inception_date from position_master where quote_no='"
								+ QuoteNo
								+ "'),'"+((String)trnExc.get("TPE_VALIDITY_END_DATE"))+"','"
								+ commodities[i][13]
								+ "',"+((String)trnExc.get("TPE_STATUS"))+","+((String)trnExc.get("TPE_PREPARED_BY"))+",to_date('"+(((String)trnExc.get("TPE_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnExc.get("TPE_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnExc.get("TPE_MODIFIED_BY"))+",to_date('"+(((String)trnExc.get("TPE_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnExc.get("TPE_MODIFIED_DT"))+"','dd-mm-yyyy'),"+((String)trnExc.get("TPE_AMOUNT"))+","+((String)trnExc.get("TPE_PRINT_FLAG"))+")",QuoteNo,"t_trn_policy_exclusion",queryCollection);

					}

				}

				//New Modification on 14th Oct 08.. i.e for each quote single row only in marine_header even it has more than one commodity.
				if(commoditiesCollection.length()>0)
					commoditiesCollection = commoditiesCollection.substring(0,(commoditiesCollection.length()-1));
				queryCollection = insertionMissippiEngine("insert into t_trn_marine_header1(MH_POLICY_ID,MH_ENDT_ID,MH_DECLARATION_ID,MH_OPEN_ID,MH_DECLARATION_NO,MH_DECLARATION_DATE,MH_CERTIFICATE_NO,MH_CERTIFICATE_DATE,MH_STAMP_DUTY,MH_SETTLEMENT_CURRENCY,MH_EXCHANGE_RATE,MH_SELECTED_TRANSIT,MH_SETTLING_AGENT,MH_A_SUBJECT_MATTER_DESC,MH_E_SUBJECT_MATTER_DESC,MH_STATUS,MH_VALIDITY_START_DATE,MH_VALIDITY_EXPIRY_DATE,MH_A_TRANSHIPMENT,MH_E_TRANSHIPMENT,MH_E_SETTLEMENT_LOC,MH_LC_ID,MH_PREPARED_BY,MH_PREPARED_DT,MH_MODIFIED_BY,MH_MODIFIED_DT,MH_START_DATE,MH_END_DATE)values ((select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnMHeader.get("MH_ENDT_ID"))+",'"
							+ Integer.parseInt(firstId)
							+ "','"
							+ ("3".equalsIgnoreCase(productId) ? "1"
									: (missippi_openPolicyId))
							+ "',"+((String)trnMHeader.get("MH_DECLARATION_NO"))+",to_date('"+(((String)trnMHeader.get("MH_DECLARATION_DATE")).equalsIgnoreCase("null")?"":(String)trnMHeader.get("MH_DECLARATION_DATE"))+"','dd-mm-yyyy'),"+((String)trnMHeader.get("MH_CERTIFICATE_NO"))+",to_date('"+(((String)trnMHeader.get("MH_CERTIFICATE_DATE")).equalsIgnoreCase("null")?"":(String)trnMHeader.get("MH_CERTIFICATE_DATE"))+"','dd-mm-yyyy'),"+((String)trnMHeader.get("MH_STAMP_DUTY"))+",(select nvl(b.rsacode,0) from marine_data a ,currency_master b where a.currency_id=b.currency_id and b.COUNTRY_ID='"+bcid+"' and a.application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "') and b.amend_id=(select max(amend_id)from currency_master where COUNTRY_ID='"+bcid+"' and currency_id=(select currency_id from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')))),(select exchange_rate from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')),(select nvl(rsacode,0) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')))),"+royalSettlingId+",('"+(((String)trnMHeader.get("MH_A_SUBJECT_MATTER_DESC")).equalsIgnoreCase("null")?"":(String)trnMHeader.get("MH_A_SUBJECT_MATTER_DESC"))+"'),'"
							+ (commoditiesCollection.replaceAll("'", "''"))
							+ "',"+((String)trnMHeader.get("MH_STATUS"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
							+ QuoteNo
							+ "'),'"+((String)trnMHeader.get("MH_VALIDITY_EXPIRY_DATE"))+"','"
							+ settilingJames
							+ "',"+mh_e_transhipment+","+((String)trnMHeader.get("MH_E_SETTLEMENT_LOC"))+",'"
							+ Integer.parseInt(secondId)
							+ "',"+((String)trnMHeader.get("MH_PREPARED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnMHeader.get("MH_MODIFIED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo + "'),to_date('"+(((String)trnMHeader.get("MH_START_DATE")).equalsIgnoreCase("null")?"":(String)trnMHeader.get("MH_START_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnMHeader.get("MH_END_DATE")).equalsIgnoreCase("null")?"":(String)trnMHeader.get("MH_END_DATE"))+"','dd-mm-yyyy'))",QuoteNo,"t_trn_marine_header1",queryCollection);


				
				queryCollection = insertionMissippiEngine("insert into t_trn_marine_transit1(MT_POLICY_ID,MT_ENDT_ID,MT_DECLARATION_ID,MT_SERIAL_NO,MT_MODE_OF_TRANSIT,MT_A_START_PLACE,MT_E_START_PLACE,MT_START_CITY,MT_START_COUNTRY,MT_A_DESTINATION_PLACE,MT_E_DESTINATION_PLACE,MT_DESTINATION_CITY,MT_DESTINATION_COUNTRY,MT_A_BL_AWB_LR_NO,MT_E_BL_AWB_LR_NO,MT_BL_AWB_LR_DATE,MT_DEPARTURE_DATE,MT_STORAGE_PERIOD,MT_MTL_CODE,MT_STATUS,MT_VALIDITY_START_DATE,MT_VALIDITY_EXPIRY_DATE,MT_PREPARED_BY,MT_PREPARED_DT,MT_MODIFIED_BY,MT_MODIFIED_DT,MT_START_DATE,MT_END_DATE)values((select quote_no from position_master where quote_no='"
						+ QuoteNo
						+ "'),"+((String)trnTrans.get("MT_ENDT_ID"))+",'"
						+ Integer.parseInt(firstId)
						+ "',"+((String)trnTrans.get("MT_SERIAL_NO"))+",(select to_number(nvl(rsacode,0)) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select mode_of_transport from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),('"+(((String)trnTrans.get("MT_A_START_PLACE")).equalsIgnoreCase("null")?"":(String)trnTrans.get("MT_A_START_PLACE"))+"'),(select substr((select (nvl(a.transit_from,'') || ', ' || b.country_name) from marine_data a, country_master b where a.application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "') and a.TRANSIT_FROM_COUNTRY_ID=b.country_id and b.amend_id=(select max(amend_id) from country_master where country_id=(select TRANSIT_FROM_COUNTRY_ID from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),1,59) from dual),"+((String)trnTrans.get("MT_START_CITY"))+",(select nvl(rsacode,0) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_id=(select transit_from_country_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')) and amend_id=(select max(amend_id) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_id=(select transit_from_country_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),('"+(((String)trnTrans.get("MT_A_DESTINATION_PLACE")).equalsIgnoreCase("null")?"":(String)trnTrans.get("MT_A_DESTINATION_PLACE"))+"'),(select substr((select (nvl(a.final_destination,'') || ', ' || b.country_name) from marine_data a, country_master b where a.application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "') and a.final_destination_country_id=b.country_id and b.amend_id=(select max(amend_id) from country_master where country_id=(select final_destination_country_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),1,59) from dual),"+((String)trnTrans.get("MT_DESTINATION_CITY"))+",(select nvl(rsacode,0) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_id=(select final_destination_country_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')) and amend_id=(select max(amend_id) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_id=(select final_destination_country_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),('"+(((String)trnTrans.get("MT_A_BL_AWB_LR_NO")).equalsIgnoreCase("null")?"":(String)trnTrans.get("MT_A_BL_AWB_LR_NO"))+"'),(select nvl(bl_awb_no,'') from marine_policy_details where quote_no='"
						+ QuoteNo
						+ "'),(select nvl(bl_awb_date,'') from marine_policy_details where quote_no='"
						+ QuoteNo
						+ "'),(select inception_date from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ quoteNo
						+ "')),"+((String)trnTrans.get("MT_STORAGE_PERIOD"))+","+((String)trnTrans.get("MT_MTL_CODE"))+","+((String)trnTrans.get("MT_STATUS"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
						+ QuoteNo
						+ "'),'"+((String)trnTrans.get("MT_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnTrans.get("MT_PREPARED_BY"))+",(select inception_date from position_master where quote_no='"
						+ QuoteNo
						+ "'),"+((String)trnTrans.get("MT_MODIFIED_BY"))+",(select inception_date from position_master where quote_no='"
						+ QuoteNo + "'),to_date('"+(((String)trnTrans.get("MT_START_DATE")).equalsIgnoreCase("null")?"":(String)trnTrans.get("MT_START_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnTrans.get("MT_END_DATE")).equalsIgnoreCase("null")?"":(String)trnTrans.get("MT_END_DATE"))+"','dd-mm-yyyy'))",QuoteNo,"t_trn_marine_transit1",queryCollection);

				
				queryCollection = insertionMissippiEngine("insert into t_trn_marine_carrier1(MCR_POLICY_ID,MCR_ENDT_ID,MCR_DECLARATION_ID,MCR_SERIAL_NO,MCR_CARRIER_CODE,MCR_CARRIER_TYPE,MCR_A_CARRIER_DESC,MCR_E_CARRIER_DESC,MCR_TONNAGE,MCR_AGE,MCR_VALIDITY_START_DATE,MCR_VALIDITY_EXPIRY_DATE,MCR_STATUS,MCR_A_CARRIER_REG_X,MCR_E_CARRIER_REG_X,MCR_CARRIER_REG_N,MCR_PREPARED_BY,MCR_PREPARED_DT,MCR_MODIFIED_BY,MCR_MODIFIED_DT,MCR_START_DATE,MCR_END_DATE)values((select quote_no from position_master where quote_no='"
						+ QuoteNo
						+ "'),"+((String)trnCar.get("MCR_ENDT_ID"))+",'"
						+ Integer.parseInt(firstId)
						+ "',"+((String)trnCar.get("MCR_SERIAL_NO"))+","+((String)trnCar.get("MCR_CARRIER_CODE"))+",(select nvl(rsacode,0) from mode_of_transport where BRANCH_CODE='"+loginBra+"' and mode_transport_id= to_number((select nvl(mode_of_transport,0) from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')))),('"+(((String)trnCar.get("MCR_A_CARRIER_DESC")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_A_CARRIER_DESC"))+"'),'"
						+ s1.replaceAll("'", "''").replaceAll("\"", "&#34;")
						+ "',"+((String)trnCar.get("MCR_TONNAGE"))+","+((String)trnCar.get("MCR_AGE"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
						+ QuoteNo
						+ "'),'"+((String)trnCar.get("MCR_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnCar.get("MCR_STATUS"))+",('"+(((String)trnCar.get("MCR_A_CARRIER_REG_X")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_A_CARRIER_REG_X"))+"'),('"+(((String)trnCar.get("MCR_E_CARRIER_REG_X")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_E_CARRIER_REG_X"))+"'),"+((String)trnCar.get("MCR_CARRIER_REG_N"))+","+((String)trnCar.get("MCR_PREPARED_BY"))+",(select entry_date from position_master where quote_no='"
						+ QuoteNo + "'),"+((String)trnCar.get("MCR_MODIFIED_BY"))+",to_date('"+(((String)trnCar.get("MCR_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_MODIFIED_DT"))+"','dd-mm-yyyy'),to_date('"+(((String)trnCar.get("MCR_START_DATE")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_START_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnCar.get("MCR_END_DATE")).equalsIgnoreCase("null")?"":(String)trnCar.get("MCR_END_DATE"))+"','dd-mm-yyyy'))",QuoteNo,"t_trn_marine_carrier1",queryCollection);
				
				queryCollection = insertionMissippiEngine("insert into t_mas_cash_customer1(CSH_POLICY_ID,CSH_CUSTOMER_ID,CSH_NAME_TITLE,CSH_A_SURNAME_TRIBE,CSH_E_SURNAME_TRIBE,CSH_A_NAME_1,CSH_E_NAME_1,CSH_A_NAME_2,CSH_E_NAME_2,CSH_A_NAME_3,CSH_E_NAME_3,CSH_A_NAME_4,CSH_E_NAME_4,CSH_A_NAME_5,CSH_E_NAME_5,CSH_A_ADDRESS_1,CSH_E_ADDRESS_1,CSH_A_ADDRESS_2,CSH_E_ADDRESS_2,CSH_A_ADDRESS_3,CSH_E_ADDRESS_3,CSH_A_ADDRESS_4,CSH_E_ADDRESS_4,CSH_A_ADDRESS_5,CSH_E_ADDRESS_5,CSH_A_ZIP_CODE,CSH_E_ZIP_CODE,CSH_A_EMAIL_ID,CSH_E_EMAIL_ID,CSH_FAX_NO,CSH_A_TELEX_NO,CSH_E_TELEX_NO,CSH_A_PHONE_NO,CSH_E_PHONE_NO,CSH_A_GSM_NO,CSH_E_GSM_NO,CSH_NATIONALITY,CSH_OC_CODE,CSH_CCG_CODE,CSH_A_CO_REGN_NO,CSH_E_CO_REGN_NO,CSH_A_PASSPORT_NO,CSH_E_PASSPORT_NO,CSH_A_ID_CARD_NO,CSH_E_ID_CARD_NO,CSH_A_VISA_NO,CSH_E_VISA_NO,CSH_USER_ID,CSH_VALIDITY_START_DATE,CSH_VALIDITY_EXPIRY_DATE,CSH_CTY_CODE,CSH_REG_CODE,CSH_LOC_CODE,CSH_TOT_ACC_CODE,CSH_INSURED_ID,CSH_ENDT_ID,CSH_CUT_CODE,CSH_PREPARED_BY,CSH_PREPARED_DT,CSH_MODIFIED_BY,CSH_MODIFIED_DT,CSH_BUSINESS)values((select quote_no from position_master where quote_no='"
						+ QuoteNo
						+ "'),"
						+ misipi_customer_code
						+ ",(select nvl(c.rsacode,0) from personal_info a, position_master b, title_master c where a.customer_id=b.customer_id and a.title=c.title_name and c.BRANCH_CODE='"+loginBra+"' and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_SURNAME_TRIBE")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_SURNAME_TRIBE"))+"'),('"+(((String)tmas.get("CSH_E_SURNAME_TRIBE")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_SURNAME_TRIBE"))+"'),('"+(((String)tmas.get("CSH_A_NAME_1")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_NAME_1"))+"'),(select substr(nvl(a.first_name,company_name),1,120) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_NAME_2")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_NAME_2"))+"'),(select substr(nvl(a.last_name,''),1,34) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_NAME_3")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_NAME_3"))+"'),('"+(((String)tmas.get("CSH_E_NAME_3")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_NAME_3"))+"'),('"+(((String)tmas.get("CSH_A_NAME_4")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_NAME_4"))+"'),('"+(((String)tmas.get("CSH_E_NAME_4")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_NAME_4"))+"'),('"+(((String)tmas.get("CSH_A_NAME_5")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_NAME_5"))+"'),('"+(((String)tmas.get("CSH_E_NAME_5")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_NAME_5"))+"'),('"+(((String)tmas.get("CSH_A_ADDRESS_1")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ADDRESS_1"))+"'),'"+fullCusAdd+"',('"+(((String)tmas.get("CSH_A_ADDRESS_2")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ADDRESS_2"))+"'),(select substr(nvl(a.address2,''),1,34) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_ADDRESS_3")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ADDRESS_3"))+"'),('"+(((String)tmas.get("CSH_E_ADDRESS_3")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_ADDRESS_3"))+"'),('"+(((String)tmas.get("CSH_A_ADDRESS_4")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ADDRESS_4"))+"'),('"+(((String)tmas.get("CSH_E_ADDRESS_4")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_ADDRESS_4"))+"'),('"+(((String)tmas.get("CSH_A_ADDRESS_5")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ADDRESS_5"))+"'),('"+(((String)tmas.get("CSH_E_ADDRESS_5")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_ADDRESS_5"))+"'),('"+(((String)tmas.get("CSH_A_ZIP_CODE")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ZIP_CODE"))+"'),('"+(((String)tmas.get("CSH_E_ZIP_CODE")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_ZIP_CODE"))+"'),('"+(((String)tmas.get("CSH_A_EMAIL_ID")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_EMAIL_ID"))+"'),(select substr(nvl(a.email,''),1,39) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),(select nvl(a.fax,0) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_TELEX_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_TELEX_NO"))+"'),('"+(((String)tmas.get("CSH_E_TELEX_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_TELEX_NO"))+"'),('"+(((String)tmas.get("CSH_A_PHONE_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_PHONE_NO"))+"'),(select nvl(a.telephone,'') from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmas.get("CSH_A_GSM_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_GSM_NO"))+"'),(select nvl(a.mobile,'') from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),	(select nvl(rsacode,0) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_name=(select country from personal_info where customer_id=(select customer_id from position_master where quote_no='"
						+ QuoteNo
						+ "')) and amend_id=(select max(amend_id) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_name=(select country from personal_info where customer_id=(select customer_id from position_master where quote_no='"
						+ QuoteNo
						+ "')))),"+((String)tmas.get("CSH_OC_CODE"))+","+((String)tmas.get("CSH_CCG_CODE"))+",('"+(((String)tmas.get("CSH_A_CO_REGN_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_CO_REGN_NO"))+"'),('"+(((String)tmas.get("CSH_E_CO_REGN_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_CO_REGN_NO"))+"'),('"+(((String)tmas.get("CSH_A_PASSPORT_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_PASSPORT_NO"))+"'),('"+(((String)tmas.get("CSH_E_PASSPORT_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_PASSPORT_NO"))+"'),('"+(((String)tmas.get("CSH_A_ID_CARD_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_ID_CARD_NO"))+"'),('"+(((String)tmas.get("CSH_E_ID_CARD_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_ID_CARD_NO"))+"'),('"+(((String)tmas.get("CSH_A_VISA_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_A_VISA_NO"))+"'),('"+(((String)tmas.get("CSH_E_VISA_NO")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_E_VISA_NO"))+"'),"+((String)tmas.get("CSH_USER_ID"))+",(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
						+ QuoteNo
						+ "'),'"+((String)tmas.get("CSH_VALIDITY_EXPIRY_DATE"))+"',"+((String)tmas.get("CSH_CTY_CODE"))+","+((String)tmas.get("CSH_REG_CODE"))+",'"+loginBra+"',"+((String)tmas.get("CSH_TOT_ACC_CODE"))+",'"
						+ Integer.parseInt(firstId)
						+ "',"+((String)tmas.get("CSH_ENDT_ID"))+","+((String)tmas.get("CSH_CUT_CODE"))+","+((String)tmas.get("CSH_PREPARED_BY"))+",to_date('"+(((String)tmas.get("CSH_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)tmas.get("CSH_MODIFIED_BY"))+",to_date('"+(((String)tmas.get("CSH_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_MODIFIED_DT"))+"','dd-mm-yyyy'),('"+(((String)tmas.get("CSH_BUSINESS")).equalsIgnoreCase("null")?"":(String)tmas.get("CSH_BUSINESS"))+"'))",QuoteNo,"t_mas_cash_customer1",queryCollection);

					String cmdjamestxt1 = "";
					if (productId.equals("11"))
						cmdjamestxt1 = ", All other condition exceptions as per Marine Open Policy No."+ missippi_openCover1;

					if (Pos_BROKER_REMARKS != null&& !Pos_BROKER_REMARKS.equals("null")	&& Pos_BROKER_REMARKS.length() > 0)
						Pos_BROKER_REMARKS = Pos_BROKER_REMARKS.replaceAll("'","''");
					else
						Pos_BROKER_REMARKS = "";
					
					finalClauses = finalClauses.replaceAll("'", "''");
					String cmdjamestxt = finalClauses + " , "+ Pos_BROKER_REMARKS + cmdjamestxt1;

					
					if (cmdjamestxt.length() > 1999)
						cmdjamestxt = cmdjamestxt.substring(1, 1999);

					//validity_end_date need to come from constant table... correction
					queryCollection = insertionMissippiEngine("insert into T_TRN_NON_STD_TEXT1 (NST_POLICY_ID ,NST_ENDT_ID ,NST_TYPE_CODE ,NST_A_TEXT ,NST_E_TEXT ,NST_VALIDITY_START_DATE ,NST_VALIDITY_END_DATE ,NST_PHR_CODE ,NST_PREPARED_BY ,NST_PREPARED_DT ,NST_MODIFIED_BY ,NST_MODIFIED_DT )values((select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnText.get("NST_ENDT_ID"))+","+((String)trnText.get("NST_TYPE_CODE"))+",('"+(((String)trnText.get("NST_A_TEXT")).equalsIgnoreCase("null")?"":(String)trnText.get("NST_A_TEXT"))+"'),'"
							+ cmdjamestxt
							+ "',(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select add_months(inception_date,12)-1 from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnText.get("NST_PHR_CODE"))+","+((String)trnText.get("NST_PREPARED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnText.get("NST_MODIFIED_BY"))+",(select inception_date from position_master where quote_no='"
							+ QuoteNo + "'))",QuoteNo,"T_TRN_NON_STD_TEXT1",queryCollection);

					queryCollection = insertionMissippiEngine("insert into t_trn_marine_lc1(LC_POLICY_ID, LC_ENDT_ID, LC_DECLARATION_ID, LC_ID, LC_SL_NO, LC_NUMBER, LC_REFERENCE, LC_DATE, LC_AMOUNT, LC_EFFECTIVE_DATE, LC_EXPIRY_DATE, LC_BANK_CODE, LC_A_BANK_NAME, LC_E_BANK_NAME, LC_VALIDITY_START_DATE, LC_VALIDITY_EXPIRY_DATE, LC_STATUS, LC_FC_AMOUNT, LC_EXCHANGE_RATE, LC_CURRENCY_CODE, LC_PREPARED_BY, LC_PREPARED_DT, LC_MODIFIED_BY, LC_MODIFIED_DT,LC_START_DATE, LC_END_DATE, LC_CONSIGNEE_NAME ) values ((select quote_no from position_master where quote_no='"
						+ QuoteNo
						+ "'),"+((String)trnLc.get("LC_ENDT_ID"))+",'"
						+ Integer.parseInt(firstId)
						+ "','"
						+ Integer.parseInt(secondId)
						+ "',"+((String)trnLc.get("LC_SL_NO"))+",(select lc_number from marine_policy_details where quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)trnLc.get("LC_REFERENCE")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_REFERENCE"))+"'),(select lc_date from marine_policy_details where quote_no='"
						+ QuoteNo
						+ "'),"+((String)trnLc.get("LC_AMOUNT"))+",to_date('"+(((String)trnLc.get("LC_EFFECTIVE_DATE")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_EFFECTIVE_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnLc.get("LC_EXPIRY_DATE")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_EXPIRY_DATE"))+"','dd-mm-yyyy'),"+((String)trnLc.get("LC_BANK_CODE"))+",('"+(((String)trnLc.get("LC_A_BANK_NAME")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_A_BANK_NAME"))+"'),(select substr(bank_name,1,59) from marine_policy_details where quote_no='"
						+ QuoteNo
						+ "'),(select to_char(inception_date,'dd-mon-yyyy') from position_master where quote_no='"
						+ QuoteNo
						+ "'),'"+((String)trnLc.get("LC_VALIDITY_EXPIRY_DATE"))+"',"+((String)trnLc.get("LC_STATUS"))+",(select round(exchange_rate*total_sum_insured,2) from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')), (select exchange_rate from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "')),(select nvl(b.rsacode,0) from marine_data a ,currency_master b where a.currency_id=b.currency_id and b.COUNTRY_ID='"+bcid+"' and a.application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo
						+ "') and b.amend_id=(select max(amend_id)from currency_master where COUNTRY_ID='"+bcid+"' and currency_id=(select currency_id from marine_data where application_no=(select application_no from position_master where quote_no='"
						+ QuoteNo + "')))),"+((String)trnLc.get("LC_PREPARED_BY"))+",to_date('"+(((String)trnLc.get("LC_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnLc.get("LC_MODIFIED_BY"))+",to_date('"+(((String)trnLc.get("LC_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_MODIFIED_DT"))+"','dd-mm-yyyy'),to_date('"+(((String)trnLc.get("LC_START_DATE")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_START_DATE"))+"','dd-mm-yyyy'),to_date('"+(((String)trnLc.get("LC_END_DATE")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_END_DATE"))+"','dd-mm-yyyy'),('"+(((String)trnLc.get("LC_CONSIGNEE_NAME")).equalsIgnoreCase("null")?"":(String)trnLc.get("LC_CONSIGNEE_NAME"))+"'))",QuoteNo,"t_trn_marine_lc1",queryCollection);


				status = true;

				queryCollection = insertionMissippiEngine("insert into t_mas_customer1 (CU_CUSTOMER_ID,CU_NAME_TITLE,CU_A_SURNAME_TRIBE,CU_E_SURNAME_TRIBE,CU_A_NAME_1,CU_E_NAME_1,CU_A_NAME_2,CU_E_NAME_2,CU_A_NAME_3,CU_E_NAME_3,CU_A_NAME_4,CU_E_NAME_4,CU_A_NAME_5,CU_E_NAME_5,CU_A_ADDRESS_1,CU_E_ADDRESS_1,CU_A_ADDRESS_2,CU_E_ADDRESS_2,CU_A_ADDRESS_3,CU_E_ADDRESS_3,CU_A_ADDRESS_4,CU_E_ADDRESS_4,CU_A_ADDRESS_5,CU_E_ADDRESS_5,CU_E_ZIP_CODE,CU_A_ZIP_CODE,CU_E_EMAIL_ID,CU_A_EMAIL_ID,CU_FAX_NO,CU_A_TELEX_NO,CU_E_TELEX_NO,CU_A_PHONE_NO,CU_E_PHONE_NO,CU_A_GSM_NO,CU_E_GSM_NO,CU_STATUS,CU_RATING,CU_A_INSTERESTS,CU_E_INSTERESTS,CU_NATIONALITY,CU_OC_CODE,CU_CCG_CODE,CU_A_CO_REGN_NO,CU_E_CO_REGN_NO,CU_A_PASSPORT_NO,CU_E_PASSPORT_NO,CU_A_ID_CARD_NO,CU_E_ID_CARD_NO,CU_A_VISA_NO,CU_E_VISA_NO,CU_USER_ID,CU_POLICY_ID,CU_CTY_CODE,CU_REG_CODE,CU_LOC_CODE,CU_TOT_ACC_CODE,CU_ACCEXEC_CODE,CU_CREDIT_AMT,CU_CREDIT_DAYS,CU_BUSINESS,CU_PREPARED_BY,CU_PREPARED_DT,CU_MODIFIED_BY,CU_MODIFIED_DT)values((select to_number(substr(to_char(nvl(customer_id,0)),1,12)) from position_master where quote_no='"
						+ QuoteNo
						+ "'),(select nvl(c.rsacode,0) from personal_info a, position_master b, title_master c where a.customer_id=b.customer_id and c.BRANCH_CODE='"+loginBra+"' and a.title=c.title_name and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_SURNAME_TRIBE")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_SURNAME_TRIBE"))+"'),('"+(((String)tmascus.get("CU_E_SURNAME_TRIBE")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_SURNAME_TRIBE"))+"'),('"+(((String)tmascus.get("CU_A_NAME_1")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_NAME_1"))+"'),(select substr(nvl(a.first_name,company_name),1,120) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_NAME_2")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_NAME_2"))+"'),(select substr(nvl(a.last_name,''),1,34) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_NAME_3")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_NAME_3"))+"'),('"+(((String)tmascus.get("CU_E_NAME_3")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_NAME_3"))+"'),('"+(((String)tmascus.get("CU_A_NAME_4")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_NAME_4"))+"'),('"+(((String)tmascus.get("CU_E_NAME_4")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_NAME_4"))+"'),('"+(((String)tmascus.get("CU_A_NAME_5")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_NAME_5"))+"'),('"+(((String)tmascus.get("CU_E_NAME_5")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_NAME_5"))+"'),('"+(((String)tmascus.get("CU_A_ADDRESS_1")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ADDRESS_1"))+"'),(select substr(nvl(a.address1,''),1,199) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_ADDRESS_2")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ADDRESS_2"))+"'),(select substr(nvl(a.address2,''),1,34) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_ADDRESS_3")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ADDRESS_3"))+"'),('"+(((String)tmascus.get("CU_E_ADDRESS_3")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_ADDRESS_3"))+"'),('"+(((String)tmascus.get("CU_A_ADDRESS_4")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ADDRESS_4"))+"'),('"+(((String)tmascus.get("CU_E_ADDRESS_4")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_ADDRESS_4"))+"'),('"+(((String)tmascus.get("CU_A_ADDRESS_5")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ADDRESS_5"))+"'),('"+(((String)tmascus.get("CU_E_ADDRESS_5")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_ADDRESS_5"))+"'),(select nvl(a.pobox,0) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_ZIP_CODE")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ZIP_CODE"))+"'),(select substr(nvl(a.email,''),1,39) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_EMAIL_ID")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_EMAIL_ID"))+"'),(select nvl(a.fax,0) from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_TELEX_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_TELEX_NO"))+"'),('"+(((String)tmascus.get("CU_E_TELEX_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_TELEX_NO"))+"'),('"+(((String)tmascus.get("CU_A_PHONE_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_PHONE_NO"))+"'),(select nvl(a.telephone,'') from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)tmascus.get("CU_A_GSM_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_GSM_NO"))+"'),(select nvl(a.mobile,'0') from personal_info a, position_master b where a.customer_id=b.customer_id and b.quote_no='"
						+ QuoteNo
						+ "'),"+((String)tmascus.get("CU_STATUS"))+","+((String)tmascus.get("CU_RATING"))+",('"+(((String)tmascus.get("CU_A_INSTERESTS")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_INSTERESTS"))+"'),('"+(((String)tmascus.get("CU_E_INSTERESTS")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_INSTERESTS"))+"'),(select nvl(rsacode,0) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_name=(select country from personal_info where customer_id=(select customer_id from position_master where quote_no='"
						+ QuoteNo
						+ "')) and amend_id=(select max(amend_id) from COUNTRY_MASTER_DETAIL where BELONGING_COUNTRY_ID='"+bcid+"' and country_name=(select country from personal_info where customer_id=(select customer_id from position_master where quote_no='"
						+ QuoteNo
						+ "')))),"+((String)tmascus.get("CU_OC_CODE"))+","+((String)tmascus.get("CU_CCG_CODE"))+",('"+(((String)tmascus.get("CU_A_CO_REGN_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_CO_REGN_NO"))+"'),('"+(((String)tmascus.get("CU_E_CO_REGN_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_CO_REGN_NO"))+"'),('"+(((String)tmascus.get("CU_A_PASSPORT_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_PASSPORT_NO"))+"'),('"+(((String)tmascus.get("CU_E_PASSPORT_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_PASSPORT_NO"))+"'),('"+(((String)tmascus.get("CU_A_ID_CARD_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_ID_CARD_NO"))+"'),('"+(((String)tmascus.get("CU_E_ID_CARD_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_ID_CARD_NO"))+"'),('"+(((String)tmascus.get("CU_A_VISA_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_A_VISA_NO"))+"'),('"+(((String)tmascus.get("CU_E_VISA_NO")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_E_VISA_NO"))+"'),(select to_number(substr(to_char(nvl(customer_id,0)),3,7)) from position_master where quote_no='"
						+ QuoteNo
						+ "'),(select to_number(substr(to_char(nvl(quote_no,0)),1,12)) from position_master where quote_no='"
						+ QuoteNo
						+ "'),"+((String)tmascus.get("CU_CTY_CODE"))+","+((String)tmascus.get("CU_REG_CODE"))+","+((String)tmascus.get("CU_LOC_CODE"))+","+((String)tmascus.get("CU_TOT_ACC_CODE"))+","+((String)tmascus.get("CU_ACCEXEC_CODE"))+","+((String)tmascus.get("CU_CREDIT_AMT"))+","+((String)tmascus.get("CU_CREDIT_DAYS"))+",('"+(((String)tmascus.get("CU_BUSINESS")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_BUSINESS"))+"'),"+((String)tmascus.get("CU_PREPARED_BY"))+",to_date('"+(((String)tmascus.get("CU_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)tmascus.get("CU_MODIFIED_BY"))+",to_date('"+(((String)tmascus.get("CU_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)tmascus.get("CU_MODIFIED_DT"))+"','dd-mm-yyyy'))",QuoteNo,(multipleCheck?"t_mas_customer1":"Final"),queryCollection);

				if(multipleCheck)
				{
					queryCollection = insertionMissippiEngine("insert into T_TRN_HDR_DEBIT_NOTE1 (DNH_DEBIT_NOTE_NO,  DNH_DEBIT_NOTE_DATE,DNH_A_DESC,DNH_E_DESC,DNH_PREPARED_BY,DNH_APPROVED_BY,DNH_PRINT_DATE,DNH_CUSTOMER_ID,DNH_CTY_CODE,DNH_REG_CODE,DNH_LOC_CODE,DNH_CUT_CODE,DNH_PREPRINT_NO,DNH_PREPARED_DT,DNH_MODIFIED_BY,DNH_MODIFIED_DT,DNH_NAME,DNH_CC_CODE,DNH_TOT_ACC_CODE,DNH_GL_CODE,DNH_POSTED_DATE,DNH_REFRESH_DATE)values((select nvl(debit_note_no,'0') from position_master where quote_no='"
						+ QuoteNo
						+ "'),(select to_date(substr(to_char(inception_date,'dd/mm/yyyy HH24:MI:SS'),1,10),'dd/mm/yyyy') from position_master where quote_no='"
						+ QuoteNo
						+ "'),('"+(((String)trnHdr.get("DNH_A_DESC")).equalsIgnoreCase("null")?"":(String)trnHdr.get("DNH_A_DESC"))+"'),('"+(((String)trnHdr.get("DNH_E_DESC")).equalsIgnoreCase("null")?"":(String)trnHdr.get("DNH_E_DESC"))+"'),"+((String)trnHdr.get("DNH_PREPARED_BY"))+","+((String)trnHdr.get("DNH_APPROVED_BY"))+",to_date('"+(((String)trnHdr.get("DNH_PRINT_DATE")).equalsIgnoreCase("null")?"":(String)trnHdr.get("DNH_PRINT_DATE"))+"','dd-mm-yyyy'),(select nvl(missippi_id,'0') from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no='"
						+ QuoteNo
						+ "'))),"+((String)trnHdr.get("DNH_CTY_CODE"))+","+((String)trnHdr.get("DNH_REG_CODE"))+",'"+loginBra+"',"+((String)trnHdr.get("DNH_CUT_CODE"))+",(select quote_no from position_master where quote_no='"
						+ QuoteNo
						+ "'), to_date('"+(((String)trnHdr.get("DNH_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnHdr.get("DNH_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnHdr.get("DNH_MODIFIED_BY"))+",to_date('"+(((String)trnHdr.get("DNH_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnHdr.get("DNH_MODIFIED_DT"))+"','dd-mm-yyyy'),(select nvl(company_name,contact_person) from broker_company_master where agency_code=(select oa_code from login_master where login_id =(select login_id from position_master where quote_no='"
						+ QuoteNo
						+ "'))),"+((String)trnHdr.get("DNH_CC_CODE"))+","+((String)trnHdr.get("DNH_TOT_ACC_CODE"))+","
						+ acode
						+ ",'"+((String)trnHdr.get("DNH_POSTED_DATE"))+"','"+((String)trnHdr.get("DNH_REFRESH_DATE"))+"')",QuoteNo,"T_TRN_HDR_DEBIT_NOTE1",queryCollection);

					// Row 1 for T_TRN_DTL_DEBIT_NOTE1
					String CommisStatement = "Less Commission @ "+commissionpercentage+" %";
					queryCollection = insertionMissippiEngine("insert into T_TRN_DTL_DEBIT_NOTE1 (DND_DEBIT_NOTE_NO,DND_DEBIT_NOTE_DATE,DND_POLICY_ID,DND_SERIAL_NO,DND_AMOUNT,DND_POLICY_NO,DND_POLICY_YEAR,DND_DESC,DND_LOCATION_CODE, DND_CLASS_CODE, DND_DOCUMENT_CODE,DND_ENDT_ID,DND_CTY_CODE,DND_REG_CODE,DND_CC_CODE,DND_CLAIM_ID,           DND_REF_TRAN_ID,DND_REF_TRAN_TYPE,DND_REF_TRAN_SERIAL_NO, DND_PREPARED_BY,DND_PREPARED_DT,   DND_MODIFIED_BY,DND_MODIFIED_DT,DND_TOT_ACC_CODE,DND_GL_CODE,DND_ANALYSIS_CODE)values((select nvl(debit_note_no,'0') from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select to_date(substr(to_char(inception_date,'dd/mm/yyyy HH24:MI:SS'),1,10),'dd/mm/yyyy') from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnDtl.get("DND_SERIAL_NO"))+",(select ROUND(-sum(nvl(excess_premium,0)+'"
							+ (Double.parseDouble(premiumSum) + Double
									.parseDouble(OverallPremium))
							+ "'),2) from marine_data  where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "')),(select to_number(substr(policy_no,9,15)) from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select to_char(inception_date,'YYYY') from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select 'BEING THE PREMIUM DUE ON MARINE CARGO INSURANCE POLICY NO' || ' : ' || policy_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),'"+loginBra+"',"+((String)trnDtl.get("DND_CLASS_CODE"))+","+((String)trnDtl.get("DND_DOCUMENT_CODE"))+","+((String)trnDtl.get("DND_ENDT_ID"))+","+((String)trnDtl.get("DND_CTY_CODE"))+","+((String)trnDtl.get("DND_REG_CODE"))+","+((String)trnDtl.get("DND_CC_CODE"))+","+((String)trnDtl.get("DND_CLAIM_ID"))+",(select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnDtl.get("DND_REF_TRAN_TYPE"))+","+((String)trnDtl.get("DND_REF_TRAN_SERIAL_NO"))+","+((String)trnDtl.get("DND_PREPARED_BY"))+",to_date('"+(((String)trnDtl.get("DND_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnDtl.get("DND_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnDtl.get("DND_MODIFIED_BY"))+",to_date('"+(((String)trnDtl.get("DND_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnDtl.get("DND_MODIFIED_DT"))+"','dd-mm-yyyy'),"+((String)trnDtl.get("DND_TOT_ACC_CODE"))+","+((String)trnDtl.get("DND_GL_CODE"))+","+((String)trnDtl.get("DND_ANALYSIS_CODE"))+")",QuoteNo,"T_TRN_DTL_DEBIT_NOTE1",queryCollection);

					// Row 2 for T_TRN_DTL_DEBIT_NOTE1
					queryCollection = insertionMissippiEngine("insert into T_TRN_DTL_DEBIT_NOTE1 (DND_DEBIT_NOTE_NO,DND_DEBIT_NOTE_DATE,DND_POLICY_ID,DND_SERIAL_NO,DND_AMOUNT,DND_POLICY_NO,DND_POLICY_YEAR,DND_DESC,DND_LOCATION_CODE, DND_CLASS_CODE, DND_DOCUMENT_CODE,DND_ENDT_ID,DND_CTY_CODE,DND_REG_CODE,DND_CC_CODE,DND_CLAIM_ID,           DND_REF_TRAN_ID,DND_REF_TRAN_TYPE,DND_REF_TRAN_SERIAL_NO, DND_PREPARED_BY,DND_PREPARED_DT,   DND_MODIFIED_BY,DND_MODIFIED_DT,DND_TOT_ACC_CODE,DND_GL_CODE,DND_ANALYSIS_CODE)values((select nvl(debit_note_no,'0') from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select to_date(substr(to_char(inception_date,'dd/mm/yyyy HH24:MI:SS'),1,10),'dd/mm/yyyy') from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnDtl1.get("DND_SERIAL_NO"))+",(select round(((select ROUND(sum(nvl(excess_premium,0)+'"
							+ (Double.parseDouble(premiumSum) + Double
									.parseDouble(OverallPremium))
							+ "'),2) from marine_data  where application_no=(select application_no from position_master where quote_no='"
							+ QuoteNo
							+ "'))*"+commissionpercentage+"*(1/100)),2)from dual),(select to_number(substr(policy_no,9,15)) from position_master where quote_no='"
							+ QuoteNo
							+ "'),(select to_char(inception_date,'YYYY') from position_master where quote_no='"
							+ QuoteNo
							+ "'),'"+CommisStatement+"','"+loginBra+"',"+((String)trnDtl1.get("DND_CLASS_CODE"))+","+((String)trnDtl1.get("DND_DOCUMENT_CODE"))+","+((String)trnDtl1.get("DND_ENDT_ID"))+","+((String)trnDtl1.get("DND_CTY_CODE"))+","+((String)trnDtl1.get("DND_REG_CODE"))+","+((String)trnDtl1.get("DND_CC_CODE"))+","+((String)trnDtl1.get("DND_CLAIM_ID"))+",(select quote_no from position_master where quote_no='"
							+ QuoteNo
							+ "'),"+((String)trnDtl1.get("DND_REF_TRAN_TYPE"))+","+((String)trnDtl1.get("DND_REF_TRAN_SERIAL_NO"))+","+((String)trnDtl1.get("DND_PREPARED_BY"))+",to_date('"+(((String)trnDtl1.get("DND_PREPARED_DT")).equalsIgnoreCase("null")?"":(String)trnDtl1.get("DND_PREPARED_DT"))+"','dd-mm-yyyy'),"+((String)trnDtl1.get("DND_MODIFIED_BY"))+",to_date('"+(((String)trnDtl1.get("DND_MODIFIED_DT")).equalsIgnoreCase("null")?"":(String)trnDtl1.get("DND_MODIFIED_DT"))+"','dd-mm-yyyy'),"+((String)trnDtl1.get("DND_TOT_ACC_CODE"))+","+((String)trnDtl1.get("DND_GL_CODE"))+","+((String)trnDtl1.get("DND_ANALYSIS_CODE"))+")",QuoteNo,"Final",queryCollection);
				}
			}
		}
		catch (Exception ee) 
		{
			System.out.println("Royal Missippi test ERROR @ 2----last   " + ee.toString());
			ee.printStackTrace();
		}
		return changeStatus;
	}
	public HashMap insertionMissippiEngine(String query,String quote,String tableName,HashMap queryCollection)
	{
		String result = "";
		int queryCounts = 0;
		queryCounts = Integer.parseInt((String)queryCollection.get("Count"));
		queryCollection.put("Query"+queryCounts++,query);
		System.out.println(" insertion MissippiEnginessssssss  FINAL RESULT  for quoteNo--->"+quote+"...on table-->"+tableName+"  -->"+queryCounts);
		queryCollection.put("Count",""+queryCounts);
		if(tableName.equalsIgnoreCase("Final"))
		{
			System.out.println(" insertion MissippiEnginessssssss  FINAL RESULT  "+ queryCounts);
			result = runner.insertionTransaction(queryCollection);
			System.out.println(" insertion MissippiEnginessssssss  FINAL RESULT  "+ result);
			if((result.length()<=0||result.equalsIgnoreCase("DIDN'T INSERTED")))
			{
				changeStatus = true;
			}
		}
		return queryCollection;
	}
	public String XmlAppend(String query, String inputs,
			String idValue) {
		String xmlResult = "";
		System.out
				.println("---------------this is a Xml Append Method---------------"
						+ paths);
		// paths=paths.replaceAll("/","/")
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(url + "/output1.xml");

			Element ele = doc.getDocumentElement();
			try {
				Element element = (Element) doc.getElementsByTagName(
						"QuoteNumber_" + idValue).item(0);
				System.out.println("Existing root name--------------"
						+ element.getNodeName());
				element.getParentNode().removeChild(element);
			} catch (Exception e) {
				System.out.println("sssss-------->" + e.toString());
			}

			System.out.println("root name--------------" + ele.getNodeName());
			Element root = doc.createElement("QuoteNumber_" + idValue);

			// for(int i=inputs.length-1;i>=0;i--)
			// {
			Element root1 = doc.createElement("SQL-Query");
			root.appendChild(root1);
			Text text1 = doc.createTextNode(query);
			root1.appendChild(text1);
			root1 = doc.createElement("error");
			root.appendChild(root1);
			text1 = doc.createTextNode(inputs);
			root1.appendChild(text1);
			// }
			ele.appendChild(root);

			TransformerFactory transfac = TransformerFactory.newInstance();
			Transformer trans = transfac.newTransformer();
			trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			trans.setOutputProperty(OutputKeys.INDENT, "yes");

			// create string from xml tree
			StringWriter sw = new StringWriter();
			StreamResult result = new StreamResult(sw);
			DOMSource source = new DOMSource(doc);
			trans.transform(source, result);
			String xmlString = sw.toString();
			File nn = new File("" + paths + "\\output.xml");
			nn.delete();

			BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(
					"" + paths + "\\output.xml"));
			BufferedWriter bufferedwriterr = new BufferedWriter(new FileWriter(
					"" + paths + "\\output1.xml"));
			System.out.println("------success----------");
			bufferedwriter.write(xmlString.toString());
			if (bufferedwriter != null)
				bufferedwriter.close();
			bufferedwriterr.write(xmlString.toString());
			if (bufferedwriterr != null)
				bufferedwriterr.close();

			xmlResult = "success";
			System.out.println("After the Appending -----------------\n"
					+ xmlString);
			// printElement(doc.getDocumentElement());
		} catch (Exception e) {
			System.out.println("File is not found.......");
			String s = XmlGeneration(query, inputs, idValue);
			xmlResult = "ssuccess";

		}
		System.out
				.println("---------------this is a Xml Append Method---------------");
		return xmlResult;
	}

	public String XmlGeneration(String Query, String inputs, String idValue) {
		String s = "";
		try {
			int iLf = 10;
			char cLf = (char) iLf;
			File outputFile = new File("" + paths + "\\output.xml");
			outputFile.createNewFile();
			FileWriter file = new FileWriter(outputFile);

			// file.write("<?xml version='1.0' encoding='ISO-8859-1'?>"+cLf);
			file.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + cLf);
			file.write("<MarineInsurance-DataTransfer>" + cLf);
			// System.out.println("---length--------"+inputs.length);
			file.write("\t<QuoteNumber_" + idValue + ">" + cLf);
			file.write("\t\t<SQL-Query>" + Query + "</SQL-Query>" + cLf);

			System.out
					.println("------------inside of the loop--------------------");
			file.write("\t\t<error>" + inputs + "</error>" + cLf);

			file.write("\t</QuoteNumber_" + idValue + ">" + cLf);
			file.write("</MarineInsurance-DataTransfer>" + cLf);
			file.close();

			outputFile = new File("" + paths + "\\output1.xml");
			outputFile.createNewFile();
			file = new FileWriter(outputFile);

			// file.write("<?xml version='1.0' encoding='ISO-8859-1'?>"+cLf);
			file.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + cLf);
			file.write("<MarineInsurance-DataTransfer>" + cLf);
			// System.out.println("---length--------"+inputs.length);
			file.write("\t<QuoteNumber_" + idValue + ">" + cLf);
			file.write("\t\t<SQL-Query>" + Query + "</SQL-Query>" + cLf);

			System.out
					.println("------------inside of the loop--------------------");
			file.write("\t\t<error>" + inputs + "</error>" + cLf);

			file.write("\t</QuoteNumber_" + idValue + ">" + cLf);
			file.write("</MarineInsurance-DataTransfer>" + cLf);
			file.close();
			s = "success";
		} 
		catch (Exception e) 
		{
			System.out.println("Error in new file Generation-----"	+ e.toString());
		}
		return s;
	}

	public String getMaxId(String pid,String brokerBra,String types) 
	{
		String current_no = null;
		try 
		{
			if(types.equalsIgnoreCase("DataTransfer"))
			{
				String args[] = new String[3];
				args[0] = brokerBra;
				args[1] = pid;
				args[2] = brokerBra;
				current_no = runner.singleSelection("select nvl(max(current_no),max(start_no)) from policyno_generate where type_id=(select DATA_TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
				current_no = current_no==null?"0":current_no;
				if(current_no.length()<=0)
					current_no = "0";
				current_no =""+(Integer.parseInt(current_no)+1);
				//
				args = new String[5];
				args[0] = current_no;
				args[1] = current_no;
				args[2] = brokerBra;
				args[3] = pid;
				args[4] = brokerBra;
				runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select DATA_TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
			}
			else if(types.equalsIgnoreCase("Transfer"))
			{
				String args[] = new String[3];
				args[0] = brokerBra;
				args[1] = pid;
				args[2] = brokerBra;
				current_no = runner.singleSelection("select nvl(max(current_no),max(start_no)) from policyno_generate where type_id=(select TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
				
				if(current_no.length()<=0)
					current_no = "0";
				current_no =""+(Integer.parseInt(current_no)+1);
				//
				args = new String[5];
				args[0] = current_no;
				args[1] = current_no;
				args[2] = brokerBra;
				args[3] = pid;
				args[4] = brokerBra;
				runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
			}
			
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in getMaxQuote in Missippi Engine  "+ e.toString());e.printStackTrace();
		}
		return current_no;

	}
	public void setMaxId(String pid,String brokerBra,String types, String id) 
	{
		try
		{
			if(types.equalsIgnoreCase("DataTransfer"))
			{
				String args[] = new String[5];
				args[0] = id;
				args[1] = id;
				args[2] = brokerBra;
				args[3] = pid;
				args[4] = brokerBra;
				runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select DATA_TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
			}
			else if(types.equalsIgnoreCase("Transfer"))
			{
				String args[] = new String[5];
				args[0] = id;
				args[1] = id;
				args[2] = brokerBra;
				args[3] = pid;
				args[4] = brokerBra;
				runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where type_id=(select TRANSFER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and product_id=?) and status='Y' and BRANCH_CODE=?",args);
			}
		} 
		catch (Exception e) 
		{
			System.out.println("ERROR in set  in Missippi Engine  "+ e.toString());e.printStackTrace();
		}

	}
	public HashMap getMissippiConsatnt(String tname, String rno,String cids)
	{
		String result[][] = new String[0][0];
		try
		{
			String args[] = new String[3];
			args[0] = tname.toLowerCase();
			args[1] = rno;
			args[2] = cids;
			result = runner.multipleSelection("select FIELD_NAME_DISPLAY,nvl(CONSTANT_VALUE,'null') from MISSIPPI_CONSTANT_VALUES where lower(TABLE_NAME_DISPLAY)=? and ROW_NOS=? and country_id=? and product_id='3' order by SERIAL_NO",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		HashMap cons = new HashMap();
		for(int r=0;r<result.length;r++)
		{
			cons.put(""+result[r][0],result[r][1]);
			
		}
		return cons;
	}
	public String getBranchValues(String brokerBra,String cids)
	{
		
		String res = "";
		try
		{
			String args[] = new String[2];
			args[0] = brokerBra;
			args[1] = cids;
			res = runner.singleSelection("select nvl(CONSTANT_VALUE,' ') from MISSIPPI_CONSTANT_VALUES where TABLE_NAME_DISPLAY='T_TRN_POLICY1_BranchEntry' and BRANCH_CODE=? and country_id=? and FIELD_NAME_DISPLAY='POL_USER_ID'",args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return res;
	}
	public void processDeclaration(String all_Qnos)
	{
		double  Commission = 0.00;
		String pol_premium="";
		String pol_commission="";
		String first_qno="";
		String sql = "select DNH_PREPRINT_NO from T_TRN_HDR_DEBIT_NOTE1 where DNH_PREPRINT_NO in ("+all_Qnos+")";
		first_qno = runner.singleSelection(sql);
		//T_TRN_POLICY1 - Start//	
		pol_premium = runner.singleSelection("select nvl(sum(POL_PREMIUM),'0') from T_TRN_POLICY1 where pol_policy_id in("+all_Qnos+")");
		runner.updation("delete from T_TRN_POLICY1 where pol_policy_id in("+all_Qnos+") and pol_policy_id not in("+first_qno+")");
		runner.updation("update T_TRN_POLICY1 set POL_PREMIUM='"+pol_premium+"' where pol_policy_id="+first_qno);
		//T_TRN_POLICY1 - End//
		//T_TRN_MARINE_HEADER1-Start//
		runner.updation("Update T_TRN_MARINE_HEADER1 set mh_policy_id="+first_qno+" where mh_policy_id in("+all_Qnos+")");
		//T_TRN_MARINE_HEADER1-ENd//
		//T_MAS_CASH_CUSTOMER1 - Start//
			runner.updation("delete from T_MAS_CASH_CUSTOMER1 where CSH_POLICY_ID in("+all_Qnos+") and CSH_POLICY_ID not in("+first_qno+")");
		//T_MAS_CASH_CUSTOMER1 - End//
		//T_TRN_PREMIUM1 - Start
		runner.updation("Update T_TRN_PREMIUM1 set PRM_POLICY_ID="+first_qno+" where PRM_POLICY_ID in("+all_Qnos+")");
		//T_TRN_PREMIUM1 - End
		//T_TRN_NON_STD_TEXT1 - Start //
		runner.updation("Delete from T_TRN_NON_STD_TEXT1 where  NST_POLICY_ID in("+all_Qnos+") and NST_POLICY_ID not in("+first_qno+")");
		//T_TRN_NON_STD_TEXT1 - End //
		//T_TRN_MARINE_TRANSIT1 - Start
		runner.updation("Update T_TRN_MARINE_TRANSIT1 set MT_POLICY_ID="+first_qno+" where MT_POLICY_ID in("+all_Qnos+")");
		//T_TRN_MARINE_TRANSIT1 - End
		//T_TRN_MARINE_DETAIL1 - Start
		runner.updation("Update T_TRN_MARINE_DETAIL1 set MD_POLICY_ID="+first_qno+" where MD_POLICY_ID in("+all_Qnos+")");
		//T_TRN_MARINE_DETAIL1 - ENd
		//T_TRN_MARINE_CARRIER1 - Start
		runner.updation("Update T_TRN_MARINE_CARRIER1 set MCR_POLICY_ID="+first_qno+" where MCR_POLICY_ID in("+all_Qnos+")");
		//T_TRN_MARINE_CARRIER1 - End
		//T_TRN_MARINE_LC1 - Start
		runner.updation("Update T_TRN_MARINE_LC1 set LC_POLICY_ID="+first_qno+" where LC_POLICY_ID in("+all_Qnos+")");
		//T_TRN_MARINE_LC1 - End
		//T_TRN_DTL_DEBIT_NOTE1 - Start

		//For RSA Issuer
				String rsaissuer = runner.singleSelection("select nvl(APPLICATION_ID,'1') from POSITION_MASTER where QUOTE_NO='"+first_qno+"'");
				String rsaPercent = "0";
				if(rsaissuer.equalsIgnoreCase("1"))
					rsaPercent = "0";
				else
				{
					try
					{
						rsaPercent =runner.singleSelection("select nvl(DETAIL_NAME,'0') from CONSTANT_DETAIL where CATEGORY_ID='100' and CATEGORY_DETAIL_ID='1' and branch_code='"+loginBra+"'");
					}
					catch (Exception e)
					{
						rsaPercent="2.5";
						System.out.println("exception in"+e.toString());e.printStackTrace();
					}
					
				}

		pol_commission = runner.singleSelection("select nvl(COMMISSION,'0')-"+rsaPercent+" from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select distinct OPEN_COVER_NO from POSITION_MASTER where quote_no in("+all_Qnos+"))) and AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_POSITION_MASTER where OPEN_COVER_NO=(select distinct OPEN_COVER_NO from POSITION_MASTER where quote_no in("+all_Qnos+"))))");
		if(pol_premium.length()>0&&pol_commission.length()>0)
			Commission = (Double.parseDouble(pol_premium)*Double.parseDouble(pol_commission))/100;
			runner.updation("Update T_TRN_DTL_DEBIT_NOTE1 set  DND_AMOUNT=-"+pol_premium+" where DND_POLICY_ID="+first_qno+" and DND_SERIAL_NO=1");
			runner.updation("Update T_TRN_DTL_DEBIT_NOTE1 set  DND_AMOUNT="+Commission+" where DND_POLICY_ID="+first_qno+" and DND_SERIAL_NO=2");
		//T_TRN_DTL_DEBIT_NOTE1 - End
	}
	//For Non Std text - means clauses, exclusion, warrenties and Extra Clauses for one off Policy
	public String[][] getAdminEditOptions(String quoteNo) 
	{
		String[][] userType = new String[0][0];
		try 
		{
				String sql = "select PDF_PRE_SHOW_STATUS,PDF_BANKER_STATUS,PDF_MODIFY_CLAUSE,PDF_MODIFY_WARRANTY,PDF_MODIFY_EXCLUSION,PDF_MODIFY_EXTRACLAUSES,PDF_BANKER_ASSURED_STATUS,PDF_CURRENCY_STATUS from position_master where  quote_no=?";
				String args[] = new String[1];
				args[0] = quoteNo;
				userType = runner.multipleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("the Exception is getAdminEditOptions "+ e.toString());e.printStackTrace();
		}
		return userType;
	}
	public String getOpenCoverNo(String quoteNo) 
	{
		String openCoverNo = "";
		try 
		{
				String sql = "select nvl(OPEN_COVER_NO,'0') from position_master where  quote_no=?";
				String args[] = new String[1];
				args[0] = quoteNo;
				openCoverNo = runner.singleSelection(sql,args);
		}
		catch (Exception e) 
		{
			System.out.println("the Exception is getOpenCoverNo "+ e.toString());e.printStackTrace();
		}
		return openCoverNo;
	}
	public String getAllClausesOne(String quoteNo,String loginBra,String bcid,String coverId,String transPortId,String exIds,
			String startCountryWarranties,String destinationCountryWarranties,String warIds,String seaOption,
			String wareStartOpt,String wareFinalOpt,String extraCovId)throws BaseException
	{
		
			runner runner = new runner();
			rsa.pdf.PDFDisplay pdis=new rsa.pdf.PDFDisplay();
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
			String pdfDisplayStatus[][] = new String[0][0];
			//Edit
			String pdfDisplayClauses[][] = new String[0][0];
			String pdfDisplayExtraClauses[][] = new String[0][0];
			String pdfDisplayWarranties[][] = new String[0][0];
			String pdfDisplayExclusions[][] = new String[0][0];
			//New
			String[][] resultExclusion = new String[0][0];
			String[][] resultWarranty = new String[0][0];
			String[][] resultClauses = new String[0][0];
			String[][] resultExtraClauses = new String[0][0];
			//For getting Edited info by Admin
			pdfDisplayStatus = getAdminEditOptions(quoteNo);
			
			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses: pdfDisplayStatus[0][2]; //// Clauses
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses: pdfDisplayStatus[0][5]; // Extra Clauses
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars: pdfDisplayStatus[0][3]; // War Clauses
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx: pdfDisplayStatus[0][4];  // Exclusion
			

			if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
			{
				concatClausesIds = "0";
			}
			else
			{
				pdfDisplayClauses = pdis.makeTwoDimArray(pdfClauses);
				for (int i = 0; i < pdfDisplayClauses.length; i++) 
				{
					concatClausesIds = concatClausesIds+ ","+(pdfDisplayClauses[i][0] == null ? "0":pdfDisplayClauses[i][0]);
				}
				concatClausesIds = concatClausesIds.substring(1,concatClausesIds.length());
			}
			if ("NOTHING".equalsIgnoreCase(pdfexClauses)) 
			{
				concatExtraClausesIds = "0";
			} 
			else 
			{
				pdfDisplayExtraClauses = pdis.makeTwoDimArray(pdfexClauses);
				for (int i = 0; i < pdfDisplayExtraClauses.length; i++) 
				{
					concatExtraClausesIds = concatExtraClausesIds+","+(pdfDisplayExtraClauses[i][0] == null ? "0":pdfDisplayExtraClauses[i][0]);
				}
				concatExtraClausesIds = concatExtraClausesIds.substring(1,concatExtraClausesIds.length());
				
			}
			if ("NOTHING".equalsIgnoreCase(pdfWars)) 
			{
				concatWarClausesIds = "0";
			} 
			else
			{
				pdfDisplayWarranties = pdis.makeTwoDimArray(pdfWars);
				for (int i = 0; i < pdfDisplayWarranties.length; i++) 
				{
					if ("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId)) 
					{
						concatWarClausesIds = concatWarClausesIds + ","+"0";
					} 
					else
					{
						concatWarClausesIds = concatWarClausesIds+","+(pdfDisplayWarranties[i][0] == null ? "0": pdfDisplayWarranties[i][0]);
					}
				}
				concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
			}

			if ("NOTHING".equalsIgnoreCase(pdfEx)) 
			{
				concatExClausesIds = "0";
				
			}
			else
			{
				pdfDisplayExclusions = pdis.makeTwoDimArray(pdfEx);
				
				for (int i = 0; i < pdfDisplayExclusions.length; i++) 
				{
					if (("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId))|| ("1".equalsIgnoreCase(transPortId) && ("2".equalsIgnoreCase(coverId) || "3".equalsIgnoreCase(coverId)))) 
					{
						concatExClausesIds = concatExClausesIds + "," + "0";
					}
					else
					{
						concatExClausesIds = concatExClausesIds+","+(pdfDisplayExclusions[i][0] == null ? "0":pdfDisplayExclusions[i][0]);
					}
				}
				concatExClausesIds = concatExClausesIds.substring(1,concatExClausesIds.length());
				
			}
			String exclusions = "";
			if (("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId))|| ("1".equalsIgnoreCase(transPortId) && ("2".equalsIgnoreCase(coverId) || "3".equalsIgnoreCase(coverId)))) 
			{
				exclusions = exclusions + "," + "0";
			}
			else 
			{
				exclusions = exclusions + "," + exIds;
			}
			exclusions = exclusions.substring(1, exclusions.length());
			String warrantyIds="";
			if ("2".equalsIgnoreCase(transPortId)&& "5".equalsIgnoreCase(coverId))
			{
				warrantyIds = warrantyIds + "," + "0";
			}
			else
			{
				warrantyIds = warrantyIds + "," + warIds;
			}
			String wareYesMsg = "";
			if ("Yes".equalsIgnoreCase(wareStartOpt)&& "Yes".equalsIgnoreCase(wareFinalOpt))
			{
				wareYesMsg = "Cover from Warehouse to Warehouse,";
			}
			else 
			{
				if (!"Yes".equalsIgnoreCase(wareStartOpt))
					warrantyIds = warrantyIds + "," + startCountryWarranties;
				if (!"Yes".equalsIgnoreCase(wareFinalOpt))
					warrantyIds = warrantyIds + "," + destinationCountryWarranties;
			}

			warrantyIds = warrantyIds.substring(1, warrantyIds.length());
							
			String sqll = "select exclusion_description from exclusion_master where exclusion_id in ("+exclusions+") and exclusion_id not in ("+concatExClausesIds+") and status in ('Y','R')  and branch_code='"+loginBra+"'  order by exclusion_id";

			resultExclusion = runner.multipleSelection(sqll);
			
			String sqll1 = "select warranty_description from warranty_master where warranty_id in ("+warrantyIds+") and warranty_id not in ("+concatWarClausesIds+") and status in ('Y','R') and branch_code='"+loginBra+"' order by WARRANTY_ID";
			resultWarranty = runner.multipleSelection(sqll1);
					
			String extraCoverId = extraCovId;
			if (transPortId.equalsIgnoreCase("3")) 
			{
				extraCoverId = "0";
			}

			if (extraCoverId.equalsIgnoreCase("0")) 
			{
				String sql23 = "select clauses_description from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") and status in ('Y','R') and branch_code='"+loginBra+"' order by clauses_id";
				resultClauses = runner.multipleSelection(sql23);
				extraCoverId = "";
			}
			else 
			{
				String sql12 = "select clauses_description from clauses_master where cover_id='"+ coverId+ "' and extra_cover_id='0' and clauses_id not in("+ concatClausesIds+ ") and status in ('Y','R') and branch_code='"+loginBra+"' order by clauses_id";
				resultClauses = runner.multipleSelection(sql12);
				
				String sql22 = "select clauses_description from clauses_master where(cover_id in('"+coverId+"') and  extra_cover_id='"+extraCoverId+"')  and clauses_id not in("	+ concatExtraClausesIds	+ ") and status in ('Y','R') and branch_code='"+loginBra+"' order by clauses_id";

				resultExtraClauses = runner.multipleSelection(sql22);
				extraCoverId = "";
			}
			
			String finalClauses = "";
			//For Clause Edit
			for(int i=0;i<pdfDisplayClauses.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayClauses[i][1]!=null?pdfDisplayClauses[i][1]:"")+",";
				
			}
			//For Cluses New
			for(int i=0;i<resultClauses.length;i++)
			{
				finalClauses = finalClauses+(resultClauses[i][0]!=null?resultClauses[i][0]:"")+",";
				
			}
			//For extra Edit
			for(int i=0;i<pdfDisplayExtraClauses.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayExtraClauses[i][1]!=null?pdfDisplayExtraClauses[i][1]:"")+",";
				
			}
			//For Extra New
			for(int i=0;i<resultExtraClauses.length;i++)
			{
				finalClauses = finalClauses+(resultExtraClauses[i][0]!=null?resultExtraClauses[i][0]:"")+",";
				
			}
			//For exclusion edit
			for(int i=0;i<pdfDisplayExclusions.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayExclusions[i][1]!=null?pdfDisplayExclusions[i][1]:"")+",";
				
			}
			//For exclusion New
			for(int i=0;i<resultExclusion.length;i++)
			{
				finalClauses = finalClauses+(resultExclusion[i][0]!=null?resultExclusion[i][0]:"")+",";
				
			}
			//For warrenty edit
			for(int i=0;i<pdfDisplayWarranties.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayWarranties[i][1]!=null?pdfDisplayWarranties[i][1]:"")+",";
				
			}
			//For warrenty New
			for(int i=0;i<resultWarranty.length;i++)
			{
				finalClauses = finalClauses+(resultWarranty[i][0]!=null?resultWarranty[i][0]:"")+",";
				
			}
			if(wareYesMsg.length()>0)
				finalClauses = finalClauses+wareYesMsg;
			if ((seaOption.trim()).length()>0) 
			{
				
				if ("LCL".equalsIgnoreCase(seaOption)) 
				{
					
					finalClauses = finalClauses +"Warranted cargo's are in less than container load only,";
				} 
				else if ("FCL".equalsIgnoreCase(seaOption))
				{
			
					finalClauses = finalClauses +"Warranted cargo's are in full container load only,";
					
				}
			}
			String policysBackDesc = getPolicysBackDesc(quoteNo);
			
			if(policysBackDesc.length()>0&&!policysBackDesc.equalsIgnoreCase("NOTHING")&&!policysBackDesc.equalsIgnoreCase("Admin"))
				finalClauses = finalClauses + policysBackDesc+",";
			else if(!policysBackDesc.equalsIgnoreCase("Admin")) 
			{
				finalClauses = finalClauses + getPolicysFreshBackDesc(loginBra)+",";
			}
			finalClauses = finalClauses + getExcessMsg(quoteNo,bcid,transPortId);
			String partialShip = "";
			try
			{
				partialShip = runner.singleSelection("select nvl(PARTIAL_SHIPMENT_ALLOWED,'N') from MARINE_POLICY_DETAILS where quote_no='"+quoteNo+"'");
				if ("Y".equalsIgnoreCase(partialShip)) 
				{
					
					finalClauses = finalClauses +"Partial shipments are allowed,";
				} 
			}
			catch (Exception e)
			{
				System.out.println("exception in mississippi..."+e.toString());e.printStackTrace();
			}
			finalClauses = finalClauses.substring(0,(finalClauses.length()-1));
			return finalClauses;
	}
	public String getPolicysFreshBackDesc(String branch) 
	{
		com.maan.services.policyInfo pol = new com.maan.services.policyInfo();
		String res = "";
		try
		{
			res = pol.getPolicysFreshBackDesc(branch);
		}
		catch (Exception e)
		{
			System.out.println("exception in policy info..."+e.toString());e.printStackTrace();
		}
		return res;
	}
	public String getPolicysBackDesc(String quote_no) 
	{
		com.maan.services.policyInfo pol = new com.maan.services.policyInfo();
		String res = "";
		try
		{
			res = pol.getPolicysBackDesc(quote_no);

		}
		catch (Exception e)
		{
			System.out.println("exception in mississippi..."+e.toString());e.printStackTrace();
		}
		return res;
	}
	/*public String getOneOffBackDateMsg(String quoteNo)
	{

		String backDateMsg = "";
		com.maan.services.policyInfo pol = new com.maan.services.policyInfo();
		String backDaysCheck[][] = new String[0][0];
		backDaysCheck	= pol.getBackDatedStatus(quoteNo);
		String backDaysPolicy="";
		String backDaysBL="";
		if(backDaysCheck.length >0)
		{
			backDaysPolicy=backDaysCheck[0][0];
			backDaysBL=backDaysCheck[0][1];
		}else
		{
			backDaysPolicy="0";
			backDaysBL="0";
		}
		int countBackDays = 0;
		String temp = getBackDatesAllowed(quoteNo);
		if(temp!=null&&temp.length()>0&&!temp.equals("0"))
			countBackDays = Integer.parseInt(temp);
		else
			countBackDays = 0;
		backDaysPolicy=backDaysPolicy==null?"0":backDaysPolicy;
		backDaysBL=backDaysBL==null?"0":backDaysBL;
		//if(Integer.parseInt(backDaysPolicy) > countBackDays || Integer.parseInt(backDaysBL) > countBackDays)
		{
			String getDetails[][] = new String[0][0];
			//getDetails = getPolicy(quoteNo);
			backDateMsg = "Subject to NO LOSS prior to date and time printed on this certificate,";
			
		}
		return backDateMsg;
	}*/
	public String getOpenCoverBackDateMsg(String quoteNo,String openCoverNo)
	{

		String backDateMsg = "";
		com.maan.services.policyInfo pol = new com.maan.services.policyInfo();
		String backDaysCheck[][] = new String[0][0];
		backDaysCheck	= pol.getBackDatedStatus(quoteNo);
		String backDaysPolicy="";
		String backDaysBL="";
		if(backDaysCheck.length >0)
		{
			backDaysPolicy=backDaysCheck[0][0];
			backDaysBL=backDaysCheck[0][1];
		}else
		{
			backDaysPolicy="0";
			backDaysBL="0";
		}
		int countBackDays = 0;
		String temp = pol.getBackDatedStatusOpencover(openCoverNo);
		if(temp!=null&&temp.length()>0)
			countBackDays = Integer.parseInt(temp);
		backDaysPolicy=backDaysPolicy==null?"0":backDaysPolicy;
		backDaysBL=backDaysBL==null?"0":backDaysBL;
		if(Integer.parseInt(backDaysPolicy) > countBackDays || Integer.parseInt(backDaysBL) > countBackDays)
		{
			String getDetails[][] = new String[0][0];
			getDetails = getPolicy(quoteNo);
			backDateMsg = getPolicysFreshBackDesc(loginBra)+",";
			
		}
		return backDateMsg;
	}
	public String getBackDatesAllowed(String quoteNo)
	{
			String result 		= "0";
			String typeQry 		= "";
			String userType 	= "";
			String backDateQry 	= "";
			try
			{
				String args[] = new String[2];
				args[0] = quoteNo;
				args[1] = quoteNo;
				backDateQry = "select nvl(BACK_DATE_ALLOWED,0) from login_user_details where agency_code in(select agency_code from login_master where login_id=(select login_id from position_master where quote_no=?)) and product_id=(select product_id from position_master where quote_no=?)";
				result = runner.singleSelection(backDateQry,args);
			}
            catch(Exception ex)
			{
                ex.printStackTrace();
            }
			return result;
     }
	public String[][] getPolicy(String quoteNo)
	{
		String[][] returnVal = new String[0][0];
		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			String qry = "select application_no,premium,to_char(entry_date,'dd') as dates,to_char(entry_date,'MM') as months,to_char(entry_date,'YYYY') as years,excess_premium,total_sum_insured,to_char(inception_date,'dd-MM-YYYY') from marine_data where application_no=(select application_no from position_master where quote_no=?)";
			returnVal = runner.multipleSelection(qry,args);
		} 
		catch (Exception e) 
		{
			System.out.println("  ERROR  in mississippi.java " + e.toString());e.printStackTrace();
		}
		return returnVal;
	}
	public String getExcessMsg(String quoteNo,String bcid,String transPortId)throws BaseException
	{
		String excessMsg="";
		rsa.pdf.finalprint cc1 = new rsa.pdf.finalprint();
		runner runner = new runner();
		String[][] returnVal = new String[0][0];
		String sql = "select md.TRANSIT_FROM_COUNTRY_ID,md.FINAL_DESTINATION_COUNTRY_ID,nvl(mr.FRAGILE,'off') from MARINE_RESULT_DETAILS mr,MARINE_DATA md where mr.APPLICATION_NO=(select APPLICATION_NO from position_master where quote_no=?) and md.APPLICATION_NO=mr.APPLICATION_NO";
		String TransitFromCountryId = "";
		String FinalDestinationCountryId = "";
		String FragileMessage = "";

		try 
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			returnVal = runner.multipleSelection(sql,args);
			if(returnVal.length>0)
			{
				TransitFromCountryId = returnVal[0][0]!=null?returnVal[0][0]:"1";
				FinalDestinationCountryId = returnVal[0][1]!=null?returnVal[0][1]:"1";
				FragileMessage = returnVal[0][2]!=null?returnVal[0][2]:"off";
			}
		} 
		catch (Exception e) {System.out.println("  ERROR  in mississippi.java " + e.toString());e.printStackTrace();
		}
		if (!"on".equalsIgnoreCase(FragileMessage))
		{
			String comExcess[][] = new String[0][0];
			if(transPortId.equalsIgnoreCase("1") || transPortId.equalsIgnoreCase("2") )
			{
				if(!FinalDestinationCountryId.equalsIgnoreCase(bcid) && TransitFromCountryId.equalsIgnoreCase(bcid))
				{
					comExcess = cc1.getComExcessPre(quoteNo,"draftMode");
					if(comExcess.length>0)
					{
						for(int e=0;e<comExcess.length;e++)
						{
							String temp = comExcess[e][0]!=null?comExcess[e][0]:"0";
							if(!temp.equals("0"))//&&eccount<=1)
							{
								excessMsg = excessMsg+"Excess : "+temp+" each and every claim,";

							}
						}
					}
				}//Export Time Only
			}//For Sea and Air only
		}
		else
		{
			excessMsg = excessMsg+"0.50% of the Sum Insured subject to minimum of US$ 250/- Each and Every Claim for FRAGILE Commodity,";
		}
		return excessMsg;
	}
	public String getAllClausesOpenCover(String quoteNo,String loginBra,String bcid,String coverId,String transPortId,String exIds,
			String startCountryWarranties,String destinationCountryWarranties,String warIds,String seaOption,String extraCovId)throws BaseException
	{
			runner runner = new runner();
			rsa.pdf.PDFDisplay pdis=new rsa.pdf.PDFDisplay();
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
			String pdfDisplayStatus[][] = new String[0][0];
			//Edit
			String pdfDisplayClauses[][] = new String[0][0];
			String pdfDisplayExtraClauses[][] = new String[0][0];
			String pdfDisplayWarranties[][] = new String[0][0];
			String pdfDisplayExclusions[][] = new String[0][0];
			//New
			String[][] resultExclusion = new String[0][0];
			String[][] resultWarranty = new String[0][0];
			String[][] resultClauses = new String[0][0];
			String[][] resultExtraClauses = new String[0][0];
			//For getting Edited info by Admin
			pdfDisplayStatus = getAdminEditOptions(quoteNo);
			String openCoverNo = getOpenCoverNo(quoteNo);
			pdfClauses = pdfDisplayStatus[0][2] == null ? pdfClauses: pdfDisplayStatus[0][2]; //// Clauses
			pdfexClauses = pdfDisplayStatus[0][5] == null ? pdfexClauses: pdfDisplayStatus[0][5]; // Extra Clauses
			pdfWars = pdfDisplayStatus[0][3] == null ? pdfWars: pdfDisplayStatus[0][3]; // War Clauses
			pdfEx = pdfDisplayStatus[0][4] == null ? pdfEx: pdfDisplayStatus[0][4];  // Exclusion
			

			if ("NOTHING".equalsIgnoreCase(pdfClauses)) 
			{
				concatClausesIds = "0";
			}
			else
			{
				pdfDisplayClauses = pdis.makeTwoDimArray(pdfClauses);
				for (int i = 0; i < pdfDisplayClauses.length; i++) 
				{
					concatClausesIds = concatClausesIds+ ","+(pdfDisplayClauses[i][0] == null ? "0":pdfDisplayClauses[i][0]);
				}
				concatClausesIds = concatClausesIds.substring(1,concatClausesIds.length());
			}
			if ("NOTHING".equalsIgnoreCase(pdfexClauses)) 
			{
				concatExtraClausesIds = "0";
			} 
			else 
			{
				pdfDisplayExtraClauses = pdis.makeTwoDimArray(pdfexClauses);
				for (int i = 0; i < pdfDisplayExtraClauses.length; i++) 
				{
					concatExtraClausesIds = concatExtraClausesIds+","+(pdfDisplayExtraClauses[i][0] == null ? "0":pdfDisplayExtraClauses[i][0]);
				}
				concatExtraClausesIds = concatExtraClausesIds.substring(1,concatExtraClausesIds.length());
				
			}
			if ("NOTHING".equalsIgnoreCase(pdfWars)) 
			{
				concatWarClausesIds = "0";
			} 
			else
			{
				pdfDisplayWarranties = pdis.makeTwoDimArray(pdfWars);
				for (int i = 0; i < pdfDisplayWarranties.length; i++) 
				{
					if ("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId)) 
					{
						concatWarClausesIds = concatWarClausesIds + ","+"0";
					} 
					else
					{
						concatWarClausesIds = concatWarClausesIds+","+(pdfDisplayWarranties[i][0] == null ? "0": pdfDisplayWarranties[i][0]);
					}
				}
				concatWarClausesIds = concatWarClausesIds.substring(1,concatWarClausesIds.length());
			}

			if ("NOTHING".equalsIgnoreCase(pdfEx)) 
			{
				concatExClausesIds = "0";
				
			}
			else
			{
				pdfDisplayExclusions = pdis.makeTwoDimArray(pdfEx);
				
				for (int i = 0; i < pdfDisplayExclusions.length; i++) 
				{
					if (("2".equalsIgnoreCase(transPortId) && "5".equalsIgnoreCase(coverId))|| ("1".equalsIgnoreCase(transPortId) && ("2".equalsIgnoreCase(coverId) || "3".equalsIgnoreCase(coverId)))) 
					{
						concatExClausesIds = concatExClausesIds + "," + "0";
					}
					else
					{
						concatExClausesIds = concatExClausesIds+","+(pdfDisplayExclusions[i][0] == null ? "0":pdfDisplayExclusions[i][0]);
					}
				}
				concatExClausesIds = concatExClausesIds.substring(1,concatExClausesIds.length());
				
			}
			String exclusions = "";
			exclusions = exclusions + "," + exIds;
			exclusions = exclusions.substring(1, exclusions.length());
			String warrantyIds="";
			warrantyIds = warrantyIds + "," + warIds;
			warrantyIds = warrantyIds + "," + startCountryWarranties;
			warrantyIds = warrantyIds + "," + destinationCountryWarranties;

			warrantyIds = warrantyIds.substring(1, warrantyIds.length());
							
			String sqll = "select occmss.exclusion_description,occmss.status,occmss.exclusion_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"'  and occmss.exclusion_id not in("+concatExClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+openCoverNo+"' and (occms.exclusion_id not in("+concatExClausesIds+") and occms.amend_id in( select max(occmsss.amend_id) from open_cover_exclusions occmsss,open_cover_position_master ocpmsss where ocpmsss.open_cover_no ='"+openCoverNo+"' and ocpmsss.proposal_no=occmsss.proposal_no and to_date(occmsss.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY')))  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY'))";

			resultExclusion = runner.multipleSelection(sqll);
			
			String sqll1 = "select occmss.warranty_description,occmss.status,occmss.warranty_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"'   and occmss.warranty_id not in("+concatWarClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+openCoverNo+"'  and (occms.warranty_id not in("+concatWarClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY')) ) and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY'))";

			resultWarranty = runner.multipleSelection(sqll1);
					
			String extraCoverId =extraCovId;
			if (transPortId.equalsIgnoreCase("3")) 
			{
				extraCoverId = "0";
			}

			if (extraCoverId.equalsIgnoreCase("0")) 
			{
				String sql23 = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and occmss.cover_id='"+coverId+"' and occmss.extra_cover_id='0' and occmss.clauses_id not in("+concatClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+openCoverNo+"' and occms.cover_id='"+coverId+"' and occmss.extra_cover_id='0' and (occms.clauses_id not in("+concatClausesIds+") and occms.amend_id in(select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and occmss.cover_id='"+coverId+"' and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY'))";

				resultClauses = runner.multipleSelection(sql23);
				extraCoverId = "";
			}
			else 
			{
				String sql12 = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and occmss.cover_id='"+coverId+"' and occmss.extra_cover_id='0'  and occmss.clauses_id not in("+concatClausesIds+") and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+openCoverNo+"' and occms.cover_id='"+coverId+"' and occmss.extra_cover_id='0' and (occms.clauses_id not in("+concatClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and occmss.cover_id='"+coverId+"' and occmss.extra_cover_id='0' and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY'))";
				resultClauses = runner.multipleSelection(sql12);
				
				String sql22 = "select occmss.clauses_description,occmss.status,occmss.clauses_id,occmss.extra_cover_id,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"')  and occmss.clauses_id not in("+concatExtraClausesIds+") and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.open_cover_no ='"+openCoverNo+"'  and (occms.clauses_id not in("+concatExtraClausesIds+") and occms.amend_id in( select max(occmss.amend_id) from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.open_cover_no ='"+openCoverNo+"' and (occmss.cover_id in('"+coverId+"') and  occmss.extra_cover_id='"+extraCoverId+"') and occmss.clauses_id  in(select cm.clauses_id from clauses_master cm,extra_cover_master ecm where cm.extra_cover_id=ecm.extra_cover_id and ecm.extra_cover_id not in (0) and cm.status in ('Y','R')) and ocpmss.proposal_no=occmss.proposal_no and to_date(occmss.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY')) )  and ocpms.proposal_no=occms.proposal_no and to_date(occms.effective_date,'dd-MON-YYYY') <= to_date(sysdate,'dd-MON-YYYY'))  order by occmss.clauses_id";

				resultExtraClauses = runner.multipleSelection(sql22);
				extraCoverId = "";
			}
			
			String finalClauses = "";
			//For Clause Edit
			for(int i=0;i<pdfDisplayClauses.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayClauses[i][1]!=null?pdfDisplayClauses[i][1]:"")+",";
				
			}
			//For Cluses New
			for(int i=0;i<resultClauses.length;i++)
			{
				finalClauses = finalClauses+(resultClauses[i][0]!=null?resultClauses[i][0]:"")+",";
				
			}
			//For extra Edit
			for(int i=0;i<pdfDisplayExtraClauses.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayExtraClauses[i][1]!=null?pdfDisplayExtraClauses[i][1]:"")+",";
				
			}
			//For Extra New
			for(int i=0;i<resultExtraClauses.length;i++)
			{
				finalClauses = finalClauses+(resultExtraClauses[i][0]!=null?resultExtraClauses[i][0]:"")+",";
				
			}
			//For exclusion edit
			for(int i=0;i<pdfDisplayExclusions.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayExclusions[i][1]!=null?pdfDisplayExclusions[i][1]:"")+",";
				
			}
			//For exclusion New
			for(int i=0;i<resultExclusion.length;i++)
			{
				finalClauses = finalClauses+(resultExclusion[i][0]!=null?resultExclusion[i][0]:"")+",";
				
			}
			//For warrenty edit
			for(int i=0;i<pdfDisplayWarranties.length;i++)
			{
				finalClauses = finalClauses+(pdfDisplayWarranties[i][1]!=null?pdfDisplayWarranties[i][1]:"")+",";
				
			}
			//For warrenty New
			for(int i=0;i<resultWarranty.length;i++)
			{
				finalClauses = finalClauses+(resultWarranty[i][0]!=null?resultWarranty[i][0]:"")+",";
				
			}
			String policysBackDesc = getPolicysBackDesc(quoteNo);
			if(policysBackDesc.length()>0&&!policysBackDesc.equalsIgnoreCase("NOTHING")&&!policysBackDesc.equalsIgnoreCase("Admin"))
				finalClauses = finalClauses + policysBackDesc;
			else if(!policysBackDesc.equalsIgnoreCase("Admin"))
				finalClauses = finalClauses + getOpenCoverBackDateMsg(quoteNo,openCoverNo);
			finalClauses = finalClauses.substring(0,(finalClauses.length()-1));
			return finalClauses;
	}
	public double getRSAIssuerCommission(String quoteNo,String rsaIssuer,String pid)
	{
		com.maan.services.policyInfo pol = new com.maan.services.policyInfo();
		double res = 0;
		String login = "";
		String polNo = "";
		String result[][]= new String[0][0];
		try
		{
			String args[] = new String[1];
			args[0] = quoteNo;
			result = runner.multipleSelection("select login_id,policy_no from position_master where quote_no=?",args);
			if(result.length>0)
			{
				login = result[0][0];
				polNo = result[0][1];
			}
			res = pol.getRSAIssuerCommission(login,rsaIssuer,pid,polNo);
		}
		catch (Exception e)
		{
			System.out.println("exception in policy info..."+e.toString());e.printStackTrace();
		}
		return res;
	}
	public boolean isMultipleCheck() {
		return multipleCheck;
	}
	public void setMultipleCheck(boolean multipleCheck) {
		this.multipleCheck = multipleCheck;
	}
	public String getCargoType(String cargoTypeId,String branchCode)
	{
		String result = "";
		try
		{
			String args[] = new String[2];
			args[0] = cargoTypeId;
			args[1] = branchCode;
			String sql = "select nvl(DETAIL_NAME,' ') from CONSTANT_DETAIL where CATEGORY_ID='42' and CATEGORY_DETAIL_ID=? and branch_Code=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e){e.printStackTrace();}
		return result;
	}
	public String getCargoRSACode(String[][] cargoTypes,String branchCode)
	{
		String result = "";
		try
		{
			String cargoTypeId = "";
			for(int i=0;i<cargoTypes.length;i++){
				cargoTypeId = cargoTypeId+cargoTypes[i][0]+",";
			}
			cargoTypeId = cargoTypeId.substring(0,cargoTypeId.length()-1);
			String args[] = new String[1];
			args[0] = branchCode;
			String sql = "select count(*) from CONSTANT_DETAIL where CATEGORY_ID='42' and CATEGORY_DETAIL_ID in("+cargoTypeId+") and branch_Code=? and RSACODE='1'";
			result = runner.singleSelection(sql,args);
			if(result.length()<=0||"0".equals(result)){
				result = "0";
			}
			else{
				result = "1";
			}
		}
		catch(Exception e){e.printStackTrace();}
		return result;
	}
}
