/**
 * @Program Author name 	---Rajesh R
 * @Creation Date & Time 	---27-04-2007 4 : 11 PM
 * @Company Name			---MaanSarovar Softwate P Limited
 * @Module					---Premium Computation
 */

package com.maan.premium.DAO;

import java.util.*;


import proj.sql.QueryBuilder;


import com.maan.services.util.runner;

public class CommodityTransaction
{	
	//Class Variables Declaration	

	private String 	sqlQuery_	= "";
	private String[][] existingCommodity    = new String[0][0];
	private String quoteId                  = "";	
	private String applicationNo            = "";	
	private String loginCode                = "";	
	private String sessionId                = "";	
	private String productId                = "";	
	private String companyId                = "";	
	//private int application_no              = 0;	
	private String stageId                  = "";
	private String ratess                   = null;
	private String warrate                  = null;
	private String seaValue                 = null;
	private String warehouseValue           = null;
	private String adminStatus              = null;
	private String openCoverNo              = "";

	public void setOpenCoverNo(String openCoverNo){
		this.openCoverNo=openCoverNo;
	}
	public String getOpenCoverNo(){
		return openCoverNo;
	}
	
	public void setAdminStatus(String adminStatus){
		this.adminStatus=adminStatus;
	}
	public String getAdminStatus(){
		return adminStatus;
	}
	
