package com.maan.common;

import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
public class ExcelDownload {	
	
	public static String writeExcel(String[] headersList, List recordsList, ByteArrayOutputStream bos) throws Exception
	{
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Sheet1");
		HSSFFont bold = wb.createFont();
		bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle style = wb.createCellStyle();		  			
		style.setFont(bold);
		HSSFRow row = null;
		HSSFCell cell = null;
		String key="", value="";
		int c=0;
		row = sheet.createRow(0);	
		for(int i=0; i<headersList.length; i++)
		{
			cell = row.createCell(new Integer(c++).shortValue());
			value=(headersList[i]==null?"":headersList[i]);
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellStyle(style);
		}
		
		if(recordsList!=null && recordsList.size()>0)
		{
			for(int i=0; i<recordsList.size(); i++)
			{
				c=0;
				row = sheet.createRow(i+1);
				Map map=(Map)recordsList.get(i);
				Set keySet=map.keySet();
				Iterator itr=keySet.iterator();
				while(itr.hasNext())
				{	
					cell = row.createCell(new Integer(c++).shortValue());
					key=(String)itr.next();
					value=(map.get(key)==null?"":map.get(key)).toString();
					cell.setCellValue(new HSSFRichTextString(value));
					cell.setCellStyle(style);
				}
			}
		}
		wb.write(bos);
		bos.close();
		return "";
	}
}