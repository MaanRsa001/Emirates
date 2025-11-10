package com.maan.common;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.maan.services.util.runner;

public class UploadValidation extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(UploadValidation.class);
	private SimpleJdbcCall validateDates;
	public String tableName =""; 
	public String moveDetailId="";
	public String getTableName(String movedetailId) {
		return selectTable( movedetailId);
	    }
	public void setTableName( String tableName) {
		this.tableName = tableName;
	}
	public String getMoveDetailId() {
		return moveDetailId;
	}
	public void setMoveDetailId(String moveDetailId) {
		this.moveDetailId = moveDetailId;
	}
	public String getTableName() {
		return tableName;
	}
	public  String selectTable(String movedetailId){
	    String selTables = "select destination_table from xl_movement_detail where movement_detail_id = ?";
		String table = (String)this.mytemplate.queryForObject(selTables, new Object[] { movedetailId }, String.class);
	  	return table;
	
	}
	public void numberValidation(String movedetailId, String tranId){
    	logger.info("numberValidation Enter");
    	//SqlRowSet primaryValues = selectPrimaryValues(movedetailId, primary, tableName, tranId);
    	String selectFieldsNumber = "select dest_field from xl_master_table_config where movement_detail_id=? and status='Y' and data_type='NUMBER'";
		//String numberFields[][] = runner.multipleSelection(selectFieldsNumber);
    	try {
			
			//Number Validation
			String validNumbers = "";
			SqlRowSet numberFields =this.mytemplate.queryForRowSet(selectFieldsNumber,new Object[] {movedetailId});
			
				while(numberFields.next()){
					validNumbers =	"update "+tableName+" w " +
					 		" set validation_error=validation_error||',"+numberFields.getString(1)+" should be number'" +
					 		" where  "+numberFields.getString(1)+
					 		" in (select case when instr(translate( "+numberFields.getString(1)+","+
							"'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'," +
							"'XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX'),'X')=0 " +
							" then 'no' else "+numberFields.getString(1)+"  end FROM "
							+tableName+") and transaction_id = '"+tranId+"'";
					System.out.println(validNumbers);
					this.mytemplate.update(validNumbers);
						
				}
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		logger.info("numberValidation Exit");
	}
    public void mandatoryValidation(String movedetailId, String tranId){
    	logger.info("mandatoryValidation Enter");
    	String selectFieldsNumber = "select dest_field,source_field from xl_master_table_config where movement_detail_id=? and status='Y' and (mandatory_status='Y' or mandatory_status='P')";
		try {
			
			//Number Validation
			String mandatories = "";
			SqlRowSet mandatoryFields =this.mytemplate.queryForRowSet(selectFieldsNumber,new Object[] {movedetailId});
			
				while(mandatoryFields.next()){
					mandatories =	"update "+tableName+" w " +
					 		" set validation_error=validation_error||',"+mandatoryFields.getString(2)+" is mandatory'" +
					 		" where  "+mandatoryFields.getString(1)+
					 		" is null and transaction_id = '"+tranId+"'";
					System.out.println(mandatories);
					this.mytemplate.update(mandatories);
						
				}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		logger.info("mandatoryValidation Exit");
	}
    public void referenceValidation(String movementDetailId, String tranId,String proposal,String branch, String openCoverNo){
    	logger.info("referenceValidation Enter");
    	// reference table validation R
    	String selectReference = "select dest_field,master_data_table,master_data_field,mandatory_status,condition,source_field from xl_master_table_config where movement_detail_id=? and status='Y' and MASTER_DATA_CHECK IS NOT NULL and MASTER_DATA_CHECK ='Y'";
    	SqlRowSet referFields =this.mytemplate.queryForRowSet(selectReference,new Object[]{movementDetailId});
		
	    while ( referFields.next()) {
	    	String dbColumnName =  referFields.getString(1)==null?"":referFields.getString(1);
	    	String REFERENCE_TABLE = referFields.getString(2)==null?"":referFields.getString(2);
			String REFERENCE_COLUMN = referFields.getString(3)==null?"":referFields.getString(3);
			String MANDATORY_STATUS = referFields.getString(4)==null?"":referFields.getString(4);
			String CONDITION = referFields.getString(5)==null?"":referFields.getString(5);
			String sourceField = referFields.getString(6)==null?"":referFields.getString(6);
			
			if (REFERENCE_TABLE != null
					&& MANDATORY_STATUS.trim().equalsIgnoreCase("Y") 
					&& (!( (dbColumnName.trim().equalsIgnoreCase("PACKING_DETAILS")) 
						|| (dbColumnName.trim().equalsIgnoreCase("COVER")) 
						|| (dbColumnName.trim().equalsIgnoreCase("VOYAGE_TO")) 
						|| (dbColumnName.trim().equalsIgnoreCase("CONVEYANCE"))
						|| (dbColumnName.trim().equalsIgnoreCase("INCOTERMS")) 
						|| (dbColumnName.trim().equalsIgnoreCase("VOYAGE_FROM"))
						))) {
				
				String	query = "update " + tableName
				+ " set validation_error=validation_error||',"
				+ sourceField + " Not Exists' " + "where " + dbColumnName
				+ " NOT IN  "
				+ "  (select distinct " + tableName + "."
				+ dbColumnName + " from " + tableName
				+ " ," + REFERENCE_TABLE + " where "
				+ REFERENCE_TABLE + "." + REFERENCE_COLUMN
				+ "=" + tableName + "." + dbColumnName
				+ " "+(CONDITION.equalsIgnoreCase("")?"":" and "+CONDITION)+" and transaction_id='"+tranId+"') and transaction_id='"+tranId+"'";
				logger.info("validateRecords() query --" + query);
				if(CONDITION.equalsIgnoreCase("") || !(CONDITION.toUpperCase()).contains("?")){
				this.mytemplate.update(query);
				}
				else if((CONDITION.toUpperCase()).contains(".BRANCH_CODE")){
					this.mytemplate.update(query,new Object[]{branch,branch});
					}
				else{ 
				this.mytemplate.update(query,new Object[]{proposal,proposal});
				}
			}
			//COVER
			
				if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("VOYAGE_FROM")){

					// Mode of Transport
					String modeQry = "select MODE_TRANSPORT_ID from OPEN_COVER_DETAIL where proposal_no=? and  amend_id =(select max(amend_id ) from OPEN_COVER_DETAIL where proposal_no=?)" ;
					SqlRowSet modeValuesRS= this.mytemplate.queryForRowSet(modeQry, new Object[]{proposal,proposal});
				    StringBuffer modeValues = new StringBuffer(); 
					while (modeValuesRS.next()){
						if(modeValuesRS.isLast()){
						modeValues.append(modeValuesRS.getString(1)); 
						}
						else{
						modeValues.append(modeValuesRS.getString(1)+",");
						}
				    }
					
					String modeQryUpdate = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
					" xl_opencover_raw.validation_error||',CONVEYANCE Not Exists' " +
					" where XL_OPENCOVER_RAW.CONVEYANCE IS NOT NULL AND XL_OPENCOVER_RAW.CONVEYANCE not in " +
					"(select TRANSPORT_DESCRIPTION from MODE_OF_TRANSPORT where " +
					" TRANSPORT_DESCRIPTION=XL_OPENCOVER_RAW.CONVEYANCE and " +
					" branch_code=?" +
					" and MODE_TRANSPORT_ID in ("+modeValues+")) and transaction_id=?";
					System.out.println("Qry Reference->"+modeQryUpdate);
					this.mytemplate.update(modeQryUpdate, new Object[]{branch,tranId});
					
					//Cover
				    String coverQry = "select COVER_ID from OPEN_COVER_SUB_DETAIL where proposal_no=? and  amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no=?)" ;
					SqlRowSet coverValuesRS= this.mytemplate.queryForRowSet(coverQry, new Object[]{proposal,proposal});
				    StringBuffer coverValues = new StringBuffer(); 
					while (coverValuesRS.next()){
						if(coverValuesRS.isLast()){
							coverValues.append(coverValuesRS.getString(1)); 
						}
						else{
							coverValues.append(coverValuesRS.getString(1)+",");
						}
				    }
					
					String updateInvalidCover = "update  XL_OPENCOVER_RAW set validation_error" +
							"=validation_error||',COVER Not Exists' where COVER IS NOT NULL AND COVER NOT IN  "+
							" (select COVER_NAME from COVER_MASTER where COVER_NAME=" +
							"  XL_OPENCOVER_RAW.COVER AND " +
							" BRANCH_CODE=? AND " +
							" COVER_MASTER.COVER_ID in("+coverValues+") and " +
							"  MODE_TRANSPORT_ID=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT" +
							"	where TRANSPORT_DESCRIPTION= XL_OPENCOVER_RAW.CONVEYANCE AND BRANCH_CODE=? AND STATUS='Y')" +
							" ) and transaction_id=?";
					
		            System.out.println("Update invalid cover qry::"+updateInvalidCover);
					this.mytemplate.update(updateInvalidCover, new Object[]{branch,branch,tranId});
				}
				// 	PACKING_DETAILS
				System.out.println("Proposal NO::"+proposal);
				if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("VOYAGE_FROM")){
					// COnveyance
					String conveyanQry = "select COVER_TYPE_ID from OPEN_COVER_SUB_DETAIL where proposal_no=? and COVER_TYPE_ID IS NOT NULL AND  amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no=?)" ;
					SqlRowSet conveyanValuesRS= this.mytemplate.queryForRowSet(conveyanQry, new Object[]{proposal,proposal});
				    StringBuffer conveyanValues = new StringBuffer(); 
					while (conveyanValuesRS.next()){
						if(conveyanValuesRS.isLast()){
							conveyanValues.append(conveyanValuesRS.getString(1)); 
						}
						else{
							conveyanValues.append(conveyanValuesRS.getString(1)+",");
						}
				    }
					
					String selectCoverNames ="select distinct COVER_NAME from OPEN_COVER_SUB_DETAIL,COVER_MASTER where COVER_MASTER.amend_id=(select max(amend_id) from COVER_MASTER where branch_code=?) and OPEN_COVER_SUB_DETAIL.cover_type_id is not null  and OPEN_COVER_SUB_DETAIL.proposal_no=? and OPEN_COVER_SUB_DETAIL.COVER_ID= COVER_MASTER.COVER_ID and  OPEN_COVER_SUB_DETAIL.COVER_ID IS NOT NULL AND  OPEN_COVER_SUB_DETAIL.amend_id =(select max(amend_id ) from OPEN_COVER_SUB_DETAIL where proposal_no=?)";
					SqlRowSet selectCoverRS= this.mytemplate.queryForRowSet(selectCoverNames, new Object[]{branch, proposal,proposal});
				    StringBuffer coverNames = new StringBuffer(); 
					while (selectCoverRS.next()){
						if(selectCoverRS.isLast()){
							coverNames.append("'"+selectCoverRS.getString(1)+"'"); 
						}
						else{
							coverNames.append("'"+selectCoverRS.getString(1)+"',");
						}
				    }
			
					String updateConveyanDefault = "";
					if(coverNames.toString().equalsIgnoreCase("")){
						
						String updateInvalidConveyan = "update  XL_OPENCOVER_RAW set validation_error" +
						"=validation_error||', PACKING DETAILS Not Exists' where  XL_OPENCOVER_RAW.PACKING_DETAILS IS NOT NULL " +
						" and transaction_id=?";
						System.out.println("Update invalid conveyan qry::"+updateInvalidConveyan);
						this.mytemplate.update(updateInvalidConveyan, new Object[]{tranId});
						
						updateConveyanDefault = "update XL_OPENCOVER_RAW set XL_OPENCOVER_RAW.PACKING_DETAILS ='0' where validation_error is null and XL_OPENCOVER_RAW.PACKING_DETAILS IS NULL and transaction_id=?";
						this.mytemplate.update(updateConveyanDefault, new Object[]{tranId});
						
					}else{
						updateConveyanDefault = "update XL_OPENCOVER_RAW set XL_OPENCOVER_RAW.PACKING_DETAILS ='0' where validation_error is null and XL_OPENCOVER_RAW.PACKING_DETAILS IS NULL AND  XL_OPENCOVER_RAW.COVER not  in ("+coverNames+") and transaction_id=?";
						this.mytemplate.update(updateConveyanDefault, new Object[]{tranId});
						String updateInvalidConveyan = "update  XL_OPENCOVER_RAW set validation_error" +
						"=validation_error||', PACKING DETAILS Not Exists' where  (XL_OPENCOVER_RAW.COVER in ("+coverNames+") or XL_OPENCOVER_RAW.PACKING_DETAILS<>'0' )AND PACKING_DETAILS NOT IN  "+
						" (select CONVDESC from CONVEYAN_MASTER where CONVDESC=" +
						"  XL_OPENCOVER_RAW.PACKING_DETAILS AND " +
						" BRANCH_CODE=? and XL_OPENCOVER_RAW.COVER in ("+coverNames+") AND " +
						" CONVEYAN_MASTER.CONV_MEAN in("+conveyanValues+") and  " +
						"  MODE_TRANSPORT_ID=(select MODE_TRANSPORT_ID from MODE_OF_TRANSPORT" +
						"	where TRANSPORT_DESCRIPTION= XL_OPENCOVER_RAW.CONVEYANCE AND BRANCH_CODE=? AND STATUS='Y'))  AND transaction_id=?";
						System.out.println("Update invalid conveyan qry::"+updateInvalidConveyan);
						this.mytemplate.update(updateInvalidConveyan, new Object[]{branch,branch,tranId});
					}
					String updateInvalidLC = "update  XL_OPENCOVER_RAW set validation_error" +
							"=validation_error||', LC NO. Not Exists' where LC_NO NOT IN  "+
							" (select LC_NUMBER from OPEN_COVER_LC_MASTER where LC_NUMBER=" +
							"  XL_OPENCOVER_RAW.LC_NO AND " +
							" OPEN_COVER_NO=? AND " +
							" STATUS='Y' AND AMEND_ID =(SELECT MAX(AMEND_ID) FROM OPEN_COVER_LC_MASTER WHERE OPEN_COVER_NO=?)) and LC_NO IS NOT NULL AND transaction_id=?";
					
		            System.out.println("Update invalid LC qry::"+updateInvalidLC+"::Open COver::"+openCoverNo);
					this.mytemplate.update(updateInvalidLC, new Object[]{openCoverNo,openCoverNo,tranId});
				}
			
			//WSRCC
				
			if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("WSRCC")){

					String queryforWAR="UPDATE xl_opencover_raw SET xl_opencover_raw.validation_error=xl_opencover_raw.validation_error||',WAR COVER Not Exists' " +
						" where xl_opencover_raw.WSRCC is null and transaction_id=?";	
					System.out.println("SALE TERM QRRY:1"+queryforWAR);
					this.mytemplate.update(queryforWAR, new Object[]{tranId});
					
					queryforWAR="UPDATE xl_opencover_raw SET xl_opencover_raw.WSRCC=UPPER(xl_opencover_raw.WSRCC)" +
						" where xl_opencover_raw.WSRCC is null and transaction_id=?";	
					System.out.println("SALE TERM QRRY:1"+queryforWAR);
					this.mytemplate.update(queryforWAR, new Object[]{tranId});
					
					queryforWAR="UPDATE xl_opencover_raw SET xl_opencover_raw.validation_error=xl_opencover_raw.validation_error||',WAR COVER Not Exists' " +
					" xl_opencover_raw.WSRCC!=(SELECT (case when OCM.WRSC_YN ='Y' then 'YES' else 'NO' end) FROM OPEN_COVER_MASTER OCM"+
					"  WHERE OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE OPENCOVER_POLICY_NUMBER=OCM.MISSIPPI_OPENCOVER_NO) and xl_opencover_raw.WSRCC is not null and transaction_id=?";
					System.out.println("SALE TERM QRRY:1"+queryforWAR);
					this.mytemplate.update(queryforWAR, new Object[]{tranId});
			}
			 			
			//Sale Term
			if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("VOYAGE_FROM")){
				 
				 
				String saleTermQry = "select sale_term_id from OPEN_COVER_SALE_TERM_MASTER where proposal_no=? and  amend_id =(select max(amend_id ) from OPEN_COVER_SALE_TERM_MASTER where proposal_no=?)" ;
				String saletermValues=(String) this.mytemplate.queryForObject(saleTermQry, new Object[]{proposal,proposal},String.class);
				String saleTermToleranceQry = "select nvl(replace(tolerance_name,' ',''),'000') tolerance_name from OPEN_COVER_SALE_TERM_MASTER where  proposal_no=? and  amend_id =(select max(amend_id ) from OPEN_COVER_SALE_TERM_MASTER where proposal_no=?)" ;
				String saletoleranceValues=(String) this.mytemplate.queryForObject(saleTermToleranceQry, new Object[]{proposal,proposal},String.class);
					if(saletoleranceValues!=null && !saletoleranceValues.trim().equalsIgnoreCase("") && !saletoleranceValues.trim().equalsIgnoreCase("000")){
				String saletermTolerance = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
				" xl_opencover_raw.validation_error||',BASIS OF VALUATION  Not Exists' " +
				" where  XL_OPENCOVER_RAW.BASIS_OF_VALUATION IS NOT NULL AND   XL_OPENCOVER_RAW.BASIS_OF_VALUATION  not in " +
				" ('"+saletoleranceValues.replaceAll(",", "','")+"') and (length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))>1 and transaction_id=?";
				System.out.println("SALE TERM QRRY:1"+saletermTolerance);
				this.mytemplate.update(saletermTolerance, new Object[]{tranId});
					}
		 			
			String saleterm = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
			" xl_opencover_raw.validation_error||',BASIS OF VALUATION Not Exists' " +
			" where  XL_OPENCOVER_RAW.BASIS_OF_VALUATION IS NOT NULL AND regexp_substr(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'[^%]+',1,1)||'%' not in " +
			"(select sale_term_name from SALE_TERM_MASTER where " +
			" sale_term_name= regexp_substr(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'[^%]+',1,1)||'%' and " +
			" branch_code=?" +
			" and sale_term_id in ("+saletermValues+")) and (length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))=1 and transaction_id=?";
			System.out.println("SALE TERM QRRY:"+saleterm);
			this.mytemplate.update(saleterm, new Object[]{branch,tranId});
			
			String saletermNo = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
			" xl_opencover_raw.validation_error||',BASIS OF VALUATION Not Exists' " +
			" where  XL_OPENCOVER_RAW.BASIS_OF_VALUATION IS NOT NULL AND regexp_substr(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'[^%]+',1,1)||'%'  in " +
			"(select sale_term_name from SALE_TERM_MASTER where " +
			" sale_term_name= regexp_substr(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'[^%]+',1,1)||'%' and " +
			" branch_code=?" +
			" and sale_term_id in ("+saletermValues+")) and  '%"+saletoleranceValues+"%' like '%'||XL_OPENCOVER_RAW.BASIS_OF_VALUATION ||'+%' and (length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))=1 " +
			" AND  XL_OPENCOVER_RAW.BASIS_OF_VALUATION not in " +
			" ('"+saletoleranceValues.replaceAll(",", "','")+"') " +
			" and transaction_id=?";
			System.out.println("SALE TERM QRRY:"+saletermNo);
			this.mytemplate.update(saletermNo, new Object[]{branch,tranId});
			
			if(saletoleranceValues.trim().equalsIgnoreCase("000")){
			String saletermNoTolerance = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
			" xl_opencover_raw.validation_error||',BASIS OF VALUATION Not Exists' " +
			" where  XL_OPENCOVER_RAW.BASIS_OF_VALUATION IS NOT NULL  and (length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))=2 and transaction_id=?";
			System.out.println("SALE TERM No tolerance QRRY:"+saletermNoTolerance);
			this.mytemplate.update(saletermNoTolerance, new Object[]{tranId});
			}
			String saleterminvalid = "update xl_opencover_raw set xl_opencover_raw.validation_error=  xl_opencover_raw.validation_error||',BASIS OF VALUATION Not Exists'  " +
					"    where transaction_id =? and ((length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+',''))=0))       or ((length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))>2)";
			this.mytemplate.update(saleterminvalid, new Object[]{tranId});
				

			//String updateToleranceDefault = "update xl_opencover_raw set tolerance=(select ) where (length (XL_OPENCOVER_RAW.BASIS_OF_VALUATION) - length(replace(XL_OPENCOVER_RAW.BASIS_OF_VALUATION,'+','')))=1";
				
			
				
			}
			if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("VOYAGE_FROM")){

				String coutryOrigination = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
						" xl_opencover_raw.validation_error||',"+sourceField+" Not Exists' " +
						" where XL_OPENCOVER_RAW.VOYAGE_FROM IS NOT NULL AND XL_OPENCOVER_RAW.VOYAGE_FROM not in " +
						" (select country_name from country_master where " +
						" country_name=XL_OPENCOVER_RAW.VOYAGE_FROM and " +
						" amend_id=(select max(amend_id) from country_master " +
						" where country_name=XL_OPENCOVER_RAW.VOYAGE_FROM)" +
						" and country_id in (" +
						" SELECT regexp_substr(ORG_countryid,'[^,]+',1,LEVEL) as countryid FROM (SELECT   OPEN_COVER_COUNTRY_ID_ORG ORG_countryid FROM OPEN_COVER_COUNTRY_MASTER WHERE   PROPOSAL_NO = ?  AND AMEND_ID = (SELECT   MAX (AMEND_ID)  FROM   OPEN_COVER_COUNTRY_MASTER    WHERE   PROPOSAL_NO = ?))CONNECT BY LEVEL <= LENGTH (ORG_countryid)  - LENGTH (REPLACE (ORG_countryid, ',', ''))  + 1)) and transaction_id='"+tranId+"'";
				System.out.println("Qry Reference->"+coutryOrigination);
				this.mytemplate.update(coutryOrigination, new Object[]{proposal,proposal});
			}
			if(tableName.equalsIgnoreCase("XL_OPENCOVER_RAW") && dbColumnName.trim().equalsIgnoreCase("VOYAGE_TO")){
			
			  String coutryDestination = "update xl_opencover_raw set xl_opencover_raw.validation_error=" +
				" xl_opencover_raw.validation_error||',"+sourceField+" Not Exists' " +
				" where XL_OPENCOVER_RAW.VOYAGE_TO IS NOT NULL AND XL_OPENCOVER_RAW.VOYAGE_TO not in " +
				" (select country_name from country_master where " +
				" country_name=XL_OPENCOVER_RAW.VOYAGE_TO and " +
				" amend_id=(select max(amend_id) from country_master " +
				" where country_name=XL_OPENCOVER_RAW.VOYAGE_TO)" +
				" and country_id in (" +
				" SELECT regexp_substr(ORG_countryid,'[^,]+',1,LEVEL) as countryid FROM (SELECT   OPEN_COVER_COUNTRY_ID_DEST ORG_countryid FROM OPEN_COVER_COUNTRY_MASTER WHERE   PROPOSAL_NO = ?  AND AMEND_ID = (SELECT   MAX (AMEND_ID)  FROM   OPEN_COVER_COUNTRY_MASTER    WHERE   PROPOSAL_NO = ?))CONNECT BY LEVEL <= LENGTH (ORG_countryid)  - LENGTH (REPLACE (ORG_countryid, ',', ''))  + 1)) and transaction_id='"+tranId+"'";
			  System.out.println("Qry Reference->"+coutryDestination);
			  this.mytemplate.update(coutryDestination, new Object[]{proposal,proposal});
						 			  
			}
		}
	    logger.info("referenceValidation Exit");
    }
	public void lengthValidation(String movedetailId, String tranId){
		logger.info("lengthValidation Enter");
		String selectFieldsLength = "select dest_field,field_length,source_field from xl_master_table_config where movement_detail_id=? and field_length is not null and status='Y' ";
		try {
				//Length Validation
				String validLength = "";
				SqlRowSet lengthFields =this.mytemplate.queryForRowSet(selectFieldsLength,new Object[]{movedetailId});
				
				while(lengthFields.next()){
					validLength =	"update "+tableName+" w " +
					 		" set validation_error=validation_error||',"+lengthFields.getString(3)+" exceeds length'" +
					 		" where  transaction_id='"+tranId+"' and length("+lengthFields.getString(1)+")>"+lengthFields.getString(2);
					System.out.println("Validate Length Query:"+validLength);
					this.mytemplate.update(validLength);
						
				}
			}	
			catch(Exception e){
				e.printStackTrace();
			}
		
		logger.info("lengthValidation Exit");
	}
	
	public void dateValidation(String productId,String campaignId,String partnerId,String movementId,String tranId){
		
		logger.info("dateValidation Enter");
		Object[] arg = new Object[]{productId,campaignId,partnerId,movementId,tranId};
		final String sql = "{ call datevalidation(?, ?, ?,?,?) }"; 
		System.out.println("args::>"+productId+","+campaignId+","+partnerId+","+movementId+","+tranId);
		this.mytemplate.update(sql, arg);
		logger.info("dateValidation Exit");
	}
	public String validateRecords(String tranId,String movementId,String movedetailId, String productId,String campaignId,String partnerId,String openCoverNo,String brokerCode,String loginId) {
		String result="";
		try {
			logger.info("validateRecords Enter");
			this.moveDetailId= movedetailId;
			this.tableName = this.getTableName(movedetailId);
			String proposal =checkProposal(openCoverNo,brokerCode,tranId,loginId);
			result = checkError( tranId);
			String branch = (String)this.mytemplate.queryForObject("select branch_code from broker_company_master where agency_code =? and status='Y'",new Object[]{brokerCode}, String.class);
			
			if(!proposal.equalsIgnoreCase("")){
				mandatoryValidation(movedetailId, tranId);
				lengthValidation(movedetailId,tranId);
				numberValidation(movedetailId,tranId);
				referenceValidation(movedetailId,tranId,proposal,branch,openCoverNo);
				dateValidation(productId,campaignId	,partnerId,movementId,tranId);
			}
			
			logger.info("validateRecords Exit");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	private String checkProposal(String openCoverNo,String brokerCode,String tranId,String loginId) {
		System.out.println("Inside Check Proposal ::");
		String proposal="";
		String qry = "update "+ tableName +" set broker_code ='"+brokerCode+"' ,OPENCOVER_POLICY_NUMBER='"+openCoverNo+"', MOC_NUMBER='"+openCoverNo+"' where transaction_id='"+tranId+"'";
		this.mytemplate.update(qry);
		System.out.println("Query for Open cover updation :"+qry);
		String brokerValid = "UPDATE "+ tableName +" SET VALIDATION_ERROR=VALIDATION_ERROR||',BROKER CODE INVALID' WHERE BROKER_CODE NOT IN ( SELECT OA_CODE FROM LOGIN_MASTER WHERE STATUS='Y' AND LOGIN_ID='"+loginId+"') AND TRANSACTION_ID=?";
		this.mytemplate.update(brokerValid,new Object[]{tranId});
		
		String openCoverValid =  "UPDATE "+ tableName +" SET VALIDATION_ERROR=VALIDATION_ERROR||',OPEN COVER NO INVALID' WHERE MOC_NUMBER NOT IN ( SELECT MISSIPPI_OPENCOVER_NO FROM OPEN_COVER_MASTER WHERE STATUS='Y' and broker_id ='"+loginId+"') AND TRANSACTION_ID=?";
		this.mytemplate.update(openCoverValid,new Object[]{tranId});
		
		int valid = this.mytemplate.queryForObject("select count(*) from "+ tableName +" where transaction_id='"+tranId+"' and validation_error is not null", Integer.class);
		if(valid == 0){
		String qry1 ="SELECT PROPOSAL_NO FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=? AND STATUS='Y' and amend_id=(select max(amend_id) from OPEN_COVER_MASTER where MISSIPPI_OPENCOVER_NO=? AND STATUS='Y' )";
		 proposal = (String)this.mytemplate.queryForObject(qry1,String.class, new Object[]{openCoverNo,openCoverNo});
		}
		return proposal;
	}	
	private String checkError(String tranId) {
		
		/*String qry1 ="SELECT count(*) FROM "+tableName+ " WHERE validation_error is not null and transaction_id=?";
		String result = (String)this.mytemplate.queryForObject(qry1, new Object[]{tranId},String.class);
		if(result.equalsIgnoreCase("0"))
			result="";*/
		return "";
	}
}

