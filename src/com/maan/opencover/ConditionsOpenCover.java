/**
 * @Program Author name 	---Rajesh R
 * @Creation Date & Time 	---27-04-2007 4 : 11 PM
 * @Company Name			---MaanSarovar Softwate P Limited
 * @Module					---Premium Computation
 */
package com.maan.opencover;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import com.maan.common.error.ErrorInfo;
import com.maan.services.util.runner;
import com.opensymphony.xwork2.ActionContext;

public class ConditionsOpenCover extends ErrorInfo{
	private String 					sqlQuery_	="";
	private String[][] existingCommodity=new String[0][0];
	private String quoteId="";
	private String applicationNo="";
	private String loginCode="";  // updated marimuthu   04-09
	private String sessionId="";
	private String productId="";
	private String companyId="";
	private String proposalNo="";
	private int application_no=0;
	private String stageId="";
	private String ratess=null;
	private String warrate=null;
	private String seaValue=null;
	private String warehouseValue=null;
	private String adminStatus=null;

	//Newly Added By karthy on Aug 13 2007 open cover policy

	private String openCoverNo="";
	private String coverId="";
	private String extraCoverId="";
	private String clausesId="";
	private String amendId="";
	private String wamendId="";
	private String eamendId="";
	private String textamendId = "";
	private String modeOfTransport="";
	private String remarks="";
	private String effectDate="";
	//private String ckbox1="", ckbox2="", ckbox3="", ckbox4="", ckbox5="";
	//private String tabox1="", tabox2 = "", tabox3 ="", tabox4="", tabox5="";


	public void setOpenCoverNo(String openCoverNo){
		this.openCoverNo=openCoverNo;
	}
	public String getOpenCoverNo(){
		return openCoverNo;
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
	
	public String getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
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

	public String getProposalNo() {
		return proposalNo;
	}
	public void setProposalNo(String proposalNo)
	{
	this.proposalNo = proposalNo;
	}

	public String getCoverId() {
		return coverId;
	}
   public void setExtraCoverId(String extracoverId){
	    this.extraCoverId = extraCoverId;
	}

	public String getExtraCoverId() {
		return extraCoverId;
	}
	public void setCoverId(String coverId){
	        this.coverId = coverId;	
	}

	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}

	public void setAdminStatus(String adminStatus){
		this.adminStatus=adminStatus;
	}
	public String getAdminStatus(){
		return adminStatus;
	}

	/*  coding added by marimuthu   - for Warranties*/
    public  HashMap getWarrantiesFromMaster(String loginBra)
    {
	    	HashMap totalConditions=new HashMap();
		    HashMap conditionsList=new HashMap();		    
		    HashMap conditionsIdsList=new HashMap();		   
            String [] args = new String[1];
            String[][] result = new String[0][0];
            try{
                    sqlQuery_="select warranty_id,warranty_description from warranty_master where status in ('Y', 'R') and BRANCH_CODE = ? order by DISPLAY_ORDER";     
                    
                    args[0] = loginBra;  

                    result = runner.multipleSelection(sqlQuery_,args);
                    if(result != null && result.length > 0){                          
                          for(int i=0;i<result.length;i++){
                                conditionsList.put(""+result[i][0], result[i][1]);
					            conditionsIdsList.put(""+i,""+result[i][0]);
                          }
                    }				  
				    conditionsIdsList.put("effectDate","null");
				    totalConditions.put("conditionsList",conditionsList);
				    totalConditions.put("conditionsIdsList",conditionsIdsList);				    
		    }
            catch(Exception exception){
			   exception.printStackTrace();
		    }           
		    return totalConditions;
	}

	public  HashMap getExclusionsFromMaster(String loginBra)
	{
			HashMap totalConditions=new HashMap();
			HashMap conditionsList=new HashMap();			
			HashMap conditionsIdsList=new HashMap();			
            String[] args = new String[1];
            String[][] result = new String[0][0];
	        try{
                    args[0] = loginBra;
	                sqlQuery_="select exclusion_id,exclusion_description from exclusion_master where status in ('Y', 'R') and BRANCH_CODE = ? order by EXCLUSION_ID";
                    result = runner.multipleSelection(sqlQuery_,args);              
                    if(result != null && result.length>0){
                          for(int i=0;i<result.length;i++){
                                conditionsList.put(""+result[i][0],result[i][1]);
						        conditionsIdsList.put(""+i,""+result[i][0]);        
                          }
                    }				
					conditionsIdsList.put("effectDate","null");
					totalConditions.put("conditionsList",conditionsList);
					totalConditions.put("conditionsIdsList",conditionsIdsList);					
			}
            catch(Exception exception){
				exception.printStackTrace();
			}            
			return totalConditions;
		}

