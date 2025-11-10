package com.maan.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import com.maan.Mail.controller.MailControllerNew;
import com.maan.adminnew.common.CommonService;
import com.maan.common.LogUtil;
import com.maan.report.JasperReports;

public class BranchReportService {
	private static final Logger logger = LogUtil.getLogger(BranchReportService.class);
	
	public void BranchReportMail() {
		try {
			final MailControllerNew mail = new MailControllerNew();
			BranchReportDAO dao = new BranchReportDAO();
			JasperReports jr = new JasperReports();
			
			final String basePath=CommonService.getApplicationPath();
			final String basePaths = basePath + "MailServerInfo/MailServerInfo.xml";
			final String downloadPath = basePath + "downloadFiles/branchReports/";
			
			logger.info("basePath==>" + basePath);
			logger.info("basePaths==>" + basePaths);
			logger.info("downloadPath==>" + downloadPath);
			
			final Calendar cal = Calendar.getInstance();
			final SimpleDateFormat oracleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			final String sysDate = oracleDateFormat.format(cal.getTime());
			
			List<Map<String,Object>> branchDetailsList = dao.getBranchDetailsList();
			 
			for(int i=0; i< branchDetailsList.size() ; i++) {
				try {
					Map<String,Object> branchDeatailsMap = branchDetailsList.get(i);
					String branchCode = branchDeatailsMap.get("BRANCH_CODE")==null?"":branchDeatailsMap.get("BRANCH_CODE").toString();
					final String branchName = branchDeatailsMap.get("BRANCH_NAME")==null?"":branchDeatailsMap.get("BRANCH_NAME").toString();
					
					int branchReportCount = dao.getBranchReportCount(sysDate,sysDate,"P","ALL",branchCode);
					if(branchReportCount>0) {
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy h.mm.ss a");
						String date = sdf.format(cal.getTime());
						
						String filePath = downloadPath + branchName + date + ".xls";
						
						jr.dayEndBranchReports(sysDate,sysDate,"P","ALL",branchCode, filePath);

						mail.BranchReportMail(filePath, branchName, branchCode);
					}
				}
				catch(Exception exception) {
					exception.printStackTrace();
					logger.debug(exception);
				}
			}
		}
		catch(Exception exception) {
			exception.printStackTrace();
			logger.debug(exception);
		}
	}
}
