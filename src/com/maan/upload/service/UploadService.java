package com.maan.upload.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.common.UploadMasterMovement;
import com.maan.common.dao.CommonDAO;
import com.maan.common.exception.BaseException;
import com.maan.upload.UploadAction;
import com.maan.upload.dao.UploadDAO;

public class UploadService  {
	private static final Logger logger = LogUtil.getLogger(UploadService.class);

	private String loginId="";
	private String brokerCode = "";
	private String tranId = "";
	private String openCoverNo = "";
	private FileItem item ;
	private String transactionDesc="";
	UploadDAO upload=new UploadDAO();
	public String getTransactionDesc() {
		return transactionDesc;
	}
	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}
	public FileItem getItem() {
		return item;
	}
	public void setItem(FileItem item) {
		this.item = item;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	
	public Map insertRecords(String typeId, File file, String movementDetailId,String campaignId, String partnerId, String transId) throws BaseException
	{
	logger.info("insertRecords - Enter || typeId: "+typeId);
	String error="";
	Map resultMap=null;
	try
	{ 
		resultMap=getRecordsList(file, upload.getDBColumns(movementDetailId), typeId);
		error=(String)resultMap.get("ERROR");
		if("".equalsIgnoreCase(error))
		{ 
		  	List typeInfo=  new CommonDAO().getTypeInfo(typeId,campaignId,partnerId);
			Map result =upload.insertRawData(typeId, (String)((Map)typeInfo.get(0)).get("DESTINATION_TABLE"), (String)resultMap.get("FIELDS"), (List)resultMap.get("RECORDS"), movementDetailId,(String)resultMap.get("TRANSACTION_INDEX"),transId,(String)resultMap.get("SNO_INDEX"),(String)resultMap.get("HEADERS"),(String)resultMap.get("FINAL_HEADERS"));
		
			resultMap.put("ERROR", result.get("error").toString());
			resultMap.put("TRANID",  result.get("tranid").toString());
		}
	}catch(Exception e) { e.printStackTrace(); }
	logger.info("insertRecords - Exit || Error: "+error);
	return resultMap;	
}
public void  moveMasterRecords(String movementDetailId,String tranId,String brokerCode,String loginId, String openCoverNo) throws BaseException
{
	logger.info("moveMasterRecords - Enter || movementDetailId: "+movementDetailId);
	String error="";
	
	try
	{   UploadMasterMovement move = new UploadMasterMovement();
		move.moveMasterRecords(movementDetailId,tranId,brokerCode,loginId,openCoverNo);
		//resultMap.put("UPLOAD_RESULT", upload.moveRecordsToMaster(tranId, typeId));
	
	}catch(Exception e) { e.printStackTrace(); }
	logger.info("moveMasterRecords - Exit || movementDetailId: "+error);
	//return resultMap;	
}

public void  removeTempRecords(String movementDetailId,String tranId) throws BaseException
{
	logger.info("removeTempRecords - Enter || movementDetailId: "+movementDetailId);
	String error="";
	
	try
	{   UploadMasterMovement move = new UploadMasterMovement();
		move.removeTempRecords(movementDetailId,tranId);
		
	}catch(Exception e) { e.printStackTrace(); }
	logger.info("removeTempRecords - Exit || movementDetailId: "+error);
	//return resultMap;	
}
public void archiveRecords(String movementDetailId, String tranId,String user) throws BaseException
{
	logger.info("archiveRecords - Enter || tranId: "+tranId);
	String error="";
	
	try
	{
		upload.archiveRawData(movementDetailId,tranId,user);
	
	}catch(Exception e) { e.printStackTrace(); }
	logger.info("archiveRecords - Exit || Error: "+error);
		
}
public static Map getRecordsList(File inputFile, List dbColumns, String typeId)
{
	List recordsList=new ArrayList();
	String record="", result="", unmatched="";
	long totalLinesProcessed = 0l;
	String[] excelHeaders=null;
	Map resultMap=null;
	boolean cont = true;
	BufferedReader in=null;
	int headerLength = 0;
	try
	{
		in = new BufferedReader(new FileReader(inputFile));
		while ((record = in.readLine()) != null && cont) 
		{
			logger.info("status----->" + record);
			totalLinesProcessed++;
			if (totalLinesProcessed == 1) 
			{
				record=record.toUpperCase().replaceAll("\t", "~");
				record=record.toUpperCase().replaceAll("[^A-Z0-9_~]", "");
				//record=record.toUpperCase();
				excelHeaders = record.split("~");
				excelHeaders = removeDupColumns(excelHeaders, dbColumns);
				resultMap = matchColumns(excelHeaders, dbColumns);
				/*if(!resultMap.isEmpty())
				{
					unmatched=(String)resultMap.get("UNMATCHED");
					if(unmatched!=null && unmatched.length()>0)
					{
						result+="Unknown Columns Found ["+unmatched+"]";
						cont=false;
						break;
					}
				}*/
				headerLength = excelHeaders.length;
			}
			record = record.replaceAll("\'", "");
			record = record.replaceAll("\"", "");
			record = record.replaceAll("\t", "~");
			String[] valuestemp = record.split("~");
			  
			if(valuestemp.length==0){
				record ="";
			}
			else{
				record = "";
				for(int i =0;i<headerLength;i++)
			    {
			    	record = record+valuestemp[i]+((i==(headerLength-1))?"":"~");
			    }
			}
			
			if((record.trim()).length()==0)
			break;
			
			record = record.replaceAll("~", "'~'");
			if (totalLinesProcessed != 1) 
			{
				
				record=formatValues((String[][])resultMap.get("COLUMN_INFO"), record);
				if(!record.trim().equalsIgnoreCase("")){
				System.out.println("RECORDS::::>>"+record);
				recordsList.add(record);
				}
			}
		}
	}catch(Exception e){e.printStackTrace();}
	finally
	{
		try {
			in.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	resultMap.put("ERROR", result);
	resultMap.put("RECORDS", recordsList);
	return resultMap;
}

public static String formatValues(String[][] columnInfoList, String record)
{
	String[] values=record.split("~");
	
	String mandatoryYN="",dataType="",columnName="", value="", error="", finalValues="";
	
		if(columnInfoList!=null)
		{
			for(int i=0; i<values.length; i++)
			{
				value=(String)values[i].replaceAll("'", "");
				if(i!=values.length-1)
				finalValues+=value+"','";	
				else
				finalValues+=value+"'";	
			}
			
			finalValues+=error;
		}
	
	return finalValues;	
}

public static Map matchColumns(String[] excelColumns, List dbColumns) 
{
	logger.info("matchColumns() - Enter");
	String[][] columnInfoList = new String[dbColumns.size()][5];
	String matchedColumns = "",finalHeaders="",headers="", unmatchedHeaders="", excelHeader="", matchedHeaders="", mobileNoColumns="", fieldName="";
	HashMap columns = new HashMap();
	Map columnInfo=null;
	int k=0;
	String transactionIndex="0";
	String referenceIndex="0";
	try 
	{
		for (int i = 0; i < excelColumns.length; i++) 
		{
			int flag = 0;
			for (int j = 0; j < dbColumns.size(); j++) 
			{
				columnInfo=(Map)dbColumns.get(j);
				excelHeader=(String)columnInfo.get("SOURCE_FIELD");
				excelHeader=excelHeader.toUpperCase().replaceAll("[^A-Z0-9_~]", "");
				if (((excelColumns[i].trim()).equalsIgnoreCase(excelHeader)))
				{
					flag = 1;
					fieldName=(String)columnInfo.get("DEST_FIELD");
					if(fieldName.equalsIgnoreCase("TRANSACTION_ID")){
						transactionIndex = i+"";
					}
					if(fieldName.equalsIgnoreCase("SNO")){
						referenceIndex = i+"";
					}
					matchedColumns+=fieldName+",";
					matchedHeaders+=excelHeader+",";
					columnInfoList[k][0]=fieldName;
					columnInfoList[k][1]=excelHeader;
					finalHeaders+=((String)excelColumns[i]).toUpperCase().replaceAll("[^A-Z0-9_~]", "")+",";
					k++;
					break;
				}
			}
			if (flag == 0) 
			{
				unmatchedHeaders+=(String)excelColumns[i]+", ";
			}
			headers+= ((String)excelColumns[i]).toUpperCase().replaceAll("[^A-Z0-9_~]", "")+((excelColumns.length-1==i)?"":", ");
		}
		if(unmatchedHeaders.length()>1)
			unmatchedHeaders=unmatchedHeaders.substring(0, unmatchedHeaders.length()-2);
		if(matchedHeaders.length()>0)
			matchedHeaders=matchedHeaders.substring(0, matchedHeaders.length()-1);
		if(matchedColumns.length()>0)
			matchedColumns=matchedColumns.substring(0, matchedColumns.length()-1);
			
		columns.put("MATCHED",matchedHeaders);
		columns.put("UNMATCHED", unmatchedHeaders);
		columns.put("FIELDS", matchedColumns);
		columns.put("COLUMN_INFO",columnInfoList);
		columns.put("TRANSACTION_INDEX",transactionIndex );
		columns.put("SNO_INDEX",referenceIndex );
		columns.put("HEADERS", headers);
		columns.put("FINAL_HEADERS",finalHeaders );
	} catch (Exception e) 
	{
		e.printStackTrace();
	}
	logger.info("matchColumns() - Exit || UnMatched: "+unmatchedHeaders);
	return columns;
}
public static String[] removeDupColumns(String columns[], List dbColumns) 
{
	int count=0, index=0;
	String temp="";
	String[] modifiedColumns=(String[])columns.clone();
	Map columnInfo=getListAsMap(dbColumns, "EXCEL_HEADER_NAME", "HEADER_TYPE");
	for (int i = 0; i < columns.length; i++) 
	{
		count=0;
		for (int j = i; j < modifiedColumns.length; j++) 
		{
			if(columns[i].equals(modifiedColumns[j]))
			{
				count++;
				if("R".equals(columnInfo.get(modifiedColumns[j]+count)))
				{
					if(count==1)
					{
						index=j;
						temp=columns[j];
					}else
					{
						if(count==2)
							modifiedColumns[index]=temp+(count-1);
						modifiedColumns[j]=columns[j]+count;
					}
				}
			}
		}
	}
	return modifiedColumns;
}

public static Map getListAsMap(List dbColumns, String key, String value)
{
	Map map=new LinkedHashMap();
	Map temp=new LinkedHashMap();
	
	if(!dbColumns.isEmpty())
	{
		for (int i = 0; i < dbColumns.size(); i++) 
		{
			temp=(Map)dbColumns.get(i);
			map.put(temp.get(key), temp.get(value));
		}
	}
	return map;
}

public String getTransactionId(String loginId, String fileName, String folderPath,  String movementDetailId, String tranId,String openCoverNo, String transactionDesc, String issuer)
{
	return new CommonDAO().insertTransactionDetails(loginId, fileName, folderPath, movementDetailId,tranId,openCoverNo,transactionDesc,issuer);
}
public List getMovements(String product, String partner, String campaign) {
	
	return new CommonDAO().getMovements(product,partner,campaign);

}

public List getExcelMovement(String product, String partner, String campaign) {
	
	return new CommonDAO().getExcelMovement(product,partner,campaign);

}
public void updateOpenCoverDetail(String brokerCode, String openCoverNo,
		String tranId) {
	upload.updateOpenCoverDetail(brokerCode,openCoverNo,tranId);
	
}
public void generateQuotations(String tranId) {
	
	upload.generateQuotations(tranId);
}

public void getResult(String tranId, String movementDetailId) {
	
	
	 upload.getResult(tranId,movementDetailId);
}
public List getResult2(String tranId, String movementDetailId) {
	
	
	return upload.getResult2(tranId,movementDetailId);
}
public List getErrorList(String sno, String tranId) {
	return upload.getErrorList(sno, tranId);
}
public List getUploadedList(String tranId) {
	
	return upload.getUploadedList(tranId);
}
public String getBrokerId(String  loginId) {
	
	
	return upload.getBrokerId(loginId);
}
public String getcustomerId(String openCoverNo) {
	
	return upload.getcustomerId(openCoverNo);
}
public List getTransactions(String openCoverNo, String issuerId) {
	
	return upload.getTransactions(openCoverNo,issuerId);
}
public String getTransDesc(String transId) {
	
	return upload.getTransDesc(transId);
}
public List getDBColumns(String movementDetailId){
	return upload.getDBColumns(movementDetailId);
}
public List<Object> getCommodityList(String openCover) {
	return upload.getCommodityList(openCover);
}
public void reuploadSave(final UploadAction up, String bcode) {
	upload.reuploadSave(up, bcode);
}
public String getWarYNOC(String openCoverNo) {
	return upload.getWarYNOC(openCoverNo);
}
}
