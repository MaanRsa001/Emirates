package com.maan.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;


public class MergePDF {
	
	/*
	public static void main(String[] args) {
        List<InputStream> list = new ArrayList<InputStream>();
        try {
            list.add(new FileInputStream(new File("E:/var/Z1C01500174Schedule.pdf")));
            list.add(new FileInputStream(new File("E:/var/Z1C01500099Receipt.pdf")));
            OutputStream out = new FileOutputStream(new File("E:/var/result.pdf"));

            doMerge(list, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/
	
	public static void doMerge(List<InputStream> list, OutputStream outputStream){
		try{
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			
			for (InputStream in : list) {
			    PdfReader reader = new PdfReader(in);
			    for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			        document.newPage();
			        //import the page from source pdf
			        PdfImportedPage page = writer.getImportedPage(reader, i);
			        //add the page to the destination pdf
			        cb.addTemplate(page, 0, 0);
			    }
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}