	public  HashMap getWSRCCFromMaster(String loginBra, String extraCoverIds)
	{
			HashMap totalConditions=new HashMap();
			HashMap conditionsList=new HashMap();
			HashMap coverList=new HashMap();			
			HashMap conditionsIdsList=new HashMap();		
            String[] args = new String[1];
            String[][] result = new String[0][0];
		    try{
                        args[0] = loginBra;
			            sqlQuery_="select clauses_id,clauses_description,extra_cover_id,cover_id from clauses_master where " +
			            		"status in ('Y','R') and BRANCH_CODE = ? and extra_cover_id in ("+extraCoverIds+
			            		") group by extra_cover_id,clauses_id,clauses_description,cover_id order by clauses_id";			    				        
                        result = runner.multipleSelection(sqlQuery_,args);

                        if(result != null && result.length > 0){
                              for(int i=0;i<result.length;i++){
                                    conditionsList.put(""+result[i][0],result[i][1]);
						            coverList.put("cover"+result[i][0],""+result[i][3]);
						            conditionsIdsList.put(""+i,""+result[i][0]);                                   
                              }
                        }
					    conditionsIdsList.put("effectDate","null");
					    totalConditions.put("conditionsList",conditionsList);
					    totalConditions.put("coverList",coverList);
					    totalConditions.put("conditionsIdsList",conditionsIdsList);					
		}
		catch(Exception exception){
			exception.printStackTrace();
		}        
		return totalConditions;
	}

    public  HashMap getConditionsFromMaster(String loginBra)
    {
		HashMap totalConditions=new HashMap();
		HashMap conditionsList=new HashMap();
		HashMap conditionsIdsList=new HashMap();
		HashMap clausesIdes =  new HashMap();
        String[] args = new String[2];
        String[][] result = new String[0][0];
	    try{
	    	 args[0] = loginBra;
             args[1] =  coverId;
             
             String coverDesc = "", coverDescQry = "";
				   coverDescQry = "select distinct description from cover_master where branch_code = " + loginBra + " and cover_id = " + coverId +"";
				   coverDesc = runner.singleSelection(coverDescQry);
				   System.out.println("coverDesc----->" + coverDesc);
				   
				                     
              //sqlQuery_="select clauses_id,clauses_description,to_char(sysdate,'dd-mm-yyyy') from clauses_master where status in ('Y', 'R') and BRANCH_CODE = ? and cover_id = ? and extra_cover_id='0' ";
              sqlQuery_="SELECT DISTINCT CM.CLAUSES_ID CLAUSES_ID,CM.CLAUSES_DESCRIPTION CLAUSES_DESCRIPTION,TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM CLAUSES_MASTER CM,COMMODITYMASTER C WHERE CM.BRANCH_CODE=? AND CM.COVER_ID=? AND CM.RSACODE IN (SELECT REGEXP_SUBSTR ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), '[^,]+', 1, LEVEL) AS CLAUSESID FROM DUAL CONNECT BY LEVEL <=LENGTH ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))))- LENGTH(REPLACE ((SUBSTR(" + coverDesc + ",1,(INSTR(" + coverDesc + ",'~',1,1)-1))), ',', ''))+ 1) AND CM.EXTRA_COVER_ID='0' AND CM.STATUS IN ('Y','R') AND CM.BRANCH_CODE=C.BRANCH_CODE AND C.COMMODITY_ID IN (SELECT COMMODITY_ID FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO="+proposalNo+" AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO="+proposalNo+")) ORDER BY CM.CLAUSES_ID";
              
              String sydate = "";                    			               
              result = runner.multipleSelection(sqlQuery_,args);

                    if(result != null && result.length > 0)
					{
                        for(int i=0;i<result.length;i++)
						{
                            conditionsList.put(""+result[i][0],result[i][1]);
					        conditionsIdsList.put(""+i,result[i][0]);
					        clausesIdes.put("id",result[i][0]);
					        sydate = result[i][2];		 
                         }
                    }				    
				    conditionsIdsList.put("effectDate","null");
				    totalConditions.put("conditionsList",conditionsList);
				    totalConditions.put("conditionsIdsList",conditionsIdsList);
				    totalConditions.put("clausesId", clausesIdes);				    
	   }
       catch(Exception exception){
			exception.printStackTrace();
	   }     
        return totalConditions;
	}

	public  HashMap getWarrantyFromOpenCoverMaster()
	{
			HashMap conditionsListExisting=new HashMap();	
			int countExis=1;
			String exisDate="";
            String[] args = new String[2];
            String[][] result = new String[0][0];
            try{	
                        args[0] = proposalNo;
                        args[1] = proposalNo;
                        sqlQuery_ = "select occmss.warranty_id,occmss.warranty_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_warranties occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_warranties occms, open_cover_position_master ocpms where ocpms.proposal_no = ?  and ocpms.proposal_no=occms.proposal_no)";	                   
                           
                        result = runner.multipleSelection(sqlQuery_,args);		
                        
                        if(result != null && result.length > 0){
                                for(int i=0;i<result.length;i++){
                                    conditionsListExisting.put("clausesId"+countExis,result[i][0]);
                                    conditionsListExisting.put("description"+countExis,result[i][1]);
                                    exisDate=result[i][2];
									countExis=countExis+1;
                                }
                        }		        
					    countExis=countExis-1;					    
					    conditionsListExisting.put("finalCount",""+countExis);
					    conditionsListExisting.put("effectDate",exisDate);
			}
            catch(Exception exception){				
                    exception.printStackTrace();
			}            
			return conditionsListExisting;
		}

