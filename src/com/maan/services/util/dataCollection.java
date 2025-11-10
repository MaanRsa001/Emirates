package com.maan.services.util;

import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;

public class dataCollection
{
	private static final Logger logger = LogUtil.getLogger(dataCollection.class);

	String searchStatus = "";
	public String getSearchStatus()
	{
		return this.searchStatus;
	}
	public void setSearchStatus(String searchStatus)
	{
		this.searchStatus = searchStatus;
	}
	String[][] empByBroker=new String[0][0];

	public String[][] getEmpByBroker(String loginId)
	{
		String  sql="select login_id from login_master where oa_code='"+getAgencyCode(loginId)+"' or created_by='"+getAgencyCode(loginId)+"' or login_id='"+loginId+"'";
		String[][]	valuess=runner.multipleSelection(sql);
		return valuess;
	}

	public String[][] titleCollection1(String branch)
	{
		String  sql="";
		String[][] title = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = branch;
			sql ="select TITLE_NAME from TITLE_MASTER where branch_code=? and status='Y' order by TITLE_NAME ";
			title = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	//NEW FOR MULTI LANGUAGE
    public String[][] titleCollection(String language)
	{
		String sql = "";
		String args[] = new String[0];
		String[][] title = new String[0][0];
		if(language == null || language.equals("") || language.equalsIgnoreCase("null"))		
		{
			sql="select title_name from  title_master where language is null";
			title = runner.multipleSelection(sql);		
		}
		else
		{
			args[0] = language;
		    sql = "select title_name from title_master where language = '"+language+"'"; 
			title = runner.multipleSelection(sql,args);		
		}

		return title;
	}
    //END 

	public String[][] nationalityCollection()
	{
		String sql = "";
		String nationality[][] = new String[0][0];
		sql = "select NATIONALITY_NAME,COUNTRY_ID from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id) order by NATIONALITY_NAME ";

		nationality = runner.multipleSelection(sql);
		return nationality;
	}

	public String[][] countryCollection()
	{
		String sql="select country_name from country_master";
		String[][] title = runner.multipleSelection(sql);
		return title;
	}

	public String[][] countryCollectionWithId()
	{
		String sql = "select distinct country_name,COUNTRY_ID from country_master order by COUNTRY_ID";
			//"select COUNTRY_NAME,COUNTRY_ID  from COUNTRY_MASTER where amend_id||country_id in( select max(amend_id)||country_id from country_master group by country_id)order by country_id";
		
		String[][] title = runner.multipleSelection(sql);
		return title;
	}
	
	public String[][] countryCollectionWithId(final String cid) throws BaseException {
		logger.info("countryCollectionWithId DataCollection Method()");
		logger.debug("ENTER");
		String args[] = new String[1];
		args[0] = cid;
		final String sql = "select distinct country_name,COUNTRY_ID from country_master where country_id=?";
		final String[][] result = runner.multipleSelection(sql, args);
		logger.debug("EXIT");
		
		return result;
	}
	
	public String[][] emirateCollection()
	{
		String sql = "select city_name from city_master where country_id=1 order by city_id ";
		String[][] title = runner.multipleSelection(sql);
		return title;
	}
	
	public String setcutomerName(String quote)
	{
		String names = "";
		String[][] returnVal = new String[0][0];
		String qry = "";
		String args[] = new String[1];
		try
		{
			args[0] = quote;
			qry="select nvl(first_name,company_name),last_name from personal_info where customer_id=(select customer_id from position_master where quote_no=?)";
			returnVal = runner.multipleSelection(qry,args);
			if(returnVal.length>0)
				names = returnVal[0][0]==null?"":returnVal[0][0]+" "+returnVal[0][1]==null?"":returnVal[0][0];
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return names;
	}

	public String getReferalStatus(String quoteId) // admin referral - reedit issue
	{
		String[][] sqll = new String[0][0];
		String args[] = new String[0];
		args = new String[1];
		args[0] = quoteId;
		String sql = "";
		sql = "select count(referral) from marine_result_details where application_no=(select application_no from position_master where quote_no=?) and referral in('Refferal')";
		String referal = runner.singleSelection(sql,args);
		if(referal.length()<0||referal.equals(""))
			referal = "0";
		args[0] = quoteId;
		sql = "select remarks,admin_referral_status from marine_data where application_no=(select application_no from position_master where quote_no=?)";
		sqll = runner.multipleSelection(sql,args);

			if(Integer.parseInt(referal)>0)
				referal="referal";
			else if(!"referal".equalsIgnoreCase(referal))
			{
				if(sqll.length>0)
				{
					if(!"Normal".equalsIgnoreCase(sqll[0][0]==null?"":sqll[0][0]))
						referal="referal";
					/*if(!"Normal".equalsIgnoreCase(sqll[0][0]==null?"":sqll[0][0]) && sqll[0][1].equalsIgnoreCase("Y"))
						referal="referal";*/
				}
			}

			if("referal".equalsIgnoreCase(referal))
				referal="Referal";
			else
				referal="Normal";
			if(sqll.length>0)
			{
				args = new String[2];
				args[0] = "Y".equalsIgnoreCase(sqll[0][1]==null?"":sqll[0][1])?"Normal":referal;
				args[1] = quoteId;
				sql = "update position_master set remarks=? where quote_no=?";
				runner.multipleUpdation(sql,args);
			}
			return referal;
	}

	//for direct Client - July 15th
	public String[][] getTrashQuote(String loginIds,String prod_id,String type,String openCoverNo,String rsaissuer)
	{
		
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String args[] = new String[0];
		String syntax="";
		String multiLoginCondition = "";
		if(prod_id.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' ";
		else
			syntax = " ";

		try
		{
			if(rsaissuer==null)
			{
				if(!type.equalsIgnoreCase("Customer"))
				{
					getsTotal = new java.util.HashMap();
					args = new String[3];
					args[0] = loginIds;
					args[1] = loginIds;
					args[2] = loginIds;

					String  sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";

					valuess = runner.multipleSelection(sql,args);

					for(int i=0;i<valuess.length;i++)
					{
						loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
					}
					if(loginAllIds.length()>0)
						loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));

					
					sql="select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no "+syntax+" and a.login_id in ("+loginAllIds+")  and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.product_id = '"+prod_id+"' and a.APPLICATION_ID='1' order by a.quote_no DESC";
					ss=runner.multipleSelection(sql);
				}
				else if(type.equalsIgnoreCase("Customer"))
				{
					if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
						multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
					}
					else{
						multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
					}
					String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
					
					//Existing One
					//String condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";
					
					//String sql="select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no "+syntax+" and (a.customer_id in (select customer_id from personal_info where customer_login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.product_id = '"+prod_id+"'  order by a.quote_no DESC";
					String sql="select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no "+syntax+" and "+condition+" and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.product_id = '"+prod_id+"'  order by a.quote_no DESC";
					ss=runner.multipleSelection(sql);
				}
			}
			else
			{
				String sql="select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no "+syntax+" and a.login_id in ('"+loginIds+"')  and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.product_id = '"+prod_id+"' and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no DESC";
					ss=runner.multipleSelection(sql);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}
	
	public String[][] getViewQuotesSearchByQuote(String qno,String login,String pid,String searchOption)
	{
			String quotes[][] = new String[0][0];
			String quoStatus[][] = new String[0][0];
			String refqno = "";
			String qnosql = "";
			String args[] = new String[0];
			ValidationFormat validationFormat = new ValidationFormat();
			
		if(!searchOption.equalsIgnoreCase("Policy")&& (validationFormat.IsDigitValidationFormat(qno)==true))
		{
			args = new String[3];
			args[0] = qno;
			args[1] = pid;
			args[2] = login;
			String statusSql = "select p.status,nvl(p.Freight_status,' '),nvl(p.remarks,' '),m.ADMIN_REFERRAL_STATUS from position_master p, marine_data m where p.quote_no=? and p.product_id=? and p.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and p.application_no=m.application_no and p.application_id='1'";
			
			quoStatus = runner.multipleSelection(statusSql,args);
			String status="";
			String frestatus="";
			String remarks="";
			String appSql="";
			String lapSql="";
			String expSql="";
			String polSql="";
			String adminStatus="";
			if(quoStatus.length>0)
			{
				status = quoStatus[0][0]!=null?quoStatus[0][0]:"";
				frestatus = quoStatus[0][1]!=null?quoStatus[0][1]:"";
				remarks = quoStatus[0][2]!=null?quoStatus[0][2]:"";
				adminStatus = quoStatus[0][3]!=null?quoStatus[0][3]:"";
				if(adminStatus.equalsIgnoreCase("Y"))
					remarks = "Referral";
				if(!frestatus.equalsIgnoreCase("F"))
				{
					
					if(status.equalsIgnoreCase("Y"))
					{
						if(status.equalsIgnoreCase("Y")&&(remarks.equalsIgnoreCase("Normal")||remarks.length()<=0))
						{
							args = new String[3];
							args[0] = login;
							args[1] = qno;
							args[2] = pid;
							qnosql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and a.quote_no=? and a.product_id=? and (a.remarks not in('Admin','Referal','AdminReferal')) and b.customer_id=a.customer_id order by quote_no DESC";
							searchStatus = "Quotes";
							quotes=runner.multipleSelection(qnosql,args);
						}
						if(quotes.length<=0&&status.equalsIgnoreCase("Y")&&(remarks.equalsIgnoreCase("Normal")||remarks.length()<=0))
						{
							args = new String[3];
							args[0] = login;
							args[1] = qno;
							args[2] = pid;

							expSql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.quote_no=? and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id=? and a.status='Y'";
							quotes=runner.multipleSelection(expSql,args);
							searchStatus="Expired";
						}
						if(quotes.length<=0&&status.equalsIgnoreCase("Y")&&!remarks.equalsIgnoreCase("Admin"))
						{
							args = new String[3];
							args[0] = login;
							args[1] = qno;
							args[2] = pid;

							refqno = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and md.application_no=a.application_no and a.quote_no=? and a.product_id=? order by a.quote_no desc";
							quotes=runner.multipleSelection(refqno,args);
							searchStatus="ReferralUnApp";
						}
						if(quotes.length<=0&&status.equalsIgnoreCase("Y")&&remarks.equalsIgnoreCase("Admin"))
						{
							args = new String[3];
							args[0] = login;
							args[1] = qno;
							args[2] = pid;
							
							appSql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Admin') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N')) and md.application_no=a.application_no and a.quote_no=? and a.product_id=? order by a.quote_no desc";
							quotes=runner.multipleSelection(appSql,args);
							searchStatus="ReferralApp";
						}
					}
					else if(status.equalsIgnoreCase("D")||status.equalsIgnoreCase("R"))
					{
							args = new String[3];
							args[0] = login;
							args[1] = qno;
							args[2] = pid;

							lapSql = "select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no and a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?))  and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.quote_no=? and a.product_id =?  order by a.quote_no DESC";
							quotes=runner.multipleSelection(lapSql,args);
							searchStatus="Lapsed";
					}
					else if(status.equalsIgnoreCase("P"))
					{
							args = new String[3];
							args[0] = login;
							args[1] = pid;
							args[2] = qno;

						polSql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,'0') not in ('LINKED') and a.product_id=? and a.quote_no=? and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) order by a.inception_date desc";
						quotes=runner.multipleSelection(polSql,args);
						searchStatus="PolicyQuote";
					}
				}
			}
		}
		else if(searchOption.equalsIgnoreCase("Policy"))
		{
				args = new String[3];
				args[0] = login;
				args[1] = qno;
				args[2] = pid;

			String singleSql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and a.policy_no=? and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and b.customer_id=a.customer_id and  nvl(a.open_cover_int_status,'0') not in ('LINKED') and a.product_id=? and a.application_id='1' order by a.inception_date desc";
			quotes=runner.multipleSelection(singleSql,args);
			searchStatus="PolicyQuote";
			if(quotes.length<=0)
			{
						args = new String[3];
						args[0] = login;
						args[1] = pid;
						args[2] = qno;
				String multipleSql = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and nvl(a.open_cover_int_status,'0') in ('LINKED') and a.product_id=? and a.policy_no=? and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and a.application_id='1' group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
				quotes=runner.multipleSelection(multipleSql,args);
				searchStatus="PolicyMultiple";
			}
		}
		return quotes;
	}


	public String[][] getViewQuotesSearchByDate(String sdate,String edate,String login,String pid,String searchOption)
	{
			String quotes[][] = new String[0][0];
			String quoStatus[][] = new String[0][0];
			String refqno = "";
			String qnosql = "";
			String args[] = new String[0];
			String status="";
			String frestatus="";
			String remarks="";
			String appSql="";
			String lapSql="";
			String expSql="";
			String polSql="";
			String adminStatus="";
			
			if(searchOption.equalsIgnoreCase("q"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = sdate;
				args[2] = edate;
				args[3] = pid;

				qnosql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b,MARINE_DATA m where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.product_id=? and (a.remarks not in('Admin','Referal','AdminReferal')) and (m.ADMIN_REFERRAL_STATUS in('N') or m.ADMIN_REFERRAL_STATUS is null) and m.APPLICATION_NO=a.APPLICATION_NO and b.customer_id=a.customer_id and a.application_id='1' order by quote_no DESC";
				searchStatus="Quotes";
				quotes=runner.multipleSelection(qnosql,args);
			}
			else if(searchOption.equalsIgnoreCase("e"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = sdate;
				args[2] = edate;
				args[3] = pid;
			
				expSql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id=? and a.status='Y' and a.application_id='1' order by quote_no DESC";
				quotes=runner.multipleSelection(expSql,args);
				searchStatus="Expired";
			}
			else if(searchOption.equalsIgnoreCase("u"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = sdate;
				args[2] = edate;
				args[3] = pid;

				refqno = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and md.application_no=a.application_no and a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.product_id=? and a.application_id='1' order by a.quote_no desc";
				quotes=runner.multipleSelection(refqno,args);
				searchStatus="ReferralUnApp";
			}
			else if(searchOption.equalsIgnoreCase("a"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = sdate;
				args[2] = edate;
				args[3] = pid;

				appSql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Admin') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N')) and md.application_no=a.application_no and a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.product_id=? and a.application_id='1' order by a.quote_no desc";
				quotes=runner.multipleSelection(appSql,args);
				searchStatus="ReferralApp";
			}
			else if(searchOption.equalsIgnoreCase("L"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = sdate;
				args[2] = edate;
				args[3] = pid;

				lapSql = "select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME,a.remarks,a.freight_status,c.remarks from position_master a,personal_info b,marine_data c where c.application_no=a.application_no and a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?))  and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and (a.status in ('D','R')) and b.customer_id=a.customer_id and a.entry_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and a.product_id =?  and a.application_id='1' order by a.quote_no DESC";
				quotes=runner.multipleSelection(lapSql,args);
				searchStatus="Lapsed";
			}
			else if(searchOption.equalsIgnoreCase("p"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = pid;
				args[2] = sdate;
				args[3] = edate;
				
				polSql = "select a.policy_no,a.customer_id,a.premium,to_char(a.inception_date,'dd')as days,to_char(a.inception_date,'MM')as months,to_char(a.inception_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,a.quote_no,a.excess_premium,b.COMPANY_NAME,a.inception_date,nvl(a.open_cover_no,'0') from position_master a,personal_info b where a.login_id in (select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,'0') not in ('LINKED') and a.product_id=? and a.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.application_id='1' order by a.inception_date desc";
				quotes=runner.multipleSelection(polSql,args);
				searchStatus="PolicyQuote";
			}
			else if(searchOption.equalsIgnoreCase("m"))
			{
				args = new String[4];
				args[0] = login;
				args[1] = pid;
				args[2] = sdate;
				args[3] = edate;

				String multipleSql = "select (a.policy_no),a.login_id,nvl(a.open_cover_no,'0'),sum(a.premium),sum(a.excess_premium),count(a.policy_no) from position_master a where a.login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) and a.status='P' and nvl(a.open_cover_int_status,'0') in ('LINKED') and a.product_id=? and a.inception_date between to_date(?,'dd-mm-yyyy') and to_date(?,'dd-mm-yyyy')+1 and (a.FREIGHT_STATUS not in('F') or a.FREIGHT_STATUS is null) and a.application_id='1' group by a.policy_no,a.login_id,a.open_cover_no order by a.policy_no";
				quotes=runner.multipleSelection(multipleSql,args);
				searchStatus="PolicyMultiple";
			
			}
		return quotes;
	}

	// Quote Register Wxisting Quotes
	public String[][] getViewQuotesNotApproved(String loginIds,String searchOption,String searchValue,String productId,String openCoverNo,String rsaissuer) //ViewQuote_B2B.jsp Information Based on openCoverNo restriction
	{
	
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String syntax = " ";
		String args[] = new String[0];
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' and (a.CERTIFICATE_NO is null and a.CERTIFICATE_DATE is null) ";
		else
			syntax = " ";
		String  sql = "";
		try
		{
			if(rsaissuer==null)
			{
				args = new String[3];
				args[0] = loginIds;
				args[1] = loginIds;
				args[2] = loginIds;
				getsTotal=new java.util.HashMap();
				sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";

				valuess=runner.multipleSelection(sql,args);

				for(int i=0;i<valuess.length;i++)
				{
					loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
				}
				if(loginAllIds.length()>0)
					loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
				
				ss=new String[0][0];
				if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and a.product_id='"+productId+"' and a.APPLICATION_ID='1' order by quote_no DESC";
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.product_id='"+productId+"'  and a.APPLICATION_ID='1' order by quote_no DESC";

				}
				else if("quote_nos".equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null)  and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')  and a.APPLICATION_ID='1'  and a.product_id='"+productId+"' order by quote_no DESC";

				}
				else if("DateWise".equalsIgnoreCase(searchOption))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
					

					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

					}catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					System.out.println(" FINAL searchValue  "+searchValue);

					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"'  and a.APPLICATION_ID='1' order by quote_no DESC";

				searchValue1=null;
				searchValue2=null;
				searchValue3=null;

				}
			}
			else
			{
				ss=new String[0][0];
				if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and a.product_id='"+productId+"'  and a.APPLICATION_ID='"+rsaissuer+"' order by quote_no DESC";
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.product_id='"+productId+"'  and a.APPLICATION_ID='"+rsaissuer+"' order by quote_no DESC";
				}
				else if("quote_nos".equalsIgnoreCase(searchOption))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null)  and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')  and a.APPLICATION_ID='"+rsaissuer+"'   and a.product_id='"+productId+"' order by quote_no DESC";
				}
				else if("DateWise".equalsIgnoreCase(searchOption))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());

					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					}
					catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					System.out.println(" FINAL searchValue  "+searchValue);

					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"'  and a.APPLICATION_ID='"+rsaissuer+"' order by quote_no DESC";

				searchValue1=null;
				searchValue2=null;
				searchValue3=null;

				}else if("OtherUsers".equalsIgnoreCase(searchOption)){
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null)  and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')  and a.product_id='"+productId+"' and a.APPLICATION_ID not in ('"+rsaissuer+"','1') order by quote_no DESC";
				}
			}
			System.out.println("Quote Register .................."+sql);
			ss=runner.multipleSelection(sql);
		
		}
		catch(Exception e)
		{e.printStackTrace();}
		return ss;
	}
	//for direct Client - July 15th &&  June - 09 Multi Login Customer
	public String[][] getViewQuotesNotApproved(String loginIds,String searchOption,String searchValue,String productId,String openCoverNo) //viewQuoteB2B.jsp Information Based on openCoverNo restriction
	{
	
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String syntax = " ";
		String multiLoginCondition = "";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' and (a.CERTIFICATE_NO is null and a.CERTIFICATE_DATE is null) ";
		else
			syntax = " ";
		
		try
		{
			getsTotal=new java.util.HashMap();
			
			if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
				multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
			}
			else{
				multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
			}
			
			//String condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"')  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";
			String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
			String sql="";		
			ss=new String[0][0];
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and a.product_id='"+productId+"' order by quote_no DESC";
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.product_id='"+productId+"' order by quote_no DESC";
			}
			else if("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null)  and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')   and a.product_id='"+productId+"' order by quote_no DESC";
			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;

				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;
				}
				catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}

				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),(nvl(m.PREMIUM,0)+nvl(m.EXCESS_PREMIUM,0)) from position_master a,personal_info b,marine_data m where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) "+syntax+" and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by quote_no DESC";

			searchValue1=null;
			searchValue2=null;
			searchValue3=null;
			}
			System.out.println("Multi Login Condition ...."+sql);
			ss=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

