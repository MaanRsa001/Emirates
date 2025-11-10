package com.maan.adminnew.rating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.maan.common.LogUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;

import com.maan.common.StringHelper;
import com.maan.adminnew.rating.RatingUploadDAO;;

public class RatingUploadService {
	private static final Logger logger = LogUtil.getLogger(RatingUploadService.class);
	RatingUploadDAO upload=new RatingUploadDAO();
	
	public String getTranactionId(String loginId, String fileName, String typeId, String filePath, String effDate, String branchCode) {
		return upload.insertTransactionDetails(loginId, fileName, typeId, filePath, effDate, branchCode);
	}
	
	public Map<Object,Object> getDownloadInfo(String typeId, String tranId, String downloadType) {
		return upload.getDownloadInfo(typeId, tranId, downloadType);
	}
	
	public List<Map<String, Object>> getDownloadData(String tranId, Map<Object,Object> downloadInfo,String downloadType) {
		return upload.getDownloadData(tranId, downloadInfo, downloadType);
	}

	public Map<Object,Object> insertRecords(String typeId, File file, String tranId) throws Exception {
		logger.info("insertRecords - Exit || typeId: "+typeId);
		String error="";
		Map<Object,Object> resultMap=null;
		try {
			resultMap=getRecordsList(file, upload.getDBColumns(typeId), typeId);
			error=(String)resultMap.get("ERROR");
			if(StringUtils.isEmpty(error)) {
				List<Map<String, Object>> typeInfo=upload.getTypeInfo(typeId);
				error=upload.insertRawData(typeId, (String)((Map)typeInfo.get(0)).get("RAW_TABLE_NAME"), (String)resultMap.get("FIELDS"), (List)resultMap.get("RECORDS"), tranId);
				if(error.equals(""))
					resultMap.put("UPLOAD_RESULT", upload.moveRecordsToMaster(tranId, typeId));
				else
					resultMap.put("ERROR", error);
			}
		}catch(Exception e) { e.printStackTrace(); }
		logger.info("insertRecords - Exit || Error: "+error);
		return resultMap;	
	}
	
