package rsa.opencoverpdf;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.util.CollectionUtils;

import rsa.pdf.HeaderFooterImage;
import rsa.pdf.PDFDisplay;
import rsa.pdf.PdfPTableCreation;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.Home.DataManipualtion.DataSelectCustomer;
import com.maan.common.LogUtil;
import com.maan.services.util.runner;

public class ScheduleBean {
	private static final Logger logger = LogUtil.getLogger(ScheduleBean.class);
	final static transient private String TAB = "\t\t";
	private String displayText;
	private String filePath;
	private String imagePath;
	private String footerImagePath;
	private String createDate;
	private String fontPath;
	private String stampImage;
	private String signImage;
	private String footImage;
	private String headImage;
	private String cols;
	private String openCoverNo;
	private String type;
	private String name;
	private String periodOfInsurance;
	private String annualTurnover;
	private String Voyage[][];
	private String toleranceName;
	private String commodityDesc[][];
	private String conveyance[][];
	private String classes[][];
	private String warranty[][];
	private String exclusion[][];
	private String warsrcc[][];
	private String warsrccclasses[][];
	private String deductible[][];
	private String rateYN;
	private String cancelClass;
	private String amendedClauseYN;
	private String additionalInsured="";
	private String policyDate="";
	private String endtNo;
	private String branchName;
	private String custName;
	private boolean endt;
	private List<String> list=null;
	/**
	 * @return the additionalInsured
	 */
	public String getAdditionalInsured() {
		return additionalInsured;
	}
	/**
	 * @param additionalInsured the additionalInsured to set
	 */
	public void setAdditionalInsured(String additionalInsured) {
		this.additionalInsured = additionalInsured;
	}
	public String getRateYN() {
		return rateYN;
	}
	public void setRateYN(String rateYN) {
		this.rateYN = rateYN;
	}
	public String[][] getDeductible() {
		return deductible;
	}
	public void setDeductible(String[][] deductible) {
		this.deductible = deductible;
	}
	private String currency;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	private int decimal;
	