	    public  HashMap getExclusionFromOpenCoverMaster()
		{
			HashMap conditionsListExisting=new HashMap();			
			int countExis=1;
			String exisDate="";
            String[] args = new String[2];
            String[][] result = new String[0][0];
	        try{
                        args[0] = proposalNo;
                        args[1] = proposalNo;
	                    sqlQuery_="select occmss.exclusion_id,occmss.exclusion_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_exclusions occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occms.amend_id) from open_cover_exclusions occms,open_cover_position_master ocpms where ocpms.proposal_no = ? and ocpms.proposal_no=occms.proposal_no)";	

                        result = runner.multipleSelection(sqlQuery_,args);
                        
                        if(result != null && result.length > 0){
                             for(int i=0;i<result.length;i++){
                                conditionsListExisting.put("clausesId"+countExis,result[i][0]);		                       
		                        conditionsListExisting.put("description"+countExis,result[i][1]);
					            exisDate=result[i][2];  
								countExis=countExis+1;
                             }
                        }     
						countExis=countExis-1;
					    conditionsListExisting.put("finalCount",""+countExis);
					    conditionsListExisting.put("effectDate",exisDate);
			    }
                catch(Exception exception){				
                    exception.printStackTrace();
			    }               
    			return conditionsListExisting;
		}

		public String[][] getWsrccFromOpenCoverMasterResult(String extraCoverIds)
		{
			String result[][]=new String[0][0];	
            String[] args = new String[2];
			try{			
                        args[0] = proposalNo;
                        args[1] = proposalNo;
//		                sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.cover_id||occmss.amend_id in(select distinct(cover_id)||max(amend_id) from open_cover_clauses where proposal_no = ? and cover_id not in(0) and occmss.extra_cover_id in ("+extraCoverIds+") group by cover_id) and cover_id not in(0) and occmss.extra_cover_id in ("+extraCoverIds+")";
		                sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate,occmss.COVER_ID from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in(select max(amend_id) from open_cover_clauses where proposal_no = ? and cover_id not in(0) and extra_cover_id in ("+extraCoverIds+")) and cover_id not in(0) and occmss.extra_cover_id in ("+extraCoverIds+")";
		                System.out.println("getWsrccFromOpenCoverMasterResult......"+sqlQuery_);
		                result = runner.multipleSelection(sqlQuery_,args);
			}
            catch(Exception exception){						
                exception.printStackTrace();
			}
			return result;
       }
        
       public HashMap getWsrccFromOpenCoverMaster(String extraCoverIds,String loginBra)
       {
	            HashMap conditionsListExisting=new HashMap();
			    int countExis=1;
			    String exisDate="";
            //    String[] args = new String[4];
                String[] args = new String[2];
                String[][] result = new String[0][0];
                try{
                         /*   args[0] = proposalNo;
                            args[1] = proposalNo;
                            args[2] = loginBra;
							args[3] = loginBra;
                           sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.amend_id in (select max(occmss.amend_id) from open_cover_clauses occmss where occmss.proposal_no = ? and occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0 and BRANCH_CODE = ?) ) and ocpmss.proposal_no=occmss.proposal_no and occmss.extra_cover_id in (select extra_cover_id from clauses_master where extra_cover_id != 0 and BRANCH_CODE = ?)";	
							
							args[0] = proposalNo;
                            args[1] = proposalNo;

							sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.cover_id||occmss.amend_id in(select distinct(cover_id)||max(amend_id) from open_cover_clauses where proposal_no = ? and cover_id not in(0) and occmss.extra_cover_id in ("+s+","+s1+","+s2+") group by cover_id) and cover_id not in(0) and occmss.extra_cover_id in ("+s+","+s1+","+s2+")";
                          	sqlQuery_ = "select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no and occmss.cover_id||occmss.amend_id in(select distinct(cover_id)||max(amend_id) from open_cover_clauses where proposal_no = ? and cover_id not in(0) and extra_cover_id in ("+extraCoverIds+") group by cover_id) and cover_id not in(0) and occmss.extra_cover_id in ("+extraCoverIds+")";*/
							sqlQuery_ = "SELECT OCCMSS.CLAUSES_ID, OCCMSS.CLAUSES_DESCRIPTION, TO_CHAR (OCCMSS.EFFECTIVE_DATE, 'dd-mm-yyyy') AS EFFECTDATE FROM OPEN_COVER_CLAUSES OCCMSS, OPEN_COVER_POSITION_MASTER OCPMSS WHERE OCPMSS.PROPOSAL_NO = ? AND OCPMSS.PROPOSAL_NO = OCCMSS.PROPOSAL_NO AND OCCMSS.AMEND_ID IN ( SELECT MAX (AMEND_ID) FROM OPEN_COVER_CLAUSES WHERE PROPOSAL_NO = OCPMSS.PROPOSAL_NO AND COVER_ID NOT IN (0) AND EXTRA_COVER_ID IN ("+extraCoverIds+")) AND COVER_ID NOT IN (0) AND OCCMSS.EXTRA_COVER_ID IN ("+extraCoverIds+")";

							 result = runner.multipleSelection(sqlQuery_,new String[]{proposalNo});

                              if(result != null && result.length > 0){
                                        for(int i=0;i<result.length;i++){
                                            conditionsListExisting.put("clausesId"+countExis,""+result[i][0]);	                   conditionsListExisting.put("description"+countExis,result[i][1]);
					                        exisDate=result[i][2];		
											countExis=countExis+1;
                                        }
                              }		
							  countExis=countExis-1;
					        conditionsListExisting.put("finalCount",""+countExis);
					        conditionsListExisting.put("effectDate",exisDate);					        
			    }
                catch(Exception exception){
    				        exception.printStackTrace();
	    		}               
		        return conditionsListExisting;
        }

