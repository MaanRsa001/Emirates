package com.maan.proj.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.FileOutputStream;

public class MsExcelCreator
{
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFRow row;

	
	private String fileName;
	private String sheetName;
	private String data[][];
	
	public MsExcelCreator()
	{
		fileName = "";
		sheetName = "";
		data = new String[0][0];
	}

	public MsExcelCreator(String fileName, String sheetName)
	{
		this.fileName	= fileName;
		this.sheetName	= sheetName;
	}

	public void setData(String[][] data)
	{
		this.data = data;
	}
	
	public String[][] getData()
	{
		return data;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	public String getFileName()
	{
		return fileName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}
	
	public String getSheetName()
	{
		return sheetName;
	}
	
	public void createExcel() throws Exception
	{
		createWorkbook();
		createSheet();
		fillData();
		writeFile();
	}

	private void createWorkbook() throws Exception
	{
		wb = new HSSFWorkbook();
	}


	private void createSheet() throws Exception
	{
		sheet = wb.createSheet(sheetName);
	}

	
	private void fillData()
	{
		for(int i=0; i < data.length; i++)
		{
			row = sheet.createRow((short) i);
			for(int j=0; j < data[i].length; j++)
			{
				row.createCell((short) j).setCellValue(data[i][j]);
			}
		}
	}

	private void writeFile() throws Exception
	{
		FileOutputStream fileOut = new FileOutputStream(fileName);
		wb.write(fileOut);
		fileOut.close();
	}
}