	public static Map<Object,Object> getRecordsList(File inputFile, List<Map<String, Object>> dbColumns, String typeId) {
		List<String> recordsList=new ArrayList<String>();
		String record="", result="", unmatched="";
		long totalLinesProcessed = 0l;
		String[] excelHeaders=null;
		Map<Object,Object> resultMap=null;
		boolean cont = true;
		BufferedReader in=null;
		try {
			in = new BufferedReader(new FileReader(inputFile));
			while ((record = in.readLine()) != null && cont)  {
				//logger.info("status----->" + record);
				totalLinesProcessed++;
				if (totalLinesProcessed == 1)  {
					record=record.toUpperCase().replaceAll("[^A-Z0-9_\t]", "");
					excelHeaders = record.split("\t");
					excelHeaders = removeDupColumns(excelHeaders, dbColumns);
					resultMap = matchColumns(excelHeaders, dbColumns);
					if(!resultMap.isEmpty()) {
						unmatched=(String)resultMap.get("UNMATCHED");
						if(unmatched!=null && unmatched.length()>0)
						{
							result+="Unknown Columns Found ["+unmatched+"]";
							cont=false;
							break;
						}
					}
				}
				record = record.replaceAll("\'", "");
				record = record.replaceAll("\"", "");
				if((record.trim()).length()==0)
					break;
				record = record.replaceAll("\t", "'\t'");
				if (totalLinesProcessed != 1) {
					if((record.trim()).length()!=0){
					record=formatValues((String[][])resultMap.get("COLUMN_INFO"), record);
					recordsList.add(record);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		resultMap.put("ERROR", result);
		resultMap.put("RECORDS", recordsList);
		return resultMap;
	}
	
	public static String formatValues(String[][] columnInfoList, String record) {
		String[] values=record.split("\t");
		String mobileNoYN="",mandatoryYN="",dataType="",columnName="", value="", error="", finalValues="";
		//boolean mobileNoFlag=false;
		if(columnInfoList!=null) {
			for(int i=0; i<values.length; i++)
			{
				mobileNoYN=(String)columnInfoList[i][1];
				mandatoryYN=(String)columnInfoList[i][2];
				dataType=(String)columnInfoList[i][3];
				columnName=(String)columnInfoList[i][4];
				value=(String)values[i].replaceAll("'", "");
				
				if("NUMBER".equalsIgnoreCase(dataType)) {
					value=StringHelper.removeChars(dataType, value);
				}else if("DECIMAL".equalsIgnoreCase(dataType)) {
					value=StringHelper.removeChars(dataType, value);
				}else if("DATE".equalsIgnoreCase(dataType)) {
					if(!StringHelper.validateDate(value, "dd/mm/yyyy"))
						value="";
				}
				if("Y".equalsIgnoreCase(mandatoryYN) && "".equals(value))
					error+="~ "+columnName+" Not available";
				finalValues+=value+"','";
			}
			/*if(!mobileNoFlag)
				error+="~ Mobile Not available";*/
			finalValues+=error;
		}
		return finalValues;
	}
	
	public static Map<Object,Object> matchColumns(String[] excelColumns, List<Map<String, Object>> dbColumns) {
		logger.info("matchColumns() - Enter");
		String[][] columnInfoList = new String[dbColumns.size()][5];
		String matchedColumns = "", unmatchedHeaders="", excelHeader="", matchedHeaders="", mobileNoColumns="", fieldName="";
		HashMap<Object,Object> columns = new HashMap<Object,Object>();
		Map<Object,Object> columnInfo=null;
		int k=0;
		try  {
			for (int i = 0; i < excelColumns.length; i++)  {
				int flag = 0;
				for (int j = 0; j < dbColumns.size(); j++)  {
					columnInfo=(Map)dbColumns.get(j);
					excelHeader=(String)columnInfo.get("EXCEL_HEADER_NAME");
					if (((excelColumns[i].trim()).equalsIgnoreCase(excelHeader))) {
						flag = 1;
						fieldName=(String)columnInfo.get("FIELD_NAME");
						matchedColumns+=fieldName+",";
						matchedHeaders+=excelHeader+",";
						columnInfoList[k][0]=fieldName;
						columnInfoList[k][1]=(String)columnInfo.get("MOBILE_NO_YN");
						columnInfoList[k][2]=(String)columnInfo.get("MANDATORY_YN");
						columnInfoList[k][3]=(String)columnInfo.get("DATA_TYPE");
						columnInfoList[k][4]=excelHeader;
						if("Y".equalsIgnoreCase((String)columnInfo.get("MOBILE_NO_YN")))
							mobileNoColumns+=fieldName+",";
						k++;
						break;
					}
				}
				if (flag == 0)
					unmatchedHeaders+=(String)excelColumns[i]+", ";
			}
			if(unmatchedHeaders.length()>1)
				unmatchedHeaders=unmatchedHeaders.substring(0, unmatchedHeaders.length()-2);
			if(matchedHeaders.length()>0)
				matchedHeaders=matchedHeaders.substring(0, matchedHeaders.length()-1);
			if(matchedColumns.length()>0)
				matchedColumns=matchedColumns.substring(0, matchedColumns.length()-1);
			if(mobileNoColumns.length()>0)
				mobileNoColumns=mobileNoColumns.substring(0, mobileNoColumns.length()-1);
				
			columns.put("MATCHED",matchedHeaders);
			columns.put("UNMATCHED", unmatchedHeaders);
			columns.put("FIELDS", matchedColumns);
			columns.put("COLUMN_INFO",columnInfoList);
			columns.put("MOBILE_FIELDS", mobileNoColumns);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("matchColumns() - Exit || UnMatched: "+unmatchedHeaders);
		return columns;
	}
	
	public static String[] removeDupColumns(String columns[], List dbColumns) {
		int count=0, index=0;
		String temp="";
		String[] modifiedColumns=(String[])columns.clone();
		Map<Object,Object> columnInfo=getListAsMap(dbColumns, "EXCEL_HEADER_NAME", "HEADER_TYPE");
		for (int i = 0; i < columns.length; i++) {
			count=0;
			for (int j = i; j < modifiedColumns.length; j++) {
				if(columns[i].equals(modifiedColumns[j])){
					count++;
					if("R".equals(columnInfo.get(modifiedColumns[j]+count))){
						if(count==1){
							index=j;
							temp=columns[j];
						}else{
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
	
	public static Map<Object,Object> getListAsMap(List<Map<String, Object>> dbColumns, String key, String value){
		Map<Object,Object> map=new LinkedHashMap<Object,Object>();
		Map<Object,Object> temp=new LinkedHashMap<Object,Object>();
		
		if(!dbColumns.isEmpty()){
			for (int i = 0; i < dbColumns.size(); i++) {
				temp=(Map)dbColumns.get(i);
				map.put(temp.get(key), temp.get(value));
			}
		}
		return map;
	}
	
	public List<Map<String, Object>> getTransDetList(String fromDate, String toDate, String typeId){
		return upload.getTransDetList(fromDate, toDate, typeId);
	}
}