//Rajesh For Freight Forwarder - start
public String[][] getViewQuotesNotApprovedForFreight(String loginIds,String searchOption,String searchValue,String productId)
{

		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[0];
		try
		{
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			getsTotal=new java.util.HashMap();
			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			System.out.println("  values length  "+valuess.length);
			ss=new String[0][0];
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),m.admin_referral_status from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status in('U','N','T') and b.customer_id=a.customer_id and a.product_id='"+productId+"' order by quote_no DESC";
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),m.admin_referral_status from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status in('U','N','T') and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.product_id='"+productId+"' order by quote_no DESC";

			}
			else if("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),m.admin_referral_status from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F')  and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status in('U','N','T') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')   and a.product_id='"+productId+"' order by quote_no DESC";

			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;

				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;
				}
				catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					e.printStackTrace();
				}
				System.out.println(" FINAL searchValue  "+searchValue);

				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0'),m.admin_referral_status from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status in('U','N','T') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by quote_no DESC";

				searchValue1=null;
				searchValue2=null;
				searchValue3=null;
			}
			ss=runner.multipleSelection(sql);
			System.out.println("   ss length   "+ss.length);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

public String[][] getViewQuotesApprovedForFreight(String loginIds,String searchOption,String searchValue,String productId)
{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[0];
		try
		{
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;

			getsTotal=new java.util.HashMap();
			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			System.out.println("  values length  "+valuess.length);
			ss=new String[0][0];
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0') from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status='A' and b.customer_id=a.customer_id and a.product_id='"+productId+"' order by quote_no DESC";
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0') from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status='A' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.product_id='"+productId+"' order by quote_no DESC";

			}
			else if("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0') from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F')  and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status='A' and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%')   and a.product_id='"+productId+"' order by quote_no DESC";

			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;

				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

				}catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}
				System.out.println(" FINAL searchValue  "+searchValue);

				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date,nvl(m.open_cover_no,'0') from position_master a,personal_info b,marine_data m,FREIGHT_POSITION_MASTER f where a.login_id in ("+loginAllIds+") and a.FREIGHT_STATUS in('F') and a.effective_date >= (select sysdate from dual) and a.status='Y' and m.application_no=a.application_no and f.quote_no=a.quote_no and f.status='A' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by quote_no DESC";

			searchValue1=null;
			searchValue2=null;
			searchValue3=null;

			}
			ss=runner.multipleSelection(sql);
			System.out.println("   ss length   "+ss.length);
		}
		catch(Exception e)
		{e.printStackTrace();}
		return ss;
	}
	public String getFriAppNo(String quoteNo)
	{
		String app = "";
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = quoteNo;
			sql = "select APPLICATION_ID from FREIGHT_POSITION_MASTER  where quote_no=?";
			app = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(app==null)
			app = "";
		return app;
	}

	public String[][] getFreightQuotes(String loginIds,String status,String refst,String productId)
	{
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String status123="";
		String status45="";
		String s="";
		if(status.equalsIgnoreCase("app"))
		{
            status123="'A'";
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
			status123="'U','T','N'";
		}
		else if(status.equalsIgnoreCase("ref"))
		{
			status123="'B'";
		}
		try
		{
			String  sql="select login_id from login_master where oa_code='"+getAgencyCode(loginIds)+"' or created_by='"+getAgencyCode(loginIds)+"' or login_id='"+loginIds+"'";
			valuess=runner.multipleSelection(sql);
			empByBroker=valuess;
			for(int i=0;i<valuess.length;i++)
			{
				loginids="'"+valuess[i][0]+"',"+loginids;
			}
			if(loginids.length()>0)
				loginids=loginids.substring(0,loginids.lastIndexOf(','));
			sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0'),a.FREIGHT_STATUS,a.remarks,md.ADMIN_REFERRAL_STATUS,a.FREIGHT_REMARKS,f.status,f.ALLOW_TO_GENERATE_POLICY_STS,nvl(md.premium,0),nvl(md.EXCESS_PREMIUM,0) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in("+status123+") and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
			System.out.println("Royal Test Freight Approve&unapprove in Broker side___________<>>>>>>>>>>>>>>"+sql);
            ss=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}

	public String[][] getFreightQuotesSearch(String loginIds,String status,String refst,String productId,String searchOption,String searchValue)
	{
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String status123="";
		String status45="";
		String s="";
		String args[] = new String[1];
		if(status.equalsIgnoreCase("app"))
		{
            status123="'A'";
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
			status123="'U','T','N'";
		}
		else if(status.equalsIgnoreCase("ref"))
		{
			status123="'B'";
		}
		try
		{
			args[0] = loginIds;
			String  sql="select login_id from personal_info where login_id in(select login_id from login_master where agency_code in(select agency_code from login_user_details where agency_code in (select agency_code from login_master where oa_code=(select oa_code from login_master where login_id=?)))) and login_id!='NONE' and application_id in ('2','5')";
			valuess = runner.multipleSelection(sql,args);
			empByBroker=valuess;
			for(int i=0;i<valuess.length;i++)
			{
				loginids="'"+valuess[i][0]+"',"+loginids;
			}
			if(loginids.length()>0)
				loginids=loginids.substring(0,loginids.lastIndexOf(','));
			//Search Start
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0'),a.FREIGHT_STATUS,a.remarks,nvl(md.ADMIN_REFERRAL_STATUS,''),nvl(a.FREIGHT_REMARKS,''),nvl(f.status,''),nvl(f.ALLOW_TO_GENERATE_POLICY_STS,''),nvl(md.premium,0),nvl(md.EXCESS_PREMIUM,0) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in("+status123+") and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
				System.out.println("Royal Test Freight Approve&unapprove in Broker side___________<>>>>>>>>>>>>>>"+sql);
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0'),a.FREIGHT_STATUS,a.remarks,nvl(md.ADMIN_REFERRAL_STATUS,''),nvl(a.FREIGHT_REMARKS,''),nvl(f.status,''),nvl(f.ALLOW_TO_GENERATE_POLICY_STS,''),nvl(md.premium,0),nvl(md.EXCESS_PREMIUM,0) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in("+status123+") and a.product_id='"+productId+"' and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.last_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
				System.out.println("Royal Test Freight Approve&unapprove in Broker side___________<>>>>>>>>>>>>>>"+sql);
			}
			else if("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0'),a.FREIGHT_STATUS,a.remarks,nvl(md.ADMIN_REFERRAL_STATUS,''),nvl(a.FREIGHT_REMARKS,''),nvl(f.status,''),nvl(f.ALLOW_TO_GENERATE_POLICY_STS,''),nvl(md.premium,0),nvl(md.EXCESS_PREMIUM,0) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in("+status123+") and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1="";
				String searchValue2="";
				String searchValue3="";

				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());

				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

				}catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0'),a.FREIGHT_STATUS,a.remarks,nvl(md.ADMIN_REFERRAL_STATUS,''),nvl(a.FREIGHT_REMARKS,''),nvl(f.status,''),nvl(f.ALLOW_TO_GENERATE_POLICY_STS,''),nvl(md.premium,0),nvl(md.EXCESS_PREMIUM,0) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.login_id in ("+loginids+") and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in("+status123+") and a.product_id='"+productId+"' and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
				System.out.println("Royal Test Freight Approve&unapprove in Broker side___________<>>>>>>>>>>>>>>"+sql);
				searchValue1="";
				searchValue2="";
				searchValue3="";
			}
            ss=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}
	public String[][] getFreightLogIds(String loginIds)
	{
		String[][] valuess = new String[0][0];
		String args[] = new String[1];
		String sql="";
		try
		{
			args[0] = loginIds;
			sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User')" +
					" and usertype!='User' and login_id not in('NONE','NAN')";
			valuess=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] getAReferal(String appNo)
	{
		
		String[][] valuess = new String[0][0];
		String  sql= ""; 
		String args[] = new String[1];
		
		try
		{
			args[0] = appNo;
			sql = "select a.ADMIN_REFERRAL_STATUS,b.remarks,a.remarks,a.REFERAL_STATUS from marine_data a,position_master b where a.application_no=b.application_no and b.application_no=?";
			valuess=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Exception in>>>>>>>>>>>>>>>>"+e.toString());
			e.printStackTrace();
		}
		return valuess;
	}

	public HashMap getReferralStatus(String qNos)
	{
		String[][] valuess=new String[0][0];
		HashMap result = new HashMap();
		String  sql= "";
		try
		{
			sql = "select b.quote_no,a.ADMIN_REFERRAL_STATUS,b.remarks,a.remarks,a.REFERAL_STATUS from marine_data a,position_master b where a.application_no=b.application_no and b.application_no in(select application_no from position_master where quote_no in("+qNos+"))";
			valuess = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in>>>>>>>>>>>>>>>>"+e.toString());
			e.printStackTrace();
		}
		if(valuess.length>0)
		{
			for(int v=0;v<valuess.length;v++)
			{
				StringTokenizer st = new StringTokenizer(qNos,",");
				while(st.hasMoreTokens())
				{
					if(valuess[v][0].equalsIgnoreCase(st.nextToken()))
					{
						result.put("q"+valuess[v][0],(valuess[v][2]==null?"":valuess[v][2]));
					}
				}
			}
		}
		return result;
	}

	public void updateFreightApprovedStatus(String[] fquotes,HashMap freUp)
	{
		String aqnos = "";
		String rqnos = "";
		String nqnos = "";
		String apnos = "";
		String rpnos = "";
		for(int f=0;f<fquotes.length;f++)
		{
			if(freUp.get("QA"+fquotes[f])!=null)
			{
				
				aqnos = aqnos+fquotes[f]+",";
			}
			else if(freUp.get("QR"+fquotes[f])!=null)
			{
			
				rqnos  = rqnos+fquotes[f]+",";
			}
			else if(freUp.get("QN"+fquotes[f])!=null)
			{
				
				nqnos  = nqnos+fquotes[f]+",";
			}
			else if(freUp.get("QS"+fquotes[f])!=null)
			{
				System.out.println("<br>status..Reject.."+freUp.get("QS"+fquotes[f]));
			}
			if(freUp.get("PA"+fquotes[f])!=null)
			{
				
				apnos = apnos+fquotes[f]+",";
			}
			else if(freUp.get("PR"+fquotes[f])!=null)
			{
				
				rpnos  = rpnos+fquotes[f]+",";
			}
			else if(freUp.get("PS"+fquotes[f])!=null)
			{
				System.out.println("<br>status..Reject.."+freUp.get("PS"+fquotes[f]));
			}
				//out.println("quotes..."+fquotes[f]);
		}
		String aqry = "";
		String rqry = "";
		String nqry = "";
		String apqry = "";
		String rpqry = "";
		String rqryP = "";
		if(aqnos.length()>0)
		{
			aqnos = aqnos.substring(0,(aqnos.length()-1));
			aqry = "update FREIGHT_POSITION_MASTER set STATUS='A' where QUOTE_NO in("+aqnos+")";
		}
		if(rqnos.length()>0)
		{
			rqnos = rqnos.substring(0,(rqnos.length()-1));
			rqry = "update FREIGHT_POSITION_MASTER set STATUS='R' where QUOTE_NO in("+rqnos+")";
			rqryP = "update POSITION_MASTER set STATUS='R' where QUOTE_NO in("+rqnos+")";
		}
		if(nqnos.length()>0)
		{
			nqnos = nqnos.substring(0,(nqnos.length()-1));
			nqry = "update FREIGHT_POSITION_MASTER set STATUS='N' where QUOTE_NO in("+nqnos+")";
		}
		if(apnos.length()>0)
		{
			apnos = apnos.substring(0,(apnos.length()-1));
			apqry = "update FREIGHT_POSITION_MASTER set ALLOW_TO_GENERATE_POLICY_STS='Y' where QUOTE_NO in("+apnos+")";
		}
		if(rpnos.length()>0)
		{
			rpnos = rpnos.substring(0,(rpnos.length()-1));
			rpqry = "update FREIGHT_POSITION_MASTER set ALLOW_TO_GENERATE_POLICY_STS='N' where QUOTE_NO in("+rpnos+")";
		}
		
		try
		{
			if(aqry.length()>0)
				runner.updation(aqry);
			if(rqry.length()>0)
				runner.updation(rqry);
			if(rqryP.length()>0)
				runner.updation(rqryP);
			if(nqry.length()>0)
				runner.updation(nqry);
			if(apqry.length()>0)
				runner.updation(apqry);
			if(rpqry.length()>0)
				runner.updation(rpqry);
		}
		catch(Exception e)
		{
			System.out.println("Exception in>>>>>>>>>>>>>>>>"+e.toString());
		}
	}

	public String getFreightName(String qno)
	{
		String sql = "";
		String result = "";
		try
		{
			sql = "select nvl((FIRST_NAME||' '||LAST_NAME),0) from PERSONAL_INFO where agency_code in (select agency_code from login_master where login_id in(select login_id from position_master where quote_no in("+qno+")))";
			result = runner.singleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public boolean getIsFreightUserQuoteNo(String qno)
	{
		 String mess = "";
		 String sql = "";
		 String args[] = new String[1];
		 args[0] = qno;
		 sql = "select freight_status from position_master where  quote_no=?";
		 mess = runner.singleSelection(sql,args);
		 boolean result1 = false;
		 mess=mess==null?"":mess;
		 if(mess.equalsIgnoreCase("F"))
		 {
			  result1 = true;
		 }
		 else
		 {
			result1 = false;
		}
		 return result1;
	}

	//Rajesh For Freight Forwarder - end
	public String[][] getViewQuotesNotApproved(String loginIds,String searchOption,String searchValue)
	{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[3];
		try
		{
			getsTotal=new java.util.HashMap();
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			ss=new String[0][0];
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id order by quote_no DESC";
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') order by quote_no DESC";
			}
			else if("quote_nos".equalsIgnoreCase(searchOption))
			{
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and (a.quote_no like '%"+searchValue+"%') order by quote_no DESC";
			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;

				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

				}catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}
				System.out.println(" FINAL searchValue  "+searchValue);

				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by quote_no DESC";

				searchValue1=null;
				searchValue2=null;
				searchValue3=null;

			}
			ss=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

	public java.util.HashMap getViewQuotes(String loginIds)
	{
		String args[] = new String[0];
		java.util.HashMap getsTotal=null;
		try
		{
			String[][] valuess=new String[0][0];
			String[][] ss=null;
			getsTotal=new java.util.HashMap();
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;

			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess = runner.multipleSelection(sql,args);
			getsTotal.put("total",""+valuess.length);
			getsTotal.put("getCus",valuess);
			for(int i=0;i<valuess.length;i++)
			{
				args = new String[1];
				args[0] = valuess[i][0];
				ss = new String[0][0];
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,b.last_name,a.login_id from position_master a,personal_info b where a.login_id=? and a.status='Y' and (a.remarks in('Referal','Normal')) and b.customer_id=a.customer_id order by quote_no";
				ss = runner.multipleSelection(sql,args);
				getsTotal.put("totsub"+i,""+ss.length);

				for(int j=0;j<ss.length;j++)
				{
					getsTotal.put("Quote_"+i+"_"+j,ss[j][0]);
					getsTotal.put("Id_"+i+"_"+j,ss[j][1]);
					getsTotal.put("Edate_"+i+"_"+j,ss[j][2]+"/"+ss[j][3]+"/"+ss[j][4]);
					getsTotal.put("Vdate_"+i+"_"+j,ss[j][5]+"/"+ss[j][6]+"/"+ss[j][7]);
					getsTotal.put("name_"+i+"_"+j,ss[j][8]+" "+ss[j][9]);
					getsTotal.put("Login_"+i+"_"+j,ss[j][10]);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getsTotal;
	}

	public java.util.HashMap getViewQuotes(String loginIds,String productId)
	{
		String args[] = new String[0];
		java.util.HashMap getsTotal=null;
		try
		{
			String[][] valuess=new String[0][0];
			String[][] ss=null;
			getsTotal=new java.util.HashMap();
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;

			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);
			getsTotal.put("total",""+valuess.length);
			getsTotal.put("getCus",valuess);
			for(int i=0;i<valuess.length;i++)
			{
				args = new String[2];
				ss=new String[0][0];
				System.out.println("   login id   "+valuess[i][0]);
				args[0] = valuess[i][0];
				args[1] = productId;
				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,b.last_name,a.login_id from position_master a,personal_info b where a.login_id=? and a.status='Y' and (a.remarks in('Referal','Normal')) and b.customer_id=a.customer_id and a.product_id=? order by quote_no";
				ss = runner.multipleSelection(sql,args);
				getsTotal.put("totsub"+i,""+ss.length);
				for(int j=0;j<ss.length;j++)
				{
					getsTotal.put("Quote_"+i+"_"+j,ss[j][0]);
					getsTotal.put("Id_"+i+"_"+j,ss[j][1]);
					getsTotal.put("Edate_"+i+"_"+j,ss[j][2]+"/"+ss[j][3]+"/"+ss[j][4]);
					getsTotal.put("Vdate_"+i+"_"+j,ss[j][5]+"/"+ss[j][6]+"/"+ss[j][7]);
					getsTotal.put("name_"+i+"_"+j,ss[j][8]+" "+ss[j][9]);
					getsTotal.put("Login_"+i+"_"+j,ss[j][10]);

				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getsTotal;
	}

	public String[][] getViewQuote(String loginId)
	{
		String[][] viewQuotes=new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = loginId;
			String qry="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,b.last_name from position_master a,personal_info b where a.login_id=? and a.status='Y' and b.customer_id=a.customer_id";
			viewQuotes=runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in VIEWQUOTE in dataCollection   "+e.toString());
			e.printStackTrace();
		}
		return viewQuotes;
	}

	public String validInteger(String value)
	{
		String returnval=null;
		try
		{
			System.out.println("--"+Integer.parseInt(value));
		}
		catch(Exception e)
		{
			return "Invalid";
		}
		return returnval;
	}


	public String validString(String value,boolean format)
	{
		String returnval=null;
		try
		{
			value=value.trim();
			String validChar=null;
			if(value.length()>0)
			{
				/*value=value.toLowerCase();
				validChar="+-abcdefghijklmnopqrstuvwxyz' ";
				if(format)
				validChar="1234567890";
				for(int i=0;i<value.length();i++)
				{
					//char c=c.charAt(i);
					if(validChar.indexOf(value.charAt(i))== -1)
					returnval="Invalid";
				}*/
			}
			else
				returnval="needed";
		}
		catch(Exception e)
		{
			return "needed";
		}
		return returnval;
	}

	public String validLength(String value,int len)
	{
		String returnval=null;
		try
		{
			value=value.trim();
			if(value.length()>=len)
			{
			}
			else
				returnval="needed";
		}
		catch(Exception e)
		{
			return "needed";
		}
		return returnval;
	}



	public String emailValidate(String mailid)
	{
		String returnval=null;

		try
		{
			mailid=mailid.trim();
			if(mailid.length()>0)
			{
				if(mailid.indexOf("@")==-1)
				{
					returnval="Invalid";
				}
				else if((mailid.substring(0,mailid.indexOf("@"))).length()<2 || (mailid.substring(mailid.indexOf("@") + 1)).length()<7  || (mailid.substring(mailid.indexOf("@") + 1)).indexOf(".") == -1)
				{
					returnval="Invalid";
				}
			}
			else
					returnval="needed";
		}
		catch(Exception e)
		{
			returnval="needed";
		}
		return returnval;

	}


	public String checkDate(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);

		java.util.Date date = df.parse(strDate, pos);

                // Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			System.out.println("Error: " + pos.getIndex());

			if (date == null) {
				System.out.println("Date is null");
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				//System.out.println("Error index: " + pos.getErrorIndex());
				return "Invalid";
			}
				return "Invalid";
		}
		return returnVal;
	}
	public String checkDate1(String strDate)
	{
		String returnVal=null;
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("yyyy-mm-dd");
		df.setLenient(false);
		java.text.ParsePosition pos = new java.text.ParsePosition(0);

		java.util.Date date = df.parse(strDate, pos);

                // Check all possible things that signal a parsing error
		if ((date == null) || (pos.getErrorIndex() != -1)) {
			System.out.println("Error: " + pos.getIndex());

			if (date == null) {
				System.out.println("Date is null");
				return "Invalid";
			}
			if (pos.getErrorIndex() != -1) {
				//System.out.println("Error index: " + pos.getErrorIndex());
				return "Invalid";
			}
				return "Invalid";
		}
		return returnVal;
	}

	public String[][] getViewQuotes123(String loginIds,String status,String refst,String productId,String type,String openCoverNo,String rsaissuer) // ReferralDispayPages. information based on opencoverno restriction.
	{
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String status123="";
		String status45="";
		String s="";
		String multiLoginCondition = "";
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' and (a.CERTIFICATE_NO is null and a.CERTIFICATE_DATE is null) ";
		else
			syntax = " ";
		
		if(status.equalsIgnoreCase("app"))
		{
	        status123="Admin";
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
			status123="Referal";
		}
		if(status.equalsIgnoreCase("app"))
		{
		    status45="Admin";
			s=refst;
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
		    status45="Admin','Normal','Referal";
			s="Y";
		}
		try
		{
			if(rsaissuer==null)
			{
				if(!type.equalsIgnoreCase("Customer"))
				{
					String  sql="select login_id from login_master where oa_code='"+getAgencyCode(loginIds)+"' or created_by='"+getAgencyCode(loginIds)+"' or login_id='"+loginIds+"'";
					valuess=runner.multipleSelection(sql);
					empByBroker=valuess;
					for(int i=0;i<valuess.length;i++)
					{
						loginids="'"+valuess[i][0]+"',"+loginids;
					}
					if(loginids.length()>0)
						loginids=loginids.substring(0,loginids.lastIndexOf(','));
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) "+syntax+" and md.application_no=a.application_no and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='1' order by a.quote_no desc";
					ss=runner.multipleSelection(sql);
				}
				else if(type.equalsIgnoreCase("Customer"))
				{
					if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
						multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
					}
					else{
						multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
					}
					
					String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
					// Existing 	
					//String  condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";

					String sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
					ss=runner.multipleSelection(sql);
				}
			}
			else
			{
				String sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) "+syntax+" and md.application_no=a.application_no and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
					ss=runner.multipleSelection(sql);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}
		
	public String  getAgencyCode(String logpersonId)
	{
		String agencyCode="";
		String sql="";
		String args[] = new String[1];
		try
		{
			args[0] = logpersonId;
			sql = "select agency_code from login_master where login_id=?";
			agencyCode = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return agencyCode;
	}

	public String[][] getMissinginfo1(String qouteId)
	{
		String sql = "";
		String[][] viewQuotes = new String[0][0];
		String args[] = new String[2];
		try
		{
			args[0] = qouteId;
			args[1] = qouteId;

			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,b.first_name,b.last_name,c.premium from position_master a,personal_info b,marine_data c where a.quote_no=? and a.status='Y' and b.customer_id=a.customer_id and c.application_no=(select application_no from position_master where quote_no=?)";
				viewQuotes = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return viewQuotes;
	}

	public String[][] getLogIds(final String loginIds,final String userType)
	{
		String[][] valuess=new String[0][0];
		String  sql = "";
		String args[] = new String[0];
		try
		{
			if("Customer".equalsIgnoreCase(userType) || "Freight".equalsIgnoreCase(userType) ){
				args = new String[1];
				sql = getMultiLoginListQry(loginIds,userType);
				args[0] = loginIds;
				if(sql.length() > 0 ){
					valuess = runner.multipleSelection(sql, args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='D'", new String[]{loginIds});
			}
			else
			{
				args = new String[3];
				args[0] = loginIds;
				args[1] = loginIds;
				args[2] = loginIds;
				sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";
				valuess = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String getBrokerName(String appno)
	{
		String args[] = new String[1];
		String sql = "";
		String bName = "";
		try
		{
			sql = "select first_name from personal_info where agency_code=(select oa_code from personal_info where login_id=(select login_id from position_master where application_no=?) and application_id in(2,3,4))";
			args[0] = appno;
			bName = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return bName;
	}

	public String[][] getAdminReferals()
	{
		String[][] admins = null;
		String sql = "";
		try
		{
			sql = "select detail_name from constant_detail where category_id=(select category_id from constant_master where category_name='Admin Referral')";
			admins = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return admins;
	}

	public String[][] getNewLogIds(String loginIds)
	{
		String[][] valuess=new String[0][0];
		String sql = "";
		String args[] = new String[1];
		try
		{
			//sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and USERTYPE!='Freight' and USERTYPE!='Customer'";			
			sql = getMultiLoginListQry(loginIds,"Freight");
			args[0] = loginIds;
			if(sql.length() > 0 ){
				valuess = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] getNewLogIdswihtFreight(String loginIds)
	{
		String[][] valuess=new String[0][0];
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginIds;
			sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and login_id not in('NONE','NAN')" ;
			valuess=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] getNewLogIds123(String loginIds)
	{
		String[][] valuess=new String[0][0];
		String sql="select login_id from login_master where oa_code=(select oa_code from login_master where login_id='"+loginIds+"' and usertype!='Customer'  and usertype!='User' and usertype!='Freight')";
		valuess=runner.multipleSelection(sql);
		return valuess;
	}
	
	public String[][] getViewQuotesNotApprovedExDate(String loginIds,String productId)
	{
		String loginAllIds = "";
		String[][] valuess = new String[0][0];
		String[][] ss = new String[0][0] ;
		try
		{
			String  sql="";
			 sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id='"+loginIds+"') or created_by=(select agency_code from login_master where login_id='"+loginIds+"') or login_id='"+loginIds+"'";
				valuess = runner.multipleSelection(sql);
				for(int i=0;i<valuess.length;i++)
				{
					loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
				}
				if(loginAllIds.length()>0)
					loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
				ss=new String[0][0];
				sql="";
				sql	=	"select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE a.login_id in ("+loginAllIds+") and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id='"+productId+"' and a.status='Y'";
				ss = runner.multipleSelection(sql);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

	// QuoteRegister Lapsed Quote for direct Client - July 15th
	public String[][] getViewQuotesNotApprovedExDate(String loginIds,String productId,String userTypes,String rsaissuer,String openCoverNo) // ViewQuote_B2B.jsp Information based on opencoverno restriction 
	{
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String syntax = " ";
		String args[] = new String[0];
		String multiLoginCondition = "";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' ";
		else
			syntax = " ";

		try
		{
			String  sql="";
			if(rsaissuer==null)
			{
				if(!userTypes.equalsIgnoreCase("Customer"))
				{
					args = new String[3];
					args[0] = loginIds;
					args[1] = loginIds;
					args[2] = loginIds;
					
					sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
					valuess = runner.multipleSelection(sql,args);

					for(int i=0;i<valuess.length;i++)
					{
						loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
					}
					if(loginAllIds.length()>0)
						loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
					ss=new String[0][0];
					sql="";
					sql	=	"select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE a.login_id in ("+loginAllIds+") "+syntax+" and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id='"+productId+"' and a.status='Y' and a.APPLICATION_ID='1'";
				}
				else if(userTypes.equalsIgnoreCase("Customer"))
				{
					if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
						multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
					}
					else{
						multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
					}
					String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
					//Existing one
					//String  condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";
					sql = "";
					sql	=	"select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE "+condition+" "+syntax+" and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id='"+productId+"' and a.status='Y'";
				}
			}
			else
			{
					sql="";
					sql	=	"select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name) from position_master a, personal_info b WHERE a.login_id in ('"+loginIds+"') "+syntax+" and a.effective_date < (select sysdate from dual) and a.customer_id = b.customer_id and a.product_id='"+productId+"' and a.status='Y' and a.APPLICATION_ID='"+rsaissuer+"'";
			}
			ss=runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getCreatedOpenCovers(String loginIds)
	{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[0];
		try
		{
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;

			getsTotal=new java.util.HashMap();
			String  sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);

			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			ss=new String[0][0];
			
			sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocpm.proposal_no,ocm.broker_id,ocm.broker_user_id from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where lower(ocm.broker_id)=lower("+loginAllIds+") and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocms.effective_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getCreatedOpenCoversSearch(String loginIds,String searchOption,String searchValue,String findtype, String branchCode, String issuer)
	{
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String balanceAmount = "trim(to_char(nvl(ocm.CROSS_VOYAGE_TURNOVER,0)-nvl(ocm.UTILIZED_AMOUNT,0),'999G999G999G999G999G999G999G999G990'))";
		String sql = "";
		try
		{
			/*if((issuer!=null && !"".equals(issuer)) && "0".equals(loginIds)){
				String[][] Broker = new ProductSelection().getBrokersList(branchCode, issuer);
				if(Broker!=null && Broker.length>0){
					for(String[] str :Broker){
						loginIds=loginIds+"','"+str[0];
					}
				}
			}*/
		/*	try
			{
				args[0] = loginIds;
				String sql1 = "select usertype from login_master where login_id=?";
				findtype = runner.singleSelection(sql1,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				args[0] = loginIds;
				sql="select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker'";
				valuess = runner.multipleSelection(sql,args);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));*/
			ss = new String[0][0];
			String condition="";
			
			/*if(findtype.equalsIgnoreCase("Customer")) June 09 - Multi Login Customer
			{
				condition="pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer'))";
				//condition="pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer')) and ocm.BROKER_USER_ID=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer')";
			}
			else if(findtype.equalsIgnoreCase("User"))*/
			if(findtype.equalsIgnoreCase("User") || findtype.equalsIgnoreCase("Customer") )
			{
				String args[] = new String[1];
				String openCoverNos = "";
				String openNos[][] = new String[0][0];
				try
				{
					args[0] = loginIds;
					String  opensql = "select nvl(open_cover_no,'0') from LOGIN_USER_DETAILS where login_id=? and  product_id='11'";
					openNos=runner.multipleSelection(opensql,args);
					for(int i=0;i<openNos.length;i++)
					{
						openCoverNos=openCoverNos+openNos[i][0];
					}
					if(openCoverNos.length()>0)
						openCoverNos=openCoverNos.replaceAll(",", "','");
					//condition = "ocpm.open_cover_no in('"+openCoverNos+"')";
					condition = "NVL (OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO) in('"+openCoverNos+"')";
				}
				catch (Exception e)
				{
					condition="ocm.broker_id in ('"+loginIds+"')";
					e.printStackTrace();
				}
			}
			else if(findtype.equalsIgnoreCase("Broker")||findtype.equalsIgnoreCase("Admin"))
			{
				condition="ocm.broker_id in ('"+loginIds+"')";
			}
			else
			{
				/*String openCoverNos = "";
				String openNos[][] = new String[0][0];
				try
				{
					String args[] = new String[4];
					args[0] = findtype;
					args[1] = findtype;
					args[2] = loginIds;
					args[3] = loginIds;
					String  opensql = "select OPEN_COVER_NO from LOGIN_RSAUSER_DETAILS where login_id=? and product_id='11' and START_DATE<=(select to_char(sysdate,'dd-MM-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-MM-yyyy') from dual) and amend_id=(select max(amend_id) from LOGIN_RSAUSER_DETAILS where login_id=? and product_id='11' and START_DATE<=(select to_char(sysdate,'dd-MM-yyyy') from dual) and END_DATE>=(select to_char(sysdate,'dd-MM-yyyy') from dual) and broker_code=(select oa_code from login_master where login_id=?)) and broker_code=(select oa_code from login_master where login_id=?) and status='Y'";
					openNos=runner.multipleSelection(opensql,args);
					for(int i=0;i<openNos.length;i++)
					{
						openCoverNos=openCoverNos+openNos[i][0]+",";
					}
					if(openCoverNos.length()>0)
						openCoverNos=openCoverNos.substring(0,openCoverNos.lastIndexOf(','));
					else openCoverNos="''";
					condition = "ocpm.open_cover_no in("+openCoverNos+")";
				}
				catch (Exception e)
				{
					condition="ocm.broker_id in ('"+loginIds+"')";
					e.printStackTrace();
				}*/
				condition="ocm.broker_id in ('"+loginIds+"')";
			}
			
			condition+=" AND OCPM.AMEND_ID=(SELECT NVL(MAX(AMEND_ID),0) FROM OPEN_COVER_POSITION_MASTER OC WHERE NVL(OC.ORIGINAL_POLICY_NO,OC.OPEN_COVER_NO)=NVL(OCPM.ORIGINAL_POLICY_NO,OCPM.OPEN_COVER_NO) AND STATUS='P') ";
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,NVL(OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO),"+balanceAmount+" from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 >= to_date(sysdate,'dd-MM-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,NVL(OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO),"+balanceAmount+" from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and (lower(trim(pi.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(pi.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 >= to_date(sysdate,'dd-MM-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}
			else if("opencover_nos".equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,NVL(OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO),"+balanceAmount+" from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and (ocpm.open_cover_no like '%"+searchValue+"%') and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 >= to_date(sysdate,'dd-MM-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;
				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;
				}catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}
			sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,NVL(OCPM.ORIGINAL_POLICY_NO, OCPM.OPEN_COVER_NO),"+balanceAmount+" from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and to_char(ocpm.inception_date,'dd/MM/YYYY')='"+searchValue+"' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 >= to_date(sysdate,'dd-MM-YYYY') and (ocpm.admin_status is null or ocpm.admin_status='Y') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
				searchValue1=null;
				searchValue2=null;
				searchValue3=null;
			}
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("No of Customer MOC ----------------"+sql);
		return ss;
	}

	/*** Lapsed OpenCovers ***/
	public String[][] getLapsedOpenCoversSearch(String loginIds,String searchOption,String searchValue)
	{
		java.util.HashMap getsTotal=null;
		String sql="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[0];
		try
		{
			args = new String[1];
			String findtype="";
			args[0] = loginIds;

			String  sql1="select usertype from login_master where login_id=?";
			findtype=runner.singleSelection(sql1,args);

			/*getsTotal=new java.util.HashMap();
			args[0] = loginIds;
			String  sql="select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?) and usertype='Broker'";
			valuess=runner.multipleSelection(sql,args);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			if(loginAllIds.length()>0)
				loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));*/
			ss=new String[0][0];
			String condition="";
			/*if(findtype.equalsIgnoreCase("Customer"))
			{
				condition="pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer'))";
				//condition="pi.customer_id=(select distinct customer_id from login_user_details where agency_code=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer')) and ocm.BROKER_USER_ID=(select agency_code from login_master where login_id='"+loginIds+"' and usertype='Customer')";
			}
			else if(findtype.equalsIgnoreCase("User"))*/
			if(findtype.equalsIgnoreCase("User") || findtype.equalsIgnoreCase("Customer") )
			{
				String openCoverNos = "";
				String openNos[][] = new String[0][0];
				try
				{
					String  opensql = "select nvl(open_cover_no,'0') from LOGIN_USER_DETAILS where login_id='"+loginIds+"' and  product_id='11'";
					openNos=runner.multipleSelection(opensql);
					for(int i=0;i<openNos.length;i++)
					{
						openCoverNos=openCoverNos+openNos[i][0];
					}
					if(openCoverNos.length()>0)
						openCoverNos=openCoverNos.replaceAll(",", "','");
					condition = "ocpm.open_cover_no in('"+openCoverNos+"')";
				}
				catch (Exception e)
				{
					condition="ocm.broker_id in ('"+loginIds+"')";
					e.printStackTrace();
				}
			}
			else
			{
				condition="ocm.broker_id in ('"+loginIds+"')";
			}
			if("select".equalsIgnoreCase(searchOption) || ("").equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,ocpm.admin_status,(trunc(sysdate)-trunc(ocpm.expiry_date)) expiry_days from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status in ('P','R') and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and (to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 < to_date(sysdate,'dd-MM-YYYY') or ocpm.admin_status='N') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}
			else if("FIRST_NAME".equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,ocpm.admin_status from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and (lower(trim(pi.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(pi.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and (to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 < to_date(sysdate,'dd-MM-YYYY') or ocpm.admin_status='N') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}
			else if("opencover_nos".equalsIgnoreCase(searchOption))
			{
				sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,ocpm.admin_status from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and (ocpm.open_cover_no like '%"+searchValue+"%') and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and (to_date(ocpm.expiry_date,'dd-MM-YYYY')+30 < to_date(sysdate,'dd-MM-YYYY') or ocpm.admin_status='N') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			}
			else if("DateWise".equalsIgnoreCase(searchOption))
			{
				String searchValue1=null;
				String searchValue2=null;
				String searchValue3=null;
				searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
				searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
				searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
				try
				{
					if(Integer.parseInt(searchValue2)<10)
						searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
					else
						searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;
				}catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
				}
			System.out.println(" FINAL searchValue  "+searchValue);
			sql="select distinct(ocpm.open_cover_no),ocm.customer_id,to_char(ocpm.inception_date,'dd')as days,to_char(ocpm.inception_date,'MM')as months,to_char(ocpm.inception_date,'YYYY')as years,to_char(ocpm.expiry_date,'dd')as days,to_char(ocpm.expiry_date,'MM')as months,to_char(ocpm.expiry_date,'YYYY')as years,nvl(pi.COMPANY_NAME,pi.first_name),ocm.broker_id,pi.company_name,ocpm.effective_date,ocpm.proposal_no,ocm.broker_user_id,ocm.MISSIPPI_OPENCOVER_NO,ocpm.admin_status from open_cover_position_master ocpm,open_cover_master ocm,personal_info pi where "+condition+" and ocm.status='Y' and ocpm.status='P' and ocm.proposal_no=ocpm.proposal_no and pi.customer_id=ocm.customer_id and pi.status='Y' and to_char(ocpm.inception_date,'dd/MM/YYYY')='"+searchValue+"' and ocm.amend_id||'-'||ocm.proposal_no in (select max(ocms.amend_id)||'-'||ocms.proposal_no from open_cover_master ocms,open_cover_position_master ocpms where ocpms.proposal_no=ocms.proposal_no and (to_date(ocpm.expiry_date,'dd-MM-YYYY') <= to_date(sysdate,'dd-MM-YYYY') or ocpm.admin_status='N') group by ocms.proposal_no) order by ocpm.open_cover_no  DESC";
			searchValue1=null;
			searchValue2=null;
			searchValue3=null;
			}
			ss=runner.multipleSelection(sql);
		}catch(Exception e)
		{
		}
		return ss;
	}

	public String[][] getLapsedDetails(String loginId)
	{
		String[][] viewQuotes = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = loginId;
			String qry="select a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.first_name,b.company_name),b.last_name from position_master a, personal_info b WHERE a.quote_no=? and a.customer_id = b.customer_id";
			viewQuotes = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in VIEWQUOTE in dataCollection   "+e.toString());
			e.printStackTrace();
		}
		return viewQuotes;
	}

	public String[][] getLapsedStatus(String branch)
	{
		String[][] LapsedStatus	=	new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			args[0] = branch;
			args[1] = branch;
			sql = "select category_detail_id,detail_name from constant_detail where branch_code=? and category_id = (select category_id from constant_master where category_name = 'LAPSED_QUOTE_REASON' and branch_code=?)";
			LapsedStatus = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return LapsedStatus;
	}

	public String getLapsedStatusRemarks(String Quote_No)
	{
		String sql="";
		String LAPSED_REMARKS = "";
		String args[] = new String [1];
		try
		{
			args[0] = Quote_No;
			sql = "select LAPSED_REMARKS from position_master where Quote_No=?";
			LAPSED_REMARKS = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return LAPSED_REMARKS;
	}

	public HashMap mailSendStatus(String[] fquotes,HashMap freUp,HashMap MailInformation)
	{
		java.util.HashMap finalDetails = new java.util.HashMap();
		String quoteNo="";
		String quoteAcc="";
		String quoteRej="";
		String quoteClear="";
		String policyAcc="";
		String policyRej="";
		String NewquoteClear="";
		
		String NewQuAcc="", NewQuRej="",NewPolAcc="",NewPolRej="";
		int count=0;
		
		String customer_name="";
		for(int f=0;f<fquotes.length;f++)
		{
				quoteAcc="";quoteRej="";policyAcc="";policyRej="";
				NewQuAcc="";NewQuRej="";NewPolAcc="";NewPolRej="";
				customer_name="";NewquoteClear="";quoteClear="";
				quoteNo = fquotes[f];
				if(MailInformation.get("qac"+fquotes[f])!=null)
				{
					quoteAcc = (String)MailInformation.get("qac"+fquotes[f]);
			
				}
				if(MailInformation.get("qrj"+fquotes[f])!=null)
				{
					quoteRej = (String)MailInformation.get("qrj"+fquotes[f]);
				
				}
				if(MailInformation.get("qss"+fquotes[f])!=null)
				{
					quoteClear = (String)MailInformation.get("qss"+fquotes[f]);
				
				}
				if(MailInformation.get("pac"+fquotes[f])!=null)
				{
					policyAcc = (String)MailInformation.get("pac"+fquotes[f]);
				
				}
				if(MailInformation.get("prj"+fquotes[f])!=null)
				{
					policyRej = (String)MailInformation.get("prj"+fquotes[f]);
				
				}
				if(MailInformation.get("cust"+fquotes[f])!=null)
				{
					customer_name = (String)MailInformation.get("cust"+fquotes[f]);
				
				}

				if(freUp.get("QA"+fquotes[f])!=null)
					{
						NewQuAcc = (String)freUp.get("QA"+fquotes[f]);
					}
				 if (freUp.get("QR"+fquotes[f])!=null)
					{
					NewQuRej = (String)freUp.get("QR"+fquotes[f]);
					}

					if (freUp.get("QN"+fquotes[f])!=null)
					{
					NewquoteClear = (String)freUp.get("QN"+fquotes[f]);
					}

				if(freUp.get("PA"+fquotes[f])!=null)
					{
						NewPolAcc = (String)freUp.get("PA"+fquotes[f]);
					}
				if (freUp.get("PR"+fquotes[f])!=null)
					{
					NewPolRej = (String)freUp.get("PR"+fquotes[f]);
					}

					if(quoteAcc.equalsIgnoreCase(NewQuAcc)&& quoteRej.equalsIgnoreCase(NewQuRej) && policyAcc.equalsIgnoreCase(NewPolAcc) && policyRej.equalsIgnoreCase(NewPolRej) && quoteClear.equalsIgnoreCase(NewquoteClear))
					{
							finalDetails.put("qno"+count,quoteNo);
							finalDetails.put("cust"+quoteNo,customer_name);
							if(NewQuAcc.equalsIgnoreCase("Y"))
								finalDetails.put("QuoteAcc"+quoteNo,NewQuAcc);
							else if(NewQuRej.equalsIgnoreCase("R"))
								finalDetails.put("QuoteRej"+quoteNo,NewQuRej);
							else if(NewQuRej.equalsIgnoreCase("N"))
								finalDetails.put("Quoteclear"+quoteNo,NewquoteClear);
							if(NewPolAcc.equalsIgnoreCase("Y"))
								finalDetails.put("PolAcc"+quoteNo, NewPolAcc);
							else if(NewPolRej.equalsIgnoreCase("N"))
								finalDetails.put("PolRej"+quoteNo, NewPolRej);
							count++;
					}
					else if("N".equalsIgnoreCase(NewquoteClear))
					{
							
							/*System.out.println(""+quoteNo+"--"+NewQuAcc+"--"+NewQuRej+"--"+NewPolAcc+"--"+NewPolRej);
							finalDetails.put("qno"+count,quoteNo);
							finalDetails.put("cust"+quoteNo,customer_name);
							if(NewQuAcc.equalsIgnoreCase("Y"))
								finalDetails.put("QuoteAcc"+quoteNo,NewQuAcc);
							else if(NewQuRej.equalsIgnoreCase("R"))
								finalDetails.put("QuoteRej"+quoteNo,NewQuRej);
							else if(NewQuRej.equalsIgnoreCase("N"))
								finalDetails.put("Quoteclear"+quoteNo,NewquoteClear);
							if(NewPolAcc.equalsIgnoreCase("Y"))
								finalDetails.put("PolAcc"+quoteNo, NewPolAcc);
							else if(NewPolRej.equalsIgnoreCase("N"))
								finalDetails.put("PolRej"+quoteNo, NewPolRej);
							count++;*/
						}
						else
						{
							
							finalDetails.put("qno"+count,quoteNo);
							finalDetails.put("cust"+quoteNo,customer_name);
							if(NewQuAcc.equalsIgnoreCase("Y"))
								finalDetails.put("QuoteAcc"+quoteNo,NewQuAcc);
							else if(NewQuRej.equalsIgnoreCase("R"))
								finalDetails.put("QuoteRej"+quoteNo,NewQuRej);
							else if(NewQuRej.equalsIgnoreCase("N"))
								finalDetails.put("Quoteclear"+quoteNo,NewquoteClear);
							if(NewPolAcc.equalsIgnoreCase("Y"))
								finalDetails.put("PolAcc"+quoteNo, NewPolAcc);
							else if(NewPolRej.equalsIgnoreCase("N"))
								finalDetails.put("PolRej"+quoteNo, NewPolRej);
							count++;
						}

		}
		finalDetails.put("count",""+count);
		return finalDetails;
	}
	public String[][] getBusinessOpenNo(String appNo)
	{
		String[][] businessOpen	=	new String[0][0];
		String args[] = new String[3];
		String sql = "";
		try
		{
			args[0] = appNo;
			args[1] = appNo;
			args[2] = appNo;
			sql = "select ocpm.OPEN_COVER_NO,ocm.BUSINESS_TYPE,ocm.MISSIPPI_OPENCOVER_NO from OPEN_COVER_MASTER ocm, OPEN_COVER_POSITION_MASTER ocpm where ocpm.OPEN_COVER_NO=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?) and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and  ocm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_Position_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?))) and ocpm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_POSITION_MASTER where open_cover_no=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?))";
			businessOpen = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Before Position Master appNo "+appNo);
		System.out.println("Before Position Master OpenCover "+businessOpen[0][0]+" " +businessOpen[0][1]);
		return businessOpen;
	}

	public void updateBusinessOpenCoverNo()
	{
		String result[][] = new String[0][0];
		String result1[][] = new String[0][0];
		String sql = "select m.APPLICATION_NO,m.OPEN_COVER_NO from marine_data m,POSITION_MASTER p where m.APPLICATION_NO=p.APPLICATION_NO and p.product_id=11 and m.OPEN_COVER_NO is not null";
		result = runner.multipleSelection(sql);
		String appNo = "";
		String args[] = new String[0];
		for(int i=0;i<result.length;i++)
		{
			args = new String[3];
			args[0] = appNo;
			args[1] = appNo;
			args[2] = appNo;
			appNo = result[i][0]!=null?result[i][0]:"0";
			String sql1 = "select ocpm.OPEN_COVER_NO,ocm.BUSINESS_TYPE from OPEN_COVER_MASTER ocm, OPEN_COVER_POSITION_MASTER ocpm where ocpm.OPEN_COVER_NO=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?) and ocm.PROPOSAL_NO=ocpm.PROPOSAL_NO and  ocm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_MASTER where PROPOSAL_NO=(select PROPOSAL_NO from OPEN_COVER_Position_MASTER where OPEN_COVER_NO=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?))) and ocpm.AMEND_ID=(select max(AMEND_ID) from OPEN_COVER_POSITION_MASTER where open_cover_no=(select OPEN_COVER_NO from marine_data where APPLICATION_NO=?))";
			result1 = runner.multipleSelection(sql1,args);
			String openCoverNo = "";
			String businessType = "";
			if(result1.length>0)
			{
				openCoverNo = result1[0][0]!=null?result1[0][0]:"0";
				businessType = result1[0][1]!=null?result1[0][1]:"1";
			}
			else
			{
				openCoverNo = "0";
				businessType = "1";
			}
			args = new String[3];
			args[0] = openCoverNo;
			args[1] = businessType;
			args[2] = appNo;

			String updateSql = "update POSITION_MASTER set OPEN_COVER_NO = ? , BUSINESS_TYPE=? where APPLICATION_NO=?";
			runner.multipleUpdation(updateSql,args);
		}
	}

	//for direct Client - July 15th
	public String[][] getSearchRefViewQuotes(String loginIds,String status,String refst,String productId,String searchBy,String searchValue, String type,String openCoverNo,String rsaissuer) // Information Based on opencoverno restriction
	{
		
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String status123="";
		String status45="";
		String s="";
		String multiLoginCondition = "";
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' ";
		else
			syntax = " ";

		if(status.equalsIgnoreCase("app"))
		{
	        status123="Admin";
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
			status123="Referal";
		}
		if(status.equalsIgnoreCase("app"))
		{
		    status45="Admin";
			s=refst;
		}
		else if(status.equalsIgnoreCase("unapp"))
		{
		    status45="Admin','Normal";
			s="Y";
		}
		String  sql = "";
		try
		{
			if(rsaissuer==null)
			{
				sql="select login_id from login_master where oa_code='"+getAgencyCode(loginIds)+"' or created_by='"+getAgencyCode(loginIds)+"' or login_id='"+loginIds+"'";
				valuess=runner.multipleSelection(sql);
				empByBroker=valuess;
				for(int i=0;i<valuess.length;i++)
				{
					loginids="'"+valuess[i][0]+"',"+loginids;
				}
				if(loginids.length()>0)
					loginids=loginids.substring(0,loginids.lastIndexOf(','));
				
				if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
					multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
				}
				else{
					multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
				}
				
				String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
				// Existing One
				//String condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";

				if("select".equalsIgnoreCase(searchBy) || ("").equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
					}
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where  "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
					}
				}
				else if("quote_nos".equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where  "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
					}
				}
				else if("DateWise".equalsIgnoreCase(searchBy))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

					}catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where  "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.effective_date >= (select sysdate from dual) order by a.quote_no desc";
					}
					searchValue1=null;
					searchValue2=null;
					searchValue3=null;
				}
			}
			else
			{
				
				if("select".equalsIgnoreCase(searchBy) || ("").equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("quote_nos".equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("DateWise".equalsIgnoreCase(searchBy))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

					}catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
					searchValue1=null;
					searchValue2=null;
					searchValue3=null;
				}else if("OtherUsers".equalsIgnoreCase(searchBy)){
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0') from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('"+status123+"') and md.ADMIN_REFERRAL_STATUS='"+refst+"') or (a.REMARKS in ('"+status45+"','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='"+s+"')) and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.effective_date >= (select sysdate from dual) and a.APPLICATION_ID not in ('"+rsaissuer+"','1') order by a.quote_no desc";
				}
			}
			ss=runner.multipleSelection(sql);
		}catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}

	//for direct Client - July 15th
	public String[][] getRejectedViewQuotes123(String loginIds,String productId,String type,String openCoverNo,String rsaissuer) //Information based on opencoverno restriction
	{
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String multiLoginCondition = "";
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' ";
		else
			syntax = " ";

		try
		{
			if(rsaissuer==null)
			{
				if(!type.equalsIgnoreCase("Customer"))
				{
					String  sql="select login_id from login_master where oa_code='"+getAgencyCode(loginIds)+"' or created_by='"+getAgencyCode(loginIds)+"' or login_id='"+loginIds+"'";
					valuess=runner.multipleSelection(sql);
					empByBroker=valuess;
					for(int i=0;i<valuess.length;i++)
					{
						loginids="'"+valuess[i][0]+"',"+loginids;
					}
					if(loginids.length()>0)
						loginids=loginids.substring(0,loginids.lastIndexOf(','));
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') "+syntax+" and md.application_no=a.application_no and a.product_id='"+productId+"' and a.APPLICATION_ID='1' order by a.quote_no desc";
					ss=runner.multipleSelection(sql);
				}
				else if(type.equalsIgnoreCase("Customer"))
				{
					if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
						multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
					}
					else{
						multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
					}
					String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";
					
					// Existing One
					//String condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";

					String sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no "+syntax+" and a.product_id='"+productId+"'";
					ss=runner.multipleSelection(sql);
				}
			}
			else
			{
				String sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') "+syntax+" and md.application_no=a.application_no and a.product_id='"+productId+"' and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				ss=runner.multipleSelection(sql);
			}
		}catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}
	//for direct Client - July 15th
	public String[][] getSearchRefRejectedViewQuotes(String loginIds,String productId,String searchBy,String searchValue,String type,String openCoverNo,String rsaissuer) //openCoverNo Restriction
	{
		String loginids="";
		String[][] valuess=new String[0][0];
		String[][] ss=new String[0][0];
		String multiLoginCondition = "";
		String syntax = " ";
		if(productId.equalsIgnoreCase("11"))
			syntax = " and a.open_cover_no='"+openCoverNo+"' ";
		else
			syntax = " ";
		String  sql = "";
		try
		{
			if(rsaissuer==null)
			{
				sql="select login_id from login_master where oa_code='"+getAgencyCode(loginIds)+"' or created_by='"+getAgencyCode(loginIds)+"' or login_id='"+loginIds+"'";
				valuess=runner.multipleSelection(sql);
				empByBroker=valuess;
				for(int i=0;i<valuess.length;i++)
				{
					loginids="'"+valuess[i][0]+"',"+loginids;
				}
				if(loginids.length()>0)
					loginids=loginids.substring(0,loginids.lastIndexOf(','));
				if(getMasterLoginChk(loginIds,"Customer")){ // June - 09 Multi Login Condition For Customer
					multiLoginCondition = "select customer_id from personal_info where login_id in(select login_id from login_master where fd_code=(select fd_code from login_master where login_id='"+loginIds+"')) ";
				}
				else{
					multiLoginCondition = " select distinct customer_id from login_user_details  where login_id='"+loginIds+"' ";
				}
				String condition="((a.customer_id in ("+multiLoginCondition+")  or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"'))))";

				//Existing One
				//String condition="((a.customer_id in (select distinct customer_id from login_user_details  where login_id='"+loginIds+"') or a.login_id='"+loginIds+"') and (a.login_id='"+loginIds+"' or a.login_id in(select login_id from login_master where oa_code=(select oa_Code from login_master where login_id='"+loginIds+"') and usertype not in('Customer'))))";

				if("select".equalsIgnoreCase(searchBy) || ("").equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"'  order by a.quote_no desc";
					}
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%')  order by a.quote_no desc";
					}
				}
				else if("quote_nos".equalsIgnoreCase(searchBy))
				{
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') order by a.quote_no desc";
					}
				}
				else if("DateWise".equalsIgnoreCase(searchBy))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

					}catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					if(!type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ("+loginids+") and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.APPLICATION_ID='1' order by a.quote_no desc";
					}
					else if(type.equalsIgnoreCase("Customer"))
					{
						sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where "+condition+" and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by a.quote_no desc";
					}
					searchValue1=null;
					searchValue2=null;
					searchValue3=null;
				}
			}
			else
			{
				if("select".equalsIgnoreCase(searchBy) || ("").equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("FIRST_NAME".equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and (lower(trim(b.company_name)) like '%"+searchValue.trim().toLowerCase()+"%' or lower(trim(b.first_name)) like '%"+searchValue.trim().toLowerCase()+"%') and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("quote_nos".equalsIgnoreCase(searchBy))
				{
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and (a.quote_no like '%"+searchValue+"%') and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
				}
				else if("DateWise".equalsIgnoreCase(searchBy))
				{
					String searchValue1=null;
					String searchValue2=null;
					String searchValue3=null;

					searchValue1=searchValue.substring(0,searchValue.indexOf("/"));
					searchValue2=searchValue.substring(searchValue.indexOf("/")+1,searchValue.lastIndexOf("/"));
					searchValue3=searchValue.substring(searchValue.lastIndexOf("/")+1,searchValue.length());
					try
					{
						if(Integer.parseInt(searchValue2)<10)
							searchValue=searchValue1+"/"+"0"+Integer.parseInt(searchValue2)+"/"+searchValue3;
						else
							searchValue=searchValue1+"/"+Integer.parseInt(searchValue2)+"/"+searchValue3;

					}catch(Exception e)
					{
						System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					}
					sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,initcap(b.first_name),initcap(b.LAST_NAME),a.login_id,a.APPLICATION_NO,b.COMPANY_NAME,nvl(md.open_cover_no,'0')from position_master a,personal_info b,marine_data md where a.login_id in ('"+loginIds+"') and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no and a.product_id='"+productId+"' and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' and a.APPLICATION_ID='"+rsaissuer+"' order by a.quote_no desc";
					searchValue1=null;
					searchValue2=null;
					searchValue3=null;
				}
			}
			ss=runner.multipleSelection(sql);
		}catch(Exception e)
		{
			System.out.println("Exception in gettin refferal data"+e.toString());
		}
		return ss;
	}

	/*** Dropdown list restriction based on having records or not	***/

	public String[][] getQuoteRegisterLogIds(String loginIds, String pid, String userType)
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[0];
		try
		{
			if("Customer".equalsIgnoreCase(userType)){
				args = new String[1];
				args[0] = loginIds;
				sql = getMultiLoginListQry(loginIds,userType);
				if(sql.length()>0){
					valuess = runner.multipleSelection(sql,args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='Y' AND PRODUCT_ID=?", new String[]{loginIds, pid});
			}
			else
			{
				args = new String[2];
				args[0] = loginIds;
				args[1] = pid;
				sql ="select login_id from login_master where oa_code = (select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and USERTYPE!='Freight' and login_id in (select distinct(a.login_id) from position_master a, personal_info b WHERE  a.product_id = ? and a.effective_date > (select sysdate from dual) and a.customer_id = b.customer_id and a.status='Y')" ;
				valuess = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getQuote RegisterLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		
		return valuess;
	}

	public String[][] getPortfolioLogIdswihtFreight(String loginIds,String pid,String userType)
	{
		String[][] valuess=new String[0][0];
		String sql = "";
		String args[] = new String[2];
		try
		{
			if("Customer".equalsIgnoreCase(userType)){
				args = new String[1];
				sql = getMultiLoginListQry(loginIds,userType);
				args[0] = loginIds;
				if(sql.length() > 0 ){
					valuess = runner.multipleSelection(sql, args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='P' AND PRODUCT_ID=?", new String[]{loginIds, pid});
			}
			else{
				args = new String[2];
				args[0] = loginIds;
				args[1] = pid;
				sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and login_id not in('NONE','NAN') and login_id in (select distinct(a.login_id) from position_master a, personal_info b WHERE  a.status='P' and product_id = ? and b.customer_id=a.customer_id and nvl(a.open_cover_int_status,'0') not in ('LINKED'))" ;
				valuess = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("<< getPortfolioLog IdswihtFreight >>"+e.toString());
			e.printStackTrace();
		}
		return valuess;
	}
	
	public String[][] getReferralUnAppLogIds(String loginIds,String pid,String userType) // Multi Login Condition for Customer
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[2];
		try
		{
			if("Customer".equalsIgnoreCase(userType)){
				args = new String[1];
				sql = getMultiLoginListQry(loginIds,userType);
				args[0] = loginIds;
				if(sql.length() > 0 ){
					valuess = runner.multipleSelection(sql, args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
//				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='Y' AND REMARKS='Referal' AND PRODUCT_ID=?", new String[]{loginIds,pid});
				valuess=runner.multipleSelection("SELECT DISTINCT A.LOGIN_ID FROM POSITION_MASTER A,MARINE_DATA B  WHERE A.APPLICATION_ID=? AND A.STATUS='Y' AND (A.REMARKS='Referal' OR B.ADMIN_REFERRAL_STATUS='Y') AND A.PRODUCT_ID=? AND B.APPLICATION_NO=A.APPLICATION_NO", new String[]{loginIds,pid});
			}
			else{
				args[0] = loginIds;
				args[1] = pid;
				sql ="select login_id from login_master where oa_code = (select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and USERTYPE!='Freight' and login_id in (select distinct(a.login_id) from position_master a,personal_info b,marine_data md where a.product_id = ? and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Referal') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','Normal','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='Y')) and md.application_no=a.application_no)" ;
				valuess = runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getReferral UnAppLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		return valuess;
	}
	
	public String[][] getReferralAppLogIds(String loginIds,String pid,String userType) // Multi Login Condition for Customer)
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[2];
		try
		{
			if("Customer".equalsIgnoreCase(userType)){
				args = new String[1];
				sql = getMultiLoginListQry(loginIds,userType);
				args[0] = loginIds;
				if(sql.length() > 0 ){
					valuess = runner.multipleSelection(sql, args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='Y' AND REMARKS='Admin' AND PRODUCT_ID=?", new String[]{loginIds,pid});
			}
			else{
				args[0] = loginIds;
				args[1] = pid;
				sql ="select login_id from login_master where oa_code = (select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and USERTYPE!='Freight' and login_id in (select distinct(a.login_id) from position_master a,personal_info b,marine_data md where a.product_id = ? and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='Y' and b.customer_id=a.customer_id and ((a.REMARKS in ('Admin') and md.ADMIN_REFERRAL_STATUS='N') or (a.REMARKS in ('Admin','NORMAL_EXCESS_PRICE') and md.ADMIN_REFERRAL_STATUS='N')) and md.application_no=a.application_no)" ;
				valuess=runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getReferral AppLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] getReferralRejectedLogIds(String loginIds,String pid,String userType) // Multi Login Condition for Customer
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[2];
		try
		{
			if("Customer".equalsIgnoreCase(userType)){
				args = new String[1];
				sql = getMultiLoginListQry(loginIds,userType);
				args[0] = loginIds;
				if(sql.length() > 0 ){
					valuess = runner.multipleSelection(sql, args);
				}
			}else if("RSAIssuer".equalsIgnoreCase(userType))
			{
				valuess=runner.multipleSelection("SELECT DISTINCT LOGIN_ID FROM POSITION_MASTER WHERE APPLICATION_ID=? AND STATUS='R' AND REMARKS='Admin' AND PRODUCT_ID=?", new String[]{loginIds,pid});
			}
			else{
				args[0] = loginIds;
				args[1] = pid;
				sql ="select login_id from login_master where oa_code = (select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and USERTYPE!='Freight' and login_id in (select distinct(a.login_id) from position_master a,personal_info b,marine_data md where a.product_id = ? and (a.FREIGHT_STATUS not in('F','B') or a.FREIGHT_STATUS is null) and a.status='R' and b.customer_id=a.customer_id and a.REMARKS in ('Admin') and md.application_no=a.application_no)" ;
				valuess=runner.multipleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			System.out.println("getReferral RejectedLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		return valuess;
	}
	
	public String[][] getFreightUnAppLogIds(String loginIds,String pid)
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[2];
		try
		{
			args[0] = loginIds;
			args[1] = pid;
			
			sql ="select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and usertype!='User' and login_id not in('NONE','NAN') and login_id in (select distinct(a.login_id)from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.product_id = ? and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in('U','T','N'))" ;
			valuess = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("<< getFreightUnAppLogIds Query Exception >>"+sql);
			System.out.println("getFreightUnAppLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		System.out.println("<< getFreightUnAppLogIds Query >>"+sql);
		return valuess;
	}

	public String[][] getFreightAppLogIds(String loginIds,String pid)
	{
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[2];
		try
		{
			args[0] = loginIds;
			args[1] = pid;
			sql ="select login_id from login_master where oa_code=(select oa_code from login_master where login_id=? and usertype!='Customer' and usertype!='User' and usertype!='Freight') and usertype!='User' and login_id not in('NONE','NAN') and login_id in (select distinct(a.login_id) from position_master a,personal_info b,marine_data md, FREIGHT_POSITION_MASTER f where a.product_id = ? and a.status='Y' and b.customer_id=a.customer_id and a.FREIGHT_STATUS in ('F') and md.application_no=a.application_no and a.quote_no=f.quote_no and f.STATUS in('A'))" ;
			valuess=runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("<< getFreightUnAppLogIds Query Exception >>"+sql);
			System.out.println("getFreightUnAppLogIds Query Exception"+e.toString());
			e.printStackTrace();
		}
		System.out.println("<< getFreightUnAppLogIds Query >>"+sql);
		return valuess;
	}
	
	/*** Dropdown list restriction based on having records or not - Ends	***/
	public void updateActiveQuote(String qno)
	{
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = qno;
			sql = "update position_master set entry_date=(select sysdate+8/24 from dual),effective_date=(select add_months(sysdate,1)-1 from dual) where quote_no=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String getPolicyCanceledStatus(String pno)
	{
		ValidationFormat validationFormat = new ValidationFormat();
			
		String sql = "";
		String res = "";
		try
		{
			if(validationFormat.IsDigitValidationFormat(pno)==true)
			{
				String args[] = new String[2];
				args[0] = pno;
				args[1] = pno;
				sql = "select Policy_Cancel_Status from position_master where policy_no=? or quote_no=?";
				res = runner.singleSelection(sql,args);
			}
			else
			{
				String args[] = new String[1];
				args[0] = pno;
				sql = "select Policy_Cancel_Status from position_master where policy_no=?";
				res = runner.singleSelection(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		res = res==null?"A":res;
		if(res.length()<=0)
			res = "A";
		return res;
	}

	//Royal World Work Started
	public static String getMaxQuote(String productTypeId,String loginBra)
	{
		/*String current_no=null;
		String typeId="";
		String args[] = new String[0];
		String sql = "";
		try
		{
			args = new String[3];
			args[0] = loginBra;
			args[1] = productTypeId;
			args[2] = loginBra;
			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?)" +
					" and status='Y' and BRANCH_CODE=?";

			current_no = runner.singleSelection(sql,args);
			
			args = new String[5];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = loginBra;
			args[3] = productTypeId;
			args[4] = loginBra;
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select QUOTE_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?)" +
					" and status='Y' and BRANCH_CODE=?";
			runner.multipleUpdation(sql,args);

		}
		catch(Exception e)
		{
			System.out.println("ERROR in getMaxQuote in DATACOLLECTION  "+e.toString());
			e.printStackTrace();
		}
		
		return current_no;*/
		
		String quoteNo = null;
	    try {
			if(productTypeId.trim().equalsIgnoreCase("3")){
				quoteNo = runner.singleSelection("select oneoff_quote.nextval from dual");
			}
			else{
				quoteNo = runner.singleSelection("select opencover_quote.nextval from dual");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return quoteNo;
	}
	/*public static  String getMaxQuote(String productTypeId)
	{
		String quoteNo = null;
	    try {
			if(productTypeId.trim().equalsIgnoreCase("3")){
				quoteNo = runner.singleSelection("select oneoff_quote.nextval from dual");
			}
			else{
				quoteNo = runner.singleSelection("select opencover_quote.nextval from dual");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		return quoteNo;
	}*/
	public String[][] emirateCollection(String cid)
	{
		String sql ="";
		String args[] = new String[1];
		String[][] title = new String[0][0];
		try
		{
			args[0] = cid;
			sql = "select city_name from city_master where country_id=? order by city_name ";
			title = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	public static synchronized String getMaxCustomerId(String loginBra,String pids)
	{
		String current_no=null;
		String args[] = new String[0];
		String sql ="";
		try
		{
			args = new String[3];
			args[0] = loginBra;
			args[1] = pids;
			args[2] = loginBra;
			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and BRANCH_CODE=?";
			current_no=runner.singleSelection(sql,args);
			
			args = new String[5];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = loginBra;
			args[3] = pids;
			args[4] = loginBra;
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID=?) and status='Y' and BRANCH_CODE=?";

			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getMaxQuote in DATACOLLECTION  "+e.toString());
			e.printStackTrace();
		}
		
		return current_no;
	}

	//Royal Worl Work ENd

	// Premium Page Excess Premium Validation
	public boolean validAmount(String value) 
	{
		boolean flag=true;
		String validChar=null;
		try
		{
			value=value.trim();
			if(value.length()>0 && value!=null )
			{
				validChar="1234567890.";
				for(int i=0;i<value.length();i++)
				{
					if(validChar.indexOf(value.charAt(i))== -1)
					{
						flag = false;
						break;
					}
				}
			}
			else
				return flag;
		}
		catch(Exception e)
		{
			System.out.println("Valid Amount in DataCollection.java1 "+value);
			return flag;
		}
		System.out.println("Valid Amount in DataCollection Java  "+ flag);
		return flag;
	}
	
	public String getMultiLoginListQry(final String loginId,final String userType)
	{
		String sql = "";
		String args[] = {loginId};
		try{
			sql = "select count (*) from login_master where agency_code=fd_code and login_id =?";
			String count = runner.singleSelection(sql,args);
			if(count.equals("0")){
				sql = "";
			}
			else if(count.equals("1")){
				sql = "select login_id from login_master where fd_code=(select fd_code from login_master where login_id=?)";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return sql;
	}
	
	public boolean getMasterLoginChk(final String loginId,final String userType)
	{
		boolean flag = true;
		String args[] = {loginId};
		try{
			final String sql = "select count (*) from login_master where agency_code=fd_code and login_id =?";
			String count = runner.singleSelection(sql,args);
			if(count.equals("0")){
				flag = false;
			}
			else if(count.equals("1")){
				flag = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	public String[][] getQuoteInfo(String applicationNo,String branchCode)
	{
		String sql ="";
		String args[] = new String[5];
		String[][] list = new String[0][0];
		try
		{
			args[0] = branchCode;
			args[1]=branchCode;
			args[2] = branchCode;
			args[3] = applicationNo;
			args[4] = branchCode;
			
			sql = "SELECT MT.TRANSPORT_DESCRIPTION, CM.COVER_NAME, CVM.CONVDESC, (CASE WHEN MD.EXTRA_COVER_ID = 0 THEN 'NO' ELSE 'YES' END) WAR_SRCC, (CASE WHEN MD.WAREHOUSE_TO_WAREHOUSE = 'YES' THEN MD.TRANSIT_FROM || ',' || CNM.COUNTRY_NAME ELSE CNM.COUNTRY_NAME END) ORIGIN_COUNTRY, (SELECT CASE WHEN MD.WARE_TO_WARE_FINAL_DEST = 'YES' THEN MD.FINAL_DESTINATION || ',' || CN1.COUNTRY_NAME ELSE CN1.COUNTRY_NAME END FROM COUNTRY_MASTER CN1 WHERE CN1.COUNTRY_ID = MD.FINAL_DESTINATION_COUNTRY_ID AND CN1.AMEND_ID = (SELECT MAX (AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_ID = CN1.COUNTRY_ID)) DEST_COUNTRY, MD.CURRENCY_NAME, CASE WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') = '0' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 1) WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') = 'NONE' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 1) WHEN SM.SALE_TERM_VALUE = 0 AND NVL (TM.TOLERANCE_NAME, '0') != 'NONE' THEN REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 1) || '+' || TM.TOLERANCE_NAME WHEN SM.SALE_TERM_VALUE > 0 AND NVL (TM.TOLERANCE_NAME, '0') = 'NONE' THEN SM.SALE_TERM_NAME WHEN SM.SALE_TERM_VALUE > 0 AND NVL (TM.TOLERANCE_NAME, '0') != 'NONE' AND NVL (TM.TOLERANCE_NAME, '0') != '0' THEN SM.SALE_TERM_NAME || '+' || TM.TOLERANCE_NAME ELSE SM.SALE_TERM_NAME END SALES_TERM, ( MD.EQUIVALENT_USD + NVL (MD.TOTAL_SALE_TERM_CHARGES, 0) + NVL (MD.TOTAL_TOLERANCE_CHARGES, 0)) EQUIVALENT, ( MD.EQUIVALENT_USD + NVL (MD.TOTAL_SALE_TERM_CHARGES, 0) + NVL (MD.TOTAL_TOLERANCE_CHARGES, 0)) / MD.EXCHANGE_RATE TOTAL_INSURED, ( NVL (MD.PREMIUM, 0) - NVL (MD.TOTAL_WAR_CHARGES, 0) - NVL (MD.POLICY_FEE, '0')) MARINE_PREMIUM, NVL (MD.TOTAL_WAR_CHARGES, 0) WAR_PREMIUM, REPLACE (NVL (MD.EXCESS_PREMIUM, 0), '-', '') ADDITIONAL_PREMIUM, NVL (MD.POLICY_FEE, '0') POLICY_ISSUNCE_FEE, (NVL (MD.PREMIUM, 0) - NVL (MD.POLICY_FEE, '0')) TOTAL_PREMIUM, (NVL (MD.PREMIUM, 0) + NVL (MD.EXCESS_PREMIUM, 0)) NET_PREMIUM, TO_CHAR (MD.INCEPTION_DATE, 'DD/MM/YYYY') POLICY_START_DATE, (SELECT FIRST_NAME FROM PERSONAL_INFO WHERE PM.CUSTOMER_ID = CUSTOMER_ID) CUSTOMER_NAME, MD.EXCHANGE_RATE, (CASE WHEN (PM.STATUS = 'Y' AND ( (PM.REMARKS IN ('Referal') AND MD.ADMIN_REFERRAL_STATUS = 'N') OR (PM.REMARKS IN ('Admin', 'Normal', 'Referal', 'NORMAL_EXCESS_PRICE') AND MD.ADMIN_REFERRAL_STATUS = 'Y'))) THEN 'Y' ELSE 'N' END) STATUS, (CASE WHEN (PM.STATUS = 'Y' AND ( (PM.REMARKS IN ('Referal') AND MD.ADMIN_REFERRAL_STATUS = 'N') OR (PM.REMARKS IN ('Admin', 'Normal', 'Referal', 'NORMAL_EXCESS_PRICE') AND MD.ADMIN_REFERRAL_STATUS = 'Y'))) THEN MD.REMARKS ELSE NULL END) REFERRAL_DESC, PM.QUOTE_NO QUOTE_NUMBER,'','',(SELECT COUNT ( * ) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MD.APPLICATION_NO AND PACKAGE_DESCRIPTION IS NULL AND AMEND_ID = (SELECT MAX (AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO = MD.APPLICATION_NO)) PACKAGE_DESCRIPTION, MD.COVER_ID, ( NVL (MD.PREMIUM, 0) - NVL (MD.TOTAL_WAR_CHARGES, 0) - NVL (MD.POLICY_FEE, '0')) + NVL (MD.TOTAL_WAR_CHARGES, 0) TOTAL, (SELECT POBOX FROM PERSONAL_INFO WHERE PM.CUSTOMER_ID = CUSTOMER_ID) POBOX, (SELECT EMIRATE FROM PERSONAL_INFO WHERE PM.CUSTOMER_ID = CUSTOMER_ID) CITY, (SELECT COUNTRY FROM PERSONAL_INFO WHERE PM.CUSTOMER_ID = CUSTOMER_ID) COUNTRY, REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 1) SALETERM, REGEXP_SUBSTR (SM.SALE_TERM_NAME, '[^+]+', 1, 2) SALETERMVALUE, tm.tolerance_name, PM.LAPSED_REMARKS FROM MARINE_DATA MD, POSITION_MASTER PM, MODE_OF_TRANSPORT MT, COVER_MASTER CM, (SELECT CYM.SNO, CYM.CONVDESC, CYM.MODE_TRANSPORT_ID, CYM.BRANCH_CODE FROM CONVEYAN_MASTER CYM WHERE CYM.BRANCH_CODE = ? AND CYM.AMEND_ID = (SELECT MAX (AMEND_ID) FROM CONVEYAN_MASTER WHERE SNO = CYM.SNO AND BRANCH_CODE = CYM.BRANCH_CODE AND TO_DATE (EFFECTIVE_DATE, 'DD-MM-YY') <= TO_DATE (SYSDATE, 'DD-MM-YY') AND MODE_TRANSPORT_ID = CYM.MODE_TRANSPORT_ID) AND TO_DATE (CYM.EFFECTIVE_DATE, 'DD-MM-YY') <= TO_DATE (SYSDATE, 'DD-MM-YY') UNION ALL SELECT 0, 'NONE', 0, ? FROM DUAL) CVM, COUNTRY_MASTER CNM, SALE_TERM_MASTER SM, (SELECT TOLERANCE_ID, tolerance_name FROM TOLERANCE_MASTER WHERE branch_code = ? UNION SELECT 0, NULL FROM DUAL) TM WHERE MD.APPLICATION_NO = ? AND MD.APPLICATION_NO = PM.APPLICATION_NO AND MT.MODE_TRANSPORT_ID = MD.MODE_OF_TRANSPORT AND MT.BRANCH_CODE = ? AND CM.COVER_ID = MD.COVER_ID AND CM.AMEND_ID = (SELECT MAX (AMEND_ID) FROM COVER_MASTER WHERE COVER_ID = CM.COVER_ID AND BRANCH_CODE = CM.BRANCH_CODE AND TO_DATE (EFFECTIVE_DATE, 'DD-MM-YY') <= TO_DATE (SYSDATE, 'DD-MM-YY')) AND TO_DATE (CM.EFFECTIVE_DATE, 'DD-MM-YY') <= TO_DATE (SYSDATE, 'DD-MM-YY') AND CM.BRANCH_CODE = MT.BRANCH_CODE AND CM.MODE_TRANSPORT_ID = MD.MODE_OF_TRANSPORT AND CVM.SNO = MD.SEA_OPTIONS AND CVM.BRANCH_CODE = MT.BRANCH_CODE AND CASE WHEN CVM.SNO = '0' THEN MD.MODE_OF_TRANSPORT ELSE TO_CHAR (CVM.MODE_TRANSPORT_ID) END = MD.MODE_OF_TRANSPORT AND CNM.COUNTRY_ID = MD.TRANSIT_FROM_COUNTRY_ID AND CNM.AMEND_ID = (SELECT MAX (AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_ID = CNM.COUNTRY_ID) AND TO_DATE (CNM.EFFECTIVE_DATE, 'DD-MM-YY') <= TO_DATE (SYSDATE, 'DD-MM-YY') AND SM.SALE_TERM_ID = MD.SALE_TERM_ID AND SM.BRANCH_CODE = MT.BRANCH_CODE AND TM.TOLERANCE_ID = NVL (MD.TOLERANCE_ID, 0)";
			list = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("EXCEPTION IN VALIDATE MONTH   "+list.length);
		return list;
	}
	public String[][] getCommodityInfo(String applicationNo,String branchCode)
	{
		String sql ="";
		String args[] = new String[2];
		String[][] list = new String[0][0];
		try
		{
			args[0]=applicationNo;
			args[1]=branchCode;
			
			//sql = "SELECT CM.COMMODITY_ID, CM.COMMODITY_NAME, NVL(MRD.COMMODITY_EXCESS_PREMIUM,0) POLICY_EXCESS, MRD.DESCRIPTION, MRD.SUM_INSURED, MRD.RATE, MRD.WARRATE, DECODE(MRD.FRAGILE,'on','YES','off','NO') FRAGILE, MRD.PACKAGE_DESCRIPTION, MRD.SUPPLIER_NAME, MRD.INVOICE_NUMBER FROM MARINE_RESULT_DETAILS MRD, COMMODITYMASTER CM WHERE MRD.APPLICATION_NO  = ? AND CM.COMMODITY_ID  = MRD.COMMODITY_ID AND CM.BRANCH_CODE  = ? AND CM.AMEND_ID  = (SELECT MAX (AMEND_ID) FROM COMMODITYMASTER WHERE COMMODITY_ID  = CM.COMMODITY_ID AND BRANCH_CODE  = CM.BRANCH_CODE AND TO_DATE (EFFECTIVE_DATE, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY')) AND TO_DATE (CM.EFFECTIVE_DATE, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY') ORDER BY CM.COMMODITY_NAME";
			sql=" SELECT CM.COMMODITY_ID, CM.COMMODITY_NAME, NVL(MRD.COMMODITY_EXCESS_PREMIUM,0) POLICY_EXCESS, MRD.DESCRIPTION, MRD.SUM_INSURED, MRD.RATE, MRD.WARRATE, DECODE(MRD.FRAGILE,'on','YES','off','NO') FRAGILE, MRD.PACKAGE_DESCRIPTION, MRD.SUPPLIER_NAME, "+
				" MRD.INVOICE_NUMBER,MRD.DESCRIPTION,mrd.currency_id  "+
				" FROM MARINE_RESULT_DETAILS MRD, COMMODITYMASTER CM "+ 
				" WHERE MRD.APPLICATION_NO  = ? AND CM.COMMODITY_ID  = MRD.COMMODITY_ID AND CM.BRANCH_CODE  = ? AND CM.AMEND_ID  = (SELECT MAX (AMEND_ID) FROM COMMODITYMASTER WHERE COMMODITY_ID  = CM.COMMODITY_ID AND BRANCH_CODE  = CM.BRANCH_CODE AND TO_DATE (EFFECTIVE_DATE, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY')) AND TO_DATE (CM.EFFECTIVE_DATE, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY') "+
				" and MRD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MARINE_RESULT_DETAILS WHERE APPLICATION_NO=MRD.APPLICATION_NO ) "+ 
				" ORDER BY CM.COMMODITY_NAME ";
			list = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("EXCEPTION IN VALIDATE MONTH   "+list.length);
		return list;
	}
	public String getApplicationNo(String quoteNo)
	{
		String sql = "";
		String count="";
		String args[] = {quoteNo};
		try{
			sql = "SELECT APPLICATION_NO FROM POSITION_MASTER WHERE QUOTE_NO=?";
			count = runner.singleSelection(sql,args);		
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	public String getCommodityInfoSingle(String applicationNo,
			String loginBranch) {

		String sql ="";
		String args[] = new String[3];
		String list ="";
		try
		{
			args[0]=applicationNo;
			args[1]=applicationNo;
			args[2]=loginBranch;
			
			sql = " SELECT INITCAP(cm.commodity_name)||' details as below:'||chr(10)||chr(10)||mrd.description||case when mrd.invoice_number is not null then chr(10)||mrd.invoice_number else null end" +
					" ||chr(10)||( Select REGEXP_REPLACE((BL_AWB_NO||chr(10)|| GHQ_FMS_CASE_NO||chr(10)|| GHQ_REFERENCE_NO),'(^|' || CHR (10) || ')' || CHR (10) || '+', '\1') "+
					" from marine_policy_details MPD,POSITION_MASTER PM where PM.quote_no=MPD.quote_no "+
					" AND PM.APPLICATION_NO=?) description "+
					" FROM marine_result_details mrd,commoditymaster cm WHERE "+
					" mrd.application_no=? AND cm.commodity_id  = mrd.commodity_id  AND cm.branch_code  = ? "+
					" AND cm.amend_id  = (SELECT MAX (amend_id) FROM commoditymaster WHERE commodity_id  = cm.commodity_id "+
					" AND branch_code  = cm.branch_code AND TO_DATE (effective_date, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY')) "+
					" AND TO_DATE (cm.effective_date, 'dd-MM-YY') < = TO_DATE (SYSDATE, 'dd-MM-YY')  AND mrd.amend_id=(SELECT MAX(amend_id) "+ 
					" FROM marine_result_details where application_no = mrd.application_no )"; 
			list = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	public String[][] getRfactorOC() {

		String sql ="";
		String[][] list = new String[0][0];
		try
		{
			sql = " select rsacode, CURRENCY_ID from currencY_master where currency_id=? and country_id=? "; 
			list = runner.multipleSelection(sql,new String[]{"1","1"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public String[][] getRfactorDC(String applicationNo) {

		String sql ="";
		String args[] = new String[2];
		String[][] list = new String[0][0];
		try
		{
			args[0]=applicationNo;
			args[1]="1";
			sql = " select rsacode,CURRENCY_ID from currencY_master where currency_id=(select currency_id from marine_data where application_no=?) and country_id=? "; 
			list = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public String[][] getGeneratedInfo(String applicationNo) {

		String sql ="";
		String args[] = new String[1];
		String[][] list = new String[0][0];
		try
		{
			args[0]=applicationNo;
			sql = " select initcap(COMPANY_NAME),NVL (TO_CHAR (sysdate, 'dd/MM/yyyy HH24:MI:SS'), 'NoDate') from broker_company_master where agency_code =(select lm.oa_code from position_master pm,login_master lm  where pm.application_no=? and pm.login_id=lm.login_id) "; 
			list = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	public String[][] getIssuersBrokersList(String productId, String quoteType, String issuer, String branchCode){
		logger.info("getIssuersBrokersList - Enter || productId: "+productId);
			String result[][] = new String[0][0]; 
			if("U".equalsIgnoreCase(quoteType)){	
				result=runner.multipleSelection("SELECT DISTINCT(A.LOGIN_ID) FROM POSITION_MASTER A,PERSONAL_INFO B,MARINE_DATA MD,BROKER_COMPANY_MASTER C, LOGIN_MASTER D WHERE A.PRODUCT_ID = ? AND (A.FREIGHT_STATUS NOT IN('F','B') OR A.FREIGHT_STATUS IS NULL) AND A.STATUS='Y' AND B.CUSTOMER_ID=A.CUSTOMER_ID AND ((A.REMARKS IN ('Referal') AND MD.ADMIN_REFERRAL_STATUS='N') OR (A.REMARKS IN ('Admin','Normal','NORMAL_EXCESS_PRICE') AND MD.ADMIN_REFERRAL_STATUS='Y')) AND MD.APPLICATION_NO=A.APPLICATION_NO AND A.EFFECTIVE_DATE >= (  SELECT   SYSDATE FROM DUAL) AND A.APPLICATION_ID NOT IN (?,'1') AND C.AGENCY_CODE=D.OA_CODE AND C.BRANCH_CODE=? AND D.LOGIN_ID=A.LOGIN_ID",new String[]{productId, issuer, branchCode});
			}else if("A".equalsIgnoreCase(quoteType)){	
				result=runner.multipleSelection("SELECT DISTINCT(A.LOGIN_ID) FROM POSITION_MASTER A,PERSONAL_INFO B,MARINE_DATA MD,BROKER_COMPANY_MASTER C, LOGIN_MASTER D WHERE A.PRODUCT_ID = ? AND (A.FREIGHT_STATUS NOT IN('F','B') OR A.FREIGHT_STATUS IS NULL) AND A.STATUS='Y' AND B.CUSTOMER_ID=A.CUSTOMER_ID AND ((A.REMARKS IN ('Admin') AND MD.ADMIN_REFERRAL_STATUS='N') OR (A.REMARKS IN ('Admin','NORMAL_EXCESS_PRICE') AND MD.ADMIN_REFERRAL_STATUS='N')) AND MD.APPLICATION_NO=A.APPLICATION_NO AND A.EFFECTIVE_DATE >= (  SELECT   SYSDATE FROM DUAL) AND A.APPLICATION_ID NOT IN (?,'1') AND C.AGENCY_CODE=D.OA_CODE AND C.BRANCH_CODE=? AND D.LOGIN_ID=A.LOGIN_ID",new String[]{productId, issuer, branchCode});
			}else if("R".equalsIgnoreCase(quoteType)){	
				result=runner.multipleSelection("SELECT DISTINCT(A.LOGIN_ID) FROM POSITION_MASTER A,PERSONAL_INFO B,MARINE_DATA MD,BROKER_COMPANY_MASTER C, LOGIN_MASTER D WHERE A.PRODUCT_ID = ? AND (A.FREIGHT_STATUS NOT IN('F','B') OR A.FREIGHT_STATUS IS NULL) AND A.STATUS='R' AND B.CUSTOMER_ID=A.CUSTOMER_ID AND A.REMARKS IN ('Admin') AND MD.APPLICATION_NO=A.APPLICATION_NO AND A.EFFECTIVE_DATE >= (  SELECT   SYSDATE FROM DUAL) AND A.APPLICATION_ID NOT IN (?,'1') AND C.AGENCY_CODE=D.OA_CODE AND C.BRANCH_CODE=? AND D.LOGIN_ID=A.LOGIN_ID",new String[]{productId, issuer, branchCode});
			}else if("P".equalsIgnoreCase(quoteType)){	
				result=runner.multipleSelection("SELECT DISTINCT(A.LOGIN_ID) FROM POSITION_MASTER A, PERSONAL_INFO B,BROKER_COMPANY_MASTER C, LOGIN_MASTER D WHERE  A.STATUS='P' AND A.PRODUCT_ID = ? AND B.CUSTOMER_ID=A.CUSTOMER_ID AND NVL(A.OPEN_COVER_INT_STATUS,'0') NOT IN ('LINKED') AND A.EFFECTIVE_DATE >= (  SELECT   SYSDATE FROM DUAL) AND A.APPLICATION_ID NOT IN (?,'1') AND C.AGENCY_CODE=D.OA_CODE AND C.BRANCH_CODE=? AND D.LOGIN_ID=A.LOGIN_ID",new String[]{productId, issuer, branchCode});
			}else{
				result=runner.multipleSelection("SELECT DISTINCT(A.LOGIN_ID) FROM POSITION_MASTER A, PERSONAL_INFO B,MARINE_DATA MD,BROKER_COMPANY_MASTER C, LOGIN_MASTER D WHERE  A.PRODUCT_ID = ? AND A.EFFECTIVE_DATE > (SELECT SYSDATE FROM DUAL) AND A.CUSTOMER_ID = B.CUSTOMER_ID AND A.STATUS='Y' AND (A.REMARKS NOT IN ('Admin', 'Referal', 'NORMAL_EXCESS_PRICE')) AND MD.APPLICATION_NO = A.APPLICATION_NO AND NVL (MD.ADMIN_REFERRAL_STATUS, 'N') NOT IN ('Y') AND A.APPLICATION_ID NOT IN (?,'1') AND C.AGENCY_CODE=D.OA_CODE AND C.BRANCH_CODE=? AND D.LOGIN_ID=A.LOGIN_ID",new String[]{productId, issuer, branchCode});
			}
		logger.info("getIssuersBrokersList - Exit || Result: "+result.length);
		return result;
	}
	
	
	
	
}// Class




