package com.maan.admin.DAO;


import java.util.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import proj.sql.QueryBuilder;
import com.maan.services.util.runner;
import proj.date.DateFunction;
import java.io.PrintWriter;

public class DatewiseBrokerDetails
{
	private String dobDay = "";
	private String dobMonth = "";
	private String dobYear = "";
	private String dobDay1 = "";
	private String dobMonth1 = "";
	private String dobYear1 = "";
	private String data1 = "";
	private String data2 = "";
	private String brokerIds = "";
	
	public void setDobDay(String dobDay)
	{
		this.dobDay=dobDay;
	}
	public void setDobMonth(String dobMonth)
	{
		this.dobMonth=dobMonth;
	}
	public void setDobYear(String dobYear)
	{
		this.dobYear=dobYear;
	}
	public String getDobDay()
	{
		return dobDay;
	}
	public String getDobMonth()
	{
		return dobMonth;
	}
	public String getDobYear()
	{
		return dobYear;
	}
	public void setDobDay1(String dobDay1)
	{
		this.dobDay1=dobDay1;
	}
	public void setDobMonth1(String dobMonth1)
	{
		this.dobMonth1=dobMonth1;
	}
	public void setDobYear1(String dobYear1)
	{
		this.dobYear1=dobYear1;
	}
	public String getDobDay1()
	{
		return dobDay1;
	}
	public String getDobMonth1()
	{
		return dobMonth1;
	}
	public String getDobYear1()
	{
		return dobYear1;
	}
	public void setData1(String data1)
	{
		this.data1=data1;
	}
	public void setData2(String data2)
	{
		this.data2=data2;
	}
	public String getData1()
	{
		return this.data1;
	}
	public String getData2()
	{
		return this.data2;
	}
	public void setbrokerIds(String brokerIds)
	{
		this.brokerIds = brokerIds;
	}
	public String getbrokerIds()
	{
		return brokerIds;
	}