	    public  HashMap getConditionsFromOpenCoverMaster()
	    {
		            HashMap conditionsListExisting=new HashMap();	            
		            int countExis=1;
		            String exisDate="";
                    String[] args = new String[4];
                    String[][] result = new String[0][0];
                    try{
                            args[0] = proposalNo;
                            args[1] = coverId;
                            args[2] = proposalNo;
                            args[3] = coverId;

//                            sqlQuery_="select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no  and occmss.cover_id = ? and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.proposal_no = ? and occms.cover_id = ? and ocpms.proposal_no=occms.proposal_no )";
                            sqlQuery_="select occmss.clauses_id,occmss.clauses_description,to_char(occmss.EFFECTIVE_DATE,'dd-mm-yyyy') as effectDate  from open_cover_clauses occmss,open_cover_position_master ocpmss where ocpmss.proposal_no = ? and ocpmss.proposal_no=occmss.proposal_no  and occmss.cover_id = ? and occmss.amend_id in (select max(occms.amend_id) from open_cover_clauses occms,open_cover_position_master ocpms where ocpms.proposal_no = ? and occms.cover_id = ? and ocpms.proposal_no=occms.proposal_no and occms.extra_cover_id='0') and occmss.extra_cover_id='0'";
							
                            result = runner.multipleSelection(sqlQuery_,args);
                            
                            if(result != null && result.length > 0)
							{
								for(int i=0;i<result.length;i++)
								{
									conditionsListExisting.put("clausesId"+(countExis),result[i][0]); 
									conditionsListExisting.put("description"+(countExis),result[i][1]);
									exisDate = result[i][2];		
									countExis=countExis+1;
								}
                            }     
							countExis=countExis-1;
            				conditionsListExisting.put("finalCount",""+(countExis));
			            	conditionsListExisting.put("effectDate",exisDate);				            
		            }
                    catch(Exception exception){
			                exception.printStackTrace();
		            }   
		            return conditionsListExisting;
	    }

	    public void insertUpdateConditions(HashMap comDetails)
	    {        		
                int rows	=	0;
                String policyDate="";
                int totalCount=0;
                String commodityId="";
                String description="";
                String eDate="";
                Calendar cal = new GregorianCalendar();         
	            int hour12 = cal.get(Calendar.HOUR);                   // 0..11
	            int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	            int min = cal.get(Calendar.MINUTE);                      // 0..59
	            int sec = cal.get(Calendar.SECOND);                      // 0..59
	            int ms = cal.get(Calendar.MILLISECOND);              // 0..999
	            int ampm = cal.get(Calendar.AM_PM);                    // 0=AM, 1=PM  
                String[] args = new String[2];              
	            policyDate		=	"SYSDATE";       
                try{                    
		            if(openCoverNo == null || "null".equals(openCoverNo) || "0".equals(openCoverNo) )  
					{
						System.out.println("insertUpdateConditionsopenCoverNo..."+openCoverNo);
                           args[0] = proposalNo;
                           args[1] = coverId;
				           sqlQuery_ = "delete from open_cover_clauses where proposal_no = ? and cover_id = ?";
				            try{
						          runner.multipleUpdation(sqlQuery_,args);
						    }
						    catch(Exception e){							        
							        e.printStackTrace();
						    }					
		            }	
		            eDate=com.maan.common.util.OracleDateConversion.ConvertDate(getEffectDate())+" "+hour24+":"+min+":"+sec;		
		            amendId=""+getMaximumAmendId(proposalNo);	                     
		          		            
		            sqlQuery_ ="insert into open_cover_clauses (proposal_no,mode_transport_id,cover_id,extra_cover_id,clauses_id,clauses_description,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE('"+eDate+"','dd/MM/yyyy HH24:MI:SS'),?,?)";	            		            
			        
                    args = new String[9];                    
			        totalCount=Integer.parseInt((String)comDetails.get("finalCount"));
			        
			        for(int i=0;i<totalCount;i++)
					{

			                clausesId=(String)comDetails.get("clausesId"+(i+1));
			                description=(String)comDetails.get("description"+(i+1));	  

                            extraCoverId = "0";	
                            remarks = "Remarks";

                            args[0] = proposalNo;
                            args[1] = modeOfTransport;
                            args[2] = coverId;
                            args[3] = extraCoverId;	
                            args[4] = clausesId;
                            args[5] = description.trim();
                            args[6] = amendId;
                            args[7] = remarks;
                            args[8] = "Y";                           
			                
			                try{
			                    runner.multipleInsertion(sqlQuery_,args);
		                    }
                            catch(Exception dd)  {
                                dd.printStackTrace();
                             }
                  }						    
		}
		catch(Exception e){
			e.printStackTrace();
		}       
	}	

