package com.maan.adminnew.rating;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.MyJdbcTemplate;

public class RatingUploadDAO extends MyJdbcTemplate 
{
	private static final Logger logger = LogUtil.getLogger(RatingUploadDAO.class);
	public String sql="";
	public List <Map<String, Object>> getDBColumns(String typeId) throws Exception
	{
		logger.info("getDBColumns - Enter || typeId: "+typeId);
		List<Map<String, Object>> list=null;
		try {
			sql = getQuery("GET_DB_COLUMNS");
			logger.info("sql==>"+sql);
			list=this.mytemplate.queryForList(sql, new String[]{typeId});
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getDBColumns - Exit || Result: "+list.size());
		return list;
	}
	
	public String insertTransactionDetails(String loginId, String fileName, String typeId, String folderPath, String effDate, String branchCode) {
		System.out.println("insertTransactionDetails() - Enter");
		String sql=getQuery("GET_SEQ_TRANS");
		String sequence=(String)this.mytemplate.queryForObject(sql, new Object[]{typeId}, String.class);
		int tranId=this.mytemplate.queryForObject("SELECT "+sequence+".NEXTVAL FROM DUAL", Integer.class);
		String args[]=new String[7];
		args[0]=tranId+"";
		args[1]=loginId;
		args[2]=fileName;
		args[3]=folderPath;
		args[4]=typeId;
		args[5]=effDate;
		args[6]=branchCode;
		sql=getQuery("INS_TRANS_DET");
		int result=this.mytemplate.update(sql,args);
		System.out.println("insertTransactionDetails() - Exit || Result: "+result+" Tran Id: "+tranId);
		return tranId+"";
	}
	
	public String insertRawData(String typeId, String tableName, String fields, List<Map<String, Object>> records,  String tranId) 
	{
		System.out.println("insertRawData() - Enter || recordsCount: "+records.size());
		String[] queries=new String[0];
		String errorMsg="", values="";
		fields=fields+", ERROR_DESC, TRAN_ID, SNO, STATUS, TYPE_ID";
		values="','"+tranId+"', SERIAL_NO.NEXTVAL,'N','"+typeId+"'" ;
		try {
			if(!records.isEmpty())
			{
				queries=new String[records.size()];
				for(int i=0; i<records.size(); i++)
				{
					sql="INSERT INTO "+tableName+"("+fields+") VALUES('"+records.get(i)+values+")";
					queries[i]=sql;
					logger.info("Sql==>"+sql);
				}
				if(queries.length>0)
					this.mytemplate.batchUpdate(queries);
			}	
		}catch (Exception e) 
		{
			logger.info("Error Sql==>"+sql);
			logger.debug("EXCEPTION @ { "+e+" }");
			String excepMsg = e.getMessage();
			if(excepMsg.contains("ORA-")) 
				errorMsg = excepMsg.substring(excepMsg.indexOf("ORA-"),excepMsg.length());
			else
				errorMsg= "Uploaded file contains invalid file format";
		}
		System.out.println("insertRawData() - Exit || Error: "+errorMsg);
		return errorMsg;
	}
	
	public List <Map<String, Object>> moveRecordsToMaster(String tranId, String typeId)  {
		logger.info("insertMainData() - Enter");
		List <Map<String, Object>> uploadResult=null;
		try{
			sql = "select VALIDATION_PROCEDURE from XL_UPLOAD_TYPE_MASTER where TYPE_ID='"+typeId+"'";
			logger.info("sql==>"+sql);
			String procName=this.mytemplate.queryForObject(sql, String.class).toString();
			this.mytemplate.update("call "+procName+"(?)", new Object[]{new String(tranId)});
			uploadResult=this.mytemplate.queryForList("SELECT TOTAL_RECORDS, VALID_RECORDS, ERROR_RECORDS FROM XL_UPLOAD_TRANSACTION_DETAILS WHERE TRAN_ID=?", new String[]{tranId});
			logger.info("insertMainData() - Exit");
		}catch(Exception e){
			e.printStackTrace();
		}
		return uploadResult;
	}
	
	public List <Map<String, Object>> getTypeInfo(String typeId)
	{
		logger.info("getTypeInfo - Enter");
		List<Map<String, Object>> list=null;
		try {
			sql = getQuery("GET_TYPE_INFO");
			logger.info("sql==>"+sql);
			list=this.mytemplate.queryForList(sql, new String[]{typeId});
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getTypeInfo - Exit || Result: "+list.size());
		return list;
	}
	
	public Map<Object,Object> getDownloadInfo(String typeId, String tranId, String downloadType)
	{
		logger.info("getDownloadInfo - Enter || tranId: "+tranId);
		String excelHeaders="", dbColumns="", tableName="";
		List<Map<String, Object>> list=null;
		List  typeList=null;
		Map<Object,Object> map=new HashMap<Object,Object>();
		try {
			sql = "SELECT EXCEL_HEADER_NAME, FIELD_NAME FROM XL_UPLOAD_FIELD_MASTER A, XL_UPLOAD_CONFIG_MASTER B WHERE A.FIELD_ID = B.FIELD_ID AND A.TYPE_ID = ?";
			logger.info("sql==>"+sql);
			list=this.mytemplate.queryForList(sql, new String[]{typeId});
			
			typeList = getTypeInfo(typeId);
			if(!list.isEmpty())
			{
				for(int i=0; i<list.size();i++){
					excelHeaders+=(String)((Map)list.get(i)).get("EXCEL_HEADER_NAME")+",";
					dbColumns+=(String)((Map)list.get(i)).get("FIELD_NAME")+",";
				}
			}
			if("E".equals(downloadType))
			{
				excelHeaders+=" ERRORDESC";
				dbColumns+=" ERROR_DESC";
				tableName=(String)((Map)typeList.get(0)).get("ERROR_TABLE_NAME");
			}else
			{
				excelHeaders=excelHeaders.substring(0,excelHeaders.length()-1);
				dbColumns=dbColumns.substring(0,dbColumns.length()-1);
				tableName="XL_UPLOAD_MASTER";
			}
			map.put("EXCEL_HEADERS", excelHeaders);
			map.put("DB_COLUMNS", dbColumns);
			map.put("TABLE_NAME", tableName);
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { "+e+" }");
			e.printStackTrace();
		}
		logger.info("getDownloadInfo - Exit");
		return map;
	}
	
	public List<Map<String, Object>> getDownloadData(String tranId, Map<Object,Object> downloadInfo,String downloadType) 
	{
		logger.info("getDownloadData - Enter || tranId: "+tranId);
		String tableName=(String)downloadInfo.get("TABLE_NAME");
		String dbColumns=(String)downloadInfo.get("DB_COLUMNS");
		List<Map<String, Object>> list=null;
		try {
			if(dbColumns!=null)
			{
				if(StringUtils.isEmpty(downloadType))
					sql="SELECT "+dbColumns+" FROM "+tableName+" WHERE TRAN_ID=?";
				else
					sql="SELECT "+dbColumns+" FROM "+tableName+" WHERE TRAN_ID=?";
				logger.debug("sql==>"+sql);
				list=this.mytemplate.queryForList(sql, new String[]{tranId});
			}
		} catch (Exception e) {
			logger.debug("EXCEPTION @ { "+e+" }");
		}
		logger.info("getDownloadData - Exit");
		return list;
	}
	
	/*public int updateExcelFormat(String tranId, String excelHeaders, String dbColumns, String mobileNoColumns) 
	{
		logger.info("updateExcelFormat() - Enter");
		int updateSts=0;
		try{
			String[] args=new String[]{excelHeaders, dbColumns, mobileNoColumns, tranId};
			updateSts=this.mytemplate.update("UPDATE SFT_TRANSACTION_DETAILS SET EXCEL_HEADERS=?, DB_COLUMNS=?, MOBILE_NO_COLUMNS=? WHERE TRAN_ID=?", args);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("updateExcelFormat() - Exit || Updated: "+updateSts);
		return updateSts;
	}*/
	
	public List<Map<String, Object>> getTransDetList(String fromDate, String toDate, String typeId){
		List<Map<String, Object>> list=null;
		logger.info("getTransDetList() - Enter");
		try{
			String sql=getQuery("GET_TRANS_DET");
			logger.info("sql==>"+sql);
			list=this.mytemplate.queryForList(sql, new Object[]{fromDate, toDate, typeId});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("getTransDetList() - Exit");
		return list;
	}
}