	public void insertUpdateCommodity(HashMap comDetails)
	{
		System.out.println("   insertUpdateCommodity   ");
		int rows			= 0;
		String policyDate	= "";
		int totalCount		= 0;		
		String commodityId  = "";
		String fragile      = "";
		String description  = "";
		String sumInsured   = "";		
		Calendar cal        = new GregorianCalendar();
		String commTypeId = "";
		String commodityExcessPremium = "";
	    String args[] = new String[0];

	    // Get the components of the time
	    int hour12 = cal.get(Calendar.HOUR);            // 0..11
	    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	    int min    = cal.get(Calendar.MINUTE);          // 0..59
	    int sec    = cal.get(Calendar.SECOND);          // 0..59
	    int ms     = cal.get(Calendar.MILLISECOND);     // 0..999
	    int ampm   = cal.get(Calendar.AM_PM);           // 0=AM, 1=PM

		policyDate		=	"SYSDATE";
		System.out.println("the SYSTEM DATE IS "+policyDate);
		args = new String[1];
		args[0] = applicationNo;

		sqlQuery_ ="select count(*) from marine_result_details where application_no=?";	
		
		try
		{
			String res = runner.singleSelection(sqlQuery_,args);
			if(res.length()>0)
			{
				rows = Integer.parseInt(res);
				System.out.println("RoyalTest rows true are.."+rows);
			}
			else
			{
				rows = 0;
				System.out.println("RoyalTest rows are.."+rows);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception in PremiumInputsBean1 insertOrUpdateCompanyInfo for Count :"+e.toString());
			e.printStackTrace();
		}
			
		if(rows<=0)
		{
			int msno = 0;
			try
			{
				msno = getMaximumSNO();
			}
			catch(Exception e)
			{
				System.out.println("The Exception occured getting max serial no commodity transaction bean--"+e.toString());
					e.printStackTrace();	
			}
		
		    try
			{
		    	String queryValues[] = new String[14];
			    System.out.println("Inside Insertion of COMMODITY");			
			        		   
			   // sqlQuery_ ="insert into marine_result_details (SNO,COMMODITY_ID,SUM_INSURED,COMPANY_ID, AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,DESCRIPTION,FRAGILE,APPLICATION_NO,product_id,open_cover_no,COMMODITY_TYPE_ID,commodity_Excess_Premium) values(?,?,?,?,?,TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),?,?,?,?,?,?,?,?,?)";	
			    sqlQuery_ ="insert into marine_result_details (SNO,COMMODITY_ID,SUM_INSURED,COMPANY_ID, AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,DESCRIPTION,FRAGILE,APPLICATION_NO,product_id,open_cover_no,COMMODITY_TYPE_ID,commodity_Excess_Premium) values(?,?,?,?,?,"+policyDate+","+policyDate+","+policyDate+","+policyDate+",?,?,?,?,?,?,?,?,?)";
				
				System.out.println("the comDetails"+comDetails.size());
				totalCount = Integer.parseInt((String)comDetails.get("finalCount"));
				System.out.println("the total count is "+totalCount);

				for(int i=0;i<totalCount;i++)
				{
					commodityId=(String)comDetails.get("commodityId"+(i+1));
					sumInsured=(String)comDetails.get("sumInsured"+(i+1));
					description=(String)comDetails.get("description"+(i+1));
					fragile=(String)comDetails.get("fragile"+(i+1));
					commTypeId = (String)comDetails.get("commodityTypeId"+(commodityId));
					commodityExcessPremium = (String)comDetails.get("commodityExcessPremium"+(commodityId));

					StringBuffer sb = new StringBuffer(description);
					description = sb.toString().replaceAll("\r"," ").replaceAll("\n"," ");
					System.out.println("This is for ITEM NO :"+(i+1));			
					System.out.println("commodityId :"+commodityId);
					System.out.println("sumInsured :"+sumInsured);
					System.out.println("CompanyId :1");
					System.out.println("Description :"+description);
					System.out.println("fragile :"+fragile);
					System.out.println("applicationNo :"+applicationNo);
					System.out.println("the Marine Result Details first productId is "+productId);
					System.out.println("the Marine Result Details first OpenCoverNo is "+openCoverNo);
					
					queryValues[0] =  ""+(msno+i);
					queryValues[1] =  ""+Integer.parseInt(commodityId);
					queryValues[2] =  ""+Double.parseDouble(sumInsured);
					queryValues[3] =  "1";
					queryValues[4] =  ""+Integer.parseInt("0");
					queryValues[5] =  "Remarks";
					queryValues[6] =  "Y";
					queryValues[7] =  description;
					queryValues[8] =  fragile;
					queryValues[9] =  ""+Integer.parseInt(applicationNo);
					queryValues[10] =  ""+Integer.parseInt(productId);
					queryValues[11] =  openCoverNo;
					queryValues[12] =  commTypeId;
					queryValues[13] =  commodityExcessPremium;

					runner.multipleUpdation(sqlQuery_,queryValues);
			   }
		    }
		    catch(Exception e)
			{
			    System.out.println("Exception in insertOrUpdateCompanyInfo()  Insertion :"+e.toString());
			    e.printStackTrace();
		    }
		    
			//	Insert into Tracking Master
		    try
			{
				insertTrackingDetails("insideInsert");
		    }
		    catch(Exception ex){
		    	System.out.println("the ERROR in INsertin traking Details"+ex.toString());
		    	ex.printStackTrace();
		    }		
		    //Insert into Tracking Master		
	    }
		else
		{	
			//INSERT UPDATE MARINE_DATA_DETAILS	

			try
			{
				
				System.out.println("Inside UPDATE INSERT Insertion of COMMODITY");
				removeCommodityDetails();				
				System.out.println("AFTER REMOVAL UPDATE INSERT Insertion of COMMODITY");				
														
				int msno = 0;
				try
				{
					msno = getMaximumSNO();
				}
				catch(Exception e)
				{
					System.out.println("The Exception occured getting max serial no commodity transaction bean--"+e.toString());
						e.printStackTrace();	
				}
				//sqlQuery_ ="insert into marine_result_details (SNO,COMMODITY_ID,SUM_INSURED,COMPANY_ID, AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,DESCRIPTION,FRAGILE,APPLICATION_NO,rate,warrate,sea_option_lcl,warehouse_warehouse,product_id,open_cover_no,COMMODITY_TYPE_ID,commodity_Excess_Premium)values(?,?,?,?,?,TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),TO_DATE("+policyDate+",'dd-mm-yyyy hh24:mi:ss'),?,?,?,?,?,?,?,?,?,?,?,?,?)";
				sqlQuery_ ="insert into marine_result_details (SNO,COMMODITY_ID,SUM_INSURED,COMPANY_ID, AMEND_ID,INCEPTION_DATE,EXPIRY_DATE,EFFECTIVE_DATE,ENTRY_DATE,REMARKS,STATUS,DESCRIPTION,FRAGILE,APPLICATION_NO,rate,warrate,sea_option_lcl,warehouse_warehouse,product_id,open_cover_no,COMMODITY_TYPE_ID,commodity_Excess_Premium)values(?,?,?,?,?,"+policyDate+","+policyDate+","+policyDate+","+policyDate+",?,?,?,?,?,?,?,?,?,?,?,?,?)";

				System.out.println("the comDetails"+comDetails.size());
				totalCount = Integer.parseInt((String)comDetails.get("finalCount"));
				System.out.println("the total count is "+totalCount);

				for(int i=0;i<totalCount;i++)
				{
					try
					{
						commodityId=(String)comDetails.get("commodityId"+(i+1));
						sumInsured=(String)comDetails.get("sumInsured"+(i+1));
						description=(String)comDetails.get("description"+(i+1));
						fragile=(String)comDetails.get("fragile"+(i+1));
						commTypeId = (String)comDetails.get("commodityTypeId"+(commodityId));
						commodityExcessPremium = (String)comDetails.get("commodityExcessPremium"+(commodityId));

						ratess="-1";
						warrate="-1";
						seaValue="-1";
						warehouseValue="-1";
						System.out.println("NOW RATE IS 0 "+ratess);
						System.out.println("existingCommodity.length  "+existingCommodity.length);

						if(existingCommodity!=null)
						{
								System.out.println("ROyal Test existingCommodity inside loop  "+existingCommodity.length);
								for(int l=0;l<existingCommodity.length;l++)
								{
									try
									{
							System.out.println("B4Companring-> "+commodityId+"--existingCommodity[l][0])--"+existingCommodity[l][0]);
										if(Integer.parseInt(commodityId)==Integer.parseInt(existingCommodity[l][0]))
										{
											ratess=existingCommodity[l][1];
											warehouseValue=existingCommodity[l][4];
											warrate=existingCommodity[l][2];
											seaValue=existingCommodity[l][3];
										}										
									}
									catch(Exception er)
									{
										er.printStackTrace();
									}
						       }
						}
						
						String queryValues[] = new String[18];
						System .out.println("RATE FROM OLD VALUES   "+ratess);
						System.out.println("This is for ITEM NO :"+(i+1));
						System.out.println("commodityId :"+commodityId);
						System.out.println("sumInsured :"+sumInsured);
						System.out.println("CompanyId :1");
						System.out.println("Description aftre description.........."+description);
						System.out.println("fragile :"+fragile);
						System.out.println("applicationNo :"+applicationNo);
						System.out.println("COMMODITY RATE FROM "+ratess);
						System.out.println("COMMODITY warrate FROM "+warrate);
						System.out.println("COMMODITY seaValue FROM "+seaValue);
						System.out.println("COMMODITY warehouseValue FROM "+warehouseValue);
						System.out.println("the Marine Result Details productId is "+productId);
						System.out.println("the Marine Result Details OpenCoverNo is "+openCoverNo);

						
						queryValues[0] = ""+(msno+i);
						queryValues[1] = ""+Integer.parseInt(commodityId);
						queryValues[2] = ""+Double.parseDouble(sumInsured);
						queryValues[3] = "1";
						queryValues[4] = ""+Integer.parseInt("0");
						queryValues[5] = "Remarks";
						queryValues[6] = "Y";
						StringBuffer sb = new StringBuffer(description);
						description = sb.toString().replaceAll("\r"," ").replaceAll("\n"," ");
						queryValues[7] = description;
						queryValues[8] = fragile;
						queryValues[9] = ""+Integer.parseInt(applicationNo);
						queryValues[10] = ratess;
						queryValues[11] = warrate;
						queryValues[12] = seaValue;
						queryValues[13] = warehouseValue;
						queryValues[14] = ""+Integer.parseInt(productId);
						queryValues[15] = openCoverNo;
						queryValues[16] = commTypeId;
						queryValues[17] = commodityExcessPremium;
						
						runner.multipleUpdation(sqlQuery_,queryValues);
					}
					catch(Exception ee){
						System.out.println(" ALREADY EXIST   "+ee.toString());
						ee.printStackTrace();
					}
				}
			}
			catch(Exception e){
				System.out.println("Exception in UPDATE insertOrUpdateCompanyInfo()  Insertion :"+e.toString());
				e.printStackTrace();
			}
			//Update into Tracking Master
			try{				
				if(getExistingTrackingStatus()>0){
					updateTrackingDetails();
				}
				else{					
					insertTrackingDetails("insideUpdate");
				}
			}
			catch(Exception ex){
				System.out.println("the ERROR in Update traking Details"+ex.toString());
				ex.printStackTrace();
			}			
			//Update into Tracking Master			
		}
	}
	
	public int getMaximumSNO()
	{
		int s_id         = 1;			
		sqlQuery_        = "select nvl(max(sno),0)+1 from marine_result_details";
		try
		{
			String temp = runner.singleSelection(sqlQuery_);
			if(temp.length()>0)
				s_id = Integer.parseInt(temp);								
		}
		catch(Exception e){
			System.out.println("the EXCEPTION IN getMaximumSNO"+e.toString());
			e.printStackTrace();
		}
		if(s_id==0){
			s_id=1;
		}
		return s_id;
	}	
	
	public int getMaximumSerialNO()
	{
		int s_id  = 1;			
		sqlQuery_ = "select nvl(max(serial_no),0)+1 from tracking_master" ;
		try
		{
			String temp = runner.singleSelection(sqlQuery_);
			if(temp.length()>0)
				s_id = Integer.parseInt(temp);
		}
		catch(Exception e){
				System.out.println("the EXCEPTION IN getMaximumSNO"+e.toString());
		}
		if(s_id==0)
		{
			s_id=1;
		}
		return s_id;
	}
	
	public void print(String methodName,String information,String type){	
	       System.out.println(methodName+"<--->"+type+"<---->"+information);		
	}

	public String getApplicationNo() {
		return applicationNo;
	}
	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getLoginCode() {
		return loginCode;
	}
	public void setLoginCode(String loginCode) {
		this.loginCode = loginCode;
	}

	public String getQuoteId() {
		return quoteId;
	}
	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	public void insertTrackingDetails(String insertStatus)
	{		
		String[] qryvalues = new String[8];
		int msno = 0;
		try
		{
			msno = getMaximumSerialNO();
		}
		catch(Exception e){
			System.out.println("Exception getting max serial no in insertTrackingDetails method "+e.toString());
			e.printStackTrace();
		}
		try
		{
			System.out.println("Inside Insertion of TRACKING_MASTER");			
	
			sqlQuery_ ="insert into TRACKING_MASTER (SERIAL_NO,SESSION_ID,LOGIN_ID,STAGE_ID, APPLICATION_NO,PRODUCT_ID,REMARKS,STATUS,START_TIME) values(?,?,?,?,?,?,?,?,SYSDATE)";	

			qryvalues[0] = ""+msno;
			qryvalues[1] = sessionId;
			qryvalues[2] = loginCode;
			qryvalues[3] = "1";
			if("insideUpdate".equalsIgnoreCase(insertStatus))
			{
				System.out.println("STATUS IS :"+insertStatus);
				System.out.println("Application No :"+applicationNo);
				qryvalues[4] = ""+Integer.parseInt(applicationNo);
			}
			else
			{
				System.out.println("Application No :"+applicationNo);
				qryvalues[4] = ""+Integer.parseInt(applicationNo);//Application No
			}
			System.out.println("Product ID :"+productId);
			qryvalues[5] = productId;
			qryvalues[6] = "Remarks";
			qryvalues[7] = "Y";

			runner.multipleInsertion(sqlQuery_, qryvalues);
		}
		catch(Exception e){
			System.out.println("Exception in insertTrackingDetails()  Insertion :"+e.toString());
			e.printStackTrace();
		}
	}
	
	public void removeCommodityDetails()
	{
		
		try
		{
		
			if("admin".equalsIgnoreCase(adminStatus))
			{
				String args[] = new String[1];
				args[0] = applicationNo;
				existingCommodity = runner.multipleSelection("select commodity_id,rate,warrate,sea_option_lcl,warehouse_warehouse from marine_result_details where application_no=?",args);
			}
			sqlQuery_ ="delete from marine_result_details where APPLICATION_NO=? and STATUS='Y'";			
			String args1[] = new String[1];
			args1[0] = applicationNo;
			runner.multipleUpdation(sqlQuery_,args1);
		}
		catch(Exception e){
			System.out.println("Exception in removeDetailsUpdatePS()  Insertion :"+e.toString());
			e.printStackTrace();			
		}
	}
	public void updateTrackingDetails()
	{	
		String qryvalues[] = new String[5];
		try
		{
			
			sqlQuery_ ="update TRACKING_MASTER set STAGE_ID=?,START_TIME=SYSDATE where APPLICATION_NO=? and STATUS=? and session_id=? and login_id=? ";
						
			qryvalues[0] = stageId;
			qryvalues[1] = applicationNo;
			qryvalues[2] = "Y";
			qryvalues[3] = sessionId;
			qryvalues[4] = loginCode;

			runner.multipleUpdation(sqlQuery_, qryvalues);
		}
		catch(Exception e)
		{
			System.out.println("Exception in updateTrackingDetails()  Insertion :"+e.toString());
			e.printStackTrace();
		}
	}

	public int getExistingTrackingStatus()
	{
		int rows=0;
		String qryvalues[] = new String[3];
		String result = "";
		
		qryvalues[0] = applicationNo;
		qryvalues[1] = loginCode;
		qryvalues[2] = sessionId;

		sqlQuery_ ="select count(*) from tracking_master where application_no=? and login_id=? and session_id=? and status='Y'";

		try
		{
			result = runner.singleSelection(sqlQuery_,qryvalues);
			if(result.length()>0 && !result.equalsIgnoreCase("") && result != null && !result.equalsIgnoreCase("null") )
			{
				rows	=	Integer.parseInt(result);
			}
			else
			{
				rows	=	0;
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();			
		}
		return rows;
	}
	
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStageId() {
		return stageId;
	}
	public void setStageId(String stageId) {
		this.stageId = stageId;
	}

	//THIS METHOD HAS BEEN SHIFTED FROM COMMODITYCONTROLLER.JAVA
	public String getErrormsg(String errorCode,String description)
	{
		String result = "";
		String sql = "";
		String args[] = new String[1];
		
		try
		{
			args[0] = errorCode;
			sql = "select error_desc from error_master where error_id=?";
			result = runner.singleSelection(sql,args);
		}
		catch(Exception e)
		{
			System.out.println("Error in ERROR INFO UNDER COMMON ERROR FOLDER ..."+e.toString());
			result="Please Provide Valid Input for "+description;
		}
		return result;
	}

	//Royal world work started
	public  HashMap getCommodityList(String loginBra,String ratingFactorId)
	{		
		String[][] commodityListDetails   = new String[0][0];
		HashMap totalCommodityDetails     = new HashMap();
		HashMap commodityList			  = new HashMap();
		HashMap commodityListExtra	      = new HashMap();
		HashMap commodityListExtraFragile = new HashMap();		
		HashMap commodityIdsList          = new HashMap();
		int count=0;	
		String args[] = new String[0];
		try
		{
			if("3".equalsIgnoreCase(productId))
			{
				args = new String[2];
				args[0] = loginBra;
				args[1] = loginBra;
				//sqlQuery_="select * from commoditymaster cm where cm.status in ('Y','R') and BRANCH_CODE=? and  cm.amend_id||'-'||cm.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and status in ('Y','R','N') and BRANCH_CODE=? group by commodity_id) order by cm.commodity_id";
				sqlQuery_="select COMMODITY_ID,COMMODITY_NAME,COMMODITY_NAME,FRAGILE,RAG,COMMODITY_TYPE_ID from commoditymaster cm where cm.status in ('Y','R') and BRANCH_CODE=? and  cm.amend_id||'-'||cm.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and status in ('Y','R','N') and BRANCH_CODE=? group by commodity_id) order by cm.COMMODITY_NAME";
				commodityListDetails	= runner.multipleSelection(sqlQuery_,args);
			}
			else if("11".equalsIgnoreCase(productId))
			{
				args = new String[4];
				args[0] = loginBra;
				args[1] = loginBra;
				args[2] = openCoverNo;
				args[3] = openCoverNo;
				sqlQuery_="select cm.commodity_id,cm.commodity_name,occomss.COMMODITY_NAME_DESC,occomss.fragile,occomss.rag,occomss.COMMODITY_TYPE_ID from commoditymaster cm,open_cover_commodity_master occomss,open_cover_position_master ocpmss where cm.status in ('Y','R') and cm.BRANCH_CODE=? and cm.amend_id||'-'||cm.commodity_id in(select max(amend_id)||'-'||commodity_id from commoditymaster where to_date(effective_date,'dd-mm-YYYY') <=to_date(SYSDATE,'dd-mm-YYYY') and status in ('Y','R') and BRANCH_CODE=? group by commodity_id) and ocpmss.open_cover_no=? and ocpmss.proposal_no=occomss.proposal_no and occomss.amend_id in (select max(occoms.amend_id) from open_cover_commodity_master occoms,open_cover_position_master ocpms where ocpms.open_cover_no =? and ocpms.proposal_no=occoms.proposal_no and to_date(occoms.effective_date,'dd-mm-YYYY') <= to_date(sysdate,'dd-mm-YYYY')) and cm.commodity_id=occomss.commodity_id order by cm.COMMODITY_NAME ";
				commodityListDetails	= runner.multipleSelection(sqlQuery_,args);
			}
							
		    String commId            = "";
		    String commName          = "";
		    String commDescription   = "";
		    String commFragile       = "";
		    String commRag           = "";
			String commTypeId  = "";
		    String commExcessPremium = "";
			if(commodityListDetails.length > 0)
			{			
					for(int i=0;i<commodityListDetails.length;i++)
					{					
							commId=commodityListDetails[i][0]==null?"":commodityListDetails[i][0];
							commName=commodityListDetails[i][1]==null?"":commodityListDetails[i][1];
						
							commDescription=commodityListDetails[i][2]==null?"":commodityListDetails[i][2];
							commFragile=commodityListDetails[i][3]==null?"":commodityListDetails[i][3];
							commRag=commodityListDetails[i][4]==null?"":commodityListDetails[i][4];
							
							if("11".equalsIgnoreCase(productId))
								commTypeId = commodityListDetails[i][5] ==null ? "":commodityListDetails[i][5];
							
							/*if("3".equalsIgnoreCase(productId))
							{
								commTypeId = commodityListDetails[i][19] ==null ? "":commodityListDetails[i][19];
								commExcessPremium = commodityListDetails[i][22] ==null ? "":commodityListDetails[i][22];
							}
							else if("11".equalsIgnoreCase(productId))
								commTypeId = commodityListDetails[i][5] ==null ? "":commodityListDetails[i][5];

							if("11".equalsIgnoreCase(productId))
							{
								commDescription=commodityListDetails[i][2]==null?"":commodityListDetails[i][2];
								commFragile=commodityListDetails[i][3]==null?"":commodityListDetails[i][3];
								commRag=commodityListDetails[i][4]==null?"":commodityListDetails[i][4];
							}	*/			
							
							commodityList.put(commId,commName);
							commodityIdsList.put(""+i,commId);
							commodityIdsList.put("CT"+commId,commTypeId);
							commodityIdsList.put("EP"+commId,commExcessPremium);
							if("11".equalsIgnoreCase(productId))
							{
								commodityListExtra.put("Description"+commId,commDescription);
								commodityListExtraFragile.put("Fragile"+commId,commFragile);
							}
				  }
				  totalCommodityDetails.put("commodityList",commodityList);
				  totalCommodityDetails.put("commodityIdsList",commodityIdsList);
				  totalCommodityDetails.put("commodityListExtra",commodityListExtra);
				  totalCommodityDetails.put("commodityListExtraFragile",commodityListExtraFragile);
			}
			else{
				 commodityListDetails=new String[0][0];
			}
		}
		catch(Exception exception){
			System.out.println("The Exception occured in getCommodityList--"+exception.toString());
			exception.printStackTrace();			
		}
		return totalCommodityDetails;
	}

	public static int getMaximumApplicationNo(String pid,String loginBra)
	{
		String current_no="";
		int appNo=0;
		try
		{
			/*String args[] = new String[3];
			args[0] = pid;
			args[1] = loginBra;
			args[2] = loginBra;

			current_no=runner.singleSelection("select nvl(max(current_no)+1,max(start_no)) from policyno_generate " +
					"where type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where product_id=? and " +
					"BRANCH_CODE=?) and status='Y' and BRANCH_CODE=?",args);
			
			String args1[] = new String[5];
			args1[0] = current_no;
			args1[1] = current_no;
			args1[2] = pid;
			args1[3] = loginBra;
			args1[4] = loginBra;

			runner.multipleUpdation("update policyno_generate set current_no=?,remarks=? where" +
					" type_id=(select APPLICATION_TYPE_ID from BRANCH_DETAIL where product_id=? and BRANCH_CODE=?) " +
					"and status='Y' and BRANCH_CODE=?",args1);*/
			current_no=runner.singleSelection("SELECT APPLICATION_NO.NEXTVAL FROM DUAL");
			//if(current_no.length()>0&&current_no!=null&&!current_no.equals("null"))
				appNo = Integer.parseInt(current_no);
		}
		catch(Exception e)
		{
			System.out.println("ERROR in getMaxQuote in DATACOLLECTION  "+e.toString());
		}

		return appNo;
	}

	public HashMap getCommoditySumInsuredInfo(String applicationNo,String branch)
	{
		String sql = "";
		String comInfo[][] = new String[0][0];
		HashMap sumInfo = new HashMap();
		HashMap comHas = new HashMap();
		String comName="";
		String sumInsured="";
		double sumInsuredDou=0;
		comHas = getCommodityList(branch,"Commodity");
		try
		{
			String args[] = new String[1];
			args[0] = applicationNo;
			sql="select commodity_id,sum_insured from marine_result_details where application_no=?";
			comInfo = runner.multipleSelection(sql,args);
		}
		catch (Exception e)
		{
			System.out.println("getCommoditySumInsuredInfo "+e.toString());
			e.printStackTrace();
		}

		for (int i=0;i<comInfo.length;i++)
		{
			if(comHas.size() >0)
				comName = comName+comHas.get(comInfo[i][0])+",";
			sumInsuredDou = sumInsuredDou+Double.parseDouble(comInfo[i][1]);
		}

		sumInfo.put("ComName",comName);
		sumInfo.put("sumIns",""+sumInsuredDou);
		sumInfo.put("Count",""+comInfo.length);
		return sumInfo;
	}

	//new for delete application if declined
	public boolean deleteApplicationNo(String appNo)
	{
		boolean flag = false;
		String args[] = new String[1];
		String checkSql = "select count(*) from MARINE_DATA where application_no=?";
		String check = "";
		args[0] = appNo;
		try
		{
			check = runner.singleSelection(checkSql,args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if(check.equals("0"))
		{
			flag = true;
			args[0] = appNo;
			String sql = "delete from MARINE_RESULT_DETAILS where application_no=?";
			try
			{
				runner.multipleUpdation(sql,args);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return flag;
	}

	public boolean getCheckApplicationNo(String appNo)
	{
		String args[] = new String[1];
		boolean flag = false;
		args[0] = appNo;
		String checkSql = "select count(*) from MARINE_DATA where application_no=?";
		String check = "";
		try
		{
			check = runner.singleSelection(checkSql,args);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		if(check.equals("0"))
		{
			flag = true;
		}
		return flag;
	}
}//Class
