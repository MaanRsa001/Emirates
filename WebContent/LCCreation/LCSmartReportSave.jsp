<%@ page import = "org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFCell"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFRow"%>
<%@ page import = "org.apache.poi.hssf.util.Region"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFPrintSetup"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFCellStyle"%>
<%@ page import = "org.apache.poi.hssf.util.HSSFColor"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFFont"%>
<%@ page import = "org.apache.poi.hssf.usermodel.HSSFDataFormat"%>
<%@ page import = "java.util.ArrayList"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.text.DateFormat"%>
<%@ page import = "java.text.SimpleDateFormat"%>
<%@ page import = "java.io.*" %>

<jsp:useBean id="lcBean" class="com.maan.opencover.LCDetails.LCInputsBean">
</jsp:useBean>

<%
	String fromBroker = request.getParameter("fromBroker");
	fromBroker = fromBroker==null?"No":fromBroker;

    
	
	/** For Branch address **/
	String braAddress = "";
	String braPO = "";
	String braCity = "";
	String braCountry = "";
	String braPhone = "";
	String placeCode[][] = new String[0][0];
	String currencyType = "";
		String brokerCode = (String)session.getAttribute("AdminBranchCode");
		HashMap brokerDetails1 = new HashMap();
		brokerDetails1 = (HashMap)session.getAttribute("BrokerDetails");
		String cid="";
		String dcid="";
		String decimalPlace ="";
		if(brokerDetails1.size()>0)
		{
			cid = (String)brokerDetails1.get("Orgination");
			dcid = (String)brokerDetails1.get("Destination");
			currencyType = (String)brokerDetails1.get("CurrencyAbb");
			decimalPlace = (String)brokerDetails1.get("CurrencyDecimal");
		}
	if(!fromBroker.equalsIgnoreCase("Yes"))
	{
		placeCode = lcBean.getBranchDetails(brokerCode);
		if(placeCode.length>0)
		{
			braAddress = placeCode[0][8] == null ? "" : placeCode[0][8];
			braPO = placeCode[0][10] == null ? "" : placeCode[0][10];
			braCity = placeCode[0][11] == null ? "" : placeCode[0][11];
			braCountry = placeCode[0][12] == null ? "" : placeCode[0][12];
			braPhone = placeCode[0][13] == null ? "" : placeCode[0][13];
		}
	}
	
	java.text.NumberFormat fmt=null;
	if(decimalPlace.equalsIgnoreCase("2"))
		fmt=new java.text.DecimalFormat("##,##0.00");
	else if(decimalPlace.equalsIgnoreCase("3"))
		fmt=new java.text.DecimalFormat("##,##0.000");
	else if(decimalPlace.equalsIgnoreCase("4"))
		fmt=new java.text.DecimalFormat("##,##0.0000");
	else
		fmt=new java.text.DecimalFormat("##,##0.00");

		
String[][] viewQuote123 = new String[0][0];
HashMap lcDetails= new HashMap();
HashMap cusDetails = new HashMap();
lcDetails = lcBean.getLCReports(brokerCode,cid);
if(lcDetails.size()>0)
{
	viewQuote123 = (String[][])lcDetails.get("LCAll");
	cusDetails = (HashMap)lcDetails.get("cusDetails");
}	
java.util.Date date = new java.util.Date();
java.text.SimpleDateFormat YearOnly = new java.text.SimpleDateFormat("yyyy");
java.text.SimpleDateFormat MonthOnly = new java.text.SimpleDateFormat("MM");
java.text.SimpleDateFormat DayOnly = new java.text.SimpleDateFormat("dd");