	public void insertUpdateWarranties(HashMap comDetails)
	{			
			int rows	=	0;
			String policyDate="";
			int totalCount=0;	
			String commodityId="";			
			String description="";
			String eDate="";
			Calendar cal = new GregorianCalendar();
		    // Get the components of the time
		    int hour12 = cal.get(Calendar.HOUR);            // 0..11
		    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
		    int min      = cal.get(Calendar.MINUTE);             // 0..59
		    int sec       = cal.get(Calendar.SECOND);             // 0..59
		    int ms       = cal.get(Calendar.MILLISECOND);         // 0..999
		    int ampm   = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM		    
            policyDate =	"SYSDATE";     
            String[] args = new String[2];
	        try{
	        	args=new String[1];
                args[0] = proposalNo;
                if(openCoverNo == null || "null".equals(openCoverNo) || "0".equals(openCoverNo) ){
			                sqlQuery_ = "delete from open_cover_warranties where proposal_no = ? ";		           
			                runner.multipleUpdation(sqlQuery_,args);
		        }			   
			    eDate=com.maan.common.util.OracleDateConversion.ConvertDate(getEffectDate())+" "+hour24+":"+min+":"+sec;			    
			    wamendId=""+getMaximumWarrantiesAmendId(proposalNo);	    
			    sqlQuery_ ="insert into open_cover_warranties (proposal_no,mode_transport_id,cover_id,extra_cover_id,warranty_id,warranty_description,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE('"+eDate+"','dd/MM/yyyy HH24:MI:SS'),?,?)";
				
				totalCount=Integer.parseInt((String)comDetails.get("finalCount"));
                 args = new String[9]; 
				for(int i=0;i<totalCount;i++)	{

				        clausesId=(String)comDetails.get("clausesId"+(i+1));
				        description=(String)comDetails.get("description"+(i+1));

                        extraCoverId = "0";	
                        remarks = "Remarks";

                        args[0] = proposalNo;
                        args[1] = "0";
                        args[2] = "0";
                        args[3] = extraCoverId;
                        args[4] = clausesId;
                        args[5] = description;
                        args[6] = wamendId;
                        args[7] = remarks;
                        args[8] = "Y";
                        
				        try{
				                runner.multipleInsertion(sqlQuery_,args);
			            }
                        catch(Exception dd){
                            dd.printStackTrace();
                        }
				}		
			}
			catch(Exception e){
				e.printStackTrace();
			}           
      }

	  public  HashMap getOpencoverFreeText()
	  {	
			HashMap conditionsListExisting =new HashMap();		
            String[] args = new String[2];
            String[][] result = new String[0][0];
			try{
						if(openCoverNo == null || "null".equals(openCoverNo))  {
                                   args[0] = proposalNo;
                                   args[1] = coverId;
								   sqlQuery_ = "delete from open_cover_free_text where proposal_no = ?  and cover_id = ?";			runner.multipleUpdation(sqlQuery_,args);		 
 		                 }
			            int countExis=1;
			            String exisDate=null;

                        args = new String[4];
                        args[0] = proposalNo;
                        args[1] = coverId;
                        args[2] = proposalNo;
                        args[3] = coverId;

                        sqlQuery_ = "select free_text_id, free_text_description, to_char(effective_date,'dd-mm-yyyy') from open_cover_free_text where proposal_no = ? and cover_id = ? and amend_id =(select max(amend_id) from open_cover_free_text where proposal_no = ? and cover_id = ?)";                        				

                        result = runner.multipleSelection(sqlQuery_,args);

                        if(result != null && result.length > 0){
                             for(int i=0;i<result.length;i++){

                                  exisDate  = result[i][2];
						          if(! "9999".equals(result[i][1])) {
						                  conditionsListExisting.put("clausesId"+(i+1),""+result[i][0]);								          
						                  conditionsListExisting.put("description"+(i+1),result[i][1]);						                  
					                }
					                else{
							                exisDate = null;
						            }
                             }
                        }		        
					   countExis=countExis-1;			
				       if(exisDate!=null) {					        
					            conditionsListExisting.put("finalCount",""+countExis);
					            conditionsListExisting.put("effectDate",exisDate);
                       }									
			}
            catch(Exception exception){
				exception.printStackTrace();
			}         
			return conditionsListExisting;
		}

