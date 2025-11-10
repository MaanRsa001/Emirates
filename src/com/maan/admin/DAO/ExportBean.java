package com.maan.admin.DAO;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import proj.date.DateFunction;

import com.maan.common.LogUtil;
import com.maan.common.error.ErrorInfo;
import com.maan.common.exception.BaseException;
import com.maan.services.util.runner;

public class ExportBean extends ErrorInfo
{
	private static final Logger logger = LogUtil.getLogger(ExportBean.class);
	final static transient private String ENTER = "- Enter";
	final static transient private String EXIT = "- Exit";
	

	private String	dobDay="";
	private String	dobMonth="";
	private String	dobYear="";
	private String dobDay1="";
	private String dobMonth1="";
	private String dobYear1="";
	private String data1="";
	private String data2="";
	private String error="";
	private String branch="";
	private String qnoFrom="";
	private String qnoTo="";
	private String quotenoss="";
	private String pid = "";
	private String coreProductCode = "";
	private String ClosedRemarks;
	private StringBuffer policyList = new StringBuffer();
	// Modified By Rajesh R For Close trn//
			/////////////////Closed Date///////////
			String	closedDay;
			String	closedMonth;
			String	closedYear;
			/////////////////Open Date/////////
			String	openDay;
			String	openMonth;
			String	openYear;
			/////////////////Entry Date////////
			String	entryDay;
			String	entryMonth;
			String	entryYear;
			////////// Remarks /////////
			String  Closed_Remarks;
			String loginId;
	///////Settr Methods//////
	public void setBranch(String branch)
	{
		this.branch = branch;
	}
	public String getBranch()
	{
		return this.branch;
	}
	public void setPid(String pid)
	{
		this.pid = pid;
	}
	public String getPid()
	{
		return this.pid;
	}
	public void setQnoFrom(String qnoFrom)
	{
		this.qnoFrom = qnoFrom;
	}
	public String getQnoFrom()
	{
		return this.qnoFrom;
	}
	public void setQnoTo(String qnoTo)
	{
		this.qnoTo = qnoTo;
	}
	public String getQnoTo()
	{
		return this.qnoTo;
	}
	public void setQuotenoss(String quotenoss)
	{
		this.quotenoss = quotenoss;
	}
	public String getQuotenoss()
	{
		return this.quotenoss;
	}
	public void setPolicyList(StringBuffer policyList)
	{
		this.policyList = policyList;
	}
	public StringBuffer getPolicyList()
	{
		return this.policyList;
	}
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
	public String getData1()
	{
		return data1;
	}
	public String getData2()
	{
		return data2;
	}
	public void setData1(String data1)
	{
		this.data1=data1;
	}
	public void setData2(String data2)
	{
		this.data2=data2;
	}
	//For Close TRN Modified By Rajesh For Close TRN Start//
	public void setClosedDay(String closedDay)
	{
		this.closedDay = closedDay;
	}
	public void setClosedMonth(String closedMonth)
	{
		this.closedMonth = closedMonth;
	}
	public void setClosedYear(String closedYear)
	{
		this.closedYear = closedYear;
	}
	public void setOpenDay(String openDay)
	{
		this.openDay = openDay;
	}
	public void setOpenMonth(String openMonth)
	{
		this.openMonth = openMonth;
	}
	public void setOpenYear(String openYear)
	{
		this.openYear = openYear;
	}
	public void setEntryDay(String entryDay)
	{
		this.entryDay = entryDay;
	}
	public void setEntryMonth(String entryMonth)
	{
		this.entryMonth = entryMonth;
	}
	public void setEntryYear(String entryYear)
	{
		this.entryYear = entryYear;
	}
	public void setClosed_Remarks(String Closed_Remarks)
	{
		this.Closed_Remarks = Closed_Remarks;
	}
	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}
	///////Getter Methods////
	public String getClosedDay()
	{
		return this.closedDay;
	}
	public String getClosedMonth()
	{
		return this.closedMonth;
	}
	public String getClosedYear()
	{
		return this.closedYear;
	}
	public String getOpenDay()
	{
		return this.openDay;
	}
	public String getOpenMonth()
	{
		return this.openMonth;
	}
	public String getOpenYear()
	{
		return this.openYear;
	}
	public String getEntryDay()
	{
		return this.entryDay;
	}
	public String getEntryMonth()
	{
		return this.entryMonth;
	}
	public String getEntryYear()
	{
		return this.entryYear;
	}
	public String getClosed_Remarks()
	{
		return this.Closed_Remarks;
	}
	public String getLoginId()
	{
		return this.loginId;
	}
	/////End///////
		public synchronized String validateDateFields()
		{
			com.maan.services.util.dataCollection data=new com.maan.services.util.dataCollection();
		
			String error="";
			String values=null;
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
        	String s = sf.format(new java.util.Date());
            String enteredDate1=dobDay1+"/"+dobMonth1+"/"+dobYear1;
			String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
			long diff1=0;
			long diff=0;

			try
			{
				DateFunction dbr=new DateFunction();
				diff1=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate1));
				diff=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
			
				if(diff>0)
					error=error+"<br>*"+runner.getErrormsg("70");
				/*if(diff1>0)
					error=error+"<br>*"+runner.getErrormsg("71");*/
				if(branch.equalsIgnoreCase("Select") ||branch.equalsIgnoreCase("")||branch.length()<=0)
					error=error+"<br>*"+"Please Select the Branchwise Option";
				if(pid.equalsIgnoreCase("Select") ||pid.equalsIgnoreCase("")||pid.length()<=0)
					error=error+"<br>*"+"Please Select the Product Option";
				
				/*long daydiff = dbr.getDayDifference(dbr.getCalendar(enteredDate),dbr.getCalendar(enteredDate1));
				if(daydiff<=0)
				{
					if(Integer.parseInt(dobYear1)<=Integer.parseInt(dobYear))
						error = error+"<br/>* Invalid End Date, End Date must be greater than Start Date";
				}*/
			}	
			catch(Exception e)
			{
				System.out.println("ExportBean .."+e.toString());
				e.printStackTrace();
			}
			
			return error;
		}
		public synchronized String validatePolicyFields(String branch)
		{
			String error="";
			try
			{
				StringTokenizer sqno = new StringTokenizer(qnoFrom,"/");
				StringTokenizer eqno = new StringTokenizer(qnoTo,"/");
				System.out.println("sqno.countTokens()"+sqno.countTokens());
				System.out.println("eqno.countTokens()"+eqno.countTokens());

				String policyBranch1 = qnoFrom.substring(0,qnoFrom.indexOf("-"));
				String policyBranch2 = qnoTo.substring(0,qnoTo.indexOf("-"));
				System.out.println("validatePolicyFields ........"+policyBranch1);
				System.out.println("validatePolicyFields ........"+policyBranch2);
				if(!policyBranch1.equalsIgnoreCase(branch) || !policyBranch2.equalsIgnoreCase(branch) )
					error = error+"<br>*"+"Invalid Branch Policy Numbers";


			/*	if((qnoFrom.length() > 0  && qnoFrom.length() < 15))
					error = error+"<br>* Start Policy Number Invalid";
				if(qnoFrom.length() > 15)
					error = error+"<br>* Start Policy Number Invalid";
				if(qnoFrom.length() == 15 && sqno.countTokens()<4 )
					error = error+"<br>* Start Policy Number Invalid";
				if((qnoTo.length() > 0  && qnoTo.length() < 15 ))
					error = error+"<br>* End Policy Number Invalid";
				if(qnoTo.length() > 15)
					error = error+"<br>* End Policy Number Invalid";
				if(qnoTo.length() == 15 && eqno.countTokens()<4 )
					error = error+"<br>* End Policy Number Invalid";*/
				if(qnoFrom.length()<=0)
					error = error+"<br>* Please Enter Start Policy Number";
				if(qnoTo.length()<=0)
					error = error+"<br>* Please Enter End Policy Number";
				/*if(branch.equalsIgnoreCase("Select") ||branch.equalsIgnoreCase("")||branch.length()<=0)
					error = error+"<br>*"+"Please Select the Branchwise Option";
				if(pid.equalsIgnoreCase("Select") ||pid.equalsIgnoreCase("")||pid.length()<=0)
					error=error+"<br>*"+"Please Select the Product Option";*/
			}	
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return error;
		}
		public synchronized String validatePolicyNos(String branch)
		{
			String error="";
			String count="";
			try
			{
				StringTokenizer sqno,spno;
				String flag="";
				int i=0;
				if(quotenoss.length()<=0)
					error = error+"<br>* Please Enter Policy Numbers";
				else
				{
					spno = new StringTokenizer(quotenoss,",");
					if(spno.countTokens()<=0){
						error = error+"<br>*"+"Invalid Policy Numbers";
						System.out.println("royal test spno..."+spno);
					}
					String policyBranch = quotenoss.substring(0,quotenoss.indexOf("-"));
					logger.info("Branch....."+branch);
					logger.info("policyBranch....."+policyBranch);
					if(!policyBranch.equalsIgnoreCase(branch)){
						error = error+"<br>*"+"Invalid Branch Policy Numbers";
					}

					while(spno.hasMoreTokens())
					{
						i++;
						String temp = spno.nextToken();
						policyList.append("'"+temp+"',");
					
						sqno = new StringTokenizer(temp,"/");
					
						if((temp.length() > 0  && temp.length() < 15))
							flag=flag+""+i+",";
						if(temp.length() == 15 && sqno.countTokens()<4 )
							flag=flag+""+i+",";
						if(temp.length()>15)
							flag=flag+""+i+",";
					}
					if(policyList.length()>0)
						policyList.deleteCharAt(policyList.length()-1);
				}
				/*if(flag.length()>0)
					error = error+"<br>*"+"Invalid Policy Numbers in Positions " + flag + ".";*/

				/*if(branch.equalsIgnoreCase("Select") ||branch.equalsIgnoreCase("")||branch.length()<=0)
					error = error+"<br>*"+"Please Select the Branchwise Option";
				if(pid.equalsIgnoreCase("Select") ||pid.equalsIgnoreCase("")||pid.length()<=0)
					error=error+"<br>*"+"Please Select the Product Option";*/
			}	
			catch(Exception e)
			{
				System.out.println("Exception in validatePolicyNos..."+e.toString());e.printStackTrace();
			}
			System.out.println("royal test error is..."+error);
			return error;
		}

	/*public String insertCloseTRN(String loginBra,String cid)
	{
		String result = "";
		String sql = "";
		try
		{
			if(loginBra.indexOf("'")!=-1)
					loginBra = loginBra.replaceAll("'","");
			String CLO_DATE_CLOSED="",CLO_DATE_OPENED="",CLO_PREPARED_DT="";
			try
			{
				CLO_DATE_CLOSED = closedDay+"-"+closedMonth+"-"+closedYear;
				CLO_DATE_OPENED = openDay+"-"+ openMonth +"-"+openYear;
				CLO_PREPARED_DT = entryDay+"-"+ entryMonth +"-"+entryYear;
			}
			catch(Exception e)
			{
				System.out.println("Exception in Date Conversion"+e.toString());
			}

			String[][] trn_close = runner.multipleSelection("select * from T_TRN_CLOSING where BRANCH_CODE='"+loginBra+"'");
			if(trn_close.length>0)
			{
				Closed_Remarks = Closed_Remarks.replaceAll("'","''");
				sql = "update T_TRN_CLOSING set CLO_DATE_CLOSED=to_date('"+CLO_DATE_CLOSED+"','DD-MM-YYYY'),CLO_REMARKS='"+Closed_Remarks+"',CLO_PREPARED_DT=to_date( '"+CLO_PREPARED_DT+"','DD-MM-YYYY'),CLO_DATE_OPENED=to_date('"+CLO_DATE_OPENED+"','DD-MM-YYYY') where CLO_TRAN_CODE=1 and BRANCH_CODE='"+loginBra+"'";
				result = runner.updation(sql);
				System.out.println("T_TRN_CLOSING updation sql"+sql);
			}
			else
			{
				sql = "insert into T_TRN_CLOSING(CLO_TRAN_CODE,CLO_DATE_CLOSED,CLO_REMARKS,CLO_PREPARED_DT,CLO_DATE_OPENED,BRANCH_CODE) values(1,to_date('"+CLO_DATE_CLOSED+"','DD-MM-YYYY'),'"+Closed_Remarks+"',to_date( '"+CLO_PREPARED_DT+"','DD-MM-YYYY'),to_date('"+CLO_DATE_OPENED+"','DD-MM-YYYY'),'"+loginBra+"')";
				result = runner.insertion(sql);
				System.out.println("T_TRN_CLOSING insertion sql"+sql);
			}
			int cm =0;
			int om =0;
			int em =0;
			String closedYear1 = "";
			String closedMonth1 = "";
			try
			{
				String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
				for(int i=0;i<MMM.length;i++)
				{
					if(MMM[i].equalsIgnoreCase(closedMonth))
						cm = i+1;
					if(MMM[i].equalsIgnoreCase(openMonth))
						om = i+1;
					if(MMM[i].equalsIgnoreCase(entryMonth))
						em = i+1;
					if(MMM[i].equalsIgnoreCase(closedMonth) && i!=11)
						closedMonth1 = MMM[i+1];
					else if(i==11)
						closedMonth1 = "JAN";
				}
			}
			catch(Exception e)
			{
				System.out.println("Exception in T_TRN_CLOSING"+e.toString());
				e.printStackTrace();
			}
			
			if(cm==12)
				closedYear1 = ""+(Integer.parseInt(closedYear)+1);
			else
				closedYear1 = ""+(Integer.parseInt(closedYear)+1);
			
			String futureDate = openDay+"-"+closedMonth1+"-"+closedYear;
			String maxRun;
			try
			{
				sql = "select max(RUNNING_SNO)+1 from TRN_CLOSING_DETAILS where BRANCH_CODE='"+loginBra+"'";
				maxRun = runner.singleSelection(sql);
				if(maxRun.equalsIgnoreCase("DIDN'T SELECTED"))
					maxRun = "1";
			}
			catch(Exception e)
			{
				maxRun = "1";
				System.out.println("Exception in Selecting  RunNo from TRN_CLOSING_DETAILS"+e.toString());
			}
			try
			{
				java.util.Date sysDate = new java.util.Date();
				SimpleDateFormat hour = new SimpleDateFormat("HH");
				SimpleDateFormat min = new SimpleDateFormat("MM");
				SimpleDateFormat sec = new SimpleDateFormat("SS");
				
				String sysHour = hour.format(sysDate);
				String sysMin = min.format(sysDate);
				String sysSec = sec.format(sysDate);
			
				int intmin = Integer.parseInt(sysMin);
				int intsec = Integer.parseInt(sysSec);
				intmin = intmin+intsec/60;
				intsec = intsec%60;
				sysMin = ""+intmin;
				sysSec = ""+intsec;

				CLO_PREPARED_DT = entryDay+"-"+ entryMonth +"-"+entryYear+" "+sysHour+":"+sysMin+":"+sysSec;
				
		}
		catch(Exception e)
		{
			System.out.println("Exception in today Date"+e.toString());
		}
			try
			{
				String monthDesc = entryMonth+" "+entryYear;
				System.out.println("monthDesc.."+monthDesc);
				System.out.println("maxRun.."+maxRun);
				System.out.println("CLO_DATE_OPENED.."+CLO_DATE_OPENED);
				System.out.println("CLO_DATE_CLOSED.."+CLO_DATE_CLOSED);
				System.out.println("futureDate.."+futureDate);
				System.out.println("Closed_Remarks.."+Closed_Remarks);
				System.out.println("CLO_PREPARED_DT.."+CLO_PREPARED_DT);
				sql = "insert into TRN_CLOSING_DETAILS values('"+maxRun+"', '"+monthDesc+"', to_date('"+CLO_DATE_OPENED+"','DD-MM-YYYY'), to_date('"+CLO_DATE_CLOSED+"','DD-MM-YYYY'), to_date('"+futureDate+"','DD-MM-YYYY'), to_date( '"+CLO_PREPARED_DT+"','DD-MM-YYYY HH24:MI:SS'),'"+Closed_Remarks+"','y','"+loginBra+"','"+cid+"')";
				runner.insertion(sql);
				System.out.println("RoyalTEst New TRn_Closing detials are inserted"+sql);
			}
			catch(Exception e)
			{
				
				System.out.println("Exception in inserting Data into TRN_CLOSING_DETAILS"+e.toString());
			}
	
		}
		catch(Exception e)
		{
			System.out.println("Exception in inserting Data into T_TRN_CLOSING"+e.toString());e.printStackTrace();
		}
		
		return result;
	}*/
		
		public String insertCloseTRN(String loginBra,final String cid,final String productCode)throws BaseException
		{
			logger.info("ExportBean insertCloseTRN method(String,String,String)");
			logger.debug(ENTER);
			String result = "";
			String sql = "";
			String CLODATECLOSED = "",CLODATEOPENED = "",CLOPREPAREDDT = "";
			String[][] trn_close = new String[0][0];
			String args[] = new String[0];
			if(loginBra.indexOf("'")!=-1){
				loginBra = loginBra.replaceAll("'","");
			}
			CLODATECLOSED = closedDay+"-"+closedMonth+"-"+closedYear;
			CLODATEOPENED = openDay+"-"+ openMonth +"-"+openYear;
			CLOPREPAREDDT = entryDay+"-"+ entryMonth +"-"+entryYear;
			args = new String[2];
			args[0] = loginBra;
			args[1] = productCode;
			trn_close = runner.multipleSelection("select * from T_TRN_CLOSING where BRANCH_CODE=? and PRODUCT_CORE_CODE=?",args);
			if(trn_close.length>0)
			{
				ClosedRemarks = ClosedRemarks.replaceAll("'","''");
				args = new String[6];
				args[0] = CLODATECLOSED;
				args[1] = ClosedRemarks;
				args[2] = CLOPREPAREDDT; 
				args[3] = CLODATEOPENED;
				args[4] = loginBra;
				args[5] = productCode;
				sql = "update T_TRN_CLOSING set CLO_DATE_CLOSED=convert(date,?,'dd/mm/yyyy'),CLO_REMARKS=?,CLO_PREPARED_DT=convert(date,?,'dd/mm/yyyy'),CLO_DATE_OPENED=convert(date,?,'dd/mm/yyyy') where CLO_TRAN_CODE=1 and BRANCH_CODE=? and PRODUCT_CORE_CODE=?";
				result = runner.multipleUpdation(sql,args);
				logger.info("T_TRN_CLOSING updation sql"+sql);
			}
			else
			{
				args = new String[6];
				args[0] = CLODATECLOSED;
				args[1] = ClosedRemarks;
				args[2] = CLOPREPAREDDT;
				args[3] = CLODATEOPENED;
				args[4] = loginBra;
				args[5] = productCode;
				sql = "insert into T_TRN_CLOSING(CLO_TRAN_CODE,CLO_DATE_CLOSED,CLO_REMARKS,CLO_PREPARED_DT,CLO_DATE_OPENED,BRANCH_CODE,PRODUCT_CORE_CODE) values(1,convert(date,?,'dd/mm/yyyy'),?,convert(date,?,'dd/mm/yyyy'),convert(date,?,'dd/mm/yyyy'),?,?)";
				result = runner.multipleInsertion(sql,args);
				logger.info("T_TRN_CLOSING insertion sql"+sql);
			}
			int closem =0;
			int opnm =0;
			int entm =0;
			String closedYear1 = "";
			String closedMonth1 = "";
			
			final String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			for(int i=0;i<MMM.length;i++)
			{
				if(MMM[i].equalsIgnoreCase(closedMonth)){
					closem = i+1;
				}
				if(MMM[i].equalsIgnoreCase(openMonth)){
					opnm = i+1;
				}
				if(MMM[i].equalsIgnoreCase(entryMonth)){
					entm = i+1;
				}
				if(MMM[i].equalsIgnoreCase(closedMonth) && i!=11){
					closedMonth1 = MMM[i+1];
				}
				else if(i==11){
					closedMonth1 = "JAN";
				}
			}
			
			logger.info(" opnm  "+opnm);			
			logger.info(" entm  "+entm);			
			logger.info(" closedYear1  "+closedYear1);
			
			if(closem == 12){
				closedYear1 = Integer.toString(Integer.parseInt(closedYear)+1);
			}
			else{
				closedYear1 = Integer.toString(Integer.parseInt(closedYear)+1);
			}
			
			String futureDate = ""; 
			futureDate = openDay+"-"+closedMonth1+"-"+closedYear;
			String maxRun = "";
			try
			{
				//sql = "select max(RUNNING_SNO)+1 from TRN_CLOSING_DETAILS where BRANCH_CODE='"+loginBra+"'";
				sql = "select max(nvl(RUNNING_SNO,0)+1) from TRN_CLOSING_DETAILS where BRANCH_CODE='"+loginBra+"'";
				maxRun = runner.singleSelection(sql);
				if("DIDN'T SELECTED".equalsIgnoreCase(maxRun) || maxRun==null){
					maxRun = "1";
				}
			}
			catch(Exception e)
			{
				maxRun = "1";
				logger.info("Exception in Selecting  RunNo from TRN_CLOSING_DETAILS"+e.toString());
			}
			
			Date sysDate = null; sysDate = new Date();
			SimpleDateFormat hour = null; hour = new SimpleDateFormat("HH",Locale.ENGLISH);
			SimpleDateFormat min = null; min = new SimpleDateFormat("MM",Locale.ENGLISH);
			SimpleDateFormat sec = null; sec = new SimpleDateFormat("SS",Locale.ENGLISH);
				
			String sysHour = ""; sysHour = hour.format(sysDate);
			String sysMin = ""; sysMin = min.format(sysDate);
			String sysSec = ""; sysSec = sec.format(sysDate);
			
			int intmin = 0; intmin = Integer.parseInt(sysMin);
			int intsec = 0; intsec = Integer.parseInt(sysSec);
			intmin = intmin+intsec/60;
			intsec = intsec%60;
			sysMin = Integer.toString(intmin);
			sysSec = Integer.toString(intsec);

			//CLOPREPAREDDT = entryDay+"-"+ entryMonth +"-"+entryYear+" "+sysHour+":"+sysMin+":"+sysSec;
			//CLOPREPAREDDT = entryYear+"-"+ entryMonth +"-"+entryDay+" "+sysHour+":"+sysMin+":"+sysSec;
			
			String monthDesc = ""; 
			monthDesc = entryMonth+" "+entryYear;
			
			logger.info("monthDesc.."+monthDesc);
			logger.info("maxRun.."+maxRun);
			logger.info("CLO_DATE_OPENED.."+CLODATEOPENED);
			logger.info("CLO_DATE_CLOSED.."+CLODATECLOSED);
			logger.info("futureDate.."+futureDate);
			logger.info("Closed_Remarks.."+ClosedRemarks);
			//logger.info("CLO_PREPARED_DT.."+CLOPREPAREDDT);
			
			args = new String[10];
			args[0] = maxRun;
			args[1] = monthDesc;
			args[2] = CLODATEOPENED;
			args[3] = CLODATECLOSED;
			args[4] = futureDate;
		//	args[5] = CLOPREPAREDDT;
			args[5] = ClosedRemarks;
			args[6] = "Y";
			args[7] = loginBra;
			args[8]= cid;
			args[9]= productCode;
			sql = "insert into TRN_CLOSING_DETAILS values(?, ?, convert(date,?,'dd/mm/yyyy'), convert(date,?,'dd/mm/yyyy'), convert(date,?,'dd/mm/yyyy'), " +
					"sysdate,?,?,?,?,?)";
			runner.multipleInsertion(sql,args);
			logger.info("RoyalTEst New TRn_Closing detials are inserted"+sql);			
			logger.debug(EXIT);
			
			return result;
		}
		
	/*public String[][] select_TRN_Closing_Details(String loginBra)
	{
		String result[][] = new String[0][0];
		String sql = "";

		try
		{
			if(loginBra.indexOf("'")!=-1)
					loginBra = loginBra.replaceAll("'","");
			sql = "select RUNNING_SNO,MONTH_DESC,to_char(START_DATE,'DD-MM-YYYY'),to_char(END_DATE,'DD-MM-YYYY'),to_char(FUTURE_DATE,'DD-MM-YYYY'),to_char(SYS_DATE,'DD-MM-YYYY HH12:MI:SS'),REMARKS from TRN_CLOSING_DETAILS where BRANCH_CODE='"+loginBra+"'";
			result = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception in getting report from trn_closing_details"+e.toString());
		}
		return result;
	}*/
		
	public String[][] selectTRNClosingDetails(String loginBra,final String proCode)throws BaseException
	{
		logger.info("ExportBean select_TRN_Closing_Details method(String,String)");
		logger.debug(ENTER);
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		if(loginBra.indexOf('\'')!=-1){
				loginBra = loginBra.replaceAll("'","");
		}
		args[0] = loginBra;
		args[1] = proCode; 
		sql = "select RUNNING_SNO,MONTH_DESC,to_char(START_DATE,'dd/mm/yyyy'),to_char(END_DATE,'dd/mm/yyyy'),to_char(FUTURE_DATE,'dd/mm/yyyy'),to_char(SYS_DATE,113),REMARKS from TRN_CLOSING_DETAILS where BRANCH_CODE=? and PRODUCT_CORE_CODE=?";
		result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] selectCloseTRN(String loginBra,final String productCode)throws BaseException
	{
		logger.info("ExportBean selectCloseTRN method(String,String)");
		logger.debug(ENTER);
		String result[][] = new String[0][0];
		String sql = "";
		String args[] = new String[2];
		if(loginBra.indexOf('\'')!=-1){
			loginBra = loginBra.replaceAll("'","");
		}
		args[0] = loginBra;
		args[1] = productCode;
		sql = "select replace(to_char( CLO_DATE_CLOSED, 106),' ','/'),CLO_REMARKS,replace(to_char( CLO_PREPARED_DT, 106),' ','/'),replace(to_char( CLO_DATE_OPENED, 106),' ','/') from T_TRN_CLOSING where BRANCH_CODE=? and PRODUCT_CORE_CODE=?";
		result = runner.multipleSelection(sql,args);
		logger.debug(EXIT);
		
		return result;
	}
	
	public String[][] selectCloseTRN(String loginBra)
	{
		String result[][] = new String[0][0];
		String sql = "";
			try
			{
				if(loginBra.indexOf("'")!=-1)
					loginBra = loginBra.replaceAll("'","");
				sql = "select to_char(CLO_DATE_CLOSED,'DD-MON-YYYY'),CLO_REMARKS,to_char(CLO_PREPARED_DT,'DD-MON-YYYY'),to_char(CLO_DATE_OPENED,'DD-MON-YYYY') from T_TRN_CLOSING where BRANCH_CODE='"+loginBra+"'";
				result = runner.multipleSelection(sql);
			}
			catch(Exception e)
			{
					System.out.println("Exception in TRN Selection"+e.toString());e.printStackTrace();
			}

		return result;
	}
	public String validateTrnCloseFields()
	{
		String trn_error="";
		int cm=0,om=0,em=0;
		try
		{
			String[] MMM={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
        	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
        	String s = sf.format(new java.util.Date());
            
			for(int i=0;i<MMM.length;i++)
			{
				if(MMM[i].equalsIgnoreCase(closedMonth))
					cm = i+1;
				if(MMM[i].equalsIgnoreCase(openMonth))
					om = i+1;
				/*if(MMM[i].equalsIgnoreCase(entryMonth))
					em = i+1;*/
			}
			String temp1=closedDay+"/"+cm+"/"+closedYear;
			String temp2=openDay+"/"+om+"/"+openYear;
			
			
			if(checkDate(temp1).equalsIgnoreCase("Invalid"))
					trn_error = trn_error+"* Invalid Close Date, Please Select Valid Date"+"<br/>";
			if(checkDate(temp2).equalsIgnoreCase("Invalid"))
				trn_error = trn_error+"* Invalid Open Date, Please Select Valid Date"+"<br/>";
			/*if(checkDate(entryDay+"/"+em+"/"+entryYear).equalsIgnoreCase("Invalid"))
				trn_error = trn_error+"* Invalid Entry Date, Please Select Valid Date"+"<br/>";*/
			
			long diff1=0;
			long diff=0;
			try
			{
				DateFunction dbr = new DateFunction();
				try
				{
					diff1 = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(temp1));
					diff = dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(temp2));
					System.out.println("open ROyal diff"+diff);
					System.out.println("Close ROyal diff1"+diff1);
				}
				catch(Exception e)
				{
					System.out.println("Exception in Date comp"+e.toString());e.printStackTrace();
				}
				/*if(diff>0)
				{
					trn_error=trn_error+"* Open Date Should Not Exceed Todays Date<br>";
					
				}*/
				
				long daydiff = dbr.getDayDifference(dbr.getCalendar(temp2),dbr.getCalendar(temp1));
				if(daydiff<=0)
				{
					if(Integer.parseInt(closedYear)<=Integer.parseInt(openYear))
						trn_error = trn_error+"* Invalid Close Date, Close Date must be greater than Open Date"+"<br/>";
						System.out.println("Royal;;;;; "+daydiff);
				}
				else
				{
					if(Integer.parseInt(closedYear)==Integer.parseInt(openYear) && cm<om)
						trn_error = trn_error+"* Invalid Close Date, Close Date must br greater than Open Date"+"<br/>";
						System.out.println("Royal;;;;;"+daydiff);
				}
			}	
			catch(Exception e)
			{
				System.out.println("Exception in close Date........."+e.toString());e.printStackTrace();
			}
			
		}
		catch(Exception e)
		{
				System.out.println("Exception in close Dat========>"+e.toString());e.printStackTrace();
		}
		return trn_error;
	}
	public String checkDate(String strDate)
	{
		String returnVal="";
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false);
		df.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
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
	
	public String[][] getBranches()
	{
		String branchIds[][]=new String[0][0];
		String sql="";
		try
		{
		   sql = "select branch_code,branch_name from branch_master";
			  branchIds = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting Branch Master "+e.toString());
	    }
		return branchIds;
	}
	public String[][] getBranches(String branchCode)
	{
		String branchIds[][]=new String[0][0];
		String sql="";
		try
		{
		   sql = "select branch_code,branch_name from branch_master where branch_code in("+branchCode+")";
		   branchIds = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("Exception getting Branch Master "+e.toString());
	    }
		return branchIds;
	}

	public String[][] getMississippiCustomerId()
    {
	   String[][] missippi = new String[0][0];
	   String sql = "";
	   String branchIds[][]=new String[0][0];
	   String bIds = "";
	   try
	   {
		 branchIds = getBranches();
		   if(branch.equals("All"))
		   {
				for(int i=0;i<branchIds.length;i++)
					bIds += "'"+branchIds[i][0]+"',";
				if(bIds.length() > 0)
					bIds = bIds.substring(0,bIds.length()-1);
		   }
		   else 
		   {
				for(int i=0;i<branchIds.length;i++)
					if (branchIds[i][0].equals(branch))
						bIds = "'"+branchIds[i][0]+"'";
		   }
		   sql = "select distinct pos.customer_id,nvl((per.first_name||' '||per.last_name||' '||per.company_name),' '),nvl(bro.company_name,' ')  from position_master pos,personal_info per,login_master log,broker_company_master bro where pos.product_id='"+pid+"' and pos.entry_date between to_date('"+data1+"','dd-MM-yyyy') and to_date('"+data2+"','dd-MM-yyyy')+1 and pos.login_id in(select login_id from login_master where oa_code in(select agency_code from broker_company_master where branch_code in("+bIds+"))) and (per.MISSIPPI_CUSTOMER_CODE is null or per.MISSIPPI_CUSTOMER_CODE='0') and per.customer_id=pos.customer_id and log.login_id=pos.login_id and bro.agency_code=log.oa_code  order by pos.customer_id";
   		   missippi=runner.multipleSelection(sql);
	   }
	   catch(Exception e)
	   {
		 System.out.println("Exception getting Mississippi Customer.Id "+e.toString());
       }
	 return missippi;
   }

  
	public String updateMississippiCode(String missipCode[][])
	{

		String sql="";
		String result="";
		String trnPolicy[][] = new String[0][0];
		String cshCustomer[][] = new String[0][0];
		try{
			for(int i=0;i<missipCode.length;i++)
			{
				if(missipCode[i][1].length() > 0)
				{
					sql = "update personal_info set MISSIPPI_CUSTOMER_CODE='"+missipCode[i][1]+"' where CUSTOMER_ID='"+missipCode[i][0]+"'";
					result=runner.updation(sql);
					sql = "select nvl(pol_customer_id,0),POL_POLICY_ID from t_trn_policy1 where POL_POLICY_ID in (select quote_no from position_master where customer_id='"+missipCode[i][0]+"' and status='P')";
					trnPolicy = runner.multipleSelection(sql);
					for(int t=0;t<trnPolicy.length;t++)
					{
						if(trnPolicy[t][0].equals("0") || trnPolicy[t][0]=="")
						{
							sql = "update t_trn_policy1 set pol_customer_id='"+missipCode[i][1]+"' where POL_POLICY_ID='"+trnPolicy[t][1]+"'";
							result=runner.updation(sql);
						}
					}//trnPolicy
					sql = "select nvl(CSH_CUSTOMER_ID,0),CSH_POLICY_ID from T_MAS_CASH_CUSTOMER1 where CSH_POLICY_ID in (select quote_no from position_master where customer_id='"+missipCode[i][0]+"' and status='P')";
					cshCustomer = runner.multipleSelection(sql);
					for(int c=0;c<cshCustomer.length;c++)
					{
						if(cshCustomer[c][0].equals("0") || cshCustomer[c][0]=="")
						{
							sql = "update T_MAS_CASH_CUSTOMER1 set CSH_CUSTOMER_ID='"+missipCode[i][1]+"' where CSH_POLICY_ID='"+cshCustomer[c][1]+"'";
							result=runner.updation(sql);
						}
					}//CSH
				}//If
			}//For
		}
		catch(Exception e)
		{
			System.out.println("Exception getting Mississippi Code Updation Bean."+e.toString());
		}
		if(result.equals("UPDATED"))
			error = "<br> Mississippi Customer Code Updated Successfully. <br>";
		else
			error = "<br> Error While Updating Mississippi Cutomer Code. <br>";
		return error;
	}

	public String[][] getBranchName(String braCode)
	{
		String sql="";
		String result[][]=new String[0][0];
		sql = "select branch_name,BRANCH_CODE from branch_master where branch_code='"+braCode+"'";
		result = runner.multipleSelection(sql);
		return result;
	}
	
	public String[][] getProductDetail(final String branchCode)
	{
		System.out.println("ExportBean getProductDetail method(String)");
		String result[][]=new String[0][0];
		String qry=""; 
		qry="select rsacode,detail_name from constant_detail where category_id='114' and BRANCH_CODE=? and status='Y' order by rsacode";
		String args[]= new String[1]; args[0] = branchCode;
		result=runner.multipleSelection(qry, args);
		return result;
	}
	public String getClosedRemarks() {
		return ClosedRemarks;
	}
	public void setClosedRemarks(String closedRemarks) {
		ClosedRemarks = closedRemarks;
	}
	public String getCoreProductCode() {
		return coreProductCode;
	}
	public void setCoreProductCode(String coreProductCode) {
		this.coreProductCode = coreProductCode;
	}
	
	public String validateRegisterReportFields()throws BaseException
	{
		String error="";
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
    	sf.setTimeZone(new java.util.SimpleTimeZone(14400000,"GMT")); 	
    	String s = sf.format(new java.util.Date());
        String enteredDate=dobDay+"/"+dobMonth+"/"+dobYear;
		long diff=0;
		try {	
				DateFunction dbr=new DateFunction();
				diff=dbr.getDayDifference(dbr.getCalendar(s),dbr.getCalendar(enteredDate));
		}	
		catch(Exception e)
		{
			System.out.println("ExportBean .."+e.toString());
			e.printStackTrace();
		}
		
		if(diff>0)
			error=error+"<br>*"+runner.getErrormsg("70");
		if(pid.equalsIgnoreCase("Select") ||pid.equalsIgnoreCase("")||pid.length()<=0)
			error=error+"<br>*"+"Please Select the Product Option";
		return error;
	}
}// Class