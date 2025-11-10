package com.maan.common;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import jakarta.mail.MessagingException;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;

import com.maan.Mail.DAO.MailInformation;
import com.maan.Mail.controller.mailController;
import com.maan.opencover.bean.opencoverEntry;

public final class SendMail extends TimerTask {
	private static final Logger logger = LogUtil.getLogger(SendMail.class);
private Timer timer;
private final static long fONCE_PER_DAY = 1000*60*60*24;
private final static int fONE_DAY = 0;
private final static int HOUR = 8;
private final static int MINUTES = 0;

public static void sendMail() {
	TimerTask fetchMail  = new SendMail();
	Timer timer = new Timer();
	timer.scheduleAtFixedRate(fetchMail, getScheduledTime(), fONCE_PER_DAY);
}
/**

* Implements TimerTask's abstract run method.

*/
public void run(){
final MailInformation mailBean = new MailInformation();
String[][] branchInfo=new opencoverEntry().getBranchInfoByOpenCoverExpiryDate();
List<LinkedHashMap<String,Object>> openCovers=null;
String emailSubjectTxt="", emailMsgTxt="", fileName="OpenCoverPolicyList.xls";
File attachment=new File(fileName);
String path=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
String basePath=path.substring(1, path.indexOf("WEB-INF"));
final String basePaths = basePath+"MailServerInfo/MailServerInfo.xml"; 
String testMsg=basePath.indexOf("test")!=-1?" - FROM TEST ENVIRONMENT":"";
if(branchInfo!=null && branchInfo.length>0){
	for (int i = 0; i < branchInfo.length; i++) {
		openCovers=new opencoverEntry().getOpenCoversByExpiryDate(branchInfo[i][0]);
		if(openCovers!=null && !openCovers.isEmpty()){
			emailSubjectTxt = "List of Open Cover Policies that are going to expire in less than 60 days for "+branchInfo[i][3];
			emailMsgTxt = mailBean.getOpenCoverEmailMsgTxt(openCovers, emailSubjectTxt);
			writeFile(openCovers, fileName);
			final String[] emailList = branchInfo[i][1].split(",");
			final String cc1Address[] = branchInfo[i][2].split(",");
			final mailController smtpMailSender = new mailController();
			try {
				smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt+testMsg, emailMsgTxt.toString(), "",basePaths,branchInfo[i][0], attachment);
			} catch (MessagingException e) {
				
				e.printStackTrace();
			}
		}
	}
} 
	branchInfo=new opencoverEntry().getBranchInfoByOpenCoverStartDate();	
	if(branchInfo!=null && branchInfo.length>0){
		for (int i = 0; i < branchInfo.length; i++) {
			openCovers=new opencoverEntry().getOpenCoversByStartDate(branchInfo[i][0]);
			if(openCovers!=null && !openCovers.isEmpty()){
				emailSubjectTxt = "List of Open Cover Policies that are going to complete a year in less than 60 days for "+branchInfo[i][3];
				emailMsgTxt = mailBean.getOpenCoverEmailMsgTxt(openCovers, emailSubjectTxt);
				writeFile(openCovers, fileName);
				final String[] emailList = branchInfo[i][1].split(",");
				final String cc1Address[] = branchInfo[i][2].split(",");
				final mailController smtpMailSender = new mailController();
				try {
					smtpMailSender.postMail( emailList,cc1Address,emailSubjectTxt+testMsg, emailMsgTxt.toString(), "",basePaths,branchInfo[i][0], attachment);
				} catch (MessagingException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
}
public void writeFile(List<LinkedHashMap<String,Object>> openCovers, String fileName) 
{
	try {
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		String[] headers=new String[]{"S.No","Core Application Policy No","Proposal No","Broker Name","Customer Name","Policy Start Date","Policy End Date"};
		ExcelDownload.writeExcel(headers, openCovers, bos);
		File fileToCreate=new File(fileName);
		FileUtils.writeByteArrayToFile(fileToCreate, bos.toByteArray());
	} catch (Exception e) {
		e.printStackTrace();
	}
	logger.info("downloadError() - Exit");
}
//expressed in milliseconds
private static Date getScheduledTime(){
	Calendar tomorrow = new GregorianCalendar();
	tomorrow.add(Calendar.DATE, fONE_DAY);
	Calendar result = new GregorianCalendar(
		tomorrow.get(Calendar.YEAR),
		tomorrow.get(Calendar.MONTH),
		tomorrow.get(Calendar.DATE),
		HOUR,
		MINUTES
	);
	return result.getTime();
}

public Timer getTimer() {
	return this.timer;
}
}