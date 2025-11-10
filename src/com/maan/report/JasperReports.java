package com.maan.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.logging.log4j.Logger;

import com.maan.DBCon.DBConnection;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.opensymphony.xwork2.ActionContext;

public class JasperReports {
	private static final Logger logger = LogUtil.getLogger(JasperReports.class);
	/*final HttpServletRequest request=ServletActionContext.getRequest();
	final HttpServletResponse response=ServletActionContext.getResponse();
	final transient private String imageURL = request.getSession().getServletContext().getRealPath("/images/");
	final transient private String contextPath = request.getSession().getServletContext().getRealPath("/");
	Map<String, Object> session=ActionContext.getContext().getSession();*/

	private final String applicationPath = CommonService.getApplicationPath();
	private final String imageURL = applicationPath + "/images/";
	//private final String jasperBasePath = applicationPath + "/Jasper/";
	
	private void init() {
		try {
			Map<String, Object> session=ActionContext.getContext().getSession();
			session.remove("pdfFilePath");
		}
		catch(Exception exception) {
			logger.debug("Exception @ JasperReports init() { " + exception);
		}
	}
	
	public void export(String startDate,String endDate, String loginId ,String jasperPath,OutputStream os,String fileName,String downloadType,String creditNoteStatus){
		init();
		try{
			Map<String, Object> session=ActionContext.getContext().getSession();					
			String actualBranch=(String)session.get("adminBranch");
			String productId=(String)session.get("product_id");		
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);

			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		
			jasperParameter.put("startDate", startDate); 
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("loginid", loginId); 
			jasperParameter.put("product", productId);	
			jasperParameter.put("branch", actualBranch); 
			jasperParameter.put("opencover", "ALL");
			jasperParameter.put("imagePath", imageURL);
			if(StringUtils.isNotBlank(creditNoteStatus))
				jasperParameter.put("creditStatus", creditNoteStatus);		

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			logger.debug(e);
			e.printStackTrace();
		}

