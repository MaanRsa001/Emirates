package com.maan.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;

import com.maan.common.LogUtil;
import com.maan.common.StringHelper;
import com.maan.common.UploadValidation;
import com.maan.quotation.service.QuotationService;
import com.maan.upload.service.UploadService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {
	private static final Logger logger = LogUtil.getLogger(UploadAction.class);
	private static final long serialVersionUID = 1L;
	Map session = ActionContext.getContext().getSession();
	HttpServletRequest request= ServletActionContext.getRequest();
	QuotationService qservice=new QuotationService();
	UploadService service=new UploadService();
	UploadValidation validate = new UploadValidation();
	ServletContext context=request.getSession().getServletContext();
	private static final String DROPDOWN = "dropdown";
	private String FILE_PATH=context.getRealPath(getText("EXCEL_UPLOAD_PATH"));
	private String CSV_PATH=context.getRealPath(getText("CSV_UPLOAD_PATH"));
	private String CSV_CONVERTER_PATH=context.getRealPath(getText("CSV_CONVERTER_PATH"));
	private String branchCode=(String)session.get("LoginBranchCode");
	private static final String FIELD = "ELEMENT_NAME";
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private String display;
	private String productId;
	private String error;
	private String tranId;
	private String partner;
	private String campaign;
	private String openCoverNo;
	private String brokerCode;
	private String issuer=StringUtils.defaultIfEmpty((String)session.get("RSAISSUER"), "");
	private String typeId;
	private List<Object> reuploadList;
	private String refNo;
	private String orderNo;
	private String interest;
	private String lcNo;
	private String conveyance;
	private String cover;
	private String vesselName;
	private String sailingDate;
	private String voyageFrom;
	private String packingDetail;
	private String voyageTo;
	private String invoiceValue;
	private String incoTerms;
	private String currency;
	private String roe;
	private String basisValue;
	private String pkgDesc;
	private String customerName;
	private String settlingAgent;
	private String commodityDesc;
	private String rowNum;
	private String saleTermPercent;
	private String option;
	private String sno;
	private String errorValid;
	private String warYN;
	private List<String> usno;
	private List<String> uinterest;
	private List<String> uconveyance;
	private List<String> ucover;
	private List<String> uvoyageFrom;
	private List<String> uvoyageTo;
	private List<String> ucurrency;
	private List<String> uincoTerms;
	private List<String> usaleTermPercent;
	private List<String> utolerance;
	private List<String> upkgDesc;
	private List<String> ucommodityDesc;
	private List<Object> otherInfo;
	private List<String> uwar;
	
	 private static final String OUTPUT_DATE_FORMAT= "dd-MMM-yyyy";
	 private static final String CVS_SEPERATOR_CHAR="\t";
	 private static final String NEW_LINE_CHARACTER="\r\n";
	
	Object[] getParams(){
	Object[] objects=new String[]{option,(String) session.get("product_id"),branchCode,session.get("openCoverNo").toString(),conveyance,cover,voyageFrom,
			voyageTo,incoTerms,saleTermPercent,"",(String)session.get("RSAISSUER"),(String) session.get("brokerCode")};
		return objects;
	}
	public String getInterest() {
		return interest;
	}
	public List<String> getUinterest() {
		return uinterest;
	}
	public void setUinterest(List<String> uinterest) {
		this.uinterest = uinterest;
	}
	public List<String> getUsno() {
		return usno;
	}
	public void setUsno(List<String> usno) {
		this.usno = usno;
	}
	public List<Object> getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(List<Object> otherInfo) {
		this.otherInfo = otherInfo;
	}
	public List<String> getUconveyance() {
		return uconveyance;
	}
	public void setUconveyance(List<String> uconveyance) {
		this.uconveyance = uconveyance;
	}
	public String getWarYN() {
		return warYN;
	}
	public void setWarYN(String warYN) {
		this.warYN = warYN;
	}
	public List<String> getUwar() {
		return uwar;
	}
	public void setUwar(List<String> uwar) {
		this.uwar = uwar;
	}
	public List<String> getUcover() {
		return ucover;
	}
	public void setUcover(List<String> ucover) {
		this.ucover = ucover;
	}
	public List<String> getUvoyageFrom() {
		return uvoyageFrom;
	}
	public void setUvoyageFrom(List<String> uvoyageFrom) {
		this.uvoyageFrom = uvoyageFrom;
	}
	public List<String> getUvoyageTo() {
		return uvoyageTo;
	}
	public void setUvoyageTo(List<String> uvoyageTo) {
		this.uvoyageTo = uvoyageTo;
	}
	public List<String> getUcurrency() {
		return ucurrency;
	}
	public void setUcurrency(List<String> ucurrency) {
		this.ucurrency = ucurrency;
	}
	public List<String> getUincoTerms() {
		return uincoTerms;
	}
	public void setUincoTerms(List<String> uincoTerms) {
		this.uincoTerms = uincoTerms;
	}
	public List<String> getUsaleTermPercent() {
		return usaleTermPercent;
	}
	public void setUsaleTermPercent(List<String> usaleTermPercent) {
		this.usaleTermPercent = usaleTermPercent;
	}
	public List<String> getUtolerance() {
		return utolerance;
	}
	public void setUtolerance(List<String> utolerance) {
		this.utolerance = utolerance;
	}
	public List<String> getUpkgDesc() {
		return upkgDesc;
	}
	public void setUpkgDesc(List<String> upkgDesc) {
		this.upkgDesc = upkgDesc;
	}
	public List<String> getUcommodityDesc() {
		return ucommodityDesc;
	}
	public void setUcommodityDesc(List<String> ucommodityDesc) {
		this.ucommodityDesc = ucommodityDesc;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSaleTermPercent() {
		return saleTermPercent;
	}
	public void setSaleTermPercent(String saleTermPercent) {
		this.saleTermPercent = saleTermPercent;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getErrorValid() {
		return errorValid;
	}
	public void setErrorValid(String errorValid) {
		this.errorValid = errorValid;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBasisValue() {
		return basisValue;
	}
	public void setBasisValue(String basisValue) {
		this.basisValue = basisValue;
	}
	public String getCommodityDesc() {
		return commodityDesc;
	}
	public void setCommodityDesc(String commodityDesc) {
		this.commodityDesc = commodityDesc;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getRefNo() {
		return refNo;
	}
	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getLcNo() {
		return lcNo;
	}
	public void setLcNo(String lcNo) {
		this.lcNo = lcNo;
	}
	public String getConveyance() {
		return conveyance;
	}
	public void setConveyance(String conveyance) {
		this.conveyance = conveyance;
	}
	public String getVesselName() {
		return vesselName;
	}
	public void setVesselName(String vesselName) {
		this.vesselName = vesselName;
	}
	public String getSailingDate() {
		return sailingDate;
	}
	public void setSailingDate(String sailingDate) {
		this.sailingDate = sailingDate;
	}
	public String getVoyageFrom() {
		return voyageFrom;
	}
	public void setVoyageFrom(String voyageFrom) {
		this.voyageFrom = voyageFrom;
	}
	public String getPackingDetail() {
		return packingDetail;
	}
	public void setPackingDetail(String packingDetail) {
		this.packingDetail = packingDetail;
	}
	public String getVoyageTo() {
		return voyageTo;
	}
	public void setVoyageTo(String voyageTo) {
		this.voyageTo = voyageTo;
	}
	public String getInvoiceValue() {
		return invoiceValue;
	}
	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
	public String getIncoTerms() {
		return incoTerms;
	}
	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
	}
	public String getRoe() {
		return roe;
	}
	public void setRoe(String roe) {
		this.roe = roe;
	}
	public String getPkgDesc() {
		return pkgDesc;
	}
	public void setPkgDesc(String pkgDesc) {
		this.pkgDesc = pkgDesc;
	}
	public String getSettlingAgent() {
		return settlingAgent;
	}
	public void setSettlingAgent(String settlingAgent) {
		this.settlingAgent = settlingAgent;
	}
	public List<Object> getReuploadList() {
		return reuploadList;
	}
	public void setReuploadList(List<Object> reuploadList) {
		this.reuploadList = reuploadList;
	}
	public String getOpenCoverNo() {
		return openCoverNo;
	}
	public void setOpenCoverNo(String openCoverNo) {
		this.openCoverNo = openCoverNo;
	}
	public String getBrokerCode() {
		return brokerCode;
	}
	public void setBrokerCode(String brokerCode) {
		this.brokerCode = brokerCode;
	}
	public String getTranId() {
		return tranId;
	}
	public void setTranId(String tranId) {
		this.tranId = tranId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public List<Map<String, Object>> getCoverList(){
		return qservice.getOptionsList("cover", getParams());
	}
	public List<Map<String, Object>> getOriginList(){
		return qservice.getOptionsList("originCountry", getParams());
	}
	public List<Map<String, Object>> getDestList(){
		return qservice.getOptionsList("destCountry", getParams());
	}
	public List<Map<String, Object>> getModeList(){
		return qservice.getOptionsList("mode", getParams());
	}
	public List<Map<String, Object>> getCurrencyList(){
		return qservice.getOptionsList("currency", getParams());
	}
	public List<Map<String, Object>> getSaleTermList(){
		return qservice.getOptionsList("saleTerm", getParams());
	}
	public List<Map<String, Object>> getPackageList(){
		return qservice.getOptionsList("package", getParams());
	}
	public List<Map<String, Object>> getPercentList(){
		return qservice.getOptionsList("saleTermPercent", getParams());
	}
	public List<Map<String, Object>> getToleranceList(){
		return qservice.getOptionsList("tolerance", getParams());
	}

	
	public String redirect(){
		System.out.println("Session login ID::"+session.get("user"));
		session.put("brokerId", service.getBrokerId(session.get("user").toString()));
		session.put("customerId", service.getcustomerId(session.get("openCoverNo").toString()));
		
		return SUCCESS; 
	}
	
	public String upload(){
		//logger.info("upload() - Enter || param2: "+param2);
		logger.info("Upload Enters");
		try {
			Map resultMap=null;
			validateFileds();
		 	
			String movementId="1";
			productId = "03";
			partner = "1004";
			campaign = "2";
			brokerCode = session.get("brokerId").toString();
			openCoverNo = session.get("openCoverNo").toString();
			String loginId = (String)session.get("user");
			List<Map> excelMovement=service.getExcelMovement(productId,partner,campaign);
			List<Map> masterMoves =service.getMovements(productId,partner,campaign);
			List result = null;
			String movementDetailId =excelMovement.get(0).get("MOVEMENT_DETAIL_ID").toString();
			if(getActionErrors().size()<=0)
			{
				uploadFileName=StringHelper.getFileNameFormat(uploadFileName, "");
				System.out.println("uploadFileName:"+uploadFileName);
	            File fileToCreate = new File(FILE_PATH, this.uploadFileName);
	            FileUtils.copyFile(this.upload, fileToCreate);
				//boolean status=FileHandler.convertExcelToCSV(CSV_CONVERTER_PATH, FILE_PATH+"/"+uploadFileName, CSV_PATH+"/"+uploadFileName);
	            excelToCSV(FILE_PATH+"/"+uploadFileName, CSV_PATH+"/"+uploadFileName);
	            System.out.println("csvFIle Path===>"+CSV_PATH+"/"+uploadFileName);
				File csvFile=new File(CSV_PATH+"/"+uploadFileName);
	           
	          if(csvFile.exists() && csvFile.canRead()) { 
					resultMap=service.insertRecords(productId, csvFile,  movementDetailId,campaign,partner,tranId);
					tranId =(String)(resultMap.get("TRANID")==null?"":resultMap.get("TRANID"));
					service.getTransactionId(loginId, uploadFileName, FILE_PATH, movementDetailId,tranId,openCoverNo,"",issuer);
					service.getResult(tranId,movementDetailId);
					error=(String)resultMap.get("ERROR");
					
					if(error.equalsIgnoreCase("") ){
						service.archiveRecords( movementDetailId,tranId,(String)session.get("user"));
						 error = validate.validateRecords(tranId,movementId, movementDetailId,  productId,campaign, partner,openCoverNo,brokerCode,loginId);
							
						 System.out.println("Movements SIZE::"+masterMoves.size());
						if(masterMoves.size()>0){
							for(int i=0;i<masterMoves.size();i++){
								System.out.println("MOVEMENT DETAIL ID:::"+masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString());
								//validate.validateRecords(tranId,movementId,movementDetailId);
								service.moveMasterRecords(masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString(),tranId,brokerCode,loginId,openCoverNo);
								if(masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString().trim().equalsIgnoreCase("6")){
								service.updateOpenCoverDetail(brokerCode,openCoverNo,tranId);
								}
								
							}
							service.removeTempRecords( masterMoves.get(0).get("MOVEMENT_DETAIL_ID").toString(),tranId);
						}
							
					}
					//error=(String)resultMap.get("ERROR");
					result = service.getResult2(tranId,movementDetailId);
					System.out.println("No error");
					System.out.println("Enters Quote Request");
					service.generateQuotations(tranId);
					
				}
				
				else
					error=getText("error.upload.conversion");
				
	            if(StringUtils.isNotEmpty(error)){
	            	addActionError(error);
	            	
	            }
	            else{
	            	request.setAttribute("UPLOAD_RESULT", result);
	            	display="result";
	            }
			}
		} catch (Exception e){
			addActionError(e.getMessage());
			e.printStackTrace();
		}
		logger.info("upload() - Exit ");
		return SUCCESS;
	}
	
	 public static void excelToCSV(String excelFileName,String csvFileName) throws Exception{
         checkValidFile(excelFileName);
         HSSFWorkbook myWorkBook = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(excelFileName)));
         HSSFSheet mySheet = myWorkBook.getSheetAt(0);
         Iterator  rowIter =  mySheet.rowIterator();
         String csvData="";
         int count=0;
         int firstCount=0;
         while (rowIter.hasNext()) {
                 HSSFRow myRow = (HSSFRow) rowIter.next();
                 count++;
                 if(count==1){
                 	firstCount=myRow.getLastCellNum();
                 }for ( int i=0;i<firstCount;i++){
                       csvData += getCellData(myRow.getCell(i));
                 }
                 csvData=csvData.substring(0, csvData.length()-1);
                 csvData+=NEW_LINE_CHARACTER;
                 System.out.println(csvData);
         }
         writeCSV(csvFileName, csvData);
     }
	 private static void writeCSV(String csvFileName,String csvData) throws Exception{
	        FileOutputStream writer = new FileOutputStream(csvFileName);
	        writer.write(csvData.getBytes());
	        writer.close();
	 }
	 private static void checkValidFile(String fileName){
	        boolean valid=true;
	        try{
	            File f = new File(fileName);
	            if ( !f.exists() || f.isDirectory() ){
	                valid=false;
	            }
	        }catch(Exception e){
	            valid=false;
	        }
	        if ( !valid){
	            System.out.println("File doesn't exist: " + fileName);
	            System.exit(0);
	        }
	 }
	 private static String getCellData( HSSFCell myCell) throws Exception{
	        String cellData="";
	         if ( myCell== null){
	             cellData += CVS_SEPERATOR_CHAR;
	         }else{
	             switch(myCell.getCellType() ){
	                 case  HSSFCell.CELL_TYPE_STRING  :
	                 case  HSSFCell.CELL_TYPE_BOOLEAN  :
	                          cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
	                          break;
	                 case HSSFCell.CELL_TYPE_NUMERIC :
	                         cellData += getNumericValue(myCell);
	                         break;
	                 case  HSSFCell.CELL_TYPE_FORMULA :
	                         cellData +=  getFormulaValue(myCell);
	             default:
	                 cellData += CVS_SEPERATOR_CHAR;;
	             }
	       }
	    return cellData;
	 }
	 private static String getFormulaValue(HSSFCell myCell) throws Exception{
	        String cellData="";
	         if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_STRING  || myCell.getCellType () ==HSSFCell.CELL_TYPE_BOOLEAN) {
	             cellData +=  myCell.getRichStringCellValue ()+CVS_SEPERATOR_CHAR;
	         }else  if ( myCell.getCachedFormulaResultType() == HSSFCell.CELL_TYPE_NUMERIC ) {
	             cellData += getNumericValue(myCell)+CVS_SEPERATOR_CHAR;
	         }
	         return cellData;
	 }
	 private static String getNumericValue(HSSFCell myCell) throws Exception {
	        String cellData="";
	         if ( HSSFDateUtil.isCellDateFormatted(myCell) ){
	               cellData += new SimpleDateFormat(OUTPUT_DATE_FORMAT).format(myCell.getDateCellValue()) +CVS_SEPERATOR_CHAR;
	           }else{
	               cellData += new BigDecimal(myCell.getNumericCellValue()).toString()+CVS_SEPERATOR_CHAR ;
	           }
	        return cellData;
	}
	
	
	public void validateFileds(){
		if(upload==null)
			addActionError(getText("error.upload.file"));
		/*if(openCoverNo==null || openCoverNo.equalsIgnoreCase(""))
			addActionError(getText("error.upload.openCover"));
		if(brokerCode==null || brokerCode.equalsIgnoreCase(""))
			addActionError(getText("error.upload.brokerCode"));*/
		
	}
	
	public String error(){ 
		List result = null;
		result=service.getErrorList(sno, tranId);
		request.setAttribute("ErrorList", result);
		request.setAttribute("TranId", tranId);
		display="errorList";
		
		return SUCCESS;
	}
	
	public String download(){ 
		List result = null;
		result=service.getUploadedList(tranId);
		request.setAttribute("UploadedList", result);
		request.setAttribute("TranId", tranId);
		display="uploadList";
		
		return SUCCESS;
	}
	
	public String list(){ 
		List result = null;
		result=service.getTransactions(session.get("openCoverNo").toString(),session.get("RSAISSUER")==null?"":session.get("RSAISSUER").toString());
		request.setAttribute("TransactionList", result);
		request.setAttribute("TranId", tranId);
		display="TransactionList";
		return SUCCESS;
	}
	
	public String reupload(){
		try{
			reuploadList=service.getErrorList(sno, tranId);
			openCoverNo=session.get("openCoverNo").toString();
			customerName=session.get("customerId").toString();
			warYN=service.getWarYNOC(openCoverNo);
			if(reuploadList!=null && reuploadList.size()>0){
				List list=new ArrayList();
				//openCoverNo=((Map)reuploadList.get(0)).get("OPENCOVER_POLICY_NUMBER")==null?"":((Map)reuploadList.get(0)).get("OPENCOVER_POLICY_NUMBER").toString();
				for(int i=0; i<reuploadList.size(); i++){
					Map map=(Map)reuploadList.get(i);
					UploadAction up=new UploadAction();
					up.refNo=map.get("REF_NO")==null?"":map.get("REF_NO").toString();
					up.orderNo=map.get("ORDER_NO")==null?"":map.get("ORDER_NO").toString();
					up.interest=map.get("INTEREST")==null?"":map.get("INTEREST").toString();
					up.lcNo=map.get("LC_NO")==null?"":map.get("LC_NO").toString();
					up.conveyance=map.get("CONVEYANCE")==null?"":map.get("CONVEYANCE").toString();
					up.cover=map.get("COVER")==null?"":map.get("COVER").toString();
					up.vesselName=map.get("VESSEL_NAME")==null?"":map.get("VESSEL_NAME").toString();
					up.sailingDate=map.get("SAILING_DATE")==null?"":map.get("SAILING_DATE").toString();
					up.voyageFrom=map.get("VOYAGE_FROM")==null?"":map.get("VOYAGE_FROM").toString();
					up.packingDetail=map.get("PACKING_DETAILS")==null?"":map.get("PACKING_DETAILS").toString();
					up.voyageTo=map.get("VOYAGE_TO")==null?"":map.get("VOYAGE_TO").toString();
					up.invoiceValue=map.get("INVOICE_VALUE")==null?"":map.get("INVOICE_VALUE").toString();
					up.incoTerms=map.get("INCOTERMS")==null?"":map.get("INCOTERMS").toString();
					up.currency=map.get("CURRENCY")==null?"":map.get("CURRENCY").toString();
					up.roe=map.get("ROE")==null?"":map.get("ROE").toString();
					up.basisValue=map.get("BASIS_OF_VALUATION")==null?"":map.get("BASIS_OF_VALUATION").toString();
					up.pkgDesc=map.get("PACKAGE_DESCRIPTION")==null?"":map.get("PACKAGE_DESCRIPTION").toString();
					up.settlingAgent=map.get("SETTLING_AGENT")==null?"":map.get("SETTLING_AGENT").toString();
					up.commodityDesc=map.get("COMMODITY_DESCRIPTION")==null?"":map.get("COMMODITY_DESCRIPTION").toString();
					up.sno=map.get("SNO")==null?"":map.get("SNO").toString();
					up.warYN=map.get("WSRCC")==null?"":map.get("WSRCC").toString();
					up.errorValid=map.get("VALIDATION_ERROR")==null?"":"	Error at Line"+(i+1)+"	:	"+((",".equals(map.get("VALIDATION_ERROR").toString().charAt(0)+""))?map.get("VALIDATION_ERROR").toString().substring(1):map.get("VALIDATION_ERROR").toString());
					list.add(up);
				}
				reuploadList=list;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "reupload";
	}
	
	public String coverList(){
		logger.info("conveyance===>"+conveyance);
		request.setAttribute(FIELD, "coverUpload");
		return DROPDOWN;
	}
	public String packageList(){
		request.setAttribute(FIELD, "packageUpload");
		return DROPDOWN;
	}
	public String percentList(){
		request.setAttribute(FIELD, "saleTermPercentUpload"); 
		return DROPDOWN;
	}
	public String toleranceList(){
		request.setAttribute(FIELD, "toleranceUpload"); 
		return DROPDOWN;
	}
	
	public String commodityList(){
		return "commodityList";
	}
	public List getCommodityList(){
		return service.getCommodityList(openCoverNo);
	}
	public String otherInfo(){
		otherInfo=service.getErrorList(sno, tranId);
		openCoverNo=session.get("openCoverNo").toString();
		customerName=session.get("customerId").toString();
		if(otherInfo!=null && otherInfo.size()>0){
			Map map=(Map)otherInfo.get(0);
			refNo=map.get("REF_NO")==null?"":map.get("REF_NO").toString();
			orderNo=map.get("ORDER_NO")==null?"":map.get("ORDER_NO").toString();
			interest=map.get("INTEREST")==null?"":map.get("INTEREST").toString();
			lcNo=map.get("LC_NO")==null?"":map.get("LC_NO").toString();
			conveyance=map.get("CONVEYANCE")==null?"":map.get("CONVEYANCE").toString();
			cover=map.get("COVER")==null?"":map.get("COVER").toString();
			vesselName=map.get("VESSEL_NAME")==null?"":map.get("VESSEL_NAME").toString();
			sailingDate=map.get("SAILING_DATE")==null?"":map.get("SAILING_DATE").toString();
			voyageFrom=map.get("VOYAGE_FROM")==null?"":map.get("VOYAGE_FROM").toString();
			packingDetail=map.get("PACKING_DETAILS")==null?"":map.get("PACKING_DETAILS").toString();
			voyageTo=map.get("VOYAGE_TO")==null?"":map.get("VOYAGE_TO").toString();
			invoiceValue=map.get("INVOICE_VALUE")==null?"":map.get("INVOICE_VALUE").toString();
			incoTerms=map.get("INCOTERMS")==null?"":map.get("INCOTERMS").toString();
			currency=map.get("CURRENCY")==null?"":map.get("CURRENCY").toString();
			roe=map.get("ROE")==null?"":map.get("ROE").toString();
			basisValue=map.get("BASIS_OF_VALUATION")==null?"":map.get("BASIS_OF_VALUATION").toString();
			pkgDesc=map.get("PACKAGE_DESCRIPTION")==null?"":map.get("PACKAGE_DESCRIPTION").toString();
			settlingAgent=map.get("SETTLING_AGENT")==null?"":map.get("SETTLING_AGENT").toString();
			commodityDesc=map.get("COMMODITY_DESCRIPTION")==null?"":map.get("COMMODITY_DESCRIPTION").toString();
			sno=map.get("SNO")==null?"":map.get("SNO").toString();
			warYN=map.get("WSRCC")==null?"":map.get("WSRCC").toString();
		}
		return "otherInfo";
	}
	
	public String reuploadSave(){
		try{
			service.reuploadSave(this, branchCode);
			String movementId="1";
			productId = "03";
			partner = "1004";
			campaign = "2";
			brokerCode = session.get("brokerId").toString();
			openCoverNo = session.get("openCoverNo").toString();
			String loginId = (String)session.get("user");
			List<Map> excelMovement=service.getExcelMovement(productId,partner,campaign);
			List<Map> masterMoves =service.getMovements(productId,partner,campaign);
			List result = null;
			
			String movementDetailId =excelMovement.get(0).get("MOVEMENT_DETAIL_ID").toString();
			//service.getResult(tranId,movementDetailId);
			//service.archiveRecords( movementDetailId,tranId,(String)session.get("user"));
				error = validate.validateRecords(tranId,movementId, movementDetailId,  productId,campaign, partner,openCoverNo,brokerCode,loginId);
				System.out.println("Movements SIZE::"+masterMoves.size());
				if(masterMoves.size()>0){
					for(int i=0;i<masterMoves.size();i++){
						System.out.println("MOVEMENT DETAIL ID:::"+masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString());
						service.moveMasterRecords(masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString(),tranId,brokerCode,loginId,openCoverNo);
						if(masterMoves.get(i).get("MOVEMENT_DETAIL_ID").toString().trim().equalsIgnoreCase("6")){
							service.updateOpenCoverDetail(brokerCode,openCoverNo,tranId);
						}
					}
					service.removeTempRecords( masterMoves.get(0).get("MOVEMENT_DETAIL_ID").toString(),tranId);
				}
			result = service.getResult2(tranId,movementDetailId);
			System.out.println("No error");
			System.out.println("Enters Quote Request");
			service.generateQuotations(tranId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list();
	}
}
