package com.maan.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class FileHandler {
	private static final Logger logger = LogUtil.getLogger(FileHandler.class);
	public static boolean convertExcelToCSV(String converterPath,
			String inPath, String outPath) {
		logger.info("convertExcelToCSV() - Enter");
		System.out.println("converterPath"+converterPath);
		System.out.println("inPath"+inPath);
		System.out.println("outPath"+outPath);
		boolean result = false;
		Runtime runTime = Runtime.getRuntime();
		Process process = null;
		try {
			process = runTime
					.exec(converterPath + " " + inPath + "," + outPath);
			process.waitFor();
			result = true;
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			process.destroy();
		}
		logger.info("convertExcelToCSV() - Exit || Result: " + result);
		return result;
	}

	

	public boolean uploadFile(String inPath, String outPath) {
		boolean result = false;
		logger.info("uploadFile() - Enter");
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new FileInputStream(inPath);
			out = new FileOutputStream(outPath);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("uploadFile() - Exit || Result: " + result);
		return result;
	}

	public static boolean downloadFile(HttpServletResponse response,
			String fileName) {
		logger.info("===>downloadFile() - Enter");
		boolean result = false;
		try {
			byte[] buf = new byte[1024];
			OutputStream out = response.getOutputStream();
			response.setHeader("Content-disposition", "attachment;filename="
					+ fileName);
			response.setContentType("application/binary");
			File file = new File(fileName);
			if (file.exists() && file.isFile() && file.canRead()) {
				FileInputStream fis = new FileInputStream(file);
				int bytesRead = 0;
				while ((bytesRead = fis.read(buf)) != -1) {
					out.write(buf, 0, bytesRead);
				}
				fis.close();
				file.delete();
			}
			out.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		logger.info("<===downloadFile() - Exit || Result: " + result);
		return result;
	}

	public static boolean convertExcelToCSV(String inPath, String outPath) throws IOException {
		List<List<HSSFCell>> cellGrid = new ArrayList<List<HSSFCell>>();
		boolean status=true;
        try {
            FileInputStream myInput = new FileInputStream(inPath);           
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
            HSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator<?> rowIter = mySheet.rowIterator();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<?> cellIter = myRow.cellIterator();
                List<HSSFCell> cellRowList = new ArrayList<HSSFCell>();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    cellRowList.add(myCell);                   
                }
                cellGrid.add(cellRowList);
            }            
            File file = new File(outPath);
            PrintStream stream = new PrintStream(file);
            for (int i = 0; i < cellGrid.size(); i++) {
                List<HSSFCell> cellRowList = cellGrid.get(i);
                for (int j = 0; j < cellRowList.size(); j++) {
                    HSSFCell myCell = (HSSFCell) cellRowList.get(j);
                    String stringCellValue ="";
                    if(myCell.getCellType()==HSSFCell.CELL_TYPE_STRING){
                    	stringCellValue = myCell.getRichStringCellValue().getString();
                    	logger.info("Cell Value=>"+stringCellValue);
                    } else if(HSSFDateUtil.isCellDateFormatted(myCell)){
                    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    	Date dt = myCell.getDateCellValue();
                    	if(dt!=null){
	                    	stringCellValue = format.format(dt);
                    	}
                    } else if(myCell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
                    	double val = myCell.getNumericCellValue();
                    	try{
	                    	if(!(val<((int)val) || val>((int)val))){
	                    		stringCellValue = String.valueOf((int)val);
	                    	}else{
	                    		stringCellValue = String.valueOf(val);
	                    	}
                    	}catch(Exception e){
                    		stringCellValue = String.valueOf(myCell.getNumericCellValue());
                    	}
                    	logger.info("Value=>"+stringCellValue);                    	                    	
                    } else {
                    	stringCellValue = myCell.getRichStringCellValue().getString();
                    }
                    stringCellValue = stringCellValue.replaceAll("\t", "");
                    stream.print(stringCellValue + (j==(cellRowList.size()-1)?"":"~"));
                }
                stream.println("");
            }
        } catch (FileNotFoundException e) {
        	logger.debug("Exception @ {"+e+"}");
        	status=false;
        }
        catch(Exception e){
        	logger.debug("Exception @ {"+e+"}");
        	status=false;
        }      
        return status;
    }
}
