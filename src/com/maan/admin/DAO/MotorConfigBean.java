package com.maan.admin.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.services.util.runner;
public class MotorConfigBean {
	private static final Logger logger = LogUtil.getLogger(MotorConfigBean.class);
	private static final String EXITMESSAGE ="@@@@@Exit from List@@@@@@@@@@@";
	private static final String EDITQUERY =".............EDIT QUERY==>";
	private static final String INSERTSUCCESS ="Inserted Successfully";
	private static final String INSERTFAIL ="Insertion Failed";
	private static final String EDIT ="Edit";
	private static final String DATEQUERY ="SELECT distinct (CASE WHEN (TO_DATE('";
	
	private static final String MODE ="Mode: ";
	 private String areaCoverId;
	 private String areaCoverCode;
	 private String areaEnglish;
	 private String areaArabic;
	 private String status;
	 private String effDay;
	 private String effMonth;
	 private String effYear;
	 private String effDate;
	 private String exDate;
	 private String remarks;
	 private String branchCode;
	 private String amendId;
	 //MOtorPolicyMaster
	 private String policyTypeId;
	 private String coreAppCode;
	 private String polyTypeEng;
	 private String polyTypeArabic;
	 private String policyTerm;
	 private String policytypeName;
	 //MotorOPCoverMaster
	 private String opCoverId;
	 private String opCoverEnglish;
	 private String opCoverArabic;
	 //Motor_Bank_Master
	 private String bankId;
	 private String bankCOde;
	 private String bankNameEng;
	 private String bankNameArabic;
	 //motor_color_master
	 private String colorId;
	 private String colorCode;
	 private String colorNameArabic;
	 private String colorNameEng;
	 //motor_cylinder_master
	 private String cylId;
	 private String cylCode;
	 private String cylNameEng;
	 private String cylNameArabic;
	 private String typeOfBodyId;
	 //motor_uplode_file_master
	 private String fileId;
	 private String fileType;
	 private String productId;
	 private String productName;
	 //motor_group_master
	 private String groupId;
	 private String groupNameEng;
	 //Motor_Voluntary_Master
	 private String voluntaryId;
	 private String voluntaryNAme;
	 private String voluntaryValue;
	 //motor_vehicletype_master
	 private String  vtypeId;
	 private String vehicleType;
	 private String vehicledesc;
	 private String vehiclearabic;
	 private String vestatus;
	 
	 
	 public String getProductName() {
			return productName;
		}
		public void setProductName(final String productName) {
			this.productName = productName;
		}
	 
