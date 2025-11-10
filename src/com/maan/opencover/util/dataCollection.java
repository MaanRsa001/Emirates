package com.maan.opencover.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.maan.services.util.runner;

public class dataCollection
{
	String[][] empByBroker=new String[0][0];

	public String[][] getEmpByBroker(String loginId)
	{
		String sql = "";
		String[][] valuess = new String[0][0];
		try
		{
			sql="select login_id from login_master where oa_code='"+getAgencyCode(loginId)+"' or created_by='"+getAgencyCode(loginId)+"' or login_id='"+loginId+"'";
			valuess = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] titleCollection(String brokerBra)
	{
		String sql = "";
		String[][] title = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = brokerBra;
			sql="select distinct title_name from title_master where branch_code=(select belonging_branch from branch_master where branch_code=?) and status='Y' ";
			title = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	public String[][] nationalityCollection()
	{
		String sql = "";
		String[][] title = new String[0][0];
		try
		{
			Date date=new Date();
			String stdate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			sql = "select cm.NATIONALITY_NAME,cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-MM-YYYY')<=to_date('"+stdate+"','dd-MM-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-MM-YYYY')<=to_date('"+stdate+"','dd-MM-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__,cm.NATIONALITY_NAME order by cm.COUNTRY_NAME";
			title = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}
	
	public String[][] countryCollection()
	{
		String sql = ""; 
		String[][] title = new String[0][0];
		try
		{
			sql = "select cm.COUNTRY_NAME,cm.COUNTRY_ID,cm.sno__,cm.amend_id from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-mm-YYYY')<=to_date(SYSDATE,'dd-mm-YYYY')  group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
		    title = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	public String[][] emirateCollection(String cid)
	{
		String sql = ""; 
		String[][] title = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = cid;
			sql = "select city_name from city_master where country_id=? and status='Y' order by city_name ";
			title = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

	public String setcutomerName(String quote)
	{
		String names = "";
		String qry = "";
		String[][] returnVal = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0]= quote;
			qry="select first_name,last_name from personal_info where customer_id=(select customer_id from position_master where quote_no=?)";
			returnVal = runner.multipleSelection(qry,args);
			names = returnVal[0][0] == null?"":returnVal[0][0]+" "+returnVal[0][1]==null?"":returnVal[0][0];
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return names;
	}
	
	public String[][] getTrashQuote(String loginIds)
	{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String sql = "";
		String args[] = new String[3];
		try
		{
			getsTotal=new java.util.HashMap();
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql="select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";
			valuess=runner.multipleSelection(sql);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			ss = new String[0][0];
			sql="select a.quote_no,a.customer_id,to_char(a.effective_date,'dd/mm/yyyy'),to_char(ADD_MONTHS(a.effective_date-1,1),'dd/mm/yyyy'),b.first_name,b.last_name,a.login_id,c.premium,a.status,a.application_no,b.COMPANY_NAME from position_master a,personal_info b,marine_data c where c.application_no=a.application_no and a.login_id in ("+loginAllIds+")  and (a.status in ('D','R') or a.effective_date<(select sysdate from dual)) and(a.remarks in('Referal','Normal','Admin')) and b.customer_id=a.customer_id order by a.quote_no DESC";
			ss = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}


	public String[][] getViewQuotesNotApproved(String loginIds)
	{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss = null;
		String args[] = new String[3];
		String sql = "";
		try
		{
			getsTotal = new java.util.HashMap();
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			
			ss = new String[0][0];
			sql = "select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','AdminReferal')) and b.customer_id=a.customer_id order by quote_no DESC";
		
			ss = runner.multipleSelection(sql);
			System.out.println("   ss length   "+ss.length);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ss;
	}

	public String[][] getViewQuotesNotApproved(String loginIds,String searchOption,String searchValue)
	{
		java.util.HashMap getsTotal=null;
		String loginAllIds="";
		String[][] valuess=new String[0][0];
		String[][] ss=null;
		String args[] = new String[3];
		String sql = "";
		try
		{
			getsTotal = new java.util.HashMap();
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql="select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess=runner.multipleSelection(sql,args);
			for(int i=0;i<valuess.length;i++)
			{
				loginAllIds="'"+valuess[i][0]+"',"+loginAllIds;
			}
			loginAllIds=loginAllIds.substring(0,loginAllIds.lastIndexOf(','));
			ss = new String[0][0];
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
				}
				catch(Exception e)
				{
					System.out.println("EXCEPTION IN VALIDATE MONTH   "+e.toString());
					e.printStackTrace();
				}

				sql="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,nvl(b.COMPANY_NAME,b.first_name),b.last_name,a.login_id,b.COMPANY_NAME,a.effective_date from position_master a,personal_info b,marine_data m where a.login_id in ("+loginAllIds+") and a.effective_date >= (select sysdate from dual) and a.status='Y' and (a.remarks not in('Admin','Referal','NORMAL_EXCESS_PRICE')) and m.application_no=a.application_no and nvl(m.admin_referral_status,'N') not in ('Y') and b.customer_id=a.customer_id and to_char(a.entry_date,'dd/MM/YYYY')='"+searchValue+"' order by quote_no DESC";
			
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
		return ss;
	}

	public java.util.HashMap getViewQuotes(String loginIds)
	{
		java.util.HashMap getsTotal=null;
		String[][] valuess=new String[0][0];
		String sql="";
		String args[] = new String[0];
		try
		{
			String[][] ss=null;
			getsTotal=new java.util.HashMap();
			args = new String[3];
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql = "select login_id from login_master where oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?";
			valuess = runner.multipleSelection(sql,args);
	
			getsTotal.put("total",""+valuess.length);
			getsTotal.put("getCus",valuess);

			for(int i=0;i<valuess.length;i++)
			{
				ss = new String[0][0];
				args = new String[1];
				args[0] = valuess[i][0];
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

	public String[][] getViewQuote(String loginId)
	{
		String[][] viewQuotes = new String[0][0];
		String args[] = new String[1];
		String qry = "";
		try
		{
			args[0] = loginId;
			qry="select a.quote_no,a.customer_id,to_char(a.entry_date,'dd')as days,to_char(a.entry_date,'MM')as months,to_char(a.entry_date,'YYYY')as years,to_char(a.effective_date,'dd')as days,to_char(a.effective_date,'MM')as months,to_char(a.effective_date,'YYYY')as years,b.first_name,b.last_name from position_master a,personal_info b where a.login_id=? and a.status='Y' and b.customer_id=a.customer_id";
			viewQuotes = runner.multipleSelection(qry,args);
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
				validChar="+-'abcdefghijklmnopqrstuvwxyz ";
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
		}catch(Exception e)
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
		
    	if ((date == null) || (pos.getErrorIndex() != -1))
		{
			if (date == null) 
				return "Invalid";
			if (pos.getErrorIndex() != -1) 
				return "Invalid";
				
			return "Invalid";
		}
		return returnVal;
	}

	public String  getAgencyCode(String logpersonId)
	{
		String agencyCode = "";
		String sql = "";
		String args[] = new String[1];
		try
		{
			args[0] = logpersonId;
			sql = "select agency_code from personal_info where login_id=?";
			agencyCode = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return agencyCode;
	}
	
	public static String getMaxCustomerId(String loginBra)
	{
		String current_no=null;
		String sql = "";
		String args[] = new String[0];
		try
		{
			/*if(loginBra.indexOf("'")!=-1)
				loginBra = loginBra.replaceAll("'","");
		
			args = new String[2];
			args[0] = loginBra;
			args[1] = loginBra;
			sql = "select nvl(max(current_no)+1,max(start_no)) from policyno_generate where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and BRANCH_CODE=? and status='Y'";
			current_no = runner.singleSelection(sql,args);
			
			args = new String[4];
			args[0] = current_no;
			args[1] = current_no;
			args[2] = loginBra;
			args[3] = loginBra;
			sql = "update policyno_generate set current_no=?,remarks=? where type_id=(select CUSTOMER_TYPE_ID from BRANCH_DETAIL where BRANCH_CODE=? and PRODUCT_ID='11') and BRANCH_CODE=? and status='Y'";
			runner.multipleUpdation(sql,args);*/
			current_no = runner.singleSelection("SELECT CUSTOMER_NUMBER_SEQ.NEXTVAL FROM DUAL");
		}
		catch(Exception e)
		{
			System.out.println("ERROR in OpenCover in DATACOLLECTION  "+e.toString());
			e.printStackTrace();
		}
		System.out.println("   DATACOLLECTION getMaxCustomerId OpenCover DataCollection"+current_no);
		return current_no;
	}

	public String[][] getLogIds(String loginIds)
	{
		String[][] valuess = new String[0][0];
		String  sql= ""; 
		String args[] = new String[3];
		try
		{
			args[0] = loginIds;
			args[1] = loginIds;
			args[2] = loginIds;
			sql = "select login_id from login_master where (oa_code=(select agency_code from login_master where login_id=?) or created_by=(select agency_code from login_master where login_id=?) or login_id=?) and login_id not in('NONE','NON')";
			valuess = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	
	public String[][] getAdminReferals()
	{
		String[][] admins = new String[0][0] ;
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
		String[][] valuess = new String[0][0];
		String sql= ""; 
		String args[] = new String[1];
		try
		{
			args[0] = loginIds;
			sql = "select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)";
			valuess = runner.multipleSelection(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return valuess;
	}

	public String[][] countryCollectionWithId(String branchCode)
	{
		String sql= ""; 
		String[][] title = new String[0][0];
		try
		{
			Date date=new Date();
			String stdate= new SimpleDateFormat("dd-MM-yyyy").format(date);
			//sql = "select cm.COUNTRY_NAME,cm.COUNTRY_ID from COUNTRY_MASTER cm where cm.status in ('Y','R') and cm.amend_id||cm.COUNTRY_ID in (select  max(amend_id)||COUNTRY_ID from COUNTRY_MASTER where  to_date(effective_date,'dd-MM-YYYY')<=to_date('"+stdate+"','dd-MM-YYYY') and status in('Y','R')  group by COUNTRY_ID) and to_date(cm.effective_date,'dd-MM-YYYY')<=to_date('"+stdate+"','dd-MM-YYYY') group by cm.COUNTRY_ID,cm.COUNTRY_NAME,cm.amend_id,cm.sno__ order by cm.COUNTRY_NAME";
			sql = "select E.COUNTRY_ID,E.COUNTRY_NAME from COUNTRY_MASTER E WHERE  E.AMEND_ID=(SELECT MAX(AMEND_ID) FROM COUNTRY_MASTER WHERE COUNTRY_NAME=E.COUNTRY_NAME) and country_id=(select origination_country_id from branch_master where branch_code=?)";
			title = runner.multipleSelection(sql, new String[]{branchCode});
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return title;
	}

} // Class

