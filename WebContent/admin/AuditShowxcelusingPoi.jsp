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



<jsp:useBean id= "admin" class = "com.maan.admin.DAO.AdminBean">
<jsp:setProperty name= "admin"   property = "*"/>
</jsp:useBean>

<%

    String  adminBranch = (String) session.getAttribute("adminBranch") ==null ? "":(String) session.getAttribute("adminBranch");
    
   
    
	String adminBra="";
	adminBra = adminBranch.replaceAll("'","");
	
	/** For Branch address **/
	String braAddress = "";
	String braPO = "";
	String braCity = "";
	String braCountry = "";
	String braPhone = "";
	String placeCode[][] = new String[0][0];
	placeCode = admin.getBranchDetails(adminBra);
	if(placeCode.length>0)
	{
		braAddress = placeCode[0][8] == null ? "" : placeCode[0][8];
		braPO = placeCode[0][10] == null ? "" : placeCode[0][10];
		braCity = placeCode[0][11] == null ? "" : placeCode[0][11];
		braCountry = placeCode[0][12] == null ? "" : placeCode[0][12];
		braPhone = placeCode[0][13] == null ? "" : placeCode[0][13];
	}
	/** For Branch address **/
	HashMap brokDetails = new HashMap();
	String currencyType = "";
	String decimalPlace="";
	String tax="";
	brokDetails = (HashMap) session.getAttribute("BrokerDetails");
	if(brokDetails.size() >0)
	{
		currencyType = (String) brokDetails.get("CurrencyName");
		decimalPlace =(String) brokDetails.get("CurrencyDecimal");
		decimalPlace = decimalPlace == null ? "2" :decimalPlace;
		tax =(String) brokDetails.get("Tax");
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

		String eDate = request.getParameter("eDate")!=null?request.getParameter("eDate"):"";

		String data1 = request.getParameter("data1")!=null?request.getParameter("data1"):"";
		String data2 = request.getParameter("data2")!=null?request.getParameter("data2"):"";
		
		String rep = request.getParameter("rep")!=null?request.getParameter("rep"):"";
		String productId = request.getParameter("productId")!=null?request.getParameter("productId"):"";
		String broker_codes="";
		broker_codes = (String)session.getAttribute("broker_codes");
		broker_codes = broker_codes == null ? "" : broker_codes;
		String freightStatus = request.getParameter("freightStatus")!=null?request.getParameter("freightStatus"):"";
		String unApp[][] = new String[0][0];
		String loginProIds = "";
		loginProIds = (String)session.getAttribute("loginProIds");
		loginProIds = loginProIds == null ? "": loginProIds;

		if(productId.equalsIgnoreCase("All"))
			productId = loginProIds;
		if(data1.length()>0&&data2.length()>0)
		{
			eDate = "between "+data1+" and "+data2;
			unApp=admin.getPortFolioForAudit(data1,data2,rep,productId,adminBranch,broker_codes,freightStatus);
		}
		

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
	   style1.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	   font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
  	   style6.setFont(font);
	   style1.setFont(font);
	   style1.setAlignment(HSSFCellStyle.ALIGN_CENTER); 
	   HSSFCell cell1,cell2,cell3,cell4,cell5,totalcell, totalcell1, datecell1, datecell2,datecell3,datecell4,cell6;
	   cell1 = row.createCell((short)3);
	   cell1.setCellValue(braAddress);
	   cell1.setCellStyle(style); 
	   cell2 = row1.createCell((short)3);
	   cell2.setCellValue(braPO);
	   cell2.setCellStyle(style);
	   cell3 = row2.createCell((short)3);
	   cell3.setCellValue(braCity+" - "+braCountry);
	   cell3.setCellStyle(style);
	   cell4 = row3.createCell((short)3);
	   cell4.setCellValue("Tel: "+braPhone);
	   cell4.setCellStyle(style);
	   cell5 = row4.createCell((short)3);
	   cell5.setCellValue("online.Emirates.ae");
	   cell5.setCellStyle(style); 
	   cell6 = row5.createCell((short)3);
	   cell6.setCellValue("Commodity Audit Reports");
	   cell6.setCellStyle(style7); 
	   sheet.addMergedRegion(new Region(0,(short)3,0,(short)6));
	   sheet.addMergedRegion(new Region(1,(short)3,1,(short)6));
	   sheet.addMergedRegion(new Region(2,(short)3,2,(short)6));
	   sheet.addMergedRegion(new Region(3,(short)3,3,(short)6));
       sheet.addMergedRegion(new Region(4,(short)3,4,(short)6));
	   sheet.addMergedRegion(new Region(5,(short)3,5,(short)6));
	   //sheet.addMergedRegion(new Region(6,(short)3,6,(short)6));
   String [] ColHeader1 =new String[0];
	ColHeader1 = new String[]{"S.No","Broker Organization","Quote Created By","Policy No","Customer Name","Commodity Name","Invoice Description","Open Cover Description","Agreed Status","Agreed Date"};
		
		
		HSSFCell cell[][] = new HSSFCell[unApp.length][ColHeader1.length]; 

	  	for(n=0;n<ColHeader1.length;n++)
		{
			try
			{
					sheet.setColumnWidth((short)0,(short)(256*5));
					sheet.setColumnWidth((short)1,(short)(256*25));
					sheet.setColumnWidth((short)2,(short)(256*25));
					sheet.setColumnWidth((short)3,(short)(256*10));
					sheet.setColumnWidth((short)4,(short)(256*25));
					sheet.autoSizeColumn((short)5);
					sheet.autoSizeColumn((short)6);
					sheet.setColumnWidth((short)7,(short)(256*25));
					sheet.setColumnWidth((short)8,(short)(256*25));
					sheet.setColumnWidth((short)9,(short)(256*25));
					//sheet.autoSizeColumn((short)n);
					cell[0][n] = row8.createCell((short)n);
					cell[0][n].setCellValue(ColHeader1[n].trim());
					cell[0][n].setCellStyle(style1);
			}
			catch(Exception e)
			{
					System.out.println("Error"+e);e.printStackTrace();
			}
		}
			   
	  

	   datecell1 = row7.createCell((short)4);
	   datecell1.setCellValue("Quotes Generated Date");
	   datecell1.setCellStyle(style);   
       datecell1 = row7.createCell((short)5);
	   datecell1.setCellValue(eDate);
	   datecell1.setCellStyle(style); 


       
	   int sno=0;
	   int k=9;
	    String login = "";
	    HashMap quotePersons = new HashMap();
		for(int i=0;i<unApp.length;i++)
		{
			login = login+"'"+(unApp[i][1]!=null?unApp[i][1]:"")+"',";
		}
		if(login.length()>0)
			login = login.substring(0,(login.length()-1));

		quotePersons = admin.getTotalQuotedPersons(login,adminBranch);

		for(int m=0;m<unApp.length;m++,k++)
		{
			String user="";
			String broker123="";
			String[][] QuotedUser = new String[0][0];
			//Broker and User Name
			if(unApp[m][14].equalsIgnoreCase("1"))
				QuotedUser = admin.getQuotedPerson(unApp[m][1],adminBranch);
			else
				QuotedUser = admin.getQuotedPerson(unApp[m][14],adminBranch);
			
			String openCoverDescription = "";
			if(unApp[m][11].equalsIgnoreCase("11"))
				openCoverDescription = admin.getOpenCoverDescription(unApp[m][2],adminBranch);
			else
				openCoverDescription = "";

			String[][] BCompanyName = new String[0][0];
			if(quotePersons.get("brokers"+unApp[m][1])!=null)
				BCompanyName = (String[][])quotePersons.get("brokers"+unApp[m][1]);
			if(BCompanyName.length<=0)
				BCompanyName = admin.getBrokerNameByUserId123(unApp[m][1],adminBranch);
			if(BCompanyName.length>0)
			{
				broker123=BCompanyName[0][1]==null?"":BCompanyName[0][1];
			}
			if(QuotedUser.length > 0)
			{	
				if(QuotedUser[0][0].equals("2"))
				{
					user=broker123;
				}
				else
				{
					user=QuotedUser[0][1]==null?"":QuotedUser[0][1];
				}
			}	
			//Customer Name 
			String str="";
			if(unApp[m][7]==null||unApp[m][7].equals("")||unApp[m][7].equalsIgnoreCase("null"))
			{
			  str=(unApp[m][4]==null?"":unApp[m][4])+" "+(unApp[m][8]==null?"":unApp[m][8]);
			}
			else
			{
			   str=unApp[m][7];
			}
			//Status
			
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
				  cell[m][n].setCellValue(broker123);
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==2)
				{
				  cell[m][n].setCellValue(user);
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==3)
				{
				  cell[m][n].setCellValue((unApp[m][9]==null?"":unApp[m][9]));
				   cell[m][n].setCellStyle(style3);  
				}
				if(n==4)
				{
				  cell[m][n].setCellValue(str);
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==5)
				{
				  cell[m][n].setCellValue((unApp[m][17]!=null?unApp[m][17]:""));
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==6)
				{
				  cell[m][n].setCellValue((unApp[m][18]!=null?unApp[m][18]:""));
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==7)
				{
				  cell[m][n].setCellValue(openCoverDescription);
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==8)
				{
				  cell[m][n].setCellValue((unApp[m][19]==null?"Nil":unApp[m][19]));
				   cell[m][n].setCellStyle(style5);  
				}
				if(n==9)
				{
				  cell[m][n].setCellValue((unApp[m][19]==null?"Nil":unApp[m][20]));
				   cell[m][n].setCellStyle(style5);  
				}
			}
		}
		String fileName = "AuditReports_"+eDate+".xls";
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