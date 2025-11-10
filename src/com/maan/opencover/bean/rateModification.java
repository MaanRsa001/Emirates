package com.maan.opencover.bean;

import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.maan.services.util.runner;
import com.maan.DBCon.DBConnection;

public class rateModification 
{
	public String proposalNo="";
	public String productId="";
	public String effectiveDay="";
	public String effectiveMonth="";
	public String effectiveYear="";
    public List rangeValues;
	
	public List getRangeValues() {
		return rangeValues;
	}
	public void setRangeValues(List rangeValues) {
		this.rangeValues = rangeValues;
	}
	public void setEffectiveDay(String effectiveDay)
	{
		this.effectiveDay=effectiveDay;
	}
	public void setEffectiveMonth(String effectiveMonth)
	{
		this.effectiveMonth=effectiveMonth;
	}
	public void setEffectiveYear(String effectiveYear)
	{
		this.effectiveYear=effectiveYear;
	}
	public void setProposalNo(String proposalNo)
	{
		this.proposalNo=proposalNo;
	}
	public void setProductId(String productId)
	{
		this.productId=productId;
	}
	public String getProposalNo()
	{
		return proposalNo;
	}
	public String getProductId()
	{
		return productId;
	}
	
	public String getEffectiveDay()
	{
		return effectiveDay;
	}
	public String getEffectiveMonth()
	{
		return effectiveMonth;
	}
	public String getEffectiveYear()
	{
		return effectiveYear;
	}

