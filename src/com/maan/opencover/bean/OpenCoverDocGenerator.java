package com.maan.opencover.bean;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import rsa.pdf.HeaderFooterImage;
import rsa.pdf.PDFCreatorBean;
import rsa.pdf.PDFDisplay;
import rsa.pdf.PdfPTableCreation;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.MasterController.NumberToWord.DefaultProcessor;
import com.maan.common.LogUtil;
import com.maan.common.exception.BaseException;


public class OpenCoverDocGenerator {
	private static final Logger logger = LogUtil.getLogger(OpenCoverDocGenerator.class);
	final static transient private String TAB = "\t\t";
	final static transient private String DEBIT = "Debit";
	final static transient private String CREDIT = "Credit";
	
	public void writeDebitPDF(final String fontPath,final String PolicyNo,final String filePath,final String url, String mode, List<HashMap<String,Object>> list)throws BaseException	{

		try{
			String[][] sourceInfo=null;
			Map<String,Object> info=list.get(0);
			String debitNoteNo=replaceNull((String)info.get("DEBIT_NOTE_NO"),"");
			double netPremium=Double.parseDouble(replaceNull((String)info.get("NET_AMOUNT")+"","0"));
			double issuanceFee=Double.parseDouble(replaceNull((String)info.get("ISSUANCE_FEE_DEBIT"),"0"));
			double depositAmt=Double.parseDouble(replaceNull((String)info.get("DEPOSIT_AMOUNT"),"0"));
			int decimalDigit=Integer.parseInt(replaceNull((String)info.get("DECIMAL_PLACES"),"0"));
			PDFCreatorBean pdfBean=new PDFCreatorBean();
			pdfBean.setUsrModeController(mode);
			HeaderFooterImage pageWater=new rsa.pdf.HeaderFooterImage();
			Document document = new Document(PageSize.A4, 50, 50, 80, 120);
			PDFDisplay pdis = new PDFDisplay();
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "", BrokerPoBox = "", BrokerCompany="", arAccountNo="";
			/*if("A".equalsIgnoreCase((String)info.get("DEBIT_NOTE_NAME")))
			{
				BrokerCompany = replaceNull((String)info.get("CUST_COMPANY"),(replaceNull((String)info.get("CUST_FIRST_NAME"),"")+replaceNull((String)info.get("CUST_LAST_NAME"),"")));
				Brokeraddress1 = replaceNull((String)info.get("CUST_ADDR1"),"");
				BrokerAddress2 = replaceNull((String)info.get("CUST_ADDR2"),""); 
				BrokerCountry = replaceNull((String)info.get("CUST_COUNTRY"),""); 
				BrokerEmirate = replaceNull((String)info.get("CUST_CITY"),"");
				BrokerPoBox = replaceNull((String)info.get("CUST_POBOX"),"");
				arAccountNo= replaceNull((String)info.get("CUST_AR_NO"),"");
			}else
			{
				BrokerCompany = replaceNull((String)info.get("BROKER_COMPANY"),"");
				Brokeraddress1 = replaceNull((String)info.get("BROKER_ADDR1"),"");
				BrokerAddress2 = replaceNull((String)info.get("BROKER_ADDR2"),""); 
				BrokerCountry = replaceNull((String)info.get("BROKER_COUNTRY"),""); 
				BrokerEmirate = replaceNull((String)info.get("BROKER_CITY"),"");
				BrokerPoBox = replaceNull((String)info.get("BROKER_POBOX"),"");
				arAccountNo= replaceNull((String)info.get("BROKER_AR_NO"),"");
			}*/
			if("A".equalsIgnoreCase((String)info.get("DEBIT_NOTE_NAME"))){
				sourceInfo=pdfBean.getCustomerInfo((String)info.get("CUSTOMER_ID"), (String)info.get("BRANCH_CODE"));
			}else{
				sourceInfo=pdfBean.getSourceInfo((String)info.get("BROKER_ID"), (String)info.get("BRANCH_CODE"));
			}
			if(sourceInfo!=null && sourceInfo.length>0)
			{
				BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
				Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
				BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
				BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
				BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
				arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
			}

			String braName = replaceNull((String)info.get("BRANCH_NAME"),"");
			String headImage  = replaceNull((String)info.get("HEADER_IMG"),"");
			String footImage  = replaceNull((String)info.get("FOOTER_IMG"),"");
			String signImage  = replaceNull((String)info.get("SIGN_IMG"),"");
			String currencyType1  = replaceNull((String)info.get("CURRENCY_ABBREVIATION"),"");
			String fills  = replaceNull((String)info.get("CURRENCY_DECIMAL_NAME"),"");
			String cols = replaceNull((String)info.get("CURRENCY_DECIMAL_DIGIT"),"");
			pageWater.setImagePath(url+"\\"+headImage);
			pageWater.setFooterImagePath(url+"\\"+footImage);
			if("Test".equalsIgnoreCase(mode))
				pageWater.setDisplayText("TEST DEBIT");
			pageWater.setCols(cols);
			pageWater.setCid(replaceNull((String)info.get("ORIGINATION_COUNTRY_ID"),"1"));
			pageWater.setFontPath(fontPath);
			pageWater.setDebitPDF(DEBIT);
			try {
					PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(filePath)); 
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					Font fontTextNormal, fontTextUnderLine;
					tableCreation.setTable(8);
					BaseFont arialFont;
					arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
					fontTextUnderLine = new Font(arialFont, 9, Font.UNDERLINE| Font.BOLD);
					fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					debitNoteNo=debitNoteNo.length()>0?debitNoteNo.substring(2):"";
					debitNoteNo=debitNoteNo.substring(0,2)+"-"+debitNoteNo.substring(2,4)+"-"+debitNoteNo.substring(4,5)+"-"+debitNoteNo.substring(5,debitNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(debitNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+replaceNull((String)info.get("DEBIT_NOTE_DATE"),""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Debit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+PolicyNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+replaceNull((String)info.get("PRODUCT_NAME"),""), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (info.get("CUST_COMPANY")== null||"".equalsIgnoreCase((String)info.get("CUST_COMPANY"))) 
					{
						tableCreation1.insertCell(":"+TAB+ (replaceNull((String)info.get("CUST_FIRST_NAME"),"")+ replaceNull((String)info.get("CUST_LAST_NAME"),"")).toUpperCase(), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(replaceNull((String)info.get("CUST_COMPANY"),"")	+ ",\t\t\t" + replaceNull((String)info.get("CUST_FIRST_NAME"),"") + replaceNull((String)info.get("CUST_LAST_NAME"),"")).toUpperCase(), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have debited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					tableCreation1.insertCell("\n   Premium due in respect of Marine Insurance", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.TOP| Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("\n   "+replaceNull((String)info.get("DEPOSIT_TYPE"),""), fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell(pdis.decimalFormat(depositAmt,decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("\n"+"   "+"Issuance Fee\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,5, 0);
					tableCreation1.insertCell("\n\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,1, 0);
					tableCreation1.insertCell("\n"+pdis.decimalFormat(issuanceFee, decimalDigit)+"\n\n", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT | Rectangle.BOTTOM,2, 5);
					tableCreation1.insertCell("   "+replaceNull((String)info.get("CORE_LOGIN_ID"),""), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
					tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					DefaultProcessor processor = new DefaultProcessor();
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " and "+fills+" "+filsWord+" only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					String urlSign;
					urlSign = url+"\\"+signImage;
					Image signatureImage;
					signatureImage = Image.getInstance(urlSign);
					signatureImage.scaleToFit(156, 107);
					tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Important Client Notice: The premium shown above is due for payment within a maximum period of 30 days from the date of this Debit Note.  Non-payment of the total premium amount due within 30 days will result in the issuance of a cancellation notice of this insurance coverage or an additional administrative surcharge of 5% of the amount due on a monthly basis. Thank you for your cooperation.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	public void writeCreditPDF(final String fontPath,final String PolicyNo,final String filePath,final String url, String mode, List<HashMap<String,Object>> list)throws BaseException	{

		try{
			String[][] sourceInfo=null;
			Map<String,Object> info=list.get(0);
			String creditNoteNo=replaceNull((String)info.get("CREDIT_NOTE_NO"),"");
			double netPremium=Double.parseDouble(replaceNull((String)info.get("COMMISSION_AMOUNT")+"","0"));
			int decimalDigit=Integer.parseInt(replaceNull((String)info.get("DECIMAL_PLACES"),"0"));
			PDFCreatorBean pdfBean=new PDFCreatorBean();
			pdfBean.setUsrModeController(mode);
			HeaderFooterImage pageWater=new rsa.pdf.HeaderFooterImage();
			Document document = new Document(PageSize.A4, 50, 50, 80, 120);
			PDFDisplay pdis = new PDFDisplay();
			String  Brokeraddress1 = "", BrokerAddress2 = "", BrokerCountry = "", BrokerEmirate = "", BrokerPoBox = "", BrokerCompany="", arAccountNo="";
			/*BrokerCompany = replaceNull((String)info.get("BROKER_COMPANY"),"");
			Brokeraddress1 = replaceNull((String)info.get("BROKER_ADDR1"),"");
			BrokerAddress2 = replaceNull((String)info.get("BROKER_ADDR2"),""); 
			BrokerCountry = replaceNull((String)info.get("BROKER_COUNTRY"),""); 
			BrokerEmirate = replaceNull((String)info.get("BROKER_CITY"),"");
			BrokerPoBox = replaceNull((String)info.get("BROKER_POBOX"),"");
			String arAccountNo= replaceNull((String)info.get("BROKER_AR_NO"),"");*/
			sourceInfo=pdfBean.getSourceInfo((String)info.get("BROKER_ID"), (String)info.get("BRANCH_CODE"));
			if(sourceInfo!=null && sourceInfo.length>0)
			{
				BrokerCompany=(sourceInfo[0][0]==null?"":sourceInfo[0][0])+" "+(sourceInfo[0][1]==null?"":sourceInfo[0][1]);
				Brokeraddress1=sourceInfo[0][2]==null?"":sourceInfo[0][2];
				BrokerAddress2=sourceInfo[0][3]==null?"":sourceInfo[0][3];
				BrokerCountry=sourceInfo[0][4]==null?"":sourceInfo[0][4];
				BrokerPoBox=sourceInfo[0][5]==null?"":sourceInfo[0][5];
				arAccountNo=sourceInfo[0][6]==null?"":sourceInfo[0][6];
			}
			String braName = replaceNull((String)info.get("BRANCH_NAME"),"");
			String headImage  = replaceNull((String)info.get("HEADER_IMG"),"");
			String footImage  = replaceNull((String)info.get("FOOTER_IMG"),"");
			String signImage  = replaceNull((String)info.get("SIGN_IMG"),"");
			String currencyType1  = replaceNull((String)info.get("CURRENCY_ABBREVIATION"),"");
			String fills  = replaceNull((String)info.get("CURRENCY_DECIMAL_NAME"),"");
			String cols = replaceNull((String)info.get("CURRENCY_DECIMAL_DIGIT"),"");
			pageWater.setImagePath(url+"\\"+headImage);
			pageWater.setFooterImagePath(url+"\\"+footImage);
			if("Test".equalsIgnoreCase(mode))
				pageWater.setDisplayText("TEST CREDIT");
			pageWater.setCols(cols);
			pageWater.setCid(replaceNull((String)info.get("ORIGINATION_COUNTRY_ID"),"1"));
			pageWater.setFontPath(fontPath);
			pageWater.setDebitPDF(CREDIT);
			try {
					PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(filePath)); 
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1,tableCreation2;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation2 = new PdfPTableCreation();
					Font fontTextNormal, fontTextUnderLine;
					tableCreation.setTable(8);
					BaseFont arialFont;
					arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
					fontTextUnderLine = new Font(arialFont, 9, Font.UNDERLINE| Font.BOLD);
					fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation2.setTable(2);
					
					tableCreation2.insertCell(TAB+BrokerCompany, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(Brokeraddress1.length()>0)
						tableCreation2.insertCell(TAB+Brokeraddress1, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					if(BrokerAddress2.length()>0)
						tableCreation2.insertCell(TAB+BrokerAddress2, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerEmirate.length()>0)
						tableCreation2.insertCell(TAB+BrokerEmirate, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerCountry.length()>0)
						tableCreation2.insertCell(TAB+BrokerCountry, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					if(BrokerPoBox.length()>0)
						tableCreation2.insertCell(TAB+BrokerPoBox, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation.insertCell(tableCreation2.getTable(), Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.setTable(7);
					tableCreation1.insertCell("Branch", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					creditNoteNo=creditNoteNo.length()>0?creditNoteNo.substring(2):"";
					creditNoteNo=creditNoteNo.substring(0,2)+"-"+creditNoteNo.substring(2,4)+"-"+creditNoteNo.substring(4,5)+"-"+creditNoteNo.substring(5,creditNoteNo.length());
					tableCreation1.insertCell(":"+TAB+(creditNoteNo), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation1.insertCell("Doc. Date", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+replaceNull((String)info.get("CREDIT_NOTE_DATE"),""), fontTextNormal, Rectangle.NO_BORDER,5, 0);
					
					tableCreation1.insertCell("A/R No", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.insertCell(":"+TAB+arAccountNo, fontTextNormal, Rectangle.NO_BORDER,5, 0);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,3, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					
					tableCreation.insertCell("Credit Note", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(13);
					tableCreation1.insertCell("Policy Number", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+PolicyNo, fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Policy Type", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation1.insertCell(":"+TAB+replaceNull((String)info.get("PRODUCT_NAME"),""), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					tableCreation1.insertCell("Original Insured", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					if (info.get("CUST_COMPANY")== null||"".equalsIgnoreCase((String)info.get("CUST_COMPANY"))) 
					{
						tableCreation1.insertCell(":"+TAB+ (replaceNull((String)info.get("CUST_FIRST_NAME"),"")+ replaceNull((String)info.get("CUST_LAST_NAME"),"")).toUpperCase(), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					} 
					else 
					{
						tableCreation1.insertCell(":"+TAB+(replaceNull((String)info.get("CUST_COMPANY"),"")	+ ",\t\t\t" + replaceNull((String)info.get("CUST_FIRST_NAME"),"") + replaceNull((String)info.get("CUST_LAST_NAME"),"")).toUpperCase(), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					if("One Stop".equalsIgnoreCase((String)info.get("OPEN_COVER_TYPE")))
					{
						tableCreation1.insertCell("Period of Cover", fontTextNormal, Rectangle.NO_BORDER,3, 0);
						tableCreation1.insertCell(":"+TAB+replaceNull((String)info.get("INCEPTION_DATE"),"")+TAB+" TO "+TAB+replaceNull((String)info.get("EXPIRY_DATE"),""), fontTextNormal, Rectangle.NO_BORDER,10, 0);
					}
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,3, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("Kindly note what we have credited your account as follows:\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation1.setTable(8);
					tableCreation1.setTableSpacing(2f);
					tableCreation1.insertCell("Description", fontTextNormal, Rectangle.BOX,5, 2);
					tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX,1, 2);
					tableCreation1.insertCell("Amount", fontTextNormal, Rectangle.BOX,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("   "+"Brokerage/Commission @ "+replaceNull((String)info.get("COMMISSION"),"")+"%", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT | Rectangle.RIGHT ,1, 0);
					tableCreation1.insertCell(pdis.decimalFormat(netPremium,decimalDigit)+TAB+TAB+TAB+TAB, fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,2, 5);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,5, 0);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,1, 2);
					tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT ,2, 2);
					tableCreation1.insertCell("   "+replaceNull((String)info.get("CORE_LOGIN_ID"),""), fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM | Rectangle.LEFT,5, 0);
					tableCreation1.insertCell(currencyType1, fontTextNormal, Rectangle.TOP | Rectangle.BOTTOM ,1, 2);
					tableCreation1.insertCell(pdis.decimalFormat(netPremium, decimalDigit)+TAB+TAB+TAB+TAB+"\n", fontTextNormal,Rectangle.TOP | Rectangle.BOTTOM | Rectangle.RIGHT,2, 5);
					DefaultProcessor processor = new DefaultProcessor();
					String net=pdis.decimalFormat(netPremium, decimalDigit)+"";
					String word1="",word2="",filsWord="";
					if(net.indexOf(".")!=-1)
					{
						word1=net.substring(0, net.indexOf("."));
						word2=net.substring(net.indexOf(".")+1, net.length());
						filsWord=processor.getName(word2);
					}else
					{
						word1=net;
					}
					filsWord=filsWord.trim().length()<=0?"Zero":filsWord;
					tableCreation1.insertCell("   "+currencyType1+" "+processor.getName(word1.replaceAll(",","")) + " And "+fills+" "+filsWord+" Only", fontTextNormal, Rectangle.RECTANGLE,8, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					String urlSign;
					urlSign = url+"\\"+signImage;
					Image signatureImage;
					signatureImage = Image.getInstance(urlSign);
					signatureImage.scaleToFit(156, 107);
					tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,8, 1);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
		}
		catch (BaseException e) {
			throw new BaseException(e, "Error");
		}
		finally 
		{
			document.close();
		}
	}catch (Exception e) 
	{
		logger.debug(e);
	}
	}
	public static String replaceNull(String value, String replacewith)
	{
		return value==null || value.equals("null")?replacewith:value;
	}
}