        public void insertUpdateWSRCCText(HashMap comDetails,String loginBra)
        {    		
		            int rows	=	0;
		            String policyDate="";
		            int totalCount=0;
		            String commodityId="";
		            String description="";
		            String eDate="";
		            String chkWarClaus="";
		            String chkClausMasCNT="";
		            int chkWarClausCnt = 0;		
		            Calendar cal = new GregorianCalendar();
	                
	                int hour12 = cal.get(Calendar.HOUR);            // 0..11
	                int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
	                int min = cal.get(Calendar.MINUTE);             // 0..59
	                int sec = cal.get(Calendar.SECOND);             // 0..59
	                int ms = cal.get(Calendar.MILLISECOND);         // 0..999
	                int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM      	                
	                policyDate		=	"SYSDATE";          
                    String[] args = new String[3];
		            try{	

		            	Map<String, Object> session=ActionContext.getContext().getSession();
		            	    totalCount=Integer.parseInt((String)comDetails.get("finalCount"));
			                if(openCoverNo == null || "null".equals(openCoverNo) || "0".equals(openCoverNo) || "GUEST".equals((String)session.get("user")))
							{			         
                        		for(int i=0;i<totalCount;i++)
								{	
					                 coverId = (String)comDetails.get("coverId"+(i+1));
     				                 amendId=""+getMaximumWSRCCID(proposalNo,coverId);
							   
									args[0] = proposalNo;
									args[1] = coverId;
									args[2] = amendId;

									sqlQuery_ = "delete from open_cover_clauses where proposal_no = ? and extra_cover_id != 0  and cover_id = ? and amend_id = ?";
									try
									{
										String sen = runner.multipleUpdation(sqlQuery_,args);						                    
										System.out.println("Delete sqlQuery_..."+sqlQuery_);
									}
									catch(Exception e){						                            
											e.printStackTrace();
									}					                        					                        
								}				                        
		                    }
			                if(getEffectDate()==null || "".equals(getEffectDate())){
			                	Calendar currentDate = Calendar.getInstance(); //Get the current date
			                	SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy"); //format it as per your requirement
			                	setEffectDate(formatter.format(currentDate.getTime()));
			                	
			                }
		                    eDate=com.maan.common.util.OracleDateConversion.ConvertDate(getEffectDate())+" "+hour24+":"+min+":"+sec;
		                    
		                    sqlQuery_ ="insert into open_cover_clauses (proposal_no,mode_transport_id,cover_id,extra_cover_id,clauses_id,clauses_description,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE('"+eDate+"','dd/MM/yyyy HH24:MI:SS'),?,?)";                    
		                    
			                //totalCount=Integer.parseInt((String)comDetails.get("finalCount"));
			                amendId   = ""+getMaximumWSRCCID(proposalNo,coverId);
			                for(int i=0;i<totalCount;i++){
				                    clausesId   = (String)comDetails.get("clausesId"+(i+1));
				                    description= (String)comDetails.get("description"+(i+1));
				                    coverId   = (String)comDetails.get("coverId"+(i+1));				            
//				                    amendId   = ""+getMaximumWSRCCID(proposalNo,coverId);						
				                    /*try{
                                            args = new String[3];
                                            args[0] = proposalNo;
                                            args[1] = coverId;
                                            args[2] = amendId;

				                            chkWarClaus = runner.singleSelection("select count(*) from OPEN_COVER_CLAUSES where proposal_no = ? and cover_id = ? and extra_cover_id!=0 and amend_id =?",args);
											System.out.println("chkWarClaus...."+chkWarClaus);
					                        chkWarClaus = chkWarClaus == null ?"0":chkWarClaus;
					                        args = new String[1];
                                            args[0] = coverId;
                                            
                                            chkClausMasCNT = runner.singleSelection("select count(*) from clauses_master where cover_id=? and extra_cover_id not in (0) and branch_code="+loginBra,args);
					                        chkClausMasCNT = chkClausMasCNT == null ?"0":chkClausMasCNT;
				                    }
				                    catch(Exception e){					                        
					                        e.printStackTrace();
				                    }
				                    chkWarClausCnt = Integer.parseInt(chkWarClaus);
				                    System.out.println("chkWarClausCnt...."+chkWarClausCnt);
				                    System.out.println("chkClausMasCNT...."+chkClausMasCNT);
				                    if(chkWarClausCnt < Integer.parseInt(chkClausMasCNT))
									{*/			                       
                                            extraCoverId = ""+getExtracoverID(clausesId,loginBra);       
                                            remarks = "Remarks";
                                            System.out.println("desc " + description.trim());
                                            args = new String[9];
                                            args[0] = proposalNo;
                                            args[1] = modeOfTransport;
                                            args[2] = coverId;
                                            args[3] = extraCoverId;
                                            args[4] = clausesId;
                                            args[5] = description.trim();
                                            args[6] = amendId;
                                            args[7] = remarks;
                                            args[8] =  "Y";					                        
					                        
                                            try{
                                                runner.multipleUpdation(sqlQuery_,args);
                                            }
                                            catch(Exception dd) {                                            
                                                dd.printStackTrace();
                                            }
//				                    }
			        }			
		}
		catch(Exception e){
			e.printStackTrace();
		}		
      }

        public void insertUpdateFreeText(HashMap comDetails)
        {	             
	  			int rows	=	0;
	  			String policyDate="";
	  			int totalCount=0;
	  			String commodityId="";	
	  			String description="";
	  			String eDate="";
	  			Calendar cal = new GregorianCalendar();	  		    
	  		    int hour12 = cal.get(Calendar.HOUR);            
	  		    int hour24 = cal.get(Calendar.HOUR_OF_DAY);    
	  		    int min = cal.get(Calendar.MINUTE);             
	  		    int sec = cal.get(Calendar.SECOND);             
	  		    int ms = cal.get(Calendar.MILLISECOND);     
	  		    int ampm = cal.get(Calendar.AM_PM);     
                String[] args = new String[0];

	  		    policyDate		=	"SYSDATE";	  			
                try{	  				        
                            if(openCoverNo == null || "null".equals(openCoverNo)){
                                args = new String[2];
                                args[0] = proposalNo;
                                args[1] = coverId;
					            sqlQuery_ = "delete from open_cover_free_text where proposal_no = ?  and cover_id = ?";
                                runner.multipleUpdation(sqlQuery_,args);                               
		                    }
                            
                            args = new String[2];
                            args[0] = proposalNo;
                            args[1] = coverId;
				            sqlQuery_ = "delete from open_cover_free_text where proposal_no = ?  and cover_id = ?";
                            runner.multipleUpdation(sqlQuery_,args);    
                            
	  			            eDate=com.maan.common.util.OracleDateConversion.ConvertDate(getEffectDate())+" "+hour24+":"+min+":"+sec;  			

	  			            textamendId=""+getMaximumtextamendId(proposalNo);	  			            	  			       
                            
	  			            sqlQuery_ ="insert into open_cover_free_text (proposal_no,AMEND_ID,mode_of_transport,cover_id,extra_cover_id,free_text_id,free_text_description,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE('"+eDate+"','dd/MM/yyyy HH24:MI:SS'),?,?)"; 			            
	  					    

	  				        totalCount=Integer.parseInt((String)comDetails.get("finalCount"));	  				        
	  				        if(totalCount == 0) {
						            totalCount = 1;
					        }
	  				        for(int i=0;i<totalCount;i++){                                 
	  				                clausesId=(String)comDetails.get("clausesId"+(i+1));
	  				                description=(String)comDetails.get("description"+(i+1));	
                                    extraCoverId = "0";  
                                     remarks = "Remarks";

                                    args = new String[9];
                                    args[0] = proposalNo;
                                    args[1] = textamendId;
                                    args[2] = modeOfTransport==null?"":modeOfTransport;
                                    args[3] = coverId==null?"0":coverId;
                                    args[4] = extraCoverId==null?"":extraCoverId;
                                    args[5] = clausesId==null?"0":clausesId;
                                    args[6] = description==null?"":description;
                                    args[7] = remarks;
                                    args[8] ="Y";
	  				                try{
	  				                        runner.multipleInsertion(sqlQuery_,args);
	  			                    }
                                    catch(Exception dd)  {
                                       dd.printStackTrace();
                                    }
	  				    }	  				    	  				
	  			}
	  			catch(Exception e){
	  				e.printStackTrace();
	  			}               
        }    