	public java.util.ArrayList getSharedLevel(String mode,String loginBra,String login,String belongingBranch)
	{
		java.util.ArrayList rateModify=new java.util.ArrayList();
		String[][] getsDetail = null;
		String sql = "";
		String args[] = new String[0];
		String values[][] = new String[0][0];
		try
		{
			sql = "select a.no_of_insurance_company,a.rsa_shared_percentage,b.insurance_company_id,b.insurance_company_name from open_cover_master a,open_cover_insurance b where a.proposal_no=? and a.status='Y' and a.product_id=? and a.amend_id=(select max(amend_id) from open_cover_master where proposal_no=?) and b.status='Y'";
			args = new String[3];
			args[0] = proposalNo;
			args[1] = productId;
			args[2] = proposalNo;
			getsDetail = runner.multipleSelection(sql,args);
			rateModify.add(0,getsDetail);
			
			getsDetail=null;
			rateModify.add(1,getsDetail);

			getsDetail = null;
			sql = "select open_cover_country_id_org ,nvl(open_cover_wsrcc_org,'0'),nvl(open_cover_warehouse_org,'0'),open_cover_country_id_dest,nvl(open_cover_wsrcc_dest,'0'),nvl(open_cover_warehouse_dest,'0'),nvl(war_rate,'0') from open_cover_country_master where proposal_no=? and amend_id=(select max(amend_id) from open_cover_country_master where proposal_no=?)";
			args = new String[2];
			args[0] = proposalNo;
			args[1] = proposalNo;
			getsDetail=runner.multipleSelection(sql,args);
			rateModify.add(2,getsDetail);

			getsDetail=null; // 
			sql = "select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name,A.COVER_TYPE_ID from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no=? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no=?) and a.status='Y' and b.BRANCH_CODE = c.BRANCH_CODE and c.BRANCH_CODE=? and c.cover_id=a.cover_id and b.mode_transport_id=a.mode_transport_id group by a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name,A.COVER_TYPE_ID order by a.mode_transport_id ,a.cover_id";
			args = new String[3];
			args[0] = proposalNo;
			args[1] = proposalNo;
			args[2] = belongingBranch;
			getsDetail = runner.multipleSelection(sql,args);
			rateModify.add(3,getsDetail);

			getsDetail=null;
			sql = "select a.commodity_id,a.commodity_name_desc,b.commodity_name,b.COMMODITY_RATE from open_cover_commodity_master a,commoditymaster b where a.proposal_no=? and b.BRANCH_CODE=? and a.amend_id=(select max(amend_id) from open_cover_commodity_master where proposal_no=?) and a.status='Y' and b.commodity_id=a.commodity_id and  b.commodity_id=a.commodity_id and b.amend_id=(select max(amend_id) from commoditymaster where commodity_id=a.commodity_id and BRANCH_CODE=?)";
			args = new String[4];
			args[0] = proposalNo;
			args[1] = belongingBranch;
			args[2] = proposalNo;
			args[3] = belongingBranch;
			getsDetail = runner.multipleSelection(sql,args);
		
			rateModify.add(4,getsDetail);

			getsDetail=null;

		//if("edit".equalsIgnoreCase(mode))
		//{		
				StringBuffer commodity = new StringBuffer();
			 	sql = "SELECT COMMODITY_ID FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=?) AND STATUS ='Y'";
			 	args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				values = runner.multipleSelection(sql,args);
				for (int i = 0; i < values.length; i++) {
					commodity.append(values[i][0] + ",");
				}
				if (commodity.length() > 0) {
					commodity.deleteCharAt(commodity.length() - 1);
				}
			
				sql = "select commodity_id,cover_id,commodity_base_rate,to_char(effective_date,'dd')as days,to_char(effective_date,'mm')as months,to_char(effective_date,'YYYY')as years,COVER_TYPE_ID,NVL(DISCOUNT_PERCENT,'0'),NVL(DISCOUNT_VALUE,'0') from open_cover_commodity_detail where proposal_no=? and amend_id=(select max(amend_id) from open_cover_commodity_detail where proposal_no=?) order by commodity_id";
				args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				getsDetail = runner.multipleSelection(sql,args);
				
				if(getsDetail.length<=0 && "GUEST".equalsIgnoreCase(login) ){ //
					sql = "SELECT CROSS_VOYAGE_TURNOVER FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=?)";
					args = new String[2];
					args[0] = proposalNo;
					args[1] = proposalNo;
					String limit = runner.singleSelection(sql,args);
					if(Double.parseDouble(limit)>0 && Double.parseDouble(limit)<=5000000){
						limit = runner.singleSelection("SELECT PERCENT FROM CONSTANT_DETAIL WHERE CATEGORY_ID='150' AND  CATEGORY_DETAIL_ID='1'");
					}else if(Double.parseDouble(limit)>5000000 && Double.parseDouble(limit)<=25000000){
						limit = runner.singleSelection("SELECT PERCENT FROM CONSTANT_DETAIL WHERE CATEGORY_ID='150' AND  CATEGORY_DETAIL_ID='2'");
					}else if(Double.parseDouble(limit)>25000000){
						limit = runner.singleSelection("SELECT PERCENT FROM CONSTANT_DETAIL WHERE CATEGORY_ID='150' AND  CATEGORY_DETAIL_ID='3'");
					}
					//sql = "SELECT COMMODITY_ID,COVER_ID,COMMODITY_RATE-(COMMODITY_RATE*"+limit+"/100) COMMODITY_RATE,TO_CHAR(EFFECTIVE_DATE,'dd')  AS DAYS,TO_CHAR(EFFECTIVE_DATE,'mm')  AS MONTHS,TO_CHAR(EFFECTIVE_DATE,'YYYY')AS YEARS,COVER_TYPE_ID FROM OPEN_COVER_COMMODITY_RATE WHERE COMMODITY_ID IN ("+commodity+") ORDER BY COMMODITY_ID";
					sql = "SELECT COMMODITY_ID,COVER_ID,COMMODITY_RATE-(COMMODITY_RATE*"+limit+"/100) COMMODITY_RATE,TO_CHAR(EFFECTIVE_DATE,'dd')  AS DAYS,TO_CHAR(EFFECTIVE_DATE,'mm')  AS MONTHS,TO_CHAR(EFFECTIVE_DATE,'YYYY')AS YEARS,COVER_TYPE_ID,NVL(DISCOUNT_PERCENT,'0'),NVL(DISCOUNT_VALUE,'0') FROM OPEN_COVER_COMMODITY_RATE OCCR WHERE  COMMODITY_ID IN ("+commodity+") AND BRANCH_CODE="+loginBra+"  AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COMMODITY_RATE OCC WHERE  OCC.COMMODITY_ID=OCCR.COMMODITY_ID AND  OCC.COVER_ID=OCCR.COVER_ID AND BRANCH_CODE="+loginBra+") ORDER BY  COMMODITY_ID";
					getsDetail = runner.multipleSelection(sql);
				}
			
				rateModify.add(5,getsDetail);
				getsDetail=null;
				sql = "select nvl(insurance_company_id,'0'),nvl(share_percentage,'0') from open_cover_share_percentage where proposal_no=? and amend_id=(select max(amend_id) from open_cover_share_percentage where proposal_no=?)";
				args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				getsDetail = runner.multipleSelection(sql,args);

				rateModify.add(6,getsDetail);

				getsDetail=null;
//				sql = "select distinct(cover_id) from open_cover_clauses where proposal_no=?";
				sql = "SELECT DISTINCT(COVER_ID) FROM OPEN_COVER_CLAUSES WHERE PROPOSAL_NO=? AND EXTRA_COVER_ID=0";
				args = new String[1];
				args[0] = proposalNo;
				getsDetail = runner.multipleSelection(sql,args);

				rateModify.add(7,getsDetail);
				getsDetail=null;
				String selection=null;
				sql = "select count(warranty_id) from open_cover_warranties where proposal_no=?";
				args = new String[1];
				args[0] = proposalNo;
				selection = runner.singleSelection(sql,args);

				rateModify.add(8,selection);
				selection=null;
				sql = "select count(exclusion_id) from open_cover_exclusions where proposal_no=?";
				args = new String[1];
				args[0] = proposalNo;
				selection = runner.singleSelection(sql,args);
				rateModify.add(9,selection);

				getsDetail=null;
				sql = "select extra_cover_id,clauses_id from open_cover_clauses where proposal_no=? and extra_cover_id not in('0') and amend_id in(select max(amend_id) from open_cover_clauses where proposal_no=? and extra_cover_id not in('0')group by cover_id)";
				args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				System.out.println("get War_clausess............."+sql);
				getsDetail=runner.multipleSelection(sql,args);
				rateModify.add(10,getsDetail);

				getsDetail=null;
				selection=null;
				sql = "select nvl(percent,0) from constant_detail where category_id=10 and category_detail_id=1 and status='Y'";
				selection=runner.singleSelection(sql);
				rateModify.add(11,selection);
				
				getsDetail=null;
				selection=null;
				sql = "select nvl(percent,0), (select distinct TRANSPORT_DESCRIPTION from MODE_OF_TRANSPORT m where m.mode_transport_id=category_detail_id),category_detail_id from constant_detail where category_id=130  and status='Y' and branch_code="+loginBra+" order by category_detail_id asc";
				System.out.println(sql+"<<<");
				getsDetail=runner.multipleSelection(sql);
				rateModify.add(12,getsDetail);
				
				getsDetail = null;
				sql = "select open_cover_country_id_org ,nvl(open_cover_wsrcc_org,'0'),nvl(open_cover_warehouse_org,'0'),open_cover_country_id_dest,nvl(open_cover_wsrcc_dest,'0'),nvl(open_cover_warehouse_dest,'0'),nvl(war_rate,'0') from open_cover_country_master where proposal_no=? and amend_id=(select max(amend_id) from open_cover_country_master where proposal_no=?)";
				System.out.println("sql edit ttt:"+ sql);
				args = new String[2];
				args[0] = proposalNo;
				args[1] = proposalNo;
				getsDetail=runner.multipleSelection(sql,args);
				rateModify.add(13,getsDetail);
			//}
		}
		catch(Exception e)
		{
			System.out.println("eRROR in getEXISTING DATa's--------->"+e.toString());
			e.printStackTrace();
		}
		return rateModify;
	}

