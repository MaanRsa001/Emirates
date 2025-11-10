package com.maan.admin.DAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.apache.logging.log4j.Logger;

import com.maan.common.LogUtil;
import com.maan.services.util.runner;


public class MotorBodyCreation {
	private static final Logger logger = LogUtil.getLogger(MotorBodyCreation.class);
	
	private String reqFrom;
	private String typeName;
	private String BranchCode="";
	private String ProductId="";
	private String appCode="";
	private String body_name_eng="";
	private String body_name_arab="";
	private String years="";
	private String liability="";
	private String status="";
	private String effDay="";
	private String effMon="";
	private String effYear="";
	private String thirdCoreAppCode="";
	private String seatingCyll="";
	private String thirdRate="";
	private String typeId="";
	private String startage="";
	private String endage="";
	private String licperiod="";
	private String comboName="";
	private String make="";
	private String modelname="";
	private String modelid="";
    private String agencyRepairYear="";
	private String rate="";
	private String startsum="";
	private String seatingstart="";
	private String seatingend="";
	private String minpremium="";
	private String endsum="";
	private String licendperiod="";
	private String makeId="";
	private String modelName="";
	private String coreCode="";
	private String modelNameArabic="";
	private String bodyId="";
	private String brokerId="";
	private String vehicleId;
	private String policyTypeId="";
	