	/**
	 * @return the decimal
	 */
	public int getDecimal() {
		return decimal;
	}
	/**
	 * @param decimal the decimal to set
	 */
	public void setDecimal(int decimal) {
		this.decimal = decimal;
	}
	private String rates[][];
	public String[][] getRates() {
		return rates;
	}
	public void setRates(String[][] rates) {
		this.rates = rates;
	}
	public String[][] getWarsrcc() {
		return warsrcc;
	}
	public void setWarsrcc(String[][] warsrcc) {
		this.warsrcc = warsrcc;
	}
	public String[][] getClasses() {
		return classes;
	}
	public void setClasses(String[][] classes) {
		this.classes = classes;
	}
	public String[][] getWarranty() {
		return warranty;
	}
	public void setWarranty(String[][] warranty) {
		this.warranty = warranty;
	}
	public String[][] getExclusion() {
		return exclusion;
	}
	public void setExclusion(String[][] exclusion) {
		this.exclusion = exclusion;
	}
	public String[][] getConveyance() {
		return conveyance;
	}
	public void setConveyance(String[][] conveyance) {
		this.conveyance = conveyance;
	}
	public String[][] getCommodityDesc() {
		return commodityDesc;
	}
	public void setCommodityDesc(String[][] commodityDesc) {
		this.commodityDesc = commodityDesc;
	}
	public String getCols() {
		return cols;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPeriodOfInsurance() {
		return periodOfInsurance;
	}
	public void setPeriodOfInsurance(String periodOfInsurance) {
		this.periodOfInsurance = periodOfInsurance;
	}
	public String getAnnualTurnover() {
		return annualTurnover;
	}
	public void setAnnualTurnover(String annualTurnover) {
		this.annualTurnover = annualTurnover;
	}
	public String[][] getVoyage() {
		return Voyage;
	}
	public void setVoyage(String[][] voyage) {
		Voyage = voyage;
	}
	public String getToleranceName() {
		return toleranceName;
	}
	public void setToleranceName(String toleranceName) {
		this.toleranceName = toleranceName;
	}
	public void setCols(String cols) {
		this.cols = cols;
	}
	public String getStampImage() {
		return stampImage;
	}
	public void setStampImage(String stampImage) {
		this.stampImage = stampImage;
	}
	public String getSignImage() {
		return signImage;
	}
	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}
	public String getFootImage() {
		return footImage;
	}
	public void setFootImage(String footImage) {
		this.footImage = footImage;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getFooterImagePath() {
		return footerImagePath;
	}
	public void setFooterImagePath(String footerImagePath) {
		this.footerImagePath = footerImagePath;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getFontPath() {
		return fontPath;
	}
	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
	
	public void createSchedulePDF() {
		Document document = null;
		PdfWriter writer=null;
		document = new Document(PageSize.A4, 50, 50, 50, 70);
		PdfPTableCreation tableCreation,tableCreation1,tableCreation2,tableCreation3,tableCreation4,tableCreation5,tableCreation6,tableCreation7,tableCreation0; 
		tableCreation=new PdfPTableCreation();
		tableCreation1=new PdfPTableCreation();
		tableCreation2=new PdfPTableCreation();
		tableCreation3=new PdfPTableCreation();
		tableCreation4=new PdfPTableCreation();
		tableCreation5=new PdfPTableCreation();
		tableCreation6=new PdfPTableCreation();
		tableCreation7=new PdfPTableCreation();
		tableCreation0=new PdfPTableCreation();
		
		try {

			try 
			{
				writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
			} 
			catch (Exception eb) 
			{
				logger.info(eb);
			}
			document.open();
			HeaderFooterImage pageWater = new HeaderFooterImage();
			pageWater.onOpenDocument(writer, document);
			pageWater.setImagePath(headImage);
			pageWater.setFooterImagePath(footImage);
			pageWater.setCreateDate(createDate);
			pageWater.setDisplayText(displayText);						
			pageWater.setCols(cols);
			String polGenDate = "";
			String polGenTime = "";
			
			polGenDate = polGenDate+" Time: "+polGenTime;
			pageWater.setPolicyOn(polGenDate);
			pageWater.setSignedImage(signImage);
			pageWater.setStampImage(stampImage);
			pageWater.setFontPath(fontPath);
			writer.setPageEvent(pageWater);
			PDFDisplay pdis=new PDFDisplay(); 
			Font fontHeadBold, fontTextNormal, fontTextUnderLine;
			tableCreation.setTable(8);
			BaseFont arialFont;
			arialFont = BaseFont.createFont(fontPath, BaseFont.CP1252,
					BaseFont.NOT_EMBEDDED);
			fontHeadBold = new Font(arialFont, 9, Font.BOLD);
			fontTextUnderLine = new Font(arialFont, 9, Font.UNDERLINE | Font.BOLD);
			fontTextNormal = new Font(arialFont, 8, Font.NORMAL);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("SCHEDULE", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			if(endt){
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation1.setTable(2);
				tableCreation1.insertCell("DEPARTMENT", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell(":"+TAB+"CARGO & MARINE HULL", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\nCLASS OF INSURANCE", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\n:"+TAB+"CARGO", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\nBRANCH/AGENCY", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\n:"+TAB+branchName, fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\nDATE", fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\n:"+TAB+StringUtils.defaultIfEmpty(policyDate,""), fontTextNormal, Rectangle.NO_BORDER,1, 0);
				tableCreation1.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,2, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,4, 0);
				tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,4, 0);
				
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.TOP,8, 0);
				tableCreation.insertCell("Endorsement No."+StringUtils.defaultIfEmpty(endtNo,"")+" attaching to and forming part of Policy No. "+openCoverNo.substring(0, openCoverNo.lastIndexOf("-"))+", in the name of "+StringUtils.defaultIfEmpty(custName,"")+" &/or their all Subsidiaries and Group Companies.", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.TOP,8, 0);
				tableCreation.insertCell("NOTWITHSTANDING anything contained in the Policy/Certificate to the contrary, it is hereby declared and agreed that the following has/ have been incorporated/amended under the above Open Policy as from the date of inception (OR AS FROM "+policyDate+")", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			}
			tableCreation1.setTable(9);
			tableCreation1.insertCell("POLICY NO", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell(openCoverNo, fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation1.insertCell("TYPE", fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell("Marine Cargo Open Cover", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation1.insertCell("NAME OF ASSURED", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell(additionalInsured.length()>0?additionalInsured:name, fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation1.insertCell("PERIOD OF INSURANCE", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			if(cancelClass!=null && cancelClass.length()>0)
			{
				tableCreation1.insertCell("Always open "+periodOfInsurance+" subject to "+cancelClass+" days Notice of Cancellation." , fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}else
			{
				tableCreation1.insertCell("Always open "+periodOfInsurance+"." , fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			document.add(tableCreation.getTable());
			if(commodityDesc!=null && commodityDesc.length>0)
			{	
				boolean flag=true;
				for(int i=0;i<commodityDesc.length;i++)
				{
					if(commodityDesc[i][0]!=null&&commodityDesc[i][0].length()>0)
					{	
						tableCreation.setTable(8);
						tableCreation1.setTable(9);
						tableCreation2.setTable(15);
						tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
						tableCreation2.insertCell(commodityDesc[i][0]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						if(flag){
							tableCreation1.insertCell("SUBJECT MATTER INSURED", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT,3, 0);
							tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT,6, 0);
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
							document.add(tableCreation.getTable());
							flag=false;
						}else if((i+1)==commodityDesc.length)
						{
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
							tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
							document.add(tableCreation.getTable());
						}else
						{
							tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT |Rectangle.RIGHT,3, 0);
							tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
							tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
							document.add(tableCreation.getTable());
						}
					}
				}
			}
			if(!endt || list.contains("35")){
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("TOTAL ESTIMATED ANNUAL TURNOVER", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell(currency+pdis.decimalFormat(Double.parseDouble(annualTurnover), decimal)+"/-", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());
			}
			if(Voyage!=null && Voyage.length>0 && Voyage[0][0]!=null){
				String voyage=Voyage[0][0];
				/*int j=0;
				for(int i=0;i<voyage.length();i+=64)
				{
					tableCreation.setTable(8);
					tableCreation1.setTable(9);
					if (i == 0) {
						tableCreation1.insertCell("VOYAGE", fontTextNormal,
								Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT
										, 3, 0);
					} else if ((i + 64) < voyage.length()) {
						tableCreation1.insertCell("", fontTextNormal,
								Rectangle.LEFT | Rectangle.RIGHT, 3, 0);
					}else
					{
						tableCreation1.insertCell("", fontTextNormal,
								Rectangle.LEFT | Rectangle.RIGHT|Rectangle.BOTTOM, 3, 0);
					}
					if ((i + 64) < voyage.length()) {
						String temp = voyage.substring(j, (j + 64));
						String orig = temp.substring(0, temp.lastIndexOf(",")+1);
						j = temp.lastIndexOf(",") +j+1;
						i=j-1;
						tableCreation1.insertCell(orig,
								fontTextNormal,  Rectangle.LEFT | Rectangle.RIGHT, 6, 0);
					} else {
						{
							tableCreation1.insertCell(voyage.substring(j, voyage
									.length()), fontTextNormal, Rectangle.LEFT
									| Rectangle.RIGHT
									| Rectangle.BOTTOM, 6, 0);
						}
					}
					tableCreation.insertCell(tableCreation1.getTable(),
							Rectangle.NO_BORDER, 8, 0);
					document.add(tableCreation.getTable());
					
				}*/
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("VOYAGE", fontTextNormal,Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT, 3, 0);
				tableCreation1.insertCell(voyage, fontTextNormal,  Rectangle.LEFT | Rectangle.RIGHT, 6, 0);
				tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER, 8, 0);
				document.add(tableCreation.getTable());
			}
			
			if(conveyance!=null && conveyance.length>0 && (!endt || list.contains("33")))
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation2.setTable(15);
				for(int i=0;i<conveyance.length;i++)
				{
					tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 0);
					tableCreation2.insertCell((conveyance[i][1]+(conveyance[i][4]==null?"":" - "+conveyance[i][4]))+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 0);
				}
				tableCreation1.insertCell("CONVEYANCE", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());
			}
			if(toleranceName!=null && toleranceName.length()>0){
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation2.setTable(15);
				tableCreation1.insertCell("BASIS OF VALUATION", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				if(toleranceName!=null && toleranceName.length()>0 && toleranceName.lastIndexOf(", ")!=-1){
					toleranceName=toleranceName.substring(0, toleranceName.lastIndexOf(", "));
				}
				tableCreation1.insertCell(toleranceName, fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());
			}
			
			if(conveyance!=null && conveyance.length>0 && (!endt || list.contains("36")))
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation2.setTable(15);
				for(int i=0;i<conveyance.length;i++)
				{	String string="";
					if("1".equals(conveyance[i][0])){
						string=" anyone Vessel";
					}else if("2".equals(conveyance[i][0])){
						string=" anyone Aircraft";
					}else if("3".equals(conveyance[i][0])){
						string=" anyone Land Conveyance";
					}else if("5".equals(conveyance[i][0])||"6".equals(conveyance[i][0])){
						string=" anyone Vessel/Aircraft";
					}
					tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
					tableCreation2.insertCell(conveyance[i][1]+" "+conveyance[i][2]+pdis.decimalFormat(Double.parseDouble(conveyance[i][3]), decimal)+"/-"+string+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
				}
				tableCreation1.insertCell("MAXIMUM LIMIT PER SHIPMENT / PER CONVEYANCE", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());
			}
			//tableCreation2.setTable(15);
			logger.info("Page Size()=====>"+document.getPageSize());
			boolean sea=true,air=true,land=true,post=true,seaair=true,all=true,war=true;
			if(classes!=null && classes.length>0)
			{	
				for(int i=0;i<classes.length;i++)
				{
					if("1".equalsIgnoreCase((classes[i][0])) || classes[i][0]==null)
					{	if(sea)
						{
							tableCreation2.setTable(15);
							tableCreation2.insertCell("In respect of Sea shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							sea=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation2.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
						
					}else if("2".equalsIgnoreCase(classes[i][0]))
					{
						if(air)
						{
							tableCreation3.setTable(15);
							tableCreation3.insertCell("In respect of Air shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							air=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation3.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation3.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}else if("3".equalsIgnoreCase(classes[i][0]))
					{
						if(land)
						{
							tableCreation4.setTable(15);
							tableCreation4.insertCell("In respect of Land shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							land=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation4.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation4.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}else if("5".equalsIgnoreCase(classes[i][0]))
					{
						if(post)
						{
							tableCreation5.setTable(15);
							tableCreation5.insertCell("In respect of Post and Parcel shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							post=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation5.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation5.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}else if("6".equalsIgnoreCase(classes[i][0]))
					{
						if(seaair)
						{
							tableCreation6.setTable(15);
							tableCreation6.insertCell("In respect of Sea and Air shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							seaair=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation6.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation6.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}
					else if("0".equalsIgnoreCase(classes[i][0]))
					{
						if(all)
						{	
							tableCreation0.setTable(15);
							tableCreation0.insertCell("In respect of all shipments:", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							all=false;
						}
						if(classes[i][1]!=null&&classes[i][1].length()>0)
						{
							tableCreation0.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation0.insertCell(classes[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}
					/*tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
					tableCreation2.insertCell(classes[i][0]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
					if(i==30)
					{
						tableCreation1.insertCell("CONDITIONS", fontTextNormal, Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT| Rectangle .BOTTOM,3, 0);
						tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
						document.add(tableCreation.getTable());
						//document.newPage();
						tableCreation.setTable(8);
						tableCreation1.setTable(9);
						tableCreation2.setTable(15);
					}*/
				}
			}
			if(warsrccclasses!=null && warsrccclasses.length>0)
			{
				for(int i=0;i<warsrccclasses.length;i++)
				{
					if("W".equalsIgnoreCase(warsrccclasses[i][0]))
					{
						if(war)
						{	
							tableCreation7.setTable(15);
							tableCreation7.insertCell("War & SRCC Clauses: ", fontTextUnderLine, Rectangle.NO_BORDER,15, 0);
							war=false;
						}
						if(warsrccclasses[i][1]!=null&&warsrccclasses[i][1].length()>0)
						{
							tableCreation7.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
							tableCreation7.insertCell(warsrccclasses[i][1]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
						}
					}
				}
			}
			if(!sea)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("CONDITIONS", fontTextNormal, Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation2.getTable(), Rectangle.LEFT  | Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!air)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal,  Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation3.getTable(), Rectangle.LEFT  | Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!land)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation4.getTable(), Rectangle.LEFT  | Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!post)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal,  Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation5.getTable(), Rectangle.LEFT  | Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!seaair)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal,  Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation6.getTable(), Rectangle.LEFT  | Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!all)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT  | Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation0.getTable(),Rectangle.LEFT  |Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if(!war)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT |Rectangle.RIGHT,3, 0);
				tableCreation1.insertCell(tableCreation7.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			if("Y".equalsIgnoreCase(amendedClauseYN)){
				tableCreation.setTable(8);
				tableCreation1.setTable(9);
				tableCreation1.insertCell("AMENDED INSOLVENCY EXCLUSION CLAUSE", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell("It is hereby agreed that the exclusion \"loss damage or expense arising from insolvency or financial default of the owners, managers, charterers or operators of the vessel and/or aircraft\" (incorporated in the Institute Cargo Clauses) is amended to read as follows:\n\n\"In no case shall this insurance cover loss, damage or expense arising from insolvency or financial default of the owners, managers, charterers or operators of the vessel and/or aircraft where the Assured is aware, or in the ordinary course of business should know that said financial default may prevent the normal prosecution of the voyage.\n\nThis exclusion shall not apply where this insurance has been assigned to the party claiming hereunder who has bought or agreed to buy the subject-matter insured in good faith under a binding contract�.", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
				document.add(tableCreation.getTable());
			}
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation2.setTable(15);
			if(exclusion!=null && exclusion.length>0)
			{
				for(int i=0;i<exclusion.length;i++)
				{
					if(exclusion[i][0]!=null && exclusion[i][0].length()>0)
					{
						tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
						tableCreation2.insertCell(exclusion[i][0]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
					}
				}
				tableCreation1.insertCell("EXCLUSIONS", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation2.setTable(15);
			if(warranty!=null && warranty.length>0)
			{
				for(int i=0;i<warranty.length;i++)
				{
					if(warranty[i][0]!=null && warranty[i][0].length()>0)
					{
						tableCreation2.insertCell("    *", fontTextNormal, Rectangle.NO_BORDER,1, 6);
						tableCreation2.insertCell(warranty[i][0]+"\n", fontTextNormal, Rectangle.NO_BORDER,14, 6);
					}
				}
				tableCreation1.insertCell("WARRANTIES", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
				tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
			document.add(tableCreation.getTable());
			if(!"Y".equalsIgnoreCase(rateYN))
			{
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("RATES", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell("As Agreed.", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
			document.add(tableCreation.getTable());
			}
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			document.add(tableCreation.getTable());
			tableCreation.insertCell("Subject to IMPORTANT NOTICE as below", fontTextNormal, Rectangle.NO_BORDER,8, 6);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("This Policy and the Clauses attached hereto are subject to English Law and Practice as per English Marine Insurance Act 1906", fontTextNormal, Rectangle.NO_BORDER,8,6);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("IMPORTANT NOTICE [In case of failure by the Assured or his agent to comply with the following Conditions, the Insurer is discharged from any liability under the policy and/or may avoid the contract]", fontTextNormal, Rectangle.NO_BORDER,8,6);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			if(!endt || list.contains("37")){
				tableCreation.insertCell("CONDITIONS", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("DISCLOSURE", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("It is a condition of this insurance that the Assured has disclosed to the Insurer, before the contract was concluded, every material circumstance which was known to the Assured, and the Assured is deemed to know every circumstance which, in the ordinary course of business, ought to be known by him. If the Assured has failed to make such disclosure, the Insurer may avoid the contract. Every circumstance is material which would influence the judgment of a prudent Insurer in fixing the premium, or determining whether he will take the risk.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("It is also a condition of this insurance that the Assured shall notify the Insurer during validity of the contract, and before the renewal(s) are concluded and during the validity of each renewal, of any changes in the material circumstances which may increase the risk to be borne by the Insurer.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("REPRESENTATION", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("Every material representation made by the Assured or his agent to the Insurer during the negotiations for the contract and/or renewal, before the contract and/or renewal(s) are concluded, and during the currency of the policy and/or renewal(s), must be true. If it be untrue the Insurer may avoid the contract. A representation is material which would influence the judgment of a prudent Insurer in fixing the premium, or determining whether he will take the risk.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("WARRANTIES", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("This policy contains warranties. Warranties are conditions which must be exactly complied with, whether they be material to the risk or not. If they be not so complied with, then, subject to any express provision in this policy, the Insurer is discharged from any liability under the policy as from the date of the breach of warranty, but without prejudice to any liability incurred by him before that date.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("UNSEAWORTHINESS AND UNFITNESS  EXCLUSION CLAUSE", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("In no case shall this insurance cover loss, damage or expense arising from unseaworthiness of vessel or craft, unfitness of vessel, craft, conveyance, container or liftvan for the safe carriage of the subject-matter insured, where the Assured or their servants are privy to such unseaworthiness or unfitness, at the time the subject-matter insured is loaded therein. ", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("The Underwriters waive any breach of the implied warranties of seaworthiness of the ship and fitness of the ship to carry the subject-matter insured to destination, unless the Assured or their servants are privy to such unseaworthiness or unfitness.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("INSTITUTE CLASSIFICATION CLAUSE CL.354  DATED 01/01/2001", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("This policy is subject to Institute Classification Clause CL.354 dated 01/01/2001 containing mainly the following:", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("1- QUALIFYING VESSELS:", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("This insurance and the marine transit rates as agreed in the policy or open cover apply only to cargoes and/or interests carried by mechanically self-propelled vessels of steel construction classed with a Classification Society which is:", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation1.setTable(17);
				tableCreation1.insertCell("    1.1", fontTextNormal, Rectangle.NO_BORDER,1, 6);
				tableCreation1.insertCell("a Member or Associate Member of the International Association of Classification Societies (IACS), or",fontTextNormal, Rectangle.NO_BORDER,16, 6);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,17, 0);
				tableCreation1.insertCell("    1.2", fontTextNormal, Rectangle.NO_BORDER,1, 6);
				tableCreation1.insertCell("a National Flag Society as defined in Clause 4 below, but only where the vessel is engaged exclusively in the costal trading of that nation (including trading on an inter-island route within an archipelago of which that nation forms part). Cargoes and/or interests carried by vessels not classed as above must be notified promptly to underwriters for rates and conditions to be agreed. Should a loss occur prior to such agreement being obtained cover may be provided but only if cover would have been available at a reasonable commercial market rate on reasonable commercial market terms.", fontTextNormal, Rectangle.NO_BORDER,16, 6);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,17, 0);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("2- NATIONAL FLAG SOCIETY:", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("A National Flag Society is a Classification Society which is domiciled in the same country as the owner of the vessel in question which must also operate under the flag of that country.", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("IMPORTANT EXCLUSIONS  [The Insurer is not liable in respect of the following] Notwithstanding anything to the contrary contained herein:", fontTextNormal, Rectangle.NO_BORDER,8, 6);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation1.setTable(17);
				tableCreation1.insertCell("    (a)", fontTextNormal, Rectangle.NO_BORDER,1, 6);
				tableCreation1.insertCell("The Insurer is not liable for any loss attributable to the willful misconduct of the Assured.", fontTextNormal, Rectangle.NO_BORDER,16, 6);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,17, 0);
				tableCreation1.insertCell("    (b)", fontTextNormal, Rectangle.NO_BORDER,1, 6);
				tableCreation1.insertCell("The Insurer is not liable for any loss proximately caused by delay, and consequences thereof, although the delay be caused by a peril insured against.", fontTextNormal, Rectangle.NO_BORDER,16, 6);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,17, 0);
				tableCreation1.insertCell("    (c)", fontTextNormal, Rectangle.NO_BORDER,1, 6);
				tableCreation1.insertCell("Except as specifically mentioned in the schedule or the clauses attached to this policy, the Insurer is not liable for ordinary wear and tear, ordinary leakage and breakage, inherent vice or nature of the subject-matter insured.", fontTextNormal, Rectangle.NO_BORDER,16, 6);
				tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
			}
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			Image signImageRoyal = null;
			Image stampImageRoyal = null;
			try
			{
				signImageRoyal  = Image.getInstance(signImage);
				stampImageRoyal  = Image.getInstance(stampImage);
//				signImageRoyal.scaleToFit(70,54);
				signImageRoyal.scaleToFit(130,130);
				stampImageRoyal.scaleToFit(130,130);
				//stampImageRoyal.scaleToFit(60,54);
				//stampImageRoyal.scaleToFit(130,130);
				
			}
			catch(Exception e)
			{
				logger.debug(e);
			}
			tableCreation.insertCell("", fontTextNormal,Rectangle.NO_BORDER,4, 1);
			tableCreation.insertCell(signImageRoyal, Rectangle.NO_BORDER,4, 1);
			/*tableCreation.insertCell("", fontTextNormal,Rectangle.NO_BORDER,4, 1);
			tableCreation.insertCell(stampImageRoyal, Rectangle.NO_BORDER,4, 1);*/
			//tableCreation.insertCell("   for/Emirates INSURANCE COMPANY", fontTextNormal, Rectangle.NO_BORDER,8, 1);
			//tableCreation.insertCell("\n\n\n\n\n\n\n\n\n\n", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
			document.add(tableCreation.getTable());
			if("Y".equalsIgnoreCase(rateYN) && (!endt || (list.contains("29") || list.contains("28"))))
			{
			document.newPage();
			tableCreation.setTable(8);
			tableCreation.insertCell("List attaching to and forming part of Policy No "+openCoverNo, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
			document.add(tableCreation.getTable());
			if(rates!=null && rates.length>0)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(19);
				tableCreation.insertCell("RATES", fontTextUnderLine, Rectangle.NO_BORDER,8, 0);
				tableCreation.insertCell("", fontTextNormal, Rectangle.NO_BORDER,8, 0);
				tableCreation1.insertCell("SNo", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
				tableCreation1.insertCell("Commodity Desc.", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				tableCreation1.insertCell("Mode of Transport", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 7);
				tableCreation1.insertCell("Coverage", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				//tableCreation1.insertCell("Conveyance", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 7);
				tableCreation1.insertCell("Rate(%)", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 7);
				/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());*/
				for(int i=0;i<rates.length;i++)
				{
					if(i!=0){
					tableCreation.setTable(8);
					tableCreation1.setTable(19);
					}
					tableCreation1.insertCell((i+1)+". ", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 1);
					tableCreation1.insertCell(rates[i][3], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					tableCreation1.insertCell(rates[i][0], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 0);
					tableCreation1.insertCell(rates[i][1], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					//tableCreation1.insertCell(rates[i][2], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 0);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(rates[i][4]), 4), fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 1);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
				}
				/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());
				tableCreation.setTable(8);*/
			}
			
			if(warsrcc!=null && warsrcc.length>0)
			{
				tableCreation.setTable(8);
				tableCreation1.setTable(10);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,10, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,10, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,10, 0);
				tableCreation1.insertCell("WAR & SRCC RATES", fontTextUnderLine, Rectangle.NO_BORDER,10, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,10, 0);
				tableCreation1.insertCell("SNo", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
				tableCreation1.insertCell("Mode of Transport", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				tableCreation1.insertCell("Rate(%)", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 7);
				/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());*/
				for(int i=0;i<warsrcc.length;i++)
				{
					if(i!=0){
						tableCreation.setTable(8);
						tableCreation1.setTable(10);
					}
					tableCreation1.insertCell((i+1)+". ", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 1);
					tableCreation1.insertCell(warsrcc[i][0], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(warsrcc[i][1]), 6), fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 1);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
				}
				//tableCreation1.insertCell(warsrcc.replaceAll("~","-"), fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}
			/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
			document.add(tableCreation.getTable());*/
			} 
			if(deductible!=null && deductible.length>0)
			{
				if(!"Y".equalsIgnoreCase(rateYN))
				{
					document.newPage();
					tableCreation.setTable(8);
					tableCreation.insertCell("List attaching to and forming part of Policy No "+openCoverNo, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
					document.add(tableCreation.getTable());
				}
				tableCreation.setTable(8);
				tableCreation1.setTable(22);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("DEDUCTIBLE", fontTextUnderLine, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("", fontTextNormal, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("Claims, if any, are payable subject to an excess for each and every loss.", fontTextNormal, Rectangle.NO_BORDER,22, 0);
				tableCreation1.insertCell("SNo", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
				tableCreation1.insertCell("Start Range", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				tableCreation1.insertCell("End Range", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				tableCreation1.insertCell("Excess Amount", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				tableCreation1.insertCell("Currency", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
				/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
				document.add(tableCreation.getTable());*/
				for(int i=0;i<deductible.length;i++)
				{
					if(i!=0){
						tableCreation.setTable(8);
						tableCreation1.setTable(22);
					}
					tableCreation1.insertCell((i+1)+". ", fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 1);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(deductible[i][0]), decimal), fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 1);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(deductible[i][1]), decimal), fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 1);
					tableCreation1.insertCell(pdis.decimalFormat(Double.parseDouble(deductible[i][2]), decimal), fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 1);
					tableCreation1.insertCell(deductible[i][3], fontTextNormal,  Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,8, 0);
					document.add(tableCreation.getTable());
				}
				//tableCreation1.insertCell(warsrcc.replaceAll("~","-"), fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			}
			/*tableCreation.insertCell(tableCreation1.getTable(),Rectangle.BOTTOM | Rectangle.TOP,8, 0);
			document.add(tableCreation.getTable());*/
		
			document.close();

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	public void getPolicyInfo(String branchCode, String openCoverNo, String imageUrl)
	{
		String query="";
		String result[][]=null;
		branchCode=branchCode.replaceAll("'","");
		query=" SELECT ocpm.open_cover_no,ocm.type,PI.TITLE||'. '||pi.first_name||chr(10)||'PO BOX '||pi.pobox||chr(10)||pi.emirate||chr(10)||pi.address1||chr(10)||pi.address2 first_name,CASE WHEN ocm.type='Standard' THEN ' From ' || TO_CHAR (ocm.policy_start_date,'dd-mm-yyyy') ELSE  ' From ' || TO_CHAR (ocm.policy_start_date,'dd-mm-yyyy') || ' To ' || TO_CHAR (ocm.policy_end_date,'dd-mm-yyyy') END \"Period Of Insurance\", "+
			" cm.short_name||'. ', CASE WHEN ocm.country_remarks IS NOT NULL THEN ocm.country_remarks ELSE 'From ' END \"Voyage\",  "+
			" ocsm.tolerance_name,NVL(BM.DECIMAL_PLACES,0) DECIMAL_PLACES,BM.HEADER_IMG,BM.FOOTER_IMG,BM.SIGN_IMG,BM.STAMP,ocm.FOREIGN_TURNOVER \"Annual Turnover\",ocpm.RATE_PRINT_STATUS,ocpm.CANCELLATION_CLAUSE,ocpm.AMENDED_CLAUSE_PRINT_STATUS FROM open_cover_position_master ocpm,open_cover_master ocm,personal_info pi,branch_master bm,currency_master cm,  "+
			" open_cover_sale_term_master ocsm WHERE ocpm.open_cover_no=? AND ocpm.proposal_no=ocm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no)  "+
			" AND ocm.customer_id=pi.customer_id AND ocm.proposal_no=ocsm.proposal_no AND ocsm.amend_id=(SELECT MAX(amend_id) FROM open_cover_sale_term_master WHERE proposal_no=ocsm.proposal_no)  "+
			" AND bm.branch_code=ocm.branch_code AND cm.country_id=bm.origination_country_id AND cm.currency_id=ocm.currency_id AND cm.amend_id=(SELECT MAX(amend_id) FROM currency_master WHERE currency_id=cm.currency_id  "+ 
			" AND country_id=cm.country_id AND trunc(effective_date)<=trunc(SYSDATE)) AND trunc(cm.effective_date)<=trunc(SYSDATE)  AND ocpm.status='P' ";
		result=runner.multipleSelection(query, new String[]{openCoverNo});
		if(result!=null && result.length>0)
		{
			for(int i=0;i<result.length;i++)
			{
				setOpenCoverNo(result[i][0]);
				setType(result[i][1]);
				setName(result[i][2]);
				setPeriodOfInsurance(result[i][3]);
				setCurrency(result[i][4]);
				this.decimal=Integer.parseInt(result[i][7]);
				this.headImage=imageUrl+"/"+result[i][8];
				this.footImage=imageUrl+"/"+result[i][9];
				this.signImage=imageUrl+"/"+result[i][10];
				this.stampImage=imageUrl+"/"+result[i][11];
				setAnnualTurnover(result[i][12]);
				this.rateYN=result[i][13];
				this.cancelClass=result[i][14];
				this.amendedClauseYN=result[i][15];
			}
		}
/*		query=" SELECT CASE WHEN OCSM.TOLERANCE_NAME IS NOT NULL THEN OCSM.TOLERANCE_NAME ELSE TD.SALE_TERM_NAME END BASISOFVALUATION FROM "+ 
			" (SELECT PROPOSAL_NO,REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,SALE_TERM_NAME || ',')).EXTRACT('//text()'),','),'amp;','') SALE_TERM_NAME "+
			" FROM ( SELECT SD.PROPOSAL_NO,SM.SALE_TERM_NAME FROM (SELECT  PROPOSAL_NO,REGEXP_SUBSTR (SALE_TERM_ID, '[^,]+', 1, LEVEL) AS SALE_TERM_ID "+
			" FROM ((SELECT OCSM.PROPOSAL_NO,OCSM.SALE_TERM_ID FROM OPEN_COVER_SALE_TERM_MASTER OCSM,open_cover_position_master OCPM "+
			" WHERE ocpm.open_cover_no=? AND OCSM.PROPOSAL_NO=OCPM.PROPOSAL_NO AND OCSM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_SALE_term_master where PROPOSAL_NO=OCSM.PROPOSAL_NO))) "+
			" CONNECT BY LEVEL <= LENGTH (SALE_TERM_ID) - LENGTH (REPLACE (SALE_TERM_ID, ',', ''))+ 1) SD,SALE_TERM_MASTER SM WHERE SM.BRANCH_CODE=? "+
			" AND SM.SALE_TERM_ID=SD.SALE_TERM_ID) GROUP BY PROPOSAL_NO) TD,open_cover_sale_term_master OCSM WHERE TD.PROPOSAL_NO=OCSM.PROPOSAL_NO "+
			" AND OCSM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_SALE_TERM_MASTER WHERE PROPOSAL_NO=OCSM.PROPOSAL_NO) ";*/
		query="SELECT CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('basisofvaluation',?,?) FROM DUAL)";
		setToleranceName(runner.singleSelection(query, new String[]{openCoverNo,branchCode}));
		query=" SELECT commodity_name_desc FROM open_cover_commodity_master occm,open_cover_position_master ocpm "+ 
			" WHERE ocpm.open_cover_no=?AND occm.proposal_no=ocpm.proposal_no "+
			" AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_commodity_master WHERE proposal_no=occm.proposal_no) "+
			" AND ocpm.status='P'ORDER BY occm.commodity_name_desc ";
		setCommodityDesc(runner.multipleSelection(query, new String[]{openCoverNo}));
		query=" SELECT MOT.MODE_TRANSPORT_ID, mot.transport_description, cm.short_name||'. ' , ocd.sum_insured_limit,REPLACE(REPLACE(ocd.CONVEYANCE,CHR(10),' '),CHR(13),' ') FROM mode_of_transport mot, "+
			" open_cover_detail ocd,open_cover_master ocm,open_cover_position_master ocpm,branch_master bm,currency_master cm "+
			" WHERE ocpm.open_cover_no=? AND ocpm.proposal_no=ocm.proposal_no AND ocd.mode_transport_id= "+
			" mot.mode_transport_id AND mot.branch_code=ocm.branch_code AND ocm.proposal_no=ocd.proposal_no "+
			" AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) "+
			" AND ocd.amend_id=(SELECT MAX(amend_id) FROM open_cover_detail WHERE proposal_no=ocd.proposal_no) "+
			" AND bm.branch_code=ocm.branch_code AND cm.country_id=bm.origination_country_id AND cm.currency_id=ocd.currency_id "+
			" AND cm.amend_id=(SELECT MAX(amend_id) FROM currency_master WHERE currency_id=cm.currency_id AND "+
			" country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')) "+
			" AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND ocpm.status='P' "+
			" ORDER BY mot.mode_transport_id ";
		setConveyance(runner.multipleSelection(query, new String[]{openCoverNo}));
	/*	 query=" SELECT CASE WHEN cnt=1 AND extracover=0 THEN (SELECT CASE WHEN cover_id IN (1,2,3) THEN "+
		 	" 1 WHEN cover_id IN (8) THEN 2 WHEN cover_id IN (9,10) THEN 3 WHEN cover_id IN (11) THEN "+
		 	" 5 WHEN cover_id IN (12) THEN 6 ELSE 0 END FROM open_cover_clauses WHERE proposal_no= "+
		 	" rt.proposal_no and clauses_description=rt.clauses_description AND amend_id= "+
		 	" (SELECT max(amend_id) FROM open_cover_clauses WHERE proposal_no=rt.proposal_no "+ 
		 	" and clauses_description=rt.clauses_description)) WHEN extracover=99 THEN 99 ELSE "+
		 	" 0 END mode_transport_id,rt.clauses_description FROM (SELECT count(*) cnt, "+
		 	" oc.clauses_description,occd.proposal_no,case when occd.extra_cover_id=0 then "+
		 	" 0 else 99 end extracover  FROM open_cover_clauses oc,(SELECT occ.proposal_no, "+
		 	" occ.cover_id,occ.extra_cover_id,max(occ.amend_id) amend_id FROM open_cover_clauses occ, "+
		 	" open_cover_position_master ocpm WHERE ocpm.open_cover_no=? AND occ.proposal_no= "+
		 	" ocpm.proposal_no AND ocpm.status='P'GROUP BY occ.proposal_no,occ.cover_id,occ.extra_cover_id) occd "+
		 	" WHERE oc.proposal_no=occd.proposal_no AND oc.cover_id=occd.cover_id AND oc.amend_id=occd.amend_id "+
		 	" Group BY oc.clauses_description,occd.proposal_no,case when occd.extra_cover_id=0 then 0 else 99 end) rt "+ 
		 	" ORDER BY mode_transport_id ";*/
		
		/*query="SELECT CASE WHEN cnt=1 THEN (CASE WHEN cover_id IN (1,2,3) THEN 1 WHEN cover_id IN (8) THEN 2 " +
				" WHEN cover_id IN (9,10) THEN 3 WHEN cover_id IN (11) THEN 5 WHEN cover_id IN (12) THEN 6 ELSE 0 END) "+ 
				" ELSE 0 END mode_transport_id,rt.clauses_description FROM (SELECT count(*) cnt,oc.clauses_description, "+
				" occd.proposal_no FROM open_cover_clauses oc, (SELECT occ.proposal_no,occ.cover_id, "+
				" max(occ.amend_id) amend_id FROM open_cover_clauses occ,open_cover_position_master ocpm "+
				" WHERE ocpm.open_cover_no=? AND occ.proposal_no=ocpm.proposal_no AND ocpm.status='P' "+
				" AND EXTRA_COVER_ID=0 GROUP BY occ.proposal_no,occ.cover_id) occd WHERE oc.proposal_no=occd.proposal_no "+
				" AND oc.cover_id=occd.cover_id AND oc.amend_id=occd.amend_id AND OC.EXTRA_COVER_ID=0 "+
				" Group BY oc.clauses_description,occd.proposal_no) rt,open_cover_clauses oc WHERE "+
				" oc.proposal_no=rt.proposal_no and oc.clauses_description=rt.clauses_description " +
				" AND oc.amend_id=(SELECT max(amend_id) FROM open_cover_clauses WHERE proposal_no=oc.proposal_no "+
				" and clauses_description=oc.clauses_description) "+
				" ORDER BY OC.MODE_TRANSPORT_ID,OC.CLAUSES_ID ";*/
		
		/*query=" SELECT CASE WHEN cnt=1 THEN (CASE WHEN cover_id IN (1,2,3) THEN 1 WHEN cover_id IN (8) THEN 2 "+
			" WHEN cover_id IN (9,10) THEN 3 WHEN cover_id IN (11) THEN 5 WHEN cover_id IN (12) THEN 6 ELSE 0 END) "+
			" ELSE 0 END mode_transport_id,rt.clauses_description FROM (SELECT count(*) cnt,oc.clauses_description, "+
			" occd.proposal_no FROM open_cover_clauses oc,(SELECT occ.proposal_no,occ.cover_id,max(occ.amend_id) "+
			" amend_id FROM open_cover_clauses occ,open_cover_position_master ocpm,open_cover_sub_detail ocsd "+
			" WHERE ocpm.open_cover_no=? AND occ.proposal_no=ocpm.proposal_no AND ocpm.status='P' "+
			" AND ocsd.proposal_no=ocpm.proposal_no AND ocsd.cover_id=occ.cover_id AND ocsd.amend_id= "+ 
			" (select max(amend_id) from open_cover_sub_detail where proposal_no=ocsd.proposal_no) "+
			" AND EXTRA_COVER_ID=0 GROUP BY occ.proposal_no,occ.cover_id) occd WHERE oc.proposal_no=occd.proposal_no "+
			" AND oc.cover_id=occd.cover_id AND oc.amend_id=occd.amend_id AND OC.EXTRA_COVER_ID=0 "+
			" Group BY oc.clauses_description,occd.proposal_no) rt,open_cover_clauses oc WHERE "+
			" oc.proposal_no=rt.proposal_no and oc.clauses_description=rt.clauses_description "+
			" AND oc.amend_id=(SELECT max(amend_id) FROM open_cover_clauses WHERE proposal_no=oc.proposal_no "+ 
			" and clauses_description=oc.clauses_description) ORDER BY OC.MODE_TRANSPORT_ID,OC.CLAUSES_ID ";*/
		
		query="SELECT CODE, CODEDESC FROM TABLE (SELECT OPENCOVERSCHEDULE(?,?,?) FROM DUAL)";
			
		setClasses(runner.multipleSelection(query, new String[]{"clauses",openCoverNo,branchCode}));
		query=" SELECT TRIM(oce.exclusion_description) FROM open_cover_exclusions oce,open_cover_position_master ocpm "+
			  " WHERE ocpm.open_cover_no=? AND ocpm.proposal_no=oce.proposal_no AND ocpm.status='P' "+
			  "	AND oce.amend_id=(SELECT MAX(amend_id) FROM open_cover_exclusions WHERE proposal_no=oce.proposal_no) ";
		setExclusion(runner.multipleSelection(query, new String[]{openCoverNo}));
		query=" SELECT TRIM(ocw.warranty_description) FROM open_cover_warranties ocw,open_cover_position_master ocpm "+
			" WHERE ocpm.open_cover_no=? AND ocpm.proposal_no=ocw.proposal_no AND ocpm.status='P' "+
			" AND ocw.amend_id=(SELECT MAX(amend_id) FROM open_cover_warranties WHERE proposal_no=ocw.proposal_no) ";
		setWarranty(runner.multipleSelection(query, new String[]{openCoverNo}));
		/*query=" SELECT CASE WHEN to_clob(ocm.country_remarks) IS NOT NULL THEN to_clob(ocm.country_remarks) "+
			" ELSE to_clob('From '||(SELECT to_clob(REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,orgcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&')) "+
			" orgcountry_name FROM (SELECT  REGEXP_SUBSTR (cm.country_name, '[^,]+', 1, LEVEL) AS orgcountry_name FROM country_master cm, "+
			" (SELECT  REGEXP_SUBSTR (open_cover_country_id_org, '[^,]+', 1, LEVEL) AS country_id_org FROM (SELECT occm.open_cover_country_id_org,ocm.country_remarks FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm "+
			" WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) "+
			" AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) "+
			" CONNECT BY LEVEL <= LENGTH (open_cover_country_id_org)- LENGTH (REPLACE (open_cover_country_id_org, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_org "+
			" AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')) "+
			" CONNECT BY LEVEL <= LENGTH (cm.country_name) - LENGTH (REPLACE (cm.country_name, ',', '')) + 1 ORDER BY cm.country_name)) "+
			" ||' To ' || (SELECT to_clob(REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,destcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&')) destcountry_name FROM (  "+
			" SELECT  cm.country_name destcountry_name FROM country_master cm,(SELECT  REGEXP_SUBSTR (open_cover_country_id_dest, '[^,]+', 1, LEVEL) AS country_id_dest "+
			" FROM (SELECT occm.open_cover_country_id_dest FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm "+
			" WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) "+
			" AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) "+
			" CONNECT BY LEVEL <= LENGTH (open_cover_country_id_dest)- LENGTH (REPLACE (open_cover_country_id_dest, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_dest "+
			" AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')) "+
			" ORDER BY cm.country_name))) "+
			" END Voyage FROM open_cover_position_master ocpm,open_cover_master ocm WHERE  ocpm.open_cover_no=? AND ocpm.proposal_no=ocm.proposal_no "+
			" AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) ";*/
		
		//query=" SELECT CASE WHEN to_clob(ocm.country_remarks) IS NOT NULL THEN to_clob(ocm.country_remarks) ELSE to_clob('From '||(SELECT to_clob(REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,orgcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&')) orgcountry_name FROM (SELECT  cm.country_name orgcountry_name FROM country_master cm, (SELECT  REGEXP_SUBSTR (open_cover_country_id_org, '[^,]+', 1, LEVEL) AS country_id_org FROM (SELECT occm.open_cover_country_id_org,ocm.country_remarks FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) CONNECT BY LEVEL <= LENGTH (open_cover_country_id_org)- LENGTH (REPLACE (open_cover_country_id_org, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_org AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')))) ||' To ' || (SELECT to_clob(REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,destcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&')) destcountry_name FROM ( SELECT  cm.country_name destcountry_name FROM country_master cm, (SELECT  REGEXP_SUBSTR (open_cover_country_id_dest, '[^,]+', 1, LEVEL) AS country_id_dest FROM (SELECT occm.open_cover_country_id_dest FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) CONNECT BY LEVEL <= LENGTH (open_cover_country_id_dest)- LENGTH (REPLACE (open_cover_country_id_dest, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_dest AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')) ))) END Voyage FROM open_cover_position_master ocpm,open_cover_master ocm WHERE  ocpm.open_cover_no=? AND ocpm.proposal_no=ocm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) ";
		query=" SELECT CASE WHEN ocm.country_remarks IS NOT NULL THEN SUBSTR(ocm.country_remarks,1,1) ELSE 'From '||(SELECT REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,orgcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&') orgcountry_name FROM (SELECT  cm.country_name orgcountry_name FROM country_master cm,(SELECT  REGEXP_SUBSTR (open_cover_country_id_org, '[^,]+', 1, LEVEL) AS country_id_org FROM (SELECT occm.open_cover_country_id_org,ocm.country_remarks FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) CONNECT BY LEVEL <= LENGTH (open_cover_country_id_org)- LENGTH (REPLACE (open_cover_country_id_org, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_org AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')))) END CR1 ,CASE WHEN ocm.country_remarks IS NOT NULL THEN SUBSTR(replace(replace(ocm.country_remarks,chr(10),null),chr(13),null),2,LENGTH(ocm.country_remarks)) ELSE chr(10)||'To ' || (SELECT REPLACE(RTRIM(XMLAGG(XMLELEMENT(E,destcountry_name || ',')).EXTRACT('//text()'),','),'&amp;','&') destcountry_name FROM (SELECT  cm.country_name destcountry_name FROM country_master cm,(SELECT  REGEXP_SUBSTR (open_cover_country_id_dest, '[^,]+', 1, LEVEL) AS country_id_dest FROM (SELECT occm.open_cover_country_id_dest FROM open_cover_country_master occm,open_cover_position_master ocpm,open_cover_master ocm WHERE ocpm.open_cover_no=? AND ocm.proposal_no=ocpm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) AND occm.proposal_no=ocpm.proposal_no AND occm.amend_id=(SELECT MAX(amend_id) FROM open_cover_country_master WHERE proposal_no=occm.proposal_no)) CONNECT BY LEVEL <= LENGTH (open_cover_country_id_dest)- LENGTH (REPLACE (open_cover_country_id_dest, ',', ''))+ 1) cd WHERE cm.country_id=cd.country_id_dest AND TO_DATE(cm.effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY') AND cm.amend_id=(SELECT MAX(amend_id) FROM country_master WHERE country_id=cm.country_id AND TO_DATE(effective_date,'dd-mm-YY')<=TO_DATE(SYSDATE,'dd-mm-YY')))) END CR2 FROM open_cover_position_master ocpm,open_cover_master ocm WHERE  ocpm.open_cover_no=? AND ocpm.proposal_no=ocm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no) ";
		setVoyage(runner.multipleSelection(query, new String[]{openCoverNo,openCoverNo,openCoverNo}));
		/*query=" SELECT SUBSTR(WAR,1,LENGTH(WAR)-1) WAR FROM (SELECT  "+
			  " (CASE WHEN W1 IS NOT NULL THEN DECODE(W1,1,'Sea Freight',2,'Air Freight',3,'Land Transit',5,'Post & Parcel',6,'Sea and Air Freight')||'~'||W2||',' ELSE NULL END)|| "+
			  " (CASE WHEN W3 IS NOT NULL THEN DECODE(W3,1,'Sea Freight',2,'Air Freight',3,'Land Transit',5,'Post & Parcel',6,'Sea and Air Freight')||'~'||W4||',' ELSE NULL END)|| "+
			  " (CASE WHEN W5 IS NOT NULL THEN DECODE(W5,1,'Sea Freight',2,'Air Freight',3,'Land Transit',5,'Post & Parcel',6,'Sea and Air Freight')||'~'||W6||',' ELSE NULL END)|| "+
			  " (CASE WHEN W7 IS NOT NULL THEN DECODE(W7,1,'Sea Freight',2,'Air Freight',3,'Land Transit',5,'Post & Parcel',6,'Sea and Air Freight')||'~'||W8||',' ELSE NULL END)|| "+
			  " (CASE WHEN W9 IS NOT NULL THEN DECODE(W9,1,'Sea Freight',2,'Air Freight',3,'Land Transit',5,'Post & Parcel',6,'Sea and Air Freight')||'~'||W10||'.' ELSE NULL END) WAR "+
			  " FROM (SELECT REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,1) W1,REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,2) W2, "+
			  " REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,3) W3,REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,4) W4, "+
			  " REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,5) W5,REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,6) W6, "+
			  " REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,7) W7,REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,8) W8, "+
			  " REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,9) W9,REGEXP_SUBSTR(OCCM.WAR_RATE,'[^#~]+',1,10) W10 FROM OPEN_COVER_COUNTRY_MASTER OCCM,OPEN_COVER_POSITION_MASTER OCPM "+
			  " WHERE OCPM.OPEN_COVER_NO=? AND OCPM.PROPOSAL_NO=OCCM.PROPOSAL_NO AND OCCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_COUNTRY_MASTER WHERE PROPOSAL_NO=OCCM.PROPOSAL_NO))) ";*/
		
		query=" SELECT MOT.TRANSPORT_DESCRIPTION,REGEXP_SUBSTR (war_rate, '[^~]+', 1, 2) WAR_RATE FROM MODE_OF_TRANSPORT MOT, "+
				" (SELECT  REGEXP_SUBSTR (war_rate, '[^#]+', 1, LEVEL) AS war_rate FROM (select occm.war_rate FROM  "+
				" OPEN_COVER_COUNTRY_MASTER OCCM, OPEN_COVER_POSITION_MASTER OCPM WHERE   OCPM.OPEN_COVER_NO = ? "+
				" AND OCPM.PROPOSAL_NO = OCCM.PROPOSAL_NO AND OCCM.AMEND_ID = (SELECT   MAX (AMEND_ID) "+
				" FROM   OPEN_COVER_COUNTRY_MASTER WHERE   PROPOSAL_NO = OCCM.PROPOSAL_NO)) CONNECT BY LEVEL <= "+
				" LENGTH (war_rate) - LENGTH (REPLACE (war_rate, '#', ''))+ 1) WR WHERE MOT.BRANCH_CODE=? "+
				" AND MOT.MODE_TRANSPORT_ID=REGEXP_SUBSTR (war_rate, '[^~]+', 1, 1)  ";
		setWarsrcc(runner.multipleSelection(query, new String[]{openCoverNo,branchCode}));
		query="  SELECT   MT.TRANSPORT_DESCRIPTION, CM.COVER_NAME, CASE WHEN OCCD.COVER_TYPE_ID=0 THEN "+
				" NULL ELSE (SELECT CONVDESC FROM CONVEYAN_MASTER WHERE BRANCH_CODE=CM.BRANCH_CODE AND "+
				" MODE_TRANSPORT_ID=MT.MODE_TRANSPORT_ID AND SNO=OCCD.COVER_TYPE_ID) END CONVDESC, "+
		  		" OCCM.COMMODITY_NAME_DESC,OCCD.commodity_base_rate FROM   OPEN_COVER_COMMODITY_DETAIL OCCD, "+
		  		" OPEN_COVER_POSITION_MASTER OCPM,OPEN_COVER_COMMODITY_MASTER OCCM,OPEN_COVER_MASTER OCM, "+
		  		" COVER_MASTER CM,MODE_OF_TRANSPORT MT WHERE  OCPM.OPEN_COVER_NO=? AND OCPM.PROPOSAL_NO "+
		  		" =OCCD.PROPOSAL_NO AND OCCD.AMEND_ID = (SELECT MAX (AMEND_ID) FROM OPEN_COVER_COMMODITY_DETAIL "+
				" WHERE PROPOSAL_NO=OCCD.PROPOSAL_NO) AND OCPM.PROPOSAL_NO=OCM.PROPOSAL_NO "+
		   		" AND OCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=OCM.PROPOSAL_NO) "+
		   		" AND OCCM.PROPOSAL_NO=OCCD.PROPOSAL_NO AND OCCM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM "+
				" OPEN_COVER_COMMODITY_MASTER WHERE PROPOSAL_NO=OCCM.PROPOSAL_NO) AND OCCD.COMMODITY_ID=OCCM.COMMODITY_ID "+
		   		" AND CM.BRANCH_CODE=OCM.BRANCH_CODE AND OCCD.COVER_ID=CM.COVER_ID AND CM.BRANCH_CODE=MT.BRANCH_CODE "+
		   		" AND CM.MODE_TRANSPORT_ID=MT.MODE_TRANSPORT_ID ORDER BY  MT.MODE_TRANSPORT_ID,OCCD.COVER_ID,OCCD.COMMODITY_ID ";
		setRates(runner.multipleSelection(query, new String[]{openCoverNo}));
		query=" SELECT OCDM.START_RANGE,OCDM.END_RANGE,OCDM.EXCESS_AMOUNT,OCDM.CURRENCY_NAME FROM OPEN_COVER_DEDUCTIBLE_MASTER OCDM, "+
				" OPEN_COVER_POSITION_MASTER ocpm WHERE ocpm.open_cover_no=? AND OCDM.OPEN_COVER_PROPOSAL_NO=ocpm.proposal_no "+
				" AND ocpm.status='P' AND" +
				"  OCDM.STATUS = 'Y' AND TO_DATE(OCDM.effective_date,'dd-mm-YY') <= TO_DATE(SYSDATE,'dd-mm-YY') AND  OCDM.AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_DEDUCTIBLE_MASTER " +
				" WHERE TO_DATE(effective_date,'dd-mm-YY') <= TO_DATE(SYSDATE,'dd-mm-YY') AND OPEN_COVER_PROPOSAL_NO=OCDM.OPEN_COVER_PROPOSAL_NO) ORDER BY OCDM.SNO";
		setDeductible(runner.multipleSelection(query, new String[]{openCoverNo}));
		query="SELECT DISTINCT 'W' code,oc.clauses_description FROM open_cover_clauses oc, "+
			" (SELECT occ.proposal_no,occ.cover_id,max(occ.amend_id) amend_id FROM open_cover_clauses occ, "+
			" open_cover_position_master ocpm,open_cover_sub_detail ocsd WHERE ocpm.open_cover_no=? "+
			" AND ocsd.proposal_no=ocpm.proposal_no AND ocsd.cover_id=occ.cover_id "+
			" AND ocsd.amend_id= (select max(amend_id) from open_cover_sub_detail where proposal_no=ocsd.proposal_no) "+
			" AND occ.proposal_no=ocpm.proposal_no AND ocpm.status='P' AND EXTRA_COVER_ID!=0 GROUP BY "+
			" occ.proposal_no,occ.cover_id) occd WHERE oc.proposal_no=occd.proposal_no AND oc.cover_id=occd.cover_id "+
			" AND oc.amend_id=occd.amend_id AND oc.EXTRA_COVER_ID!=0 ORDER BY oc.clauses_description ";
		warsrccclasses=runner.multipleSelection(query, new String[]{openCoverNo});
		
		String insured=new DataSelectCustomer().getOpenCoverAdditionalInsuredInfo(openCoverNo);
		query = "SELECT REPLACE((PI.TITLE||'. '||PI.FIRST_NAME||', '||'PO BOX '||PI.POBOX||', '||PI.EMIRATE||', '||PI.ADDRESS1||', '||PI.ADDRESS2),', ,',NULL) FROM PERSONAL_INFO PI WHERE PI.CUSTOMER_ID IN ("+insured+") ORDER BY INSTR('"+insured+"', PI.CUSTOMER_ID)";
		String[][] insuredList = runner.multipleSelection(query);
		if(insuredList!=null && insuredList.length>1){
			for (int i = 0; i < insuredList.length; i++) {
				if(i<insuredList.length-1){
					additionalInsured+=insuredList[i][0]+" and/or\n";
				}else{
					additionalInsured+=insuredList[i][0];
				}
			}
		}
		
	}
	public void getPolicyInfoByProposalNo(String branchCode, String openCoverNo, String imageUrl, String endtstatus)
	{
		String query="", endtType="";
		String result[][]=null;
		branchCode=branchCode.replaceAll("'","");
		String args[]=new String[]{openCoverNo,branchCode};
		query=" SELECT ocpm.open_cover_no,ocm.type,PI.TITLE||'. '||pi.first_name||chr(10)||'PO BOX '||pi.pobox||chr(10)||pi.emirate||chr(10)||pi.address1||chr(10)||pi.address2 first_name,CASE WHEN ocm.type='Standard' THEN ' From ' || TO_CHAR (ocm.policy_start_date,'dd-mm-yyyy') ELSE  ' From ' || TO_CHAR (ocm.policy_start_date,'dd-mm-yyyy') || ' To ' || TO_CHAR (ocm.policy_end_date,'dd-mm-yyyy') END \"Period Of Insurance\", "+
		" cm.short_name||'. ', CASE WHEN ocm.country_remarks IS NOT NULL THEN ocm.country_remarks ELSE 'From ' END \"Voyage\",  "+
		" ocsm.tolerance_name,NVL(BM.DECIMAL_PLACES,0) DECIMAL_PLACES,BM.HEADER_IMG,BM.FOOTER_IMG,BM.SIGN_IMG,BM.STAMP,ocm.FOREIGN_TURNOVER \"Annual Turnover\",ocpm.RATE_PRINT_STATUS,ocpm.CANCELLATION_CLAUSE,ocpm.AMENDED_CLAUSE_PRINT_STATUS,ocpm.ENDT_TYPE,to_char(ocpm.EFFECTIVE_DATE,'dd/mm/yyyy'),ocpm.amend_id,bm.BRANCH_NAME,PI.TITLE||'. '||PI.FIRST_NAME CUST_NAME FROM open_cover_position_master ocpm,open_cover_master ocm,personal_info pi,branch_master bm,currency_master cm,  "+
		" open_cover_sale_term_master ocsm WHERE ocpm.proposal_no=? AND ocpm.proposal_no=ocm.proposal_no AND ocm.amend_id=(SELECT MAX(amend_id) FROM open_cover_master WHERE proposal_no=ocm.proposal_no)  "+
		" AND ocm.customer_id=pi.customer_id AND ocm.proposal_no=ocsm.proposal_no AND ocsm.amend_id=(SELECT MAX(amend_id) FROM open_cover_sale_term_master WHERE proposal_no=ocsm.proposal_no)  "+
		" AND bm.branch_code=ocm.branch_code AND cm.country_id=bm.origination_country_id AND cm.currency_id=ocm.currency_id AND cm.amend_id=(SELECT MAX(amend_id) FROM currency_master WHERE currency_id=cm.currency_id  "+ 
		" AND country_id=cm.country_id AND trunc(effective_date)<=trunc(SYSDATE)) AND trunc(cm.effective_date)<=trunc(SYSDATE)   ";
		result=runner.multipleSelection(query, new String[]{openCoverNo});
		if(result!=null && result.length>0)
		{
			for(int i=0;i<result.length;i++)
			{
				setOpenCoverNo(result[i][0]);
				setType(result[i][1]);
				setName(result[i][2]);
				setPeriodOfInsurance(result[i][3]);
				setCurrency(result[i][4]);
				this.decimal=Integer.parseInt(result[i][7]);
				this.headImage=imageUrl+"/"+result[i][8];
				this.footImage=imageUrl+"/"+result[i][9];
				this.signImage=imageUrl+"/"+result[i][10];
				this.stampImage=imageUrl+"/"+result[i][11];
				setAnnualTurnover(result[i][12]);
				this.rateYN=result[i][13];
				this.cancelClass=result[i][14];
				this.amendedClauseYN=result[i][15];
				endtType=result[i][16];
				this.policyDate=result[i][17];
				this.endtNo=result[i][18];
				this.branchName=result[i][19];
				this.custName=result[i][20];
			}
		}
		if("true".equalsIgnoreCase(endtstatus))
		{
		this.endt=StringUtils.isNotEmpty(endtType);
		}
		if(StringUtils.isNotEmpty(endtType)){
			list=(List<String>)CollectionUtils.arrayToList(endtType.split(","));
		}
		if(!endt || list.contains("24")){
			query="SELECT CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('basisofvaluation',?,?) FROM DUAL)";
			setToleranceName(runner.singleSelection(query, args));
		}if(!endt || list.contains("32")){
		query="SELECT CODE FROM TABLE(SELECT OPENCOVERSCHEDULE('commodity',?,?) FROM DUAL)";
		setCommodityDesc(runner.multipleSelection(query,args));
		}if(!endt || list.contains("33") || list.contains("36")){
		query="SELECT CODE, CODEDESC, CODEVALUE, CODETEXT1, CODETEXT2 FROM TABLE(SELECT OPENCOVERSCHEDULE('modeoftransport',?,?) FROM DUAL)";
		setConveyance(runner.multipleSelection(query, args));
		}if(!endt || list.contains("23")){
		query="SELECT CODE, CODEDESC FROM TABLE (SELECT OPENCOVERSCHEDULE('clauses',?,?) FROM DUAL)";
		setClasses(runner.multipleSelection(query, args));
		}if(!endt || list.contains("25")){
		query="SELECT CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('exclusion',?,?) FROM DUAL)";
		setExclusion(runner.multipleSelection(query, args));
		}if(!endt || list.contains("26")){
		query="SELECT CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('warranty',?,?) FROM DUAL)";
		setWarranty(runner.multipleSelection(query, args));
		}if(!endt || list.contains("27")){
		query="SELECT CODE, CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('country',?,?) FROM DUAL)";
		setVoyage(runner.multipleSelection(query, args));
		}if(!endt || list.contains("28")){
		query="SELECT CODE, CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('warrate',?,?) FROM DUAL)";
		setWarsrcc(runner.multipleSelection(query, args));
		}if(!endt || list.contains("29")){
		query="SELECT CODE, CODEDESC, CODEVALUE, CODETEXT1, CODETEXT2 FROM TABLE(SELECT OPENCOVERSCHEDULE('commodityrate',?,?) FROM DUAL)";
		setRates(runner.multipleSelection(query, args));
		}if(!endt || list.contains("34")){
		query="SELECT CODE, CODEDESC, CODEVALUE, CODETEXT1 FROM TABLE(SELECT OPENCOVERSCHEDULE('deductible',?,?) FROM DUAL)";
		setDeductible(runner.multipleSelection(query, args));
		}if(!endt || list.contains("30")){
		query="SELECT CODE, CODEDESC FROM TABLE(SELECT OPENCOVERSCHEDULE('war',?,?) FROM DUAL)";
		warsrccclasses=runner.multipleSelection(query, args);
		}if(!endt || list.contains("31")){
			query="SELECT CODE FROM TABLE(SELECT OPENCOVERSCHEDULE('customer',?,?) FROM DUAL)";
			String[][] insuredList = runner.multipleSelection(query, args);
			if(insuredList!=null && insuredList.length>1){
				for (int i = 0; i < insuredList.length; i++) {
					if(i<insuredList.length-1){
						additionalInsured+=insuredList[i][0]+" and/or\n";
					}else{
						additionalInsured+=insuredList[i][0];
					}
				}
			}
		}
	}
	public String getOpenCoverAdditionalInsuredInfo(String proposalNo)
	{
		String sql= ""; 
		String result="";
		try{
			sql = "SELECT CASE WHEN A.ADDITIONAL_INSURED IS NULL THEN TO_CHAR(A.CUSTOMER_ID) ELSE TO_CHAR(A.CUSTOMER_ID)||','||A.ADDITIONAL_INSURED END FROM OPEN_COVER_MASTER A WHERE A.PROPOSAL_NO=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM OPEN_COVER_MASTER WHERE PROPOSAL_NO=A.PROPOSAL_NO)";
			result = runner.singleSelection(sql,new String[]{proposalNo});
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
}
