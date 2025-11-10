package com.maan.upload.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;
import com.maan.upload.UploadAction;
import com.maan.webservice.PolicyGenerationAction;
import com.maan.webservice.service.QuoteGeneration;


public class UploadDAO extends MyJdbcTemplate 
{
	private static final Logger logger = LogUtil.getLogger(UploadDAO.class);
	public String sql="";
	public List getDBColumns(String movementDetailId)
	{
		logger.info("getDBColumns - Enter || movementDetailId: "+movementDetailId);
		List list= new ArrayList();
		try {
			sql = "SELECT SOURCE_FIELD, DEST_FIELD, MANDATORY_STATUS, DATA_TYPE, FIELD_ID FROM XL_MASTER_TABLE_CONFIG  WHERE MOVEMENT_DETAIL_ID=? AND STATUS='Y'";
			list=this.mytemplate.queryForList(sql, new String[]{movementDetailId});
			
		} catch (Exception e) {
			
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getDBColumns  sql: "+sql);
		logger.info("getDBColumns - Exit || Result: "+list.size());
		
		return list;
	}
	public List getDownloadData(String tranId, Map downloadInfo) 
	{
		logger.info("getDownloadData - Enter || tranId: "+tranId);
		String tableName=(String)downloadInfo.get("TABLE_NAME");
		String dbColumns=(String)downloadInfo.get("DB_COLUMNS");
		List list=null;
		try {
			if(dbColumns!=null)
			{
				sql="SELECT "+dbColumns+" FROM "+tableName+" WHERE TRAN_ID=? AND STATUS='Y'";
				logger.debug("sql==>"+sql);
				list=this.mytemplate.queryForList(sql, new String[]{tranId});
			}
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getDownloadData - Exit");
		
		return list;
	}
	public Map insertRawData(String typeId, String tableName, String fields, List records, String movementDetailId, String tranIndex, String transId, String snoIndex, String headers,String finalHeaders) 
	{
		System.out.println("insertRawData() - Enter || recordsCount: "+records.size());
		String[] queries=new String[0];
		
		String sqlRaw = "";
		boolean error = false;
		String validationError = "";
		String errorMsg="", values="",tranId="0";
		Map result = new HashMap();
		//String tempTable = "TEMP_"+Calendar.DATE+Calendar.MONTH+Calendar.YEAR+Calendar.getInstance().getTimeInMillis();
		//String tempCreateTable = "CREATE TABLE  "+tempTable+"("+headers.replaceAll(",", " varchar2(500 byte),")+" varchar2(500 byte),TRANSACTION_ID varchar2(500 byte),SNO varchar2(500 byte))";
		//this.mytemplate.execute(tempCreateTable);
		String sequence = "select raw_sequence from xl_movement_detail where movement_detail_id=?";
		if(!fields.contains("TRANSACTION_ID")){
			fields=fields+", TRANSACTION_ID,SNO";
			if(StringUtils.isBlank(transId)){
			 tranId=(String)this.mytemplate.queryForObject("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL",String.class);
			}else{
				// To addd records to the existing transaction
				tranId = transId;
			}
			String sequenceName = (String)this.mytemplate.queryForObject(sequence, new Object[]{movementDetailId}, String.class);
			values=",'"+tranId+"',"+sequenceName+".nextval";
		} 
		else{
			System.out.println("Error Reupload");
			error = true;
		}
		
		try 
		{
			int errorTransactionStartIndex=0;;
			int errorTransactionEndIndex=0;
			String errorTranID="";
			if(error){
			 errorTransactionStartIndex = nthOccurrence(records.get(0).toString(), '\'', ((Integer.parseInt(tranIndex)*2)-1));
			 errorTransactionEndIndex = nthOccurrence(records.get(0).toString(), '\'', ((Integer.parseInt(tranIndex)*2)));
			 errorTranID= records.get(0).toString().substring(errorTransactionStartIndex,errorTransactionEndIndex).replaceAll("'", "");
			}
			if(!records.isEmpty())
			{
				
				for(int i=0; i<records.size(); i++)
				{
					if(error){
						System.out.println("Error Tran ID"+errorTranID);
						String spli[] = errorTranID.split("\\.");
						tranId =spli[0]==null?errorTranID:spli[0];
						System.out.println(records.get(i).toString());
						int errorSnoStartIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)-1));
						int errorSnoEndIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)));
						String errorSNO= records.get(i).toString().substring(errorSnoStartIndex,errorSnoEndIndex).replaceAll("'", "");
						System.out.println("errorSNO"+errorSNO);
						sql= "select count(*) from XL_OPENCOVER_RAW WHERE TRANSACTION_ID=? and sno=?";
						String count = (String)this.mytemplate.queryForObject(sql, new Object[]{errorTranID,errorSNO},String.class);
						if(count.equalsIgnoreCase("0"))
							validationError =validationError+"Reference No "+errorSNO + " Not Exists<br/>";
						sql= "select count(*) from webservice_marine_quote WHERE TRANSACTION_ID=? and reference_number=?";
						 count = (String)this.mytemplate.queryForObject(sql, new Object[]{errorTranID,errorSNO},String.class);
						if(!count.equalsIgnoreCase("0"))
							validationError ="Quote already generated for Reference No "+errorSNO + "<br/>";
						
					}
					
				}
				
			}	
			System.out.println("validationError"+validationError);
			if(!records.isEmpty() && validationError.equalsIgnoreCase(""))
			{
				System.out.println("Error Exists::"+error);
				queries=new String[records.size()];
				
				for(int i=0; i<records.size(); i++)
				{
					if(error){
						int errorSnoStartIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)-1));
						int errorSnoEndIndex = nthOccurrence(records.get(i).toString(), '\'', ((Integer.parseInt(snoIndex)*2)));
						String errorSNO= records.get(i).toString().substring(errorSnoStartIndex,errorSnoEndIndex).replaceAll("'", "");
						sql= "delete from "+tableName+" WHERE TRANSACTION_ID=? and sno=?";
						System.out.println("delete query ::"+sql+":error::"+errorTranID+"sn::"+errorSNO);
						this.mytemplate.update(sql,new Object[]{errorTranID,errorSNO});
					}
					
					sql="INSERT INTO "+tableName+"("+fields+") VALUES('"+records.get(i)+values+" )";
					System.out.println("Queries"+i+"::"+sql);
					queries[i]=sql;
				//	sql="INSERT INTO "+tableName+"("+fields+") select "+finalHeaders+" TRANSACTION_ID,SNO from "+tempTable+" ";
					//sqlRaw=sql;
					logger.info("Sql test1==>"+sql);
				}	
				try {
					if(queries.length>0){
						this.mytemplate.batchUpdate(queries);
					
						//this.mytemplate.update(sqlRaw);
					}	
						//this.mytemplate.execute("DROP TABLE "+tempTable);
					
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				
			}
		}catch (Exception e) 
		{
			logger.info("Error Sql==>"+sql);
			logger.debug("EXCEPTION @ { "+e+" }");
			e.printStackTrace();
			String excepMsg = e.getMessage();
			if(excepMsg.contains("ORA-")) 
				errorMsg = excepMsg.substring(excepMsg.indexOf("ORA-"),excepMsg.length());
			else
				errorMsg= "Uploaded file contains invalid file format";
		} 
		System.out.println("insertRawData() - Exit || Error: "+errorMsg);
		System.out.println("Validation Error:"+validationError);
		System.out.println("tranId:"+tranId);
		result.put("tranid", tranId);
		result.put("error", validationError);
		return result;
	}
	public String insertMasterData(String typeId, String tableName, String fields, List records,  String tranId) 
	{
		System.out.println("insertMasterData() - Enter || recordsCount: "+records.size());
		String[] queries=new String[0];
		String errorMsg="", values="";
		fields=fields+", TRANSACTION_ID";
		values=tranId+"'";
		
		try 
		{
			if(!records.isEmpty())
			{
				queries=new String[records.size()];
				for(int i=0; i<records.size(); i++)
				{
					sql="INSERT INTO "+tableName+"("+fields+") VALUES('"+records.get(i)+values+" )";
					queries[i]=sql;
					logger.info("Sql test1==>"+sql);
				}	
				try {
					if(queries.length>0)
						this.mytemplate.batchUpdate(queries);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
				    
			}	
		}catch (Exception e) 
		{
			logger.info("Error Sql==>"+sql);
			logger.debug("EXCEPTION @ { "+e+" }");
			
			
		}
		System.out.println("insertMasterData() - Exit || Error: "+errorMsg);
		return errorMsg;
	}
	public void archiveRawData(String movementDetailId, String tranId, String user) 
	{
		System.out.println("archiveRawData() - Enter: "+tranId);
		
		try 
		{
			String qry = "SELECT ARCHIVE_TABLE,DESTINATION_TABLE FROM XL_MOVEMENT_DETAIL WHERE ARCHIVE_TABLE IS NOT NULL AND MOVEMENT_DETAIL_ID=?";
			SqlRowSet rset = this.mytemplate.queryForRowSet(qry, new Object[] { movementDetailId });
			if(rset.next()){
				String archiveTable = rset.getString(1);
				String rawTable = rset.getString(2);
				String sql="insert into "+archiveTable+" (select * from "+rawTable+" where transaction_id=?) ";
				this.mytemplate.update(sql,new Object[]{tranId});
				String updateQry = "update "+archiveTable+" set transaction_date=sysdate , user_id=? where transaction_date is null and transaction_id=?";
				this.mytemplate.update(updateQry,new Object[]{user,tranId});
			}
			
		}catch (Exception e) 
		{
			logger.info("Error Sql==>"+sql);
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		System.out.println("archiveRawData() - Exit ");
		
	}
	public void updateOpenCoverDetail(String brokerCode, String openCoverNo,
			String tranId) {
		logger.info("updateOpenCoverDetail Enter" + tranId);
		
		try { 
			String qry = "update WEBSERVICE_MARINE_QUOTE_RAW set broker_code ='"+brokerCode+"' , MOC_NUMBER='"+openCoverNo+"' where transaction_id='"+tranId+"'";
			this.mytemplate.update(qry);
			System.out.println(qry);
			String brokerValid = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET VALIDATION_ERROR=VALIDATION_ERROR||',BROKER CODE INVALID' WHERE BROKER_CODE NOT IN ( SELECT AGENCY_CODE FROM BROKER_COMPANY_MASTER WHERE STATUS='Y' ) AND TRANSACTION_ID=?";
			this.mytemplate.update(brokerValid,new Object[]{tranId});
			System.out.println(brokerValid);
			//String openCoverValid =  "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET VALIDATION_ERROR=VALIDATION_ERROR||',OPEN COVER NO INVALID' WHERE MOC_NUMBER NOT IN ( SELECT MISSIPPI_OPENCOVER_NO FROM OPEN_COVER_MASTER WHERE STATUS='Y' and broker_id in (select company_name from BROKER_COMPANY_MASTER bc where broker_code=bc.agency_code and status='Y')) AND TRANSACTION_ID=?";
			//this.mytemplate.update(openCoverValid,new Object[]{tranId});
			//System.out.println(openCoverValid);
			String updateProposal = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW SET PROPOSAL_NO=(SELECT PROPOSAL_NO FROM OPEN_COVER_MASTER OCM WHERE MOC_NUMBER =OCM.MISSIPPI_OPENCOVER_NO AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO =OCM.MISSIPPI_OPENCOVER_NO )) WHERE  TRANSACTION_ID=?";
			System.out.println("Update "+updateProposal);
			this.mytemplate.update(updateProposal,new Object[]{tranId});
				
				//Customer Info Updation 
				String customerUpdate = "UPDATE WEBSERVICE_MARINE_QUOTE_RAW WR SET " +
				" WR.OPENCOVER_POLICY_NUMBER=WR.MOC_NUMBER," +
				/*" WR.WSRCC=(SELECT (case when OCM.WRSC_YN ='Y' then 'YES' else 'NO' end) FROM OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+*/
				" WR.CUSTOMER_ID=(SELECT PI.CUSTOMER_ID FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.CUSTOMER_NAME=(SELECT PI.FIRST_NAME FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.ADDRESS1=(SELECT PI.ADDRESS1 FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.ADDRESS2=(SELECT PI.ADDRESS2 FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.PO_BOX=(SELECT PI.POBOX FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.COUNTRY=(SELECT PI.COUNTRY FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.EMIRATE=(SELECT PI.EMIRATE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.MOBILE_NUMBER=(SELECT PI.MOBILE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.CUST_TITLE=(SELECT PI.TITLE FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER),"+
				" WR.EMAIL=(SELECT PI.EMAIL FROM PERSONAL_INFO PI,OPEN_COVER_MASTER OCM"+
				"            WHERE OCM.CUSTOMER_ID=PI.CUSTOMER_ID"+
				"            AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=OCM.MISSIPPI_OPENCOVER_NO)"+
				"            AND PI.AMEND_ID=(SELECT MAX(AMEND_ID) FROM PERSONAL_INFO WHERE CUSTOMER_ID=PI.CUSTOMER_ID)"+
				"            AND OCM.MISSIPPI_OPENCOVER_NO=WR.MOC_NUMBER)"+
				" WHERE WR.TRANSACTION_ID=? AND VALIDATION_ERROR IS NULL";
				System.out.println("customerUpdate"+customerUpdate);
				this.mytemplate.update(customerUpdate,new Object[]{tranId});
		
			
		} catch (Exception e) {
		
			e.printStackTrace();
			logger.info("updateOpenCoverDetail Error"+e);
		}
		
		logger.info("updateOpenCoverDetail Exit");
		
	}
	public void generateQuotations(String tranId) {
		System.out.println("generateQuotations Enters");
		SqlRowSet rs = this.mytemplate.queryForRowSet("select reference_number from webservice_marine_quote where transaction_id=?",new Object[]{tranId});
		while (rs.next()){
		QuoteGeneration quoteGeneration = new QuoteGeneration();
		PolicyGenerationAction generate = new PolicyGenerationAction(rs.getString(1));
		String remarks= generate.quoteGeneration();
		
		String response = quoteGeneration.generateResponse(rs.getString(1),remarks);
		System.out.println("Response ::"+rs.getString(1)+"::"+response);
		}
		System.out.println("generateQuotations Exits");
	}
	public void getResult(String tranId, String movementDetailId) {
			
		String destTable = (String)this.mytemplate.queryForObject("select destination_table from xl_movement_detail where movement_detail_id=?",new Object[]{movementDetailId},String.class);
		
		try {
			String count =(String)this.mytemplate.queryForObject("select (select count(*) from "+destTable+" where transaction_id=?)+(select count(*) from webservice_marine_quote where transaction_id=?) from dual", new Object[]{tranId,tranId},String.class);
			System.out.println("Total No Of Records::"+ count);
			this.mytemplate.update("update xl_transaction_details set total_no_of_records=? where transaction_id=?",new Object[]{count,tranId});
						 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
        System.out.println("getResult() - Exit");
		
	}
	
	public List getResult2(String tranId, String movementDetailId) {
		String destTable = (String)this.mytemplate.queryForObject("select destination_table from xl_movement_detail where movement_detail_id=?",new Object[]{movementDetailId},String.class);
				
		try {
			String updateTransaction =  "update XL_TRANSACTION_DETAILS set UPLOADED_COUNT=(select (select q.TOTAL_NO_OF_RECORDS from XL_TRANSACTION_DETAILS q where transaction_id=?)-(select count(*) from "+destTable+" where transaction_id=?) from dual),PENDING_COUNT=(select count(*) from "+destTable+" where transaction_id=?) where transaction_id=?";
			this.mytemplate.update(updateTransaction, new Object[]{tranId,tranId,tranId,tranId});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		List uploadResult=this.mytemplate.queryForList("SELECT TOTAL_NO_OF_RECORDS, UPLOADED_COUNT, PENDING_COUNT FROM XL_TRANSACTION_DETAILS WHERE TRANSACTION_ID=?", new String[]{tranId});
		System.out.println("getResult2() - Exit");
		return uploadResult;
	}
	public List getErrorList(String sno, String tranId) {
		System.out.println("Enter getErrorList");
		List list = null;
		try {
			if(StringUtils.isBlank(sno)){
				list = this.mytemplate.queryForList("select SR_NO, REF_NO, ORDER_NO, INTEREST,COMMODITY_DESCRIPTION, LC_NO, CONVEYANCE, COVER, VESSEL_NAME, SAILING_DATE, PACKING_DETAILS, VOYAGE_FROM, VOYAGE_TO, INVOICE_VALUE, CURRENCY, INCOTERMS, ROE, BASIS_OF_VALUATION, PACKAGE_DESCRIPTION, SETTLING_AGENT, SNO, TRANSACTION_ID, VALIDATION_ERROR, OPENCOVER_POLICY_NUMBER, WSRCC " +
					" from xl_opencover_raw where transaction_id=? and validation_error is not null",new Object[]{tranId});
			}else{
				list = this.mytemplate.queryForList("select SR_NO, REF_NO, ORDER_NO, INTEREST,COMMODITY_DESCRIPTION, LC_NO, CONVEYANCE, COVER, VESSEL_NAME, SAILING_DATE, PACKING_DETAILS, VOYAGE_FROM, VOYAGE_TO, INVOICE_VALUE, CURRENCY, INCOTERMS, ROE, BASIS_OF_VALUATION, PACKAGE_DESCRIPTION, SETTLING_AGENT, SNO, TRANSACTION_ID, VALIDATION_ERROR, OPENCOVER_POLICY_NUMBER, WSRCC " +
						" from xl_opencover_raw where transaction_id=? and SNO=? and validation_error is not null",new Object[]{tranId, sno});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Enter getErrorList");
		return list;
	}
	public List getErrorListBk(String tranId) {
		
		System.out.println("Enter getErrorList");
		List list = null;
		try {
			list = this.mytemplate.queryForList("select * from xl_opencover_raw where transaction_id=? and validation_error is not null",new Object[]{tranId});
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("Enter getErrorList");
		return list;
	}
	public List getUploadedList(String tranId) {
		
		System.out.println("Enter getUploadedList");
		List list = null;
		list = this.mytemplate.queryForList("SELECT (case when mq.status='Q' then to_char(MQ.PREMIUM) else '' end) PREMIUM_TOTAL,MRD.SUM_INSURED,MD.EXCHANGE_RATE,MRD.SUMINSUREDLOCAL,MD.PREMIUM,MRD.PREMIUM,MD.TOTAL_WAR_CHARGES,MD.POLICY_FEE,MRD.RATE,MRD.WARRATE,X.SR_NO, X.REF_NO, X.ORDER_NO,    X.INTEREST, X.LC_NO, X.CONVEYANCE,    X.COVER, X.VESSEL_NAME, X.SAILING_DATE,    X.PACKING_DETAILS, X.VOYAGE_FROM, X.VOYAGE_TO,    X.INVOICE_VALUE, X.CURRENCY, X.INCOTERMS,    X.ROE, X.BASIS_OF_VALUATION, X.WSRCC,    X.PACKAGE_DESCRIPTION, X.SETTLING_AGENT, X.SUM_INSURED_DHS,    MQ.MARINE_PREMIUM, MQ.WAR_PREMIUM, X.TRANSACTION_ID,    X.SNO, X.VALIDATION_ERROR, X.VALIDATION_STATUS,    X.MOC_NUMBER, X.BROKER_CODE, X.OPENCOVER_POLICY_NUMBER ,MQ.QUOTE_NO,X.COMMODITY_DESCRIPTION,MQ.REMARKS ,MQ.ISSUANCE_FEE," +
				"(select sum(SUMINSUREDLOCAL)+sum(SALE_TERM_CHARGES)+sum(TOLERANCE_CHARGES) from MARINE_RESULT_DETAILS where application_no = mrd.application_no and AMEND_ID=(select max(amend_id) from MARINE_RESULT_DETAILS where application_no = mrd.application_no)) SUM_LOCAL, mrd.exchange_rate" +
				" FROM XL_OPENCOVER_ARC X,WEBSERVICE_MARINE_QUOTE MQ,POSITION_MASTER PM,MARINE_DATA MD,MARINE_RESULT_DETAILS MRD WHERE ( x.transaction_date=(select max(transaction_date) from xl_opencover_arc where SNO=MQ.REFERENCE_NUMBER AND transaction_id=?) )and X.TRANSACTION_ID = ? AND X.SNO=MQ.REFERENCE_NUMBER AND X.TRANSACTION_ID=MQ.TRANSACTION_ID AND MQ.QUOTE_NO=PM.QUOTE_NO AND PM.APPLICATION_NO=MD.APPLICATION_NO AND PM.APPLICATION_NO=MRD.APPLICATION_NO ORDER BY MQ.QUOTE_NO ASC",new Object[]{tranId,tranId});
		System.out.println("Exit getUploadedList");
		return list;
	}
	public String getBrokerId(String loginId) {
		System.out.println("Enter getBrokerId");
		String brokerId="";
		try {
			brokerId = (String)this.mytemplate.queryForObject("SELECT OA_CODE FROM LOGIN_MASTER WHERE LOGIN_ID=?",new Object[]{loginId},String.class);
			System.out.println("Exit getBrokerId");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return brokerId;
	}
	public String getcustomerId(String openCoverNo) {
		System.out.println("Enter getcustomerId");
		String customerId="";
		try {
			customerId = (String)this.mytemplate.queryForObject("SELECT FIRST_NAME || NVL(LAST_NAME,'') FROM PERSONAL_INFO WHERE CUSTOMER_ID IN( SELECT CUSTOMER_ID FROM OPEN_COVER_MASTER WHERE  MISSIPPI_OPENCOVER_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE MISSIPPI_OPENCOVER_NO=?))",new Object[]{openCoverNo,openCoverNo},String.class);
			System.out.println("Exit getcustomerId");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return customerId;
	} 
	public List getTransactions(String openCoverNo, String issuerId) {
		System.out.println("Enter getTransactions");
		List list = null;
		list = this.mytemplate.queryForList("SELECT distinct TR.TRANSACTION_ID, TR.MOVEMENT_DETAIL_ID, TR.TRANSACTION_DATE,TO_CHAR(TR.TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TR.TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,TR.USER_NAME, TR.FILE_NAME, TR.PATH, TR.TOTAL_NO_OF_RECORDS, TR.UPLOADED_COUNT, TR.PENDING_COUNT, TR.UPLOAD_STATUS, TR.REMARKS, TR.ACTIVE, TR.OPENCOVER_POLICY_NUMBER,TR.TRANSACTION_DESCRIPTION FROM XL_TRANSACTION_DETAILS TR where TR.OPENCOVER_POLICY_NUMBER=?   and TR.transaction_id is not null and tr.issuer is "+((issuerId.trim().toString().equals(""))?"":" not ")+" null "+((issuerId.trim().toString().equals(""))?"":" and  trim(issuer)='"+issuerId.trim()+"' ")+" order by TR.transaction_id desc",new Object[]{openCoverNo});
		System.out.println("SELECT distinct TR.TRANSACTION_ID, TR.MOVEMENT_DETAIL_ID, TR.TRANSACTION_DATE,TO_CHAR(TR.TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TR.TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,TR.USER_NAME, TR.FILE_NAME, TR.PATH, TR.TOTAL_NO_OF_RECORDS, TR.UPLOADED_COUNT, TR.PENDING_COUNT, TR.UPLOAD_STATUS, TR.REMARKS, TR.ACTIVE, TR.OPENCOVER_POLICY_NUMBER,TR.TRANSACTION_DESCRIPTION FROM XL_TRANSACTION_DETAILS TR where TR.OPENCOVER_POLICY_NUMBER=?   and TR.transaction_id is not null and tr.issuer is "+((issuerId.trim().toString().equals(""))?"":" not ")+" null "+((issuerId.trim().toString().equals(""))?"":" and  trim(issuer)='"+issuerId.trim()+"' ")+" order by TR.transaction_id desc");
		//System.out.println("SELECT TRANSACTION_ID, MOVEMENT_DETAIL_ID, TRANSACTION_DATE,TO_CHAR(TRANSACTION_DATE,'DD/MM/YYYY') TRN_DATE, TO_CHAR(TRANSACTION_DATE,'HH24:MI:SS')  TRN_TIME,USER_NAME, FILE_NAME, PATH, TOTAL_NO_OF_RECORDS, UPLOADED_COUNT, PENDING_COUNT, UPLOAD_STATUS, REMARKS, ACTIVE, OPENCOVER_POLICY_NUMBER FROM XL_TRANSACTION_DETAILS where OPENCOVER_POLICY_NUMBER=? order by transaction_id desc");
		System.out.println("OpencoverNo::"+openCoverNo);
		System.out.println("Exit getTransactions");
		return list;
	}
	public  int nthOccurrence(String str, char c, int n) { 
		int pos = str.indexOf(c, 0);   
		while (n-- > 0 && pos != -1)   
			pos = str.indexOf(c, pos+1); 
		return pos; 
		} 
		
	public List getTypeInfo(String typeId,String campaignId, String partnerId)
	{
		logger.info("getTypeInfo - Enter");
		List list=null;
	
		try {
			sql = "SELECT DESTINATION_TABLE FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID='1' and product_id=? and campaign_id= ? and partner_id=? and status='Y'";
			list=this.mytemplate.queryForList(sql, new String[]{typeId,campaignId,partnerId});
		} catch (Exception e) {
			
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getTypeInfo - Exit || Result: "+list.size());
		
		return list;
	}

	

	public String insertTransactionDetails(String loginId, String fileName, String folderPath, String movementDetailId, String tranId2) 
	{
		String resultTranId = "";
		int count = this.mytemplate.queryForObject("select count(*) from XL_TRANSACTION_DETAILS where transaction_id ='"+tranId2+"'", Integer.class);
		if(count==0){
		 //int tranId=this.mytemplate.queryForInt("SELECT TRANSACTION_NO.NEXTVAL FROM DUAL");
		
		String args[]=new String[5];
		args[0]=tranId2+"";
		args[1]=loginId;
		args[2]=fileName;
		args[3]=folderPath;
		args[4]=movementDetailId;
		sql="INSERT INTO XL_TRANSACTION_DETAILS (TRANSACTION_ID,TRANSACTION_DATE,USER_NAME,FILE_NAME,PATH,ACTIVE,MOVEMENT_DETAIL_ID) " +
				" VALUES(?,SYSDATE,?,?,?,'Y',?)";
		int result=this.mytemplate.update(sql,args);
		System.out.println("insertTransactionDetails() - Exit || Result: "+result+" Tran Id: "+tranId2);
		resultTranId=tranId2+"";
		}
		else
		{
			//sql= "delete from XL_OPENCOVER_RAW WHERE TRANSACTION_ID=?";
			//this.mytemplate.update(sql,new Object[]{tranId2});
			resultTranId=tranId2;
		}
		return resultTranId;
	}


	public List getMovements(String product, String partner, String campaign) {
		
		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID<>1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' AND MOVEMENT_TYPE='A' ORDER BY PRIORITY ASC";
		   movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
		return movements;
	}
	public List getExcelMovement(String product, String partner, String campaign) {
		
		//logger.info("getExcelMovement");
		List movements = null;
		String sql = "SELECT MOVEMENT_DETAIL_ID FROM XL_MOVEMENT_DETAIL WHERE MOVEMENT_ID=1 AND PRODUCT_ID=? AND PARTNER_ID=? AND CAMPAIGN_ID=?  AND STATUS='Y' ORDER BY PRIORITY ASC";
	    movements= this.mytemplate.queryForList(sql,new Object[]{product,partner,campaign});
	    //logger.info("getExcelMovement query "+ sql);
		return movements;
	}
	public String getTransDesc(String transId) {
		
		String transDesc = (String) this.mytemplate.queryForObject("select transaction_description from xl_transaction_details where transaction_id=?",new Object[]{transId},String.class);
		return transDesc;
	}
	public void updateIssuer(String issuer , String tranId) {
		
		this.mytemplate.update("update webservice_marine_quote set issuer=? where transaction_id=?",new Object[]{issuer,tranId});
	}

	public List<Object> getCommodityList(String openCover) {
		List ocList=null;
		try{
			String sql=getQuery("GET_COMMODITY_LIST_OC");
			logger.info("Query====>"+sql);
			ocList=this.mytemplate.queryForList(sql,new Object[]{openCover});
		}catch(Exception e){
			e.printStackTrace();
		}
		return ocList;
	}
	
	public void reuploadSave(final UploadAction up, String bcode) {
		logger.info("ENETR==>reuploadSave");
		try{
			for(int i=1; i<up.getUsno().size();i++){
				Object args[]=new Object[]{i, up.getUinterest().get(i), up.getUconveyance().get(i),bcode,up.getUcover().get(i), up.getUcover().get(i),up.getUvoyageFrom().get(i),
						up.getUvoyageTo().get(i),up.getUcurrency().get(i), bcode, up.getUincoTerms().get(i),up.getUsaleTermPercent().get(i), bcode,up.getUtolerance().get(i), bcode,
						up.getUpkgDesc().get(i),up.getUcommodityDesc().get(i), up.getUwar().get(i),"", up.getTranId(), up.getUsno().get(i)};
				for(Object k:args)
					logger.info("args===>"+k.toString());
				String sql=getQuery("UPD_XL_OPENCOVER_RAW_UPLOAD");
				this.mytemplate.update(sql,args);
				sql=getQuery("UPD_XL_OPENCOVER_ARC_UPLOAD");
				this.mytemplate.update(sql,args);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("EXIT==>reuploadSave");
	}
	
	public String getWarYNOC(String openCoverNo) {
		logger.info("ENETR==>getWarYNOC");
		String warYN="";
		try{
			logger.info("args===>"+openCoverNo);
			String sql=getQuery("GET_WARYN_OPENCOVER");
			warYN=this.mytemplate.queryForObject(sql,new Object[]{openCoverNo}, String.class).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("EXIT==>getWarYNOC");
		return warYN;
	}
}
