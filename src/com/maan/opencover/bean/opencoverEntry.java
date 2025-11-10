package com.maan.opencover.bean;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.maan.services.util.runner;

public class opencoverEntry
{
	private String sqlQuery = "";
	private String proposalNo = "";
	private String CountyId = "";

	public opencoverEntry()
    {
		 System.out.println("opencoverEntry");
	}

	public void setProposalNo(String proposalNo)
	{
		  this.proposalNo = proposalNo;
	}
	public String getProposalNo()
	{
		  return proposalNo;
 	}
	public void setCountyId(String CountyId)
	{
		this.CountyId = CountyId;
		System.out.println("Country ID is"+CountyId);
	}
	public String getCountyId()
	{
		return CountyId;
	}

	public HashMap getBrokerDetail()   /* 21st Aug*/
	{
		HashMap ht = new HashMap();
		String args[] = new String[2];
		try
		{
			sqlQuery = " select nvl(company_name,'0'), nvl(contact_person,'0') from broker_company_master where agency_code = (select oa_code from login_master where login_id = (select broker_id from open_cover_master where proposal_no = ? and amend_id = (select max(amend_id) from open_cover_master where proposal_no = ? )))";
			args[0] = proposalNo;
			args[1] = proposalNo;
			String as2[][] = runner.multipleSelection(sqlQuery,args);
			ht.put("BrInf",as2);
			if(as2.length > 0)
			{
				for(int k=0;k<as2.length;k++)
				{
					ht.put("com_name", as2[0][0]);
					ht.put("con_per", as2[0][1]);
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return ht;
	}

	public String[][] getDetails()
	{
		String[][] result = new String[0][0];
		try
		{
			result = new String[0][0];
			sqlQuery = "select a.proposal_no,nvl(a.open_cover_no,'0'),nvl(to_char(b.policy_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.policy_end_date,'DD-mm-YYYY'),' '),nvl(first_name,nvl(company_name,' ')) from open_cover_position_master a, open_cover_master b,personal_info c where a.status='Y' and a.proposal_no = b.proposal_no and b.amend_id = (select max(amend_id) from open_cover_master b where b.proposal_no = a.proposal_no) and c.customer_id =b.customer_id order by a.proposal_no";
			result = runner.multipleSelection(sqlQuery);
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getDetailsApproved()
	{
		String[][] result = null;
		try
		{
		result = new String[0][0];
		sqlQuery = "select a.proposal_no,nvl(a.open_cover_no,'0'),nvl(to_char(b.policy_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.policy_end_date,'DD-mm-YYYY'),' '),nvl(first_name,nvl(company_name,' ')) from open_cover_position_master a, open_cover_master b,personal_info c where a.status='P' and a.proposal_no = b.proposal_no and b.amend_id = (select max(amend_id) from open_cover_master b where b.proposal_no = a.proposal_no) and c.customer_id =b.customer_id order by a.proposal_no";
		result = runner.multipleSelection(sqlQuery);
		}
		catch(Exception e1) 
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}

	public String[][] getportfolioDetails(String bid,String policyno)
	{
	  String[][] result = new String[0][0];
	   try
	    {
		   String balanceAmount = "trim(to_char(nvl(d.CROSS_VOYAGE_TURNOVER,0)-nvl(d.UTILIZED_AMOUNT,0),'999G999G999G999G999G999G999G999G990'))";
		   if(policyno.equalsIgnoreCase(""))
			{
		    String args[] = new String[1];
		    args[0] = bid;
//			sqlQuery="select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.open_cover_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-mm-YYYY'),' '),nvl(c.first_name,c.company_name),d.customer_id,b.amend_id,d.MISSIPPI_OPENCOVER_NO from open_cover_position_master a, open_cover_detail b,personal_info c,open_cover_master d where a.status='P' and a.proposal_no = b.proposal_no and a.expiry_date >=(select sysdate from dual) and b.amend_id = (select max(amend_id) from open_cover_detail  where proposal_no = a.proposal_no) and a.proposal_no in (select (proposal_no) from open_cover_master where broker_id=?) and d.proposal_no=a.proposal_no and c.customer_id =d.customer_id and d.amend_id=(select max(amend_id) from open_cover_master where proposal_no=a.proposal_no) and (a.admin_status is null or a.admin_status='Y') order by a.proposal_no desc";
//			sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-mm-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-mm-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE A.STATUS = 'P' AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND D.BROKER_ID=? ORDER BY A.PROPOSAL_NO DESC";
			//sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-mm-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-mm-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO, D.debit_note_no, D.credit_note_no FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y' AND ORIGINAL_POLICY_NO IS NOT NULL)) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND D.BROKER_ID=? ORDER BY A.PROPOSAL_NO DESC";
		    sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-mm-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-mm-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO, D.debit_note_no, D.credit_note_no,"+balanceAmount+" FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y' AND ORIGINAL_POLICY_NO IS NOT NULL)) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND D.BROKER_ID=? ORDER BY A.PROPOSAL_NO DESC";
			result = runner.multipleSelection(sqlQuery,args);
			}
		    else if(!policyno.equalsIgnoreCase("")&& bid.equalsIgnoreCase("select"))
		    {
			   String args[] = new String[1];
		       args[0] = "%"+policyno+"%";
		       sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-mm-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-mm-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO, D.debit_note_no, D.credit_note_no,"+balanceAmount+" FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y' AND ORIGINAL_POLICY_NO IS NOT NULL)) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND A.OPEN_COVER_NO LIKE ? ORDER BY A.PROPOSAL_NO DESC";
			   result = runner.multipleSelection(sqlQuery,args);
		    }
			else 
			{
		         String args[] = new String[3];
		         args[0] = bid;
		         args[1] = "%"+policyno+"%";
		         args[2] = "%"+policyno+"%";
		         sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-mm-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-mm-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO, D.debit_note_no, D.credit_note_no,"+balanceAmount+" FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y' AND ORIGINAL_POLICY_NO IS NOT NULL)) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND D.BROKER_ID=? AND (A.OPEN_COVER_NO LIKE ? or A.ORIGINAL_POLICY_NO LIKE ? ) ORDER BY A.PROPOSAL_NO DESC";
			     result = runner.multipleSelection(sqlQuery,args);
			}
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}

  public java.util.ArrayList getQuoteDetails(String bid)
  {
	  java.util.ArrayList values=new java.util.ArrayList();
	  String[][] result = new String[0][0];
	  String args[] = new String[1];
	  try
	  {
		result = new String[0][0];
		args[0]= bid;
		sqlQuery = "select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.policy_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.policy_end_date,'DD-mm-YYYY'),' '),nvl(first_name,company_name),RENEWAL_STATUS,b.MISSIPPI_OPENCOVER_NO from open_cover_position_master a, open_cover_master b,personal_info c where a.status='Y' and a.original_policy_no is null and a.proposal_no = b.proposal_no and b.amend_id = (select max(amend_id) from open_cover_master b where b.proposal_no = a.proposal_no) and a.proposal_no in (select proposal_no from open_cover_master where broker_id=?) and c.customer_id =b.customer_id order by a.proposal_no desc";
		result = runner.multipleSelection(sqlQuery,args);
		values.add(0,result);
		result = null;
		sqlQuery = "select a.proposal_no,nvl(to_char(b.open_cover_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-mm-YYYY'),' ') from open_cover_position_master a,open_cover_detail b where a.status='Y' and a.original_policy_no is null and b.proposal_no=a.proposal_no and b.amend_id=(select max(amend_id) from open_cover_detail where proposal_no=a.proposal_no)";
		result = runner.multipleSelection(sqlQuery);
		values.add(1,result);
	}
	catch(Exception e1)
	{
		System.out.println("Error in selection"+e1);
		e1.printStackTrace();
	}
    return values;
  }

	public String getAdminBranch(String loginId)
	{
		String adminBranch="";
		String allBranches[][] = new String[0][0];
		String branches="";
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginId;
			sql = "select nvl(BRANCH_CODE,'020') from LOGIN_MASTER where login_id=?";
			adminBranch = runner.singleSelection(sql,args);
			System.out.println("Query 1==>"+runner.singleSelection(sql,args));
			System.out.println("Query 2==>"+runner.singleSelection("select nvl(BRANCH_CODE,'020') from LOGIN_MASTER where login_id='"+loginId+"'",new String[0]));
			System.out.println("Admin Branch==>"+adminBranch+"==>LoginID==>"+loginId);			
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin getAdminBranch data"+e.toString());
			e.printStackTrace();
		}

		try
		{
			sql = "select BRANCH_CODE,BRANCH_NAME from branch_master where status='Y'";
			allBranches = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			System.out.println("allBranches "+e.toString());
			e.printStackTrace();
		}
		try
		{
			System.out.println("Admin Branch==>"+adminBranch);
			if("All".equalsIgnoreCase(adminBranch))
			{
				System.out.println("Inside All");
				for(int i=0;i<allBranches.length;i++)
					branches = branches+"'"+allBranches[i][0]+"',";
				branches = branches.substring(0,branches.length()-1);
			}
			else if(adminBranch.length()>0)
			{
				System.out.println("Inside Admin Branch");
				adminBranch = adminBranch.replaceAll(",","','");
				branches = "'"+adminBranch+"'";
			}
			else
			{
				System.out.println("Inside Else");
				for(int i=0;i<allBranches.length;i++)
					branches = branches+"'"+allBranches[i][0]+"',";
				if(branches.length()>1)
					branches = branches.substring(0,branches.length()-1);
			}
			System.out.println("Branches==>"+branches);
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin getAdminBranch data"+e.toString());
			e.printStackTrace();
		}
		return branches;
	}

/*** Lapsed Portfolio in admin side ***/
	public String[][] getAdminLapsedPortfolioDetails(String bid)
	{
		String[][] result = new String[0][0];
		String args[]= new String[1];
	
		try
		{
			result = new String[0][0];
//			sqlQuery="select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.open_cover_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-mm-YYYY'),' '),nvl(c.first_name,c.company_name),d.customer_id,b.amend_id,d.MISSIPPI_OPENCOVER_NO,a.admin_status from open_cover_position_master a, open_cover_detail b,personal_info c,open_cover_master d where a.status in('P','D') and a.proposal_no = b.proposal_no and (a.expiry_date <=(select sysdate from dual) or a.admin_status='N') and  b.amend_id = (select max(amend_id) from open_cover_detail  where proposal_no = a.proposal_no) and a.proposal_no in (select (proposal_no) from open_cover_master where broker_id=?) and d.proposal_no=a.proposal_no and c.customer_id =d.customer_id and d.amend_id=(select max(amend_id) from open_cover_master where proposal_no=a.proposal_no) order by a.proposal_no desc";
			sqlQuery="select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.open_cover_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-mm-YYYY'),' '),nvl(c.first_name,c.company_name),d.customer_id,b.amend_id,d.MISSIPPI_OPENCOVER_NO,a.admin_status from open_cover_position_master a, open_cover_detail b,personal_info c,open_cover_master d where a.status in('P') and a.proposal_no = b.proposal_no and ((trunc(a.expiry_date)-trunc(sysdate))<=7 or a.admin_status='N') and nvl(a.renewal_status,' ')!='R' and  b.amend_id = (select max(amend_id) from open_cover_detail  where proposal_no = a.proposal_no) and a.proposal_no in (select (proposal_no) from open_cover_master where broker_id=?) and d.proposal_no=a.proposal_no and c.customer_id =d.customer_id and d.amend_id=(select max(amend_id) from open_cover_master where proposal_no=a.proposal_no) And A.amend_id=(Select max(Amend_id) from open_cover_position_master oc WHere nvl(A.original_policy_no,A.open_Cover_no)=nvl(oc.original_policy_no,oc.open_cover_no) and a.status=oc.status) order by a.proposal_no desc";
			args[0] = bid;
			result = runner.multipleSelection(sqlQuery,args);
		}
		catch(Exception e1) 
		{
			System.out.println("getAdminLapsedPortfolioDetails"+e1);
			e1.printStackTrace();
		}
		return result;
	}

	public boolean openCoverActiveDeactive(String proposalNo, String ad)
	{
		String sql = "";
		String args[] = new String[0];
		try
		{
			if(ad.equalsIgnoreCase("Active"))
			{
				args = new String[1];
				args[0] = proposalNo;
				sql="update open_cover_position_master set expiry_date=(select add_months(sysdate,12)-1 from dual) where Proposal_no= ?";
				runner.multipleUpdation(sql,args);
				args = new String[1];
				args[0] = proposalNo;
				sql="update OPEN_COVER_DETAIL set OPEN_COVER_END_DATE=(select add_months(sysdate,12)-1 from dual) where Proposal_no= ?";
				runner.multipleUpdation(sql,args);
				args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				sql ="update open_cover_master set POLICY_END_DATE=(select add_months(sysdate,12)-1 from dual) where Proposal_no=? and AMEND_ID = (select max(amend_id) from OPEN_COVER_MASTER where Proposal_no = ?)";
				runner.multipleUpdation(sql,args);
			}
			else if (ad.equalsIgnoreCase("DeActive"))
			{
				args = new String[1];
				args[0] = proposalNo;
				sql = "update open_cover_position_master set status='D' where Proposal_no = ?";
				runner.multipleUpdation(sql,args);
			}
			else 
				return false;
		}
		catch(Exception e)
		{
			System.out.println("Exception in openCoverActiveDeactive"+e.toString());
			e.printStackTrace();
		}
		return true;
	}

	/*** Get DeActivated OpenCovers ***/

	public String[][] getDeActvatedPortfolioDetails(String bid)
	{
		String[][] result = new String[0][0];
		String args[] = new String[1];
		try
		{
			args[0] = bid;
			sqlQuery="select distinct(a.proposal_no),nvl(a.open_cover_no,'0'),nvl(to_char(b.open_cover_start_date,'DD-mm-YYYY'),' '),nvl(to_char(b.open_cover_end_date,'DD-mm-YYYY'),' '),nvl(c.first_name,c.company_name),d.customer_id,b.amend_id,d.MISSIPPI_OPENCOVER_NO from open_cover_position_master a, open_cover_detail b,personal_info c,open_cover_master d where a.status='D' and a.proposal_no = b.proposal_no and b.amend_id = (select max(amend_id) from open_cover_detail  where proposal_no = a.proposal_no) and a.proposal_no in (select (proposal_no) from open_cover_master where broker_id=?) and d.proposal_no=a.proposal_no and c.customer_id =d.customer_id and d.amend_id=(select max(amend_id) from open_cover_master where proposal_no=a.proposal_no) order by a.proposal_no desc";
			result = runner.multipleSelection(sqlQuery,args);
		}
		catch(Exception e1) 
		{
			System.out.println("Error in selection"+e1.toString());
			e1.printStackTrace();
		}
		return result;
	}

	public void openCoverDeactivate(String proNo)
	{
		String sql = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = proNo;
			sql = "update OPEN_COVER_POSITION_MASTER set admin_status='N' where PROPOSAL_NO=?";
			runner.multipleUpdation(sql,args);
			sql="select status from open_cover_position_master where PROPOSAL_NO=?";
			String status= runner.singleSelection(sql,args); 
			if("Y".equalsIgnoreCase(status)){
				sql = "Select original_policy_no,amend_id-1  from OPEN_COVER_POSITION_MASTER where proposal_no=?";
				String result[][] = runner.multipleSelection(sql,args);
				if("0".equals(result[0][1])) {
					sql = "update OPEN_COVER_POSITION_MASTER set endt_status='' where  OPEN_COVER_NO=? and amend_id=?";
				}
				else {
					sql = "update OPEN_COVER_POSITION_MASTER set endt_status='' where  original_policy_no=? and amend_id=?";
				}
				args = new String[]{result[0][0],result[0][1]};
				runner.multipleUpdation(sql,args);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void openCoverActivate(String proNo)
	{
		String sql = ""; 
		String args[] = new String[1];
		try
		{
			args[0] = proNo;
			sql = "update OPEN_COVER_POSITION_MASTER set admin_status='Y' where PROPOSAL_NO=?";
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<LinkedHashMap<String,Object>> getOpenCoversByExpiryDate(String branchCode)
	{
		List<LinkedHashMap<String,Object>> result = null;
		try
		{
			sqlQuery="SELECT ROWNUM SNO,X.* FROM (SELECT NVL (A.OPEN_COVER_NO, '0') OPEN_COVER_NO, A.PROPOSAL_NO PROPOSAL_NO, E.COMPANY_NAME BROKER, NVL (C.FIRST_NAME, C.COMPANY_NAME) CUSTOMER, NVL (TO_CHAR (A.INCEPTION_DATE, 'DD-mm-YYYY'), ' ') INCEPTION_DATE, NVL (TO_CHAR (A.EXPIRY_DATE, 'DD-mm-YYYY'), ' ') EXPIRY_DATE FROM OPEN_COVER_POSITION_MASTER A, PERSONAL_INFO C, OPEN_COVER_MASTER D, BROKER_COMPANY_MASTER E WHERE A.STATUS = 'P' AND (TRUNC (A.EXPIRY_DATE) - TRUNC (SYSDATE)) BETWEEN 0 AND 60 AND MOD ( (TRUNC (A.EXPIRY_DATE) - TRUNC (SYSDATE)), 10) = 0 AND A.PROPOSAL_NO = D.PROPOSAL_NO AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = D.PROPOSAL_NO) AND C.CUSTOMER_ID = D.CUSTOMER_ID AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND E.AGENCY_CODE = (SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID = D.BROKER_ID) AND D.BRANCH_CODE=? ORDER BY EXPIRY_DATE) X";
			result = (List<LinkedHashMap<String,Object>>)runner.getOrderedResultList(sqlQuery, new String[]{branchCode});
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
	public List<LinkedHashMap<String,Object>> getOpenCoversByStartDate(String branchCode)
	{
		List<LinkedHashMap<String,Object>> result = null;
		try
		{
			sqlQuery="SELECT ROWNUM SNO,X.* FROM (SELECT NVL (A.OPEN_COVER_NO, '0') OPEN_COVER_NO, A.PROPOSAL_NO PROPOSAL_NO, E.COMPANY_NAME BROKER, NVL (C.FIRST_NAME, C.COMPANY_NAME) CUSTOMER, NVL (TO_CHAR (A.INCEPTION_DATE, 'DD-mm-YYYY'), ' ') INCEPTION_DATE, NVL (TO_CHAR (A.EXPIRY_DATE, 'DD-mm-YYYY'), ' ') EXPIRY_DATE FROM OPEN_COVER_POSITION_MASTER A, PERSONAL_INFO C, OPEN_COVER_MASTER D, BROKER_COMPANY_MASTER E WHERE A.STATUS = 'P' AND (TRUNC(A.INCEPTION_DATE+((EXTRACT (YEAR FROM SYSDATE)-EXTRACT (YEAR FROM A.INCEPTION_DATE))*365))-TRUNC(SYSDATE)) BETWEEN 0 AND 60 AND MOD ( (TRUNC(A.INCEPTION_DATE+((EXTRACT (YEAR FROM SYSDATE)-EXTRACT (YEAR FROM A.INCEPTION_DATE))*365))-TRUNC(SYSDATE)), 10) = 0 AND TRUNC (A.EXPIRY_DATE) >= TRUNC (SYSDATE) AND A.PROPOSAL_NO = D.PROPOSAL_NO AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = D.PROPOSAL_NO) AND C.CUSTOMER_ID = D.CUSTOMER_ID AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND E.AGENCY_CODE = (SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID = D.BROKER_ID) AND D.BRANCH_CODE=? ORDER BY EXPIRY_DATE) X";
			result = (List<LinkedHashMap<String,Object>>)runner.getOrderedResultList(sqlQuery, new String[]{branchCode});
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
	public String[][] getBranchInfoByOpenCoverExpiryDate()
	{
		String[][] result = new String[0][0];
		try
		{
			sqlQuery="SELECT DISTINCT D.BRANCH_CODE, F.EMAIL_TO, F.EMAIL_CC,B.BRANCH_NAME FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_MASTER D, MAIL_DETAILS F, BRANCH_MASTER B WHERE A.STATUS = 'P' AND (TRUNC (A.EXPIRY_DATE) - TRUNC (SYSDATE)) BETWEEN 0 AND 60 AND MOD ( (TRUNC (A.EXPIRY_DATE) - TRUNC (SYSDATE)), 10) = 0 AND A.PROPOSAL_NO = D.PROPOSAL_NO AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = D.PROPOSAL_NO) AND F.BRANCH_CODE = D.BRANCH_CODE AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND B.BRANCH_CODE=D.BRANCH_CODE ORDER BY D.BRANCH_CODE";
			result = runner.multipleSelection(sqlQuery);
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
	public String[][] getBranchInfoByOpenCoverStartDate()
	{
	  String[][] result = new String[0][0];
	   try
	    {
			sqlQuery="SELECT DISTINCT D.BRANCH_CODE, F.EMAIL_TO, F.EMAIL_CC, B.BRANCH_NAME FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_MASTER D, MAIL_DETAILS F, BRANCH_MASTER B WHERE A.STATUS = 'P' AND ( trunc(A.INCEPTION_DATE + ((EXTRACT (YEAR FROM SYSDATE) - EXTRACT (YEAR FROM A.INCEPTION_DATE)) * 365)) - trunc(SYSDATE)) BETWEEN 0 AND 60 AND mod((trunc(A.INCEPTION_DATE+((EXTRACT (YEAR FROM SYSDATE)-EXTRACT (YEAR FROM A.INCEPTION_DATE))*365))-trunc(SYSDATE)),10) = 0 AND TRUNC (A.EXPIRY_DATE) >= TRUNC (SYSDATE) AND A.PROPOSAL_NO = D.PROPOSAL_NO AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = D.PROPOSAL_NO) AND F.BRANCH_CODE = D.BRANCH_CODE AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND B.BRANCH_CODE = D.BRANCH_CODE ORDER BY D.BRANCH_CODE";
			result = runner.multipleSelection(sqlQuery);
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
	public String getBranchCode(String loginId)
	{
		String branches="";
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = loginId;
			sql = "SELECT BRANCH_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?";
			branches = runner.singleSelection(sql,args);
			System.out.println("Branch Code==>"+branches);			
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin getAdminBranch data"+e.toString());
			e.printStackTrace();
		}
		return branches;
	}
	public String getBranchPrefix(String branchCode)
	{
		String branches="";
		String args[] = new String[1];
		String sql = "";
		try
		{
			args[0] = branchCode;
			sql = "SELECT BRANCH_PREFIX FROM BRANCH_MASTER WHERE BRANCH_CODE=?";
			branches = runner.singleSelection(sql,args);
			System.out.println("Branch Prefix==>"+branches);			
		}
		catch(Exception e)
		{
			System.out.println("Exception in gettin getAdminBranch data"+e.toString());
			e.printStackTrace();
		}
		return branches;
	}
	public String[][] getonlineDetails(String search,String policyno,String mail)
	{
	  String[][] result = new String[0][0];
	   try
	    {
		   if(search.equalsIgnoreCase("4"))
			{
			    String args[] = new String[2];
			    args[0] = policyno+"%";
			    args[1] = ("%"+mail+"%").trim();
				sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-MON-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-MON-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND C.FIRST_NAME LIKE ? AND C.EMAIL LIKE ? ORDER BY A.PROPOSAL_NO DESC";
				result = runner.multipleSelection(sqlQuery,args);
			}
		    else if(search.equalsIgnoreCase("3"))
		    {
			   String args[] = new String[2];
		       args[0] = policyno+"%";
		       args[1] = ("%"+mail+"%").trim();
		       sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-MON-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-MON-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND A.OPEN_COVER_NO LIKE ? AND C.EMAIL LIKE ? ORDER BY A.PROPOSAL_NO DESC";
			   result = runner.multipleSelection(sqlQuery,args);
		    }
		    else if(search.equalsIgnoreCase("2"))
			{
				String args[] = new String[2];
		        args[0] = policyno+"%";
		        args[1] = ("%"+mail+"%").trim();
			    sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-MON-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-MON-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND A.PROPOSAL_NO LIKE ? AND C.EMAIL LIKE ? ORDER BY A.PROPOSAL_NO DESC";
				result = runner.multipleSelection(sqlQuery,args);
			}
		    else 
			{
		         String args[] = new String[1];
		         args[0] = policyno;
		         sqlQuery="SELECT DISTINCT (A.PROPOSAL_NO), NVL (A.OPEN_COVER_NO, '0'), NVL (TO_CHAR (B.OPEN_COVER_START_DATE, 'DD-MON-YYYY'), ' '), NVL (TO_CHAR (B.OPEN_COVER_END_DATE, 'DD-MON-YYYY'), ' '), NVL (C.FIRST_NAME, C.COMPANY_NAME), D.CUSTOMER_ID, B.AMEND_ID, D.MISSIPPI_OPENCOVER_NO,A.STATUS, A.ENDT_STATUS, A.ORIGINAL_POLICY_NO FROM OPEN_COVER_POSITION_MASTER A, OPEN_COVER_DETAIL B, PERSONAL_INFO C, OPEN_COVER_MASTER D WHERE (A.STATUS = 'P' OR (A.STATUS='Y')) AND A.PROPOSAL_NO = B.PROPOSAL_NO AND TRUNC(A.EXPIRY_DATE) >= TRUNC(SYSDATE) AND B.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_DETAIL WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND A.PROPOSAL_NO IN (SELECT (PROPOSAL_NO) FROM OPEN_COVER_MASTER WHERE BROKER_ID = D.BROKER_ID) AND D.PROPOSAL_NO = A.PROPOSAL_NO AND C.CUSTOMER_ID = D.CUSTOMER_ID AND D.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO = A.PROPOSAL_NO) AND (A.ADMIN_STATUS IS NULL OR A.ADMIN_STATUS = 'Y') AND A.OPEN_COVER_NO = ? ORDER BY A.PROPOSAL_NO DESC";
			     result = runner.multipleSelection(sqlQuery,args);
			}
		}
		catch(Exception e1)  
		{
			System.out.println("Error in selection"+e1);
			e1.printStackTrace();
		}
		return result;
	}
}//Class 


