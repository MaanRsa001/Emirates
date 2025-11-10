package com.maan.admin.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.maan.services.util.runner;

public class MotorCoverageDetailsDAO 
{

	private String branchCode;
	private String policyTypeId;
	private String sno;
	private String policyTypeCoverId;
	private String opcover;
	private String typeOfBody;
	private String status;
	private String groups;
	
	private String opcoverId;
	private String typeOfBodyId;
	private String effDay;
	private String effMon;
	private String effYear;
	private String expDay;
	private String expMon;
	private String expYear;
	private String remarks;
	private String rate;
	private String groupId;
	private String description;
	private String defaultselected;
	private String addon;
	private String deletable;
	private String coreappcode;
//	Added by chinna for Grid option
	private String calcType;
	
	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	//End Grid option
	public String getCoreappcode() {
		return coreappcode;
	}

	public void setCoreappcode(String coreappcode) {
		this.coreappcode = coreappcode;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getOpcover() {
		return opcover;
	}

	public void setOpcover(String opcover) {
		this.opcover = opcover;
	}

	public String getPolicyTypeCoverId() {
		return policyTypeCoverId;
	}

	public void setPolicyTypeCoverId(String policyTypeCoverId) {
		this.policyTypeCoverId = policyTypeCoverId;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTypeOfBody() {
		return typeOfBody;
	}

	public void setTypeOfBody(String typeOfBody) {
		this.typeOfBody = typeOfBody;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getPolicyTypeId() {
		return policyTypeId;
	}

	public void setPolicyTypeId(String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}
	
	
	public StringBuffer validateCoverDetails(MotorCoverageDetailsDAO mDAO,StringBuffer errors,String mode)
	{
		if("".equalsIgnoreCase(mDAO.getEffDay()) || "DD".equalsIgnoreCase(mDAO.getEffDay()) || "".equalsIgnoreCase(mDAO.getEffMon()) || "MM".equalsIgnoreCase(mDAO.getEffMon()) || "".equalsIgnoreCase(mDAO.getEffYear()) || "YYYY".equalsIgnoreCase(mDAO.getEffYear())) 
		errors.append("Select Effective Date <br>");
		if("".equalsIgnoreCase(mDAO.getExpDay()) || "DD".equalsIgnoreCase(mDAO.getExpDay()) || "".equalsIgnoreCase(mDAO.getExpMon()) || "MM".equalsIgnoreCase(mDAO.getExpMon()) || "".equalsIgnoreCase(mDAO.getExpYear()) || "YYYY".equalsIgnoreCase(mDAO.getExpYear()))
		errors.append("Select Expiry Date <br>");
		if(errors.length()==0)
		{
			String effectiveDate=mDAO.getEffDay()+"-"+mDAO.getEffMon()+"-"+mDAO.getEffYear();
			String expiryDate=mDAO.getExpDay()+"-"+mDAO.getExpMon()+"-"+mDAO.getExpYear();
			errors=validateDate(effectiveDate,expiryDate,errors);
		}
		if("".equalsIgnoreCase(mDAO.getCoreappcode()))	
		errors.append("Enter CoreAppCode <br>");
		if("".equalsIgnoreCase(mDAO.getRate()) && !"G".equalsIgnoreCase(mDAO.getCalcType()))//Modified	
		errors.append("Enter Rate <br>");
		if("".equalsIgnoreCase(mDAO.getGroupId()))
		errors.append("Select Group <br>");
		if("".equalsIgnoreCase(mDAO.getDescription()))
		errors.append("Enter Description <br>");
		if("".equalsIgnoreCase(mDAO.getStatus()))
		errors.append("Select Status <br>");
		//Added by chinna for Grid Option
		if("".equalsIgnoreCase(mDAO.getCalcType()))
			errors.append("Select Calculation Type <br>");
		//End
		
		if("".equalsIgnoreCase(mDAO.getDefaultselected()) && "".equalsIgnoreCase(mDAO.getAddon()))
		{
			errors.append("Select Default Selected <br>");
		}else
		{
			if("Y".equalsIgnoreCase(mDAO.getDefaultselected()))
			{
				mDAO.setAddon("N");
			}
			if("Y".equalsIgnoreCase(mDAO.getAddon()))
			{
				mDAO.setDefaultselected("N");
				mDAO.setDeletable("N");
			}
		}
		if(errors.length()==0)
		{
			if(coverageDataExistence(mDAO,mode))
			{
				errors.append("Record Already Exists <br>");
			}
		}
		
		
		return errors;
	}
	
	private StringBuffer validateDate(String startDate,String endDate,StringBuffer error)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date start,end,today;
		today=new Date();
		today.setHours(00);
		today.setMinutes(00);
		today.setSeconds(00);
		
		try{
		today=dateFormat.parse(dateFormat.format(today));
		start=dateFormat.parse(startDate);
		end=dateFormat.parse(endDate);
		
		if(start.after(end))
		error.append("Effective Date Should Be Lesser Than Expiry Date <br>");
		if(end.before(today))
		error.append("Expiry Date Should Be Greater Than Current Date <br>");
		}catch(Exception e){System.out.println("validateDate => "+e);}

		return error;
	}
	
	public void getPolicyCoverDetailData(String policyTypeCoverId,String branchCode)
	{
		String date,dateTemp[];
		String result[][]=new String[0][0];
		String arg[]=new String[2];
		arg[0]=policyTypeCoverId;
		arg[1]=branchCode;//Calc_type is added in query and all
		String query="SELECT POLICYTYPE_COVERID,POLICYTYPE_ID,POLICYCOVER_ID,TYPE_OF_BODY,COREAPP_CODE,STATUS,TO_CHAR(EFFECTIVE_DATE,'dd-MM-yyyy') EFFECTIVE_DATE,TO_CHAR(EXPIRY_DATE,'dd-MM-yyyy') EXPIRY_DATE,REMARKS,RATE,GROUP_ID,DESCRIPTION,IS_SELECTED,IS_ADDON,DELETABLE,CALC_TYPE FROM MOTOR_POLICY_COVER_DETAIL MPD WHERE POLICYTYPE_COVERID=? AND BRANCH_CODE=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_COVERID=MPD.POLICYTYPE_COVERID)";
		result=runner.multipleSelection(query,arg);
		
		if(result.length>0)
		{
			policyTypeCoverId=result[0][0]==null?"":result[0][0];
			policyTypeId=result[0][1]==null?"":result[0][1];
			opcoverId=result[0][2]==null?"":result[0][2];
			typeOfBodyId=result[0][3]==null?"":result[0][3];
			coreappcode=result[0][4]==null?"":result[0][4];
			status=result[0][5]==null?"":result[0][5];
			date=result[0][6]==null?"":result[0][6];
			
			if(date!=null && date.length()>0)	
			{
				dateTemp=date.split("-");
				if(dateTemp.length==3)
				{	
					effDay=dateTemp[0];
					effMon=dateTemp[1];	
					effYear=dateTemp[2];
				}
			}
			else
			{
				effDay="";
				effMon="";	
				effYear="";
			}
			date=result[0][7]==null?"":result[0][7];
			
			if(date!=null && date.length()>0)	
			{
				dateTemp=date.split("-");
				if(dateTemp.length==3)
				{	
					expDay=dateTemp[0];
					expMon=dateTemp[1];	
					expYear=dateTemp[2];
				}
			}
			else
			{
				expDay="";
				expMon="";	
				expYear="";
			}
			
			remarks=result[0][8]==null?"":result[0][8];
			rate=result[0][9]==null?"":result[0][9];
			groupId=result[0][10]==null?"":result[0][10];
			description=result[0][11]==null?"":result[0][11];
			defaultselected=result[0][12]==null?"":result[0][12];
			addon=result[0][13]==null?"":result[0][13];
			deletable=result[0][14]==null?"":result[0][14];
			calcType=result[0][15]==null?"":result[0][15];
		}
		
	}
	
	public boolean coverageDataExistence(MotorCoverageDetailsDAO mDAO,String mode)
	{
		boolean result=false;
		int count=0;
		//String query="SELECT COUNT(*) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_ID=? AND POLICYCOVER_ID=? AND TYPE_OF_BODY=? AND COREAPP_CODE=? AND STATUS=? AND EFFECTIVE_DATE=? AND EXPIRY_DATE=? AND REMARKS=? AND RATE=? AND GROUP_ID=? AND DESCRIPTION=? AND IS_SELECTED=? AND IS_ADDON=? AND DELETABLE=? AND POLICYTYPE_COVERID NOT IN (?)";
		String query="SELECT COUNT(*) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_ID=? AND POLICYCOVER_ID=? AND TYPE_OF_BODY=? AND COREAPP_CODE=? AND STATUS=? AND EFFECTIVE_DATE=to_date(?,'dd-mm-yyyy') AND EXPIRY_DATE=to_date(?,'dd-mm-yyyy') AND REMARKS=? AND RATE=? AND GROUP_ID=? AND DESCRIPTION=? AND IS_SELECTED=? AND IS_ADDON=? AND DELETABLE=? AND POLICYTYPE_COVERID NOT IN (?)";
		String arg[] = new String[15];
		
		arg[0]=mDAO.getPolicyTypeId();
		arg[1]=mDAO.getOpcoverId();
		arg[2]=mDAO.getTypeOfBodyId();
		arg[3]=mDAO.getCoreappcode();
		arg[4]=mDAO.getStatus();
		arg[5]=mDAO.getEffDay()+"-"+mDAO.getEffMon()+"-"+mDAO.getEffYear();
		arg[6]=mDAO.getExpDay()+"-"+mDAO.getExpMon()+"-"+mDAO.getExpYear();
		arg[7]=mDAO.getRemarks();	
		arg[8]=mDAO.getRate();
		arg[9]=mDAO.getGroupId();
		arg[10]=mDAO.getDescription();
		arg[11]=mDAO.getDefaultselected();
		arg[12]=mDAO.getAddon();
		arg[13]=mDAO.getDeletable();
		
		if("New".equalsIgnoreCase(mode))
		arg[14]="0";
		else if("Edit".equalsIgnoreCase(mode))
		arg[14]=mDAO.getPolicyTypeCoverId();
		
		count=Integer.parseInt(runner.singleSelection(query, arg));
		if(count>0)
		result=true;
			
		return result;
	}
	
	
	public void insertCoverageDetails(MotorCoverageDetailsDAO mDAO,String mode)
	{
		String query="";
		String arg[]=new String[0];
		//System.out.println("Mode: "+mode);
		
		if("New".equalsIgnoreCase(mode))
		{	
		arg=new String[16];//Modified
		arg[0]=mDAO.getPolicyTypeId();
		arg[1]=mDAO.getOpcoverId();
		arg[2]=mDAO.getTypeOfBodyId();
		arg[3]=mDAO.getCoreappcode();
		arg[4]=mDAO.getStatus();
		arg[5]=mDAO.getEffDay()+"-"+mDAO.getEffMon()+"-"+mDAO.getEffYear();
		arg[6]=mDAO.getExpDay()+"-"+mDAO.getExpMon()+"-"+mDAO.getExpYear();
		arg[7]=mDAO.getRemarks();
		arg[8]=mDAO.getRate();
		arg[9]=mDAO.getGroupId();
		arg[10]=mDAO.getDescription();
		arg[11]=mDAO.getDefaultselected();
		arg[12]=mDAO.getAddon();
		arg[13]=mDAO.getDeletable();
		arg[14]=mDAO.getBranchCode();
		arg[15]=mDAO.getCalcType();//Added by chinna
//		query="INSERT INTO MOTOR_POLICY_COVER_DETAIL(POLICYTYPE_COVERID,POLICYTYPE_ID,POLICYCOVER_ID,TYPE_OF_BODY,COREAPP_CODE,STATUS,EFFECTIVE_DATE,EXPIRY_DATE,REMARKS,RATE,GROUP_ID,DESCRIPTION,IS_SELECTED,IS_ADDON,DELETABLE,BRANCH_CODE,AMEND_ID) VALUES ((SELECT NVL(MAX(POLICYTYPE_COVERID)+1,0) FROM MOTOR_POLICY_COVER_DETAIL),?,?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),TO_DATE(?,'dd-MM-yyyy'),?,?,?,?,?,?,?,?,0)";
		query="INSERT INTO MOTOR_POLICY_COVER_DETAIL(POLICYTYPE_COVERID,POLICYTYPE_ID,POLICYCOVER_ID,TYPE_OF_BODY,COREAPP_CODE,STATUS,EFFECTIVE_DATE,EXPIRY_DATE,REMARKS,RATE,GROUP_ID,DESCRIPTION,IS_SELECTED,IS_ADDON,DELETABLE,BRANCH_CODE,CALC_TYPE,AMEND_ID) VALUES ((SELECT NVL(MAX(POLICYTYPE_COVERID)+1,0) FROM MOTOR_POLICY_COVER_DETAIL),?,?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),TO_DATE(?,'dd-MM-yyyy'),?,?,?,?,?,?,?,?,?,0)";
		}
		else if("Edit".equalsIgnoreCase(mode))
		{
		arg=new String[18];//Modified
		
		arg[0]=mDAO.getPolicyTypeCoverId();
		arg[1]=mDAO.getPolicyTypeId();
		arg[2]=mDAO.getOpcoverId();
		arg[3]=mDAO.getTypeOfBodyId();
		arg[4]=mDAO.getCoreappcode();
		arg[5]=mDAO.getStatus();
		arg[6]=mDAO.getEffDay()+"-"+mDAO.getEffMon()+"-"+mDAO.getEffYear();
		arg[7]=mDAO.getExpDay()+"-"+mDAO.getExpMon()+"-"+mDAO.getExpYear();
		arg[8]=mDAO.getRemarks();
		arg[9]=mDAO.getRate();
		arg[10]=mDAO.getGroupId();
		arg[11]=mDAO.getDescription();
		arg[12]=mDAO.getDefaultselected();
		arg[13]=mDAO.getAddon();
		arg[14]=mDAO.getDeletable();
		arg[15]=mDAO.getBranchCode();
		arg[16]=mDAO.getCalcType();//Added
		arg[17]=mDAO.getPolicyTypeCoverId();
		
		
//		query="INSERT INTO MOTOR_POLICY_COVER_DETAIL(POLICYTYPE_COVERID,POLICYTYPE_ID,POLICYCOVER_ID,TYPE_OF_BODY,COREAPP_CODE,STATUS,EFFECTIVE_DATE,EXPIRY_DATE,REMARKS,RATE,GROUP_ID,DESCRIPTION,IS_SELECTED,IS_ADDON,DELETABLE,BRANCH_CODE,AMEND_ID) VALUES (?,?,?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),TO_DATE(?,'dd-MM-yyyy'),?,?,?,?,?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_COVERID=?) )";
		query="INSERT INTO MOTOR_POLICY_COVER_DETAIL(POLICYTYPE_COVERID,POLICYTYPE_ID,POLICYCOVER_ID,TYPE_OF_BODY,COREAPP_CODE,STATUS,EFFECTIVE_DATE,EXPIRY_DATE,REMARKS,RATE,GROUP_ID,DESCRIPTION,IS_SELECTED,IS_ADDON,DELETABLE,BRANCH_CODE,CALC_TYPE,AMEND_ID) VALUES (?,?,?,?,?,?,TO_DATE(?,'dd-MM-yyyy'),TO_DATE(?,'dd-MM-yyyy'),?,?,?,?,?,?,?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_COVERID=?) )";
		}
		
		/*System.out.println("Query: "+query);
		for(int i=0;i<arg.length;i++)
		{
			System.out.println("arg["+i+"]: "+arg[i]);
		}
		*/
		runner.multipleInsertion(query, arg);
	}
	
	public String[][] getPolicyTypeMaster(String branchCode)
	{
		String[][] result=new String[0][0];
		String query="SELECT MP.POLICYTYPE_ID,MP.POLICYTYPE_NAME FROM MOTOR_POLICYTYPE_MASTER MP WHERE MP.STATUS='Y' AND MP.AMEND_ID=(SELECT (MAX(AMEND_ID)) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=MP.POLICYTYPE_ID) AND BRANCH_CODE='"+branchCode+"'";
		result=runner.multipleSelection(query);
		return result;
	}
	
	public String[][] getOpCoverMaster(String branchCode)
	{
		String[][] result=new String[0][0];
		String query="SELECT OPCOVER_ID,OPCOVER_DESC_ENGLISH FROM MOTOR_OPCOVER_MASTER MO WHERE STATUS='Y' AND BRANCH_CODE='"+branchCode+"' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_OPCOVER_MASTER WHERE OPCOVER_ID=MO.OPCOVER_ID)";
		result=runner.multipleSelection(query);
		return result;
	}
	
	public String[][] getTypeOfBodyMaster(String branchCode)
	{
		String[][] result=new String[0][0];
		String query="SELECT TYPE_OF_BODY_ID,TYPE_OF_BODY_NAME FROM MOTOR_TYPE_BODY_MASTER MB WHERE STATUS='Y' AND BRANCH_CODE='"+branchCode+"' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MB.TYPE_OF_BODY_ID AND BRANCH_CODE=MB.BRANCH_CODE)";
		result=runner.multipleSelection(query);
		return result;
	}
	
	
	public String[][] getGroupMaster(String branchCode)
	{
		String[][] result=new String[0][0];
		String query="SELECT GROUP_ID,GROUP_DESC_ENGLISH FROM MOTOR_GROUP_MASTER MG WHERE STATUS='Y' AND BRANCH_CODE='"+branchCode+"' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_GROUP_MASTER WHERE GROUP_ID=MG.GROUP_ID)";
		result=runner.multipleSelection(query);
		return result;
	}
	
	public List getPolicyCoverDetailList(String policyTypeId,String typeOfBodyId,String branchCode)
	{
		List finalList=new ArrayList();
		String[][] result=new String[0][0];
		//String query="SELECT ROWNUM,a.* FROM (SELECT MD.POLICYTYPE_COVERID,(SELECT OPCOVER_DESC_ENGLISH FROM MOTOR_OPCOVER_MASTER WHERE OPCOVER_ID=MD.POLICYCOVER_ID AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_OPCOVER_MASTER WHERE OPCOVER_ID=MD.POLICYCOVER_ID)) OPCOVER_DESC_ENGLISH,(SELECT TYPE_OF_BODY_NAME FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY)) TYPE_OF_BODY_NAME,CASE WHEN (MD.STATUS='Y') THEN 'Active' ELSE 'DeActive' END STATUS,MD.GROUP_ID FROM MOTOR_POLICY_COVER_DETAIL MD WHERE MD.STATUS='Y' AND MD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_COVERID=MD.POLICYTYPE_COVERID) AND MD.POLICYTYPE_ID=? AND MD.TYPE_OF_BODY=? AND MD.BRANCH_CODE=?  ORDER BY MD.POLICYTYPE_COVERID ASC) a";
		//String query="SELECT ROWNUM,a.*,OP.OPCOVER_DESC_ENGLISH,OP.OPCOVER_ID FROM (SELECT MD.POLICYTYPE_COVERID,MD.POLICYCOVER_ID,(SELECT TYPE_OF_BODY_NAME FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY)) TYPE_OF_BODY_NAME,CASE WHEN (MD.STATUS='Y') THEN 'Active' ELSE 'DeActive' END STATUS,MD.GROUP_ID FROM MOTOR_POLICY_COVER_DETAIL MD WHERE MD.STATUS='Y' AND MD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICY_COVER_DETAIL WHERE POLICYTYPE_COVERID=MD.POLICYTYPE_COVERID) AND MD.POLICYTYPE_ID=? AND MD.TYPE_OF_BODY=? AND MD.BRANCH_CODE=?  ORDER BY MD.POLICYTYPE_COVERID ASC) a RIGHT JOIN MOTOR_OPCOVER_MASTER OP ON(a.POLICYCOVER_ID=OP.OPCOVER_ID)";
		String query="SELECT ROWNUM,a.*,OP.OPCOVER_DESC_ENGLISH,OP.OPCOVER_ID FROM (SELECT MD.POLICYTYPE_COVERID,MD.POLICYCOVER_ID,(SELECT TYPE_OF_BODY_NAME FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_TYPE_BODY_MASTER WHERE TYPE_OF_BODY_ID=MD.TYPE_OF_BODY AND BRANCH_CODE=MD.BRANCH_CODE) AND BRANCH_CODE=MD.BRANCH_CODE ) TYPE_OF_BODY_NAME, CASE WHEN (MD.STATUS='Y') THEN 'Active' ELSE 'DeActive' END STATUS,(SELECT DISTINCT GROUP_DESC_ENGLISH FROM MOTOR_GROUP_MASTER WHERE GROUP_ID=MD.GROUP_ID AND STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_GROUP_MASTER WHERE GROUP_ID=MD.GROUP_ID AND BRANCH_CODE=MD.BRANCH_CODE) AND BRANCH_CODE=MD.BRANCH_CODE) FROM MOTOR_POLICY_COVER_DETAIL MD WHERE MD.STATUS='Y' AND MD.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_POLICY_COVER_DETAIL WHERE MD.POLICYTYPE_ID = POLICYTYPE_ID AND MD.POLICYCOVER_ID = POLICYCOVER_ID AND MD.TYPE_OF_BODY = TYPE_OF_BODY AND MD.BRANCH_CODE = BRANCH_CODE AND POLICYTYPE_COVERID=MD.POLICYTYPE_COVERID) AND MD.POLICYTYPE_ID=? AND MD.TYPE_OF_BODY=? AND MD.BRANCH_CODE=?  ORDER BY MD.POLICYTYPE_COVERID ASC) a RIGHT JOIN (SELECT OPCOVER_ID,OPCOVER_DESC_ENGLISH,BRANCH_CODE FROM MOTOR_OPCOVER_MASTER MO WHERE STATUS='Y' AND BRANCH_CODE=? AND AMEND_ID||'-'||OPCOVER_ID||'-'||BRANCH_CODE = (SELECT MAX(AMEND_ID)||'-'||OPCOVER_ID||'-'||BRANCH_CODE FROM MOTOR_OPCOVER_MASTER WHERE OPCOVER_ID=MO.OPCOVER_ID AND BRANCH_CODE=MO.BRANCH_CODE GROUP BY OPCOVER_ID,BRANCH_CODE)) OP ON(a.POLICYCOVER_ID=OP.OPCOVER_ID) ORDER BY OP.OPCOVER_ID";
		
		String arg[]=new String[4];
		arg[0]=policyTypeId;
		arg[1]=typeOfBodyId;
		arg[2]=branchCode;
		arg[3]=branchCode;
		result=runner.multipleSelection(query,arg);
		
		for(int i=0;i<result.length;i++)
		{
			MotorCoverageDetailsDAO mDao=new MotorCoverageDetailsDAO();
			mDao.setSno(result[i][0]);
			mDao.setPolicyTypeCoverId(result[i][1]==null?"0":result[i][1]);
			mDao.setOpcover(result[i][6]);
			mDao.setOpcoverId(result[i][7]);
			mDao.setTypeOfBody(result[i][3]);
			mDao.setStatus(result[i][4]);
			mDao.setGroups(result[i][5]);
			finalList.add(mDao);
		}
		
		return finalList;
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		this.addon = addon;
	}

	public String getDefaultselected() {
		return defaultselected;
	}

	public void setDefaultselected(String defaultselected) {
		this.defaultselected = defaultselected;
	}

	public String getDeletable() {
		return deletable;
	}

	public void setDeletable(String deletable) {
		this.deletable = deletable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEffDay() {
		return effDay;
	}

	public void setEffDay(String effDay) {
		this.effDay = effDay;
	}

	public String getEffMon() {
		return effMon;
	}

	public void setEffMon(String effMon) {
		this.effMon = effMon;
	}

	public String getEffYear() {
		return effYear;
	}

	public void setEffYear(String effYear) {
		this.effYear = effYear;
	}

	public String getExpDay() {
		return expDay;
	}

	public void setExpDay(String expDay) {
		this.expDay = expDay;
	}

	public String getExpMon() {
		return expMon;
	}

	public void setExpMon(String expMon) {
		this.expMon = expMon;
	}

	public String getExpYear() {
		return expYear;
	}

	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getOpcoverId() {
		return opcoverId;
	}

	public void setOpcoverId(String opcoverId) {
		this.opcoverId = opcoverId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTypeOfBodyId() {
		return typeOfBodyId;
	}

	public void setTypeOfBodyId(String typeOfBodyId) {
		this.typeOfBodyId = typeOfBodyId;
	}
	
}