		logger.info("Filling report finished.........");	
	}

	public void opencoverSchedule(String proposalNo, String openCoverNo, String endtstatus, String branchcode, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();

			jasperParameter.put("proposalNo",proposalNo);
			jasperParameter.put("openCoverNo",openCoverNo);
			jasperParameter.put("endtStatus",endtstatus);
			jasperParameter.put("branchCode", branchcode);	
			jasperParameter.put("imagePath", imageURL);
			
			String jasperPath = applicationPath + "/Jasper/CargoOpenCover.jasper";
			
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		}
		catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}

	public void certificateSchedule(String applicationNo, String branchCode, String belongingBranch, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("Application_No",applicationNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("belongingBranchCode", belongingBranch);
			jasperParameter.put("imagePath", imageURL);
			String jasperPath="";
			String jasperFileName = "";
			String productId = "";
			
			productId = new com.maan.common.dao.CommonDAO().getProductIdByAppNo(applicationNo);
			if("3".equals(productId)) {
				jasperFileName = "CargoCertificate.jasper";
			}
			else if("11".equals(productId)) {
				jasperFileName = "OpenCoverCertificate.jasper";
			}
			jasperPath = applicationPath + "/Jasper/"+jasperFileName;

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,URLDecoder.decode(filePath, "UTF-8"));
		} 
		catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}
	
	public void certificatePrint(String applicationNo, String branchCode, String belongingBranch, String filePath, String productId) {
		init();
		if("11".equals(productId)) {
			certificateSchedule(applicationNo, branchCode, belongingBranch, filePath);
		}
		else {
			try{
				final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
				jasperLogger.setLevel(Level.ERROR);
				HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
				jasperParameter.put("applicationNo",applicationNo);
				jasperParameter.put("branchCode", branchCode);
				jasperParameter.put("belongingBranch", belongingBranch);
				jasperParameter.put("imagePath", imageURL);
				jasperParameter.put("brokerImagePath", applicationPath);
				String jasperPath="";
				jasperPath = applicationPath + "/Jasper/CargoPrint.jasper";
	
				JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
				JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
			} 
			catch (Exception e1) {
				logger.info("Exception @ Jasper Reports : "+e1);
				e1.printStackTrace();
			}
		}
	}

	public void certificateEndt(String policyNo, String applicationNo, String branchCode, String belongingBranch, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("policyNo",policyNo);
			jasperParameter.put("applicationNo",applicationNo);
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("belongingBranch", belongingBranch);
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/CargoEndtCertificate.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}
	public void debitNote(String PolicyNo, String branchCode, String filePath){
		Map<String, Object> session=ActionContext.getContext().getSession();
		String vatTax=(String) session.get("VATINVOICE");
		if(!"VATINVOICE".equalsIgnoreCase(vatTax)){
		debitNoteNew(PolicyNo, branchCode, filePath);
		}else{
			debitNoteNew(PolicyNo, branchCode, filePath);
		}
		session.remove("VATINVOICE");
	}
	
	public void creditNote(String PolicyNo, String branchCode, String filePath) {
		Map<String, Object> session=ActionContext.getContext().getSession();
		String vatTax=(String) session.get("VATINVOICE");
		if(!"VATINVOICE".equalsIgnoreCase(vatTax)){
			creditNoteNew(PolicyNo, branchCode, filePath);
		}else{
			creditNoteNew(PolicyNo, branchCode, filePath);
		}
		session.remove("VATINVOICE");
		
	}

	public void debitNoteNew(String PolicyNo, String branchCode, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("policyNo",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("pvType", "DR");
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/TAXINVOICE.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
			
		} catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}

	public void creditNoteNew(String PolicyNo, String branchCode, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			
			jasperParameter.put("policyNo",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			jasperParameter.put("pvType", "CR");
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/TAXINVOICE.jasper";
			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
			
		} catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}
	
	public void debitNoteOld(String PolicyNo, String branchCode, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			/*String applicationNo = finalBean.getApplicationNo(PolicyNo);
			if(StringUtils.isBlank(applicationNo)){
				applicationNo = finalBean.getApplicationNo1(PolicyNo);
			}*/
			jasperParameter.put("policyNumber",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/CargoDebit.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}

	public void creditNoteOld(String PolicyNo, String branchCode, String filePath) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("policyNumber",PolicyNo);
			jasperParameter.put("branchCode", branchCode);	
			jasperParameter.put("imagePath", imageURL);
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/CargoCredit.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			logger.info("Exception @ Jasper Reports : "+e1);
			e1.printStackTrace();
		}
	}
	
	public void LCSmartReport(String loginId,String branchCode,String type,String value,String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = applicationPath + "/Jasper/LCSmartReport.jasper";
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("loginId", loginId); 
			jasperParameter.put("branchCode", branchCode);
			jasperParameter.put("type", type); 
			jasperParameter.put("value", value);	

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);
		}
		catch(Exception e){
			logger.info("Exception @ Jasper Reports : "+e);
			logger.debug(e);
			e.printStackTrace();
		}

		logger.info("Filling report finished.........");
	}
	
	public void openCoverReport(String startDate, String endDate, String loginId, String branchCode, String downloadType, OutputStream os) {
		init();
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			String jasperPath = applicationPath + "/Jasper/OpenCoverReport.jasper";
			HashMap<String,Object> jasperParameter = new HashMap<String,Object>();    		

			jasperParameter.put("startDate", startDate); 
			jasperParameter.put("endDate", endDate);
			jasperParameter.put("loginId", loginId); 
			jasperParameter.put("branchCode", branchCode);	

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			exporter(downloadType, jasperPrint, os);

		}
		catch(Exception e){
			logger.debug(e);
			e.printStackTrace();
		}

		logger.info("Filling report finished.........");
	}
	
	public void branchReports(String startDate,String endDate,String status,String productId,String branchCode, String filePath) {
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);	
			jasperParameter.put("status", status);
			jasperParameter.put("productId", productId);
			jasperParameter.put("branchCode", branchCode);
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/BranchReports.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			JasperExportManager.exportReportToPdfFile(jasperPrint,filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void dayEndBranchReports(String startDate,String endDate,String status,String productId,String branchCode, String filePath) {
		try{
			final org.apache.log4j.Logger jasperLogger = org.apache.log4j.Logger.getLogger("net.sf.jasperreports");	
			jasperLogger.setLevel(Level.ERROR);
			HashMap<String, Object> jasperParameter = new HashMap<String, Object>();
			jasperParameter.put("startDate", startDate);
			jasperParameter.put("endDate", endDate);	
			jasperParameter.put("status", status);
			jasperParameter.put("branchCode", branchCode);
			String jasperPath="";
			jasperPath = applicationPath + "/Jasper/DayEndBranchReports.jasper";

			JasperPrint jasperPrint = fillReport(jasperPath,jasperParameter);
			
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			exporter("excel",jasperPrint,baos);
			StreamToFile(filePath, baos);
			logger.info("Filling report finished.........");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	private void compileReportToFile(String jasperPath) throws Exception {
		File jasperFile = new File(jasperPath);
		if(!jasperFile.exists()){
			String path=jasperFile.getAbsolutePath().replace(".jasper", ".jrxml");
			String temp = JasperCompileManager.compileReportToFile(path);
			logger.info("Compiled File "+temp);
		}
	}
	private JasperPrint fillReport(String jasperPath, Map<String,Object> jasperParameter) throws Exception {
		compileReportToFile(jasperPath);
		Connection connection=null;
		try {
			logger.info("JASPER Connection is started .........");
			 connection=DBConnection.getInstance().getDBConnection();
			logger.info("Filling report started.........");
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath,jasperParameter, connection);
			logger.info("Filling report ended.........");
			return jasperPrint;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(connection!=null) {
				connection.close();
				logger.info("JASPER Connection is Closed.........");
			}
		}
		return null;
	}
	private void exporter(String downloadType, JasperPrint jasperPrint, OutputStream os) throws Exception {
		if("excel".equals(downloadType))
		{
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
			exporter.exportReport();
		}			
		else if("pdf".equals(downloadType))
		{
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);					
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);							
			exporter.exportReport();				
		}			
		else
		{

			JRHtmlExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM,os);	

			exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND, false);
			exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, true);
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
			exporter.setParameter(JRHtmlExporterParameter.ZOOM_RATIO, 1.3F);
			exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
			exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,true);	
			exporter.exportReport();			
		}
	}
	private void StreamToFile(String filePath, ByteArrayOutputStream baos) throws IOException {
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream (filePath);
		    baos.writeTo(fos);
		} catch(IOException ioe) {
		    // Handle exception here
		    ioe.printStackTrace();
		} finally {
		    fos.close();
		}
	}


}
