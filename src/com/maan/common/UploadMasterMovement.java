package com.maan.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UploadMasterMovement extends MyJdbcTemplate{
	private static final Logger logger = LogUtil.getLogger(UploadMasterMovement.class);
	
	public SqlRowSet getTables(String movedetailId){
		String selTables = "select source_table,destination_table from xl_movement_detail where movement_detail_id = ?";
	    SqlRowSet tableset = this.mytemplate.queryForRowSet(selTables, new Object[] { movedetailId });
	    return tableset; 
	}
	public List<Map<String, Object>> getColumnList(String movedetailId)
	{
		String query = "SELECT FIELD_ID,SOURCE_FIELD,DEST_FIELD,STATUS,DATA_TYPE,DATA_FORMAT,"
			+ " MASTER_DATA_TABLE,MASTER_DATA_FIELD,MANDATORY_STATUS,MASTER_DATA_VALUE,MASTER_DATA_CHECK," +
					"CONSTANT_VALUE,FORMULA,CONDITION from "
			+ " XL_MASTER_TABLE_CONFIG  WHERE STATUS='Y' AND MOVEMENT_DETAIL_ID="+ movedetailId + "  order by FIELD_ID";
		logger.info("Query for Column List"+query);
		List<Map<String, Object>> list = this.mytemplate.queryForList(query);
		return list;
	}
	public StringBuffer getReferenceQuery(String destTable,String dbcolumn,String movementValue,String refTable,String refColumn,String tranId,String condition,String branchCode)
	{
		StringBuffer query = new StringBuffer();
		String temp="";
		if(destTable.equalsIgnoreCase("webservice_marine_quote") || destTable.equalsIgnoreCase("webservice_marine_commodity")){
			temp= "and quote_no is null";
		}
		if(destTable.equalsIgnoreCase("webservice_marine_quote_raw") || destTable.equalsIgnoreCase("webservice_marine_commodity_rw"))
		{
			temp= "and response_time is null";
		}
		query.append("update "+destTable+" DT set DT."+dbcolumn+"=(" +
				" select distinct MT." + movementValue+ " from " + refTable + " MT where "+(condition.equalsIgnoreCase("")?"":(condition+" and "))+ 
				"  ( MT.status='Y' or MT.status='R') and MT."+refColumn+"=DT."+dbcolumn+")" +
				" where  DT." + dbcolumn+ " is not null and DT.TRANSACTION_ID='"+tranId+"'" + temp);
		
		System.out.println("mmquery " + query);
		return query;
	}
	public StringBuffer getConstantQuery(String destTable,String dbcolumn,String movementValue,String tranId)
	{
		StringBuffer query = new StringBuffer();
		String temp="";
		if(destTable.equalsIgnoreCase("webservice_marine_quote") || destTable.equalsIgnoreCase("webservice_marine_commodity")){
			temp= " and quote_no is null";
		}
		if(destTable.equalsIgnoreCase("webservice_marine_quote_raw") || destTable.equalsIgnoreCase("webservice_marine_commodity_rw"))
		{
			temp= " and response_time is null";
		}
		
		query.append( "update " + destTable+ " set "+destTable+"."+ dbcolumn + " ");
		query.append( " = '"+movementValue+"' ");
		query.append(  " where transaction_id='"+tranId+"'  "+temp );
		return query;
	}
	public StringBuffer getFormulaQuery(String destTable,String dbcolumn,String movementValue,String tranId)
	{
		StringBuffer query = new StringBuffer();
		String temp="";
		if(destTable.equalsIgnoreCase("webservice_marine_quote") || destTable.equalsIgnoreCase("webservice_marine_commodity")){
			temp= " and quote_no is null";
		}
		query.append( "update " + destTable+ " set "+destTable+"."+ dbcolumn + " ");
		query.append( " = "+movementValue+" ");
		query.append(  " where transaction_id='"+tranId+"'  "+temp );
		return query;
	}
	public String getMasterMovementQry(String sourceTable,String destTable,String tranId,String columnQry,String valuesQry,String condition)
	{
		logger.info("Column Query is  "+columnQry);
		logger.info("Value Query is"+valuesQry);
		String temp="";
		if(sourceTable.equalsIgnoreCase("webservice_marine_quote_raw") || sourceTable.equalsIgnoreCase("webservice_marine_commodity_rw")){
			temp= " and "+sourceTable+".reference_number not in( select reference_number from "+destTable+" where transaction_id='"+tranId+"') ";
		}
		String moveMasterQuery = "insert into " +destTable + "( " + columnQry + " transaction_id) " +
								 " (select " + valuesQry+ "transaction_id from " + sourceTable  + " where "	
								 + sourceTable + ".validation_error is null and transaction_id="+tranId + condition + temp+ " )";
       logger.info("Master Movement Query ::"+moveMasterQuery);
		return moveMasterQuery;
	}
	public void moveMasterRecords(String movedetailId,String tranId,String brokerCode,String loginId, String openCoverNo) {
		
		try {
			logger.info("moveMasterRecords Enter: movedetailId "+movedetailId);
			SqlRowSet tableset = this.getTables(movedetailId);
			String sourceTable = "";
			String destTable = "";
			if(tableset.next()){
				sourceTable = tableset.getString(1).toString();
				destTable = tableset.getString(2).toString();
			}
			String branchCode = (String)this.mytemplate.queryForObject("select branch_code from broker_company_master where agency_code =? and status='Y'",new Object[]{brokerCode}, String.class);
			System.out.println("Branch Code :"+branchCode);
			final List<String> queries =new ArrayList<String>();
			final List<String> masterqueries =new ArrayList<String>();
			Map values;
			List<Map<String, Object>> list = this.getColumnList(movedetailId);
			String proposal = "";
			String qry1 ="SELECT PROPOSAL_NO FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=? AND STATUS='Y' and amend_id=(select max(amend_id) from OPEN_COVER_MASTER where MISSIPPI_OPENCOVER_NO=? AND STATUS='Y' )";
			proposal = (String)this.mytemplate.queryForObject(qry1, new Object[]{openCoverNo,openCoverNo},String.class);
			System.out.println("MMUTHUproposal " + proposal);
			
			if (!list.isEmpty()) {
				String dbcolumn, data_type, data_format,sourceColumn;
				final StringBuffer masterqry=new StringBuffer(),tempqry=new StringBuffer();
				
				String refTable, refColumn, mandatorySts,movementValue,condition="";
				for (int j = 0; j < list.size(); j++) {
					values =  list.get(j);
					final String active = (String) values.get("STATUS");
					if ("Y".equalsIgnoreCase(active)|| "1".equalsIgnoreCase(active)) {
						dbcolumn = (String) values.get("DEST_FIELD");
						sourceColumn =(String)( values.get("SOURCE_FIELD")==null?"":values.get("SOURCE_FIELD").toString().trim()); 
						data_type = (String) values.get("DATA_TYPE") == null ? "": (String) values.get("DATA_TYPE");
						data_format = (String) values.get("DATA_FORMAT");
						mandatorySts = (String) values.get("MANDATORY_STATUS") == null ? "": (String) values.get("MANDATORY_STATUS");
						condition = (String) values.get("CONDITION") == null ? "": (String) values.get("CONDITION");
						if( !sourceColumn.equalsIgnoreCase("")){
							masterqry.append(dbcolumn.trim());
							masterqry.append(',');
						}
				
						if ("DATE".equalsIgnoreCase(data_type)) {
							tempqry.append( ("to_date(" + sourceTable + "."+ sourceColumn.trim() + ",'DD/MM/YYYY')")+ ",");
						}
						else if ("TIMESTAMP".equalsIgnoreCase(data_type)) {
							tempqry.append(("to_date(substr("+ sourceTable+ "."+ sourceColumn.trim()+ ",1,"+ (data_format.equalsIgnoreCase("DD/MM/YYYY") ? "11": "10") + "),'" + data_format + "')")+ ",");
						}
						else if ("TIME".equalsIgnoreCase(data_type)) {
							tempqry.append( ("replace(" + sourceTable + "."+ sourceColumn.trim() + ",':','')")+ ",");
						} 
						else if (!mandatorySts.equalsIgnoreCase("P") && (values.get("MASTER_DATA_CHECK")!=null && values.get("MASTER_DATA_CHECK").toString().equalsIgnoreCase("Y")))
						{
							refTable = (String) values.get("MASTER_DATA_TABLE");
							refColumn = (String) values.get("MASTER_DATA_FIELD");
							movementValue = (String) values.get("MASTER_DATA_VALUE");
						    StringBuffer query=new StringBuffer();
						    query = this.getReferenceQuery(destTable, dbcolumn, movementValue, refTable, refColumn, tranId,condition,branchCode);
							masterqueries.add(query.toString());
							logger.info("Query for reference updation::"+query.toString());
							tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
						}
						else if (!mandatorySts.equalsIgnoreCase("P") && (values.get("CONSTANT_VALUE")!=null ))
						{
							movementValue =(String) ( values.get("CONSTANT_VALUE"));
						    StringBuffer query=new StringBuffer();
						    query = this.getConstantQuery(destTable, dbcolumn, movementValue,  tranId);
							queries.add(query.toString());
							logger.info("Query for Constant updation::"+query.toString());
							if(!sourceColumn.equalsIgnoreCase(""))
								tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
							//tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
						}
						else if (!mandatorySts.equalsIgnoreCase("P") && (values.get("FORMULA")!=null ))
						{
							movementValue =(String) ( values.get("FORMULA"));
						    StringBuffer query=new StringBuffer();
						    query = this.getFormulaQuery(destTable, dbcolumn, movementValue,  tranId);
							queries.add(query.toString());
							logger.info("Query for Formula updation::"+query.toString());
							if(!sourceColumn.equalsIgnoreCase(""))
								tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
							//tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
						}
						else{
							if(!sourceColumn.equalsIgnoreCase(""))
								tempqry.append("trim("+(sourceTable + "." + sourceColumn.trim())+ "),");
						}
					}
				}
				// Master Movement Query Execution
				String moveMasterQuery = this.getMasterMovementQry(sourceTable, destTable, tranId, masterqry.toString(), tempqry.toString(), condition);
				System.out.println("Master Query ::"+moveMasterQuery);
				this.mytemplate.execute(moveMasterQuery);
				
				 if(destTable.equalsIgnoreCase("WEBSERVICE_MARINE_QUOTE_RAW")){
					 
					/*String selOpenCover = "select distinct MOC_NUMBER from webservice_marine_quote_raw where transaction_id=? ";
					String openCoverNo = (String)this.mytemplate.queryForObject(selOpenCover,new Object[]{tranId}, String.class);
					System.out.println("openCoverNo::>>>> "+ selOpenCover+openCoverNo);
					*/
					System.out.println("PROPOSAL UPDATION::"+proposal);
					String updateToleranceDefault = "update  WEBSERVICE_MARINE_QUOTE_RAW " +
							" set WEBSERVICE_MARINE_QUOTE_RAW.tolerance = " +
							" (SELECT REPLACE(REGEXP_SUBSTR(OCSM.TOLERANCE_NAME,'[^+,]+'," +
							"instr(OCSM.TOLERANCE_NAME,WEBSERVICE_MARINE_QUOTE_RAW.saleterm_value),3),'%',NULL) " +
							"ff FROM OPEN_COVER_SALE_TERM_MASTER OCSM WHERE  OCSM.PROPOSAL_NO=? " +
							"AND OCSM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_SALE_TERM_MASTER " +
							"WHERE PROPOSAL_NO=OCSM.PROPOSAL_NO) AND OCSM.TOLERANCE_NAME " +
							"LIKE '%,'||WEBSERVICE_MARINE_QUOTE_RAW.saleterm_value||',%') where " +
							"(length (WEBSERVICE_MARINE_QUOTE_RAW.saleterm_value ) - " +
							"length(replace(WEBSERVICE_MARINE_QUOTE_RAW.saleterm_value ,'+','')))=1" +
							" and transaction_id=?";
					
					 SqlRowSet ss = this.mytemplate.queryForRowSet("select saleterm_value,tolerance from webservice_marine_quote_raw where transaction_id='"+tranId+"'"); 
					  System.out.println("QUERY:"+"select saleterm_value,tolerance from webservice_marine_quote_raw where transaction_id='"+tranId+"'");
					    while (ss.next()){
					    	System.out.println("values:sale::::"+ss.getString(1));
					    	System.out.println("values:tolerance::::"+ss.getString(2));
					    }
					System.out.println("updateToleranceDefault::"+updateToleranceDefault);
					this.mytemplate.update(updateToleranceDefault,new Object[]{proposal,tranId});
					 SqlRowSet ss1 = this.mytemplate.queryForRowSet("select saleterm_value,tolerance from webservice_marine_quote_raw where transaction_id='"+tranId+"'"); 
					  System.out.println("QUERY:"+"select saleterm_value,tolerance from webservice_marine_quote_raw where transaction_id='"+tranId+"'");
					    while (ss1.next()){
					    	System.out.println("values:sale::::"+ss1.getString(1));
					    	System.out.println("values:tolerance::::"+ss1.getString(2));
					    }
					String updateTolerance = "update WEBSERVICE_MARINE_QUOTE_RAW set tolerance =  replace(regexp_substr(TOLERANCE,'[^+]+',1,3),'%','') where (length (WEBSERVICE_MARINE_QUOTE_RAW.TOLERANCE ) - " +
							"length(replace(WEBSERVICE_MARINE_QUOTE_RAW.TOLERANCE ,'+','')))>1 and transaction_id=?";
					this.mytemplate.update(updateTolerance,new Object[]{tranId});
					}
				 
				// formula and constant Updations
				for(int i=0;i<queries.size();i++){
					this.mytemplate.update(queries.get(i));
				}
				
				if(destTable.equalsIgnoreCase("WEBSERVICE_MARINE_QUOTE_RAW")){
					
					 SqlRowSet ss = this.mytemplate.queryForRowSet("select saleterm_value,saleterm_percent from webservice_marine_quote_raw where transaction_id='"+tranId+"'"); 
					  System.out.println("QUERY:"+"select saleterm_value,saleterm_percent from webservice_marine_quote_raw where transaction_id='"+tranId+"'");
					    while (ss.next()){
					    	System.out.println("values:::::"+ss.getString(1));
					    	System.out.println("values:::::"+ss.getString(2));
					    }
					/*String updateSaleTerm = "update webservice_marine_quote_raw wr set saleterm_percent = " +
							"(select sale_term_id from sale_term_master mt where ((wr.saleterm_value||'+'||wr.saleterm_percent||'%')=mt.sale_term_name) and branch_code=?) where wr.transaction_id=?";
					this.mytemplate.update(updateSaleTerm, new Object [] {branchCode,tranId} );
					
					
					updateSaleTerm = "update webservice_marine_quote_raw wr set saleterm_value=(select distinct(RSACODE) from SALE_TERM_MASTER where wr.SALETERM_VALUE=SALE_TERM_ID) where wr.transaction_id=?";
					this.mytemplate.update(updateSaleTerm, new Object [] {tranId} );
					*/
					String updateSaleTerm = "update webservice_marine_quote_raw wr set (saleterm_value,saleterm_percent)=" +
						"(select rsacode, sale_term_id from sale_term_master mt where ((wr.saleterm_value||'+'||wr.saleterm_percent||'%')=mt.sale_term_name) and branch_code=?) where wr.transaction_id=?";
					this.mytemplate.update(updateSaleTerm, new Object [] {branchCode,tranId} );
				
					String updateLC = "update webservice_marine_quote_raw wr set LC_NUMBER ='NONE' where wr.LC_NUMBER is null  and wr.transaction_id=?";
					this.mytemplate.update(updateLC, new Object [] {tranId} );
				
					
					String updateSaleTermInvalid = "update webservice_marine_quote_raw wr set validation_error = validation_error || ',INVALID SALE TERM'  where wr.saleterm_value is null and  wr.transaction_id=?";
					this.mytemplate.update(updateSaleTermInvalid, new Object [] {tranId} );
				}
				
				if(destTable.equalsIgnoreCase("WEBSERVICE_MARINE_QUOTE")){
					String settleOthers = "update webservice_marine_quote q set settling_agent_name = settling_agent, settling_agent='' where settling_agent<>'ABUDHABI NATIONAL INSURANCE COMPANY' and quote_no is null and  transaction_id=?";
					System.out.println("settling agent Others Qry "+settleOthers);
					this.mytemplate.update(settleOthers, new Object [] {tranId} );
					String settlingAgent =	"update webservice_marine_quote q set settling_agent = (select  settling_agent_id from settling_agent_master where settling_agent_id<>310 and BELONGING_COUNTRY_ID =q.DESTINATION_COUNTRY and branch_code=? and status='Y')  where settling_agent='ABUDHABI NATIONAL INSURANCE COMPANY' and   quote_no is null and  transaction_id=?";
				    System.out.println("settling agent Qry "+settlingAgent);
				    this.mytemplate.update(settlingAgent, new Object [] {branchCode,tranId} );
				    
				    String bankId =	"update webservice_marine_quote q set ISSUING_BANK = " +
		    		"(select  bank_id from open_cover_lc_master " +
		    		" where lc_number=q.lc_number and open_cover_no=q.opencover_policy_number " +
		    		"and amend_id=(select max(amend_id) from " +
		    		"open_cover_lc_master where open_cover_no=q.opencover_policy_number) " +
		    		"and status='Y')  where quote_no is null and   transaction_id=?";
				    System.out.println("issuing bank  update  Qry "+bankId);
				    this.mytemplate.update(bankId, new Object [] {tranId} );
				    
				    String lcNumber =	"update webservice_marine_quote q set lc_number = " +
				    		"(select  lc_id from open_cover_lc_master " +
				    		" where lc_number=q.lc_number and open_cover_no=q.opencover_policy_number " +
				    		"and amend_id=(select max(amend_id) from " +
				    		"open_cover_lc_master where open_cover_no=q.opencover_policy_number) " +
				    		"and status='Y')  where  quote_no is null and  transaction_id=?";
				    System.out.println("lcNumber update  Qry "+lcNumber);
				    this.mytemplate.update(lcNumber, new Object [] {tranId} );
				   
				    String updateLogin = "update webservice_marine_quote wr set login_id=?,branch_code=?  where wr.transaction_id=? and quote_no is null   ";
					this.mytemplate.update(updateLogin, new Object [] {loginId,branchCode,tranId} );
					
					
					String updateTolerance = "update webservice_marine_quote q set tolerance = (select distinct tolerance_id from TOLERANCE_MASTER where TOLERANCE_VALUE =q.TOLERANCE and branch_code=? and status='Y')  where quote_no is null and   transaction_id=?";
					System.out.println("settling agent Qry "+updateTolerance);
					this.mytemplate.update(updateTolerance, new Object [] {branchCode,tranId} );
					
					String updateVessel = "update webservice_marine_quote r set " +
							"vessel_name=(SELECT CASE WHEN  MODE_OF_TRANSPORT = 1 " +
							" THEN NVL (VESSEL_NAME, 'Any Approved Vessel')" +
							"WHEN MODE_OF_TRANSPORT = 2      " +
							"THEN NVL (VESSEL_NAME, 'Any Scheduled Airline')WHEN " +
							"      MODE_OF_TRANSPORT = 3 THEN NVL (VESSEL_NAME, 'Any Approved Carrier')  " +
							"     WHEN MODE_OF_TRANSPORT =  5   THEN NVL (VESSEL_NAME, 'Any Approved Courier') " +
							"     WHEN MODE_OF_TRANSPORT =  6    THEN NVL (VESSEL_NAME, 'Any Approved Vessel/Scheduled Airline ')     " +
							"  ELSE NVL (VESSEL_NAME, '')   END     " +
							"from webservice_marine_quote where  r.transaction_id=transaction_id and r.reference_number =reference_number )    " +
							"  where r.quote_no is null and   r.transaction_id=? and VESSEL_NAME IS NULL";
					this.mytemplate.update(updateVessel, new Object [] {tranId} );
					
					String updateConveyance = "update webservice_marine_quote r set sea_coverages='0' where sea_coverages is null and quote_no is null and transaction_id=?";
					this.mytemplate.update(updateConveyance, new Object [] {tranId} );
					}
				//master data updations
				try {
					for(int i=0;i<masterqueries.size();i++){
						System.out.println("mmproposal "  + proposal);
						if((masterqueries.get(i).replaceAll(" " ,"")).contains("PROPOSAL_NO=?")){
							this.mytemplate.update(masterqueries.get(i),new Object[]{proposal,proposal});
						} else if(masterqueries.get(i).contains("?")){
					    	this.mytemplate.update(masterqueries.get(i),new Object[]{branchCode});
						} else {
							this.mytemplate.update(masterqueries.get(i));
						}
					}
					String updateREsponse = "update WEBSERVICE_MARINE_QUOTE_RAW set RESPONSE_TIME=sysdate where transaction_id = ?";
					this.mytemplate.update(updateREsponse, new Object [] {tranId} );
					
					String updateComm = "update webservice_marine_commodity_rw wr set RESPONSE_TIME=sysdate where transaction_id = ?";
					this.mytemplate.update(updateComm, new Object [] {tranId} );
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				}
			logger.info("moveMasterRecords Exit");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}	
	public void removeTempRecords(String movedetailId,String tranId) {
		
		logger.info("removeTempRecords Enter: movedetailId "+movedetailId);
		SqlRowSet tableset = this.getTables(movedetailId);
		String sourceTable = "";
		if(tableset.next()){
			sourceTable = tableset.getString(1).toString();
		}
		this.mytemplate.update("delete from "+sourceTable+" where validation_error is null and transaction_id=? ",new Object[]{tranId});
		logger.info("removeTempRecords Exit");
	}	
}