	public String getVoluntaryId() {
		return voluntaryId;
	}
	public void setVoluntaryId(final String voluntaryId) {
		this.voluntaryId = voluntaryId;
	}
	public String getVoluntaryNAme() {
		return voluntaryNAme;
	}
	public void setVoluntaryNAme(final String voluntaryNAme) {
		this.voluntaryNAme = voluntaryNAme;
	}
	public String getVoluntaryValue() {
		return voluntaryValue;
	}
	public void setVoluntaryValue(final String voluntaryValue) {
		this.voluntaryValue = voluntaryValue;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(final String groupId) {
		this.groupId = groupId;
	}
	public String getGroupNameEng() {
		return groupNameEng;
	}
	public void setGroupNameEng(final String groupNameEng) {
		this.groupNameEng = groupNameEng;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(final String fileId) {
		this.fileId = fileId;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(final String fileType) {
		this.fileType = fileType;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(final String productId) {
		this.productId = productId;
	}
	public String getCylCode() {
		return cylCode;
	}
	public void setCylCode(final String cylCode) {
		this.cylCode = cylCode;
	}
	public String getCylId() {
		return cylId;
	}
	public void setCylId(final String cylId) {
		this.cylId = cylId;
	}
	public String getCylNameArabic() {
		return cylNameArabic;
	}
	public void setCylNameArabic(final String cylNameArabic) {
		this.cylNameArabic = cylNameArabic;
	}
	public String getCylNameEng() {
		return cylNameEng;
	}
	public void setCylNameEng(final String cylNameEng) {
		this.cylNameEng = cylNameEng;
	}
	public String getTypeOfBodyId() {
		return typeOfBodyId;
	}
	public void setTypeOfBodyId(final String typeOfBodyId) {
		this.typeOfBodyId = typeOfBodyId;
	}
	public String getColorCode() {
		return colorCode;
	}
	public void setColorCode(final String colorCode) {
		this.colorCode = colorCode;
	}
	public String getColorId() {
		return colorId;
	}
	public void setColorId(final String colorId) {
		this.colorId = colorId;
	}
	public String getColorNameArabic() {
		return colorNameArabic;
	}
	public void setColorNameArabic(final String colorNameArabic) {
		this.colorNameArabic = colorNameArabic;
	}
	public String getColorNameEng() {
		return colorNameEng;
	}
	public void setColorNameEng(final String colorNameEng) {
		this.colorNameEng = colorNameEng;
	}
	public String getBankCOde() {
		return bankCOde;
	}
	public void setBankCOde(final String bankCOde) {
		this.bankCOde = bankCOde;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(final String bankId) {
		this.bankId = bankId;
	}
	public String getBankNameArabic() {
		return bankNameArabic;
	}
	public void setBankNameArabic(final String bankNameArabic) {
		this.bankNameArabic = bankNameArabic;
	}
	public String getBankNameEng() {
		return bankNameEng;
	}
	public void setBankNameEng(final String bankNameEng) {
		this.bankNameEng = bankNameEng;
	}
	public String getOpCoverArabic() {
		return opCoverArabic;
	}
	public void setOpCoverArabic(final String opCoverArabic) {
		this.opCoverArabic = opCoverArabic;
	}
	public String getOpCoverEnglish() {
		return opCoverEnglish;
	}
	public void setOpCoverEnglish(final String opCoverEnglish) {
		this.opCoverEnglish = opCoverEnglish;
	}
	public String getOpCoverId() {
		return opCoverId;
	}
	public void setOpCoverId(final String opCoverId) {
		this.opCoverId = opCoverId;
	}
	public String getAmendId() {
		return amendId;
	}
	public void setAmendId(final String amendId) {
		this.amendId = amendId;
	}
	public String getAreaArabic() {
		return areaArabic;
	}
	public void setAreaArabic(final String areaArabic) {
		this.areaArabic = areaArabic;
	}
	public String getAreaCoverCode() {
		return areaCoverCode;
	}
	public void setAreaCoverCode(final String areaCoverCode) {
		this.areaCoverCode = areaCoverCode;
	}
	public String getAreaCoverId() {
		return areaCoverId;
	}
	public void setAreaCoverId(final String areaCoverId) {
		this.areaCoverId = areaCoverId;
	}
	public String getAreaEnglish() {
		return areaEnglish;
	}
	public void setAreaEnglish(final String areaEnglish) {
		this.areaEnglish = areaEnglish;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(final String branchCode) {
		this.branchCode = branchCode;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(final String effDate) {
		this.effDate = effDate;
	}
	public String getEffDay() {
		return effDay;
	}
	public void setEffDay(final String effDay) {
		this.effDay = effDay;
	}
	public String getEffMonth() {
		return effMonth;
	}
	public void setEffMonth(final String effMonth) {
		this.effMonth = effMonth;
	}
	public String getEffYear() {
		return effYear;
	}
	public void setEffYear(final String effYear) {
		this.effYear = effYear;
	}
	public String getExDate() {
		return exDate;
	}
	public void setExDate(final String exDate) {
		this.exDate = exDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(final String status) {
		this.status = status;
	}
	
	//From Here Motor Policy Type Master Getter And Setter Started
	
	public String getCoreAppCode() {
		return coreAppCode;
	}
	public void setCoreAppCode(final String coreAppCode) {
		this.coreAppCode = coreAppCode;
	}
	public String getPolicyTypeId() {
		return policyTypeId;
	}
	public void setPolicyTypeId(final String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}
	public String getPolyTypeArabic() {
		return polyTypeArabic;
	}
	public void setPolyTypeArabic(final String polyTypeArabic) {
		this.polyTypeArabic = polyTypeArabic;
	}
	public String getPolyTypeEng() {
		return polyTypeEng;
	}
	public void setPolyTypeEng(final String polyTypeEng) {
		this.polyTypeEng = polyTypeEng;
	}
	public String getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}
	
	

	
	public String[][] getConfig(final String branchCode)
	{
		String result[][]=new String[0][0];
		try
		{
		final String sql="select AREACOVER_ID,AREACOVER_CODE,AREACOVER_DESC_ENGLISH,AREACOVER_DESC_ARABIC,substr((EFFECTIVE_DATE),1,10) AS Effective_date,case when(status='Y') then 'Active' else 'InActive' end status from MOTOR_AREACOVERAGE_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_AREACOVERAGE_MASTER where branch_code=? and AREACOVER_ID=ac.AREACOVER_ID ) order by areacover_id asc ";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			
			logger.fatal(e);
		}
		return result;
	}

	public ArrayList getConfigAreaList(final String[][] result) 
	{
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of CONFIG************");
			
			                   
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean = new MotorConfigBean();
					bean.setAreaCoverId(result[i][0] == null ? "" : result[i][0]);
					bean.setAreaCoverCode(result[i][1] == null ? "" : result[i][1]);
					bean.setAreaEnglish(result[i][2] == null ? "": result[i][2]);
					bean.setAreaArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setEffDate(result[i][4] == null ? "" : result[i][4]);
					bean.setStatus(result[i][5] == null ? "" : result[i][5]);
					list.add(bean);
			}
		}
		catch(Exception ex){
			
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   }
	
	
	
	public String checkValidDate(final String ddmmyyyy)
	 	{
	 		final DateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy"); 
	         dateFormat.setLenient(false);
	 		
	     	Date date = new Date();
	     	 String msg="";
	 		try{
	 			date = dateFormat.parse (ddmmyyyy);
	 			
	 		}
	 		catch(ParseException e){
	 			msg= "Enter ValidDate";	
	 		}
	 		return msg;
	 		
	     }
	
	public String isValidDate(final String date)
    {
   	 String message="";
   	 try
   	 {
   	  message=checkValidDate(date);
   	 if(message.length()>0)
   	 {
   		 message= "Enter Valid Date";
   	 }
   	
   	 else
   	 {
   	 final Calendar cal = Calendar.getInstance();
   	final DateFormat dateFormat = new SimpleDateFormat ("dd-MM-yyyy");
   	 
   	 final Date date1 = dateFormat.parse (date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        
        if (cal.getTime().after(date1))
        {
        	message= "Effective date should be greater or equal to current date";
        }
        else
        {
        	message= "";
        }
   	 }
   	 
   	 
   	 
   	 }
   	 catch(Exception e)
   	 {
   		message ="";			 
   	 }
   	 
   	 return message;
    }
	public String insertAreaConfig(final String branchCode, String areaCoverId,final MotorConfigBean bean,final String mode) 
	{
		 
		
		//MakeBean bean = new MakeBean();
		
		
		String date123=null;
		if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
		{
			
			date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
		}
		bean.setEffDate(date123);
		if (!EDIT.equalsIgnoreCase(mode))
		{
		 
			final String qry="SELECT NVL(MAX(AREACOVER_ID)+1,0) FROM MOTOR_AREACOVERAGE_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
			areaCoverId=runner.singleSelection(qry);
		}
		
		logger.info(MODE+mode+" areaCoverId: "+areaCoverId);
		String errorMessage;
		try
		{
			logger.info("hi Ram");
		final String sql="INSERT INTO MOTOR_AREACOVERAGE_MASTER(AREACOVER_ID,AREACOVER_DESC_ENGLISH,AREACOVER_DESC_ARABIC,AREACOVER_CODE,AMEND_ID,EFFECTIVE_DATE,STATUS,BRANCH_CODE,REMARKS) VALUES (?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_AREACOVERAGE_MASTER WHERE AREACOVER_ID=? AND BRANCH_CODE=?),TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,?,?)";
		String cols[]=new String[9];
		cols[0]=areaCoverId;
		cols[1]=bean.getAreaEnglish();
		cols[2]=bean.getAreaArabic();
		cols[3]=bean.getAreaCoverCode();
		cols[4]=areaCoverId;
		cols[5]=branchCode;
		cols[6]=bean.getStatus();
		cols[7]=branchCode;
		cols[8]=bean.getRemarks();
		
		logger.info(EDITQUERY+sql);
		runner.multipleInsertion(sql,cols);
		errorMessage= INSERTSUCCESS;	
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
			errorMessage= INSERTFAIL;
		}


		return errorMessage;
		
	}
	public String[][] getAreaCongigById(final String branchCode, final String areaCoverId) 
	{
		

		String result[][]=new String[0][0];
		try
		{
		final String sql="select AREACOVER_ID,AREACOVER_CODE,AREACOVER_DESC_ENGLISH,AREACOVER_DESC_ARABIC,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),STATUS,REMARKS from MOTOR_AREACOVERAGE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_AREACOVERAGE_MASTER where branch_code=? and AREACOVER_ID=ac.AREACOVER_ID )and ac.AREACOVER_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=areaCoverId;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	public String isVAlidAreaDate(final String ddmmyyyy,final String areaCoverId,final String branchCode)
 	{
		//String sql="SELECT (CASE WHEN (TO_DATE('"+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(TO_CHAR(EFFECTIVE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_AREACOVERAGE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_AREACOVERAGE_MASTER where branch_code=? and AREACOVER_ID=ac.AREACOVER_ID )and ac.AREACOVER_ID=?";
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_AREACOVERAGE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_AREACOVERAGE_MASTER where branch_code=? and AREACOVER_ID=ac.AREACOVER_ID )and ac.AREACOVER_ID=?";
		
		
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=areaCoverId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	
//	From Here Motor Policy Type Master DAO Started
	public String[][] getMotorPolicy(final String branchCode2) 
	{
		 String result[][]=new String[0][0];
		try
		{
		final String sql="select POLICYTYPE_ID,COREAPP_CODE,POLICYTYPE_DESC_ENGLISH,POLICYTYPE_DESC_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE,POLICY_TERM,POLICYTYPE_NAME from MOTOR_POLICYTYPE_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_POLICYTYPE_MASTER where branch_code=? and POLICYTYPE_ID=ac.POLICYTYPE_ID ) order by POLICYTYPE_ID asc ";
		String cols[]=new String[2];
		cols[0]=branchCode2;
		cols[1]=branchCode2;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	}
	
	public ArrayList getMotorPolicyTypeList(final String[][] result) 
	{

		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of MotorType************");
			
			                  
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean = new MotorConfigBean();
					bean.setPolicyTypeId(result[i][0] == null ? "" : result[i][0]);
					bean.setCoreAppCode(result[i][1] == null ? "" : result[i][1]);
					bean.setPolyTypeEng(result[i][2] == null ? "": result[i][2]);
					bean.setPolyTypeArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setStatus(result[i][4] == null ? "" : result[i][4]);
					bean.setEffDate(result[i][5] == null ? "" : result[i][5]);
					bean.setExDate(result[i][6] == null ? "" : result[i][6]);
					bean.setRemarks(result[i][7] == null ? "" : result[i][7]);
					bean.setAmendId(result[i][8] == null ? "" : result[i][8]);
					bean.setBranchCode(result[i][9] == null ? "" : result[i][9]);
					bean.setPolicyTerm(result[i][10] == null ? "" : result[i][10]);
					bean.setPolicytypeName(result[i][11] == null ? "" : result[i][11]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	}
	public String[][] getMotorPolicyById(final String branchCode, final String policyTypeId) 
	{
		String result[][]=new String[0][0];
		try
		{			
		final String sql="select POLICYTYPE_ID,COREAPP_CODE,POLICYTYPE_DESC_ENGLISH,POLICYTYPE_DESC_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS,POLICY_TERM,POLICYTYPE_NAME from MOTOR_POLICYTYPE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_POLICYTYPE_MASTER where branch_code=? and POLICYTYPE_ID=ac.POLICYTYPE_ID )and ac.POLICYTYPE_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=policyTypeId;
		result=runner.multipleSelection(sql,cols);
		logger.info(sql);
		logger.info(cols[1]);
		logger.info(cols[2]);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
		
	}
	public String isVAlidEffDate(final String ddmmyyyy,final String policyId,final String branchCode)
 	{
		//String sql="SELECT (CASE WHEN (TO_DATE('"+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(TO_CHAR(EFFECTIVE_DATE,'DD-MM-YYYY'),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_POLICYTYPE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_POLICYTYPE_MASTER where branch_code=? and POLICYTYPE_ID=ac.POLICYTYPE_ID )and ac.POLICYTYPE_ID=?";
		
		final String sql="SELECT (CASE WHEN (TO_DATE('"+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_POLICYTYPE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_POLICYTYPE_MASTER where branch_code=? and POLICYTYPE_ID=ac.POLICYTYPE_ID )and ac.POLICYTYPE_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=policyTypeId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public String insertMotorPolicy(final String branchCode, String policyTypeId, final MotorConfigBean bean, final String mode) 
	{
		 
		
		//MakeBean bean = new MakeBean();
		String result="";
		
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" policyTypeId: "+policyTypeId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
			 
				final String sql="SELECT NVL(MAX(POLICYTYPE_ID)+1,0) FROM MOTOR_POLICYTYPE_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				policyTypeId=runner.singleSelection(sql);
				final String sql1="INSERT INTO MOTOR_POLICYTYPE_MASTER(POLICYTYPE_ID,COREAPP_CODE,POLICYTYPE_DESC_ENGLISH,POLICYTYPE_DESC_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE,POLICY_TERM,POLICYTYPE_NAME) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=? AND BRANCH_CODE=?),?,?,?)";
				String cols[]=new String[11];
				cols[0]=policyTypeId;
				cols[1]=bean.getCoreAppCode();
				cols[2]=bean.getPolyTypeEng();
				cols[3]=bean.getPolyTypeArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=policyTypeId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				cols[9]=bean.getPolicyTerm();
				cols[10]=bean.getPolicytypeName();
				
				logger.info(EDITQUERY+sql1);
				result=runner.multipleInsertion(sql1,cols);				
			}else{
				final String sql="UPDATE MOTOR_POLICYTYPE_MASTER SET COREAPP_CODE=?,POLICYTYPE_DESC_ENGLISH=?,POLICYTYPE_DESC_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=?,POLICY_TERM=?,POLICYTYPE_NAME=? WHERE POLICYTYPE_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[9];
				cols[0]=bean.getCoreAppCode();
				cols[1]=bean.getPolyTypeEng();
				cols[2]=bean.getPolyTypeArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=bean.getPolicyTerm();
				cols[6]=bean.getPolicytypeName();
				cols[7]=policyTypeId;
				cols[8]=branchCode;
				
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
			}			
			
			result  = INSERTSUCCESS;	
			
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result  = INSERTFAIL;
		}
		return result;
	}
	public String[][] getOPCover(final String branchCode)
	{

		String result[][]=new String[0][0];
		try
		{
		final String sql="select OPCOVER_ID,COREAPP_CODE,OPCOVER_DESC_ENGLISH,OPCOVER_DESC_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_OPCOVER_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_OPCOVER_MASTER where branch_code=? and OPCOVER_ID=ac.OPCOVER_ID ) order by OPCOVER_ID asc ";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	
		
	}
	public ArrayList getMotorOPCoverList(final String[][] result)
	{


		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of OPCoverLis************");
			
			                  
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean = new MotorConfigBean();
					bean.setOpCoverId(result[i][0] == null ? "" : result[i][0]);
					bean.setCoreAppCode(result[i][1] == null ? "" : result[i][1]);
					bean.setOpCoverEnglish(result[i][2] == null ? "": result[i][2]);
					bean.setOpCoverArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setStatus(result[i][4] == null ? "" : result[i][4]);
					bean.setEffDate(result[i][5] == null ? "" : result[i][5]);
					bean.setExDate(result[i][6] == null ? "" : result[i][6]);
					bean.setRemarks(result[i][7] == null ? "" : result[i][7]);
					bean.setAmendId(result[i][8] == null ? "" : result[i][8]);
					bean.setBranchCode(result[i][9] == null ? "" : result[i][9]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	
		
	}
	public String[][] getMotorOPCoverById(final String branchCode,final String opCoverId) 
	{

		String result[][]=new String[0][0];
		try
		{			
		final String sql="select OPCOVER_ID,COREAPP_CODE,OPCOVER_DESC_ENGLISH,OPCOVER_DESC_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_OPCOVER_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_OPCOVER_MASTER where branch_code=? and OPCOVER_ID=ac.OPCOVER_ID )and ac.OPCOVER_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=opCoverId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	public String isVAlidOPDate(final String ddmmyyyy,final String opCoverId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_OPCOVER_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_OPCOVER_MASTER where branch_code=? and OPCOVER_ID=ac.OPCOVER_ID )and ac.OPCOVER_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=opCoverId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	
	public String insertOPCover(final String branchCode, String opCoverId, final MotorConfigBean bean, final String mode) 
	{
		 
		
		//MakeBean bean = new MakeBean();
		String result="";
		
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" opCoverId: "+opCoverId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
			 
				final String sql1="SELECT NVL(MAX(OPCOVER_ID)+1,0) FROM MOTOR_OPCOVER_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				opCoverId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_OPCOVER_MASTER(OPCOVER_ID,COREAPP_CODE,OPCOVER_DESC_ENGLISH,OPCOVER_DESC_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_OPCOVER_MASTER WHERE OPCOVER_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[9];
				cols[0]=opCoverId;
				cols[1]=bean.getCoreAppCode();
				cols[2]=bean.getOpCoverEnglish();
				cols[3]=bean.getOpCoverArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=opCoverId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				final String sql="UPDATE MOTOR_OPCOVER_MASTER SET COREAPP_CODE=?,OPCOVER_DESC_ENGLISH=?,OPCOVER_DESC_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=? WHERE OPCOVER_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=bean.getCoreAppCode();
				cols[1]=bean.getOpCoverEnglish();
				cols[2]=bean.getOpCoverArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=opCoverId;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	}
	public String[][] getBank(final String branchCode) 
	{


		String result[][]=new String[0][0];
		try
		{
		final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_BANK_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_BANK_MASTER where branch_code=? and BANK_ID=ac.BANK_ID ) order by BANK_ID asc ";
		//String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	
		
	
		
	}
	public ArrayList getMotorBankList(final String[][] result) 
	{
		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getMotorBankList************");
			
			                  
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean = new MotorConfigBean();
					bean.setBankId(result[i][0] == null ? "" : result[i][0]);
					bean.setBankCOde(result[i][1] == null ? "" : result[i][1]);
					bean.setBankNameEng(result[i][2] == null ? "": result[i][2]);
					bean.setBankNameArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setStatus(result[i][4] == null ? "" : result[i][4]);
					bean.setEffDate(result[i][5] == null ? "" : result[i][5]);
					bean.setExDate(result[i][6] == null ? "" : result[i][6]);
					bean.setRemarks(result[i][7] == null ? "" : result[i][7]);
					bean.setAmendId(result[i][8] == null ? "" : result[i][8]);
					bean.setBranchCode(result[i][9] == null ? "" : result[i][9]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	
		
	
		
	}
	public String[][] getMotorBankById(final String branchCode,final String bankId) 
	{

		String result[][]=new String[0][0];
		try
		{			
		final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		//String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=bankId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	public String isVAlidBankDate(final String ddmmyyyy,final String bankId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_BANK_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_BANK_MASTER where branch_code=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=bankId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public String insertMotorBank(final String branchCode, String bankId,final MotorConfigBean bean,final String mode)
	{

		logger.info("&&&&&&&&&&&__&&&&&&"+branchCode+"&&&&--&&"+bankId+"&&&--&&&"+bean.getEffDate()+"&&&&-&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				//date123=com.maan.common.util.OracleDateConversion.ConvertDate(bean.getEffDate() + "-" + bean.getEffMonth() + "-" + bean.getEffYear());
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" bankId: "+bankId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
			 
				final String sql1="SELECT NVL(MAX(BANK_ID)+1,0) FROM MOTOR_BANK_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				bankId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_BANK_MASTER(BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_BANK_MASTER WHERE BANK_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[9];
				cols[0]=bankId;
				cols[1]=bean.getBankCOde();
				cols[2]=bean.getBankNameEng();
				cols[3]=bean.getBankNameArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=bankId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				final String sql="UPDATE MOTOR_BANK_MASTER SET BANK_CODE=?,BANK_NAME_ENGLISH=?,BANK_NAME_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=? WHERE BANK_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=bean.getBankCOde();
				cols[1]=bean.getBankNameEng();
				cols[2]=bean.getBankNameArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=bankId;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	}
	public String[][] getMotorColor(final String branchCode)
	{



		String result[][]=new String[0][0];
		try
		{
		final String sql="select COLOR_ID,COLOR_CODE,COLOR_DESC_ENGLISH,COLOR_DESC_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_COLOR_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_COLOR_MASTER where branch_code=? and COLOR_ID=ac.COLOR_ID ) order by COLOR_ID asc ";
		
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	
	public ArrayList getMotorColorList(final String[][] result) 
	{
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of MotorColor************");
			
			                  
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
					bean.setColorId(result[i][0] == null ? "" : result[i][0]);
					bean.setColorCode(result[i][1] == null ? "" : result[i][1]);
					bean.setColorNameEng(result[i][2] == null ? "": result[i][2]);
					bean.setColorNameArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setStatus(result[i][4] == null ? "" : result[i][4]);
					bean.setEffDate(result[i][5] == null ? "" : result[i][5]);
					bean.setExDate(result[i][6] == null ? "" : result[i][6]);
					bean.setRemarks(result[i][7] == null ? "" : result[i][7]);
					bean.setAmendId(result[i][8] == null ? "" : result[i][8]);
					bean.setBranchCode(result[i][9] == null ? "" : result[i][9]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   }
	public String[][] getMotorColorById(final String branchCode,final String colorId)
	{


		String result[][]=new String[0][0];
		try
		{			
		final String sql="select COLOR_ID,COLOR_CODE,COLOR_DESC_ENGLISH,COLOR_DESC_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_COLOR_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_COLOR_MASTER where BRANCH_CODE=? and COLOR_ID=ac.COLOR_ID )and ac.COLOR_ID=?";
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=colorId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
		
	}
	public String[][] getMotorVehicleById(final String branchCode,final String typeid)
	{


		String result[][]=new String[0][0];
		try
		{			
		final String sql="select vtype_id,vtype_code,vehicletype_desc_english,vehicletype_desc_arabic,status from motor_vehicletype_master where branch_code=? and vtype_id=?";
		
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=typeid;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
		
	}
	public String isVAlidColorDate(final String ddmmyyyy,final String colorId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_COLOR_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_COLOR_MASTER where branch_code=? and COLOR_ID=ac.COLOR_ID )and ac.COLOR_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=colorId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public int getColorCount(final String branchCode, final String colorId, final String colorCode)
	{
		final String sql="select count(*) from motor_color_master mcm where color_code=? and branch_code=? and color_id!=? and amend_id=(select max(amend_id) from motor_color_master mcm where color_code=mcm.color_code and branch_code=mcm.branch_code and color_id=mcm.color_id)";
		String cols[]=new String[3];
		cols[0]=colorCode;
		cols[1]=branchCode;
		cols[2]=colorId;
		final String count=runner.singleSelection(sql, cols);
		final int count1=Integer.parseInt(count);
		return count1;
	}
	
	
	public int getVehicleCount(final String branchCode, final String strvtypeid, final String vehicletype)
	{
		final String sql="select count(*) from motor_vehicletype_master mvm  where vtype_id!=? and branch_code=? and vtype_code=? and amend_id=(select max(amend_id) from motor_vehicletype_master mcm where vtype_id=mcm.vtype_id and vtype_code=mcm.vtype_code and branch_code=mcm.branch_code)";
		String cols[]=new String[3];
		cols[0]=strvtypeid;
		cols[1]=branchCode;
		cols[2]=vehicletype;
		final String count=runner.singleSelection(sql, cols);
		final int count1=Integer.parseInt(count);
		return count1;
	}
	public String insertMotorColor(final String branchCode,  String colorId, final MotorConfigBean bean, final String mode) 
	{


		logger.info("&&&&&&&&&&&&&^^&&&&"+branchCode+"&&^^&&&&"+colorId+"&&&&^^&&"+bean.getEffDate()+"&&^^&&&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		 
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" colorId: "+colorId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
			 
				final String sql1="SELECT NVL(MAX(COLOR_ID)+1,0) FROM MOTOR_COLOR_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				colorId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_COLOR_MASTER(COLOR_ID,COLOR_CODE,COLOR_DESC_ENGLISH,COLOR_DESC_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_COLOR_MASTER WHERE COLOR_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[9];
				cols[0]=colorId;
				cols[1]=bean.getColorCode();
				cols[2]=bean.getColorNameEng();
				cols[3]=bean.getColorNameArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=colorId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				final String sql="UPDATE MOTOR_COLOR_MASTER SET COLOR_CODE=?,COLOR_DESC_ENGLISH=?,COLOR_DESC_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=? WHERE COLOR_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=bean.getColorCode();
				cols[1]=bean.getColorNameEng();
				cols[2]=bean.getColorNameArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=colorId;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	
		
	}
	
	public String insertMotorVehicle(final String branchCode,  String vtypeid, final MotorConfigBean bean, final String mode) 
	{


		logger.info("&&&&&&&&&&&&&^^&&&&"+branchCode+"&&^^&&&&"+vtypeid+"&&&&^^&&&&^^&&&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		 
		String date123=null;
		try
		{
			/*if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);*/
			if (!EDIT.equalsIgnoreCase(mode))
			{
			 
				final String sql1="SELECT NVL(MAX(VTYPE_ID)+1,0) FROM MOTOR_VEHICLETYPE_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				vtypeid=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_VEHICLETYPE_MASTER(VTYPE_ID,VTYPE_CODE,VEHICLETYPE_DESC_ENGLISH,VEHICLETYPE_DESC_ARABIC,STATUS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_VEHICLETYPE_MASTER WHERE VTYPE_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[8];
				cols[0]=vtypeid;
				cols[1]=bean.getVehicleType();
				cols[2]=bean.getVehicledesc();
				cols[3]=bean.getVehiclearabic();
				cols[4]=bean.getVestatus();
				cols[5]=vtypeid;
				cols[6]=branchCode;
				cols[7]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				final String sql="UPDATE MOTOR_VEHICLETYPE_MASTER SET VTYPE_CODE=?,VEHICLETYPE_DESC_ENGLISH=?,VEHICLETYPE_DESC_ARABIC=?,STATUS=? WHERE VTYPE_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[6];
				cols[0]=bean.getVehicleType();
				cols[1]=bean.getVehicledesc();
				cols[2]=bean.getVehiclearabic();
				cols[3]=bean.getVestatus();
				cols[4]=vtypeid;
				cols[5]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
	}
	
	
	public String[][] getFinanceBank(final String branchCode) 
	{


		String result[][]=new String[0][0];
		try
		{
		final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_FINANCE_BANK_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_FINANCE_BANK_MASTER where branch_code=? and BANK_ID=ac.BANK_ID ) order by BANK_ID asc ";
		//final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	
		
	
		
	}
	public ArrayList getMotorFinanceBankList(final String[][] result) 
	{
		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getMotorFinanceBankList************");
			
			                  
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
					bean.setBankId(result[i][0] == null ? "" : result[i][0]);
					bean.setBankCOde(result[i][1] == null ? "" : result[i][1]);
					bean.setBankNameEng(result[i][2] == null ? "": result[i][2]);
					bean.setBankNameArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setStatus(result[i][4] == null ? "" : result[i][4]);
					bean.setEffDate(result[i][5] == null ? "" : result[i][5]);
					bean.setExDate(result[i][6] == null ? "" : result[i][6]);
					bean.setRemarks(result[i][7] == null ? "" : result[i][7]);
					bean.setAmendId(result[i][8] == null ? "" : result[i][8]);
					bean.setBranchCode(result[i][9] == null ? "" : result[i][9]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	
		
	
		
	}
	public String[][] getMotorFinanceBankById(final String branchCode, final String bankId) 
	{

		String result[][]=new String[0][0];
		try
		{			
		final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_FINANCE_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_FINANCE_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		//final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=bankId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	
	
	public String isVAlidFinanceDate(final String  ddmmyyyy,final String cylId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_FINANCE_BANK_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_FINANCE_BANK_MASTER where branch_code=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=cylId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public String insertMotorFinanceBank(final String branchCode, String bankId, final MotorConfigBean bean, final String mode)
	{

		logger.info("&&&&%%&&&&&&&&&&&&&"+branchCode+"&&&&%%&&"+bankId+"&&&&%&&"+bean.getEffDate()+"&&&&%%&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		 
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" bankId: "+bankId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
				logger.info("I am Using This Finance Bank MAster______");
				final String sql1="SELECT NVL(MAX(BANK_ID)+1,0) FROM MOTOR_FINANCE_BANK_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				bankId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_FINANCE_BANK_MASTER(BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_FINANCE_BANK_MASTER WHERE BANK_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[9];
				cols[0]=bankId;
				cols[1]=bean.getBankCOde();
				cols[2]=bean.getBankNameEng();
				cols[3]=bean.getBankNameArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=bankId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				logger.info("I am Using This Finance Bank MAster______");
				final String sql="UPDATE MOTOR_FINANCE_BANK_MASTER SET BANK_CODE=?,BANK_NAME_ENGLISH=?,BANK_NAME_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=? WHERE BANK_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=bean.getBankCOde();
				cols[1]=bean.getBankNameEng();
				cols[2]=bean.getBankNameArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=bankId;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	}
	
	public String[][] getCylinderDisplay(final String branchCode, final String typeOfBodyId)
	{
		
		String result[][]=new String[0][0];
		
		try
		{
			//final String sql="select CYL_ID,CYL_CODE,CYLINDER_DESC_ENGLISH,CYLINDER_DESC_ARABIC,to_char(EFFECTIVE_DATE,'YYYY') year,to_char(EFFECTIVE_DATE,'MM') month,to_char(EFFECTIVE_DATE,'DD') day,STATUS,TYPE_OF_BODY  from MOTOR_CYCLINDER_MASTER where  BRANCH_CODE=?  and TYPE_OF_BODY=? and STATUS='Y' AND MODEL_ID||AMEND_ID in (SELECT MODEL_ID||MAX(AMEND_ID) FROM MOTOR_CYCLINDER_MASTER where  BRANCH_CODE=?  and TYPE_OF_BODY=? and STATUS='Y' group by TYPE_OF_BODY)";

			final String sql="select  CYL_ID,CYL_CODE,CYLINDER_DESC_ENGLISH,CYLINDER_DESC_ARABIC,to_char(EFFECTIVE_DATE,'YYYY') year,to_char(EFFECTIVE_DATE,'MM') month,to_char(EFFECTIVE_DATE,'DD') day,(case when STATUS='Y' then 'Active' else 'InActive' end),TYPE_OF_BODY  from MOTOR_CYCLINDER_MASTER where  BRANCH_CODE=?  and TYPE_OF_BODY=? AND CYL_ID||AMEND_ID in (SELECT CYL_ID||MAX(AMEND_ID) FROM MOTOR_CYCLINDER_MASTER where  BRANCH_CODE=?  and TYPE_OF_BODY=? group by TYPE_OF_BODY,CYL_ID)";
			String cols[]=new String[4];
			cols[0]=branchCode;
			cols[1]=typeOfBodyId;
			cols[2]=branchCode;
			cols[3]=typeOfBodyId;
			
			result=runner.multipleSelection(sql, cols);
		 	
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		
		return result;
		
	}
	public String getCylinderTypeOfBody(final String branchCode,final String typeOfBodyId)
	{
		 String result="";
		try
		{
		final String sql="select TYPE_OF_BODY_NAME  from MOTOR_TYPE_BODY_MASTER a where BRANCH_CODE=? and TYPE_OF_BODY_ID=? and AMEND_ID=(select max(AMEND_ID) from MOTOR_TYPE_BODY_MASTER where BRANCH_CODE=a.BRANCH_CODE and TYPE_OF_BODY_ID=a.TYPE_OF_BODY_ID )";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=typeOfBodyId;
		 result=runner.singleSelection(sql, cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	public ArrayList getMotorCylinderList(final String[][] result)
	{
		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getMotorCylinderList************");
			
			                  
			
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
					bean.setCylId(result[i][0] == null ? "" : result[i][0]);
					bean.setCylCode(result[i][1] == null ? "" : result[i][1]);
					bean.setCylNameEng(result[i][2] == null ? "": result[i][2]);
					bean.setCylNameArabic(result[i][3] == null ? "" : result[i][3]);
					bean.setEffYear(result[i][4] == null ? "": result[i][4]);
					bean.setEffMonth(result[i][5] == null ? "": result[i][5]);
					bean.setEffDay(result[i][6] == null ? "": result[i][6]);
					bean.setStatus(result[i][7] == null ? "" : result[i][7]);
					bean.setTypeOfBodyId(result[i][8] == null ? "" : result[i][8]);
					list.add(bean);
				for(int j=0; j<8; j++)
				{
					logger.info("result=====>>>: "+result[i][j]);
				}
				
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	
		
	
		
	}
	public String[][] getMotorCylinderById(final String branchCode, final String cylId) 
	{

		String result[][]=new String[0][0];
		try
		{			
		final String sql="select CYL_ID,CYL_CODE,CYLINDER_DESC_ENGLISH,CYLINDER_DESC_ARABIC,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS,TYPE_OF_BODY from MOTOR_CYCLINDER_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_CYCLINDER_MASTER where BRANCH_CODE=? and CYL_ID=ac.CYL_ID )and ac.CYL_ID=?";
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=cylId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	public String isVAlidCylinderDate(final String ddmmyyyy,final String cylId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_CYCLINDER_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_CYCLINDER_MASTER where branch_code=? and CYL_ID=ac.CYL_ID )and ac.CYL_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=cylId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	
	public String insertMotorCylinder(final String branchCode, String cylId, final MotorConfigBean bean, final String mode)
	{


		logger.info("&&&&&&!!&&&&&&&&&&&"+branchCode+"&&!!&&&&"+cylId+"&&&!&&&"+bean.getEffDate()+"&&!!&&&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		 
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" cylId: "+cylId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
				logger.info("I am Using This Cylinder Master______");
				final String sql1="SELECT NVL(MAX(CYL_ID)+1,0) FROM MOTOR_CYCLINDER_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				cylId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_CYCLINDER_MASTER(CYL_ID,CYL_CODE,CYLINDER_DESC_ENGLISH,CYLINDER_DESC_ARABIC,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE,TYPE_OF_BODY) VALUES (?,?,?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_CYCLINDER_MASTER WHERE CYL_ID=? AND BRANCH_CODE=?),?,?)";
				String cols[]=new String[10];
				cols[0]=cylId;
				cols[1]=bean.getCylCode();
				cols[2]=bean.getCylNameEng();
				cols[3]=bean.getCylNameArabic();
				cols[4]=bean.getStatus();
				cols[5]=bean.getRemarks();
				cols[6]=cylId;
				cols[7]=branchCode;
				cols[8]=branchCode;
				cols[9]=bean.getTypeOfBodyId();
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				logger.info("I am Using This MOTOR_CYCLINDER_MASTER______");
				final String sql="UPDATE MOTOR_CYCLINDER_MASTER SET CYL_CODE=?,CYLINDER_DESC_ENGLISH=?,CYLINDER_DESC_ARABIC=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=?,TYPE_OF_BODY=? WHERE CYL_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[8];
				cols[0]=bean.getCylCode();
				cols[1]=bean.getCylNameEng();
				cols[2]=bean.getCylNameArabic();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=bean.getTypeOfBodyId();
				cols[6]=cylId;
				cols[7]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	
		
	}
	public String[][] getUplodeFIle(final String branchCode)
	{
		String result[][]=new String[0][0];
		try
		{
		final String sql="select FILE_ID,FILE_TYPE,PRODUCT_ID,case when(status='Y') then 'Active' else 'InActive' end status,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_UPLOADED_FILEMASTER ac where branch_code=? and amend_id = (select max(amend_id) from MOTOR_UPLOADED_FILEMASTER where branch_code=? and FILE_ID=ac.FILE_ID ) order by FILE_ID asc ";
		//final String sql="select BANK_ID,BANK_CODE,BANK_NAME_ENGLISH,BANK_NAME_ARABIC,case when(status='Y') then 'Active' else 'InActive' end status,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_BANK_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_BANK_MASTER where BRANCH_CODE=? and BANK_ID=ac.BANK_ID )and ac.BANK_ID=?";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
		
	}

	
	
	public ArrayList getMotorUplodeFileList(final String[][] result, final String [][] pids) 
	{
		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getMotorUplodeFileList************");
			
			
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
				bean.setFileId(result[i][0] == null ? "" : result[i][0]);
				bean.setFileType(result[i][1] == null ? "" : result[i][1]);
				bean.setProductId(result[i][2] == null ? "" : result[i][2]);
				bean.setStatus(result[i][3] == null ? "" : result[i][3]);
				bean.setRemarks(result[i][4] == null ? "" : result[i][4]);
				bean.setAmendId(result[i][5] == null ? "" : result[i][5]);
				bean.setBranchCode(result[i][6] == null ? "" : result[i][6]);
				for (int j = 0; j < pids.length; j++){
					if((result[i][2] == null ? "" : result[i][2]).equals(pids[j][0])){
						bean.setProductName(pids[j][1] == null ? "" : pids[j][1]);
					}
				}
				list.add(bean);
			}
		}catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	}
	
	public String[][] getVehicleFile(final String branchCode)
	{


		String result[][]=new String[0][0];
		try
		{
		final String sql="select vtype_id,vtype_code,vehicletype_desc_english,vehicletype_desc_arabic,case when(status='Y') then 'Active' else 'InActive' end status from motor_vehicletype_master ac where  branch_code=? and amend_id in (select max(amend_id) from motor_vehicletype_master where branch_code=? and vtype_id=ac.vtype_id)";
		
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	}
	
	public ArrayList getVehicleUsageFileList(final String[][] result) 
	{
		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getVehicleUsageFileList************");
			
			
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
				bean.setVtypeId(result[i][0] == null ? "" : result[i][0]);
				bean.setVehicleType(result[i][1] == null ? "" : result[i][1]);
				bean.setVehicledesc(result[i][2] == null ? "" : result[i][2]);
				bean.setVehiclearabic(result[i][3] == null ? "" : result[i][3]);
				bean.setVestatus(result[i][4] == null ? "" : result[i][4]);
				list.add(bean);
				}
				
			}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	}
	
	
	public String[][] getMotorFileById(final String branchCode, final String fileId) 
	{


		String result[][]=new String[0][0];
		try
		{			
		final String sql="select FILE_ID,FILE_TYPE,PRODUCT_ID,STATUS,REMARKS from MOTOR_UPLOADED_FILEMASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_UPLOADED_FILEMASTER where BRANCH_CODE=? and FILE_ID=ac.FILE_ID )and ac.FILE_ID=?";
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=fileId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
		
	}
	public String insertMotorFile(final String branchCode, String fileId, final MotorConfigBean bean, final String mode) 
	{
		logger.info("&&&&&&&&&&&@@&&&&&&"+branchCode+"&&&@&&&"+fileId+"&&&@&&&"+"&&&@@&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		
		try
		{
			logger.info(MODE+mode+" fileId: "+fileId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
				final String productId=bean.getProductId();
			 
				final String sql1="SELECT NVL(MAX(FILE_ID)+1,0) FROM MOTOR_UPLOADED_FILEMASTER WHERE BRANCH_CODE='"+branchCode+"'";
				fileId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_UPLOADED_FILEMASTER(FILE_ID,FILE_TYPE,PRODUCT_ID,STATUS,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_UPLOADED_FILEMASTER WHERE FILE_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[8];
				cols[0]=fileId;
				logger.info("In DAO"+bean.getFileType());
				cols[1]=bean.getFileType();
				cols[2]=productId;	
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=fileId;
				cols[6]=branchCode;
				cols[7]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				logger.info("I am Using This MOTOR_UPLOADED_FILEMASTER_____");
				final String sql="UPDATE MOTOR_UPLOADED_FILEMASTER SET FILE_ID=?,FILE_TYPE=?,PRODUCT_ID=?,STATUS=?,REMARKS=? WHERE FILE_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=fileId;
				cols[1]=bean.getFileType();
				cols[2]=bean.getProductId();	
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=fileId;
				cols[6]=branchCode;
				
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	
		
	}
	public String[][] getMotorGroup(final String branchCode)
	{


		String result[][]=new String[0][0];
		try
		{
		final String sql="select GROUP_ID,GROUP_DESC_ENGLISH,case when(status='Y') then 'Active' else 'InActive' end status,substr((EFFECTIVE_DATE),1,10) AS Effective_date,EXPIRY_DATE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_GROUP_MASTER ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_GROUP_MASTER where branch_code=? and GROUP_ID=ac.GROUP_ID ) order by GROUP_ID asc ";
		
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
	}
	public ArrayList getMotorGroupList(final String[][] result)
	{

		
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getMotorGroupList************");
			
			                 
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
					bean.setGroupId(result[i][0] == null ? "" : result[i][0]);
					bean.setGroupNameEng(result[i][1] == null ? "" : result[i][1]);
					bean.setStatus(result[i][2] == null ? "" : result[i][2]);
					bean.setEffDate(result[i][3] == null ? "" : result[i][3]);
					bean.setExDate(result[i][4] == null ? "" : result[i][4]);
					bean.setRemarks(result[i][5] == null ? "" : result[i][5]);
					bean.setAmendId(result[i][6] == null ? "" : result[i][6]);
					bean.setBranchCode(result[i][7] == null ? "" : result[i][7]);
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	}
	
	
	
	
	public String[][] getMotorGroupById(final String branchCode, final String groupId) 
	{


		String result[][]=new String[0][0];
		try
		{			
		final String sql="select GROUP_ID,GROUP_DESC_ENGLISH,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS from MOTOR_GROUP_MASTER ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_GROUP_MASTER where BRANCH_CODE=? and GROUP_ID=ac.GROUP_ID )and ac.GROUP_ID=?";
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=groupId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
		
	}
	
	public String isVAlidGroupDate(final String ddmmyyyy,final String groupId,final String branchCode)
 	{
		final String sql=DATEQUERY+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_GROUP_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_GROUP_MASTER where branch_code=? and GROUP_ID=ac.GROUP_ID )and ac.GROUP_ID=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=groupId;
		final String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public String insertMotorGroup(final String branchCode, String groupId, final MotorConfigBean bean,final String mode)
	{

		logger.info("&&&&&&&&dg&&&&&&&&&"+branchCode+"&&gf&&&&"+groupId+"&&&rt&&&"+bean.getEffDate()+"&&&tg&&&"+mode);
		
		//MakeBean bean = new MakeBean();
		String result="";
		
		String date123=null;
		try
		{
			if (!bean.getEffDay().equals("") && !bean.getEffDay().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMonth + "-" + effYear);
			}
			bean.setEffDate(date123);
			logger.info(MODE+mode+" groupId: "+groupId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
				logger.info("I am Using This MOTOR_GROUP_MASTER______");
				final String sql1="SELECT NVL(MAX(GROUP_ID)+1,0) FROM MOTOR_GROUP_MASTER WHERE BRANCH_CODE='"+branchCode+"'";
				groupId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_GROUP_MASTER(GROUP_ID,GROUP_DESC_ENGLISH,STATUS,EFFECTIVE_DATE,REMARKS,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_GROUP_MASTER WHERE GROUP_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[7];
				cols[0]=groupId;
				cols[1]=bean.getGroupNameEng();
				cols[2]=bean.getStatus();
				cols[3]=bean.getRemarks();
				cols[4]=groupId;
				cols[5]=branchCode;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				logger.info("I am Using This MOTOR_GROUP_MASTER______");
				final String sql="UPDATE MOTOR_GROUP_MASTER SET GROUP_DESC_ENGLISH=?,STATUS=?,EFFECTIVE_DATE=TO_DATE('"+bean.getEffDate()+"','dd/MM/yyyy'),REMARKS=? WHERE GROUP_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[5];
				cols[0]=bean.getGroupNameEng();
				cols[1]=bean.getStatus();
				cols[2]=bean.getRemarks();
				cols[3]=groupId;
				cols[4]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				
			}	
		}
		catch(Exception e)
		{
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	}
	public String[][] getMotorVoluntary(final String branchCode)
	{
		String result[][]=new String[0][0];
		try
		{
		final String sql="select VOLUNTARY_ID,VOLUNTARY_NAME,PRODUCT_ID,case when(status='Y') then 'Active' else 'InActive' end status,VOLUNTARY_VALUE,CORE_CODE,REMARKS,AMEND_ID,BRANCH_CODE from MOTOR_VOLUNTARY_EXCESS ac where branch_code=? and amend_id in (select max(amend_id) from MOTOR_VOLUNTARY_EXCESS where branch_code=? and VOLUNTARY_ID=ac.VOLUNTARY_ID ) order by VOLUNTARY_ID asc ";
		
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	public ArrayList getmotorVoluntaryList(  final String[][] result,final String pids[][]) 
	{
		final ArrayList list = new ArrayList();
		try{
			logger.info("**************Entry to List Of getmotorVoluntaryList************");
			
			                           
			for (int i = 0; i < result.length; i++) 
			{
				final MotorConfigBean bean  = new MotorConfigBean();
					bean.setVoluntaryId(result[i][0] == null ? "" : result[i][0]);
					bean.setVoluntaryNAme(result[i][1] == null ? "" : result[i][1]);
					bean.setProductId(result[i][2] == null ? "" : result[i][2]);
					bean.setStatus(result[i][3] == null ? "" : result[i][3]);
					bean.setVoluntaryValue(result[i][4] == null ? "" : result[i][4]);
					bean.setCoreAppCode(result[i][5] == null ? "" : result[i][5]);
					bean.setRemarks(result[i][6] == null ? "" : result[i][6]);
					bean.setAmendId(result[i][7] == null ? "" : result[i][7]);
					bean.setBranchCode(result[i][8] == null ? "" : result[i][8]);
					for (int j = 0; j < pids.length; j++){
						if((result[i][2] == null ? "" : result[i][2]).equals(pids[j][0])){
							bean.setProductName(pids[j][1] == null ? "" : pids[j][1]);
						}
					}
					
					list.add(bean);
			}
		}
		catch(Exception ex){
			logger.fatal(ex);
		}
		logger.info(EXITMESSAGE);

		return list;
   
	
		
	
		
	
		
	}
	public String[][] getVoluntaryById(final String branchCode, final String voluntaryId) 
	{


		String result[][]=new String[0][0];
		try
		{			
		final String sql="select VOLUNTARY_ID,VOLUNTARY_NAME,STATUS,REMARKS,PRODUCT_ID,VOLUNTARY_VALUE,CORE_CODE from MOTOR_VOLUNTARY_EXCESS ac where ac.BRANCH_CODE=? and ac.AMEND_ID in (select max(AMEND_ID) from MOTOR_VOLUNTARY_EXCESS where BRANCH_CODE=? and VOLUNTARY_ID=ac.VOLUNTARY_ID )and ac.VOLUNTARY_ID=?";
		
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=voluntaryId;
		result=runner.multipleSelection(sql,cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	
		
	}
	public String insertMotorVoluntary(final String branchCode, String voluntaryId,final MotorConfigBean bean,final String mode) 
	{

		logger.info("&&&&&&&&dh&&&&&&&&&"+branchCode+"&&&fdg&&&"+voluntaryId+"&&&&dg&&"+bean.getEffDate()+"&&fdg&&&&"+mode);
		
		String result="";
		
		try{
			logger.info(MODE+mode+" voluntaryId: "+voluntaryId);
			if (!EDIT.equalsIgnoreCase(mode))
			{
				logger.info("I am Using This #########______");
				final String sql1="SELECT NVL(MAX(VOLUNTARY_ID)+1,0) FROM MOTOR_VOLUNTARY_EXCESS WHERE BRANCH_CODE='"+branchCode+"'";
				voluntaryId=runner.singleSelection(sql1);
				final String sql="INSERT INTO MOTOR_VOLUNTARY_EXCESS(VOLUNTARY_ID,VOLUNTARY_NAME,STATUS,REMARKS,VOLUNTARY_VALUE,PRODUCT_ID,CORE_CODE,AMEND_ID,BRANCH_CODE) VALUES (?,?,?,?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_GROUP_MASTER WHERE VOLUNTARY_ID=? AND BRANCH_CODE=?),?)";
				String cols[]=new String[10];
				cols[0]=voluntaryId;
				cols[1]=bean.getVoluntaryNAme();
				cols[2]=bean.getStatus();
				cols[3]=bean.getRemarks();
				cols[4]=bean.getVoluntaryValue();
				cols[5]=bean.getProductId();
				cols[6]=bean.getCoreAppCode();
				cols[7]=voluntaryId;
				cols[8]=branchCode;
				cols[9]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;			
			}else{
				logger.info("I am Using This MOTOR_VOLUNTARY_EXCESS_____");
				final String sql="UPDATE MOTOR_VOLUNTARY_EXCESS SET VOLUNTARY_NAME=?,VOLUNTARY_VALUE=?,PRODUCT_ID=?,STATUS=?,REMARKS=? WHERE VOLUNTARY_ID=? AND BRANCH_CODE=?";
				String cols[]=new String[7];
				cols[0]=bean.getVoluntaryNAme();
				cols[1]=bean.getVoluntaryValue();
				cols[2]=bean.getProductId();
				cols[3]=bean.getStatus();
				cols[4]=bean.getRemarks();
				cols[5]=voluntaryId;
				cols[6]=branchCode;
				logger.info(EDITQUERY+sql);
				result=runner.multipleInsertion(sql,cols);
				result = INSERTSUCCESS;	
				}	
		}
		catch(Exception e){
			logger.fatal(e);
			result = INSERTFAIL;
		}
		return result;
	
		
	}
	
	
	public String[][] getProductIds(final String adminPid,final String branchCode)
	{
		final String sql1="select PRODUCT_ID,PRODUCT_NAME FROM PRODUCT_MASTER WHERE PRODUCT_ID IN ("+adminPid+") AND BRANCH_CODE="+branchCode;
		final String [][] pids=runner.multipleSelection(sql1);
		return pids;
	}
	public String getVehiclearabic() {
		return vehiclearabic;
	}
	public void setVehiclearabic(String vehiclearabic) {
		this.vehiclearabic = vehiclearabic;
	}
	public String getVehicledesc() {
		return vehicledesc;
	}
	public void setVehicledesc(String vehicledesc) {
		this.vehicledesc = vehicledesc;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getVestatus() {
		return vestatus;
	}
	public void setVestatus(String vestatus) {
		this.vestatus = vestatus;
	}
	public String getVtypeId() {
		return vtypeId;
	}
	public void setVtypeId(String vtypeId) {
		this.vtypeId = vtypeId;
	}
	public String getPolicytypeName() {
		return policytypeName;
	}
	public void setPolicytypeName(String policytypeName) {
		this.policytypeName = policytypeName;
	}
	
	
	


	
	
	
	

}