	public String getPolicyTypeId() {
		return policyTypeId;
	}
	public void setPolicyTypeId(String policyTypeId) {
		this.policyTypeId = policyTypeId;
	}
	public String getBodyId() {
		return bodyId;
	}
	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}
	public String getCoreCode() {
		return coreCode;
	}
	public void setCoreCode(String coreCode) {
		this.coreCode = coreCode;
	}
	public String getMakeId() {
		return makeId;
	}
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getModelNameArabic() {
		return modelNameArabic;
	}
	public void setModelNameArabic(String modelNameArabic) {
		this.modelNameArabic = modelNameArabic;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getBody_name_arab() {
		return body_name_arab;
	}
	public void setBody_name_arab(String body_name_arab) {
		this.body_name_arab = body_name_arab;
	}
	public String getBody_name_eng() {
		return body_name_eng;
	}
	public void setBody_name_eng(String body_name_eng) {
		this.body_name_eng = body_name_eng;
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
	public String getLiability() {
		return liability;
	}
	public void setLiability(String liability) {
		this.liability = liability;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getBranchCode() {
		return BranchCode;
	}
	public void setBranchCode(String branchCode) {
		BranchCode = branchCode;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getSeatingCyll() {
		return seatingCyll;
	}
	public void setSeatingCyll(String seatingCyll) {
		this.seatingCyll = seatingCyll;
	}
	public String getThirdCoreAppCode() {
		return thirdCoreAppCode;
	}
	public void setThirdCoreAppCode(String thirdCoreAppCode) {
		this.thirdCoreAppCode = thirdCoreAppCode;
	}
	public String getThirdRate() {
		return thirdRate;
	}
	public void setThirdRate(String thirdRate) {
		this.thirdRate = thirdRate;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getComboName() {
		return comboName;
	}
	public void setComboName(String comboName) {
		this.comboName = comboName;
	}
	public String getEndage() {
		System.out.println("end age in bean"+endage);
		return endage;
	}
	public void setEndage(String endage) {
		this.endage = endage;
		System.out.println("end age in bean setter"+this.endage);
	}
	public String getLicperiod() {
		return licperiod;
	}
	public void setLicperiod(String licperiod) {
		this.licperiod = licperiod;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModelname() {
		return modelname;
	}
	public void setModelname(String modelname) {
		this.modelname = modelname;
	}
	public String getStartage() {
		return startage;
	}
	public void setStartage(String startage) {
		this.startage = startage;
	}
	public String getAgencyRepairYear() {
		return agencyRepairYear;
	}
	public void setAgencyRepairYear(String agencyRepairYear) {
		this.agencyRepairYear = agencyRepairYear;
	}
	public String getMinpremium() {
		return minpremium;
	}
	public void setMinpremium(String minpremium) {
		this.minpremium = minpremium;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSeatingend() {
		return seatingend;
	}
	public void setSeatingend(String seatingend) {
		this.seatingend = seatingend;
	}
	public String getSeatingstart() {
		return seatingstart;
	}
	public void setSeatingstart(String seatingstart) {
		this.seatingstart = seatingstart;
	}
	public String getStartsum() {
		return startsum;
	}
	public void setStartsum(String startsum) {
		this.startsum = startsum;
	}
	public String getEndsum() {
		return endsum;
	}
	public void setEndsum(String endsum) {
		this.endsum = endsum;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getLicendperiod() {
		return licendperiod;
	}
	public void setLicendperiod(String licendperiod) {
		this.licendperiod = licendperiod;
	}

	
	public String[][] getMotorMake(String branchCode)
	{
		String result[][]=new String[0][0];
		try
		{
			String sql="select MAKE_ID,MAKE_NAME,MAKE_NAME_ARABIC,CORE_CODE,(CASE WHEN STATUS='Y' THEN 'Active' ELSE 'InActive' END) from MOTOR_MAKE_MASTER mm where branch_code=?  and amend_id = (select max(amend_id) from MOTOR_MAKE_MASTER where branch_code=mm.branch_code and make_id=mm.make_id ) order by MAKE_NAME asc";
			String cols[]=new String[1];
			cols[0]=branchCode;
			result=runner.multipleSelection(sql,cols);		
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList getMakeList( String[][] value)
    {
		ArrayList list = new ArrayList();
		try{
			System.out.println("Entry to List");
			
			for (int i = 0; i < value.length; i++) {
					MakeBean bean = new MakeBean();
					bean.setMakeId(value[i][0] == null ? "" : value[i][0]);
					bean.setMakeName(value[i][1] == null ? "" : value[i][1]);
					bean.setMakeArabic(value[i][2] == null ? "": value[i][2]);
					bean.setCoreCode(value[i][3] == null ? "" : value[i][3]);
					bean.setStatus(value[i][4] == null ? "" : value[i][4]);
					list.add(bean);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println("Exit from List");

		return list;
   }
	
	public String[][] getMakeEdit(String branchCode,String makeId)
	{
		String result[][]=new String[0][0];
		try
		{
		String sql="select MAKE_ID,MAKE_NAME,MAKE_NAME_ARABIC,CORE_CODE,STATUS,to_char(EFFECTIVE_DATE,'YYYY'),to_char(EFFECTIVE_DATE,'MM'),to_char(EFFECTIVE_DATE,'DD'),REMARKS,REFERRAL_STATUS from MOTOR_MAKE_MASTER mm where branch_code=? and make_id=? and amend_id = (select max(amend_id) from MOTOR_MAKE_MASTER where branch_code=mm.branch_code and make_id=mm.make_id)";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=makeId;
		result=runner.multipleSelection(sql, cols);
		 	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String getTypeOfMake(final String branchCode,final String makeId)
	{

		 String result="";
		try
		{
		final String sql="select MAKE_NAME  from MOTOR_MAKE_MASTER a where BRANCH_CODE=? and MAKE_ID=? and AMEND_ID=(select max(AMEND_ID) from MOTOR_MAKE_MASTER where BRANCH_CODE=a.BRANCH_CODE and MAKE_ID=a.MAKE_ID )";
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=makeId;
		 result=runner.singleSelection(sql, cols);
		}
		catch(Exception e)
		{
			logger.fatal(e);
		}
		return result;
	}
	
	
	
	public String isVAlidMakeDate(String ddmmyyyy,String makeId2,String branchCode)
 	{
		String sql="SELECT distinct (CASE WHEN (TO_DATE('"+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_MAKE_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_MAKE_MASTER where branch_code=? and make_id=ac.make_id )and ac.make_id=?";
		String cols[]=new String[3];
		cols[0]=branchCode;
		cols[1]=branchCode;
		cols[2]=makeId2;
		String result=runner.singleSelection(sql, cols);
		
		return result;
		
 	}
	
	public String[][] getModelDisplay(String branchCode,String makeId)
	{
		String result[][]=new String[0][0];
		try
		{
//			String sql="select MODEL_ID,MAKE_ID,MODEL_NAME,MODEL_NAME_ARABIC,CORE_CODE,to_char(EFFECTIVE_DATE,'YYYY') year,to_char(EFFECTIVE_DATE,'MM') month,to_char(EFFECTIVE_DATE,'DD') day,(CASE WHEN STATUS='Y' THEN 'Active' ELSE 'InActive' END),BODY_ID,REFERRAL_STATUS,CATEGORY_ID,REMARKS  from MOTOR_MODEL_MASTER mm where  BRANCH_CODE=?  and MAKE_ID=? and STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_MODEL_MASTER where  BRANCH_CODE=mm.branch_code  and MAKE_ID=mm.make_id and model_id=mm.model_id)";
			String sql="select distinct a.model_id, a.make_id, b.make_name, a.model_name,a.MODEL_NAME_ARABIC, a.CORE_CODE,to_char(a.EFFECTIVE_DATE,'YYYY') year,to_char(a.EFFECTIVE_DATE,'MM') month,to_char(a.EFFECTIVE_DATE,'DD') day,(CASE WHEN a.STATUS='Y' THEN 'Active' ELSE 'InActive' END),a.BODY_ID,a.REFERRAL_STATUS,a.CATEGORY_ID,a.REMARKS  from MOTOR_MODEL_MASTER a, MOTOR_MAKE_MASTER b where  a.BRANCH_CODE=?  and a.MAKE_ID=? and b.make_id=a.make_id AND a.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_MODEL_MASTER where  BRANCH_CODE=a.branch_code  and MAKE_ID=a.make_id and model_id=a.model_id) AND b.AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_MAKE_MASTER where  BRANCH_CODE=b.branch_code  and MAKE_ID=b.make_id)";
			String cols[]=new String[2];
			cols[0]=branchCode;
			cols[1]=makeId;
			result=runner.multipleSelection(sql, cols);
		 	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	

	public ArrayList getModelEditList( String[][] value)
	{
		ArrayList list = new ArrayList();
		MakeBean bean=new MakeBean();
		try{
			System.out.println("Entry to List");
			for (int i = 0; i < value.length; i++) {
					bean = new MakeBean();
					bean.setModelId(value[i][0] == null ? "" : value[i][0]);
					bean.setMakeId(value[i][1] == null ? "" : value[i][1]);
					bean.setMakeName(value[i][2] == null ? "" : value[i][2]);
					bean.setModelName(value[i][3] == null ? "" : value[i][3]);
					bean.setModelArabic(value[i][4] == null ? "": value[i][4]);
					bean.setCoreCode(value[i][5] == null ? "" : value[i][5]);
					bean.setEffYear(value[i][6] == null ? "" : value[i][6]);
					bean.setEffMonth(value[i][7] == null ? "" : value[i][7]);
					bean.setEffDate(value[i][8] == null ? "" : value[i][8]);
					bean.setStatus(value[i][9] == null ? "" : value[i][9]);
					bean.setBodyId(value[i][10] == null ? "" : value[i][10]);
					bean.setReferalStatus(value[i][11] == null ? "" : value[i][11]);
					bean.setCategoryId(value[i][12] == null ? "" : value[i][12]);
					bean.setRemarks(value[i][13] == null ? "" : value[i][13]);
					list.add(bean);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	System.out.println("Exit from List");
	System.out.println("Size OF List:--->"+list.size());
	return list;
	}
	
	public String[][] getBodyId(String branchCode)
	{
		String result[][]=new String[0][0];
		try
		{
		String sql="SELECT TYPE_OF_BODY_ID,TYPE_OF_BODY_NAME FROM MOTOR_TYPE_BODY_MASTER mtb WHERE STATUS='Y' AND BRANCH_CODE=? AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_TYPE_BODY_MASTER WHERE BRANCH_CODE=mtb.branch_code and type_of_body_id=mtb.type_of_body_id)";
		String cols[]=new String[1];
		cols[0]=branchCode;
		result=runner.multipleSelection(sql,cols);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public String[][] getModelEdit(String branchCode,String makeId, String modelId)
	{
		String result[][]=new String[0][0];
		try
		{
			String sql="select MODEL_ID,MAKE_ID,MODEL_NAME,MODEL_NAME_ARABIC,CORE_CODE,to_char(EFFECTIVE_DATE,'YYYY') year,to_char(EFFECTIVE_DATE,'MM') month,to_char(EFFECTIVE_DATE,'DD') day,STATUS,BODY_ID,REFERRAL_STATUS,CATEGORY_ID,REMARKS  from MOTOR_MODEL_MASTER mm where  BRANCH_CODE=?  and MAKE_ID=? and MODEL_ID=? and STATUS='Y' AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_MODEL_MASTER where  BRANCH_CODE=mm.branch_code  and MAKE_ID=mm.make_id and MODEL_ID=mm.model_id)";
			String cols[]=new String[3];
			cols[0]=branchCode;
			cols[1]=makeId;
			cols[2]=modelId;
			
			result=runner.multipleSelection(sql, cols);
		 	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String[][] getPolicyTypeDetails(String branchCode){
		String[][] result;
		String query="SELECT POLICYTYPE_ID,POLICYTYPE_NAME FROM MOTOR_POLICYTYPE_MASTER MR WHERE STATUS='Y' AND AMEND_ID= (SELECT MAX(AMEND_ID) FROM MOTOR_POLICYTYPE_MASTER WHERE POLICYTYPE_ID=MR.POLICYTYPE_ID AND BRANCH_CODE=MR.BRANCH_CODE) AND POLICYTYPE_ID NOT IN(2) AND BRANCH_CODE='"+branchCode+"'";
		result=runner.multipleSelection(query);
		System.out.println("Query: "+query);
		return result;
	}
	
	
	public String[][] getBrokerMasterDetails(String branchCode)
	{
		String[][] result;
		String query="select agency_code,username from login_master where upper(usertype)='BROKER' and status='Y' and agency_code in (select agency_code from broker_company_master where branch_code='"+branchCode+"' and status='Y')";
		result=runner.multipleSelection(query);
		System.out.println("Query: "+query);
		return result;
	}
	
	public String[][] getVehicleMasterDetails(String branchCode)
	{
		String[][] result;
		String query="select VTYPE_ID,VEHICLETYPE_DESC_ENGLISH from MOTOR_VEHICLETYPE_MASTER where status='Y' and branch_code='"+branchCode+"'";
		result=runner.multipleSelection(query);
		System.out.println("Query: "+query);
		return result;
		
	}
	                
	public String isertOrUpdateBodyFormData(String mode)
	{
		
	    try
		{
	    	logger.info("method isertOrUpdateBodyFormData start "+mode);
			String date123 = "";
			String args[]=new String[11];
			if (!effDay.equals("") && !effDay.equals("0")&& !effMon.equals("") && !effMon.equals("0")&& !effYear.equals("") && !effMon.equals("0")) 
			{
				date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMon + "-" + effYear);
			}
			args[0]=BranchCode;
			args[1]=ProductId;
			if("edit".equalsIgnoreCase(mode))
			{	
			args[2]=typeId;
			}
			else
			{
				System.out.println("select nvl(max(type_of_body_id)+1,1) from MOTOR_TYPE_BODY_MASTER where product_id="+ProductId+" and branch_code="+BranchCode+" ");
				args[2]=runner.singleSelection("select nvl(max(type_of_body_id)+1,1) from MOTOR_TYPE_BODY_MASTER where product_id="+ProductId+" and branch_code="+BranchCode+" ");
			}
			logger.info("type_of_body_id "+args[2]);
			args[3]=appCode;
			if("edit".equalsIgnoreCase(mode))
			{	
			args[4]=runner.singleSelection("select max(amend_id)+1 from MOTOR_TYPE_BODY_MASTER where type_of_body_id='"+args[2]+"' AND BRANCH_CODE='"+BranchCode+"'");
			}
			else
			{
				args[4]="0";
			}
			args[5]=body_name_eng;
			args[6]=body_name_arab;
			args[7]=date123;
			args[8]=status;
			args[9]=years;
			args[10]=liability;	
		    String sql="insert into MOTOR_TYPE_BODY_MASTER (BRANCH_CODE,PRODUCT_ID,TYPE_OF_BODY_ID,COREAPP_CODE,AMEND_ID,TYPE_OF_BODY_NAME,TYPE_OF_BODY_NAME_ARABIC,EFFECTIVE_DATE,STATUS,NO_OF_YEARS_ALLOWED,THIRD_PARTY_LIABILITY)values(?,?,?,?,?,?,?,?,?,?,?)";
		    runner.multipleInsertion(sql, args);
			return "Inserted Successfully";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Insertion Failed";
			
		}
	}
	public void isertOrUpdateThirdPartyFormData(String mode,String thirdpartyid)
	{
		
		 try
			{
			    String date123="";
		    	logger.info("method isertOrUpdateBodyFormData start "+thirdpartyid+" mode: "+mode);
				String args[]=new String[10];
				args[0]=BranchCode;
				args[1]="65";
				args[3]=typeId;
				if("edit".equalsIgnoreCase(mode))
				{
				args[2]=thirdpartyid;	
				}
			  	else
				{	
				args[2]=runner.singleSelection("select nvl(max(THIRD_PARTY_ID)+1,1) from MOTOR_TYPE_THIRD_PARTY where branch_code='"+BranchCode+"' and product_id='"+args[1]+"' ");
				}
				
				if (!effDay.equals("") && !effDay.equals("0")&& !effMon.equals("") && !effMon.equals("0")&& !effYear.equals("") && !effMon.equals("0")) 
				{
					date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMon + "-" + effYear);
				}
				if("edit".equalsIgnoreCase(mode))
				{	
				logger.info("hello");
				args[4]=runner.singleSelection("select nvl(max(amend_id)+1,1) from MOTOR_TYPE_THIRD_PARTY where THIRD_PARTY_ID='"+args[2]+"' and branch_code='"+BranchCode+"' and product_id='"+args[1]+"'");
				}
				else
				{
					args[4]="0";
				}
				args[5]=seatingCyll;
				args[6]=thirdRate;
				args[7]=date123;
				args[8]=thirdCoreAppCode;
				args[9]=status;
					
				String sql="insert into MOTOR_TYPE_THIRD_PARTY (BRANCH_CODE,PRODUCT_ID,THIRD_PARTY_ID,TYPEID,AMEND_ID,SEATING_CYLINDER,THIRD_PARTY_RATE,EFFECTIVE_DATE,COREAPP_CODE,STATUS) values (?,?,?,?,?,?,?,?,?,?)";
			    runner.multipleInsertion(sql, args);
			}
				catch(Exception e)
       			{
				e.printStackTrace();
                }
	}
	public String checkThirdPartyExistence()
	{
		logger.info("calledhfhf");
		String q="select count(*) from MOTOR_TYPE_THIRD_PARTY where SEATING_CYLINDER='"+seatingCyll+"' and THIRD_PARTY_RATE='"+thirdRate+"' and COREAPP_CODE='"+thirdCoreAppCode+"' and branch_code='"+BranchCode+"' and product_id='"+ProductId+"' and typeid='"+typeId+"' and amend_id in(select max(amend_id) from MOTOR_TYPE_THIRD_PARTY where product_id='"+ProductId+"' and branch_code='"+BranchCode+"' and typeid='"+typeId+"')";
		String i=runner.singleSelection(q);
		if(Integer.parseInt(i)>0)
		{
			return "Record Already Exist<br>";
		}
		else
		{
			return "";
		}
}
	public void isertOrUpdateMotorBodyLimit(String mode,String bodyid)
	{
	          System.out.println("Mode: "+mode);
   		
		 try
			{
			    String date123="";  
		    	logger.info("method isertOrUpdateBodybodylimit start "+typeId+""+bodyid+""+mode);
				String args[]=new String[16];
				args[1]=typeId;
				
				if("insert".equalsIgnoreCase(mode))
				{
					args[0]=runner.singleSelection("select nvl(max(typeid)+1,1) from MOTOR_TYPE_BODY_LIMIT  where  product_id=65 and branch_code='"+BranchCode+"' ");
				}
				
				else
				{
					args[0]=bodyid;
				}	
				args[2]=startage;
				args[3]=endage;
				args[4]=licperiod;
				args[5]=modelid.length()==0?"Nil":modelid;
				
			
				if (!effDay.equals("") && !effDay.equals("0")&& !effMon.equals("") && !effMon.equals("0")&& !effYear.equals("") && !effMon.equals("0")) 
				{
					date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMon + "-" + effYear);
				}
				if("edit".equalsIgnoreCase(mode))
				{	
				args[6]=runner.singleSelection("select max(amend_id)+1 from MOTOR_TYPE_BODY_LIMIT where typeid='"+bodyid+"' and branch_code="+BranchCode+" and product_id="+ProductId+" ");
				}
				else
				{
					args[6]="0";
				}
				args[8]=ProductId;
				args[7]=BranchCode;
				args[9]=date123;
				args[10]=status;
				args[11]=comboName;
				args[12]=licendperiod;
				args[13]=licencePeriod(licperiod,licendperiod);
				args[14]=brokerId;
				args[15]=vehicleId;
				System.out.println("vehicleId at doo--->"+vehicleId);
				
				String sql="insert into MOTOR_TYPE_BODY_LIMIT (TYPEID,TYPE_OF_BODY_ID,DRIVER_START_AGE,DRIVER_END_AGE,UAE_LICENCE_START,MODEL_ID,AMEND_ID,BRANCH_CODE,PRODUCT_ID,EFFECTIVEDATE,STATUS,COMBO_NAME,UAE_LICENCE_END,UAE_LICENCE_PERIOD,AGENCY_CODE,VEHICLE_TYPE) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			    runner.multipleInsertion(sql, args);
			}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
			    
	}
	
	public String licencePeriod(String start,String end)
	{
		String result="";
		int temp=0;
		try{
			temp=(Integer.parseInt(end)-Integer.parseInt(start));
		if(temp>1)
		result=">1";
		else
		result="<1";
		}catch(Exception e){System.out.println("Exception In licencePeriod(): "+e); result=">1";}
		return result;
	}
	
	public String checkMotorBodyLimtExistence(String mode)
	{
		/*if("insert".equalsIgnoreCase(mode))
		typeId="0";
		*/
		
		logger.info("modelid is"+modelid);
		//String query="select count(*) from MOTOR_TYPE_BODY_LIMIT where typeid="+typeId+" and product_id="+ProductId+" and branch_code="+BranchCode+" and DRIVER_START_AGE="+startage+" and DRIVER_END_AGE="+endage+" and UAE_LICENCE_PERIOD='"+licperiod+"' and MODEL_ID='"+modelid+"'and status='Y' and AGENCY_CODE='"+brokerId+"' and amend_id=(select max(amend_id) from motor_type_body_limit where typeid='"+typeId+"')";
		String query="select count(*) from MOTOR_TYPE_BODY_LIMIT where product_id="+ProductId+" and branch_code="+BranchCode+" and DRIVER_START_AGE="+startage+" and DRIVER_END_AGE="+endage+" and UAE_LICENCE_PERIOD='>"+licperiod+"' and MODEL_ID='"+modelid+"'and status='Y' and AGENCY_CODE='"+brokerId+"' and typeid not in ("+typeId+")";
	    logger.info(query);
		String i=runner.singleSelection(query);
	    
	     if(Integer.parseInt(i)>0)
	     {
	    	 return "Record Already Exist";
	     }
	     else
	    	 return "";
	    
	}
	public void isertOrUpdateMotorBodyRate(String mode,String typerate)
	{
		
		 try
			{
			   logger.info("type rate in bean"+typerate);
			    String date123="";
		    	logger.info("method isertOrUpdateBodyFormData start "+mode);
				String args[]=new String[15];
				args[0]=BranchCode;
				args[1]="65";
				if("insert".equalsIgnoreCase(mode))
				{	
				logger.info("hello"+BranchCode+""+ProductId);	
				args[2]=runner.singleSelection("select nvl(max(TYPE_RATE_ID)+1,1) from MOTOR_TYPE_BODY_RATE where product_id="+args[1]+" and branch_code="+BranchCode+"");
				logger.info("hello"+args[2]);
				}
				else
				{
					logger.info("helloelse");
					args[2]=typerate;	
				}
				args[3]=typeId;
				args[4]=agencyRepairYear;
				args[5]=rate;
				args[6]=minpremium;
				args[7]=seatingstart;
				args[8]=seatingend;
				args[9]=startsum;
				args[10]=endsum;
				
				if (!effDay.equals("") && !effDay.equals("0")&& !effMon.equals("") && !effMon.equals("0")&& !effYear.equals("") && !effMon.equals("0")) 
				{
					logger.info("inside date format "+effDay);
					date123=com.maan.common.util.OracleDateConversion.ConvertDate(effDay + "-" + effMon + "-" + effYear);
				}
				if("insert".equalsIgnoreCase(mode))
				{
					args[11]="0";
				
				}
				else
				{
					args[11]=runner.singleSelection("select max(amend_id)+1 from MOTOR_TYPE_BODY_RATE where TYPE_RATE_ID='"+args[2]+"' and branch_code="+BranchCode+" and product_id="+args[1]+" ");
				}
				args[12]=date123;
				args[13]=status;
				args[14]=policyTypeId;
				
				String sql="insert into MOTOR_TYPE_BODY_RATE (BRANCH_CODE,PRODUCT_ID,TYPE_RATE_ID,TYPEID,AGENCY_REPAIR_YEAR,RATE,MINIMUM_PREMIUM,SEATING_TON_START,SEATING_TON_END,START_SUMINSURED,END_SUMINSURED,AMEND_ID,EFFECTIVE_DATE,STATUS,POLICYTYPE_ID) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			    runner.multipleInsertion(sql, args);
			    System.out.println("Query For isertOrUpdateMotorBodyRate()");  
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	public String checkMotorBodyRateExistence()
	{
		String query="select count(*) from MOTOR_TYPE_BODY_RATE where AGENCY_REPAIR_YEAR='"+agencyRepairYear+"' AND RATE='"+rate+"' AND MINIMUM_PREMIUM='"+minpremium+"' AND SEATING_TON_START='"+seatingstart+"' AND SEATING_TON_END='"+seatingend+"' AND START_SUMINSURED='"+startsum+"' AND END_SUMINSURED='"+endsum+"' AND PRODUCT_ID=65 AND BRANCH_CODE='"+BranchCode+"' AND TYPEID='"+typeId+"' AND POLICYTYPE_ID='"+policyTypeId+"'";
        String i=runner.singleSelection(query);
        if(Integer.parseInt(i)>0)
        {	
          return "Record Already Exist<br>";
        }
        else
        	return "";
		
	}
	public String[][] getMotorBodyTypeId(String branchCode,String productID)
	{
		String result[][]=new String[0][0]; 	
		try
    	{
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=productID;
		String sql="select TYPE_OF_BODY_ID,TYPE_OF_BODY_NAME,NO_OF_YEARS_ALLOWED,THIRD_PARTY_LIABILITY from MOTOR_TYPE_BODY_MASTER mtb where branch_code=? and product_id=? and status='Y' and amend_id = (select max(amend_id) from motor_type_body_master where product_id=mtb.product_id and branch_code=mtb.branch_code and type_of_body_id=mtb.type_of_body_id) order by type_of_body_id";
		result=runner.multipleSelection(sql, cols);
    	}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return result;
	}

	public String[][] getMotorTypeId(String branchCode,String productID,String bodyid)
	{
		String result[][]=new String[0][0]; 	
		try
    	{
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=productID;
		String sql="select TYPEID from MOTOR_TYPE_BODY_Limit mtb where branch_code=? and product_id=? and status='Y' and type_of_body_id="+bodyid+"  and amend_id = (select max(amend_id) from motor_type_body_limit where product_id=mtb.product_id and branch_code=mtb.branch_code and typeid=mtb.typeid) order by typeid";
		result=runner.multipleSelection(sql, cols);
    	}
		catch(Exception e)
		{
			
		 e.printStackTrace();
		}
		return result;
	}
	public String[][] getMotorBody(String branchCode,String productID)
	{
		String result[][]=new String[0][0]; 	
		try
    	{
		String cols[]=new String[2];
		cols[0]=branchCode;
		cols[1]=productID;
		String sql="select TYPE_OF_BODY_ID,TYPE_OF_BODY_NAME,NO_OF_YEARS_ALLOWED,THIRD_PARTY_LIABILITY from MOTOR_TYPE_BODY_MASTER mtb where branch_code=? and product_id=? and amend_id = (select max(amend_id) from motor_type_body_master where product_id=mtb.product_id and branch_code=mtb.branch_code and type_of_body_id = mtb.type_of_body_id) order by type_of_body_id";
		result=runner.multipleSelection(sql, cols);
    	}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		return result;
	}
	
	public void getMotorBodyEdit(String typeid,String productID,String branchCode)
	{
		String result[][]=new String[0][0]; 	
		try
    	{
		String cols[]=new String[3];
		cols[0]=typeid;
		cols[1]=branchCode;
		cols[2]=productID;
		String sql="select BRANCH_CODE,PRODUCT_ID,TYPE_OF_BODY_ID,COREAPP_CODE,AMEND_ID,TYPE_OF_BODY_NAME,TYPE_OF_BODY_NAME_ARABIC,to_char(EFFECTIVE_DATE,'YYYY-MM-DD'),STATUS,NO_OF_YEARS_ALLOWED,THIRD_PARTY_LIABILITY from MOTOR_TYPE_BODY_MASTER where type_of_body_id=? and  branch_code=? and product_id=? and amend_id=(select max(amend_id) from MOTOR_TYPE_BODY_MASTER where  branch_code="+branchCode+" and product_id="+productID+" and type_of_body_id='"+typeid+"')";
		result=runner.multipleSelection(sql, cols);
    	}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		typeId=result[0][2];
		body_name_eng=result[0][5];
		body_name_arab=result[0][6];
		years=result[0][9];
		liability=result[0][10];
		status=result[0][8];
		String date=result[0][7];
		logger.info("hi"+result.length);
		if(date!=null )
		{
			logger.info("hi");
			StringTokenizer st =new StringTokenizer(date,"-");
		    while(st.hasMoreTokens())
		    {
		    	logger.info("hi");
		    	effYear=st.nextToken();
		    	effMon=st.nextToken();
		    	effDay=st.nextToken();
		    	
		    }
		    	
	    }
		
		logger.info("hello"+date);	
		appCode=result[0][3];
		 
		
	}
public String[][] getMakeIds(String branchCode)
{
	String result[][]=new String[0][0]; 	
	try
   	{
	String cols[]=new String[1];
	cols[0]=branchCode;
	String sql="select MAKE_ID,MAKE_NAME from MOTOR_MAKE_MASTER mm where branch_code=? and  amend_id=(select max(amend_id) from MOTOR_MAKE_MASTER where branch_code=mm.branch_code and make_id=mm.make_id) and status='Y' ";
	result=runner.multipleSelection(sql, cols);
   	}
	catch(Exception e)
	{
	 e.printStackTrace();
	}
	return result;
}
public String[][] getModel(String makeId,String branchCode, String typeID)
{
	String result[][]=new String[0][0]; 	
	try
   	{
	String cols[]=new String[3];
	cols[0]=branchCode;
	cols[1]=makeId;
	cols[2]=typeID;
	String sql="select MODEL_ID,MODEL_NAME from MOTOR_MODEL_MASTER mm where branch_code=? and make_id=? and status='Y' and body_id=? and amend_id=(select max(amend_id) from MOTOR_MODEL_MASTER where branch_code=mm.branch_code and make_id=mm.make_id and body_id=mm.body_id and model_id=mm.model_id )";
	result=runner.multipleSelection(sql, cols);
   	}
	catch(Exception e)
	{
	 e.printStackTrace();
	}
	return result;
}
public String[] getModelNames(String branchCode,String[] modelId)
{
	logger.info("model ID length"+modelId.length);
	String model[][]=new String[0][0];
	String result[]=new String[2];
	StringBuffer s=new StringBuffer();
	StringBuffer s1=new StringBuffer();
	StringBuffer s2=new StringBuffer();
	
	for(int i=0;i<modelId.length;i++)
	{
		s.append(modelId[i]);
		if(i==modelId.length-1)
			break;
		s.append(",");
		logger.info("model ID "+s);
	}
	for(int i=0;i<modelId.length;i++)
	{
		s1.append(modelId[i]);
		if(i==modelId.length-1)
			break;
		s1.append("~");
		logger.info("model ID "+s1);
	}
	String sql="select MODEL_NAME from MOTOR_MODEL_MASTER mm where BRANCH_CODE='"+branchCode+"' and MODEL_ID IN ("+s+") and amend_id=(select max(amend_id) from motor_model_master where branch_code=mm.branch_code and make_id=mm.make_id and model_id=mm.model_id)";
	model=runner.multipleSelection(sql);
	for(int i=0;i<model.length;i++)
	{
		s2.append(model[i][0]);
		if(i==model.length-1)
			break;
		s2.append("~");
		logger.info("model name "+s2);
	}
	result[0]=s1.toString();
	result[1]=s2.toString();
	return result;
}
public String[][] getMotorBodyLimit(String typeId,String productId,String branchCode)
{
	String result[][]=new String[0][0];
	String cols[]=new String[3];
	String sql="select typeid,driver_start_age,driver_end_age,model_id,type_of_body_id,VEHICLETYPE_DESC_ENGLISH,NVL((SELECT username from login_master where agency_code=mas.AGENCY_CODE ),'None') broker,nvl(agency_code,'None') from motor_type_body_limit mas,MOTOR_VEHICLETYPE_MASTER mvm where type_of_body_id=? and mas.branch_code=mvm.branch_code and mas.Vehicle_type=VTYPE_ID and mas.branch_code=? and product_id=? and mas.amend_id=(select max(amend_id) from motor_type_body_limit  where BRANCH_CODE=mas.BRANCH_CODE  AND PRODUCT_ID=mas.PRODUCT_ID and type_of_body_id=mas.type_of_body_id   and typeid=mas.typeid) and mvm.amend_id=(select max(amend_id) from MOTOR_VEHICLETYPE_MASTER where branch_code=mvm.branch_code and vtype_id=mvm.vtype_id) order by typeid";
	cols[0]=typeId;
	cols[1]=branchCode;
	cols[2]=productId;
	
	result=runner.multipleSelection(sql, cols);	
	return result;
}
public String[][] getMotorRate(String typeId,String productId,String branchCode)
{
	String result[][]=new String[0][0];
	String cols[]=new String[3];
	System.out.println("typeId: "+typeId+" productId: "+productId+" branchCode: "+branchCode);
	String sql="select TYPEID,TYPE_RATE_ID,AGENCY_REPAIR_YEAR,RATE,MINIMUM_PREMIUM,SEATING_TON_START,SEATING_TON_END,START_SUMINSURED,END_SUMINSURED,(select POLICYTYPE_NAME from MOTOR_POLICYTYPE_MASTER mp where POLICYTYPE_ID=mr.POLICYTYPE_ID and branch_code=mr.branch_code and amend_id=(select max(amend_id) from MOTOR_POLICYTYPE_MASTER where POLICYTYPE_ID=mp.POLICYTYPE_ID and branch_code=mp.branch_code)) POLICY_TYPE from motor_type_body_rate mr where typeid=? and branch_code=? and product_id=? and amend_id||'-'||type_rate_id in(select max(amend_id)||'-'||type_rate_id from motor_type_body_rate where BRANCH_CODE='"+branchCode+"' AND PRODUCT_ID='"+productId+"' and typeid="+typeId+" group by type_rate_id)";
	cols[0]=typeId;
	cols[1]=branchCode;
	cols[2]=productId;
	result=runner.multipleSelection(sql, cols);	
	return result;
}
public void getMotorRateEdit(String typeId,String typerate,String productId,String branchCode)
{
	String result[][]=new String[0][0];
	String cols[]=new String[8];
	String sql="select TYPEID,AGENCY_REPAIR_YEAR,RATE,MINIMUM_PREMIUM,SEATING_TON_START,SEATING_TON_END,START_SUMINSURED,END_SUMINSURED,to_char(EFFECTIVE_DATE,'YYYY-MM-DD'),STATUS,POLICYTYPE_ID  from motor_type_body_rate where typeid=? and type_rate_id=? and branch_code=? and product_id=? and amend_id=(select max(amend_id) from motor_type_body_RATE where typeid=? and branch_code=? and product_id=? and type_rate_id=?)";
	cols[0]=typeId;
	cols[1]=typerate;
	cols[2]=branchCode;
	cols[3]=productId;
	cols[4]=typeId;
	cols[5]=branchCode;
	cols[6]=productId;
	cols[7]=typerate;
	result=runner.multipleSelection(sql, cols);	
	
	agencyRepairYear=result[0][1];
	rate=result[0][2];
	startsum=result[0][6];
	seatingstart=result[0][4];
	seatingend=result[0][5];
	minpremium=result[0][3];
	endsum=result[0][7];
	String date=result[0][8];
	status=result[0][9];
	policyTypeId=result[0][10];
	
	logger.info(date);
	if(date!=null && date.length()>0)
	{
		StringTokenizer st=new StringTokenizer(date,"-");
		while(st.hasMoreTokens())
		{
		effYear=st.nextToken();
		effMon=st.nextToken();
		effDay=st.nextToken();
		}	
	}

}

	public void getMotorBodyLimitEdit(String typeId,String bodyid,String productId,String branchCode)
	{
		String result[][]=new String[0][0];
		String cols[]=new String[7];
		String sql="select TYPEID,TYPE_OF_BODY_ID,DRIVER_START_AGE,DRIVER_END_AGE,UAE_LICENCE_START,MODEL_ID,AMEND_ID,BRANCH_CODE,PRODUCT_ID,to_char(EFFECTIVEDATE,'YYYY-MM-DD'),STATUS,COMBO_NAME,UAE_LICENCE_END,VEHICLE_TYPE  from motor_type_body_LIMIT where typeid=? and type_OF_BODY_id=? and branch_code=? and product_id=? and amend_id=(select max(amend_id) from motor_type_body_LIMIT where   branch_code=? and product_id=? and typeid=?)";
		cols[0]=bodyid;
		cols[1]=typeId;
		cols[2]=branchCode;
		cols[3]=productId;
		cols[4]=branchCode;
		cols[5]=productId;
		cols[6]=bodyid;
		result=runner.multipleSelection(sql, cols);
		typeId=result[0][0];
		startage=result[0][2];
		endage=result[0][3];
		licperiod=result[0][4];
		modelid=result[0][5];
		status=result[0][10];
		licendperiod=result[0][12];
		vehicleId=result[0][13];
		String date=result[0][9];
		logger.info(date);
		if(date!=null && date.length()>0)
		{
			StringTokenizer st=new StringTokenizer(date,"-");
			while(st.hasMoreTokens())
			{
			effYear=st.nextToken();
			effMon=st.nextToken();
			effDay=st.nextToken();
			}	
		}
	}
	public void getMotorModelEdit(String modelId,String branchCode)
	{
		String result[][]=new String[0][0];
		String cols[]=new String[2];
		String sql="select MODEL_ID,MAKE_ID,BODY_ID,MODEL_NAME,MODEL_NAME_ARABIC,CORE_CODE,substr((EFFECTIVE_DATE),1,10)AS Effective_date,STATUS   from MOTOR_MODEL_MASTER mm where  BRANCH_CODE=?  and MODEL_ID=? and STATUS='Y' and amend_id=(select max(amend_id) from motor_model_master where branch_code=mm.branch_code and make_id=mm.make_id and model_id=mm.model_id)";
		cols[0]=modelId;
		cols[1]=branchCode;
		result=runner.multipleSelection(sql, cols);
		modelid=result[0][0];
		makeId=result[0][1];
		bodyId=result[0][2];
		modelName=result[0][3];
		modelNameArabic=result[0][4];
		coreCode=result[0][5];
		status=result[0][7];
		String date=result[0][6];
		logger.info(date);
		if(date!=null && date.length()>0)
		{
			StringTokenizer st=new StringTokenizer(date,"-");
			while(st.hasMoreTokens())
			{
			effYear=st.nextToken();
			effMon=st.nextToken();
			effDay=st.nextToken();
			}	
		}
		
	}
	public String getMakeId(String modelId,String BranchCode,String typeId)
	{
		StringBuffer sb=new StringBuffer();
		logger.info(""+modelId);
		StringTokenizer st=new StringTokenizer(modelId,"-");
		while(st.hasMoreTokens())
		{
			sb.append(st.nextToken());
			sb.append(",");
			logger.info(""+sb);
		}
		sb.delete(sb.length()-1, sb.length());
		String result[][]=new String[0][0];
		result=runner.multipleSelection("select make_id from motor_model_master mm where model_id in("+sb+") and body_id='"+typeId+"' and branch_code='"+BranchCode+"' and amend_id=(select max(amend_id) from motor_model_master where branch_code=mm.branch_code and make_id=mm.make_id and model_id=mm.model_id)");
		String s=result[0][0];
		logger.info("modelid is"+s);
		return s;
	}
	public String getModelNamesinedit(String modelid,String branchCode,String typeid)
	{
		String modelid1=modelid.replace('-', ',');
	    StringBuffer sb=new StringBuffer();
		String result[][]=new String[0][0];
		result=runner.multipleSelection("select model_name from motor_model_master mm where model_id in("+modelid1+") and body_id='"+typeid+"' and branch_code='"+branchCode+"' and amend_id=(select max(amend_id) from motor_model_master where branch_code=mm.branch_code and make_id=mm.make_id and model_id=mm.model_id)");
		for(int i=0;i<result.length;i++)
		{
			logger.info("final string ============="+result.length);
			sb.append(result[i][0]);
			if(i==result.length-1)
				break;
			sb.append(",");
			
		}
		logger.info("final string ============="+sb.toString());
		return sb.toString();
		
	}
	public String[][] getMotorThirdParty(String branchCode,String productId,String typeid)
	{
	 String result[][]=new String[0][0];
	 //String sql="select TYPEID,THIRD_PARTY_ID,SEATING_CYLINDER,THIRD_PARTY_RATE,COREAPP_CODE from motor_type_third_party where  typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' and THIRD_PARTY_ID||amend_id in(select third_party_id||max(amend_id) from motor_type_third_party where  typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' group by THIRD_PARTY_ID)";
	 String sql="select TYPEID,THIRD_PARTY_ID,(SELECT CYLINDER_DESC_ENGLISH FROM MOTOR_CYCLINDER_MASTER mcm WHERE CYL_ID=MP.SEATING_CYLINDER AND STATUS='Y' AND BRANCH_CODE=MP.branch_code AND AMEND_ID=(SELECT MAX(AMEND_ID) FROM MOTOR_CYCLINDER_MASTER WHERE CYL_ID=MP.SEATING_CYLINDER and branch_code=mcm.branch_code)  ) SEATING_CYLINDER,THIRD_PARTY_RATE,COREAPP_CODE from motor_type_third_party MP where  typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' and amend_id=(select max(amend_id) from motor_type_third_party where  typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' and THIRD_PARTY_ID=MP.third_party_id)";
	 
	 result=runner.multipleSelection(sql);
     return result;
	}
	public void getMotorThirdPartyEdit(String productId,String branchCode,String typeid,String thirdparty)
	{
		String result[][] =new String[0][0];
		String sql="select TYPEID,SEATING_CYLINDER,THIRD_PARTY_RATE,to_char(EFFECTIVE_DATE,'YYYY-MM-DD'),COREAPP_CODE,STATUS from motor_type_third_party where typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' and third_party_id='"+thirdparty+"' and amend_id=(select max(amend_id) from motor_type_third_party where typeid="+typeid+" and product_id='"+productId+"' and branch_code='"+branchCode+"' and third_party_id='"+thirdparty+"')  ";
		result=runner.multipleSelection(sql);
		status=result[0][5];
		String date=result[0][3];
		
		if(date!=null)
		{	
		logger.info("hello");	
		StringTokenizer st=new StringTokenizer(date,"-");
		{
		effYear=st.nextToken();
		effMon=st.nextToken();
		effDay=st.nextToken();
		}
		}
		thirdCoreAppCode=result[0][4];
		seatingCyll=result[0][1];
		thirdRate=result[0][2];
	 }
	
	 public boolean containsOnlyNumbers(String str) 
     {
		 try {
			 Integer.parseInt(str);
	        }
		 catch (NumberFormatException ex) {
	     return true;
	     }
	     return false;
	 }
	 public boolean containsOnlyDecimalNumbers(String str) 
     {
		 try {
			 Float.parseFloat(str);
	        }
		 catch (NumberFormatException ex) {
	     return true;
	     }
	     return false;
	 }
     public String checkValidDate(String ddmmyyyy)
 	{
 		DateFormat df = new SimpleDateFormat ("dd-MM-yyyy"); 
         df.setLenient(false);
 		
     	Date dt = new Date();
 		try{
 			dt = df.parse (ddmmyyyy);
 		   return "";
 		}
 		catch(ParseException e){
 		 return "Enter ValidDate";	
 		}
 		
     }
     public String isValidDate(String date)
     {
    	 String s="";
    	 try
    	 {
    	  s=checkValidDate(date);
    	 if(s.length()>0)
    	 {
    		 s= "Enter Valid Date";
    	 }
    	 else
    	 {
    	 Calendar cal = Calendar.getInstance();
    	 DateFormat df = new SimpleDateFormat ("dd-MM-yyyy");
    	 Date date1 = df.parse (date);
         cal.set(Calendar.HOUR_OF_DAY, 0);
         cal.set(Calendar.MINUTE, 0);
         cal.set(Calendar.SECOND, 0);
         cal.set(Calendar.MILLISECOND, 0);
         if (cal.getTime().after(date1))
        	 s= "Effective date should be greater or equal to current date";
         else
        	 s= "";
    	 }
    	 }
    	 catch(Exception e)
    	 {
    		s ="";			 
    	 }
    	 return s;
     }
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getReqFrom() {
		return reqFrom;
	}
	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}
	public String getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(String brokerId) {
		this.brokerId = brokerId;
	}
	
	

public String insertMakeEdit(String branchCode,String makeId,MakeBean bean, String mode)
{
	String result=null;
	String date123=null;
	if (!bean.getEffDate().equals("") && !bean.getEffDate().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
	{
		date123=com.maan.common.util.OracleDateConversion.ConvertDate(bean.getEffDate() + "-" + bean.getEffMonth() + "-" + bean.getEffYear());
	}
	bean.setEffectiveDate(date123);
	if (makeId==null || (makeId.trim()).length()<=0 || (makeId.trim()).equals("0") || !"Edit".equalsIgnoreCase(mode)) {
		 String q="SELECT NVL(MAX(MAKE_ID)+1,0) FROM MOTOR_MAKE_MASTER where branch_code='"+branchCode+"'";
		 
		 makeId=runner.singleSelection(q);
		 
		 
	}
	try
	{
		String sql="INSERT INTO MOTOR_MAKE_MASTER(MAKE_ID,MAKE_NAME,AMEND_ID,MAKE_NAME_ARABIC,CORE_CODE,EFFECTIVE_DATE,STATUS,BRANCH_CODE,REMARKS,REFERRAL_STATUS) VALUES (?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_MAKE_MASTER WHERE MAKE_ID="+makeId+" and branch_code='"+branchCode+"'),?,?,TO_DATE('"+bean.getEffectiveDate()+"','dd/MM/yyyy'),?,?,?,?)";
		String cols[]=new String[8];
		cols[0]=makeId;
		cols[1]=bean.getMakeName();
		cols[2]=bean.getMakeArabic();
		cols[3]=bean.getCoreCode();
		cols[4]=bean.getStatus();
		cols[5]=branchCode;
		cols[6]=bean.getRemarks();
		cols[7]=bean.getReferalStatus();
		
		System.out.println(".............EDIT QUERY...........");
		System.out.println(sql);
		result=runner.multipleInsertion(sql,cols);
		return "Inserted Successfully";	
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return "Insertion Failed";
	}

}
public String[][] getModelEdit(String branchCode,String makeId)
{
	String result[][]=new String[0][0];
	try
	{
	String sql="select MODEL_ID,MAKE_ID,MODEL_NAME,MODEL_NAME_ARABIC,CORE_CODE,substr((EFFECTIVE_DATE),1,10) AS Effective_date,STATUS from MOTOR_MODEL_MASTER mm  where branch_code=? and make_id=? and amend_id=(select max(amend_id) from MOTOR_MODEL_MASTER where mm.branch_code=branch_code and mm.make_id=make_id and mm.model_id=model_id)";
	String cols[]=new String[2];
	cols[0]=branchCode;
	cols[1]=makeId;
	result=runner.multipleSelection(sql, cols);
	 	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return result;
}

public String isVAlidModelDate(String ddmmyyyy,String modelId,String branchCode)
	{
	String sql="SELECT distinct (CASE WHEN (TO_DATE('"+ddmmyyyy+"','DD-MM-YYYY')-TO_DATE(nvl(to_char(effective_date,'dd-mm-yyyy'),to_char(sysdate,'dd-mm-yyyy')),'DD-MM-YYYY'))>=0 THEN 'YES' ELSE 'NO' END) FROM MOTOR_MODEL_MASTER ac where ac.branch_code=? and ac.amend_id in (select max(amend_id) from MOTOR_MODEL_MASTER where branch_code=? and model_id=ac.model_id )and ac.model_id=?";
	String cols[]=new String[3];
	cols[0]=branchCode;
	cols[1]=branchCode;
	cols[2]=modelId;
	String result=runner.singleSelection(sql, cols);
	
	return result;
	
	}




public String insertModelEdit(String branchCode,String mode, MakeBean bean)
{
	String result=null;
	String date123=null;
	
	String modelId = bean.getModelId();
	if (!bean.getEffDate().equals("") && !bean.getEffDate().equals("0")&& !bean.getEffMonth().equals("") && !bean.getEffMonth().equals("0")&& !bean.getEffYear().equals("") && !bean.getEffMonth().equals("0")) 
	{
		date123=com.maan.common.util.OracleDateConversion.ConvertDate(bean.getEffDate() + "-" + bean.getEffMonth() + "-" + bean.getEffYear());
	}
	bean.setEffectiveDate(date123);
	if (modelId==null || (modelId.trim()).length()<=0 || (modelId.trim()).equals("0") || !"Edit".equalsIgnoreCase(mode)) {
		 String q="SELECT NVL(MAX(MODEL_ID)+1,0) FROM MOTOR_MODEL_MASTER where branch_code='"+branchCode+"'";
		 
		 modelId=runner.singleSelection(q);
		 
		 
	}
	try
	{
		System.out.println("Make Id IS"+makeId);
		System.out.println("Effective Date:;;;;;;;;;;;;;;;;;;;;;;;;;;;;"+bean.getEffDate());
		//String sql="INSERT INTO MOTOR_MODEL_MASTER(BODY_ID,MAKE_ID,MODEL_NAME,AMEND_ID,MODEL_NAME_ARABIC,CORE_CODE,EFFECTIVE_DATE,STATUS,BRANCH_CODE,REFERRAL_STATUS,CATEGORY_ID,MODEL_ID,REMARKS) VALUES (?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_MODEL_MASTER WHERE model_id=? and make_id='"+bean.getMakeId()+"' and branch_code='"+branchCode+"'),?,?,TO_DATE('"+bean.getEffectiveDate()+"','dd/MM/yyyy'),?,?,?,?,?,?)";
		
		String sql="INSERT INTO MOTOR_MODEL_MASTER(BODY_ID,MAKE_ID,MODEL_NAME,AMEND_ID,MODEL_NAME_ARABIC,CORE_CODE,EFFECTIVE_DATE,STATUS,BRANCH_CODE,REFERRAL_STATUS,CATEGORY_ID,MODEL_ID,REMARKS) VALUES (?,?,?,(SELECT NVL(MAX(AMEND_ID)+1,0) FROM MOTOR_MODEL_MASTER WHERE model_id=? and make_id='"+bean.getMakeId()+"' and branch_code='"+branchCode+"'),?,?,TO_DATE('"+bean.getEffectiveDate()+"','dd/MM/yyyy'),?,?,?,?,?,?)";
		String cols[]=new String[12];
		cols[0]=bean.getBodyId();
		cols[1]=bean.getMakeId();
		cols[2]=bean.getModelName();
		cols[3]=modelId;
		cols[4]=bean.getModelArabic();
		cols[5]=bean.getCoreCode();
		cols[6]=bean.getStatus();
		cols[7]=branchCode;
		cols[8]=bean.getReferalStatus();
		cols[9]=bean.getCategoryId();
		cols[10]=modelId;
		cols[11]=bean.getRemarks();
		
		
		
		System.out.println(".............EDIT QUERY...........");
		System.out.println(sql);
		result=runner.multipleInsertion(sql,cols);
		return "Inserted Successfully";	
	
	}catch(Exception e)
	{
		e.printStackTrace();
		return "Insertion Failed";
	}

}

public ArrayList getModelList( String[][] value)
{
	ArrayList list = new ArrayList();
	MakeBean bean=new MakeBean();
	try{
		System.out.println("Entry to List");
		
		for (int i = 0; i < value.length; i++) {
				bean = new MakeBean();
				bean.setModelId(value[i][0] == null ? "" : value[i][0]);
				bean.setMakeId(value[i][1] == null ? "" : value[i][1]);
				bean.setModelName(value[i][2] == null ? "" : value[i][2]);
				bean.setModelArabic(value[i][3] == null ? "": value[i][3]);
				bean.setCoreCode(value[i][4] == null ? "" : value[i][4]);
				bean.setEffectiveDate(value[i][5] == null ? "" : value[i][5]);
				bean.setStatus(value[i][6] == null ? "" : value[i][6]);
				list.add(bean);
		}
	}
	catch(Exception ex){
		ex.printStackTrace();
	}
System.out.println("Exit from List");

return list;
}
public String getVehicleId() {
	return vehicleId;
}
public void setVehicleId(String vehicleId) {
	this.vehicleId = vehicleId;
}
}