		public void insertUpdateExclusions(HashMap comDetails)
		{		
			int rows	=	0;
			String policyDate="";
			int totalCount=0;
			String commodityId="";						 
			String description="";
			String eDate="";
			Calendar cal = new GregorianCalendar();		    
		    int hour12 = cal.get(Calendar.HOUR);            // 0..11
		    int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
		    int min = cal.get(Calendar.MINUTE);             // 0..59
		    int sec = cal.get(Calendar.SECOND);             // 0..59
		    int ms = cal.get(Calendar.MILLISECOND);         // 0..999
		    int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM

            String[] args = new String[0];
		    policyDate		=	"SYSDATE";
            try{			
                    args = new String[1]; 
                    if(openCoverNo == null || "null".equals(openCoverNo) || "0".equals(openCoverNo) ){
                                    args[0] =  proposalNo;
                                    //args[1] = coverId;
					                sqlQuery_ = "delete from open_cover_exclusions where proposal_no = ?";
					                runner.multipleUpdation(sqlQuery_,args);    
				     }
			        eDate=com.maan.common.util.OracleDateConversion.ConvertDate(getEffectDate())+" "+hour24+":"+min+":"+sec;	
        			eamendId=""+getMaximumExclusionsAmendId(proposalNo);			
			        sqlQuery_ ="insert into open_cover_exclusions (proposal_no,mode_transport_id,cover_id,extra_cover_id,exclusion_id,exclusion_description,AMEND_ID,EFFECTIVE_DATE,REMARKS,STATUS)values(?,?,?,?,?,?,?,TO_DATE('"+eDate+"','dd/MM/yyyy HH24:MI:SS'),?,?)";	        				   
				    totalCount=Integer.parseInt((String)comDetails.get("finalCount"));			
				    for(int i=0;i<totalCount;i++){
			            clausesId=(String)comDetails.get("clausesId"+(i+1));
        	            description=(String)comDetails.get("description"+(i+1));
                        extraCoverId = "0";
                        remarks = "Remarks";

                        args = new String[9];
                        args[0] = proposalNo;
                        args[1] = "0";
                        args[2] = "0";
                        args[3] = extraCoverId;
                        args[4] = clausesId;
                        args[5] = description;
                        args[6] = eamendId;
                        args[7] = remarks;
                        args[8] = "Y";
				        
				        try{
				                runner.multipleInsertion(sqlQuery_,args);
			            }
                        catch(Exception dd)  {
                            dd.printStackTrace();
                        }
                  }				
			}
			catch(Exception e){
				e.printStackTrace();
			}            
    }

