package com.maan.common;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;
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
import com.maan.common.exception.BaseException;
import com.maan.quotation.service.PremiumService;
import com.maan.webservice.dao.PolicyGenerationDAO;


public class DocGenerator {
	final static transient private String TAB = "\t\t";
	final static transient private String DEBIT = "Debit";
	private static final Logger logger = LogUtil.getLogger(DocGenerator.class);
	public static void writeDebitPDF(final String fontPath,final String PolicyNo,final String filePath,final String url, String mode, List<Map<String,Object>> list, List<Object> endType, String applicationNo, String branchCode, String belongingBranch)throws BaseException	{

		try{
			List<Map<String, Object>> commodityList=null;
			Map<String,Object> conditions=null;
			Map<String,Object> info=list.get(0);
			int decimalDigit=Integer.parseInt(StringUtils.defaultIfEmpty(info.get("DECIMAL_PLACES").toString(),"0"));
			PDFCreatorBean pdfBean=new PDFCreatorBean();
			PremiumService pService=new PremiumService();
			pdfBean.setUsrModeController(mode);
			HeaderFooterImage pageWater=new rsa.pdf.HeaderFooterImage();
			Document document = new Document(PageSize.A4, 50, 50, 80, 120);
			PDFDisplay pdis = new PDFDisplay();
			String custCompany="", custAddress1="",custAddress2="",custCountry="",custPoBox="";
			boolean financial=new PolicyGenerationDAO().isFinancial(applicationNo);
			String productId = StringUtils.defaultIfEmpty(info.get("PRODUCT_ID").toString(),"");
			String orginalPolicyNo = StringUtils.defaultIfEmpty((String)info.get("ORIGINAL_POLICY_NO"),"");
			String lcNo = StringUtils.defaultIfEmpty((String)info.get("LC_NUMBER"),"");
			String policyFee = StringUtils.defaultIfEmpty(info.get("POLICY_FEE").toString(),"0");
			String excessPremium = StringUtils.defaultIfEmpty(info.get("EXCESS_PREMIUM").toString(),"0");
			String braName = StringUtils.defaultIfEmpty((String)info.get("BRANCH_NAME"),"");
			String headImage  = StringUtils.defaultIfEmpty((String)info.get("HEADER_IMG"),"");
			String footImage  = StringUtils.defaultIfEmpty((String)info.get("FOOTER_IMG"),"");
			String signImage  = StringUtils.defaultIfEmpty((String)info.get("SIGN_IMG"),"");
			String currencyType1  = StringUtils.defaultIfEmpty((String)info.get("CURRENCY_ABBREVIATION"),"");
			String cols = StringUtils.defaultIfEmpty(info.get("CURRENCY_DECIMAL_DIGIT").toString(),"");
			String endtPremYN  = StringUtils.defaultIfEmpty((String)info.get("ENDT_PREM_YN"),"");
			String endtClausesYN  = StringUtils.defaultIfEmpty((String)info.get("ENDT_CLAUSES_YN"),"");
			/*custCompany = StringUtils.defaultIfEmpty((String)info.get("CUST_COMPANY"),(StringUtils.defaultIfEmpty((String)info.get("CUST_FIRST_NAME"),"")+StringUtils.defaultIfEmpty((String)info.get("CUST_LAST_NAME"),"")));
			custAddress1 = StringUtils.defaultIfEmpty((String)info.get("CUST_ADDR1"),"");
			custAddress2 = StringUtils.defaultIfEmpty((String)info.get("CUST_ADDR2"),""); 
			custCountry = StringUtils.defaultIfEmpty((String)info.get("CUST_COUNTRY"),""); 
			custEmirate = StringUtils.defaultIfEmpty((String)info.get("CUST_CITY"),"");
			custPoBox = StringUtils.defaultIfEmpty((String)info.get("CUST_POBOX"),"");*/
			
			String[][] custInfo=pdfBean.getCustomerInfo(info.get("CUSTOMER_ID").toString(), branchCode);
			if(custInfo!=null && custInfo.length>0)
			{
				custCompany=(custInfo[0][0]==null?"":custInfo[0][0])+" "+(custInfo[0][1]==null?"":custInfo[0][1]);
				custAddress1=custInfo[0][2]==null?"":custInfo[0][2];
				custAddress2=custInfo[0][3]==null?"":custInfo[0][3];
				custCountry=custInfo[0][4]==null?"":custInfo[0][4];
				custPoBox=custInfo[0][5]==null?"":custInfo[0][5];
			}
			double sumInsured=Double.parseDouble(StringUtils.defaultIfEmpty(info.get("SUMINSURED").toString(),"0"));
			double prevSumInsured=Double.parseDouble(StringUtils.defaultIfEmpty(info.get("PREV_SUMINSURED").toString(),"0"));
			double totalPremium=Double.parseDouble(StringUtils.defaultIfEmpty(info.get("FINAL_TOTAL").toString(),"0"));
			double netPremium=Double.parseDouble(StringUtils.defaultIfEmpty(info.get("NET_PREMIUM").toString(),"0"));
			
			pageWater.setImagePath(url+"/"+headImage);
			pageWater.setFooterImagePath(url+"/"+footImage);
			if("Test".equalsIgnoreCase(mode))
				pageWater.setDisplayText("TEST ENDORSEMENT");
			pageWater.setCols(cols);
			pageWater.setCid(StringUtils.defaultIfEmpty(info.get("ORIGINATION_COUNTRY_ID").toString(),"1"));
			pageWater.setFontPath(fontPath);
			pageWater.setDebitPDF(DEBIT);
			try {
					PdfWriter writer=PdfWriter.getInstance(document,new FileOutputStream(filePath)); 
					writer.setPageEvent(pageWater);
					document.open();
					PdfPTableCreation tableCreation,tableCreation1;
					tableCreation = new PdfPTableCreation();
					tableCreation1 = new PdfPTableCreation();
					tableCreation.setTable(2);
					BaseFont arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,BaseFont.NOT_EMBEDDED);
					Font fontTextUnderLine = new Font(arialFont, 9, Font.UNDERLINE| Font.BOLD);
					Font fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation1.setTable(2);
					tableCreation1.insertCell("Department", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell(":"+TAB+"CARGO & MARINE HULL", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\nClass of Insurance", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\n:"+TAB+"Cargo", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\nBranch/Agency", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\n:"+TAB+braName, fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\nDate", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\n:"+TAB+StringUtils.defaultIfEmpty((String)info.get("POLICY_DATE"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,1, 0);
					
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.TOP,2, 0);
					if("11".equals(productId)){
						tableCreation.insertCell("Endorsement No."+StringUtils.defaultIfEmpty(info.get("ENDT_NO").toString(),"")+" attaching to and forming part of Policy No. "+orginalPolicyNo.substring(0, orginalPolicyNo.lastIndexOf("-"))+", Insurance Certificate No. "+StringUtils.defaultIfEmpty((String)info.get("ORIGINAL_POLICY_NO"),"")+", in the name of "+StringUtils.defaultIfEmpty((String)info.get("CUST_NAME"),"")+" &/or their all Subsidiaries and Group Companies.", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					}else{
						tableCreation.insertCell("Endorsement No."+StringUtils.defaultIfEmpty(info.get("ENDT_NO").toString(),"")+" attaching to and forming part of Policy No. "+orginalPolicyNo+", in the name of "+StringUtils.defaultIfEmpty((String)info.get("CUST_NAME"),"")+" &/or their all Subsidiaries and Group Companies.", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					}
					if(lcNo.trim().length()>0){
						tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
						tableCreation.insertCell("\nL/C No. "+lcNo, fontTextNormal, Rectangle.NO_BORDER,2, 0);
					}
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("\n", fontTextNormal, Rectangle.TOP,2, 0);
					tableCreation.insertCell("NOTWITHSTANDING anything contained in the Policy/Certificate to the contrary, it is hereby declared and agreed that the following has/ have been incorporated/amended under the above policy/certificate as from the date of inception (OR AS FROM "+StringUtils.defaultIfEmpty((String)info.get("POLICY_DATE"),"")+")", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					
					if(endType.contains("13")){
						commodityList=pService.getQuotationInsuredValue(applicationNo,belongingBranch);
						if(!CollectionUtils.isEmpty(commodityList)){
							Map<String, Object> cMap=null;
							String invoiceNo="";
							for (int i = 0; i < commodityList.size(); i++) {
								cMap=(Map<String, Object>)commodityList.get(i);
								invoiceNo=StringUtils.defaultIfEmpty((String)cMap.get("INVOICE_NUMBER"),"");
								if(i==0){
									tableCreation.insertCellPad("\n�   Subject Matter Insured ", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
									tableCreation.insertCellPad("\n:"+TAB+StringUtils.defaultIfEmpty((String)cMap.get("DESCRIPTION"),"")+(invoiceNo.trim().length()>0?" INV# "+invoiceNo:""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
								}else{
									tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
									tableCreation.insertCellPad(" "+TAB+StringUtils.defaultIfEmpty((String)cMap.get("DESCRIPTION"),"")+(invoiceNo.trim().length()>0?" INV# "+invoiceNo:""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
								}
							}
						}
					}
					if(endType.contains("1")){
						tableCreation.insertCellPad("�   Additional Assured (ASSUREDS)", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+custCompany, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						if(custAddress1.length()>0){
							tableCreation.insertCellPad(TAB, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(" "+TAB+custAddress1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}if(custAddress2.length()>0){
							tableCreation.insertCellPad(TAB, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(" "+TAB+custAddress2, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}if(custCountry.length()>0){
							tableCreation.insertCellPad(TAB, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(" "+TAB+custCountry, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}if(custPoBox.length()>0){
							tableCreation.insertCellPad(TAB, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(" "+TAB+custPoBox, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}
					}if(endType.contains("8") || endType.contains("13") || endType.contains("14")){
						tableCreation.insertCellPad("�   Sum Insured ", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+(sumInsured>prevSumInsured?"Increased from ":(sumInsured<prevSumInsured?"Decreased from ":""))+currencyType1+TAB+pdis.decimalFormat(prevSumInsured, decimalDigit)+" To "+currencyType1+TAB+pdis.decimalFormat(sumInsured, decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("6")){
						tableCreation.insertCellPad("�   Voyage details ", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("VOYAGE"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("11")){
						tableCreation.insertCellPad("�   Basis of valuation", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("BASIS_OF_VAL").toString(),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("5") || endType.contains("22")){
						tableCreation.insertCellPad("�   Cover", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("COVER_NAME"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("8")){
						tableCreation.insertCellPad("�   Currency", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("CURRENCY"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}
					if(endType.contains("2")){
						tableCreation.insertCellPad("�   LC Bank", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("BANK_NAME"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad("�   LC Number", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+lcNo, fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}
					if(endType.contains("3")){
						tableCreation.insertCellPad("�   Bill of Lading/Air Way Bill Number", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("BL_AWB_NO"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("4") || endType.contains("6")){
						tableCreation.insertCellPad("�   Settling Agent ", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("SETTLING_AGENT"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("5")){
						tableCreation.insertCellPad("�   Conveyance", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("CONVEYANCE"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(endType.contains("9")){
						tableCreation.insertCellPad("�   War & SRCC", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+StringUtils.defaultIfEmpty((String)info.get("WSRCC"),""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}if(financial && "Y".equalsIgnoreCase(endtPremYN.trim())){
						tableCreation.insertCellPad("�   Premium", fontTextNormal, Rectangle.NO_BORDER,1, 0, 40);
						tableCreation.insertCellPad(":"+TAB+currencyType1+TAB+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("PREMIUM").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad("Consequently, "+(netPremium>0?" an additional ":(netPremium<0?" a refund ":""))+"premium of "+currencyType1+TAB+pdis.decimalFormat(netPremium, decimalDigit)+", as calculated hereunder becomes due from (TO) the Insured:-", fontTextNormal, Rectangle.NO_BORDER,2, 0,50);
						
						tableCreation.insertCellPad("\nSum Insured", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad("\n:"+TAB+currencyType1+TAB+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("SUMINSURED").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 0);
					}	
					document.add(tableCreation.getTable());
					if("Y".equalsIgnoreCase(endtPremYN.trim()) || "Y".equalsIgnoreCase(endtClausesYN.trim())){
						if(financial && "Y".equalsIgnoreCase(endtPremYN.trim())){
						tableCreation.setTable(4);
						tableCreation.insertCellPad("\nMarine Rate Chargeable @ "+StringUtils.defaultIfEmpty(info.get("RATE").toString(),"")+"%", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad("\n:"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad("\n"+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("MARINE_PREMIUM").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("War Rate Chargeable @ "+StringUtils.defaultIfEmpty(info.get("WARRATE").toString(),"")+"%", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad(""+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("WAR_PREMIUM").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,4, 0,50);
						
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation1.setTable(4);
						tableCreation1.insertCell(Rectangle.TOP, 4);
						tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,2, 0);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("Total", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad(pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("TOTAL").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("\nLess Marine & War Premiums already Charged", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad("\n:"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad("\n"+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(info.get("LESS_PREMIUM").toString(),"0")), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,4, 0,50);
						
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation1.setTable(4);
						tableCreation1.insertCell(Rectangle.TOP, 4);
						tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,2, 0);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad((totalPremium>0?"Additional ":(totalPremium<0?"Refund ":""))+"Premium", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad(pdis.decimalFormat(totalPremium, decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						if(Double.parseDouble(excessPremium)>0){
							tableCreation.insertCellPad("Excess Premium ", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
							tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(pdis.decimalFormat(Double.parseDouble(excessPremium), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
							tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}if(Double.parseDouble(policyFee)>0){
							tableCreation.insertCellPad("Issuance Fee ", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
							tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
							tableCreation.insertCellPad(pdis.decimalFormat(Double.parseDouble(policyFee), decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
							tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						}
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,4, 0,50);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation1.setTable(4);
						tableCreation1.insertCell(Rectangle.TOP, 4);
						tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,2, 0);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.insertCellPad("Total"+(netPremium>0?" Additional ":(netPremium<0?" Refund ":""))+" Premium", fontTextNormal, Rectangle.NO_BORDER,1, 0,50);
						tableCreation.insertCellPad(":"+TAB+currencyType1, fontTextNormal, Rectangle.NO_BORDER,1, 0);
						tableCreation.insertCellPad(pdis.decimalFormat(netPremium, decimalDigit), fontTextNormal, Rectangle.NO_BORDER,1, 1);
						tableCreation.insertCellPad("", fontTextNormal, Rectangle.NO_BORDER,1, 0);
						
						tableCreation.getTable().setTotalWidth(new float[]{30f,5f, 10f, 15f});
						document.add(tableCreation.getTable());
						}
						conditions=pService.getConditionsInfo(applicationNo, belongingBranch);
						if(!CollectionUtils.isEmpty(conditions) && "Y".equalsIgnoreCase(endtClausesYN.trim())){
							tableCreation.setTable(2);
							tableCreation.insertCell("\n\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
							writeConditions(tableCreation, (List<Object>)conditions.get("clausesDesc"), "Clauses", fontTextNormal, fontTextUnderLine);
							writeConditions(tableCreation, (List<Object>)conditions.get("extraClausesDesc"), "", fontTextNormal, fontTextUnderLine);
							writeConditions(tableCreation, (List<Object>)conditions.get("warrantyDesc"), "Warranties", fontTextNormal, fontTextUnderLine);
							writeConditions(tableCreation, (List<Object>)conditions.get("exclusionsDesc"), "Exclusions", fontTextNormal, fontTextUnderLine);
							tableCreation.getTable().setKeepTogether(true);
							document.add(tableCreation.getTable());
						}
						int index=0;
						List<Map<String, Object>> deductibles=pService.getPolicyDeductibles(applicationNo);
						if(deductibles.size()>1)
						{
							tableCreation1.setTable(4);
							tableCreation1.insertCell("\n"+"Deductible", fontTextUnderLine, Rectangle.NO_BORDER,4, 0);
							tableCreation1.insertCell("Claims, if any, are payable subject to an excess for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
							tableCreation1.insertCell("S.No", fontTextNormal, Rectangle.BOX, 1, 2);
							tableCreation1.insertCell("Subject-Matter Insured", fontTextNormal, Rectangle.BOX, 1, 2);
							tableCreation1.insertCell("Excess", fontTextNormal, Rectangle.BOX, 1, 2);
							tableCreation1.insertCell("Currency", fontTextNormal, Rectangle.BOX, 1, 0);
							for(int e=0;e<deductibles.size();e++)
							{
								Map<String, Object> map=(Map<String, Object>)deductibles.get(e);
								if(!CollectionUtils.isEmpty(map)){
									String excess = StringUtils.defaultIfEmpty((String)map.get("COMMODITY_EXCESS_PREMIUM"), "0");
									if(!"0".equals(excess))
									{
										tableCreation1.insertCell(String.valueOf(++index), fontTextNormal, Rectangle.BOX, 1, 2);
										tableCreation1.insertCell(StringUtils.defaultIfEmpty((String)map.get("DESCRIPTION"), ""), fontTextNormal, Rectangle.BOX, 1, 0);
										tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(excess), decimalDigit), fontTextNormal, Rectangle.BOX, 1, 1);
										tableCreation1.insertCell(StringUtils.defaultIfEmpty((String)map.get("CURRENCY_NAME"), currencyType1), fontTextNormal, Rectangle.BOX, 1, 0);
									}
								}
							}
						}else if(deductibles.size()>0)
						{
							tableCreation1.setTable(4);
							for(int e=0;e<deductibles.size();e++)
							{
								Map<String, Object> map=(Map<String, Object>)deductibles.get(e);
								if(!CollectionUtils.isEmpty(map)){
									String excess = StringUtils.defaultIfEmpty((String)map.get("COMMODITY_EXCESS_PREMIUM"), "0");
									if(!"0".equals(excess))
									{
										tableCreation1.insertCell("\n"+"Deductible", fontTextUnderLine, Rectangle.NO_BORDER,4, 0);
										tableCreation1.insertCell("Claims, if any, are payable subject to an excess of "+StringUtils.defaultIfEmpty((String)map.get("CURRENCY_NAME"), currencyType1)+" "+pdis.decimalFormat(Double.parseDouble(StringUtils.defaultIfEmpty(map.get("COMMODITY_EXCESS_PREMIUM").toString(), "0")), decimalDigit)+" for each and every loss.", fontTextNormal, Rectangle.NO_BORDER, 4, 0);
										++index;
									}
								}
							}
						}
						if(index>0){
							tableCreation1.getTable().setWidths(new float[] {10f, 30f, 20f, 10f});
							tableCreation.setTable(2);
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,2, 0);
							document.add(tableCreation.getTable());
						}
					}
					tableCreation.setTable(2);
					tableCreation.insertCell("\nSubject otherwise to the same terms, conditions and limitations of the Policy/Certificate.", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("\n\n\n\n\n\n\n\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					String urlSign;
					urlSign = url+"\\"+signImage;
					Image signatureImage;
					signatureImage = Image.getInstance(urlSign);
					signatureImage.scaleToFit(156, 107);
					if("Y".equalsIgnoreCase((String)info.get("PDF_STAMP_STATUS"))){
						tableCreation.insertCell(signatureImage, Rectangle.NO_BORDER,2, 1);
					}else{
						tableCreation.insertCell("Emirates Insurance Co.", fontTextNormal, Rectangle.NO_BORDER,2, 5);
					}
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,2, 0);
					tableCreation.getTable().setKeepTogether(true);
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
	public static void writeConditions(PdfPTableCreation tableCreation, List<Object> list, String title, Font fontTextNormal, Font fontTextUnderLine)throws BaseException {
		Map<String, String> map=null;
		if(!CollectionUtils.isEmpty(list)){
			if(StringUtils.isNotEmpty(title)){
				tableCreation.insertCell("\n"+title, fontTextUnderLine, Rectangle.NO_BORDER,2, 0);
			}
			for (int i = 0; i < list.size(); i++) {
				map=(Map<String,String>)list.get(i);
				String value=map.get("CODEDESC");
				if(StringUtils.isNotEmpty(value)){
					tableCreation.insertCell(value, fontTextNormal, Rectangle.NO_BORDER,2, 0);
				}
			}
		}
	}
}