	public synchronized String dateValidation()
	{
		com.maan.services.util.dataCollection data=new com.maan.services.util.dataCollection();
		String error="";
		String values=null;
		try
		{
			if (brokerIds.equalsIgnoreCase("select"))
			{
				error =error+ "<br> *Please Select the Broker...";
			}
			values=data.checkDate(dobDay+"/"+dobMonth+"/"+dobYear);
			if("Invalid".equalsIgnoreCase(values))
				error=error+"<br>*"+runner.getErrormsg("62");
	
			values=data.checkDate(dobDay1+"/"+dobMonth1+"/"+dobYear1);
			if("Invalid".equalsIgnoreCase(values))
				error=error+"<br>*"+runner.getErrormsg("63");
             else if(Integer.parseInt(dobYear1)<Integer.parseInt(dobYear))
             {
				error=error+"<br>*"+runner.getErrormsg("72");
			 }
			 else if(Integer.parseInt(dobYear1)==Integer.parseInt(dobYear))
             {
				if(Integer.parseInt(dobMonth1)<Integer.parseInt(dobMonth))
				{
		         error=error+"<br>*"+runner.getErrormsg("72");
				}
				else if(Integer.parseInt(dobMonth1)==Integer.parseInt(dobMonth))
				{
					if(Integer.parseInt(dobDay1)<Integer.parseInt(dobDay))
					{
		             error=error+"<br>*"+runner.getErrormsg("72");
					}
				}
			}
			SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        	simpleFormatter.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT"));
			String s = simpleFormatter.format(new java.util.Date());
			String enteredDate1=dobDay1+"/"+dobMonth1+"/"+dobYear1;
			String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
			long diff1=0;
			long diff=0;
			try
			{
				DateFunction dbr=new DateFunction();
				diff1=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));
				diff=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
			}
			catch(Exception e)
			{
			}
			if(diff>0)
			{
                error=error+"<br>*"+runner.getErrormsg("70");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception in "+e.toString());
		}
		return error;		
	}

	public String[][] getMississipiNo(String openCoverNo)
	{
		String qry="";
		String[][] mississipiNo=new String[0][0];
		try
		{
			qry = "select distinct a.company_name,c.MISSIPPI_OPENCOVER_NO,b.login_id,nvl(d.first_name,d.company_name) from broker_company_master a,login_master b,open_cover_master c,personal_info d where b.agency_code=a.agency_code and c.broker_id=b.login_id and c.customer_id=d.customer_id and c.proposal_no in (select proposal_no from open_cover_position_master where open_cover_no in('"+openCoverNo+"') and status='P') order by a.company_name";
			mississipiNo = runner.multipleSelection(qry);
		}
		catch (Exception e)
		{
			System.out.println("Exception in "+e.toString());
			e.printStackTrace();
		}
		return mississipiNo;
	}

	public String[][] datewiseBrokerOpenCoverDetails()
	{

		String result[][] = new String[0][0];
		String qry ="";
		String temp="";
		String values[][] = new String[0][0];
		try
		{
			 qry = "select open_cover_no,count(status),nvl(sum(premium+excess_premium),0),nvl(sum(COMMISSION),0),nvl(sum(PRO_COMMISSION),0) from position_master where status='P' and product_id='11' and open_cover_no is not null and INCEPTION_DATE between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY')+1 and login_id in(select login_id from login_master where oa_code=(select oa_code from login_master where login_id=?)) group by open_cover_no order by open_cover_no";
			 String args[] = new String[3];
			 args[0] = data1;
			 args[1] = data2;
			 args[2] = brokerIds;
			result = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR "+e.toString());
			e.printStackTrace();
		}
		return result;
	}

	public String[][] getBrokersHasCover()
	{
		String[][] result=null;
		try
		{
		String qry =("select distinct a.company_name,b.login_id from broker_company_master a,login_master b,open_cover_master c where b.agency_code=a.agency_code and c.broker_id=b.login_id and c.proposal_no in (select proposal_no from open_cover_position_master where open_cover_no is not null and status='P') order by a.company_name");
			result=runner.multipleSelection(qry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public String[][] getBrokersHasCover(String branchCode)
	{
		String[][] result=null;
		try
		{
			String qry = "select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by lower(bcm.COMPANY_NAME)";
			result=runner.multipleSelection(qry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}

	public String getBrokerLoginId(String branchCode)
	{
		String[][] result=null;
		String loginIds = "";
		try
		{
			String qry = "select bcm.company_name,pi.login_id from BROKER_COMPANY_MASTER bcm,personal_info pi where bcm.agency_code=pi.agency_code  and bcm.status='Y' and pi.status=bcm.status and APPLICATION_ID='2' and pi.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+branchCode+"))) order by lower(bcm.COMPANY_NAME)";
			result=runner.multipleSelection(qry);
		}
		catch(Exception e)
		{
			System.out.println("getBrokerLoginId "+e.toString());
			e.printStackTrace();
		}
		for(int i=0;i<result.length;i++)
		{
			loginIds =loginIds+"'"+result[i][1]+"',";
		}
		loginIds = loginIds.substring(0,loginIds.length()-1);

		return loginIds;
	}

	public String[][] datewiseBrokerOpenCoverDetailsAll(String totLogin)
	{

		String result[][] = new String[0][0];
		String temp = "";
		String qry ="";
		String values[][] = new String[0][0];
		try
		{
			 qry = "select open_cover_no,count(status),nvl(sum(premium+excess_premium),0),nvl(sum(COMMISSION),0),nvl(sum(PRO_COMMISSION),0) from position_master where status='P' and product_id='11' and open_cover_no is not null and INCEPTION_DATE between to_date(?,'DD-MM-YYYY') and to_date(?,'DD-MM-YYYY')+1 and login_id in(select login_id from login_master where oa_code in (select oa_code from login_master where login_id in("+totLogin+"))) group by open_cover_no order by open_cover_no";
			  String args[] = new String[2];
			 args[0] = data1;
			 args[1] = data2;
			result = runner.multipleSelection(qry,args);
		}
		catch(Exception e)
		{
			System.out.println("ERROR "+e.toString());
			e.printStackTrace();
		}
		return result;
	}

}//class