	public void storedRates(java.util.ArrayList storedValue,String opencoverNo)
	{
		int[]updateCounts=null;
		String qry="";
		java.util.StringTokenizer tokens=null;
		java.sql.Connection conn=null;
		java.sql.PreparedStatement pre=null;
		java.sql.PreparedStatement prepar=null;
		String sql = "";
		String args[] = new String[0];
		try
		{
			String[][] totalValue=null;String effectiveDate=null;
			String covers=null;
			try
			{
			  effectiveDate=""+com.maan.common.util.OracleDateConversion.ConvertDate(effectiveDay+"-"+effectiveMonth+"-"+effectiveYear);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			conn = DBConnection.getInstance().getDBConnection();
			try
			{
				if(opencoverNo==null)
				{
					args = new String[1];
					args[0] = proposalNo;
					sql = "delete from open_cover_commodity_detail where proposal_no=?";
					runner.multipleUpdation(sql,args);
					sql = "delete from open_cover_deductible_master where open_cover_proposal_no=?";
					runner.multipleUpdation(sql,args);
					
				}
				if(rangeValues.size()==0)
				{ 
					args = new String[1];
					args[0] = proposalNo;
					runner.multipleUpdation("update open_cover_deductible_master set status='N' where open_cover_proposal_no=?",args);
				}
				/**** Added for Deductibles**/
			    String amendId = runner.singleSelection("select nvl(max(amend_id)+1,'0') from open_cover_deductible_master where open_cover_proposal_no='"+proposalNo+"'");
				String qry2="insert into open_cover_deductible_master(sno,open_cover_proposal_no" +
				" ,start_range,end_range,excess_amount,amend_id,effective_date,currency_id,currency_name,status,EXCESS_PERCENT) values((select nvl(max(sno)+1,'1') from open_cover_deductible_master where open_cover_proposal_no='"+proposalNo+"'),?,?,?,?,?,'"+effectiveDate+"',?,?,'Y',?)";
							
				
				for(int k=0;k<rangeValues.size();k++){
					Map m = (Map)rangeValues.get(k);
					String currrencyName = runner.singleSelection("select b.SHORT_name from  " +
							"currency_master b where b.COUNTRY_ID=1 AND b.CURRENCY_ID='"+m.get("currencyValue")+"'  " +
									"and b.status='Y' and b.amend_id||b.currency_id  in(select max(amend_id)||currency_id from currency_master where effective_date <=SYSDATE+10/24  and status='Y' and COUNTRY_ID=1 group by currency_id) "); 
					
					System.out.println("Map 1::"+ m.get("start"));
					System.out.println("Map 2::"+ m.get("end"));
					System.out.println("Map 3::"+ m.get("amount"));
					System.out.println("Map 3::"+ m.get("currencyValue"));
					System.out.println("Map 4::"+ m.get("percent")); 
					prepar	=conn.prepareStatement(qry2);
					prepar.setString(1,proposalNo);
					prepar.setString(2, m.get("start").toString());
					prepar.setString(3, m.get("end").toString());
					prepar.setString(4, m.get("amount").toString());
					prepar.setString(5,amendId);
					prepar.setString(6, m.get("currencyValue").toString());
					prepar.setString(7,currrencyName);
					prepar.setString(8,m.get("percent").toString());
					prepar.execute();
				}
				
				System.out.println("Insertion ranges::"+qry2);
				
				/**** End Added for Deductibles**/
				qry="insert into open_cover_commodity_detail(proposal_no,amend_id,commodity_id,cover_id,commodity_base_rate,effective_date,status,COVER_TYPE_ID)values(?,(select nvl(max(amend_id)+1,'0') from open_cover_commodity_detail where proposal_no='"+proposalNo+"'),?,?,?,'"+effectiveDate+"',?,?)";
				
				pre	=conn.prepareStatement(qry);
				totalValue=(String[][])storedValue.get(0);
				covers=null;
				String coverTypeId="0";
				for(int i=0;i<totalValue.length;i++)
				{
					tokens=null;
					tokens=new java.util.StringTokenizer(totalValue[i][1],",");
					while(tokens.hasMoreTokens())
					{
						covers=tokens.nextToken();
						coverTypeId="0";
						if(covers.indexOf("~")!=-1)
						{
							coverTypeId=covers.substring(covers.indexOf('~')+1,covers.length());
							covers=covers.substring(0,covers.indexOf('~'));
						}
						pre.setString(1,proposalNo);
						pre.setString(2,totalValue[i][0]);
						pre.setString(3,(covers.substring(0,covers.indexOf('-'))));
						pre.setString(4,(covers.substring(covers.indexOf('-')+1,covers.length())));
						pre.setString(5,"Y");
						pre.setString(6,coverTypeId);
						pre.addBatch();
					}
				}
				updateCounts = pre.executeBatch();
				
				//DISCOUNT PERCENT
				qry="UPDATE OPEN_COVER_COMMODITY_DETAIL SET DISCOUNT_PERCENT=? WHERE PROPOSAL_NO=? AND COVER_ID=? AND COMMODITY_ID=? AND COVER_TYPE_ID=?";
				pre	=conn.prepareStatement(qry);
				totalValue=(String[][])storedValue.get(4);
				covers=null;
				coverTypeId="0";
				for(int i=0;i<totalValue.length;i++)
				{
					tokens=null;
					tokens=new java.util.StringTokenizer(totalValue[i][1],",");
					while(tokens.hasMoreTokens())
					{
						covers=tokens.nextToken();
						coverTypeId="0";
						if(covers.indexOf("~")!=-1)
						{
							coverTypeId=covers.substring(covers.indexOf('~')+1,covers.length());
							covers=covers.substring(0,covers.indexOf('~'));
						}
						pre.setString(1,(covers.substring(covers.indexOf('-')+1,covers.length())));
						pre.setString(2,proposalNo);
						pre.setString(3,(covers.substring(0,covers.indexOf('-'))));
						pre.setString(4,totalValue[i][0]);
						pre.setString(5,coverTypeId);
						pre.executeUpdate();
					}
				}
				//DISCOUNT RATE
				qry="UPDATE OPEN_COVER_COMMODITY_DETAIL SET DISCOUNT_VALUE=? WHERE PROPOSAL_NO=? AND COVER_ID=? AND COMMODITY_ID=? AND COVER_TYPE_ID=?";
				pre	=conn.prepareStatement(qry);
				totalValue=(String[][])storedValue.get(5);
				covers=null;
				coverTypeId="0";
				for(int i=0;i<totalValue.length;i++)
				{
					tokens=null;
					tokens=new java.util.StringTokenizer(totalValue[i][1],",");
					while(tokens.hasMoreTokens())
					{
						covers=tokens.nextToken();
						coverTypeId="0";
						if(covers.indexOf("~")!=-1)
						{
							coverTypeId=covers.substring(covers.indexOf('~')+1,covers.length());
							covers=covers.substring(0,covers.indexOf('~'));
						}
						pre.setString(1,(covers.substring(covers.indexOf('-')+1,covers.length())));
						pre.setString(2,proposalNo);
						pre.setString(3,(covers.substring(0,covers.indexOf('-'))));
						pre.setString(4,totalValue[i][0]);
						pre.setString(5,coverTypeId);
						pre.executeUpdate();
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			try
			{
				if(opencoverNo==null)
				{
					args = new String[1];
					args[0] = proposalNo;
					sql = "delete from open_cover_share_percentage where proposal_no=?";
					runner.multipleUpdation(sql,args);
				}
					
				pre.clearBatch();
				pre=null;
				covers=null;
				qry="insert into open_cover_share_percentage(proposal_no,amend_id,insurance_company_id,share_percentage,effective_date,status)values(?,(select nvl(max(amend_id)+1,'0') from open_cover_share_percentage where proposal_no='"+proposalNo+"'),?,?,'"+effectiveDate+"',?)";
				pre	=conn.prepareStatement(qry);
				totalValue=(String[][])storedValue.get(2);
				tokens=null;

				for(int i=0;i<totalValue.length;i++)
				{
					if(!"0".equalsIgnoreCase(totalValue[i][0]))
					{
						pre.setString(1,proposalNo);
						pre.setString(2,totalValue[i][0]);
						pre.setString(3,totalValue[i][1]);
						pre.setString(4,"Y");
						pre.addBatch();
					}		
				}
				updateCounts = pre.executeBatch();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			try
			{
				pre.clearBatch();
				pre=null;
				covers=null;

				qry="update open_cover_country_master set war_rate=? where proposal_no=? and amend_id=(select max(amend_id) from open_cover_country_master where proposal_no='"+proposalNo+"')";
				System.out.println("war_rate=>"+storedValue.get(1));
				pre	=conn.prepareStatement(qry);
				pre.setString(1,(String)storedValue.get(1));
				pre.setString(2,proposalNo);
		
				pre.executeUpdate();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		catch(Exception e)
		{
			System.out.println("<ERROR>  While executing  RATEMODIFICATION  ---->"+e.toString());
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" RATEMODIFICATION  Connection is Closed.........");
			}
		}
		System.out.println("<updateCounts>  While executing  RATEMODIFICATION  ---->"+updateCounts);
	}


	/*** End Deductible **/
	public String[][] getWARClause(String loginBra, String extraCoverIds)
	{
		String[][] getTotalWar = new String[0][0];
		String sql = "";
		try
		{
			sql = "select clauses_id,clauses_description,extra_cover_id from clauses_master where status in ('Y', 'R')  and extra_cover_id in ("+extraCoverIds+") and BRANCH_CODE='"+loginBra+"'";
			System.out.println("getWARClause.............."+sql);
			getTotalWar = runner.multipleSelection(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return getTotalWar;
	}
    
	public String[][] getDeductibles(String proposalNo, String effDate)
	{
		//String deductibles = "select START_RANGE, END_RANGE, EXCESS_AMOUNT,CURRENCY_ID  from open_cover_deductible_master where amend_id= (select max(amend_id) from open_cover_deductible_master where open_cover_proposal_no='"+proposalNo+"' and effective_date<=to_date('"+effDate+"','dd/mm/yy')) and open_cover_proposal_no='"+proposalNo+"' and status='Y' and effective_date<=to_date('"+effDate+"','dd/mm/yy')";
		String deductibles = "select START_RANGE, END_RANGE, EXCESS_AMOUNT,CURRENCY_ID,EXCESS_PERCENT from open_cover_deductible_master where amend_id= (select max(amend_id) from open_cover_deductible_master   where open_cover_proposal_no='"+proposalNo+"' and trunc(effective_date) <= to_date('"+effDate+"','dd/mm/yyyy')) and open_cover_proposal_no='"+proposalNo+"' and status='Y' and trunc(effective_date) <= to_date('"+effDate+"','dd/mm/yyyy')";
		System.out.println("Deductibles::"+deductibles);
		String[][] result = runner.multipleSelection(deductibles);
		return result;
	}
	public String getRangeValidation(List<Map> input){
		String error="";
		for (int i=0;i<input.size();i++){
			for(int j=i+1;j<input.size();j++){
				Map map1 = (Map)input.get(i);
				Map map2 = (Map)input.get(j);
				if(i!=j){
					String sel = runner.singleSelection("select 'yes' from dual where "+map2.get("start")+" between "+map1.get("start")+" and "+map1.get("end")+"");
					if(sel!=null && sel.equalsIgnoreCase("yes"))
					error=error+"<br>* Start Range Limit Invalid in Row "+(j+1)+ " with Row "+(i+1)+" Limits ";
					String sel2 = runner.singleSelection("select 'yes' from dual where "+map2.get("end")+" between "+map1.get("start")+" and "+map1.get("end")+"");
					if(sel2!=null && sel2.equalsIgnoreCase("yes"))
					error=error+"<br>* End Range Limit Invalid in Row "+(j+1)+ " with Row "+(i+1)+" Limits ";
				}
			}
		}
		return error;
	}
	public void updateConfirmYN(String proposalNo ,String ratesYN)
	{
		String sql = "";
		String args[] = new String[3];
		try
		{
			sql = "update open_cover_master set CONFIRM_STATUS=? where Proposal_no=? and AMEND_ID = (select max(amend_id) from OPEN_COVER_MASTER where Proposal_no = ?)";
			args[0] = ratesYN;
			args[1] = proposalNo;
			args[2] = proposalNo;
			runner.multipleUpdation(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("   Error in updatoin updateConfirmYN "+e.toString());
			e.printStackTrace();
		}
	}
	public String getConfirmInfo(String proposalNo)
	{
		String sql="SELECT DECODE(CONFIRM_STATUS, NULL, 'NO',CONFIRM_STATUS) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=? and AMEND_ID = (select max(amend_id) from OPEN_COVER_MASTER where Proposal_no = ?)";
		String result=runner.singleSelection(sql, new String[]{proposalNo,proposalNo});
		return result;
	}

}
// Class