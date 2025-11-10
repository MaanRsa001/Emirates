package rsa.pdf;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.maan.common.LogUtil;
import com.maan.services.policyInfo;
import com.maan.services.util.runner;

public class QutationSchedule {
	private static final Logger logger = LogUtil.getLogger(QutationSchedule.class);
	private String headImage;
	private String footImage;
	private String signImage;
	private String stampImage;
	private String fontPath;
	private String productId;
	private String cols;
	private String filePath;
	private String user;
	private String loginBranch;
	private String openCoverNo;
	private String bulletImg;
	private String belongingBranch;
	
	public String getBulletImg() {
		return bulletImg;
	}

	public void setBulletImg(String bulletImg) {
		this.bulletImg = bulletImg;
	}
	
	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getFootImage() {
		return footImage;
	}

	public void setFootImage(String footImage) {
		this.footImage = footImage;
	}

	public String getSignImage() {
		return signImage;
	}

	public void setSignImage(String signImage) {
		this.signImage = signImage;
	}

	public String getStampImage() {
		return stampImage;
	}

	public void setStampImage(String stampImage) {
		this.stampImage = stampImage;
	}

	public String getFontPath() {
		return fontPath;
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOpenCoverNo() {
		return openCoverNo;
	}

	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLoginBranch() {
		return loginBranch;
	}

	public void setLoginBranch(String loginBranch) {
		this.loginBranch = loginBranch;
	}
	private Map<String, Object> brokerDetails;


	public Map<String, Object> getBrokerDetails() {
		return brokerDetails;
	}

	public void setBrokerDetails(Map<String, Object> brokerDetails) {
		this.brokerDetails = brokerDetails;
	}
	
	public void writeQuoteSchedule(String quoteNo)
	{
		logger.info("PDFCreatorBean writeQuoteSchedule()-Enter");
		try
		{
			rsa.pdf.finalprint finalBean;
			finalBean = new rsa.pdf.finalprint();
			com.maan.services.util.dataCollection collect = null;
			collect = new com.maan.services.util.dataCollection();
			com.maan.premium.DAO.PremiumInputsBean pib=new com.maan.premium.DAO.PremiumInputsBean();
			com.maan.services.policyInfo pol=new com.maan.services.policyInfo();
			String[][] quoteInfo= new String[0][0];		
			String[][] commInfo=new String[0][0];
			String[][] rfactorOC=new String[0][0];
			String[][] rfactorDC=new String[0][0];
			String[][] generatedInfo=new String[0][0];
			String applicationNo=collect.getApplicationNo(quoteNo);
			quoteInfo=collect.getQuoteInfo(applicationNo,belongingBranch);	
			commInfo=collect.getCommodityInfo(applicationNo,belongingBranch);
			rfactorOC=collect.getRfactorOC();
			rfactorDC=collect.getRfactorDC(applicationNo);
			generatedInfo=collect.getGeneratedInfo(applicationNo);
			String commInfoSingle=collect.getCommodityInfoSingle(applicationNo,belongingBranch);
			String currencyType="";	
			String decimalPlace="";			
			if(brokerDetails.size()>0)
			{					
				currencyType = (String)brokerDetails.get("CurrencyAbb");
				System.out.print(currencyType);
				decimalPlace =(String) brokerDetails.get("CurrencyDecimal");
			}	
			java.text.NumberFormat rate =new java.text.DecimalFormat("0.000000");
			
			PdfWriter writer=null;
			Document document =  new Document(PageSize.A4, 50, 50, 50, 60);
			try 
			{
				writer = PdfWriter.getInstance(document,new FileOutputStream(filePath));
			} 
			catch (Exception e) 
			{
				logger.debug(e);
			}
			PdfPTableCreation tableCreation,tableCreation1,tableCreation2;
			tableCreation = new PdfPTableCreation();
			tableCreation1=new PdfPTableCreation();
			tableCreation2=new PdfPTableCreation();
			document.open();
			HeaderFooterImage pageWater = new HeaderFooterImage();
			pageWater.onOpenDocument(writer, document);
			pageWater.setImagePath(headImage);
			pageWater.setFooterImagePath(footImage);
			//pageWater.setCreateDate(createDate);
			pageWater.setDisplayText("");						
			pageWater.setCols(cols);
			pageWater.setSignedImage(signImage);
			pageWater.setStampImage(stampImage);
			pageWater.setFontPath(fontPath);
			writer.setPageEvent(pageWater);
			PDFDisplay pdis=new PDFDisplay(); 
			Image signatureImage;
			signatureImage = Image.getInstance(bulletImg);
			signatureImage.scaleToFit(5, 5);
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
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell(("Draft No.: "+quoteNo), fontHeadBold, Rectangle.NO_BORDER,8, 1);
			//tableCreation.insertCell("DRAFT SCHEDULE", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("Draft", fontTextUnderLine, Rectangle.NO_BORDER,8, 2);
			tableCreation.insertCell("", fontHeadBold, Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Insured", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation2.setTable(15);
			if(quoteInfo[0][17] != null&&quoteInfo[0][17].length()>0)
				tableCreation2.insertCell(quoteInfo[0][17], fontTextNormal,Rectangle.NO_BORDER,15, 0);
			if(quoteInfo[0][27] != null&&quoteInfo[0][27].length()>0)
				tableCreation2.insertCell("P.O. Box "+quoteInfo[0][27], fontTextNormal,Rectangle.NO_BORDER,15, 0);
			if(quoteInfo[0][28] != null&&quoteInfo[0][28].length()>0)
				tableCreation2.insertCell(quoteInfo[0][28], fontTextNormal,Rectangle.NO_BORDER,15, 0);
			if(quoteInfo[0][29] != null&&quoteInfo[0][29].length()>0)
				tableCreation2.insertCell(quoteInfo[0][29], fontTextNormal,Rectangle.NO_BORDER,15, 0);
			tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Subject-Matter Insured", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			if(commInfo.length > 1)
			{
				tableCreation2.setTable(20);
				for(int i=0; i<commInfo.length; i++){ 
					if(i==0)
					{
						tableCreation2.insertCell("S.No",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
						//tableCreation2.insertCell("Commodity Name",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,8, 0);
						tableCreation2.insertCell("Commodity Description",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,9, 7);
						tableCreation2.insertCell("Sum Insured",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 7);
						tableCreation2.insertCell("Rate",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 7);
						tableCreation2.insertCell("Excess",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);//tableCreation2.insertCell("Invoice No.",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
						//tableCreation2.insertCell("AWB No.",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					}
					tableCreation2.insertCell((i+1)+".",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
					//tableCreation2.insertCell(commInfo[i][3]==null?"":commInfo[i][3],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,8, 0);
					tableCreation2.insertCell(commInfo[i][11] == null ? "": commInfo[i][11],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,9, 0);
					//tableCreation2.insertCell(commInfo[i][4] == null ? "":pdis.decimalFormat(Double.parseDouble(commInfo[i][4]),Integer.parseInt(decimalPlace))+"("+currencyType +")",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					tableCreation2.insertCell(commInfo[i][4] == null ? "":pdis.decimalFormat(Double.parseDouble(pib.getRoundedValue(commInfo[i][4], (rfactorDC[0][1]==null?"":rfactorDC[0][1]), loginBranch)),Integer.parseInt(decimalPlace))+"("+(rfactorDC[0][0]==null?"":rfactorDC[0][0]) +")",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 1);
					tableCreation2.insertCell(commInfo[i][5] == null ? "":(rate.format(Double.parseDouble(commInfo[i][5]))+"%"),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 1);
					tableCreation2.insertCell(commInfo[i][2] == null ? "0":commInfo[i][2],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 1);
					//tableCreation2.insertCell(commInfo[i][10] == null ? "": commInfo[i][10],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
					//tableCreation2.insertCell(commInfo[i][12] == null ? "": commInfo[i][12],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 0);
				}
				tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
				
			}else
			{	
				//tableCreation1.insertCell(commInfo[0][3]== null ? "": commInfo[0][3],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation1.insertCell(((commInfoSingle!= null &&commInfoSingle.length()>0) ? commInfoSingle:"" ),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
				tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			}
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Voyage", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			String voyage=((("From "+(quoteInfo[0][4] == null ? "": quoteInfo[0][4]))+" To ")+(quoteInfo[0][5] == null ? "": quoteInfo[0][5]));
			tableCreation1.insertCell(voyage.toString(),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Conveyance", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell(quoteInfo[0][0] == null ? "": quoteInfo[0][0],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Estimated Sum Insured", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			String str="";
			if(quoteInfo[0][31] != null && quoteInfo[0][31].length()>0 && !("0%".equalsIgnoreCase(quoteInfo[0][31])) && !("NONE".equalsIgnoreCase(quoteInfo[0][31]))){
				str+=" + "+quoteInfo[0][31];
			}
			 if(quoteInfo[0][32] != null && quoteInfo[0][32].length()>0 && !("0%".equalsIgnoreCase(quoteInfo[0][32])) && !("NONE".equalsIgnoreCase(quoteInfo[0][32]))){ 
				 str+=" + "+quoteInfo[0][32];
			}
			str+=";";
			String sumIns=(((((((((((currencyType)+" ")+(quoteInfo[0][8] == null ? "": pdis.decimalFormat(Double.parseDouble(quoteInfo[0][8]),Integer.parseInt(decimalPlace)))+" (Being ")+(quoteInfo[0][6] == null ? "": quoteInfo[0][6]))+" ")+(quoteInfo[0][9] == null ? "": pdis.decimalFormat(Double.parseDouble(pib.getRoundedValue(quoteInfo[0][9], (rfactorDC[0][1]==null?"":rfactorDC[0][1]),loginBranch)),Integer.parseInt(decimalPlace)))+" ")+str)+" ")+(quoteInfo[0][7] == null ? "": quoteInfo[0][7])+"; Exchange Rate ")+(quoteInfo[0][18] == null ? "": quoteInfo[0][18]))+")");
			logger.info("SumIns=>"+sumIns);
			tableCreation1.insertCell(sumIns,fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			tableCreation1.insertCell("Conditions", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
			tableCreation1.insertCell("Subject to terms and conditions of standard Marine Insured Policy to issued, based on:-",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
			tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
			document.add(tableCreation.getTable());
			tableCreation.setTable(8);
			tableCreation1.setTable(9);
			com.maan.services.ClausesWarrenty clausesValue = new com.maan.services.ClausesWarrenty();
			String remarksAdmin = "";
			String brokerUserText="";
			String freeModeId = "";
			String freeCoverId = "";
			String freeSeaOption = "";
			int clausesCount = 0;
			int exClausesCount = 0;
			int exCount = 0;
			int warCount = 0;
			String exisCoverId = "0";	
			String exisExCoverId = "0";
			String exisTransportId = "0";
			
			try {
				Map displayResults = new HashMap();
				String exStatus = "";
				String warStatus = "";
				String claStatus = "";
				String exClaStatus = "";
			
				String[][] extraClauses = new String[0][0];
			
				String[][] exclusions = new String[0][0];
				String[][] warranties = new String[0][0];
				String[][] clauses = new String[0][0];
			
				String[][] EditExtraClauses = new String[0][0];
			
				String[][] EditExClauses = new String[0][0];
				String[][] EditWarClauses = new String[0][0];
				String[][] EditClauses = new String[0][0];
			
					/*if(quoteNo!=null)
						displayResults = pol.getExclusionWarrantyClauses(quoteNo,user, "rer",productId, openCoverNo, loginBranch);
					else
					{
						pol.setLoginBra(loginBranch);
						displayResults = pol.getExclusionWarrantyClauses(applicationNo,user);
					} */	
				displayResults = new policyInfo().getConditions(applicationNo,loginBranch);
			
				if (displayResults.size() > 0) {
				exisCoverId = (String) displayResults.get("coverId") == null ? exisCoverId
						: (String) displayResults.get("coverId");
				exisExCoverId = (String) displayResults
						.get("extraCoverId") == null ? exisExCoverId
						: (String) displayResults.get("extraCoverId");
				exisTransportId = (String) displayResults
						.get("transportId") == null ? exisTransportId
						: (String) displayResults.get("transportId");
			
				exclusions = (String[][]) displayResults
						.get("exclusionsDesc") == null ? exclusions
						: (String[][]) displayResults
						.get("exclusionsDesc");
				warranties = (String[][]) displayResults
						.get("warrantyDesc") == null ? warranties
						: (String[][]) displayResults
						.get("warrantyDesc");
				clauses = (String[][]) displayResults
						.get("clausesDesc") == null ? clauses
						: (String[][]) displayResults
						.get("clausesDesc");
			
				extraClauses = (String[][]) displayResults
						.get("extraClausesDesc") == null ? extraClauses
						: (String[][]) displayResults
						.get("extraClausesDesc");
			
				EditClauses = (String[][]) displayResults
						.get("editedClauses") == null ? EditClauses
						: (String[][]) displayResults
						.get("editedClauses");//[0][83]);
				EditExtraClauses = (String[][]) displayResults
						.get("editedExtraClauses") == null ? EditExtraClauses
						: (String[][]) displayResults
						.get("editedExtraClauses");//[0][83]);
			
				EditWarClauses = (String[][]) displayResults
						.get("editedWarClauses") == null ? EditWarClauses
						: (String[][]) displayResults
						.get("editedWarClauses");//[0][83]);
				EditExClauses = (String[][]) displayResults
						.get("editedExClauses") == null ? EditExClauses
						: (String[][]) displayResults
						.get("editedExClauses");//[0][83]);
				}
				boolean clususesflag=true;
							try {
								if (EditClauses.length > 0) {
							for (int a222 = 0; a222 < EditClauses.length; a222++) {
								tableCreation2.setTable(45);
								tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
								//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
								tableCreation2.insertCell(EditClauses[a222][1] == null ? "": EditClauses[a222][1],fontTextNormal,Rectangle .NO_BORDER,43, 0);
								if(clususesflag)
								{
									tableCreation1.insertCell("Clauses", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
									tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP,6, 0);
									clususesflag=false;
								}else{
									tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
									tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT,6, 0);
								}
								tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
								document.add(tableCreation.getTable());
								tableCreation.setTable(8);
								tableCreation1.setTable(9);
								clausesCount = clausesCount + 1;
					}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						if (clauses.length > 0) {
					for (int kc = 0; kc < clauses.length; kc++) {
						claStatus = clauses[kc][1] == null ? ""
						: clauses[kc][1];

					  if ("R".equalsIgnoreCase(claStatus)) {

						    tableCreation2.setTable(45);
						    tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
							//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
							tableCreation2.insertCell(clauses[kc][0] == null ? "": clauses[kc][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
							if (clususesflag)
							{
								tableCreation1.insertCell("Clauses", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,3, 0);
								tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP,6, 0);
								clususesflag=false;
							}else{
								tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT |Rectangle.RIGHT,3, 0);
								tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT,6, 0);
							}
							tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);							
							document.add(tableCreation.getTable());
							tableCreation.setTable(8);
							tableCreation1.setTable(9);
							clausesCount = clausesCount + 1;
						} else {
							 tableCreation2.setTable(45);
							 tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
							 //tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
							 tableCreation2.insertCell(clauses[kc][0]== null ? "": clauses[kc][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
							 if (clususesflag)
								{
									tableCreation1.insertCell("Clauses", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
									 tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT|Rectangle.TOP,6, 0);
									clususesflag=false;
								}else{
									tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
									 tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT|Rectangle.RIGHT,6, 0);
								}
							 tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
							 document.add(tableCreation.getTable());
							 tableCreation.setTable(8);
							 tableCreation1.setTable(9);
						}
						}
								}
								} catch (Exception e) {
									e.printStackTrace();
								}
										try {
										if (EditExtraClauses.length > 0) {
									for (int a222 = 0; a222 < EditExtraClauses.length; a222++) {
										 tableCreation2.setTable(45);
										 tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
										 //tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
										 tableCreation2.insertCell(EditExtraClauses[a222][1]== null ? "": EditExtraClauses[a222][1],fontTextNormal,Rectangle.NO_BORDER,43, 0);
										 tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT |Rectangle.RIGHT,3, 0);
										 tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
										 tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
										 document.add(tableCreation.getTable());
										 tableCreation.setTable(8);
										 tableCreation1.setTable(9);
										 exClausesCount = exClausesCount + 1;
								}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								try {
									if (extraClauses.length > 0) {
								for (int kec = 0; kec < extraClauses.length; kec++) {
									exClaStatus = extraClauses[kec][1] == null ? ""
									: extraClauses[kec][1];

									if ("R".equalsIgnoreCase(exClaStatus)) {
										 tableCreation2.setTable(45);
										 tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
										 //tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
										 tableCreation2.insertCell(extraClauses[kec][0]== null ? "": extraClauses[kec][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
										 tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT | Rectangle.RIGHT,3, 0);
										 tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.RIGHT,6, 0);
										 tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
										 document.add(tableCreation.getTable());
										 tableCreation.setTable(8);
										 tableCreation1.setTable(9);
										 exClausesCount = exClausesCount + 1;
										} else {
											 tableCreation2.setTable(45);
											 tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2,2);
											 //tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
											 tableCreation2.insertCell(extraClauses[kec][0]== null ? "": extraClauses[kec][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
											 tableCreation1.insertCell("", fontTextNormal, Rectangle.LEFT  |Rectangle.RIGHT,3, 0);
											 tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT  |Rectangle.RIGHT,6, 0);
											 tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
											 document.add(tableCreation.getTable());
											 tableCreation.setTable(8);
											 tableCreation1.setTable(9);
									}
									}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
									
									
					//EXCLUSIONS	
									
									
					tableCreation.setTable(8);
					tableCreation1.setTable(9);		
					boolean exclusionflag=true;
										try {
											if (EditExClauses.length > 0) {
										for (int a222 = 0; a222 < EditExClauses.length; a222++) {
											 tableCreation2.setTable(45);
											 	tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
												//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
												tableCreation2.insertCell(EditExClauses[a222][1] == null ? "": EditExClauses[a222][1],fontTextNormal,Rectangle.NO_BORDER,43, 0);
												 if (exclusionflag)
													{
														tableCreation1.insertCell("Exclusions", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.RIGHT|Rectangle.TOP,6, 0);
														exclusionflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.RIGHT,6, 0);
													}
												tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
												document.add(tableCreation.getTable());
												 tableCreation.setTable(8);
												 tableCreation1.setTable(9);
												exCount = exCount + 1;
										}
											}
										} catch (Exception e) {
											e.printStackTrace();
										}
										try {
											if (exclusions.length > 0) {
											for (int ke = 0; ke < exclusions.length; ke++) {
		
											exStatus = exclusions[ke][1] == null ? ""
											: exclusions[ke][1];
		
											if ("R".equalsIgnoreCase(exStatus)) {
												tableCreation2.setTable(45);
												tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
												//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
												tableCreation2.insertCell(exclusions[ke][0] == null ? "":exclusions[ke][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
												 if (exclusionflag)
													{
														tableCreation1.insertCell("Exclusions", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
														exclusionflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
													}
												
												tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
												document.add(tableCreation.getTable());
												 tableCreation.setTable(8);
												 tableCreation1.setTable(9);
												exCount = exCount + 1;
											} else {
												
												tableCreation2.setTable(45);
												tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
												//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
												tableCreation2.insertCell(exclusions[ke][0] == null ? "":exclusions[ke][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
												 if (exclusionflag)
													{
														tableCreation1.insertCell("Exclusions", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
														exclusionflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
													}
												tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
												document.add(tableCreation.getTable());
												 tableCreation.setTable(8);
												 tableCreation1.setTable(9);
											  }
											 }
											}
										  } 
										  catch (Exception e) {
												e.printStackTrace();
										 }
										  
										  //WARRANTIES	  
										  boolean warrantiesflag=true;
										  
											tableCreation.setTable(8);
											tableCreation1.setTable(9);			
										  
									try 
									{
										/*if (EditWarClauses.length > 0 || warranties.length > 0) {
											tableCreation1.insertCell("WARRANTIES", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);		
								
										}*/
										if (EditWarClauses.length > 0)
										{
												for (int a222 = 0; a222 < EditWarClauses.length; a222++) {
													
													tableCreation2.setTable(45);
													tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2,2);
													//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
													tableCreation2.insertCell(EditWarClauses[a222][1] == null ? "": EditWarClauses[a222][1],fontTextNormal,Rectangle.NO_BORDER,43, 0);
													if (warrantiesflag)
													{
														tableCreation1.insertCell("Warranties", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
														warrantiesflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
													}
													tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
													document.add(tableCreation.getTable());
													warCount = warCount + 1;
												}
													}
												} catch (Exception e) {
													e.printStackTrace();
												}
												try {
													if (warranties.length > 0) {
												for (int kw = 0; kw < warranties.length; kw++) {
													warStatus = warranties[kw][1] == null ? ""
													: warranties[kw][1];
			
													if ("R".equalsIgnoreCase(warStatus)) {
														tableCreation2.setTable(45);
														tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
														//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
														tableCreation2.insertCell(warranties[kw][0] == null ? "": warranties[kw][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
														if (warrantiesflag)
														{
															tableCreation1.insertCell("Warranties", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
															tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
															warrantiesflag=false;
														}else{
															tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
															tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
														}
														tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
														document.add(tableCreation.getTable());
														 tableCreation.setTable(8);
														 tableCreation1.setTable(9);
														warCount = warCount + 1;
											} else {
												tableCreation2.setTable(45);
												tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
												//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
												tableCreation2.insertCell(warranties[kw][0] == null ? "": warranties[kw][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
												if (warrantiesflag)
												{
													tableCreation1.insertCell("Warranties", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
													tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
													warrantiesflag=false;
												}else{
													tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
													tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
												}
												
												tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
												document.add(tableCreation.getTable());
												 tableCreation.setTable(8);
												 tableCreation1.setTable(9);
												
											}
											}
												}
											} catch (Exception e) {
												e.printStackTrace();
											}

											String pdfModify = pol.getPolicysBackDesc(quoteNo);
											pdfModify = pdfModify == null ? "" : pdfModify;
											String adminpdfModify = "";
											if (pdfModify.length() > 0
											&& !pdfModify.equalsIgnoreCase("NOTHING"))
												adminpdfModify = pdfModify;
											else if (!pdfModify.equalsIgnoreCase("Admin"))
												pdfModify = pol.getPolicysFreshBackDesc(belongingBranch);
											/*if (pdfModify.equalsIgnoreCase("Admin")) {
											}*/
											if ("3".equalsIgnoreCase(productId)
											&& adminpdfModify.length() <= 0
											&& !pdfModify.equalsIgnoreCase("NOTHING")
											&& !pdfModify.equalsIgnoreCase("Admin")) {
												
												/*tableCreation.insertCell(pdfModify,fontTextNormal, Rectangle.NO_BORDER,8, 2);
												document.add(tableCreation.getTable());*/
											} else if ("3".equalsIgnoreCase(productId)
											&& adminpdfModify.length() > 0
											&& !pdfModify.equalsIgnoreCase("NOTHING")
											&& !pdfModify.equalsIgnoreCase("Admin")) {
												/*tableCreation.insertCell(adminpdfModify,fontTextNormal, Rectangle.NO_BORDER,8, 2);
												document.add(tableCreation.getTable());*/
											}
											if (adminpdfModify.length() > 0)
												pdfModify = adminpdfModify;
											if (!pol.getPolicyBackStatus(
											quoteNo)
											.equalsIgnoreCase("0")
											&& "11".equalsIgnoreCase(productId)) {
												/*tableCreation.insertCell(pdfModify,fontTextNormal, Rectangle.NO_BORDER,8, 2);
												document.add(tableCreation.getTable());*/
											}
											try {
												if ("11".equalsIgnoreCase(productId)) {
											String[][] openFreeText = new String[0][0];
											String[][] openSeaFreeTextOptions = new String[0][0];
											openFreeText = pol.getOpenCoverRelatedDatas(
													openCoverNo, freeModeId, freeCoverId,
													"OPENFREETEXT", belongingBranch);
											if ("1".equalsIgnoreCase(freeModeId)) {
												if ("FCL".equalsIgnoreCase(freeSeaOption)) {
													freeSeaOption = "101";
												} else if ("LCL".equalsIgnoreCase(freeSeaOption)) {
													freeSeaOption = "102";
												} else if ("BREAK BULK"
												.equalsIgnoreCase(freeSeaOption)) {
													freeSeaOption = "103";
												} else if ("BULK".equalsIgnoreCase(freeSeaOption)) {
													freeSeaOption = "104";
												} else {
													freeSeaOption = "0";
												}
											}
											if ("0".equalsIgnoreCase(freeSeaOption)
													|| "".equalsIgnoreCase(freeSeaOption)) {
												openSeaFreeTextOptions = new String[0][0];
											} else {
												openSeaFreeTextOptions = pol
												.getOpenCoverRelatedDatas(openCoverNo,
														freeModeId, freeSeaOption,
														"OPENFREETEXT", belongingBranch);
											}
											
											tableCreation.setTable(8);
											tableCreation1.setTable(9);
											boolean specialflag=true;
											/*if (openFreeText.length > 0
													|| openSeaFreeTextOptions.length > 0) {
												tableCreation1.insertCell("Special Conditions", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
											}*/
											if (openFreeText.length > 0) {
												for (int ft = 0; ft < openFreeText.length; ft++) {
													tableCreation2.setTable(45);
													tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2,2);
													//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
													tableCreation2.insertCell(openFreeText[ft][0] == null ? ""	: openFreeText[ft][0],fontTextNormal,Rectangle.NO_BORDER,43, 0);
													if (specialflag)
													{
														tableCreation1.insertCell("Special Conditions", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT |Rectangle.TOP,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
														specialflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
													}
													
													tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
													document.add(tableCreation.getTable());
													 tableCreation.setTable(8);
													 tableCreation1.setTable(9);
											}
											}
											if (openSeaFreeTextOptions.length > 0) {
												for (int fts = 0; fts < openSeaFreeTextOptions.length; fts++) {
													tableCreation2.setTable(10);
													tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
													//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
													tableCreation2.insertCell(openSeaFreeTextOptions[fts][0] == null ? "": openSeaFreeTextOptions[fts][0],fontTextNormal,Rectangle.NO_BORDER,8, 0);
													if (specialflag)
													{
														tableCreation1.insertCell("Special Conditions", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
														specialflag=false;
													}else{
														tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
														tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
													}
													
													tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);	
													document.add(tableCreation.getTable());
													tableCreation.setTable(8);
													tableCreation1.setTable(9);
										}
										}
											}//End of Product Id 11
										} catch (Exception e) {
											e.printStackTrace();
										}
										
										tableCreation.setTable(8);
										tableCreation1.setTable(9);	
										
										if ("NIL".equalsIgnoreCase(remarksAdmin)
										|| "".equalsIgnoreCase(remarksAdmin)) {

										} else {
											tableCreation1.insertCell("Admin Clauses", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
											}
											} catch (Exception ex) {
											ex.printStackTrace();
											}
										brokerUserText=pol.getBrokerUserFreeText(quoteNo);
										boolean freetextflag=true;
										if(brokerUserText.trim().length()>0)
										{
											tableCreation2.setTable(45);
											tableCreation2.insertCellMiddle(signatureImage, Rectangle.NO_BORDER,2, 2);
											//tableCreation2.insertCell(" * ",fontTextNormal,Rectangle.NO_BORDER,2, 0);
											tableCreation2.insertCell(brokerUserText,fontTextNormal,Rectangle.NO_BORDER,43, 0);
											if (freetextflag)
											{
												tableCreation1.insertCell("Broker/User Free Text", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP ,3, 0);
												tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT|Rectangle.TOP,6, 0);
												freetextflag=false;
											}else{
												tableCreation1.insertCell("", fontTextNormal,Rectangle.LEFT |Rectangle.RIGHT,3, 0);
												tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
											}
											tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT |Rectangle.RIGHT,6, 0);
											tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
											document.add(tableCreation.getTable());
											tableCreation.setTable(8);
											tableCreation1.setTable(9);
										}
								tableCreation.setTable(8);
								tableCreation1.setTable(9);	
								if (commInfo.length ==1){
									tableCreation1.insertCell("Rates", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
									
									tableCreation2.setTable(28);
									tableCreation2.insertCell("Marine       : ",fontTextNormal,Rectangle.LEFT ,4, 0);
									tableCreation2.insertCell(((("At ")+(commInfo[0][5] == null ? "": rate.format(Double.parseDouble(commInfo[0][5]))))+"%"),fontTextNormal,Rectangle.RIGHT,24, 0);
									tableCreation2.insertCell("War& Srcc : ",fontTextNormal,Rectangle.LEFT ,4, 0);
									tableCreation2.insertCell((("Rate ruling at the time of shipment shall be applied currently being at ")+(commInfo[0][6] == null ? "": rate.format(Double.parseDouble(commInfo[0][6])))+"%"),fontTextNormal,Rectangle.RIGHT,24, 0);
									tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
									tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
									document.add(tableCreation.getTable());
									tableCreation.setTable(8);
									tableCreation1.setTable(9);
							
							}
								tableCreation.setTable(8);
								tableCreation1.setTable(9);	
								tableCreation1.insertCell("Premium", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
								tableCreation2.setTable(13);
								tableCreation2.insertCell(("Marine       : "+currencyType),fontTextNormal,Rectangle.LEFT | Rectangle.TOP,3, 0);
								tableCreation2.insertCell((quoteInfo[0][10] == null ? "":pdis.decimalFormat(Double.parseDouble(quoteInfo[0][10]),Integer.parseInt(decimalPlace))),fontTextNormal, Rectangle.TOP,3, 1);
								tableCreation2.insertCell("",fontTextNormal,Rectangle.RIGHT| Rectangle.TOP,7, 0);
								tableCreation2.insertCell((("War& Srcc : ")+currencyType),fontTextNormal,Rectangle.LEFT  ,3, 0);
								tableCreation2.insertCell((quoteInfo[0][11] == null ? "": pdis.decimalFormat(Double.parseDouble(quoteInfo[0][11]),Integer.parseInt(decimalPlace))),fontTextNormal,Rectangle.NO_BORDER,3, 1);
								tableCreation2.insertCell("",fontTextNormal,Rectangle.RIGHT,7, 0);
								tableCreation2.insertCell(("Total          : "+currencyType),fontTextNormal,Rectangle.LEFT  ,3, 0);
								tableCreation2.insertCell(((quoteInfo[0][11] == null ? "": pdis.decimalFormat((Double.parseDouble(quoteInfo[0][10])+Double.parseDouble(quoteInfo[0][11])),Integer.parseInt(decimalPlace)))),fontTextNormal,Rectangle.TOP,3, 1);
								tableCreation2.insertCell("",fontTextNormal,Rectangle.RIGHT,7, 0);
								tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
								tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
								document.add(tableCreation.getTable());
								tableCreation.setTable(8);
								tableCreation1.setTable(9);	
								tableCreation1.insertCell("Issuance Fees", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
								tableCreation2.setTable(12);
								tableCreation2.insertCell((((currencyType)+" ")+(quoteInfo[0][13] == null ? "": pdis.decimalFormat(Double.parseDouble(quoteInfo[0][13]),Integer.parseInt(decimalPlace)))),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,12, 0);
								tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
								tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
								document.add(tableCreation.getTable());
								tableCreation.setTable(8);
								tableCreation1.setTable(9);	
								tableCreation1.insertCell("Validity",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
								tableCreation1.insertCell(("Our Quotation is valid for 30 days from "+(quoteInfo[0][16] == null ? "": quoteInfo[0][16])),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
								tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
								document.add(tableCreation.getTable());
								tableCreation.setTable(8);
								
								//Excess Added By Ezhumalai.D start
								String displayMode="DRAFTMODE";
								int decimalDigit = 0;
								int index=0;
								String comExcess[][] = new String[0][0];
								
								if ("3".equalsIgnoreCase(productId)) {
								comExcess = finalBean.getComExcessPre(quoteNo,displayMode);
								}else{
								comExcess = finalBean.getComExcessPre(quoteNo,displayMode,openCoverNo);	
								}
								
								/*if(comExcess.length>1)
								{
									tableCreation.setTable(8);
									tableCreation1.setTable(9);
									tableCreation1.insertCell("EXCESS", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
								    tableCreation2.setTable(20);
									for(int e=0;e<comExcess.length;e++)
									{
									String temp = comExcess[e][0]==null?"0":comExcess[e][0];
										if(e==0)
										{
											tableCreation2.insertCell("S.No",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
											tableCreation2.insertCell("SUBJECT-MATTER INSURED",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,9, 7);
											tableCreation2.insertCell("CURRENCY",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 7);
											tableCreation2.insertCell("EXCESS",fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 7);
										}
										tableCreation2.insertCell(String.valueOf(++index),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,2, 7);
										tableCreation2.insertCell(comExcess[e][1],fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,9, 0);
										tableCreation2.insertCell(""+currencyType,fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,4, 1);
										tableCreation2.insertCell(pdis.decimalFormat(Double.parseDouble(temp), decimalDigit),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,5, 1);
									}
									tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
									tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
								}*/
								
								if(comExcess.length>0 && comExcess.length==1)
								{
									tableCreation.setTable(8);
									tableCreation1.setTable(9);	
									for(int e=0;e<comExcess.length;e++)
									{
										String temp = comExcess[e][0]==null?"0":comExcess[e][0];
										if(!"0".equals(temp))
										{
											tableCreation1.insertCell("Excess", fontTextNormal, Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,3, 0);
											tableCreation2.setTable(12);
											tableCreation2.insertCell("Claims, if any, are payable subject to an excess of "+currencyType+" "+pdis.decimalFormat(Double.parseDouble(temp), decimalDigit),fontTextNormal,Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,12, 0);
											tableCreation1.insertCell(tableCreation2.getTable(),Rectangle.LEFT | Rectangle.TOP |Rectangle.RIGHT|Rectangle .BOTTOM,6, 0);
											tableCreation.insertCell(tableCreation1.getTable(), Rectangle.NO_BORDER,8, 2);
											++index;
										}
									}
									document.add(tableCreation.getTable());
									tableCreation.setTable(8);
								}
								//Excess Added By Ezhumalai.D End
								
								String polGenDate = "";
								String polGenTime = "";
								if(generatedInfo[0][1]!=null&&generatedInfo[0][1].length()>0)
								{
									polGenDate = generatedInfo[0][1].substring(0,generatedInfo[0][1].indexOf(' '));
									polGenTime = generatedInfo[0][1].substring((generatedInfo[0][1].indexOf(' '))+1,generatedInfo[0][1].length());
								}
								polGenDate = polGenDate+" Time: "+polGenTime;
								tableCreation1.setTable(8);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,8, 0);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,8, 0);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,8, 0);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,8, 0);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,8, 0);
								tableCreation1.insertCell("Created on: "+ polGenDate, fontTextNormal,Rectangle.NO_BORDER, 8, 0);
								tableCreation1.insertCell("", fontTextNormal,Rectangle.NO_BORDER, 8, 0);
								tableCreation1.insertCell("Entered By: "+(generatedInfo[0][0]==null?"":generatedInfo[0][0]), fontTextNormal,Rectangle.NO_BORDER, 8, 0);
								tableCreation.insertCell(tableCreation1.getTable(),Rectangle.NO_BORDER,4, 0,0);
								tableCreation.insertCell("",fontTextNormal,Rectangle.NO_BORDER,4, 0);
								document.add(tableCreation.getTable());
			
			document.close();
			
		}catch(Exception e)
		{
			logger.debug(e);
		}
		logger.info("PDFCreatorBean writeQuoteSchedule()-Exit");
	}
	
	public String getApplicationNo1(String quoteNo)
	{
		logger.info("getApplicationNo - Enter || quoteNo: "+quoteNo);
		String result=runner.singleSelection("SELECT APPLICATION_NO FROM POSITION_MASTER WHERE quote_no=? AND STATUS in ('Y','E','D')",new String[]{quoteNo});
		logger.info("getApplicationNo - Exit || Result: "+result);
		return 	result;
	}
	
	public String getBelongingBranch(String branchCode) {
		logger.info("getBelongingBranch - Enter || branchCode: "+branchCode);
		String result=runner.singleSelection("SELECT BELONGING_BRANCH FROM BRANCH_MASTER WHERE BRANCH_CODE=?",new String[]{branchCode});
		logger.info("getBelongingBranch - Exit || Result: "+result);
		return 	result;
	}

	public String getBelongingBranch() {
		return belongingBranch;
	}

	public void setBelongingBranch(String belongingBranch) {
		this.belongingBranch = belongingBranch;
	}
}