String YearString = YearOnly.format(date);
String MonthString = MonthOnly.format(date);
String DayString = DayOnly.format(date);
String rptDate = DayString+"/"+MonthString+"/"+YearString;
  int n = 0;
  try
  {
	   System.setProperty("java.awt.headless", "true"); 
	   HSSFWorkbook wb = new HSSFWorkbook();
	   HSSFSheet sheet = wb.createSheet("new sheet");
	   sheet.getPrintSetup().setLandscape(true);
	   sheet.getPrintSetup().setScale((short)95);
	   HSSFDataFormat format = wb.createDataFormat();
	   HSSFFont font = wb.createFont();
	   HSSFFont font1 = wb.createFont();
	   font.setFontName("Arial");
	   font.setFontHeightInPoints((short)9);
	   font.setBoldweight((short)10);
	   font1.setFontName("Arial");
	   font1.setFontHeightInPoints((short)8);
	   font1.setBoldweight((short)10);
	   HSSFRow row = sheet.createRow((short)0);
	   HSSFRow row1 = sheet.createRow((short)1);
	   HSSFRow row2 = sheet.createRow((short)2);
	   HSSFRow row3 = sheet.createRow((short)3);
	   HSSFRow row4 = sheet.createRow((short)4);
	   HSSFRow row5 = sheet.createRow((short)5);
	   HSSFRow row6 = sheet.createRow((short)6);
	   HSSFRow row7 = sheet.createRow((short)7);
	   HSSFRow row8 = sheet.createRow((short)8);
	   HSSFRow row9 = sheet.createRow((short)9);
	   HSSFRow row10 = sheet.createRow((short)10);
	   HSSFRow row11 = sheet.createRow((short)11);
	   HSSFRow row12 = sheet.createRow((short)12);
	   HSSFRow row13 = sheet.createRow((short)13);
	   HSSFRow row14 = sheet.createRow((short)14);
	   HSSFRow row15 = sheet.createRow((short)16);
	   HSSFRow totalrow;
	   HSSFCellStyle style = wb.createCellStyle();
	   HSSFCellStyle style1 = wb.createCellStyle();
	   HSSFCellStyle style2 = wb.createCellStyle();
	   HSSFCellStyle style3 = wb.createCellStyle();
	   HSSFCellStyle style4 = wb.createCellStyle();
	   HSSFCellStyle style5 = wb.createCellStyle();
	   HSSFCellStyle style6 = wb.createCellStyle();
	   HSSFCellStyle style7 = wb.createCellStyle();
	   style.setFont(font); 
	   style7.setFont(font);
	   style7.setWrapText( true );
	  style7.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  style1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	  style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  style1.setRightBorderColor(HSSFColor.BLACK.index);
	  style1.setLeftBorderColor(HSSFColor.BLACK.index);
	  style1.setTopBorderColor(HSSFColor.BLACK.index);
	  style1.setBottomBorderColor(HSSFColor.BLACK.index);
	  font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	  style1.setFont(font);
	  style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	  style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  style3.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  style3.setWrapText( true );
	  style3.setFont(font1);
	  style4.setFont(font1);
	  style4.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
      style4.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style4.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  style4.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  style4.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  style4.setWrapText( true );
	  style5.setAlignment(HSSFCellStyle.ALIGN_LEFT);
      style5.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style5.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  style5.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  style5.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  style5.setWrapText( true );
	  style5.setFont(font1);
	  style6.setBorderRight(HSSFCellStyle.BORDER_THIN);
	  style6.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
	  style6.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
 	  style6.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	  style6.setBorderTop(HSSFCellStyle.BORDER_THIN);
	  style6.setBorderBottom(HSSFCellStyle.BORDER_THIN);
	  style6.setRightBorderColor(HSSFColor.BLACK.index);
	  style6.setLeftBorderColor(HSSFColor.BLACK.index);
	  style6.setTopBorderColor(HSSFColor.BLACK.index);
	  style6.setBottomBorderColor(HSSFColor.BLACK.index);
	  style6.setDataFormat(format.getFormat("#,##0.00"));
	  style6.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	   style1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	   font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
  	   style6.setFont(font);
	   style1.setFont(font);
	   style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	   HSSFCell cell1,cell2,cell3,cell4,cell5,totalcell, totalcell1, datecell1, datecell2,datecell3,datecell4,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14;
	   sheet.addMergedRegion(new Region(0,(short)3,0,(short)6));
	   sheet.addMergedRegion(new Region(1,(short)3,1,(short)6));
	   sheet.addMergedRegion(new Region(2,(short)3,2,(short)6));
	   sheet.addMergedRegion(new Region(3,(short)3,3,(short)6));
       sheet.addMergedRegion(new Region(4,(short)3,4,(short)6));
	   sheet.addMergedRegion(new Region(5,(short)3,5,(short)6));
	if(!fromBroker.equalsIgnoreCase("Yes"))
	{
	   cell1 = row.createCell((short)2);
	   cell1.setCellValue(braAddress);
	   cell1.setCellStyle(style); 
	   cell2 = row1.createCell((short)2);
	   cell2.setCellValue(braPO);
	   cell2.setCellStyle(style);
	   cell3 = row2.createCell((short)2);
	   cell3.setCellValue(braCity+" - "+braCountry);
	   cell3.setCellStyle(style);
	   cell4 = row3.createCell((short)2);
	   cell4.setCellValue("Tel: "+braPhone);
	   cell4.setCellStyle(style);
	   cell5 = row4.createCell((short)2);
	   cell5.setCellValue("online.Emirates.ae");
	   cell5.setCellStyle(style); 
	}
	   cell6 = row5.createCell((short)2);
	   cell6.setCellValue("LC Smart Reports");
	   cell6.setCellStyle(style1); 
	   
	   cell7 = row6.createCell((short)2);
	   cell7.setCellValue("As On Date "+rptDate);
	   cell7.setCellStyle(style1); 
	   
   String [] ColHeader1 =new String[0];
	ColHeader1 = new String[]{"S.No","Broker Name","Open Cover Code","Customer Name","Bank Name","LC Number","LC Start Date","LC Expiry Date","LC Amount","Used Amount("+currencyType+")","Pending LC Amount "+currencyType};
		
		
		HSSFCell cell[][] = new HSSFCell[viewQuote123.length][ColHeader1.length]; 

	  	for(n=0;n<ColHeader1.length;n++)
		{
			try
			{
					sheet.setColumnWidth((short)0,(short)(256*5));
					sheet.setColumnWidth((short)1,(short)(256*25));
					sheet.setColumnWidth((short)2,(short)(256*35));
					sheet.setColumnWidth((short)3,(short)(256*30));
					sheet.setColumnWidth((short)4,(short)(256*20));
					sheet.setColumnWidth((short)5,(short)(256*15));
					sheet.setColumnWidth((short)6,(short)(256*15));
					sheet.setColumnWidth((short)7,(short)(256*15));
					sheet.setColumnWidth((short)8,(short)(256*15));
					sheet.setColumnWidth((short)9,(short)(256*15));
					sheet.setColumnWidth((short)10,(short)(256*25));
										
					cell[0][n] = row8.createCell((short)n);
					cell[0][n].setCellValue(ColHeader1[n].trim());
					cell[0][n].setCellStyle(style1);
			}
			catch(Exception e)
			{
					System.out.println("Error"+e);e.printStackTrace();
			}
		}
			   
	  	 //  if(true)return;


       
	int sno=0;
	int k=9;
	for(int m=0;m<viewQuote123.length;m++,k++)
	{
		sno = sno+1;
		
		for(n=0;n<ColHeader1.length;n++)
		{
			row5 = sheet.createRow((short)k);
			cell[m][n]= row5.createCell((short)n);	
			if(n==0)
			{
			  cell[m][n].setCellValue(sno);
			   cell[m][n].setCellStyle(style3);  
			}
			if(n==1)
			{
			  cell[m][n].setCellValue((viewQuote123[m][0]==null?"":viewQuote123[m][0]));
			   cell[m][n].setCellStyle(style5);  
			}
			if(n==2)
			{
			  cell[m][n].setCellValue((viewQuote123[m][1]==null?"":viewQuote123[m][1]));
			   cell[m][n].setCellStyle(style5);  
			}
			if(n==3)
			{
				String str="";
				if(cusDetails.get(""+viewQuote123[m][2])!=null)
					str=(String)cusDetails.get(""+viewQuote123[m][2]);
				cell[m][n].setCellValue(str);
				cell[m][n].setCellStyle(style5);  
			}
			if(n==4)
			{
			  cell[m][n].setCellValue(viewQuote123[m][3]==null?"":viewQuote123[m][3]);
			  cell[m][n].setCellStyle(style5);  
			}
			if(n==5)
			{
			  cell[m][n].setCellValue((viewQuote123[m][4]==null?"":viewQuote123[m][4]));
			  cell[m][n].setCellStyle(style5);  
			}
			if(n==6)
			{
			  cell[m][n].setCellValue(viewQuote123[m][8]==null?"":viewQuote123[m][8]);
			  cell[m][n].setCellStyle(style3);  
			}
			if(n==7)
			{
			  cell[m][n].setCellValue(viewQuote123[m][9]==null?"":viewQuote123[m][9]);
			  cell[m][n].setCellStyle(style3);  
			}
			if(n==8)
			{
				  double LCAmt = 0;
				  LCAmt = Double.parseDouble((viewQuote123[m][10]==null?"0":viewQuote123[m][10]));
				  cell[m][n].setCellValue(fmt.format(LCAmt));
				  cell[m][n].setCellStyle(style4);  
			}
			if(n==9)
			{
				  double usedAmt = 0;
				  //double exRate = lcBean.getExchangeRate(viewQuote123[m][13],cid);
				  usedAmt = lcBean.getLCUsedSumInsured(viewQuote123[m][11],viewQuote123[m][12],viewQuote123[m][7]);
				  cell[m][n].setCellValue(fmt.format(usedAmt));
				  cell[m][n].setCellStyle(style4);  
			}
			if(n==10)
			{
				  double LCPending = 0;
				  LCPending = Double.parseDouble((viewQuote123[m][5]==null?"0":viewQuote123[m][5]));
				  cell[m][n].setCellValue(fmt.format(LCPending));
				  cell[m][n].setCellStyle(style4);  
			}
		}
	}
	

	String fileName = "LCSamrtReports.xls";
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String pathx = request.getRealPath("/");
	FileOutputStream fileOut = new FileOutputStream(pathx+fileName);
	wb.write(fileOut);
	FileInputStream fileIn = new FileInputStream(pathx+fileName);
	byte b[] = new byte[fileIn.available()];
	fileIn.read(b);
	response.setHeader("Content-Dispositio- n", "application/msexcel");
	response.setContentType("application/msexcel");
	response.setHeader("content-disposition","attachment;name=\""+fileName+"\";filename=\""+fileName+"\"");
	response.getOutputStream().write(b);
	response.getOutputStream().flush();
	fileOut.close();  
}
catch ( Exception ex )
{  
	ex.printStackTrace();
}     
%>	