	public int getMaximumProposalNo()
	{
		int application_id=1;
        String res = "";
		sqlQuery_ ="select max(proposal_no) from open_cover_clauses" ;		
		try{

             res = runner.singleSelection(sqlQuery_);
             if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") ||res.equalsIgnoreCase("0") ) {
                  application_id=1;
             }
             else{
                  application_id = Integer.parseInt(res) + 1;
             }	
		}
		catch(Exception e){
		     e.printStackTrace();
		}        
		if(application_id==0){
			application_id=1;
		}
		return application_id;
	}

	/*public void print(String methodName,String information,String type){
	        System.out.println(methodName+"<--->"+type+"<---->"+information);
	}*/

    public int getExtracoverID(String clauseId,String loginBra)
    {	
	        int claId=0;	        
	        String sqlQuery_	="";
            String[] args = new String[2];
            String res = "";
	 	    try{
			            if(loginBra.indexOf("'")!=-1)
				                loginBra = loginBra.replaceAll("'","");			 	 	    
                        args[0] = clauseId;
                        args[1] = loginBra;
	 	                sqlQuery_ = "select extra_cover_id from clauses_master where clauses_id = ? and BRANCH_CODE= ? ";	
                        
                        res = runner.singleSelection(sqlQuery_,args);
                        if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("0")){
                              claId = 1; 
                        }
                        else{
                                claId = Integer.parseInt(res);
                        }                
	        }
            catch(Exception ebbb) {
                ebbb.printStackTrace();
		    }          
	        return claId;
     }

     public int getMaximumWarrantiesAmendId(String proposalNo)
     {
	        int s_id=1;      
	        String 					sqlQuery_	="";
            String[] args = new String[1];
            args[0] = proposalNo;            
	        sqlQuery_ ="select max(amend_id) from open_cover_position_master where proposal_No = ?";
            String res = "";	        
	        try{		            
		            res = runner.singleSelection(sqlQuery_,args);
                    if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("")){
                        s_id = 0;   
                    }
                    else{
                    	s_id = Integer.parseInt(res);
                    }            
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }            
	        /*if(s_id==0){
		            s_id=1;
	        }*/
 	        return s_id;
     }

    public int getMaximumtextamendId (String proposalNo)
    {
	        int        s_id=1;
            String 	sqlQuery_	="";            
            String[] args = new String[1];
            args[0] = proposalNo;
	        sqlQuery_ = "select max(amend_id) from open_cover_position_master where proposal_No = ?";	        
            String res = "";
            try{                   
		            res = runner.singleSelection(sqlQuery_,args);
                    if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("")){
                        s_id = 0;   
                    }
                    else{
                         s_id = Integer.parseInt(res);
                    }	            
	        }
	        catch(Exception e){
                 e.printStackTrace();	        
	        }           
	        /*if(s_id==0){
		            s_id=1;
	        }*/
	        return s_id;
        }

        public int getMaximumWSRCCID(String proposalNo ,String coverId)
        {
        	    int s_id = 0; 
	            String sqlQuery_ = "";	    	
                String[] args = new String[1];
                args[0] = proposalNo;
//                args[1] = coverId;
                String res = "";
//	            sqlQuery_ ="select max(amend_id) from open_cover_clauses where proposal_No = ? and cover_id = ?";	    
	            //sqlQuery_ ="select max(amend_id)+1 from open_cover_clauses where proposal_No = ? and cover_id NOT IN (0) AND extra_cover_id IN (1)";	    
                sqlQuery_ ="select max(amend_id) from open_cover_position_master where proposal_No = ? ";                
        	    try{   
                    res = runner.singleSelection(sqlQuery_,args);
                    if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.length()==0){
                        s_id = 0;   
                    }
                    else{
                         s_id = Integer.parseInt(res);
                    }	                       
	            }
	            catch(Exception e){	
                    e.printStackTrace();
	            }
	            /*if(s_id==0){
		            s_id=1;
	            }*/
	            return s_id;
        }

        public int getMaximumExclusionsAmendId(String proposalNo)
        {
	            int s_id=1;
	            String 	sqlQuery_	="";
                String res = "";
                String[] args = new String[1];
                args[0] = proposalNo;
	            sqlQuery_ ="select max(amend_id) from open_cover_position_master where proposal_No = ?";	        
	            try{		                
		                res = runner.singleSelection(sqlQuery_,args);		                
		               if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("")){
                             s_id = 0;   
                        }
                        else{
                             s_id = Integer.parseInt(res);
                        }	    
	            }
	            catch(Exception e){		        
		            e.printStackTrace();
	            }	
	            /*if(s_id==0){
		            s_id=1;
	            }*/
	            return s_id;
        }

       public int getMaximumAmendId(String proposalNo)
      {
	        int s_id=1;
	        String 	sqlQuery_	="";	
            String[] args = new String[1];
            args[0] = proposalNo;
            String res = "";
	        sqlQuery_ ="select max(amend_id) from open_cover_position_master where proposal_No = ?";	        
	        try{	
                    res = runner.singleSelection(sqlQuery_,args);

                     if(res == null || res.equalsIgnoreCase("null") || res.equalsIgnoreCase("") || res.equalsIgnoreCase("")){
                             s_id = 0;   
                     }
                     else{
                             s_id = Integer.parseInt(res);
                     }	     
	        }
	        catch(Exception e){
                e.printStackTrace();		        
	        }	        
	       /* if(s_id==0){
		        s_id=1;
	        }*/
	        return s_id;
    }

    public String[][] getProposalNoDetails(String proposalNo,String loginBra)
	{
		String[][] proposalDetails = new String[0][0];
        String[] args = new String[3];
		try{
            args[0] = proposalNo;
            args[1] = loginBra;
            args[2] = proposalNo;

			proposalDetails=runner.multipleSelection("select a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name from open_cover_sub_detail a,mode_of_transport b,cover_master c where a.proposal_no = ? and c.BRANCH_CODE=b.BRANCH_CODE and c.BRANCH_CODE = ? and a.amend_id=(select max(amend_id) from open_cover_sub_detail where proposal_no = ?) and a.status='Y' and c.cover_id=a.cover_id and b.mode_transport_id=a.mode_transport_id group by a.mode_transport_id,a.cover_id,b.transport_description,c.cover_name order by a.mode_transport_id ,a.cover_id",args);
		}
		catch(Exception e){			
			e.printStackTrace();
		}
		return proposalDetails;
	}
    public String getExtraCoverIds(String proposalNo,String loginBra)
    {
    	String result="";
    	try{
    		result=runner.singleSelection("SELECT RTRIM(XMLAGG(XMLELEMENT(E,A.MODE_TRANSPORT_ID || ',')).EXTRACT('//text()'),',') MODE_TRANSPORT_ID FROM OPEN_COVER_DETAIL A WHERE   A.PROPOSAL_NO = ? AND A.AMEND_ID = (SELECT   MAX (AMEND_ID) FROM   OPEN_COVER_DETAIL WHERE   PROPOSAL_NO = A.PROPOSAL_NO)",new String[]{proposalNo});
    	}
    	catch(Exception e){			
    		e.printStackTrace();
    	}
    	return result;
    }
}